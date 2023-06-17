package tacos.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderSubmitMessageHandler implements GenericHandler<EmailOrder> {
    @Override
    public Object handle(EmailOrder payload, MessageHeaders headers) {
        log.info(payload.toString());
        return null;
    }
}
