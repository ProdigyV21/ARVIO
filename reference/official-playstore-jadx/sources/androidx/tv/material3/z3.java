package androidx.tv.material3;

/* JADX INFO: loaded from: classes3.dex */
public final class z3 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ float f6604i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ float f6605l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public z3(float f10, float f11) {
        super(1);
        this.f6604i = f10;
        this.f6605l = f11;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        return Float.valueOf(((Boolean) obj).booleanValue() ? this.f6604i : this.f6605l);
    }
}
