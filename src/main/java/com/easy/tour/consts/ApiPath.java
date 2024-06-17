package com.easy.tour.consts;

public interface ApiPath {
    /**
     * Swagger URL
     * ⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️⬇️
     * http://localhost:8080/swagger-ui/index.html#/
     * */

    String API = "/api/v1";
    //http://localhost:8080/api/v1


    //Ping
    String PING = API + "/ping";

    //User
    String USER_LOGIN = API + "/login";
    String USER_FORGOT_PASSWORD = API + "/forgot-password";

    String USER_GET_ALL = API + "/user/get-all";
    String USER_GET_UUID = API + "/user/{uuid}";
    String USER_CREATE = API + "/user/create";
    String USER_REGISTER = API + "/user/register";
    String USER_UPDATE_INFO = API + "/user/update";
    String USER_CHANGE_PASSWORD = API + "/user/change-password";
    String USER_DELETE = API + "/user/delete/{uuid}";


    //Price
    String PRICE_GET_All = API + "/price/get-all";
    String PRICE_GET_BY_TOUR_CODE = API + "/price/{tourCode}";
    String PRICE_CREATE = API + "/price/create-price";
    String PRICE_UPDATE = API + "/price/update/{tourCode}";
    String PRICE_DELETE = API + "/price/delete/{tourCode}";
    // end

    //Tour
    String TOUR_CREATE = API + "/tour/create-tour";
    String TOUR_GET_All = API + "/tour/get-all";
    String TOUR_GET_BY_TOUR_CODE = API + "/tour/{tourCode}";
    String TOUR_UPDATE = API + "/tour/update/{tourCode}";
    String TOUR_DELETE = API + "/tour/delete/{tourCode}";
    String TOUR_NON_PRICE_GET_ALL = API + "/tour/get-all-tour-no-price";
    String TOUR_GET_ALL_PRODUCT = API + "/tour/get-all-product";
    String TOUR_GET_All_FOR_BOOKING = API + "/tour/get-all-for-booking";

    //Tour request
    String TOUR_REQUEST_GET_All = API + "/tour-request/get-all";
    String TOUR_REQUEST_GET_BY_UUID = API + "/tour-request/{uuid}";
    String TOUR_REQUEST_CREATE = API + "/tour-request/create";
    String TOUR_REQUEST_UPDATE = API + "/tour-request/update/{uuid}";
    String TOUR_REQUEST_DELETE = API + "/tour-request/delete/{uuid}";

    //Departure Date
    String DEPARTURE_DATE_CREATE = API + "/tour/create-date";
    String TOUR_ONLY_GET_ALL = API +"/tour/get-all-tour-code";
    String DEPARTURE_DATE_GET_ALL = API +"/tour/create-date-get-all";

    //Order
    String ORDER_GET_All = API + "/order/get-all";
    String ORDER_GET_BY_ID = API + "/order/{id}";
    String ORDER_CREATE = API + "/order/create";
    String ORDER_UPDATE = API + "/order/update/{id}";
    String ORDER_DELETE = API + "/order/delete/{id}";

    //Refund Request
    String REFUND_REQUEST_GET_ALL = API + "/refund-request/get-all";
    String REFUND_REQUEST_GET_BY_ID = API + "/refund-requets/get-all-by-id";
    String REFUND_REQUEST_CREATE = API + "/refund-requets/create";
    String REFUND_REQUEST_UPDATE = API +"/refund-request/update/{id}";
    String REFUND_REQUEST_DELETE = API + "/refund-request/delete";

    //Management
    String REPORT_GET_ALL = API + "/management/get-all";
    String REPORT_PDF = API + "/jasperReport/exportPDF";
    String REPORT_GET_BY_MONTH = API + "/management/getByMonth";
}
