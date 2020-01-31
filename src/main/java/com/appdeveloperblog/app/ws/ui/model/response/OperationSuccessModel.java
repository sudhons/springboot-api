package com.appdeveloperblog.app.ws.ui.model.response;

public class OperationSuccessModel<T> extends OperationStatusModel<T> {
  private final T data;

  public OperationSuccessModel() {
    this(null);
  }

  public OperationSuccessModel(T data) {
    super(RequestOperationStatus.SUCCESS.name());
    this.data = data;
  }

  public T getData() {
    return data;
  }
}
