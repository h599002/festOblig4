package no.hvl.dat108.fest.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import no.hvl.dat108.fest.model.UsersModel;

public class LoginUtil {

    public static void loggUtBruker(HttpSession session){
        session.invalidate();
    }

    public static void loggInnBruker(HttpServletRequest request, UsersModel deltager){
        loggUtBruker(request.getSession());

        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(300); // 5 min utlogging
        session.setAttribute("brukernavn", deltager.getMobil());

    }

    public static boolean erBrukerInnlogget(HttpSession session) {
        return session != null && session.getAttribute("brukernavn") != null;
    }

}
