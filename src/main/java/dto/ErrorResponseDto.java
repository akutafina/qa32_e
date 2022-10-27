package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class ErrorResponseDto {
    String code;
    String details;
    String message;
    String timestamp;
}
