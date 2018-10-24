package desarrollomobile.tiendadeclases.tiendadeclases.Service;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriasClient {

    @GET("/categorias")
    Call<ListaDeCategorias> getCategorias();


}
