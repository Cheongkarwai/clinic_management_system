package cheong.clinic_management_system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cheong.clinic_management_system.entity.User;


public interface IUserRepository extends JpaRepository<User, String> {

}
