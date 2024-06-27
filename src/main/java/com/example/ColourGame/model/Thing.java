package com.example.ColourGame.model;

import java.util.HashSet;
import java.util.Set;

public class Thing {

    private String name;
    private Set<String> subscribedColors;

    public Thing(String name) {
        this.name = name;
        this.subscribedColors = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void subscribe(String color) {
        subscribedColors.add(color);
    }

    public void unsubscribe(String color) {
        subscribedColors.remove(color);
    }

    public boolean isSubscribedTo(String color) {
        return subscribedColors.contains(color);
    }

    public Set<String> getSubscribedColors() {
        return subscribedColors;
    }

    @Override
    public String toString() {
        return "I’m " + name + "! I’m sometimes " + String.join(", ", subscribedColors) + "!";
    }
}
