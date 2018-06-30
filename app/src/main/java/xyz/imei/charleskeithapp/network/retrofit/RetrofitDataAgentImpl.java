package xyz.imei.charleskeithapp.network.retrofit;

import android.support.annotation.NonNull;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.imei.charleskeithapp.events.ApiErrorEvent;
import xyz.imei.charleskeithapp.events.SuccessLoadNewProductsEvent;
import xyz.imei.charleskeithapp.events.SuccessRefreshNewProductsEvent;
import xyz.imei.charleskeithapp.network.ProductDataAgent;
import xyz.imei.charleskeithapp.network.responses.GetNewProductResponse;
import xyz.imei.charleskeithapp.utils.CharlesKeithConstants;

public class RetrofitDataAgentImpl implements ProductDataAgent {

    private static RetrofitDataAgentImpl mObjInstance;

    private CKApi mApi;

    private RetrofitDataAgentImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CharlesKeithConstants.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mApi = retrofit.create(CKApi.class);
    }

    public static RetrofitDataAgentImpl getmObjInstance() {
        if (mObjInstance == null) mObjInstance = new RetrofitDataAgentImpl();
        return mObjInstance;
    }

    @Override
    public void getNewProducts(String token, String page , final boolean isRefresh) {
        Call<GetNewProductResponse> call = mApi.loadNewProducts(token, page);
        call.enqueue(new Callback<GetNewProductResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetNewProductResponse> call, @NonNull Response<GetNewProductResponse> response) {
                GetNewProductResponse newProductResponse = response.body();
                if (newProductResponse != null && newProductResponse.isResponseOK()) {
                    if (isRefresh){
                        SuccessRefreshNewProductsEvent event = new SuccessRefreshNewProductsEvent(newProductResponse.getNewProducts());
                        EventBus.getDefault().post(event);
                    }else {
                        SuccessLoadNewProductsEvent event = new SuccessLoadNewProductsEvent(newProductResponse.getNewProducts());
                        EventBus.getDefault().post(event);
                    }
                }else if (newProductResponse != null){
                    ApiErrorEvent event = new ApiErrorEvent(newProductResponse.getMessage());
                    EventBus.getDefault().post(event);
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetNewProductResponse> call, @NonNull Throwable t) {
                Log.d("" , t.getMessage());
                ApiErrorEvent event = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });
    }
}
