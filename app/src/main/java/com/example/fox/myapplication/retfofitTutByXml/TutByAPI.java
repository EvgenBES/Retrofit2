package com.example.fox.myapplication.retfofitTutByXml;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TutByAPI {

    @GET("afisha.rss")
    Call<RSSFeedTut> loadRSSFeedTut();
}
