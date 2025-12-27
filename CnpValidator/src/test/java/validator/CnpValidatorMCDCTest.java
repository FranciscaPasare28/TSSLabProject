package validator;

import org.example.validator.CnpValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CnpValidatorMCDCTest {

    @Test
    @DisplayName("M1 - Toate conditiile true (CNP valid)")
    void returnsTrueWhenAllConditionsAreMet() {
        // S=1, MM=01, DD=01, JJ=22
        assertTrue(CnpValidator.isValidCnp("1980101220018"));
    }

    @Test
    @DisplayName("M2 - S invalid (C4 = false)")
    void returnsFalseWhenSIsInvalid() {
        // S=0 invalid
        assertFalse(CnpValidator.isValidCnp("0980101220018"));
    }

    @Test
    @DisplayName("M3 - Luna invalidă (C5 = false)")
    void returnsFalseWhenMonthIsInvalid() {
        // MM=13 invalid
        assertFalse(CnpValidator.isValidCnp("1981301220018"));
    }

    @Test
    @DisplayName("M4 - Zi invalidă (C6 = false)")
    void returnsFalseWhenDayIsInvalid() {
        // DD=32 invalid
        assertFalse(CnpValidator.isValidCnp("1980132220018"));
    }

    @Test
    @DisplayName("M5 - Județ invalid (C7 = false)")
    void returnsFalseWhenCountyIsInvalid() {
        // JJ=60 invalid
        assertFalse(CnpValidator.isValidCnp("1980101600018"));
    }
}
