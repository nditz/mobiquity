package com.mobiquity.packages;

import java.util.List;

public class Package {
    private double maxWeight;
    private List<Item> items;

    public Package(double maxWeight, List<Item> items) {
        this.maxWeight = maxWeight;
        this.items = items;
    }

    public Package() {

    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "Package{" +
                "maxWeight=" + maxWeight +
                ", items=" + items +
                '}';
    }
}
