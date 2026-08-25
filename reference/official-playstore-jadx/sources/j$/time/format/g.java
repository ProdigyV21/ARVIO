package j$.time.format;

import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.LocalTime;
import j$.time.ZoneOffset;
import j$.time.temporal.TemporalAccessor;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class g implements e {
    @Override // j$.time.format.e
    public final boolean l(w wVar, StringBuilder sb2) {
        Long lA = wVar.a(j$.time.temporal.a.INSTANT_SECONDS);
        TemporalAccessor temporalAccessor = wVar.f18299a;
        j$.time.temporal.a aVar = j$.time.temporal.a.NANO_OF_SECOND;
        Long lValueOf = temporalAccessor.e(aVar) ? Long.valueOf(temporalAccessor.H(aVar)) : null;
        int i10 = 0;
        if (lA == null) {
            return false;
        }
        long jLongValue = lA.longValue();
        int iA = aVar.f18349b.a(lValueOf != null ? lValueOf.longValue() : 0L, aVar);
        if (jLongValue >= -62167219200L) {
            long j10 = jLongValue - 253402300800L;
            long jQ = j$.com.android.tools.r8.a.Q(j10, 315569520000L) + 1;
            LocalDateTime localDateTimeY = LocalDateTime.Y(j$.com.android.tools.r8.a.P(j10, 315569520000L) - 62167219200L, 0, ZoneOffset.UTC);
            if (jQ > 0) {
                sb2.append('+');
                sb2.append(jQ);
            }
            sb2.append(localDateTimeY);
            if (localDateTimeY.f18108b.f18114c == 0) {
                sb2.append(":00");
            }
        } else {
            long j11 = jLongValue + 62167219200L;
            long j12 = j11 / 315569520000L;
            long j13 = j11 % 315569520000L;
            LocalDateTime localDateTimeY2 = LocalDateTime.Y(j13 - 62167219200L, 0, ZoneOffset.UTC);
            int length = sb2.length();
            sb2.append(localDateTimeY2);
            if (localDateTimeY2.f18108b.f18114c == 0) {
                sb2.append(":00");
            }
            if (j12 < 0) {
                if (localDateTimeY2.f18107a.getYear() == -10000) {
                    sb2.replace(length, length + 2, Long.toString(j12 - 1));
                } else if (j13 == 0) {
                    sb2.insert(length, j12);
                } else {
                    sb2.insert(length + 1, Math.abs(j12));
                }
            }
        }
        if (iA > 0) {
            sb2.append('.');
            int i11 = 100000000;
            while (true) {
                if (iA <= 0 && i10 % 3 == 0 && i10 >= -2) {
                    break;
                }
                int i12 = iA / i11;
                sb2.append((char) (i12 + 48));
                iA -= i12 * i11;
                i11 /= 10;
                i10++;
            }
        }
        sb2.append('Z');
        return true;
    }

    @Override // j$.time.format.e
    public final int m(u uVar, CharSequence charSequence, int i10) {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder.a(DateTimeFormatter.ISO_LOCAL_DATE);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral = dateTimeFormatterBuilder.appendLiteral('T');
        j$.time.temporal.a aVar = j$.time.temporal.a.HOUR_OF_DAY;
        dateTimeFormatterBuilderAppendLiteral.k(aVar, 2);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral2 = dateTimeFormatterBuilderAppendLiteral.appendLiteral(':');
        j$.time.temporal.a aVar2 = j$.time.temporal.a.MINUTE_OF_HOUR;
        dateTimeFormatterBuilderAppendLiteral2.k(aVar2, 2);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral3 = dateTimeFormatterBuilderAppendLiteral2.appendLiteral(':');
        j$.time.temporal.a aVar3 = j$.time.temporal.a.SECOND_OF_MINUTE;
        dateTimeFormatterBuilderAppendLiteral3.k(aVar3, 2);
        j$.time.temporal.a aVar4 = j$.time.temporal.a.NANO_OF_SECOND;
        int i11 = 1;
        dateTimeFormatterBuilderAppendLiteral3.b(aVar4, 0, 9, true);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral4 = dateTimeFormatterBuilderAppendLiteral3.appendLiteral('Z');
        dateTimeFormatterBuilderAppendLiteral4.getClass();
        d dVar = dateTimeFormatterBuilderAppendLiteral4.toFormatter(Locale.getDefault()).f18199a;
        if (dVar.f18236b) {
            dVar = new d(dVar.f18235a, false);
        }
        u uVar2 = new u(uVar.f18290a);
        uVar2.f18291b = uVar.f18291b;
        uVar2.f18292c = uVar.f18292c;
        int iM = dVar.m(uVar2, charSequence, i10);
        if (iM < 0) {
            return iM;
        }
        long jLongValue = uVar2.d(j$.time.temporal.a.YEAR).longValue();
        int iIntValue = uVar2.d(j$.time.temporal.a.MONTH_OF_YEAR).intValue();
        int iIntValue2 = uVar2.d(j$.time.temporal.a.DAY_OF_MONTH).intValue();
        int iIntValue3 = uVar2.d(aVar).intValue();
        int iIntValue4 = uVar2.d(aVar2).intValue();
        Long lD = uVar2.d(aVar3);
        Long lD2 = uVar2.d(aVar4);
        int iIntValue5 = lD != null ? lD.intValue() : 0;
        int iIntValue6 = lD2 != null ? lD2.intValue() : 0;
        if (iIntValue3 == 24 && iIntValue4 == 0 && iIntValue5 == 0 && iIntValue6 == 0) {
            iIntValue3 = 0;
        } else if (iIntValue3 == 23 && iIntValue4 == 59 && iIntValue5 == 60) {
            uVar.c().f18226d = true;
            i11 = 0;
            iIntValue5 = 59;
        } else {
            i11 = 0;
        }
        int i12 = ((int) jLongValue) % 10000;
        try {
            LocalDateTime localDateTime = LocalDateTime.f18105c;
            LocalDate localDateOf = LocalDate.of(i12, iIntValue, iIntValue2);
            LocalTime localTimeY = LocalTime.Y(iIntValue3, iIntValue4, iIntValue5, 0);
            return uVar.f(aVar4, iIntValue6, i10, uVar.f(j$.time.temporal.a.INSTANT_SECONDS, j$.com.android.tools.r8.a.w(new LocalDateTime(localDateOf, localTimeY).d0(localDateOf.plusDays(i11), localTimeY), ZoneOffset.UTC) + j$.com.android.tools.r8.a.R(jLongValue / 10000, 315569520000L), i10, iM));
        } catch (RuntimeException unused) {
            return ~i10;
        }
    }

    public final String toString() {
        return "Instant()";
    }
}
