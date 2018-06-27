package xyz.imei.charleskeithapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import xyz.imei.charleskeithapp.delegates.ProductDelegate;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private ProductDelegate mDelegate;

    public ProductViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.onTapProduct();
            }
        });
    }

    public void bind(ProductDelegate delegate) {
        mDelegate = delegate;
    }
}
