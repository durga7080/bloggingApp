package com.blog.blogapp.payload;

public class JwtAuthResponse {

	private String Token;

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public JwtAuthResponse(String token) {
		super();
		Token = token;
	}

	public JwtAuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
