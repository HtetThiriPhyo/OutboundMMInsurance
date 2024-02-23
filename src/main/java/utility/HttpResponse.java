package utility;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class HttpResponse<T> {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss", timezone = "Asia/Rangoon")
    private Date timeStamp;
    private int httpResponseCode;
    private HttpStatus httpResponseStatus;
    private String message;
    private T data;

    public HttpResponse(int httpResponseCode, HttpStatus httpResponseStatus, String message, T data) {
        this.timeStamp = new Date();
        this.httpResponseCode = httpResponseCode;
        this.httpResponseStatus = httpResponseStatus;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseEntity<HttpResponse<T>> createResponse(T data, HttpStatus httpStatus) {
        HttpResponse<T> body = new HttpResponse<>(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), data);
        return new ResponseEntity<>(body, httpStatus);
    }
    public static <T> ResponseEntity<HttpResponse<T>> createResponse(T data, HttpHeaders headers, HttpStatus httpStatus) {
        HttpResponse<T> body = new HttpResponse<>(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), data);
        return new ResponseEntity<>(body, headers, httpStatus);
    }

}

