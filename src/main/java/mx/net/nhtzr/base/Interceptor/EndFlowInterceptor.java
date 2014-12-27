package mx.net.nhtzr.base.Interceptor;

import mx.net.nhtzr.base.Annotation.EndsFlow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Invalida la session despues de responder una peticion a la cual la
 * Aplicacion considera como ultima. Es decir, una peticion de la cual
 * se puede asumir que el cliente no planea continuar interactuar con el portal
 * entrar en el portal.
 */
public class EndFlowInterceptor extends HandlerInterceptorAdapter {

    Logger log = LoggerFactory.getLogger(EndFlowInterceptor.class);

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler.getClass().isAnnotationPresent(EndsFlow.class)) {
            request.getSession().invalidate();
        }
        super.postHandle(request, response, handler, modelAndView);
    }

}