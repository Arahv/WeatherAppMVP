package com.cognizant.weatherapp.presenter;

import com.cognizant.weatherapp.ReponseListener;
import com.cognizant.weatherapp.WeatherListViewModel;
import com.cognizant.weatherapp.app.AppController;
import com.cognizant.weatherapp.base.BasePresenter;
import com.cognizant.weatherapp.data.GetWeatherResponse;
import com.cognizant.weatherapp.data.WeatherRepo;
import com.cognizant.weatherapp.utils.LoggerUtils;
import com.cognizant.weatherapp.view.DetailsView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajajawahar on 24/02/18.
 */

public class DetailsPresenter implements BasePresenter<DetailsView> {

  private DetailsView view;
  private WeatherRepo weatherRepo;

  public DetailsPresenter() {
    weatherRepo = AppController.getInstance().getWeatherRepo();
  }

  @Override public void attachView(DetailsView view) {
    this.view = view;
  }

  @Override public void detachView() {
    view = null;
  }

  @Override public void destroy() {

  }

  public void fetchDataFromAPI(String input) {
    weatherRepo.getWeatherResponse(input, new ReponseListener() {
      @Override public void onSuccess(GetWeatherResponse response) {
        if (response.getCity() != null) {
          view.setTitle(String.format("%s / %s", response.getCity().getName(),
              response.getCity().getCountry()));
          convertWeatherListToViewModels(response);
        }
      }

      @Override public void onFailure(Throwable throwable) {
        LoggerUtils.logUnExpectedException(throwable);
        view.showError(throwable.getMessage());
      }
    });
  }

  private void convertWeatherListToViewModels(GetWeatherResponse getWeatherResponse) {
    List<WeatherListViewModel> weatherListViewModels = new ArrayList<>();
    List<GetWeatherResponse.Lists> list = getWeatherResponse.getList();
    for (int i = 0, size = list.size(); i < size; i++) {
      GetWeatherResponse.Lists lists = list.get(i);
      weatherListViewModels.add(createViewModel(lists));
    }
    view.setDataToList(weatherListViewModels);
  }

  private WeatherListViewModel createViewModel(GetWeatherResponse.Lists list) {
    WeatherListViewModel weatherListViewModel = new WeatherListViewModel();
    weatherListViewModel.setDate(list.getDate());
    weatherListViewModel.setMaximumTemp(list.getMain().getTempMax());
    weatherListViewModel.setMinimumTemp(list.getMain().getTempMin());
    return weatherListViewModel;
  }
}
