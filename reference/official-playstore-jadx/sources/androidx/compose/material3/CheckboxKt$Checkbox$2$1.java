package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "()V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class CheckboxKt$Checkbox$2$1 extends r implements r7.a<t0> {
    final /* synthetic */ boolean $checked;
    final /* synthetic */ l<Boolean, t0> $onCheckedChange;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public CheckboxKt$Checkbox$2$1(l<? super Boolean, t0> lVar, boolean z) {
        super(0);
        this.$onCheckedChange = lVar;
        this.$checked = z;
    }

    @Override // r7.a
    public /* bridge */ /* synthetic */ Object invoke() {
        m1319invoke();
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
    public final void m1319invoke() {
        this.$onCheckedChange.invoke(Boolean.valueOf(!this.$checked));
    }
}
