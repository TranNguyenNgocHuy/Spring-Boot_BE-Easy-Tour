package com.easy.tour.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;


@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class UserDTO extends BaseObject {

    private String uuid;

    private String lastName;

    private String firstName;

    private String email;

    private String password;

    private String newPassword;

    private String confirmNewPassword;

    private Boolean gender;

    private Long phoneNumber;

    private Set<String> roles;

    private String accessToken;

    private String avatarImg;
}
