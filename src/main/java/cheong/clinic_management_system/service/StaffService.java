package cheong.clinic_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cheong.clinic_management_system.entity.Staff;
import cheong.clinic_management_system.repository.StaffRepository;


@Service
@Transactional
public class StaffService implements IStaffService{

	private StaffRepository staffRepository;
	
	public StaffService(StaffRepository staffRepository) {
		this.staffRepository = staffRepository;
	}
	@Override
	public List<Staff> findAll() {
		return staffRepository.findAll();
	}

	@Override
	public Staff findById(String id) {
		return staffRepository.findById(id).orElseThrow();
	}

	@Override
	public void save(Staff staff) {
		staffRepository.save(staff);
	}

	@Override
	public void update(Staff staff) {
		staffRepository.save(staff);
	}

	@Override
	public void deleteById(String id) {
		staffRepository.deleteById(id);
	}

}
