package io.github.kimmking.gateway.filter.impl;

import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author Zhangtianle
 */
public class HttpRequestFilterImpl implements HttpRequestFilter {

    private String name;

    public HttpRequestFilterImpl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void filter(FullHttpRequest request, ChannelHandlerContext ctx) {
        request.headers().add("nio", name);
    }
}
