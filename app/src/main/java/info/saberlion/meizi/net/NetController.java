package info.saberlion.meizi.net;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


public class NetController {
    public static final String TAG = NetController.class
            .getSimpleName();

    private RequestQueue mRequestQueue;

    private ImageLoader mImageLoader;

    private Context mContext;

    private static NetController mInstance;

    final int maxDiskCacheBytes = 1 << 27;

    private NetController(Context context){
        mContext = context;
        mRequestQueue = Volley.newRequestQueue(mContext,maxDiskCacheBytes);
        mImageLoader = new ImageLoader(mRequestQueue,new LruBitmapCache());
    }

    public static NetController createInstance(Context context) {
        if (context != null) {
            if (mInstance == null) {
                mInstance = new NetController(context);
            } else {
                throw new IllegalArgumentException("Context must be set");
            }
        }
        return mInstance;
    }


    public static synchronized NetController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        if (this.mImageLoader == null) {
            this.mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
