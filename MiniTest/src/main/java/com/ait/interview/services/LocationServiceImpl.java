package com.ait.interview.services;

import com.ait.interview.domain.Location;
import com.ait.interview.repositories.LocationRepository;
import java.util.List;
import org.springframework.stereotype.Component;
import sun.jvm.hotspot.utilities.Assert;

@Component("loationService")
public class LocationServiceImpl implements LocationService {
  private final LocationRepository locationRepository;

  @Override
  public Location getById(Long id) {
    return this.locationRepository.findById(id);
  }

  public LocationServiceImpl(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  @Override
  public List<Location> getAll() {
    return this.locationRepository.findAll();
  }

  @Override
  public List<Location> getAllLocationByArea(int area) {

    return this.locationRepository.findAllByArea(area);
  }

  @Override
  public List<Location> getListArea() {
    return this.locationRepository.findByParentId(1L);
  }

  @Override
  public List<Location> getListAllIgnoreRootArea() {
    return this.locationRepository.findAllByAreaNotAndParentIdIsNot(0, 1L);
  }
}
