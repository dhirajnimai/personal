package com.tollplaza.Tollplaza.entity;

import java.util.Date;
import java.util.HashMap;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AbstractModel {

	@CreatedBy
	private String createdBy;
	@LastModifiedBy
	private String updatedBy;
	
	@CreatedDate
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@CreatedDate
	private Long createdOnEpoch;
	
	@LastModifiedDate
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date updatedOn;
	
	@LastModifiedDate
	private Long updatedOnEpoch;
	
	public static void main(String[] args) {
		

	    HashMap<Integer,Integer> obj=new HashMap<>();
	   
			int[] array={7,2,8,5};
	    int target=9;
	   
	           
	        int i,search;
	        //System.out.println("The pairs having sum "+sum+" are");
	        for( i=0;i<array.length;i++){
	            search=target-array[i];
	            if(obj.containsValue(search)){
	                System.out.println(array[i]+" and "+search);
	            }
	            else
	            {
	                obj.put(i,array[i]);
	            }
	        }
		



		
	}
	
}
