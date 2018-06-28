package xyz.imei.charleskeithapp.network.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.imei.charleskeithapp.network.responses.GetNewProductResponse;
import xyz.imei.charleskeithapp.utils.CharlesKeithConstants;

public interface CKApi {

    @FormUrlEncoded
    @POST(CharlesKeithConstants.GET_NEW_PRODUCTS)
    Call<GetNewProductResponse> loadNewProducts(
            @Field(CharlesKeithConstants.PARAM_ACCESS_TOKEN) String token ,
            @Field(CharlesKeithConstants.PARAM_PAGE)String page
    );

}
