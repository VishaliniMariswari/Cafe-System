package com.vishalini.cafe.POJO;

import java.io.Serializable;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

// @data provides default Zero Arg Constructor, Default Constructor , getters & setters to our class

// NOTE : Only during first run table will get created if we stop the pgm & run again we can't see 
//table getting created only if we drop the table and run again table gets created 

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="contactNumber")
	private String contactNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="status")
	private String status;
	
	@Column(name="role")
	private String role;
}
