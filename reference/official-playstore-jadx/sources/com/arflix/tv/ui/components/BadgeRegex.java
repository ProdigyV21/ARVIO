package com.arflix.tv.ui.components;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b%\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0006\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0006\u001a\u0004\b\u0010\u0010\bR\u0017\u0010\u0011\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0006\u001a\u0004\b\u0012\u0010\bR\u0017\u0010\u0013\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0006\u001a\u0004\b\u0014\u0010\bR\u0017\u0010\u0015\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0006\u001a\u0004\b\u0016\u0010\bR\u0017\u0010\u0017\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0006\u001a\u0004\b\u0018\u0010\bR\u0017\u0010\u0019\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0006\u001a\u0004\b\u001a\u0010\bR\u0017\u0010\u001b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0006\u001a\u0004\b\u001c\u0010\bR\u0017\u0010\u001d\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u0006\u001a\u0004\b\u001e\u0010\bR\u0017\u0010\u001f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u0006\u001a\u0004\b \u0010\bR\u0017\u0010!\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b!\u0010\u0006\u001a\u0004\b\"\u0010\bR\u0017\u0010#\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b#\u0010\u0006\u001a\u0004\b$\u0010\bR\u0017\u0010%\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b%\u0010\u0006\u001a\u0004\b&\u0010\bR\u0017\u0010'\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b'\u0010\u0006\u001a\u0004\b(\u0010\b¨\u0006)"}, d2 = {"Lcom/arflix/tv/ui/components/BadgeRegex;", "", "<init>", "()V", "Lkotlin/text/m;", "RES_4K", "Lkotlin/text/m;", "getRES_4K", "()Lkotlin/text/m;", "RES_1080", "getRES_1080", "RES_720", "getRES_720", "RES_480", "getRES_480", "DV", "getDV", "HDR10_PLUS", "getHDR10_PLUS", "HDR10", "getHDR10", "HDR", "getHDR", "IMAX", "getIMAX", "ATMOS", "getATMOS", "TRUEHD", "getTRUEHD", "DTS_X", "getDTS_X", "DTS_HD_MA", "getDTS_HD_MA", "DTS_HD", "getDTS_HD", "DTS", "getDTS", "DD_PLUS", "getDD_PLUS", "DD", "getDD", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class BadgeRegex {
    public static final BadgeRegex INSTANCE = new BadgeRegex();
    private static final kotlin.text.m RES_4K = new kotlin.text.m("\\b(4K|2160p|UHD)\\b", 0);
    private static final kotlin.text.m RES_1080 = new kotlin.text.m("\\b1080p\\b", 0);
    private static final kotlin.text.m RES_720 = new kotlin.text.m("\\b720p\\b", 0);
    private static final kotlin.text.m RES_480 = new kotlin.text.m("\\b480p\\b", 0);
    private static final kotlin.text.m DV = new kotlin.text.m("\\b(DV|DoVi|Dolby[\\s._-]*Vision)\\b", 0);
    private static final kotlin.text.m HDR10_PLUS = new kotlin.text.m("\\b(HDR10\\+|HDR10\\s*PLUS|HDR\\s*10\\s*\\+)\\b", 0);
    private static final kotlin.text.m HDR10 = new kotlin.text.m("\\bHDR10\\b", 0);
    private static final kotlin.text.m HDR = new kotlin.text.m("\\bHDR\\b", 0);
    private static final kotlin.text.m IMAX = new kotlin.text.m("\\bIMAX\\b", 0);
    private static final kotlin.text.m ATMOS = new kotlin.text.m("\\bATMOS\\b", 0);
    private static final kotlin.text.m TRUEHD = new kotlin.text.m("\\bTRUEHD\\b", 0);
    private static final kotlin.text.m DTS_X = new kotlin.text.m("\\bDTS[-_.: ]?X\\b", 0);
    private static final kotlin.text.m DTS_HD_MA = new kotlin.text.m("\\bDTS[-_. ]?(?:HD[-_. ]?)?(?:MA|MASTER)\\b", 0);
    private static final kotlin.text.m DTS_HD = new kotlin.text.m("\\bDTS[-_. ]?HD\\b", 0);
    private static final kotlin.text.m DTS = new kotlin.text.m("\\bDTS\\b", 0);
    private static final kotlin.text.m DD_PLUS = new kotlin.text.m("\\b(DDP|DD\\+|EAC-?3|E-?AC-?3)\\b", 0);
    private static final kotlin.text.m DD = new kotlin.text.m("\\b(AC-?3|DD(?:[ ._-]?5[ ._-]?1)?|DOLBY[ ._-]?DIGITAL)\\b", 0);

    private BadgeRegex() {
    }

    public final kotlin.text.m getATMOS() {
        return ATMOS;
    }

    public final kotlin.text.m getDD() {
        return DD;
    }

    public final kotlin.text.m getDD_PLUS() {
        return DD_PLUS;
    }

    public final kotlin.text.m getDTS() {
        return DTS;
    }

    public final kotlin.text.m getDTS_HD() {
        return DTS_HD;
    }

    public final kotlin.text.m getDTS_HD_MA() {
        return DTS_HD_MA;
    }

    public final kotlin.text.m getDTS_X() {
        return DTS_X;
    }

    public final kotlin.text.m getDV() {
        return DV;
    }

    public final kotlin.text.m getHDR() {
        return HDR;
    }

    public final kotlin.text.m getHDR10() {
        return HDR10;
    }

    public final kotlin.text.m getHDR10_PLUS() {
        return HDR10_PLUS;
    }

    public final kotlin.text.m getIMAX() {
        return IMAX;
    }

    public final kotlin.text.m getRES_1080() {
        return RES_1080;
    }

    public final kotlin.text.m getRES_480() {
        return RES_480;
    }

    public final kotlin.text.m getRES_4K() {
        return RES_4K;
    }

    public final kotlin.text.m getRES_720() {
        return RES_720;
    }

    public final kotlin.text.m getTRUEHD() {
        return TRUEHD;
    }
}
