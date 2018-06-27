package xyz.imei.charleskeithapp.data.models;

public class CKModel {

    private static CKModel objInstance;

    private CKModel() {
    }

    public static CKModel getObjInstance() {
        if (objInstance == null) objInstance = new CKModel();
        return objInstance;
    }


}
