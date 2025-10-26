package com.sil.digitalbankingbackend.response;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Response {

    private boolean success;
    private String message;
    private Object payload;
    private HttpStatus status;
    private String token;

    // Constructor
    public Response(HttpStatus status, boolean success, String message, Object payload) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.payload = payload;
    }
    public Response(HttpStatus status, boolean success, String message,String token, Object payload) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.payload = payload;
        this.token = token;
    }

    // Static helper methods for convenience
    public static ResponseEntity<Response> getResponseData(boolean success, String message, Object payload) {
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new Response(status, success, message, payload), status);
    }

    public static ResponseEntity<Response> getResponseData(HttpStatus status, String message, Object payload) {
        boolean success = status.is2xxSuccessful();
        return new ResponseEntity<>(new Response(status, success, message, payload), status);
    }
    public static ResponseEntity<Response> getResponseData(HttpStatus status,String message, String token, Object payload) {
        boolean success = status.is2xxSuccessful();
        return new ResponseEntity<>(new Response(status, success, message,token, payload), status);
    }

    // Getters & Setters
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
