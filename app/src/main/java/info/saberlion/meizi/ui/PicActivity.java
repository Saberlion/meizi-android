package info.saberlion.meizi.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import info.saberlion.meizi.R;
import info.saberlion.meizi.net.NetController;
import info.saberlion.meizi.util.ToastUtil;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PicActivity extends Activity {
    public static String TAG;
    public static final String IMAGE_URL = "image_url";
    boolean mIsHidden;
    ImageView mImageView;
    ImageLoader mImageLoader = NetController.getInstance().getImageLoader();
    RequestQueue mRequestQueue = NetController.getInstance().getRequestQueue();
    String mImageUrl;
    AppBarLayout mAppBar;
    Toolbar mToolbar;
    PhotoViewAttacher mPhotoViewAttacher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();

        setContentView(R.layout.activity_pic);



        mImageView = (ImageView) findViewById(R.id.pic_detail);
        mImageUrl = getIntent().getStringExtra(IMAGE_URL);
        mPhotoViewAttacher = new PhotoViewAttacher(mImageView);

        ImageRequest imageRequest = new ImageRequest(
                mImageUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        VolleyLog.d("success");
                        mImageView.setImageBitmap(response);
                        mPhotoViewAttacher.update();
                    }
                },
                0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.RGB_565,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(error.toString());
                    }
                });
        mRequestQueue.add(imageRequest);

        mPhotoViewAttacher.setOnLongClickListener(new View.OnLongClickListener(){

            /**
             * Called when a view has been clicked and held.
             *
             * @param v The view that was clicked and held.
             * @return true if the callback consumed the long click, false otherwise.
             */
            @Override
            public boolean onLongClick(View v) {
                //TODO 保存图片
                Log.d(TAG,"onLongClick");
                saveImage(createViewBitmap(v));
                return true;
            }
        });

    }

    public Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

    private void saveImage(Bitmap bitmap) {
        String app_name = this.getString(R.string.app_name);
        File myDir = new File("/sdcard/"+app_name);
        myDir.mkdirs();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formator= new SimpleDateFormat("yyyyMMdd-HHMMSS");
        String n = formator.format(new Date(System.currentTimeMillis()));
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            String toastString = this.getString(R.string.save_image)+fname;
            ToastUtil.showShort(toastString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void hideOrShowToolbar() {
        mAppBar.animate()
                .translationY(mIsHidden ? 0 : -mAppBar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();

        mIsHidden = !mIsHidden;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPhotoViewAttacher.cleanup();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
