/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.filtros;

import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis.silva
 */
public class Myfilte implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        String LoginURI = request.getContextPath() + "/faces/login.xhtml";

        boolean LoggedIn = session != null && session.getAttribute("user") != null;
        boolean LoginRequest = request.getRequestURI().equals(LoginURI);
        boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath()
                + ResourceHandler.RESOURCE_IDENTIFIER);

        if (LoggedIn || LoginRequest || resourceRequest) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(LoginURI);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig arg0) {

    }
}
