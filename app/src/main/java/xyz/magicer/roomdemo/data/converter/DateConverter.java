package xyz.magicer.roomdemo.data.converter;

import androidx.room.TypeConverter;

import java.util.Date;


//官方Simple中的Converter例子
public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
