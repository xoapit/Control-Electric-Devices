package xoapit.controldev;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Xoapit on 10/17/2016.
 * dinh nghia REST API cho retrofit
 */
public interface ResApi {
    @GET("/device.json")
    Call<Device> getData();   //get file json ve

    @FormUrlEncoded
    @POST("/android.php")
    Call<Device> sendData1(@Field("D01") String D01);  //tim D01 va gui gia tri cua D01 di
    @FormUrlEncoded
    @POST("/android.php")
    Call<Device> sendData2(@Field("D02") String D02);
    @FormUrlEncoded
    @POST("/android.php")
    Call<Device> sendData3(@Field("D03") String D03);
    @FormUrlEncoded
    @POST("/android.php")
    Call<Device> sendData4(@Field("D04") String D04);

    //du lieu gui trang thai bat tat tat ca cac thiet bi
    @FormUrlEncoded
    @POST("/android.php")
    Call<Device> sendStateAll(@Field("stateAll") String stateAll);

    //du lieu gui hen gio
    @FormUrlEncoded
    @POST("/getTime.php")
    Call<Device> sendTime(@Field("sendData") String sendData);  //

    //login user
    @FormUrlEncoded
    @POST("/androidlogin.php")
    Call<Device> createLogin(@Field("user") String user,@Field("pass") String pass);
}
