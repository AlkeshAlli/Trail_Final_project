package com.example.finalprojectgroup8;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Node_Interface {
    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> register(@Field("userid") String userid,
                                @Field("role") String role,
                                @Field("intro") String intro,
                                @Field("email") String email);

    @GET("getuserdata")
    Call<UserDTO> getuserdata(@Query("userid") String userid);
}
