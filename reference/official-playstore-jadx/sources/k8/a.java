package k8;

import g8.i1;
import g8.j1;
import g8.k1;
import z6.f;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends k1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f19522d = new a("package", false, 0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final a f19523e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a f19524f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f19525c;

    static {
        boolean z = true;
        f19523e = new a("protected_and_package", z, 1);
        f19524f = new a("protected_static", z, 2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(String str, boolean z, int i10) {
        super(str, z);
        this.f19525c = i10;
    }

    @Override // g8.k1
    public Integer a(k1 k1Var) {
        switch (this.f19525c) {
            case 0:
                if (this == k1Var) {
                    return 0;
                }
                f fVar = j1.f15518a;
                return k1Var == i1.f15511h || k1Var == i1.f15512i ? 1 : -1;
            case 1:
                if (equals(k1Var)) {
                    return 0;
                }
                if (k1Var == i1.f15508e) {
                    return null;
                }
                f fVar2 = j1.f15518a;
                return k1Var == i1.f15511h || k1Var == i1.f15512i ? 1 : -1;
            default:
                return super.a(k1Var);
        }
    }

    @Override // g8.k1
    public final String b() {
        switch (this.f19525c) {
            case 0:
                return "public/*package*/";
            case 1:
                return "protected/*protected and package*/";
            default:
                return "protected/*protected static*/";
        }
    }

    @Override // g8.k1
    public final k1 c() {
        switch (this.f19525c) {
        }
        return i1.f15513j;
    }
}
