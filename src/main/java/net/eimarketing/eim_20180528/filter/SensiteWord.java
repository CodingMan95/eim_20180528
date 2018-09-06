package net.eimarketing.eim_20180528.filter;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

/**
 * 敏感词过滤
 * 1.读取敏感词库
 * 2.初始化敏感词库，构建dfa算法模型
 * Created by ASUS on 2018/5/24
 *
 * @Authod Grey Wolf
 */

@Component
public class SensiteWord {


//    public static void main(String[] args) throws Exception {
//        SensiteWord sensiteWord=new SensiteWord();
//        Set<String>set=sensiteWord.readSensitivateWord();//读取敏感词库
//        HashMap map=sensiteWord.initSensitivateWord(set);
//        String content = "操你妈，sb,shit";
//
//        String c=sensiteWord.replaceSensitiveWord(content,"*");
//        System.out.println("用户content:"+content);
//        Set<String>set1=sensiteWord.getSensitivateWord(content);
//        System.out.println("用户content中的敏感词:"+set1.size()+"---"+set1);
//        System.out.println("过滤后的content:"+c);
//    }

    /**
     *读取敏感词库，文件方式
     * @return
     */
    public Set<String> readSensitivateWord() throws Exception {
        Set<String>set=null;
        File file=new File("http://app.i-mineral.cn/eim_20180528/utils/SensitiveWord.txt");
        InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(file),"GBK");
        //文件是否存在
        if(file.exists()&&file.isFile()){
            set=new HashSet<>();
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String word=null;
            while ((word=bufferedReader.readLine())!=null){
                set.add(word);
            }
        }else {
            throw new Exception("敏感词库文件不存在");
        }
        //关闭文件流
        inputStreamReader.close();
        return set;
    }

    /**
     * 输出set的内容
     * @param set
     */
   public void sysSet(Set<String> set){
        for (String word:set){
            System.out.println("word:"+word);
        }
   }

    /**
     * 输出map的内容
     * @param map
     */
   public void sysMap(HashMap map){
       Iterator entries = map.entrySet().iterator();

       while (entries.hasNext()) {

           Map.Entry entry = (Map.Entry) entries.next();

           Object key = entry.getKey();

           Object value = entry.getValue();

           System.out.println("Key = " + key + ", Value = " + value);

       }
   }


    /**
     * 返回敏感词库列表模型
     * @param set
     * @return
     */
   public HashMap initSensitivateWord(Set<String>set){
       HashMap map=new HashMap(set.size());
       String key=null;
       Map nowMap=null;
       HashMap<String,String> newMap=null;
       //迭代敏感词库
       Iterator<String> iterator=set.iterator();
       while (iterator.hasNext()){
           key=iterator.next();
           nowMap=map;
           int i;
           for (i=0;i<key.length();i++){
               //转换成char型
               char keyChar=key.charAt(i);
               //获取
               Object wordMap=nowMap.get(keyChar);
               //如果存在，直接赋值
               if(wordMap!=null){
                   nowMap= (Map) wordMap;
               }else {//不存在，则构建一个map.将isEnd 设置为0，最后一个为1
                   newMap=new HashMap<String,String>();
                   newMap.put("isEnd","0");
                   nowMap.put(keyChar,newMap);
                   nowMap=newMap;
               }
               //最后一个
               if (i==key.length()-1){
                   nowMap.put("isEnd","1");
               }
           }
       }
       return map;
   }

    /**
     * 替换敏感字符串
     * @param txt
     * @param replaceChar
     * @return
     */
   public String replaceSensitiveWord(String txt,String replaceChar) throws Exception {
       String resultTxt=txt;
       //获取内容中所有的敏感词
       Set<String>set=getSensitivateWord(txt);
       System.out.println("获取所有敏感词：");
       sysSet(set);
       Iterator<String> iterator=set.iterator();
       String word=null;
       String replaceTxt=null;
       while (iterator.hasNext()){
           word=iterator.next();
           //获取要替换的字符串
           replaceTxt=getReplaceTxt(replaceChar,word.length());
           resultTxt=resultTxt.replaceAll(word,replaceTxt);
       }
       return resultTxt;
   }

    /**
     * 获取内容的敏感词
     * @param txt
     * @return
     */
    public Set<String> getSensitivateWord(String txt) throws Exception {
       Set<String>set=new HashSet<>();
       int i;
       for (i=0;i<txt.length();i++){
           //判断是否包含敏感字符,返回敏感个数
           int length=checkSensitiveWord(txt,i);
           if(length>0){
               set.add(txt.substring(i,i+length));
               //因为for会自增
               i=i+length-1;
           }
       }
       return set;
    }

    /**
     * 检查文字中是否包含敏感字符
     * @param txt
     * @param begin
     * @return 如果存在，则返回敏感词字符的长度，不存在返回0
     */
    private int checkSensitiveWord(String txt, int begin) throws Exception {
        //敏感词结束标识符
        boolean flag=false;
        //匹配标识数默认为0
        int matchFlag=0;
        char word=0;
        Map nowMap=initSensitivateWord(readSensitivateWord());
        int i;
        for (i=begin;i<txt.length();i++){
            word=txt.charAt(i);
            //获取指定key
            nowMap=(Map)nowMap.get(word);
            //存在，则判断是否为最后一个
            if(nowMap!=null){
                //找到相应的key,匹配标识为1
                matchFlag++;
                if("1".equals(nowMap.get("isEnd"))){
                    flag=true;
                }
            }else{//不存在，直接返回
                break;
            }
        }
        //长度必须大于等于1，为词
        if (matchFlag < 2|| !flag) {
            matchFlag=0;
        }
        return matchFlag;
    }

    /**
     * 返回要替换敏感字符串
     * @param replaceChar
     * @param length
     * @return
     */
    private String getReplaceTxt(String replaceChar, int length) {
       int i;
       String word=replaceChar;
       for (i=1;i<length;i++){
           word+=replaceChar;
       }
       return word;
    }

}
