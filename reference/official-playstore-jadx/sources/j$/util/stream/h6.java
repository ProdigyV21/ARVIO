package j$.util.stream;

import j$.util.Collection;
import j$.util.List;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class h6 extends z5 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList f18712d;

    @Override // j$.util.stream.h5, j$.util.stream.l5
    public final void c(long j10) {
        if (j10 >= 2147483639) {
            throw new IllegalArgumentException("Stream size exceeds max array size");
        }
        this.f18712d = j10 >= 0 ? new ArrayList((int) j10) : new ArrayList();
    }

    @Override // j$.util.stream.h5, j$.util.stream.l5
    public final void end() {
        List list = this.f18712d;
        Comparator comparator = this.f18993b;
        if (list instanceof j$.util.List) {
            ((j$.util.List) list).sort(comparator);
        } else {
            List.CC.$default$sort(list, comparator);
        }
        long size = this.f18712d.size();
        l5 l5Var = this.f18711a;
        l5Var.c(size);
        if (this.f18994c) {
            ArrayList arrayList = this.f18712d;
            int size2 = arrayList.size();
            int i10 = 0;
            while (i10 < size2) {
                Object obj = arrayList.get(i10);
                i10++;
                if (l5Var.f()) {
                    break;
                } else {
                    l5Var.n(obj);
                }
            }
        } else {
            ArrayList arrayList2 = this.f18712d;
            Objects.requireNonNull(l5Var);
            Collection.EL.a(arrayList2, new j$.util.function.i(l5Var, 6));
        }
        l5Var.end();
        this.f18712d = null;
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        this.f18712d.add(obj);
    }
}
