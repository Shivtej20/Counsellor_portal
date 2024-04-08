package com.example.counsellorPortal.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
//@Getter
//@Setter
public class Dashboard {

	private Long totalEnquiries;
	private Long openEnquiries;
	private Long enrolledEnquiries;
	private Long lostEnquiries;

}
