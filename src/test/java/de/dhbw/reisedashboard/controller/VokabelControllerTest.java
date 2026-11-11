package de.dhbw.reisedashboard.controller;

import de.dhbw.reisedashboard.config.LoginInterceptor;
import de.dhbw.reisedashboard.model.Benutzer;
import de.dhbw.reisedashboard.model.Lektion;
import de.dhbw.reisedashboard.model.Sprache;
import de.dhbw.reisedashboard.model.Vokabel;
import de.dhbw.reisedashboard.repository.BenutzerRepository;
import de.dhbw.reisedashboard.repository.LektionRepository;
import de.dhbw.reisedashboard.repository.SpracheRepository;
import de.dhbw.reisedashboard.repository.VokabelRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Prueft die Wege des VokabelControllers: ohne Login kein Zugriff, und eine beantwortete Vokabel
 * leitet zurueck zum Trainer. Die GET-Ansichten werden nicht gerendert (Templates fehlen noch).
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class VokabelControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BenutzerRepository benutzerRepository;
    @Autowired
    private SpracheRepository spracheRepository;
    @Autowired
    private LektionRepository lektionRepository;
    @Autowired
    private VokabelRepository vokabelRepository;

    private MockHttpSession session;
    private Lektion lektion;
    private Vokabel vokabel;

    @BeforeEach
    void anlegen() {
        Benutzer benutzer = benutzerRepository.save(new Benutzer("Michael", "Kurz", 0));
        Sprache sprache = spracheRepository.save(new Sprache("Russisch"));
        lektion = lektionRepository.save(new Lektion(sprache, "Erste Woerter"));
        vokabel = vokabelRepository.save(new Vokabel(lektion, "Haus", "dom", "dom"));
        session = new MockHttpSession();
        session.setAttribute(LoginInterceptor.SESSION_BENUTZER, benutzer.getBenutzerID());
    }

    @Test
    void ohneLoginKeinZugriffAufDenTrainer() throws Exception {
        mockMvc.perform(get("/lernen"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void beantworteteVokabelLeitetZurueckZumTrainer() throws Exception {
        mockMvc.perform(post("/lernen/lektion/" + lektion.getLektionID() + "/vokabel/" + vokabel.getVokabelID())
                        .session(session)
                        .param("richtig", "true"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/lernen/lektion/" + lektion.getLektionID()));
    }
}
