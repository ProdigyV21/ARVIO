package com.arflix.tv.data.repository;

import com.arflix.tv.util.Constants;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000¨\u0006\u0005"}, d2 = {"normalizeWatchlistArtworkUrl", "", "rawValue", "isBackdrop", "", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class WatchlistRepositoryKt {
    public static final String normalizeWatchlistArtworkUrl(String str, boolean z) {
        String string;
        if (str != null && (string = kotlin.text.o.L0(str).toString()) != null) {
            if (string.length() <= 0) {
                string = null;
            }
            if (string != null) {
                if (kotlin.text.u.P(string, "http://", true) || kotlin.text.u.P(string, "https://", true) || kotlin.text.u.P(string, "content://", true) || kotlin.text.u.P(string, "file://", true) || kotlin.text.u.P(string, "data:", true)) {
                    return string;
                }
                if (kotlin.text.u.P(string, "//", false)) {
                    return "https:".concat(string);
                }
                if (kotlin.text.o.A0(string, '/')) {
                    return (z ? "https://image.tmdb.org/t/p/original" : Constants.IMAGE_BASE).concat(string);
                }
                return string;
            }
        }
        return null;
    }
}
