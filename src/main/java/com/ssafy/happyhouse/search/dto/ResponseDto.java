package com.ssafy.happyhouse.search.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ResponseDto {
    @ApiModelProperty(notes = "상태 코드", example = "200", required = true)
    private HttpStatus status;
    @ApiModelProperty(notes = "상태 메시지", example = "성공", required = true)
    private String msg;
    @ApiModelProperty(notes = "반환 리스트", example = "200", required = false)
    private Object data;

    public ResponseDto(){
        this.status = HttpStatus.BAD_REQUEST;
        this.msg = null;
        this.data = null;
    }
}
