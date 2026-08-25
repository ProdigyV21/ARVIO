package androidx.compose.material3;

import androidx.compose.foundation.ProgressSemanticsKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.material3.tokens.SliderTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import f7.j;
import io.ktor.http.LinkHeader;
import ka.k0;
import ka.l0;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0014\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\u001a\u007f\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00000\t2\b\b\u0003\u0010\f\u001a\u00020\u000b2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u0013\u0010\u0014\u001aµ\u0001\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0003\u0010\f\u001a\u00020\u000b2\u0019\b\u0002\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00162\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00162\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00000\tH\u0007¢\u0006\u0004\b\u0013\u0010\u0019\u001au\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u00152\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\u0019\b\u0002\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00162\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0004\b\u0013\u0010\u001b\u001a\u0081\u0001\u0010\u001c\u001a\u00020\u00032\f\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00000\t2\u0018\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00000\t\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00000\t2\b\b\u0003\u0010\f\u001a\u00020\u000b2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u001c\u0010\u001d\u001aæ\u0001\u0010\u001c\u001a\u00020\u00032\f\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00000\t2\u0018\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00000\t\u0012\u0004\u0012\u00020\u00030\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00000\t2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u001e\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00112\u0019\b\u0002\u0010!\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00162\u0019\b\u0002\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00162\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00162\b\b\u0003\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u001c\u0010#\u001a\u009a\u0001\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020 2\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u001e\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00112\u0019\b\u0002\u0010!\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00162\u0019\b\u0002\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00162\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0016H\u0007¢\u0006\u0004\b\u001c\u0010$\u001aa\u0010%\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00112\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00162\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0016H\u0003¢\u0006\u0004\b%\u0010&\u001a\u0082\u0001\u0010'\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020 2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u00112\u0017\u0010!\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00162\u0017\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u00162\u0017\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0002\b\u0016H\u0003¢\u0006\u0004\b'\u0010(\u001a/\u0010.\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u00002\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u0000H\u0002¢\u0006\u0004\b.\u0010/\u001a5\u00109\u001a\u0010\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u0000\u0018\u000105*\u0002002\u0006\u00102\u001a\u0002012\u0006\u00104\u001a\u000203H\u0082@ø\u0001\u0000¢\u0006\u0004\b7\u00108\u001a\u0017\u0010:\u001a\u00020*2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b:\u0010;\u001a7\u0010A\u001a\u00020\u00002\u0006\u0010<\u001a\u00020\u00002\u0006\u0010=\u001a\u00020\u00002\u0006\u0010>\u001a\u00020\u00002\u0006\u0010?\u001a\u00020\u00002\u0006\u0010@\u001a\u00020\u0000H\u0002¢\u0006\u0004\bA\u0010B\u001a:\u0010A\u001a\u00020C2\u0006\u0010<\u001a\u00020\u00002\u0006\u0010=\u001a\u00020\u00002\u0006\u0010D\u001a\u00020C2\u0006\u0010?\u001a\u00020\u00002\u0006\u0010@\u001a\u00020\u0000H\u0002ø\u0001\u0000¢\u0006\u0004\bE\u0010F\u001a'\u0010J\u001a\u00020\u00002\u0006\u0010G\u001a\u00020\u00002\u0006\u0010H\u001a\u00020\u00002\u0006\u0010I\u001a\u00020\u0000H\u0002¢\u0006\u0004\bJ\u0010K\u001a#\u0010L\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\bL\u0010M\u001a#\u0010N\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u001a\u001a\u00020 2\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\bN\u0010O\u001a#\u0010P\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u001a\u001a\u00020 2\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\bP\u0010O\u001a+\u0010Q\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u0007H\u0003¢\u0006\u0004\bQ\u0010R\u001a3\u0010S\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u001a\u001a\u00020 2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\u0007H\u0003¢\u0006\u0004\bS\u0010T\u001a\u001f\u0010W\u001a\u00020C2\u0006\u0010U\u001a\u00020\u00002\u0006\u0010V\u001a\u00020\u0000H\u0001¢\u0006\u0004\bW\u0010X\u001a\u001d\u0010W\u001a\u00020C2\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00000\tH\u0001¢\u0006\u0004\bW\u0010Z\"\u001a\u0010\\\u001a\u00020[8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\\\u0010]\u001a\u0004\b^\u0010_\"\u0014\u0010`\u001a\u00020[8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b`\u0010]\"\u0014\u0010b\u001a\u00020a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bb\u0010c\"\u0014\u0010d\u001a\u00020[8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bd\u0010]\"\u0014\u0010e\u001a\u00020[8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\be\u0010]\"\u0014\u0010f\u001a\u00020[8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bf\u0010]\"\u001a\u0010g\u001a\u00020[8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bg\u0010]\u001a\u0004\bh\u0010_\"\u001e\u0010m\u001a\u00020\u0007*\u00020C8@X\u0081\u0004¢\u0006\f\u0012\u0004\bk\u0010l\u001a\u0004\bi\u0010j\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006n"}, d2 = {"", "value", "Lkotlin/Function1;", "Lx6/t0;", "onValueChange", "Landroidx/compose/ui/Modifier;", "modifier", "", "enabled", "Lx7/e;", "valueRange", "", "steps", "Lkotlin/Function0;", "onValueChangeFinished", "Landroidx/compose/material3/SliderColors;", "colors", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Slider", "(FLr7/l;Landroidx/compose/ui/Modifier;ZLx7/e;ILr7/a;Landroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/material3/SliderState;", "Landroidx/compose/runtime/Composable;", "thumb", "track", "(FLr7/l;Landroidx/compose/ui/Modifier;ZLr7/a;Landroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;ILr7/q;Lr7/q;Lx7/e;Landroidx/compose/runtime/Composer;III)V", "state", "(Landroidx/compose/material3/SliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/q;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "RangeSlider", "(Lx7/e;Lr7/l;Landroidx/compose/ui/Modifier;ZLx7/e;ILr7/a;Landroidx/compose/material3/SliderColors;Landroidx/compose/runtime/Composer;II)V", "startInteractionSource", "endInteractionSource", "Landroidx/compose/material3/RangeSliderState;", "startThumb", "endThumb", "(Lx7/e;Lr7/l;Landroidx/compose/ui/Modifier;ZLx7/e;Lr7/a;Landroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/q;Lr7/q;Lr7/q;ILandroidx/compose/runtime/Composer;III)V", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SliderColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/q;Lr7/q;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "SliderImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderState;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/q;Lr7/q;Landroidx/compose/runtime/Composer;I)V", "RangeSliderImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/RangeSliderState;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/q;Lr7/q;Lr7/q;Landroidx/compose/runtime/Composer;I)V", "current", "", "tickFractions", "minPx", "maxPx", "snapValueToTick", "(F[FFF)F", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Landroidx/compose/ui/input/pointer/PointerId;", TtmlNode.ATTR_ID, "Landroidx/compose/ui/input/pointer/PointerType;", LinkHeader.Parameters.Type, "Lx6/x;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "awaitSlop-8vUncbI", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JILd7/d;)Ljava/lang/Object;", "awaitSlop", "stepsToTickFractions", "(I)[F", "a1", "b1", "x1", "a2", "b2", "scale", "(FFFFF)F", "Landroidx/compose/material3/SliderRange;", "x", "scale-ziovWd0", "(FFJFF)J", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "pos", "calcFraction", "(FFF)F", "sliderSemantics", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderState;Z)Landroidx/compose/ui/Modifier;", "rangeSliderStartThumbSemantics", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/RangeSliderState;Z)Landroidx/compose/ui/Modifier;", "rangeSliderEndThumbSemantics", "sliderTapModifier", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SliderState;Landroidx/compose/foundation/interaction/MutableInteractionSource;Z)Landroidx/compose/ui/Modifier;", "rangeSliderPressDragModifier", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/RangeSliderState;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;Z)Landroidx/compose/ui/Modifier;", TtmlNode.START, "endInclusive", "SliderRange", "(FF)J", "range", "(Lx7/e;)J", "Landroidx/compose/ui/unit/Dp;", "ThumbWidth", "F", "getThumbWidth", "()F", "ThumbHeight", "Landroidx/compose/ui/unit/DpSize;", "ThumbSize", "J", "ThumbDefaultElevation", "ThumbPressedElevation", "TickSize", "TrackHeight", "getTrackHeight", "isSpecified-If1S1O4", "(J)Z", "isSpecified-If1S1O4$annotations", "(J)V", "isSpecified", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SliderKt {
    private static final float ThumbDefaultElevation;
    private static final float ThumbHeight;
    private static final float ThumbPressedElevation;
    private static final long ThumbSize;
    private static final float ThumbWidth;
    private static final float TickSize;
    private static final float TrackHeight;

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$RangeSlider$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/material3/RangeSliderState;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements q<RangeSliderState, Composer, Integer, t0> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $startInteractionSource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(MutableInteractionSource mutableInteractionSource, SliderColors sliderColors, boolean z) {
            super(3);
            this.$startInteractionSource = mutableInteractionSource;
            this.$colors = sliderColors;
            this.$enabled = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((RangeSliderState) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(RangeSliderState rangeSliderState, Composer composer, int i10) {
            if ((i10 & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-811582901, i10, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:431)");
            }
            SliderDefaults.INSTANCE.m1888Thumb9LiSoMs(this.$startInteractionSource, null, this.$colors, this.$enabled, 0L, composer, 196614, 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$RangeSlider$11, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass11 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $endInteractionSource;
        final /* synthetic */ q<RangeSliderState, Composer, Integer, t0> $endThumb;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<x7.e<Float>, t0> $onValueChange;
        final /* synthetic */ r7.a<t0> $onValueChangeFinished;
        final /* synthetic */ MutableInteractionSource $startInteractionSource;
        final /* synthetic */ q<RangeSliderState, Composer, Integer, t0> $startThumb;
        final /* synthetic */ int $steps;
        final /* synthetic */ q<RangeSliderState, Composer, Integer, t0> $track;
        final /* synthetic */ x7.e<Float> $value;
        final /* synthetic */ x7.e<Float> $valueRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass11(x7.e<Float> eVar, l<? super x7.e<Float>, t0> lVar, Modifier modifier, boolean z, x7.e<Float> eVar2, r7.a<t0> aVar, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, q<? super RangeSliderState, ? super Composer, ? super Integer, t0> qVar, q<? super RangeSliderState, ? super Composer, ? super Integer, t0> qVar2, q<? super RangeSliderState, ? super Composer, ? super Integer, t0> qVar3, int i10, int i11, int i12, int i13) {
            super(2);
            this.$value = eVar;
            this.$onValueChange = lVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$valueRange = eVar2;
            this.$onValueChangeFinished = aVar;
            this.$colors = sliderColors;
            this.$startInteractionSource = mutableInteractionSource;
            this.$endInteractionSource = mutableInteractionSource2;
            this.$startThumb = qVar;
            this.$endThumb = qVar2;
            this.$track = qVar3;
            this.$steps = i10;
            this.$$changed = i11;
            this.$$changed1 = i12;
            this.$$default = i13;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SliderKt.RangeSlider(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$valueRange, this.$onValueChangeFinished, this.$colors, this.$startInteractionSource, this.$endInteractionSource, this.$startThumb, this.$endThumb, this.$track, this.$steps, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$RangeSlider$14, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass14 extends r implements q<RangeSliderState, Composer, Integer, t0> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $startInteractionSource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass14(MutableInteractionSource mutableInteractionSource, SliderColors sliderColors, boolean z) {
            super(3);
            this.$startInteractionSource = mutableInteractionSource;
            this.$colors = sliderColors;
            this.$enabled = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((RangeSliderState) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(RangeSliderState rangeSliderState, Composer composer, int i10) {
            if ((i10 & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1884205643, i10, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:628)");
            }
            SliderDefaults.INSTANCE.m1888Thumb9LiSoMs(this.$startInteractionSource, null, this.$colors, this.$enabled, 0L, composer, 196608, 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$RangeSlider$15, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass15 extends r implements q<RangeSliderState, Composer, Integer, t0> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $endInteractionSource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass15(MutableInteractionSource mutableInteractionSource, SliderColors sliderColors, boolean z) {
            super(3);
            this.$endInteractionSource = mutableInteractionSource;
            this.$colors = sliderColors;
            this.$enabled = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((RangeSliderState) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(RangeSliderState rangeSliderState, Composer composer, int i10) {
            if ((i10 & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1016457138, i10, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:635)");
            }
            SliderDefaults.INSTANCE.m1888Thumb9LiSoMs(this.$endInteractionSource, null, this.$colors, this.$enabled, 0L, composer, 196608, 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$RangeSlider$16, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass16 extends r implements q<RangeSliderState, Composer, Integer, t0> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass16(SliderColors sliderColors, boolean z) {
            super(3);
            this.$colors = sliderColors;
            this.$enabled = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((RangeSliderState) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(RangeSliderState rangeSliderState, Composer composer, int i10) {
            if ((i10 & 6) == 0) {
                i10 |= composer.changed(rangeSliderState) ? 4 : 2;
            }
            if ((i10 & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1617375262, i10, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:642)");
            }
            SliderDefaults.INSTANCE.Track(rangeSliderState, (Modifier) null, this.$colors, this.$enabled, composer, (i10 & 14) | 24576, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$RangeSlider$18, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass18 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $endInteractionSource;
        final /* synthetic */ q<RangeSliderState, Composer, Integer, t0> $endThumb;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ MutableInteractionSource $startInteractionSource;
        final /* synthetic */ q<RangeSliderState, Composer, Integer, t0> $startThumb;
        final /* synthetic */ RangeSliderState $state;
        final /* synthetic */ q<RangeSliderState, Composer, Integer, t0> $track;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass18(RangeSliderState rangeSliderState, Modifier modifier, boolean z, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, q<? super RangeSliderState, ? super Composer, ? super Integer, t0> qVar, q<? super RangeSliderState, ? super Composer, ? super Integer, t0> qVar2, q<? super RangeSliderState, ? super Composer, ? super Integer, t0> qVar3, int i10, int i11) {
            super(2);
            this.$state = rangeSliderState;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$colors = sliderColors;
            this.$startInteractionSource = mutableInteractionSource;
            this.$endInteractionSource = mutableInteractionSource2;
            this.$startThumb = qVar;
            this.$endThumb = qVar2;
            this.$track = qVar3;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SliderKt.RangeSlider(this.$state, this.$modifier, this.$enabled, this.$colors, this.$startInteractionSource, this.$endInteractionSource, this.$startThumb, this.$endThumb, this.$track, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$RangeSlider$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/material3/RangeSliderState;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements q<RangeSliderState, Composer, Integer, t0> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $endInteractionSource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(MutableInteractionSource mutableInteractionSource, SliderColors sliderColors, boolean z) {
            super(3);
            this.$endInteractionSource = mutableInteractionSource;
            this.$colors = sliderColors;
            this.$enabled = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((RangeSliderState) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(RangeSliderState rangeSliderState, Composer composer, int i10) {
            if ((i10 & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1832060001, i10, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:438)");
            }
            SliderDefaults.INSTANCE.m1888Thumb9LiSoMs(this.$endInteractionSource, null, this.$colors, this.$enabled, 0L, composer, 196614, 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$RangeSlider$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/material3/RangeSliderState;", "rangeSliderState", "Lx6/t0;", "invoke", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements q<RangeSliderState, Composer, Integer, t0> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(SliderColors sliderColors, boolean z) {
            super(3);
            this.$colors = sliderColors;
            this.$enabled = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((RangeSliderState) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(RangeSliderState rangeSliderState, Composer composer, int i10) {
            if ((i10 & 6) == 0) {
                i10 |= composer.changed(rangeSliderState) ? 4 : 2;
            }
            if ((i10 & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(377064480, i10, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:445)");
            }
            SliderDefaults.INSTANCE.Track(rangeSliderState, (Modifier) null, this.$colors, this.$enabled, composer, (i10 & 14) | 24576, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$RangeSlider$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<x7.e<Float>, t0> $onValueChange;
        final /* synthetic */ r7.a<t0> $onValueChangeFinished;
        final /* synthetic */ int $steps;
        final /* synthetic */ x7.e<Float> $value;
        final /* synthetic */ x7.e<Float> $valueRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(x7.e<Float> eVar, l<? super x7.e<Float>, t0> lVar, Modifier modifier, boolean z, x7.e<Float> eVar2, int i10, r7.a<t0> aVar, SliderColors sliderColors, int i11, int i12) {
            super(2);
            this.$value = eVar;
            this.$onValueChange = lVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$valueRange = eVar2;
            this.$steps = i10;
            this.$onValueChangeFinished = aVar;
            this.$colors = sliderColors;
            this.$$changed = i11;
            this.$$default = i12;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SliderKt.RangeSlider(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$valueRange, this.$steps, this.$onValueChangeFinished, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$RangeSlider$7, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass7 extends r implements q<RangeSliderState, Composer, Integer, t0> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $startInteractionSource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass7(MutableInteractionSource mutableInteractionSource, SliderColors sliderColors, boolean z) {
            super(3);
            this.$startInteractionSource = mutableInteractionSource;
            this.$colors = sliderColors;
            this.$enabled = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((RangeSliderState) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(RangeSliderState rangeSliderState, Composer composer, int i10) {
            if ((i10 & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1963073082, i10, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:521)");
            }
            SliderDefaults.INSTANCE.m1888Thumb9LiSoMs(this.$startInteractionSource, null, this.$colors, this.$enabled, 0L, composer, 196608, 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$RangeSlider$8, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass8 extends r implements q<RangeSliderState, Composer, Integer, t0> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $endInteractionSource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass8(MutableInteractionSource mutableInteractionSource, SliderColors sliderColors, boolean z) {
            super(3);
            this.$endInteractionSource = mutableInteractionSource;
            this.$colors = sliderColors;
            this.$enabled = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((RangeSliderState) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(RangeSliderState rangeSliderState, Composer composer, int i10) {
            if ((i10 & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1908709951, i10, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:528)");
            }
            SliderDefaults.INSTANCE.m1888Thumb9LiSoMs(this.$endInteractionSource, null, this.$colors, this.$enabled, 0L, composer, 196608, 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$RangeSlider$9, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass9 extends r implements q<RangeSliderState, Composer, Integer, t0> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass9(SliderColors sliderColors, boolean z) {
            super(3);
            this.$colors = sliderColors;
            this.$enabled = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((RangeSliderState) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(RangeSliderState rangeSliderState, Composer composer, int i10) {
            if ((i10 & 6) == 0) {
                i10 |= composer.changed(rangeSliderState) ? 4 : 2;
            }
            if ((i10 & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-429193201, i10, -1, "androidx.compose.material3.RangeSlider.<anonymous> (Slider.kt:535)");
            }
            SliderDefaults.INSTANCE.Track(rangeSliderState, (Modifier) null, this.$colors, this.$enabled, composer, (i10 & 14) | 24576, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$RangeSliderImpl$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05383 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $endInteractionSource;
        final /* synthetic */ q<RangeSliderState, Composer, Integer, t0> $endThumb;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ MutableInteractionSource $startInteractionSource;
        final /* synthetic */ q<RangeSliderState, Composer, Integer, t0> $startThumb;
        final /* synthetic */ RangeSliderState $state;
        final /* synthetic */ q<RangeSliderState, Composer, Integer, t0> $track;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05383(Modifier modifier, RangeSliderState rangeSliderState, boolean z, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, q<? super RangeSliderState, ? super Composer, ? super Integer, t0> qVar, q<? super RangeSliderState, ? super Composer, ? super Integer, t0> qVar2, q<? super RangeSliderState, ? super Composer, ? super Integer, t0> qVar3, int i10) {
            super(2);
            this.$modifier = modifier;
            this.$state = rangeSliderState;
            this.$enabled = z;
            this.$startInteractionSource = mutableInteractionSource;
            this.$endInteractionSource = mutableInteractionSource2;
            this.$startThumb = qVar;
            this.$endThumb = qVar2;
            this.$track = qVar3;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SliderKt.RangeSliderImpl(this.$modifier, this.$state, this.$enabled, this.$startInteractionSource, this.$endInteractionSource, this.$startThumb, this.$endThumb, this.$track, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$Slider$10, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass10 extends r implements q<SliderState, Composer, Integer, t0> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass10(MutableInteractionSource mutableInteractionSource, SliderColors sliderColors, boolean z) {
            super(3);
            this.$interactionSource = mutableInteractionSource;
            this.$colors = sliderColors;
            this.$enabled = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((SliderState) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(SliderState sliderState, Composer composer, int i10) {
            if ((i10 & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1426271326, i10, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:345)");
            }
            SliderDefaults.INSTANCE.m1888Thumb9LiSoMs(this.$interactionSource, null, this.$colors, this.$enabled, 0L, composer, 196608, 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$Slider$11, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C053911 extends r implements q<SliderState, Composer, Integer, t0> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C053911(SliderColors sliderColors, boolean z) {
            super(3);
            this.$colors = sliderColors;
            this.$enabled = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((SliderState) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(SliderState sliderState, Composer composer, int i10) {
            if ((i10 & 6) == 0) {
                i10 |= composer.changed(sliderState) ? 4 : 2;
            }
            if ((i10 & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(577038345, i10, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:352)");
            }
            SliderDefaults.INSTANCE.Track(sliderState, (Modifier) null, this.$colors, this.$enabled, composer, (i10 & 14) | 24576, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$Slider$13, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass13 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ SliderState $state;
        final /* synthetic */ q<SliderState, Composer, Integer, t0> $thumb;
        final /* synthetic */ q<SliderState, Composer, Integer, t0> $track;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass13(SliderState sliderState, Modifier modifier, boolean z, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, q<? super SliderState, ? super Composer, ? super Integer, t0> qVar, q<? super SliderState, ? super Composer, ? super Integer, t0> qVar2, int i10, int i11) {
            super(2);
            this.$state = sliderState;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$colors = sliderColors;
            this.$interactionSource = mutableInteractionSource;
            this.$thumb = qVar;
            this.$track = qVar2;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SliderKt.Slider(this.$state, this.$modifier, this.$enabled, this.$colors, this.$interactionSource, this.$thumb, this.$track, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$Slider$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/material3/SliderState;", "it", "Lx6/t0;", "invoke", "(Landroidx/compose/material3/SliderState;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05402 extends r implements q<SliderState, Composer, Integer, t0> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05402(MutableInteractionSource mutableInteractionSource, SliderColors sliderColors, boolean z) {
            super(3);
            this.$interactionSource = mutableInteractionSource;
            this.$colors = sliderColors;
            this.$enabled = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((SliderState) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(SliderState sliderState, Composer composer, int i10) {
            if ((i10 & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(308249025, i10, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:168)");
            }
            SliderDefaults.INSTANCE.m1888Thumb9LiSoMs(this.$interactionSource, null, this.$colors, this.$enabled, 0L, composer, 196608, 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$Slider$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/material3/SliderState;", "sliderState", "Lx6/t0;", "invoke", "(Landroidx/compose/material3/SliderState;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05413 extends r implements q<SliderState, Composer, Integer, t0> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05413(SliderColors sliderColors, boolean z) {
            super(3);
            this.$colors = sliderColors;
            this.$enabled = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((SliderState) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(SliderState sliderState, Composer composer, int i10) {
            if ((i10 & 6) == 0) {
                i10 |= composer.changed(sliderState) ? 4 : 2;
            }
            if ((i10 & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1843234110, i10, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:175)");
            }
            SliderDefaults.INSTANCE.Track(sliderState, (Modifier) null, this.$colors, this.$enabled, composer, (i10 & 14) | 24576, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$Slider$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05424 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<Float, t0> $onValueChange;
        final /* synthetic */ r7.a<t0> $onValueChangeFinished;
        final /* synthetic */ int $steps;
        final /* synthetic */ float $value;
        final /* synthetic */ x7.e<Float> $valueRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05424(float f10, l<? super Float, t0> lVar, Modifier modifier, boolean z, x7.e<Float> eVar, int i10, r7.a<t0> aVar, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, int i11, int i12) {
            super(2);
            this.$value = f10;
            this.$onValueChange = lVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$valueRange = eVar;
            this.$steps = i10;
            this.$onValueChangeFinished = aVar;
            this.$colors = sliderColors;
            this.$interactionSource = mutableInteractionSource;
            this.$$changed = i11;
            this.$$default = i12;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SliderKt.Slider(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$valueRange, this.$steps, this.$onValueChangeFinished, this.$colors, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$Slider$6, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass6 extends r implements q<SliderState, Composer, Integer, t0> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass6(MutableInteractionSource mutableInteractionSource, SliderColors sliderColors, boolean z) {
            super(3);
            this.$interactionSource = mutableInteractionSource;
            this.$colors = sliderColors;
            this.$enabled = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((SliderState) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(SliderState sliderState, Composer composer, int i10) {
            if ((i10 & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1756326375, i10, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:251)");
            }
            SliderDefaults.INSTANCE.m1888Thumb9LiSoMs(this.$interactionSource, null, this.$colors, this.$enabled, 0L, composer, 196608, 18);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$Slider$7, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05437 extends r implements q<SliderState, Composer, Integer, t0> {
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05437(SliderColors sliderColors, boolean z) {
            super(3);
            this.$colors = sliderColors;
            this.$enabled = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((SliderState) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(SliderState sliderState, Composer composer, int i10) {
            if ((i10 & 6) == 0) {
                i10 |= composer.changed(sliderState) ? 4 : 2;
            }
            if ((i10 & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2083675534, i10, -1, "androidx.compose.material3.Slider.<anonymous> (Slider.kt:258)");
            }
            SliderDefaults.INSTANCE.Track(sliderState, (Modifier) null, this.$colors, this.$enabled, composer, (i10 & 14) | 24576, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$Slider$8, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05448 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ SliderColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<Float, t0> $onValueChange;
        final /* synthetic */ r7.a<t0> $onValueChangeFinished;
        final /* synthetic */ int $steps;
        final /* synthetic */ q<SliderState, Composer, Integer, t0> $thumb;
        final /* synthetic */ q<SliderState, Composer, Integer, t0> $track;
        final /* synthetic */ float $value;
        final /* synthetic */ x7.e<Float> $valueRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05448(float f10, l<? super Float, t0> lVar, Modifier modifier, boolean z, r7.a<t0> aVar, SliderColors sliderColors, MutableInteractionSource mutableInteractionSource, int i10, q<? super SliderState, ? super Composer, ? super Integer, t0> qVar, q<? super SliderState, ? super Composer, ? super Integer, t0> qVar2, x7.e<Float> eVar, int i11, int i12, int i13) {
            super(2);
            this.$value = f10;
            this.$onValueChange = lVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$onValueChangeFinished = aVar;
            this.$colors = sliderColors;
            this.$interactionSource = mutableInteractionSource;
            this.$steps = i10;
            this.$thumb = qVar;
            this.$track = qVar2;
            this.$valueRange = eVar;
            this.$$changed = i11;
            this.$$changed1 = i12;
            this.$$default = i13;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SliderKt.Slider(this.$value, this.$onValueChange, this.$modifier, this.$enabled, this.$onValueChangeFinished, this.$colors, this.$interactionSource, this.$steps, this.$thumb, this.$track, this.$valueRange, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$SliderImpl$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05453 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ SliderState $state;
        final /* synthetic */ q<SliderState, Composer, Integer, t0> $thumb;
        final /* synthetic */ q<SliderState, Composer, Integer, t0> $track;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05453(Modifier modifier, SliderState sliderState, boolean z, MutableInteractionSource mutableInteractionSource, q<? super SliderState, ? super Composer, ? super Integer, t0> qVar, q<? super SliderState, ? super Composer, ? super Integer, t0> qVar2, int i10) {
            super(2);
            this.$modifier = modifier;
            this.$state = sliderState;
            this.$enabled = z;
            this.$interactionSource = mutableInteractionSource;
            this.$thumb = qVar;
            this.$track = qVar2;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SliderKt.SliderImpl(this.$modifier, this.$state, this.$enabled, this.$interactionSource, this.$thumb, this.$track, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$rangeSliderEndThumbSemantics$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05461 extends r implements l<SemanticsPropertyReceiver, t0> {
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ RangeSliderState $state;
        final /* synthetic */ x7.e<Float> $valueRange;

        /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$rangeSliderEndThumbSemantics$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "targetValue", "", "invoke", "(F)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class C01081 extends r implements l<Float, Boolean> {
            final /* synthetic */ RangeSliderState $state;
            final /* synthetic */ x7.e<Float> $valueRange;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01081(x7.e<Float> eVar, RangeSliderState rangeSliderState) {
                super(1);
                this.$valueRange = eVar;
                this.$state = rangeSliderState;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Number) obj).floatValue());
            }

            public final Boolean invoke(float f10) {
                int endSteps$material3_release;
                float fM = qb.d.m(f10, ((Number) this.$valueRange.getStart()).floatValue(), ((Number) this.$valueRange.b()).floatValue());
                boolean z = false;
                if (this.$state.getEndSteps$material3_release() > 0 && (endSteps$material3_release = this.$state.getEndSteps$material3_release() + 1) >= 0) {
                    float fAbs = fM;
                    float f11 = fAbs;
                    int i10 = 0;
                    while (true) {
                        float fLerp = MathHelpersKt.lerp(((Number) this.$valueRange.getStart()).floatValue(), ((Number) this.$valueRange.b()).floatValue(), i10 / (this.$state.getEndSteps$material3_release() + 1));
                        float f12 = fLerp - fM;
                        if (Math.abs(f12) <= fAbs) {
                            fAbs = Math.abs(f12);
                            f11 = fLerp;
                        }
                        if (i10 == endSteps$material3_release) {
                            break;
                        }
                        i10++;
                    }
                    fM = f11;
                }
                if (fM != this.$state.getActiveRangeEnd()) {
                    long jSliderRange = SliderKt.SliderRange(this.$state.getActiveRangeStart(), fM);
                    if (!SliderRange.m1904equalsimpl0(jSliderRange, SliderKt.SliderRange(this.$state.getActiveRangeStart(), this.$state.getActiveRangeEnd()))) {
                        if (this.$state.getOnValueChange$material3_release() != null) {
                            l<SliderRange, t0> onValueChange$material3_release = this.$state.getOnValueChange$material3_release();
                            if (onValueChange$material3_release != null) {
                                onValueChange$material3_release.invoke(SliderRange.m1901boximpl(jSliderRange));
                            }
                        } else {
                            this.$state.setActiveRangeStart(SliderRange.m1906getStartimpl(jSliderRange));
                            this.$state.setActiveRangeEnd(SliderRange.m1905getEndInclusiveimpl(jSliderRange));
                        }
                    }
                    r7.a<t0> onValueChangeFinished = this.$state.getOnValueChangeFinished();
                    if (onValueChangeFinished != null) {
                        onValueChangeFinished.invoke();
                    }
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05461(boolean z, x7.e<Float> eVar, RangeSliderState rangeSliderState) {
            super(1);
            this.$enabled = z;
            this.$valueRange = eVar;
            this.$state = rangeSliderState;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SemanticsPropertyReceiver) obj);
            return t0.f22605a;
        }

        public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            if (!this.$enabled) {
                SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
            }
            SemanticsPropertiesKt.setProgress$default(semanticsPropertyReceiver, null, new C01081(this.$valueRange, this.$state), 1, null);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1", f = "Slider.kt", l = {1496}, m = "invokeSuspend")
    public static final class C05471 extends j implements p<PointerInputScope, d7.d<? super t0>, Object> {
        final /* synthetic */ MutableInteractionSource $endInteractionSource;
        final /* synthetic */ MutableInteractionSource $startInteractionSource;
        final /* synthetic */ RangeSliderState $state;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1", f = "Slider.kt", l = {1497}, m = "invokeSuspend")
        public static final class C01091 extends j implements p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
            final /* synthetic */ RangeSliderState $state;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;)V"}, k = 3, mv = {1, 8, 0})
            @f7.e(c = "androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1$1", f = "Slider.kt", l = {1498, 1509, 1528}, m = "invokeSuspend")
            public static final class C01101 extends f7.h implements p<AwaitPointerEventScope, d7.d<? super t0>, Object> {
                final /* synthetic */ k0 $$this$coroutineScope;
                final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                final /* synthetic */ RangeSliderState $state;
                private /* synthetic */ Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                Object L$4;
                int label;

                /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1$1$2, reason: invalid class name */
                @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
                @f7.e(c = "androidx.compose.material3.SliderKt$rangeSliderPressDragModifier$1$1$1$2", f = "Slider.kt", l = {1545}, m = "invokeSuspend")
                public static final class AnonymousClass2 extends j implements p<k0, d7.d<? super t0>, Object> {
                    final /* synthetic */ f0 $draggingStart;
                    final /* synthetic */ DragInteraction $finishInteraction;
                    final /* synthetic */ RangeSliderLogic $rangeSliderLogic;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public AnonymousClass2(RangeSliderLogic rangeSliderLogic, f0 f0Var, DragInteraction dragInteraction, d7.d<? super AnonymousClass2> dVar) {
                        super(2, dVar);
                        this.$rangeSliderLogic = rangeSliderLogic;
                        this.$draggingStart = f0Var;
                        this.$finishInteraction = dragInteraction;
                    }

                    @Override // f7.a
                    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                        return new AnonymousClass2(this.$rangeSliderLogic, this.$draggingStart, this.$finishInteraction, dVar);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        int i10 = this.label;
                        if (i10 == 0) {
                            k2.c.G(obj);
                            MutableInteractionSource mutableInteractionSourceActiveInteraction = this.$rangeSliderLogic.activeInteraction(this.$draggingStart.f19738i);
                            DragInteraction dragInteraction = this.$finishInteraction;
                            this.label = 1;
                            Object objEmit = mutableInteractionSourceActiveInteraction.emit(dragInteraction, this);
                            e7.a aVar = e7.a.f15033i;
                            if (objEmit == aVar) {
                                return aVar;
                            }
                        } else {
                            if (i10 != 1) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            k2.c.G(obj);
                        }
                        return t0.f22605a;
                    }

                    @Override // r7.p
                    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                        return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01101(RangeSliderState rangeSliderState, RangeSliderLogic rangeSliderLogic, k0 k0Var, d7.d<? super C01101> dVar) {
                    super(2, dVar);
                    this.$state = rangeSliderState;
                    this.$rangeSliderLogic = rangeSliderLogic;
                    this.$$this$coroutineScope = k0Var;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    C01101 c01101 = new C01101(this.$state, this.$rangeSliderLogic, this.$$this$coroutineScope, dVar);
                    c01101.L$0 = obj;
                    return c01101;
                }

                @Override // r7.p
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, d7.d<? super t0> dVar) {
                    return ((C01101) create(awaitPointerEventScope, dVar)).invokeSuspend(t0.f22605a);
                }

                /* JADX WARN: Code restructure failed: missing block: B:51:0x0160, code lost:
                
                    if (r1 != r11) goto L53;
                 */
                /* JADX WARN: Removed duplicated region for block: B:36:0x00e1  */
                @Override // f7.a
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r17) {
                    /*
                        Method dump skipped, instruction units count: 413
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.C05471.C01091.C01101.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01091(PointerInputScope pointerInputScope, RangeSliderState rangeSliderState, RangeSliderLogic rangeSliderLogic, d7.d<? super C01091> dVar) {
                super(2, dVar);
                this.$$this$pointerInput = pointerInputScope;
                this.$state = rangeSliderState;
                this.$rangeSliderLogic = rangeSliderLogic;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                C01091 c01091 = new C01091(this.$$this$pointerInput, this.$state, this.$rangeSliderLogic, dVar);
                c01091.L$0 = obj;
                return c01091;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    k0 k0Var = (k0) this.L$0;
                    PointerInputScope pointerInputScope = this.$$this$pointerInput;
                    C01101 c01101 = new C01101(this.$state, this.$rangeSliderLogic, k0Var, null);
                    this.label = 1;
                    Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, c01101, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objAwaitEachGesture == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                return ((C01091) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05471(RangeSliderState rangeSliderState, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, d7.d<? super C05471> dVar) {
            super(2, dVar);
            this.$state = rangeSliderState;
            this.$startInteractionSource = mutableInteractionSource;
            this.$endInteractionSource = mutableInteractionSource2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C05471 c05471 = new C05471(this.$state, this.$startInteractionSource, this.$endInteractionSource, dVar);
            c05471.L$0 = obj;
            return c05471;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super t0> dVar) {
            return ((C05471) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                C01091 c01091 = new C01091((PointerInputScope) this.L$0, this.$state, new RangeSliderLogic(this.$state, this.$startInteractionSource, this.$endInteractionSource), null);
                this.label = 1;
                Object objC = l0.c(c01091, this);
                e7.a aVar = e7.a.f15033i;
                if (objC == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$rangeSliderStartThumbSemantics$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05481 extends r implements l<SemanticsPropertyReceiver, t0> {
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ RangeSliderState $state;
        final /* synthetic */ x7.e<Float> $valueRange;

        /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$rangeSliderStartThumbSemantics$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "targetValue", "", "invoke", "(F)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class C01111 extends r implements l<Float, Boolean> {
            final /* synthetic */ RangeSliderState $state;
            final /* synthetic */ x7.e<Float> $valueRange;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01111(x7.e<Float> eVar, RangeSliderState rangeSliderState) {
                super(1);
                this.$valueRange = eVar;
                this.$state = rangeSliderState;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Number) obj).floatValue());
            }

            public final Boolean invoke(float f10) {
                int startSteps$material3_release;
                float fM = qb.d.m(f10, ((Number) this.$valueRange.getStart()).floatValue(), ((Number) this.$valueRange.b()).floatValue());
                boolean z = false;
                if (this.$state.getStartSteps$material3_release() > 0 && (startSteps$material3_release = this.$state.getStartSteps$material3_release() + 1) >= 0) {
                    float fAbs = fM;
                    float f11 = fAbs;
                    int i10 = 0;
                    while (true) {
                        float fLerp = MathHelpersKt.lerp(((Number) this.$valueRange.getStart()).floatValue(), ((Number) this.$valueRange.b()).floatValue(), i10 / (this.$state.getStartSteps$material3_release() + 1));
                        float f12 = fLerp - fM;
                        if (Math.abs(f12) <= fAbs) {
                            fAbs = Math.abs(f12);
                            f11 = fLerp;
                        }
                        if (i10 == startSteps$material3_release) {
                            break;
                        }
                        i10++;
                    }
                    fM = f11;
                }
                if (fM != this.$state.getActiveRangeStart()) {
                    long jSliderRange = SliderKt.SliderRange(fM, this.$state.getActiveRangeEnd());
                    if (!SliderRange.m1904equalsimpl0(jSliderRange, SliderKt.SliderRange(this.$state.getActiveRangeStart(), this.$state.getActiveRangeEnd()))) {
                        if (this.$state.getOnValueChange$material3_release() != null) {
                            l<SliderRange, t0> onValueChange$material3_release = this.$state.getOnValueChange$material3_release();
                            if (onValueChange$material3_release != null) {
                                onValueChange$material3_release.invoke(SliderRange.m1901boximpl(jSliderRange));
                            }
                        } else {
                            this.$state.setActiveRangeStart(SliderRange.m1906getStartimpl(jSliderRange));
                            this.$state.setActiveRangeEnd(SliderRange.m1905getEndInclusiveimpl(jSliderRange));
                        }
                    }
                    r7.a<t0> onValueChangeFinished = this.$state.getOnValueChangeFinished();
                    if (onValueChangeFinished != null) {
                        onValueChangeFinished.invoke();
                    }
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05481(boolean z, x7.e<Float> eVar, RangeSliderState rangeSliderState) {
            super(1);
            this.$enabled = z;
            this.$valueRange = eVar;
            this.$state = rangeSliderState;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SemanticsPropertyReceiver) obj);
            return t0.f22605a;
        }

        public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            if (!this.$enabled) {
                SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
            }
            SemanticsPropertiesKt.setProgress$default(semanticsPropertyReceiver, null, new C01111(this.$valueRange, this.$state), 1, null);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$sliderSemantics$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05491 extends r implements l<SemanticsPropertyReceiver, t0> {
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ SliderState $state;

        /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$sliderSemantics$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "targetValue", "", "invoke", "(F)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        public static final class C01121 extends r implements l<Float, Boolean> {
            final /* synthetic */ SliderState $state;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01121(SliderState sliderState) {
                super(1);
                this.$state = sliderState;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke(((Number) obj).floatValue());
            }

            public final Boolean invoke(float f10) {
                int steps;
                float fM = qb.d.m(f10, ((Number) this.$state.getValueRange().getStart()).floatValue(), ((Number) this.$state.getValueRange().b()).floatValue());
                boolean z = false;
                if (this.$state.getSteps() > 0 && (steps = this.$state.getSteps() + 1) >= 0) {
                    float fAbs = fM;
                    float f11 = fAbs;
                    int i10 = 0;
                    while (true) {
                        float fLerp = MathHelpersKt.lerp(((Number) this.$state.getValueRange().getStart()).floatValue(), ((Number) this.$state.getValueRange().b()).floatValue(), i10 / (this.$state.getSteps() + 1));
                        float f12 = fLerp - fM;
                        if (Math.abs(f12) <= fAbs) {
                            fAbs = Math.abs(f12);
                            f11 = fLerp;
                        }
                        if (i10 == steps) {
                            break;
                        }
                        i10++;
                    }
                    fM = f11;
                }
                if (fM != this.$state.getValue()) {
                    if (fM != this.$state.getValue()) {
                        if (this.$state.getOnValueChange$material3_release() != null) {
                            l<Float, t0> onValueChange$material3_release = this.$state.getOnValueChange$material3_release();
                            if (onValueChange$material3_release != null) {
                                onValueChange$material3_release.invoke(Float.valueOf(fM));
                            }
                        } else {
                            this.$state.setValue(fM);
                        }
                    }
                    r7.a<t0> onValueChangeFinished = this.$state.getOnValueChangeFinished();
                    if (onValueChangeFinished != null) {
                        onValueChangeFinished.invoke();
                    }
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05491(boolean z, SliderState sliderState) {
            super(1);
            this.$enabled = z;
            this.$state = sliderState;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SemanticsPropertyReceiver) obj);
            return t0.f22605a;
        }

        public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            if (!this.$enabled) {
                SemanticsPropertiesKt.disabled(semanticsPropertyReceiver);
            }
            SemanticsPropertiesKt.setProgress$default(semanticsPropertyReceiver, null, new C01121(this.$state), 1, null);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$sliderTapModifier$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.SliderKt$sliderTapModifier$1", f = "Slider.kt", l = {1469}, m = "invokeSuspend")
    public static final class C05501 extends j implements p<PointerInputScope, d7.d<? super t0>, Object> {
        final /* synthetic */ SliderState $state;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$sliderTapModifier$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/PressGestureScope;Landroidx/compose/ui/geometry/Offset;)V"}, k = 3, mv = {1, 8, 0})
        @f7.e(c = "androidx.compose.material3.SliderKt$sliderTapModifier$1$1", f = "Slider.kt", l = {}, m = "invokeSuspend")
        public static final class C01131 extends j implements q<PressGestureScope, Offset, d7.d<? super t0>, Object> {
            final /* synthetic */ SliderState $state;
            /* synthetic */ long J$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01131(SliderState sliderState, d7.d<? super C01131> dVar) {
                super(3, dVar);
                this.$state = sliderState;
            }

            @Override // r7.q
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                return m1899invoked4ec7I((PressGestureScope) obj, ((Offset) obj2).getPackedValue(), (d7.d) obj3);
            }

            /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
            public final Object m1899invoked4ec7I(PressGestureScope pressGestureScope, long j10, d7.d<? super t0> dVar) {
                C01131 c01131 = new C01131(this.$state, dVar);
                c01131.J$0 = j10;
                return c01131.invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                this.$state.m1912onPressk4lQ0M$material3_release(this.J$0);
                return t0.f22605a;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.SliderKt$sliderTapModifier$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/ui/geometry/Offset;", "it", "Lx6/t0;", "invoke-k-4lQ0M", "(J)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass2 extends r implements l<Offset, t0> {
            final /* synthetic */ SliderState $state;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(SliderState sliderState) {
                super(1);
                this.$state = sliderState;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                m1900invokek4lQ0M(((Offset) obj).getPackedValue());
                return t0.f22605a;
            }

            /* JADX INFO: renamed from: invoke-k-4lQ0M, reason: not valid java name */
            public final void m1900invokek4lQ0M(long j10) {
                this.$state.dispatchRawDelta(0.0f);
                this.$state.getGestureEndAction$material3_release().invoke();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05501(SliderState sliderState, d7.d<? super C05501> dVar) {
            super(2, dVar);
            this.$state = sliderState;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C05501 c05501 = new C05501(this.$state, dVar);
            c05501.L$0 = obj;
            return c05501;
        }

        @Override // r7.p
        public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super t0> dVar) {
            return ((C05501) create(pointerInputScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                C01131 c01131 = new C01131(this.$state, null);
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$state);
                this.label = 1;
                Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, c01131, anonymousClass2, this, 3, null);
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
            return t0.f22605a;
        }
    }

    static {
        SliderTokens sliderTokens = SliderTokens.INSTANCE;
        float fM2823getHandleWidthD9Ej5fM = sliderTokens.m2823getHandleWidthD9Ej5fM();
        ThumbWidth = fM2823getHandleWidthD9Ej5fM;
        float fM2822getHandleHeightD9Ej5fM = sliderTokens.m2822getHandleHeightD9Ej5fM();
        ThumbHeight = fM2822getHandleHeightD9Ej5fM;
        ThumbSize = DpKt.m5700DpSizeYgX7TsA(fM2823getHandleWidthD9Ej5fM, fM2822getHandleHeightD9Ej5fM);
        ThumbDefaultElevation = Dp.m5678constructorimpl(1);
        ThumbPressedElevation = Dp.m5678constructorimpl(6);
        TickSize = sliderTokens.m2829getTickMarksContainerSizeD9Ej5fM();
        TrackHeight = sliderTokens.m2824getInactiveTrackHeightD9Ej5fM();
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:144:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x010a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void RangeSlider(x7.e<java.lang.Float> r28, r7.l<? super x7.e<java.lang.Float>, x6.t0> r29, androidx.compose.ui.Modifier r30, boolean r31, x7.e<java.lang.Float> r32, int r33, r7.a<x6.t0> r34, androidx.compose.material3.SliderColors r35, androidx.compose.runtime.Composer r36, int r37, int r38) {
        /*
            Method dump skipped, instruction units count: 561
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.RangeSlider(x7.e, r7.l, androidx.compose.ui.Modifier, boolean, x7.e, int, r7.a, androidx.compose.material3.SliderColors, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x034f  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x03a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void RangeSliderImpl(androidx.compose.ui.Modifier r25, final androidx.compose.material3.RangeSliderState r26, boolean r27, androidx.compose.foundation.interaction.MutableInteractionSource r28, androidx.compose.foundation.interaction.MutableInteractionSource r29, r7.q<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r30, r7.q<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r31, r7.q<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r32, androidx.compose.runtime.Composer r33, int r34) {
        /*
            Method dump skipped, instruction units count: 957
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.RangeSliderImpl(androidx.compose.ui.Modifier, androidx.compose.material3.RangeSliderState, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.foundation.interaction.MutableInteractionSource, r7.q, r7.q, r7.q, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:154:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Slider(float r27, r7.l<? super java.lang.Float, x6.t0> r28, androidx.compose.ui.Modifier r29, boolean r30, x7.e<java.lang.Float> r31, int r32, r7.a<x6.t0> r33, androidx.compose.material3.SliderColors r34, androidx.compose.foundation.interaction.MutableInteractionSource r35, androidx.compose.runtime.Composer r36, int r37, int r38) {
        /*
            Method dump skipped, instruction units count: 585
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.Slider(float, r7.l, androidx.compose.ui.Modifier, boolean, x7.e, int, r7.a, androidx.compose.material3.SliderColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:103:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01f0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SliderImpl(androidx.compose.ui.Modifier r27, final androidx.compose.material3.SliderState r28, boolean r29, androidx.compose.foundation.interaction.MutableInteractionSource r30, r7.q<? super androidx.compose.material3.SliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r31, r7.q<? super androidx.compose.material3.SliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r32, androidx.compose.runtime.Composer r33, int r34) {
        /*
            Method dump skipped, instruction units count: 724
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.SliderImpl(androidx.compose.ui.Modifier, androidx.compose.material3.SliderState, boolean, androidx.compose.foundation.interaction.MutableInteractionSource, r7.q, r7.q, androidx.compose.runtime.Composer, int):void");
    }

    public static final long SliderRange(float f10, float f11) {
        if ((Float.isNaN(f10) && Float.isNaN(f11)) || f10 <= f11) {
            return SliderRange.m1902constructorimpl((((long) Float.floatToRawIntBits(f11)) & 4294967295L) | (Float.floatToRawIntBits(f10) << 32));
        }
        throw new IllegalArgumentException(("start(" + f10 + ") must be <= endInclusive(" + f11 + ')').toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /* JADX INFO: renamed from: awaitSlop-8vUncbI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m1892awaitSlop8vUncbI(androidx.compose.ui.input.pointer.AwaitPointerEventScope r8, long r9, int r11, d7.d<? super x6.x> r12) {
        /*
            boolean r0 = r12 instanceof androidx.compose.material3.SliderKt$awaitSlop$1
            if (r0 == 0) goto L14
            r0 = r12
            androidx.compose.material3.SliderKt$awaitSlop$1 r0 = (androidx.compose.material3.SliderKt$awaitSlop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            androidx.compose.material3.SliderKt$awaitSlop$1 r0 = new androidx.compose.material3.SliderKt$awaitSlop$1
            r0.<init>(r12)
            goto L12
        L1a:
            java.lang.Object r12 = r6.result
            int r0 = r6.label
            r1 = 1
            if (r0 == 0) goto L33
            if (r0 != r1) goto L2b
            java.lang.Object r8 = r6.L$0
            kotlin.jvm.internal.h0 r8 = (kotlin.jvm.internal.h0) r8
            k2.c.G(r12)
            goto L53
        L2b:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L33:
            k2.c.G(r12)
            kotlin.jvm.internal.h0 r12 = new kotlin.jvm.internal.h0
            r12.<init>()
            androidx.compose.material3.SliderKt$awaitSlop$postPointerSlop$1 r5 = new androidx.compose.material3.SliderKt$awaitSlop$postPointerSlop$1
            r5.<init>(r12)
            r6.L$0 = r12
            r6.label = r1
            r1 = r8
            r2 = r9
            r4 = r11
            java.lang.Object r8 = androidx.compose.material3.DragGestureDetectorCopyKt.m1536awaitHorizontalPointerSlopOrCancellationgDDlDlE(r1, r2, r4, r5, r6)
            e7.a r9 = e7.a.f15033i
            if (r8 != r9) goto L50
            return r9
        L50:
            r7 = r12
            r12 = r8
            r8 = r7
        L53:
            androidx.compose.ui.input.pointer.PointerInputChange r12 = (androidx.compose.ui.input.pointer.PointerInputChange) r12
            if (r12 == 0) goto L64
            float r8 = r8.f19742i
            java.lang.Float r9 = new java.lang.Float
            r9.<init>(r8)
            x6.x r8 = new x6.x
            r8.<init>(r12, r9)
            return r8
        L64:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.m1892awaitSlop8vUncbI(androidx.compose.ui.input.pointer.AwaitPointerEventScope, long, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calcFraction(float f10, float f11, float f12) {
        float f13 = f11 - f10;
        return qb.d.m(f13 == 0.0f ? 0.0f : (f12 - f10) / f13, 0.0f, 1.0f);
    }

    public static final float getThumbWidth() {
        return ThumbWidth;
    }

    public static final float getTrackHeight() {
        return TrackHeight;
    }

    /* JADX INFO: renamed from: isSpecified-If1S1O4, reason: not valid java name */
    public static final boolean m1893isSpecifiedIf1S1O4(long j10) {
        return j10 != SliderRange.INSTANCE.m1911getUnspecifiedFYbKRX4();
    }

    /* JADX INFO: renamed from: isSpecified-If1S1O4$annotations, reason: not valid java name */
    public static /* synthetic */ void m1894isSpecifiedIf1S1O4$annotations(long j10) {
    }

    private static final Modifier rangeSliderEndThumbSemantics(Modifier modifier, RangeSliderState rangeSliderState, boolean z) {
        x7.d dVar = new x7.d(rangeSliderState.getActiveRangeStart(), ((Number) rangeSliderState.getValueRange().b()).floatValue());
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default(modifier, false, new C05461(z, dVar, rangeSliderState), 1, null), rangeSliderState.getActiveRangeEnd(), dVar, rangeSliderState.getEndSteps$material3_release());
    }

    private static final Modifier rangeSliderPressDragModifier(Modifier modifier, RangeSliderState rangeSliderState, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2, boolean z) {
        return z ? SuspendingPointerInputFilterKt.pointerInput(modifier, new Object[]{mutableInteractionSource, mutableInteractionSource2, rangeSliderState}, (p<? super PointerInputScope, ? super d7.d<? super t0>, ? extends Object>) new C05471(rangeSliderState, mutableInteractionSource, mutableInteractionSource2, null)) : modifier;
    }

    private static final Modifier rangeSliderStartThumbSemantics(Modifier modifier, RangeSliderState rangeSliderState, boolean z) {
        x7.d dVar = new x7.d(((Number) rangeSliderState.getValueRange().getStart()).floatValue(), rangeSliderState.getActiveRangeEnd());
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default(modifier, false, new C05481(z, dVar, rangeSliderState), 1, null), rangeSliderState.getActiveRangeStart(), dVar, rangeSliderState.getStartSteps$material3_release());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float scale(float f10, float f11, float f12, float f13, float f14) {
        return MathHelpersKt.lerp(f13, f14, calcFraction(f10, f11, f12));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: scale-ziovWd0, reason: not valid java name */
    public static final long m1895scaleziovWd0(float f10, float f11, long j10, float f12, float f13) {
        return SliderRange(scale(f10, f11, SliderRange.m1906getStartimpl(j10), f12, f13), scale(f10, f11, SliderRange.m1905getEndInclusiveimpl(j10), f12, f13));
    }

    private static final Modifier sliderSemantics(Modifier modifier, SliderState sliderState, boolean z) {
        return ProgressSemanticsKt.progressSemantics(SemanticsModifierKt.semantics$default(modifier, false, new C05491(z, sliderState), 1, null), sliderState.getValue(), new x7.d(((Number) sliderState.getValueRange().getStart()).floatValue(), ((Number) sliderState.getValueRange().b()).floatValue()), sliderState.getSteps());
    }

    private static final Modifier sliderTapModifier(Modifier modifier, SliderState sliderState, MutableInteractionSource mutableInteractionSource, boolean z) {
        return z ? SuspendingPointerInputFilterKt.pointerInput(modifier, sliderState, mutableInteractionSource, new C05501(sliderState, null)) : modifier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float snapValueToTick(float f10, float[] fArr, float f11, float f12) {
        Float fValueOf;
        if (fArr.length == 0) {
            fValueOf = null;
        } else {
            float f13 = fArr[0];
            int length = fArr.length - 1;
            if (length == 0) {
                fValueOf = Float.valueOf(f13);
            } else {
                float fAbs = Math.abs(MathHelpersKt.lerp(f11, f12, f13) - f10);
                x7.h it = new i(1, length, 1).iterator();
                while (it.f22624m) {
                    float f14 = fArr[it.nextInt()];
                    float fAbs2 = Math.abs(MathHelpersKt.lerp(f11, f12, f14) - f10);
                    if (Float.compare(fAbs, fAbs2) > 0) {
                        f13 = f14;
                        fAbs = fAbs2;
                    }
                }
                fValueOf = Float.valueOf(f13);
            }
        }
        return fValueOf != null ? MathHelpersKt.lerp(f11, f12, fValueOf.floatValue()) : f10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float[] stepsToTickFractions(int i10) {
        if (i10 == 0) {
            return new float[0];
        }
        int i11 = i10 + 2;
        float[] fArr = new float[i11];
        for (int i12 = 0; i12 < i11; i12++) {
            fArr[i12] = i12 / (i10 + 1);
        }
        return fArr;
    }

    public static final long SliderRange(x7.e<Float> eVar) {
        float fFloatValue = ((Number) eVar.getStart()).floatValue();
        float fFloatValue2 = ((Number) eVar.b()).floatValue();
        if ((Float.isNaN(fFloatValue) && Float.isNaN(fFloatValue2)) || fFloatValue <= fFloatValue2) {
            return SliderRange.m1902constructorimpl((((long) Float.floatToRawIntBits(fFloatValue)) << 32) | (((long) Float.floatToRawIntBits(fFloatValue2)) & 4294967295L));
        }
        throw new IllegalArgumentException(("ClosedFloatingPointRange<Float>.start(" + fFloatValue + ") must be <= ClosedFloatingPoint.endInclusive(" + fFloatValue2 + ')').toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:197:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Slider(float r27, r7.l<? super java.lang.Float, x6.t0> r28, androidx.compose.ui.Modifier r29, boolean r30, r7.a<x6.t0> r31, androidx.compose.material3.SliderColors r32, androidx.compose.foundation.interaction.MutableInteractionSource r33, int r34, r7.q<? super androidx.compose.material3.SliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r35, r7.q<? super androidx.compose.material3.SliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r36, x7.e<java.lang.Float> r37, androidx.compose.runtime.Composer r38, int r39, int r40, int r41) {
        /*
            Method dump skipped, instruction units count: 748
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.Slider(float, r7.l, androidx.compose.ui.Modifier, boolean, r7.a, androidx.compose.material3.SliderColors, androidx.compose.foundation.interaction.MutableInteractionSource, int, r7.q, r7.q, x7.e, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x03f4  */
    /* JADX WARN: Removed duplicated region for block: B:241:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void RangeSlider(x7.e<java.lang.Float> r31, r7.l<? super x7.e<java.lang.Float>, x6.t0> r32, androidx.compose.ui.Modifier r33, boolean r34, x7.e<java.lang.Float> r35, r7.a<x6.t0> r36, androidx.compose.material3.SliderColors r37, androidx.compose.foundation.interaction.MutableInteractionSource r38, androidx.compose.foundation.interaction.MutableInteractionSource r39, r7.q<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r40, r7.q<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r41, r7.q<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r42, int r43, androidx.compose.runtime.Composer r44, int r45, int r46, int r47) {
        /*
            Method dump skipped, instruction units count: 1030
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.RangeSlider(x7.e, r7.l, androidx.compose.ui.Modifier, boolean, x7.e, r7.a, androidx.compose.material3.SliderColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.foundation.interaction.MutableInteractionSource, r7.q, r7.q, r7.q, int, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:129:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0108  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Slider(androidx.compose.material3.SliderState r17, androidx.compose.ui.Modifier r18, boolean r19, androidx.compose.material3.SliderColors r20, androidx.compose.foundation.interaction.MutableInteractionSource r21, r7.q<? super androidx.compose.material3.SliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r22, r7.q<? super androidx.compose.material3.SliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r23, androidx.compose.runtime.Composer r24, int r25, int r26) {
        /*
            Method dump skipped, instruction units count: 446
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.Slider(androidx.compose.material3.SliderState, androidx.compose.ui.Modifier, boolean, androidx.compose.material3.SliderColors, androidx.compose.foundation.interaction.MutableInteractionSource, r7.q, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:159:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void RangeSlider(androidx.compose.material3.RangeSliderState r22, androidx.compose.ui.Modifier r23, boolean r24, androidx.compose.material3.SliderColors r25, androidx.compose.foundation.interaction.MutableInteractionSource r26, androidx.compose.foundation.interaction.MutableInteractionSource r27, r7.q<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r28, r7.q<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r29, r7.q<? super androidx.compose.material3.RangeSliderState, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 604
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SliderKt.RangeSlider(androidx.compose.material3.RangeSliderState, androidx.compose.ui.Modifier, boolean, androidx.compose.material3.SliderColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.foundation.interaction.MutableInteractionSource, r7.q, r7.q, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }
}
