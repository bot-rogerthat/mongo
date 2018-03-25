package com.example.model;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.Transient;
import java.util.Date;

@Document
public class User {
	@Id
	private ObjectId id;
	@Indexed
	private String name;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dayOfBirth;
	private byte[] image;
	private String base64;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
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

	public Date getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(Date dayOfBirth) {
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
