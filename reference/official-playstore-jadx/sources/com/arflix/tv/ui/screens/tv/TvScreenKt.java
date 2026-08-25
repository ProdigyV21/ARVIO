package com.arflix.tv.ui.screens.tv;

import android.app.ActivityManager;
import android.content.Context;
import android.os.SystemClock;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.KeyboardArrowDownKt;
import androidx.compose.material.icons.filled.KeyboardArrowUpKt;
import androidx.compose.material.icons.filled.LiveTvKt;
import androidx.compose.material.icons.filled.StarKt;
import androidx.compose.material.icons.filled.StarOutlineKt;
import androidx.compose.material.icons.filled.VisibilityOffKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotLongStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.fragment.app.a2;
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
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.ui.PlayerView;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.data.model.DrmInfo;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.ui.focus.ArvioDpadFocusKt;
import com.arflix.tv.ui.screens.tv.TvScreenKt;
import com.arflix.tv.ui.theme.AnimationConstants;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.util.ClearKeyUtil;
import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import com.google.common.util.concurrent.r0;
import io.ktor.http.ContentDisposition;
import j$.time.Instant;
import j$.time.ZoneId;
import j$.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Ð\u0001\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\t\u001a-\u0010\u0006\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0002¢\u0006\u0004\b\u0006\u0010\u0007\u001a9\u0010\u000b\u001a\u0004\u0018\u00010\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00002\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00000\tH\u0002¢\u0006\u0004\b\u000b\u0010\f\u001a\u0013\u0010\u000e\u001a\u00020\r*\u00020\u0002H\u0002¢\u0006\u0004\b\u000e\u0010\u000f\u001a#\u0010\u0013\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001f\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u001f\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\rH\u0002¢\u0006\u0004\b\u001f\u0010 \u001a\u009d\u0001\u0010/\u001a\u00020(2\b\b\u0002\u0010\"\u001a\u00020!2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00022\u000e\b\u0002\u0010)\u001a\b\u0012\u0004\u0012\u00020(0'2\u000e\b\u0002\u0010*\u001a\b\u0012\u0004\u0012\u00020(0'2\u000e\b\u0002\u0010+\u001a\b\u0012\u0004\u0012\u00020(0'2\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020(0'2\u000e\b\u0002\u0010-\u001a\b\u0012\u0004\u0012\u00020(0'2\u000e\b\u0002\u0010.\u001a\b\u0012\u0004\u0012\u00020(0'H\u0007¢\u0006\u0004\b/\u00100\u001a \u00104\u001a\u00020(2\u0006\u00102\u001a\u0002012\u0006\u00103\u001a\u00020\u0005H\u0082@¢\u0006\u0004\b4\u00105\u001a\u0081\u0002\u0010G\u001a\u00020(2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00002\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u0002062\u0006\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00020\r2\u0006\u0010:\u001a\u0002012\u0014\b\u0002\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020(0;2\u0014\b\u0002\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020(0;2\b\b\u0002\u0010>\u001a\u00020\u00052\u000e\b\u0002\u0010?\u001a\b\u0012\u0004\u0012\u00020(0'2\u0014\b\u0002\u0010@\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020(0;2\u0014\b\u0002\u0010A\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020(0;2\u0014\b\u0002\u0010B\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020(0;2\u0014\b\u0002\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020(0;2\u0014\b\u0002\u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020(0;2\b\b\u0002\u0010F\u001a\u00020EH\u0003¢\u0006\u0004\bG\u0010H\u001a±\u0001\u0010N\u001a\u00020(2\u0006\u0010I\u001a\u00020\u00022\u0006\u00109\u001a\u00020\r2\u0006\u0010J\u001a\u00020\r2\b\b\u0002\u0010K\u001a\u00020\r2\u000e\b\u0002\u0010L\u001a\b\u0012\u0004\u0012\u00020(0'2\u000e\b\u0002\u0010M\u001a\b\u0012\u0004\u0012\u00020(0'2\u000e\b\u0002\u0010?\u001a\b\u0012\u0004\u0012\u00020(0'2\u000e\b\u0002\u0010@\u001a\b\u0012\u0004\u0012\u00020(0'2\u000e\b\u0002\u0010A\u001a\b\u0012\u0004\u0012\u00020(0'2\u000e\b\u0002\u0010B\u001a\b\u0012\u0004\u0012\u00020(0'2\u000e\b\u0002\u0010C\u001a\b\u0012\u0004\u0012\u00020(0'2\u000e\b\u0002\u0010D\u001a\b\u0012\u0004\u0012\u00020(0'H\u0003¢\u0006\u0004\bN\u0010O\u001a5\u0010U\u001a\u00020(2\u0006\u0010P\u001a\u00020\u00012\b\u0010R\u001a\u0004\u0018\u00010Q2\b\u0010S\u001a\u0004\u0018\u00010Q2\b\b\u0002\u0010T\u001a\u00020\rH\u0003¢\u0006\u0004\bU\u0010V\u001a\u0091\u0001\u0010`\u001a\u00020(2\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u0012\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020X0\t2\u0006\u0010Z\u001a\u00020\r2\u0006\u0010[\u001a\u00020\u00052\u0006\u0010\\\u001a\u00020\r2\u0006\u0010]\u001a\u00020\r2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\f\u0010^\u001a\b\u0012\u0004\u0012\u00020\u0002062\u0006\u0010:\u001a\u0002012\u0014\b\u0002\u0010_\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020(0;2\b\b\u0002\u0010F\u001a\u00020EH\u0003¢\u0006\u0004\b`\u0010a\u001a1\u0010f\u001a\u00020(2\u0006\u0010c\u001a\u00020b2\u0006\u0010d\u001a\u00020b2\u0006\u0010e\u001a\u00020b2\b\b\u0002\u0010T\u001a\u00020\rH\u0003¢\u0006\u0004\bf\u0010g\u001a\u0097\u0001\u0010o\u001a\u00020(2\u0006\u0010P\u001a\u00020\u00012\f\u0010h\u001a\b\u0012\u0004\u0012\u00020Q0\u00002\b\u0010R\u001a\u0004\u0018\u00010Q2\f\u0010i\u001a\b\u0012\u0004\u0012\u00020Q0\u00002\u0006\u00109\u001a\u00020\r2\u0006\u0010j\u001a\u00020\r2\u0006\u0010k\u001a\u00020\r2\u0006\u0010l\u001a\u00020\r2\u0006\u0010c\u001a\u00020b2\u0006\u0010e\u001a\u00020b2\u0006\u0010d\u001a\u00020b2\u0006\u0010n\u001a\u00020m2\b\b\u0002\u0010T\u001a\u00020\r2\u000e\b\u0002\u0010L\u001a\b\u0012\u0004\u0012\u00020(0'H\u0003¢\u0006\u0004\bo\u0010p\u001aE\u0010s\u001a\u00020(2\b\u0010R\u001a\u0004\u0018\u00010Q2\b\u0010S\u001a\u0004\u0018\u00010Q2\u0006\u0010d\u001a\u00020b2\u0006\u0010q\u001a\u00020\r2\u0006\u0010r\u001a\u00020\r2\b\b\u0002\u0010F\u001a\u00020EH\u0003¢\u0006\u0004\bs\u0010t\u001aq\u0010u\u001a\u00020(2\f\u0010h\u001a\b\u0012\u0004\u0012\u00020Q0\u00002\b\u0010R\u001a\u0004\u0018\u00010Q2\f\u0010i\u001a\b\u0012\u0004\u0012\u00020Q0\u00002\u0006\u0010c\u001a\u00020b2\u0006\u0010e\u001a\u00020b2\u0006\u0010d\u001a\u00020b2\u0006\u0010n\u001a\u00020m2\u0006\u0010q\u001a\u00020\r2\b\b\u0002\u0010r\u001a\u00020\r2\b\b\u0002\u0010F\u001a\u00020EH\u0003¢\u0006\u0004\bu\u0010v\u001aK\u0010x\u001a\b\u0012\u0004\u0012\u00020w0\u00002\f\u0010h\u001a\b\u0012\u0004\u0012\u00020Q0\u00002\b\u0010R\u001a\u0004\u0018\u00010Q2\f\u0010i\u001a\b\u0012\u0004\u0012\u00020Q0\u00002\u0006\u0010c\u001a\u00020b2\u0006\u0010e\u001a\u00020bH\u0002¢\u0006\u0004\bx\u0010y\u001a#\u0010{\u001a\b\u0012\u0004\u0012\u00020w0\u00002\f\u0010z\u001a\b\u0012\u0004\u0012\u00020w0\u0000H\u0002¢\u0006\u0004\b{\u0010|\u001a<\u0010\u0084\u0001\u001a\u00020(2\u0006\u0010}\u001a\u00020\u00022\u0006\u0010\u007f\u001a\u00020~2\n\b\u0002\u0010\u0081\u0001\u001a\u00030\u0080\u00012\f\u0010L\u001a\b\u0012\u0004\u0012\u00020(0'H\u0003¢\u0006\u0006\b\u0082\u0001\u0010\u0083\u0001\u001a\u0012\u0010\u0085\u0001\u001a\u00020(H\u0003¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001\u001a\u001b\u0010\u0088\u0001\u001a\u00020\u00022\u0007\u0010\u0087\u0001\u001a\u00020bH\u0002¢\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001\"\"\u0010\u008c\u0001\u001a\r \u008b\u0001*\u0005\u0018\u00010\u008a\u00010\u008a\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008c\u0001\u0010\u008d\u0001¨\u0006ª\u0001²\u0006\u000e\u0010\u008f\u0001\u001a\u00030\u008e\u00018\nX\u008a\u0084\u0002²\u0006\u000f\u0010\u0090\u0001\u001a\u00020\u00128\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0091\u0001\u001a\u00020\u00058\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0092\u0001\u001a\u00020\u00058\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0093\u0001\u001a\u00020\u00058\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00028\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0094\u0001\u001a\u00020\r8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0095\u0001\u001a\u00020\r8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0096\u0001\u001a\u00020\r8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0097\u0001\u001a\u00020b8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010\u0098\u0001\u001a\u0004\u0018\u00010b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0099\u0001\u001a\u00020b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u009a\u0001\u001a\u00020b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u009b\u0001\u001a\u00020\r8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u009c\u0001\u001a\u00020\r8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u009d\u0001\u001a\u00020\r8\n@\nX\u008a\u008e\u0002²\u0006\u0012\u00107\u001a\b\u0012\u0004\u0012\u00020\u0002068\nX\u008a\u0084\u0002²\u0006\u0012\u0010^\u001a\b\u0012\u0004\u0012\u00020\u0002068\nX\u008a\u0084\u0002²\u0006\u000f\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u00028\nX\u008a\u0084\u0002²\u0006\u000f\u0010\u009f\u0001\u001a\u00020\r8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010 \u0001\u001a\u0004\u0018\u00010\u00198\n@\nX\u008a\u008e\u0002²\u0006\u0012\u0010¢\u0001\u001a\u0005\u0018\u00010¡\u00018\n@\nX\u008a\u008e\u0002²\u0006\u0012\u0010£\u0001\u001a\u0005\u0018\u00010¡\u00018\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¤\u0001\u001a\u0004\u0018\u00010\u00028\nX\u008a\u0084\u0002²\u0006\u0011\u0010¥\u0001\u001a\u0004\u0018\u00010\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¦\u0001\u001a\u00020\u00058\n@\nX\u008a\u008e\u0002²\u0006\r\u0010§\u0001\u001a\u00020m8\nX\u008a\u0084\u0002²\u0006\u000f\u0010¨\u0001\u001a\u00020\r8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010d\u001a\u00020b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010©\u0001\u001a\u00020\r8\n@\nX\u008a\u008e\u0002"}, d2 = {"", "Lcom/arflix/tv/data/model/IptvChannel;", "", "selectedChannelId", "playingChannelId", "", "preferredIndexFor", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)I", "groups", "", "channelsByGroup", "preferredStartupGroup", "(Ljava/util/List;Ljava/util/Map;)Ljava/lang/String;", "", "isPriorityGuideGroup", "(Ljava/lang/String;)Z", "hasGroups", "hasChannels", "Lcom/arflix/tv/ui/screens/tv/TvFocusZone;", "toTvFocusZone", "(Ljava/lang/String;ZZ)Lcom/arflix/tv/ui/screens/tv/TvFocusZone;", "Landroid/content/Context;", "context", "Landroidx/media3/exoplayer/source/DefaultMediaSourceFactory;", "mediaSourceFactory", "Landroidx/media3/exoplayer/ExoPlayer;", "createTvExoPlayer", "(Landroid/content/Context;Landroidx/media3/exoplayer/source/DefaultMediaSourceFactory;)Landroidx/media3/exoplayer/ExoPlayer;", "memoryClassMb", "isLowRamDevice", "Lcom/arflix/tv/ui/screens/tv/LegacyTvBufferProfile;", "buildLegacyTvBufferProfile", "(IZ)Lcom/arflix/tv/ui/screens/tv/LegacyTvBufferProfile;", "Lcom/arflix/tv/ui/screens/tv/TvViewModel;", "viewModel", "Lcom/arflix/tv/data/model/Profile;", "currentProfile", "initialChannelId", "initialStreamUrl", "Lkotlin/Function0;", "Lx6/t0;", "onNavigateToHome", "onNavigateToSearch", "onNavigateToWatchlist", "onNavigateToSettings", "onSwitchProfile", "onBack", "TvScreen", "(Lcom/arflix/tv/ui/screens/tv/TvViewModel;Lcom/arflix/tv/data/model/Profile;Ljava/lang/String;Ljava/lang/String;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/foundation/lazy/LazyListState;", "state", "targetIndex", "smoothScrollTo", "(Landroidx/compose/foundation/lazy/LazyListState;ILd7/d;)Ljava/lang/Object;", "", "favoriteGroups", "focusedGroupIndex", "isFocused", "listState", "Lkotlin/Function1;", "onGroupClick", "onGroupLongPress", "showMenuForIndex", "onDismissMenu", "onToggleFavorite", "onToggleHidden", "onMoveUp", "onMoveToTop", "onMoveDown", "Landroidx/compose/ui/Modifier;", "modifier", "CategoryRail", "(Ljava/util/List;Ljava/util/Set;IZLandroidx/compose/foundation/lazy/LazyListState;Lr7/l;Lr7/l;ILr7/a;Lr7/l;Lr7/l;Lr7/l;Lr7/l;Lr7/l;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;III)V", ContentDisposition.Parameters.Name, "isFavorite", "showMenu", "onClick", "onLongPress", "GroupRailItem", "(Ljava/lang/String;ZZZLr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;III)V", "channel", "Lcom/arflix/tv/data/model/IptvProgram;", "nowProgram", "nextProgram", "isMobile", "FullscreenEpgOverlay", "(Lcom/arflix/tv/data/model/IptvChannel;Lcom/arflix/tv/data/model/IptvProgram;Lcom/arflix/tv/data/model/IptvProgram;ZLandroidx/compose/runtime/Composer;II)V", "channels", "Lcom/arflix/tv/data/model/IptvNowNext;", "nowNext", "isLoading", "focusedChannelIndex", "guideFocused", "fastNavigating", "favoriteChannels", "onChannelClick", "GuidePanel", "(Ljava/util/List;Ljava/util/Map;ZIZZLjava/lang/String;Ljava/util/Set;Landroidx/compose/foundation/lazy/LazyListState;Lr7/l;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;III)V", "", "windowStart", "now", "windowEnd", "GuideTimeHeader", "(JJJZLandroidx/compose/runtime/Composer;II)V", "recentPrograms", "upcomingPrograms", "isPlaying", "isFavoriteChannel", "showDetailedTimeline", "", "nowRatio", "GuideChannelRow", "(Lcom/arflix/tv/data/model/IptvChannel;Ljava/util/List;Lcom/arflix/tv/data/model/IptvProgram;Ljava/util/List;ZZZZJJJFZLr7/a;Landroidx/compose/runtime/Composer;III)V", "isRowFocused", "isRowPlaying", "CompactGuideLane", "(Lcom/arflix/tv/data/model/IptvProgram;Lcom/arflix/tv/data/model/IptvProgram;JZZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "TimelineProgramLane", "(Ljava/util/List;Lcom/arflix/tv/data/model/IptvProgram;Ljava/util/List;JJJFZZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "Lcom/arflix/tv/ui/screens/tv/ProgramSegment;", "buildTimelineSegments", "(Ljava/util/List;Lcom/arflix/tv/data/model/IptvProgram;Ljava/util/List;JJ)Ljava/util/List;", "items", "mergeAdjacentTimelineSegments", "(Ljava/util/List;)Ljava/util/List;", "label", "Landroidx/compose/ui/graphics/vector/ImageVector;", "icon", "Landroidx/compose/ui/graphics/Color;", "iconTint", "FocusableMenuItem-cf5BqRc", "(Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;JLr7/a;Landroidx/compose/runtime/Composer;II)V", "FocusableMenuItem", "NotConfiguredPanel", "(Landroidx/compose/runtime/Composer;I)V", "utcMillis", "formatProgramTime", "(J)Ljava/lang/String;", "j$/time/format/DateTimeFormatter", "kotlin.jvm.PlatformType", "programTimeFormatter", "Lj$/time/format/DateTimeFormatter;", "Lcom/arflix/tv/ui/screens/tv/TvUiState;", "uiState", "focusZone", "sidebarFocusIndex", "groupIndex", "channelIndex", "showGroupContextMenu", "isFullScreen", "showFullscreenOverlay", "fullscreenOverlayTrigger", "centerDownAtMs", "lastNavigationAt", "restoredSessionAt", "startupDefaultApplied", "isFastNavigating", "rootHasFocus", "latestEpgAnchorChannelId", "isPlayerReleased", "exoPlayer", "Landroidx/media3/ui/PlayerView;", "miniPlayerView", "fullPlayerView", "currentStreamUrl", "lastPreparedStreamUrl", "playerRetryCount", "categoryRailAlpha", "ignoreMenuSelectUntilRelease", "focused", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TvScreenKt {
    private static final DateTimeFormatter programTimeFormatter = DateTimeFormatter.ofPattern("h:mm a");

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[androidx.lifecycle.p.values().length];
            try {
                iArr[androidx.lifecycle.p.ON_PAUSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[androidx.lifecycle.p.ON_RESUME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J\t\u0010\u0016\u001a\u00020\bHÆ\u0003J@\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0011R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0011¨\u0006\u001f"}, d2 = {"com/arflix/tv/ui/screens/tv/TvScreenKt$buildTimelineSegments$TimedProgram", "", TtmlNode.START, "", TtmlNode.END, "program", "Lcom/arflix/tv/data/model/IptvProgram;", "isNow", "", "isPast", "<init>", "(JJLcom/arflix/tv/data/model/IptvProgram;ZZ)V", "getStart", "()J", "getEnd", "getProgram", "()Lcom/arflix/tv/data/model/IptvProgram;", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "(JJLcom/arflix/tv/data/model/IptvProgram;ZZ)Lcom/arflix/tv/ui/screens/tv/TvScreenKt$buildTimelineSegments$TimedProgram;", "equals", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TimedProgram {
        private final long end;
        private final boolean isNow;
        private final boolean isPast;
        private final IptvProgram program;
        private final long start;

        public TimedProgram(long j10, long j11, IptvProgram iptvProgram, boolean z, boolean z5) {
            this.start = j10;
            this.end = j11;
            this.program = iptvProgram;
            this.isNow = z;
            this.isPast = z5;
        }

        public static /* synthetic */ TimedProgram copy$default(TimedProgram timedProgram, long j10, long j11, IptvProgram iptvProgram, boolean z, boolean z5, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                j10 = timedProgram.start;
            }
            long j12 = j10;
            if ((i10 & 2) != 0) {
                j11 = timedProgram.end;
            }
            long j13 = j11;
            if ((i10 & 4) != 0) {
                iptvProgram = timedProgram.program;
            }
            IptvProgram iptvProgram2 = iptvProgram;
            if ((i10 & 8) != 0) {
                z = timedProgram.isNow;
            }
            boolean z10 = z;
            if ((i10 & 16) != 0) {
                z5 = timedProgram.isPast;
            }
            return timedProgram.copy(j12, j13, iptvProgram2, z10, z5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getStart() {
            return this.start;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getEnd() {
            return this.end;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final IptvProgram getProgram() {
            return this.program;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsNow() {
            return this.isNow;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getIsPast() {
            return this.isPast;
        }

        public final TimedProgram copy(long start, long end, IptvProgram program, boolean isNow, boolean isPast) {
            return new TimedProgram(start, end, program, isNow, isPast);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TimedProgram)) {
                return false;
            }
            TimedProgram timedProgram = (TimedProgram) other;
            return this.start == timedProgram.start && this.end == timedProgram.end && kotlin.jvm.internal.p.a(this.program, timedProgram.program) && this.isNow == timedProgram.isNow && this.isPast == timedProgram.isPast;
        }

        public final long getEnd() {
            return this.end;
        }

        public final IptvProgram getProgram() {
            return this.program;
        }

        public final long getStart() {
            return this.start;
        }

        public int hashCode() {
            long j10 = this.start;
            long j11 = this.end;
            return ((((this.program.hashCode() + (((((int) (j10 ^ (j10 >>> 32))) * 31) + ((int) ((j11 >>> 32) ^ j11))) * 31)) * 31) + (this.isNow ? 1231 : 1237)) * 31) + (this.isPast ? 1231 : 1237);
        }

        public final boolean isNow() {
            return this.isNow;
        }

        public final boolean isPast() {
            return this.isPast;
        }

        public String toString() {
            long j10 = this.start;
            long j11 = this.end;
            IptvProgram iptvProgram = this.program;
            boolean z = this.isNow;
            boolean z5 = this.isPast;
            StringBuilder sbR = androidx.compose.material3.d.r(j10, "TimedProgram(start=", ", end=");
            sbR.append(j11);
            sbR.append(", program=");
            sbR.append(iptvProgram);
            sbR.append(", isNow=");
            sbR.append(z);
            sbR.append(", isPast=");
            sbR.append(z5);
            sbR.append(")");
            return sbR.toString();
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvScreenKt$smoothScrollTo$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvScreenKt", f = "TvScreen.kt", l = {1517, 1519, 1521}, m = "smoothScrollTo", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        int I$6;
        int I$7;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TvScreenKt.smoothScrollTo(null, 0, this);
        }
    }

    private static final void CategoryRail(final List<String> list, final Set<String> set, final int i10, final boolean z, final LazyListState lazyListState, r7.l<? super Integer, t0> lVar, r7.l<? super Integer, t0> lVar2, int i11, r7.a<t0> aVar, r7.l<? super String, t0> lVar3, r7.l<? super String, t0> lVar4, r7.l<? super String, t0> lVar5, r7.l<? super String, t0> lVar6, r7.l<? super String, t0> lVar7, Modifier modifier, Composer composer, final int i12, final int i13, final int i14) {
        int i15;
        Set<String> set2;
        int i16;
        LazyListState lazyListState2;
        r7.l<? super Integer, t0> lVar8;
        r7.l<? super Integer, t0> lVar9;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        final r7.l<? super Integer, t0> lVar10;
        final r7.l<? super Integer, t0> lVar11;
        r7.a<t0> aVar2;
        final r7.l<? super String, t0> lVar12;
        r7.l<? super String, t0> lVar13;
        r7.l<? super String, t0> lVar14;
        r7.l<? super String, t0> lVar15;
        r7.l<? super String, t0> lVar16;
        final r7.a<t0> aVar3;
        final r7.l<? super String, t0> lVar17;
        final r7.l<? super String, t0> lVar18;
        final r7.l<? super String, t0> lVar19;
        final r7.l<? super String, t0> lVar20;
        final Modifier modifier2;
        Composer composer2;
        final int i24;
        final r7.l<? super Integer, t0> lVar21;
        final r7.l<? super Integer, t0> lVar22;
        final r7.a<t0> aVar4;
        final r7.l<? super String, t0> lVar23;
        final r7.l<? super String, t0> lVar24;
        final r7.l<? super String, t0> lVar25;
        final r7.l<? super String, t0> lVar26;
        final r7.l<? super String, t0> lVar27;
        Composer composerStartRestartGroup = composer.startRestartGroup(1637799777);
        if ((i12 & 6) == 0) {
            i15 = (composerStartRestartGroup.changed(list) ? 4 : 2) | i12;
        } else {
            i15 = i12;
        }
        if ((i12 & 48) == 0) {
            set2 = set;
            i15 |= composerStartRestartGroup.changed(set2) ? 32 : 16;
        } else {
            set2 = set;
        }
        if ((i12 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i16 = i10;
            i15 |= composerStartRestartGroup.changed(i16) ? 256 : 128;
        } else {
            i16 = i10;
        }
        if ((i12 & 3072) == 0) {
            i15 |= composerStartRestartGroup.changed(z) ? 2048 : 1024;
        }
        if ((i12 & 24576) == 0) {
            lazyListState2 = lazyListState;
            i15 |= composerStartRestartGroup.changed(lazyListState2) ? 16384 : 8192;
        } else {
            lazyListState2 = lazyListState;
        }
        int i25 = i14 & 32;
        if (i25 != 0) {
            i15 |= 196608;
            lVar8 = lVar;
        } else {
            lVar8 = lVar;
            if ((i12 & 196608) == 0) {
                i15 |= composerStartRestartGroup.changedInstance(lVar8) ? 131072 : 65536;
            }
        }
        int i26 = i14 & 64;
        if (i26 != 0) {
            i15 |= 1572864;
            lVar9 = lVar2;
        } else {
            lVar9 = lVar2;
            if ((i12 & 1572864) == 0) {
                i15 |= composerStartRestartGroup.changedInstance(lVar9) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
            }
        }
        int i27 = i14 & 128;
        if (i27 != 0) {
            i15 |= 12582912;
            i17 = i11;
        } else {
            i17 = i11;
            if ((i12 & 12582912) == 0) {
                i15 |= composerStartRestartGroup.changed(i17) ? 8388608 : 4194304;
            }
        }
        int i28 = i14 & 256;
        if (i28 != 0) {
            i15 |= 100663296;
        } else if ((i12 & 100663296) == 0) {
            i15 |= composerStartRestartGroup.changedInstance(aVar) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        int i29 = i14 & 512;
        if (i29 != 0) {
            i15 |= 805306368;
        } else if ((i12 & 805306368) == 0) {
            i15 |= composerStartRestartGroup.changedInstance(lVar3) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        int i30 = i14 & 1024;
        if (i30 != 0) {
            i19 = i13 | 6;
            i18 = i30;
        } else if ((i13 & 6) == 0) {
            i18 = i30;
            i19 = i13 | (composerStartRestartGroup.changedInstance(lVar4) ? 4 : 2);
        } else {
            i18 = i30;
            i19 = i13;
        }
        int i31 = i14 & 2048;
        if (i31 != 0) {
            i19 |= 48;
            i20 = i31;
        } else if ((i13 & 48) == 0) {
            i20 = i31;
            i19 |= composerStartRestartGroup.changedInstance(lVar5) ? 32 : 16;
        } else {
            i20 = i31;
        }
        int i32 = i19;
        int i33 = i14 & 4096;
        if (i33 != 0) {
            i21 = i32 | RendererCapabilities.DECODER_SUPPORT_MASK;
        } else {
            int i34 = i32;
            if ((i13 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                i34 |= composerStartRestartGroup.changedInstance(lVar6) ? 256 : 128;
            }
            i21 = i34;
        }
        int i35 = i14 & 8192;
        if (i35 != 0) {
            i22 = i21 | 3072;
        } else {
            int i36 = i21;
            if ((i13 & 3072) == 0) {
                i22 = i36 | (composerStartRestartGroup.changedInstance(lVar7) ? 2048 : 1024);
            } else {
                i22 = i36;
            }
        }
        int i37 = i14 & 16384;
        if (i37 != 0) {
            i23 = i22 | 24576;
        } else {
            int i38 = i22;
            if ((i13 & 24576) == 0) {
                i23 = i38 | (composerStartRestartGroup.changed(modifier) ? 16384 : 8192);
            } else {
                i23 = i38;
            }
        }
        if ((i15 & 306783379) == 306783378 && (i23 & 9363) == 9362 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            lVar23 = lVar3;
            lVar25 = lVar5;
            lVar27 = lVar7;
            modifier2 = modifier;
            i24 = i17;
            lVar22 = lVar9;
            lVar21 = lVar8;
            composer2 = composerStartRestartGroup;
            aVar4 = aVar;
            lVar24 = lVar4;
            lVar26 = lVar6;
        } else {
            if (i25 != 0) {
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new j0(5);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                lVar10 = (r7.l) objRememberedValue;
            } else {
                lVar10 = lVar8;
            }
            if (i26 != 0) {
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new j0(6);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                lVar11 = (r7.l) objRememberedValue2;
            } else {
                lVar11 = lVar9;
            }
            final int i39 = i27 != 0 ? -1 : i17;
            if (i28 != 0) {
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new g(7);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                aVar2 = (r7.a) objRememberedValue3;
            } else {
                aVar2 = aVar;
            }
            if (i29 != 0) {
                Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new j0(7);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                lVar12 = (r7.l) objRememberedValue4;
            } else {
                lVar12 = lVar3;
            }
            if (i18 != 0) {
                Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue5 = new j0(8);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                lVar13 = (r7.l) objRememberedValue5;
            } else {
                lVar13 = lVar4;
            }
            if (i20 != 0) {
                Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = new j0(9);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
                }
                lVar14 = (r7.l) objRememberedValue6;
            } else {
                lVar14 = lVar5;
            }
            if (i33 != 0) {
                Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue7 = new j0(10);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue7);
                }
                lVar15 = (r7.l) objRememberedValue7;
            } else {
                lVar15 = lVar6;
            }
            if (i35 != 0) {
                Object objRememberedValue8 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue8 = new j0(11);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue8);
                }
                lVar16 = (r7.l) objRememberedValue8;
            } else {
                lVar16 = lVar7;
            }
            Modifier modifier3 = i37 != 0 ? Modifier.INSTANCE : modifier;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1637799777, i15, i23, "com.arflix.tv.ui.screens.tv.CategoryRail (TvScreen.kt:1543)");
            }
            r7.a<t0> aVar5 = aVar2;
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = Arrangement.INSTANCE.m441spacedBy0680j_4(Dp.m5678constructorimpl(1));
            Modifier modifierArvioDpadFocusGroup$default = ArvioDpadFocusKt.arvioDpadFocusGroup$default(modifier3, null, false, 3, null);
            boolean z5 = ((i15 & 14) == 4) | ((i15 & 7168) == 2048) | ((i15 & 896) == 256) | ((i15 & 112) == 32) | ((29360128 & i15) == 8388608) | ((458752 & i15) == 131072) | ((3670016 & i15) == 1048576) | ((234881024 & i15) == 67108864) | ((1879048192 & i15) == 536870912) | ((i23 & 14) == 4) | ((i23 & 112) == 32) | ((i23 & 896) == 256) | ((i23 & 7168) == 2048);
            Object objRememberedValue9 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                aVar3 = aVar5;
                lVar17 = lVar13;
                lVar18 = lVar14;
                lVar19 = lVar15;
                final Set<String> set3 = set2;
                lVar20 = lVar16;
                final int i40 = i16;
                r7.l lVar28 = new r7.l() { // from class: com.arflix.tv.ui.screens.tv.u
                    @Override // r7.l
                    public final Object invoke(Object obj) {
                        return TvScreenKt.CategoryRail$lambda$8$0(list, z, i40, set3, i39, lVar10, lVar11, aVar3, lVar12, lVar17, lVar18, lVar19, lVar20, (LazyListScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(lVar28);
                objRememberedValue9 = lVar28;
            } else {
                aVar3 = aVar5;
                lVar17 = lVar13;
                lVar18 = lVar14;
                lVar19 = lVar15;
                lVar20 = lVar16;
            }
            LazyDslKt.LazyColumn(modifierArvioDpadFocusGroup$default, lazyListState2, null, false, horizontalOrVerticalM441spacedBy0680j_4, null, null, false, (r7.l) objRememberedValue9, composerStartRestartGroup, ((i15 >> 9) & 112) | 24576, 236);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            composer2 = composerStartRestartGroup;
            i24 = i39;
            lVar21 = lVar10;
            lVar22 = lVar11;
            aVar4 = aVar3;
            lVar23 = lVar12;
            lVar24 = lVar17;
            lVar25 = lVar18;
            lVar26 = lVar19;
            lVar27 = lVar20;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.tv.v
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return TvScreenKt.CategoryRail$lambda$9(list, set, i10, z, lazyListState, lVar21, lVar22, i24, aVar4, lVar23, lVar24, lVar25, lVar26, lVar27, modifier2, i12, i13, i14, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CategoryRail$lambda$0$0(int i10) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CategoryRail$lambda$1$0(int i10) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CategoryRail$lambda$3$0(String str) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CategoryRail$lambda$4$0(String str) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CategoryRail$lambda$5$0(String str) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CategoryRail$lambda$6$0(String str) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CategoryRail$lambda$7$0(String str) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CategoryRail$lambda$8$0(List list, boolean z, int i10, Set set, int i11, r7.l lVar, r7.l lVar2, r7.a aVar, r7.l lVar3, r7.l lVar4, r7.l lVar5, r7.l lVar6, r7.l lVar7, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new TvScreenKt$CategoryRail$lambda$8$0$$inlined$itemsIndexed$1(new a(3), list), new TvScreenKt$CategoryRail$lambda$8$0$$inlined$itemsIndexed$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new TvScreenKt$CategoryRail$lambda$8$0$$inlined$itemsIndexed$3(list, z, i10, set, i11, lVar, lVar2, aVar, lVar3, lVar4, lVar5, lVar6, lVar7)));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object CategoryRail$lambda$8$0$0(int i10, String str) {
        return a2.j(i10, str, "#");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CategoryRail$lambda$9(List list, Set set, int i10, boolean z, LazyListState lazyListState, r7.l lVar, r7.l lVar2, int i11, r7.a aVar, r7.l lVar3, r7.l lVar4, r7.l lVar5, r7.l lVar6, r7.l lVar7, Modifier modifier, int i12, int i13, int i14, Composer composer, int i15) {
        CategoryRail(list, set, i10, z, lazyListState, lVar, lVar2, i11, aVar, lVar3, lVar4, lVar5, lVar6, lVar7, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), RecomposeScopeImplKt.updateChangedFlags(i13), i14);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0419  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:151:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void CompactGuideLane(final com.arflix.tv.data.model.IptvProgram r69, final com.arflix.tv.data.model.IptvProgram r70, final long r71, final boolean r73, final boolean r74, androidx.compose.ui.Modifier r75, androidx.compose.runtime.Composer r76, final int r77, final int r78) {
        /*
            Method dump skipped, instruction units count: 1095
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt.CompactGuideLane(com.arflix.tv.data.model.IptvProgram, com.arflix.tv.data.model.IptvProgram, long, boolean, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 CompactGuideLane$lambda$3(IptvProgram iptvProgram, IptvProgram iptvProgram2, long j10, boolean z, boolean z5, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        CompactGuideLane(iptvProgram, iptvProgram2, j10, z, z5, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: FocusableMenuItem-cf5BqRc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6331FocusableMenuItemcf5BqRc(final java.lang.String r29, final androidx.compose.ui.graphics.vector.ImageVector r30, long r31, r7.a<x6.t0> r33, androidx.compose.runtime.Composer r34, int r35, int r36) {
        /*
            Method dump skipped, instruction units count: 384
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt.m6331FocusableMenuItemcf5BqRc(java.lang.String, androidx.compose.ui.graphics.vector.ImageVector, long, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final boolean FocusableMenuItem_cf5BqRc$lambda$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void FocusableMenuItem_cf5BqRc$lambda$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 FocusableMenuItem_cf5BqRc$lambda$3$0(MutableState mutableState, FocusState focusState) {
        FocusableMenuItem_cf5BqRc$lambda$2(mutableState, focusState.isFocused());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 FocusableMenuItem_cf5BqRc$lambda$4(String str, MutableState mutableState, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2078448960, i10, -1, "com.arflix.tv.ui.screens.tv.FocusableMenuItem.<anonymous> (TvScreen.kt:2434)");
            }
            TextKt.m6020Text4IGK_g(str, null, FocusableMenuItem_cf5BqRc$lambda$1(mutableState) ? Color.INSTANCE.m3509getWhite0d7_KjU() : Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.8f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(12), FocusableMenuItem_cf5BqRc$lambda$1(mutableState) ? FontWeight.INSTANCE.getSemiBold() : FontWeight.INSTANCE.getNormal(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer, 0, 0, 65530);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 FocusableMenuItem_cf5BqRc$lambda$5(long j10, ImageVector imageVector, MutableState mutableState, Composer composer, int i10) {
        if ((i10 & 3) == 2 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1799771933, i10, -1, "com.arflix.tv.ui.screens.tv.FocusableMenuItem.<anonymous> (TvScreen.kt:2435)");
            }
            if (!FocusableMenuItem_cf5BqRc$lambda$1(mutableState)) {
                j10 = Color.m3471copywmQWz5c$default(j10, 0.5f, 0.0f, 0.0f, 0.0f, 14, null);
            }
            IconKt.m1610Iconww6aTOc(imageVector, (String) null, SizeKt.m578size3ABfNKs(Modifier.INSTANCE, Dp.m5678constructorimpl(16)), j10, composer, 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 FocusableMenuItem_cf5BqRc$lambda$6(String str, ImageVector imageVector, long j10, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        m6331FocusableMenuItemcf5BqRc(str, imageVector, j10, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0416  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0426  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0437  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x046e  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x04e9  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x04f0  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x059a  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x05a1  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0667  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0671  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x067c  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0682  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0686  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x068e  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x069a  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x06c9  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x06d5  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x06d9  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x06ea  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x06fd  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0738  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0744  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0748  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x076c  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0775  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0801  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0808  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0852  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0859  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0862  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x086b  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x08a8  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x08b0  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x08d7  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x08de  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x095f  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0965  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x097d  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0986  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0a1c  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0a22  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0a33  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0a37  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0a94  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0aa0  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0aa4  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0ab5  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0aca  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0b06  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0b11  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0be3  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0da1  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x103f  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x1072  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x107d  */
    /* JADX WARN: Removed duplicated region for block: B:397:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02eb  */
    /* JADX WARN: Type inference failed for: r15v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r37v0 */
    /* JADX WARN: Type inference failed for: r37v1 */
    /* JADX WARN: Type inference failed for: r37v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void FullscreenEpgOverlay(com.arflix.tv.data.model.IptvChannel r83, com.arflix.tv.data.model.IptvProgram r84, com.arflix.tv.data.model.IptvProgram r85, boolean r86, androidx.compose.runtime.Composer r87, int r88, int r89) {
        /*
            Method dump skipped, instruction units count: 4238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt.FullscreenEpgOverlay(com.arflix.tv.data.model.IptvChannel, com.arflix.tv.data.model.IptvProgram, com.arflix.tv.data.model.IptvProgram, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 FullscreenEpgOverlay$lambda$3(IptvChannel iptvChannel, IptvProgram iptvProgram, IptvProgram iptvProgram2, boolean z, int i10, int i11, Composer composer, int i12) {
        FullscreenEpgOverlay(iptvChannel, iptvProgram, iptvProgram2, z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01b5  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0421  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0445  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x04ba  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x04d2  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0520  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0570  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0601  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0612  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0628  */
    /* JADX WARN: Removed duplicated region for block: B:261:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0120  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void GroupRailItem(final java.lang.String r82, final boolean r83, final boolean r84, boolean r85, r7.a<x6.t0> r86, r7.a<x6.t0> r87, r7.a<x6.t0> r88, r7.a<x6.t0> r89, r7.a<x6.t0> r90, r7.a<x6.t0> r91, r7.a<x6.t0> r92, r7.a<x6.t0> r93, androidx.compose.runtime.Composer r94, final int r95, final int r96, final int r97) {
        /*
            Method dump skipped, instruction units count: 1598
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt.GroupRailItem(java.lang.String, boolean, boolean, boolean, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void GroupRailItem$lambda$10(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GroupRailItem$lambda$11$2(boolean z, final r7.a aVar, final r7.a aVar2, final r7.a aVar3, final r7.a aVar4, final r7.a aVar5, final r7.a aVar6, ColumnScope columnScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1615293595, i10, -1, "com.arflix.tv.ui.screens.tv.GroupRailItem.<anonymous>.<anonymous> (TvScreen.kt:1622)");
            }
            String str = z ? "Unfavorite" : "Favorite";
            ImageVector starOutline = z ? StarOutlineKt.getStarOutline(Icons.INSTANCE.getDefault()) : StarKt.getStar(Icons.INSTANCE.getDefault());
            long jColor = ColorKt.Color(4294296856L);
            boolean zChanged = composer.changed(aVar) | composer.changed(aVar2);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                final int i11 = 0;
                objRememberedValue = new r7.a() { // from class: com.arflix.tv.ui.screens.tv.y
                    @Override // r7.a
                    public final Object invoke() {
                        switch (i11) {
                            case 0:
                                return TvScreenKt.GroupRailItem$lambda$11$2$0$0(aVar, aVar2);
                            case 1:
                                return TvScreenKt.GroupRailItem$lambda$11$2$1$0(aVar, aVar2);
                            case 2:
                                return TvScreenKt.GroupRailItem$lambda$11$2$2$0(aVar, aVar2);
                            case 3:
                                return TvScreenKt.GroupRailItem$lambda$11$2$3$0(aVar, aVar2);
                            default:
                                return TvScreenKt.GroupRailItem$lambda$11$2$4$0(aVar, aVar2);
                        }
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            m6331FocusableMenuItemcf5BqRc(str, starOutline, jColor, (r7.a) objRememberedValue, composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
            Icons icons = Icons.INSTANCE;
            ImageVector visibilityOff = VisibilityOffKt.getVisibilityOff(icons.getDefault());
            boolean zChanged2 = composer.changed(aVar) | composer.changed(aVar3);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                final int i12 = 1;
                objRememberedValue2 = new r7.a() { // from class: com.arflix.tv.ui.screens.tv.y
                    @Override // r7.a
                    public final Object invoke() {
                        switch (i12) {
                            case 0:
                                return TvScreenKt.GroupRailItem$lambda$11$2$0$0(aVar, aVar3);
                            case 1:
                                return TvScreenKt.GroupRailItem$lambda$11$2$1$0(aVar, aVar3);
                            case 2:
                                return TvScreenKt.GroupRailItem$lambda$11$2$2$0(aVar, aVar3);
                            case 3:
                                return TvScreenKt.GroupRailItem$lambda$11$2$3$0(aVar, aVar3);
                            default:
                                return TvScreenKt.GroupRailItem$lambda$11$2$4$0(aVar, aVar3);
                        }
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            m6331FocusableMenuItemcf5BqRc("Hide", visibilityOff, 0L, (r7.a) objRememberedValue2, composer, 6, 4);
            ImageVector keyboardArrowUp = KeyboardArrowUpKt.getKeyboardArrowUp(icons.getDefault());
            boolean zChanged3 = composer.changed(aVar) | composer.changed(aVar4);
            Object objRememberedValue3 = composer.rememberedValue();
            if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                final int i13 = 2;
                objRememberedValue3 = new r7.a() { // from class: com.arflix.tv.ui.screens.tv.y
                    @Override // r7.a
                    public final Object invoke() {
                        switch (i13) {
                            case 0:
                                return TvScreenKt.GroupRailItem$lambda$11$2$0$0(aVar, aVar4);
                            case 1:
                                return TvScreenKt.GroupRailItem$lambda$11$2$1$0(aVar, aVar4);
                            case 2:
                                return TvScreenKt.GroupRailItem$lambda$11$2$2$0(aVar, aVar4);
                            case 3:
                                return TvScreenKt.GroupRailItem$lambda$11$2$3$0(aVar, aVar4);
                            default:
                                return TvScreenKt.GroupRailItem$lambda$11$2$4$0(aVar, aVar4);
                        }
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            m6331FocusableMenuItemcf5BqRc("Move to Top", keyboardArrowUp, 0L, (r7.a) objRememberedValue3, composer, 6, 4);
            ImageVector keyboardArrowUp2 = KeyboardArrowUpKt.getKeyboardArrowUp(icons.getDefault());
            boolean zChanged4 = composer.changed(aVar) | composer.changed(aVar5);
            Object objRememberedValue4 = composer.rememberedValue();
            if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                final int i14 = 3;
                objRememberedValue4 = new r7.a() { // from class: com.arflix.tv.ui.screens.tv.y
                    @Override // r7.a
                    public final Object invoke() {
                        switch (i14) {
                            case 0:
                                return TvScreenKt.GroupRailItem$lambda$11$2$0$0(aVar, aVar5);
                            case 1:
                                return TvScreenKt.GroupRailItem$lambda$11$2$1$0(aVar, aVar5);
                            case 2:
                                return TvScreenKt.GroupRailItem$lambda$11$2$2$0(aVar, aVar5);
                            case 3:
                                return TvScreenKt.GroupRailItem$lambda$11$2$3$0(aVar, aVar5);
                            default:
                                return TvScreenKt.GroupRailItem$lambda$11$2$4$0(aVar, aVar5);
                        }
                    }
                };
                composer.updateRememberedValue(objRememberedValue4);
            }
            m6331FocusableMenuItemcf5BqRc("Move Up", keyboardArrowUp2, 0L, (r7.a) objRememberedValue4, composer, 6, 4);
            ImageVector keyboardArrowDown = KeyboardArrowDownKt.getKeyboardArrowDown(icons.getDefault());
            boolean zChanged5 = composer.changed(aVar) | composer.changed(aVar6);
            Object objRememberedValue5 = composer.rememberedValue();
            if (zChanged5 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                final int i15 = 4;
                objRememberedValue5 = new r7.a() { // from class: com.arflix.tv.ui.screens.tv.y
                    @Override // r7.a
                    public final Object invoke() {
                        switch (i15) {
                            case 0:
                                return TvScreenKt.GroupRailItem$lambda$11$2$0$0(aVar, aVar6);
                            case 1:
                                return TvScreenKt.GroupRailItem$lambda$11$2$1$0(aVar, aVar6);
                            case 2:
                                return TvScreenKt.GroupRailItem$lambda$11$2$2$0(aVar, aVar6);
                            case 3:
                                return TvScreenKt.GroupRailItem$lambda$11$2$3$0(aVar, aVar6);
                            default:
                                return TvScreenKt.GroupRailItem$lambda$11$2$4$0(aVar, aVar6);
                        }
                    }
                };
                composer.updateRememberedValue(objRememberedValue5);
            }
            m6331FocusableMenuItemcf5BqRc("Move Down", keyboardArrowDown, 0L, (r7.a) objRememberedValue5, composer, 6, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GroupRailItem$lambda$11$2$0$0(r7.a aVar, r7.a aVar2) {
        aVar.invoke();
        aVar2.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GroupRailItem$lambda$11$2$1$0(r7.a aVar, r7.a aVar2) {
        aVar.invoke();
        aVar2.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GroupRailItem$lambda$11$2$2$0(r7.a aVar, r7.a aVar2) {
        aVar.invoke();
        aVar2.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GroupRailItem$lambda$11$2$3$0(r7.a aVar, r7.a aVar2) {
        aVar.invoke();
        aVar2.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GroupRailItem$lambda$11$2$4$0(r7.a aVar, r7.a aVar2) {
        aVar.invoke();
        aVar2.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GroupRailItem$lambda$12(String str, boolean z, boolean z5, boolean z10, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, r7.a aVar7, r7.a aVar8, int i10, int i11, int i12, Composer composer, int i13) {
        GroupRailItem(str, z, z5, z10, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, aVar8, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean GroupRailItem$lambda$9(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:195:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x03dd  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0401  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x04db  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x04e3  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x053b  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0547  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x054b  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x055c  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0571  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x05b8  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0603  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x064f  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x065b  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x065f  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0683  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x069f  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x06a6  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x06ad  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x06eb  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x06f1  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0737  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x07cf  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x07e0  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x084d  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x08b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void GuideChannelRow(final com.arflix.tv.data.model.IptvChannel r79, final java.util.List<com.arflix.tv.data.model.IptvProgram> r80, final com.arflix.tv.data.model.IptvProgram r81, final java.util.List<com.arflix.tv.data.model.IptvProgram> r82, final boolean r83, final boolean r84, final boolean r85, final boolean r86, final long r87, final long r89, final long r91, final float r93, boolean r94, r7.a<x6.t0> r95, androidx.compose.runtime.Composer r96, final int r97, final int r98, final int r99) {
        /*
            Method dump skipped, instruction units count: 2282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt.GuideChannelRow(com.arflix.tv.data.model.IptvChannel, java.util.List, com.arflix.tv.data.model.IptvProgram, java.util.List, boolean, boolean, boolean, boolean, long, long, long, float, boolean, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GuideChannelRow$lambda$2(IptvChannel iptvChannel, List list, IptvProgram iptvProgram, List list2, boolean z, boolean z5, boolean z10, boolean z11, long j10, long j11, long j12, float f10, boolean z12, r7.a aVar, int i10, int i11, int i12, Composer composer, int i13) {
        GuideChannelRow(iptvChannel, list, iptvProgram, list2, z, z5, z10, z11, j10, j11, j12, f10, z12, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:188:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0132  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void GuidePanel(final java.util.List<com.arflix.tv.data.model.IptvChannel> r45, final java.util.Map<java.lang.String, com.arflix.tv.data.model.IptvNowNext> r46, final boolean r47, final int r48, final boolean r49, final boolean r50, final java.lang.String r51, final java.util.Set<java.lang.String> r52, final androidx.compose.foundation.lazy.LazyListState r53, r7.l<? super java.lang.Integer, x6.t0> r54, androidx.compose.ui.Modifier r55, androidx.compose.runtime.Composer r56, final int r57, final int r58, final int r59) {
        /*
            Method dump skipped, instruction units count: 1083
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt.GuidePanel(java.util.List, java.util.Map, boolean, int, boolean, boolean, java.lang.String, java.util.Set, androidx.compose.foundation.lazy.LazyListState, r7.l, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GuidePanel$lambda$0$0(int i10) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long GuidePanel$lambda$2(MutableState<Long> mutableState) {
        return mutableState.getValue().longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void GuidePanel$lambda$3(MutableState<Long> mutableState, long j10) {
        mutableState.setValue(Long.valueOf(j10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GuidePanel$lambda$5$1$0(List list, boolean z, int i10, Map map, String str, Set set, long j10, long j11, float f10, boolean z5, r7.l lVar, MutableState mutableState, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new TvScreenKt$GuidePanel$lambda$5$1$0$$inlined$itemsIndexed$1(new a(4), list), new TvScreenKt$GuidePanel$lambda$5$1$0$$inlined$itemsIndexed$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new TvScreenKt$GuidePanel$lambda$5$1$0$$inlined$itemsIndexed$3(list, z, i10, map, str, set, j10, j11, f10, z5, lVar, mutableState)));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object GuidePanel$lambda$5$1$0$0(int i10, IptvChannel iptvChannel) {
        return a2.j(i10, iptvChannel.getId(), "#");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GuidePanel$lambda$6(List list, Map map, boolean z, int i10, boolean z5, boolean z10, String str, Set set, LazyListState lazyListState, r7.l lVar, Modifier modifier, int i11, int i12, int i13, Composer composer, int i14) {
        GuidePanel(list, map, z, i10, z5, z10, str, set, lazyListState, lVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), RecomposeScopeImplKt.updateChangedFlags(i12), i13);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ea A[LOOP:0: B:55:0x00e6->B:57:0x00ea, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:84:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void GuideTimeHeader(final long r46, final long r48, final long r50, boolean r52, androidx.compose.runtime.Composer r53, final int r54, final int r55) {
        /*
            Method dump skipped, instruction units count: 499
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt.GuideTimeHeader(long, long, long, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GuideTimeHeader$lambda$0$0(long j10, long j11, List list, long j12, TextStyle textStyle, BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, int i10) {
        BoxWithConstraintsScope boxWithConstraintsScope2;
        Composer composer2;
        int i11;
        if ((i10 & 6) == 0) {
            boxWithConstraintsScope2 = boxWithConstraintsScope;
            composer2 = composer;
            i11 = i10 | (composer2.changed(boxWithConstraintsScope2) ? 4 : 2);
        } else {
            boxWithConstraintsScope2 = boxWithConstraintsScope;
            composer2 = composer;
            i11 = i10;
        }
        if ((i11 & 19) == 18 && composer2.getSkipping()) {
            composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-973491818, i11, -1, "com.arflix.tv.ui.screens.tv.GuideTimeHeader.<anonymous>.<anonymous> (TvScreen.kt:1947)");
            }
            long j13 = j10 - j11;
            if (j13 < 1) {
                j13 = 1;
            }
            float f10 = j13;
            float fMo467getMaxWidthD9Ej5fM = boxWithConstraintsScope2.mo467getMaxWidthD9Ej5fM();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                long jLongValue = ((Number) it.next()).longValue();
                float fM = qb.d.m((jLongValue - j11) / f10, 0.0f, 0.95f);
                boolean z = Math.abs(jLongValue - j12) < 900000;
                boolean z5 = jLongValue % 3600000 == 0;
                String programTime = formatProgramTime(jLongValue);
                FontWeight.Companion companion = FontWeight.INSTANCE;
                TextKt.m6020Text4IGK_g(programTime, OffsetKt.m490offsetVpY3zN4$default(Modifier.INSTANCE, Dp.m5678constructorimpl(fM * fMo467getMaxWidthD9Ej5fM), 0.0f, 2, null), z ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null) : z5 ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.35f, 0.0f, 0.0f, 0.0f, 14, null) : Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(textStyle, 0L, 0L, z5 ? companion.getMedium() : companion.getNormal(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777211, null), composer, 0, 0, 65528);
                f10 = f10;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 GuideTimeHeader$lambda$1(long j10, long j11, long j12, boolean z, int i10, int i11, Composer composer, int i12) {
        GuideTimeHeader(j10, j11, j12, z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    private static final void NotConfiguredPanel(Composer composer, int i10) {
        Composer composer2;
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1497334753);
        if (i10 == 0 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
            i11 = 6;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1497334753, i10, -1, "com.arflix.tv.ui.screens.tv.NotConfiguredPanel (TvScreen.kt:2446)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierM529padding3ABfNKs = PaddingKt.m529padding3ABfNKs(com.arflix.tv.data.repository.g.l(14, SizeKt.fillMaxSize$default(companion, 0.0f, 1, null), com.arflix.tv.ui.theme.ColorKt.getBackgroundCard()), Dp.m5678constructorimpl(24));
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment center = companion2.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM529padding3ABfNKs);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Alignment.Horizontal centerHorizontally = companion2.getCenterHorizontally();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(Arrangement.INSTANCE, centerHorizontally, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(companion);
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
            r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyF, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            IconKt.m1610Iconww6aTOc(LiveTvKt.getLiveTv(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(40)), com.arflix.tv.ui.theme.ColorKt.getTextSecondary(), composerStartRestartGroup, 3504, 0);
            com.arflix.tv.data.repository.g.p(12, companion, composerStartRestartGroup, 6);
            String strStringResource = StringResources_androidKt.stringResource(R.string.iptv_not_configured, composerStartRestartGroup, 0);
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            composer2 = composerStartRestartGroup;
            i11 = 6;
            TextKt.m6020Text4IGK_g(strStringResource, null, com.arflix.tv.ui.theme.ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getSectionTitle(), composer2, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(6)), composer2, 6);
            TextKt.m6020Text4IGK_g("Open Settings and add your M3U URL.", null, com.arflix.tv.ui.theme.ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getBody(), composer2, 390, 0, 65530);
            if (androidx.compose.foundation.c.A(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.j(i10, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 NotConfiguredPanel$lambda$1(int i10, Composer composer, int i11) {
        NotConfiguredPanel(composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:111:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0122  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void TimelineProgramLane(final java.util.List<com.arflix.tv.data.model.IptvProgram> r32, final com.arflix.tv.data.model.IptvProgram r33, final java.util.List<com.arflix.tv.data.model.IptvProgram> r34, final long r35, final long r37, final long r39, final float r41, final boolean r42, boolean r43, androidx.compose.ui.Modifier r44, androidx.compose.runtime.Composer r45, final int r46, final int r47) {
        /*
            Method dump skipped, instruction units count: 406
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt.TimelineProgramLane(java.util.List, com.arflix.tv.data.model.IptvProgram, java.util.List, long, long, long, float, boolean, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:118:0x04b2  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x05e3  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x038d  */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 TimelineProgramLane$lambda$0(java.util.List r97, com.arflix.tv.data.model.IptvProgram r98, java.util.List r99, long r100, long r102, float r104, boolean r105, boolean r106, long r107, long r109, androidx.compose.foundation.layout.BoxWithConstraintsScope r111, androidx.compose.runtime.Composer r112, int r113) {
        /*
            Method dump skipped, instruction units count: 1768
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt.TimelineProgramLane$lambda$0(java.util.List, com.arflix.tv.data.model.IptvProgram, java.util.List, long, long, float, boolean, boolean, long, long, androidx.compose.foundation.layout.BoxWithConstraintsScope, androidx.compose.runtime.Composer, int):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TimelineProgramLane$lambda$1(List list, IptvProgram iptvProgram, List list2, long j10, long j11, long j12, float f10, boolean z, boolean z5, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        TimelineProgramLane(list, iptvProgram, list2, j10, j11, j12, f10, z, z5, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:1004:0x2610  */
    /* JADX WARN: Removed duplicated region for block: B:1007:0x265b  */
    /* JADX WARN: Removed duplicated region for block: B:1010:0x2667  */
    /* JADX WARN: Removed duplicated region for block: B:1011:0x266b  */
    /* JADX WARN: Removed duplicated region for block: B:1014:0x267c  */
    /* JADX WARN: Removed duplicated region for block: B:1018:0x2691  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:1022:0x269c  */
    /* JADX WARN: Removed duplicated region for block: B:1036:0x2701  */
    /* JADX WARN: Removed duplicated region for block: B:1039:0x2729  */
    /* JADX WARN: Removed duplicated region for block: B:1040:0x272c  */
    /* JADX WARN: Removed duplicated region for block: B:1045:0x2765  */
    /* JADX WARN: Removed duplicated region for block: B:1049:0x27b5  */
    /* JADX WARN: Removed duplicated region for block: B:1070:0x287e  */
    /* JADX WARN: Removed duplicated region for block: B:1093:0x29c2  */
    /* JADX WARN: Removed duplicated region for block: B:1095:0x29cf  */
    /* JADX WARN: Removed duplicated region for block: B:1098:0x29e2  */
    /* JADX WARN: Removed duplicated region for block: B:1099:0x29ed  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:1116:0x2aee  */
    /* JADX WARN: Removed duplicated region for block: B:1120:0x2b0b  */
    /* JADX WARN: Removed duplicated region for block: B:1125:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0330  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x038d  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x03fc  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0430  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0471  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0480  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0490  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0493  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x049b  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x04a4  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x04d6  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x04e9  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x04fd  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x050f  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0520  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0533  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x054b  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x057c  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x058f  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x05a1  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x05b8  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x05ca  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x05d9  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x05e8  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x060b  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0636  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0650  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x066e  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0683  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x068e  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x069f  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x06a2  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x06b8  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0724  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0755  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0785  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x07b7  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x07ca  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x07ea  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0801 A[LOOP:1: B:326:0x07fb->B:328:0x0801, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:336:0x082a  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0842  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x08a7  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x08b5  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x08c3  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x08d4  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x08dd  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x08f0  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x08fa  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x0923  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x0926  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x0958  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x096c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x09eb  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x09ef  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x09f3  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x09f5  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0a36  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x0a44  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0a85  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x0a8d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x0aee  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x0b37  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x0b6f  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x0bc0  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0bd0  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x0c2e  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x0c3e  */
    /* JADX WARN: Removed duplicated region for block: B:433:0x0ccd  */
    /* JADX WARN: Removed duplicated region for block: B:436:0x0cd8  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x0d0a  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x0d12  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x0d6c  */
    /* JADX WARN: Removed duplicated region for block: B:456:0x0df2  */
    /* JADX WARN: Removed duplicated region for block: B:461:0x0e43  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x0eb2  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x0edf  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x0f1e  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x0f36  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x0f56  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x0f7a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x0f94  */
    /* JADX WARN: Removed duplicated region for block: B:491:0x0fa0  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x0fb0  */
    /* JADX WARN: Removed duplicated region for block: B:495:0x0fb9  */
    /* JADX WARN: Removed duplicated region for block: B:498:0x0fc8  */
    /* JADX WARN: Removed duplicated region for block: B:499:0x0fd1  */
    /* JADX WARN: Removed duplicated region for block: B:502:0x0fe0  */
    /* JADX WARN: Removed duplicated region for block: B:505:0x0fec  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x0ff2  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:510:0x1004  */
    /* JADX WARN: Removed duplicated region for block: B:513:0x1032  */
    /* JADX WARN: Removed duplicated region for block: B:516:0x103b  */
    /* JADX WARN: Removed duplicated region for block: B:520:0x1055  */
    /* JADX WARN: Removed duplicated region for block: B:523:0x1066  */
    /* JADX WARN: Removed duplicated region for block: B:525:0x106a  */
    /* JADX WARN: Removed duplicated region for block: B:527:0x106e  */
    /* JADX WARN: Removed duplicated region for block: B:528:0x1070  */
    /* JADX WARN: Removed duplicated region for block: B:531:0x1091  */
    /* JADX WARN: Removed duplicated region for block: B:535:0x10a2  */
    /* JADX WARN: Removed duplicated region for block: B:538:0x10e0  */
    /* JADX WARN: Removed duplicated region for block: B:541:0x10f3  */
    /* JADX WARN: Removed duplicated region for block: B:543:0x10fa  */
    /* JADX WARN: Removed duplicated region for block: B:548:0x1120  */
    /* JADX WARN: Removed duplicated region for block: B:555:0x117b  */
    /* JADX WARN: Removed duplicated region for block: B:562:0x11b8  */
    /* JADX WARN: Removed duplicated region for block: B:565:0x1211  */
    /* JADX WARN: Removed duplicated region for block: B:566:0x1221  */
    /* JADX WARN: Removed duplicated region for block: B:569:0x1298  */
    /* JADX WARN: Removed duplicated region for block: B:570:0x129a  */
    /* JADX WARN: Removed duplicated region for block: B:573:0x12a7  */
    /* JADX WARN: Removed duplicated region for block: B:574:0x12a9  */
    /* JADX WARN: Removed duplicated region for block: B:577:0x12b7  */
    /* JADX WARN: Removed duplicated region for block: B:578:0x12b9  */
    /* JADX WARN: Removed duplicated region for block: B:581:0x12c6  */
    /* JADX WARN: Removed duplicated region for block: B:582:0x12c8  */
    /* JADX WARN: Removed duplicated region for block: B:585:0x12d5  */
    /* JADX WARN: Removed duplicated region for block: B:586:0x12d7  */
    /* JADX WARN: Removed duplicated region for block: B:589:0x12e2  */
    /* JADX WARN: Removed duplicated region for block: B:590:0x12e4  */
    /* JADX WARN: Removed duplicated region for block: B:597:0x1326  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:600:0x13a8  */
    /* JADX WARN: Removed duplicated region for block: B:603:0x13b4  */
    /* JADX WARN: Removed duplicated region for block: B:604:0x13b8  */
    /* JADX WARN: Removed duplicated region for block: B:607:0x13c9  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:610:0x13da  */
    /* JADX WARN: Removed duplicated region for block: B:614:0x13f9  */
    /* JADX WARN: Removed duplicated region for block: B:619:0x1447  */
    /* JADX WARN: Removed duplicated region for block: B:622:0x1471  */
    /* JADX WARN: Removed duplicated region for block: B:638:0x151f  */
    /* JADX WARN: Removed duplicated region for block: B:662:0x1603  */
    /* JADX WARN: Removed duplicated region for block: B:664:0x160b  */
    /* JADX WARN: Removed duplicated region for block: B:667:0x165d  */
    /* JADX WARN: Removed duplicated region for block: B:670:0x1669  */
    /* JADX WARN: Removed duplicated region for block: B:671:0x166d  */
    /* JADX WARN: Removed duplicated region for block: B:674:0x167e  */
    /* JADX WARN: Removed duplicated region for block: B:678:0x1693  */
    /* JADX WARN: Removed duplicated region for block: B:682:0x16a6  */
    /* JADX WARN: Removed duplicated region for block: B:683:0x16a8  */
    /* JADX WARN: Removed duplicated region for block: B:686:0x16d7  */
    /* JADX WARN: Removed duplicated region for block: B:690:0x16e7  */
    /* JADX WARN: Removed duplicated region for block: B:697:0x1727  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:700:0x173c  */
    /* JADX WARN: Removed duplicated region for block: B:701:0x173f  */
    /* JADX WARN: Removed duplicated region for block: B:704:0x174b  */
    /* JADX WARN: Removed duplicated region for block: B:709:0x176a  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:716:0x178a  */
    /* JADX WARN: Removed duplicated region for block: B:723:0x17aa  */
    /* JADX WARN: Removed duplicated region for block: B:728:0x17c7  */
    /* JADX WARN: Removed duplicated region for block: B:733:0x17e4  */
    /* JADX WARN: Removed duplicated region for block: B:738:0x1805  */
    /* JADX WARN: Removed duplicated region for block: B:741:0x1881  */
    /* JADX WARN: Removed duplicated region for block: B:744:0x188d  */
    /* JADX WARN: Removed duplicated region for block: B:745:0x1891  */
    /* JADX WARN: Removed duplicated region for block: B:748:0x18a2  */
    /* JADX WARN: Removed duplicated region for block: B:752:0x18b7  */
    /* JADX WARN: Removed duplicated region for block: B:756:0x1921  */
    /* JADX WARN: Removed duplicated region for block: B:759:0x192d  */
    /* JADX WARN: Removed duplicated region for block: B:760:0x1931  */
    /* JADX WARN: Removed duplicated region for block: B:763:0x1942  */
    /* JADX WARN: Removed duplicated region for block: B:767:0x1957  */
    /* JADX WARN: Removed duplicated region for block: B:771:0x1962  */
    /* JADX WARN: Removed duplicated region for block: B:774:0x197c  */
    /* JADX WARN: Removed duplicated region for block: B:777:0x199e  */
    /* JADX WARN: Removed duplicated region for block: B:778:0x19a6  */
    /* JADX WARN: Removed duplicated region for block: B:780:0x19ae  */
    /* JADX WARN: Removed duplicated region for block: B:781:0x19b3  */
    /* JADX WARN: Removed duplicated region for block: B:783:0x19ba  */
    /* JADX WARN: Removed duplicated region for block: B:784:0x19c1  */
    /* JADX WARN: Removed duplicated region for block: B:786:0x19cc  */
    /* JADX WARN: Removed duplicated region for block: B:787:0x19d3  */
    /* JADX WARN: Removed duplicated region for block: B:790:0x1a0d  */
    /* JADX WARN: Removed duplicated region for block: B:793:0x1a19  */
    /* JADX WARN: Removed duplicated region for block: B:794:0x1a1d  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:801:0x1a41  */
    /* JADX WARN: Removed duplicated region for block: B:804:0x1a4a  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:885:0x1d73  */
    /* JADX WARN: Removed duplicated region for block: B:887:0x1d7c  */
    /* JADX WARN: Removed duplicated region for block: B:890:0x1e2f  */
    /* JADX WARN: Removed duplicated region for block: B:892:0x1e36  */
    /* JADX WARN: Removed duplicated region for block: B:895:0x1ec4  */
    /* JADX WARN: Removed duplicated region for block: B:897:0x1ecb  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:925:0x1ffe  */
    /* JADX WARN: Removed duplicated region for block: B:927:0x2007  */
    /* JADX WARN: Removed duplicated region for block: B:930:0x20c9  */
    /* JADX WARN: Removed duplicated region for block: B:932:0x20d0  */
    /* JADX WARN: Removed duplicated region for block: B:935:0x2161  */
    /* JADX WARN: Removed duplicated region for block: B:937:0x216a  */
    /* JADX WARN: Removed duplicated region for block: B:980:0x248a  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011d  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TvScreen(com.arflix.tv.ui.screens.tv.TvViewModel r126, com.arflix.tv.data.model.Profile r127, java.lang.String r128, java.lang.String r129, r7.a<x6.t0> r130, r7.a<x6.t0> r131, r7.a<x6.t0> r132, r7.a<x6.t0> r133, r7.a<x6.t0> r134, r7.a<x6.t0> r135, androidx.compose.runtime.Composer r136, final int r137, final int r138) {
        /*
            Method dump skipped, instruction units count: 11032
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt.TvScreen(com.arflix.tv.ui.screens.tv.TvViewModel, com.arflix.tv.data.model.Profile, java.lang.String, java.lang.String, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState TvScreen$lambda$10$0(boolean z) {
        return SnapshotIntStateKt.mutableIntStateOf(z ? 4 : 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlayerView TvScreen$lambda$100(MutableState<PlayerView> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String TvScreen$lambda$102(State<String> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult TvScreen$lambda$103$0(final MutableState mutableState, final MutableState mutableState2, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$lambda$103$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                TvScreenKt.TvScreen$lambda$92(mutableState, true);
                ExoPlayer exoPlayerTvScreen$lambda$94 = TvScreenKt.TvScreen$lambda$94(mutableState2);
                if (exoPlayerTvScreen$lambda$94 != null) {
                    exoPlayerTvScreen$lambda$94.release();
                }
                mutableState2.setValue(null);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult TvScreen$lambda$104$0(final androidx.lifecycle.y yVar, final MutableState mutableState, final MutableState mutableState2, DisposableEffectScope disposableEffectScope) {
        final androidx.lifecycle.w wVar = new androidx.lifecycle.w() { // from class: com.arflix.tv.ui.screens.tv.a0
            @Override // androidx.lifecycle.w
            public final void onStateChanged(androidx.lifecycle.y yVar2, androidx.lifecycle.p pVar) {
                TvScreenKt.TvScreen$lambda$104$0$0(mutableState, mutableState2, yVar2, pVar);
            }
        };
        yVar.getLifecycle().addObserver(wVar);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$lambda$104$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                yVar.getLifecycle().removeObserver(wVar);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TvScreen$lambda$104$0$0(MutableState mutableState, MutableState mutableState2, androidx.lifecycle.y yVar, androidx.lifecycle.p pVar) {
        ExoPlayer exoPlayerTvScreen$lambda$94;
        int i10 = WhenMappings.$EnumSwitchMapping$0[pVar.ordinal()];
        if (i10 == 1) {
            ExoPlayer exoPlayerTvScreen$lambda$942 = TvScreen$lambda$94(mutableState);
            if (exoPlayerTvScreen$lambda$942 != null) {
                exoPlayerTvScreen$lambda$942.pause();
                return;
            }
            return;
        }
        if (i10 != 2 || TvScreen$lambda$23(mutableState2) == null || (exoPlayerTvScreen$lambda$94 = TvScreen$lambda$94(mutableState)) == null) {
            return;
        }
        exoPlayerTvScreen$lambda$94.play();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String TvScreen$lambda$107(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.media3.common.Player$Listener, com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$31$1$listener$1] */
    public static final DisposableEffectResult TvScreen$lambda$115$0(MutableState mutableState, final MutableState mutableState2, final State state, final MutableIntState mutableIntState, final IptvChannel iptvChannel, final MutableState mutableState3, final MutableState mutableState4, DisposableEffectScope disposableEffectScope) {
        final ExoPlayer exoPlayerTvScreen$lambda$94 = TvScreen$lambda$94(mutableState);
        if (exoPlayerTvScreen$lambda$94 == 0) {
            return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$lambda$115$0$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                }
            };
        }
        final ?? r02 = new Player.Listener() { // from class: com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$31$1$listener$1
            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
                androidx.media3.common.j.a(this, audioAttributes);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onAudioSessionIdChanged(int i10) {
                androidx.media3.common.j.b(this, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
                androidx.media3.common.j.c(this, commands);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onCues(CueGroup cueGroup) {
                androidx.media3.common.j.d(this, cueGroup);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
                androidx.media3.common.j.f(this, deviceInfo);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onDeviceVolumeChanged(int i10, boolean z) {
                androidx.media3.common.j.g(this, i10, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onEvents(Player player, Player.Events events) {
                androidx.media3.common.j.h(this, player, events);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onIsLoadingChanged(boolean z) {
                androidx.media3.common.j.i(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onIsPlayingChanged(boolean z) {
                androidx.media3.common.j.j(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onLoadingChanged(boolean z) {
                androidx.media3.common.j.k(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j10) {
                androidx.media3.common.j.l(this, j10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i10) {
                androidx.media3.common.j.m(this, mediaItem, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                androidx.media3.common.j.n(this, mediaMetadata);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onMetadata(androidx.media3.common.Metadata metadata) {
                androidx.media3.common.j.o(this, metadata);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlayWhenReadyChanged(boolean z, int i10) {
                androidx.media3.common.j.p(this, z, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                androidx.media3.common.j.q(this, playbackParameters);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlaybackStateChanged(int i10) {
                androidx.media3.common.j.r(this, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlaybackSuppressionReasonChanged(int i10) {
                androidx.media3.common.j.s(this, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlayerError(PlaybackException error) {
                String strTvScreen$lambda$102;
                DrmInfo drmInfo;
                String licenseUrl;
                if (TvScreenKt.TvScreen$lambda$91(mutableState2) || (strTvScreen$lambda$102 = TvScreenKt.TvScreen$lambda$102(state)) == null) {
                    return;
                }
                mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
                if (mutableIntState.getIntValue() > 3) {
                    System.err.println("[IPTV] Playback failed after 3 retries: " + error.getMessage() + " URL=" + strTvScreen$lambda$102);
                    return;
                }
                exoPlayerTvScreen$lambda$94.clearMediaItems();
                MediaItem.Builder liveConfiguration = new MediaItem.Builder().setUri(strTvScreen$lambda$102).setLiveConfiguration(new MediaItem.LiveConfiguration.Builder().setMinPlaybackSpeed(1.0f).setMaxPlaybackSpeed(1.0f).setTargetOffsetMs(4000L).build());
                IptvChannel iptvChannel2 = iptvChannel;
                if (iptvChannel2 != null && (drmInfo = iptvChannel2.getDrmInfo()) != null) {
                    ClearKeyUtil clearKeyUtil = ClearKeyUtil.INSTANCE;
                    MediaItem.DrmConfiguration.Builder builder = new MediaItem.DrmConfiguration.Builder(clearKeyUtil.drmSchemeToUuid(drmInfo.getScheme()));
                    if (!kotlin.jvm.internal.p.a(drmInfo.getScheme(), "clearkey") || (licenseUrl = drmInfo.getLicenseUrl()) == null || kotlin.text.o.h0(licenseUrl)) {
                        String licenseUrl2 = drmInfo.getLicenseUrl();
                        if (licenseUrl2 != null && !kotlin.text.o.h0(licenseUrl2)) {
                            builder.setLicenseUri(kotlin.text.o.H0(drmInfo.getLicenseUrl(), "|"));
                        }
                    } else {
                        String strBuildClearKeyLicenseUri = clearKeyUtil.buildClearKeyLicenseUri(drmInfo.getLicenseUrl());
                        if (strBuildClearKeyLicenseUri != null) {
                            builder.setLicenseUri(strBuildClearKeyLicenseUri);
                        }
                    }
                    liveConfiguration.setDrmConfiguration(builder.build());
                }
                exoPlayerTvScreen$lambda$94.setMediaItem(liveConfiguration.build());
                exoPlayerTvScreen$lambda$94.prepare();
                exoPlayerTvScreen$lambda$94.setPlayWhenReady(true);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
                androidx.media3.common.j.u(this, playbackException);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlayerStateChanged(boolean z, int i10) {
                androidx.media3.common.j.v(this, z, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
                androidx.media3.common.j.w(this, mediaMetadata);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPositionDiscontinuity(int i10) {
                androidx.media3.common.j.x(this, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onRenderedFirstFrame() {
                androidx.media3.common.j.z(this);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onRepeatModeChanged(int i10) {
                androidx.media3.common.j.A(this, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onSeekBackIncrementChanged(long j10) {
                androidx.media3.common.j.B(this, j10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onSeekForwardIncrementChanged(long j10) {
                androidx.media3.common.j.C(this, j10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
                androidx.media3.common.j.D(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
                androidx.media3.common.j.E(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onSurfaceSizeChanged(int i10, int i11) {
                androidx.media3.common.j.F(this, i10, i11);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onTimelineChanged(Timeline timeline, int i10) {
                androidx.media3.common.j.G(this, timeline, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
                androidx.media3.common.j.H(this, trackSelectionParameters);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onTracksChanged(Tracks tracks) {
                androidx.media3.common.j.I(this, tracks);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onVideoSizeChanged(VideoSize videoSize) {
                if (TvScreenKt.TvScreen$lambda$91(mutableState2)) {
                    return;
                }
                PlayerView playerViewTvScreen$lambda$97 = TvScreenKt.TvScreen$lambda$97(mutableState3);
                if (playerViewTvScreen$lambda$97 != null) {
                    playerViewTvScreen$lambda$97.setResizeMode(0);
                }
                PlayerView playerViewTvScreen$lambda$100 = TvScreenKt.TvScreen$lambda$100(mutableState4);
                if (playerViewTvScreen$lambda$100 != null) {
                    playerViewTvScreen$lambda$100.setResizeMode(0);
                }
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onVolumeChanged(float f10) {
                androidx.media3.common.j.K(this, f10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onCues(List list) {
                androidx.media3.common.j.e(this, list);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i10) {
                androidx.media3.common.j.y(this, positionInfo, positionInfo2, i10);
            }
        };
        exoPlayerTvScreen$lambda$94.addListener(r02);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.TvScreenKt$TvScreen$lambda$115$0$$inlined$onDispose$2
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                exoPlayerTvScreen$lambda$94.removeListener(r02);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$116$0(MutableState mutableState, MutableState mutableState2, FocusState focusState) {
        TvScreen$lambda$55(mutableState, focusState.getHasFocus());
        if (!focusState.getHasFocus()) {
            mutableState2.setValue(null);
        }
        return t0.f22605a;
    }

    private static final float TvScreen$lambda$118$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$2$0$0$0(List list, MutableIntState mutableIntState, MutableState mutableState, State state, MutableState mutableState2, MutableState mutableState3, MutableIntState mutableIntState2, int i10) {
        mutableIntState.setIntValue(i10);
        mutableState.setValue(TvFocusZone.GROUPS);
        Map<String, List<IptvChannel>> channelsByGroup = TvScreen$lambda$6(state).getChannelsByGroup();
        String str = (String) kotlin.collections.x.p0(i10, list);
        if (str == null) {
            str = "";
        }
        List<IptvChannel> list2 = channelsByGroup.get(str);
        if (list2 == null) {
            list2 = kotlin.collections.z.f19728i;
        }
        int iPreferredIndexFor = preferredIndexFor(list2, TvScreen$lambda$20(mutableState2), TvScreen$lambda$23(mutableState3));
        mutableIntState2.setIntValue(iPreferredIndexFor < 0 ? 0 : iPreferredIndexFor);
        IptvChannel iptvChannel = (IptvChannel) kotlin.collections.x.p0(iPreferredIndexFor, list2);
        mutableState2.setValue(iptvChannel != null ? iptvChannel.getId() : null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$2$0$1$0(MutableIntState mutableIntState, MutableState mutableState, int i10) {
        mutableIntState.setIntValue(i10);
        TvScreen$lambda$27(mutableState, true);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$2$0$2$0(MutableState mutableState) {
        TvScreen$lambda$27(mutableState, false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$2$0$3$0(TvViewModel tvViewModel, String str) {
        tvViewModel.toggleFavoriteGroup(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$2$0$4$0(TvViewModel tvViewModel, String str) {
        tvViewModel.toggleHiddenGroup(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$2$0$5$0(TvViewModel tvViewModel, String str) {
        tvViewModel.moveGroupUp(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$2$0$6$0(TvViewModel tvViewModel, String str) {
        tvViewModel.moveGroupToTop(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$2$0$7$0(TvViewModel tvViewModel, String str) {
        tvViewModel.moveGroupDown(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$2$0$8$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(TvScreen$lambda$118$1(state));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$2$1$0$2$0(IptvChannel iptvChannel, MutableState mutableState, MutableState mutableState2) {
        if (iptvChannel != null) {
            mutableState.setValue(iptvChannel.getId());
            TvScreen$lambda$30(mutableState2, true);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlayerView TvScreen$lambda$118$2$1$0$3$0$0(MutableState mutableState, Context context) {
        PlayerView playerView = new PlayerView(context);
        playerView.setKeepScreenOn(true);
        mutableState.setValue(playerView);
        playerView.setPlayer(null);
        playerView.setUseController(false);
        playerView.setResizeMode(0);
        playerView.setKeepContentOnPlayerReset(true);
        playerView.setShutterBackgroundColor(-16119286);
        return playerView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$2$1$0$3$1$0(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, PlayerView playerView) {
        playerView.setKeepScreenOn(true);
        mutableState.setValue(playerView);
        if (!TvScreen$lambda$29(mutableState2)) {
            playerView.setResizeMode(0);
            if (playerView.getPlayer() != TvScreen$lambda$94(mutableState3)) {
                playerView.setPlayer(TvScreen$lambda$94(mutableState3));
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$2$1$1$0(List list, MutableIntState mutableIntState, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, int i10) {
        IptvChannel iptvChannel = (IptvChannel) kotlin.collections.x.p0(i10, list);
        t0 t0Var = t0.f22605a;
        if (iptvChannel == null) {
            return t0Var;
        }
        mutableIntState.setIntValue(i10);
        mutableState.setValue(TvFocusZone.GUIDE);
        if (kotlin.jvm.internal.p.a(TvScreen$lambda$23(mutableState2), iptvChannel.getId())) {
            mutableState3.setValue(iptvChannel.getId());
            TvScreen$lambda$30(mutableState4, true);
        } else {
            mutableState3.setValue(iptvChannel.getId());
            mutableState2.setValue(iptvChannel.getId());
        }
        return t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$3$0(MutableState mutableState, MutableState mutableState2) {
        TvScreen$lambda$33(mutableState, !TvScreen$lambda$32(mutableState));
        if (TvScreen$lambda$32(mutableState)) {
            TvScreen$lambda$36(mutableState2, System.currentTimeMillis());
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlayerView TvScreen$lambda$118$4$1$0(MutableState mutableState, Context context) {
        PlayerView playerView = new PlayerView(context);
        playerView.setKeepScreenOn(true);
        mutableState.setValue(playerView);
        playerView.setPlayer(null);
        playerView.setUseController(false);
        playerView.setResizeMode(0);
        playerView.setKeepContentOnPlayerReset(true);
        playerView.setShutterBackgroundColor(0);
        return playerView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$4$2$0(MutableState mutableState, PlayerView playerView) {
        playerView.setKeepScreenOn(true);
        mutableState.setValue(playerView);
        playerView.setResizeMode(0);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$4$3(IptvChannel iptvChannel, IptvProgram iptvProgram, IptvProgram iptvProgram2, boolean z, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-299907807, i10, -1, "com.arflix.tv.ui.screens.tv.TvScreen.<anonymous>.<anonymous>.<anonymous> (TvScreen.kt:1392)");
        }
        FullscreenEpgOverlay(iptvChannel, iptvProgram, iptvProgram2, z, composer, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x02a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 TvScreen$lambda$118$4$4(androidx.compose.runtime.MutableState r35, androidx.compose.runtime.MutableState r36, final java.util.List r37, final androidx.compose.runtime.MutableState r38, final java.util.Map r39, final androidx.compose.runtime.MutableIntState r40, final androidx.compose.runtime.MutableState r41, final androidx.compose.runtime.MutableState r42, androidx.compose.animation.AnimatedVisibilityScope r43, androidx.compose.runtime.Composer r44, int r45) {
        /*
            Method dump skipped, instruction units count: 687
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt.TvScreen$lambda$118$4$4(androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, java.util.List, androidx.compose.runtime.MutableState, java.util.Map, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$4$4$0$0$0(MutableState mutableState, MutableState mutableState2) {
        TvScreen$lambda$30(mutableState, false);
        TvScreen$lambda$33(mutableState2, false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$4$4$0$1$0$0(List list, MutableState mutableState, Map map, MutableIntState mutableIntState, MutableState mutableState2, MutableState mutableState3) {
        Integer num;
        if (!list.isEmpty()) {
            String strTvScreen$lambda$23 = TvScreen$lambda$23(mutableState);
            int iIntValue = (strTvScreen$lambda$23 == null || (num = (Integer) map.get(strTvScreen$lambda$23)) == null) ? -1 : num.intValue();
            int iW = iIntValue <= 0 ? t7.a.w(list) : iIntValue - 1;
            IptvChannel iptvChannel = (IptvChannel) list.get(iW);
            mutableIntState.setIntValue(iW);
            mutableState2.setValue(iptvChannel.getId());
            mutableState.setValue(iptvChannel.getId());
            TvScreen$lambda$36(mutableState3, System.currentTimeMillis());
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$118$4$4$0$1$1$0(List list, MutableState mutableState, Map map, MutableIntState mutableIntState, MutableState mutableState2, MutableState mutableState3) {
        Integer num;
        if (!list.isEmpty()) {
            String strTvScreen$lambda$23 = TvScreen$lambda$23(mutableState);
            int iIntValue = (strTvScreen$lambda$23 == null || (num = (Integer) map.get(strTvScreen$lambda$23)) == null) ? -1 : num.intValue();
            int size = iIntValue < 0 ? 0 : (iIntValue + 1) % list.size();
            IptvChannel iptvChannel = (IptvChannel) list.get(size);
            mutableIntState.setIntValue(size);
            mutableState2.setValue(iptvChannel.getId());
            mutableState.setValue(iptvChannel.getId());
            TvScreen$lambda$36(mutableState3, System.currentTimeMillis());
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$119(TvViewModel tvViewModel, Profile profile, String str, String str2, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, int i10, int i11, Composer composer, int i12) {
        TvScreen(tvViewModel, profile, str, str2, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState TvScreen$lambda$13$0() {
        return SnapshotIntStateKt.mutableIntStateOf(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState TvScreen$lambda$16$0() {
        return SnapshotIntStateKt.mutableIntStateOf(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState TvScreen$lambda$19$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String TvScreen$lambda$20(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState TvScreen$lambda$22$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String TvScreen$lambda$23(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean TvScreen$lambda$26(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TvScreen$lambda$27(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState TvScreen$lambda$28$0(String str) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(str != null), null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean TvScreen$lambda$29(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TvScreen$lambda$30(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean TvScreen$lambda$32(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TvScreen$lambda$33(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long TvScreen$lambda$35(MutableState<Long> mutableState) {
        return mutableState.getValue().longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TvScreen$lambda$36(MutableState<Long> mutableState, long j10) {
        mutableState.setValue(Long.valueOf(j10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long TvScreen$lambda$38(MutableState<Long> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableLongState TvScreen$lambda$43$0() {
        return SnapshotLongStateKt.mutableLongStateOf(0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean TvScreen$lambda$47(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TvScreen$lambda$48(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean TvScreen$lambda$50(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TvScreen$lambda$51(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean TvScreen$lambda$54(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void TvScreen$lambda$55(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TvUiState TvScreen$lambda$6(State<TvUiState> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$60$0(r7.a aVar, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4) {
        if (TvScreen$lambda$26(mutableState)) {
            TvScreen$lambda$27(mutableState, false);
        } else if (TvScreen$lambda$29(mutableState2) && TvScreen$lambda$32(mutableState3)) {
            TvScreen$lambda$33(mutableState3, false);
        } else if (TvScreen$lambda$29(mutableState2)) {
            TvScreen$lambda$30(mutableState2, false);
            TvScreen$lambda$33(mutableState3, false);
        } else if (TvScreen$lambda$8(mutableState4) == TvFocusZone.GUIDE) {
            mutableState4.setValue(TvFocusZone.GROUPS);
        } else if (TvScreen$lambda$8(mutableState4) == TvFocusZone.GROUPS) {
            mutableState4.setValue(TvFocusZone.SIDEBAR);
        } else {
            aVar.invoke();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvScreen$lambda$61$0(MutableLongState mutableLongState) {
        mutableLongState.setLongValue(SystemClock.elapsedRealtime());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set TvScreen$lambda$62$0(State state) {
        return kotlin.collections.x.g1(TvScreen$lambda$6(state).getSnapshot().getFavoriteGroups());
    }

    private static final Set<String> TvScreen$lambda$63(State<? extends Set<String>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set TvScreen$lambda$64$0(State state) {
        return kotlin.collections.x.g1(TvScreen$lambda$6(state).getSnapshot().getFavoriteChannels());
    }

    private static final Set<String> TvScreen$lambda$65(State<? extends Set<String>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState TvScreen$lambda$7$0(State state) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TvScreen$lambda$6(state).isConfigured() ? TvFocusZone.GROUPS : TvFocusZone.SIDEBAR, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String TvScreen$lambda$71(State<String> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TvFocusZone TvScreen$lambda$8(MutableState<TvFocusZone> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean TvScreen$lambda$91(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TvScreen$lambda$92(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ExoPlayer TvScreen$lambda$94(MutableState<ExoPlayer> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlayerView TvScreen$lambda$97(MutableState<PlayerView> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TvScreen$prepareStream(HlsMediaSource.Factory factory, MutableState<ExoPlayer> mutableState, MutableState<Boolean> mutableState2, String str, DrmInfo drmInfo) {
        String licenseUrl;
        ExoPlayer exoPlayerTvScreen$lambda$94 = TvScreen$lambda$94(mutableState);
        if (exoPlayerTvScreen$lambda$94 == null || TvScreen$lambda$91(mutableState2)) {
            return;
        }
        exoPlayerTvScreen$lambda$94.stop();
        exoPlayerTvScreen$lambda$94.clearMediaItems();
        MediaItem.Builder liveConfiguration = new MediaItem.Builder().setUri(str).setLiveConfiguration(new MediaItem.LiveConfiguration.Builder().setMinPlaybackSpeed(1.0f).setMaxPlaybackSpeed(1.0f).setTargetOffsetMs(4000L).build());
        if (drmInfo != null) {
            ClearKeyUtil clearKeyUtil = ClearKeyUtil.INSTANCE;
            MediaItem.DrmConfiguration.Builder builder = new MediaItem.DrmConfiguration.Builder(clearKeyUtil.drmSchemeToUuid(drmInfo.getScheme()));
            if (!kotlin.jvm.internal.p.a(drmInfo.getScheme(), "clearkey") || (licenseUrl = drmInfo.getLicenseUrl()) == null || kotlin.text.o.h0(licenseUrl)) {
                String licenseUrl2 = drmInfo.getLicenseUrl();
                if (licenseUrl2 != null && !kotlin.text.o.h0(licenseUrl2)) {
                    builder.setLicenseUri(kotlin.text.o.H0(drmInfo.getLicenseUrl(), "|"));
                }
            } else {
                String strBuildClearKeyLicenseUri = clearKeyUtil.buildClearKeyLicenseUri(drmInfo.getLicenseUrl());
                if (strBuildClearKeyLicenseUri != null) {
                    builder.setLicenseUri(strBuildClearKeyLicenseUri);
                }
            }
            liveConfiguration.setDrmConfiguration(builder.build());
        }
        MediaItem mediaItemBuild = liveConfiguration.build();
        String lowerCase = str.toLowerCase(Locale.ROOT);
        if (kotlin.text.o.T(lowerCase, ".m3u8", false) || kotlin.text.o.T(lowerCase, "/hls", false) || kotlin.text.o.T(lowerCase, "format=hls", false)) {
            exoPlayerTvScreen$lambda$94.setMediaSource(factory.createMediaSource(mediaItemBuild));
        } else {
            exoPlayerTvScreen$lambda$94.setMediaItem(mediaItemBuild);
        }
        exoPlayerTvScreen$lambda$94.prepare();
        exoPlayerTvScreen$lambda$94.setPlayWhenReady(true);
    }

    public static /* synthetic */ void TvScreen$prepareStream$default(HlsMediaSource.Factory factory, MutableState mutableState, MutableState mutableState2, String str, DrmInfo drmInfo, int i10, Object obj) {
        if ((i10 & 16) != 0) {
            drmInfo = null;
        }
        TvScreen$prepareStream(factory, mutableState, mutableState2, str, drmInfo);
    }

    private static final LegacyTvBufferProfile buildLegacyTvBufferProfile(int i10, boolean z) {
        if (i10 < 256) {
            i10 = 256;
        }
        return new LegacyTvBufferProfile((z || i10 <= 384) ? AnimationConstants.DURATION_KEN_BURNS : 25000, (z || i10 <= 384) ? 120000 : 150000, 1000, (z || i10 <= 384) ? AuthApiStatusCodes.AUTH_API_INVALID_CREDENTIALS : 4000, ((z || i10 <= 256) ? 64 : i10 <= 384 ? 96 : i10 <= 512 ? 128 : 160) * ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES, (z || i10 <= 384) ? 10000 : 15000);
    }

    private static final List<ProgramSegment> buildTimelineSegments(List<IptvProgram> list, IptvProgram iptvProgram, List<IptvProgram> list2, long j10, long j11) {
        long j12;
        long j13;
        long j14 = j11 - j10;
        if (j14 < 1) {
            j14 = 1;
        }
        float f10 = j14;
        z6.c cVarN = t7.a.n();
        for (IptvProgram iptvProgram2 : list) {
            cVarN.add(new TimedProgram(iptvProgram2.getStartUtcMillis(), iptvProgram2.getEndUtcMillis(), iptvProgram2, false, true));
        }
        if (iptvProgram != null) {
            cVarN.add(new TimedProgram(iptvProgram.getStartUtcMillis(), iptvProgram.getEndUtcMillis(), iptvProgram, true, false));
        }
        for (IptvProgram iptvProgram3 : list2) {
            cVarN.add(new TimedProgram(iptvProgram3.getStartUtcMillis(), iptvProgram3.getEndUtcMillis(), iptvProgram3, false, false));
        }
        z6.c cVarE = t7.a.e(cVarN);
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        ListIterator listIterator = cVarE.listIterator(0);
        while (true) {
            z6.a aVar = (z6.a) listIterator;
            if (!aVar.hasNext()) {
                break;
            }
            Object next = aVar.next();
            TimedProgram timedProgram = (TimedProgram) next;
            if (hashSet.add(new x6.j0(Long.valueOf(timedProgram.getStart()), Long.valueOf(timedProgram.getEnd()), timedProgram.getProgram().getTitle()))) {
                arrayList.add(next);
            }
        }
        List<TimedProgram> listW0 = kotlin.collections.x.W0(arrayList, new Comparator() { // from class: com.arflix.tv.ui.screens.tv.TvScreenKt$buildTimelineSegments$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e(Long.valueOf(((TvScreenKt.TimedProgram) t2).getStart()), Long.valueOf(((TvScreenKt.TimedProgram) t10).getStart()));
            }
        });
        ArrayList arrayList2 = new ArrayList();
        long j15 = j10;
        for (TimedProgram timedProgram2 : listW0) {
            long jMax = Math.max(qb.d.o(timedProgram2.getStart(), j10, j11), j15);
            long jO = qb.d.o(timedProgram2.getEnd(), j10, j11);
            if (jO > jMax) {
                if (jMax > j15) {
                    float fBuildTimelineSegments$ratio = buildTimelineSegments$ratio(j10, j11, f10, j15);
                    j12 = j15;
                    j13 = jMax;
                    arrayList2.add(new ProgramSegment("", fBuildTimelineSegments$ratio, buildTimelineSegments$ratio(j10, j11, f10, j13), false, true, false, 32, null));
                } else {
                    j12 = j15;
                    j13 = jMax;
                }
                float fM = qb.d.m((jO - j13) / f10, 0.0f, 1.0f);
                if (fM > 0.006f) {
                    String strBuildTimelineSegments$labelWithTime = buildTimelineSegments$labelWithTime(timedProgram2.getProgram(), fM);
                    float fBuildTimelineSegments$ratio2 = buildTimelineSegments$ratio(j10, j11, f10, j13);
                    j15 = jO;
                    arrayList2.add(new ProgramSegment(strBuildTimelineSegments$labelWithTime, fBuildTimelineSegments$ratio2, buildTimelineSegments$ratio(j10, j11, f10, j15), timedProgram2.isNow(), false, timedProgram2.isPast(), 16, null));
                } else {
                    j15 = jO;
                }
                if (j15 < j12) {
                    j15 = j12;
                }
            }
        }
        long j16 = j15;
        if (j16 < j11) {
            arrayList2.add(new ProgramSegment("", buildTimelineSegments$ratio(j10, j11, f10, j16), buildTimelineSegments$ratio(j10, j11, f10, j11), false, true, false, 32, null));
        }
        return mergeAdjacentTimelineSegments(arrayList2);
    }

    private static final String buildTimelineSegments$labelWithTime(IptvProgram iptvProgram, float f10) {
        return f10 >= 0.16f ? androidx.compose.foundation.c.t(formatProgramTime(iptvProgram.getStartUtcMillis()), "  ", iptvProgram.getTitle()) : iptvProgram.getTitle();
    }

    private static final float buildTimelineSegments$ratio(long j10, long j11, float f10, long j12) {
        return qb.d.m((qb.d.o(j12, j10, j11) - j10) / f10, 0.0f, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ExoPlayer createTvExoPlayer(Context context, DefaultMediaSourceFactory defaultMediaSourceFactory) {
        Object systemService = context.getSystemService("activity");
        ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
        int memoryClass = activityManager != null ? activityManager.getMemoryClass() : RendererCapabilities.DECODER_SUPPORT_MASK;
        boolean z = false;
        if (activityManager != null && activityManager.isLowRamDevice()) {
            z = true;
        }
        LegacyTvBufferProfile legacyTvBufferProfileBuildLegacyTvBufferProfile = buildLegacyTvBufferProfile(memoryClass, z);
        ExoPlayer exoPlayerBuild = new ExoPlayer.Builder(context).setMediaSourceFactory(defaultMediaSourceFactory).setLoadControl(new DefaultLoadControl.Builder().setBufferDurationsMs(legacyTvBufferProfileBuildLegacyTvBufferProfile.getMinBufferMs(), legacyTvBufferProfileBuildLegacyTvBufferProfile.getMaxBufferMs(), legacyTvBufferProfileBuildLegacyTvBufferProfile.getBufferForPlaybackMs(), legacyTvBufferProfileBuildLegacyTvBufferProfile.getBufferForPlaybackAfterRebufferMs()).setTargetBufferBytes(legacyTvBufferProfileBuildLegacyTvBufferProfile.getTargetBufferBytes()).setPrioritizeTimeOverSizeThresholds(true).setBackBuffer(legacyTvBufferProfileBuildLegacyTvBufferProfile.getBackBufferMs(), true).build()).build();
        exoPlayerBuild.setPlayWhenReady(true);
        exoPlayerBuild.setVideoScalingMode(1);
        return exoPlayerBuild;
    }

    private static final String formatProgramTime(long j10) {
        return programTimeFormatter.format(Instant.ofEpochMilli(j10).atZone(ZoneId.systemDefault()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isPriorityGuideGroup(String str) {
        if (kotlin.jvm.internal.p.a(str, TvViewModelKt.FAVORITES_GROUP_NAME)) {
            return true;
        }
        List listH = TvScreenRegexes.INSTANCE.getNON_ALPHANUMERIC_REGEX().h(str.toLowerCase(Locale.ROOT));
        ArrayList arrayList = new ArrayList();
        for (Object obj : listH) {
            if (!kotlin.text.o.h0((String) obj)) {
                arrayList.add(obj);
            }
        }
        Set setG1 = kotlin.collections.x.g1(arrayList);
        return setG1.contains("netherlands") || setG1.contains("nederland") || setG1.contains("nl");
    }

    private static final List<ProgramSegment> mergeAdjacentTimelineSegments(List<ProgramSegment> list) {
        if (list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (ProgramSegment programSegment : list) {
            ProgramSegment programSegment2 = (ProgramSegment) kotlin.collections.x.y0(arrayList);
            if (programSegment2 != null && kotlin.text.u.L(programSegment2.getLabel(), programSegment.getLabel(), true) && programSegment2.isNow() == programSegment.isNow() && programSegment2.isFiller() == programSegment.isFiller() && programSegment2.isPast() == programSegment.isPast()) {
                arrayList.set(t7.a.w(arrayList), ProgramSegment.copy$default(programSegment2, null, 0.0f, programSegment.getEndRatio(), false, false, false, 59, null));
            } else {
                arrayList.add(programSegment);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int preferredIndexFor(List<IptvChannel> list, String str, String str2) {
        int i10;
        if (list.isEmpty()) {
            return 0;
        }
        int i11 = -1;
        if (str != null) {
            Iterator<IptvChannel> it = list.iterator();
            i10 = 0;
            while (it.hasNext()) {
                if (kotlin.jvm.internal.p.a(it.next().getId(), str)) {
                    break;
                }
                i10++;
            }
            i10 = -1;
        } else {
            i10 = -1;
        }
        if (i10 >= 0) {
            return i10;
        }
        if (str2 != null) {
            Iterator<IptvChannel> it2 = list.iterator();
            int i12 = 0;
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                if (kotlin.jvm.internal.p.a(it2.next().getId(), str2)) {
                    i11 = i12;
                    break;
                }
                i12++;
            }
        }
        if (i11 >= 0) {
            return i11;
        }
        return 0;
    }

    private static final String preferredStartupGroup(List<String> list, Map<String, ? extends List<IptvChannel>> map) {
        Object next;
        List<IptvChannel> list2 = map.get(TvViewModelKt.FAVORITES_GROUP_NAME);
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        if (list2 == null) {
            list2 = zVar;
        }
        if (!list2.isEmpty()) {
            return TvViewModelKt.FAVORITES_GROUP_NAME;
        }
        Iterator<T> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            List<IptvChannel> list3 = map.get((String) next);
            if (list3 == null) {
                list3 = zVar;
            }
            if (!list3.isEmpty()) {
                break;
            }
        }
        return (String) next;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00f4, code lost:
    
        if (androidx.compose.foundation.lazy.LazyListState.scrollToItem$default(r16, r4, 0, r6, 2, null) == r15) goto L60;
     */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object smoothScrollTo(androidx.compose.foundation.lazy.LazyListState r16, int r17, d7.d<? super x6.t0> r18) {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvScreenKt.smoothScrollTo(androidx.compose.foundation.lazy.LazyListState, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TvFocusZone toTvFocusZone(String str, boolean z, boolean z5) {
        String upperCase = str.toUpperCase(Locale.ROOT);
        return upperCase.equals("SIDEBAR") ? z ? TvFocusZone.GROUPS : TvFocusZone.SIDEBAR : upperCase.equals("GROUPS") ? z ? TvFocusZone.GROUPS : TvFocusZone.SIDEBAR : z5 ? TvFocusZone.GUIDE : z ? TvFocusZone.GROUPS : TvFocusZone.SIDEBAR;
    }
}
