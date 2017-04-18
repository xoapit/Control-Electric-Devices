package xoapit.controldev;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends Activity {

    Button btn1, btn2, btn3, btn4;
    Button btnOption;
    String valueD = "0";      //bien tam de gui gia tri di
    int check1 = 0, check2 = 0, check3 = 0, check4 = 0;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //khoi tao retrofit de gan API endpoint(domain url) cho retrofit
        retrofit=new Retrofit.Builder().baseUrl("http://doanvdk.16mb.com")
                //xu dung gson cho viec parse va maps json data toi object
                .addConverterFactory(GsonConverterFactory.create())//chuyen file json ve dt
                .build();
        btn1= (Button)findViewById(R.id.btn1);
        btn2= (Button)findViewById(R.id.btn2);
        btn3= (Button)findViewById(R.id.btn3);
        btn4= (Button)findViewById(R.id.btn4);
        checkStateInit();

    }


    public void btn1_OnClick(View v){
        check1=check1^1;
        if(check1==0)
            valueD="0";
        else
            valueD="1";
        //tao resApi de su dung retrofit, khoi tao cuoi goi cho retrofit
        ResApi resApi = retrofit.create(ResApi.class);
        //cuoc goi khong dong bo, chay duoi background
        String valueDE= encodeString(valueD);
        final Call<Device> callSend1 = resApi.sendData1(valueDE); // send data
        callSend1.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Response<Device> response, Retrofit retrofit) { }
            @Override
            public void onFailure(Throwable t) { }
        });  //thuc thi vien gui du lieu, va co phan hoi
        Call<Device> call = resApi.getData(); //get json ve
        call.enqueue(new Callback<Device>() {
            //phuong thuc duoc trien khai khi implement interface Callback
            @Override
            public void onResponse(Response<Device> response, Retrofit retrofit) {
                //lay du lieu tra ve tu response qua body
                Device device = response.body();
                String s = device.getD01();
                if(valueD=="1")
                    Toast.makeText(getBaseContext(), "D01 ON", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getBaseContext(), "D01 OFF", Toast.LENGTH_SHORT).show();
                if(check1==0) btn1.setText("D01 OFF");
                else btn1.setText("D01 ON");
            }
            @Override
            public void onFailure(Throwable t) {
                check1=check1^1;
                if(check1==0) btn1.setText("D01 OFF");
                else btn1.setText("D01 ON");
                Toast.makeText(getBaseContext(), "No Connect", Toast.LENGTH_SHORT).show();  //message thong bao
            }
        });

    }

    public void btn2_OnClick(View v){
        check2=check2^1;
        if(check2==0)           valueD="0";
        else            valueD="1";
        ResApi resApi = retrofit.create(ResApi.class);
        String valueDE= encodeString(valueD);
        Call<Device> callSend2 = resApi.sendData2(valueDE); // send data
        callSend2.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Response<Device> response, Retrofit retrofit) { }
            @Override
            public void onFailure(Throwable t) { }
        });  //thuc thi vien gui du lieu, va co phan hoi
        Call<Device> call = resApi.getData(); //get json ve
        call.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Response<Device> response, Retrofit retrofit) {
                //lay du lieu tra ve tu response qua body
                Device device = response.body();
                String s = device.getD02();
                if(valueD=="1")
                    Toast.makeText(getBaseContext(), "D02 ON", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getBaseContext(), "D02 OFF", Toast.LENGTH_SHORT).show();
                if(check2==0) btn2.setText("D02 OFF");
                else btn2.setText("D02 ON");
            }
            @Override
            public void onFailure(Throwable t) {
                check2=check2^1;
                if(check2==0) btn2.setText("D02 OFF");
                else btn2.setText("D02 ON");
                Toast.makeText(getBaseContext(), "No Connect", Toast.LENGTH_SHORT).show();  //message thong bao
            }
        });

    }

    public void btn3_OnClick(View v){
        check3=check3^1;
        if(check3==0)           valueD="0";
        else            valueD="1";
        ResApi resApi = retrofit.create(ResApi.class);
        String valueDE= encodeString(valueD);
        final Call<Device> callSend3 = resApi.sendData3(valueDE); // send data
        callSend3.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Response<Device> response, Retrofit retrofit) { }
            @Override
            public void onFailure(Throwable t) { }
        });  //thuc thi vien gui du lieu, va co phan hoi
        Call<Device> call = resApi.getData(); //get json ve
        call.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Response<Device> response, Retrofit retrofit) {
                //lay du lieu tra ve tu response qua body
                Device device = response.body();
                String s = device.getD03();
                if(valueD=="1")
                    Toast.makeText(getBaseContext(), "D03 ON", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getBaseContext(), "D03 OFF", Toast.LENGTH_SHORT).show();
                if(check3==0) btn3.setText("D03 OFF");
                else btn3.setText("D03 ON");
            }
            @Override
            public void onFailure(Throwable t) {
                check3=check3^1;
                if(check3==0) btn3.setText("D03 OFF");
                else btn3.setText("D03 ON");
                Toast.makeText(getBaseContext(), "No Connect", Toast.LENGTH_SHORT).show();  //message thong bao
            }
        });
    }

    public void btn4_OnClick(View v){
        check4=check4^1;
        if(check4==0)           valueD="0";
        else            valueD="1";
        ResApi resApi = retrofit.create(ResApi.class);
        String valueDE= encodeString(valueD);
        final Call<Device> callSend4 = resApi.sendData4(valueDE); // send data
        callSend4.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Response<Device> response, Retrofit retrofit) { }
            @Override
            public void onFailure(Throwable t) { }
        });  //thuc thi vien gui du lieu, va co phan hoi
        Call<Device> call = resApi.getData(); //get json ve
        call.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Response<Device> response, Retrofit retrofit) {
                //lay du lieu tra ve tu response qua body
                Device device = response.body();
                String s = device.getD04();
                if(valueD=="1")
                    Toast.makeText(getBaseContext(), "D04 ON", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getBaseContext(), "D04 OFF", Toast.LENGTH_SHORT).show();
                if(check4==0) btn4.setText("D04 OFF");
                else btn4.setText("D04 ON");
            }
            @Override
            public void onFailure(Throwable t) {
                check4=check4^1;
                if(check4==0) btn4.setText("D04 OFF");
                else btn4.setText("D04 ON");
                Toast.makeText(getBaseContext(), "No Connect", Toast.LENGTH_SHORT).show();  //message thong bao
            }
        });
    }
    
    //kiem tra trang thai ban dau
    void checkStateInit(){

        ResApi resApi = retrofit.create(ResApi.class);
        Call<Device> callCheck = resApi.getData(); //get json ve
        callCheck.enqueue(new Callback<Device>() {
            //phuong thuc duoc trien khai khi implement interface Callback
            @Override
            public void onResponse(Response<Device> response, Retrofit retrofit) {
                //lay du lieu tra ve tu response qua body
                Device device = response.body();
                String s1 = device.getD01();
                if(s1.equals("0")) {
                    btn1.setText("D01 OFF");
                    check1=0;
                }
                else {
                    btn1.setText("D01 ON");
                    check1=1;
                }

                String s2 = device.getD02();
                if(s2.equals("0")) {
                    btn2.setText("D02 OFF");
                    check2=0;
                }
                else {
                    btn2.setText("D02 ON");
                    check2=1;
                }

                String s3 = device.getD03();
                if(s3.equals("0")) {
                    btn3.setText("D03 OFF");
                    check3=0;
                }
                else {
                    btn3.setText("D03  ON");
                    check3=1;
                }

                String s4 = device.getD04();
                if(s4.equals("0")) {
                    btn4.setText("D04 OFF");
                    check4=0;
                }
                else {
                    btn4.setText("D04 ON");
                    check4=1;
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getBaseContext(), "No Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btn_Option_OnClick(View v){
        try{
            Intent i=new Intent(this,OptionActivity.class);
            startActivity(i);
        }catch (Exception e){}
    }

    //kiem tra trang thai thiet bi
    public void btn_Refresh_OnClick(View v){
        checkStateInit();
    }

    public void btn_Logout_OnClick(View v){
        try{
            Intent i=new Intent(this,LoginActivity.class);
            startActivity(i);
        }catch (Exception e){}
    }

    //bat tat ca cac thiet bi
    public void btn_OnAll_OnClick(View v){
        ResApi resApi = retrofit.create(ResApi.class);

        final Call<Device> callSend = resApi.sendStateAll(encodeString("OnAll")); // send data
        callSend.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Response<Device> response, Retrofit retrofit) { }
            @Override
            public void onFailure(Throwable t) { }
        });
        Call<Device> call = resApi.getData(); //get json ve, kiem tra du lieu
        call.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Response<Device> response, Retrofit retrofit) {
                Toast.makeText(getBaseContext(), "On all device", Toast.LENGTH_SHORT).show();
                btn1.setText("D01 ON");
                btn2.setText("D02 ON");
                btn3.setText("D03 ON");
                btn4.setText("D04 ON");
                check1=1;
                check2=1;
                check3=1;
                check4=1;
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getBaseContext(), "No Connect", Toast.LENGTH_SHORT).show();  //message thong bao
            }
        });
    }

    //Tat tat ca cac thiet bi
    public void btn_OffAll_OnClick(View v){
        ResApi resApi = retrofit.create(ResApi.class);
        final Call<Device> callSend = resApi.sendStateAll(encodeString("OffAll")); // send data
        callSend.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Response<Device> response, Retrofit retrofit) { }
            @Override
            public void onFailure(Throwable t) { }
        });
        Call<Device> call = resApi.getData(); //get json ve, kiem tra du lieu
        call.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Response<Device> response, Retrofit retrofit) {
                Toast.makeText(getBaseContext(), "Off all device", Toast.LENGTH_SHORT).show();
                btn1.setText("D01 OFF");
                btn2.setText("D02 OFF");
                btn3.setText("D03 OFF");
                btn4.setText("D04 OFF");
                check1=0;
                check2=0;
                check3=0;
                check4=0;
            }
            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getBaseContext(), "No Connect", Toast.LENGTH_SHORT).show();  //message thong bao
            }
        });
    }

    public void btn_About_OnClick(View v){
        Intent i= new Intent(this,About.class);
        startActivity(i);
    }

    public String encodeString(String s){
        try {
            byte[] data = s.getBytes();
            s = Base64.encodeToString(data, Base64.DEFAULT).toString();
            s = "xcvcv" + s;
            data = s.getBytes();
            s= Base64.encodeToString(data, Base64.DEFAULT).toString();
        }catch (Exception e){
        }
        return s;
    }
}
