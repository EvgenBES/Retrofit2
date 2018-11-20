package com.example.fox.myapplication.retfofitTutByXml;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ControllerTut implements Callback<RSSFeedTut> {

    static final String BASE_URL = "https://news.tut.by/rss/";

    public void start() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        TutByAPI tutByAPI = retrofit.create(TutByAPI.class);

        Call<RSSFeedTut> call = tutByAPI.loadRSSFeedTut();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<RSSFeedTut> call, Response<RSSFeedTut> response) {
        if (response.isSuccessful()) {
            RSSFeedTut rss = response.body();
            System.out.println("Титул канала: " + rss.getChannelTitle());
            rss.getArticlesList().forEach(
                    articles -> System.out.println("Титул: " + articles.getTitle() + " Линк: " + articles.getLink() + "\nDescription: " + articles.getDescription())
            );
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<RSSFeedTut> call, Throwable t) {
        t.printStackTrace();
    }
}
