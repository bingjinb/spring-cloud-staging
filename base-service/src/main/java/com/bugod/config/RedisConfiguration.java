package com.bugod.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * <pre>
 * Copyright (C) 2020 XXX股份有限公司
 * FileName: RedisConfig
 * Author:   虫神
 * Date:     2020/4/9 16:17
 * Description: Redis 缓存配置
 *     参考：https://blog.csdn.net/dreamhai/article/details/80642010
 *  注解：
 *    @Cacheable    对于一个支持缓存的方法，Spring会在其被调用后将其返回值缓存起来，以保证下次利用同样的参数来执行该方法时可以直接从缓存中获取结果
 *      value：  缓存的名称，在 spring 配置文件中定义，必须指定至少一个
 *      key：    缓存的 key，可以为空，如果指定要按照 SpEL 表达式编写，如果不指定，则缺省按照方法的所有参数进行组合
 *      condition：  缓存的条件，可以为空，使用 SpEL 编写，返回 true 或者 false，只有为 true 才进行缓存
 *
 *    @CachePut     每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中
 *
 *    @CacheEvict
 *                  清除缓存元素，有value、key、condition、allEntries和beforeInvocation
 *      allEntries 是boolean类型，表示是否需要清除缓存中的所有元素。默认为false，表示不需要。当指定了allEntries为true时，Spring Cache将忽略指定的key
 *      beforeInvocation 清除操作默认是在对应方法成功执行之后触发的，即方法如果因为抛出异常而未能成功返回时也不会触发清除操作。使用beforeInvocation可以改变触发清除操作的时间，当我们指定该属性值为true时，Spring会在调用该方法之前清除缓存中的指定元素。
 *
 *
 *
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间         Jira编号            描述
 *
 *
 * </pre>
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {

    /**
     * 设置 redis 下 key 生成规则，尽量保持唯一性，即默认生成规则：
     * 类名::方法名:参数名
     * @return KeyGenerator
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
//        SimpleKeyGenerator simpleKeyGenerator = new SimpleKeyGenerator();
//        return simpleKeyGenerator;
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append("::" + method.getName() + ":");
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * retemplate相关配置【mark】
     * 1. 通过注解的方式 @Cacheable、@CachePut放入缓存，通过桌面工具看不到value
     * 2. value值通过默认的、StringRedisSerializer、Jackson2JsonRedisSerializer等序列号方式都无效
     * 3. 如果希望在桌面工具里看到完整的 value 值，建议用 RedisUtil工具下的方法
     *
     * @param factory redis连接工厂
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        // 注：Jackson框架反序列化漏洞。攻击者可以远程获得网站控制权，安全风险高，虽然当前版本已修复但不建议使用。
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        jacksonSeial.setObjectMapper(om);

        // key的序列化采用StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        // value值的序列化采用fastJsonRedisSerializer
        template.setValueSerializer(jacksonSeial);
        template.setHashValueSerializer(jacksonSeial);
        template.afterPropertiesSet();

        return template;
    }


}
