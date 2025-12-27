package org.example.validator;

@SuppressWarnings("unused")
public class CnpValidatorNotKilled {

    @SuppressWarnings("unused")
    public static boolean isValidCnp(String cnp) {

        if (cnp == null) {
            return false;
        }

        if (cnp.length() != 13) {
            return false;
        }

        for (char c : cnp.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        int s  = cnp.charAt(0) - '0';
        int mm = Integer.parseInt(cnp.substring(3, 5));
        int dd = Integer.parseInt(cnp.substring(5, 7));
        int jj = Integer.parseInt(cnp.substring(7, 9));

        if (s < 1 || s > 9) {
            return false;
        }

        if (mm < 1 || mm > 12) {
            return false;
        }

        int maxDay;
        switch (mm) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                maxDay = 31;
                break;
            case 4: case 6: case 9: case 11:
                maxDay = 30;
                break;
            case 2:
                maxDay = 29;
                break;
            default:
                return false;
        }

        if (dd < 1 || dd > maxDay) {
            return false;
        }

        // ❌ MUTANT NE-ECHIVALENT (NEOMORÂT)
        // original: (jj >= 1 && jj <= 52) || (jj == 99)
        boolean countyOk = (jj >= 1 && jj <= 52);

        return countyOk;
    }
}
