package com.tollplaza.Tollplaza.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="toll_user_role")
public class TollplazaUserRole extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int tollUsrRoleId;
	
 @JsonBackReference	
@ManyToOne(fetch =FetchType.EAGER)
private TollMUser user;
@ManyToOne
private TollMRole role;
private String status;


	
	
	
	
}
