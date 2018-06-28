package xyz.imei.charleskeithapp.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import xyz.imei.charleskeithapp.data.vos.ProductVO;

public class GetNewProductResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private String page;

    @SerializedName("newProducts")
    private List<ProductVO> newProducts;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<ProductVO> getNewProducts() {
        return newProducts;
    }

    public boolean isResponseOK(){
        return (code == 200 && newProducts != null );
    }

}
