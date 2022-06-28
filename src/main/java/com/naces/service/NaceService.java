package com.naces.service;

import com.naces.dto.NaceDto;

public interface NaceService {

    /**
     * Creates nomenclature of economic activities.
     *
     * @param naceDto the nomenclature of economic activities details
     * @return nomenclature of economic activities
     */
    NaceDto createNace(NaceDto naceDto);

    /**
     * Gets nomenclature of economic activities data by order id.
     *
     * @param order the order id
     * @return nomenclature of economic activities
     */
    NaceDto getNaceByOrder(Long order);
}
