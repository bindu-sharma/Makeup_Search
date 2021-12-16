package my.first.makeup_search;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1,entities = {Product_List_Item.class})

public abstract class Product_List_Item_Database extends RoomDatabase {
    abstract public Product_Item_DAO getProductItemDAO();
}
