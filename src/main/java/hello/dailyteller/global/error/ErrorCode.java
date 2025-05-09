package hello.dailyteller.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    // COMMON
    INVALID_CODE(HttpStatus.BAD_REQUEST, "C001", "Invalid Code"),
    RESOURCE_NOT_FOUND(HttpStatus.NO_CONTENT, "C002", "Resource not found"),
    EXPIRED_CODE(HttpStatus.BAD_REQUEST, "C003", "Expired Code"),

    // AWS
    AWS_ERROR(HttpStatus.BAD_REQUEST, "A001", "aws client error");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

}
