package xyz.imei.charleskeithapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.imei.charleskeithapp.R;
import xyz.imei.charleskeithapp.data.models.CKModel;
import xyz.imei.charleskeithapp.data.vos.ProductVO;
import xyz.imei.charleskeithapp.delegates.ProductDelegate;

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private ProductDelegate mDelegate;
    private ProductVO mProductVO;

    @BindView(R.id.iv_product_image)
    ImageView ivProductImage;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;

    public ProductViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this , itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.onTapProduct(mProductVO);
            }
        });
    }

    public void bind(ProductDelegate delegate, ProductVO productVO) {
        mDelegate = delegate;
        mProductVO = productVO;
        Glide.with(ivProductImage.getContext())
                .load(productVO.getProductImage())
                .apply(RequestOptions.fitCenterTransform())
                .into(ivProductImage);

        tvProductName.setText(productVO.getProductTitle());
    }
}
