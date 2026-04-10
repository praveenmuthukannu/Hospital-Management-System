package com.example.hospital.hospitalDto;

import lombok.Data;


@Data
public class PatientAppointmentDto {
     private Long id;
	 private String patientname;
	    private String date;
	    private String time;
	    private String reason;
	    private String status; 
	   

		public PatientAppointmentDto(Long id,String patientname, String date, String time, String reason, String status) {
			super();
			this.id = id;
			this.patientname = patientname;
			this.date = date;
			this.time = time;
			this.reason = reason;
			this.status = status;
		}
	    
	    
}
