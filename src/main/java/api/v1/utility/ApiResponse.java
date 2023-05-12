package api.v1.utility;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private int code;
    private String message;
    private T body;

    public ApiResponse(int code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }
}
