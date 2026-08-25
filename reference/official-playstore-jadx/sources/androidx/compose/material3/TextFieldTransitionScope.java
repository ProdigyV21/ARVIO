package androidx.compose.material3;

import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JÇ\u0001\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00060\t¢\u0006\u0002\b\n2\u0006\u0010\r\u001a\u00020\f2z\u0010\u0018\u001av\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00170\u000e¢\u0006\u0002\b\nH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001c²\u0006\f\u0010\u0012\u001a\u00020\u000f8\nX\u008a\u0084\u0002²\u0006\f\u0010\u0015\u001a\u00020\u000f8\nX\u008a\u0084\u0002²\u0006\f\u0010\u0016\u001a\u00020\u000f8\nX\u008a\u0084\u0002²\u0006\f\u0010\u0013\u001a\u00020\u00068\nX\u008a\u0084\u0002²\u0006\f\u0010\u0014\u001a\u00020\u00068\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material3/TextFieldTransitionScope;", "", "<init>", "()V", "Landroidx/compose/material3/InputPhase;", "inputState", "Landroidx/compose/ui/graphics/Color;", "focusedTextStyleColor", "unfocusedTextStyleColor", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composable;", "contentColor", "", "showLabel", "Lkotlin/Function5;", "", "Lx6/y;", ContentDisposition.Parameters.Name, "labelProgress", "labelTextStyleColor", "labelContentColor", "placeholderOpacity", "prefixSuffixOpacity", "Lx6/t0;", "content", "Transition-DTcfvLk", "(Landroidx/compose/material3/InputPhase;JJLr7/q;ZLr7/u;Landroidx/compose/runtime/Composer;I)V", "Transition", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class TextFieldTransitionScope {
    public static final TextFieldTransitionScope INSTANCE = new TextFieldTransitionScope();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[InputPhase.values().length];
            try {
                iArr[InputPhase.Focused.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[InputPhase.UnfocusedEmpty.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[InputPhase.UnfocusedNotEmpty.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private TextFieldTransitionScope() {
    }

    private static final float Transition_DTcfvLk$lambda$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float Transition_DTcfvLk$lambda$3(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float Transition_DTcfvLk$lambda$5(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final long Transition_DTcfvLk$lambda$7(State<Color> state) {
        return state.getValue().m3482unboximpl();
    }

    private static final long Transition_DTcfvLk$lambda$8(State<Color> state) {
        return state.getValue().m3482unboximpl();
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x04a1  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01ac  */
    /* JADX INFO: renamed from: Transition-DTcfvLk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m2151TransitionDTcfvLk(androidx.compose.material3.InputPhase r27, long r28, long r30, r7.q<? super androidx.compose.material3.InputPhase, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, androidx.compose.ui.graphics.Color> r32, boolean r33, r7.u<? super java.lang.Float, ? super androidx.compose.ui.graphics.Color, ? super androidx.compose.ui.graphics.Color, ? super java.lang.Float, ? super java.lang.Float, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r34, androidx.compose.runtime.Composer r35, int r36) {
        /*
            Method dump skipped, instruction units count: 1215
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldTransitionScope.m2151TransitionDTcfvLk(androidx.compose.material3.InputPhase, long, long, r7.q, boolean, r7.u, androidx.compose.runtime.Composer, int):void");
    }
}
