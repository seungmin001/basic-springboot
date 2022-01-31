package com.example.smspring.springboot.config.auth;

import com.example.smspring.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                // h2-console 화면을 사용하기 위해 해당 옵션 disable
                .and()
                    .authorizeRequests()
                    // URL별 권한 관리 설정 옵션 시작점. antMatchers 사용 전 필수
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() // 모두 접근 가능
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //USER 권한만 가능
                    // 권한 관리 대상 지정 옵션. URL HTTP Method별 관리 가능.
                    .anyRequest().authenticated()
                    // 설정된 값 이외 나머지에 대해 인증된 사용자만 접근가능.
                .and()
                    .logout()
                    // 로그아웃 기능 설정 진입점
                        .logoutSuccessUrl("/")
                        //로그아웃 성공 시 / 주소 이동
                .and()
                    .oauth2Login()
                    //oauth2 로그인 기능 설정 진입점
                        .userInfoEndpoint()
                        // 로그인 성공 이후 사용자 정보 가져올 때 설정 담당
                            .userService(customOAuth2UserService);
                            // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스 구현체 등록. 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시가능.
    }
}