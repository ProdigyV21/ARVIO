package com.arflix.tv.ui.screens.home;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0006\u001a\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/arflix/tv/ui/screens/home/HomeRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "HTML_TAG", "Lkotlin/text/m;", "getHTML_TAG", "()Lkotlin/text/m;", "NON_BREAKING_SPACE", "getNON_BREAKING_SPACE", "UNICODE_SPACE", "getUNICODE_SPACE", "WHITESPACE", "getWHITESPACE", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class HomeRegexes {
    public static final HomeRegexes INSTANCE = new HomeRegexes();
    private static final kotlin.text.m HTML_TAG = new kotlin.text.m("<[^>]*>");
    private static final kotlin.text.m NON_BREAKING_SPACE = new kotlin.text.m("[   ]");
    private static final kotlin.text.m UNICODE_SPACE = new kotlin.text.m("\\p{Z}+");
    private static final kotlin.text.m WHITESPACE = new kotlin.text.m("\\s+");

    private HomeRegexes() {
    }

    public final kotlin.text.m getHTML_TAG() {
        return HTML_TAG;
    }

    public final kotlin.text.m getNON_BREAKING_SPACE() {
        return NON_BREAKING_SPACE;
    }

    public final kotlin.text.m getUNICODE_SPACE() {
        return UNICODE_SPACE;
    }

    public final kotlin.text.m getWHITESPACE() {
        return WHITESPACE;
    }
}
