package com.ain.engine.jms;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

import java.util.HashMap;
import java.util.Map;

import javax.jms.*;

@Log4j2
@Component
public class MsgReceiver implements MessageListener {

    @Getter
    private Map<String, Object> engines = new HashMap<String, Object>();

    public MsgReceiver() throws Exception {
        log.info("--------> Msg Receiver loaded !!!!! <----------");

    }

    public void onMessage(Message message) {
        log.info("==================== Wrong msg! " + message);

        engines.forEach((k, v) -> log.info("engine list => " + k + "=" + v));

        if (message instanceof BytesMessage) {
            try {
                BytesMessage bytesMsg = (BytesMessage) message;
                int totSize = (int)bytesMsg.getBodyLength();
                byte[] buffer = new byte[totSize];
                bytesMsg.readBytes(buffer);
                log.info("on byte message ~~~~~~~~~~~~~~");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else if (message instanceof TextMessage) {
            try {
                TextMessage txtMsg = (TextMessage) message;
                String txt = txtMsg.getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public void receiveMessage(String message) {
        log.info("Message Received:" + message );
    }
}
