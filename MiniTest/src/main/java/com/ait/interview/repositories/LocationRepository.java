package com.ait.interview.repositories;

import com.ait.interview.domain.Location;
import com.ait.interview.domain.LocationCustom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface LocationRepository extends Repository<Location, Long> {
  List<Location> findAll();
  List<Location> findByParentId(Long parentId);
  
  Page<Location> findAllByAreaNotAndParentIdIsNot(int area, Long parentId, Pageable pageable);
  
  Location findById(Long id);

  @Query("select id, name from Location ")
  List<LocationCustom> custom();
  
  Page<Location> findAllByAreaAndParentIdIsNot(int area, Long parentId, Pageable pageable);
}
