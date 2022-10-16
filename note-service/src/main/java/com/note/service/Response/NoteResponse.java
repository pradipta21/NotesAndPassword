package com.note.service.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class NoteResponse {

    String responseMessage;
    String id;
    Object result;

    public NoteResponse() {
    }

    public NoteResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public NoteResponse(String responseMessage, String id) {
        this.responseMessage = responseMessage;
        this.id = id;
        this.result = null;
    }

    public NoteResponse(String responseMessage, String id, Object result) {
        this.responseMessage = responseMessage;
        this.id = id;
        this.result = result;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private String getJsonString(Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
