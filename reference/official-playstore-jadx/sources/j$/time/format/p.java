package j$.time.format;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes2.dex */
public final class p implements e {
    public static final p INSENSITIVE;
    public static final p LENIENT;
    public static final p SENSITIVE;
    public static final p STRICT;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ p[] f18274a;

    @Override // j$.time.format.e
    public final boolean l(w wVar, StringBuilder sb2) {
        return true;
    }

    public static p valueOf(String str) {
        return (p) Enum.valueOf(p.class, str);
    }

    public static p[] values() {
        return (p[]) f18274a.clone();
    }

    static {
        p pVar = new p("SENSITIVE", 0);
        SENSITIVE = pVar;
        p pVar2 = new p("INSENSITIVE", 1);
        INSENSITIVE = pVar2;
        p pVar3 = new p("STRICT", 2);
        STRICT = pVar3;
        p pVar4 = new p("LENIENT", 3);
        LENIENT = pVar4;
        f18274a = new p[]{pVar, pVar2, pVar3, pVar4};
    }

    @Override // j$.time.format.e
    public final int m(u uVar, CharSequence charSequence, int i10) {
        int iOrdinal = ordinal();
        if (iOrdinal == 0) {
            uVar.f18291b = true;
            return i10;
        }
        if (iOrdinal == 1) {
            uVar.f18291b = false;
            return i10;
        }
        if (iOrdinal == 2) {
            uVar.f18292c = true;
            return i10;
        }
        if (iOrdinal != 3) {
            return i10;
        }
        uVar.f18292c = false;
        return i10;
    }

    @Override // java.lang.Enum
    public final String toString() {
        int iOrdinal = ordinal();
        if (iOrdinal == 0) {
            return "ParseCaseSensitive(true)";
        }
        if (iOrdinal == 1) {
            return "ParseCaseSensitive(false)";
        }
        if (iOrdinal == 2) {
            return "ParseStrict(true)";
        }
        if (iOrdinal == 3) {
            return "ParseStrict(false)";
        }
        throw new IllegalStateException("Unreachable");
    }
}
