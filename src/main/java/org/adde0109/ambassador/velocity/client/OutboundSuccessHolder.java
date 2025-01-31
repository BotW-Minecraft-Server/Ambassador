package org.adde0109.ambassador.velocity.client;

import com.velocitypowered.proxy.protocol.packet.ServerLoginSuccess;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class OutboundSuccessHolder extends ChannelOutboundHandlerAdapter {

  private ServerLoginSuccess packet;
  private ChannelHandlerContext ctx;

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    this.ctx = ctx;
  }

  @Override
  public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
    if ((msg instanceof ServerLoginSuccess packet)) {
      this.packet = packet;
    } else {
      ctx.write(msg, promise);
    }
  }

  public void sendPacket() {
    ctx.write(packet, ctx.voidPromise());
    ctx.flush();
  }
}
