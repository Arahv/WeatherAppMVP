package com.cognizant.weatherapp.view;

import com.cognizant.weatherapp.WeatherListViewModel;
import com.cognizant.weatherapp.base.BaseView;
import java.util.List;

/**
 * Created by rajajawahar on 24/02/18.
 */

public interface DetailsView extends BaseView {

  void setTitle(String title);

  void setDataToList(List<WeatherListViewModel> listViewModels);

  void showError(String message);
}
