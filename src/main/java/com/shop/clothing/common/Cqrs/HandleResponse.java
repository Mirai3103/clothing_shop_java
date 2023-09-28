package com.shop.clothing.common.Cqrs;

import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
public class HandleResponse<T>{
    private T data= null;
    @Getter
    private String error;

    public  T get(){
        return data;
    }
    public boolean isOk(){
        return error == null || error.isEmpty();
    }

    public boolean hasError(){
        return error != null && !error.isEmpty();
    }
    public static <T>HandleResponse<T> ok( T data){
        return HandleResponse.<T>builder().data(data).build();
    }
    public static <T>HandleResponse<T> ok( ){
        return HandleResponse.<T>builder().build();
    }
    public static <T>HandleResponse<T> error(String error){
        return HandleResponse.<T>builder().error(error).build();
    }
}
