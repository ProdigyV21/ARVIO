package com.arflix.tv.ui.screens.player;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0006\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0006\u001a\u0004\b\u0010\u0010\bR\u0017\u0010\u0011\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0006\u001a\u0004\b\u0012\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/ui/screens/player/PlayerRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "ALPHA_DASH", "Lkotlin/text/m;", "getALPHA_DASH", "()Lkotlin/text/m;", "ALPHA", "getALPHA", "WHITESPACE", "getWHITESPACE", "SIZE_PATTERN_1", "getSIZE_PATTERN_1", "SIZE_PATTERN_2", "getSIZE_PATTERN_2", "SIZE_PATTERN_3", "getSIZE_PATTERN_3", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class PlayerRegexes {
    public static final PlayerRegexes INSTANCE = new PlayerRegexes();
    private static final kotlin.text.m ALPHA_DASH = new kotlin.text.m("[A-Za-z-]+");
    private static final kotlin.text.m ALPHA = new kotlin.text.m("[A-Za-z]+");
    private static final kotlin.text.m WHITESPACE = new kotlin.text.m("\\s+");
    private static final kotlin.text.m SIZE_PATTERN_1 = new kotlin.text.m("(\\d+(?:\\.\\d+)?)\\s*(TB|GB|MB|KB)");
    private static final kotlin.text.m SIZE_PATTERN_2 = new kotlin.text.m("(\\d+(?:\\.\\d+)?)\\s*(TIB|GIB|MIB|KIB)");
    private static final kotlin.text.m SIZE_PATTERN_3 = new kotlin.text.m("^(\\d+(?:\\.\\d+)?)$");

    private PlayerRegexes() {
    }

    public final kotlin.text.m getALPHA() {
        return ALPHA;
    }

    public final kotlin.text.m getALPHA_DASH() {
        return ALPHA_DASH;
    }

    public final kotlin.text.m getSIZE_PATTERN_1() {
        return SIZE_PATTERN_1;
    }

    public final kotlin.text.m getSIZE_PATTERN_2() {
        return SIZE_PATTERN_2;
    }

    public final kotlin.text.m getSIZE_PATTERN_3() {
        return SIZE_PATTERN_3;
    }

    public final kotlin.text.m getWHITESPACE() {
        return WHITESPACE;
    }
}
