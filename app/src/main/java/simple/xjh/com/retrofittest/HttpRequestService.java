package simple.xjh.com.retrofittest;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 作者：xujunhe on 16-6-16 11:28
 */
public interface HttpRequestService {


    @GET("https://publicobject.com/helloworld.txt")
    Call<String> get();

    @FormUrlEncoded
    @POST("Platform/Mobile/Login")
    Call<String> login(@Field("msid")String msid,@Field("pwd")String pwd,@Field("imei")String imei);

    @POST("http://192.168.1.254:8080/upload/UploadServlet")
    Call<String> postSingleFile(@Body MultipartBody body);



}
