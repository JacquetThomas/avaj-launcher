/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.fortytwo.avajlauncher.Exceptions;

public class LoggerException extends Exception {

    public LoggerException(String message) {
        super(message);
    }

    public LoggerException(String message, Throwable err) {
        super(message, err);
    }

    public LoggerException(Throwable err) {
        super(err);
    }
}
