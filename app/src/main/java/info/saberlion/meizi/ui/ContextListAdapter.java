package info.saberlion.meizi.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import info.saberlion.meizi.R;
import info.saberlion.meizi.api.MeiziApi;
import info.saberlion.meizi.model.Filename;
import info.saberlion.meizi.net.NetController;
import info.saberlion.meizi.util.IntentUtil;


public class ContextListAdapter extends RecyclerView.Adapter<ContextListAdapter.ViewHolder> {


    final static String TAG = ContextListAdapter.class.getName();

    Context mContext;

    List<Filename> items = new ArrayList<>();

    RequestQueue requestQueue = NetController.getInstance().getRequestQueue();

    ImageLoader imageLoader = NetController.getInstance().getImageLoader();

    public ContextListAdapter(Context mContext) {
        this.mContext = mContext;
    }


    public void setItems(List<Filename> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addItems(List<Filename> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_pic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Filename item = items.get(position);
        holder.setItem(item);
        holder.imageView.setImageUrl(MeiziApi.getPicUrl(item.filename), imageLoader);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final NetworkImageView imageView;

        public void setItem(Filename item) {
            this.item = item;
        }

        private Filename item;


        public ViewHolder(View itemView) {
            super(itemView);

            imageView = (NetworkImageView) itemView.findViewById(R.id.pic);
            imageView.setDefaultImageResId(R.drawable.ic_autorenew_black_18dp);
            imageView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick" + v.getId());
            switch (v.getId()) {
                case R.id.pic:

                case R.id.title:
//                    String topic_title = item.title;
//                    IntentUtils.openWebView(v.getContext(), topic_title, openUrl);
                    IntentUtil.openPic(v.getContext(),MeiziApi.getPicUrl(item.filename));
                    break;

            }
        }
    }
}
