package com.example.finalprojectgroup8;

public class ResponseDTO {

    public Boolean success;

    public String msg;

    public ResponseDTO(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public ResponseDTO() {
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    @Override
    public String toString() {
        return "ResponseDTO{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                '}';
    }
}

