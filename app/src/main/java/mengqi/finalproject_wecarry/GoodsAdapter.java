package mengqi.finalproject_wecarry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lu on 2/23/2016.
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodViewHolder> {
    private List<Good> goods;
    private Context context;


    public GoodsAdapter(Firebase goodsRef, Context context) {
        goods=new ArrayList<>();
        this.context = context;

        goodsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Good good = dataSnapshot.getValue(Good.class);
                goods.add(good);
                notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Good good = dataSnapshot.getValue(Good.class);
                goods.remove(good);
                notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    @Override
    public GoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_goods, parent, false);
        return new GoodViewHolder(view, context);
    }


    @Override
    public void onBindViewHolder(GoodViewHolder holder, int position) {
        Good good = goods.get(position);
        holder.bind(good);
//        holder.personName.setText(good.name);
//        holder.personInfo.setText(good.info);
//        holder.personPhoto.setImageResource(good.photoId);
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }
}

