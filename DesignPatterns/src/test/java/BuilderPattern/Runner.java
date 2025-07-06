package BuilderPattern;

public class Runner {
    public static void main(String[] args) {
        Product myTv = new Product.ProductBuilder("MI TV", 12500.0).build();
        System.out.println(myTv);

        Product appleTv = new Product.ProductBuilder("Apple TV", 125000.0)
                .setDescription("Mehenga TV")
                .setCategory("Electronics")
                .setDiscount(10.1)
                .setFreeshipping(false)
                .setStock(10)
                .build();
        appleTv.setDiscount(5.2);
        System.out.println(appleTv);
    }
}
