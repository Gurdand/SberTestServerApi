package app;

import app.restcontroller.AuthorizationRestController;
import app.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorizationRestController.class)
public class AuthorizationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void authorizationTest() throws Exception {

        MockHttpSession session = new MockHttpSession();

        String login = "test";
        given(userService.authorization(login)).willReturn(true);

        this.mockMvc.perform(get("/api/authorization/" + login).session(session))
                .andExpect(status().isOk());

        Assert.assertEquals(session.getAttribute("Authorization"), login);

    }

    @Test
    public void wrongLoginAuthorization() throws Exception {
        String wrongLogin = "wrongLogin";
        given(userService.authorization(wrongLogin)).willReturn(false);

        this.mockMvc.perform(get("/api/authorization/" + wrongLogin))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void logoutTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        String login = "testLogin";
        session.setAttribute("Authorization", login);

        this.mockMvc.perform(get("/api/logout").session(session))
                .andExpect(status().isOk());

        Assert.assertNull(session.getAttribute("Authorization"));
    }
}
