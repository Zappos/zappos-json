package com.zappos.jacinda.data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 
 * @author hussachai
 *
 */
public class ContainingFormattableObject {

  private Date date = new Date();

  private Timestamp timestamp = new Timestamp(date.getTime());

  private BigDecimal bigDecimal = new BigDecimal(123);

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public BigDecimal getBigDecimal() {
    return bigDecimal;
  }

  public void setBigDecimal(BigDecimal bigDecimal) {
    this.bigDecimal = bigDecimal;
  }

}
