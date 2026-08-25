package io.ktor.http;

import io.ktor.util.date.GMTDate;
import io.ktor.util.date.GMTDateParser;
import io.ktor.util.date.InvalidDateStringException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.text.o;
import t7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0004*\u00020\u0002\u001a\u0014\u0010\u0006\u001a\u00020\u0002*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002\u001a\n\u0010\t\u001a\u00020\u0002*\u00020\u0004\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"HTTP_DATE_FORMATS", "", "", "fromCookieToGmtDate", "Lio/ktor/util/date/GMTDate;", "fromHttpToGmtDate", "padZero", "", "length", "toHttpDate", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DateUtilsKt {
    private static final List<String> HTTP_DATE_FORMATS = a.E("***, dd MMM YYYY hh:mm:ss zzz", "****, dd-MMM-YYYY hh:mm:ss zzz", "*** MMM d hh:mm:ss YYYY", "***, dd-MMM-YYYY hh:mm:ss zzz", "***, dd-MMM-YYYY hh-mm-ss zzz", "***, dd MMM YYYY hh:mm:ss zzz", "*** dd-MMM-YYYY hh:mm:ss zzz", "*** dd MMM YYYY hh:mm:ss zzz", "*** dd-MMM-YYYY hh-mm-ss zzz", "***,dd-MMM-YYYY hh:mm:ss zzz", "*** MMM d YYYY hh:mm:ss zzz");

    public static final GMTDate fromCookieToGmtDate(String str) {
        String string = o.L0(str).toString();
        try {
            return new CookieDateParser().parse(string);
        } catch (InvalidCookieDateException unused) {
            return fromHttpToGmtDate(string);
        }
    }

    public static final GMTDate fromHttpToGmtDate(String str) {
        String string = o.L0(str).toString();
        Iterator<String> it = HTTP_DATE_FORMATS.iterator();
        while (it.hasNext()) {
            try {
                return new GMTDateParser(it.next()).parse(str);
            } catch (InvalidDateStringException unused) {
            }
        }
        throw new IllegalStateException(("Failed to parse date: " + string).toString());
    }

    private static final String padZero(int i10, int i11) {
        return o.o0(i11, String.valueOf(i10));
    }

    public static final String toHttpDate(GMTDate gMTDate) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(gMTDate.getDayOfWeek().getValue() + ", ");
        sb2.append(padZero(gMTDate.getDayOfMonth(), 2) + ' ');
        sb2.append(gMTDate.getMonth().getValue() + ' ');
        sb2.append(padZero(gMTDate.getYear(), 4));
        sb2.append(" " + padZero(gMTDate.getHours(), 2) + ':' + padZero(gMTDate.getMinutes(), 2) + ':' + padZero(gMTDate.getSeconds(), 2) + ' ');
        sb2.append("GMT");
        return sb2.toString();
    }
}
