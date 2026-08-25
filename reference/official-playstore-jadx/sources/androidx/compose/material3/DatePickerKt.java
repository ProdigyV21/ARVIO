package androidx.compose.material3;

import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.LazyListStateKt;
import androidx.compose.foundation.lazy.grid.GridCells;
import androidx.compose.foundation.lazy.grid.LazyGridDslKt;
import androidx.compose.foundation.lazy.grid.LazyGridState;
import androidx.compose.foundation.lazy.grid.LazyGridStateKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ArrowDropDownKt;
import androidx.compose.material3.Strings;
import androidx.compose.material3.tokens.DatePickerModalTokens;
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
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.RotateKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.android.gms.cast.MediaTrack;
import d7.k;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import ka.k0;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;
import x6.x;
import x7.i;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Î\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\u001am\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\b\b2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\b\b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\rH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001aH\u0010\u001c\u001a\u00020\u00002\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001aT\u0010\"\u001a\u00020\u00002\n\u0010\u001f\u001a\u00060\u001dj\u0002`\u001e2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0019\u001a\u00020\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a\u0084\u0001\u0010+\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0013\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\b\b2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\b\b2\u0013\u0010#\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\b\b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010%\u001a\u00020$2\u0006\u0010'\u001a\u00020&2\u0011\u0010(\u001a\r\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0001ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a6\u00101\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010,\u001a\u00020\u00162\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00070-H\u0001ø\u0001\u0000¢\u0006\u0004\b/\u00100\u001a\u009c\u0001\u0010>\u001a\u00020\u00072\b\u00102\u001a\u0004\u0018\u00010\u00112\u0006\u00103\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u00162#\u00107\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0011¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u00070-2!\u00109\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020\u00070-2\u0006\u0010;\u001a\u00020:2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\rH\u0003ø\u0001\u0000¢\u0006\u0004\b<\u0010=\u001a\u008f\u0001\u0010?\u001a\u00020\u00072\b\u00102\u001a\u0004\u0018\u00010\u00112\u0006\u00103\u001a\u00020\u00112!\u00107\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u00070-2!\u00109\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020\u00070-2\u0006\u0010;\u001a\u00020:2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\rH\u0003¢\u0006\u0004\b?\u0010@\u001aZ\u0010G\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0013\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0002\b\b2\u0006\u0010B\u001a\u00020A2\u0006\u0010C\u001a\u00020A2\u0006\u0010D\u001a\u00020&2\u0011\u0010(\u001a\r\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0001ø\u0001\u0000¢\u0006\u0004\bE\u0010F\u001a\u008f\u0001\u0010J\u001a\u00020\u00072\u0006\u0010I\u001a\u00020H2\b\u00102\u001a\u0004\u0018\u00010\u00112!\u00107\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u00070-2!\u00109\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020\u00070-2\u0006\u0010;\u001a\u00020:2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\rH\u0003¢\u0006\u0004\bJ\u0010K\u001aK\u0010L\u001a\u00020\u00072\u0006\u0010I\u001a\u00020H2!\u00109\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(8\u0012\u0004\u0012\u00020\u00070-2\u0006\u0010;\u001a\u00020:2\u0006\u0010\u0015\u001a\u00020\u0014H\u0080@¢\u0006\u0004\bL\u0010M\u001a\u001f\u0010N\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010;\u001a\u00020:H\u0001¢\u0006\u0004\bN\u0010O\u001ax\u0010W\u001a\u00020\u00072\u0006\u0010Q\u001a\u00020P2!\u00107\u001a\u001d\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020\u00070-2\u0006\u0010R\u001a\u00020\u00112\b\u0010S\u001a\u0004\u0018\u00010\u00112\b\u0010T\u001a\u0004\u0018\u00010\u00112\b\u0010V\u001a\u0004\u0018\u00010U2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\rH\u0001¢\u0006\u0004\bW\u0010X\u001a\u0017\u0010Z\u001a\u00020Y2\u0006\u0010\u0015\u001a\u00020\u0014H\u0000¢\u0006\u0004\bZ\u0010[\u001a9\u0010b\u001a\u0004\u0018\u00010a2\u0006\u0010\\\u001a\u00020\u000b2\u0006\u0010]\u001a\u00020\u000b2\u0006\u0010^\u001a\u00020\u000b2\u0006\u0010_\u001a\u00020\u000b2\u0006\u0010`\u001a\u00020\u000bH\u0003¢\u0006\u0004\bb\u0010c\u001ap\u0010k\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010d\u001a\u00020\u000b2\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010f\u001a\u00020\u000b2\u0006\u0010g\u001a\u00020\u000b2\u0006\u0010h\u001a\u00020\u000b2\u0006\u0010i\u001a\u00020\u000b2\u0006\u0010j\u001a\u00020a2\u0006\u0010\u000e\u001a\u00020\r2\u0011\u0010(\u001a\r\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0003¢\u0006\u0004\bk\u0010l\u001ab\u0010o\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u00103\u001a\u00020\u00112!\u0010n\u001a\u001d\u0012\u0013\u0012\u00110Y¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(m\u0012\u0004\u0012\u00020\u00070-2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010;\u001a\u00020:2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\rH\u0003¢\u0006\u0004\bo\u0010p\u001a`\u0010r\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010d\u001a\u00020\u000b2\u0006\u0010q\u001a\u00020\u000b2\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010g\u001a\u00020\u000b2\u0006\u0010j\u001a\u00020a2\u0006\u0010\u000e\u001a\u00020\r2\u0011\u0010(\u001a\r\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0003¢\u0006\u0004\br\u0010s\u001ai\u0010{\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010t\u001a\u00020\u000b2\u0006\u0010u\u001a\u00020\u000b2\u0006\u0010v\u001a\u00020\u000b2\u0006\u0010w\u001a\u00020a2\f\u0010x\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010y\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0003¢\u0006\u0004\b{\u0010|\u001aB\u0010~\u001a\u00020\u00072\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010}\u001a\u00020\u000b2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0011\u0010(\u001a\r\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\b\bH\u0003¢\u0006\u0004\b~\u0010\u007f\u001a?\u0010\u0087\u0001\u001a\n\u0012\u0005\u0012\u00030\u0086\u00010\u0085\u00012\u0007\u0010\u0001\u001a\u00030\u0080\u00012\b\u0010\u0082\u0001\u001a\u00030\u0081\u00012\u0007\u0010\u0083\u0001\u001a\u00020a2\u0007\u0010\u0084\u0001\u001a\u00020aH\u0002¢\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001\"\u001f\u0010\u0089\u0001\u001a\u00020&8\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u0089\u0001\u0010\u008a\u0001\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001\"\u001f\u0010\u008d\u0001\u001a\u00020&8\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u008d\u0001\u0010\u008a\u0001\u001a\u0006\b\u008e\u0001\u0010\u008c\u0001\"\u001f\u0010\u008f\u0001\u001a\u00020&8\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u008f\u0001\u0010\u008a\u0001\u001a\u0006\b\u0090\u0001\u0010\u008c\u0001\" \u0010\u0092\u0001\u001a\u00030\u0091\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u0092\u0001\u0010\u0093\u0001\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001\"\u0018\u0010\u0096\u0001\u001a\u00030\u0091\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0096\u0001\u0010\u0093\u0001\"\u0018\u0010\u0097\u0001\u001a\u00030\u0091\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0097\u0001\u0010\u0093\u0001\"\u0017\u0010\u0098\u0001\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0098\u0001\u0010\u008a\u0001\"\u0017\u0010\u0099\u0001\u001a\u00020Y8\u0002X\u0082T¢\u0006\b\n\u0006\b\u0099\u0001\u0010\u009a\u0001\"\u0017\u0010\u009b\u0001\u001a\u00020Y8\u0002X\u0082T¢\u0006\b\n\u0006\b\u009b\u0001\u0010\u009a\u0001\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u009c\u0001²\u0006\u000e\u0010v\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002"}, d2 = {"Landroidx/compose/material3/DatePickerState;", "state", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/material3/DatePickerFormatter;", "dateFormatter", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", LinkHeader.Parameters.Title, "headline", "", "showModeToggle", "Landroidx/compose/material3/DatePickerColors;", "colors", "DatePicker", "(Landroidx/compose/material3/DatePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DatePickerFormatter;Lr7/p;Lr7/p;ZLandroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;II)V", "", "initialSelectedDateMillis", "initialDisplayedMonthMillis", "Lx7/i;", "yearRange", "Landroidx/compose/material3/DisplayMode;", "initialDisplayMode", "Landroidx/compose/material3/SelectableDates;", "selectableDates", "rememberDatePickerState-EU0dCGE", "(Ljava/lang/Long;Ljava/lang/Long;Lx7/i;ILandroidx/compose/material3/SelectableDates;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DatePickerState;", "rememberDatePickerState", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "locale", "DatePickerState-sHin3Bw", "(Ljava/util/Locale;Ljava/lang/Long;Ljava/lang/Long;Lx7/i;ILandroidx/compose/material3/SelectableDates;)Landroidx/compose/material3/DatePickerState;", "DatePickerState", "modeToggleButton", "Landroidx/compose/ui/text/TextStyle;", "headlineTextStyle", "Landroidx/compose/ui/unit/Dp;", "headerMinHeight", "content", "DateEntryContainer-au3_HiA", "(Landroidx/compose/ui/Modifier;Lr7/p;Lr7/p;Lr7/p;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/text/TextStyle;FLr7/p;Landroidx/compose/runtime/Composer;I)V", "DateEntryContainer", "displayMode", "Lkotlin/Function1;", "onDisplayModeChange", "DisplayModeToggleButton-tER2X8s", "(Landroidx/compose/ui/Modifier;ILr7/l;Landroidx/compose/runtime/Composer;I)V", "DisplayModeToggleButton", "selectedDateMillis", "displayedMonthMillis", "Lx6/y;", ContentDisposition.Parameters.Name, "dateInMillis", "onDateSelectionChange", "monthInMillis", "onDisplayedMonthChange", "Landroidx/compose/material3/CalendarModel;", "calendarModel", "SwitchableDateEntryContent-d7iavvg", "(Ljava/lang/Long;JILr7/l;Lr7/l;Landroidx/compose/material3/CalendarModel;Lx7/i;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "SwitchableDateEntryContent", "DatePickerContent", "(Ljava/lang/Long;JLr7/l;Lr7/l;Landroidx/compose/material3/CalendarModel;Lx7/i;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/graphics/Color;", "titleContentColor", "headlineContentColor", "minHeight", "DatePickerHeader-pc5RIQQ", "(Landroidx/compose/ui/Modifier;Lr7/p;JJFLr7/p;Landroidx/compose/runtime/Composer;I)V", "DatePickerHeader", "Landroidx/compose/foundation/lazy/LazyListState;", "lazyListState", "HorizontalMonthsList", "(Landroidx/compose/foundation/lazy/LazyListState;Ljava/lang/Long;Lr7/l;Lr7/l;Landroidx/compose/material3/CalendarModel;Lx7/i;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "updateDisplayedMonth", "(Landroidx/compose/foundation/lazy/LazyListState;Lr7/l;Landroidx/compose/material3/CalendarModel;Lx7/i;Ld7/d;)Ljava/lang/Object;", "WeekDays", "(Landroidx/compose/material3/DatePickerColors;Landroidx/compose/material3/CalendarModel;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/material3/CalendarMonth;", "month", "todayMillis", "startDateMillis", "endDateMillis", "Landroidx/compose/material3/SelectedRangeInfo;", "rangeSelectionInfo", "Month", "(Landroidx/compose/material3/CalendarMonth;Lr7/l;JLjava/lang/Long;Ljava/lang/Long;Landroidx/compose/material3/SelectedRangeInfo;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "", "numberOfMonthsInRange", "(Lx7/i;)I", "rangeSelectionEnabled", "isToday", "isStartDate", "isEndDate", "isInRange", "", "dayContentDescription", "(ZZZZZLandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "selected", "onClick", "animateChecked", "enabled", "today", "inRange", MediaTrack.ROLE_DESCRIPTION, "Day", "(Landroidx/compose/ui/Modifier;ZLr7/a;ZZZZLjava/lang/String;Landroidx/compose/material3/DatePickerColors;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "year", "onYearSelected", "YearPicker", "(Landroidx/compose/ui/Modifier;JLr7/l;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/CalendarModel;Lx7/i;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "currentYear", "Year", "(Landroidx/compose/ui/Modifier;ZZLr7/a;ZLjava/lang/String;Landroidx/compose/material3/DatePickerColors;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "nextAvailable", "previousAvailable", "yearPickerVisible", "yearPickerText", "onNextClicked", "onPreviousClicked", "onYearPickerButtonClicked", "MonthsNavigation", "(Landroidx/compose/ui/Modifier;ZZZLjava/lang/String;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/runtime/Composer;I)V", "expanded", "YearPickerMenuButton", "(Lr7/a;ZLandroidx/compose/ui/Modifier;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "Lka/k0;", "coroutineScope", "scrollUpLabel", "scrollDownLabel", "", "Landroidx/compose/ui/semantics/CustomAccessibilityAction;", "customScrollActions", "(Landroidx/compose/foundation/lazy/grid/LazyGridState;Lka/k0;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;", "RecommendedSizeForAccessibility", "F", "getRecommendedSizeForAccessibility", "()F", "MonthYearHeight", "getMonthYearHeight", "DatePickerHorizontalPadding", "getDatePickerHorizontalPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "DatePickerModeTogglePadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getDatePickerModeTogglePadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "DatePickerTitlePadding", "DatePickerHeadlinePadding", "YearsVerticalPadding", "MaxCalendarRows", "I", "YearsInRow", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DatePickerKt {
    private static final PaddingValues DatePickerHeadlinePadding;
    private static final float DatePickerHorizontalPadding;
    private static final PaddingValues DatePickerModeTogglePadding;
    private static final PaddingValues DatePickerTitlePadding;
    private static final int MaxCalendarRows = 6;
    private static final int YearsInRow = 3;
    private static final float YearsVerticalPadding;
    private static final float RecommendedSizeForAccessibility = Dp.m5678constructorimpl(48);
    private static final float MonthYearHeight = Dp.m5678constructorimpl(56);

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePicker$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ DatePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(DatePickerState datePickerState) {
            super(2);
            this.$state = datePickerState;
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
                ComposerKt.traceEventStart(-1504998463, i10, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:156)");
            }
            DatePickerDefaults.INSTANCE.m1476DatePickerTitlehOD91z4(this.$state.mo1498getDisplayModejFl4v0(), PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerTitlePadding), composer, 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePicker$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ DatePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(DatePickerState datePickerState, DatePickerFormatter datePickerFormatter) {
            super(2);
            this.$state = datePickerState;
            this.$dateFormatter = datePickerFormatter;
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
                ComposerKt.traceEventStart(-1780043561, i10, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:162)");
            }
            DatePickerDefaults.INSTANCE.m1475DatePickerHeadline3kbWawI(this.$state.getSelectedDateMillis(), this.$state.mo1498getDisplayModejFl4v0(), this.$dateFormatter, PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.DatePickerHeadlinePadding), composer, 27648, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePicker$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ DatePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(DatePickerState datePickerState) {
            super(2);
            this.$state = datePickerState;
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
                ComposerKt.traceEventStart(1982226759, i10, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:180)");
            }
            Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, DatePickerKt.getDatePickerModeTogglePadding());
            int iMo1498getDisplayModejFl4v0 = this.$state.mo1498getDisplayModejFl4v0();
            composer.startReplaceableGroup(-1036920264);
            boolean zChanged = composer.changed(this.$state);
            DatePickerState datePickerState = this.$state;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new DatePickerKt$DatePicker$4$1$1(datePickerState);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            DatePickerKt.m1484DisplayModeToggleButtontER2X8s(modifierPadding, iMo1498getDisplayModejFl4v0, (l) objRememberedValue, composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePicker$5, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass5 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ DatePickerState $state;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass5(DatePickerState datePickerState, CalendarModel calendarModel, DatePickerFormatter datePickerFormatter, DatePickerColors datePickerColors) {
            super(2);
            this.$state = datePickerState;
            this.$calendarModel = calendarModel;
            this.$dateFormatter = datePickerFormatter;
            this.$colors = datePickerColors;
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
                ComposerKt.traceEventStart(-1840727866, i10, -1, "androidx.compose.material3.DatePicker.<anonymous> (DatePicker.kt:195)");
            }
            Long selectedDateMillis = this.$state.getSelectedDateMillis();
            long displayedMonthMillis = this.$state.getDisplayedMonthMillis();
            int iMo1498getDisplayModejFl4v0 = this.$state.mo1498getDisplayModejFl4v0();
            composer.startReplaceableGroup(-1036919665);
            boolean zChanged = composer.changed(this.$state);
            DatePickerState datePickerState = this.$state;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new DatePickerKt$DatePicker$5$1$1(datePickerState);
                composer.updateRememberedValue(objRememberedValue);
            }
            l lVar = (l) objRememberedValue;
            composer.endReplaceableGroup();
            composer.startReplaceableGroup(-1036919567);
            boolean zChanged2 = composer.changed(this.$state);
            DatePickerState datePickerState2 = this.$state;
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new DatePickerKt$DatePicker$5$2$1(datePickerState2);
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceableGroup();
            DatePickerKt.m1485SwitchableDateEntryContentd7iavvg(selectedDateMillis, displayedMonthMillis, iMo1498getDisplayModejFl4v0, lVar, (l) objRememberedValue2, this.$calendarModel, this.$state.getYearRange(), this.$dateFormatter, this.$state.getSelectableDates(), this.$colors, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePicker$6, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass6 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ p<Composer, Integer, t0> $headline;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ boolean $showModeToggle;
        final /* synthetic */ DatePickerState $state;
        final /* synthetic */ p<Composer, Integer, t0> $title;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass6(DatePickerState datePickerState, Modifier modifier, DatePickerFormatter datePickerFormatter, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, boolean z, DatePickerColors datePickerColors, int i10, int i11) {
            super(2);
            this.$state = datePickerState;
            this.$modifier = modifier;
            this.$dateFormatter = datePickerFormatter;
            this.$title = pVar;
            this.$headline = pVar2;
            this.$showModeToggle = z;
            this.$colors = datePickerColors;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            DatePickerKt.DatePicker(this.$state, this.$modifier, this.$dateFormatter, this.$title, this.$headline, this.$showModeToggle, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$DatePickerContent$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04882 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ long $displayedMonthMillis;
        final /* synthetic */ l<Long, t0> $onDateSelectionChange;
        final /* synthetic */ l<Long, t0> $onDisplayedMonthChange;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ Long $selectedDateMillis;
        final /* synthetic */ i $yearRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04882(Long l10, long j10, l<? super Long, t0> lVar, l<? super Long, t0> lVar2, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, int i10) {
            super(2);
            this.$selectedDateMillis = l10;
            this.$displayedMonthMillis = j10;
            this.$onDateSelectionChange = lVar;
            this.$onDisplayedMonthChange = lVar2;
            this.$calendarModel = calendarModel;
            this.$yearRange = iVar;
            this.$dateFormatter = datePickerFormatter;
            this.$selectableDates = selectableDates;
            this.$colors = datePickerColors;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            DatePickerKt.DatePickerContent(this.$selectedDateMillis, this.$displayedMonthMillis, this.$onDateSelectionChange, this.$onDisplayedMonthChange, this.$calendarModel, this.$yearRange, this.$dateFormatter, this.$selectableDates, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$Day$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04892 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $content;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04892(p<? super Composer, ? super Integer, t0> pVar) {
            super(2);
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
                ComposerKt.traceEventStart(-2031780827, i10, -1, "androidx.compose.material3.Day.<anonymous> (DatePicker.kt:1971)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            DatePickerModalTokens datePickerModalTokens = DatePickerModalTokens.INSTANCE;
            Modifier modifierM572requiredSizeVpY3zN4 = SizeKt.m572requiredSizeVpY3zN4(companion, datePickerModalTokens.m2478getDateStateLayerWidthD9Ej5fM(), datePickerModalTokens.m2477getDateStateLayerHeightD9Ej5fM());
            Alignment center = Alignment.INSTANCE.getCenter();
            p<Composer, Integer, t0> pVar = this.$content;
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM572requiredSizeVpY3zN4);
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

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$Day$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04903 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ boolean $animateChecked;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ String $description;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ boolean $inRange;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ boolean $selected;
        final /* synthetic */ boolean $today;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04903(Modifier modifier, boolean z, r7.a<t0> aVar, boolean z5, boolean z10, boolean z11, boolean z12, String str, DatePickerColors datePickerColors, p<? super Composer, ? super Integer, t0> pVar, int i10) {
            super(2);
            this.$modifier = modifier;
            this.$selected = z;
            this.$onClick = aVar;
            this.$animateChecked = z5;
            this.$enabled = z10;
            this.$today = z11;
            this.$inRange = z12;
            this.$description = str;
            this.$colors = datePickerColors;
            this.$content = pVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            DatePickerKt.Day(this.$modifier, this.$selected, this.$onClick, this.$animateChecked, this.$enabled, this.$today, this.$inRange, this.$description, this.$colors, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$HorizontalMonthsList$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ CalendarMonth $firstMonth;
        final /* synthetic */ LazyListState $lazyListState;
        final /* synthetic */ l<Long, t0> $onDateSelectionChange;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ Long $selectedDateMillis;
        final /* synthetic */ CalendarDate $today;
        final /* synthetic */ i $yearRange;

        /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$HorizontalMonthsList$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C00951 extends r implements l<SemanticsPropertyReceiver, t0> {
            public static final C00951 INSTANCE = new C00951();

            /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$HorizontalMonthsList$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            public static final class C00961 extends r implements r7.a<Float> {
                public static final C00961 INSTANCE = new C00961();

                public C00961() {
                    super(0);
                }

                @Override // r7.a
                public final Float invoke() {
                    return Float.valueOf(0.0f);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$HorizontalMonthsList$1$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            public static final class AnonymousClass2 extends r implements r7.a<Float> {
                public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

                public AnonymousClass2() {
                    super(0);
                }

                @Override // r7.a
                public final Float invoke() {
                    return Float.valueOf(0.0f);
                }
            }

            public C00951() {
                super(1);
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SemanticsPropertyReceiver) obj);
                return t0.f22605a;
            }

            public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                SemanticsPropertiesKt.setHorizontalScrollAxisRange(semanticsPropertyReceiver, new ScrollAxisRange(C00961.INSTANCE, AnonymousClass2.INSTANCE, false, 4, null));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(LazyListState lazyListState, i iVar, CalendarModel calendarModel, CalendarMonth calendarMonth, l<? super Long, t0> lVar, CalendarDate calendarDate, Long l10, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors) {
            super(2);
            this.$lazyListState = lazyListState;
            this.$yearRange = iVar;
            this.$calendarModel = calendarModel;
            this.$firstMonth = calendarMonth;
            this.$onDateSelectionChange = lVar;
            this.$today = calendarDate;
            this.$selectedDateMillis = l10;
            this.$dateFormatter = datePickerFormatter;
            this.$selectableDates = selectableDates;
            this.$colors = datePickerColors;
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
                ComposerKt.traceEventStart(1504086906, i10, -1, "androidx.compose.material3.HorizontalMonthsList.<anonymous> (DatePicker.kt:1651)");
            }
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, C00951.INSTANCE, 1, null);
            LazyListState lazyListState = this.$lazyListState;
            FlingBehavior flingBehaviorRememberSnapFlingBehavior$material3_release = DatePickerDefaults.INSTANCE.rememberSnapFlingBehavior$material3_release(lazyListState, null, composer, RendererCapabilities.DECODER_SUPPORT_MASK, 2);
            composer.startReplaceableGroup(1286688325);
            boolean zChangedInstance = composer.changedInstance(this.$yearRange) | composer.changedInstance(this.$calendarModel) | composer.changed(this.$firstMonth) | composer.changed(this.$onDateSelectionChange) | composer.changed(this.$today) | composer.changed(this.$selectedDateMillis) | composer.changedInstance(this.$dateFormatter) | composer.changed(this.$selectableDates) | composer.changed(this.$colors);
            i iVar = this.$yearRange;
            CalendarModel calendarModel = this.$calendarModel;
            CalendarMonth calendarMonth = this.$firstMonth;
            l<Long, t0> lVar = this.$onDateSelectionChange;
            CalendarDate calendarDate = this.$today;
            Long l10 = this.$selectedDateMillis;
            DatePickerFormatter datePickerFormatter = this.$dateFormatter;
            SelectableDates selectableDates = this.$selectableDates;
            DatePickerColors datePickerColors = this.$colors;
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new DatePickerKt$HorizontalMonthsList$1$2$1(iVar, calendarModel, calendarMonth, lVar, calendarDate, l10, datePickerFormatter, selectableDates, datePickerColors);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            LazyDslKt.LazyRow(modifierSemantics$default, lazyListState, null, false, null, null, flingBehaviorRememberSnapFlingBehavior$material3_release, false, (l) objRememberedValue, composer, 0, TsExtractor.TS_PACKET_SIZE);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$HorizontalMonthsList$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04913 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ LazyListState $lazyListState;
        final /* synthetic */ l<Long, t0> $onDateSelectionChange;
        final /* synthetic */ l<Long, t0> $onDisplayedMonthChange;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ Long $selectedDateMillis;
        final /* synthetic */ i $yearRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04913(LazyListState lazyListState, Long l10, l<? super Long, t0> lVar, l<? super Long, t0> lVar2, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, int i10) {
            super(2);
            this.$lazyListState = lazyListState;
            this.$selectedDateMillis = l10;
            this.$onDateSelectionChange = lVar;
            this.$onDisplayedMonthChange = lVar2;
            this.$calendarModel = calendarModel;
            this.$yearRange = iVar;
            this.$dateFormatter = datePickerFormatter;
            this.$selectableDates = selectableDates;
            this.$colors = datePickerColors;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            DatePickerKt.HorizontalMonthsList(this.$lazyListState, this.$selectedDateMillis, this.$onDateSelectionChange, this.$onDisplayedMonthChange, this.$calendarModel, this.$yearRange, this.$dateFormatter, this.$selectableDates, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$Month$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04922 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ DatePickerFormatter $dateFormatter;
        final /* synthetic */ Long $endDateMillis;
        final /* synthetic */ CalendarMonth $month;
        final /* synthetic */ l<Long, t0> $onDateSelectionChange;
        final /* synthetic */ SelectedRangeInfo $rangeSelectionInfo;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ Long $startDateMillis;
        final /* synthetic */ long $todayMillis;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04922(CalendarMonth calendarMonth, l<? super Long, t0> lVar, long j10, Long l10, Long l11, SelectedRangeInfo selectedRangeInfo, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, int i10) {
            super(2);
            this.$month = calendarMonth;
            this.$onDateSelectionChange = lVar;
            this.$todayMillis = j10;
            this.$startDateMillis = l10;
            this.$endDateMillis = l11;
            this.$rangeSelectionInfo = selectedRangeInfo;
            this.$dateFormatter = datePickerFormatter;
            this.$selectableDates = selectableDates;
            this.$colors = datePickerColors;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            DatePickerKt.Month(this.$month, this.$onDateSelectionChange, this.$todayMillis, this.$startDateMillis, this.$endDateMillis, this.$rangeSelectionInfo, this.$dateFormatter, this.$selectableDates, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$MonthsNavigation$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04932 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ boolean $nextAvailable;
        final /* synthetic */ r7.a<t0> $onNextClicked;
        final /* synthetic */ r7.a<t0> $onPreviousClicked;
        final /* synthetic */ r7.a<t0> $onYearPickerButtonClicked;
        final /* synthetic */ boolean $previousAvailable;
        final /* synthetic */ String $yearPickerText;
        final /* synthetic */ boolean $yearPickerVisible;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04932(Modifier modifier, boolean z, boolean z5, boolean z10, String str, r7.a<t0> aVar, r7.a<t0> aVar2, r7.a<t0> aVar3, DatePickerColors datePickerColors, int i10) {
            super(2);
            this.$modifier = modifier;
            this.$nextAvailable = z;
            this.$previousAvailable = z5;
            this.$yearPickerVisible = z10;
            this.$yearPickerText = str;
            this.$onNextClicked = aVar;
            this.$onPreviousClicked = aVar2;
            this.$onYearPickerButtonClicked = aVar3;
            this.$colors = datePickerColors;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            DatePickerKt.MonthsNavigation(this.$modifier, this.$nextAvailable, this.$previousAvailable, this.$yearPickerVisible, this.$yearPickerText, this.$onNextClicked, this.$onPreviousClicked, this.$onYearPickerButtonClicked, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$WeekDays$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04942 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C04942(DatePickerColors datePickerColors, CalendarModel calendarModel, int i10) {
            super(2);
            this.$colors = datePickerColors;
            this.$calendarModel = calendarModel;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            DatePickerKt.WeekDays(this.$colors, this.$calendarModel, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$Year$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04952 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $content;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04952(p<? super Composer, ? super Integer, t0> pVar) {
            super(2);
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
                ComposerKt.traceEventStart(-1573188346, i10, -1, "androidx.compose.material3.Year.<anonymous> (DatePicker.kt:2117)");
            }
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Alignment center = Alignment.INSTANCE.getCenter();
            p<Composer, Integer, t0> pVar = this.$content;
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
            p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
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

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$Year$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04963 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ boolean $currentYear;
        final /* synthetic */ String $description;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ boolean $selected;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04963(Modifier modifier, boolean z, boolean z5, r7.a<t0> aVar, boolean z10, String str, DatePickerColors datePickerColors, p<? super Composer, ? super Integer, t0> pVar, int i10) {
            super(2);
            this.$modifier = modifier;
            this.$selected = z;
            this.$currentYear = z5;
            this.$onClick = aVar;
            this.$enabled = z10;
            this.$description = str;
            this.$colors = datePickerColors;
            this.$content = pVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            DatePickerKt.Year(this.$modifier, this.$selected, this.$currentYear, this.$onClick, this.$enabled, this.$description, this.$colors, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$YearPicker$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04971 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ long $displayedMonthMillis;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<Integer, t0> $onYearSelected;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ i $yearRange;

        /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$YearPicker$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C00971 extends r implements l<SemanticsPropertyReceiver, t0> {
            public static final C00971 INSTANCE = new C00971();

            /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$YearPicker$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            public static final class C00981 extends r implements r7.a<Float> {
                public static final C00981 INSTANCE = new C00981();

                public C00981() {
                    super(0);
                }

                @Override // r7.a
                public final Float invoke() {
                    return Float.valueOf(0.0f);
                }
            }

            /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$YearPicker$1$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Float;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            public static final class AnonymousClass2 extends r implements r7.a<Float> {
                public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

                public AnonymousClass2() {
                    super(0);
                }

                @Override // r7.a
                public final Float invoke() {
                    return Float.valueOf(0.0f);
                }
            }

            public C00971() {
                super(1);
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SemanticsPropertyReceiver) obj);
                return t0.f22605a;
            }

            public final void invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                SemanticsPropertiesKt.setVerticalScrollAxisRange(semanticsPropertyReceiver, new ScrollAxisRange(C00981.INSTANCE, AnonymousClass2.INSTANCE, false, 4, null));
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04971(CalendarModel calendarModel, long j10, i iVar, DatePickerColors datePickerColors, Modifier modifier, l<? super Integer, t0> lVar, SelectableDates selectableDates) {
            super(2);
            this.$calendarModel = calendarModel;
            this.$displayedMonthMillis = j10;
            this.$yearRange = iVar;
            this.$colors = datePickerColors;
            this.$modifier = modifier;
            this.$onYearSelected = lVar;
            this.$selectableDates = selectableDates;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            GridCells.Fixed fixed;
            Object datePickerKt$YearPicker$1$2$1;
            if ((i10 & 3) == 2 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1301915789, i10, -1, "androidx.compose.material3.YearPicker.<anonymous> (DatePicker.kt:1997)");
            }
            CalendarModel calendarModel = this.$calendarModel;
            int year = calendarModel.getMonth(calendarModel.getToday()).getYear();
            int year2 = this.$calendarModel.getMonth(this.$displayedMonthMillis).getYear();
            LazyGridState lazyGridStateRememberLazyGridState = LazyGridStateKt.rememberLazyGridState(Math.max(0, (year2 - this.$yearRange.f22619i) - 3), 0, composer, 0, 2);
            long jM1392applyTonalElevationRFCenO8 = ColorSchemeKt.m1392applyTonalElevationRFCenO8(MaterialTheme.INSTANCE.getColorScheme(composer, 6), this.$colors.getContainerColor(), ((Dp) composer.consume(SurfaceKt.getLocalAbsoluteTonalElevation())).m5692unboximpl(), composer, 0);
            Object objG = a0.c.g(773894976, composer, -492369756);
            Composer.Companion companion = Composer.INSTANCE;
            if (objG == companion.getEmpty()) {
                objG = com.arflix.tv.data.repository.g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composer), composer);
            }
            composer.endReplaceableGroup();
            k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objG).getCoroutineScope();
            composer.endReplaceableGroup();
            Strings.Companion companion2 = Strings.INSTANCE;
            String strM1994getStringNWtq28 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_picker_scroll_to_earlier_years), composer, 0);
            String strM1994getStringNWtq282 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_picker_scroll_to_later_years), composer, 0);
            GridCells.Fixed fixed2 = new GridCells.Fixed(3);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(BackgroundKt.m179backgroundbw27NRU$default(this.$modifier, jM1392applyTonalElevationRFCenO8, null, 2, null), false, C00971.INSTANCE, 1, null);
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.HorizontalOrVertical spaceEvenly = arrangement.getSpaceEvenly();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = arrangement.m441spacedBy0680j_4(DatePickerKt.YearsVerticalPadding);
            composer.startReplaceableGroup(-969328877);
            boolean zChangedInstance = composer.changedInstance(this.$yearRange) | composer.changed(lazyGridStateRememberLazyGridState) | composer.changedInstance(coroutineScope) | composer.changed(strM1994getStringNWtq28) | composer.changed(strM1994getStringNWtq282) | composer.changed(year2) | composer.changed(year) | composer.changed(this.$onYearSelected) | composer.changed(this.$selectableDates) | composer.changed(this.$colors);
            i iVar = this.$yearRange;
            l<Integer, t0> lVar = this.$onYearSelected;
            SelectableDates selectableDates = this.$selectableDates;
            DatePickerColors datePickerColors = this.$colors;
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == companion.getEmpty()) {
                fixed = fixed2;
                datePickerKt$YearPicker$1$2$1 = new DatePickerKt$YearPicker$1$2$1(iVar, lazyGridStateRememberLazyGridState, coroutineScope, strM1994getStringNWtq28, strM1994getStringNWtq282, year2, year, lVar, selectableDates, datePickerColors);
                composer.updateRememberedValue(datePickerKt$YearPicker$1$2$1);
            } else {
                datePickerKt$YearPicker$1$2$1 = objRememberedValue;
                fixed = fixed2;
            }
            composer.endReplaceableGroup();
            LazyGridDslKt.LazyVerticalGrid(fixed, modifierSemantics$default, lazyGridStateRememberLazyGridState, null, false, horizontalOrVerticalM441spacedBy0680j_4, spaceEvenly, null, false, (l) datePickerKt$YearPicker$1$2$1, composer, 1769472, 408);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$YearPicker$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C04982 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ CalendarModel $calendarModel;
        final /* synthetic */ DatePickerColors $colors;
        final /* synthetic */ long $displayedMonthMillis;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ l<Integer, t0> $onYearSelected;
        final /* synthetic */ SelectableDates $selectableDates;
        final /* synthetic */ i $yearRange;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04982(Modifier modifier, long j10, l<? super Integer, t0> lVar, SelectableDates selectableDates, CalendarModel calendarModel, i iVar, DatePickerColors datePickerColors, int i10) {
            super(2);
            this.$modifier = modifier;
            this.$displayedMonthMillis = j10;
            this.$onYearSelected = lVar;
            this.$selectableDates = selectableDates;
            this.$calendarModel = calendarModel;
            this.$yearRange = iVar;
            this.$colors = datePickerColors;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            DatePickerKt.YearPicker(this.$modifier, this.$displayedMonthMillis, this.$onYearSelected, this.$selectableDates, this.$calendarModel, this.$yearRange, this.$colors, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$YearPickerMenuButton$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/layout/RowScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/foundation/layout/RowScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C04991 extends r implements q<RowScope, Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ boolean $expanded;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C04991(p<? super Composer, ? super Integer, t0> pVar, boolean z) {
            super(3);
            this.$content = pVar;
            this.$expanded = z;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((RowScope) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(RowScope rowScope, Composer composer, int i10) {
            String strM1994getStringNWtq28;
            if ((i10 & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1899012021, i10, -1, "androidx.compose.material3.YearPickerMenuButton.<anonymous> (DatePicker.kt:2202)");
            }
            this.$content.invoke(composer, 0);
            Modifier.Companion companion = Modifier.INSTANCE;
            SpacerKt.Spacer(SizeKt.m578size3ABfNKs(companion, ButtonDefaults.INSTANCE.m1273getIconSpacingD9Ej5fM()), composer, 6);
            ImageVector arrowDropDown = ArrowDropDownKt.getArrowDropDown(Icons.Filled.INSTANCE);
            if (this.$expanded) {
                composer.startReplaceableGroup(1071201785);
                Strings.Companion companion2 = Strings.INSTANCE;
                strM1994getStringNWtq28 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_picker_switch_to_day_selection), composer, 0);
                composer.endReplaceableGroup();
            } else {
                composer.startReplaceableGroup(1071201872);
                Strings.Companion companion3 = Strings.INSTANCE;
                strM1994getStringNWtq28 = Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_picker_switch_to_year_selection), composer, 0);
                composer.endReplaceableGroup();
            }
            IconKt.m1610Iconww6aTOc(arrowDropDown, strM1994getStringNWtq28, RotateKt.rotate(companion, this.$expanded ? 180.0f : 0.0f), 0L, composer, 0, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$YearPickerMenuButton$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05002 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ boolean $expanded;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05002(r7.a<t0> aVar, boolean z, Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
            super(2);
            this.$onClick = aVar;
            this.$expanded = z;
            this.$modifier = modifier;
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
            DatePickerKt.YearPickerMenuButton(this.$onClick, this.$expanded, this.$modifier, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DatePickerKt$updateDisplayedMonth$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05012 extends r implements r7.a<Integer> {
        final /* synthetic */ LazyListState $lazyListState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C05012(LazyListState lazyListState) {
            super(0);
            this.$lazyListState = lazyListState;
        }

        @Override // r7.a
        public final Integer invoke() {
            return Integer.valueOf(this.$lazyListState.getFirstVisibleItemIndex());
        }
    }

    static {
        float f10 = 12;
        DatePickerHorizontalPadding = Dp.m5678constructorimpl(f10);
        DatePickerModeTogglePadding = PaddingKt.m526PaddingValuesa9UjIt4$default(0.0f, 0.0f, Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(f10), 3, null);
        float f11 = 24;
        float f12 = 16;
        DatePickerTitlePadding = PaddingKt.m526PaddingValuesa9UjIt4$default(Dp.m5678constructorimpl(f11), Dp.m5678constructorimpl(f12), Dp.m5678constructorimpl(f10), 0.0f, 8, null);
        DatePickerHeadlinePadding = PaddingKt.m526PaddingValuesa9UjIt4$default(Dp.m5678constructorimpl(f11), 0.0f, Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(f10), 2, null);
        YearsVerticalPadding = Dp.m5678constructorimpl(f12);
    }

    /* JADX INFO: renamed from: DateEntryContainer-au3_HiA, reason: not valid java name */
    public static final void m1480DateEntryContainerau3_HiA(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, DatePickerColors datePickerColors, TextStyle textStyle, float f10, p<? super Composer, ? super Integer, t0> pVar4, Composer composer, int i10) {
        int i11;
        p<? super Composer, ? super Integer, t0> pVar5;
        p<? super Composer, ? super Integer, t0> pVar6;
        p<? super Composer, ? super Integer, t0> pVar7;
        DatePickerColors datePickerColors2;
        TextStyle textStyle2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1507356255);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            pVar5 = pVar;
            i11 |= composerStartRestartGroup.changedInstance(pVar5) ? 32 : 16;
        } else {
            pVar5 = pVar;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            pVar6 = pVar2;
            i11 |= composerStartRestartGroup.changedInstance(pVar6) ? 256 : 128;
        } else {
            pVar6 = pVar2;
        }
        if ((i10 & 3072) == 0) {
            pVar7 = pVar3;
            i11 |= composerStartRestartGroup.changedInstance(pVar7) ? 2048 : 1024;
        } else {
            pVar7 = pVar3;
        }
        if ((i10 & 24576) == 0) {
            datePickerColors2 = datePickerColors;
            i11 |= composerStartRestartGroup.changed(datePickerColors2) ? 16384 : 8192;
        } else {
            datePickerColors2 = datePickerColors;
        }
        if ((196608 & i10) == 0) {
            textStyle2 = textStyle;
            i11 |= composerStartRestartGroup.changed(textStyle2) ? 131072 : 65536;
        } else {
            textStyle2 = textStyle;
        }
        if ((1572864 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(f10) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((12582912 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar4) ? 8388608 : 4194304;
        }
        if ((4793491 & i11) == 4793490 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1507356255, i11, -1, "androidx.compose.material3.DateEntryContainer (DatePicker.kt:1302)");
            }
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(SizeKt.m582sizeInqDBjuR0$default(modifier, DatePickerModalTokens.INSTANCE.m2474getContainerWidthD9Ej5fM(), 0.0f, 0.0f, 0.0f, 14, null), false, DatePickerKt$DateEntryContainer$1.INSTANCE, 1, null);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(Alignment.INSTANCE, Arrangement.INSTANCE.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierSemantics$default);
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
            p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            m1481DatePickerHeaderpc5RIQQ(Modifier.INSTANCE, pVar, datePickerColors2.getTitleContentColor(), datePickerColors2.getHeadlineContentColor(), f10, ComposableLambdaKt.composableLambda(composerStartRestartGroup, -229007058, true, new DatePickerKt$DateEntryContainer$2$1(pVar6, pVar7, pVar5, datePickerColors2, textStyle2)), composerStartRestartGroup, (i11 & 112) | 196614 | (57344 & (i11 >> 6)));
            composer2 = composerStartRestartGroup;
            pVar4.invoke(composer2, Integer.valueOf((i11 >> 21) & 14));
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
            scopeUpdateScopeEndRestartGroup.updateScope(new DatePickerKt$DateEntryContainer$3(modifier, pVar, pVar2, pVar3, datePickerColors, textStyle, f10, pVar4, i10));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:137:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x010f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DatePicker(androidx.compose.material3.DatePickerState r24, androidx.compose.ui.Modifier r25, androidx.compose.material3.DatePickerFormatter r26, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r27, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r28, boolean r29, androidx.compose.material3.DatePickerColors r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 546
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DatePickerKt.DatePicker(androidx.compose.material3.DatePickerState, androidx.compose.ui.Modifier, androidx.compose.material3.DatePickerFormatter, r7.p, r7.p, boolean, androidx.compose.material3.DatePickerColors, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DatePickerContent(Long l10, long j10, l<? super Long, t0> lVar, l<? super Long, t0> lVar2, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-434467002);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(l10) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(j10) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(lVar) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(lVar2) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(calendarModel) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(iVar) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i11 |= (2097152 & i10) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((12582912 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(selectableDates) ? 8388608 : 4194304;
        }
        if ((i10 & 100663296) == 0) {
            i11 |= composerStartRestartGroup.changed(datePickerColors) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i11 & 38347923) == 38347922 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-434467002, i11, -1, "androidx.compose.material3.DatePickerContent (DatePicker.kt:1477)");
            }
            CalendarMonth month = calendarModel.getMonth(j10);
            LazyListState lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(month.indexIn(iVar), 0, composerStartRestartGroup, 0, 2);
            Object objG = a0.c.g(773894976, composerStartRestartGroup, -492369756);
            Composer.Companion companion = Composer.INSTANCE;
            if (objG == companion.getEmpty()) {
                objG = com.arflix.tv.data.repository.g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composerStartRestartGroup), composerStartRestartGroup);
            }
            composerStartRestartGroup.endReplaceableGroup();
            k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objG).getCoroutineScope();
            composerStartRestartGroup.endReplaceableGroup();
            int i12 = i11;
            MutableState mutableState = (MutableState) RememberSaveableKt.m3084rememberSaveable(new Object[0], (Saver) null, (String) null, (r7.a) DatePickerKt$DatePickerContent$yearPickerVisible$2.INSTANCE, composerStartRestartGroup, 3072, 6);
            Locale localeDefaultLocale = ActualAndroid_androidKt.defaultLocale(composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.Vertical top = arrangement.getTop();
            Alignment.Companion companion3 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion3, top, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion4.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion2);
            if (!(composerStartRestartGroup.getApplier() != null)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
            p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            float f10 = DatePickerHorizontalPadding;
            Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(companion2, f10, 0.0f, 2, null);
            boolean canScrollForward = lazyListStateRememberLazyListState.getCanScrollForward();
            boolean canScrollBackward = lazyListStateRememberLazyListState.getCanScrollBackward();
            boolean zDatePickerContent$lambda$8 = DatePickerContent$lambda$8(mutableState);
            String monthYear = datePickerFormatter.formatMonthYear(Long.valueOf(j10), localeDefaultLocale);
            if (monthYear == null) {
                monthYear = "-";
            }
            composerStartRestartGroup.startReplaceableGroup(-269656881);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(coroutineScope) | composerStartRestartGroup.changed(lazyListStateRememberLazyListState);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new DatePickerKt$DatePickerContent$1$1$1(coroutineScope, lazyListStateRememberLazyListState);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            r7.a aVar = (r7.a) objRememberedValue;
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(-269656336);
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(coroutineScope) | composerStartRestartGroup.changed(lazyListStateRememberLazyListState);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new DatePickerKt$DatePickerContent$1$2$1(coroutineScope, lazyListStateRememberLazyListState);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            r7.a aVar2 = (r7.a) objRememberedValue2;
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(-269655774);
            boolean zChanged = composerStartRestartGroup.changed(mutableState);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new DatePickerKt$DatePickerContent$1$3$1(mutableState);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composerStartRestartGroup.endReplaceableGroup();
            int i13 = i12 & 234881024;
            MonthsNavigation(modifierM531paddingVpY3zN4$default, canScrollForward, canScrollBackward, zDatePickerContent$lambda$8, monthYear, aVar, aVar2, (r7.a) objRememberedValue3, datePickerColors, composerStartRestartGroup, i13 | 6);
            composerStartRestartGroup = composerStartRestartGroup;
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(companion3, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(companion2);
            if (!(composerStartRestartGroup.getApplier() != null)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composerStartRestartGroup);
            p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Modifier modifierM531paddingVpY3zN4$default2 = PaddingKt.m531paddingVpY3zN4$default(companion2, f10, 0.0f, 2, null);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK2 = androidx.compose.foundation.c.k(companion3, arrangement.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion4.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default2);
            if (!(composerStartRestartGroup.getApplier() != null)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composerStartRestartGroup);
            p pVarU3 = a0.c.u(companion4, composerM2991constructorimpl3, measurePolicyK2, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf3, composerStartRestartGroup, 0, 2058660585);
            WeekDays(datePickerColors, calendarModel, composerStartRestartGroup, ((i12 >> 24) & 14) | ((i12 >> 9) & 112));
            HorizontalMonthsList(lazyListStateRememberLazyListState, l10, lVar, lVar2, calendarModel, iVar, datePickerFormatter, selectableDates, datePickerColors, composerStartRestartGroup, ((i12 << 3) & 112) | (i12 & 896) | (i12 & 7168) | (57344 & i12) | (458752 & i12) | (3670016 & i12) | (29360128 & i12) | i13);
            androidx.compose.foundation.c.y(composerStartRestartGroup);
            AnimatedVisibilityKt.AnimatedVisibility(DatePickerContent$lambda$8(mutableState), ClipKt.clipToBounds(companion2), EnterExitTransitionKt.expandVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.6f, 1, null)), EnterExitTransitionKt.shrinkVertically$default(null, null, false, null, 15, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)), (String) null, ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1193716082, true, new DatePickerKt$DatePickerContent$1$4$2(j10, mutableState, coroutineScope, lazyListStateRememberLazyListState, iVar, month, selectableDates, calendarModel, datePickerColors)), composerStartRestartGroup, 200112, 16);
            if (androidx.compose.foundation.c.A(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C04882(l10, j10, lVar, lVar2, calendarModel, iVar, datePickerFormatter, selectableDates, datePickerColors, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DatePickerContent$lambda$8(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DatePickerContent$lambda$9(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: renamed from: DatePickerHeader-pc5RIQQ, reason: not valid java name */
    public static final void m1481DatePickerHeaderpc5RIQQ(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, long j10, long j11, float f10, p<? super Composer, ? super Integer, t0> pVar2, Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-996037719);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(j10) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changed(j11) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changed(f10) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar2) ? 131072 : 65536;
        }
        if ((74899 & i11) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-996037719, i11, -1, "androidx.compose.material3.DatePickerHeader (DatePicker.kt:1592)");
            }
            Modifier modifierThen = SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null).then(pVar != null ? SizeKt.m563defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, 0.0f, f10, 1, null) : Modifier.INSTANCE);
            Arrangement.HorizontalOrVertical spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyJ = androidx.compose.foundation.c.j(Alignment.INSTANCE, spaceBetween, composerStartRestartGroup, 6, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierThen);
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
            p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyJ, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceableGroup(1127544336);
            if (pVar != null) {
                ProvideContentColorTextStyleKt.m1782ProvideContentColorTextStyle3JVO9M(j10, TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6), DatePickerModalTokens.INSTANCE.getHeaderSupportingTextFont()), ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1936268514, true, new DatePickerKt$DatePickerHeader$1$1(pVar)), composerStartRestartGroup, ((i11 >> 6) & 14) | RendererCapabilities.DECODER_SUPPORT_MASK);
            }
            composerStartRestartGroup.endReplaceableGroup();
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(j11)), pVar2, composerStartRestartGroup, ProvidedValue.$stable | ((i11 >> 12) & 112));
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
            scopeUpdateScopeEndRestartGroup.updateScope(new DatePickerKt$DatePickerHeader$2(modifier, pVar, j10, j11, f10, pVar2, i10));
        }
    }

    /* JADX INFO: renamed from: DatePickerState-sHin3Bw, reason: not valid java name */
    public static final DatePickerState m1482DatePickerStatesHin3Bw(Locale locale, Long l10, Long l11, i iVar, int i10, SelectableDates selectableDates) {
        return new DatePickerStateImpl(l10, l11, iVar, i10, selectableDates, locale, null);
    }

    /* JADX INFO: renamed from: DatePickerState-sHin3Bw$default, reason: not valid java name */
    public static /* synthetic */ DatePickerState m1483DatePickerStatesHin3Bw$default(Locale locale, Long l10, Long l11, i iVar, int i10, SelectableDates selectableDates, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            l10 = null;
        }
        if ((i11 & 4) != 0) {
            l11 = l10;
        }
        if ((i11 & 8) != 0) {
            iVar = DatePickerDefaults.INSTANCE.getYearRange();
        }
        if ((i11 & 16) != 0) {
            i10 = DisplayMode.INSTANCE.m1531getPickerjFl4v0();
        }
        if ((i11 & 32) != 0) {
            selectableDates = DatePickerDefaults.INSTANCE.getAllDates();
        }
        SelectableDates selectableDates2 = selectableDates;
        i iVar2 = iVar;
        return m1482DatePickerStatesHin3Bw(locale, l10, l11, iVar2, i10, selectableDates2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Day(Modifier modifier, boolean z, r7.a<t0> aVar, boolean z5, boolean z10, boolean z11, boolean z12, String str, DatePickerColors datePickerColors, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        int i11;
        boolean z13;
        boolean z14;
        boolean z15;
        DatePickerColors datePickerColors2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1434777861);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            z13 = z5;
            i11 |= composerStartRestartGroup.changed(z13) ? 2048 : 1024;
        } else {
            z13 = z5;
        }
        if ((i10 & 24576) == 0) {
            z14 = z10;
            i11 |= composerStartRestartGroup.changed(z14) ? 16384 : 8192;
        } else {
            z14 = z10;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(z11) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            z15 = z12;
            i11 |= composerStartRestartGroup.changed(z15) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        } else {
            z15 = z12;
        }
        if ((12582912 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(str) ? 8388608 : 4194304;
        }
        if ((100663296 & i10) == 0) {
            datePickerColors2 = datePickerColors;
            i11 |= composerStartRestartGroup.changed(datePickerColors2) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        } else {
            datePickerColors2 = datePickerColors;
        }
        if ((805306368 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        int i12 = i11;
        if ((306783379 & i12) == 306783378 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1434777861, i12, -1, "androidx.compose.material3.Day (DatePicker.kt:1937)");
            }
            composerStartRestartGroup.startReplaceableGroup(1664739143);
            boolean z16 = (29360128 & i12) == 8388608;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z16 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new DatePickerKt$Day$1$1(str);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierSemantics = SemanticsModifierKt.semantics(modifier, true, (l) objRememberedValue);
            DatePickerModalTokens datePickerModalTokens = DatePickerModalTokens.INSTANCE;
            Shape value = ShapesKt.getValue(datePickerModalTokens.getDateContainerShape(), composerStartRestartGroup, 6);
            int i13 = i12 >> 3;
            int i14 = i12 >> 15;
            long jM3482unboximpl = datePickerColors2.dayContainerColor$material3_release(z, z14, z13, composerStartRestartGroup, (i14 & 7168) | (i13 & 14) | ((i12 >> 9) & 112) | (i13 & 896)).getValue().m3482unboximpl();
            int i15 = i12 >> 12;
            SurfaceKt.m2005Surfaced85dljk(z, aVar, modifierSemantics, z10, value, jM3482unboximpl, datePickerColors.dayContentColor$material3_release(z11, z, z15, z10, composerStartRestartGroup, (i14 & 14) | (i12 & 112) | (i15 & 896) | (i13 & 7168) | (i15 & 57344)).getValue().m3482unboximpl(), 0.0f, 0.0f, (!z11 || z) ? null : BorderStrokeKt.m207BorderStrokecXLIe8U(datePickerModalTokens.m2479getDateTodayContainerOutlineWidthD9Ej5fM(), datePickerColors.getTodayDateBorderColor()), (MutableInteractionSource) null, ComposableLambdaKt.composableLambda(composerStartRestartGroup, -2031780827, true, new C04892(pVar)), composerStartRestartGroup, i13 & 7294, 48, 1408);
            composerStartRestartGroup = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C04903(modifier, z, aVar, z5, z10, z11, z12, str, datePickerColors, pVar, i10));
        }
    }

    /* JADX INFO: renamed from: DisplayModeToggleButton-tER2X8s, reason: not valid java name */
    public static final void m1484DisplayModeToggleButtontER2X8s(Modifier modifier, int i10, l<? super DisplayMode, t0> lVar, Composer composer, int i11) {
        int i12;
        boolean z;
        Modifier modifier2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1393846115);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(lVar) ? 256 : 128;
        }
        if ((i12 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1393846115, i12, -1, "androidx.compose.material3.DisplayModeToggleButton (DatePicker.kt:1354)");
            }
            if (DisplayMode.m1526equalsimpl0(i10, DisplayMode.INSTANCE.m1531getPickerjFl4v0())) {
                composerStartRestartGroup.startReplaceableGroup(-1814955688);
                composerStartRestartGroup.startReplaceableGroup(-1814955657);
                z = (i12 & 896) == 256;
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new DatePickerKt$DisplayModeToggleButton$1$1(lVar);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composerStartRestartGroup.endReplaceableGroup();
                modifier2 = modifier;
                IconButtonKt.IconButton((r7.a) objRememberedValue, modifier2, false, null, null, ComposableSingletons$DatePickerKt.INSTANCE.m1418getLambda1$material3_release(), composerStartRestartGroup, ((i12 << 3) & 112) | 196608, 28);
                composerStartRestartGroup.endReplaceableGroup();
            } else {
                composerStartRestartGroup.startReplaceableGroup(-1814955404);
                composerStartRestartGroup.startReplaceableGroup(-1814955373);
                z = (i12 & 896) == 256;
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new DatePickerKt$DisplayModeToggleButton$2$1(lVar);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                composerStartRestartGroup.endReplaceableGroup();
                modifier2 = modifier;
                IconButtonKt.IconButton((r7.a) objRememberedValue2, modifier2, false, null, null, ComposableSingletons$DatePickerKt.INSTANCE.m1419getLambda2$material3_release(), composerStartRestartGroup, ((i12 << 3) & 112) | 196608, 28);
                composerStartRestartGroup.endReplaceableGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new DatePickerKt$DisplayModeToggleButton$3(modifier2, i10, lVar, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HorizontalMonthsList(LazyListState lazyListState, Long l10, l<? super Long, t0> lVar, l<? super Long, t0> lVar2, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, Composer composer, int i10) {
        int i11;
        Long l11;
        l<? super Long, t0> lVar3;
        SelectableDates selectableDates2;
        DatePickerColors datePickerColors2;
        Object datePickerKt$HorizontalMonthsList$2$1;
        LazyListState lazyListState2 = lazyListState;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1994757941);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(lazyListState2) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            l11 = l10;
            i11 |= composerStartRestartGroup.changed(l11) ? 32 : 16;
        } else {
            l11 = l10;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            lVar3 = lVar;
            i11 |= composerStartRestartGroup.changedInstance(lVar3) ? 256 : 128;
        } else {
            lVar3 = lVar;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(lVar2) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(calendarModel) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(iVar) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i11 |= (2097152 & i10) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((12582912 & i10) == 0) {
            selectableDates2 = selectableDates;
            i11 |= composerStartRestartGroup.changed(selectableDates2) ? 8388608 : 4194304;
        } else {
            selectableDates2 = selectableDates;
        }
        if ((100663296 & i10) == 0) {
            datePickerColors2 = datePickerColors;
            i11 |= composerStartRestartGroup.changed(datePickerColors2) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        } else {
            datePickerColors2 = datePickerColors;
        }
        if ((38347923 & i11) == 38347922 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1994757941, i11, -1, "androidx.compose.material3.HorizontalMonthsList (DatePicker.kt:1640)");
            }
            CalendarDate today = calendarModel.getToday();
            composerStartRestartGroup.startReplaceableGroup(1346192500);
            boolean zChanged = composerStartRestartGroup.changed(iVar);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = calendarModel.getMonth(iVar.f22619i, 1);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            int i12 = i11;
            TextKt.ProvideTextStyle(TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6), DatePickerModalTokens.INSTANCE.getDateLabelTextFont()), ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1504086906, true, new AnonymousClass1(lazyListState2, iVar, calendarModel, (CalendarMonth) objRememberedValue, lVar3, today, l11, datePickerFormatter, selectableDates2, datePickerColors2)), composerStartRestartGroup, 48);
            composerStartRestartGroup.startReplaceableGroup(1346194369);
            int i13 = i12 & 14;
            boolean zChangedInstance = (i13 == 4) | ((i12 & 7168) == 2048) | composerStartRestartGroup.changedInstance(calendarModel) | composerStartRestartGroup.changedInstance(iVar);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                lazyListState2 = lazyListState;
                datePickerKt$HorizontalMonthsList$2$1 = new DatePickerKt$HorizontalMonthsList$2$1(lazyListState2, lVar2, calendarModel, iVar, null);
                composerStartRestartGroup.updateRememberedValue(datePickerKt$HorizontalMonthsList$2$1);
            } else {
                datePickerKt$HorizontalMonthsList$2$1 = objRememberedValue2;
                lazyListState2 = lazyListState;
            }
            composerStartRestartGroup.endReplaceableGroup();
            EffectsKt.LaunchedEffect(lazyListState2, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) datePickerKt$HorizontalMonthsList$2$1, composerStartRestartGroup, i13);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C04913(lazyListState2, l10, lVar, lVar2, calendarModel, iVar, datePickerFormatter, selectableDates, datePickerColors, i10));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0329  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Month(androidx.compose.material3.CalendarMonth r30, r7.l<? super java.lang.Long, x6.t0> r31, long r32, java.lang.Long r34, java.lang.Long r35, androidx.compose.material3.SelectedRangeInfo r36, androidx.compose.material3.DatePickerFormatter r37, androidx.compose.material3.SelectableDates r38, androidx.compose.material3.DatePickerColors r39, androidx.compose.runtime.Composer r40, int r41) {
        /*
            Method dump skipped, instruction units count: 1214
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DatePickerKt.Month(androidx.compose.material3.CalendarMonth, r7.l, long, java.lang.Long, java.lang.Long, androidx.compose.material3.SelectedRangeInfo, androidx.compose.material3.DatePickerFormatter, androidx.compose.material3.SelectableDates, androidx.compose.material3.DatePickerColors, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MonthsNavigation(Modifier modifier, boolean z, boolean z5, boolean z10, String str, r7.a<t0> aVar, r7.a<t0> aVar2, r7.a<t0> aVar3, DatePickerColors datePickerColors, Composer composer, int i10) {
        int i11;
        r7.a<t0> aVar4;
        r7.a<t0> aVar5;
        DatePickerColors datePickerColors2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-773929258);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(z5) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changed(z10) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changed(str) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            aVar4 = aVar2;
            i11 |= composerStartRestartGroup.changedInstance(aVar4) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        } else {
            aVar4 = aVar2;
        }
        if ((12582912 & i10) == 0) {
            aVar5 = aVar3;
            i11 |= composerStartRestartGroup.changedInstance(aVar5) ? 8388608 : 4194304;
        } else {
            aVar5 = aVar3;
        }
        if ((100663296 & i10) == 0) {
            datePickerColors2 = datePickerColors;
            i11 |= composerStartRestartGroup.changed(datePickerColors2) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        } else {
            datePickerColors2 = datePickerColors;
        }
        if ((38347923 & i11) == 38347922 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-773929258, i11, -1, "androidx.compose.material3.MonthsNavigation (DatePicker.kt:2139)");
            }
            Modifier modifierM567requiredHeight3ABfNKs = SizeKt.m567requiredHeight3ABfNKs(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null), MonthYearHeight);
            Arrangement.Horizontal start = z10 ? Arrangement.INSTANCE.getStart() : Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composerStartRestartGroup, 48);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM567requiredHeight3ABfNKs);
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
            p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(datePickerColors2.getNavigationContentColor())), ComposableLambdaKt.composableLambda(composerStartRestartGroup, -962805198, true, new DatePickerKt$MonthsNavigation$1$1(aVar5, z10, str, aVar4, z5, aVar, z)), composerStartRestartGroup, ProvidedValue.$stable | 48);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new C04932(modifier, z, z5, z10, str, aVar, aVar2, aVar3, datePickerColors2, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: SwitchableDateEntryContent-d7iavvg, reason: not valid java name */
    public static final void m1485SwitchableDateEntryContentd7iavvg(Long l10, long j10, int i10, l<? super Long, t0> lVar, l<? super Long, t0> lVar2, CalendarModel calendarModel, i iVar, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, Composer composer, int i11) {
        int i12;
        l<? super Long, t0> lVar3;
        l<? super Long, t0> lVar4;
        CalendarModel calendarModel2;
        i iVar2;
        SelectableDates selectableDates2;
        DatePickerColors datePickerColors2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-895379221);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(l10) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(j10) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(i10) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            lVar3 = lVar;
            i12 |= composerStartRestartGroup.changedInstance(lVar3) ? 2048 : 1024;
        } else {
            lVar3 = lVar;
        }
        if ((i11 & 24576) == 0) {
            lVar4 = lVar2;
            i12 |= composerStartRestartGroup.changedInstance(lVar4) ? 16384 : 8192;
        } else {
            lVar4 = lVar2;
        }
        if ((196608 & i11) == 0) {
            calendarModel2 = calendarModel;
            i12 |= composerStartRestartGroup.changedInstance(calendarModel2) ? 131072 : 65536;
        } else {
            calendarModel2 = calendarModel;
        }
        if ((1572864 & i11) == 0) {
            iVar2 = iVar;
            i12 |= composerStartRestartGroup.changedInstance(iVar2) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        } else {
            iVar2 = iVar;
        }
        if ((12582912 & i11) == 0) {
            i12 |= (16777216 & i11) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 8388608 : 4194304;
        }
        if ((100663296 & i11) == 0) {
            selectableDates2 = selectableDates;
            i12 |= composerStartRestartGroup.changed(selectableDates2) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        } else {
            selectableDates2 = selectableDates;
        }
        if ((805306368 & i11) == 0) {
            datePickerColors2 = datePickerColors;
            i12 |= composerStartRestartGroup.changed(datePickerColors2) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        } else {
            datePickerColors2 = datePickerColors;
        }
        int i13 = i12;
        if ((306783379 & i13) == 306783378 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-895379221, i13, -1, "androidx.compose.material3.SwitchableDateEntryContent (DatePicker.kt:1389)");
            }
            int i14 = -((Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity())).mo279roundToPx0680j_4(Dp.m5678constructorimpl(48));
            DisplayMode displayModeM1523boximpl = DisplayMode.m1523boximpl(i10);
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(Modifier.INSTANCE, false, DatePickerKt$SwitchableDateEntryContent$1.INSTANCE, 1, null);
            composerStartRestartGroup.startReplaceableGroup(1777156755);
            boolean zChanged = composerStartRestartGroup.changed(i14);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new DatePickerKt$SwitchableDateEntryContent$2$1(i14);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            composer2 = composerStartRestartGroup;
            AnimatedContentKt.AnimatedContent(displayModeM1523boximpl, modifierSemantics$default, (l) objRememberedValue, null, "DatePickerDisplayModeAnimation", null, ComposableLambdaKt.composableLambda(composerStartRestartGroup, -459778869, true, new DatePickerKt$SwitchableDateEntryContent$3(l10, j10, lVar3, lVar4, calendarModel2, iVar2, datePickerFormatter, selectableDates2, datePickerColors2)), composer2, ((i13 >> 6) & 14) | 1597440, 40);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new DatePickerKt$SwitchableDateEntryContent$4(l10, j10, i10, lVar, lVar2, calendarModel, iVar, datePickerFormatter, selectableDates, datePickerColors, i11));
        }
    }

    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v6 */
    public static final void WeekDays(DatePickerColors datePickerColors, CalendarModel calendarModel, Composer composer, int i10) {
        Composer composer2;
        ?? r32 = 0;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1849465391);
        int i11 = (i10 & 6) == 0 ? (composerStartRestartGroup.changed(datePickerColors) ? 4 : 2) | i10 : i10;
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(calendarModel) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1849465391, i11, -1, "androidx.compose.material3.WeekDays (DatePicker.kt:1721)");
            }
            int firstDayOfWeek = calendarModel.getFirstDayOfWeek();
            List<x> weekdayNames = calendarModel.getWeekdayNames();
            ArrayList arrayList = new ArrayList();
            int i12 = firstDayOfWeek - 1;
            int size = weekdayNames.size();
            for (int i13 = i12; i13 < size; i13++) {
                arrayList.add(weekdayNames.get(i13));
            }
            for (int i14 = 0; i14 < i12; i14++) {
                arrayList.add(weekdayNames.get(i14));
            }
            int i15 = 6;
            TextStyle textStyleFromToken = TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6), DatePickerModalTokens.INSTANCE.getWeekdaysLabelTextFont());
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SizeKt.m563defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, 0.0f, RecommendedSizeForAccessibility, 1, null), 0.0f, 1, null);
            Arrangement.HorizontalOrVertical spaceEvenly = Arrangement.INSTANCE.getSpaceEvenly();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceEvenly, centerVertically, composerStartRestartGroup, 54);
            int i16 = -1323940314;
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
            p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceableGroup(-971954356);
            int size2 = arrayList.size();
            int i17 = 0;
            while (i17 < size2) {
                x xVar = (x) arrayList.get(i17);
                Modifier.Companion companion2 = Modifier.INSTANCE;
                composerStartRestartGroup.startReplaceableGroup(784223355);
                boolean zChanged = composerStartRestartGroup.changed(xVar);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new DatePickerKt$WeekDays$1$1$1$1(xVar);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composerStartRestartGroup.endReplaceableGroup();
                Modifier modifierClearAndSetSemantics = SemanticsModifierKt.clearAndSetSemantics(companion2, (l) objRememberedValue);
                float f10 = RecommendedSizeForAccessibility;
                Modifier modifierM580sizeVpY3zN4 = SizeKt.m580sizeVpY3zN4(modifierClearAndSetSemantics, f10, f10);
                Alignment center = Alignment.INSTANCE.getCenter();
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, r32, composerStartRestartGroup, i15);
                composerStartRestartGroup.startReplaceableGroup(i16);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, r32);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM580sizeVpY3zN4);
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
                p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                Composer composer3 = composerStartRestartGroup;
                TextKt.m2153Text4IGK_g((String) xVar.f22609l, SizeKt.wrapContentSize$default(companion2, null, false, 3, null), datePickerColors.getWeekdayContentColor(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, TextAlign.m5575boximpl(TextAlign.INSTANCE.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, (l<? super TextLayoutResult, t0>) null, textStyleFromToken, composer3, 48, 0, 65016);
                composer3.endReplaceableGroup();
                composer3.endNode();
                composer3.endReplaceableGroup();
                composer3.endReplaceableGroup();
                i17++;
                composerStartRestartGroup = composer3;
                r32 = 0;
                i15 = 6;
                arrayList = arrayList;
                i16 = -1323940314;
            }
            composer2 = composerStartRestartGroup;
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
            scopeUpdateScopeEndRestartGroup.updateScope(new C04942(datePickerColors, calendarModel, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Year(Modifier modifier, boolean z, boolean z5, r7.a<t0> aVar, boolean z10, String str, DatePickerColors datePickerColors, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        int i11;
        Object objM207BorderStrokecXLIe8U;
        Composer composerStartRestartGroup = composer.startRestartGroup(238547184);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(z5) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changed(z10) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(str) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(datePickerColors) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((12582912 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar) ? 8388608 : 4194304;
        }
        if ((4793491 & i11) == 4793490 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(238547184, i11, -1, "androidx.compose.material3.Year (DatePicker.kt:2085)");
            }
            composerStartRestartGroup.startReplaceableGroup(84263149);
            int i12 = i11 & 112;
            boolean z11 = ((i11 & 896) == 256) | (i12 == 32);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z11 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objM207BorderStrokecXLIe8U = (!z5 || z) ? null : BorderStrokeKt.m207BorderStrokecXLIe8U(DatePickerModalTokens.INSTANCE.m2479getDateTodayContainerOutlineWidthD9Ej5fM(), datePickerColors.getTodayDateBorderColor());
                composerStartRestartGroup.updateRememberedValue(objM207BorderStrokecXLIe8U);
            } else {
                objM207BorderStrokecXLIe8U = objRememberedValue;
            }
            BorderStroke borderStroke = (BorderStroke) objM207BorderStrokecXLIe8U;
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(84263865);
            boolean z12 = (458752 & i11) == 131072;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z12 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new DatePickerKt$Year$1$1(str);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceableGroup();
            int i13 = i11 >> 3;
            int i14 = i13 & 14;
            int i15 = i11 >> 9;
            int i16 = i11 >> 6;
            SurfaceKt.m2005Surfaced85dljk(z, aVar, SemanticsModifierKt.semantics(modifier, true, (l) objRememberedValue2), z10, ShapesKt.getValue(DatePickerModalTokens.INSTANCE.getSelectionYearStateLayerShape(), composerStartRestartGroup, 6), datePickerColors.yearContainerColor$material3_release(z, z10, composerStartRestartGroup, i14 | (i15 & 112) | ((i11 >> 12) & 896)).getValue().m3482unboximpl(), datePickerColors.yearContentColor$material3_release(z5, z, z10, composerStartRestartGroup, (i16 & 14) | i12 | (i16 & 896) | (i15 & 7168)).getValue().m3482unboximpl(), 0.0f, 0.0f, borderStroke, (MutableInteractionSource) null, ComposableLambdaKt.composableLambda(composerStartRestartGroup, -1573188346, true, new C04952(pVar)), composerStartRestartGroup, i14 | (i16 & 112) | (i13 & 7168), 48, 1408);
            composerStartRestartGroup = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C04963(modifier, z, z5, aVar, z10, str, datePickerColors, pVar, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void YearPicker(Modifier modifier, long j10, l<? super Integer, t0> lVar, SelectableDates selectableDates, CalendarModel calendarModel, i iVar, DatePickerColors datePickerColors, Composer composer, int i10) {
        Modifier modifier2;
        int i11;
        l<? super Integer, t0> lVar2;
        SelectableDates selectableDates2;
        CalendarModel calendarModel2;
        i iVar2;
        DatePickerColors datePickerColors2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1286899812);
        if ((i10 & 6) == 0) {
            modifier2 = modifier;
            i11 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i10;
        } else {
            modifier2 = modifier;
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(j10) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            lVar2 = lVar;
            i11 |= composerStartRestartGroup.changedInstance(lVar2) ? 256 : 128;
        } else {
            lVar2 = lVar;
        }
        if ((i10 & 3072) == 0) {
            selectableDates2 = selectableDates;
            i11 |= composerStartRestartGroup.changed(selectableDates2) ? 2048 : 1024;
        } else {
            selectableDates2 = selectableDates;
        }
        if ((i10 & 24576) == 0) {
            calendarModel2 = calendarModel;
            i11 |= composerStartRestartGroup.changedInstance(calendarModel2) ? 16384 : 8192;
        } else {
            calendarModel2 = calendarModel;
        }
        if ((196608 & i10) == 0) {
            iVar2 = iVar;
            i11 |= composerStartRestartGroup.changedInstance(iVar2) ? 131072 : 65536;
        } else {
            iVar2 = iVar;
        }
        if ((1572864 & i10) == 0) {
            datePickerColors2 = datePickerColors;
            i11 |= composerStartRestartGroup.changed(datePickerColors2) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        } else {
            datePickerColors2 = datePickerColors;
        }
        if ((599187 & i11) == 599186 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1286899812, i11, -1, "androidx.compose.material3.YearPicker (DatePicker.kt:1993)");
            }
            TextKt.ProvideTextStyle(TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6), DatePickerModalTokens.INSTANCE.getSelectionYearLabelTextFont()), ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1301915789, true, new C04971(calendarModel2, j10, iVar2, datePickerColors2, modifier2, lVar2, selectableDates2)), composerStartRestartGroup, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new C04982(modifier, j10, lVar, selectableDates, calendarModel, iVar, datePickerColors, i10));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:64:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void YearPickerMenuButton(r7.a<x6.t0> r19, boolean r20, androidx.compose.ui.Modifier r21, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r22, androidx.compose.runtime.Composer r23, int r24, int r25) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DatePickerKt.YearPickerMenuButton(r7.a, boolean, androidx.compose.ui.Modifier, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<CustomAccessibilityAction> customScrollActions(LazyGridState lazyGridState, k0 k0Var, String str, String str2) {
        return t7.a.E(new CustomAccessibilityAction(str, new DatePickerKt$customScrollActions$scrollUpAction$1(lazyGridState, k0Var)), new CustomAccessibilityAction(str2, new DatePickerKt$customScrollActions$scrollDownAction$1(lazyGridState, k0Var)));
    }

    private static final String dayContentDescription(boolean z, boolean z5, boolean z10, boolean z11, boolean z12, Composer composer, int i10) {
        composer.startReplaceableGroup(502032503);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(502032503, i10, -1, "androidx.compose.material3.dayContentDescription (DatePicker.kt:1900)");
        }
        StringBuilder sb2 = new StringBuilder();
        composer.startReplaceableGroup(-852185051);
        if (z) {
            if (z10) {
                composer.startReplaceableGroup(-852184961);
                Strings.Companion companion = Strings.INSTANCE;
                sb2.append(Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_range_picker_start_headline), composer, 0));
                composer.endReplaceableGroup();
            } else if (z11) {
                composer.startReplaceableGroup(-852184821);
                Strings.Companion companion2 = Strings.INSTANCE;
                sb2.append(Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_range_picker_end_headline), composer, 0));
                composer.endReplaceableGroup();
            } else if (z12) {
                composer.startReplaceableGroup(-852184683);
                Strings.Companion companion3 = Strings.INSTANCE;
                sb2.append(Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_range_picker_day_in_range), composer, 0));
                composer.endReplaceableGroup();
            } else {
                composer.startReplaceableGroup(-852184582);
                composer.endReplaceableGroup();
            }
        }
        composer.endReplaceableGroup();
        if (z5) {
            if (sb2.length() > 0) {
                sb2.append(", ");
            }
            Strings.Companion companion4 = Strings.INSTANCE;
            sb2.append(Strings_androidKt.m1994getStringNWtq28(Strings.m1925constructorimpl(R.string.m3c_date_picker_today_description), composer, 0));
        }
        String string = sb2.length() == 0 ? null : sb2.toString();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return string;
    }

    public static final float getDatePickerHorizontalPadding() {
        return DatePickerHorizontalPadding;
    }

    public static final PaddingValues getDatePickerModeTogglePadding() {
        return DatePickerModeTogglePadding;
    }

    public static final float getMonthYearHeight() {
        return MonthYearHeight;
    }

    public static final float getRecommendedSizeForAccessibility() {
        return RecommendedSizeForAccessibility;
    }

    public static final int numberOfMonthsInRange(i iVar) {
        return ((iVar.f22620l - iVar.f22619i) + 1) * 12;
    }

    /* JADX INFO: renamed from: rememberDatePickerState-EU0dCGE, reason: not valid java name */
    public static final DatePickerState m1487rememberDatePickerStateEU0dCGE(Long l10, Long l11, i iVar, int i10, SelectableDates selectableDates, Composer composer, int i11, int i12) {
        composer.startReplaceableGroup(2065763010);
        if ((i12 & 1) != 0) {
            l10 = null;
        }
        Long l12 = l10;
        Long l13 = (i12 & 2) != 0 ? l12 : l11;
        if ((i12 & 4) != 0) {
            iVar = DatePickerDefaults.INSTANCE.getYearRange();
        }
        i iVar2 = iVar;
        if ((i12 & 8) != 0) {
            i10 = DisplayMode.INSTANCE.m1531getPickerjFl4v0();
        }
        int i13 = i10;
        if ((i12 & 16) != 0) {
            selectableDates = DatePickerDefaults.INSTANCE.getAllDates();
        }
        SelectableDates selectableDates2 = selectableDates;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2065763010, i11, -1, "androidx.compose.material3.rememberDatePickerState (DatePicker.kt:363)");
        }
        Locale localeDefaultLocale = ActualAndroid_androidKt.defaultLocale(composer, 0);
        Object[] objArr = new Object[0];
        Saver<DatePickerStateImpl, Object> Saver = DatePickerStateImpl.INSTANCE.Saver(selectableDates2, localeDefaultLocale);
        composer.startReplaceableGroup(-1398082866);
        boolean zChangedInstance = ((((57344 & i11) ^ 24576) > 16384 && composer.changed(selectableDates2)) || (i11 & 24576) == 16384) | ((((i11 & 14) ^ 6) > 4 && composer.changed(l12)) || (i11 & 6) == 4) | ((((i11 & 112) ^ 48) > 32 && composer.changed(l13)) || (i11 & 48) == 32) | composer.changedInstance(iVar2) | ((((i11 & 7168) ^ 3072) > 2048 && composer.changed(i13)) || (i11 & 3072) == 2048) | composer.changedInstance(localeDefaultLocale);
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Object datePickerKt$rememberDatePickerState$1$1 = new DatePickerKt$rememberDatePickerState$1$1(l12, l13, iVar2, i13, selectableDates2, localeDefaultLocale);
            composer.updateRememberedValue(datePickerKt$rememberDatePickerState$1$1);
            objRememberedValue = datePickerKt$rememberDatePickerState$1$1;
        }
        composer.endReplaceableGroup();
        DatePickerStateImpl datePickerStateImpl = (DatePickerStateImpl) RememberSaveableKt.m3084rememberSaveable(objArr, (Saver) Saver, (String) null, (r7.a) objRememberedValue, composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return datePickerStateImpl;
    }

    public static final Object updateDisplayedMonth(final LazyListState lazyListState, final l<? super Long, t0> lVar, final CalendarModel calendarModel, final i iVar, d7.d<? super t0> dVar) {
        Object objCollect = SnapshotStateKt.snapshotFlow(new C05012(lazyListState)).collect(new na.k() { // from class: androidx.compose.material3.DatePickerKt.updateDisplayedMonth.3
            @Override // na.k
            public /* bridge */ /* synthetic */ Object emit(Object obj, d7.d dVar2) {
                return emit(((Number) obj).intValue(), (d7.d<? super t0>) dVar2);
            }

            public final Object emit(int i10, d7.d<? super t0> dVar2) {
                int firstVisibleItemIndex = lazyListState.getFirstVisibleItemIndex() / 12;
                lVar.invoke(new Long(calendarModel.getMonth(iVar.f22619i + firstVisibleItemIndex, (lazyListState.getFirstVisibleItemIndex() % 12) + 1).getStartUtcTimeMillis()));
                return t0.f22605a;
            }
        }, dVar);
        return objCollect == e7.a.f15033i ? objCollect : t0.f22605a;
    }
}
