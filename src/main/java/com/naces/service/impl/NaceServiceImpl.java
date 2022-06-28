package com.naces.service.impl;

import com.naces.dto.NaceDto;
import com.naces.model.NaceEntity;
import com.naces.repository.NaceRepository;
import com.naces.service.NaceService;
import com.naces.util.NaceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Slf4j
public class NaceServiceImpl implements NaceService {

    @Autowired
    private NaceRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public NaceDto createNace(NaceDto naceDto) {
        NaceEntity entity = NaceUtil.mapNaceEntity(naceDto);
        log.info("NaceServiceImpl::createNace() start to create nomenclature of economic details by order id:{}",
            entity.getOrder());
        entity = repository.save(entity);
        log.info(
            "NaceServiceImpl::createNace() successfully created nomenclature of economic details by order id:{}, " +
                "create date time:{}",
            entity.getOrder(), entity.getCreateDateTime());
        return NaceUtil.mapNaceDto(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public NaceDto getNaceByOrder(Long order) {
        log.info("NaceServiceImpl::getNaceByOrder() start to get nomenclature of economic details by order id:{}",
            order);
        Optional<NaceEntity> entity = repository.findById(order);
        if (entity.isPresent()) {
            log.info(
                "NaceServiceImpl::getNaceByOrder() successfully to get nomenclature of economic details by order id:{}",
                order);
            return NaceUtil.mapNaceDto(entity.get());
        }
        throw new EntityNotFoundException();
    }
}
