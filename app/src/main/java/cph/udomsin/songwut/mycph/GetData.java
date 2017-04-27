package cph.udomsin.songwut.mycph;

import android.content.Context;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

/**
 * Created by snowdark69 on 27/4/2560.
 */
//extends AyncTask เพื่อบังคับให้ทำซ้ำ และ ใช้ <String เพื่อรับค่า Void เพื่อไม่ให้หมุนติ้วๆ>
public class GetData extends AsyncTask<String, Void,String>{ //Alt + Enter หน้า AsyncTask เพื่้อสร้าง Implement (@Override .. return null)

    private Context context;

    public GetData(Context context) { //alt + enter เพื่อสร้าง constructor อัตโนมัติ หลังจาก private Context แล้ว
        this.context = context;
    }


    @Override
    protected String doInBackground(String... params) {

        try {

            //จะใช้ Libery ต้องสร้าง Instant ก่อน เพื่อเอา Class มาใช้
            OkHttpClient okHttpClient = new OkHttpClient(); //สืบทอด Class ก่อน เพื่อจะรับค่า

            //Request.Builder(Class) builder(Object)
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(params[0]).build(); //ใช้ params เพราะ Protect สร้างตัวแปร params ไว้
            // เพื่อรับค่า Json ส่งมาจาก Server (ชุดคำสั่ง php บนเซิฟเวอร์ (username password)) โดยใช้ Response
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) { //try catch > เพื่อสร้างตัวแปรเพื่อเก็บ error ที่รับได้
            Log.d("27AprilV1", "e doin ==>" + e.toString());
            //e.printStackTrace(); สรา้าง Log ทุกอย่าง
            return null;
        }

    }
}//Main Class
