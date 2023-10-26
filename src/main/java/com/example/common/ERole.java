package com.example.common;

public enum ERole {

	Admin("Role_Admin"),
	User("Role_User");

	public static int adminRole = 1;
	public static int userRole = 2;

	private final String NAME;

	ERole(String NAME) {
		this.NAME = NAME;
	}

	@Override
	public String toString() {
		return NAME;
	}
}
