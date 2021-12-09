package my.first.makeup_search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {

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
    }

