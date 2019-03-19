package com.example.deanery;

import com.example.deanery.dataModels.auditory.GetAllAuditories;
import com.example.deanery.dataModels.department.Department;
import com.example.deanery.dataModels.login.AuthTokenFromLogin;
import com.example.deanery.dataModels.login.BodyForLogin;
import com.example.deanery.dataModels.department.GetAllDepartments;
import com.example.deanery.dataModels.lecturer.GetAllLecturers;
import com.example.deanery.dataModels.lecturer.GetLecturer;
import com.example.deanery.dataModels.lecturer.GetStatus;
import com.example.deanery.dataModels.lecturer.Lecturer;
import com.example.deanery.dataModels.login.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DeaneryAPI {

    @POST("auth/login")
    Call<AuthTokenFromLogin> loginPost(@Body BodyForLogin bodyForLogin);

    @GET("auth/me")
    Call<User> getUser(@Query("token") String token);

    @POST("lecturers")
    Call<Lecturer> createLecturer(@Query("token") String token, @Body Lecturer lecturer);

    @POST("lecturers/{id}")
    Call<Lecturer> updateLecturer(@Path("id") Integer id , @Query("token") String token, @Body Lecturer lecturer);

    @DELETE("lecturers/{id}")
    Call<GetStatus> deleteLecturer(@Path("id") Integer id , @Query("token") String token);

    @GET("lecturers")
    Call<GetAllLecturers> getAllLecturers(@Query("token") String token);

    @GET("lecturers/{id}")
    Call<GetLecturer> getLecturer(@Path("id") Integer id , @Query("token") String token);

    @POST("departments")
    Call<Department> createDepartment(@Query("token") String token, @Body Department department);

    @POST("departments/{id}")
    Call<Department> updateDepartment(@Path("id") Integer id , @Query("token") String token, @Body Department department);

    @GET("departments")
    Call<GetAllDepartments> getAllDepartments(@Query("token") String token);

    @GET("auditories")
    Call<GetAllAuditories> getAllAuditories(@Query("token") String token);
}
