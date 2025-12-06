package nl.vissersuwald.sheepadmin.services;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JwtAuthFilter jwtAuthFilter;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockFilterChain filterChain;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        filterChain = new MockFilterChain();
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void doFilterInternal_shouldSkip_whenNoAuthorizationHeader() throws ServletException, IOException {
        // no Authorization header set

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        // should not set authentication
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        // jwtService should not be called
        verifyNoInteractions(jwtService, userDetailsService);
    }

    @Test
    void doFilterInternal_shouldSkip_whenHeaderDoesNotStartWithBearer() throws ServletException, IOException {
        request.addHeader("Authorization", "Basic some-token");

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verifyNoInteractions(jwtService, userDetailsService);
    }

    @Test
    void doFilterInternal_shouldSkip_whenExtractedUsernameIsNull() throws ServletException, IOException {
        String token = "fake-jwt-token";
        request.addHeader("Authorization", "Bearer " + token);

        when(jwtService.extractUsername(token)).thenReturn(null);

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(jwtService).extractUsername(token);
        verifyNoMoreInteractions(jwtService);
        verifyNoInteractions(userDetailsService);
    }

    @Test
    void doFilterInternal_shouldNotOverride_whenAuthenticationAlreadyPresent() throws ServletException, IOException {
        // given existing authentication in the context
        UserDetails existingUser = new User("existingUser", "password", Collections.emptyList());
        UsernamePasswordAuthenticationToken existingAuth =
                new UsernamePasswordAuthenticationToken(existingUser, null, existingUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(existingAuth);

        String token = "some-token";
        request.addHeader("Authorization", "Bearer " + token);

        // jwtService should still be called to extract username, but filter must not override existing auth
        when(jwtService.extractUsername(token)).thenReturn("newUser");

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        // authentication should remain the same
        assertSame(existingAuth, SecurityContextHolder.getContext().getAuthentication());
        verify(jwtService).extractUsername(token);
        // since auth is already present, we should not load user or validate token
        verifyNoInteractions(userDetailsService);
        verify(jwtService, times(1)).extractUsername(token);
        verify(jwtService, never()).isTokenValid(anyString(), any());
    }

    @Test
    void doFilterInternal_shouldAuthenticate_whenTokenValid() throws ServletException, IOException {
        String token = "valid-token";
        String username = "testuser";
        request.addHeader("Authorization", "Bearer " + token);

        UserDetails userDetails = new User(username, "password", Collections.emptyList());

        when(jwtService.extractUsername(token)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtService.isTokenValid(token, userDetails)).thenReturn(true);

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        assertNotNull(authentication);
        assertTrue(authentication instanceof UsernamePasswordAuthenticationToken);
        assertEquals(userDetails, authentication.getPrincipal());
        assertIterableEquals(userDetails.getAuthorities(), authentication.getAuthorities());

        // verify interactions
        verify(jwtService).extractUsername(token);
        verify(userDetailsService).loadUserByUsername(username);
        verify(jwtService).isTokenValid(token, userDetails);
    }

    @Test
    void doFilterInternal_shouldNotAuthenticate_whenTokenInvalid() throws ServletException, IOException {
        String token = "invalid-token";
        String username = "testuser";
        request.addHeader("Authorization", "Bearer " + token);

        UserDetails userDetails = new User(username, "password", Collections.emptyList());

        when(jwtService.extractUsername(token)).thenReturn(username);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtService.isTokenValid(token, userDetails)).thenReturn(false);

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());

        verify(jwtService).extractUsername(token);
        verify(userDetailsService).loadUserByUsername(username);
        verify(jwtService).isTokenValid(token, userDetails);
    }
}