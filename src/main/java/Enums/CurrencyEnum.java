package Enums;

import Interfaces.CheckboxValues;

import java.util.stream.Stream;

public enum CurrencyEnum implements CheckboxValues {
    USD("USD","USD - US dollar", "United States"),
    EUR("EUR","EUR - Euro", "European Union"),
    JPY("JPY","JPY - Japanese yen", "Japan"),
    GPB("GPB","GBP - Pound sterling", "United Kingdom"),
    CAD("CAD","CAD - Canadian dollar", "Canada"),
    AUD("AUD","AUD - Australian Dollar", "Australia"),
    CHF("CHF","CHF - Swiss frank", "Switzerland"),
    CNY("CNY","CNY - Chinese yuan", "China"),
    NZD("NZD","NZD - New Zealand dollar", "New Zealand"),
    BRL("BRL","BRL - Brazilian real", "Brazil"),
    KRW("KRW","KRW - South Korean won", "South Korea"),
    HKD("HKD","HKD - Hong Kong dollar", "Hong Kong"),
    SGD("SGD","SGD - Singapore dollar", "Singapore"),
    MXN("MXN","MXN - Mexican peso", "Mexico");

    private String checkboxLabel;
    private String code;
    private String country;

    CurrencyEnum(String code, String checkboxLabel, String country) {
        this.code = code;
        this.checkboxLabel = checkboxLabel;
        this.country = country;
    }

    @Override
    public String getLabel() {
        return checkboxLabel;
    }

    public String getCode() {
        return code;
    }

    public String getCountry() {
        return country;
    }

    public static CurrencyEnum getCurrencyByCode(String code) {
        return Stream.of(CurrencyEnum.values()).filter(currency -> currency.getCode().toUpperCase().equals(code.toUpperCase())).findFirst().orElse(null);
    }
}
