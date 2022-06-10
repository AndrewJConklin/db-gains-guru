package com.gainsguru.gainsgurudb.set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

import java.sql.Date;

import javax.persistence.Column;

import lombok.Data;

@Entity
@Data
@Table(name = "sets")
public class Set {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "exerciseID")
  private int exerciseID;

  @Column(name = "date")
  private Date date;

  @Column(name = "reps")
  private int reps;

  @Column(name = "weight")
  private int weight;
}