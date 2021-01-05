package com.song.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.util.ResourceUtils;

import com.github.wxpay.sdk.WXPayConfig;

public class WXMyConfigUtil implements WXPayConfig {
	
	private byte[] certData;
	 
    public WXMyConfigUtil() throws Exception {
 
//        String certPath = "apiclient_cert.p12";//从微信商户平台下载的安全证书存放的目录
 
    	File file = ResourceUtils.getFile("classpath:apiclient_cert.p12");
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }
 
    @Override
    public String getAppID() {
        return "wxf020b9146ae3ec37";
    }
 
    //parnerid
    @Override
    public String getMchID() {
        return "1511815351";
    }
 
    @Override
    public String getKey() {
        return "111112222233333444445555566666Lw";
    }
 
    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }
 
    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }
 
    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }


}
