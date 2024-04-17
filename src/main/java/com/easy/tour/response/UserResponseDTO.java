package com.easy.tour.response;

import com.easy.tour.dto.UserDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserResponseDTO extends ResponseDTO<UserDTO> {
    private String accessToken;
    private String tokenType;
}
