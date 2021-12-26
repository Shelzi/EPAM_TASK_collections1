package by.epam.lab.entity;

import java.util.Scanner;

public class PriceDiscountPurchase extends Purchase {
    private Byn discount;

    public PriceDiscountPurchase() {
    }

    public PriceDiscountPurchase(String name, int price, int number, int discount) {
        super(name, price, number);
        this.discount = new Byn(discount);
    }

    public PriceDiscountPurchase(Scanner scanner) {
        super(scanner);
        this.discount = new Byn(scanner.nextInt());
    }

    public PriceDiscountPurchase(int discount, Purchase purchase) {
        super(purchase);
        this.discount = new Byn(discount);
    }

    @Override
    protected Byn getFinalPrice() {
        return getPrice().sub(discount);
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }
}