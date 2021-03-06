package com.rba.ecommerce.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rba.ecommerce.R;
import com.rba.ecommerce.model.response.BrandResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ricardo Bravo on 3/07/16.
 */

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {

    private Context context;
    private List<BrandResponse.DataEntity> brandEntityList;
    static LayoutInflater inflater = null;


    public BrandAdapter(Context context, List<BrandResponse.DataEntity> brandEntityList) {
        if(context!=null){
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.brandEntityList = brandEntityList;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_brand, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        BrandResponse.DataEntity brandEntity = brandEntityList.get(position);
        holder.lblDescription.setText(context.getString(R.string.number_product,
                String.valueOf(brandEntity.getTotal())));

        Picasso.with(context)
                .load(brandEntity.getImage())
                .error(R.drawable.ic_image_not_found)
                .placeholder(R.drawable.ic_image_not_found)
                .resize(300, 300)
                .into(holder.imgBrand);
    }

    @Override
    public int getItemCount() {
        return brandEntityList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgBrand;
        private TextView lblDescription;
        private CardView cvGeneral;

        public ViewHolder(View view) {
            super(view);
            lblDescription = (TextView) view.findViewById(R.id.lblDescription);
            cvGeneral = (CardView) view.findViewById(R.id.cvGeneral);
            imgBrand = (ImageView) view.findViewById(R.id.imgBrand);

            cvGeneral.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cvGeneral:
                    Toast.makeText(context, "Pressed: "+brandEntityList.get(getAdapterPosition())
                            .getDescription(), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
