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
        Document document = Jsoup.connect("http://www.astronomerstelegram.org/?read=12685").get();
        // 爬取电文序号
        String numberName = document.select("#telegram > p:nth-child(3)").text();
        // 获取电文需要
        String messageNumber = subNumber(numberName);

        // 获取标题和正文
        String content = document.select("#telegram").text();

        // 爬取爆发源名称
        String burstName = subBurstName(content);
        // 爬取观测设备
        String device = subDevice(content);

        // 获取爆发源类型
        String burstTypeName = subBurstType(content);


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
     *
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
     * 获取爆发源
     *
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


    /**
     * 判断是否是新源还是已知源
     *
     * @param burstType
     * @return
     */
    static String subBurstType(String burstType) {
        String buType = null;
        String type = "uncatalogued X-ray transient source";
        if (burstType.contains(type)) {
            buType = "新源";
        } else {
            buType = "已知源";
        }
        return buType;
    }


}
