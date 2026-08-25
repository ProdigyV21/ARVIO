package androidx.compose.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.tokens.TimeInputTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0002H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "it", "invoke", "(Lr7/p;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TimePickerKt$TimePickerTextField$1$1$2 extends r implements q<p<? super Composer, ? super Integer, ? extends t0>, Composer, Integer, t0> {
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ TextFieldColors $textFieldColors;
    final /* synthetic */ TextFieldValue $value;

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$TimePickerTextField$1$1$2$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ TextFieldColors $textFieldColors;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors) {
            super(2);
            this.$interactionSource = mutableInteractionSource;
            this.$textFieldColors = textFieldColors;
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
                ComposerKt.traceEventStart(-968963953, i10, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1636)");
            }
            OutlinedTextFieldDefaults.INSTANCE.m1744ContainerBoxnbWgWpA(true, false, this.$interactionSource, this.$textFieldColors, ShapesKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldContainerShape(), composer, 6), 0.0f, 0.0f, composer, 12583350, 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TimePickerKt$TimePickerTextField$1$1$2(TextFieldValue textFieldValue, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors) {
        super(3);
        this.$value = textFieldValue;
        this.$interactionSource = mutableInteractionSource;
        this.$textFieldColors = textFieldColors;
    }

    @Override // r7.q
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((p<? super Composer, ? super Integer, t0>) obj, (Composer) obj2, ((Number) obj3).intValue());
        return t0.f22605a;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void invoke(p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        int i11;
        if ((i10 & 6) == 0) {
            i11 = i10 | (composer.changedInstance(pVar) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i11 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2133555260, i11, -1, "androidx.compose.material3.TimePickerTextField.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1626)");
        }
        OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
        String text = this.$value.getText();
        VisualTransformation none = VisualTransformation.INSTANCE.getNone();
        PaddingValues paddingValuesM522PaddingValues0680j_4 = PaddingKt.m522PaddingValues0680j_4(Dp.m5678constructorimpl(0));
        MutableInteractionSource mutableInteractionSource = this.$interactionSource;
        TextFieldColors textFieldColors = this.$textFieldColors;
        outlinedTextFieldDefaults.DecorationBox(text, pVar, true, true, none, mutableInteractionSource, false, null, null, null, null, null, null, null, textFieldColors, paddingValuesM522PaddingValues0680j_4, ComposableLambdaKt.composableLambda(composer, -968963953, true, new AnonymousClass1(mutableInteractionSource, textFieldColors)), composer, ((i11 << 3) & 112) | 224640, 14352384, 16320);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}
