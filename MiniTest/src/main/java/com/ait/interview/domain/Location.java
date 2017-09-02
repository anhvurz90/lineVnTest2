package com.ait.interview.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Location {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "parent_id")
  private Long parentId;

  @Column
  private String name;

  @Column
  private int area;

  @Column(name = "name_hiragana")
  private String nameHiragana;

  @Column(name = "name_katakana")
  private String nameKatakana;

  @Column(name = "name_latin")
  private String nameLatin;

  @Column
  private Float latitude;

  @Column
  private Float longitude;

  protected Location() {

  }

  public Location(Long parentId, String name) {
    this.parentId = parentId;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Long getParentId() {
    return parentId;
  }

  public Long getId() {
    return id;
  }

  public int getArea() {
    return area;
  }
}
