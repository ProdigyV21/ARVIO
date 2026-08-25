package j8;

import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes5.dex */
public final class r extends i9.q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ LinkedHashSet f19335c;

    public r(LinkedHashSet linkedHashSet) {
        this.f19335c = linkedHashSet;
    }

    public static /* synthetic */ void a(int i10) {
        Object[] objArr = new Object[3];
        if (i10 == 1) {
            objArr[0] = "fromSuper";
        } else if (i10 != 2) {
            objArr[0] = "fakeOverride";
        } else {
            objArr[0] = "fromCurrent";
        }
        objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/EnumEntrySyntheticClassDescriptor$EnumEntryScope$4";
        if (i10 == 1 || i10 == 2) {
            objArr[2] = "conflict";
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
        i9.n.r(cVar, null);
        this.f19335c.add(cVar);
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
}
