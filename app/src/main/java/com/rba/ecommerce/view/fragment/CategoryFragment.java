package com.rba.ecommerce.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rba.ecommerce.R;
import com.rba.ecommerce.model.response.CategoryResponse;
import com.rba.ecommerce.util.api.EcommerceApiManager;
import com.rba.ecommerce.util.http.ConnectionDetector;
import com.rba.ecommerce.view.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ricardo Bravo on 29/05/16.
 */

public class CategoryFragment extends Fragment implements View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    private List<CategoryResponse.DataEntity> categoryEntityList;
    private TextView lblText;
    private AppCompatButton btnRetry;
    private LinearLayout linError;
    private SwipeRefreshLayout swpRefresh;

    public CategoryFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        lblText = (TextView) view.findViewById(R.id.lblText);
        linError = (LinearLayout) view.findViewById(R.id.linError);
        btnRetry = (AppCompatButton) view.findViewById(R.id.btnRetry);
        rcvCategory = (RecyclerView) view.findViewById(R.id.rcvCategory);
        swpRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swpRefresh);
        categoryEntityList = new ArrayList<>();

        swpRefresh.setOnRefreshListener(this);

        swpRefresh.post(new Runnable() {
            @Override
            public void run() {
                swpRefresh.setRefreshing(true);
                getData();
            }
        });
    }

    private void getData(){

        if(categoryEntityList.size()>0){
            categoryEntityList.clear();
        }

        if(ConnectionDetector.isInternet(getActivity())){

            Call<CategoryResponse> call = EcommerceApiManager.apiManager().getAllCategoryProduct();

            call.enqueue(new Callback<CategoryResponse>() {
                @Override
                public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {

                    Log.i("x- code", ""+response.code());

                    if(response.isSuccessful()){
                        Log.i("x- data", call.toString());

                        if(TextUtils.equals(response.body().get_meta().getStatus(), getString(R.string.success))){
                            categoryEntityList = response.body().getData();
                            categoryAdapter = new CategoryAdapter(getActivity(), categoryEntityList);
                            rcvCategory.setLayoutManager(new StaggeredGridLayoutManager(2,
                                    StaggeredGridLayoutManager.VERTICAL));
                            rcvCategory.setItemAnimator(new DefaultItemAnimator());
                            rcvCategory.setAdapter(categoryAdapter);
                        }

                        swpRefresh.setRefreshing(false);

                    }else{
                        showError(true, getString(R.string.error_ocurred));
                    }
                }

                @Override
                public void onFailure(Call<CategoryResponse> call, Throwable t) {
                    Log.i("x-failure", t.getMessage());
                }
            });
        }else{
            showError(true, getString(R.string.verify_connection));
        }
    }

    private void showError(boolean variable, String message){

        if(variable){
            rcvCategory.setVisibility(View.GONE);
            linError.setVisibility(View.VISIBLE);
            lblText.setText(message);
            btnRetry.setOnClickListener(this);
        }else{
            rcvCategory.setVisibility(View.VISIBLE);
            linError.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRetry:
                getData();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        getData();
    }

    @Override
    public void onStop() {
        super.onStop();

        if(swpRefresh.isRefreshing()){
            swpRefresh.setRefreshing(false);
        }

    }
}
