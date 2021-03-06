package com.ait.interview.services;

import com.ait.interview.domain.Location;
import com.ait.interview.repositories.LocationRepository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component("loationService")
public class LocationServiceImpl implements LocationService {
  private final LocationRepository locationRepository;

  @Override
  @Cacheable("getLocationById")
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
  @Cacheable("getAllLocationByArea")
  public Page<Location> getAllLocationByArea(int area, Pageable pageable) {
    return this.locationRepository.findAllByAreaAndParentIdIsNot(area, 1L, pageable);
  }

  @Override
  @Cacheable("getListArea")
  public List<Location> getListArea() {
    return this.locationRepository.findByParentId(1L);
  }

  @Override
  @Cacheable("getListAllIgnoreRootArea")
  public Page<Location> getListAllIgnoreRootArea(Pageable pageable) {
    return locationRepository.findAllByAreaNotAndParentIdIsNot(0, 1L, pageable);
  }
  
}
