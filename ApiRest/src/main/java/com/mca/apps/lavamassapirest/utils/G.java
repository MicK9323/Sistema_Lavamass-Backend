package com.mca.apps.lavamassapirest.utils;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Miguel Cortegana
 * @version 1.0.0
 */
public class G {

    public static final Map<Integer, String> MESES;
    public static final Map<Integer, String> DIAS;

    static {
        MESES = new HashMap<>();
        MESES.put(1, "ENERO");
        MESES.put(2, "FEBRERO");
        MESES.put(3, "MARZO");
        MESES.put(4, "ABRIL");
        MESES.put(5, "MAYO");
        MESES.put(6, "JUNIO");
        MESES.put(7, "JULIO");
        MESES.put(8, "AGOSTO");
        MESES.put(9, "SEPTIEMBRE");
        MESES.put(10, "OCTUBRE");
        MESES.put(11, "NOVIEMBRE");
        MESES.put(12, "DICIEMBRE");

        DIAS = new HashMap<>();
        DIAS.put(1, "DOMINGO");
        DIAS.put(2, "LUNES");
        DIAS.put(3, "MARTES");
        DIAS.put(4, "MIERCOLES");
        DIAS.put(5, "JUEVES");
        DIAS.put(6, "VIERNES");
        DIAS.put(7, "SABADO");
    }

//    String methods

    public static String buildCodUser(String dni, String lastname) {
        return String.format("X%s%s%s", cutLeft(lastname, 2).toUpperCase(), cutRight(dni, 3), getYear());
    }

    public static String cutLeft(String string, int size) {
        String result;
        result = size > string.length() ? string : string.substring(0, size);
        return result;
    }

    public static String cutRight(String string, int size) {
        String result;
        result = size > string.length() ? string : string.substring(string.length() - size);
        return result;
    }

    public static String upCase(String string) {
        return string.toUpperCase();
    }

    public static String lowCase(String string) {
        return string.toLowerCase();
    }

    public static String capitalize(String string) {
        if (string.length() > 1) {
            String[] array = string.split(" ");
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<array.length;i++){
                sb.append(" ");
                array[i] = upCase(cutLeft(array[i], 1)) + lowCase(cutRight(array[i], array[i].length() - 1));
                sb.append(array[i]);
            }
            return sb.toString().trim();
        } else {
            return upCase(cutLeft(string, 1)) + lowCase(cutRight(string, string.length() - 1));
        }
    }

    //    Integer methods
    public static int ramdom(int limit) {
        Double d = Math.random() * limit;
        return d.intValue();
    }

    public static int toInteger(String number) {
        return Integer.parseInt(number);
    }

    //    Date methods
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static int getDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getDayOfWeek() {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    }

    public static Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Date newDate(){ return new Date(); }

    public static Date newDateByTime(int nowTime, int timeout){
        return new Date(nowTime + timeout);
    }
}
