package com.hyperativa.rest_application.builder.entities;

import com.hyperativa.rest_application.entities.Card;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.hyperativa.rest_application.builder.ConstantsBuilder.DEFAULT_CARD_NUMBER;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CardBuilder {
    public static Card buildCard() {
        return Card.builder()
                .id(1L)
                .number(DEFAULT_CARD_NUMBER)
                .build();
    }

    public static Iterable<Card> buildCardList() {
        Card card1 = Card.builder()
                .id(1L)
                .number(DEFAULT_CARD_NUMBER + "1")
                .build();

        Card card2 = Card.builder()
                .id(2L)
                .number(DEFAULT_CARD_NUMBER + "2")
                .build();

        return java.util.List.of(card1, card2);
    }
}
