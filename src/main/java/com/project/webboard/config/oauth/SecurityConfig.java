package com.project.webboard.config.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@RequiredArgsConstructor
//@EnableWebSecurity : 스프링 시큐리티 설정들을 활성화함
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().disable().headers().frameOptions().disable() : h2-console 화면을 사용하기 위해
                .csrf().disable()
                .headers().frameOptions().disable()

                // .authorizeRequests() : URL별 권한 관리를 설정하는 옵션의 시작점, 이게 있어야지 antMatchers옵션 사용 가능
                // antMatchers : 권한 관리 대상을 지정하는 옵션, URL&HTTP 메소드별로 관리가능
                // anyRequest : 설정된 값들 이외 나머지 URL //authenticated() : 로그인한 사용자들만 접근 가능하도록 함
                .and()
                .authorizeRequests()
                .antMatchers("/home","/notice","/detailnotice", "/groups").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
//                    .anyRequest().authenticated()


                //.logout().logoutSuccessUrl("/") : 로그아웃 시 성공 주소
                .and()
                .logout()
                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")


                // oauth2Login : OAuth2 로그인 기능에 대한 여러 설정의 진입점
                // userInfoEndpoint : OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들
                // .userService(customOAuth2UserService); 소셜 로그인 성공 후 후속 조치를 진핼 할 UserService인터페이스의 구현체 등록 -> 로그인 이후 사용자의 정보를 가져온 상태에서 추가로 진행하고자하는 기능을 명시
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/home")
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }

}
