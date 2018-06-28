package xyz.imei.charleskeithapp.events;

import java.util.List;

import xyz.imei.charleskeithapp.data.vos.ProductVO;

public class SuccessRefreshNewProductsEvent {

    private List<ProductVO> newProducts;

    public SuccessRefreshNewProductsEvent(List<ProductVO> newProducts) {
        this.newProducts = newProducts;
    }

    public List<ProductVO> getNewProducts() {
        return newProducts;
    }
}
