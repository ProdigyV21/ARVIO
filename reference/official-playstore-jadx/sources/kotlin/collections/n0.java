package kotlin.collections;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes5.dex */
public final class n0 extends f {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19699i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f19700l;

    public /* synthetic */ n0(Object obj, int i10) {
        this.f19699i = i10;
        this.f19700l = obj;
    }

    @Override // kotlin.collections.a, java.util.Collection, java.util.List
    public /* bridge */ boolean contains(Object obj) {
        switch (this.f19699i) {
            case 1:
                if (obj instanceof String) {
                    return super.contains((String) obj);
                }
                return false;
            default:
                return super.contains(obj);
        }
    }

    @Override // kotlin.collections.f, java.util.List
    public final Object get(int i10) {
        switch (this.f19699i) {
            case 0:
                return ((List) this.f19700l).get(x.Y(i10, this));
            default:
                String strGroup = ((kotlin.text.k) this.f19700l).f19946a.group(i10);
                return strGroup == null ? "" : strGroup;
        }
    }

    @Override // kotlin.collections.f, kotlin.collections.a
    public final int getSize() {
        switch (this.f19699i) {
            case 0:
                return ((List) this.f19700l).size();
            default:
                return ((kotlin.text.k) this.f19700l).f19946a.groupCount() + 1;
        }
    }

    @Override // kotlin.collections.f, java.util.List
    public /* bridge */ int indexOf(Object obj) {
        switch (this.f19699i) {
            case 1:
                if (obj instanceof String) {
                    return super.indexOf((String) obj);
                }
                return -1;
            default:
                return super.indexOf(obj);
        }
    }

    @Override // kotlin.collections.f, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        switch (this.f19699i) {
            case 0:
                return new m0(this, 0);
            default:
                return super.iterator();
        }
    }

    @Override // kotlin.collections.f, java.util.List
    public /* bridge */ int lastIndexOf(Object obj) {
        switch (this.f19699i) {
            case 1:
                if (obj instanceof String) {
                    return super.lastIndexOf((String) obj);
                }
                return -1;
            default:
                return super.lastIndexOf(obj);
        }
    }

    @Override // kotlin.collections.f, java.util.List
    public ListIterator listIterator() {
        switch (this.f19699i) {
            case 0:
                return new m0(this, 0);
            default:
                return super.listIterator();
        }
    }

    @Override // kotlin.collections.f, java.util.List
    public ListIterator listIterator(int i10) {
        switch (this.f19699i) {
            case 0:
                return new m0(this, i10);
            default:
                return super.listIterator(i10);
        }
    }
}
