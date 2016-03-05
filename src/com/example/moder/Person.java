package com.example.moder;

import java.util.List;

public class Person {
private String name;
private String url;
private String age;
private List<SchooleInfo>schooleInfos;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
public List<SchooleInfo> getSchooleInfos() {
	return schooleInfos;
}
public void setSchooleInfos(List<SchooleInfo> schooleInfos) {
	this.schooleInfos = schooleInfos;
}

}
