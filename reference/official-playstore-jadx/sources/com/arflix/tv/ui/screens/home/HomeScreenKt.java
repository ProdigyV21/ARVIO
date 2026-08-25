package com.arflix.tv.ui.screens.home;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.view.Precision;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.BoxWithConstraintsScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.LazyListStateKt;
import androidx.compose.foundation.pager.PagerScope;
import androidx.compose.foundation.pager.PagerState;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MenuKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.fragment.app.a2;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.datasource.okhttp.OkHttpDataSource;
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.extractor.WavUtil;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.ui.PlayerView;
import androidx.tv.material3.TextKt;
import coil.ImageLoader;
import coil.compose.AsyncImageKt;
import coil.compose.AsyncImagePainter;
import coil.request.ImageRequest;
import com.arflix.tv.R;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.NextEpisode;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.data.repository.CloudSyncStatus;
import com.arflix.tv.network.OkHttpProvider;
import com.arflix.tv.ui.components.MobileHeroBannerKt;
import com.arflix.tv.ui.components.n1;
import com.arflix.tv.ui.focus.ArvioDpadFocusKt;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.util.MediaBadgesKt;
import io.ktor.http.ContentDisposition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000à\u0001\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u001f\u001a\u0017\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0002\u0010\u0003\u001a\u0017\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004H\u0003¢\u0006\u0004\b\u0006\u0010\u0007\u001a#\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0002¢\u0006\u0004\b\n\u0010\u000b\u001a\u001f\u0010\u000e\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000e\u0010\u000f\u001a/\u0010\u0014\u001a\u0004\u0018\u00010\u00132\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0017\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001a+\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00000\b2\u0006\u0010\u0019\u001a\u00020\u00002\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00130\bH\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u001f\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a/\u0010#\u001a\u00020\u00102\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00000\b2\b\u0010!\u001a\u0004\u0018\u00010\u00002\u0006\u0010\"\u001a\u00020\u0010H\u0000¢\u0006\u0004\b#\u0010$\u001a7\u0010)\u001a\u00020\u00102\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00000\b2\b\u0010&\u001a\u0004\u0018\u00010\u00002\u0006\u0010\"\u001a\u00020\u00102\u0006\u0010(\u001a\u00020'H\u0000¢\u0006\u0004\b)\u0010*\u001a\u001d\u0010+\u001a\u00020\u00102\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\bH\u0002¢\u0006\u0004\b+\u0010,\u001a\u0019\u0010-\u001a\u00020'2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0013H\u0002¢\u0006\u0004\b-\u0010.\u001a\u0017\u00102\u001a\u0002012\u0006\u00100\u001a\u00020/H\u0002¢\u0006\u0004\b2\u00103\u001a.\u0010:\u001a\u000209*\u0002042\u0006\u00106\u001a\u0002052\u0006\u00107\u001a\u00020\u00102\b\b\u0002\u00108\u001a\u00020'H\u0082@¢\u0006\u0004\b:\u0010;\u001a7\u0010A\u001a\u0002092\b\u0010<\u001a\u0004\u0018\u00010\u00002\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100=2\b\b\u0002\u0010@\u001a\u00020?H\u0003¢\u0006\u0004\bA\u0010B\u001aå\u0002\u0010_\u001a\u0002092\b\b\u0002\u0010D\u001a\u00020C2\u000e\b\u0002\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u00002\u0014\b\u0002\u0010I\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000H2\n\b\u0002\u0010K\u001a\u0004\u0018\u00010J2*\b\u0002\u0010N\u001a$\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u0002090L2\u0014\b\u0002\u0010P\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002090O2\u000e\b\u0002\u0010R\u001a\b\u0012\u0004\u0012\u0002090Q2\u000e\b\u0002\u0010S\u001a\b\u0012\u0004\u0012\u0002090Q2<\b\u0002\u0010Y\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0000¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(W\u0012\u0015\u0012\u0013\u0018\u00010\u0000¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(X\u0012\u0004\u0012\u0002090T20\b\u0002\u0010[\u001a*\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u0000\u0012\u0004\u0012\u0002090Z2\u000e\b\u0002\u0010\\\u001a\b\u0012\u0004\u0012\u0002090Q2\u000e\b\u0002\u0010]\u001a\b\u0012\u0004\u0012\u0002090Q2\u000e\b\u0002\u0010^\u001a\b\u0012\u0004\u0012\u0002090QH\u0007¢\u0006\u0004\b_\u0010`\u001aA\u0010d\u001a\u0002092\u0006\u0010\u0016\u001a\u00020\u00132\b\u0010a\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010b\u001a\u0004\u0018\u00010\u00002\b\b\u0002\u0010c\u001a\u00020'2\b\b\u0002\u0010@\u001a\u00020?H\u0003¢\u0006\u0004\bd\u0010e\u001a\u0017\u0010h\u001a\u00020\u00002\u0006\u0010g\u001a\u00020fH\u0002¢\u0006\u0004\bh\u0010i\u001a\u0017\u0010j\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0013H\u0002¢\u0006\u0004\bj\u0010\u0018\u001a1\u0010n\u001a\u0002092\u0006\u0010k\u001a\u00020\u00102\u0006\u0010l\u001a\u00020'2\u0006\u0010m\u001a\u00020'2\b\b\u0002\u0010@\u001a\u00020?H\u0003¢\u0006\u0004\bn\u0010o\u001aù\u0001\u0010{\u001a\u0002092\b\u0010p\u001a\u0004\u0018\u00010\u00132\b\u0010q\u001a\u0004\u0018\u00010\u00002\b\u0010r\u001a\u0004\u0018\u00010\u00002\u0006\u0010t\u001a\u00020s2\b\b\u0002\u0010u\u001a\u00020'2\b\b\u0002\u0010c\u001a\u00020'2*\b\u0002\u0010N\u001a$\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u0002090L2<\b\u0002\u0010Y\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0000¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(W\u0012\u0015\u0012\u0013\u0018\u00010\u0000¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(X\u0012\u0004\u0012\u0002090T2\u0014\b\u0002\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020'0O2\u0016\b\u0002\u0010w\u001a\u0010\u0012\u0004\u0012\u00020\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00000O2\u0016\b\u0002\u0010x\u001a\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00000OH\u0003¢\u0006\u0004\by\u0010z\u001aF\u0010\u0080\u0001\u001a\u0002092\u0006\u0010\u0016\u001a\u00020\u00132\b\u0010b\u001a\u0004\u0018\u00010\u00002\u0006\u0010t\u001a\u00020s2\f\u0010|\u001a\b\u0012\u0004\u0012\u0002090Q2\f\u0010}\u001a\b\u0012\u0004\u0012\u0002090QH\u0003¢\u0006\u0004\b~\u0010\u007f\u001a\u008d\u0001\u0010\u0082\u0001\u001a\u0002092\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0015\b\u0002\u0010\u0081\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000H2\n\b\u0002\u0010K\u001a\u0004\u0018\u00010J2\u000e\b\u0002\u0010R\u001a\b\u0012\u0004\u0012\u0002090Q2\u000e\b\u0002\u0010]\u001a\b\u0012\u0004\u0012\u0002090Q2(\u0010N\u001a$\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u0002090LH\u0003¢\u0006\u0006\b\u0082\u0001\u0010\u0083\u0001\u001aÈ\u0005\u0010 \u0001\u001a\u0002092\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0013\u0010\u0081\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000H2\b\u0010\u0085\u0001\u001a\u00030\u0084\u00012\u0007\u0010\u0086\u0001\u001a\u00020'2\u0007\u0010\u0087\u0001\u001a\u00020f2\u0006\u0010t\u001a\u00020s2\u0007\u0010\u0088\u0001\u001a\u00020f2\u0007\u0010\u0089\u0001\u001a\u00020'2\u0007\u0010\u008a\u0001\u001a\u00020'2\t\b\u0002\u0010\u008b\u0001\u001a\u00020'2\u000f\b\u0002\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u0002090Q2\b\b\u0002\u0010u\u001a\u00020'2\n\b\u0002\u0010p\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010r\u001a\u0004\u0018\u00010\u00002\u000e\b\u0002\u0010|\u001a\b\u0012\u0004\u0012\u0002090Q2\u000e\b\u0002\u0010}\u001a\b\u0012\u0004\u0012\u0002090Q2\b\u0010K\u001a\u0004\u0018\u00010J2\t\b\u0002\u0010\u008d\u0001\u001a\u00020\u00102\t\b\u0002\u0010\u008e\u0001\u001a\u00020\u00002\n\b\u0002\u0010\u0090\u0001\u001a\u00030\u008f\u00012\t\b\u0002\u0010\u0091\u0001\u001a\u00020'2\u0015\b\u0002\u0010\u0092\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020'0H2\t\b\u0002\u0010\u0093\u0001\u001a\u00020'2\u0015\b\u0002\u0010\u0094\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002090O2\u0015\b\u0002\u0010\u0095\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u0002090O2\u001b\b\u0002\u0010\u0096\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u0002090T2(\u0010N\u001a$\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u0002090L2\u0012\u0010P\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002090O2\f\u0010R\u001a\b\u0012\u0004\u0012\u0002090Q2\f\u0010S\u001a\b\u0012\u0004\u0012\u0002090Q2:\u0010Y\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0000¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(W\u0012\u0015\u0012\u0013\u0018\u00010\u0000¢\u0006\f\bU\u0012\b\bV\u0012\u0004\b\b(X\u0012\u0004\u0012\u0002090T2$\u0010x\u001a \u0012\u0014\u0012\u00120\u0010¢\u0006\r\bU\u0012\t\bV\u0012\u0005\b\b(\u0097\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00000O2\u0015\b\u0002\u0010\u0098\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020'0O2\u0015\b\u0002\u0010\u0099\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u0002090O2\f\u0010\\\u001a\b\u0012\u0004\u0012\u0002090Q2\f\u0010]\u001a\b\u0012\u0004\u0012\u0002090Q2\f\u0010^\u001a\b\u0012\u0004\u0012\u0002090Q2\u000b\b\u0002\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u00002\t\b\u0002\u0010\u009b\u0001\u001a\u00020f2\t\b\u0002\u0010\u009c\u0001\u001a\u0002052\u0019\u0010\u009d\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u0002090TH\u0003¢\u0006\u0006\b\u009e\u0001\u0010\u009f\u0001\u001aÀ\u0003\u0010¥\u0001\u001a\u0002092\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0013\u0010\u0081\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000H2\b\u0010\u0085\u0001\u001a\u00030\u0084\u00012\u0007\u0010\u0086\u0001\u001a\u00020'2\u0006\u0010t\u001a\u00020s2\u0007\u0010\u0088\u0001\u001a\u00020f2\u0007\u0010\u0089\u0001\u001a\u00020'2\b\b\u0002\u0010u\u001a\u00020'2\u0015\b\u0002\u0010\u0092\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020'0H2\t\b\u0002\u0010\u0093\u0001\u001a\u00020'2\u0015\b\u0002\u0010\u0094\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002090O2\u0015\b\u0002\u0010\u0095\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u0002090O2\n\b\u0002\u0010p\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010r\u001a\u0004\u0018\u00010\u00002\u000e\b\u0002\u0010|\u001a\b\u0012\u0004\u0012\u0002090Q2\u000e\b\u0002\u0010}\u001a\b\u0012\u0004\u0012\u0002090Q2\n\b\u0002\u0010K\u001a\u0004\u0018\u00010J2\u000e\b\u0002\u0010R\u001a\b\u0012\u0004\u0012\u0002090Q2\u000e\b\u0002\u0010]\u001a\b\u0012\u0004\u0012\u0002090Q2*\b\u0002\u0010N\u001a$\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u0002090L2\u001b\b\u0002\u0010\u0096\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u0002090T2\u000b\b\u0002\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u00002\t\b\u0002\u0010\u009b\u0001\u001a\u00020f2\t\b\u0002\u0010\u009c\u0001\u001a\u0002052\u0013\u0010¡\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u0002090O2\u001d\b\u0002\u0010¢\u0001\u001a\u0016\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u000209\u0018\u00010TH\u0003¢\u0006\u0006\b£\u0001\u0010¤\u0001\u001a\u009d\u0002\u0010©\u0001\u001a\u0002092\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0013\u0010\u0081\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000H2\u0006\u0010t\u001a\u00020s2\u0007\u0010\u0089\u0001\u001a\u00020'2\n\b\u0002\u0010K\u001a\u0004\u0018\u00010J2\u000e\b\u0002\u0010R\u001a\b\u0012\u0004\u0012\u0002090Q2\u000e\b\u0002\u0010]\u001a\b\u0012\u0004\u0012\u0002090Q2\u0015\b\u0002\u0010\u0092\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020'0H2\u0015\b\u0002\u0010\u0094\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002090O2*\b\u0002\u0010N\u001a$\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0012\u0004\u0012\u0002090L2\u0013\u0010¡\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u0002090O2\u001d\b\u0002\u0010¢\u0001\u001a\u0016\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u000209\u0018\u00010T2\u001b\b\u0002\u0010¦\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u0002090TH\u0003¢\u0006\u0006\b§\u0001\u0010¨\u0001\u001aê\u0001\u0010¬\u0001\u001a\u0002092\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0013\u0010\u0081\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000H2\b\u0010\u0085\u0001\u001a\u00030\u0084\u00012\u0007\u0010\u0086\u0001\u001a\u00020'2\u0006\u0010t\u001a\u00020s2\u0007\u0010\u0088\u0001\u001a\u00020f2\u0007\u0010\u0089\u0001\u001a\u00020'2\u0015\b\u0002\u0010\u0092\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020'0H2\t\b\u0002\u0010\u0093\u0001\u001a\u00020'2\u0015\b\u0002\u0010\u0094\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002090O2\u0015\b\u0002\u0010\u0095\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u0002090O2\u000b\b\u0002\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u00002\t\b\u0002\u0010\u009b\u0001\u001a\u00020f2\t\b\u0002\u0010\u009c\u0001\u001a\u0002052\u0013\u0010¡\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u0002090OH\u0003¢\u0006\u0006\bª\u0001\u0010«\u0001\u001a-\u0010²\u0001\u001a\u00020s2\u0007\u0010\u00ad\u0001\u001a\u00020s2\u0007\u0010®\u0001\u001a\u00020s2\u0007\u0010¯\u0001\u001a\u00020sH\u0003¢\u0006\u0006\b°\u0001\u0010±\u0001\u001a.\u0010¹\u0001\u001a\u0002092\u0007\u0010³\u0001\u001a\u00020\u00002\u0007\u0010´\u0001\u001a\u00020\u00002\b\u0010¶\u0001\u001a\u00030µ\u0001H\u0003¢\u0006\u0006\b·\u0001\u0010¸\u0001\u001a\u001c\u0010º\u0001\u001a\u0002092\b\b\u0002\u0010@\u001a\u00020?H\u0003¢\u0006\u0006\bº\u0001\u0010»\u0001\u001a\u0012\u0010¼\u0001\u001a\u000209H\u0003¢\u0006\u0006\b¼\u0001\u0010½\u0001\u001a\u001b\u0010¿\u0001\u001a\u0002092\u0007\u0010¾\u0001\u001a\u00020\u0000H\u0003¢\u0006\u0006\b¿\u0001\u0010À\u0001\u001aJ\u0010Ê\u0001\u001a\u0002092\u0007\u0010´\u0001\u001a\u00020\u00002\b\u0010Â\u0001\u001a\u00030Á\u00012\u0007\u0010Ã\u0001\u001a\u00020\u00102\u0007\u0010Ä\u0001\u001a\u00020s2\u0007\u0010Å\u0001\u001a\u00020s2\b\u0010Ç\u0001\u001a\u00030Æ\u0001H\u0003¢\u0006\u0006\bÈ\u0001\u0010É\u0001\u001a\u001b\u0010Ë\u0001\u001a\u0002092\u0007\u0010´\u0001\u001a\u00020\u0000H\u0003¢\u0006\u0006\bË\u0001\u0010À\u0001\u001aå\u0001\u0010Õ\u0001\u001a\u0002092\u0006\u0010\u0005\u001a\u00020\u00042\u0013\u0010\u0081\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000H2\u0007\u0010Ì\u0001\u001a\u00020'2\t\b\u0002\u0010Í\u0001\u001a\u00020'2\t\b\u0002\u0010\u0089\u0001\u001a\u00020'2\t\b\u0002\u0010®\u0001\u001a\u00020s2\t\b\u0002\u0010Î\u0001\u001a\u00020'2\t\b\u0002\u0010\u0093\u0001\u001a\u00020'2\u000f\b\u0002\u0010Ï\u0001\u001a\b\u0012\u0004\u0012\u0002090Q2\u0007\u0010Ð\u0001\u001a\u00020\u00102\u0007\u0010Ñ\u0001\u001a\u00020'2\u000b\b\u0002\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u00002\t\b\u0002\u0010\u009b\u0001\u001a\u00020f2\t\b\u0002\u0010\u009c\u0001\u001a\u0002052\u0013\u0010¡\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u0002090O2\u0019\u0010Ò\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u0002090TH\u0003¢\u0006\u0006\bÓ\u0001\u0010Ô\u0001\"#\u0010Ö\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00000H8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÖ\u0001\u0010×\u0001\"#\u0010Ø\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00000H8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bØ\u0001\u0010×\u0001¨\u0006û\u0001²\u0006\u0011\u0010Ù\u0001\u001a\u0004\u0018\u00010\u00008\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010Ú\u0001\u001a\u0004\u0018\u00010\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Û\u0001\u001a\u00020'8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010Ý\u0001\u001a\u00030Ü\u00018\nX\u008a\u0084\u0002²\u0006\u000f\u0010\u0087\u0001\u001a\u00020f8\n@\nX\u008a\u008e\u0002²\u0006\u0013\u0010Þ\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\b8\nX\u008a\u0084\u0002²\u0006\u0013\u0010ß\u0001\u001a\b\u0012\u0004\u0012\u00020\u00040\b8\nX\u008a\u0084\u0002²\u0006\u000f\u0010à\u0001\u001a\u0004\u0018\u00010\u00138\nX\u008a\u0084\u0002²\u0006\u000f\u0010á\u0001\u001a\u00020'8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010â\u0001\u001a\u00020'8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010ã\u0001\u001a\u00020'8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010ä\u0001\u001a\u0004\u0018\u00010\u00138\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010å\u0001\u001a\u00020'8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010æ\u0001\u001a\u00020'8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010ç\u0001\u001a\u0004\u0018\u00010\u00108\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010è\u0001\u001a\u00020'8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010é\u0001\u001a\u00020'8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010ê\u0001\u001a\u0004\u0018\u0001018\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010ë\u0001\u001a\u0004\u0018\u00010\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010ì\u0001\u001a\u0004\u0018\u00010\u00108\nX\u008a\u0084\u0002²\u0006\r\u0010í\u0001\u001a\u0002058\nX\u008a\u0084\u0002²\u0006\u0011\u0010î\u0001\u001a\u0004\u0018\u00010\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010ï\u0001\u001a\u0004\u0018\u00010\u00008\nX\u008a\u0084\u0002²\u0006\r\u0010ð\u0001\u001a\u0002058\nX\u008a\u0084\u0002²\u0006\r\u0010ñ\u0001\u001a\u0002058\nX\u008a\u0084\u0002²\u0006\u000f\u0010ò\u0001\u001a\u00020'8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010ó\u0001\u001a\u00020f8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010ô\u0001\u001a\u00020'8\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010!\u001a\u0004\u0018\u00010\u00008\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010õ\u0001\u001a\u0004\u0018\u00010\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Ñ\u0001\u001a\u00020'8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010ö\u0001\u001a\u00020\u00108\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010÷\u0001\u001a\u00020\u00108\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010ø\u0001\u001a\u00020\u00108\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010ù\u0001\u001a\u00020\u00108\n@\nX\u008a\u008e\u0002²\u0006\r\u0010ú\u0001\u001a\u00020s8\nX\u008a\u0084\u0002"}, d2 = {"", "value", "cleanOverviewText", "(Ljava/lang/String;)Ljava/lang/String;", "Lcom/arflix/tv/data/model/Category;", "category", "localizedCategoryTitle", "(Lcom/arflix/tv/data/model/Category;Landroidx/compose/runtime/Composer;I)Ljava/lang/String;", "", "categories", "deduplicateHomeCategories", "(Ljava/util/List;)Ljava/util/List;", "first", "second", "chooseContinueWatchingCategory", "(Lcom/arflix/tv/data/model/Category;Lcom/arflix/tv/data/model/Category;)Lcom/arflix/tv/data/model/Category;", "", "rowIndex", "itemIndex", "Lcom/arflix/tv/data/model/MediaItem;", "getFocusedItem", "(Ljava/util/List;II)Lcom/arflix/tv/data/model/MediaItem;", "item", "homeRowItemKey", "(Lcom/arflix/tv/data/model/MediaItem;)Ljava/lang/String;", "categoryId", "items", "stableHomeRowItemKeys", "(Ljava/lang/String;Ljava/util/List;)Ljava/util/List;", TtmlNode.TAG_LAYOUT, "stableHomeRowKey", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "categoryIds", "preferredCategoryId", "fallbackIndex", "resolveHomeCategoryIndex", "(Ljava/util/List;Ljava/lang/String;I)I", "itemKeys", "preferredItemKey", "", "hasMore", "resolveHomeItemIndex", "(Ljava/util/List;Ljava/lang/String;IZ)I", "preferredHomeStartRowIndex", "(Ljava/util/List;)I", "isActionableHomeItem", "(Lcom/arflix/tv/data/model/MediaItem;)Z", "Landroid/content/Context;", "context", "Lcom/arflix/tv/ui/screens/home/HomeHeroPlaybackHandles;", "createHomeHeroPlaybackHandles", "(Landroid/content/Context;)Lcom/arflix/tv/ui/screens/home/HomeHeroPlaybackHandles;", "Landroidx/compose/foundation/lazy/LazyListState;", "", "deltaPx", "durationMillis", "isRtl", "Lx6/t0;", "animateHomeScrollDelta", "(Landroidx/compose/foundation/lazy/LazyListState;FIZLd7/d;)Ljava/lang/Object;", "backdropUrl", "Lx6/x;", "backdropSize", "Landroidx/compose/ui/Modifier;", "modifier", "HomeBackdropCrossfade", "(Ljava/lang/String;Lx6/x;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "Lcom/arflix/tv/ui/screens/home/HomeViewModel;", "viewModel", "preloadedCategories", "preloadedHeroItem", "preloadedHeroLogoUrl", "", "preloadedLogoCache", "Lcom/arflix/tv/data/model/Profile;", "currentProfile", "Lkotlin/Function4;", "Lcom/arflix/tv/data/model/MediaType;", "onNavigateToDetails", "Lkotlin/Function1;", "onNavigateToCollection", "Lkotlin/Function0;", "onNavigateToSearch", "onNavigateToWatchlist", "Lkotlin/Function2;", "Lx6/y;", ContentDisposition.Parameters.Name, "channelId", "streamUrl", "onNavigateToTv", "Lkotlin/Function5;", "onNavigateToPlayer", "onNavigateToSettings", "onSwitchProfile", "onExitApp", "HomeScreen", "(Lcom/arflix/tv/ui/screens/home/HomeViewModel;Ljava/util/List;Lcom/arflix/tv/data/model/MediaItem;Ljava/lang/String;Ljava/util/Map;Lcom/arflix/tv/data/model/Profile;Lr7/r;Lr7/l;Lr7/a;Lr7/a;Lr7/p;Lr7/s;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;III)V", "logoUrl", "overviewOverride", "showBudget", "HeroSection", "(Lcom/arflix/tv/data/model/MediaItem;Ljava/lang/String;Ljava/lang/String;ZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "", "budget", "formatBudgetCompact", "(J)Ljava/lang/String;", "imdbRatingFor", "rank", "isFocused", "compact", "TopRankRibbon", "(IZZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "heroItem", "heroLogoUrl", "heroOverviewOverride", "Landroidx/compose/ui/unit/Dp;", "contentStartPadding", "isMobile", "isIptvItem", "getIptvChannelId", "getIptvStreamUrl", "HomeHeroLayer-WSk2ftU", "(Lcom/arflix/tv/data/model/MediaItem;Ljava/lang/String;Ljava/lang/String;FZZLr7/r;Lr7/p;Lr7/l;Lr7/l;Lr7/l;Landroidx/compose/runtime/Composer;III)V", "HomeHeroLayer", "onPlay", "onDetails", "MobileHeroOverlay-942rkJo", "(Lcom/arflix/tv/data/model/MediaItem;Ljava/lang/String;FLr7/a;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "MobileHeroOverlay", "cardLogoUrls", "MobileHeroCarousel", "(Ljava/util/List;Ljava/util/Map;Lcom/arflix/tv/data/model/Profile;Lr7/a;Lr7/a;Lr7/r;Landroidx/compose/runtime/Composer;II)V", "Lcom/arflix/tv/ui/screens/home/HomeFocusState;", "focusState", "limitRowsDuringStartup", "suppressSelectUntilMs", "fastScrollThresholdMs", "usePosterCards", "isContextMenuOpen", "trailerIsPlaying", "onTrailerStop", "profileCount", "clockFormat", "Lcom/arflix/tv/data/repository/CloudSyncStatus;", "syncStatus", "hasUpdateBadge", "categoryHasMoreMap", "smoothScrolling", "onLoadMoreCategory", "onItemFocusedPrefetch", "onMobileCategoryVisiblePosition", "itemId", "isSportsHomeItem", "onSportsHomeItemClick", "featuredTrailerKey", "featuredTrailerDelayMs", "featuredTrailerVolume", "onOpenContextMenu", "HomeInputLayer-0DYc4_I", "(Ljava/util/List;Ljava/util/Map;Lcom/arflix/tv/ui/screens/home/HomeFocusState;ZJFJZZZLr7/a;ZLcom/arflix/tv/data/model/MediaItem;Ljava/lang/String;Lr7/a;Lr7/a;Lcom/arflix/tv/data/model/Profile;ILjava/lang/String;Lcom/arflix/tv/data/repository/CloudSyncStatus;ZLjava/util/Map;ZLr7/l;Lr7/l;Lr7/p;Lr7/r;Lr7/l;Lr7/a;Lr7/a;Lr7/p;Lr7/l;Lr7/l;Lr7/l;Lr7/a;Lr7/a;Lr7/a;Ljava/lang/String;JFLr7/p;Landroidx/compose/runtime/Composer;IIIIIII)V", "HomeInputLayer", "onItemClick", "onItemLongClick", "HomeRowsLayer-HGFtVhc", "(Ljava/util/List;Ljava/util/Map;Lcom/arflix/tv/ui/screens/home/HomeFocusState;ZFJZZLjava/util/Map;ZLr7/l;Lr7/l;Lcom/arflix/tv/data/model/MediaItem;Ljava/lang/String;Lr7/a;Lr7/a;Lcom/arflix/tv/data/model/Profile;Lr7/a;Lr7/a;Lr7/r;Lr7/p;Ljava/lang/String;JFLr7/l;Lr7/p;Landroidx/compose/runtime/Composer;IIII)V", "HomeRowsLayer", "onCategoryVisiblePosition", "MobileHomeRowsLayer-uS7v9H0", "(Ljava/util/List;Ljava/util/Map;FZLcom/arflix/tv/data/model/Profile;Lr7/a;Lr7/a;Ljava/util/Map;Lr7/l;Lr7/r;Lr7/l;Lr7/p;Lr7/p;Landroidx/compose/runtime/Composer;III)V", "MobileHomeRowsLayer", "TvHomeRowsLayer-GVfq81o", "(Ljava/util/List;Ljava/util/Map;Lcom/arflix/tv/ui/screens/home/HomeFocusState;ZFJZLjava/util/Map;ZLr7/l;Lr7/l;Ljava/lang/String;JFLr7/l;Landroidx/compose/runtime/Composer;III)V", "TvHomeRowsLayer", "itemWidth", "startPadding", "minimum", "lockedHomeRailEndPadding-Ynw60rg", "(FFFLandroidx/compose/runtime/Composer;I)F", "lockedHomeRailEndPadding", "label", "rating", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "ArcticFuseRatingBadge-XO-JAsU", "(Ljava/lang/String;Ljava/lang/String;JLandroidx/compose/runtime/Composer;I)V", "ArcticFuseRatingBadge", "PrimeLogo", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "IncludedWithPrimeBadge", "(Landroidx/compose/runtime/Composer;I)V", "text", "MetaPill", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "Lcoil/ImageLoader;", "imageLoader", "ratingFontSize", "logoWidth", "logoHeight", "Landroidx/compose/ui/graphics/Shadow;", "textShadow", "ImdbSvgRatingBadge-6PoWaU8", "(Ljava/lang/String;Lcoil/ImageLoader;IFFLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/runtime/Composer;I)V", "ImdbSvgRatingBadge", "ImdbBadge", "isCurrentRow", "isRanked", "categoryHasMore", "onLoadMore", "focusedItemIndex", "isFastScrolling", "onItemFocused", "ContentRow-IOMxRjY", "(Lcom/arflix/tv/data/model/Category;Ljava/util/Map;ZZZFZZLr7/a;IZLjava/lang/String;JFLr7/l;Lr7/p;Landroidx/compose/runtime/Composer;III)V", "ContentRow", "movieGenres", "Ljava/util/Map;", "tvGenres", "displayedBackdropUrl", "pendingBackdropUrl", "pendingBackdropReady", "Lcom/arflix/tv/ui/screens/home/HomeUiState;", "uiState", "sportsHomeRows", "latestDisplayCategories", "latestDisplayHeroItem", "startupEffectsSettled", "suppressHeroVideoPlayback", "showContextMenu", "contextMenuItem", "contextMenuIsContinueWatching", "contextMenuIsInWatchlist", "collectionVideoFinishedId", "isTrailerPlaying", "trailerSuppressed", "heroPlaybackHandles", "preparedHeroVideoUrl", "latestFocusedCollectionId", "heroVideoAlpha", "settledBackdrop", "latestCurrentBackdrop", "scale", "expandFraction", "selectPressedInHome", "selectDownAtMs", "rootHasFocus", "focusedCategoryId", "lastAppliedTargetIndex", "featuredExpandedForIndex", "lastScrollIndex", "lastScrollOffset", "animatedCardWidth", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class HomeScreenKt {
    private static final Map<Integer, String> movieGenres = kotlin.collections.h0.t0(new x6.x(28, "Action"), new x6.x(12, "Adventure"), new x6.x(16, "Animation"), new x6.x(35, "Comedy"), new x6.x(80, "Crime"), new x6.x(99, "Documentary"), new x6.x(18, "Drama"), new x6.x(10751, "Family"), new x6.x(14, "Fantasy"), new x6.x(36, "History"), new x6.x(27, "Horror"), new x6.x(10402, "Music"), new x6.x(9648, "Mystery"), new x6.x(10749, "Romance"), new x6.x(878, "Sci-Fi"), new x6.x(10770, "TV Movie"), new x6.x(53, "Thriller"), new x6.x(10752, "War"), new x6.x(37, "Western"));
    private static final Map<Integer, String> tvGenres = kotlin.collections.h0.t0(new x6.x(10759, "Action & Adventure"), new x6.x(16, "Animation"), new x6.x(35, "Comedy"), new x6.x(80, "Crime"), new x6.x(99, "Documentary"), new x6.x(18, "Drama"), new x6.x(10751, "Family"), new x6.x(10762, "Kids"), new x6.x(9648, "Mystery"), new x6.x(10763, "News"), new x6.x(10764, "Reality"), new x6.x(10765, "Sci-Fi & Fantasy"), new x6.x(10766, "Soap"), new x6.x(10767, "Talk"), new x6.x(10768, "War & Politics"), new x6.x(37, "Western"));

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToastType.values().length];
            try {
                iArr[ToastType.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ToastType.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ToastType.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.home.HomeScreenKt$animateHomeScrollDelta$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/ScrollScope;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$animateHomeScrollDelta$2", f = "HomeScreen.kt", l = {468}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ScrollScope, d7.d<? super t0>, Object> {
        final /* synthetic */ float $targetDelta;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(float f10, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$targetDelta = f10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final t0 invokeSuspend$lambda$0(kotlin.jvm.internal.h0 h0Var, ScrollScope scrollScope, float f10, float f11) {
            float f12 = f10 - h0Var.f19742i;
            if (Math.abs(f12) > 0.01f) {
                scrollScope.scrollBy(f12);
            }
            h0Var.f19742i = f10;
            return t0.f22605a;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$targetDelta, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(ScrollScope scrollScope, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(scrollScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ScrollScope scrollScope = (ScrollScope) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                kotlin.jvm.internal.h0 h0Var = new kotlin.jvm.internal.h0();
                float f10 = this.$targetDelta;
                SpringSpec springSpecSpring$default = AnimationSpecKt.spring$default(0.85f, 200.0f, null, 4, null);
                s0 s0Var = new s0(h0Var, scrollScope, 0);
                this.L$0 = null;
                this.L$1 = null;
                this.label = 1;
                Object objAnimate$default = SuspendAnimationKt.animate$default(0.0f, f10, 0.0f, springSpecSpring$default, s0Var, this, 4, null);
                e7.a aVar = e7.a.f15033i;
                if (objAnimate$default == aVar) {
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

    /* JADX INFO: renamed from: ArcticFuseRatingBadge-XO-JAsU, reason: not valid java name */
    private static final void m6238ArcticFuseRatingBadgeXOJAsU(String str, String str2, long j10, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1084584052);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(str2) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(j10) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1084584052, i11, -1, "com.arflix.tv.ui.screens.home.ArcticFuseRatingBadge (HomeScreen.kt:3239)");
            }
            Alignment.Companion companion = Alignment.INSTANCE;
            Alignment.Vertical centerVertically = companion.getCenterVertically();
            float f10 = 4;
            Arrangement.HorizontalOrVertical horizontalOrVerticalG = androidx.compose.foundation.c.g(f10, Arrangement.INSTANCE, composerStartRestartGroup, 693286680);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalG, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion2);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            Modifier modifierH = a2.h(f10, com.arflix.tv.data.repository.g.j(f10, companion2, j10), Dp.m5678constructorimpl(8), composerStartRestartGroup, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(companion, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierH);
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
            r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextStyle caption = arflixTypography.getCaption();
            long sp = TextUnitKt.getSp(9);
            FontWeight.Companion companion4 = FontWeight.INSTANCE;
            FontWeight bold = companion4.getBold();
            Color.Companion companion5 = Color.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(caption, companion5.m3498getBlack0d7_KjU(), sp, bold, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777208, null), composer2, i11 & 14, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            TextKt.m6020Text4IGK_g(str2, null, companion5.m3509getWhite0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(12), companion4.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer2, ((i11 >> 3) & 14) | RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new g0(j10, i10, str, str2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ArcticFuseRatingBadge_XO_JAsU$lambda$1(String str, String str2, long j10, int i10, Composer composer, int i11) {
        m6238ArcticFuseRatingBadgeXOJAsU(str, str2, j10, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x045f  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0478  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x047c  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x048d  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x0490  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x04a4  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x04af  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x04bf  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x04d0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:273:0x04d4  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x04e5  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x04fd  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0517  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0538  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x053b  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x054b  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x055c  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x055f  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x056f  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x05a0  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x05f0  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x05f3  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x05f9  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x05fc  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0602  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0605  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x060d  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x0613  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0668  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x066b  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x066e  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x06b3  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x06c8  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x06e5  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x06ef  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x070b  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x0712  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x0716  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x0719  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0740  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x0743  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x0753  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x0756  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x076a  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x0796  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x082c  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x0838  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x083c  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x084d  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x085b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x08a7  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x08b3  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x08b7  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x08d6  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0982  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x0987  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x09ba  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x09c6  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x09ca  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x09ee  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x0a2d  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x0a2f  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x0a38  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x0a3a  */
    /* JADX WARN: Removed duplicated region for block: B:433:0x0a46  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x0a48  */
    /* JADX WARN: Removed duplicated region for block: B:437:0x0a50  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x0a52  */
    /* JADX WARN: Removed duplicated region for block: B:441:0x0a71  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x0a73  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x0a97  */
    /* JADX WARN: Removed duplicated region for block: B:446:0x0a99  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x0aa6  */
    /* JADX WARN: Removed duplicated region for block: B:450:0x0aa8  */
    /* JADX WARN: Removed duplicated region for block: B:453:0x0abc  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x0abe  */
    /* JADX WARN: Removed duplicated region for block: B:461:0x0af3  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x0b50  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x0bd0  */
    /* JADX WARN: Removed duplicated region for block: B:468:0x0be1  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0bf6  */
    /* JADX WARN: Removed duplicated region for block: B:476:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x012f  */
    /* JADX INFO: renamed from: ContentRow-IOMxRjY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6239ContentRowIOMxRjY(final com.arflix.tv.data.model.Category r120, final java.util.Map<java.lang.String, java.lang.String> r121, final boolean r122, boolean r123, boolean r124, float r125, boolean r126, boolean r127, r7.a<x6.t0> r128, final int r129, final boolean r130, java.lang.String r131, long r132, float r134, final r7.l<? super com.arflix.tv.data.model.MediaItem, x6.t0> r135, final r7.p<? super com.arflix.tv.data.model.MediaItem, ? super java.lang.Integer, x6.t0> r136, androidx.compose.runtime.Composer r137, final int r138, final int r139, final int r140) {
        /*
            Method dump skipped, instruction units count: 3096
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt.m6239ContentRowIOMxRjY(com.arflix.tv.data.model.Category, java.util.Map, boolean, boolean, boolean, float, boolean, boolean, r7.a, int, boolean, java.lang.String, long, float, r7.l, r7.p, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ContentRow_IOMxRjY$lambda$19$1$0$0(List list, List list2, boolean z, boolean z5, r7.a aVar, boolean z10, Category category, boolean z11, int i10, Map map, boolean z12, boolean z13, float f10, String str, float f11, boolean z14, boolean z15, boolean z16, boolean z17, State state, State state2, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new HomeScreenKt$ContentRow_IOMxRjY$lambda$19$1$0$0$$inlined$itemsIndexed$1(new l(list2, 0), list), new HomeScreenKt$ContentRow_IOMxRjY$lambda$19$1$0$0$$inlined$itemsIndexed$2(list, z, z5), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new HomeScreenKt$ContentRow_IOMxRjY$lambda$19$1$0$0$$inlined$itemsIndexed$3(list, aVar, z10, category, z11, i10, z5, z, map, z12, z13, f10, str, f11, z14, z15, z16, z17, state, state2)));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object ContentRow_IOMxRjY$lambda$19$1$0$0$0(List list, int i10, MediaItem mediaItem) {
        return list.get(i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float ContentRow_IOMxRjY$lambda$19$1$0$0$2$8(State<Dp> state) {
        return state.getValue().m5692unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ContentRow_IOMxRjY$lambda$20(Category category, Map map, boolean z, boolean z5, boolean z10, float f10, boolean z11, boolean z12, r7.a aVar, int i10, boolean z13, String str, long j10, float f11, r7.l lVar, r7.p pVar, int i11, int i12, int i13, Composer composer, int i14) {
        m6239ContentRowIOMxRjY(category, map, z, z5, z10, f10, z11, z12, aVar, i10, z13, str, j10, f11, lVar, pVar, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), RecomposeScopeImplKt.updateChangedFlags(i12), i13);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0429  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x05c2  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0600  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x060e  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0638  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0644  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0648  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x066c  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0678  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0885  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0913  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0930  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0937  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0939  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0947  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x096b  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x098c  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x09a0  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x09a3  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x09b3  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x09bf  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x09f8  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0a04  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0a08  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0a19  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0a2e  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0a79  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x0a85  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0a89  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x0a9a  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0aad  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0abe  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0c06  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0c20  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0ca5  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0cb0  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0dc1  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0dcf  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x10f0  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x111a  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x111c  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x1130  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x1132  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x1137  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x117f  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x118b  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x118f  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x11b3  */
    /* JADX WARN: Removed duplicated region for block: B:401:0x126d  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x127c  */
    /* JADX WARN: Removed duplicated region for block: B:412:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x028c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void HeroSection(com.arflix.tv.data.model.MediaItem r112, java.lang.String r113, java.lang.String r114, boolean r115, androidx.compose.ui.Modifier r116, androidx.compose.runtime.Composer r117, int r118, int r119) {
        /*
            Method dump skipped, instruction units count: 4749
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt.HeroSection(com.arflix.tv.data.model.MediaItem, java.lang.String, java.lang.String, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HeroSection$lambda$2(MediaItem mediaItem, String str, String str2, boolean z, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        HeroSection(mediaItem, str, str2, z, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:119:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x025e  */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void HomeBackdropCrossfade(java.lang.String r28, x6.x r29, androidx.compose.ui.Modifier r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 783
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt.HomeBackdropCrossfade(java.lang.String, x6.x, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final ImageRequest HomeBackdropCrossfade$buildBackdropRequest(int i10, int i11, Context context, String str) {
        String str2 = str + "|" + i10 + "x" + i11;
        return new ImageRequest.Builder(context).data(str).size(i10, i11).precision(Precision.INEXACT).allowHardware(true).memoryCacheKey(str2).placeholderMemoryCacheKey(str2).crossfade(false).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String HomeBackdropCrossfade$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeBackdropCrossfade$lambda$13$1$1$0(Animatable animatable, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(((Number) animatable.getValue()).floatValue());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeBackdropCrossfade$lambda$13$1$2$0(MutableState mutableState, AsyncImagePainter.State.Success success) {
        HomeBackdropCrossfade$lambda$8(mutableState, true);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeBackdropCrossfade$lambda$14(String str, x6.x xVar, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        HomeBackdropCrossfade(str, xVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String HomeBackdropCrossfade$lambda$4(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean HomeBackdropCrossfade$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HomeBackdropCrossfade$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x033e  */
    /* JADX WARN: Removed duplicated region for block: B:142:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0142  */
    /* JADX INFO: renamed from: HomeHeroLayer-WSk2ftU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6240HomeHeroLayerWSk2ftU(final com.arflix.tv.data.model.MediaItem r24, final java.lang.String r25, final java.lang.String r26, final float r27, boolean r28, boolean r29, r7.r<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, ? super java.lang.Integer, ? super java.lang.Integer, x6.t0> r30, r7.p<? super java.lang.String, ? super java.lang.String, x6.t0> r31, r7.l<? super com.arflix.tv.data.model.MediaItem, java.lang.Boolean> r32, r7.l<? super com.arflix.tv.data.model.MediaItem, java.lang.String> r33, r7.l<? super java.lang.Integer, java.lang.String> r34, androidx.compose.runtime.Composer r35, final int r36, final int r37, final int r38) {
        /*
            Method dump skipped, instruction units count: 847
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt.m6240HomeHeroLayerWSk2ftU(com.arflix.tv.data.model.MediaItem, java.lang.String, java.lang.String, float, boolean, boolean, r7.r, r7.p, r7.l, r7.l, r7.l, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeHeroLayer_WSk2ftU$lambda$0$0(MediaType mediaType, int i10, Integer num, Integer num2) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeHeroLayer_WSk2ftU$lambda$1$0(String str, String str2) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean HomeHeroLayer_WSk2ftU$lambda$2$0(MediaItem mediaItem) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String HomeHeroLayer_WSk2ftU$lambda$3$0(MediaItem mediaItem) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String HomeHeroLayer_WSk2ftU$lambda$4$0(int i10) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeHeroLayer_WSk2ftU$lambda$6(MediaItem mediaItem, String str, String str2, float f10, boolean z, boolean z5, r7.r rVar, r7.p pVar, r7.l lVar, r7.l lVar2, r7.l lVar3, int i10, int i11, int i12, Composer composer, int i13) {
        m6240HomeHeroLayerWSk2ftU(mediaItem, str, str2, f10, z, z5, rVar, pVar, lVar, lVar2, lVar3, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:134:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x024a  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0348  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x03a7  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x03d0  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x0406  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x040f  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x0424  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x0434  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x0440  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0494  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x0496  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x0498  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x049c  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x04bc  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x04c5  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x04cc  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x04cf  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x04d4  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x04ee  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x0508  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x050c  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x050f  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x0513  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x0518  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x051e  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x0523  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x0527  */
    /* JADX WARN: Removed duplicated region for block: B:416:0x052a  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x052e  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x0531  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0535  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x0538  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x053c  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x0557  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x0576  */
    /* JADX WARN: Removed duplicated region for block: B:436:0x057a  */
    /* JADX WARN: Removed duplicated region for block: B:441:0x0596  */
    /* JADX WARN: Removed duplicated region for block: B:443:0x059b  */
    /* JADX WARN: Removed duplicated region for block: B:447:0x05b4  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x05b8  */
    /* JADX WARN: Removed duplicated region for block: B:453:0x05d4  */
    /* JADX WARN: Removed duplicated region for block: B:455:0x05da  */
    /* JADX WARN: Removed duplicated region for block: B:456:0x05dd  */
    /* JADX WARN: Removed duplicated region for block: B:459:0x05e3  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x05e6  */
    /* JADX WARN: Removed duplicated region for block: B:462:0x05ea  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x05ed  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x05f5  */
    /* JADX WARN: Removed duplicated region for block: B:467:0x0600  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x0610  */
    /* JADX WARN: Removed duplicated region for block: B:473:0x0624  */
    /* JADX WARN: Removed duplicated region for block: B:474:0x0626  */
    /* JADX WARN: Removed duplicated region for block: B:477:0x0631  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x0648  */
    /* JADX WARN: Removed duplicated region for block: B:483:0x065d  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x066b  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x067e  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x06c5  */
    /* JADX WARN: Removed duplicated region for block: B:491:0x06c7  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x06d8  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x0703  */
    /* JADX WARN: Removed duplicated region for block: B:498:0x0705  */
    /* JADX WARN: Removed duplicated region for block: B:501:0x0710  */
    /* JADX WARN: Removed duplicated region for block: B:502:0x0712  */
    /* JADX WARN: Removed duplicated region for block: B:505:0x071d  */
    /* JADX WARN: Removed duplicated region for block: B:509:0x072b  */
    /* JADX WARN: Removed duplicated region for block: B:513:0x076f  */
    /* JADX WARN: Removed duplicated region for block: B:514:0x0771  */
    /* JADX WARN: Removed duplicated region for block: B:517:0x077e  */
    /* JADX WARN: Removed duplicated region for block: B:519:0x0784  */
    /* JADX WARN: Removed duplicated region for block: B:522:0x07a6  */
    /* JADX WARN: Removed duplicated region for block: B:523:0x07a8  */
    /* JADX WARN: Removed duplicated region for block: B:526:0x07b1  */
    /* JADX WARN: Removed duplicated region for block: B:527:0x07b3  */
    /* JADX WARN: Removed duplicated region for block: B:534:0x07ca  */
    /* JADX WARN: Removed duplicated region for block: B:537:0x07de  */
    /* JADX WARN: Removed duplicated region for block: B:538:0x07e0  */
    /* JADX WARN: Removed duplicated region for block: B:541:0x07e7  */
    /* JADX WARN: Removed duplicated region for block: B:543:0x07ed  */
    /* JADX WARN: Removed duplicated region for block: B:780:0x0d89  */
    /* JADX WARN: Removed duplicated region for block: B:783:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: HomeInputLayer-0DYc4_I, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6241HomeInputLayer0DYc4_I(final java.util.List<com.arflix.tv.data.model.Category> r73, final java.util.Map<java.lang.String, java.lang.String> r74, final com.arflix.tv.ui.screens.home.HomeFocusState r75, final boolean r76, final long r77, final float r79, final long r80, final boolean r82, final boolean r83, boolean r84, r7.a<x6.t0> r85, boolean r86, com.arflix.tv.data.model.MediaItem r87, java.lang.String r88, r7.a<x6.t0> r89, r7.a<x6.t0> r90, final com.arflix.tv.data.model.Profile r91, int r92, java.lang.String r93, com.arflix.tv.data.repository.CloudSyncStatus r94, boolean r95, java.util.Map<java.lang.String, java.lang.Boolean> r96, boolean r97, r7.l<? super java.lang.String, x6.t0> r98, r7.l<? super com.arflix.tv.data.model.MediaItem, x6.t0> r99, r7.p<? super java.lang.String, ? super java.lang.Integer, x6.t0> r100, final r7.r<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, ? super java.lang.Integer, ? super java.lang.Integer, x6.t0> r101, final r7.l<? super java.lang.String, x6.t0> r102, final r7.a<x6.t0> r103, final r7.a<x6.t0> r104, final r7.p<? super java.lang.String, ? super java.lang.String, x6.t0> r105, final r7.l<? super java.lang.Integer, java.lang.String> r106, r7.l<? super com.arflix.tv.data.model.MediaItem, java.lang.Boolean> r107, r7.l<? super com.arflix.tv.data.model.MediaItem, x6.t0> r108, final r7.a<x6.t0> r109, final r7.a<x6.t0> r110, final r7.a<x6.t0> r111, java.lang.String r112, long r113, float r115, final r7.p<? super com.arflix.tv.data.model.MediaItem, ? super java.lang.Boolean, x6.t0> r116, androidx.compose.runtime.Composer r117, final int r118, final int r119, final int r120, final int r121, final int r122, final int r123, final int r124) {
        /*
            Method dump skipped, instruction units count: 3533
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt.m6241HomeInputLayer0DYc4_I(java.util.List, java.util.Map, com.arflix.tv.ui.screens.home.HomeFocusState, boolean, long, float, long, boolean, boolean, boolean, r7.a, boolean, com.arflix.tv.data.model.MediaItem, java.lang.String, r7.a, r7.a, com.arflix.tv.data.model.Profile, int, java.lang.String, com.arflix.tv.data.repository.CloudSyncStatus, boolean, java.util.Map, boolean, r7.l, r7.l, r7.p, r7.r, r7.l, r7.a, r7.a, r7.p, r7.l, r7.l, r7.l, r7.a, r7.a, r7.a, java.lang.String, long, float, r7.p, androidx.compose.runtime.Composer, int, int, int, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean HomeInputLayer_0DYc4_I$lambda$10(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HomeInputLayer_0DYc4_I$lambda$11(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean HomeInputLayer_0DYc4_I$lambda$16(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void HomeInputLayer_0DYc4_I$lambda$17(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState HomeInputLayer_0DYc4_I$lambda$18$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String HomeInputLayer_0DYc4_I$lambda$19(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeInputLayer_0DYc4_I$lambda$27$0(HomeFocusState homeFocusState, r7.a aVar, List list, MutableState mutableState, MutableLongState mutableLongState) {
        String id;
        HomeInputLayer_0DYc4_I$lambda$11(mutableState, false);
        mutableLongState.setLongValue(0L);
        if (homeFocusState.isSidebarFocused()) {
            aVar.invoke();
        } else {
            Category category = (Category) kotlin.collections.x.p0(homeFocusState.getCurrentRowIndex(), list);
            if (category != null && (id = category.getId()) != null) {
                homeFocusState.getRowItemIndicesByCategoryId().put(id, Integer.valueOf(homeFocusState.getCurrentItemIndex()));
            }
            homeFocusState.setSidebarFocused(true);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeInputLayer_0DYc4_I$lambda$3$0(String str) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeInputLayer_0DYc4_I$lambda$33$0(MutableState mutableState, MutableState mutableState2, MutableLongState mutableLongState, FocusState focusState) {
        HomeInputLayer_0DYc4_I$lambda$17(mutableState, focusState.getHasFocus());
        if (!focusState.getHasFocus()) {
            HomeInputLayer_0DYc4_I$lambda$11(mutableState2, false);
            mutableLongState.setLongValue(0L);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 HomeInputLayer_0DYc4_I$lambda$34$0$0(r7.l r6, r7.l r7, r7.p r8, r7.l r9, r7.l r10, r7.r r11, com.arflix.tv.data.model.MediaItem r12) {
        /*
            boolean r0 = isActionableHomeItem(r12)
            x6.t0 r1 = x6.t0.f22605a
            if (r0 != 0) goto L9
            return r1
        L9:
            java.lang.Object r6 = r6.invoke(r12)
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L19
            r7.invoke(r12)
            return r1
        L19:
            java.lang.String r6 = r12.getStatus()
            r7 = 0
            r0 = 1
            r2 = 0
            if (r6 == 0) goto L3b
            java.lang.String r3 = "iptv:"
            java.lang.String r6 = kotlin.text.o.r0(r6, r3)
            java.lang.String r4 = r12.getStatus()
            if (r4 == 0) goto L3b
            boolean r3 = kotlin.text.u.P(r4, r3, r7)
            if (r3 != r0) goto L3b
            boolean r3 = kotlin.text.o.h0(r6)
            if (r3 != 0) goto L3b
            goto L3c
        L3b:
            r6 = r2
        L3c:
            java.lang.String r3 = r12.getStatus()
            if (r3 == 0) goto L5b
            java.lang.String r4 = "collection:"
            java.lang.String r3 = kotlin.text.o.r0(r3, r4)
            java.lang.String r5 = r12.getStatus()
            if (r5 == 0) goto L5b
            boolean r7 = kotlin.text.u.P(r5, r4, r7)
            if (r7 != r0) goto L5b
            boolean r7 = kotlin.text.o.h0(r3)
            if (r7 != 0) goto L5b
            goto L5c
        L5b:
            r3 = r2
        L5c:
            if (r6 == 0) goto L6e
            int r7 = r12.getId()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Object r7 = r9.invoke(r7)
            r8.invoke(r6, r7)
            return r1
        L6e:
            if (r3 == 0) goto L74
            r10.invoke(r3)
            return r1
        L74:
            com.arflix.tv.data.model.MediaType r6 = r12.getMediaType()
            int r7 = r12.getId()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            com.arflix.tv.data.model.NextEpisode r8 = r12.getNextEpisode()
            if (r8 == 0) goto L8f
            int r8 = r8.getSeasonNumber()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            goto L90
        L8f:
            r8 = r2
        L90:
            com.arflix.tv.data.model.NextEpisode r9 = r12.getNextEpisode()
            if (r9 == 0) goto L9e
            int r9 = r9.getEpisodeNumber()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r9)
        L9e:
            r11.invoke(r6, r7, r8, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt.HomeInputLayer_0DYc4_I$lambda$34$0$0(r7.l, r7.l, r7.p, r7.l, r7.l, r7.r, com.arflix.tv.data.model.MediaItem):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeInputLayer_0DYc4_I$lambda$34$1$0(r7.l lVar, r7.l lVar2, r7.p pVar, MediaItem mediaItem, boolean z) {
        if (((Boolean) lVar.invoke(mediaItem)).booleanValue()) {
            lVar2.invoke(mediaItem);
        } else {
            pVar.invoke(mediaItem, Boolean.valueOf(z));
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeInputLayer_0DYc4_I$lambda$35(List list, Map map, HomeFocusState homeFocusState, boolean z, long j10, float f10, long j11, boolean z5, boolean z10, boolean z11, r7.a aVar, boolean z12, MediaItem mediaItem, String str, r7.a aVar2, r7.a aVar3, Profile profile, int i10, String str2, CloudSyncStatus cloudSyncStatus, boolean z13, Map map2, boolean z14, r7.l lVar, r7.l lVar2, r7.p pVar, r7.r rVar, r7.l lVar3, r7.a aVar4, r7.a aVar5, r7.p pVar2, r7.l lVar4, r7.l lVar5, r7.l lVar6, r7.a aVar6, r7.a aVar7, r7.a aVar8, String str3, long j12, float f11, r7.p pVar3, int i11, int i12, int i13, int i14, int i15, int i16, int i17, Composer composer, int i18) {
        m6241HomeInputLayer0DYc4_I(list, map, homeFocusState, z, j10, f10, j11, z5, z10, z11, aVar, z12, mediaItem, str, aVar2, aVar3, profile, i10, str2, cloudSyncStatus, z13, map2, z14, lVar, lVar2, pVar, rVar, lVar3, aVar4, aVar5, pVar2, lVar4, lVar5, lVar6, aVar6, aVar7, aVar8, str3, j12, f11, pVar3, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), RecomposeScopeImplKt.updateChangedFlags(i12), RecomposeScopeImplKt.updateChangedFlags(i13), RecomposeScopeImplKt.updateChangedFlags(i14), RecomposeScopeImplKt.updateChangedFlags(i15), i16, i17);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeInputLayer_0DYc4_I$lambda$4$0(MediaItem mediaItem) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeInputLayer_0DYc4_I$lambda$5$0(String str, int i10) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean HomeInputLayer_0DYc4_I$lambda$6$0(MediaItem mediaItem) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeInputLayer_0DYc4_I$lambda$7$0(MediaItem mediaItem) {
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0274  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0368  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x038a  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x03b0  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x041b  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0425  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x042a  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x043f  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x04f1  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x055d  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x0586  */
    /* JADX WARN: Removed duplicated region for block: B:334:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0128  */
    /* JADX INFO: renamed from: HomeRowsLayer-HGFtVhc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6242HomeRowsLayerHGFtVhc(final java.util.List<com.arflix.tv.data.model.Category> r47, final java.util.Map<java.lang.String, java.lang.String> r48, final com.arflix.tv.ui.screens.home.HomeFocusState r49, final boolean r50, final float r51, final long r52, final boolean r54, boolean r55, java.util.Map<java.lang.String, java.lang.Boolean> r56, boolean r57, r7.l<? super java.lang.String, x6.t0> r58, r7.l<? super com.arflix.tv.data.model.MediaItem, x6.t0> r59, com.arflix.tv.data.model.MediaItem r60, java.lang.String r61, r7.a<x6.t0> r62, r7.a<x6.t0> r63, com.arflix.tv.data.model.Profile r64, r7.a<x6.t0> r65, r7.a<x6.t0> r66, r7.r<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, ? super java.lang.Integer, ? super java.lang.Integer, x6.t0> r67, r7.p<? super java.lang.String, ? super java.lang.Integer, x6.t0> r68, java.lang.String r69, long r70, float r72, final r7.l<? super com.arflix.tv.data.model.MediaItem, x6.t0> r73, r7.p<? super com.arflix.tv.data.model.MediaItem, ? super java.lang.Boolean, x6.t0> r74, androidx.compose.runtime.Composer r75, final int r76, final int r77, final int r78, final int r79) {
        /*
            Method dump skipped, instruction units count: 1452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt.m6242HomeRowsLayerHGFtVhc(java.util.List, java.util.Map, com.arflix.tv.ui.screens.home.HomeFocusState, boolean, float, long, boolean, boolean, java.util.Map, boolean, r7.l, r7.l, com.arflix.tv.data.model.MediaItem, java.lang.String, r7.a, r7.a, com.arflix.tv.data.model.Profile, r7.a, r7.a, r7.r, r7.p, java.lang.String, long, float, r7.l, r7.p, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeRowsLayer_HGFtVhc$lambda$0$0(String str) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeRowsLayer_HGFtVhc$lambda$1$0(MediaItem mediaItem) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeRowsLayer_HGFtVhc$lambda$6$0(MediaType mediaType, int i10, Integer num, Integer num2) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeRowsLayer_HGFtVhc$lambda$7$0(String str, int i10) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeRowsLayer_HGFtVhc$lambda$8$0(r7.p pVar, List list, r7.l lVar, String str, int i10) {
        List<MediaItem> items;
        pVar.invoke(str, Integer.valueOf(i10));
        Iterator it = list.iterator();
        int i11 = 0;
        while (true) {
            if (!it.hasNext()) {
                i11 = -1;
                break;
            }
            if (kotlin.jvm.internal.p.a(((Category) it.next()).getId(), str)) {
                break;
            }
            i11++;
        }
        Category category = (Category) kotlin.collections.x.p0(i11, list);
        MediaItem mediaItem = (category == null || (items = category.getItems()) == null) ? null : (MediaItem) kotlin.collections.x.p0(i10, items);
        if (mediaItem != null) {
            lVar.invoke(mediaItem);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeRowsLayer_HGFtVhc$lambda$9(List list, Map map, HomeFocusState homeFocusState, boolean z, float f10, long j10, boolean z5, boolean z10, Map map2, boolean z11, r7.l lVar, r7.l lVar2, MediaItem mediaItem, String str, r7.a aVar, r7.a aVar2, Profile profile, r7.a aVar3, r7.a aVar4, r7.r rVar, r7.p pVar, String str2, long j11, float f11, r7.l lVar3, r7.p pVar2, int i10, int i11, int i12, int i13, Composer composer, int i14) {
        m6242HomeRowsLayerHGFtVhc(list, map, homeFocusState, z, f10, j10, z5, z10, map2, z11, lVar, lVar2, mediaItem, str, aVar, aVar2, profile, aVar3, aVar4, rVar, pVar, str2, j11, f11, lVar3, pVar2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), RecomposeScopeImplKt.updateChangedFlags(i12), i13);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:1001:0x1961  */
    /* JADX WARN: Removed duplicated region for block: B:1004:0x1970  */
    /* JADX WARN: Removed duplicated region for block: B:1008:0x1998  */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:1010:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:101:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0364  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0384  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x039d  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x040c  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x043d  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x043f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x044d  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x044f  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x045a  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x046c  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0486  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x04ce  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x04d2  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x04dd  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x04df  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x04f8  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x050b  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0537  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0553  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x055c  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0597  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x05b4  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x05c7  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x05fa  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x062b  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x062d  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x0638  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x063a  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0649  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x067b  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x0684  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x06c0  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x070d  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x0715  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x071c  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x0736  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x0763  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x0772  */
    /* JADX WARN: Removed duplicated region for block: B:378:0x0782  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x0792  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x07a0  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x07ba  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x07c5  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x07ef  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:401:0x07f8  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x0806  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x0824  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x0851  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x085d  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x086d  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x0876  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x0883  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x0891  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x08a1  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x08af  */
    /* JADX WARN: Removed duplicated region for block: B:433:0x08c3  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x08cd  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x08f5  */
    /* JADX WARN: Removed duplicated region for block: B:446:0x0936  */
    /* JADX WARN: Removed duplicated region for block: B:451:0x0980  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x09c0  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x09ed  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x09f8  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0a03  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x0a10  */
    /* JADX WARN: Removed duplicated region for block: B:482:0x0a25  */
    /* JADX WARN: Removed duplicated region for block: B:485:0x0a2d  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x0a36  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x0a38  */
    /* JADX WARN: Removed duplicated region for block: B:491:0x0a3a  */
    /* JADX WARN: Removed duplicated region for block: B:499:0x0a58  */
    /* JADX WARN: Removed duplicated region for block: B:500:0x0a66  */
    /* JADX WARN: Removed duplicated region for block: B:503:0x0a76  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x0a84  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x0a8a  */
    /* JADX WARN: Removed duplicated region for block: B:508:0x0a93  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:511:0x0aa0  */
    /* JADX WARN: Removed duplicated region for block: B:514:0x0abf  */
    /* JADX WARN: Removed duplicated region for block: B:515:0x0ac4  */
    /* JADX WARN: Removed duplicated region for block: B:518:0x0adc  */
    /* JADX WARN: Removed duplicated region for block: B:519:0x0ae9  */
    /* JADX WARN: Removed duplicated region for block: B:526:0x0b0a  */
    /* JADX WARN: Removed duplicated region for block: B:529:0x0b23  */
    /* JADX WARN: Removed duplicated region for block: B:530:0x0b2c  */
    /* JADX WARN: Removed duplicated region for block: B:533:0x0b3b  */
    /* JADX WARN: Removed duplicated region for block: B:536:0x0b4a  */
    /* JADX WARN: Removed duplicated region for block: B:537:0x0b4f  */
    /* JADX WARN: Removed duplicated region for block: B:540:0x0b5a  */
    /* JADX WARN: Removed duplicated region for block: B:543:0x0b6b  */
    /* JADX WARN: Removed duplicated region for block: B:544:0x0b6e  */
    /* JADX WARN: Removed duplicated region for block: B:546:0x0b71  */
    /* JADX WARN: Removed duplicated region for block: B:549:0x0b7d  */
    /* JADX WARN: Removed duplicated region for block: B:581:0x0c98  */
    /* JADX WARN: Removed duplicated region for block: B:589:0x0cb4  */
    /* JADX WARN: Removed duplicated region for block: B:592:0x0cbf  */
    /* JADX WARN: Removed duplicated region for block: B:599:0x0cfd  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:602:0x0d20  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:623:0x0da8  */
    /* JADX WARN: Removed duplicated region for block: B:628:0x0dff  */
    /* JADX WARN: Removed duplicated region for block: B:649:0x0e8d  */
    /* JADX WARN: Removed duplicated region for block: B:665:0x0ee8  */
    /* JADX WARN: Removed duplicated region for block: B:667:0x0eee  */
    /* JADX WARN: Removed duplicated region for block: B:670:0x0efe  */
    /* JADX WARN: Removed duplicated region for block: B:671:0x0f09  */
    /* JADX WARN: Removed duplicated region for block: B:677:0x0f43  */
    /* JADX WARN: Removed duplicated region for block: B:680:0x0f6a  */
    /* JADX WARN: Removed duplicated region for block: B:685:0x0f8e  */
    /* JADX WARN: Removed duplicated region for block: B:688:0x0fc4  */
    /* JADX WARN: Removed duplicated region for block: B:691:0x0fd0  */
    /* JADX WARN: Removed duplicated region for block: B:692:0x0fd4  */
    /* JADX WARN: Removed duplicated region for block: B:699:0x0ff8  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:702:0x1017  */
    /* JADX WARN: Removed duplicated region for block: B:703:0x1023  */
    /* JADX WARN: Removed duplicated region for block: B:706:0x1042  */
    /* JADX WARN: Removed duplicated region for block: B:707:0x1044  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:710:0x1050  */
    /* JADX WARN: Removed duplicated region for block: B:711:0x1052  */
    /* JADX WARN: Removed duplicated region for block: B:714:0x1061  */
    /* JADX WARN: Removed duplicated region for block: B:715:0x1063  */
    /* JADX WARN: Removed duplicated region for block: B:718:0x106e  */
    /* JADX WARN: Removed duplicated region for block: B:722:0x107e  */
    /* JADX WARN: Removed duplicated region for block: B:726:0x10b4  */
    /* JADX WARN: Removed duplicated region for block: B:727:0x10b7  */
    /* JADX WARN: Removed duplicated region for block: B:730:0x10bf  */
    /* JADX WARN: Removed duplicated region for block: B:731:0x10c1  */
    /* JADX WARN: Removed duplicated region for block: B:734:0x10ca  */
    /* JADX WARN: Removed duplicated region for block: B:735:0x10cd  */
    /* JADX WARN: Removed duplicated region for block: B:738:0x10d9  */
    /* JADX WARN: Removed duplicated region for block: B:742:0x10ea  */
    /* JADX WARN: Removed duplicated region for block: B:747:0x1149  */
    /* JADX WARN: Removed duplicated region for block: B:750:0x1160  */
    /* JADX WARN: Removed duplicated region for block: B:753:0x1178  */
    /* JADX WARN: Removed duplicated region for block: B:756:0x1181  */
    /* JADX WARN: Removed duplicated region for block: B:760:0x119a  */
    /* JADX WARN: Removed duplicated region for block: B:762:0x11a0  */
    /* JADX WARN: Removed duplicated region for block: B:767:0x11bd  */
    /* JADX WARN: Removed duplicated region for block: B:778:0x11f3  */
    /* JADX WARN: Removed duplicated region for block: B:781:0x1210  */
    /* JADX WARN: Removed duplicated region for block: B:782:0x1213  */
    /* JADX WARN: Removed duplicated region for block: B:785:0x1221  */
    /* JADX WARN: Removed duplicated region for block: B:786:0x1233  */
    /* JADX WARN: Removed duplicated region for block: B:789:0x12cb  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:835:0x13ff  */
    /* JADX WARN: Removed duplicated region for block: B:881:0x1625  */
    /* JADX WARN: Removed duplicated region for block: B:884:0x1642  */
    /* JADX WARN: Removed duplicated region for block: B:885:0x164e  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:955:0x183b  */
    /* JADX WARN: Removed duplicated region for block: B:956:0x1845  */
    /* JADX WARN: Removed duplicated region for block: B:975:0x18b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void HomeScreen(com.arflix.tv.ui.screens.home.HomeViewModel r81, java.util.List<com.arflix.tv.data.model.Category> r82, com.arflix.tv.data.model.MediaItem r83, java.lang.String r84, java.util.Map<java.lang.String, java.lang.String> r85, com.arflix.tv.data.model.Profile r86, r7.r<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, ? super java.lang.Integer, ? super java.lang.Integer, x6.t0> r87, r7.l<? super java.lang.String, x6.t0> r88, r7.a<x6.t0> r89, r7.a<x6.t0> r90, r7.p<? super java.lang.String, ? super java.lang.String, x6.t0> r91, r7.s<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, x6.t0> r92, r7.a<x6.t0> r93, r7.a<x6.t0> r94, r7.a<x6.t0> r95, androidx.compose.runtime.Composer r96, final int r97, final int r98, final int r99) {
        /*
            Method dump skipped, instruction units count: 6574
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt.HomeScreen(com.arflix.tv.ui.screens.home.HomeViewModel, java.util.List, com.arflix.tv.data.model.MediaItem, java.lang.String, java.util.Map, com.arflix.tv.data.model.Profile, r7.r, r7.l, r7.a, r7.a, r7.p, r7.s, r7.a, r7.a, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$0$0(MediaType mediaType, int i10, Integer num, Integer num2) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$1$0(String str) {
        return t0.f22605a;
    }

    private static final HomeUiState HomeScreen$lambda$10(State<HomeUiState> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult HomeScreen$lambda$15$0(final androidx.lifecycle.y yVar, final HomeViewModel homeViewModel, final MutableLongState mutableLongState, DisposableEffectScope disposableEffectScope) {
        final androidx.lifecycle.w wVar = new androidx.lifecycle.w() { // from class: com.arflix.tv.ui.screens.home.t
            @Override // androidx.lifecycle.w
            public final void onStateChanged(androidx.lifecycle.y yVar2, androidx.lifecycle.p pVar) {
                HomeScreenKt.HomeScreen$lambda$15$0$0(homeViewModel, mutableLongState, yVar2, pVar);
            }
        };
        yVar.getLifecycle().addObserver(wVar);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$lambda$15$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                yVar.getLifecycle().removeObserver(wVar);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HomeScreen$lambda$15$0$0(HomeViewModel homeViewModel, MutableLongState mutableLongState, androidx.lifecycle.y yVar, androidx.lifecycle.p pVar) {
        if (pVar == androidx.lifecycle.p.ON_RESUME) {
            homeViewModel.refreshContinueWatchingOnly(false);
            homeViewModel.pullCloudStateOnResume();
            mutableLongState.setLongValue(SystemClock.elapsedRealtime() + 150);
        }
    }

    private static final List<Category> HomeScreen$lambda$16(State<? extends List<Category>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Category> HomeScreen$lambda$19(State<? extends List<Category>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MediaItem HomeScreen$lambda$20(State<MediaItem> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$21$0(HomeViewModel homeViewModel, r7.a aVar, r7.s sVar, MediaItem mediaItem) {
        homeViewModel.openSportsHomeItem(mediaItem, aVar, sVar);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HomeFocusState HomeScreen$lambda$24$0() {
        return new HomeFocusState(0, 0, 0, 7, null);
    }

    private static final boolean HomeScreen$lambda$26(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HomeScreen$lambda$27(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean HomeScreen$lambda$29(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HomeScreen$lambda$30(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean HomeScreen$lambda$34(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void HomeScreen$lambda$35(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MediaItem HomeScreen$lambda$37(MutableState<MediaItem> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$4$0(String str, String str2) {
        return t0.f22605a;
    }

    private static final boolean HomeScreen$lambda$40(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void HomeScreen$lambda$41(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean HomeScreen$lambda$43(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HomeScreen$lambda$44(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$45$0(MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
        HomeScreen$lambda$35(mutableState, false);
        mutableState2.setValue(null);
        HomeScreen$lambda$41(mutableState3, false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$5$0(MediaType mediaType, int i10, String str, String str2, String str3) {
        return t0.f22605a;
    }

    private static final Integer HomeScreen$lambda$51(MutableState<Integer> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean HomeScreen$lambda$57(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void HomeScreen$lambda$58(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean HomeScreen$lambda$60(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HomeScreen$lambda$61(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HomeHeroPlaybackHandles HomeScreen$lambda$66(MutableState<HomeHeroPlaybackHandles> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String HomeScreen$lambda$69(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult HomeScreen$lambda$71$0(final MutableState mutableState, final MutableState mutableState2, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$lambda$71$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                ExoPlayer player;
                HomeHeroPlaybackHandles homeHeroPlaybackHandlesHomeScreen$lambda$66 = HomeScreenKt.HomeScreen$lambda$66(mutableState);
                if (homeHeroPlaybackHandlesHomeScreen$lambda$66 != null && (player = homeHeroPlaybackHandlesHomeScreen$lambda$66.getPlayer()) != null) {
                    player.release();
                }
                mutableState.setValue(null);
                mutableState2.setValue(null);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer HomeScreen$lambda$73(State<Integer> state) {
        return state.getValue();
    }

    private static final float HomeScreen$lambda$74(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [androidx.media3.common.Player$Listener, com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$23$1$listener$1] */
    public static final DisposableEffectResult HomeScreen$lambda$75$0(final ExoPlayer exoPlayer, final State state, final MutableState mutableState, DisposableEffectScope disposableEffectScope) {
        if (exoPlayer == 0) {
            return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$lambda$75$0$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                }
            };
        }
        final ?? r32 = new Player.Listener() { // from class: com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$23$1$listener$1
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
            public final /* synthetic */ void onMediaItemTransition(androidx.media3.common.MediaItem mediaItem, int i10) {
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
            public void onPlaybackStateChanged(int playbackState) {
                Integer numHomeScreen$lambda$73;
                if (playbackState != 4 || (numHomeScreen$lambda$73 = HomeScreenKt.HomeScreen$lambda$73(state)) == null) {
                    return;
                }
                mutableState.setValue(Integer.valueOf(numHomeScreen$lambda$73.intValue()));
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlaybackSuppressionReasonChanged(int i10) {
                androidx.media3.common.j.s(this, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlayerError(PlaybackException playbackException) {
                androidx.media3.common.j.t(this, playbackException);
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
            public final /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
                androidx.media3.common.j.J(this, videoSize);
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
        exoPlayer.addListener(r32);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$lambda$75$0$$inlined$onDispose$2
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                exoPlayer.removeListener(r32);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$10$0$0(r7.p pVar, String str, String str2) {
        pVar.invoke(str, str2);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$11$0$0$0(HomeViewModel homeViewModel) {
        homeViewModel.refresh();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$12$0$0$0(HomeViewModel homeViewModel, MediaItem mediaItem, r7.l lVar, r7.p pVar, r7.r rVar) {
        if (homeViewModel.isSportsHomeItem(mediaItem)) {
            lVar.invoke(mediaItem);
        } else if (homeViewModel.isIptvItem(mediaItem)) {
            pVar.invoke(homeViewModel.getIptvChannelId(mediaItem), homeViewModel.getIptvStreamUrl(mediaItem.getId()));
        } else {
            MediaType mediaType = mediaItem.getMediaType();
            Integer numValueOf = Integer.valueOf(mediaItem.getId());
            NextEpisode nextEpisode = mediaItem.getNextEpisode();
            Integer numValueOf2 = nextEpisode != null ? Integer.valueOf(nextEpisode.getSeasonNumber()) : null;
            NextEpisode nextEpisode2 = mediaItem.getNextEpisode();
            rVar.invoke(mediaType, numValueOf, numValueOf2, nextEpisode2 != null ? Integer.valueOf(nextEpisode2.getEpisodeNumber()) : null);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$12$0$1$0(HomeViewModel homeViewModel, MediaItem mediaItem, r7.l lVar, r7.p pVar, r7.r rVar) {
        if (homeViewModel.isSportsHomeItem(mediaItem)) {
            lVar.invoke(mediaItem);
        } else if (homeViewModel.isIptvItem(mediaItem)) {
            pVar.invoke(homeViewModel.getIptvChannelId(mediaItem), homeViewModel.getIptvStreamUrl(mediaItem.getId()));
        } else {
            MediaType mediaType = mediaItem.getMediaType();
            Integer numValueOf = Integer.valueOf(mediaItem.getId());
            NextEpisode nextEpisode = mediaItem.getNextEpisode();
            Integer numValueOf2 = nextEpisode != null ? Integer.valueOf(nextEpisode.getSeasonNumber()) : null;
            NextEpisode nextEpisode2 = mediaItem.getNextEpisode();
            rVar.invoke(mediaType, numValueOf, numValueOf2, nextEpisode2 != null ? Integer.valueOf(nextEpisode2.getEpisodeNumber()) : null);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$12$0$2$0(HomeViewModel homeViewModel, MediaItem mediaItem) {
        homeViewModel.toggleWatchlist(mediaItem);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$12$0$3$0(HomeViewModel homeViewModel, MediaItem mediaItem) {
        homeViewModel.toggleWatched(mediaItem);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$12$0$4$0(HomeViewModel homeViewModel, MediaItem mediaItem) {
        homeViewModel.removeFromContinueWatching(mediaItem);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$12$0$5$0(MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
        HomeScreen$lambda$35(mutableState, false);
        mutableState2.setValue(null);
        HomeScreen$lambda$41(mutableState3, false);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$13$0$0(HomeViewModel homeViewModel) {
        homeViewModel.dismissToast();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$14$0(HomeViewModel homeViewModel) {
        homeViewModel.downloadAppUpdate();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$15$0(HomeViewModel homeViewModel) {
        homeViewModel.cancelDownloadAppUpdate();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$16$0(HomeViewModel homeViewModel) {
        homeViewModel.installAppUpdateOrRequestPermission();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$17$0(HomeViewModel homeViewModel) {
        homeViewModel.dismissAppUpdateDialog();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$18$0(HomeViewModel homeViewModel) {
        homeViewModel.ignoreAppUpdate();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String HomeScreen$lambda$77$2(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String HomeScreen$lambda$77$4(State<String> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$6$0$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(HomeScreen$lambda$74(state));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlayerView HomeScreen$lambda$77$6$1$0(ExoPlayer exoPlayer, Context context) {
        PlayerView playerView = new PlayerView(context);
        playerView.setUseController(false);
        playerView.setControllerAutoShow(false);
        playerView.hideController();
        playerView.setFocusable(false);
        playerView.setFocusableInTouchMode(false);
        playerView.setDescendantFocusability(393216);
        playerView.setResizeMode(4);
        playerView.setShutterBackgroundColor(0);
        playerView.setKeepContentOnPlayerReset(true);
        playerView.setPlayer(exoPlayer);
        return playerView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$6$2$0(ExoPlayer exoPlayer, PlayerView playerView) {
        playerView.setUseController(false);
        playerView.setControllerAutoShow(false);
        playerView.hideController();
        playerView.setPlayer(exoPlayer);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$6$3$0(MutableState mutableState, boolean z) {
        HomeScreen$lambda$58(mutableState, z);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DrawResult HomeScreen$lambda$77$6$4$0(CacheDrawScope cacheDrawScope) {
        final float fM3305getWidthimpl = Size.m3305getWidthimpl(cacheDrawScope.m3140getSizeNHjbRc());
        final float fM3302getHeightimpl = Size.m3302getHeightimpl(cacheDrawScope.m3140getSizeNHjbRc());
        Brush.Companion companion = Brush.INSTANCE;
        Float fValueOf = Float.valueOf(0.0f);
        Color.Companion companion2 = Color.INSTANCE;
        x6.x xVar = new x6.x(fValueOf, Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.95f, 0.0f, 0.0f, 0.0f, 14, null)));
        x6.x xVar2 = new x6.x(Float.valueOf(0.12f), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.88f, 0.0f, 0.0f, 0.0f, 14, null)));
        x6.x xVar3 = new x6.x(Float.valueOf(0.22f), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.72f, 0.0f, 0.0f, 0.0f, 14, null)));
        x6.x xVar4 = new x6.x(Float.valueOf(0.32f), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null)));
        x6.x xVar5 = new x6.x(Float.valueOf(0.42f), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null)));
        x6.x xVar6 = new x6.x(Float.valueOf(0.55f), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null)));
        x6.x xVar7 = new x6.x(Float.valueOf(0.65f), Color.m3462boximpl(companion2.m3507getTransparent0d7_KjU()));
        Float fValueOf2 = Float.valueOf(1.0f);
        final Brush brushM3428horizontalGradient8A3gB4$default = Brush.Companion.m3428horizontalGradient8A3gB4$default(companion, new x6.x[]{xVar, xVar2, xVar3, xVar4, xVar5, xVar6, xVar7, new x6.x(fValueOf2, Color.m3462boximpl(companion2.m3507getTransparent0d7_KjU()))}, 0.0f, fM3305getWidthimpl, 0, 8, (Object) null);
        final Brush brushM3436verticalGradient8A3gB4$default = Brush.Companion.m3436verticalGradient8A3gB4$default(companion, new x6.x[]{new x6.x(fValueOf, Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null))), new x6.x(Float.valueOf(0.06f), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.45f, 0.0f, 0.0f, 0.0f, 14, null))), new x6.x(Float.valueOf(0.15f), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.15f, 0.0f, 0.0f, 0.0f, 14, null))), new x6.x(Float.valueOf(0.25f), Color.m3462boximpl(companion2.m3507getTransparent0d7_KjU())), new x6.x(fValueOf2, Color.m3462boximpl(companion2.m3507getTransparent0d7_KjU()))}, 0.0f, fM3302getHeightimpl, 0, 8, (Object) null);
        final Brush brushM3436verticalGradient8A3gB4$default2 = Brush.Companion.m3436verticalGradient8A3gB4$default(companion, new x6.x[]{new x6.x(fValueOf, Color.m3462boximpl(companion2.m3507getTransparent0d7_KjU())), new x6.x(Float.valueOf(0.85f), Color.m3462boximpl(companion2.m3507getTransparent0d7_KjU())), new x6.x(Float.valueOf(0.92f), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null))), new x6.x(fValueOf2, Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.85f, 0.0f, 0.0f, 0.0f, 14, null)))}, 0.0f, fM3302getHeightimpl, 0, 8, (Object) null);
        return cacheDrawScope.onDrawBehind(new r7.l() { // from class: com.arflix.tv.ui.screens.home.e
            @Override // r7.l
            public final Object invoke(Object obj) {
                return HomeScreenKt.HomeScreen$lambda$77$6$4$0$0(brushM3428horizontalGradient8A3gB4$default, fM3305getWidthimpl, fM3302getHeightimpl, brushM3436verticalGradient8A3gB4$default, brushM3436verticalGradient8A3gB4$default2, (DrawScope) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$6$4$0$0(Brush brush, float f10, float f11, Brush brush2, Brush brush3, DrawScope drawScope) {
        androidx.compose.ui.graphics.drawscope.c.J(drawScope, brush, 0L, SizeKt.Size(0.66f * f10, f11), 0.0f, null, null, 0, 122, null);
        androidx.compose.ui.graphics.drawscope.c.J(drawScope, brush2, 0L, SizeKt.Size(f10, 0.26f * f11), 0.0f, null, null, 0, 122, null);
        androidx.compose.ui.graphics.drawscope.c.J(drawScope, brush3, OffsetKt.Offset(0.0f, 0.84f * f11), SizeKt.Size(f10, f11 * 0.16f), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$7$0(Animatable animatable, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(((Number) animatable.getValue()).floatValue());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$8$0$0(MutableState mutableState) {
        HomeScreen$lambda$61(mutableState, true);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$8$1$0(MediaItem mediaItem, HomeViewModel homeViewModel, r7.l lVar, r7.p pVar, r7.l lVar2, r7.r rVar) {
        if (mediaItem != null) {
            if (homeViewModel.isSportsHomeItem(mediaItem)) {
                lVar.invoke(mediaItem);
            } else if (homeViewModel.isIptvItem(mediaItem)) {
                pVar.invoke(homeViewModel.getIptvChannelId(mediaItem), homeViewModel.getIptvStreamUrl(mediaItem.getId()));
            } else {
                if (homeViewModel.isCollectionItem(mediaItem)) {
                    String status = mediaItem.getStatus();
                    Object objR0 = status != null ? kotlin.text.o.r0(status, "collection:") : null;
                    if (objR0 == null) {
                        objR0 = "";
                    }
                    lVar2.invoke(objR0);
                } else {
                    MediaType mediaType = mediaItem.getMediaType();
                    Integer numValueOf = Integer.valueOf(mediaItem.getId());
                    NextEpisode nextEpisode = mediaItem.getNextEpisode();
                    Integer numValueOf2 = nextEpisode != null ? Integer.valueOf(nextEpisode.getSeasonNumber()) : null;
                    NextEpisode nextEpisode2 = mediaItem.getNextEpisode();
                    rVar.invoke(mediaType, numValueOf, numValueOf2, nextEpisode2 != null ? Integer.valueOf(nextEpisode2.getEpisodeNumber()) : null);
                }
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$8$2$0(MediaItem mediaItem, HomeViewModel homeViewModel, r7.l lVar, r7.p pVar, r7.l lVar2, r7.r rVar) {
        if (mediaItem != null) {
            if (homeViewModel.isSportsHomeItem(mediaItem)) {
                lVar.invoke(mediaItem);
            } else if (homeViewModel.isIptvItem(mediaItem)) {
                pVar.invoke(homeViewModel.getIptvChannelId(mediaItem), homeViewModel.getIptvStreamUrl(mediaItem.getId()));
            } else {
                if (homeViewModel.isCollectionItem(mediaItem)) {
                    String status = mediaItem.getStatus();
                    String strR0 = status != null ? kotlin.text.o.r0(status, "collection:") : null;
                    if (strR0 == null) {
                        strR0 = "";
                    }
                    lVar2.invoke(strR0);
                } else {
                    rVar.invoke(mediaItem.getMediaType(), Integer.valueOf(mediaItem.getId()), null, null);
                }
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$8$3$0(HomeViewModel homeViewModel, String str) {
        homeViewModel.loadNextPageForCategory(str);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$8$4$0(MediaItem mediaItem) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$8$5$0(HomeViewModel homeViewModel, String str, int i10) {
        homeViewModel.onMobileCategoryVisiblePosition(str, i10);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$8$8$0(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MediaItem mediaItem, boolean z) {
        mutableState.setValue(mediaItem);
        HomeScreen$lambda$41(mutableState2, z);
        HomeScreen$lambda$35(mutableState3, true);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$77$9$0(Animatable animatable, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(((Number) animatable.getValue()).floatValue());
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 HomeScreen$lambda$78(HomeViewModel homeViewModel, List list, MediaItem mediaItem, String str, Map map, Profile profile, r7.r rVar, r7.l lVar, r7.a aVar, r7.a aVar2, r7.p pVar, r7.s sVar, r7.a aVar3, r7.a aVar4, r7.a aVar5, int i10, int i11, int i12, Composer composer, int i13) {
        HomeScreen(homeViewModel, list, mediaItem, str, map, profile, rVar, lVar, aVar, aVar2, pVar, sVar, aVar3, aVar4, aVar5, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return t0.f22605a;
    }

    private static final void ImdbBadge(String str, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1306889244);
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1306889244, i11, -1, "com.arflix.tv.ui.screens.home.ImdbBadge (HomeScreen.kt:3377)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            float f10 = 4;
            Modifier modifierH = a2.h(5, com.arflix.tv.data.repository.g.j(f10, companion, ColorKt.Color(4294296856L)), Dp.m5678constructorimpl(8), composerStartRestartGroup, 733328855);
            Alignment.Companion companion2 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(f10, Arrangement.INSTANCE, composerStartRestartGroup, 693286680), companion2.getCenterVertically(), composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
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
            r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyRowMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextStyle caption = arflixTypography.getCaption();
            long sp = TextUnitKt.getSp(10);
            FontWeight.Companion companion4 = FontWeight.INSTANCE;
            FontWeight bold = companion4.getBold();
            Color.Companion companion5 = Color.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g("IMDb", null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(caption, companion5.m3498getBlack0d7_KjU(), sp, bold, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777208, null), composer2, 6, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            TextKt.m6020Text4IGK_g(str, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), companion5.m3498getBlack0d7_KjU(), TextUnitKt.getSp(11), companion4.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777208, null), composer2, i11 & 14, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            if (androidx.compose.foundation.c.A(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.screens.collections.b(str, i10, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ImdbBadge$lambda$1(String str, int i10, Composer composer, int i11) {
        ImdbBadge(str, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    /* JADX INFO: renamed from: ImdbSvgRatingBadge-6PoWaU8, reason: not valid java name */
    private static final void m6243ImdbSvgRatingBadge6PoWaU8(final String str, final ImageLoader imageLoader, int i10, final float f10, final float f11, final Shadow shadow, Composer composer, final int i11) {
        int i12;
        int i13;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(483192638);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(imageLoader) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 = i10;
            i12 |= composerStartRestartGroup.changed(i13) ? 256 : 128;
        } else {
            i13 = i10;
        }
        if ((i11 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changed(f10) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i12 |= composerStartRestartGroup.changed(f11) ? 16384 : 8192;
        }
        if ((196608 & i11) == 0) {
            i12 |= composerStartRestartGroup.changed(shadow) ? 131072 : 65536;
        }
        if ((74899 & i12) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(483192638, i12, -1, "com.arflix.tv.ui.screens.home.ImdbSvgRatingBadge (HomeScreen.kt:3340)");
            }
            Context context = (Context) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            boolean zChanged = composerStartRestartGroup.changed(context);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new ImageRequest.Builder(context).data(Integer.valueOf(R.raw.logo_imdb_rectangle)).bitmapConfig(Bitmap.Config.ARGB_8888).allowRgb565(false).build();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            ImageRequest imageRequest = (ImageRequest) objRememberedValue;
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalG = androidx.compose.foundation.c.g(3, Arrangement.INSTANCE, composerStartRestartGroup, 693286680);
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalG, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            AsyncImageKt.m6026AsyncImageMvsnxeU(imageRequest, "IMDb", imageLoader, androidx.compose.foundation.layout.SizeKt.m564height3ABfNKs(androidx.compose.foundation.layout.SizeKt.m583width3ABfNKs(companion, f10), f11), null, null, null, ContentScale.INSTANCE.getFit(), 0.0f, null, 0, composerStartRestartGroup, ((i12 << 3) & 896) | 12582960, 0, 1904);
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, null, Color.INSTANCE.m3509getWhite0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(i13), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, shadow, null, 0, 0, 0L, null, null, null, 0, 0, null, 16769017, null), composer2, (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK, 3072, 57338);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final int i14 = i13;
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.home.f0
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return HomeScreenKt.ImdbSvgRatingBadge_6PoWaU8$lambda$2(str, imageLoader, i14, f10, f11, shadow, i11, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 ImdbSvgRatingBadge_6PoWaU8$lambda$2(String str, ImageLoader imageLoader, int i10, float f10, float f11, Shadow shadow, int i11, Composer composer, int i12) {
        m6243ImdbSvgRatingBadge6PoWaU8(str, imageLoader, i10, f10, f11, shadow, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return t0.f22605a;
    }

    private static final void IncludedWithPrimeBadge(Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-464929422);
        if (i10 == 0 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-464929422, i10, -1, "com.arflix.tv.ui.screens.home.IncludedWithPrimeBadge (HomeScreen.kt:3285)");
            }
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalG = androidx.compose.foundation.c.g(6, Arrangement.INSTANCE, composerStartRestartGroup, 693286680);
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalG, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            IconKt.m1610Iconww6aTOc(CheckKt.getCheck(Icons.Filled.INSTANCE), (String) null, BackgroundKt.m179backgroundbw27NRU$default(androidx.compose.foundation.layout.SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(16)), Color.INSTANCE.m3507getTransparent0d7_KjU(), null, 2, null), com.arflix.tv.ui.theme.ColorKt.getPrimeBlue(), composerStartRestartGroup, 3504, 0);
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.included_with_prime, composerStartRestartGroup, 0), null, com.arflix.tv.ui.theme.ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(13), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            composerStartRestartGroup = composerStartRestartGroup;
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.j(i10, 3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 IncludedWithPrimeBadge$lambda$1(int i10, Composer composer, int i11) {
        IncludedWithPrimeBadge(composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    private static final void MetaPill(String str, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1876298411);
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1876298411, i11, -1, "com.arflix.tv.ui.screens.home.MetaPill (HomeScreen.kt:3311)");
            }
            float f10 = 2;
            Modifier modifierH = a2.h(f10, com.arflix.tv.data.repository.g.j(f10, Modifier.INSTANCE, Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.2f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(6), composerStartRestartGroup, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, null, com.arflix.tv.ui.theme.ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(11), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer2, (i11 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.ui.screens.collections.b(str, i10, 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MetaPill$lambda$1(String str, int i10, Composer composer, int i11) {
        MetaPill(str, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:192:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x03ce  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x03f8  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x04e3  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0527  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0529  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0530  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0538  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x05bd  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0736  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0745  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0756  */
    /* JADX WARN: Removed duplicated region for block: B:319:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0128  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void MobileHeroCarousel(final java.util.List<com.arflix.tv.data.model.Category> r46, java.util.Map<java.lang.String, java.lang.String> r47, com.arflix.tv.data.model.Profile r48, r7.a<x6.t0> r49, r7.a<x6.t0> r50, final r7.r<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, ? super java.lang.Integer, ? super java.lang.Integer, x6.t0> r51, androidx.compose.runtime.Composer r52, final int r53, final int r54) {
        /*
            Method dump skipped, instruction units count: 1896
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt.MobileHeroCarousel(java.util.List, java.util.Map, com.arflix.tv.data.model.Profile, r7.a, r7.a, r7.r, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileHeroCarousel$lambda$3(List list, Map map, Profile profile, r7.a aVar, r7.a aVar2, r7.r rVar, int i10, int i11, Composer composer, int i12) {
        MobileHeroCarousel(list, map, profile, aVar, aVar2, rVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int MobileHeroCarousel$lambda$4$0(int i10) {
        return i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileHeroCarousel$lambda$6$0$0$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileHeroCarousel$lambda$6$0$2$0(r7.a aVar) {
        aVar.invoke();
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileHeroCarousel$lambda$6$1(List list, r7.r rVar, Map map, PagerState pagerState, PagerScope pagerScope, int i10, Composer composer, int i11) {
        Object year;
        Object c0Var;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-173889176, i11, -1, "com.arflix.tv.ui.screens.home.MobileHeroCarousel.<anonymous>.<anonymous> (HomeScreen.kt:2141)");
        }
        MediaItem mediaItem = (MediaItem) list.get(i10 % list.size());
        boolean zChanged = composer.changed(mediaItem.getId()) | composer.changed(mediaItem.getGenreIds());
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            Map<Integer, String> map2 = mediaItem.getMediaType() == MediaType.TV ? tvGenres : movieGenres;
            List<Integer> genreIds = mediaItem.getGenreIds();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = genreIds.iterator();
            while (it.hasNext()) {
                String str = map2.get(Integer.valueOf(((Number) it.next()).intValue()));
                if (str != null) {
                    arrayList.add(str);
                }
            }
            objRememberedValue = kotlin.collections.x.X0(arrayList, 3);
            composer.updateRememberedValue(objRememberedValue);
        }
        List list2 = (List) objRememberedValue;
        boolean zChanged2 = composer.changed(mediaItem.getId()) | composer.changed(mediaItem.getReleaseDate()) | composer.changed(mediaItem.getYear());
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            String releaseDate = mediaItem.getReleaseDate();
            if (releaseDate == null || kotlin.text.o.h0(releaseDate)) {
                year = mediaItem.getYear();
            } else {
                try {
                    Locale locale = Locale.ENGLISH;
                    Date date = new SimpleDateFormat("d MMM yyyy", locale).parse(releaseDate);
                    c0Var = date != null ? new SimpleDateFormat("d MMM", locale).format(date) : null;
                } catch (Throwable th) {
                    c0Var = new x6.c0(th);
                }
                objRememberedValue2 = (String) (c0Var instanceof x6.c0 ? null : c0Var);
                if (objRememberedValue2 == null) {
                    year = mediaItem.getYear();
                }
                composer.updateRememberedValue(objRememberedValue2);
            }
            objRememberedValue2 = year;
            composer.updateRememberedValue(objRememberedValue2);
        }
        String str2 = (String) objRememberedValue2;
        boolean zChanged3 = composer.changed(mediaItem.getId()) | composer.changed(mediaItem.getImdbRating());
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = imdbRatingFor(mediaItem);
            composer.updateRememberedValue(objRememberedValue3);
        }
        String str3 = (String) objRememberedValue3;
        boolean zChanged4 = composer.changed(mediaItem.getId());
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = (String) map.get(mediaItem.getMediaType() + "_" + mediaItem.getId());
            composer.updateRememberedValue(objRememberedValue4);
        }
        String str4 = (String) objRememberedValue4;
        boolean z = (((i11 & 112) ^ 48) > 32 && composer.changed(i10)) || (i11 & 48) == 32;
        Object objRememberedValue5 = composer.rememberedValue();
        if (z || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue5 = SnapshotStateKt.derivedStateOf(new com.arflix.tv.network.b(pagerState, i10, 1));
            composer.updateRememberedValue(objRememberedValue5);
        }
        State state = (State) objRememberedValue5;
        String backdrop = mediaItem.getBackdrop();
        if (backdrop == null && (backdrop = mediaItem.getImage()) == null) {
            backdrop = "";
        }
        String title = mediaItem.getTitle();
        boolean zChanged5 = composer.changed(rVar) | composer.changed(mediaItem);
        Object objRememberedValue6 = composer.rememberedValue();
        if (zChanged5 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue6 = new p0(rVar, mediaItem, 3);
            composer.updateRememberedValue(objRememberedValue6);
        }
        r7.a aVar = (r7.a) objRememberedValue6;
        Modifier.Companion companion = Modifier.INSTANCE;
        boolean zChanged6 = composer.changed(state);
        Object objRememberedValue7 = composer.rememberedValue();
        if (zChanged6 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue7 = new j(state, 0);
            composer.updateRememberedValue(objRememberedValue7);
        }
        MobileHeroBannerKt.MobileHeroBanner(backdrop, title, list2, str2, str3, str4, aVar, GraphicsLayerModifierKt.graphicsLayer(companion, (r7.l) objRememberedValue7), composer, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float MobileHeroCarousel$lambda$6$1$4$0(PagerState pagerState, int i10) {
        return qb.d.m(1.0f - (Math.abs(pagerState.getCurrentPageOffsetFraction() + (pagerState.getCurrentPage() - i10)) * 0.13f), 0.87f, 1.0f);
    }

    private static final float MobileHeroCarousel$lambda$6$1$5(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileHeroCarousel$lambda$6$1$6$0(r7.r rVar, MediaItem mediaItem) {
        rVar.invoke(mediaItem.getMediaType(), Integer.valueOf(mediaItem.getId()), null, null);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileHeroCarousel$lambda$6$1$7$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(MobileHeroCarousel$lambda$6$1$5(state));
        graphicsLayerScope.setScaleY(MobileHeroCarousel$lambda$6$1$5(state));
        return t0.f22605a;
    }

    private static final float MobileHeroCarousel$lambda$6$2$0$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileHeroCarousel$lambda$7(List list, Map map, Profile profile, r7.a aVar, r7.a aVar2, r7.r rVar, int i10, int i11, Composer composer, int i12) {
        MobileHeroCarousel(list, map, profile, aVar, aVar2, rVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:119:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x074d  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x083e  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x084a  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x084e  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0872  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x08be  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x08ca  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x08ce  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x08f2  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0922  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x092e  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0932  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0956  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0a69  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0a75  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0a79  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0a9d  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0ad0  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0adc  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0ae0  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0b04  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0bd9  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01cc  */
    /* JADX INFO: renamed from: MobileHeroOverlay-942rkJo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6244MobileHeroOverlay942rkJo(final com.arflix.tv.data.model.MediaItem r80, final java.lang.String r81, final float r82, final r7.a<x6.t0> r83, final r7.a<x6.t0> r84, androidx.compose.runtime.Composer r85, final int r86) {
        /*
            Method dump skipped, instruction units count: 3063
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt.m6244MobileHeroOverlay942rkJo(com.arflix.tv.data.model.MediaItem, java.lang.String, float, r7.a, r7.a, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileHeroOverlay_942rkJo$lambda$5(MediaItem mediaItem, String str, float f10, r7.a aVar, r7.a aVar2, int i10, Composer composer, int i11) {
        m6244MobileHeroOverlay942rkJo(mediaItem, str, f10, aVar, aVar2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0287  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:228:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0122  */
    /* JADX INFO: renamed from: MobileHomeRowsLayer-uS7v9H0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6245MobileHomeRowsLayeruS7v9H0(final java.util.List<com.arflix.tv.data.model.Category> r38, final java.util.Map<java.lang.String, java.lang.String> r39, final float r40, final boolean r41, com.arflix.tv.data.model.Profile r42, r7.a<x6.t0> r43, r7.a<x6.t0> r44, java.util.Map<java.lang.String, java.lang.Boolean> r45, r7.l<? super java.lang.String, x6.t0> r46, r7.r<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, ? super java.lang.Integer, ? super java.lang.Integer, x6.t0> r47, final r7.l<? super com.arflix.tv.data.model.MediaItem, x6.t0> r48, r7.p<? super com.arflix.tv.data.model.MediaItem, ? super java.lang.Boolean, x6.t0> r49, r7.p<? super java.lang.String, ? super java.lang.Integer, x6.t0> r50, androidx.compose.runtime.Composer r51, final int r52, final int r53, final int r54) {
        /*
            Method dump skipped, instruction units count: 901
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt.m6245MobileHomeRowsLayeruS7v9H0(java.util.List, java.util.Map, float, boolean, com.arflix.tv.data.model.Profile, r7.a, r7.a, java.util.Map, r7.l, r7.r, r7.l, r7.p, r7.p, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileHomeRowsLayer_uS7v9H0$lambda$2$0(String str) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileHomeRowsLayer_uS7v9H0$lambda$3$0(MediaType mediaType, int i10, Integer num, Integer num2) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileHomeRowsLayer_uS7v9H0$lambda$4$0(String str, int i10) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileHomeRowsLayer_uS7v9H0$lambda$5$0(List list, Map map, Profile profile, r7.a aVar, r7.a aVar2, r7.r rVar, r7.p pVar, float f10, Map map2, float f11, r7.l lVar, r7.l lVar2, r7.p pVar2, LazyListScope lazyListScope) {
        lazyListScope.item("mobile_hero", "mobile_hero", ComposableLambdaKt.composableLambdaInstance(-1294163868, true, new u(list, map, profile, aVar, aVar2, rVar)));
        lazyListScope.items(list.size(), new HomeScreenKt$MobileHomeRowsLayer_uS7v9H0$lambda$5$0$$inlined$itemsIndexed$1(new c(4), list), new HomeScreenKt$MobileHomeRowsLayer_uS7v9H0$lambda$5$0$$inlined$itemsIndexed$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new HomeScreenKt$MobileHomeRowsLayer_uS7v9H0$lambda$5$0$$inlined$itemsIndexed$3(list, pVar, f10, map2, f11, lVar, lVar2, pVar2, map)));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileHomeRowsLayer_uS7v9H0$lambda$5$0$0(List list, Map map, Profile profile, r7.a aVar, r7.a aVar2, r7.r rVar, LazyItemScope lazyItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1294163868, i10, -1, "com.arflix.tv.ui.screens.home.MobileHomeRowsLayer.<anonymous>.<anonymous>.<anonymous> (HomeScreen.kt:2811)");
            }
            MobileHeroCarousel(list, map, profile, aVar, aVar2, rVar, composer, 0, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object MobileHomeRowsLayer_uS7v9H0$lambda$5$0$1(int i10, Category category) {
        return stableHomeRowKey("mobile", category.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 MobileHomeRowsLayer_uS7v9H0$lambda$6(List list, Map map, float f10, boolean z, Profile profile, r7.a aVar, r7.a aVar2, Map map2, r7.l lVar, r7.r rVar, r7.l lVar2, r7.p pVar, r7.p pVar2, int i10, int i11, int i12, Composer composer, int i13) {
        m6245MobileHomeRowsLayeruS7v9H0(list, map, f10, z, profile, aVar, aVar2, map2, lVar, rVar, lVar2, pVar, pVar2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return t0.f22605a;
    }

    private static final void PrimeLogo(Modifier modifier, Composer composer, int i10, int i11) {
        Modifier modifier2;
        int i12;
        Composer composer2;
        int i13;
        Composer composerStartRestartGroup = composer.startRestartGroup(-775860063);
        int i14 = i11 & 1;
        if (i14 != 0) {
            i12 = i10 | 6;
            modifier2 = modifier;
        } else if ((i10 & 6) == 0) {
            modifier2 = modifier;
            i12 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i10;
        } else {
            modifier2 = modifier;
            i12 = i10;
        }
        if ((i12 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
            i13 = 4;
        } else {
            Modifier modifier3 = i14 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-775860063, i12, -1, "com.arflix.tv.ui.screens.home.PrimeLogo (HomeScreen.kt:3267)");
            }
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(Arrangement.INSTANCE, centerVertically, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier3);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyG, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            composer2 = composerStartRestartGroup;
            i13 = 4;
            TextKt.m6020Text4IGK_g("prime", null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, new TextStyle(com.arflix.tv.ui.theme.ColorKt.getPrimeBlue(), TextUnitKt.getSp(20), FontWeight.INSTANCE.getBlack(), (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, TextUnitKt.getSp(-0.5d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777080, (kotlin.jvm.internal.h) null), composer2, 6, 0, WavUtil.TYPE_WAVE_FORMAT_EXTENSIBLE);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new n1(modifier2, i10, i11, i13));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 PrimeLogo$lambda$1(Modifier modifier, int i10, int i11, Composer composer, int i12) {
        PrimeLogo(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:88:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TopRankRibbon(int r19, boolean r20, boolean r21, androidx.compose.ui.Modifier r22, androidx.compose.runtime.Composer r23, int r24, int r25) {
        /*
            Method dump skipped, instruction units count: 392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt.TopRankRibbon(int, boolean, boolean, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TopRankRibbon$lambda$1(int i10, boolean z, boolean z5, Modifier modifier, int i11, int i12, Composer composer, int i13) {
        TopRankRibbon(i10, z, z5, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x029b  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0436  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0445  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x04cd  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x04e2  */
    /* JADX WARN: Removed duplicated region for block: B:293:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0116  */
    /* JADX INFO: renamed from: TvHomeRowsLayer-GVfq81o, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6246TvHomeRowsLayerGVfq81o(final java.util.List<com.arflix.tv.data.model.Category> r30, final java.util.Map<java.lang.String, java.lang.String> r31, final com.arflix.tv.ui.screens.home.HomeFocusState r32, final boolean r33, final float r34, final long r35, final boolean r37, java.util.Map<java.lang.String, java.lang.Boolean> r38, boolean r39, r7.l<? super java.lang.String, x6.t0> r40, r7.l<? super com.arflix.tv.data.model.MediaItem, x6.t0> r41, java.lang.String r42, long r43, float r45, final r7.l<? super com.arflix.tv.data.model.MediaItem, x6.t0> r46, androidx.compose.runtime.Composer r47, final int r48, final int r49, final int r50) {
        /*
            Method dump skipped, instruction units count: 1286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt.m6246TvHomeRowsLayerGVfq81o(java.util.List, java.util.Map, com.arflix.tv.ui.screens.home.HomeFocusState, boolean, float, long, boolean, java.util.Map, boolean, r7.l, r7.l, java.lang.String, long, float, r7.l, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvHomeRowsLayer_GVfq81o$lambda$0$0(String str) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvHomeRowsLayer_GVfq81o$lambda$1$0(MediaItem mediaItem) {
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean TvHomeRowsLayer_GVfq81o$lambda$12(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TvHomeRowsLayer_GVfq81o$lambda$13(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvHomeRowsLayer_GVfq81o$lambda$15(int i10, final List list, HomeFocusState homeFocusState, long j10, boolean z, List list2, Density density, final int i11, final r7.l lVar, final Map map, final float f10, final Map map2, final String str, final long j11, final float f11, final r7.l lVar2, final MutableState mutableState, BoxWithConstraintsScope boxWithConstraintsScope, Composer composer, int i12) {
        int i13;
        Object homeScreenKt$TvHomeRowsLayer$6$1$1;
        Integer num;
        Composer.Companion companion;
        int i14;
        LazyListState lazyListState;
        HomeFocusState homeFocusState2;
        boolean z5;
        if ((i12 & 6) == 0) {
            i13 = i12 | (composer.changed(boxWithConstraintsScope) ? 4 : 2);
        } else {
            i13 = i12;
        }
        if ((i13 & 19) == 18 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-612742847, i13, -1, "com.arflix.tv.ui.screens.home.TvHomeRowsLayer.<anonymous> (HomeScreen.kt:3090)");
            }
            float fM5692unboximpl = ((Dp) qb.d.p(Dp.m5676boximpl(Dp.m5678constructorimpl(boxWithConstraintsScope.mo466getMaxHeightD9Ej5fM() * 0.31f)), Dp.m5676boximpl(Dp.m5678constructorimpl(260)), Dp.m5676boximpl(Dp.m5678constructorimpl(340)))).m5692unboximpl();
            LazyListState lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composer, 0, 3);
            Object objRememberedValue = composer.rememberedValue();
            Composer.Companion companion2 = Composer.INSTANCE;
            if (objRememberedValue == companion2.getEmpty()) {
                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(-1);
                composer.updateRememberedValue(objRememberedValue);
            }
            MutableIntState mutableIntState = (MutableIntState) objRememberedValue;
            int size = list.size() - 1;
            if (size < 0) {
                size = 0;
            }
            int iN = qb.d.n(i10, 0, size);
            Integer numValueOf = Integer.valueOf(iN);
            boolean zChanged = composer.changed(lazyListStateRememberLazyListState) | composer.changed(iN) | composer.changed(homeFocusState) | composer.changed(j10) | composer.changed(z) | composer.changed(list2) | composer.changed(density);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged || objRememberedValue2 == companion2.getEmpty()) {
                num = numValueOf;
                companion = companion2;
                i14 = 1;
                homeScreenKt$TvHomeRowsLayer$6$1$1 = new HomeScreenKt$TvHomeRowsLayer$6$1$1(lazyListStateRememberLazyListState, iN, homeFocusState, j10, z, mutableIntState, list2, density, null);
                lazyListState = lazyListStateRememberLazyListState;
                homeFocusState2 = homeFocusState;
                z5 = z;
                composer.updateRememberedValue(homeScreenKt$TvHomeRowsLayer$6$1$1);
            } else {
                lazyListState = lazyListStateRememberLazyListState;
                homeFocusState2 = homeFocusState;
                z5 = z;
                homeScreenKt$TvHomeRowsLayer$6$1$1 = objRememberedValue2;
                companion = companion2;
                i14 = 1;
                num = numValueOf;
            }
            EffectsKt.LaunchedEffect(num, (r7.p<? super ka.k0, ? super d7.d<? super t0>, ? extends Object>) homeScreenKt$TvHomeRowsLayer$6$1$1, composer, 0);
            Modifier.Companion companion3 = Modifier.INSTANCE;
            Alignment.Companion companion4 = Alignment.INSTANCE;
            Modifier modifierClipToBounds = ClipKt.clipToBounds(ArvioDpadFocusKt.arvioManualBringIntoViewBoundary(androidx.compose.foundation.layout.SizeKt.m564height3ABfNKs(androidx.compose.foundation.layout.SizeKt.fillMaxWidth$default(boxWithConstraintsScope.align(companion3, companion4.getBottomStart()), 0.0f, i14, null), fM5692unboximpl)));
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(companion4, false, composer, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion5.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierClipToBounds);
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
            r7.p pVarU = a0.c.u(companion5, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            PaddingValues paddingValuesM526PaddingValuesa9UjIt4$default = PaddingKt.m526PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, fM5692unboximpl, 7, null);
            Modifier modifierClipToBounds2 = ClipKt.clipToBounds(ArvioDpadFocusKt.arvioDpadFocusGroup$default(androidx.compose.foundation.layout.SizeKt.fillMaxSize$default(companion3, 0.0f, i14, null), null, false, i14, null));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = Arrangement.INSTANCE.m441spacedBy0680j_4(Dp.m5678constructorimpl(0));
            boolean zChanged2 = composer.changed(list) | composer.changed(i11) | composer.changed(homeFocusState2) | composer.changed(lVar) | composer.changed(map) | composer.changed(f10) | composer.changed(map2) | composer.changed(z5) | composer.changed(str) | composer.changed(j11) | composer.changed(f11) | composer.changed(lVar2);
            Object objRememberedValue3 = composer.rememberedValue();
            if (zChanged2 || objRememberedValue3 == companion.getEmpty()) {
                final boolean z10 = z5;
                final HomeFocusState homeFocusState3 = homeFocusState2;
                Object obj = new r7.l() { // from class: com.arflix.tv.ui.screens.home.k
                    @Override // r7.l
                    public final Object invoke(Object obj2) {
                        return HomeScreenKt.TvHomeRowsLayer_GVfq81o$lambda$15$4$0$0(list, i11, homeFocusState3, lVar, map, f10, map2, z10, str, j11, f11, lVar2, mutableState, (LazyListScope) obj2);
                    }
                };
                composer.updateRememberedValue(obj);
                objRememberedValue3 = obj;
            }
            LazyDslKt.LazyColumn(modifierClipToBounds2, lazyListState, paddingValuesM526PaddingValuesa9UjIt4$default, false, horizontalOrVerticalM441spacedBy0680j_4, null, null, false, (r7.l) objRememberedValue3, composer, 24576, 232);
            if (androidx.compose.foundation.c.D(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvHomeRowsLayer_GVfq81o$lambda$15$4$0$0(List list, int i10, HomeFocusState homeFocusState, r7.l lVar, Map map, float f10, Map map2, boolean z, String str, long j10, float f11, r7.l lVar2, MutableState mutableState, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new HomeScreenKt$TvHomeRowsLayer_GVfq81o$lambda$15$4$0$0$$inlined$itemsIndexed$1(new c(6), list), new HomeScreenKt$TvHomeRowsLayer_GVfq81o$lambda$15$4$0$0$$inlined$itemsIndexed$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new HomeScreenKt$TvHomeRowsLayer_GVfq81o$lambda$15$4$0$0$$inlined$itemsIndexed$3(list, i10, homeFocusState, lVar, map, f10, map2, z, str, j10, f11, lVar2, mutableState)));
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object TvHomeRowsLayer_GVfq81o$lambda$15$4$0$0$0(int i10, Category category) {
        return stableHomeRowKey("tv", category.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 TvHomeRowsLayer_GVfq81o$lambda$16(List list, Map map, HomeFocusState homeFocusState, boolean z, float f10, long j10, boolean z5, Map map2, boolean z10, r7.l lVar, r7.l lVar2, String str, long j11, float f11, r7.l lVar3, int i10, int i11, int i12, Composer composer, int i13) {
        m6246TvHomeRowsLayerGVfq81o(list, map, homeFocusState, z, f10, j10, z5, map2, z10, lVar, lVar2, str, j11, f11, lVar3, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String TvHomeRowsLayer_GVfq81o$lambda$3(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object animateHomeScrollDelta(LazyListState lazyListState, float f10, int i10, boolean z, d7.d<? super t0> dVar) {
        Object objScroll;
        if (z) {
            f10 = -f10;
        }
        float fAbs = Math.abs(f10);
        t0 t0Var = t0.f22605a;
        return (fAbs > 1.0f && (objScroll = lazyListState.scroll(MutatePriority.PreventUserInput, new AnonymousClass2(f10, null), dVar)) == e7.a.f15033i) ? objScroll : t0Var;
    }

    public static /* synthetic */ Object animateHomeScrollDelta$default(LazyListState lazyListState, float f10, int i10, boolean z, d7.d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            z = false;
        }
        return animateHomeScrollDelta(lazyListState, f10, i10, z, dVar);
    }

    private static final Category chooseContinueWatchingCategory(Category category, Category category2) {
        boolean z;
        List<MediaItem> items = category.getItems();
        boolean z5 = true;
        if (items == null || !items.isEmpty()) {
            Iterator<T> it = items.iterator();
            while (it.hasNext()) {
                if (!((MediaItem) it.next()).isPlaceholder()) {
                    z = true;
                    break;
                }
            }
            z = false;
        } else {
            z = false;
        }
        List<MediaItem> items2 = category2.getItems();
        if (items2 == null || !items2.isEmpty()) {
            Iterator<T> it2 = items2.iterator();
            while (it2.hasNext()) {
                if (!((MediaItem) it2.next()).isPlaceholder()) {
                    break;
                }
            }
            z5 = false;
        } else {
            z5 = false;
        }
        return ((!z5 || z) && ((z && !z5) || category2.getItems().size() <= category.getItems().size())) ? category : category2;
    }

    private static final String cleanOverviewText(String str) {
        HomeRegexes homeRegexes = HomeRegexes.INSTANCE;
        String strO = com.arflix.tv.data.repository.g.o(homeRegexes.getWHITESPACE(), homeRegexes.getUNICODE_SPACE().f(" ", homeRegexes.getNON_BREAKING_SPACE().f(" ", homeRegexes.getHTML_TAG().f(" ", str))), " ");
        return kotlin.text.o.h0(strO) ? "No description available." : strO;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HomeHeroPlaybackHandles createHomeHeroPlaybackHandles(Context context) {
        gb.g0 g0Var = new gb.g0();
        g0Var.f15664b = new gb.o(2, 2L, TimeUnit.MINUTES);
        g0Var.f15670h = true;
        g0Var.f15671i = true;
        g0Var.f15668f = true;
        g0Var.d(OkHttpProvider.INSTANCE.getDns());
        TimeUnit timeUnit = TimeUnit.SECONDS;
        g0Var.b(15L, timeUnit);
        g0Var.e(20L, timeUnit);
        OkHttpDataSource.Factory userAgent = new OkHttpDataSource.Factory(new gb.h0(g0Var)).setUserAgent("ARVIO/1.7.0 (Android TV)");
        HlsMediaSource.Factory allowChunklessPreparation = new HlsMediaSource.Factory(userAgent).setAllowChunklessPreparation(true);
        DefaultMediaSourceFactory dataSourceFactory = new DefaultMediaSourceFactory(context).setDataSourceFactory(userAgent);
        ExoPlayer exoPlayerBuild = new ExoPlayer.Builder(context).setMediaSourceFactory(dataSourceFactory).setLoadControl(new DefaultLoadControl.Builder().setBufferDurationsMs(2000, 8000, 750, 1500).setTargetBufferBytes(12582912).setPrioritizeTimeOverSizeThresholds(true).setBackBuffer(0, false).build()).build();
        exoPlayerBuild.setPlayWhenReady(false);
        exoPlayerBuild.setVideoScalingMode(2);
        exoPlayerBuild.setVolume(1.0f);
        return new HomeHeroPlaybackHandles(exoPlayerBuild, allowChunklessPreparation);
    }

    private static final List<Category> deduplicateHomeCategories(List<Category> list) {
        if (list.size() < 2) {
            return list;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
        for (Category categoryChooseContinueWatchingCategory : list) {
            Category category = (Category) linkedHashMap.get(categoryChooseContinueWatchingCategory.getId());
            String id = categoryChooseContinueWatchingCategory.getId();
            if (category != null) {
                if (kotlin.jvm.internal.p.a(categoryChooseContinueWatchingCategory.getId(), "continue_watching")) {
                    categoryChooseContinueWatchingCategory = chooseContinueWatchingCategory(category, categoryChooseContinueWatchingCategory);
                } else if (!category.getItems().isEmpty() || categoryChooseContinueWatchingCategory.getItems().isEmpty()) {
                    categoryChooseContinueWatchingCategory = category;
                }
            }
            linkedHashMap.put(id, categoryChooseContinueWatchingCategory);
        }
        return kotlin.collections.x.c1(linkedHashMap.values());
    }

    private static final String formatBudgetCompact(long j10) {
        if (j10 < C.NANOS_PER_SECOND) {
            return j10 >= 1000000 ? androidx.compose.material3.d.k(j10 / ((long) 1000000), "$", "M") : j10 >= 1000 ? androidx.compose.material3.d.k(j10 / ((long) 1000), "$", "K") : androidx.compose.foundation.c.q(j10, "$");
        }
        return "$" + (j10 / 1.0E9d) + "B";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MediaItem getFocusedItem(List<Category> list, int i10, int i11) {
        List<MediaItem> items;
        List<MediaItem> items2;
        List<MediaItem> items3;
        MediaItem mediaItem;
        Category category = (Category) kotlin.collections.x.p0(i10, list);
        if (category != null && (items3 = category.getItems()) != null && (mediaItem = (MediaItem) kotlin.collections.x.p0(i11, items3)) != null) {
            return mediaItem;
        }
        MediaItem mediaItem2 = (category == null || (items2 = category.getItems()) == null) ? null : (MediaItem) kotlin.collections.x.o0(items2);
        if (mediaItem2 != null) {
            return mediaItem2;
        }
        Category category2 = (Category) kotlin.collections.x.o0(list);
        if (category2 == null || (items = category2.getItems()) == null) {
            return null;
        }
        return (MediaItem) kotlin.collections.x.o0(items);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String homeRowItemKey(MediaItem mediaItem) {
        NextEpisode nextEpisode = mediaItem.getNextEpisode();
        String strN = nextEpisode != null ? androidx.compose.foundation.c.n(nextEpisode.getSeasonNumber(), nextEpisode.getEpisodeNumber(), "_S", "E") : null;
        if (strN == null) {
            strN = "";
        }
        return mediaItem.getMediaType().name() + "-" + mediaItem.getId() + strN;
    }

    private static final String imdbRatingFor(MediaItem mediaItem) {
        return MediaBadgesKt.parseRatingValue(mediaItem.getImdbRating()) > 0.0f ? mediaItem.getImdbRating() : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isActionableHomeItem(MediaItem mediaItem) {
        return (mediaItem == null || mediaItem.getId() <= 0 || mediaItem.isPlaceholder()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0168  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String localizedCategoryTitle(com.arflix.tv.data.model.Category r3, androidx.compose.runtime.Composer r4, int r5) {
        /*
            Method dump skipped, instruction units count: 454
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.home.HomeScreenKt.localizedCategoryTitle(com.arflix.tv.data.model.Category, androidx.compose.runtime.Composer, int):java.lang.String");
    }

    /* JADX INFO: renamed from: lockedHomeRailEndPadding-Ynw60rg, reason: not valid java name */
    private static final float m6248lockedHomeRailEndPaddingYnw60rg(float f10, float f11, float f12, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2013234645, i10, -1, "com.arflix.tv.ui.screens.home.lockedHomeRailEndPadding (HomeScreen.kt:3228)");
        }
        float fM5692unboximpl = ((Dp) qb.d.k(Dp.m5676boximpl(Dp.m5678constructorimpl(Dp.m5678constructorimpl(Dp.m5678constructorimpl(((Configuration) composer.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration())).screenWidthDp) - f11) - f10)), Dp.m5676boximpl(f12))).m5692unboximpl();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return fM5692unboximpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int preferredHomeStartRowIndex(List<Category> list) {
        int i10;
        List<MediaItem> items;
        Iterator<Category> it = list.iterator();
        int i11 = 0;
        loop0: while (true) {
            i10 = -1;
            if (!it.hasNext()) {
                i11 = -1;
                break;
            }
            Category next = it.next();
            if (!kotlin.text.u.P(next.getId(), "collection_row_", false) && ((items = next.getItems()) == null || !items.isEmpty())) {
                Iterator<T> it2 = items.iterator();
                while (it2.hasNext()) {
                    if (!((MediaItem) it2.next()).isPlaceholder()) {
                        break loop0;
                    }
                }
            }
            i11++;
        }
        if (i11 >= 0) {
            return i11;
        }
        Iterator<Category> it3 = list.iterator();
        int i12 = 0;
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            if (!kotlin.text.u.P(it3.next().getId(), "collection_row_", false)) {
                i10 = i12;
                break;
            }
            i12++;
        }
        if (i10 >= 0) {
            return i10;
        }
        return 0;
    }

    public static final int resolveHomeCategoryIndex(List<String> list, String str, int i10) {
        if (list.isEmpty()) {
            return 0;
        }
        int iIndexOf = str != null ? list.indexOf(str) : -1;
        return iIndexOf >= 0 ? iIndexOf : qb.d.n(i10, 0, t7.a.w(list));
    }

    public static final int resolveHomeItemIndex(List<String> list, String str, int i10, boolean z) {
        int iIndexOf = str != null ? list.indexOf(str) : -1;
        if (iIndexOf >= 0) {
            return iIndexOf;
        }
        if (i10 < 0) {
            i10 = 0;
        }
        return (list.isEmpty() || i10 <= t7.a.w(list) || z) ? i10 : t7.a.w(list);
    }

    public static final List<String> stableHomeRowItemKeys(String str, List<MediaItem> list) {
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
        for (MediaItem mediaItem : list) {
            String strHomeRowItemKey = mediaItem.isPlaceholder() ? "placeholder_" + str + "_" + mediaItem.getId() : homeRowItemKey(mediaItem);
            Integer num = (Integer) map.get(strHomeRowItemKey);
            int iIntValue = num != null ? num.intValue() : 0;
            map.put(strHomeRowItemKey, Integer.valueOf(iIntValue + 1));
            if (iIntValue != 0) {
                strHomeRowItemKey = a2.j(iIntValue, strHomeRowItemKey, "#duplicate");
            }
            arrayList.add(strHomeRowItemKey);
        }
        return arrayList;
    }

    public static final String stableHomeRowKey(String str, String str2) {
        return androidx.compose.foundation.c.t(str, "_home_row_", str2);
    }
}
