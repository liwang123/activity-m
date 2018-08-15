package org.trustnote.activity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.trustnote.activity.common.utils.Md5Util;

/**
 * @author zhuxl
 */
@Configuration
@EnableWebSecurity(debug = true)
public class ZxlWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ZxlUserDetailsService zxlUserDetailsService;
    @Autowired
    private ZxlSecurityMetadataSource zxlSecurityMetadataSource;
    @Autowired
    private ZxlAccessDecisionManager zxlAccessDecisionManager;
    @Autowired
    private ZxlAccessDeniedServletHandler zxlAccessDeniedServletHandler;
    @Autowired
    private ZxlAuthenticationEntryPoint zxlAuthenticationEntryPoint;
    @Autowired
    private ZxlAuthenticationSuccessHandler zxlAuthenticationSuccessHandler;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.zxlUserDetailsService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(final CharSequence charSequence) {
                return Md5Util.getMd5Code((String) charSequence);
            }

            @Override
            public boolean matches(final CharSequence charSequence, final String s) {
                return s.equals(Md5Util.getMd5ByObjAndSalt((String) charSequence));
            }
        });
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/user/logout").permitAll()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(final O o) {
                        o.setSecurityMetadataSource(ZxlWebSecurityConfig.this.zxlSecurityMetadataSource);
                        o.setAccessDecisionManager(ZxlWebSecurityConfig.this.zxlAccessDecisionManager);
                        return o;
                    }
                })
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .successHandler(this.zxlAuthenticationSuccessHandler)
                .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                .csrf().disable();
        http.exceptionHandling()
                .authenticationEntryPoint(this.zxlAuthenticationEntryPoint)
                .accessDeniedHandler(this.zxlAccessDeniedServletHandler);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public ZxlAuthenticationSuccessHandler zxlSuccessHandler() {
        return new ZxlAuthenticationSuccessHandler();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler zxlFailHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }

}
