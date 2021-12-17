package my.first.makeup_search;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Favourites_Activity extends AppCompatActivity implements
        DatabaseAdapter.FavouriteItemClickListener,
        Database_Manager.DatabaseListener{

    Product_List_Item_Database db;

    ArrayList<Product_List_Item> favouriteListFromDB = new ArrayList<>();
    RecyclerView recyclerView;
    DatabaseAdapter adapter;
   // ProductItemRecyclerViewAdapter adapter;

    TextView productName;
    TextView productPrice;
    ImageView productImage;
    Database_Manager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar!=null){
            actionBar.setTitle("Favourites");
        }

        dbManager = ((MyApp)getApplication()).getDatabaseManager();
        db = dbManager.getDbInstance(this);
        dbManager.getAllFavourites();
        dbManager.listener = this;


       // setContentView(R.layout.activity_favourites);
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
        productImage = findViewById(R.id.product_image);
        recyclerView = findViewById(R.id.favouriteProductsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DatabaseAdapter(this,favouriteListFromDB);
        adapter.listener = this;
        recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);



    }

    @Override
    public void databaseAllProductsListener(List <Product_List_Item> list) {
        adapter.FavouriteItemList = (ArrayList<Product_List_Item>) list;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFavouriteItemClicked(Product_List_Item selectedProductItem) {

    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //Toast.makeText(Favourites_Activity.this, "Item Moveing", Toast.LENGTH_SHORT).show();

            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            //Remove swiped item from list and notify the RecyclerView
            int position = viewHolder.getAdapterPosition();
            dbManager.deleteFromFavourites(adapter.FavouriteItemList.get(position));

            adapter.FavouriteItemList.remove(position);
            // we have to remove it from db as well

            adapter.notifyDataSetChanged();

        }
    };
}
