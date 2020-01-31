package com.appdeveloperblog.app.ws.ui.model.response;

public class OperationFailedModel<T> extends OperationStatusModel<T> {
  private final T error;

  public OperationFailedModel(T error) {
    super(RequestOperationStatus.FAILED.name());
    this.error = error;
  }

  public T getError() {
    return error;
  }
}
