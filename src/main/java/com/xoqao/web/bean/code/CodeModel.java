package com.xoqao.web.bean.code;

import java.io.File;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/8/10.
 * Email:dx96_j@163.com
 */
public class CodeModel {

    private String contents;
    private int width = 400;
    private int height = 400;
    private String format = "png";
    private String character_set = "utf-8";
    private int fontSize = 12;
    private File logoFile;
    private float logoRatio = 0.20f;
    private String desc;
    private String location;
    private int whiteWidth;//白边的宽度
    private int[] bottomStart;//二维码最下边的开始坐标
    private int[] bottomEnd;//二维码最下边的结束坐标

    private int[] codestart;  //二维码起始坐标
    private FontSide fontSideBuiding; //楼层描述
    private FontSide fontSideRoom; //场馆描述
    private FontSide fontSideLocal; //位置
    private File backimg;   //背景图

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCharacter_set() {
        return character_set;
    }

    public void setCharacter_set(String character_set) {
        this.character_set = character_set;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public File getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(File logoFile) {
        this.logoFile = logoFile;
    }

    public float getLogoRatio() {
        return logoRatio;
    }

    public void setLogoRatio(float logoRatio) {
        this.logoRatio = logoRatio;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getWhiteWidth() {
        return whiteWidth;
    }

    public void setWhiteWidth(int whiteWidth) {
        this.whiteWidth = whiteWidth;
    }

    public int[] getBottomStart() {
        return bottomStart;
    }

    public void setBottomStart(int[] bottomStart) {
        this.bottomStart = bottomStart;
    }

    public int[] getBottomEnd() {
        return bottomEnd;
    }

    public void setBottomEnd(int[] bottomEnd) {
        this.bottomEnd = bottomEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int[] getCodestart() {
        return codestart;
    }

    public void setCodestart(int[] codestart) {
        this.codestart = codestart;
    }

    public FontSide getFontSideBuiding() {
        return fontSideBuiding;
    }

    public void setFontSideBuiding(FontSide fontSideBuiding) {
        this.fontSideBuiding = fontSideBuiding;
    }

    public FontSide getFontSideRoom() {
        return fontSideRoom;
    }

    public void setFontSideRoom(FontSide fontSideRoom) {
        this.fontSideRoom = fontSideRoom;
    }

    public FontSide getFontSideLocal() {
        return fontSideLocal;
    }

    public void setFontSideLocal(FontSide fontSideLocal) {
        this.fontSideLocal = fontSideLocal;
    }

    public File getBackimg() {
        return backimg;
    }

    public void setBackimg(File backimg) {
        this.backimg = backimg;
    }
}
