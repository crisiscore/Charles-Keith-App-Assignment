package xyz.imei.charleskeithapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.imei.charleskeithapp.R;
import xyz.imei.charleskeithapp.viewholders.RelatedProductViewHolder;

public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductViewHolder> {

    @NonNull
    @Override
    public RelatedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_related_product , parent ,false);
        return new RelatedProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedProductViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
