package com.fachrizal.shoppingmall.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataResponse<T> {
    private T data;
}
