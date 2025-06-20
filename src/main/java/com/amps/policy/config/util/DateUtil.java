package com.amps.policy.config.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DateUtil {

    public Date getFormattedDate(String dateString) {
        List<SimpleDateFormat> knownPatterns = new ArrayList<SimpleDateFormat>();
        knownPatterns.add(new SimpleDateFormat("MM-dd-yyyy"));
        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd"));
        knownPatterns.add(new SimpleDateFormat("MM/dd/yyyy"));
        knownPatterns.add(new SimpleDateFormat("yyyy/MM/dd"));
        knownPatterns.add(new SimpleDateFormat("dd-MM-yyyy"));
        knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
        for (SimpleDateFormat pattern : knownPatterns) {
            try {
            	pattern.setLenient(false);
                return pattern.parse(dateString);
            } catch (ParseException pe) {
            }
        }
        return null;
    }
}
