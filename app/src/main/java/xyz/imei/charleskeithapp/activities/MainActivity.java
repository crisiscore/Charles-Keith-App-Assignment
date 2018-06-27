package xyz.imei.charleskeithapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.imei.charleskeithapp.R;
import xyz.imei.charleskeithapp.adapters.ProductAdapter;
import xyz.imei.charleskeithapp.delegates.ProductDelegate;

public class MainActivity extends AppCompatActivity implements ProductDelegate{

    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.rv_new_in)RecyclerView rvNewIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind( this , this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) getSupportActionBar().setTitle(null);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext() ,2);
        rvNewIn.setLayoutManager(layoutManager);
        rvNewIn.setAdapter(new ProductAdapter(this));
    }

    @Override
    public void onTapProduct() {
        Toast.makeText(getApplicationContext(), "On Tap Product", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext() , ProductDetailsActivity.class);
        startActivity(intent);
    }
}
