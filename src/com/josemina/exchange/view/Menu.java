package com.josemina.exchange.view;

import com.josemina.exchange.models.Currency;
import com.josemina.exchange.models.CurrencyConvertion;
import com.josemina.exchange.services.ApiServices;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import static com.josemina.exchange.utils.JsonToMap.readJsonFile;

public class Menu {

    public static void conversorMenu() throws IOException {
        Map<String, String> currentsList = readJsonFile("currents.json");
        boolean enable = true;
        do {
            System.out.println("***************************************");
            System.out.println("Bienvenido a su conversor de moneda");
            System.out.println("***************************************");
            for (Map.Entry<String, String> entry : currentsList.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("Escoga la moneda de la quiere convertir, (debe ingresar el codigo de moneda, ejemplo COP)");
            Scanner base = new Scanner(System.in);
            String baseCurrency = base.nextLine();
            String baseChoose = currentsList.get(baseCurrency.toUpperCase());

            System.out.println("Escoga la moneda a la que va a convertir");
            Scanner target = new Scanner(System.in);
            String targetCurrency = target.nextLine();
            String targetChoose = currentsList.get(targetCurrency.toUpperCase());

            System.out.println("Digite el valor que desea convertir");
            Scanner input = new Scanner(System.in);
            double value = input.nextDouble();


            Currency currency = new Currency(baseCurrency, targetCurrency, value);
            ApiServices apiServices = new ApiServices();
            try {
                CurrencyConvertion currencyConvertion = apiServices.converterCurrent(currency);
                System.out.println(value + " " + baseChoose + " equivale a: " + currencyConvertion.conversion_result() + " " + targetChoose);

            } catch (IllegalStateException e) {
                throw new IllegalStateException("El valor ingresado no es correcto");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("***************************************");
            System.out.println("Presione 1 para volver a ejecutar, cualquier otra cosa para salir");
            Scanner exit = new Scanner(System.in);
            String option = exit.nextLine();
            if (!option.equals("1")) {
                enable = false;
            }
        }
        while (enable);
    }
}
