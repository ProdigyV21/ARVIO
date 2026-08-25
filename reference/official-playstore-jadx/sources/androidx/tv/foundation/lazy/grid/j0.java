package androidx.tv.foundation.lazy.grid;

/* JADX INFO: loaded from: classes3.dex */
public final class j0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5256i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j0(int i10) {
        super(1);
        this.f5256i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        return Integer.valueOf(((h0) obj).f5245a - this.f5256i);
    }
}
