package com.welab.k8s_backend_post.common.web.context;

import com.welab.k8s_backend_post.common.exception.NotFound;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class GatewayRequestHeaderUtils {
    public static String getRequestHeaderParamAsString(String key) {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return requestAttributes.getRequest().getHeader(key);
    }
    public static String getUserId() {
        String userId = getRequestHeaderParamAsString("X-Auth-UserId");
        if (userId == null) {
            return null;
        }
        return userId;
    }
    public static String getClientDevice() {
        String clientDevice = getRequestHeaderParamAsString("X-Client-Device");
        if (clientDevice == null) {
            return null;
        }
        return clientDevice;
    }
    public static String getClientAddress() {
        String clientAddress = getRequestHeaderParamAsString("X-Client-Address");
        if (clientAddress == null) {
            return null;
        }
        return clientAddress;
    }
    public static String getUserIdOrThrowException() {
        String userId = getUserId();
        System.out.println(userId);
        if (userId == null) {
            throw new NotFound("헤더에 userId 정보가 없습니다.");
        }
        return userId;
    }
    public static String getClientDeviceOrThrowException() {
        String clientDevice = getClientDevice();
        if (clientDevice == null) {
            throw new NotFound("헤더에 사용자 디바이스 정보가 없습니다.");}
        return clientDevice;
    }
    public static String getClientAddressOrThrowException() {
        String clientAddress = getClientAddress();
        if (clientAddress == null) {
            throw new NotFound("헤더에 사용자IP 주소 정보가 없습니다.");}
        return clientAddress;
    }
}