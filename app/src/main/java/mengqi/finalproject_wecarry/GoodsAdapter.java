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
    public static String departureArea = "";
    public static String arrivalArea = "";
    public static String datePreferred = "";





    //filter boolean
    private boolean filterDep(Good good){
       return departureArea.equals("") || good.departureArea.equals(departureArea);
    }

    private boolean filterArv(Good good){
        return arrivalArea.equals("") || good.arrivalArea.equals(arrivalArea);
    }

    private boolean filterDate(Good good){
        return datePreferred.equals("") || good.datePreferred.equals(datePreferred);
    }

//    private boolean filterContent(Good good){
//        return whatToCarry.equals("") || good.whatToCarry.equals(whatToCarry);
//    }


    public GoodsAdapter(Firebase goodsRef, final Context context, final int filter, final String userEmail) {
        goods=new ArrayList<>();
        this.context = context;

        goodsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Good good = dataSnapshot.getValue(Good.class);
                switch (filter) {
                    case 1:
                        goods.add(good);
                        break;
                    case 2:
                        boolean show = filterArv(good) && filterDep(good) &&filterDate(good);//&& filterContent(good);
                        if (show) {
                            goods.add(good);
                        }
                        break;
                    case 3:
                        if(good.userEmail.equals(userEmail)){
                            goods.add(good);
                        }
                        break;
                }
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
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }
}

