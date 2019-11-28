package com.weather.forcast.interceptors;

import java.io.IOException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import com.weather.forcast.utilies.Constants;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

	private final Logger logger =LoggerFactory.getLogger(this.getClass());

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		logRequest(request, body);
		ClientHttpResponse httpResponse= execution.execute(request, body);
		logResponse(httpResponse);
		return httpResponse;
	}

	private void logRequest(HttpRequest request, byte[] body) throws IOException {
		logger.info(Constants.START_REMOTE_REQUEST);
		logger.info("URI			:{}", request.getURI().toString());
		logger.info("Method			:{}", request.getMethod().toString());
		logger.info("Headers		:{}", request.getHeaders().toString());
		logger.info("Request body	:{}", new String(body,"UTF-8").toString());
		logger.info(Constants.END_REMOTE_REQUEST);
	}

	private void logResponse(ClientHttpResponse response) throws IOException {
		logger.info(Constants.START_REMOTE_RESPONSE);
		logger.info("Status code	:{}", response.getStatusCode().toString());
		logger.info("Status text	:{}", response.getStatusText().toString());
		logger.info("Headers		:{}", response.getHeaders().toString());
		logger.info("Response body	:{}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
		logger.info(Constants.END_REMOTE_RESPONSE);
	}
}
