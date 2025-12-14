package br.com.resenhasociocultural.apiresenha.config;

import br.com.resenhasociocultural.apiresenha.features.youth.YouthViewType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToYouthViewConverter implements Converter<String, YouthViewType> {
    @Override
    public YouthViewType convert(String source) {
        if (source.isBlank()){
            return null;
        }
        return YouthViewType.valueOf(source.toUpperCase());
    }
}
