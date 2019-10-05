package com.example.apicollaboration.ArraySavingClass;

import com.skt.Tmap.TMapPoint;

import java.util.ArrayList;

public class array_saving_class {

    public static ArrayList<TMapPoint> alTMapPoint = new ArrayList<>();
    public static ArrayList<String> nameOfIt = new ArrayList<>();
    public static ArrayList<String> addressOfIt = new ArrayList<>();
    public static ArrayList<String> final_location = new ArrayList<>();
    public static ArrayList<TMapPoint> final_Point = new ArrayList<>();

    public static String POIName[] = new String[100];
    public static TMapPoint recent_point = new TMapPoint(0, 0);
    public static TMapPoint center_point = new TMapPoint(0, 0);
    public static double tempX;
    public static double tempY;
    public static boolean centerOfIt = false;

}
