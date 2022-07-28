package com.edufire.dic3;

import com.edufire.dic3.Models.APIResponse;

public interface OnFetchDataListener {
    void onFetchData(APIResponse apiResponse,String message);
    void onError(String message);
}
