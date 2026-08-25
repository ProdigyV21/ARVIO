package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import com.arflix.tv.ui.screens.player.SubtitleAiModel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsScreenKt$AiModelDialog$2$1$1$1$1$1", f = "SettingsScreen.kt", l = {6589}, m = "invokeSuspend", v = 2)
public final class SettingsScreenKt$AiModelDialog$2$1$1$1$1$1 extends f7.j implements r7.p<PointerInputScope, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ SubtitleAiModel $model;
    final /* synthetic */ r7.l<SubtitleAiModel, x6.t0> $onModelSelected;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SettingsScreenKt$AiModelDialog$2$1$1$1$1$1(r7.l<? super SubtitleAiModel, x6.t0> lVar, SubtitleAiModel subtitleAiModel, d7.d<? super SettingsScreenKt$AiModelDialog$2$1$1$1$1$1> dVar) {
        super(2, dVar);
        this.$onModelSelected = lVar;
        this.$model = subtitleAiModel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invokeSuspend$lambda$0(r7.l lVar, SubtitleAiModel subtitleAiModel, Offset offset) {
        lVar.invoke(subtitleAiModel);
        return x6.t0.f22605a;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        SettingsScreenKt$AiModelDialog$2$1$1$1$1$1 settingsScreenKt$AiModelDialog$2$1$1$1$1$1 = new SettingsScreenKt$AiModelDialog$2$1$1$1$1$1(this.$onModelSelected, this.$model, dVar);
        settingsScreenKt$AiModelDialog$2$1$1$1$1$1.L$0 = obj;
        return settingsScreenKt$AiModelDialog$2$1$1$1$1$1;
    }

    @Override // r7.p
    public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super x6.t0> dVar) {
        return ((SettingsScreenKt$AiModelDialog$2$1$1$1$1$1) create(pointerInputScope, dVar)).invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            c4 c4Var = new c4(this.$onModelSelected, this.$model, 0);
            this.L$0 = null;
            this.label = 1;
            Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, null, c4Var, this, 7, null);
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
