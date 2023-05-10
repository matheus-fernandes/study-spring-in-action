package tacos.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import tacos.entity.Order;

import javax.jms.JMSException;
import javax.jms.Message;

import static tacos.constant.JmsMessagingConstants.*;

@Service
@RequiredArgsConstructor
public class OrderJmsSender {

    private final JmsTemplate jms;

    public void sendOrder(Order order){
        jms.convertAndSend(TACO_CLOUD_ORDER_QUEUE, order, this::addOrderSource);
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty(ORDER_SOURCE_PROPERTY, ORDER_SOURCE_VALUE_WEB);
        return message;
    }
}
