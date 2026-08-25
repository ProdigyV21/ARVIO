package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;

/* JADX INFO: loaded from: classes3.dex */
public final class o3 extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6259i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f6260l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ PressInteraction.Press f6261m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o3(MutableInteractionSource mutableInteractionSource, PressInteraction.Press press, d7.d dVar) {
        super(2, dVar);
        this.f6260l = mutableInteractionSource;
        this.f6261m = press;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        return new o3(this.f6260l, this.f6261m, dVar);
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((o3) create((ka.k0) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.f6259i;
        if (i10 == 0) {
            k2.c.G(obj);
            this.f6259i = 1;
            Object objEmit = this.f6260l.emit(this.f6261m, this);
            e7.a aVar = e7.a.f15033i;
            if (objEmit == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return x6.t0.f22605a;
    }
}
