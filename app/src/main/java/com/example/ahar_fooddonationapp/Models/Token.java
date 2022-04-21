package com.example.ahar_fooddonationapp.Models;

import java.time.OffsetDateTime;
import java.util.Date;

public class Token {
    private static String token;
    private Date expirationDate;

    public static String getToken() {
        return token;
    }
    public static void setToken(String token) {
        Token.token = token;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
