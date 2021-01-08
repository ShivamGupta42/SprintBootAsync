package com.springboot.async.springBootAsync.config;

import com.springboot.async.springBootAsync.Entities.PostMatchConvertor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/*

 https://stackoverflow.com/questions/39385960/issue-with-content-type-in-spring/51160620#51160620

 */

//@Configuration
public class PostMatchReqConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    private PostMatchConvertor converter() {
        PostMatchConvertor converter = new PostMatchConvertor();
        MediaType mediaType = new MediaType("application", "x-www-form-urlencoded", Charset.forName("UTF-8"));
        converter.setSupportedMediaTypes(Arrays.asList(mediaType));
        return converter;
    }

}
