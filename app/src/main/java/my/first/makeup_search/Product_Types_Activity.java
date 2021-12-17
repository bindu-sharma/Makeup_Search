package my.first.makeup_search;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Product_Types_Activity extends AppCompatActivity implements
        ProductTypeRecyclerViewAdapter.ProductTypeClickListener, NetworkingService.NetworkingListener {
    ArrayList<Product_Type_Item> productList = new ArrayList<>();
    RecyclerView recyclerView;
    ProductTypeRecyclerViewAdapter adapter;
    NetworkingService networkingService;
    JsonService jsonService;
    String BrandSelected;
    String productTypeSelected;
   // TextView selectedBrandName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_types);

        BrandSelected = getIntent().getStringExtra("Selected Brand Name");
        ActionBar actionBar = getSupportActionBar();

        if(actionBar!=null){
            actionBar.setTitle(BrandSelected);
       }
        // Getting the saved state
        if (savedInstanceState != null) {
            BrandSelected = savedInstanceState.getString("Selected Brand Name");
        }

        System.out.println(BrandSelected);

        //selectedBrandName = (TextView) findViewById(R.id.brand_name_selected);
       // selectedBrandName.setText(BrandSelected);

        networkingService = ( (MyApp)getApplication()).getNetworkingService();
        jsonService = ( (MyApp)getApplication()).getJsonService();
        networkingService.fetchProductTypes(BrandSelected);
        networkingService.listener = this;

        recyclerView = findViewById(R.id.productTypeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductTypeRecyclerViewAdapter(this,productList);
        adapter.listener = this;
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Selected Brand Name", BrandSelected);
    }


    @Override
    public void APINetworkListener(String jsonString) {
        productList =  jsonService.parseProductList(jsonString);
        adapter.productTypeList = productList;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void APINetworkingListerForImage(Bitmap image) {

    }

    @Override
    public void onProductTypeSelected(Product_Type_Item selectedProductType) {

        productTypeSelected = selectedProductType.getProductTypeName();
        Intent intent = new Intent(this,Product_List_Activity.class);

        intent.putExtra("Brand Name Selected", String.valueOf(BrandSelected));
        intent.putExtra("Product Type Selected", String.valueOf(productTypeSelected));
        startActivity(intent);

    }
}