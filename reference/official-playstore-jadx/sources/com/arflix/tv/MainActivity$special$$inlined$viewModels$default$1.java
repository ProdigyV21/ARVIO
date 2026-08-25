package com.arflix.tv;

import androidx.activity.t;
import androidx.lifecycle.g1;
import kotlin.Metadata;
import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/lifecycle/d1;", "VM", "Landroidx/lifecycle/g1;", "invoke", "()Landroidx/lifecycle/g1;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
public final class MainActivity$special$$inlined$viewModels$default$1 extends r implements r7.a<g1> {
    final /* synthetic */ t $this_viewModels;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainActivity$special$$inlined$viewModels$default$1(t tVar) {
        super(0);
        this.$this_viewModels = tVar;
    }

    @Override // r7.a
    public final g1 invoke() {
        return this.$this_viewModels.getDefaultViewModelProviderFactory();
    }
}
