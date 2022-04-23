package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.database.Database;
import com.company.enums.CustomerStatus;
import com.company.model.Category;
import com.company.model.Customer;
import com.company.service.CustomerService;
import com.company.util.InlineKeyboardUtil;
import com.company.util.KeyboardUtil;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

import java.util.Optional;

import static com.company.container.ComponentContainer.ADMIN_ID;
import static com.company.container.ComponentContainer.MY_TELEGRAM_BOT;

public class MainController {
    static AdminController adminController;

    public void handleMessage(User user, Message message) {
        if (message.hasText()) {
            handleText(user, message);
        } else if (message.hasContact()) {
            handleContact(user, message);
        }
    }

    private void handleContact(User user, Message message) {
        Contact contact = message.getContact();
        String customerId = String.valueOf(contact.getUserId());

        Customer customer = CustomerService.getCustomerById(customerId);
        if (customer != null) {
            customer = new Customer(customerId, contact.getFirstName(),
                    contact.getLastName(), contact.getPhoneNumber(), CustomerStatus.SHARE_CONTACT);
            CustomerService.addCustomer(customer);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(message.getChatId()));
            MY_TELEGRAM_BOT.sendMsg(sendMessage);
            sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        }

        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(String.valueOf(message.getChatId()));
        deleteMessage.setMessageId(message.getMessageId());
        MY_TELEGRAM_BOT.sendMsg(deleteMessage);


        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Amalni tanlang:");
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        sendMessage.setReplyMarkup(InlineKeyboardUtil.Menu());
        MY_TELEGRAM_BOT.sendMsg(sendMessage);
    }

    private void handleText(User user, Message message) {
        String text = message.getText();
        String chatId = String.valueOf(message.getChatId());

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        Customer customer = CustomerService.getCustomerById(String.valueOf(message.getChatId()));

        if (text.equals("/start")) {

            if (customer == null) {
                sendMessage.setText("<b>Assalomu Aleykum!</b>\n" +
                        "Raqamingizmi jo'nating.");
                sendMessage.setParseMode(ParseMode.HTML);
                sendMessage.setReplyMarkup(KeyboardUtil.contactMarkup());
                sendMessage.setChatId(String.valueOf(message.getChatId()));
                MY_TELEGRAM_BOT.sendMsg(sendMessage);

            } else {
                sendMessage.setText("<b>💻Assalomu Aleykum Apple.optom uz Botiga Hush Kelibsiz ! \n\nMenudan Birortasini Tanlang</b>\n");
                sendMessage.setParseMode(ParseMode.HTML);

                sendMessage.setReplyMarkup(InlineKeyboardUtil.Menu());
                MY_TELEGRAM_BOT.sendMsg(sendMessage);
            }
        }
    }


    public void handleCallBack(User user, Message message, String data) {

        if (data.equals("web_tarmoq")) {

            DeleteMessage deleteMessage = new DeleteMessage();
            deleteMessage.setChatId(String.valueOf(message.getChatId()));
            deleteMessage.setMessageId(message.getMessageId());
            MY_TELEGRAM_BOT.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Bizning tarmoqlar");
            sendMessage.setChatId(String.valueOf(message.getChatId()));
            sendMessage.setReplyMarkup(InlineKeyboardUtil.NetAddressMenu());
            MY_TELEGRAM_BOT.sendMsg(sendMessage);

        } else if (data.equals("back")) {
            DeleteMessage deleteMessage = new DeleteMessage();
            deleteMessage.setChatId(String.valueOf(message.getChatId()));
            deleteMessage.setMessageId(message.getMessageId());
            MY_TELEGRAM_BOT.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Amalni tanlang: ");
            sendMessage.setChatId(String.valueOf(message.getChatId()));
            sendMessage.setReplyMarkup(InlineKeyboardUtil.Menu());
            MY_TELEGRAM_BOT.sendMsg(sendMessage);

        } else if (data.equals("menu_")) {
            DeleteMessage deleteMessage = new DeleteMessage();
            deleteMessage.setChatId(String.valueOf(message.getChatId()));
            deleteMessage.setMessageId(message.getMessageId());
            MY_TELEGRAM_BOT.sendMsg(deleteMessage);

            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Categoriyalardan birini tanlang : 🔽");
            sendMessage.setChatId(String.valueOf(message.getChatId()));
            sendMessage.setReplyMarkup(InlineKeyboardUtil.categorysMenu());
            MY_TELEGRAM_BOT.sendMsg(sendMessage);
        }
    }
}