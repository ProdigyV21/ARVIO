package androidx.compose.material3;

import androidx.compose.animation.core.MutableTransitionState;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.TransformOrigin;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0005\u001a\u00020\u0004*\u00020\u0004H&¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\t\u001a\u00020\u0004*\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0007H&¢\u0006\u0004\b\t\u0010\nJW\u0010\u0017\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u00102\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\r0\u0012¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015H\u0007¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/compose/material3/ExposedDropdownMenuBoxScope;", "", "<init>", "()V", "Landroidx/compose/ui/Modifier;", "menuAnchor", "(Landroidx/compose/ui/Modifier;)Landroidx/compose/ui/Modifier;", "", "matchTextFieldWidth", "exposedDropdownSize", "(Landroidx/compose/ui/Modifier;Z)Landroidx/compose/ui/Modifier;", "expanded", "Lkotlin/Function0;", "Lx6/t0;", "onDismissRequest", "modifier", "Landroidx/compose/foundation/ScrollState;", "scrollState", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "ExposedDropdownMenu", "(ZLr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/ScrollState;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class ExposedDropdownMenuBoxScope {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: androidx.compose.material3.ExposedDropdownMenuBoxScope$ExposedDropdownMenu$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $content;
        final /* synthetic */ MutableTransitionState<Boolean> $expandedState;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ ScrollState $scrollState;
        final /* synthetic */ MutableState<TransformOrigin> $transformOriginState;
        final /* synthetic */ ExposedDropdownMenuBoxScope this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(MutableTransitionState<Boolean> mutableTransitionState, MutableState<TransformOrigin> mutableState, ScrollState scrollState, ExposedDropdownMenuBoxScope exposedDropdownMenuBoxScope, Modifier modifier, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar) {
            super(2);
            this.$expandedState = mutableTransitionState;
            this.$transformOriginState = mutableState;
            this.$scrollState = scrollState;
            this.this$0 = exposedDropdownMenuBoxScope;
            this.$modifier = modifier;
            this.$content = qVar;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(723773237, i10, -1, "androidx.compose.material3.ExposedDropdownMenuBoxScope.ExposedDropdownMenu.<anonymous> (ExposedDropdownMenu.android.kt:297)");
            }
            MenuKt.DropdownMenuContent(this.$expandedState, this.$transformOriginState, this.$scrollState, ExposedDropdownMenuBoxScope.exposedDropdownSize$default(this.this$0, this.$modifier, false, 1, null), this.$content, composer, MutableTransitionState.$stable | 48, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ExposedDropdownMenuBoxScope$ExposedDropdownMenu$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<ColumnScope, Composer, Integer, t0> $content;
        final /* synthetic */ boolean $expanded;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onDismissRequest;
        final /* synthetic */ ScrollState $scrollState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(boolean z, r7.a<t0> aVar, Modifier modifier, ScrollState scrollState, q<? super ColumnScope, ? super Composer, ? super Integer, t0> qVar, int i10, int i11) {
            super(2);
            this.$expanded = z;
            this.$onDismissRequest = aVar;
            this.$modifier = modifier;
            this.$scrollState = scrollState;
            this.$content = qVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            ExposedDropdownMenuBoxScope.this.ExposedDropdownMenu(this.$expanded, this.$onDismissRequest, this.$modifier, this.$scrollState, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    public static /* synthetic */ Modifier exposedDropdownSize$default(ExposedDropdownMenuBoxScope exposedDropdownMenuBoxScope, Modifier modifier, boolean z, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: exposedDropdownSize");
        }
        if ((i10 & 1) != 0) {
            z = true;
        }
        return exposedDropdownMenuBoxScope.exposedDropdownSize(modifier, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:117:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x011c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void ExposedDropdownMenu(boolean r26, r7.a<x6.t0> r27, androidx.compose.ui.Modifier r28, androidx.compose.foundation.ScrollState r29, r7.q<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 506
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ExposedDropdownMenuBoxScope.ExposedDropdownMenu(boolean, r7.a, androidx.compose.ui.Modifier, androidx.compose.foundation.ScrollState, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    public abstract Modifier exposedDropdownSize(Modifier modifier, boolean z);

    public abstract Modifier menuAnchor(Modifier modifier);
}
