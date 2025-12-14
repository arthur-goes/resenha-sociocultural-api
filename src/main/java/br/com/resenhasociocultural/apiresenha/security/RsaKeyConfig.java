package br.com.resenhasociocultural.apiresenha.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.text.ParseException;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class RsaKeyConfig {

    @Value("${spring.jwt.rsa-private-key.json}")
    private String rsaPrivateKeyJson;

    @Bean
    @ConditionalOnMissingBean(RsaKeyFetcher.class)
    public RsaKeyFetcher onRunGeneratedKeyFetcher() {
        return () -> {
            try {
                log.warn("Could not load RSA key from configuration. Generating a temporary key for this run. Previously issued JWT tokens may become invalid.");
                return new RSAKeyGenerator(2048)
                    .keyUse(KeyUse.SIGNATURE)
                    .algorithm(JWSAlgorithm.RS256)
                    .keyIDFromThumbprint(true)
                    .generate();
            } catch (JOSEException e) {
                throw new RuntimeException("Failed to generate temporary RSA key.", e);
            }
        };
    }

    @Bean
    @Primary
    @ConditionalOnExpression("T(org.springframework.util.StringUtils).hasText('${spring.jwt.rsa-private-key.json}')")
    RsaKeyFetcher enviromentVariableKeyFetcher() throws ParseException {
        return () -> {
            log.debug("Obtaining RSA JSON from properties.");
            return RSAKey.parse(this.rsaPrivateKeyJson);
        };
    }

}
