package com.rba.ecommerce.model.response;

import java.util.List;

/**
 * Created by Ricardo Bravo on 2/07/16.
 */

public class BrandResponse {

    /**
     * status : success
     * code : 200
     */

    private MetaEntity _meta;
    /**
     * brand_id : 1
     * description : Adidas
     * image : http://5.kicksonfire.net/wp-content/uploads/2016/02/adidas-logo.jpg?6ce6a3
     * total : 5
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

    public static class DataEntity {
        private int brand_id;
        private String description;
        private String image;
        private int total;

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}
