package my.first.makeup_search;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductItemRecyclerViewAdapter extends
        RecyclerView.Adapter<ProductItemRecyclerViewAdapter.ViewHolder>{

    Context context;
    ArrayList<Product_List_Item> productItemList; //List of Product Items

    //Creating interface for click listener for one row in the recycler view

    public interface ProductItemClickListener{
        void onProductItemSelected(Product_List_Item selectedProductItem);
    }

    public ProductItemClickListener listener;

    // Creating viewholder i.e each row in the list

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView product_item_name;
        private final TextView product_item_price;
        private final ImageView product_item_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_item_name = itemView.findViewById(R.id.product_name);
            product_item_price = itemView.findViewById(R.id.product_price);
            product_item_image = itemView.findViewById(R.id.product_image);
        }
//Getters for instance variables

        public TextView getProduct_item_name() {
            return product_item_name;
        }

        public TextView getProduct_item_price() {
            return product_item_price;
        }

        public ImageView getProduct_item_image() {
            return product_item_image;
        }

        }


    ProductItemRecyclerViewAdapter(Context myContext, ArrayList<Product_List_Item> productList){
        context = myContext;
        productItemList = productList;

    }
    @NonNull
    @Override

    // Function to inflate row xml in the list xml
    public ProductItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_item,parent,false);
        return new ViewHolder(view);
    }


    // Function to populate the rows in the list
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //  holder.getProduct_type().setText(productTypeList.get(position));
        holder.getProduct_item_name().setText(productItemList.get(position).getProductName());
        holder.getProduct_item_price().setText("$" + (productItemList.get(position).getProductPrice()+""));
        System.out.println(productItemList.get(position).getImage());

        Picasso.with(context).load(productItemList.get(position).getImage()).into(holder.getProduct_item_image());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onProductItemSelected(productItemList.get(position));
            }
        });

    }


    @Override
    public int getItemCount() {
        return productItemList.size();
    }
}



