package cheong.clinic_management_system.dto;

import java.time.LocalDateTime;


public class AppointmentDTO {
	
	private int id;

	private LocalDateTime appointmentStartDateTime;
	
	private LocalDateTime appointmentEndDateTime;
	
	private LocalDateTime dateTimeCreated;
	
	private boolean status;
	
	public AppointmentDTO(int id, LocalDateTime appointmentStartDateTime, LocalDateTime appointmentEndDateTime,
			LocalDateTime dateTimeCreated, boolean status) {
		this.id = id;
		this.appointmentStartDateTime = appointmentStartDateTime;
		this.appointmentEndDateTime = appointmentEndDateTime;
		this.dateTimeCreated = dateTimeCreated;
		this.status = status;
	}

}
