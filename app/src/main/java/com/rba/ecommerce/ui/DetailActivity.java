package com.rba.ecommerce.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.gson.Gson;
import com.rba.ecommerce.R;
import com.rba.ecommerce.model.response.ProductBrandResponse;
import com.rba.ecommerce.util.Constant;

public class DetailActivity extends AppCompatActivity {

    private ProductBrandResponse.DataEntity productEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            productEntity = (ProductBrandResponse.DataEntity) extras.getSerializable(Constant.PROD_OBJ);
        }

        Log.i("x- data", new Gson().toJson(productEntity));


    }

}
