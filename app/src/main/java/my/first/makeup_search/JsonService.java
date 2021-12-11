package my.first.makeup_search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {
//Function to parse JSON for Product Types List

    public ArrayList<Product_Type_Item> parseProductList(String jsonProductList) {
            ArrayList<Product_Type_Item> allProductsFromAPI = new ArrayList<>(0);

            try {
                for (int i = 0; i < jsonProductList.length(); i++) {
                    JSONArray jsonArray = new JSONArray(jsonProductList);
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String productType = jsonObject.getString("product_type");

                    allProductsFromAPI.add(new Product_Type_Item(productType));
                    System.out.println(allProductsFromAPI);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return allProductsFromAPI;
        }

        //Function to parse JSON for Product Items List
    public ArrayList<Product_List_Item> parseProductItemList(String jsonProductItemList) {
        ArrayList<Product_List_Item> allProductsItemsFromAPI = new ArrayList<>(0);

        try {
            for (int i = 0; i < jsonProductItemList.length(); i++) {
                JSONArray jsonArray = new JSONArray(jsonProductItemList);
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String productName = jsonObject.getString("name");
                Double productPrice = Double.valueOf(jsonObject.getString("price"));
                String productImage = jsonObject.getString("image_link");

                allProductsItemsFromAPI.add(new Product_List_Item(productImage,productName,productPrice));

               // System.out.println(allProductsFromAPI);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return allProductsItemsFromAPI;
    }

    }

