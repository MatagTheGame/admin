package com.matag.admin.exception;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;

@Value
@JsonDeserialize(builder = ErrorResponse.ErrorResponseBuilder.class)
@Builder
public class ErrorResponse {
  String error;

  @JsonPOJOBuilder(withPrefix = "")
  public static class ErrorResponseBuilder {

  }
}
