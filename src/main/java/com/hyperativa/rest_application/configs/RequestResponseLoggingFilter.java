package com.hyperativa.rest_application.configs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestResponseLoggingFilter extends OncePerRequestFilter {
    private static final Logger logger = LogManager.getLogger(RequestResponseLoggingFilter.class);
    private static final int MAX_PAYLOAD_LENGTH = 4096;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        long start = System.currentTimeMillis();
        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            long duration = System.currentTimeMillis() - start;
            logRequest(wrappedRequest);
            logResponse(wrappedResponse, duration);
            wrappedResponse.copyBodyToResponse();
        }
    }

    private void logRequest(ContentCachingRequestWrapper request) {
        String payload = payloadAsString(request.getContentAsByteArray(), request.getCharacterEncoding());
        Map<String, String> headers = extractHeaders(request);
        logger.info("Incoming Request: method={} uri={} headers={} payload={}",
                request.getMethod(),
                request.getRequestURI() + queryString(request),
                headers,
                payload);
    }

    private void logResponse(ContentCachingResponseWrapper response, long duration) {
        String payload = payloadAsString(response.getContentAsByteArray(), response.getCharacterEncoding());
        logger.info("Outgoing Response: status={} payload={} durationMs={}",
                response.getStatus(),
                payload,
                duration);
    }

    private Map<String, String> extractHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> names = request.getHeaderNames();
        if (names == null) return map;
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            map.put(name, request.getHeader(name));
        }
        return map;
    }

    private String queryString(HttpServletRequest request) {
        String qs = request.getQueryString();
        return (qs == null || qs.isEmpty()) ? "" : "?" + qs;
    }

    private String payloadAsString(byte[] buf, String encoding) {
        if (buf == null || buf.length == 0) return "";
        int length = Math.min(buf.length, MAX_PAYLOAD_LENGTH);
        try {
            Charset charset = (encoding != null) ? Charset.forName(encoding) : Charset.defaultCharset();
            String payload = new String(buf, 0, length, charset);
            if (buf.length > MAX_PAYLOAD_LENGTH) {
                payload += " ...(truncated, totalBytes=" + buf.length + ")";
            }
            return payload;
        } catch (Exception e) {
            return "[unknown payload: " + e.getMessage() + "]";
        }
    }
}