package com.udacity.gradle.builditbigger;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

/**
 * Created by Mohamed Ibrahim on 3/19/2017.
 **/

@RunWith(AndroidJUnit4.class)
@SmallTest
public class AsyncTest extends AndroidTestCase {

    @Test
    public void verifyResponse() throws ExecutionException, InterruptedException {
        JokeAsyncTask endpointsAsyncTask = new JokeAsyncTask();
        endpointsAsyncTask.execute();
        String result = endpointsAsyncTask.get();
        assertNotSame(result, "");
    }
}
