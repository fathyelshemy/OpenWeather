package com.weather.forcast.utilies;

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
}
