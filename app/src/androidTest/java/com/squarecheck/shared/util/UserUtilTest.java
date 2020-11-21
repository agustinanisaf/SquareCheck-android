package com.squarecheck.shared.util;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.login.model.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class UserUtilTest {

    private Context appContext;
    private UserUtil userUtil;

    @Before
    public void setUp() {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        UtilProvider.initialize(appContext, UserUtil.class);

        userUtil = (UserUtil) UtilProvider.getUtil(UserUtil.class);
    }

    @Test
    public void initialize() {
        // Assign
        User user = new User("Test", "1234");

        // Act
        userUtil.initialize(user);
        User sessionData = userUtil.getSessionData();

        // Assert
        assertNotNull(sessionData);
        assertEquals(sessionData.getEmail(), user.getEmail());
        assertEquals(sessionData.getPassword(), user.getPassword());
    }

    @Test
    public void destroy() {
        // Assign

        // Act
        userUtil.destroy();
        User sessionData = userUtil.getSessionData();

        // Assert
        assertNull(sessionData);
    }

    @Test
    public void update() {
        // Assign
        User user = new User("Test", "1234");

        // Act
        userUtil.initialize(user);
        User newUser = new User("Try", "5678");
        userUtil.update(newUser);
        User sessionData = userUtil.getSessionData();

        // Assert
        assertNotNull(sessionData);
        assertNotEquals(sessionData.getEmail(), user.getEmail());
        assertEquals(sessionData.getEmail(), newUser.getEmail());
        assertNotEquals(sessionData.getPassword(), user.getPassword());
        assertEquals(sessionData.getPassword(), newUser.getPassword());
    }

    @Test
    public void create() {
        // Assign
        UserUtil util = new UserUtil();

        // Act
        UserUtil userUtil = (UserUtil) util.create(appContext);

        // Assert
        assertNotNull(userUtil);
    }
}