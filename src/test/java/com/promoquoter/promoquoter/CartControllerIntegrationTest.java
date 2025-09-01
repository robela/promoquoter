package com.promoquoter.promoquoter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promoquoter.promoquoter.dto.CartQuoteRequest;
import com.promoquoter.promoquoter.dto.CartItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCartQuoteEndpoint() throws Exception {
        CartQuoteRequest request = new CartQuoteRequest();
        CartItemDto item = new CartItemDto();
        item.setProductId(1L); // just Make sure prduct with ID 1 exists in DB
        item.setQty(2);
        request.setItems(List.of(item));
        request.setCustomerSegment("REGULAR");

        mockMvc.perform(post("/cart/quote")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items").isArray())
                .andExpect(jsonPath("$.total").exists());
    }
}
