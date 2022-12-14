package com.example.petmed;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {

    @GET("product/")
    Call<List<Product>> getProducts();

    @POST("product/add/")
    Call<ResponseBody> addProduct(@Body Product p);

    @POST("product/update/{id}")
    Call<ResponseBody> updateProduct(@Path("id") long productId, @Body Product p);
}
