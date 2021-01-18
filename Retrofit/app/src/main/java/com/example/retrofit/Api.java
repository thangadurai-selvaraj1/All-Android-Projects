package com.example.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
      String BASE_URL = "http://54.202.78.122/moodle/webservice/rest/";
    @FormUrlEncoded
    @POST("server.php?wstoken=9e77a1cf0a53209546916d2428b3e290&wsfunction=core_course_get_contents&moodlewsrestformat=json")
    Call<List<Model>> getName(@Field("courseid") int name);
}
