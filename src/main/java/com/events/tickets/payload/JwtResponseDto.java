package com.events.tickets.payload;

public class JwtResponseDto {
	private String accessToken;
	private String tokenType;

	public JwtResponseDto(String accessToken) {
		super();
		this.accessToken = accessToken;
		this.tokenType = "Bearer";
	}

	public JwtResponseDto() {
		super();
		this.tokenType = "Bearer";
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
}
