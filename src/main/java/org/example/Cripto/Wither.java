package org.example.Cripto;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class Wither {
    public String witherNow() {
        String url = "https://crypto.com/price";//страница для парсинга
        StringBuilder sb = new StringBuilder();
        try {
            Document doc = Jsoup.connect(url).get();//используем библиотеку Jsoup для парсинга
            //извлекаем информацию о топ-50 криптовалютах
            Elements rows = doc.select("table tbody tr");
            sb.append("Top 50 Cryptocurrencies:\n");
            //используем `String.format()` для разделения столбцов
            sb.append(String.format("%-5s %-20s %-20s %-15s\n", "№", "Name", "Price", "Volume 24H"));
            int i = 1;
            //получаем name название, price цену и объем торгов volume
            for (Element row : rows) {
                String name = row.select("td[class=css-1sem0fc]>>a[class=chakra-link css-tzmkfm]>>div[class=css-18biwo]>>div[class=css-ttxvk0]>>p[class=chakra-text css-rkws3]").text();
                String price = row.select("td[class=css-1vyy4qg]>>div[class=css-b1ilzc]").text();
                String volume = row.select("td[class=css-1b7j986]").text();
                //используем `String.format()` для разделения столбцов
                sb.append(String.format("%-5d %-20s %-20s %-15s\n", i, name, price, volume));
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

