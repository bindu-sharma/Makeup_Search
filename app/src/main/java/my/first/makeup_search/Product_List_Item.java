package my.first.makeup_search;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product_List_Item {

    @PrimaryKey(autoGenerate = true)
            int id;

    public String image;
   public String productName;
   public double productPrice;

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
