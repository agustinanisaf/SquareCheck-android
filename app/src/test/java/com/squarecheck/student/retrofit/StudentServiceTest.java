package com.squarecheck.student.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squarecheck.MockResponseFileReader;
import com.squarecheck.shared.api_response.APIResponseCollection;
import com.squarecheck.shared.retrofit.JWTAuthenticationInterceptor;
import com.squarecheck.student.model.StudentModel;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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

class StudentServiceTest {

    private MockResponseFileReader fileReader = new MockResponseFileReader();

    private MockWebServer server = new MockWebServer();

    private StudentService studentService;

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

        studentService = new Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient.Builder().addInterceptor(new JWTAuthenticationInterceptor(authToken)).build())
                .build()
                .create(StudentService.class);
    }

    @AfterEach
    void tearDown() throws IOException {
        server.shutdown();
    }

    @Test
    void getStudent() throws IOException {
        // Assign
        String json = fileReader.readJson("student/student_response.json");
        assertNotNull(json);
        MockResponse response = new MockResponse()
                .setResponseCode(HTTP_OK)
                .setBody(json);
        server.enqueue(response);

        Call<APIResponseCollection<StudentModel>> call = studentService.getStudent();

        // Act
        Response<APIResponseCollection<StudentModel>> execute = call.execute();

        // Assert
        assertEquals(response.toString().contains("200"), String.valueOf(execute.code()).contains("200"));
        assert execute.body() != null;
        assertNotNull(execute.body().getData().getDepartment());
        assertNotNull(execute.body().getData().getClassroom());
        assertNotNull(execute.body().getData().getUser());
    }
}