package validator;

import org.example.validator.CnpValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CnpValidatorEPTest {

    @Test
    @DisplayName("EP1 - CNP este null")
    void returnsFalseWhenCnpIsNull() {
        assertFalse(CnpValidator.isValidCnp(null));
    }

    @Test
    @DisplayName("EP2 - CNP prea scurt (lungime < 13)")
    void returnsFalseWhenCnpTooShort() {
        assertFalse(CnpValidator.isValidCnp("198010122001"));
    }

    @Test
    @DisplayName("EP3 - CNP prea lung (lungime > 13)")
    void returnsFalseWhenCnpTooLong() {
        assertFalse(CnpValidator.isValidCnp("19801012200189"));
    }

    @Test
    @DisplayName("EP4 - CNP conține caractere non-digit")
    void returnsFalseWhenCnpContainsNonDigit() {
        assertFalse(CnpValidator.isValidCnp("19801A1220018"));
    }

    @Test
    @DisplayName("EP5 - S invalid (în afara intervalului 1–9)")
    void returnsFalseWhenSIsInvalid() {
        assertFalse(CnpValidator.isValidCnp("0980101220018"));
    }

    @Test
    @DisplayName("EP6 - Lună invalidă (MM = 13)")
    void returnsFalseWhenMonthIsInvalid() {
        assertFalse(CnpValidator.isValidCnp("1981301220018"));
    }

    @Test
    @DisplayName("EP7 - Zi invalidă pentru lună (DD = 32)")
    void returnsFalseWhenDayIsInvalid() {
        assertFalse(CnpValidator.isValidCnp("1980132220018"));
    }

    @Test
    @DisplayName("EP8 - CNP valid (toate condițiile îndeplinite)")
    void returnsTrueWhenCnpIsValid() {
        assertTrue(CnpValidator.isValidCnp("1980101220018"));
    }

        @Test
    @DisplayName("EP9 - Zi invalidă (DD=00) -> acoperă dd < 1")
    void returnsFalseWhenDayIsZero() {
        assertFalse(CnpValidator.isValidCnp("1980100220018")); // DD=00
    }

    @Test
    @DisplayName("EP10 - Lună cu 30 zile (MM=04) + zi invalidă (DD=31) -> case 30 zile")
    void returnsFalseWhenDayInvalidFor30DayMonth() {
        assertFalse(CnpValidator.isValidCnp("1980431220018")); // Aprilie 31
    }

    @Test
    @DisplayName("EP11 - Februarie (MM=02) + zi 29 -> case 2 (valid în implementare)")
    void returnsTrueWhenFeb29() {
        assertTrue(CnpValidator.isValidCnp("1980229220018")); // Feb 29
    }

    @Test
    @DisplayName("EP12 - Județ 99 -> acoperă ramura (jj==99)")
    void returnsTrueWhenCountyIs99() {
        assertTrue(CnpValidator.isValidCnp("1980101990018")); // JJ=99
    }

    @Test
    @DisplayName("EP13 - Județ invalid (JJ=53) -> acoperă if(!countyOk)")
    void returnsFalseWhenCountyIsInvalid() {
        assertFalse(CnpValidator.isValidCnp("1980101530018")); // JJ=53
    }

}
