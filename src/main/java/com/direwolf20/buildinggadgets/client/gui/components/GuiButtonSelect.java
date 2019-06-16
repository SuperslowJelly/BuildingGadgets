package com.direwolf20.buildinggadgets.client.gui.components;

import javax.annotation.Nullable;

public class GuiButtonSelect extends GuiButtonHelpText {
    protected boolean selected;

    public GuiButtonSelect(int x, int y, int width, int height, String text, String helpTextKey, @Nullable IPressable action) {
        super(x, y, width, height, text, helpTextKey, action != null ? action : b -> {});
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void toggleSelected() {
        selected ^= true;
    }
}