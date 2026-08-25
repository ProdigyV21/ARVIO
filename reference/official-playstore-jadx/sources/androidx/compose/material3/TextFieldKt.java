package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
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
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
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
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aØ\u0002\u0010&\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u001f\u001a\u00020\u001d2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020$H\u0007¢\u0006\u0004\b&\u0010'\u001aØ\u0002\u0010&\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020(2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u001f\u001a\u00020\u001d2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020$H\u0007¢\u0006\u0004\b&\u0010)\u001aª\u0002\u0010&\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u001f\u001a\u00020\u001d2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020$H\u0007¢\u0006\u0004\b&\u0010*\u001aª\u0002\u0010&\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020(2\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0015\b\u0002\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0015\b\u0002\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\b\b\u0002\u0010\u0015\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\u001f\u001a\u00020\u001d2\b\b\u0002\u0010!\u001a\u00020 2\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020$H\u0007¢\u0006\u0004\b&\u0010+\u001aî\u0001\u00105\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0011\u0010,\u001a\r\u0012\u0004\u0012\u00020\u00030\f¢\u0006\u0002\b\r2\u0013\u0010\u000e\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0019\u0010\u000f\u001a\u0015\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002¢\u0006\u0002\b\r2\u0013\u0010-\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0013\u0010.\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0013\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0013\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u00100\u001a\u00020/2\u0011\u00101\u001a\r\u0012\u0004\u0012\u00020\u00030\f¢\u0006\u0002\b\r2\u0013\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0003\u0018\u00010\f¢\u0006\u0002\b\r2\u0006\u00104\u001a\u000203H\u0001¢\u0006\u0004\b5\u00106\u001a\u001b\u00108\u001a\u00020\u001d*\u00020\u001d2\u0006\u00107\u001a\u00020\u001dH\u0002¢\u0006\u0004\b8\u00109\u001aR\u0010E\u001a\u00020\u001d2\u0006\u0010:\u001a\u00020\u001d2\u0006\u0010;\u001a\u00020\u001d2\u0006\u0010<\u001a\u00020\u001d2\u0006\u0010=\u001a\u00020\u001d2\u0006\u0010>\u001a\u00020\u001d2\u0006\u0010?\u001a\u00020\u001d2\u0006\u0010@\u001a\u00020\u001d2\u0006\u0010B\u001a\u00020AH\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010D\u001ar\u0010Q\u001a\u00020\u001d2\u0006\u0010F\u001a\u00020\u001d2\u0006\u0010G\u001a\u00020\u001d2\u0006\u0010H\u001a\u00020\u001d2\u0006\u0010I\u001a\u00020\u001d2\u0006\u0010J\u001a\u00020\u001d2\u0006\u0010K\u001a\u00020\u001d2\u0006\u0010L\u001a\u00020\u001d2\u0006\u0010M\u001a\u00020\u001d2\u0006\u00100\u001a\u00020/2\u0006\u0010B\u001a\u00020A2\u0006\u0010N\u001a\u00020/2\u0006\u00104\u001a\u000203H\u0002ø\u0001\u0000¢\u0006\u0004\bO\u0010P\u001a¡\u0001\u0010a\u001a\u00020\u0003*\u00020R2\u0006\u0010S\u001a\u00020\u001d2\u0006\u0010T\u001a\u00020\u001d2\u0006\u0010V\u001a\u00020U2\b\u0010W\u001a\u0004\u0018\u00010U2\b\u0010X\u001a\u0004\u0018\u00010U2\b\u0010Y\u001a\u0004\u0018\u00010U2\b\u0010Z\u001a\u0004\u0018\u00010U2\b\u0010[\u001a\u0004\u0018\u00010U2\b\u0010\\\u001a\u0004\u0018\u00010U2\u0006\u0010]\u001a\u00020U2\b\u0010^\u001a\u0004\u0018\u00010U2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010_\u001a\u00020\u001d2\u0006\u0010`\u001a\u00020\u001d2\u0006\u00100\u001a\u00020/2\u0006\u0010N\u001a\u00020/H\u0002¢\u0006\u0004\ba\u0010b\u001a\u0087\u0001\u0010d\u001a\u00020\u0003*\u00020R2\u0006\u0010S\u001a\u00020\u001d2\u0006\u0010T\u001a\u00020\u001d2\u0006\u0010c\u001a\u00020U2\b\u0010X\u001a\u0004\u0018\u00010U2\b\u0010Y\u001a\u0004\u0018\u00010U2\b\u0010Z\u001a\u0004\u0018\u00010U2\b\u0010[\u001a\u0004\u0018\u00010U2\b\u0010\\\u001a\u0004\u0018\u00010U2\u0006\u0010]\u001a\u00020U2\b\u0010^\u001a\u0004\u0018\u00010U2\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010N\u001a\u00020/2\u0006\u00104\u001a\u000203H\u0002¢\u0006\u0004\bd\u0010e\u001a\u001b\u0010h\u001a\u00020\u0005*\u00020\u00052\u0006\u0010g\u001a\u00020fH\u0000¢\u0006\u0004\bh\u0010i\"\u001a\u0010k\u001a\u00020j8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bk\u0010l\u001a\u0004\bm\u0010n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006o"}, d2 = {"", "value", "Lkotlin/Function1;", "Lx6/t0;", "onValueChange", "Landroidx/compose/ui/Modifier;", "modifier", "", "enabled", "readOnly", "Landroidx/compose/ui/text/TextStyle;", "textStyle", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "label", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "isError", "Landroidx/compose/ui/text/input/VisualTransformation;", "visualTransformation", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardActions;", "keyboardActions", "singleLine", "", "maxLines", "minLines", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Landroidx/compose/ui/graphics/Shape;", "shape", "Landroidx/compose/material3/TextFieldColors;", "colors", TextFieldImplKt.TextFieldId, "(Ljava/lang/String;Lr7/l;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "Landroidx/compose/ui/text/input/TextFieldValue;", "(Landroidx/compose/ui/text/input/TextFieldValue;Lr7/l;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "(Ljava/lang/String;Lr7/l;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "(Landroidx/compose/ui/text/input/TextFieldValue;Lr7/l;Landroidx/compose/ui/Modifier;ZZLandroidx/compose/ui/text/TextStyle;Lr7/p;Lr7/p;Lr7/p;Lr7/p;Lr7/p;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;ZIILandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/TextFieldColors;Landroidx/compose/runtime/Composer;IIII)V", "textField", "leading", "trailing", "", "animationProgress", TtmlNode.RUBY_CONTAINER, "supporting", "Landroidx/compose/foundation/layout/PaddingValues;", "paddingValues", "TextFieldLayout", "(Landroidx/compose/ui/Modifier;Lr7/p;Lr7/p;Lr7/q;Lr7/p;Lr7/p;Lr7/p;Lr7/p;ZFLr7/p;Lr7/p;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;II)V", "from", "substractConstraintSafely", "(II)I", "leadingWidth", "trailingWidth", "prefixWidth", "suffixWidth", "textFieldWidth", "labelWidth", "placeholderWidth", "Landroidx/compose/ui/unit/Constraints;", "constraints", "calculateWidth-yeHjK3Y", "(IIIIIIIJ)I", "calculateWidth", "textFieldHeight", "labelHeight", "leadingHeight", "trailingHeight", "prefixHeight", "suffixHeight", "placeholderHeight", "supportingHeight", "density", "calculateHeight-mKXJcVc", "(IIIIIIIIFJFLandroidx/compose/foundation/layout/PaddingValues;)I", "calculateHeight", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "width", "totalHeight", "Landroidx/compose/ui/layout/Placeable;", "textfieldPlaceable", "labelPlaceable", "placeholderPlaceable", "leadingPlaceable", "trailingPlaceable", "prefixPlaceable", "suffixPlaceable", "containerPlaceable", "supportingPlaceable", "labelEndPosition", "textPosition", "placeWithLabel", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;IILandroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;ZIIFF)V", "textPlaceable", "placeWithoutLabel", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;IILandroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;ZFLandroidx/compose/foundation/layout/PaddingValues;)V", "Landroidx/compose/foundation/BorderStroke;", "indicatorBorder", "drawIndicatorLine", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/BorderStroke;)Landroidx/compose/ui/Modifier;", "Landroidx/compose/ui/unit/Dp;", "TextFieldWithLabelVerticalPadding", "F", "getTextFieldWithLabelVerticalPadding", "()F", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextFieldKt {
    private static final float TextFieldWithLabelVerticalPadding = Dp.m5678constructorimpl(8);

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$10, reason: invalid class name */
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
            TextFieldKt.TextField(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$readOnly, this.$textStyle, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$supportingText, this.$isError, this.$visualTransformation, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$minLines, this.$interactionSource, this.$shape, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed2), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$2, reason: invalid class name */
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

        /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0002H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "innerTextField", "invoke", "(Lr7/p;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements q<p<? super Composer, ? super Integer, ? extends t0>, Composer, Integer, t0> {
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(String str, boolean z, boolean z5, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, boolean z10, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, Shape shape, TextFieldColors textFieldColors) {
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
                this.$shape = shape;
                this.$colors = textFieldColors;
            }

            @Override // r7.q
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                invoke((p<? super Composer, ? super Integer, t0>) obj, (Composer) obj2, ((Number) obj3).intValue());
                return t0.f22605a;
            }

            public final void invoke(p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
                Composer composer2;
                int i11;
                if ((i10 & 6) == 0) {
                    composer2 = composer;
                    i11 = i10 | (composer2.changedInstance(pVar) ? 4 : 2);
                } else {
                    composer2 = composer;
                    i11 = i10;
                }
                if ((i11 & 19) == 18 && composer2.getSkipping()) {
                    composer2.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-288211827, i11, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:222)");
                }
                TextFieldDefaults.INSTANCE.DecorationBox(this.$value, pVar, this.$enabled, this.$singleLine, this.$visualTransformation, this.$interactionSource, this.$isError, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$shape, this.$colors, null, null, composer, (i11 << 3) & 112, 100663296, 196608);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Modifier modifier, boolean z, TextFieldColors textFieldColors, String str, l<? super String, t0> lVar, boolean z5, boolean z10, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z11, int i10, int i11, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, Shape shape) {
            super(2);
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
            this.$label = pVar;
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
                ComposerKt.traceEventStart(1859145987, i10, -1, "androidx.compose.material3.TextField.<anonymous> (TextField.kt:200)");
            }
            Modifier modifier = this.$modifier;
            boolean z = this.$isError;
            Strings.Companion companion = Strings.INSTANCE;
            Modifier modifierDefaultErrorSemantics = TextFieldImplKt.defaultErrorSemantics(modifier, z, Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(androidx.compose.ui.R.string.default_error_message), composer, 0));
            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
            Modifier modifierM562defaultMinSizeVpY3zN4 = SizeKt.m562defaultMinSizeVpY3zN4(modifierDefaultErrorSemantics, textFieldDefaults.m2129getMinWidthD9Ej5fM(), textFieldDefaults.m2128getMinHeightD9Ej5fM());
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
            BasicTextFieldKt.BasicTextField(str, lVar, modifierM562defaultMinSizeVpY3zN4, z5, z10, textStyle, keyboardOptions, keyboardActions, z11, i11, i12, visualTransformation, (l<? super TextLayoutResult, t0>) null, mutableInteractionSource, solidColor, ComposableLambdaKt.composableLambda(composer, -288211827, true, new AnonymousClass1(str, z5, z11, visualTransformation, mutableInteractionSource, this.$isError, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$shape, this.$colors)), composer, 0, 196608, 4096);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$3, reason: invalid class name */
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
            TextFieldKt.TextField(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$readOnly, this.$textStyle, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$isError, this.$visualTransformation, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$minLines, this.$interactionSource, this.$shape, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed2), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$5, reason: invalid class name */
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

        /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$5$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0002H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "innerTextField", "invoke", "(Lr7/p;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements q<p<? super Composer, ? super Integer, ? extends t0>, Composer, Integer, t0> {
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

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(TextFieldValue textFieldValue, boolean z, boolean z5, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, boolean z10, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, Shape shape, TextFieldColors textFieldColors) {
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
                this.$shape = shape;
                this.$colors = textFieldColors;
            }

            @Override // r7.q
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                invoke((p<? super Composer, ? super Integer, t0>) obj, (Composer) obj2, ((Number) obj3).intValue());
                return t0.f22605a;
            }

            public final void invoke(p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
                Composer composer2;
                int i11;
                if ((i10 & 6) == 0) {
                    composer2 = composer;
                    i11 = i10 | (composer2.changedInstance(pVar) ? 4 : 2);
                } else {
                    composer2 = composer;
                    i11 = i10;
                }
                if ((i11 & 19) == 18 && composer2.getSkipping()) {
                    composer2.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1751957978, i11, -1, "androidx.compose.material3.TextField.<anonymous>.<anonymous> (TextField.kt:368)");
                }
                TextFieldDefaults.INSTANCE.DecorationBox(this.$value.getText(), pVar, this.$enabled, this.$singleLine, this.$visualTransformation, this.$interactionSource, this.$isError, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$shape, this.$colors, null, null, composer, (i11 << 3) & 112, 100663296, 196608);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass5(Modifier modifier, boolean z, TextFieldColors textFieldColors, TextFieldValue textFieldValue, l<? super TextFieldValue, t0> lVar, boolean z5, boolean z10, TextStyle textStyle, KeyboardOptions keyboardOptions, KeyboardActions keyboardActions, boolean z11, int i10, int i11, VisualTransformation visualTransformation, MutableInteractionSource mutableInteractionSource, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, p<? super Composer, ? super Integer, t0> pVar7, Shape shape) {
            super(2);
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
            this.$label = pVar;
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
                ComposerKt.traceEventStart(-1163788208, i10, -1, "androidx.compose.material3.TextField.<anonymous> (TextField.kt:346)");
            }
            Modifier modifier = this.$modifier;
            boolean z = this.$isError;
            Strings.Companion companion = Strings.INSTANCE;
            Modifier modifierDefaultErrorSemantics = TextFieldImplKt.defaultErrorSemantics(modifier, z, Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(androidx.compose.ui.R.string.default_error_message), composer, 0));
            TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
            Modifier modifierM562defaultMinSizeVpY3zN4 = SizeKt.m562defaultMinSizeVpY3zN4(modifierDefaultErrorSemantics, textFieldDefaults.m2129getMinWidthD9Ej5fM(), textFieldDefaults.m2128getMinHeightD9Ej5fM());
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
            BasicTextFieldKt.BasicTextField(textFieldValue, lVar, modifierM562defaultMinSizeVpY3zN4, z5, z10, textStyle, keyboardOptions, keyboardActions, z11, i11, i12, visualTransformation, (l<? super TextLayoutResult, t0>) null, mutableInteractionSource, solidColor, ComposableLambdaKt.composableLambda(composer, 1751957978, true, new AnonymousClass1(textFieldValue, z5, z11, visualTransformation, mutableInteractionSource, this.$isError, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$shape, this.$colors)), composer, 0, 196608, 4096);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$6, reason: invalid class name */
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
            TextFieldKt.TextField(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$readOnly, this.$textStyle, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$prefix, this.$suffix, this.$supportingText, this.$isError, this.$visualTransformation, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$minLines, this.$interactionSource, this.$shape, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed2), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextField$8, reason: invalid class name */
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
            TextFieldKt.TextField(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$readOnly, this.$textStyle, this.$label, this.$placeholder, this.$leadingIcon, this.$trailingIcon, this.$supportingText, this.$isError, this.$visualTransformation, this.$keyboardOptions, this.$keyboardActions, this.$singleLine, this.$maxLines, this.$minLines, this.$interactionSource, this.$shape, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed2), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$TextFieldLayout$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05662 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ float $animationProgress;
        final /* synthetic */ p<Composer, Integer, t0> $container;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leading;
        final /* synthetic */ Modifier $modifier;
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
        public C05662(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, q<? super Modifier, ? super Composer, ? super Integer, t0> qVar, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, boolean z, float f10, p<? super Composer, ? super Integer, t0> pVar7, p<? super Composer, ? super Integer, t0> pVar8, PaddingValues paddingValues, int i10, int i11) {
            super(2);
            this.$modifier = modifier;
            this.$textField = pVar;
            this.$label = pVar2;
            this.$placeholder = qVar;
            this.$leading = pVar3;
            this.$trailing = pVar4;
            this.$prefix = pVar5;
            this.$suffix = pVar6;
            this.$singleLine = z;
            this.$animationProgress = f10;
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
            TextFieldKt.TextFieldLayout(this.$modifier, this.$textField, this.$label, this.$placeholder, this.$leading, this.$trailing, this.$prefix, this.$suffix, this.$singleLine, this.$animationProgress, this.$container, this.$supporting, this.$paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TextFieldKt$drawIndicatorLine$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<ContentDrawScope, t0> {
        final /* synthetic */ BorderStroke $indicatorBorder;
        final /* synthetic */ float $strokeWidthDp;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(float f10, BorderStroke borderStroke) {
            super(1);
            this.$strokeWidthDp = f10;
            this.$indicatorBorder = borderStroke;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ContentDrawScope) obj);
            return t0.f22605a;
        }

        public final void invoke(ContentDrawScope contentDrawScope) {
            contentDrawScope.drawContent();
            if (Dp.m5683equalsimpl0(this.$strokeWidthDp, Dp.INSTANCE.m5696getHairlineD9Ej5fM())) {
                return;
            }
            float density = contentDrawScope.getDensity() * this.$strokeWidthDp;
            float fM3302getHeightimpl = Size.m3302getHeightimpl(contentDrawScope.mo3916getSizeNHjbRc()) - (density / 2);
            androidx.compose.ui.graphics.drawscope.c.B(contentDrawScope, this.$indicatorBorder.getBrush(), OffsetKt.Offset(0.0f, fM3302getHeightimpl), OffsetKt.Offset(Size.m3305getWidthimpl(contentDrawScope.mo3916getSizeNHjbRc()), fM3302getHeightimpl), density, 0, null, 0.0f, null, 0, 496, null);
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
    public static final void TextField(java.lang.String r66, r7.l<? super java.lang.String, x6.t0> r67, androidx.compose.ui.Modifier r68, boolean r69, boolean r70, androidx.compose.ui.text.TextStyle r71, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r72, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r73, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r74, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r75, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r76, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r77, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r78, boolean r79, androidx.compose.ui.text.input.VisualTransformation r80, androidx.compose.foundation.text.KeyboardOptions r81, androidx.compose.foundation.text.KeyboardActions r82, boolean r83, int r84, int r85, androidx.compose.foundation.interaction.MutableInteractionSource r86, androidx.compose.ui.graphics.Shape r87, androidx.compose.material3.TextFieldColors r88, androidx.compose.runtime.Composer r89, int r90, int r91, int r92, int r93) {
        /*
            Method dump skipped, instruction units count: 1498
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldKt.TextField(java.lang.String, r7.l, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.material3.TextFieldColors, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    public static final void TextFieldLayout(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, q<? super Modifier, ? super Composer, ? super Integer, t0> qVar, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, p<? super Composer, ? super Integer, t0> pVar5, p<? super Composer, ? super Integer, t0> pVar6, boolean z, float f10, p<? super Composer, ? super Integer, t0> pVar7, p<? super Composer, ? super Integer, t0> pVar8, PaddingValues paddingValues, Composer composer, int i10, int i11) {
        int i12;
        int i13;
        float f11;
        q<? super Modifier, ? super Composer, ? super Integer, t0> qVar2;
        p<? super Composer, ? super Integer, t0> pVar9;
        p<? super Composer, ? super Integer, t0> pVar10;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1830307184);
        if ((i10 & 6) == 0) {
            i12 = i10 | (composerStartRestartGroup.changed(modifier) ? 4 : 2);
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar2) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(qVar) ? 2048 : 1024;
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
            i13 = i11 | (composerStartRestartGroup.changedInstance(pVar7) ? 4 : 2);
        } else {
            i13 = i11;
        }
        if ((i11 & 48) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(pVar8) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changed(paddingValues) ? 256 : 128;
        }
        int i15 = i13;
        if ((i14 & 306783379) == 306783378 && (i15 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            qVar2 = qVar;
            pVar9 = pVar;
            pVar10 = pVar8;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1830307184, i14, i15, "androidx.compose.material3.TextFieldLayout (TextField.kt:516)");
            }
            composerStartRestartGroup.startReplaceableGroup(243139239);
            boolean z5 = ((1879048192 & i14) == 536870912) | ((234881024 & i14) == 67108864) | ((i15 & 896) == 256);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new TextFieldMeasurePolicy(z, f10, paddingValues);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            TextFieldMeasurePolicy textFieldMeasurePolicy = (TextFieldMeasurePolicy) objRememberedValue;
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
            Updater.m2998setimpl(composerM2991constructorimpl, textFieldMeasurePolicy, companion.getSetMeasurePolicy());
            Updater.m2998setimpl(composerM2991constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            p<ComposeUiNode, Integer, t0> setCompositeKeyHash = companion.getSetCompositeKeyHash();
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, setCompositeKeyHash);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            pVar7.invoke(composerStartRestartGroup, Integer.valueOf(i15 & 14));
            composerStartRestartGroup.startReplaceableGroup(-95271705);
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
            composerStartRestartGroup.startReplaceableGroup(-95271370);
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
            float fCalculateStartPadding = PaddingKt.calculateStartPadding(paddingValues, layoutDirection);
            float fCalculateEndPadding = PaddingKt.calculateEndPadding(paddingValues, layoutDirection);
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
            composerStartRestartGroup.startReplaceableGroup(-95270430);
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
            composerStartRestartGroup.startReplaceableGroup(-95270031);
            if (pVar6 != null) {
                float f12 = fCalculateEndPadding;
                Modifier modifierM533paddingqDBjuR0$default2 = PaddingKt.m533paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m566heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.SuffixId), TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null), TextFieldImplKt.getPrefixSuffixTextPadding(), 0.0f, f12, 0.0f, 10, null);
                f11 = f12;
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
            composerStartRestartGroup.startReplaceableGroup(-95269633);
            if (pVar2 != null) {
                Modifier modifierM533paddingqDBjuR0$default3 = PaddingKt.m533paddingqDBjuR0$default(SizeKt.wrapContentHeight$default(SizeKt.m566heightInVpY3zN4$default(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.LabelId), DpKt.m5721lerpMdfbLM(TextFieldImplKt.getMinTextLineHeight(), TextFieldImplKt.getMinFocusedLabelLineHeight(), f10), 0.0f, 2, null), null, false, 3, null), fM5678constructorimpl3, 0.0f, f11, 0.0f, 10, null);
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyD3 = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
                int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor6 = companion.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf6 = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default3);
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
                pVar2.invoke(composerStartRestartGroup, Integer.valueOf((i14 >> 6) & 14));
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Modifier modifierWrapContentHeight$default = SizeKt.wrapContentHeight$default(SizeKt.m566heightInVpY3zN4$default(companion2, TextFieldImplKt.getMinTextLineHeight(), 0.0f, 2, null), null, false, 3, null);
            if (pVar5 != null) {
                fM5678constructorimpl3 = Dp.m5678constructorimpl(0);
            }
            Modifier modifierM533paddingqDBjuR0$default4 = PaddingKt.m533paddingqDBjuR0$default(modifierWrapContentHeight$default, fM5678constructorimpl3, 0.0f, pVar6 == null ? f11 : Dp.m5678constructorimpl(0), 0.0f, 10, null);
            composerStartRestartGroup.startReplaceableGroup(-95268909);
            if (qVar != null) {
                qVar2 = qVar;
                qVar2.invoke(LayoutIdKt.layoutId(companion2, TextFieldImplKt.PlaceholderId).then(modifierM533paddingqDBjuR0$default4), composerStartRestartGroup, Integer.valueOf((i14 >> 6) & 112));
            } else {
                qVar2 = qVar;
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierThen3 = LayoutIdKt.layoutId(companion2, TextFieldImplKt.TextFieldId).then(modifierM533paddingqDBjuR0$default4);
            composerStartRestartGroup.startReplaceableGroup(733328855);
            Alignment.Companion companion3 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyD4 = a0.c.d(companion3, true, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor7 = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf7 = LayoutKt.modifierMaterializerOf(modifierThen3);
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
            BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
            pVar9 = pVar;
            pVar9.invoke(composerStartRestartGroup, Integer.valueOf((i14 >> 3) & 14));
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(243142996);
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
                pVar10.invoke(composerStartRestartGroup, Integer.valueOf((i15 >> 3) & 14));
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
            scopeUpdateScopeEndRestartGroup.updateScope(new C05662(modifier, pVar9, pVar2, qVar2, pVar3, pVar4, pVar5, pVar6, z, f10, pVar7, pVar10, paddingValues, i10, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateHeight-mKXJcVc, reason: not valid java name */
    public static final int m2149calculateHeightmKXJcVc(int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, float f10, long j10, float f11, PaddingValues paddingValues) {
        boolean z = i11 > 0;
        float fM5678constructorimpl = Dp.m5678constructorimpl(paddingValues.getBottom() + paddingValues.getTop()) * f11;
        if (z) {
            fM5678constructorimpl = MathHelpersKt.lerp(Dp.m5678constructorimpl(TextFieldImplKt.getTextFieldPadding() * 2) * f11, fM5678constructorimpl, f10);
        }
        int[] iArr = {i16, i14, i15, MathHelpersKt.lerp(i11, 0, f10)};
        for (int i18 = 0; i18 < 4; i18++) {
            i10 = Math.max(i10, iArr[i18]);
        }
        return Math.max(Constraints.m5647getMinHeightimpl(j10), Math.max(i12, Math.max(i13, t7.a.M(fM5678constructorimpl + MathHelpersKt.lerp(0, i11, f10) + i10))) + i17);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateWidth-yeHjK3Y, reason: not valid java name */
    public static final int m2150calculateWidthyeHjK3Y(int i10, int i11, int i12, int i13, int i14, int i15, int i16, long j10) {
        int i17 = i12 + i13;
        return Math.max(Math.max(i14 + i17, Math.max(i16 + i17, i15)) + i10 + i11, Constraints.m5648getMinWidthimpl(j10));
    }

    public static final Modifier drawIndicatorLine(Modifier modifier, BorderStroke borderStroke) {
        return DrawModifierKt.drawWithContent(modifier, new AnonymousClass1(borderStroke.getWidth(), borderStroke));
    }

    public static final float getTextFieldWithLabelVerticalPadding() {
        return TextFieldWithLabelVerticalPadding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeWithLabel(Placeable.PlacementScope placementScope, int i10, int i11, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, Placeable placeable6, Placeable placeable7, Placeable placeable8, Placeable placeable9, boolean z, int i12, int i13, float f10, float f11) {
        Placeable.PlacementScope.m4669place70tqf50$default(placementScope, placeable8, IntOffset.INSTANCE.m5812getZeronOccac(), 0.0f, 2, null);
        int iHeightOrZero = i11 - TextFieldImplKt.heightOrZero(placeable9);
        if (placeable4 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, 0, Alignment.INSTANCE.getCenterVertically().align(placeable4.getHeight(), iHeightOrZero), 0.0f, 4, null);
        }
        if (placeable5 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable5, i10 - placeable5.getWidth(), Alignment.INSTANCE.getCenterVertically().align(placeable5.getHeight(), iHeightOrZero), 0.0f, 4, null);
        }
        if (placeable2 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, TextFieldImplKt.widthOrZero(placeable4), (z ? Alignment.INSTANCE.getCenterVertically().align(placeable2.getHeight(), iHeightOrZero) : t7.a.M(TextFieldImplKt.getTextFieldPadding() * f11)) - t7.a.M((r1 - i12) * f10), 0.0f, 4, null);
        }
        if (placeable6 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable6, TextFieldImplKt.widthOrZero(placeable4), i13, 0.0f, 4, null);
        }
        if (placeable7 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable7, (i10 - TextFieldImplKt.widthOrZero(placeable5)) - placeable7.getWidth(), i13, 0.0f, 4, null);
        }
        int iWidthOrZero = TextFieldImplKt.widthOrZero(placeable6) + TextFieldImplKt.widthOrZero(placeable4);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, iWidthOrZero, i13, 0.0f, 4, null);
        if (placeable3 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, iWidthOrZero, i13, 0.0f, 4, null);
        }
        if (placeable9 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable9, 0, iHeightOrZero, 0.0f, 4, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeWithoutLabel(Placeable.PlacementScope placementScope, int i10, int i11, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, Placeable placeable6, Placeable placeable7, Placeable placeable8, boolean z, float f10, PaddingValues paddingValues) {
        Placeable.PlacementScope.m4669place70tqf50$default(placementScope, placeable7, IntOffset.INSTANCE.m5812getZeronOccac(), 0.0f, 2, null);
        int iHeightOrZero = i11 - TextFieldImplKt.heightOrZero(placeable8);
        int iM = t7.a.M(paddingValues.getTop() * f10);
        if (placeable3 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, 0, Alignment.INSTANCE.getCenterVertically().align(placeable3.getHeight(), iHeightOrZero), 0.0f, 4, null);
        }
        if (placeable4 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable4, i10 - placeable4.getWidth(), Alignment.INSTANCE.getCenterVertically().align(placeable4.getHeight(), iHeightOrZero), 0.0f, 4, null);
        }
        if (placeable5 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable5, TextFieldImplKt.widthOrZero(placeable3), placeWithoutLabel$calculateVerticalPosition(z, iHeightOrZero, iM, placeable5), 0.0f, 4, null);
        }
        if (placeable6 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable6, (i10 - TextFieldImplKt.widthOrZero(placeable4)) - placeable6.getWidth(), placeWithoutLabel$calculateVerticalPosition(z, iHeightOrZero, iM, placeable6), 0.0f, 4, null);
        }
        int iWidthOrZero = TextFieldImplKt.widthOrZero(placeable5) + TextFieldImplKt.widthOrZero(placeable3);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, iWidthOrZero, placeWithoutLabel$calculateVerticalPosition(z, iHeightOrZero, iM, placeable), 0.0f, 4, null);
        if (placeable2 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, iWidthOrZero, placeWithoutLabel$calculateVerticalPosition(z, iHeightOrZero, iM, placeable2), 0.0f, 4, null);
        }
        if (placeable8 != null) {
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable8, 0, iHeightOrZero, 0.0f, 4, null);
        }
    }

    private static final int placeWithoutLabel$calculateVerticalPosition(boolean z, int i10, int i11, Placeable placeable) {
        return z ? Alignment.INSTANCE.getCenterVertically().align(placeable.getHeight(), i10) : i11;
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
    public static final void TextField(androidx.compose.ui.text.input.TextFieldValue r66, r7.l<? super androidx.compose.ui.text.input.TextFieldValue, x6.t0> r67, androidx.compose.ui.Modifier r68, boolean r69, boolean r70, androidx.compose.ui.text.TextStyle r71, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r72, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r73, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r74, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r75, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r76, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r77, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r78, boolean r79, androidx.compose.ui.text.input.VisualTransformation r80, androidx.compose.foundation.text.KeyboardOptions r81, androidx.compose.foundation.text.KeyboardActions r82, boolean r83, int r84, int r85, androidx.compose.foundation.interaction.MutableInteractionSource r86, androidx.compose.ui.graphics.Shape r87, androidx.compose.material3.TextFieldColors r88, androidx.compose.runtime.Composer r89, int r90, int r91, int r92, int r93) {
        /*
            Method dump skipped, instruction units count: 1498
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldKt.TextField(androidx.compose.ui.text.input.TextFieldValue, r7.l, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, r7.p, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.material3.TextFieldColors, androidx.compose.runtime.Composer, int, int, int, int):void");
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
    public static final /* synthetic */ void TextField(java.lang.String r39, r7.l r40, androidx.compose.ui.Modifier r41, boolean r42, boolean r43, androidx.compose.ui.text.TextStyle r44, r7.p r45, r7.p r46, r7.p r47, r7.p r48, r7.p r49, boolean r50, androidx.compose.ui.text.input.VisualTransformation r51, androidx.compose.foundation.text.KeyboardOptions r52, androidx.compose.foundation.text.KeyboardActions r53, boolean r54, int r55, int r56, androidx.compose.foundation.interaction.MutableInteractionSource r57, androidx.compose.ui.graphics.Shape r58, androidx.compose.material3.TextFieldColors r59, androidx.compose.runtime.Composer r60, int r61, int r62, int r63, int r64) {
        /*
            Method dump skipped, instruction units count: 1234
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldKt.TextField(java.lang.String, r7.l, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, r7.p, r7.p, r7.p, r7.p, r7.p, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.material3.TextFieldColors, androidx.compose.runtime.Composer, int, int, int, int):void");
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
    public static final /* synthetic */ void TextField(androidx.compose.ui.text.input.TextFieldValue r39, r7.l r40, androidx.compose.ui.Modifier r41, boolean r42, boolean r43, androidx.compose.ui.text.TextStyle r44, r7.p r45, r7.p r46, r7.p r47, r7.p r48, r7.p r49, boolean r50, androidx.compose.ui.text.input.VisualTransformation r51, androidx.compose.foundation.text.KeyboardOptions r52, androidx.compose.foundation.text.KeyboardActions r53, boolean r54, int r55, int r56, androidx.compose.foundation.interaction.MutableInteractionSource r57, androidx.compose.ui.graphics.Shape r58, androidx.compose.material3.TextFieldColors r59, androidx.compose.runtime.Composer r60, int r61, int r62, int r63, int r64) {
        /*
            Method dump skipped, instruction units count: 1234
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldKt.TextField(androidx.compose.ui.text.input.TextFieldValue, r7.l, androidx.compose.ui.Modifier, boolean, boolean, androidx.compose.ui.text.TextStyle, r7.p, r7.p, r7.p, r7.p, r7.p, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, boolean, int, int, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.ui.graphics.Shape, androidx.compose.material3.TextFieldColors, androidx.compose.runtime.Composer, int, int, int, int):void");
    }
}
