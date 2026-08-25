package cb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f7473b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final n0 f7474c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(ya.h hVar, int i10) {
        super(hVar);
        this.f7473b = i10;
        switch (i10) {
            case 1:
                super(hVar);
                this.f7474c = new c(hVar.getDescriptor(), 2);
                break;
            case 2:
                super(hVar);
                this.f7474c = new c(hVar.getDescriptor(), 3);
                break;
            default:
                this.f7474c = new c(hVar.getDescriptor(), 1);
                break;
        }
    }

    @Override // cb.a
    public final Object a() {
        switch (this.f7473b) {
            case 0:
                return new ArrayList();
            case 1:
                return new HashSet();
            default:
                return new LinkedHashSet();
        }
    }

    @Override // cb.a
    public final int b(Object obj) {
        switch (this.f7473b) {
            case 0:
                return ((ArrayList) obj).size();
            case 1:
                return ((HashSet) obj).size();
            default:
                return ((LinkedHashSet) obj).size();
        }
    }

    @Override // cb.a
    public final Iterator c(Object obj) {
        return ((Collection) obj).iterator();
    }

    @Override // cb.a
    public final int d(Object obj) {
        return ((Collection) obj).size();
    }

    @Override // cb.a
    public final Object g(Object obj) {
        switch (this.f7473b) {
            case 0:
                return new ArrayList((Collection) null);
            case 1:
                return new HashSet((Collection) null);
            default:
                return new LinkedHashSet((Collection) null);
        }
    }

    @Override // ya.s, ya.c
    public final ab.g getDescriptor() {
        switch (this.f7473b) {
        }
        return (c) this.f7474c;
    }

    @Override // cb.a
    public final Object h(Object obj) {
        switch (this.f7473b) {
            case 0:
                return (ArrayList) obj;
            case 1:
                return (HashSet) obj;
            default:
                return (LinkedHashSet) obj;
        }
    }

    @Override // cb.t
    public final void i(int i10, Object obj, Object obj2) {
        switch (this.f7473b) {
            case 0:
                ((ArrayList) obj).add(i10, obj2);
                break;
            case 1:
                ((HashSet) obj).add(obj2);
                break;
            default:
                ((LinkedHashSet) obj).add(obj2);
                break;
        }
    }
}
