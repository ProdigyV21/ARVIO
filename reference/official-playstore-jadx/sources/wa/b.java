package wa;

import j$.time.OffsetDateTime;
import j$.time.format.DateTimeParseException;
import kotlin.text.o;
import kotlinx.datetime.DateTimeFormatException;
import ya.h;

/* JADX INFO: loaded from: classes5.dex */
public final class b {
    public static c a(String str) {
        try {
            int iE0 = o.e0(str, 'T', 0, true, 2);
            if (iE0 != -1) {
                int length = str.length() - 1;
                if (length >= 0) {
                    while (true) {
                        int i10 = length - 1;
                        char cCharAt = str.charAt(length);
                        if (cCharAt == '+' || cCharAt == '-') {
                            break;
                        }
                        if (i10 < 0) {
                            break;
                        }
                        length = i10;
                    }
                    length = -1;
                    if (length >= iE0 && o.e0(str, ':', length, false, 4) == -1) {
                        str = str + ":00";
                    }
                } else {
                    length = -1;
                    if (length >= iE0) {
                        str = str + ":00";
                    }
                }
            }
            return new c(OffsetDateTime.parse(str).toInstant());
        } catch (DateTimeParseException e5) {
            throw new DateTimeFormatException(e5);
        }
    }

    public final h<c> serializer() {
        return xa.a.f22712a;
    }
}
