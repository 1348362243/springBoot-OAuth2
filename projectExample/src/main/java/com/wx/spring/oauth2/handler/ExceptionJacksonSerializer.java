package com.wx.spring.oauth2.handler;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * 重写OAuth2Exception 返回数据格式
 * @Description:
 * @Title: ExceptionJacksonSerializer
 * @author wangxin
 * @date 2019年10月15日
 */
public class ExceptionJacksonSerializer extends StdSerializer<OAuth2Exception>{
	
	/** serialVersionUID*/  
	private static final long serialVersionUID = 1L;

	protected ExceptionJacksonSerializer() {
 	   super(OAuth2Exception.class);
 	}

	@Override
	public void serialize(OAuth2Exception value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeStartObject();
		gen.writeObjectField("status", value.getHttpErrorCode());
        String errorMessage = value.getOAuth2ErrorCode();
        String msg = value.getMessage();
        if (errorMessage != null) {
            errorMessage = HtmlUtils.htmlEscape(errorMessage);
        }
        if(msg.equals("Bad credentials")){
        	msg = "密码错误";
        }
        gen.writeStringField("msg",  msg);
        gen.writeStringField("alert", errorMessage);
        if (value.getAdditionalInformation()!=null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                gen.writeStringField(key, add);
            }
        }
        gen.writeEndObject();
	}
}
