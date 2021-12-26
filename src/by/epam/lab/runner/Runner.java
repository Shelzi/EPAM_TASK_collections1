package by.epam.lab.runner;

import by.epam.lab.entity.*;
import by.epam.lab.factory.PurchaseFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Runner {
    public static void main(String[] args) {
        final int PURCHASES_NUMBER = 14;
        Map<Purchase, WeekDay> firstPurchaseMap = new HashMap<>();
        Map<Purchase, WeekDay> lastPurchaseMap = new HashMap<>();
        List<PriceDiscountPurchase> pricePurchaseList = new ArrayList<>();
        Map<WeekDay, List<Purchase>> weekDayListMap = new EnumMap<>(WeekDay.class);
        try (Scanner sc = new Scanner(new FileReader("src/in.txt"))) {
            sc.useLocale(Locale.ENGLISH);
            for (int i = 0; i < PURCHASES_NUMBER; i++) {
                Purchase purchase = PurchaseFactory.getPurchaseFromFactory(sc);
                WeekDay weekDay = WeekDay.valueOf(sc.nextLine());
                firstPurchaseMap.put(purchase, weekDay);
                lastPurchaseMap.putIfAbsent(purchase, weekDay);
                if (purchase instanceof PriceDiscountPurchase) {
                    pricePurchaseList.add((PriceDiscountPurchase) purchase);
                }
                weekDayListMap.putIfAbsent(weekDay, new ArrayList<>());
                weekDayListMap.get(weekDay).add(purchase);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file is not found");
        }
        printMap(firstPurchaseMap);
        System.out.println();
        printMap(lastPurchaseMap);
        System.out.println();
        searchMap(firstPurchaseMap, new Purchase("bread", 155, 1));
        searchMap(lastPurchaseMap, new Purchase("bread", 155, 1));
        searchMap(firstPurchaseMap, new Purchase("bread", 170, 1));
        removeEntries(firstPurchaseMap, new EntryChecker<Purchase, WeekDay>() {
            @Override
            public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                return entry.getKey().getName().equals("meat");
            }
        });
        removeEntries(lastPurchaseMap, new EntryChecker<Purchase, WeekDay>() {
            @Override
            public boolean check(Map.Entry<Purchase, WeekDay> entry) {
                return entry.getValue().equals(WeekDay.FRIDAY);
            }
        });
        printMap(firstPurchaseMap);
        System.out.println();
        printMap(lastPurchaseMap);
        System.out.println();
        System.out.println((getTotalCost(pricePurchaseList)));
        printMap(weekDayListMap);
        for (Map.Entry<WeekDay, List<Purchase>> entry : weekDayListMap.entrySet()) {
            System.out.println("Total cost for " + entry.getKey() + " => " + getTotalCost(entry.getValue()));
        }
        searchMap(weekDayListMap, WeekDay.MONDAY);

    }

    private static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() +
                    "=>" + entry.getValue());
        }
    }

    private static <K, V> void searchMap(Map<K, V> map, K searchedElement) {
        if (map.containsKey(searchedElement)) {
            System.out.println(map.get(searchedElement));
        } else {
            System.out.println("Element not found");
        }
    }

    private static <K, V> void removeEntries(Map<K, V> map, EntryChecker<K, V> checker) {
        Iterator<Map.Entry<K, V>> mapIterator = map.entrySet().iterator();
        while (mapIterator.hasNext()) {
            Map.Entry<K, V> entry = mapIterator.next();
            if (checker.check(entry)) {
                mapIterator.remove();
            }
        }
    }

    private static Byn getTotalCost(List<? extends Purchase> list) {
        Byn totalCost = new Byn();
        for (Purchase purchase : list) {
            totalCost = totalCost.add(purchase.getCost());
        }
        return totalCost;
    }
}