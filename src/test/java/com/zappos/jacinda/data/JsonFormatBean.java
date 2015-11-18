package com.zappos.jacinda.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zappos.json.annot.JsonFormat;
import com.zappos.json.format.AbstractValueFormatter;
import com.zappos.json.format.ValueFormatter;

public class JsonFormatBean {
  
  public static class CustomDateFormatter extends AbstractValueFormatter<Date>{
    public static final String FIXED_DATE_STRING = "1998-01-01";
    public static final Date FIXED_DATE;
    static{
      try {
        FIXED_DATE = new SimpleDateFormat("yyyy-MM-dd").parse(FIXED_DATE_STRING);
      } catch (ParseException e) {
        throw new RuntimeException(e);
      }
    }
    @Override
    public String format(Date object) {
      if(getPattern() != null){
        return toJson(new SimpleDateFormat(getPattern()).format(object));
      }
      return toJson(FIXED_DATE_STRING);
    }
    @Override
    public Date parse(String string) throws Exception {
      if(getPattern() != null){
        return new SimpleDateFormat(getPattern()).parse(string);
      }
      return FIXED_DATE;
    }
    @Override
    public ValueFormatter<Date> newInstance() {
      return new CustomDateFormatter();
    }
  }
  
  @JsonFormat("yyyy-MM-dd")
  private Date date = new Date();
  
  @JsonFormat(formatter=CustomDateFormatter.class)
  private Date fixedDate = new Date();
  
  @JsonFormat(value = "dd MMM yyyy", formatter = CustomDateFormatter.class)
  private Date date2 = new Date();
  
  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Date getFixedDate() {
    return fixedDate;
  }

  public void setFixedDate(Date fixedDate) {
    this.fixedDate = fixedDate;
  }

  public Date getDate2() {
    return date2;
  }

  public void setDate2(Date date2) {
    this.date2 = date2;
  }
  
  
}
