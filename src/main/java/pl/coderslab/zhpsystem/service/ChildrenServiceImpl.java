package pl.coderslab.zhpsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.zhpsystem.DTO.ChildrenDTO;
import pl.coderslab.zhpsystem.entity.Children;
import pl.coderslab.zhpsystem.repository.ChildrenRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class ChildrenServiceImpl implements ChildrenService{

    private final ChildrenRepository childrenRepository;

    public ChildrenServiceImpl(ChildrenRepository childrenRepository) {
        this.childrenRepository = childrenRepository;
    }

    @Override
    public List<ChildrenDTO> getAllChildren() {
        List<Children> childrenList = childrenRepository.findAllChildren();
        List<ChildrenDTO> childrenListDto = new ArrayList<>();
        for (int i = 0; i < childrenList.size(); i++) {
            ChildrenDTO currentChildren = new ChildrenDTO();
            currentChildren.setId(childrenList.get(i).getId());
            currentChildren.setFirstName(childrenList.get(i).getFirstName());
            currentChildren.setLastName(childrenList.get(i).getLastName());
            currentChildren.setPhone(childrenList.get(i).getPhone());

            childrenListDto.add(currentChildren);
        }
        return childrenListDto;
    }

}
