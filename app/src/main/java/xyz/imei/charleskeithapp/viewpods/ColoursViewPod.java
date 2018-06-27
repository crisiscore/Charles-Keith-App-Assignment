package xyz.imei.charleskeithapp.viewpods;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xyz.imei.charleskeithapp.R;
import xyz.imei.charleskeithapp.adapters.ProductColorAdapter;
import xyz.imei.charleskeithapp.adapters.RelatedProductAdapter;

public class ColoursViewPod extends RelativeLayout {

    @BindView(R.id.rv_product_colours)
    RecyclerView rvProductColourSlide;
    @BindView(R.id.tv_colour_label)
    TextView tvColourLabel;
    @BindView(R.id.vp_slide)
    ColoursViewPod vpColours;
    private boolean isShow = false;

    public ColoursViewPod(Context context) {
        super(context);
    }

    public ColoursViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColoursViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void setColourSlide() {
        rvProductColourSlide.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvProductColourSlide.setAdapter(new ProductColorAdapter());
    }

    @OnClick(R.id.tv_colour_label)
    public void displaySlide() {

        if (isShow) {
            isShow = false;
            vpColours.setBackgroundColor(getResources().getColor(android.R.color.transparent));
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_END);
            rvProductColourSlide.setLayoutParams(params);

        } else {
            isShow = true;
            vpColours.setBackgroundColor(getResources().getColor(R.color.background_colour_slide));
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_END);
            rvProductColourSlide.setLayoutParams(params);
         }
    }
}
