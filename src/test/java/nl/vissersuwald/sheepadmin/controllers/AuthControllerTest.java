package nl.vissersuwald.sheepadmin.controllers;

import nl.vissersuwald.sheepadmin.services.JwtService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtService jwtService;

    @Test
    void login_withValidCredentials_returnsToken() throws Exception {
        String username = "testuser";
        String password = "secret";
        String fakeToken = "jwt-token-123";
        String requestJson = """
                {
                  "username": "testuser",
                  "password": "secret"
                }
                """;

        Authentication authResult = new UsernamePasswordAuthenticationToken(username, password);
        Mockito.when(authenticationManager.authenticate(any(Authentication.class)))
                .thenReturn(authResult);
        Mockito.when(jwtService.generateToken(eq(username)))
                .thenReturn(fakeToken);

        mockMvc.perform(
                        post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token", is(fakeToken)));

        Mockito.verify(authenticationManager).authenticate(
                Mockito.argThat(auth ->
                        auth instanceof UsernamePasswordAuthenticationToken token &&
                                token.getPrincipal().equals(username) &&
                                token.getCredentials().equals(password)
                )
        );
        Mockito.verify(jwtService).generateToken(username);
    }

    @Test
    void login_withBadCredentials_returnsUnauthorized() throws Exception {
        String requestJson = """
                {
                  "username": "testuser",
                  "password": "wrong"
                }
                """;

        Mockito.when(authenticationManager.authenticate(any(Authentication.class)))
                .thenThrow(new org.springframework.security.authentication.BadCredentialsException("Bad credentials"));

        // when + then
        mockMvc.perform(
                        post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                )
                .andDo(print())
                .andExpect(status().isUnauthorized());
        // body will depend on how you handle the exception globally
    }
}