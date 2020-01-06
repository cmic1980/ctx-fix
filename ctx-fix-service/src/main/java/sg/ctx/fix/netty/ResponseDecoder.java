package sg.ctx.fix.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author yu.miao
 */
public class ResponseDecoder extends ByteToMessageDecoder {
    private static Logger logger = LoggerFactory.getLogger(ResponseDecoder.class);
    private short length = -1;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        var content = in.toString(CharsetUtil.UTF_8);

        logger.info("Fix in message:" + content);
    }
}
