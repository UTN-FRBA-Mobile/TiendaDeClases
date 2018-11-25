package desarrollomobile.tiendadeclases.tiendadeclases.users

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UsersApiClient {

    companion object {

        fun getRetrofitClient(): UsersApi {

            val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
            val client : OkHttpClient = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
            }.build()
            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("http://167.99.3.180:8080/TDC-0.1/").client(client).build()

            return retrofit.create(UsersApi::class.java)
        }
    }
}
