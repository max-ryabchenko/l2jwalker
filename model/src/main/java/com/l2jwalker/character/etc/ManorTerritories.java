package com.l2jwalker.character.etc;

import java.util.ArrayList;
import java.util.List;

public class ManorTerritories {

    private final List<String> manorsNames;

    public ManorTerritories(int _count) {
        manorsNames = new ArrayList<String>(_count);
    }

    public List<String> getNames() {
        return manorsNames;
    }

    public void addName(int place, String name) {
        manorsNames.add(place - 1, name);
    }

}
