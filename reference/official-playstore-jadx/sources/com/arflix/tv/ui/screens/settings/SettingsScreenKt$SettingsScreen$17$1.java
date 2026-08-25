package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.ScrollState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsScreenKt$SettingsScreen$17$1", f = "SettingsScreen.kt", l = {603}, m = "invokeSuspend", v = 2)
public final class SettingsScreenKt$SettingsScreen$17$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<Zone> $activeZone$delegate;
    final /* synthetic */ boolean $isTouchDevice;
    final /* synthetic */ MutableIntState $sectionIndex$delegate;
    final /* synthetic */ ScrollState $sectionScrollState;
    final /* synthetic */ List<String> $sections;
    float F$0;
    int I$0;
    int I$1;
    int I$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsScreenKt$SettingsScreen$17$1(boolean z, ScrollState scrollState, List<String> list, MutableState<Zone> mutableState, MutableIntState mutableIntState, d7.d<? super SettingsScreenKt$SettingsScreen$17$1> dVar) {
        super(2, dVar);
        this.$isTouchDevice = z;
        this.$sectionScrollState = scrollState;
        this.$sections = list;
        this.$activeZone$delegate = mutableState;
        this.$sectionIndex$delegate = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new SettingsScreenKt$SettingsScreen$17$1(this.$isTouchDevice, this.$sectionScrollState, this.$sections, this.$activeZone$delegate, this.$sectionIndex$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int maxValue;
        int i10 = this.label;
        x6.t0 t0Var = x6.t0.f22605a;
        if (i10 == 0) {
            k2.c.G(obj);
            if (this.$isTouchDevice || SettingsScreenKt.SettingsScreen$lambda$36(this.$activeZone$delegate) != Zone.SECTION || (maxValue = this.$sectionScrollState.getMaxValue()) <= 0) {
                return t0Var;
            }
            int iW = t7.a.w(this.$sections);
            if (iW < 1) {
                iW = 1;
            }
            float fN = qb.d.n(this.$sectionIndex$delegate.getIntValue(), 0, iW) / iW;
            int iN = qb.d.n((int) (maxValue * fN), 0, maxValue);
            if (Math.abs(this.$sectionScrollState.getValue() - iN) > 24) {
                ScrollState scrollState = this.$sectionScrollState;
                this.I$0 = maxValue;
                this.I$1 = iW;
                this.F$0 = fN;
                this.I$2 = iN;
                this.label = 1;
                Object objAnimateScrollTo$default = ScrollState.animateScrollTo$default(scrollState, iN, null, this, 2, null);
                e7.a aVar = e7.a.f15033i;
                if (objAnimateScrollTo$default == aVar) {
                    return aVar;
                }
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((SettingsScreenKt$SettingsScreen$17$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
