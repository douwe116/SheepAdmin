package nl.vissersuwald.sheepadmin.controllers;

import nl.vissersuwald.sheepadmin.logic.SheepTestLogic;
import nl.vissersuwald.sheepadmin.models.farming.Sheep;
import nl.vissersuwald.sheepadmin.repositories.farming.SheepRepository;
import nl.vissersuwald.sheepadmin.services.JwtAuthFilter;
import nl.vissersuwald.sheepadmin.services.JwtService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SheepController.class)
@AutoConfigureMockMvc(addFilters = false)   // ⬅ disable security filters
class SheepControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private JwtAuthFilter jwtAuthFilter;
    @MockBean private JwtService jwtService;
    @MockBean private UserDetailsService userDetailsService;
    @MockBean private SheepRepository sheepRepository;

    private Sheep sampleSheep() {
        Sheep sheep = SheepTestLogic.createNewSheep("Dolly");
        return sheep;
    }

    @Test
    void testListSheep() throws Exception {
        Mockito.when(sheepRepository.findAll()).thenReturn(
                Arrays.asList(sampleSheep())
        );

        mockMvc.perform(
                get("/api/sheep"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Dolly")));
    }

    @Test
    void testGetSheepById() throws Exception {
        Mockito.when(sheepRepository.getReferenceById(1L)).thenReturn(sampleSheep());

        mockMvc.perform(get("/api/sheep/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Dolly")));
    }

    @Test
    void testCreateSheep() throws Exception {
        Mockito.when(sheepRepository.saveAndFlush(any(Sheep.class)))
                .thenReturn(sampleSheep());

        mockMvc.perform(post("/api/sheep")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Dolly\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Dolly")));
    }

    @Test
    void testDeleteSheep() throws Exception {
        mockMvc.perform(delete("/api/sheep/1"))
                .andExpect(status().isAccepted());

        Mockito.verify(sheepRepository).deleteById(1L);
    }

    @Test
    void testUpdateSheep() throws Exception {
        Sheep existing = sampleSheep();
        Sheep updated = sampleSheep();
        updated.setName("Updated Sheep");

        Mockito.when(sheepRepository.getReferenceById(1L)).thenReturn(existing);
        Mockito.when(sheepRepository.saveAndFlush(any(Sheep.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/api/sheep/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Sheep\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Updated Sheep")));
    }
}
