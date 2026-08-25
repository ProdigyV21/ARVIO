package com.arflix.tv.data.repository;

import io.ktor.http.LinkHeader;
import java.text.Normalizer;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005JC\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\b2\b\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bJ%\u0010\u0013\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerMatcher;", "", "<init>", "()V", "normalizeTitle", "", LinkHeader.Parameters.Title, "score", "", "requestedTitle", "requestedYear", "imdbId", "tmdbId", "tvdbId", "candidate", "Lcom/arflix/tv/data/repository/HomeServerCandidateInfo;", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Lcom/arflix/tv/data/repository/HomeServerCandidateInfo;)I", "isAcceptable", "", "isLikelySameVersion", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/arflix/tv/data/repository/HomeServerCandidateInfo;)Z", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HomeServerMatcher {
    public static final int $stable = 0;
    public static final HomeServerMatcher INSTANCE = new HomeServerMatcher();

    private HomeServerMatcher() {
    }

    public final boolean isAcceptable(int score) {
        return score >= 150 || score >= 900;
    }

    public final boolean isLikelySameVersion(String requestedTitle, Integer requestedYear, HomeServerCandidateInfo candidate) {
        String strNormalizeTitle = normalizeTitle(requestedTitle);
        String strNormalizeTitle2 = normalizeTitle(candidate.getTitle());
        if (kotlin.text.o.h0(strNormalizeTitle) || !kotlin.jvm.internal.p.a(strNormalizeTitle, strNormalizeTitle2)) {
            return false;
        }
        Integer productionYear = candidate.getProductionYear();
        if (productionYear != null) {
            int iIntValue = productionYear.intValue();
            if (requestedYear != null && Math.abs(requestedYear.intValue() - iIntValue) > 1) {
                return false;
            }
        }
        return true;
    }

    public final String normalizeTitle(String title) {
        String strNormalize = Normalizer.normalize(title, Normalizer.Form.NFD);
        HomeServerRegexes homeServerRegexes = HomeServerRegexes.INSTANCE;
        return homeServerRegexes.getMULTI_SPACE_REGEX().f(" ", kotlin.text.o.L0(homeServerRegexes.getARTICLES_REGEX().f(" ", homeServerRegexes.getNON_ALPHA_NUM_REGEX().f(" ", kotlin.text.u.O(homeServerRegexes.getDIACRITICS_REGEX().f("", strNormalize).toLowerCase(Locale.US), "&", " and ", false)))).toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int score(java.lang.String r6, java.lang.Integer r7, java.lang.String r8, java.lang.Integer r9, java.lang.Integer r10, com.arflix.tv.data.repository.HomeServerCandidateInfo r11) {
        /*
            Method dump skipped, instruction units count: 267
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerMatcher.score(java.lang.String, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Integer, com.arflix.tv.data.repository.HomeServerCandidateInfo):int");
    }
}
