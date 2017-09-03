package com.ait.interview.controllers;

import com.ait.interview.domain.Location;
import com.ait.interview.domain.UserLocation;
import com.ait.interview.services.ChatService;
import com.ait.interview.services.LocationService;
import com.ait.interview.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  public Map<String, Object> ajax(@RequestParam(name = "area") int area,
                                  @RequestParam(name = "page") int page,
                                  @RequestParam(name = "count") int size) {
    if (area == 0) {
      int count = locationService.countListAllIgnoreRootArea();
      Pageable pageable = new PageRequest(page-1, size);
      List<Location> results = locationService.getListAllIgnoreRootArea(pageable);
      Map<String, Object> ret = new HashMap<>();
      ret.put("count", count);
      ret.put("results", results);
      return ret;
    } else {
      int count = locationService.countAllLocationByArea(area);
      Pageable pageable = new PageRequest(page-1, size);
      List<Location> results = locationService.getAllLocationByArea(area, pageable);
      Map<String, Object> ret = new HashMap<>();
      ret.put("count", count);
      ret.put("results", results);
      return ret;
    }
  }

  @GetMapping("/detail")
  public String detail(Model model, @RequestParam(name = "id") Long id) {
    Location location = this.locationService.getById(id);
    Location parent = this.locationService.getById(location.getId());
    List<UserLocation> users = this.userService.getUsersInLocation(location.getId());
    model.addAttribute("location", location);
    model.addAttribute("parent", parent);
    model.addAttribute("users", users);
    users.forEach(user -> {
      user.setCountChat(this.chatService.count(user.getUserId()));
      user.setLatestChat(this.chatService.getLatestChat(user.getUserId()));
    });
    return "location/detail";
  }
}
