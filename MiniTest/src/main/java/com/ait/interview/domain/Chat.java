package com.ait.interview.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Chat {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String title;

  @ManyToOne
  private User owner;

  @ManyToOne
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
