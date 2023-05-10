package tacos.messaging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import tacos.entity.Order;

import java.util.Map;


@Configuration
@EnableScheduling
public class MessagingConfig {
    @Bean
    public MappingJackson2MessageConverter messageConverter(){
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");
        messageConverter.setTypeIdMappings(Map.of("order", Order.class));
        return messageConverter;
    }
}
