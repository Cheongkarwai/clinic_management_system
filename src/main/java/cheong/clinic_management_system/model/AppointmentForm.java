package cheong.clinic_management_system.model;

import java.time.LocalDateTime;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class AppointmentForm {
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@NotNull(message="Start date time is required")
	@FutureOrPresent(message="Date time must be present or future")
	private LocalDateTime startDateTime;
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@NotNull(message="End date time is required")
	@FutureOrPresent(message="Date time must be present or future")
	private LocalDateTime endDateTime;
	
	@NotEmpty(message="Patient id is required")
	private String patientId;
	
	@NotEmpty(message="Doctor id is required")
	private String doctorId;
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}
	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

}
