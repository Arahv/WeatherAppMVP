package com.cognizant.weatherapp.base;

/**
 * Created by rajajawahar on 24/02/18.
 */

public interface BasePresenter<V extends BaseView> {

  void attachView(V view);

  void detachView();

  void destroy();
}
