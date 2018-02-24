package com.cognizant.weatherapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.cognizant.weatherapp.R;
import com.cognizant.weatherapp.presenter.SearchPresenter;
import com.cognizant.weatherapp.view.SearchView;

public class SearchActivity extends AppCompatActivity implements SearchView {

  @BindView(R.id.et_city) EditText etCity;
  private SearchPresenter searchPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);
    searchPresenter = new SearchPresenter();
    searchPresenter.attachView(this);
    ButterKnife.bind(this);
  }

  public void search(View view) {
    searchPresenter.validateInput(etCity.getText().toString().trim());
  }

  @Override public Context getcontext() {
    return this;
  }

  @Override public void navigateToDetails(String input) {
    startActivity(DetailsActivity.getCallingIntent(this, input));
  }

  @Override public void showError(String message) {
    Snackbar.make(etCity, message, Snackbar.LENGTH_SHORT).show();
  }

  @Override protected void onDestroy() {
    searchPresenter.destroy();
    super.onDestroy();
  }
}
