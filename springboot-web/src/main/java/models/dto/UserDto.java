package models.dto;

import lombok.Getter;
import lombok.Setter;
import models.User;

@Getter
@Setter
public class UserDto {
    private String title;
    private User user;
}
