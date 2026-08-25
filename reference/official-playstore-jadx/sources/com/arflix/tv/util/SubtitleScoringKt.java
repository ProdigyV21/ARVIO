package com.arflix.tv.util;

import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.arflix.tv.data.repository.g;
import com.google.android.gms.cast.CredentialsData;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.text.i;
import kotlin.text.k;
import kotlin.text.m;
import kotlin.text.o;
import qb.d;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\f\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001d\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0000¢\u0006\u0004\b\u0007\u0010\b\"\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b\"\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00000\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e\"\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00000\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u000e\"\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00000\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u000e\"\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00000\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u000e\"\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00000\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u000e\"\u0014\u0010\u0013\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u000b\"\u0014\u0010\u0014\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u000b\"\u0014\u0010\u0015\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u000b\"\u0014\u0010\u0016\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u000b\"\u0014\u0010\u0017\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u000b¨\u0006\u0018"}, d2 = {"", "token", "", "tokenWeight", "(Ljava/lang/String;)I", "streamSource", "subtitleId", "weightedSubtitleScore", "(Ljava/lang/String;Ljava/lang/String;)I", "Lkotlin/text/m;", "EPISODE_RE", "Lkotlin/text/m;", "", "RESOLUTIONS", "Ljava/util/Set;", "SOURCES", "CODECS", "AUDIO", "NOISE", "FILE_EXT_RE", "SPLIT_SEASON_RE", "PURE_NUMBERS_RE", "SUBTITLE_BRACKET_RE", "SEPARATOR_RE", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SubtitleScoringKt {
    private static final m EPISODE_RE = new m("s\\d{1,2}e\\d{1,2}|\\d{1,2}x\\d{1,2}", 0);
    private static final Set<String> RESOLUTIONS = r.p0(new String[]{"480p", "576p", "720p", "1080p", "1080i", "2160p", "4k", "4320p", "8k", "uhd"});
    private static final Set<String> SOURCES = r.p0(new String[]{"bluray", "blu", "bdrip", "bdremux", "webrip", "webdl", CredentialsData.CREDENTIALS_TYPE_WEB, "hdrip", "hdtv", "pdtv", "dsr", "dvd", "dvdrip", "dvdscr", "remux", "amzn", "nf", "hulu", "dsnp", "atvp", "hmax", "pcok", "cr", "pmtp"});
    private static final Set<String> CODECS = r.p0(new String[]{"x264", "x265", "h264", "h265", "hevc", "avc", "xvid", "divx", "vp9", "av1", "mpeg2", "mpeg4"});
    private static final Set<String> AUDIO = r.p0(new String[]{HlsSegmentFormat.AAC, HlsSegmentFormat.AC3, "eac3", "dts", "atmos", "truehd", "flac", HlsSegmentFormat.MP3, "opus", "vorbis", "dd5", "dd2", "ddplus"});
    private static final Set<String> NOISE = r.p0(new String[]{"ntsc", "pal", "proper", "repack", "readnfo", "extended", "theatrical", "imax", "hdr", "hdr10", "hdr10plus", "sdr", "dv", "dolbyvision", "hlg", "dubbed", "subbed", CmcdConfiguration.KEY_DEADLINE, "rip", "complete", "internal", "limited", "dc", "directors", "cut", "unrated", "retail", "esub", "engsub", "hsub", "sub", "subs", "51", "71", "20", "english", "french", "german", "spanish", "italian", "portuguese", "arabic", "russian", "chinese", "japanese", "korean", "turkish", "hebrew", "hindi", "dutch", "polish", "czech", "hungarian", "romanian", "swedish", "norwegian", "danish", "finnish", "greek", "thai", "vietnamese", "indonesian", "malay"});
    private static final m FILE_EXT_RE = new m("\\.(mkv|mp4|avi|mov|wmv|flv|m4v|ts|m2ts)$", 0);
    private static final m SPLIT_SEASON_RE = new m("(s\\d{1,2})\\s+(e\\d{1,2})", 0);
    private static final m PURE_NUMBERS_RE = new m("\\d+");
    private static final m SUBTITLE_BRACKET_RE = new m("^\\[[^]]+]");
    private static final m SEPARATOR_RE = new m("[.\\-_\\s]+");

    private static final int tokenWeight(String str) {
        if (PURE_NUMBERS_RE.e(str)) {
            return 0;
        }
        if (EPISODE_RE.e(str)) {
            return 8;
        }
        if (RESOLUTIONS.contains(str)) {
            return 3;
        }
        if (SOURCES.contains(str)) {
            return 4;
        }
        if (CODECS.contains(str) || AUDIO.contains(str)) {
            return 2;
        }
        return NOISE.contains(str) ? 1 : 10;
    }

    public static final int weightedSubtitleScore(String str, String str2) {
        if (!o.h0(str) && !o.h0(str2)) {
            String strO = g.o(SUBTITLE_BRACKET_RE, str2, "");
            x xVarWeightedSubtitleScore$bodyAndGroup = weightedSubtitleScore$bodyAndGroup(weightedSubtitleScore$normalise(str));
            String str3 = (String) xVarWeightedSubtitleScore$bodyAndGroup.f22608i;
            String str4 = (String) xVarWeightedSubtitleScore$bodyAndGroup.f22609l;
            x xVarWeightedSubtitleScore$bodyAndGroup2 = weightedSubtitleScore$bodyAndGroup(weightedSubtitleScore$normalise(strO));
            String str5 = (String) xVarWeightedSubtitleScore$bodyAndGroup2.f22608i;
            String str6 = (String) xVarWeightedSubtitleScore$bodyAndGroup2.f22609l;
            List listH = SEPARATOR_RE.h(str3.toLowerCase(Locale.ROOT));
            ArrayList<String> arrayList = new ArrayList();
            for (Object obj : listH) {
                if (((String) obj).length() > 1) {
                    arrayList.add(obj);
                }
            }
            List listH2 = SEPARATOR_RE.h(str5.toLowerCase(Locale.ROOT));
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : listH2) {
                if (((String) obj2).length() > 1) {
                    arrayList2.add(obj2);
                }
            }
            Set setG1 = kotlin.collections.x.g1(arrayList2);
            int i10 = 0;
            int i11 = 0;
            for (String str7 : arrayList) {
                int i12 = tokenWeight(str7);
                if (i12 != 0) {
                    i10 += i12;
                    if (setG1.contains(str7)) {
                        i11 += i12;
                    }
                }
            }
            if (str4 != null) {
                i10 += 5;
                if (str4.equals(str6)) {
                    i11 += 5;
                }
            }
            if (i10 != 0) {
                return d.n((i11 * 100) / i10, 0, 100);
            }
        }
        return 0;
    }

    private static final x weightedSubtitleScore$bodyAndGroup(String str) {
        int iK0 = o.k0(str, '-', 0, 6);
        if (iK0 < 0) {
            return new x(str, null);
        }
        String strF = FILE_EXT_RE.f("", o.L0(str.substring(iK0 + 1).toLowerCase(Locale.ROOT)).toString());
        return new x(str.substring(0, iK0), o.h0(strF) ? null : strF);
    }

    private static final String weightedSubtitleScore$normalise(String str) {
        m mVar = SPLIT_SEASON_RE;
        mVar.getClass();
        k kVarB = m.b(mVar, str);
        if (kVarB == null) {
            return str.toString();
        }
        int length = str.length();
        StringBuilder sb2 = new StringBuilder(length);
        int i10 = 0;
        do {
            sb2.append((CharSequence) str, i10, kVarB.d().f22619i);
            sb2.append(weightedSubtitleScore$normalise$lambda$0(kVarB));
            i10 = kVarB.d().f22620l + 1;
            kVarB = kVarB.next();
            if (i10 >= length) {
                break;
            }
        } while (kVarB != null);
        if (i10 < length) {
            sb2.append((CharSequence) str, i10, length);
        }
        return sb2.toString();
    }

    private static final CharSequence weightedSubtitleScore$normalise$lambda$0(i iVar) {
        Object obj = iVar.b().get(1);
        Object obj2 = iVar.b().get(2);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(obj);
        sb2.append(obj2);
        return sb2.toString();
    }
}
