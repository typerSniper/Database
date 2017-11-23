package com.DBProject.Controller;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class DefaultController {
    @RequestMapping(value="/*", method = {RequestMethod.GET})
    public ModelAndView getModelView(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return new ModelAndView("site/index");
    }

    @RequestMapping(value={"/site/*", "/site/**"}, method = {RequestMethod.GET})
    public ModelAndView getModelViewSite(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        String servletPath = request.getServletPath();
        String page = servletPath.split("/")[2];
        return new ModelAndView("site/"+page);
    }

    @RequestMapping(value={"/site", "/site/"}, method = {RequestMethod.GET})
    public ModelAndView getViewSite(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        response.sendRedirect("/");
        return null;
    }

    @RequestMapping(value = "/404", method = {RequestMethod.GET})
    public ModelAndView send404 (final HttpServletRequest request) {
        return new ModelAndView("404");
    }

    @RequestMapping(value={"/student*", "/student/*"}, method = {RequestMethod.GET})
    public ModelAndView getModelViewStudent(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return new ModelAndView("student/student_index");
    }

    @RequestMapping(value={"/coordinator*", "/coordinator/*"}, method = {RequestMethod.GET})
    public ModelAndView getModelViewIc(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return new ModelAndView("ic/ic_index");
    }

    @RequestMapping(value={"/recruiter*", "/recruiter/*"}, method = {RequestMethod.GET})
    public ModelAndView getModelViewCompany(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return new ModelAndView("company/company_index");
    }

    @RequestMapping(value="views/**", method = {RequestMethod.GET})
    public ModelAndView getPage(final HttpServletRequest request, final HttpServletResponse response) {
        String servletPath = request.getServletPath();
        String folder = servletPath.split("/")[2];
        String page = servletPath.split("/")[3];
        System.out.println(folder+"/"+page);
        // String page = servletPath.substring(servletPath.lastIndexOf("/") + 1);
        return new ModelAndView(folder+"/"+page);
    }


    public static String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
