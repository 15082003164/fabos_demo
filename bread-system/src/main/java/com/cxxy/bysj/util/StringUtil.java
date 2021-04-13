package com.cxxy.bysj.util;


import java.io.File;
import java.lang.reflect.Method;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// StringUtil工具类
public class StringUtil {

        //object转字符串
        public static String toString(Object object){
            return object == null ? "" : object.toString();
        }

        //object转字符串给一个默认值
        public static String toString(Object object,String defaultValue){
            return object == null ? defaultValue : object.toString();
        }

        //object转int给默认值
        public static int toInt(Object obj,int defaultValue) {
            try {
                return Integer.valueOf(obj.toString());
            } catch (Exception e){
                return defaultValue;
            }
        }

        //object转double给默认值
        public static double toDouble(Object obj,double defaultValue){
            try {
                return Double.valueOf(obj.toString());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                return defaultValue;
            }
        }

        //判断obj是否为空
        public static boolean isNotEmpty(Object obj) {
            return obj != null && !"".equals(obj.toString().trim());
        }

        //生成32位的UUID
        public static String getUUID32() {
            return UUID.randomUUID().toString().replace("-", "");
        }

        /**
         * 判断一个字符串是否有值，空格也不算有值
         * @param str 字符串
         * @return boolean
         */
        public static boolean availableStr(String str){
            if(str != null && !"".equals(str) && !"".equals(str.trim())){
                return true;
            }else{
                return false;
            }
        }

        /**
         * 验证文件名是否正确
         * @param param 文件名
         * @return 正确返回true
         */
        public static boolean verifyFileName(String param) {
            if (param == null || "".equals(param)||param.length()>255) {
                return false;
            }
            if (param.indexOf("..")>-1||param.indexOf("//")>-1||param.indexOf("")>-1){
                return false;
            }
            String regEx = "[^*|\\:\"<>?]+\\.[^*|\\:\"<>?\u4E00-\u9FA5]+";
            return param.matches(regEx);
        }

        /**
         * 验证文件路径是否正确
         * @param param 文件路径
         * @return 正确返回true
         */
        public static boolean verifyFilePath(String param) {
            if (param == null || "".equals(param)||param.length()>255) {
                return false;
            }
            if (param.indexOf("..")>-1||param.indexOf("//")>-1||param.indexOf("")>-1){
                return false;
            }
            String regEx = "[^*|\"<>?\\.\u4E00-\u9FA5]+";
            return param.matches(regEx);
        }



        /**
         * 输入输出处理
         * @param input 输入输出字符
         * @return 处理后结果
         */
        public static String filterInput(String input) {
            List<String> list = new ArrayList<String>();

            list.add("<");
            list.add(">");
            list.add("(");
            list.add(")");
            list.add("&");
            list.add("?");
            list.add(";");

            String encode = Normalizer.normalize(input, Normalizer.Form.NFKC);

            for (int i=0;i<list.size();i++) {
                encode = encode.replace(list.get(i), "");
            }

            return encode;
        }



        /**
         * 判断数组中是否有对应方法名的方法
         * @param methods 方法数组
         * @param name 方法名
         * @return
         */
        private static boolean hasMethod(Method[] methods,String name){
            if (methods==null||methods.length==0){
                return false;
            }
            for (int i =0; i < methods.length;i++){
                if (methods[i].getName().equals(name)){
                    return true;
                }
            }
            return false;
        }



        /**
         * 判断一个字符在字符串中出现的次数
         * @param str 字符串
         * @param ch 字符
         * @return
         */
        public static int getCharNumber(String str,String ch){
            if (str == null || str.length()==0 || ch.indexOf(ch)<0){
                return -1;
            }
            int count = 0;
            int index = 0;
            do{
                index = str.indexOf(ch,index);
                if (index>-1){
                    count++;
                    index = index+ch.length();
                }
            }while (index>-1);
            return count;
        }


    }




