package kotlin.reflect;

import org.jsoup.nodes.DocumentType;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes5.dex */
public final class u {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final u f19913i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final u f19914l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final u f19915m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final u f19916n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final /* synthetic */ u[] f19917o;

    static {
        u uVar = new u(DocumentType.PUBLIC_KEY, 0);
        f19913i = uVar;
        u uVar2 = new u("PROTECTED", 1);
        f19914l = uVar2;
        u uVar3 = new u("INTERNAL", 2);
        f19915m = uVar3;
        u uVar4 = new u("PRIVATE", 3);
        f19916n = uVar4;
        f19917o = new u[]{uVar, uVar2, uVar3, uVar4};
    }

    public static u valueOf(String str) {
        return (u) Enum.valueOf(u.class, str);
    }

    public static u[] values() {
        return (u[]) f19917o.clone();
    }
}
