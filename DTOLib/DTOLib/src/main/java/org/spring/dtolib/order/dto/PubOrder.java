package org.spring.dtolib.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PubOrder {
    private String orderName;
    private String cost;
    private boolean payment;
}
