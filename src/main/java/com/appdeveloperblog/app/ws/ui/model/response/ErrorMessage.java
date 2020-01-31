package com.appdeveloperblog.app.ws.ui.model.response;

import java.util.Date;

public class ErrorMessage {
  private final Date timestamp;
  private final String status = RequestOperationStatus.FAILED.name();
  private final String message;

  public ErrorMessage(Date timestamp, String message) {
    this.timestamp = timestamp;
    this.message = message;
  }

  public Date getTimestamp() {
    return this.timestamp;
  }

  public String getStatus() {
    return this.status;
  }

  public String getMessage() {
    return this.message;
  }
}
