package sample.com.webservice.common.service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.com.webservice.common.data.MovieResponseData;

/**
 * Created by anfer on 24-Sep-18.
 */

public class CallResponse implements Callback {

    /**
     * callBackSvc
     */
    private CallBackInterface callBackSvc;

    /**
     * responseObj
     */
    private MovieResponseData responseObj;

    /**
     * CallResponse
     *
     * @param callBackInterface
     * @param response
     */
    public CallResponse(CallBackInterface callBackInterface, MovieResponseData response) {
        callBackSvc = callBackInterface;
        responseObj = response;
    }

    @Override
    public void onResponse(Call call, Response response) {
        if (response.isSuccessful()) {
            if (response.body() != null) {
                responseObj = (MovieResponseData) response.body();
                responseObj.setSuccess(true);
            } else {
                responseObj.setSuccess(false);
                responseObj.setErrorMessage("Error in response");
            }
        } else {
            int status = response.code();
            switch (status) {
                case 404:
                    responseObj.setErrorMessage("webservice not found");
                    break;
                case 500:
                    responseObj.setErrorMessage("internal server error");
                    break;
                case 503:
                    responseObj.setErrorMessage("service unavailable");
                    break;
                default:
                    responseObj.setErrorMessage("unknown error");
                    break;
            }
        }
        callBackSvc.onCallBack(responseObj);
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Exception e = (Exception) t;
        e.printStackTrace();
        responseObj.setSuccess(false);
        responseObj.setErrorMessage(e.getMessage());
        callBackSvc.onCallBack(responseObj);
    }
}
