package cph.udomsin.songwut.mycph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private ImageView imageView;
    private ListView listView;
    private String[] loginStrings,nameStrings,dateStrings,detailStrings, qrCodeStrings; //ใช้รับค่าจากเซิฟเวอร์เป็นอาเรย์

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        initailView(); // สร้าง Method เอง

        //Get Value From Intent
        getValueFromIntent();

        createListView();

        controller();


    }// Main Method


    //สร้าง Overide Method สำเร็จรูป แล้ว สือทอด Class มาจาก สิ่งที่มีอยู่แล้ว
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {// ถ้า scan แล้วได้ค่า ให้เอากลับมาให้ดู
            String result = data.getStringExtra("SCAN_RESULT"); // ค่าบังคับ SCAN_RESULT
            Log.d("28AprilV3", "Result From Scan ==> " + result);

            Intent intent = new Intent(ServiceActivity.this, DetailActivity.class);
            intent.putExtra("QRcode", result);
            startActivity(intent);

        }

    }

    private void controller() { // เมื่อจิ้มที่รูปแล้วจะให้ทำอะไร
        imageView.setOnClickListener(ServiceActivity.this); //ต้องสร้าง Implement ของ ServiceActivity โดยกด alt + enter (เพิ่้อจะสั่งให้ทำอะไรต่อ)
    }

    private void createListView() {
        final String tag = "27AprilV2";
        String urlPHP = "http://swiftcodingthai.com/cph/getProduct.php";

        try {

            GetData getData = new GetData(ServiceActivity.this);
            getData.execute(urlPHP);
            String strJSON = getData.get();
            Log.d(tag, "JSON ==> " + strJSON);

            //รับค่า Json ที่ถูกส่งค่ากลับมาเป็นเป็น array
            JSONArray jsonArray = new JSONArray(strJSON);

            //จองหน่วยคววามจำ (ประกาศตัวแปร)
            nameStrings = new String[jsonArray.length()];
            dateStrings = new String[jsonArray.length()];
            detailStrings = new String[jsonArray.length()];
            qrCodeStrings = new String[jsonArray.length()];

            for (int i = 0;i<jsonArray.length();i++) {
                //สร้าง Object Json มารับค่า
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("Name");
                dateStrings[i] = jsonObject.getString("Date_Receive");
                detailStrings[i] = jsonObject.getString("Description");
                qrCodeStrings[i] = jsonObject.getString("QR_code");

            }

            MyAdapter myAdapter = new MyAdapter(ServiceActivity.this, nameStrings, dateStrings, detailStrings);
            listView.setAdapter(myAdapter);

            //setonitemclick = จิ้มที่ position ไหน , เมื่อจิ้มที่ list View ให้ืำอะไีร
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //เมื่อเลือก OnItemClick จะสร้างเ method ใให้อัตโนมัติ
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Log.d(tag, "You Click ==> " + qrCodeStrings[position]);

                    //เมื่อจิ้มที่ list view แล้วไปทำงานที่ detailActivity
                    Intent intent = new Intent(ServiceActivity.this, DetailActivity.class);

                    //แนบข้อมูลไปด้วย (QRcode)
                    intent.putExtra("QRcode", qrCodeStrings[position]);
                    startActivity(intent);

                }
            });

        } catch (Exception e) { //สร้างตัวดักจับ error ที่รับได้
            Log.d(tag, "e createListView ==>" + e.toString());
        }

    }

    private void getValueFromIntent() {
        loginStrings = getIntent().getStringArrayExtra("Login"); //รับค่ามาจาก activity_service (intent)
        textView.setText(loginStrings[1]); //loginStrings ตำแหน่งที่ 1 (Name)
    }

    private void initailView() {
        textView = (TextView) findViewById(R.id.txtName); //Cast (TextView) โดย alt + Enter ที่ txtname
        imageView = (ImageView) findViewById(R.id.imvQR);
        listView = (ListView) findViewById(R.id.livProduct);

    }

    @Override
    public void onClick(View v) {

        String tag = "28AprilV3";

        if (v == imageView) {
            try {

                Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); //ส่ง key SCAN_MODE และ QR_CODE_MODE เข้าไปเพื่อให้รู้ว่าจะทำอะไรที่เว็ปปลายทาง
                startActivityForResult(intent, 10); //เลข 10 คือ integer ของ request code


            } catch (Exception e) {
                Log.d(tag, "e onClick ==> " + e.toString());
            }
        }
    }
} //Main Class
