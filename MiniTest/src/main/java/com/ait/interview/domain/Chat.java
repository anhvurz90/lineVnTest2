package com.ait.interview.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Chat {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String title;

  @ManyToOne
  @JsonIgnore
  private User owner;

  @ManyToOne
  @JsonIgnore
  private Location location;

  @Column
  private int status;

  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public User getOwner() {
    return owner;
  }

  public Location getLocation() {
    return location;
  }

  public int getStatus() {
    return status;
  }
}
