package net.eimarketing.eim_20180528.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Upload {
	
	public static String download(String urlString, String filename,String savePath) throws Exception {
	    // 构造URL
	    URL url = new URL(urlString);
	    // 打开连接
	    URLConnection con = url.openConnection();
	    //设置请求超时为5s
	    con.setConnectTimeout(5*1000);
	    // 输入流
	    InputStream is = con.getInputStream();
	
	    // 1K的数据缓冲
	    byte[] bs = new byte[1024];
	    // 读取到的数据长度
	    int len;
	    // 输出的文件流
	   File sf=new File(savePath);
	   if(!sf.exists()){
		   sf.mkdirs();
	   }
	   OutputStream os = new FileOutputStream(sf.getPath()+"//"+filename);
	    // 开始读取
	    while ((len = is.read(bs)) != -1) {
	      os.write(bs, 0, len);
	    }
	    // 完毕，关闭所有链接
	    os.close();
	    is.close();
	    return "http://app.i-mineral.cn/eim_20180528/wechatImg/"+filename;
	} 

	public static void main(String[] args) throws Exception {
		download("http://thirdwx.qlogo.cn/mmopen/vi_32/YyyOyKSxHEd3YExmz6hCX6ooGrjxnUvC9OTtccOibdDkVB3iarqxfHJC0ZABwnnFetfkc5Rf1X0RQ3dkBwjZKFsw/132", "沙克.jpg","f:\\aa\\");  
	    System.out.println("成功！");
	}
}
