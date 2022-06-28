package com.naces.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naces.dto.NaceDto;
import com.naces.model.NaceEntity;

import java.sql.Timestamp;

public final class NaceUtil {

    private NaceUtil() {
        throw new IllegalArgumentException("Util class");
    }

    /**
     * Maps nace dto to nace entity.
     *
     * @param naceDto the nomenclature of economic details
     * @return nomenclature of economic entity
     */
    public static NaceEntity mapNaceEntity(NaceDto naceDto) {
        ObjectMapper mapper = new ObjectMapper();
        NaceEntity naceEntity = mapper.convertValue(naceDto, NaceEntity.class);
        naceEntity.setUpdatedDateTime(new Timestamp(System.currentTimeMillis()));
        return naceEntity;
    }

    /**
     * Maps nace entity to nace dto.
     *
     * @param naceEntity the nomenclature of economic entity
     * @return nomenclature of economic details
     */
    public static NaceDto mapNaceDto(NaceEntity naceEntity) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(naceEntity, NaceDto.class);
    }
}
