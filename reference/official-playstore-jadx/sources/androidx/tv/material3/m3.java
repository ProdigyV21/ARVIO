package androidx.tv.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;

/* JADX INFO: loaded from: classes3.dex */
public final class m3 extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6216i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableInteractionSource f6217l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ PressInteraction.Press f6218m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m3(MutableInteractionSource mutableInteractionSource, PressInteraction.Press press, d7.d dVar) {
        super(2, dVar);
        this.f6217l = mutableInteractionSource;
        this.f6218m = press;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        return new m3(this.f6217l, this.f6218m, dVar);
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        return ((m3) create((ka.k0) obj, (d7.d) obj2)).invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.f6216i;
        if (i10 == 0) {
            k2.c.G(obj);
            PressInteraction.Release release = new PressInteraction.Release(this.f6218m);
            this.f6216i = 1;
            Object objEmit = this.f6217l.emit(release, this);
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
