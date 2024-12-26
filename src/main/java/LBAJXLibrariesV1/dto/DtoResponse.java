package LBAJXLibrariesV1.dto;

import java.util.List;

public class DtoResponse {
    private String status;
    private List data;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DtoResponse(String status, List data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
