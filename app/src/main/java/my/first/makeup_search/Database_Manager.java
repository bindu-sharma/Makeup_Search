package my.first.makeup_search;

import android.content.Context;
import android.net.LinkAddress;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Database_Manager {

    interface DatabaseListener{
        void databaseAllProductsListener(List<Product_List_Item> list);
    }
    public  DatabaseListener listener;
    static Product_List_Item_Database db;

     ExecutorService databaseExecutor = Executors.newFixedThreadPool(4);
     Handler databaseHandler = new Handler(Looper.getMainLooper());


     private static void buildDBInstance(Context context){
        db = Room.databaseBuilder
                (context,Product_List_Item_Database.class,"ProductListdb").build();
    }

   public static Product_List_Item_Database getDbInstance(Context context){
        if (db == null) {
            buildDBInstance(context); // creating database if it does not exist
        }
        return db; // return database if already created
    }
    public  void insertNewProduct(Product_List_Item newProduct){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.getProductItemDAO().insertNewProductItem(newProduct);
            }
        });
    }

    public  void deleteFromFavourites(Product_List_Item newProduct){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                db.getProductItemDAO().deleteProductItem(newProduct);
            }
        });
    }

    public void getAllFavourites(){
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                List<Product_List_Item> list = db.getProductItemDAO().getAll();
                databaseHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.databaseAllProductsListener(list);

                    }
                });
            }
        });

    }
}
