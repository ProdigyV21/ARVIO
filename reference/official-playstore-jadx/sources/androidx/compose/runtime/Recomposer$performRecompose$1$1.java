package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import t.d0;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class Recomposer$performRecompose$1$1 extends r implements r7.a<t0> {
    final /* synthetic */ ControlledComposition $composition;
    final /* synthetic */ d0 $modifiedValues;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Recomposer$performRecompose$1$1(d0 d0Var, ControlledComposition controlledComposition) {
        super(0);
        this.$modifiedValues = d0Var;
        this.$composition = controlledComposition;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m2977invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m2977invoke() {
        d0 d0Var = this.$modifiedValues;
        ControlledComposition controlledComposition = this.$composition;
        Object[] objArr = d0Var.f21836b;
        long[] jArr = d0Var.f21835a;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i10 = 0;
        while (true) {
            long j10 = jArr[i10];
            if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i11 = 8 - ((~(i10 - length)) >>> 31);
                for (int i12 = 0; i12 < i11; i12++) {
                    if ((255 & j10) < 128) {
                        controlledComposition.recordWriteOf(objArr[(i10 << 3) + i12]);
                    }
                    j10 >>= 8;
                }
                if (i11 != 8) {
                    return;
                }
            }
            if (i10 == length) {
                return;
            } else {
                i10++;
            }
        }
    }
}
