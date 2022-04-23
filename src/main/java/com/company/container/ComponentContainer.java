package com.company.container;

import com.company.ShopBot;
import com.company.enums.AdminStatus;
import com.company.enums.CustomerStatus;
import com.company.model.Category;
import com.company.model.Customer;
import com.company.model.Product;

import java.util.HashMap;
import java.util.Map;

public abstract class ComponentContainer {



    public static final String BOT_TOKEN = "5293074225:AAGjtkUL4yjFhD0-R4wrG-53wMzUb5uWavM";
    public static final String BOT_NAME = "For_Apple_bot";

    public static final String ADMIN_ID = "1879817666";
    public static int generalId = 0;

    public static ShopBot MY_TELEGRAM_BOT;

    public static Map<String, Product> productMap = new HashMap<>();

    public static Map<String, Product> crudStepMap = new HashMap<>();


    public static Map<String, Category> categoryMap = new HashMap<>();
    public static Map<String, Customer> customerMap = new HashMap<>();
    public static Map<String, AdminStatus> productStepMap = new HashMap<>();

    public static Map<String, CustomerStatus> customerStepMap = new HashMap<>();

    public static Map<String, AdminStatus> categoryStepMap = new HashMap<>();



}
