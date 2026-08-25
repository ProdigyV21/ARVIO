package com.arflix.tv.ui.components;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\bM\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0006\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0006\u001a\u0004\b\u0010\u0010\bR\u0017\u0010\u0011\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0006\u001a\u0004\b\u0012\u0010\bR\u0017\u0010\u0013\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0006\u001a\u0004\b\u0014\u0010\bR\u0017\u0010\u0015\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0006\u001a\u0004\b\u0016\u0010\bR\u0017\u0010\u0017\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0006\u001a\u0004\b\u0018\u0010\bR\u0017\u0010\u0019\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u0006\u001a\u0004\b\u001a\u0010\bR\u0017\u0010\u001b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u0006\u001a\u0004\b\u001c\u0010\bR\u0017\u0010\u001d\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u0006\u001a\u0004\b\u001e\u0010\bR\u0017\u0010\u001f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u001f\u0010\u0006\u001a\u0004\b \u0010\bR\u0017\u0010!\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b!\u0010\u0006\u001a\u0004\b\"\u0010\bR\u0017\u0010#\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b#\u0010\u0006\u001a\u0004\b$\u0010\bR\u0017\u0010%\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b%\u0010\u0006\u001a\u0004\b&\u0010\bR\u0017\u0010'\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b'\u0010\u0006\u001a\u0004\b(\u0010\bR\u0017\u0010)\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b)\u0010\u0006\u001a\u0004\b*\u0010\bR\u0017\u0010+\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b+\u0010\u0006\u001a\u0004\b,\u0010\bR\u0017\u0010-\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b-\u0010\u0006\u001a\u0004\b.\u0010\bR\u0017\u0010/\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b/\u0010\u0006\u001a\u0004\b0\u0010\bR\u0017\u00101\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b1\u0010\u0006\u001a\u0004\b2\u0010\bR\u0017\u00103\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b3\u0010\u0006\u001a\u0004\b4\u0010\bR\u0017\u00105\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b5\u0010\u0006\u001a\u0004\b6\u0010\bR\u0017\u00107\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b7\u0010\u0006\u001a\u0004\b8\u0010\bR\u0017\u00109\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b9\u0010\u0006\u001a\u0004\b:\u0010\bR\u0017\u0010;\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b;\u0010\u0006\u001a\u0004\b<\u0010\bR\u0017\u0010=\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b=\u0010\u0006\u001a\u0004\b>\u0010\bR\u0017\u0010?\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b?\u0010\u0006\u001a\u0004\b@\u0010\bR\u0017\u0010A\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\bA\u0010\u0006\u001a\u0004\bB\u0010\bR\u0017\u0010C\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\bC\u0010\u0006\u001a\u0004\bD\u0010\bR\u0017\u0010E\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\bE\u0010\u0006\u001a\u0004\bF\u0010\bR\u0017\u0010G\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\bG\u0010\u0006\u001a\u0004\bH\u0010\bR\u0017\u0010I\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\bI\u0010\u0006\u001a\u0004\bJ\u0010\bR\u0017\u0010K\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\bK\u0010\u0006\u001a\u0004\bL\u0010\bR\u0017\u0010M\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\bM\u0010\u0006\u001a\u0004\bN\u0010\bR\u0017\u0010O\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\bO\u0010\u0006\u001a\u0004\bP\u0010\b¨\u0006Q"}, d2 = {"Lcom/arflix/tv/ui/components/StreamRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "AV1", "Lkotlin/text/m;", "getAV1", "()Lkotlin/text/m;", "HEVC", "getHEVC", "H264", "getH264", "REMUX", "getREMUX", "BLURAY", "getBLURAY", "WEBDL", "getWEBDL", "WEBRIP", "getWEBRIP", "HDTV", "getHDTV", "CAM", "getCAM", "ATMOS", "getATMOS", "TRUEHD", "getTRUEHD", "DTS", "getDTS", "DTS_X", "getDTS_X", "DTS_HD_MA", "getDTS_HD_MA", "DTS_HD_ONLY", "getDTS_HD_ONLY", "DD_PLUS", "getDD_PLUS", "DD", "getDD", "CH71", "getCH71", "CH51", "getCH51", "MULTI_AUDIO", "getMULTI_AUDIO", "LANGUAGE_HINT", "getLANGUAGE_HINT", "DV", "getDV", "HDR10_PLUS", "getHDR10_PLUS", "HDR10", "getHDR10", "HDR", "getHDR", "BITRATE", "getBITRATE", "EDITION", "getEDITION", "IMAX", "getIMAX", "WHITESPACE", "getWHITESPACE", "SIZE_PATTERN_1", "getSIZE_PATTERN_1", "SIZE_PATTERN_2", "getSIZE_PATTERN_2", "SIZE_PATTERN_3", "getSIZE_PATTERN_3", "EXTENSION_REMOVAL", "getEXTENSION_REMOVAL", "YEAR_REMOVAL", "getYEAR_REMOVAL", "SIZE_LINE_PATTERN", "getSIZE_LINE_PATTERN", "CHANNEL_TAG_PATTERN", "getCHANNEL_TAG_PATTERN", "MD_NOISE", "getMD_NOISE", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class StreamRegexes {
    public static final StreamRegexes INSTANCE = new StreamRegexes();
    private static final kotlin.text.m AV1 = new kotlin.text.m("\\bAV1\\b", 0);
    private static final kotlin.text.m HEVC = new kotlin.text.m("\\b(HEVC|X265|H265)\\b", 0);
    private static final kotlin.text.m H264 = new kotlin.text.m("\\b(H264|X264|AVC)\\b", 0);
    private static final kotlin.text.m REMUX = new kotlin.text.m("\\bREMUX\\b", 0);
    private static final kotlin.text.m BLURAY = new kotlin.text.m("\\b(BLURAY|BDRIP|BDREMUX)\\b", 0);
    private static final kotlin.text.m WEBDL = new kotlin.text.m("\\b(WEB[- .]?DL|WEBDL)\\b", 0);
    private static final kotlin.text.m WEBRIP = new kotlin.text.m("\\bWEB[- .]?RIP\\b", 0);
    private static final kotlin.text.m HDTV = new kotlin.text.m("\\bHDTV\\b", 0);
    private static final kotlin.text.m CAM = new kotlin.text.m("\\b(CAM|TS|TELESYNC|HDCAM)\\b", 0);
    private static final kotlin.text.m ATMOS = new kotlin.text.m("\\bATMOS\\b", 0);
    private static final kotlin.text.m TRUEHD = new kotlin.text.m("\\bTRUEHD\\b", 0);
    private static final kotlin.text.m DTS = new kotlin.text.m("\\b(DTS[- .]?HD|DTS|DDP|EAC3|AC3|AAC)\\b", 0);
    private static final kotlin.text.m DTS_X = new kotlin.text.m("\\bDTS[-_.: ]?X\\b", 0);
    private static final kotlin.text.m DTS_HD_MA = new kotlin.text.m("\\bDTS[-_. ]?(?:HD[-_. ]?)?(?:MA|MASTER)\\b", 0);
    private static final kotlin.text.m DTS_HD_ONLY = new kotlin.text.m("\\bDTS[-_. ]?HD\\b", 0);
    private static final kotlin.text.m DD_PLUS = new kotlin.text.m("\\b(DDP|DD\\+|EAC-?3|E-?AC-?3)\\b", 0);
    private static final kotlin.text.m DD = new kotlin.text.m("\\b(AC-?3|DD(?:[ ._-]?5[ ._-]?1)?|DOLBY[ ._-]?DIGITAL)\\b", 0);
    private static final kotlin.text.m CH71 = new kotlin.text.m("\\b7[ .]?1\\b", 0);
    private static final kotlin.text.m CH51 = new kotlin.text.m("\\b5[ .]?1\\b", 0);
    private static final kotlin.text.m MULTI_AUDIO = new kotlin.text.m("\\b(MULTI|DUAL[ .-]?AUDIO|MULTI[ .-]?AUDIO)\\b", 0);
    private static final kotlin.text.m LANGUAGE_HINT = new kotlin.text.m("\\b(ENG|ENGLISH|HIN|HINDI|TAM|TAMIL|TEL|TELUGU|JPN|JAPANESE|KOR|KOREAN|SPA|SPANISH|FRE|FRENCH|GER|GERMAN|ITA|ITALIAN)\\b", 0);
    private static final kotlin.text.m DV = new kotlin.text.m("\\b(DV|DoVi|Dolby[\\s._-]*Vision)\\b", 0);
    private static final kotlin.text.m HDR10_PLUS = new kotlin.text.m("\\b(HDR10\\+|HDR10\\s*PLUS|HDR\\s*10\\s*\\+)\\b", 0);
    private static final kotlin.text.m HDR10 = new kotlin.text.m("\\bHDR10\\b", 0);
    private static final kotlin.text.m HDR = new kotlin.text.m("\\bHDR(10\\+?|10)?\\b", 0);
    private static final kotlin.text.m BITRATE = new kotlin.text.m("(\\d+(?:\\.\\d+)?)\\s*(?:Mbps|ᴹᵇᵖˢ)", 0);
    private static final kotlin.text.m EDITION = new kotlin.text.m("\\b(Extended(?:\\s+Cut)?|Director'?s\\s+Cut|Theatrical(?:\\s+Cut)?|Unrated|Uncut|Remastered|Special\\s+Edition|Ultimate\\s+Edition|Criterion)\\b", 0);
    private static final kotlin.text.m IMAX = new kotlin.text.m("\\bIMAX\\b", 0);
    private static final kotlin.text.m WHITESPACE = new kotlin.text.m("\\s+");
    private static final kotlin.text.m SIZE_PATTERN_1 = new kotlin.text.m("(\\d+(?:\\.\\d+)?)\\s*(TB|GB|MB|KB)");
    private static final kotlin.text.m SIZE_PATTERN_2 = new kotlin.text.m("(\\d+(?:\\.\\d+)?)\\s*(TIB|GIB|MIB|KIB)");
    private static final kotlin.text.m SIZE_PATTERN_3 = new kotlin.text.m("^(\\d+(?:\\.\\d+)?)$");
    private static final kotlin.text.m EXTENSION_REMOVAL = new kotlin.text.m("\\.(mkv|mp4|avi|mov|ts)$", 0);
    private static final kotlin.text.m YEAR_REMOVAL = new kotlin.text.m("\\b(19|20)\\d{2}\\b.*");
    private static final kotlin.text.m SIZE_LINE_PATTERN = new kotlin.text.m("^[╰└].*\\d+(\\.\\d+)?\\s*(GB|MB|KB|TB).*$", 0);
    private static final kotlin.text.m CHANNEL_TAG_PATTERN = new kotlin.text.m("^\\[.+]$");
    private static final kotlin.text.m MD_NOISE = new kotlin.text.m("[`*_]{1,4}");

    private StreamRegexes() {
    }

    public final kotlin.text.m getATMOS() {
        return ATMOS;
    }

    public final kotlin.text.m getAV1() {
        return AV1;
    }

    public final kotlin.text.m getBITRATE() {
        return BITRATE;
    }

    public final kotlin.text.m getBLURAY() {
        return BLURAY;
    }

    public final kotlin.text.m getCAM() {
        return CAM;
    }

    public final kotlin.text.m getCH51() {
        return CH51;
    }

    public final kotlin.text.m getCH71() {
        return CH71;
    }

    public final kotlin.text.m getCHANNEL_TAG_PATTERN() {
        return CHANNEL_TAG_PATTERN;
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

    public final kotlin.text.m getDTS_HD_MA() {
        return DTS_HD_MA;
    }

    public final kotlin.text.m getDTS_HD_ONLY() {
        return DTS_HD_ONLY;
    }

    public final kotlin.text.m getDTS_X() {
        return DTS_X;
    }

    public final kotlin.text.m getDV() {
        return DV;
    }

    public final kotlin.text.m getEDITION() {
        return EDITION;
    }

    public final kotlin.text.m getEXTENSION_REMOVAL() {
        return EXTENSION_REMOVAL;
    }

    public final kotlin.text.m getH264() {
        return H264;
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

    public final kotlin.text.m getHDTV() {
        return HDTV;
    }

    public final kotlin.text.m getHEVC() {
        return HEVC;
    }

    public final kotlin.text.m getIMAX() {
        return IMAX;
    }

    public final kotlin.text.m getLANGUAGE_HINT() {
        return LANGUAGE_HINT;
    }

    public final kotlin.text.m getMD_NOISE() {
        return MD_NOISE;
    }

    public final kotlin.text.m getMULTI_AUDIO() {
        return MULTI_AUDIO;
    }

    public final kotlin.text.m getREMUX() {
        return REMUX;
    }

    public final kotlin.text.m getSIZE_LINE_PATTERN() {
        return SIZE_LINE_PATTERN;
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

    public final kotlin.text.m getTRUEHD() {
        return TRUEHD;
    }

    public final kotlin.text.m getWEBDL() {
        return WEBDL;
    }

    public final kotlin.text.m getWEBRIP() {
        return WEBRIP;
    }

    public final kotlin.text.m getWHITESPACE() {
        return WHITESPACE;
    }

    public final kotlin.text.m getYEAR_REMOVAL() {
        return YEAR_REMOVAL;
    }
}
