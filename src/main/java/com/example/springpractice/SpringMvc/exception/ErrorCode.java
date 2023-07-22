package com.example.springpractice.SpringMvc.exception;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
  ID_MUST_MOR_THAN_0("10001", "회원 ID는 0보다 커야합니다.");
  private final String code;
  private final String description;
  private static final Map<String, ErrorCode> ERROR_CODE_MAP = new HashMap<>();

  static {
    for (ErrorCode errorCode : ErrorCode.values()) {
      ERROR_CODE_MAP.put(errorCode.description, errorCode);
    }
  }

  public static ErrorCode findCode(String description) {
    return ERROR_CODE_MAP.get(description);
  }
}
