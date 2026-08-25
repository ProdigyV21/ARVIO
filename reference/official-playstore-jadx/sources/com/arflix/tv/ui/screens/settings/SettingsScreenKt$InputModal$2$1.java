package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.ScrollState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsScreenKt$InputModal$2$1", f = "SettingsScreen.kt", l = {10436, 10440}, m = "invokeSuspend", v = 2)
public final class SettingsScreenKt$InputModal$2$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ List<InputField> $fields;
    final /* synthetic */ MutableIntState $focusedIndex$delegate;
    final /* synthetic */ ScrollState $formScrollState;
    final /* synthetic */ MutableState<Integer> $lastFocusedFieldIndex$delegate;
    int I$0;
    int I$1;
    int I$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SettingsScreenKt$InputModal$2$1(List<InputField> list, ScrollState scrollState, MutableIntState mutableIntState, MutableState<Integer> mutableState, d7.d<? super SettingsScreenKt$InputModal$2$1> dVar) {
        super(2, dVar);
        this.$fields = list;
        this.$formScrollState = scrollState;
        this.$focusedIndex$delegate = mutableIntState;
        this.$lastFocusedFieldIndex$delegate = mutableState;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        SettingsScreenKt$InputModal$2$1 settingsScreenKt$InputModal$2$1 = new SettingsScreenKt$InputModal$2$1(this.$fields, this.$formScrollState, this.$focusedIndex$delegate, this.$lastFocusedFieldIndex$delegate, dVar);
        settingsScreenKt$InputModal$2$1.L$0 = obj;
        return settingsScreenKt$InputModal$2$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0087, code lost:
    
        if (androidx.compose.foundation.ScrollState.animateScrollTo$default(r6, r7, null, r12, 2, null) == r5) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b0, code lost:
    
        if (androidx.compose.foundation.ScrollState.animateScrollTo$default(r6, r7, null, r12, 2, null) == r5) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b2, code lost:
    
        return r5;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = r12.L$0
            ka.k0 r0 = (ka.k0) r0
            int r0 = r12.label
            r1 = 2
            r2 = 1
            if (r0 == 0) goto L27
            if (r0 == r2) goto L1f
            if (r0 != r1) goto L17
            java.lang.Object r0 = r12.L$1
            ka.k0 r0 = (ka.k0) r0
            k2.c.G(r13)     // Catch: java.lang.Throwable -> Lb3
            goto Lb3
        L17:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L1f:
            java.lang.Object r0 = r12.L$1
            ka.k0 r0 = (ka.k0) r0
            k2.c.G(r13)     // Catch: java.lang.Throwable -> L8a
            goto L8a
        L27:
            k2.c.G(r13)
            java.util.List<com.arflix.tv.ui.screens.settings.InputField> r13 = r12.$fields
            int r13 = r13.size()
            androidx.compose.runtime.MutableIntState r0 = r12.$focusedIndex$delegate
            int r0 = com.arflix.tv.ui.screens.settings.SettingsScreenKt.access$InputModal$lambda$1(r0)
            r3 = 0
            r4 = 0
            e7.a r5 = e7.a.f15033i
            if (r0 < 0) goto L8c
            if (r0 >= r13) goto L8c
            androidx.compose.runtime.MutableState<java.lang.Integer> r13 = r12.$lastFocusedFieldIndex$delegate
            androidx.compose.runtime.MutableIntState r0 = r12.$focusedIndex$delegate
            int r0 = com.arflix.tv.ui.screens.settings.SettingsScreenKt.access$InputModal$lambda$1(r0)
            java.lang.Integer r1 = new java.lang.Integer
            r1.<init>(r0)
            com.arflix.tv.ui.screens.settings.SettingsScreenKt.access$InputModal$lambda$5(r13, r1)
            androidx.compose.foundation.ScrollState r13 = r12.$formScrollState
            int r13 = r13.getMaxValue()
            if (r13 <= 0) goto L8a
            java.util.List<com.arflix.tv.ui.screens.settings.InputField> r0 = r12.$fields
            int r0 = r0.size()
            if (r0 <= r2) goto L8a
            androidx.compose.runtime.MutableIntState r0 = r12.$focusedIndex$delegate
            int r0 = com.arflix.tv.ui.screens.settings.SettingsScreenKt.access$InputModal$lambda$1(r0)
            float r0 = (float) r0
            java.util.List<com.arflix.tv.ui.screens.settings.InputField> r1 = r12.$fields
            int r1 = r1.size()
            int r1 = r1 - r2
            float r1 = (float) r1
            float r0 = r0 / r1
            float r1 = (float) r13
            float r0 = r0 * r1
            int r7 = (int) r0
            androidx.compose.foundation.ScrollState r6 = r12.$formScrollState
            r12.L$0 = r4     // Catch: java.lang.Throwable -> L8a
            r12.L$1 = r4     // Catch: java.lang.Throwable -> L8a
            r12.I$0 = r13     // Catch: java.lang.Throwable -> L8a
            r12.I$1 = r7     // Catch: java.lang.Throwable -> L8a
            r12.I$2 = r3     // Catch: java.lang.Throwable -> L8a
            r12.label = r2     // Catch: java.lang.Throwable -> L8a
            r8 = 0
            r10 = 2
            r11 = 0
            r9 = r12
            java.lang.Object r13 = androidx.compose.foundation.ScrollState.animateScrollTo$default(r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> Lb3
            if (r13 != r5) goto Lb3
            goto Lb2
        L8a:
            r9 = r12
            goto Lb3
        L8c:
            r9 = r12
            androidx.compose.runtime.MutableIntState r13 = r9.$focusedIndex$delegate
            int r13 = com.arflix.tv.ui.screens.settings.SettingsScreenKt.access$InputModal$lambda$1(r13)
            java.util.List<com.arflix.tv.ui.screens.settings.InputField> r0 = r9.$fields
            int r0 = r0.size()
            if (r13 < r0) goto Lb3
            androidx.compose.foundation.ScrollState r6 = r9.$formScrollState
            int r7 = r6.getMaxValue()     // Catch: java.lang.Throwable -> Lb3
            r9.L$0 = r4     // Catch: java.lang.Throwable -> Lb3
            r9.L$1 = r4     // Catch: java.lang.Throwable -> Lb3
            r9.I$0 = r3     // Catch: java.lang.Throwable -> Lb3
            r9.label = r1     // Catch: java.lang.Throwable -> Lb3
            r8 = 0
            r10 = 2
            r11 = 0
            java.lang.Object r13 = androidx.compose.foundation.ScrollState.animateScrollTo$default(r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> Lb3
            if (r13 != r5) goto Lb3
        Lb2:
            return r5
        Lb3:
            x6.t0 r13 = x6.t0.f22605a
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsScreenKt$InputModal$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((SettingsScreenKt$InputModal$2$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
