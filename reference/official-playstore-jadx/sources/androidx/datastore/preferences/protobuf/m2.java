package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes3.dex */
public final class m2 implements t1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v1 f2474a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2475b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object[] f2476c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2477d;

    public m2(r0 r0Var, String str, Object[] objArr) {
        this.f2474a = r0Var;
        this.f2475b = str;
        this.f2476c = objArr;
        char cCharAt = str.charAt(0);
        if (cCharAt < 55296) {
            this.f2477d = cCharAt;
            return;
        }
        int i10 = cCharAt & 8191;
        int i11 = 13;
        int i12 = 1;
        while (true) {
            int i13 = i12 + 1;
            char cCharAt2 = str.charAt(i12);
            if (cCharAt2 < 55296) {
                this.f2477d = i10 | (cCharAt2 << i11);
                return;
            } else {
                i10 |= (cCharAt2 & 8191) << i11;
                i11 += 13;
                i12 = i13;
            }
        }
    }

    @Override // androidx.datastore.preferences.protobuf.t1
    public final boolean a() {
        return (this.f2477d & 2) == 2;
    }

    @Override // androidx.datastore.preferences.protobuf.t1
    public final v1 b() {
        return this.f2474a;
    }

    @Override // androidx.datastore.preferences.protobuf.t1
    public final int c() {
        return (this.f2477d & 1) == 1 ? 1 : 2;
    }
}
