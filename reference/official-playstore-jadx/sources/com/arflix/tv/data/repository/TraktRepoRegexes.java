package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0006\u001a\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/arflix/tv/data/repository/TraktRepoRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "DIACRITICS_REGEX", "Lkotlin/text/m;", "getDIACRITICS_REGEX", "()Lkotlin/text/m;", "NON_ALPHA_NUM_REGEX", "getNON_ALPHA_NUM_REGEX", "HOURS_REGEX", "getHOURS_REGEX", "MINS_REGEX", "getMINS_REGEX", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TraktRepoRegexes {
    public static final TraktRepoRegexes INSTANCE = new TraktRepoRegexes();
    private static final kotlin.text.m DIACRITICS_REGEX = new kotlin.text.m("\\p{Mn}+");
    private static final kotlin.text.m NON_ALPHA_NUM_REGEX = new kotlin.text.m("[^a-z0-9]+");
    private static final kotlin.text.m HOURS_REGEX = new kotlin.text.m("(\\d+)\\s*h");
    private static final kotlin.text.m MINS_REGEX = new kotlin.text.m("(\\d+)\\s*m");
    public static final int $stable = 8;

    private TraktRepoRegexes() {
    }

    public final kotlin.text.m getDIACRITICS_REGEX() {
        return DIACRITICS_REGEX;
    }

    public final kotlin.text.m getHOURS_REGEX() {
        return HOURS_REGEX;
    }

    public final kotlin.text.m getMINS_REGEX() {
        return MINS_REGEX;
    }

    public final kotlin.text.m getNON_ALPHA_NUM_REGEX() {
        return NON_ALPHA_NUM_REGEX;
    }
}
