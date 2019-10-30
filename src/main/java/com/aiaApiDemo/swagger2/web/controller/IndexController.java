package com.aiaApiDemo.swagger2.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "首頁", description = "登入首頁")
@RequestMapping("/")
@RestController
public class IndexController {
	
    @ApiOperation(value = "Swagger RESTful APIs", notes = "Swagger RESTful APIs")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "AIA Swagger RESTful APIs Demon";
    }
    
    @ApiOperation(value = "swagger首頁", notes = "API 接口列表")
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public void swagger(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui.html"); // 導頁
    }
}