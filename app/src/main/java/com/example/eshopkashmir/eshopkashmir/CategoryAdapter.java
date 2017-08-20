package com.example.eshopkashmir.eshopkashmir;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Umair on 06-Jun-17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<Category> category;
    private Context context;

    public CategoryAdapter(Context context,ArrayList<Category> category) {
        this.category = category;
        this.context = context;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder viewHolder, int i) {

       viewHolder.text_category.setText(category.get(i).getCategory_name());
//        Picasso.with(context).load(category.get(i).getCategory_image_url()).error(category.get(i).getCategory_image_url()).transform(new CircleTransform())
//                .resize(240, 120).into(viewHolder.img_category);

        viewHolder.img_category.setImageResource(category.get(i).getCategory_image_url());


    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView text_category;
        private ImageView img_category;
        public ViewHolder(View view) {
            super(view);

            text_category = (TextView)view.findViewById(R.id.text_category);
            img_category = (ImageView) view.findViewById(R.id.img_category);
        }
    }


}
