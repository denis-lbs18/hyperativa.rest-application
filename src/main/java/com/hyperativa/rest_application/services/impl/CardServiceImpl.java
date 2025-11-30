package com.hyperativa.rest_application.services.impl;

import com.hyperativa.rest_application.converters.CardEntityToDtoConverter;
import com.hyperativa.rest_application.dtos.CardDto;
import com.hyperativa.rest_application.entities.Card;
import com.hyperativa.rest_application.repositories.CardRepository;
import com.hyperativa.rest_application.services.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements ICardService {
    private final CardRepository cardRepository;
    private final CardEntityToDtoConverter converter;

    @Override
    public CardDto saveCard(String number) {
        String cleaned = cleanNumber(number);
        if (cleaned.isEmpty()) throw new IllegalArgumentException("Invalid card number");
        if (cardRepository.existsByNumber(cleaned)) {
            Card card = cardRepository.findByNumber(cleaned).orElseThrow();
            return converter.convert(card);
        }
        Card card = new Card(cleaned);
        Card saved = cardRepository.save(card);
        return converter.convert(saved);
    }

    @Override
    public List<CardDto> saveFromTxtFile(MultipartFile file) throws IOException {
        List<String> extracted = extractCardNumbersFromTxt(file);
        List<CardDto> saved = new ArrayList<>();
        for (String num : extracted) {
            if (!cardRepository.existsByNumber(num)) {
                Card card = cardRepository.save(new Card(num));
                saved.add(converter.convert(card));
            }
        }
        return saved;
    }

    @Override
    public List<CardDto> findAll() {
        List<CardDto> cards = new ArrayList<>();
        cardRepository.findAll().forEach(card -> cards.add(converter.convert(card)));

        return cards;
    }

    @Override
    public Optional<Card> findByNumber(String number) {
        return cardRepository.findByNumber(cleanNumber(number));
    }

    private String cleanNumber(String raw) {
        if (raw == null) return "";
        String onlyDigits = raw.replaceAll("\\D", "");
        return onlyDigits.trim();
    }

    private List<String> extractCardNumbersFromTxt(MultipartFile file) throws IOException {
        List<String> results = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                if (line.length() >= 26) {
                    try {
                        String candidate = line.substring(7, 26);
                        candidate = candidate.replaceAll("\\s+", "");
                        candidate = candidate.replaceAll("\\D", "");
                        if (!candidate.isEmpty()) {
                            results.add(candidate);
                        }
                    } catch (IndexOutOfBoundsException ignored) {}
                }
            }
        }
        // deduplicate while preserving insertion order
        return results.stream().distinct().collect(Collectors.toList());
    }
}