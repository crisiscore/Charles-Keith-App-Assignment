package xyz.imei.charleskeithapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.imei.charleskeithapp.R;
import xyz.imei.charleskeithapp.viewholders.ProductImageViewHolder;

public class ProductImageAdapter extends RecyclerView.Adapter<ProductImageViewHolder> {

    @NonNull
    @Override
    public ProductImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_product_image , parent , false);
        return new ProductImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductImageViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
