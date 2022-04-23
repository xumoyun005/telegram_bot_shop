package com.company.util;


import com.company.database.Database;
import com.company.model.Category;
import com.company.model.Product;
import com.company.service.CategoryService;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InlineKeyboardUtil {
    public static InlineKeyboardMarkup productMenu() {
        InlineKeyboardButton addButton = getButton("Add product", "add_product");
        InlineKeyboardButton updateButton = getButton("Update product", "update_product");
        InlineKeyboardButton deleteButton = getButton("Delete product", "delete_product");
        InlineKeyboardButton listButton = getButton("Show product list", "show_product_list");

        List<InlineKeyboardButton> row1 = getRow(addButton);
        List<InlineKeyboardButton> row2 = getRow(updateButton);
        List<InlineKeyboardButton> row3 = getRow(deleteButton);
        List<InlineKeyboardButton> row4 = getRow(listButton);

        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2, row3, row4);
        return new InlineKeyboardMarkup(rowList);
    }

    public static InlineKeyboardMarkup categorysMenu(){
        CategoryService.loadCategoryList();

        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();

        for (Category category : Database.categoryList) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(category.getName());
            button.setCallbackData("category/"+category.getId());
            List<InlineKeyboardButton> row = getRow(button);

            rowList.add(row);
        }

        return inlineMarkup(rowList);
    }

    public static InlineKeyboardMarkup categoryMenu() {
        InlineKeyboardButton addButtonC = getButton("Add category", "add_category");
        InlineKeyboardButton updateButtonC = getButton("Update category", "update_category");
        InlineKeyboardButton deleteButtonC = getButton("Delete category", "delete_category");
        InlineKeyboardButton listButtonC = getButton("Show category list", "show_category_list");

        List<InlineKeyboardButton> row5 = getRow(addButtonC);
        List<InlineKeyboardButton> row6 = getRow(updateButtonC);
        List<InlineKeyboardButton> row7 = getRow(deleteButtonC);
        List<InlineKeyboardButton> row8 = getRow(listButtonC);
        List<List<InlineKeyboardButton>> rowList = getRowList(row5, row6, row7, row8);
        return new InlineKeyboardMarkup(rowList);
    }

    private static InlineKeyboardButton getButton(String demo, String data) {
        InlineKeyboardButton button = new InlineKeyboardButton(demo);
        button.setCallbackData(data);
        return button;
    }

    private static List<InlineKeyboardButton> getRow(InlineKeyboardButton... buttons) {
        return Arrays.asList(buttons);
    }

    private static List<List<InlineKeyboardButton>> getRowList(List<InlineKeyboardButton>... rows) {
        return Arrays.asList(rows);
    }

    public static InlineKeyboardMarkup categoryInlineMarkup() {

        CategoryService.loadCategoryList();

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        for (Category category : Database.categoryList) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(category.getName());
            button.setCallbackData("add_product_category_id/" + category.getId());
            buttonList.add(button);
            rowList.add(buttonList);
        }
        return new InlineKeyboardMarkup(rowList);
    }

    public static InlineKeyboardMarkup categoryInlineRemoveProduct(){
        CategoryService.loadCategoryList();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        for (Category category : Database.categoryList){
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(category.getName());
            button.setCallbackData("remove_product_category_id/" + category.getId());
            buttonList.add(button);
            rowList.add(buttonList);
        }
        return new InlineKeyboardMarkup(rowList);
    }


    public static InlineKeyboardMarkup categoryShow() {
        CategoryService.loadCategoryList();

        List<List<InlineKeyboardButton>> listList = new ArrayList<>();
        for (Category category : Database.categoryList) {
            List<InlineKeyboardButton> buttonList = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton(category.getName());
            button.setCallbackData("show_category/" + category.getId());
            buttonList.add(button);
            listList.add(buttonList);
        }

        InlineKeyboardButton button = getButton("ortga", "back_from_category_list");
        List<InlineKeyboardButton> row = getRow(button);
        listList.add(row);

        return new InlineKeyboardMarkup(listList);
    }


    public static InlineKeyboardMarkup Menu() {

        InlineKeyboardButton menuButton = getButton("📠MENU", "menu_");
        InlineKeyboardButton magazineButton = getButton("🏪Bizning manzil", "magazine_url");
        InlineKeyboardButton contactButton = getButton("📞Bizning ijtimoiy tarmoqlarimiz", "web_tarmoq");
        InlineKeyboardButton helpButton = getButton("💬HELP", "help");

        magazineButton.setUrl("https://www.google.com/maps/place/Apple.OptomUz/@41.3336413,69.2638133,20z/data=!4m12!1m6!3m5!1s0x38ae8b2c0b6cfaeb:0xc6e15033771e39c0!2sApple.OptomUz!8m2!3d41.3336413!4d69.2639772!3m4!1s0x38ae8b2c0b6cfaeb:0xc6e15033771e39c0!8m2!3d41.3336413!4d69.2639772");
        helpButton.setUrl("https://t.me/apple_optomuz");

        List<InlineKeyboardButton> row1 = getRow(menuButton);
        List<InlineKeyboardButton> row2 = getRow(magazineButton);
        List<InlineKeyboardButton> row3 = getRow(helpButton);
        List<InlineKeyboardButton> row4 = getRow(contactButton);


        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2, row3, row4);

        return new InlineKeyboardMarkup(rowList);
    }

    public static InlineKeyboardMarkup NetAddressMenu() {
        InlineKeyboardButton instagram = getButton("📤 Instgram", "instagram");
        InlineKeyboardButton telegram = getButton("📤 Telegram", "telegram");
        InlineKeyboardButton facebook = getButton("📤 Facebook", "facebook");
        InlineKeyboardButton back = getButton("◀ BACK", "back");

        instagram.setUrl("https://www.instagram.com/apple.optomuz/");
        telegram.setUrl("https://t.me/apple_optomuz");
        facebook.setUrl("https://www.facebook.com/profile.php?id=100068726450448");


        List<InlineKeyboardButton> row1 = getRow(instagram);
        List<InlineKeyboardButton> row2 = getRow(telegram, facebook);
        List<InlineKeyboardButton> row3 = getRow(back);

        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2, row3);
        return new InlineKeyboardMarkup(rowList);
    }

    public static InlineKeyboardMarkup confirmAddProductMarkup() {

        InlineKeyboardButton commit = getButton("Ha", "add_product_commit");
        InlineKeyboardButton cancel = getButton("Yo'q", "add_product_cancel");

        return new InlineKeyboardMarkup(getRowList(getRow(commit, cancel)));
    }

    public static InlineKeyboardMarkup inlineMarkup(List<List<InlineKeyboardButton>> keyboard) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup confirmAddCategoryMarkup() {
        InlineKeyboardButton commit = getButton("Ha", "add_category_commit");
        InlineKeyboardButton cancel = getButton("Yo'q", "add_category_cancel");

        return new InlineKeyboardMarkup(getRowList(getRow(commit, cancel)));
    }

    public static InlineKeyboardMarkup selectCountMenu(Product product) {
        String suffix = " ta";

        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();

        for (int i = 1; i <= 7 ; i+=3) {
            InlineKeyboardButton button1 = getButton(i + suffix, "count/" + product.getId() + "/" + i);
            InlineKeyboardButton button2 = getButton((i+1) + suffix, "count/" + product.getId() + "/" + (i+1));
            InlineKeyboardButton button3 = getButton((i+2) + suffix, "count/" + product.getId() + "/" + (i+2));

            List<InlineKeyboardButton> row = getRow(button1, button2, button3);

            rowList.add(row);
        }

        InlineKeyboardButton back = getButton(
                "Orqaga qaytish",
                DemoUtil.BACK_FROM_COUNT_PRODUCT+"/"+product.getCategoryId()
        );
        List<InlineKeyboardButton> row = getRow(back);
        rowList.add(row);

        return inlineMarkup(rowList);
    }
    public static InlineKeyboardMarkup updateProduct() {
        InlineKeyboardButton change_name = getButton("🖊 Nomini O'zgartirish 🖊", "change_name");
        InlineKeyboardButton change_price = getButton("💸 Narxini O'zgartirish 💸", "change_price");
        InlineKeyboardButton change_deleted = getButton("📑 O'chirilganlarini Yoqish  📑", "change_deleted_on");
        InlineKeyboardButton change_deleted_switch = getButton("📑 Yoqilganlarni o'chirish  📑", "change_deleted_off");
        InlineKeyboardButton change_deleted_show = getButton("📜 O'chirilganlarini Ko'rish  📜", "change_deleted_show");
        InlineKeyboardButton back = getButton("📜 Orqaga 📜", "back_crud");


        List<InlineKeyboardButton> row1 = getRow(change_name,change_price);
        List<InlineKeyboardButton> row2 = getRow(change_deleted_show);
        List<InlineKeyboardButton> row3 = getRow(change_deleted,change_deleted_switch);
        List<InlineKeyboardButton> row4 = getRow(back);


        List<List<InlineKeyboardButton>> rowList = getRowList(row1, row2,row3,row4);

        return new InlineKeyboardMarkup(rowList);
    }
}
