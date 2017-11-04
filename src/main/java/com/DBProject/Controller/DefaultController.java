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
        return new ModelAndView("index");
    }

    @RequestMapping(value={"/recruit*", "/recruit/*"}, method = {RequestMethod.GET})
    public ModelAndView getModelViewRecruit(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return new ModelAndView("whyrecruit");
    }

    @RequestMapping(value={"/procedure*", "/procedure/*"}, method = {RequestMethod.GET})
    public ModelAndView getModelViewProcedure(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return new ModelAndView("procedures");
    }

    @RequestMapping(value={"/department*", "/department/*"}, method = {RequestMethod.GET})
    public ModelAndView getModelViewDepartments(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return new ModelAndView("departments");
    }

    @RequestMapping(value={"/aboutus*", "/aboutus/*"}, method = {RequestMethod.GET})
    public ModelAndView getModelViewAboutUs(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return new ModelAndView("aboutus");
    }

    @RequestMapping(value = "/404", method = {RequestMethod.GET})
    public ModelAndView send404 (final HttpServletRequest request) {
        return new ModelAndView("404");
    }

    @RequestMapping(value={"/student*", "/student/*"}, method = {RequestMethod.GET})
    public ModelAndView getModelViewStudent(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
//        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ModelAndView("student_index");
    }

    @RequestMapping(value={"/coordinator*", "/coordinator/*"}, method = {RequestMethod.GET})
    public ModelAndView getModelViewIc(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return new ModelAndView("ic_index");
    }

    @RequestMapping(value={"/company*", "/company/*"}, method = {RequestMethod.GET})
    public ModelAndView getModelViewCompany(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return new ModelAndView("company_index");
    }

    @RequestMapping(value="views/*", method = {RequestMethod.GET})
    public ModelAndView getPage(final HttpServletRequest request, final HttpServletResponse response) {
        String servletPath = request.getServletPath();
        String page = servletPath.substring(servletPath.lastIndexOf("/") + 1);
        return new ModelAndView(page);
    }

    public static boolean isAnonymous() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
    }


    public static String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
