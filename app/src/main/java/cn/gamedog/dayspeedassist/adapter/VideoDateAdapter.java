package cn.gamedog.dayspeedassist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.gamedog.dayspeedassist.R;
import cn.gamedog.dayspeedassist.bean.Itemdata;

/**
 * Created by Tech on 2016/8/16.
 */
public class VideoDateAdapter extends  RecyclerView.Adapter<VideoDateAdapter.ItemViewHolder> {
    private List<Itemdata> data;
    private Context context;

    public  VideoDateAdapter(Context context, List<Itemdata> list) {
        this.context=context;
        this.data=list;

    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Itemdata itemdata=data.get(position);
        holder.loadImage(itemdata.getLitpic());
        holder.loadTitle(itemdata.getShorttitle());
        holder.loadClick(itemdata);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.litpic)
        ImageView litpic;
        @Bind(R.id.base_layout)
        RelativeLayout layoutRipple;
        @Bind(R.id.title)
        TextView title;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //    litpic= (ImageView) itemView.findViewById(R.id.litpic);
            // title= (TextView) itemView.findViewById(R.id.title);
        }
        public  void loadImage(String url){
            Glide.with(context).load(url).into(litpic);
        }
        public void loadTitle(String txt){
            title.setText(txt);
        }
        public void loadClick(Itemdata data){
            layoutRipple.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"sadsadsafsarfwaf",1).show();
                }
            });

        }

    }
}
