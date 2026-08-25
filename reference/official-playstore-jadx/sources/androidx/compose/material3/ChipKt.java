package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.tokens.SuggestionChipTokens;
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
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a¨\u0001\u0010\u0015\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001a¨\u0001\u0010\u0015\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u0015\u0010\u0018\u001a¨\u0001\u0010\u0019\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u0019\u0010\u0016\u001a¨\u0001\u0010\u0019\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u0019\u0010\u0018\u001a°\u0001\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u001b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u001d\u0010\u001e\u001a°\u0001\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u001b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0007¢\u0006\u0004\b\u001f\u0010\u001e\u001aÇ\u0001\u0010!\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00072\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0015\b\u0002\u0010 \u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u001b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0007¢\u0006\u0004\b!\u0010\"\u001a\u0091\u0001\u0010$\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u0015\b\u0002\u0010#\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0007¢\u0006\u0004\b$\u0010%\u001a\u0091\u0001\u0010$\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u0015\b\u0002\u0010#\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0007¢\u0006\u0004\b$\u0010&\u001a\u0091\u0001\u0010'\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u0015\b\u0002\u0010#\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0007¢\u0006\u0004\b'\u0010%\u001a\u0091\u0001\u0010'\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u0015\b\u0002\u0010#\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u0014\u001a\u00020\u0013H\u0007¢\u0006\u0004\b'\u0010&\u001a¹\u0001\u00102\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\b\u001a\u00020\u00072\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00032\u0006\u0010)\u001a\u00020(2\u0006\u0010+\u001a\u00020*2\u0013\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010-\u001a\u00020,2\u0006\u0010/\u001a\u00020.2\u0006\u0010\u0014\u001a\u00020\u0013H\u0003ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001aÎ\u0001\u00105\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00052\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0006\u0010\b\u001a\u00020\u00072\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00032\u0006\u0010)\u001a\u00020(2\u0013\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0013\u0010 \u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u001b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u001c2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010-\u001a\u00020,2\u0006\u0010/\u001a\u00020.2\u0006\u0010\u0014\u001a\u00020\u0013H\u0003ø\u0001\u0000¢\u0006\u0004\b3\u00104\u001a\u0094\u0001\u0010:\u001a\u00020\u00012\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00032\u0006\u0010)\u001a\u00020(2\u0006\u0010+\u001a\u00020*2\u0013\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0013\u0010 \u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\u00032\u0006\u00106\u001a\u00020*2\u0006\u00107\u001a\u00020*2\u0006\u0010-\u001a\u00020,2\u0006\u0010/\u001a\u00020.H\u0003ø\u0001\u0000¢\u0006\u0004\b8\u00109\u001a-\u0010>\u001a\u00020.2\b\b\u0002\u0010;\u001a\u00020\u00072\b\b\u0002\u0010<\u001a\u00020\u00072\b\b\u0002\u0010=\u001a\u00020\u0007H\u0002¢\u0006\u0004\b>\u0010?\"\u0014\u0010@\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010A\"\u0014\u0010B\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010C\"\u0014\u0010D\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bD\u0010C\"\u0014\u0010E\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010C\"\u0014\u0010G\u001a\u00020F8\u0002X\u0082T¢\u0006\u0006\n\u0004\bG\u0010H\"\u0014\u0010I\u001a\u00020F8\u0002X\u0082T¢\u0006\u0006\n\u0004\bI\u0010H\"\u0014\u0010J\u001a\u00020F8\u0002X\u0082T¢\u0006\u0006\n\u0004\bJ\u0010H\"\u0018\u0010N\u001a\u00020\r*\u00020K8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bL\u0010M\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006O"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "onClick", "Landroidx/compose/runtime/Composable;", ChipKt.LabelLayoutId, "Landroidx/compose/ui/Modifier;", "modifier", "", "enabled", ChipKt.LeadingIconLayoutId, ChipKt.TrailingIconLayoutId, "Landroidx/compose/ui/graphics/Shape;", "shape", "Landroidx/compose/material3/ChipColors;", "colors", "Landroidx/compose/material3/ChipElevation;", "elevation", "Landroidx/compose/foundation/BorderStroke;", "border", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "AssistChip", "(Lr7/a;Lr7/p;Landroidx/compose/ui/Modifier;ZLr7/p;Lr7/p;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/material3/ChipBorder;", "(Lr7/a;Lr7/p;Landroidx/compose/ui/Modifier;ZLr7/p;Lr7/p;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/material3/ChipBorder;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "ElevatedAssistChip", "selected", "Landroidx/compose/material3/SelectableChipColors;", "Landroidx/compose/material3/SelectableChipElevation;", "FilterChip", "(ZLr7/a;Lr7/p;Landroidx/compose/ui/Modifier;ZLr7/p;Lr7/p;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SelectableChipColors;Landroidx/compose/material3/SelectableChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "ElevatedFilterChip", "avatar", "InputChip", "(ZLr7/a;Lr7/p;Landroidx/compose/ui/Modifier;ZLr7/p;Lr7/p;Lr7/p;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SelectableChipColors;Landroidx/compose/material3/SelectableChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "icon", "SuggestionChip", "(Lr7/a;Lr7/p;Landroidx/compose/ui/Modifier;ZLr7/p;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "(Lr7/a;Lr7/p;Landroidx/compose/ui/Modifier;ZLr7/p;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/material3/ChipBorder;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "ElevatedSuggestionChip", "Landroidx/compose/ui/text/TextStyle;", "labelTextStyle", "Landroidx/compose/ui/graphics/Color;", "labelColor", "Landroidx/compose/ui/unit/Dp;", "minHeight", "Landroidx/compose/foundation/layout/PaddingValues;", "paddingValues", "Chip-nkUnTEs", "(Landroidx/compose/ui/Modifier;Lr7/a;ZLr7/p;Landroidx/compose/ui/text/TextStyle;JLr7/p;Lr7/p;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "Chip", "SelectableChip-u0RnIRE", "(ZLandroidx/compose/ui/Modifier;Lr7/a;ZLr7/p;Landroidx/compose/ui/text/TextStyle;Lr7/p;Lr7/p;Lr7/p;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SelectableChipColors;Landroidx/compose/material3/SelectableChipElevation;Landroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "SelectableChip", "leadingIconColor", "trailingIconColor", "ChipContent-fe0OD_I", "(Lr7/p;Landroidx/compose/ui/text/TextStyle;JLr7/p;Lr7/p;Lr7/p;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)V", "ChipContent", "hasAvatar", "hasLeadingIcon", "hasTrailingIcon", "inputChipPadding", "(ZZZ)Landroidx/compose/foundation/layout/PaddingValues;", "HorizontalElementsPadding", "F", "AssistChipPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "FilterChipPadding", "SuggestionChipPadding", "", "LeadingIconLayoutId", "Ljava/lang/String;", "LabelLayoutId", "TrailingIconLayoutId", "Landroidx/compose/material3/ColorScheme;", "getDefaultSuggestionChipColors", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/ChipColors;", "defaultSuggestionChipColors", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChipKt {
    private static final PaddingValues AssistChipPadding;
    private static final PaddingValues FilterChipPadding;
    private static final float HorizontalElementsPadding;
    private static final String LabelLayoutId = "label";
    private static final String LeadingIconLayoutId = "leadingIcon";
    private static final PaddingValues SuggestionChipPadding;
    private static final String TrailingIconLayoutId = "trailingIcon";

    /* JADX INFO: renamed from: androidx.compose.material3.ChipKt$AssistChip$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ BorderStroke $border;
        final /* synthetic */ ChipColors $colors;
        final /* synthetic */ ChipElevation $elevation;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(r7.a<t0> aVar, p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, boolean z, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, Shape shape, ChipColors chipColors, ChipElevation chipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i10, int i11, int i12) {
            super(2);
            this.$onClick = aVar;
            this.$label = pVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$leadingIcon = pVar2;
            this.$trailingIcon = pVar3;
            this.$shape = shape;
            this.$colors = chipColors;
            this.$elevation = chipElevation;
            this.$border = borderStroke;
            this.$interactionSource = mutableInteractionSource;
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
            ChipKt.AssistChip(this.$onClick, this.$label, this.$modifier, this.$enabled, this.$leadingIcon, this.$trailingIcon, this.$shape, this.$colors, this.$elevation, this.$border, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ChipKt$AssistChip$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ ChipBorder $border;
        final /* synthetic */ ChipColors $colors;
        final /* synthetic */ ChipElevation $elevation;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(r7.a<t0> aVar, p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, boolean z, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, Shape shape, ChipColors chipColors, ChipElevation chipElevation, ChipBorder chipBorder, MutableInteractionSource mutableInteractionSource, int i10, int i11, int i12) {
            super(2);
            this.$onClick = aVar;
            this.$label = pVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$leadingIcon = pVar2;
            this.$trailingIcon = pVar3;
            this.$shape = shape;
            this.$colors = chipColors;
            this.$elevation = chipElevation;
            this.$border = chipBorder;
            this.$interactionSource = mutableInteractionSource;
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
            ChipKt.AssistChip(this.$onClick, this.$label, this.$modifier, this.$enabled, this.$leadingIcon, this.$trailingIcon, this.$shape, this.$colors, this.$elevation, this.$border, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ChipKt$ElevatedAssistChip$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04792 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ BorderStroke $border;
        final /* synthetic */ ChipColors $colors;
        final /* synthetic */ ChipElevation $elevation;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04792(r7.a<t0> aVar, p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, boolean z, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, Shape shape, ChipColors chipColors, ChipElevation chipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i10, int i11, int i12) {
            super(2);
            this.$onClick = aVar;
            this.$label = pVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$leadingIcon = pVar2;
            this.$trailingIcon = pVar3;
            this.$shape = shape;
            this.$colors = chipColors;
            this.$elevation = chipElevation;
            this.$border = borderStroke;
            this.$interactionSource = mutableInteractionSource;
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
            ChipKt.ElevatedAssistChip(this.$onClick, this.$label, this.$modifier, this.$enabled, this.$leadingIcon, this.$trailingIcon, this.$shape, this.$colors, this.$elevation, this.$border, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ChipKt$ElevatedAssistChip$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04804 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ ChipBorder $border;
        final /* synthetic */ ChipColors $colors;
        final /* synthetic */ ChipElevation $elevation;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04804(r7.a<t0> aVar, p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, boolean z, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, Shape shape, ChipColors chipColors, ChipElevation chipElevation, ChipBorder chipBorder, MutableInteractionSource mutableInteractionSource, int i10, int i11, int i12) {
            super(2);
            this.$onClick = aVar;
            this.$label = pVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$leadingIcon = pVar2;
            this.$trailingIcon = pVar3;
            this.$shape = shape;
            this.$colors = chipColors;
            this.$elevation = chipElevation;
            this.$border = chipBorder;
            this.$interactionSource = mutableInteractionSource;
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
            ChipKt.ElevatedAssistChip(this.$onClick, this.$label, this.$modifier, this.$enabled, this.$leadingIcon, this.$trailingIcon, this.$shape, this.$colors, this.$elevation, this.$border, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ChipKt$ElevatedFilterChip$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04812 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ BorderStroke $border;
        final /* synthetic */ SelectableChipColors $colors;
        final /* synthetic */ SelectableChipElevation $elevation;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ boolean $selected;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04812(boolean z, r7.a<t0> aVar, p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, boolean z5, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, Shape shape, SelectableChipColors selectableChipColors, SelectableChipElevation selectableChipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i10, int i11, int i12) {
            super(2);
            this.$selected = z;
            this.$onClick = aVar;
            this.$label = pVar;
            this.$modifier = modifier;
            this.$enabled = z5;
            this.$leadingIcon = pVar2;
            this.$trailingIcon = pVar3;
            this.$shape = shape;
            this.$colors = selectableChipColors;
            this.$elevation = selectableChipElevation;
            this.$border = borderStroke;
            this.$interactionSource = mutableInteractionSource;
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
            ChipKt.ElevatedFilterChip(this.$selected, this.$onClick, this.$label, this.$modifier, this.$enabled, this.$leadingIcon, this.$trailingIcon, this.$shape, this.$colors, this.$elevation, this.$border, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ChipKt$ElevatedSuggestionChip$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04822 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ BorderStroke $border;
        final /* synthetic */ ChipColors $colors;
        final /* synthetic */ ChipElevation $elevation;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ p<Composer, Integer, t0> $icon;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ Shape $shape;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04822(r7.a<t0> aVar, p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, boolean z, p<? super Composer, ? super Integer, t0> pVar2, Shape shape, ChipColors chipColors, ChipElevation chipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i10, int i11) {
            super(2);
            this.$onClick = aVar;
            this.$label = pVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$icon = pVar2;
            this.$shape = shape;
            this.$colors = chipColors;
            this.$elevation = chipElevation;
            this.$border = borderStroke;
            this.$interactionSource = mutableInteractionSource;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            ChipKt.ElevatedSuggestionChip(this.$onClick, this.$label, this.$modifier, this.$enabled, this.$icon, this.$shape, this.$colors, this.$elevation, this.$border, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ChipKt$ElevatedSuggestionChip$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04834 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ ChipBorder $border;
        final /* synthetic */ ChipColors $colors;
        final /* synthetic */ ChipElevation $elevation;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ p<Composer, Integer, t0> $icon;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ Shape $shape;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04834(r7.a<t0> aVar, p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, boolean z, p<? super Composer, ? super Integer, t0> pVar2, Shape shape, ChipColors chipColors, ChipElevation chipElevation, ChipBorder chipBorder, MutableInteractionSource mutableInteractionSource, int i10, int i11) {
            super(2);
            this.$onClick = aVar;
            this.$label = pVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$icon = pVar2;
            this.$shape = shape;
            this.$colors = chipColors;
            this.$elevation = chipElevation;
            this.$border = chipBorder;
            this.$interactionSource = mutableInteractionSource;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            ChipKt.ElevatedSuggestionChip(this.$onClick, this.$label, this.$modifier, this.$enabled, this.$icon, this.$shape, this.$colors, this.$elevation, this.$border, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ChipKt$FilterChip$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04842 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ BorderStroke $border;
        final /* synthetic */ SelectableChipColors $colors;
        final /* synthetic */ SelectableChipElevation $elevation;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ boolean $selected;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04842(boolean z, r7.a<t0> aVar, p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, boolean z5, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, Shape shape, SelectableChipColors selectableChipColors, SelectableChipElevation selectableChipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i10, int i11, int i12) {
            super(2);
            this.$selected = z;
            this.$onClick = aVar;
            this.$label = pVar;
            this.$modifier = modifier;
            this.$enabled = z5;
            this.$leadingIcon = pVar2;
            this.$trailingIcon = pVar3;
            this.$shape = shape;
            this.$colors = selectableChipColors;
            this.$elevation = selectableChipElevation;
            this.$border = borderStroke;
            this.$interactionSource = mutableInteractionSource;
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
            ChipKt.FilterChip(this.$selected, this.$onClick, this.$label, this.$modifier, this.$enabled, this.$leadingIcon, this.$trailingIcon, this.$shape, this.$colors, this.$elevation, this.$border, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ChipKt$InputChip$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04852 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $avatar;
        final /* synthetic */ float $avatarOpacity;
        final /* synthetic */ Shape $avatarShape;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04852(float f10, Shape shape, p<? super Composer, ? super Integer, t0> pVar) {
            super(2);
            this.$avatarOpacity = f10;
            this.$avatarShape = shape;
            this.$avatar = pVar;
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
                ComposerKt.traceEventStart(1154227507, i10, -1, "androidx.compose.material3.InputChip.<anonymous> (Chip.kt:606)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            composer.startReplaceableGroup(946467138);
            boolean zChanged = composer.changed(this.$avatarOpacity) | composer.changed(this.$avatarShape);
            float f10 = this.$avatarOpacity;
            Shape shape = this.$avatarShape;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new ChipKt$InputChip$2$1$1(f10, shape);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(companion, (l) objRememberedValue);
            Alignment center = Alignment.INSTANCE.getCenter();
            p<Composer, Integer, t0> pVar = this.$avatar;
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierGraphicsLayer);
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
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
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

    /* JADX INFO: renamed from: androidx.compose.material3.ChipKt$InputChip$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$changed1;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $avatar;
        final /* synthetic */ BorderStroke $border;
        final /* synthetic */ SelectableChipColors $colors;
        final /* synthetic */ SelectableChipElevation $elevation;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ boolean $selected;
        final /* synthetic */ Shape $shape;
        final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(boolean z, r7.a<t0> aVar, p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, boolean z5, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, Shape shape, SelectableChipColors selectableChipColors, SelectableChipElevation selectableChipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i10, int i11, int i12) {
            super(2);
            this.$selected = z;
            this.$onClick = aVar;
            this.$label = pVar;
            this.$modifier = modifier;
            this.$enabled = z5;
            this.$leadingIcon = pVar2;
            this.$avatar = pVar3;
            this.$trailingIcon = pVar4;
            this.$shape = shape;
            this.$colors = selectableChipColors;
            this.$elevation = selectableChipElevation;
            this.$border = borderStroke;
            this.$interactionSource = mutableInteractionSource;
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
            ChipKt.InputChip(this.$selected, this.$onClick, this.$label, this.$modifier, this.$enabled, this.$leadingIcon, this.$avatar, this.$trailingIcon, this.$shape, this.$colors, this.$elevation, this.$border, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), RecomposeScopeImplKt.updateChangedFlags(this.$$changed1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ChipKt$SuggestionChip$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04862 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ BorderStroke $border;
        final /* synthetic */ ChipColors $colors;
        final /* synthetic */ ChipElevation $elevation;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ p<Composer, Integer, t0> $icon;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ Shape $shape;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04862(r7.a<t0> aVar, p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, boolean z, p<? super Composer, ? super Integer, t0> pVar2, Shape shape, ChipColors chipColors, ChipElevation chipElevation, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, int i10, int i11) {
            super(2);
            this.$onClick = aVar;
            this.$label = pVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$icon = pVar2;
            this.$shape = shape;
            this.$colors = chipColors;
            this.$elevation = chipElevation;
            this.$border = borderStroke;
            this.$interactionSource = mutableInteractionSource;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            ChipKt.SuggestionChip(this.$onClick, this.$label, this.$modifier, this.$enabled, this.$icon, this.$shape, this.$colors, this.$elevation, this.$border, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.ChipKt$SuggestionChip$4, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04874 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ ChipBorder $border;
        final /* synthetic */ ChipColors $colors;
        final /* synthetic */ ChipElevation $elevation;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ p<Composer, Integer, t0> $icon;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ Shape $shape;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04874(r7.a<t0> aVar, p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, boolean z, p<? super Composer, ? super Integer, t0> pVar2, Shape shape, ChipColors chipColors, ChipElevation chipElevation, ChipBorder chipBorder, MutableInteractionSource mutableInteractionSource, int i10, int i11) {
            super(2);
            this.$onClick = aVar;
            this.$label = pVar;
            this.$modifier = modifier;
            this.$enabled = z;
            this.$icon = pVar2;
            this.$shape = shape;
            this.$colors = chipColors;
            this.$elevation = chipElevation;
            this.$border = chipBorder;
            this.$interactionSource = mutableInteractionSource;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            ChipKt.SuggestionChip(this.$onClick, this.$label, this.$modifier, this.$enabled, this.$icon, this.$shape, this.$colors, this.$elevation, this.$border, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    static {
        float fM5678constructorimpl = Dp.m5678constructorimpl(8);
        HorizontalElementsPadding = fM5678constructorimpl;
        AssistChipPadding = PaddingKt.m524PaddingValuesYgX7TsA$default(fM5678constructorimpl, 0.0f, 2, null);
        FilterChipPadding = PaddingKt.m524PaddingValuesYgX7TsA$default(fM5678constructorimpl, 0.0f, 2, null);
        SuggestionChipPadding = PaddingKt.m524PaddingValuesYgX7TsA$default(fM5678constructorimpl, 0.0f, 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0309  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:192:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0108  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AssistChip(r7.a<x6.t0> r35, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r36, androidx.compose.ui.Modifier r37, boolean r38, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r39, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r40, androidx.compose.ui.graphics.Shape r41, androidx.compose.material3.ChipColors r42, androidx.compose.material3.ChipElevation r43, androidx.compose.foundation.BorderStroke r44, androidx.compose.foundation.interaction.MutableInteractionSource r45, androidx.compose.runtime.Composer r46, int r47, int r48, int r49) {
        /*
            Method dump skipped, instruction units count: 820
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ChipKt.AssistChip(r7.a, r7.p, androidx.compose.ui.Modifier, boolean, r7.p, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.ChipColors, androidx.compose.material3.ChipElevation, androidx.compose.foundation.BorderStroke, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Chip-nkUnTEs, reason: not valid java name */
    public static final void m1341ChipnkUnTEs(Modifier modifier, r7.a<t0> aVar, boolean z, p<? super Composer, ? super Integer, t0> pVar, TextStyle textStyle, long j10, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, Shape shape, ChipColors chipColors, ChipElevation chipElevation, BorderStroke borderStroke, float f10, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, Composer composer, int i10, int i11) {
        int i12;
        p<? super Composer, ? super Integer, t0> pVar4;
        int i13;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1400504719);
        if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(aVar) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            pVar4 = pVar;
            i12 |= composerStartRestartGroup.changedInstance(pVar4) ? 2048 : 1024;
        } else {
            pVar4 = pVar;
        }
        if ((i10 & 24576) == 0) {
            i12 |= composerStartRestartGroup.changed(textStyle) ? 16384 : 8192;
        }
        if ((i10 & 196608) == 0) {
            i12 |= composerStartRestartGroup.changed(j10) ? 131072 : 65536;
        }
        if ((i10 & 1572864) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar2) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i10 & 12582912) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar3) ? 8388608 : 4194304;
        }
        if ((i10 & 100663296) == 0) {
            i12 |= composerStartRestartGroup.changed(shape) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i10 & 805306368) == 0) {
            i12 |= composerStartRestartGroup.changed(chipColors) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i11 & 6) == 0) {
            i13 = i11 | (composerStartRestartGroup.changed(chipElevation) ? 4 : 2);
        } else {
            i13 = i11;
        }
        if ((i11 & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(borderStroke) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changed(f10) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i13 |= composerStartRestartGroup.changed(paddingValues) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i13 |= composerStartRestartGroup.changed(mutableInteractionSource) ? 16384 : 8192;
        }
        int i14 = i13;
        if ((i12 & 306783379) == 306783378 && (i14 & 9363) == 9362 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1400504719, i12, i14, "androidx.compose.material3.Chip (Chip.kt:1861)");
            }
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier, false, ChipKt$Chip$1.INSTANCE, 1, null);
            long jM1321containerColorvNxB06k$material3_release = chipColors.m1321containerColorvNxB06k$material3_release(z);
            float fM1340tonalElevationu2uoSUM$material3_release = chipElevation != null ? chipElevation.m1340tonalElevationu2uoSUM$material3_release(z) : Dp.m5678constructorimpl(0);
            composerStartRestartGroup.startReplaceableGroup(64045604);
            State<Dp> stateShadowElevation$material3_release = chipElevation != null ? chipElevation.shadowElevation$material3_release(z, mutableInteractionSource, composerStartRestartGroup, ((i12 >> 6) & 14) | ((i14 >> 9) & 112) | ((i14 << 6) & 896)) : null;
            composerStartRestartGroup.endReplaceableGroup();
            composer2 = composerStartRestartGroup;
            int i15 = i12;
            SurfaceKt.m2007Surfaceo_FOJdg(aVar, modifierSemantics$default, z, shape, jM1321containerColorvNxB06k$material3_release, 0L, fM1340tonalElevationu2uoSUM$material3_release, stateShadowElevation$material3_release != null ? stateShadowElevation$material3_release.getValue().m5692unboximpl() : Dp.m5678constructorimpl(0), borderStroke, mutableInteractionSource, ComposableLambdaKt.composableLambda(composer2, -1985962652, true, new ChipKt$Chip$2(pVar4, textStyle, j10, pVar2, pVar3, chipColors, z, f10, paddingValues)), composer2, ((i15 >> 15) & 7168) | ((i15 >> 3) & 14) | (i15 & 896) | ((i14 << 21) & 234881024) | (1879048192 & (i14 << 15)), 6, 32);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new ChipKt$Chip$3(modifier, aVar, z, pVar, textStyle, j10, pVar2, pVar3, shape, chipColors, chipElevation, borderStroke, f10, paddingValues, mutableInteractionSource, i10, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ChipContent-fe0OD_I, reason: not valid java name */
    public static final void m1342ChipContentfe0OD_I(p<? super Composer, ? super Integer, t0> pVar, TextStyle textStyle, long j10, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, long j11, long j12, float f10, PaddingValues paddingValues, Composer composer, int i10) {
        int i11;
        long j13;
        p<? super Composer, ? super Integer, t0> pVar5;
        p<? super Composer, ? super Integer, t0> pVar6;
        p<? super Composer, ? super Integer, t0> pVar7;
        long j14;
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(-782878228);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(pVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(textStyle) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            j13 = j10;
            i11 |= composerStartRestartGroup.changed(j13) ? 256 : 128;
        } else {
            j13 = j10;
        }
        if ((i10 & 3072) == 0) {
            pVar5 = pVar2;
            i11 |= composerStartRestartGroup.changedInstance(pVar5) ? 2048 : 1024;
        } else {
            pVar5 = pVar2;
        }
        if ((i10 & 24576) == 0) {
            pVar6 = pVar3;
            i11 |= composerStartRestartGroup.changedInstance(pVar6) ? 16384 : 8192;
        } else {
            pVar6 = pVar3;
        }
        if ((196608 & i10) == 0) {
            pVar7 = pVar4;
            i11 |= composerStartRestartGroup.changedInstance(pVar7) ? 131072 : 65536;
        } else {
            pVar7 = pVar4;
        }
        if ((1572864 & i10) == 0) {
            j14 = j11;
            i11 |= composerStartRestartGroup.changed(j14) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        } else {
            j14 = j11;
        }
        if ((12582912 & i10) == 0) {
            i12 = i11 | (composerStartRestartGroup.changed(j12) ? 8388608 : 4194304);
        } else {
            i12 = i11;
        }
        if ((100663296 & i10) == 0) {
            i12 |= composerStartRestartGroup.changed(f10) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i10 & 805306368) == 0) {
            i12 |= composerStartRestartGroup.changed(paddingValues) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i12 & 306783379) == 306783378 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-782878228, i12, -1, "androidx.compose.material3.ChipContent (Chip.kt:1947)");
            }
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(j13)), TextKt.getLocalTextStyle().provides(textStyle)}, ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1748799148, true, new ChipKt$ChipContent$1(f10, paddingValues, pVar6, pVar5, pVar7, j14, pVar, j12)), composerStartRestartGroup, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new ChipKt$ChipContent$2(pVar, textStyle, j13, pVar2, pVar3, pVar4, j11, j12, f10, paddingValues, i10));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:189:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0108  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ElevatedAssistChip(r7.a<x6.t0> r35, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r36, androidx.compose.ui.Modifier r37, boolean r38, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r39, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r40, androidx.compose.ui.graphics.Shape r41, androidx.compose.material3.ChipColors r42, androidx.compose.material3.ChipElevation r43, androidx.compose.foundation.BorderStroke r44, androidx.compose.foundation.interaction.MutableInteractionSource r45, androidx.compose.runtime.Composer r46, int r47, int r48, int r49) {
        /*
            Method dump skipped, instruction units count: 786
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ChipKt.ElevatedAssistChip(r7.a, r7.p, androidx.compose.ui.Modifier, boolean, r7.p, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.ChipColors, androidx.compose.material3.ChipElevation, androidx.compose.foundation.BorderStroke, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:202:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0113  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ElevatedFilterChip(boolean r36, r7.a<x6.t0> r37, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r38, androidx.compose.ui.Modifier r39, boolean r40, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r41, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r42, androidx.compose.ui.graphics.Shape r43, androidx.compose.material3.SelectableChipColors r44, androidx.compose.material3.SelectableChipElevation r45, androidx.compose.foundation.BorderStroke r46, androidx.compose.foundation.interaction.MutableInteractionSource r47, androidx.compose.runtime.Composer r48, int r49, int r50, int r51) {
        /*
            Method dump skipped, instruction units count: 845
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ChipKt.ElevatedFilterChip(boolean, r7.a, r7.p, androidx.compose.ui.Modifier, boolean, r7.p, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.SelectableChipColors, androidx.compose.material3.SelectableChipElevation, androidx.compose.foundation.BorderStroke, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:176:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ElevatedSuggestionChip(r7.a<x6.t0> r32, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r33, androidx.compose.ui.Modifier r34, boolean r35, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r36, androidx.compose.ui.graphics.Shape r37, androidx.compose.material3.ChipColors r38, androidx.compose.material3.ChipElevation r39, androidx.compose.foundation.BorderStroke r40, androidx.compose.foundation.interaction.MutableInteractionSource r41, androidx.compose.runtime.Composer r42, int r43, int r44) {
        /*
            Method dump skipped, instruction units count: 713
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ChipKt.ElevatedSuggestionChip(r7.a, r7.p, androidx.compose.ui.Modifier, boolean, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.ChipColors, androidx.compose.material3.ChipElevation, androidx.compose.foundation.BorderStroke, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:204:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0113  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void FilterChip(boolean r41, r7.a<x6.t0> r42, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r43, androidx.compose.ui.Modifier r44, boolean r45, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r46, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r47, androidx.compose.ui.graphics.Shape r48, androidx.compose.material3.SelectableChipColors r49, androidx.compose.material3.SelectableChipElevation r50, androidx.compose.foundation.BorderStroke r51, androidx.compose.foundation.interaction.MutableInteractionSource r52, androidx.compose.runtime.Composer r53, int r54, int r55, int r56) {
        /*
            Method dump skipped, instruction units count: 891
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ChipKt.FilterChip(boolean, r7.a, r7.p, androidx.compose.ui.Modifier, boolean, r7.p, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.SelectableChipColors, androidx.compose.material3.SelectableChipElevation, androidx.compose.foundation.BorderStroke, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0357  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x03ce  */
    /* JADX WARN: Removed duplicated region for block: B:238:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0116  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void InputChip(boolean r42, r7.a<x6.t0> r43, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r44, androidx.compose.ui.Modifier r45, boolean r46, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r47, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r48, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r49, androidx.compose.ui.graphics.Shape r50, androidx.compose.material3.SelectableChipColors r51, androidx.compose.material3.SelectableChipElevation r52, androidx.compose.foundation.BorderStroke r53, androidx.compose.foundation.interaction.MutableInteractionSource r54, androidx.compose.runtime.Composer r55, int r56, int r57, int r58) {
        /*
            Method dump skipped, instruction units count: 996
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ChipKt.InputChip(boolean, r7.a, r7.p, androidx.compose.ui.Modifier, boolean, r7.p, r7.p, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.SelectableChipColors, androidx.compose.material3.SelectableChipElevation, androidx.compose.foundation.BorderStroke, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: SelectableChip-u0RnIRE, reason: not valid java name */
    public static final void m1343SelectableChipu0RnIRE(boolean z, Modifier modifier, r7.a<t0> aVar, boolean z5, p<? super Composer, ? super Integer, t0> pVar, TextStyle textStyle, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, Shape shape, SelectableChipColors selectableChipColors, SelectableChipElevation selectableChipElevation, BorderStroke borderStroke, float f10, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, Composer composer, int i10, int i11) {
        int i12;
        int i13;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(402951308);
        if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(aVar) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changed(z5) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 16384 : 8192;
        }
        if ((i10 & 196608) == 0) {
            i12 |= composerStartRestartGroup.changed(textStyle) ? 131072 : 65536;
        }
        if ((i10 & 1572864) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar2) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i10 & 12582912) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar3) ? 8388608 : 4194304;
        }
        if ((i10 & 100663296) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar4) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i10 & 805306368) == 0) {
            i12 |= composerStartRestartGroup.changed(shape) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i11 & 6) == 0) {
            i13 = i11 | (composerStartRestartGroup.changed(selectableChipColors) ? 4 : 2);
        } else {
            i13 = i11;
        }
        if ((i11 & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(selectableChipElevation) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changed(borderStroke) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i13 |= composerStartRestartGroup.changed(f10) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i13 |= composerStartRestartGroup.changed(paddingValues) ? 16384 : 8192;
        }
        if ((i11 & 196608) == 0) {
            i13 |= composerStartRestartGroup.changed(mutableInteractionSource) ? 131072 : 65536;
        }
        int i14 = i13;
        if ((i12 & 306783379) == 306783378 && (74899 & i14) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(402951308, i12, i14, "androidx.compose.material3.SelectableChip (Chip.kt:1906)");
            }
            int i15 = i12;
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier, false, ChipKt$SelectableChip$1.INSTANCE, 1, null);
            int i16 = (i15 >> 9) & 14;
            long jM3482unboximpl = selectableChipColors.containerColor$material3_release(z5, z, composerStartRestartGroup, ((i15 << 3) & 112) | i16 | ((i14 << 6) & 896)).getValue().m3482unboximpl();
            float fM1859tonalElevationu2uoSUM$material3_release = selectableChipElevation != null ? selectableChipElevation.m1859tonalElevationu2uoSUM$material3_release(z5) : Dp.m5678constructorimpl(0);
            composerStartRestartGroup.startReplaceableGroup(1036687729);
            State<Dp> stateShadowElevation$material3_release = selectableChipElevation == null ? null : selectableChipElevation.shadowElevation$material3_release(z5, mutableInteractionSource, composerStartRestartGroup, i16 | ((i14 >> 12) & 112) | ((i14 << 3) & 896));
            composerStartRestartGroup.endReplaceableGroup();
            composer2 = composerStartRestartGroup;
            SurfaceKt.m2005Surfaced85dljk(z, aVar, modifierSemantics$default, z5, shape, jM3482unboximpl, 0L, fM1859tonalElevationu2uoSUM$material3_release, stateShadowElevation$material3_release != null ? stateShadowElevation$material3_release.getValue().m5692unboximpl() : Dp.m5678constructorimpl(0), borderStroke, mutableInteractionSource, ComposableLambdaKt.composableLambda(composer2, -577614814, true, new ChipKt$SelectableChip$2(selectableChipColors, z5, z, pVar, textStyle, pVar2, pVar3, pVar4, f10, paddingValues)), composer2, (i15 & 14) | ((i15 >> 3) & 112) | (i15 & 7168) | ((i15 >> 15) & 57344) | ((i14 << 21) & 1879048192), ((i14 >> 15) & 14) | 48, 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new ChipKt$SelectableChip$3(z, modifier, aVar, z5, pVar, textStyle, pVar2, pVar3, pVar4, shape, selectableChipColors, selectableChipElevation, borderStroke, f10, paddingValues, mutableInteractionSource, i10, i11));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:179:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x010a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SuggestionChip(r7.a<x6.t0> r32, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r33, androidx.compose.ui.Modifier r34, boolean r35, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r36, androidx.compose.ui.graphics.Shape r37, androidx.compose.material3.ChipColors r38, androidx.compose.material3.ChipElevation r39, androidx.compose.foundation.BorderStroke r40, androidx.compose.foundation.interaction.MutableInteractionSource r41, androidx.compose.runtime.Composer r42, int r43, int r44) {
        /*
            Method dump skipped, instruction units count: 755
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ChipKt.SuggestionChip(r7.a, r7.p, androidx.compose.ui.Modifier, boolean, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.ChipColors, androidx.compose.material3.ChipElevation, androidx.compose.foundation.BorderStroke, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final ChipColors getDefaultSuggestionChipColors(ColorScheme colorScheme) {
        ChipColors defaultSuggestionChipColorsCached = colorScheme.getDefaultSuggestionChipColorsCached();
        if (defaultSuggestionChipColorsCached != null) {
            return defaultSuggestionChipColorsCached;
        }
        Color.Companion companion = Color.INSTANCE;
        long jM3507getTransparent0d7_KjU = companion.m3507getTransparent0d7_KjU();
        SuggestionChipTokens suggestionChipTokens = SuggestionChipTokens.INSTANCE;
        ChipColors chipColors = new ChipColors(jM3507getTransparent0d7_KjU, ColorSchemeKt.fromToken(colorScheme, suggestionChipTokens.getLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, suggestionChipTokens.getLeadingIconColor()), companion.m3508getUnspecified0d7_KjU(), companion.m3507getTransparent0d7_KjU(), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, suggestionChipTokens.getDisabledLabelTextColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, suggestionChipTokens.getDisabledLeadingIconColor()), 0.38f, 0.0f, 0.0f, 0.0f, 14, null), companion.m3508getUnspecified0d7_KjU(), null);
        colorScheme.setDefaultSuggestionChipColorsCached$material3_release(chipColors);
        return chipColors;
    }

    private static final PaddingValues inputChipPadding(boolean z, boolean z5, boolean z10) {
        return PaddingKt.m526PaddingValuesa9UjIt4$default(Dp.m5678constructorimpl((z || !z5) ? 4 : 8), 0.0f, Dp.m5678constructorimpl(z10 ? 8 : 4), 0.0f, 10, null);
    }

    public static /* synthetic */ PaddingValues inputChipPadding$default(boolean z, boolean z5, boolean z10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        if ((i10 & 2) != 0) {
            z5 = false;
        }
        if ((i10 & 4) != 0) {
            z10 = false;
        }
        return inputChipPadding(z, z5, z10);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x022e  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:195:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0108  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void ElevatedAssistChip(r7.a r35, r7.p r36, androidx.compose.ui.Modifier r37, boolean r38, r7.p r39, r7.p r40, androidx.compose.ui.graphics.Shape r41, androidx.compose.material3.ChipColors r42, androidx.compose.material3.ChipElevation r43, androidx.compose.material3.ChipBorder r44, androidx.compose.foundation.interaction.MutableInteractionSource r45, androidx.compose.runtime.Composer r46, int r47, int r48, int r49) {
        /*
            Method dump skipped, instruction units count: 824
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ChipKt.ElevatedAssistChip(r7.a, r7.p, androidx.compose.ui.Modifier, boolean, r7.p, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.ChipColors, androidx.compose.material3.ChipElevation, androidx.compose.material3.ChipBorder, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:182:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00fb  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void ElevatedSuggestionChip(r7.a r32, r7.p r33, androidx.compose.ui.Modifier r34, boolean r35, r7.p r36, androidx.compose.ui.graphics.Shape r37, androidx.compose.material3.ChipColors r38, androidx.compose.material3.ChipElevation r39, androidx.compose.material3.ChipBorder r40, androidx.compose.foundation.interaction.MutableInteractionSource r41, androidx.compose.runtime.Composer r42, int r43, int r44) {
        /*
            Method dump skipped, instruction units count: 748
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ChipKt.ElevatedSuggestionChip(r7.a, r7.p, androidx.compose.ui.Modifier, boolean, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.ChipColors, androidx.compose.material3.ChipElevation, androidx.compose.material3.ChipBorder, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:199:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0108  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void AssistChip(r7.a r35, r7.p r36, androidx.compose.ui.Modifier r37, boolean r38, r7.p r39, r7.p r40, androidx.compose.ui.graphics.Shape r41, androidx.compose.material3.ChipColors r42, androidx.compose.material3.ChipElevation r43, androidx.compose.material3.ChipBorder r44, androidx.compose.foundation.interaction.MutableInteractionSource r45, androidx.compose.runtime.Composer r46, int r47, int r48, int r49) {
        /*
            Method dump skipped, instruction units count: 850
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ChipKt.AssistChip(r7.a, r7.p, androidx.compose.ui.Modifier, boolean, r7.p, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.ChipColors, androidx.compose.material3.ChipElevation, androidx.compose.material3.ChipBorder, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:186:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x010a  */
    @x6.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final /* synthetic */ void SuggestionChip(r7.a r32, r7.p r33, androidx.compose.ui.Modifier r34, boolean r35, r7.p r36, androidx.compose.ui.graphics.Shape r37, androidx.compose.material3.ChipColors r38, androidx.compose.material3.ChipElevation r39, androidx.compose.material3.ChipBorder r40, androidx.compose.foundation.interaction.MutableInteractionSource r41, androidx.compose.runtime.Composer r42, int r43, int r44) {
        /*
            Method dump skipped, instruction units count: 779
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ChipKt.SuggestionChip(r7.a, r7.p, androidx.compose.ui.Modifier, boolean, r7.p, androidx.compose.ui.graphics.Shape, androidx.compose.material3.ChipColors, androidx.compose.material3.ChipElevation, androidx.compose.material3.ChipBorder, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }
}
