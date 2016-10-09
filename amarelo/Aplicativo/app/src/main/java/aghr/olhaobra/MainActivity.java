package aghr.olhaobra;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import aghr.olhaobra.POJO.Cidade;
import aghr.olhaobra.POJO.Contributor;
import aghr.olhaobra.POJO.Obra;
import aghr.olhaobra.POJO.User;
import aghr.olhaobra.Util.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class MainActivity extends AppCompatActivity {
//
    //public static MainActivity Instance;

    private Service service = new Service();
    private final int REQ_CODE_SPEECH_INPUT = 100;
    List<Obra> obras;

    //ListView lvObras = (ListView) findViewById(R.id.lvObras);

    View clickedListItem;
    int clickedPosition;


    //private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        Instance = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olha_obra_main);


//        obras = Obra.getMockList();

//        ArrayList<Cidade> teste = service.GetCidades();

        //AtualizaListaObras();

//        Service service = Service.retrofit.create(Service.class);

    }

    public void authenticate(View view) {
        goToListObras();
    }

    public void goToListObras()
    {
        Intent intent = new Intent(this, ListObrasActivity.class);
        startActivity(intent);
    }
}
