package rs.ac.uns.ftn.xwsservice.mappers;

import rs.ac.uns.ftn.xwsservice.dto.response.ReviewerDTO;
import rs.ac.uns.ftn.xwsservice.dto.response.UserDTO;
import rs.ac.uns.ftn.xwsservice.dto.request.UserRegistrationDTO;
import rs.ac.uns.ftn.xwsservice.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    private UserMapper() {
    }

    public static UserDTO toDto(User user) {
        return new UserDTO(user);
    }

    public static List<UserDTO> toDtoList(List<User> users) {
        return users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
    }

    public static User toEntity(UserRegistrationDTO userInfo) {
        User user = new User();
        user.setUsername(userInfo.getUsername());
        user.setActivatedAccount(false);
        user.setEmail(userInfo.getEmail());
        user.setFirstName(userInfo.getFirstName());
        user.setLastName(userInfo.getLastName());
        user.setPassword("");

        return user;
    }

    public static List<ReviewerDTO> toReviewerDtoList(List<User> users) {
        return users.stream().map(ReviewerDTO::new).collect(Collectors.toList());
    }
}
