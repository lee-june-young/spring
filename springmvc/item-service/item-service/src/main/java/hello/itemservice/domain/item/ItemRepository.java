package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //static // 실제로는 hashMap 사용하면 안됨 - 여러 쓰레드가 동시에 사용하면 이렇게 쓰면 안됨.
    private static long sequence = 0L; // static // 얘도 얼터밋 롱? 같은 거 사용해야해

    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
        //store.values() 이걸 그대로 반환해도 되는데, store에 변화가 없도록 안전하게 반환!
    }

    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore(){
        store.clear();
    }
}
