package com.cognizant.weatherapp.view;

import com.cognizant.weatherapp.base.BaseView;

/**
 * Created by rajajawahar on 24/02/18.
 */

public interface SearchView extends BaseView {

  void navigateToDetails(String input);

  void showError(String message);
}
