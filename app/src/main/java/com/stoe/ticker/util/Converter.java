package com.stoe.ticker.util;


import androidx.room.TypeConverter;

import com.stoe.ticker.model.Priority;

import java.util.Date;

//ca sa convertim tipul de date introdus de noi pt taskuri in format inteles de room (cum ar fi priority si date)
public class Converter {

    @TypeConverter
    public static Date fromTimestamp(Long value){
        return value == null ? null : new Date(value);
        //if value e null, atunci return null, else create new date(value)
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date){
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String fromPriority(Priority priority){
        return priority == null ? null : priority.name();
    }

    @TypeConverter
    public static Priority toPriority(String priority){
        return priority == null ? null : Priority.valueOf(priority);
    }
}
