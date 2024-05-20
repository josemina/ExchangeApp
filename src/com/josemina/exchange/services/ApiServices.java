package com.josemina.exchange.services;

import com.google.gson.Gson;

import com.josemina.exchange.models.Currency;
import com.josemina.exchange.models.CurrencyConvertion;
import com.josemina.exchange.utils.HttpClientUtil;

//import java.lang.reflect.Type;
//import java.util.List;


public class ApiServices {
    private static final String baseUrl = "https://v6.exchangerate-api.com/v6/";
    private static final String apiKey = "fe98f520906497291d7eefaf";

    public CurrencyConvertion converterCurrent(Currency currency) {
        String url = baseUrl + apiKey + "/pair/" + currency.baseCode() + "/" + currency.targetCode() + "/" + currency.amount();
        HttpClientUtil newQuery = new HttpClientUtil();
        return new Gson().fromJson(newQuery.sendGet(url), CurrencyConvertion.class);
    }

//    public List<String> getCurriencies(){
//        String url = baseUrl + apiKey + "/codes";
//        HttpClientUtil newQuery = new HttpClientUtil();
//        Type listType = new TypeToken<List<String>>() {}.getType();
//        return new Gson().fromJson(newQuery.sendGet(url),listType);
//    }


}

