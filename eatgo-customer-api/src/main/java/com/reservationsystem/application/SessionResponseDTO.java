package com.reservationsystem.application;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponseDTO {

    private String accessToken;
}
