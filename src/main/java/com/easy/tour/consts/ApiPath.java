package com.easy.tour.consts;

public interface ApiPath {

    String API = "/api/v1";
    //http://localhost:8080/api/v1


    //Ping
    String PING = API + "/ping";

    //User
    String USER_LOGIN = API + "/login";
    String USER_GET_ALL = API + "/user/get-all";
    String USER_GET_UUID = API + "/user/get-by-uuid";
    String USER_REGISTER = API + "/user/register";
    String USER_UPDATE = API + "/user/update";
    String USER_DELETE = API + "/user/delete";


    //Price

    String PRICE_GET_All = API + "/price/get-all";
    String PRICE_CREATE = API + "/price/create-price";
    String PRICE_UPDATE = API + "/price/{tourCode}";
    String PRICE_DELETE = API + "/price/{tourCode}";
    // end
}
