package no.hvl.dat108.fest.Util;

public class InputValidator {

    public static boolean erGyldigNavn(String string){
        boolean harFeil = string.length() > 20 || string.length() < 2;
        if(!string.matches("[a-zA-ZæøåÆØÅ\\s-]+")){
            harFeil = true;
        }
        return !harFeil;
    }
    public static boolean erGyldigMobil(String string){
        return string.matches("^\\d{8}$");
    }
    public static boolean erGyldigPassord(String string){
        return string.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}");
    }
    public static boolean erGyldigKjonn(String string){
        return string.equals("mann")  || string.equals("kvinne");
    }

}
