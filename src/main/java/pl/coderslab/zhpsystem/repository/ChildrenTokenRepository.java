package pl.coderslab.zhpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.zhpsystem.entity.Children;
import pl.coderslab.zhpsystem.entity.ChildrenToken;
import java.util.List;

@Repository
public interface ChildrenTokenRepository  extends JpaRepository<ChildrenToken, Long> {


    @Query(value = "select * from children_token where children_id=?1", nativeQuery = true)
    List<ChildrenToken> findAllTokensByChildren(Long id);

    @Query("select t from ChildrenToken t where t.token=?1 and t.children=?2")
    ChildrenToken findTokenIdByTokenAndChildren(String token, Children children);

    @Query(value = "insert into user_children ('children_id', 'user_id') values (1, 2)", nativeQuery = true)
    void addChildrenToParent(Long childrenId, Long userId);
}
