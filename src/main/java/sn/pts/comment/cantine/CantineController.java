package sn.pts.comment.cantine;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("cantine")
@RequiredArgsConstructor
public class CantineController {
    private final MenuService menuService;
    private final OrderService orderService;

    @PostMapping("menu")
    private ResponseEntity<?> addMenu(@RequestBody Map<String, String> dishes){
        //return ResponseEntity.ok().body("Size : " + dishes.size());
        return ResponseEntity.ok().body(menuService.addMenu(dishes));
    }

    @GetMapping("menu")
    private ResponseEntity<?> listMenu(){
        return ResponseEntity.ok().body(menuService.listMenu());
    }

    @PostMapping("order")
    private ResponseEntity<?> addMenu(@RequestBody OrderEntity order, @RequestParam Long menuId){
        return ResponseEntity.ok().body(orderService.addOrder(order, menuId));
    }

    @GetMapping("order")
    private ResponseEntity<?> listOrder(){
        return ResponseEntity.ok().body(orderService.getOrders());
    }

}
