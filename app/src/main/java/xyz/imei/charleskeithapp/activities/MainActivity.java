package xyz.imei.charleskeithapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.imei.charleskeithapp.R;
import xyz.imei.charleskeithapp.adapters.ProductAdapter;
import xyz.imei.charleskeithapp.data.models.CKModel;
import xyz.imei.charleskeithapp.data.vos.ProductVO;
import xyz.imei.charleskeithapp.delegates.ProductDelegate;
import xyz.imei.charleskeithapp.events.ApiErrorEvent;
import xyz.imei.charleskeithapp.events.SuccessLoadNewProductsEvent;
import xyz.imei.charleskeithapp.events.SuccessRefreshNewProductsEvent;
import xyz.imei.charleskeithapp.utils.CharlesKeithConstants;
import xyz.imei.charleskeithapp.viewpods.RetryViewPod;

public class MainActivity extends BaseActivity implements ProductDelegate {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_new_in_two_col)
    RecyclerView rvNewIn;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.vp_retry)
    RetryViewPod vpRetry;
    @BindView(R.id.tv_item_count)
    TextView tvItemCount;
    @BindView(R.id.tv_sort_by)
    TextView tvSortBy;
    @BindView(R.id.iv_one_col)
    ImageView ivOneCol;
    @BindView(R.id.iv_two_col)
    ImageView ivTwoCol;
    private ProductAdapter adapter;
    private int productQty;
    private GridLayoutManager layoutManager;
    private boolean isOneCol = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) getSupportActionBar().setTitle(null);

        layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvNewIn.setLayoutManager(layoutManager);
        adapter = new ProductAdapter(this);

        rvNewIn.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private boolean isListReached = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1
                        && !isListReached) {
                    CKModel.getObjInstance().loadNewProducts();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int pastVisibleItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if (visibleItemCount + pastVisibleItems < totalItemCount) {
                    isListReached = false;
                }
            }
        });

        rvNewIn.setAdapter(adapter);

        CKModel.getObjInstance().loadNewProducts();

        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CKModel.getObjInstance().refreshNewProducts();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @OnClick({R.id.iv_one_col, R.id.iv_two_col})
    public void sortColumn(View view) {
        if (view.getId() == R.id.iv_one_col) {
            layoutManager.setSpanCount(1);
        } else if (view.getId() == R.id.iv_two_col) {
            layoutManager.setSpanCount(2);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Override
    public void onTapProduct(ProductVO productVO) {
        Toast.makeText(getApplicationContext(), "On Tap Product", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ProductDetailsActivity.class);
        intent.putExtra(CharlesKeithConstants.PRODUCT_ID, productVO.getProductId());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessLoadProducts(SuccessLoadNewProductsEvent event) {
        swipeRefreshLayout.setRefreshing(false);
        adapter.appendProductList(event.getNewProducts());
        vpRetry.setVisibility(View.GONE);
        setItemCount(event.getNewProducts().size());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessRefreshGetProducts(SuccessRefreshNewProductsEvent event) {
        swipeRefreshLayout.setRefreshing(false);
        adapter.refreshProductList(event.getNewProducts());
        vpRetry.setVisibility(View.GONE);
        setItemCount(event.getNewProducts().size());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailLoadProducts(ApiErrorEvent event) {
        swipeRefreshLayout.setRefreshing(false);
        vpRetry.setVisibility(View.VISIBLE);
        Toast.makeText(getApplicationContext(), event.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void setItemCount(int size) {
        productQty += size;
        tvItemCount.setText(String.valueOf(productQty).concat(" ").concat(getResources().getString(R.string.dummy_count)));
    }
}
