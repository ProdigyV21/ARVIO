package com.caverock.androidsvg;

import java.util.HashMap;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes4.dex */
public final class h {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final h f12325i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final h f12326l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final h f12327m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final h f12328n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final HashMap f12329o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ h[] f12330p;

    /* JADX INFO: Fake field, exist only in values array */
    h EF0;

    static {
        h hVar = new h("target", 0);
        h hVar2 = new h("root", 1);
        h hVar3 = new h("nth_child", 2);
        f12325i = hVar3;
        h hVar4 = new h("nth_last_child", 3);
        h hVar5 = new h("nth_of_type", 4);
        f12326l = hVar5;
        h hVar6 = new h("nth_last_of_type", 5);
        f12327m = hVar6;
        h hVar7 = new h("first_child", 6);
        h hVar8 = new h("last_child", 7);
        h hVar9 = new h("first_of_type", 8);
        h hVar10 = new h("last_of_type", 9);
        h hVar11 = new h("only_child", 10);
        h hVar12 = new h("only_of_type", 11);
        h hVar13 = new h("empty", 12);
        h hVar14 = new h("not", 13);
        h hVar15 = new h("lang", 14);
        h hVar16 = new h("link", 15);
        h hVar17 = new h("visited", 16);
        h hVar18 = new h("hover", 17);
        h hVar19 = new h("active", 18);
        h hVar20 = new h("focus", 19);
        h hVar21 = new h("enabled", 20);
        h hVar22 = new h("disabled", 21);
        h hVar23 = new h("checked", 22);
        h hVar24 = new h("indeterminate", 23);
        h hVar25 = new h("UNSUPPORTED", 24);
        f12328n = hVar25;
        f12330p = new h[]{hVar, hVar2, hVar3, hVar4, hVar5, hVar6, hVar7, hVar8, hVar9, hVar10, hVar11, hVar12, hVar13, hVar14, hVar15, hVar16, hVar17, hVar18, hVar19, hVar20, hVar21, hVar22, hVar23, hVar24, hVar25};
        f12329o = new HashMap();
        for (h hVar26 : values()) {
            if (hVar26 != f12328n) {
                f12329o.put(hVar26.name().replace('_', '-'), hVar26);
            }
        }
    }

    public static h valueOf(String str) {
        return (h) Enum.valueOf(h.class, str);
    }

    public static h[] values() {
        return (h[]) f12330p.clone();
    }
}
