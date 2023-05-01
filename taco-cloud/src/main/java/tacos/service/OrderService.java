package tacos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tacos.entity.Order;
import tacos.repository.OrderRepository;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final TacoService tacoService;
    private final OrderRepository orderRepository;

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    public Order patchOrder(Long id, Order order){
        Order updatedOrder = orderRepository.findById(id)
                .map((previousOrder) -> {
                    setIfNotNull(order::getDeliveryName, previousOrder::setDeliveryName);
                    setIfNotNull(order::getDeliveryStreet, previousOrder::setDeliveryStreet);
                    setIfNotNull(order::getDeliveryCity, previousOrder::setDeliveryCity);
                    setIfNotNull(order::getDeliveryState, previousOrder::setDeliveryState);
                    setIfNotNull(order::getDeliveryZip, previousOrder::setDeliveryZip);
                    setIfNotNull(order::getCcNumber, previousOrder::setCcNumber);
                    setIfNotNull(order::getCcExpiration, previousOrder::setCcExpiration);
                    setIfNotNull(order::getCcCVV, previousOrder::setCcCVV);

                    return previousOrder;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return orderRepository.save(updatedOrder);
    }

    private <T> void setIfNotNull(Supplier<T> getter, Consumer<T> setter){
        T value = getter.get();
        if (value != null){
            setter.accept(value);
        }
    }

    public void save(Order order) {
        order.setTacos(
                order.getTacos().stream()
                        .map(tacoService::save)
                        .collect(Collectors.toList())
        );

        orderRepository.save(order);
    }
}
