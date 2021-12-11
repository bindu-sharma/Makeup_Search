package my.first.makeup_search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Product_List_Activity extends AppCompatActivity implements
        NetworkingService.NetworkingListener, ProductItemRecyclerViewAdapter.ProductItemClickListener {

    ArrayList<Product_List_Item> productItemsList = new ArrayList<>();
    RecyclerView recyclerView;
    ProductItemRecyclerViewAdapter adapter;
    NetworkingService networkingService;
    JsonService jsonService;
    TextView productName;
    TextView productPrice;
    ImageView productImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    }
}