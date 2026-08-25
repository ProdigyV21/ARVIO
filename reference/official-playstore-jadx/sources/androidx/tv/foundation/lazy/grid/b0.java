package androidx.tv.foundation.lazy.grid;

/* JADX INFO: loaded from: classes3.dex */
public final class b0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5151i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f5152l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b0(int i10, int i11) {
        super(1);
        this.f5151i = i10;
        this.f5152l = i11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        int i10 = ((c0) obj).f5175a;
        boolean z = false;
        if (this.f5151i <= i10 && i10 <= this.f5152l) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
