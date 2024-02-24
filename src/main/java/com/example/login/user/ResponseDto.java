package com.example.login.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto<T> {
    /** ResponseDto :: 상태코드, 메세지  */

    int status;
    T data;



    //private String error;
    //private List<T> data;
}


//HttpStatus status;  enum 타입