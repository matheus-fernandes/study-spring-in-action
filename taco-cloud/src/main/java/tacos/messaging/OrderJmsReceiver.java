package tacos.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tacos.constant.JmsMessagingConstants;
import tacos.entity.Order;

import java.util.concurrent.TimeUnit;

import static tacos.constant.JmsMessagingConstants.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderJmsReceiver {
    private final JmsTemplate jms;

    public Order receiveOrder(){
        return (Order) jms.receiveAndConvert(TACO_CLOUD_ORDER_QUEUE);
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.SECONDS)
    public void hourlyReceive(){
        Order order = receiveOrder();
        log.info("Order received by hourly task: {}", order.toString());
    }
}
