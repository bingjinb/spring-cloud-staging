package com.bugod.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.bugod.factory.EnumConverterFactory;
import com.bugod.interceptor.LogInterceptor;
import com.bugod.interceptor.TraceIdInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new EnumConverterFactory());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods("GET", "POST", "DELETE", "PUT").maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TraceIdInterceptor()).addPathPatterns("/api/**");
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/api/**");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(e -> e instanceof MappingJackson2HttpMessageConverter);
        converters.removeIf(e -> e instanceof StringHttpMessageConverter);
        StringHttpMessageConverter stringMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        FastJsonHttpMessageConverter jsonMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig jsonConfig = new FastJsonConfig();
        List<MediaType> supportedMediaTypes = Arrays.asList(
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_ATOM_XML,
                MediaType.APPLICATION_FORM_URLENCODED,
                MediaType.APPLICATION_OCTET_STREAM,
                MediaType.APPLICATION_PDF,
                MediaType.APPLICATION_RSS_XML,
                MediaType.APPLICATION_XHTML_XML,
                MediaType.APPLICATION_XML,
                MediaType.IMAGE_GIF,
                MediaType.IMAGE_JPEG,
                MediaType.IMAGE_PNG,
                MediaType.TEXT_EVENT_STREAM,
                MediaType.TEXT_HTML,
                MediaType.TEXT_MARKDOWN,
                MediaType.TEXT_PLAIN,
                MediaType.TEXT_XML);
        jsonMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
        jsonConfig.setSerializerFeatures(SerializerFeature.SortField,
                SerializerFeature.WriteEnumUsingToString,
                SerializerFeature.QuoteFieldNames,
                SerializerFeature.SkipTransientField,
                SerializerFeature.BrowserCompatible,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteMapNullValue);
        jsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonMessageConverter.setFastJsonConfig(jsonConfig);
        jsonMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
        converters.add(jsonMessageConverter);
        converters.add(stringMessageConverter);
    }
}
