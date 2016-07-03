package com.rba.ecommerce.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rba.ecommerce.R;
import com.rba.ecommerce.model.response.BrandResponse;
import com.rba.ecommerce.util.api.EcommerceApiManager;
import com.rba.ecommerce.util.http.ConnectionDetector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ricardo Bravo on 29/05/16.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView lblText;
    private AppCompatButton btnRetry;
    private LinearLayout linGeneral, linError;
    private BrandResponse brandResponse;

    public HomeFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        linGeneral = (LinearLayout) view.findViewById(R.id.linGeneral);
        linError = (LinearLayout) view.findViewById(R.id.linError);
        lblText = (TextView) view.findViewById(R.id.lblText);
        btnRetry = (AppCompatButton) view.findViewById(R.id.btnRetry);

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        getBrand();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void setupViewPager() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());

        for(BrandResponse.DataEntity dataEntity : brandResponse.getData()){
            adapter.addFragment(new TabFragment().newInstance(dataEntity.getBrand_id()),
                    dataEntity.getDescription());
        }

        viewPager.setOffscreenPageLimit(brandResponse.getData().size());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        showError(false, null);

    }

    private void getBrand(){
        if(ConnectionDetector.isInternet(getActivity())){

            Call<BrandResponse> call = EcommerceApiManager.apiManager().getAllBrand();

            call.enqueue(new Callback<BrandResponse>() {
                @Override
                public void onResponse(Call<BrandResponse> call, Response<BrandResponse> response) {

                    Log.i("x- code", ""+response.code());

                    if(response.isSuccessful()){
                        Log.i("x- data", call.toString());

                        brandResponse = response.body();

                        if(TextUtils.equals(brandResponse.get_meta().getStatus(), getString(R.string.success))){
                            setupViewPager();
                        }

                    }else{
                        showError(true, getString(R.string.error_ocurred));
                    }
                }

                @Override
                public void onFailure(Call<BrandResponse> call, Throwable t) {
                    Log.i("x-failure", t.getMessage());
                }
            });
        }else{
            showError(true, getString(R.string.verify_connection));
        }
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void showError(boolean variable, String message){

        if(variable){
            linGeneral.setVisibility(View.GONE);
            linError.setVisibility(View.VISIBLE);
            lblText.setText(message);
            btnRetry.setOnClickListener(this);
        }else{
            linGeneral.setVisibility(View.VISIBLE);
            linError.setVisibility(View.GONE);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRetry:
                getBrand();
                break;
            default:
                break;
        }
    }
}
