package info.saberlion.meizi.api;

/**
 * Created by Arthur on 2015/11/23.
 */
public class MeiziApi {
    public static final String MEIZI_API_RANDOM = "http://114.215.135.17/api/meizi/%d/random";

    public static final String MEIZI_API_PIC_URL = "http://114.215.135.17/static/pic/%s";
    // MeiziApi GET
    public static String getRandomMeizi(int n) {
        return String.format(MEIZI_API_RANDOM,n);
    }

    public static String getPicUrl(String filename) {
        return String.format(MEIZI_API_PIC_URL,filename);
    }

}
