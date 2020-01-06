package sg.ctx.fix;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quickfix.fix44.Logon;
import sg.ctx.fix.netty.ResponseDecoder;

import java.time.LocalDateTime;

/**
 * @author yu.miao
 */
public class FixClient {
    private static Logger logger = LoggerFactory.getLogger(FixClient.class);

    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(workerGroup);
        b.channel(NioSocketChannel.class);
        b.option(ChannelOption.SO_KEEPALIVE, true);
        b.handler(new ResponseDecoder());
        ChannelFuture future = null;
        try {
            future = b.connect("192.168.100.4", 21501).sync();
            future.addListener(s -> {
                if (s.isSuccess()) {
                    System.out.println("isSuccess: " + s.isSuccess());
                } else {
                    System.out.println("Unsuccess ...");
                }
            });
            var channel = future.channel();


            Logon logon = new Logon();
            var header = logon.getHeader();

            header.setField(new quickfix.field.BeginString("FIXT.1.1"));
            header.setField(new quickfix.field.MsgType("A"));
            header.setField(new quickfix.field.SenderCompID("FIRM01"));
            header.setField(new quickfix.field.TargetCompID("CTX"));
            // header.setField(new quickfix.field.MsgSeqNum(201));
            header.setInt(34, 361);
            header.setField(new quickfix.field.SendingTime(LocalDateTime.now()));

            logon.set(new quickfix.field.EncryptMethod(0));
            logon.set(new quickfix.field.HeartBtInt(60));
            logon.setInt(1137, 8);
            // user name
            // logon.setString(553,"FIRM01FC01");
            logon.setString(553, "FIRM01FC01");
            logon.setString(554, "password");

            var logonContent = logon.toString();
            logger.info("Fix out message:: " + logonContent);
            ByteBuf buffer = Unpooled.copiedBuffer(logonContent, CharsetUtil.UTF_8);

            future.channel().writeAndFlush(buffer);


            /*
            NewOrderSingle orderSingle = new NewOrderSingle();
            header = orderSingle.getHeader();
            header.setField(new quickfix.field.BeginString("FIXT.1.1"));
            header.setField(new quickfix.field.MsgType("A"));
            header.setField(new quickfix.field.SenderCompID("FIRM01"));
            header.setField(new quickfix.field.TargetCompID("CTX"));
            // header.setField(new quickfix.field.MsgSeqNum(201));
            header.setInt(34, 342);
            header.setField(new quickfix.field.SendingTime(LocalDateTime.now()));

            orderSingle.set(new ClOrdID("202001030000000002"));
            orderSingle.set(new Account("FIRM01001"));
            orderSingle.set(new OrderQty(1));
            orderSingle.set(new OrdType('2'));
            orderSingle.set(new Side('1'));
            orderSingle.set(new TransactTime(LocalDateTime.now()));

            var orderContent = orderSingle.toString();
            logger.info("Fix out message:: " + orderContent);
            buffer = Unpooled.copiedBuffer(orderContent, CharsetUtil.UTF_8);
            future.channel().writeAndFlush(buffer);
            */

            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
