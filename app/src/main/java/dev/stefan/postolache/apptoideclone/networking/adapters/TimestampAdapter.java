package dev.stefan.postolache.apptoideclone.networking.adapters;

import com.squareup.moshi.FromJson;

import java.sql.Timestamp;

public class TimestampAdapter {

    @FromJson
    Timestamp fromJson(String timestamp) {
        return Timestamp.valueOf(timestamp);
    }
}
