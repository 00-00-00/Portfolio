package com.ground0.portfolio.util;

/**
 * Created by zer0 on 10/10/16.
 */
public interface BackPressHandler {

  /***
   *
   * @return
   * true : if the back was handled
   * false : if the back was not handled
   */
  public boolean onBackPressed();
}
