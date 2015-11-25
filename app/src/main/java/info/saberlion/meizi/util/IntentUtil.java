package info.saberlion.meizi.util;

import android.content.Context;
import android.content.Intent;

import info.saberlion.meizi.ui.PicActivity;

public class IntentUtil {
    public static void openPic(Context context,String imageUrl){
        Intent intent = new Intent(context, PicActivity.class);
        intent.putExtra(PicActivity.IMAGE_URL,imageUrl);
        context.startActivity(intent);
    }
}
