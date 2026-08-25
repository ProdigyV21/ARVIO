package io.ktor.http;

import androidx.fragment.app.a2;
import io.ktor.util.date.GMTDateParser;
import kotlin.Metadata;
import x7.l;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a-\u0010\u0007\u001a\u00020\u00062\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\b\u001a-\u0010\u0007\u001a\u00020\u00062\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\t¨\u0006\n"}, d2 = {"Lx7/l;", "range", "", "fullLength", "Lio/ktor/http/RangeUnits;", "unit", "", "contentRangeHeaderValue", "(Lx7/l;Ljava/lang/Long;Lio/ktor/http/RangeUnits;)Ljava/lang/String;", "(Lx7/l;Ljava/lang/Long;Ljava/lang/String;)Ljava/lang/String;", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ContentRangeKt {
    public static final String contentRangeHeaderValue(l lVar, Long l10, RangeUnits rangeUnits) {
        return contentRangeHeaderValue(lVar, l10, rangeUnits.getUnitToken());
    }

    public static /* synthetic */ String contentRangeHeaderValue$default(l lVar, Long l10, RangeUnits rangeUnits, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            l10 = null;
        }
        if ((i10 & 4) != 0) {
            rangeUnits = RangeUnits.Bytes;
        }
        return contentRangeHeaderValue(lVar, l10, rangeUnits);
    }

    public static final String contentRangeHeaderValue(l lVar, Long l10, String str) {
        StringBuilder sbP = a2.p(str, " ");
        if (lVar != null) {
            sbP.append(lVar.f22627i);
            sbP.append('-');
            sbP.append(lVar.f22628l);
        } else {
            sbP.append(GMTDateParser.ANY);
        }
        sbP.append('/');
        Object obj = l10;
        if (l10 == null) {
            obj = "*";
        }
        sbP.append(obj);
        return sbP.toString();
    }

    public static /* synthetic */ String contentRangeHeaderValue$default(l lVar, Long l10, String str, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            l10 = null;
        }
        if ((i10 & 4) != 0) {
            str = RangeUnits.Bytes.getUnitToken();
        }
        return contentRangeHeaderValue(lVar, l10, str);
    }
}
