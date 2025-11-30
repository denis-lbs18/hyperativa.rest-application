
package com.hyperativa.rest_application.services;

import com.hyperativa.rest_application.dtos.CardDto;
import com.hyperativa.rest_application.entities.Card;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ICardService {
    CardDto saveCard(String number);
    List<CardDto> saveFromTxtFile(MultipartFile file) throws IOException;
    List<CardDto> findAll();
    Optional<Card> findByNumber(String number);
}