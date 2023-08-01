package com.wo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wo
 * @date 2023/7/29 22:57
 * @description 响应结果类
 */
@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class R<T> {
    /**
     * 响应码
     */
    private Integer code;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data ;
    /**
     * 操作成功。
     * @param message 响应消息
     * @return 200状态码
     * @param <T> 泛型
     */
    public static <T> R<T> ok(String message){
        return create(200,message,null);
    }
    /**
     * 操作成功。
     * @param message 响应消息
     * @param data 响应数据
     * @return 200状态码
     * @param <T> 泛型
     */
    public static <T> R<T> ok(String message, T data){
        return create(200,message,data);
    }
    /**
     * 生成了新的资源。
     * @param message 响应消息
     * @return 201状态码
     * @param <T> 泛型
     */
    public static <T> R<T> created(String message){
        return create(201,message,null);
    }
    /**
     * 生成了新的资源。
     * @param message 响应消息
     * @param data 响应数据
     * @return 201状态码
     * @param <T> 泛型
     */
    public static <T> R<T> created(String message, T data){
        return create(201,message,data);
    }
    /**
     * 资源已经不存在。
     * @param message 响应消息
     * @return 204状态码
     * @param <T> 泛型
     */
    public static <T> R<T> noContent(String message){
        return create(204,message,null);
    }
    /**
     * 服务器不理解客户端的请求，未做任何处理。
     * @param message 响应消息
     * @return 400状态码
     * @param <T> 泛型
     */
    public static <T> R<T> badRequest(String message){
        return create(400,message,null);
    }
    /**
     * 用户未提供身份验证凭据，或者没有通过身份验证。
     * @param message 响应消息
     * @return 401状态码
     * @param <T> 泛型
     */
    public static <T> R<T> unauthorized(String message){
        return create(401,message,null);
    }
    /**
     * 用户通过了身份验证，但是不具有访问资源所需的权限。
     * @param message 响应消息
     * @return 403状态码
     * @param <T> 泛型
     */
    public static <T> R<T> forbidden(String message){
        return create(403,message,null);
    }
    /**
     * 所请求的资源不存在，或不可用。
     * @param message 响应消息
     * @return 404状态码
     * @param <T> 泛型
     */
    public static <T> R<T> notFound(String message){
        return create(404,message,null);
    }
    /**
     * 用户已经通过身份验证，但是所用的 HTTP 方法不在他的权限之内。
     * @param message 响应消息
     * @return 405状态码
     * @param <T> 泛型
     */
    public static <T> R<T> methodNotAllowed(String message){
        return create(405,message,null);
    }
    /**
     * 所请求的资源已从这个地址转移，不再可用。
     * @param message 响应消息
     * @return 410状态码
     * @param <T> 泛型
     */
    public static <T> R<T> gone(String message){
        return create(410,message,null);
    }
    /**
     * 客户端要求的返回格式不支持。比如，API 只能返回 JSON 格式，但是客户端要求返回 XML 格式。
     * @param message 响应消息
     * @return 415状态码
     * @param <T> 泛型
     */
    public static <T> R<T> unsupportedMediaType(String message){
        return create(415,message,null);
    }
    /**
     * 客户端上传的附件无法处理，导致请求失败。
     * @param message 响应消息
     * @return 422状态码
     * @param <T> 泛型
     */
    public static <T> R<T> unprocessableEntity(String message){
        return create(422,message,null);
    }
    /**
     * 客户端的请求次数超过限额。
     * @param message 响应消息
     * @return 429状态码
     * @param <T> 泛型
     */
    public static <T> R<T> tooManyRequests(String message){
        return create(429,message,null);
    }
    /**
     * 客户端请求有效，服务器处理时发生了意外。
     * @param message 响应消息
     * @return 500状态码
     * @param <T> 泛型
     */
    public static <T> R<T> internalServerError(String message){
        return create(500,message,null);
    }
    /**
     * 服务器无法处理请求，一般用于网站维护状态。
     * @param message 响应消息
     * @return 503状态码
     * @param <T> 泛型
     */
    public static <T> R<T> serviceUnavailable(String message){
        return create(503,message,null);
    }
}
