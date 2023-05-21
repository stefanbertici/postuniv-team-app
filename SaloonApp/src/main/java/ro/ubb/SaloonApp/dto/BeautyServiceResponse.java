package ro.ubb.SaloonApp.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BeautyServiceResponse {
    private Set<BeautyServiceDto> beautyServices;
    private String errorMessage;
}
