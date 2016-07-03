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
import com.rba.ecommerce.model.response.ProductBrandResponse;
import com.rba.ecommerce.util.api.EcommerceApiManager;
import com.rba.ecommerce.util.http.ConnectionDetector;
import com.rba.ecommerce.view.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ricardo Bravo on 02/07/16.
 */

public class TabFragment extends Fragment implements View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener {

    private static final String CODE = "code";
    private RecyclerView rcvProduct;
    private ProductAdapter productAdapter;
    private List<ProductBrandResponse.DataEntity> productEntityList;
    private TextView lblText;
    private AppCompatButton btnRetry;
    private LinearLayout linError;
    private SwipeRefreshLayout swpRefresh;

    public TabFragment() {
    }

    public TabFragment newInstance(int  code){
        TabFragment tabFragment = new TabFragment();
        Bundle args = new Bundle();
        args.putInt(CODE, code);
        tabFragment.setArguments(args);
        return tabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        lblText = (TextView) view.findViewById(R.id.lblText);
        linError = (LinearLayout) view.findViewById(R.id.linError);
        btnRetry = (AppCompatButton) view.findViewById(R.id.btnRetry);
        rcvProduct = (RecyclerView) view.findViewById(R.id.rcvProduct);
        swpRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swpRefresh);
        productEntityList = new ArrayList<>();

        swpRefresh.setOnRefreshListener(this);

        swpRefresh.post(new Runnable() {
            @Override
            public void run() {
                swpRefresh.setRefreshing(true);
                getProduct();
            }
        });

    }

    private void getProduct(){

        if(productEntityList.size()>0){
            productEntityList.clear();
        }

        if(ConnectionDetector.isInternet(getActivity())){

            Call<ProductBrandResponse> call = EcommerceApiManager.apiManager()
                    .getProductByBrand(String.valueOf(getArguments().getInt(CODE)));

            call.enqueue(new Callback<ProductBrandResponse>() {
                @Override
                public void onResponse(Call<ProductBrandResponse> call,
                                       Response<ProductBrandResponse> response) {

                    Log.i("x- code", ""+response.code());

                    if(response.isSuccessful()){
                        Log.i("x- data", call.toString());

                        if(TextUtils.equals(response.body().get_meta().getStatus(), getString(R.string.success))){
                            productEntityList = response.body().getData();
                            productAdapter = new ProductAdapter(getActivity(), productEntityList);
                            rcvProduct.setLayoutManager(new StaggeredGridLayoutManager(2,
                                    StaggeredGridLayoutManager.VERTICAL));
                            rcvProduct.setItemAnimator(new DefaultItemAnimator());
                            rcvProduct.setAdapter(productAdapter);
                        }

                        swpRefresh.setRefreshing(false);

                    }else{
                        showError(true, getString(R.string.error_ocurred));
                    }
                }

                @Override
                public void onFailure(Call<ProductBrandResponse> call, Throwable t) {
                    Log.i("x-failure", t.getMessage());
                }
            });
        }else{
            showError(true, getString(R.string.verify_connection));
        }
    }

    private void showError(boolean variable, String message){

        if(variable){
            rcvProduct.setVisibility(View.GONE);
            linError.setVisibility(View.VISIBLE);
            lblText.setText(message);
            btnRetry.setOnClickListener(this);
        }else{
            rcvProduct.setVisibility(View.VISIBLE);
            linError.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRetry:
                getProduct();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRefresh() {
        getProduct();
    }

    @Override
    public void onStop() {
        super.onStop();

        if(swpRefresh.isRefreshing()){
            swpRefresh.setRefreshing(false);
        }

    }

}
