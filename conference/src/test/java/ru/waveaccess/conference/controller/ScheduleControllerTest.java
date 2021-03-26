package ru.waveaccess.conference.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.waveaccess.conference.controller.rest.ScheduleRestController;
import ru.waveaccess.conference.mapper.ScheduleDtoMapper;
import ru.waveaccess.conference.model.form.PresentationForm;
import ru.waveaccess.conference.security.details.UserDetailsServiceImpl;
import ru.waveaccess.conference.service.interfaces.ScheduleService;

import javax.sql.DataSource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ScheduleRestController.class)
public class ScheduleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ScheduleService scheduleService;
    @MockBean
    private ScheduleDtoMapper scheduleDtoMapper;
    @MockBean
    private UserDetailsServiceImpl userDetailsService;
    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @MockBean
    private DataSource dataSource;


    @BeforeEach
    public void setUp() {
        ScheduleRestController scheduleRestController = new ScheduleRestController(scheduleService, scheduleDtoMapper);
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleRestController).build();
    }

    @Test
    public void getPresentationTest() throws Exception {


        PresentationForm presentationForm = PresentationForm.builder()
                                                            .name("Test")
                                                            .build();


        mockMvc.perform(get("/schedule/api/room/303")).andExpect(status().isOk());

    }
}
