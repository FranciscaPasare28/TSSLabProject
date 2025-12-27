package validator;

import org.example.validator.CnpValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CnpValidatorCEGTest {

    @Test
    @DisplayName("CE1 - C1=1 (CNP null)")
    void returnsFalseWhenCnpIsNull() {
        assertFalse(CnpValidator.isValidCnp(null));
    }

    @Test
    @DisplayName("CE2 - C2=1 (lungime != 13, exemplu < 13)")
    void returnsFalseWhenLengthNotThirteen() {
        assertFalse(CnpValidator.isValidCnp("198010122001")); // 12 caractere
    }

    @Test
    @DisplayName("CE3 - C3=1 (conține caracter non-digit)")
    void returnsFalseWhenContainsNonDigit() {
        assertFalse(CnpValidator.isValidCnp("19801A1220018"));
    }

    @Test
    @DisplayName("CE4 - C4=1 (S invalid)")
    void returnsFalseWhenSIsInvalid() {
        assertFalse(CnpValidator.isValidCnp("0980101220018")); // S=0
    }

    @Test
    @DisplayName("CE5 - C5=1 (MM invalid)")
    void returnsFalseWhenMonthIsInvalid() {
        assertFalse(CnpValidator.isValidCnp("1981301220018")); // MM=13
    }

    @Test
    @DisplayName("CE6 - C6=1 (DD invalid)")
    void returnsFalseWhenDayIsInvalid() {
        assertFalse(CnpValidator.isValidCnp("1980132220018")); // DD=32
    }

    @Test
    @DisplayName("CE7 - C7=1 (JJ invalid)")
    void returnsFalseWhenCountyIsInvalid() {
        assertFalse(CnpValidator.isValidCnp("1980101600018")); // JJ=60
    }

    @Test
    @DisplayName("CE8 - toate cauzele false => CNP valid")
    void returnsTrueWhenAllConditionsMet() {
        assertTrue(CnpValidator.isValidCnp("1980101220018")); // valid
    }

    @Test
void monthWith30Days_validDay30() {
    assertTrue(CnpValidator.isValidCnp("1980430220018")); // MM=04, DD=30
}

@Test
void february_day29_isAccepted() {
    assertTrue(CnpValidator.isValidCnp("1980229220018")); // MM=02, DD=29
}

@Test
void dayZero_isInvalid() {
    assertFalse(CnpValidator.isValidCnp("1980100220018")); // DD=00
}

@Test
void day31_inApril_isInvalid() {
    assertFalse(CnpValidator.isValidCnp("1980431220018")); // MM=04, DD=31
}

@Test
void county99_isAccepted() {
    assertTrue(CnpValidator.isValidCnp("1980101990018")); // JJ=99
}

@Test
void county53_isInvalid() {
    assertFalse(CnpValidator.isValidCnp("1980101530018")); // JJ=53
}

}
