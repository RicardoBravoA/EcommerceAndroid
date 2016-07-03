package com.rba.ecommerce.model.response;

import java.util.List;

/**
 * Created by Ricardo Bravo on 3/07/16.
 */

public class CategoryResponse {

    /**
     * status : success
     * code : 200
     */

    private MetaEntity _meta;
    /**
     * category_id : 1
     * description : Casual
     * image : http://1.bp.blogspot.com/-4oJsZJxPDLs/UWml6HmxIVI/AAAAAAAAFZ4/IfZUnScD3dU/s1600/Zapatillas+Running+Adidas+Climacool+Clima+Cool+Revolution+Men+Hombre+David+Beckham+5.jpg
     * total : 6
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
        private int category_id;
        private String description;
        private String image;
        private int total;

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
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
