package com.hyperativa.rest_application.builder;

import lombok.NoArgsConstructor;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static com.hyperativa.rest_application.builder.ConstantsBuilder.DEFAULT_TXT_FILE;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class MultiPartFileBuilder {
    public static MultipartFile buildMultiPartFile() {
        byte[] content = DEFAULT_TXT_FILE.getBytes();
        return new MockMultipartFile(
                "file",
                "test-file.txt",
                "text/plain",
                content
        );
    }
}
