package com.cognizant.weatherapp.presenter;

import android.text.TextUtils;
import com.cognizant.weatherapp.base.BasePresenter;
import com.cognizant.weatherapp.view.SearchView;

/**
 * Created by rajajawahar on 24/02/18.
 */

public class SearchPresenter implements BasePresenter<SearchView> {

  private SearchView view;

  @Override public void attachView(SearchView view) {
    this.view = view;
  }

  @Override public void detachView() {
    view = null;
  }

  @Override public void destroy() {

  }

  public void validateInput(String input) {
    if (TextUtils.isEmpty(input)) {
      view.showError("Enter Valid Inout");
      return;
    }
    view.navigateToDetails(input);
  }
}
