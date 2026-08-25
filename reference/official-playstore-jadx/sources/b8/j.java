package b8;

import java.lang.reflect.Field;
import kotlin.reflect.b0;

/* JADX INFO: loaded from: classes5.dex */
public final class j extends k {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f7235e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j(Field field, boolean z, int i10) {
        super(field, z);
        this.f7235e = i10;
    }

    @Override // b8.u
    public void c(Object[] objArr) {
        switch (this.f7235e) {
            case 1:
                b0.f(this, objArr);
                d(kotlin.collections.r.Z(objArr));
                break;
            default:
                super.c(objArr);
                break;
        }
    }
}
