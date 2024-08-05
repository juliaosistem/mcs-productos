package com.juliaosystem.api.controller;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.common.lib.api.response.PlantillaResponse;
import com.common.lib.utils.errors.AbtractError;
import com.juliaosystem.api.dtos.ProductoDTO;
import com.juliaosystem.infraestructure.services.primary.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ImportAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})

class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @MockBean
    private AbtractError abtractError;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
     void testConcurrentUpdate() throws Exception {

        UUID productoId = UUID.randomUUID();
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(productoId);
        productoDTO.setCantidad(100);


        when(productoService.update(any(ProductoDTO.class))).thenAnswer(invocation -> {

            Thread.sleep(100);
            return PlantillaResponse.<ProductoDTO>builder()
                    .httpStatus(HttpStatus.OK)
                    .data(productoDTO)
                    .build();
        });

        ExecutorService executor = Executors.newFixedThreadPool(10);
        Future<?>[] futures = new Future[10];

        for (int i = 0; i < 10; i++) {
            final int index = i;
            futures[i] = executor.submit(() -> {
                try {
                    ResultActions resultActions = mockMvc.perform(put("/productos/update")
                            .header("ip", "127.0.0.1")
                            .header("dominio", "example.com")
                            .header("usuario", "testUser")
                            .header("idBussines", "1")
                            .header("proceso", "testProcess")
                            .content(objectMapper.writeValueAsString(productoDTO))
                            .contentType(MediaType.APPLICATION_JSON));

                    MvcResult result = resultActions.andReturn();
                    resultActions
                            .andExpect(status().isOk())
                            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }


        for (Future<?> future : futures) {
            future.get();
        }
        verify(productoService).update(any(ProductoDTO.class));
    }
}


