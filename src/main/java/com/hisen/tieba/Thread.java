package com.hisen.tieba;

public class Thread {
	private int num;
	private String title;
	private String content;
	private String time;
	private BaiduPostBar bar;
	public Thread(int num, String title, String content, String time,
			BaiduPostBar bar) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.time = time;
		this.bar = bar;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public BaiduPostBar getBar() {
		return bar;
	}
	public void setBar(BaiduPostBar bar) {
		this.bar = bar;
	}
	@Override
	public String toString() {
		return "\t"+num+"\n\t标题："+title+"\n\t内容："+content+"\n\t跟帖时间："+time;
	}
	
}
