package androidx.compose.material3;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.material3.tokens.TopAppBarSmallTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import io.ktor.http.LinkHeader;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.h0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u0081\u0001\u0010\u0011\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0013\b\u0002\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\u001e\b\u0002\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u0002¢\u0006\u0002\b\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0081\u0001\u0010\u0013\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0013\b\u0002\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\u001e\b\u0002\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u0002¢\u0006\u0002\b\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0007¢\u0006\u0004\b\u0013\u0010\u0012\u001a\u0081\u0001\u0010\u0014\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0013\b\u0002\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\u001e\b\u0002\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u0002¢\u0006\u0002\b\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0007¢\u0006\u0004\b\u0014\u0010\u0012\u001a\u0081\u0001\u0010\u0015\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0013\b\u0002\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\u001e\b\u0002\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u0002¢\u0006\u0002\b\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0007¢\u0006\u0004\b\u0015\u0010\u0012\u001a\u0081\u0001\u0010\u0016\u001a\u00020\u00012\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0013\b\u0002\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\u001e\b\u0002\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u0002¢\u0006\u0002\b\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0007¢\u0006\u0004\b\u0016\u0010\u0012\u001a\u0083\u0001\u0010!\u001a\u00020\u00012\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u0002¢\u0006\u0002\b\t2\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00022\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00182\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a\u008f\u0001\u0010!\u001a\u00020\u00012\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u0002¢\u0006\u0002\b\t2\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00022\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00182\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\"H\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001al\u0010!\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00182\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\f\u001a\u00020\u000b2\u001c\u0010%\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u0002¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0004\b&\u0010'\u001ax\u0010!\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00182\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\b\b\u0002\u0010\u001e\u001a\u00020\u001d2\b\b\u0002\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\"2\u001c\u0010%\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u0002¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0004\b(\u0010)\u001a-\u0010/\u001a\u00020.2\b\b\u0002\u0010+\u001a\u00020*2\b\b\u0002\u0010,\u001a\u00020*2\b\b\u0002\u0010-\u001a\u00020*H\u0007¢\u0006\u0004\b/\u00100\u001a-\u00102\u001a\u0002012\b\b\u0002\u0010+\u001a\u00020*2\b\b\u0002\u0010,\u001a\u00020*2\b\b\u0002\u0010-\u001a\u00020*H\u0007¢\u0006\u0004\b2\u00103\u001a'\u00104\u001a\u0002012\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*H\u0007¢\u0006\u0004\b4\u00105\u001a@\u0010=\u001a\u00020<2\u0006\u00106\u001a\u0002012\u0006\u00107\u001a\u00020*2\u000e\u00109\u001a\n\u0012\u0004\u0012\u00020*\u0018\u0001082\u000e\u0010;\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010:H\u0083@¢\u0006\u0004\b=\u0010>\u001a\u0087\u0001\u0010C\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\u0006\u0010@\u001a\u00020?2\u0006\u0010B\u001a\u00020A2\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u0002¢\u0006\u0002\b\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0003¢\u0006\u0004\bC\u0010D\u001aµ\u0001\u0010L\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\u0006\u0010@\u001a\u00020?2\u0006\u0010E\u001a\u00020\u001b2\u0011\u0010F\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\u0006\u0010G\u001a\u00020?2\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u0002¢\u0006\u0002\b\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010H\u001a\u00020\u001b2\u0006\u0010I\u001a\u00020\u001b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0003ø\u0001\u0000¢\u0006\u0004\bJ\u0010K\u001a£\u0001\u0010Z\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010M\u001a\u00020*2\u0006\u0010N\u001a\u00020\u00182\u0006\u0010O\u001a\u00020\u00182\u0006\u0010P\u001a\u00020\u00182\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\u0006\u0010@\u001a\u00020?2\u0006\u0010Q\u001a\u00020*2\u0006\u0010S\u001a\u00020R2\u0006\u0010U\u001a\u00020T2\u0006\u0010E\u001a\u00020V2\u0006\u0010W\u001a\u00020A2\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00022\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0002H\u0003ø\u0001\u0000¢\u0006\u0004\bX\u0010Y\u001a@\u0010[\u001a\u00020<2\u0006\u00106\u001a\u00020.2\u0006\u00107\u001a\u00020*2\u000e\u00109\u001a\n\u0012\u0004\u0012\u00020*\u0018\u0001082\u000e\u0010;\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010:H\u0082@¢\u0006\u0004\b[\u0010\\\"\u0014\u0010]\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b]\u0010^\"\u001a\u0010_\u001a\u00020\u001b8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b_\u0010^\u001a\u0004\b`\u0010a\"\u0014\u0010b\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bb\u0010^\"\u0014\u0010c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bc\u0010^\"\u001a\u0010e\u001a\u00020d8\u0000X\u0080\u0004¢\u0006\f\n\u0004\be\u0010f\u001a\u0004\bg\u0010h\"\u0014\u0010i\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bi\u0010^\"\u0014\u0010j\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bj\u0010^\"\u0014\u0010k\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bk\u0010^\"\u0014\u0010l\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bl\u0010^\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006n²\u0006\f\u0010m\u001a\u00020\u00188\nX\u008a\u0084\u0002"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", LinkHeader.Parameters.Title, "Landroidx/compose/ui/Modifier;", "modifier", "navigationIcon", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Lx6/n;", "actions", "Landroidx/compose/foundation/layout/WindowInsets;", "windowInsets", "Landroidx/compose/material3/TopAppBarColors;", "colors", "Landroidx/compose/material3/TopAppBarScrollBehavior;", "scrollBehavior", "TopAppBar", "(Lr7/p;Landroidx/compose/ui/Modifier;Lr7/p;Lr7/q;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", "SmallTopAppBar", "CenterAlignedTopAppBar", "MediumTopAppBar", "LargeTopAppBar", "floatingActionButton", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "Landroidx/compose/ui/unit/Dp;", "tonalElevation", "Landroidx/compose/foundation/layout/PaddingValues;", "contentPadding", "BottomAppBar-Snr_uVM", "(Lr7/q;Landroidx/compose/ui/Modifier;Lr7/p;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/runtime/Composer;II)V", "BottomAppBar", "Landroidx/compose/material3/BottomAppBarScrollBehavior;", "BottomAppBar-qhFBPw4", "(Lr7/q;Landroidx/compose/ui/Modifier;Lr7/p;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/BottomAppBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", "content", "BottomAppBar-1oL4kX8", "(Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/WindowInsets;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "BottomAppBar-e-3WI5M", "(Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/BottomAppBarScrollBehavior;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "", "initialHeightOffsetLimit", "initialHeightOffset", "initialContentOffset", "Landroidx/compose/material3/TopAppBarState;", "rememberTopAppBarState", "(FFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarState;", "Landroidx/compose/material3/BottomAppBarState;", "rememberBottomAppBarState", "(FFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/BottomAppBarState;", "BottomAppBarState", "(FFF)Landroidx/compose/material3/BottomAppBarState;", "state", "velocity", "Landroidx/compose/animation/core/DecayAnimationSpec;", "flingAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "snapAnimationSpec", "Landroidx/compose/ui/unit/Velocity;", "settleAppBarBottom", "(Landroidx/compose/material3/BottomAppBarState;FLandroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/ui/text/TextStyle;", "titleTextStyle", "", "centeredTitle", "SingleRowTopAppBar", "(Landroidx/compose/ui/Modifier;Lr7/p;Landroidx/compose/ui/text/TextStyle;ZLr7/p;Lr7/q;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", "titleBottomPadding", "smallTitle", "smallTitleTextStyle", "maxHeight", "pinnedHeight", "TwoRowsTopAppBar-tjU4iQQ", "(Landroidx/compose/ui/Modifier;Lr7/p;Landroidx/compose/ui/text/TextStyle;FLr7/p;Landroidx/compose/ui/text/TextStyle;Lr7/p;Lr7/q;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;FFLandroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;III)V", "TwoRowsTopAppBar", "heightPx", "navigationIconContentColor", "titleContentColor", "actionIconContentColor", "titleAlpha", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "titleVerticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "titleHorizontalArrangement", "", "hideTitleSemantics", "TopAppBarLayout-kXwM9vE", "(Landroidx/compose/ui/Modifier;FJJJLr7/p;Landroidx/compose/ui/text/TextStyle;FLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;IZLr7/p;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "TopAppBarLayout", "settleAppBar", "(Landroidx/compose/material3/TopAppBarState;FLandroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Ld7/d;)Ljava/lang/Object;", "BottomAppBarHorizontalPadding", "F", "BottomAppBarVerticalPadding", "getBottomAppBarVerticalPadding", "()F", "FABHorizontalPadding", "FABVerticalPadding", "Landroidx/compose/animation/core/CubicBezierEasing;", "TopTitleAlphaEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "getTopTitleAlphaEasing", "()Landroidx/compose/animation/core/CubicBezierEasing;", "MediumTitleBottomPadding", "LargeTitleBottomPadding", "TopAppBarHorizontalPadding", "TopAppBarTitleInset", "appBarContainerColor", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AppBarKt {
    private static final float BottomAppBarHorizontalPadding;
    private static final float BottomAppBarVerticalPadding;
    private static final float FABHorizontalPadding;
    private static final float FABVerticalPadding;
    private static final float LargeTitleBottomPadding;
    private static final float MediumTitleBottomPadding;
    private static final float TopAppBarHorizontalPadding;
    private static final float TopAppBarTitleInset;
    private static final CubicBezierEasing TopTitleAlphaEasing;

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$CenterAlignedTopAppBar$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<RowScope, Composer, Integer, t0> $actions;
        final /* synthetic */ TopAppBarColors $colors;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ p<Composer, Integer, t0> $navigationIcon;
        final /* synthetic */ TopAppBarScrollBehavior $scrollBehavior;
        final /* synthetic */ p<Composer, Integer, t0> $title;
        final /* synthetic */ WindowInsets $windowInsets;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar2, q<? super RowScope, ? super Composer, ? super Integer, t0> qVar, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i10, int i11) {
            super(2);
            this.$title = pVar;
            this.$modifier = modifier;
            this.$navigationIcon = pVar2;
            this.$actions = qVar;
            this.$windowInsets = windowInsets;
            this.$colors = topAppBarColors;
            this.$scrollBehavior = topAppBarScrollBehavior;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AppBarKt.CenterAlignedTopAppBar(this.$title, this.$modifier, this.$navigationIcon, this.$actions, this.$windowInsets, this.$colors, this.$scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$LargeTopAppBar$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04571 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<RowScope, Composer, Integer, t0> $actions;
        final /* synthetic */ TopAppBarColors $colors;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ p<Composer, Integer, t0> $navigationIcon;
        final /* synthetic */ TopAppBarScrollBehavior $scrollBehavior;
        final /* synthetic */ p<Composer, Integer, t0> $title;
        final /* synthetic */ WindowInsets $windowInsets;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04571(p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar2, q<? super RowScope, ? super Composer, ? super Integer, t0> qVar, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i10, int i11) {
            super(2);
            this.$title = pVar;
            this.$modifier = modifier;
            this.$navigationIcon = pVar2;
            this.$actions = qVar;
            this.$windowInsets = windowInsets;
            this.$colors = topAppBarColors;
            this.$scrollBehavior = topAppBarScrollBehavior;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AppBarKt.LargeTopAppBar(this.$title, this.$modifier, this.$navigationIcon, this.$actions, this.$windowInsets, this.$colors, this.$scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$MediumTopAppBar$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04581 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<RowScope, Composer, Integer, t0> $actions;
        final /* synthetic */ TopAppBarColors $colors;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ p<Composer, Integer, t0> $navigationIcon;
        final /* synthetic */ TopAppBarScrollBehavior $scrollBehavior;
        final /* synthetic */ p<Composer, Integer, t0> $title;
        final /* synthetic */ WindowInsets $windowInsets;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04581(p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar2, q<? super RowScope, ? super Composer, ? super Integer, t0> qVar, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i10, int i11) {
            super(2);
            this.$title = pVar;
            this.$modifier = modifier;
            this.$navigationIcon = pVar2;
            this.$actions = qVar;
            this.$windowInsets = windowInsets;
            this.$colors = topAppBarColors;
            this.$scrollBehavior = topAppBarScrollBehavior;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AppBarKt.MediumTopAppBar(this.$title, this.$modifier, this.$navigationIcon, this.$actions, this.$windowInsets, this.$colors, this.$scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $actionsRow;
        final /* synthetic */ boolean $centeredTitle;
        final /* synthetic */ TopAppBarColors $colors;
        final /* synthetic */ p<Composer, Integer, t0> $navigationIcon;
        final /* synthetic */ TopAppBarScrollBehavior $scrollBehavior;
        final /* synthetic */ p<Composer, Integer, t0> $title;
        final /* synthetic */ TextStyle $titleTextStyle;
        final /* synthetic */ WindowInsets $windowInsets;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(WindowInsets windowInsets, TopAppBarColors topAppBarColors, p<? super Composer, ? super Integer, t0> pVar, TextStyle textStyle, boolean z, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, TopAppBarScrollBehavior topAppBarScrollBehavior) {
            super(2);
            this.$windowInsets = windowInsets;
            this.$colors = topAppBarColors;
            this.$title = pVar;
            this.$titleTextStyle = textStyle;
            this.$centeredTitle = z;
            this.$navigationIcon = pVar2;
            this.$actionsRow = pVar3;
            this.$scrollBehavior = topAppBarScrollBehavior;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            TopAppBarState state;
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(376925230, i10, -1, "androidx.compose.material3.SingleRowTopAppBar.<anonymous> (AppBar.kt:1691)");
            }
            Object objConsume = composer.consume(CompositionLocalsKt.getLocalDensity());
            TopAppBarScrollBehavior topAppBarScrollBehavior = this.$scrollBehavior;
            float heightOffset = ((topAppBarScrollBehavior == null || (state = topAppBarScrollBehavior.getState()) == null) ? 0.0f : state.getHeightOffset()) + ((Density) objConsume).mo285toPx0680j_4(TopAppBarSmallTokens.INSTANCE.m2896getContainerHeightD9Ej5fM());
            Modifier modifierClipToBounds = ClipKt.clipToBounds(WindowInsetsPaddingKt.windowInsetsPadding(Modifier.INSTANCE, this.$windowInsets));
            long jM2308getNavigationIconContentColor0d7_KjU = this.$colors.getNavigationIconContentColor();
            long jM2310getTitleContentColor0d7_KjU = this.$colors.getTitleContentColor();
            long jM2306getActionIconContentColor0d7_KjU = this.$colors.getActionIconContentColor();
            p<Composer, Integer, t0> pVar = this.$title;
            TextStyle textStyle = this.$titleTextStyle;
            Arrangement arrangement = Arrangement.INSTANCE;
            AppBarKt.m1227TopAppBarLayoutkXwM9vE(modifierClipToBounds, heightOffset, jM2308getNavigationIconContentColor0d7_KjU, jM2310getTitleContentColor0d7_KjU, jM2306getActionIconContentColor0d7_KjU, pVar, textStyle, 1.0f, arrangement.getCenter(), this.$centeredTitle ? arrangement.getCenter() : arrangement.getStart(), 0, false, this.$navigationIcon, this.$actionsRow, composer, 113246208, 3126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$SingleRowTopAppBar$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<RowScope, Composer, Integer, t0> $actions;
        final /* synthetic */ boolean $centeredTitle;
        final /* synthetic */ TopAppBarColors $colors;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ p<Composer, Integer, t0> $navigationIcon;
        final /* synthetic */ TopAppBarScrollBehavior $scrollBehavior;
        final /* synthetic */ p<Composer, Integer, t0> $title;
        final /* synthetic */ TextStyle $titleTextStyle;
        final /* synthetic */ WindowInsets $windowInsets;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, TextStyle textStyle, boolean z, p<? super Composer, ? super Integer, t0> pVar2, q<? super RowScope, ? super Composer, ? super Integer, t0> qVar, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i10, int i11) {
            super(2);
            this.$modifier = modifier;
            this.$title = pVar;
            this.$titleTextStyle = textStyle;
            this.$centeredTitle = z;
            this.$navigationIcon = pVar2;
            this.$actions = qVar;
            this.$windowInsets = windowInsets;
            this.$colors = topAppBarColors;
            this.$scrollBehavior = topAppBarScrollBehavior;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AppBarKt.SingleRowTopAppBar(this.$modifier, this.$title, this.$titleTextStyle, this.$centeredTitle, this.$navigationIcon, this.$actions, this.$windowInsets, this.$colors, this.$scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$SmallTopAppBar$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04591 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<RowScope, Composer, Integer, t0> $actions;
        final /* synthetic */ TopAppBarColors $colors;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ p<Composer, Integer, t0> $navigationIcon;
        final /* synthetic */ TopAppBarScrollBehavior $scrollBehavior;
        final /* synthetic */ p<Composer, Integer, t0> $title;
        final /* synthetic */ WindowInsets $windowInsets;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04591(p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar2, q<? super RowScope, ? super Composer, ? super Integer, t0> qVar, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i10, int i11) {
            super(2);
            this.$title = pVar;
            this.$modifier = modifier;
            this.$navigationIcon = pVar2;
            this.$actions = qVar;
            this.$windowInsets = windowInsets;
            this.$colors = topAppBarColors;
            this.$scrollBehavior = topAppBarScrollBehavior;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AppBarKt.SmallTopAppBar(this.$title, this.$modifier, this.$navigationIcon, this.$actions, this.$windowInsets, this.$colors, this.$scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$TopAppBar$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04601 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ q<RowScope, Composer, Integer, t0> $actions;
        final /* synthetic */ TopAppBarColors $colors;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ p<Composer, Integer, t0> $navigationIcon;
        final /* synthetic */ TopAppBarScrollBehavior $scrollBehavior;
        final /* synthetic */ p<Composer, Integer, t0> $title;
        final /* synthetic */ WindowInsets $windowInsets;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04601(p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar2, q<? super RowScope, ? super Composer, ? super Integer, t0> qVar, WindowInsets windowInsets, TopAppBarColors topAppBarColors, TopAppBarScrollBehavior topAppBarScrollBehavior, int i10, int i11) {
            super(2);
            this.$title = pVar;
            this.$modifier = modifier;
            this.$navigationIcon = pVar2;
            this.$actions = qVar;
            this.$windowInsets = windowInsets;
            this.$colors = topAppBarColors;
            this.$scrollBehavior = topAppBarScrollBehavior;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            AppBarKt.TopAppBar(this.$title, this.$modifier, this.$navigationIcon, this.$actions, this.$windowInsets, this.$colors, this.$scrollBehavior, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$settleAppBar$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.material3.AppBarKt", f = "AppBar.kt", l = {2241, 2257}, m = "settleAppBar")
    public static final class C04611 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C04611(d7.d<? super C04611> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AppBarKt.settleAppBar(null, 0.0f, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$settleAppBar$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04622 extends r implements l<AnimationScope<Float, AnimationVector1D>, t0> {
        final /* synthetic */ h0 $lastValue;
        final /* synthetic */ h0 $remainingVelocity;
        final /* synthetic */ TopAppBarState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04622(h0 h0Var, TopAppBarState topAppBarState, h0 h0Var2) {
            super(1);
            this.$lastValue = h0Var;
            this.$state = topAppBarState;
            this.$remainingVelocity = h0Var2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AnimationScope<Float, AnimationVector1D>) obj);
            return t0.f22605a;
        }

        public final void invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
            float fFloatValue = animationScope.getValue().floatValue() - this.$lastValue.f19742i;
            float heightOffset = this.$state.getHeightOffset();
            this.$state.setHeightOffset(heightOffset + fFloatValue);
            float fAbs = Math.abs(heightOffset - this.$state.getHeightOffset());
            this.$lastValue.f19742i = animationScope.getValue().floatValue();
            this.$remainingVelocity.f19742i = animationScope.getVelocity().floatValue();
            if (Math.abs(fFloatValue - fAbs) > 0.5f) {
                animationScope.cancelAnimation();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$settleAppBar$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04633 extends r implements l<AnimationScope<Float, AnimationVector1D>, t0> {
        final /* synthetic */ TopAppBarState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04633(TopAppBarState topAppBarState) {
            super(1);
            this.$state = topAppBarState;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AnimationScope<Float, AnimationVector1D>) obj);
            return t0.f22605a;
        }

        public final void invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
            this.$state.setHeightOffset(animationScope.getValue().floatValue());
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$settleAppBarBottom$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @f7.e(c = "androidx.compose.material3.AppBarKt", f = "AppBar.kt", l = {1578, 1594}, m = "settleAppBarBottom")
    public static final class C04641 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C04641(d7.d<? super C04641> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AppBarKt.settleAppBarBottom(null, 0.0f, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$settleAppBarBottom$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04652 extends r implements l<AnimationScope<Float, AnimationVector1D>, t0> {
        final /* synthetic */ h0 $lastValue;
        final /* synthetic */ h0 $remainingVelocity;
        final /* synthetic */ BottomAppBarState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04652(h0 h0Var, BottomAppBarState bottomAppBarState, h0 h0Var2) {
            super(1);
            this.$lastValue = h0Var;
            this.$state = bottomAppBarState;
            this.$remainingVelocity = h0Var2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AnimationScope<Float, AnimationVector1D>) obj);
            return t0.f22605a;
        }

        public final void invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
            float fFloatValue = animationScope.getValue().floatValue() - this.$lastValue.f19742i;
            float heightOffset = this.$state.getHeightOffset();
            this.$state.setHeightOffset(heightOffset + fFloatValue);
            float fAbs = Math.abs(heightOffset - this.$state.getHeightOffset());
            this.$lastValue.f19742i = animationScope.getValue().floatValue();
            this.$remainingVelocity.f19742i = animationScope.getVelocity().floatValue();
            if (Math.abs(fFloatValue - fAbs) > 0.5f) {
                animationScope.cancelAnimation();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$settleAppBarBottom$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Landroidx/compose/animation/core/AnimationScope;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/core/AnimationScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04663 extends r implements l<AnimationScope<Float, AnimationVector1D>, t0> {
        final /* synthetic */ BottomAppBarState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04663(BottomAppBarState bottomAppBarState) {
            super(1);
            this.$state = bottomAppBarState;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((AnimationScope<Float, AnimationVector1D>) obj);
            return t0.f22605a;
        }

        public final void invoke(AnimationScope<Float, AnimationVector1D> animationScope) {
            this.$state.setHeightOffset(animationScope.getValue().floatValue());
        }
    }

    static {
        float f10 = 16;
        float f11 = 12;
        float fM5678constructorimpl = Dp.m5678constructorimpl(Dp.m5678constructorimpl(f10) - Dp.m5678constructorimpl(f11));
        BottomAppBarHorizontalPadding = fM5678constructorimpl;
        float fM5678constructorimpl2 = Dp.m5678constructorimpl(Dp.m5678constructorimpl(f10) - Dp.m5678constructorimpl(f11));
        BottomAppBarVerticalPadding = fM5678constructorimpl2;
        FABHorizontalPadding = Dp.m5678constructorimpl(Dp.m5678constructorimpl(f10) - fM5678constructorimpl);
        FABVerticalPadding = Dp.m5678constructorimpl(Dp.m5678constructorimpl(f11) - fM5678constructorimpl2);
        TopTitleAlphaEasing = new CubicBezierEasing(0.8f, 0.0f, 0.8f, 0.15f);
        MediumTitleBottomPadding = Dp.m5678constructorimpl(24);
        LargeTitleBottomPadding = Dp.m5678constructorimpl(28);
        float fM5678constructorimpl3 = Dp.m5678constructorimpl(4);
        TopAppBarHorizontalPadding = fM5678constructorimpl3;
        TopAppBarTitleInset = Dp.m5678constructorimpl(Dp.m5678constructorimpl(f10) - fM5678constructorimpl3);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00ef  */
    /* JADX INFO: renamed from: BottomAppBar-1oL4kX8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1223BottomAppBar1oL4kX8(androidx.compose.ui.Modifier r24, long r25, long r27, float r29, androidx.compose.foundation.layout.PaddingValues r30, androidx.compose.foundation.layout.WindowInsets r31, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r32, androidx.compose.runtime.Composer r33, int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 480
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.m1223BottomAppBar1oL4kX8(androidx.compose.ui.Modifier, long, long, float, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.layout.WindowInsets, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:142:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x010f  */
    /* JADX INFO: renamed from: BottomAppBar-Snr_uVM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1224BottomAppBarSnr_uVM(r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r27, androidx.compose.ui.Modifier r28, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r29, long r30, long r32, float r34, androidx.compose.foundation.layout.PaddingValues r35, androidx.compose.foundation.layout.WindowInsets r36, androidx.compose.runtime.Composer r37, int r38, int r39) {
        /*
            Method dump skipped, instruction units count: 500
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.m1224BottomAppBarSnr_uVM(r7.q, androidx.compose.ui.Modifier, r7.p, long, long, float, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.layout.WindowInsets, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:172:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x010f  */
    /* JADX INFO: renamed from: BottomAppBar-e-3WI5M, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1225BottomAppBare3WI5M(androidx.compose.ui.Modifier r35, long r36, long r38, float r40, androidx.compose.foundation.layout.PaddingValues r41, androidx.compose.foundation.layout.WindowInsets r42, androidx.compose.material3.BottomAppBarScrollBehavior r43, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r44, androidx.compose.runtime.Composer r45, int r46, int r47) {
        /*
            Method dump skipped, instruction units count: 723
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.m1225BottomAppBare3WI5M(androidx.compose.ui.Modifier, long, long, float, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.layout.WindowInsets, androidx.compose.material3.BottomAppBarScrollBehavior, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:157:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00f7  */
    /* JADX INFO: renamed from: BottomAppBar-qhFBPw4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1226BottomAppBarqhFBPw4(r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r27, androidx.compose.ui.Modifier r28, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r29, long r30, long r32, float r34, androidx.compose.foundation.layout.PaddingValues r35, androidx.compose.foundation.layout.WindowInsets r36, androidx.compose.material3.BottomAppBarScrollBehavior r37, androidx.compose.runtime.Composer r38, int r39, int r40) {
        /*
            Method dump skipped, instruction units count: 561
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.m1226BottomAppBarqhFBPw4(r7.q, androidx.compose.ui.Modifier, r7.p, long, long, float, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.layout.WindowInsets, androidx.compose.material3.BottomAppBarScrollBehavior, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final BottomAppBarState BottomAppBarState(float f10, float f11, float f12) {
        return new BottomAppBarStateImpl(f10, f11, f12);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:125:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CenterAlignedTopAppBar(r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r21, androidx.compose.ui.Modifier r22, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r23, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r24, androidx.compose.foundation.layout.WindowInsets r25, androidx.compose.material3.TopAppBarColors r26, androidx.compose.material3.TopAppBarScrollBehavior r27, androidx.compose.runtime.Composer r28, int r29, int r30) {
        /*
            Method dump skipped, instruction units count: 459
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.CenterAlignedTopAppBar(r7.p, androidx.compose.ui.Modifier, r7.p, r7.q, androidx.compose.foundation.layout.WindowInsets, androidx.compose.material3.TopAppBarColors, androidx.compose.material3.TopAppBarScrollBehavior, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:125:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LargeTopAppBar(r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r26, androidx.compose.ui.Modifier r27, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r28, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r29, androidx.compose.foundation.layout.WindowInsets r30, androidx.compose.material3.TopAppBarColors r31, androidx.compose.material3.TopAppBarScrollBehavior r32, androidx.compose.runtime.Composer r33, int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 498
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.LargeTopAppBar(r7.p, androidx.compose.ui.Modifier, r7.p, r7.q, androidx.compose.foundation.layout.WindowInsets, androidx.compose.material3.TopAppBarColors, androidx.compose.material3.TopAppBarScrollBehavior, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:125:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void MediumTopAppBar(r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r26, androidx.compose.ui.Modifier r27, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r28, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r29, androidx.compose.foundation.layout.WindowInsets r30, androidx.compose.material3.TopAppBarColors r31, androidx.compose.material3.TopAppBarScrollBehavior r32, androidx.compose.runtime.Composer r33, int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 498
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.MediumTopAppBar(r7.p, androidx.compose.ui.Modifier, r7.p, r7.q, androidx.compose.foundation.layout.WindowInsets, androidx.compose.material3.TopAppBarColors, androidx.compose.material3.TopAppBarScrollBehavior, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:159:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SingleRowTopAppBar(androidx.compose.ui.Modifier r30, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r31, androidx.compose.ui.text.TextStyle r32, boolean r33, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r34, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r35, androidx.compose.foundation.layout.WindowInsets r36, androidx.compose.material3.TopAppBarColors r37, androidx.compose.material3.TopAppBarScrollBehavior r38, androidx.compose.runtime.Composer r39, int r40, int r41) {
        /*
            Method dump skipped, instruction units count: 665
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.SingleRowTopAppBar(androidx.compose.ui.Modifier, r7.p, androidx.compose.ui.text.TextStyle, boolean, r7.p, r7.q, androidx.compose.foundation.layout.WindowInsets, androidx.compose.material3.TopAppBarColors, androidx.compose.material3.TopAppBarScrollBehavior, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final long SingleRowTopAppBar$lambda$7(State<Color> state) {
        return state.getValue().m3482unboximpl();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:125:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0114  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SmallTopAppBar(r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r19, androidx.compose.ui.Modifier r20, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r21, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r22, androidx.compose.foundation.layout.WindowInsets r23, androidx.compose.material3.TopAppBarColors r24, androidx.compose.material3.TopAppBarScrollBehavior r25, androidx.compose.runtime.Composer r26, int r27, int r28) {
        /*
            Method dump skipped, instruction units count: 400
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.SmallTopAppBar(r7.p, androidx.compose.ui.Modifier, r7.p, r7.q, androidx.compose.foundation.layout.WindowInsets, androidx.compose.material3.TopAppBarColors, androidx.compose.material3.TopAppBarScrollBehavior, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:125:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TopAppBar(r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r21, androidx.compose.ui.Modifier r22, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r23, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r24, androidx.compose.foundation.layout.WindowInsets r25, androidx.compose.material3.TopAppBarColors r26, androidx.compose.material3.TopAppBarScrollBehavior r27, androidx.compose.runtime.Composer r28, int r29, int r30) {
        /*
            Method dump skipped, instruction units count: 459
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.TopAppBar(r7.p, androidx.compose.ui.Modifier, r7.p, r7.q, androidx.compose.foundation.layout.WindowInsets, androidx.compose.material3.TopAppBarColors, androidx.compose.material3.TopAppBarScrollBehavior, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: TopAppBarLayout-kXwM9vE, reason: not valid java name */
    public static final void m1227TopAppBarLayoutkXwM9vE(Modifier modifier, final float f10, long j10, long j11, long j12, p<? super Composer, ? super Integer, t0> pVar, TextStyle textStyle, float f11, final Arrangement.Vertical vertical, final Arrangement.Horizontal horizontal, final int i10, boolean z, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, Composer composer, int i11, int i12) {
        int i13;
        int i14;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-6794037);
        if ((i11 & 6) == 0) {
            i13 = i11 | (composerStartRestartGroup.changed(modifier) ? 4 : 2);
        } else {
            i13 = i11;
        }
        if ((i11 & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(f10) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changed(j10) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i13 |= composerStartRestartGroup.changed(j11) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i13 |= composerStartRestartGroup.changed(j12) ? 16384 : 8192;
        }
        if ((196608 & i11) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(pVar) ? 131072 : 65536;
        }
        if ((1572864 & i11) == 0) {
            i13 |= composerStartRestartGroup.changed(textStyle) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i11 & 12582912) == 0) {
            i13 |= composerStartRestartGroup.changed(f11) ? 8388608 : 4194304;
        }
        if ((i11 & 100663296) == 0) {
            i13 |= composerStartRestartGroup.changed(vertical) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i11 & 805306368) == 0) {
            i13 |= composerStartRestartGroup.changed(horizontal) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        int i15 = i13;
        if ((i12 & 6) == 0) {
            i14 = i12 | (composerStartRestartGroup.changed(i10) ? 4 : 2);
        } else {
            i14 = i12;
        }
        if ((i12 & 48) == 0) {
            i14 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i12 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i14 |= composerStartRestartGroup.changedInstance(pVar2) ? 256 : 128;
        }
        if ((i12 & 3072) == 0) {
            i14 |= composerStartRestartGroup.changedInstance(pVar3) ? 2048 : 1024;
        }
        int i16 = i14;
        if ((i15 & 306783379) == 306783378 && (i16 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-6794037, i15, i16, "androidx.compose.material3.TopAppBarLayout (AppBar.kt:1899)");
            }
            composerStartRestartGroup.startReplaceableGroup(1019460550);
            boolean z5 = ((1879048192 & i15) == 536870912) | ((i15 & 112) == 32) | ((i15 & 234881024) == 67108864) | ((i16 & 14) == 4);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.AppBarKt$TopAppBarLayout$2$1

                    /* JADX INFO: renamed from: androidx.compose.material3.AppBarKt$TopAppBarLayout$2$1$1, reason: invalid class name */
                    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                    public static final class AnonymousClass1 extends r implements l<Placeable.PlacementScope, t0> {
                        final /* synthetic */ Placeable $actionIconsPlaceable;
                        final /* synthetic */ long $constraints;
                        final /* synthetic */ int $layoutHeight;
                        final /* synthetic */ Placeable $navigationIconPlaceable;
                        final /* synthetic */ MeasureScope $this_Layout;
                        final /* synthetic */ int $titleBaseline;
                        final /* synthetic */ int $titleBottomPadding;
                        final /* synthetic */ Arrangement.Horizontal $titleHorizontalArrangement;
                        final /* synthetic */ Placeable $titlePlaceable;
                        final /* synthetic */ Arrangement.Vertical $titleVerticalArrangement;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public AnonymousClass1(Placeable placeable, int i10, Placeable placeable2, Arrangement.Horizontal horizontal, long j10, Placeable placeable3, MeasureScope measureScope, Arrangement.Vertical vertical, int i11, int i12) {
                            super(1);
                            this.$navigationIconPlaceable = placeable;
                            this.$layoutHeight = i10;
                            this.$titlePlaceable = placeable2;
                            this.$titleHorizontalArrangement = horizontal;
                            this.$constraints = j10;
                            this.$actionIconsPlaceable = placeable3;
                            this.$this_Layout = measureScope;
                            this.$titleVerticalArrangement = vertical;
                            this.$titleBottomPadding = i11;
                            this.$titleBaseline = i12;
                        }

                        @Override // r7.l
                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            invoke((Placeable.PlacementScope) obj);
                            return t0.f22605a;
                        }

                        public final void invoke(Placeable.PlacementScope placementScope) {
                            int iM5646getMaxWidthimpl;
                            int i10;
                            int height;
                            int iMax;
                            int iF;
                            int iM5646getMaxWidthimpl2;
                            Placeable placeable = this.$navigationIconPlaceable;
                            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, androidx.compose.foundation.c.f(placeable, this.$layoutHeight, 2), 0.0f, 4, null);
                            Placeable placeable2 = this.$titlePlaceable;
                            Arrangement.Horizontal horizontal = this.$titleHorizontalArrangement;
                            Arrangement arrangement = Arrangement.INSTANCE;
                            if (kotlin.jvm.internal.p.a(horizontal, arrangement.getCenter())) {
                                iM5646getMaxWidthimpl = androidx.compose.foundation.c.B(this.$titlePlaceable, Constraints.m5646getMaxWidthimpl(this.$constraints), 2);
                                if (iM5646getMaxWidthimpl < this.$navigationIconPlaceable.getWidth()) {
                                    iM5646getMaxWidthimpl2 = this.$navigationIconPlaceable.getWidth() - iM5646getMaxWidthimpl;
                                } else if (this.$titlePlaceable.getWidth() + iM5646getMaxWidthimpl > Constraints.m5646getMaxWidthimpl(this.$constraints) - this.$actionIconsPlaceable.getWidth()) {
                                    iM5646getMaxWidthimpl2 = (Constraints.m5646getMaxWidthimpl(this.$constraints) - this.$actionIconsPlaceable.getWidth()) - (this.$titlePlaceable.getWidth() + iM5646getMaxWidthimpl);
                                }
                                iM5646getMaxWidthimpl += iM5646getMaxWidthimpl2;
                            } else {
                                iM5646getMaxWidthimpl = kotlin.jvm.internal.p.a(horizontal, arrangement.getEnd()) ? (Constraints.m5646getMaxWidthimpl(this.$constraints) - this.$titlePlaceable.getWidth()) - this.$actionIconsPlaceable.getWidth() : Math.max(this.$this_Layout.mo279roundToPx0680j_4(AppBarKt.TopAppBarTitleInset), this.$navigationIconPlaceable.getWidth());
                            }
                            int i11 = iM5646getMaxWidthimpl;
                            Arrangement.Vertical vertical = this.$titleVerticalArrangement;
                            if (kotlin.jvm.internal.p.a(vertical, arrangement.getCenter())) {
                                iF = androidx.compose.foundation.c.f(this.$titlePlaceable, this.$layoutHeight, 2);
                            } else if (!kotlin.jvm.internal.p.a(vertical, arrangement.getBottom())) {
                                i10 = 0;
                                Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i11, i10, 0.0f, 4, null);
                                Placeable.PlacementScope.placeRelative$default(placementScope, this.$actionIconsPlaceable, Constraints.m5646getMaxWidthimpl(this.$constraints) - this.$actionIconsPlaceable.getWidth(), androidx.compose.foundation.c.f(this.$actionIconsPlaceable, this.$layoutHeight, 2), 0.0f, 4, null);
                            } else {
                                if (this.$titleBottomPadding == 0) {
                                    height = this.$layoutHeight;
                                    iMax = this.$titlePlaceable.getHeight();
                                } else {
                                    height = this.$layoutHeight - this.$titlePlaceable.getHeight();
                                    iMax = Math.max(0, (this.$titleBottomPadding - this.$titlePlaceable.getHeight()) + this.$titleBaseline);
                                }
                                iF = height - iMax;
                            }
                            i10 = iF;
                            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i11, i10, 0.0f, 4, null);
                            Placeable.PlacementScope.placeRelative$default(placementScope, this.$actionIconsPlaceable, Constraints.m5646getMaxWidthimpl(this.$constraints) - this.$actionIconsPlaceable.getWidth(), androidx.compose.foundation.c.f(this.$actionIconsPlaceable, this.$layoutHeight, 2), 0.0f, 4, null);
                        }
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i17) {
                        return androidx.compose.ui.layout.g.a(this, intrinsicMeasureScope, list, i17);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i17) {
                        return androidx.compose.ui.layout.g.b(this, intrinsicMeasureScope, list, i17);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j13) {
                        int iM5646getMaxWidthimpl;
                        int size = list.size();
                        for (int i17 = 0; i17 < size; i17++) {
                            Measurable measurable = list.get(i17);
                            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable), "navigationIcon")) {
                                Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j13, 0, 0, 0, 0, 14, null));
                                int size2 = list.size();
                                for (int i18 = 0; i18 < size2; i18++) {
                                    Measurable measurable2 = list.get(i18);
                                    if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable2), "actionIcons")) {
                                        Placeable placeableMo4631measureBRTryo02 = measurable2.mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j13, 0, 0, 0, 0, 14, null));
                                        if (Constraints.m5646getMaxWidthimpl(j13) == Integer.MAX_VALUE) {
                                            iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j13);
                                        } else {
                                            iM5646getMaxWidthimpl = (Constraints.m5646getMaxWidthimpl(j13) - placeableMo4631measureBRTryo0.getWidth()) - placeableMo4631measureBRTryo02.getWidth();
                                            if (iM5646getMaxWidthimpl < 0) {
                                                iM5646getMaxWidthimpl = 0;
                                            }
                                        }
                                        int i19 = iM5646getMaxWidthimpl;
                                        int size3 = list.size();
                                        for (int i20 = 0; i20 < size3; i20++) {
                                            Measurable measurable3 = list.get(i20);
                                            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable3), LinkHeader.Parameters.Title)) {
                                                Placeable placeableMo4631measureBRTryo03 = measurable3.mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j13, 0, i19, 0, 0, 12, null));
                                                int i21 = placeableMo4631measureBRTryo03.get(AlignmentLineKt.getLastBaseline()) != Integer.MIN_VALUE ? placeableMo4631measureBRTryo03.get(AlignmentLineKt.getLastBaseline()) : 0;
                                                int iM = Float.isNaN(f10) ? 0 : t7.a.M(f10);
                                                return MeasureScope.CC.q(measureScope, Constraints.m5646getMaxWidthimpl(j13), iM, null, new AnonymousClass1(placeableMo4631measureBRTryo0, iM, placeableMo4631measureBRTryo03, horizontal, j13, placeableMo4631measureBRTryo02, measureScope, vertical, i10, i21), 4, null);
                                            }
                                        }
                                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                                    }
                                }
                                throw new NoSuchElementException("Collection contains no element matching the predicate.");
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i17) {
                        return androidx.compose.ui.layout.g.c(this, intrinsicMeasureScope, list, i17);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i17) {
                        return androidx.compose.ui.layout.g.d(this, intrinsicMeasureScope, list, i17);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            composerStartRestartGroup.endReplaceableGroup();
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
            p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Modifier modifierLayoutId = LayoutIdKt.layoutId(companion2, "navigationIcon");
            float f12 = TopAppBarHorizontalPadding;
            Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(modifierLayoutId, f12, 0.0f, 0.0f, 0.0f, 14, null);
            composerStartRestartGroup.startReplaceableGroup(733328855);
            Alignment.Companion companion3 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyD = a0.c.d(companion3, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion.getConstructor();
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
            p pVarU2 = a0.c.u(companion, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ProvidedValue<Color> providedValueProvides = ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(j10));
            int i17 = ProvidedValue.$stable;
            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, pVar2, composerStartRestartGroup, ((i16 >> 3) & 112) | i17);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierM3633graphicsLayerAp8cVGQ$default = GraphicsLayerModifierKt.m3633graphicsLayerAp8cVGQ$default(PaddingKt.m531paddingVpY3zN4$default(LayoutIdKt.layoutId(companion2, LinkHeader.Parameters.Title), f12, 0.0f, 2, null).then(z ? SemanticsModifierKt.clearAndSetSemantics(companion2, AppBarKt$TopAppBarLayout$1$2.INSTANCE) : companion2), 0.0f, 0.0f, f11, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, null, false, null, 0L, 0L, 0, 131067, null);
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD2 = a0.c.d(companion3, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM3633graphicsLayerAp8cVGQ$default);
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
            p pVarU3 = a0.c.u(companion, composerM2991constructorimpl3, measurePolicyD2, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf3, composerStartRestartGroup, 0, 2058660585);
            int i18 = i15 >> 9;
            ProvideContentColorTextStyleKt.m1782ProvideContentColorTextStyle3JVO9M(j11, textStyle, pVar, composerStartRestartGroup, (i18 & 14) | ((i15 >> 15) & 112) | (i18 & 896));
            composer2 = composerStartRestartGroup;
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            Modifier modifierM533paddingqDBjuR0$default2 = PaddingKt.m533paddingqDBjuR0$default(LayoutIdKt.layoutId(companion2, "actionIcons"), 0.0f, 0.0f, f12, 0.0f, 11, null);
            composer2.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD3 = a0.c.d(companion3, false, composer2, 0, -1323940314);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composer2.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor4 = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default2);
            if (composer2.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor4);
            } else {
                composer2.useNode();
            }
            Composer composerM2991constructorimpl4 = Updater.m2991constructorimpl(composer2);
            p pVarU4 = a0.c.u(companion, composerM2991constructorimpl4, measurePolicyD3, composerM2991constructorimpl4, currentCompositionLocalMap4);
            if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
            }
            qVarModifierMaterializerOf4.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer2)), composer2, 0);
            composer2.startReplaceableGroup(2058660585);
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(j12)), pVar3, composer2, ((i16 >> 6) & 112) | i17);
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AppBarKt$TopAppBarLayout$3(modifier, f10, j10, j11, j12, pVar, textStyle, f11, vertical, horizontal, i10, z, pVar2, pVar3, i11, i12));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:108:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:204:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0126  */
    /* JADX INFO: renamed from: TwoRowsTopAppBar-tjU4iQQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1228TwoRowsTopAppBartjU4iQQ(androidx.compose.ui.Modifier r40, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r41, androidx.compose.ui.text.TextStyle r42, float r43, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r44, androidx.compose.ui.text.TextStyle r45, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r46, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r47, androidx.compose.foundation.layout.WindowInsets r48, androidx.compose.material3.TopAppBarColors r49, float r50, float r51, androidx.compose.material3.TopAppBarScrollBehavior r52, androidx.compose.runtime.Composer r53, int r54, int r55, int r56) {
        /*
            Method dump skipped, instruction units count: 844
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.m1228TwoRowsTopAppBartjU4iQQ(androidx.compose.ui.Modifier, r7.p, androidx.compose.ui.text.TextStyle, float, r7.p, androidx.compose.ui.text.TextStyle, r7.p, r7.q, androidx.compose.foundation.layout.WindowInsets, androidx.compose.material3.TopAppBarColors, float, float, androidx.compose.material3.TopAppBarScrollBehavior, androidx.compose.runtime.Composer, int, int, int):void");
    }

    public static final float getBottomAppBarVerticalPadding() {
        return BottomAppBarVerticalPadding;
    }

    public static final CubicBezierEasing getTopTitleAlphaEasing() {
        return TopTitleAlphaEasing;
    }

    public static final BottomAppBarState rememberBottomAppBarState(float f10, float f11, float f12, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1420874240);
        if ((i11 & 1) != 0) {
            f10 = -3.4028235E38f;
        }
        if ((i11 & 2) != 0) {
            f11 = 0.0f;
        }
        if ((i11 & 4) != 0) {
            f12 = 0.0f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1420874240, i10, -1, "androidx.compose.material3.rememberBottomAppBarState (AppBar.kt:1372)");
        }
        Object[] objArr = new Object[0];
        Saver<BottomAppBarState, ?> saver = BottomAppBarState.INSTANCE.getSaver();
        composer.startReplaceableGroup(647586024);
        boolean z = ((((i10 & 14) ^ 6) > 4 && composer.changed(f10)) || (i10 & 6) == 4) | ((((i10 & 112) ^ 48) > 32 && composer.changed(f11)) || (i10 & 48) == 32) | ((((i10 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(f12)) || (i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new AppBarKt$rememberBottomAppBarState$1$1(f10, f11, f12);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        BottomAppBarState bottomAppBarState = (BottomAppBarState) RememberSaveableKt.m3084rememberSaveable(objArr, (Saver) saver, (String) null, (r7.a) objRememberedValue, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return bottomAppBarState;
    }

    public static final TopAppBarState rememberTopAppBarState(float f10, float f11, float f12, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1801969826);
        if ((i11 & 1) != 0) {
            f10 = -3.4028235E38f;
        }
        if ((i11 & 2) != 0) {
            f11 = 0.0f;
        }
        if ((i11 & 4) != 0) {
            f12 = 0.0f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1801969826, i10, -1, "androidx.compose.material3.rememberTopAppBarState (AppBar.kt:1051)");
        }
        Object[] objArr = new Object[0];
        Saver<TopAppBarState, ?> saver = TopAppBarState.INSTANCE.getSaver();
        composer.startReplaceableGroup(1171243704);
        boolean z = ((((i10 & 14) ^ 6) > 4 && composer.changed(f10)) || (i10 & 6) == 4) | ((((i10 & 112) ^ 48) > 32 && composer.changed(f11)) || (i10 & 48) == 32) | ((((i10 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(f12)) || (i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256);
        Object objRememberedValue = composer.rememberedValue();
        if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new AppBarKt$rememberTopAppBarState$1$1(f10, f11, f12);
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        TopAppBarState topAppBarState = (TopAppBarState) RememberSaveableKt.m3084rememberSaveable(objArr, (Saver) saver, (String) null, (r7.a) objRememberedValue, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return topAppBarState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object settleAppBar(androidx.compose.material3.TopAppBarState r22, float r23, androidx.compose.animation.core.DecayAnimationSpec<java.lang.Float> r24, androidx.compose.animation.core.AnimationSpec<java.lang.Float> r25, d7.d<? super androidx.compose.ui.unit.Velocity> r26) {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.settleAppBar(androidx.compose.material3.TopAppBarState, float, androidx.compose.animation.core.DecayAnimationSpec, androidx.compose.animation.core.AnimationSpec, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object settleAppBarBottom(androidx.compose.material3.BottomAppBarState r22, float r23, androidx.compose.animation.core.DecayAnimationSpec<java.lang.Float> r24, androidx.compose.animation.core.AnimationSpec<java.lang.Float> r25, d7.d<? super androidx.compose.ui.unit.Velocity> r26) {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.settleAppBarBottom(androidx.compose.material3.BottomAppBarState, float, androidx.compose.animation.core.DecayAnimationSpec, androidx.compose.animation.core.AnimationSpec, d7.d):java.lang.Object");
    }
}
