package com.marketdataanalytics.controller.response;

import com.marketdataanalytics.controller.response.base.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResponseOfItem<T> extends BaseResponse {

    @NonNull
    private T value;
}