package com.springboot.async.springBootAsync.Entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.util.Map;

public class PostMatchConvertor extends AbstractHttpMessageConverter<PostMatchReq> {

    private static final FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected boolean supports(Class<?> clazz) {
        return (PostMatchReq.class == clazz);
    }

    @Override
    protected PostMatchReq readInternal(Class<? extends PostMatchReq> clazz, HttpInputMessage inputMessage) throws HttpMessageNotReadableException, IOException {
        Map<String, String> vals = formHttpMessageConverter.read(null, inputMessage).toSingleValueMap();
        return mapper.convertValue(vals, PostMatchReq.class);
    }

    @Override
    protected void writeInternal(PostMatchReq myObject, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }

}
