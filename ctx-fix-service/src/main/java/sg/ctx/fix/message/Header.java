package sg.ctx.fix.message;



/**
 * @author yu.miao
 */
public class Header {
    private String beginString;
    private String msgType;

    public String getBeginString() {
        return beginString;
    }

    public void setBeginString(String beginString) {
        this.beginString = beginString;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
