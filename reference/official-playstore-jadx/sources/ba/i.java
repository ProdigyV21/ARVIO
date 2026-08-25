package ba;

import java.util.Arrays;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f9.f f7288a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.text.m f7289b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Collection f7290c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final r7.l f7291d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e[] f7292e;

    public i(f9.f fVar, kotlin.text.m mVar, Collection collection, r7.l lVar, e... eVarArr) {
        this.f7288a = fVar;
        this.f7289b = mVar;
        this.f7290c = collection;
        this.f7291d = lVar;
        this.f7292e = eVarArr;
    }

    public /* synthetic */ i(f9.f fVar, e[] eVarArr) {
        this(fVar, eVarArr, h.f7278l);
    }

    public i(f9.f fVar, e[] eVarArr, r7.l lVar) {
        this(fVar, null, null, lVar, (e[]) Arrays.copyOf(eVarArr, eVarArr.length));
    }

    public /* synthetic */ i(Collection collection, e[] eVarArr) {
        this(collection, eVarArr, h.f7280n);
    }

    public i(Collection collection, e[] eVarArr, r7.l lVar) {
        this(null, null, collection, lVar, (e[]) Arrays.copyOf(eVarArr, eVarArr.length));
    }
}
