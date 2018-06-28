package xyz.imei.charleskeithapp.network;

public interface ProductDataAgent {

    void getNewProducts(String token , String page , boolean isRefresh);

}
