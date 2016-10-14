package com.ground0.portfolio.util;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * Created by zer0 on 14/10/16.
 */

public class LocalDateUtil {
  final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  public static String getDateString(LocalDateTime localDateTime) {
    return localDateTime.format(DATE_TIME_FORMATTER);
  }
}
