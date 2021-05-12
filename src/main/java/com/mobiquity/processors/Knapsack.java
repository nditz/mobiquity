package com.mobiquity.processors;

import com.mobiquity.packages.Package;

import java.util.List;

public class Knapsack implements IProcess<List<Package>, String> {

    @Override
    public String execute(List<Package> packages) {
        StringBuilder builder = new StringBuilder();
        for (Package apackage : packages) {
            // check constraints before passing parameters to the algorithm
            // max package weight is equal to or less than 100
            int maxPackageWeight = (int) apackage.getMaxWeight();
            if (maxPackageWeight > 100)
                maxPackageWeight =100;
            int noOfItems = apackage.getItems().size();
            // we can only choose from up to 15 items
            if (noOfItems > 15)
                noOfItems = 15;
            int[] weight = new int[noOfItems];
            int[] value = new int[noOfItems];
            for (int i = 0; i < noOfItems; i++) {
                // check if item weight and item value constraint is met
                weight[i] = (int) apackage.getItems().get(i).getWeight() > 100 ? 100 : (int) apackage.getItems().get(i).getWeight();
                value[i] = (int) apackage.getItems().get(i).getValue() > 100 ? 100 : (int) apackage.getItems().get(i).getValue();
            }
            builder.append(knapSack(maxPackageWeight, weight, value, noOfItems));
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }


    // Knapsack algorithm to determine the max value and min weight 
    private static String knapSack(int maxWeight, int[] itemWeight, int[] itemValue, int noOfItems) {
        int item, weight;
        int K[][] = new int[noOfItems + 1][maxWeight + 1];
        int[] selected = new int[noOfItems + 1];

        // Build table K[][] in bottom up manner
        for (item = 0; item <= noOfItems; item++) {
            for (weight = 0; weight <= maxWeight; weight++) {
                if (item == 0 || weight == 0) {
                    //selected[i] = 1;
                    K[item][weight] = 0;
                } else if (itemWeight[item - 1] <= weight) {
                    selected[item] = 1;
                    K[item][weight] = Math.max(itemValue[item - 1] + K[item - 1][weight - itemWeight[item - 1]], K[item - 1][weight]);
                } else {
                    selected[item] = 0;
                    K[item][weight] = K[item - 1][weight];
                }
            }
        }

        int tempW = maxWeight;
        int y = 0; //to index in selected
        for (int x = noOfItems; x > 0; x--) {
            if ((tempW - itemWeight[x - 1] >= 0) && (K[x][tempW] - K[x - 1][tempW - itemWeight[x - 1]] == itemValue[x - 1])) {
                selected[y++] = x - 1; //store current index and increment y
                tempW -= itemWeight[x - 1];
            }
        }

        StringBuilder builder = new StringBuilder();

        if (y == 0) {
            builder.append("-");
        } else {
            for (int j = y - 1; j >= 0; j--) {
                builder.append(selected[j] + 1).append(" ");
            }
        }
        return builder.toString();
    }
}
