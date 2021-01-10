package com.squarecheck.shared.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateUtilTest {

    String now = "2020-11-22 09:00:00";
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void getDay() throws ParseException {
        // Assign
        Date currentDate = df.parse(now);

        // Act
        String day = DateUtil.getDay(currentDate);

        // Assert
        assert day != null;
        assertEquals("Minggu", day);
    }

    @Test
    public void getNullDay() {
        // Assign

        // Act
        String day = DateUtil.getDay(null);

        // Assert
        assert day == null;
    }

    @Test
    public void getDate() throws ParseException {
        // Assign
        Date currentDate = df.parse(now);

        // Act
        String date = DateUtil.getDate(currentDate);

        // Assert
        assert date != null;
        assertEquals("22 November 2020", date);
    }

    @Test
    public void getNullDate() {
        // Assign

        // Act
        String date = DateUtil.getDate(null);

        // Assert
        assert date == null;
    }

    @Test
    public void getFullDate() throws ParseException {
        // Assign
        Date currentDate = df.parse(now);

        // Act
        String fullDate = DateUtil.getFullDate(currentDate);

        // Assert
        assert fullDate != null;
        assertEquals("Minggu, 22 November 2020", fullDate);
    }

    @Test
    public void getNullFullDate() {
        // Assign

        // Act
        String fullDate = DateUtil.getFullDate(null);

        // Assert
        assert fullDate == null;
    }

    @Test
    public void getTime() throws ParseException {
        // Assign
        Date currentDate = df.parse(now);

        // Act
        String time = DateUtil.getTime(currentDate);

        // Assert
        assertEquals("09:11", time);
    }

    @Test
    public void getNullTime() {
        // Assign

        // Act
        String time = DateUtil.getTime(null);

        // Assert
        assertEquals("-- : --", time);
    }
}