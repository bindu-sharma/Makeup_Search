package my.first.makeup_search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        Context context;
        ArrayList<Product_Type_Item> productTypeList; //List of Product Types

        //Creating interface for click listener for one row in the recycler view

        public interface ProductTypeClickListener{
            void onProductTypeSelected(Product_Type_Item selectedProductType);
        }

        public ProductTypeClickListener listener;

        // Creating viewholder i.e each row in the list

        public class ViewHolder extends RecyclerView.ViewHolder{

            private final TextView product_type;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                product_type = itemView.findViewById(R.id.productName);
            }
//Getters for instance variables

            public TextView getProduct_type(){
                return product_type;
            }

        }

        RecyclerViewAdapter(Context myContext, ArrayList<Product_Type_Item> productList){
            context = myContext;
            productTypeList = productList;

        }
        @NonNull
        @Override

        // Function to inflate row xml in the list xml
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.product_item,parent,false);
            return new ViewHolder(view);
        }

        // Function to populate the rows in the list
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            //  holder.getProduct_type().setText(productTypeList.get(position));
            holder.getProduct_type().setText(productTypeList.get(position).getProductTypeName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onProductTypeSelected(productTypeList.get(position));
                }
            });

        }

        @Override
        public int getItemCount() {
            return productTypeList.size();
        }
    }



