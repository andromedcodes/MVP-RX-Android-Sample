package com.task.ui.component.Home;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.task.App;
import com.task.R;
import com.task.data.remote.dto.Scooter;
import com.task.ui.base.BaseActivity;
import com.task.ui.component.ScooterLocation.ScooterLocatorActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.task.utils.Constants.SCOOTERS_KEY;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public class HomeActivity extends BaseActivity implements HomeView {
    @Inject
    HomePresenter presenter;
    @Bind(R.id.rv_products_list)
    RecyclerView rvScooters;
    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    @Bind(R.id.tv_no_data)
    TextView tvNoData;

    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(HomeActivity.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_activity;
    }

    @Override
    public void initializeScootersList(List<Scooter> scooters) {
        ScootersAdapter scootersAdapter = new ScootersAdapter(presenter.getRecyclerItemListener(), scooters);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvScooters.setLayoutManager(layoutManager);
        rvScooters.setHasFixedSize(true);
        rvScooters.setAdapter(scootersAdapter);
    }

    @Override
    public void setLoaderVisibility(boolean isVisible) {
        pbLoading.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void navigateToScooterLocator(ArrayList<Scooter> scooters) {
        Intent intent = new Intent(this, ScooterLocatorActivity.class);
        intent.putParcelableArrayListExtra(SCOOTERS_KEY, scooters);
        startActivity(intent);
    }

    @Override
    public void setNoDataVisibility(boolean isVisible) {
        tvNoData.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void setListVisibility(boolean isVisible) {
        rvScooters.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @OnClick({R.id.ic_toolbar_map})
    public void onClick() {
        presenter.onMapClick();
    }
}