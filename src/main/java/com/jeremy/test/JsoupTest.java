package com.jeremy.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chenjun on 2019/4/25
 */
public class JsoupTest {


    public static void main(String[] args) throws Exception {
        Document document = Jsoup.connect("http://www.astronomerstelegram.org/?read=11399").get();
        // 爬取电文序号
        String numberName = document.select("#telegram > p:nth-child(3)").text();

        String messageNumber = subNumber(numberName);
        System.out.println("电文序号"+messageNumber);
        // 获取标题和正文
        String content = document.select("#telegram").text();
        // 爬取观测设备
        String device = subDevice(content);





    }


    /**
     * 获取电文序号
     *
     * @param numberName
     * @return
     */
    static String subNumber(String numberName) {
        final String start = "ATel";
        final String end = ";";
        // 判断当前标题是否包含
        if (numberName.contains(numberName)) {
            int startNum = numberName.indexOf(start);
            int endNum = numberName.indexOf(end);
            String messageNumer = numberName.substring(startNum, endNum);
            return messageNumer;
        } else {
            return null;
        }
    }

    //



    /**
     * 获取观测地址
     * @param deviceName
     * @return
     */
    static String subDevice(String deviceName) {
        String device = null;
        String[] deviceArr = {"HXMT", "ATCA", "MAXI/GSC"};
        for (int i = 0; i < deviceArr.length; i++) {
            if (deviceName.contains(deviceArr[i])) {
                int indexValue = deviceName.indexOf(deviceArr[i]);
                // 获取截取的值
                device = deviceName.substring(indexValue, indexValue + deviceArr[i].length());
                break;
            }
        }
        return device;
    }

    /**
     *
     *
     * @param deviceName
     * @return
     */
   /* static List<String> subDevice(String deviceName) {
        String[] deviceArr = {"HXMT", "ATCA", "MAXI/GSC"};
        List<String> valueList = new ArrayList<>();
        for (int i = 0; i < deviceArr.length; i++) {
            name(deviceName, deviceArr[i], valueList);
        }
        return valueList;
    }*/

    /**
     * 递归调用自己
     *
     * @param subName
     * @param name
     * @param valueList
     * @return
     */
   /* static List<String> name(String subName, String name, List<String> valueList) {
        if (subName.contains(name)) {
            int indexValue = subName.indexOf(name);
            // 获取截取的值
            String subValue = subName.substring(indexValue, indexValue + name.length());
            valueList.add(subValue);
            String subNext = subName.substring(indexValue + name.length());
            name(subNext, name, valueList);
        }
        return valueList;

    }*/

    /**
     * 获取爆发源
     * @param burstSourceName
     * @return
     */
    static String subBurstName(String burstSourceName) {
        String checkNum = null;
        String[] burstName = {"MAXI J", "XTE J"};
        for (int i = 0; i < burstName.length; i++) {
            if (burstSourceName.contains(burstName[i])) {
                int indexValue = burstSourceName.indexOf(burstName[i]);
                if (burstName[i].equals("MAXI J")) {
                    checkNum = burstSourceName.substring(indexValue, indexValue + 14);
                } else {
                    checkNum = burstSourceName.substring(indexValue, indexValue + 13);
                }
            }
        }
        return checkNum;
    }


   /* static String subBurstLocation(String burstSourceLocation) {
        String locationName = null;
        int index = 0 ;
        String [] location = {"R.A.", "Decl.", "RA", "Dec.", "(RA, Dec)"};
        for (int i = 0; i < location.length; i++) {
            if (burstSourceLocation.contains(location[i])) {
                int indexValue = burstSourceLocation.indexOf(location[i]);
                String equalsSrt = burstSourceLocation.substring(indexValue);
                // 判断如果这个截取到的字符串包含等于，那么表名位置有效
                if (equalsSrt.contains("=")) {
                    int equalIndexValue = equalsSrt.indexOf("=");
                    String locationStr = equalsSrt.substring(equalIndexValue, equalIndexValue+40);
                    for (int j = locationStr.length(); --j >= 0;) {
                        int chr = locationStr.charAt(j);
                        // 判断最后一位数是数字的索引值
                        if (chr < 48 || chr > 57) {
                              index = j-1;
                              continue;
                        }
                    }
                    // 在截
                    locationName  =  locationStr.substring(0,index);
                }
            }
        }
        return locationName;
    }*/

    /*static String subBurstType(String burstType) {
        String type = "uncatalogued X-ray transient source";
    }*/


}
