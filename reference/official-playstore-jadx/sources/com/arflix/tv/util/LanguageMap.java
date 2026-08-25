package com.arflix.tv.util;

import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.h0;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/arflix/tv/util/LanguageMap;", "", "<init>", "()V", "ISO_LANG_MAP", "", "", "getLanguageName", "code", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class LanguageMap {
    public static final int $stable = 0;
    public static final LanguageMap INSTANCE = new LanguageMap();
    private static final Map<String, String> ISO_LANG_MAP = h0.t0(new x("ar", "Arabic"), new x("ara", "Arabic"), new x("bn", "Bengali"), new x("ben", "Bengali"), new x("zh", "Chinese"), new x("chi", "Chinese"), new x("zho", "Chinese"), new x("nl", "Dutch"), new x("nld", "Dutch"), new x("dut", "Dutch"), new x("en", "English"), new x("eng", "English"), new x("fr", "French"), new x("fre", "French"), new x("fra", "French"), new x("de", "German"), new x("ger", "German"), new x("deu", "German"), new x("gu", "Gujarati"), new x("guj", "Gujarati"), new x("hi", "Hindi"), new x("hin", "Hindi"), new x("it", "Italian"), new x("ita", "Italian"), new x("ja", "Japanese"), new x("jpn", "Japanese"), new x("kn", "Kannada"), new x("kan", "Kannada"), new x("ko", "Korean"), new x("kor", "Korean"), new x("ml", "Malayalam"), new x("mal", "Malayalam"), new x("mr", "Marathi"), new x("mar", "Marathi"), new x("pl", "Polish"), new x("pol", "Polish"), new x("pt", "Portuguese"), new x("por", "Portuguese"), new x("pa", "Punjabi"), new x("pan", "Punjabi"), new x("ru", "Russian"), new x("rus", "Russian"), new x("es", "Spanish"), new x("spa", "Spanish"), new x("sv", "Swedish"), new x("swe", "Swedish"), new x("ta", "Tamil"), new x("tam", "Tamil"), new x("te", "Telugu"), new x("tel", "Telugu"), new x("th", "Thai"), new x("tha", "Thai"), new x("tr", "Turkish"), new x("tur", "Turkish"));

    private LanguageMap() {
    }

    public final String getLanguageName(String code) {
        Map<String, String> map = ISO_LANG_MAP;
        Locale locale = Locale.ROOT;
        String str = map.get(code.toLowerCase(locale));
        return str == null ? code.toUpperCase(locale) : str;
    }
}
