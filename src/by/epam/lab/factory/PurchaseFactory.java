package by.epam.lab.factory;

import by.epam.lab.entity.PriceDiscountPurchase;
import by.epam.lab.entity.Purchase;


import java.util.Scanner;
import java.util.StringTokenizer;

public class PurchaseFactory {
    enum PurchaseKind {
        GENERAL_PURCHASE {
            Purchase getPurchase(Scanner sc) {
                return new Purchase(sc);
            }
        },
        PRICE_DIS_PURCHASE {
            PriceDiscountPurchase getPurchase(Scanner sc) {
                return new PriceDiscountPurchase(sc);
            }
        };

        abstract Purchase getPurchase(Scanner sc);
    }

    public static Purchase getPurchaseFromFactory(Scanner sc) {
        String args = sc.nextLine();
        int countMatches = new StringTokenizer(args, ";", false).countTokens();
        if (countMatches == 3) {
            return PurchaseKind.GENERAL_PURCHASE.getPurchase(new Scanner(args));
        } else if (countMatches == 4) {
            return PurchaseKind.PRICE_DIS_PURCHASE.getPurchase(new Scanner(args));
        } else {
            throw new IllegalArgumentException();
        }
    }
}