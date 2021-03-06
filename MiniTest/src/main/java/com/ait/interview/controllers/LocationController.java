package com.ait.interview.controllers;

import com.ait.interview.domain.Location;
import com.ait.interview.domain.UserLocation;
import com.ait.interview.services.ChatService;
import com.ait.interview.services.LocationService;
import com.ait.interview.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/location")
public class LocationController {

  @Autowired
  private LocationService locationService;

  @Autowired
  private UserService userService;

  @Autowired
  private ChatService chatService;

  @GetMapping("/")
  public String index(Model model) {
    return "location/all";
  }

  @GetMapping("/areas")
  @ResponseBody
  public List<Location> areas() {
    return locationService.getListArea();
  }

  
  @GetMapping("/ajax")
  @ResponseBody
  public Page<Location> ajax(@RequestParam(name = "area") int area,
                                  @RequestParam(name = "page") int page,
                                  @RequestParam(name = "count") int size) {
    if (area == 0) {
      Pageable pageable = new PageRequest(page-1, size);
      Page<Location> pageResult = locationService.getListAllIgnoreRootArea(pageable);
      return pageResult;
    } else {
      Pageable pageable = new PageRequest(page-1, size);
      Page<Location> pageResult = locationService.getAllLocationByArea(area, pageable);
      return pageResult;
    }
  }

  @GetMapping("/detail")
  public String detail(Model model, @RequestParam(name = "id") Long id) {
    return "location/detail";
  }
  
  @GetMapping("/users")
  @ResponseBody
  public Page<UserLocation> users(@RequestParam Map<String, String> params) {
//      @RequestParam(name = "id") Long locationId,
//      @RequestParam(name = "page") int page,
//      @RequestParam(name = "count") int size
    long locationId = Long.parseLong(params.get("id"));
    String searchPattern = params.containsKey("searchPattern")? params.get("searchPattern"): "";
    Pageable pageable = buildPageable(params);
    Location location = this.locationService.getById(locationId);
    Location parent = this.locationService.getById(location.getId());
    
    //int count = userService.countUsersInLocation(locationId, searchPattern);
    Page<UserLocation> users = userService.getUsersInLocation(location.getId(), searchPattern, pageable);
    return users;
    
//    Map<String, Object> ret = new HashMap<>();
//    ret.put("count", count);
//    ret.put("results", users);
//    return ret;
  }

  private Pageable buildPageable(Map<String, String> params) {
    int page = Integer.parseInt(params.get("page"));
    int size = Integer.parseInt(params.get("count"));
    for (String param: params.keySet()) {
        if (param.startsWith("sorting[")) {
            String prop = param.substring("sorting[".length());
            prop = prop.substring(0, prop.length()-1);
            String direction = params.get(param);
            return new PageRequest(page-1, size, 
                                   "asc".equalsIgnoreCase(direction)?Sort.Direction.ASC: Sort.Direction.DESC, 
                                   convert(prop));
        }
    }
    return new PageRequest(page-1, size);
  }
  
  private String convert(String prop) {
      switch (prop) {
          case "userName": return "username";
          case "locationName": return "location.name";
      }
      return prop;
  }
}
