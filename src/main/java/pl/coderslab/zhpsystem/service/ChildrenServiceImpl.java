package pl.coderslab.zhpsystem.service;

import org.springframework.stereotype.Service;
import pl.coderslab.zhpsystem.DTO.ChildrenDTO;
import pl.coderslab.zhpsystem.DTO.ChildrenTokenDTO;
import pl.coderslab.zhpsystem.entity.Children;
import pl.coderslab.zhpsystem.entity.ChildrenToken;
import pl.coderslab.zhpsystem.entity.User;
import pl.coderslab.zhpsystem.repository.ChildrenRepository;
import pl.coderslab.zhpsystem.repository.ChildrenTokenRepository;
import pl.coderslab.zhpsystem.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
@Service
public class ChildrenServiceImpl implements ChildrenService{

    private final ChildrenRepository childrenRepository;
    private final UserRepository userRepository;
    private final ChildrenTokenRepository childrenTokenRepository;

    public ChildrenServiceImpl(ChildrenRepository childrenRepository, UserRepository userRepository, ChildrenTokenRepository childrenTokenRepository) {
        this.childrenRepository = childrenRepository;
        this.userRepository = userRepository;
        this.childrenTokenRepository = childrenTokenRepository;
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


    @Override
    public ChildrenDTO findByChildrenId(Long id) {
        Children children = childrenRepository.findChildrenById(id);
        ChildrenDTO editingChildren = new ChildrenDTO();

        editingChildren.setId(children.getId());
        editingChildren.setFirstName(children.getFirstName());
        editingChildren.setLastName(children.getLastName());
        editingChildren.setPhone(children.getPhone());
        editingChildren.setPesel(children.getPesel());
        editingChildren.setCity(children.getCity());
        editingChildren.setPostCode(children.getPostCode());
        editingChildren.setStreet(children.getStreet());
        editingChildren.setEvidenceNumber(children.getEvidenceNumber());
        editingChildren.setActive(children.getActive());

        return editingChildren;
    }

    @Override
    public void updateChildren(ChildrenDTO childrenDto){

        Children currentChildren = childrenRepository.findChildrenById(childrenDto.getId());

        currentChildren.setId(childrenDto.getId());
        currentChildren.setFirstName(childrenDto.getFirstName());
        currentChildren.setLastName(childrenDto.getLastName());
        currentChildren.setPhone(childrenDto.getPhone());
        currentChildren.setPesel(childrenDto.getPesel());
        currentChildren.setCity(childrenDto.getCity());
        currentChildren.setPostCode(childrenDto.getPostCode());
        currentChildren.setStreet(childrenDto.getStreet());
        currentChildren.setEvidenceNumber(childrenDto.getEvidenceNumber());
        currentChildren.setActive(childrenDto.getActive());
        childrenRepository.save(currentChildren);

    }

    @Override
    public List<ChildrenTokenDTO> findTokensByChildrenId(Long id){
        List<ChildrenToken> childrenTokenList = childrenTokenRepository.findAllTokensByChildren(id);
        List<ChildrenTokenDTO> childrenTokenListDto = new ArrayList<>();
        for (int i = 0; i < childrenTokenList.size(); i++) {
            ChildrenTokenDTO currentToken = new ChildrenTokenDTO();
            currentToken.setId(childrenTokenList.get(i).getId());
            currentToken.setToken(childrenTokenList.get(i).getToken());
            currentToken.setActive(childrenTokenList.get(i).getActive());
            currentToken.setCreated(childrenTokenList.get(i).getCreated());
            childrenTokenListDto.add(currentToken);
        }

        return childrenTokenListDto;

    }
    @Override
    public void createNewToken(Long id){

        String token = randomString(2)+""+id*3+""+randomString(3)+""+id*5;
        ChildrenToken childrenToken = new ChildrenToken();
        Children children = childrenRepository.findChildrenById(id);
        childrenToken.setToken(token);
        childrenToken.setActive(1);
        childrenToken.setChildren(children);
        childrenTokenRepository.save(childrenToken);

    }
    @Override
    public boolean checkToken(String pesel, String token, Long userId){

       Children children = new Children();
        try {
            children = childrenRepository.findChildrenByPesel(pesel);
            ChildrenToken childrenToken = new ChildrenToken();
            try {
                childrenToken = childrenTokenRepository.findTokenIdByTokenAndChildren(token, children);

                if(childrenToken.getChildren().getId().equals(children.getId())&&childrenToken.getActive()==1)
                {
                    User user = new User();
                    user = userRepository.findUserById(userId);

                    user.getChilds().add(children);
                    children.getUsers().add(user);

                    userRepository.save(user);
                    childrenToken.setActive(0);
                    childrenTokenRepository.save(childrenToken);
                    return true;
                }
                return false;

            } catch (Exception e) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }


    }

    @Override
    public List<ChildrenDTO> getAllChildrenByParentId(Long loggedUser){

        List<Children> childrenList = childrenRepository.findAllChildrenByParentId(loggedUser);
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

    private static String randomString(int len)
    {
        char[] str = new char[100];

        for (int i = 0; i < len; i++)
        {
            str[i] = (char) (((int)(Math.random() * 26)) + (int)'A');
        }

        return (new String(str, 0, len));
    }

}
