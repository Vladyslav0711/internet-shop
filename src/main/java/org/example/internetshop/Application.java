package org.example.internetshop;

import org.example.internetshop.lib.Injector;
import org.example.internetshop.model.Item;
import org.example.internetshop.service.ItemService;

public class Application {
    private static Injector injector = Injector.getInstance("org.example.internetshop");

    public static void main(String[] args) {
        ItemService itemService = (ItemService) injector.getInstance(ItemService.class);
        Item nokia = new Item("Nokia", 2400.0);
        Item simens = new Item("Simens", 1100.0);
        Item sumsung = new Item("Sumsung", 3100.0);
        itemService.create(nokia);
        itemService.create(simens);
        itemService.create(sumsung);

        System.out.println("Get all:");
        itemService.getAll()
                .stream()
                .map(Item::toString)
                .forEach(System.out::println);

        System.out.println("Get simens by id:");
        System.out.println(itemService.get(simens.getId()).get().toString());

        System.out.println("Update");
        simens.setPrice(5123.0);
        itemService.update(simens);
        itemService.getAll().forEach(System.out::println);

        System.out.println("Delete nokia");
        itemService.delete(nokia.getId());
        itemService.getAll().forEach(System.out::println);
    }
}
