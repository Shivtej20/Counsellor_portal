package com.example.counsellorPortal.entity;

import java.time.LocalDate;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Enquiry_Table")
@Getter
@Setter
public class Enquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer enquiryId;
	private String stu_Name;
	private Long stu_Phno;
	private String Mode;
	private String course;
	private String status;
	@CreationTimestamp
	private LocalDate created_Date;
	@UpdateTimestamp
	private LocalDate updated_Date;

	@ManyToOne
	@JoinColumn(name = "userId") // foreign key = column key
	private User user;

}
