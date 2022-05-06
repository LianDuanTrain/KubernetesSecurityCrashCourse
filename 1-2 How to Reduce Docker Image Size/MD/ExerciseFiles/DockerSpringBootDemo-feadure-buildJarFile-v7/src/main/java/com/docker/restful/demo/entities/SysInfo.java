package com.docker.restful.demo.entities;

import java.util.Map;

public class SysInfo {
private String ip;
private String hostName;
private Map sysENV;
public String getIp() {
	return ip;
}
public void setIp(String ip) {
	this.ip = ip;
}
public String getHostName() {
	return hostName;
}
public void setHostName(String hostname) {
	this.hostName = hostname;
}
public Map getSysENV() {
	return sysENV;
}
public void setSysENV(Map sysENV) {
	this.sysENV = sysENV;
}

}
