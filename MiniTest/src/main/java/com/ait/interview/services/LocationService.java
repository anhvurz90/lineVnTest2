package com.ait.interview.services;

import com.ait.interview.domain.Location;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface LocationService {
  Location getById(Long id);
  List<Location> getAll();
  List<Location> getListArea();

  int countListAllIgnoreRootArea();
  List<Location> getListAllIgnoreRootArea(Pageable pageable);
  
  int countAllLocationByArea(int area);
  List<Location> getAllLocationByArea(int area, Pageable pageable);
}
