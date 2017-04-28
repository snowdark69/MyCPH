package cph.udomsin.songwut.mycph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private TextView nameTextView, dateTextView, detailTextView, receiveNameTextView;
    private String qrCodeString;
    private String tag = "28AprilV1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //ผูกตัวแปร
        initialView();

        controller();

        //get value from intent
        getValueFromIntent();

        //show view
        showView();

    } //Main Method

    private void showView() {

        //สือทอดคลาสที่สร้างขึ้นมา (MyConstant.java) เพื่อจะใช้งานในหน้านี้
        MyConstant myConstant = new MyConstant();
        //สร้างตัวแปรเพื่อใช้ Class ในหน้านี้
        String urlPHP = myConstant.getUrlGetProductWhereQR();
        String[] comlumProduct = myConstant.getColumnProduct(); //ต้องประกาศชนิดเป็น array เพราะข้อมูลที่สืบทอด class มาเป็น array


        try {
            //สือทอดคลาสที่สร้างขึ้นมา (GetProductWhereQR.java)
            GetProductWhereQR getProductWhereQR = new GetProductWhereQR(DetailActivity.this); // สร้าง class เพื่อใช้เในหน้านี้
            getProductWhereQR.execute(comlumProduct[2],qrCodeString, urlPHP);// เอามาใช้ ค่าตำแหน่งที่ 2 จาก getProductWhereQR มาใช้

            String strJSON = getProductWhereQR.get();
            Log.d(tag, "JSON ==> " + strJSON );

            //เปลี่ยน Json ที่ส่งค่ามาเป็นค่าให้ใช้งานได้
            JSONArray jsonArray = new JSONArray(strJSON);

            //จองหน่วยความจำ (สร้างตัวแปรรับค่า ของ json)
            String[] resultStrings = new String[comlumProduct.length]; //comlumProduct.length จำนวนของ Column ยังไม่ใส่ตัวเลขเพราะยังไม่รู้ว่าจะเอา column ไหน
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            for (int i=0;i<resultStrings.length;i++) {// วนลูปรับค่าของ Json ตำแหน่งที่ i ไปเลื่อยๆ
                resultStrings[i] = jsonObject.getString(comlumProduct[i]); //สร้างตัวแปรเพื่อรับค่า
                Log.d(tag, "result(" + i + ") ==>" + resultStrings[i]);
            }

            //เอาค่ามาโชว์จาก JSON mี่วนลูปมาโชว์
            nameTextView.setText(resultStrings[1]);
            dateTextView.setText(resultStrings[5]);
            detailTextView.setText(resultStrings[4]);
            //receiveNameTextView.setText(resultStrings[2]);

            //จะ where ค่า ID ของ user ในproduct โดยใช้ method findNameReceive (สร้างใหม่) ควบคุม
            receiveNameTextView.setText(findNameReceive(resultStrings[3]));



        } catch (Exception e) {
            Log.d(tag, "e showView ==> " + e.toString());
        }

    }

    //private int findNameReceive(String resultString) {
    private String findNameReceive(String idReceive) {

        String tag2 = "28AprilV2";
        MyConstant myConstant = new MyConstant();

        try {
            //สืบทอด Class จาก GetProductWhereQR.java มาที่ DetailActivity.java
            //จะใช้ ID หาชื่อจากอีกตารางนึง โดยสือทอด Class จาก
            GetProductWhereQR getProductWhereQR = new GetProductWhereQR(DetailActivity.this);
            getProductWhereQR.execute("id", idReceive, myConstant.getUrlGetUserWhereID());
            String strJSON = getProductWhereQR.get();
            Log.d(tag2, "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            return jsonObject.getString("Name"); // ส่งค่า Name คือกลับมาให้

        } catch (Exception e) {
            Log.d(tag2, "e findName ==> " + e.toString());
            return null;
        }

    }

    private void getValueFromIntent() {
        qrCodeString = getIntent().getStringExtra("QRcode");
        Log.d(tag, "QRcode ==> " + qrCodeString);
    }

    private void controller() {
        imageView.setOnClickListener(DetailActivity.this); // สร้าง Implemet ของ DetailActivity เพื่อใช้งาน
    }


    private void initialView() {
        imageView = (ImageView) findViewById(R.id.imvBack);
        nameTextView = (TextView) findViewById(R.id.txtName);
        dateTextView = (TextView) findViewById(R.id.txtDate);
        detailTextView = (TextView) findViewById(R.id.txtDetail);
        receiveNameTextView = (TextView) findViewById(R.id.txtReceiveName);
    }

    @Override
    public void onClick(View v) { //สร้าง class เมื่อกด imageView จากตัว controller
        if (v == imageView) {
            finish();
        }
    }
} //Main Class
