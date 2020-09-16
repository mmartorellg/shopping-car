package com.shoppingcar.demo.controller.utils;

import com.shoppingcar.demo.controller.ClientController;
import com.shoppingcar.demo.controller.ProductController;
import com.shoppingcar.demo.controller.SaleController;
import com.shoppingcar.demo.controller.UserController;

public class ControllerMapper {

    public static void writeLog(String name, String text) {
        if (name.contains(ClientController.class.getCanonicalName())) {
            ClientController.writeLog(text);

        } else {
            if (name.contains(SaleController.class.getCanonicalName())) {
                SaleController.writeLog(text);
            } else {
                if (name.contains(ProductController.class.getCanonicalName())) {
                    ProductController.writeLog(text);

                } else {
                    if (name.contains(UserController.class.getCanonicalName())) {
                        UserController.writeLog(text);

                    }
                }
            }
        }
    }
}
