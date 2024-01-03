package constants;

public class Regex {
    public static final String HOTEL_ID = "^[hH]\\d{2}$"; // H01, H02, H03, ...
    public static final String HOTEL_PHONE = "^(\\+\\d{1,2}\\s?)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$"; // phone
                                                                                                               // number
    public static final String HOTEL_RATING = "^\\d+\\sstar$"; // 1 star, 2 star, 3 star, ...
}
