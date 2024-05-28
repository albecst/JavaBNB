package Logica;

import java.time.LocalDate;

/**
 * La clase Validacion contiene métodos estáticos para validar diferentes tipos de datos.
 */
public class Validacion {

    /**
     * Comprueba que el nombre no solo contenga espacios en blanco o letras .
     *
     * @param nombre el nombre a validar
     * @return true si el nombre es válido, false en caso contrario
     */
    public static boolean validarNombre(String nombre) {
        for (char car : nombre.toCharArray()) {
            // Utilizar isWhitespace() en vez de isSpaceChar() para tener en cuenta también tabulados...
            if (!Character.isLetter(car) && !Character.isWhitespace(car)) {
                return false;
            }
        }
        return !nombre.isEmpty();
    }

    /**
     * Valida que el número de teléfono tenga exactamente 9 dígitos y comience con 6, 7 o 9.
     *
     * @param telefono el número de teléfono a validar
     * @return true si el teléfono es válido, false en caso contrario
     */
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

    /**
     * Valida que el DNI tenga exactamente 9 caracteres y que la letra coincida con el número.
     *
     * @param dni el DNI a validar
     * @return true si el DNI es válido, false en caso contrario
     */
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

    /**
     * Valida que el correo electrónico contenga una única arroba y al menos un punto.
     *
     * @param email el correo electrónico a validar
     * @return true si el correo electrónico es válido, false en caso contrario
     */
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

    /**
     * Valida que la contraseña contenga al menos 8 caracteres, una letra minúscula, una letra mayúscula y un dígito.
     *
     * @param password la contraseña a validar
     * @return true si la contraseña es válida, false en caso contrario
     */
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

    /**
     * Valida los datos de una tarjeta de crédito.
     *
     * @param tarjeta el número de la tarjeta
     * @param dia el día de expiración
     * @param mes el mes de expiración
     * @param año el año de expiración
     * @param cvv el código CVV de la tarjeta
     * @return true si los datos de la tarjeta son válidos, false en caso contrario
     */
    public static boolean validarTarjeta(String tarjeta, int dia, int mes, int año, String cvv) {
        if (dia == -1) {
            return false;
        }
        if (tarjeta.length() != 16) {
            System.out.println("Tarjeta no es válida");
            return false;
        } else if (año < LocalDate.now().getYear()) {
            System.out.println("Año no válido");
            return false;
        } else if (cvv.length() != 3) {
            System.out.println("CVV no válido");
            return false;
        } else if (mes <= 12) {
            if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
                return dia >= 1 && dia <= 31;
            } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                return dia >= 1 && dia <= 30;
            } else if (mes == 2) {
                if (año % 4 == 0 && (año % 100 != 0 || año % 400 == 0)) {
                    return dia >= 1 && dia <= 29;
                } else {
                    return dia >= 1 && dia <= 28;
                }
            }
        }
        return false;
    }

    /**
     * Valida si el código promocional para convertirse en VIP es válido.
     *
     * @param promocode el código promocional
     * @return true si el código es válido, false en caso contrario
     */
    public static boolean validarVipPromocode(String promocode) {
        return "JAVABNB2024".equals(promocode.toUpperCase());
    }

    /**
     * Valida si el código promocional es válido o está vacío.
     *
     * @param promocode el código promocional
     * @return true si el código es válido o está vacío, false en caso contrario
     */
    public static boolean validarPromocode(String promocode) {
        return "JAVABNB2024".equals(promocode.toUpperCase()) || promocode.isEmpty();
    }

    /**
     * Comprueba la existencia de un cliente basado en su correo, DNI y teléfono.
     *
     * @param correo el correo del cliente
     * @param dni el DNI del cliente
     * @param telefono el teléfono del cliente
     * @return true si el cliente ya existe, false en caso contrario
     */
    public static boolean comprobarExistenciaCliente(String correo, String dni, String telefono) {
        if (!JavaBNB.getClientes().isEmpty()) {
            for (Cliente cliente : JavaBNB.getClientes()) {
                if (cliente.getCorreo().equals(correo)) {
                    System.out.println("Este correo ya existe");
                    return true;
                } else if (cliente.getDni().equals(dni)) {
                    System.out.println("Este DNI ya existe");
                    return true;
                } else if (cliente.getTelefono().equals(telefono)) {
                    System.out.println("Este teléfono ya existe");
                    return true;
                }
            }
        }
        return false;
    }
}
