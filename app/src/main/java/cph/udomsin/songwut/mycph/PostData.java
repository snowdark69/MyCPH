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
 * Created by snowdark69 on 26/4/2560.
 */

//AsyncTask > จะส่งข้อมูลไปเรื่อยๆจนกว่าจะสำเร็จ
//ใส่ Void จะไม่มีโปรเซส หมุนติ้วๆขึ้นมาโชว์ แล้วส่ง ่ค่า string กลับมา ($Result --> echo True,False
public class PostData extends AsyncTask<String, Void, String>{ //สร้าง implement AsyncTask โดยกด Alt + Enter



    private Context context; // ใช้ context ส่งข้อมูล เข้าเซิฟเวอร์ , มอง context เป็น ท่อที่ใช้ส่งข้อมูล , สร้าง constructor กด alt + enter หน้า context
    //private static final String urlPHP = "http://swiftcodingthai.com/cph/addUserMasterArt.php"; //สร้างตัวแปรคงที่ (Static)ที่ไม่สามารถแก้ไข้ได้ ที่ชือว่า urlPHP
    private static final String urlPHP = "http://swiftcodingthai.com/cph/addUserMaster.php";

    public PostData(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        try { // กรณีที่เขียน Code ที่มีโอกาสเออเร่อ เ่ช่น กรณีจะส่งข้อมูลเข้าเน็ต แต่ไม่ได้ต่อเน็ต จะให้ทำใหม่ [try > catch > exception] ERROR ที่ยอมรับได้

            OkHttpClient okHttpClient = new OkHttpClient(); //ต้องสร้าง instant ถ้าจะใช้ class ภายนอกมาใช้

            //ผูก String ตัวแปร เพื่อส่งค่าให้ PHP สามารถรับได้ (RequestBody) ซึ่งต้องตรงกับ PHP ตอน รับค่า
            RequestBody requestBody = new FormEncodingBuilder() // ctrl + space ถ้าไม่เห็นค่า่ FormEncodingBuilder
                    .add("isAdd", "true")
                    .add("User", params[0])
                    .add("Name", params[1])
                    .add("Password", params[2])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlPHP).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) { // เก็บ error ไว้ที่ e
            Log.d("26AprilV1", "e doin =>" + e.toString()); //Filter Log
            return null; //ส่ง่ว่างกลับมาถ้าไม่สำเร็จ
        }
        //return null;
    }
} // Main Class
