package e8;

/* JADX INFO: loaded from: classes5.dex */
public abstract class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f9.c f15059a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f15060b;

    public j(f9.c cVar, String str) {
        this.f15059a = cVar;
        this.f15060b = str;
    }

    public final f9.f a(int i10) {
        return f9.f.j(this.f15060b + i10);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f15059a);
        sb2.append('.');
        return androidx.compose.foundation.c.u(sb2, this.f15060b, 'N');
    }
}
