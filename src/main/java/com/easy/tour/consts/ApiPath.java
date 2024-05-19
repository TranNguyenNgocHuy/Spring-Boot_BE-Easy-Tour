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
    String USER_UPDATE = API + "/user/update";
    String USER_DELETE = API + "/user/delete";


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
}
