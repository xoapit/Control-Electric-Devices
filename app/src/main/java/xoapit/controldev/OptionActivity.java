package xoapit.controldev;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class OptionActivity extends Activity {

    TimePicker timePicker;
    DatePicker picker;
    NumberPicker np, np2;
    private TextView txtdisplayTime;
    private Button btnsetTime;
    private Button btnDatePicker, btnTimePicker;
    private int mYear, mMonth, mDay, mHour, mMinute;

    String strdevice = "1", strState = "0", timeInit = "1234567890123456";
    String strDate = "1234567890123456", strTime = "1234567890123456";
    Retrofit retro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_option);

        retro = new Retrofit.Builder().baseUrl("http://doanvdk.16mb.com")
                //xu dung gson cho viec parse va maps json data toi object
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnDatePicker = (Button) findViewById(R.id.btn_date);
        btnTimePicker = (Button) findViewById(R.id.btn_time);


        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        picker = (DatePicker) findViewById(R.id.datePicker);

        np = (NumberPicker) findViewById(R.id.numPicker);
        np2 = (NumberPicker) findViewById(R.id.numPicker2);

        //hien thi thoi gian, trang thai thiet bi mong muon hen gio
        txtdisplayTime = (TextView) findViewById(R.id.txtViewTime);
        //button get lay thoi gian va thuc hien gui du lieu di
        btnsetTime = (Button) findViewById(R.id.btnsetTime);


        //set time ban dau cho dialogtime, date time lay tu timepicker, datepicker
        initTime();
        timeInit = getDataDateTime(); //gia tri time data ban dau mac dinh
        txtdisplayTime.setText(getNotice());

        btnsetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temp = getdt();
                String tempValue=temp;

                temp=encodeString(temp);
                String tbtime = getNotice();
                ResApi resApi = retro.create(ResApi.class);
                if (timeInit.compareTo(tempValue) < 0 && (tempValue.length() == 16)) {
                    final Call<Device> callT = resApi.sendTime(temp); // send data time len server /getTime.php

                    Toast.makeText(getBaseContext(), tbtime, Toast.LENGTH_LONG).show();
                    callT.enqueue(new Callback<Device>() {
                        @Override
                        public void onResponse(Response<Device> response, Retrofit retro) {
                        }

                        @Override
                        public void onFailure(Throwable t) {
                        }
                    });
                } else {
                    Toast.makeText(getBaseContext(), "Fail! Time Error!!!", Toast.LENGTH_LONG).show();
                }
            }
        });


        //number picker

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal1) {
                strdevice = String.valueOf(newVal1);
                txtdisplayTime.setText(getNotice());
            }
        });
        np.setMinValue(1);
        np.setMaxValue(4);
        np.setDisplayedValues(new String[]{"D01-Light", "D02-Fan", "D03-Motor", "D04-Water Heater"});


        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            public void onValueChange(NumberPicker picker, int oldVal, int newVal2) {
                strState = String.valueOf(newVal2);
                txtdisplayTime.setText(getNotice());

            }
        });
        np2.setMinValue(0);
        np2.setMaxValue(1);
        np2.setDisplayedValues(new String[]{"OFF", "ON"});
    }

    //get lay gia tri time tu picker luc ban dau
    public void initTime() {
        mYear = picker.getYear();
        mMonth = picker.getMonth();
        mDay = picker.getDayOfMonth();
        mHour = timePicker.getCurrentHour();
        mMinute = timePicker.getCurrentMinute();
    }

    //tra ve thoi gian hien tai, lay tu time/date picker
    public String getDataDateTime() {
        String temp = new String("0000000000000000");
        StringBuilder buil = new StringBuilder();
        StringBuilder builTemp = new StringBuilder();

        buil.append(picker.getYear());
        builTemp.append(buil.toString());  //get year
        buil.delete(0, buil.length()); //delete All

        buil.append(picker.getMonth() + 1);
        temp = buil.toString();
        temp = fixStr(temp);
        builTemp.append(temp);
        buil.delete(0, buil.length()); //delete All

        buil.append(picker.getDayOfMonth());
        temp = buil.toString();
        temp = fixStr(temp);
        builTemp.append(temp);
        buil.delete(0, buil.length()); //delete All


        buil.append(timePicker.getCurrentHour());
        temp = buil.toString();
        temp = fixStr(temp);
        builTemp.append(temp);
        buil.delete(0, buil.length()); //delete All

        buil.append(timePicker.getCurrentMinute());
        temp = buil.toString();
        temp = fixStr(temp);
        builTemp.append(temp);
        buil.delete(0, buil.length()); //delete All
        builTemp.append("00");
        builTemp.append(strdevice);//bit chi thiet bi
        builTemp.append(strState); //bit chi trang thai

        temp = builTemp.toString();
        return temp;
    }

    public String fixStr(String x) {
        String tmp = "0";
        if (x.length() == 1) x = tmp.concat(x);
        return x;
    }


    //xu li xu kien cho date/time dialog
    public void btn_setDateTime_OnClick(View v) {
        if (v == btnDatePicker) {
            //Khoi dong datepicker dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            mYear = year;
                            mMonth = monthOfYear;
                            mDay = dayOfMonth;
                            txtdisplayTime.setText(getNotice());
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {
            // Khoi dong Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {


                            mHour = hourOfDay;
                            mMinute = minute;
                            txtdisplayTime.setText(getNotice());
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    //tra ve chuoi thoi gian se gui di
    public String getdt() {
        return fixStr(String.valueOf(mYear)) + fixStr(String.valueOf(mMonth + 1)) + fixStr(String.valueOf(mDay)) + fixStr(String.valueOf(mHour)) + fixStr(String.valueOf(mMinute)) + "00" + strdevice + strState;
    }

    //tra ve chuoi time hien dai de thong bao len Toast
    public String getNotice() {
        if (strState.equals("1"))
            return "Time: " + fixStr(String.valueOf(mMonth + 1)) + "/" + fixStr(String.valueOf(mDay)) + "/" + fixStr(String.valueOf(mYear)) + "   " + fixStr(String.valueOf(mHour)) + ":" + fixStr(String.valueOf(mMinute)) + "    D0" + strdevice + "  ON";
        else
            return "Time: " + fixStr(String.valueOf(mMonth + 1)) + "/" + fixStr(String.valueOf(mDay)) + "/" + fixStr(String.valueOf(mYear)) + "   " + fixStr(String.valueOf(mHour)) + ":" + fixStr(String.valueOf(mMinute)) + "    D0" + strdevice + " OFF";
    }

    public void btn_RemoveAll_OnClick(View v){
        ResApi resApi = retro.create(ResApi.class);
        final Call<Device> callT = resApi.sendTime("deleteAll"); // send data time len server /time.php
        Toast.makeText(getBaseContext(),"You removed all", Toast.LENGTH_LONG).show();
        callT.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Response<Device> response, Retrofit retro) {
            }
            @Override
            public void onFailure(Throwable t) {
            }
        });
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