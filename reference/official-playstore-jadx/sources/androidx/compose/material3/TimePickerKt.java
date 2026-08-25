package androidx.compose.material3;

import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.Selection;
import androidx.compose.material3.Strings;
import androidx.compose.material3.tokens.TimeInputTokens;
import androidx.compose.material3.tokens.TimePickerTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpOffset;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import com.google.common.util.concurrent.r0;
import d7.k;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a8\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006H\u0007ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a+\u0010\f\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\f\u0010\r\u001a-\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u0011H\u0007¢\u0006\u0004\b\u0013\u0010\u0014\u001a3\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0011H\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u001a3\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0011H\u0001¢\u0006\u0004\b\u0018\u0010\u0017\u001a'\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u0000H\u0003¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001f\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0003¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001f\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0003¢\u0006\u0004\b\u001d\u0010\u001c\u001a\u001f\u0010\u001e\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0003¢\u0006\u0004\b\u001e\u0010\u001c\u001a'\u0010\u001f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0003¢\u0006\u0004\b\u001f\u0010 \u001a'\u0010!\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0003¢\u0006\u0004\b!\u0010 \u001a?\u0010'\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\"2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$H\u0003¢\u0006\u0004\b'\u0010(\u001aS\u00102\u001a\u00020\b2\u0006\u0010)\u001a\u00020\u00112\u0006\u0010*\u001a\u00020$2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\b0+2\u0006\u0010\u0005\u001a\u00020\u00042\u001c\u00101\u001a\u0018\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\b0-¢\u0006\u0002\b/¢\u0006\u0002\b0H\u0003¢\u0006\u0004\b2\u00103\u001a\u0017\u00104\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0003¢\u0006\u0004\b4\u00105\u001a:\u0010;\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u00106\u001a\u00020\u000e2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u00108\u001a\u0002072\u0006\u0010\u0005\u001a\u00020\u0004H\u0003ø\u0001\u0000¢\u0006\u0004\b9\u0010:\u001a'\u0010<\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0011H\u0001¢\u0006\u0004\b<\u0010=\u001a#\u0010>\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b>\u0010?\u001a/\u0010@\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u00106\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0011H\u0003¢\u0006\u0004\b@\u0010A\u001a]\u0010J\u001a\u00020\b2\u0006\u00108\u001a\u0002072\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u00106\u001a\u00020B2\u0006\u0010C\u001a\u00020B2\u0006\u0010D\u001a\u00020\u000e2!\u0010G\u001a\u001d\u0012\u0013\u0012\u00110B¢\u0006\f\bE\u0012\b\bF\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\b0-H\u0002ø\u0001\u0000¢\u0006\u0004\bH\u0010I\u001ab\u0010R\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u00106\u001a\u00020B2\u0012\u0010K\u001a\u000e\u0012\u0004\u0012\u00020B\u0012\u0004\u0012\u00020\b0-2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u00108\u001a\u0002072\b\b\u0002\u0010M\u001a\u00020L2\b\b\u0002\u0010O\u001a\u00020N2\u0006\u0010\u0005\u001a\u00020\u0004H\u0003ø\u0001\u0000¢\u0006\u0004\bP\u0010Q\u001a7\u0010W\u001a\u00020\b2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010T\u001a\u00020S2\u0011\u00101\u001a\r\u0012\u0004\u0012\u00020\b0+¢\u0006\u0002\b/H\u0003ø\u0001\u0000¢\u0006\u0004\bU\u0010V\u001a*\u0010\\\u001a\u00020Y2\u0006\u00108\u001a\u0002072\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010X\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\bZ\u0010[\u001a+\u0010a\u001a\u000e\u0012\u0004\u0012\u00020]\u0012\u0004\u0012\u00020]0`2\u0006\u0010^\u001a\u00020]2\u0006\u0010_\u001a\u00020]H\u0002¢\u0006\u0004\ba\u0010b\u001a/\u0010g\u001a\u00020]2\u0006\u0010c\u001a\u00020]2\u0006\u0010d\u001a\u00020]2\u0006\u0010e\u001a\u00020\u000e2\u0006\u0010f\u001a\u00020\u000eH\u0002¢\u0006\u0004\bg\u0010h\u001a\u001f\u0010k\u001a\u00020]2\u0006\u0010i\u001a\u00020]2\u0006\u0010j\u001a\u00020]H\u0002¢\u0006\u0004\bk\u0010l\u001a\u001b\u0010m\u001a\u00020\u0002*\u00020\u00022\u0006\u0010m\u001a\u00020\u0011H\u0003¢\u0006\u0004\bm\u0010n\"\u0014\u0010o\u001a\u00020]8\u0002X\u0082T¢\u0006\u0006\n\u0004\bo\u0010p\"\u0014\u0010r\u001a\u00020q8\u0002X\u0082T¢\u0006\u0006\n\u0004\br\u0010s\"\u0014\u0010t\u001a\u00020]8\u0002X\u0082T¢\u0006\u0006\n\u0004\bt\u0010p\"\u0014\u0010u\u001a\u00020]8\u0002X\u0082T¢\u0006\u0006\n\u0004\bu\u0010p\"\u0014\u0010v\u001a\u00020]8\u0002X\u0082T¢\u0006\u0006\n\u0004\bv\u0010p\"\u0014\u0010w\u001a\u00020S8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bw\u0010p\"\u0014\u0010x\u001a\u00020S8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bx\u0010p\"\u0014\u0010y\u001a\u00020S8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\by\u0010p\"\u0014\u0010z\u001a\u00020S8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bz\u0010p\"\u0014\u0010{\u001a\u00020S8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b{\u0010p\"\u0014\u0010|\u001a\u00020S8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b|\u0010p\"\u0014\u0010}\u001a\u00020S8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b}\u0010p\"\u0014\u0010~\u001a\u00020S8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b~\u0010p\"\u0014\u0010\u007f\u001a\u00020S8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u007f\u0010p\"\u001e\u0010\u0081\u0001\u001a\t\u0012\u0004\u0012\u00020\u000e0\u0080\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0081\u0001\u0010\u0082\u0001\"\u001e\u0010\u0083\u0001\u001a\t\u0012\u0004\u0012\u00020\u000e0\u0080\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0083\u0001\u0010\u0082\u0001\"\u001e\u0010\u0084\u0001\u001a\t\u0012\u0004\u0012\u00020\u000e0\u0080\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0084\u0001\u0010\u0082\u0001\"\u0016\u0010\u0085\u0001\u001a\u00020S8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0085\u0001\u0010p\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u008b\u0001²\u0006\r\u0010\u0086\u0001\u001a\u00020\u00118\nX\u008a\u0084\u0002²\u0006\u000f\u0010\u0087\u0001\u001a\u00020B8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0088\u0001\u001a\u00020B8\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010\u008a\u0001\u001a\u00030\u0089\u00018\n@\nX\u008a\u008e\u0002"}, d2 = {"Landroidx/compose/material3/TimePickerState;", "state", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/material3/TimePickerColors;", "colors", "Landroidx/compose/material3/TimePickerLayoutType;", "layoutType", "Lx6/t0;", "TimePicker-mT9BvqQ", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;ILandroidx/compose/runtime/Composer;II)V", "TimePicker", "TimeInput", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;II)V", "", "initialHour", "initialMinute", "", "is24Hour", "rememberTimePickerState", "(IIZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TimePickerState;", "autoSwitchToMinute", "VerticalTimePicker", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;ZLandroidx/compose/runtime/Composer;II)V", "HorizontalTimePicker", "TimeInputImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/material3/TimePickerState;Landroidx/compose/runtime/Composer;I)V", "HorizontalClockDisplay", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "VerticalClockDisplay", "ClockDisplayNumbers", "HorizontalPeriodToggle", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "VerticalPeriodToggle", "Landroidx/compose/ui/layout/MeasurePolicy;", "measurePolicy", "Landroidx/compose/ui/graphics/Shape;", "startShape", "endShape", "PeriodToggleImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/ui/layout/MeasurePolicy;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)V", "checked", "shape", "Lkotlin/Function0;", "onClick", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "ToggleItem", "(ZLandroidx/compose/ui/graphics/Shape;Lr7/a;Landroidx/compose/material3/TimePickerColors;Lr7/q;Landroidx/compose/runtime/Composer;I)V", "DisplaySeparator", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "value", "Landroidx/compose/material3/Selection;", "selection", "TimeSelector-uXwN82Y", "(Landroidx/compose/ui/Modifier;ILandroidx/compose/material3/TimePickerState;ILandroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "TimeSelector", "ClockFace", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;ZLandroidx/compose/runtime/Composer;I)V", "drawSelector", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;)Landroidx/compose/ui/Modifier;", "ClockText", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;IZLandroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/text/input/TextFieldValue;", "prevValue", "max", "Lx6/y;", ContentDisposition.Parameters.Name, "onNewValue", "timeInputOnChange-gIWD5Rc", "(ILandroidx/compose/material3/TimePickerState;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/TextFieldValue;ILr7/l;)V", "timeInputOnChange", "onValueChange", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardActions;", "keyboardActions", "TimePickerTextField-lxUZ_iY", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/input/TextFieldValue;Lr7/l;Landroidx/compose/material3/TimePickerState;ILandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;II)V", "TimePickerTextField", "Landroidx/compose/ui/unit/Dp;", "radius", "CircularLayout-uFdPcIQ", "(Landroidx/compose/ui/Modifier;FLr7/p;Landroidx/compose/runtime/Composer;II)V", "CircularLayout", "number", "", "numberContentDescription-YKJpE6Y", "(IZILandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "numberContentDescription", "", "current", "new", "Lx6/x;", "valuesForAnimation", "(FF)Lx6/x;", "x1", "y1", "x2", "y2", "dist", "(FFII)F", "y", "x", "atan", "(FF)F", "visible", "(Landroidx/compose/ui/Modifier;Z)Landroidx/compose/ui/Modifier;", "FullCircle", "F", "", "QuarterCircle", "D", "RadiansPerMinute", "RadiansPerHour", "SeparatorZIndex", "OuterCircleSizeRadius", "InnerCircleRadius", "ClockDisplayBottomMargin", "ClockFaceBottomMargin", "DisplaySeparatorWidth", "SupportLabelTop", "TimeInputBottomPadding", "MaxDistance", "MinimumInteractiveSize", "", "Minutes", "Ljava/util/List;", "Hours", "ExtraHours", "PeriodToggleMargin", "touchExplorationServicesEnabled", "hourValue", "minuteValue", "Landroidx/compose/ui/geometry/Offset;", TtmlNode.CENTER, "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TimePickerKt {
    private static final float ClockFaceBottomMargin;
    private static final float DisplaySeparatorWidth;
    private static final List<Integer> ExtraHours;
    private static final float FullCircle = 6.2831855f;
    private static final List<Integer> Hours;
    private static final float PeriodToggleMargin;
    private static final double QuarterCircle = 1.5707963267948966d;
    private static final float RadiansPerHour = 0.5235988f;
    private static final float RadiansPerMinute = 0.10471976f;
    private static final float SeparatorZIndex = 2.0f;
    private static final float TimeInputBottomPadding;
    private static final float OuterCircleSizeRadius = Dp.m5678constructorimpl(101);
    private static final float InnerCircleRadius = Dp.m5678constructorimpl(69);
    private static final float ClockDisplayBottomMargin = Dp.m5678constructorimpl(36);
    private static final float SupportLabelTop = Dp.m5678constructorimpl(7);
    private static final float MaxDistance = Dp.m5678constructorimpl(74);
    private static final float MinimumInteractiveSize = Dp.m5678constructorimpl(48);
    private static final List<Integer> Minutes = t7.a.E(0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55);

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockDisplayNumbers$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ TimePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(TimePickerState timePickerState, TimePickerColors timePickerColors) {
            super(2);
            this.$state = timePickerState;
            this.$colors = timePickerColors;
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
                ComposerKt.traceEventStart(-477913269, i10, -1, "androidx.compose.material3.ClockDisplayNumbers.<anonymous> (TimePicker.kt:966)");
            }
            TimePickerState timePickerState = this.$state;
            TimePickerColors timePickerColors = this.$colors;
            composer.startReplaceableGroup(693286680);
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyI = androidx.compose.foundation.c.i(Alignment.INSTANCE, Arrangement.INSTANCE.getStart(), composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
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
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyI, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            TimePickerTokens timePickerTokens = TimePickerTokens.INSTANCE;
            Modifier modifierM580sizeVpY3zN4 = SizeKt.m580sizeVpY3zN4(companion, timePickerTokens.m2880getTimeSelectorContainerWidthD9Ej5fM(), timePickerTokens.m2879getTimeSelectorContainerHeightD9Ej5fM());
            int hourForDisplay$material3_release = timePickerState.getHourForDisplay$material3_release();
            Selection.Companion companion3 = Selection.INSTANCE;
            TimePickerKt.m2182TimeSelectoruXwN82Y(modifierM580sizeVpY3zN4, hourForDisplay$material3_release, timePickerState, companion3.m1869getHourJiIwxys(), timePickerColors, composer, 3078);
            TimePickerKt.DisplaySeparator(SizeKt.m580sizeVpY3zN4(companion, TimePickerKt.DisplaySeparatorWidth, timePickerTokens.m2876getPeriodSelectorVerticalContainerHeightD9Ej5fM()), composer, 6);
            TimePickerKt.m2182TimeSelectoruXwN82Y(SizeKt.m580sizeVpY3zN4(companion, timePickerTokens.m2880getTimeSelectorContainerWidthD9Ej5fM(), timePickerTokens.m2879getTimeSelectorContainerHeightD9Ej5fM()), timePickerState.getMinute(), timePickerState, companion3.m1870getMinuteJiIwxys(), timePickerColors, composer, 3078);
            if (androidx.compose.foundation.c.D(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockDisplayNumbers$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ TimePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(TimePickerState timePickerState, TimePickerColors timePickerColors, int i10) {
            super(2);
            this.$state = timePickerState;
            this.$colors = timePickerColors;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TimePickerKt.ClockDisplayNumbers(this.$state, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05701 extends r implements l<SemanticsPropertyReceiver, t0> {
        public static final C05701 INSTANCE = new C05701();

        public C05701() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SemanticsPropertyReceiver) obj);
            return t0.f22605a;
        }

        public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            SemanticsPropertiesKt.selectableGroup(semanticsPropertyReceiver);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\u000b¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"", "", "screen", "Lx6/t0;", "invoke", "(Ljava/util/List;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05712 extends r implements q<List<? extends Integer>, Composer, Integer, t0> {
        final /* synthetic */ boolean $autoSwitchToMinute;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ TimePickerState $state;

        /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
            final /* synthetic */ boolean $autoSwitchToMinute;
            final /* synthetic */ TimePickerColors $colors;
            final /* synthetic */ List<Integer> $screen;
            final /* synthetic */ TimePickerState $state;

            /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$2$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class C01141 extends r implements p<Composer, Integer, t0> {
                final /* synthetic */ boolean $autoSwitchToMinute;
                final /* synthetic */ List<Integer> $screen;
                final /* synthetic */ TimePickerState $state;

                /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$2$1$1$2, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                public static final class C01152 extends r implements p<Composer, Integer, t0> {
                    final /* synthetic */ boolean $autoSwitchToMinute;
                    final /* synthetic */ TimePickerState $state;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C01152(TimePickerState timePickerState, boolean z) {
                        super(2);
                        this.$state = timePickerState;
                        this.$autoSwitchToMinute = z;
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
                            ComposerKt.traceEventStart(-448649404, i10, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1382)");
                        }
                        int size = TimePickerKt.ExtraHours.size();
                        TimePickerState timePickerState = this.$state;
                        boolean z = this.$autoSwitchToMinute;
                        for (int i11 = 0; i11 < size; i11++) {
                            int iIntValue = ((Number) TimePickerKt.ExtraHours.get(i11)).intValue();
                            Modifier.Companion companion = Modifier.INSTANCE;
                            composer.startReplaceableGroup(-1469917176);
                            boolean zChanged = composer.changed(i11);
                            Object objRememberedValue = composer.rememberedValue();
                            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new TimePickerKt$ClockFace$2$1$1$2$1$1$1(i11);
                                composer.updateRememberedValue(objRememberedValue);
                            }
                            composer.endReplaceableGroup();
                            TimePickerKt.ClockText(SemanticsModifierKt.semantics$default(companion, false, (l) objRememberedValue, 1, null), timePickerState, iIntValue, z, composer, 0);
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01141(List<Integer> list, TimePickerState timePickerState, boolean z) {
                    super(2);
                    this.$screen = list;
                    this.$state = timePickerState;
                    this.$autoSwitchToMinute = z;
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
                        ComposerKt.traceEventStart(-2018362505, i10, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1358)");
                    }
                    composer.startReplaceableGroup(-504293055);
                    int size = this.$screen.size();
                    TimePickerState timePickerState = this.$state;
                    List<Integer> list = this.$screen;
                    boolean z = this.$autoSwitchToMinute;
                    for (int i11 = 0; i11 < size; i11++) {
                        int iIntValue = (!timePickerState.getIs24hour() || Selection.m1865equalsimpl0(timePickerState.m2204getSelectionJiIwxys$material3_release(), Selection.INSTANCE.m1870getMinuteJiIwxys())) ? list.get(i11).intValue() : list.get(i11).intValue() % 12;
                        Modifier.Companion companion = Modifier.INSTANCE;
                        composer.startReplaceableGroup(-1916851139);
                        boolean zChanged = composer.changed(i11);
                        Object objRememberedValue = composer.rememberedValue();
                        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new TimePickerKt$ClockFace$2$1$1$1$1$1(i11);
                            composer.updateRememberedValue(objRememberedValue);
                        }
                        composer.endReplaceableGroup();
                        boolean z5 = z;
                        TimePickerKt.ClockText(SemanticsModifierKt.semantics$default(companion, false, (l) objRememberedValue, 1, null), timePickerState, iIntValue, z5, composer, 0);
                        z = z5;
                    }
                    composer.endReplaceableGroup();
                    if (Selection.m1865equalsimpl0(this.$state.m2204getSelectionJiIwxys$material3_release(), Selection.INSTANCE.m1869getHourJiIwxys()) && this.$state.getIs24hour()) {
                        TimePickerKt.m2179CircularLayoutuFdPcIQ(BackgroundKt.m178backgroundbw27NRU(SizeKt.m578size3ABfNKs(LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutId.InnerCircle), TimePickerTokens.INSTANCE.m2868getClockDialContainerSizeD9Ej5fM()), Color.INSTANCE.m3507getTransparent0d7_KjU(), RoundedCornerShapeKt.getCircleShape()), TimePickerKt.InnerCircleRadius, ComposableLambdaKt.composableLambda(composer, -448649404, true, new C01152(this.$state, this.$autoSwitchToMinute)), composer, 432, 0);
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(TimePickerColors timePickerColors, List<Integer> list, TimePickerState timePickerState, boolean z) {
                super(2);
                this.$colors = timePickerColors;
                this.$screen = list;
                this.$state = timePickerState;
                this.$autoSwitchToMinute = z;
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
                    ComposerKt.traceEventStart(-1385633737, i10, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous> (TimePicker.kt:1355)");
                }
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(this.$colors.m2157clockDialContentColorvNxB06k$material3_release(false))), ComposableLambdaKt.composableLambda(composer, -2018362505, true, new C01141(this.$screen, this.$state, this.$autoSwitchToMinute)), composer, ProvidedValue.$stable | 48);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05712(TimePickerState timePickerState, boolean z, TimePickerColors timePickerColors) {
            super(3);
            this.$state = timePickerState;
            this.$autoSwitchToMinute = z;
            this.$colors = timePickerColors;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((List<Integer>) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(List<Integer> list, Composer composer, int i10) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1628166511, i10, -1, "androidx.compose.material3.ClockFace.<anonymous> (TimePicker.kt:1348)");
            }
            TimePickerKt.m2179CircularLayoutuFdPcIQ(TimePickerKt.drawSelector(SizeKt.m578size3ABfNKs(Modifier.INSTANCE.then(new ClockDialModifier(this.$state, this.$autoSwitchToMinute)), TimePickerTokens.INSTANCE.m2868getClockDialContainerSizeD9Ej5fM()), this.$state, this.$colors), TimePickerKt.OuterCircleSizeRadius, ComposableLambdaKt.composableLambda(composer, -1385633737, true, new AnonymousClass1(this.$colors, list, this.$state, this.$autoSwitchToMinute)), composer, 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockFace$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ boolean $autoSwitchToMinute;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ TimePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(TimePickerState timePickerState, TimePickerColors timePickerColors, boolean z, int i10) {
            super(2);
            this.$state = timePickerState;
            this.$colors = timePickerColors;
            this.$autoSwitchToMinute = z;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TimePickerKt.ClockFace(this.$state, this.$colors, this.$autoSwitchToMinute, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ClockText$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ boolean $autoSwitchToMinute;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ TimePickerState $state;
        final /* synthetic */ int $value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(Modifier modifier, TimePickerState timePickerState, int i10, boolean z, int i11) {
            super(2);
            this.$modifier = modifier;
            this.$state = timePickerState;
            this.$value = i10;
            this.$autoSwitchToMinute = z;
            this.$$changed = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TimePickerKt.ClockText(this.$modifier, this.$state, this.$value, this.$autoSwitchToMinute, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$DisplaySeparator$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05721 extends r implements l<SemanticsPropertyReceiver, t0> {
        public static final C05721 INSTANCE = new C05721();

        public C05721() {
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

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$DisplaySeparator$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05733 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ Modifier $modifier;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05733(Modifier modifier, int i10) {
            super(2);
            this.$modifier = modifier;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TimePickerKt.DisplaySeparator(this.$modifier, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$HorizontalClockDisplay$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05742 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ TimePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05742(TimePickerState timePickerState, TimePickerColors timePickerColors, int i10) {
            super(2);
            this.$state = timePickerState;
            this.$colors = timePickerColors;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TimePickerKt.HorizontalClockDisplay(this.$state, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$HorizontalPeriodToggle$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05751 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ TimePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05751(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, int i10) {
            super(2);
            this.$modifier = modifier;
            this.$state = timePickerState;
            this.$colors = timePickerColors;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TimePickerKt.HorizontalPeriodToggle(this.$modifier, this.$state, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$HorizontalTimePicker$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05762 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ boolean $autoSwitchToMinute;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ TimePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05762(TimePickerState timePickerState, Modifier modifier, TimePickerColors timePickerColors, boolean z, int i10, int i11) {
            super(2);
            this.$state = timePickerState;
            this.$modifier = modifier;
            this.$colors = timePickerColors;
            this.$autoSwitchToMinute = z;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TimePickerKt.HorizontalTimePicker(this.$state, this.$modifier, this.$colors, this.$autoSwitchToMinute, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$PeriodToggleImpl$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05773 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ Shape $endShape;
        final /* synthetic */ MeasurePolicy $measurePolicy;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ Shape $startShape;
        final /* synthetic */ TimePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05773(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, MeasurePolicy measurePolicy, Shape shape, Shape shape2, int i10) {
            super(2);
            this.$modifier = modifier;
            this.$state = timePickerState;
            this.$colors = timePickerColors;
            this.$measurePolicy = measurePolicy;
            this.$startShape = shape;
            this.$endShape = shape2;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TimePickerKt.PeriodToggleImpl(this.$modifier, this.$state, this.$colors, this.$measurePolicy, this.$startShape, this.$endShape, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$TimeInput$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05781 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ TimePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05781(TimePickerState timePickerState, Modifier modifier, TimePickerColors timePickerColors, int i10, int i11) {
            super(2);
            this.$state = timePickerState;
            this.$modifier = modifier;
            this.$colors = timePickerColors;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TimePickerKt.TimeInput(this.$state, this.$modifier, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$TimeInputImpl$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05792 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ TimePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05792(Modifier modifier, TimePickerColors timePickerColors, TimePickerState timePickerState, int i10) {
            super(2);
            this.$modifier = modifier;
            this.$colors = timePickerColors;
            this.$state = timePickerState;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TimePickerKt.TimeInputImpl(this.$modifier, this.$colors, this.$state, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$ToggleItem$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05802 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ boolean $checked;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ q<RowScope, Composer, Integer, t0> $content;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ Shape $shape;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05802(boolean z, Shape shape, r7.a<t0> aVar, TimePickerColors timePickerColors, q<? super RowScope, ? super Composer, ? super Integer, t0> qVar, int i10) {
            super(2);
            this.$checked = z;
            this.$shape = shape;
            this.$onClick = aVar;
            this.$colors = timePickerColors;
            this.$content = qVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TimePickerKt.ToggleItem(this.$checked, this.$shape, this.$onClick, this.$colors, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$VerticalClockDisplay$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05812 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ TimePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05812(TimePickerState timePickerState, TimePickerColors timePickerColors, int i10) {
            super(2);
            this.$state = timePickerState;
            this.$colors = timePickerColors;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TimePickerKt.VerticalClockDisplay(this.$state, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$VerticalPeriodToggle$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05821 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ TimePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05821(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, int i10) {
            super(2);
            this.$modifier = modifier;
            this.$state = timePickerState;
            this.$colors = timePickerColors;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TimePickerKt.VerticalPeriodToggle(this.$modifier, this.$state, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$VerticalTimePicker$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05831 extends r implements l<SemanticsPropertyReceiver, t0> {
        public static final C05831 INSTANCE = new C05831();

        public C05831() {
            super(1);
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((SemanticsPropertyReceiver) obj);
            return t0.f22605a;
        }

        public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$VerticalTimePicker$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05843 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ boolean $autoSwitchToMinute;
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ TimePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05843(TimePickerState timePickerState, Modifier modifier, TimePickerColors timePickerColors, boolean z, int i10, int i11) {
            super(2);
            this.$state = timePickerState;
            this.$modifier = modifier;
            this.$colors = timePickerColors;
            this.$autoSwitchToMinute = z;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TimePickerKt.VerticalTimePicker(this.$state, this.$modifier, this.$colors, this.$autoSwitchToMinute, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$drawSelector$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05851 extends r implements l<ContentDrawScope, t0> {
        final /* synthetic */ TimePickerColors $colors;
        final /* synthetic */ TimePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05851(TimePickerState timePickerState, TimePickerColors timePickerColors) {
            super(1);
            this.$state = timePickerState;
            this.$colors = timePickerColors;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((ContentDrawScope) obj);
            return t0.f22605a;
        }

        public final void invoke(ContentDrawScope contentDrawScope) {
            long jOffset = OffsetKt.Offset(contentDrawScope.mo285toPx0680j_4(DpOffset.m5739getXD9Ej5fM(this.$state.m2205getSelectorPosRKDOV3M$material3_release())), contentDrawScope.mo285toPx0680j_4(DpOffset.m5741getYD9Ej5fM(this.$state.m2205getSelectorPosRKDOV3M$material3_release())));
            TimePickerTokens timePickerTokens = TimePickerTokens.INSTANCE;
            float f10 = 2;
            float fMo285toPx0680j_4 = contentDrawScope.mo285toPx0680j_4(timePickerTokens.m2870getClockDialSelectorHandleContainerSizeD9Ej5fM()) / f10;
            long selectorColor = this.$colors.getSelectorColor();
            long jM3498getBlack0d7_KjU = Color.INSTANCE.m3498getBlack0d7_KjU();
            BlendMode.Companion companion = BlendMode.INSTANCE;
            androidx.compose.ui.graphics.drawscope.c.x(contentDrawScope, jM3498getBlack0d7_KjU, fMo285toPx0680j_4, jOffset, 0.0f, null, null, companion.m3393getClear0nO6VwU(), 56, null);
            contentDrawScope.drawContent();
            androidx.compose.ui.graphics.drawscope.c.x(contentDrawScope, selectorColor, fMo285toPx0680j_4, jOffset, 0.0f, null, null, companion.m3421getXor0nO6VwU(), 56, null);
            androidx.compose.ui.graphics.drawscope.c.C(contentDrawScope, selectorColor, androidx.compose.ui.geometry.SizeKt.m3315getCenteruvyYCjk(contentDrawScope.mo3916getSizeNHjbRc()), Offset.m3240minusMKHz9U(jOffset, OffsetKt.Offset(((float) Math.cos(this.$state.getCurrentAngle$material3_release().getValue().floatValue())) * fMo285toPx0680j_4, ((float) Math.sin(this.$state.getCurrentAngle$material3_release().getValue().floatValue())) * fMo285toPx0680j_4)), contentDrawScope.mo285toPx0680j_4(timePickerTokens.m2871getClockDialSelectorTrackContainerWidthD9Ej5fM()), 0, null, 0.0f, null, companion.m3420getSrcOver0nO6VwU(), PsExtractor.VIDEO_STREAM_MASK, null);
            androidx.compose.ui.graphics.drawscope.c.x(contentDrawScope, selectorColor, contentDrawScope.mo285toPx0680j_4(timePickerTokens.m2869getClockDialSelectorCenterContainerSizeD9Ej5fM()) / f10, androidx.compose.ui.geometry.SizeKt.m3315getCenteruvyYCjk(contentDrawScope.mo3916getSizeNHjbRc()), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
            androidx.compose.ui.graphics.drawscope.c.x(contentDrawScope, this.$colors.m2157clockDialContentColorvNxB06k$material3_release(true), fMo285toPx0680j_4, jOffset, 0.0f, null, null, companion.m3403getDstOver0nO6VwU(), 56, null);
        }
    }

    static {
        float f10 = 24;
        ClockFaceBottomMargin = Dp.m5678constructorimpl(f10);
        DisplaySeparatorWidth = Dp.m5678constructorimpl(f10);
        TimeInputBottomPadding = Dp.m5678constructorimpl(f10);
        List<Integer> listE = t7.a.E(12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        Hours = listE;
        ArrayList arrayList = new ArrayList(listE.size());
        int size = listE.size();
        for (int i10 = 0; i10 < size; i10++) {
            arrayList.add(Integer.valueOf((listE.get(i10).intValue() % 12) + 12));
        }
        ExtraHours = arrayList;
        PeriodToggleMargin = Dp.m5678constructorimpl(12);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: CircularLayout-uFdPcIQ, reason: not valid java name */
    public static final void m2179CircularLayoutuFdPcIQ(Modifier modifier, final float f10, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(1548175696);
        int i13 = i11 & 1;
        if (i13 != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i11 & 2) != 0) {
            i12 |= 48;
        } else if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(f10) ? 32 : 16;
        }
        if ((i11 & 4) != 0) {
            i12 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 256 : 128;
        }
        if ((i12 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1548175696, i12, -1, "androidx.compose.material3.CircularLayout (TimePicker.kt:1679)");
            }
            composerStartRestartGroup.startReplaceableGroup(1651957759);
            boolean z = (i12 & 112) == 32;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.TimePickerKt$CircularLayout$1$1

                    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$CircularLayout$1$1$1, reason: invalid class name */
                    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                    public static final class AnonymousClass1 extends r implements l<Placeable.PlacementScope, t0> {
                        final /* synthetic */ long $constraints;
                        final /* synthetic */ Placeable $innerCirclePlaceable;
                        final /* synthetic */ List<Placeable> $placeables;
                        final /* synthetic */ float $radiusPx;
                        final /* synthetic */ Placeable $selectorPlaceable;
                        final /* synthetic */ float $theta;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        public AnonymousClass1(Placeable placeable, List<? extends Placeable> list, Placeable placeable2, long j10, float f10, float f11) {
                            super(1);
                            this.$selectorPlaceable = placeable;
                            this.$placeables = list;
                            this.$innerCirclePlaceable = placeable2;
                            this.$constraints = j10;
                            this.$radiusPx = f10;
                            this.$theta = f11;
                        }

                        @Override // r7.l
                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            invoke((Placeable.PlacementScope) obj);
                            return t0.f22605a;
                        }

                        public final void invoke(Placeable.PlacementScope placementScope) {
                            Placeable placeable = this.$selectorPlaceable;
                            if (placeable != null) {
                                Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, null);
                            }
                            List<Placeable> list = this.$placeables;
                            long j10 = this.$constraints;
                            float f10 = this.$radiusPx;
                            float f11 = this.$theta;
                            int size = list.size();
                            int i10 = 0;
                            while (i10 < size) {
                                Placeable placeable2 = list.get(i10);
                                double d4 = f10;
                                double d10 = ((double) (i10 * f11)) - 1.5707963267948966d;
                                Placeable.PlacementScope.place$default(placementScope, placeable2, t7.a.L((Math.cos(d10) * d4) + ((double) ((Constraints.m5646getMaxWidthimpl(j10) / 2) - (placeable2.getWidth() / 2)))), t7.a.L((Math.sin(d10) * d4) + ((double) ((Constraints.m5645getMaxHeightimpl(j10) / 2) - (placeable2.getHeight() / 2)))), 0.0f, 4, null);
                                i10++;
                                list = list;
                                j10 = j10;
                            }
                            Placeable placeable3 = this.$innerCirclePlaceable;
                            if (placeable3 != null) {
                                Placeable.PlacementScope.place$default(placementScope, placeable3, androidx.compose.foundation.c.B(this.$innerCirclePlaceable, Constraints.m5648getMinWidthimpl(this.$constraints), 2), androidx.compose.foundation.c.f(this.$innerCirclePlaceable, Constraints.m5647getMinHeightimpl(this.$constraints), 2), 0.0f, 4, null);
                            }
                        }
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                        return androidx.compose.ui.layout.g.a(this, intrinsicMeasureScope, list, i14);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                        return androidx.compose.ui.layout.g.b(this, intrinsicMeasureScope, list, i14);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
                        Measurable measurable;
                        Measurable measurable2;
                        float fMo285toPx0680j_4 = measureScope.mo285toPx0680j_4(f10);
                        long jM5637copyZbe2FdA$default = Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 10, null);
                        ArrayList arrayList = new ArrayList(list.size());
                        int size = list.size();
                        int i14 = 0;
                        for (int i15 = 0; i15 < size; i15++) {
                            Measurable measurable3 = list.get(i15);
                            Measurable measurable4 = measurable3;
                            if (LayoutIdKt.getLayoutId(measurable4) != LayoutId.Selector && LayoutIdKt.getLayoutId(measurable4) != LayoutId.InnerCircle) {
                                arrayList.add(measurable3);
                            }
                        }
                        ArrayList arrayList2 = new ArrayList(arrayList.size());
                        int size2 = arrayList.size();
                        for (int i16 = 0; i16 < size2; i16++) {
                            arrayList2.add(((Measurable) arrayList.get(i16)).mo4631measureBRTryo0(jM5637copyZbe2FdA$default));
                        }
                        int size3 = list.size();
                        int i17 = 0;
                        while (true) {
                            if (i17 >= size3) {
                                measurable = null;
                                break;
                            }
                            measurable = list.get(i17);
                            if (LayoutIdKt.getLayoutId(measurable) == LayoutId.Selector) {
                                break;
                            }
                            i17++;
                        }
                        Measurable measurable5 = measurable;
                        int size4 = list.size();
                        while (true) {
                            if (i14 >= size4) {
                                measurable2 = null;
                                break;
                            }
                            measurable2 = list.get(i14);
                            if (LayoutIdKt.getLayoutId(measurable2) == LayoutId.InnerCircle) {
                                break;
                            }
                            i14++;
                        }
                        Measurable measurable6 = measurable2;
                        return MeasureScope.CC.q(measureScope, Constraints.m5648getMinWidthimpl(j10), Constraints.m5647getMinHeightimpl(j10), null, new AnonymousClass1(measurable5 != null ? measurable5.mo4631measureBRTryo0(jM5637copyZbe2FdA$default) : null, arrayList2, measurable6 != null ? measurable6.mo4631measureBRTryo0(jM5637copyZbe2FdA$default) : null, j10, fMo285toPx0680j_4, 6.2831855f / arrayList2.size()), 4, null);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                        return androidx.compose.ui.layout.g.c(this, intrinsicMeasureScope, list, i14);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                        return androidx.compose.ui.layout.g.d(this, intrinsicMeasureScope, list, i14);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            composerStartRestartGroup.endReplaceableGroup();
            int i14 = ((i12 >> 6) & 14) | ((i12 << 3) & 112);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier);
            int i15 = ((i14 << 9) & 7168) | 6;
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
            p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            pVar.invoke(composerStartRestartGroup, Integer.valueOf((i15 >> 9) & 14));
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        Modifier modifier2 = modifier;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new TimePickerKt$CircularLayout$2(modifier2, f10, pVar, i10, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ClockDisplayNumbers(TimePickerState timePickerState, TimePickerColors timePickerColors, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-934561141);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(timePickerState) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(timePickerColors) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-934561141, i11, -1, "androidx.compose.material3.ClockDisplayNumbers (TimePicker.kt:962)");
            }
            CompositionLocalKt.CompositionLocalProvider(TextKt.getLocalTextStyle().provides(TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6), TimePickerTokens.INSTANCE.getTimeSelectorLabelTextFont())), ComposableLambdaKt.composableLambda(composerStartRestartGroup, -477913269, true, new AnonymousClass1(timePickerState, timePickerColors)), composerStartRestartGroup, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass2(timePickerState, timePickerColors, i10));
        }
    }

    public static final void ClockFace(TimePickerState timePickerState, TimePickerColors timePickerColors, boolean z, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1525091100);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(timePickerState) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(timePickerColors) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1525091100, i11, -1, "androidx.compose.material3.ClockFace (TimePicker.kt:1339)");
            }
            CrossfadeKt.Crossfade(timePickerState.getValues$material3_release(), SemanticsModifierKt.semantics$default(SizeKt.m578size3ABfNKs(BackgroundKt.m178backgroundbw27NRU(Modifier.INSTANCE, timePickerColors.getClockDialColor(), RoundedCornerShapeKt.getCircleShape()), TimePickerTokens.INSTANCE.m2868getClockDialContainerSizeD9Ej5fM()), false, C05701.INSTANCE, 1, null), AnimationSpecKt.tween$default(350, 0, null, 6, null), (String) null, ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1628166511, true, new C05712(timePickerState, z, timePickerColors)), composerStartRestartGroup, 24576, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass3(timePickerState, timePickerColors, z, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ClockText(Modifier modifier, TimePickerState timePickerState, int i10, boolean z, Composer composer, int i11) {
        int i12;
        int i13;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1420123631);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(timePickerState) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(i10) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        int i14 = i12;
        if ((i14 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1420123631, i14, -1, "androidx.compose.material3.ClockText (TimePicker.kt:1467)");
            }
            TextStyle textStyleFromToken = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6), TimePickerTokens.INSTANCE.getClockDialLabelTextFont());
            float fMo285toPx0680j_4 = ((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo285toPx0680j_4(MaxDistance);
            composerStartRestartGroup.startReplaceableGroup(-1652988653);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m3225boximpl(Offset.INSTANCE.m3252getZeroF1C5BW0()), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState = (MutableState) objRememberedValue;
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(773894976);
            composerStartRestartGroup.startReplaceableGroup(-492369756);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = com.arflix.tv.data.repository.g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composerStartRestartGroup), composerStartRestartGroup);
            }
            composerStartRestartGroup.endReplaceableGroup();
            k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
            composerStartRestartGroup.endReplaceableGroup();
            String strM2187numberContentDescriptionYKJpE6Y = m2187numberContentDescriptionYKJpE6Y(timePickerState.m2204getSelectionJiIwxys$material3_release(), timePickerState.getIs24hour(), i10, composerStartRestartGroup, i14 & 896);
            String localString$default = ActualJvm_jvmKt.toLocalString$default(i10, 0, 0, false, 7, null);
            boolean zA = Selection.m1865equalsimpl0(timePickerState.m2204getSelectionJiIwxys$material3_release(), Selection.INSTANCE.m1870getMinuteJiIwxys()) ? kotlin.jvm.internal.p.a(ActualJvm_jvmKt.toLocalString$default(timePickerState.getMinute(), 0, 0, false, 7, null), localString$default) : kotlin.jvm.internal.p.a(ActualJvm_jvmKt.toLocalString$default(timePickerState.getHour(), 0, 0, false, 7, null), localString$default);
            Alignment center = Alignment.INSTANCE.getCenter();
            Modifier modifierM578size3ABfNKs = SizeKt.m578size3ABfNKs(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier), MinimumInteractiveSize);
            composerStartRestartGroup.startReplaceableGroup(-1652987978);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new TimePickerKt$ClockText$1$1(mutableState);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierFocusable$default = FocusableKt.focusable$default(OnGloballyPositionedModifierKt.onGloballyPositioned(modifierM578size3ABfNKs, (l) objRememberedValue3), false, null, 3, null);
            composerStartRestartGroup.startReplaceableGroup(-1652987865);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(coroutineScope) | ((i14 & 112) == 32) | composerStartRestartGroup.changed(fMo285toPx0680j_4) | ((i14 & 7168) == 2048) | composerStartRestartGroup.changed(zA);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue4 == companion.getEmpty()) {
                boolean z5 = zA;
                i13 = 6;
                TimePickerKt$ClockText$2$1 timePickerKt$ClockText$2$1 = new TimePickerKt$ClockText$2$1(z5, coroutineScope, timePickerState, fMo285toPx0680j_4, z, mutableState);
                composerStartRestartGroup.updateRememberedValue(timePickerKt$ClockText$2$1);
                objRememberedValue4 = timePickerKt$ClockText$2$1;
            } else {
                i13 = 6;
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierSemantics = SemanticsModifierKt.semantics(modifierFocusable$default, true, (l) objRememberedValue4);
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, i13);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierSemantics);
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
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Modifier.Companion companion3 = Modifier.INSTANCE;
            composerStartRestartGroup.startReplaceableGroup(859631475);
            boolean zChanged = composerStartRestartGroup.changed(strM2187numberContentDescriptionYKJpE6Y);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = new TimePickerKt$ClockText$3$1$1(strM2187numberContentDescriptionYKJpE6Y);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            composerStartRestartGroup.endReplaceableGroup();
            composer2 = composerStartRestartGroup;
            TextKt.m2153Text4IGK_g(localString$default, SemanticsModifierKt.clearAndSetSemantics(companion3, (l) objRememberedValue5), 0L, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, textStyleFromToken, composer2, 0, 0, 65532);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass4(modifier, timePickerState, i10, z, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long ClockText$lambda$28(MutableState<Offset> mutableState) {
        return mutableState.getValue().getPackedValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ClockText$lambda$29(MutableState<Offset> mutableState, long j10) {
        mutableState.setValue(Offset.m3225boximpl(j10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DisplaySeparator(Modifier modifier, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(2100674302);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2100674302, i11, -1, "androidx.compose.material3.DisplaySeparator (TimePicker.kt:1165)");
            }
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default((TextStyle) composerStartRestartGroup.consume(TextKt.getLocalTextStyle()), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5582getCentere0LSkKk(), 0, 0L, null, null, new LineHeightStyle(LineHeightStyle.Alignment.INSTANCE.m5559getCenterPIaL0Z0(), LineHeightStyle.Trim.INSTANCE.m5571getBothEVpEnUU(), null), 0, 0, null, 15695871, null);
            Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(modifier, C05721.INSTANCE);
            Alignment center = Alignment.INSTANCE.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierClearAndSetSemantics);
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
            p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m2153Text4IGK_g(":", (Modifier) null, ColorSchemeKt.getValue(TimeInputTokens.INSTANCE.getTimeFieldSeparatorColor(), composerStartRestartGroup, 6), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, textStyleM5205copyp1EtxEg$default, composer2, 6, 0, 65530);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C05733(modifier, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HorizontalClockDisplay(TimePickerState timePickerState, TimePickerColors timePickerColors, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(755539561);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(timePickerState) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(timePickerColors) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(755539561, i11, -1, "androidx.compose.material3.HorizontalClockDisplay (TimePicker.kt:921)");
            }
            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            Modifier.Companion companion = Modifier.INSTANCE;
            Alignment.Companion companion2 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyJ = androidx.compose.foundation.c.j(companion2, center, composerStartRestartGroup, 6, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
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
            p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyJ, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ClockDisplayNumbers(timePickerState, timePickerColors, composerStartRestartGroup, i11 & 126);
            composerStartRestartGroup.startReplaceableGroup(-552392411);
            if (!timePickerState.getIs24hour()) {
                Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(companion, 0.0f, PeriodToggleMargin, 0.0f, 0.0f, 13, null);
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composerStartRestartGroup, 0, -1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default);
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
                p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                TimePickerTokens timePickerTokens = TimePickerTokens.INSTANCE;
                int i12 = i11 << 3;
                HorizontalPeriodToggle(SizeKt.m580sizeVpY3zN4(companion, timePickerTokens.m2874getPeriodSelectorHorizontalContainerWidthD9Ej5fM(), timePickerTokens.m2873getPeriodSelectorHorizontalContainerHeightD9Ej5fM()), timePickerState, timePickerColors, composerStartRestartGroup, (i12 & 896) | (i12 & 112) | 6);
                androidx.compose.foundation.c.y(composerStartRestartGroup);
            }
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C05742(timePickerState, timePickerColors, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HorizontalPeriodToggle(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, Composer composer, int i10) {
        int i11;
        Modifier modifier2;
        TimePickerState timePickerState2;
        TimePickerColors timePickerColors2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1261215927);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(timePickerState) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(timePickerColors) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
            timePickerState2 = timePickerState;
            timePickerColors2 = timePickerColors;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1261215927, i11, -1, "androidx.compose.material3.HorizontalPeriodToggle (TimePicker.kt:1002)");
            }
            composerStartRestartGroup.startReplaceableGroup(759555873);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.TimePickerKt$HorizontalPeriodToggle$measurePolicy$1$1

                    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$HorizontalPeriodToggle$measurePolicy$1$1$1, reason: invalid class name */
                    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                    public static final class AnonymousClass1 extends r implements l<Placeable.PlacementScope, t0> {
                        final /* synthetic */ List<Placeable> $items;
                        final /* synthetic */ Placeable $spacerPlaceable;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        public AnonymousClass1(List<? extends Placeable> list, Placeable placeable) {
                            super(1);
                            this.$items = list;
                            this.$spacerPlaceable = placeable;
                        }

                        @Override // r7.l
                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            invoke((Placeable.PlacementScope) obj);
                            return t0.f22605a;
                        }

                        public final void invoke(Placeable.PlacementScope placementScope) {
                            Placeable.PlacementScope.place$default(placementScope, this.$items.get(0), 0, 0, 0.0f, 4, null);
                            Placeable.PlacementScope.place$default(placementScope, this.$items.get(1), this.$items.get(0).getWidth(), 0, 0.0f, 4, null);
                            Placeable.PlacementScope.place$default(placementScope, this.$spacerPlaceable, this.$items.get(0).getWidth() - (this.$spacerPlaceable.getWidth() / 2), 0, 0.0f, 4, null);
                        }
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return androidx.compose.ui.layout.g.a(this, intrinsicMeasureScope, list, i12);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return androidx.compose.ui.layout.g.b(this, intrinsicMeasureScope, list, i12);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
                        int size = list.size();
                        for (int i12 = 0; i12 < size; i12++) {
                            Measurable measurable = list.get(i12);
                            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable), "Spacer")) {
                                Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j10, 0, measureScope.mo279roundToPx0680j_4(TimePickerTokens.INSTANCE.m2875getPeriodSelectorOutlineWidthD9Ej5fM()), 0, 0, 12, null));
                                ArrayList arrayList = new ArrayList(list.size());
                                int size2 = list.size();
                                for (int i13 = 0; i13 < size2; i13++) {
                                    Measurable measurable2 = list.get(i13);
                                    if (!kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable2), "Spacer")) {
                                        arrayList.add(measurable2);
                                    }
                                }
                                ArrayList arrayList2 = new ArrayList(arrayList.size());
                                int size3 = arrayList.size();
                                for (int i14 = 0; i14 < size3; i14++) {
                                    arrayList2.add(((Measurable) arrayList.get(i14)).mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j10, 0, Constraints.m5646getMaxWidthimpl(j10) / 2, 0, 0, 12, null)));
                                }
                                return MeasureScope.CC.q(measureScope, Constraints.m5646getMaxWidthimpl(j10), Constraints.m5645getMaxHeightimpl(j10), null, new AnonymousClass1(arrayList2, placeableMo4631measureBRTryo0), 4, null);
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return androidx.compose.ui.layout.g.c(this, intrinsicMeasureScope, list, i12);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return androidx.compose.ui.layout.g.d(this, intrinsicMeasureScope, list, i12);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            composerStartRestartGroup.endReplaceableGroup();
            CornerBasedShape cornerBasedShape = (CornerBasedShape) ShapesKt.getValue(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), composerStartRestartGroup, 6);
            modifier2 = modifier;
            timePickerState2 = timePickerState;
            timePickerColors2 = timePickerColors;
            PeriodToggleImpl(modifier2, timePickerState2, timePickerColors2, measurePolicy, ShapesKt.start(cornerBasedShape), ShapesKt.end(cornerBasedShape), composerStartRestartGroup, (i11 & 14) | 3072 | (i11 & 112) | (i11 & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C05751(modifier2, timePickerState2, timePickerColors2, i10));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void HorizontalTimePicker(androidx.compose.material3.TimePickerState r18, androidx.compose.ui.Modifier r19, androidx.compose.material3.TimePickerColors r20, boolean r21, androidx.compose.runtime.Composer r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 394
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.HorizontalTimePicker(androidx.compose.material3.TimePickerState, androidx.compose.ui.Modifier, androidx.compose.material3.TimePickerColors, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PeriodToggleImpl(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, MeasurePolicy measurePolicy, Shape shape, Shape shape2, Composer composer, int i10) {
        int i11;
        Shape shape3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1374241901);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(timePickerState) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(timePickerColors) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changed(measurePolicy) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changed(shape) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            shape3 = shape2;
            i11 |= composerStartRestartGroup.changed(shape3) ? 131072 : 65536;
        } else {
            shape3 = shape2;
        }
        if ((74899 & i11) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1374241901, i11, -1, "androidx.compose.material3.PeriodToggleImpl (TimePicker.kt:1095)");
            }
            TimePickerTokens timePickerTokens = TimePickerTokens.INSTANCE;
            BorderStroke borderStrokeM207BorderStrokecXLIe8U = BorderStrokeKt.m207BorderStrokecXLIe8U(timePickerTokens.m2875getPeriodSelectorOutlineWidthD9Ej5fM(), timePickerColors.getPeriodSelectorBorderColor());
            CornerBasedShape cornerBasedShape = (CornerBasedShape) ShapesKt.getValue(timePickerTokens.getPeriodSelectorContainerShape(), composerStartRestartGroup, 6);
            Strings.Companion companion = Strings.INSTANCE;
            String strM1994getStringNWtq28 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_time_picker_period_toggle_description), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(-2008454294);
            boolean zChanged = composerStartRestartGroup.changed(strM1994getStringNWtq28);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new TimePickerKt$PeriodToggleImpl$1$1(strM1994getStringNWtq28);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierBorder = BorderKt.border(SelectableGroupKt.selectableGroup(SemanticsModifierKt.semantics$default(modifier, false, (l) objRememberedValue, 1, null)), borderStrokeM207BorderStrokecXLIe8U, cornerBasedShape);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierBorder);
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
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            boolean z = !timePickerState.isAfternoonToggle$material3_release();
            composerStartRestartGroup.startReplaceableGroup(1654477599);
            int i12 = i11 & 112;
            boolean z5 = i12 == 32;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new TimePickerKt$PeriodToggleImpl$2$1$1(timePickerState);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceableGroup();
            ComposableSingletons$TimePickerKt composableSingletons$TimePickerKt = ComposableSingletons$TimePickerKt.INSTANCE;
            int i13 = (i11 << 3) & 7168;
            int i14 = i11;
            ToggleItem(z, shape, (r7.a) objRememberedValue2, timePickerColors, composableSingletons$TimePickerKt.m1444getLambda1$material3_release(), composerStartRestartGroup, ((i11 >> 9) & 112) | 24576 | i13);
            SpacerKt.Spacer(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxSize$default(ZIndexModifierKt.zIndex(LayoutIdKt.layoutId(Modifier.INSTANCE, "Spacer"), 2.0f), 0.0f, 1, null), timePickerColors.getPeriodSelectorBorderColor(), null, 2, null), composerStartRestartGroup, 0);
            boolean z10 = false;
            boolean zIsAfternoonToggle$material3_release = timePickerState.isAfternoonToggle$material3_release();
            composerStartRestartGroup.startReplaceableGroup(1654478145);
            if (i12 == 32) {
                z10 = true;
            }
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new TimePickerKt$PeriodToggleImpl$2$2$1(timePickerState);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composerStartRestartGroup.endReplaceableGroup();
            ToggleItem(zIsAfternoonToggle$material3_release, shape3, (r7.a) objRememberedValue3, timePickerColors, composableSingletons$TimePickerKt.m1445getLambda2$material3_release(), composerStartRestartGroup, ((i14 >> 12) & 112) | 24576 | i13);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C05773(modifier, timePickerState, timePickerColors, measurePolicy, shape, shape2, i10));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TimeInput(androidx.compose.material3.TimePickerState r7, androidx.compose.ui.Modifier r8, androidx.compose.material3.TimePickerColors r9, androidx.compose.runtime.Composer r10, int r11, int r12) {
        /*
            r0 = -760850373(0xffffffffd2a6583b, float:-3.5722242E11)
            androidx.compose.runtime.Composer r10 = r10.startRestartGroup(r0)
            r1 = r12 & 1
            if (r1 == 0) goto Le
            r1 = r11 | 6
            goto L1e
        Le:
            r1 = r11 & 6
            if (r1 != 0) goto L1d
            boolean r1 = r10.changed(r7)
            if (r1 == 0) goto L1a
            r1 = 4
            goto L1b
        L1a:
            r1 = 2
        L1b:
            r1 = r1 | r11
            goto L1e
        L1d:
            r1 = r11
        L1e:
            r2 = r12 & 2
            if (r2 == 0) goto L25
            r1 = r1 | 48
            goto L35
        L25:
            r3 = r11 & 48
            if (r3 != 0) goto L35
            boolean r3 = r10.changed(r8)
            if (r3 == 0) goto L32
            r3 = 32
            goto L34
        L32:
            r3 = 16
        L34:
            r1 = r1 | r3
        L35:
            r3 = r11 & 384(0x180, float:5.38E-43)
            if (r3 != 0) goto L49
            r3 = r12 & 4
            if (r3 != 0) goto L46
            boolean r3 = r10.changed(r9)
            if (r3 == 0) goto L46
            r3 = 256(0x100, float:3.59E-43)
            goto L48
        L46:
            r3 = 128(0x80, float:1.8E-43)
        L48:
            r1 = r1 | r3
        L49:
            r3 = r1 & 147(0x93, float:2.06E-43)
            r4 = 146(0x92, float:2.05E-43)
            if (r3 != r4) goto L5c
            boolean r3 = r10.getSkipping()
            if (r3 != 0) goto L56
            goto L5c
        L56:
            r10.skipToGroupEnd()
        L59:
            r3 = r8
            r4 = r9
            goto La8
        L5c:
            r10.startDefaults()
            r3 = r11 & 1
            r4 = 6
            if (r3 == 0) goto L75
            boolean r3 = r10.getDefaultsInvalid()
            if (r3 == 0) goto L6b
            goto L75
        L6b:
            r10.skipToGroupEnd()
            r2 = r12 & 4
            if (r2 == 0) goto L84
        L72:
            r1 = r1 & (-897(0xfffffffffffffc7f, float:NaN))
            goto L84
        L75:
            if (r2 == 0) goto L79
            androidx.compose.ui.Modifier$Companion r8 = androidx.compose.ui.Modifier.INSTANCE
        L79:
            r2 = r12 & 4
            if (r2 == 0) goto L84
            androidx.compose.material3.TimePickerDefaults r9 = androidx.compose.material3.TimePickerDefaults.INSTANCE
            androidx.compose.material3.TimePickerColors r9 = r9.colors(r10, r4)
            goto L72
        L84:
            r10.endDefaults()
            boolean r2 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r2 == 0) goto L93
            r2 = -1
            java.lang.String r3 = "androidx.compose.material3.TimeInput (TimePicker.kt:248)"
            androidx.compose.runtime.ComposerKt.traceEventStart(r0, r1, r2, r3)
        L93:
            int r0 = r1 >> 3
            r0 = r0 & 126(0x7e, float:1.77E-43)
            int r1 = r1 << r4
            r1 = r1 & 896(0x380, float:1.256E-42)
            r0 = r0 | r1
            TimeInputImpl(r8, r9, r7, r10, r0)
            boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r0 == 0) goto L59
            androidx.compose.runtime.ComposerKt.traceEventEnd()
            goto L59
        La8:
            androidx.compose.runtime.ScopeUpdateScope r8 = r10.endRestartGroup()
            if (r8 == 0) goto Lb9
            androidx.compose.material3.TimePickerKt$TimeInput$1 r1 = new androidx.compose.material3.TimePickerKt$TimeInput$1
            r2 = r7
            r5 = r11
            r6 = r12
            r1.<init>(r2, r3, r4, r5, r6)
            r8.updateScope(r1)
        Lb9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.TimeInput(androidx.compose.material3.TimePickerState, androidx.compose.ui.Modifier, androidx.compose.material3.TimePickerColors, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TimeInputImpl(Modifier modifier, TimePickerColors timePickerColors, TimePickerState timePickerState, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-475657989);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(timePickerColors) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(timePickerState) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-475657989, i11, -1, "androidx.compose.material3.TimeInputImpl (TimePicker.kt:817)");
            }
            Object[] objArr = new Object[0];
            TextFieldValue.Companion companion = TextFieldValue.INSTANCE;
            Saver<TextFieldValue, Object> saver = companion.getSaver();
            composerStartRestartGroup.startReplaceableGroup(565122579);
            int i12 = i11 & 896;
            boolean z = i12 == 256;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new TimePickerKt$TimeInputImpl$hourValue$2$1(timePickerState);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            MutableState mutableStateRememberSaveable = RememberSaveableKt.rememberSaveable(objArr, (Saver) saver, (String) null, (r7.a) objRememberedValue, composerStartRestartGroup, 0, 4);
            Object[] objArr2 = new Object[0];
            Saver<TextFieldValue, Object> saver2 = companion.getSaver();
            composerStartRestartGroup.startReplaceableGroup(565122759);
            boolean z5 = i12 == 256;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new TimePickerKt$TimeInputImpl$minuteValue$2$1(timePickerState);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceableGroup();
            MutableState mutableStateRememberSaveable2 = RememberSaveableKt.rememberSaveable(objArr2, (Saver) saver2, (String) null, (r7.a) objRememberedValue2, composerStartRestartGroup, 0, 4);
            composer2 = composerStartRestartGroup;
            int i13 = i11;
            Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(modifier, 0.0f, 0.0f, 0.0f, TimeInputBottomPadding, 7, null);
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment.Vertical top = companion2.getTop();
            composer2.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyG = d.g(Arrangement.INSTANCE, top, composer2, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default);
            if (composer2.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor);
            } else {
                composer2.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer2);
            p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyG, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composer2, qVarModifierMaterializerOf, composer2, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            Typography typography = MaterialTheme.INSTANCE.getTypography(composer2, 6);
            TimeInputTokens timeInputTokens = TimeInputTokens.INSTANCE;
            CompositionLocalKt.CompositionLocalProvider(TextKt.getLocalTextStyle().provides(TextStyle.m5205copyp1EtxEg$default(TypographyKt.fromToken(typography, timeInputTokens.getTimeFieldLabelTextFont()), timePickerColors.m2176timeSelectorContentColorvNxB06k$material3_release(true), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5582getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744446, null)), ComposableLambdaKt.composableLambda(composer2, 1306700887, true, new TimePickerKt$TimeInputImpl$1$1(mutableStateRememberSaveable, timePickerState, timePickerColors, mutableStateRememberSaveable2)), composer2, ProvidedValue.$stable | 48);
            composer2.startReplaceableGroup(565126032);
            if (!timePickerState.getIs24hour()) {
                Modifier.Companion companion4 = Modifier.INSTANCE;
                Modifier modifierM533paddingqDBjuR0$default2 = PaddingKt.m533paddingqDBjuR0$default(companion4, PeriodToggleMargin, 0.0f, 0.0f, 0.0f, 14, null);
                composer2.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composer2, 0, -1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default2);
                if (composer2.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor2);
                } else {
                    composer2.useNode();
                }
                Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composer2);
                p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                a0.c.y(composer2, qVarModifierMaterializerOf2, composer2, 0, 2058660585);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                VerticalPeriodToggle(SizeKt.m580sizeVpY3zN4(companion4, timeInputTokens.m2863getPeriodSelectorContainerWidthD9Ej5fM(), timeInputTokens.m2862getPeriodSelectorContainerHeightD9Ej5fM()), timePickerState, timePickerColors, composer2, ((i13 >> 3) & 112) | 6 | ((i13 << 3) & 896));
                androidx.compose.foundation.c.y(composer2);
            }
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C05792(modifier, timePickerColors, timePickerState, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$lambda$5(MutableState<TextFieldValue> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$lambda$8(MutableState<TextFieldValue> mutableState) {
        return mutableState.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0110  */
    /* JADX INFO: renamed from: TimePicker-mT9BvqQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2180TimePickermT9BvqQ(androidx.compose.material3.TimePickerState r13, androidx.compose.ui.Modifier r14, androidx.compose.material3.TimePickerColors r15, int r16, androidx.compose.runtime.Composer r17, int r18, int r19) {
        /*
            Method dump skipped, instruction units count: 296
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.m2180TimePickermT9BvqQ(androidx.compose.material3.TimePickerState, androidx.compose.ui.Modifier, androidx.compose.material3.TimePickerColors, int, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x04fd  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x04ff  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0507  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x051a  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0534  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x053d  */
    /* JADX WARN: Removed duplicated region for block: B:176:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0138  */
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
    /* JADX INFO: renamed from: TimePickerTextField-lxUZ_iY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2181TimePickerTextFieldlxUZ_iY(androidx.compose.ui.Modifier r122, androidx.compose.ui.text.input.TextFieldValue r123, r7.l<? super androidx.compose.ui.text.input.TextFieldValue, x6.t0> r124, androidx.compose.material3.TimePickerState r125, int r126, androidx.compose.foundation.text.KeyboardOptions r127, androidx.compose.foundation.text.KeyboardActions r128, androidx.compose.material3.TimePickerColors r129, androidx.compose.runtime.Composer r130, int r131, int r132) {
        /*
            Method dump skipped, instruction units count: 1363
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.m2181TimePickerTextFieldlxUZ_iY(androidx.compose.ui.Modifier, androidx.compose.ui.text.input.TextFieldValue, r7.l, androidx.compose.material3.TimePickerState, int, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, androidx.compose.material3.TimePickerColors, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean TimePicker_mT9BvqQ$lambda$0(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: TimeSelector-uXwN82Y, reason: not valid java name */
    public static final void m2182TimeSelectoruXwN82Y(Modifier modifier, int i10, TimePickerState timePickerState, int i11, TimePickerColors timePickerColors, Composer composer, int i12) {
        int i13;
        int iM1925constructorimpl;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(21099367);
        if ((i12 & 6) == 0) {
            i13 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i12;
        } else {
            i13 = i12;
        }
        if ((i12 & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        if ((i12 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changed(timePickerState) ? 256 : 128;
        }
        if ((i12 & 3072) == 0) {
            i13 |= composerStartRestartGroup.changed(i11) ? 2048 : 1024;
        }
        if ((i12 & 24576) == 0) {
            i13 |= composerStartRestartGroup.changed(timePickerColors) ? 16384 : 8192;
        }
        if ((i13 & 9363) == 9362 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(21099367, i13, -1, "androidx.compose.material3.TimeSelector (TimePicker.kt:1194)");
            }
            boolean zM1865equalsimpl0 = Selection.m1865equalsimpl0(timePickerState.m2204getSelectionJiIwxys$material3_release(), i11);
            if (Selection.m1865equalsimpl0(i11, Selection.INSTANCE.m1869getHourJiIwxys())) {
                Strings.Companion companion = Strings.INSTANCE;
                iM1925constructorimpl = Strings.m1925constructorimpl(R.string.m3c_time_picker_hour_selection);
            } else {
                Strings.Companion companion2 = Strings.INSTANCE;
                iM1925constructorimpl = Strings.m1925constructorimpl(R.string.m3c_time_picker_minute_selection);
            }
            String strM1994getStringNWtq28 = Strings_androidKt.m1994getStringNWtq28(iM1925constructorimpl, composerStartRestartGroup, 0);
            long jM2175timeSelectorContainerColorvNxB06k$material3_release = timePickerColors.m2175timeSelectorContainerColorvNxB06k$material3_release(zM1865equalsimpl0);
            long jM2176timeSelectorContentColorvNxB06k$material3_release = timePickerColors.m2176timeSelectorContentColorvNxB06k$material3_release(zM1865equalsimpl0);
            composerStartRestartGroup.startReplaceableGroup(773894976);
            composerStartRestartGroup.startReplaceableGroup(-492369756);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion3 = Composer.INSTANCE;
            if (objRememberedValue == companion3.getEmpty()) {
                objRememberedValue = com.arflix.tv.data.repository.g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composerStartRestartGroup), composerStartRestartGroup);
            }
            composerStartRestartGroup.endReplaceableGroup();
            k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(-633372797);
            boolean zChanged = composerStartRestartGroup.changed(strM1994getStringNWtq28);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == companion3.getEmpty()) {
                objRememberedValue2 = new TimePickerKt$TimeSelector$1$1(strM1994getStringNWtq28);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierSemantics = SemanticsModifierKt.semantics(modifier, true, (l) objRememberedValue2);
            Shape value = ShapesKt.getValue(TimePickerTokens.INSTANCE.getTimeSelectorContainerShape(), composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-633372653);
            boolean zChangedInstance = ((i13 & 7168) == 2048) | ((i13 & 896) == 256) | composerStartRestartGroup.changedInstance(coroutineScope);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue3 == companion3.getEmpty()) {
                objRememberedValue3 = new TimePickerKt$TimeSelector$2$1(i11, timePickerState, coroutineScope);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composerStartRestartGroup.endReplaceableGroup();
            composer2 = composerStartRestartGroup;
            SurfaceKt.m2005Surfaced85dljk(zM1865equalsimpl0, (r7.a<t0>) objRememberedValue3, modifierSemantics, false, value, jM2175timeSelectorContainerColorvNxB06k$material3_release, 0L, 0.0f, 0.0f, (BorderStroke) null, (MutableInteractionSource) null, (p<? super Composer, ? super Integer, t0>) ComposableLambdaKt.composableLambda(composerStartRestartGroup, -1338709103, true, new TimePickerKt$TimeSelector$3(i11, timePickerState, i10, jM2176timeSelectorContentColorvNxB06k$material3_release)), composer2, 0, 48, 1992);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new TimePickerKt$TimeSelector$4(modifier, i10, timePickerState, i11, timePickerColors, i12));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ToggleItem(boolean z, Shape shape, r7.a<t0> aVar, TimePickerColors timePickerColors, q<? super RowScope, ? super Composer, ? super Integer, t0> qVar, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1937408098);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(shape) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changed(timePickerColors) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(qVar) ? 16384 : 8192;
        }
        if ((i11 & 9363) == 9362 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1937408098, i11, -1, "androidx.compose.material3.ToggleItem (TimePicker.kt:1144)");
            }
            long jM2174periodSelectorContentColorvNxB06k$material3_release = timePickerColors.m2174periodSelectorContentColorvNxB06k$material3_release(z);
            long jM2173periodSelectorContainerColorvNxB06k$material3_release = timePickerColors.m2173periodSelectorContainerColorvNxB06k$material3_release(z);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(ZIndexModifierKt.zIndex(Modifier.INSTANCE, z ? 0.0f : 1.0f), 0.0f, 1, null);
            composerStartRestartGroup.startReplaceableGroup(526522672);
            boolean z5 = (i11 & 14) == 4;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new TimePickerKt$ToggleItem$1$1(z);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            ButtonKt.TextButton(aVar, SemanticsModifierKt.semantics$default(modifierFillMaxSize$default, false, (l) objRememberedValue, 1, null), false, shape, ButtonDefaults.INSTANCE.m1277textButtonColorsro_MJ88(jM2173periodSelectorContainerColorvNxB06k$material3_release, jM2174periodSelectorContentColorvNxB06k$material3_release, 0L, 0L, composerStartRestartGroup, 24576, 12), null, null, PaddingKt.m522PaddingValues0680j_4(Dp.m5678constructorimpl(0)), null, qVar, composerStartRestartGroup, ((i11 >> 6) & 14) | 12582912 | ((i11 << 6) & 7168) | ((i11 << 15) & 1879048192), 356);
            composer2 = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C05802(z, shape, aVar, timePickerColors, qVar, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VerticalClockDisplay(TimePickerState timePickerState, TimePickerColors timePickerColors, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(2054675515);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(timePickerState) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(timePickerColors) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2054675515, i11, -1, "androidx.compose.material3.VerticalClockDisplay (TimePicker.kt:940)");
            }
            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            Modifier.Companion companion = Modifier.INSTANCE;
            Alignment.Companion companion2 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyC = androidx.compose.foundation.c.C(companion2, center, composerStartRestartGroup, 6, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
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
            p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyC, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ClockDisplayNumbers(timePickerState, timePickerColors, composerStartRestartGroup, i11 & 126);
            composerStartRestartGroup.startReplaceableGroup(952914149);
            if (!timePickerState.getIs24hour()) {
                Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(companion, PeriodToggleMargin, 0.0f, 0.0f, 0.0f, 14, null);
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composerStartRestartGroup, 0, -1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default);
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
                p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                TimePickerTokens timePickerTokens = TimePickerTokens.INSTANCE;
                int i12 = i11 << 3;
                VerticalPeriodToggle(SizeKt.m580sizeVpY3zN4(companion, timePickerTokens.m2877getPeriodSelectorVerticalContainerWidthD9Ej5fM(), timePickerTokens.m2876getPeriodSelectorVerticalContainerHeightD9Ej5fM()), timePickerState, timePickerColors, composerStartRestartGroup, (i12 & 896) | (i12 & 112) | 6);
                androidx.compose.foundation.c.y(composerStartRestartGroup);
            }
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C05812(timePickerState, timePickerColors, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VerticalPeriodToggle(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors, Composer composer, int i10) {
        int i11;
        Modifier modifier2;
        TimePickerState timePickerState2;
        TimePickerColors timePickerColors2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1898918107);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(timePickerState) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(timePickerColors) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
            timePickerState2 = timePickerState;
            timePickerColors2 = timePickerColors;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1898918107, i11, -1, "androidx.compose.material3.VerticalPeriodToggle (TimePicker.kt:1047)");
            }
            composerStartRestartGroup.startReplaceableGroup(-2030104119);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1

                    /* JADX INFO: renamed from: androidx.compose.material3.TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1$1, reason: invalid class name */
                    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                    public static final class AnonymousClass1 extends r implements l<Placeable.PlacementScope, t0> {
                        final /* synthetic */ List<Placeable> $items;
                        final /* synthetic */ Placeable $spacerPlaceable;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        public AnonymousClass1(List<? extends Placeable> list, Placeable placeable) {
                            super(1);
                            this.$items = list;
                            this.$spacerPlaceable = placeable;
                        }

                        @Override // r7.l
                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            invoke((Placeable.PlacementScope) obj);
                            return t0.f22605a;
                        }

                        public final void invoke(Placeable.PlacementScope placementScope) {
                            Placeable.PlacementScope.place$default(placementScope, this.$items.get(0), 0, 0, 0.0f, 4, null);
                            Placeable.PlacementScope.place$default(placementScope, this.$items.get(1), 0, this.$items.get(0).getHeight(), 0.0f, 4, null);
                            Placeable.PlacementScope.place$default(placementScope, this.$spacerPlaceable, 0, this.$items.get(0).getHeight() - (this.$spacerPlaceable.getHeight() / 2), 0.0f, 4, null);
                        }
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return androidx.compose.ui.layout.g.a(this, intrinsicMeasureScope, list, i12);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return androidx.compose.ui.layout.g.b(this, intrinsicMeasureScope, list, i12);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
                        int size = list.size();
                        for (int i12 = 0; i12 < size; i12++) {
                            Measurable measurable = list.get(i12);
                            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable), "Spacer")) {
                                Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, measureScope.mo279roundToPx0680j_4(TimePickerTokens.INSTANCE.m2875getPeriodSelectorOutlineWidthD9Ej5fM()), 3, null));
                                ArrayList arrayList = new ArrayList(list.size());
                                int size2 = list.size();
                                for (int i13 = 0; i13 < size2; i13++) {
                                    Measurable measurable2 = list.get(i13);
                                    if (!kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable2), "Spacer")) {
                                        arrayList.add(measurable2);
                                    }
                                }
                                ArrayList arrayList2 = new ArrayList(arrayList.size());
                                int size3 = arrayList.size();
                                for (int i14 = 0; i14 < size3; i14++) {
                                    arrayList2.add(((Measurable) arrayList.get(i14)).mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, Constraints.m5645getMaxHeightimpl(j10) / 2, 3, null)));
                                }
                                return MeasureScope.CC.q(measureScope, Constraints.m5646getMaxWidthimpl(j10), Constraints.m5645getMaxHeightimpl(j10), null, new AnonymousClass1(arrayList2, placeableMo4631measureBRTryo0), 4, null);
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return androidx.compose.ui.layout.g.c(this, intrinsicMeasureScope, list, i12);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i12) {
                        return androidx.compose.ui.layout.g.d(this, intrinsicMeasureScope, list, i12);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            composerStartRestartGroup.endReplaceableGroup();
            CornerBasedShape cornerBasedShape = (CornerBasedShape) ShapesKt.getValue(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), composerStartRestartGroup, 6);
            modifier2 = modifier;
            timePickerState2 = timePickerState;
            timePickerColors2 = timePickerColors;
            PeriodToggleImpl(modifier2, timePickerState2, timePickerColors2, measurePolicy, ShapesKt.top(cornerBasedShape), ShapesKt.bottom(cornerBasedShape), composerStartRestartGroup, (i11 & 14) | 3072 | (i11 & 112) | (i11 & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C05821(modifier2, timePickerState2, timePickerColors2, i10));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:90:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void VerticalTimePicker(androidx.compose.material3.TimePickerState r16, androidx.compose.ui.Modifier r17, androidx.compose.material3.TimePickerColors r18, boolean r19, androidx.compose.runtime.Composer r20, int r21, int r22) {
        /*
            Method dump skipped, instruction units count: 395
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.VerticalTimePicker(androidx.compose.material3.TimePickerState, androidx.compose.ui.Modifier, androidx.compose.material3.TimePickerColors, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float atan(float f10, float f11) {
        float fAtan2 = ((float) Math.atan2(f10, f11)) - 1.5707964f;
        return fAtan2 < 0.0f ? fAtan2 + FullCircle : fAtan2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float dist(float f10, float f11, int i10, int i11) {
        return (float) Math.hypot(i10 - f10, i11 - f11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier drawSelector(Modifier modifier, TimePickerState timePickerState, TimePickerColors timePickerColors) {
        return DrawModifierKt.drawWithContent(modifier, new C05851(timePickerState, timePickerColors));
    }

    /* JADX INFO: renamed from: numberContentDescription-YKJpE6Y, reason: not valid java name */
    public static final String m2187numberContentDescriptionYKJpE6Y(int i10, boolean z, int i11, Composer composer, int i12) {
        int iM1925constructorimpl;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1826155772, i12, -1, "androidx.compose.material3.numberContentDescription (TimePicker.kt:1724)");
        }
        if (Selection.m1865equalsimpl0(i10, Selection.INSTANCE.m1870getMinuteJiIwxys())) {
            Strings.Companion companion = Strings.INSTANCE;
            iM1925constructorimpl = Strings.m1925constructorimpl(R.string.m3c_time_picker_minute_suffix);
        } else if (z) {
            Strings.Companion companion2 = Strings.INSTANCE;
            iM1925constructorimpl = Strings.m1925constructorimpl(R.string.m3c_time_picker_hour_24h_suffix);
        } else {
            Strings.Companion companion3 = Strings.INSTANCE;
            iM1925constructorimpl = Strings.m1925constructorimpl(R.string.m3c_time_picker_hour_suffix);
        }
        String strM1995getStringiSCLEhQ = Strings_androidKt.m1995getStringiSCLEhQ(iM1925constructorimpl, new Object[]{Integer.valueOf(i11)}, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return strM1995getStringiSCLEhQ;
    }

    public static final TimePickerState rememberTimePickerState(int i10, int i11, boolean z, Composer composer, int i12, int i13) {
        composer.startReplaceableGroup(1237715277);
        int i14 = (i13 & 1) != 0 ? 0 : i10;
        int i15 = (i13 & 2) != 0 ? 0 : i11;
        boolean zIs24HourFormat = (i13 & 4) != 0 ? TimeFormat_androidKt.is24HourFormat(composer, 0) : z;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1237715277, i12, -1, "androidx.compose.material3.rememberTimePickerState (TimePicker.kt:556)");
        }
        Object[] objArr = new Object[0];
        Saver<TimePickerState, ?> Saver = TimePickerState.INSTANCE.Saver();
        composer.startReplaceableGroup(1737740702);
        boolean z5 = ((((i12 & 14) ^ 6) > 4 && composer.changed(i14)) || (i12 & 6) == 4) | ((((i12 & 112) ^ 48) > 32 && composer.changed(i15)) || (i12 & 48) == 32) | ((((i12 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(zIs24HourFormat)) || (i12 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new TimePickerKt$rememberTimePickerState$1$1(i14, i15, zIs24HourFormat);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        TimePickerState timePickerState = (TimePickerState) RememberSaveableKt.m3084rememberSaveable(objArr, (Saver) Saver, (String) null, (r7.a) objRememberedValue, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return timePickerState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: timeInputOnChange-gIWD5Rc, reason: not valid java name */
    public static final void m2188timeInputOnChangegIWD5Rc(int i10, TimePickerState timePickerState, TextFieldValue textFieldValue, TextFieldValue textFieldValue2, int i11, l<? super TextFieldValue, t0> lVar) {
        if (kotlin.jvm.internal.p.a(textFieldValue.getText(), textFieldValue2.getText())) {
            lVar.invoke(textFieldValue);
            return;
        }
        if (textFieldValue.getText().length() == 0) {
            if (Selection.m1865equalsimpl0(i10, Selection.INSTANCE.m1869getHourJiIwxys())) {
                timePickerState.setHour$material3_release(0);
            } else {
                timePickerState.setMinute$material3_release(0);
            }
            lVar.invoke(TextFieldValue.m5425copy3r_uNRQ$default(textFieldValue, "", 0L, (TextRange) null, 6, (Object) null));
            return;
        }
        try {
            int i12 = (textFieldValue.getText().length() == 3 && TextRange.m5192getStartimpl(textFieldValue.getSelection()) == 1) ? r0.i(textFieldValue.getText().charAt(0)) : Integer.parseInt(textFieldValue.getText());
            if (i12 <= i11) {
                Selection.Companion companion = Selection.INSTANCE;
                if (Selection.m1865equalsimpl0(i10, companion.m1869getHourJiIwxys())) {
                    timePickerState.setHour$material3_release(i12);
                    if (i12 > 1 && !timePickerState.getIs24hour()) {
                        timePickerState.m2207setSelectioniHAOin8$material3_release(companion.m1870getMinuteJiIwxys());
                    }
                } else {
                    timePickerState.setMinute$material3_release(i12);
                }
                lVar.invoke(textFieldValue.getText().length() <= 2 ? textFieldValue : TextFieldValue.m5425copy3r_uNRQ$default(textFieldValue, String.valueOf(textFieldValue.getText().charAt(0)), 0L, (TextRange) null, 6, (Object) null));
            }
        } catch (NumberFormatException | IllegalArgumentException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x valuesForAnimation(float f10, float f11) {
        if (Math.abs(f10 - f11) <= 3.141592653589793d) {
            return new x(Float.valueOf(f10), Float.valueOf(f11));
        }
        double d4 = f10;
        if (d4 > 3.141592653589793d && f11 < 3.141592653589793d) {
            f11 += FullCircle;
        } else if (d4 < 3.141592653589793d && f11 > 3.141592653589793d) {
            f10 += FullCircle;
        }
        return new x(Float.valueOf(f10), Float.valueOf(f11));
    }

    private static final Modifier visible(Modifier modifier, boolean z) {
        return modifier.then(new VisibleModifier(z, InspectableValueKt.isDebugInspectorInfoEnabled() ? new TimePickerKt$visible$$inlined$debugInspectorInfo$1(z) : InspectableValueKt.getNoInspectorInfo()));
    }
}
