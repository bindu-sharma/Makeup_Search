package my.first.makeup_search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Product_List_Activity extends AppCompatActivity implements
        RecyclerViewAdapter.ProductTypeClickListener, NetworkingService.NetworkingListener {
    ArrayList<Product_Type_Item> productList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    NetworkingService networkingService;
    JsonService jsonService;

    TextView selectedBrandName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        String BrandSelected = getIntent().getStringExtra("Selected Brand Name");
        System.out.println(BrandSelected);

        selectedBrandName = (TextView) findViewById(R.id.brand_name_selected);
        selectedBrandName.setText(BrandSelected);

        networkingService = ( (MyApp)getApplication()).getNetworkingService();
        jsonService = ( (MyApp)getApplication()).getJsonService();
        networkingService.fetchProductTypes(BrandSelected);
        networkingService.listener = this;

        recyclerView = findViewById(R.id.productTypeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this,productList);
        adapter.listener = this;
        recyclerView.setAdapter(adapter);

//        adapter.productTypeList = new ArrayList<>(0);
//        adapter.notifyDataSetChanged();

    }

    @Override
    public void APINetworkListener(String jsonString) {
        productList =  jsonService.parseProductList(jsonString);
        //productList = new ArrayList<Product_Type_Item>(Integer.parseInt("Lipstick"));
        adapter.productTypeList = productList;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onProductTypeSelected(Product_Type_Item selectedProductType) {

    }
}