package com.trinoq.mealmanager.utils;

import com.trinoq.mealmanager.features.model.models.ActiveGroupInformation;
import com.trinoq.mealmanager.features.model.models.BazarListInformation;
import com.trinoq.mealmanager.features.model.models.DailyMealInputEndTime;
import com.trinoq.mealmanager.features.model.models.GroupAllMembersInformation;
import com.trinoq.mealmanager.features.model.models.GroupInformation;
import com.trinoq.mealmanager.features.model.models.UserInformation;

import java.util.ArrayList;

public class Utils {

    public static ArrayList<GroupInformation> groupInformations=new ArrayList<>();
    public static ArrayList<UserInformation> userInformations=new ArrayList<>();
    public static ArrayList<BazarListInformation> bazarListInformations=new ArrayList<>();
    public static ArrayList<GroupAllMembersInformation> groupAllMembersInformations=new ArrayList<>();
    public static ArrayList<ActiveGroupInformation> activeGroupInformations =new ArrayList<>();

    public static String useridTest = null;
    public static Integer count=0;

    public static ArrayList<DailyMealInputEndTime> dailyMealInputEndTimeinfo=new ArrayList<>();
    public final static String IMAGE_BASE_URL = "http://mealapp.trinoq.com/Userimage/";
}
