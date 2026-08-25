package com.arflix.tv.ui.screens.tv.live;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.Window;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.ButtonKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.datasource.okhttp.OkHttpDataSource;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.ui.PlayerView;
import androidx.mediarouter.media.GlobalMediaRouter;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import androidx.tv.material3.TextKt;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.arflix.tv.R;
import com.arflix.tv.data.model.DrmInfo;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.ui.components.AppTopBarKt;
import com.arflix.tv.ui.components.SidebarItem;
import com.arflix.tv.ui.screens.tv.TvUiState;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.ui.theme.ColorKt;
import com.fasterxml.jackson.core.JsonPointer;
import io.sentry.ProfilingTraceData;
import io.sentry.metrics.MetricsUnit;
import io.sentry.protocol.SentryThread;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import net.bytebuddy.pool.TypePool;
import org.fusesource.jansi.AnsiConsole;
import org.schabi.newpipe.extractor.services.media_ccc.extractors.MediaCCCRecentKiosk;

/* JADX INFO: compiled from: LiveTvScreen.kt */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000Ú\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\u001a\u0017\u0010\u000e\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000f\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\u0010\u001aV\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00132\u0006\u0010\u001c\u001a\u00020\u001aH\u0002\u001aH\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00132\u0006\u0010\u001f\u001a\u00020\u000b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001e0\u00132\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00132\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00132\u0006\u0010#\u001a\u00020\u0001H\u0000\u001a$\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010%2\u0006\u0010&\u001a\u00020\u00012\u0006\u0010'\u001a\u00020\u0001H\u0002\u001a,\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010%2\u0006\u0010)\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\u00012\u0006\u0010'\u001a\u00020\u0001H\u0002\u001a$\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010%2\u0006\u0010)\u001a\u00020\u00012\u0006\u0010*\u001a\u00020\u0001H\u0002\u001a\f\u0010,\u001a\u00020\u001a*\u00020\u0014H\u0002\u001a\u000e\u0010-\u001a\u00020\u001a*\u0004\u0018\u00010.H\u0002\u001a\u0018\u0010/\u001a\u00020\u001a2\u0006\u00100\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u000bH\u0002\u001a$\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b03*\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b03H\u0002\u001a*\u00104\u001a\b\u0012\u0004\u0012\u0002050\u00132\f\u00106\u001a\b\u0012\u0004\u0012\u0002050\u00132\f\u00107\u001a\b\u0012\u0004\u0012\u0002050\u0013H\u0002\u001a\u001e\u00108\u001a\u0004\u0018\u00010.2\b\u00109\u001a\u0004\u0018\u00010.2\b\u0010:\u001a\u0004\u0018\u00010.H\u0002\u001a\u0012\u0010;\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013*\u00020\u0014H\u0002\u001a\u0010\u0010<\u001a\u00020\u001a2\u0006\u0010=\u001a\u00020\u000bH\u0002\u001a\u0014\u0010>\u001a\u000205*\u0002052\u0006\u0010?\u001a\u00020\bH\u0002\u001a\u0014\u0010@\u001a\u00020\b*\u00020\u001e2\u0006\u0010?\u001a\u00020\bH\u0002\u001a\u0014\u0010A\u001a\u00020\b*\u00020\u001e2\u0006\u0010?\u001a\u00020\bH\u0002\u001a\u000e\u0010B\u001a\u00020\u001a*\u0004\u0018\u00010\u0014H\u0002\u001a\f\u0010C\u001a\u00020\u001a*\u00020\u0014H\u0002\u001a\u0010\u0010D\u001a\u00020\u00012\u0006\u0010E\u001a\u00020\u0014H\u0002\u001a\u001e\u0010F\u001a\u00020\u00142\u0006\u0010E\u001a\u00020\u00142\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0002\u001aß\u0001\u0010H\u001a\u00020I2\b\b\u0002\u0010J\u001a\u00020K2\n\b\u0002\u0010L\u001a\u0004\u0018\u00010M2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\u000b2\u0014\b\u0002\u0010P\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020I0Q2\u000e\b\u0002\u0010R\u001a\b\u0012\u0004\u0012\u00020I0S2\u000e\b\u0002\u0010T\u001a\b\u0012\u0004\u0012\u00020I0S2\u000e\b\u0002\u0010U\u001a\b\u0012\u0004\u0012\u00020I0S2\u000e\b\u0002\u0010V\u001a\b\u0012\u0004\u0012\u00020I0S2\u0010\b\u0002\u0010W\u001a\n\u0012\u0004\u0012\u00020I\u0018\u00010S2\u001a\b\u0002\u0010X\u001a\u0014\u0012\u0004\u0012\u00020Z\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020I0Y2\u000e\b\u0002\u0010[\u001a\b\u0012\u0004\u0012\u00020I0S2\u000e\b\u0002\u0010\\\u001a\b\u0012\u0004\u0012\u00020I0SH\u0007¢\u0006\u0002\u0010]\u001a\u001b\u0010^\u001a\u0004\u0018\u00010\u0001*\u00020_2\u0006\u0010\u001f\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010`\u001a\u0012\u0010d\u001a\u00020\u000b2\b\u0010e\u001a\u0004\u0018\u00010\u000bH\u0002\u001a\u0012\u0010f\u001a\u00020\u000b2\b\u0010e\u001a\u0004\u0018\u00010\u000bH\u0002\u001a\u0010\u0010g\u001a\u00020\u000b2\u0006\u0010h\u001a\u00020iH\u0002\u001a\u0018\u0010j\u001a\u00020k2\u0006\u0010l\u001a\u00020\u00012\u0006\u0010m\u001a\u00020\u001aH\u0002\u001a\u0017\u0010n\u001a\u0004\u0018\u00010\u00012\u0006\u0010h\u001a\u00020iH\u0002¢\u0006\u0002\u0010o\u001a\u0010\u0010p\u001a\u00020\u000b2\u0006\u0010=\u001a\u00020\u000bH\u0002\u001a\u000f\u0010q\u001a\u0004\u0018\u00010r*\u00020sH\u0082\u0010\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010a\u001a\u00020bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010c\u001a\u00020bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006t²\u0006\n\u0010u\u001a\u00020vX\u008a\u0084\u0002²\u0006\n\u0010w\u001a\u00020vX\u008a\u0084\u0002²\u0006\n\u0010x\u001a\u00020\bX\u008a\u0084\u0002²\u0006\n\u0010y\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010z\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010{\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\n\u0010|\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\"\u0010}\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010~0\u0013X\u008a\u008e\u0002²\u0006\u000b\u0010\u007f\u001a\u00030\u0080\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010\u0081\u0001\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010\u0082\u0001\u001a\u00020\bX\u008a\u008e\u0002²\u0006\r\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u000bX\u008a\u008e\u0002²\u0006\r\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u000bX\u008a\u008e\u0002²\u0006\r\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u000bX\u008a\u008e\u0002²\u0006\r\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u000bX\u008a\u008e\u0002²\u0006\u000b\u0010\u0087\u0001\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\r\u0010\u0088\u0001\u001a\u0004\u0018\u000105X\u008a\u008e\u0002²\u0006\u000b\u0010\u0089\u0001\u001a\u00020\bX\u008a\u008e\u0002²\u0006\r\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u0014X\u008a\u008e\u0002²\u0006\u000b\u0010\u008b\u0001\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010\u008c\u0001\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010\u008d\u0001\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\u000b\u0010\u008e\u0001\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\u000b\u0010\u008f\u0001\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010\u0090\u0001\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010\u0091\u0001\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010\u0092\u0001\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010\u0093\u0001\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\u000b\u0010\u0094\u0001\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\r\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u0014X\u008a\u008e\u0002²\u0006\u000e\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010\u0098\u0001\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\u000b\u0010\u0099\u0001\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\u000b\u0010\u009a\u0001\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\u000b\u0010\u009b\u0001\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\u000b\u0010\u009c\u0001\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\r\u0010\u009d\u0001\u001a\u0004\u0018\u00010\u0014X\u008a\u008e\u0002²\u0006\u000b\u0010\u009e\u0001\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\u000b\u0010\u009f\u0001\u001a\u00020\bX\u008a\u008e\u0002²\u0006\u000b\u0010 \u0001\u001a\u00020\bX\u008a\u008e\u0002²\u0006\u000b\u0010¡\u0001\u001a\u00020\bX\u008a\u008e\u0002²\u0006\u000b\u0010¢\u0001\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\u000b\u0010£\u0001\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\u000b\u0010¤\u0001\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\r\u0010¥\u0001\u001a\u0004\u0018\u00010\u000bX\u008a\u008e\u0002²\u0006\u000b\u0010¦\u0001\u001a\u00020\u001aX\u008a\u008e\u0002²\u0006\u0017\u0010§\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b03X\u008a\u008e\u0002²\u0006\u000b\u0010¨\u0001\u001a\u00020\bX\u008a\u008e\u0002²\u0006\u000b\u0010©\u0001\u001a\u00020\u0001X\u008a\u008e\u0002²\u0006\u000e\u0010ª\u0001\u001a\u0005\u0018\u00010«\u0001X\u008a\u008e\u0002²\u0006\f\u0010¬\u0001\u001a\u00030\u00ad\u0001X\u008a\u0084\u0002"}, d2 = {"GuideInitialWindowRows", "", "GuidePageRows", "GuideMaxWindowRows", "GuidePagedLoadStepRows", "GuideVisibleFirstRows", "GuideVisibleFirstRowsAllChannels", "CatchupSeekStepMs", "", "CatchupUrlAnchorGranularityMs", "IptvPlaybackUserAgent", "", "VisibleGuidePastWindowMs", "VisibleGuideFutureWindowMs", "digitForTvKeyCode", "keyCode", "(I)Ljava/lang/Integer;", "chooseStartupChannelId", "filteredChannels", "", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "filteredChannelIds", "", "explicitInitialChannelId", "sessionLastChannelId", "hasOpenedBefore", "", "favoriteChannelIds", "isFullyEnriched", "selectPagedChannelsInProviderOrder", "Lcom/arflix/tv/data/model/IptvChannel;", "categoryId", "providerWindow", "favoriteChannels", "recentChannels", "limit", "guideWindowAround", "Lkotlin/Pair;", "index", "total", "expandGuideWindowAfter", "start", TtmlNode.END, "expandGuideWindowBefore", "hasGuideIdentity", "hasGuideData", "Lcom/arflix/tv/data/model/IptvNowNext;", "isSafePlaybackHeader", "name", "value", "safePlaybackHeaders", "", "mergeProgramLists", "Lcom/arflix/tv/data/model/IptvProgram;", "first", MetricsUnit.Duration.SECOND, "mergeGuideSlices", "primary", "secondary", "guideFallbackKeys", "looksLikeMpegTsUrl", "url", "shiftedForCatchup", "offsetMs", "catchupUrlAnchorOffset", "catchupInSegmentSeekOffset", "supportsCatchupHistory", "hasExplicitCatchupSource", "catchupQualityRank", TvContractCompat.PARAM_CHANNEL, "catchupPlaybackVariant", "channels", "LiveTvScreen", "", "viewModel", "Lcom/arflix/tv/ui/screens/tv/TvViewModel;", "currentProfile", "Lcom/arflix/tv/data/model/Profile;", "initialChannelId", "initialStreamUrl", "onFullscreenChanged", "Lkotlin/Function1;", "onNavigateToHome", "Lkotlin/Function0;", "onNavigateToSearch", "onNavigateToWatchlist", "onNavigateToSettings", "onNavigateToIptvSettings", "onNavigateToDetails", "Lkotlin/Function2;", "Lcom/arflix/tv/data/model/MediaType;", "onSwitchProfile", "onBack", "(Lcom/arflix/tv/ui/screens/tv/TvViewModel;Lcom/arflix/tv/data/model/Profile;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;III)V", "countForCategory", "Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;", "(Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;Ljava/lang/String;)Ljava/lang/Integer;", "IptvGroupPipeSpacingRegex", "Lkotlin/text/Regex;", "IptvGroupWhitespaceRegex", "looseIptvGroupKey", "group", "compactIptvGroupKey", "classifyPlaybackError", MediaRouteProviderProtocol.SERVICE_DATA_ERROR, "Landroidx/media3/common/PlaybackException;", "buildLiveTvBufferProfile", "Lcom/arflix/tv/ui/screens/tv/live/LiveTvBufferProfile;", "memoryClassMb", "isLowRamDevice", "httpResponseCode", "(Landroidx/media3/common/PlaybackException;)Ljava/lang/Integer;", "redactPlaybackUrl", "findActivity", "Landroid/app/Activity;", "Landroid/content/Context;", "app_sideloadDebug", SentryThread.JsonKeys.STATE, "Lcom/arflix/tv/ui/screens/tv/TvUiState;", "currentUiState", "guideClockMillis", "selectedCategoryId", "selectedProviderId", "pagedLoadedLimit", "lastKnownPagedTotal", "lastKnownPlaylistGroupCounts", "Lkotlin/Triple;", "focusZone", "Lcom/arflix/tv/ui/screens/tv/live/LiveTvFocusZone;", "topBarFocusIndex", "lastGuideUserNavigationAt", "filteredChannelsCategoryKey", "playingChannelId", "focusedChannelId", "epgPrefetchAnchorId", "startupChannelApplied", "playingCatchupProgram", "catchupPlaybackOffsetMs", "retainedPlayingChannel", "guideWindowStart", "guideWindowEnd", "guideScopeKey", "searchOpen", "focusSelectedChannelSignal", "focusEpgSignal", "focusSearchCategorySignal", "focusCategoryRailSignal", "isFullScreen", "fullscreenGuideOpen", "variantPickerChannel", "programActionDialog", "Lcom/arflix/tv/ui/screens/tv/live/ProgramActionData;", "programActionSearching", "hudPokeSignal", "quickZapOpen", "isHudVisible", "guideOpenedFromQuickZap", "guideChannel", "channelNumberBuffer", "lastChannelDigitAt", "playerPositionMs", "playerDurationMs", "playerIsPlaying", "playerPlayWhenReady", "playerIsBuffering", "lastPreparedStreamUrl", "lastPreparedIsHls", "lastPreparedHeaders", "lastPreparedCatchupOffsetMs", "playerRetryCount", "playbackDiagnostic", "Lcom/arflix/tv/ui/screens/tv/live/PlaybackDiagnostic;", "fsProgress", ""}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LiveTvScreenKt {
    private static final long CatchupSeekStepMs = 30000;
    private static final long CatchupUrlAnchorGranularityMs = 60000;
    private static final int GuideInitialWindowRows = 48;
    private static final int GuideMaxWindowRows = 144;
    private static final int GuidePageRows = 48;
    private static final int GuidePagedLoadStepRows = 192;
    private static final int GuideVisibleFirstRows = 28;
    private static final int GuideVisibleFirstRowsAllChannels = 18;
    private static final Regex IptvGroupPipeSpacingRegex = new Regex("\\s*\\|\\s*");
    private static final Regex IptvGroupWhitespaceRegex = new Regex("\\s+");
    private static final String IptvPlaybackUserAgent = "VLC/3.0.20 LibVLC/3.0.20";
    private static final long VisibleGuideFutureWindowMs = 172800000;
    private static final long VisibleGuidePastWindowMs = 172800000;

    /* JADX INFO: compiled from: LiveTvScreen.kt */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Quality.values().length];
            try {
                iArr[Quality.K4.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Quality.FHD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Quality.HD.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Quality.SD.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Lifecycle.Event.values().length];
            try {
                iArr2[Lifecycle.Event.ON_PAUSE.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[Lifecycle.Event.ON_RESUME.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$259(TvViewModel tvViewModel, Profile profile, String str, String str2, Function1 function1, Function0 function0, Function0 function02, Function0 function03, Function0 function04, Function0 function05, Function2 function2, Function0 function06, Function0 function07, int i, int i2, int i3, Composer composer, int i4) {
        LiveTvScreen(tvViewModel, profile, str, str2, function1, function0, function02, function03, function04, function05, function2, function06, function07, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer digitForTvKeyCode(int keyCode) {
        switch (keyCode) {
            case 7:
            case 144:
                return 0;
            case 8:
            case 145:
                return 1;
            case 9:
            case 146:
                return 2;
            case 10:
            case 147:
                return 3;
            case 11:
            case 148:
                return 4;
            case 12:
            case 149:
                return 5;
            case 13:
            case 150:
                return 6;
            case 14:
            case 151:
                return 7;
            case 15:
            case 152:
                return 8;
            case 16:
            case 153:
                return 9;
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String chooseStartupChannelId(List<EnrichedChannel> list, Set<String> set, String explicitInitialChannelId, String sessionLastChannelId, boolean hasOpenedBefore, List<String> list2, boolean isFullyEnriched) {
        Object next;
        if (explicitInitialChannelId != null) {
            String str = set.contains(explicitInitialChannelId) ? explicitInitialChannelId : null;
            if (str != null) {
                return str;
            }
        }
        if (explicitInitialChannelId != null && !isFullyEnriched) {
            return null;
        }
        Iterator it = list2.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (set.contains((String) next)) {
                break;
            }
        }
        String str2 = (String) next;
        if (str2 != null) {
            return str2;
        }
        if (!list2.isEmpty() && !isFullyEnriched) {
            return null;
        }
        if (hasOpenedBefore) {
            String str3 = !StringsKt.isBlank(sessionLastChannelId) && set.contains(sessionLastChannelId) ? sessionLastChannelId : null;
            if (str3 != null) {
                return str3;
            }
            if (!StringsKt.isBlank(sessionLastChannelId) && !isFullyEnriched) {
                return null;
            }
        }
        return ((EnrichedChannel) CollectionsKt.first((List) list)).getId();
    }

    public static final List<IptvChannel> selectPagedChannelsInProviderOrder(String categoryId, List<IptvChannel> providerWindow, List<IptvChannel> favoriteChannels, List<IptvChannel> recentChannels, int limit) {
        List<IptvChannel> list;
        Intrinsics.checkNotNullParameter(categoryId, "categoryId");
        Intrinsics.checkNotNullParameter(providerWindow, "providerWindow");
        Intrinsics.checkNotNullParameter(favoriteChannels, "favoriteChannels");
        Intrinsics.checkNotNullParameter(recentChannels, "recentChannels");
        if (Intrinsics.areEqual(categoryId, "fav")) {
            list = favoriteChannels;
        } else {
            list = Intrinsics.areEqual(categoryId, MediaCCCRecentKiosk.KIOSK_ID) ? recentChannels : providerWindow;
        }
        return CollectionsKt.take(list, RangesKt.coerceAtLeast(limit, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair<Integer, Integer> guideWindowAround(int index, int total) {
        if (total <= 0) {
            return TuplesKt.to(0, 0);
        }
        int safeIndex = RangesKt.coerceIn(index, 0, total - 1);
        int start = RangesKt.coerceAtLeast(safeIndex - 0, 0);
        int end = RangesKt.coerceAtMost(start + 48, total);
        int balancedStart = RangesKt.coerceAtLeast(end - 48, 0);
        return TuplesKt.to(Integer.valueOf(balancedStart), Integer.valueOf(end));
    }

    private static final Pair<Integer, Integer> expandGuideWindowAfter(int start, int end, int total) {
        if (end >= total) {
            return TuplesKt.to(Integer.valueOf(start), Integer.valueOf(end));
        }
        int nextEnd = RangesKt.coerceAtMost(end + 48, total);
        int overflow = RangesKt.coerceAtLeast((nextEnd - start) - 144, 0);
        return TuplesKt.to(Integer.valueOf(RangesKt.coerceAtMost(start + overflow, nextEnd)), Integer.valueOf(nextEnd));
    }

    private static final Pair<Integer, Integer> expandGuideWindowBefore(int start, int end) {
        if (start <= 0) {
            return TuplesKt.to(Integer.valueOf(start), Integer.valueOf(end));
        }
        int nextStart = RangesKt.coerceAtLeast(start - 48, 0);
        int overflow = RangesKt.coerceAtLeast((end - nextStart) - 144, 0);
        return TuplesKt.to(Integer.valueOf(nextStart), Integer.valueOf(RangesKt.coerceAtLeast(end - overflow, nextStart)));
    }

    private static final boolean hasGuideIdentity(EnrichedChannel $this$hasGuideIdentity) {
        String epgId = $this$hasGuideIdentity.getSource().getEpgId();
        if (epgId == null || StringsKt.isBlank(epgId)) {
            String tvgName = $this$hasGuideIdentity.getSource().getTvgName();
            if (tvgName == null || StringsKt.isBlank(tvgName)) {
                return false;
            }
        }
        return true;
    }

    private static final boolean hasGuideData(IptvNowNext $this$hasGuideData) {
        return ($this$hasGuideData == null || ($this$hasGuideData.getNow() == null && $this$hasGuideData.getNext() == null && $this$hasGuideData.getLater() == null && $this$hasGuideData.getUpcoming().isEmpty() && $this$hasGuideData.getRecent().isEmpty())) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00f3 A[LOOP:0: B:7:0x001a->B:22:0x00f3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0127 A[LOOP:1: B:26:0x0103->B:41:0x0127, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00f0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0124 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final boolean isSafePlaybackHeader(java.lang.String r28, java.lang.String r29) {
        /*
            Method dump skipped, instruction units count: 311
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.isSafePlaybackHeader(java.lang.String, java.lang.String):boolean");
    }

    private static final Map<String, String> safePlaybackHeaders(Map<String, String> map) {
        if (map.isEmpty()) {
            return MapsKt.emptyMap();
        }
        Map linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (isSafePlaybackHeader(StringsKt.trim((CharSequence) entry.getKey()).toString(), StringsKt.trim((CharSequence) entry.getValue()).toString())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Map linkedHashMap2 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap.size()));
        for (Object obj : linkedHashMap.entrySet()) {
            linkedHashMap2.put(StringsKt.trim((CharSequence) ((Map.Entry) obj).getKey()).toString(), ((Map.Entry) obj).getValue());
        }
        Map linkedHashMap3 = new LinkedHashMap(MapsKt.mapCapacity(linkedHashMap2.size()));
        for (Object obj2 : linkedHashMap2.entrySet()) {
            linkedHashMap3.put(((Map.Entry) obj2).getKey(), StringsKt.trim((CharSequence) ((Map.Entry) obj2).getValue()).toString());
        }
        return linkedHashMap3;
    }

    private static final List<IptvProgram> mergeProgramLists(List<IptvProgram> list, List<IptvProgram> list2) {
        if (list.isEmpty()) {
            return list2;
        }
        if (list2.isEmpty()) {
            return list;
        }
        Iterable iterablePlus = CollectionsKt.plus((Collection) list, (Iterable) list2);
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : iterablePlus) {
            IptvProgram iptvProgram = (IptvProgram) obj;
            if (hashSet.add(iptvProgram.getStartUtcMillis() + ":" + iptvProgram.getEndUtcMillis() + ":" + iptvProgram.getTitle())) {
                arrayList.add(obj);
            }
        }
        return CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$mergeProgramLists$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Long.valueOf(((IptvProgram) t).getStartUtcMillis()), Long.valueOf(((IptvProgram) t2).getStartUtcMillis()));
            }
        });
    }

    private static final IptvNowNext mergeGuideSlices(IptvNowNext primary, IptvNowNext secondary) {
        if (!hasGuideData(primary)) {
            return secondary;
        }
        if (!hasGuideData(secondary)) {
            return primary;
        }
        if (primary == null) {
            return secondary;
        }
        if (secondary == null) {
            return primary;
        }
        IptvProgram now = primary.getNow();
        if (now == null) {
            now = secondary.getNow();
        }
        IptvProgram iptvProgram = now;
        IptvProgram next = primary.getNext();
        if (next == null) {
            next = secondary.getNext();
        }
        IptvProgram iptvProgram2 = next;
        IptvProgram later = primary.getLater();
        if (later == null) {
            later = secondary.getLater();
        }
        return new IptvNowNext(iptvProgram, iptvProgram2, later, mergeProgramLists(primary.getUpcoming(), secondary.getUpcoming()), mergeProgramLists(primary.getRecent(), secondary.getRecent()));
    }

    private static final List<String> guideFallbackKeys(EnrichedChannel $this$guideFallbackKeys) {
        String playlistId = StringsKt.trim((CharSequence) StringsKt.substringBefore($this$guideFallbackKeys.getId(), AbstractJsonLexerKt.COLON, "")).toString();
        String str = playlistId;
        if (StringsKt.isBlank(str)) {
            str = AnsiConsole.JANSI_MODE_DEFAULT;
        }
        String prefix = str;
        LinkedHashSet keys = new LinkedHashSet();
        guideFallbackKeys$addKey(keys, prefix, "epg", $this$guideFallbackKeys.getSource().getEpgId());
        guideFallbackKeys$addKey(keys, prefix, "tvg", $this$guideFallbackKeys.getSource().getTvgName());
        String variantKey = $this$guideFallbackKeys.getSource().getVariantKey();
        if (variantKey != null) {
            if (Intrinsics.areEqual(variantKey, $this$guideFallbackKeys.getSource().getId())) {
                variantKey = null;
            }
            if (variantKey != null) {
                guideFallbackKeys$addKey(keys, prefix, "variant", variantKey);
            }
        }
        guideFallbackKeys$addKey(keys, prefix, "name", StringsKt.trim((CharSequence) LiveTvScreenRegexes.INSTANCE.getMULTI_SPACE().replace(LiveTvScreenRegexes.INSTANCE.getQUALITY_REMOVAL().replace(StringsKt.substringAfter($this$guideFallbackKeys.getName(), '|', $this$guideFallbackKeys.getName()), " "), " ")).toString());
        return CollectionsKt.toList(keys);
    }

    private static final void guideFallbackKeys$addKey(LinkedHashSet<String> linkedHashSet, String prefix, String kind, String value) {
        String string;
        if (value == null || (string = StringsKt.trim((CharSequence) value).toString()) == null) {
            return;
        }
        String normalized = string.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(normalized, "toLowerCase(...)");
        if (normalized != null) {
            if (StringsKt.isBlank(normalized)) {
                normalized = null;
            }
            if (normalized != null) {
                linkedHashSet.add(prefix + "|" + kind + ":" + normalized);
            }
        }
    }

    private static final boolean looksLikeMpegTsUrl(String url) {
        String lower = url.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lower, "toLowerCase(...)");
        String path = StringsKt.substringBefore$default(lower, '?', (String) null, 2, (Object) null);
        if (StringsKt.endsWith$default(path, ".m3u8", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) lower, (CharSequence) "output=m3u8", false, 2, (Object) null)) {
            return false;
        }
        if (StringsKt.endsWith$default(path, ".ts", false, 2, (Object) null) || StringsKt.endsWith$default(path, "timeshift.php", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) lower, (CharSequence) "output=ts", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) path, (CharSequence) "/timeshift/", false, 2, (Object) null)) {
            return true;
        }
        Iterable iterableSplit$default = StringsKt.split$default((CharSequence) StringsKt.trim(StringsKt.substringAfter(StringsKt.substringAfter(path, "://", ""), JsonPointer.SEPARATOR, ""), JsonPointer.SEPARATOR), new char[]{JsonPointer.SEPARATOR}, false, 0, 6, (Object) null);
        Collection arrayList = new ArrayList();
        for (Object obj : iterableSplit$default) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        List segments = (List) arrayList;
        return segments.size() >= 4 && StringsKt.equals((String) CollectionsKt.first(segments), "live", true) && StringsKt.toIntOrNull(StringsKt.substringBefore$default((String) CollectionsKt.last(segments), TypePool.Default.LazyTypeDescription.GenericTypeToken.INNER_CLASS_PATH, (String) null, 2, (Object) null)) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IptvProgram shiftedForCatchup(IptvProgram $this$shiftedForCatchup, long offsetMs) {
        long latestStartOffset = RangesKt.coerceAtLeast(($this$shiftedForCatchup.getEndUtcMillis() - $this$shiftedForCatchup.getStartUtcMillis()) - 1000, 0L);
        long safeOffset = RangesKt.coerceIn(offsetMs, 0L, latestStartOffset);
        return safeOffset <= 0 ? $this$shiftedForCatchup : IptvProgram.copy$default($this$shiftedForCatchup, null, null, RangesKt.coerceAtMost($this$shiftedForCatchup.getStartUtcMillis() + safeOffset, $this$shiftedForCatchup.getEndUtcMillis() - 1000), 0L, null, 27, null);
    }

    private static final long catchupUrlAnchorOffset(IptvChannel $this$catchupUrlAnchorOffset, long offsetMs) {
        String type;
        String string;
        long safeOffset = RangesKt.coerceAtLeast(offsetMs, 0L);
        String catchupType = $this$catchupUrlAnchorOffset.getCatchupType();
        if (catchupType == null || (string = StringsKt.trim((CharSequence) catchupType).toString()) == null) {
            type = null;
        } else {
            type = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(type, "toLowerCase(...)");
        }
        if (type == null) {
            type = "";
        }
        boolean usesMinuteStart = true;
        if (!SetsKt.setOf((Object[]) new String[]{"xtream", "xc", "xciptv", "timeshift"}).contains(type) && $this$catchupUrlAnchorOffset.getXtreamStreamId() == null && !StringsKt.contains((CharSequence) $this$catchupUrlAnchorOffset.getStreamUrl(), (CharSequence) "/live/", true)) {
            usesMinuteStart = false;
        }
        if (usesMinuteStart) {
            return safeOffset - (safeOffset % 60000);
        }
        return safeOffset;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long catchupInSegmentSeekOffset(IptvChannel $this$catchupInSegmentSeekOffset, long offsetMs) {
        long safeOffset = RangesKt.coerceAtLeast(offsetMs, 0L);
        return RangesKt.coerceAtLeast(safeOffset - catchupUrlAnchorOffset($this$catchupInSegmentSeekOffset, safeOffset), 0L);
    }

    private static final boolean supportsCatchupHistory(EnrichedChannel $this$supportsCatchupHistory) {
        IptvChannel source;
        if ($this$supportsCatchupHistory == null || (source = $this$supportsCatchupHistory.getSource()) == null) {
            return false;
        }
        if (source.getCatchupDays() > 0) {
            return true;
        }
        String catchupType = source.getCatchupType();
        if (catchupType == null || StringsKt.isBlank(catchupType)) {
            String catchupSource = source.getCatchupSource();
            if (catchupSource == null || StringsKt.isBlank(catchupSource)) {
                return StringsKt.contains((CharSequence) source.getStreamUrl(), (CharSequence) "/timeshift/", true) || source.getXtreamStreamId() != null || StringsKt.contains((CharSequence) source.getStreamUrl(), (CharSequence) "/live/", true);
            }
        }
        return true;
    }

    private static final boolean hasExplicitCatchupSource(EnrichedChannel $this$hasExplicitCatchupSource) {
        IptvChannel source = $this$hasExplicitCatchupSource.getSource();
        if (source.getCatchupDays() > 0) {
            return true;
        }
        String catchupType = source.getCatchupType();
        if (catchupType == null || StringsKt.isBlank(catchupType)) {
            String catchupSource = source.getCatchupSource();
            if (catchupSource == null || StringsKt.isBlank(catchupSource)) {
                return StringsKt.contains((CharSequence) source.getStreamUrl(), (CharSequence) "/timeshift/", true);
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int catchupQualityRank(EnrichedChannel channel) {
        switch (WhenMappings.$EnumSwitchMapping$0[channel.getQuality().ordinal()]) {
            case 1:
                return 4;
            case 2:
                return 3;
            case 3:
                return 2;
            case 4:
                return 1;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final EnrichedChannel catchupPlaybackVariant(final EnrichedChannel channel, List<EnrichedChannel> list) {
        if (hasExplicitCatchupSource(channel)) {
            return channel;
        }
        final String key = LiveTvEnhancementsKt.variantGroupKey(channel);
        Sequence sequenceFilter = SequencesKt.filter(SequencesKt.filter(CollectionsKt.asSequence(list), new Function1() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(LiveTvScreenKt.catchupPlaybackVariant$lambda$0(channel, key, (EnrichedChannel) obj));
            }
        }), new Function1() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$$ExternalSyntheticLambda35
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(LiveTvScreenKt.catchupPlaybackVariant$lambda$1((EnrichedChannel) obj));
            }
        });
        final Comparator comparator = new Comparator() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$catchupPlaybackVariant$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((EnrichedChannel) t).getSource().getCatchupDays()), Integer.valueOf(((EnrichedChannel) t2).getSource().getCatchupDays()));
            }
        };
        EnrichedChannel enrichedChannel = (EnrichedChannel) SequencesKt.maxWithOrNull(sequenceFilter, new Comparator() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$catchupPlaybackVariant$$inlined$thenBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int previousCompare = comparator.compare(t, t2);
                return previousCompare != 0 ? previousCompare : ComparisonsKt.compareValues(Integer.valueOf(LiveTvScreenKt.catchupQualityRank((EnrichedChannel) t)), Integer.valueOf(LiveTvScreenKt.catchupQualityRank((EnrichedChannel) t2)));
            }
        });
        if (enrichedChannel != null) {
            return enrichedChannel;
        }
        return channel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean catchupPlaybackVariant$lambda$0(EnrichedChannel $channel, String $key, EnrichedChannel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return !Intrinsics.areEqual(it.getId(), $channel.getId()) && Intrinsics.areEqual(LiveTvEnhancementsKt.variantGroupKey(it), $key);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean catchupPlaybackVariant$lambda$1(EnrichedChannel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return hasExplicitCatchupSource(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$0$0(boolean it) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$5$0(MediaType mediaType, int i) {
        Intrinsics.checkNotNullParameter(mediaType, "<unused var>");
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable search skipped. Vars limit reached: 7279 (expected less than 5000) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:1002:0x285b  */
    /* JADX WARN: Removed duplicated region for block: B:1043:0x293c  */
    /* JADX WARN: Removed duplicated region for block: B:1046:0x2946  */
    /* JADX WARN: Removed duplicated region for block: B:1054:0x2a1a  */
    /* JADX WARN: Removed duplicated region for block: B:1055:0x2a1c  */
    /* JADX WARN: Removed duplicated region for block: B:1066:0x2b10  */
    /* JADX WARN: Removed duplicated region for block: B:1067:0x2b13  */
    /* JADX WARN: Removed duplicated region for block: B:1070:0x2b39  */
    /* JADX WARN: Removed duplicated region for block: B:1071:0x2b4b  */
    /* JADX WARN: Removed duplicated region for block: B:1074:0x2b98  */
    /* JADX WARN: Removed duplicated region for block: B:1075:0x2ba5  */
    /* JADX WARN: Removed duplicated region for block: B:1078:0x2bcc  */
    /* JADX WARN: Removed duplicated region for block: B:1079:0x2bd9  */
    /* JADX WARN: Removed duplicated region for block: B:1082:0x2bff  */
    /* JADX WARN: Removed duplicated region for block: B:1083:0x2c0c  */
    /* JADX WARN: Removed duplicated region for block: B:1086:0x2c33  */
    /* JADX WARN: Removed duplicated region for block: B:1087:0x2c40  */
    /* JADX WARN: Removed duplicated region for block: B:1090:0x2c5a  */
    /* JADX WARN: Removed duplicated region for block: B:1091:0x2c5c  */
    /* JADX WARN: Removed duplicated region for block: B:1102:0x2cda  */
    /* JADX WARN: Removed duplicated region for block: B:1103:0x2cf7  */
    /* JADX WARN: Removed duplicated region for block: B:1106:0x2d24  */
    /* JADX WARN: Removed duplicated region for block: B:1107:0x2d38  */
    /* JADX WARN: Removed duplicated region for block: B:1110:0x2d5f  */
    /* JADX WARN: Removed duplicated region for block: B:1111:0x2d73  */
    /* JADX WARN: Removed duplicated region for block: B:1114:0x2d9c  */
    /* JADX WARN: Removed duplicated region for block: B:1115:0x2db7  */
    /* JADX WARN: Removed duplicated region for block: B:1118:0x2dda  */
    /* JADX WARN: Removed duplicated region for block: B:1119:0x2ddc  */
    /* JADX WARN: Removed duplicated region for block: B:1130:0x2e39  */
    /* JADX WARN: Removed duplicated region for block: B:1131:0x2e3b  */
    /* JADX WARN: Removed duplicated region for block: B:1142:0x2e8d  */
    /* JADX WARN: Removed duplicated region for block: B:1143:0x2e9a  */
    /* JADX WARN: Removed duplicated region for block: B:1146:0x2ebf  */
    /* JADX WARN: Removed duplicated region for block: B:1147:0x2ecc  */
    /* JADX WARN: Removed duplicated region for block: B:1150:0x2ef0  */
    /* JADX WARN: Removed duplicated region for block: B:1151:0x2efd  */
    /* JADX WARN: Removed duplicated region for block: B:1154:0x2f21  */
    /* JADX WARN: Removed duplicated region for block: B:1155:0x2f2e  */
    /* JADX WARN: Removed duplicated region for block: B:1158:0x2f52  */
    /* JADX WARN: Removed duplicated region for block: B:1159:0x2f5f  */
    /* JADX WARN: Removed duplicated region for block: B:1162:0x2f8b  */
    /* JADX WARN: Removed duplicated region for block: B:1163:0x2fa6  */
    /* JADX WARN: Removed duplicated region for block: B:1166:0x2fce  */
    /* JADX WARN: Removed duplicated region for block: B:1167:0x2fea  */
    /* JADX WARN: Removed duplicated region for block: B:1170:0x3015  */
    /* JADX WARN: Removed duplicated region for block: B:1171:0x3031  */
    /* JADX WARN: Removed duplicated region for block: B:1174:0x305a  */
    /* JADX WARN: Removed duplicated region for block: B:1175:0x3076  */
    /* JADX WARN: Removed duplicated region for block: B:1178:0x30a0  */
    /* JADX WARN: Removed duplicated region for block: B:1179:0x30b4  */
    /* JADX WARN: Removed duplicated region for block: B:1182:0x30f9  */
    /* JADX WARN: Removed duplicated region for block: B:1186:0x310b  */
    /* JADX WARN: Removed duplicated region for block: B:1190:0x3180  */
    /* JADX WARN: Removed duplicated region for block: B:1194:0x31a1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1198:0x3213  */
    /* JADX WARN: Removed duplicated region for block: B:1199:0x322c  */
    /* JADX WARN: Removed duplicated region for block: B:1202:0x325b  */
    /* JADX WARN: Removed duplicated region for block: B:1203:0x3274  */
    /* JADX WARN: Removed duplicated region for block: B:1206:0x32dc  */
    /* JADX WARN: Removed duplicated region for block: B:1210:0x3308  */
    /* JADX WARN: Removed duplicated region for block: B:1214:0x3387  */
    /* JADX WARN: Removed duplicated region for block: B:1215:0x33db  */
    /* JADX WARN: Removed duplicated region for block: B:1218:0x3406  */
    /* JADX WARN: Removed duplicated region for block: B:1219:0x3457  */
    /* JADX WARN: Removed duplicated region for block: B:1222:0x347c  */
    /* JADX WARN: Removed duplicated region for block: B:1226:0x348f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1230:0x34d9  */
    /* JADX WARN: Removed duplicated region for block: B:1234:0x34ee  */
    /* JADX WARN: Removed duplicated region for block: B:1238:0x352b  */
    /* JADX WARN: Removed duplicated region for block: B:1242:0x3540 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1245:0x3554  */
    /* JADX WARN: Removed duplicated region for block: B:1246:0x3557  */
    /* JADX WARN: Removed duplicated region for block: B:1248:0x355a  */
    /* JADX WARN: Removed duplicated region for block: B:1249:0x355f  */
    /* JADX WARN: Removed duplicated region for block: B:1251:0x3563  */
    /* JADX WARN: Removed duplicated region for block: B:1254:0x3572 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1259:0x35a0  */
    /* JADX WARN: Removed duplicated region for block: B:1263:0x35b9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1267:0x364f  */
    /* JADX WARN: Removed duplicated region for block: B:1271:0x365c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1275:0x368d  */
    /* JADX WARN: Removed duplicated region for block: B:1276:0x3698  */
    /* JADX WARN: Removed duplicated region for block: B:1279:0x36b8  */
    /* JADX WARN: Removed duplicated region for block: B:1280:0x36c3  */
    /* JADX WARN: Removed duplicated region for block: B:1283:0x36e3  */
    /* JADX WARN: Removed duplicated region for block: B:1284:0x36ff  */
    /* JADX WARN: Removed duplicated region for block: B:1287:0x3729  */
    /* JADX WARN: Removed duplicated region for block: B:1288:0x3745  */
    /* JADX WARN: Removed duplicated region for block: B:1291:0x3770  */
    /* JADX WARN: Removed duplicated region for block: B:1292:0x378c  */
    /* JADX WARN: Removed duplicated region for block: B:1295:0x37c3  */
    /* JADX WARN: Removed duplicated region for block: B:1299:0x37d8  */
    /* JADX WARN: Removed duplicated region for block: B:1303:0x3870  */
    /* JADX WARN: Removed duplicated region for block: B:1307:0x388d  */
    /* JADX WARN: Removed duplicated region for block: B:1311:0x38e4  */
    /* JADX WARN: Removed duplicated region for block: B:1312:0x38f8  */
    /* JADX WARN: Removed duplicated region for block: B:1315:0x3921  */
    /* JADX WARN: Removed duplicated region for block: B:1316:0x393d  */
    /* JADX WARN: Removed duplicated region for block: B:1319:0x3966  */
    /* JADX WARN: Removed duplicated region for block: B:1320:0x3980  */
    /* JADX WARN: Removed duplicated region for block: B:1323:0x39ab  */
    /* JADX WARN: Removed duplicated region for block: B:1324:0x39b8  */
    /* JADX WARN: Removed duplicated region for block: B:1327:0x39dc  */
    /* JADX WARN: Removed duplicated region for block: B:1328:0x39e9  */
    /* JADX WARN: Removed duplicated region for block: B:1331:0x3a0d  */
    /* JADX WARN: Removed duplicated region for block: B:1332:0x3a21  */
    /* JADX WARN: Removed duplicated region for block: B:1335:0x3a55  */
    /* JADX WARN: Removed duplicated region for block: B:1339:0x3a6b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1342:0x3a71  */
    /* JADX WARN: Removed duplicated region for block: B:1343:0x3a7b  */
    /* JADX WARN: Removed duplicated region for block: B:1351:0x3ad4  */
    /* JADX WARN: Removed duplicated region for block: B:1355:0x3ae2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1359:0x3b05  */
    /* JADX WARN: Removed duplicated region for block: B:1360:0x3b0a  */
    /* JADX WARN: Removed duplicated region for block: B:1363:0x3b73  */
    /* JADX WARN: Removed duplicated region for block: B:1367:0x3b97  */
    /* JADX WARN: Removed duplicated region for block: B:1371:0x3c02  */
    /* JADX WARN: Removed duplicated region for block: B:1372:0x3c07  */
    /* JADX WARN: Removed duplicated region for block: B:1375:0x3c70  */
    /* JADX WARN: Removed duplicated region for block: B:1379:0x3c92 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1383:0x3cf2  */
    /* JADX WARN: Removed duplicated region for block: B:1384:0x3cf4  */
    /* JADX WARN: Removed duplicated region for block: B:1387:0x3d1d  */
    /* JADX WARN: Removed duplicated region for block: B:1391:0x3d3d  */
    /* JADX WARN: Removed duplicated region for block: B:1395:0x3dba  */
    /* JADX WARN: Removed duplicated region for block: B:1399:0x3dd6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1403:0x3e30  */
    /* JADX WARN: Removed duplicated region for block: B:1407:0x3e3e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1411:0x3e60  */
    /* JADX WARN: Removed duplicated region for block: B:1414:0x3e68  */
    /* JADX WARN: Removed duplicated region for block: B:1417:0x3e86  */
    /* JADX WARN: Removed duplicated region for block: B:1418:0x3e9a  */
    /* JADX WARN: Removed duplicated region for block: B:1426:0x3ec2  */
    /* JADX WARN: Removed duplicated region for block: B:1429:0x3edd  */
    /* JADX WARN: Removed duplicated region for block: B:1430:0x3ef1  */
    /* JADX WARN: Removed duplicated region for block: B:1438:0x3f18  */
    /* JADX WARN: Removed duplicated region for block: B:1441:0x3f88  */
    /* JADX WARN: Removed duplicated region for block: B:1445:0x3fbd  */
    /* JADX WARN: Removed duplicated region for block: B:1449:0x403a  */
    /* JADX WARN: Removed duplicated region for block: B:1454:0x4048  */
    /* JADX WARN: Removed duplicated region for block: B:1457:0x4057  */
    /* JADX WARN: Removed duplicated region for block: B:1458:0x4059  */
    /* JADX WARN: Removed duplicated region for block: B:1477:0x40e5  */
    /* JADX WARN: Removed duplicated region for block: B:1484:0x4119  */
    /* JADX WARN: Removed duplicated region for block: B:1485:0x411e  */
    /* JADX WARN: Removed duplicated region for block: B:1496:0x4176  */
    /* JADX WARN: Removed duplicated region for block: B:1497:0x4187  */
    /* JADX WARN: Removed duplicated region for block: B:1516:0x4245  */
    /* JADX WARN: Removed duplicated region for block: B:1545:0x43ef  */
    /* JADX WARN: Removed duplicated region for block: B:1548:0x44a4  */
    /* JADX WARN: Removed duplicated region for block: B:1551:0x44b0  */
    /* JADX WARN: Removed duplicated region for block: B:1552:0x44b6  */
    /* JADX WARN: Removed duplicated region for block: B:1563:0x456e  */
    /* JADX WARN: Removed duplicated region for block: B:1564:0x45df  */
    /* JADX WARN: Removed duplicated region for block: B:1659:0x4b66  */
    /* JADX WARN: Removed duplicated region for block: B:1660:0x4b69  */
    /* JADX WARN: Removed duplicated region for block: B:1663:0x4bab  */
    /* JADX WARN: Removed duplicated region for block: B:1664:0x4bae  */
    /* JADX WARN: Removed duplicated region for block: B:1675:0x4cdf  */
    /* JADX WARN: Removed duplicated region for block: B:1679:0x4d08  */
    /* JADX WARN: Removed duplicated region for block: B:1683:0x4d9c  */
    /* JADX WARN: Removed duplicated region for block: B:1687:0x4db7  */
    /* JADX WARN: Removed duplicated region for block: B:1691:0x4e11  */
    /* JADX WARN: Removed duplicated region for block: B:1695:0x4e1f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1699:0x4e51  */
    /* JADX WARN: Removed duplicated region for block: B:1703:0x4e5f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1707:0x4e90  */
    /* JADX WARN: Removed duplicated region for block: B:1711:0x4e9e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1715:0x4ed1  */
    /* JADX WARN: Removed duplicated region for block: B:1719:0x4edf A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1723:0x4f3f  */
    /* JADX WARN: Removed duplicated region for block: B:1727:0x4f60 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1731:0x4ff1  */
    /* JADX WARN: Removed duplicated region for block: B:1735:0x5015 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1739:0x5080  */
    /* JADX WARN: Removed duplicated region for block: B:1743:0x5090 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1747:0x50e1  */
    /* JADX WARN: Removed duplicated region for block: B:1751:0x50f7  */
    /* JADX WARN: Removed duplicated region for block: B:1787:0x5410  */
    /* JADX WARN: Removed duplicated region for block: B:1791:0x5421  */
    /* JADX WARN: Removed duplicated region for block: B:1795:0x5453  */
    /* JADX WARN: Removed duplicated region for block: B:1799:0x5465 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1803:0x5498  */
    /* JADX WARN: Removed duplicated region for block: B:1807:0x54aa A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1811:0x54dd  */
    /* JADX WARN: Removed duplicated region for block: B:1815:0x54ef A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1819:0x5522  */
    /* JADX WARN: Removed duplicated region for block: B:1823:0x5534 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1827:0x5567  */
    /* JADX WARN: Removed duplicated region for block: B:1831:0x5579 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1835:0x55e3  */
    /* JADX WARN: Removed duplicated region for block: B:1839:0x560d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1843:0x5690  */
    /* JADX WARN: Removed duplicated region for block: B:1847:0x56a2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1851:0x57db  */
    /* JADX WARN: Removed duplicated region for block: B:1854:0x57e7  */
    /* JADX WARN: Removed duplicated region for block: B:1855:0x57ed  */
    /* JADX WARN: Removed duplicated region for block: B:1858:0x5821  */
    /* JADX WARN: Removed duplicated region for block: B:1862:0x5837 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1866:0x58d0  */
    /* JADX WARN: Removed duplicated region for block: B:1870:0x58ed A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1874:0x5951  */
    /* JADX WARN: Removed duplicated region for block: B:1878:0x595f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1882:0x5990  */
    /* JADX WARN: Removed duplicated region for block: B:1886:0x59a1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1890:0x59e0  */
    /* JADX WARN: Removed duplicated region for block: B:1891:0x59ec  */
    /* JADX WARN: Removed duplicated region for block: B:1893:0x59f2  */
    /* JADX WARN: Removed duplicated region for block: B:1894:0x5a03  */
    /* JADX WARN: Removed duplicated region for block: B:1905:0x5a9d  */
    /* JADX WARN: Removed duplicated region for block: B:1909:0x5aab A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1913:0x5b20  */
    /* JADX WARN: Removed duplicated region for block: B:1914:0x5b23  */
    /* JADX WARN: Removed duplicated region for block: B:1917:0x5b67  */
    /* JADX WARN: Removed duplicated region for block: B:1921:0x5b75  */
    /* JADX WARN: Removed duplicated region for block: B:1932:0x5bcd  */
    /* JADX WARN: Removed duplicated region for block: B:1933:0x5bd8  */
    /* JADX WARN: Removed duplicated region for block: B:1936:0x5c31  */
    /* JADX WARN: Removed duplicated region for block: B:1940:0x5c56  */
    /* JADX WARN: Removed duplicated region for block: B:1944:0x5d00  */
    /* JADX WARN: Removed duplicated region for block: B:1948:0x5d27  */
    /* JADX WARN: Removed duplicated region for block: B:1952:0x5db7  */
    /* JADX WARN: Removed duplicated region for block: B:1956:0x5dcf  */
    /* JADX WARN: Removed duplicated region for block: B:1960:0x5e24  */
    /* JADX WARN: Removed duplicated region for block: B:1964:0x5e36 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1968:0x5e6b  */
    /* JADX WARN: Removed duplicated region for block: B:1972:0x5e7d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1976:0x5eb0  */
    /* JADX WARN: Removed duplicated region for block: B:1980:0x5ec2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1984:0x5ef7  */
    /* JADX WARN: Removed duplicated region for block: B:1988:0x5f09 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1992:0x5f6d  */
    /* JADX WARN: Removed duplicated region for block: B:1996:0x5f97 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2000:0x6031  */
    /* JADX WARN: Removed duplicated region for block: B:2004:0x6056  */
    /* JADX WARN: Removed duplicated region for block: B:2008:0x60ca  */
    /* JADX WARN: Removed duplicated region for block: B:2012:0x60de A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2016:0x6136  */
    /* JADX WARN: Removed duplicated region for block: B:2020:0x6157  */
    /* JADX WARN: Removed duplicated region for block: B:2026:0x621b  */
    /* JADX WARN: Removed duplicated region for block: B:2027:0x6220  */
    /* JADX WARN: Removed duplicated region for block: B:2030:0x625b  */
    /* JADX WARN: Removed duplicated region for block: B:2058:0x6553  */
    /* JADX WARN: Removed duplicated region for block: B:2067:0x65e8  */
    /* JADX WARN: Removed duplicated region for block: B:2081:0x66cd  */
    /* JADX WARN: Removed duplicated region for block: B:2084:0x66d9  */
    /* JADX WARN: Removed duplicated region for block: B:2085:0x66df  */
    /* JADX WARN: Removed duplicated region for block: B:2125:0x6859  */
    /* JADX WARN: Removed duplicated region for block: B:2131:0x6885  */
    /* JADX WARN: Removed duplicated region for block: B:2132:0x6888  */
    /* JADX WARN: Removed duplicated region for block: B:2135:0x6890  */
    /* JADX WARN: Removed duplicated region for block: B:2136:0x6895  */
    /* JADX WARN: Removed duplicated region for block: B:2139:0x68b5  */
    /* JADX WARN: Removed duplicated region for block: B:2148:0x69d0  */
    /* JADX WARN: Removed duplicated region for block: B:2151:0x6a2c  */
    /* JADX WARN: Removed duplicated region for block: B:2155:0x6a4c  */
    /* JADX WARN: Removed duplicated region for block: B:2159:0x6aa4  */
    /* JADX WARN: Removed duplicated region for block: B:2163:0x6ab8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2167:0x6b06  */
    /* JADX WARN: Removed duplicated region for block: B:2171:0x6b27 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2175:0x6b89  */
    /* JADX WARN: Removed duplicated region for block: B:2179:0x6ba5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2183:0x6c1d  */
    /* JADX WARN: Removed duplicated region for block: B:2187:0x6c3f  */
    /* JADX WARN: Removed duplicated region for block: B:2191:0x6cc5  */
    /* JADX WARN: Removed duplicated region for block: B:2195:0x6cdb  */
    /* JADX WARN: Removed duplicated region for block: B:2199:0x6d50  */
    /* JADX WARN: Removed duplicated region for block: B:2203:0x6d76 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2207:0x6df0  */
    /* JADX WARN: Removed duplicated region for block: B:2211:0x6e01 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2215:0x6e5d  */
    /* JADX WARN: Removed duplicated region for block: B:2219:0x6e78 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2223:0x6ebf  */
    /* JADX WARN: Removed duplicated region for block: B:2224:0x6ed7  */
    /* JADX WARN: Removed duplicated region for block: B:2227:0x6f04  */
    /* JADX WARN: Removed duplicated region for block: B:2228:0x6f13  */
    /* JADX WARN: Removed duplicated region for block: B:2230:0x6f40  */
    /* JADX WARN: Removed duplicated region for block: B:2236:0x6f99  */
    /* JADX WARN: Removed duplicated region for block: B:2239:0x6fa0  */
    /* JADX WARN: Removed duplicated region for block: B:2242:0x6fa7  */
    /* JADX WARN: Removed duplicated region for block: B:2245:0x6fd6  */
    /* JADX WARN: Removed duplicated region for block: B:2246:0x6fee  */
    /* JADX WARN: Removed duplicated region for block: B:2257:0x70d2  */
    /* JADX WARN: Removed duplicated region for block: B:2258:0x70e3  */
    /* JADX WARN: Removed duplicated region for block: B:2264:0x7129  */
    /* JADX WARN: Removed duplicated region for block: B:2275:0x71a8  */
    /* JADX WARN: Removed duplicated region for block: B:2276:0x71b7  */
    /* JADX WARN: Removed duplicated region for block: B:2279:0x71e8  */
    /* JADX WARN: Removed duplicated region for block: B:2283:0x71fd  */
    /* JADX WARN: Removed duplicated region for block: B:2287:0x7255  */
    /* JADX WARN: Removed duplicated region for block: B:2288:0x7264  */
    /* JADX WARN: Removed duplicated region for block: B:2291:0x72ff  */
    /* JADX WARN: Removed duplicated region for block: B:2295:0x73a6  */
    /* JADX WARN: Removed duplicated region for block: B:2299:0x73b9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2303:0x73f4  */
    /* JADX WARN: Removed duplicated region for block: B:2329:0x7576  */
    /* JADX WARN: Removed duplicated region for block: B:2330:0x7578  */
    /* JADX WARN: Removed duplicated region for block: B:2333:0x7584  */
    /* JADX WARN: Removed duplicated region for block: B:2334:0x7589  */
    /* JADX WARN: Removed duplicated region for block: B:2336:0x75d9  */
    /* JADX WARN: Removed duplicated region for block: B:2339:0x7672  */
    /* JADX WARN: Removed duplicated region for block: B:2358:0x779b  */
    /* JADX WARN: Removed duplicated region for block: B:2361:0x782c  */
    /* JADX WARN: Removed duplicated region for block: B:2362:0x7835  */
    /* JADX WARN: Removed duplicated region for block: B:2365:0x7866  */
    /* JADX WARN: Removed duplicated region for block: B:2370:0x79c5  */
    /* JADX WARN: Removed duplicated region for block: B:2373:0x79fc  */
    /* JADX WARN: Removed duplicated region for block: B:2386:0x410d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:2388:0x295e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:2391:0x28c6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:2392:0x28ba A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:2398:0x27d1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:2406:0x22d4 A[ADDED_TO_REGION, EDGE_INSN: B:2406:0x22d4->B:867:0x22d4 BREAK  A[LOOP:7: B:861:0x22b4->B:866:0x22c4], REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:2411:0x2315 A[ADDED_TO_REGION, EDGE_INSN: B:2411:0x2315->B:881:0x2315 BREAK  A[LOOP:9: B:876:0x22ff->B:880:0x230d], REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:2421:0x2369 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:2428:0x1791 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:393:0x0d62  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0d65  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0d8b  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x0d98  */
    /* JADX WARN: Removed duplicated region for block: B:401:0x0ddb  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0de9  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x0e39  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x0e44  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x0e68  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x0e82  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x0ead  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x0ec1  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0eda  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x0edf  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x0f5b  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x0f78  */
    /* JADX WARN: Removed duplicated region for block: B:433:0x0fde  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x0ff8  */
    /* JADX WARN: Removed duplicated region for block: B:437:0x101f  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x1039  */
    /* JADX WARN: Removed duplicated region for block: B:441:0x105b  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x1071  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x10c8  */
    /* JADX WARN: Removed duplicated region for block: B:450:0x10e2  */
    /* JADX WARN: Removed duplicated region for block: B:453:0x110b  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x1125  */
    /* JADX WARN: Removed duplicated region for block: B:457:0x1150  */
    /* JADX WARN: Removed duplicated region for block: B:461:0x116a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:465:0x11d3  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x11e4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:472:0x1203  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x121c  */
    /* JADX WARN: Removed duplicated region for block: B:537:0x14ae  */
    /* JADX WARN: Removed duplicated region for block: B:538:0x14bb  */
    /* JADX WARN: Removed duplicated region for block: B:541:0x14fd  */
    /* JADX WARN: Removed duplicated region for block: B:542:0x1511  */
    /* JADX WARN: Removed duplicated region for block: B:545:0x153c  */
    /* JADX WARN: Removed duplicated region for block: B:546:0x1547  */
    /* JADX WARN: Removed duplicated region for block: B:549:0x158a  */
    /* JADX WARN: Removed duplicated region for block: B:550:0x15a9  */
    /* JADX WARN: Removed duplicated region for block: B:553:0x15e0  */
    /* JADX WARN: Removed duplicated region for block: B:554:0x15f6  */
    /* JADX WARN: Removed duplicated region for block: B:557:0x1620  */
    /* JADX WARN: Removed duplicated region for block: B:558:0x1636  */
    /* JADX WARN: Removed duplicated region for block: B:576:0x16d2  */
    /* JADX WARN: Removed duplicated region for block: B:594:0x176d  */
    /* JADX WARN: Removed duplicated region for block: B:603:0x17c7  */
    /* JADX WARN: Removed duplicated region for block: B:604:0x17db  */
    /* JADX WARN: Removed duplicated region for block: B:615:0x1842  */
    /* JADX WARN: Removed duplicated region for block: B:622:0x1865  */
    /* JADX WARN: Removed duplicated region for block: B:625:0x186d  */
    /* JADX WARN: Removed duplicated region for block: B:626:0x1872  */
    /* JADX WARN: Removed duplicated region for block: B:645:0x18ee  */
    /* JADX WARN: Removed duplicated region for block: B:646:0x18f3  */
    /* JADX WARN: Removed duplicated region for block: B:672:0x19bb  */
    /* JADX WARN: Removed duplicated region for block: B:673:0x19d0  */
    /* JADX WARN: Removed duplicated region for block: B:677:0x1a03  */
    /* JADX WARN: Removed duplicated region for block: B:678:0x1a10  */
    /* JADX WARN: Removed duplicated region for block: B:681:0x1a5a  */
    /* JADX WARN: Removed duplicated region for block: B:682:0x1a6c  */
    /* JADX WARN: Removed duplicated region for block: B:692:0x1ae6  */
    /* JADX WARN: Removed duplicated region for block: B:693:0x1aeb  */
    /* JADX WARN: Removed duplicated region for block: B:695:0x1aef  */
    /* JADX WARN: Removed duplicated region for block: B:698:0x1aff  */
    /* JADX WARN: Removed duplicated region for block: B:699:0x1b04  */
    /* JADX WARN: Removed duplicated region for block: B:701:0x1b08  */
    /* JADX WARN: Removed duplicated region for block: B:705:0x1b61  */
    /* JADX WARN: Removed duplicated region for block: B:706:0x1b73  */
    /* JADX WARN: Removed duplicated region for block: B:717:0x1c3f  */
    /* JADX WARN: Removed duplicated region for block: B:718:0x1c41  */
    /* JADX WARN: Removed duplicated region for block: B:729:0x1d21  */
    /* JADX WARN: Removed duplicated region for block: B:733:0x1d3d  */
    /* JADX WARN: Removed duplicated region for block: B:737:0x1d9d  */
    /* JADX WARN: Removed duplicated region for block: B:740:0x1daa  */
    /* JADX WARN: Removed duplicated region for block: B:750:0x1e1b  */
    /* JADX WARN: Removed duplicated region for block: B:751:0x1e20  */
    /* JADX WARN: Removed duplicated region for block: B:771:0x1f36  */
    /* JADX WARN: Removed duplicated region for block: B:778:0x1f90  */
    /* JADX WARN: Removed duplicated region for block: B:779:0x1fa9  */
    /* JADX WARN: Removed duplicated region for block: B:782:0x1fe5  */
    /* JADX WARN: Removed duplicated region for block: B:786:0x2001  */
    /* JADX WARN: Removed duplicated region for block: B:790:0x2071  */
    /* JADX WARN: Removed duplicated region for block: B:794:0x2088 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:798:0x20ae  */
    /* JADX WARN: Removed duplicated region for block: B:805:0x2119  */
    /* JADX WARN: Removed duplicated region for block: B:810:0x212b A[PHI: r6
      0x212b: PHI (r6v129 java.lang.String) = (r6v128 java.lang.String), (r6v485 java.lang.String) binds: [B:804:0x2117, B:807:0x211f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:820:0x21b6  */
    /* JADX WARN: Removed duplicated region for block: B:821:0x21b9  */
    /* JADX WARN: Removed duplicated region for block: B:824:0x21c9  */
    /* JADX WARN: Removed duplicated region for block: B:825:0x21cc  */
    /* JADX WARN: Removed duplicated region for block: B:827:0x21d0  */
    /* JADX WARN: Removed duplicated region for block: B:832:0x21df  */
    /* JADX WARN: Removed duplicated region for block: B:834:0x21e2  */
    /* JADX WARN: Removed duplicated region for block: B:837:0x21f8  */
    /* JADX WARN: Removed duplicated region for block: B:841:0x2211  */
    /* JADX WARN: Removed duplicated region for block: B:844:0x221f  */
    /* JADX WARN: Removed duplicated region for block: B:848:0x2225  */
    /* JADX WARN: Removed duplicated region for block: B:850:0x2229  */
    /* JADX WARN: Removed duplicated region for block: B:851:0x222e  */
    /* JADX WARN: Removed duplicated region for block: B:855:0x226e A[LOOP:6: B:853:0x2268->B:855:0x226e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:858:0x2292  */
    /* JADX WARN: Removed duplicated region for block: B:859:0x229e  */
    /* JADX WARN: Removed duplicated region for block: B:863:0x22bc A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:869:0x22df  */
    /* JADX WARN: Removed duplicated region for block: B:878:0x2307  */
    /* JADX WARN: Removed duplicated region for block: B:883:0x2320  */
    /* JADX WARN: Removed duplicated region for block: B:892:0x2341  */
    /* JADX WARN: Removed duplicated region for block: B:898:0x2354  */
    /* JADX WARN: Removed duplicated region for block: B:902:0x2366  */
    /* JADX WARN: Removed duplicated region for block: B:907:0x243d  */
    /* JADX WARN: Removed duplicated region for block: B:911:0x245f  */
    /* JADX WARN: Removed duplicated region for block: B:915:0x251c  */
    /* JADX WARN: Removed duplicated region for block: B:919:0x252a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:923:0x2597  */
    /* JADX WARN: Removed duplicated region for block: B:927:0x25ab A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:931:0x25dc  */
    /* JADX WARN: Removed duplicated region for block: B:951:0x26c3  */
    /* JADX WARN: Removed duplicated region for block: B:955:0x26d3  */
    /* JADX WARN: Removed duplicated region for block: B:959:0x2729  */
    /* JADX WARN: Removed duplicated region for block: B:963:0x2741 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:966:0x274f  */
    /* JADX WARN: Removed duplicated region for block: B:967:0x2772  */
    /* JADX WARN: Removed duplicated region for block: B:971:0x2790  */
    /* JADX WARN: Removed duplicated region for block: B:983:0x27cb  */
    /* JADX WARN: Removed duplicated region for block: B:985:0x27ce  */
    /* JADX WARN: Removed duplicated region for block: B:990:0x281b  */
    /* JADX WARN: Removed duplicated region for block: B:994:0x2831  */
    /* JADX WARN: Removed duplicated region for block: B:999:0x2850  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LiveTvScreen(com.arflix.tv.ui.screens.tv.TvViewModel r290, com.arflix.tv.data.model.Profile r291, java.lang.String r292, java.lang.String r293, kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r294, kotlin.jvm.functions.Function0<kotlin.Unit> r295, kotlin.jvm.functions.Function0<kotlin.Unit> r296, kotlin.jvm.functions.Function0<kotlin.Unit> r297, kotlin.jvm.functions.Function0<kotlin.Unit> r298, kotlin.jvm.functions.Function0<kotlin.Unit> r299, kotlin.jvm.functions.Function2<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, kotlin.Unit> r300, kotlin.jvm.functions.Function0<kotlin.Unit> r301, kotlin.jvm.functions.Function0<kotlin.Unit> r302, androidx.compose.runtime.Composer r303, final int r304, final int r305, final int r306) {
        /*
            Method dump skipped, instruction units count: 31289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.LiveTvScreen(com.arflix.tv.ui.screens.tv.TvViewModel, com.arflix.tv.data.model.Profile, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TvUiState LiveTvScreen$lambda$8(State<TvUiState> state) {
        return (TvUiState) state.getValue();
    }

    private static final TvUiState LiveTvScreen$lambda$9(State<TvUiState> state) {
        return (TvUiState) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long LiveTvScreen$lambda$12(State<Long> state) {
        return ((Number) state.getValue()).longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$13$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("all", null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$14(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$16$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("all", null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$17(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState LiveTvScreen$lambda$24$0() {
        return SnapshotIntStateKt.mutableIntStateOf(144);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LiveTvScreen$lambda$25(MutableIntState $pagedLoadedLimit$delegate) {
        return $pagedLoadedLimit$delegate.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState LiveTvScreen$lambda$27$0() {
        return SnapshotIntStateKt.mutableIntStateOf(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LiveTvScreen$lambda$28(MutableIntState $lastKnownPagedTotal$delegate) {
        return $lastKnownPagedTotal$delegate.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Triple<String, String, Integer>> LiveTvScreen$lambda$31(MutableState<List<Triple<String, String, Integer>>> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$41$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(LiveTvFocusZone.CATEGORY_LIST, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LiveTvFocusZone LiveTvScreen$lambda$42(MutableState<LiveTvFocusZone> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LiveTvScreen$lambda$45(MutableIntState $topBarFocusIndex$delegate) {
        return $topBarFocusIndex$delegate.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState LiveTvScreen$lambda$44$0(boolean $hasProfile, int $maxTopBarIndex) {
        return SnapshotIntStateKt.mutableIntStateOf(RangesKt.coerceIn(AppTopBarKt.topBarSelectedIndex(SidebarItem.TV, $hasProfile), 0, $maxTopBarIndex));
    }

    private static final long LiveTvScreen$lambda$48(MutableLongState $lastGuideUserNavigationAt$delegate) {
        return $lastGuideUserNavigationAt$delegate.getLongValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$noteGuideUserNavigation(MutableLongState lastGuideUserNavigationAt$delegate) {
        lastGuideUserNavigationAt$delegate.setLongValue(System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$isGuideUserNavigating(MutableLongState lastGuideUserNavigationAt$delegate) {
        return System.currentTimeMillis() - LiveTvScreen$lambda$48(lastGuideUserNavigationAt$delegate) < 2500;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$52(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final IptvNowNext LiveTvScreen$guideForChannel(State<TvUiState> state, EnrichedChannel channel) {
        if (channel == null) {
            return null;
        }
        return LiveTvScreen$lambda$8(state).getSnapshot().getNowNext().get(channel.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$63$0(String $initialChannelId) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default($initialChannelId, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$64(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LiveTvScreen$lambda$67$0(final TvViewModel $viewModel, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$67$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $viewModel.setLiveTvPlaybackActive(false);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$68$0(String $resumeChannelId) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default($resumeChannelId, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$69(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$71$0(String $resumeChannelId) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default($resumeChannelId, null, 2, null);
    }

    private static final String LiveTvScreen$lambda$72(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$74$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$75(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$lambda$76(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IptvProgram LiveTvScreen$lambda$78(MutableState<IptvProgram> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long LiveTvScreen$lambda$81(MutableLongState $catchupPlaybackOffsetMs$delegate) {
        return $catchupPlaybackOffsetMs$delegate.getLongValue();
    }

    private static final void LiveTvScreen$commitFocusedChannel(Pair<String, String>[] pairArr, Job[] focusCommitJob, CoroutineScope focusCommitScope, MutableState<String> mutableState, Map<String, String> map, MutableState<String> mutableState2, MutableState<String> mutableState3, EnrichedChannel channel) {
        pairArr[0] = TuplesKt.to(channel.getId(), LiveTvScreen$lambda$14(mutableState));
        Job job = focusCommitJob[0];
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        focusCommitJob[0] = BuildersKt__Builders_commonKt.launch$default(focusCommitScope, null, null, new LiveTvScreenKt$LiveTvScreen$commitFocusedChannel$1(pairArr, map, mutableState2, mutableState3, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LiveTvScreen$lambda$85$0(final Job[] $focusCommitJob, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$85$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                Job job = $focusCommitJob[0];
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EnrichedChannel LiveTvScreen$lambda$89(MutableState<EnrichedChannel> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState LiveTvScreen$lambda$96$0() {
        return SnapshotIntStateKt.mutableIntStateOf(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LiveTvScreen$lambda$97(MutableIntState $guideWindowStart$delegate) {
        return $guideWindowStart$delegate.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LiveTvScreen$lambda$100(MutableIntState $guideWindowEnd$delegate) {
        return $guideWindowEnd$delegate.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState LiveTvScreen$lambda$99$0() {
        return SnapshotIntStateKt.mutableIntStateOf(48);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$setGuideWindow(List<EnrichedChannel> list, MutableIntState guideWindowStart$delegate, MutableIntState guideWindowEnd$delegate, Pair<Integer, Integer> pair) {
        int total = list.size();
        int start = RangesKt.coerceIn(pair.getFirst().intValue(), 0, RangesKt.coerceAtLeast(total, 0));
        int end = RangesKt.coerceIn(pair.getSecond().intValue(), start, total);
        guideWindowStart$delegate.setIntValue(start);
        guideWindowEnd$delegate.setIntValue(end);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$requestGuideWindowBefore(MutableIntState guideWindowStart$delegate, MutableIntState guideWindowEnd$delegate, List<EnrichedChannel> list) {
        LiveTvScreen$setGuideWindow(list, guideWindowStart$delegate, guideWindowEnd$delegate, expandGuideWindowBefore(LiveTvScreen$lambda$97(guideWindowStart$delegate), LiveTvScreen$lambda$100(guideWindowEnd$delegate)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$requestGuideWindowAfter(int selectedCategoryTotalCount, List<EnrichedChannel> list, MutableIntState guideWindowEnd$delegate, MutableIntState pagedLoadedLimit$delegate, MutableIntState guideWindowStart$delegate) {
        boolean hasMorePagedRows = selectedCategoryTotalCount > list.size();
        if (hasMorePagedRows && LiveTvScreen$lambda$100(guideWindowEnd$delegate) >= RangesKt.coerceAtLeast(list.size() - 48, 0)) {
            pagedLoadedLimit$delegate.setIntValue(RangesKt.coerceAtLeast(RangesKt.coerceAtMost(LiveTvScreen$lambda$25(pagedLoadedLimit$delegate) + 192, selectedCategoryTotalCount), 144));
        }
        int availableRows = Math.max(list.size(), RangesKt.coerceAtMost(LiveTvScreen$lambda$25(pagedLoadedLimit$delegate), selectedCategoryTotalCount));
        LiveTvScreen$setGuideWindow(list, guideWindowStart$delegate, guideWindowEnd$delegate, expandGuideWindowAfter(LiveTvScreen$lambda$97(guideWindowStart$delegate), LiveTvScreen$lambda$100(guideWindowEnd$delegate), availableRows));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$103$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$104(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final void LiveTvScreen$lambda$115$1$addChannel(List<String> list, EnrichedChannel channel) {
        String id;
        if (channel == null || (id = channel.getId()) == null || list.contains(id)) {
            return;
        }
        list.add(id);
    }

    private static final void LiveTvScreen$lambda$115$1$addGuideFirst(List<EnrichedChannel> list, List<String> list2, int index) {
        EnrichedChannel channel = (EnrichedChannel) CollectionsKt.getOrNull(list, index);
        if (channel != null && hasGuideIdentity(channel)) {
            LiveTvScreen$lambda$115$1$addChannel(list2, channel);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$115$1$0(Set $favSet, EnrichedChannel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return $favSet.contains(it.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$115$1$1(Map $visibleChannelsById, EnrichedChannel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return $visibleChannelsById.containsKey(it.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$120$0$0(EnrichedChannel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$124$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$125(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LiveTvScreen$lambda$126(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final int LiveTvScreen$lambda$128(MutableIntState $focusSelectedChannelSignal$delegate) {
        return $focusSelectedChannelSignal$delegate.getIntValue();
    }

    private static final int LiveTvScreen$lambda$131(MutableIntState $focusEpgSignal$delegate) {
        return $focusEpgSignal$delegate.getIntValue();
    }

    private static final int LiveTvScreen$lambda$134(MutableIntState $focusSearchCategorySignal$delegate) {
        return $focusSearchCategorySignal$delegate.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LiveTvScreen$lambda$137(MutableIntState $focusCategoryRailSignal$delegate) {
        return $focusCategoryRailSignal$delegate.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$139$0(String $initialStreamUrl) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf($initialStreamUrl != null), null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$140(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LiveTvScreen$lambda$141(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$143(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$lambda$144(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final EnrichedChannel LiveTvScreen$lambda$146(MutableState<EnrichedChannel> mutableState) {
        return mutableState.getValue();
    }

    private static final ProgramActionData LiveTvScreen$lambda$149(MutableState<ProgramActionData> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean LiveTvScreen$lambda$152(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$lambda$153(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LiveTvScreen$lambda$155$0(final Function1 $onFullscreenChanged, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$155$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $onFullscreenChanged.invoke(false);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LiveTvScreen$lambda$162(MutableState<Integer> mutableState) {
        return mutableState.getValue().intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$lambda$163(MutableState<Integer> mutableState, int i) {
        mutableState.setValue(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$165(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LiveTvScreen$lambda$166(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$168(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LiveTvScreen$lambda$169(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean LiveTvScreen$lambda$171(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LiveTvScreen$lambda$172(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final EnrichedChannel LiveTvScreen$lambda$174(MutableState<EnrichedChannel> mutableState) {
        return mutableState.getValue();
    }

    private static final List<String> LiveTvScreen$getAvailableCategoryIds(LiveCategoryTree tree) {
        List list = new ArrayList();
        for (LiveCategory liveCategory : tree.getTop()) {
            if (liveCategory.getCount() > 0 || Intrinsics.areEqual(liveCategory.getId(), "all")) {
                list.add(liveCategory.getId());
                if (Intrinsics.areEqual(liveCategory.getId(), "all")) {
                    for (LiveCategory liveCategory2 : liveCategory.getChildren()) {
                        if (liveCategory2.getCount() > 0) {
                            list.add(liveCategory2.getId());
                        }
                    }
                }
            }
        }
        for (LiveCategory liveCategory3 : tree.getGlobal().getCategories()) {
            if (liveCategory3.getCount() > 0) {
                list.add(liveCategory3.getId());
            }
        }
        for (LiveCategory liveCategory4 : tree.getCountries().getCategories()) {
            if (liveCategory4.getCount() > 0) {
                list.add(liveCategory4.getId());
                for (LiveCategory liveCategory5 : liveCategory4.getChildren()) {
                    if (liveCategory5.getCount() > 0) {
                        list.add(liveCategory5.getId());
                    }
                }
            }
        }
        for (LiveCategory liveCategory6 : tree.getAdult().getCategories()) {
            if (liveCategory6.getCount() > 0) {
                list.add(liveCategory6.getId());
            }
        }
        return CollectionsKt.distinct(list);
    }

    private static final void LiveTvScreen$cycleCategory(MutableState<EnrichedChannels> mutableState, MutableState<String> mutableState2, boolean forward) {
        int nextIndex;
        LiveCategoryTree tree = mutableState.getValue().getTree();
        List<String> listLiveTvScreen$getAvailableCategoryIds = LiveTvScreen$getAvailableCategoryIds(tree);
        if (listLiveTvScreen$getAvailableCategoryIds.isEmpty()) {
            return;
        }
        int currentIndex = listLiveTvScreen$getAvailableCategoryIds.indexOf(LiveTvScreen$lambda$14(mutableState2));
        if (forward) {
            nextIndex = (currentIndex + 1) % listLiveTvScreen$getAvailableCategoryIds.size();
        } else {
            nextIndex = ((currentIndex - 1) + listLiveTvScreen$getAvailableCategoryIds.size()) % listLiveTvScreen$getAvailableCategoryIds.size();
        }
        String str = (String) CollectionsKt.getOrNull(listLiveTvScreen$getAvailableCategoryIds, nextIndex);
        if (str == null) {
            str = "all";
        }
        mutableState2.setValue(str);
    }

    private static final void LiveTvScreen$openFullscreenGuide(EnrichedChannel playingChannel, TvViewModel $viewModel, MutableState<EnrichedChannel> mutableState, MutableState<String> mutableState2, MutableState<Boolean> mutableState3, MutableState<Integer> mutableState4) {
        mutableState.setValue(playingChannel);
        $viewModel.refreshCatchupHistoryForChannel(LiveTvScreen$lambda$64(mutableState2));
        LiveTvScreen$lambda$144(mutableState3, true);
        LiveTvScreen$lambda$163(mutableState4, LiveTvScreen$lambda$162(mutableState4) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LiveTvScreen$lambda$180$0(boolean $isTouchDevice, final Activity $activity, MutableState $isFullScreen$delegate, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        if ($isTouchDevice && LiveTvScreen$lambda$140($isFullScreen$delegate)) {
            final Integer previousOrientation = $activity != null ? Integer.valueOf($activity.getRequestedOrientation()) : null;
            if ($activity != null) {
                $activity.setRequestedOrientation(6);
            }
            final Window window = $activity != null ? $activity.getWindow() : null;
            if (window != null) {
                WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(window, window.getDecorView());
                controller.setSystemBarsBehavior(2);
                controller.hide(WindowInsetsCompat.Type.systemBars());
            }
            return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$180$0$$inlined$onDispose$2
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                    if (previousOrientation != null) {
                        $activity.setRequestedOrientation(previousOrientation.intValue());
                    }
                    if (window == null) {
                        return;
                    }
                    new WindowInsetsControllerCompat(window, window.getDecorView()).show(WindowInsetsCompat.Type.systemBars());
                }
            };
        }
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$180$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
            }
        };
    }

    private static final void LiveTvScreen$zap(List<EnrichedChannel> list, MutableState<EnrichedChannels> mutableState, Map<String, ? extends List<EnrichedChannel>> map, Map<String, String> map2, MutableLongState lastGuideUserNavigationAt$delegate, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<IptvProgram> mutableState6, MutableLongState catchupPlaybackOffsetMs$delegate, MutableState<Boolean> mutableState7, int delta) {
        LiveTvScreen$noteGuideUserNavigation(lastGuideUserNavigationAt$delegate);
        if (list.isEmpty()) {
            return;
        }
        String currentDisplayId = LiveTvEnhancementsKt.displayChannelIdFor(LiveTvScreen$lambda$64(mutableState2), mutableState.getValue().getIndex().getById(), map);
        int currentIdx = -1;
        if (currentDisplayId != null) {
            int i = 0;
            Iterator<EnrichedChannel> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (Intrinsics.areEqual(((EnrichedChannel) it.next()).getId(), currentDisplayId)) {
                    currentIdx = i;
                    break;
                }
                i++;
            }
        }
        int start = currentIdx >= 0 ? currentIdx : 0;
        int size = list.size();
        int nextIdx = (((start + delta) % size) + size) % size;
        mutableState2.setValue(list.get(nextIdx).getId());
        mutableState3.setValue(list.get(nextIdx).getId());
        mutableState4.setValue(list.get(nextIdx).getId());
        map2.put(LiveTvScreen$lambda$14(mutableState5), list.get(nextIdx).getId());
        mutableState6.setValue(null);
        catchupPlaybackOffsetMs$delegate.setLongValue(0L);
        LiveTvScreen$lambda$144(mutableState7, false);
    }

    private static final void LiveTvScreen$focusPlaylistSearch(MutableLongState lastGuideUserNavigationAt$delegate, MutableState<LiveTvFocusZone> mutableState, MutableIntState focusSearchCategorySignal$delegate, FocusRequester sidebarFocus) {
        LiveTvScreen$noteGuideUserNavigation(lastGuideUserNavigationAt$delegate);
        mutableState.setValue(LiveTvFocusZone.CATEGORY_LIST);
        focusSearchCategorySignal$delegate.setIntValue(LiveTvScreen$lambda$134(focusSearchCategorySignal$delegate) + 1);
        try {
            Result.Companion companion = Result.INSTANCE;
            sidebarFocus.requestFocus();
            Result.m10018constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m10018constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$focusProviderSwitcher(List<TvProviderFilter> list, MutableLongState lastGuideUserNavigationAt$delegate, MutableState<LiveTvFocusZone> mutableState, MutableIntState focusSearchCategorySignal$delegate, FocusRequester sidebarFocus, FocusRequester providerFocus) {
        LiveTvScreen$noteGuideUserNavigation(lastGuideUserNavigationAt$delegate);
        if (list.size() <= 1) {
            LiveTvScreen$focusPlaylistSearch(lastGuideUserNavigationAt$delegate, mutableState, focusSearchCategorySignal$delegate, sidebarFocus);
            return;
        }
        mutableState.setValue(LiveTvFocusZone.PROVIDER_SWITCHER);
        try {
            Result.Companion companion = Result.INSTANCE;
            providerFocus.requestFocus();
            Result.m10018constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m10018constructorimpl(ResultKt.createFailure(th));
        }
    }

    static /* synthetic */ void LiveTvScreen$focusChannelList$default(MutableState mutableState, MutableState mutableState2, MutableLongState mutableLongState, Map map, Map map2, List list, MutableState mutableState3, MutableState mutableState4, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState5, MutableIntState mutableIntState3, FocusRequester focusRequester, String str, int i, Object obj) {
        String str2;
        if ((i & 8192) != 0) {
            String strLiveTvScreen$lambda$69 = LiveTvScreen$lambda$69(mutableState);
            if (strLiveTvScreen$lambda$69 == null) {
                strLiveTvScreen$lambda$69 = LiveTvScreen$lambda$64(mutableState2);
            }
            str2 = strLiveTvScreen$lambda$69;
        } else {
            str2 = str;
        }
        LiveTvScreen$focusChannelList(mutableState, mutableState2, mutableLongState, map, map2, list, mutableState3, mutableState4, mutableIntState, mutableIntState2, mutableState5, mutableIntState3, focusRequester, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LiveTvScreen$focusChannelList(androidx.compose.runtime.MutableState<java.lang.String> r8, androidx.compose.runtime.MutableState<java.lang.String> r9, androidx.compose.runtime.MutableLongState r10, java.util.Map<java.lang.String, java.lang.String> r11, java.util.Map<java.lang.String, java.lang.Integer> r12, java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel> r13, androidx.compose.runtime.MutableState<java.lang.String> r14, androidx.compose.runtime.MutableState<java.lang.String> r15, androidx.compose.runtime.MutableIntState r16, androidx.compose.runtime.MutableIntState r17, androidx.compose.runtime.MutableState<com.arflix.tv.ui.screens.tv.live.LiveTvFocusZone> r18, androidx.compose.runtime.MutableIntState r19, androidx.compose.ui.focus.FocusRequester r20, java.lang.String r21) {
        /*
            LiveTvScreen$noteGuideUserNavigation(r10)
            r0 = 1
            if (r21 == 0) goto L4c
            r1 = r21
            r2 = 0
            LiveTvScreen$lambda$70(r8, r1)
            LiveTvScreen$lambda$73(r14, r1)
            java.lang.String r3 = LiveTvScreen$lambda$14(r15)
            r11.put(r3, r1)
            java.lang.Object r3 = r12.get(r1)
            java.lang.Integer r3 = (java.lang.Integer) r3
            if (r3 == 0) goto L46
            int r4 = LiveTvScreen$lambda$97(r16)
            int r5 = LiveTvScreen$lambda$100(r17)
            int r6 = r3.intValue()
            r7 = 0
            if (r4 > r6) goto L30
            if (r6 >= r5) goto L30
            r7 = r0
        L30:
            if (r7 != 0) goto L46
            int r4 = r3.intValue()
            int r5 = r13.size()
            kotlin.Pair r4 = guideWindowAround(r4, r5)
            r5 = r16
            r6 = r17
            LiveTvScreen$setGuideWindow(r13, r5, r6, r4)
            goto L4a
        L46:
            r5 = r16
            r6 = r17
        L4a:
            goto L50
        L4c:
            r5 = r16
            r6 = r17
        L50:
            com.arflix.tv.ui.screens.tv.live.LiveTvFocusZone r1 = com.arflix.tv.ui.screens.tv.live.LiveTvFocusZone.CHANNEL_LIST
            r2 = r18
            LiveTvScreen$lambda$43(r2, r1)
            int r1 = LiveTvScreen$lambda$128(r19)
            int r1 = r1 + r0
            r3 = r19
            LiveTvScreen$lambda$129(r3, r1)
            kotlin.Result$Companion r0 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L6e
            r0 = 0
            r20.requestFocus()     // Catch: java.lang.Throwable -> L6e
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L6e
            kotlin.Result.m10018constructorimpl(r0)     // Catch: java.lang.Throwable -> L6e
            goto L78
        L6e:
            r0 = move-exception
            kotlin.Result$Companion r1 = kotlin.Result.INSTANCE
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            kotlin.Result.m10018constructorimpl(r0)
        L78:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.LiveTvScreen$focusChannelList(androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableLongState, java.util.Map, java.util.Map, java.util.List, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableIntState, androidx.compose.ui.focus.FocusRequester, java.lang.String):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void LiveTvScreen$focusEpg(java.util.Map<java.lang.String, java.lang.String> r8, java.util.Map<java.lang.String, java.lang.Integer> r9, java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel> r10, androidx.compose.runtime.MutableLongState r11, androidx.compose.runtime.MutableState<java.lang.String> r12, androidx.compose.runtime.MutableState<java.lang.String> r13, androidx.compose.runtime.MutableState<java.lang.String> r14, androidx.compose.runtime.MutableIntState r15, androidx.compose.runtime.MutableIntState r16, androidx.compose.runtime.MutableState<com.arflix.tv.ui.screens.tv.live.LiveTvFocusZone> r17, androidx.compose.runtime.MutableIntState r18, androidx.compose.ui.focus.FocusRequester r19, java.lang.String r20) {
        /*
            r1 = r20
            LiveTvScreen$noteGuideUserNavigation(r11)
            LiveTvScreen$lambda$70(r12, r1)
            LiveTvScreen$lambda$73(r13, r1)
            java.lang.String r0 = LiveTvScreen$lambda$14(r14)
            r8.put(r0, r1)
            java.lang.Object r0 = r9.get(r1)
            r2 = r0
            java.lang.Integer r2 = (java.lang.Integer) r2
            r0 = 1
            if (r2 == 0) goto L43
            int r3 = LiveTvScreen$lambda$97(r15)
            int r4 = LiveTvScreen$lambda$100(r16)
            int r5 = r2.intValue()
            r6 = 0
            if (r3 > r5) goto L2e
            if (r5 >= r4) goto L2e
            r6 = r0
        L2e:
            if (r6 != 0) goto L43
            int r3 = r2.intValue()
            int r4 = r10.size()
            kotlin.Pair r3 = guideWindowAround(r3, r4)
            r4 = r15
            r5 = r16
            LiveTvScreen$setGuideWindow(r10, r15, r5, r3)
            goto L46
        L43:
            r4 = r15
            r5 = r16
        L46:
            com.arflix.tv.ui.screens.tv.live.LiveTvFocusZone r3 = com.arflix.tv.ui.screens.tv.live.LiveTvFocusZone.EPG
            r6 = r17
            LiveTvScreen$lambda$43(r6, r3)
            int r3 = LiveTvScreen$lambda$131(r18)
            int r3 = r3 + r0
            r7 = r18
            LiveTvScreen$lambda$132(r7, r3)
            kotlin.Result$Companion r0 = kotlin.Result.INSTANCE     // Catch: java.lang.Throwable -> L63
            r0 = 0
            r19.requestFocus()     // Catch: java.lang.Throwable -> L63
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L63
            kotlin.Result.m10018constructorimpl(r0)     // Catch: java.lang.Throwable -> L63
            goto L6d
        L63:
            r0 = move-exception
            kotlin.Result$Companion r3 = kotlin.Result.INSTANCE
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            kotlin.Result.m10018constructorimpl(r0)
        L6d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.LiveTvScreen$focusEpg(java.util.Map, java.util.Map, java.util.List, androidx.compose.runtime.MutableLongState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableIntState, androidx.compose.ui.focus.FocusRequester, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$exitFullScreenPlayback(CoroutineScope focusCommitScope, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<Boolean> mutableState3, MutableState<Boolean> mutableState4, MutableState<Integer> mutableState5, MutableLongState lastGuideUserNavigationAt$delegate, Map<String, String> map, Map<String, Integer> map2, List<EnrichedChannel> list, MutableState<String> mutableState6, MutableState<String> mutableState7, MutableIntState guideWindowStart$delegate, MutableIntState guideWindowEnd$delegate, MutableState<LiveTvFocusZone> mutableState8, MutableIntState focusSelectedChannelSignal$delegate, FocusRequester epgFocus) {
        String strLiveTvScreen$lambda$64 = LiveTvScreen$lambda$64(mutableState);
        if (strLiveTvScreen$lambda$64 == null) {
            strLiveTvScreen$lambda$64 = LiveTvScreen$lambda$69(mutableState2);
        }
        String returnFocusChannelId = strLiveTvScreen$lambda$64;
        LiveTvScreen$lambda$144(mutableState3, false);
        LiveTvScreen$lambda$141(mutableState4, false);
        LiveTvScreen$lambda$163(mutableState5, LiveTvScreen$lambda$162(mutableState5) + 1);
        BuildersKt__Builders_commonKt.launch$default(focusCommitScope, null, null, new LiveTvScreenKt$LiveTvScreen$exitFullScreenPlayback$1(returnFocusChannelId, mutableState2, mutableState, lastGuideUserNavigationAt$delegate, map, map2, list, mutableState6, mutableState7, guideWindowStart$delegate, guideWindowEnd$delegate, mutableState8, focusSelectedChannelSignal$delegate, epgFocus, null), 3, null);
    }

    private static final void LiveTvScreen$selectChannel(Map<String, String> map, MutableState<EnrichedChannels> mutableState, Map<String, ? extends List<EnrichedChannel>> map2, MutableLongState lastGuideUserNavigationAt$delegate, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<Boolean> mutableState6, MutableState<IptvProgram> mutableState7, MutableLongState catchupPlaybackOffsetMs$delegate, MutableState<Integer> mutableState8, MutableState<Boolean> mutableState9, EnrichedChannel channel) {
        LiveTvScreen$noteGuideUserNavigation(lastGuideUserNavigationAt$delegate);
        mutableState2.setValue(channel.getId());
        mutableState3.setValue(channel.getId());
        map.put(LiveTvScreen$lambda$14(mutableState4), channel.getId());
        String currentDisplayId = LiveTvEnhancementsKt.displayChannelIdFor(LiveTvScreen$lambda$64(mutableState5), mutableState.getValue().getIndex().getById(), map2);
        boolean isSamePlayingChannel = Intrinsics.areEqual(channel.getId(), LiveTvScreen$lambda$64(mutableState5)) || Intrinsics.areEqual(channel.getId(), currentDisplayId);
        if (!isSamePlayingChannel || LiveTvScreen$lambda$140(mutableState6)) {
            mutableState5.setValue(channel.getId());
            mutableState7.setValue(null);
            catchupPlaybackOffsetMs$delegate.setLongValue(0L);
            LiveTvScreen$lambda$144(mutableState9, false);
            return;
        }
        mutableState7.setValue(null);
        catchupPlaybackOffsetMs$delegate.setLongValue(0L);
        LiveTvScreen$lambda$141(mutableState6, true);
        LiveTvScreen$lambda$163(mutableState8, LiveTvScreen$lambda$162(mutableState8) + 1);
    }

    private static final void LiveTvScreen$openVariantPicker(Map<String, ? extends List<EnrichedChannel>> map, MutableLongState lastGuideUserNavigationAt$delegate, MutableState<EnrichedChannel> mutableState, EnrichedChannel channel) {
        LiveTvScreen$noteGuideUserNavigation(lastGuideUserNavigationAt$delegate);
        if (LiveTvEnhancementsKt.variantCountFor(channel, map) <= 1) {
            return;
        }
        mutableState.setValue(channel);
    }

    private static final void LiveTvScreen$playVariant(MutableState<EnrichedChannels> mutableState, Map<String, ? extends List<EnrichedChannel>> map, Map<String, String> map2, MutableLongState lastGuideUserNavigationAt$delegate, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<IptvProgram> mutableState6, MutableLongState catchupPlaybackOffsetMs$delegate, MutableState<Boolean> mutableState7, Map<String, Integer> map3, List<EnrichedChannel> list, MutableIntState guideWindowStart$delegate, MutableIntState guideWindowEnd$delegate, MutableState<LiveTvFocusZone> mutableState8, MutableIntState focusSelectedChannelSignal$delegate, FocusRequester epgFocus, EnrichedChannel channel) {
        LiveTvScreen$noteGuideUserNavigation(lastGuideUserNavigationAt$delegate);
        String displayId = LiveTvEnhancementsKt.displayChannelIdFor(channel.getId(), mutableState.getValue().getIndex().getById(), map);
        if (displayId == null) {
            displayId = channel.getId();
        }
        mutableState2.setValue(channel.getId());
        mutableState3.setValue(displayId);
        mutableState4.setValue(displayId);
        map2.put(LiveTvScreen$lambda$14(mutableState5), displayId);
        mutableState6.setValue(null);
        catchupPlaybackOffsetMs$delegate.setLongValue(0L);
        LiveTvScreen$lambda$144(mutableState7, false);
        LiveTvScreen$focusChannelList(mutableState3, mutableState2, lastGuideUserNavigationAt$delegate, map2, map3, list, mutableState4, mutableState5, guideWindowStart$delegate, guideWindowEnd$delegate, mutableState8, focusSelectedChannelSignal$delegate, epgFocus, displayId);
    }

    private static final void LiveTvScreen$playProgramInMini(List<EnrichedChannel> list, Map<String, String> map, MutableLongState lastGuideUserNavigationAt$delegate, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<IptvProgram> mutableState5, MutableLongState catchupPlaybackOffsetMs$delegate, MutableState<Boolean> mutableState6, Map<String, Integer> map2, List<EnrichedChannel> list2, MutableIntState guideWindowStart$delegate, MutableIntState guideWindowEnd$delegate, MutableState<LiveTvFocusZone> mutableState7, MutableIntState focusSelectedChannelSignal$delegate, FocusRequester epgFocus, EnrichedChannel channel, IptvProgram program) {
        EnrichedChannel enrichedChannel;
        EnrichedChannel playbackChannel;
        LiveTvScreen$noteGuideUserNavigation(lastGuideUserNavigationAt$delegate);
        if (program != null) {
            enrichedChannel = channel;
            playbackChannel = catchupPlaybackVariant(enrichedChannel, list);
        } else {
            enrichedChannel = channel;
            playbackChannel = enrichedChannel;
        }
        if (program != null && !Intrinsics.areEqual(playbackChannel.getId(), enrichedChannel.getId())) {
            System.err.println("[IPTV-Catchup] using archive variant source=" + enrichedChannel.getId() + " playback=" + playbackChannel.getId() + " quality=" + playbackChannel.getQuality().getLabel() + " days=" + playbackChannel.getCatchupDays());
        }
        mutableState.setValue(playbackChannel.getId());
        mutableState2.setValue(playbackChannel.getId());
        map.put(LiveTvScreen$lambda$14(mutableState3), playbackChannel.getId());
        mutableState4.setValue(playbackChannel.getId());
        mutableState5.setValue(program);
        catchupPlaybackOffsetMs$delegate.setLongValue(0L);
        LiveTvScreen$lambda$144(mutableState6, false);
        LiveTvScreen$focusChannelList(mutableState, mutableState4, lastGuideUserNavigationAt$delegate, map, map2, list2, mutableState2, mutableState3, guideWindowStart$delegate, guideWindowEnd$delegate, mutableState7, focusSelectedChannelSignal$delegate, epgFocus, playbackChannel.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$playProgramInFullscreen(EnrichedChannel playingChannel, List<EnrichedChannel> list, MutableState<IptvProgram> mutableState, MutableLongState catchupPlaybackOffsetMs$delegate, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<Boolean> mutableState5, MutableState<Boolean> mutableState6, MutableState<Integer> mutableState7, IptvProgram program, EnrichedChannel targetChannel) {
        EnrichedChannel channel = targetChannel == null ? playingChannel : targetChannel;
        if (!Intrinsics.areEqual(program, LiveTvScreen$lambda$78(mutableState))) {
            catchupPlaybackOffsetMs$delegate.setLongValue(0L);
        }
        if (channel != null) {
            EnrichedChannel playbackChannel = catchupPlaybackVariant(channel, list);
            if (!Intrinsics.areEqual(playbackChannel.getId(), LiveTvScreen$lambda$64(mutableState2))) {
                System.err.println("[IPTV-Catchup] using fullscreen archive variant source=" + channel.getId() + " playback=" + playbackChannel.getId() + " quality=" + playbackChannel.getQuality().getLabel() + " days=" + playbackChannel.getCatchupDays());
                mutableState2.setValue(playbackChannel.getId());
                mutableState3.setValue(playbackChannel.getId());
                mutableState4.setValue(playbackChannel.getId());
            }
        }
        mutableState.setValue(program);
        LiveTvScreen$lambda$144(mutableState5, false);
        LiveTvScreen$lambda$141(mutableState6, true);
        LiveTvScreen$lambda$163(mutableState7, LiveTvScreen$lambda$162(mutableState7) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$189(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final long LiveTvScreen$lambda$192(MutableState<Long> mutableState) {
        return mutableState.getValue().longValue();
    }

    private static final void LiveTvScreen$lambda$193(MutableState<Long> mutableState, long j) {
        mutableState.setValue(Long.valueOf(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$tuneChannelNumber(Map<String, String> map, MutableLongState lastGuideUserNavigationAt$delegate, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<IptvProgram> mutableState4, MutableLongState catchupPlaybackOffsetMs$delegate, MutableState<Boolean> mutableState5, MutableState<String> mutableState6, Map<String, Integer> map2, List<EnrichedChannel> list, MutableIntState guideWindowStart$delegate, MutableIntState guideWindowEnd$delegate, MutableState<LiveTvFocusZone> mutableState7, MutableIntState focusSelectedChannelSignal$delegate, FocusRequester epgFocus, MutableState<Integer> mutableState8, EnrichedChannel channel) {
        LiveTvScreen$noteGuideUserNavigation(lastGuideUserNavigationAt$delegate);
        mutableState.setValue(channel.getId());
        mutableState2.setValue(channel.getId());
        mutableState3.setValue(channel.getId());
        mutableState4.setValue(null);
        catchupPlaybackOffsetMs$delegate.setLongValue(0L);
        LiveTvScreen$lambda$144(mutableState5, false);
        map.put(LiveTvScreen$lambda$14(mutableState6), channel.getId());
        LiveTvScreen$focusChannelList(mutableState2, mutableState, lastGuideUserNavigationAt$delegate, map, map2, list, mutableState3, mutableState6, guideWindowStart$delegate, guideWindowEnd$delegate, mutableState7, focusSelectedChannelSignal$delegate, epgFocus, channel.getId());
        LiveTvScreen$lambda$163(mutableState8, LiveTvScreen$lambda$162(mutableState8) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$handleChannelNumberDigit(MutableState<EnrichedChannels> mutableState, MutableState<Long> mutableState2, MutableState<String> mutableState3, Map<String, String> map, MutableLongState lastGuideUserNavigationAt$delegate, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<String> mutableState6, MutableState<IptvProgram> mutableState7, MutableLongState catchupPlaybackOffsetMs$delegate, MutableState<Boolean> mutableState8, MutableState<String> mutableState9, Map<String, Integer> map2, List<EnrichedChannel> list, MutableIntState guideWindowStart$delegate, MutableIntState guideWindowEnd$delegate, MutableState<LiveTvFocusZone> mutableState10, MutableIntState focusSelectedChannelSignal$delegate, FocusRequester epgFocus, MutableState<Integer> mutableState11, int digit) {
        Object next;
        long now = System.currentTimeMillis();
        String prefix = now - LiveTvScreen$lambda$192(mutableState2) > 1500 ? "" : LiveTvScreen$lambda$189(mutableState3);
        mutableState3.setValue(StringsKt.takeLast(prefix + digit, 4));
        LiveTvScreen$lambda$193(mutableState2, now);
        Iterator it = mutableState.getValue().getAll().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(String.valueOf(((EnrichedChannel) next).getNumber()), LiveTvScreen$lambda$189(mutableState3))) {
                break;
            }
        }
        EnrichedChannel enrichedChannel = (EnrichedChannel) next;
        if (enrichedChannel == null) {
            return true;
        }
        LiveTvScreen$tuneChannelNumber(map, lastGuideUserNavigationAt$delegate, mutableState4, mutableState5, mutableState6, mutableState7, catchupPlaybackOffsetMs$delegate, mutableState8, mutableState9, map2, list, guideWindowStart$delegate, guideWindowEnd$delegate, mutableState10, focusSelectedChannelSignal$delegate, epgFocus, mutableState11, enrichedChannel);
        mutableState3.setValue("");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LiveTvScreen$lambda$203$0(final ExoPlayer $exoPlayer, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$203$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $exoPlayer.release();
            }
        };
    }

    private static final long LiveTvScreen$lambda$205(MutableLongState $playerPositionMs$delegate) {
        return $playerPositionMs$delegate.getLongValue();
    }

    private static final long LiveTvScreen$lambda$208(MutableLongState $playerDurationMs$delegate) {
        return $playerDurationMs$delegate.getLongValue();
    }

    private static final boolean LiveTvScreen$lambda$211(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$lambda$212(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean LiveTvScreen$lambda$214(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$lambda$215(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean LiveTvScreen$lambda$217(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$lambda$218(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LiveTvScreen$lambda$220$0(final LifecycleOwner $lifecycleOwner, final ExoPlayer $exoPlayer, final TvViewModel $viewModel, final MutableState $playingChannelId$delegate, final State $currentUiState$delegate, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        final LifecycleEventObserver obs = new LifecycleEventObserver() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$$ExternalSyntheticLambda21
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                LiveTvScreenKt.LiveTvScreen$lambda$220$0$0($exoPlayer, $viewModel, $playingChannelId$delegate, $currentUiState$delegate, lifecycleOwner, event);
            }
        };
        $lifecycleOwner.getLifecycle().addObserver(obs);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$220$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $lifecycleOwner.getLifecycle().removeObserver(obs);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$lambda$220$0$0(ExoPlayer $exoPlayer, TvViewModel $viewModel, MutableState $playingChannelId$delegate, State $currentUiState$delegate, LifecycleOwner lifecycleOwner, Lifecycle.Event ev) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<unused var>");
        Intrinsics.checkNotNullParameter(ev, "ev");
        switch (WhenMappings.$EnumSwitchMapping$1[ev.ordinal()]) {
            case 1:
                $exoPlayer.pause();
                break;
            case 2:
                if (LiveTvScreen$lambda$64($playingChannelId$delegate) != null) {
                    $exoPlayer.play();
                }
                if (LiveTvScreen$lambda$9($currentUiState$delegate).isConfigured() && !LiveTvScreen$lambda$9($currentUiState$delegate).getSnapshot().getChannels().isEmpty() && $viewModel.getIptvRepository().cachedEpgAgeMs() > 21600000) {
                    $viewModel.refresh(false, false, false);
                    break;
                }
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$222(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$225(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LiveTvScreen$lambda$226(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<String, String> LiveTvScreen$lambda$228(MutableState<Map<String, String>> mutableState) {
        return mutableState.getValue();
    }

    private static final long LiveTvScreen$lambda$231(MutableLongState $lastPreparedCatchupOffsetMs$delegate) {
        return $lastPreparedCatchupOffsetMs$delegate.getLongValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LiveTvScreen$lambda$234(MutableIntState $playerRetryCount$delegate) {
        return $playerRetryCount$delegate.getIntValue();
    }

    private static final PlaybackDiagnostic LiveTvScreen$lambda$237(MutableState<PlaybackDiagnostic> mutableState) {
        return mutableState.getValue();
    }

    static /* synthetic */ void LiveTvScreen$prepareStream$default(Map map, OkHttpDataSource.Factory factory, long j, ExoPlayer exoPlayer, Context context, EnrichedChannel enrichedChannel, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableLongState mutableLongState, MutableState mutableState5, MutableIntState mutableIntState, MutableState mutableState6, MutableLongState mutableLongState2, String str, boolean z, Map map2, boolean z2, long j2, DrmInfo drmInfo, boolean z3, int i, Object obj) {
        LiveTvScreen$prepareStream(map, factory, j, exoPlayer, context, enrichedChannel, mutableState, mutableState2, mutableState3, mutableState4, mutableLongState, mutableState5, mutableIntState, mutableState6, mutableLongState2, str, z, map2, z2, (i & 524288) != 0 ? 0L : j2, (i & 1048576) != 0 ? null : drmInfo, (i & 2097152) != 0 ? false : z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LiveTvScreen$prepareStream(java.util.Map<java.lang.String, java.lang.String> r22, androidx.media3.datasource.okhttp.OkHttpDataSource.Factory r23, long r24, androidx.media3.exoplayer.ExoPlayer r26, android.content.Context r27, com.arflix.tv.ui.screens.tv.live.EnrichedChannel r28, androidx.compose.runtime.MutableState<java.lang.String> r29, androidx.compose.runtime.MutableState<java.lang.Boolean> r30, androidx.compose.runtime.MutableState<java.util.Map<java.lang.String, java.lang.String>> r31, androidx.compose.runtime.MutableState<com.arflix.tv.data.model.IptvProgram> r32, androidx.compose.runtime.MutableLongState r33, androidx.compose.runtime.MutableState<java.lang.Boolean> r34, androidx.compose.runtime.MutableIntState r35, androidx.compose.runtime.MutableState<com.arflix.tv.ui.screens.tv.live.PlaybackDiagnostic> r36, androidx.compose.runtime.MutableLongState r37, java.lang.String r38, boolean r39, java.util.Map<java.lang.String, java.lang.String> r40, boolean r41, long r42, com.arflix.tv.data.model.DrmInfo r44, boolean r45) {
        /*
            Method dump skipped, instruction units count: 518
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.LiveTvScreen$prepareStream(java.util.Map, androidx.media3.datasource.okhttp.OkHttpDataSource$Factory, long, androidx.media3.exoplayer.ExoPlayer, android.content.Context, com.arflix.tv.ui.screens.tv.live.EnrichedChannel, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableLongState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableLongState, java.lang.String, boolean, java.util.Map, boolean, long, com.arflix.tv.data.model.DrmInfo, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$toggleCatchupPlayback(ExoPlayer exoPlayer, MutableState<IptvProgram> mutableState, MutableState<Boolean> mutableState2, MutableState<Integer> mutableState3) {
        if (LiveTvScreen$lambda$78(mutableState) == null) {
            return;
        }
        if (exoPlayer.isPlaying()) {
            exoPlayer.pause();
            LiveTvScreen$lambda$215(mutableState2, false);
            System.err.println("[IPTV-Catchup] pause position=" + exoPlayer.getCurrentPosition());
        } else {
            exoPlayer.setPlayWhenReady(true);
            exoPlayer.play();
            LiveTvScreen$lambda$215(mutableState2, true);
            System.err.println("[IPTV-Catchup] play position=" + exoPlayer.getCurrentPosition());
        }
        LiveTvScreen$lambda$163(mutableState3, LiveTvScreen$lambda$162(mutableState3) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$seekCatchupBy(ExoPlayer exoPlayer, long catchupUrlAnchorOffsetMs, EnrichedChannel playingChannel, MutableState<IptvProgram> mutableState, MutableLongState playerDurationMs$delegate, MutableLongState catchupPlaybackOffsetMs$delegate, MutableState<Integer> mutableState2, MutableLongState playerPositionMs$delegate, MutableState<Boolean> mutableState3, long deltaMs) {
        long maxPosition;
        IptvChannel source;
        long jCatchupUrlAnchorOffset;
        long duration;
        long targetInSegment;
        IptvProgram program = LiveTvScreen$lambda$78(mutableState);
        if (program == null) {
            return;
        }
        Long lValueOf = Long.valueOf(RangesKt.coerceAtLeast(program.getEndUtcMillis() - program.getStartUtcMillis(), 0L));
        if ((lValueOf.longValue() > 0 ? 1 : 0) == 0) {
            lValueOf = null;
        }
        long duration2 = lValueOf != null ? lValueOf.longValue() : LiveTvScreen$lambda$208(playerDurationMs$delegate);
        boolean wasPlayRequested = exoPlayer.getPlayWhenReady();
        long maxPosition2 = duration2 > 1000 ? duration2 - 1000 : duration2;
        long current = catchupUrlAnchorOffsetMs + RangesKt.coerceAtLeast(exoPlayer.getCurrentPosition(), 0L);
        if (maxPosition2 > 0) {
            maxPosition = maxPosition2;
            current = RangesKt.coerceAtMost(current, maxPosition);
        } else {
            maxPosition = maxPosition2;
        }
        long target = RangesKt.coerceAtLeast(current + deltaMs, 0L);
        if (maxPosition > 0) {
            target = RangesKt.coerceAtMost(target, maxPosition);
        }
        if (target == LiveTvScreen$lambda$81(catchupPlaybackOffsetMs$delegate)) {
            LiveTvScreen$lambda$163(mutableState2, LiveTvScreen$lambda$162(mutableState2) + 1);
            return;
        }
        IptvChannel source2 = playingChannel != null ? playingChannel.getSource() : null;
        if (source2 != null) {
            source = source2;
            jCatchupUrlAnchorOffset = catchupUrlAnchorOffset(source, target);
        } else {
            source = source2;
            jCatchupUrlAnchorOffset = 0;
        }
        long targetAnchor = jCatchupUrlAnchorOffset;
        long targetInSegment2 = source != null ? catchupInSegmentSeekOffset(source, target) : target;
        boolean sameAnchor = targetAnchor == catchupUrlAnchorOffsetMs;
        catchupPlaybackOffsetMs$delegate.setLongValue(target);
        playerPositionMs$delegate.setLongValue(target);
        exoPlayer.setPlayWhenReady(true);
        if (!sameAnchor) {
            duration = duration2;
            targetInSegment = targetInSegment2;
        } else {
            duration = duration2;
            targetInSegment = targetInSegment2;
            exoPlayer.seekTo(targetInSegment);
        }
        exoPlayer.play();
        LiveTvScreen$lambda$215(mutableState3, true);
        System.err.println("[IPTV-Catchup] seek delta=" + deltaMs + " current=" + current + " target=" + target + " duration=" + duration + " wasPlayRequested=" + wasPlayRequested + " state=" + exoPlayer.getPlaybackState() + " anchor=" + catchupUrlAnchorOffsetMs + " targetAnchor=" + targetAnchor + " inSegment=" + targetInSegment + " sameAnchor=" + sameAnchor + " exo=" + exoPlayer.getCurrentPosition());
        LiveTvScreen$lambda$163(mutableState2, LiveTvScreen$lambda$162(mutableState2) + 1);
    }

    private static final void LiveTvScreen$seekToPosition(IptvNowNext currentNowNext, EnrichedChannel playingChannel, ExoPlayer exoPlayer, MutableState<IptvProgram> mutableState, MutableLongState playerPositionMs$delegate, long catchupUrlAnchorOffsetMs, MutableLongState playerDurationMs$delegate, MutableLongState catchupPlaybackOffsetMs$delegate, MutableState<Integer> mutableState2, MutableState<Boolean> mutableState3, MutableState<String> mutableState4, MutableState<Boolean> mutableState5, long targetMs) {
        long jLiveTvScreen$lambda$205;
        if (LiveTvScreen$lambda$78(mutableState) != null) {
            long delta = targetMs - LiveTvScreen$lambda$205(playerPositionMs$delegate);
            LiveTvScreen$seekCatchupBy(exoPlayer, catchupUrlAnchorOffsetMs, playingChannel, mutableState, playerDurationMs$delegate, catchupPlaybackOffsetMs$delegate, mutableState2, playerPositionMs$delegate, mutableState3, delta);
            return;
        }
        IptvProgram currentNow = currentNowNext != null ? currentNowNext.getNow() : null;
        if (currentNow != null && currentNow.getStartUtcMillis() > 0) {
            jLiveTvScreen$lambda$205 = RangesKt.coerceAtLeast(System.currentTimeMillis() - currentNow.getStartUtcMillis(), 0L);
        } else {
            jLiveTvScreen$lambda$205 = LiveTvScreen$lambda$205(playerPositionMs$delegate);
        }
        long currentElapsed = jLiveTvScreen$lambda$205;
        long boundedTarget = RangesKt.coerceIn(targetMs, 0L, currentElapsed);
        if (boundedTarget >= currentElapsed) {
            LiveTvScreen$lambda$163(mutableState2, LiveTvScreen$lambda$162(mutableState2) + 1);
            return;
        }
        if (playingChannel != null && currentNow != null && supportsCatchupHistory(playingChannel)) {
            System.err.println("[IPTV-Catchup] auto-switch catchup program=" + currentNow.getTitle() + " targetMs=" + boundedTarget);
            mutableState.setValue(currentNow);
            catchupPlaybackOffsetMs$delegate.setLongValue(boundedTarget);
            playerPositionMs$delegate.setLongValue(boundedTarget);
            mutableState4.setValue(null);
            LiveTvScreen$lambda$218(mutableState5, true);
            LiveTvScreen$lambda$163(mutableState2, LiveTvScreen$lambda$162(mutableState2) + 1);
            return;
        }
        long currentExo = exoPlayer.getCurrentPosition();
        Long lValueOf = Long.valueOf(exoPlayer.getDuration());
        long jLongValue = lValueOf.longValue();
        Long l = (jLongValue > 0L ? 1 : (jLongValue == 0L ? 0 : -1)) > 0 && (jLongValue > C.TIME_UNSET ? 1 : (jLongValue == C.TIME_UNSET ? 0 : -1)) != 0 ? lValueOf : null;
        long maxExo = l != null ? l.longValue() : 60000L;
        long delta2 = boundedTarget - currentElapsed;
        long newExo = RangesKt.coerceIn(currentExo + delta2, 0L, maxExo);
        exoPlayer.seekTo(newExo);
        LiveTvScreen$lambda$163(mutableState2, LiveTvScreen$lambda$162(mutableState2) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$returnCatchupToLive(ExoPlayer exoPlayer, MutableState<IptvProgram> mutableState, MutableState<String> mutableState2, MutableLongState catchupPlaybackOffsetMs$delegate, MutableState<Boolean> mutableState3, MutableState<String> mutableState4, MutableState<Boolean> mutableState5, MutableState<Integer> mutableState6) {
        if (LiveTvScreen$lambda$78(mutableState) == null) {
            return;
        }
        PrintStream printStream = System.err;
        String strLiveTvScreen$lambda$64 = LiveTvScreen$lambda$64(mutableState2);
        if (strLiveTvScreen$lambda$64 == null) {
            strLiveTvScreen$lambda$64 = "";
        }
        printStream.println("[IPTV-Catchup] return-live channel=" + strLiveTvScreen$lambda$64);
        mutableState.setValue(null);
        catchupPlaybackOffsetMs$delegate.setLongValue(0L);
        LiveTvScreen$lambda$144(mutableState3, false);
        mutableState4.setValue(null);
        LiveTvScreen$lambda$218(mutableState5, true);
        exoPlayer.play();
        LiveTvScreen$lambda$163(mutableState6, LiveTvScreen$lambda$162(mutableState6) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$245$0(String $currentStreamUrl, MutableState $playingChannelId$delegate, MutableState $isFullScreen$delegate, MutableState $hudPokeSignal$delegate) {
        if (LiveTvScreen$lambda$64($playingChannelId$delegate) != null || $currentStreamUrl != null) {
            LiveTvScreen$lambda$141($isFullScreen$delegate, true);
            LiveTvScreen$lambda$163($hudPokeSignal$delegate, LiveTvScreen$lambda$162($hudPokeSignal$delegate) + 1);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$41$1$listener$1] */
    public static final DisposableEffectResult LiveTvScreen$lambda$247$0(final ExoPlayer $exoPlayer, final MutableState $playerIsBuffering$delegate, final MutableState $playbackDiagnostic$delegate, final EnrichedChannel $playingChannel, final long $catchupUrlAnchorOffsetMs, final TvViewModel $viewModel, final Context $context, final CoroutineScope $coroutineScope, final MutableState $lastPreparedStreamUrl$delegate, final MutableState $lastPreparedIsHls$delegate, final MutableIntState $playerRetryCount$delegate, final MutableState $playingCatchupProgram$delegate, final MutableState $lastPreparedHeaders$delegate, final MutableLongState $catchupPlaybackOffsetMs$delegate, final Map $baseRequestHeaders, final OkHttpDataSource.Factory $iptvDataSourceFactory, final MutableLongState $lastPreparedCatchupOffsetMs$delegate, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        final ?? r0 = new Player.Listener() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$41$1$listener$1
            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
                Player.Listener.CC.$default$onAudioAttributesChanged(this, audioAttributes);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onAudioSessionIdChanged(int i) {
                Player.Listener.CC.$default$onAudioSessionIdChanged(this, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
                Player.Listener.CC.$default$onAvailableCommandsChanged(this, commands);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onCues(CueGroup cueGroup) {
                Player.Listener.CC.$default$onCues(this, cueGroup);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onCues(List list) {
                Player.Listener.CC.$default$onCues(this, list);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
                Player.Listener.CC.$default$onDeviceInfoChanged(this, deviceInfo);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onDeviceVolumeChanged(int i, boolean z) {
                Player.Listener.CC.$default$onDeviceVolumeChanged(this, i, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onEvents(Player player, Player.Events events) {
                Player.Listener.CC.$default$onEvents(this, player, events);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onIsPlayingChanged(boolean z) {
                Player.Listener.CC.$default$onIsPlayingChanged(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onLoadingChanged(boolean z) {
                Player.Listener.CC.$default$onLoadingChanged(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j) {
                Player.Listener.CC.$default$onMaxSeekToPreviousPositionChanged(this, j);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i) {
                Player.Listener.CC.$default$onMediaItemTransition(this, mediaItem, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                Player.Listener.CC.$default$onMediaMetadataChanged(this, mediaMetadata);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onMetadata(androidx.media3.common.Metadata metadata) {
                Player.Listener.CC.$default$onMetadata(this, metadata);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlayWhenReadyChanged(boolean z, int i) {
                Player.Listener.CC.$default$onPlayWhenReadyChanged(this, z, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                Player.Listener.CC.$default$onPlaybackParametersChanged(this, playbackParameters);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlaybackSuppressionReasonChanged(int i) {
                Player.Listener.CC.$default$onPlaybackSuppressionReasonChanged(this, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
                Player.Listener.CC.$default$onPlayerErrorChanged(this, playbackException);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlayerStateChanged(boolean z, int i) {
                Player.Listener.CC.$default$onPlayerStateChanged(this, z, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
                Player.Listener.CC.$default$onPlaylistMetadataChanged(this, mediaMetadata);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPositionDiscontinuity(int i) {
                Player.Listener.CC.$default$onPositionDiscontinuity(this, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
                Player.Listener.CC.$default$onPositionDiscontinuity(this, positionInfo, positionInfo2, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onRenderedFirstFrame() {
                Player.Listener.CC.$default$onRenderedFirstFrame(this);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onRepeatModeChanged(int i) {
                Player.Listener.CC.$default$onRepeatModeChanged(this, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onSeekBackIncrementChanged(long j) {
                Player.Listener.CC.$default$onSeekBackIncrementChanged(this, j);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onSeekForwardIncrementChanged(long j) {
                Player.Listener.CC.$default$onSeekForwardIncrementChanged(this, j);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
                Player.Listener.CC.$default$onShuffleModeEnabledChanged(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
                Player.Listener.CC.$default$onSkipSilenceEnabledChanged(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onSurfaceSizeChanged(int i, int i2) {
                Player.Listener.CC.$default$onSurfaceSizeChanged(this, i, i2);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onTimelineChanged(Timeline timeline, int i) {
                Player.Listener.CC.$default$onTimelineChanged(this, timeline, i);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
                Player.Listener.CC.$default$onTrackSelectionParametersChanged(this, trackSelectionParameters);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onTracksChanged(Tracks tracks) {
                Player.Listener.CC.$default$onTracksChanged(this, tracks);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
                Player.Listener.CC.$default$onVideoSizeChanged(this, videoSize);
            }

            @Override // androidx.media3.common.Player.Listener
            public /* synthetic */ void onVolumeChanged(float f) {
                Player.Listener.CC.$default$onVolumeChanged(this, f);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlaybackStateChanged(int playbackState) {
                LiveTvScreenKt.LiveTvScreen$lambda$218($playerIsBuffering$delegate, playbackState == 2);
                if (playbackState != 3) {
                    return;
                }
                $playbackDiagnostic$delegate.setValue(null);
                LiveTvScreenKt.LiveTvScreen$lambda$218($playerIsBuffering$delegate, false);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onIsLoadingChanged(boolean isLoading) {
                if ($exoPlayer.getPlaybackState() == 2 || (isLoading && !$exoPlayer.isPlaying())) {
                    LiveTvScreenKt.LiveTvScreen$lambda$218($playerIsBuffering$delegate, true);
                } else if ($exoPlayer.getPlaybackState() == 3) {
                    LiveTvScreenKt.LiveTvScreen$lambda$218($playerIsBuffering$delegate, false);
                }
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlayerError(PlaybackException error) {
                int catchupCandidateCount;
                int maxRetryCount;
                Map<String, String> mapLiveTvScreen$lambda$228;
                Intrinsics.checkNotNullParameter(error, "error");
                LiveTvScreenKt.LiveTvScreen$lambda$218($playerIsBuffering$delegate, false);
                String prepared = LiveTvScreenKt.LiveTvScreen$lambda$222($lastPreparedStreamUrl$delegate);
                if (prepared == null) {
                    return;
                }
                boolean preparedIsHls = LiveTvScreenKt.LiveTvScreen$lambda$225($lastPreparedIsHls$delegate);
                int nextAttempt = LiveTvScreenKt.LiveTvScreen$lambda$234($playerRetryCount$delegate) + 1;
                $playerRetryCount$delegate.setIntValue(nextAttempt);
                EnrichedChannel enrichedChannel = $playingChannel;
                IptvChannel retryChannel = enrichedChannel != null ? enrichedChannel.getSource() : null;
                IptvProgram retryProgram = LiveTvScreenKt.LiveTvScreen$lambda$78($playingCatchupProgram$delegate);
                IptvProgram retryStreamProgram = retryProgram != null ? LiveTvScreenKt.shiftedForCatchup(retryProgram, $catchupUrlAnchorOffsetMs) : null;
                if (retryChannel != null && retryProgram != null) {
                    catchupCandidateCount = $viewModel.getIptvRepository().getCatchupUrlCandidates(retryChannel, retryStreamProgram == null ? retryProgram : retryStreamProgram).size();
                } else {
                    catchupCandidateCount = 0;
                }
                if (retryProgram != null) {
                    maxRetryCount = RangesKt.coerceAtMost(RangesKt.coerceAtLeast(catchupCandidateCount - 1, 0), 2);
                } else {
                    maxRetryCount = 3;
                }
                if (nextAttempt <= maxRetryCount) {
                    if (retryChannel == null || (mapLiveTvScreen$lambda$228 = retryChannel.getRequestHeaders()) == null) {
                        mapLiveTvScreen$lambda$228 = LiveTvScreenKt.LiveTvScreen$lambda$228($lastPreparedHeaders$delegate);
                    }
                    BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new LiveTvScreenKt$LiveTvScreen$41$1$listener$1$onPlayerError$1(nextAttempt, error, catchupCandidateCount, $context, maxRetryCount, mapLiveTvScreen$lambda$228, retryChannel, $viewModel, retryStreamProgram, retryProgram, prepared, preparedIsHls, $playbackDiagnostic$delegate, $catchupPlaybackOffsetMs$delegate, $baseRequestHeaders, $iptvDataSourceFactory, $catchupUrlAnchorOffsetMs, $exoPlayer, $playingChannel, $lastPreparedStreamUrl$delegate, $lastPreparedIsHls$delegate, $lastPreparedHeaders$delegate, $playingCatchupProgram$delegate, $lastPreparedCatchupOffsetMs$delegate, $playerIsBuffering$delegate, $playerRetryCount$delegate, null), 3, null);
                    return;
                }
                MutableState<PlaybackDiagnostic> mutableState = $playbackDiagnostic$delegate;
                String string = $context.getString(R.string.live_diag_playback_failed);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                mutableState.setValue(new PlaybackDiagnostic(string, error.getErrorCodeName() + ": " + LiveTvScreenKt.classifyPlaybackError(error), PlaybackDiagnosticSeverity.Error));
                PrintStream printStream = System.err;
                int i = error.errorCode;
                String errorCodeName = error.getErrorCodeName();
                Object objHttpResponseCode = LiveTvScreenKt.httpResponseCode(error);
                if (objHttpResponseCode == null) {
                    objHttpResponseCode = "-";
                }
                printStream.println("[IPTV] Live playback failed after retries code=" + i + " name=" + errorCodeName + " status=" + objHttpResponseCode + " attempts=" + maxRetryCount + " candidates=" + catchupCandidateCount + " url=" + LiveTvScreenKt.redactPlaybackUrl(prepared));
            }
        };
        $exoPlayer.addListener((Player.Listener) r0);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$247$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $exoPlayer.removeListener(r0);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$250$0(MutableState $searchOpen$delegate) {
        LiveTvScreen$lambda$126($searchOpen$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$251$0(MutableState $variantPickerChannel$delegate) {
        $variantPickerChannel$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$252$0(MutableState $fullscreenGuideOpen$delegate) {
        LiveTvScreen$lambda$144($fullscreenGuideOpen$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$253$0(MutableState $playingCatchupProgram$delegate, ExoPlayer $exoPlayer, MutableState $playingChannelId$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $fullscreenGuideOpen$delegate, MutableState $lastPreparedStreamUrl$delegate, MutableState $playerIsBuffering$delegate, MutableState $hudPokeSignal$delegate, CoroutineScope $focusCommitScope, MutableState $focusedChannelId$delegate, MutableState $isFullScreen$delegate, MutableLongState $lastGuideUserNavigationAt$delegate, Map $rememberedChannelByCategory, Map $filteredChannelIndexById, List $filteredChannels, MutableState $epgPrefetchAnchorId$delegate, MutableState $selectedCategoryId$delegate, MutableIntState $guideWindowStart$delegate, MutableIntState $guideWindowEnd$delegate, MutableState $focusZone$delegate, MutableIntState $focusSelectedChannelSignal$delegate, FocusRequester $epgFocus) {
        if (LiveTvScreen$lambda$78($playingCatchupProgram$delegate) != null) {
            LiveTvScreen$returnCatchupToLive($exoPlayer, $playingCatchupProgram$delegate, $playingChannelId$delegate, $catchupPlaybackOffsetMs$delegate, $fullscreenGuideOpen$delegate, $lastPreparedStreamUrl$delegate, $playerIsBuffering$delegate, $hudPokeSignal$delegate);
        } else {
            LiveTvScreen$exitFullScreenPlayback($focusCommitScope, $playingChannelId$delegate, $focusedChannelId$delegate, $fullscreenGuideOpen$delegate, $isFullScreen$delegate, $hudPokeSignal$delegate, $lastGuideUserNavigationAt$delegate, $rememberedChannelByCategory, $filteredChannelIndexById, $filteredChannels, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $guideWindowStart$delegate, $guideWindowEnd$delegate, $focusZone$delegate, $focusSelectedChannelSignal$delegate, $epgFocus);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$254$0(Function0 $onBack) {
        $onBack.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$0$0(boolean $hasProfile, int $maxTopBarIndex, MutableState $focusZone$delegate, MutableIntState $topBarFocusIndex$delegate) {
        $focusZone$delegate.setValue(LiveTvFocusZone.TOPBAR);
        $topBarFocusIndex$delegate.setIntValue(RangesKt.coerceIn(AppTopBarKt.topBarSelectedIndex(SidebarItem.TV, $hasProfile), 0, $maxTopBarIndex));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$1$0$0(MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $selectedProviderId$delegate, MutableState $selectedCategoryId$delegate, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        LiveTvScreen$noteGuideUserNavigation($lastGuideUserNavigationAt$delegate);
        $selectedProviderId$delegate.setValue(id);
        $selectedCategoryId$delegate.setValue("all");
        $focusedChannelId$delegate.setValue(null);
        $epgPrefetchAnchorId$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$1$1$0(MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $focusZone$delegate, MutableIntState $focusSearchCategorySignal$delegate, FocusRequester $sidebarFocus) {
        LiveTvScreen$focusPlaylistSearch($lastGuideUserNavigationAt$delegate, $focusZone$delegate, $focusSearchCategorySignal$delegate, $sidebarFocus);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$1$4$0(TvViewModel $viewModel, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $viewModel.toggleFavoriteChannel(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$1$3$0$0(EnrichedChannel $channel, Map $variantGroups, MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $variantPickerChannel$delegate) {
        LiveTvScreen$openVariantPicker($variantGroups, $lastGuideUserNavigationAt$delegate, $variantPickerChannel$delegate, $channel);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$1$5$0(MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $selectedCategoryId$delegate, String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        LiveTvScreen$noteGuideUserNavigation($lastGuideUserNavigationAt$delegate);
        $selectedCategoryId$delegate.setValue(id);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$1$6$0(MutableState $searchOpen$delegate) {
        LiveTvScreen$lambda$126($searchOpen$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$1$7$0(MutableState $focusZone$delegate, Map $rememberedChannelByCategory, MutableState $visibleEnrichedState, Map $variantGroups, MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, MutableState $selectedCategoryId$delegate, MutableState $playingChannelId$delegate, MutableState $isFullScreen$delegate, MutableState $playingCatchupProgram$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $hudPokeSignal$delegate, MutableState $fullscreenGuideOpen$delegate, EnrichedChannel channel, IptvProgram iptvProgram) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        $focusZone$delegate.setValue(LiveTvFocusZone.CHANNEL_LIST);
        LiveTvScreen$selectChannel($rememberedChannelByCategory, $visibleEnrichedState, $variantGroups, $lastGuideUserNavigationAt$delegate, $focusedChannelId$delegate, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $playingChannelId$delegate, $isFullScreen$delegate, $playingCatchupProgram$delegate, $catchupPlaybackOffsetMs$delegate, $hudPokeSignal$delegate, $fullscreenGuideOpen$delegate, channel);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$1$8$0(MutableState $programActionDialog$delegate, List $visibleChannels, Map $rememberedChannelByCategory, MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, MutableState $selectedCategoryId$delegate, MutableState $playingChannelId$delegate, MutableState $playingCatchupProgram$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $fullscreenGuideOpen$delegate, Map $filteredChannelIndexById, List $filteredChannels, MutableIntState $guideWindowStart$delegate, MutableIntState $guideWindowEnd$delegate, MutableState $focusZone$delegate, MutableIntState $focusSelectedChannelSignal$delegate, FocusRequester $epgFocus, EnrichedChannel channel, IptvProgram program) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        if (program != null) {
            $programActionDialog$delegate.setValue(new ProgramActionData(channel, program));
        } else {
            LiveTvScreen$playProgramInMini($visibleChannels, $rememberedChannelByCategory, $lastGuideUserNavigationAt$delegate, $focusedChannelId$delegate, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $playingChannelId$delegate, $playingCatchupProgram$delegate, $catchupPlaybackOffsetMs$delegate, $fullscreenGuideOpen$delegate, $filteredChannelIndexById, $filteredChannels, $guideWindowStart$delegate, $guideWindowEnd$delegate, $focusZone$delegate, $focusSelectedChannelSignal$delegate, $epgFocus, channel, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$1$9$0(Pair[] $pendingFocusCommit, Job[] $focusCommitJob, CoroutineScope $focusCommitScope, MutableState $selectedCategoryId$delegate, Map $rememberedChannelByCategory, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, EnrichedChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        LiveTvScreen$commitFocusedChannel($pendingFocusCommit, $focusCommitJob, $focusCommitScope, $selectedCategoryId$delegate, $rememberedChannelByCategory, $focusedChannelId$delegate, $epgPrefetchAnchorId$delegate, channel);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$1$10$0(TvViewModel $viewModel, String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        $viewModel.toggleFavoriteChannel(id);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LiveTvScreen$lambda$258$1$11$0(Map $variantGroups, EnrichedChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        return LiveTvEnhancementsKt.variantCountFor(channel, $variantGroups);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$1$12$0(Map $variantGroups, MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $variantPickerChannel$delegate, EnrichedChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        LiveTvScreen$openVariantPicker($variantGroups, $lastGuideUserNavigationAt$delegate, $variantPickerChannel$delegate, channel);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$1$13$0(MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $focusZone$delegate, MutableIntState $focusSearchCategorySignal$delegate, FocusRequester $sidebarFocus) {
        LiveTvScreen$focusPlaylistSearch($lastGuideUserNavigationAt$delegate, $focusZone$delegate, $focusSearchCategorySignal$delegate, $sidebarFocus);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$1$14$0(Map $rememberedChannelByCategory, Map $filteredChannelIndexById, List $filteredChannels, MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, MutableState $selectedCategoryId$delegate, MutableIntState $guideWindowStart$delegate, MutableIntState $guideWindowEnd$delegate, MutableState $focusZone$delegate, MutableIntState $focusEpgSignal$delegate, FocusRequester $epgFocus, EnrichedChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        LiveTvScreen$focusEpg($rememberedChannelByCategory, $filteredChannelIndexById, $filteredChannels, $lastGuideUserNavigationAt$delegate, $focusedChannelId$delegate, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $guideWindowStart$delegate, $guideWindowEnd$delegate, $focusZone$delegate, $focusEpgSignal$delegate, $epgFocus, channel.getId());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$1$15$0(MutableState $focusedChannelId$delegate, MutableState $playingChannelId$delegate, MutableLongState $lastGuideUserNavigationAt$delegate, Map $rememberedChannelByCategory, Map $filteredChannelIndexById, List $filteredChannels, MutableState $epgPrefetchAnchorId$delegate, MutableState $selectedCategoryId$delegate, MutableIntState $guideWindowStart$delegate, MutableIntState $guideWindowEnd$delegate, MutableState $focusZone$delegate, MutableIntState $focusSelectedChannelSignal$delegate, FocusRequester $epgFocus, EnrichedChannel channel) {
        String strLiveTvScreen$lambda$69;
        if ((channel == null || (strLiveTvScreen$lambda$69 = channel.getId()) == null) && (strLiveTvScreen$lambda$69 = LiveTvScreen$lambda$69($focusedChannelId$delegate)) == null) {
            strLiveTvScreen$lambda$69 = LiveTvScreen$lambda$64($playingChannelId$delegate);
        }
        LiveTvScreen$focusChannelList($focusedChannelId$delegate, $playingChannelId$delegate, $lastGuideUserNavigationAt$delegate, $rememberedChannelByCategory, $filteredChannelIndexById, $filteredChannels, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $guideWindowStart$delegate, $guideWindowEnd$delegate, $focusZone$delegate, $focusSelectedChannelSignal$delegate, $epgFocus, strLiveTvScreen$lambda$69);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$0$0(MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $selectedCategoryId$delegate, String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        LiveTvScreen$noteGuideUserNavigation($lastGuideUserNavigationAt$delegate);
        $selectedCategoryId$delegate.setValue(id);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$1$0(MutableState $searchOpen$delegate) {
        LiveTvScreen$lambda$126($searchOpen$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$2$0(TvViewModel $viewModel, MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $selectedCategoryId$delegate, String playlistId, String groupName) {
        Intrinsics.checkNotNullParameter(groupName, "groupName");
        LiveTvScreen$noteGuideUserNavigation($lastGuideUserNavigationAt$delegate);
        $selectedCategoryId$delegate.setValue("all");
        $viewModel.toggleHiddenGroup(playlistId, groupName);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$3$0(TvViewModel $viewModel, MutableLongState $lastGuideUserNavigationAt$delegate, String playlistId, String groupName) {
        Intrinsics.checkNotNullParameter(groupName, "groupName");
        LiveTvScreen$noteGuideUserNavigation($lastGuideUserNavigationAt$delegate);
        $viewModel.toggleHiddenGroup(playlistId, groupName);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$4$0(TvViewModel $viewModel, String playlistId, String groupName) {
        Intrinsics.checkNotNullParameter(groupName, "groupName");
        $viewModel.moveGroupUp(playlistId, groupName);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$5$0(TvViewModel $viewModel, String playlistId, String groupName) {
        Intrinsics.checkNotNullParameter(groupName, "groupName");
        $viewModel.moveGroupToTop(playlistId, groupName);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$6$0(TvViewModel $viewModel, String playlistId, String groupName) {
        Intrinsics.checkNotNullParameter(groupName, "groupName");
        $viewModel.moveGroupDown(playlistId, groupName);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$7$0(MutableState $focusZone$delegate) {
        if (LiveTvScreen$lambda$42($focusZone$delegate) != LiveTvFocusZone.TOPBAR) {
            $focusZone$delegate.setValue(LiveTvFocusZone.CATEGORY_LIST);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0053 A[PHI: r0
      0x0053: PHI (r0v10 java.lang.String) = (r0v9 java.lang.String), (r0v13 java.lang.String) binds: [B:17:0x002f, B:25:0x0043] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit LiveTvScreen$lambda$258$2$8$0(java.util.Map r15, java.util.List r16, androidx.compose.runtime.MutableState r17, java.util.Map r18, androidx.compose.runtime.MutableState r19, androidx.compose.runtime.MutableState r20, androidx.compose.runtime.MutableLongState r21, androidx.compose.runtime.MutableState r22, androidx.compose.runtime.MutableIntState r23, androidx.compose.runtime.MutableIntState r24, androidx.compose.runtime.MutableState r25, androidx.compose.runtime.MutableIntState r26, androidx.compose.ui.focus.FocusRequester r27) {
        /*
            r4 = r18
            java.lang.String r0 = LiveTvScreen$lambda$14(r17)
            java.lang.Object r0 = r15.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            r1 = 0
            if (r0 == 0) goto L19
        L10:
            r2 = r0
            r3 = 0
            boolean r5 = r4.containsKey(r2)
            if (r5 == 0) goto L19
            goto L1a
        L19:
            r0 = r1
        L1a:
            r14 = r0
            if (r14 != 0) goto L55
            java.lang.String r0 = LiveTvScreen$lambda$69(r19)
            if (r0 == 0) goto L2e
            r2 = r0
            r3 = 0
            boolean r5 = r4.containsKey(r2)
            if (r5 == 0) goto L2c
            goto L2f
        L2c:
            r0 = r1
            goto L2f
        L2e:
            r0 = r1
        L2f:
            if (r0 != 0) goto L53
            java.lang.String r0 = LiveTvScreen$lambda$64(r20)
            if (r0 == 0) goto L42
            r2 = r0
            r3 = 0
            boolean r5 = r4.containsKey(r2)
            if (r5 == 0) goto L40
            goto L43
        L40:
            r0 = r1
            goto L43
        L42:
            r0 = r1
        L43:
            if (r0 != 0) goto L53
            java.lang.Object r0 = kotlin.collections.CollectionsKt.firstOrNull(r16)
            com.arflix.tv.ui.screens.tv.live.EnrichedChannel r0 = (com.arflix.tv.ui.screens.tv.live.EnrichedChannel) r0
            if (r0 == 0) goto L51
            java.lang.String r1 = r0.getId()
        L51:
            r13 = r1
            goto L56
        L53:
            r13 = r0
            goto L56
        L55:
            r13 = r14
        L56:
            r3 = r15
            r5 = r16
            r7 = r17
            r0 = r19
            r1 = r20
            r2 = r21
            r6 = r22
            r8 = r23
            r9 = r24
            r10 = r25
            r11 = r26
            r12 = r27
            LiveTvScreen$focusChannelList(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.LiveTvScreen$lambda$258$2$8$0(java.util.Map, java.util.List, androidx.compose.runtime.MutableState, java.util.Map, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableLongState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableIntState, androidx.compose.ui.focus.FocusRequester):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$9$0(boolean $hasProfile, int $maxTopBarIndex, MutableIntState $topBarFocusIndex$delegate, MutableState $focusZone$delegate) {
        $topBarFocusIndex$delegate.setIntValue(RangesKt.coerceIn(AppTopBarKt.topBarSelectedIndex(SidebarItem.TV, $hasProfile), 0, $maxTopBarIndex));
        $focusZone$delegate.setValue(LiveTvFocusZone.TOPBAR);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$10$0$0(MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $selectedProviderId$delegate, MutableState $selectedCategoryId$delegate, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        LiveTvScreen$noteGuideUserNavigation($lastGuideUserNavigationAt$delegate);
        $selectedProviderId$delegate.setValue(id);
        $selectedCategoryId$delegate.setValue("all");
        $focusedChannelId$delegate.setValue(null);
        $epgPrefetchAnchorId$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$10$1$0(boolean $hasProfile, int $maxTopBarIndex, MutableIntState $topBarFocusIndex$delegate, MutableState $focusZone$delegate) {
        $topBarFocusIndex$delegate.setIntValue(RangesKt.coerceIn(AppTopBarKt.topBarSelectedIndex(SidebarItem.TV, $hasProfile), 0, $maxTopBarIndex));
        $focusZone$delegate.setValue(LiveTvFocusZone.TOPBAR);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$10$2$0(MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $focusZone$delegate, MutableIntState $focusSearchCategorySignal$delegate, FocusRequester $sidebarFocus) {
        LiveTvScreen$focusPlaylistSearch($lastGuideUserNavigationAt$delegate, $focusZone$delegate, $focusSearchCategorySignal$delegate, $sidebarFocus);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$10$5$0(TvViewModel $viewModel, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $viewModel.toggleFavoriteChannel(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$10$4$0$0(EnrichedChannel $channel, Map $variantGroups, MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $variantPickerChannel$delegate) {
        LiveTvScreen$openVariantPicker($variantGroups, $lastGuideUserNavigationAt$delegate, $variantPickerChannel$delegate, $channel);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$10$7$0(Map $rememberedChannelByCategory, MutableState $visibleEnrichedState, Map $variantGroups, MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, MutableState $selectedCategoryId$delegate, MutableState $playingChannelId$delegate, MutableState $isFullScreen$delegate, MutableState $playingCatchupProgram$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $hudPokeSignal$delegate, MutableState $fullscreenGuideOpen$delegate, EnrichedChannel channel, IptvProgram iptvProgram) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        LiveTvScreen$selectChannel($rememberedChannelByCategory, $visibleEnrichedState, $variantGroups, $lastGuideUserNavigationAt$delegate, $focusedChannelId$delegate, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $playingChannelId$delegate, $isFullScreen$delegate, $playingCatchupProgram$delegate, $catchupPlaybackOffsetMs$delegate, $hudPokeSignal$delegate, $fullscreenGuideOpen$delegate, channel);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$10$8$0(MutableState $programActionDialog$delegate, List $visibleChannels, Map $rememberedChannelByCategory, MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, MutableState $selectedCategoryId$delegate, MutableState $playingChannelId$delegate, MutableState $playingCatchupProgram$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $fullscreenGuideOpen$delegate, Map $filteredChannelIndexById, List $filteredChannels, MutableIntState $guideWindowStart$delegate, MutableIntState $guideWindowEnd$delegate, MutableState $focusZone$delegate, MutableIntState $focusSelectedChannelSignal$delegate, FocusRequester $epgFocus, EnrichedChannel channel, IptvProgram program) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        if (program != null) {
            $programActionDialog$delegate.setValue(new ProgramActionData(channel, program));
        } else {
            LiveTvScreen$playProgramInMini($visibleChannels, $rememberedChannelByCategory, $lastGuideUserNavigationAt$delegate, $focusedChannelId$delegate, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $playingChannelId$delegate, $playingCatchupProgram$delegate, $catchupPlaybackOffsetMs$delegate, $fullscreenGuideOpen$delegate, $filteredChannelIndexById, $filteredChannels, $guideWindowStart$delegate, $guideWindowEnd$delegate, $focusZone$delegate, $focusSelectedChannelSignal$delegate, $epgFocus, channel, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$10$9$0(Pair[] $pendingFocusCommit, Job[] $focusCommitJob, CoroutineScope $focusCommitScope, MutableState $selectedCategoryId$delegate, Map $rememberedChannelByCategory, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, EnrichedChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        LiveTvScreen$commitFocusedChannel($pendingFocusCommit, $focusCommitJob, $focusCommitScope, $selectedCategoryId$delegate, $rememberedChannelByCategory, $focusedChannelId$delegate, $epgPrefetchAnchorId$delegate, channel);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$10$10$0(TvViewModel $viewModel, String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        $viewModel.toggleFavoriteChannel(id);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LiveTvScreen$lambda$258$2$10$11$0(Map $variantGroups, EnrichedChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        return LiveTvEnhancementsKt.variantCountFor(channel, $variantGroups);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$10$12$0(Map $variantGroups, MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $variantPickerChannel$delegate, EnrichedChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        LiveTvScreen$openVariantPicker($variantGroups, $lastGuideUserNavigationAt$delegate, $variantPickerChannel$delegate, channel);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$10$13$0(MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $focusZone$delegate, MutableIntState $focusSearchCategorySignal$delegate, FocusRequester $sidebarFocus) {
        LiveTvScreen$focusPlaylistSearch($lastGuideUserNavigationAt$delegate, $focusZone$delegate, $focusSearchCategorySignal$delegate, $sidebarFocus);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$10$14$0(Map $rememberedChannelByCategory, Map $filteredChannelIndexById, List $filteredChannels, MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, MutableState $selectedCategoryId$delegate, MutableIntState $guideWindowStart$delegate, MutableIntState $guideWindowEnd$delegate, MutableState $focusZone$delegate, MutableIntState $focusEpgSignal$delegate, FocusRequester $epgFocus, EnrichedChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        LiveTvScreen$focusEpg($rememberedChannelByCategory, $filteredChannelIndexById, $filteredChannels, $lastGuideUserNavigationAt$delegate, $focusedChannelId$delegate, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $guideWindowStart$delegate, $guideWindowEnd$delegate, $focusZone$delegate, $focusEpgSignal$delegate, $epgFocus, channel.getId());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$10$15$0(MutableState $focusedChannelId$delegate, MutableState $playingChannelId$delegate, MutableLongState $lastGuideUserNavigationAt$delegate, Map $rememberedChannelByCategory, Map $filteredChannelIndexById, List $filteredChannels, MutableState $epgPrefetchAnchorId$delegate, MutableState $selectedCategoryId$delegate, MutableIntState $guideWindowStart$delegate, MutableIntState $guideWindowEnd$delegate, MutableState $focusZone$delegate, MutableIntState $focusSelectedChannelSignal$delegate, FocusRequester $epgFocus, EnrichedChannel channel) {
        String strLiveTvScreen$lambda$69;
        if ((channel == null || (strLiveTvScreen$lambda$69 = channel.getId()) == null) && (strLiveTvScreen$lambda$69 = LiveTvScreen$lambda$69($focusedChannelId$delegate)) == null) {
            strLiveTvScreen$lambda$69 = LiveTvScreen$lambda$64($playingChannelId$delegate);
        }
        LiveTvScreen$focusChannelList($focusedChannelId$delegate, $playingChannelId$delegate, $lastGuideUserNavigationAt$delegate, $rememberedChannelByCategory, $filteredChannelIndexById, $filteredChannels, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $guideWindowStart$delegate, $guideWindowEnd$delegate, $focusZone$delegate, $focusSelectedChannelSignal$delegate, $epgFocus, strLiveTvScreen$lambda$69);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$2$10$6$0(MutableState $focusZone$delegate, FocusState it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (it.getHasFocus() && LiveTvScreen$lambda$42($focusZone$delegate) == LiveTvFocusZone.CATEGORY_LIST) {
            $focusZone$delegate.setValue(LiveTvFocusZone.CHANNEL_LIST);
        }
        return Unit.INSTANCE;
    }

    private static final float LiveTvScreen$lambda$258$3(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$4$0(MutableState $fullscreenGuideOpen$delegate, MutableState $hudPokeSignal$delegate, MutableState $quickZapOpen$delegate, MutableState $playingCatchupProgram$delegate, ExoPlayer $exoPlayer, MutableState $playingChannelId$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $lastPreparedStreamUrl$delegate, MutableState $playerIsBuffering$delegate, CoroutineScope $focusCommitScope, MutableState $focusedChannelId$delegate, MutableState $isFullScreen$delegate, MutableLongState $lastGuideUserNavigationAt$delegate, Map $rememberedChannelByCategory, Map $filteredChannelIndexById, List $filteredChannels, MutableState $epgPrefetchAnchorId$delegate, MutableState $selectedCategoryId$delegate, MutableIntState $guideWindowStart$delegate, MutableIntState $guideWindowEnd$delegate, MutableState $focusZone$delegate, MutableIntState $focusSelectedChannelSignal$delegate, FocusRequester $epgFocus) {
        if (LiveTvScreen$lambda$143($fullscreenGuideOpen$delegate)) {
            LiveTvScreen$lambda$144($fullscreenGuideOpen$delegate, false);
            LiveTvScreen$lambda$163($hudPokeSignal$delegate, LiveTvScreen$lambda$162($hudPokeSignal$delegate) + 1);
        } else if (!LiveTvScreen$lambda$165($quickZapOpen$delegate)) {
            if (LiveTvScreen$lambda$78($playingCatchupProgram$delegate) != null) {
                LiveTvScreen$returnCatchupToLive($exoPlayer, $playingCatchupProgram$delegate, $playingChannelId$delegate, $catchupPlaybackOffsetMs$delegate, $fullscreenGuideOpen$delegate, $lastPreparedStreamUrl$delegate, $playerIsBuffering$delegate, $hudPokeSignal$delegate);
            } else {
                LiveTvScreen$exitFullScreenPlayback($focusCommitScope, $playingChannelId$delegate, $focusedChannelId$delegate, $fullscreenGuideOpen$delegate, $isFullScreen$delegate, $hudPokeSignal$delegate, $lastGuideUserNavigationAt$delegate, $rememberedChannelByCategory, $filteredChannelIndexById, $filteredChannels, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $guideWindowStart$delegate, $guideWindowEnd$delegate, $focusZone$delegate, $focusSelectedChannelSignal$delegate, $epgFocus);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$5$0(float $scale, State $fsProgress$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.mo3965setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.22f, 0.18f));
        graphicsLayer.setScaleX($scale);
        graphicsLayer.setScaleY($scale);
        graphicsLayer.setAlpha(LiveTvScreen$lambda$258$3($fsProgress$delegate));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$8$0(MutableState $hudPokeSignal$delegate) {
        LiveTvScreen$lambda$163($hudPokeSignal$delegate, LiveTvScreen$lambda$162($hudPokeSignal$delegate) + 1);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlayerView LiveTvScreen$lambda$258$10$0$0(ExoPlayer $exoPlayer, Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        PlayerView playerView = new PlayerView(ctx);
        playerView.setKeepScreenOn(true);
        playerView.setPlayer($exoPlayer);
        playerView.setUseController(false);
        playerView.setKeepContentOnPlayerReset(true);
        return playerView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$1$0(ExoPlayer $exoPlayer, PlayerView view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setKeepScreenOn(true);
        if (view.getPlayer() != $exoPlayer) {
            view.setPlayer($exoPlayer);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$3$0(MutableState $playingCatchupProgram$delegate, ExoPlayer $exoPlayer, MutableState $playingChannelId$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $fullscreenGuideOpen$delegate, MutableState $lastPreparedStreamUrl$delegate, MutableState $playerIsBuffering$delegate, MutableState $hudPokeSignal$delegate, CoroutineScope $focusCommitScope, MutableState $focusedChannelId$delegate, MutableState $isFullScreen$delegate, MutableLongState $lastGuideUserNavigationAt$delegate, Map $rememberedChannelByCategory, Map $filteredChannelIndexById, List $filteredChannels, MutableState $epgPrefetchAnchorId$delegate, MutableState $selectedCategoryId$delegate, MutableIntState $guideWindowStart$delegate, MutableIntState $guideWindowEnd$delegate, MutableState $focusZone$delegate, MutableIntState $focusSelectedChannelSignal$delegate, FocusRequester $epgFocus) {
        if (LiveTvScreen$lambda$78($playingCatchupProgram$delegate) != null) {
            LiveTvScreen$returnCatchupToLive($exoPlayer, $playingCatchupProgram$delegate, $playingChannelId$delegate, $catchupPlaybackOffsetMs$delegate, $fullscreenGuideOpen$delegate, $lastPreparedStreamUrl$delegate, $playerIsBuffering$delegate, $hudPokeSignal$delegate);
        } else {
            LiveTvScreen$exitFullScreenPlayback($focusCommitScope, $playingChannelId$delegate, $focusedChannelId$delegate, $fullscreenGuideOpen$delegate, $isFullScreen$delegate, $hudPokeSignal$delegate, $lastGuideUserNavigationAt$delegate, $rememberedChannelByCategory, $filteredChannelIndexById, $filteredChannels, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $guideWindowStart$delegate, $guideWindowEnd$delegate, $focusZone$delegate, $focusSelectedChannelSignal$delegate, $epgFocus);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$4$0(EnrichedChannel $playingChannel, TvViewModel $viewModel, MutableState $guideChannel$delegate, MutableState $playingChannelId$delegate, MutableState $fullscreenGuideOpen$delegate, MutableState $hudPokeSignal$delegate) {
        LiveTvScreen$openFullscreenGuide($playingChannel, $viewModel, $guideChannel$delegate, $playingChannelId$delegate, $fullscreenGuideOpen$delegate, $hudPokeSignal$delegate);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$5$0(ExoPlayer $exoPlayer, MutableState $playingCatchupProgram$delegate, MutableState $playerPlayWhenReady$delegate, MutableState $hudPokeSignal$delegate) {
        if (LiveTvScreen$lambda$78($playingCatchupProgram$delegate) != null) {
            LiveTvScreen$toggleCatchupPlayback($exoPlayer, $playingCatchupProgram$delegate, $playerPlayWhenReady$delegate, $hudPokeSignal$delegate);
        } else {
            if ($exoPlayer.isPlaying()) {
                $exoPlayer.pause();
                LiveTvScreen$lambda$215($playerPlayWhenReady$delegate, false);
            } else {
                $exoPlayer.setPlayWhenReady(true);
                $exoPlayer.play();
                LiveTvScreen$lambda$215($playerPlayWhenReady$delegate, true);
            }
            LiveTvScreen$lambda$163($hudPokeSignal$delegate, LiveTvScreen$lambda$162($hudPokeSignal$delegate) + 1);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$6$0(IptvNowNext $currentNowNext, MutableLongState $playerPositionMs$delegate, EnrichedChannel $playingChannel, ExoPlayer $exoPlayer, MutableState $playingCatchupProgram$delegate, long $catchupUrlAnchorOffsetMs, MutableLongState $playerDurationMs$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $hudPokeSignal$delegate, MutableState $playerPlayWhenReady$delegate, MutableState $lastPreparedStreamUrl$delegate, MutableState $playerIsBuffering$delegate) {
        long currentElapsed;
        IptvProgram currentNow = $currentNowNext != null ? $currentNowNext.getNow() : null;
        if (currentNow != null && currentNow.getStartUtcMillis() > 0) {
            currentElapsed = RangesKt.coerceAtLeast(System.currentTimeMillis() - currentNow.getStartUtcMillis(), 0L);
        } else {
            currentElapsed = LiveTvScreen$lambda$205($playerPositionMs$delegate);
        }
        LiveTvScreen$seekToPosition($currentNowNext, $playingChannel, $exoPlayer, $playingCatchupProgram$delegate, $playerPositionMs$delegate, $catchupUrlAnchorOffsetMs, $playerDurationMs$delegate, $catchupPlaybackOffsetMs$delegate, $hudPokeSignal$delegate, $playerPlayWhenReady$delegate, $lastPreparedStreamUrl$delegate, $playerIsBuffering$delegate, RangesKt.coerceAtLeast(currentElapsed - 10000, 0L));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$7$0(IptvNowNext $currentNowNext, MutableLongState $playerPositionMs$delegate, EnrichedChannel $playingChannel, ExoPlayer $exoPlayer, MutableState $playingCatchupProgram$delegate, long $catchupUrlAnchorOffsetMs, MutableLongState $playerDurationMs$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $hudPokeSignal$delegate, MutableState $playerPlayWhenReady$delegate, MutableState $lastPreparedStreamUrl$delegate, MutableState $playerIsBuffering$delegate) {
        long currentElapsed;
        IptvProgram currentNow = $currentNowNext != null ? $currentNowNext.getNow() : null;
        if (currentNow != null && currentNow.getStartUtcMillis() > 0) {
            currentElapsed = RangesKt.coerceAtLeast(System.currentTimeMillis() - currentNow.getStartUtcMillis(), 0L);
        } else {
            currentElapsed = LiveTvScreen$lambda$205($playerPositionMs$delegate);
        }
        LiveTvScreen$seekToPosition($currentNowNext, $playingChannel, $exoPlayer, $playingCatchupProgram$delegate, $playerPositionMs$delegate, $catchupUrlAnchorOffsetMs, $playerDurationMs$delegate, $catchupPlaybackOffsetMs$delegate, $hudPokeSignal$delegate, $playerPlayWhenReady$delegate, $lastPreparedStreamUrl$delegate, $playerIsBuffering$delegate, currentElapsed + 10000);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$8$0(List $filteredChannels, EnrichedChannel $playingChannel, Map $rememberedChannelByCategory, MutableState $visibleEnrichedState, Map $variantGroups, MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, MutableState $selectedCategoryId$delegate, MutableState $playingChannelId$delegate, MutableState $isFullScreen$delegate, MutableState $playingCatchupProgram$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $hudPokeSignal$delegate, MutableState $fullscreenGuideOpen$delegate) {
        int curIdx = 0;
        Iterator it = $filteredChannels.iterator();
        while (true) {
            if (it.hasNext()) {
                if (Intrinsics.areEqual(((EnrichedChannel) it.next()).getId(), $playingChannel != null ? $playingChannel.getId() : null)) {
                    break;
                }
                curIdx++;
            } else {
                curIdx = -1;
                break;
            }
        }
        if (curIdx > 0) {
            LiveTvScreen$selectChannel($rememberedChannelByCategory, $visibleEnrichedState, $variantGroups, $lastGuideUserNavigationAt$delegate, $focusedChannelId$delegate, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $playingChannelId$delegate, $isFullScreen$delegate, $playingCatchupProgram$delegate, $catchupPlaybackOffsetMs$delegate, $hudPokeSignal$delegate, $fullscreenGuideOpen$delegate, (EnrichedChannel) $filteredChannels.get(curIdx - 1));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$9$0(List $filteredChannels, EnrichedChannel $playingChannel, Map $rememberedChannelByCategory, MutableState $visibleEnrichedState, Map $variantGroups, MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, MutableState $selectedCategoryId$delegate, MutableState $playingChannelId$delegate, MutableState $isFullScreen$delegate, MutableState $playingCatchupProgram$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $hudPokeSignal$delegate, MutableState $fullscreenGuideOpen$delegate) {
        int curIdx = 0;
        Iterator it = $filteredChannels.iterator();
        while (true) {
            if (it.hasNext()) {
                if (Intrinsics.areEqual(((EnrichedChannel) it.next()).getId(), $playingChannel != null ? $playingChannel.getId() : null)) {
                    break;
                }
                curIdx++;
            } else {
                curIdx = -1;
                break;
            }
        }
        boolean z = false;
        if (curIdx >= 0 && curIdx < $filteredChannels.size() - 1) {
            z = true;
        }
        if (z) {
            LiveTvScreen$selectChannel($rememberedChannelByCategory, $visibleEnrichedState, $variantGroups, $lastGuideUserNavigationAt$delegate, $focusedChannelId$delegate, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $playingChannelId$delegate, $isFullScreen$delegate, $playingCatchupProgram$delegate, $catchupPlaybackOffsetMs$delegate, $hudPokeSignal$delegate, $fullscreenGuideOpen$delegate, (EnrichedChannel) $filteredChannels.get(curIdx + 1));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$10$0(EnrichedChannel $playingChannel, MutableState $playingCatchupProgram$delegate, MutableLongState $playerPositionMs$delegate, ExoPlayer $exoPlayer, long $catchupUrlAnchorOffsetMs, MutableLongState $playerDurationMs$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $hudPokeSignal$delegate, MutableState $playerPlayWhenReady$delegate, MutableState $lastPreparedStreamUrl$delegate, MutableState $lastPreparedIsHls$delegate, MutableState $lastPreparedHeaders$delegate, Map $baseRequestHeaders, OkHttpDataSource.Factory $iptvDataSourceFactory, Context $context, MutableLongState $lastPreparedCatchupOffsetMs$delegate, MutableState $playerIsBuffering$delegate, MutableIntState $playerRetryCount$delegate, MutableState $playbackDiagnostic$delegate) {
        IptvChannel source;
        if (LiveTvScreen$lambda$78($playingCatchupProgram$delegate) != null) {
            LiveTvScreen$seekCatchupBy($exoPlayer, $catchupUrlAnchorOffsetMs, $playingChannel, $playingCatchupProgram$delegate, $playerDurationMs$delegate, $catchupPlaybackOffsetMs$delegate, $hudPokeSignal$delegate, $playerPositionMs$delegate, $playerPlayWhenReady$delegate, -LiveTvScreen$lambda$205($playerPositionMs$delegate));
        } else {
            String preparedStream = LiveTvScreen$lambda$222($lastPreparedStreamUrl$delegate);
            if (preparedStream != null) {
                LiveTvScreen$prepareStream$default($baseRequestHeaders, $iptvDataSourceFactory, $catchupUrlAnchorOffsetMs, $exoPlayer, $context, $playingChannel, $lastPreparedStreamUrl$delegate, $lastPreparedIsHls$delegate, $lastPreparedHeaders$delegate, $playingCatchupProgram$delegate, $lastPreparedCatchupOffsetMs$delegate, $playerIsBuffering$delegate, $playerRetryCount$delegate, $playbackDiagnostic$delegate, $catchupPlaybackOffsetMs$delegate, preparedStream, LiveTvScreen$lambda$225($lastPreparedIsHls$delegate), LiveTvScreen$lambda$228($lastPreparedHeaders$delegate), true, 0L, ($playingChannel == null || (source = $playingChannel.getSource()) == null) ? null : source.getDrmInfo(), true, 524288, null);
            }
            LiveTvScreen$lambda$163($hudPokeSignal$delegate, LiveTvScreen$lambda$162($hudPokeSignal$delegate) + 1);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$11$0(ExoPlayer $exoPlayer, MutableState $playingCatchupProgram$delegate, MutableState $playingChannelId$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $fullscreenGuideOpen$delegate, MutableState $lastPreparedStreamUrl$delegate, MutableState $playerIsBuffering$delegate, MutableState $hudPokeSignal$delegate) {
        LiveTvScreen$returnCatchupToLive($exoPlayer, $playingCatchupProgram$delegate, $playingChannelId$delegate, $catchupPlaybackOffsetMs$delegate, $fullscreenGuideOpen$delegate, $lastPreparedStreamUrl$delegate, $playerIsBuffering$delegate, $hudPokeSignal$delegate);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$12$0(IptvNowNext $currentNowNext, EnrichedChannel $playingChannel, ExoPlayer $exoPlayer, MutableState $playingCatchupProgram$delegate, MutableLongState $playerPositionMs$delegate, long $catchupUrlAnchorOffsetMs, MutableLongState $playerDurationMs$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $hudPokeSignal$delegate, MutableState $playerPlayWhenReady$delegate, MutableState $lastPreparedStreamUrl$delegate, MutableState $playerIsBuffering$delegate, long targetMs) {
        LiveTvScreen$seekToPosition($currentNowNext, $playingChannel, $exoPlayer, $playingCatchupProgram$delegate, $playerPositionMs$delegate, $catchupUrlAnchorOffsetMs, $playerDurationMs$delegate, $catchupPlaybackOffsetMs$delegate, $hudPokeSignal$delegate, $playerPlayWhenReady$delegate, $lastPreparedStreamUrl$delegate, $playerIsBuffering$delegate, targetMs);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$13$0(MutableState $quickZapOpen$delegate, MutableState $isHudVisible$delegate) {
        LiveTvScreen$lambda$166($quickZapOpen$delegate, true);
        LiveTvScreen$lambda$169($isHudVisible$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$14$0(MutableState $isHudVisible$delegate, boolean it) {
        LiveTvScreen$lambda$169($isHudVisible$delegate, it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$15$0(MutableState $fullscreenGuideOpen$delegate, MutableState $guideOpenedFromQuickZap$delegate, MutableState $quickZapOpen$delegate, MutableState $hudPokeSignal$delegate) {
        LiveTvScreen$lambda$144($fullscreenGuideOpen$delegate, false);
        if (LiveTvScreen$lambda$171($guideOpenedFromQuickZap$delegate)) {
            LiveTvScreen$lambda$172($guideOpenedFromQuickZap$delegate, false);
            LiveTvScreen$lambda$166($quickZapOpen$delegate, true);
        } else {
            LiveTvScreen$lambda$163($hudPokeSignal$delegate, LiveTvScreen$lambda$162($hudPokeSignal$delegate) + 1);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$16$0(EnrichedChannel $playingChannel, MutableState $guideChannel$delegate, MutableState $guideOpenedFromQuickZap$delegate, List $visibleChannels, MutableState $playingCatchupProgram$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $playingChannelId$delegate, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, MutableState $fullscreenGuideOpen$delegate, MutableState $isFullScreen$delegate, MutableState $hudPokeSignal$delegate, IptvProgram program) {
        EnrichedChannel enrichedChannelLiveTvScreen$lambda$174 = LiveTvScreen$lambda$174($guideChannel$delegate);
        if (enrichedChannelLiveTvScreen$lambda$174 == null) {
            enrichedChannelLiveTvScreen$lambda$174 = $playingChannel;
        }
        EnrichedChannel target = enrichedChannelLiveTvScreen$lambda$174;
        LiveTvScreen$lambda$172($guideOpenedFromQuickZap$delegate, false);
        LiveTvScreen$playProgramInFullscreen($playingChannel, $visibleChannels, $playingCatchupProgram$delegate, $catchupPlaybackOffsetMs$delegate, $playingChannelId$delegate, $focusedChannelId$delegate, $epgPrefetchAnchorId$delegate, $fullscreenGuideOpen$delegate, $isFullScreen$delegate, $hudPokeSignal$delegate, program, target);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$17$0(MutableState $fullscreenGuideOpen$delegate, MutableState $quickZapOpen$delegate) {
        LiveTvScreen$lambda$144($fullscreenGuideOpen$delegate, false);
        LiveTvScreen$lambda$166($quickZapOpen$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$18$0(MutableState $selectedCategoryId$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $selectedCategoryId$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$19$0(MutableState $quickZapOpen$delegate, MutableState $hudPokeSignal$delegate) {
        LiveTvScreen$lambda$166($quickZapOpen$delegate, false);
        LiveTvScreen$lambda$163($hudPokeSignal$delegate, LiveTvScreen$lambda$162($hudPokeSignal$delegate) + 1);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$20$0(Map $rememberedChannelByCategory, MutableState $playingChannelId$delegate, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, MutableState $playingCatchupProgram$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $quickZapOpen$delegate, MutableState $selectedCategoryId$delegate, MutableState $hudPokeSignal$delegate, EnrichedChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        $playingChannelId$delegate.setValue(channel.getId());
        $focusedChannelId$delegate.setValue(channel.getId());
        $epgPrefetchAnchorId$delegate.setValue(channel.getId());
        $playingCatchupProgram$delegate.setValue(null);
        $catchupPlaybackOffsetMs$delegate.setLongValue(0L);
        LiveTvScreen$lambda$166($quickZapOpen$delegate, false);
        $rememberedChannelByCategory.put(LiveTvScreen$lambda$14($selectedCategoryId$delegate), channel.getId());
        LiveTvScreen$lambda$163($hudPokeSignal$delegate, LiveTvScreen$lambda$162($hudPokeSignal$delegate) + 1);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$10$21$0(MutableState $guideChannel$delegate, MutableState $quickZapOpen$delegate, MutableState $guideOpenedFromQuickZap$delegate, MutableState $fullscreenGuideOpen$delegate, EnrichedChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        $guideChannel$delegate.setValue(channel);
        LiveTvScreen$lambda$166($quickZapOpen$delegate, false);
        LiveTvScreen$lambda$172($guideOpenedFromQuickZap$delegate, true);
        LiveTvScreen$lambda$144($fullscreenGuideOpen$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$12$0(State $fsProgress$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setAlpha(1.0f - LiveTvScreen$lambda$258$3($fsProgress$delegate));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$14(List $allDisplayChannels, HashMap $effectiveGuideNowNext, TvViewModel $viewModel, Set $hiddenGroupSet, final MutableState $searchOpen$delegate, final MutableState $selectedCategoryId$delegate, final MutableState $playingChannelId$delegate, final MutableState $focusedChannelId$delegate, final MutableState $epgPrefetchAnchorId$delegate, final Map $filteredChannelIndexById, final MutableIntState $guideWindowStart$delegate, final MutableIntState $guideWindowEnd$delegate, final List $filteredChannels, final MutableState $focusZone$delegate, final MutableState $visibleEnrichedState, final MutableLongState $lastGuideUserNavigationAt$delegate, final Map $rememberedChannelByCategory, final MutableIntState $focusSelectedChannelSignal$delegate, final FocusRequester $epgFocus, AnimatedVisibilityScope AnimatedVisibility, Composer $composer, int $changed) {
        LiveTvScreenKt$LiveTvScreen$50$14$1$1 liveTvScreenKt$LiveTvScreen$50$14$1$1;
        Object obj;
        Object obj2;
        Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
        ComposerKt.sourceInformation($composer, "C2998@145139L781,3012@145950L22,3013@145999L379,2995@144995L1398:LiveTvScreen.kt#qnwad4");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1978974016, $changed, -1, "com.arflix.tv.ui.screens.tv.live.LiveTvScreen.<anonymous>.<anonymous> (LiveTvScreen.kt:2995)");
        }
        HashMap map = $effectiveGuideNowNext;
        ComposerKt.sourceInformationMarkerStart($composer, 876488589, "CC(remember):LiveTvScreen.kt#9igjgp");
        boolean zChangedInstance = $composer.changedInstance($viewModel) | $composer.changed($hiddenGroupSet);
        Object objRememberedValue = $composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            liveTvScreenKt$LiveTvScreen$50$14$1$1 = new LiveTvScreenKt$LiveTvScreen$50$14$1$1($viewModel, $hiddenGroupSet, null);
            $composer.updateRememberedValue(liveTvScreenKt$LiveTvScreen$50$14$1$1);
        } else {
            liveTvScreenKt$LiveTvScreen$50$14$1$1 = objRememberedValue;
        }
        Function2 function2 = (Function2) liveTvScreenKt$LiveTvScreen$50$14$1$1;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 876513782, "CC(remember):LiveTvScreen.kt#9igjgp");
        boolean zChanged = $composer.changed($searchOpen$delegate);
        Object objRememberedValue2 = $composer.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            obj = new Function0() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return LiveTvScreenKt.LiveTvScreen$lambda$258$14$1$0($searchOpen$delegate);
                }
            };
            $composer.updateRememberedValue(obj);
        } else {
            obj = objRememberedValue2;
        }
        Function0 function0 = (Function0) obj;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 876515707, "CC(remember):LiveTvScreen.kt#9igjgp");
        boolean zChanged2 = $composer.changed($selectedCategoryId$delegate) | $composer.changed($playingChannelId$delegate) | $composer.changed($focusedChannelId$delegate) | $composer.changed($epgPrefetchAnchorId$delegate) | $composer.changed($searchOpen$delegate) | $composer.changed($filteredChannelIndexById) | $composer.changed($guideWindowStart$delegate) | $composer.changed($guideWindowEnd$delegate) | $composer.changed($filteredChannels) | $composer.changed($focusZone$delegate);
        Object objRememberedValue3 = $composer.rememberedValue();
        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            obj2 = new Function1() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return LiveTvScreenKt.LiveTvScreen$lambda$258$14$2$0($visibleEnrichedState, $selectedCategoryId$delegate, $playingChannelId$delegate, $focusedChannelId$delegate, $epgPrefetchAnchorId$delegate, $searchOpen$delegate, $lastGuideUserNavigationAt$delegate, $rememberedChannelByCategory, $filteredChannelIndexById, $filteredChannels, $guideWindowStart$delegate, $guideWindowEnd$delegate, $focusZone$delegate, $focusSelectedChannelSignal$delegate, $epgFocus, (EnrichedChannel) obj3);
                }
            };
            $composer.updateRememberedValue(obj2);
        } else {
            obj2 = objRememberedValue3;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        SearchOverlayKt.SearchOverlay($allDisplayChannels, map, function2, function0, (Function1) obj2, $composer, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$14$1$0(MutableState $searchOpen$delegate) {
        LiveTvScreen$lambda$126($searchOpen$delegate, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$14$2$0(MutableState $visibleEnrichedState, MutableState $selectedCategoryId$delegate, MutableState $playingChannelId$delegate, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, MutableState $searchOpen$delegate, MutableLongState $lastGuideUserNavigationAt$delegate, Map $rememberedChannelByCategory, Map $filteredChannelIndexById, List $filteredChannels, MutableIntState $guideWindowStart$delegate, MutableIntState $guideWindowEnd$delegate, MutableState $focusZone$delegate, MutableIntState $focusSelectedChannelSignal$delegate, FocusRequester $epgFocus, EnrichedChannel channel) {
        Intrinsics.checkNotNullParameter(channel, "channel");
        $selectedCategoryId$delegate.setValue(LiveCategoryKt.bestCategoryIdForChannel(channel, ((EnrichedChannels) $visibleEnrichedState.getValue()).getTree()));
        $playingChannelId$delegate.setValue(channel.getId());
        $focusedChannelId$delegate.setValue(channel.getId());
        $epgPrefetchAnchorId$delegate.setValue(channel.getId());
        LiveTvScreen$lambda$126($searchOpen$delegate, false);
        LiveTvScreen$focusChannelList($focusedChannelId$delegate, $playingChannelId$delegate, $lastGuideUserNavigationAt$delegate, $rememberedChannelByCategory, $filteredChannelIndexById, $filteredChannels, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $guideWindowStart$delegate, $guideWindowEnd$delegate, $focusZone$delegate, $focusSelectedChannelSignal$delegate, $epgFocus, channel.getId());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$16$0(MutableState $variantPickerChannel$delegate) {
        $variantPickerChannel$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$17$0(MutableState $visibleEnrichedState, Map $variantGroups, Map $rememberedChannelByCategory, MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $playingChannelId$delegate, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, MutableState $selectedCategoryId$delegate, MutableState $playingCatchupProgram$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $fullscreenGuideOpen$delegate, Map $filteredChannelIndexById, List $filteredChannels, MutableIntState $guideWindowStart$delegate, MutableIntState $guideWindowEnd$delegate, MutableState $focusZone$delegate, MutableIntState $focusSelectedChannelSignal$delegate, FocusRequester $epgFocus, EnrichedChannel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        LiveTvScreen$playVariant($visibleEnrichedState, $variantGroups, $rememberedChannelByCategory, $lastGuideUserNavigationAt$delegate, $playingChannelId$delegate, $focusedChannelId$delegate, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $playingCatchupProgram$delegate, $catchupPlaybackOffsetMs$delegate, $fullscreenGuideOpen$delegate, $filteredChannelIndexById, $filteredChannels, $guideWindowStart$delegate, $guideWindowEnd$delegate, $focusZone$delegate, $focusSelectedChannelSignal$delegate, $epgFocus, it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$18$0(MutableState $programActionDialog$delegate) {
        $programActionDialog$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$21(IptvProgram $program, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C3060@147943L305:LiveTvScreen.kt#qnwad4");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-107257039, $changed, -1, "com.arflix.tv.ui.screens.tv.live.LiveTvScreen.<anonymous>.<anonymous> (LiveTvScreen.kt:3060)");
            }
            TextKt.m7396Text4IGK_g($program.getTitle(), null, Color.INSTANCE.m3828getWhite0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m6086getEllipsisgIe3tQ8(), false, 2, 0, null, ArflixTypography.INSTANCE.getCardTitle(), $composer, RendererCapabilities.DECODER_SUPPORT_MASK, 1575984, 55290);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0207  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final kotlin.Unit LiveTvScreen$lambda$258$22(com.arflix.tv.ui.screens.tv.live.EnrichedChannel r49, com.arflix.tv.data.model.IptvProgram r50, boolean r51, androidx.compose.runtime.Composer r52, int r53) {
        /*
            Method dump skipped, instruction units count: 525
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.LiveTvScreen$lambda$258$22(com.arflix.tv.ui.screens.tv.live.EnrichedChannel, com.arflix.tv.data.model.IptvProgram, boolean, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$19(final CoroutineScope $coroutineScope, final TvViewModel $viewModel, final IptvProgram $program, final Function2 $onNavigateToDetails, final Function0 $onNavigateToSearch, final MutableState $programActionDialog$delegate, final MutableState $programActionSearching$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C3087@149257L724,3101@150005L332,3086@149211L1126:LiveTvScreen.kt#qnwad4");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(704423861, $changed, -1, "com.arflix.tv.ui.screens.tv.live.LiveTvScreen.<anonymous>.<anonymous> (LiveTvScreen.kt:3086)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1653498583, "CC(remember):LiveTvScreen.kt#9igjgp");
            boolean zChangedInstance = $composer.changedInstance($coroutineScope) | $composer.changedInstance($viewModel) | $composer.changed($program) | $composer.changed($onNavigateToDetails) | $composer.changed($onNavigateToSearch);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = new Function0() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$$ExternalSyntheticLambda19
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LiveTvScreenKt.LiveTvScreen$lambda$258$19$0$0($coroutineScope, $programActionDialog$delegate, $programActionSearching$delegate, $viewModel, $program, $onNavigateToDetails, $onNavigateToSearch);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = objRememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) obj, null, false, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1143007336, true, new Function3() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return LiveTvScreenKt.LiveTvScreen$lambda$258$19$1($programActionSearching$delegate, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$19$0$0(CoroutineScope $coroutineScope, MutableState $programActionDialog$delegate, MutableState $programActionSearching$delegate, TvViewModel $viewModel, IptvProgram $program, Function2 $onNavigateToDetails, Function0 $onNavigateToSearch) {
        $programActionDialog$delegate.setValue(null);
        LiveTvScreen$lambda$153($programActionSearching$delegate, true);
        BuildersKt__Builders_commonKt.launch$default($coroutineScope, null, null, new LiveTvScreenKt$LiveTvScreen$50$19$1$1$1($viewModel, $program, $onNavigateToDetails, $onNavigateToSearch, $programActionSearching$delegate, null), 3, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$19$1(MutableState $programActionSearching$delegate, RowScope TextButton, Composer $composer, int $changed) {
        String strStringResource;
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C3102@150053L262:LiveTvScreen.kt#qnwad4");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1143007336, $changed, -1, "com.arflix.tv.ui.screens.tv.live.LiveTvScreen.<anonymous>.<anonymous>.<anonymous> (LiveTvScreen.kt:3102)");
            }
            if (LiveTvScreen$lambda$152($programActionSearching$delegate)) {
                $composer.startReplaceGroup(77839246);
                $composer.endReplaceGroup();
                strStringResource = "Searching...";
            } else {
                $composer.startReplaceGroup(1081582307);
                ComposerKt.sourceInformation($composer, "3103@150142L43");
                strStringResource = StringResources_androidKt.stringResource(R.string.epg_search_sources, $composer, 0);
                $composer.endReplaceGroup();
            }
            TextKt.m7396Text4IGK_g(strStringResource, null, ColorKt.getPink(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getButton(), $composer, RendererCapabilities.DECODER_SUPPORT_MASK, 1572864, 65530);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$20(final List $visibleChannels, final MutableState $focusedChannelId$delegate, final MutableState $epgPrefetchAnchorId$delegate, final MutableState $selectedCategoryId$delegate, final MutableState $playingChannelId$delegate, final Map $filteredChannelIndexById, final MutableIntState $guideWindowStart$delegate, final MutableIntState $guideWindowEnd$delegate, final List $filteredChannels, final MutableState $focusZone$delegate, final EnrichedChannel $channel, final IptvProgram $program, final MutableState $programActionDialog$delegate, final Map $rememberedChannelByCategory, final MutableLongState $lastGuideUserNavigationAt$delegate, final MutableState $playingCatchupProgram$delegate, final MutableLongState $catchupPlaybackOffsetMs$delegate, final MutableState $fullscreenGuideOpen$delegate, final MutableIntState $focusSelectedChannelSignal$delegate, final FocusRequester $epgFocus, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C3111@150484L146,3110@150438L505:LiveTvScreen.kt#qnwad4");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1848900237, $changed, -1, "com.arflix.tv.ui.screens.tv.live.LiveTvScreen.<anonymous>.<anonymous> (LiveTvScreen.kt:3110)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1323335973, "CC(remember):LiveTvScreen.kt#9igjgp");
            boolean zChanged = $composer.changed($visibleChannels) | $composer.changed($focusedChannelId$delegate) | $composer.changed($epgPrefetchAnchorId$delegate) | $composer.changed($selectedCategoryId$delegate) | $composer.changed($playingChannelId$delegate) | $composer.changed($filteredChannelIndexById) | $composer.changed($guideWindowStart$delegate) | $composer.changed($guideWindowEnd$delegate) | $composer.changed($filteredChannels) | $composer.changed($focusZone$delegate) | $composer.changed($channel) | $composer.changed($program);
            Object objRememberedValue = $composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = new Function0() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LiveTvScreenKt.LiveTvScreen$lambda$258$20$0$0($channel, $program, $programActionDialog$delegate, $visibleChannels, $rememberedChannelByCategory, $lastGuideUserNavigationAt$delegate, $focusedChannelId$delegate, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $playingChannelId$delegate, $playingCatchupProgram$delegate, $catchupPlaybackOffsetMs$delegate, $fullscreenGuideOpen$delegate, $filteredChannelIndexById, $filteredChannels, $guideWindowStart$delegate, $guideWindowEnd$delegate, $focusZone$delegate, $focusSelectedChannelSignal$delegate, $epgFocus);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = objRememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) obj, null, false, null, null, null, null, null, null, ComposableSingletons$LiveTvScreenKt.INSTANCE.getLambda$598635862$app_sideloadDebug(), $composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LiveTvScreen$lambda$258$20$0$0(EnrichedChannel $channel, IptvProgram $program, MutableState $programActionDialog$delegate, List $visibleChannels, Map $rememberedChannelByCategory, MutableLongState $lastGuideUserNavigationAt$delegate, MutableState $focusedChannelId$delegate, MutableState $epgPrefetchAnchorId$delegate, MutableState $selectedCategoryId$delegate, MutableState $playingChannelId$delegate, MutableState $playingCatchupProgram$delegate, MutableLongState $catchupPlaybackOffsetMs$delegate, MutableState $fullscreenGuideOpen$delegate, Map $filteredChannelIndexById, List $filteredChannels, MutableIntState $guideWindowStart$delegate, MutableIntState $guideWindowEnd$delegate, MutableState $focusZone$delegate, MutableIntState $focusSelectedChannelSignal$delegate, FocusRequester $epgFocus) {
        $programActionDialog$delegate.setValue(null);
        LiveTvScreen$playProgramInMini($visibleChannels, $rememberedChannelByCategory, $lastGuideUserNavigationAt$delegate, $focusedChannelId$delegate, $epgPrefetchAnchorId$delegate, $selectedCategoryId$delegate, $playingChannelId$delegate, $playingCatchupProgram$delegate, $catchupPlaybackOffsetMs$delegate, $fullscreenGuideOpen$delegate, $filteredChannelIndexById, $filteredChannels, $guideWindowStart$delegate, $guideWindowEnd$delegate, $focusZone$delegate, $focusSelectedChannelSignal$delegate, $epgFocus, $channel, $program);
        return Unit.INSTANCE;
    }

    private static final Integer countForCategory$findCount(Sequence<LiveCategory> sequence, String $categoryId) {
        for (LiveCategory category : sequence) {
            if (Intrinsics.areEqual(category.getId(), $categoryId)) {
                return Integer.valueOf(category.getCount());
            }
            Integer childCount = countForCategory$findCount(CollectionsKt.asSequence(category.getChildren()), $categoryId);
            if (childCount != null) {
                return childCount;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer countForCategory(LiveCategoryTree $this$countForCategory, String categoryId) {
        return countForCategory$findCount(SequencesKt.flatten(SequencesKt.sequenceOf((Object[]) new Sequence[]{CollectionsKt.asSequence($this$countForCategory.getTop()), CollectionsKt.asSequence($this$countForCategory.getGlobal().getCategories()), CollectionsKt.asSequence($this$countForCategory.getCountries().getCategories()), CollectionsKt.asSequence($this$countForCategory.getAdult().getCategories()), CollectionsKt.asSequence($this$countForCategory.getHidden().getCategories())})), categoryId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String looseIptvGroupKey(String group) {
        String lowerCase = IptvGroupWhitespaceRegex.replace(IptvGroupPipeSpacingRegex.replace(StringsKt.trim((CharSequence) (group == null ? "" : group)).toString(), "|"), " ").toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return lowerCase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String compactIptvGroupKey(String group) throws IOException {
        CharSequence lowerCase = (group == null ? "" : group).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        CharSequence charSequence = lowerCase;
        Appendable sb = new StringBuilder();
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = charSequence.charAt(i);
            if (Character.isLetterOrDigit(cCharAt)) {
                sb.append(cCharAt);
            }
        }
        return ((StringBuilder) sb).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String classifyPlaybackError(PlaybackException error) {
        Integer numHttpResponseCode = httpResponseCode(error);
        if (numHttpResponseCode != null) {
            return "provider returned HTTP " + numHttpResponseCode.intValue();
        }
        String errorCodeName = error.getErrorCodeName();
        Intrinsics.checkNotNullExpressionValue(errorCodeName, "getErrorCodeName(...)");
        String name = errorCodeName.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(name, "toLowerCase(...)");
        if (StringsKt.contains$default((CharSequence) name, (CharSequence) ProfilingTraceData.TRUNCATION_REASON_TIMEOUT, false, 2, (Object) null)) {
            return "network timeout";
        }
        if (StringsKt.contains$default((CharSequence) name, (CharSequence) "network", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) name, (CharSequence) "io", false, 2, (Object) null)) {
            return "network or provider error";
        }
        if (StringsKt.contains$default((CharSequence) name, (CharSequence) "parser", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) name, (CharSequence) "manifest", false, 2, (Object) null)) {
            return "stream format issue";
        }
        if (StringsKt.contains$default((CharSequence) name, (CharSequence) "decoder", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) name, (CharSequence) "audio", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) name, (CharSequence) "video", false, 2, (Object) null)) {
            return "device codec issue";
        }
        return "source did not start";
    }

    private static final LiveTvBufferProfile buildLiveTvBufferProfile(int memoryClassMb, boolean isLowRamDevice) {
        int heapMb = RangesKt.coerceAtLeast(memoryClassMb, 256);
        boolean constrained = isLowRamDevice || heapMb <= 384;
        int targetMb = constrained ? 32 : 48;
        return new LiveTvBufferProfile(15000, 30000, 1000, constrained ? 2500 : 3000, targetMb * 1024 * 1024, 5000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer httpResponseCode(PlaybackException error) {
        for (PlaybackException cause = error; cause != null; cause = cause.getCause()) {
            if (cause instanceof HttpDataSource.InvalidResponseCodeException) {
                return Integer.valueOf(((HttpDataSource.InvalidResponseCodeException) cause).responseCode);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String redactPlaybackUrl(String url) {
        String withoutQuerySecrets = LiveTvScreenRegexes.INSTANCE.getQUERY_SECRETS().replace(url, new Function1() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$$ExternalSyntheticLambda25
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LiveTvScreenKt.redactPlaybackUrl$lambda$0((MatchResult) obj);
            }
        });
        return StringsKt.take(LiveTvScreenRegexes.INSTANCE.getIPTV_URL_REDACT_REGEX().replace(withoutQuerySecrets, new Function1() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$$ExternalSyntheticLambda26
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LiveTvScreenKt.redactPlaybackUrl$lambda$1((MatchResult) obj);
            }
        }), GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence redactPlaybackUrl$lambda$0(MatchResult match) {
        Intrinsics.checkNotNullParameter(match, "match");
        return ((Object) match.getGroupValues().get(1)) + "***";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence redactPlaybackUrl$lambda$1(MatchResult match) {
        Intrinsics.checkNotNullParameter(match, "match");
        return ((Object) match.getGroupValues().get(1)) + "***/***" + ((Object) match.getGroupValues().get(4));
    }

    private static final Activity findActivity(Context $this$findActivity) {
        while (!($this$findActivity instanceof Activity)) {
            if (!($this$findActivity instanceof ContextWrapper)) {
                return null;
            }
            Context baseContext = ((ContextWrapper) $this$findActivity).getBaseContext();
            Intrinsics.checkNotNullExpressionValue(baseContext, "getBaseContext(...)");
            $this$findActivity = baseContext;
        }
        return (Activity) $this$findActivity;
    }
}
