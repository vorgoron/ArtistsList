package com.vorgoron.artistslist;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vorgoron.artistslist.model.api.response.Artist;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static List<Artist> getTestArtistList() throws IOException {
        String json = readString(TestConstants.ARTISTS_LIST);
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation();
        Gson gson = gsonBuilder.create();
        return Arrays.asList(gson.getAdapter(Artist[].class).fromJson(json));
    }

    public static String readString(String fileName) {
        InputStream stream = TestUtils.class.getClassLoader().getResourceAsStream(fileName);
        try {
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            return new String(buffer, "utf8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
