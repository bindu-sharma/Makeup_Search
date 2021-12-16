package my.first.makeup_search;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Product_Item_DAO {
    @Insert
    void  insertNewProductItem(Product_List_Item productListItem);

    @Delete
    void deleteProductItem(Product_List_Item productListItem);


    @Query("SELECT * FROM Product_List_Item")
    List<Product_List_Item> getAll();
}
