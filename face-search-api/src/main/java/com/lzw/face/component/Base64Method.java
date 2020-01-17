package com.lzw.face.component;

import com.lzw.face.exception.FaceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.regex.Pattern;

/**
 * base64 工具类
 * @author jamesluozhiwei
 * @date 2020/01/14
 */
@Slf4j
@Component
public class Base64Method {

    /**
     * 网络图片正则表达式
     */
    private final Pattern urlPattern = Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    /**
     * encoder
     */
    private final Base64.Encoder base64Encoder = Base64.getEncoder();

    /**
     * 是否url
     * @param image
     * @return
     */
    public boolean matchUrl(String image){
        return urlPattern.matcher(image).matches();
    }

    /**
     * 获取网络图片并转换成Base64
     * @param image
     * @return
     */
    public String encodeImageToBase64(String image){
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            URL url = new URL(image);
            BufferedImage bufferedImage = ImageIO.read(url);
            ImageIO.write(bufferedImage,"jpg",outputStream);
            return base64Encoder.encodeToString(outputStream.toByteArray());
        } catch (IOException e) {
            log.error("download image error:",e);
            throw new FaceException("获取网络图片("+image+")失败，请确认您的url！");
        }
    }

}
