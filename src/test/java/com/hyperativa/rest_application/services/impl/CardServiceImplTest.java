package com.hyperativa.rest_application.services.impl;

import com.hyperativa.rest_application.converters.CardEntityToDtoConverter;
import com.hyperativa.rest_application.dtos.CardDto;
import com.hyperativa.rest_application.repositories.CardRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static com.hyperativa.rest_application.builder.ConstantsBuilder.ANY_STRING;
import static com.hyperativa.rest_application.builder.ConstantsBuilder.DEFAULT_CARD_NUMBER;
import static com.hyperativa.rest_application.builder.MultiPartFileBuilder.buildMultiPartFile;
import static com.hyperativa.rest_application.builder.entities.CardBuilder.buildCard;
import static com.hyperativa.rest_application.builder.entities.CardBuilder.buildCardList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@MockitoSettings
class CardServiceImplTest {
    @InjectMocks
    private CardServiceImpl service;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private CardEntityToDtoConverter converter;

    @Test
    void shouldPass_WhenSaveExistingCard_ReturnsCardDto() {
        when(cardRepository.existsByNumber(anyString())).thenReturn(true);
        when(cardRepository.findByNumber(anyString())).thenReturn(java.util.Optional.ofNullable(buildCard()));
        when(converter.convert(any())).thenCallRealMethod();

        CardDto cardDto = service.saveCard(DEFAULT_CARD_NUMBER);

        assertEquals(DEFAULT_CARD_NUMBER, cardDto.getNumber());
    }

    @Test
    void shouldPass_WhenSaveNewCard_ReturnsCardDto() {
        when(cardRepository.existsByNumber(anyString())).thenReturn(false);
        when(converter.convert(any())).thenCallRealMethod();
        when(cardRepository.save(any())).thenReturn(buildCard());

        CardDto cardDto = service.saveCard(DEFAULT_CARD_NUMBER);

        assertEquals(DEFAULT_CARD_NUMBER, cardDto.getNumber());
    }

    @Test
    void shouldFail_WhensaveCard_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> service.saveCard(ANY_STRING));
    }

    @SneakyThrows
    @Test
    void shouldPass_WhenSaveFromTxtFile_ReturnsListOfCardDto() {
        when(cardRepository.existsByNumber(anyString())).thenReturn(false);
        when(cardRepository.save(any())).thenReturn(buildCard());
        when(converter.convert(any())).thenCallRealMethod();

        List<CardDto> cardDtos = service.saveFromTxtFile(buildMultiPartFile());
        assertEquals(10, cardDtos.size());
    }

    @Test
    void shouldPass_WhenFindAll_ReturnListOfCardDto() {
        when(cardRepository.findAll()).thenReturn(buildCardList());
        when(converter.convert(any())).thenCallRealMethod();

        var cards = service.findAll();

        assertEquals(2, cards.size());
    }

    @Test
    void shouldPass_WhenFindByNumber_ReturnsOptionalOfCard() {
        when(cardRepository.findByNumber(anyString())).thenReturn(java.util.Optional.ofNullable(buildCard()));

        var cardOpt = service.findByNumber(DEFAULT_CARD_NUMBER);

        assertTrue(cardOpt.isPresent());
        assertEquals(DEFAULT_CARD_NUMBER, cardOpt.get().getNumber());
    }
}