package com.skillsup.model;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    List<PositionItem> items;
    BigDecimal total;
    User user;
    OrderStatus orderStatus;

    class PositionItem {
      Order order;
      int amount;
      BigDecimal subtotal;
    }
}
