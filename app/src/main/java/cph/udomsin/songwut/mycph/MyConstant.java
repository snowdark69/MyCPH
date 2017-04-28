package cph.udomsin.songwut.mycph;

/**
 * Created by snowdark69 on 28/4/2560.
 */

public class MyConstant {

    // ชื่อเว็ป
    private String urlGetProductWhereQR = "http://swiftcodingthai.com/cph/getProductWhereQRartz.php";

    public String[] getColumnProduct() {
        return columnProduct;
    }

    // คอลัมภ์ มีหลายคอลัมภ์ต้องใช้ สตริง
    private String[] columnProduct = new String[]{"id","Name","QR_code","id_Receive",
                                                "Description","Date_Receive"};

    //alt + insert เพื่อสร้าง method getter ของตัวแปรนั้นๆ เพื่อส่งค่าให้ ***

    public String getUrlGetProductWhereQR() {
        return urlGetProductWhereQR;
    }
} //Main Class
