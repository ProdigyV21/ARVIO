package com.arflix.tv.data.telegram;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.arflix.tv.data.repository.g;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.collections.n0;
import kotlin.collections.s;
import kotlin.text.k;
import kotlin.text.m;
import kotlin.text.o;
import kotlin.text.u;
import x6.x;
import x7.i;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000f\u0010\u000eJ[\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0017\u0010\u0018J=\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u00192\u0006\u0010\f\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u001a\u0010\u001bJM\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00040\u00192\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u001c\u001a\u00020\u0004¢\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010!\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020\u0004¢\u0006\u0004\b!\u0010\"¨\u0006$"}, d2 = {"Lcom/arflix/tv/data/telegram/TelegramSearchMatcher;", "", "<init>", "()V", "", "text", "Lx6/x;", "", "extractSeasonEpisode", "(Ljava/lang/String;)Lx6/x;", "extractEpisodeOnly", "(Ljava/lang/String;)Ljava/lang/Integer;", LinkHeader.Parameters.Title, "cleanTitle", "(Ljava/lang/String;)Ljava/lang/String;", "normalize", "fileName", MediaTrack.ROLE_CAPTION, "localizedTitle", "englishTitle", "year", "season", "episode", "score", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)I", "", "buildMovieQueries", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;", "languageCode", "buildSeriesQueries", "(Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;", CmcdData.STREAMING_FORMAT_SS, "", "isHebrew", "(Ljava/lang/String;)Z", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TelegramSearchMatcher {
    public static final int $stable = 0;
    private static final String SEP = "[\\s._\\-x+,&:]{0,2}";
    private static final String SEP_MID = "[\\s._\\-x+,&:]{0,4}";
    private static final m EPISODE_PATTERN = new m("[Ss][e]?(?:ason)?[\\s._\\-x+,&:]{0,2}(\\d{1,2})[\\s._\\-x+,&:]{0,4}[Ee][p]?(?:isode)?[\\s._\\-x+,&:]{0,2}(\\d{1,4})|ע(?:ונה)?[\\s._\\-x+,&:]{0,2}(\\d{1,2})[\\s._\\-x+,&:]{0,4}פ(?:רק)?[\\s._\\-x+,&:]{0,2}(\\d{1,4})", 0);
    private static final m EPISODE_ONLY_PATTERN = new m("פ(?:רק)?[\\s._\\-x+,&:]{0,2}(\\d{1,4})");
    private static final m YEAR_PATTERN = new m("\\b(19|20)\\d{2}\\b");
    private static final m NOISE = new m("[._\\-\\[\\]()'\",!?:]");
    private static final m MULTI_SPACE = new m("\\s+");
    private static final m SIZE_SUFFIX = new m("\\.(mkv|mp4|avi|mov|wmv|m4v|ts|m2ts)$", 0);
    private static final i HEBREW_RANGE = new i(1424, 1535, 1);

    @Inject
    public TelegramSearchMatcher() {
    }

    public static /* synthetic */ List buildMovieQueries$default(TelegramSearchMatcher telegramSearchMatcher, String str, Integer num, String str2, String str3, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            str2 = null;
        }
        if ((i10 & 8) != 0) {
            str3 = null;
        }
        return telegramSearchMatcher.buildMovieQueries(str, num, str2, str3);
    }

    public static /* synthetic */ List buildSeriesQueries$default(TelegramSearchMatcher telegramSearchMatcher, String str, int i10, int i11, String str2, String str3, String str4, int i12, Object obj) {
        if ((i12 & 8) != 0) {
            str2 = null;
        }
        if ((i12 & 16) != 0) {
            str3 = null;
        }
        if ((i12 & 32) != 0) {
            str4 = "en";
        }
        return telegramSearchMatcher.buildSeriesQueries(str, i10, i11, str2, str3, str4);
    }

    private final String cleanTitle(String title) {
        return TelegramSearchMatcherRegexes.INSTANCE.getDIACRITICS_REGEX().f("", Normalizer.normalize(o.L0(u.O(u.O(title, ":", "", false), "  ", " ", false)).toString(), Normalizer.Form.NFKD));
    }

    private final Integer extractEpisodeOnly(String text) {
        m mVar = EPISODE_ONLY_PATTERN;
        k kVarB = m.b(mVar, text);
        if (kVarB == null && (kVarB = m.b(mVar, normalize(text))) == null) {
            return null;
        }
        return u.R((String) ((n0) kVarB.b()).get(1));
    }

    private final x extractSeasonEpisode(String text) {
        m mVar = EPISODE_PATTERN;
        k kVarB = m.b(mVar, text);
        if (kVarB == null && (kVarB = m.b(mVar, normalize(text))) == null) {
            return null;
        }
        Integer numR = u.R((String) ((n0) kVarB.b()).get(1));
        if (numR == null && (numR = u.R((String) ((n0) kVarB.b()).get(3))) == null) {
            return null;
        }
        int iIntValue = numR.intValue();
        Integer numR2 = u.R((String) ((n0) kVarB.b()).get(2));
        if (numR2 == null && (numR2 = u.R((String) ((n0) kVarB.b()).get(4))) == null) {
            return null;
        }
        return new x(Integer.valueOf(iIntValue), Integer.valueOf(numR2.intValue()));
    }

    private final String normalize(String text) {
        return g.o(MULTI_SPACE, NOISE.f(" ", SIZE_SUFFIX.f("", text)), " ").toLowerCase(Locale.ROOT);
    }

    public static /* synthetic */ int score$default(TelegramSearchMatcher telegramSearchMatcher, String str, String str2, String str3, String str4, String str5, Integer num, Integer num2, Integer num3, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            str4 = null;
        }
        if ((i10 & 16) != 0) {
            str5 = null;
        }
        return telegramSearchMatcher.score(str, str2, str3, str4, str5, num, num2, num3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int score$lambda$2(kotlin.text.i iVar) {
        return Integer.parseInt(iVar.getValue());
    }

    public final List<String> buildMovieQueries(String title, Integer year, String localizedTitle, String englishTitle) {
        String strCleanTitle;
        if (englishTitle == null || (strCleanTitle = cleanTitle(englishTitle)) == null) {
            strCleanTitle = cleanTitle(title);
        }
        String strCleanTitle2 = localizedTitle != null ? cleanTitle(localizedTitle) : null;
        ArrayList arrayList = new ArrayList();
        if (year != null) {
            arrayList.add(strCleanTitle + " " + year);
        }
        arrayList.add(strCleanTitle);
        if (strCleanTitle2 != null && !strCleanTitle2.equalsIgnoreCase(strCleanTitle)) {
            if (year != null) {
                arrayList.add(strCleanTitle2 + " " + year);
            }
            arrayList.add(strCleanTitle2);
        }
        return kotlin.collections.x.g0(arrayList);
    }

    public final List<String> buildSeriesQueries(String title, int season, int episode, String localizedTitle, String englishTitle, String languageCode) {
        String strCleanTitle;
        if (englishTitle == null || (strCleanTitle = cleanTitle(englishTitle)) == null) {
            strCleanTitle = cleanTitle(title);
        }
        String strCleanTitle2 = localizedTitle != null ? cleanTitle(localizedTitle) : null;
        boolean z = strCleanTitle2 == null || strCleanTitle2.equalsIgnoreCase(strCleanTitle);
        String strValueOf = String.valueOf(season);
        String strValueOf2 = String.valueOf(episode);
        String strO0 = o.o0(2, String.valueOf(season));
        String strO02 = o.o0(2, String.valueOf(episode));
        ArrayList arrayList = new ArrayList();
        if (languageCode.equals("he")) {
            String str = (z || strCleanTitle2 == null) ? strCleanTitle : strCleanTitle2;
            kotlin.collections.x.b0(arrayList, t7.a.E(androidx.compose.material3.d.o(str, " ע", strValueOf, " פ", strValueOf2), androidx.compose.material3.d.o(str, " ע", strValueOf, "פ", strValueOf2), androidx.compose.material3.d.o(str, " עונה ", strValueOf, " פרק ", strValueOf2)));
            if (season == 1) {
                kotlin.collections.x.b0(arrayList, t7.a.E(androidx.compose.foundation.c.t(str, " פ", strValueOf2), androidx.compose.foundation.c.t(str, " פרק ", strValueOf2)));
            }
        }
        if (!z) {
            kotlin.collections.x.b0(arrayList, t7.a.E(androidx.compose.material3.d.o(strCleanTitle2, " s", strValueOf, "e", strValueOf2), androidx.compose.material3.d.o(strCleanTitle2, " s", strO0, "e", strO02), androidx.compose.material3.d.o(strCleanTitle2, " s", strValueOf, " e", strValueOf2), androidx.compose.material3.d.o(strCleanTitle2, " s", strO0, " e", strO02)));
        }
        kotlin.collections.x.b0(arrayList, t7.a.E(androidx.compose.material3.d.o(strCleanTitle, " s", strValueOf, "e", strValueOf2), androidx.compose.material3.d.o(strCleanTitle, " s", strO0, "e", strO02), androidx.compose.material3.d.o(strCleanTitle, " s", strValueOf, " e", strValueOf2), androidx.compose.material3.d.o(strCleanTitle, " s", strO0, " e", strO02)));
        ArrayList arrayList2 = new ArrayList(s.U(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((String) it.next()).toLowerCase(Locale.ROOT));
        }
        return kotlin.collections.x.g0(arrayList2);
    }

    public final boolean isHebrew(String s10) {
        for (int i10 = 0; i10 < s10.length(); i10++) {
            char cCharAt = s10.charAt(i10);
            i iVar = HEBREW_RANGE;
            int i11 = iVar.f22619i;
            int i12 = iVar.f22620l;
            if (i11 <= cCharAt && cCharAt <= i12) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:78:0x011d, code lost:
    
        if (r9 == null) goto L80;
     */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0096 A[EDGE_INSN: B:91:0x0096->B:41:0x0096 BREAK  A[LOOP:0: B:37:0x007b->B:92:?]] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int score(java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.Integer r13, java.lang.Integer r14, java.lang.Integer r15) {
        /*
            Method dump skipped, instruction units count: 317
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.telegram.TelegramSearchMatcher.score(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer):int");
    }
}
