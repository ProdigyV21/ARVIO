package com.arflix.tv.data.telegram;

import kotlin.Metadata;
import kotlin.text.m;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramSearchMatcherRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "DIACRITICS_REGEX", "Lkotlin/text/m;", "getDIACRITICS_REGEX", "()Lkotlin/text/m;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class TelegramSearchMatcherRegexes {
    public static final TelegramSearchMatcherRegexes INSTANCE = new TelegramSearchMatcherRegexes();
    private static final m DIACRITICS_REGEX = new m("\\p{Mn}+");

    private TelegramSearchMatcherRegexes() {
    }

    public final m getDIACRITICS_REGEX() {
        return DIACRITICS_REGEX;
    }
}
