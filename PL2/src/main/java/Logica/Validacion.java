package Logica;

public class Validacion {
    public static boolean validarNombre(String nombre){
        for (char car: nombre.toCharArray()){
            if(!Character.isLetter(car) && !Character.isSpace(car)){
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
        } 
        catch (NumberFormatException e) {
            return false; // Los primeros 8 caracteres deben ser dígitos
        }
        char letraEsperada = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(num % 23);
        return dni.charAt(8) == letraEsperada; // El último carácter debe coincidir con la letra esperada
}

    public static boolean validarEmail(String email){
        boolean fin = false;
        int arroba = 0;

        if(email.isEmpty()){
            return false;
        }
        for (char car: email.toCharArray()){
            if(car == '@'){++arroba;}
            if(car == '.'){fin = true;}
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

}

