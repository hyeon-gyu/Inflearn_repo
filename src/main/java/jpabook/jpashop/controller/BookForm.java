package jpabook.jpashop.controller;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    private Long id;
    //상품수정을 해야해서 id값 가지고 있어야함
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;
}
