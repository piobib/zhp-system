package pl.coderslab.zhpsystem.exceptions;

public class ObjectError {    private String fieldName;
    private String message;    public ObjectError(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }    public String getFieldName() {
        return fieldName;
    }    public String getMessage() {
        return message;
    }
}