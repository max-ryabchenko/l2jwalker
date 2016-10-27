package com.l2jwalker.character.trade;

import java.util.ArrayList;
import java.util.List;

public class MultiSellEntry {

    private final int entryId;
    private boolean stackable = true;

    private List<MultiSellIngredient> products = new ArrayList<MultiSellIngredient>();
    private List<MultiSellIngredient> ingredients = new ArrayList<MultiSellIngredient>();

    public MultiSellEntry(int entryId) {
        this.entryId = entryId;
    }

    public int getEntryId() {
        return entryId;
    }

    public synchronized void addProduct(MultiSellIngredient multiSellIngredient) {
        this.products.add(multiSellIngredient);
    }

    public synchronized void addIngredient(MultiSellIngredient multiSellIngredient) {
        this.ingredients.add(multiSellIngredient);
    }

    public synchronized List<MultiSellIngredient> getProducts() {
        return this.products;
    }

    public synchronized List<MultiSellIngredient> getIngredients() {
        return this.ingredients;
    }

    public boolean isStackable() {
        return stackable;
    }

    public void setStackable(boolean stackable) {
        this.stackable = stackable;
    }

    @Override
    public String toString() {
        if (this.getProducts().size() == 1 && this.getProducts().get(0).getData() != null) {
            return this.getProducts().get(0).getData().getName();
        }
        return "[" + this.entryId + "]";
    }
}
