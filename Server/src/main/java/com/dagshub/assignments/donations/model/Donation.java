package com.dagshub.assignments.donations.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.time.Clock;
import java.time.Instant;
import java.util.UUID;

public class Donation {
  private String id;
  private Integer amount;
  private Instant date;

  public Donation() {
  }

  public Donation(Integer amount) {
    this.id = UUID.randomUUID().toString();
    this.amount = amount;
    this.date = Instant.now(Clock.systemUTC());
  }

  public Donation(String id, Integer amount,Instant date) {
    this.id = id;
    this.amount = amount;
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public Integer getAmount() {
    return amount;
  }

  public Instant getDate() {
    return date;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("amount", amount)
        .add("date",date.toString())
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Donation donation = (Donation) o;
    return Objects.equal(id, donation.id) &&
        Objects.equal(amount, donation.amount);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, amount, date);
  }
}
