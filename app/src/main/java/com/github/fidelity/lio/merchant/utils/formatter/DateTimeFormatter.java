package com.github.fidelity.lio.merchant.utils.formatter;

import android.text.format.DateFormat;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.inject.Inject;

public class DateTimeFormatter implements Formatter<String, String> {

    @Inject
    public DateTimeFormatter() { /**/ }

    @Override
    public String format(String input) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        try {
            if (input != null) {
                Date date = sdf.parse(input);

                return DateFormat.format("dd/MM/yy, HH:mm", date).toString();
            }
        } catch (ParseException e) {
            Log.e("DateFormat", e.getMessage(), e);
        }

        return "--";
    }
}