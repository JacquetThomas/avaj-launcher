package com.fortytwo.avajlauncher.Control;

import java.util.logging.*;

public class MyFormatter extends Formatter{
    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();
        sb.append(record.getMessage()).append('\n');
        return sb.toString();
    }
}