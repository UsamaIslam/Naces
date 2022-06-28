package com.naces.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naces.dto.NaceDto;
import com.naces.exception.ErrorResponse;
import com.naces.exception.ValidationError;
import com.naces.service.NaceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "/application-test.properties")
class NaceControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NaceService service;

    @Test
    void testCreateNaceWhenValidPayloadExpectNaceDto() throws Exception {
        String body = objectMapper.writeValueAsString(getNaceDto());
        MvcResult result = mvc.perform(post("/api/v1/naces/create").contentType("application/json").content(body))
            .andExpect(status().is2xxSuccessful()).andReturn();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
        NaceDto naceDto = objectMapper.readValue(result.getResponse().getContentAsString(), NaceDto.class);
        Assertions.assertEquals(398481L, naceDto.getOrder());
        Assertions.assertEquals("A", naceDto.getCode());
        Assertions.assertEquals("AGRICULTURE FORESTRY AND FISHING", naceDto.getDescription());
        Assertions.assertEquals(1, naceDto.getLevel());
        Assertions.assertEquals("A", naceDto.getReference());
        Assertions.assertEquals(
            "This section includes the exploitation of vegetal and animal natural resources, comprising the " +
                "activities of growing of crops, raising and breeding of animals, harvesting of timber and other " +
                "plants, animals or animal products from a farm", naceDto.getItemInclude());
        Assertions.assertNotNull(naceDto.getCreateDateTime());
        Assertions.assertNotNull(naceDto.getUpdatedDateTime());

    }

    @Test
    void testGetNacesWhenValidPayloadExpectNaceDto() throws Exception {
        MvcResult result = mvc.perform(get("/api/v1/naces/{order}/getNaces", 398481).contentType("application/json")).andExpect(
            status().isOk()).andReturn();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
       NaceDto naceDto = objectMapper.readValue(result.getResponse().getContentAsString(), NaceDto.class);
        Assertions.assertEquals(398481L, naceDto.getOrder());
        Assertions.assertEquals("A", naceDto.getCode());
        Assertions.assertEquals("AGRICULTURE FORESTRY AND FISHING", naceDto.getDescription());
        Assertions.assertEquals(1, naceDto.getLevel());
        Assertions.assertEquals("A", naceDto.getReference());
        Assertions.assertEquals(
            "This section includes the exploitation of vegetal and animal natural resources, comprising the " +
                "activities of growing of crops, raising and breeding of animals, harvesting of timber and other " +
                "plants, animals or animal products from a farm", naceDto.getItemInclude());
        Assertions.assertNotNull(naceDto.getCreateDateTime());
        Assertions.assertNotNull(naceDto.getUpdatedDateTime());

    }

    @Test
    void testCreateNaceWhenInValidPayloadExpectBadRequestResponse() throws Exception {
        NaceDto dto = getNaceDto();
        dto.setLevel(null);
        dto.setReference("");
        dto.setItemInclude("");
        dto.setDescription("");
        dto.setCode("");
        String body = objectMapper.writeValueAsString(dto);
        MvcResult result = mvc.perform(post("/api/v1/naces/create").contentType("application/json").content(body))
            .andExpect(status().is4xxClientError()).andReturn();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
        ValidationError naceDtos = objectMapper.readValue(result.getResponse().getContentAsString(),
            ValidationError.class);
        Assertions.assertNotNull(naceDtos);
        Assertions.assertEquals(6, naceDtos.getErrorResponses().size());

    }

    @Test
    void testGetNacesWhenInValidOrderIdPassedExpectNaceDto() throws Exception {
        MvcResult result = mvc.perform(get("/api/v1/naces/{order}/getNaces", 398471).contentType("application/json")).andExpect(
            status().isNotFound()).andReturn();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
        ErrorResponse errorResponse = objectMapper.readValue(result.getResponse().getContentAsString(), ErrorResponse.class);
        Assertions.assertEquals("No Record Found against order id",errorResponse.getMessage());


    }

    private NaceDto getNaceDto() {
        NaceDto naceDto = new NaceDto();
        naceDto.setOrder(398481L);
        naceDto.setCode("A");
        naceDto.setDescription("AGRICULTURE FORESTRY AND FISHING");
        naceDto.setItemExclude("");
        naceDto.setItemAlsoInclude("");
        naceDto.setLevel(1);
        naceDto.setItemInclude(
            "This section includes the exploitation of vegetal and animal natural resources, comprising the " +
                "activities of growing of crops, raising and breeding of animals, harvesting of timber and other " +
                "plants, animals or animal products from a farm");
        naceDto.setReference("A");
        return naceDto;

    }
}
