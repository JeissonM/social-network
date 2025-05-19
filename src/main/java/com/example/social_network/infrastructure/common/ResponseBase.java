package com.example.social_network.infrastructure.common;

public class ResponseBase<T> {

    private int error;
    private String errorMessage;
    private int httpStatusCode;
    private T data;

    public ResponseBase() {}

    public ResponseBase(int error, String errorMessage, int httpStatusCode, T data) {
        this.error = error;
        this.errorMessage = errorMessage;
        this.httpStatusCode = httpStatusCode;
        this.data = data;
    }

    // éxito con datos
    public static <T> ResponseBase<T> success(T data, int httpStatusCode, String message) {
        return new ResponseBase<>(0, message, httpStatusCode, data);
    }

    // error sin datos
    public static <T> ResponseBase<T> error(String errorMessage, int httpStatusCode) {
        return new ResponseBase<>(1, errorMessage, httpStatusCode, null);
    }

    // error con datos (como mapa de errores)
    public static <T> ResponseBase<T> error(String errorMessage, int httpStatusCode, T data) {
        return new ResponseBase<>(1, errorMessage, httpStatusCode, data);
    }

    // Getters y Setters
    public int getError() { return error; }
    public void setError(int error) { this.error = error; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public int getHttpStatusCode() { return httpStatusCode; }
    public void setHttpStatusCode(int httpStatusCode) { this.httpStatusCode = httpStatusCode; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}
