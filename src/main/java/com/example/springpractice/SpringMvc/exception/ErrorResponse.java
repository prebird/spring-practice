package com.example.springpractice.SpringMvc.exception;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Access;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ErrorResponse {
  private final String errorCode;
  private final LocalDateTime dateTime;
  private final UUID logId;

  public static ErrorResponse of(UUID logId, Exception exception) {
    return ErrorResponse.builder()
        .errorCode(exception.getMessage())
        .dateTime(LocalDateTime.now())
        .logId(logId)
        .build();
  }
}
