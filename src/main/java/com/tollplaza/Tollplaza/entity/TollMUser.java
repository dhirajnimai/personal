package com.tollplaza.Tollplaza.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
@Entity
@Table(name="toll_m_user")
public class TollMUser extends AbstractModel {
	
	@Id
	private String userId;
	private String firstName;
	private String lastName;
	private String phone;
	private String emailId;
	private String companyName;
	private String password;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "userId")
	private List<CompanyMasterLoginEntity> loginList;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "user")
	@JsonIgnore
	private Set<TollplazaUserRole> userRoles=new HashSet<>();
	
	

}
