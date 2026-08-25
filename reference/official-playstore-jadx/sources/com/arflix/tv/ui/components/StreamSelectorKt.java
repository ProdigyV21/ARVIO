package com.arflix.tv.ui.components;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.FocusableKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.FlowLayoutKt;
import androidx.compose.foundation.layout.FlowRowScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.foundation.lazy.LazyListStateKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material.icons.filled.CloseKt;
import androidx.compose.material.icons.filled.CloudKt;
import androidx.compose.material.icons.filled.PlayArrowKt;
import androidx.compose.material.icons.filled.SettingsKt;
import androidx.compose.material3.IconKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.focus.FocusPropertiesKt;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tv.material3.TextKt;
import com.arflix.tv.R;
import com.arflix.tv.data.model.StreamBehaviorHints;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.repository.HomeServerRepository;
import com.arflix.tv.ui.focus.ArvioDpadFocusKt;
import com.arflix.tv.ui.motion.ArvioBackMotion;
import com.arflix.tv.ui.motion.ArvioPredictiveBackKt;
import com.arflix.tv.ui.theme.ArflixTypography;
import com.arflix.tv.ui.theme.ColorKt;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import j$.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b.\n\u0002\u0018\u0002\n\u0002\b+\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0006\n\u0002\b\u0017\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a1\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0000H\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a#\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u00002\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0000H\u0000¢\u0006\u0004\b\t\u0010\n\u001a\u001f\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\fH\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a$\u0010\u0014\u001a\u00020\u0013*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fH\u0082@¢\u0006\u0004\b\u0014\u0010\u0015\u001aÛ\u0001\u0010)\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00162\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\u0010\u0018\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0019\u001a\u00020\u00162\b\b\u0002\u0010\u001a\u001a\u00020\u00032\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00162\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00002\b\b\u0002\u0010\u001d\u001a\u00020\f2\b\b\u0002\u0010\u001e\u001a\u00020\f2\b\b\u0002\u0010 \u001a\u00020\u001f2\b\b\u0002\u0010!\u001a\u00020\u00162\u000e\b\u0002\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030\"2\u0014\b\u0002\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00130$2\u0014\b\u0002\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00130$2\u000e\b\u0002\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00130'H\u0007¢\u0006\u0004\b)\u0010*\u001aË\u0002\u0010>\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\f\u0010,\u001a\b\u0012\u0004\u0012\u00020+0\u00002\b\u0010\u0018\u001a\u0004\u0018\u00010\u00012\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00030\u00002\u0006\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\u00162\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00030\u00002\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00030\u00002\u0006\u00103\u001a\u00020\f2\u0006\u00104\u001a\u00020\f2\u0006\u00105\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u00102\u0006\u00107\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u00108\u001a\u00020\u00162\u0006\u00109\u001a\u00020\f2\u0006\u0010:\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\b\b\u0002\u0010;\u001a\u00020\f2\u0006\u0010!\u001a\u00020\u00162\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030\"2\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130$2\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130$2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00130$H\u0003¢\u0006\u0004\b>\u0010?\u001a\u0017\u0010@\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\u0001H\u0002¢\u0006\u0004\b@\u0010A\u001a\u0017\u0010B\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0001H\u0000¢\u0006\u0004\bB\u0010C\u001a!\u0010F\u001a\u00020\u00162\u0006\u0010D\u001a\u00020\u00012\b\u0010E\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\bF\u0010G\u001a#\u0010I\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\u00012\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u0003H\u0002¢\u0006\u0004\bI\u0010J\u001a\u001f\u0010M\u001a\u00020\u00162\u0006\u0010K\u001a\u00020+2\u0006\u0010L\u001a\u00020\u0003H\u0002¢\u0006\u0004\bM\u0010N\u001aC\u0010P\u001a\u00020\u00032\u0006\u0010O\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u00162\b\b\u0002\u0010;\u001a\u00020\f2\b\b\u0002\u0010!\u001a\u00020\u0016H\u0002¢\u0006\u0004\bP\u0010Q\u001a\u0017\u0010S\u001a\u00020\u00032\u0006\u0010R\u001a\u00020\u0003H\u0002¢\u0006\u0004\bS\u0010T\u001a\u0017\u0010U\u001a\u00020+2\u0006\u0010\u000b\u001a\u00020\u0001H\u0002¢\u0006\u0004\bU\u0010V\u001a#\u0010W\u001a\u0004\u0018\u00010\u00032\b\u0010R\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u001a\u001a\u00020\u0003H\u0002¢\u0006\u0004\bW\u0010X\u001a\u0015\u0010Y\u001a\u0004\u0018\u00010\u0003*\u00020\u0001H\u0002¢\u0006\u0004\bY\u0010C\u001a\u001d\u0010[\u001a\b\u0012\u0004\u0012\u00020Z0\u00002\u0006\u0010K\u001a\u00020+H\u0002¢\u0006\u0004\b[\u0010\\\u001a\u0017\u0010]\u001a\u00020\u00032\u0006\u0010K\u001a\u00020+H\u0002¢\u0006\u0004\b]\u0010^\u001a\u001b\u0010`\u001a\u0004\u0018\u00010\u00032\b\u0010_\u001a\u0004\u0018\u00010\u0003H\u0002¢\u0006\u0004\b`\u0010T\u001a\u0017\u0010a\u001a\u00020\u00032\u0006\u0010K\u001a\u00020+H\u0002¢\u0006\u0004\ba\u0010^\u001a\u0017\u0010b\u001a\u00020\u00132\u0006\u0010K\u001a\u00020+H\u0003¢\u0006\u0004\bb\u0010c\u001a\u001f\u0010e\u001a\u00020\u00132\u0006\u0010K\u001a\u00020+2\u0006\u0010d\u001a\u00020\u0016H\u0003¢\u0006\u0004\be\u0010f\u001a%\u0010h\u001a\u00020\u00132\u0006\u0010K\u001a\u00020+2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00130'H\u0003¢\u0006\u0004\bh\u0010i\u001a5\u0010m\u001a\u00020\u00132\u0006\u0010j\u001a\u00020\u00032\u0006\u0010k\u001a\u00020\u00162\u0006\u0010l\u001a\u00020\u00162\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00130'H\u0003¢\u0006\u0004\bm\u0010n\u001a\u0087\u0001\u0010p\u001a\u00020\u00132\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00030\u00002\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00030\u00002\u0006\u00103\u001a\u00020\f2\u0006\u00104\u001a\u00020\f2\u0006\u0010l\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u00102\u0006\u0010o\u001a\u00020\f2\u0006\u00109\u001a\u00020\f2\u0006\u0010:\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00130$H\u0003¢\u0006\u0004\bp\u0010q\u001a5\u0010r\u001a\u00020\u00132\u0006\u0010j\u001a\u00020\u00032\u0006\u0010k\u001a\u00020\u00162\u0006\u0010l\u001a\u00020\u00162\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00130'H\u0003¢\u0006\u0004\br\u0010n\u001a\u001f\u0010u\u001a\u00020\u00132\u0006\u0010s\u001a\u00020\u00032\u0006\u0010t\u001a\u00020\u0003H\u0003¢\u0006\u0004\bu\u0010v\u001a_\u0010x\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00162\b\b\u0002\u0010;\u001a\u00020\f2\b\b\u0002\u0010!\u001a\u00020\u00162\u000e\b\u0002\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030\"2\n\b\u0002\u0010w\u001a\u0004\u0018\u00010\u0003H\u0003¢\u0006\u0004\bx\u0010y\u001a5\u0010z\u001a\u00020\u00132\u0006\u0010K\u001a\u00020+2\u0006\u0010l\u001a\u00020\u00162\u0006\u0010k\u001a\u00020\u00162\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00130'H\u0003¢\u0006\u0004\bz\u0010{\u001a3\u0010~\u001a\u00020\u00132\u0006\u0010K\u001a\u00020+2\u0006\u0010|\u001a\u00020\f2\b\b\u0002\u0010d\u001a\u00020\u00162\b\b\u0002\u0010}\u001a\u00020\u0016H\u0003¢\u0006\u0004\b~\u0010\u007f\u001a0\u0010\u0080\u0001\u001a\u00020\u00132\b\u0010_\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010d\u001a\u00020\u00162\b\b\u0002\u0010}\u001a\u00020\u0016H\u0003¢\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001\u001a/\u0010\u0083\u0001\u001a\u00020\u00132\u0007\u0010\u0082\u0001\u001a\u00020\u00032\b\b\u0002\u0010d\u001a\u00020\u00162\b\b\u0002\u0010}\u001a\u00020\u0016H\u0003¢\u0006\u0006\b\u0083\u0001\u0010\u0081\u0001\u001a,\u0010\u0084\u0001\u001a\u00020\u00132\u0006\u0010K\u001a\u00020+2\u0006\u0010|\u001a\u00020\f2\b\b\u0002\u0010}\u001a\u00020\u0016H\u0003¢\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001\u001a.\u0010\u0088\u0001\u001a\u00020\u00132\u0006\u0010K\u001a\u00020+2\u0006\u0010|\u001a\u00020\f2\n\b\u0002\u0010\u0087\u0001\u001a\u00030\u0086\u0001H\u0003¢\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001\u001a%\u0010\u008b\u0001\u001a\u00020\u00132\u0007\u0010\u008a\u0001\u001a\u00020Z2\b\b\u0002\u0010}\u001a\u00020\u0016H\u0003¢\u0006\u0006\b\u008b\u0001\u0010\u008c\u0001\u001a\u001b\u0010\u008e\u0001\u001a\u00030\u008d\u00012\u0006\u0010j\u001a\u00020\u0003H\u0002¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001\u001a$\u0010\u0090\u0001\u001a\u00020\u00132\u0006\u0010j\u001a\u00020\u00032\b\b\u0002\u0010}\u001a\u00020\u0016H\u0003¢\u0006\u0006\b\u0090\u0001\u0010\u0091\u0001\u001a0\u0010\u0092\u0001\u001a\u00020\u00132\u0006\u0010K\u001a\u00020+2\u0006\u0010k\u001a\u00020\u00162\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00130'H\u0003¢\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001\u001aB\u0010\u009a\u0001\u001a\u00020\u00132\b\u0010\u0095\u0001\u001a\u00030\u0094\u00012\u0006\u0010t\u001a\u00020\u00032\u0006\u0010s\u001a\u00020\u00032\b\u0010\u0097\u0001\u001a\u00030\u0096\u00012\n\b\u0002\u0010\u0087\u0001\u001a\u00030\u0086\u0001H\u0003¢\u0006\u0006\b\u0098\u0001\u0010\u0099\u0001\u001a7\u0010\u009b\u0001\u001a\u00020\u00132\u0006\u0010K\u001a\u00020+2\u0006\u0010l\u001a\u00020\u00162\u0006\u0010k\u001a\u00020\u00162\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00130'H\u0003¢\u0006\u0005\b\u009b\u0001\u0010{\u001a7\u0010\u009c\u0001\u001a\u00020\u00132\u0006\u0010j\u001a\u00020\u00032\u0006\u0010k\u001a\u00020\u00162\u0006\u0010l\u001a\u00020\u00162\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00130'H\u0003¢\u0006\u0005\b\u009c\u0001\u0010n\u001a\u001a\u0010\u009d\u0001\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\u0001H\u0003¢\u0006\u0006\b\u009d\u0001\u0010\u009e\u0001\u001a\u001a\u0010\u009f\u0001\u001a\u00020\u001f2\u0006\u0010\u000b\u001a\u00020\u0001H\u0002¢\u0006\u0006\b\u009f\u0001\u0010 \u0001\u001a\u001b\u0010¢\u0001\u001a\u00020\u001f2\u0007\u0010¡\u0001\u001a\u00020\u0003H\u0002¢\u0006\u0006\b¢\u0001\u0010£\u0001\u001a%\u0010§\u0001\u001a\u00020\u001f2\b\u0010¥\u0001\u001a\u00030¤\u00012\u0007\u0010¦\u0001\u001a\u00020\u0003H\u0002¢\u0006\u0006\b§\u0001\u0010¨\u0001\u001a\u001b\u0010ª\u0001\u001a\u00020\f2\u0007\u0010©\u0001\u001a\u00020\u0003H\u0002¢\u0006\u0006\bª\u0001\u0010«\u0001\"\u0018\u0010¬\u0001\u001a\u00030\u0096\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¬\u0001\u0010\u00ad\u0001\"\u0018\u0010®\u0001\u001a\u00030\u0096\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b®\u0001\u0010\u00ad\u0001\"\u0018\u0010¯\u0001\u001a\u00030\u0096\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¯\u0001\u0010\u00ad\u0001\"\u0018\u0010°\u0001\u001a\u00030\u0096\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b°\u0001\u0010\u00ad\u0001\"\u0018\u0010±\u0001\u001a\u00030\u0096\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b±\u0001\u0010\u00ad\u0001\"\u0018\u0010²\u0001\u001a\u00030\u0096\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b²\u0001\u0010\u00ad\u0001\"\u0018\u0010³\u0001\u001a\u00030\u0096\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b³\u0001\u0010\u00ad\u0001\"\u0018\u0010´\u0001\u001a\u00030\u0096\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b´\u0001\u0010\u00ad\u0001\"\u0018\u0010µ\u0001\u001a\u00030\u0096\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bµ\u0001\u0010\u00ad\u0001\"\u0018\u0010¶\u0001\u001a\u00030\u0096\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¶\u0001\u0010\u00ad\u0001\"\u0018\u0010·\u0001\u001a\u00030\u0096\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b·\u0001\u0010\u00ad\u0001\"\u0018\u0010¸\u0001\u001a\u00030\u0096\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¸\u0001\u0010\u00ad\u0001¨\u0006¾\u0001²\u0006\u000e\u0010\u0011\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u00104\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u00103\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010/\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010.\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¹\u0001\u001a\u00020\u00038\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010;\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\r\u0010º\u0001\u001a\u00020\u00168\nX\u008a\u0084\u0002²\u0006\r\u0010»\u0001\u001a\u00020\u00168\nX\u008a\u0084\u0002²\u0006\r\u0010º\u0001\u001a\u00020\u00168\nX\u008a\u0084\u0002²\u0006\r\u0010»\u0001\u001a\u00020\u00168\nX\u008a\u0084\u0002²\u0006\u000e\u0010½\u0001\u001a\u00030¼\u00018\nX\u008a\u0084\u0002"}, d2 = {"", "Lcom/arflix/tv/data/model/StreamSource;", "streams", "", "addonOrderedIds", "Lcom/arflix/tv/ui/components/SourceAddonTab;", "buildSourceAddonTabs", "(Ljava/util/List;Ljava/util/List;)Ljava/util/List;", "tabs", "sourceAddonTabKeys", "(Ljava/util/List;)Ljava/util/List;", "stream", "", "index", "sourceStreamRowKey", "(Lcom/arflix/tv/data/model/StreamSource;I)Ljava/lang/String;", "Landroidx/tv/foundation/lazy/list/m0;", "focusedIndex", "itemCount", "Lx6/t0;", "scrollToKeepFocusVisible", "(Landroidx/tv/foundation/lazy/list/m0;IILd7/d;)Ljava/lang/Object;", "", "isVisible", "selectedStream", "isLoading", LinkHeader.Parameters.Title, MediaTrack.ROLE_SUBTITLE, "hasStreamingAddons", "completedAddons", "totalAddons", "", "streamSearchStartTime", "pluginScrapersLoading", "", "loadingPluginNames", "Lkotlin/Function1;", "onFocusedStream", "onSelect", "Lkotlin/Function0;", "onClose", "StreamSelector", "(ZLjava/util/List;Lcom/arflix/tv/data/model/StreamSource;ZLjava/lang/String;Ljava/lang/String;ZLjava/util/List;IIJZLjava/util/Set;Lr7/l;Lr7/l;Lr7/a;Landroidx/compose/runtime/Composer;III)V", "Lcom/arflix/tv/ui/components/SourcePresentation;", "flatPresentations", "sourceFilters", "selectedFilterIndex", "focusedFilterIndex", "filterFocused", "tabLabels", "tabKeys", "selectedTabIndex", "focusedTabIndex", "addonRailFocused", "listState", "addonListState", "streamsFocused", "count4K", "count1080", "elapsedSeconds", "onFilterSelected", "onAddonSelected", "OledSourceSelectorTv", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lcom/arflix/tv/data/model/StreamSource;Ljava/util/List;IIZLjava/util/List;Ljava/util/List;IIZLandroidx/tv/foundation/lazy/list/m0;Landroidx/tv/foundation/lazy/list/m0;IZIIZZIIIZLjava/util/Set;Lr7/l;Lr7/l;Lr7/l;Landroidx/compose/runtime/Composer;IIII)V", "keepsOwnStreamOrder", "(Lcom/arflix/tv/data/model/StreamSource;)Z", "sourceTabId", "(Lcom/arflix/tv/data/model/StreamSource;)Ljava/lang/String;", "candidate", "selected", "isSelectedSource", "(Lcom/arflix/tv/data/model/StreamSource;Lcom/arflix/tv/data/model/StreamSource;)Z", "blob", "isDebridLikeSource", "(Lcom/arflix/tv/data/model/StreamSource;Ljava/lang/String;)Z", "presentation", "selectedFilter", "sourceFilterMatches", "(Lcom/arflix/tv/ui/components/SourcePresentation;Ljava/lang/String;)Z", "sourceCount", "sourceStatusText", "(IIIZIZ)Ljava/lang/String;", "raw", "cleanSourceDisplayTitle", "(Ljava/lang/String;)Ljava/lang/String;", "presentSource", "(Lcom/arflix/tv/data/model/StreamSource;)Lcom/arflix/tv/ui/components/SourcePresentation;", "cleanStreamDescription", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "multiSourceCountLabel", "Lcom/arflix/tv/ui/components/SourceBadge;", "sourceBadges", "(Lcom/arflix/tv/ui/components/SourcePresentation;)Ljava/util/List;", "rowSubtitle", "(Lcom/arflix/tv/ui/components/SourcePresentation;)Ljava/lang/String;", "language", "languageBadgeText", "bestMatchReason", "PremiumQualityPill", "(Lcom/arflix/tv/ui/components/SourcePresentation;Landroidx/compose/runtime/Composer;I)V", "compact", "SourceMetadataChips", "(Lcom/arflix/tv/ui/components/SourcePresentation;ZLandroidx/compose/runtime/Composer;I)V", "onClick", "BestMatchStrip", "(Lcom/arflix/tv/ui/components/SourcePresentation;Lr7/a;Landroidx/compose/runtime/Composer;I)V", "text", "isSelected", "isFocused", "SourceFilterChip", "(Ljava/lang/String;ZZLr7/a;Landroidx/compose/runtime/Composer;I)V", "totalSources", "SourceAddonRail", "(Ljava/util/List;Ljava/util/List;IIZLandroidx/tv/foundation/lazy/list/m0;IIIIILr7/l;Landroidx/compose/runtime/Composer;II)V", "AddonRailItem", "label", "value", "RailMetric", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "message", "SourceEmptyState", "(ZIIZIZLjava/util/Set;Ljava/lang/String;Landroidx/compose/runtime/Composer;II)V", "OledSourceRow", "(Lcom/arflix/tv/ui/components/SourcePresentation;ZZLr7/a;Landroidx/compose/runtime/Composer;I)V", "maxBadges", "inverted", "SourceBadgeTray", "(Lcom/arflix/tv/ui/components/SourcePresentation;IZZLandroidx/compose/runtime/Composer;II)V", "SourceLanguageBadge", "(Ljava/lang/String;ZZLandroidx/compose/runtime/Composer;II)V", ContentDisposition.Parameters.Size, "SourceSizeBadge", "OledBadgeRow", "(Lcom/arflix/tv/ui/components/SourcePresentation;IZLandroidx/compose/runtime/Composer;II)V", "Landroidx/compose/ui/Modifier;", "modifier", "OledBadgeFlow", "(Lcom/arflix/tv/ui/components/SourcePresentation;ILandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "badge", "SourceBadgeView", "(Lcom/arflix/tv/ui/components/SourceBadge;ZLandroidx/compose/runtime/Composer;II)V", "Landroidx/compose/ui/unit/Dp;", "sourceBadgeWidth", "(Ljava/lang/String;)F", "OledTextBadge", "(Ljava/lang/String;ZLandroidx/compose/runtime/Composer;II)V", "MobileStreamCard", "(Lcom/arflix/tv/ui/components/SourcePresentation;ZLr7/a;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/graphics/vector/ImageVector;", "icon", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "MiniStatCard-uDo3WH8", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Ljava/lang/String;JLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "MiniStatCard", "GlassyStreamCard", "FilterTab", "CompactQualityBadge", "(Lcom/arflix/tv/data/model/StreamSource;Landroidx/compose/runtime/Composer;I)V", "getSizeBytes", "(Lcom/arflix/tv/data/model/StreamSource;)J", "sizeStr", "parseSizeString", "(Ljava/lang/String;)J", "", "number", "unit", "calculateBytes", "(DLjava/lang/String;)J", "quality", "qualityScore", "(Ljava/lang/String;)I", "OledPanel", "J", "OledPanelStrong", "OledBorder", "OledMutedBorder", "OledMutedText", "GlassWhite", "GlassBorder", "GlassHighlight", "AccentGreen", "AccentBlue", "AccentPurple", "AccentGold", "focusZone", "canScrollUp", "canScrollDown", "", "scale", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class StreamSelectorKt {
    private static final long AccentBlue;
    private static final long AccentGold;
    private static final long AccentGreen;
    private static final long AccentPurple;
    private static final long GlassBorder;
    private static final long GlassHighlight;
    private static final long GlassWhite;
    private static final long OledBorder;
    private static final long OledMutedBorder;
    private static final long OledMutedText;
    private static final long OledPanel;
    private static final long OledPanelStrong;

    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0018\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"com/arflix/tv/ui/components/StreamSelectorKt$StreamSelector$SourceFilter", "", "label", "", "<init>", "(Ljava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "component1", "copy", "(Ljava/lang/String;)Lcom/arflix/tv/ui/components/StreamSelectorKt$StreamSelector$SourceFilter;", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SourceFilter {
        private final String label;

        public SourceFilter(String str) {
            this.label = str;
        }

        public static /* synthetic */ SourceFilter copy$default(SourceFilter sourceFilter, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = sourceFilter.label;
            }
            return sourceFilter.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        public final SourceFilter copy(String label) {
            return new SourceFilter(label);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof SourceFilter) && kotlin.jvm.internal.p.a(this.label, ((SourceFilter) other).label);
        }

        public final String getLabel() {
            return this.label;
        }

        public int hashCode() {
            return this.label.hashCode();
        }

        public String toString() {
            return a0.c.l("SourceFilter(label=", this.label, ")");
        }
    }

    static {
        Color.Companion companion = Color.INSTANCE;
        long jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.055f, 0.0f, 0.0f, 0.0f, 14, null);
        OledPanel = jM3471copywmQWz5c$default;
        long jM3471copywmQWz5c$default2 = Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.095f, 0.0f, 0.0f, 0.0f, 14, null);
        OledPanelStrong = jM3471copywmQWz5c$default2;
        long jM3471copywmQWz5c$default3 = Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.16f, 0.0f, 0.0f, 0.0f, 14, null);
        OledBorder = jM3471copywmQWz5c$default3;
        OledMutedBorder = Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.08f, 0.0f, 0.0f, 0.0f, 14, null);
        OledMutedText = Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.58f, 0.0f, 0.0f, 0.0f, 14, null);
        GlassWhite = jM3471copywmQWz5c$default;
        GlassBorder = jM3471copywmQWz5c$default3;
        GlassHighlight = jM3471copywmQWz5c$default2;
        AccentGreen = companion.m3509getWhite0d7_KjU();
        AccentBlue = companion.m3509getWhite0d7_KjU();
        AccentPurple = Color.m3471copywmQWz5c$default(companion.m3509getWhite0d7_KjU(), 0.86f, 0.0f, 0.0f, 0.0f, 14, null);
        AccentGold = companion.m3509getWhite0d7_KjU();
    }

    private static final void AddonRailItem(String str, boolean z, boolean z5, r7.a<x6.t0> aVar, Composer composer, int i10) {
        String str2;
        int i11;
        long jM3471copywmQWz5c$default;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(65250080);
        if ((i10 & 6) == 0) {
            str2 = str;
            i11 = (composerStartRestartGroup.changed(str2) ? 4 : 2) | i10;
        } else {
            str2 = str;
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
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(65250080, i11, -1, "com.arflix.tv.ui.components.AddonRailItem (StreamSelector.kt:1806)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion2 = Composer.INSTANCE;
            if (objRememberedValue == companion2.getEmpty()) {
                objRememberedValue = new com.arflix.tv.data.api.c(13);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierFocusProperties = FocusPropertiesKt.focusProperties(modifierFillMaxWidth$default, (r7.l) objRememberedValue);
            float f10 = 11;
            Modifier modifierK = com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.l(f10, com.arflix.tv.data.repository.g.n(modifierFocusProperties, f10), z5 ? Color.INSTANCE.m3509getWhite0d7_KjU() : z ? OledPanelStrong : Color.INSTANCE.m3507getTransparent0d7_KjU()), Dp.m5678constructorimpl(1), z5 ? Color.INSTANCE.m3509getWhite0d7_KjU() : Color.INSTANCE.m3507getTransparent0d7_KjU());
            boolean z10 = (i11 & 7168) == 2048;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue2 == companion2.getEmpty()) {
                objRememberedValue2 = new g(15, aVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(ClickableKt.m214clickableXHw0xAI$default(modifierK, false, null, null, (r7.a) objRememberedValue2, 7, null), Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(9));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(Arrangement.INSTANCE, centerVertically, composerStartRestartGroup, 48, -1323940314);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyG, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(12), (z || z5) ? FontWeight.INSTANCE.getBold() : FontWeight.INSTANCE.getMedium(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null);
            if (z5) {
                jM3471copywmQWz5c$default = Color.INSTANCE.m3498getBlack0d7_KjU();
            } else {
                jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(ColorKt.getTextPrimary(), z ? 1.0f : 0.66f, 0.0f, 0.0f, 0.0f, 14, null);
            }
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str2, androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null), jM3471copywmQWz5c$default, 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, textStyleM5205copyp1EtxEg$default, composer2, i11 & 14, 3120, 55288);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new t2(str, z, z5, aVar, i10, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AddonRailItem$lambda$0$0(FocusProperties focusProperties) {
        focusProperties.setCanFocus(false);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AddonRailItem$lambda$1$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AddonRailItem$lambda$3(String str, boolean z, boolean z5, r7.a aVar, int i10, Composer composer, int i11) {
        AddonRailItem(str, z, z5, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void BestMatchStrip(SourcePresentation sourcePresentation, r7.a<x6.t0> aVar, Composer composer, int i10) {
        int i11;
        Composer composer2;
        r7.a<x6.t0> aVar2 = aVar;
        Composer composerStartRestartGroup = composer.startRestartGroup(-285147362);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(sourcePresentation) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar2) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-285147362, i11, -1, "com.arflix.tv.ui.components.BestMatchStrip (StreamSelector.kt:1592)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            float f10 = 16;
            Modifier modifierK = com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.l(f10, com.arflix.tv.data.repository.g.n(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), f10), OledPanelStrong), Dp.m5678constructorimpl(1), OledBorder);
            boolean z = (i11 & 112) == 32;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                aVar2 = aVar;
                objRememberedValue = new g(16, aVar2);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                aVar2 = aVar;
            }
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(ClickableKt.m214clickableXHw0xAI$default(modifierK, false, null, null, (r7.a) objRememberedValue, 7, null), Dp.m5678constructorimpl(f10), Dp.m5678constructorimpl(13));
            Alignment.Companion companion2 = Alignment.INSTANCE;
            Alignment.Vertical centerVertically = companion2.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            Arrangement arrangement = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(arrangement, centerVertically, composerStartRestartGroup, 48, -1323940314);
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
            r7.p pVarU = a0.c.u(companion3, composerM2991constructorimpl, measurePolicyG, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            Modifier modifierM578size3ABfNKs = SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(42));
            Color.Companion companion4 = Color.INSTANCE;
            int i12 = i11;
            Modifier modifierM178backgroundbw27NRU = BackgroundKt.m178backgroundbw27NRU(modifierM578size3ABfNKs, companion4.m3509getWhite0d7_KjU(), RoundedCornerShapeKt.getCircleShape());
            Alignment center = companion2.getCenter();
            composerStartRestartGroup.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM178backgroundbw27NRU);
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
            r7.p pVarU2 = a0.c.u(companion3, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            IconKt.m1610Iconww6aTOc(PlayArrowKt.getPlayArrow(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.play, composerStartRestartGroup, 0), SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(25)), companion4.m3498getBlack0d7_KjU(), composerStartRestartGroup, 3456, 0);
            androidx.compose.foundation.c.y(composerStartRestartGroup);
            float f11 = 14;
            androidx.compose.material3.d.w(f11, companion, composerStartRestartGroup, 6);
            Modifier modifierA = androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion2, arrangement.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierA);
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
            r7.p pVarU3 = a0.c.u(companion3, composerM2991constructorimpl3, measurePolicyK, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf3, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Alignment.Vertical centerVertically2 = companion2.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyG2 = androidx.compose.material3.d.g(arrangement, centerVertically2, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor4 = companion3.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(companion);
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
            r7.p pVarU4 = a0.c.u(companion3, composerM2991constructorimpl4, measurePolicyG2, composerM2991constructorimpl4, currentCompositionLocalMap4);
            if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf4, composerStartRestartGroup, 0, 2058660585);
            String strStringResource = StringResources_androidKt.stringResource(R.string.stream_best_match, composerStartRestartGroup, 0);
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextStyle caption = arflixTypography.getCaption();
            long sp = TextUnitKt.getSp(11);
            FontWeight.Companion companion5 = FontWeight.INSTANCE;
            TextKt.m6020Text4IGK_g(strStringResource, null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(caption, 0L, sp, companion5.getBold(), null, null, null, null, TextUnitKt.getSp(1), null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777081, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            androidx.compose.material3.d.w(10, companion, composerStartRestartGroup, 6);
            String strBestMatchReason = bestMatchReason(sourcePresentation);
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(12), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null);
            long j10 = OledMutedText;
            TextOverflow.Companion companion6 = TextOverflow.INSTANCE;
            TextKt.m6020Text4IGK_g(strBestMatchReason, null, j10, 0L, null, null, null, 0L, null, null, 0L, companion6.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, textStyleM5205copyp1EtxEg$default, composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 3120, 55290);
            androidx.compose.foundation.c.y(composerStartRestartGroup);
            com.arflix.tv.data.repository.g.p(5, companion, composerStartRestartGroup, 6);
            TextKt.m6020Text4IGK_g(androidx.compose.foundation.c.t(sourcePresentation.getAddonLabel(), " - ", sourcePresentation.getRawTitle()), null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, companion6.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getBody(), 0L, TextUnitKt.getSp(15), companion5.getSemiBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 3120, 55290);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            SpacerKt.Spacer(SizeKt.m583width3ABfNKs(companion, Dp.m5678constructorimpl(f11)), composerStartRestartGroup, 6);
            OledBadgeRow(sourcePresentation, 6, false, composerStartRestartGroup, (i12 & 14) | 48, 4);
            composer2 = composerStartRestartGroup;
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new x2(sourcePresentation, aVar2, i10, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 BestMatchStrip$lambda$0$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 BestMatchStrip$lambda$2(SourcePresentation sourcePresentation, r7.a aVar, int i10, Composer composer, int i11) {
        BestMatchStrip(sourcePresentation, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x03aa  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x04ab  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x05ae  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x05bd  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x039c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void CompactQualityBadge(com.arflix.tv.data.model.StreamSource r103, androidx.compose.runtime.Composer r104, int r105) {
        /*
            Method dump skipped, instruction units count: 1492
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.CompactQualityBadge(com.arflix.tv.data.model.StreamSource, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 CompactQualityBadge$lambda$2(StreamSource streamSource, int i10, Composer composer, int i11) {
        CompactQualityBadge(streamSource, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void FilterTab(String str, boolean z, boolean z5, r7.a<x6.t0> aVar, Composer composer, int i10) {
        String str2;
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1148188744);
        if ((i10 & 6) == 0) {
            str2 = str;
            i11 = (composerStartRestartGroup.changed(str2) ? 4 : 2) | i10;
        } else {
            str2 = str;
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
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1148188744, i11, -1, "com.arflix.tv.ui.components.FilterTab (StreamSelector.kt:2473)");
            }
            Modifier modifierM191borderxT4_qwU = Modifier.INSTANCE;
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(modifierM191borderxT4_qwU, 0.0f, 1, null);
            boolean z10 = (i11 & 7168) == 2048;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            int i12 = 13;
            if (z10 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new g(i12, aVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            float f10 = 8;
            Modifier modifierL = com.arflix.tv.data.repository.g.l(f10, ClickableKt.m214clickableXHw0xAI$default(modifierFillMaxWidth$default, false, null, null, (r7.a) objRememberedValue, 7, null), z5 ? Color.INSTANCE.m3509getWhite0d7_KjU() : z ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.15f, 0.0f, 0.0f, 0.0f, 14, null) : Color.INSTANCE.m3507getTransparent0d7_KjU());
            if (z && !z5) {
                modifierM191borderxT4_qwU = BorderKt.m191borderxT4_qwU(modifierM191borderxT4_qwU, Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f10)));
            }
            Modifier modifierH = androidx.fragment.app.a2.h(10, modifierL.then(modifierM191borderxT4_qwU), Dp.m5678constructorimpl(12), composerStartRestartGroup, 733328855);
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
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getBody(), 0L, TextUnitKt.getSp(13), (z || z5) ? FontWeight.INSTANCE.getSemiBold() : FontWeight.INSTANCE.getNormal(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null);
            Color.Companion companion2 = Color.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str2, null, z5 ? companion2.m3498getBlack0d7_KjU() : companion2.m3509getWhite0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, textStyleM5205copyp1EtxEg$default, composer2, i11 & 14, 3120, 55290);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new t2(str, z, z5, aVar, i10, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FilterTab$lambda$0$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FilterTab$lambda$2(String str, boolean z, boolean z5, r7.a aVar, int i10, Composer composer, int i11) {
        FilterTab(str, z, z5, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x034b  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0398  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x03e8  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0480  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x048c  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0490  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x04f5  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0501  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0505  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0529  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0546  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x054d  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x058d  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0593  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x06af  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x06bd  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0775  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0784  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void GlassyStreamCard(com.arflix.tv.ui.components.SourcePresentation r79, boolean r80, boolean r81, r7.a<x6.t0> r82, androidx.compose.runtime.Composer r83, int r84) {
        /*
            Method dump skipped, instruction units count: 1951
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.GlassyStreamCard(com.arflix.tv.ui.components.SourcePresentation, boolean, boolean, r7.a, androidx.compose.runtime.Composer, int):void");
    }

    private static final float GlassyStreamCard$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 GlassyStreamCard$lambda$1$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 GlassyStreamCard$lambda$3(SourcePresentation sourcePresentation, boolean z, boolean z5, r7.a aVar, int i10, Composer composer, int i11) {
        GlassyStreamCard(sourcePresentation, z, z5, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x019b  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x028c  */
    /* JADX WARN: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: MiniStatCard-uDo3WH8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m6191MiniStatCarduDo3WH8(final androidx.compose.ui.graphics.vector.ImageVector r66, final java.lang.String r67, final java.lang.String r68, final long r69, androidx.compose.ui.Modifier r71, androidx.compose.runtime.Composer r72, final int r73, final int r74) {
        /*
            Method dump skipped, instruction units count: 671
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.m6191MiniStatCarduDo3WH8(androidx.compose.ui.graphics.vector.ImageVector, java.lang.String, java.lang.String, long, androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MiniStatCard_uDo3WH8$lambda$1(ImageVector imageVector, String str, String str2, long j10, Modifier modifier, int i10, int i11, Composer composer, int i12) {
        m6191MiniStatCarduDo3WH8(imageVector, str, str2, j10, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x03ed A[LOOP:0: B:109:0x03e7->B:111:0x03ed, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x04c6  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x05aa  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x05b9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x03a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void MobileStreamCard(final com.arflix.tv.ui.components.SourcePresentation r98, final boolean r99, final r7.a<x6.t0> r100, androidx.compose.runtime.Composer r101, final int r102) {
        /*
            Method dump skipped, instruction units count: 1489
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.MobileStreamCard(com.arflix.tv.ui.components.SourcePresentation, boolean, r7.a, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobileStreamCard$lambda$0$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 MobileStreamCard$lambda$2(SourcePresentation sourcePresentation, boolean z, r7.a aVar, int i10, Composer composer, int i11) {
        MobileStreamCard(sourcePresentation, z, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void OledBadgeFlow(SourcePresentation sourcePresentation, int i10, Modifier modifier, Composer composer, int i11, int i12) {
        int i13;
        Modifier modifier2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1721296348);
        if ((i11 & 6) == 0) {
            i13 = (composerStartRestartGroup.changed(sourcePresentation) ? 4 : 2) | i11;
        } else {
            i13 = i11;
        }
        if ((i11 & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        int i14 = i12 & 4;
        if (i14 != 0) {
            i13 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i13 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
        } else {
            modifier2 = i14 != 0 ? Modifier.INSTANCE : modifier;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1721296348, i13, -1, "com.arflix.tv.ui.components.OledBadgeFlow (StreamSelector.kt:2124)");
            }
            Arrangement arrangement = Arrangement.INSTANCE;
            float f10 = 5;
            MeasurePolicy measurePolicyRowMeasurementHelper = FlowLayoutKt.rowMeasurementHelper(arrangement.m441spacedBy0680j_4(Dp.m5678constructorimpl(f10)), androidx.compose.foundation.c.g(f10, arrangement, composerStartRestartGroup, 1098475987), Integer.MAX_VALUE, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier2);
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRowMeasurementHelper, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            FlowRowScopeInstance flowRowScopeInstance = FlowRowScopeInstance.INSTANCE;
            SourceSizeBadge(sourcePresentation.getStream().getSize(), false, false, composerStartRestartGroup, 0, 6);
            SourceLanguageBadge(sourcePresentation.getLanguageLabel(), false, false, composerStartRestartGroup, 0, 6);
            composerStartRestartGroup.startReplaceGroup(906223722);
            Iterator it = kotlin.collections.x.X0(sourceBadges(sourcePresentation), i10).iterator();
            while (it.hasNext()) {
                SourceBadgeView((SourceBadge) it.next(), false, composerStartRestartGroup, 0, 2);
            }
            composerStartRestartGroup.endReplaceGroup();
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
            scopeUpdateScopeEndRestartGroup.updateScope(new e3(sourcePresentation, i10, modifier2, i11, i12));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 OledBadgeFlow$lambda$1(SourcePresentation sourcePresentation, int i10, Modifier modifier, int i11, int i12, Composer composer, int i13) {
        OledBadgeFlow(sourcePresentation, i10, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return x6.t0.f22605a;
    }

    private static final void OledBadgeRow(final SourcePresentation sourcePresentation, final int i10, boolean z, Composer composer, final int i11, final int i12) {
        int i13;
        Composer composerStartRestartGroup = composer.startRestartGroup(-601328811);
        if ((i11 & 6) == 0) {
            i13 = (composerStartRestartGroup.changed(sourcePresentation) ? 4 : 2) | i11;
        } else {
            i13 = i11;
        }
        if ((i11 & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(i10) ? 32 : 16;
        }
        int i14 = i12 & 4;
        if (i14 != 0) {
            i13 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i11 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i13 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if ((i13 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i14 != 0) {
                z = false;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-601328811, i13, -1, "com.arflix.tv.ui.components.OledBadgeRow (StreamSelector.kt:2101)");
            }
            Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = Arrangement.INSTANCE.m441spacedBy0680j_4(Dp.m5678constructorimpl(5));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            Modifier.Companion companion = Modifier.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM441spacedBy0680j_4, centerVertically, composerStartRestartGroup, 54);
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
            composerStartRestartGroup.startReplaceGroup(1973348205);
            Iterator it = kotlin.collections.x.X0(sourceBadges(sourcePresentation), i10).iterator();
            while (it.hasNext()) {
                SourceBadgeView((SourceBadge) it.next(), z, composerStartRestartGroup, (i13 >> 3) & 112, 0);
            }
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        final boolean z5 = z;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new r7.p() { // from class: com.arflix.tv.ui.components.a3
                @Override // r7.p
                public final Object invoke(Object obj, Object obj2) {
                    int iIntValue = ((Integer) obj2).intValue();
                    return StreamSelectorKt.OledBadgeRow$lambda$1(sourcePresentation, i10, z5, i11, i12, (Composer) obj, iIntValue);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 OledBadgeRow$lambda$1(SourcePresentation sourcePresentation, int i10, boolean z, int i11, int i12, Composer composer, int i13) {
        OledBadgeRow(sourcePresentation, i10, z, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return x6.t0.f22605a;
    }

    private static final void OledSourceRow(SourcePresentation sourcePresentation, boolean z, boolean z5, r7.a<x6.t0> aVar, Composer composer, int i10) {
        int i11;
        float f10;
        Modifier modifierM191borderxT4_qwU;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1104864410);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(sourcePresentation) ? 4 : 2) | i10;
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
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1104864410, i11, -1, "com.arflix.tv.ui.components.OledSourceRow (StreamSelector.kt:1929)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            float f11 = 1;
            Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(SizeKt.m566heightInVpY3zN4$default(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), Dp.m5678constructorimpl(92), 0.0f, 2, null), Dp.m5678constructorimpl(3), Dp.m5678constructorimpl(f11));
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion2 = Composer.INSTANCE;
            if (objRememberedValue == companion2.getEmpty()) {
                objRememberedValue = new com.arflix.tv.data.api.c(19);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            float f12 = 15;
            Modifier modifierL = com.arflix.tv.data.repository.g.l(f12, com.arflix.tv.data.repository.g.n(FocusPropertiesKt.focusProperties(modifierM530paddingVpY3zN4, (r7.l) objRememberedValue), f12), z ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.11f, 0.0f, 0.0f, 0.0f, 14, null) : z5 ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.07f, 0.0f, 0.0f, 0.0f, 14, null) : Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.028f, 0.0f, 0.0f, 0.0f, 14, null));
            if (z) {
                f10 = f11;
                modifierM191borderxT4_qwU = BorderKt.m191borderxT4_qwU(companion, Dp.m5678constructorimpl((float) 1.5d), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.96f, 0.0f, 0.0f, 0.0f, 14, null), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f12)));
            } else {
                f10 = f11;
                modifierM191borderxT4_qwU = companion;
            }
            Modifier modifierThen = modifierL.then(modifierM191borderxT4_qwU);
            boolean z10 = (i11 & 7168) == 2048;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue2 == companion2.getEmpty()) {
                objRememberedValue2 = new g(18, aVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierH = androidx.fragment.app.a2.h(7, ClickableKt.m214clickableXHw0xAI$default(modifierThen, false, null, null, (r7.a) objRememberedValue2, 7, null), Dp.m5678constructorimpl(11), composerStartRestartGroup, -483455358);
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.Vertical top = arrangement.getTop();
            Alignment.Companion companion3 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion3, top, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion4.getConstructor();
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
            r7.p pVarU = a0.c.u(companion4, composerM2991constructorimpl, measurePolicyK, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            Modifier modifierFillMaxHeight$default = SizeKt.fillMaxHeight$default(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), 0.0f, 1, null);
            Alignment.Vertical centerVertically = companion3.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(arrangement, centerVertically, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierFillMaxHeight$default);
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
            r7.p pVarU2 = a0.c.u(companion4, composerM2991constructorimpl2, measurePolicyG, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            float f13 = f10;
            OledBadgeFlow(sourcePresentation, 8, androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 0.42f, false, 2, null), composerStartRestartGroup, (i11 & 14) | 48, 0);
            androidx.compose.material3.d.w(12, companion, composerStartRestartGroup, 6);
            Modifier modifierA = androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 0.58f, false, 2, null);
            composerStartRestartGroup.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK2 = androidx.compose.foundation.c.k(companion3, arrangement.getTop(), composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierA);
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
            r7.p pVarU3 = a0.c.u(companion4, composerM2991constructorimpl3, measurePolicyK2, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf3, composerStartRestartGroup, 0, 2058660585);
            Alignment.Vertical centerVertically2 = companion3.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyG2 = androidx.compose.material3.d.g(arrangement, centerVertically2, composerStartRestartGroup, 48, -1323940314);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor4 = companion4.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(companion);
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
            r7.p pVarU4 = a0.c.u(companion4, composerM2991constructorimpl4, measurePolicyG2, composerM2991constructorimpl4, currentCompositionLocalMap4);
            if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf4, composerStartRestartGroup, 0, 2058660585);
            String rawTitle = sourcePresentation.getRawTitle();
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            TextStyle body = arflixTypography.getBody();
            long sp = TextUnitKt.getSp(12);
            long sp2 = TextUnitKt.getSp(15);
            FontWeight.Companion companion5 = FontWeight.INSTANCE;
            TextKt.m6020Text4IGK_g(rawTitle, androidx.compose.foundation.layout.e.a(rowScopeInstance, companion, 1.0f, false, 2, null), ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(body, 0L, sp, z ? companion5.getBold() : companion5.getSemiBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, sp2, null, null, null, 0, 0, null, 16646137, null), composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65528);
            Composer composer2 = composerStartRestartGroup;
            if (z5) {
                composer2.startReplaceGroup(392446435);
                androidx.compose.material3.d.w(10, companion, composer2, 6);
                Modifier modifierM578size3ABfNKs = SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(22));
                Color.Companion companion6 = Color.INSTANCE;
                Modifier modifierM191borderxT4_qwU2 = BorderKt.m191borderxT4_qwU(BackgroundKt.m178backgroundbw27NRU(modifierM578size3ABfNKs, Color.m3471copywmQWz5c$default(companion6.m3509getWhite0d7_KjU(), 0.14f, 0.0f, 0.0f, 0.0f, 14, null), RoundedCornerShapeKt.getCircleShape()), Dp.m5678constructorimpl(f13), Color.m3471copywmQWz5c$default(companion6.m3509getWhite0d7_KjU(), 0.9f, 0.0f, 0.0f, 0.0f, 14, null), RoundedCornerShapeKt.getCircleShape());
                Alignment center = companion3.getCenter();
                composer2.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer2, 6);
                composer2.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap5 = composer2.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor5 = companion4.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf5 = LayoutKt.modifierMaterializerOf(modifierM191borderxT4_qwU2);
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
                r7.p pVarU5 = a0.c.u(companion4, composerM2991constructorimpl5, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl5, currentCompositionLocalMap5);
                if (composerM2991constructorimpl5.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                    a0.c.w(currentCompositeKeyHash5, composerM2991constructorimpl5, currentCompositeKeyHash5, pVarU5);
                }
                a0.c.y(composer2, qVarModifierMaterializerOf5, composer2, 0, 2058660585);
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                IconKt.m1610Iconww6aTOc(CheckKt.getCheck(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.selected, composer2, 0), SizeKt.m578size3ABfNKs(companion, Dp.m5678constructorimpl(f12)), companion6.m3509getWhite0d7_KjU(), composer2, 3456, 0);
                composer2 = composer2;
                com.arflix.tv.data.repository.g.q(composer2);
            } else {
                composer2.startReplaceGroup(393258480);
                composer2.endReplaceGroup();
            }
            androidx.compose.foundation.c.y(composer2);
            com.arflix.tv.data.repository.g.p(5, companion, composer2, 6);
            Composer composer3 = composer2;
            TextKt.m6020Text4IGK_g(rowSubtitle(sourcePresentation), null, OledMutedText, 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 2, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(10), FontWeight.INSTANCE.getMedium(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer3, RendererCapabilities.DECODER_SUPPORT_MASK, 3120, 55290);
            composerStartRestartGroup = composer3;
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
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
            scopeUpdateScopeEndRestartGroup.updateScope(new u2(sourcePresentation, z, z5, aVar, i10, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 OledSourceRow$lambda$0$0(FocusProperties focusProperties) {
        focusProperties.setCanFocus(false);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 OledSourceRow$lambda$1$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 OledSourceRow$lambda$3(SourcePresentation sourcePresentation, boolean z, boolean z5, r7.a aVar, int i10, Composer composer, int i11) {
        OledSourceRow(sourcePresentation, z, z5, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:189:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x036c  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x03db  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0401  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x044d  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0459  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x046e  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0481  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x04bf  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x04cb  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x04cf  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0536  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0542  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0546  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x056a  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x062d  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0639  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x063d  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x064e  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0663  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0674 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:327:0x067d  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x0758  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x0868  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x08e5  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x08f2  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x091b  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0a35  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x0a4d  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0a92  */
    /* JADX WARN: Removed duplicated region for block: B:461:0x0df0  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x0dfb  */
    /* JADX WARN: Removed duplicated region for block: B:471:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void OledSourceSelectorTv(final java.lang.String r88, final java.lang.String r89, final java.util.List<com.arflix.tv.data.model.StreamSource> r90, final java.util.List<com.arflix.tv.ui.components.SourcePresentation> r91, final com.arflix.tv.data.model.StreamSource r92, final java.util.List<java.lang.String> r93, final int r94, final int r95, final boolean r96, final java.util.List<java.lang.String> r97, final java.util.List<java.lang.String> r98, final int r99, final int r100, final boolean r101, final androidx.tv.foundation.lazy.list.m0 r102, final androidx.tv.foundation.lazy.list.m0 r103, final int r104, final boolean r105, final int r106, final int r107, final boolean r108, final boolean r109, final int r110, final int r111, int r112, final boolean r113, final java.util.Set<java.lang.String> r114, r7.l<? super java.lang.Integer, x6.t0> r115, final r7.l<? super java.lang.Integer, x6.t0> r116, final r7.l<? super com.arflix.tv.data.model.StreamSource, x6.t0> r117, androidx.compose.runtime.Composer r118, final int r119, final int r120, final int r121, final int r122) {
        /*
            Method dump skipped, instruction units count: 3655
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.OledSourceSelectorTv(java.lang.String, java.lang.String, java.util.List, java.util.List, com.arflix.tv.data.model.StreamSource, java.util.List, int, int, boolean, java.util.List, java.util.List, int, int, boolean, androidx.tv.foundation.lazy.list.m0, androidx.tv.foundation.lazy.list.m0, int, boolean, int, int, boolean, boolean, int, int, int, boolean, java.util.Set, r7.l, r7.l, r7.l, androidx.compose.runtime.Composer, int, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 OledSourceSelectorTv$lambda$0$0$0$1$0$0$0(r7.l lVar, int i10) {
        lVar.invoke(Integer.valueOf(i10));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 OledSourceSelectorTv$lambda$0$0$0$2$0$0(List list, boolean z, int i10, final StreamSource streamSource, final r7.l lVar, androidx.tv.foundation.lazy.list.g0 g0Var) {
        final int i11 = 0;
        for (Object obj : list) {
            int i12 = i11 + 1;
            if (i11 < 0) {
                t7.a.Q();
                throw null;
            }
            final SourcePresentation sourcePresentation = (SourcePresentation) obj;
            final boolean z5 = z;
            final int i13 = i10;
            androidx.fragment.app.a2.d(g0Var, sourceStreamRowKey(sourcePresentation.getStream(), i11), ComposableLambdaKt.composableLambdaInstance(1032724367, true, new r7.q() { // from class: com.arflix.tv.ui.components.w2
                @Override // r7.q
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    int iIntValue = ((Integer) obj4).intValue();
                    return StreamSelectorKt.OledSourceSelectorTv$lambda$0$0$0$2$0$0$0$0(sourcePresentation, z5, i11, i13, streamSource, lVar, (androidx.tv.foundation.lazy.list.c0) obj2, (Composer) obj3, iIntValue);
                }
            }), 2);
            z = z5;
            i10 = i13;
            i11 = i12;
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 OledSourceSelectorTv$lambda$0$0$0$2$0$0$0$0(SourcePresentation sourcePresentation, boolean z, int i10, int i11, StreamSource streamSource, r7.l lVar, androidx.tv.foundation.lazy.list.c0 c0Var, Composer composer, int i12) {
        if ((i12 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1032724367, i12, -1, "com.arflix.tv.ui.components.OledSourceSelectorTv.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (StreamSelector.kt:887)");
            }
            boolean z5 = z && i10 == i11;
            boolean zIsSelectedSource = isSelectedSource(sourcePresentation.getStream(), streamSource);
            boolean zChanged = composer.changed(lVar) | composer.changed(sourcePresentation);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new j3(lVar, sourcePresentation, 0);
                composer.updateRememberedValue(objRememberedValue);
            }
            OledSourceRow(sourcePresentation, z5, zIsSelectedSource, (r7.a) objRememberedValue, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 OledSourceSelectorTv$lambda$0$0$0$2$0$0$0$0$0$0(r7.l lVar, SourcePresentation sourcePresentation) {
        lVar.invoke(sourcePresentation.getStream());
        return x6.t0.f22605a;
    }

    private static final boolean OledSourceSelectorTv$lambda$0$0$0$2$2(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean OledSourceSelectorTv$lambda$0$0$0$2$4(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 OledSourceSelectorTv$lambda$1(String str, String str2, List list, List list2, StreamSource streamSource, List list3, int i10, int i11, boolean z, List list4, List list5, int i12, int i13, boolean z5, androidx.tv.foundation.lazy.list.m0 m0Var, androidx.tv.foundation.lazy.list.m0 m0Var2, int i14, boolean z10, int i15, int i16, boolean z11, boolean z12, int i17, int i18, int i19, boolean z13, Set set, r7.l lVar, r7.l lVar2, r7.l lVar3, int i20, int i21, int i22, int i23, Composer composer, int i24) {
        OledSourceSelectorTv(str, str2, list, list2, streamSource, list3, i10, i11, z, list4, list5, i12, i13, z5, m0Var, m0Var2, i14, z10, i15, i16, z11, z12, i17, i18, i19, z13, set, lVar, lVar2, lVar3, composer, RecomposeScopeImplKt.updateChangedFlags(i20 | 1), RecomposeScopeImplKt.updateChangedFlags(i21), RecomposeScopeImplKt.updateChangedFlags(i22), i23);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:63:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void OledTextBadge(java.lang.String r59, boolean r60, androidx.compose.runtime.Composer r61, int r62, int r63) {
        /*
            Method dump skipped, instruction units count: 399
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.OledTextBadge(java.lang.String, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 OledTextBadge$lambda$1(String str, boolean z, int i10, int i11, Composer composer, int i12) {
        OledTextBadge(str, z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    private static final void PremiumQualityPill(SourcePresentation sourcePresentation, Composer composer, int i10) {
        int i11;
        Composer composer2;
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(1306667432);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(sourcePresentation) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
            i12 = 0;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1306667432, i11, -1, "com.arflix.tv.ui.components.PremiumQualityPill (StreamSelector.kt:1533)");
            }
            float f10 = 8;
            Modifier modifierH = androidx.fragment.app.a2.h(5, com.arflix.tv.data.repository.g.k(f10, com.arflix.tv.data.repository.g.j(f10, Modifier.INSTANCE, Color.m3471copywmQWz5c$default(sourcePresentation.m6190getQualityColor0d7_KjU(), 0.16f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(1), Color.m3471copywmQWz5c$default(sourcePresentation.m6190getQualityColor0d7_KjU(), 0.28f, 0.0f, 0.0f, 0.0f, 14, null)), Dp.m5678constructorimpl(10), composerStartRestartGroup, 733328855);
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
            i12 = 0;
            TextKt.m6020Text4IGK_g(sourcePresentation.getResolutionLabel(), null, sourcePresentation.m6190getQualityColor0d7_KjU(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(10), FontWeight.INSTANCE.getBlack(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer2, 0, 0, 65530);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new c3(sourcePresentation, i10, i12));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PremiumQualityPill$lambda$1(SourcePresentation sourcePresentation, int i10, Composer composer, int i11) {
        PremiumQualityPill(sourcePresentation, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    private static final void RailMetric(String str, String str2, Composer composer, int i10) {
        int i11;
        Composer composer2;
        String str3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1203453710);
        if ((i10 & 6) == 0) {
            i11 = i10 | (composerStartRestartGroup.changed(str) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(str2) ? 32 : 16;
        }
        if ((i11 & 19) == 18 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            str3 = str2;
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1203453710, i11, -1, "com.arflix.tv.ui.components.RailMetric (StreamSelector.kt:1847)");
            }
            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.HorizontalOrVertical spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion.getConstructor();
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
            r7.p pVarU = a0.c.u(companion, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str, null, OledMutedText, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(11), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composer2, (i11 & 14) | RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            str3 = str2;
            TextKt.m6020Text4IGK_g(str3, null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(12), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer2, ((i11 >> 3) & 14) | RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new c1(str, str3, i10, 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 RailMetric$lambda$1(String str, String str2, int i10, Composer composer, int i11) {
        RailMetric(str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:134:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0309  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0313  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x03af  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x045d  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x04ea  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0524  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0530  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x0534  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x055c  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0585  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x05b0  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x05c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void SourceAddonRail(final java.util.List<java.lang.String> r76, final java.util.List<java.lang.String> r77, final int r78, final int r79, final boolean r80, androidx.tv.foundation.lazy.list.m0 r81, final int r82, final int r83, final int r84, int r85, int r86, final r7.l<? super java.lang.Integer, x6.t0> r87, androidx.compose.runtime.Composer r88, final int r89, final int r90) {
        /*
            Method dump skipped, instruction units count: 1516
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.SourceAddonRail(java.util.List, java.util.List, int, int, boolean, androidx.tv.foundation.lazy.list.m0, int, int, int, int, int, r7.l, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceAddonRail$lambda$0$0$0$0(List list, List list2, int i10, boolean z, final int i11, final r7.l lVar, androidx.tv.foundation.lazy.list.g0 g0Var) {
        final int i12 = 0;
        for (Object obj : list) {
            int i13 = i12 + 1;
            if (i12 < 0) {
                t7.a.Q();
                throw null;
            }
            final String str = (String) obj;
            final int i14 = i10;
            final boolean z5 = z;
            androidx.fragment.app.a2.d(g0Var, (i12 < 0 || i12 >= list2.size()) ? a0.c.i(i12, "source_addon:fallback:") : list2.get(i12), ComposableLambdaKt.composableLambdaInstance(2134639180, true, new r7.q() { // from class: com.arflix.tv.ui.components.i3
                @Override // r7.q
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    int iIntValue = ((Integer) obj4).intValue();
                    return StreamSelectorKt.SourceAddonRail$lambda$0$0$0$0$0$1(str, i12, i14, z5, i11, lVar, (androidx.tv.foundation.lazy.list.c0) obj2, (Composer) obj3, iIntValue);
                }
            }), 2);
            i10 = i14;
            z = z5;
            i12 = i13;
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceAddonRail$lambda$0$0$0$0$0$1(String str, int i10, int i11, boolean z, int i12, r7.l lVar, androidx.tv.foundation.lazy.list.c0 c0Var, Composer composer, int i13) {
        int i14;
        boolean z5;
        if ((i13 & 17) == 16 && composer.getSkipping()) {
            composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2134639180, i13, -1, "com.arflix.tv.ui.components.SourceAddonRail.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (StreamSelector.kt:1732)");
            }
            boolean z10 = false;
            if (i10 == i11) {
                i14 = i10;
                z5 = true;
            } else {
                i14 = i10;
                z5 = false;
            }
            if (z && i14 == i12) {
                z10 = true;
            }
            boolean zChanged = composer.changed(lVar) | composer.changed(i14);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new m2(i14, 1, lVar);
                composer.updateRememberedValue(objRememberedValue);
            }
            AddonRailItem(str, z5, z10, (r7.a) objRememberedValue, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceAddonRail$lambda$0$0$0$0$0$1$0$0(r7.l lVar, int i10) {
        lVar.invoke(Integer.valueOf(i10));
        return x6.t0.f22605a;
    }

    private static final boolean SourceAddonRail$lambda$0$0$2(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean SourceAddonRail$lambda$0$0$4(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceAddonRail$lambda$1(List list, List list2, int i10, int i11, boolean z, androidx.tv.foundation.lazy.list.m0 m0Var, int i12, int i13, int i14, int i15, int i16, r7.l lVar, int i17, int i18, Composer composer, int i19) {
        SourceAddonRail(list, list2, i10, i11, z, m0Var, i12, i13, i14, i15, i16, lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i17 | 1), RecomposeScopeImplKt.updateChangedFlags(i18));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void SourceBadgeTray(final com.arflix.tv.ui.components.SourcePresentation r14, final int r15, boolean r16, boolean r17, androidx.compose.runtime.Composer r18, final int r19, final int r20) {
        /*
            Method dump skipped, instruction units count: 336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.SourceBadgeTray(com.arflix.tv.ui.components.SourcePresentation, int, boolean, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceBadgeTray$lambda$1(SourcePresentation sourcePresentation, int i10, boolean z, boolean z5, int i11, int i12, Composer composer, int i13) {
        SourceBadgeTray(sourcePresentation, i10, z, z5, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void SourceBadgeView(com.arflix.tv.ui.components.SourceBadge r16, boolean r17, androidx.compose.runtime.Composer r18, int r19, int r20) {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.SourceBadgeView(com.arflix.tv.ui.components.SourceBadge, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceBadgeView$lambda$0(SourceBadge sourceBadge, boolean z, int i10, int i11, Composer composer, int i12) {
        SourceBadgeView(sourceBadge, z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01f7  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0377  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x045a  */
    /* JADX WARN: Removed duplicated region for block: B:165:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0155  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void SourceEmptyState(final boolean r70, final int r71, final int r72, final boolean r73, int r74, boolean r75, java.util.Set<java.lang.String> r76, java.lang.String r77, androidx.compose.runtime.Composer r78, final int r79, final int r80) {
        /*
            Method dump skipped, instruction units count: 1127
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.SourceEmptyState(boolean, int, int, boolean, int, boolean, java.util.Set, java.lang.String, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceEmptyState$lambda$1(boolean z, int i10, int i11, boolean z5, int i12, boolean z10, Set set, String str, int i13, int i14, Composer composer, int i15) {
        SourceEmptyState(z, i10, i11, z5, i12, z10, set, str, composer, RecomposeScopeImplKt.updateChangedFlags(i13 | 1), i14);
        return x6.t0.f22605a;
    }

    private static final void SourceFilterChip(String str, boolean z, boolean z5, r7.a<x6.t0> aVar, Composer composer, int i10) {
        String str2;
        int i11;
        long jM3471copywmQWz5c$default;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(665228810);
        if ((i10 & 6) == 0) {
            str2 = str;
            i11 = (composerStartRestartGroup.changed(str2) ? 4 : 2) | i10;
        } else {
            str2 = str;
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
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(665228810, i11, -1, "com.arflix.tv.ui.components.SourceFilterChip (StreamSelector.kt:1657)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            float f10 = MediaError.DetailedErrorCode.GENERIC;
            Modifier modifierK = com.arflix.tv.data.repository.g.k(f10, BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.i(f10, companion), z5 ? Color.INSTANCE.m3509getWhite0d7_KjU() : z ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.12f, 0.0f, 0.0f, 0.0f, 14, null) : OledPanel, null, 2, null), Dp.m5678constructorimpl(1), z5 ? Color.INSTANCE.m3509getWhite0d7_KjU() : z ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.28f, 0.0f, 0.0f, 0.0f, 14, null) : OledMutedBorder);
            boolean z10 = (i11 & 7168) == 2048;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new g(17, aVar);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierH = androidx.fragment.app.a2.h(6, ClickableKt.m214clickableXHw0xAI$default(modifierK, false, null, null, (r7.a) objRememberedValue, 7, null), Dp.m5678constructorimpl(14), composerStartRestartGroup, 733328855);
            MeasurePolicy measurePolicyD = a0.c.d(Alignment.INSTANCE, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
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
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyD, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            TextStyle textStyleM5205copyp1EtxEg$default = TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getCaption(), 0L, TextUnitKt.getSp(11), (z || z5) ? FontWeight.INSTANCE.getBold() : FontWeight.INSTANCE.getSemiBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null);
            if (z5) {
                jM3471copywmQWz5c$default = Color.INSTANCE.m3498getBlack0d7_KjU();
            } else {
                jM3471copywmQWz5c$default = Color.m3471copywmQWz5c$default(ColorKt.getTextPrimary(), z ? 0.96f : 0.82f, 0.0f, 0.0f, 0.0f, 14, null);
            }
            composer2 = composerStartRestartGroup;
            TextKt.m6020Text4IGK_g(str2, null, jM3471copywmQWz5c$default, 0L, null, null, null, 0L, null, null, 0L, 0, false, 1, 0, null, textStyleM5205copyp1EtxEg$default, composer2, i11 & 14, 3072, 57338);
            if (androidx.compose.foundation.c.D(composer2)) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new t2(str, z, z5, aVar, i10, 2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceFilterChip$lambda$0$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceFilterChip$lambda$2(String str, boolean z, boolean z5, r7.a aVar, int i10, Composer composer, int i11) {
        SourceFilterChip(str, z, z5, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:97:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void SourceLanguageBadge(java.lang.String r60, boolean r61, boolean r62, androidx.compose.runtime.Composer r63, int r64, int r65) {
        /*
            Method dump skipped, instruction units count: 499
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.SourceLanguageBadge(java.lang.String, boolean, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceLanguageBadge$lambda$0(String str, boolean z, boolean z5, int i10, int i11, Composer composer, int i12) {
        SourceLanguageBadge(str, z, z5, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceLanguageBadge$lambda$2(String str, boolean z, boolean z5, int i10, int i11, Composer composer, int i12) {
        SourceLanguageBadge(str, z, z5, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0188  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void SourceMetadataChips(com.arflix.tv.ui.components.SourcePresentation r47, boolean r48, androidx.compose.runtime.Composer r49, int r50) {
        /*
            Method dump skipped, instruction units count: 834
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.SourceMetadataChips(com.arflix.tv.ui.components.SourcePresentation, boolean, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceMetadataChips$lambda$1(SourcePresentation sourcePresentation, boolean z, int i10, Composer composer, int i11) {
        SourceMetadataChips(sourcePresentation, z, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:97:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void SourceSizeBadge(java.lang.String r59, boolean r60, boolean r61, androidx.compose.runtime.Composer r62, int r63, int r64) {
        /*
            Method dump skipped, instruction units count: 495
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.SourceSizeBadge(java.lang.String, boolean, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceSizeBadge$lambda$0(String str, boolean z, boolean z5, int i10, int i11, Composer composer, int i12) {
        SourceSizeBadge(str, z, z5, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 SourceSizeBadge$lambda$2(String str, boolean z, boolean z5, int i10, int i11, Composer composer, int i12) {
        SourceSizeBadge(str, z, z5, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x023c  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0267  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x026d  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x02c8  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x02dd  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x02ef  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0369  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x03fd  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0425  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0445  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0479  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0492  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x049f  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x04a2  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x04b1  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x04e3  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0500 A[LOOP:12: B:295:0x04fa->B:297:0x0500, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0529  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0536  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0539  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x054c  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0563 A[LOOP:11: B:315:0x055d->B:317:0x0563, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:321:0x057e  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0581  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x0589  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x0591  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x066d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:517:0x0abe  */
    /* JADX WARN: Removed duplicated region for block: B:553:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0111  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void StreamSelector(final boolean r50, final java.util.List<com.arflix.tv.data.model.StreamSource> r51, final com.arflix.tv.data.model.StreamSource r52, boolean r53, java.lang.String r54, java.lang.String r55, boolean r56, java.util.List<java.lang.String> r57, int r58, int r59, long r60, boolean r62, java.util.Set<java.lang.String> r63, r7.l<? super com.arflix.tv.data.model.StreamSource, x6.t0> r64, r7.l<? super com.arflix.tv.data.model.StreamSource, x6.t0> r65, r7.a<x6.t0> r66, androidx.compose.runtime.Composer r67, final int r68, final int r69, final int r70) {
        /*
            Method dump skipped, instruction units count: 2776
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.StreamSelector(boolean, java.util.List, com.arflix.tv.data.model.StreamSource, boolean, java.lang.String, java.lang.String, boolean, java.util.List, int, int, long, boolean, java.util.Set, r7.l, r7.l, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 StreamSelector$lambda$0$0(StreamSource streamSource) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 StreamSelector$lambda$1$0(StreamSource streamSource) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String StreamSelector$lambda$19(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int StreamSelector$lambda$33$5(Comparator comparator, kotlin.collections.d0 d0Var, kotlin.collections.d0 d0Var2) {
        return (keepsOwnStreamOrder(((SourcePresentation) d0Var.f19689b).getStream()) && keepsOwnStreamOrder(((SourcePresentation) d0Var2.f19689b).getStream())) ? kotlin.jvm.internal.p.c(d0Var.f19688a, d0Var2.f19688a) : comparator.compare(d0Var, d0Var2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int StreamSelector$lambda$33$6(r7.p pVar, Object obj, Object obj2) {
        return ((Number) pVar.invoke(obj, obj2)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int StreamSelector$lambda$42$0(int i10) {
        return i10 / 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int StreamSelector$lambda$43$0(int i10) {
        return i10 / 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 StreamSelector$lambda$44(boolean z, ArvioBackMotion arvioBackMotion, FocusRequester focusRequester, final boolean z5, final r7.a aVar, final List list, final List list2, final r7.l lVar, final MutableState mutableState, final MutableIntState mutableIntState, final MutableIntState mutableIntState2, final MutableIntState mutableIntState3, String str, String str2, List list3, List list4, StreamSource streamSource, List list5, List list6, androidx.tv.foundation.lazy.list.m0 m0Var, androidx.tv.foundation.lazy.list.m0 m0Var2, int i10, int i11, boolean z10, boolean z11, int i12, int i13, boolean z12, Set set, final MutableIntState mutableIntState4, final MutableIntState mutableIntState5, MutableIntState mutableIntState6, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i14) {
        Modifier modifier;
        Alignment.Companion companion;
        Modifier.Companion companion2;
        int i15;
        final MutableIntState mutableIntState7;
        final MutableIntState mutableIntState8;
        boolean z13;
        String strStringResource;
        float f10;
        int i16;
        Integer num;
        float f11;
        FocusRequester focusRequester2;
        int i17;
        String strStringResource2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1086946828, i14, -1, "com.arflix.tv.ui.components.StreamSelector.<anonymous> (StreamSelector.kt:390)");
        }
        Modifier.Companion companion3 = Modifier.INSTANCE;
        Modifier modifierM179backgroundbw27NRU$default = BackgroundKt.m179backgroundbw27NRU$default(SizeKt.fillMaxSize$default(companion3, 0.0f, 1, null), z ? Color.m3471copywmQWz5c$default(Color.INSTANCE.m3498getBlack0d7_KjU(), qb.d.m((1.0f - (arvioBackMotion.getEased() * 0.5f)) * 0.95f, 0.0f, 0.95f), 0.0f, 0.0f, 0.0f, 14, null) : Color.INSTANCE.m3498getBlack0d7_KjU(), null, 2, null);
        composer.startReplaceableGroup(733328855);
        Alignment.Companion companion4 = Alignment.INSTANCE;
        MeasurePolicy measurePolicyD = a0.c.d(companion4, false, composer, 0, -1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion5.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierM179backgroundbw27NRU$default);
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
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
        Modifier modifierArvioBackModal = ArvioPredictiveBackKt.arvioBackModal(FocusableKt.focusable$default(FocusRequesterModifierKt.focusRequester(SizeKt.fillMaxSize$default(companion3, 0.0f, 1, null), focusRequester), false, null, 3, null), arvioBackMotion);
        boolean zChanged = composer.changed(z5) | composer.changed(aVar) | composer.changed(list) | composer.changed(list2) | composer.changed(lVar);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            modifier = modifierArvioBackModal;
            companion = companion4;
            companion2 = companion3;
            i15 = 733328855;
            r7.l<KeyEvent, Boolean> lVar2 = new r7.l<KeyEvent, Boolean>() { // from class: com.arflix.tv.ui.components.StreamSelectorKt$StreamSelector$11$1$1$1
                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return m6192invokeZmokQxo(((KeyEvent) obj).m4390unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m6192invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    boolean z14 = false;
                    z14 = false;
                    if (KeyEventType.m4394equalsimpl0(KeyEvent_androidKt.m4402getTypeZmokQxo(keyEvent), KeyEventType.INSTANCE.m4398getKeyDownCS__XNY())) {
                        boolean z15 = z5;
                        long jM4401getKeyZmokQxo = KeyEvent_androidKt.m4401getKeyZmokQxo(keyEvent);
                        if (z15) {
                            Key.Companion companion6 = Key.INSTANCE;
                            if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion6.m4169getDirectionLeftEK5gGoQ())) {
                                jM4401getKeyZmokQxo = companion6.m4170getDirectionRightEK5gGoQ();
                            } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion6.m4170getDirectionRightEK5gGoQ())) {
                                jM4401getKeyZmokQxo = companion6.m4169getDirectionLeftEK5gGoQ();
                            }
                        }
                        Key.Companion companion7 = Key.INSTANCE;
                        if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion7.m4182getEscapeEK5gGoQ())) {
                            aVar.invoke();
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion7.m4171getDirectionUpEK5gGoQ())) {
                            if (kotlin.jvm.internal.p.a(StreamSelectorKt.StreamSelector$lambda$19(mutableState), "addons")) {
                                if (mutableIntState.getIntValue() > 0) {
                                    mutableIntState.setIntValue(mutableIntState.getIntValue() - 1);
                                    mutableIntState2.setIntValue(mutableIntState.getIntValue());
                                    mutableIntState3.setIntValue(0);
                                }
                            } else if (mutableIntState3.getIntValue() > 0) {
                                mutableIntState3.setIntValue(mutableIntState3.getIntValue() - 1);
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion7.m4166getDirectionDownEK5gGoQ())) {
                            if (kotlin.jvm.internal.p.a(StreamSelectorKt.StreamSelector$lambda$19(mutableState), "addons")) {
                                if (mutableIntState.getIntValue() < list.size() - 1) {
                                    mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
                                    mutableIntState2.setIntValue(mutableIntState.getIntValue());
                                    mutableIntState3.setIntValue(0);
                                }
                            } else if (mutableIntState3.getIntValue() < list2.size() - 1) {
                                mutableIntState3.setIntValue(mutableIntState3.getIntValue() + 1);
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion7.m4169getDirectionLeftEK5gGoQ())) {
                            if (kotlin.jvm.internal.p.a(StreamSelectorKt.StreamSelector$lambda$19(mutableState), "addons")) {
                                mutableState.setValue("streams");
                                MutableIntState mutableIntState9 = mutableIntState3;
                                int intValue = mutableIntState9.getIntValue();
                                int size = list2.size() - 1;
                                int i18 = size >= 0 ? size : 0;
                                if (intValue > i18) {
                                    intValue = i18;
                                }
                                mutableIntState9.setIntValue(intValue);
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion7.m4170getDirectionRightEK5gGoQ())) {
                            if (kotlin.jvm.internal.p.a(StreamSelectorKt.StreamSelector$lambda$19(mutableState), "streams") && list.size() > 1) {
                                mutableState.setValue("addons");
                                mutableIntState.setIntValue(mutableIntState2.getIntValue());
                            }
                        } else if (Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion7.m4179getEnterEK5gGoQ()) || Key.m4093equalsimpl0(jM4401getKeyZmokQxo, companion7.m4165getDirectionCenterEK5gGoQ())) {
                            if (kotlin.jvm.internal.p.a(StreamSelectorKt.StreamSelector$lambda$19(mutableState), "addons")) {
                                mutableState.setValue("streams");
                                mutableIntState3.setIntValue(0);
                            } else {
                                StreamSource streamSource2 = (StreamSource) kotlin.collections.x.p0(mutableIntState3.getIntValue(), list2);
                                if (streamSource2 != null) {
                                    lVar.invoke(streamSource2);
                                }
                            }
                        }
                        z14 = true;
                    }
                    return Boolean.valueOf(z14);
                }
            };
            mutableIntState7 = mutableIntState2;
            mutableIntState8 = mutableIntState3;
            composer.updateRememberedValue(lVar2);
            objRememberedValue = lVar2;
        } else {
            mutableIntState8 = mutableIntState3;
            modifier = modifierArvioBackModal;
            companion = companion4;
            companion2 = companion3;
            i15 = 733328855;
            mutableIntState7 = mutableIntState2;
        }
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(modifier, (r7.l) objRememberedValue);
        composer.startReplaceableGroup(i15);
        MeasurePolicy measurePolicyD2 = a0.c.d(companion, false, composer, 0, -1323940314);
        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor2 = companion5.getConstructor();
        r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierOnKeyEvent);
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
        r7.p pVarU2 = a0.c.u(companion5, composerM2991constructorimpl2, measurePolicyD2, composerM2991constructorimpl2, currentCompositionLocalMap2);
        if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
            a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
        }
        a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
        if (z) {
            composer.startReplaceGroup(1204673979);
            LazyListState lazyListStateRememberLazyListState = LazyListStateKt.rememberLazyListState(0, 0, composer, 0, 3);
            Modifier.Companion companion6 = companion2;
            float f12 = 16;
            Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(SizeKt.fillMaxSize$default(companion6, 0.0f, 1, null), Dp.m5678constructorimpl(f12), 0.0f, 2, null);
            composer.startReplaceableGroup(-483455358);
            Arrangement arrangement = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyK = androidx.compose.foundation.c.k(companion, arrangement.getTop(), composer, 0, -1323940314);
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor3 = companion5.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
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
            r7.p pVarU3 = a0.c.u(companion5, composerM2991constructorimpl3, measurePolicyK, composerM2991constructorimpl3, currentCompositionLocalMap3);
            if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
            }
            a0.c.y(composer, qVarModifierMaterializerOf3, composer, 0, 2058660585);
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            float f13 = 12;
            float f14 = 8;
            Modifier modifierM533paddingqDBjuR0$default = PaddingKt.m533paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(companion6, 0.0f, 1, null), 0.0f, Dp.m5678constructorimpl(f13), 0.0f, Dp.m5678constructorimpl(f14), 5, null);
            Alignment.Vertical centerVertically = companion.getCenterVertically();
            composer.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyG = androidx.compose.material3.d.g(arrangement, centerVertically, composer, 48, -1323940314);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor4 = companion5.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierM533paddingqDBjuR0$default);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor4);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl4 = Updater.m2991constructorimpl(composer);
            r7.p pVarU4 = a0.c.u(companion5, composerM2991constructorimpl4, measurePolicyG, composerM2991constructorimpl4, currentCompositionLocalMap4);
            if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU4);
            }
            a0.c.y(composer, qVarModifierMaterializerOf4, composer, 0, 2058660585);
            Modifier modifierA = androidx.compose.foundation.layout.e.a(RowScopeInstance.INSTANCE, companion6, 1.0f, false, 2, null);
            composer.startReplaceableGroup(-483455358);
            MeasurePolicy measurePolicyK2 = androidx.compose.foundation.c.k(companion, arrangement.getTop(), composer, 0, -1323940314);
            int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap5 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor5 = companion5.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf5 = LayoutKt.modifierMaterializerOf(modifierA);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor5);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl5 = Updater.m2991constructorimpl(composer);
            r7.p pVarU5 = a0.c.u(companion5, composerM2991constructorimpl5, measurePolicyK2, composerM2991constructorimpl5, currentCompositionLocalMap5);
            if (composerM2991constructorimpl5.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                a0.c.w(currentCompositeKeyHash5, composerM2991constructorimpl5, currentCompositeKeyHash5, pVarU5);
            }
            a0.c.y(composer, qVarModifierMaterializerOf5, composer, 0, 2058660585);
            composer.startReplaceGroup(504508243);
            if (str.length() == 0) {
                z13 = false;
                strStringResource = StringResources_androidKt.stringResource(R.string.stream_title_select_source, composer, 0);
            } else {
                z13 = false;
                strStringResource = str;
            }
            composer.endReplaceGroup();
            ArflixTypography arflixTypography = ArflixTypography.INSTANCE;
            Alignment.Companion companion7 = companion;
            float f15 = f12;
            TextKt.m6020Text4IGK_g(strStringResource, null, ColorKt.getTextPrimary(), 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m5632getEllipsisgIe3tQ8(), false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getBody(), 0L, TextUnitKt.getSp(18), FontWeight.INSTANCE.getBold(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 3120, 55290);
            TextKt.m6020Text4IGK_g(list3.size() + " " + StringResources_androidKt.stringResource(R.string.sources_available, composer, 0), null, ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography.getCaption(), 0L, TextUnitKt.getSp(12), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
            Composer composer2 = composer;
            androidx.compose.foundation.c.y(composer2);
            Modifier modifierM179backgroundbw27NRU$default2 = BackgroundKt.m179backgroundbw27NRU$default(ClipKt.clip(SizeKt.m578size3ABfNKs(companion6, Dp.m5678constructorimpl((float) 36)), RoundedCornerShapeKt.getCircleShape()), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null);
            boolean zChanged2 = composer2.changed(aVar);
            Object objRememberedValue2 = composer2.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new g(19, aVar);
                composer2.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierM214clickableXHw0xAI$default = ClickableKt.m214clickableXHw0xAI$default(modifierM179backgroundbw27NRU$default2, false, null, null, (r7.a) objRememberedValue2, 7, null);
            Alignment center = companion7.getCenter();
            composer2.startReplaceableGroup(733328855);
            int i18 = 6;
            int i19 = 0;
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer2, 6);
            composer2.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap6 = composer2.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor6 = companion5.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf6 = LayoutKt.modifierMaterializerOf(modifierM214clickableXHw0xAI$default);
            if (!com.arflix.tv.data.repository.g.s(composer2.getApplier())) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor6);
            } else {
                composer2.useNode();
            }
            Composer composerM2991constructorimpl6 = Updater.m2991constructorimpl(composer2);
            r7.p pVarU6 = a0.c.u(companion5, composerM2991constructorimpl6, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl6, currentCompositionLocalMap6);
            if (composerM2991constructorimpl6.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                a0.c.w(currentCompositeKeyHash6, composerM2991constructorimpl6, currentCompositeKeyHash6, pVarU6);
            }
            Integer num2 = 0;
            a0.c.y(composer2, qVarModifierMaterializerOf6, composer2, null, 2058660585);
            IconKt.m1610Iconww6aTOc(CloseKt.getClose(Icons.INSTANCE.getDefault()), StringResources_androidKt.stringResource(R.string.close, composer2, 0), SizeKt.m578size3ABfNKs(companion6, Dp.m5678constructorimpl(20)), ColorKt.getTextPrimary(), composer2, 3456, 0);
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            int i20 = 14;
            int i21 = 1;
            if (list.size() > 1) {
                composer2.startReplaceGroup(1029463391);
                Modifier modifierM531paddingVpY3zN4$default2 = PaddingKt.m531paddingVpY3zN4$default(ScrollKt.horizontalScroll$default(SizeKt.fillMaxWidth$default(companion6, 0.0f, 1, null), ScrollKt.rememberScrollState(0, composer2, 0, 1), false, null, false, 14, null), 0.0f, Dp.m5678constructorimpl(f14), 1, null);
                float f16 = f14;
                MeasurePolicy measurePolicyC = androidx.compose.foundation.c.C(companion7, androidx.compose.foundation.c.g(f16, arrangement, composer2, 693286680), composer2, 6, -1323940314);
                int currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap7 = composer2.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor7 = companion5.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf7 = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default2);
                if (!com.arflix.tv.data.repository.g.s(composer2.getApplier())) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor7);
                } else {
                    composer2.useNode();
                }
                Composer composerM2991constructorimpl7 = Updater.m2991constructorimpl(composer2);
                r7.p pVarU7 = a0.c.u(companion5, composerM2991constructorimpl7, measurePolicyC, composerM2991constructorimpl7, currentCompositionLocalMap7);
                if (composerM2991constructorimpl7.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl7.rememberedValue(), Integer.valueOf(currentCompositeKeyHash7))) {
                    a0.c.w(currentCompositeKeyHash7, composerM2991constructorimpl7, currentCompositeKeyHash7, pVarU7);
                }
                a0.c.y(composer2, qVarModifierMaterializerOf7, composer2, null, 2058660585);
                composer2.startReplaceGroup(414688192);
                final int i22 = 0;
                for (Object obj : list) {
                    int i23 = i22 + 1;
                    if (i22 < 0) {
                        t7.a.Q();
                        throw null;
                    }
                    String str3 = (String) obj;
                    int i24 = i22 == mutableIntState2.getIntValue() ? i21 : i19;
                    Modifier.Companion companion8 = Modifier.INSTANCE;
                    float f17 = f15;
                    Modifier modifierThen = BackgroundKt.m179backgroundbw27NRU$default(com.arflix.tv.data.repository.g.i(f17, companion8), i24 != 0 ? Color.m3471copywmQWz5c$default(ColorKt.getPink(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null) : Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.08f, 0.0f, 0.0f, 0.0f, 14, null), null, 2, null).then(i24 != 0 ? BorderKt.m191borderxT4_qwU(companion8, Dp.m5678constructorimpl(i21), Color.m3471copywmQWz5c$default(ColorKt.getPink(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f17))) : BorderKt.m191borderxT4_qwU(companion8, Dp.m5678constructorimpl(i21), Color.m3471copywmQWz5c$default(Color.INSTANCE.m3509getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null), RoundedCornerShapeKt.m798RoundedCornerShape0680j_4(Dp.m5678constructorimpl(f17))));
                    boolean zChanged3 = composer2.changed(i22);
                    Object objRememberedValue3 = composer2.rememberedValue();
                    if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new r7.a() { // from class: com.arflix.tv.ui.components.l3
                            @Override // r7.a
                            public final Object invoke() {
                                return StreamSelectorKt.StreamSelector$lambda$44$0$1$3$1$0$0$0(i22, mutableIntState2, mutableIntState3);
                            }
                        };
                        composer2.updateRememberedValue(objRememberedValue3);
                    }
                    Modifier modifierH = androidx.fragment.app.a2.h(i18, ClickableKt.m214clickableXHw0xAI$default(modifierThen, false, null, null, (r7.a) objRememberedValue3, 7, null), Dp.m5678constructorimpl(i20), composer2, 733328855);
                    MeasurePolicy measurePolicyD3 = a0.c.d(Alignment.INSTANCE, false, composer2, 0, -1323940314);
                    int currentCompositeKeyHash8 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap8 = composer2.getCurrentCompositionLocalMap();
                    ComposeUiNode.Companion companion9 = ComposeUiNode.INSTANCE;
                    r7.a<ComposeUiNode> constructor8 = companion9.getConstructor();
                    r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf8 = LayoutKt.modifierMaterializerOf(modifierH);
                    if (!com.arflix.tv.data.repository.g.s(composer2.getApplier())) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor8);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM2991constructorimpl8 = Updater.m2991constructorimpl(composer2);
                    r7.p pVarU8 = a0.c.u(companion9, composerM2991constructorimpl8, measurePolicyD3, composerM2991constructorimpl8, currentCompositionLocalMap8);
                    if (composerM2991constructorimpl8.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl8.rememberedValue(), Integer.valueOf(currentCompositeKeyHash8))) {
                        a0.c.w(currentCompositeKeyHash8, composerM2991constructorimpl8, currentCompositeKeyHash8, pVarU8);
                    }
                    a0.c.y(composer2, qVarModifierMaterializerOf8, composer2, num2, 2058660585);
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    TextStyle caption = ArflixTypography.INSTANCE.getCaption();
                    long sp = TextUnitKt.getSp(12);
                    FontWeight.Companion companion10 = FontWeight.INSTANCE;
                    f15 = f17;
                    TextKt.m6020Text4IGK_g(str3, null, i24 != 0 ? ColorKt.getPink() : ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 1, 0, null, TextStyle.m5205copyp1EtxEg$default(caption, 0L, sp, i24 != 0 ? companion10.getSemiBold() : companion10.getNormal(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer, 0, 3072, 57338);
                    composer2 = composer;
                    androidx.compose.foundation.c.y(composer2);
                    i22 = i23;
                    f16 = f16;
                    i20 = 14;
                    num2 = num2;
                    i21 = 1;
                    i18 = 6;
                    i19 = 0;
                }
                i16 = i20;
                f10 = f16;
                num = num2;
                f11 = f15;
                focusRequester2 = null;
                composer2.endReplaceGroup();
                composer2.endReplaceableGroup();
                composer2.endNode();
                composer2.endReplaceableGroup();
                composer2.endReplaceableGroup();
                composer2.endReplaceGroup();
            } else {
                f10 = f14;
                i16 = 14;
                num = null;
                f11 = f15;
                focusRequester2 = null;
                composer2.startReplaceGroup(1031697716);
                composer2.endReplaceGroup();
            }
            if (list3.isEmpty()) {
                composer2.startReplaceGroup(1031947390);
                boolean z14 = z10 || (i12 < i13 && i13 > 0) || z12;
                Modifier.Companion companion11 = Modifier.INSTANCE;
                Modifier modifierA2 = androidx.compose.foundation.layout.d.a(columnScopeInstance, SizeKt.fillMaxSize$default(companion11, 0.0f, 1, focusRequester2), 1.0f, false, 2, null);
                Alignment.Companion companion12 = Alignment.INSTANCE;
                Alignment center2 = companion12.getCenter();
                composer2.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center2, false, composer2, 6);
                composer2.startReplaceableGroup(-1323940314);
                int currentCompositeKeyHash9 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap9 = composer2.getCurrentCompositionLocalMap();
                ComposeUiNode.Companion companion13 = ComposeUiNode.INSTANCE;
                r7.a<ComposeUiNode> constructor9 = companion13.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf9 = LayoutKt.modifierMaterializerOf(modifierA2);
                if (!com.arflix.tv.data.repository.g.s(composer2.getApplier())) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor9);
                } else {
                    composer2.useNode();
                }
                Composer composerM2991constructorimpl9 = Updater.m2991constructorimpl(composer2);
                r7.p pVarU9 = a0.c.u(companion13, composerM2991constructorimpl9, measurePolicyRememberBoxMeasurePolicy2, composerM2991constructorimpl9, currentCompositionLocalMap9);
                if (composerM2991constructorimpl9.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl9.rememberedValue(), Integer.valueOf(currentCompositeKeyHash9))) {
                    a0.c.w(currentCompositeKeyHash9, composerM2991constructorimpl9, currentCompositeKeyHash9, pVarU9);
                }
                Integer num3 = num;
                a0.c.y(composer2, qVarModifierMaterializerOf9, composer2, num3, 2058660585);
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                Alignment.Horizontal centerHorizontally = companion12.getCenterHorizontally();
                float f18 = f11;
                Modifier modifierM = com.arflix.tv.data.repository.g.m(32, com.arflix.tv.data.repository.g.k(f18, com.arflix.tv.data.repository.g.j(f18, companion11, GlassWhite), Dp.m5678constructorimpl(1), GlassBorder), composer2, -483455358);
                MeasurePolicy measurePolicyF = androidx.compose.material3.d.f(Arrangement.INSTANCE, centerHorizontally, composer2, 48, -1323940314);
                int currentCompositeKeyHash10 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap10 = composer2.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor10 = companion13.getConstructor();
                r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf10 = LayoutKt.modifierMaterializerOf(modifierM);
                if (!com.arflix.tv.data.repository.g.s(composer2.getApplier())) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor10);
                } else {
                    composer2.useNode();
                }
                Composer composerM2991constructorimpl10 = Updater.m2991constructorimpl(composer2);
                r7.p pVarU10 = a0.c.u(companion13, composerM2991constructorimpl10, measurePolicyF, composerM2991constructorimpl10, currentCompositionLocalMap10);
                if (composerM2991constructorimpl10.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl10.rememberedValue(), Integer.valueOf(currentCompositeKeyHash10))) {
                    a0.c.w(currentCompositeKeyHash10, composerM2991constructorimpl10, currentCompositeKeyHash10, pVarU10);
                }
                a0.c.y(composer2, qVarModifierMaterializerOf10, composer2, num3, 2058660585);
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                if (z14) {
                    composer2.startReplaceGroup(253636245);
                    LoadingIndicatorKt.m6162LoadingIndicator5_Ve2oQ(null, Dp.m5678constructorimpl(40), ColorKt.getPink(), 0.0f, composer2, 432, 9);
                    SpacerKt.Spacer(SizeKt.m564height3ABfNKs(companion11, Dp.m5678constructorimpl(f13)), composer2, 6);
                    composer2.startReplaceGroup(-1377284148);
                    StringBuilder sb2 = new StringBuilder();
                    if (mutableIntState6.getIntValue() > 0) {
                        sb2.append(mutableIntState6.getIntValue() + "s • ");
                    }
                    if (!set.isEmpty()) {
                        composer2.startReplaceGroup(-1126577010);
                        sb2.append(StringResources_androidKt.stringResource(R.string.plugins_loading, new Object[]{kotlin.collections.x.u0(set, ", ", null, null, null, 62)}, composer2, 0));
                        composer2.endReplaceGroup();
                    } else if (z12) {
                        composer2.startReplaceGroup(-1126571794);
                        sb2.append(StringResources_androidKt.stringResource(R.string.plugins_loading, new Object[]{"..."}, composer2, 0));
                        composer2.endReplaceGroup();
                    } else if (i13 > 0) {
                        composer2.startReplaceGroup(-1126567731);
                        sb2.append(StringResources_androidKt.stringResource(R.string.stream_searching_addons, new Object[]{Integer.valueOf(i12), Integer.valueOf(i13)}, composer2, 0));
                        composer2.endReplaceGroup();
                    } else {
                        composer2.startReplaceGroup(-1126563417);
                        sb2.append(StringResources_androidKt.stringResource(R.string.finding_sources, composer2, 0));
                        composer2.endReplaceGroup();
                    }
                    String string = sb2.toString();
                    composer2.endReplaceGroup();
                    TextKt.m6020Text4IGK_g(string, null, ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(ArflixTypography.INSTANCE.getBody(), 0L, TextUnitKt.getSp(i16), FontWeight.INSTANCE.getMedium(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer2, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
                    composer2.endReplaceGroup();
                } else {
                    composer2.startReplaceGroup(255020767);
                    long jColor = !z11 ? androidx.compose.ui.graphics.ColorKt.Color(4282090230L) : Color.m3471copywmQWz5c$default(ColorKt.getTextSecondary(), 0.5f, 0.0f, 0.0f, 0.0f, 14, null);
                    Modifier modifierM178backgroundbw27NRU = BackgroundKt.m178backgroundbw27NRU(SizeKt.m578size3ABfNKs(companion11, Dp.m5678constructorimpl(48)), Color.m3471copywmQWz5c$default(jColor, 0.1f, 0.0f, 0.0f, 0.0f, 14, null), RoundedCornerShapeKt.getCircleShape());
                    Alignment center3 = companion12.getCenter();
                    composer2.startReplaceableGroup(733328855);
                    MeasurePolicy measurePolicyRememberBoxMeasurePolicy3 = BoxKt.rememberBoxMeasurePolicy(center3, false, composer2, 6);
                    composer2.startReplaceableGroup(-1323940314);
                    int currentCompositeKeyHash11 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap11 = composer2.getCurrentCompositionLocalMap();
                    r7.a<ComposeUiNode> constructor11 = companion13.getConstructor();
                    r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf11 = LayoutKt.modifierMaterializerOf(modifierM178backgroundbw27NRU);
                    if (!com.arflix.tv.data.repository.g.s(composer2.getApplier())) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor11);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM2991constructorimpl11 = Updater.m2991constructorimpl(composer2);
                    r7.p pVarU11 = a0.c.u(companion13, composerM2991constructorimpl11, measurePolicyRememberBoxMeasurePolicy3, composerM2991constructorimpl11, currentCompositionLocalMap11);
                    if (composerM2991constructorimpl11.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl11.rememberedValue(), Integer.valueOf(currentCompositeKeyHash11))) {
                        a0.c.w(currentCompositeKeyHash11, composerM2991constructorimpl11, currentCompositeKeyHash11, pVarU11);
                    }
                    a0.c.y(composer2, qVarModifierMaterializerOf11, composer2, num3, 2058660585);
                    Icons.Filled filled = Icons.INSTANCE.getDefault();
                    IconKt.m1610Iconww6aTOc(!z11 ? SettingsKt.getSettings(filled) : CloudKt.getCloud(filled), (String) null, SizeKt.m578size3ABfNKs(companion11, Dp.m5678constructorimpl(24)), jColor, composer2, 432, 0);
                    com.arflix.tv.data.repository.g.r(composer2, f13, companion11, composer2, 6);
                    if (z11) {
                        composer2.startReplaceGroup(-1377210000);
                        i17 = R.string.stream_no_sources_found;
                    } else {
                        composer2.startReplaceGroup(-1377211821);
                        i17 = R.string.stream_no_streaming_addons;
                    }
                    String strStringResource3 = StringResources_androidKt.stringResource(i17, composer2, 0);
                    composer2.endReplaceGroup();
                    ArflixTypography arflixTypography2 = ArflixTypography.INSTANCE;
                    TextKt.m6020Text4IGK_g(strStringResource3, null, ColorKt.getTextSecondary(), 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography2.getBody(), 0L, TextUnitKt.getSp(i16), FontWeight.INSTANCE.getMedium(), null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777209, null), composer, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65530);
                    composer2 = composer;
                    com.arflix.tv.data.repository.g.p(4, companion11, composer2, 6);
                    if (z11) {
                        composer2.startReplaceGroup(-1377185135);
                        strStringResource2 = StringResources_androidKt.stringResource(R.string.stream_try_adding_addons, composer2, 0);
                        composer2.endReplaceGroup();
                    } else {
                        composer2.startReplaceGroup(-1377189490);
                        strStringResource2 = StringResources_androidKt.stringResource(R.string.stream_no_addons_hint, composer2, 0);
                        composer2.endReplaceGroup();
                    }
                    TextKt.m6020Text4IGK_g(strStringResource2, null, Color.m3471copywmQWz5c$default(ColorKt.getTextSecondary(), 0.6f, 0.0f, 0.0f, 0.0f, 14, null), 0L, null, null, null, 0L, null, TextAlign.m5575boximpl(TextAlign.INSTANCE.m5582getCentere0LSkKk()), 0L, 0, false, 0, 0, null, TextStyle.m5205copyp1EtxEg$default(arflixTypography2.getCaption(), 0L, TextUnitKt.getSp(12), null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777213, null), composer2, RendererCapabilities.DECODER_SUPPORT_MASK, 0, 65018);
                    composer2.endReplaceGroup();
                }
                com.arflix.tv.data.repository.g.v(composer2);
            } else {
                int i25 = 1;
                composer2.startReplaceGroup(1036548751);
                PaddingValues paddingValuesM524PaddingValuesYgX7TsA$default = PaddingKt.m524PaddingValuesYgX7TsA$default(0.0f, Dp.m5678constructorimpl(f10), 1, focusRequester2);
                Arrangement.HorizontalOrVertical horizontalOrVerticalM441spacedBy0680j_4 = Arrangement.INSTANCE.m441spacedBy0680j_4(Dp.m5678constructorimpl(6));
                Modifier modifierArvioDpadFocusGroup$default = ArvioDpadFocusKt.arvioDpadFocusGroup$default(androidx.compose.foundation.layout.d.a(columnScopeInstance, SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, focusRequester2), 1.0f, false, 2, null), focusRequester2, false, 3, focusRequester2);
                boolean zChanged4 = composer2.changed(list4) | composer2.changed(streamSource) | composer2.changed(lVar);
                Object objRememberedValue4 = composer2.rememberedValue();
                if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new a0(list4, streamSource, lVar, i25);
                    composer2.updateRememberedValue(objRememberedValue4);
                }
                LazyDslKt.LazyColumn(modifierArvioDpadFocusGroup$default, lazyListStateRememberLazyListState, paddingValuesM524PaddingValuesYgX7TsA$default, false, horizontalOrVerticalM441spacedBy0680j_4, null, null, false, (r7.l) objRememberedValue4, composer2, 24960, 232);
                composer.endReplaceGroup();
            }
            com.arflix.tv.data.repository.g.q(composer);
        } else {
            composer.startReplaceGroup(1202483023);
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list5, 10));
            Iterator it = list5.iterator();
            while (it.hasNext()) {
                arrayList.add(((SourceFilter) it.next()).getLabel());
            }
            int intValue = mutableIntState4.getIntValue();
            int intValue2 = mutableIntState5.getIntValue();
            int intValue3 = mutableIntState7.getIntValue();
            int intValue4 = mutableIntState.getIntValue();
            boolean zA = kotlin.jvm.internal.p.a(StreamSelector$lambda$19(mutableState), "addons");
            int intValue5 = mutableIntState8.getIntValue();
            boolean zA2 = kotlin.jvm.internal.p.a(StreamSelector$lambda$19(mutableState), "streams");
            int intValue6 = mutableIntState6.getIntValue();
            Object objRememberedValue5 = composer.rememberedValue();
            Composer.Companion companion14 = Composer.INSTANCE;
            if (objRememberedValue5 == companion14.getEmpty()) {
                final int i26 = 0;
                objRememberedValue5 = new r7.l() { // from class: com.arflix.tv.ui.components.k3
                    @Override // r7.l
                    public final Object invoke(Object obj2) {
                        int i27 = i26;
                        int iIntValue = ((Integer) obj2).intValue();
                        switch (i27) {
                            case 0:
                                return StreamSelectorKt.StreamSelector$lambda$44$0$1$1$0(mutableIntState4, mutableIntState5, mutableIntState8, iIntValue);
                            default:
                                return StreamSelectorKt.StreamSelector$lambda$44$0$1$2$0(mutableIntState4, mutableIntState5, mutableIntState8, iIntValue);
                        }
                    }
                };
                composer.updateRememberedValue(objRememberedValue5);
            }
            r7.l lVar3 = (r7.l) objRememberedValue5;
            Object objRememberedValue6 = composer.rememberedValue();
            if (objRememberedValue6 == companion14.getEmpty()) {
                final int i27 = 1;
                objRememberedValue6 = new r7.l() { // from class: com.arflix.tv.ui.components.k3
                    @Override // r7.l
                    public final Object invoke(Object obj2) {
                        int i272 = i27;
                        int iIntValue = ((Integer) obj2).intValue();
                        switch (i272) {
                            case 0:
                                return StreamSelectorKt.StreamSelector$lambda$44$0$1$1$0(mutableIntState7, mutableIntState, mutableIntState8, iIntValue);
                            default:
                                return StreamSelectorKt.StreamSelector$lambda$44$0$1$2$0(mutableIntState7, mutableIntState, mutableIntState8, iIntValue);
                        }
                    }
                };
                composer.updateRememberedValue(objRememberedValue6);
            }
            OledSourceSelectorTv(str, str2, list3, list4, streamSource, arrayList, intValue, intValue2, false, list, list6, intValue3, intValue4, zA, m0Var, m0Var2, intValue5, zA2, i10, i11, z10, z11, i12, i13, intValue6, z12, set, lVar3, (r7.l) objRememberedValue6, lVar, composer, 100663296, 0, 113246208, 0);
            composer.endReplaceGroup();
        }
        if (androidx.compose.foundation.c.A(composer)) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 StreamSelector$lambda$44$0$1$1$0(MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableIntState mutableIntState3, int i10) {
        mutableIntState.setIntValue(i10);
        mutableIntState2.setIntValue(i10);
        mutableIntState3.setIntValue(0);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 StreamSelector$lambda$44$0$1$2$0(MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableIntState mutableIntState3, int i10) {
        mutableIntState.setIntValue(i10);
        mutableIntState2.setIntValue(i10);
        mutableIntState3.setIntValue(0);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 StreamSelector$lambda$44$0$1$3$0$1$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 StreamSelector$lambda$44$0$1$3$1$0$0$0(int i10, MutableIntState mutableIntState, MutableIntState mutableIntState2) {
        mutableIntState.setIntValue(i10);
        mutableIntState2.setIntValue(0);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 StreamSelector$lambda$44$0$1$3$3$0(List list, StreamSource streamSource, r7.l lVar, LazyListScope lazyListScope) {
        lazyListScope.items(list.size(), null, new StreamSelectorKt$StreamSelector$lambda$44$0$1$3$3$0$$inlined$items$default$3(StreamSelectorKt$StreamSelector$lambda$44$0$1$3$3$0$$inlined$items$default$1.INSTANCE, list), ComposableLambdaKt.composableLambdaInstance(-632812321, true, new StreamSelectorKt$StreamSelector$lambda$44$0$1$3$3$0$$inlined$items$default$4(list, streamSource, lVar)));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 StreamSelector$lambda$45(boolean z, List list, StreamSource streamSource, boolean z5, String str, String str2, boolean z10, List list2, int i10, int i11, long j10, boolean z11, Set set, r7.l lVar, r7.l lVar2, r7.a aVar, int i12, int i13, int i14, Composer composer, int i15) {
        StreamSelector(z, list, streamSource, z5, str, str2, z10, list2, i10, i11, j10, z11, set, lVar, lVar2, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i12 | 1), RecomposeScopeImplKt.updateChangedFlags(i13), i14);
        return x6.t0.f22605a;
    }

    private static final String bestMatchReason(SourcePresentation sourcePresentation) {
        String transportLabel = sourcePresentation.getTransportLabel();
        String lowerCase = transportLabel != null ? transportLabel.equals("Cached") ? "cached" : transportLabel.toLowerCase(Locale.ROOT) : null;
        String resolutionLabel = sourcePresentation.getResolutionLabel();
        if (kotlin.text.o.h0(resolutionLabel)) {
            resolutionLabel = null;
        }
        String lowerCase2 = resolutionLabel != null ? resolutionLabel.toLowerCase(Locale.ROOT) : null;
        String codecLabel = sourcePresentation.getCodecLabel();
        String lowerCase3 = codecLabel != null ? codecLabel.toLowerCase(Locale.ROOT) : null;
        String audioLabel = sourcePresentation.getAudioLabel();
        String strU0 = kotlin.collections.x.u0(kotlin.collections.x.X0(kotlin.collections.r.X(new String[]{lowerCase, lowerCase2, lowerCase3, audioLabel != null ? audioLabel.toLowerCase(Locale.ROOT) : null}), 3), " - ", null, null, null, 62);
        return kotlin.text.o.h0(strU0) ? "recommended source" : strU0;
    }

    public static final List<SourceAddonTab> buildSourceAddonTabs(List<StreamSource> list, final List<String> list2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (StreamSource streamSource : list) {
            String str = (String) kotlin.collections.x.o0(kotlin.text.o.y0(streamSource.getAddonName(), new String[]{" - "}, 0, 6));
            String string = str != null ? kotlin.text.o.L0(str).toString() : null;
            if (string == null) {
                string = "";
            }
            if (kotlin.text.o.h0(string)) {
                string = streamSource.getAddonName();
                if (kotlin.text.o.h0(string)) {
                    string = "Addon";
                }
            }
            Map.EL.putIfAbsent(linkedHashMap, sourceTabId(streamSource), string);
        }
        final Collection collectionValues = linkedHashMap.values();
        java.util.Map mapD0 = xc.d.d0(new kotlin.collections.c0<String, String>() { // from class: com.arflix.tv.ui.components.StreamSelectorKt$buildSourceAddonTabs$$inlined$groupingBy$1
            @Override // kotlin.collections.c0
            public String keyOf(String element) {
                return element.toLowerCase(Locale.ROOT);
            }

            @Override // kotlin.collections.c0
            public Iterator<String> sourceIterator() {
                return collectionValues.iterator();
            }
        });
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            String str2 = (String) entry.getKey();
            String strT = (String) entry.getValue();
            Locale locale = Locale.ROOT;
            Integer num = (Integer) mapD0.get(strT.toLowerCase(locale));
            if ((num != null ? num.intValue() : 0) > 1) {
                String upperCase = kotlin.text.o.J0(4, str2).toUpperCase(locale);
                if (kotlin.text.o.h0(upperCase)) {
                    upperCase = "ADDON";
                }
                strT = androidx.compose.foundation.c.t(strT, " #", upperCase);
            }
            int i10 = 2;
            String str3 = strT;
            while (!linkedHashSet.add(str3.toLowerCase(Locale.ROOT))) {
                str3 = strT + " (" + i10 + ")";
                i10++;
            }
            arrayList.add(new SourceAddonTab(str2, str3));
        }
        return list2.isEmpty() ? arrayList : kotlin.collections.x.W0(arrayList, new Comparator() { // from class: com.arflix.tv.ui.components.StreamSelectorKt$buildSourceAddonTabs$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int i11;
                SourceAddonTab sourceAddonTab = (SourceAddonTab) t2;
                Iterator it = list2.iterator();
                int i12 = 0;
                while (true) {
                    i11 = -1;
                    if (!it.hasNext()) {
                        i12 = -1;
                        break;
                    }
                    String str4 = (String) it.next();
                    if (kotlin.text.o.T(sourceAddonTab.getId(), str4, false) || kotlin.text.o.T(str4, sourceAddonTab.getId(), false)) {
                        break;
                    }
                    i12++;
                }
                if (i12 < 0) {
                    i12 = Integer.MAX_VALUE;
                }
                Integer numValueOf = Integer.valueOf(i12);
                SourceAddonTab sourceAddonTab2 = (SourceAddonTab) t10;
                int i13 = 0;
                for (String str5 : list2) {
                    if (kotlin.text.o.T(sourceAddonTab2.getId(), str5, false) || kotlin.text.o.T(str5, sourceAddonTab2.getId(), false)) {
                        i11 = i13;
                        break;
                    }
                    i13++;
                }
                return com.google.common.util.concurrent.r0.e(numValueOf, Integer.valueOf(i11 >= 0 ? i11 : Integer.MAX_VALUE));
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x002b, code lost:
    
        if (r6.equals("MB") == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0034, code lost:
    
        if (r6.equals("KB") == false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x003d, code lost:
    
        if (r6.equals("GB") == false) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final long calculateBytes(double r4, java.lang.String r6) {
        /*
            int r0 = r6.hashCode()
            r1 = 2267(0x8db, float:3.177E-42)
            r2 = 4652218415073722368(0x4090000000000000, double:1024.0)
            if (r0 == r1) goto L37
            r1 = 2391(0x957, float:3.35E-42)
            if (r0 == r1) goto L2e
            r1 = 2453(0x995, float:3.437E-42)
            if (r0 == r1) goto L25
            r1 = 2670(0xa6e, float:3.741E-42)
            if (r0 == r1) goto L17
            goto L3f
        L17:
            java.lang.String r0 = "TB"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L3f
            double r4 = r4 * r2
        L20:
            double r4 = r4 * r2
        L21:
            double r4 = r4 * r2
        L22:
            double r4 = r4 * r2
            long r4 = (long) r4
            return r4
        L25:
            java.lang.String r0 = "MB"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L21
            goto L3f
        L2e:
            java.lang.String r0 = "KB"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L22
            goto L3f
        L37:
            java.lang.String r0 = "GB"
            boolean r6 = r6.equals(r0)
            if (r6 != 0) goto L20
        L3f:
            long r4 = (long) r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.calculateBytes(double, java.lang.String):long");
    }

    private static final String cleanSourceDisplayTitle(String str) {
        String strReplace = str.replace('\n', ' ').replace('\r', ' ');
        StreamRegexes streamRegexes = StreamRegexes.INSTANCE;
        String strO = com.arflix.tv.data.repository.g.o(streamRegexes.getWHITESPACE(), strReplace, " ");
        if (strO.length() <= 92) {
            return kotlin.text.o.h0(strO) ? "Unknown source" : strO;
        }
        String strO2 = com.arflix.tv.data.repository.g.o(streamRegexes.getWHITESPACE(), streamRegexes.getYEAR_REMOVAL().f("", streamRegexes.getEXTENSION_REMOVAL().f("", strO)).replace('.', ' ').replace('_', ' '), " ");
        int length = strO2.length();
        if (8 > length || length >= 71) {
            strO2 = null;
        }
        return strO2 == null ? kotlin.text.o.O0(kotlin.text.o.I0(92, strO), '.', ' ', '-', '_') : strO2;
    }

    private static final String cleanStreamDescription(String str, String str2) {
        if (str == null || kotlin.text.o.h0(str)) {
            return null;
        }
        List listN0 = kotlin.text.o.n0(str);
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(listN0, 10));
        Iterator it = listN0.iterator();
        while (it.hasNext()) {
            androidx.fragment.app.a2.x((String) it.next(), arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            String str3 = (String) obj;
            if (!kotlin.text.o.h0(str3) && !str3.equals("None") && !str3.equalsIgnoreCase(str2)) {
                StreamRegexes streamRegexes = StreamRegexes.INSTANCE;
                if (!streamRegexes.getSIZE_LINE_PATTERN().e(str3) && !streamRegexes.getCHANNEL_TAG_PATTERN().e(str3)) {
                    arrayList2.add(obj);
                }
            }
        }
        String string = kotlin.text.o.L0(kotlin.collections.x.u0(arrayList2, "\n", null, null, new com.arflix.tv.data.api.c(14), 30)).toString();
        if (kotlin.text.o.h0(string)) {
            return null;
        }
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence cleanStreamDescription$lambda$2(String str) {
        return com.arflix.tv.data.repository.g.o(StreamRegexes.INSTANCE.getMD_NOISE(), str, "");
    }

    private static final long getSizeBytes(StreamSource streamSource) {
        return parseSizeString(streamSource.getSize());
    }

    private static final boolean isDebridLikeSource(StreamSource streamSource, String str) {
        String addonName = streamSource.getAddonName();
        StringBuilder sb2 = new StringBuilder();
        if (str == null) {
            str = "";
        }
        sb2.append(str);
        sb2.append(' ');
        sb2.append(streamSource.getSource());
        sb2.append(' ');
        sb2.append(streamSource.getQuality());
        sb2.append(' ');
        sb2.append(streamSource.getAddonName());
        sb2.append(' ');
        StreamBehaviorHints behaviorHints = streamSource.getBehaviorHints();
        String filename = behaviorHints != null ? behaviorHints.getFilename() : null;
        if (filename == null) {
            filename = "";
        }
        sb2.append(filename);
        sb2.append(' ');
        String rawLabel = streamSource.getRawLabel();
        if (rawLabel == null) {
            rawLabel = "";
        }
        sb2.append(rawLabel);
        sb2.append(' ');
        String description = streamSource.getDescription();
        if (description == null) {
            description = "";
        }
        sb2.append(description);
        sb2.append(' ');
        String addonTitle = streamSource.getAddonTitle();
        if (addonTitle == null) {
            addonTitle = "";
        }
        sb2.append(addonTitle);
        sb2.append(' ');
        StreamBehaviorHints behaviorHints2 = streamSource.getBehaviorHints();
        String provider = behaviorHints2 != null ? behaviorHints2.getProvider() : null;
        if (provider == null) {
            provider = "";
        }
        sb2.append(provider);
        sb2.append(' ');
        StreamBehaviorHints behaviorHints3 = streamSource.getBehaviorHints();
        String sourceLabel = behaviorHints3 != null ? behaviorHints3.getSourceLabel() : null;
        if (sourceLabel == null) {
            sourceLabel = "";
        }
        sb2.append(sourceLabel);
        sb2.append(' ');
        String url = streamSource.getUrl();
        sb2.append(url != null ? url : "");
        String string = sb2.toString();
        return kotlin.text.o.T(addonName, "torbox", true) || kotlin.text.o.T(addonName, "torrentio tb", true) || kotlin.text.o.T(addonName, "torrentio rd", true) || kotlin.text.o.T(addonName, "torrentio pm", true) || kotlin.text.o.T(addonName, "torrentio ad", true) || kotlin.text.o.T(string, "debrid", true) || kotlin.text.o.T(string, "real-debrid", true) || kotlin.text.o.T(string, "realdebrid", true) || kotlin.text.o.T(string, "premiumize", true) || kotlin.text.o.T(string, "alldebrid", true) || kotlin.text.o.T(string, " RD+", true) || kotlin.text.o.T(string, "[RD+]", true) || kotlin.text.o.T(string, " TB+", true) || kotlin.text.o.T(string, "[TB+]", true) || kotlin.text.o.T(string, "torbox", true);
    }

    public static /* synthetic */ boolean isDebridLikeSource$default(StreamSource streamSource, String str, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str = null;
        }
        return isDebridLikeSource(streamSource, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean isSelectedSource(com.arflix.tv.data.model.StreamSource r8, com.arflix.tv.data.model.StreamSource r9) {
        /*
            r0 = 0
            if (r9 != 0) goto L4
            return r0
        L4:
            boolean r1 = kotlin.jvm.internal.p.a(r8, r9)
            r2 = 1
            if (r1 == 0) goto Lc
            return r2
        Lc:
            java.lang.String r1 = r8.getAddonId()
            boolean r1 = kotlin.text.o.h0(r1)
            if (r1 != 0) goto L26
            java.lang.String r1 = r8.getAddonId()
            java.lang.String r3 = r9.getAddonId()
            boolean r1 = kotlin.jvm.internal.p.a(r1, r3)
            if (r1 == 0) goto L26
            r1 = r2
            goto L27
        L26:
            r1 = r0
        L27:
            java.lang.String r3 = r8.getSource()
            boolean r3 = kotlin.text.o.h0(r3)
            if (r3 != 0) goto L41
            java.lang.String r3 = r8.getSource()
            java.lang.String r4 = r9.getSource()
            boolean r3 = kotlin.jvm.internal.p.a(r3, r4)
            if (r3 == 0) goto L41
            r3 = r2
            goto L42
        L41:
            r3 = r0
        L42:
            com.arflix.tv.data.model.StreamBehaviorHints r4 = r8.getBehaviorHints()
            r5 = 0
            if (r4 == 0) goto L6a
            java.lang.String r4 = r4.getFilename()
            if (r4 == 0) goto L6a
            boolean r6 = kotlin.text.o.h0(r4)
            if (r6 != 0) goto L56
            goto L57
        L56:
            r4 = r5
        L57:
            if (r4 == 0) goto L6a
            com.arflix.tv.data.model.StreamBehaviorHints r6 = r9.getBehaviorHints()
            if (r6 == 0) goto L64
            java.lang.String r6 = r6.getFilename()
            goto L65
        L64:
            r6 = r5
        L65:
            boolean r4 = r4.equals(r6)
            goto L6b
        L6a:
            r4 = r0
        L6b:
            com.arflix.tv.data.model.StreamBehaviorHints r6 = r8.getBehaviorHints()
            if (r6 == 0) goto L92
            java.lang.String r6 = r6.getBingeGroup()
            if (r6 == 0) goto L92
            boolean r7 = kotlin.text.o.h0(r6)
            if (r7 != 0) goto L7e
            goto L7f
        L7e:
            r6 = r5
        L7f:
            if (r6 == 0) goto L92
            com.arflix.tv.data.model.StreamBehaviorHints r7 = r9.getBehaviorHints()
            if (r7 == 0) goto L8c
            java.lang.String r7 = r7.getBingeGroup()
            goto L8d
        L8c:
            r7 = r5
        L8d:
            boolean r6 = r6.equals(r7)
            goto L93
        L92:
            r6 = r0
        L93:
            java.lang.String r8 = r8.getUrl()
            if (r8 == 0) goto Lab
            boolean r7 = kotlin.text.o.h0(r8)
            if (r7 != 0) goto La0
            r5 = r8
        La0:
            if (r5 == 0) goto Lab
            java.lang.String r8 = r9.getUrl()
            boolean r8 = r5.equals(r8)
            goto Lac
        Lab:
            r8 = r0
        Lac:
            if (r8 != 0) goto Lb8
            if (r1 == 0) goto Lb7
            if (r3 != 0) goto Lb8
            if (r4 != 0) goto Lb8
            if (r6 == 0) goto Lb7
            goto Lb8
        Lb7:
            return r0
        Lb8:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.isSelectedSource(com.arflix.tv.data.model.StreamSource, com.arflix.tv.data.model.StreamSource):boolean");
    }

    private static final boolean keepsOwnStreamOrder(StreamSource streamSource) {
        return kotlin.text.o.T(streamSource.getAddonName(), "aiostream", true) || kotlin.text.o.T(streamSource.getAddonId(), "aiostream", true);
    }

    private static final String languageBadgeText(String str) {
        if (str == null || kotlin.text.o.h0(str)) {
            return null;
        }
        String upperCase = kotlin.text.o.L0(str).toString().toUpperCase(Locale.ROOT);
        return (kotlin.text.o.T(upperCase, "MULTI", false) || kotlin.text.o.T(upperCase, "LANG", false)) ? "🌐 MULTI" : kotlin.collections.r.p0(new String[]{"EN", "ENG", "ENGLISH"}).contains(upperCase) ? "🇬🇧 EN" : kotlin.collections.r.p0(new String[]{"NL", "NLD", "DUT", "DUTCH", "NEDERLANDS"}).contains(upperCase) ? "🇳🇱 NL" : kotlin.collections.r.p0(new String[]{"JA", "JPN", "JAPANESE"}).contains(upperCase) ? "🇯🇵 JA" : kotlin.collections.r.p0(new String[]{"KO", "KOR", "KOREAN"}).contains(upperCase) ? "🇰🇷 KO" : kotlin.collections.r.p0(new String[]{"ES", "SPA", "SPANISH"}).contains(upperCase) ? "🇪🇸 ES" : kotlin.collections.r.p0(new String[]{"FR", "FRE", "FRA", "FRENCH"}).contains(upperCase) ? "🇫🇷 FR" : kotlin.collections.r.p0(new String[]{"DE", "GER", "DEU", "GERMAN"}).contains(upperCase) ? "🇩🇪 DE" : kotlin.collections.r.p0(new String[]{"IT", "ITA", "ITALIAN"}).contains(upperCase) ? "🇮🇹 IT" : kotlin.collections.r.p0(new String[]{"HI", "HIN", "HINDI"}).contains(upperCase) ? "🇮🇳 HI" : kotlin.collections.r.p0(new String[]{"TA", "TAM", "TAMIL"}).contains(upperCase) ? "🇮🇳 TA" : kotlin.collections.r.p0(new String[]{"TE", "TEL", "TELUGU"}).contains(upperCase) ? "🇮🇳 TE" : kotlin.text.o.I0(6, upperCase);
    }

    private static final String multiSourceCountLabel(StreamSource streamSource) {
        if (streamSource.getSources().size() > 1) {
            return androidx.fragment.app.a2.i(streamSource.getSources().size(), " sources");
        }
        if (streamSource.getSources().size() == 1) {
            return "1 source";
        }
        return null;
    }

    private static final long parseSizeString(String str) {
        Long lS;
        Double dG;
        Double dG2;
        if (kotlin.text.o.h0(str)) {
            return 0L;
        }
        String strO = kotlin.text.u.O(str.toUpperCase(Locale.ROOT), ",", ".", false);
        StreamRegexes streamRegexes = StreamRegexes.INSTANCE;
        String strO2 = com.arflix.tv.data.repository.g.o(streamRegexes.getWHITESPACE(), strO, " ");
        kotlin.text.k kVarB = kotlin.text.m.b(streamRegexes.getSIZE_PATTERN_1(), strO2);
        if (kVarB != null && (dG2 = kotlin.text.t.G((String) ((kotlin.collections.n0) kVarB.b()).get(1))) != null) {
            return calculateBytes(dG2.doubleValue(), (String) ((kotlin.collections.n0) kVarB.b()).get(2));
        }
        kotlin.text.k kVarB2 = kotlin.text.m.b(streamRegexes.getSIZE_PATTERN_2(), strO2);
        if (kVarB2 != null && (dG = kotlin.text.t.G((String) ((kotlin.collections.n0) kVarB2.b()).get(1))) != null) {
            return calculateBytes(dG.doubleValue(), kotlin.text.u.O((String) ((kotlin.collections.n0) kVarB2.b()).get(2), "IB", "B", false));
        }
        kotlin.text.k kVarB3 = kotlin.text.m.b(streamRegexes.getSIZE_PATTERN_3(), strO2);
        if (kVarB3 == null || (lS = kotlin.text.u.S((String) ((kotlin.collections.n0) kVarB3.b()).get(1))) == null) {
            return 0L;
        }
        return lS.longValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:100:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x03b3  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x03c8  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x046d  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0472  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0483  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x048d  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x0503  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0508  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x050d  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0512  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x0517  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x0537  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x0546  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x054d  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x055a  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x0578  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x0582  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0586  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x059c  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x05ba  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x05bc  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x05c4  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x05e1  */
    /* JADX WARN: Removed duplicated region for block: B:378:0x05ec  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x05f3  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x05fe  */
    /* JADX WARN: Removed duplicated region for block: B:392:0x0635  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x063c  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x064b  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x0677  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x068c  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x066e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final com.arflix.tv.ui.components.SourcePresentation presentSource(com.arflix.tv.data.model.StreamSource r32) {
        /*
            Method dump skipped, instruction units count: 1736
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.presentSource(com.arflix.tv.data.model.StreamSource):com.arflix.tv.ui.components.SourcePresentation");
    }

    private static final int qualityScore(String str) {
        if (kotlin.text.o.T(str, "4K", true) || kotlin.text.o.T(str, "2160p", false)) {
            return 4;
        }
        if (kotlin.text.o.T(str, "1080p", true)) {
            return 3;
        }
        if (kotlin.text.o.T(str, "720p", true)) {
            return 2;
        }
        return kotlin.text.o.T(str, "480p", true) ? 1 : 0;
    }

    private static final String rowSubtitle(SourcePresentation sourcePresentation) {
        String addonLabel;
        String upstreamLabel = sourcePresentation.getUpstreamLabel();
        if (upstreamLabel == null || (addonLabel = androidx.compose.foundation.c.t(sourcePresentation.getAddonLabel(), " — ", upstreamLabel)) == null) {
            addonLabel = sourcePresentation.getAddonLabel();
        }
        z6.c cVarN = t7.a.n();
        cVarN.add(addonLabel);
        cVarN.addAll(sourcePresentation.getAttributionLabels());
        String editionLabel = sourcePresentation.getEditionLabel();
        if (editionLabel != null) {
            cVarN.add(editionLabel);
        }
        String bitrateLabel = sourcePresentation.getBitrateLabel();
        if (bitrateLabel != null) {
            cVarN.add(bitrateLabel);
        }
        z6.c cVarE = t7.a.e(cVarN);
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        ListIterator listIterator = cVarE.listIterator(0);
        while (true) {
            z6.a aVar = (z6.a) listIterator;
            if (!aVar.hasNext()) {
                return kotlin.collections.x.u0(arrayList, " · ", null, null, null, 62);
            }
            Object next = aVar.next();
            if (hashSet.add(StreamSelectorRegexes.INSTANCE.getDISTINCT_TITLE_REGEX().f("", ((String) next).toLowerCase(Locale.ROOT)))) {
                arrayList.add(next);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object scrollToKeepFocusVisible(androidx.tv.foundation.lazy.list.m0 m0Var, int i10, int i11, d7.d<? super x6.t0> dVar) {
        int iN;
        Object objD;
        Object objD2;
        if (i11 != 0) {
            int iN2 = qb.d.n(i10, 0, i11 - 1);
            List visibleItemsInfo = m0Var.c().getVisibleItemsInfo();
            boolean zIsEmpty = visibleItemsInfo.isEmpty();
            e7.a aVar = e7.a.f15033i;
            if (zIsEmpty) {
                Object objD3 = m0Var.d(iN2, 0, dVar);
                if (objD3 == aVar) {
                    return objD3;
                }
            } else {
                int index = ((androidx.tv.foundation.lazy.list.b0) kotlin.collections.x.m0(visibleItemsInfo)).getIndex();
                int index2 = ((androidx.tv.foundation.lazy.list.b0) kotlin.collections.x.w0(visibleItemsInfo)).getIndex();
                int i12 = (index2 - index) + 1;
                if (i12 < 1) {
                    i12 = 1;
                }
                if (iN2 <= index) {
                    int i13 = iN2 - 1;
                    if (i13 < 0) {
                        i13 = 0;
                    }
                    if (i13 != index && (objD2 = m0Var.d(i13, 0, dVar)) == aVar) {
                        return objD2;
                    }
                } else if (iN2 >= index2 && (iN = qb.d.n((iN2 - i12) + 2, 0, iN2)) != index && (objD = m0Var.d(iN, 0, dVar)) == aVar) {
                    return objD;
                }
            }
        }
        return x6.t0.f22605a;
    }

    public static final List<String> sourceAddonTabKeys(List<SourceAddonTab> list) {
        List listSingletonList = Collections.singletonList("source_addon:all");
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add("source_addon:id:" + ((SourceAddonTab) it.next()).getId());
        }
        return kotlin.collections.x.I0(listSingletonList, arrayList);
    }

    private static final float sourceBadgeWidth(String str) {
        return kotlin.text.u.L(str, "4K", true) ? Dp.m5678constructorimpl(42) : kotlin.text.u.L(str, "1080p", true) ? Dp.m5678constructorimpl(56) : kotlin.text.u.L(str, "720p", true) ? Dp.m5678constructorimpl(50) : kotlin.text.u.L(str, "REMUX", true) ? Dp.m5678constructorimpl(62) : kotlin.text.u.L(str, "BluRay", true) ? Dp.m5678constructorimpl(62) : kotlin.text.u.L(str, "Atmos", true) ? Dp.m5678constructorimpl(66) : kotlin.text.u.L(str, "TrueHD", true) ? Dp.m5678constructorimpl(62) : kotlin.text.u.L(str, "DTS-HD MA", true) ? Dp.m5678constructorimpl(78) : kotlin.text.u.L(str, "DTS-HD", true) ? Dp.m5678constructorimpl(64) : kotlin.text.u.L(str, "DTS:X", true) ? Dp.m5678constructorimpl(58) : kotlin.text.u.L(str, "DD+", true) ? Dp.m5678constructorimpl(48) : kotlin.text.u.L(str, "DD", true) ? Dp.m5678constructorimpl(42) : kotlin.text.u.L(str, "DV", true) ? Dp.m5678constructorimpl(76) : kotlin.text.u.L(str, "IMAX", true) ? Dp.m5678constructorimpl(54) : kotlin.text.u.L(str, "7.1", true) ? Dp.m5678constructorimpl(40) : kotlin.text.u.L(str, "5.1", true) ? Dp.m5678constructorimpl(40) : kotlin.text.u.L(str, "HDR10+", true) ? Dp.m5678constructorimpl(64) : kotlin.text.u.L(str, "HDR10", true) ? Dp.m5678constructorimpl(58) : kotlin.text.u.L(str, "HDR", true) ? Dp.m5678constructorimpl(48) : Dp.m5678constructorimpl(52);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final java.util.List<com.arflix.tv.ui.components.SourceBadge> sourceBadges(com.arflix.tv.ui.components.SourcePresentation r10) {
        /*
            Method dump skipped, instruction units count: 882
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.StreamSelectorKt.sourceBadges(com.arflix.tv.ui.components.SourcePresentation):java.util.List");
    }

    private static final boolean sourceFilterMatches(SourcePresentation sourcePresentation, String str) {
        StreamSource stream = sourcePresentation.getStream();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(stream.getSource());
        sb2.append(' ');
        sb2.append(stream.getQuality());
        sb2.append(' ');
        sb2.append(stream.getAddonName());
        sb2.append(' ');
        StreamBehaviorHints behaviorHints = stream.getBehaviorHints();
        String filename = behaviorHints != null ? behaviorHints.getFilename() : null;
        if (filename == null) {
            filename = "";
        }
        sb2.append(filename);
        sb2.append(' ');
        String url = stream.getUrl();
        sb2.append(url != null ? url : "");
        String string = sb2.toString();
        int iHashCode = str.hashCode();
        if (iHashCode == 1687) {
            if (str.equals("4K")) {
                return kotlin.jvm.internal.p.a(sourcePresentation.getResolutionLabel(), "4K");
            }
            return true;
        }
        if (iHashCode == 46737913) {
            if (str.equals("1080p")) {
                return kotlin.jvm.internal.p.a(sourcePresentation.getResolutionLabel(), "1080p");
            }
            return true;
        }
        if (iHashCode == 2043090316) {
            if (str.equals("Debrid")) {
                return isDebridLikeSource(stream, string);
            }
            return true;
        }
        if (iHashCode == 2047248393 && str.equals("Direct")) {
            return sourcePresentation.getSortDirect();
        }
        return true;
    }

    private static final String sourceStatusText(int i10, int i11, int i12, boolean z, int i13, boolean z5) {
        int i14 = i12 - i11;
        if (i14 < 0) {
            i14 = 0;
        }
        String strI = (i13 <= 0 || !(z || z5)) ? "" : androidx.fragment.app.a2.i(i13, "s • ");
        if (z && i12 > 0 && i14 > 0) {
            return strI + i10 + " found - still checking " + i14 + " " + (i14 == 1 ? "addon" : "addons");
        }
        if (z) {
            return strI + i10 + " found - searching sources";
        }
        if (z5) {
            return strI + i10 + " found - searching for more sources";
        }
        if (i12 <= 0) {
            return androidx.fragment.app.a2.i(i10, " found");
        }
        return i10 + " found - " + i11 + DomExceptionUtils.SEPARATOR + i12 + " addons checked";
    }

    public static /* synthetic */ String sourceStatusText$default(int i10, int i11, int i12, boolean z, int i13, boolean z5, int i14, Object obj) {
        if ((i14 & 16) != 0) {
            i13 = 0;
        }
        if ((i14 & 32) != 0) {
            z5 = false;
        }
        return sourceStatusText(i10, i11, i12, z, i13, z5);
    }

    public static final String sourceStreamRowKey(StreamSource streamSource, int i10) {
        return "source_stream:" + sourceTabId(streamSource) + ":" + i10;
    }

    public static final String sourceTabId(StreamSource streamSource) {
        String addonName;
        String str = (String) kotlin.collections.x.o0(kotlin.text.o.y0(streamSource.getAddonName(), new String[]{" - "}, 0, 6));
        if (str == null || (addonName = kotlin.text.o.L0(str).toString()) == null) {
            addonName = streamSource.getAddonName();
        }
        if (kotlin.jvm.internal.p.a(streamSource.getAddonId(), HomeServerRepository.ADDON_ID) && !kotlin.text.o.h0(addonName)) {
            return androidx.compose.foundation.c.t(streamSource.getAddonId(), ":", addonName);
        }
        String addonId = streamSource.getAddonId();
        return kotlin.text.o.h0(addonId) ? addonName : addonId;
    }
}
