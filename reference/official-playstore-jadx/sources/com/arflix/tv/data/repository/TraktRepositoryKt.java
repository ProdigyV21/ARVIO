package com.arflix.tv.data.repository;

import com.arflix.tv.data.api.TmdbEpisode;
import com.arflix.tv.data.api.TmdbTvSeason;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\r\u001a1\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0002\u0010\u0007\u001a7\u0010\b\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0003H\u0002¢\u0006\u0002\u0010\u000b\u001a\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002\u001a\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002\u001a\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002\u001a\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u000fH\u0002\u001a\u0012\u0010\u0016\u001a\u00020\u000f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u000fH\u0002\u001aA\u0010\u0018\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u00012\b\u0010\u001a\u001a\u0004\u0018\u00010\u00012\b\u0010\u001b\u001a\u0004\u0018\u00010\u00012\b\u0010\u001c\u001a\u0004\u0018\u00010\u00012\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0002\u0010\u001e¨\u0006\u001f"}, d2 = {"estimateWatchedEpisodesBeforeCurrent", "", "seasons", "", "Lcom/arflix/tv/data/api/TmdbTvSeason;", "currentSeason", "currentEpisode", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Integer;", "estimateAiredEpisodeCount", "currentSeasonEpisodes", "Lcom/arflix/tv/data/api/TmdbEpisode;", "(Ljava/util/List;Ljava/lang/Integer;Ljava/util/List;)Ljava/lang/Integer;", "isAlreadyAiredDate", "", "rawDate", "", "formatResumeClock", "totalSeconds", "", "formatTimeRemainingCompact", "parseRuntimeLabelSeconds", "label", "formatDateString", "dateStr", "buildEpisodeKey", "traktEpisodeId", "showTraktId", "showTmdbId", "season", "episode", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/String;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TraktRepositoryKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final String buildEpisodeKey(Integer num, Integer num2, Integer num3, Integer num4, Integer num5) {
        if (num != null) {
            return "trakt:" + num;
        }
        if (num2 != null && num4 != null && num5 != null) {
            return "show_trakt:" + num2 + ":" + num4 + ":" + num5;
        }
        if (num3 == null || num4 == null || num5 == null) {
            return null;
        }
        return "show_tmdb:" + num3 + ":" + num4 + ":" + num5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer estimateAiredEpisodeCount(List<TmdbTvSeason> list, Integer num, List<TmdbEpisode> list2) {
        Object next;
        int episodeCount;
        if (num != null) {
            ga.h hVar = new ga.h(ga.r.I(new ga.p(list, 3), new o0(1, num)));
            int i10 = 0;
            int i11 = 0;
            while (hVar.hasNext()) {
                int episodeCount2 = ((TmdbTvSeason) hVar.next()).getEpisodeCount();
                if (episodeCount2 < 0) {
                    episodeCount2 = 0;
                }
                i11 += episodeCount2;
            }
            if (list2 == null) {
                Iterator<T> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    if (((TmdbTvSeason) next).getSeasonNumber() == num.intValue()) {
                        break;
                    }
                }
                TmdbTvSeason tmdbTvSeason = (TmdbTvSeason) next;
                if (tmdbTvSeason != null) {
                    if (!isAlreadyAiredDate(tmdbTvSeason.getAirDate())) {
                        tmdbTvSeason = null;
                    }
                    if (tmdbTvSeason != null && (episodeCount = tmdbTvSeason.getEpisodeCount()) >= 0) {
                        i10 = episodeCount;
                    }
                }
            } else if (!list2.isEmpty()) {
                Iterator<T> it2 = list2.iterator();
                while (it2.hasNext()) {
                    if (isAlreadyAiredDate(((TmdbEpisode) it2.next()).getAirDate()) && (i10 = i10 + 1) < 0) {
                        t7.a.P();
                        throw null;
                    }
                }
            }
            int i12 = i11 + i10;
            Integer numValueOf = Integer.valueOf(i12);
            if (i12 > 0) {
                return numValueOf;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean estimateAiredEpisodeCount$lambda$0(Integer num, TmdbTvSeason tmdbTvSeason) {
        return tmdbTvSeason.getSeasonNumber() > 0 && tmdbTvSeason.getSeasonNumber() < num.intValue() && isAlreadyAiredDate(tmdbTvSeason.getAirDate());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer estimateWatchedEpisodesBeforeCurrent(List<TmdbTvSeason> list, Integer num, Integer num2) {
        if (num == null || num2 == null) {
            return null;
        }
        ga.h hVar = new ga.h(ga.r.I(new ga.p(list, 3), new o0(0, num)));
        int i10 = 0;
        while (hVar.hasNext()) {
            int episodeCount = ((TmdbTvSeason) hVar.next()).getEpisodeCount();
            if (episodeCount < 0) {
                episodeCount = 0;
            }
            i10 += episodeCount;
        }
        int iIntValue = num2.intValue() - 1;
        return Integer.valueOf(i10 + (iIntValue >= 0 ? iIntValue : 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean estimateWatchedEpisodesBeforeCurrent$lambda$0(Integer num, TmdbTvSeason tmdbTvSeason) {
        return tmdbTvSeason.getSeasonNumber() > 0 && tmdbTvSeason.getSeasonNumber() < num.intValue();
    }

    private static final String formatDateString(String str) throws Exception {
        if (str == null || str.length() == 0) {
            return "";
        }
        try {
            Locale locale = Locale.US;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MMMM d, yyyy", locale);
            Date date = simpleDateFormat.parse(str);
            if (date != null) {
                String str2 = simpleDateFormat2.format(date);
                if (str2 != null) {
                    return str2;
                }
            }
            return "";
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String formatResumeClock(long j10) {
        if (j10 < 0) {
            j10 = 0;
        }
        long j11 = 3600;
        long j12 = j10 / j11;
        long j13 = 60;
        long j14 = (j10 % j11) / j13;
        long j15 = j10 % j13;
        return j12 > 0 ? String.format("%d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j12), Long.valueOf(j14), Long.valueOf(j15)}, 3)) : String.format("%d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j14), Long.valueOf(j15)}, 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String formatTimeRemainingCompact(long j10) {
        if (j10 < 0) {
            j10 = 0;
        }
        if (j10 < 60) {
            return null;
        }
        long j11 = 3600;
        long j12 = j10 / j11;
        long j13 = (j10 % j11) / ((long) 60);
        if (j12 > 0 && j13 > 0) {
            return j12 + "hr " + j13 + "min left";
        }
        if (j12 > 0) {
            return j12 + "hr left";
        }
        return j13 + "min left";
    }

    private static final boolean isAlreadyAiredDate(String str) {
        String string = str != null ? kotlin.text.o.L0(str).toString() : null;
        if (string == null) {
            string = "";
        }
        if (string.length() == 0) {
            return false;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            simpleDateFormat.setLenient(false);
            Date date = simpleDateFormat.parse(string);
            if (date == null) {
                return false;
            }
            if (date.getTime() <= System.currentTimeMillis()) {
                return true;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long parseRuntimeLabelSeconds(String str) {
        List listB;
        String str2;
        Long lS;
        List listB2;
        String str3;
        Long lS2;
        String lowerCase = str.toLowerCase(Locale.US);
        if (!kotlin.text.o.h0(lowerCase)) {
            TraktRepoRegexes traktRepoRegexes = TraktRepoRegexes.INSTANCE;
            kotlin.text.k kVarB = kotlin.text.m.b(traktRepoRegexes.getHOURS_REGEX(), lowerCase);
            long jLongValue = (kVarB == null || (listB2 = kVarB.b()) == null || (str3 = (String) kotlin.collections.x.p0(1, listB2)) == null || (lS2 = kotlin.text.u.S(str3)) == null) ? 0L : lS2.longValue() * 60;
            kotlin.text.k kVarB2 = kotlin.text.m.b(traktRepoRegexes.getMINS_REGEX(), lowerCase);
            if (kVarB2 != null && (listB = kVarB2.b()) != null && (str2 = (String) kotlin.collections.x.p0(1, listB)) != null && (lS = kotlin.text.u.S(str2)) != null) {
                jLongValue += lS.longValue();
            }
            Long lValueOf = Long.valueOf(jLongValue);
            if (jLongValue <= 0) {
                lValueOf = null;
            }
            if (lValueOf != null) {
                return lValueOf.longValue() * 60;
            }
        }
        return 0L;
    }
}
