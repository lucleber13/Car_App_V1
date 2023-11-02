package com.cbcoders.car_app_v1.Users.model.ENUMs;

public enum RoleEnum {
	SUPERADMIN(1),
	ADMIN(2),
	MANAGER(3),
	SALESPERSON(4),
	WORKSHOP(5),
	VALETER(6);

	private final int code;

	RoleEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static RoleEnum valueOf(int code) {
		for (RoleEnum roleEnum : RoleEnum.values()) {
			if (roleEnum.getCode() == code) {
				return roleEnum;
			}
		}
		throw new IllegalArgumentException("Invalid Role Code");
	}
}
