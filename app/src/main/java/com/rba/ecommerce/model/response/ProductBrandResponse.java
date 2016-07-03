package com.rba.ecommerce.model.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ricardo Bravo on 2/07/16.
 */

public class ProductBrandResponse implements Serializable {


    /**
     * status : success
     * code : 200
     */

    private MetaEntity _meta;
    /**
     * product_id : 1
     * description : Product 1
     * price : 1.00
     * brand_id : 1
     * category_id : 1
     * latitude :
     * longitude :
     * image : https://alicarnold.files.wordpress.com/2009/11/new-product.jpg
     * outstanding : 1
     */

    private List<DataEntity> data;

    public MetaEntity get_meta() {
        return _meta;
    }

    public void set_meta(MetaEntity _meta) {
        this._meta = _meta;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class MetaEntity {
        private String status;
        private String code;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static class DataEntity implements Serializable {
        private int product_id;
        private String description;
        private String price;
        private int brand_id;
        private int category_id;
        private String latitude;
        private String longitude;
        private String image;
        private int outstanding;

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getOutstanding() {
            return outstanding;
        }

        public void setOutstanding(int outstanding) {
            this.outstanding = outstanding;
        }
    }
}
