package shop.mtcoding.blog._core.errors;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.mtcoding.blog._core.errors.exception.*;

// RuntimeException이 터지면 해당 파일로 오류가 모인다
@Slf4j // 테스트 코드에 절대쓰면 안됨! 지원안함
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public String ex400(Exception400 e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        log.warn("400 : "+e.getMessage()); // 워닝이라고 한다 400이 찍힘, 유효성 검사 실패
        return "err/400";
    }
    @ExceptionHandler(Exception401.class)
    public String ex401(Exception401 e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        log.warn("401 : "+e.getMessage());
        log.warn("IP : "+request.getRemoteAddr());
        log.warn("IP : "+request.getHeader("User-Agent"));
        return "err/401";
    }
    @ExceptionHandler(Exception403.class)
    public String ex403(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        log.warn("403 : "+e.getMessage());
        // 사실 관리자에게 sms 전송해줘야 함, 한 번 올 때마다 전송되는게 아니라 누적3번 되면 할 수있겠끔
        // 로직을 짜주는게 좋음

        return "err/403";
    }
    @ExceptionHandler(Exception404.class)
    public String ex404(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        log.info("404 : "+e.getMessage()); // 404 요청이 있었다는 정도
        return "err/404";
    }
    @ExceptionHandler(Exception500.class)
    public String ex500(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        log.error("500 : "+e.getMessage());
        return "err/500";
    }
}
