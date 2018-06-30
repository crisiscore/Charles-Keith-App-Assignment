package xyz.imei.charleskeithapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.imei.charleskeithapp.R;
import xyz.imei.charleskeithapp.adapters.ProductImageAdapter;
import xyz.imei.charleskeithapp.adapters.RelatedProductAdapter;
import xyz.imei.charleskeithapp.viewpods.ColoursViewPod;

public class ProductDetailsActivity extends BaseActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_product_images)RecyclerView rvProductImages;
    @BindView(R.id.rv_related_products)RecyclerView rvRelatedProducts;
    @BindView(R.id.vp_slide)ColoursViewPod vpSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }

        rvProductImages.setLayoutManager(new LinearLayoutManager(getApplicationContext() , LinearLayoutManager.VERTICAL , false));
        rvProductImages.setAdapter(new ProductImageAdapter());

        rvRelatedProducts.setLayoutManager(new LinearLayoutManager(getApplicationContext() , LinearLayoutManager.HORIZONTAL , false));
        rvRelatedProducts.setAdapter(new RelatedProductAdapter());

        vpSlide.setColourSlide();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) super.onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
