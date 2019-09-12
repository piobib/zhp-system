package pl.coderslab.zhpsystem.service;


import pl.coderslab.zhpsystem.DTO.ChildrenDTO;
import pl.coderslab.zhpsystem.DTO.ChildrenTokenDTO;
import pl.coderslab.zhpsystem.DTO.UserDTO;
import pl.coderslab.zhpsystem.entity.ChildrenToken;

import java.util.List;

public interface ChildrenService {

    List<ChildrenDTO> getAllChildren();

    ChildrenDTO findByChildrenId(Long id);

    void updateChildren(ChildrenDTO childrenDTO);

    List<ChildrenTokenDTO> findTokensByChildrenId(Long id);

    void createNewToken(Long id);

    boolean checkToken(String pesel, String token, Long userId);
}
