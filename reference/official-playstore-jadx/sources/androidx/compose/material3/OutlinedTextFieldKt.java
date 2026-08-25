package androidx.compose.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.Strings;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¦\u0001\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aØ\u0002\u0010&\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u001f\u001a\u00020\u001d2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020$H\u0007¢\u0006\u0004\b&\u0010'\u001aØ\u0002\u0010&\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020(2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u001f\u001a\u00020\u001d2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020$H\u0007¢\u0006\u0004\b&\u0010)\u001aª\u0002\u0010&\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u001f\u001a\u00020\u001d2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020$H\u0007¢\u0006\u0004\b&\u0010*\u001aª\u0002\u0010&\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020(2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u001f\u001a\u00020\u001d2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020$H\u0007¢\u0006\u0004\b&\u0010+\u001a\u0082\u0002\u00107\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0011\u0010,\u001a\r\u0012\u0004\u0012\u00020\u00030\f¢\u0006\u0002\b\r2\u0019\u0010\u000f\u001a\u0015\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002¢\u0006\u0002\b\r2\u0013\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0013\u0010-\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0013\u0010.\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0013\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0013\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u00100\u001a\u00020/2\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u00030\u00022\u0011\u00103\u001a\r\u0012\u0004\u0012\u00020\u00030\f¢\u0006\u0002\b\r2\u0013\u00104\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0006\u00106\u001a\u000205H\u0001¢\u0006\u0004\b7\u00108\u001a\u001b\u0010:\u001a\u00020\u001d*\u00020\u001d2\u0006\u00109\u001a\u00020\u001dH\u0002¢\u0006\u0004\b:\u0010;\u001aj\u0010H\u001a\u00020\u001d2\u0006\u0010<\u001a\u00020\u001d2\u0006\u0010=\u001a\u00020\u001d2\u0006\u0010>\u001a\u00020\u001d2\u0006\u0010?\u001a\u00020\u001d2\u0006\u0010@\u001a\u00020\u001d2\u0006\u0010A\u001a\u00020\u001d2\u0006\u0010B\u001a\u00020\u001d2\u0006\u00100\u001a\u00020/2\u0006\u0010D\u001a\u00020C2\u0006\u0010E\u001a\u00020/2\u0006\u00106\u001a\u000205H\u0002ø\u0001\u0000¢\u0006\u0004\bF\u0010G\u001ar\u0010S\u001a\u00020\u001d2\u0006\u0010I\u001a\u00020\u001d2\u0006\u0010J\u001a\u00020\u001d2\u0006\u0010K\u001a\u00020\u001d2\u0006\u0010L\u001a\u00020\u001d2\u0006\u0010M\u001a\u00020\u001d2\u0006\u0010N\u001a\u00020\u001d2\u0006\u0010O\u001a\u00020\u001d2\u0006\u0010P\u001a\u00020\u001d2\u0006\u00100\u001a\u00020/2\u0006\u0010D\u001a\u00020C2\u0006\u0010E\u001a\u00020/2\u0006\u00106\u001a\u000205H\u0002ø\u0001\u0000¢\u0006\u0004\bQ\u0010R\u001a¡\u0001\u0010c\u001a\u00020\u0003*\u00020T2\u0006\u0010U\u001a\u00020\u001d2\u0006\u0010V\u001a\u00020\u001d2\b\u0010X\u001a\u0004\u0018\u00010W2\b\u0010Y\u001a\u0004\u0018\u00010W2\b\u0010Z\u001a\u0004\u0018\u00010W2\b\u0010[\u001a\u0004\u0018\u00010W2\u0006\u0010\\\u001a\u00020W2\b\u0010]\u001a\u0004\u0018\u00010W2\b\u0010^\u001a\u0004\u0018\u00010W2\u0006\u0010_\u001a\u00020W2\b\u0010`\u001a\u0004\u0018\u00010W2\u0006\u00100\u001a\u00020/2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010E\u001a\u00020/2\u0006\u0010b\u001a\u00020a2\u0006\u00106\u001a\u000205H\u0002¢\u0006\u0004\bc\u0010d\u001a&\u0010h\u001a\u00020\u0005*\u00020\u00052\u0006\u0010e\u001a\u0002012\u0006\u00106\u001a\u000205H\u0000ø\u0001\u0000¢\u0006\u0004\bf\u0010g\"\u0014\u0010j\u001a\u00020i8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bj\u0010k\"\u001a\u0010l\u001a\u00020i8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bl\u0010k\u001a\u0004\bm\u0010n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006o"}, d2 = {"", "value", "Lkotlin/Function1;", "Lx6/t0;", "onValueChange", "Landroidx/compose/ui/Modifier;", "modifier", "", "enabled", "readOnly", "Landroidx/compose/ui/text/TextStyle;", "textStyle", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "label", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "isError", "Landroidx/compose/ui/text/input/VisualTransformation;", "visualTransformation", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardActions;", "keyboardActions", "singleLine", "", "maxLines", "minLines", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Landroidx/compose/ui/graphics/Shape;", "shape", "Landroidx/compose/material3/TextFieldColors;", "colors", "OutlinedTextField", "(Ljava/lang/String;Lr7/l;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "Landroidx/compose/ui/text/input/TextFieldValue;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lr7/l;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "(Ljava/lang/String;Lr7/l;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "(Landroidx/compose/ui/text/input/TextFieldValue;Lr7/l;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "textField", "leading", "trailing", "", "animationProgress", "Landroidx/compose/ui/geometry/Size;", "onLabelMeasured", TtmlNode.RUBY_CONTAINER, "supporting", "Landroidx/compose/foundation/layout/PaddingValues;", "paddingValues", "OutlinedTextFieldLayout", "(Landroidx/compose/ui/Modifier;Lr7/p;Lr7/q;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;ZFLr7/l;Lr7/p;Lr7/p;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;II)V", "from", "substractConstraintSafely", "(II)I", "leadingPlaceableWidth", "trailingPlaceableWidth", "prefixPlaceableWidth", "suffixPlaceableWidth", "textFieldPlaceableWidth", "labelPlaceableWidth", "placeholderPlaceableWidth", "Landroidx/compose/ui/unit/Constraints;", "constraints", "density", "calculateWidth-DHJA7U0", "(IIIIIIIFJFLandroidx/compose/foundation/layout/PaddingValues;)I", "calculateWidth", "leadingHeight", "trailingHeight", "prefixHeight", "suffixHeight", "textFieldHeight", "labelHeight", "placeholderHeight", "supportingHeight", "calculateHeight-mKXJcVc", "(IIIIIIIIFJFLandroidx/compose/foundation/layout/PaddingValues;)I", "calculateHeight", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "totalHeight", "width", "Landroidx/compose/ui/layout/Placeable;", "leadingPlaceable", "trailingPlaceable", "prefixPlaceable", "suffixPlaceable", "textFieldPlaceable", "labelPlaceable", "placeholderPlaceable", "containerPlaceable", "supportingPlaceable", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "place", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;IILandroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;FZFLandroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/foundation/layout/PaddingValues;)V", "labelSize", "outlineCutout-12SF9DM", "(Landroidx/compose/ui/Modifier;JLandroidx/compose/foundation/layout/PaddingValues;)Landroidx/compose/ui/Modifier;", "outlineCutout", "Landroidx/compose/ui/unit/Dp;", "OutlinedTextFieldInnerPadding", "F", "OutlinedTextFieldTopPadding", "getOutlinedTextFieldTopPadding", "()F", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OutlinedTextFieldKt {
    private static final float OutlinedTextFieldInnerPadding = Dp.m5678constructorimpl(4);
    private static final float OutlinedTextFieldTopPadding = Dp.m5678constructorimpl(8);

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$10, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass10 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$changed2;
        final /* synthetic */ int $$default;
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ int $minLines;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<TextFieldValue, t0> $onValueChange;
        final /* synthetic */ p<Composer, Integer, t0> $placeholder;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ p<Composer, Integer, t0> $supportingText;
        final /* synthetic */ TextStyle $textStyle;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
        final /* synthetic */ TextFieldValue $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass10(TextFieldValue textFieldValue, l<? super TextFieldValue, t0> lVar, Modifier modifier, boolean z, boolean z5, TextStyle textStyle, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, boolean z10, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z11, int i10, int i11, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i12, int i13, int i14, int i15) {
            super(2);
            this.$value = textFieldValue;
            this.$onValueChange = lVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$readOnly = z5;
            this.$textStyle = textStyle;
            this.$label = pVar;
            this.$placeholder = pVar2;
            this.$leadingIcon = pVar3;
            this.$trailingIcon = pVar4;
            this.$supportingText = pVar5;
            this.$isError = z10;
            this.$visualTransformation = visualTransformation;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$singleLine = z11;
            this.$maxLines = i10;
            this.$minLines = i11;
            this.$interactionSource = mutableInteractionSource;
            this.$shape = shape;
            this.$colors = textFieldColors;
            this.$$changed = i12;
            this.$$changed1 = i13;
            this.$$changed2 = i14;
            this.$$default = i15;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            OutlinedTextFieldKt.OutlinedTextField(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$readOnly, this.$textStyle, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$supportingText, this.$isError, this.$visualTransformation, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$minLines, this.$interactionSource, this.$shape, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed2), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ TextStyle $mergedTextStyle;
        final /* synthetic */ int $minLines;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<String, t0> $onValueChange;
        final /* synthetic */ p<Composer, Integer, t0> $placeholder;
        final /* synthetic */ p<Composer, Integer, t0> $prefix;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ p<Composer, Integer, t0> $suffix;
        final /* synthetic */ p<Composer, Integer, t0> $supportingText;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
        final /* synthetic */ String $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements l<SemanticsPropertyReceiver, t0> {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

            public AnonymousClass1() {
                super(1);
            }

            public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SemanticsPropertyReceiver) obj);
                return t0.f22605a;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0002H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "innerTextField", "invoke", "(Lr7/p;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C01062 extends r implements q<p<? super Composer, ? super Integer, ? extends t0>, Composer, Integer, t0> {
            final /* synthetic */ TextFieldColors $colors;
            final /* synthetic */ boolean $enabled;
            final /* synthetic */ MutableInteractionSource $interactionSource;
            final /* synthetic */ boolean $isError;
            final /* synthetic */ p<Composer, Integer, t0> $label;
            final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
            final /* synthetic */ p<Composer, Integer, t0> $placeholder;
            final /* synthetic */ p<Composer, Integer, t0> $prefix;
            final /* synthetic */ Shape $shape;
            final /* synthetic */ boolean $singleLine;
            final /* synthetic */ p<Composer, Integer, t0> $suffix;
            final /* synthetic */ p<Composer, Integer, t0> $supportingText;
            final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
            final /* synthetic */ String $value;
            final /* synthetic */ VisualTransformation $visualTransformation;

            /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$2$2$1, reason: invalid class name */
            @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
                final /* synthetic */ TextFieldColors $colors;
                final /* synthetic */ boolean $enabled;
                final /* synthetic */ MutableInteractionSource $interactionSource;
                final /* synthetic */ boolean $isError;
                final /* synthetic */ Shape $shape;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(boolean z, boolean z5, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape) {
                    super(2);
                    this.$enabled = z;
                    this.$isError = z5;
                    this.$interactionSource = mutableInteractionSource;
                    this.$colors = textFieldColors;
                    this.$shape = shape;
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
                        ComposerKt.traceEventStart(2108828640, i10, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:219)");
                    }
                    OutlinedTextFieldDefaults.INSTANCE.m1744ContainerBoxnbWgWpA(this.$enabled, this.$isError, this.$interactionSource, this.$colors, this.$shape, 0.0f, 0.0f, composer, 12582912, 96);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public C01062(String str, boolean z, boolean z5, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, boolean z10, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, TextFieldColors textFieldColors, Shape shape) {
                super(3);
                this.$value = str;
                this.$enabled = z;
                this.$singleLine = z5;
                this.$visualTransformation = visualTransformation;
                this.$interactionSource = mutableInteractionSource;
                this.$isError = z10;
                this.$label = pVar;
                this.$placeholder = pVar2;
                this.$leadingIcon = pVar3;
                this.$trailingIcon = pVar4;
                this.$prefix = pVar5;
                this.$suffix = pVar6;
                this.$supportingText = pVar7;
                this.$colors = textFieldColors;
                this.$shape = shape;
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
                    ComposerKt.traceEventStart(1474611661, i11, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:202)");
                }
                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                int i12 = i11;
                String str = this.$value;
                boolean z = this.$enabled;
                boolean z5 = this.$singleLine;
                VisualTransformation visualTransformation = this.$visualTransformation;
                MutableInteractionSource mutableInteractionSource = this.$interactionSource;
                boolean z10 = this.$isError;
                p<Composer, Integer, t0> pVar2 = this.$label;
                p<Composer, Integer, t0> pVar3 = this.$placeholder;
                p<Composer, Integer, t0> pVar4 = this.$leadingIcon;
                p<Composer, Integer, t0> pVar5 = this.$trailingIcon;
                p<Composer, Integer, t0> pVar6 = this.$prefix;
                p<Composer, Integer, t0> pVar7 = this.$suffix;
                p<Composer, Integer, t0> pVar8 = this.$supportingText;
                TextFieldColors textFieldColors = this.$colors;
                outlinedTextFieldDefaults.DecorationBox(str, pVar, z, z5, visualTransformation, mutableInteractionSource, z10, pVar2, pVar3, pVar4, pVar5, pVar6, pVar7, pVar8, textFieldColors, null, ComposableLambdaKt.composableLambda(composer, 2108828640, true, new AnonymousClass1(z, z10, mutableInteractionSource, textFieldColors, this.$shape)), composer, (i12 << 3) & 112, 14155776, 32768);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, boolean z, TextFieldColors textFieldColors, String str, l<? super String, t0> lVar, boolean z5, boolean z10, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z11, int i10, int i11, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, Shape shape) {
            super(2);
            this.$label = pVar;
            this.$modifier = modifier;
            this.$isError = z;
            this.$colors = textFieldColors;
            this.$value = str;
            this.$onValueChange = lVar;
            this.$enabled = z5;
            this.$readOnly = z10;
            this.$mergedTextStyle = textStyle;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$singleLine = z11;
            this.$maxLines = i10;
            this.$minLines = i11;
            this.$visualTransformation = visualTransformation;
            this.$interactionSource = mutableInteractionSource;
            this.$placeholder = pVar2;
            this.$leadingIcon = pVar3;
            this.$trailingIcon = pVar4;
            this.$prefix = pVar5;
            this.$suffix = pVar6;
            this.$supportingText = pVar7;
            this.$shape = shape;
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
                ComposerKt.traceEventStart(-1886965181, i10, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:173)");
            }
            Modifier modifierM533paddingqDBjuR0$default = this.$label != null ? PaddingKt.m533paddingqDBjuR0$default(SemanticsModifierKt.semantics(this.$modifier, true, AnonymousClass1.INSTANCE), 0.0f, OutlinedTextFieldKt.getOutlinedTextFieldTopPadding(), 0.0f, 0.0f, 13, null) : this.$modifier;
            boolean z = this.$isError;
            Strings.Companion companion = Strings.INSTANCE;
            Modifier modifierDefaultErrorSemantics = TextFieldImplKt.defaultErrorSemantics(modifierM533paddingqDBjuR0$default, z, Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(androidx.compose.ui.R.string.default_error_message), composer, 0));
            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
            Modifier modifierM562defaultMinSizeVpY3zN4 = SizeKt.m562defaultMinSizeVpY3zN4(modifierDefaultErrorSemantics, outlinedTextFieldDefaults.m1749getMinWidthD9Ej5fM(), outlinedTextFieldDefaults.m1748getMinHeightD9Ej5fM());
            SolidColor solidColor = new SolidColor(this.$colors.cursorColor$material3_release(this.$isError, composer, 0).getValue().m3482unboximpl(), null);
            String str = this.$value;
            l<String, t0> lVar = this.$onValueChange;
            boolean z5 = this.$enabled;
            boolean z10 = this.$readOnly;
            TextStyle textStyle = this.$mergedTextStyle;
            KeyboardOptions keyboardOptions = this.$keyboardOptions;
            KeyboardActions keyboardActions = this.$keyboardActions;
            boolean z11 = this.$singleLine;
            int i11 = this.$maxLines;
            int i12 = this.$minLines;
            VisualTransformation visualTransformation = this.$visualTransformation;
            MutableInteractionSource mutableInteractionSource = this.$interactionSource;
            BasicTextFieldKt.BasicTextField(str, lVar, modifierM562defaultMinSizeVpY3zN4, z5, z10, textStyle, keyboardOptions, keyboardActions, z11, i11, i12, visualTransformation, (l<? super TextLayoutResult, t0>) null, mutableInteractionSource, solidColor, ComposableLambdaKt.composableLambda(composer, 1474611661, true, new C01062(str, z5, z11, visualTransformation, mutableInteractionSource, this.$isError, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$colors, this.$shape)), composer, 0, 196608, 4096);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$changed2;
        final /* synthetic */ int $$default;
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ int $minLines;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<String, t0> $onValueChange;
        final /* synthetic */ p<Composer, Integer, t0> $placeholder;
        final /* synthetic */ p<Composer, Integer, t0> $prefix;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ p<Composer, Integer, t0> $suffix;
        final /* synthetic */ p<Composer, Integer, t0> $supportingText;
        final /* synthetic */ TextStyle $textStyle;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
        final /* synthetic */ String $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(String str, l<? super String, t0> lVar, Modifier modifier, boolean z, boolean z5, TextStyle textStyle, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, boolean z10, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z11, int i10, int i11, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i12, int i13, int i14, int i15) {
            super(2);
            this.$value = str;
            this.$onValueChange = lVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$readOnly = z5;
            this.$textStyle = textStyle;
            this.$label = pVar;
            this.$placeholder = pVar2;
            this.$leadingIcon = pVar3;
            this.$trailingIcon = pVar4;
            this.$prefix = pVar5;
            this.$suffix = pVar6;
            this.$supportingText = pVar7;
            this.$isError = z10;
            this.$visualTransformation = visualTransformation;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$singleLine = z11;
            this.$maxLines = i10;
            this.$minLines = i11;
            this.$interactionSource = mutableInteractionSource;
            this.$shape = shape;
            this.$colors = textFieldColors;
            this.$$changed = i12;
            this.$$changed1 = i13;
            this.$$changed2 = i14;
            this.$$default = i15;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            OutlinedTextFieldKt.OutlinedTextField(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$readOnly, this.$textStyle, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$isError, this.$visualTransformation, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$minLines, this.$interactionSource, this.$shape, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed2), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass5 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ TextStyle $mergedTextStyle;
        final /* synthetic */ int $minLines;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<TextFieldValue, t0> $onValueChange;
        final /* synthetic */ p<Composer, Integer, t0> $placeholder;
        final /* synthetic */ p<Composer, Integer, t0> $prefix;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ p<Composer, Integer, t0> $suffix;
        final /* synthetic */ p<Composer, Integer, t0> $supportingText;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
        final /* synthetic */ TextFieldValue $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$5$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements l<SemanticsPropertyReceiver, t0> {
            public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

            public AnonymousClass1() {
                super(1);
            }

            public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SemanticsPropertyReceiver) obj);
                return t0.f22605a;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$5$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0002H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "innerTextField", "invoke", "(Lr7/p;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass2 extends r implements q<p<? super Composer, ? super Integer, ? extends t0>, Composer, Integer, t0> {
            final /* synthetic */ TextFieldColors $colors;
            final /* synthetic */ boolean $enabled;
            final /* synthetic */ MutableInteractionSource $interactionSource;
            final /* synthetic */ boolean $isError;
            final /* synthetic */ p<Composer, Integer, t0> $label;
            final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
            final /* synthetic */ p<Composer, Integer, t0> $placeholder;
            final /* synthetic */ p<Composer, Integer, t0> $prefix;
            final /* synthetic */ Shape $shape;
            final /* synthetic */ boolean $singleLine;
            final /* synthetic */ p<Composer, Integer, t0> $suffix;
            final /* synthetic */ p<Composer, Integer, t0> $supportingText;
            final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
            final /* synthetic */ TextFieldValue $value;
            final /* synthetic */ VisualTransformation $visualTransformation;

            /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$5$2$1, reason: invalid class name */
            @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
                final /* synthetic */ TextFieldColors $colors;
                final /* synthetic */ boolean $enabled;
                final /* synthetic */ MutableInteractionSource $interactionSource;
                final /* synthetic */ boolean $isError;
                final /* synthetic */ Shape $shape;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(boolean z, boolean z5, MutableInteractionSource mutableInteractionSource, TextFieldColors textFieldColors, Shape shape) {
                    super(2);
                    this.$enabled = z;
                    this.$isError = z5;
                    this.$interactionSource = mutableInteractionSource;
                    this.$colors = textFieldColors;
                    this.$shape = shape;
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
                        ComposerKt.traceEventStart(255570733, i10, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous>.<anonymous> (OutlinedTextField.kt:379)");
                    }
                    OutlinedTextFieldDefaults.INSTANCE.m1744ContainerBoxnbWgWpA(this.$enabled, this.$isError, this.$interactionSource, this.$colors, this.$shape, 0.0f, 0.0f, composer, 12582912, 96);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass2(TextFieldValue textFieldValue, boolean z, boolean z5, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, boolean z10, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, TextFieldColors textFieldColors, Shape shape) {
                super(3);
                this.$value = textFieldValue;
                this.$enabled = z;
                this.$singleLine = z5;
                this.$visualTransformation = visualTransformation;
                this.$interactionSource = mutableInteractionSource;
                this.$isError = z10;
                this.$label = pVar;
                this.$placeholder = pVar2;
                this.$leadingIcon = pVar3;
                this.$trailingIcon = pVar4;
                this.$prefix = pVar5;
                this.$suffix = pVar6;
                this.$supportingText = pVar7;
                this.$colors = textFieldColors;
                this.$shape = shape;
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
                    ComposerKt.traceEventStart(-757328870, i11, -1, "androidx.compose.material3.OutlinedTextField.<anonymous>.<anonymous> (OutlinedTextField.kt:362)");
                }
                OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
                String text = this.$value.getText();
                boolean z = this.$enabled;
                boolean z5 = this.$singleLine;
                VisualTransformation visualTransformation = this.$visualTransformation;
                MutableInteractionSource mutableInteractionSource = this.$interactionSource;
                boolean z10 = this.$isError;
                p<Composer, Integer, t0> pVar2 = this.$label;
                p<Composer, Integer, t0> pVar3 = this.$placeholder;
                p<Composer, Integer, t0> pVar4 = this.$leadingIcon;
                p<Composer, Integer, t0> pVar5 = this.$trailingIcon;
                p<Composer, Integer, t0> pVar6 = this.$prefix;
                p<Composer, Integer, t0> pVar7 = this.$suffix;
                p<Composer, Integer, t0> pVar8 = this.$supportingText;
                TextFieldColors textFieldColors = this.$colors;
                outlinedTextFieldDefaults.DecorationBox(text, pVar, z, z5, visualTransformation, mutableInteractionSource, z10, pVar2, pVar3, pVar4, pVar5, pVar6, pVar7, pVar8, textFieldColors, null, ComposableLambdaKt.composableLambda(composer, 255570733, true, new AnonymousClass1(z, z10, mutableInteractionSource, textFieldColors, this.$shape)), composer, (i11 << 3) & 112, 14155776, 32768);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, boolean z, TextFieldColors textFieldColors, TextFieldValue textFieldValue, l<? super TextFieldValue, t0> lVar, boolean z5, boolean z10, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z11, int i10, int i11, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, Shape shape) {
            super(2);
            this.$label = pVar;
            this.$modifier = modifier;
            this.$isError = z;
            this.$colors = textFieldColors;
            this.$value = textFieldValue;
            this.$onValueChange = lVar;
            this.$enabled = z5;
            this.$readOnly = z10;
            this.$mergedTextStyle = textStyle;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$singleLine = z11;
            this.$maxLines = i10;
            this.$minLines = i11;
            this.$visualTransformation = visualTransformation;
            this.$interactionSource = mutableInteractionSource;
            this.$placeholder = pVar2;
            this.$leadingIcon = pVar3;
            this.$trailingIcon = pVar4;
            this.$prefix = pVar5;
            this.$suffix = pVar6;
            this.$supportingText = pVar7;
            this.$shape = shape;
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
                ComposerKt.traceEventStart(1830921872, i10, -1, "androidx.compose.material3.OutlinedTextField.<anonymous> (OutlinedTextField.kt:333)");
            }
            Modifier modifierM533paddingqDBjuR0$default = this.$label != null ? PaddingKt.m533paddingqDBjuR0$default(SemanticsModifierKt.semantics(this.$modifier, true, AnonymousClass1.INSTANCE), 0.0f, OutlinedTextFieldKt.getOutlinedTextFieldTopPadding(), 0.0f, 0.0f, 13, null) : this.$modifier;
            boolean z = this.$isError;
            Strings.Companion companion = Strings.INSTANCE;
            Modifier modifierDefaultErrorSemantics = TextFieldImplKt.defaultErrorSemantics(modifierM533paddingqDBjuR0$default, z, Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(androidx.compose.ui.R.string.default_error_message), composer, 0));
            OutlinedTextFieldDefaults outlinedTextFieldDefaults = OutlinedTextFieldDefaults.INSTANCE;
            Modifier modifierM562defaultMinSizeVpY3zN4 = SizeKt.m562defaultMinSizeVpY3zN4(modifierDefaultErrorSemantics, outlinedTextFieldDefaults.m1749getMinWidthD9Ej5fM(), outlinedTextFieldDefaults.m1748getMinHeightD9Ej5fM());
            SolidColor solidColor = new SolidColor(this.$colors.cursorColor$material3_release(this.$isError, composer, 0).getValue().m3482unboximpl(), null);
            TextFieldValue textFieldValue = this.$value;
            l<TextFieldValue, t0> lVar = this.$onValueChange;
            boolean z5 = this.$enabled;
            boolean z10 = this.$readOnly;
            TextStyle textStyle = this.$mergedTextStyle;
            KeyboardOptions keyboardOptions = this.$keyboardOptions;
            KeyboardActions keyboardActions = this.$keyboardActions;
            boolean z11 = this.$singleLine;
            int i11 = this.$maxLines;
            int i12 = this.$minLines;
            VisualTransformation visualTransformation = this.$visualTransformation;
            MutableInteractionSource mutableInteractionSource = this.$interactionSource;
            BasicTextFieldKt.BasicTextField(textFieldValue, lVar, modifierM562defaultMinSizeVpY3zN4, z5, z10, textStyle, keyboardOptions, keyboardActions, z11, i11, i12, visualTransformation, (l<? super TextLayoutResult, t0>) null, mutableInteractionSource, solidColor, ComposableLambdaKt.composableLambda(composer, -757328870, true, new AnonymousClass2(textFieldValue, z5, z11, visualTransformation, mutableInteractionSource, this.$isError, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$colors, this.$shape)), composer, 0, 196608, 4096);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$6, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass6 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$changed2;
        final /* synthetic */ int $$default;
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ int $minLines;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<TextFieldValue, t0> $onValueChange;
        final /* synthetic */ p<Composer, Integer, t0> $placeholder;
        final /* synthetic */ p<Composer, Integer, t0> $prefix;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ p<Composer, Integer, t0> $suffix;
        final /* synthetic */ p<Composer, Integer, t0> $supportingText;
        final /* synthetic */ TextStyle $textStyle;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
        final /* synthetic */ TextFieldValue $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass6(TextFieldValue textFieldValue, l<? super TextFieldValue, t0> lVar, Modifier modifier, boolean z, boolean z5, TextStyle textStyle, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, boolean z10, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z11, int i10, int i11, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i12, int i13, int i14, int i15) {
            super(2);
            this.$value = textFieldValue;
            this.$onValueChange = lVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$readOnly = z5;
            this.$textStyle = textStyle;
            this.$label = pVar;
            this.$placeholder = pVar2;
            this.$leadingIcon = pVar3;
            this.$trailingIcon = pVar4;
            this.$prefix = pVar5;
            this.$suffix = pVar6;
            this.$supportingText = pVar7;
            this.$isError = z10;
            this.$visualTransformation = visualTransformation;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$singleLine = z11;
            this.$maxLines = i10;
            this.$minLines = i11;
            this.$interactionSource = mutableInteractionSource;
            this.$shape = shape;
            this.$colors = textFieldColors;
            this.$$changed = i12;
            this.$$changed1 = i13;
            this.$$changed2 = i14;
            this.$$default = i15;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            OutlinedTextFieldKt.OutlinedTextField(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$readOnly, this.$textStyle, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$isError, this.$visualTransformation, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$minLines, this.$interactionSource, this.$shape, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed2), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextField$8, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass8 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$changed2;
        final /* synthetic */ int $$default;
        final /* synthetic */ TextFieldColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ boolean $isError;
        final /* synthetic */ KeyboardActions $keyboardActions;
        final /* synthetic */ KeyboardOptions $keyboardOptions;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ int $maxLines;
        final /* synthetic */ int $minLines;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<String, t0> $onValueChange;
        final /* synthetic */ p<Composer, Integer, t0> $placeholder;
        final /* synthetic */ boolean $readOnly;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ p<Composer, Integer, t0> $supportingText;
        final /* synthetic */ TextStyle $textStyle;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
        final /* synthetic */ String $value;
        final /* synthetic */ VisualTransformation $visualTransformation;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass8(String str, l<? super String, t0> lVar, Modifier modifier, boolean z, boolean z5, TextStyle textStyle, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, boolean z10, VisualTransformation visualTransformation, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z11, int i10, int i11, MutableInteractionSource mutableInteractionSource, Shape shape, TextFieldColors textFieldColors, int i12, int i13, int i14, int i15) {
            super(2);
            this.$value = str;
            this.$onValueChange = lVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$readOnly = z5;
            this.$textStyle = textStyle;
            this.$label = pVar;
            this.$placeholder = pVar2;
            this.$leadingIcon = pVar3;
            this.$trailingIcon = pVar4;
            this.$supportingText = pVar5;
            this.$isError = z10;
            this.$visualTransformation = visualTransformation;
            this.$keyboardOptions = keyboardOptions;
            this.$keyboardActions = keyboardActions;
            this.$singleLine = z11;
            this.$maxLines = i10;
            this.$minLines = i11;
            this.$interactionSource = mutableInteractionSource;
            this.$shape = shape;
            this.$colors = textFieldColors;
            this.$$changed = i12;
            this.$$changed1 = i13;
            this.$$changed2 = i14;
            this.$$default = i15;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            OutlinedTextFieldKt.OutlinedTextField(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$readOnly, this.$textStyle, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$supportingText, this.$isError, this.$visualTransformation, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$minLines, this.$interactionSource, this.$shape, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed2), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.OutlinedTextFieldKt$OutlinedTextFieldLayout$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05312 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ float $animationProgress;
        final /* synthetic */ p<Composer, Integer, t0> $container;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leading;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<Size, t0> $onLabelMeasured;
        final /* synthetic */ PaddingValues $paddingValues;
        final /* synthetic */ q<Modifier, Composer, Integer, t0> $placeholder;
        final /* synthetic */ p<Composer, Integer, t0> $prefix;
        final /* synthetic */ boolean $singleLine;
        final /* synthetic */ p<Composer, Integer, t0> $suffix;
        final /* synthetic */ p<Composer, Integer, t0> $supporting;
        final /* synthetic */ p<Composer, Integer, t0> $textField;
        final /* synthetic */ p<Composer, Integer, t0> $trailing;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05312(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, q<? super Modifier, ? super Composer, ? super Integer, t0> qVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, boolean z, float f10, l<? super Size, t0> lVar, p<? super Composer, ? super Integer, t0> pVar7, p<? super Composer, ? super Integer, t0> pVar8, PaddingValues paddingValues, int i10, int i11) {
            super(2);
            this.$modifier = modifier;
            this.$textField = pVar;
            this.$placeholder = qVar;
            this.$label = pVar2;
            this.$leading = pVar3;
            this.$trailing = pVar4;
            this.$prefix = pVar5;
            this.$suffix = pVar6;
            this.$singleLine = z;
            this.$animationProgress = f10;
            this.$onLabelMeasured = lVar;
            this.$container = pVar7;
            this.$supporting = pVar8;
            this.$paddingValues = paddingValues;
            this.$$changed = i10;
            this.$$changed1 = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            OutlinedTextFieldKt.OutlinedTextFieldLayout(this.$modifier, this.$textField, this.$placeholder, this.$label, this.$leading, this.$trailing, this.$prefix, this.$suffix, this.$singleLine, this.$animationProgress, this.$onLabelMeasured, this.$container, this.$supporting, this.$paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0392  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x03c9  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x03d0  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x03d3  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x03f5  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x03f9  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0404  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0421  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0424  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0453  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0461  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x046e  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x04a3  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x04f8  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x0591  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x05bd  */
    /* JADX WARN: Removed duplicated region for block: B:359:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0125  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void OutlinedTextField(java.lang.String r66, r7.l<? super java.lang.String, x6.t0> r67, androidx.compose.ui.Modifier r68, boolean r69, boolean r70, androidx.compose.ui.text.TextStyle r71, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r72, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r73, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r74, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r75, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r76, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r77, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r78, boolean r79, androidx.compose.ui.text.input.VisualTransformation r80, androidx.compose.foundation.text.KeyboardOptions r81, androidx.compose.foundation.text.KeyboardActions r82, boolean r83, int r84, int r85, androidx.compose.foundation.interaction.MutableInteractionSource r86, androidx.compose.ui.graphics.Shape r87, androidx.compose.material3.TextFieldColors r88, androidx.compose.runtime.Composer r89, int r90, int r91, int r92, int r93) {
        /*
            Method dump skipped, instruction units count: 1499
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField(java.lang.String, r7.l, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.material3.TextFieldColors, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    public static final void OutlinedTextFieldLayout(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, q<? super Modifier, ? super Composer, ? super Integer, t0> qVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, boolean z, float f10, l<? super Size, t0> lVar, p<? super Composer, ? super Integer, t0> pVar7, p<? super Composer, ? super Integer, t0> pVar8, PaddingValues paddingValues, Composer composer, int i10, int i11) {
        int i12;
        int i13;
        PaddingValues paddingValues2;
        float f11;
        p<? super Composer, ? super Integer, t0> pVar9;
        float f12;
        p<? super Composer, ? super Integer, t0> pVar10;
        Composer composerStartRestartGroup = composer.startRestartGroup(1408290209);
        if ((i10 & 6) == 0) {
            i12 = i10 | (composerStartRestartGroup.changed(modifier) ? 4 : 2);
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(qVar) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar2) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar3) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar4) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar5) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((12582912 & i10) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar6) ? 8388608 : 4194304;
        }
        if ((100663296 & i10) == 0) {
            i12 |= composerStartRestartGroup.changed(z) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i10 & 805306368) == 0) {
            i12 |= composerStartRestartGroup.changed(f10) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        int i14 = i12;
        if ((i11 & 6) == 0) {
            i13 = i11 | (composerStartRestartGroup.changedInstance(lVar) ? 4 : 2);
        } else {
            i13 = i11;
        }
        if ((i11 & 48) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(pVar7) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(pVar8) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            paddingValues2 = paddingValues;
            i13 |= composerStartRestartGroup.changed(paddingValues2) ? 2048 : 1024;
        } else {
            paddingValues2 = paddingValues;
        }
        int i15 = i13;
        if ((i14 & 306783379) == 306783378 && (i15 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            pVar9 = pVar2;
            pVar10 = pVar8;
            f12 = f10;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1408290209, i14, i15, "androidx.compose.material3.OutlinedTextFieldLayout (OutlinedTextField.kt:521)");
            }
            composerStartRestartGroup.startReplaceableGroup(-2058767641);
            boolean z5 = ((234881024 & i14) == 67108864) | ((i15 & 14) == 4) | ((i14 & 1879048192) == 536870912) | ((i15 & 7168) == 2048);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new OutlinedTextFieldMeasurePolicy(lVar, z, f10, paddingValues2);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            OutlinedTextFieldMeasurePolicy outlinedTextFieldMeasurePolicy = (OutlinedTextFieldMeasurePolicy) objRememberedValue;
            composerStartRestartGroup.endReplaceableGroup();
            LayoutDirection layoutDirection = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
            Updater.m2998setimpl(composerM2991constructorimpl, outlinedTextFieldMeasurePolicy, companion.getSetMeasurePolicy());
            Updater.m2998setimpl(composerM2991constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            p<ComposeUiNode, Integer, t0> setCompositeKeyHash = companion.getSetCompositeKeyHash();
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, setCompositeKeyHash);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            pVar7.invoke(composerStartRestartGroup, Integer.valueOf((i15 >> 3) & 14));
            composerStartRestartGroup.startReplaceableGroup(1116455313);
            if (pVar3 != null) {
                Modifier modifierThen = LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.LeadingId).then(TextFieldImplKt.getIconDefaultSizeModifier());
                Alignment center = Alignment.INSTANCE.getCenter();
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
                composerStartRestartGroup.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierThen);
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composerStartRestartGroup);
                p pVarU = a0.c.u(companion, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                pVar3.invoke(composerStartRestartGroup, Integer.valueOf((i14 >> 12) & 14));
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
            }
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(1116455598);
            if (pVar4 != null) {
                Modifier modifierThen2 = LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.TrailingId).then(TextFieldImplKt.getIconDefaultSizeModifier());
                Alignment center2 = Alignment.INSTANCE.getCenter();
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center2, false, composerStartRestartGroup, 6);
                composerStartRestartGroup.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor3 = companion.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierThen2);
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composerStartRestartGroup);
                p pVarU2 = a0.c.u(companion, composerM2991constructorimpl3, measurePolicyRememberBoxMeasurePolicy2, composerM2991constructorimpl3, currentCompositionLocalMap3);
                if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU2);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf3, composerStartRestartGroup, 0, 2058660585);
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                pVar4.invoke(composerStartRestartGroup, Integer.valueOf((i14 >> 15) & 14));
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
            }
            composerStartRestartGroup.endReplaceableGroup();
            float fCalculateStartPadding = PaddingKt.calculateStartPadding(paddingValues2, layoutDirection);
            float fCalculateEndPadding = PaddingKt.calculateEndPadding(paddingValues2, layoutDirection);
            if (pVar3 != null) {
                float fM5678constructorimpl = Dp.m5678constructorimpl(fCalculateStartPadding - TextFieldImplKt.getHorizontalIconPadding());
                float fM5678constructorimpl2 = Dp.m5678constructorimpl(0);
                if (fM5678constructorimpl < fM5678constructorimpl2) {
                    fM5678constructorimpl = fM5678constructorimpl2;
                }
                fCalculateStartPadding = Dp.m5678constructorimpl(fM5678constructorimpl);
            }
            float fM5678constructorimpl3 = fCalculateStartPadding;
            if (pVar4 != null) {
                float fM5678constructorimpl4 = Dp.m5678constructorimpl(fCalculateEndPadding - TextFieldImplKt.getHorizontalIconPadding());
                float fM5678constructorimpl5 = Dp.m5678constructorimpl(0);
                if (fM5678constructorimpl4 < fM5678constructorimpl5) {
                    fM5678constructorimpl4 = fM5678constructorimpl5;
                }
                fCalculateEndPadding = Dp.m5678constructorimpl(fM5678constructorimpl4);
            }
            composerStartRestartGroup.startReplaceableGroup(1116456488);
            if (pVar5 != null) {
                Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m566heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.PrefixId), TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null), fM5678constructorimpl3, 0.0f, TextFieldImplKt.getPrefixSuffixTextPadding(), 0.0f, 10, null);
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor4 = companion.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default);
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor4);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl4 = Updater.m2991constructorimpl(composerStartRestartGroup);
                p pVarU3 = a0.c.u(companion, composerM2991constructorimpl4, measurePolicyD, composerM2991constructorimpl4, currentCompositionLocalMap4);
                if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                    a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU3);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf4, composerStartRestartGroup, 0, 2058660585);
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                pVar5.invoke(composerStartRestartGroup, Integer.valueOf((i14 >> 18) & 14));
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
            }
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(1116456887);
            if (pVar6 != null) {
                f11 = fCalculateEndPadding;
                Modifier modifierM533paddingqDBjuR0$default2 = PaddingKt.m533paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m566heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.SuffixId), TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null), TextFieldImplKt.getPrefixSuffixTextPadding(), 0.0f, f11, 0.0f, 10, null);
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyD2 = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
                int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor5 = companion.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf5 = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default2);
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor5);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl5 = Updater.m2991constructorimpl(composerStartRestartGroup);
                p pVarU4 = a0.c.u(companion, composerM2991constructorimpl5, measurePolicyD2, composerM2991constructorimpl5, currentCompositionLocalMap5);
                if (composerM2991constructorimpl5.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                    a0.c.w(currentCompositeKeyHash5, composerM2991constructorimpl5, currentCompositeKeyHash5, pVarU4);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf5, composerStartRestartGroup, 0, 2058660585);
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                pVar6.invoke(composerStartRestartGroup, Integer.valueOf((i14 >> 21) & 14));
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
            } else {
                f11 = fCalculateEndPadding;
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Modifier modifierWrapContentHeight$default = SizeKt.wrapContentHeight$default(SizeKt.m566heightInVpY3zN4$default(companion2, TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null);
            if (pVar5 != null) {
                fM5678constructorimpl3 = Dp.m5678constructorimpl(0);
            }
            Modifier modifierM533paddingqDBjuR0$default3 = PaddingKt.m533paddingqDBjuR0$default(modifierWrapContentHeight$default, fM5678constructorimpl3, 0.0f, pVar6 == null ? f11 : Dp.m5678constructorimpl(0), 0.0f, 10, null);
            composerStartRestartGroup.startReplaceableGroup(1116457597);
            if (qVar != null) {
                qVar.invoke(LayoutIdKt.layoutId(companion2, TextFieldImplKt.PlaceholderId).then(modifierM533paddingqDBjuR0$default3), composerStartRestartGroup, Integer.valueOf((i14 >> 3) & 112));
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierThen3 = LayoutIdKt.layoutId(companion2, TextFieldImplKt.TextFieldId).then(modifierM533paddingqDBjuR0$default3);
            composerStartRestartGroup.startReplaceableGroup(733328855);
            Alignment.Companion companion3 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyD3 = a0.c.d(companion3, true, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor6 = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf6 = LayoutKt.modifierMaterializerOf(modifierThen3);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor6);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl6 = Updater.m2991constructorimpl(composerStartRestartGroup);
            p pVarU5 = a0.c.u(companion, composerM2991constructorimpl6, measurePolicyD3, composerM2991constructorimpl6, currentCompositionLocalMap6);
            if (composerM2991constructorimpl6.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                a0.c.w(currentCompositeKeyHash6, composerM2991constructorimpl6, currentCompositeKeyHash6, pVarU5);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf6, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
            pVar.invoke(composerStartRestartGroup, Integer.valueOf((i14 >> 3) & 14));
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(1116458015);
            if (pVar2 != null) {
                f12 = f10;
                Modifier modifierLayoutId = LayoutIdKt.layoutId(SizeKt.wrapContentHeight$default(SizeKt.m566heightInVpY3zN4$default(companion2, DpKt.m5721lerpMdfbLM(TextFieldImplKt.getMinTextLineHeight(), TextFieldImplKt.getMinFocusedLabelLineHeight(), f12), 0.0f, 2, null), null, false, 3, null), TextFieldImplKt.LabelId);
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyD4 = a0.c.d(companion3, false, composerStartRestartGroup, 0, -1323940314);
                int currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor7 = companion.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf7 = LayoutKt.modifierMaterializerOf(modifierLayoutId);
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor7);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl7 = Updater.m2991constructorimpl(composerStartRestartGroup);
                p pVarU6 = a0.c.u(companion, composerM2991constructorimpl7, measurePolicyD4, composerM2991constructorimpl7, currentCompositionLocalMap7);
                if (composerM2991constructorimpl7.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl7.rememberedValue(), Integer.valueOf(currentCompositeKeyHash7))) {
                    a0.c.w(currentCompositeKeyHash7, composerM2991constructorimpl7, currentCompositeKeyHash7, pVarU6);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf7, composerStartRestartGroup, 0, 2058660585);
                pVar9 = pVar2;
                pVar9.invoke(composerStartRestartGroup, Integer.valueOf((i14 >> 9) & 14));
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
            } else {
                pVar9 = pVar2;
                f12 = f10;
            }
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(-2058764244);
            if (pVar8 != null) {
                Modifier modifierPadding = PaddingKt.padding(SizeKt.wrapContentHeight$default(SizeKt.m566heightInVpY3zN4$default(LayoutIdKt.layoutId(companion2, TextFieldImplKt.SupportingId), TextFieldImplKt.getMinSupportingTextLineHeight(), 0.0f, 2, null), null, false, 3, null), TextFieldDefaults.m2119supportingTextPaddinga9UjIt4$material3_release$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null));
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyD5 = a0.c.d(companion3, false, composerStartRestartGroup, 0, -1323940314);
                int currentCompositeKeyHash8 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor8 = companion.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf8 = LayoutKt.modifierMaterializerOf(modifierPadding);
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor8);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl8 = Updater.m2991constructorimpl(composerStartRestartGroup);
                p pVarU7 = a0.c.u(companion, composerM2991constructorimpl8, measurePolicyD5, composerM2991constructorimpl8, currentCompositionLocalMap8);
                if (composerM2991constructorimpl8.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl8.rememberedValue(), Integer.valueOf(currentCompositeKeyHash8))) {
                    a0.c.w(currentCompositeKeyHash8, composerM2991constructorimpl8, currentCompositeKeyHash8, pVarU7);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf8, composerStartRestartGroup, 0, 2058660585);
                pVar10 = pVar8;
                pVar10.invoke(composerStartRestartGroup, Integer.valueOf((i15 >> 6) & 14));
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
            } else {
                pVar10 = pVar8;
            }
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C05312(modifier, pVar, qVar, pVar9, pVar3, pVar4, pVar5, pVar6, z, f12, lVar, pVar7, pVar10, paddingValues2, i10, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateHeight-mKXJcVc, reason: not valid java name */
    public static final int m1753calculateHeightmKXJcVc(int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, float f10, long j10, float f11, PaddingValues paddingValues) {
        int[] iArr = {i16, i12, i13, MathHelpersKt.lerp(i15, 0, f10)};
        for (int i18 = 0; i18 < 4; i18++) {
            i14 = Math.max(i14, iArr[i18]);
        }
        float top = paddingValues.getTop() * f11;
        return Math.max(Constraints.m5647getMinHeightimpl(j10), Math.max(i10, Math.max(i11, t7.a.M(MathHelpersKt.lerp(top, Math.max(top, i15 / 2.0f), f10) + i14 + (paddingValues.getBottom() * f11)))) + i17);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateWidth-DHJA7U0, reason: not valid java name */
    public static final int m1754calculateWidthDHJA7U0(int i10, int i11, int i12, int i13, int i14, int i15, int i16, float f10, long j10, float f11, PaddingValues paddingValues) {
        int i17 = i12 + i13;
        int iMax = Math.max(i14 + i17, Math.max(i16 + i17, MathHelpersKt.lerp(i15, 0, f10))) + i10 + i11;
        LayoutDirection layoutDirection = LayoutDirection.Ltr;
        return Math.max(iMax, Math.max(t7.a.M((i15 + (Dp.m5678constructorimpl(paddingValues.mo481calculateRightPaddingu2uoSUM(layoutDirection) + paddingValues.mo480calculateLeftPaddingu2uoSUM(layoutDirection)) * f11)) * f10), Constraints.m5648getMinWidthimpl(j10)));
    }

    public static final float getOutlinedTextFieldTopPadding() {
        return OutlinedTextFieldTopPadding;
    }

    /* JADX INFO: renamed from: outlineCutout-12SF9DM, reason: not valid java name */
    public static final Modifier m1755outlineCutout12SF9DM(Modifier modifier, long j10, PaddingValues paddingValues) {
        return DrawModifierKt.drawWithContent(modifier, new OutlinedTextFieldKt$outlineCutout$1(j10, paddingValues));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void place(Placeable.PlacementScope placementScope, int i10, int i11, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, Placeable placeable6, Placeable placeable7, Placeable placeable8, Placeable placeable9, float f10, boolean z, float f11, LayoutDirection layoutDirection, PaddingValues paddingValues) {
        Placeable placeable10;
        boolean z5;
        int i12;
        int i13;
        Placeable.PlacementScope.m4669place70tqf50$default(placementScope, placeable8, IntOffset.INSTANCE.m5812getZeronOccac(), 0.0f, 2, null);
        int iHeightOrZero = i10 - TextFieldImplKt.heightOrZero(placeable9);
        int iM = t7.a.M(paddingValues.getTop() * f11);
        int iM2 = t7.a.M(PaddingKt.calculateStartPadding(paddingValues, layoutDirection) * f11);
        float horizontalIconPadding = TextFieldImplKt.getHorizontalIconPadding() * f11;
        if (placeable != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, Alignment.INSTANCE.getCenterVertically().align(placeable.getHeight(), iHeightOrZero), 0.0f, 4, null);
        }
        if (placeable2 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i11 - placeable2.getWidth(), Alignment.INSTANCE.getCenterVertically().align(placeable2.getHeight(), iHeightOrZero), 0.0f, 4, null);
        }
        if (placeable6 != null) {
            placeable10 = placeable6;
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable10, t7.a.M(placeable == null ? 0.0f : (1 - f10) * (TextFieldImplKt.widthOrZero(placeable) - horizontalIconPadding)) + iM2, MathHelpersKt.lerp(z ? Alignment.INSTANCE.getCenterVertically().align(placeable6.getHeight(), iHeightOrZero) : iM, -(placeable6.getHeight() / 2), f10), 0.0f, 4, null);
        } else {
            placeable10 = placeable6;
        }
        if (placeable3 != null) {
            i13 = iM;
            i12 = iHeightOrZero;
            z5 = z;
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, TextFieldImplKt.widthOrZero(placeable), place$calculateVerticalPosition(z, iHeightOrZero, iM, placeable10, placeable3), 0.0f, 4, null);
        } else {
            z5 = z;
            i12 = iHeightOrZero;
            i13 = iM;
        }
        if (placeable4 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, (i11 - TextFieldImplKt.widthOrZero(placeable2)) - placeable4.getWidth(), place$calculateVerticalPosition(z5, i12, i13, placeable10, placeable4), 0.0f, 4, null);
        }
        int iWidthOrZero = TextFieldImplKt.widthOrZero(placeable3) + TextFieldImplKt.widthOrZero(placeable);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable5, iWidthOrZero, place$calculateVerticalPosition(z5, i12, i13, placeable10, placeable5), 0.0f, 4, null);
        if (placeable7 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable7, iWidthOrZero, place$calculateVerticalPosition(z5, i12, i13, placeable10, placeable7), 0.0f, 4, null);
        }
        if (placeable9 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable9, 0, i12, 0.0f, 4, null);
        }
    }

    private static final int place$calculateVerticalPosition(boolean z, int i10, int i11, Placeable placeable, Placeable placeable2) {
        if (z) {
            i11 = Alignment.INSTANCE.getCenterVertically().align(placeable2.getHeight(), i10);
        }
        return Math.max(i11, TextFieldImplKt.heightOrZero(placeable) / 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int substractConstraintSafely(int i10, int i11) {
        return i10 == Integer.MAX_VALUE ? i10 : i10 - i11;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0392  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x03c3  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x03c9  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x03d0  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x03d3  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x03f5  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x03f9  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0404  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0421  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0424  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0453  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0461  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x046e  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x04a3  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x04f8  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x0591  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x05bd  */
    /* JADX WARN: Removed duplicated region for block: B:359:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0125  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void OutlinedTextField(androidx.compose.ui.text.input.TextFieldValue r66, r7.l<? super androidx.compose.ui.text.input.TextFieldValue, x6.t0> r67, androidx.compose.ui.Modifier r68, boolean r69, boolean r70, androidx.compose.ui.text.TextStyle r71, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r72, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r73, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r74, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r75, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r76, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r77, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r78, boolean r79, androidx.compose.ui.text.input.VisualTransformation r80, androidx.compose.foundation.text.KeyboardOptions r81, androidx.compose.foundation.text.KeyboardActions r82, boolean r83, int r84, int r85, androidx.compose.foundation.interaction.MutableInteractionSource r86, androidx.compose.ui.graphics.Shape r87, androidx.compose.material3.TextFieldColors r88, androidx.compose.runtime.Composer r89, int r90, int r91, int r92, int r93) {
        /*
            Method dump skipped, instruction units count: 1499
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField(androidx.compose.ui.text.input.TextFieldValue, r7.l, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.material3.TextFieldColors, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x038f  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x03c1  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x03e7  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0491  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:327:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010e  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void OutlinedTextField(java.lang.String r39, r7.l r40, androidx.compose.ui.Modifier r41, boolean r42, boolean r43, androidx.compose.ui.text.TextStyle r44, r7.p r45, r7.p r46, r7.p r47, r7.p r48, r7.p r49, boolean r50, androidx.compose.ui.text.input.VisualTransformation r51, androidx.compose.foundation.text.KeyboardOptions r52, androidx.compose.foundation.text.KeyboardActions r53, boolean r54, int r55, int r56, androidx.compose.foundation.interaction.MutableInteractionSource r57, androidx.compose.ui.graphics.Shape r58, androidx.compose.material3.TextFieldColors r59, androidx.compose.runtime.Composer r60, int r61, int r62, int r63, int r64) {
        /*
            Method dump skipped, instruction units count: 1234
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField(java.lang.String, r7.l, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, r7.p, r7.p, r7.p, r7.p, r7.p, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.material3.TextFieldColors, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x038f  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x03c1  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x03e7  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0491  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:327:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010e  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void OutlinedTextField(androidx.compose.ui.text.input.TextFieldValue r39, r7.l r40, androidx.compose.ui.Modifier r41, boolean r42, boolean r43, androidx.compose.ui.text.TextStyle r44, r7.p r45, r7.p r46, r7.p r47, r7.p r48, r7.p r49, boolean r50, androidx.compose.ui.text.input.VisualTransformation r51, androidx.compose.foundation.text.KeyboardOptions r52, androidx.compose.foundation.text.KeyboardActions r53, boolean r54, int r55, int r56, androidx.compose.foundation.interaction.MutableInteractionSource r57, androidx.compose.ui.graphics.Shape r58, androidx.compose.material3.TextFieldColors r59, androidx.compose.runtime.Composer r60, int r61, int r62, int r63, int r64) {
        /*
            Method dump skipped, instruction units count: 1234
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.OutlinedTextFieldKt.OutlinedTextField(androidx.compose.ui.text.input.TextFieldValue, r7.l, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, r7.p, r7.p, r7.p, r7.p, r7.p, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.material3.TextFieldColors, androidx.compose.runtime.Composer, int, int, int, int):void");
    }
}
