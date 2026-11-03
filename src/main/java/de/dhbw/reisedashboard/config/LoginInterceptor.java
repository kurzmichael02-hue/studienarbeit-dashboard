package de.dhbw.reisedashboard.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Laesst nur Anfragen durch, bei denen in der Session ein Benutzer gewaehlt ist.
 * Kein Spring Security, weil die App nur im eigenen WLAN auf dem Pi laeuft und
 * die Profile ohne Passwort gewechselt werden (Profil-Auswahl wie am Fernseher).
 * Die Trennung der Benutzerdaten haengt trotzdem an dieser Session-Id.
 */
public class LoginInterceptor implements HandlerInterceptor {

    public static final String SESSION_BENUTZER = "benutzerId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (istEingeloggt(request)) {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }

    public static boolean istEingeloggt(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute(SESSION_BENUTZER) != null;
    }
}
