package mengqi.finalproject_wecarry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Lu on 2/23/2016.
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodViewHolder> {
    private List<Good> goods;
    private Context context;

    public GoodsAdapter(List<Good> goods, Context context) {
        this.goods = goods;
        this.context = context;
    }

    @Override
    public GoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_goods, parent, false);
        return new GoodViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(GoodViewHolder holder, int position) {
        Good good = goods.get(position);
//        holder.personName.setText(good.name);
//        holder.personInfo.setText(good.info);
//        holder.personPhoto.setImageResource(good.photoId);
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }
}
