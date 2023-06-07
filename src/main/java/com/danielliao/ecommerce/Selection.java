package com.danielliao.ecommerce;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Selection {
    private Integer productID;
    private Integer selectedQuantity;
}
