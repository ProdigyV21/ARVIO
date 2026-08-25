package j$.time;

import j$.time.temporal.TemporalAccessor;
import j$.util.Objects;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ZoneId implements Serializable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map f18121a;
    private static final long serialVersionUID = 8352817235686L;

    public abstract j$.time.zone.f V();

    public abstract void Z(DataOutput dataOutput);

    public abstract String k();

    static {
        Map.Entry[] entryArr = {j$.com.android.tools.r8.a.N("ACT", "Australia/Darwin"), j$.com.android.tools.r8.a.N("AET", "Australia/Sydney"), j$.com.android.tools.r8.a.N("AGT", "America/Argentina/Buenos_Aires"), j$.com.android.tools.r8.a.N("ART", "Africa/Cairo"), j$.com.android.tools.r8.a.N("AST", "America/Anchorage"), j$.com.android.tools.r8.a.N("BET", "America/Sao_Paulo"), j$.com.android.tools.r8.a.N("BST", "Asia/Dhaka"), j$.com.android.tools.r8.a.N("CAT", "Africa/Harare"), j$.com.android.tools.r8.a.N("CNT", "America/St_Johns"), j$.com.android.tools.r8.a.N("CST", "America/Chicago"), j$.com.android.tools.r8.a.N("CTT", "Asia/Shanghai"), j$.com.android.tools.r8.a.N("EAT", "Africa/Addis_Ababa"), j$.com.android.tools.r8.a.N("ECT", "Europe/Paris"), j$.com.android.tools.r8.a.N("IET", "America/Indiana/Indianapolis"), j$.com.android.tools.r8.a.N("IST", "Asia/Kolkata"), j$.com.android.tools.r8.a.N("JST", "Asia/Tokyo"), j$.com.android.tools.r8.a.N("MIT", "Pacific/Apia"), j$.com.android.tools.r8.a.N("NET", "Asia/Yerevan"), j$.com.android.tools.r8.a.N("NST", "Pacific/Auckland"), j$.com.android.tools.r8.a.N("PLT", "Asia/Karachi"), j$.com.android.tools.r8.a.N("PNT", "America/Phoenix"), j$.com.android.tools.r8.a.N("PRT", "America/Puerto_Rico"), j$.com.android.tools.r8.a.N("PST", "America/Los_Angeles"), j$.com.android.tools.r8.a.N("SST", "Pacific/Guadalcanal"), j$.com.android.tools.r8.a.N("VST", "Asia/Ho_Chi_Minh"), j$.com.android.tools.r8.a.N("EST", "-05:00"), j$.com.android.tools.r8.a.N("MST", "-07:00"), j$.com.android.tools.r8.a.N("HST", "-10:00")};
        HashMap map = new HashMap(28);
        for (int i10 = 0; i10 < 28; i10++) {
            Map.Entry entry = entryArr[i10];
            Object objRequireNonNull = Objects.requireNonNull(entry.getKey());
            if (map.put(objRequireNonNull, Objects.requireNonNull(entry.getValue())) != null) {
                throw new IllegalArgumentException("duplicate key: " + objRequireNonNull);
            }
        }
        f18121a = Collections.unmodifiableMap(map);
    }

    public static ZoneId systemDefault() {
        String id = TimeZone.getDefault().getID();
        Map map = f18121a;
        Objects.requireNonNull(id, "zoneId");
        Objects.requireNonNull(map, "aliasMap");
        Object objRequireNonNull = (String) map.get(id);
        if (objRequireNonNull == null) {
            objRequireNonNull = Objects.requireNonNull(id, "defaultObj");
        }
        return of((String) objRequireNonNull);
    }

    public static ZoneId of(String str) {
        return W(str, true);
    }

    public static ZoneId X(String str, ZoneOffset zoneOffset) {
        Objects.requireNonNull(str, "prefix");
        Objects.requireNonNull(zoneOffset, "offset");
        if (str.isEmpty()) {
            return zoneOffset;
        }
        if (!str.equals("GMT") && !str.equals("UTC") && !str.equals("UT")) {
            throw new IllegalArgumentException("prefix should be GMT, UTC or UT, is: ".concat(str));
        }
        if (zoneOffset.f18126b != 0) {
            str = str.concat(zoneOffset.f18127c);
        }
        return new t(str, zoneOffset.V());
    }

    public static ZoneId W(String str, boolean z) {
        Objects.requireNonNull(str, "zoneId");
        if (str.length() <= 1 || str.startsWith("+") || str.startsWith("-")) {
            return ZoneOffset.b0(str);
        }
        if (str.startsWith("UTC") || str.startsWith("GMT")) {
            return Y(str, 3, z);
        }
        if (str.startsWith("UT")) {
            return Y(str, 2, z);
        }
        return t.a0(str, z);
    }

    public static ZoneId Y(String str, int i10, boolean z) {
        String strSubstring = str.substring(0, i10);
        if (str.length() == i10) {
            return X(strSubstring, ZoneOffset.UTC);
        }
        if (str.charAt(i10) != '+' && str.charAt(i10) != '-') {
            return t.a0(str, z);
        }
        try {
            ZoneOffset zoneOffsetB0 = ZoneOffset.b0(str.substring(i10));
            if (zoneOffsetB0 == ZoneOffset.UTC) {
                return X(strSubstring, zoneOffsetB0);
            }
            return X(strSubstring, zoneOffsetB0);
        } catch (DateTimeException e5) {
            throw new DateTimeException("Invalid ID for offset-based ZoneId: ".concat(str), e5);
        }
    }

    public static ZoneId U(TemporalAccessor temporalAccessor) {
        ZoneId zoneId = (ZoneId) temporalAccessor.G(j$.time.temporal.p.f18370e);
        if (zoneId != null) {
            return zoneId;
        }
        throw new DateTimeException("Unable to obtain ZoneId from TemporalAccessor: " + temporalAccessor + " of type " + temporalAccessor.getClass().getName());
    }

    public ZoneId() {
        if (getClass() != ZoneOffset.class && getClass() != t.class) {
            throw new AssertionError("Invalid subclass");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ZoneId) {
            return k().equals(((ZoneId) obj).k());
        }
        return false;
    }

    public int hashCode() {
        return k().hashCode();
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public String toString() {
        return k();
    }

    private Object writeReplace() {
        return new o((byte) 7, this);
    }
}
