package net.eimarketing.eim_20180528.util;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * base64 编码、解码util
 * 
 * @author ZJS
 */
@SuppressWarnings("restriction")
public class Base64Utils {

    public static String encode(String s) {
        if (s == null)
            return null;
        String res = "";
        try {
            res = new BASE64Encoder().encode(s.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }


    public static String decode(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b,"GBK");
        } catch (Exception e) {
            return null;
        }
    }
}