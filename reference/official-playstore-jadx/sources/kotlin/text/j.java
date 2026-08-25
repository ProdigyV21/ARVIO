package kotlin.text;

import androidx.core.view.z0;
import java.util.Iterator;
import java.util.regex.Matcher;

/* JADX INFO: loaded from: classes5.dex */
public final class j extends kotlin.collections.a implements h {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ k f19945i;

    public j(k kVar) {
        this.f19945i = kVar;
    }

    public final f a(int i10) {
        k kVar = this.f19945i;
        Matcher matcher = kVar.f19946a;
        x7.i iVarX = qb.d.X(matcher.start(i10), matcher.end(i10));
        if (iVarX.f22619i >= 0) {
            return new f(kVar.f19946a.group(i10), iVarX);
        }
        return null;
    }

    @Override // kotlin.collections.a, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj == null ? true : obj instanceof f) {
            return super.contains((f) obj);
        }
        return false;
    }

    @Override // kotlin.text.h
    public final f get(String str) {
        return i7.n.f16237a.E(this.f19945i.f19946a, str);
    }

    @Override // kotlin.collections.a
    public final int getSize() {
        return this.f19945i.f19946a.groupCount() + 1;
    }

    @Override // kotlin.collections.a, java.util.Collection
    public final boolean isEmpty() {
        return false;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new z0(new ga.t(new ga.p(t7.a.t(this), 3), new com.arflix.tv.cast.a(this, 8)));
    }
}
