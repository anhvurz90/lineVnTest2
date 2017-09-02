package com.ait.interview.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(optional = false)
  private Location location;

  @Column
  private String uuid;

  @Column
  private String username;

  @Column
  private int active;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
  private Set<Chat> chats;

  protected User() {

  }

  public User(String uuid, String username, int active) {
    this.uuid = uuid;
    this.username = username;
    this.active = active;
  }

  public Long getId() {
    return id;
  }

  public String getUuid() {
    return uuid;
  }

  public int getActive() {
    return active;
  }

  public String getUsername() {
    return username;
  }

  public Location getLocation() {
    return location;
  }

  public Set<Chat> getChats() {
    return chats;
  }
}
