package Logica;

import java.time.LocalDate;

public class Validacion {

    public static boolean validarNombre(String nombre) {
        for (char car : nombre.toCharArray()) {
            if (!Character.isLetter(car) && !Character.isSpace(car)) {
                return false;
            }
        }
        return !nombre.isEmpty();
    }

    public static boolean validarTelefono(String telefono) {
        if (telefono.length() != 9) {
            return false; // El número de teléfono debe tener exactamente 9 dígitos
        }
        char primerDigito = telefono.charAt(0);
        if (primerDigito != '6' && primerDigito != '7' && primerDigito != '9') {
            return false; // El primer dígito del teléfono debe ser 6, 7 o 9
        }
        for (char car : telefono.toCharArray()) {
            if (!Character.isDigit(car)) {
                return false; // Todos los caracteres deben ser dígitos
            }
        }
        return true;
    }

    public static boolean validarDNI(String dni) {
        if (dni.length() != 9) {
            return false; // El DNI debe tener exactamente 9 caracteres
        }
        int num = 0;
        try {
            num = Integer.parseInt(dni.substring(0, 8));
        } catch (NumberFormatException e) {
            return false; // Los primeros 8 caracteres deben ser dígitos
        }
        char letraEsperada = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(num % 23);
        return dni.charAt(8) == letraEsperada; // El último carácter debe coincidir con la letra esperada
    }

    public static boolean validarEmail(String email) {
        boolean fin = false;
        int arroba = 0;

        if (email.isEmpty()) {
            return false;
        }
        for (char car : email.toCharArray()) {
            if (car == '@') {
                ++arroba;
            }
            if (car == '.') {
                fin = true;
            }
        }
        return arroba == 1 && fin;
    }

    public static boolean validarContraseña(String password) {
        boolean hasUpper = false, hasLower = false, hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }

        return password.length() >= 8 && hasLower && hasUpper && hasDigit;
    }

    public static boolean validarTarjeta(String tarjeta, int dia, int mes, int año, String cvv) {

        boolean valido = false;

        if (0 > tarjeta.length() || tarjeta.length() > 16) {
            valido = false;
            System.out.println("Tarjeta no es válida");
        } else if (año < LocalDate.now().getYear()) {
            valido = false;
            System.out.println("Año no válido");
        } else if (año >= LocalDate.now().getYear()) {
            valido = true;

        } else if (cvv.length() != 3) {
            valido = false;
            System.out.println("CVV no válido");
        } else if (mes <= 12) {
            if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {

                valido = dia >= 1 && dia <= 31;
            } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {

                valido = dia >= 1 && dia <= 30;

            } else if (mes == 2) {
                if (año % 4 == 0 && (año % 100 != 0 || año % 400 == 0)) {
                    valido = dia >= 1 && dia <= 29;
                } else {
                    valido = dia >= 1 && dia <= 28;
                }
            }
        }

        return valido;
    }

    public static boolean validarVipPromocode(String promocode) {
        boolean vip = false;
        if ((promocode.toUpperCase()).equals("JAVABNB2024")) {
            vip = true;
        } else if (promocode.isEmpty()) { //Si no introduce nada, no hay vip
            vip = false;
        }
        return vip;
    }
    
    public static boolean validarPromocode(String promocode){
        boolean valido = false;
        if (promocode.toUpperCase().equals("JAVABNB2024") || promocode.isEmpty()){
            valido = true;
        }
        return valido;
    }
}
