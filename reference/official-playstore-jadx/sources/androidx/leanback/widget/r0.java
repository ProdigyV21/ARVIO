package androidx.leanback.widget;

import android.util.Property;

/* JADX INFO: loaded from: classes3.dex */
public final class r0 extends Property {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f3110a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r0(Class cls, String str, int i10) {
        super(cls, str);
        this.f3110a = i10;
    }

    @Override // android.util.Property
    public final Object get(Object obj) {
        switch (this.f3110a) {
            case 0:
                return Float.valueOf(((s0) obj).f3115a);
            case 1:
                return Float.valueOf(((s0) obj).f3119e);
            case 2:
                return Float.valueOf(((s0) obj).f3117c);
            default:
                return Integer.valueOf(((m1) obj).getStreamPosition());
        }
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        switch (this.f3110a) {
            case 0:
                s0 s0Var = (s0) obj;
                s0Var.f3115a = ((Float) obj2).floatValue();
                s0Var.a();
                s0Var.f3124j.invalidate();
                break;
            case 1:
                s0 s0Var2 = (s0) obj;
                float fFloatValue = ((Float) obj2).floatValue();
                s0Var2.f3119e = fFloatValue;
                float f10 = fFloatValue / 2.0f;
                s0Var2.f3120f = f10;
                PagingIndicator pagingIndicator = s0Var2.f3124j;
                s0Var2.f3121g = f10 * pagingIndicator.F;
                pagingIndicator.invalidate();
                break;
            case 2:
                s0 s0Var3 = (s0) obj;
                s0Var3.f3117c = ((Float) obj2).floatValue() * s0Var3.f3122h * s0Var3.f3123i;
                s0Var3.f3124j.invalidate();
                break;
            default:
                ((m1) obj).setStreamPosition(((Integer) obj2).intValue());
                break;
        }
    }
}
