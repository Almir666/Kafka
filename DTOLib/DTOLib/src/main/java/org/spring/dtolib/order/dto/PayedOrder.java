package org.spring.dtolib.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PayedOrder {
    private String orderName;
    private boolean payed;
}
