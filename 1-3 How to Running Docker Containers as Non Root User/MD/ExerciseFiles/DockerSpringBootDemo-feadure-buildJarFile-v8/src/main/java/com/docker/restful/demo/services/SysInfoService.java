package com.docker.restful.demo.services;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.docker.restful.demo.controller.SysInfoResource;
import com.docker.restful.demo.entities.SysInfo;

@Service
public class SysInfoService {
	Logger log = LoggerFactory.getLogger(SysInfoResource.class);
	public SysInfo getSysInfo() {
		log.info("getSysInfo start");
		SysInfo sysInfo = new SysInfo();
		InetAddress ip;

		String hostname;
		try {
			ip = InetAddress.getLocalHost();
			hostname = ip.getHostName();
			log.debug("Your current IP address : " + ip);
			log.debug("Your current Hostname : " + hostname);
			
			sysInfo.setIp(ip.toString());
			sysInfo.setHostName(hostname);
			Map map = System.getenv();
			Iterator it = map.entrySet().iterator();
			sysInfo.setSysENV(map);
			
			while(it.hasNext())
			{
				Entry entry = (Entry)it.next();
				log.debug(entry.getKey()+"=");
				log.debug(entry.getValue().toString());
			}
			
		} catch (UnknownHostException e) {
			log.error("ERROR : "+e);
			e.printStackTrace();
		}
		log.info("getSysInfo end");
		return sysInfo;
		
	}
}
