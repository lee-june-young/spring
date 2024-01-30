package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {
    private Long id;
    private String itemName;
    private Integer price; // Integer를 쓴 이유는 price가 안들어갈 때도 있다고 가정
    private Integer quantity; //int면 0이라도 들어가야하므로 Integer로 사용함.

    public Item() {

    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
