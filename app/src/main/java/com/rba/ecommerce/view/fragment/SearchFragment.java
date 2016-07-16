package com.rba.ecommerce.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rba.ecommerce.R;
import com.rba.ecommerce.model.response.FilterResponse;
import com.rba.ecommerce.util.api.EcommerceApiManager;
import com.rba.ecommerce.util.control.menutag.TagGroup;
import com.rba.ecommerce.util.control.menutag.listener.TagListener;
import com.rba.ecommerce.util.http.ConnectionDetector;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ricardo Bravo on 29/05/16.
 */

public class SearchFragment extends Fragment implements View.OnClickListener, TagListener {

    private TagGroup tagGroupBrand = null, tagGroupCategory = null;
    private TextView lblText;
    private AppCompatButton btnRetry;
    private LinearLayout linError, linSearch;
    private FilterResponse filterResponseList;
    private List<FilterResponse.DataEntity> dataEntityList;

    public SearchFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tagGroupBrand = (TagGroup) view.findViewById(R.id.tagGroupBrand);
        tagGroupCategory = (TagGroup) view.findViewById(R.id.tagGroupCategory);
        lblText = (TextView) view.findViewById(R.id.lblText);
        linError = (LinearLayout) view.findViewById(R.id.linError);
        linSearch = (LinearLayout) view.findViewById(R.id.linSearch);
        btnRetry = (AppCompatButton) view.findViewById(R.id.btnRetry);

        tagGroupCategory.setTagListener(this);
        tagGroupBrand.setTagListener(this);
        btnRetry.setOnClickListener(this);

        getData();
    }

    private void getData(){

        if(ConnectionDetector.isInternet(getActivity())){
            Call<FilterResponse> call = EcommerceApiManager.apiManager().getAllFilter();
            call.enqueue(new Callback<FilterResponse>() {
                @Override
                public void onResponse(Call<FilterResponse> call, Response<FilterResponse> response) {
                    if(response.isSuccessful()){

                        dataEntityList = response.body().getData();
                        filterResponseList = response.body();

                        Log.i("x- brand", new Gson().toJson(filterResponseList));

                        tagGroupBrand.addTags(filterResponseList.getBrand());
                        tagGroupCategory.addTags(filterResponseList.getCategory());

                    }else{
                        showError(true, getString(R.string.error_ocurred));
                    }
                }

                @Override
                public void onFailure(Call<FilterResponse> call, Throwable t) {
                    Log.i("x-failure", t.getMessage());
                }
            });
        }else{
            showError(true, getString(R.string.verify_connection));
        }

    }

    private void showError(boolean variable, String message){

        if(variable){
            linSearch.setVisibility(View.GONE);
            linError.setVisibility(View.VISIBLE);
            lblText.setText(message);
            btnRetry.setOnClickListener(this);
        }else{
            linSearch.setVisibility(View.VISIBLE);
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
    public void onTagDeselected(View view, int index) {

        switch (view.getId()){
            case R.id.tagGroupBrand:
                tagGroupCategory.removeAllViews();
                tagGroupCategory.addTags(filterResponseList.getCategory());
                break;
            case R.id.tagGroupCategory:
                break;
            default:
                break;
        }

    }

    @Override
    public void onTagSelected(View view, int index) {

        switch (view.getId()){
            case R.id.tagGroupBrand:
                tagGroupCategory.removeAllViews();
                Log.i("x- category", new Gson().toJson(dataEntityList.get(index).getCategory()));
                tagGroupCategory.addTags(dataEntityList.get(index).getCategory());
                break;
            case R.id.tagGroupCategory:
                break;
            default:
                break;
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
