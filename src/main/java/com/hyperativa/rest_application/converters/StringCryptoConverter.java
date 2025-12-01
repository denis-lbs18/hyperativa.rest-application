package com.hyperativa.rest_application.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

@Converter
public class StringCryptoConverter implements AttributeConverter<String, String> {
    @Value("${jasypt.encryptor.password}")
    private String password;
    private static final String ENCRYPTION_PASSWORD_PROPERTY = "jasypt.encryptor.password";

    private final StandardPBEStringEncryptor encryptor;

    public StringCryptoConverter(Environment environment) {

        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setPassword(environment.getProperty(ENCRYPTION_PASSWORD_PROPERTY));
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return encryptor.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return encryptor.decrypt(dbData);
    }
}