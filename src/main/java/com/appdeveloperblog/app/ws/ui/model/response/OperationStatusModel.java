package com.appdeveloperblog.app.ws.ui.model.response;

import java.util.Date;

public abstract class OperationStatusModel<T> {
  private final Date timestamp;
  private final String status;

  public OperationStatusModel(String status) {
    this.status = status;
    timestamp = new Date();
  }

  public String getStatus() {
    return this.status;
  }

  public Date getTimestamp() {
    return timestamp;
  }
}
