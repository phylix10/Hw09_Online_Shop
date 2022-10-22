package com.alireza.model.enumeration;

public enum Category {
    ELECTRONIC(null),
        TV(ELECTRONIC),
        RADIO(ELECTRONIC),
    SHOE(null),
        SPORT(SHOE),
        FORMAL(SHOE),
    READABLE(null),
        BOOK(READABLE),
        MAGAZINE(READABLE);


    final Category parent;

    Category(Category parent) {
        this.parent = parent;
    }
}
