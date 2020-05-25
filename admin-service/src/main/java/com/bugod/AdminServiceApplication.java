package com.bugod;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *  参考文档：
 *      https://www.cnblogs.com/forezp/p/10242004.html
 *      https://codecentric.github.io/spring-boot-admin/2.2.3/#_what_is_spring_boot_admin
 *
 *
 */
@Configuration(proxyBeanMethods = false)
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableAdminServer
public class AdminServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }

    @Profile("insecure")
    @Configuration(proxyBeanMethods = false)
    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {

        private final String adminContextPath;

        public SecurityPermitAllConfig(AdminServerProperties adminServerProperties) {
            this.adminContextPath = adminServerProperties.getContextPath();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests((authorizeRequests) -> authorizeRequests.anyRequest().permitAll())
                    .csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                            .ignoringRequestMatchers(
                                    new AntPathRequestMatcher(this.adminContextPath + "/instances",
                                            HttpMethod.POST.toString()),
                                    new AntPathRequestMatcher(this.adminContextPath + "/instances/*",
                                            HttpMethod.DELETE.toString()),
                                    new AntPathRequestMatcher(this.adminContextPath + "/actuator/**")));

        }

    }

    @Profile("secure")
    @Configuration(proxyBeanMethods = false)
    public static class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

        private final String adminContextPath;

        public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
            this.adminContextPath = adminServerProperties.getContextPath();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
            successHandler.setTargetUrlParameter("redirectTo");
            successHandler.setDefaultTargetUrl(this.adminContextPath + "/");

            http.authorizeRequests((authorizeRequests) -> authorizeRequests
                    .antMatchers(this.adminContextPath + "/assets/**").permitAll()
                    .antMatchers(this.adminContextPath + "/login").permitAll().anyRequest().authenticated())
                    .formLogin((formLogin) -> formLogin.loginPage(this.adminContextPath + "/login")
                            .successHandler(successHandler))
                    .logout((logout) -> logout.logoutUrl(this.adminContextPath + "/logout"))
                    .httpBasic(Customizer.withDefaults())
                    .csrf((csrf) -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                            .ignoringRequestMatchers(
                                    new AntPathRequestMatcher(this.adminContextPath + "/instances",
                                            HttpMethod.POST.toString()),
                                    new AntPathRequestMatcher(this.adminContextPath + "/instances/*",
                                            HttpMethod.DELETE.toString()),
                                    new AntPathRequestMatcher(this.adminContextPath + "/actuator/**")));
        }

    }
}
