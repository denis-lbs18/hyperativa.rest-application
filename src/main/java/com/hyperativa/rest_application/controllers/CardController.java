// java
package com.hyperativa.rest_application.controllers;

import com.hyperativa.rest_application.dtos.CardDto;
import com.hyperativa.rest_application.entities.Card;
import com.hyperativa.rest_application.services.ICardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
@Tag(name = "Cards", description = "Card registration and retrieval endpoints")
public class CardController {
    private final ICardService cardService;

    @Operation(summary = "Create a single card record", description = "Registers a single full card number. In production store PANs encrypted or tokenize.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Card created", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CardDto> createCard(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Card payload", required = true, content = @Content)
            @Valid @RequestBody CardDto dto) {
        CardDto cardDto = cardService.saveCard(dto.getNumber());
        return ResponseEntity.status(201).body(cardDto);
    }

    @Operation(summary = "Upload TXT file with card records", description = "Accepts a TXT file in the specified fixed-width format. Extracts card numbers from columns 8-26 (1-based) of each data line.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "File processed and cards stored", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Invalid file", content = @Content)
    })
    @PostMapping(path = "/upload-txt", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CardDto>> uploadTxt(
            @RequestParam("file") MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<CardDto> saved = cardService.saveFromTxtFile(file);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "List all stored cards", description = "Returns all stored card records")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cards returned", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<CardDto>> listAll() {
        return ResponseEntity.ok(cardService.findAll());
    }

    @Operation(summary = "Find card by number", description = "Search card by full number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Card found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Card not found", content = @Content)
    })
    @GetMapping("/{number}")
    public ResponseEntity<Card> findByNumber(@PathVariable String number) {
        return cardService.findByNumber(number)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}