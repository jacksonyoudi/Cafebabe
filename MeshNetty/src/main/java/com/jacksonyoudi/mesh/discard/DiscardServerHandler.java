package com.jacksonyoudi.mesh.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @program: Cafebabe
 * @description:
 * @author: changyouliang
 * @date: 2021/09/28
 **/
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //  super.channelRead(ctx, msg);
        System.out.println("helo");
        ((ByteBuf) msg).release(); // 释放，丢弃掉

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印异常，关闭
        cause.printStackTrace();
        ctx.close();
    }
}
