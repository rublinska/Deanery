package com.example.deanery;

import com.example.deanery.dataModels.auditory.Auditory;
import com.example.deanery.dataModels.common.DeaneryGet;
import com.example.deanery.dataModels.common.DeaneryGetList;
import com.example.deanery.dataModels.department.Department;
import com.example.deanery.dataModels.discipline.Discipline;
import com.example.deanery.dataModels.login.AuthTokenFromLogin;
import com.example.deanery.dataModels.login.BodyForLogin;
import com.example.deanery.dataModels.GetStatus;
import com.example.deanery.dataModels.lecturer.Lecturer;
import com.example.deanery.dataModels.login.User;
import com.example.deanery.dataModels.schedule.ScheduleItem;
import com.example.deanery.dataModels.specialty.Specialty;
import com.example.deanery.dataModels.student.Student;

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
    Call<DeaneryGetList<Lecturer>> getAllLecturers(@Query("token") String token);

    @GET("lecturers/{id}")
    Call<DeaneryGet<Lecturer>> getLecturer(@Path("id") Integer id , @Query("token") String token);


    @POST("departments")
    Call<Department> createDepartment(@Query("token") String token, @Body Department department);

    @POST("departments/{id}")
    Call<Department> updateDepartment(@Path("id") Integer id , @Query("token") String token, @Body Department department);

    @DELETE("departments/{id}")
    Call<GetStatus> deleteDepartment(@Path("id") Integer id , @Query("token") String token);

    @GET("departments")
    Call<DeaneryGetList<Department>> getAllDepartments(@Query("token") String token);


    @GET("auditories")
    Call<DeaneryGetList<Auditory>> getAllAuditories(@Query("token") String token);

    @POST("departments/{id}")
    Call<Auditory> updateAuditory(@Path("id") Integer id , @Query("token") String token, @Body Auditory auditory);


    @POST("students")
    Call<Student> createStudent(@Query("token") String token, @Body Student student);

    @POST("students/{id}")
    Call<Student> updateStudent(@Path("id") Integer id , @Query("token") String token, @Body Student student);

    @DELETE("students/{id}")
    Call<GetStatus> deleteStudent(@Path("id") Integer id , @Query("token") String token);

    @GET("students")
    Call<DeaneryGetList<Student>> getAllStudents(@Query("token") String token);


    @GET("specialties")
    Call<DeaneryGetList<Specialty>> getAllSpecialties(@Query("token") String token);


    @POST("disciplines")
    Call<Discipline> createDiscipline(@Query("token") String token, @Body Discipline discipline);

    @POST("disciplines/{id}")
    Call<Discipline> updateDiscipline(@Path("id") Integer id , @Query("token") String token, @Body Discipline discipline);

    @DELETE("disciplines/{id}")
    Call<GetStatus> deleteDiscipline(@Path("id") Integer id , @Query("token") String token);

    @GET("disciplines")
    Call<DeaneryGetList<Discipline>> getAllDisciplines(@Query("token") String token);

    @GET("university-schedules")
    Call<DeaneryGetList<ScheduleItem>> getAllScheduleItems(@Query("token") String token);

}
