package androidx.compose.material3;

import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.Strings;
import androidx.compose.material3.tokens.DialogTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.window.DialogProperties;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u001aß\u0001\u0010\u0018\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0015\b\u0002\u0010\u0007\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0015\b\u0002\u0010\b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001aD\u0010\u001a\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00142\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0003H\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001aD\u0010\u0018\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00142\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0003H\u0007¢\u0006\u0004\b\u0018\u0010\u001b\"\u0014\u0010\u001c\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d\"\u0014\u0010\u001e\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001d\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001f"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "onDismissRequest", "Landroidx/compose/runtime/Composable;", "confirmButton", "Landroidx/compose/ui/Modifier;", "modifier", "dismissButton", "icon", LinkHeader.Parameters.Title, "text", "Landroidx/compose/ui/graphics/Shape;", "shape", "Landroidx/compose/ui/graphics/Color;", "containerColor", "iconContentColor", "titleContentColor", "textContentColor", "Landroidx/compose/ui/unit/Dp;", "tonalElevation", "Landroidx/compose/ui/window/DialogProperties;", "properties", "AlertDialog-Oix01E0", "(Lr7/a;Lr7/p;Landroidx/compose/ui/Modifier;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Landroidx/compose/ui/graphics/Shape;JJJJFLandroidx/compose/ui/window/DialogProperties;Landroidx/compose/runtime/Composer;III)V", "AlertDialog", "content", "BasicAlertDialog", "(Lr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/window/DialogProperties;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "ButtonsMainAxisSpacing", "F", "ButtonsCrossAxisSpacing", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AndroidAlertDialog_androidKt {
    private static final float ButtonsMainAxisSpacing = Dp.m5678constructorimpl(8);
    private static final float ButtonsCrossAxisSpacing = Dp.m5678constructorimpl(12);

    /* JADX INFO: renamed from: androidx.compose.material3.AndroidAlertDialog_androidKt$AlertDialog$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $confirmButton;
        final /* synthetic */ long $containerColor;
        final /* synthetic */ p<Composer, Integer, t0> $dismissButton;
        final /* synthetic */ p<Composer, Integer, t0> $icon;
        final /* synthetic */ long $iconContentColor;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ p<Composer, Integer, t0> $text;
        final /* synthetic */ long $textContentColor;
        final /* synthetic */ p<Composer, Integer, t0> $title;
        final /* synthetic */ long $titleContentColor;
        final /* synthetic */ float $tonalElevation;

        /* JADX INFO: renamed from: androidx.compose.material3.AndroidAlertDialog_androidKt$AlertDialog$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C00841 extends r implements p<Composer, Integer, t0> {
            final /* synthetic */ p<Composer, Integer, t0> $confirmButton;
            final /* synthetic */ p<Composer, Integer, t0> $dismissButton;

            /* JADX INFO: renamed from: androidx.compose.material3.AndroidAlertDialog_androidKt$AlertDialog$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class C00851 extends r implements p<Composer, Integer, t0> {
                final /* synthetic */ p<Composer, Integer, t0> $confirmButton;
                final /* synthetic */ p<Composer, Integer, t0> $dismissButton;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public C00851(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2) {
                    super(2);
                    this.$dismissButton = pVar;
                    this.$confirmButton = pVar2;
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
                        ComposerKt.traceEventStart(-909933713, i10, -1, "androidx.compose.material3.AlertDialog.<anonymous>.<anonymous>.<anonymous> (AndroidAlertDialog.android.kt:103)");
                    }
                    p<Composer, Integer, t0> pVar = this.$dismissButton;
                    composer.startReplaceableGroup(-1969500671);
                    if (pVar != null) {
                        pVar.invoke(composer, 0);
                    }
                    composer.endReplaceableGroup();
                    this.$confirmButton.invoke(composer, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C00841(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2) {
                super(2);
                this.$dismissButton = pVar;
                this.$confirmButton = pVar2;
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
                    ComposerKt.traceEventStart(-3244296, i10, -1, "androidx.compose.material3.AlertDialog.<anonymous>.<anonymous> (AndroidAlertDialog.android.kt:99)");
                }
                AlertDialogKt.m1216AlertDialogFlowRowixp7dh8(AndroidAlertDialog_androidKt.ButtonsMainAxisSpacing, AndroidAlertDialog_androidKt.ButtonsCrossAxisSpacing, ComposableLambdaKt.composableLambda(composer, -909933713, true, new C00851(this.$dismissButton, this.$confirmButton)), composer, 438);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, Shape shape, long j10, float f10, long j11, long j12, long j13, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5) {
            super(2);
            this.$icon = pVar;
            this.$title = pVar2;
            this.$text = pVar3;
            this.$shape = shape;
            this.$containerColor = j10;
            this.$tonalElevation = f10;
            this.$iconContentColor = j11;
            this.$titleContentColor = j12;
            this.$textContentColor = j13;
            this.$dismissButton = pVar4;
            this.$confirmButton = pVar5;
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
                ComposerKt.traceEventStart(461081686, i10, -1, "androidx.compose.material3.AlertDialog.<anonymous> (AndroidAlertDialog.android.kt:97)");
            }
            AlertDialogKt.m1215AlertDialogContent4hvqGtA(ComposableLambdaKt.composableLambda(composer, -3244296, true, new C00841(this.$dismissButton, this.$confirmButton)), null, this.$icon, this.$title, this.$text, this.$shape, this.$containerColor, this.$tonalElevation, ColorSchemeKt.getValue(DialogTokens.INSTANCE.getActionLabelTextColor(), composer, 6), this.$iconContentColor, this.$titleContentColor, this.$textContentColor, composer, 6, 0, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AndroidAlertDialog_androidKt$AlertDialog$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $confirmButton;
        final /* synthetic */ long $containerColor;
        final /* synthetic */ p<Composer, Integer, t0> $dismissButton;
        final /* synthetic */ p<Composer, Integer, t0> $icon;
        final /* synthetic */ long $iconContentColor;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onDismissRequest;
        final /* synthetic */ DialogProperties $properties;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ p<Composer, Integer, t0> $text;
        final /* synthetic */ long $textContentColor;
        final /* synthetic */ p<Composer, Integer, t0> $title;
        final /* synthetic */ long $titleContentColor;
        final /* synthetic */ float $tonalElevation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(r7.a<t0> aVar, p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, Shape shape, long j10, long j11, long j12, long j13, float f10, DialogProperties dialogProperties, int i10, int i11, int i12) {
            super(2);
            this.$onDismissRequest = aVar;
            this.$confirmButton = pVar;
            this.$modifier = modifier;
            this.$dismissButton = pVar2;
            this.$icon = pVar3;
            this.$title = pVar4;
            this.$text = pVar5;
            this.$shape = shape;
            this.$containerColor = j10;
            this.$iconContentColor = j11;
            this.$titleContentColor = j12;
            this.$textContentColor = j13;
            this.$tonalElevation = f10;
            this.$properties = dialogProperties;
            this.$$changed = i10;
            this.$$changed1 = i11;
            this.$$default = i12;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AndroidAlertDialog_androidKt.m1220AlertDialogOix01E0(this.$onDismissRequest, this.$confirmButton, this.$modifier, this.$dismissButton, this.$icon, this.$title, this.$text, this.$shape, this.$containerColor, this.$iconContentColor, this.$titleContentColor, this.$textContentColor, this.$tonalElevation, this.$properties, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AndroidAlertDialog_androidKt$AlertDialog$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onDismissRequest;
        final /* synthetic */ DialogProperties $properties;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(r7.a<t0> aVar, Modifier modifier, DialogProperties dialogProperties, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
            super(2);
            this.$onDismissRequest = aVar;
            this.$modifier = modifier;
            this.$properties = dialogProperties;
            this.$content = pVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AndroidAlertDialog_androidKt.AlertDialog(this.$onDismissRequest, this.$modifier, this.$properties, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AndroidAlertDialog_androidKt$BasicAlertDialog$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04551 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ Modifier $modifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04551(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar) {
            super(2);
            this.$modifier = modifier;
            this.$content = pVar;
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
                ComposerKt.traceEventStart(-777289724, i10, -1, "androidx.compose.material3.BasicAlertDialog.<anonymous> (AndroidAlertDialog.android.kt:160)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            String strM1994getStringNWtq28 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_dialog), composer, 0);
            Modifier modifierM582sizeInqDBjuR0$default = SizeKt.m582sizeInqDBjuR0$default(this.$modifier, AlertDialogKt.getDialogMinWidth(), 0.0f, AlertDialogKt.getDialogMaxWidth(), 0.0f, 10, null);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            composer.startReplaceableGroup(-874813489);
            boolean zChanged = composer.changed(strM1994getStringNWtq28);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new AndroidAlertDialog_androidKt$BasicAlertDialog$1$1$1(strM1994getStringNWtq28);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            Modifier modifierThen = modifierM582sizeInqDBjuR0$default.then(SemanticsModifierKt.semantics$default(companion2, false, (l) objRememberedValue, 1, null));
            p<Composer, Integer, t0> pVar = this.$content;
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, true, composer, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierThen);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer);
            p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            if (a0.c.B(pVar, composer, 0)) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AndroidAlertDialog_androidKt$BasicAlertDialog$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04562 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onDismissRequest;
        final /* synthetic */ DialogProperties $properties;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04562(r7.a<t0> aVar, Modifier modifier, DialogProperties dialogProperties, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
            super(2);
            this.$onDismissRequest = aVar;
            this.$modifier = modifier;
            this.$properties = dialogProperties;
            this.$content = pVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AndroidAlertDialog_androidKt.BasicAlertDialog(this.$onDismissRequest, this.$modifier, this.$properties, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AlertDialog(r7.a<x6.t0> r17, androidx.compose.ui.Modifier r18, androidx.compose.ui.window.DialogProperties r19, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r20, androidx.compose.runtime.Composer r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AndroidAlertDialog_androidKt.AlertDialog(r7.a, androidx.compose.ui.Modifier, androidx.compose.ui.window.DialogProperties, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x026a  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x031e  */
    /* JADX WARN: Removed duplicated region for block: B:227:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0113  */
    /* JADX INFO: renamed from: AlertDialog-Oix01E0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1220AlertDialogOix01E0(r7.a<x6.t0> r30, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r31, androidx.compose.ui.Modifier r32, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r33, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r34, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r35, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r36, androidx.compose.ui.graphics.Shape r37, long r38, long r40, long r42, long r44, float r46, androidx.compose.ui.window.DialogProperties r47, androidx.compose.runtime.Composer r48, int r49, int r50, int r51) {
        /*
            Method dump skipped, instruction units count: 822
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AndroidAlertDialog_androidKt.m1220AlertDialogOix01E0(r7.a, r7.p, androidx.compose.ui.Modifier, r7.p, r7.p, r7.p, r7.p, androidx.compose.ui.graphics.Shape, long, long, long, long, float, androidx.compose.ui.window.DialogProperties, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BasicAlertDialog(r7.a<x6.t0> r16, androidx.compose.ui.Modifier r17, androidx.compose.ui.window.DialogProperties r18, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r19, androidx.compose.runtime.Composer r20, int r21, int r22) {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AndroidAlertDialog_androidKt.BasicAlertDialog(r7.a, androidx.compose.ui.Modifier, androidx.compose.ui.window.DialogProperties, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }
}
