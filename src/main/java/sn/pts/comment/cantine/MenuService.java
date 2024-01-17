package sn.pts.comment.cantine;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuEntity addMenu(Map<String, String> dishes){
        MenuEntity menu = new MenuEntity();
        Set<String> newDishes = new HashSet<>();
        for (int i = 1; i <= dishes.size();i++){
            newDishes.add(dishes.get("dish" + i));
        }
        menu.setDishes(newDishes);
        menu.setDate(Date.from(Instant.now()));
        return menuRepository.save(menu);
    }

    public List<MenuEntity> listMenu(){
        return menuRepository.findAll();
    }

    public MenuEntity getMenu(Long id){
        return menuRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu not found with this ID = " + id));
    }
}
