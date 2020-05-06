package com.example.bourbon.activities.clement_activities.model;

public class ListUpload {

    private String CustomerId ;
    private String ShopId;
    private String imageUri;
    private String Manuel;

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public ListUpload(String customerId, String shopId, String imageUri,String manuel) {
        CustomerId = customerId;
        ShopId = shopId;
        this.imageUri = imageUri;
        Manuel = manuel;
    }

    public String getManuel() {
        return Manuel;
    }

    public void setManuel(String manuel) {
        Manuel = manuel;
    }

    public ListUpload(){

    }
}
