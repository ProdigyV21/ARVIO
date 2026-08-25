package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class i0 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f6089i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f6090l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i0(int i10, int i11) {
        super(0);
        this.f6089i = i11;
        this.f6090l = i10;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f6089i) {
            case 0:
                return Float.valueOf(this.f6090l - 1);
            default:
                return new l0(this.f6090l);
        }
    }
}
