package aghr.olhaobra.Util;

import java.util.ArrayList;
import java.util.List;

import aghr.olhaobra.MainActivity;
import aghr.olhaobra.POJO.Cidade;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Hadryel on 09/10/2016.
 */


interface APIAccessor {
    @GET("http://olhaobraapi.azurewebsites.net/api/cidade")
    Call<List<Cidade>> getCidades();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}

public class Service {
    private static APIAccessor apiAcessor;

    public Service()
    {
        apiAcessor = APIAccessor.retrofit.create(APIAccessor.class);
    }

    private ArrayList<Cidade> _listCidadeParser;

    public ArrayList<Cidade> GetCidades()
    {
        ArrayList<Cidade> retorno = new ArrayList<Cidade>();

        Call<List<Cidade>> call = apiAcessor.getCidades();

        call.enqueue(new Callback<List<Cidade>>() {
            @Override
            public void onResponse(Call<List<Cidade>> call, Response<List<Cidade>> response) {
                _listCidadeParser = (ArrayList<Cidade>) response.body();
            }

            @Override
            public void onFailure(Call<List<Cidade>> call, Throwable throwable) {

            }

            public void onPostExecute()
            {

            }
        });
        retorno = _listCidadeParser;
        _listCidadeParser = null;

        return retorno;
    }
}
