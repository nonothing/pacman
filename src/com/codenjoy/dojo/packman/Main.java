package com.codenjoy.dojo.packman;

import com.codenjoy.dojo.packman.controller.MenuController;
import com.codenjoy.dojo.packman.model.Menu;
import com.codenjoy.dojo.packman.view.MenuRenderer;


public class Main {
    public static void main(String[] args) {
        new MenuController(new Menu(), new MenuRenderer()).startGame();
    }

}
