package x7;

import i7.n;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a implements Iterable, s7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final char f22610i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final char f22611l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f22612m = 1;

    public a(char c10, char c11) {
        this.f22610i = c10;
        this.f22611l = (char) n.a(c10, c11, 1);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new b(this.f22610i, this.f22611l, this.f22612m);
    }
}
