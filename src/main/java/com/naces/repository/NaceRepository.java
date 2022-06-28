package com.naces.repository;

import com.naces.model.NaceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NaceRepository extends CrudRepository<NaceEntity, Long> {

}
