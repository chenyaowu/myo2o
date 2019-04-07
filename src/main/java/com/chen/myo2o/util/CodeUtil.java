package com.chen.myo2o.util;

import com.google.code.kaptcha.Constants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class CodeUtil {
    public static boolean checkVerifyCode(HttpServletRequest request){
        String verifyCodeExpected = (String) request.getSession().getAttribute(
                Constants.KAPTCHA_SESSION_KEY  );

        String verifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");
        if(verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)){
            return false;
        }
        return true;
    }
    public static BitMatrix generateQRCodeStream(String url,
                                                 HttpServletResponse resp) {
        //给响应头添加头部信息
        resp.setHeader("Cache-Control", "no-store");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/png");
        //设置图片的文字编码以及内边框边距
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 0);
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(url,
                    BarcodeFormat.QR_CODE, 300, 300, hints);
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
        return bitMatrix;
    }
}
