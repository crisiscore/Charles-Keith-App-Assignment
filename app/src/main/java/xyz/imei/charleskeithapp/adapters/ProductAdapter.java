package xyz.imei.charleskeithapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.imei.charleskeithapp.R;
import xyz.imei.charleskeithapp.delegates.ProductDelegate;
import xyz.imei.charleskeithapp.viewholders.ProductViewHolder;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private ProductDelegate mDelegate;

    public ProductAdapter(ProductDelegate delegate) {
        mDelegate = delegate;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_product , parent ,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(mDelegate);
    }

    @Override
    public int getItemCount() {
        return 20;
    }
}
