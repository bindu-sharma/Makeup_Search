package my.first.makeup_search;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Product_List_Activity extends AppCompatActivity implements
        NetworkingService.NetworkingListener, ProductItemRecyclerViewAdapter.ProductItemClickListener{
    Product_List_Item_Database db;
    ArrayList<Product_List_Item> productItemsList = new ArrayList<>();
    RecyclerView recyclerView;
    ProductItemRecyclerViewAdapter adapter;
    NetworkingService networkingService;
    JsonService jsonService;
    TextView productName;
    TextView productPrice;
    ImageView productImage;
    Database_Manager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = ((MyApp)getApplication()).getDatabaseManager();
        db = dbManager.getDbInstance(this);
        setContentView(R.layout.activity_product_list);
        productName = findViewById(R.id.product_name);
        productPrice = findViewById(R.id.product_price);
        productImage = findViewById(R.id.product_image);
         String selectedBrandName = getIntent().getStringExtra("Brand Name Selected");
         System.out.println("Selected Brand Name = " + selectedBrandName);

         String selectedProductType = getIntent().getStringExtra("Product Type Selected");
        System.out.println("Selected Product Name = " + selectedProductType);
       // http://makeup-api.herokuapp.com/api/v1/products.json?brand=covergirl&product_type=lipstick

        String query1 = "&product_type=";

          String query = selectedBrandName + query1 + selectedProductType;

        networkingService = ( (MyApp)getApplication()).getNetworkingService();
        jsonService = ( (MyApp)getApplication()).getJsonService();
        networkingService.fetchProductTypes(query);
        networkingService.listener = this;

        recyclerView = findViewById(R.id.productListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductItemRecyclerViewAdapter(this,productItemsList);
        adapter.listener = this;
        recyclerView.setAdapter(adapter);



    }

    @Override
    public void APINetworkListener(String jsonString) {
        productItemsList =  jsonService.parseProductItemList(jsonString);
        adapter.productItemList = productItemsList;
        adapter.notifyDataSetChanged();

    }

    @Override
    public void APINetworkingListerForImage(Bitmap image) {
      //  productImage.setImageBitmap(image);
    }

    @Override
    public void onProductItemSelected(Product_List_Item selectedProductItem) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add to favourites ?");
        builder.setNegativeButton("Cancel",null);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbManager.insertNewProduct(selectedProductItem);

                System.out.println("*****New Product Added****");
                System.out.print(selectedProductItem);
            }
        });

        builder.setCancelable(false);
        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();


    }
}