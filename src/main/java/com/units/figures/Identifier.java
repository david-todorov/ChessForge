package com.units.figures;

public class Identifier {
    private final String id;

    public Identifier(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}
