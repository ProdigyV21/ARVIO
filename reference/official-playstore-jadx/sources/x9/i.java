package x9;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.collections.z;
import v9.o0;

/* JADX INFO: loaded from: classes5.dex */
public final class i implements o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j f22689a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String[] f22690b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f22691c;

    public i(j jVar, String... strArr) {
        this.f22689a = jVar;
        this.f22690b = strArr;
        String str = jVar.f22704i;
        Object[] objArrCopyOf = Arrays.copyOf(strArr, strArr.length);
        this.f22691c = String.format("[Error type: %s]", Arrays.copyOf(new Object[]{String.format(str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length))}, 1));
    }

    @Override // v9.o0
    public final Collection d() {
        return z.f19728i;
    }

    @Override // v9.o0
    public final g8.h e() {
        k.f22706a.getClass();
        return k.f22708c;
    }

    @Override // v9.o0
    public final boolean f() {
        return false;
    }

    @Override // v9.o0
    public final List getParameters() {
        return z.f19728i;
    }

    @Override // v9.o0
    public final d8.k j() {
        return (d8.f) d8.f.f14698f.getValue();
    }

    public final String toString() {
        return this.f22691c;
    }
}
