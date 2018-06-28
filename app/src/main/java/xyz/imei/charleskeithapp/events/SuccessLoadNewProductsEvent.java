package xyz.imei.charleskeithapp.events;

import java.util.ArrayList;
import java.util.List;

import xyz.imei.charleskeithapp.data.vos.ProductVO;

public class SuccessLoadNewProductsEvent {

    private List<ProductVO> newProducts;

    public SuccessLoadNewProductsEvent(List<ProductVO> newProducts) {
        this.newProducts = newProducts;
    }

    public List<ProductVO> getNewProducts() {
        if (newProducts == null) return new ArrayList<>();
        return newProducts;
    }
}
