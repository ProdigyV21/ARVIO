package j2;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes3.dex */
public final class h {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final h f19114i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ h[] f19115l;

    /* JADX INFO: Fake field, exist only in values array */
    h EF0;

    static {
        h hVar = new h("CornerExtraLarge", 0);
        h hVar2 = new h("CornerExtraLargeTop", 1);
        h hVar3 = new h("CornerExtraSmall", 2);
        h hVar4 = new h("CornerExtraSmallTop", 3);
        h hVar5 = new h("CornerFull", 4);
        f19114i = hVar5;
        f19115l = new h[]{hVar, hVar2, hVar3, hVar4, hVar5, new h("CornerLarge", 5), new h("CornerLargeEnd", 6), new h("CornerLargeTop", 7), new h("CornerMedium", 8), new h("CornerNone", 9), new h("CornerSmall", 10)};
    }

    public static h valueOf(String str) {
        return (h) Enum.valueOf(h.class, str);
    }

    public static h[] values() {
        return (h[]) f19115l.clone();
    }
}
