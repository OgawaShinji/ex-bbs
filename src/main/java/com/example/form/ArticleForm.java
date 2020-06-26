package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ArticleForm {

	@NotBlank(message = "名前が空白です")
	@Size(min = 1, max = 50, message = "名前は1文字以上50文字以下で入力してください")
	private String name;
	@NotBlank(message = "内容が空白です")
	@Size(min = 1, max = 100, message = "内容は1文字以上100文字以下で入力してください")
	private String content;

	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
