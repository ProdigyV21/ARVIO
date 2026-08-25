package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsScreenKt$AiApiKeyDialog$3$1$1$3$2$1", f = "SettingsScreen.kt", l = {6703}, m = "invokeSuspend", v = 2)
public final class SettingsScreenKt$AiApiKeyDialog$3$1$1$3$2$1 extends f7.j implements r7.p<PointerInputScope, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ r7.l<String, x6.t0> $onSave;
    final /* synthetic */ MutableState<String> $value$delegate;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SettingsScreenKt$AiApiKeyDialog$3$1$1$3$2$1(r7.l<? super String, x6.t0> lVar, MutableState<String> mutableState, d7.d<? super SettingsScreenKt$AiApiKeyDialog$3$1$1$3$2$1> dVar) {
        super(2, dVar);
        this.$onSave = lVar;
        this.$value$delegate = mutableState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invokeSuspend$lambda$0(r7.l lVar, MutableState mutableState, Offset offset) {
        lVar.invoke(SettingsScreenKt.AiApiKeyDialog$lambda$1(mutableState));
        return x6.t0.f22605a;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        SettingsScreenKt$AiApiKeyDialog$3$1$1$3$2$1 settingsScreenKt$AiApiKeyDialog$3$1$1$3$2$1 = new SettingsScreenKt$AiApiKeyDialog$3$1$1$3$2$1(this.$onSave, this.$value$delegate, dVar);
        settingsScreenKt$AiApiKeyDialog$3$1$1$3$2$1.L$0 = obj;
        return settingsScreenKt$AiApiKeyDialog$3$1$1$3$2$1;
    }

    @Override // r7.p
    public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super x6.t0> dVar) {
        return ((SettingsScreenKt$AiApiKeyDialog$3$1$1$3$2$1) create(pointerInputScope, dVar)).invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            int i11 = 0;
            b4 b4Var = new b4(i11, this.$value$delegate, this.$onSave);
            this.L$0 = null;
            this.label = 1;
            Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, null, b4Var, this, 7, null);
            e7.a aVar = e7.a.f15033i;
            if (objDetectTapGestures$default == aVar) {
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
