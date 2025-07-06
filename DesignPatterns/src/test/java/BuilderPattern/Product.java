package BuilderPattern;

import lombok.Data;

@Data
public class Product {
    private String name;
    private Double price;

    //optional fields
    private String description;
    private String category;
    private Double discount;
    private boolean freeshipping;
    private int stock;

    private Product(ProductBuilder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
        this.category = builder.category;
        this.discount = builder.discount;
        this.freeshipping = builder.freeshipping;
        this.stock = builder.stock;
    }

    public static class ProductBuilder {
        private final String name;
        private final Double price;
        //optional fields
        private String description;
        private String category;
        private Double discount;
        private boolean freeshipping;
        private int stock;

        //public constructor for mandatory fields
        public ProductBuilder(String name, Double price) {
            this.name = name;
            this.price = price;
        }
        //setter for optional fields
        public ProductBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public ProductBuilder setDiscount(Double discount) {
            this.discount = discount;
            return this;
        }

        public ProductBuilder setFreeshipping(boolean freeshipping) {
            this.freeshipping = freeshipping;
            return this;
        }

        public ProductBuilder setStock(int stock) {
            this.stock = stock;
            return this;
        }

        public Product build(){
            return new Product(this);
        }
    }
}
