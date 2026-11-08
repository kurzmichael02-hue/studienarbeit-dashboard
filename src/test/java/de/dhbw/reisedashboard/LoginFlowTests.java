package de.dhbw.reisedashboard;

import de.dhbw.reisedashboard.config.LoginInterceptor;
import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.repository.BenutzerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class LoginFlowTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BenutzerRepository benutzerRepository;

    private Integer benutzerId;

    @BeforeEach
    void profilAnlegen() {
        benutzerId = benutzerRepository.save(new Benutzer("Michael", "Kurz", 0)).getBenutzerID();
    }

    @Test
    void ohneSessionGehtsZurLoginSeite() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void mitSessionKommtManRein() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(LoginInterceptor.SESSION_BENUTZER, benutzerId);

        mockMvc.perform(get("/").session(session))
                .andExpect(status().isOk());
    }

    @Test
    void anmeldenSetztSessionUndLeitetUm() throws Exception {
        mockMvc.perform(post("/login").param("benutzerId", benutzerId.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(request().sessionAttribute(LoginInterceptor.SESSION_BENUTZER, benutzerId));
    }

    @Test
    void unbekanntesProfilKommtNichtRein() throws Exception {
        mockMvc.perform(post("/login").param("benutzerId", "99999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"))
                .andExpect(request().sessionAttribute(LoginInterceptor.SESSION_BENUTZER, (Object) null));
    }

    @Test
    void neuesProfilWirdAngelegtUndAngemeldet() throws Exception {
        mockMvc.perform(post("/benutzer/neu").param("vorname", "Schayan").param("nachname", "Goudarzi"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void abmeldenLoeschtSession() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(LoginInterceptor.SESSION_BENUTZER, benutzerId);

        mockMvc.perform(post("/logout").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        mockMvc.perform(get("/").session(session))
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void statischeDateienBrauchenKeinLogin() throws Exception {
        mockMvc.perform(get("/css/app.css"))
                .andExpect(status().isOk());
    }
}
