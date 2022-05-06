package com.docker.restful.demo.controller;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docker.restful.demo.entities.SysInfo;
import com.docker.restful.demo.services.SysInfoService;

@RestController

public class SysInfoResource {
	Logger log = LoggerFactory.getLogger(SysInfoResource.class);
	@Autowired
	private SysInfoService sysInfoService;

	   
	@RequestMapping("/sys")
    @CrossOrigin
    public Response getInfo() {
    	log.info("getInfo start");
    	SysInfo sysInfo  = sysInfoService.getSysInfo();
    	log.info("getInfo end");
		return Response.status(200).entity(sysInfo).build();
	}

}
