package com.ciklum.tuitechtest.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponse(@JsonProperty("status") int statusCode, @JsonProperty("Message") String message) {
}
