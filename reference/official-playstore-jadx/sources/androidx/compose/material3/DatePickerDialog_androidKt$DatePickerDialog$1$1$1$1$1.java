package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class DatePickerDialog_androidKt$DatePickerDialog$1$1$1$1$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ p<Composer, Integer, t0> $confirmButton;
    final /* synthetic */ p<Composer, Integer, t0> $dismissButton;

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerDialog_androidKt$DatePickerDialog$1$1$1$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $confirmButton;
        final /* synthetic */ p<Composer, Integer, t0> $dismissButton;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2) {
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
                ComposerKt.traceEventStart(-330605494, i10, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:104)");
            }
            p<Composer, Integer, t0> pVar = this.$dismissButton;
            composer.startReplaceableGroup(1914517404);
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
    public DatePickerDialog_androidKt$DatePickerDialog$1$1$1$1$1(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2) {
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
            ComposerKt.traceEventStart(1174914401, i10, -1, "androidx.compose.material3.DatePickerDialog.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (DatePickerDialog.android.kt:100)");
        }
        AlertDialogKt.m1216AlertDialogFlowRowixp7dh8(DatePickerDialog_androidKt.DialogButtonsMainAxisSpacing, DatePickerDialog_androidKt.DialogButtonsCrossAxisSpacing, ComposableLambdaKt.composableLambda(composer, -330605494, true, new AnonymousClass1(this.$dismissButton, this.$confirmButton)), composer, 438);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
