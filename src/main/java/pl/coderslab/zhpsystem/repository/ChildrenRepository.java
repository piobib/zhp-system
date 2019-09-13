package pl.coderslab.zhpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.zhpsystem.entity.Children;
import pl.coderslab.zhpsystem.entity.ChildrenToken;
import pl.coderslab.zhpsystem.entity.User;

import java.util.List;

@Repository
public interface ChildrenRepository extends JpaRepository<Children, Long> {

    Children findChildrenById(Long id);

    @Query("select c from Children c")
    List<Children> findAllChildren();

    @Query("select c from Children c where c.pesel=?1")
    Children findChildrenByPesel(String pesel);

    @Query(value = "select c.* from children as c JOIN user_children as u ON c.id=u.children_id where u.user_id=?1", nativeQuery = true)
    List<Children> findAllChildrenByParentId(Long user);

}
