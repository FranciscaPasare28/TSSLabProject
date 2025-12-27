package validator;

import org.example.validator.CnpValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CnpValidatorBVATest {

    @Test
    @DisplayName("BVA1 - Lungime 12 (invalid)")
    void returnsFalseWhenLengthIsTwelve() {
        assertFalse(CnpValidator.isValidCnp("198010122001")); // 12 cifre
    }

    @Test
    @DisplayName("BVA2 - Lungime 13 (valid)")
    void returnsTrueWhenLengthIsThirteen() {
        assertTrue(CnpValidator.isValidCnp("1980101220018")); // 13 cifre valide
    }

    @Test
    @DisplayName("BVA3 - Lungime 14 (invalid)")
    void returnsFalseWhenLengthIsFourteen() {
        assertFalse(CnpValidator.isValidCnp("19801012200189")); // 14 cifre
    }

      @Test
    @DisplayName("BVA - S sub limita minimă (S=0) -> invalid")
    void sBelowMin_invalid() {
        assertFalse(CnpValidator.isValidCnp("0980101220018"));
    }

    @Test
    @DisplayName("BVA - S la limita maximă (S=9) -> valid")
    void sMax_valid() {
        assertTrue(CnpValidator.isValidCnp("9980101220018"));
    }

    @Test
    @DisplayName("BVA - Lună peste limita maximă (MM=13) -> invalid")
    void monthAboveMax_invalid() {
        assertFalse(CnpValidator.isValidCnp("1981301220018"));
    }

    @Test
    @DisplayName("BVA - Zi sub limita minimă (DD=00) -> invalid")
    void dayBelowMin_invalid() {
        assertFalse(CnpValidator.isValidCnp("1980100220018"));
    }

    @Test
    @DisplayName("BVA - Lună 30 zile: DD=30 (max valid) -> valid")
    void dayMax30_valid() {
        assertTrue(CnpValidator.isValidCnp("1980430220018")); // Aprilie 30
    }

    @Test
    @DisplayName("BVA - Lună 30 zile: DD=31 (peste max) -> invalid")
    void dayAboveMax30_invalid() {
        assertFalse(CnpValidator.isValidCnp("1980431220018")); // Aprilie 31
    }

    @Test
    @DisplayName("BVA - Februarie: DD=29 (max valid în implementare) -> valid")
    void feb29_valid() {
        assertTrue(CnpValidator.isValidCnp("1980229220018"));
    }

    @Test
    @DisplayName("BVA - Februarie: DD=30 (peste max) -> invalid")
    void feb30_invalid() {
        assertFalse(CnpValidator.isValidCnp("1980230220018"));
    }

    @Test
    @DisplayName("BVA - Județ special: JJ=99 -> valid")
    void county99_valid() {
        assertTrue(CnpValidator.isValidCnp("1980101990018"));
    }

    @Test
    @DisplayName("BVA - Județ peste limita maximă (JJ=53) -> invalid")
    void countyAboveMax_invalid() {
        assertFalse(CnpValidator.isValidCnp("1980101530018"));
    }

    @Test
@DisplayName("BVA - Lună sub limita minimă (MM=00) -> invalid")
void monthBelowMin_invalid() {
    assertFalse(CnpValidator.isValidCnp("1980001220018")); // MM=00
}

@Test
@DisplayName("BVA - Lună la limita maximă (MM=12) -> valid")
void monthMax_valid() {
    assertTrue(CnpValidator.isValidCnp("1981231220018")); // MM=12, DD=31
}

@Test
@DisplayName("BVA - Zi la limita maximă pentru lună de 31 zile (DD=31) -> valid")
void dayMax31_valid() {
    assertTrue(CnpValidator.isValidCnp("1980131220018")); // MM=01, DD=31
}

@Test
@DisplayName("BVA - Județ la limita minimă (JJ=01) -> valid")
void countyMin_valid() {
    assertTrue(CnpValidator.isValidCnp("1980101010018")); // JJ=01
}

@Test
@DisplayName("BVA - Județ la limita maximă (JJ=52) -> valid")
void countyMax_valid() {
    assertTrue(CnpValidator.isValidCnp("1980101520018")); // JJ=52
}

@Test
@DisplayName("BVA - Județ sub limita minimă (JJ=00) -> invalid")
void countyBelowMin_invalid() {
    assertFalse(CnpValidator.isValidCnp("1980101000018")); // JJ=00
}

}
