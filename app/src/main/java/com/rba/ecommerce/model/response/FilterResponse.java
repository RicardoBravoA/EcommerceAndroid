package com.rba.ecommerce.model.response;

import java.util.List;

/**
 * Created by Ricardo Bravo on 12/07/16.
 */

public class FilterResponse {

    /**
     * status : success
     * code : 200
     */

    private MetaEntity _meta;
    /**
     * brand_id : 1
     * description : Adidas
     * category : [{"category_id":1,"description":"Casual"},{"category_id":2,"description":"Deportiva"}]
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
        /**
         * category_id : 1
         * description : Casual
         */

        private List<CategoryEntity> category;

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

        public List<CategoryEntity> getCategory() {
            return category;
        }

        public void setCategory(List<CategoryEntity> category) {
            this.category = category;
        }

        @Override
        public String toString() {
            return description;
        }

        public static class CategoryEntity {
            private int category_id;
            private String description;

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

            @Override
            public String toString() {
                return description;
            }
        }
    }
}
