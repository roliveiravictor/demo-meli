package stonetree.com.mercadolivre.core.model;

public class Error {

    private int code;
    private String message;

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public char getToastMessage(){
        final String messageStr = this.getMessage();
        return messageStr.charAt(0);
    }

}
