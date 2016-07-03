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
import com.rba.ecommerce.model.response.CategoryResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ricardo Bravo on 3/07/16.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private List<CategoryResponse.DataEntity> categoryEntityList;
    static LayoutInflater inflater = null;


    public CategoryAdapter(Context context, List<CategoryResponse.DataEntity> categoryEntityList) {
        if(context!=null){
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.categoryEntityList = categoryEntityList;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CategoryResponse.DataEntity categoryEntity = categoryEntityList.get(position);
        holder.lblDescription.setText(context.getString(R.string.number_product,
                String.valueOf(categoryEntity.getTotal())));

        Picasso.with(context)
                .load(categoryEntity.getImage())
                .error(R.drawable.ic_image_not_found)
                .placeholder(R.drawable.ic_image_not_found)
                .resize(300, 300)
                .into(holder.imgCategory);
    }

    @Override
    public int getItemCount() {
        return categoryEntityList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgCategory;
        private TextView lblDescription;
        private CardView cvGeneral;

        public ViewHolder(View view) {
            super(view);
            lblDescription = (TextView) view.findViewById(R.id.lblDescription);
            cvGeneral = (CardView) view.findViewById(R.id.cvGeneral);
            imgCategory = (ImageView) view.findViewById(R.id.imgCategory);

            cvGeneral.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.cvGeneral:
                    Toast.makeText(context, "Pressed: "+categoryEntityList.get(getAdapterPosition())
                            .getDescription(), Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
