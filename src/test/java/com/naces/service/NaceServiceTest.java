package com.naces.service;

import com.naces.dto.NaceDto;
import com.naces.model.NaceEntity;
import com.naces.repository.NaceRepository;
import com.naces.service.impl.NaceServiceImpl;
import com.naces.util.NaceUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class NaceServiceTest {

    @InjectMocks
    private NaceServiceImpl service;

    @Mock
    private NaceRepository repository;

    @Test
    void testCreateNacesWithRequiredParamsExpectedNacesDto() {
        NaceEntity entity = NaceUtil.mapNaceEntity(getNaceDto());
        //given
        Mockito.when(repository.save(entity)).thenReturn(entity);
        //when
        NaceDto naceDto = service.createNace(getNaceDto());
        //then
        Assertions.assertNotNull(naceDto);
        Assertions.assertEquals(398481, naceDto.getOrder());
        Assertions.assertEquals("A", naceDto.getCode());
        Assertions.assertEquals("AGRICULTURE FORESTRY AND FISHING", naceDto.getDescription());
        Assertions.assertEquals(1, naceDto.getLevel());
        Assertions.assertEquals("A", naceDto.getReference());
        Assertions.assertEquals(
            "This section includes the exploitation of vegetal and animal natural resources, comprising the " +
                "activities of growing of crops, raising and breeding of animals, harvesting of timber and other " +
                "plants, animals or animal products from a farm", naceDto.getItemInclude());
        Mockito.verify(repository, Mockito.times(1)).save(entity);
    }

    @Test
    void testGetNacesWhenPassedOrderExpectNacesDto() {
        NaceEntity entity = NaceUtil.mapNaceEntity(getNaceDto());
        //given
        Mockito.when(repository.findById(398481L)).thenReturn(Optional.of(entity));
        //when
        NaceDto naceDto = service.getNaceByOrder(398481L);
        //then
        Assertions.assertEquals(398481, naceDto.getOrder());
        Assertions.assertEquals("A", naceDto.getCode());
        Assertions.assertEquals("AGRICULTURE FORESTRY AND FISHING", naceDto.getDescription());
        Assertions.assertEquals(1, naceDto.getLevel());
        Assertions.assertEquals("A", naceDto.getReference());
        Assertions.assertEquals(
            "This section includes the exploitation of vegetal and animal natural resources, comprising the " +
                "activities of growing of crops, raising and breeding of animals, harvesting of timber and other " +
                "plants, animals or animal products from a farm", naceDto.getItemInclude());
        Mockito.verify(repository, Mockito.times(1)).findById(398481L);
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
