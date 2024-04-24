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

import com.art.stuff.domain.ItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemRepository itemRepository;

    
    @Test
    @WithMockUser
    public void testCreateItem() throws Exception {
        mockMvc.perform(post("/additem")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Mug\", \"quantity\":18, \"typeId\":1, \"locationId\":1, \"isFragile\":true, \"isChecked\":false}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("additem")); 
    }


    @Test
    @WithMockUser
    public void testEditItem() throws Exception {
        mockMvc.perform(put("/items/edit/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Cup\", \"quantity\":19, \"typeId\":2, \"locationId\":2, \"isFragile\":true, \"isChecked\":true}"))
                .andExpect(status().isOk())
                .andExpect(content().string("edititem"));
    }

    @Test
    @WithMockUser
    public void testDeleteItem() throws Exception {
        mockMvc.perform(delete("/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("redirect:../stuff"));
    }
}
