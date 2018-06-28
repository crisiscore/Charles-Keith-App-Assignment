package xyz.imei.charleskeithapp.data.models;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xyz.imei.charleskeithapp.data.vos.ProductVO;
import xyz.imei.charleskeithapp.events.ApiErrorEvent;
import xyz.imei.charleskeithapp.events.SuccessLoadNewProductsEvent;
import xyz.imei.charleskeithapp.events.SuccessRefreshNewProductsEvent;
import xyz.imei.charleskeithapp.network.ProductDataAgent;
import xyz.imei.charleskeithapp.network.retrofit.RetrofitDataAgentImpl;
import xyz.imei.charleskeithapp.utils.CharlesKeithConstants;

public class CKModel {

    private static CKModel objInstance;

    private ProductDataAgent dataAgent ;

    private Map<String , ProductVO> mProductMap;

    private int mPage;

    private CKModel() {

        dataAgent = RetrofitDataAgentImpl.getmObjInstance();

        mPage = 1;

        mProductMap = new HashMap<>();

        EventBus.getDefault().register(this);
    }

    public static CKModel getObjInstance() {
        if (objInstance == null) objInstance = new CKModel();
        return objInstance;
    }

    public void loadNewProducts(){
        dataAgent.getNewProducts(CharlesKeithConstants.ACCESS_TOKEN , String.valueOf(mPage) , false);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetProducts(SuccessLoadNewProductsEvent event) {
        setDataIntoRepo(event.getNewProducts());
        mPage++;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessRefreshGetProducts(SuccessRefreshNewProductsEvent event) {
        setDataIntoRepo(event.getNewProducts());
    }

    private void setDataIntoRepo(List<ProductVO> newProducts) {
        for (ProductVO productVO : newProducts){
            mProductMap.put(String.valueOf(productVO.getProductId()) , productVO);
        }
    }

    public ProductVO getProductById(String id){return mProductMap.get(id);}

    public void refreshNewProducts() {
        mPage =1;
        dataAgent.getNewProducts(CharlesKeithConstants.ACCESS_TOKEN , String.valueOf(mPage) , true);
    }
}
