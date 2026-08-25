package androidx.emoji2.text;

/* JADX INFO: loaded from: classes3.dex */
public final class w implements v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f2647a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2648b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2649c = -1;

    public w(int i10) {
        this.f2647a = i10;
    }

    @Override // androidx.emoji2.text.v
    public final boolean b(CharSequence charSequence, int i10, int i11, i0 i0Var) {
        int i12 = this.f2647a;
        if (i10 > i12 || i12 >= i11) {
            return i11 <= i12;
        }
        this.f2648b = i10;
        this.f2649c = i11;
        return false;
    }

    @Override // androidx.emoji2.text.v
    public final Object getResult() {
        return this;
    }
}
