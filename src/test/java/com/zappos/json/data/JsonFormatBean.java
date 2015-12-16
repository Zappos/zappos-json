/*
 * Copyright 2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *     http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */

package com.zappos.json.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zappos.json.ZapposJson;
import com.zappos.json.annot.JsonFormat;
import com.zappos.json.format.AbstractValueFormatter;
import com.zappos.json.format.ValueFormatter;

/**
 * 
 * @author Hussachai Puripunpinyo
 *
 */
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
    public Date cast(Object obj) {
      return Date.class.cast(obj);
    }
    
    @Override
    public String format(ZapposJson zapposJson, Date object) {
      if(getPattern() != null){
        return toJson(zapposJson, new SimpleDateFormat(getPattern()).format(object));
      }
      return toJson(zapposJson, FIXED_DATE_STRING);
    }
    
    @Override
    public Date parse(ZapposJson zapposJson, String string) throws Exception {
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
