package com.hmall.interceptor;

import com.hmall.common.utils.UserContext;
import com.hmall.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    // 白名单路径
    private static final String[] WHITE_LIST = {
        "/api/auth/**",
        "/health",
        "/actuator/**",
        "/swagger-ui/**",
        "/v3/api-docs/**"
    };
    private final JwtTool jwtTool;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        // 1. 白名单检查
        if (isWhiteList(requestURI)) {
            log.debug("白名单请求直接放行: {} {}", method, requestURI);
            return true;
        }
        // 2. 静态资源检查
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        try {
            // 1.获取请求头中的 token
            String token = request.getHeader("authorization");
            // 2. 校验并解析 token，获取用户ID
            Long userId = jwtTool.parseToken(token);
            // 3.存入上下文: 将用户ID写入上下文，便于后续获取
            UserContext.setUser(userId);
            // 4.放行
            return true;


        } catch (Exception e) {
            log.error("拦截器处理异常，请求: {} {}", method, requestURI, e);
            return handleInternalError(response);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清理用户上下文，防止内存泄漏
        UserContext.removeUser();

        if (ex!=null) {
            log.error("请求处理异常: {} {}", request.getMethod(), request.getRequestURI(), ex);
        }
    }

    private boolean isWhiteList(String requestURI) {
        return Arrays.stream(WHITE_LIST)
            .anyMatch(pattern -> pathMatcher.match(pattern, requestURI));
    }

    private boolean handleInternalError(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String jsonResponse = "{\"code\":500,\"message\":\"服务器内部错误\"}";
        response.getWriter().write(jsonResponse);
        return false;
    }
}
