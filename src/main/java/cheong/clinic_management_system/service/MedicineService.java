package cheong.clinic_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cheong.clinic_management_system.entity.Medicine;
import cheong.clinic_management_system.repository.MedicineRepository;



@Service
@Transactional
public class MedicineService implements IMedicineService{

	private MedicineRepository medicineRepository;
	
	public MedicineService(MedicineRepository medicineRepository) {
		this.medicineRepository = medicineRepository;
	}
	@Override
	@Transactional(readOnly = true)
	public List<Medicine> findAll() {
		return medicineRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Medicine findById(String id) {
		return medicineRepository.findById(id).orElseThrow();
	}

	@Override
	public void save(Medicine medicine) {
		medicineRepository.save(medicine);
		
	}

	@Override
	public void update(Medicine medicine) {
		medicineRepository.save(medicine);
		
	}

	@Override
	public void deleteById(String id) {
		medicineRepository.deleteById(id);
	}

}
