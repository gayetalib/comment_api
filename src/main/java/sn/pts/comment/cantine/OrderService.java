package sn.pts.comment.cantine;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final MenuService menuService;

    public OrderEntity addOrder(OrderEntity order, Long menuId){
        MenuEntity menu = menuService.getMenu(menuId);
        OrderEntity newOrder = OrderEntity.builder()
                .date(Date.from(Instant.now()))
                .dish(order.getDish())
                .employeeName(order.getEmployeeName())
                .status(OrderStatus.APPROUVED)
                .forEmployee(order.isForEmployee())
                .menu(menu)
                .build();
        return repository.save(newOrder);
    }

    public List<OrderEntity> getOrders(){
        return repository.findAll();
    }
}