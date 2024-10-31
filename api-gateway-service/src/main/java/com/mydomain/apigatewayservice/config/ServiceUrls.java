package com.mydomain.apigatewayservice.config;

public class ServiceUrls {
    public static String ALL = "/**";
    public static String LB = "lb://";
    public static String EXCHANGE_ROUTE = "/currency-exchange";
    public static String CONVERSION_ROUTE_FEIGN= "/currency-conversion-feign";
    public static String CONVERSION_ROUTE_NEW= "/currency-conversion-new";
    public static String CONVERSION_ROUTE = "/currency-conversion";
    public static String EXCHANGE_SERVICE = "currency-exchange-service";
    public static String CONVERSION_SERVICE = "currency-conversion-service";
}
