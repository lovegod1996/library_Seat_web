package com.xoqao.web.utils;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.xoqao.web.bean.code.CodeModel;
import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明：
 * Author: lovegod
 * Date:  2017/8/10.
 * Email:dx96_j@163.com
 */
public class CodeCreator {

    private static int BLACK = 0x000000;
    private static int WHITE = 0xFFFFFF;
    private static int DEEPRED=0x8e162f;

    private BufferedImage createCodeImage(CodeModel info) {
        String contents = info.getContents();
        int width = info.getWidth();
        int height = info.getHeight();
        Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hint.put(EncodeHintType.CHARACTER_SET, info.getCharacter_set());
        hint.put(EncodeHintType.MARGIN, 0);
        MultiFormatWriter writer = new MultiFormatWriter();
        BufferedImage img = null;
        try {
            BitMatrix bm = writer.encode(contents, BarcodeFormat.QR_CODE, width, height, hint);
            int[] locationTopLeft = bm.getTopLeftOnBit();
            int[] locationBottomRight = bm.getBottomRightOnBit();
            info.setBottomStart(new int[]{locationTopLeft[0], locationBottomRight[1]});
            info.setBottomEnd(locationBottomRight);
            int w = bm.getWidth();
            int h = bm.getHeight();
            img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    img.setRGB(x, y, bm.get(x, y) ? BLACK : WHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return img;
    }

    public void createCodeImage(CodeModel info, OutputStream output) {
        BufferedImage bm = createCodeImage(info);
        File logoFile = info.getLogoFile();
        if (logoFile != null && logoFile.exists()) {
            try {
                BufferedImage logoImg = ImageIO.read(logoFile);
                int logoWidth = logoImg.getWidth();
                int logoHeight = logoImg.getHeight();
                int width = bm.getWidth();
                int height = bm.getHeight();
                float ratio = info.getLogoRatio();
                if (ratio > 0) {
                    logoWidth = logoWidth > width * ratio ? (int) (width * ratio) : logoWidth;
                    logoHeight = logoHeight > height * ratio ? (int) (height * ratio) : logoHeight;
                }
                int x = (width - logoWidth) / 2;
                int y = (height - logoHeight) / 2;
                Graphics g = bm.getGraphics();
                g.drawImage(logoImg, x, y, logoWidth, logoHeight, null);
                String desc = info.getDesc();
                //int whiteWidth = 8;
                if (!StringUtils.isEmpty(desc)) {
                    int whiteWidth = info.getHeight() - info.getBottomEnd()[1];
//              width = info.getBottomEnd()[0]-info.getBottomStart()[0];
//              height = info.getBottomEnd()[1]+1;
                    Font font = new Font("黑体", Font.BOLD, info.getFontSize());
                    int fontHeight = g.getFontMetrics(font).getHeight();
                    //计算需要多少行
                    int lineNum = 1;
                    int currentLineLen = 0;
                    for (int i = 0; i < desc.length(); i++) {
                        char c = desc.charAt(i);
                        int charWidth = g.getFontMetrics(font).charWidth(c);
                        if (currentLineLen + charWidth > width) {
                            lineNum++;
                            currentLineLen = 0;
                            continue;
                        }
                        currentLineLen += charWidth;
                    }
                    int totalFontHeight = fontHeight * lineNum;
                    int wordTopMargin = 12;
                    BufferedImage bm1 = new BufferedImage(width, height + totalFontHeight + wordTopMargin + whiteWidth+20, BufferedImage.TYPE_INT_RGB);
                    Graphics g1 = bm1.getGraphics();
                    if (totalFontHeight + wordTopMargin - whiteWidth > 0) {
                        g1.setColor(Color.WHITE);
                        g1.fillRect(0, height, width, totalFontHeight + wordTopMargin + whiteWidth+20);
                    }
                    g1.setColor(new Color(BLACK));
                    g1.setFont(font);
                    g1.drawImage(bm, 0, 0, null);
                    width = info.getBottomEnd()[0] - info.getBottomStart()[0];
                    height = info.getBottomEnd()[1] + 1;
                    currentLineLen = 0;
                    int currentLineIndex = 0;
                    int baseLo = g1.getFontMetrics().getAscent();
                    Integer fontTHeight=fontHeight;
                    for (int i = 0; i < desc.length(); i++) {
                        String c = desc.substring(i, i + 1);
                        int charWidth = g.getFontMetrics(font).stringWidth(c);

                        if(currentLineIndex==(lineNum-1)){
                            Font font2 = new Font("黑体", Font.BOLD, 30);
                            g1.setFont(font2);
                            g1.setColor(new Color(DEEPRED));
                            fontHeight=fontTHeight+10;
                        }
                        if (currentLineLen + charWidth > width) {
                            currentLineIndex++;
                            currentLineLen = 0;
                            g1.drawString(c, currentLineLen + whiteWidth, height + baseLo + fontHeight * (currentLineIndex) + wordTopMargin);
                            currentLineLen = charWidth;
                            continue;
                        }
                        g1.drawString(c, currentLineLen + whiteWidth, height + baseLo + fontHeight * (currentLineIndex) + wordTopMargin);
                        currentLineLen += charWidth;
                    }
                    g1.dispose();
                    bm = bm1;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            ImageIO.write(bm, StringUtils.isEmpty(info.getFormat()) ? info.getFormat() : info.getFormat(), output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createCodeImage(CodeModel info, File file) {
        File parent = file.getParentFile();
        if (!parent.exists()) parent.mkdirs();
        OutputStream output = null;
        try {
            output = new BufferedOutputStream(new FileOutputStream(file));
            createCodeImage(info, output);
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createCodeImage(CodeModel info, String filePath) {
        createCodeImage(info, new File(filePath));
    }

    public String decode(InputStream input) {
        Map<DecodeHintType, Object> hint = new HashMap<DecodeHintType, Object>();
        hint.put(DecodeHintType.POSSIBLE_FORMATS, BarcodeFormat.QR_CODE);
        String result = "";
        try {
            BufferedImage img = ImageIO.read(input);
            int[] pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
            LuminanceSource source = new RGBLuminanceSource(img.getWidth(), img.getHeight(), pixels);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            QRCodeReader reader = new QRCodeReader();
            Result r = reader.decode(bitmap, hint);
            result = r.getText();
        } catch (Exception e) {
            result = "读取错误";
        }
        return result;
    }


}
