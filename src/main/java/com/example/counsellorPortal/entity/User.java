package com.example.counsellorPortal.entity;

import java.time.LocalDate;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Counsellor_Table")
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String name;
	private String email;
	private String pwd;
	private Long phno;
	@CreationTimestamp
	private LocalDate created_Date;
	@UpdateTimestamp
	private LocalDate updated_Date;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Enquiry> enquiries;

}
