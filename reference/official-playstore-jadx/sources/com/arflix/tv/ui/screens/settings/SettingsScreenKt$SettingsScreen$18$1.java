package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.ScrollState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsScreenKt$SettingsScreen$18$1", f = "SettingsScreen.kt", l = {632, 647}, m = "invokeSuspend", v = 2)
public final class SettingsScreenKt$SettingsScreen$18$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<Zone> $activeZone$delegate;
    final /* synthetic */ MutableIntState $contentFocusIndex$delegate;
    final /* synthetic */ SettingsFocusTracker $focusTracker;
    final /* synthetic */ ScrollState $scrollState;
    final /* synthetic */ MutableIntState $sectionIndex$delegate;
    final /* synthetic */ r7.l<String, Integer> $sectionMaxIndex;
    final /* synthetic */ List<String> $sections;
    float F$0;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SettingsScreenKt$SettingsScreen$18$1(SettingsFocusTracker settingsFocusTracker, ScrollState scrollState, List<String> list, r7.l<? super String, Integer> lVar, MutableState<Zone> mutableState, MutableIntState mutableIntState, MutableIntState mutableIntState2, d7.d<? super SettingsScreenKt$SettingsScreen$18$1> dVar) {
        super(2, dVar);
        this.$focusTracker = settingsFocusTracker;
        this.$scrollState = scrollState;
        this.$sections = list;
        this.$sectionMaxIndex = lVar;
        this.$activeZone$delegate = mutableState;
        this.$contentFocusIndex$delegate = mutableIntState;
        this.$sectionIndex$delegate = mutableIntState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        SettingsScreenKt$SettingsScreen$18$1 settingsScreenKt$SettingsScreen$18$1 = new SettingsScreenKt$SettingsScreen$18$1(this.$focusTracker, this.$scrollState, this.$sections, this.$sectionMaxIndex, this.$activeZone$delegate, this.$contentFocusIndex$delegate, this.$sectionIndex$delegate, dVar);
        settingsScreenKt$SettingsScreen$18$1.L$0 = obj;
        return settingsScreenKt$SettingsScreen$18$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x006a, code lost:
    
        if (androidx.compose.foundation.relocation.a.a(r0, null, r13, 1, null) == r7) goto L35;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsScreenKt$SettingsScreen$18$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((SettingsScreenKt$SettingsScreen$18$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
