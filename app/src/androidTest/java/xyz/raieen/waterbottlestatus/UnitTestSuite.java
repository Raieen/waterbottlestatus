package xyz.raieen.waterbottlestatus;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class UnitTestSuite {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    MainActivity mainActivity;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityRule.getActivity();
    }

    @Test
    public void testGetStatus() {
        mainActivity.setStatus(true);
        assertTrue(mainActivity.getStatus());

        mainActivity.setStatus(false);
        assertFalse(mainActivity.getStatus());
    }

    @Test
    public void testSetStatus() {
        boolean status = mainActivity.getStatus();
        mainActivity.setStatus(!status);
        assertNotEquals(status, mainActivity.getStatus());
    }

    @Test
    public void testGetRandomString() {
        int hasWater = R.array.has_water;

        String[] array = mainActivity.getResources().getStringArray(hasWater);
        String string = mainActivity.getRandomString(hasWater);

        int noWater = R.array.no_water;
        String[] array1 = mainActivity.getResources().getStringArray(noWater);
        String string1 = mainActivity.getRandomString(noWater);

        assertTrue(Arrays.asList(array).contains(string));
        assertTrue(Arrays.asList(array1).contains(string1));
    }
}
