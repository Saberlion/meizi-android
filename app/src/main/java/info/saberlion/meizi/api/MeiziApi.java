package info.saberlion.meizi.api;

/**
 * Created by Arthur on 2015/11/23.
 */
public class MeiziApi {
    public static final String MEIZI_API_RANDOM = "http://114.215.135.17/api/meizi/%d/random";

    public static final String MEIZI_API_PIC_URL = "http://saberlion.u.qiniudn.com/%s";

    public static final String MEIZI_API_THUMBNAIL_URL =
            "http://saberlion.u.qiniudn.com/%s?imageView2/0/w/%d/h/%d/interlace/1";

    private static int mHight = 768;
    private static int mWidth = 768;

    // MeiziApi GET
    public static String getRandomMeizi(int n) {
        return String.format(MEIZI_API_RANDOM, n);
    }

    public static String getPicUrl(String filename) {
        return String.format(MEIZI_API_PIC_URL, filename);
    }

    public static String getThumbNailUrl(String filename) {
        return String.format(MEIZI_API_THUMBNAIL_URL, filename, mWidth, mHight);
    }

    public static String getThumbNailUrl(String filename, int width, int hight) {
        return String.format(MEIZI_API_THUMBNAIL_URL, filename, width, hight);
    }

}
