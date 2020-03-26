package com.skillsup.model;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    List<PositionItem> items;
    BigDecimal total;
    User user;
    OrderStatus orderStatus;

    public BigDecimal subtotal() {
        return  new PositionItem().subtotal();
    }

    class PositionItem {
      Order order;
      int amount;
      BigDecimal total;

        public BigDecimal subtotal() {
            return Order.this.total;
        }
    }


}
