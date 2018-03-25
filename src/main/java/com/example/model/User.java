package com.example.model;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.persistence.*;
import java.beans.Transient;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String email;
	private Timestamp dayOfBirth;
	private byte[] image;
	private String base64;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Timestamp dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getBase64() {
		return Base64.encode(image);
	}

	@Transient
	public void setBase64(String base64) {
		this.base64 = base64;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", dayOfBirth=" + dayOfBirth +
				'}';
	}
}
