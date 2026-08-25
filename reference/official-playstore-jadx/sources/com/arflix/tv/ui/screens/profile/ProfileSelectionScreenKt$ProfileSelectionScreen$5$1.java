package com.arflix.tv.ui.screens.profile;

import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt$ProfileSelectionScreen$5$1", f = "ProfileSelectionScreen.kt", l = {128, 140}, m = "invokeSuspend", v = 2)
public final class ProfileSelectionScreenKt$ProfileSelectionScreen$5$1 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ List<FocusRequester> $focusRequesters;
    final /* synthetic */ State<ProfileUiState> $uiState$delegate;
    int I$0;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileSelectionScreenKt$ProfileSelectionScreen$5$1(List<FocusRequester> list, State<ProfileUiState> state, d7.d<? super ProfileSelectionScreenKt$ProfileSelectionScreen$5$1> dVar) {
        super(2, dVar);
        this.$focusRequesters = list;
        this.$uiState$delegate = state;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new ProfileSelectionScreenKt$ProfileSelectionScreen$5$1(this.$focusRequesters, this.$uiState$delegate, dVar);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:0|2|(1:(1:(6:6|47|41|(1:43)|44|45)(2:7|8))(1:9))(4:10|(3:12|(0)|39)|44|45)|15|(5:19|(2:20|(2:22|(1:52)(1:25))(2:51|26))|27|(1:30)|(1:32))|49|33|(1:35)|44|45|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00ac, code lost:
    
        r8.L$0 = null;
        r8.I$0 = r2;
        r8.label = 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b8, code lost:
    
        if (ka.s0.a(100, r8) != r3) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00bb, code lost:
    
        r0 = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c6 A[Catch: IllegalStateException -> 0x00c9, TRY_LEAVE, TryCatch #0 {IllegalStateException -> 0x00c9, blocks: (B:41:0x00bc, B:43:0x00c6), top: B:47:0x00bc }] */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt$ProfileSelectionScreen$5$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((ProfileSelectionScreenKt$ProfileSelectionScreen$5$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
