package xyz.imei.charleskeithapp.viewpods;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;

public class RetryViewPod extends RelativeLayout {

    public RetryViewPod(Context context) {
        super(context);
    }

    public RetryViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RetryViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this ,this);
    }
}
