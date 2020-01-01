package me.xiezefan.easyim.server.resource.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import me.xiezefan.easyim.server.model.Timetable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseVo {
	public int start;
	public int week;
	public String describe;
	public int period ;
	
	
	public CourseVo(Timetable timetable) {
		this.start = timetable.getStart();
	    this.week = timetable.getWeek();
	    this.describe= timetable.getDescribe();
	    this.period= timetable.getPeriod();
	}


	public int getStart() {
		return start;
	}


	public void setStart(int start) {
		this.start = start;
	}


	public int getWeek() {
		return week;
	}


	public void setWeek(int week) {
		this.week = week;
	}


	public String getDescribe() {
		return describe;
	}


	public void setDescribe(String describe) {
		this.describe = describe;
	}


	public int getPeriod() {
		return period;
	}


	public void setPeriod(int period) {
		this.period = period;
	}


}
