package desarrollomobile.tiendadeclases.tiendadeclases;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private String API_BASE_URL = "https://demo4416100.mockable.io";
    private Retrofit mRetrofit;
    private static final ApiClient ourInstance = new ApiClient();

    public static ApiClient getInstance() {
        return ourInstance;
    }

    private ApiClient() {
        createRetroClnt();
    }
    private void createRetroClnt(){
        if(mRetrofit==null){
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            Retrofit.Builder builder =
                    new Retrofit.Builder()
                            .baseUrl(API_BASE_URL)
                            .addConverterFactory(
                                    GsonConverterFactory.create()
                            );
            mRetrofit = builder.client(httpClient.build())
                    .build();
        }
    }

    public Retrofit getClient(){
              return mRetrofit;
    }
}

