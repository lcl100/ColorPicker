package com.mycom.myapp.colorpicker;

import java.awt.*;

public class Utils {
    /**
     * 将三位RGB值转换成HEX格式
     * @param R 十进制的R值
     * @param G 十进制的G值
     * @param B 十进制的B值
     * @return 返回转换成功HEX格式的字符串
     */
    public String rgbToHex(int R,int G,int B){
        return "#"+Integer.toHexString(R)+Integer.toHexString(G)+Integer.toHexString(B);
    }

    /**
     * 操作结果：将HEX格式字符串转换成Color对象
     * @param hex HEX格式字符串
     * @return Color 转换完成的颜色对象
     */
    public Color hexToRgb(String hex){
        String rHex=hex.substring(1,3);
        String gHex=hex.substring(3,5);
        String bHex=hex.substring(5,7);
        int R=Integer.parseInt(rHex,16);
        int G=Integer.parseInt(gHex,16);
        int B=Integer.parseInt(bHex,16);
        return new Color(R,G,B);
    }
}
