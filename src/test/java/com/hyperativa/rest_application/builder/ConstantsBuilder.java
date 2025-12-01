package com.hyperativa.rest_application.builder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstantsBuilder {
    public static final String ANY_STRING = "AnyString";
    public static final String UPDATED_NAME = "Updated Name";
    public static final String DEFAULT_EMAIL = "test@mail.com";
    public static final String DEFAULT_CARD_NUMBER = "1234567890123";
    public static final String CHARGE = "LOTE0001";
    public static final String DEFAULT_TXT_FILE = """
                                                  DESAFIO-HYPERATIVA           20180524LOTE0001000010
                                                  C1     4456897922969999
                                                  C2     4456897999999999
                                                  C3     4456897999999999
                                                  C4     4456897998199999
                                                  C5     4456897999999999124
                                                  C6     4456897912999999
                                                  C7     445689799999998
                                                  C8     4456897919999999
                                                  C9     4456897999099999
                                                  C10    4456897919999999
                                                  LOTE0003000010
                                                  """;
}
