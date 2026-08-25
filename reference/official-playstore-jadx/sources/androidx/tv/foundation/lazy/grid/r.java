package androidx.tv.foundation.lazy.grid;

import androidx.compose.runtime.State;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class r extends kotlin.jvm.internal.z {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5288i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(int i10, int i11, Class cls, Object obj, String str, String str2) {
        super(obj, cls, str, str2, i10);
        this.f5288i = i11;
    }

    @Override // kotlin.reflect.n
    public final Object get() {
        switch (this.f5288i) {
            case 0:
                return ((State) this.receiver).getValue();
            case 1:
                return ((State) this.receiver).getValue();
            default:
                return this.receiver.getClass().getSimpleName();
        }
    }
}
