package com.cjw.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * spring security 配置
 * @author cjw
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	/**
	 * 配置用户认证
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("ccc")
			.password("ccc")
			.roles("ADIMN");
	}

	/**
	 * 请求授权
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable().headers().disable()
			.authorizeRequests()
			.antMatchers("/","/static/**","/film/**","/webSite/**","/webSiteInfo/**","/aboutMe").permitAll() //配置不需要认证的路径
			.anyRequest().authenticated() //其他路径需要认证
			.and()
			.formLogin()
			.loginPage("/login") //指定登陆请求地址
			.defaultSuccessUrl("/admin") //登陆成功的默认跳转页面
			.permitAll()
			.and()
			.logout()
			.logoutSuccessUrl("/login") //注销后跳转的页面
			.permitAll();
	}

	
}
