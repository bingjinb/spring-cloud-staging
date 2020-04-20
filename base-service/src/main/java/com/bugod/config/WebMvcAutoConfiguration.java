package com.bugod.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.bugod.entity.property.Bugod;
import com.bugod.factory.EnumConverterFactory;
import com.bugod.interceptor.LimitInterceptor;
import com.bugod.interceptor.LogInterceptor;
import com.bugod.interceptor.TraceIdInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.DispatcherType;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableConfigurationProperties({Bugod.class})
public class WebMvcAutoConfiguration implements WebMvcConfigurer {

    private final Bugod properties;

    public WebMvcAutoConfiguration(Bugod properties) {
        this.properties = properties;
    }

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
        /**
         *  【注意】：addPathPatterns(/api/**),  /api/** 路径是针对 Controller 层的 @RequestMapping("/api") 设置的路径
         */
        registry.addInterceptor(new TraceIdInterceptor()).addPathPatterns("/api/**");
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/api/**");
        registry.addInterceptor(new LimitInterceptor()).addPathPatterns("/api/**");
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

    @Bean
    public FilterRegistrationBean globalFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new GlobalFilter());
        registration.addUrlPatterns("/api/*");
        registration.setName("globalFilter");
        registration.setOrder(Integer.MAX_VALUE);
        return registration;
    }
}
