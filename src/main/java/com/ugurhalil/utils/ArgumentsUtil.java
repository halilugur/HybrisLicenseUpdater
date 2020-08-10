/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ugurhalil.utils;

import java.lang.module.FindException;
import java.util.List;

/**
 *
 * @author halilugur
 */
public class ArgumentsUtil {

    public static List<String> args;

    public static boolean hasArgument(String arg) {
        return args.contains(arg);
    }

    public static String findArgumentValue(String arg, boolean required) {
        if (required) {
            if (args.indexOf(arg) == -1) {
                throw new FindException("Plase fill " + arg + " argument!");
            } else {
                return args.get(args.indexOf(arg) + 1);

            }
        }
        return null;
    }
}
