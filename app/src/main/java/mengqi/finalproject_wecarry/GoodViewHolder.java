package mengqi.finalproject_wecarry;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lu on 2/23/2016.
 */
public class GoodViewHolder extends RecyclerView.ViewHolder {
    public CardView goodsCard;
    public TextView goodsDeparture;
    public TextView goodsArrival;
    public TextView goodsFlex;
    public TextView goodsContent;
    public TextView goodsTime;
    public TextView specialNote;

    private Context context;


    public GoodViewHolder(View itemView, final Context context) {
        super(itemView);
        goodsCard = (CardView) itemView.findViewById(R.id.goods_card);
        goodsDeparture = (TextView) itemView.findViewById(R.id.goods_departure);
        goodsArrival = (TextView) itemView.findViewById(R.id.goods_arrival);
        goodsContent = (TextView) itemView.findViewById(R.id.goods_content);
        goodsTime = (TextView) itemView.findViewById(R.id.goods_time);
        this.context = context;
    }

    public void bind(final Good good) {
        goodsTime.setText(good.datePreferred);
        goodsContent.setText(good.whatToCarry);
        goodsArrival.setText(good.arrivalArea);
        goodsDeparture.setText(good.departureArea);
        goodsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GoodsActivity.class);


                intent.putExtra("EXDE",good.departureArea);
                intent.putExtra("EXAR",good.arrivalArea);
                intent.putExtra("EXDA",good.datePreferred);
                intent.putExtra("EXWH",good.whatToCarry);
                intent.putExtra("EXFL",good.flexibility);
                intent.putExtra("EXEM",good.userEmail);
                intent.putExtra("EXPO",good.photo);
                v.getContext().startActivity(intent);
            }

        });
    }

}
