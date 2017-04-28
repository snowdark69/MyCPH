package cph.udomsin.songwut.mycph;

/**
 * Created by snowdark69 on 28/4/2560.
 */

public class MyConstant {

    // ชื่อเว็ป
    private String urlGetProductWhereQR = "http://swiftcodingthai.com/cph/getProductWhereQRartz.php";
    //alt + insert เพื่อสร้าง method getter ของตัวแปรนั้นๆ เพื่อส่งค่าให้ ***
    public String getUrlGetProductWhereQR() {
        return urlGetProductWhereQR;
    }



    private String urlGetUserWhereID = "http://swiftcodingthai.com/cph/getUserWhereID.php";
    //alt + insert เพื่อสร้าง method getter ของตัวแปรนั้นๆ เพื่อส่งค่าให้ ***
    public String getUrlGetUserWhereID() {
        return urlGetUserWhereID;
    }


    // คอลัมภ์ มีหลายคอลัมภ์ต้องใช้ สตริง
    private String[] columnProduct = new String[]{"id","Name","QR_code","id_Receive",
                                                "Description","Date_Receive"};
    //alt + insert เพื่อสร้าง method getter ของตัวแปรนั้นๆ เพื่อส่งค่าให้ ***
    public String[] getColumnProduct() {
        return columnProduct;
    }


} //Main Class
