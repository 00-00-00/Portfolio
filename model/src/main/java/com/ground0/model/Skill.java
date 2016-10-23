package com.ground0.model;

/**
 * Created by zer0 on 14/10/16.
 */

public class Skill {

  Integer proficiencyPercent;
  String category;
  String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getProficiencyPercent() {
    return proficiencyPercent;
  }

  public void setProficiencyPercent(Integer proficiencyPercent) {
    this.proficiencyPercent = proficiencyPercent;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
