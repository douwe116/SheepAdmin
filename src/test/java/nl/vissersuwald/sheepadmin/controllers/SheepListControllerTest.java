package nl.vissersuwald.sheepadmin.controllers;


import nl.vissersuwald.sheepadmin.dto.SheepListDto;
import nl.vissersuwald.sheepadmin.repositories.farming.SheepRepository;
import nl.vissersuwald.sheepadmin.services.JwtAuthFilter;
import nl.vissersuwald.sheepadmin.services.JwtService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SheepListController.class)
@AutoConfigureMockMvc(addFilters = false)
class SheepListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthFilter jwtAuthFilter;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private SheepRepository sheepRepository;

    @Test
    @DisplayName("GET /api/sheeplist returns paged SheepListDto for given year without name filter")
    void getSheepList_returnsSheepListForYear() throws Exception {
        // given
        Long currentYear = 2024L;
        Long previousYear = 2023L;   // controller will compute this, just for our mock
        int page = 0;
        int size = 20;

        SheepListDto dto1 = new SheepListDto(
                1L,
                "Dolly",
                new Date(120, 3, 31),
                10L, // fatherId
                11L, // motherId
                null, // fosterMotherId
                2020,
                null, // dateOfDeath
                null, // reasonOfDeath
                "white",
                null, // oldName
                "Some breeder",
                "F",  // gender
                3L,   // lambCountThisYear
                5L    // lambCountPreviousYears
        );

        SheepListDto dto2 = new SheepListDto(
                2L,
                "Shaun",
                new Date(119, 3, 31),
                20L,
                21L,
                null,
                2019,
                null,
                null,
                "black",
                null,
                "Another breeder",
                "M",
                2L,
                4L
        );

        Page<SheepListDto> pageResult =
                new PageImpl<>(List.of(dto1, dto2), PageRequest.of(page, size), 2);

        // when repo is called with (2024, 2023, null, pageable) return pageResult
        Mockito.when(sheepRepository.findSheepList(
                        eq(currentYear),
                        eq(previousYear),
                        isNull(),
                        any(Pageable.class)
                ))
                .thenReturn(pageResult);

        // when + then
        mockMvc.perform(get("/api/sheeplist")
                        .param("currentYear", currentYear.toString())
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                // Page wrapper: content is an array inside it
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].name", is("Dolly")))
                .andExpect(jsonPath("$.content[0].lambCountThisYear", is(3)))
                .andExpect(jsonPath("$.content[0].lambCountPreviousYears", is(5)))
                .andExpect(jsonPath("$.content[1].id", is(2)))
                .andExpect(jsonPath("$.content[1].name", is("Shaun")));
    }
}
