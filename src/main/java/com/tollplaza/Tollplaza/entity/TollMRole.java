package com.tollplaza.Tollplaza.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="toll_m_role")
public class TollMRole extends AbstractModel {
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	private String rollName;
	private String rollShortName;
	private String status;
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "role")
	private Set<TollplazaUserRole> userRoles=new HashSet<>();

}
