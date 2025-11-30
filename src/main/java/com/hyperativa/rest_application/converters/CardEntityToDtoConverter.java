package com.hyperativa.rest_application.converters;

import com.hyperativa.rest_application.dtos.CardDto;
import com.hyperativa.rest_application.entities.Card;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CardEntityToDtoConverter implements Converter<Card, CardDto> {
    @Override
    public CardDto convert(@NonNull Card source) {
        CardDto tartget = new CardDto();
        BeanUtils.copyProperties(source, tartget);
        return tartget;
    }
}
