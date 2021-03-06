package com.brothers.admin.kill_some_time;

import com.brothers.admin.kill_some_time.data.HaiVlApiImpl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private static final String TAG = "ExampleUnitTest";
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void testHaiVl() throws Exception {
        HaiVlApiImpl haiVl = HaiVlApiImpl.get();
        haiVl.getSource().size();
        assertEquals(15, haiVl.getSource().size());
    }

}