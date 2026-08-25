package com.arflix.tv.data.repository;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u0005J\u0015\u0010\n\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u000bJ\u0010\u0010\n\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/arflix/tv/data/repository/IptvIdSentinels;", "", "<init>", "()V", "IMDB_NONE", "", "TMDB_NONE", "", "normalizeImdb", TtmlNode.ATTR_ID, "normalizeTmdb", "(Ljava/lang/Integer;)I", "isReal", "", "imdb", "tmdb", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class IptvIdSentinels {
    public static final String IMDB_NONE = "tt0";
    public static final IptvIdSentinels INSTANCE = new IptvIdSentinels();
    public static final int TMDB_NONE = 0;

    private IptvIdSentinels() {
    }

    public final boolean isReal(int tmdb) {
        return tmdb > 0;
    }

    public final String normalizeImdb(String id) {
        String string;
        String lowerCase = (id == null || (string = kotlin.text.o.L0(id).toString()) == null) ? null : string.toLowerCase(Locale.US);
        if (lowerCase == null) {
            lowerCase = "";
        }
        return (!kotlin.text.o.h0(lowerCase) && kotlin.text.u.P(lowerCase, TtmlNode.TAG_TT, false) && lowerCase.length() >= 3) ? lowerCase : IMDB_NONE;
    }

    public final int normalizeTmdb(Integer id) {
        if (id == null) {
            return 0;
        }
        if (id.intValue() <= 0) {
            id = null;
        }
        if (id != null) {
            return id.intValue();
        }
        return 0;
    }

    public final boolean isReal(String imdb) {
        return (kotlin.text.o.h0(imdb) || imdb.equals(IMDB_NONE)) ? false : true;
    }

    public final int normalizeTmdb(String id) {
        Integer numR;
        String string = id != null ? kotlin.text.o.L0(id).toString() : null;
        if (string == null) {
            string = "";
        }
        if (!kotlin.text.o.h0(string) && (numR = kotlin.text.u.R(string)) != null) {
            Integer num = numR.intValue() > 0 ? numR : null;
            if (num != null) {
                return num.intValue();
            }
        }
        return 0;
    }
}
