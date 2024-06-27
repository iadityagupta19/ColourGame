package com.example.ColourGame.service;

import com.example.ColourGame.model.Thing;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GameRunner {

    private final Map<String, Thing> things;

    public GameRunner() {
        things = new HashMap<>();
        things.put("salt", new Thing("Salt"));
        things.put("banana", new Thing("Banana"));
        things.put("ink", new Thing("Ink"));
        things.put("blood", new Thing("Blood"));
        things.put("sky", new Thing("Sky"));
        things.put("apple", new Thing("Apple"));
        things.put("frog", new Thing("Frog"));
    }

    public void processCommand(String command) {
        if (command.startsWith("+")) {
            subscribe(command.substring(1).toLowerCase());
        } else if (command.startsWith("-")) {
            unsubscribe(command.substring(1).toLowerCase());
        } else if (command.equals("list")) {
            listSubscriptions();
        } else if (command.equals("exit")) {
            System.exit(0);
        } else {
            notifyThings(command.toLowerCase());
        }
    }

    public void subscribe(String thingName) {
        Thing thing = things.get(thingName);
        if (thing != null) {
            thing.subscribe("white");
            System.out.println("Subscribed to " + thingName);
        } else {
            System.out.println("Thing not found: " + thingName);
        }
    }

    public void unsubscribe(String thingName) {
        Thing thing = things.get(thingName);
        if (thing != null) {
            thing.unsubscribe("white");
            System.out.println("Unsubscribed from " + thingName);
        } else {
            System.out.println("Thing not found: " + thingName);
        }
    }

    public List<Thing> listSubscriptions() {
        return things.values().stream()
                .filter(thing -> !thing.getSubscribedColors().isEmpty())
                .collect(Collectors.toList());
    }

    public void notifyThings(String color) {
        things.values().forEach(thing -> {
            if (thing.isSubscribedTo(color)) {
                if (thing.getName().equals("Frog")) {
                    System.out.println("I’m Frog! I am " + color + " today.");
                } else {
                    System.out.println("I’m " + thing.getName() + "! I’m sometimes " + color + "!");
                }
            }
        });
    }
}
