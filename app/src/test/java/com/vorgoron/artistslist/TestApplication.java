package com.vorgoron.artistslist;

import com.vorgoron.artistslist.di.component.ApplicationComponent;
import com.vorgoron.artistslist.di.component.DaggerTestApplicationComponent;
import com.vorgoron.artistslist.di.module.TestApplicationModule;
import com.vorgoron.artistslist.di.module.TestNetModule;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;


/**
 * Приложение для тестов.
 */
public class TestApplication extends ArtistsApplication {

    @Override
    public ApplicationComponent initComponent() {
        DaggerTestApplicationComponent.Builder builder = DaggerTestApplicationComponent.builder()
                .testApplicationModule(new TestApplicationModule(TestApplication.this));

        try {
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
            builder.testNetModule(new TestNetModule(baseUrl.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder
                .build();
    }
}
