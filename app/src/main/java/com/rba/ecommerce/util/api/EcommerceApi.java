package com.rba.ecommerce.util.api;

import com.rba.ecommerce.model.response.BrandResponse;
import com.rba.ecommerce.util.Constant;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ricardo Bravo on 2/07/16.
 */

public interface EcommerceApi {

    @GET(Constant.URL_BRAND_ALL)
    Call<BrandResponse> getAllBrand();

}
