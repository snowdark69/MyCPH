package cph.udomsin.songwut.mycph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ServiceActivity extends AppCompatActivity {

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


    }// Main Method

    private void createListView() {
        String tag = "27AprilV2";
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
} //Main Class
