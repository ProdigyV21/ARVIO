package b8;

import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes5.dex */
public final class n extends o {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f7237g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(Field field, boolean z, boolean z5, int i10) {
        super(field, z, z5);
        this.f7237g = i10;
    }

    @Override // b8.o, b8.u
    public void c(Object[] objArr) {
        switch (this.f7237g) {
            case 1:
                super.c(objArr);
                d(kotlin.collections.r.Z(objArr));
                break;
            default:
                super.c(objArr);
                break;
        }
    }
}
