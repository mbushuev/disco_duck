package com.example.maks.discoduck.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.maks.discoduck.R;
import com.example.maks.discoduck.adapters.CatalogAdapter;
import com.example.maks.discoduck.models.CatalogItem;
import com.example.maks.discoduck.utils.DataListener;
import com.example.maks.discoduck.utils.DataManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatalogFragment extends BaseFragment {
    public static final String TAG = "com.example.maks.discoduck.fragments.CatalogFragment";

    @BindView(R.id.fmt_catalog_rv_catalog)
    RecyclerView recyclerView;

    @BindView(R.id.fmt_catalog_pg_duck)
    ProgressBar progressBar;

    @BindView(R.id.fmt_catalog_ll_error_container)
    LinearLayout errorLinearLayout;

    @BindView(R.id.fmt_catalog_srl_swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    private CatalogAdapter catalogAdapter;
    private DataListener<List<CatalogItem>> dataListener = new DataListener<List<CatalogItem>>() {
        @Override
        public void onSuccess(List<CatalogItem> data) {
            stopSwipeRefresh();
            setData(data);
            setVisibleContent(true);
        }

        @Override
        public void onError() {
            Toast.makeText(getActivity(), getString(R.string.error_load_data), Toast.LENGTH_SHORT).show();
            stopSwipeRefresh();
            setVisibleContent(true);
            showError(null != errorLinearLayout && !DataManager.INSTANCE.isDataLoaded());
        }
    };

    private void showError(boolean isVisible) {
        errorLinearLayout.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    private void stopSwipeRefresh() {
        if (null != swipeRefreshLayout && swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fmt_catalog, container, false);

        if (null != root) {
            ButterKnife.bind(this, root);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(new CatalogAdapter(getContext()));
            swipeRefreshLayout.setOnRefreshListener(() -> DataManager.INSTANCE.requestNewDataFromServer(dataListener));

            updateUI();
        }

        return root;
    }

    private void updateUI() {
        showError(false);
        showLoader(true);
        DataManager.INSTANCE.getData(dataListener);
    }

    private void showLoader(boolean isVisible) {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    private void setData(List<CatalogItem> data) {
        if (null == catalogAdapter) {
            catalogAdapter = new CatalogAdapter(data, getContext());
            recyclerView.setAdapter(catalogAdapter);
        }
        catalogAdapter.notifyDataSetChanged();
    }

    private void setVisibleContent(boolean visibleContent) {
        showLoader(!visibleContent);
        showError(false);
        swipeRefreshLayout.setVisibility(visibleContent ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(visibleContent ? View.VISIBLE : View.GONE);
    }
}
