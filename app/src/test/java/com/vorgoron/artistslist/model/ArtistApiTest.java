package com.vorgoron.artistslist.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vorgoron.artistslist.BaseTest;
import com.vorgoron.artistslist.TestConstants;
import com.vorgoron.artistslist.TestUtils;
import com.vorgoron.artistslist.model.api.ArtistApi;
import com.vorgoron.artistslist.model.api.response.Artist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class ArtistApiTest extends BaseTest {

    private Observable<Artist> artistObservable;

    @Before
    public void setUp() throws IOException {
        getTestComponent().inject(ArtistApiTest.this);

        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockWebServer.setDispatcher(new Dispatcher() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                return new MockResponse()
                        .setResponseCode(200)
                        .setBody(TestUtils.readString(TestConstants.ARTISTS_LIST));
            }
        });
        HttpUrl baseUrl = mockWebServer.url("/");

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl)
                .client(new OkHttpClient())
                .build();

        ArtistApi artistApi = retrofit.create(ArtistApi.class);

        artistObservable = artistApi.getArtists().flatMap(Observable::from);
    }

    @Test
    public void testNames() {
        Observable.zip(
                artistObservable.map(Artist::getName),
                Observable.just("Led Zeppelin", "Кипелов", "Король и Шут"),
                String::equals
        ).subscribe(Assert::assertTrue);
    }

    @Test
    public void testTracks() {
        Observable.zip(
                artistObservable.map(Artist::getTracks),
                Observable.just(316, 97, 59),
                Integer::equals
        ).subscribe(Assert::assertTrue);
    }
}
