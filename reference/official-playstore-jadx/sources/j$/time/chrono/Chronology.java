package j$.time.chrono;

import androidx.media3.extractor.text.ttml.TtmlNode;
import j$.time.DateTimeException;
import j$.time.Instant;
import j$.time.ZoneId;
import j$.time.temporal.TemporalAccessor;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceLoader;

/* JADX INFO: loaded from: classes2.dex */
public interface Chronology extends Comparable<Chronology> {
    ChronoLocalDate E(TemporalAccessor temporalAccessor);

    ChronoLocalDateTime K(TemporalAccessor temporalAccessor);

    ChronoLocalDate N(int i10, int i11, int i12);

    ChronoLocalDate P(Map map, j$.time.format.b0 b0Var);

    ChronoZonedDateTime Q(Instant instant, ZoneId zoneId);

    boolean T(long j10);

    boolean equals(Object obj);

    int hashCode();

    ChronoLocalDate j(long j10);

    String k();

    String o();

    ChronoZonedDateTime p(TemporalAccessor temporalAccessor);

    ChronoLocalDate q(int i10, int i11);

    String toString();

    j$.time.temporal.s u(j$.time.temporal.a aVar);

    List w();

    j x(int i10);

    /* JADX INFO: renamed from: y */
    int compareTo(Chronology chronology);

    int z(j jVar, int i10);

    /* JADX INFO: renamed from: j$.time.chrono.Chronology$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static Chronology a(TemporalAccessor temporalAccessor) {
            Objects.requireNonNull(temporalAccessor, "temporal");
            Object objRequireNonNull = (Chronology) temporalAccessor.G(j$.time.temporal.p.f18367b);
            p pVar = p.f18175d;
            if (objRequireNonNull == null) {
                objRequireNonNull = Objects.requireNonNull(pVar, "defaultObj");
            }
            return (Chronology) objRequireNonNull;
        }

        public static Chronology ofLocale(Locale locale) {
            ConcurrentHashMap concurrentHashMap = a.f18134a;
            Objects.requireNonNull(locale, "locale");
            String unicodeLocaleType = locale.getUnicodeLocaleType("ca");
            if (unicodeLocaleType == null) {
                unicodeLocaleType = locale.equals(a.f18136c) ? "japanese" : null;
            }
            if (unicodeLocaleType == null || "iso".equals(unicodeLocaleType) || "iso8601".equals(unicodeLocaleType)) {
                return p.f18175d;
            }
            do {
                Chronology chronology = (Chronology) a.f18135b.get(unicodeLocaleType);
                if (chronology != null) {
                    return chronology;
                }
            } while (a.n());
            for (Chronology chronology2 : ServiceLoader.load(Chronology.class)) {
                if (unicodeLocaleType.equals(chronology2.o())) {
                    return chronology2;
                }
            }
            throw new DateTimeException("Unknown calendar system: ".concat(unicodeLocaleType));
        }

        public static Chronology b(String str) {
            ConcurrentHashMap concurrentHashMap = a.f18134a;
            Objects.requireNonNull(str, TtmlNode.ATTR_ID);
            do {
                Chronology chronology = (Chronology) a.f18134a.get(str);
                if (chronology == null) {
                    chronology = (Chronology) a.f18135b.get(str);
                }
                if (chronology != null) {
                    return chronology;
                }
            } while (a.n());
            for (Chronology chronology2 : ServiceLoader.load(Chronology.class)) {
                if (str.equals(chronology2.k()) || str.equals(chronology2.o())) {
                    return chronology2;
                }
            }
            throw new DateTimeException("Unknown chronology: " + str);
        }
    }
}
