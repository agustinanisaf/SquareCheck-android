package com.squarecheck.calendar.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squarecheck.MockResponseFileReader;
import com.squarecheck.calendar.model.CalendarModel;
import com.squarecheck.shared.model.APIResponseCollection;
import com.squarecheck.shared.retrofit.JWTAuthenticationInterceptor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CalendarServiceTest {

    private MockResponseFileReader fileReader = new MockResponseFileReader();

    private MockWebServer server = new MockWebServer();

    private CalendarService calendarService;

    @BeforeEach
    void setUp() throws IOException {
        server.start();

        String authToken = fileReader.readJson("auth_token.txt");

        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .setPrettyPrinting()
                .setVersion(1.0)
                .create();

        calendarService = new Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient.Builder().addInterceptor(new JWTAuthenticationInterceptor(authToken)).build())
                .build()
                .create(CalendarService.class);
    }

    @AfterEach
    void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    void getCalendars() throws IOException {
        // Assign
        String json = fileReader.readJson("calendar/calendar_response.json");
        assertNotNull(json);
        MockResponse response = new MockResponse()
                .setResponseCode(HTTP_OK)
                .setBody(json);
        server.enqueue(response);

        Call<APIResponseCollection<List<CalendarModel>>> call = calendarService.getCalendars();

        // Act
        Response<APIResponseCollection<List<CalendarModel>>> execute = call.execute();

        // Assert
        assertEquals(response.toString().contains("200"), String.valueOf(execute.code()).contains("200"));
        assert execute.body() != null;
        assertNotNull(execute.body().getData().get(0));
    }
}