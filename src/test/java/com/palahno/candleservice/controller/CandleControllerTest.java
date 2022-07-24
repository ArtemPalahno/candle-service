package com.palahno.candleservice.controller;

import com.palahno.candleservice.entity.Candle;
import com.palahno.candleservice.service.CandleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Artem Palahno
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CandleController.class)
class CandleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandleService candleService;

    @Test
    void shouldReturnCandleWhenRequestIsValid() throws Exception {
        String candleName = "testName";
        Candle candle = buildCandle(candleName);

        Mockito.when(candleService.findAllByName(candleName)).thenReturn(List.of(candle));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/"+candleName+"/candles")
                        .param("name", candleName)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].name").value(candleName))
                .andExpect(status().isOk());
    }

    @Test
    void shouldThrowExceptionWhenStockIsAbsent() throws Exception {
        String candleName = "testName";

        Mockito.when(candleService.findAllByName(candleName)).thenReturn(List.of());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/"+candleName+"/candles")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    private Candle buildCandle(String name) {
        Candle candle = new Candle();
        candle.setName(name);
        return candle;
    }
}