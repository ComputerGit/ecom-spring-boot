package com.at.t.eCommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Refresh_Token_Request {
	
	@NotBlank(message = "Refresh token cannot be blank")
    private String refreshToken;

    private String deviceId;   // Optional – for multi-device support
    private String ipAddress;  // Optional – for security logs

}
