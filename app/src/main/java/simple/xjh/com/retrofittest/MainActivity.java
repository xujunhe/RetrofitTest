package simple.xjh.com.retrofittest;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.io.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

   private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        retrofit = new Retrofit.Builder().baseUrl("http://192.168.1.254/oa/").addConverterFactory(ScalarsConverterFactory.create()).build();
    }

    public void requestGet(View view)
    {
        HttpRequestService httpRequestService = retrofit.create(HttpRequestService.class);
        Call<String> stringCall = httpRequestService.get();
        stringCall.enqueue(new ResultCall());
    }


    public void requestPost(View view)
    {
        HttpRequestService httpRequestService = retrofit.create(HttpRequestService.class);
        Call<String> resultService = httpRequestService.login("15333332222", "123456", "xxxxxxx");
        resultService.enqueue(new ResultCall());
    }

    public void requestPostFile(View view)
    {
        HttpRequestService httpRequestService = retrofit.create(HttpRequestService.class);

        File file = new File(Environment.getExternalStorageDirectory(), "yun145.apk");
        File file2 = new File(Environment.getExternalStorageDirectory(), "207测试版.apk");
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"),file);
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("application/octet-stream"),file2);

        MultipartBody.Builder multipartBody = new MultipartBody.Builder();
        multipartBody.addFormDataPart("file",file.getName(),requestBody);
        multipartBody.addFormDataPart("file",file2.getName(),requestBody2);
        multipartBody.addFormDataPart("name","value");
        multipartBody.addFormDataPart("name1","value1");
        multipartBody.addFormDataPart("name2","value2");
        MultipartBody build = multipartBody.build();
        Call<String> resultService = httpRequestService.postSingleFile(build);
        resultService.enqueue(new ResultCall());
    }
















    private  class ResultCall implements   Callback<String>
    {

        @Override
        public void onResponse(Call<String> call, Response<String> response) {
            Toast.makeText(getApplicationContext(),response.body(),Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(Call<String> call, Throwable t) {
            Toast.makeText(getApplicationContext(),"请求失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }
    }



}
