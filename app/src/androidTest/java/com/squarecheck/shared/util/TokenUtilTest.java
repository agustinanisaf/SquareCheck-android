package com.squarecheck.shared.util;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.login.model.Token;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class TokenUtilTest {

    private Context appContext;
    private TokenUtil tokenUtil;

    @Before
    public void setUp() {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        UtilProvider.initialize(appContext, TokenUtil.class);

        tokenUtil = (TokenUtil) UtilProvider.getUtil(TokenUtil.class);
    }

    @Test
    public void initialize() {
        // Assign
        Token token = new Token("1234", "FCM", 0);

        // Act
        tokenUtil.initialize(token);
        Token sessionData = tokenUtil.getSessionData();

        // Assert
        assertNotNull(sessionData);
        assertEquals(sessionData.getToken(), token.getToken());
        assertEquals(sessionData.getTokenType(), token.getTokenType());
        assertEquals(sessionData.getExpiresIn(), token.getExpiresIn());
    }

    @Test
    public void destroy() {
        // Assign

        // Act
        tokenUtil.destroy();
        Token sessionData = tokenUtil.getSessionData();

        // Assert
        assertNull(sessionData);
    }

    @Test
    public void update() {
        // Assign
        Token token = new Token("1234", "FCM", 0);

        // Act
        tokenUtil.initialize(token);
        Token newToken = new Token("5678", "FCM", 0);
        tokenUtil.update(newToken);
        Token sessionData = tokenUtil.getSessionData();

        // Assert
        assertNotNull(sessionData);
        assertNotEquals(sessionData.getToken(), token.getToken());
        assertEquals(sessionData.getToken(), newToken.getToken());
    }

    @Test
    public void create() {
        // Assign
        TokenUtil util = new TokenUtil();

        // Act
        TokenUtil tokenUtil = (TokenUtil) util.create(appContext);

        // Assert
        assertNotNull(tokenUtil);
    }
}