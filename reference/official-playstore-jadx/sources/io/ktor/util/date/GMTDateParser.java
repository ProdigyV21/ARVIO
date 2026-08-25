package io.ktor.util.date;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J#\u0010\u000b\u001a\u00020\n*\u00020\u00062\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u0002¢\u0006\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0011¨\u0006\u0013"}, d2 = {"Lio/ktor/util/date/GMTDateParser;", "", "", "pattern", "<init>", "(Ljava/lang/String;)V", "Lio/ktor/util/date/GMTDateBuilder;", "", LinkHeader.Parameters.Type, "chunk", "Lx6/t0;", "handleToken", "(Lio/ktor/util/date/GMTDateBuilder;CLjava/lang/String;)V", "dateString", "Lio/ktor/util/date/GMTDate;", "parse", "(Ljava/lang/String;)Lio/ktor/util/date/GMTDate;", "Ljava/lang/String;", "Companion", "ktor-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class GMTDateParser {
    public static final char ANY = '*';
    public static final char DAY_OF_MONTH = 'd';
    public static final char HOURS = 'h';
    public static final char MINUTES = 'm';
    public static final char MONTH = 'M';
    public static final char SECONDS = 's';
    public static final char YEAR = 'Y';
    public static final char ZONE = 'z';
    private final String pattern;

    public GMTDateParser(String str) {
        this.pattern = str;
        if (str.length() <= 0) {
            throw new IllegalStateException("Date parser pattern shouldn't be empty.");
        }
    }

    private final void handleToken(GMTDateBuilder gMTDateBuilder, char c10, String str) {
        if (c10 == 's') {
            gMTDateBuilder.setSeconds(Integer.valueOf(Integer.parseInt(str)));
            return;
        }
        if (c10 == 'm') {
            gMTDateBuilder.setMinutes(Integer.valueOf(Integer.parseInt(str)));
            return;
        }
        if (c10 == 'h') {
            gMTDateBuilder.setHours(Integer.valueOf(Integer.parseInt(str)));
            return;
        }
        if (c10 == 'd') {
            gMTDateBuilder.setDayOfMonth(Integer.valueOf(Integer.parseInt(str)));
            return;
        }
        if (c10 == 'M') {
            gMTDateBuilder.setMonth(Month.INSTANCE.from(str));
            return;
        }
        if (c10 == 'Y') {
            gMTDateBuilder.setYear(Integer.valueOf(Integer.parseInt(str)));
            return;
        }
        if (c10 == 'z') {
            if (!p.a(str, "GMT")) {
                throw new IllegalStateException("Check failed.");
            }
        } else if (c10 != '*') {
            for (int i10 = 0; i10 < str.length(); i10++) {
                if (str.charAt(i10) != c10) {
                    throw new IllegalStateException("Check failed.");
                }
            }
        }
    }

    public final GMTDate parse(String dateString) {
        GMTDateBuilder gMTDateBuilder = new GMTDateBuilder();
        char cCharAt = this.pattern.charAt(0);
        int i10 = 0;
        int i11 = 1;
        int i12 = 0;
        while (i11 < this.pattern.length()) {
            try {
                if (this.pattern.charAt(i11) == cCharAt) {
                    i11++;
                } else {
                    int i13 = (i12 + i11) - i10;
                    handleToken(gMTDateBuilder, cCharAt, dateString.substring(i12, i13));
                    try {
                        cCharAt = this.pattern.charAt(i11);
                        i10 = i11;
                        i11++;
                        i12 = i13;
                    } catch (Throwable unused) {
                        i12 = i13;
                        throw new InvalidDateStringException(dateString, i12, this.pattern);
                    }
                }
            } catch (Throwable unused2) {
            }
        }
        if (i12 < dateString.length()) {
            handleToken(gMTDateBuilder, cCharAt, dateString.substring(i12));
        }
        return gMTDateBuilder.build();
    }
}
