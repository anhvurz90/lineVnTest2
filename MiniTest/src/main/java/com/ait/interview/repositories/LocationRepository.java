package com.ait.interview.repositories;

import com.ait.interview.domain.Location;
import com.ait.interview.domain.LocationCustom;

import java.util.List;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface LocationRepository extends Repository<Location, Long> {
  List<Location> findAll();
  List<Location> findByParentId(Long parentId);
  
  List<Location> findAllByAreaNotAndParentIdIsNot(int area, Long parentId, Pageable pageable);
  
  //@Query(select count(*) from Location where )
  Integer countByAreaNotAndParentIdIsNot(int area, Long parentId);
  Location findById(Long id);

  @Query("select id, name from Location ")
  List<LocationCustom> custom();
  
  Integer countByArea(int area);
  List<Location> findAllByArea(int area, Pageable pageable);
}
