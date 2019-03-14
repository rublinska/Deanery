package com.example.deanery;

import com.example.deanery.dataModels.AuthTokenFromLogin;
import com.example.deanery.dataModels.BodyForLogin;
import com.example.deanery.dataModels.GetLecturer;
import com.example.deanery.dataModels.GetLecturersData;
import com.example.deanery.dataModels.Lecturer;
import com.example.deanery.dataModels.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DeaneryAPI {

    @POST("auth/login")
    Call<AuthTokenFromLogin> loginPost(@Body BodyForLogin bodyForLogin);

    @GET("auth/me")
    Call<User> getUser(@Query("token") String token);

    @GET("lecturers")
    Call<GetLecturersData> getLecturerData(@Query("token") String token);

    @GET("lecturers/1")
    Call<GetLecturer> lecturer(@Query("token") String token/*, @Query("id") Integer id */);
}