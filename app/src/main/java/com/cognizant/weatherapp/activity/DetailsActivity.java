package com.cognizant.weatherapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.cognizant.weatherapp.R;
import com.cognizant.weatherapp.WeatherListViewModel;
import com.cognizant.weatherapp.adapter.WeatherListAdapter;
import com.cognizant.weatherapp.presenter.DetailsPresenter;
import com.cognizant.weatherapp.view.DetailsView;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class DetailsActivity extends AppCompatActivity implements DetailsView {

  @BindView(R.id.rv_details) RecyclerView rvDetails;

  private WeatherListAdapter weatherListAdapter;

  public static Intent getCallingIntent(Activity activity, String searchKey) {
    Intent intent = new Intent(activity, DetailsActivity.class);
    intent.putExtra("searchKey", searchKey);
    return intent;
  }

  private String searchKey;
  private DetailsPresenter detailsPresenter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_details);
    ButterKnife.bind(this);
    detailsPresenter = new DetailsPresenter();
    detailsPresenter.attachView(this);
    weatherListAdapter = new WeatherListAdapter(this);
    rvDetails.setLayoutManager(new LinearLayoutManager(this, VERTICAL, false));
    rvDetails.setHasFixedSize(true);
    rvDetails.setAdapter(weatherListAdapter);
    if (getIntent().hasExtra("searchKey")) {
      searchKey = getIntent().getStringExtra("searchKey");
    }
    if (!searchKey.isEmpty()) {
      detailsPresenter.fetchDataFromAPI(searchKey);
    }
  }

  @Override public Context getcontext() {
    return this;
  }

  @Override public void setTitle(String title) {
    getSupportActionBar().setTitle(title);
  }

  @Override public void setDataToList(List<WeatherListViewModel> listViewModels) {
    weatherListAdapter.setDataToList(listViewModels);
  }

  @Override public void showError(String message) {
    Snackbar.make(rvDetails, message, Snackbar.LENGTH_SHORT).show();
  }
}
