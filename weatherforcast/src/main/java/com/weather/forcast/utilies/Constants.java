package com.weather.forcast.utilies;

import java.util.Date;

public final class Constants {
	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 864_000_000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	public static final String CLASS_NAME="info: in Class \t ";
	public static final String METHOD_NAME=" Method Name is: \t";
	public static final String START_REMOTE_REQUEST="=======================remote request ========================= ";
	public static final String END_REMOTE_REQUEST="=======================end request =========================";
	public static final String START_REMOTE_RESPONSE="=======================remote response ========================= ";
	public static final String END_REMOTE_RESPONSE="=======================end response ========================= ";
	public static final String END_METHOD="=================================================================================================";
	public static final String METHOD_ARGUMENTS="Method Arguments: \t";
	public static final String METHOD_RETURN="Method outcome /t";
	public static final String REQUEST_PARAM="request params ";
	public static final String QUERY_PARAM="q:";
	public static final String APPID="\t APPID: ";
	public static final String UNITES="\t units: ";
	public static final String LOCAL_WEATHER_NOTE="WeatherNote from DB: ";
	public static final String DEGREE_LESS_THAN_10="you should wear very heavy clothes it's snow";
	public static final String DEGREE_BETWEEN_10_AND_15="you should wear heavy clothes, it's rain ";
	public static final String DEGREE_BETWEEN_15_AND_20="it's fine for parking";
	public static final String DEGREE_MORE_THAN_20="it's hot";
	
	public static final String NOT_FOUND_JWT_TOKEN="Unable to get JWT Token";
	public static final String JWT_TOKEN_EXPIRED="JWT Token has expired";
	public static final String BEARER_ERROR="JWT Token does not begin with Bearer String";
	public static final String Authorization="Authorization";
	public static final String message="message";
	public static final String GENERIC_ERROR_MESSAGE="Unable to process this request.";
	public static final String USER_NOT_FOUND="your mail is not found";
	
	public static final String INCOMING_REQUEST="========================= inComing request at :"+new Date().toGMTString()+"====================";
	public static final String OUTGOING_RESPONSE="========================= outGoing response at :"+new Date().toGMTString()+"====================";
	
}
