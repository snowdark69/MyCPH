package cph.udomsin.songwut.mycph;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

//Class cannot use _ # @ and not be use 123xxxxx
//extend MainActivity เพื่อให้ใช้หน้าจอได้
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Explicit ประกาศตัวแปร ถ้าประกาศมากเกินไป Ram จะไม่พอ
    //[aceess](private) [type](EditText) [name](userEdittext) name ให้พิมพ์ชื่อแล้วกด ctrl + spaceเพื่อตั้งชื่อให้ถูกตามเงื่อนไข
    private EditText userEditText,passwordEditText; //กดปุ่ม shift + ctrl + enter เพื่อจบประโยค จะเติมตัวปิดให้อัตโนมัติ
    private TextView textView;
    private Button button;
    private String userString, passwordString; // สร้างตัวแปรเพื่อรอรับ่ค่า

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Statement
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // สร้าง Method (Function)
        //Initial View (ผูกตัวแปร)
        //ขึ้นด้นจ้วยตัวเล็ก
        initialView(); // กด alt + enter ในวงเล็บ เพิ่อสร้างอัตโนมัติ

        //Controller ทำให้ปุ่มมันทำงานได้
        controller(); // ตัวแปรต้องตั้งชื่อเป็นตัวเล็ก

    }//Main Method

    //void เป็นคำสั่งที่ไม่ส่งค่ากลับ
    private void controller() {
        textView.setOnClickListener(MainActivity.this); // alt + enter ที่ MainActivity เพื่อสร้าง Implement เพื่อให้ setOnClickListener ใช้งานได้ใน MainActivity
        button.setOnClickListener(MainActivity.this);
    }

    //Initial View (ผูกตัวแปร จากที่สร้างใน activity_main)
    private void initialView() {
        userEditText = (EditText) findViewById(R.id.edtuser); //ต้องเลือกชื่อ R ให้ตรงกับชื่อของตัวโปรแกรม , id
        passwordEditText = (EditText) findViewById(R.id.edtPassword); //กด alt + enter หลัง edtPassword เพิ่อสร้าง Cast (EditText)
        textView = (TextView) findViewById(R.id.txtLogin);
        button = (Button) findViewById(R.id.btnLogin);
    }


    @Override
    public void onClick(View v) {

        //For TextView
        //shift + ctrl + enter ในวงเล็บ เพื่อให้สร้างตัวจบประโยค,ถ้า v = textView (จิ้มที่ text View)
        if (v == textView) {
            //Intent to SignUp ย้ายไปหน้า SignUp
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class); //ย้ายจาก MainActivity ไป SignUp
            startActivity(intent); //เปิดหน้า SignUp มาแทนที่
        }

        //For Button
        if (v == button) {

            //GEt Value From EditText
            userString = userEditText.getText().toString().trim();
            passwordString = passwordEditText.getText().toString().trim();

            //Check Space
            if (userString.equals("") || passwordString.equals("")) {
                //ถ้า user หรือ password มีค่าว่าง
                MyAlert myAlert = new MyAlert(MainActivity.this);
                myAlert.myDialog("Have Space","Please Fill All Every Blank");
            } else {
                //ถ้า user หรือ password มีค่า
                //สร้าง method แยก
                checkUserAnPass(); //Alt + Enter เพื่อสร้าง Method ที่เราคิดขึ้นมาเอง (checkUserAnPass)

            }

        }
   }

    private void checkUserAnPass() { //โครงสร้าง method ของ checkUserAnPass สร้าง
        try {

            //String urlJason = "http://swiftcodingthai.com/cph/getDataArt.php";
            String urlJason = "http://swiftcodingthai.com/cph/getDataMaster.php";
            boolean b = true;
            String[] columnStrings = new String[]{"id", "Name", "User", "Password"};
            String[] loginStrings = new String[columnStrings.length];


            //สร้างตัวรับค่า (object getdata)
            GetData getData = new GetData(MainActivity.this);
            getData.execute(urlJason);

            // สร้างตัวแประ strJSON เพื่อนรับค่า getData
            String strJSON = getData.get();
            Log.d("27AprilV1", "JSON ==>" + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString(columnStrings[2]))) {
                    b = false;
                    for (int i1=0;i1<columnStrings.length;i1++) {
                        loginStrings[i1] = jsonObject.getString(columnStrings[i1]);
                        Log.d("27AprilV1", "logigString(" + i1 + ") ==> " + loginStrings[i1]);
                    }
                }
            }
            if (b) {
                //User False
                MyAlert myAlert = new MyAlert(MainActivity.this);
                myAlert.myDialog("User False","No This User in my database");

            } else if (passwordString.equals(loginStrings[3])) {
                Toast.makeText(MainActivity.this,"Welcome" + loginStrings[1],
                        Toast.LENGTH_SHORT).show();

            } else {
                MyAlert myAlert = new MyAlert(MainActivity.this);
                myAlert.myDialog("Password False","Please Try Again Password False");

            }

        } catch (Exception e) {
            Log.d("27AprilV1", "e CheckUser ==>" + e.toString());
            //e.printStackTrace(); สรา้าง Log ทุกอย่าง
        }
    }
}   //Main Class
