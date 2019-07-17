package com.example.mydrugpal;

import android.util.Log;

import com.google.firebase.Timestamp;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class LogIntakeNotificationReceiverUnitTests {

    /**
     * tests that the Timstamp check returns true
     * Method returns true if the input time is less than the current time
     * @author Richard Purcell, Ian Sifton
     */
    @Test
    public void lastEntry_greaterThanTwelve()
    {
        // June 10, 11:59am
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.DAY_OF_MONTH, 10);
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.HOUR_OF_DAY,11);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);

        Date date = cal.getTime();
        boolean check = hasIntakeToday(new Timestamp(date));

        assertTrue(check);
    }
    
    private boolean hasIntakeToday(Timestamp lastEntry) {
        boolean intakeEntryRequired = false;
        Date date = new Date();
        Timestamp todayTime = new Timestamp(date);
        Timestamp latestEntryDate = lastEntry;

        long diffInSeconds = todayTime.getSeconds() - latestEntryDate.getSeconds();

        long twelveHoursInSeconds = 60 * 60 * 12;
        if (diffInSeconds > twelveHoursInSeconds) {
            intakeEntryRequired = true;
        }

        return intakeEntryRequired;
    }
}