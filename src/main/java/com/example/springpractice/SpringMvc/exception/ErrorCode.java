package com.example.springpractice.SpringMvc.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  ID_MUST_MOR_THAN_0("10001", "회원 ID는 0보다 커야합니다.");
  private final String code;
  private final String description;

  public static ErrorCode findCode(String description) {
    for (ErrorCode errorCode : ErrorCode.values()) {
      if (errorCode.description.equals(description)) {
        return errorCode;
      }
    }
    throw new RuntimeException("에러 코드를 찾지 못했습니다.");
  }
}
