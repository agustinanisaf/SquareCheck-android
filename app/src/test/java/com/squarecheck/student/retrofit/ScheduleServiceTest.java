package com.squarecheck.student.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squarecheck.MockResponseFileReader;
import com.squarecheck.shared.model.APIResponse;
import com.squarecheck.shared.model.APIResponseCollection;
import com.squarecheck.shared.retrofit.JWTAuthenticationInterceptor;
import com.squarecheck.student.model.PresenceModel;
import com.squarecheck.student.model.ScheduleModel;

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

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ScheduleServiceTest {

    private MockResponseFileReader fileReader = new MockResponseFileReader();

    private MockWebServer server = new MockWebServer();

    private ScheduleService scheduleService;

    private int scheduleId = 49;

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

        scheduleService = new Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient.Builder().addInterceptor(new JWTAuthenticationInterceptor(authToken)).build())
                .build()
                .create(ScheduleService.class);
    }

    @AfterEach
    void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    void getSummary() throws IOException {
        // Assign
        String json = fileReader.readJson("schedule/schedule_summary_response.json");
        assertNotNull(json);
        MockResponse response = new MockResponse()
                .setResponseCode(HTTP_OK)
                .setBody(json);
        server.enqueue(response);

        Call<APIResponseCollection<List<ScheduleModel>>> call = scheduleService.getSummary();

        // Act
        Response<APIResponseCollection<List<ScheduleModel>>> execute = call.execute();

        // Assert
        assertEquals(response.toString().contains("200"), String.valueOf(execute.code()).contains("200"));
        assert execute.body() != null;
        assertNotNull(execute.body().getData().get(0).getTime());
    }

    @Test
    void getSchedules() throws IOException {
        // Assign
        String json = fileReader.readJson("schedule/schedules_response.json");
        assertNotNull(json);
        MockResponse response = new MockResponse()
                .setResponseCode(HTTP_OK)
                .setBody(json);
        server.enqueue(response);

        Call<APIResponseCollection<List<ScheduleModel>>> call = scheduleService.getSchedules();

        // Act
        Response<APIResponseCollection<List<ScheduleModel>>> execute = call.execute();

        // Assert
        assertEquals(response.toString().contains("200"), String.valueOf(execute.code()).contains("200"));
        assert execute.body() != null;
        assertNotNull(execute.body().getData().get(0).getTime());
    }

    @Test
    void getSchedule() throws IOException {
        // Assign
        String json = fileReader.readJson("schedule/schedule_response.json");
        assertNotNull(json);
        MockResponse response = new MockResponse()
                .setResponseCode(HTTP_OK)
                .setBody(json);
        server.enqueue(response);

        Call<APIResponseCollection<ScheduleModel>> call = scheduleService.getSchedule(1);

        // Act
        Response<APIResponseCollection<ScheduleModel>> execute = call.execute();

        // Assert
        assertEquals(response.toString().contains("200"), String.valueOf(execute.code()).contains("200"));
        assert execute.body() != null;
        assertNotNull(execute.body().getData().getTime());
    }

    @Test
    void getAttendances() throws IOException {
        // Assign
        String json = fileReader.readJson("schedule/schedule_attendances_response.json");
        assertNotNull(json);
        MockResponse response = new MockResponse()
                .setResponseCode(HTTP_OK)
                .setBody(json);
        server.enqueue(response);

        Call<APIResponseCollection<List<PresenceModel>>> call = scheduleService.getAttendances(scheduleId);

        // Act
        Response<APIResponseCollection<List<PresenceModel>>> execute = call.execute();

        // Assert
        assertEquals(response.toString().contains("200"), String.valueOf(execute.code()).contains("200"));
        assert execute.body() != null;
        assertNotNull(execute.body().getData().get(0).getStatus());
    }

    @Test
    void open() throws IOException {
        // Assign
        String json = fileReader.readJson("schedule/schedule_open_response.json");
        assertNotNull(json);
        MockResponse response = new MockResponse()
                .setResponseCode(HTTP_OK)
                .setBody(json);
        server.enqueue(response);

        Call<APIResponseCollection<ScheduleModel>> call = scheduleService.open(scheduleId);

        // Act
        Response<APIResponseCollection<ScheduleModel>> execute = call.execute();

        // Assert
        assertEquals(response.toString().contains("200"), String.valueOf(execute.code()).contains("200"));
        assert execute.body() != null;
        assertNotNull(execute.body().getData().getTime());
    }

    @Test
    void close() throws IOException {
        // Assign
        String json = fileReader.readJson("schedule/schedule_close_response.json");
        assertNotNull(json);
        MockResponse response = new MockResponse()
                .setResponseCode(HTTP_CREATED)
                .setBody(json);
        server.enqueue(response);

        Call<APIResponseCollection<ScheduleModel>> call = scheduleService.close(scheduleId);

        // Act
        Response<APIResponseCollection<ScheduleModel>> execute = call.execute();

        // Assert
        assertEquals(HTTP_CREATED, execute.code());
        assert execute.body() != null;
        assertNotNull(execute.body().getData().getTime());
    }

    @Test
    void remove() throws IOException {
        // Assign
        String json = fileReader.readJson("schedule/schedule_remove_response.json");
        assertNotNull(json);
        MockResponse response = new MockResponse()
                .setResponseCode(HTTP_OK)
                .setBody(json);
        server.enqueue(response);

        Call<APIResponse> call = scheduleService.remove(scheduleId);

        // Act
        Response<APIResponse> execute = call.execute();

        // Assert
        assertEquals(response.toString().contains("200"), String.valueOf(execute.code()).contains("200"));
        assert execute.body() != null;
        assertNotNull(execute.body().getDescription());
    }

    @Test
    void attend() throws IOException {
        // Assign
        String json = fileReader.readJson("schedule/schedule_attend_response.json");
        assertNotNull(json);
        MockResponse response = new MockResponse()
                .setResponseCode(HTTP_OK)
                .setBody(json);
        server.enqueue(response);

        Call<APIResponseCollection<PresenceModel>> call = scheduleService.attend(scheduleId);

        // Act
        Response<APIResponseCollection<PresenceModel>> execute = call.execute();

        // Assert
        assertEquals(response.toString().contains("200"), String.valueOf(execute.code()).contains("200"));
        assert execute.body() != null;
        assertNotNull(execute.body().getData().getStatus());
    }
}