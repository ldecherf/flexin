package se.anyro.nfc_reader.data;
/**
 * Created by aymane on 22/03/17.
 */
public class DataManager {

    public ProxService proxService = ProxService.Factory.makeProxService(ProxService.ENDPOINT);

    private static DataManager mInstance;

    public static DataManager getInstance() {
        if(mInstance == null) {
            mInstance = new DataManager();
        }
        return mInstance;
    }



}