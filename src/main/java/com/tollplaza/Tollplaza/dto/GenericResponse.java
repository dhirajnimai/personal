package com.tollplaza.Tollplaza.dto;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
public class GenericResponse<E> implements Serializable {
	private String errMessage;
	private E data;
	private float totalEarning;
	private List list;
	private String errCode ;
	private String status;
	
	
	
	
	
}
