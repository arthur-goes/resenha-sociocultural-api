package br.com.resenhasociocultural.apiresenha.security;

import com.nimbusds.jose.jwk.RSAKey;

import java.text.ParseException;

public interface RsaKeyFetcher {
    RSAKey fetchRsaKey() throws ParseException;
}
