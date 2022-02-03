package com.example.smspring.springboot.config.auth;

import com.example.smspring.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    // 조건에 맞는 경우 메소드가 있다면 Resolver의 구현체가 지정한 값을 해당 메소드의 파라미터로 넘길 수 있다

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) { // 컨트롤러 메소드의 특정 파라미터를 지원하는지 판단
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null; // @LoginUser 어노테이션 확인
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType()); // 파라미터의 클래스 타입이 SessionUser.class인지 확인
        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // 파라미터에 전달할 객체 생성. 세션에서 객체 가져오기
        return httpSession.getAttribute("user");
    }
}
