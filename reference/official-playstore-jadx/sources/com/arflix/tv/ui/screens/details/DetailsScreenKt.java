package com.arflix.tv.ui.screens.details;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.SystemClock;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.AspectRatioKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.ScheduleKt;
import androidx.compose.material.icons.filled.StarKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MenuKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.fragment.app.a2;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.tv.foundation.lazy.list.LazyDslKt;
import androidx.tv.foundation.lazy.list.LazyListStateKt;
import androidx.tv.material3.TextKt;
import coil.ImageLoader;
import coil.compose.AsyncImageKt;
import coil.compose.SingletonAsyncImageKt;
import coil.request.ImageRequest;
import com.arflix.tv.R;
import com.arflix.tv.data.model.CastMember;
import com.arflix.tv.data.model.Episode;
import com.arflix.tv.data.model.EpisodeIdentity;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.data.model.Review;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.ui.focus.ArvioDpadFocusKt;
import com.arflix.tv.ui.skin.ArvioFocusKt;
import com.arflix.tv.ui.skin.ArvioSkin;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.util.MediaBadgesKt;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b<\n\u0002\u0018\u0002\n\u0002\b\u001b\u001aµ\u0002\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2H\u0010\u000f\u001aD\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\n2\u0018\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00102\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u000e0\u00122\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0007¢\u0006\u0004\b\u001c\u0010\u001d\u001a÷\u0001\u00101\u001a\u0002002\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\u00022\u0006\u0010'\u001a\u00020\u00022\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u0012H\u0002¢\u0006\u0004\b1\u00102\u001aÿ\u0001\u00105\u001a\u0002002\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\u00022\u0006\u0010'\u001a\u00020\u00022\u0006\u00104\u001a\u0002032\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u0012H\u0002¢\u0006\u0004\b5\u00106\u001a§\u0004\u0010e\u001a\u00020\u000e2\u0006\u00108\u001a\u0002072\b\u00109\u001a\u0004\u0018\u00010\f2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020;0:2\u0006\u0010=\u001a\u00020\u00022\u0006\u0010>\u001a\u00020\u00022\f\u0010@\u001a\b\u0012\u0004\u0012\u00020?0:2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020A0:2\f\u0010C\u001a\b\u0012\u0004\u0012\u0002070:2\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0D2\u000e\b\u0002\u0010F\u001a\b\u0012\u0004\u0012\u0002070:2\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010H\u001a\u0002002\b\b\u0002\u0010I\u001a\u00020\u00022\u0006\u0010J\u001a\u00020\u001e2\u0006\u0010K\u001a\u00020\u00022\u0006\u0010L\u001a\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\u0006\u0010N\u001a\u00020\u00022\u0006\u0010O\u001a\u00020\u00022\u0006\u0010P\u001a\u00020\u00022\u0006\u0010Q\u001a\u00020\u00022\u0006\u0010R\u001a\u0002002\u000e\b\u0002\u0010S\u001a\b\u0012\u0004\u0012\u00020\f0:2\n\b\u0002\u0010T\u001a\u0004\u0018\u00010\f2 \b\u0002\u0010V\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020U0D2\n\b\u0002\u0010W\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010X\u001a\u0002002\b\b\u0002\u0010Y\u001a\u0002002\b\b\u0002\u0010Z\u001a\u0002002\b\b\u0002\u0010[\u001a\u0002002\b\b\u0002\u0010\\\u001a\u0002002\u000e\b\u0002\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\u0014\b\u0002\u0010]\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0014\b\u0002\u0010^\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0016\b\u0002\u0010_\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00122\u0014\b\u0002\u0010`\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0014\b\u0002\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\b\b\u0002\u0010b\u001a\u0002002\u0014\b\u0002\u0010c\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0014\b\u0002\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u0012H\u0003¢\u0006\u0004\be\u0010f\u001a¡\u0003\u0010q\u001a\u00020\u000e2\u0006\u0010h\u001a\u00020g2\u0006\u00108\u001a\u0002072\f\u0010<\u001a\b\u0012\u0004\u0012\u00020;0:2\u0006\u0010=\u001a\u00020\u00022\u0006\u0010>\u001a\u00020\u00022\f\u0010@\u001a\b\u0012\u0004\u0012\u00020?0:2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020A0:2\f\u0010C\u001a\b\u0012\u0004\u0012\u0002070:2\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0D2\u000e\b\u0002\u0010F\u001a\b\u0012\u0004\u0012\u0002070:2\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010I\u001a\u00020\u00022\u0006\u0010J\u001a\u00020\u001e2\b\u0010i\u001a\u0004\u0018\u00010\u001e2\u0006\u0010L\u001a\u00020\u00022\u0006\u0010M\u001a\u00020\u00022\u0006\u0010N\u001a\u00020\u00022\u0006\u0010O\u001a\u00020\u00022\u0006\u0010P\u001a\u00020\u00022\u0006\u0010Q\u001a\u00020\u00022\u001e\u0010V\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020U0D2\u0006\u0010Z\u001a\u0002002\u0006\u0010[\u001a\u0002002\u0006\u0010b\u001a\u0002002\u0006\u0010k\u001a\u00020j2\u0006\u0010l\u001a\u00020j2\u0006\u0010n\u001a\u00020m2\u0006\u0010Y\u001a\u0002002\u0012\u0010^\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010`\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0012\u0010c\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u00122\u0014\b\u0002\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u0012H\u0003¢\u0006\u0004\bo\u0010p\u001a\u0083\u0001\u0010v\u001a\u00020\u000e2\u0006\u0010=\u001a\u00020\u00022\u0006\u0010>\u001a\u00020\u00022\f\u0010<\u001a\b\u0012\u0004\u0012\u00020;0:2\u001e\u0010V\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020U0D2\b\u0010i\u001a\u0004\u0018\u00010\u001e2\u0006\u0010N\u001a\u00020\u00022\u0006\u0010r\u001a\u00020j2\u0006\u0010s\u001a\u00020j2\u0012\u0010^\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u0012H\u0003¢\u0006\u0004\bt\u0010u\u001a\u0017\u0010z\u001a\u00020y2\u0006\u0010x\u001a\u00020wH\u0002¢\u0006\u0004\bz\u0010{\u001a\u0017\u0010|\u001a\u00020y2\u0006\u0010x\u001a\u00020wH\u0002¢\u0006\u0004\b|\u0010{\u001a\u0017\u0010~\u001a\u00020\f2\u0006\u0010}\u001a\u00020\fH\u0002¢\u0006\u0004\b~\u0010\u007f\u001aj\u0010\u0082\u0001\u001a\u00020\u000e2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020;0:2\u0006\u0010=\u001a\u00020\u00022\u0006\u0010>\u001a\u00020\u00022\u0006\u0010L\u001a\u00020\u00022\b\b\u0002\u0010M\u001a\u00020\u00022\u0006\u0010\\\u001a\u0002002\n\b\u0002\u0010i\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010r\u001a\u00020j2\b\b\u0002\u0010s\u001a\u00020jH\u0003¢\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001\u001af\u0010\u0085\u0001\u001a\u00020\u000e2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020;0:2\u0006\u0010L\u001a\u00020\u00022\b\u0010i\u001a\u0004\u0018\u00010\u001e2\u0006\u0010n\u001a\u00020m2\u0006\u0010r\u001a\u00020j2\u0006\u0010s\u001a\u00020j2\u0006\u0010b\u001a\u0002002\u0012\u0010`\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u0012H\u0003¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001\u001aV\u0010\u0088\u0001\u001a\u00020\u000e2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020?0:2\u0006\u0010O\u001a\u00020\u00022\b\u0010i\u001a\u0004\u0018\u00010\u001e2\u0006\u0010r\u001a\u00020j2\u0006\u0010s\u001a\u00020j2\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u0012H\u0003¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001\u001aB\u0010\u008b\u0001\u001a\u00020\u000e2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020A0:2\u0006\u0010P\u001a\u00020\u00022\b\u0010i\u001a\u0004\u0018\u00010\u001e2\u0006\u0010r\u001a\u00020j2\u0006\u0010s\u001a\u00020jH\u0003¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001\u001ar\u0010\u008e\u0001\u001a\u00020\u000e2\f\u0010C\u001a\b\u0012\u0004\u0012\u0002070:2\u0012\u0010E\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0D2\u0006\u0010Q\u001a\u00020\u00022\b\u0010i\u001a\u0004\u0018\u00010\u001e2\u0006\u0010Z\u001a\u0002002\u0006\u0010r\u001a\u00020j2\u0006\u0010s\u001a\u00020j2\u0012\u0010c\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u0012H\u0003¢\u0006\u0006\b\u008c\u0001\u0010\u008d\u0001\u001ah\u0010\u0091\u0001\u001a\u00020\u000e2\f\u0010F\u001a\b\u0012\u0004\u0012\u0002070:2\b\u0010G\u001a\u0004\u0018\u00010\f2\u0006\u0010I\u001a\u00020\u00022\b\u0010i\u001a\u0004\u0018\u00010\u001e2\u0006\u0010Z\u001a\u0002002\u0006\u0010r\u001a\u00020j2\u0006\u0010s\u001a\u00020j2\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000e0\u0012H\u0003¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001\u001a-\u0010\u0097\u0001\u001a\u0002002\u0007\u0010\u0092\u0001\u001a\u00020\u00022\u0007\u0010\u0093\u0001\u001a\u00020j2\u0007\u0010\u0094\u0001\u001a\u00020jH\u0003¢\u0006\u0006\b\u0095\u0001\u0010\u0096\u0001\u001a$\u0010\u0099\u0001\u001a\u0002002\u0007\u0010\u0092\u0001\u001a\u00020\u00022\u0007\u0010\u0098\u0001\u001a\u00020\u0002H\u0002¢\u0006\u0006\b\u0099\u0001\u0010\u009a\u0001\u001a6\u0010 \u0001\u001a\u00020j2\u0007\u0010\u0093\u0001\u001a\u00020j2\u0007\u0010\u009b\u0001\u001a\u00020j2\u0007\u0010\u009c\u0001\u001a\u00020j2\u0007\u0010\u009d\u0001\u001a\u00020jH\u0003¢\u0006\u0006\b\u009e\u0001\u0010\u009f\u0001\u001a*\u0010¤\u0001\u001a\u00020\u000e*\u00030¡\u00012\u0007\u0010¢\u0001\u001a\u00020w2\u0007\u0010£\u0001\u001a\u00020\u0002H\u0082@¢\u0006\u0006\b¤\u0001\u0010¥\u0001\u001a6\u0010«\u0001\u001a\u00020\u000e2\u0007\u0010\u009b\u0001\u001a\u00020j2\u0007\u0010¦\u0001\u001a\u00020j2\u0007\u0010§\u0001\u001a\u00020j2\u0007\u0010¨\u0001\u001a\u00020wH\u0003¢\u0006\u0006\b©\u0001\u0010ª\u0001\u001aI\u0010°\u0001\u001a\u00020\u000e2\b\u0010¬\u0001\u001a\u00030¡\u00012\u0007\u0010\u00ad\u0001\u001a\u0002002\u0007\u0010\u0098\u0001\u001a\u00020\u00022\u0007\u0010\u0092\u0001\u001a\u00020\u00022\u0007\u0010\u0093\u0001\u001a\u00020j2\u0007\u0010\u0094\u0001\u001a\u00020jH\u0003¢\u0006\u0006\b®\u0001\u0010¯\u0001\u001a\u001a\u0010±\u0001\u001a\u00020\f2\u0006\u00108\u001a\u000207H\u0002¢\u0006\u0006\b±\u0001\u0010²\u0001\u001aI\u0010¼\u0001\u001a\u00020\u000e2\u0006\u0010x\u001a\u00020\f2\b\u0010´\u0001\u001a\u00030³\u00012\u0007\u0010µ\u0001\u001a\u00020\u00022\u0007\u0010¶\u0001\u001a\u00020j2\u0007\u0010·\u0001\u001a\u00020j2\b\u0010¹\u0001\u001a\u00030¸\u0001H\u0003¢\u0006\u0006\bº\u0001\u0010»\u0001\u001a6\u0010Ã\u0001\u001a\u00020\u000e2\u0007\u0010½\u0001\u001a\u00020\f2\u0007\u0010¾\u0001\u001a\u00020\f2\u0007\u0010¿\u0001\u001a\u00020y2\u0007\u0010À\u0001\u001a\u00020yH\u0003¢\u0006\u0006\bÁ\u0001\u0010Â\u0001\u001a\u0012\u0010Ä\u0001\u001a\u00020\u000eH\u0003¢\u0006\u0006\bÄ\u0001\u0010Å\u0001\u001a_\u0010Í\u0001\u001a\u00020\u000e2\b\u0010Ç\u0001\u001a\u00030Æ\u00012\u0007\u0010È\u0001\u001a\u00020\f2\t\b\u0002\u0010É\u0001\u001a\u0002002\t\b\u0002\u0010Ê\u0001\u001a\u0002002\t\b\u0002\u0010Ë\u0001\u001a\u0002002\b\b\u0002\u0010h\u001a\u00020g2\r\u0010Ì\u0001\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0003¢\u0006\u0006\bÍ\u0001\u0010Î\u0001\u001aT\u0010Ñ\u0001\u001a\u00020\u000e2\b\u0010Ç\u0001\u001a\u00030Æ\u00012\u0007\u0010Ï\u0001\u001a\u00020\f2\t\b\u0002\u0010Ê\u0001\u001a\u0002002\t\b\u0002\u0010Ð\u0001\u001a\u0002002\b\b\u0002\u0010h\u001a\u00020g2\r\u0010Ì\u0001\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0003¢\u0006\u0006\bÑ\u0001\u0010Ò\u0001\u001aO\u0010Õ\u0001\u001a\u00020\u000e2\b\u0010Ç\u0001\u001a\u00030Æ\u00012\u0007\u0010È\u0001\u001a\u00020\f2\u0007\u0010Ó\u0001\u001a\u0002002\t\b\u0002\u0010É\u0001\u001a\u0002002\t\b\u0002\u0010Ô\u0001\u001a\u0002002\t\b\u0002\u0010Ê\u0001\u001a\u000200H\u0003¢\u0006\u0006\bÕ\u0001\u0010Ö\u0001\u001aJ\u0010Û\u0001\u001a\u00020\u000e2\u0007\u0010×\u0001\u001a\u00020;2\t\b\u0002\u0010Ø\u0001\u001a\u00020j2\u0007\u0010Ó\u0001\u001a\u0002002\b\b\u0002\u0010b\u001a\u0002002\u000f\b\u0002\u0010Ì\u0001\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0003¢\u0006\u0006\bÙ\u0001\u0010Ú\u0001\u001a\u001c\u0010Ý\u0001\u001a\u0004\u0018\u00010\f2\u0007\u0010Ü\u0001\u001a\u00020\fH\u0002¢\u0006\u0005\bÝ\u0001\u0010\u007f\u001a\u001b\u0010Þ\u0001\u001a\u0002002\u0007\u0010Ü\u0001\u001a\u00020\fH\u0002¢\u0006\u0006\bÞ\u0001\u0010ß\u0001\u001ag\u0010å\u0001\u001a\u00020\u000e2\u0007\u0010à\u0001\u001a\u00020\u00022\u0007\u0010á\u0001\u001a\u0002002\u0007\u0010Ó\u0001\u001a\u0002002\t\b\u0002\u0010â\u0001\u001a\u00020\u00022\t\b\u0002\u0010ã\u0001\u001a\u00020\u00022\u000f\b\u0002\u0010Ì\u0001\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00142\u0011\b\u0002\u0010ä\u0001\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0014H\u0003¢\u0006\u0006\bå\u0001\u0010æ\u0001\u001a$\u0010è\u0001\u001a\u00020\u000e2\u0007\u0010ç\u0001\u001a\u00020?2\u0007\u0010Ó\u0001\u001a\u000200H\u0003¢\u0006\u0006\bè\u0001\u0010é\u0001\u001a3\u0010ë\u0001\u001a\u00020\u000e2\u0007\u0010ê\u0001\u001a\u00020?2\u0007\u0010Ó\u0001\u001a\u0002002\r\u0010Ì\u0001\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0003¢\u0006\u0006\bë\u0001\u0010ì\u0001\u001a$\u0010î\u0001\u001a\u00020\u000e2\u0007\u0010í\u0001\u001a\u00020A2\u0007\u0010Ó\u0001\u001a\u000200H\u0003¢\u0006\u0006\bî\u0001\u0010ï\u0001\u001a\u001b\u0010ð\u0001\u001a\u00020\u000e2\u0007\u0010È\u0001\u001a\u00020\fH\u0003¢\u0006\u0006\bð\u0001\u0010ñ\u0001\u001a\u001a\u0010ò\u0001\u001a\u00020\u000e2\u0006\u0010x\u001a\u00020\fH\u0003¢\u0006\u0006\bò\u0001\u0010ñ\u0001\u001a\u0012\u0010ó\u0001\u001a\u00020\u000eH\u0003¢\u0006\u0006\bó\u0001\u0010Å\u0001\u001a\u001b\u0010õ\u0001\u001a\u00020\u000e2\u0007\u0010ô\u0001\u001a\u00020\fH\u0003¢\u0006\u0006\bõ\u0001\u0010ñ\u0001\u001a\u001b\u0010÷\u0001\u001a\u00020\u000e2\u0007\u0010ö\u0001\u001a\u00020\fH\u0003¢\u0006\u0006\b÷\u0001\u0010ñ\u0001\u001a\u001a\u0010ø\u0001\u001a\u00020\u000e2\u0006\u0010T\u001a\u00020\fH\u0003¢\u0006\u0006\bø\u0001\u0010ñ\u0001\u001a\u001b\u0010ú\u0001\u001a\u00020\u000e2\u0007\u0010ù\u0001\u001a\u00020\fH\u0003¢\u0006\u0006\bú\u0001\u0010ñ\u0001\u001aG\u0010ü\u0001\u001a\u00020\u000e2\u0006\u00108\u001a\u0002072\t\u0010û\u0001\u001a\u0004\u0018\u00010\f2\u0006\u0010Z\u001a\u0002002\u0007\u0010Ó\u0001\u001a\u0002002\u000f\b\u0002\u0010Ì\u0001\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0014H\u0003¢\u0006\u0006\bü\u0001\u0010ý\u0001¨\u0006\u009e\u0002²\u0006\f\u00104\u001a\u0002038\nX\u008a\u0084\u0002²\u0006\u000e\u0010J\u001a\u00020\u001e8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010K\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010L\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010M\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010N\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010O\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010P\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010Q\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010I\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010þ\u0001\u001a\u00020\r8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010ÿ\u0001\u001a\u0002008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0080\u0002\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0081\u0002\u001a\u0002008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0082\u0002\u001a\u0002008\n@\nX\u008a\u008e\u0002²\u0006\u0012\u0010\u0084\u0002\u001a\u0005\u0018\u00010\u0083\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0085\u0002\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0086\u0002\u001a\u0002008\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010\u0087\u0002\u001a\u0004\u0018\u00010;8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0088\u0002\u001a\u0002008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0089\u0002\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u008a\u0002\u001a\u00020\r8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u008b\u0002\u001a\u0002008\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010b\u001a\u0002008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u008c\u0002\u001a\u00020w8\n@\nX\u008a\u008e\u0002²\u0006\r\u0010\u008d\u0002\u001a\u00020w8\nX\u008a\u0084\u0002²\u0006\r\u0010\u008e\u0002\u001a\u00020\u00028\nX\u008a\u0084\u0002²\u0006\u000f\u0010\u008f\u0002\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0090\u0002\u001a\u0002008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0091\u0002\u001a\u0002008\n@\nX\u008a\u008e\u0002²\u0006\r\u0010\u0092\u0002\u001a\u00020j8\nX\u008a\u0084\u0002²\u0006\r\u0010\u0093\u0002\u001a\u00020j8\nX\u008a\u0084\u0002²\u0006\u000f\u0010\u0094\u0002\u001a\u0004\u0018\u00010\u001e8\nX\u008a\u0084\u0002²\u0006\r\u0010\u0095\u0002\u001a\u00020\u00028\nX\u008a\u0084\u0002²\u0006\r\u0010\u0096\u0002\u001a\u00020\u00028\nX\u008a\u0084\u0002²\u0006\u000f\u0010\u0097\u0002\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0098\u0002\u001a\u00020\u00028\n@\nX\u008a\u008e\u0002²\u0006\r\u0010\u0099\u0002\u001a\u00020j8\nX\u008a\u0084\u0002²\u0006\r\u0010\u009a\u0002\u001a\u00020j8\nX\u008a\u0084\u0002²\u0006\r\u0010\u009b\u0002\u001a\u00020w8\nX\u008a\u0084\u0002²\u0006\r\u0010\u009c\u0002\u001a\u00020w8\nX\u008a\u0084\u0002²\u0006\r\u0010¿\u0001\u001a\u00020y8\nX\u008a\u0084\u0002²\u0006\r\u0010À\u0001\u001a\u00020y8\nX\u008a\u0084\u0002²\u0006\r\u0010\u009d\u0002\u001a\u00020w8\nX\u008a\u0084\u0002²\u0006\r\u0010\u009c\u0002\u001a\u00020w8\nX\u008a\u0084\u0002²\u0006\r\u0010\u009c\u0002\u001a\u00020w8\nX\u008a\u0084\u0002²\u0006\r\u0010\u009c\u0002\u001a\u00020w8\nX\u008a\u0084\u0002²\u0006\r\u0010\u009c\u0002\u001a\u00020w8\nX\u008a\u0084\u0002²\u0006\r\u0010\u009d\u0002\u001a\u00020w8\nX\u008a\u0084\u0002"}, d2 = {"Lcom/arflix/tv/data/model/MediaType;", "mediaType", "", "mediaId", "initialSeason", "initialEpisode", "Lcom/arflix/tv/ui/screens/details/DetailsViewModel;", "viewModel", "Lcom/arflix/tv/data/model/Profile;", "currentProfile", "Lkotlin/Function8;", "Lcom/arflix/tv/data/model/EpisodeIdentity;", "", "", "Lx6/t0;", "onNavigateToPlayer", "Lkotlin/Function2;", "onNavigateToDetails", "Lkotlin/Function1;", "onNavigateToCollection", "Lkotlin/Function0;", "onNavigateToHome", "onNavigateToSearch", "onNavigateToWatchlist", "onNavigateToTv", "onNavigateToSettings", "onSwitchProfile", "onBack", "DetailsScreen", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;Lcom/arflix/tv/ui/screens/details/DetailsViewModel;Lcom/arflix/tv/data/model/Profile;Lr7/v;Lr7/p;Lr7/l;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;III)V", "Lcom/arflix/tv/ui/screens/details/FocusSection;", "section", "buttonIdx", "episodeIdx", "ratingsIdx", "seasonIdx", "castIdx", "reviewIdx", "similarIdx", "collectionIdx", "setButton", "setEpisode", "setRatings", "setSeason", "setCast", "setReview", "setSimilar", "setCollection", "", "handleLeft", "(Lcom/arflix/tv/ui/screens/details/FocusSection;IIIIIIIILr7/l;Lr7/l;Lr7/l;Lr7/l;Lr7/l;Lr7/l;Lr7/l;Lr7/l;)Z", "Lcom/arflix/tv/ui/screens/details/DetailsUiState;", "uiState", "handleRight", "(Lcom/arflix/tv/ui/screens/details/FocusSection;IIIIIIIILcom/arflix/tv/ui/screens/details/DetailsUiState;Lr7/l;Lr7/l;Lr7/l;Lr7/l;Lr7/l;Lr7/l;Lr7/l;Lr7/l;)Z", "Lcom/arflix/tv/data/model/MediaItem;", "item", "logoUrl", "", "Lcom/arflix/tv/data/model/Episode;", "episodes", "totalSeasons", "currentSeason", "Lcom/arflix/tv/data/model/CastMember;", "cast", "Lcom/arflix/tv/data/model/Review;", "reviews", "similar", "", "similarLogoUrls", "collectionItems", "collectionName", "hasCollectionAction", "collectionIndex", "focusedSection", "buttonIndex", "episodeIndex", "ratingsIndex", "seasonIndex", "castIndex", "reviewIndex", "similarIndex", "isInWatchlist", "genres", "budget", "Lx6/x;", "seasonProgress", "playLabel", "hasTrailer", "contentHasFocus", "usePosterCards", "showEpisodeRatings", "isMobile", "onButtonClick", "onSeasonClick", "onSeasonLongClick", "onEpisodeClick", "onCastClick", "spoilerBlurEnabled", "onSimilarClick", "onCollectionClick", "DetailsContent", "(Lcom/arflix/tv/data/model/MediaItem;Ljava/lang/String;Ljava/util/List;IILjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/Map;Ljava/util/List;Ljava/lang/String;ZILcom/arflix/tv/ui/screens/details/FocusSection;IIIIIIIZLjava/util/List;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;ZZZZZLr7/a;Lr7/l;Lr7/l;Lr7/l;Lr7/l;Lr7/l;ZLr7/l;Lr7/l;Landroidx/compose/runtime/Composer;IIIIII)V", "Landroidx/compose/ui/Modifier;", "modifier", "focusSectionForUi", "Landroidx/compose/ui/unit/Dp;", "contentRowHeight", "contentRowBottomPadding", "Landroid/content/res/Configuration;", "configuration", "DetailsTvRows-FSc51bE", "(Landroidx/compose/ui/Modifier;Lcom/arflix/tv/data/model/MediaItem;Ljava/util/List;IILjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/Map;Ljava/util/List;Ljava/lang/String;ILcom/arflix/tv/ui/screens/details/FocusSection;Lcom/arflix/tv/ui/screens/details/FocusSection;IIIIIILjava/util/Map;ZZZFFLandroid/content/res/Configuration;ZLr7/l;Lr7/l;Lr7/l;Lr7/l;Lr7/l;Landroidx/compose/runtime/Composer;IIIIII)V", "DetailsTvRows", "contentStartPadding", "contentOuterStartPadding", "DetailsSeasonRail-4EFweAY", "(IILjava/util/List;Ljava/util/Map;Lcom/arflix/tv/ui/screens/details/FocusSection;IFFLr7/l;Landroidx/compose/runtime/Composer;I)V", "DetailsSeasonRail", "", "rating", "Landroidx/compose/ui/graphics/Color;", "getEpisodeRatingColor", "(F)J", "getEpisodeRatingTextColor", "ratingStr", "formatEpisodeRating", "(Ljava/lang/String;)Ljava/lang/String;", "DetailsEpisodeRatingsRail-HXNGIdc", "(Ljava/util/List;IIIIZLcom/arflix/tv/ui/screens/details/FocusSection;FFLandroidx/compose/runtime/Composer;II)V", "DetailsEpisodeRatingsRail", "DetailsEpisodeRail-3GLzNTs", "(Ljava/util/List;ILcom/arflix/tv/ui/screens/details/FocusSection;Landroid/content/res/Configuration;FFZLr7/l;Landroidx/compose/runtime/Composer;I)V", "DetailsEpisodeRail", "DetailsCastRail-6PoWaU8", "(Ljava/util/List;ILcom/arflix/tv/ui/screens/details/FocusSection;FFLr7/l;Landroidx/compose/runtime/Composer;I)V", "DetailsCastRail", "DetailsReviewRail-BMayB_o", "(Ljava/util/List;ILcom/arflix/tv/ui/screens/details/FocusSection;FFLandroidx/compose/runtime/Composer;I)V", "DetailsReviewRail", "DetailsSimilarRail-nbWgWpA", "(Ljava/util/List;Ljava/util/Map;ILcom/arflix/tv/ui/screens/details/FocusSection;ZFFLr7/l;Landroidx/compose/runtime/Composer;I)V", "DetailsSimilarRail", "DetailsCollectionRail-nbWgWpA", "(Ljava/util/List;Ljava/lang/String;ILcom/arflix/tv/ui/screens/details/FocusSection;ZFFLr7/l;Landroidx/compose/runtime/Composer;I)V", "DetailsCollectionRail", "totalItems", "itemWidth", "itemSpacing", "detailsRailIsScrollable-73KfpEQ", "(IFFLandroidx/compose/runtime/Composer;I)Z", "detailsRailIsScrollable", "focusedItemIndex", "detailsRailUsesFixedFirstSlotFocus", "(II)Z", "startPadding", "outerStartPadding", "minimum", "lockedDetailsRailEndPadding--vR-za8", "(FFFFLandroidx/compose/runtime/Composer;I)F", "lockedDetailsRailEndPadding", "Landroidx/tv/foundation/lazy/list/m0;", "deltaPx", "durationMillis", "animateDetailsScrollDelta", "(Landroidx/tv/foundation/lazy/list/m0;FILd7/d;)Ljava/lang/Object;", "topPadding", "width", "aspectRatio", "FixedDetailsRailFocusOverlay-yajeYGU", "(FFFFLandroidx/compose/runtime/Composer;I)V", "FixedDetailsRailFocusOverlay", "rowState", "isCurrentRow", "HomeStyleRowAutoScroll-NuRrP5Q", "(Landroidx/tv/foundation/lazy/list/m0;ZIIFFLandroidx/compose/runtime/Composer;I)V", "HomeStyleRowAutoScroll", "imdbRatingFor", "(Lcom/arflix/tv/data/model/MediaItem;)Ljava/lang/String;", "Lcoil/ImageLoader;", "imageLoader", "ratingFontSize", "logoWidth", "logoHeight", "Landroidx/compose/ui/graphics/Shadow;", "textShadow", "DetailsImdbSvgRatingBadge-6PoWaU8", "(Ljava/lang/String;Lcoil/ImageLoader;IFFLandroidx/compose/ui/graphics/Shadow;Landroidx/compose/runtime/Composer;I)V", "DetailsImdbSvgRatingBadge", "label", "value", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "contentColor", "MobileScoreBadge-9z6LAg8", "(Ljava/lang/String;Ljava/lang/String;JJLandroidx/compose/runtime/Composer;I)V", "MobileScoreBadge", "MobileMetadataSeparator", "(Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/graphics/vector/ImageVector;", "icon", "text", "isPrimary", "isActive", "isOutlined", "onClick", "MobileActionButton", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;ZZZLandroidx/compose/ui/Modifier;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "contentDescription", "enabled", "MobileIconActionButton", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;ZZLandroidx/compose/ui/Modifier;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "isFocused", "isIconOnly", "PremiumActionButton", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;ZZZZLandroidx/compose/runtime/Composer;II)V", "episode", "cardWidth", "EpisodeCard-EUb7tLY", "(Lcom/arflix/tv/data/model/Episode;FZZLr7/a;Landroidx/compose/runtime/Composer;II)V", "EpisodeCard", "rawDate", "formatEpisodeAirDateLabel", "isFutureEpisodeAirDate", "(Ljava/lang/String;)Z", "season", "isSelected", "watchedCount", "totalCount", "onLongClick", "SeasonButton", "(IZZIILr7/a;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "member", "CastCard", "(Lcom/arflix/tv/data/model/CastMember;ZLandroidx/compose/runtime/Composer;I)V", "castMember", "CircularCastCard", "(Lcom/arflix/tv/data/model/CastMember;ZLr7/a;Landroidx/compose/runtime/Composer;I)V", "review", "ReviewCard", "(Lcom/arflix/tv/data/model/Review;ZLandroidx/compose/runtime/Composer;I)V", "MetaPill", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "ImdbBadge", "OngoingBadge", "genre", "GenreBadge", "language", "LanguageBadge", "BudgetBadge", "status", "StatusBadge", "logoImageUrl", "SimilarMediaCard", "(Lcom/arflix/tv/data/model/MediaItem;Ljava/lang/String;ZZLr7/a;Landroidx/compose/runtime/Composer;II)V", "suppressSelectUntilMs", "isSidebarFocused", "sidebarFocusIndex", "showStreamSelector", "showTrailerPlayer", "Lcom/arflix/tv/ui/screens/details/PendingAutoPlayRequest;", "pendingAutoPlayRequest", "autoPlayWaitTick", "showEpisodeContextMenu", "contextMenuEpisode", "showSeasonContextMenu", "contextMenuSeason", "seasonSelectDownAtMs", "ignoreFirstResumeRefresh", "stickyThreshold", "topBarAlpha", "seasonFocusIndex", "previousRatingsIndex", "leftChevronBump", "rightChevronBump", "leftOffset", "rightOffset", "currentFocusedSection", "currentEpisodeIndex", "scrollTargetIndex", "lastScrollIndex", "lastScrollOffset", "horizontalPadding", "animatedWidth", "labelAlpha", "scale", "borderAlpha", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DetailsScreenKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

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
            int[] iArr2 = new int[FocusSection.values().length];
            try {
                iArr2[FocusSection.BUTTONS.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[FocusSection.EPISODES.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[FocusSection.RATINGS.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[FocusSection.SEASONS.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[FocusSection.CAST.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[FocusSection.REVIEWS.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[FocusSection.SIMILAR.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[FocusSection.COLLECTION.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsScreenKt$animateDetailsScrollDelta$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/foundation/gestures/ScrollScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/gestures/ScrollScope;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsScreenKt$animateDetailsScrollDelta$2", f = "DetailsScreen.kt", l = {3249}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ScrollScope, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ float $deltaPx;
        final /* synthetic */ int $durationMillis;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(float f10, int i10, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$deltaPx = f10;
            this.$durationMillis = i10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final x6.t0 invokeSuspend$lambda$0(kotlin.jvm.internal.h0 h0Var, ScrollScope scrollScope, float f10, float f11) {
            float f12 = f10 - h0Var.f19742i;
            if (Math.abs(f12) > 0.01f) {
                scrollScope.scrollBy(f12);
            }
            h0Var.f19742i = f10;
            return x6.t0.f22605a;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$deltaPx, this.$durationMillis, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(ScrollScope scrollScope, d7.d<? super x6.t0> dVar) {
            return ((AnonymousClass2) create(scrollScope, dVar)).invokeSuspend(x6.t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ScrollScope scrollScope = (ScrollScope) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                kotlin.jvm.internal.h0 h0Var = new kotlin.jvm.internal.h0();
                float f10 = this.$deltaPx;
                TweenSpec tweenSpecTween$default = AnimationSpecKt.tween$default(this.$durationMillis, 0, EasingKt.getFastOutSlowInEasing(), 2, null);
                d1 d1Var = new d1(h0Var, scrollScope, 0);
                this.L$0 = null;
                this.L$1 = null;
                this.label = 1;
                Object objAnimate$default = SuspendAnimationKt.animate$default(0.0f, f10, 0.0f, tweenSpecTween$default, d1Var, this, 4, null);
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
            return x6.t0.f22605a;
        }
    }

    private static final void BudgetBadge(String str, Composer composer, int i10) {
        int i11;
        int i12;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1582874231);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
            i12 = 2;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1582874231, i11, -1, "com.arflix.tv.ui.screens.details.BudgetBadge (DetailsScreen.kt:4513)");
            }
            long jColor = ColorKt.Color(4279286145L);
            float f10 = 6;
            Modifier modifierH = a2.h(5, com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.j(f10, Modifier.INSTANCE, Color.m3471copywmQWz5c$default(jColor, 0.15f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(jColor, 0.3f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(10), composerStartRestartGroup, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
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
            i12 = 2;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(androidx.compose.foundation.c.t(StringResources_androidKt.stringResource(R.string.budget, composerStartRestartGroup, 0).toUpperCase(Locale.ROOT), ": ", str), null, jColor, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getLabel(), composer2, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new f(str, i10, i12));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 BudgetBadge$lambda$1(String str, int i10, Composer composer, int i11) {
        BudgetBadge(str, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void CastCard(final CastMember castMember, boolean z, Composer composer, int i10) {
        int i11;
        Modifier modifierGraphicsLayer;
        long jM6496getTextMuted0d7_KjU;
        boolean z5 = z;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1779756864);
        int i12 = 4;
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changed(castMember) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z5) ? 32 : 16;
        }
        int i13 = i11;
        if ((i13 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1779756864, i13, -1, "com.arflix.tv.ui.screens.details.CastCard (DetailsScreen.kt:4078)");
            }
            RoundedCornerShape circleShape = RoundedCornerShapeKt.getCircleShape();
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(z5 ? 1.08f : 1.0f, AnimationSpecKt.spring$default(0.5f, 1500.0f, null, 4, null), 0.0f, "cast_scale", null, composerStartRestartGroup, 3120, 20);
            float fM5678constructorimpl = Dp.m5678constructorimpl((z5 || CastCard$lambda$0(stateAnimateFloatAsState) != 1.0f) ? 3 : 0);
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierM583width3ABfNKs = SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(MenuKt.InTransitionDuration));
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(Arrangement.INSTANCE, centerHorizontally, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM583width3ABfNKs);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyF, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            if (CastCard$lambda$0(stateAnimateFloatAsState) == 1.0f) {
                composerStartRestartGroup.startReplaceGroup(1089184844);
                composerStartRestartGroup.endReplaceGroup();
                modifierGraphicsLayer = companion;
            } else {
                composerStartRestartGroup.startReplaceGroup(1089058736);
                boolean zChanged = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new s(stateAnimateFloatAsState, i12);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(companion, (r7.l) objRememberedValue);
                composerStartRestartGroup.endReplaceGroup();
            }
            Modifier modifierThen = SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(100)).then(modifierGraphicsLayer);
            ArvioSkin arvioSkin = ArvioSkin.INSTANCE;
            float f10 = 8;
            ArvioFocusKt.m6499ArvioFocusableSurfaceaewEPWQ(modifierThen, circleShape, Color.m3471copywmQWz5c$default(arvioSkin.getColors(composerStartRestartGroup, 6).m6494getSurfaceRaised0d7_KjU(), 0.65f, 0.0f, 0.0f, 0.0f, 14, null), 1.0f, 1.0f, fM5678constructorimpl, Dp.m5678constructorimpl(f10), 0.18f, 0L, 0.0f, false, 0L, 0L, false, false, false, false, false, z, null, null, null, ComposableLambdaKt.rememberComposableLambda(-927618512, true, new r7.r() { // from class: com.arflix.tv.ui.screens.details.u0
                @Override // r7.r
                public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                    int iIntValue = ((Integer) obj4).intValue();
                    return DetailsScreenKt.CastCard$lambda$1$1(castMember, (BoxScope) obj, ((Boolean) obj2).booleanValue(), (Composer) obj3, iIntValue);
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, 14183424, ((i13 << 21) & 234881024) | 806879232, RendererCapabilities.DECODER_SUPPORT_MASK, 3342080);
            z5 = z;
            SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion, Dp.m5678constructorimpl(f10)), composerStartRestartGroup, 6);
            String name = castMember.getName();
            TextStyle cardTitle = arvioSkin.getTypography(composerStartRestartGroup, 6).getCardTitle();
            if (z5) {
                composerStartRestartGroup.startReplaceGroup(-934647231);
                jM6496getTextMuted0d7_KjU = arvioSkin.getColors(composerStartRestartGroup, 6).m6497getTextPrimary0d7_KjU();
            } else {
                composerStartRestartGroup.startReplaceGroup(-934646145);
                jM6496getTextMuted0d7_KjU = arvioSkin.getColors(composerStartRestartGroup, 6).m6496getTextMuted0d7_KjU();
            }
            composerStartRestartGroup.endReplaceGroup();
            long j10 = jM6496getTextMuted0d7_KjU;
            TextOverflow.Companion companion3 = TextOverflow.INSTANCE;
            TextKt.m6020Text4IGK_g(name, null, j10, 0L, null, null, null, 0L, null, null, 0L, companion3.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, cardTitle, composerStartRestartGroup, 0, 3120, 55290);
            composerStartRestartGroup = composerStartRestartGroup;
            if (castMember.getCharacter().length() > 0) {
                composerStartRestartGroup.startReplaceGroup(1090897315);
                TextKt.m6020Text4IGK_g(castMember.getCharacter(), null, Color.m3471copywmQWz5c$default(arvioSkin.getColors(composerStartRestartGroup, 6).m6496getTextMuted0d7_KjU(), 0.85f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, companion3.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, arvioSkin.getTypography(composerStartRestartGroup, 6).getCaption(), composerStartRestartGroup, 0, 3120, 55290);
                composerStartRestartGroup = composerStartRestartGroup;
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1091161900);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new p0(castMember, z5, i10, 1));
        }
    }

    private static final float CastCard$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CastCard$lambda$1$0$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(CastCard$lambda$0(state));
        graphicsLayerScope.setScaleY(CastCard$lambda$0(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CastCard$lambda$1$1(CastMember castMember, BoxScope boxScope, boolean z, Composer composer, int i10) {
        if ((i10 & TsExtractor.TS_STREAM_TYPE_AC3) == 128 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-927618512, i10, -1, "com.arflix.tv.ui.screens.details.CastCard.<anonymous>.<anonymous> (DetailsScreen.kt:4117)");
            }
            if (castMember.getProfilePath() != null) {
                composer.startReplaceGroup(-863432669);
                SingletonAsyncImageKt.m6053AsyncImage3HmZ8SU(castMember.getProfilePath(), castMember.getName(), SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, composer, 1573248, 952);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(-863169541);
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                Alignment center = Alignment.INSTANCE.getCenter();
                composer.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
                composer.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                r7.a<ComposeUiNode> constructor = companion.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxSize$default);
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
                r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
                if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
                }
                a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                Character chA0 = kotlin.text.o.a0(castMember.getName());
                String strValueOf = chA0 != null ? String.valueOf(chA0.charValue()) : null;
                if (strValueOf == null) {
                    strValueOf = "";
                }
                ArvioSkin arvioSkin = ArvioSkin.INSTANCE;
                TextKt.m6020Text4IGK_g(strValueOf, null, arvioSkin.getColors(composer, 6).m6496getTextMuted0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arvioSkin.getTypography(composer, 6).getSectionTitle(), composer, 0, 0, 65530);
                com.arflix.tv.data.repository.g.q(composer);
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CastCard$lambda$2(CastMember castMember, boolean z, int i10, Composer composer, int i11) {
        CastCard(castMember, z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x03bc  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x04b4  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x04c3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0371  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CircularCastCard(com.arflix.tv.data.model.CastMember r98, boolean r99, r7.a<x6.t0> r100, androidx.compose.runtime.Composer r101, int r102) {
        /*
            Method dump skipped, instruction units count: 1237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.CircularCastCard(com.arflix.tv.data.model.CastMember, boolean, r7.a, androidx.compose.runtime.Composer, int):void");
    }

    private static final float CircularCastCard$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CircularCastCard$lambda$1$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CircularCastCard$lambda$2$0$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(CircularCastCard$lambda$0(state));
        graphicsLayerScope.setScaleY(CircularCastCard$lambda$0(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CircularCastCard$lambda$3(CastMember castMember, boolean z, r7.a aVar, int i10, Composer composer, int i11) {
        CircularCastCard(castMember, z, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: renamed from: DetailsCastRail-6PoWaU8, reason: not valid java name */
    private static final void m6210DetailsCastRail6PoWaU8(List<CastMember> list, int i10, FocusSection focusSection, float f10, float f11, r7.l<? super Integer, x6.t0> lVar, Composer composer, int i11) {
        List<CastMember> list2;
        int i12;
        float f12;
        Composer composerStartRestartGroup = composer.startRestartGroup(1936672802);
        if ((i11 & 6) == 0) {
            list2 = list;
            i12 = (composerStartRestartGroup.changed(list2) ? 4 : 2) | i11;
        } else {
            list2 = list;
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(focusSection == null ? -1 : focusSection.ordinal()) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changed(f10) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i12 |= composerStartRestartGroup.changed(f11) ? 16384 : 8192;
        }
        if ((196608 & i11) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(lVar) ? 131072 : 65536;
        }
        if ((74899 & i12) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            f12 = f10;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1936672802, i12, -1, "com.arflix.tv.ui.screens.details.DetailsCastRail (DetailsScreen.kt:2915)");
            }
            androidx.tv.foundation.lazy.list.m0 m0VarRememberTvLazyListState = LazyListStateKt.rememberTvLazyListState(0, 0, composerStartRestartGroup, 0, 3);
            boolean z = true;
            if (focusSection != FocusSection.CAST) {
                z = false;
            }
            float f13 = 90;
            float f14 = 16;
            m6221HomeStyleRowAutoScrollNuRrP5Q(m0VarRememberTvLazyListState, z, i10, list2.size(), Dp.m5678constructorimpl(f13), Dp.m5678constructorimpl(f14), composerStartRestartGroup, ((i12 << 3) & 896) | 221184);
            State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(lVar, composerStartRestartGroup, (i12 >> 15) & 14);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            int i13 = i12;
            Modifier.Companion companion = Modifier.INSTANCE;
            Arrangement arrangement = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(Alignment.INSTANCE, arrangement.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            float f15 = 10;
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.cast, composerStartRestartGroup, 0), PaddingKt.m533paddingqDBjuR0$default(companion, f10, 0.0f, 0.0f, Dp.m5678constructorimpl(f15), 6, null), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.9f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArvioSkin.INSTANCE.getTypography(composerStartRestartGroup, 6).getSectionTitle(), 0L, TextUnitKt.getSp(14), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65528);
            composerStartRestartGroup = composerStartRestartGroup;
            Modifier modifierArvioDpadFocusGroup$default = ArvioDpadFocusKt.arvioDpadFocusGroup$default(companion, null, false, 1, null);
            int i14 = i13 >> 6;
            f12 = f10;
            PaddingValues paddingValuesM525PaddingValuesa9UjIt4 = PaddingKt.m525PaddingValuesa9UjIt4(f12, Dp.m5678constructorimpl(f15), m6225lockedDetailsRailEndPaddingvRza8(Dp.m5678constructorimpl(f13), f10, f11, Dp.m5678constructorimpl(MenuKt.InTransitionDuration), composerStartRestartGroup, (i14 & 896) | (i14 & 112) | 3078), Dp.m5678constructorimpl(f15));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(f14));
            boolean zChanged = ((i13 & 112) == 32) | ((i13 & 14) == 4) | composerStartRestartGroup.changed(stateRememberUpdatedState) | ((i13 & 896) == 256);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                n0 n0Var = new n0(i10, 0, list, focusSection, stateRememberUpdatedState);
                composerStartRestartGroup.updateRememberedValue(n0Var);
                objRememberedValue = n0Var;
            }
            LazyDslKt.TvLazyRow(modifierArvioDpadFocusGroup$default, m0VarRememberTvLazyListState, paddingValuesM525PaddingValuesa9UjIt4, false, horizontalOrVerticalM441spacedBy0680j_4, null, false, null, (r7.l) objRememberedValue, composerStartRestartGroup, 24576, 232);
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new o0(list, i10, focusSection, f12, f11, lVar, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsCastRail_6PoWaU8$lambda$0$0$0(List list, FocusSection focusSection, int i10, State state, androidx.tv.foundation.lazy.list.g0 g0Var) {
        g0Var.items(list.size(), new DetailsScreenKt$DetailsCastRail_6PoWaU8$lambda$0$0$0$$inlined$itemsIndexed$default$1(new g(7), list), new DetailsScreenKt$DetailsCastRail_6PoWaU8$lambda$0$0$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-906771355, true, new DetailsScreenKt$DetailsCastRail_6PoWaU8$lambda$0$0$0$$inlined$itemsIndexed$default$3(list, focusSection, i10, state)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object DetailsCastRail_6PoWaU8$lambda$0$0$0$0(int i10, CastMember castMember) {
        return castMember.getId() + "_" + castMember.getCharacter() + "_" + i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsCastRail_6PoWaU8$lambda$1(List list, int i10, FocusSection focusSection, float f10, float f11, r7.l lVar, int i11, Composer composer, int i12) {
        m6210DetailsCastRail6PoWaU8(list, i10, focusSection, f10, f11, lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0382  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x03b1  */
    /* JADX INFO: renamed from: DetailsCollectionRail-nbWgWpA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6211DetailsCollectionRailnbWgWpA(java.util.List<com.arflix.tv.data.model.MediaItem> r76, java.lang.String r77, int r78, com.arflix.tv.ui.screens.details.FocusSection r79, boolean r80, float r81, float r82, r7.l<? super java.lang.Integer, x6.t0> r83, androidx.compose.runtime.Composer r84, int r85) {
        /*
            Method dump skipped, instruction units count: 982
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.m6211DetailsCollectionRailnbWgWpA(java.util.List, java.lang.String, int, com.arflix.tv.ui.screens.details.FocusSection, boolean, float, float, r7.l, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsCollectionRail_nbWgWpA$lambda$0$0$0$0(List list, boolean z, FocusSection focusSection, int i10, boolean z5, State state, androidx.tv.foundation.lazy.list.g0 g0Var) {
        g0Var.items(list.size(), new DetailsScreenKt$DetailsCollectionRail_nbWgWpA$lambda$0$0$0$0$$inlined$itemsIndexed$default$1(new g(4), list), new DetailsScreenKt$DetailsCollectionRail_nbWgWpA$lambda$0$0$0$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-906771355, true, new DetailsScreenKt$DetailsCollectionRail_nbWgWpA$lambda$0$0$0$0$$inlined$itemsIndexed$default$3(list, z, focusSection, i10, z5, state)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object DetailsCollectionRail_nbWgWpA$lambda$0$0$0$0$0(int i10, MediaItem mediaItem) {
        StringBuilder sbQ = a2.q("col_", mediaItem.getMediaType().name(), "_", mediaItem.getId(), "_");
        sbQ.append(i10);
        return sbQ.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsCollectionRail_nbWgWpA$lambda$1(List list, String str, int i10, FocusSection focusSection, boolean z, float f10, float f11, r7.l lVar, int i11, Composer composer, int i12) {
        m6211DetailsCollectionRailnbWgWpA(list, str, i10, focusSection, z, f10, f11, lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:1011:0x1e7e  */
    /* JADX WARN: Removed duplicated region for block: B:1014:0x1e99  */
    /* JADX WARN: Removed duplicated region for block: B:1052:0x208b  */
    /* JADX WARN: Removed duplicated region for block: B:1055:0x20a2  */
    /* JADX WARN: Removed duplicated region for block: B:1081:0x2262  */
    /* JADX WARN: Removed duplicated region for block: B:1084:0x22c1  */
    /* JADX WARN: Removed duplicated region for block: B:1087:0x22ca  */
    /* JADX WARN: Removed duplicated region for block: B:1090:0x2334  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:1145:0x2841  */
    /* JADX WARN: Removed duplicated region for block: B:1148:0x284d  */
    /* JADX WARN: Removed duplicated region for block: B:1149:0x2851  */
    /* JADX WARN: Removed duplicated region for block: B:1152:0x2862  */
    /* JADX WARN: Removed duplicated region for block: B:1156:0x2877  */
    /* JADX WARN: Removed duplicated region for block: B:1160:0x289d  */
    /* JADX WARN: Removed duplicated region for block: B:1162:0x28a5  */
    /* JADX WARN: Removed duplicated region for block: B:1165:0x28f6  */
    /* JADX WARN: Removed duplicated region for block: B:1168:0x2902  */
    /* JADX WARN: Removed duplicated region for block: B:1169:0x2906  */
    /* JADX WARN: Removed duplicated region for block: B:1172:0x2917  */
    /* JADX WARN: Removed duplicated region for block: B:1176:0x292c  */
    /* JADX WARN: Removed duplicated region for block: B:1180:0x2972  */
    /* JADX WARN: Removed duplicated region for block: B:1183:0x297e  */
    /* JADX WARN: Removed duplicated region for block: B:1184:0x2982  */
    /* JADX WARN: Removed duplicated region for block: B:1187:0x2993  */
    /* JADX WARN: Removed duplicated region for block: B:1191:0x29a8  */
    /* JADX WARN: Removed duplicated region for block: B:1195:0x29b3  */
    /* JADX WARN: Removed duplicated region for block: B:1196:0x2a0f  */
    /* JADX WARN: Removed duplicated region for block: B:1199:0x2a1d  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:1215:0x2b15  */
    /* JADX WARN: Removed duplicated region for block: B:1219:0x2b49 A[LOOP:4: B:1217:0x2b43->B:1219:0x2b49, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:1222:0x2b74  */
    /* JADX WARN: Removed duplicated region for block: B:1227:0x2b81  */
    /* JADX WARN: Removed duplicated region for block: B:1230:0x2b89  */
    /* JADX WARN: Removed duplicated region for block: B:1231:0x2b8b  */
    /* JADX WARN: Removed duplicated region for block: B:1234:0x2b92  */
    /* JADX WARN: Removed duplicated region for block: B:1239:0x2b9c  */
    /* JADX WARN: Removed duplicated region for block: B:1245:0x2bb8  */
    /* JADX WARN: Removed duplicated region for block: B:1248:0x2bc7  */
    /* JADX WARN: Removed duplicated region for block: B:1249:0x2bc9  */
    /* JADX WARN: Removed duplicated region for block: B:1255:0x2bd7  */
    /* JADX WARN: Removed duplicated region for block: B:1264:0x2bf3  */
    /* JADX WARN: Removed duplicated region for block: B:1270:0x2bff  */
    /* JADX WARN: Removed duplicated region for block: B:1276:0x2c09  */
    /* JADX WARN: Removed duplicated region for block: B:1278:0x2c0c  */
    /* JADX WARN: Removed duplicated region for block: B:1279:0x2c14  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:1282:0x2cac  */
    /* JADX WARN: Removed duplicated region for block: B:1285:0x2cb8  */
    /* JADX WARN: Removed duplicated region for block: B:1286:0x2cbc  */
    /* JADX WARN: Removed duplicated region for block: B:1289:0x2ccd  */
    /* JADX WARN: Removed duplicated region for block: B:1293:0x2ce2  */
    /* JADX WARN: Removed duplicated region for block: B:1297:0x2d2d  */
    /* JADX WARN: Removed duplicated region for block: B:1300:0x2d39  */
    /* JADX WARN: Removed duplicated region for block: B:1301:0x2d3d  */
    /* JADX WARN: Removed duplicated region for block: B:1304:0x2d4e  */
    /* JADX WARN: Removed duplicated region for block: B:1308:0x2d61  */
    /* JADX WARN: Removed duplicated region for block: B:1311:0x2de8  */
    /* JADX WARN: Removed duplicated region for block: B:1312:0x2e95  */
    /* JADX WARN: Removed duplicated region for block: B:1314:0x2ea2  */
    /* JADX WARN: Removed duplicated region for block: B:1315:0x2f4d  */
    /* JADX WARN: Removed duplicated region for block: B:1318:0x2f5b  */
    /* JADX WARN: Removed duplicated region for block: B:1335:0x2fea  */
    /* JADX WARN: Removed duplicated region for block: B:1349:0x30e7  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:1351:0x30f3  */
    /* JADX WARN: Removed duplicated region for block: B:1356:0x3183  */
    /* JADX WARN: Removed duplicated region for block: B:1358:0x318e  */
    /* JADX WARN: Removed duplicated region for block: B:1359:0x3234  */
    /* JADX WARN: Removed duplicated region for block: B:1361:0x3241  */
    /* JADX WARN: Removed duplicated region for block: B:1364:0x3299  */
    /* JADX WARN: Removed duplicated region for block: B:1367:0x32a5  */
    /* JADX WARN: Removed duplicated region for block: B:1368:0x32a9  */
    /* JADX WARN: Removed duplicated region for block: B:1375:0x32cd  */
    /* JADX WARN: Removed duplicated region for block: B:1378:0x33d1  */
    /* JADX WARN: Removed duplicated region for block: B:1381:0x33dd  */
    /* JADX WARN: Removed duplicated region for block: B:1382:0x33e1  */
    /* JADX WARN: Removed duplicated region for block: B:1389:0x3405  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:1400:0x346f  */
    /* JADX WARN: Removed duplicated region for block: B:1403:0x347b  */
    /* JADX WARN: Removed duplicated region for block: B:1404:0x347f  */
    /* JADX WARN: Removed duplicated region for block: B:1411:0x34a3  */
    /* JADX WARN: Removed duplicated region for block: B:1418:0x34bf  */
    /* JADX WARN: Removed duplicated region for block: B:1421:0x34d7  */
    /* JADX WARN: Removed duplicated region for block: B:1422:0x34d9  */
    /* JADX WARN: Removed duplicated region for block: B:1429:0x34ec  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:1432:0x353f  */
    /* JADX WARN: Removed duplicated region for block: B:1435:0x354b  */
    /* JADX WARN: Removed duplicated region for block: B:1436:0x354f  */
    /* JADX WARN: Removed duplicated region for block: B:1439:0x3560  */
    /* JADX WARN: Removed duplicated region for block: B:1443:0x3575  */
    /* JADX WARN: Removed duplicated region for block: B:1447:0x358e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1449:0x3592  */
    /* JADX WARN: Removed duplicated region for block: B:1452:0x35b7  */
    /* JADX WARN: Removed duplicated region for block: B:1453:0x35b9  */
    /* JADX WARN: Removed duplicated region for block: B:1458:0x35c8  */
    /* JADX WARN: Removed duplicated region for block: B:1461:0x361b  */
    /* JADX WARN: Removed duplicated region for block: B:1464:0x3627  */
    /* JADX WARN: Removed duplicated region for block: B:1465:0x362b  */
    /* JADX WARN: Removed duplicated region for block: B:1468:0x363c  */
    /* JADX WARN: Removed duplicated region for block: B:1472:0x3651  */
    /* JADX WARN: Removed duplicated region for block: B:1476:0x366d  */
    /* JADX WARN: Removed duplicated region for block: B:1479:0x3672  */
    /* JADX WARN: Removed duplicated region for block: B:1482:0x3699  */
    /* JADX WARN: Removed duplicated region for block: B:1483:0x369b  */
    /* JADX WARN: Removed duplicated region for block: B:1488:0x36aa  */
    /* JADX WARN: Removed duplicated region for block: B:1491:0x36d6  */
    /* JADX WARN: Removed duplicated region for block: B:1492:0x36d8  */
    /* JADX WARN: Removed duplicated region for block: B:1499:0x36ea  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:1502:0x3727  */
    /* JADX WARN: Removed duplicated region for block: B:1505:0x3733  */
    /* JADX WARN: Removed duplicated region for block: B:1506:0x3737  */
    /* JADX WARN: Removed duplicated region for block: B:1513:0x375b  */
    /* JADX WARN: Removed duplicated region for block: B:1519:0x3778  */
    /* JADX WARN: Removed duplicated region for block: B:1522:0x379e  */
    /* JADX WARN: Removed duplicated region for block: B:1523:0x37a0  */
    /* JADX WARN: Removed duplicated region for block: B:1528:0x37af  */
    /* JADX WARN: Removed duplicated region for block: B:1531:0x37fc  */
    /* JADX WARN: Removed duplicated region for block: B:1534:0x3808  */
    /* JADX WARN: Removed duplicated region for block: B:1535:0x380c  */
    /* JADX WARN: Removed duplicated region for block: B:1538:0x381d  */
    /* JADX WARN: Removed duplicated region for block: B:1542:0x3832  */
    /* JADX WARN: Removed duplicated region for block: B:1546:0x3841  */
    /* JADX WARN: Removed duplicated region for block: B:1547:0x3846  */
    /* JADX WARN: Removed duplicated region for block: B:1549:0x384c  */
    /* JADX WARN: Removed duplicated region for block: B:1551:0x385d  */
    /* JADX WARN: Removed duplicated region for block: B:1553:0x3869  */
    /* JADX WARN: Removed duplicated region for block: B:1556:0x386e  */
    /* JADX WARN: Removed duplicated region for block: B:1559:0x3892  */
    /* JADX WARN: Removed duplicated region for block: B:1560:0x3894  */
    /* JADX WARN: Removed duplicated region for block: B:1565:0x38a3  */
    /* JADX WARN: Removed duplicated region for block: B:1568:0x38f0  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:1571:0x38fc  */
    /* JADX WARN: Removed duplicated region for block: B:1572:0x3900  */
    /* JADX WARN: Removed duplicated region for block: B:1575:0x3911  */
    /* JADX WARN: Removed duplicated region for block: B:1579:0x3926  */
    /* JADX WARN: Removed duplicated region for block: B:1583:0x3935  */
    /* JADX WARN: Removed duplicated region for block: B:1584:0x393a  */
    /* JADX WARN: Removed duplicated region for block: B:1587:0x3947  */
    /* JADX WARN: Removed duplicated region for block: B:1590:0x394c  */
    /* JADX WARN: Removed duplicated region for block: B:1593:0x3972  */
    /* JADX WARN: Removed duplicated region for block: B:1625:0x3a4f  */
    /* JADX WARN: Removed duplicated region for block: B:1628:0x3b4d  */
    /* JADX WARN: Removed duplicated region for block: B:1633:0x3bbb A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x033c  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x03fa  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x042c  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x046b  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x04af  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x04f8  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x04fb  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x04ff  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x0501  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x0509  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x0512  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x0516  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0519  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x051d  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0520  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x0524  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x0527  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x052b  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x052e  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0532  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x0535  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x0539  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x053c  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x0540  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x0543  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x0547  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x054a  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x0551  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x0555  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0558  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x055e  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x0579  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x057f  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x059b  */
    /* JADX WARN: Removed duplicated region for block: B:436:0x05a1  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x05bd  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x05c3  */
    /* JADX WARN: Removed duplicated region for block: B:443:0x05c5  */
    /* JADX WARN: Removed duplicated region for block: B:446:0x05cb  */
    /* JADX WARN: Removed duplicated region for block: B:450:0x05e5  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x05eb  */
    /* JADX WARN: Removed duplicated region for block: B:456:0x0606  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x060c  */
    /* JADX WARN: Removed duplicated region for block: B:459:0x060f  */
    /* JADX WARN: Removed duplicated region for block: B:462:0x0615  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x062f  */
    /* JADX WARN: Removed duplicated region for block: B:468:0x0635  */
    /* JADX WARN: Removed duplicated region for block: B:473:0x067c  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x06b2  */
    /* JADX WARN: Removed duplicated region for block: B:477:0x06bf  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x06d5  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x06d8  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x072c  */
    /* JADX WARN: Removed duplicated region for block: B:520:0x085a  */
    /* JADX WARN: Removed duplicated region for block: B:522:0x085d  */
    /* JADX WARN: Removed duplicated region for block: B:554:0x099a  */
    /* JADX WARN: Removed duplicated region for block: B:557:0x09a6  */
    /* JADX WARN: Removed duplicated region for block: B:558:0x09aa  */
    /* JADX WARN: Removed duplicated region for block: B:561:0x09bb  */
    /* JADX WARN: Removed duplicated region for block: B:565:0x09d0  */
    /* JADX WARN: Removed duplicated region for block: B:569:0x0a14  */
    /* JADX WARN: Removed duplicated region for block: B:572:0x0a20  */
    /* JADX WARN: Removed duplicated region for block: B:573:0x0a24  */
    /* JADX WARN: Removed duplicated region for block: B:576:0x0a35  */
    /* JADX WARN: Removed duplicated region for block: B:580:0x0a48  */
    /* JADX WARN: Removed duplicated region for block: B:583:0x0a55  */
    /* JADX WARN: Removed duplicated region for block: B:586:0x0b49  */
    /* JADX WARN: Removed duplicated region for block: B:606:0x0c35  */
    /* JADX WARN: Removed duplicated region for block: B:609:0x0ca4  */
    /* JADX WARN: Removed duplicated region for block: B:612:0x0cb0  */
    /* JADX WARN: Removed duplicated region for block: B:613:0x0cb4  */
    /* JADX WARN: Removed duplicated region for block: B:616:0x0cc5  */
    /* JADX WARN: Removed duplicated region for block: B:620:0x0cda  */
    /* JADX WARN: Removed duplicated region for block: B:624:0x0d15  */
    /* JADX WARN: Removed duplicated region for block: B:628:0x0d21  */
    /* JADX WARN: Removed duplicated region for block: B:633:0x0d43  */
    /* JADX WARN: Removed duplicated region for block: B:636:0x0d7a  */
    /* JADX WARN: Removed duplicated region for block: B:639:0x0d86  */
    /* JADX WARN: Removed duplicated region for block: B:640:0x0d8a  */
    /* JADX WARN: Removed duplicated region for block: B:647:0x0dae  */
    /* JADX WARN: Removed duplicated region for block: B:650:0x0db7  */
    /* JADX WARN: Removed duplicated region for block: B:651:0x0e0c  */
    /* JADX WARN: Removed duplicated region for block: B:654:0x0e86  */
    /* JADX WARN: Removed duplicated region for block: B:657:0x0e92  */
    /* JADX WARN: Removed duplicated region for block: B:658:0x0e96  */
    /* JADX WARN: Removed duplicated region for block: B:661:0x0ea7  */
    /* JADX WARN: Removed duplicated region for block: B:665:0x0ebc  */
    /* JADX WARN: Removed duplicated region for block: B:669:0x0ecd  */
    /* JADX WARN: Removed duplicated region for block: B:670:0x0efd  */
    /* JADX WARN: Removed duplicated region for block: B:673:0x0f0c  */
    /* JADX WARN: Removed duplicated region for block: B:674:0x0faa  */
    /* JADX WARN: Removed duplicated region for block: B:676:0x0fb5  */
    /* JADX WARN: Removed duplicated region for block: B:677:0x1057  */
    /* JADX WARN: Removed duplicated region for block: B:680:0x1072  */
    /* JADX WARN: Removed duplicated region for block: B:681:0x1120  */
    /* JADX WARN: Removed duplicated region for block: B:684:0x1199  */
    /* JADX WARN: Removed duplicated region for block: B:687:0x11a5  */
    /* JADX WARN: Removed duplicated region for block: B:688:0x11a9  */
    /* JADX WARN: Removed duplicated region for block: B:691:0x11ba  */
    /* JADX WARN: Removed duplicated region for block: B:695:0x11cf  */
    /* JADX WARN: Removed duplicated region for block: B:699:0x11de  */
    /* JADX WARN: Removed duplicated region for block: B:703:0x11f1  */
    /* JADX WARN: Removed duplicated region for block: B:706:0x1227  */
    /* JADX WARN: Removed duplicated region for block: B:708:0x122b  */
    /* JADX WARN: Removed duplicated region for block: B:715:0x1242  */
    /* JADX WARN: Removed duplicated region for block: B:718:0x12b6  */
    /* JADX WARN: Removed duplicated region for block: B:721:0x12c2  */
    /* JADX WARN: Removed duplicated region for block: B:722:0x12c6  */
    /* JADX WARN: Removed duplicated region for block: B:725:0x12d7  */
    /* JADX WARN: Removed duplicated region for block: B:729:0x12ec  */
    /* JADX WARN: Removed duplicated region for block: B:733:0x132c  */
    /* JADX WARN: Removed duplicated region for block: B:735:0x1330  */
    /* JADX WARN: Removed duplicated region for block: B:738:0x1338  */
    /* JADX WARN: Removed duplicated region for block: B:740:0x1340  */
    /* JADX WARN: Removed duplicated region for block: B:743:0x1395  */
    /* JADX WARN: Removed duplicated region for block: B:745:0x1399  */
    /* JADX WARN: Removed duplicated region for block: B:750:0x13a9  */
    /* JADX WARN: Removed duplicated region for block: B:753:0x13d5  */
    /* JADX WARN: Removed duplicated region for block: B:754:0x13da  */
    /* JADX WARN: Removed duplicated region for block: B:756:0x13e0  */
    /* JADX WARN: Removed duplicated region for block: B:758:0x13f3  */
    /* JADX WARN: Removed duplicated region for block: B:761:0x1424  */
    /* JADX WARN: Removed duplicated region for block: B:763:0x1428  */
    /* JADX WARN: Removed duplicated region for block: B:768:0x1438  */
    /* JADX WARN: Removed duplicated region for block: B:771:0x145f  */
    /* JADX WARN: Removed duplicated region for block: B:772:0x1464  */
    /* JADX WARN: Removed duplicated region for block: B:774:0x146a  */
    /* JADX WARN: Removed duplicated region for block: B:776:0x147b  */
    /* JADX WARN: Removed duplicated region for block: B:779:0x14a8  */
    /* JADX WARN: Removed duplicated region for block: B:780:0x14aa  */
    /* JADX WARN: Removed duplicated region for block: B:785:0x14b9  */
    /* JADX WARN: Removed duplicated region for block: B:868:0x17db  */
    /* JADX WARN: Removed duplicated region for block: B:870:0x17e6  */
    /* JADX WARN: Removed duplicated region for block: B:879:0x18f9  */
    /* JADX WARN: Removed duplicated region for block: B:904:0x19d5  */
    /* JADX WARN: Removed duplicated region for block: B:907:0x19ee  */
    /* JADX WARN: Removed duplicated region for block: B:908:0x19f0  */
    /* JADX WARN: Removed duplicated region for block: B:913:0x19ff  */
    /* JADX WARN: Removed duplicated region for block: B:918:0x1a0d  */
    /* JADX WARN: Removed duplicated region for block: B:921:0x1a17  */
    /* JADX WARN: Removed duplicated region for block: B:937:0x1a98  */
    /* JADX WARN: Removed duplicated region for block: B:940:0x1aa7  */
    /* JADX WARN: Removed duplicated region for block: B:970:0x1c7e  */
    /* JADX WARN: Removed duplicated region for block: B:973:0x1c97  */
    /* JADX WARN: Type inference failed for: r9v66 */
    /* JADX WARN: Type inference failed for: r9v67, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r9v74 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void DetailsContent(final com.arflix.tv.data.model.MediaItem r158, final java.lang.String r159, final java.util.List<com.arflix.tv.data.model.Episode> r160, final int r161, final int r162, final java.util.List<com.arflix.tv.data.model.CastMember> r163, final java.util.List<com.arflix.tv.data.model.Review> r164, final java.util.List<com.arflix.tv.data.model.MediaItem> r165, final java.util.Map<java.lang.String, java.lang.String> r166, java.util.List<com.arflix.tv.data.model.MediaItem> r167, java.lang.String r168, boolean r169, int r170, final com.arflix.tv.ui.screens.details.FocusSection r171, final int r172, final int r173, final int r174, final int r175, final int r176, final int r177, final int r178, final boolean r179, java.util.List<java.lang.String> r180, java.lang.String r181, java.util.Map<java.lang.Integer, x6.x> r182, java.lang.String r183, boolean r184, boolean r185, boolean r186, boolean r187, boolean r188, r7.a<x6.t0> r189, r7.l<? super java.lang.Integer, x6.t0> r190, r7.l<? super java.lang.Integer, x6.t0> r191, r7.l<? super java.lang.Integer, x6.t0> r192, r7.l<? super java.lang.Integer, x6.t0> r193, r7.l<? super java.lang.Integer, x6.t0> r194, boolean r195, r7.l<? super java.lang.Integer, x6.t0> r196, r7.l<? super java.lang.Integer, x6.t0> r197, androidx.compose.runtime.Composer r198, final int r199, final int r200, final int r201, final int r202, final int r203, final int r204) {
        /*
            Method dump skipped, instruction units count: 15292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.DetailsContent(com.arflix.tv.data.model.MediaItem, java.lang.String, java.util.List, int, int, java.util.List, java.util.List, java.util.List, java.util.Map, java.util.List, java.lang.String, boolean, int, com.arflix.tv.ui.screens.details.FocusSection, int, int, int, int, int, int, int, boolean, java.util.List, java.lang.String, java.util.Map, java.lang.String, boolean, boolean, boolean, boolean, boolean, r7.a, r7.l, r7.l, r7.l, r7.l, r7.l, boolean, r7.l, r7.l, androidx.compose.runtime.Composer, int, int, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$1$0(int i10) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float DetailsContent$lambda$10$0(ScrollState scrollState, MutableState mutableState) {
        if (DetailsContent$lambda$8(mutableState) < 0.0f || scrollState.getValue() <= DetailsContent$lambda$8(mutableState)) {
            return 0.0f;
        }
        return qb.d.m((scrollState.getValue() - DetailsContent$lambda$8(mutableState)) / 150.0f, 0.0f, 1.0f);
    }

    private static final float DetailsContent$lambda$11(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$16$0$0$0$0(ScrollState scrollState, State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(DetailsContent$lambda$11(state));
        graphicsLayerScope.setTranslationY(scrollState.getValue());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$16$0$0$2$0$0(ScrollState scrollState, int i10, Density density, MutableState mutableState, LayoutCoordinates layoutCoordinates) {
        float fM3237getYimpl = (Offset.m3237getYimpl(LayoutCoordinatesKt.positionInWindow(layoutCoordinates)) + scrollState.getValue()) - (i10 - density.mo285toPx0680j_4(Dp.m5678constructorimpl(12)));
        if (DetailsContent$lambda$8(mutableState) < 0.0f || Math.abs(fM3237getYimpl - DetailsContent$lambda$8(mutableState)) > 10.0f) {
            DetailsContent$lambda$9(mutableState, fM3237getYimpl);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$16$0$0$2$1$0(ScrollState scrollState, MutableState mutableState, GraphicsLayerScope graphicsLayerScope) {
        if (DetailsContent$lambda$8(mutableState) >= 0.0f && scrollState.getValue() > DetailsContent$lambda$8(mutableState)) {
            float value = scrollState.getValue() - DetailsContent$lambda$8(mutableState);
            graphicsLayerScope.setTranslationY(value);
            float fM = 1.0f - (qb.d.m(value / 200.0f, 0.0f, 1.0f) * 0.28f);
            graphicsLayerScope.setScaleX(fM);
            graphicsLayerScope.setScaleY(fM);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$16$0$1$0$0(r7.l lVar) {
        lVar.invoke(0);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$16$0$1$1$0$0(r7.l lVar) {
        lVar.invoke(1);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$16$0$1$1$1$0(r7.l lVar) {
        lVar.invoke(2);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$16$0$1$1$2$0(r7.l lVar) {
        lVar.invoke(3);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$16$0$1$1$3$0(r7.l lVar) {
        lVar.invoke(4);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$16$0$1$2$0$1$0(r7.l lVar, int i10) {
        lVar.invoke(Integer.valueOf(i10));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$16$0$1$2$0$2$0$0(r7.l lVar, int i10) {
        lVar.invoke(Integer.valueOf(i10));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$16$0$11$0(List list, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new DetailsScreenKt$DetailsContent$lambda$16$0$11$0$$inlined$itemsIndexed$1(new g(9), list), new DetailsScreenKt$DetailsContent$lambda$16$0$11$0$$inlined$itemsIndexed$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new DetailsScreenKt$DetailsContent$lambda$16$0$11$0$$inlined$itemsIndexed$3(list)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object DetailsContent$lambda$16$0$11$0$0(int i10, Review review) {
        return "mob_review_" + review.getAuthor() + "_" + i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$16$0$2$0(List list, boolean z, r7.l lVar, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new DetailsScreenKt$DetailsContent$lambda$16$0$2$0$$inlined$itemsIndexed$1(new g(10), list), new DetailsScreenKt$DetailsContent$lambda$16$0$2$0$$inlined$itemsIndexed$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new DetailsScreenKt$DetailsContent$lambda$16$0$2$0$$inlined$itemsIndexed$3(list, z, lVar)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object DetailsContent$lambda$16$0$2$0$0(int i10, Episode episode) {
        StringBuilder sbV = androidx.compose.foundation.c.v("mob_ep_", episode.getSeasonNumber(), "_", episode.getEpisodeNumber(), "_");
        sbV.append(i10);
        return sbV.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$16$0$5$0(List list, r7.l lVar, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new DetailsScreenKt$DetailsContent$lambda$16$0$5$0$$inlined$itemsIndexed$1(new g(6), list), new DetailsScreenKt$DetailsContent$lambda$16$0$5$0$$inlined$itemsIndexed$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new DetailsScreenKt$DetailsContent$lambda$16$0$5$0$$inlined$itemsIndexed$3(list, lVar)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object DetailsContent$lambda$16$0$5$0$0(int i10, CastMember castMember) {
        return androidx.compose.foundation.c.n(castMember.getId(), i10, "mob_cast_", "_");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$16$0$7$0(List list, Map map, boolean z, r7.l lVar, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new DetailsScreenKt$DetailsContent$lambda$16$0$7$0$$inlined$itemsIndexed$1(new g(2), list), new DetailsScreenKt$DetailsContent$lambda$16$0$7$0$$inlined$itemsIndexed$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new DetailsScreenKt$DetailsContent$lambda$16$0$7$0$$inlined$itemsIndexed$3(list, map, z, lVar)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object DetailsContent$lambda$16$0$7$0$0(int i10, MediaItem mediaItem) {
        StringBuilder sbQ = a2.q("mob_sim_", mediaItem.getMediaType().name(), "_", mediaItem.getId(), "_");
        sbQ.append(i10);
        return sbQ.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$16$0$9$0(List list, boolean z, r7.l lVar, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new DetailsScreenKt$DetailsContent$lambda$16$0$9$0$$inlined$itemsIndexed$1(new g(8), list), new DetailsScreenKt$DetailsContent$lambda$16$0$9$0$$inlined$itemsIndexed$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new DetailsScreenKt$DetailsContent$lambda$16$0$9$0$$inlined$itemsIndexed$3(list, z, lVar)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object DetailsContent$lambda$16$0$9$0$0(int i10, MediaItem mediaItem) {
        StringBuilder sbQ = a2.q("mob_col_", mediaItem.getMediaType().name(), "_", mediaItem.getId(), "_");
        sbQ.append(i10);
        return sbQ.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$17(MediaItem mediaItem, String str, List list, int i10, int i11, List list2, List list3, List list4, Map map, List list5, String str2, boolean z, int i12, FocusSection focusSection, int i13, int i14, int i15, int i16, int i17, int i18, int i19, boolean z5, List list6, String str3, Map map2, String str4, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, r7.a aVar, r7.l lVar, r7.l lVar2, r7.l lVar3, r7.l lVar4, r7.l lVar5, boolean z15, r7.l lVar6, r7.l lVar7, int i20, int i21, int i22, int i23, int i24, int i25, Composer composer, int i26) {
        DetailsContent(mediaItem, str, list, i10, i11, list2, list3, list4, map, list5, str2, z, i12, focusSection, i13, i14, i15, i16, i17, i18, i19, z5, list6, str3, map2, str4, z10, z11, z12, z13, z14, aVar, lVar, lVar2, lVar3, lVar4, lVar5, z15, lVar6, lVar7, composer, RecomposeScopeImplKt.updateChangedFlags(i20 | 1), RecomposeScopeImplKt.updateChangedFlags(i21), RecomposeScopeImplKt.updateChangedFlags(i22), RecomposeScopeImplKt.updateChangedFlags(i23), i24, i25);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$18$1$0$0$0(r7.l lVar) {
        lVar.invoke(0);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$18$1$0$11$0(r7.l lVar) {
        lVar.invoke(5);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$18$1$0$2$0(r7.l lVar) {
        lVar.invoke(1);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$18$1$0$4$0(r7.l lVar) {
        lVar.invoke(2);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$18$1$0$5$0(boolean z, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(z ? 1.0f : 0.4f);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$18$1$0$7$0(r7.l lVar) {
        lVar.invoke(3);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$18$1$0$9$0(r7.l lVar) {
        lVar.invoke(4);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$19(MediaItem mediaItem, String str, List list, int i10, int i11, List list2, List list3, List list4, Map map, List list5, String str2, boolean z, int i12, FocusSection focusSection, int i13, int i14, int i15, int i16, int i17, int i18, int i19, boolean z5, List list6, String str3, Map map2, String str4, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, r7.a aVar, r7.l lVar, r7.l lVar2, r7.l lVar3, r7.l lVar4, r7.l lVar5, boolean z15, r7.l lVar6, r7.l lVar7, int i20, int i21, int i22, int i23, int i24, int i25, Composer composer, int i26) {
        DetailsContent(mediaItem, str, list, i10, i11, list2, list3, list4, map, list5, str2, z, i12, focusSection, i13, i14, i15, i16, i17, i18, i19, z5, list6, str3, map2, str4, z10, z11, z12, z13, z14, aVar, lVar, lVar2, lVar3, lVar4, lVar5, z15, lVar6, lVar7, composer, RecomposeScopeImplKt.updateChangedFlags(i20 | 1), RecomposeScopeImplKt.updateChangedFlags(i21), RecomposeScopeImplKt.updateChangedFlags(i22), RecomposeScopeImplKt.updateChangedFlags(i23), i24, i25);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$2$0(int i10) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$3$0(int i10) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$4$0(int i10) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$5$0(int i10) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsContent$lambda$6$0(int i10) {
        return x6.t0.f22605a;
    }

    private static final float DetailsContent$lambda$8(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    private static final void DetailsContent$lambda$9(MutableState<Float> mutableState, float f10) {
        mutableState.setValue(Float.valueOf(f10));
    }

    /* JADX INFO: renamed from: DetailsEpisodeRail-3GLzNTs, reason: not valid java name */
    private static final void m6212DetailsEpisodeRail3GLzNTs(final List<Episode> list, int i10, FocusSection focusSection, Configuration configuration, float f10, float f11, final boolean z, r7.l<? super Integer, x6.t0> lVar, Composer composer, int i11) {
        int i12;
        int i13;
        boolean z5;
        float f12;
        boolean z10;
        Composer composerStartRestartGroup = composer.startRestartGroup(-691164612);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(list) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(focusSection == null ? -1 : focusSection.ordinal()) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(configuration) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i12 |= composerStartRestartGroup.changed(f10) ? 16384 : 8192;
        }
        if ((i11 & 196608) == 0) {
            i12 |= composerStartRestartGroup.changed(f11) ? 131072 : 65536;
        }
        if ((1572864 & i11) == 0) {
            i12 |= composerStartRestartGroup.changed(z) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((i11 & 12582912) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(lVar) ? 8388608 : 4194304;
        }
        if ((i12 & 4793491) == 4793490 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-691164612, i12, -1, "com.arflix.tv.ui.screens.details.DetailsEpisodeRail (DetailsScreen.kt:2840)");
            }
            final float fM5678constructorimpl = Dp.m5678constructorimpl(configuration.screenWidthDp < 1400 ? 292 : 300);
            androidx.tv.foundation.lazy.list.m0 m0VarRememberTvLazyListState = LazyListStateKt.rememberTvLazyListState(0, 0, composerStartRestartGroup, 0, 3);
            FocusSection focusSection2 = FocusSection.EPISODES;
            boolean z11 = focusSection == focusSection2 && detailsRailUsesFixedFirstSlotFocus(list.size(), i10);
            if (focusSection == focusSection2) {
                i13 = i12;
                z5 = true;
            } else {
                i13 = i12;
                z5 = false;
            }
            float f13 = 16;
            int i14 = i13;
            m6221HomeStyleRowAutoScrollNuRrP5Q(m0VarRememberTvLazyListState, z5, i10, list.size(), fM5678constructorimpl, Dp.m5678constructorimpl(f13), composerStartRestartGroup, ((i13 << 3) & 896) | 196608);
            composerStartRestartGroup = composerStartRestartGroup;
            final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(focusSection, composerStartRestartGroup, (i14 >> 6) & 14);
            final State stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(Integer.valueOf(i10), composerStartRestartGroup, (i14 >> 3) & 14);
            final State stateRememberUpdatedState3 = SnapshotStateKt.rememberUpdatedState(lVar, composerStartRestartGroup, (i14 >> 21) & 14);
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Modifier modifierArvioDpadFocusGroup$default = ArvioDpadFocusKt.arvioDpadFocusGroup$default(companion, null, false, 1, null);
            int i15 = i14 >> 9;
            float f14 = 6;
            PaddingValues paddingValuesM525PaddingValuesa9UjIt4 = PaddingKt.m525PaddingValuesa9UjIt4(f10, Dp.m5678constructorimpl(f14), m6225lockedDetailsRailEndPaddingvRza8(fM5678constructorimpl, f10, f11, Dp.m5678constructorimpl(520), composerStartRestartGroup, (i15 & 112) | 3072 | (i15 & 896)), Dp.m5678constructorimpl(f14));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = Arrangement.INSTANCE.m441spacedBy0680j_4(Dp.m5678constructorimpl(f13));
            boolean zChanged = ((i14 & 14) == 4) | composerStartRestartGroup.changed(stateRememberUpdatedState) | composerStartRestartGroup.changed(stateRememberUpdatedState2) | composerStartRestartGroup.changed(stateRememberUpdatedState3) | composerStartRestartGroup.changed(fM5678constructorimpl) | composerStartRestartGroup.changed(z11) | ((i14 & 3670016) == 1048576);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                final boolean z12 = z11;
                r7.l lVar2 = new r7.l() { // from class: com.arflix.tv.ui.screens.details.v0
                    @Override // r7.l
                    public final Object invoke(Object obj) {
                        return DetailsScreenKt.DetailsEpisodeRail_3GLzNTs$lambda$2$0$0(list, fM5678constructorimpl, z12, z, stateRememberUpdatedState, stateRememberUpdatedState2, stateRememberUpdatedState3, (androidx.tv.foundation.lazy.list.g0) obj);
                    }
                };
                f12 = fM5678constructorimpl;
                z10 = z12;
                composerStartRestartGroup.updateRememberedValue(lVar2);
                objRememberedValue = lVar2;
            } else {
                f12 = fM5678constructorimpl;
                z10 = z11;
            }
            LazyDslKt.TvLazyRow(modifierArvioDpadFocusGroup$default, m0VarRememberTvLazyListState, paddingValuesM525PaddingValuesa9UjIt4, false, horizontalOrVerticalM441spacedBy0680j_4, null, false, null, (r7.l) objRememberedValue, composerStartRestartGroup, 24576, 232);
            if (z10) {
                composerStartRestartGroup.startReplaceGroup(1997147265);
                m6220FixedDetailsRailFocusOverlayyajeYGU(f10, Dp.m5678constructorimpl(f14), f12, 1.7777778f, composerStartRestartGroup, ((i14 >> 12) & 14) | 3120);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1997373100);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r(list, i10, focusSection, configuration, f10, f11, z, lVar, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FocusSection DetailsEpisodeRail_3GLzNTs$lambda$0(State<? extends FocusSection> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int DetailsEpisodeRail_3GLzNTs$lambda$1(State<Integer> state) {
        return state.getValue().intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsEpisodeRail_3GLzNTs$lambda$2$0$0(List list, float f10, boolean z, boolean z5, State state, State state2, State state3, androidx.tv.foundation.lazy.list.g0 g0Var) {
        g0Var.items(list.size(), new DetailsScreenKt$DetailsEpisodeRail_3GLzNTs$lambda$2$0$0$$inlined$itemsIndexed$default$1(new g(5), list), new DetailsScreenKt$DetailsEpisodeRail_3GLzNTs$lambda$2$0$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-906771355, true, new DetailsScreenKt$DetailsEpisodeRail_3GLzNTs$lambda$2$0$0$$inlined$itemsIndexed$default$3(list, f10, z, z5, state, state2, state3)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object DetailsEpisodeRail_3GLzNTs$lambda$2$0$0$0(int i10, Episode episode) {
        return episode.getSeasonNumber() + "_" + episode.getEpisodeNumber() + "_" + i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsEpisodeRail_3GLzNTs$lambda$3(List list, int i10, FocusSection focusSection, Configuration configuration, float f10, float f11, boolean z, r7.l lVar, int i11, Composer composer, int i12) {
        m6212DetailsEpisodeRail3GLzNTs(list, i10, focusSection, configuration, f10, f11, z, lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x012e  */
    /* JADX INFO: renamed from: DetailsEpisodeRatingsRail-HXNGIdc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6213DetailsEpisodeRatingsRailHXNGIdc(final java.util.List<com.arflix.tv.data.model.Episode> r103, final int r104, final int r105, final int r106, int r107, final boolean r108, com.arflix.tv.ui.screens.details.FocusSection r109, float r110, float r111, androidx.compose.runtime.Composer r112, final int r113, final int r114) {
        /*
            Method dump skipped, instruction units count: 2266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.m6213DetailsEpisodeRatingsRailHXNGIdc(java.util.List, int, int, int, int, boolean, com.arflix.tv.ui.screens.details.FocusSection, float, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsEpisodeRatingsRail_HXNGIdc$lambda$0(List list, int i10, int i11, int i12, int i13, boolean z, FocusSection focusSection, float f10, float f11, int i14, int i15, Composer composer, int i16) {
        m6213DetailsEpisodeRatingsRailHXNGIdc(list, i10, i11, i12, i13, z, focusSection, f10, f11, composer, RecomposeScopeImplKt.updateChangedFlags(i14 | 1), i15);
        return x6.t0.f22605a;
    }

    private static final float DetailsEpisodeRatingsRail_HXNGIdc$lambda$11(State<Dp> state) {
        return state.getValue().m5692unboximpl();
    }

    private static final float DetailsEpisodeRatingsRail_HXNGIdc$lambda$12(State<Dp> state) {
        return state.getValue().m5692unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsEpisodeRatingsRail_HXNGIdc$lambda$13$0$0(List list, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), new DetailsScreenKt$DetailsEpisodeRatingsRail_HXNGIdc$lambda$13$0$0$$inlined$itemsIndexed$default$1(new g(11), list), new DetailsScreenKt$DetailsEpisodeRatingsRail_HXNGIdc$lambda$13$0$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new DetailsScreenKt$DetailsEpisodeRatingsRail_HXNGIdc$lambda$13$0$0$$inlined$itemsIndexed$default$3(list)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object DetailsEpisodeRatingsRail_HXNGIdc$lambda$13$0$0$0(int i10, Episode episode) {
        StringBuilder sbV = androidx.compose.foundation.c.v("mob_rate_", episode.getSeasonNumber(), "_", episode.getEpisodeNumber(), "_");
        sbV.append(i10);
        return sbV.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsEpisodeRatingsRail_HXNGIdc$lambda$14(List list, int i10, int i11, int i12, int i13, boolean z, FocusSection focusSection, float f10, float f11, int i14, int i15, Composer composer, int i16) {
        m6213DetailsEpisodeRatingsRailHXNGIdc(list, i10, i11, i12, i13, z, focusSection, f10, f11, composer, RecomposeScopeImplKt.updateChangedFlags(i14 | 1), i15);
        return x6.t0.f22605a;
    }

    private static final boolean DetailsEpisodeRatingsRail_HXNGIdc$lambda$5(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DetailsEpisodeRatingsRail_HXNGIdc$lambda$6(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean DetailsEpisodeRatingsRail_HXNGIdc$lambda$8(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DetailsEpisodeRatingsRail_HXNGIdc$lambda$9(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: renamed from: DetailsImdbSvgRatingBadge-6PoWaU8, reason: not valid java name */
    private static final void m6214DetailsImdbSvgRatingBadge6PoWaU8(String str, ImageLoader imageLoader, int i10, float f10, float f11, Shadow shadow, Composer composer, int i11) {
        int i12;
        int i13;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(2025501574);
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
                ComposerKt.traceEventStart(2025501574, i12, -1, "com.arflix.tv.ui.screens.details.DetailsImdbSvgRatingBadge (DetailsScreen.kt:3379)");
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
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
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
            AsyncImageKt.m6026AsyncImageMvsnxeU(imageRequest, "IMDb", imageLoader, SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion, f10), f11), null, null, null, ContentScale.INSTANCE.getFit(), 0.0f, null, 0, composerStartRestartGroup, ((i12 << 3) & 896) | 12582960, 0, 1904);
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, null, Color.INSTANCE.m3509getWhite0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(i13), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, shadow, null, 0, 0, 0L, null, null, null, 0, 0, null, 16769017, null), composer2, (i12 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK, 3072, 57338);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new o0(str, imageLoader, i13, f10, f11, shadow, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsImdbSvgRatingBadge_6PoWaU8$lambda$2(String str, ImageLoader imageLoader, int i10, float f10, float f11, Shadow shadow, int i11, Composer composer, int i12) {
        m6214DetailsImdbSvgRatingBadge6PoWaU8(str, imageLoader, i10, f10, f11, shadow, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: renamed from: DetailsReviewRail-BMayB_o, reason: not valid java name */
    private static final void m6215DetailsReviewRailBMayB_o(final List<Review> list, int i10, final FocusSection focusSection, float f10, final float f11, Composer composer, final int i11) {
        int i12;
        final int i13;
        float f12;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(241768686);
        if ((i11 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(list) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changed(focusSection == null ? -1 : focusSection.ordinal()) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i12 |= composerStartRestartGroup.changed(f10) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i12 |= composerStartRestartGroup.changed(f11) ? 16384 : 8192;
        }
        if ((i12 & 9363) == 9362 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            i13 = i10;
            composer2 = composerStartRestartGroup;
            f12 = f10;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(241768686, i12, -1, "com.arflix.tv.ui.screens.details.DetailsReviewRail (DetailsScreen.kt:2979)");
            }
            boolean z = false;
            androidx.tv.foundation.lazy.list.m0 m0VarRememberTvLazyListState = LazyListStateKt.rememberTvLazyListState(0, 0, composerStartRestartGroup, 0, 3);
            if (focusSection == FocusSection.REVIEWS) {
                z = true;
            }
            float f13 = 320;
            float f14 = 16;
            int i14 = i12;
            m6221HomeStyleRowAutoScrollNuRrP5Q(m0VarRememberTvLazyListState, z, i10, list.size(), Dp.m5678constructorimpl(f13), Dp.m5678constructorimpl(f14), composerStartRestartGroup, ((i12 << 3) & 896) | 221184);
            i13 = i10;
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            Modifier.Companion companion = Modifier.INSTANCE;
            Arrangement arrangement = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(Alignment.INSTANCE, arrangement.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.reviews, composerStartRestartGroup, 0), PaddingKt.m533paddingqDBjuR0$default(companion, f10, 0.0f, 0.0f, Dp.m5678constructorimpl(10), 6, null), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.9f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArvioSkin.INSTANCE.getTypography(composerStartRestartGroup, 6).getSectionTitle(), 0L, TextUnitKt.getSp(14), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65528);
            Modifier modifierArvioDpadFocusGroup$default = ArvioDpadFocusKt.arvioDpadFocusGroup$default(companion, null, false, 1, null);
            int i15 = i14 >> 6;
            f12 = f10;
            float f15 = 14;
            PaddingValues paddingValuesM525PaddingValuesa9UjIt4 = PaddingKt.m525PaddingValuesa9UjIt4(f12, Dp.m5678constructorimpl(f15), m6225lockedDetailsRailEndPaddingvRza8(Dp.m5678constructorimpl(f13), f12, f11, Dp.m5678constructorimpl(350), composerStartRestartGroup, (i15 & 896) | (i15 & 112) | 3078), Dp.m5678constructorimpl(f15));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(f14));
            boolean z5 = ((i14 & 14) == 4) | ((i14 & 896) == 256) | ((i14 & 112) == 32);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new r7.l() { // from class: com.arflix.tv.ui.screens.details.a0
                    @Override // r7.l
                    public final Object invoke(Object obj) {
                        return DetailsScreenKt.DetailsReviewRail_BMayB_o$lambda$0$0$0(list, focusSection, i13, (androidx.tv.foundation.lazy.list.g0) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            LazyDslKt.TvLazyRow(modifierArvioDpadFocusGroup$default, m0VarRememberTvLazyListState, paddingValuesM525PaddingValuesa9UjIt4, false, horizontalOrVerticalM441spacedBy0680j_4, null, false, null, (r7.l) objRememberedValue, composerStartRestartGroup, 24576, 232);
            composer2 = composerStartRestartGroup;
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final float f16 = f12;
            final int i16 = i13;
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.details.b0
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return DetailsScreenKt.DetailsReviewRail_BMayB_o$lambda$1(list, i16, focusSection, f16, f11, i11, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsReviewRail_BMayB_o$lambda$0$0$0(List list, FocusSection focusSection, int i10, androidx.tv.foundation.lazy.list.g0 g0Var) {
        g0Var.items(list.size(), new DetailsScreenKt$DetailsReviewRail_BMayB_o$lambda$0$0$0$$inlined$itemsIndexed$default$1(new g(3), list), new DetailsScreenKt$DetailsReviewRail_BMayB_o$lambda$0$0$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-906771355, true, new DetailsScreenKt$DetailsReviewRail_BMayB_o$lambda$0$0$0$$inlined$itemsIndexed$default$3(list, focusSection, i10)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object DetailsReviewRail_BMayB_o$lambda$0$0$0$0(int i10, Review review) {
        return a2.j(i10, review.getId(), "_");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsReviewRail_BMayB_o$lambda$1(List list, int i10, FocusSection focusSection, float f10, float f11, int i11, Composer composer, int i12) {
        m6215DetailsReviewRailBMayB_o(list, i10, focusSection, f10, f11, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0189  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0286  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x038a  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x03f9  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0408  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0424  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0458  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x048c  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x04bb  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x04c5  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x04d5  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x04ea  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x04ff  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0516  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x052b  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x053a  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0540  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0543  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0556  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0571  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x057f  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0591  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x059c  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x05b3  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x05c9  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x05e0  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x05ec  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x05fe  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x0613  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0628  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x063b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x064b  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x064e  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0659  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x065c  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0668  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x066b  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0675  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0678  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0683  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x0691  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x06ac  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x06ba  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x06cd  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x06d0  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x06d9  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x06e2  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x06fe  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x0731  */
    /* JADX WARN: Removed duplicated region for block: B:365:0x0734  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x073c  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x073f  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0747  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x074a  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0751  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x0754  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x0763  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x087e  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x08bf  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x08e6  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x08f4  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x0944  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x0947  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x0953  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x0956  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x0960  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x0963  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x0987  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x0a09  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x0a39  */
    /* JADX WARN: Removed duplicated region for block: B:436:0x0a69  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x0a95  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x0a98  */
    /* JADX WARN: Removed duplicated region for block: B:443:0x0aa0  */
    /* JADX WARN: Removed duplicated region for block: B:444:0x0aa3  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x0ab1  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x0b03  */
    /* JADX WARN: Removed duplicated region for block: B:455:0x0b1b  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x0b2d  */
    /* JADX WARN: Removed duplicated region for block: B:459:0x0b3b  */
    /* JADX WARN: Removed duplicated region for block: B:462:0x0b4a  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x0b4d  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x0b57  */
    /* JADX WARN: Removed duplicated region for block: B:467:0x0b5a  */
    /* JADX WARN: Removed duplicated region for block: B:474:0x0b72  */
    /* JADX WARN: Removed duplicated region for block: B:477:0x0b9e  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x0bb4  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x0bc2  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x0bd4  */
    /* JADX WARN: Removed duplicated region for block: B:485:0x0bde  */
    /* JADX WARN: Removed duplicated region for block: B:496:0x0c05  */
    /* JADX WARN: Removed duplicated region for block: B:499:0x0c0f  */
    /* JADX WARN: Removed duplicated region for block: B:500:0x0c12  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x0c27  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:510:0x0c69  */
    /* JADX WARN: Removed duplicated region for block: B:511:0x0c9c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:557:0x0e32  */
    /* JADX WARN: Removed duplicated region for block: B:560:0x0e3e  */
    /* JADX WARN: Removed duplicated region for block: B:561:0x0e42  */
    /* JADX WARN: Removed duplicated region for block: B:566:0x0e61  */
    /* JADX WARN: Removed duplicated region for block: B:571:0x0e82  */
    /* JADX WARN: Removed duplicated region for block: B:587:0x1038  */
    /* JADX WARN: Removed duplicated region for block: B:589:0x1040  */
    /* JADX WARN: Removed duplicated region for block: B:593:0x106c  */
    /* JADX WARN: Removed duplicated region for block: B:594:0x109b  */
    /* JADX WARN: Removed duplicated region for block: B:601:0x10d2  */
    /* JADX WARN: Removed duplicated region for block: B:604:0x10e7  */
    /* JADX WARN: Removed duplicated region for block: B:605:0x10e9  */
    /* JADX WARN: Removed duplicated region for block: B:612:0x10fb  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:661:0x135e  */
    /* JADX WARN: Removed duplicated region for block: B:666:0x13cc  */
    /* JADX WARN: Removed duplicated region for block: B:669:0x13f5  */
    /* JADX WARN: Removed duplicated region for block: B:670:0x13f7  */
    /* JADX WARN: Removed duplicated region for block: B:673:0x13ff  */
    /* JADX WARN: Removed duplicated region for block: B:674:0x1401  */
    /* JADX WARN: Removed duplicated region for block: B:677:0x1408  */
    /* JADX WARN: Removed duplicated region for block: B:678:0x140a  */
    /* JADX WARN: Removed duplicated region for block: B:685:0x141d  */
    /* JADX WARN: Removed duplicated region for block: B:688:0x143e  */
    /* JADX WARN: Removed duplicated region for block: B:689:0x1448  */
    /* JADX WARN: Removed duplicated region for block: B:692:0x1469  */
    /* JADX WARN: Removed duplicated region for block: B:693:0x1477  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:722:0x1586  */
    /* JADX WARN: Removed duplicated region for block: B:727:0x15a2  */
    /* JADX WARN: Removed duplicated region for block: B:730:0x15b6  */
    /* JADX WARN: Removed duplicated region for block: B:733:0x15e0  */
    /* JADX WARN: Removed duplicated region for block: B:734:0x15eb  */
    /* JADX WARN: Removed duplicated region for block: B:757:0x1658  */
    /* JADX WARN: Removed duplicated region for block: B:761:0x1678  */
    /* JADX WARN: Removed duplicated region for block: B:763:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0129  */
    /* JADX WARN: Type inference failed for: r14v107 */
    /* JADX WARN: Type inference failed for: r14v79 */
    /* JADX WARN: Type inference failed for: r14v80 */
    /* JADX WARN: Type inference failed for: r1v55 */
    /* JADX WARN: Type inference failed for: r1v56 */
    /* JADX WARN: Type inference failed for: r1v58 */
    /* JADX WARN: Type inference failed for: r1v59 */
    /* JADX WARN: Type inference failed for: r1v64 */
    /* JADX WARN: Type inference failed for: r1v65 */
    /* JADX WARN: Type inference failed for: r2v46 */
    /* JADX WARN: Type inference failed for: r2v47, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v87 */
    /* JADX WARN: Type inference failed for: r3v46 */
    /* JADX WARN: Type inference failed for: r3v47, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v48 */
    /* JADX WARN: Type inference failed for: r3v72 */
    /* JADX WARN: Type inference failed for: r3v88 */
    /* JADX WARN: Type inference failed for: r3v89 */
    /* JADX WARN: Type inference failed for: r7v34 */
    /* JADX WARN: Type inference failed for: r7v35 */
    /* JADX WARN: Type inference failed for: r7v54 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DetailsScreen(final com.arflix.tv.data.model.MediaType r105, final int r106, java.lang.Integer r107, java.lang.Integer r108, com.arflix.tv.ui.screens.details.DetailsViewModel r109, com.arflix.tv.data.model.Profile r110, final r7.v<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, ? super com.arflix.tv.data.model.EpisodeIdentity, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.String, ? super java.lang.Long, x6.t0> r111, final r7.p<? super com.arflix.tv.data.model.MediaType, ? super java.lang.Integer, x6.t0> r112, r7.l<? super java.lang.String, x6.t0> r113, r7.a<x6.t0> r114, r7.a<x6.t0> r115, r7.a<x6.t0> r116, r7.a<x6.t0> r117, r7.a<x6.t0> r118, r7.a<x6.t0> r119, final r7.a<x6.t0> r120, androidx.compose.runtime.Composer r121, final int r122, final int r123, final int r124) {
        /*
            Method dump skipped, instruction units count: 5782
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.DetailsScreen(com.arflix.tv.data.model.MediaType, int, java.lang.Integer, java.lang.Integer, com.arflix.tv.ui.screens.details.DetailsViewModel, com.arflix.tv.data.model.Profile, r7.v, r7.p, r7.l, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$0$0(String str) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState DetailsScreen$lambda$14$0() {
        return SnapshotIntStateKt.mutableIntStateOf(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState DetailsScreen$lambda$17$0() {
        return SnapshotIntStateKt.mutableIntStateOf(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState DetailsScreen$lambda$20$0() {
        return SnapshotIntStateKt.mutableIntStateOf(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DetailsScreen$lambda$39(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DetailsScreen$lambda$40(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DetailsScreen$lambda$45(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DetailsScreen$lambda$46(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DetailsScreen$lambda$48(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DetailsScreen$lambda$49(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PendingAutoPlayRequest DetailsScreen$lambda$51(MutableState<PendingAutoPlayRequest> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DetailsScreen$lambda$57(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DetailsScreen$lambda$58(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final Episode DetailsScreen$lambda$60(MutableState<Episode> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DetailsScreen$lambda$63(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DetailsScreen$lambda$64(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DetailsUiState DetailsScreen$lambda$7(State<DetailsUiState> state) {
        return state.getValue();
    }

    private static final boolean DetailsScreen$lambda$72(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void DetailsScreen$lambda$73(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean DetailsScreen$lambda$76(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DetailsScreen$lambda$77(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult DetailsScreen$lambda$82$0(final androidx.lifecycle.y yVar, final DetailsViewModel detailsViewModel, final MutableState mutableState, DisposableEffectScope disposableEffectScope) {
        final androidx.lifecycle.w wVar = new androidx.lifecycle.w() { // from class: com.arflix.tv.ui.screens.details.y0
            @Override // androidx.lifecycle.w
            public final void onStateChanged(androidx.lifecycle.y yVar2, androidx.lifecycle.p pVar) {
                DetailsScreenKt.DetailsScreen$lambda$82$0$0(detailsViewModel, mutableState, yVar2, pVar);
            }
        };
        yVar.getLifecycle().addObserver(wVar);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.details.DetailsScreenKt$DetailsScreen$lambda$82$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                yVar.getLifecycle().removeObserver(wVar);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DetailsScreen$lambda$82$0$0(DetailsViewModel detailsViewModel, MutableState mutableState, androidx.lifecycle.y yVar, androidx.lifecycle.p pVar) {
        if (pVar == androidx.lifecycle.p.ON_RESUME) {
            if (DetailsScreen$lambda$72(mutableState)) {
                DetailsScreen$lambda$73(mutableState, false);
            } else {
                detailsViewModel.refreshAfterPlayerReturn();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0076 A[PHI: r8
      0x0076: PHI (r8v14 java.lang.Integer) = (r8v3 java.lang.Integer), (r8v8 java.lang.Integer) binds: [B:27:0x0074, B:33:0x0091] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 DetailsScreen$lambda$88$0(androidx.compose.runtime.State r11, androidx.compose.runtime.State r12, com.arflix.tv.data.model.MediaType r13, com.arflix.tv.ui.screens.details.DetailsViewModel r14, androidx.compose.runtime.MutableState r15, androidx.compose.runtime.State r16, androidx.compose.runtime.MutableIntState r17, androidx.compose.runtime.MutableState r18, androidx.compose.runtime.MutableState r19, androidx.compose.runtime.MutableState r20, androidx.compose.runtime.MutableIntState r21, int r22) {
        /*
            Method dump skipped, instruction units count: 363
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.DetailsScreen$lambda$88$0(androidx.compose.runtime.State, androidx.compose.runtime.State, com.arflix.tv.data.model.MediaType, com.arflix.tv.ui.screens.details.DetailsViewModel, androidx.compose.runtime.MutableState, androidx.compose.runtime.State, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableIntState, int):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$89$0(DetailsViewModel detailsViewModel, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableIntState mutableIntState3, int i10) {
        mutableIntState.setIntValue(i10);
        mutableIntState2.setIntValue(0);
        mutableIntState3.setIntValue(0);
        detailsViewModel.loadSeason(i10 + 1);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FocusSection DetailsScreen$lambda$9(MutableState<FocusSection> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$90$0(MutableIntState mutableIntState, MutableState mutableState, int i10) {
        mutableIntState.setIntValue(i10 + 1);
        DetailsScreen$lambda$64(mutableState, true);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$91$0(State state, boolean z, DetailsViewModel detailsViewModel, MutableIntState mutableIntState, MutableState mutableState, State state2, MutableIntState mutableIntState2, MutableState mutableState2, int i10) {
        DetailsUiState detailsUiState = (DetailsUiState) state.getValue();
        Episode episode = (Episode) kotlin.collections.x.p0(i10, detailsUiState.getEpisodes());
        if (episode != null) {
            mutableIntState.setIntValue(i10);
            if (z || !detailsUiState.getAutoPlaySingleSource()) {
                DetailsScreen$lambda$46(mutableState, true);
                detailsViewModel.loadStreams(detailsUiState.getImdbId(), episode.getIdentity());
            } else {
                DetailsScreen$requestFastAutoPlay(detailsViewModel, mutableState, state2, mutableIntState2, mutableState2, detailsUiState.getImdbId(), Integer.valueOf(episode.getSeasonNumber()), Integer.valueOf(episode.getEpisodeNumber()), null, Integer.valueOf(episode.getTmdbSeasonNumber()), Integer.valueOf(episode.getTmdbEpisodeNumber()));
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$92$0(State state, DetailsViewModel detailsViewModel, int i10) {
        CastMember castMember = (CastMember) kotlin.collections.x.p0(i10, ((DetailsUiState) state.getValue()).getCast());
        if (castMember != null) {
            detailsViewModel.loadPerson(castMember.getId());
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$93$0(State state, r7.p pVar, int i10) {
        MediaItem mediaItem = (MediaItem) kotlin.collections.x.p0(i10, ((DetailsUiState) state.getValue()).getSimilar());
        if (mediaItem != null) {
            pVar.invoke(mediaItem.getMediaType(), Integer.valueOf(mediaItem.getId()));
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$94$0(State state, r7.p pVar, int i10) {
        MediaItem mediaItem = (MediaItem) kotlin.collections.x.p0(i10, ((DetailsUiState) state.getValue()).getCollectionItems());
        if (mediaItem != null) {
            pVar.invoke(mediaItem.getMediaType(), Integer.valueOf(mediaItem.getId()));
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$95$0(r7.a aVar, MutableState mutableState) {
        if (DetailsScreen$lambda$48(mutableState)) {
            DetailsScreen$lambda$49(mutableState, false);
        } else {
            aVar.invoke();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$1$0(DetailsViewModel detailsViewModel) {
        detailsViewModel.closePersonModal();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$10$0(DetailsViewModel detailsViewModel, MutableIntState mutableIntState) {
        detailsViewModel.markSeasonWatched(mutableIntState.getIntValue());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$11$0(DetailsViewModel detailsViewModel, MutableIntState mutableIntState) {
        detailsViewModel.markSeasonUnwatched(mutableIntState.getIntValue());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$12$0(MutableState mutableState) {
        DetailsScreen$lambda$64(mutableState, false);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$13$0$0(DetailsViewModel detailsViewModel) {
        detailsViewModel.dismissToast();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$2$0(DetailsViewModel detailsViewModel, r7.p pVar, MediaType mediaType, int i10) {
        detailsViewModel.closePersonModal();
        pVar.invoke(mediaType, Integer.valueOf(i10));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$3$0(MutableState mutableState) {
        DetailsScreen$lambda$49(mutableState, false);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$4$0(MutableState mutableState) {
        DetailsScreen$lambda$49(mutableState, false);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$5$0$0(MutableState mutableState) {
        DetailsScreen$lambda$49(mutableState, false);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$6$0(DetailsViewModel detailsViewModel, State state, StreamSource streamSource) {
        detailsViewModel.prewarmStreamsAround(streamSource, DetailsScreen$lambda$7(state).getStreams());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$7$0(DetailsViewModel detailsViewModel, Context context, int i10, r7.v vVar, MediaType mediaType, MutableState mutableState, State state, MutableIntState mutableIntState, StreamSource streamSource) {
        boolean zIsPendingDebridStream = AutoPlaySourcePlannerKt.isPendingDebridStream(streamSource);
        x6.t0 t0Var = x6.t0.f22605a;
        if (zIsPendingDebridStream) {
            detailsViewModel.showToast(context.getString(R.string.details_toast_debrid_downloading), ToastType.ERROR);
            return t0Var;
        }
        DetailsScreen$lambda$46(mutableState, false);
        Episode episode = (Episode) kotlin.collections.x.p0(mutableIntState.getIntValue(), DetailsScreen$lambda$7(state).getEpisodes());
        detailsViewModel.recordPlayedEpisode(i10, episode != null ? episode.getIdentity() : null);
        Integer numValueOf = Integer.valueOf(i10);
        EpisodeIdentity identity = episode != null ? episode.getIdentity() : null;
        String imdbId = DetailsScreen$lambda$7(state).getImdbId();
        String url = streamSource.getUrl();
        String str = (url == null || kotlin.text.o.h0(url)) ? null : url;
        String addonId = streamSource.getAddonId();
        String str2 = !kotlin.text.o.h0(addonId) ? addonId : null;
        String source = streamSource.getSource();
        vVar.invoke(mediaType, numValueOf, identity, imdbId, str, str2, !kotlin.text.o.h0(source) ? source : null, null);
        return t0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$8$0(MutableState mutableState) {
        DetailsScreen$lambda$46(mutableState, false);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$9$0$0(Episode episode, MutableState mutableState, State state, DetailsViewModel detailsViewModel, MutableState mutableState2, MutableIntState mutableIntState, MutableState mutableState3) {
        DetailsScreen$lambda$58(mutableState, false);
        DetailsScreen$requestFastAutoPlay(detailsViewModel, mutableState2, state, mutableIntState, mutableState3, DetailsScreen$lambda$7(state).getImdbId(), Integer.valueOf(episode.getSeasonNumber()), Integer.valueOf(episode.getEpisodeNumber()), null, Integer.valueOf(episode.getTmdbSeasonNumber()), Integer.valueOf(episode.getTmdbEpisodeNumber()));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$9$1$0(DetailsViewModel detailsViewModel, Episode episode, MutableState mutableState, MutableState mutableState2, State state) {
        DetailsScreen$lambda$58(mutableState, false);
        DetailsScreen$lambda$46(mutableState2, true);
        detailsViewModel.loadStreams(DetailsScreen$lambda$7(state).getImdbId(), episode.getIdentity());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$9$2$0(DetailsViewModel detailsViewModel, Episode episode) {
        detailsViewModel.markEpisodeWatched(episode.getSeasonNumber(), episode.getEpisodeNumber(), !episode.isWatched());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$97$9$3$0(MutableState mutableState, MutableState mutableState2) {
        DetailsScreen$lambda$58(mutableState, false);
        mutableState2.setValue(null);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsScreen$lambda$98(MediaType mediaType, int i10, Integer num, Integer num2, DetailsViewModel detailsViewModel, Profile profile, r7.v vVar, r7.p pVar, r7.l lVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, r7.a aVar7, int i11, int i12, int i13, Composer composer, int i14) {
        DetailsScreen(mediaType, i10, num, num2, detailsViewModel, profile, vVar, pVar, lVar, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), RecomposeScopeImplKt.updateChangedFlags(i12), i13);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DetailsScreen$requestFastAutoPlay(DetailsViewModel detailsViewModel, MutableState<Boolean> mutableState, State<DetailsUiState> state, MutableIntState mutableIntState, MutableState<PendingAutoPlayRequest> mutableState2, String str, Integer num, Integer num2, Long l10, Integer num3, Integer num4) {
        Object next;
        EpisodeIdentity episodeIdentityResolveEpisodeIdentity;
        DetailsScreen$lambda$46(mutableState, false);
        Iterator<T> it = DetailsScreen$lambda$7(state).getEpisodes().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            Episode episode = (Episode) next;
            int seasonNumber = episode.getSeasonNumber();
            if (num != null && seasonNumber == num.intValue()) {
                int episodeNumber = episode.getEpisodeNumber();
                if (num2 != null && episodeNumber == num2.intValue()) {
                    break;
                }
            }
        }
        Episode episode2 = (Episode) next;
        if (episode2 == null || (episodeIdentityResolveEpisodeIdentity = episode2.getIdentity()) == null) {
            episodeIdentityResolveEpisodeIdentity = detailsViewModel.resolveEpisodeIdentity(num, num2, num3, num4);
        }
        detailsViewModel.loadStreams(str, episodeIdentityResolveEpisodeIdentity);
        mutableIntState.setIntValue(0);
        mutableState2.setValue(new PendingAutoPlayRequest(episodeIdentityResolveEpisodeIdentity, l10, SystemClock.elapsedRealtime()));
    }

    public static /* synthetic */ void DetailsScreen$requestFastAutoPlay$default(DetailsViewModel detailsViewModel, MutableState mutableState, State state, MutableIntState mutableIntState, MutableState mutableState2, String str, Integer num, Integer num2, Long l10, Integer num3, Integer num4, int i10, Object obj) {
        if ((i10 & 512) != 0) {
            num3 = num;
        }
        if ((i10 & 1024) != 0) {
            num4 = num2;
        }
        DetailsScreen$requestFastAutoPlay(detailsViewModel, mutableState, state, mutableIntState, mutableState2, str, num, num2, l10, num3, num4);
    }

    /* JADX INFO: renamed from: DetailsSeasonRail-4EFweAY, reason: not valid java name */
    private static final void m6216DetailsSeasonRail4EFweAY(final int i10, final int i11, final List<Episode> list, final Map<Integer, x6.x> map, final FocusSection focusSection, final int i12, final float f10, final float f11, final r7.l<? super Integer, x6.t0> lVar, Composer composer, final int i13) {
        int i14;
        float f12;
        Composer composer2;
        Composer composer3;
        Composer composerStartRestartGroup = composer.startRestartGroup(816470498);
        if ((i13 & 6) == 0) {
            i14 = (composerStartRestartGroup.changed(i10) ? 4 : 2) | i13;
        } else {
            i14 = i13;
        }
        if ((i13 & 48) == 0) {
            i14 |= composerStartRestartGroup.changed(i11) ? 32 : 16;
        }
        if ((i13 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i14 |= composerStartRestartGroup.changed(list) ? 256 : 128;
        }
        if ((i13 & 3072) == 0) {
            i14 |= composerStartRestartGroup.changed(map) ? 2048 : 1024;
        }
        if ((i13 & 24576) == 0) {
            i14 |= composerStartRestartGroup.changed(focusSection == null ? -1 : focusSection.ordinal()) ? 16384 : 8192;
        }
        if ((196608 & i13) == 0) {
            i14 |= composerStartRestartGroup.changed(i12) ? 131072 : 65536;
        }
        if ((1572864 & i13) == 0) {
            i14 |= composerStartRestartGroup.changed(f10) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((12582912 & i13) == 0) {
            f12 = f11;
            i14 |= composerStartRestartGroup.changed(f12) ? 8388608 : 4194304;
        } else {
            f12 = f11;
        }
        if ((i13 & 100663296) == 0) {
            i14 |= composerStartRestartGroup.changedInstance(lVar) ? C.BUFFER_FLAG_NOT_DEPENDED_ON : 33554432;
        }
        if ((i14 & 38347923) == 38347922 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer3 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(816470498, i14, -1, "com.arflix.tv.ui.screens.details.DetailsSeasonRail (DetailsScreen.kt:2517)");
            }
            androidx.tv.foundation.lazy.list.m0 m0VarRememberTvLazyListState = LazyListStateKt.rememberTvLazyListState(0, 0, composerStartRestartGroup, 0, 3);
            boolean z = (i14 & 14) == 4;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = kotlin.collections.x.c1(new x7.i(1, i10, 1));
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final List list2 = (List) objRememberedValue;
            float f13 = 128;
            float f14 = 8;
            int i15 = i14;
            m6221HomeStyleRowAutoScrollNuRrP5Q(m0VarRememberTvLazyListState, focusSection == FocusSection.SEASONS, i12, i10, Dp.m5678constructorimpl(f13), Dp.m5678constructorimpl(f14), composerStartRestartGroup, ((i14 >> 9) & 896) | 221184 | ((i14 << 9) & 7168));
            int i16 = i15 & 57344;
            boolean z5 = (i16 == 16384) | ((458752 & i15) == 131072);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt.derivedStateOf(new z0(focusSection, i12, 2));
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final State state = (State) objRememberedValue2;
            final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(lVar, composerStartRestartGroup, (i15 >> 24) & 14);
            Modifier modifierArvioDpadFocusGroup$default = ArvioDpadFocusKt.arvioDpadFocusGroup$default(Modifier.INSTANCE, null, false, 1, null);
            int i17 = i15 >> 15;
            float f15 = 6;
            PaddingValues paddingValuesM525PaddingValuesa9UjIt4 = PaddingKt.m525PaddingValuesa9UjIt4(f10, Dp.m5678constructorimpl(f15), m6225lockedDetailsRailEndPaddingvRza8(Dp.m5678constructorimpl(f13), f10, f12, Dp.m5678constructorimpl(150), composerStartRestartGroup, (i17 & 896) | (i17 & 112) | 3078), Dp.m5678constructorimpl(f15));
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = Arrangement.INSTANCE.m441spacedBy0680j_4(Dp.m5678constructorimpl(f14));
            boolean zChanged = composerStartRestartGroup.changed(list2) | ((i15 & 7168) == 2048) | ((i15 & 112) == 32) | ((i15 & 896) == 256) | composerStartRestartGroup.changed(stateRememberUpdatedState) | (i16 == 16384) | composerStartRestartGroup.changed(state);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                composer2 = composerStartRestartGroup;
                r7.l lVar2 = new r7.l() { // from class: com.arflix.tv.ui.screens.details.v
                    @Override // r7.l
                    public final Object invoke(Object obj) {
                        return DetailsScreenKt.DetailsSeasonRail_4EFweAY$lambda$3$0(list2, map, i11, list, focusSection, stateRememberUpdatedState, state, (androidx.tv.foundation.lazy.list.g0) obj);
                    }
                };
                composer2.updateRememberedValue(lVar2);
                objRememberedValue3 = lVar2;
            } else {
                composer2 = composerStartRestartGroup;
            }
            Composer composer4 = composer2;
            LazyDslKt.TvLazyRow(modifierArvioDpadFocusGroup$default, m0VarRememberTvLazyListState, paddingValuesM525PaddingValuesa9UjIt4, false, horizontalOrVerticalM441spacedBy0680j_4, null, false, null, (r7.l) objRememberedValue3, composer4, 24576, 232);
            composer3 = composer4;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.details.w
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return DetailsScreenKt.DetailsSeasonRail_4EFweAY$lambda$4(i10, i11, list, map, focusSection, i12, f10, f11, lVar, i13, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int DetailsSeasonRail_4EFweAY$lambda$1$0(FocusSection focusSection, int i10) {
        if (focusSection == FocusSection.SEASONS) {
            return i10;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int DetailsSeasonRail_4EFweAY$lambda$2(State<Integer> state) {
        return state.getValue().intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsSeasonRail_4EFweAY$lambda$3$0(List list, Map map, int i10, List list2, FocusSection focusSection, State state, State state2, androidx.tv.foundation.lazy.list.g0 g0Var) {
        g0Var.items(list.size(), new DetailsScreenKt$DetailsSeasonRail_4EFweAY$lambda$3$0$$inlined$itemsIndexed$default$1(new g(0), list), new DetailsScreenKt$DetailsSeasonRail_4EFweAY$lambda$3$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-906771355, true, new DetailsScreenKt$DetailsSeasonRail_4EFweAY$lambda$3$0$$inlined$itemsIndexed$default$3(list, map, i10, list2, focusSection, state, state2)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object DetailsSeasonRail_4EFweAY$lambda$3$0$0(int i10, int i11) {
        return Integer.valueOf(i11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsSeasonRail_4EFweAY$lambda$4(int i10, int i11, List list, Map map, FocusSection focusSection, int i12, float f10, float f11, r7.l lVar, int i13, Composer composer, int i14) {
        m6216DetailsSeasonRail4EFweAY(i10, i11, list, map, focusSection, i12, f10, f11, lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i13 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x030a  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x03b4  */
    /* JADX INFO: renamed from: DetailsSimilarRail-nbWgWpA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6217DetailsSimilarRailnbWgWpA(final java.util.List<com.arflix.tv.data.model.MediaItem> r76, final java.util.Map<java.lang.String, java.lang.String> r77, final int r78, final com.arflix.tv.ui.screens.details.FocusSection r79, final boolean r80, float r81, float r82, r7.l<? super java.lang.Integer, x6.t0> r83, androidx.compose.runtime.Composer r84, int r85) {
        /*
            Method dump skipped, instruction units count: 985
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.m6217DetailsSimilarRailnbWgWpA(java.util.List, java.util.Map, int, com.arflix.tv.ui.screens.details.FocusSection, boolean, float, float, r7.l, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsSimilarRail_nbWgWpA$lambda$0$0$0$0(List list, Map map, boolean z, FocusSection focusSection, int i10, boolean z5, State state, androidx.tv.foundation.lazy.list.g0 g0Var) {
        g0Var.items(list.size(), new DetailsScreenKt$DetailsSimilarRail_nbWgWpA$lambda$0$0$0$0$$inlined$itemsIndexed$default$1(new g(1), list), new DetailsScreenKt$DetailsSimilarRail_nbWgWpA$lambda$0$0$0$0$$inlined$itemsIndexed$default$2(list), ComposableLambdaKt.composableLambdaInstance(-906771355, true, new DetailsScreenKt$DetailsSimilarRail_nbWgWpA$lambda$0$0$0$0$$inlined$itemsIndexed$default$3(list, map, z, focusSection, i10, z5, state)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object DetailsSimilarRail_nbWgWpA$lambda$0$0$0$0$0(int i10, MediaItem mediaItem) {
        return mediaItem.getMediaType().name() + "_" + mediaItem.getId() + "_" + i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsSimilarRail_nbWgWpA$lambda$1(List list, Map map, int i10, FocusSection focusSection, boolean z, float f10, float f11, r7.l lVar, int i11, Composer composer, int i12) {
        m6217DetailsSimilarRailnbWgWpA(list, map, i10, focusSection, z, f10, f11, lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0316  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x03a2  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x03b6  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0400 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0406  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x0427  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x0429  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x042d  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x043a  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0441  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x0445  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x045a  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x045e  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x0469  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x0473  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x0484  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x0488  */
    /* JADX WARN: Removed duplicated region for block: B:359:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x04ce  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x0507  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x0509  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x0512  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0514  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x0547  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0549  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x0560  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x056e  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x05d4  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x05dc  */
    /* JADX WARN: Removed duplicated region for block: B:392:0x0621  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0627  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x062b  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x062d  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x0637  */
    /* JADX WARN: Removed duplicated region for block: B:401:0x0639  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x0644  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0646  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x064d  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x064f  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x0655  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x0657  */
    /* JADX WARN: Removed duplicated region for block: B:416:0x0661  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x0663  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x066d  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x066f  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x0676  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x0678  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x0689  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x068b  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x0697  */
    /* JADX WARN: Removed duplicated region for block: B:433:0x0699  */
    /* JADX WARN: Removed duplicated region for block: B:436:0x06a1  */
    /* JADX WARN: Removed duplicated region for block: B:437:0x06a3  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x06b6  */
    /* JADX WARN: Removed duplicated region for block: B:441:0x06b8  */
    /* JADX WARN: Removed duplicated region for block: B:444:0x06be  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x06c0  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x06c8  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x06ca  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x06d1  */
    /* JADX WARN: Removed duplicated region for block: B:453:0x06d3  */
    /* JADX WARN: Removed duplicated region for block: B:456:0x06db  */
    /* JADX WARN: Removed duplicated region for block: B:457:0x06dd  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x06e5  */
    /* JADX WARN: Removed duplicated region for block: B:461:0x06e7  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x06ef  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x06f1  */
    /* JADX WARN: Removed duplicated region for block: B:468:0x06f8  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x06fa  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0702  */
    /* JADX WARN: Removed duplicated region for block: B:473:0x0704  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x070a  */
    /* JADX WARN: Removed duplicated region for block: B:477:0x070c  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x0714  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x0716  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x071e  */
    /* JADX WARN: Removed duplicated region for block: B:485:0x0720  */
    /* JADX WARN: Removed duplicated region for block: B:488:0x0728  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x072a  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x0731  */
    /* JADX WARN: Removed duplicated region for block: B:493:0x0733  */
    /* JADX WARN: Removed duplicated region for block: B:496:0x073b  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x073d  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x0756  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x07ba  */
    /* JADX WARN: Removed duplicated region for block: B:511:0x07c9  */
    /* JADX WARN: Removed duplicated region for block: B:516:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: DetailsTvRows-FSc51bE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6218DetailsTvRowsFSc51bE(final androidx.compose.ui.Modifier r63, final com.arflix.tv.data.model.MediaItem r64, final java.util.List<com.arflix.tv.data.model.Episode> r65, final int r66, final int r67, final java.util.List<com.arflix.tv.data.model.CastMember> r68, final java.util.List<com.arflix.tv.data.model.Review> r69, final java.util.List<com.arflix.tv.data.model.MediaItem> r70, final java.util.Map<java.lang.String, java.lang.String> r71, java.util.List<com.arflix.tv.data.model.MediaItem> r72, java.lang.String r73, int r74, final com.arflix.tv.ui.screens.details.FocusSection r75, final com.arflix.tv.ui.screens.details.FocusSection r76, final int r77, final int r78, final int r79, final int r80, final int r81, final int r82, final java.util.Map<java.lang.Integer, x6.x> r83, final boolean r84, final boolean r85, final boolean r86, final float r87, final float r88, final android.content.res.Configuration r89, final boolean r90, final r7.l<? super java.lang.Integer, x6.t0> r91, final r7.l<? super java.lang.Integer, x6.t0> r92, final r7.l<? super java.lang.Integer, x6.t0> r93, final r7.l<? super java.lang.Integer, x6.t0> r94, r7.l<? super java.lang.Integer, x6.t0> r95, androidx.compose.runtime.Composer r96, final int r97, final int r98, final int r99, final int r100, final int r101, final int r102) {
        /*
            Method dump skipped, instruction units count: 2075
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.m6218DetailsTvRowsFSc51bE(androidx.compose.ui.Modifier, com.arflix.tv.data.model.MediaItem, java.util.List, int, int, java.util.List, java.util.List, java.util.List, java.util.Map, java.util.List, java.lang.String, int, com.arflix.tv.ui.screens.details.FocusSection, com.arflix.tv.ui.screens.details.FocusSection, int, int, int, int, int, int, java.util.Map, boolean, boolean, boolean, float, float, android.content.res.Configuration, boolean, r7.l, r7.l, r7.l, r7.l, r7.l, androidx.compose.runtime.Composer, int, int, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsTvRows_FSc51bE$lambda$0$0(int i10) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsTvRows_FSc51bE$lambda$12$0(Animatable animatable, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setTranslationY(((Number) animatable.getValue()).floatValue());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsTvRows_FSc51bE$lambda$13$0(MediaItem mediaItem, final List list, final int i10, boolean z, boolean z5, final List list2, final List list3, List list4, List list5, final int i11, final Map map, final FocusSection focusSection, final int i12, final float f10, final float f11, final r7.l lVar, final int i13, Configuration configuration, boolean z10, r7.l lVar2, final int i14, final int i15, final r7.l lVar3, final int i16, String str, int i17, boolean z11, r7.l lVar4, Map map2, int i18, r7.l lVar5, androidx.tv.foundation.lazy.list.g0 g0Var) {
        MediaType mediaType = mediaItem.getMediaType();
        MediaType mediaType2 = MediaType.TV;
        if (mediaType == mediaType2 && !list.isEmpty()) {
            if (i10 > 1) {
                a2.d(g0Var, null, ComposableLambdaKt.composableLambdaInstance(-149052914, true, new r7.q() { // from class: com.arflix.tv.ui.screens.details.i
                    @Override // r7.q
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        int iIntValue = ((Integer) obj3).intValue();
                        return DetailsScreenKt.DetailsTvRows_FSc51bE$lambda$13$0$0(i10, i11, list, map, focusSection, i12, f10, f11, lVar, (androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, iIntValue);
                    }
                }), 3);
            }
            a2.d(g0Var, null, ComposableLambdaKt.composableLambdaInstance(183070473, true, new j(list, i13, focusSection, configuration, f10, f11, z10, lVar2)), 3);
        }
        if (mediaItem.getMediaType() == mediaType2 && !list.isEmpty() && z && z5) {
            a2.d(g0Var, null, ComposableLambdaKt.composableLambdaInstance(-232366670, true, new r7.q() { // from class: com.arflix.tv.ui.screens.details.k
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return DetailsScreenKt.DetailsTvRows_FSc51bE$lambda$13$0$2(list, i10, i11, i13, i14, focusSection, f10, f11, (androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, iIntValue);
                }
            }), 3);
        }
        if (!list2.isEmpty()) {
            a2.d(g0Var, null, ComposableSingletons$DetailsScreenKt.INSTANCE.getLambda$783853457$app_playRelease(), 3);
            a2.d(g0Var, null, ComposableLambdaKt.composableLambdaInstance(-1890959878, true, new r7.q() { // from class: com.arflix.tv.ui.screens.details.l
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return DetailsScreenKt.DetailsTvRows_FSc51bE$lambda$13$0$3(list2, i15, focusSection, f10, f11, lVar3, (androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, iIntValue);
                }
            }), 3);
        }
        if (!list3.isEmpty()) {
            a2.d(g0Var, null, ComposableSingletons$DetailsScreenKt.INSTANCE.getLambda$1800073584$app_playRelease(), 3);
            a2.d(g0Var, null, ComposableLambdaKt.composableLambdaInstance(-874739751, true, new r7.q() { // from class: com.arflix.tv.ui.screens.details.m
                @Override // r7.q
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int iIntValue = ((Integer) obj3).intValue();
                    return DetailsScreenKt.DetailsTvRows_FSc51bE$lambda$13$0$4(list3, i16, focusSection, f10, f11, (androidx.tv.foundation.lazy.list.c0) obj, (Composer) obj2, iIntValue);
                }
            }), 3);
        }
        if (!list4.isEmpty()) {
            a2.d(g0Var, null, ComposableSingletons$DetailsScreenKt.INSTANCE.m6207getLambda$1478673585$app_playRelease(), 3);
            a2.d(g0Var, null, ComposableLambdaKt.composableLambdaInstance(141480376, true, new j(list4, str, i17, focusSection, z11, f10, f11, lVar4, 1)), 3);
        }
        if (!list5.isEmpty()) {
            a2.d(g0Var, null, ComposableSingletons$DetailsScreenKt.INSTANCE.m6209getLambda$462453458$app_playRelease(), 3);
            a2.d(g0Var, null, ComposableLambdaKt.composableLambdaInstance(1157700503, true, new j(list5, map2, i18, focusSection, z11, f10, f11, lVar5, 2)), 3);
        }
        a2.d(g0Var, null, ComposableSingletons$DetailsScreenKt.INSTANCE.m6206getLambda$1308153404$app_playRelease(), 3);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsTvRows_FSc51bE$lambda$13$0$0(int i10, int i11, List list, Map map, FocusSection focusSection, int i12, float f10, float f11, r7.l lVar, androidx.tv.foundation.lazy.list.c0 c0Var, Composer composer, int i13) {
        if ((i13 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-149052914, i13, -1, "com.arflix.tv.ui.screens.details.DetailsTvRows.<anonymous>.<anonymous>.<anonymous> (DetailsScreen.kt:2398)");
            }
            m6216DetailsSeasonRail4EFweAY(i10, i11, list, map, focusSection, i12, f10, f11, lVar, composer, 14155776);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsTvRows_FSc51bE$lambda$13$0$1(List list, int i10, FocusSection focusSection, Configuration configuration, float f10, float f11, boolean z, r7.l lVar, androidx.tv.foundation.lazy.list.c0 c0Var, Composer composer, int i11) {
        if ((i11 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(183070473, i11, -1, "com.arflix.tv.ui.screens.details.DetailsTvRows.<anonymous>.<anonymous>.<anonymous> (DetailsScreen.kt:2413)");
            }
            m6212DetailsEpisodeRail3GLzNTs(list, i10, focusSection, configuration, f10, f11, z, lVar, composer, 221184);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsTvRows_FSc51bE$lambda$13$0$2(List list, int i10, int i11, int i12, int i13, FocusSection focusSection, float f10, float f11, androidx.tv.foundation.lazy.list.c0 c0Var, Composer composer, int i14) {
        if ((i14 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-232366670, i14, -1, "com.arflix.tv.ui.screens.details.DetailsTvRows.<anonymous>.<anonymous>.<anonymous> (DetailsScreen.kt:2428)");
            }
            m6213DetailsEpisodeRatingsRailHXNGIdc(list, i10, i11, i12, i13, false, focusSection, f10, f11, composer, 113442816, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsTvRows_FSc51bE$lambda$13$0$3(List list, int i10, FocusSection focusSection, float f10, float f11, r7.l lVar, androidx.tv.foundation.lazy.list.c0 c0Var, Composer composer, int i11) {
        if ((i11 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1890959878, i11, -1, "com.arflix.tv.ui.screens.details.DetailsTvRows.<anonymous>.<anonymous>.<anonymous> (DetailsScreen.kt:2445)");
            }
            m6210DetailsCastRail6PoWaU8(list, i10, focusSection, f10, f11, lVar, composer, 27648);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsTvRows_FSc51bE$lambda$13$0$4(List list, int i10, FocusSection focusSection, float f10, float f11, androidx.tv.foundation.lazy.list.c0 c0Var, Composer composer, int i11) {
        if ((i11 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-874739751, i11, -1, "com.arflix.tv.ui.screens.details.DetailsTvRows.<anonymous>.<anonymous>.<anonymous> (DetailsScreen.kt:2459)");
            }
            m6215DetailsReviewRailBMayB_o(list, i10, focusSection, f10, f11, composer, 27648);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsTvRows_FSc51bE$lambda$13$0$5(List list, String str, int i10, FocusSection focusSection, boolean z, float f10, float f11, r7.l lVar, androidx.tv.foundation.lazy.list.c0 c0Var, Composer composer, int i11) {
        if ((i11 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(141480376, i11, -1, "com.arflix.tv.ui.screens.details.DetailsTvRows.<anonymous>.<anonymous>.<anonymous> (DetailsScreen.kt:2473)");
            }
            m6211DetailsCollectionRailnbWgWpA(list, str, i10, focusSection, z, f10, f11, lVar, composer, 1769472);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsTvRows_FSc51bE$lambda$13$0$6(List list, Map map, int i10, FocusSection focusSection, boolean z, float f10, float f11, r7.l lVar, androidx.tv.foundation.lazy.list.c0 c0Var, Composer composer, int i11) {
        if ((i11 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1157700503, i11, -1, "com.arflix.tv.ui.screens.details.DetailsTvRows.<anonymous>.<anonymous>.<anonymous> (DetailsScreen.kt:2489)");
            }
            m6217DetailsSimilarRailnbWgWpA(list, map, i10, focusSection, z, f10, f11, lVar, composer, 1769472);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 DetailsTvRows_FSc51bE$lambda$14(Modifier modifier, MediaItem mediaItem, List list, int i10, int i11, List list2, List list3, List list4, Map map, List list5, String str, int i12, FocusSection focusSection, FocusSection focusSection2, int i13, int i14, int i15, int i16, int i17, int i18, Map map2, boolean z, boolean z5, boolean z10, float f10, float f11, Configuration configuration, boolean z11, r7.l lVar, r7.l lVar2, r7.l lVar3, r7.l lVar4, r7.l lVar5, int i19, int i20, int i21, int i22, int i23, int i24, Composer composer, int i25) {
        m6218DetailsTvRowsFSc51bE(modifier, mediaItem, list, i10, i11, list2, list3, list4, map, list5, str, i12, focusSection, focusSection2, i13, i14, i15, i16, i17, i18, map2, z, z5, z10, f10, f11, configuration, z11, lVar, lVar2, lVar3, lVar4, lVar5, composer, RecomposeScopeImplKt.updateChangedFlags(i19 | 1), RecomposeScopeImplKt.updateChangedFlags(i20), RecomposeScopeImplKt.updateChangedFlags(i21), RecomposeScopeImplKt.updateChangedFlags(i22), i23, i24);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:139:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01dc  */
    /* JADX INFO: renamed from: EpisodeCard-EUb7tLY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6219EpisodeCardEUb7tLY(final com.arflix.tv.data.model.Episode r34, float r35, final boolean r36, boolean r37, r7.a<x6.t0> r38, androidx.compose.runtime.Composer r39, final int r40, final int r41) {
        /*
            Method dump skipped, instruction units count: 826
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.m6219EpisodeCardEUb7tLY(com.arflix.tv.data.model.Episode, float, boolean, boolean, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float EpisodeCard_EUb7tLY$lambda$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EpisodeCard_EUb7tLY$lambda$7$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(EpisodeCard_EUb7tLY$lambda$1(state));
        graphicsLayerScope.setScaleY(EpisodeCard_EUb7tLY$lambda$1(state));
        graphicsLayerScope.mo3646setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, 1.0f));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:105:0x05a0  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x05d2  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0625  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x062f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 EpisodeCard_EUb7tLY$lambda$8(com.arflix.tv.data.model.Episode r123, boolean r124, coil.request.ImageRequest r125, java.lang.String r126, boolean r127, java.lang.String r128, java.lang.String r129, coil.ImageLoader r130, java.lang.String r131, androidx.compose.foundation.layout.BoxScope r132, boolean r133, androidx.compose.runtime.Composer r134, int r135) {
        /*
            Method dump skipped, instruction units count: 2671
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.EpisodeCard_EUb7tLY$lambda$8(com.arflix.tv.data.model.Episode, boolean, coil.request.ImageRequest, java.lang.String, boolean, java.lang.String, java.lang.String, coil.ImageLoader, java.lang.String, androidx.compose.foundation.layout.BoxScope, boolean, androidx.compose.runtime.Composer, int):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 EpisodeCard_EUb7tLY$lambda$9(Episode episode, float f10, boolean z, boolean z5, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        m6219EpisodeCardEUb7tLY(episode, f10, z, z5, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: renamed from: FixedDetailsRailFocusOverlay-yajeYGU, reason: not valid java name */
    private static final void m6220FixedDetailsRailFocusOverlayyajeYGU(final float f10, final float f11, final float f12, final float f13, Composer composer, final int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-353813314);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(f10) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(f11) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changed(f12) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changed(f13) ? 2048 : 1024;
        }
        if ((i11 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-353813314, i11, -1, "com.arflix.tv.ui.screens.details.FixedDetailsRailFocusOverlay (DetailsScreen.kt:3268)");
            }
            Modifier modifierZIndex = ZIndexModifierKt.zIndex(AspectRatioKt.aspectRatio$default(SizeKt.m583width3ABfNKs(PaddingKt.m533paddingqDBjuR0$default(Modifier.INSTANCE, f10, f11, 0.0f, 0.0f, 12, null), f12), f13, false, 2, null), 4.0f);
            ArvioSkin arvioSkin = ArvioSkin.INSTANCE;
            composer2 = composerStartRestartGroup;
            ArvioFocusKt.m6499ArvioFocusableSurfaceaewEPWQ(modifierZIndex, ArvioFocusKt.m6502rememberArvioCardShapekHDZbjc(arvioSkin.getRadius(composerStartRestartGroup, 6).m6517getMdD9Ej5fM(), composerStartRestartGroup, 0, 0), Color.INSTANCE.m3507getTransparent0d7_KjU(), 1.0f, 0.97f, Dp.m5678constructorimpl((float) 2.5d), 0.0f, 0.0f, arvioSkin.getColors(composerStartRestartGroup, 6).m6491getFocusOutline0d7_KjU(), 0.0f, false, 0L, 0L, false, false, false, false, false, true, null, null, null, ComposableSingletons$DetailsScreenKt.INSTANCE.m6205getLambda$101879240$app_playRelease(), composer2, 224640, 102260736, RendererCapabilities.DECODER_SUPPORT_MASK, 3849920);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.details.h
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return DetailsScreenKt.FixedDetailsRailFocusOverlay_yajeYGU$lambda$0(f10, f11, f12, f13, i10, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FixedDetailsRailFocusOverlay_yajeYGU$lambda$0(float f10, float f11, float f12, float f13, int i10, Composer composer, int i11) {
        m6220FixedDetailsRailFocusOverlayyajeYGU(f10, f11, f12, f13, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void GenreBadge(String str, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1180151125);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1180151125, i11, -1, "com.arflix.tv.ui.screens.details.GenreBadge (DetailsScreen.kt:4476)");
            }
            float f10 = 6;
            Modifier modifierH = a2.h(5, com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.j(f10, Modifier.INSTANCE, Color.m3471copywmQWz5c$default(com.arflix.tv.ui.theme.ColorKt.getPink(), 0.15f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(com.arflix.tv.ui.theme.ColorKt.getPink(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(10), composerStartRestartGroup, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
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
            TextKt.m6020Text4IGK_g(MediaBadgesKt.formatGenreName(str), null, com.arflix.tv.ui.theme.ColorKt.getPink(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getLabel(), composer2, 0, 0, 65530);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new f(str, i10, 3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 GenreBadge$lambda$1(String str, int i10, Composer composer, int i11) {
        GenreBadge(str, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0237  */
    /* JADX INFO: renamed from: HomeStyleRowAutoScroll-NuRrP5Q, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6221HomeStyleRowAutoScrollNuRrP5Q(final androidx.tv.foundation.lazy.list.m0 r24, final boolean r25, final int r26, final int r27, final float r28, final float r29, androidx.compose.runtime.Composer r30, final int r31) {
        /*
            Method dump skipped, instruction units count: 595
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.m6221HomeStyleRowAutoScrollNuRrP5Q(androidx.tv.foundation.lazy.list.m0, boolean, int, int, float, float, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int HomeStyleRowAutoScroll_NuRrP5Q$lambda$1$0(boolean z, int i10, int i11, int i12) {
        if (!z || i10 < 0 || i11 == 0) {
            return -1;
        }
        return i10 > i12 ? i12 : i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 HomeStyleRowAutoScroll_NuRrP5Q$lambda$12(androidx.tv.foundation.lazy.list.m0 m0Var, boolean z, int i10, int i11, float f10, float f11, int i12, Composer composer, int i13) {
        m6221HomeStyleRowAutoScrollNuRrP5Q(m0Var, z, i10, i11, f10, f11, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int HomeStyleRowAutoScroll_NuRrP5Q$lambda$2(State<Integer> state) {
        return state.getValue().intValue();
    }

    private static final void ImdbBadge(String str, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(777280230);
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
                ComposerKt.traceEventStart(777280230, i11, -1, "com.arflix.tv.ui.screens.details.ImdbBadge (DetailsScreen.kt:4422)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            float f10 = 6;
            Modifier modifierH = a2.h(f10, com.arflix.tv.data.repository.g.j(f10, companion, ColorKt.Color(4294296856L)), Dp.m5678constructorimpl(10), composerStartRestartGroup, 733328855);
            Alignment.Companion companion2 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyD = a0.c.d(companion2, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
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
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(companion);
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
            TextStyle label = arflixTypography.getLabel();
            Color.Companion companion4 = Color.INSTANCE;
            TextKt.m6020Text4IGK_g("IMDb", null, companion4.m3498getBlack0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, label, composerStartRestartGroup, 390, 0, 65530);
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, null, companion4.m3498getBlack0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, arflixTypography.getLabel(), composer2, (i11 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            if (androidx.compose.foundation.c.A(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new f(str, i10, 4));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ImdbBadge$lambda$1(String str, int i10, Composer composer, int i11) {
        ImdbBadge(str, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void LanguageBadge(String str, Composer composer, int i10) {
        int i11;
        int i12;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1250986596);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
            i12 = 5;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1250986596, i11, -1, "com.arflix.tv.ui.screens.details.LanguageBadge (DetailsScreen.kt:4493)");
            }
            long purple = com.arflix.tv.ui.theme.ColorKt.getPurple();
            float f10 = 6;
            Modifier modifierH = a2.h(5, com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.j(f10, Modifier.INSTANCE, Color.m3471copywmQWz5c$default(purple, 0.15f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(purple, 0.3f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(10), composerStartRestartGroup, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
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
            i12 = 5;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str.toUpperCase(Locale.ROOT), null, purple, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getLabel(), composer2, 0, 0, 65530);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new f(str, i10, i12));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LanguageBadge$lambda$1(String str, int i10, Composer composer, int i11) {
        LanguageBadge(str, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void MetaPill(String str, Composer composer, int i10) {
        int i11;
        int i12;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(2038399085);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
            i12 = 1;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2038399085, i11, -1, "com.arflix.tv.ui.screens.details.MetaPill (DetailsScreen.kt:4405)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Color.Companion companion2 = Color.INSTANCE;
            float f10 = 6;
            Modifier modifierH = a2.h(f10, com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.j(f10, companion, Color.m3471copywmQWz5c$default(companion2.m3509getWhite0d7_KjU(), 0.05f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(companion2.m3509getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(12), composerStartRestartGroup, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
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
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            i12 = 1;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str.toUpperCase(Locale.ROOT), null, com.arflix.tv.ui.theme.ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getLabel(), composer2, 0, 0, 65530);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new f(str, i10, i12));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MetaPill$lambda$1(String str, int i10, Composer composer, int i11) {
        MetaPill(str, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02f6  */
    /* JADX WARN: Removed duplicated region for block: B:138:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x013a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void MobileActionButton(androidx.compose.ui.graphics.vector.ImageVector r58, java.lang.String r59, boolean r60, boolean r61, boolean r62, androidx.compose.ui.Modifier r63, r7.a<x6.t0> r64, androidx.compose.runtime.Composer r65, int r66, int r67) {
        /*
            Method dump skipped, instruction units count: 775
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.MobileActionButton(androidx.compose.ui.graphics.vector.ImageVector, java.lang.String, boolean, boolean, boolean, androidx.compose.ui.Modifier, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobileActionButton$lambda$1(ImageVector imageVector, String str, boolean z, boolean z5, boolean z10, Modifier modifier, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        MobileActionButton(imageVector, str, z, z5, z10, modifier, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:116:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01e4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void MobileIconActionButton(androidx.compose.ui.graphics.vector.ImageVector r31, java.lang.String r32, boolean r33, boolean r34, androidx.compose.ui.Modifier r35, r7.a<x6.t0> r36, androidx.compose.runtime.Composer r37, int r38, int r39) {
        /*
            Method dump skipped, instruction units count: 612
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.MobileIconActionButton(androidx.compose.ui.graphics.vector.ImageVector, java.lang.String, boolean, boolean, androidx.compose.ui.Modifier, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobileIconActionButton$lambda$1(ImageVector imageVector, String str, boolean z, boolean z5, Modifier modifier, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        MobileIconActionButton(imageVector, str, z, z5, modifier, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    private static final void MobileMetadataSeparator(Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-390201214);
        if (i10 == 0 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-390201214, i10, -1, "com.arflix.tv.ui.screens.details.MobileMetadataSeparator (DetailsScreen.kt:3446)");
            }
            TextKt.m6020Text4IGK_g("•", null, Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.42f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(15), FontWeight.INSTANCE.getSemiBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composerStartRestartGroup, 390, 3072, 57338);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new o(i10, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobileMetadataSeparator$lambda$0(int i10, Composer composer, int i11) {
        MobileMetadataSeparator(composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: renamed from: MobileScoreBadge-9z6LAg8, reason: not valid java name */
    private static final void m6222MobileScoreBadge9z6LAg8(final String str, final String str2, final long j10, final long j11, Composer composer, final int i10) {
        int i11;
        long j12;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1986813997);
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
        if ((i10 & 3072) == 0) {
            j12 = j11;
            i11 |= composerStartRestartGroup.changed(j12) ? 2048 : 1024;
        } else {
            j12 = j11;
        }
        if ((i11 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1986813997, i11, -1, "com.arflix.tv.ui.screens.details.MobileScoreBadge (DetailsScreen.kt:3421)");
            }
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = Arrangement.INSTANCE.m441spacedBy0680j_4(Dp.m5678constructorimpl(4));
            Modifier modifierH = a2.h(2, com.arflix.tv.data.repository.g.j(3, Modifier.INSTANCE, j10), Dp.m5678constructorimpl(6), composerStartRestartGroup, 693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM441spacedBy0680j_4, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextStyle caption = arflixTypography.getCaption();
            long sp = TextUnitKt.getSp(8);
            FontWeight.Companion companion2 = FontWeight.INSTANCE;
            int i12 = i11 & 14;
            int i13 = i11 >> 3;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, null, j12, 0L, null, null, null, 0L, null, null, 0L, 0, false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(caption, 0L, sp, companion2.getBlack(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer2, i12 | (i13 & 896), 3072, 57338);
            TextKt.m6020Text4IGK_g(str2, null, j11, 0L, null, null, null, 0L, null, null, 0L, 0, false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(11), companion2.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer2, i13 & 910, 3072, 57338);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.screens.details.x0
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return DetailsScreenKt.MobileScoreBadge_9z6LAg8$lambda$1(str, str2, j10, j11, i10, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobileScoreBadge_9z6LAg8$lambda$1(String str, String str2, long j10, long j11, int i10, Composer composer, int i11) {
        m6222MobileScoreBadge9z6LAg8(str, str2, j10, j11, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void OngoingBadge(Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2008751758);
        if (i10 == 0 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            i11 = 1;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2008751758, i10, -1, "com.arflix.tv.ui.screens.details.OngoingBadge (DetailsScreen.kt:4448)");
            }
            long jColor = ColorKt.Color(4280472558L);
            Modifier.Companion companion = Modifier.INSTANCE;
            float f10 = 6;
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.j(f10, companion, Color.m3471copywmQWz5c$default(jColor, 0.15f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(jColor, 0.3f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(10), Dp.m5678constructorimpl(5));
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(4, Arrangement.INSTANCE, composerStartRestartGroup, 693286680), Alignment.INSTANCE.getCenterVertically(), composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            IconKt.m1610Iconww6aTOc(ScheduleKt.getSchedule(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(14)), jColor, composerStartRestartGroup, 3504, 0);
            i11 = 1;
            TextKt.m6020Text4IGK_g(StringResources_androidKt.stringResource(R.string.ongoing, composerStartRestartGroup, 0).toUpperCase(Locale.ROOT), null, jColor, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getLabel(), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            composerStartRestartGroup = composerStartRestartGroup;
            if (androidx.compose.foundation.c.D(composerStartRestartGroup)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new o(i10, i11));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 OngoingBadge$lambda$1(int i10, Composer composer, int i11) {
        OngoingBadge(composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0321  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0326  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x036f  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x03db  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x03fa  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x043c  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0448  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x046b  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x048d  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0502  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0511  */
    /* JADX WARN: Removed duplicated region for block: B:180:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x020e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void PremiumActionButton(final androidx.compose.ui.graphics.vector.ImageVector r72, final java.lang.String r73, final boolean r74, boolean r75, boolean r76, boolean r77, androidx.compose.runtime.Composer r78, final int r79, final int r80) {
        /*
            Method dump skipped, instruction units count: 1316
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.PremiumActionButton(androidx.compose.ui.graphics.vector.ImageVector, java.lang.String, boolean, boolean, boolean, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PremiumActionButton$lambda$10$0$0$0(Density density, State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(PremiumActionButton$lambda$4(state));
        graphicsLayerScope.setTranslationX(density.mo285toPx0680j_4(Dp.m5678constructorimpl(6)) * (1.0f - PremiumActionButton$lambda$4(state)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PremiumActionButton$lambda$11(ImageVector imageVector, String str, boolean z, boolean z5, boolean z10, boolean z11, int i10, int i11, Composer composer, int i12) {
        PremiumActionButton(imageVector, str, z, z5, z10, z11, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    private static final float PremiumActionButton$lambda$2(State<Dp> state) {
        return state.getValue().m5692unboximpl();
    }

    private static final float PremiumActionButton$lambda$3(State<Dp> state) {
        return state.getValue().m5692unboximpl();
    }

    private static final float PremiumActionButton$lambda$4(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float PremiumActionButton$lambda$5(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final long PremiumActionButton$lambda$6(State<Color> state) {
        return state.getValue().m3482unboximpl();
    }

    private static final long PremiumActionButton$lambda$7(State<Color> state) {
        return state.getValue().m3482unboximpl();
    }

    private static final float PremiumActionButton$lambda$8(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PremiumActionButton$lambda$9$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(PremiumActionButton$lambda$5(state));
        graphicsLayerScope.setScaleY(PremiumActionButton$lambda$5(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ReviewCard(Review review, boolean z, Composer composer, int i10) {
        int i11;
        Modifier modifierGraphicsLayer;
        long jM3471copywmQWz5c$default;
        Alignment.Companion companion;
        ComposeUiNode.Companion companion2;
        Modifier.Companion companion3;
        Arrangement arrangement;
        int i12;
        Composer composer2;
        int i13;
        Composer composer3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2068654426);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(review) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer3 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2068654426, i11, -1, "com.arflix.tv.ui.screens.details.ReviewCard (DetailsScreen.kt:4273)");
            }
            float f10 = 16;
            RoundedCornerShape roundedCornerShapeM798RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10));
            State<Float> stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(z ? 1.08f : 1.0f, AnimationSpecKt.spring$default(0.5f, 1500.0f, null, 4, null), 0.0f, "review_scale", null, composerStartRestartGroup, 3120, 20);
            State<Float> stateAnimateFloatAsState2 = AnimateAsStateKt.animateFloatAsState(z ? 1.0f : 0.2f, AnimationSpecKt.tween$default(150, 0, null, 6, null), 0.0f, "review_border", null, composerStartRestartGroup, 3120, 20);
            if (ReviewCard$lambda$0(stateAnimateFloatAsState) == 1.0f) {
                composerStartRestartGroup.startReplaceGroup(1202480004);
                composerStartRestartGroup.endReplaceGroup();
                modifierGraphicsLayer = Modifier.INSTANCE;
            } else {
                composerStartRestartGroup.startReplaceGroup(1202373364);
                Modifier.Companion companion4 = Modifier.INSTANCE;
                boolean zChanged = composerStartRestartGroup.changed(stateAnimateFloatAsState);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new s(stateAnimateFloatAsState, 2);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(companion4, (r7.l) objRememberedValue);
                composerStartRestartGroup.endReplaceGroup();
            }
            Modifier.Companion companion5 = Modifier.INSTANCE;
            Modifier modifierThen = SizeKt.m564height3ABfNKs(SizeKt.m583width3ABfNKs(companion5, Dp.m5678constructorimpl(320)), Dp.m5678constructorimpl(160)).then(modifierGraphicsLayer);
            Color.Companion companion6 = Color.INSTANCE;
            Modifier modifierM178backgroundbw27NRU = BackgroundKt.m178backgroundbw27NRU(modifierThen, Color.m3471copywmQWz5c$default(companion6.m3509getWhite0d7_KjU(), z ? 0.12f : 0.06f, 0.0f, 0.0f, 0.0f, 14, null), roundedCornerShapeM798RoundedCornerShape0680j_4);
            float fM5678constructorimpl = Dp.m5678constructorimpl(z ? 2 : 1);
            if (z) {
                composerStartRestartGroup.startReplaceGroup(592992882);
                jM3471copywmQWz5c$default = ArvioSkin.INSTANCE.getColors(composerStartRestartGroup, 6).m6491getFocusOutline0d7_KjU();
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(592993855);
                composerStartRestartGroup.endReplaceGroup();
                jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(companion6.m3509getWhite0d7_KjU(), ReviewCard$lambda$1(stateAnimateFloatAsState2), 0.0f, 0.0f, 0.0f, 14, null);
            }
            Modifier modifierM = com.arflix.tv.data.repository.g.m(f10, BorderKt.m191borderxT4_qwU(modifierM178backgroundbw27NRU, fM5678constructorimpl, jM3471copywmQWz5c$default, roundedCornerShapeM798RoundedCornerShape0680j_4), composerStartRestartGroup, 733328855);
            Alignment.Companion companion7 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyD = a0.c.d(companion7, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion8 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion8.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM);
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
            r7.p pVarU = a0.c.u(companion8, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(companion5, 0.0f, 1, null);
            Arrangement arrangement2 = Arrangement.INSTANCE;
            Arrangement.HorizontalOrVertical spaceBetween = arrangement2.getSpaceBetween();
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyJ = androidx.compose.foundation.c.j(companion7, spaceBetween, composerStartRestartGroup, 6, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion8.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierFillMaxSize$default);
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
            r7.p pVarU2 = a0.c.u(companion8, composerM2991constructorimpl2, measurePolicyJ, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(10, arrangement2, composerStartRestartGroup, 693286680), companion7.getCenterVertically(), composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion8.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(companion5);
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
            r7.p pVarU3 = a0.c.u(companion8, composerM2991constructorimpl3, measurePolicyRowMeasurePolicy, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf3, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            Modifier modifierClip = ClipKt.clip(SizeKt.m578size3ABfNKs(companion5, Dp.m5678constructorimpl(36)), RoundedCornerShapeKt.getCircleShape());
            ArvioSkin arvioSkin = ArvioSkin.INSTANCE;
            Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(modifierClip, Color.m3471copywmQWz5c$default(arvioSkin.getColors(composerStartRestartGroup, 6).m6494getSurfaceRaised0d7_KjU(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null);
            Alignment center = companion7.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor4 = companion8.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierM179backgroundbw27NRU$default);
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
            r7.p pVarU4 = a0.c.u(companion8, composerM2991constructorimpl4, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl4, currentCompositionLocalMap4);
            if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf4, composerStartRestartGroup, 0, 2058660585);
            if (review.getAuthorAvatar() != null) {
                composerStartRestartGroup.startReplaceGroup(248517756);
                companion3 = companion5;
                companion = companion7;
                companion2 = companion8;
                arrangement = arrangement2;
                SingletonAsyncImageKt.m6053AsyncImage3HmZ8SU(review.getAuthorAvatar(), review.getAuthor(), SizeKt.fillMaxSize$default(companion3, 0.0f, 1, null), null, null, null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, composerStartRestartGroup, 1573248, 952);
                composer2 = composerStartRestartGroup;
                composer2.endReplaceGroup();
                i12 = 6;
            } else {
                companion = companion7;
                companion2 = companion8;
                companion3 = companion5;
                arrangement = arrangement2;
                composerStartRestartGroup.startReplaceGroup(248854106);
                i12 = 6;
                TextKt.m6020Text4IGK_g(kotlin.text.o.I0(1, review.getAuthor()).toUpperCase(Locale.ROOT), null, Color.m3471copywmQWz5c$default(companion6.m3509getWhite0d7_KjU(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arvioSkin.getTypography(composerStartRestartGroup, 6).getButton(), 0L, TextUnitKt.getSp(14), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
                composer2 = composerStartRestartGroup;
                composer2.endReplaceGroup();
            }
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion, arrangement.getTop(), composer2, 0, -1323940314);
            int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap5 = composer2.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor5 = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf5 = LayoutKt.modifierMaterializerOf(companion3);
            if (composer2.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor5);
            } else {
                composer2.useNode();
            }
            Composer composerM2991constructorimpl5 = Updater.m2991constructorimpl(composer2);
            ComposeUiNode.Companion companion9 = companion2;
            r7.p pVarU5 = a0.c.u(companion9, composerM2991constructorimpl5, measurePolicyK, composerM2991constructorimpl5, currentCompositionLocalMap5);
            if (composerM2991constructorimpl5.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                a0.c.w(currentCompositeKeyHash5, composerM2991constructorimpl5, currentCompositeKeyHash5, pVarU5);
            }
            a0.c.y(composer2, qVarModifierMaterializerOf5, composer2, 0, 2058660585);
            String author = review.getAuthor();
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(arvioSkin.getTypography(composer2, i12).getCardTitle(), 0L, TextUnitKt.getSp(13), FontWeight.INSTANCE.getSemiBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null);
            long jM3509getWhite0d7_KjU = companion6.m3509getWhite0d7_KjU();
            TextOverflow.Companion companion10 = TextOverflow.INSTANCE;
            Composer composer4 = composer2;
            TextKt.m6020Text4IGK_g(author, null, jM3509getWhite0d7_KjU, 0L, null, null, null, 0L, null, null, 0L, companion10.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, textStyleM5205copyp1EtxEg$default, composer4, RendererCapabilities.DECODER_SUPPORT_MASK, 3120, 55290);
            Composer composer5 = composer4;
            if (review.getRating() == null || review.getRating().floatValue() <= 0.0f) {
                i13 = 12;
                composer5.startReplaceGroup(65884006);
                composer5.endReplaceGroup();
            } else {
                composer5.startReplaceGroup(65065513);
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(androidx.compose.foundation.c.g(4, arrangement, composer5, 693286680), companion.getCenterVertically(), composer5, 54);
                composer5.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                CompositionLocalMap currentCompositionLocalMap6 = composer5.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor6 = companion9.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf6 = LayoutKt.modifierMaterializerOf(companion3);
                if (composer5.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer5.startReusableNode();
                if (composer5.getInserting()) {
                    composer5.createNode(constructor6);
                } else {
                    composer5.useNode();
                }
                Composer composerM2991constructorimpl6 = Updater.m2991constructorimpl(composer5);
                r7.p pVarU6 = a0.c.u(companion9, composerM2991constructorimpl6, measurePolicyRowMeasurePolicy2, composerM2991constructorimpl6, currentCompositionLocalMap6);
                if (composerM2991constructorimpl6.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                    a0.c.w(currentCompositeKeyHash6, composerM2991constructorimpl6, currentCompositeKeyHash6, pVarU6);
                }
                a0.c.y(composer5, qVarModifierMaterializerOf6, composer5, 0, 2058660585);
                IconKt.m1610Iconww6aTOc(StarKt.getStar(Icons.INSTANCE.getDefault()), (String) null, SizeKt.m578size3ABfNKs(companion3, Dp.m5678constructorimpl(12)), ColorKt.Color(4294296856L), composer5, 3504, 0);
                i13 = 12;
                TextKt.m6020Text4IGK_g(String.format("%.1f", Arrays.copyOf(new Object[]{review.getRating()}, 1)), null, Color.m3471copywmQWz5c$default(companion6.m3509getWhite0d7_KjU(), 0.8f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arvioSkin.getTypography(composer5, 6).getCaption(), 0L, TextUnitKt.getSp(11), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composer5, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
                composer5 = composer5;
                com.arflix.tv.data.repository.g.q(composer5);
            }
            composer5.endReplaceableGroup();
            composer5.endNode();
            composer5.endReplaceableGroup();
            composer5.endReplaceableGroup();
            composer5.endReplaceableGroup();
            composer5.endNode();
            composer5.endReplaceableGroup();
            composer5.endReplaceableGroup();
            composer3 = composer5;
            TextKt.m6020Text4IGK_g(review.getContent(), SizeKt.fillMaxWidth$default(companion3, 0.0f, 1, null), Color.m3471copywmQWz5c$default(companion6.m3509getWhite0d7_KjU(), 0.85f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, null, 0L, companion10.m5632getEllipsisgIe3tQ8(), false, 4, 0, null, TextStyle.m5205copyp1EtxEg$default(arvioSkin.getTypography(composer5, 6).getBody(), 0L, TextUnitKt.getSp(i13), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, TextUnitKt.getSp(18), null, null, null, 0, 0, null, 16646141, null), composer3, 432, 3120, 55288);
            if (androidx.compose.foundation.c.A(composer3)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer3.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new p0(review, z, i10, 0));
        }
    }

    private static final float ReviewCard$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float ReviewCard$lambda$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ReviewCard$lambda$2$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setScaleX(ReviewCard$lambda$0(state));
        graphicsLayerScope.setScaleY(ReviewCard$lambda$0(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ReviewCard$lambda$4(Review review, boolean z, int i10, Composer composer, int i11) {
        ReviewCard(review, z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:106:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x03fa  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:158:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0162  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SeasonButton(final int r70, final boolean r71, final boolean r72, int r73, int r74, r7.a<x6.t0> r75, r7.a<x6.t0> r76, androidx.compose.runtime.Composer r77, final int r78, final int r79) {
        /*
            Method dump skipped, instruction units count: 1048
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.SeasonButton(int, boolean, boolean, int, int, r7.a, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SeasonButton$lambda$2(int i10, boolean z, boolean z5, int i11, int i12, r7.a aVar, r7.a aVar2, int i13, int i14, Composer composer, int i15) {
        SeasonButton(i10, z, z5, i11, i12, aVar, aVar2, composer, RecomposeScopeImplKt.updateChangedFlags(i13 | 1), i14);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:92:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SimilarMediaCard(com.arflix.tv.data.model.MediaItem r55, java.lang.String r56, boolean r57, boolean r58, r7.a<x6.t0> r59, androidx.compose.runtime.Composer r60, int r61, int r62) {
        /*
            Method dump skipped, instruction units count: 472
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsScreenKt.SimilarMediaCard(com.arflix.tv.data.model.MediaItem, java.lang.String, boolean, boolean, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SimilarMediaCard$lambda$4(MediaItem mediaItem, String str, boolean z, boolean z5, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        SimilarMediaCard(mediaItem, str, z, z5, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    private static final void StatusBadge(String str, Composer composer, int i10) {
        int i11;
        int i12;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(762699446);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
            i12 = 0;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(762699446, i11, -1, "com.arflix.tv.ui.screens.details.StatusBadge (DetailsScreen.kt:4533)");
            }
            x6.x xVar = kotlin.text.o.T(str, "Return", true) ? new x6.x(Color.m3462boximpl(ColorKt.Color(4280472558L)), Color.m3462boximpl(ColorKt.Color(4280472558L))) : (!kotlin.text.o.T(str, "Ended", true) && kotlin.text.o.T(str, "Cancel", true)) ? new x6.x(Color.m3462boximpl(ColorKt.Color(4293870660L)), Color.m3462boximpl(ColorKt.Color(4293870660L))) : new x6.x(Color.m3462boximpl(ColorKt.Color(4285231744L)), Color.m3462boximpl(ColorKt.Color(4285231744L)));
            long jM3482unboximpl = ((Color) xVar.f22608i).m3482unboximpl();
            long jM3482unboximpl2 = ((Color) xVar.f22609l).m3482unboximpl();
            float f10 = 6;
            Modifier modifierH = a2.h(5, com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.j(f10, Modifier.INSTANCE, Color.m3471copywmQWz5c$default(jM3482unboximpl, 0.15f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(jM3482unboximpl, 0.3f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(10), composerStartRestartGroup, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierH);
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
            i12 = 0;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str.toUpperCase(Locale.ROOT), null, jM3482unboximpl2, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ArflixTypography.INSTANCE.getLabel(), composer2, 0, 0, 65530);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new f(str, i10, i12));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 StatusBadge$lambda$1(String str, int i10, Composer composer, int i11) {
        StatusBadge(str, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object animateDetailsScrollDelta(androidx.tv.foundation.lazy.list.m0 m0Var, float f10, int i10, d7.d<? super x6.t0> dVar) {
        Object objScroll;
        float fAbs = Math.abs(f10);
        x6.t0 t0Var = x6.t0.f22605a;
        return (fAbs > 1.0f && (objScroll = m0Var.scroll(MutatePriority.PreventUserInput, new AnonymousClass2(f10, i10, null), dVar)) == e7.a.f15033i) ? objScroll : t0Var;
    }

    /* JADX INFO: renamed from: detailsRailIsScrollable-73KfpEQ, reason: not valid java name */
    private static final boolean m6224detailsRailIsScrollable73KfpEQ(int i10, float f10, float f11, Composer composer, int i11) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1255148080, i11, -1, "com.arflix.tv.ui.screens.details.detailsRailIsScrollable (DetailsScreen.kt:3208)");
        }
        Configuration configuration = (Configuration) composer.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration());
        Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
        boolean zChanged = ((((i11 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) > 256 && composer.changed(f11)) || (i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 256) | composer.changed(configuration) | composer.changed(density) | ((((i11 & 112) ^ 48) > 32 && composer.changed(f10)) || (i11 & 48) == 32);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            int iMo279roundToPx0680j_4 = density.mo279roundToPx0680j_4(((Dp) qb.d.k(Dp.m5676boximpl(Dp.m5678constructorimpl(Dp.m5678constructorimpl(Dp.m5678constructorimpl(configuration.screenWidthDp) - Dp.m5678constructorimpl(56)) - Dp.m5678constructorimpl(12))), Dp.m5676boximpl(Dp.m5678constructorimpl(1)))).m5692unboximpl());
            int iMo279roundToPx0680j_42 = density.mo279roundToPx0680j_4(Dp.m5678constructorimpl(f10 + f11));
            if (iMo279roundToPx0680j_42 < 1) {
                iMo279roundToPx0680j_42 = 1;
            }
            int i12 = ((iMo279roundToPx0680j_4 + iMo279roundToPx0680j_42) - 1) / iMo279roundToPx0680j_42;
            if (i12 < 1) {
                i12 = 1;
            }
            objRememberedValue = Integer.valueOf(i12);
            composer.updateRememberedValue(objRememberedValue);
        }
        boolean z = i10 > ((Number) objRememberedValue).intValue();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return z;
    }

    private static final boolean detailsRailUsesFixedFirstSlotFocus(int i10, int i11) {
        return i11 >= 0 && i10 > 0 && i10 > 1 && i11 <= i10 - 1;
    }

    private static final String formatEpisodeAirDateLabel(String str) {
        String string = kotlin.text.o.L0(str).toString();
        if (string.length() == 0) {
            return null;
        }
        try {
            Locale locale = Locale.US;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
            simpleDateFormat.setLenient(false);
            Date date = simpleDateFormat.parse(string);
            return date == null ? string : new SimpleDateFormat("d MMM yyyy", locale).format(date);
        } catch (Exception unused) {
            return string;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String formatEpisodeRating(String str) {
        Float fH = kotlin.text.t.H(str);
        if (fH == null || fH.floatValue() <= 0.0f) {
            return "—";
        }
        String str2 = String.format(Locale.US, "%.1f", Arrays.copyOf(new Object[]{fH}, 1));
        return kotlin.text.u.K(str2, ".0", false) ? kotlin.text.o.H0(str2, ".") : str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long getEpisodeRatingColor(float f10) {
        return f10 >= 9.0f ? ColorKt.Color(4279790139L) : f10 >= 8.0f ? ColorKt.Color(4280857699L) : f10 >= 7.5f ? ColorKt.Color(4294234175L) : f10 >= 7.0f ? ColorKt.Color(4294155282L) : f10 >= 6.0f ? ColorKt.Color(4293348412L) : f10 > 0.0f ? ColorKt.Color(4284692852L) : Color.INSTANCE.m3501getDarkGray0d7_KjU();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long getEpisodeRatingTextColor(float f10) {
        return (f10 < 7.0f || f10 >= 8.0f) ? Color.INSTANCE.m3509getWhite0d7_KjU() : ColorKt.Color(4280098079L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean handleLeft(FocusSection focusSection, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, r7.l<? super Integer, x6.t0> lVar, r7.l<? super Integer, x6.t0> lVar2, r7.l<? super Integer, x6.t0> lVar3, r7.l<? super Integer, x6.t0> lVar4, r7.l<? super Integer, x6.t0> lVar5, r7.l<? super Integer, x6.t0> lVar6, r7.l<? super Integer, x6.t0> lVar7, r7.l<? super Integer, x6.t0> lVar8) {
        switch (WhenMappings.$EnumSwitchMapping$1[focusSection.ordinal()]) {
            case 1:
                if (i10 > 0) {
                    lVar.invoke(Integer.valueOf(i10 - 1));
                }
                return true;
            case 2:
                if (i11 > 0) {
                    lVar2.invoke(Integer.valueOf(i11 - 1));
                }
                return true;
            case 3:
                if (i12 > 0) {
                    lVar3.invoke(Integer.valueOf(i12 - 1));
                }
                return true;
            case 4:
                if (i13 > 0) {
                    lVar4.invoke(Integer.valueOf(i13 - 1));
                }
                return true;
            case 5:
                if (i14 > 0) {
                    lVar5.invoke(Integer.valueOf(i14 - 1));
                }
                return true;
            case 6:
                if (i15 > 0) {
                    lVar6.invoke(Integer.valueOf(i15 - 1));
                }
                return true;
            case 7:
                if (i16 > 0) {
                    lVar7.invoke(Integer.valueOf(i16 - 1));
                }
                return true;
            case 8:
                if (i17 > 0) {
                    lVar8.invoke(Integer.valueOf(i17 - 1));
                }
                return true;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean handleRight(FocusSection focusSection, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, DetailsUiState detailsUiState, r7.l<? super Integer, x6.t0> lVar, r7.l<? super Integer, x6.t0> lVar2, r7.l<? super Integer, x6.t0> lVar3, r7.l<? super Integer, x6.t0> lVar4, r7.l<? super Integer, x6.t0> lVar5, r7.l<? super Integer, x6.t0> lVar6, r7.l<? super Integer, x6.t0> lVar7, r7.l<? super Integer, x6.t0> lVar8) {
        switch (WhenMappings.$EnumSwitchMapping$1[focusSection.ordinal()]) {
            case 1:
                if (i10 < (detailsUiState.getCollectionId() != null ? 5 : 4)) {
                    lVar.invoke(Integer.valueOf(i10 + 1));
                }
                return true;
            case 2:
                if (i11 < detailsUiState.getEpisodes().size() - 1) {
                    lVar2.invoke(Integer.valueOf(i11 + 1));
                }
                return true;
            case 3:
                if (i12 < ((detailsUiState.getEpisodes().size() + 11) / 12) - 1) {
                    lVar3.invoke(Integer.valueOf(i12 + 1));
                }
                return true;
            case 4:
                if (i13 < detailsUiState.getTotalSeasons() - 1) {
                    lVar4.invoke(Integer.valueOf(i13 + 1));
                }
                return true;
            case 5:
                if (i14 < detailsUiState.getCast().size() - 1) {
                    lVar5.invoke(Integer.valueOf(i14 + 1));
                }
                return true;
            case 6:
                if (i15 < detailsUiState.getReviews().size() - 1) {
                    lVar6.invoke(Integer.valueOf(i15 + 1));
                }
                return true;
            case 7:
                if (i16 < detailsUiState.getSimilar().size() - 1) {
                    lVar7.invoke(Integer.valueOf(i16 + 1));
                }
                return true;
            case 8:
                if (i17 < detailsUiState.getCollectionItems().size() - 1) {
                    lVar8.invoke(Integer.valueOf(i17 + 1));
                }
                return true;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private static final String imdbRatingFor(MediaItem mediaItem) {
        return MediaBadgesKt.parseRatingValue(mediaItem.getImdbRating()) > 0.0f ? mediaItem.getImdbRating() : "";
    }

    private static final boolean isFutureEpisodeAirDate(String str) {
        String string = kotlin.text.o.L0(str).toString();
        if (string.length() == 0) {
            return false;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            simpleDateFormat.setLenient(false);
            Date date = simpleDateFormat.parse(string);
            if (date == null) {
                return false;
            }
            return date.after(new Date());
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: lockedDetailsRailEndPadding--vR-za8, reason: not valid java name */
    private static final float m6225lockedDetailsRailEndPaddingvRza8(float f10, float f11, float f12, float f13, Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1613787026, i10, -1, "com.arflix.tv.ui.screens.details.lockedDetailsRailEndPadding (DetailsScreen.kt:3235)");
        }
        float fM5692unboximpl = ((Dp) qb.d.k(Dp.m5676boximpl(Dp.m5678constructorimpl(Dp.m5678constructorimpl(Dp.m5678constructorimpl(Dp.m5678constructorimpl(((Configuration) composer.consume(AndroidCompositionLocals_androidKt.getLocalConfiguration())).screenWidthDp) - f12) - f11) - f10)), Dp.m5676boximpl(f13))).m5692unboximpl();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return fM5692unboximpl;
    }
}
