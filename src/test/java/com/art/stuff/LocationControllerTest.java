package com.art.stuff;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.MockMvc;

import com.art.stuff.domain.Location;
import com.art.stuff.domain.LocationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LocationRepository locationRepository;

    @Test
    @WithMockUser
    public void testCreateLocation() throws Exception {
        Location newLocation = new Location();
        newLocation.setName("Shelf 3");

        mockMvc.perform(post("/addlocation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newLocation)))
                .andExpect(status().isOk())
                .andExpect(content().string("addlocation")); 
    }

    @Test
    public void testDeleteLocation() throws Exception {
        mockMvc.perform(delete("/location/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateTypeUnauthorized() throws Exception {
        Location newLocation = new Location();
        newLocation.setName("Shelf 1");

        mockMvc.perform(post("/addlocation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newLocation)))
                .andExpect(status().isForbidden());
        
    }
    
}
