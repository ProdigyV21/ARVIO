package com.arflix.tv.ui.screens.tv;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/TvViewModelRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "NON_ALPHANUMERIC_REGEX", "Lkotlin/text/m;", "getNON_ALPHANUMERIC_REGEX", "()Lkotlin/text/m;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class TvViewModelRegexes {
    public static final TvViewModelRegexes INSTANCE = new TvViewModelRegexes();
    private static final kotlin.text.m NON_ALPHANUMERIC_REGEX = new kotlin.text.m("[^a-z0-9]+");

    private TvViewModelRegexes() {
    }

    public final kotlin.text.m getNON_ALPHANUMERIC_REGEX() {
        return NON_ALPHANUMERIC_REGEX;
    }
}
