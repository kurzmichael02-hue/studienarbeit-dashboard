package de.dhbw.reisedashboard;

import de.dhbw.reisedashboard.config.LoginInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LoginFlowTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void ohneSessionGehtsZurLoginSeite() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void mitSessionKommtManRein() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(LoginInterceptor.SESSION_BENUTZER, 1L);

        mockMvc.perform(get("/").session(session))
                .andExpect(status().isOk());
    }

    @Test
    void anmeldenSetztSessionUndLeitetUm() throws Exception {
        mockMvc.perform(post("/login").param("benutzerId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(request().sessionAttribute(LoginInterceptor.SESSION_BENUTZER, 1L));
    }

    @Test
    void abmeldenLoeschtSession() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute(LoginInterceptor.SESSION_BENUTZER, 1L);

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
