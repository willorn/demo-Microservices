package com.hmall.common.utils;

/**
 * @author tianyi
 */
public class UserContext {
    
    // 异步任务最佳实践：阿里巴巴的  https://github.com/alibaba/transmittable-thread-local
    private static final ThreadLocal<Long> tl = new ThreadLocal<>();

    /**
     * 保存当前登录用户信息到ThreadLocal
     * @param userId 用户id
     */
    public static void setUser(Long userId) {
        tl.set(userId);
    }

    /**
     * 获取当前登录用户信息
     * @return 用户id
     */
    public static Long getUser() {
        return tl.get();
    }

    /**
     * 移除当前登录用户信息
     */
    public static void removeUser(){
        tl.remove();
    }
}
