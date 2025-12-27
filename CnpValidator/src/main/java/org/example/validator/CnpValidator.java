package org.example.validator;

@SuppressWarnings("unused")
public class CnpValidator {

    @SuppressWarnings("unused")
    public static boolean isValidCnp(String cnp) {

        // R1: CNP nu trebuie să fie null
        if (cnp == null) {
            return false;
        }

        // R2: lungime exact 13
        if (cnp.length() != 13) {
            return false;
        }

        // R3: toate caracterele trebuie să fie cifre
        for (char c : cnp.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        // extragere câmpuri: S YY MM DD JJ NNN C
        int s  = cnp.charAt(0) - '0';
        int mm = Integer.parseInt(cnp.substring(3, 5));
        int dd = Integer.parseInt(cnp.substring(5, 7));
        int jj = Integer.parseInt(cnp.substring(7, 9));

        // R4: S în [1..9] (simplificat)
        if (s < 1 || s > 9) {
            return false;
        }

        // R5: MM în [1..12]
        if (mm < 1 || mm > 12) {
            return false;
        }

        // R6: DD valid pentru luna MM (simplificat: acceptăm 29 pentru februarie)
        int maxDay;
        switch (mm) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                maxDay = 31;
                break;
            case 4: case 6: case 9: case 11:
                maxDay = 30;
                break;
            case 2:
                maxDay = 29; // simplificare (fără calcul an bisect)
                break;
            default:
                return false;
        }

        if (dd < 1 || dd > maxDay) {
            return false;
        }

        // R7: județ JJ în [01..52] sau 99
        boolean countyOk = (jj >= 1 && jj <= 52) || (jj == 99);
        if (!countyOk) {
            return false;
        }

        return true;
    }
}
