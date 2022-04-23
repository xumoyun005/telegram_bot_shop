package com.company.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KeyboardUtil {
    public static ReplyKeyboardMarkup contactMarkup(){
        KeyboardButton contactButton = new KeyboardButton("Raqamni jo'natish");
        contactButton.setRequestContact(true);
        KeyboardRow row = getRow(contactButton);
        List<KeyboardRow> rowList = getRowList(row);

        return getMarkup(rowList);


    }
    public static ReplyKeyboardMarkup adminMenu() {
        KeyboardButton button = getButton("Category CRUD");
        KeyboardButton button1 = getButton("Product CRUD");
        KeyboardButton button2 = getButton("E'lon berish");


        KeyboardRow keyboardRow = getRow(button, button1, button2);

        return getMarkup(Collections.singletonList(keyboardRow));
    }
    private static KeyboardButton getButton(String demo){
        return new KeyboardButton(demo);
    }

    private static KeyboardRow getRow(KeyboardButton ... buttons){
        return new KeyboardRow(Arrays.asList(buttons));
    }

    private static List<KeyboardRow> getRowList(KeyboardRow ... rows){
        return Arrays.asList(rows);
    }

    private static ReplyKeyboardMarkup getMarkup(List<KeyboardRow> keyboard){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        return replyKeyboardMarkup;
    }
}
