package info.saberlion.meizi.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import info.saberlion.meizi.R;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Arthur on 2015/11/25.
 */
public class PicActivity extends AppCompatActivity {
    public static String TAG;
    public static final String IMAGE_URL ="image_url";

    ImageView mImageView;

    String mImageUrl;

    PhotoViewAttacher mPhotoViewAttacher;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        setContentView(R.layout.activity_pic);
        mImageView = (ImageView) findViewById(R.id.pic_detail);
        mImageUrl = getIntent().getStringExtra(IMAGE_URL);
        mPhotoViewAttacher = new PhotoViewAttacher(mImageView);
        Picasso.with(this)
                .load(mImageUrl)
                .into(mImageView);

    }

//    private void setupPhotoAttacher() {
//        mPhotoViewAttacher = new PhotoViewAttacher(mImageView);
//        mPhotoViewAttacher.setOnViewTapListener((view, v, v1) -> hideOrShowToolbar());
//        // @formatter:off
//        mPhotoViewAttacher.setOnLongClickListener(v -> {
//            new AlertDialog.Builder(PicActivity.this)
//                    .setMessage(getString(R.string.ask_saving_picture))
//                    .setNegativeButton(android.R.string.cancel,
//                            (dialog, which) -> dialog.dismiss())
//                    .setPositiveButton(android.R.string.ok,
//                            (dialog, which) -> {
//                                saveImageToGallery();
//                                dialog.dismiss();
//                            })
//                .show();
//        // @formatter:on
//        return true;
//        });
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPhotoViewAttacher.cleanup();
    }
}
