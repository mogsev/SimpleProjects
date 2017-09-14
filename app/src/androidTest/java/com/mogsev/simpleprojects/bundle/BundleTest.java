package com.mogsev.simpleprojects.bundle;

import android.content.Context;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.mogsev.simpleprojects.model.User;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Eugene Sikaylo on 08.09.2017.
 */

@RunWith(AndroidJUnit4.class)
public class BundleTest {

    @Test
    @Ignore
    public void useBundleTest() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.mogsev.simpleprojects", appContext.getPackageName());
    }

    @Test
    public void bundleParcelable_Successful() throws Exception {
        System.out.println("bundleParcelable_Successful start");
        Context appContext = InstrumentationRegistry.getTargetContext();

        User user = new User();
        user.setId(1);
        user.setFirstName("Eugene");
        user.setLastName("Sikaylo");

        Bundle bundle = new Bundle();
        bundle.putParcelable("USER", user);

        assertTrue(bundle.containsKey("USER"));

        User newUser = bundle.getParcelable("USER");
        Log.i("BundleTest", "User: " + newUser);

    }

}
