package com.asamart.model;

public class JwtResponse {
	private String jwtToken;
	private String username;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// Builder pattern
	public static class JwtResponseBuilder {
		private String jwtToken;
		private String username;

		public JwtResponseBuilder jwtToken(String jwtToken) {
			this.jwtToken = jwtToken;
			return this;
		}

		public JwtResponseBuilder username(String username) {
			this.username = username;
			return this;
		}

		public JwtResponse build() {
			JwtResponse response = new JwtResponse();
			response.setJwtToken(this.jwtToken);
			response.setUsername(this.username);
			return response;
		}
	}

	public static JwtResponseBuilder builder() {
		return new JwtResponseBuilder();
	}

	// Other methods...
}
