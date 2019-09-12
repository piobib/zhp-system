package pl.coderslab.zhpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.zhpsystem.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("select u from User u")
    List<User> findAllUsers();

   // @Query("select u from User u where u.id = ?1")
    //User findUserById(Long id);
    User findUserById(Long id);

}