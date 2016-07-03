package com.rba.ecommerce.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rba.ecommerce.R;
import com.rba.ecommerce.model.response.ProductBrandResponse;
import com.rba.ecommerce.ui.DetailActivity;
import com.rba.ecommerce.util.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ricardo Bravo on 2/07/16.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private List<ProductBrandResponse.DataEntity> productEntityList;
    static LayoutInflater inflater = null;


    public ProductAdapter(Context context, List<ProductBrandResponse.DataEntity> productEntityList) {
        if(context!=null){
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.productEntityList = productEntityList;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductBrandResponse.DataEntity productEntity = productEntityList.get(position);
        holder.lblPrice.setText(context.getString(R.string.price_text, productEntity.getPrice()));

        Picasso.with(context)
                .load(productEntity.getImage())
                .error(R.drawable.ic_image_not_found)
                .placeholder(R.drawable.ic_image_not_found)
                .into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return productEntityList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgProduct;
        private TextView lblPrice;
        private CardView cvGeneral;

        public ViewHolder(View view) {
            super(view);
            lblPrice = (TextView) view.findViewById(R.id.lblPrice);
            cvGeneral = (CardView) view.findViewById(R.id.cvGeneral);
            imgProduct = (ImageView) view.findViewById(R.id.imgProduct);

            cvGeneral.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cvGeneral:
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra(Constant.PROD_OBJ, productEntityList.get(getAdapterPosition()));
                    context.startActivity(i);
                    break;
                default:
                    break;
            }
        }
    }
}
