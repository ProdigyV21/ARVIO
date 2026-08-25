package p4;

import com.google.common.util.concurrent.r0;

/* JADX INFO: loaded from: classes4.dex */
public final class c extends e {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final transient e f21045m;

    public c(e eVar) {
        this.f21045m = eVar;
    }

    @Override // p4.e, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return this.f21045m.contains(obj);
    }

    @Override // p4.e
    public final e e() {
        return this.f21045m;
    }

    @Override // p4.e, java.util.List
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public final e subList(int i10, int i11) {
        e eVar = this.f21045m;
        r0.H(i10, i11, eVar.size());
        return eVar.subList(eVar.size() - i11, eVar.size() - i10).e();
    }

    @Override // java.util.List
    public final Object get(int i10) {
        e eVar = this.f21045m;
        r0.G(i10, eVar.size());
        return eVar.get((eVar.size() - 1) - i10);
    }

    @Override // p4.e, java.util.List
    public final int indexOf(Object obj) {
        int iLastIndexOf = this.f21045m.lastIndexOf(obj);
        if (iLastIndexOf >= 0) {
            return (r0.size() - 1) - iLastIndexOf;
        }
        return -1;
    }

    @Override // p4.e, java.util.List
    public final int lastIndexOf(Object obj) {
        int iIndexOf = this.f21045m.indexOf(obj);
        if (iIndexOf >= 0) {
            return (r0.size() - 1) - iIndexOf;
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f21045m.size();
    }
}
