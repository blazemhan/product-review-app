package com.blazemhan.productreviewapp.utils;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimiterFilter extends OncePerRequestFilter {
        private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

        private Bucket resolveBucket(String ip) {
            return cache.computeIfAbsent(ip, k ->
                    Bucket4j.builder()
                            .addLimit(Bandwidth.classic(10, Refill.greedy(10, Duration.ofMinutes(1))))
                            .build()
            );
        }
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

            String ip = request.getRemoteAddr();
            Bucket bucket = resolveBucket(ip);
            if (bucket.tryConsume(1)) {
                filterChain.doFilter(request, response);

            } else {
                response.setStatus(429);
                response.getWriter().write("Rate limit exceeded");
            }
        }
    }




