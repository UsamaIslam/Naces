package com.naces.controller;

import com.naces.dto.NaceDto;
import com.naces.service.NaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/naces")
@Slf4j
public class NaceController {

    @Autowired
    private NaceService naceService;

    /**
     * Creates nomenclature of economic activities.
     *
     * @param naceDto the nomenclature of economic activities details
     * @return nomenclature of economic activities
     */
    @PostMapping("/create")
    public ResponseEntity<NaceDto> putNaceDetails(@Valid @RequestBody NaceDto naceDto) {
       log.info("NaceController::putNaceDetails() start to create nomenclature of economic details by order id:{}", naceDto.getOrder());
        naceDto = naceService.createNace(naceDto);
        return new ResponseEntity<>(naceDto, HttpStatus.CREATED);

    }

    /**
     * Gets nomenclature of economic activities data by order id.
     *
     * @param order the order id
     * @return nomenclature of economic activities
     */
    @GetMapping("/{order}/getNaces")
    public ResponseEntity<NaceDto> getNaceDetails(@PathVariable(name = "order") Long order) {
        log.info("NaceController::getNaceDetails() start to get nomenclature of economic details by order id:{}", order);
        NaceDto naceDto = naceService.getNaceByOrder(order);
        return new ResponseEntity<>(naceDto, HttpStatus.OK);
    }
}
