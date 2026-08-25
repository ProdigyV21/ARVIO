package com.arflix.tv.ui.screens.player;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.RemoteAction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Icon;
import android.media.AudioManager;
import android.media.audiofx.LoudnessEnhancer;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.util.Rational;
import android.view.Window;
import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material.icons.filled.VolumeDownKt;
import androidx.compose.material.icons.filled.VolumeMuteKt;
import androidx.compose.material.icons.filled.VolumeUpKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidImageBitmap_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.CanvasKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.ImageBitmapKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.VectorPainter;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import androidx.core.view.r3;
import androidx.core.view.s3;
import androidx.core.view.t3;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.fragment.app.a2;
import androidx.lifecycle.compose.FlowExtKt;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.tv.material3.TextKt;
import coil.compose.SingletonAsyncImageKt;
import com.arflix.tv.R;
import com.arflix.tv.cast.CastManager;
import com.arflix.tv.data.model.EpisodeIdentity;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.SportsAddonCapabilities;
import com.arflix.tv.data.model.StreamBehaviorHints;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.model.Subtitle;
import com.arflix.tv.data.repository.SkipInterval;
import com.arflix.tv.ui.components.NextEpisodeOverlayKt;
import com.arflix.tv.ui.components.PlaybackQualityBadgesKt;
import com.arflix.tv.ui.components.StreamSelectorKt;
import com.arflix.tv.ui.components.ToastKt;
import com.arflix.tv.ui.components.ToastType;
import com.arflix.tv.ui.components.h2;
import com.arflix.tv.ui.screens.details.discord.DiscordRpcManager;
import com.arflix.tv.ui.skin.ArvioSkinKt;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.util.DeviceType;
import com.arflix.tv.util.FrameRateUtils;
import com.arflix.tv.util.SubtitleScoringKt;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000ö\u0001\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b4\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b4\n\u0002\u0010\"\n\u0002\b\u0013\u001a\u001f\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0004\u0010\u0005\u001a+\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0006*\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u0006H\u0002¢\u0006\u0004\b\u0007\u0010\b\u001a\u0081\u0002\u0010#\u001a\u00020\u001e2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u001b2\u000e\b\u0002\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2,\b\u0002\u0010\"\u001a&\u0012\u0004\u0012\u00020!\u0012\u0006\u0012\u0004\u0018\u00010\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u0000\u0012\u0006\u0012\u0004\u0018\u00010\u0000\u0012\u0004\u0012\u00020\u001e0 H\u0007¢\u0006\u0004\b#\u0010$\u001a\u009d\u0001\u00106\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020%2\u0006\u0010'\u001a\u00020\u00002\u0006\u0010)\u001a\u00020(2\b\b\u0002\u0010+\u001a\u00020*2\b\b\u0002\u0010,\u001a\u00020*2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u001e0-2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u000e\b\u0002\u00100\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u000e\b\u0002\u00101\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u000e\b\u0002\u00102\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u000e\b\u0002\u00103\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0003¢\u0006\u0004\b4\u00105\u001aC\u0010>\u001a\u00020\u001e2\b\u00107\u001a\u0004\u0018\u00010\u00002\u0006\u00108\u001a\u00020\u00002\b\b\u0002\u0010:\u001a\u0002092\n\b\u0002\u0010<\u001a\u0004\u0018\u00010;2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u0000H\u0003¢\u0006\u0004\b>\u0010?\u001aA\u0010C\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020\u00002\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\u0006\u0010A\u001a\u00020\u00032\u0006\u0010B\u001a\u00020\u00032\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0003¢\u0006\u0004\bC\u0010D\u001a/\u0010K\u001a\u0004\u0018\u00010\u000b2\u0006\u0010F\u001a\u00020E2\u0006\u0010H\u001a\u00020G2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020G0IH\u0002¢\u0006\u0004\bK\u0010L\u001a'\u0010N\u001a\u0004\u0018\u00010\u000b2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020G0I2\u0006\u0010M\u001a\u00020\u0000H\u0002¢\u0006\u0004\bN\u0010O\u001a\u001d\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00000I2\u0006\u0010M\u001a\u00020\u0000H\u0002¢\u0006\u0004\bP\u0010Q\u001a\u0019\u0010S\u001a\u00020\u00032\b\u0010R\u001a\u0004\u0018\u00010\u0000H\u0002¢\u0006\u0004\bS\u0010T\u001a\u0019\u0010V\u001a\u00020\u00002\b\u0010U\u001a\u0004\u0018\u00010\u0000H\u0002¢\u0006\u0004\bV\u0010W\u001a\u000f\u0010X\u001a\u00020\u0000H\u0003¢\u0006\u0004\bX\u0010Y\u001a\u001f\u0010\\\u001a\u00020\u00002\u0006\u0010Z\u001a\u00020\u00182\u0006\u0010[\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\\\u0010]\u001aW\u0010g\u001a\u00020\u00032\u0006\u0010_\u001a\u00020^2\u0006\u0010`\u001a\u00020\u000b2\u0006\u0010a\u001a\u00020\u000b2\u0012\u0010b\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u001e0-2\f\u0010c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\f\u0010d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002¢\u0006\u0004\be\u0010f\u001aÔ\u0002\u0010\u0080\u0001\u001a\u00020\u001e2\f\u0010i\u001a\b\u0012\u0004\u0012\u00020h0I2\b\u0010j\u001a\u0004\u0018\u00010h2\b\b\u0002\u0010k\u001a\u00020\u00032\b\b\u0002\u0010l\u001a\u00020\u00032\b\b\u0002\u0010m\u001a\u00020\u00002\b\b\u0002\u0010n\u001a\u00020\u00002\b\b\u0002\u0010o\u001a\u00020\u00032\b\b\u0002\u0010p\u001a\u00020\u00032\f\u0010J\u001a\b\u0012\u0004\u0012\u00020G0I2\u0006\u0010q\u001a\u00020\u000b2\u0006\u0010r\u001a\u00020\u000b2\u0006\u0010s\u001a\u00020\u000b2*\u0010u\u001a&\u0012\"\u0012 \u0012\u0004\u0012\u00020\u0000\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020h0t0I0t0I2\u0006\u0010v\u001a\u00020\u000b2\u0006\u0010w\u001a\u00020\u000b2\u0006\u0010x\u001a\u00020\u000b2\b\b\u0002\u0010y\u001a\u00020\u00002\u0012\u0010z\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u001e0-2\u0012\u0010{\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u001e0-2\u0012\u0010|\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u00020\u001e0-2\u000e\b\u0002\u0010}\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u000e\b\u0002\u0010~\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u000e\b\u0002\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\f\u0010c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0003¢\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001\u001a;\u0010\u0083\u0001\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020\u00002\u0007\u0010\u0082\u0001\u001a\u00020\u00032\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\b\b\u0002\u0010:\u001a\u000209H\u0003¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001\u001aR\u0010\u0088\u0001\u001a\u00020\u001e2\u0007\u0010\u0085\u0001\u001a\u00020\u00002\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00002\u0007\u0010\u0082\u0001\u001a\u00020\u00032\u0006\u0010A\u001a\u00020\u00032\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u000b\b\u0002\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u0000H\u0003¢\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001\u001a=\u0010\u008c\u0001\u001a\u00020\u001e2\u0006\u0010\u0001\u001a\u00020\u00002\u0007\u0010\u008a\u0001\u001a\u00020\u000b2\u0006\u0010A\u001a\u00020\u00032\u0007\u0010\u008b\u0001\u001a\u00020\u00032\u0007\u0010\u0082\u0001\u001a\u00020\u0003H\u0003¢\u0006\u0006\b\u008c\u0001\u0010\u008d\u0001\u001a<\u0010\u008f\u0001\u001a\u00020\u001e2\u0006\u0010\u0001\u001a\u00020\u00002\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u00002\u0007\u0010\u0082\u0001\u001a\u00020\u00032\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0003¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001\u001a:\u0010\u0091\u0001\u001a\u00020\u001e2\u0007\u0010\u0085\u0001\u001a\u00020\u00002\u0007\u0010\u0082\u0001\u001a\u00020\u00032\u0006\u0010A\u001a\u00020\u00032\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0003¢\u0006\u0006\b\u0091\u0001\u0010\u0092\u0001\u001a\u001b\u0010\u0094\u0001\u001a\u00020\u00002\u0007\u0010\u0093\u0001\u001a\u00020\u0018H\u0002¢\u0006\u0006\b\u0094\u0001\u0010\u0095\u0001\u001a\u001b\u0010\u0097\u0001\u001a\u00020\u00002\u0007\u0010\u0096\u0001\u001a\u00020\u0018H\u0002¢\u0006\u0006\b\u0097\u0001\u0010\u0095\u0001\u001a*\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u00002\t\u0010\u0098\u0001\u001a\u0004\u0018\u00010\u00002\t\u0010\u0099\u0001\u001a\u0004\u0018\u00010\u0000H\u0002¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001\u001a\u001a\u0010\u009d\u0001\u001a\u00020\u00002\u0007\u0010\u009c\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0005\b\u009d\u0001\u0010W\u001a&\u0010 \u0001\u001a\u00020\u00032\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010h2\u0007\u0010\u009f\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0006\b \u0001\u0010¡\u0001\u001a(\u0010¢\u0001\u001a\u0004\u0018\u00010\u00182\t\u0010\u009e\u0001\u001a\u0004\u0018\u00010h2\u0007\u0010\u009f\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0006\b¢\u0001\u0010£\u0001\u001a\u001b\u0010¤\u0001\u001a\u00020\u00002\u0007\u0010\u0093\u0001\u001a\u00020\u0018H\u0002¢\u0006\u0006\b¤\u0001\u0010\u0095\u0001\u001a\u001b\u0010¥\u0001\u001a\u00020\u00002\u0007\u0010\u0086\u0001\u001a\u00020hH\u0002¢\u0006\u0006\b¥\u0001\u0010¦\u0001\u001a\u001a\u0010§\u0001\u001a\u00020\u00002\u0006\u0010H\u001a\u00020GH\u0002¢\u0006\u0006\b§\u0001\u0010¨\u0001\u001a'\u0010ª\u0001\u001a\t\u0012\u0005\u0012\u00030©\u00010I2\f\u0010i\u001a\b\u0012\u0004\u0012\u00020h0IH\u0002¢\u0006\u0006\bª\u0001\u0010«\u0001\u001a\u001a\u0010\u00ad\u0001\u001a\u00020\u00002\u0007\u0010¬\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0005\b\u00ad\u0001\u0010W\u001a.\u0010²\u0001\u001a\u00030±\u00012\u0007\u0010®\u0001\u001a\u00020\u000b2\u0007\u0010¯\u0001\u001a\u00020\u00032\u0007\u0010°\u0001\u001a\u00020\u0003H\u0002¢\u0006\u0006\b²\u0001\u0010³\u0001\u001a'\u0010·\u0001\u001a\u00020\u00182\n\u0010µ\u0001\u001a\u0005\u0018\u00010´\u00012\u0007\u0010¶\u0001\u001a\u00020\u0003H\u0002¢\u0006\u0006\b·\u0001\u0010¸\u0001\u001a%\u0010¼\u0001\u001a\u00020\u00002\b\u0010º\u0001\u001a\u00030¹\u00012\u0007\u0010»\u0001\u001a\u00020\u0003H\u0002¢\u0006\u0006\b¼\u0001\u0010½\u0001\u001a\u001c\u0010¾\u0001\u001a\u00020\u00002\b\u0010º\u0001\u001a\u00030¹\u0001H\u0002¢\u0006\u0006\b¾\u0001\u0010¿\u0001\u001a\u001b\u0010Á\u0001\u001a\u00020\u00182\u0007\u0010À\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0006\bÁ\u0001\u0010Â\u0001\u001a'\u0010Ã\u0001\u001a\u00020\u00032\u0007\u0010¬\u0001\u001a\u00020\u00002\n\u0010µ\u0001\u001a\u0005\u0018\u00010´\u0001H\u0002¢\u0006\u0006\bÃ\u0001\u0010Ä\u0001\u001a\u001e\u0010Å\u0001\u001a\u00020\u00032\n\u0010µ\u0001\u001a\u0005\u0018\u00010´\u0001H\u0002¢\u0006\u0006\bÅ\u0001\u0010Æ\u0001\u001a\u001e\u0010Ç\u0001\u001a\u00020\u00032\n\u0010µ\u0001\u001a\u0005\u0018\u00010´\u0001H\u0002¢\u0006\u0006\bÇ\u0001\u0010Æ\u0001\u001a\u001b\u0010É\u0001\u001a\u00020\u001e2\u0007\u0010È\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0006\bÉ\u0001\u0010Ê\u0001\u001a\u0012\u0010Ë\u0001\u001a\u00020\u000bH\u0002¢\u0006\u0006\bË\u0001\u0010Ì\u0001\u001a\u001b\u0010Ï\u0001\u001a\u0005\u0018\u00010Î\u0001*\u00030Í\u0001H\u0082\u0010¢\u0006\u0006\bÏ\u0001\u0010Ð\u0001\u001a$\u0010Ó\u0001\u001a\u00020\u000b2\u0007\u0010Ñ\u0001\u001a\u00020\u00002\u0007\u0010Ò\u0001\u001a\u00020\u000bH\u0002¢\u0006\u0006\bÓ\u0001\u0010Ô\u0001\u001aU\u0010Ü\u0001\u001a\u00020\u001e2\b\u0010Ö\u0001\u001a\u00030Õ\u00012\u0006\u0010\n\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\u0007\u0010×\u0001\u001a\u00020\u00032\b\u0010Ù\u0001\u001a\u00030Ø\u00012\b\b\u0002\u0010:\u001a\u000209H\u0003¢\u0006\u0006\bÚ\u0001\u0010Û\u0001\u001a\u0012\u0010Ý\u0001\u001a\u00020\u001eH\u0003¢\u0006\u0006\bÝ\u0001\u0010Þ\u0001\u001a8\u0010ß\u0001\u001a\u00020\u00002\b\u0010Ö\u0001\u001a\u00030Õ\u00012\u0006\u0010\n\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000bH\u0002¢\u0006\u0006\bß\u0001\u0010à\u0001\u001a\u001e\u0010á\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010µ\u0001\u001a\u00030´\u0001H\u0002¢\u0006\u0006\bá\u0001\u0010â\u0001\u001a#\u0010ã\u0001\u001a\u00020\u000b2\u0006\u0010y\u001a\u00020\u00002\u0007\u0010\u0086\u0001\u001a\u00020hH\u0002¢\u0006\u0006\bã\u0001\u0010ä\u0001\u001a¥\u0001\u0010ð\u0001\u001a\u00020\u001e2\u0007\u0010å\u0001\u001a\u00020\u000b2\u0007\u0010æ\u0001\u001a\u00020\u00182\u0007\u0010ç\u0001\u001a\u00020\u000b2\u0007\u0010è\u0001\u001a\u00020\u000b2\u0013\u0010é\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u001e0-2\r\u0010ê\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\r\u0010ë\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\r\u0010ì\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\r\u0010í\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\r\u0010î\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\r\u0010ï\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0003¢\u0006\u0006\bð\u0001\u0010ñ\u0001\u001ab\u0010÷\u0001\u001a\u00020\u001e2\u0007\u0010\u0085\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0007\u0010\u009e\u0001\u001a\u00020\u00032\b\u0010ò\u0001\u001a\u00030Ø\u00012\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\r\u0010ó\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\r\u0010ô\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0003¢\u0006\u0006\bõ\u0001\u0010ö\u0001\u001a\u001a\u0010ø\u0001\u001a\u00020\u00002\u0007\u0010¬\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0005\bø\u0001\u0010W\"\u0017\u0010ù\u0001\u001a\u00020\u00008\u0002X\u0082T¢\u0006\b\n\u0006\bù\u0001\u0010ú\u0001\"\u0017\u0010û\u0001\u001a\u00020\u00008\u0002X\u0082T¢\u0006\b\n\u0006\bû\u0001\u0010ú\u0001\"\u0017\u0010ü\u0001\u001a\u00020\u00008\u0002X\u0082T¢\u0006\b\n\u0006\bü\u0001\u0010ú\u0001\"\u0017\u0010ý\u0001\u001a\u00020\u00008\u0002X\u0082T¢\u0006\b\n\u0006\bý\u0001\u0010ú\u0001\"\u0017\u0010þ\u0001\u001a\u00020\u00008\u0002X\u0082T¢\u0006\b\n\u0006\bþ\u0001\u0010ú\u0001\"\u0017\u0010ÿ\u0001\u001a\u00020\u00188\u0002X\u0082T¢\u0006\b\n\u0006\bÿ\u0001\u0010\u0080\u0002\"\u0017\u0010\u0081\u0002\u001a\u00020\u00038\u0002X\u0082T¢\u0006\b\n\u0006\b\u0081\u0002\u0010\u0082\u0002¨\u0006Ý\u0002²\u0006\u000e\u0010Ö\u0001\u001a\u00030Õ\u00018\nX\u008a\u0084\u0002²\u0006\u000e\u0010\u0083\u0002\u001a\u00030Õ\u00018\nX\u008a\u0084\u0002²\u0006\u000e\u0010\u0085\u0002\u001a\u00030\u0084\u00028\nX\u008a\u0084\u0002²\u0006\u000f\u0010\u0086\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0087\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010»\u0001\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0088\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0089\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u008a\u0002\u001a\u00020\u00188\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u008b\u0002\u001a\u00020\u00188\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010<\u001a\u00020;8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u008c\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u008d\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u008e\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u008f\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0090\u0002\u001a\u00020\u00188\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0091\u0002\u001a\u00020\u00188\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0092\u0002\u001a\u00020\u00188\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0093\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0094\u0002\u001a\u00020\u00188\n@\nX\u008a\u008e\u0002²\u0006\u0012\u0010\u0096\u0002\u001a\u0005\u0018\u00010\u0095\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0097\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0098\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0099\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u009a\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u009b\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u009c\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u009d\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u009e\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u009f\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010 \u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010¡\u0002\u001a\u0004\u0018\u00010!8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010¢\u0002\u001a\u0004\u0018\u00010\u00008\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010£\u0002\u001a\u0004\u0018\u00010\u00008\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010¤\u0002\u001a\u0004\u0018\u00010\u00008\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010¥\u0002\u001a\u0004\u0018\u00010!8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010¦\u0002\u001a\u0004\u0018\u00010!8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010§\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¨\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010©\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010ª\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010v\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010w\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010x\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010«\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¬\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u00ad\u0002\u001a\u00020\u00188\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010®\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¯\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010°\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u0014\u0010J\u001a\b\u0012\u0004\u0012\u00020G0I8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010q\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010±\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010²\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010³\u0002\u001a\u0004\u0018\u00010\u00188\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010´\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010µ\u0002\u001a\u0004\u0018\u00010\u00188\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010¶\u0002\u001a\u0004\u0018\u00010\u00008\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010·\u0002\u001a\u0004\u0018\u00010\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¸\u0002\u001a\u00020\u00188\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¹\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010º\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010»\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¼\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010½\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010¾\u0002\u001a\u0004\u0018\u00010\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¿\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010À\u0002\u001a\u0004\u0018\u00010\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Á\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Â\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Ã\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Ä\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010Å\u0002\u001a\u0004\u0018\u00010\u00188\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010Æ\u0002\u001a\u0004\u0018\u00010\u00188\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Ç\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010È\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010É\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u0016\u0010Ë\u0002\u001a\t\u0012\u0004\u0012\u00020\u000b0Ê\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Ì\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Í\u0002\u001a\u00020\u00188\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Î\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Ï\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Ð\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Ñ\u0002\u001a\u00020\u00188\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Ò\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Ó\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\r\u0010Ô\u0002\u001a\u00020\u00038\nX\u008a\u0084\u0002²\u0006\u000f\u0010Õ\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\r\u0010Ö\u0002\u001a\u00020;8\nX\u008a\u0084\u0002²\u0006\u000f\u0010×\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Ø\u0002\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\r\u0010Ù\u0002\u001a\u00020;8\nX\u008a\u0084\u0002²\u0006\r\u0010Ù\u0002\u001a\u00020;8\nX\u008a\u0084\u0002²\u0006\r\u0010Ú\u0002\u001a\u00020;8\nX\u008a\u0084\u0002²\u0006\r\u0010Ù\u0002\u001a\u00020;8\nX\u008a\u0084\u0002²\u0006\u000f\u0010Û\u0002\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010Ü\u0002\u001a\u00020\u000b8\n@\nX\u008a\u008e\u0002"}, d2 = {"", ContentDisposition.Parameters.Name, "value", "", "isSafePlaybackHeader", "(Ljava/lang/String;Ljava/lang/String;)Z", "", "safePlaybackHeaders", "(Ljava/util/Map;)Ljava/util/Map;", "Lcom/arflix/tv/data/model/MediaType;", "mediaType", "", "mediaId", "seasonNumber", "episodeNumber", "tmdbSeasonNumber", "tmdbEpisodeNumber", "kitsuId", "kitsuEpisodeNumber", "imdbId", "streamUrl", "preferredAddonId", "preferredSourceName", "preferredBingeGroup", "", "startPositionMs", "isLiveStream", "Lcom/arflix/tv/ui/screens/player/PlayerViewModel;", "viewModel", "Lkotlin/Function0;", "Lx6/t0;", "onBack", "Lkotlin/Function4;", "Lcom/arflix/tv/data/model/EpisodeIdentity;", "onPlayNext", "PlayerScreen", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;ZLcom/arflix/tv/ui/screens/player/PlayerViewModel;Lr7/a;Lr7/r;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/ui/graphics/vector/ImageVector;", "icon", "contentDescription", "Landroidx/compose/ui/focus/FocusRequester;", "focusRequester", "Landroidx/compose/ui/unit/Dp;", ContentDisposition.Parameters.Size, "iconSize", "Lkotlin/Function1;", "onFocusChanged", "onClick", "onLeftKey", "onRightKey", "onUpKey", "onDownKey", "PlayerIconButton-xI7PXdU", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Landroidx/compose/ui/focus/FocusRequester;FFLr7/l;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;III)V", "PlayerIconButton", "logoUrl", LinkHeader.Parameters.Title, "Landroidx/compose/ui/Modifier;", "modifier", "", "progress", "phaseLabel", "PulsingLogo", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/ui/Modifier;Ljava/lang/Float;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)V", "text", "isFocused", "isPrimary", "ErrorButton", "(Ljava/lang/String;Landroidx/compose/ui/graphics/vector/ImageVector;ZZLr7/a;Landroidx/compose/runtime/Composer;II)V", "Landroidx/media3/exoplayer/ExoPlayer;", "exoPlayer", "Lcom/arflix/tv/ui/screens/player/AudioTrackInfo;", "track", "", "audioTracks", "applyAudioTrackSelection", "(Landroidx/media3/exoplayer/ExoPlayer;Lcom/arflix/tv/ui/screens/player/AudioTrackInfo;Ljava/util/List;)Ljava/lang/Integer;", "preferredCode", "findPreferredAudioTrackIndex", "(Ljava/util/List;Ljava/lang/String;)Ljava/lang/Integer;", "nativeAudioLanguageHints", "(Ljava/lang/String;)Ljava/util/List;", "mimeType", "isBitmapSubtitleMime", "(Ljava/lang/String;)Z", "code", "getFullLanguageName", "(Ljava/lang/String;)Ljava/lang/String;", "rememberPlayerClockFormat", "(Landroidx/compose/runtime/Composer;I)Ljava/lang/String;", "timestampMs", "clockFormat", "formatPlayerClockTime", "(JLjava/lang/String;)Ljava/lang/String;", "Landroidx/compose/ui/input/key/Key;", "key", "currentIndex", "maxIndex", "setIndex", "onClose", "onSelect", "handleSubtitleMenuKey-SfgetU0", "(JIILr7/l;Lr7/a;Lr7/a;)Z", "handleSubtitleMenuKey", "Lcom/arflix/tv/data/model/Subtitle;", "subtitles", "selectedSubtitle", "isAiTranslating", "isAiAvailable", "aiTargetLanguageName", "matchLanguageName", "isLiveAudioTranslating", "isFindingBestMatch", "selectedAudioIndex", "activeTab", "focusedIndex", "Lx6/x;", "subtitleGroups", "subtitleLangIndex", "subtitleTrackIndex", "subtitlePanelFocus", "streamSource", "onTabChanged", "onSelectSubtitle", "onSelectAudio", "onToggleAi", "onToggleLiveAudio", "onFindBestMatch", "SubtitleMenu", "(Ljava/util/List;Lcom/arflix/tv/data/model/Subtitle;ZZLjava/lang/String;Ljava/lang/String;ZZLjava/util/List;IIILjava/util/List;IIILjava/lang/String;Lr7/l;Lr7/l;Lr7/l;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;IIII)V", "isSelected", "TabButton", "(Ljava/lang/String;ZLr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "label", MediaTrack.ROLE_SUBTITLE, "subtitleDetail", "TrackMenuItem", "(Ljava/lang/String;Ljava/lang/String;ZZLr7/a;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)V", "count", "isActivePanel", "LangPanelItem", "(Ljava/lang/String;IZZZLandroidx/compose/runtime/Composer;I)V", MediaTrack.ROLE_DESCRIPTION, "MobileTrackItem", "(Ljava/lang/String;Ljava/lang/String;ZLr7/a;Landroidx/compose/runtime/Composer;I)V", "SubtitleMenuItem", "(Ljava/lang/String;ZZLr7/a;Landroidx/compose/runtime/Composer;I)V", "ms", "formatTime", "(J)Ljava/lang/String;", "bytes", "formatFileSize", "codec", "trackLabel", "detectAudioCodecLabel", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", TtmlNode.ATTR_ID, "subtitleBaseId", "selected", "rowId", "isSameSubtitleTrack", "(Lcom/arflix/tv/data/model/Subtitle;Ljava/lang/String;)Z", "matchedOffsetMsFor", "(Lcom/arflix/tv/data/model/Subtitle;Ljava/lang/String;)Ljava/lang/Long;", "formatMatchOffset", "subtitleTrackId", "(Lcom/arflix/tv/data/model/Subtitle;)Ljava/lang/String;", "audioTrackKey", "(Lcom/arflix/tv/ui/screens/player/AudioTrackInfo;)Ljava/lang/String;", "Landroidx/media3/common/MediaItem$SubtitleConfiguration;", "buildExternalSubtitleConfigurations", "(Ljava/util/List;)Ljava/util/List;", "url", "subtitleMimeTypeFromUrl", "memoryClassMb", "isLowRamDevice", "isTvDevice", "Lcom/arflix/tv/ui/screens/player/PlaybackBufferProfile;", "buildPlaybackBufferProfile", "(IZZ)Lcom/arflix/tv/ui/screens/player/PlaybackBufferProfile;", "Lcom/arflix/tv/data/model/StreamSource;", "stream", "isManualSelection", "estimateInitialStartupTimeoutMs", "(Lcom/arflix/tv/data/model/StreamSource;Z)J", "Landroidx/media3/common/PlaybackException;", "error", "hasPlaybackStarted", "playbackErrorMessageFor", "(Landroidx/media3/common/PlaybackException;Z)Ljava/lang/String;", "classifyPlaybackFailure", "(Landroidx/media3/common/PlaybackException;)Ljava/lang/String;", "sizeStr", "parseSizeToBytes", "(Ljava/lang/String;)J", "isLikelyHlsPlaybackUrl", "(Ljava/lang/String;Lcom/arflix/tv/data/model/StreamSource;)Z", "isLikelyHeavyStream", "(Lcom/arflix/tv/data/model/StreamSource;)Z", "isLikelyDolbyVisionStream", "message", "playbackStartupDiag", "(Ljava/lang/String;)V", "resolveFrameRateOffStrategy", "()I", "Landroid/content/Context;", "Landroid/app/Activity;", "findActivity", "(Landroid/content/Context;)Landroid/app/Activity;", "fieldName", "fallback", "readMedia3FrameRateConst", "(Ljava/lang/String;I)I", "Lcom/arflix/tv/ui/screens/player/PlayerUiState;", "uiState", "isPaused", "Landroidx/compose/ui/graphics/Color;", "accentColor", "PlayerMetadataChrome-cd68TDI", "(Lcom/arflix/tv/ui/screens/player/PlayerUiState;Lcom/arflix/tv/data/model/MediaType;Ljava/lang/Integer;Ljava/lang/Integer;ZJLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "PlayerMetadataChrome", "PlayerMetaSeparator", "(Landroidx/compose/runtime/Composer;I)V", "buildPlaybackBaseMetaLine", "(Lcom/arflix/tv/ui/screens/player/PlayerUiState;Lcom/arflix/tv/data/model/MediaType;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/String;", "formatStreamSizeInGb", "(Lcom/arflix/tv/data/model/StreamSource;)Ljava/lang/String;", "subtitleMatchScore", "(Ljava/lang/String;Lcom/arflix/tv/data/model/Subtitle;)I", "selectedRow", "syncOffsetMs", "sizePct", "verticalPct", "onRowSelect", "onOffsetDecrease", "onOffsetIncrease", "onSizeDecrease", "onSizeIncrease", "onVerticalDecrease", "onVerticalIncrease", "PlayerSubtitleSettingsPanel", "(IJIILr7/l;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "accent", "onDecrease", "onIncrease", "PlayerSubtitleSettingRow-fWhpE4E", "(Ljava/lang/String;Ljava/lang/String;ZJLr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "PlayerSubtitleSettingRow", "guessCastMimeType", "PIP_ACTION_REWIND", "Ljava/lang/String;", "PIP_ACTION_PLAY_PAUSE", "PIP_ACTION_FORWARD", "ADDON_SUB_ID_PREFIX", "MATCH_OFFSET_ID_MARKER", "SUBTITLE_PRELOAD_GATE_TIMEOUT_MS", "J", "PLAYER_SCREEN_DIAGNOSTICS", "Z", "latestUiState", "Lcom/arflix/tv/cast/CastManager$CastState;", "castState", "isPlaying", "isBuffering", "firstVideoFrameRendered", "showControls", "currentPosition", "duration", "currentPlaybackState", "nextEpisodeTransitionInProgress", "skipAmount", "showSkipOverlay", "lastSkipTime", "skipStartPosition", "skipPreviewPosition", "isControlScrubbing", "scrubPreviewPosition", "Lka/v1;", "controlsSeekJob", "currentVolume", "showVolumeIndicator", "showAspectIndicator", "aspectIndicatorTrigger", "isMuted", "volumeBeforeMute", "focusedButton", "showSubtitleMenu", "showSourceMenu", "showNextEpisodePrompt", "pendingNextIdentity", "pendingNextAddonId", "pendingNextSourceName", "pendingNextBingeGroup", "nextEpisodeIdentity", "previousEpisodeIdentity", "nextEpisodePromptButton", "playerResizeMode", "subtitleMenuIndex", "subtitleMenuTab", "showSubtitleSettings", "subtitleSettingsRow", "subtitleSyncOffsetMs", "subtitleSizePct", "subtitleVerticalPct", "useVideoFrameSubtitleViewport", "userPickedAudioForStream", "errorModalFocusIndex", "bufferingStartTime", "userSelectedSourceManually", "streamSelectedTime", "startupPhase", "switchNotice", "switchNoticeUntilMs", "playbackIssueReported", "startupRecoverAttempted", "startupHardFailureReported", "startupSameSourceRetryCount", "startupSameSourceRefreshAttempted", "startupUrlLock", "pendingStartupFailover", "pendingStartupFailoverMessage", "pendingStartupFailureRecorded", "dvStartupFallbackStage", "midPlaybackRecoveryAttempts", "blackVideoRecoveryStage", "blackVideoReadySinceMs", "readyPlayingSinceMs", "rebufferRecoverAttempted", "longRebufferCount", "autoAdvanceAttempts", "", "triedStreamIndexes", "isAutoAdvancing", "lastProgressReportSecond", "playerReleased", "isInPipMode", "currentStreamIndex", "lastCastPositionMs", "subtitleRebuildDone", "initialSubtitleCount", "isTranslatingLive", "trackbarFocused", "trackbarHeight", "trackbarWidthPx", "focused", "scale", "animatedProgress", "resolvedFormat", "mobileTab", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PlayerScreenKt {
    private static final String ADDON_SUB_ID_PREFIX = "arvio-addon-sub:";
    private static final String MATCH_OFFSET_ID_MARKER = "#ofs";
    private static final String PIP_ACTION_FORWARD = "com.arflix.tv.pip.FORWARD";
    private static final String PIP_ACTION_PLAY_PAUSE = "com.arflix.tv.pip.PLAY_PAUSE";
    private static final String PIP_ACTION_REWIND = "com.arflix.tv.pip.REWIND";
    private static final boolean PLAYER_SCREEN_DIAGNOSTICS = true;
    private static final long SUBTITLE_PRELOAD_GATE_TIMEOUT_MS = 14000;

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
            try {
                iArr[androidx.lifecycle.p.ON_STOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:141:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0166  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01de  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void ErrorButton(java.lang.String r32, androidx.compose.ui.graphics.vector.ImageVector r33, boolean r34, boolean r35, r7.a<x6.t0> r36, androidx.compose.runtime.Composer r37, int r38, int r39) {
        /*
            Method dump skipped, instruction units count: 784
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.ErrorButton(java.lang.String, androidx.compose.ui.graphics.vector.ImageVector, boolean, boolean, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float ErrorButton$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ErrorButton$lambda$1$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ErrorButton$lambda$2$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(ErrorButton$lambda$0(state));
        graphicsLayerScope.setScaleY(ErrorButton$lambda$0(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ErrorButton$lambda$4(String str, ImageVector imageVector, boolean z, boolean z5, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        ErrorButton(str, imageVector, z, z5, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LangPanelItem(String str, int i10, boolean z, boolean z5, boolean z10, Composer composer, int i11) {
        String str2;
        int i12;
        long jM3509getWhite0d7_KjU;
        int i13;
        Object obj;
        float f10;
        Composer composerStartRestartGroup = composer.startRestartGroup(-679522481);
        if ((i11 & 6) == 0) {
            str2 = str;
            i12 = (composerStartRestartGroup.changed(str2) ? 4 : 2) | i11;
        } else {
            str2 = str;
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changed(z5) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i12 |= composerStartRestartGroup.changed(z10) ? 16384 : 8192;
        }
        if ((i12 & 9363) == 9362 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-679522481, i12, -1, "com.arflix.tv.ui.screens.player.LangPanelItem (PlayerScreen.kt:5421)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            int i14 = i12;
            float f11 = 8;
            float f12 = 10;
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(com.arflix.tv.data.repository.g.l(f11, SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), z ? Color.INSTANCE.m3509getWhite0d7_KjU() : z5 ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : Color.INSTANCE.m3507getTransparent0d7_KjU()), Dp.m5678constructorimpl(f12), Dp.m5678constructorimpl(f11));
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment.Vertical centerVertically = companion2.getCenterVertically();
            Arrangement.HorizontalOrVertical spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
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
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(arflixTypography.getBody(), 0L, TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null);
            Color.Companion companion4 = Color.INSTANCE;
            TextKt.m6020Text4IGK_g(str2, androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null), z ? companion4.m3498getBlack0d7_KjU() : companion4.m3509getWhite0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, textStyleM5205copyp1EtxEg$default, composerStartRestartGroup, i14 & 14, 3120, 55288);
            composerStartRestartGroup = composerStartRestartGroup;
            if (i10 > 0) {
                composerStartRestartGroup.startReplaceGroup(-1629050003);
                androidx.compose.material3.d.w(4, companion, composerStartRestartGroup, 6);
                Color.Companion companion5 = Color.INSTANCE;
                if (z) {
                    jM3509getWhite0d7_KjU = companion5.m3498getBlack0d7_KjU();
                    i13 = 14;
                    obj = null;
                    f10 = 0.2f;
                } else {
                    jM3509getWhite0d7_KjU = companion5.m3509getWhite0d7_KjU();
                    i13 = 14;
                    obj = null;
                    f10 = 0.15f;
                }
                Modifier modifierH = a2.h(1, com.arflix.tv.data.repository.g.j(f12, companion, Color.m3471copywmQWz5c$default(jM3509getWhite0d7_KjU, f10, 0.0f, 0.0f, 0.0f, i13, obj)), Dp.m5678constructorimpl(5), composerStartRestartGroup, 733328855);
                MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composerStartRestartGroup, 0, -1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierH);
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
                String strValueOf = String.valueOf(i10);
                TextStyle textStyleM5205copyp1EtxEg$default2 = TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(10), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null);
                Color.Companion companion6 = Color.INSTANCE;
                TextKt.m6020Text4IGK_g(strValueOf, null, z ? companion6.m3498getBlack0d7_KjU() : companion6.m3509getWhite0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, textStyleM5205copyp1EtxEg$default2, composerStartRestartGroup, 0, 0, 65530);
                composerStartRestartGroup = composerStartRestartGroup;
                com.arflix.tv.data.repository.g.q(composerStartRestartGroup);
            } else if (z10) {
                composerStartRestartGroup.startReplaceGroup(-1628382542);
                ImageVector check = CheckKt.getCheck(Icons.INSTANCE.getDefault());
                Color.Companion companion7 = Color.INSTANCE;
                IconKt.m1610Iconww6aTOc(check, (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(14)), z ? companion7.m3498getBlack0d7_KjU() : companion7.m3509getWhite0d7_KjU(), composerStartRestartGroup, 432, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-1628152553);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new h2(str, i10, z, z5, z10, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LangPanelItem$lambda$1(String str, int i10, boolean z, boolean z5, boolean z10, int i11, Composer composer, int i12) {
        LangPanelItem(str, i10, z, z5, z10, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MobileTrackItem(String str, String str2, boolean z, r7.a<x6.t0> aVar, Composer composer, int i10) {
        String str3;
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(1470552116);
        if ((i10 & 6) == 0) {
            str3 = str;
            i11 = (composerStartRestartGroup.changed(str3) ? 4 : 2) | i10;
        } else {
            str3 = str;
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(str2) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 2048 : 1024;
        }
        if ((i11 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1470552116, i11, -1, "com.arflix.tv.ui.screens.player.MobileTrackItem (PlayerScreen.kt:5480)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion2 = Composer.INSTANCE;
            if (objRememberedValue == companion2.getEmpty()) {
                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
            boolean z5 = (i11 & 7168) == 2048;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue2 == companion2.getEmpty()) {
                objRememberedValue2 = new k(7, aVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            float f10 = 12;
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(ClickableKt.m212clickableO2vRcR0$default(modifierFillMaxWidth$default, mutableInteractionSource, null, false, null, null, (r7.a) objRememberedValue2, 28, null), Dp.m5678constructorimpl(16), Dp.m5678constructorimpl(f10));
            Alignment.Companion companion3 = Alignment.INSTANCE;
            Alignment.Vertical centerVertically = companion3.getCenterVertically();
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.HorizontalOrVertical spaceBetween = arrangement.getSpaceBetween();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
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
            r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            Modifier modifierA = androidx.compose.foundation.layout.e.a(RowScopeInstance.INSTANCE, companion, 1.0f, false, 2, null);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion3, arrangement.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierA);
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
            r7.p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyK, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(arflixTypography.getBody(), 0L, TextUnitKt.getSp(14), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null);
            Color.Companion companion5 = Color.INSTANCE;
            long jM3509getWhite0d7_KjU = z ? companion5.m3509getWhite0d7_KjU() : Color.m3471copywmQWz5c$default(companion5.m3509getWhite0d7_KjU(), 0.85f, 0.0f, 0.0f, 0.0f, 14, null);
            TextOverflow.Companion companion6 = TextOverflow.INSTANCE;
            int i12 = i11;
            TextKt.m6020Text4IGK_g(str3, null, jM3509getWhite0d7_KjU, 0L, null, null, null, 0L, null, null, 0L, companion6.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, textStyleM5205copyp1EtxEg$default, composerStartRestartGroup, i11 & 14, 3120, 55290);
            composerStartRestartGroup = composerStartRestartGroup;
            if (str2 != null) {
                composerStartRestartGroup.startReplaceGroup(877042133);
                TextKt.m6020Text4IGK_g(str2, null, Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, companion6.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(12), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, ((i12 >> 3) & 14) | RendererCapabilities.DECODER_SUPPORT_MASK, 3120, 55290);
                composerStartRestartGroup = composerStartRestartGroup;
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(877331828);
                composerStartRestartGroup.endReplaceGroup();
            }
            androidx.compose.foundation.c.y(composerStartRestartGroup);
            if (z) {
                composerStartRestartGroup.startReplaceGroup(1932869646);
                IconKt.m1610Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.selected, composerStartRestartGroup, 0), SizeKt.m578size3ABfNKs(PaddingKt.m533paddingqDBjuR0$default(companion, Dp.m5678constructorimpl(f10), 0.0f, 0.0f, 0.0f, 14, null), Dp.m5678constructorimpl(20)), ColorKt.Color(4283215696L), composerStartRestartGroup, 3456, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1933183242);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new z0(str, str2, z, aVar, i10, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobileTrackItem$lambda$1$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobileTrackItem$lambda$3(String str, String str2, boolean z, r7.a aVar, int i10, Composer composer, int i11) {
        MobileTrackItem(str, str2, z, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x03a6  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x03b2  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x03f8  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0427  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0437  */
    /* JADX WARN: Removed duplicated region for block: B:239:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x010f  */
    /* JADX INFO: renamed from: PlayerIconButton-xI7PXdU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6262PlayerIconButtonxI7PXdU(final androidx.compose.ui.graphics.vector.ImageVector r39, final java.lang.String r40, final androidx.compose.ui.focus.FocusRequester r41, float r42, float r43, final r7.l<? super java.lang.Boolean, x6.t0> r44, final r7.a<x6.t0> r45, r7.a<x6.t0> r46, r7.a<x6.t0> r47, r7.a<x6.t0> r48, r7.a<x6.t0> r49, androidx.compose.runtime.Composer r50, final int r51, final int r52, final int r53) {
        /*
            Method dump skipped, instruction units count: 1102
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.m6262PlayerIconButtonxI7PXdU(androidx.compose.ui.graphics.vector.ImageVector, java.lang.String, androidx.compose.ui.focus.FocusRequester, float, float, r7.l, r7.a, r7.a, r7.a, r7.a, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerIconButton_xI7PXdU$lambda$11$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerIconButton_xI7PXdU$lambda$12$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(PlayerIconButton_xI7PXdU$lambda$7(state));
        graphicsLayerScope.setScaleY(PlayerIconButton_xI7PXdU$lambda$7(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerIconButton_xI7PXdU$lambda$14(ImageVector imageVector, String str, FocusRequester focusRequester, float f10, float f11, r7.l lVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, int i10, int i11, int i12, Composer composer, int i13) {
        m6262PlayerIconButtonxI7PXdU(imageVector, str, focusRequester, f10, f11, lVar, aVar, aVar2, aVar3, aVar4, aVar5, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return x6.t0.f22605a;
    }

    private static final boolean PlayerIconButton_xI7PXdU$lambda$5(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void PlayerIconButton_xI7PXdU$lambda$6(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final float PlayerIconButton_xI7PXdU$lambda$7(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerIconButton_xI7PXdU$lambda$8$0(r7.l lVar, MutableState mutableState, FocusState focusState) {
        PlayerIconButton_xI7PXdU$lambda$6(mutableState, focusState.isFocused());
        lVar.invoke(Boolean.valueOf(focusState.isFocused()));
        return x6.t0.f22605a;
    }

    private static final void PlayerMetaSeparator(Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1776401489);
        if (i10 == 0 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1776401489, i10, -1, "com.arflix.tv.ui.screens.player.PlayerMetaSeparator (PlayerScreen.kt:6206)");
            }
            TextKt.m6020Text4IGK_g("|", null, Color.m3471copywmQWz5c$default(com.arflix.tv.ui.theme.ColorKt.getTextPrimary(), 0.45f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(12), FontWeight.INSTANCE.getMedium(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composerStartRestartGroup, 6, 3072, 57338);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new com.arflix.tv.j(i10, 4));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerMetaSeparator$lambda$0(int i10, Composer composer, int i11) {
        PlayerMetaSeparator(composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: renamed from: PlayerMetadataChrome-cd68TDI, reason: not valid java name */
    private static final void m6263PlayerMetadataChromecd68TDI(final PlayerUiState playerUiState, final MediaType mediaType, final Integer num, final Integer num2, final boolean z, final long j10, Modifier modifier, Composer composer, final int i10, final int i11) {
        PlayerUiState playerUiState2;
        int i12;
        Modifier modifier2;
        String str;
        float fM5678constructorimpl;
        Composer composer2;
        Modifier.Companion companion;
        Modifier modifier3;
        StreamSource streamSource;
        int i13;
        String str2;
        int i14;
        boolean z5;
        int i15;
        String episodeTitle;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1894604730);
        if ((i10 & 6) == 0) {
            playerUiState2 = playerUiState;
            i12 = (composerStartRestartGroup.changed(playerUiState2) ? 4 : 2) | i10;
        } else {
            playerUiState2 = playerUiState;
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(mediaType.ordinal()) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(num) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changed(num2) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i12 |= composerStartRestartGroup.changed(z) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i12 |= composerStartRestartGroup.changed(j10) ? 131072 : 65536;
        }
        int i16 = i11 & 64;
        if (i16 != 0) {
            i12 |= 1572864;
            modifier2 = modifier;
        } else {
            modifier2 = modifier;
            if ((i10 & 1572864) == 0) {
                i12 |= composerStartRestartGroup.changed(modifier2) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
            }
        }
        if ((i12 & 599187) == 599186 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (i16 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1894604730, i12, -1, "com.arflix.tv.ui.screens.player.PlayerMetadataChrome (PlayerScreen.kt:6078)");
            }
            String title = (mediaType != MediaType.TV || (episodeTitle = playerUiState2.getEpisodeTitle()) == null || kotlin.text.o.h0(episodeTitle)) ? playerUiState2.getTitle() : playerUiState2.getEpisodeTitle();
            String strBuildPlaybackBaseMetaLine = buildPlaybackBaseMetaLine(playerUiState, mediaType, num, num2);
            StreamSource selectedStream = playerUiState2.getSelectedStream();
            String streamSizeInGb = selectedStream != null ? formatStreamSizeInGb(selectedStream) : null;
            boolean z10 = selectedStream != null && (PlaybackQualityBadgesKt.buildPlaybackBadges(selectedStream).isEmpty() ^ true);
            String overview = playerUiState2.getOverview();
            String string = overview != null ? kotlin.text.o.L0(overview).toString() : null;
            if (string == null) {
                string = "";
            }
            String str3 = string;
            float fM5678constructorimpl2 = Dp.m5678constructorimpl(44);
            float fM5678constructorimpl3 = Dp.m5678constructorimpl(230);
            if (!z || kotlin.text.o.h0(str3)) {
                str = title;
                fM5678constructorimpl = z ? Dp.m5678constructorimpl(MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED) : Dp.m5678constructorimpl(86);
            } else {
                str = title;
                fM5678constructorimpl = Dp.m5678constructorimpl(TsExtractor.TS_STREAM_TYPE_DTS);
            }
            Modifier modifierM585widthInVpY3zN4$default = SizeKt.m585widthInVpY3zN4$default(modifier2, 0.0f, Dp.m5678constructorimpl(z ? 620 : 520), 1, null);
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment.Vertical top = companion2.getTop();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            Arrangement arrangement = Arrangement.INSTANCE;
            Modifier modifier4 = modifier2;
            MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(arrangement, top, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM585widthInVpY3zN4$default);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyG, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            Modifier.Companion companion4 = Modifier.INSTANCE;
            float f10 = 2;
            BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(PaddingKt.m533paddingqDBjuR0$default(companion4, 0.0f, Dp.m5678constructorimpl(f10), 0.0f, 0.0f, 13, null), Dp.m5678constructorimpl(f10)), fM5678constructorimpl), Color.m3471copywmQWz5c$default(j10, z ? 0.78f : 0.46f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composerStartRestartGroup, 0);
            androidx.compose.material3.d.w(16, companion4, composerStartRestartGroup, 6);
            Modifier modifierM585widthInVpY3zN4$default2 = SizeKt.m585widthInVpY3zN4$default(companion4, 0.0f, Dp.m5678constructorimpl(z ? 560 : 470), 1, null);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(z ? 5 : 4));
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyJ = androidx.compose.foundation.c.j(companion2, horizontalOrVerticalM441spacedBy0680j_4, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM585widthInVpY3zN4$default2);
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
            r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyJ, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            String logoUrl = playerUiState.getLogoUrl();
            if (logoUrl == null || kotlin.text.o.h0(logoUrl)) {
                composer2 = composerStartRestartGroup;
                companion = companion4;
                modifier3 = null;
                streamSource = selectedStream;
                i13 = 16;
                if (kotlin.text.o.h0(str)) {
                    str2 = str;
                    composer2.startReplaceGroup(-1778582014);
                    composer2.endReplaceGroup();
                } else {
                    composer2.startReplaceGroup(-1778955316);
                    str2 = str;
                    TextKt.m6020Text4IGK_g(str2, null, com.arflix.tv.ui.theme.ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getSectionTitle(), 0L, TextUnitKt.getSp(18), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer2, 0, 3120, 55290);
                    composer2.endReplaceGroup();
                }
            } else {
                composerStartRestartGroup.startReplaceGroup(-1779385162);
                String logoUrl2 = playerUiState.getLogoUrl();
                modifier3 = null;
                String title2 = playerUiState.getTitle();
                Alignment centerStart = companion2.getCenterStart();
                ContentScale fit = ContentScale.INSTANCE.getFit();
                Modifier modifierM564height3ABfNKs = SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion4, fM5678constructorimpl3), fM5678constructorimpl2);
                companion = companion4;
                i13 = 16;
                streamSource = selectedStream;
                SingletonAsyncImageKt.m6053AsyncImage3HmZ8SU(logoUrl2, title2, modifierM564height3ABfNKs, null, null, centerStart, fit, 0.0f, null, 0, composerStartRestartGroup, 1769856, 920);
                composer2 = composerStartRestartGroup;
                composer2.endReplaceGroup();
                str2 = str;
            }
            String logoUrl3 = playerUiState.getLogoUrl();
            if (logoUrl3 == null || kotlin.text.o.h0(logoUrl3) || kotlin.text.o.h0(str2)) {
                composer2.startReplaceGroup(-1778098910);
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(-1778472212);
                TextKt.m6020Text4IGK_g(str2, null, com.arflix.tv.ui.theme.ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getSectionTitle(), 0L, TextUnitKt.getSp(i13), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer2, 0, 3120, 55290);
                composer2.endReplaceGroup();
            }
            if (kotlin.text.o.h0(strBuildPlaybackBaseMetaLine) && !z10 && (streamSizeInGb == null || kotlin.text.o.h0(streamSizeInGb))) {
                composer2.startReplaceGroup(-1776213118);
                composer2.endReplaceGroup();
                i14 = 540;
            } else {
                composer2.startReplaceGroup(-1777929898);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_42 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(8));
                Alignment.Vertical centerVertically = companion2.getCenterVertically();
                Modifier modifierM585widthInVpY3zN4$default3 = SizeKt.m585widthInVpY3zN4$default(companion, 0.0f, Dp.m5678constructorimpl(540), 1, modifier3);
                composer2.startReplaceableGroup(693286680);
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM441spacedBy0680j_42, centerVertically, composer2, 54);
                composer2.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor3 = companion3.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM585widthInVpY3zN4$default3);
                if (composer2.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor3);
                } else {
                    composer2.useNode();
                }
                Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composer2);
                r7.p pVarU3 = a0.c.u(companion3, composerM2991constructorimpl3, measurePolicyRowMeasurePolicy, composerM2991constructorimpl3, currentCompositionLocalMap3);
                if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
                }
                a0.c.y(composer2, qVarModifierMaterializerOf3, composer2, 0, 2058660585);
                if (kotlin.text.o.h0(strBuildPlaybackBaseMetaLine)) {
                    i14 = 540;
                    composer2.startReplaceGroup(-438273333);
                    composer2.endReplaceGroup();
                    z5 = false;
                } else {
                    composer2.startReplaceGroup(-438809571);
                    i14 = 540;
                    TextKt.m6020Text4IGK_g(strBuildPlaybackBaseMetaLine, null, Color.m3471copywmQWz5c$default(com.arflix.tv.ui.theme.ColorKt.getTextPrimary(), 0.78f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(12), FontWeight.INSTANCE.getMedium(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer2, 0, 3120, 55290);
                    composer2.endReplaceGroup();
                    z5 = true;
                }
                if (streamSource == null || !z10) {
                    composer2.startReplaceGroup(-437987637);
                    composer2.endReplaceGroup();
                } else {
                    composer2.startReplaceGroup(-438197135);
                    if (z5) {
                        composer2.startReplaceGroup(540055372);
                        i15 = 0;
                        PlayerMetaSeparator(composer2, 0);
                    } else {
                        i15 = 0;
                        composer2.startReplaceGroup(-438132469);
                    }
                    composer2.endReplaceGroup();
                    PlaybackQualityBadgesKt.PlaybackQualityBadgeRow(streamSource, modifier3, composer2, i15, 2);
                    composer2.endReplaceGroup();
                    z5 = true;
                }
                if (streamSizeInGb == null || kotlin.text.o.h0(streamSizeInGb)) {
                    composer2.startReplaceGroup(-437411285);
                    composer2.endReplaceGroup();
                } else {
                    composer2.startReplaceGroup(-437911966);
                    if (z5) {
                        composer2.startReplaceGroup(540064268);
                        PlayerMetaSeparator(composer2, 0);
                    } else {
                        composer2.startReplaceGroup(-437856693);
                    }
                    composer2.endReplaceGroup();
                    TextKt.m6020Text4IGK_g(streamSizeInGb, null, Color.m3471copywmQWz5c$default(com.arflix.tv.ui.theme.ColorKt.getTextPrimary(), 0.82f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(12), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer2, 0, 3072, 57338);
                    composer2.endReplaceGroup();
                }
                com.arflix.tv.data.repository.g.q(composer2);
            }
            if (!z || kotlin.text.o.h0(str3)) {
                composer2.startReplaceGroup(-1775786558);
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(-1776131991);
                TextKt.m6020Text4IGK_g(str3, SizeKt.m585widthInVpY3zN4$default(companion, 0.0f, Dp.m5678constructorimpl(i14), 1, modifier3), Color.m3471copywmQWz5c$default(com.arflix.tv.ui.theme.ColorKt.getTextPrimary(), 0.76f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 3, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getBody(), 0L, TextUnitKt.getSp(13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composer2, 48, 3120, 55288);
                composer2.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.A(composer2)) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.player.p
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return PlayerScreenKt.PlayerMetadataChrome_cd68TDI$lambda$3(playerUiState, mediaType, num, num2, z, j10, modifier5, i10, i11, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerMetadataChrome_cd68TDI$lambda$3(PlayerUiState playerUiState, MediaType mediaType, Integer num, Integer num2, boolean z, long j10, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        m6263PlayerMetadataChromecd68TDI(playerUiState, mediaType, num, num2, z, j10, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:1001:0x1989  */
    /* JADX WARN: Removed duplicated region for block: B:1004:0x199f  */
    /* JADX WARN: Removed duplicated region for block: B:1009:0x1a1a  */
    /* JADX WARN: Removed duplicated region for block: B:1014:0x1a34  */
    /* JADX WARN: Removed duplicated region for block: B:1019:0x1a53  */
    /* JADX WARN: Removed duplicated region for block: B:1020:0x1a55  */
    /* JADX WARN: Removed duplicated region for block: B:1021:0x1a5b  */
    /* JADX WARN: Removed duplicated region for block: B:1027:0x1a72  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:1032:0x1a91  */
    /* JADX WARN: Removed duplicated region for block: B:1037:0x1aaf  */
    /* JADX WARN: Removed duplicated region for block: B:1038:0x1ab1  */
    /* JADX WARN: Removed duplicated region for block: B:1039:0x1ab7  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:1045:0x1ad5  */
    /* JADX WARN: Removed duplicated region for block: B:1046:0x1ad7  */
    /* JADX WARN: Removed duplicated region for block: B:1047:0x1add  */
    /* JADX WARN: Removed duplicated region for block: B:1051:0x1af5  */
    /* JADX WARN: Removed duplicated region for block: B:1052:0x1b06  */
    /* JADX WARN: Removed duplicated region for block: B:1055:0x1b30  */
    /* JADX WARN: Removed duplicated region for block: B:1058:0x1b39  */
    /* JADX WARN: Removed duplicated region for block: B:1062:0x1b59  */
    /* JADX WARN: Removed duplicated region for block: B:1064:0x1b5f  */
    /* JADX WARN: Removed duplicated region for block: B:1071:0x1b88  */
    /* JADX WARN: Removed duplicated region for block: B:1078:0x1bd4  */
    /* JADX WARN: Removed duplicated region for block: B:1080:0x1c47  */
    /* JADX WARN: Removed duplicated region for block: B:1083:0x1c52  */
    /* JADX WARN: Removed duplicated region for block: B:1088:0x1d1b  */
    /* JADX WARN: Removed duplicated region for block: B:1091:0x1d24  */
    /* JADX WARN: Removed duplicated region for block: B:1095:0x1d4d  */
    /* JADX WARN: Removed duplicated region for block: B:1099:0x1d57  */
    /* JADX WARN: Removed duplicated region for block: B:1106:0x1da2  */
    /* JADX WARN: Removed duplicated region for block: B:1113:0x1ded  */
    /* JADX WARN: Removed duplicated region for block: B:1116:0x1e1d  */
    /* JADX WARN: Removed duplicated region for block: B:1117:0x1e2d  */
    /* JADX WARN: Removed duplicated region for block: B:1124:0x1e68  */
    /* JADX WARN: Removed duplicated region for block: B:1127:0x1eaa  */
    /* JADX WARN: Removed duplicated region for block: B:1128:0x1eac  */
    /* JADX WARN: Removed duplicated region for block: B:1131:0x1eb9  */
    /* JADX WARN: Removed duplicated region for block: B:1132:0x1ebb  */
    /* JADX WARN: Removed duplicated region for block: B:1135:0x1ec8  */
    /* JADX WARN: Removed duplicated region for block: B:1136:0x1eca  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:1143:0x1eef  */
    /* JADX WARN: Removed duplicated region for block: B:1150:0x1f46  */
    /* JADX WARN: Removed duplicated region for block: B:1153:0x1f67  */
    /* JADX WARN: Removed duplicated region for block: B:1154:0x1f75  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:1161:0x1fbe  */
    /* JADX WARN: Removed duplicated region for block: B:1164:0x1ffd  */
    /* JADX WARN: Removed duplicated region for block: B:1165:0x2001  */
    /* JADX WARN: Removed duplicated region for block: B:1170:0x2020  */
    /* JADX WARN: Removed duplicated region for block: B:1175:0x203f  */
    /* JADX WARN: Removed duplicated region for block: B:1182:0x2074  */
    /* JADX WARN: Removed duplicated region for block: B:1189:0x210d  */
    /* JADX WARN: Removed duplicated region for block: B:1192:0x2173  */
    /* JADX WARN: Removed duplicated region for block: B:1195:0x218a  */
    /* JADX WARN: Removed duplicated region for block: B:1202:0x21b7  */
    /* JADX WARN: Removed duplicated region for block: B:1205:0x21d8  */
    /* JADX WARN: Removed duplicated region for block: B:1210:0x221c  */
    /* JADX WARN: Removed duplicated region for block: B:1215:0x2264  */
    /* JADX WARN: Removed duplicated region for block: B:1222:0x22a1  */
    /* JADX WARN: Removed duplicated region for block: B:1229:0x22f4  */
    /* JADX WARN: Removed duplicated region for block: B:1234:0x2319  */
    /* JADX WARN: Removed duplicated region for block: B:1241:0x234c  */
    /* JADX WARN: Removed duplicated region for block: B:1244:0x2381  */
    /* JADX WARN: Removed duplicated region for block: B:1245:0x23a1  */
    /* JADX WARN: Removed duplicated region for block: B:1248:0x23c1  */
    /* JADX WARN: Removed duplicated region for block: B:1249:0x23cf  */
    /* JADX WARN: Removed duplicated region for block: B:1252:0x23eb  */
    /* JADX WARN: Removed duplicated region for block: B:1253:0x23f9  */
    /* JADX WARN: Removed duplicated region for block: B:1256:0x242c  */
    /* JADX WARN: Removed duplicated region for block: B:1257:0x242e  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:1260:0x2434  */
    /* JADX WARN: Removed duplicated region for block: B:1261:0x2436  */
    /* JADX WARN: Removed duplicated region for block: B:1264:0x243e  */
    /* JADX WARN: Removed duplicated region for block: B:1265:0x2440  */
    /* JADX WARN: Removed duplicated region for block: B:1268:0x2468  */
    /* JADX WARN: Removed duplicated region for block: B:1269:0x246a  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:1272:0x247c  */
    /* JADX WARN: Removed duplicated region for block: B:1276:0x248c  */
    /* JADX WARN: Removed duplicated region for block: B:1279:0x2498 A[PHI: r2 r215 r234
      0x2498: PHI (r2v99 int) = (r2v73 int), (r2v100 int) binds: [B:1278:0x2496, B:1275:0x2485] A[DONT_GENERATE, DONT_INLINE]
      0x2498: PHI (r215v4 com.arflix.tv.ui.screens.player.NextEpisodePromptGate) = 
      (r215v2 com.arflix.tv.ui.screens.player.NextEpisodePromptGate)
      (r215v5 com.arflix.tv.ui.screens.player.NextEpisodePromptGate)
     binds: [B:1278:0x2496, B:1275:0x2485] A[DONT_GENERATE, DONT_INLINE]
      0x2498: PHI (r234v31 java.lang.Integer) = (r234v24 java.lang.Integer), (r234v32 java.lang.Integer) binds: [B:1278:0x2496, B:1275:0x2485] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1280:0x249a  */
    /* JADX WARN: Removed duplicated region for block: B:1283:0x24a5  */
    /* JADX WARN: Removed duplicated region for block: B:1286:0x24ae  */
    /* JADX WARN: Removed duplicated region for block: B:1289:0x24b4 A[PHI: r3
      0x24b4: PHI (r3v104 java.lang.Integer) = (r3v97 java.lang.Integer), (r3v105 java.lang.Integer) binds: [B:1288:0x24b2, B:1284:0x24ab] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1290:0x24b6  */
    /* JADX WARN: Removed duplicated region for block: B:1293:0x24bf  */
    /* JADX WARN: Removed duplicated region for block: B:1295:0x24c5  */
    /* JADX WARN: Removed duplicated region for block: B:1304:0x25b4  */
    /* JADX WARN: Removed duplicated region for block: B:1309:0x260b  */
    /* JADX WARN: Removed duplicated region for block: B:1314:0x2633  */
    /* JADX WARN: Removed duplicated region for block: B:1321:0x269b  */
    /* JADX WARN: Removed duplicated region for block: B:1328:0x26dc  */
    /* JADX WARN: Removed duplicated region for block: B:1333:0x270f  */
    /* JADX WARN: Removed duplicated region for block: B:1340:0x2746  */
    /* JADX WARN: Removed duplicated region for block: B:1345:0x277d  */
    /* JADX WARN: Removed duplicated region for block: B:1348:0x2797  */
    /* JADX WARN: Removed duplicated region for block: B:1350:0x279d  */
    /* JADX WARN: Removed duplicated region for block: B:1352:0x27a1  */
    /* JADX WARN: Removed duplicated region for block: B:1353:0x27a3  */
    /* JADX WARN: Removed duplicated region for block: B:1360:0x27b6  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:1373:0x27f0  */
    /* JADX WARN: Removed duplicated region for block: B:1375:0x27f4  */
    /* JADX WARN: Removed duplicated region for block: B:1376:0x27f6  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:1383:0x2806  */
    /* JADX WARN: Removed duplicated region for block: B:1386:0x2826  */
    /* JADX WARN: Removed duplicated region for block: B:1387:0x2829  */
    /* JADX WARN: Removed duplicated region for block: B:1390:0x282f  */
    /* JADX WARN: Removed duplicated region for block: B:1391:0x2832  */
    /* JADX WARN: Removed duplicated region for block: B:1394:0x2863  */
    /* JADX WARN: Removed duplicated region for block: B:1399:0x286e  */
    /* JADX WARN: Removed duplicated region for block: B:1402:0x287b  */
    /* JADX WARN: Removed duplicated region for block: B:1403:0x2887  */
    /* JADX WARN: Removed duplicated region for block: B:1406:0x2935  */
    /* JADX WARN: Removed duplicated region for block: B:1410:0x295e  */
    /* JADX WARN: Removed duplicated region for block: B:1421:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0203  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02bf  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x038d  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0461  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x0499  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0509  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x051b  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0525  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0542  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0544  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x0549  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x056a  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0575  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x05a7  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x05b3  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x05e7  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x05eb  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x05ed  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x060c  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x062b  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x064e  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x067a  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x06a1  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x06cd  */
    /* JADX WARN: Removed duplicated region for block: B:378:0x06e6  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x0706  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x0714  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x0726  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x0731  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0740  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x074b  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x075a  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0765  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0774  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x078e  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x07a3  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x07b8  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x07ce  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x07e5  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x07fc  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x0813  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x0821  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x0830  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x0845  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x085a  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x086f  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x087d  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x088d  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x08a2  */
    /* JADX WARN: Removed duplicated region for block: B:441:0x08b7  */
    /* JADX WARN: Removed duplicated region for block: B:450:0x08e8  */
    /* JADX WARN: Removed duplicated region for block: B:453:0x0902  */
    /* JADX WARN: Removed duplicated region for block: B:456:0x091f  */
    /* JADX WARN: Removed duplicated region for block: B:457:0x0932  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0944  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x095c  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x0973  */
    /* JADX WARN: Removed duplicated region for block: B:467:0x0982  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x0991  */
    /* JADX WARN: Removed duplicated region for block: B:473:0x09aa  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x09bc  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x09ce  */
    /* JADX WARN: Removed duplicated region for block: B:482:0x09e0  */
    /* JADX WARN: Removed duplicated region for block: B:485:0x09f2  */
    /* JADX WARN: Removed duplicated region for block: B:488:0x0a04  */
    /* JADX WARN: Removed duplicated region for block: B:491:0x0a16  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x0a28  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x0a3a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:500:0x0a4c  */
    /* JADX WARN: Removed duplicated region for block: B:503:0x0a5e  */
    /* JADX WARN: Removed duplicated region for block: B:506:0x0a70  */
    /* JADX WARN: Removed duplicated region for block: B:509:0x0a82  */
    /* JADX WARN: Removed duplicated region for block: B:512:0x0a99  */
    /* JADX WARN: Removed duplicated region for block: B:513:0x0aa7  */
    /* JADX WARN: Removed duplicated region for block: B:516:0x0ab9  */
    /* JADX WARN: Removed duplicated region for block: B:517:0x0ac9  */
    /* JADX WARN: Removed duplicated region for block: B:520:0x0ad8  */
    /* JADX WARN: Removed duplicated region for block: B:521:0x0ae8  */
    /* JADX WARN: Removed duplicated region for block: B:524:0x0af9  */
    /* JADX WARN: Removed duplicated region for block: B:527:0x0b0e  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:530:0x0b23  */
    /* JADX WARN: Removed duplicated region for block: B:533:0x0b38  */
    /* JADX WARN: Removed duplicated region for block: B:536:0x0b4d  */
    /* JADX WARN: Removed duplicated region for block: B:539:0x0b62  */
    /* JADX WARN: Removed duplicated region for block: B:542:0x0b90  */
    /* JADX WARN: Removed duplicated region for block: B:543:0x0b92  */
    /* JADX WARN: Removed duplicated region for block: B:546:0x0b9b  */
    /* JADX WARN: Removed duplicated region for block: B:547:0x0b9d  */
    /* JADX WARN: Removed duplicated region for block: B:550:0x0baa  */
    /* JADX WARN: Removed duplicated region for block: B:551:0x0bac  */
    /* JADX WARN: Removed duplicated region for block: B:558:0x0bc8  */
    /* JADX WARN: Removed duplicated region for block: B:561:0x0bd0  */
    /* JADX WARN: Removed duplicated region for block: B:564:0x0bde  */
    /* JADX WARN: Removed duplicated region for block: B:566:0x0be4  */
    /* JADX WARN: Removed duplicated region for block: B:571:0x0bf0  */
    /* JADX WARN: Removed duplicated region for block: B:574:0x0bfd  */
    /* JADX WARN: Removed duplicated region for block: B:575:0x0bff  */
    /* JADX WARN: Removed duplicated region for block: B:578:0x0c0e  */
    /* JADX WARN: Removed duplicated region for block: B:579:0x0c10  */
    /* JADX WARN: Removed duplicated region for block: B:582:0x0c23  */
    /* JADX WARN: Removed duplicated region for block: B:583:0x0c25  */
    /* JADX WARN: Removed duplicated region for block: B:586:0x0c32  */
    /* JADX WARN: Removed duplicated region for block: B:590:0x0c48  */
    /* JADX WARN: Removed duplicated region for block: B:593:0x0c87  */
    /* JADX WARN: Removed duplicated region for block: B:596:0x0c9b  */
    /* JADX WARN: Removed duplicated region for block: B:599:0x0cb9  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:600:0x0cbb  */
    /* JADX WARN: Removed duplicated region for block: B:607:0x0cd7  */
    /* JADX WARN: Removed duplicated region for block: B:614:0x0d1d  */
    /* JADX WARN: Removed duplicated region for block: B:617:0x0d4a  */
    /* JADX WARN: Removed duplicated region for block: B:618:0x0d4d  */
    /* JADX WARN: Removed duplicated region for block: B:623:0x0d5d  */
    /* JADX WARN: Removed duplicated region for block: B:626:0x0d75  */
    /* JADX WARN: Removed duplicated region for block: B:627:0x0d7f  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:630:0x0d90  */
    /* JADX WARN: Removed duplicated region for block: B:633:0x0da6  */
    /* JADX WARN: Removed duplicated region for block: B:636:0x0dbc  */
    /* JADX WARN: Removed duplicated region for block: B:639:0x0dd2  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:642:0x0de8  */
    /* JADX WARN: Removed duplicated region for block: B:645:0x0dfe  */
    /* JADX WARN: Removed duplicated region for block: B:646:0x0e0d  */
    /* JADX WARN: Removed duplicated region for block: B:649:0x0e1c  */
    /* JADX WARN: Removed duplicated region for block: B:652:0x0e33  */
    /* JADX WARN: Removed duplicated region for block: B:655:0x0e49  */
    /* JADX WARN: Removed duplicated region for block: B:658:0x0e61  */
    /* JADX WARN: Removed duplicated region for block: B:669:0x0e8d  */
    /* JADX WARN: Removed duplicated region for block: B:677:0x0eb1  */
    /* JADX WARN: Removed duplicated region for block: B:678:0x0ec0  */
    /* JADX WARN: Removed duplicated region for block: B:685:0x0f31  */
    /* JADX WARN: Removed duplicated region for block: B:689:0x0f43  */
    /* JADX WARN: Removed duplicated region for block: B:693:0x0f7e  */
    /* JADX WARN: Removed duplicated region for block: B:701:0x0fb4  */
    /* JADX WARN: Removed duplicated region for block: B:714:0x102b A[LOOP:2: B:712:0x1025->B:714:0x102b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:717:0x1075  */
    /* JADX WARN: Removed duplicated region for block: B:723:0x1090  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:736:0x10d3  */
    /* JADX WARN: Removed duplicated region for block: B:739:0x10ea  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:742:0x1101  */
    /* JADX WARN: Removed duplicated region for block: B:745:0x1118  */
    /* JADX WARN: Removed duplicated region for block: B:748:0x112d  */
    /* JADX WARN: Removed duplicated region for block: B:749:0x1137  */
    /* JADX WARN: Removed duplicated region for block: B:752:0x1145  */
    /* JADX WARN: Removed duplicated region for block: B:757:0x1175  */
    /* JADX WARN: Removed duplicated region for block: B:760:0x119f  */
    /* JADX WARN: Removed duplicated region for block: B:761:0x11ab  */
    /* JADX WARN: Removed duplicated region for block: B:764:0x11bf  */
    /* JADX WARN: Removed duplicated region for block: B:767:0x11d5  */
    /* JADX WARN: Removed duplicated region for block: B:770:0x11e9  */
    /* JADX WARN: Removed duplicated region for block: B:773:0x1206  */
    /* JADX WARN: Removed duplicated region for block: B:774:0x1210  */
    /* JADX WARN: Removed duplicated region for block: B:777:0x1221  */
    /* JADX WARN: Removed duplicated region for block: B:778:0x122d  */
    /* JADX WARN: Removed duplicated region for block: B:781:0x123d  */
    /* JADX WARN: Removed duplicated region for block: B:782:0x1248  */
    /* JADX WARN: Removed duplicated region for block: B:785:0x1257  */
    /* JADX WARN: Removed duplicated region for block: B:788:0x126f  */
    /* JADX WARN: Removed duplicated region for block: B:791:0x1286  */
    /* JADX WARN: Removed duplicated region for block: B:792:0x1292  */
    /* JADX WARN: Removed duplicated region for block: B:795:0x12a4  */
    /* JADX WARN: Removed duplicated region for block: B:798:0x12b9  */
    /* JADX WARN: Removed duplicated region for block: B:801:0x12d0  */
    /* JADX WARN: Removed duplicated region for block: B:804:0x12e5  */
    /* JADX WARN: Removed duplicated region for block: B:807:0x12fc  */
    /* JADX WARN: Removed duplicated region for block: B:808:0x1306  */
    /* JADX WARN: Removed duplicated region for block: B:811:0x1316  */
    /* JADX WARN: Removed duplicated region for block: B:814:0x132b  */
    /* JADX WARN: Removed duplicated region for block: B:817:0x1340  */
    /* JADX WARN: Removed duplicated region for block: B:818:0x134a  */
    /* JADX WARN: Removed duplicated region for block: B:821:0x135a  */
    /* JADX WARN: Removed duplicated region for block: B:824:0x136f  */
    /* JADX WARN: Removed duplicated region for block: B:827:0x1386  */
    /* JADX WARN: Removed duplicated region for block: B:828:0x1390  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:831:0x13a0  */
    /* JADX WARN: Removed duplicated region for block: B:834:0x13b5  */
    /* JADX WARN: Removed duplicated region for block: B:835:0x13c1  */
    /* JADX WARN: Removed duplicated region for block: B:838:0x13d1  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:841:0x13e8  */
    /* JADX WARN: Removed duplicated region for block: B:844:0x13fd  */
    /* JADX WARN: Removed duplicated region for block: B:847:0x1412  */
    /* JADX WARN: Removed duplicated region for block: B:848:0x1420  */
    /* JADX WARN: Removed duplicated region for block: B:851:0x1432  */
    /* JADX WARN: Removed duplicated region for block: B:858:0x14ba  */
    /* JADX WARN: Removed duplicated region for block: B:865:0x150a  */
    /* JADX WARN: Removed duplicated region for block: B:872:0x1589  */
    /* JADX WARN: Removed duplicated region for block: B:875:0x15fe  */
    /* JADX WARN: Removed duplicated region for block: B:876:0x1600  */
    /* JADX WARN: Removed duplicated region for block: B:879:0x1606  */
    /* JADX WARN: Removed duplicated region for block: B:880:0x1608  */
    /* JADX WARN: Removed duplicated region for block: B:887:0x161c  */
    /* JADX WARN: Removed duplicated region for block: B:890:0x1626  */
    /* JADX WARN: Removed duplicated region for block: B:893:0x1631  */
    /* JADX WARN: Removed duplicated region for block: B:897:0x163f  */
    /* JADX WARN: Removed duplicated region for block: B:900:0x1649 A[PHI: r231 r235
      0x1649: PHI (r231v41 com.arflix.tv.util.DeviceType) = (r231v16 com.arflix.tv.util.DeviceType), (r231v42 com.arflix.tv.util.DeviceType) binds: [B:899:0x1647, B:896:0x163c] A[DONT_GENERATE, DONT_INLINE]
      0x1649: PHI (r235v34 java.lang.Integer) = (r235v12 java.lang.Integer), (r235v35 java.lang.Integer) binds: [B:899:0x1647, B:896:0x163c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:901:0x164b  */
    /* JADX WARN: Removed duplicated region for block: B:904:0x1656  */
    /* JADX WARN: Removed duplicated region for block: B:905:0x1658  */
    /* JADX WARN: Removed duplicated region for block: B:908:0x1665  */
    /* JADX WARN: Removed duplicated region for block: B:909:0x1667  */
    /* JADX WARN: Removed duplicated region for block: B:912:0x1674  */
    /* JADX WARN: Removed duplicated region for block: B:913:0x1676  */
    /* JADX WARN: Removed duplicated region for block: B:916:0x1681  */
    /* JADX WARN: Removed duplicated region for block: B:917:0x1683  */
    /* JADX WARN: Removed duplicated region for block: B:920:0x168f  */
    /* JADX WARN: Removed duplicated region for block: B:921:0x1691  */
    /* JADX WARN: Removed duplicated region for block: B:924:0x169d  */
    /* JADX WARN: Removed duplicated region for block: B:925:0x169f  */
    /* JADX WARN: Removed duplicated region for block: B:928:0x16a9  */
    /* JADX WARN: Removed duplicated region for block: B:929:0x16ab  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:932:0x16b6  */
    /* JADX WARN: Removed duplicated region for block: B:933:0x16b8  */
    /* JADX WARN: Removed duplicated region for block: B:936:0x16c7  */
    /* JADX WARN: Removed duplicated region for block: B:937:0x16c9  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:940:0x16d4  */
    /* JADX WARN: Removed duplicated region for block: B:941:0x16d6  */
    /* JADX WARN: Removed duplicated region for block: B:944:0x16df  */
    /* JADX WARN: Removed duplicated region for block: B:945:0x16e1  */
    /* JADX WARN: Removed duplicated region for block: B:948:0x16e9  */
    /* JADX WARN: Removed duplicated region for block: B:952:0x1733  */
    /* JADX WARN: Removed duplicated region for block: B:955:0x17a7  */
    /* JADX WARN: Removed duplicated region for block: B:960:0x1806  */
    /* JADX WARN: Removed duplicated region for block: B:963:0x1840  */
    /* JADX WARN: Removed duplicated region for block: B:966:0x187e  */
    /* JADX WARN: Removed duplicated region for block: B:969:0x1894  */
    /* JADX WARN: Removed duplicated region for block: B:974:0x18bb  */
    /* JADX WARN: Removed duplicated region for block: B:979:0x18e3  */
    /* JADX WARN: Removed duplicated region for block: B:984:0x1900  */
    /* JADX WARN: Removed duplicated region for block: B:989:0x1924  */
    /* JADX WARN: Removed duplicated region for block: B:992:0x1945  */
    /* JADX WARN: Removed duplicated region for block: B:995:0x195d  */
    /* JADX WARN: Removed duplicated region for block: B:998:0x1973  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void PlayerScreen(final com.arflix.tv.data.model.MediaType r228, final int r229, java.lang.Integer r230, java.lang.Integer r231, java.lang.Integer r232, java.lang.Integer r233, java.lang.Integer r234, java.lang.Integer r235, java.lang.String r236, java.lang.String r237, java.lang.String r238, java.lang.String r239, java.lang.String r240, java.lang.Long r241, boolean r242, com.arflix.tv.ui.screens.player.PlayerViewModel r243, r7.a<x6.t0> r244, r7.r<? super com.arflix.tv.data.model.EpisodeIdentity, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, x6.t0> r245, androidx.compose.runtime.Composer r246, final int r247, final int r248, final int r249) throws java.lang.IllegalAccessException, java.lang.NoSuchMethodException, java.lang.InstantiationException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 10632
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.PlayerScreen(com.arflix.tv.data.model.MediaType, int, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, boolean, com.arflix.tv.ui.screens.player.PlayerViewModel, r7.a, r7.r, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$adjustVolume(int i10, AudioManager audioManager, MutableIntState mutableIntState, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, int i11) {
        int iN = qb.d.n(mutableIntState.getIntValue() + i11, 0, i10);
        audioManager.setStreamVolume(3, iN, 0);
        mutableIntState.setIntValue(iN);
        PlayerScreen$lambda$89(mutableState, iN == 0);
        PlayerScreen$lambda$80(mutableState2, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PictureInPictureParams PlayerScreen$buildPipParams(Context context, VectorPainter vectorPainter, VectorPainter vectorPainter2, VectorPainter vectorPainter3, VectorPainter vectorPainter4, MutableState<Boolean> mutableState, Density density) {
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        String string = context.getString(R.string.player_cd_rewind);
        String string2 = context.getString(R.string.player_cd_forward);
        String string3 = context.getString(PlayerScreen$lambda$20(mutableState) ? R.string.player_cd_pause : R.string.play);
        RemoteAction[] remoteActionArr = new RemoteAction[3];
        remoteActionArr[0] = new RemoteAction(PlayerScreen$vectorToDrawableIcon(context, density, vectorPainter), string, string, PlayerScreen$buildPipParams$lambda$281(context, PIP_ACTION_REWIND, 10));
        if (!PlayerScreen$lambda$20(mutableState)) {
            vectorPainter2 = vectorPainter3;
        }
        remoteActionArr[1] = new RemoteAction(PlayerScreen$vectorToDrawableIcon(context, density, vectorPainter2), string3, string3, PlayerScreen$buildPipParams$lambda$281(context, PIP_ACTION_PLAY_PAUSE, 11));
        remoteActionArr[2] = new RemoteAction(PlayerScreen$vectorToDrawableIcon(context, density, vectorPainter4), string2, string2, PlayerScreen$buildPipParams$lambda$281(context, PIP_ACTION_FORWARD, 12));
        return new PictureInPictureParams.Builder().setAspectRatio(new Rational(16, 9)).setActions(t7.a.E(remoteActionArr)).build();
    }

    private static final PendingIntent PlayerScreen$buildPipParams$lambda$281(Context context, String str, int i10) {
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        return PendingIntent.getBroadcast(context, i10, intent, 201326592);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$dvStripEnabledNow(Set<String> set, State<PlayerUiState> state) {
        if (PlayerScreen$lambda$4(state).getDolbyVisionCompatEnabled()) {
            return true;
        }
        String selectedStreamUrl = PlayerScreen$lambda$4(state).getSelectedStreamUrl();
        if (selectedStreamUrl == null) {
            selectedStreamUrl = "";
        }
        return set.contains(selectedStreamUrl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$1$0(EpisodeIdentity episodeIdentity, String str, String str2, String str3) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$109(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$110(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$112(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$113(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$115(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$116(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final EpisodeIdentity PlayerScreen$lambda$118(MutableState<EpisodeIdentity> mutableState) {
        return mutableState.getValue();
    }

    private static final String PlayerScreen$lambda$121(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String PlayerScreen$lambda$124(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String PlayerScreen$lambda$127(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EpisodeIdentity PlayerScreen$lambda$130(MutableState<EpisodeIdentity> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EpisodeIdentity PlayerScreen$lambda$133(MutableState<EpisodeIdentity> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$140$0(ka.k0 k0Var, MutableState mutableState, MutableLongState mutableLongState, MutableLongState mutableLongState2, MutableIntState mutableIntState, r7.r rVar, PlayerViewModel playerViewModel, EpisodeIdentity episodeIdentity, String str, String str2, String str3) {
        if (!PlayerScreen$lambda$47(mutableState)) {
            PlayerScreen$lambda$48(mutableState, true);
            long longValue = mutableLongState.getLongValue();
            long longValue2 = mutableLongState2.getLongValue();
            ka.m0.p(k0Var, null, 0, new PlayerScreenKt$PlayerScreen$playNextEpisode$1$1$1(rVar, episodeIdentity, str, str2, str3, playerViewModel, longValue, longValue2, longValue2 > 0 ? qb.d.n((int) ((longValue / longValue2) * 100.0d), 0, 100) : 0, mutableIntState.getIntValue(), null), 3);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$141$0(r7.r rVar, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5) {
        PlayerScreen$lambda$116(mutableState, false);
        EpisodeIdentity episodeIdentityPlayerScreen$lambda$118 = PlayerScreen$lambda$118(mutableState2);
        x6.t0 t0Var = x6.t0.f22605a;
        if (episodeIdentityPlayerScreen$lambda$118 == null) {
            return t0Var;
        }
        rVar.invoke(episodeIdentityPlayerScreen$lambda$118, PlayerScreen$lambda$121(mutableState3), PlayerScreen$lambda$124(mutableState4), PlayerScreen$lambda$127(mutableState5));
        return t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$142$0(r7.a aVar, MutableState mutableState) {
        PlayerScreen$lambda$116(mutableState, false);
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult PlayerScreen$lambda$15$0(final Activity activity, DisposableEffectScope disposableEffectScope) {
        final Integer numValueOf = activity != null ? Integer.valueOf(activity.getRequestedOrientation()) : null;
        if (activity != null) {
            activity.setRequestedOrientation(6);
        }
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$lambda$15$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                Integer num = numValueOf;
                if (num != null) {
                    activity.setRequestedOrientation(num.intValue());
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult PlayerScreen$lambda$16$0(Activity activity, final DeviceType deviceType, DisposableEffectScope disposableEffectScope) {
        final Window window = activity != null ? activity.getWindow() : null;
        if (window != null && deviceType != DeviceType.TV) {
            androidx.core.view.x0 x0Var = new androidx.core.view.x0(window.getDecorView());
            int i10 = Build.VERSION.SDK_INT;
            k2.c t3Var = i10 >= 30 ? new t3(window, x0Var) : i10 >= 26 ? new s3(window, x0Var) : new r3(window, x0Var);
            t3Var.D();
            t3Var.u();
        }
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$lambda$16$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                Window window2 = window;
                if (window2 == null || deviceType == DeviceType.TV) {
                    return;
                }
                androidx.core.view.x0 x0Var2 = new androidx.core.view.x0(window2.getDecorView());
                int i11 = Build.VERSION.SDK_INT;
                (i11 >= 30 ? new t3(window2, x0Var2) : i11 >= 26 ? new s3(window2, x0Var2) : new r3(window2, x0Var2)).E();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$162(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$163(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult PlayerScreen$lambda$17$0(CastManager castManager, DeviceType deviceType, DisposableEffectScope disposableEffectScope) {
        castManager.initialize(deviceType.isTouchDevice());
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$lambda$17$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$177(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$178(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Comparable PlayerScreen$lambda$179$2(String str, String str2, Map.Entry entry) {
        String str3 = (String) entry.getKey();
        if (kotlin.text.u.L(str3, str, true)) {
            return 0;
        }
        return kotlin.text.u.L(str3, str2, true) ? 1 : 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Comparable PlayerScreen$lambda$179$3(Map.Entry entry) {
        return (String) entry.getKey();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult PlayerScreen$lambda$18$0(DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$lambda$18$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                DiscordRpcManager.INSTANCE.disconnect();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<AudioTrackInfo> PlayerScreen$lambda$181(MutableState<List<AudioTrackInfo>> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$187(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$188(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long PlayerScreen$lambda$193(MutableState<Long> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$196(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$197(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$20(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long PlayerScreen$lambda$200(MutableState<Long> mutableState) {
        return mutableState.getValue();
    }

    private static final String PlayerScreen$lambda$203(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String PlayerScreen$lambda$206(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$21(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$213(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$214(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$216(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$217(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$219(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$220(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$225(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$226(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String PlayerScreen$lambda$228(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$23(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$231(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$232(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String PlayerScreen$lambda$234(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$237(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$238(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$24(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long PlayerScreen$lambda$249(MutableState<Long> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long PlayerScreen$lambda$252(MutableState<Long> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$255(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$256(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$26(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set<Integer> PlayerScreen$lambda$264(MutableState<Set<Integer>> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$267(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$268(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$27(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$274(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$275(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$277(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void PlayerScreen$lambda$278(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$282$0(Context context, VectorPainter vectorPainter, VectorPainter vectorPainter2, VectorPainter vectorPainter3, VectorPainter vectorPainter4, MutableState mutableState, Density density, Activity activity) {
        PictureInPictureParams pictureInPictureParamsPlayerScreen$buildPipParams;
        if (Build.VERSION.SDK_INT >= 26 && (pictureInPictureParamsPlayerScreen$buildPipParams = PlayerScreen$buildPipParams(context, vectorPainter, vectorPainter2, vectorPainter3, vectorPainter4, mutableState, density)) != null && activity != null) {
            activity.enterPictureInPictureMode(pictureInPictureParamsPlayerScreen$buildPipParams);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult PlayerScreen$lambda$283$0(DeviceType deviceType, final androidx.lifecycle.y yVar, final Activity activity, final MutableState mutableState, final MutableState mutableState2, DisposableEffectScope disposableEffectScope) {
        if (Build.VERSION.SDK_INT < 26 || !deviceType.isTouchDevice()) {
            return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$lambda$283$0$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                }
            };
        }
        final androidx.lifecycle.w wVar = new androidx.lifecycle.w() { // from class: com.arflix.tv.ui.screens.player.t0
            @Override // androidx.lifecycle.w
            public final void onStateChanged(androidx.lifecycle.y yVar2, androidx.lifecycle.p pVar) {
                PlayerScreenKt.PlayerScreen$lambda$283$0$1(activity, mutableState, mutableState2, yVar2, pVar);
            }
        };
        yVar.getLifecycle().addObserver(wVar);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$lambda$283$0$$inlined$onDispose$2
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                yVar.getLifecycle().removeObserver(wVar);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$283$0$1(Activity activity, MutableState mutableState, MutableState mutableState2, androidx.lifecycle.y yVar, androidx.lifecycle.p pVar) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[pVar.ordinal()];
        if (i10 != 1) {
            if (i10 != 2) {
                return;
            }
            PlayerScreen$lambda$278(mutableState, false);
        } else {
            if (activity == null || !activity.isInPictureInPictureMode()) {
                return;
            }
            PlayerScreen$lambda$278(mutableState, true);
            PlayerScreen$lambda$33(mutableState2, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$29(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlayerUiState PlayerScreen$lambda$3(State<PlayerUiState> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$30(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult PlayerScreen$lambda$315$0(final AiSubtitleRenderersFactory aiSubtitleRenderersFactory, final PlayerViewModel playerViewModel, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$lambda$315$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                AudioCaptureProcessor audioCaptureProcessor = aiSubtitleRenderersFactory.getAudioCaptureProcessor();
                if (audioCaptureProcessor != null) {
                    audioCaptureProcessor.setOnChunk(null);
                }
                playerViewModel.setBufferedReferenceIntervalsProvider(null);
                playerViewModel.setBufferedCueTextsProvider(null);
                playerViewModel.getGeminiLiveService().disconnect();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult PlayerScreen$lambda$317$0(final androidx.lifecycle.y yVar, final Activity activity, final ExoPlayer exoPlayer, DisposableEffectScope disposableEffectScope) {
        final androidx.lifecycle.w wVar = new androidx.lifecycle.w() { // from class: com.arflix.tv.ui.screens.player.n
            @Override // androidx.lifecycle.w
            public final void onStateChanged(androidx.lifecycle.y yVar2, androidx.lifecycle.p pVar) {
                PlayerScreenKt.PlayerScreen$lambda$317$0$0(activity, exoPlayer, yVar2, pVar);
            }
        };
        yVar.getLifecycle().addObserver(wVar);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$lambda$317$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                yVar.getLifecycle().removeObserver(wVar);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$317$0$0(Activity activity, ExoPlayer exoPlayer, androidx.lifecycle.y yVar, androidx.lifecycle.p pVar) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[pVar.ordinal()];
        if (i10 != 1) {
            if (i10 == 3 && exoPlayer.isPlaying()) {
                exoPlayer.pause();
                return;
            }
            return;
        }
        if ((Build.VERSION.SDK_INT < 26 || activity == null || !activity.isInPictureInPictureMode()) && exoPlayer.isPlaying()) {
            exoPlayer.pause();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.content.BroadcastReceiver, com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$17$1$receiver$1] */
    public static final DisposableEffectResult PlayerScreen$lambda$318$0(DeviceType deviceType, final Context context, final ExoPlayer exoPlayer, final MutableState mutableState, DisposableEffectScope disposableEffectScope) {
        if (Build.VERSION.SDK_INT < 26 || !deviceType.isTouchDevice()) {
            return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$lambda$318$0$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                }
            };
        }
        final ?? r1 = new BroadcastReceiver() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$17$1$receiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context ctx, Intent intent) {
                String action;
                if (PlayerScreenKt.PlayerScreen$lambda$274(mutableState) || (action = intent.getAction()) == null) {
                    return;
                }
                int iHashCode = action.hashCode();
                if (iHashCode == -1403205021) {
                    if (action.equals("com.arflix.tv.pip.REWIND")) {
                        ExoPlayer exoPlayer2 = exoPlayer;
                        long currentPosition = exoPlayer2.getCurrentPosition() - 10000;
                        exoPlayer2.seekTo(currentPosition >= 0 ? currentPosition : 0L);
                        return;
                    }
                    return;
                }
                if (iHashCode == -428133709) {
                    if (action.equals("com.arflix.tv.pip.PLAY_PAUSE")) {
                        if (exoPlayer.isPlaying()) {
                            exoPlayer.pause();
                            return;
                        } else {
                            exoPlayer.play();
                            return;
                        }
                    }
                    return;
                }
                if (iHashCode == 1967254013 && action.equals("com.arflix.tv.pip.FORWARD")) {
                    long duration = exoPlayer.getDuration();
                    ExoPlayer exoPlayer3 = exoPlayer;
                    long currentPosition2 = exoPlayer3.getCurrentPosition() + 10000;
                    if (duration <= 0) {
                        duration = Long.MAX_VALUE;
                    }
                    if (currentPosition2 > duration) {
                        currentPosition2 = duration;
                    }
                    exoPlayer3.seekTo(currentPosition2);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(PIP_ACTION_REWIND);
        intentFilter.addAction(PIP_ACTION_PLAY_PAUSE);
        intentFilter.addAction(PIP_ACTION_FORWARD);
        qb.d.N(context, r1, intentFilter, 4);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$lambda$318$0$$inlined$onDispose$2
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                context.unregisterReceiver(r1);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$319$0(boolean z, CastManager castManager, ExoPlayer exoPlayer, ka.k0 k0Var, MutableState mutableState, MutableState mutableState2, MutableLongState mutableLongState, MutableLongState mutableLongState2, MutableState mutableState3, long j10) {
        long currentPosition;
        x6.t0 t0Var = x6.t0.f22605a;
        if (z) {
            if (j10 > 0) {
                castManager.skipForward(j10);
                return t0Var;
            }
            castManager.skipBack(-j10);
            return t0Var;
        }
        if (PlayerScreen$lambda$274(mutableState)) {
            return t0Var;
        }
        if (PlayerScreen$lambda$65(mutableState2)) {
            currentPosition = mutableLongState.getLongValue();
        } else {
            currentPosition = exoPlayer.getCurrentPosition();
            if (currentPosition < 0) {
                currentPosition = 0;
            }
        }
        long j11 = currentPosition + j10;
        if (j11 < 0) {
            j11 = 0;
        }
        if (mutableLongState2.getLongValue() > 0) {
            long longValue = mutableLongState2.getLongValue();
            if (j11 > longValue) {
                j11 = longValue;
            }
        }
        mutableLongState.setLongValue(j11);
        PlayerScreen$lambda$66(mutableState2, true);
        ka.v1 v1VarPlayerScreen$lambda$71 = PlayerScreen$lambda$71(mutableState3);
        if (v1VarPlayerScreen$lambda$71 != null) {
            v1VarPlayerScreen$lambda$71.cancel((CancellationException) null);
        }
        mutableState3.setValue(ka.m0.p(k0Var, null, 0, new PlayerScreenKt$PlayerScreen$queueControlsSeek$1$1$1(exoPlayer, mutableState, mutableLongState, mutableState2, null), 3));
        return t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$32(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$320$0(boolean z, CastManager castManager, ExoPlayer exoPlayer, MutableLongState mutableLongState, MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
        x6.t0 t0Var = x6.t0.f22605a;
        if (z) {
            castManager.seekTo(mutableLongState.getLongValue());
            PlayerScreen$lambda$66(mutableState, false);
            return t0Var;
        }
        if (!PlayerScreen$lambda$274(mutableState2) && PlayerScreen$lambda$65(mutableState)) {
            ka.v1 v1VarPlayerScreen$lambda$71 = PlayerScreen$lambda$71(mutableState3);
            if (v1VarPlayerScreen$lambda$71 != null) {
                v1VarPlayerScreen$lambda$71.cancel((CancellationException) null);
            }
            exoPlayer.seekTo(mutableLongState.getLongValue());
            PlayerScreen$lambda$66(mutableState, false);
        }
        return t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long PlayerScreen$lambda$322(MutableState<Long> mutableState) {
        return mutableState.getValue().longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$323(MutableState<Long> mutableState, long j10) {
        mutableState.setValue(Long.valueOf(j10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$33(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult PlayerScreen$lambda$330$0(final Activity activity, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$lambda$330$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                Activity activity2 = activity;
                if (activity2 != null) {
                    FrameRateUtils.INSTANCE.restoreOriginalMode(activity2);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$335(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult PlayerScreen$lambda$351$0(final AtomicBoolean atomicBoolean, final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final ExoPlayer exoPlayer, final PlayerViewModel playerViewModel, final MutableState mutableState4, final MutableIntState mutableIntState, final MutableIntState mutableIntState2, final AudioManager audioManager, final DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$lambda$351$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                ka.v1 v1VarPlayerScreen$lambda$71 = PlayerScreenKt.PlayerScreen$lambda$71(mutableState);
                Long l10 = null;
                if (v1VarPlayerScreen$lambda$71 != null) {
                    v1VarPlayerScreen$lambda$71.cancel((CancellationException) null);
                }
                atomicBoolean.set(true);
                PlayerScreenKt.PlayerScreen$lambda$275(mutableState2, true);
                if (!PlayerScreenKt.PlayerScreen$lambda$47(mutableState3)) {
                    try {
                        long duration = exoPlayer.getDuration();
                        Long lValueOf = Long.valueOf(duration);
                        if (duration > 0 && duration != C.TIME_UNSET) {
                            l10 = lValueOf;
                        }
                        long jLongValue = l10 != null ? l10.longValue() : 0L;
                        playerViewModel.saveProgress(exoPlayer.getCurrentPosition(), jLongValue, jLongValue > 0 ? qb.d.n((int) ((exoPlayer.getCurrentPosition() / jLongValue) * 100.0d), 0, 100) : 0, exoPlayer.isPlaying(), exoPlayer.getPlaybackState());
                    } catch (Throwable unused) {
                    }
                }
                try {
                    exoPlayer.release();
                } catch (Throwable unused2) {
                }
                if (PlayerScreenKt.PlayerScreen$lambda$88(mutableState4) || mutableIntState.getIntValue() == 0) {
                    int intValue = mutableIntState2.getIntValue();
                    try {
                        audioManager.setStreamVolume(3, intValue >= 1 ? intValue : 1, 0);
                    } catch (Throwable unused3) {
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult PlayerScreen$lambda$352$0(ExoPlayer exoPlayer, State state, final DisposableEffectScope disposableEffectScope) {
        int audioSessionId = exoPlayer.getAudioSessionId();
        int volumeBoostDb = PlayerScreen$lambda$3(state).getVolumeBoostDb();
        final LoudnessEnhancer loudnessEnhancer = null;
        if (volumeBoostDb > 0 && audioSessionId != 0) {
            try {
                LoudnessEnhancer loudnessEnhancer2 = new LoudnessEnhancer(audioSessionId);
                loudnessEnhancer2.setTargetGain(volumeBoostDb * 100);
                loudnessEnhancer2.setEnabled(true);
                loudnessEnhancer = loudnessEnhancer2;
            } catch (Throwable th) {
                androidx.compose.material3.d.y("LoudnessEnhancer unavailable on this device: ", th.getMessage(), "PlayerScreen");
            }
        }
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$lambda$352$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                try {
                    LoudnessEnhancer loudnessEnhancer3 = loudnessEnhancer;
                    if (loudnessEnhancer3 != null) {
                        loudnessEnhancer3.setEnabled(false);
                    }
                    LoudnessEnhancer loudnessEnhancer4 = loudnessEnhancer;
                    if (loudnessEnhancer4 != null) {
                        loudnessEnhancer4.release();
                    }
                } catch (Throwable unused) {
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$355$0(ka.k0 k0Var, MutableState mutableState, MutableState mutableState2, FocusRequester focusRequester) {
        PlayerScreen$lambda$110(mutableState, false);
        PlayerScreen$lambda$33(mutableState2, true);
        ka.m0.p(k0Var, null, 0, new PlayerScreenKt$PlayerScreen$43$1$1(focusRequester, null), 3);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$356$0(ka.k0 k0Var, MutableState mutableState, MutableState mutableState2, FocusRequester focusRequester) {
        PlayerScreen$lambda$113(mutableState, false);
        PlayerScreen$lambda$33(mutableState2, true);
        ka.m0.p(k0Var, null, 0, new PlayerScreenKt$PlayerScreen$44$1$1(focusRequester, null), 3);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$357$0(ka.k0 k0Var, MutableState mutableState, MutableState mutableState2, FocusRequester focusRequester) {
        PlayerScreen$lambda$163(mutableState, false);
        PlayerScreen$lambda$33(mutableState2, true);
        ka.m0.p(k0Var, null, 0, new PlayerScreenKt$PlayerScreen$45$1$1(focusRequester, null), 3);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$358$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$359$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$360$0(r7.a aVar, MutableState mutableState) {
        if (PlayerScreen$lambda$32(mutableState)) {
            PlayerScreen$lambda$33(mutableState, false);
        } else {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$361$0(MutableIntState mutableIntState, MutableIntState mutableIntState2) {
        int intValue = mutableIntState.getIntValue();
        mutableIntState.setIntValue(intValue != 0 ? intValue != 4 ? 0 : 3 : 4);
        mutableIntState2.setIntValue(mutableIntState2.getIntValue() + 1);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v37 */
    /* JADX WARN: Type inference failed for: r0v38, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v92 */
    /* JADX WARN: Type inference failed for: r12v11, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v35 */
    /* JADX WARN: Type inference failed for: r12v37 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20, types: [java.lang.Float] */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r4v14, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v17, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v93 */
    /* JADX WARN: Type inference failed for: r4v94 */
    public static final x6.t0 PlayerScreen$lambda$362(final FocusRequester focusRequester, final boolean z, final boolean z5, final State state, final r7.l lVar, final ExoPlayer exoPlayer, final r7.a aVar, final MediaType mediaType, final r7.r rVar, final r7.r rVar2, final r7.a aVar2, final r7.a aVar3, final PlayerViewModel playerViewModel, final ka.k0 k0Var, final List list, final State state2, final AudioManager audioManager, final MutableState mutableState, final MutableState mutableState2, final MutableState mutableState3, final MutableState mutableState4, final MutableState mutableState5, final MutableState mutableState6, final MutableIntState mutableIntState, final MutableState mutableState7, final MutableIntState mutableIntState2, final MutableIntState mutableIntState3, final MutableLongState mutableLongState, final MutableIntState mutableIntState4, final MutableIntState mutableIntState5, final FocusRequester focusRequester2, final FocusRequester focusRequester3, final MutableIntState mutableIntState6, final MutableIntState mutableIntState7, final MutableIntState mutableIntState8, final MutableIntState mutableIntState9, final MutableIntState mutableIntState10, final MutableState mutableState8, final MutableState mutableState9, final MutableIntState mutableIntState11, final MutableLongState mutableLongState2, final MutableState mutableState10, final MutableIntState mutableIntState12, final MutableLongState mutableLongState3, final MutableLongState mutableLongState4, final MutableLongState mutableLongState5, final int i10, final MutableIntState mutableIntState13, final MutableState mutableState11, final MutableState mutableState12, final FocusRequester focusRequester4, String str, String str2, String str3, String str4, boolean z10, final Integer num, final Integer num2, MutableIntState mutableIntState14, MutableState mutableState13, MutableState mutableState14, final MutableState mutableState15, MutableState mutableState16, MutableState mutableState17, final MutableState mutableState18, final long j10, final MutableState mutableState19, final String str5, final boolean z11, final boolean z12, final MutableLongState mutableLongState6, final State state3, final CastManager castManager, final Context context, final boolean z13, final boolean z14, final FocusRequester focusRequester5, final FocusRequester focusRequester6, final FocusRequester focusRequester7, final FocusRequester focusRequester8, final String str6, final FocusRequester focusRequester9, final r7.a aVar4, final FocusRequester focusRequester10, final FocusRequester focusRequester11, final r7.a aVar5, final MutableIntState mutableIntState15, final FocusRequester focusRequester12, final r7.a aVar6, final MutableState mutableState20, final MutableLongState mutableLongState7, final MutableState mutableState21, final MutableFloatState mutableFloatState, MutableState mutableState22, MutableState mutableState23, MutableState mutableState24, MutableState mutableState25, MutableIntState mutableIntState16, MutableState mutableState26, MutableState mutableState27, MutableState mutableState28, MutableIntState mutableIntState17, MutableState mutableState29, MutableState mutableState30, Composer composer, int i11) {
        Modifier modifier;
        State state4;
        Modifier.Companion companion;
        Modifier modifierPointerInput;
        Modifier modifier2;
        final ExoPlayer exoPlayer2;
        Modifier.Companion companion2;
        Integer num3;
        State state5;
        Alignment.Companion companion3;
        Modifier.Companion companion4;
        int i12;
        float f10;
        FiniteAnimationSpec finiteAnimationSpec;
        BoxScopeInstance boxScopeInstance;
        Integer num4;
        ComposeUiNode.Companion companion5;
        Modifier.Companion companion6;
        int i13;
        float f11;
        Composer composer2;
        Integer num5;
        Modifier.Companion companion7;
        ComposeUiNode.Companion companion8;
        int i14;
        boolean z15;
        ?? N;
        ?? r12;
        Integer num6;
        int i15;
        int i16;
        BoxScopeInstance boxScopeInstance2;
        Alignment.Companion companion9;
        BoxScopeInstance boxScopeInstance3;
        Modifier.Companion companion10;
        Integer num7;
        PlayerViewModel playerViewModel2;
        Object obj;
        Modifier.Companion companion11;
        BoxScopeInstance boxScopeInstance4;
        Object n0Var;
        Composer composer3;
        boolean z16;
        Object nVar;
        Boolean bool;
        Composer composer4 = composer;
        if ((i11 & 3) == 2 && composer4.getSkipping()) {
            composer4.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1601713466, i11, -1, "com.arflix.tv.ui.screens.player.PlayerScreen.<anonymous> (PlayerScreen.kt:2654)");
            }
            Modifier.Companion companion12 = Modifier.INSTANCE;
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(companion12, 0.0f, 1, null);
            Color.Companion companion13 = Color.INSTANCE;
            Modifier modifierFocusable$default = FocusableKt.focusable$default(FocusRequesterModifierKt.focusRequester(BackgroundKt.m179backgroundbw27NRU$default(modifierFillMaxSize$default, companion13.m3498getBlack0d7_KjU(), null, 2, null), focusRequester), false, null, 3, null);
            if (z) {
                composer4.startReplaceGroup(-440518602);
                Boolean boolValueOf = Boolean.valueOf(z5);
                boolean zChanged = composer4.changed(state) | composer4.changed(lVar);
                Object objRememberedValue = composer4.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    bool = boolValueOf;
                    modifier = modifierFocusable$default;
                    state4 = state;
                    companion = companion12;
                    PlayerScreenKt$PlayerScreen$49$1$1 playerScreenKt$PlayerScreen$49$1$1 = new PlayerScreenKt$PlayerScreen$49$1$1(lVar, state4, mutableState, mutableState2, mutableState3, null);
                    composer4.updateRememberedValue(playerScreenKt$PlayerScreen$49$1$1);
                    objRememberedValue = playerScreenKt$PlayerScreen$49$1$1;
                } else {
                    bool = boolValueOf;
                    modifier = modifierFocusable$default;
                    state4 = state;
                    companion = companion12;
                }
                modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, bool, (r7.p<? super PointerInputScope, ? super d7.d<? super x6.t0>, ? extends Object>) objRememberedValue);
                composer4.endReplaceGroup();
            } else {
                modifier = modifierFocusable$default;
                state4 = state;
                companion = companion12;
                composer4.startReplaceGroup(-439233032);
                composer4.endReplaceGroup();
                modifierPointerInput = companion;
            }
            Modifier modifierThen = modifier.then(modifierPointerInput);
            boolean zChangedInstance = composer4.changedInstance(exoPlayer) | composer4.changed(aVar) | composer4.changed(lVar) | composer4.changed(mediaType.ordinal()) | composer4.changed(state4) | composer4.changed(rVar) | composer4.changed(rVar2) | composer4.changed(aVar2) | composer4.changed(aVar3) | composer4.changedInstance(playerViewModel) | composer4.changedInstance(k0Var) | composer4.changed(list) | composer4.changed(state2) | composer4.changedInstance(audioManager);
            Object objRememberedValue2 = composer4.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                modifier2 = modifierThen;
                exoPlayer2 = exoPlayer;
                companion2 = companion;
                num3 = 0;
                final State state6 = state4;
                objRememberedValue2 = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$2$1

                    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$2$1$10, reason: invalid class name */
                    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
                    @f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$2$1$10", f = "PlayerScreen.kt", l = {2959}, m = "invokeSuspend", v = 2)
                    public static final class AnonymousClass10 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
                        final /* synthetic */ FocusRequester $subtitleButtonFocusRequester;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public AnonymousClass10(FocusRequester focusRequester, d7.d<? super AnonymousClass10> dVar) {
                            super(2, dVar);
                            this.$subtitleButtonFocusRequester = focusRequester;
                        }

                        @Override // f7.a
                        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                            return new AnonymousClass10(this.$subtitleButtonFocusRequester, dVar);
                        }

                        @Override // f7.a
                        public final Object invokeSuspend(Object obj) {
                            int i10 = this.label;
                            if (i10 == 0) {
                                k2.c.G(obj);
                                this.label = 1;
                                Object objA = ka.s0.a(150L, this);
                                e7.a aVar = e7.a.f15033i;
                                if (objA == aVar) {
                                    return aVar;
                                }
                            } else {
                                if (i10 != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                k2.c.G(obj);
                            }
                            try {
                                this.$subtitleButtonFocusRequester.requestFocus();
                            } catch (Exception unused) {
                            }
                            return x6.t0.f22605a;
                        }

                        @Override // r7.p
                        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                            return ((AnonymousClass10) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
                        }
                    }

                    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$2$1$11, reason: invalid class name */
                    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
                    @f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$2$1$11", f = "PlayerScreen.kt", l = {2968}, m = "invokeSuspend", v = 2)
                    public static final class AnonymousClass11 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
                        final /* synthetic */ FocusRequester $subtitleButtonFocusRequester;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public AnonymousClass11(FocusRequester focusRequester, d7.d<? super AnonymousClass11> dVar) {
                            super(2, dVar);
                            this.$subtitleButtonFocusRequester = focusRequester;
                        }

                        @Override // f7.a
                        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                            return new AnonymousClass11(this.$subtitleButtonFocusRequester, dVar);
                        }

                        @Override // f7.a
                        public final Object invokeSuspend(Object obj) {
                            int i10 = this.label;
                            if (i10 == 0) {
                                k2.c.G(obj);
                                this.label = 1;
                                Object objA = ka.s0.a(150L, this);
                                e7.a aVar = e7.a.f15033i;
                                if (objA == aVar) {
                                    return aVar;
                                }
                            } else {
                                if (i10 != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                k2.c.G(obj);
                            }
                            try {
                                this.$subtitleButtonFocusRequester.requestFocus();
                            } catch (Exception unused) {
                            }
                            return x6.t0.f22605a;
                        }

                        @Override // r7.p
                        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                            return ((AnonymousClass11) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
                        }
                    }

                    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$2$1$13, reason: invalid class name */
                    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
                    @f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$2$1$13", f = "PlayerScreen.kt", l = {2999}, m = "invokeSuspend", v = 2)
                    public static final class AnonymousClass13 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
                        final /* synthetic */ FocusRequester $subtitleButtonFocusRequester;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public AnonymousClass13(FocusRequester focusRequester, d7.d<? super AnonymousClass13> dVar) {
                            super(2, dVar);
                            this.$subtitleButtonFocusRequester = focusRequester;
                        }

                        @Override // f7.a
                        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                            return new AnonymousClass13(this.$subtitleButtonFocusRequester, dVar);
                        }

                        @Override // f7.a
                        public final Object invokeSuspend(Object obj) {
                            int i10 = this.label;
                            if (i10 == 0) {
                                k2.c.G(obj);
                                this.label = 1;
                                Object objA = ka.s0.a(150L, this);
                                e7.a aVar = e7.a.f15033i;
                                if (objA == aVar) {
                                    return aVar;
                                }
                            } else {
                                if (i10 != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                k2.c.G(obj);
                            }
                            try {
                                this.$subtitleButtonFocusRequester.requestFocus();
                            } catch (Exception unused) {
                            }
                            return x6.t0.f22605a;
                        }

                        @Override // r7.p
                        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                            return ((AnonymousClass13) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
                        }
                    }

                    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$2$1$14, reason: invalid class name */
                    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
                    @f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$2$1$14", f = "PlayerScreen.kt", l = {3074}, m = "invokeSuspend", v = 2)
                    public static final class AnonymousClass14 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
                        final /* synthetic */ FocusRequester $skipIntroFocusRequester;
                        private /* synthetic */ Object L$0;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public AnonymousClass14(FocusRequester focusRequester, d7.d<? super AnonymousClass14> dVar) {
                            super(2, dVar);
                            this.$skipIntroFocusRequester = focusRequester;
                        }

                        @Override // f7.a
                        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                            AnonymousClass14 anonymousClass14 = new AnonymousClass14(this.$skipIntroFocusRequester, dVar);
                            anonymousClass14.L$0 = obj;
                            return anonymousClass14;
                        }

                        @Override // f7.a
                        public final Object invokeSuspend(Object obj) {
                            ka.k0 k0Var = (ka.k0) this.L$0;
                            int i10 = this.label;
                            if (i10 == 0) {
                                k2.c.G(obj);
                                this.L$0 = k0Var;
                                this.label = 1;
                                Object objA = ka.s0.a(40L, this);
                                e7.a aVar = e7.a.f15033i;
                                if (objA == aVar) {
                                    return aVar;
                                }
                            } else {
                                if (i10 != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                k2.c.G(obj);
                            }
                            try {
                                this.$skipIntroFocusRequester.requestFocus();
                            } catch (Throwable unused) {
                            }
                            return x6.t0.f22605a;
                        }

                        @Override // r7.p
                        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                            return ((AnonymousClass14) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
                        }
                    }

                    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$2$1$7, reason: invalid class name */
                    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
                    @f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$2$1$7", f = "PlayerScreen.kt", l = {2857}, m = "invokeSuspend", v = 2)
                    public static final class AnonymousClass7 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
                        final /* synthetic */ FocusRequester $subtitleSettingsBtnFocusRequester;
                        private /* synthetic */ Object L$0;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public AnonymousClass7(FocusRequester focusRequester, d7.d<? super AnonymousClass7> dVar) {
                            super(2, dVar);
                            this.$subtitleSettingsBtnFocusRequester = focusRequester;
                        }

                        @Override // f7.a
                        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                            AnonymousClass7 anonymousClass7 = new AnonymousClass7(this.$subtitleSettingsBtnFocusRequester, dVar);
                            anonymousClass7.L$0 = obj;
                            return anonymousClass7;
                        }

                        @Override // f7.a
                        public final Object invokeSuspend(Object obj) {
                            ka.k0 k0Var = (ka.k0) this.L$0;
                            int i10 = this.label;
                            if (i10 == 0) {
                                k2.c.G(obj);
                                this.L$0 = k0Var;
                                this.label = 1;
                                Object objA = ka.s0.a(120L, this);
                                e7.a aVar = e7.a.f15033i;
                                if (objA == aVar) {
                                    return aVar;
                                }
                            } else {
                                if (i10 != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                k2.c.G(obj);
                            }
                            try {
                                this.$subtitleSettingsBtnFocusRequester.requestFocus();
                            } catch (Throwable unused) {
                            }
                            return x6.t0.f22605a;
                        }

                        @Override // r7.p
                        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                            return ((AnonymousClass7) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
                        }
                    }

                    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$2$1$8, reason: invalid class name */
                    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
                    @f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$2$1$8", f = "PlayerScreen.kt", l = {2884}, m = "invokeSuspend", v = 2)
                    public static final class AnonymousClass8 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
                        final /* synthetic */ FocusRequester $subtitleButtonFocusRequester;
                        int label;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public AnonymousClass8(FocusRequester focusRequester, d7.d<? super AnonymousClass8> dVar) {
                            super(2, dVar);
                            this.$subtitleButtonFocusRequester = focusRequester;
                        }

                        @Override // f7.a
                        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                            return new AnonymousClass8(this.$subtitleButtonFocusRequester, dVar);
                        }

                        @Override // f7.a
                        public final Object invokeSuspend(Object obj) {
                            int i10 = this.label;
                            if (i10 == 0) {
                                k2.c.G(obj);
                                this.label = 1;
                                Object objA = ka.s0.a(150L, this);
                                e7.a aVar = e7.a.f15033i;
                                if (objA == aVar) {
                                    return aVar;
                                }
                            } else {
                                if (i10 != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                k2.c.G(obj);
                            }
                            try {
                                this.$subtitleButtonFocusRequester.requestFocus();
                            } catch (Exception unused) {
                            }
                            return x6.t0.f22605a;
                        }

                        @Override // r7.p
                        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                            return ((AnonymousClass8) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
                        }
                    }

                    @Override // r7.l
                    public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                        return m6267invokeZmokQxo(((KeyEvent) obj2).m4390unboximpl());
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                    public final Boolean m6267invokeZmokQxo(android.view.KeyEvent keyEvent) throws Exception {
                        String addonId;
                        String source;
                        StreamBehaviorHints behaviorHints;
                        String bingeGroup;
                        List list2;
                        x6.x xVar;
                        Subtitle subtitle;
                        String str7;
                        String str8;
                        List list3;
                        String str9;
                        String str10;
                        String addonId2;
                        String source2;
                        StreamBehaviorHints behaviorHints2;
                        String bingeGroup2;
                        Boolean bool2 = Boolean.TRUE;
                        boolean z17 = false;
                        boolean z18 = false;
                        size = 0;
                        int size = 0;
                        boolean z19 = false;
                        z17 = false;
                        z17 = false;
                        z17 = false;
                        z17 = false;
                        if (KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                            long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                            Key.Companion companion14 = Key.INSTANCE;
                            if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion14.m4229getMediaPlayPauseEK5gGoQ())) {
                                if (exoPlayer2.isPlaying()) {
                                    exoPlayer2.pause();
                                } else {
                                    exoPlayer2.play();
                                }
                                PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, true);
                                return bool2;
                            }
                            if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion14.m4228getMediaPlayEK5gGoQ())) {
                                exoPlayer2.play();
                                PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, true);
                                return bool2;
                            }
                            if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion14.m4227getMediaPauseEK5gGoQ())) {
                                exoPlayer2.pause();
                                PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, true);
                                return bool2;
                            }
                            if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion14.m4237getMediaStopEK5gGoQ())) {
                                exoPlayer2.pause();
                                aVar.invoke();
                                return bool2;
                            }
                            if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion14.m4232getMediaRewindEK5gGoQ())) {
                                lVar.invoke(-10000L);
                                PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, true);
                                return bool2;
                            }
                            if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion14.m4225getMediaFastForwardEK5gGoQ())) {
                                lVar.invoke(10000L);
                                PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, true);
                                return bool2;
                            }
                            String str11 = null;
                            if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion14.m4226getMediaNextEK5gGoQ())) {
                                if (mediaType == MediaType.TV && PlayerScreenKt.PlayerScreen$lambda$130(mutableState4) != null) {
                                    StreamSource selectedStream = PlayerScreenKt.PlayerScreen$lambda$3(state6).getSelectedStream();
                                    EpisodeIdentity episodeIdentityPlayerScreen$lambda$130 = PlayerScreenKt.PlayerScreen$lambda$130(mutableState4);
                                    if (episodeIdentityPlayerScreen$lambda$130 == null) {
                                        return bool2;
                                    }
                                    r7.r<EpisodeIdentity, String, String, String, x6.t0> rVar3 = rVar;
                                    if (selectedStream == null || (addonId2 = selectedStream.getAddonId()) == null || kotlin.text.o.h0(addonId2)) {
                                        addonId2 = null;
                                    }
                                    if (selectedStream == null || (source2 = selectedStream.getSource()) == null || kotlin.text.o.h0(source2)) {
                                        source2 = null;
                                    }
                                    if (selectedStream != null && (behaviorHints2 = selectedStream.getBehaviorHints()) != null && (bingeGroup2 = behaviorHints2.getBingeGroup()) != null && !kotlin.text.o.h0(bingeGroup2)) {
                                        str11 = bingeGroup2;
                                    }
                                    rVar3.invoke(episodeIdentityPlayerScreen$lambda$130, addonId2, source2, str11);
                                    return bool2;
                                }
                            } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion14.m4230getMediaPreviousEK5gGoQ()) && mediaType == MediaType.TV && PlayerScreenKt.PlayerScreen$lambda$133(mutableState5) != null) {
                                StreamSource selectedStream2 = PlayerScreenKt.PlayerScreen$lambda$3(state6).getSelectedStream();
                                EpisodeIdentity episodeIdentityPlayerScreen$lambda$133 = PlayerScreenKt.PlayerScreen$lambda$133(mutableState5);
                                if (episodeIdentityPlayerScreen$lambda$133 == null) {
                                    return bool2;
                                }
                                r7.r<EpisodeIdentity, String, String, String, x6.t0> rVar4 = rVar2;
                                if (selectedStream2 == null || (addonId = selectedStream2.getAddonId()) == null || kotlin.text.o.h0(addonId)) {
                                    addonId = null;
                                }
                                if (selectedStream2 == null || (source = selectedStream2.getSource()) == null || kotlin.text.o.h0(source)) {
                                    source = null;
                                }
                                if (selectedStream2 != null && (behaviorHints = selectedStream2.getBehaviorHints()) != null && (bingeGroup = behaviorHints.getBingeGroup()) != null && !kotlin.text.o.h0(bingeGroup)) {
                                    str11 = bingeGroup;
                                }
                                rVar4.invoke(episodeIdentityPlayerScreen$lambda$133, addonId, source, str11);
                                return bool2;
                            }
                            if (PlayerScreenKt.PlayerScreen$lambda$115(mutableState6)) {
                                long jM4401getKeyZmokQxo2 = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, companion14.m4169getDirectionLeftEK5gGoQ())) {
                                    mutableIntState.setIntValue(0);
                                    return bool2;
                                }
                                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, companion14.m4170getDirectionRightEK5gGoQ())) {
                                    mutableIntState.setIntValue(1);
                                    return bool2;
                                }
                                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, companion14.m4179getEnterEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, companion14.m4165getDirectionCenterEK5gGoQ())) {
                                    if (mutableIntState.getIntValue() == 0) {
                                        aVar2.invoke();
                                        return bool2;
                                    }
                                    aVar3.invoke();
                                    return bool2;
                                }
                                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, companion14.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo2, companion14.m4182getEscapeEK5gGoQ())) {
                                    aVar3.invoke();
                                    return bool2;
                                }
                            } else {
                                if ((Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent), companion14.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent), companion14.m4182getEscapeEK5gGoQ())) && !PlayerScreenKt.PlayerScreen$lambda$109(mutableState) && !PlayerScreenKt.PlayerScreen$lambda$112(mutableState2) && !PlayerScreenKt.PlayerScreen$lambda$115(mutableState6) && !PlayerScreenKt.PlayerScreen$lambda$162(mutableState7) && PlayerScreenKt.PlayerScreen$lambda$3(state6).getError() == null) {
                                    if (PlayerScreenKt.PlayerScreen$lambda$32(mutableState3)) {
                                        PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, false);
                                    } else {
                                        aVar.invoke();
                                    }
                                    return bool2;
                                }
                                if (PlayerScreenKt.PlayerScreen$lambda$3(state6).getError() != null) {
                                    int i17 = !PlayerScreenKt.PlayerScreen$lambda$3(state6).isSetupError() ? 1 : 0;
                                    long jM4401getKeyZmokQxo3 = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, companion14.m4169getDirectionLeftEK5gGoQ())) {
                                        if (mutableIntState2.getIntValue() > 0) {
                                            mutableIntState2.setIntValue(mutableIntState2.getIntValue() - 1);
                                        }
                                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, companion14.m4170getDirectionRightEK5gGoQ())) {
                                        if (mutableIntState2.getIntValue() < i17) {
                                            mutableIntState2.setIntValue(mutableIntState2.getIntValue() + 1);
                                        }
                                    } else {
                                        if (!Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, companion14.m4179getEnterEK5gGoQ()) && !Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, companion14.m4165getDirectionCenterEK5gGoQ())) {
                                            if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, companion14.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo3, companion14.m4182getEscapeEK5gGoQ())) {
                                                aVar.invoke();
                                            }
                                            return Boolean.valueOf(z18);
                                        }
                                        if (!PlayerScreenKt.PlayerScreen$lambda$3(state6).isSetupError() && mutableIntState2.getIntValue() == 0) {
                                            playerViewModel.retry();
                                        } else {
                                            aVar.invoke();
                                        }
                                    }
                                    z18 = true;
                                    return Boolean.valueOf(z18);
                                }
                                if (PlayerScreenKt.PlayerScreen$lambda$162(mutableState7)) {
                                    long jM4401getKeyZmokQxo4 = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo4, companion14.m4171getDirectionUpEK5gGoQ())) {
                                        MutableIntState mutableIntState18 = mutableIntState3;
                                        int intValue = mutableIntState18.getIntValue() - 1;
                                        mutableIntState18.setIntValue(intValue >= 0 ? intValue : 0);
                                        return bool2;
                                    }
                                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo4, companion14.m4166getDirectionDownEK5gGoQ())) {
                                        MutableIntState mutableIntState19 = mutableIntState3;
                                        int intValue2 = mutableIntState19.getIntValue() + 1;
                                        mutableIntState19.setIntValue(intValue2 <= 2 ? intValue2 : 2);
                                        return bool2;
                                    }
                                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo4, companion14.m4169getDirectionLeftEK5gGoQ())) {
                                        int intValue3 = mutableIntState3.getIntValue();
                                        if (intValue3 == 0) {
                                            MutableLongState mutableLongState8 = mutableLongState;
                                            long longValue = mutableLongState8.getLongValue() - 100;
                                            mutableLongState8.setLongValue(longValue < -10000 ? -10000L : longValue);
                                            return bool2;
                                        }
                                        if (intValue3 == 1) {
                                            MutableIntState mutableIntState20 = mutableIntState4;
                                            int intValue4 = mutableIntState20.getIntValue() - 10;
                                            mutableIntState20.setIntValue(intValue4 >= 50 ? intValue4 : 50);
                                            return bool2;
                                        }
                                        if (intValue3 == 2) {
                                            MutableIntState mutableIntState21 = mutableIntState5;
                                            int intValue5 = mutableIntState21.getIntValue() - 1;
                                            mutableIntState21.setIntValue(intValue5 >= 0 ? intValue5 : 0);
                                            return bool2;
                                        }
                                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo4, companion14.m4170getDirectionRightEK5gGoQ())) {
                                        int intValue6 = mutableIntState3.getIntValue();
                                        if (intValue6 == 0) {
                                            MutableLongState mutableLongState9 = mutableLongState;
                                            long longValue2 = mutableLongState9.getLongValue() + 100;
                                            mutableLongState9.setLongValue(longValue2 > 10000 ? 10000L : longValue2);
                                            return bool2;
                                        }
                                        if (intValue6 == 1) {
                                            MutableIntState mutableIntState22 = mutableIntState4;
                                            int intValue7 = mutableIntState22.getIntValue() + 10;
                                            if (intValue7 > 300) {
                                                intValue7 = 300;
                                            }
                                            mutableIntState22.setIntValue(intValue7);
                                            return bool2;
                                        }
                                        if (intValue6 == 2) {
                                            MutableIntState mutableIntState23 = mutableIntState5;
                                            int intValue8 = mutableIntState23.getIntValue() + 1;
                                            mutableIntState23.setIntValue(intValue8 <= 50 ? intValue8 : 50);
                                            return bool2;
                                        }
                                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo4, companion14.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo4, companion14.m4182getEscapeEK5gGoQ())) {
                                        PlayerScreenKt.PlayerScreen$lambda$163(mutableState7, false);
                                        PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, true);
                                        ka.m0.p(k0Var, null, 0, new AnonymousClass7(focusRequester2, null), 3);
                                        return bool2;
                                    }
                                } else {
                                    if (PlayerScreenKt.PlayerScreen$lambda$109(mutableState)) {
                                        long jM4401getKeyZmokQxo5 = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                                        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo5, companion14.m4229getMediaPlayPauseEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo5, companion14.m4228getMediaPlayEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo5, companion14.m4227getMediaPauseEK5gGoQ())) {
                                            if (Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent), companion14.m4227getMediaPauseEK5gGoQ())) {
                                                exoPlayer2.pause();
                                            } else if (!Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent), companion14.m4228getMediaPlayEK5gGoQ()) && exoPlayer2.isPlaying()) {
                                                exoPlayer2.pause();
                                            } else {
                                                exoPlayer2.play();
                                            }
                                            PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, true);
                                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo5, companion14.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo5, companion14.m4182getEscapeEK5gGoQ())) {
                                            PlayerScreenKt.PlayerScreen$lambda$110(mutableState, false);
                                            PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, true);
                                            ka.m0.p(k0Var, null, 0, new AnonymousClass8(focusRequester3, null), 3);
                                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo5, companion14.m4171getDirectionUpEK5gGoQ())) {
                                            if (mutableIntState6.getIntValue() == 1) {
                                                if (mutableIntState7.getIntValue() > 0) {
                                                    mutableIntState7.setIntValue(mutableIntState7.getIntValue() - 1);
                                                }
                                            } else if (mutableIntState8.getIntValue() == 0) {
                                                if (mutableIntState9.getIntValue() > 0) {
                                                    mutableIntState9.setIntValue(mutableIntState9.getIntValue() - 1);
                                                }
                                            } else if (mutableIntState10.getIntValue() > 0) {
                                                mutableIntState10.setIntValue(mutableIntState10.getIntValue() - 1);
                                            }
                                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo5, companion14.m4166getDirectionDownEK5gGoQ())) {
                                            if (mutableIntState6.getIntValue() == 1) {
                                                int intValue9 = mutableIntState7.getIntValue();
                                                int size2 = PlayerScreenKt.PlayerScreen$lambda$181(mutableState8).size();
                                                if (size2 < 1) {
                                                    size2 = 1;
                                                }
                                                if (intValue9 < size2 - 1) {
                                                    mutableIntState7.setIntValue(mutableIntState7.getIntValue() + 1);
                                                }
                                            } else if (mutableIntState8.getIntValue() != 0) {
                                                x6.x xVar2 = (x6.x) kotlin.collections.x.p0(mutableIntState9.getIntValue() - 1, list);
                                                int i18 = ((kotlin.text.o.h0(PlayerScreenKt.PlayerScreen$lambda$4(state2).getMatchLanguageName()) || xVar2 == null || (str10 = (String) xVar2.f22608i) == null || !str10.equalsIgnoreCase(PlayerScreenKt.PlayerScreen$lambda$4(state2).getMatchLanguageName())) ? 0 : 1) + ((!PlayerScreenKt.PlayerScreen$lambda$4(state2).isAiAvailable() || kotlin.text.o.h0(PlayerScreenKt.PlayerScreen$lambda$4(state2).getAiTargetLanguageName()) || xVar2 == null || (str9 = (String) xVar2.f22608i) == null || !str9.equalsIgnoreCase(PlayerScreenKt.PlayerScreen$lambda$4(state2).getAiTargetLanguageName())) ? 0 : 1);
                                                if (xVar2 != null && (list3 = (List) xVar2.f22609l) != null) {
                                                    size = list3.size();
                                                }
                                                if (mutableIntState10.getIntValue() < (size + i18) - 1) {
                                                    mutableIntState10.setIntValue(mutableIntState10.getIntValue() + 1);
                                                }
                                            } else if (mutableIntState9.getIntValue() < list.size()) {
                                                mutableIntState9.setIntValue(mutableIntState9.getIntValue() + 1);
                                            }
                                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo5, companion14.m4169getDirectionLeftEK5gGoQ())) {
                                            if (mutableIntState6.getIntValue() == 1) {
                                                mutableIntState6.setIntValue(0);
                                                mutableIntState8.setIntValue(0);
                                            } else if (mutableIntState8.getIntValue() == 1) {
                                                mutableIntState8.setIntValue(0);
                                            }
                                        } else {
                                            if (!Key.m4093equalsimpl0(jM4401getKeyZmokQxo5, companion14.m4170getDirectionRightEK5gGoQ())) {
                                                if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo5, companion14.m4179getEnterEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo5, companion14.m4165getDirectionCenterEK5gGoQ())) {
                                                    if (mutableIntState6.getIntValue() == 1) {
                                                        AudioTrackInfo audioTrackInfo = (AudioTrackInfo) kotlin.collections.x.p0(mutableIntState7.getIntValue(), PlayerScreenKt.PlayerScreen$lambda$181(mutableState8));
                                                        if (audioTrackInfo != null) {
                                                            ExoPlayer exoPlayer3 = exoPlayer2;
                                                            MutableState<Boolean> mutableState31 = mutableState9;
                                                            MutableState<List<AudioTrackInfo>> mutableState32 = mutableState8;
                                                            MutableIntState mutableIntState24 = mutableIntState11;
                                                            PlayerScreenKt.PlayerScreen$lambda$188(mutableState31, true);
                                                            Integer numApplyAudioTrackSelection = PlayerScreenKt.applyAudioTrackSelection(exoPlayer3, audioTrackInfo, PlayerScreenKt.PlayerScreen$lambda$181(mutableState32));
                                                            if (numApplyAudioTrackSelection != null) {
                                                                mutableIntState24.setIntValue(numApplyAudioTrackSelection.intValue());
                                                            }
                                                        }
                                                        PlayerScreenKt.PlayerScreen$lambda$110(mutableState, false);
                                                        PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, true);
                                                        ka.m0.p(k0Var, null, 0, new AnonymousClass10(focusRequester3, null), 3);
                                                    } else if (mutableIntState8.getIntValue() != 0) {
                                                        x6.x xVar3 = (x6.x) kotlin.collections.x.p0(mutableIntState9.getIntValue() - 1, list);
                                                        int i19 = (kotlin.text.o.h0(PlayerScreenKt.PlayerScreen$lambda$4(state2).getMatchLanguageName()) || xVar3 == null || (str8 = (String) xVar3.f22608i) == null || !str8.equalsIgnoreCase(PlayerScreenKt.PlayerScreen$lambda$4(state2).getMatchLanguageName())) ? 0 : 1;
                                                        int i20 = (!PlayerScreenKt.PlayerScreen$lambda$4(state2).isAiAvailable() || kotlin.text.o.h0(PlayerScreenKt.PlayerScreen$lambda$4(state2).getAiTargetLanguageName()) || xVar3 == null || (str7 = (String) xVar3.f22608i) == null || !str7.equalsIgnoreCase(PlayerScreenKt.PlayerScreen$lambda$4(state2).getAiTargetLanguageName())) ? 0 : 1;
                                                        int intValue10 = mutableIntState10.getIntValue() - (i19 + i20);
                                                        if (i19 != 0 && mutableIntState10.getIntValue() == 0) {
                                                            PlayerViewModel.runFindBestMatch$default(playerViewModel, false, 1, null);
                                                        } else if (i20 == 0 || mutableIntState10.getIntValue() != i19) {
                                                            if (xVar3 != null && (list2 = (List) xVar3.f22609l) != null && (xVar = (x6.x) kotlin.collections.x.p0(intValue10, list2)) != null && (subtitle = (Subtitle) xVar.f22609l) != null) {
                                                                PlayerViewModel.selectSubtitle$default(playerViewModel, subtitle, false, 2, null);
                                                            }
                                                        } else if (!PlayerScreenKt.PlayerScreen$lambda$4(state2).isAiTranslating()) {
                                                            playerViewModel.activateAiTranslation();
                                                        }
                                                        PlayerScreenKt.PlayerScreen$lambda$110(mutableState, false);
                                                        PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, true);
                                                        ka.m0.p(k0Var, null, 0, new AnonymousClass13(focusRequester3, null), 3);
                                                    } else if (mutableIntState9.getIntValue() == 0) {
                                                        playerViewModel.disableSubtitles();
                                                        PlayerScreenKt.PlayerScreen$lambda$110(mutableState, false);
                                                        PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, true);
                                                        ka.m0.p(k0Var, null, 0, new AnonymousClass11(focusRequester3, null), 3);
                                                    } else {
                                                        mutableIntState8.setIntValue(1);
                                                        mutableIntState10.setIntValue(0);
                                                    }
                                                }
                                                return Boolean.valueOf(z19);
                                            }
                                            if (mutableIntState6.getIntValue() == 0 && mutableIntState8.getIntValue() == 1) {
                                                mutableIntState6.setIntValue(1);
                                                mutableIntState7.setIntValue(0);
                                            } else if (mutableIntState6.getIntValue() == 0 && mutableIntState9.getIntValue() > 0) {
                                                mutableIntState8.setIntValue(1);
                                                mutableIntState10.setIntValue(0);
                                            } else if (mutableIntState6.getIntValue() == 0 && mutableIntState9.getIntValue() == 0) {
                                                mutableIntState6.setIntValue(1);
                                                mutableIntState7.setIntValue(0);
                                            }
                                        }
                                        z19 = true;
                                        return Boolean.valueOf(z19);
                                    }
                                    long jM4401getKeyZmokQxo6 = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                                    if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo6, companion14.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo6, companion14.m4182getEscapeEK5gGoQ())) {
                                        aVar.invoke();
                                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo6, companion14.m4169getDirectionLeftEK5gGoQ())) {
                                        if (!PlayerScreenKt.PlayerScreen$lambda$32(mutableState3)) {
                                            long jCurrentTimeMillis = System.currentTimeMillis();
                                            if (jCurrentTimeMillis - mutableLongState2.getLongValue() >= 1200 || !PlayerScreenKt.PlayerScreen$lambda$53(mutableState10)) {
                                                mutableLongState3.setLongValue(exoPlayer2.getCurrentPosition());
                                                mutableIntState12.setIntValue(-10);
                                            } else {
                                                MutableIntState mutableIntState25 = mutableIntState12;
                                                mutableIntState25.setIntValue(qb.d.n(mutableIntState25.getIntValue() - 10, -10000, 10000));
                                            }
                                            mutableLongState2.setLongValue(jCurrentTimeMillis);
                                            long intValue11 = (((long) mutableIntState12.getIntValue()) * 1000) + mutableLongState3.getLongValue();
                                            if (intValue11 < 0) {
                                                intValue11 = 0;
                                            }
                                            if (mutableLongState4.getLongValue() > 0) {
                                                long longValue3 = mutableLongState4.getLongValue();
                                                if (intValue11 > longValue3) {
                                                    intValue11 = longValue3;
                                                }
                                            }
                                            mutableLongState5.setLongValue(intValue11);
                                            exoPlayer2.seekTo(intValue11);
                                            PlayerScreenKt.PlayerScreen$lambda$54(mutableState10, true);
                                        }
                                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo6, companion14.m4170getDirectionRightEK5gGoQ())) {
                                        if (!PlayerScreenKt.PlayerScreen$lambda$32(mutableState3)) {
                                            long jCurrentTimeMillis2 = System.currentTimeMillis();
                                            if (jCurrentTimeMillis2 - mutableLongState2.getLongValue() >= 1200 || !PlayerScreenKt.PlayerScreen$lambda$53(mutableState10)) {
                                                mutableLongState3.setLongValue(exoPlayer2.getCurrentPosition());
                                                mutableIntState12.setIntValue(10);
                                            } else {
                                                MutableIntState mutableIntState26 = mutableIntState12;
                                                mutableIntState26.setIntValue(qb.d.n(mutableIntState26.getIntValue() + 10, -10000, 10000));
                                            }
                                            mutableLongState2.setLongValue(jCurrentTimeMillis2);
                                            long intValue12 = (((long) mutableIntState12.getIntValue()) * 1000) + mutableLongState3.getLongValue();
                                            if (intValue12 < 0) {
                                                intValue12 = 0;
                                            }
                                            if (mutableLongState4.getLongValue() > 0) {
                                                long longValue4 = mutableLongState4.getLongValue();
                                                if (intValue12 > longValue4) {
                                                    intValue12 = longValue4;
                                                }
                                            }
                                            mutableLongState5.setLongValue(intValue12);
                                            exoPlayer2.seekTo(intValue12);
                                            PlayerScreenKt.PlayerScreen$lambda$54(mutableState10, true);
                                        }
                                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo6, companion14.m4372getVolumeUpEK5gGoQ())) {
                                        PlayerScreenKt.PlayerScreen$adjustVolume(i10, audioManager, mutableIntState13, mutableState11, mutableState12, 1);
                                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo6, companion14.m4370getVolumeDownEK5gGoQ())) {
                                        PlayerScreenKt.PlayerScreen$adjustVolume(i10, audioManager, mutableIntState13, mutableState11, mutableState12, -1);
                                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo6, companion14.m4171getDirectionUpEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo6, companion14.m4166getDirectionDownEK5gGoQ())) {
                                        Object[] objArr = (PlayerScreenKt.PlayerScreen$lambda$3(state6).getActiveSkipInterval() == null || PlayerScreenKt.PlayerScreen$lambda$3(state6).getSkipIntervalDismissed()) ? false : true;
                                        if (!PlayerScreenKt.PlayerScreen$lambda$32(mutableState3)) {
                                            if (objArr == true && Key.m4093equalsimpl0(KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent), companion14.m4171getDirectionUpEK5gGoQ())) {
                                                ka.m0.p(k0Var, null, 0, new AnonymousClass14(focusRequester4, null), 3);
                                            } else {
                                                PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, true);
                                            }
                                        }
                                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo6, companion14.m4179getEnterEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo6, companion14.m4165getDirectionCenterEK5gGoQ())) {
                                        if (exoPlayer2.isPlaying()) {
                                            exoPlayer2.pause();
                                        } else {
                                            exoPlayer2.play();
                                        }
                                        if (!PlayerScreenKt.PlayerScreen$lambda$32(mutableState3)) {
                                            PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, true);
                                        }
                                    } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo6, companion14.m4317getSpacebarEK5gGoQ())) {
                                        if (exoPlayer2.isPlaying()) {
                                            exoPlayer2.pause();
                                        } else {
                                            exoPlayer2.play();
                                        }
                                        PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, true);
                                    } else if (!PlayerScreenKt.PlayerScreen$lambda$32(mutableState3)) {
                                        PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, true);
                                    }
                                    z17 = true;
                                }
                            }
                            return bool2;
                        }
                        return Boolean.valueOf(z17);
                    }
                };
                composer4 = composer;
                state5 = state6;
                composer4.updateRememberedValue(objRememberedValue2);
            } else {
                modifier2 = modifierThen;
                state5 = state4;
                exoPlayer2 = exoPlayer;
                companion2 = companion;
                num3 = 0;
            }
            Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(modifier2, (r7.l) objRememberedValue2);
            composer4.startReplaceableGroup(733328855);
            Alignment.Companion companion14 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyD = a0.c.d(companion14, false, composer4, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
            CompositionLocalMap currentCompositionLocalMap = composer4.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion15 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion15.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierOnKeyEvent);
            if (composer4.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer4.startReusableNode();
            if (composer4.getInserting()) {
                composer4.createNode(constructor);
            } else {
                composer4.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer4);
            r7.p pVarU = a0.c.u(companion15, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            Integer num8 = num3;
            a0.c.y(composer4, qVarModifierMaterializerOf, composer4, num8, 2058660585);
            BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
            if (z5) {
                composer4.startReplaceGroup(1401921496);
                companion3 = companion14;
                companion4 = companion2;
                i12 = 1;
                f10 = 0.0f;
                finiteAnimationSpec = null;
                BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxSize$default(companion4, 0.0f, 1, null), companion13.m3498getBlack0d7_KjU(), null, 2, null), composer4, 6);
                composer4.endReplaceGroup();
            } else {
                companion3 = companion14;
                companion4 = companion2;
                i12 = 1;
                f10 = 0.0f;
                finiteAnimationSpec = null;
                composer4.startReplaceGroup(1402004142);
                composer4.endReplaceGroup();
            }
            if (PlayerScreen$lambda$3(state5).getSelectedStreamUrl() == null || z5) {
                boxScopeInstance = boxScopeInstance5;
                num4 = num8;
                companion5 = companion15;
                companion6 = companion4;
                i13 = i12;
                f11 = f10;
                composer2 = composer4;
                composer2.startReplaceGroup(1407911502);
                composer2.endReplaceGroup();
            } else {
                composer4.startReplaceGroup(1402439258);
                Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(companion4, f10, i12, finiteAnimationSpec);
                String str7 = str;
                boolean zChangedInstance2 = composer4.changedInstance(exoPlayer2) | composer4.changed(str7) | composer4.changed(str2) | composer4.changed(str3) | composer4.changed(str4) | composer4.changed(z10);
                Object objRememberedValue3 = composer4.rememberedValue();
                if (zChangedInstance2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    companion6 = companion4;
                    boxScopeInstance = boxScopeInstance5;
                    num4 = num8;
                    i13 = 1;
                    f11 = 0.0f;
                    companion5 = companion15;
                    nVar = new com.arflix.tv.ui.screens.details.n(exoPlayer2, mutableIntState14, str7, str2, str3, str4, z10, mutableState13);
                    str7 = str7;
                    composer4.updateRememberedValue(nVar);
                } else {
                    boxScopeInstance = boxScopeInstance5;
                    num4 = num8;
                    companion5 = companion15;
                    companion6 = companion4;
                    nVar = objRememberedValue3;
                    i13 = 1;
                    f11 = 0.0f;
                }
                r7.l lVar2 = (r7.l) nVar;
                boolean zChangedInstance3 = composer4.changedInstance(exoPlayer2) | composer4.changed(str7) | composer4.changed(state5);
                Object objRememberedValue4 = composer4.rememberedValue();
                if (zChangedInstance3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    i0 i0Var = new i0(exoPlayer2, mutableIntState14, mutableState14, str7, mutableState13, mutableIntState4, mutableIntState5, state5);
                    composer4.updateRememberedValue(i0Var);
                    objRememberedValue4 = i0Var;
                }
                Composer composer5 = composer4;
                AndroidView_androidKt.AndroidView(lVar2, modifierFillMaxSize$default2, (r7.l) objRememberedValue4, composer5, 48, 0);
                composer2 = composer5;
                composer2.endReplaceGroup();
            }
            String str8 = "";
            if (PlayerScreen$lambda$3(state).isLoading() || PlayerScreen$lambda$3(state).getSelectedStreamUrl() == null || !PlayerScreen$lambda$26(mutableState15)) {
                composer2.startReplaceGroup(1408154790);
                Modifier modifierFillMaxSize$default3 = SizeKt.fillMaxSize$default(companion6, f11, i13, finiteAnimationSpec);
                Alignment center = companion3.getCenter();
                composer2.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer2, 6);
                composer2.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor2 = companion5.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierFillMaxSize$default3);
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
                r7.p pVarU2 = a0.c.u(companion5, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
                if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
                }
                Integer num9 = num4;
                a0.c.y(composer2, qVarModifierMaterializerOf2, composer2, num9, 2058660585);
                if (PlayerScreen$lambda$3(state).getBackdropUrl() != null) {
                    composer2.startReplaceGroup(-133219596);
                    companion8 = companion5;
                    num5 = num9;
                    SingletonAsyncImageKt.m6053AsyncImage3HmZ8SU(PlayerScreen$lambda$3(state).getBackdropUrl(), null, SizeKt.fillMaxSize$default(companion6, f11, i13, finiteAnimationSpec), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, composer, 1573296, 952);
                    companion7 = companion6;
                    i14 = 6;
                    BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxSize$default(companion7, f11, i13, finiteAnimationSpec), Color.m3471copywmQWz5c$default(companion13.m3498getBlack0d7_KjU(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer, 6);
                    composer.endReplaceGroup();
                } else {
                    num5 = num9;
                    companion7 = companion6;
                    companion8 = companion5;
                    i14 = 6;
                    composer2.startReplaceGroup(-132740057);
                    composer2.endReplaceGroup();
                }
                String logoUrl = PlayerScreen$lambda$3(state).getLogoUrl();
                String title = PlayerScreen$lambda$3(state).getTitle();
                ?? streamProgress = PlayerScreen$lambda$3(state).getShowLoadingStats() ? PlayerScreen$lambda$3(state).getStreamProgress() : finiteAnimationSpec;
                String strPlayerScreen$lambda$206 = PlayerScreen$lambda$206(mutableState16);
                if (strPlayerScreen$lambda$206 == null) {
                    ?? PlayerScreen$lambda$203 = PlayerScreen$lambda$203(mutableState17);
                    if (PlayerScreen$lambda$3(state).getSelectedStreamUrl() == null) {
                        PlayerScreen$lambda$203 = finiteAnimationSpec;
                    }
                    if (PlayerScreen$lambda$203 != 0) {
                        List<String> pendingSubtitleAddons = PlayerScreen$lambda$3(state).getPendingSubtitleAddons();
                        z15 = false;
                        z15 = false;
                        if (!kotlin.text.u.P(PlayerScreen$lambda$203, "Loading subtitles", false) || pendingSubtitleAddons.isEmpty()) {
                            N = PlayerScreen$lambda$203;
                        } else {
                            String strU0 = kotlin.collections.x.u0(kotlin.collections.x.X0(pendingSubtitleAddons, 2), ", ", null, null, null, 62);
                            int size = pendingSubtitleAddons.size() - 2;
                            N = androidx.compose.material3.d.n("Loading subtitles… (", strU0, size > 0 ? a0.c.i(size, " +") : "", ")");
                        }
                    } else {
                        z15 = false;
                        N = finiteAnimationSpec;
                    }
                    if (N == 0) {
                        strPlayerScreen$lambda$206 = PlayerScreen$lambda$3(state).getStreamLoadPhase();
                        z15 = z15;
                    }
                    PulsingLogo(logoUrl, title, null, streamProgress, N, composer, 0, 4);
                    composer2 = composer;
                    com.arflix.tv.data.repository.g.q(composer2);
                    r12 = z15;
                } else {
                    z15 = false;
                }
                N = strPlayerScreen$lambda$206;
                PulsingLogo(logoUrl, title, null, streamProgress, N, composer, 0, 4);
                composer2 = composer;
                com.arflix.tv.data.repository.g.q(composer2);
                r12 = z15;
            } else {
                composer2.startReplaceGroup(1410376622);
                composer2.endReplaceGroup();
                companion7 = companion6;
                companion8 = companion5;
                num5 = num4;
                i14 = 6;
                r12 = 0;
            }
            if (PlayerScreen$lambda$23(mutableState18) && PlayerScreen$lambda$26(mutableState15) && PlayerScreen$lambda$3(state).getSelectedStreamUrl() != null) {
                composer2.startReplaceGroup(1410634015);
                Modifier modifierFillMaxSize$default4 = SizeKt.fillMaxSize$default(companion7, f11, i13, finiteAnimationSpec);
                Alignment center2 = companion3.getCenter();
                composer2.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center2, r12, composer2, i14);
                i15 = -1323940314;
                composer2.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer2, r12);
                CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor3 = companion8.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierFillMaxSize$default4);
                if (composer2.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor3);
                } else {
                    composer2.useNode();
                }
                Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composer2);
                ComposeUiNode.Companion companion16 = companion8;
                r7.p pVarU3 = a0.c.u(companion16, composerM2991constructorimpl3, measurePolicyRememberBoxMeasurePolicy2, composerM2991constructorimpl3, currentCompositionLocalMap3);
                if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
                }
                Integer num10 = num5;
                a0.c.y(composer2, qVarModifierMaterializerOf3, composer2, num10, 2058660585);
                companion8 = companion16;
                num6 = num10;
                PulsingLogo(PlayerScreen$lambda$3(state).getLogoUrl(), PlayerScreen$lambda$3(state).getTitle(), null, null, null, composer, 0, 28);
                composer2 = composer;
                com.arflix.tv.data.repository.g.q(composer2);
            } else {
                num6 = num5;
                i15 = -1323940314;
                composer2.startReplaceGroup(1410863694);
                composer2.endReplaceGroup();
            }
            if (PlayerScreen$lambda$26(mutableState15)) {
                composer2.startReplaceGroup(1411075145);
                SkipInterval activeSkipInterval = PlayerScreen$lambda$3(state).getActiveSkipInterval();
                boolean skipIntervalDismissed = PlayerScreen$lambda$3(state).getSkipIntervalDismissed();
                boolean zPlayerScreen$lambda$32 = PlayerScreen$lambda$32(mutableState3);
                boolean zChanged2 = composer2.changed(activeSkipInterval) | composer2.changedInstance(exoPlayer) | composer2.changedInstance(playerViewModel);
                Object objRememberedValue5 = composer2.rememberedValue();
                if (zChanged2 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue5 = new j0(activeSkipInterval, exoPlayer, playerViewModel, r12);
                    composer2.updateRememberedValue(objRememberedValue5);
                }
                boxScopeInstance2 = boxScopeInstance;
                i16 = -1323940314;
                SkipIntroButtonKt.SkipIntroButton(activeSkipInterval, skipIntervalDismissed, zPlayerScreen$lambda$32, (r7.a) objRememberedValue5, focusRequester4, PaddingKt.m533paddingqDBjuR0$default(ZIndexModifierKt.zIndex(boxScopeInstance2.align(companion7, companion3.getBottomEnd()), 5.0f), 0.0f, 0.0f, Dp.m5678constructorimpl(z ? 24 : 48), Dp.m5678constructorimpl(PlayerScreen$lambda$32(mutableState3) ? 90 : 32), 3, null), composer2, 24576, 0);
                composer2.endReplaceGroup();
            } else {
                i16 = i15;
                boxScopeInstance2 = boxScopeInstance;
                composer2.startReplaceGroup(1411879502);
                composer2.endReplaceGroup();
            }
            float f12 = 16;
            Composer composer6 = composer;
            AnimatedVisibilityKt.AnimatedVisibility((boolean) ((PlayerScreen$lambda$26(mutableState15) && PlayerScreen$lambda$3(state).isAiTranslating() && PlayerScreen$lambda$362$2$5(FlowExtKt.collectAsStateWithLifecycle(playerViewModel.isTranslatingLive(), (androidx.lifecycle.y) null, (androidx.lifecycle.q) null, (d7.j) null, composer, 0, 7))) ? 1 : r12), ZIndexModifierKt.zIndex(PaddingKt.m533paddingqDBjuR0$default(boxScopeInstance2.align(companion7, companion3.getTopEnd()), 0.0f, Dp.m5678constructorimpl(12), Dp.m5678constructorimpl(f12), 0.0f, 9, null), 6.0f), EnterExitTransitionKt.fadeIn$default(finiteAnimationSpec, f11, 3, finiteAnimationSpec), EnterExitTransitionKt.fadeOut$default(finiteAnimationSpec, f11, 3, finiteAnimationSpec), (String) null, ComposableSingletons$PlayerScreenKt.INSTANCE.getLambda$1581803292$app_playRelease(), composer6, 200064, 16);
            if (PlayerScreen$lambda$26(mutableState15) && PlayerScreen$lambda$3(state).isFindingBestMatch()) {
                composer6.startReplaceGroup(1413881699);
                Modifier modifierZIndex = ZIndexModifierKt.zIndex(PaddingKt.m530paddingVpY3zN4(com.arflix.tv.data.repository.g.l(20, PaddingKt.m533paddingqDBjuR0$default(boxScopeInstance2.align(companion7, companion3.getTopCenter()), 0.0f, Dp.m5678constructorimpl(f12), 0.0f, 0.0f, 13, null), Color.m3471copywmQWz5c$default(companion13.m3498getBlack0d7_KjU(), 0.72f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(f12), Dp.m5678constructorimpl(8)), 6.0f);
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(10, Arrangement.INSTANCE, composer6, 693286680), companion3.getCenterVertically(), composer6, 54);
                composer6.startReplaceableGroup(i16);
                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer6, r12);
                CompositionLocalMap currentCompositionLocalMap4 = composer6.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor4 = companion8.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierZIndex);
                if (composer6.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer6.startReusableNode();
                if (composer6.getInserting()) {
                    composer6.createNode(constructor4);
                } else {
                    composer6.useNode();
                }
                Composer composerM2991constructorimpl4 = Updater.m2991constructorimpl(composer6);
                ComposeUiNode.Companion companion17 = companion8;
                r7.p pVarU4 = a0.c.u(companion17, composerM2991constructorimpl4, measurePolicyRowMeasurePolicy, composerM2991constructorimpl4, currentCompositionLocalMap4);
                if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                    a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
                }
                a0.c.y(composer6, qVarModifierMaterializerOf4, composer6, num6, 2058660585);
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                boxScopeInstance3 = boxScopeInstance2;
                ProgressIndicatorKt.m1762CircularProgressIndicatorLxG7B9w(SizeKt.m578size3ABfNKs(companion7, Dp.m5678constructorimpl(14)), ColorKt.Color(4286499056L), Dp.m5678constructorimpl(2), 0L, 0, composer, 438, 24);
                String matchStatusText = PlayerScreen$lambda$3(state).getMatchStatusText();
                if (kotlin.text.o.h0(matchStatusText)) {
                    matchStatusText = "Searching for a match…";
                }
                companion9 = companion3;
                companion8 = companion17;
                num7 = num6;
                companion10 = companion7;
                TextKt.m6020Text4IGK_g(matchStatusText, null, Color.m3471copywmQWz5c$default(companion13.m3509getWhite0d7_KjU(), 0.9f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getLabelLarge(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
                composer6 = composer;
                com.arflix.tv.data.repository.g.q(composer6);
            } else {
                companion9 = companion3;
                boxScopeInstance3 = boxScopeInstance2;
                companion10 = companion7;
                num7 = num6;
                composer6.startReplaceGroup(1415009262);
                composer6.endReplaceGroup();
            }
            String aiErrorToast = PlayerScreen$lambda$3(state).getAiErrorToast();
            if (aiErrorToast == null) {
                composer6.startReplaceGroup(1415090698);
                composer6.endReplaceGroup();
                playerViewModel2 = playerViewModel;
            } else {
                composer6.startReplaceGroup(1415090699);
                ToastType toastType = ToastType.ERROR;
                playerViewModel2 = playerViewModel;
                boolean zChangedInstance4 = composer6.changedInstance(playerViewModel2);
                Object objRememberedValue6 = composer6.rememberedValue();
                if (zChangedInstance4 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue6 = new j(playerViewModel2, 3);
                    composer6.updateRememberedValue(objRememberedValue6);
                }
                ToastKt.Toast(aiErrorToast, toastType, true, 5000L, (r7.a) objRememberedValue6, composer6, 3504, 0);
                composer6.endReplaceGroup();
            }
            String matchToast = PlayerScreen$lambda$3(state).getMatchToast();
            if (matchToast == null) {
                composer6.startReplaceGroup(1415585706);
                composer6.endReplaceGroup();
                boxScopeInstance4 = boxScopeInstance3;
                companion11 = companion10;
            } else {
                composer6.startReplaceGroup(1415585707);
                boolean zChangedInstance5 = composer6.changedInstance(playerViewModel2);
                Object objRememberedValue7 = composer6.rememberedValue();
                if (zChangedInstance5 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                    obj = null;
                    objRememberedValue7 = new PlayerScreenKt$PlayerScreen$49$3$8$1$1(playerViewModel2, null);
                    composer6.updateRememberedValue(objRememberedValue7);
                } else {
                    obj = null;
                }
                EffectsKt.LaunchedEffect(matchToast, (r7.p<? super ka.k0, ? super d7.d<? super x6.t0>, ? extends Object>) objRememberedValue7, composer6, 0);
                BoxScopeInstance boxScopeInstance6 = boxScopeInstance3;
                Modifier.Companion companion18 = companion10;
                Modifier modifierZIndex2 = ZIndexModifierKt.zIndex(PaddingKt.m530paddingVpY3zN4(com.arflix.tv.data.repository.g.l(20, PaddingKt.m533paddingqDBjuR0$default(boxScopeInstance6.align(companion18, companion9.getTopCenter()), 0.0f, (PlayerScreen$lambda$26(mutableState15) && PlayerScreen$lambda$3(state).isFindingBestMatch()) ? Dp.m5678constructorimpl(60) : Dp.m5678constructorimpl(f12), 0.0f, 0.0f, 13, null), Color.m3471copywmQWz5c$default(companion13.m3498getBlack0d7_KjU(), 0.72f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(f12), Dp.m5678constructorimpl(8)), 6.0f);
                Alignment.Vertical centerVertically = companion9.getCenterVertically();
                composer6.startReplaceableGroup(693286680);
                MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(Arrangement.INSTANCE, centerVertically, composer6, 48, -1323940314);
                int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer6, 0);
                CompositionLocalMap currentCompositionLocalMap5 = composer6.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor5 = companion8.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf5 = LayoutKt.modifierMaterializerOf(modifierZIndex2);
                if (composer6.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer6.startReusableNode();
                if (composer6.getInserting()) {
                    composer6.createNode(constructor5);
                } else {
                    composer6.useNode();
                }
                Composer composerM2991constructorimpl5 = Updater.m2991constructorimpl(composer6);
                r7.p pVarU5 = a0.c.u(companion8, composerM2991constructorimpl5, measurePolicyG, composerM2991constructorimpl5, currentCompositionLocalMap5);
                if (composerM2991constructorimpl5.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                    a0.c.w(currentCompositeKeyHash5, composerM2991constructorimpl5, currentCompositeKeyHash5, pVarU5);
                }
                a0.c.y(composer6, qVarModifierMaterializerOf5, composer6, num7, 2058660585);
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                companion11 = companion18;
                boxScopeInstance4 = boxScopeInstance6;
                TextKt.m6020Text4IGK_g(matchToast, null, Color.m3471copywmQWz5c$default(companion13.m3509getWhite0d7_KjU(), 0.9f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, MaterialTheme.INSTANCE.getTypography(composer6, MaterialTheme.$stable).getLabelLarge(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
                com.arflix.tv.data.repository.g.q(composer);
            }
            AnimatedVisibilityKt.AnimatedVisibility((!PlayerScreen$lambda$26(mutableState15) || !PlayerScreen$lambda$32(mutableState3) || PlayerScreen$lambda$109(mutableState) || PlayerScreen$lambda$112(mutableState2) || PlayerScreen$lambda$277(mutableState13)) ? false : true, (Modifier) null, EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, 2, null), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), 0.0f, 2, null), (String) null, ComposableLambdaKt.rememberComposableLambda(69482117, true, new r7.q() { // from class: com.arflix.tv.ui.screens.player.k0
                @Override // r7.q
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    int iIntValue = ((Integer) obj4).intValue();
                    return PlayerScreenKt.PlayerScreen$lambda$362$2$9(z, mediaType, num, num2, j10, mutableState15, mutableState19, mutableState18, state, str5, z11, z12, mutableLongState4, mutableLongState6, state3, z5, castManager, context, z13, z14, focusRequester3, state2, list, k0Var, focusRequester2, focusRequester5, focusRequester6, lVar, focusRequester7, exoPlayer, focusRequester8, str6, focusRequester9, aVar4, focusRequester10, rVar, focusRequester11, aVar5, mutableIntState15, mutableIntState7, mutableIntState8, mutableIntState9, mutableIntState10, mutableState, focusRequester, focusRequester12, mutableState7, mutableIntState3, mutableState2, mutableState3, focusRequester4, mutableState4, aVar6, mutableState20, mutableLongState7, mutableState21, mutableFloatState, (AnimatedVisibilityScope) obj2, (Composer) obj3, iIntValue);
                }
            }, composer, 54), composer, 200064, 18);
            Modifier.Companion companion19 = companion11;
            BoxScopeInstance boxScopeInstance7 = boxScopeInstance4;
            AnimatedVisibilityKt.AnimatedVisibility(PlayerScreen$lambda$162(mutableState7) && PlayerScreen$lambda$26(mutableState15), ZIndexModifierKt.zIndex(boxScopeInstance7.align(companion19, companion9.getCenter()), 8.0f), EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, 2, null), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, 2, null), (String) null, ComposableLambdaKt.rememberComposableLambda(-1672741724, true, new h0(mutableIntState3, mutableLongState, mutableIntState4, mutableIntState5, 2), composer, 54), composer, 200064, 16);
            PlayerViewModel playerViewModel3 = playerViewModel;
            ka.k0 k0Var2 = k0Var;
            AnimatedVisibilityKt.AnimatedVisibility(PlayerScreen$lambda$109(mutableState), (Modifier) null, EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, 2, null), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), 0.0f, 2, null), (String) null, ComposableLambdaKt.rememberComposableLambda(880001731, true, new r7.q() { // from class: com.arflix.tv.ui.screens.player.m0
                @Override // r7.q
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    int iIntValue = ((Integer) obj4).intValue();
                    return PlayerScreenKt.PlayerScreen$lambda$362$2$11(list, playerViewModel, state, k0Var, exoPlayer, mutableState8, mutableIntState11, mutableIntState6, mutableIntState7, mutableIntState9, mutableIntState10, mutableIntState8, mutableState, mutableState3, focusRequester3, mutableState9, (AnimatedVisibilityScope) obj2, (Composer) obj3, iIntValue);
                }
            }, composer, 54), composer, 200064, 18);
            boolean zPlayerScreen$lambda$112 = PlayerScreen$lambda$112(mutableState2);
            List<StreamSource> streams = PlayerScreen$lambda$3(state).getStreams();
            StreamSource selectedStream = PlayerScreen$lambda$3(state).getSelectedStream();
            boolean zIsLoadingStreams = PlayerScreen$lambda$3(state).isLoadingStreams();
            boolean z17 = !PlayerScreen$lambda$3(state).isSetupError();
            List<String> addonOrderedIds = PlayerScreen$lambda$3(state).getAddonOrderedIds();
            String title2 = PlayerScreen$lambda$3(state).getTitle();
            if (num != null && num2 != null) {
                str8 = "S" + num + " E" + num2;
            }
            boolean zChangedInstance6 = composer.changedInstance(playerViewModel3) | composer.changed(state);
            Object objRememberedValue8 = composer.rememberedValue();
            if (zChangedInstance6 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new v1(playerViewModel3, state, 4);
                composer.updateRememberedValue(objRememberedValue8);
            }
            r7.l lVar3 = (r7.l) objRememberedValue8;
            boolean zChangedInstance7 = composer.changedInstance(playerViewModel3) | composer.changedInstance(exoPlayer) | composer.changedInstance(k0Var2);
            Object objRememberedValue9 = composer.rememberedValue();
            if (zChangedInstance7 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                n0Var = new n0(playerViewModel3, exoPlayer, k0Var2, mutableState22, mutableState23, mutableState24, mutableState25, mutableIntState16, mutableState26, mutableState27, mutableState28, mutableIntState17, mutableState2, mutableState3, focusRequester5);
                composer3 = composer;
                playerViewModel3 = playerViewModel3;
                k0Var2 = k0Var2;
                composer3.updateRememberedValue(n0Var);
            } else {
                n0Var = objRememberedValue9;
                composer3 = composer;
            }
            r7.l lVar4 = (r7.l) n0Var;
            boolean zChangedInstance8 = composer3.changedInstance(k0Var2);
            Object objRememberedValue10 = composer3.rememberedValue();
            if (zChangedInstance8 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue10 = new z(k0Var2, mutableState2, mutableState3, focusRequester5, 1);
                composer3.updateRememberedValue(objRememberedValue10);
            }
            StreamSelectorKt.StreamSelector(zPlayerScreen$lambda$112, streams, selectedStream, zIsLoadingStreams, title2, str8, z17, addonOrderedIds, 0, 0, 0L, false, null, lVar3, lVar4, (r7.a) objRememberedValue10, composer3, 0, 0, 7936);
            boolean zPlayerScreen$lambda$115 = PlayerScreen$lambda$115(mutableState6);
            String title3 = PlayerScreen$lambda$3(state).getTitle();
            EpisodeIdentity episodeIdentityPlayerScreen$lambda$118 = PlayerScreen$lambda$118(mutableState29);
            String strI = a0.c.i(episodeIdentityPlayerScreen$lambda$118 != null ? episodeIdentityPlayerScreen$lambda$118.getDisplayEpisode() : 0, "Episode ");
            EpisodeIdentity episodeIdentityPlayerScreen$lambda$1182 = PlayerScreen$lambda$118(mutableState29);
            int displaySeason = episodeIdentityPlayerScreen$lambda$1182 != null ? episodeIdentityPlayerScreen$lambda$1182.getDisplaySeason() : 0;
            EpisodeIdentity episodeIdentityPlayerScreen$lambda$1183 = PlayerScreen$lambda$118(mutableState29);
            int displayEpisode = episodeIdentityPlayerScreen$lambda$1183 != null ? episodeIdentityPlayerScreen$lambda$1183.getDisplayEpisode() : 0;
            String backdropUrl = PlayerScreen$lambda$3(state).getBackdropUrl();
            Integer numValueOf = Integer.valueOf(mutableIntState.getIntValue());
            Object objRememberedValue11 = composer3.rememberedValue();
            if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                z16 = true;
                objRememberedValue11 = new q(mutableIntState, true ? 1 : 0);
                composer3.updateRememberedValue(objRememberedValue11);
            } else {
                z16 = true;
            }
            NextEpisodeOverlayKt.NextEpisodeOverlay(zPlayerScreen$lambda$115, title3, strI, displaySeason, displayEpisode, backdropUrl, 10, numValueOf, (r7.l) objRememberedValue11, aVar2, aVar3, composer3, 102236160, 0, 0);
            float f13 = 48;
            Composer composer7 = composer3;
            AnimatedVisibilityKt.AnimatedVisibility(PlayerScreen$lambda$79(mutableState12), PaddingKt.m533paddingqDBjuR0$default(boxScopeInstance7.align(companion19, companion9.getCenterEnd()), 0.0f, 0.0f, Dp.m5678constructorimpl(f13), 0.0f, 11, null), EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, 2, null), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), 0.0f, 2, null), (String) null, ComposableLambdaKt.rememberComposableLambda(-862222110, z16, new r7.q() { // from class: com.arflix.tv.ui.screens.player.f0
                @Override // r7.q
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    int iIntValue = ((Integer) obj4).intValue();
                    return PlayerScreenKt.PlayerScreen$lambda$362$2$16(i10, mutableState11, mutableIntState13, j10, (AnimatedVisibilityScope) obj2, (Composer) obj3, iIntValue);
                }
            }, composer3, 54), composer7, 200064, 16);
            AnimatedVisibilityKt.AnimatedVisibility(PlayerScreen$lambda$82(mutableState30), boxScopeInstance7.align(companion19, companion9.getCenter()), EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, 2, null), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), 0.0f, 2, null), (String) null, ComposableLambdaKt.rememberComposableLambda(1690521345, z16, new g0(str6, 0), composer3, 54), composer7, 200064, 16);
            AnimatedVisibilityKt.AnimatedVisibility(PlayerScreen$lambda$53(mutableState10), PaddingKt.m533paddingqDBjuR0$default(boxScopeInstance7.align(companion19, companion9.getBottomCenter()), 0.0f, 0.0f, 0.0f, Dp.m5678constructorimpl(f13), 7, null), EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, 2, null), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), 0.0f, 2, null), (String) null, ComposableLambdaKt.rememberComposableLambda(-51702496, z16, new h0(mutableIntState12, mutableLongState4, mutableLongState5, mutableLongState6, 0), composer3, 54), composer3, 200064, 16);
            AnimatedVisibilityKt.AnimatedVisibility(PlayerScreen$lambda$3(state).getError() != null ? z16 : false, (Modifier) null, EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, 2, null), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(200, 0, null, 6, null), 0.0f, 2, null), (String) null, ComposableLambdaKt.rememberComposableLambda(-1793926337, z16, new h0(state, playerViewModel3, aVar, mutableIntState2), composer3, 54), composer3, 200064, 18);
            if (androidx.compose.foundation.c.D(composer)) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0137  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final com.arflix.tv.ui.screens.player.FullViewportSubtitlePlayerView PlayerScreen$lambda$362$2$0$0(androidx.media3.exoplayer.ExoPlayer r14, androidx.compose.runtime.MutableIntState r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, boolean r20, androidx.compose.runtime.MutableState r21, android.content.Context r22) {
        /*
            Method dump skipped, instruction units count: 336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.PlayerScreen$lambda$362$2$0$0(androidx.media3.exoplayer.ExoPlayer, androidx.compose.runtime.MutableIntState, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, androidx.compose.runtime.MutableState, android.content.Context):com.arflix.tv.ui.screens.player.FullViewportSubtitlePlayerView");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 PlayerScreen$lambda$362$2$1$0(androidx.media3.exoplayer.ExoPlayer r1, androidx.compose.runtime.MutableIntState r2, androidx.compose.runtime.MutableState r3, java.lang.String r4, androidx.compose.runtime.MutableState r5, androidx.compose.runtime.MutableIntState r6, androidx.compose.runtime.MutableIntState r7, androidx.compose.runtime.State r8, com.arflix.tv.ui.screens.player.FullViewportSubtitlePlayerView r9) {
        /*
            r0 = 1
            r9.setKeepScreenOn(r0)
            r9.setPlayer(r1)
            int r1 = PlayerScreen$lambda$144(r2)
            r9.setResizeMode(r1)
            boolean r1 = PlayerScreen$lambda$177(r3)
            r9.setUseVideoFrameForSubtitles(r1)
            androidx.media3.ui.SubtitleView r1 = r9.getSubtitleView()
            if (r1 == 0) goto L98
            int r2 = r4.hashCode()
            r3 = 73190171(0x45ccb1b, float:2.5954137E-36)
            if (r2 == r3) goto L47
            r3 = 79996135(0x4c4a4e7, float:4.6230817E-36)
            if (r2 == r3) goto L3b
            r3 = 1536020203(0x5b8dceeb, float:7.983096E16)
            if (r2 == r3) goto L2f
            goto L4f
        L2f:
            java.lang.String r2 = "Extra Large"
            boolean r2 = r4.equals(r2)
            if (r2 != 0) goto L38
            goto L4f
        L38:
            r2 = 1108344832(0x42100000, float:36.0)
            goto L54
        L3b:
            java.lang.String r2 = "Small"
            boolean r2 = r4.equals(r2)
            if (r2 != 0) goto L44
            goto L4f
        L44:
            r2 = 1099956224(0x41900000, float:18.0)
            goto L54
        L47:
            java.lang.String r2 = "Large"
            boolean r2 = r4.equals(r2)
            if (r2 != 0) goto L52
        L4f:
            r2 = 1103101952(0x41c00000, float:24.0)
            goto L54
        L52:
            r2 = 1106247680(0x41f00000, float:30.0)
        L54:
            boolean r3 = PlayerScreen$lambda$277(r5)
            if (r3 == 0) goto L5e
            r3 = 1053609165(0x3ecccccd, float:0.4)
            goto L60
        L5e:
            r3 = 1065353216(0x3f800000, float:1.0)
        L60:
            int r4 = PlayerScreen$lambda$171(r6)
            float r4 = (float) r4
            r5 = 1120403456(0x42c80000, float:100.0)
            float r4 = r4 / r5
            float r4 = r4 * r2
            float r4 = r4 * r3
            r2 = 2
            r1.setFixedTextSize(r2, r4)
            int r2 = PlayerScreen$lambda$174(r7)
            float r2 = (float) r2
            float r2 = r2 / r5
            r3 = 0
            r4 = 1056964608(0x3f000000, float:0.5)
            float r2 = qb.d.m(r2, r3, r4)
            r1.setBottomPaddingFraction(r2)
            com.arflix.tv.ui.screens.player.PlayerUiState r2 = PlayerScreen$lambda$3(r8)
            boolean r2 = r2.isFindingBestMatch()
            if (r2 == 0) goto L94
            com.arflix.tv.ui.screens.player.PlayerUiState r2 = PlayerScreen$lambda$3(r8)
            boolean r2 = r2.isAiTranslating()
            if (r2 != 0) goto L94
            r2 = 4
            goto L95
        L94:
            r2 = 0
        L95:
            r1.setVisibility(r2)
        L98:
            x6.t0 r1 = x6.t0.f22605a
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.PlayerScreen$lambda$362$2$1$0(androidx.media3.exoplayer.ExoPlayer, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableState, java.lang.String, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.State, com.arflix.tv.ui.screens.player.FullViewportSubtitlePlayerView):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$10(MutableIntState mutableIntState, final MutableLongState mutableLongState, final MutableIntState mutableIntState2, final MutableIntState mutableIntState3, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1672741724, i10, -1, "com.arflix.tv.ui.screens.player.PlayerScreen.<anonymous>.<anonymous>.<anonymous> (PlayerScreen.kt:3852)");
        }
        int intValue = mutableIntState.getIntValue();
        long longValue = mutableLongState.getLongValue();
        int intValue2 = mutableIntState2.getIntValue();
        int intValue3 = mutableIntState3.getIntValue();
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new q(mutableIntState, 0);
            composer.updateRememberedValue(objRememberedValue);
        }
        r7.l lVar = (r7.l) objRememberedValue;
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            final int i11 = 0;
            objRememberedValue2 = new r7.a() { // from class: com.arflix.tv.ui.screens.player.r
                @Override // r7.a
                public final Object invoke() {
                    switch (i11) {
                        case 0:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$1$0(mutableLongState);
                        default:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$2$0(mutableLongState);
                    }
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        r7.a aVar = (r7.a) objRememberedValue2;
        Object objRememberedValue3 = composer.rememberedValue();
        if (objRememberedValue3 == companion.getEmpty()) {
            final int i12 = 1;
            objRememberedValue3 = new r7.a() { // from class: com.arflix.tv.ui.screens.player.r
                @Override // r7.a
                public final Object invoke() {
                    switch (i12) {
                        case 0:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$1$0(mutableLongState);
                        default:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$2$0(mutableLongState);
                    }
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        r7.a aVar2 = (r7.a) objRememberedValue3;
        Object objRememberedValue4 = composer.rememberedValue();
        if (objRememberedValue4 == companion.getEmpty()) {
            final int i13 = 0;
            objRememberedValue4 = new r7.a() { // from class: com.arflix.tv.ui.screens.player.s
                @Override // r7.a
                public final Object invoke() {
                    switch (i13) {
                        case 0:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$3$0(mutableIntState2);
                        case 1:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$4$0(mutableIntState2);
                        case 2:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$5$0(mutableIntState2);
                        default:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$6$0(mutableIntState2);
                    }
                }
            };
            composer.updateRememberedValue(objRememberedValue4);
        }
        r7.a aVar3 = (r7.a) objRememberedValue4;
        Object objRememberedValue5 = composer.rememberedValue();
        if (objRememberedValue5 == companion.getEmpty()) {
            final int i14 = 1;
            objRememberedValue5 = new r7.a() { // from class: com.arflix.tv.ui.screens.player.s
                @Override // r7.a
                public final Object invoke() {
                    switch (i14) {
                        case 0:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$3$0(mutableIntState2);
                        case 1:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$4$0(mutableIntState2);
                        case 2:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$5$0(mutableIntState2);
                        default:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$6$0(mutableIntState2);
                    }
                }
            };
            composer.updateRememberedValue(objRememberedValue5);
        }
        r7.a aVar4 = (r7.a) objRememberedValue5;
        Object objRememberedValue6 = composer.rememberedValue();
        if (objRememberedValue6 == companion.getEmpty()) {
            final int i15 = 2;
            objRememberedValue6 = new r7.a() { // from class: com.arflix.tv.ui.screens.player.s
                @Override // r7.a
                public final Object invoke() {
                    switch (i15) {
                        case 0:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$3$0(mutableIntState3);
                        case 1:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$4$0(mutableIntState3);
                        case 2:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$5$0(mutableIntState3);
                        default:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$6$0(mutableIntState3);
                    }
                }
            };
            composer.updateRememberedValue(objRememberedValue6);
        }
        r7.a aVar5 = (r7.a) objRememberedValue6;
        Object objRememberedValue7 = composer.rememberedValue();
        if (objRememberedValue7 == companion.getEmpty()) {
            final int i16 = 3;
            objRememberedValue7 = new r7.a() { // from class: com.arflix.tv.ui.screens.player.s
                @Override // r7.a
                public final Object invoke() {
                    switch (i16) {
                        case 0:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$3$0(mutableIntState3);
                        case 1:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$4$0(mutableIntState3);
                        case 2:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$5$0(mutableIntState3);
                        default:
                            return PlayerScreenKt.PlayerScreen$lambda$362$2$10$6$0(mutableIntState3);
                    }
                }
            };
            composer.updateRememberedValue(objRememberedValue7);
        }
        PlayerSubtitleSettingsPanel(intValue, longValue, intValue2, intValue3, lVar, aVar, aVar2, aVar3, aVar4, aVar5, (r7.a) objRememberedValue7, composer, 920346624, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$10$0$0(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$10$1$0(MutableLongState mutableLongState) {
        long longValue = mutableLongState.getLongValue() - 100;
        if (longValue < -10000) {
            longValue = -10000;
        }
        mutableLongState.setLongValue(longValue);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$10$2$0(MutableLongState mutableLongState) {
        long longValue = mutableLongState.getLongValue() + 100;
        if (longValue > 10000) {
            longValue = 10000;
        }
        mutableLongState.setLongValue(longValue);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$10$3$0(MutableIntState mutableIntState) {
        int intValue = mutableIntState.getIntValue() - 10;
        if (intValue < 50) {
            intValue = 50;
        }
        mutableIntState.setIntValue(intValue);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$10$4$0(MutableIntState mutableIntState) {
        int intValue = mutableIntState.getIntValue() + 10;
        if (intValue > 300) {
            intValue = 300;
        }
        mutableIntState.setIntValue(intValue);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$10$5$0(MutableIntState mutableIntState) {
        int intValue = mutableIntState.getIntValue() - 1;
        if (intValue < 0) {
            intValue = 0;
        }
        mutableIntState.setIntValue(intValue);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$10$6$0(MutableIntState mutableIntState) {
        int intValue = mutableIntState.getIntValue() + 1;
        if (intValue > 50) {
            intValue = 50;
        }
        mutableIntState.setIntValue(intValue);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$11(List list, PlayerViewModel playerViewModel, State state, ka.k0 k0Var, ExoPlayer exoPlayer, MutableState mutableState, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableIntState mutableIntState3, MutableIntState mutableIntState4, MutableIntState mutableIntState5, MutableIntState mutableIntState6, MutableState mutableState2, MutableState mutableState3, FocusRequester focusRequester, MutableState mutableState4, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        String source;
        int i11;
        PlayerViewModel playerViewModel2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(880001731, i10, -1, "com.arflix.tv.ui.screens.player.PlayerScreen.<anonymous>.<anonymous>.<anonymous> (PlayerScreen.kt:3873)");
        }
        List<Subtitle> subtitles = PlayerScreen$lambda$3(state).getSubtitles();
        Subtitle selectedSubtitle = PlayerScreen$lambda$3(state).getSelectedSubtitle();
        boolean zIsAiTranslating = PlayerScreen$lambda$3(state).isAiTranslating();
        boolean zIsAiAvailable = PlayerScreen$lambda$3(state).isAiAvailable();
        String aiTargetLanguageName = PlayerScreen$lambda$3(state).getAiTargetLanguageName();
        String matchLanguageName = PlayerScreen$lambda$3(state).getMatchLanguageName();
        List<AudioTrackInfo> listPlayerScreen$lambda$181 = PlayerScreen$lambda$181(mutableState);
        int intValue = mutableIntState.getIntValue();
        int intValue2 = mutableIntState2.getIntValue();
        int intValue3 = mutableIntState3.getIntValue();
        StreamSource selectedStream = PlayerScreen$lambda$3(state).getSelectedStream();
        if (selectedStream == null || (source = selectedStream.getSource()) == null) {
            source = "";
        }
        String str = source;
        int intValue4 = mutableIntState4.getIntValue();
        int intValue5 = mutableIntState5.getIntValue();
        int intValue6 = mutableIntState6.getIntValue();
        boolean zIsLiveAudioTranslating = PlayerScreen$lambda$3(state).isLiveAudioTranslating();
        boolean zIsFindingBestMatch = PlayerScreen$lambda$3(state).isFindingBestMatch();
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new v1(mutableIntState2, mutableIntState3, 6);
            composer.updateRememberedValue(objRememberedValue);
        }
        r7.l lVar = (r7.l) objRememberedValue;
        boolean zChangedInstance = composer.changedInstance(playerViewModel) | composer.changed(state) | composer.changedInstance(k0Var);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
            com.arflix.tv.ui.components.x xVar = new com.arflix.tv.ui.components.x(playerViewModel, k0Var, state, mutableState2, mutableState3, focusRequester, 3);
            composer.updateRememberedValue(xVar);
            objRememberedValue2 = xVar;
        }
        r7.l lVar2 = (r7.l) objRememberedValue2;
        boolean zChangedInstance2 = composer.changedInstance(exoPlayer) | composer.changedInstance(k0Var);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue3 == companion.getEmpty()) {
            i11 = intValue2;
            playerViewModel2 = playerViewModel;
            i0 i0Var = new i0(exoPlayer, k0Var, mutableState4, mutableState, mutableIntState, mutableState2, mutableState3, focusRequester);
            composer.updateRememberedValue(i0Var);
            objRememberedValue3 = i0Var;
        } else {
            i11 = intValue2;
            playerViewModel2 = playerViewModel;
        }
        r7.l lVar3 = (r7.l) objRememberedValue3;
        boolean zChangedInstance3 = composer.changedInstance(playerViewModel2);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChangedInstance3 || objRememberedValue4 == companion.getEmpty()) {
            objRememberedValue4 = new j(playerViewModel2, 0);
            composer.updateRememberedValue(objRememberedValue4);
        }
        r7.a aVar = (r7.a) objRememberedValue4;
        boolean zChangedInstance4 = composer.changedInstance(playerViewModel2);
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChangedInstance4 || objRememberedValue5 == companion.getEmpty()) {
            objRememberedValue5 = new j(playerViewModel2, 1);
            composer.updateRememberedValue(objRememberedValue5);
        }
        r7.a aVar2 = (r7.a) objRememberedValue5;
        boolean zChangedInstance5 = composer.changedInstance(playerViewModel2);
        Object objRememberedValue6 = composer.rememberedValue();
        if (zChangedInstance5 || objRememberedValue6 == companion.getEmpty()) {
            objRememberedValue6 = new j(playerViewModel2, 2);
            composer.updateRememberedValue(objRememberedValue6);
        }
        r7.a aVar3 = (r7.a) objRememberedValue6;
        boolean zChangedInstance6 = composer.changedInstance(k0Var);
        Object objRememberedValue7 = composer.rememberedValue();
        if (zChangedInstance6 || objRememberedValue7 == companion.getEmpty()) {
            objRememberedValue7 = new z(k0Var, mutableState2, mutableState3, focusRequester, 0);
            composer.updateRememberedValue(objRememberedValue7);
        }
        SubtitleMenu(subtitles, selectedSubtitle, zIsAiTranslating, zIsAiAvailable, aiTargetLanguageName, matchLanguageName, zIsLiveAudioTranslating, zIsFindingBestMatch, listPlayerScreen$lambda$181, intValue, i11, intValue3, list, intValue4, intValue5, intValue6, str, lVar, lVar2, lVar3, aVar, aVar2, aVar3, (r7.a) objRememberedValue7, composer, 0, 12582912, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$11$0$0(MutableIntState mutableIntState, MutableIntState mutableIntState2, int i10) {
        mutableIntState.setIntValue(i10);
        mutableIntState2.setIntValue(0);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$11$1$0(PlayerViewModel playerViewModel, ka.k0 k0Var, State state, MutableState mutableState, MutableState mutableState2, FocusRequester focusRequester, int i10) {
        if (i10 == 0) {
            playerViewModel.disableSubtitles();
        } else {
            Subtitle subtitle = (Subtitle) kotlin.collections.x.p0(i10 - 1, PlayerScreen$lambda$3(state).getSubtitles());
            if (subtitle != null) {
                PlayerViewModel.selectSubtitle$default(playerViewModel, subtitle, false, 2, null);
            }
        }
        PlayerScreen$lambda$110(mutableState, false);
        PlayerScreen$lambda$33(mutableState2, true);
        ka.m0.p(k0Var, null, 0, new PlayerScreenKt$PlayerScreen$49$3$11$2$1$2(focusRequester, null), 3);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$11$2$0(ExoPlayer exoPlayer, ka.k0 k0Var, MutableState mutableState, MutableState mutableState2, MutableIntState mutableIntState, MutableState mutableState3, MutableState mutableState4, FocusRequester focusRequester, AudioTrackInfo audioTrackInfo) throws Exception {
        PlayerScreen$lambda$188(mutableState, true);
        Integer numApplyAudioTrackSelection = applyAudioTrackSelection(exoPlayer, audioTrackInfo, PlayerScreen$lambda$181(mutableState2));
        if (numApplyAudioTrackSelection != null) {
            mutableIntState.setIntValue(numApplyAudioTrackSelection.intValue());
        }
        PlayerScreen$lambda$110(mutableState3, false);
        PlayerScreen$lambda$33(mutableState4, true);
        ka.m0.p(k0Var, null, 0, new PlayerScreenKt$PlayerScreen$49$3$11$3$1$2(focusRequester, null), 3);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$11$3$0(PlayerViewModel playerViewModel) {
        playerViewModel.activateAiTranslation();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$11$4$0(PlayerViewModel playerViewModel) {
        playerViewModel.toggleLiveAudioTranslation();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$11$5$0(PlayerViewModel playerViewModel) {
        PlayerViewModel.runFindBestMatch$default(playerViewModel, false, 1, null);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$11$6$0(ka.k0 k0Var, MutableState mutableState, MutableState mutableState2, FocusRequester focusRequester) {
        PlayerScreen$lambda$110(mutableState, false);
        PlayerScreen$lambda$33(mutableState2, true);
        ka.m0.p(k0Var, null, 0, new PlayerScreenKt$PlayerScreen$49$3$11$7$1$1(focusRequester, null), 3);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$12$0(PlayerViewModel playerViewModel, State state, StreamSource streamSource) {
        playerViewModel.prewarmStreamsAround(streamSource, PlayerScreen$lambda$3(state).getStreams());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$13$0(PlayerViewModel playerViewModel, ExoPlayer exoPlayer, ka.k0 k0Var, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableIntState mutableIntState, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableIntState mutableIntState2, MutableState mutableState8, MutableState mutableState9, FocusRequester focusRequester, StreamSource streamSource) {
        PlayerScreen$lambda$197(mutableState, true);
        PlayerScreen$lambda$214(mutableState2, false);
        PlayerScreen$lambda$217(mutableState3, false);
        PlayerScreen$lambda$220(mutableState4, false);
        mutableIntState.setIntValue(0);
        PlayerScreen$lambda$226(mutableState5, false);
        mutableState6.setValue(null);
        PlayerScreen$lambda$256(mutableState7, false);
        mutableIntState2.setIntValue(0);
        playerViewModel.selectStream(streamSource, Long.valueOf(exoPlayer.getCurrentPosition()));
        PlayerScreen$lambda$113(mutableState8, false);
        PlayerScreen$lambda$33(mutableState9, true);
        ka.m0.p(k0Var, null, 0, new PlayerScreenKt$PlayerScreen$49$3$13$1$1(focusRequester, null), 3);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$14$0(ka.k0 k0Var, MutableState mutableState, MutableState mutableState2, FocusRequester focusRequester) {
        PlayerScreen$lambda$113(mutableState, false);
        PlayerScreen$lambda$33(mutableState2, true);
        ka.m0.p(k0Var, null, 0, new PlayerScreenKt$PlayerScreen$49$3$14$1$1(focusRequester, null), 3);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$15$0(MutableIntState mutableIntState, int i10) {
        mutableIntState.setIntValue(i10);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$16(int i10, MutableState mutableState, MutableIntState mutableIntState, long j10, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i11) {
        String strI;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-862222110, i11, -1, "com.arflix.tv.ui.screens.player.PlayerScreen.<anonymous>.<anonymous>.<anonymous> (PlayerScreen.kt:4010)");
        }
        Alignment.Companion companion = Alignment.INSTANCE;
        Alignment.Horizontal centerHorizontally = companion.getCenterHorizontally();
        Modifier.Companion companion2 = Modifier.INSTANCE;
        Color.Companion companion3 = Color.INSTANCE;
        Modifier modifierM = com.arflix.tv.data.repository.g.m(16, com.arflix.tv.data.repository.g.j(12, companion2, Color.m3471copywmQWz5c$default(companion3.m3498getBlack0d7_KjU(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null)), composer, -483455358);
        MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(Arrangement.INSTANCE, centerHorizontally, composer, 48, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion4.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM);
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
        r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyF, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        IconKt.m1610Iconww6aTOc((PlayerScreen$lambda$88(mutableState) || mutableIntState.getIntValue() == 0) ? VolumeMuteKt.getVolumeMute(Icons.INSTANCE.getDefault()) : mutableIntState.getIntValue() < i10 / 2 ? VolumeDownKt.getVolumeDown(Icons.INSTANCE.getDefault()) : VolumeUpKt.getVolumeUp(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.player_cd_volume, composer, 0), SizeKt.m578size3ABfNKs(companion2, Dp.m5678constructorimpl(32)), companion3.m3509getWhite0d7_KjU(), composer, 3456, 0);
        float f10 = 8;
        SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion2, Dp.m5678constructorimpl(f10)), composer, 6);
        float f11 = 4;
        Modifier modifierL = com.arflix.tv.data.repository.g.l(f11, SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion2, Dp.m5678constructorimpl(f10)), Dp.m5678constructorimpl(100)), Color.m3471copywmQWz5c$default(companion3.m3509getWhite0d7_KjU(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null));
        composer.startReplaceableGroup(733328855);
        MeasurePolicy measurePolicyD = a0.c.d(companion, false, composer, 0, -1323940314);
        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierL);
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor2);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composer);
        r7.p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
        if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
            a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
        }
        a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
        BoxKt.Box(BoxScopeInstance.INSTANCE.align(com.arflix.tv.data.repository.g.l(f11, SizeKt.fillMaxSize(SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), qb.d.m(mutableIntState.getIntValue() / i10, 0.0f, 1.0f)), j10), companion.getBottomCenter()), composer, 0);
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        com.arflix.tv.data.repository.g.p(f10, companion2, composer, 6);
        if (PlayerScreen$lambda$88(mutableState)) {
            composer.startReplaceGroup(-2018010351);
            strI = StringResources_androidKt.stringResource(R.string.player_muted, composer, 0);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(-2018008975);
            composer.endReplaceGroup();
            strI = a2.i((mutableIntState.getIntValue() * 100) / i10, "%");
        }
        TextKt.m6020Text4IGK_g(strI, null, companion3.m3509getWhite0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getCaption(), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
        if (androidx.compose.foundation.c.D(composer)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$17(String str, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1690521345, i10, -1, "com.arflix.tv.ui.screens.player.PlayerScreen.<anonymous>.<anonymous>.<anonymous> (PlayerScreen.kt:4057)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        Color.Companion companion2 = Color.INSTANCE;
        Modifier modifierH = a2.h(14, com.arflix.tv.data.repository.g.j(10, companion, Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.65f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(24), composer, 733328855);
        MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composer, 0, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion3.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
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
        r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        TextKt.m6020Text4IGK_g(str, null, companion2.m3509getWhite0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getBody(), 0L, TextUnitKt.getSp(18), FontWeight.INSTANCE.getMedium(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
        if (androidx.compose.foundation.c.D(composer)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$18(MutableIntState mutableIntState, MutableLongState mutableLongState, MutableLongState mutableLongState2, MutableLongState mutableLongState3, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-51702496, i10, -1, "com.arflix.tv.ui.screens.player.PlayerScreen.<anonymous>.<anonymous>.<anonymous> (PlayerScreen.kt:4082)");
        }
        Alignment.Companion companion = Alignment.INSTANCE;
        Alignment.Horizontal centerHorizontally = companion.getCenterHorizontally();
        Modifier.Companion companion2 = Modifier.INSTANCE;
        Modifier modifierFillMaxWidth = SizeKt.fillMaxWidth(companion2, 0.72f);
        Color.Companion companion3 = Color.INSTANCE;
        Modifier modifierH = a2.h(14, com.arflix.tv.data.repository.g.l(12, modifierFillMaxWidth, Color.m3471copywmQWz5c$default(companion3.m3498getBlack0d7_KjU(), 0.55f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(20), composer, -483455358);
        Arrangement arrangement = Arrangement.INSTANCE;
        MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(arrangement, centerHorizontally, composer, 48, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion4.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
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
        r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyF, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
        String strO = mutableIntState.getIntValue() >= 0 ? androidx.compose.foundation.c.o(mutableIntState.getIntValue(), "+", CmcdData.STREAMING_FORMAT_SS) : a2.i(mutableIntState.getIntValue(), CmcdData.STREAMING_FORMAT_SS);
        ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
        TextStyle sectionTitle = arflixTypography.getSectionTitle();
        long sp = TextUnitKt.getSp(26);
        FontWeight.Companion companion5 = FontWeight.INSTANCE;
        TextKt.m6020Text4IGK_g(strO, null, companion3.m3509getWhite0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(sectionTitle, 0L, sp, companion5.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, new Shadow(companion3.m3498getBlack0d7_KjU(), OffsetKt.Offset(2.0f, 2.0f), 8.0f, null), null, 0, 0, 0L, null, null, null, 0, 0, null, 16769017, null), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
        Composer composer2 = composer;
        if (mutableLongState.getLongValue() > 0) {
            composer2.startReplaceGroup(1749223843);
            long longValue = mutableLongState2.getLongValue();
            Long lValueOf = Long.valueOf(longValue);
            if (longValue <= 0) {
                lValueOf = null;
            }
            long jLongValue = lValueOf != null ? lValueOf.longValue() : mutableLongState3.getLongValue();
            float fM = qb.d.m(jLongValue / mutableLongState.getLongValue(), 0.0f, 1.0f);
            float f10 = 10;
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion2, Dp.m5678constructorimpl(f10)), composer2, 6);
            Alignment.Vertical centerVertically = companion.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(f10));
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null);
            composer2.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM441spacedBy0680j_4, centerVertically, composer2, 54);
            composer2.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
            r7.p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyRowMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composer2, qVarModifierMaterializerOf2, composer2, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            TextKt.m6020Text4IGK_g(formatTime(jLongValue), null, companion3.m3509getWhite0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(13), companion5.getMedium(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            float f11 = 5;
            float f12 = 3;
            Modifier modifierL = com.arflix.tv.data.repository.g.l(f12, SizeKt.m564height3ABfNKs(androidx.compose.foundation.layout.e.a(rowScopeInstance, companion2, 1.0f, false, 2, null), Dp.m5678constructorimpl(f11)), Color.m3471copywmQWz5c$default(companion3.m3509getWhite0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null));
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(companion, false, composer, 0, -1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierL);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor3);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composer);
            r7.p pVarU3 = a0.c.u(companion4, composerM2991constructorimpl3, measurePolicyD, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composer, qVarModifierMaterializerOf3, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            BoxKt.Box(com.arflix.tv.data.repository.g.l(f12, SizeKt.m564height3ABfNKs(SizeKt.fillMaxWidth(companion2, fM), Dp.m5678constructorimpl(f11)), companion3.m3509getWhite0d7_KjU()), composer, 0);
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
            TextKt.m6020Text4IGK_g(formatTime(mutableLongState.getLongValue()), null, Color.m3471copywmQWz5c$default(companion3.m3509getWhite0d7_KjU(), 0.75f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(13), companion5.getMedium(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            composer2 = composer;
            com.arflix.tv.data.repository.g.q(composer2);
        } else {
            composer2.startReplaceGroup(1751181400);
            composer2.endReplaceGroup();
        }
        if (androidx.compose.foundation.c.D(composer2)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:108:0x04be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 PlayerScreen$lambda$362$2$19(androidx.compose.runtime.State r50, com.arflix.tv.ui.screens.player.PlayerViewModel r51, r7.a r52, androidx.compose.runtime.MutableIntState r53, androidx.compose.animation.AnimatedVisibilityScope r54, androidx.compose.runtime.Composer r55, int r56) {
        /*
            Method dump skipped, instruction units count: 1278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.PlayerScreen$lambda$362$2$19(androidx.compose.runtime.State, com.arflix.tv.ui.screens.player.PlayerViewModel, r7.a, androidx.compose.runtime.MutableIntState, androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$19$0$0$1$0$0(PlayerViewModel playerViewModel) {
        playerViewModel.retry();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$4$0(SkipInterval skipInterval, ExoPlayer exoPlayer, PlayerViewModel playerViewModel) {
        x6.t0 t0Var = x6.t0.f22605a;
        if (skipInterval != null) {
            long endMs = skipInterval.getEndMs() + 500;
            if (endMs < 0) {
                endMs = 0;
            }
            exoPlayer.seekTo(endMs);
            playerViewModel.dismissSkipInterval();
        }
        return t0Var;
    }

    private static final boolean PlayerScreen$lambda$362$2$5(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$7$0$0(PlayerViewModel playerViewModel) {
        playerViewModel.dismissAiErrorToast();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0530  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0546  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x05e0  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x05fe  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x06b7  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x06f0  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0745  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x07bd  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x07c7  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x07d3  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x07db  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x07df  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x07e9  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0824  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0830  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0834  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0845  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0858  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0869  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x086e  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x08a3  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x08af  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x08b3  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x08c4  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x08d7  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x08e0  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x08ec  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0905  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x090f  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0921  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x092d  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0940  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x094c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0962  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x096c  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0984  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x098c  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x09a0  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x09a8  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x09b9  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x09c4  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x09fc  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0a08  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0a2f  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0a47  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0a7d  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0a89  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0aa1  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0aae  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0abe  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0acb  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0b10  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x0b1a  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x0b29  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0b33  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0b55  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x0b6d  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0b7a  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0b8a  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0b94  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0bce  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0be6  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0bf4  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0c03  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0c1b  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x0c27  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x0c42  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x0c4c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x0c67  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x0d2d  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x0d4c  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x0d56  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x0d65  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0d7a  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x0d90  */
    /* JADX WARN: Removed duplicated region for block: B:378:0x0dbf  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0dc5  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x0ddc  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x0de2  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x0df9  */
    /* JADX WARN: Removed duplicated region for block: B:392:0x0e03  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x0e1e  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0e24  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x0e3d  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x0e60  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x0f20  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x0f54  */
    /* JADX WARN: Removed duplicated region for block: B:427:0x0f5f  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x0f6f  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x0f75  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x0f95  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x0fa1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x0fc9  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x0ff4  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x10b6  */
    /* JADX WARN: Removed duplicated region for block: B:467:0x10c7  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x1187  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x1197  */
    /* JADX WARN: Removed duplicated region for block: B:493:0x119e  */
    /* JADX WARN: Removed duplicated region for block: B:496:0x11e2  */
    /* JADX WARN: Removed duplicated region for block: B:499:0x11ee  */
    /* JADX WARN: Removed duplicated region for block: B:500:0x11f2  */
    /* JADX WARN: Removed duplicated region for block: B:503:0x1205  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x121c  */
    /* JADX WARN: Removed duplicated region for block: B:511:0x122b  */
    /* JADX WARN: Removed duplicated region for block: B:512:0x1230  */
    /* JADX WARN: Removed duplicated region for block: B:515:0x1240  */
    /* JADX WARN: Removed duplicated region for block: B:517:0x1247  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:520:0x12ac  */
    /* JADX WARN: Removed duplicated region for block: B:522:0x12b2  */
    /* JADX WARN: Removed duplicated region for block: B:525:0x12ea  */
    /* JADX WARN: Removed duplicated region for block: B:528:0x12fd  */
    /* JADX WARN: Removed duplicated region for block: B:529:0x1300  */
    /* JADX WARN: Removed duplicated region for block: B:534:0x1331  */
    /* JADX WARN: Removed duplicated region for block: B:537:0x1357  */
    /* JADX WARN: Removed duplicated region for block: B:539:0x135f  */
    /* JADX WARN: Removed duplicated region for block: B:542:0x1370  */
    /* JADX WARN: Removed duplicated region for block: B:545:0x138f  */
    /* JADX WARN: Removed duplicated region for block: B:549:0x139c  */
    /* JADX WARN: Removed duplicated region for block: B:552:0x13d7  */
    /* JADX WARN: Removed duplicated region for block: B:556:0x13e4  */
    /* JADX WARN: Removed duplicated region for block: B:559:0x1431  */
    /* JADX WARN: Removed duplicated region for block: B:561:0x1437  */
    /* JADX WARN: Removed duplicated region for block: B:564:0x1465  */
    /* JADX WARN: Removed duplicated region for block: B:566:0x146b  */
    /* JADX WARN: Removed duplicated region for block: B:569:0x14c0  */
    /* JADX WARN: Removed duplicated region for block: B:572:0x14cc  */
    /* JADX WARN: Removed duplicated region for block: B:573:0x14d0  */
    /* JADX WARN: Removed duplicated region for block: B:576:0x14e1  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:580:0x14f4  */
    /* JADX WARN: Removed duplicated region for block: B:583:0x1501  */
    /* JADX WARN: Removed duplicated region for block: B:585:0x150c  */
    /* JADX WARN: Removed duplicated region for block: B:591:0x152a  */
    /* JADX WARN: Removed duplicated region for block: B:592:0x152d  */
    /* JADX WARN: Removed duplicated region for block: B:595:0x155d  */
    /* JADX WARN: Removed duplicated region for block: B:601:0x157d  */
    /* JADX WARN: Removed duplicated region for block: B:604:0x15c2  */
    /* JADX WARN: Removed duplicated region for block: B:607:0x15ce  */
    /* JADX WARN: Removed duplicated region for block: B:608:0x15d2  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:611:0x15e3  */
    /* JADX WARN: Removed duplicated region for block: B:615:0x15f6  */
    /* JADX WARN: Removed duplicated region for block: B:618:0x160e  */
    /* JADX WARN: Removed duplicated region for block: B:619:0x1611  */
    /* JADX WARN: Removed duplicated region for block: B:622:0x1661  */
    /* JADX WARN: Removed duplicated region for block: B:623:0x1666  */
    /* JADX WARN: Removed duplicated region for block: B:626:0x16f0  */
    /* JADX WARN: Removed duplicated region for block: B:628:0x16f8  */
    /* JADX WARN: Removed duplicated region for block: B:631:0x1773  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x029d A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 PlayerScreen$lambda$362$2$9(final boolean r84, final com.arflix.tv.data.model.MediaType r85, java.lang.Integer r86, java.lang.Integer r87, long r88, androidx.compose.runtime.MutableState r90, androidx.compose.runtime.MutableState r91, androidx.compose.runtime.MutableState r92, androidx.compose.runtime.State r93, java.lang.String r94, boolean r95, boolean r96, androidx.compose.runtime.MutableLongState r97, androidx.compose.runtime.MutableLongState r98, androidx.compose.runtime.State r99, final boolean r100, final com.arflix.tv.cast.CastManager r101, final android.content.Context r102, boolean r103, boolean r104, final androidx.compose.ui.focus.FocusRequester r105, final androidx.compose.runtime.State r106, final java.util.List r107, ka.k0 r108, androidx.compose.ui.focus.FocusRequester r109, androidx.compose.ui.focus.FocusRequester r110, androidx.compose.ui.focus.FocusRequester r111, r7.l r112, final androidx.compose.ui.focus.FocusRequester r113, final androidx.media3.exoplayer.ExoPlayer r114, final androidx.compose.ui.focus.FocusRequester r115, java.lang.String r116, final androidx.compose.ui.focus.FocusRequester r117, r7.a r118, final androidx.compose.ui.focus.FocusRequester r119, r7.r r120, androidx.compose.ui.focus.FocusRequester r121, r7.a r122, androidx.compose.runtime.MutableIntState r123, final androidx.compose.runtime.MutableIntState r124, final androidx.compose.runtime.MutableIntState r125, final androidx.compose.runtime.MutableIntState r126, final androidx.compose.runtime.MutableIntState r127, final androidx.compose.runtime.MutableState r128, final androidx.compose.ui.focus.FocusRequester r129, androidx.compose.ui.focus.FocusRequester r130, androidx.compose.runtime.MutableState r131, androidx.compose.runtime.MutableIntState r132, androidx.compose.runtime.MutableState r133, androidx.compose.runtime.MutableState r134, androidx.compose.ui.focus.FocusRequester r135, androidx.compose.runtime.MutableState r136, final r7.a r137, androidx.compose.runtime.MutableState r138, androidx.compose.runtime.MutableLongState r139, androidx.compose.runtime.MutableState r140, androidx.compose.runtime.MutableFloatState r141, androidx.compose.animation.AnimatedVisibilityScope r142, androidx.compose.runtime.Composer r143, int r144) {
        /*
            Method dump skipped, instruction units count: 6009
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.PlayerScreen$lambda$362$2$9(boolean, com.arflix.tv.data.model.MediaType, java.lang.Integer, java.lang.Integer, long, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.State, java.lang.String, boolean, boolean, androidx.compose.runtime.MutableLongState, androidx.compose.runtime.MutableLongState, androidx.compose.runtime.State, boolean, com.arflix.tv.cast.CastManager, android.content.Context, boolean, boolean, androidx.compose.ui.focus.FocusRequester, androidx.compose.runtime.State, java.util.List, ka.k0, androidx.compose.ui.focus.FocusRequester, androidx.compose.ui.focus.FocusRequester, androidx.compose.ui.focus.FocusRequester, r7.l, androidx.compose.ui.focus.FocusRequester, androidx.media3.exoplayer.ExoPlayer, androidx.compose.ui.focus.FocusRequester, java.lang.String, androidx.compose.ui.focus.FocusRequester, r7.a, androidx.compose.ui.focus.FocusRequester, r7.r, androidx.compose.ui.focus.FocusRequester, r7.a, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableState, androidx.compose.ui.focus.FocusRequester, androidx.compose.ui.focus.FocusRequester, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.ui.focus.FocusRequester, androidx.compose.runtime.MutableState, r7.a, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableLongState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableFloatState, androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$0$0$3$0$0(boolean z, CastManager castManager, Context context) {
        if (z) {
            castManager.disconnect();
        } else {
            androidx.mediarouter.app.g gVar = new androidx.mediarouter.app.g(context);
            androidx.mediarouter.media.z routeSelector = castManager.getRouteSelector();
            if (routeSelector == null) {
                throw new IllegalArgumentException("selector must not be null");
            }
            if (!gVar.f4285o.equals(routeSelector)) {
                gVar.f4285o = routeSelector;
                if (gVar.B) {
                    androidx.mediarouter.media.k0 k0Var = gVar.f4283m;
                    androidx.mediarouter.app.d dVar = gVar.f4284n;
                    k0Var.e(dVar);
                    k0Var.a(routeSelector, dVar, 1);
                }
                gVar.e();
            }
            gVar.show();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$0$0(MutableIntState mutableIntState, boolean z) {
        if (z) {
            mutableIntState.setIntValue(1);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ec A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$1$0(java.util.List r7, ka.k0 r8, androidx.compose.runtime.MutableIntState r9, androidx.compose.runtime.MutableIntState r10, androidx.compose.runtime.State r11, androidx.compose.runtime.MutableIntState r12, androidx.compose.runtime.MutableIntState r13, androidx.compose.runtime.MutableState r14, androidx.compose.ui.focus.FocusRequester r15) {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$0$1$0(java.util.List, ka.k0, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.State, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableState, androidx.compose.ui.focus.FocusRequester):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$10$0(boolean z) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$11$0(MutableState mutableState, MutableState mutableState2) {
        PlayerScreen$lambda$113(mutableState, true);
        PlayerScreen$lambda$33(mutableState2, true);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$12$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$13$0(boolean z, FocusRequester focusRequester, FocusRequester focusRequester2) {
        if (z) {
            focusRequester.requestFocus();
        } else {
            focusRequester2.requestFocus();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$14$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$15$0(boolean z) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$16$0(r7.l lVar) {
        lVar.invoke(-10000L);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$17$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$18$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$19$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$2$0(MediaType mediaType, FocusRequester focusRequester, FocusRequester focusRequester2) {
        if (mediaType == MediaType.TV) {
            focusRequester.requestFocus();
        } else {
            focusRequester2.requestFocus();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$20$0(MutableIntState mutableIntState, boolean z) {
        if (z) {
            mutableIntState.setIntValue(0);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$21$0(boolean z, CastManager castManager, ExoPlayer exoPlayer) {
        if (z) {
            if (castManager.isRemotePlaying()) {
                castManager.pause();
            } else {
                castManager.play();
            }
        } else if (exoPlayer.isPlaying()) {
            exoPlayer.pause();
        } else {
            exoPlayer.play();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$22$0(boolean z, FocusRequester focusRequester, FocusRequester focusRequester2) {
        if (z) {
            focusRequester.requestFocus();
        } else {
            focusRequester2.requestFocus();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$23$0(boolean z, FocusRequester focusRequester, FocusRequester focusRequester2) {
        if (z) {
            focusRequester.requestFocus();
        } else {
            focusRequester2.requestFocus();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$24$0(FocusRequester focusRequester, State state) {
        if (PlayerScreen$lambda$3(state).getActiveSkipInterval() != null && !PlayerScreen$lambda$3(state).getSkipIntervalDismissed()) {
            focusRequester.requestFocus();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$25$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$26$0(boolean z) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$27$0(r7.l lVar) {
        lVar.invoke(10000L);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$28$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$29$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$3$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$30$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$31$0(boolean z) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$32$0(boolean z, FocusRequester focusRequester, FocusRequester focusRequester2) {
        if (z) {
            focusRequester.requestFocus();
        } else {
            focusRequester2.requestFocus();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$33$0(MediaType mediaType, FocusRequester focusRequester, boolean z, FocusRequester focusRequester2, FocusRequester focusRequester3) {
        if (mediaType == MediaType.TV) {
            focusRequester.requestFocus();
        } else if (!z || Build.VERSION.SDK_INT < 26) {
            focusRequester3.requestFocus();
        } else {
            focusRequester2.requestFocus();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$34$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$35$0(boolean z) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$36$0(r7.r rVar, MutableState mutableState, State state) {
        String addonId;
        String source;
        StreamBehaviorHints behaviorHints;
        String bingeGroup;
        Object objPlayerScreen$lambda$130 = PlayerScreen$lambda$130(mutableState);
        x6.t0 t0Var = x6.t0.f22605a;
        if (objPlayerScreen$lambda$130 == null) {
            return t0Var;
        }
        StreamSource selectedStream = PlayerScreen$lambda$3(state).getSelectedStream();
        Object obj = null;
        if (selectedStream == null || (addonId = selectedStream.getAddonId()) == null || kotlin.text.o.h0(addonId)) {
            addonId = null;
        }
        if (selectedStream == null || (source = selectedStream.getSource()) == null || kotlin.text.o.h0(source)) {
            source = null;
        }
        if (selectedStream != null && (behaviorHints = selectedStream.getBehaviorHints()) != null && (bingeGroup = behaviorHints.getBingeGroup()) != null && !kotlin.text.o.h0(bingeGroup)) {
            obj = bingeGroup;
        }
        rVar.invoke(objPlayerScreen$lambda$130, addonId, source, obj);
        return t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$37$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$38$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$39$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$4$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$40$0(boolean z) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$41$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$42$0(MediaType mediaType, FocusRequester focusRequester, FocusRequester focusRequester2) {
        if (mediaType == MediaType.TV) {
            focusRequester.requestFocus();
        } else {
            focusRequester2.requestFocus();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$43$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$44$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$5$0(boolean z) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$6$0(ka.k0 k0Var, MutableState mutableState, MutableIntState mutableIntState, FocusRequester focusRequester) {
        PlayerScreen$lambda$163(mutableState, !PlayerScreen$lambda$162(mutableState));
        if (PlayerScreen$lambda$162(mutableState)) {
            mutableIntState.setIntValue(0);
            ka.m0.p(k0Var, null, 0, new PlayerScreenKt$PlayerScreen$49$3$9$1$2$1$7$1$1(focusRequester, null), 3);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$7$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$8$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$0$9$0(FocusRequester focusRequester) {
        focusRequester.requestFocus();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$362$2$9$0$1$1$1(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void PlayerScreen$lambda$362$2$9$0$1$1$2(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$1$7$0(MutableIntState mutableIntState, IntSize intSize) {
        mutableIntState.setIntValue(IntSize.m5844getWidthimpl(intSize.getPackedValue()));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$362$2$9$0$1$1$8$0(r7.a aVar, MutableState mutableState, MutableState mutableState2, FocusState focusState) {
        PlayerScreen$lambda$362$2$9$0$1$1$2(mutableState, focusState.isFocused());
        if (!focusState.isFocused() && PlayerScreen$lambda$65(mutableState2)) {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerScreen$lambda$363(MediaType mediaType, int i10, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, String str, String str2, String str3, String str4, String str5, Long l10, boolean z, PlayerViewModel playerViewModel, r7.a aVar, r7.r rVar, int i11, int i12, int i13, Composer composer, int i14) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        PlayerScreen(mediaType, i10, num, num2, num3, num4, num5, num6, str, str2, str3, str4, str5, l10, z, playerViewModel, aVar, rVar, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), RecomposeScopeImplKt.updateChangedFlags(i12), i13);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlayerUiState PlayerScreen$lambda$4(State<PlayerUiState> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$47(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void PlayerScreen$lambda$48(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$53(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$54(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CastManager.CastState PlayerScreen$lambda$6(State<? extends CastManager.CastState> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$65(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$66(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ka.v1 PlayerScreen$lambda$71(MutableState<ka.v1> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$79(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$80(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean PlayerScreen$lambda$82(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$lambda$83(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$lambda$88(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void PlayerScreen$lambda$89(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PlayerScreen$markPlaybackStarted(PlayerViewModel playerViewModel, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableState<String> mutableState3, MutableState<Boolean> mutableState4, MutableIntState mutableIntState, MutableState<Long> mutableState5, MutableIntState mutableIntState2, MutableState<Boolean> mutableState6, MutableIntState mutableIntState3, String str) {
        if (PlayerScreen$lambda$26(mutableState)) {
            return;
        }
        PlayerScreen$lambda$27(mutableState, true);
        PlayerScreen$lambda$232(mutableState2, false);
        mutableState3.setValue(null);
        PlayerScreen$lambda$238(mutableState4, false);
        mutableIntState.setIntValue(0);
        Long lPlayerScreen$lambda$200 = PlayerScreen$lambda$200(mutableState5);
        long j10 = 0;
        if (lPlayerScreen$lambda$200 != null) {
            long jCurrentTimeMillis = System.currentTimeMillis() - lPlayerScreen$lambda$200.longValue();
            if (jCurrentTimeMillis >= 0) {
                j10 = jCurrentTimeMillis;
            }
        }
        int intValue = mutableIntState2.getIntValue();
        boolean zPlayerScreen$lambda$225 = PlayerScreen$lambda$225(mutableState6);
        int intValue2 = mutableIntState3.getIntValue();
        StringBuilder sb2 = new StringBuilder("started reason=");
        sb2.append(str);
        sb2.append(" startupMs=");
        sb2.append(j10);
        sb2.append(" retries=");
        sb2.append(intValue);
        sb2.append(" refresh=");
        sb2.append(zPlayerScreen$lambda$225);
        playbackStartupDiag(androidx.compose.foundation.c.p(intValue2, " failovers=", sb2));
        playerViewModel.onPlaybackStarted(j10, mutableIntState2.getIntValue() + (PlayerScreen$lambda$225(mutableState6) ? 1 : 0), mutableIntState3.getIntValue());
    }

    private static final void PlayerScreen$toggleMute(AudioManager audioManager, MutableState<Boolean> mutableState, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState<Boolean> mutableState2) {
        if (PlayerScreen$lambda$88(mutableState)) {
            audioManager.setStreamVolume(3, mutableIntState.getIntValue(), 0);
            mutableIntState2.setIntValue(mutableIntState.getIntValue());
            PlayerScreen$lambda$89(mutableState, false);
        } else {
            mutableIntState.setIntValue(mutableIntState2.getIntValue());
            audioManager.setStreamVolume(3, 0, 0);
            mutableIntState2.setIntValue(0);
            PlayerScreen$lambda$89(mutableState, true);
        }
        PlayerScreen$lambda$80(mutableState2, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PlayerScreen$tryAdvanceToNextStream(PlayerViewModel playerViewModel, State<PlayerUiState> state, MutableIntState mutableIntState, MutableState<Set<Integer>> mutableState, MutableIntState mutableIntState2, MutableState<String> mutableState2, MutableLongState mutableLongState, MutableState<Boolean> mutableState3, MutableState<Boolean> mutableState4, MutableState<Boolean> mutableState5, MutableState<Boolean> mutableState6, MutableIntState mutableIntState3, MutableState<Boolean> mutableState7, MutableState<String> mutableState8, MutableState<Boolean> mutableState9, MutableState<String> mutableState10, MutableState<Boolean> mutableState11, MutableIntState mutableIntState4, MutableState<Boolean> mutableState12, MutableIntState mutableIntState5, MutableState<Boolean> mutableState13, String str, boolean z, String str2) {
        Object next;
        List<StreamSource> streams = PlayerScreen$lambda$3(state).getStreams();
        if (streams.size() <= 1) {
            playerViewModel.onFailoverAttempt(false);
            return false;
        }
        x7.i iVarX = qb.d.X(1, streams.size());
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iVarX, 10));
        x7.h hVarC = iVarX.iterator();
        while (hVarC.f22624m) {
            arrayList.add(Integer.valueOf((mutableIntState.getIntValue() + hVarC.nextInt()) % streams.size()));
        }
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            int iIntValue = ((Number) next).intValue();
            StreamSource streamSource = streams.get(iIntValue);
            if (streamSource.getUrl() != null && (!kotlin.text.o.h0(r10)) && !PlayerScreen$lambda$264(mutableState).contains(Integer.valueOf(iIntValue)) && (str == null || kotlin.text.o.h0(str) || !kotlin.jvm.internal.p.a(streamSource.getAddonId(), str))) {
                if (!playerViewModel.isPlaybackHostTemporarilyBad(streamSource)) {
                    break;
                }
            }
        }
        Integer num = (Integer) next;
        int iIntValue2 = num != null ? num.intValue() : -1;
        if (iIntValue2 < 0) {
            playerViewModel.onFailoverAttempt(false);
            return false;
        }
        playerViewModel.onFailoverAttempt(true);
        mutableIntState2.setIntValue(mutableIntState2.getIntValue() + 1);
        int intValue = mutableIntState.getIntValue();
        StreamSource selectedStream = PlayerScreen$lambda$3(state).getSelectedStream();
        String addonId = selectedStream != null ? selectedStream.getAddonId() : null;
        StreamSource selectedStream2 = PlayerScreen$lambda$3(state).getSelectedStream();
        String quality = selectedStream2 != null ? selectedStream2.getQuality() : null;
        StreamSource selectedStream3 = PlayerScreen$lambda$3(state).getSelectedStream();
        String size = selectedStream3 != null ? selectedStream3.getSize() : null;
        String addonId2 = streams.get(iIntValue2).getAddonId();
        String quality2 = streams.get(iIntValue2).getQuality();
        String size2 = streams.get(iIntValue2).getSize();
        StringBuilder sbV = androidx.compose.foundation.c.v("advancing source from index=", intValue, " to index=", iIntValue2, " from=");
        y.a.i(sbV, addonId, DomExceptionUtils.SEPARATOR, quality, DomExceptionUtils.SEPARATOR);
        y.a.i(sbV, size, " to=", addonId2, DomExceptionUtils.SEPARATOR);
        playbackStartupDiag(androidx.compose.material3.d.q(sbV, quality2, DomExceptionUtils.SEPARATOR, size2));
        if (z) {
            playerViewModel.onSelectedStreamPlaybackFailure();
        }
        mutableIntState.setIntValue(iIntValue2);
        mutableState.setValue(kotlin.collections.q0.c0(PlayerScreen$lambda$264(mutableState), Integer.valueOf(iIntValue2)));
        StreamSource streamSource2 = streams.get(iIntValue2);
        StringBuilder sbP = a2.p(str2, " — switching");
        List listE = t7.a.E(streamSource2.getQuality(), streamSource2.getSize());
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : listE) {
            if (!kotlin.text.o.h0((String) obj)) {
                arrayList2.add(obj);
            }
        }
        String strU0 = kotlin.collections.x.u0(arrayList2, " · ", null, null, null, 62);
        if (!kotlin.text.o.h0(strU0)) {
            sbP.append(" to ".concat(strU0));
        }
        sbP.append("…");
        mutableState2.setValue(sbP.toString());
        mutableLongState.setLongValue(System.currentTimeMillis() + 3500);
        PlayerScreen$lambda$197(mutableState3, false);
        PlayerScreen$lambda$214(mutableState4, false);
        PlayerScreen$lambda$217(mutableState5, false);
        PlayerScreen$lambda$220(mutableState6, false);
        mutableIntState3.setIntValue(0);
        PlayerScreen$lambda$226(mutableState7, false);
        mutableState8.setValue(null);
        PlayerScreen$lambda$232(mutableState9, false);
        mutableState10.setValue(null);
        PlayerScreen$lambda$238(mutableState11, false);
        mutableIntState4.setIntValue(0);
        PlayerScreen$lambda$256(mutableState12, false);
        mutableIntState5.setIntValue(0);
        PlayerScreen$lambda$268(mutableState13, true);
        PlayerViewModel.selectStream$default(playerViewModel, streams.get(iIntValue2), null, 2, null);
        return true;
    }

    private static final Icon PlayerScreen$vectorToDrawableIcon(Context context, Density density, Painter painter) {
        int i10 = (int) (((int) (((context.getResources().getDisplayMetrics().widthPixels * 0.35f) * 9.0f) / 16.0f)) * 0.3f);
        int iMo279roundToPx0680j_4 = density.mo279roundToPx0680j_4(Dp.m5678constructorimpl(48));
        int i11 = i10 < iMo279roundToPx0680j_4 ? iMo279roundToPx0680j_4 : i10;
        ImageBitmap imageBitmapM3679ImageBitmapx__hDU$default = ImageBitmapKt.m3679ImageBitmapx__hDU$default(i11, i11, 0, false, null, 28, null);
        CanvasDrawScope canvasDrawScope = new CanvasDrawScope();
        float f10 = i11;
        long jSize = androidx.compose.ui.geometry.SizeKt.Size(f10, f10);
        LayoutDirection layoutDirection = LayoutDirection.Ltr;
        Canvas Canvas = CanvasKt.Canvas(imageBitmapM3679ImageBitmapx__hDU$default);
        CanvasDrawScope.DrawParams drawParams = canvasDrawScope.getDrawParams();
        Density densityComponent1 = drawParams.getDensity();
        LayoutDirection layoutDirectionComponent2 = drawParams.getLayoutDirection();
        Canvas canvasComponent3 = drawParams.getCanvas();
        long jM3918component4NHjbRc = drawParams.getSize();
        CanvasDrawScope.DrawParams drawParams2 = canvasDrawScope.getDrawParams();
        drawParams2.setDensity(density);
        drawParams2.setLayoutDirection(layoutDirection);
        drawParams2.setCanvas(Canvas);
        drawParams2.m3921setSizeuvyYCjk(jSize);
        Canvas.save();
        Painter.m4015drawx_KDEd0$default(painter, canvasDrawScope, jSize, 0.0f, ColorFilter.Companion.m3513tintxETnrds$default(ColorFilter.INSTANCE, Color.INSTANCE.m3509getWhite0d7_KjU(), 0, 2, null), 2, null);
        Canvas.restore();
        CanvasDrawScope.DrawParams drawParams3 = canvasDrawScope.getDrawParams();
        drawParams3.setDensity(densityComponent1);
        drawParams3.setLayoutDirection(layoutDirectionComponent2);
        drawParams3.setCanvas(canvasComponent3);
        drawParams3.m3921setSizeuvyYCjk(jM3918component4NHjbRc);
        return Icon.createWithBitmap(AndroidImageBitmap_androidKt.asAndroidBitmap(imageBitmapM3679ImageBitmapx__hDU$default));
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02f4  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0440  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0486  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0492  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0496  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x04ba  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0298  */
    /* JADX INFO: renamed from: PlayerSubtitleSettingRow-fWhpE4E, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6264PlayerSubtitleSettingRowfWhpE4E(final java.lang.String r103, final java.lang.String r104, final boolean r105, final long r106, final r7.a<x6.t0> r108, final r7.a<x6.t0> r109, final r7.a<x6.t0> r110, androidx.compose.runtime.Composer r111, final int r112) {
        /*
            Method dump skipped, instruction units count: 1403
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.m6264PlayerSubtitleSettingRowfWhpE4E(java.lang.String, java.lang.String, boolean, long, r7.a, r7.a, r7.a, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerSubtitleSettingRow_fWhpE4E$lambda$2(String str, String str2, boolean z, long j10, r7.a aVar, r7.a aVar2, r7.a aVar3, int i10, Composer composer, int i11) {
        m6264PlayerSubtitleSettingRowfWhpE4E(str, str2, z, j10, aVar, aVar2, aVar3, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void PlayerSubtitleSettingsPanel(final int i10, final long j10, final int i11, final int i12, final r7.l<? super Integer, x6.t0> lVar, final r7.a<x6.t0> aVar, final r7.a<x6.t0> aVar2, final r7.a<x6.t0> aVar3, final r7.a<x6.t0> aVar4, final r7.a<x6.t0> aVar5, final r7.a<x6.t0> aVar6, Composer composer, final int i13, final int i14) {
        int i15;
        int i16;
        int i17;
        String strJ;
        int i18;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1091390684);
        if ((i13 & 6) == 0) {
            i15 = (composerStartRestartGroup.changed(i10) ? 4 : 2) | i13;
        } else {
            i15 = i13;
        }
        if ((i13 & 48) == 0) {
            i15 |= composerStartRestartGroup.changed(j10) ? 32 : 16;
        }
        if ((i13 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i15 |= composerStartRestartGroup.changed(i11) ? 256 : 128;
        }
        if ((i13 & 3072) == 0) {
            i15 |= composerStartRestartGroup.changed(i12) ? 2048 : 1024;
        }
        if ((i13 & 24576) == 0) {
            i15 |= composerStartRestartGroup.changedInstance(lVar) ? 16384 : 8192;
        }
        if ((196608 & i13) == 0) {
            i15 |= composerStartRestartGroup.changedInstance(aVar) ? 131072 : 65536;
        }
        if ((1572864 & i13) == 0) {
            i15 |= composerStartRestartGroup.changedInstance(aVar2) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i13 & 12582912) == 0) {
            i15 |= composerStartRestartGroup.changedInstance(aVar3) ? 8388608 : 4194304;
        }
        if ((i13 & 100663296) == 0) {
            i15 |= composerStartRestartGroup.changedInstance(aVar4) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i13 & 805306368) == 0) {
            i15 |= composerStartRestartGroup.changedInstance(aVar5) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((i14 & 6) == 0) {
            i16 = i14 | (composerStartRestartGroup.changedInstance(aVar6) ? 4 : 2);
        } else {
            i16 = i14;
        }
        if ((i15 & 306783379) == 306783378 && (i16 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1091390684, i15, i16, "com.arflix.tv.ui.screens.player.PlayerSubtitleSettingsPanel (PlayerScreen.kt:6274)");
            }
            Color color = (Color) composerStartRestartGroup.consume(ArvioSkinKt.getLocalAccentColorOverride());
            long jM3482unboximpl = color != null ? color.m3482unboximpl() : Color.INSTANCE.m3509getWhite0d7_KjU();
            int i19 = i15;
            long j11 = j10 < 0 ? -j10 : j10;
            if (j10 == 0) {
                strJ = "0.0s";
                i17 = i16;
            } else {
                String str = j10 > 0 ? "+" : "-";
                i17 = i16;
                long j12 = 1000;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(j11 / j12);
                sb2.append(".");
                strJ = a0.c.j((j11 % j12) / ((long) 100), CmcdData.STREAMING_FORMAT_SS, sb2);
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierM583width3ABfNKs = SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(280));
            Color.Companion companion2 = Color.INSTANCE;
            String str2 = strJ;
            float f10 = 16;
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.l(f10, modifierM583width3ABfNKs, Color.m3471copywmQWz5c$default(companion2.m3498getBlack0d7_KjU(), 0.92f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(companion2.m3509getWhite0d7_KjU(), 0.12f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(20), Dp.m5678constructorimpl(f10));
            MeasurePolicy measurePolicyJ = androidx.compose.foundation.c.j(Alignment.INSTANCE, androidx.compose.foundation.c.g(4, Arrangement.INSTANCE, composerStartRestartGroup, -483455358), composerStartRestartGroup, 6, -1323940314);
            int i20 = 0;
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyJ, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.subtitle_settings_title, composerStartRestartGroup, 0), PaddingKt.m533paddingqDBjuR0$default(companion, 0.0f, 0.0f, 0.0f, Dp.m5678constructorimpl(6), 7, null), companion2.m3509getWhite0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getSectionTitle(), 0L, TextUnitKt.getSp(16), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composerStartRestartGroup, 432, 0, 65528);
            String strStringResource = StringResources_androidKt.stringResource(R.string.subtitle_delay, composerStartRestartGroup, 0);
            boolean z = i10 == 0;
            int i21 = i19 & 57344;
            boolean z5 = i21 == 16384;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new u(lVar, i20);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            m6264PlayerSubtitleSettingRowfWhpE4E(strStringResource, str2, z, jM3482unboximpl, (r7.a) objRememberedValue, aVar, aVar2, composerStartRestartGroup, i19 & 4128768);
            String strStringResource2 = StringResources_androidKt.stringResource(R.string.subtitle_size_label, composerStartRestartGroup, 0);
            String strI = a2.i(i11, "%");
            boolean z10 = i10 == 1;
            boolean z11 = i21 == 16384;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z11 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                i18 = 1;
                objRememberedValue2 = new u(lVar, i18);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                i18 = 1;
            }
            m6264PlayerSubtitleSettingRowfWhpE4E(strStringResource2, strI, z10, jM3482unboximpl, (r7.a) objRememberedValue2, aVar3, aVar4, composerStartRestartGroup, (i19 >> 6) & 4128768);
            String strStringResource3 = StringResources_androidKt.stringResource(R.string.subtitle_vertical_position, composerStartRestartGroup, 0);
            String strI2 = a2.i(i12, "%");
            boolean z12 = i10 == 2 ? i18 : 0;
            int i22 = i21 == 16384 ? i18 : 0;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (i22 != 0 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new u(lVar, 2);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composer2 = composerStartRestartGroup;
            m6264PlayerSubtitleSettingRowfWhpE4E(strStringResource3, strI2, z12, jM3482unboximpl, (r7.a) objRememberedValue3, aVar5, aVar6, composer2, ((i19 >> 12) & 458752) | ((i17 << 18) & 3670016));
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.player.v
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return PlayerScreenKt.PlayerSubtitleSettingsPanel$lambda$1(i10, j10, i11, i12, lVar, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, i13, i14, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerSubtitleSettingsPanel$lambda$0$0$0(r7.l lVar) {
        lVar.invoke(0);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerSubtitleSettingsPanel$lambda$0$1$0(r7.l lVar) {
        lVar.invoke(1);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerSubtitleSettingsPanel$lambda$0$2$0(r7.l lVar) {
        lVar.invoke(2);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PlayerSubtitleSettingsPanel$lambda$1(int i10, long j10, int i11, int i12, r7.l lVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, int i13, int i14, Composer composer, int i15) {
        PlayerSubtitleSettingsPanel(i10, j10, i11, i12, lVar, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, composer, RecomposeScopeImplKt.updateChangedFlags(i13 | 1), RecomposeScopeImplKt.updateChangedFlags(i14));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x03c8  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x04a3  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x04b7  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x051b  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:173:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void PulsingLogo(java.lang.String r76, java.lang.String r77, androidx.compose.ui.Modifier r78, java.lang.Float r79, java.lang.String r80, androidx.compose.runtime.Composer r81, int r82, int r83) {
        /*
            Method dump skipped, instruction units count: 1337
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.PulsingLogo(java.lang.String, java.lang.String, androidx.compose.ui.Modifier, java.lang.Float, java.lang.String, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PulsingLogo$lambda$0$0(KeyframesSpec.KeyframesSpecConfig keyframesSpecConfig) {
        keyframesSpecConfig.setDurationMillis(1500);
        Float fValueOf = Float.valueOf(1.0f);
        keyframesSpecConfig.at(fValueOf, 0);
        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(1.08f), 160), EasingKt.getFastOutSlowInEasing());
        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(1.02f), 280), EasingKt.getFastOutSlowInEasing());
        keyframesSpecConfig.using(keyframesSpecConfig.at(Float.valueOf(1.12f), MediaError.DetailedErrorCode.DASH_MANIFEST_UNKNOWN), EasingKt.getFastOutSlowInEasing());
        keyframesSpecConfig.using(keyframesSpecConfig.at(fValueOf, 620), EasingKt.getFastOutSlowInEasing());
        keyframesSpecConfig.at(fValueOf, 1500);
        return x6.t0.f22605a;
    }

    private static final float PulsingLogo$lambda$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float PulsingLogo$lambda$2(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PulsingLogo$lambda$3$0$0$0(State state, DrawScope drawScope) {
        float fMo285toPx0680j_4 = drawScope.mo285toPx0680j_4(Dp.m5678constructorimpl(4));
        float fM3304getMinDimensionimpl = Size.m3304getMinDimensionimpl(drawScope.mo3916getSizeNHjbRc()) - fMo285toPx0680j_4;
        long jOffset = OffsetKt.Offset((Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc()) - fM3304getMinDimensionimpl) / 2.0f, (Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc()) - fM3304getMinDimensionimpl) / 2.0f);
        long jSize = androidx.compose.ui.geometry.SizeKt.Size(fM3304getMinDimensionimpl, fM3304getMinDimensionimpl);
        Color.Companion companion = Color.INSTANCE;
        long jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.15f, 0.0f, 0.0f, 0.0f, 14, null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        androidx.compose.ui.graphics.drawscope.c.v(drawScope, jM3471copywmQWz5c$default, 0.0f, 360.0f, false, jOffset, jSize, 0.0f, new Stroke(fMo285toPx0680j_4, 0.0f, companion2.m3804getRoundKaPHkGw(), 0, null, 26, null), null, 0, 832, null);
        androidx.compose.ui.graphics.drawscope.c.v(drawScope, companion.m3509getWhite0d7_KjU(), -90.0f, 360.0f * PulsingLogo$lambda$2(state), false, jOffset, jSize, 0.0f, new Stroke(fMo285toPx0680j_4, 0.0f, companion2.m3804getRoundKaPHkGw(), 0, null, 26, null), null, 0, 832, null);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PulsingLogo$lambda$3$0$1$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(PulsingLogo$lambda$1(state));
        graphicsLayerScope.setScaleY(PulsingLogo$lambda$1(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PulsingLogo$lambda$4(String str, String str2, Modifier modifier, Float f10, String str3, int i10, int i11, Composer composer, int i12) {
        PulsingLogo(str, str2, modifier, f10, str3, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0358  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x03c9  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x03d4  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x03f5  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x045f  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x046e  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x04c3  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x04cf  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x04d3  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x04f7  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0566  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x05b6  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x05c2  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x05c6  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x05ea  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x063c  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0648  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x064c  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x065d  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0672  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x0686  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x0689  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0691  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x0693  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x069a  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x06a3  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x06c7  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x06cc  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x06d1  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x06d3  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x06e0  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x070a  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x07b9  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x07f8  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x0804  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x0808  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x082c  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x0835  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x0b0e  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x0b4f  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x0b51  */
    /* JADX WARN: Removed duplicated region for block: B:483:0x0b5c  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x0b5e  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x0b66  */
    /* JADX WARN: Removed duplicated region for block: B:488:0x0b68  */
    /* JADX WARN: Removed duplicated region for block: B:491:0x0b78  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x0b7a  */
    /* JADX WARN: Removed duplicated region for block: B:495:0x0b82  */
    /* JADX WARN: Removed duplicated region for block: B:496:0x0b84  */
    /* JADX WARN: Removed duplicated region for block: B:499:0x0b91  */
    /* JADX WARN: Removed duplicated region for block: B:500:0x0b93  */
    /* JADX WARN: Removed duplicated region for block: B:503:0x0b9d  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x0b9f  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x0baa  */
    /* JADX WARN: Removed duplicated region for block: B:508:0x0bac  */
    /* JADX WARN: Removed duplicated region for block: B:515:0x0bc9  */
    /* JADX WARN: Removed duplicated region for block: B:519:0x0c1a  */
    /* JADX WARN: Removed duplicated region for block: B:543:0x0ce3  */
    /* JADX WARN: Removed duplicated region for block: B:546:0x0cef  */
    /* JADX WARN: Removed duplicated region for block: B:547:0x0cf3  */
    /* JADX WARN: Removed duplicated region for block: B:554:0x0d17  */
    /* JADX WARN: Removed duplicated region for block: B:556:0x0db4  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:610:0x101e  */
    /* JADX WARN: Removed duplicated region for block: B:613:0x102a  */
    /* JADX WARN: Removed duplicated region for block: B:614:0x102e  */
    /* JADX WARN: Removed duplicated region for block: B:617:0x103f  */
    /* JADX WARN: Removed duplicated region for block: B:621:0x1054  */
    /* JADX WARN: Removed duplicated region for block: B:625:0x1069  */
    /* JADX WARN: Removed duplicated region for block: B:626:0x106c  */
    /* JADX WARN: Removed duplicated region for block: B:629:0x1124  */
    /* JADX WARN: Removed duplicated region for block: B:632:0x1133  */
    /* JADX WARN: Removed duplicated region for block: B:633:0x1135  */
    /* JADX WARN: Removed duplicated region for block: B:636:0x113c  */
    /* JADX WARN: Removed duplicated region for block: B:638:0x1142  */
    /* JADX WARN: Removed duplicated region for block: B:641:0x118c  */
    /* JADX WARN: Removed duplicated region for block: B:644:0x1198  */
    /* JADX WARN: Removed duplicated region for block: B:645:0x119c  */
    /* JADX WARN: Removed duplicated region for block: B:648:0x11b1  */
    /* JADX WARN: Removed duplicated region for block: B:652:0x11c6  */
    /* JADX WARN: Removed duplicated region for block: B:656:0x1250  */
    /* JADX WARN: Removed duplicated region for block: B:659:0x125c  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:660:0x1260  */
    /* JADX WARN: Removed duplicated region for block: B:663:0x1271  */
    /* JADX WARN: Removed duplicated region for block: B:667:0x1284  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:671:0x12b5  */
    /* JADX WARN: Removed duplicated region for block: B:722:0x1504  */
    /* JADX WARN: Removed duplicated region for block: B:723:0x1506  */
    /* JADX WARN: Removed duplicated region for block: B:726:0x150d  */
    /* JADX WARN: Removed duplicated region for block: B:727:0x150f  */
    /* JADX WARN: Removed duplicated region for block: B:730:0x1517  */
    /* JADX WARN: Removed duplicated region for block: B:731:0x1519  */
    /* JADX WARN: Removed duplicated region for block: B:734:0x1523  */
    /* JADX WARN: Removed duplicated region for block: B:735:0x1525  */
    /* JADX WARN: Removed duplicated region for block: B:738:0x152d  */
    /* JADX WARN: Removed duplicated region for block: B:739:0x152f  */
    /* JADX WARN: Removed duplicated region for block: B:742:0x1539  */
    /* JADX WARN: Removed duplicated region for block: B:743:0x153b  */
    /* JADX WARN: Removed duplicated region for block: B:746:0x1543  */
    /* JADX WARN: Removed duplicated region for block: B:747:0x1545  */
    /* JADX WARN: Removed duplicated region for block: B:750:0x154b  */
    /* JADX WARN: Removed duplicated region for block: B:751:0x154d  */
    /* JADX WARN: Removed duplicated region for block: B:754:0x1555  */
    /* JADX WARN: Removed duplicated region for block: B:755:0x1557  */
    /* JADX WARN: Removed duplicated region for block: B:758:0x155d  */
    /* JADX WARN: Removed duplicated region for block: B:759:0x155f  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:762:0x1569  */
    /* JADX WARN: Removed duplicated region for block: B:763:0x156b  */
    /* JADX WARN: Removed duplicated region for block: B:766:0x1571  */
    /* JADX WARN: Removed duplicated region for block: B:767:0x1573  */
    /* JADX WARN: Removed duplicated region for block: B:770:0x157a  */
    /* JADX WARN: Removed duplicated region for block: B:771:0x157c  */
    /* JADX WARN: Removed duplicated region for block: B:774:0x1582  */
    /* JADX WARN: Removed duplicated region for block: B:775:0x1584  */
    /* JADX WARN: Removed duplicated region for block: B:778:0x158c  */
    /* JADX WARN: Removed duplicated region for block: B:779:0x158e  */
    /* JADX WARN: Removed duplicated region for block: B:782:0x1596  */
    /* JADX WARN: Removed duplicated region for block: B:783:0x1598  */
    /* JADX WARN: Removed duplicated region for block: B:787:0x159f  */
    /* JADX WARN: Removed duplicated region for block: B:794:0x15c4  */
    /* JADX WARN: Removed duplicated region for block: B:798:0x1622  */
    /* JADX WARN: Removed duplicated region for block: B:802:0x163b  */
    /* JADX WARN: Removed duplicated region for block: B:807:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0141  */
    /* JADX WARN: Type inference failed for: r12v44 */
    /* JADX WARN: Type inference failed for: r12v45, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r12v49 */
    /* JADX WARN: Type inference failed for: r14v41 */
    /* JADX WARN: Type inference failed for: r14v42, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v48, types: [boolean] */
    /* JADX WARN: Type inference failed for: r14v50 */
    /* JADX WARN: Type inference failed for: r15v12 */
    /* JADX WARN: Type inference failed for: r15v19, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r15v20 */
    /* JADX WARN: Type inference failed for: r15v21, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r15v27 */
    /* JADX WARN: Type inference failed for: r15v31, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r15v33 */
    /* JADX WARN: Type inference failed for: r15v34 */
    /* JADX WARN: Type inference failed for: r15v35 */
    /* JADX WARN: Type inference failed for: r15v36 */
    /* JADX WARN: Type inference failed for: r20v31 */
    /* JADX WARN: Type inference failed for: r33v3, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r3v72, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r5v38, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r5v65, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r5v68, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r5v69 */
    /* JADX WARN: Type inference failed for: r5v77 */
    /* JADX WARN: Type inference failed for: r5v78 */
    /* JADX WARN: Type inference failed for: r5v79 */
    /* JADX WARN: Type inference failed for: r6v33 */
    /* JADX WARN: Type inference failed for: r6v34, types: [int] */
    /* JADX WARN: Type inference failed for: r6v80 */
    /* JADX WARN: Type inference failed for: r6v81 */
    /* JADX WARN: Type inference failed for: r6v85 */
    /* JADX WARN: Type inference failed for: r6v86 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void SubtitleMenu(final java.util.List<com.arflix.tv.data.model.Subtitle> r105, final com.arflix.tv.data.model.Subtitle r106, boolean r107, boolean r108, java.lang.String r109, java.lang.String r110, boolean r111, boolean r112, final java.util.List<com.arflix.tv.ui.screens.player.AudioTrackInfo> r113, final int r114, final int r115, final int r116, final java.util.List<? extends x6.x> r117, final int r118, final int r119, final int r120, java.lang.String r121, final r7.l<? super java.lang.Integer, x6.t0> r122, final r7.l<? super java.lang.Integer, x6.t0> r123, final r7.l<? super com.arflix.tv.ui.screens.player.AudioTrackInfo, x6.t0> r124, r7.a<x6.t0> r125, r7.a<x6.t0> r126, r7.a<x6.t0> r127, final r7.a<x6.t0> r128, androidx.compose.runtime.Composer r129, final int r130, final int r131, final int r132, final int r133) {
        /*
            Method dump skipped, instruction units count: 5741
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.SubtitleMenu(java.util.List, com.arflix.tv.data.model.Subtitle, boolean, boolean, java.lang.String, java.lang.String, boolean, boolean, java.util.List, int, int, int, java.util.List, int, int, int, java.lang.String, r7.l, r7.l, r7.l, r7.a, r7.a, r7.a, r7.a, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$12$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$13$2$0$1$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$13$2$1$0$1$0(int i10, r7.l lVar, MutableIntState mutableIntState) {
        mutableIntState.setIntValue(i10);
        lVar.invoke(Integer.valueOf(i10));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$13$2$2$0(List list, List list2, MutableIntState mutableIntState, Subtitle subtitle, boolean z, r7.l lVar, String str, boolean z5, String str2, r7.a aVar, r7.a aVar2, boolean z10, r7.a aVar3, boolean z11, r7.a aVar4, final String str3, int i10, r7.l lVar2, LazyListScope lazyListScope) {
        final boolean z12;
        final boolean z13 = z;
        boolean z14 = true;
        if (mutableIntState.getIntValue() == 0) {
            final Subtitle subtitle2 = subtitle;
            final r7.l lVar3 = lVar;
            LazyListScope.CC.i(lazyListScope, null, null, ComposableLambdaKt.composableLambdaInstance(-1013032694, true, new d1(subtitle2, z13, lVar3)), 3, null);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                x6.x xVar = (x6.x) it.next();
                String str4 = (String) xVar.f22608i;
                List<x6.x> list3 = (List) xVar.f22609l;
                boolean zA = kotlin.jvm.internal.p.a(str4, "Live Audio");
                boolean z15 = false;
                boolean z16 = (kotlin.text.o.h0(str) || !kotlin.text.u.L(str4, str, z14)) ? false : z14;
                if (z5 && !kotlin.text.o.h0(str2) && kotlin.text.u.L(str4, str2, z14)) {
                    z15 = z14;
                }
                LazyListScope.CC.i(lazyListScope, androidx.compose.material3.d.C("mobile_header_", str4), null, ComposableLambdaKt.composableLambdaInstance(-343611071, z14, new g0(str4, 1)), 2, null);
                if (zA) {
                    LazyListScope.CC.i(lazyListScope, "mobile_live_audio_item", null, ComposableLambdaKt.composableLambdaInstance(-355794234, z14, new e1(z13, aVar, aVar2, 0)), 2, null);
                }
                if (z16) {
                    LazyListScope.CC.i(lazyListScope, "mobile_find_best_match_item", null, ComposableLambdaKt.composableLambdaInstance(1322490543, z14, new e1(z10, aVar3, aVar2, 1)), 2, null);
                }
                if (z15) {
                    z12 = z11;
                    LazyListScope.CC.i(lazyListScope, "mobile_ai_item", null, ComposableLambdaKt.composableLambdaInstance(-371585714, z14, new com.arflix.tv.ui.components.z0(str2, z12, aVar4, aVar2)), 2, null);
                } else {
                    z12 = z11;
                }
                for (x6.x xVar2 : list3) {
                    final int iIntValue = ((Number) xVar2.f22608i).intValue();
                    final Subtitle subtitle3 = (Subtitle) xVar2.f22609l;
                    LazyListScope.CC.i(lazyListScope, androidx.compose.material3.d.C("mobile_", subtitle3.getId()), null, ComposableLambdaKt.composableLambdaInstance(2042087018, true, new r7.q() { // from class: com.arflix.tv.ui.screens.player.f1
                        @Override // r7.q
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            int iIntValue2 = ((Integer) obj3).intValue();
                            return PlayerScreenKt.SubtitleMenu$lambda$13$2$2$0$1$4$0(str3, subtitle3, subtitle2, z12, z13, lVar3, iIntValue, (LazyItemScope) obj, (Composer) obj2, iIntValue2);
                        }
                    }), 2, null);
                    subtitle2 = subtitle;
                    z13 = z;
                    lVar3 = lVar;
                    z12 = z11;
                    z14 = true;
                }
                subtitle2 = subtitle;
                z13 = z;
                lVar3 = lVar;
            }
        } else if (list2.isEmpty()) {
            LazyListScope.CC.i(lazyListScope, null, null, ComposableSingletons$PlayerScreenKt.INSTANCE.m6260getLambda$406282834$app_playRelease(), 3, null);
        } else {
            lazyListScope.items(list2.size(), new PlayerScreenKt$SubtitleMenu$lambda$13$2$2$0$$inlined$itemsIndexed$default$1(new com.arflix.tv.ui.components.l1(2), list2), new PlayerScreenKt$SubtitleMenu$lambda$13$2$2$0$$inlined$itemsIndexed$default$2(list2), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new PlayerScreenKt$SubtitleMenu$lambda$13$2$2$0$$inlined$itemsIndexed$default$3(list2, i10, lVar2, list2)));
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$13$2$2$0$0(Subtitle subtitle, boolean z, r7.l lVar, LazyItemScope lazyItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1013032694, i10, -1, "com.arflix.tv.ui.screens.player.SubtitleMenu.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PlayerScreen.kt:5149)");
            }
            boolean z5 = subtitle == null && !z;
            boolean zChanged = composer.changed(lVar);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new u(lVar, 3);
                composer.updateRememberedValue(objRememberedValue);
            }
            MobileTrackItem("Off", null, z5, (r7.a) objRememberedValue, composer, 54);
            BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m564height3ABfNKs(PaddingKt.m531paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5678constructorimpl(8), 0.0f, 2, null), Dp.m5678constructorimpl(1)), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.06f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$13$2$2$0$0$0$0(r7.l lVar) {
        lVar.invoke(0);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$13$2$2$0$1$0(String str, LazyItemScope lazyItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-343611071, i10, -1, "com.arflix.tv.ui.screens.player.SubtitleMenu.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PlayerScreen.kt:5172)");
            }
            TextKt.m6020Text4IGK_g(str.toUpperCase(Locale.ROOT), PaddingKt.m533paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5678constructorimpl(16), Dp.m5678constructorimpl(8), 0.0f, Dp.m5678constructorimpl(2), 4, null), Color.m3471copywmQWz5c$default(com.arflix.tv.ui.theme.ColorKt.getTextSecondary(), 0.45f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(10), null, null, null, null, null, TextUnitKt.getSp(1), null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777085, null), composer, 48, 0, 65528);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$13$2$2$0$1$1(boolean z, r7.a aVar, r7.a aVar2, LazyItemScope lazyItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-355794234, i10, -1, "com.arflix.tv.ui.screens.player.SubtitleMenu.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PlayerScreen.kt:5186)");
            }
            boolean zChanged = composer.changed(aVar) | composer.changed(aVar2);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new t(1, aVar, aVar2);
                composer.updateRememberedValue(objRememberedValue);
            }
            MobileTrackItem("Translate Audio", "AI", z, (r7.a) objRememberedValue, composer, 54);
            BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m564height3ABfNKs(PaddingKt.m531paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5678constructorimpl(8), 0.0f, 2, null), Dp.m5678constructorimpl(1)), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.06f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$13$2$2$0$1$1$0$0(r7.a aVar, r7.a aVar2) {
        aVar.invoke();
        aVar2.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$13$2$2$0$1$2(boolean z, r7.a aVar, r7.a aVar2, LazyItemScope lazyItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1322490543, i10, -1, "com.arflix.tv.ui.screens.player.SubtitleMenu.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PlayerScreen.kt:5203)");
            }
            String str = z ? "Scanning…" : "Find Best Match";
            boolean zChanged = composer.changed(aVar) | composer.changed(aVar2);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new t(0, aVar, aVar2);
                composer.updateRememberedValue(objRememberedValue);
            }
            MobileTrackItem(str, "Auto", z, (r7.a) objRememberedValue, composer, 48);
            BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m564height3ABfNKs(PaddingKt.m531paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5678constructorimpl(8), 0.0f, 2, null), Dp.m5678constructorimpl(1)), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.06f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$13$2$2$0$1$2$0$0(r7.a aVar, r7.a aVar2) {
        aVar.invoke();
        aVar2.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$13$2$2$0$1$3(String str, boolean z, r7.a aVar, r7.a aVar2, LazyItemScope lazyItemScope, Composer composer, int i10) {
        if ((i10 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-371585714, i10, -1, "com.arflix.tv.ui.screens.player.SubtitleMenu.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PlayerScreen.kt:5220)");
            }
            boolean zChanged = composer.changed(aVar) | composer.changed(aVar2);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new t(2, aVar, aVar2);
                composer.updateRememberedValue(objRememberedValue);
            }
            MobileTrackItem(str, "AI", z, (r7.a) objRememberedValue, composer, 48);
            BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m564height3ABfNKs(PaddingKt.m531paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5678constructorimpl(8), 0.0f, 2, null), Dp.m5678constructorimpl(1)), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.06f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$13$2$2$0$1$3$0$0(r7.a aVar, r7.a aVar2) {
        aVar.invoke();
        aVar2.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$13$2$2$0$1$4$0(String str, Subtitle subtitle, Subtitle subtitle2, boolean z, boolean z5, r7.l lVar, int i10, LazyItemScope lazyItemScope, Composer composer, int i11) {
        String strC;
        String str2;
        String strU0;
        if ((i11 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2042087018, i11, -1, "com.arflix.tv.ui.screens.player.SubtitleMenu.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PlayerScreen.kt:5237)");
            }
            int iSubtitleMatchScore = subtitleMatchScore(str, subtitle);
            String fullLanguageName = getFullLanguageName(subtitle.getLang());
            Long lMatchedOffsetMsFor = matchedOffsetMsFor(subtitle2, subtitle.getId());
            if (lMatchedOffsetMsFor == null || (strC = androidx.compose.material3.d.C(" · ", formatMatchOffset(lMatchedOffsetMsFor.longValue()))) == null) {
                strC = "";
            }
            if (subtitle.isEmbedded() || iSubtitleMatchScore <= 0) {
                str2 = fullLanguageName;
            } else {
                str2 = fullLanguageName + " (" + iSubtitleMatchScore + "%)";
            }
            String strM = androidx.compose.material3.d.m(str2, strC);
            if (subtitle.isEmbedded() && kotlin.text.o.h0(subtitle.getUrl())) {
                String label = subtitle.getLabel();
                if (kotlin.text.o.h0(label) || label.equalsIgnoreCase(fullLanguageName)) {
                    label = null;
                }
                strU0 = kotlin.collections.x.u0(kotlin.collections.r.X(new String[]{"Built-in", label, subtitle.isForced() ? "Forced" : null}), " · ", null, null, null, 62);
            } else {
                String provider = subtitle.getProvider();
                if (kotlin.text.o.h0(provider)) {
                    provider = null;
                }
                strU0 = kotlin.collections.x.u0(kotlin.collections.r.X(new String[]{provider, subtitle.isForced() ? "Forced" : null}), " · ", null, null, null, 62);
                if (kotlin.text.o.h0(strU0)) {
                    strU0 = null;
                }
            }
            boolean z10 = (z || z5 || !isSameSubtitleTrack(subtitle2, subtitle.getId())) ? false : true;
            boolean zChanged = composer.changed(lVar) | composer.changed(i10);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new a1(i10, 0, lVar);
                composer.updateRememberedValue(objRememberedValue);
            }
            MobileTrackItem(strM, strU0, z10, (r7.a) objRememberedValue, composer, 0);
            BoxKt.Box(BackgroundKt.m179backgroundbw27NRU$default(SizeKt.m564height3ABfNKs(PaddingKt.m531paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m5678constructorimpl(8), 0.0f, 2, null), Dp.m5678constructorimpl(1)), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.06f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null), composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$13$2$2$0$1$4$0$4$0(r7.l lVar, int i10) {
        lVar.invoke(Integer.valueOf(i10 + 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object SubtitleMenu$lambda$13$2$2$0$2(int i10, AudioTrackInfo audioTrackInfo) {
        return audioTrackKey(audioTrackInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$14(List list, Subtitle subtitle, boolean z, boolean z5, String str, String str2, boolean z10, boolean z11, List list2, int i10, int i11, int i12, List list3, int i13, int i14, int i15, String str3, r7.l lVar, r7.l lVar2, r7.l lVar3, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, int i16, int i17, int i18, int i19, Composer composer, int i20) {
        SubtitleMenu(list, subtitle, z, z5, str, str2, z10, z11, list2, i10, i11, i12, list3, i13, i14, i15, str3, lVar, lVar2, lVar3, aVar, aVar2, aVar3, aVar4, composer, RecomposeScopeImplKt.updateChangedFlags(i16 | 1), RecomposeScopeImplKt.updateChangedFlags(i17), RecomposeScopeImplKt.updateChangedFlags(i18), i19);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$6$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$7$1$0$0$0(r7.l lVar) {
        lVar.invoke(0);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$7$1$0$1$0(r7.l lVar) {
        lVar.invoke(1);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$7$1$1$0$0$0(List list, final int i10, final int i11, final Subtitle subtitle, final boolean z, boolean z5, boolean z10, String str, LazyListScope lazyListScope) {
        LazyListScope.CC.i(lazyListScope, null, null, ComposableLambdaKt.composableLambdaInstance(-1646373973, true, new r7.q() { // from class: com.arflix.tv.ui.screens.player.c1
            @Override // r7.q
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                int iIntValue = ((Integer) obj3).intValue();
                return PlayerScreenKt.SubtitleMenu$lambda$7$1$1$0$0$0$0(i10, i11, subtitle, z, (LazyItemScope) obj, (Composer) obj2, iIntValue);
            }
        }), 3, null);
        lazyListScope.items(list.size(), null, new PlayerScreenKt$SubtitleMenu$lambda$7$1$1$0$0$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new PlayerScreenKt$SubtitleMenu$lambda$7$1$1$0$0$0$$inlined$itemsIndexed$default$3(list, i10, i11, z5, z10, str, subtitle)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$7$1$1$0$0$0$0(int i10, int i11, Subtitle subtitle, boolean z, LazyItemScope lazyItemScope, Composer composer, int i12) {
        if ((i12 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1646373973, i12, -1, "com.arflix.tv.ui.screens.player.SubtitleMenu.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PlayerScreen.kt:4839)");
            }
            LangPanelItem("Off", 0, i10 == 0 && i11 == 0, i11 == 0, subtitle == null && !z, composer, 54);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$7$1$1$0$2$0(boolean z, boolean z5, x6.x xVar, final boolean z10, int i10, int i11, final int i12, final String str, final boolean z11, String str2, Subtitle subtitle, int i13, boolean z12, LazyListScope lazyListScope) {
        final int i14;
        final int i15;
        if (z) {
            i14 = i10;
            i15 = i11;
            LazyListScope.CC.i(lazyListScope, null, null, ComposableLambdaKt.composableLambdaInstance(-266073238, true, new r7.q() { // from class: com.arflix.tv.ui.screens.player.o0
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return PlayerScreenKt.SubtitleMenu$lambda$7$1$1$0$2$0$0(z10, i14, i15, (LazyItemScope) obj, (Composer) obj2, iIntValue);
                }
            }), 3, null);
        } else {
            i14 = i10;
            i15 = i11;
        }
        if (z5) {
            LazyListScope.CC.i(lazyListScope, null, null, ComposableLambdaKt.composableLambdaInstance(-511090783, true, new r7.q() { // from class: com.arflix.tv.ui.screens.player.p0
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return PlayerScreenKt.SubtitleMenu$lambda$7$1$1$0$2$0$1(i14, i15, i12, str, z11, (LazyItemScope) obj, (Composer) obj2, iIntValue);
                }
            }), 3, null);
        }
        List list = (List) xVar.f22609l;
        lazyListScope.items(list.size(), null, new PlayerScreenKt$SubtitleMenu$lambda$7$1$1$0$2$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new PlayerScreenKt$SubtitleMenu$lambda$7$1$1$0$2$0$$inlined$itemsIndexed$default$3(list, str2, subtitle, i13, z11, z12, i10, i11)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$7$1$1$0$2$0$0(boolean z, int i10, int i11, LazyItemScope lazyItemScope, Composer composer, int i12) {
        if ((i12 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-266073238, i12, -1, "com.arflix.tv.ui.screens.player.SubtitleMenu.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PlayerScreen.kt:4906)");
            }
            String str = z ? "Scanning…" : "Find Best Match";
            boolean z5 = i10 == 1 && i11 == 0;
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new d0(1);
                composer.updateRememberedValue(objRememberedValue);
            }
            TrackMenuItem(str, "Auto", z, z5, (r7.a) objRememberedValue, "Auto-pick the best-synced subtitle", composer, 221232, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$7$1$1$0$2$0$1(int i10, int i11, int i12, String str, boolean z, LazyItemScope lazyItemScope, Composer composer, int i13) {
        if ((i13 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-511090783, i13, -1, "com.arflix.tv.ui.screens.player.SubtitleMenu.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (PlayerScreen.kt:4919)");
            }
            boolean z5 = i10 == 1 && i11 == i12;
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new d0(0);
                composer.updateRememberedValue(objRememberedValue);
            }
            TrackMenuItem(str, "AI", z, z5, (r7.a) objRememberedValue, null, composer, 221232, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenu$lambda$7$1$1$1$0(List list, int i10, int i11, r7.l lVar, LazyListScope lazyListScope) {
        if (list.isEmpty()) {
            LazyListScope.CC.i(lazyListScope, null, null, ComposableSingletons$PlayerScreenKt.INSTANCE.m6261getLambda$619373845$app_playRelease(), 3, null);
        } else {
            lazyListScope.items(list.size(), new PlayerScreenKt$SubtitleMenu$lambda$7$1$1$1$0$$inlined$itemsIndexed$default$1(new com.arflix.tv.ui.components.l1(1), list), new PlayerScreenKt$SubtitleMenu$lambda$7$1$1$1$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new PlayerScreenKt$SubtitleMenu$lambda$7$1$1$1$0$$inlined$itemsIndexed$default$3(list, i10, i11, lVar)));
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object SubtitleMenu$lambda$7$1$1$1$0$0(int i10, AudioTrackInfo audioTrackInfo) {
        return audioTrackKey(audioTrackInfo);
    }

    private static final void SubtitleMenuItem(final String str, boolean z, boolean z5, r7.a<x6.t0> aVar, Composer composer, final int i10) {
        int i11;
        final boolean z10;
        final boolean z11;
        final r7.a<x6.t0> aVar2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1919896449);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
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
        if ((i11 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            aVar2 = aVar;
            z11 = z5;
            z10 = z;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1919896449, i11, -1, "com.arflix.tv.ui.screens.player.SubtitleMenuItem (PlayerScreen.kt:5532)");
            }
            int i12 = i11 << 3;
            TrackMenuItem(getFullLanguageName(str), null, z, z5, aVar, null, composerStartRestartGroup, (i12 & 896) | 48 | (i12 & 7168) | (i12 & 57344), 32);
            z10 = z;
            z11 = z5;
            aVar2 = aVar;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.player.q0
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return PlayerScreenKt.SubtitleMenuItem$lambda$0(str, z10, z11, aVar2, i10, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SubtitleMenuItem$lambda$0(String str, boolean z, boolean z5, r7.a aVar, int i10, Composer composer, int i11) {
        SubtitleMenuItem(str, z, z5, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:89:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void TabButton(java.lang.String r64, boolean r65, r7.a<x6.t0> r66, androidx.compose.ui.Modifier r67, androidx.compose.runtime.Composer r68, int r69, int r70) {
        /*
            Method dump skipped, instruction units count: 520
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.TabButton(java.lang.String, boolean, r7.a, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TabButton$lambda$0$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TabButton$lambda$2(String str, boolean z, r7.a aVar, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        TabButton(str, z, aVar, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01ea  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0239  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:140:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TrackMenuItem(java.lang.String r101, java.lang.String r102, boolean r103, boolean r104, r7.a<x6.t0> r105, java.lang.String r106, androidx.compose.runtime.Composer r107, int r108, int r109) {
        /*
            Method dump skipped, instruction units count: 1082
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.TrackMenuItem(java.lang.String, java.lang.String, boolean, boolean, r7.a, java.lang.String, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TrackMenuItem$lambda$0$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TrackMenuItem$lambda$2(String str, String str2, boolean z, boolean z5, r7.a aVar, String str3, int i10, int i11, Composer composer, int i12) {
        TrackMenuItem(str, str2, z, z5, aVar, str3, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer applyAudioTrackSelection(ExoPlayer exoPlayer, AudioTrackInfo audioTrackInfo, List<AudioTrackInfo> list) throws Exception {
        try {
            boolean z = true;
            TrackSelectionParameters.Builder preferredAudioLanguage = exoPlayer.getTrackSelectionParameters().buildUpon().clearOverridesOfType(1).setPreferredAudioLanguage(audioTrackInfo.getLanguage());
            com.google.common.collect.h1 groups = exoPlayer.getCurrentTracks().getGroups();
            int size = groups.size();
            int groupIndex = audioTrackInfo.getGroupIndex();
            int i10 = 0;
            if (groupIndex >= 0 && groupIndex < size) {
                Tracks.Group group = (Tracks.Group) groups.get(audioTrackInfo.getGroupIndex());
                boolean z5 = group.getType() == 1;
                int i11 = group.length;
                int trackIndex = audioTrackInfo.getTrackIndex();
                if (trackIndex < 0 || trackIndex >= i11) {
                    z = false;
                }
                if (z5 && z) {
                    preferredAudioLanguage.setOverrideForType(new TrackSelectionOverride(group.getMediaTrackGroup(), audioTrackInfo.getTrackIndex()));
                }
            }
            exoPlayer.setTrackSelectionParameters(preferredAudioLanguage.build());
            Iterator<AudioTrackInfo> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    i10 = -1;
                    break;
                }
                AudioTrackInfo next = it.next();
                if (next.getGroupIndex() == audioTrackInfo.getGroupIndex() && next.getTrackIndex() == audioTrackInfo.getTrackIndex()) {
                    break;
                }
                i10++;
            }
            Integer numValueOf = Integer.valueOf(i10);
            if (numValueOf.intValue() < 0) {
                numValueOf = null;
            }
            return Integer.valueOf(numValueOf != null ? numValueOf.intValue() : audioTrackInfo.getIndex());
        } catch (IllegalArgumentException e5) {
            androidx.compose.material3.d.y("applyAudioTrackSelection rejected stale index: ", e5.getMessage(), "PlayerScreen");
            return null;
        } catch (IllegalStateException e6) {
            androidx.compose.material3.d.y("applyAudioTrackSelection on invalid player: ", e6.getMessage(), "PlayerScreen");
            return null;
        } catch (Exception e10) {
            if (e10 instanceof CancellationException) {
                throw e10;
            }
            Log.e("PlayerScreen", "applyAudioTrackSelection unexpected error", e10);
            return null;
        }
    }

    private static final String audioTrackKey(AudioTrackInfo audioTrackInfo) {
        Integer numValueOf = Integer.valueOf(audioTrackInfo.getIndex());
        Integer numValueOf2 = Integer.valueOf(audioTrackInfo.getGroupIndex());
        Integer numValueOf3 = Integer.valueOf(audioTrackInfo.getTrackIndex());
        String language = audioTrackInfo.getLanguage();
        if (language == null) {
            language = "";
        }
        String label = audioTrackInfo.getLabel();
        if (label == null) {
            label = "";
        }
        Integer numValueOf4 = Integer.valueOf(audioTrackInfo.getChannelCount());
        Integer numValueOf5 = Integer.valueOf(audioTrackInfo.getSampleRate());
        String codec = audioTrackInfo.getCodec();
        return kotlin.collections.x.u0(t7.a.E(numValueOf, numValueOf2, numValueOf3, language, label, numValueOf4, numValueOf5, codec != null ? codec : ""), "|", null, null, null, 62);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<MediaItem.SubtitleConfiguration> buildExternalSubtitleConfigurations(List<Subtitle> list) {
        ga.i iVarQ = ga.r.Q(ga.r.I(new ga.p(list, 3), new com.arflix.tv.data.api.c(29)), new w0(2));
        ga.b bVar = new ga.b(new ga.h(iVarQ), new w0(4));
        if (!bVar.hasNext()) {
            return kotlin.collections.z.f19728i;
        }
        Object next = bVar.next();
        if (!bVar.hasNext()) {
            return Collections.singletonList(next);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(next);
        while (bVar.hasNext()) {
            arrayList.add(bVar.next());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean buildExternalSubtitleConfigurations$lambda$0(Subtitle subtitle) {
        return !subtitle.isEmbedded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MediaItem.SubtitleConfiguration buildExternalSubtitleConfigurations$lambda$1(Subtitle subtitle) {
        Object c0Var;
        String string = kotlin.text.o.L0(subtitle.getUrl()).toString();
        if (kotlin.text.o.h0(string)) {
            return null;
        }
        if (kotlin.text.u.P(string, "//", false)) {
            string = "https:".concat(string);
        }
        try {
            c0Var = new MediaItem.SubtitleConfiguration.Builder(Uri.parse(string)).setId(ADDON_SUB_ID_PREFIX + subtitleTrackId(subtitle)).setMimeType(subtitleMimeTypeFromUrl(string)).setLanguage(subtitle.getLang()).setLabel(subtitle.getLabel()).setSelectionFlags(0).setRoleFlags(128).build();
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        return (MediaItem.SubtitleConfiguration) (c0Var instanceof x6.c0 ? null : c0Var);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String buildExternalSubtitleConfigurations$lambda$2(MediaItem.SubtitleConfiguration subtitleConfiguration) {
        String str = subtitleConfiguration.id;
        return str == null ? String.valueOf(subtitleConfiguration.uri) : str;
    }

    private static final String buildPlaybackBaseMetaLine(PlayerUiState playerUiState, MediaType mediaType, Integer num, Integer num2) {
        String string;
        ArrayList arrayList = new ArrayList();
        if (mediaType == MediaType.TV) {
            if (num != null) {
                arrayList.add("Season " + num.intValue());
            }
            if (num2 != null) {
                arrayList.add("Episode " + num2.intValue());
            }
        } else {
            String releaseYear = playerUiState.getReleaseYear();
            if (releaseYear != null && (string = kotlin.text.o.L0(releaseYear).toString()) != null) {
                if (kotlin.text.o.h0(string)) {
                    string = null;
                }
                if (string != null) {
                    arrayList.add(string);
                }
            }
        }
        return kotlin.collections.x.u0(kotlin.collections.x.g0(arrayList), " | ", null, null, null, 62);
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final com.arflix.tv.ui.screens.player.PlaybackBufferProfile buildPlaybackBufferProfile(int r14, boolean r15, boolean r16) {
        /*
            r0 = 256(0x100, float:3.59E-43)
            if (r14 >= r0) goto L5
            r14 = r0
        L5:
            r1 = 768(0x300, float:1.076E-42)
            r2 = 512(0x200, float:7.17E-43)
            r3 = 384(0x180, float:5.38E-43)
            if (r15 != 0) goto L21
            if (r14 > r0) goto L10
            goto L21
        L10:
            if (r14 > r3) goto L15
            r4 = 128(0x80, float:1.8E-43)
            goto L23
        L15:
            if (r14 > r2) goto L1a
            r4 = 224(0xe0, float:3.14E-43)
            goto L23
        L1a:
            if (r14 > r1) goto L1f
            r4 = 288(0x120, float:4.04E-43)
            goto L23
        L1f:
            r4 = r3
            goto L23
        L21:
            r4 = 80
        L23:
            if (r15 != 0) goto L38
            if (r14 > r0) goto L28
            goto L38
        L28:
            if (r14 > r3) goto L2e
            r5 = 26000(0x6590, float:3.6434E-41)
        L2c:
            r7 = r5
            goto L3b
        L2e:
            if (r14 > r2) goto L34
            r5 = 34000(0x84d0, float:4.7644E-41)
            goto L2c
        L34:
            r5 = 40000(0x9c40, float:5.6052E-41)
            goto L2c
        L38:
            r5 = 20000(0x4e20, float:2.8026E-41)
            goto L2c
        L3b:
            if (r15 != 0) goto L51
            if (r14 > r0) goto L40
            goto L51
        L40:
            if (r14 > r3) goto L47
            r5 = 95000(0x17318, float:1.33123E-40)
        L45:
            r8 = r5
            goto L55
        L47:
            if (r14 > r2) goto L4d
            r5 = 130000(0x1fbd0, float:1.82169E-40)
            goto L45
        L4d:
            r5 = 170000(0x29810, float:2.38221E-40)
            goto L45
        L51:
            r5 = 70000(0x11170, float:9.8091E-41)
            goto L45
        L55:
            if (r16 == 0) goto L5f
            if (r15 != 0) goto L5b
            if (r14 > r3) goto L5f
        L5b:
            r5 = 550(0x226, float:7.71E-43)
        L5d:
            r9 = r5
            goto L67
        L5f:
            if (r16 == 0) goto L64
            r5 = 450(0x1c2, float:6.3E-43)
            goto L5d
        L64:
            r5 = 350(0x15e, float:4.9E-43)
            goto L5d
        L67:
            r5 = 5000(0x1388, float:7.006E-42)
            if (r15 != 0) goto L7b
            if (r14 > r0) goto L6e
            goto L7b
        L6e:
            if (r14 > r3) goto L72
            r10 = r5
            goto L7e
        L72:
            if (r14 > r2) goto L78
            r2 = 6500(0x1964, float:9.108E-42)
        L76:
            r10 = r2
            goto L7e
        L78:
            r2 = 8000(0x1f40, float:1.121E-41)
            goto L76
        L7b:
            r2 = 4000(0xfa0, float:5.605E-42)
            goto L76
        L7e:
            if (r15 != 0) goto L89
            if (r14 > r0) goto L83
            goto L89
        L83:
            if (r14 > r3) goto L87
            r5 = 3000(0xbb8, float:4.204E-42)
        L87:
            r12 = r5
            goto L8c
        L89:
            r5 = 2000(0x7d0, float:2.803E-42)
            goto L87
        L8c:
            com.arflix.tv.ui.screens.player.PlaybackBufferProfile r6 = new com.arflix.tv.ui.screens.player.PlaybackBufferProfile
            r0 = 1048576(0x100000, float:1.469368E-39)
            int r11 = r4 * r0
            if (r15 != 0) goto L99
            if (r14 <= r1) goto L99
            r14 = 1
        L97:
            r13 = r14
            goto L9b
        L99:
            r14 = 0
            goto L97
        L9b:
            r6.<init>(r7, r8, r9, r10, r11, r12, r13)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.buildPlaybackBufferProfile(int, boolean, boolean):com.arflix.tv.ui.screens.player.PlaybackBufferProfile");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String classifyPlaybackFailure(PlaybackException playbackException) {
        int i10 = -1;
        for (Throwable cause = playbackException; cause != null && i10 < 0; cause = cause.getCause()) {
            if (cause instanceof HttpDataSource.InvalidResponseCodeException) {
                i10 = ((HttpDataSource.InvalidResponseCodeException) cause).responseCode;
            }
        }
        if (i10 > 0) {
            return i10 == 403 ? "Source blocked" : i10 == 404 ? "Source removed" : i10 == 410 ? "Source expired" : i10 == 429 ? "Source rate-limited" : (500 > i10 || i10 >= 600) ? androidx.compose.foundation.c.o(i10, "Source error (", ")") : "Source unavailable";
        }
        StringBuilder sb2 = new StringBuilder();
        String message = playbackException.getMessage();
        if (message == null) {
            message = "";
        }
        sb2.append(message);
        sb2.append(' ');
        Throwable cause2 = playbackException.getCause();
        String message2 = cause2 != null ? cause2.getMessage() : null;
        sb2.append(message2 != null ? message2 : "");
        String lowerCase = sb2.toString().toLowerCase(Locale.ROOT);
        if (kotlin.text.o.T(lowerCase, "unknownhost", false) || kotlin.text.o.T(lowerCase, "unable to resolve host", false) || kotlin.text.o.T(lowerCase, "no address associated with hostname", false)) {
            return "Source offline";
        }
        int i11 = playbackException.errorCode;
        return (i11 == 4003 || i11 == 4001 || i11 == 4002 || i11 == 4005 || i11 == 4004) ? "Video format not supported by this device" : (i11 == 3003 || i11 == 3001) ? "Unplayable content" : (i11 == 1003 || i11 == 2002 || kotlin.text.o.T(lowerCase, "timeout", false) || kotlin.text.o.T(lowerCase, "timed out", false) || kotlin.text.o.T(lowerCase, "sockettimeout", false)) ? "Source too slow to load" : playbackException.errorCode == 2004 ? "Source rejected request" : "Playback error";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String detectAudioCodecLabel(String str, String str2) {
        StringBuilder sb2 = new StringBuilder();
        if (str != null) {
            sb2.append(str);
            sb2.append(' ');
        }
        if (str2 != null) {
            sb2.append(str2);
        }
        String lowerCase = sb2.toString().toLowerCase(Locale.ROOT);
        if (kotlin.text.o.h0(lowerCase)) {
            return null;
        }
        if (kotlin.text.o.T(lowerCase, "dts:x", false) || kotlin.text.o.T(lowerCase, "dtsx", false) || kotlin.text.o.T(lowerCase, "dts x", false)) {
            return "DTS:X";
        }
        if (kotlin.text.o.T(lowerCase, "dts-hd", false) || kotlin.text.o.T(lowerCase, "dts hd", false) || kotlin.text.o.T(lowerCase, "dtshd", false) || kotlin.text.o.T(lowerCase, "dca-ma", false) || kotlin.text.o.T(lowerCase, "dca-hd", false)) {
            return "DTS-HD";
        }
        if (kotlin.text.o.T(lowerCase, "truehd", false) && kotlin.text.o.T(lowerCase, "atmos", false)) {
            return "TrueHD Atmos";
        }
        if (kotlin.text.o.T(lowerCase, "truehd", false)) {
            return "TrueHD";
        }
        if (kotlin.text.o.T(lowerCase, "eac3", false) || kotlin.text.o.T(lowerCase, HlsSegmentFormat.E_AC3, false) || kotlin.text.o.T(lowerCase, "dd+", false)) {
            return "E-AC3";
        }
        if (kotlin.text.o.T(lowerCase, HlsSegmentFormat.AC3, false) || kotlin.text.o.T(lowerCase, "dd ", false) || kotlin.text.u.K(lowerCase, "dd", false)) {
            return "AC3";
        }
        if (kotlin.text.o.T(lowerCase, "dts", false)) {
            return "DTS";
        }
        if (kotlin.text.o.T(lowerCase, HlsSegmentFormat.AAC, false)) {
            return "AAC";
        }
        if (kotlin.text.o.T(lowerCase, HlsSegmentFormat.MP3, false)) {
            return "MP3";
        }
        if (kotlin.text.o.T(lowerCase, "opus", false)) {
            return "Opus";
        }
        if (kotlin.text.o.T(lowerCase, "flac", false)) {
            return "FLAC";
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x00a8 A[PHI: r4
      0x00a8: PHI (r4v18 long) = (r4v12 long), (r4v15 long), (r4v20 long) binds: [B:57:0x00d1, B:49:0x00bc, B:41:0x00a6] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final long estimateInitialStartupTimeoutMs(com.arflix.tv.data.model.StreamSource r15, boolean r16) {
        /*
            Method dump skipped, instruction units count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.estimateInitialStartupTimeoutMs(com.arflix.tv.data.model.StreamSource, boolean):long");
    }

    private static final Activity findActivity(Context context) {
        while (!(context instanceof Activity)) {
            if (!(context instanceof ContextWrapper)) {
                return null;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return (Activity) context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer findPreferredAudioTrackIndex(List<AudioTrackInfo> list, String str) {
        String fullLanguageName = getFullLanguageName(str);
        if (!kotlin.jvm.internal.p.a(fullLanguageName, "Unknown")) {
            ArrayList<String> arrayListH0 = kotlin.collections.x.H0(fullLanguageName.toLowerCase(Locale.ROOT), nativeAudioLanguageHints(str));
            Iterator<AudioTrackInfo> it = list.iterator();
            int i10 = 0;
            loop0: while (true) {
                if (!it.hasNext()) {
                    i10 = -1;
                    break;
                }
                AudioTrackInfo next = it.next();
                String fullLanguageName2 = getFullLanguageName(next.getLanguage());
                if (!kotlin.jvm.internal.p.a(fullLanguageName2, "Unknown") && kotlin.text.u.L(fullLanguageName2, fullLanguageName, true)) {
                    break;
                }
                String label = next.getLabel();
                String string = label != null ? kotlin.text.o.L0(label.toLowerCase(Locale.ROOT)).toString() : null;
                if (string == null) {
                    string = "";
                }
                if (!kotlin.text.o.h0(string) && !arrayListH0.isEmpty()) {
                    for (String str2 : arrayListH0) {
                        if (!kotlin.text.o.h0(str2) && kotlin.text.o.T(string, str2, false)) {
                            break loop0;
                        }
                    }
                }
                i10++;
            }
            Integer numValueOf = Integer.valueOf(i10);
            if (i10 >= 0) {
                return numValueOf;
            }
        }
        return null;
    }

    private static final String formatFileSize(long j10) {
        if (j10 >= 1073741824) {
            return String.format("%.1f GB", Arrays.copyOf(new Object[]{Double.valueOf(j10 / 1.073741824E9d)}, 1));
        }
        if (j10 >= 1048576) {
            return String.format("%.0f MB", Arrays.copyOf(new Object[]{Double.valueOf(j10 / 1048576.0d)}, 1));
        }
        if (j10 >= 1024) {
            return String.format("%.0f KB", Arrays.copyOf(new Object[]{Double.valueOf(j10 / 1024.0d)}, 1));
        }
        return j10 + " B";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String formatMatchOffset(long j10) {
        return androidx.compose.foundation.c.t(j10 >= 0 ? "+" : "-", String.format(Locale.US, "%.1f", Arrays.copyOf(new Object[]{Double.valueOf(Math.abs(j10) / 1000.0d)}, 1)), CmcdData.STREAMING_FORMAT_SS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String formatPlayerClockTime(long j10, String str) {
        return new SimpleDateFormat(kotlin.jvm.internal.p.a(str, "12h") ? "h:mm a" : "HH:mm", Locale.getDefault()).format(new Date(j10));
    }

    private static final String formatStreamSizeInGb(StreamSource streamSource) {
        Object next;
        Long videoSize;
        Long lValueOf = Long.valueOf(parseSizeToBytes(streamSource.getSize()));
        Long lValueOf2 = Long.valueOf(parseSizeToBytes(streamSource.getSource()));
        String description = streamSource.getDescription();
        if (description == null) {
            description = "";
        }
        Long lValueOf3 = Long.valueOf(parseSizeToBytes(description));
        StreamBehaviorHints behaviorHints = streamSource.getBehaviorHints();
        String filename = behaviorHints != null ? behaviorHints.getFilename() : null;
        Long lValueOf4 = Long.valueOf(parseSizeToBytes(filename != null ? filename : ""));
        Long lValueOf5 = Long.valueOf(parseSizeToBytes(streamSource.getQuality()));
        Long sizeBytes = streamSource.getSizeBytes();
        Long lValueOf6 = Long.valueOf(sizeBytes != null ? sizeBytes.longValue() : 0L);
        StreamBehaviorHints behaviorHints2 = streamSource.getBehaviorHints();
        Iterator it = kotlin.collections.r.G(new Long[]{lValueOf, lValueOf2, lValueOf3, lValueOf4, lValueOf5, lValueOf6, Long.valueOf((behaviorHints2 == null || (videoSize = behaviorHints2.getVideoSize()) == null) ? 0L : videoSize.longValue())}).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((Number) next).longValue() > 0) {
                break;
            }
        }
        if (((Long) next) != null) {
            return String.format(Locale.US, "%.2f GB", Arrays.copyOf(new Object[]{Double.valueOf(r1.longValue() / 1.073741824E9d)}, 1));
        }
        return null;
    }

    private static final String formatTime(long j10) {
        if (j10 <= 0) {
            return "00:00";
        }
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long hours = timeUnit.toHours(j10);
        long j11 = 60;
        long minutes = timeUnit.toMinutes(j10) % j11;
        long seconds = timeUnit.toSeconds(j10) % j11;
        return hours > 0 ? String.format("%d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(hours), Long.valueOf(minutes), Long.valueOf(seconds)}, 3)) : String.format("%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(minutes), Long.valueOf(seconds)}, 2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:400:0x04ff, code lost:
    
        if (r2.equals("unknown") == false) goto L490;
     */
    /* JADX WARN: Code restructure failed: missing block: B:402:0x0503, code lost:
    
        return "Unknown";
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x010f, code lost:
    
        if (r2.equals(androidx.media3.common.C.LANGUAGE_UNDETERMINED) == false) goto L490;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String getFullLanguageName(java.lang.String r4) {
        /*
            Method dump skipped, instruction units count: 2056
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.getFullLanguageName(java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String guessCastMimeType(String str) {
        return kotlin.text.o.T(str, ".m3u8", true) ? MimeTypes.APPLICATION_M3U8 : kotlin.text.o.T(str, ".mpd", true) ? MimeTypes.APPLICATION_MPD : MimeTypes.VIDEO_MP4;
    }

    /* JADX INFO: renamed from: handleSubtitleMenuKey-SfgetU0, reason: not valid java name */
    private static final boolean m6265handleSubtitleMenuKeySfgetU0(long j10, int i10, int i11, r7.l<? super Integer, x6.t0> lVar, r7.a<x6.t0> aVar, r7.a<x6.t0> aVar2) {
        Key.Companion companion = Key.INSTANCE;
        if (Key.m4093equalsimpl0(j10, companion.m4108getBackEK5gGoQ()) || Key.m4093equalsimpl0(j10, companion.m4182getEscapeEK5gGoQ())) {
            aVar.invoke();
            return true;
        }
        if (Key.m4093equalsimpl0(j10, companion.m4171getDirectionUpEK5gGoQ())) {
            if (i10 > 0) {
                lVar.invoke(Integer.valueOf(i10 - 1));
            }
            return true;
        }
        if (Key.m4093equalsimpl0(j10, companion.m4166getDirectionDownEK5gGoQ())) {
            if (i10 < i11 - 1) {
                lVar.invoke(Integer.valueOf(i10 + 1));
            }
            return true;
        }
        if (!Key.m4093equalsimpl0(j10, companion.m4179getEnterEK5gGoQ()) && !Key.m4093equalsimpl0(j10, companion.m4165getDirectionCenterEK5gGoQ())) {
            return false;
        }
        aVar2.invoke();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isBitmapSubtitleMime(String str) {
        String string;
        return (str == null || (string = kotlin.text.o.L0(str.toLowerCase(Locale.ROOT)).toString()) == null || (!string.equals(MimeTypes.APPLICATION_PGS) && !string.equals(MimeTypes.APPLICATION_VOBSUB) && !string.equals(MimeTypes.APPLICATION_DVBSUBS) && !kotlin.text.o.T(string, "pgs", false) && !kotlin.text.o.T(string, "vobsub", false) && !kotlin.text.o.T(string, "dvbsub", false))) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isLikelyDolbyVisionStream(StreamSource streamSource) {
        String filename;
        if (streamSource != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(streamSource.getQuality());
            sb2.append(' ');
            sb2.append(streamSource.getSource());
            sb2.append(' ');
            sb2.append(streamSource.getAddonName());
            StreamBehaviorHints behaviorHints = streamSource.getBehaviorHints();
            if (behaviorHints != null && (filename = behaviorHints.getFilename()) != null) {
                sb2.append(' ');
                sb2.append(filename);
            }
            String lowerCase = sb2.toString().toLowerCase(Locale.ROOT);
            if (kotlin.text.o.T(lowerCase, "dolby vision", false) || kotlin.text.o.T(lowerCase, " dovi", false) || kotlin.text.o.T(lowerCase, " dv ", false) || kotlin.text.o.T(lowerCase, " dvp", false) || kotlin.text.o.T(lowerCase, "hdr10+dv", false)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isLikelyHeavyStream(StreamSource streamSource) {
        String filename;
        if (streamSource != null) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(streamSource.getQuality());
            sb2.append(' ');
            sb2.append(streamSource.getSource());
            sb2.append(' ');
            sb2.append(streamSource.getAddonName());
            StreamBehaviorHints behaviorHints = streamSource.getBehaviorHints();
            if (behaviorHints != null && (filename = behaviorHints.getFilename()) != null) {
                sb2.append(' ');
                sb2.append(filename);
            }
            String lowerCase = sb2.toString().toLowerCase(Locale.ROOT);
            if (parseSizeToBytes(streamSource.getSize()) >= 21474836480L || kotlin.text.o.T(lowerCase, "4k", false) || kotlin.text.o.T(lowerCase, "2160", false) || kotlin.text.o.T(lowerCase, "remux", false) || kotlin.text.o.T(lowerCase, "dolby vision", false) || kotlin.text.o.T(lowerCase, " dovi", false)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isLikelyHlsPlaybackUrl(String str, StreamSource streamSource) {
        Object c0Var;
        String lowerCase = str.toLowerCase(Locale.ROOT);
        if (kotlin.text.o.T(lowerCase, ".m3u8", false) || kotlin.text.o.T(lowerCase, "/hls", false) || kotlin.text.o.T(lowerCase, "format=hls", false)) {
            return true;
        }
        try {
            c0Var = Uri.parse(str);
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (c0Var instanceof x6.c0) {
            c0Var = null;
        }
        Uri uri = (Uri) c0Var;
        if (uri == null) {
            return false;
        }
        String host = uri.getHost();
        if (host == null) {
            host = "";
        }
        Locale locale = Locale.ROOT;
        String lowerCase2 = host.toLowerCase(locale);
        String path = uri.getPath();
        if (path == null) {
            path = "";
        }
        String lowerCase3 = path.toLowerCase(locale);
        StringBuilder sb2 = new StringBuilder();
        String addonId = streamSource != null ? streamSource.getAddonId() : null;
        if (addonId == null) {
            addonId = "";
        }
        sb2.append(addonId);
        sb2.append(' ');
        String addonName = streamSource != null ? streamSource.getAddonName() : null;
        if (addonName == null) {
            addonName = "";
        }
        sb2.append(addonName);
        sb2.append(' ');
        String source = streamSource != null ? streamSource.getSource() : null;
        if (source == null) {
            source = "";
        }
        sb2.append(source);
        sb2.append(' ');
        String quality = streamSource != null ? streamSource.getQuality() : null;
        sb2.append(quality != null ? quality : "");
        String lowerCase4 = sb2.toString().toLowerCase(locale);
        if (kotlin.text.u.P(lowerCase3, "/playlist/", false)) {
            return kotlin.text.o.T(lowerCase2, "highfly", false) || kotlin.text.o.T(lowerCase4, "highfly", false) || kotlin.text.o.T(lowerCase4, SportsAddonCapabilities.SPORTS_CATEGORY_ROW_ID, false);
        }
        return false;
    }

    private static final boolean isSafePlaybackHeader(String str, String str2) {
        if (!kotlin.text.o.h0(str) && !kotlin.text.o.h0(str2)) {
            for (int i10 = 0; i10 < str.length(); i10++) {
                char cCharAt = str.charAt(i10);
                if ('!' > cCharAt || cCharAt >= 127 || kotlin.collections.r.p0(new Character[]{'(', ')', '<', '>', '@', ',', ';', ':', '\\', '\"', '/', '[', ']', '?', '=', '{', '}'}).contains(Character.valueOf(cCharAt))) {
                    return false;
                }
            }
            for (int i11 = 0; i11 < str2.length(); i11++) {
                char cCharAt2 = str2.charAt(i11);
                if (cCharAt2 != '\t' && (' ' > cCharAt2 || cCharAt2 >= 127)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isSameSubtitleTrack(Subtitle subtitle, String str) {
        return subtitle != null && kotlin.jvm.internal.p.a(subtitleBaseId(subtitle.getId()), subtitleBaseId(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long matchedOffsetMsFor(Subtitle subtitle, String str) {
        Long lS;
        if (!isSameSubtitleTrack(subtitle, str) || (lS = kotlin.text.u.S(kotlin.text.o.D0(subtitle.getId(), "#ofs", ""))) == null || lS.longValue() == 0) {
            return null;
        }
        return lS;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static final List<String> nativeAudioLanguageHints(String str) {
        String fullLanguageName = getFullLanguageName(str);
        switch (fullLanguageName.hashCode()) {
            case -1898802383:
                if (fullLanguageName.equals("Polish")) {
                    return t7.a.E("polski", "polskie", "polsku", "lektor", "dubbing pl");
                }
                break;
            case -1463714219:
                if (fullLanguageName.equals("Portuguese")) {
                    return t7.a.E("português", "portugues");
                }
                break;
            case -1074763917:
                if (fullLanguageName.equals("Russian")) {
                    return t7.a.E("русский", "русская", "rus");
                }
                break;
            case -539078964:
                if (fullLanguageName.equals("Ukrainian")) {
                    return t7.a.E("українська", "ukr");
                }
                break;
            case -517823520:
                if (fullLanguageName.equals("Italian")) {
                    return Collections.singletonList("italiano");
                }
                break;
            case -347177772:
                if (fullLanguageName.equals("Spanish")) {
                    return t7.a.E("español", "espanol", "castellano");
                }
                break;
            case 65610643:
                if (fullLanguageName.equals("Czech")) {
                    return t7.a.E("čeština", "cesky", "dabing");
                }
                break;
            case 2112439738:
                if (fullLanguageName.equals("French")) {
                    return t7.a.E("français", "francais");
                }
                break;
            case 2129449382:
                if (fullLanguageName.equals("German")) {
                    return Collections.singletonList("deutsch");
                }
                break;
        }
        return kotlin.collections.z.f19728i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ad  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final long parseSizeToBytes(java.lang.String r4) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.parseSizeToBytes(java.lang.String):long");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String playbackErrorMessageFor(androidx.media3.common.PlaybackException r1, boolean r2) {
        /*
            int r1 = r1.errorCode
            r0 = 1003(0x3eb, float:1.406E-42)
            if (r1 == r0) goto L36
            r0 = 2004(0x7d4, float:2.808E-42)
            if (r1 == r0) goto L33
            r0 = 3001(0xbb9, float:4.205E-42)
            if (r1 == r0) goto L30
            r0 = 3003(0xbbb, float:4.208E-42)
            if (r1 == r0) goto L30
            r0 = 2001(0x7d1, float:2.804E-42)
            if (r1 == r0) goto L36
            r0 = 2002(0x7d2, float:2.805E-42)
            if (r1 == r0) goto L36
            r0 = 4001(0xfa1, float:5.607E-42)
            if (r1 == r0) goto L2d
            r0 = 4002(0xfa2, float:5.608E-42)
            if (r1 == r0) goto L2d
            r0 = 4004(0xfa4, float:5.611E-42)
            if (r1 == r0) goto L2d
            r0 = 4005(0xfa5, float:5.612E-42)
            if (r1 == r0) goto L2d
            java.lang.String r1 = "Source failed to play"
            goto L38
        L2d:
            java.lang.String r1 = "Codec not supported by this device"
            goto L38
        L30:
            java.lang.String r1 = "Source format is invalid or unsupported"
            goto L38
        L33:
            java.lang.String r1 = "Source server rejected playback request"
            goto L38
        L36:
            java.lang.String r1 = "Network timeout while loading source"
        L38:
            if (r2 == 0) goto L41
            java.lang.String r2 = ". Try another source."
            java.lang.String r1 = r1.concat(r2)
            return r1
        L41:
            java.lang.String r2 = " during startup. Trying another source may work."
            java.lang.String r1 = r1.concat(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt.playbackErrorMessageFor(androidx.media3.common.PlaybackException, boolean):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void playbackStartupDiag(String str) {
        a2.v("[PlaybackStartup] ", str, System.err);
    }

    private static final int readMedia3FrameRateConst(String str, int i10) {
        Object c0Var;
        try {
            c0Var = Integer.valueOf(C.class.getField(str).getInt(null));
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        Object objValueOf = Integer.valueOf(i10);
        if (c0Var instanceof x6.c0) {
            c0Var = objValueOf;
        }
        return ((Number) c0Var).intValue();
    }

    private static final String rememberPlayerClockFormat(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(263816012, i10, -1, "com.arflix.tv.ui.screens.player.rememberPlayerClockFormat (PlayerScreen.kt:4674)");
        }
        Context context = (Context) composer.consume(AndroidCompositionLocals_androidKt.getLocalContext());
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("24h", null, 2, null);
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableState mutableState = (MutableState) objRememberedValue;
        boolean zChangedInstance = composer.changedInstance(context);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new PlayerScreenKt$rememberPlayerClockFormat$1$1(context, mutableState, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        EffectsKt.LaunchedEffect(context, (r7.p<? super ka.k0, ? super d7.d<? super x6.t0>, ? extends Object>) objRememberedValue2, composer, 0);
        String strRememberPlayerClockFormat$lambda$1 = rememberPlayerClockFormat$lambda$1(mutableState);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return strRememberPlayerClockFormat$lambda$1;
    }

    private static final String rememberPlayerClockFormat$lambda$1(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int resolveFrameRateOffStrategy() {
        return readMedia3FrameRateConst("VIDEO_CHANGE_FRAME_RATE_STRATEGY_OFF", 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<String, String> safePlaybackHeaders(Map<String, String> map) {
        if (map.isEmpty()) {
            return kotlin.collections.a0.f19683i;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (isSafePlaybackHeader(kotlin.text.o.L0(entry.getKey()).toString(), kotlin.text.o.L0(entry.getValue()).toString())) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(kotlin.collections.i0.q0(linkedHashMap.size()));
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            linkedHashMap2.put(kotlin.text.o.L0((String) entry2.getKey()).toString(), entry2.getValue());
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(kotlin.collections.i0.q0(linkedHashMap2.size()));
        for (Map.Entry entry3 : linkedHashMap2.entrySet()) {
            linkedHashMap3.put(entry3.getKey(), kotlin.text.o.L0((String) entry3.getValue()).toString());
        }
        return linkedHashMap3;
    }

    private static final String subtitleBaseId(String str) {
        return kotlin.text.o.H0(str, "#ofs");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int subtitleMatchScore(String str, Subtitle subtitle) {
        if (subtitle.isEmbedded()) {
            return 100;
        }
        return SubtitleScoringKt.weightedSubtitleScore(str, subtitle.getId());
    }

    private static final String subtitleMimeTypeFromUrl(String str) {
        String lowerCase = kotlin.text.o.O0(kotlin.text.o.G0('?', str, str), '/').toLowerCase(Locale.ROOT);
        return kotlin.text.u.K(lowerCase, ".vtt", false) ? MimeTypes.TEXT_VTT : (kotlin.text.u.K(lowerCase, ".srt", false) || kotlin.text.u.K(lowerCase, ".srt.gz", false)) ? MimeTypes.APPLICATION_SUBRIP : (kotlin.text.u.K(lowerCase, ".ass", false) || kotlin.text.u.K(lowerCase, ".ssa", false)) ? MimeTypes.TEXT_SSA : (kotlin.text.u.K(lowerCase, ".ttml", false) || kotlin.text.u.K(lowerCase, ".dfxp", false)) ? MimeTypes.APPLICATION_TTML : MimeTypes.APPLICATION_SUBRIP;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String subtitleTrackId(Subtitle subtitle) {
        String string = kotlin.text.o.L0(subtitle.getId()).toString();
        if (!kotlin.text.o.h0(string)) {
            String string2 = kotlin.text.o.L0(subtitle.getProvider()).toString();
            return kotlin.text.o.h0(string2) ? string : androidx.compose.foundation.c.t(string2, "|", string);
        }
        String string3 = kotlin.text.o.L0(subtitle.getUrl()).toString();
        if (kotlin.text.o.h0(string3)) {
            String string4 = kotlin.text.o.L0(subtitle.getLang()).toString();
            Locale locale = Locale.ROOT;
            string3 = androidx.compose.foundation.c.t(string4.toLowerCase(locale), "|", kotlin.text.o.L0(subtitle.getLabel()).toString().toLowerCase(locale));
        }
        int iHashCode = string3.hashCode();
        com.google.common.util.concurrent.r0.c(16);
        return androidx.compose.material3.d.C("ext_", m2.f0.V(16, ((long) iHashCode) & 4294967295L));
    }
}
