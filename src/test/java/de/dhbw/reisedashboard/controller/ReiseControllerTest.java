package de.dhbw.reisedashboard.controller;

import de.dhbw.reisedashboard.config.LoginInterceptor;
import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.model.Reise;
import de.dhbw.reisedashboard.model.Sprache;
import de.dhbw.reisedashboard.repository.BenutzerRepository;
import de.dhbw.reisedashboard.repository.SpracheRepository;
import de.dhbw.reisedashboard.service.ReiseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Prueft die Wege (Routen) des ReiseControllers: ohne Login kommt man nicht rein, und das
 * Anlegen einer Reise sowie das Nachtragen einer Ausgabe leiten auf die richtige Seite um.
 * Die GET-Ansichten werden hier nicht gerendert, weil die Thymeleaf-Templates noch fehlen.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ReiseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BenutzerRepository benutzerRepository;
    @Autowired
    private SpracheRepository spracheRepository;
    @Autowired
    private ReiseService reiseService;

    private MockHttpSession session;
    private Benutzer benutzer;
    private Sprache sprache;

    @BeforeEach
    void anlegen() {
        benutzer = benutzerRepository.save(new Benutzer("Michael", "Kurz", 0));
        sprache = spracheRepository.save(new Sprache("Russisch"));
        session = new MockHttpSession();
        session.setAttribute(LoginInterceptor.SESSION_BENUTZER, benutzer.getBenutzerID());
    }

    @Test
    void ohneLoginKommtManNichtAufDieReisen() throws Exception {
        mockMvc.perform(get("/reisen"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void reiseAnlegenLeitetAufDieDetailseite() throws Exception {
        mockMvc.perform(post("/reisen").session(session)
                        .param("spracheId", sprache.getSpracheID().toString())
                        .param("ziel", "Samara")
                        .param("land", "Russland")
                        .param("budget", "1000.00")
                        .param("startdatum", "2027-03-01")
                        .param("enddatum", "2027-03-14"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/reisen/*"));
    }

    @Test
    void ausgabeNachtragenLeitetZurueckAufDieReise() throws Exception {
        Reise reise = reiseService.reiseAnlegen(benutzer, sprache, "Tokio", "Japan",
                new BigDecimal("2000.00"), LocalDate.of(2027, 4, 1), LocalDate.of(2027, 4, 20));

        mockMvc.perform(post("/reisen/" + reise.getReiseID() + "/ausgabe").session(session)
                        .param("kategorie", "Flug")
                        .param("betrag", "300.00"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/reisen/" + reise.getReiseID()));
    }

    @Test
    void fremdeReiseGibtVierNullVier() throws Exception {
        Reise reise = reiseService.reiseAnlegen(benutzer, sprache, "Samara", "Russland",
                new BigDecimal("1000.00"), LocalDate.of(2027, 3, 1), LocalDate.of(2027, 3, 14));

        // Ein zweites Profil versucht, die Reise des ersten ueber die URL zu oeffnen.
        Benutzer anderer = benutzerRepository.save(new Benutzer("Schayan", "Goudarzi", 0));
        MockHttpSession andereSession = new MockHttpSession();
        andereSession.setAttribute(LoginInterceptor.SESSION_BENUTZER, anderer.getBenutzerID());

        mockMvc.perform(get("/reisen/" + reise.getReiseID()).session(andereSession))
                .andExpect(status().isNotFound());
    }
}
