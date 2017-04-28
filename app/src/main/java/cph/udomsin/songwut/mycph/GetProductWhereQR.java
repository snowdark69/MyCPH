package cph.udomsin.songwut.mycph;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by snowdark69 on 28/4/2560.
 */

public class GetProductWhereQR extends AsyncTask<String, Void , String>{ //ต้อง Implement AsyncTask จาก  alt + enter

    private Context context; // เอาไว้เดึงข้อมูล สื่อสารกัน , สร้าง constructor ของ context โดย alt + enter
    //private static final String urlPHP = "http://swiftcodingthai.com/cph/getProductWhereQRartz.php";

    public GetProductWhereQR(Context context) { //Constructor ของ context
        this.context = context;
    }


    @Override
    protected String doInBackground(String... params) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient(); // สร้าง Class ของ OkHTTPClient โดยใช้ Libery ของ Com Sqaure
            RequestBody requestBody = new FormEncodingBuilder()// สร้างตัวรับคค่าแบบมัดรวมมา โดยให้ตรงกับ PHP (GetProductWhereQR.php

                    .add("isAdd", "true")
                    //.add("QR_code", params[0])
                    .add(params[0],params[1]) //ค้นหาจาก ID และ ชื่อ
                    .build();
            Request.Builder builder = new Request.Builder();
            //Request request = builder.url(urlPHP).post(requestBody).build();
            Request request = builder.url(params[2]).post(requestBody).build();//รับค่า QR_Code
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();


        } catch (Exception e) {
            Log.d("28AprilV1", "e doin ==> " + e.toString());
        }

        return null;
    }
}//Main Class
