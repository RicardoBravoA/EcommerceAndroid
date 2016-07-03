package com.rba.ecommerce.util.api;

import com.rba.ecommerce.model.response.BrandResponse;
import com.rba.ecommerce.model.response.ProductBrandResponse;
import com.rba.ecommerce.util.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ricardo Bravo on 2/07/16.
 */

public interface EcommerceApi {

    @GET(Constant.URL_BRAND_ALL)
    Call<BrandResponse> getAllBrand();

    @GET(Constant.URL_PRODUCT_BRAND)
    Call<ProductBrandResponse> getProductByBrand(@Path("id") String id);

}
