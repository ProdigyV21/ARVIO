package com.arflix.tv.ui.screens.player;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/arflix/tv/ui/screens/player/AiSubtitleRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "BRACKET_REGEX", "Lkotlin/text/m;", "getBRACKET_REGEX", "()Lkotlin/text/m;", "MUSIC_REGEX", "getMUSIC_REGEX", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class AiSubtitleRegexes {
    public static final AiSubtitleRegexes INSTANCE = new AiSubtitleRegexes();
    private static final kotlin.text.m BRACKET_REGEX = new kotlin.text.m("\\[.*?\\]");
    private static final kotlin.text.m MUSIC_REGEX = new kotlin.text.m("[♪♫]+");

    private AiSubtitleRegexes() {
    }

    public final kotlin.text.m getBRACKET_REGEX() {
        return BRACKET_REGEX;
    }

    public final kotlin.text.m getMUSIC_REGEX() {
        return MUSIC_REGEX;
    }
}
