package my.first.makeup_search;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkingService {

//    String iconURL1 = "https://openweathermap.org/img/wn/";
//    String iconURL2 = "@2x.png";

        String url = "http://makeup-api.herokuapp.com/api/v1/products.json?brand=";

        public static final ExecutorService networkingExecutor = Executors.newFixedThreadPool(4);
        static Handler networkHandler = new Handler(Looper.getMainLooper());

        interface NetworkingListener{
            void APINetworkListener(String jsonString);
            void APINetworkingListerForImage(Bitmap image);
        }

        NetworkingListener listener;


        public void fetchProductTypes(String text){
            String completeURL = url + text;
            connect(completeURL);
        }

    public void getImageData(String imageLink){
        String imageURL = imageLink;
        networkingExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL urlObj = new URL(imageURL);
                    InputStream in = ((InputStream)urlObj.getContent());
                    Bitmap imageData = BitmapFactory.decodeStream(in);
                    networkHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.APINetworkingListerForImage(imageData);
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

        private void connect(String url){
            networkingExecutor.execute(new Runnable() {
                String jsonString = "";
                @Override
                public void run() {
                    HttpURLConnection httpURLConnection = null;

                    try {
                        URL urlObject = new URL(url);
                        httpURLConnection = (HttpURLConnection) urlObject.openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setRequestProperty("Content-Type","application/json");
                        int status = httpURLConnection.getResponseCode();

                        System.out.println(status);
                        if ((status >= 200) && (status <= 299)) {
                            InputStream in = httpURLConnection.getInputStream();
                            InputStreamReader inputStreamReader = new InputStreamReader(in);
                            int read = 0;
                            while ((read = inputStreamReader.read()) != -1) {
                                char c = (char) read;
                                jsonString += c;
                            }
                            System.out.println(jsonString);
                            final String finalJson = jsonString;
                            System.out.println(finalJson);
                            networkHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    //send data to main thread
                                    listener.APINetworkListener(finalJson);
                                }
                            });
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }  finally {
                        httpURLConnection.disconnect();
                    }
                }
            });
        }
    }

