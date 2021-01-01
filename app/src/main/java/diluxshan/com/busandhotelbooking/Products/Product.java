package diluxshan.com.busandhotelbooking.Products;

public class Product {
    private Integer productID;

    private String productName;

    private String productType;

    private Integer productQTY;

    private boolean productAVL;

    private static Product instance = new Product();

    public Product(Integer productID, String productName, String productType, Integer productQTY, boolean productAVL) {
        this.productID = productID;
        this.productName = productName;
        this.productType = productType;
        this.productQTY = productQTY;
        this.productAVL = productAVL;
    }

    public Product() { }

    //Get the only object available
    public static Product getInstance(){
        return instance;
    }



    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getProductQTY() {
        return productQTY;
    }

    public void setProductQTY(Integer productQTY) {
        this.productQTY = productQTY;
    }

    public boolean isProductAVL() {
        return productAVL;
    }

    public void setProductAVL(boolean productAVL) {
        this.productAVL = productAVL;
    }
}
