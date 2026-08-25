package ba;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends kotlin.collections.b {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f7270m = -1;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ c f7271n;

    public b(c cVar) {
        this.f7271n = cVar;
    }

    @Override // kotlin.collections.b
    public final void a() {
        int i10;
        Object[] objArr;
        do {
            i10 = this.f7270m + 1;
            this.f7270m = i10;
            objArr = this.f7271n.f7272i;
            if (i10 >= objArr.length) {
                break;
            }
        } while (objArr[i10] == null);
        if (i10 >= objArr.length) {
            this.f19684i = 2;
        } else {
            this.f19685l = objArr[i10];
            this.f19684i = 1;
        }
    }
}
