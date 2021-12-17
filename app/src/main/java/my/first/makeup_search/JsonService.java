package my.first.makeup_search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {

    public ArrayList<Product_Type_Item> parseProductList(String jsonProductList) {
        ArrayList<Product_Type_Item> allProductsFromAPI = new ArrayList<>(0);
        String productType;
        Product_Type_Item newProduct;
        JSONArray jsonArray;
        JSONObject jsonObject;
        try {
            jsonArray = new JSONArray(jsonProductList);

            jsonObject = jsonArray.getJSONObject(0);
            productType = jsonObject.getString("product_type");
            newProduct = new Product_Type_Item(productType);
            System.out.println("**************************");
            allProductsFromAPI.add(newProduct);

// Preventing duplicate Objects

            for (int i = 1; i <=jsonArray.length()-1; i++) {
                jsonObject = jsonArray.getJSONObject(i);
                productType = jsonObject.getString("product_type");
                System.out.println("*************************");

                //System.out.println(allProductsFromAPI.get(i).getProductTypeName().equals(productType));

                if(!(allProductsFromAPI.get(i-1).getProductTypeName().equals(productType))){
                    newProduct = new Product_Type_Item(productType);
                    allProductsFromAPI.add(newProduct);
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // List<Product_Type_Item> listWithoutDuplicates = allProductsFromAPI.stream().distinct().collect(Collectors.toList());
       finally {
            return allProductsFromAPI;

        }
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
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
System.out.println(allProductsItemsFromAPI);
        return allProductsItemsFromAPI;
    }

}


