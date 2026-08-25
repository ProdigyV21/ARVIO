package com.arflix.tv.util;

import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.google.common.util.concurrent.r0;
import j$.time.LocalDate;
import j$.time.format.DateTimeFormatter;
import j$.time.temporal.ChronoUnit;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.text.i;
import kotlin.text.k;
import kotlin.text.m;
import kotlin.text.o;
import kotlin.text.t;
import x6.c0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000b\u001a\u0015\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\r\"\u001c\u0010\u0010\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011\"\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014\"\u0014\u0010\u0015\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/model/MediaItem;", "item", "j$/time/LocalDate", "now", "", "isInCinema", "(Lcom/arflix/tv/data/model/MediaItem;Lj$/time/LocalDate;)Z", "", "raw", "", "parseRatingValue", "(Ljava/lang/String;)F", "formatGenreName", "(Ljava/lang/String;)Ljava/lang/String;", "j$/time/format/DateTimeFormatter", "kotlin.jvm.PlatformType", "releaseDateFormatter", "Lj$/time/format/DateTimeFormatter;", "Lkotlin/text/m;", "genreWordStartRegex", "Lkotlin/text/m;", "tvWordRegex", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MediaBadgesKt {
    private static final DateTimeFormatter releaseDateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final m genreWordStartRegex = new m("(^|[\\s/&-])([\\p{L}])");
    private static final m tvWordRegex = new m("\\bTv\\b");

    public static final String formatGenreName(String str) {
        String string;
        String string2 = o.L0(str).toString();
        if (string2.length() == 0) {
            return string2;
        }
        String lowerCase = string2.toLowerCase(Locale.ROOT);
        m mVar = genreWordStartRegex;
        mVar.getClass();
        k kVarB = m.b(mVar, lowerCase);
        if (kVarB == null) {
            string = lowerCase.toString();
        } else {
            int length = lowerCase.length();
            StringBuilder sb2 = new StringBuilder(length);
            int i10 = 0;
            do {
                sb2.append((CharSequence) lowerCase, i10, kVarB.d().f22619i);
                sb2.append(formatGenreName$lambda$0(kVarB));
                i10 = kVarB.d().f22620l + 1;
                kVarB = kVarB.next();
                if (i10 >= length) {
                    break;
                }
            } while (kVarB != null);
            if (i10 < length) {
                sb2.append((CharSequence) lowerCase, i10, length);
            }
            string = sb2.toString();
        }
        return tvWordRegex.f("TV", string);
    }

    private static final CharSequence formatGenreName$lambda$0(i iVar) {
        Object obj = iVar.b().get(1);
        String str = (String) iVar.b().get(2);
        if (str.length() > 0) {
            str = ((Object) r0.F(str.charAt(0), Locale.ROOT)) + str.substring(1);
        }
        return obj + str;
    }

    public static final boolean isInCinema(MediaItem mediaItem, LocalDate localDate) {
        String releaseDate;
        Object c0Var;
        if (mediaItem.getMediaType() != MediaType.MOVIE || (releaseDate = mediaItem.getReleaseDate()) == null) {
            return false;
        }
        if (o.h0(releaseDate)) {
            releaseDate = null;
        }
        if (releaseDate == null) {
            return false;
        }
        try {
            c0Var = LocalDate.parse(releaseDate, releaseDateFormatter);
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        LocalDate localDate2 = (LocalDate) (c0Var instanceof c0 ? null : c0Var);
        return (localDate2 == null || localDate2.isAfter(localDate) || ChronoUnit.DAYS.between(localDate2, localDate) >= 60) ? false : true;
    }

    public static /* synthetic */ boolean isInCinema$default(MediaItem mediaItem, LocalDate localDate, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            localDate = LocalDate.now();
        }
        return isInCinema(mediaItem, localDate);
    }

    public static final float parseRatingValue(String str) {
        Float fH;
        if (o.h0(str) || (fH = t.H(o.L0(str).toString().replace(',', '.'))) == null) {
            return 0.0f;
        }
        return fH.floatValue();
    }
}
