package com.gainsguru.gainsgurudb.entry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import java.sql.Date;
import lombok.Data;

@Entity
@Data
@Table(name = "entries")
public class Entry {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "fat")
  private float fat;

  @Column(name = "protein")
  private float protein;

  @Column(name = "carbs")
  private float carbs;

  @Column(name = "calories")
  private int calories;

  @Column(name = "date")
  private Date date;

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public float getProtein() {
    return this.protein;
  }

  public float getCarbs() {
    return this.carbs;
  }

  public int getCalories() {
    return this.calories;
  }

  public Date getDate() {
    return this.date;
  }

  public float getFat() {
    return this.fat;
  }

  public void setName(String name2) {
    this.name = name2;
  }

  public void setFat(float fat2) {
    this.fat = fat2;
  }

  public void setProtein(float protein2) {
    this.protein = protein2;
  }

  public void setCarbs(float carbs2) {
    this.carbs = carbs2;
  }

  public void setDate(Date date2) {
    this.date = date2;
  }

}