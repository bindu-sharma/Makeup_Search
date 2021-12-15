package my.first.makeup_search;

import android.app.Application;

public class MyApp extends Application {
    private NetworkingService networkingService = new NetworkingService();
    private JsonService jsonService = new JsonService();

    public JsonService getJsonService() {
        return jsonService;
    }

    public NetworkingService getNetworkingService() {
        return networkingService;
    }

    private Database_Manager databaseManager = new Database_Manager();

    public Database_Manager getDatabaseManager() {
        return databaseManager;
    }
}

