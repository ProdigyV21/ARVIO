package q8;

import i9.n;
import i9.q;
import java.util.Collection;
import java.util.LinkedHashSet;
import s9.o;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ o f21329c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ LinkedHashSet f21330d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ boolean f21331e;

    public a(o oVar, LinkedHashSet linkedHashSet, boolean z) {
        this.f21329c = oVar;
        this.f21330d = linkedHashSet;
        this.f21331e = z;
    }

    public static /* synthetic */ void a(int i10) {
        Object[] objArr = new Object[3];
        if (i10 == 1) {
            objArr[0] = "fromSuper";
        } else if (i10 == 2) {
            objArr[0] = "fromCurrent";
        } else if (i10 == 3) {
            objArr[0] = "member";
        } else if (i10 != 4) {
            objArr[0] = "fakeOverride";
        } else {
            objArr[0] = "overridden";
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/load/java/components/DescriptorResolverUtils$1";
        if (i10 == 1 || i10 == 2) {
            objArr[2] = "conflict";
        } else if (i10 == 3 || i10 == 4) {
            objArr[2] = "setOverriddenDescriptors";
        } else {
            objArr[2] = "addFakeOverride";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    @Override // i9.q
    public final void b(g8.c cVar) {
        if (cVar == null) {
            a(0);
            throw null;
        }
        n.r(cVar, new d8.i(this, 2));
        this.f21330d.add(cVar);
    }

    @Override // i9.q
    public final void d(g8.c cVar, g8.c cVar2) {
        if (cVar == null) {
            a(1);
            throw null;
        }
        if (cVar2 != null) {
            return;
        }
        a(2);
        throw null;
    }

    @Override // i9.q
    public final void p(g8.c cVar, Collection collection) {
        if (cVar == null) {
            a(3);
            throw null;
        }
        if (!this.f21331e || cVar.getKind() == 2) {
            cVar.z0(collection);
        }
    }
}
