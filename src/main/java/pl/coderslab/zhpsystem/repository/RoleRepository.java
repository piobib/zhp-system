package pl.coderslab.zhpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.coderslab.zhpsystem.entity.Role;
import pl.coderslab.zhpsystem.entity.User;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);

    @Query("select r from Role r")
    List<Role> findAllRoles();

    @Query(value = "select role_id from user_role where user_id=?1", nativeQuery = true)
    Integer findRoleById(Long id);
}
