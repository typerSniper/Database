package com.DBProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jatin on 02/10/17.
 */

@Controller
public class DefaultController {
    @RequestMapping(value="/*", method = {RequestMethod.GET})
    public ModelAndView getModelView(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        System.out.println("here");
        return new ModelAndView("index");
    }

    @RequestMapping(value="views/*", method = {RequestMethod.GET})
    public ModelAndView getPage(final HttpServletRequest request, final HttpServletResponse response) {
        String servletPath = request.getServletPath();
        String page = servletPath.substring(servletPath.lastIndexOf("/")+1);
        System.out.println(page);
        return new ModelAndView(page);
    }



}
