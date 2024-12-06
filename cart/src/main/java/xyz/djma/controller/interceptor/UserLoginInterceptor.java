package xyz.djma.controller.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.djma.controller.Code;
import xyz.djma.controller.Result;
import xyz.djma.domain.User;
import xyz.djma.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class UserLoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Result result = new Result(Code.BUSINESS_ERR, null, "用户未登陆");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        // 获取session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            objectMapper.writeValue(response.getWriter(), result);
            return false;
        }
        // 获取用户对象
        User user = userService.getUserById(userId);
        if (user == null) {
            objectMapper.writeValue(response.getWriter(), result);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
