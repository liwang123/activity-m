package org.trustnote.activity.service.iface;

public interface TickerService {

    void saveBitZTicker(String currencyPair);

    String getTicker(String currency, String language);
}
