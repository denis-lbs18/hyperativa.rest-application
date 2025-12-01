package com.hyperativa.rest_application.builder.dtos;

import com.hyperativa.rest_application.dtos.CardDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.hyperativa.rest_application.builder.ConstantsBuilder.DEFAULT_CARD_NUMBER;
import static com.hyperativa.rest_application.builder.ConstantsBuilder.CHARGE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CardDtoBuilder {
    public static CardDto buildCardDto() {
        return CardDto.builder()
                .number(DEFAULT_CARD_NUMBER)
                .charge(CHARGE)
                .build();
    }

    public static List<CardDto> buildCardDtoList() {
        CardDto card1 = CardDto.builder()
                .number(DEFAULT_CARD_NUMBER + "1")
                .charge(CHARGE)
                .build();

        CardDto card2 = CardDto.builder()
                .number(DEFAULT_CARD_NUMBER + "2")
                .charge(CHARGE)
                .build();

        return List.of(card1, card2);
    }
}
