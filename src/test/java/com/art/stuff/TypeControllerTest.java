package com.art.stuff;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;
// import org.springframework.security.test.context.support.WithMockUser;
// import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
// import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import com.art.stuff.domain.Type;
import com.art.stuff.domain.TypeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc

// @WebMvcTest(TypeController.class)
public class TypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TypeRepository typeRepository;

    @Test
    @WithMockUser
    public void testCreateType() throws Exception {
        Type newType = new Type();
        newType.setName("Clothes");

        mockMvc.perform(post("/addtype")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newType)))
                .andExpect(status().isOk())
                .andExpect(content().string("addtype")); 
    }

    @Test
    public void testDeleteType() throws Exception {
        mockMvc.perform(delete("/type/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateTypeUnauthorized() throws Exception {
        Type newType = new Type();
        newType.setName("Clothes");

        mockMvc.perform(post("/addtype")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newType)))
                .andExpect(status().isForbidden());
        
    }
    
}