package com.ait.interview.services;

import com.ait.interview.domain.Location;
import java.util.List;

public interface LocationService {
  Location getById(Long id);
  List<Location> getAll();
  List<Location> getAllLocationByArea(int area);
  List<Location> getListArea();

  List<Location> getListAllIgnoreRootArea();
}
