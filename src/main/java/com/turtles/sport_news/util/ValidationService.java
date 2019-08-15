package com.turtles.sport_news.util;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public boolean allExist(String ...args) {
        for (String arg: args) {j
            if (arg == null || arg.isEmpty())
                return false;
        }
        return true;
    }
}
