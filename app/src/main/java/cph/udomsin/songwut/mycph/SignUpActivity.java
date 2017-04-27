package cph.udomsin.songwut.mycph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEdittext,userEdittext,passwordEditText;
    private Button button;
    private String nameString, userString, passwordString; //ประกาศตัวแปร เพื่อรับ่ค่าจาก EditText เพื่อ โปรแกรมจะไม่สามารถรับ่่ค่าตรงๆจาก Editext ที่พิมพ์เข้ามาได้

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initialView();

        controller();

    }

    private void controller() {
        button.setOnClickListener(SignUpActivity.this); //alt + enter on click for make implements
    }


    private void initialView() {
        nameEdittext = (EditText) findViewById(R.id.edtName);
        userEdittext = (EditText) findViewById(R.id.edtuser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        button = (Button) findViewById(R.id.btnRegis);
    }

    @Override
    public void onClick(View v) {

        if (v == button) { //ถ้ากดปุ่ม

            //Get Value From Edit Text
            nameString = nameEdittext.getText().toString().trim(); // ให้ค่า nameEdittext จากนั้นแปลงเป็น string และให้ตัดค่าว่างออก
            userString = userEdittext.getText().toString().trim();
            passwordString = passwordEditText.getText().toString().trim();

            //check space
            if (nameString.equals("")) { // ถ้า user ไม่กรอกจะเป็น true , ใน java จะใช้ == ไม่ได้ ต้องใช้ equals แทน
                // Have Spcae
                MyAlert myAlert = new MyAlert(SignUpActivity.this);

                //สืบทอด class เอา instant มาทำงาน
                myAlert.myDialog("มีช่องว่าง", "กรุณากรอกทุกช่อง"); //มี่ช่องว่าง = title , กรุณากรอกทุกช่อง = ข้อมความ

            } else {
                // No Space
                try {

                    PostData postData = new PostData(SignUpActivity.this);
                    postData.execute(userString, nameString, passwordString);

                    String result = postData.get();
                    Log.d("26AprilV1", "result ==>" + result);
                    if (Boolean.parseBoolean(result)) {
                        Toast.makeText(SignUpActivity.this, "Upload Value To Server OK",
                                Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Cannot Upload",
                                Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    Log.d("26AprilV1", "e SignUP ==>" + e.toString());
                }
            }

        }

    }
}
