package com.crystal.flexin.data;
/**
 * Created by aymane on 22/03/17.
 */
public class DataManager {

    public FlexinService proxService = FlexinService.Factory.makeFlexinService(FlexinService.URL);

    private static DataManager mInstance;

    public static DataManager getInstance() {
        if(mInstance == null) {
            mInstance = new DataManager();
        }
        return mInstance;
    }



}