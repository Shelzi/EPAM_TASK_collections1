package by.epam.lab.entity;

import java.util.Objects;
import java.util.Scanner;

public class Purchase {
    private String name;
    private Byn price;
    private int quantity;

    public Purchase() {}

    public Purchase (String name, int valueByn, int quantity) {
        this.name = name;
        price = new Byn(valueByn);
        this.quantity = quantity;
    }

    public Purchase (Scanner scannedFile) {
        scannedFile.useDelimiter(";");
        name = scannedFile.next();
        price = new Byn(Integer.parseInt(scannedFile.next()));
        quantity = Integer.parseInt(scannedFile.next());
    }

    public Purchase(Purchase purchase){
        name = purchase.name;
        price = purchase.price;
        quantity = purchase.quantity;
    }


    protected void setName(String name) {
        this.name = name;
    }

    protected void setPrice(int value) {
        this.price = new Byn(value);
    }

    protected void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    protected Byn getPrice() {
        return new Byn(price);
    }

    protected Byn getFinalPrice() {
        return price;
    }

    public final Byn getCost() {
        return getFinalPrice().mul(quantity,RoundMethod.ROUND,0);
    }

    protected String fieldsToString() {
        return getClass().getSimpleName() + ";" + name + ";" + price + ";" + quantity;
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Purchase)) {
            return false;
        }
        Purchase purchase = (Purchase) o;
        return quantity == purchase.quantity &&
                Objects.equals(name, purchase.name) &&
                Objects.equals(price, purchase.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity);
    }
}