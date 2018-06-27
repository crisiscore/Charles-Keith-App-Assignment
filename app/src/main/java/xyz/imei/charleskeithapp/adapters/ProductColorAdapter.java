package xyz.imei.charleskeithapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.imei.charleskeithapp.R;
import xyz.imei.charleskeithapp.viewholders.ProductColorViewHolder;
import xyz.imei.charleskeithapp.viewholders.ProductImageViewHolder;

public class ProductColorAdapter extends RecyclerView.Adapter<ProductColorViewHolder> {

    @NonNull
    @Override
    public ProductColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_colour_product , parent , false);
        return new ProductColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductColorViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
