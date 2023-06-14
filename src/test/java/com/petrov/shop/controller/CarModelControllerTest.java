package com.petrov.shop.controller;

import com.petrov.shop.dto.CarLabelDto;
import com.petrov.shop.dto.CarModelDto;
import com.petrov.shop.entity.CarLabel;
import com.petrov.shop.entity.CarModel;
import com.petrov.shop.service.CarLabelService;
import com.petrov.shop.service.CarModelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
public class CarModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarLabelService carLabelService;

    @Autowired
    private CarModelService carModelService;

    @Test
    public void testCarLabelAndModel() throws Exception {
        CarLabel carLabel1 = carLabelService.saveOrUpdate(new CarLabelDto(null, "testLabel1"));
        CarLabel carLabel2 = carLabelService.saveOrUpdate(new CarLabelDto(null, "testLabel2"));

        CarModel carModel1 = carModelService.saveOrUpdate(new CarModelDto(null, "testModel1", new BigDecimal(10000000000000000L), carLabel1.getLabelName()));
        CarModel carModel2 = carModelService.saveOrUpdate(new CarModelDto(null, "testModel2", new BigDecimal(3000), carLabel2.getLabelName()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/models")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].modelName", is(carModel1.getModelName())))
                .andExpect(jsonPath("$[1].modelName", is(carModel2.getModelName())));

        System.out.println(carLabel1.getLabelName());
        System.out.println(carLabel2.getLabelName());
    }
}
