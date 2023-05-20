package com.cheong.clinic.medicine_dispensing.model;
import java.util.LinkedList;
import java.util.List;


public class MedicineForm {

	private List<MedicineAndCheck> medicineList = new LinkedList<>();

	public List<MedicineAndCheck> getMedicineList() {
		return medicineList;
	}
	public void setMedicineList(List<MedicineAndCheck> medicines) {
		this.medicineList = medicines;
	}

	

}
