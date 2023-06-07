package com.danielliao.ecommerce;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SelectedProductForm {
    private Integer memberID;
    private List<Selection> selections;
}
