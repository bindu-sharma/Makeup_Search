package my.first.makeup_search;

public class Product_List_Item {
    String image;
    String productName;
    double productPrice;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public Product_List_Item(String image, String productName, Double productPrice) {
        this.image = image;
        this.productName = productName;
        this.productPrice = productPrice;
    }
}
