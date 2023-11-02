package com.cbcoders.car_app_v1.Users.model;

import com.cbcoders.car_app_v1.Users.model.ENUMs.RoleEnum;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;
	private Integer code;

	public Role() {
	}

	public Role(RoleEnum code) {
		setCode(code);
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public RoleEnum getCode() {
		return RoleEnum.valueOf(code);
	}

	public void setCode(RoleEnum code) {
		if (code != null) {
			this.code = code.getCode();
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Role role)) return false;
		return Objects.equals(getRoleId(), role.getRoleId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getRoleId());
	}

	@Override
	public String toString() {
		return "Role{" +
				"roleId=" + roleId +
				", code=" + code +
				'}';
	}
}
