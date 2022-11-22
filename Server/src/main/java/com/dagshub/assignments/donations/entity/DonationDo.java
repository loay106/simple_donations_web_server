package com.dagshub.assignments.donations.entity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Document(collection = "donations")
public class DonationDo {

  static public DateTimeFormatter FMT = DateTimeFormatter
          .ofPattern("yyyy-MM-dd HH:mm:ss")
          .withZone(ZoneId.systemDefault());
  @Id
  private String id;
  private Integer amount;
  private String date;

  public DonationDo() {
  }

  public DonationDo(String id,Integer amount,Instant date) {
    this.id = id;
    this.amount = amount;
    this.date = formatDate(date);
  }
  public String getId() {
    return id;
  }

  public Integer getAmount() {
    return amount;
  }

  public Instant getDate() {
    return parseDate(date);
  }

  static public String formatDate(Instant date){
    return FMT.format(date);
  }

  static public Instant parseDate(String date){
    return Instant.from(FMT.parse(date));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("amount", amount)
        .add("date",date)
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
    DonationDo donation = (DonationDo) o;
    return Objects.equal(id, donation.id) &&
        Objects.equal(amount, donation.amount)
            && Objects.equal(date,donation.date);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, amount,date);
  }
}
