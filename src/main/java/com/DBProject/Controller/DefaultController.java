package com.DBProject.Controller;

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

    @RequestMapping(value={"/student*", "/student/*"}, method = {RequestMethod.GET})
    public ModelAndView getModelViewStudent(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return new ModelAndView("student/student_index");
    }

    @RequestMapping(value={"/ic*", "/ic/*"}, method = {RequestMethod.GET})
    public ModelAndView getModelViewIc(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return new ModelAndView("ic/ic_index");
    }

    @RequestMapping(value={"/company*", "/company/*"}, method = {RequestMethod.GET})
    public ModelAndView getModelViewCompany(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        return new ModelAndView("company/company_index");
    }

    @RequestMapping(value="views/*", method = {RequestMethod.GET})
    public ModelAndView getPage(final HttpServletRequest request, final HttpServletResponse response) {
        String servletPath = request.getServletPath();
        String page = servletPath.substring(servletPath.lastIndexOf("/") + 1);
        System.out.println(page);
        return new ModelAndView(page);
    }

    @RequestMapping(value="views/student/*", method = {RequestMethod.GET})
    public ModelAndView getPageStudent(final HttpServletRequest request, final HttpServletResponse response) {
        String servletPath = request.getServletPath();
        String page = servletPath.substring(servletPath.lastIndexOf("/") + 1);
        System.out.println(page);
        return new ModelAndView("student/"+page);
    }
}
