package com.arflix.tv.ui.screens.tv.live;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.view.Window;
import androidx.compose.animation.AnimatedVisibilityScope;
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
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.core.view.r3;
import androidx.core.view.s3;
import androidx.core.view.t3;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.MimeTypes;
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
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.ui.PlayerView;
import com.arflix.tv.R;
import com.arflix.tv.data.model.DrmInfo;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.data.model.Profile;
import com.arflix.tv.ui.components.AppTopBarKt;
import com.arflix.tv.ui.components.SidebarItem;
import com.arflix.tv.ui.screens.tv.TvUiState;
import com.arflix.tv.ui.screens.tv.TvViewModel;
import com.arflix.tv.util.ClearKeyUtil;
import com.google.android.gms.auth.api.proxy.AuthApiStatusCodes;
import io.ktor.http.ContentDisposition;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
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

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000Æ\u0001\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a\u0019\u0010\u0002\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0001\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u0002\u0010\u0003\u001a]\u0010\u0010\u001a\u0004\u0018\u00010\b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\u0010\n\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u00042\u0006\u0010\u000f\u001a\u00020\fH\u0002¢\u0006\u0004\b\u0010\u0010\u0011\u001aO\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00130\u00042\u0006\u0010\u0012\u001a\u00020\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00042\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u00042\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u00042\u0006\u0010\u0017\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a+\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u001c2\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0000H\u0002¢\u0006\u0004\b\u001d\u0010\u001e\u001a3\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u001c2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0000H\u0002¢\u0006\u0004\b!\u0010\"\u001a+\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000\u001c2\u0006\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u0000H\u0002¢\u0006\u0004\b#\u0010\u001e\u001a\u0013\u0010$\u001a\u00020\f*\u00020\u0005H\u0002¢\u0006\u0004\b$\u0010%\u001a\u0015\u0010'\u001a\u00020\f*\u0004\u0018\u00010&H\u0002¢\u0006\u0004\b'\u0010(\u001a\u001f\u0010+\u001a\u00020\f2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\bH\u0002¢\u0006\u0004\b+\u0010,\u001a+\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0-*\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0-H\u0002¢\u0006\u0004\b.\u0010/\u001a1\u00103\u001a\b\u0012\u0004\u0012\u0002000\u00042\f\u00101\u001a\b\u0012\u0004\u0012\u0002000\u00042\f\u00102\u001a\b\u0012\u0004\u0012\u0002000\u0004H\u0002¢\u0006\u0004\b3\u00104\u001a%\u00107\u001a\u0004\u0018\u00010&2\b\u00105\u001a\u0004\u0018\u00010&2\b\u00106\u001a\u0004\u0018\u00010&H\u0002¢\u0006\u0004\b7\u00108\u001a\u0019\u00109\u001a\b\u0012\u0004\u0012\u00020\b0\u0004*\u00020\u0005H\u0002¢\u0006\u0004\b9\u0010:\u001a\u0017\u0010<\u001a\u00020\f2\u0006\u0010;\u001a\u00020\bH\u0002¢\u0006\u0004\b<\u0010=\u001a\u001b\u0010@\u001a\u000200*\u0002002\u0006\u0010?\u001a\u00020>H\u0002¢\u0006\u0004\b@\u0010A\u001a\u001b\u0010B\u001a\u00020>*\u00020\u00132\u0006\u0010?\u001a\u00020>H\u0002¢\u0006\u0004\bB\u0010C\u001a\u001b\u0010D\u001a\u00020>*\u00020\u00132\u0006\u0010?\u001a\u00020>H\u0002¢\u0006\u0004\bD\u0010C\u001a\u0015\u0010E\u001a\u00020\f*\u0004\u0018\u00010\u0005H\u0002¢\u0006\u0004\bE\u0010%\u001a\u0013\u0010F\u001a\u00020\f*\u00020\u0005H\u0002¢\u0006\u0004\bF\u0010%\u001a\u0017\u0010H\u001a\u00020\u00002\u0006\u0010G\u001a\u00020\u0005H\u0002¢\u0006\u0004\bH\u0010I\u001a%\u0010K\u001a\u00020\u00052\u0006\u0010G\u001a\u00020\u00052\f\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002¢\u0006\u0004\bK\u0010L\u001aÅ\u0001\u0010^\u001a\u00020T2\b\b\u0002\u0010N\u001a\u00020M2\n\b\u0002\u0010P\u001a\u0004\u0018\u00010O2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010R\u001a\u0004\u0018\u00010\b2\u0014\b\u0002\u0010U\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020T0S2\u000e\b\u0002\u0010W\u001a\b\u0012\u0004\u0012\u00020T0V2\u000e\b\u0002\u0010X\u001a\b\u0012\u0004\u0012\u00020T0V2\u000e\b\u0002\u0010Y\u001a\b\u0012\u0004\u0012\u00020T0V2\u000e\b\u0002\u0010Z\u001a\b\u0012\u0004\u0012\u00020T0V2\u0010\b\u0002\u0010[\u001a\n\u0012\u0004\u0012\u00020T\u0018\u00010V2\u000e\b\u0002\u0010\\\u001a\b\u0012\u0004\u0012\u00020T0V2\u000e\b\u0002\u0010]\u001a\b\u0012\u0004\u0012\u00020T0VH\u0007¢\u0006\u0004\b^\u0010_\u001a\u001d\u0010a\u001a\u0004\u0018\u00010\u0000*\u00020`2\u0006\u0010\u0012\u001a\u00020\bH\u0002¢\u0006\u0004\ba\u0010b\u001a\u0019\u0010d\u001a\u00020\b2\b\u0010c\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\bd\u0010e\u001a\u0019\u0010f\u001a\u00020\b2\b\u0010c\u001a\u0004\u0018\u00010\bH\u0002¢\u0006\u0004\bf\u0010e\u001a\u0017\u0010i\u001a\u00020\b2\u0006\u0010h\u001a\u00020gH\u0002¢\u0006\u0004\bi\u0010j\u001a\u001f\u0010n\u001a\u00020m2\u0006\u0010k\u001a\u00020\u00002\u0006\u0010l\u001a\u00020\fH\u0002¢\u0006\u0004\bn\u0010o\u001a\u0019\u0010p\u001a\u0004\u0018\u00010\u00002\u0006\u0010h\u001a\u00020gH\u0002¢\u0006\u0004\bp\u0010q\u001a\u0017\u0010r\u001a\u00020\b2\u0006\u0010;\u001a\u00020\bH\u0002¢\u0006\u0004\br\u0010e\u001a\u0016\u0010u\u001a\u0004\u0018\u00010t*\u00020sH\u0082\u0010¢\u0006\u0004\bu\u0010v\"\u0014\u0010w\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\bw\u0010x\"\u0014\u0010y\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\by\u0010x\"\u0014\u0010z\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\bz\u0010x\"\u0014\u0010{\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b{\u0010x\"\u0014\u0010|\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b|\u0010x\"\u0014\u0010}\u001a\u00020\u00008\u0002X\u0082T¢\u0006\u0006\n\u0004\b}\u0010x\"\u0014\u0010~\u001a\u00020>8\u0002X\u0082T¢\u0006\u0006\n\u0004\b~\u0010\u007f\"\u0016\u0010\u0080\u0001\u001a\u00020>8\u0002X\u0082T¢\u0006\u0007\n\u0005\b\u0080\u0001\u0010\u007f\"\u0017\u0010\u0081\u0001\u001a\u00020\b8\u0002X\u0082T¢\u0006\b\n\u0006\b\u0081\u0001\u0010\u0082\u0001\"\u0016\u0010\u0083\u0001\u001a\u00020>8\u0002X\u0082T¢\u0006\u0007\n\u0005\b\u0083\u0001\u0010\u007f\"\u0016\u0010\u0084\u0001\u001a\u00020>8\u0002X\u0082T¢\u0006\u0007\n\u0005\b\u0084\u0001\u0010\u007f\"\u0018\u0010\u0086\u0001\u001a\u00030\u0085\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0086\u0001\u0010\u0087\u0001\"\u0018\u0010\u0088\u0001\u001a\u00030\u0085\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0088\u0001\u0010\u0087\u0001¨\u0006¿\u0001²\u0006\u000e\u0010\u008a\u0001\u001a\u00030\u0089\u00018\nX\u008a\u0084\u0002²\u0006\u000e\u0010\u008b\u0001\u001a\u00030\u0089\u00018\nX\u008a\u0084\u0002²\u0006\r\u0010\u008c\u0001\u001a\u00020>8\nX\u008a\u0084\u0002²\u0006\u000f\u0010\u008d\u0001\u001a\u00020\b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u008e\u0001\u001a\u00020\b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u008f\u0001\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0090\u0001\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006(\u0010\u0092\u0001\u001a\u001b\u0012\u0017\u0012\u0015\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00000\u0091\u00010\u00048\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010\u0094\u0001\u001a\u00030\u0093\u00018\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0095\u0001\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u0096\u0001\u001a\u00020>8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010\u0097\u0001\u001a\u0004\u0018\u00010\b8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010\u0098\u0001\u001a\u0004\u0018\u00010\b8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010\u0099\u0001\u001a\u0004\u0018\u00010\b8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010\u009a\u0001\u001a\u0004\u0018\u00010\b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u009b\u0001\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010\u009c\u0001\u001a\u0004\u0018\u0001008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u009d\u0001\u001a\u00020>8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u00058\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u009f\u0001\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010 \u0001\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¡\u0001\u001a\u00020\b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¢\u0001\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010£\u0001\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¤\u0001\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¥\u0001\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¦\u0001\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010§\u0001\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¨\u0001\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010©\u0001\u001a\u0004\u0018\u00010\u00058\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010ª\u0001\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010«\u0001\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¬\u0001\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010\u00ad\u0001\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010®\u0001\u001a\u0004\u0018\u00010\u00058\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¯\u0001\u001a\u00020\b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010°\u0001\u001a\u00020>8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010±\u0001\u001a\u00020>8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010²\u0001\u001a\u00020>8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010³\u0001\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010´\u0001\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010µ\u0001\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u0011\u0010¶\u0001\u001a\u0004\u0018\u00010\b8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010·\u0001\u001a\u00020\f8\n@\nX\u008a\u008e\u0002²\u0006\u001b\u0010¸\u0001\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0-8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010¹\u0001\u001a\u00020>8\n@\nX\u008a\u008e\u0002²\u0006\u000f\u0010º\u0001\u001a\u00020\u00008\n@\nX\u008a\u008e\u0002²\u0006\u0012\u0010¼\u0001\u001a\u0005\u0018\u00010»\u00018\n@\nX\u008a\u008e\u0002²\u0006\u000e\u0010¾\u0001\u001a\u00030½\u00018\nX\u008a\u0084\u0002"}, d2 = {"", "keyCode", "digitForTvKeyCode", "(I)Ljava/lang/Integer;", "", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "filteredChannels", "", "", "filteredChannelIds", "explicitInitialChannelId", "sessionLastChannelId", "", "hasOpenedBefore", "favoriteChannelIds", "isFullyEnriched", "chooseStartupChannelId", "(Ljava/util/List;Ljava/util/Set;Ljava/lang/String;Ljava/lang/String;ZLjava/util/List;Z)Ljava/lang/String;", "categoryId", "Lcom/arflix/tv/data/model/IptvChannel;", "providerWindow", "favoriteChannels", "recentChannels", "limit", "selectPagedChannelsInProviderOrder", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;I)Ljava/util/List;", "index", "total", "Lx6/x;", "guideWindowAround", "(II)Lx6/x;", TtmlNode.START, TtmlNode.END, "expandGuideWindowAfter", "(III)Lx6/x;", "expandGuideWindowBefore", "hasGuideIdentity", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;)Z", "Lcom/arflix/tv/data/model/IptvNowNext;", "hasGuideData", "(Lcom/arflix/tv/data/model/IptvNowNext;)Z", ContentDisposition.Parameters.Name, "value", "isSafePlaybackHeader", "(Ljava/lang/String;Ljava/lang/String;)Z", "", "safePlaybackHeaders", "(Ljava/util/Map;)Ljava/util/Map;", "Lcom/arflix/tv/data/model/IptvProgram;", "first", "second", "mergeProgramLists", "(Ljava/util/List;Ljava/util/List;)Ljava/util/List;", "primary", "secondary", "mergeGuideSlices", "(Lcom/arflix/tv/data/model/IptvNowNext;Lcom/arflix/tv/data/model/IptvNowNext;)Lcom/arflix/tv/data/model/IptvNowNext;", "guideFallbackKeys", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;)Ljava/util/List;", "url", "looksLikeMpegTsUrl", "(Ljava/lang/String;)Z", "", "offsetMs", "shiftedForCatchup", "(Lcom/arflix/tv/data/model/IptvProgram;J)Lcom/arflix/tv/data/model/IptvProgram;", "catchupUrlAnchorOffset", "(Lcom/arflix/tv/data/model/IptvChannel;J)J", "catchupInSegmentSeekOffset", "supportsCatchupHistory", "hasExplicitCatchupSource", "channel", "catchupQualityRank", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;)I", "channels", "catchupPlaybackVariant", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Ljava/util/List;)Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "Lcom/arflix/tv/ui/screens/tv/TvViewModel;", "viewModel", "Lcom/arflix/tv/data/model/Profile;", "currentProfile", "initialChannelId", "initialStreamUrl", "Lkotlin/Function1;", "Lx6/t0;", "onFullscreenChanged", "Lkotlin/Function0;", "onNavigateToHome", "onNavigateToSearch", "onNavigateToWatchlist", "onNavigateToSettings", "onNavigateToIptvSettings", "onSwitchProfile", "onBack", "LiveTvScreen", "(Lcom/arflix/tv/ui/screens/tv/TvViewModel;Lcom/arflix/tv/data/model/Profile;Ljava/lang/String;Ljava/lang/String;Lr7/l;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Lr7/a;Landroidx/compose/runtime/Composer;III)V", "Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;", "countForCategory", "(Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;Ljava/lang/String;)Ljava/lang/Integer;", "group", "looseIptvGroupKey", "(Ljava/lang/String;)Ljava/lang/String;", "compactIptvGroupKey", "Landroidx/media3/common/PlaybackException;", "error", "classifyPlaybackError", "(Landroidx/media3/common/PlaybackException;)Ljava/lang/String;", "memoryClassMb", "isLowRamDevice", "Lcom/arflix/tv/ui/screens/tv/live/LiveTvBufferProfile;", "buildLiveTvBufferProfile", "(IZ)Lcom/arflix/tv/ui/screens/tv/live/LiveTvBufferProfile;", "httpResponseCode", "(Landroidx/media3/common/PlaybackException;)Ljava/lang/Integer;", "redactPlaybackUrl", "Landroid/content/Context;", "Landroid/app/Activity;", "findActivity", "(Landroid/content/Context;)Landroid/app/Activity;", "GuideInitialWindowRows", "I", "GuidePageRows", "GuideMaxWindowRows", "GuidePagedLoadStepRows", "GuideVisibleFirstRows", "GuideVisibleFirstRowsAllChannels", "CatchupSeekStepMs", "J", "CatchupUrlAnchorGranularityMs", "IptvPlaybackUserAgent", "Ljava/lang/String;", "VisibleGuidePastWindowMs", "VisibleGuideFutureWindowMs", "Lkotlin/text/m;", "IptvGroupPipeSpacingRegex", "Lkotlin/text/m;", "IptvGroupWhitespaceRegex", "Lcom/arflix/tv/ui/screens/tv/TvUiState;", "state", "currentUiState", "guideClockMillis", "selectedCategoryId", "selectedProviderId", "pagedLoadedLimit", "lastKnownPagedTotal", "Lx6/j0;", "lastKnownPlaylistGroupCounts", "Lcom/arflix/tv/ui/screens/tv/live/LiveTvFocusZone;", "focusZone", "topBarFocusIndex", "lastGuideUserNavigationAt", "filteredChannelsCategoryKey", "playingChannelId", "focusedChannelId", "epgPrefetchAnchorId", "startupChannelApplied", "playingCatchupProgram", "catchupPlaybackOffsetMs", "retainedPlayingChannel", "guideWindowStart", "guideWindowEnd", "guideScopeKey", "searchOpen", "focusSelectedChannelSignal", "focusEpgSignal", "focusSearchCategorySignal", "focusCategoryRailSignal", "isFullScreen", "fullscreenGuideOpen", "variantPickerChannel", "hudPokeSignal", "quickZapOpen", "isHudVisible", "guideOpenedFromQuickZap", "guideChannel", "channelNumberBuffer", "lastChannelDigitAt", "playerPositionMs", "playerDurationMs", "playerIsPlaying", "playerPlayWhenReady", "playerIsBuffering", "lastPreparedStreamUrl", "lastPreparedIsHls", "lastPreparedHeaders", "lastPreparedCatchupOffsetMs", "playerRetryCount", "Lcom/arflix/tv/ui/screens/tv/live/PlaybackDiagnostic;", "playbackDiagnostic", "", "fsProgress", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LiveTvScreenKt {
    private static final long CatchupSeekStepMs = 30000;
    private static final long CatchupUrlAnchorGranularityMs = 60000;
    private static final int GuideInitialWindowRows = 48;
    private static final int GuideMaxWindowRows = 144;
    private static final int GuidePageRows = 48;
    private static final int GuidePagedLoadStepRows = 192;
    private static final int GuideVisibleFirstRows = 28;
    private static final int GuideVisibleFirstRowsAllChannels = 18;
    private static final kotlin.text.m IptvGroupPipeSpacingRegex = new kotlin.text.m("\\s*\\|\\s*");
    private static final kotlin.text.m IptvGroupWhitespaceRegex = new kotlin.text.m("\\s+");
    private static final String IptvPlaybackUserAgent = "VLC/3.0.20 LibVLC/3.0.20";
    private static final long VisibleGuideFutureWindowMs = 172800000;
    private static final long VisibleGuidePastWindowMs = 172800000;

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Quality.values().length];
            try {
                iArr[Quality.K4.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Quality.FHD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Quality.HD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Quality.SD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[androidx.lifecycle.p.values().length];
            try {
                iArr2[androidx.lifecycle.p.ON_PAUSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[androidx.lifecycle.p.ON_RESUME.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: Multi-variable search skipped. Vars limit reached: 6077 (expected less than 5000) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:1000:0x1eec  */
    /* JADX WARN: Removed duplicated region for block: B:1007:0x1f69  */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:1010:0x1fc3  */
    /* JADX WARN: Removed duplicated region for block: B:1011:0x1fff  */
    /* JADX WARN: Removed duplicated region for block: B:1014:0x2011  */
    /* JADX WARN: Removed duplicated region for block: B:1019:0x2064  */
    /* JADX WARN: Removed duplicated region for block: B:1026:0x2091  */
    /* JADX WARN: Removed duplicated region for block: B:1033:0x20b5  */
    /* JADX WARN: Removed duplicated region for block: B:1035:0x20bf  */
    /* JADX WARN: Removed duplicated region for block: B:1036:0x20c2  */
    /* JADX WARN: Removed duplicated region for block: B:1038:0x20c5  */
    /* JADX WARN: Removed duplicated region for block: B:1039:0x20ca  */
    /* JADX WARN: Removed duplicated region for block: B:1041:0x20ce  */
    /* JADX WARN: Removed duplicated region for block: B:1045:0x20db  */
    /* JADX WARN: Removed duplicated region for block: B:1053:0x20fd  */
    /* JADX WARN: Removed duplicated region for block: B:1058:0x2162  */
    /* JADX WARN: Removed duplicated region for block: B:1061:0x217b  */
    /* JADX WARN: Removed duplicated region for block: B:1064:0x2190  */
    /* JADX WARN: Removed duplicated region for block: B:1067:0x21a5  */
    /* JADX WARN: Removed duplicated region for block: B:1068:0x21b1  */
    /* JADX WARN: Removed duplicated region for block: B:1071:0x21c1  */
    /* JADX WARN: Removed duplicated region for block: B:1074:0x21d8  */
    /* JADX WARN: Removed duplicated region for block: B:1081:0x2210  */
    /* JADX WARN: Removed duplicated region for block: B:1088:0x2283  */
    /* JADX WARN: Removed duplicated region for block: B:1091:0x22af  */
    /* JADX WARN: Removed duplicated region for block: B:1092:0x22b9  */
    /* JADX WARN: Removed duplicated region for block: B:1095:0x22c9  */
    /* JADX WARN: Removed duplicated region for block: B:1098:0x22e0  */
    /* JADX WARN: Removed duplicated region for block: B:1101:0x22f7  */
    /* JADX WARN: Removed duplicated region for block: B:1104:0x230e  */
    /* JADX WARN: Removed duplicated region for block: B:1107:0x2325  */
    /* JADX WARN: Removed duplicated region for block: B:1112:0x2352  */
    /* JADX WARN: Removed duplicated region for block: B:1113:0x2354  */
    /* JADX WARN: Removed duplicated region for block: B:1114:0x2357  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:1126:0x239c  */
    /* JADX WARN: Removed duplicated region for block: B:1129:0x23c9  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:1131:0x23d3  */
    /* JADX WARN: Removed duplicated region for block: B:1138:0x243b  */
    /* JADX WARN: Removed duplicated region for block: B:1141:0x2493  */
    /* JADX WARN: Removed duplicated region for block: B:1142:0x2498  */
    /* JADX WARN: Removed duplicated region for block: B:1149:0x250c  */
    /* JADX WARN: Removed duplicated region for block: B:1152:0x2562  */
    /* JADX WARN: Removed duplicated region for block: B:1153:0x2564  */
    /* JADX WARN: Removed duplicated region for block: B:1160:0x2586  */
    /* JADX WARN: Removed duplicated region for block: B:1167:0x25e1  */
    /* JADX WARN: Removed duplicated region for block: B:1172:0x2618  */
    /* JADX WARN: Removed duplicated region for block: B:1178:0x2635  */
    /* JADX WARN: Removed duplicated region for block: B:1181:0x2640  */
    /* JADX WARN: Removed duplicated region for block: B:1182:0x264e  */
    /* JADX WARN: Removed duplicated region for block: B:1190:0x2670  */
    /* JADX WARN: Removed duplicated region for block: B:1193:0x267b  */
    /* JADX WARN: Removed duplicated region for block: B:1194:0x2687  */
    /* JADX WARN: Removed duplicated region for block: B:1202:0x26a7  */
    /* JADX WARN: Removed duplicated region for block: B:1209:0x2737  */
    /* JADX WARN: Removed duplicated region for block: B:1217:0x27ad  */
    /* JADX WARN: Removed duplicated region for block: B:1220:0x27b4  */
    /* JADX WARN: Removed duplicated region for block: B:1221:0x27b6  */
    /* JADX WARN: Removed duplicated region for block: B:1228:0x27c9  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:1235:0x27fc  */
    /* JADX WARN: Removed duplicated region for block: B:1238:0x2806  */
    /* JADX WARN: Removed duplicated region for block: B:1245:0x2831  */
    /* JADX WARN: Removed duplicated region for block: B:1246:0x2836  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:1254:0x285b  */
    /* JADX WARN: Removed duplicated region for block: B:1256:0x2865  */
    /* JADX WARN: Removed duplicated region for block: B:1265:0x2880  */
    /* JADX WARN: Removed duplicated region for block: B:1277:0x28e5  */
    /* JADX WARN: Removed duplicated region for block: B:1305:0x2a5c  */
    /* JADX WARN: Removed duplicated region for block: B:1308:0x2acc  */
    /* JADX WARN: Removed duplicated region for block: B:1311:0x2ad8  */
    /* JADX WARN: Removed duplicated region for block: B:1312:0x2adc  */
    /* JADX WARN: Removed duplicated region for block: B:1315:0x2aed  */
    /* JADX WARN: Removed duplicated region for block: B:1318:0x2afe  */
    /* JADX WARN: Removed duplicated region for block: B:1322:0x2b13  */
    /* JADX WARN: Removed duplicated region for block: B:1324:0x2b5c  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:1365:0x2cbf  */
    /* JADX WARN: Removed duplicated region for block: B:1372:0x2cf4  */
    /* JADX WARN: Removed duplicated region for block: B:1375:0x2d42  */
    /* JADX WARN: Removed duplicated region for block: B:1376:0x2d49  */
    /* JADX WARN: Removed duplicated region for block: B:1378:0x2d4d  */
    /* JADX WARN: Removed duplicated region for block: B:1380:0x2d66  */
    /* JADX WARN: Removed duplicated region for block: B:1392:0x2dc8  */
    /* JADX WARN: Removed duplicated region for block: B:1395:0x2e0a  */
    /* JADX WARN: Removed duplicated region for block: B:1398:0x2e15  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:1402:0x2e30  */
    /* JADX WARN: Removed duplicated region for block: B:1405:0x2e3b  */
    /* JADX WARN: Removed duplicated region for block: B:1409:0x2e91  */
    /* JADX WARN: Removed duplicated region for block: B:1411:0x2e96  */
    /* JADX WARN: Removed duplicated region for block: B:1414:0x2eb7  */
    /* JADX WARN: Removed duplicated region for block: B:1416:0x2ebc  */
    /* JADX WARN: Removed duplicated region for block: B:1419:0x2ef1  */
    /* JADX WARN: Removed duplicated region for block: B:1423:0x2f04  */
    /* JADX WARN: Removed duplicated region for block: B:1426:0x2f94  */
    /* JADX WARN: Removed duplicated region for block: B:1430:0x2fbf  */
    /* JADX WARN: Removed duplicated region for block: B:1434:0x303d  */
    /* JADX WARN: Removed duplicated region for block: B:1438:0x304d  */
    /* JADX WARN: Removed duplicated region for block: B:1442:0x307c  */
    /* JADX WARN: Removed duplicated region for block: B:1445:0x3087  */
    /* JADX WARN: Removed duplicated region for block: B:1449:0x30a0  */
    /* JADX WARN: Removed duplicated region for block: B:1451:0x30a8  */
    /* JADX WARN: Removed duplicated region for block: B:1456:0x30c7  */
    /* JADX WARN: Removed duplicated region for block: B:1463:0x30ea  */
    /* JADX WARN: Removed duplicated region for block: B:1470:0x3140  */
    /* JADX WARN: Removed duplicated region for block: B:1477:0x31b1  */
    /* JADX WARN: Removed duplicated region for block: B:1482:0x320b  */
    /* JADX WARN: Removed duplicated region for block: B:1489:0x3251  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:1510:0x33c8  */
    /* JADX WARN: Removed duplicated region for block: B:1515:0x33e9  */
    /* JADX WARN: Removed duplicated region for block: B:1520:0x340d  */
    /* JADX WARN: Removed duplicated region for block: B:1525:0x342b  */
    /* JADX WARN: Removed duplicated region for block: B:1530:0x3449  */
    /* JADX WARN: Removed duplicated region for block: B:1535:0x3468  */
    /* JADX WARN: Removed duplicated region for block: B:1540:0x3487  */
    /* JADX WARN: Removed duplicated region for block: B:1545:0x34a6  */
    /* JADX WARN: Removed duplicated region for block: B:1552:0x34ff  */
    /* JADX WARN: Removed duplicated region for block: B:1559:0x356d  */
    /* JADX WARN: Removed duplicated region for block: B:1562:0x3632  */
    /* JADX WARN: Removed duplicated region for block: B:1565:0x363e  */
    /* JADX WARN: Removed duplicated region for block: B:1566:0x3642  */
    /* JADX WARN: Removed duplicated region for block: B:1569:0x3657  */
    /* JADX WARN: Removed duplicated region for block: B:1573:0x366c  */
    /* JADX WARN: Removed duplicated region for block: B:1577:0x3696  */
    /* JADX WARN: Removed duplicated region for block: B:1581:0x36a1  */
    /* JADX WARN: Removed duplicated region for block: B:1588:0x36e2  */
    /* JADX WARN: Removed duplicated region for block: B:1595:0x3713  */
    /* JADX WARN: Removed duplicated region for block: B:1598:0x375a  */
    /* JADX WARN: Removed duplicated region for block: B:1599:0x3761  */
    /* JADX WARN: Removed duplicated region for block: B:1601:0x3765  */
    /* JADX WARN: Removed duplicated region for block: B:1603:0x3778  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:1615:0x37d7  */
    /* JADX WARN: Removed duplicated region for block: B:1618:0x382f  */
    /* JADX WARN: Removed duplicated region for block: B:1620:0x3834  */
    /* JADX WARN: Removed duplicated region for block: B:1628:0x3866  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:1633:0x387f  */
    /* JADX WARN: Removed duplicated region for block: B:1636:0x3893  */
    /* JADX WARN: Removed duplicated region for block: B:1637:0x3898  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:1644:0x38e1  */
    /* JADX WARN: Removed duplicated region for block: B:1647:0x3967  */
    /* JADX WARN: Removed duplicated region for block: B:1651:0x398e  */
    /* JADX WARN: Removed duplicated region for block: B:1655:0x3a10  */
    /* JADX WARN: Removed duplicated region for block: B:1659:0x3a20  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:1663:0x3a4f  */
    /* JADX WARN: Removed duplicated region for block: B:1666:0x3a5a  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:1670:0x3a73  */
    /* JADX WARN: Removed duplicated region for block: B:1672:0x3a7b  */
    /* JADX WARN: Removed duplicated region for block: B:1675:0x3a92  */
    /* JADX WARN: Removed duplicated region for block: B:1678:0x3a9d  */
    /* JADX WARN: Removed duplicated region for block: B:1682:0x3ab6  */
    /* JADX WARN: Removed duplicated region for block: B:1686:0x3ac2  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:1693:0x3b18  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:1700:0x3b89  */
    /* JADX WARN: Removed duplicated region for block: B:1705:0x3bd5  */
    /* JADX WARN: Removed duplicated region for block: B:1712:0x3c19  */
    /* JADX WARN: Removed duplicated region for block: B:1717:0x3c68  */
    /* JADX WARN: Removed duplicated region for block: B:1719:0x3c6d  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:1722:0x3cac A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:1735:0x3dd2  */
    /* JADX WARN: Removed duplicated region for block: B:1742:0x3e80  */
    /* JADX WARN: Removed duplicated region for block: B:1745:0x3efe  */
    /* JADX WARN: Removed duplicated region for block: B:1752:0x3f4d  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0279  */
    /* JADX WARN: Removed duplicated region for block: B:1765:0x3fc9  */
    /* JADX WARN: Removed duplicated region for block: B:1768:0x3fd5  */
    /* JADX WARN: Removed duplicated region for block: B:1769:0x3fd9  */
    /* JADX WARN: Removed duplicated region for block: B:1772:0x3fee  */
    /* JADX WARN: Removed duplicated region for block: B:1776:0x4005  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:1780:0x401f  */
    /* JADX WARN: Removed duplicated region for block: B:1782:0x4027  */
    /* JADX WARN: Removed duplicated region for block: B:1787:0x4043  */
    /* JADX WARN: Removed duplicated region for block: B:1805:0x4092  */
    /* JADX WARN: Removed duplicated region for block: B:1811:0x40ba  */
    /* JADX WARN: Removed duplicated region for block: B:1812:0x40bd  */
    /* JADX WARN: Removed duplicated region for block: B:1815:0x40c5  */
    /* JADX WARN: Removed duplicated region for block: B:1817:0x40cc  */
    /* JADX WARN: Removed duplicated region for block: B:1820:0x40df  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0297  */
    /* JADX WARN: Removed duplicated region for block: B:1830:0x4193  */
    /* JADX WARN: Removed duplicated region for block: B:1837:0x41d7  */
    /* JADX WARN: Removed duplicated region for block: B:1840:0x4205  */
    /* JADX WARN: Removed duplicated region for block: B:1843:0x4210  */
    /* JADX WARN: Removed duplicated region for block: B:1847:0x4251  */
    /* JADX WARN: Removed duplicated region for block: B:1851:0x4263  */
    /* JADX WARN: Removed duplicated region for block: B:1855:0x42a9  */
    /* JADX WARN: Removed duplicated region for block: B:1859:0x42b9  */
    /* JADX WARN: Removed duplicated region for block: B:1863:0x4317  */
    /* JADX WARN: Removed duplicated region for block: B:1867:0x432d  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x02af  */
    /* JADX WARN: Removed duplicated region for block: B:1871:0x43a5  */
    /* JADX WARN: Removed duplicated region for block: B:1875:0x43c1  */
    /* JADX WARN: Removed duplicated region for block: B:1879:0x4431  */
    /* JADX WARN: Removed duplicated region for block: B:1883:0x4441  */
    /* JADX WARN: Removed duplicated region for block: B:1887:0x4493  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:1891:0x44a4  */
    /* JADX WARN: Removed duplicated region for block: B:1895:0x44e6  */
    /* JADX WARN: Removed duplicated region for block: B:1899:0x44f8  */
    /* JADX WARN: Removed duplicated region for block: B:1903:0x4528  */
    /* JADX WARN: Removed duplicated region for block: B:1904:0x4536  */
    /* JADX WARN: Removed duplicated region for block: B:1907:0x4548  */
    /* JADX WARN: Removed duplicated region for block: B:1909:0x4570  */
    /* JADX WARN: Removed duplicated region for block: B:1915:0x45aa  */
    /* JADX WARN: Removed duplicated region for block: B:1918:0x45b1  */
    /* JADX WARN: Removed duplicated region for block: B:1921:0x45b8  */
    /* JADX WARN: Removed duplicated region for block: B:1924:0x45d3  */
    /* JADX WARN: Removed duplicated region for block: B:1925:0x45de  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:1932:0x4625  */
    /* JADX WARN: Removed duplicated region for block: B:1935:0x465f  */
    /* JADX WARN: Removed duplicated region for block: B:1941:0x4694  */
    /* JADX WARN: Removed duplicated region for block: B:1946:0x46c0  */
    /* JADX WARN: Removed duplicated region for block: B:1949:0x46d8  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:1956:0x470e  */
    /* JADX WARN: Removed duplicated region for block: B:1959:0x4744  */
    /* JADX WARN: Removed duplicated region for block: B:1960:0x475c  */
    /* JADX WARN: Removed duplicated region for block: B:1962:0x4779  */
    /* JADX WARN: Removed duplicated region for block: B:1969:0x47e9  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:2003:0x48ef  */
    /* JADX WARN: Removed duplicated region for block: B:2006:0x497b  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:2022:0x4a40  */
    /* JADX WARN: Removed duplicated region for block: B:2025:0x4aa7  */
    /* JADX WARN: Removed duplicated region for block: B:2026:0x4aaf  */
    /* JADX WARN: Removed duplicated region for block: B:2029:0x4ae0  */
    /* JADX WARN: Removed duplicated region for block: B:2033:0x4b02  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:2042:0x282a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:2089:0x163f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:208:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:2095:0x0e83 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:2097:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0315  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x03d0  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x03e6  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x03ed  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0435  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0487  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x04e8  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x051d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x0560  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0579  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x05a7  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x05da  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x060f  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x061c  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0640  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x06c9  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0737  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x077d  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x07b4  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x07cd  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0841  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x0886  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x08a1  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x08a3  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x08b9  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x08fb  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x092c  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x0941  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x094d  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x0963  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x097b  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x0980  */
    /* JADX WARN: Removed duplicated region for block: B:369:0x09fa  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0a3f  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x0a4b  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0a61  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x0a80  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x0abb  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x0ac7  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x0add  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0b05  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0b60  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x0b74  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x0b83  */
    /* JADX WARN: Removed duplicated region for block: B:453:0x0d0f  */
    /* JADX WARN: Removed duplicated region for block: B:456:0x0d3d  */
    /* JADX WARN: Removed duplicated region for block: B:457:0x0d4a  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0d5c  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x0d71  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x0d8b  */
    /* JADX WARN: Removed duplicated region for block: B:467:0x0d98  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x0dab  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x0dc9  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x0dff  */
    /* JADX WARN: Removed duplicated region for block: B:482:0x0e05  */
    /* JADX WARN: Removed duplicated region for block: B:488:0x0e3a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x0e59  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x0e63  */
    /* JADX WARN: Removed duplicated region for block: B:506:0x0e9b  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x0ea8  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:512:0x0ecc  */
    /* JADX WARN: Removed duplicated region for block: B:522:0x0ef5  */
    /* JADX WARN: Removed duplicated region for block: B:524:0x0efb  */
    /* JADX WARN: Removed duplicated region for block: B:529:0x0f16  */
    /* JADX WARN: Removed duplicated region for block: B:533:0x0f27  */
    /* JADX WARN: Removed duplicated region for block: B:537:0x0f38  */
    /* JADX WARN: Removed duplicated region for block: B:539:0x0f3f  */
    /* JADX WARN: Removed duplicated region for block: B:544:0x0f5a  */
    /* JADX WARN: Removed duplicated region for block: B:548:0x0f6b  */
    /* JADX WARN: Removed duplicated region for block: B:556:0x0faa  */
    /* JADX WARN: Removed duplicated region for block: B:558:0x0fb4  */
    /* JADX WARN: Removed duplicated region for block: B:559:0x0fce  */
    /* JADX WARN: Removed duplicated region for block: B:563:0x0fe8  */
    /* JADX WARN: Removed duplicated region for block: B:566:0x101e  */
    /* JADX WARN: Removed duplicated region for block: B:573:0x105f  */
    /* JADX WARN: Removed duplicated region for block: B:575:0x106f  */
    /* JADX WARN: Removed duplicated region for block: B:576:0x1074  */
    /* JADX WARN: Removed duplicated region for block: B:578:0x1077  */
    /* JADX WARN: Removed duplicated region for block: B:581:0x1080  */
    /* JADX WARN: Removed duplicated region for block: B:582:0x1085  */
    /* JADX WARN: Removed duplicated region for block: B:585:0x108b  */
    /* JADX WARN: Removed duplicated region for block: B:586:0x108d  */
    /* JADX WARN: Removed duplicated region for block: B:590:0x10c9  */
    /* JADX WARN: Removed duplicated region for block: B:595:0x1114  */
    /* JADX WARN: Removed duplicated region for block: B:598:0x114c  */
    /* JADX WARN: Removed duplicated region for block: B:599:0x114e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:606:0x1173  */
    /* JADX WARN: Removed duplicated region for block: B:609:0x11e7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:613:0x11f9  */
    /* JADX WARN: Removed duplicated region for block: B:617:0x1239  */
    /* JADX WARN: Removed duplicated region for block: B:620:0x1245  */
    /* JADX WARN: Removed duplicated region for block: B:627:0x127c  */
    /* JADX WARN: Removed duplicated region for block: B:629:0x128d  */
    /* JADX WARN: Removed duplicated region for block: B:630:0x1290  */
    /* JADX WARN: Removed duplicated region for block: B:638:0x12fb  */
    /* JADX WARN: Removed duplicated region for block: B:645:0x1331  */
    /* JADX WARN: Removed duplicated region for block: B:648:0x1345  */
    /* JADX WARN: Removed duplicated region for block: B:656:0x137a  */
    /* JADX WARN: Removed duplicated region for block: B:657:0x1388  */
    /* JADX WARN: Removed duplicated region for block: B:664:0x13bf  */
    /* JADX WARN: Removed duplicated region for block: B:671:0x1410  */
    /* JADX WARN: Removed duplicated region for block: B:674:0x1423  */
    /* JADX WARN: Removed duplicated region for block: B:681:0x1470  */
    /* JADX WARN: Removed duplicated region for block: B:692:0x14ed  */
    /* JADX WARN: Removed duplicated region for block: B:694:0x14f9  */
    /* JADX WARN: Removed duplicated region for block: B:696:0x14fe  */
    /* JADX WARN: Removed duplicated region for block: B:699:0x150b  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:700:0x150e  */
    /* JADX WARN: Removed duplicated region for block: B:702:0x1512  */
    /* JADX WARN: Removed duplicated region for block: B:708:0x1523  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:714:0x1535  */
    /* JADX WARN: Removed duplicated region for block: B:720:0x1549  */
    /* JADX WARN: Removed duplicated region for block: B:722:0x154d  */
    /* JADX WARN: Removed duplicated region for block: B:723:0x1552  */
    /* JADX WARN: Removed duplicated region for block: B:727:0x1589 A[LOOP:6: B:725:0x1583->B:727:0x1589, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:729:0x1595  */
    /* JADX WARN: Removed duplicated region for block: B:730:0x159c  */
    /* JADX WARN: Removed duplicated region for block: B:770:0x163c  */
    /* JADX WARN: Removed duplicated region for block: B:779:0x1702  */
    /* JADX WARN: Removed duplicated region for block: B:784:0x179f  */
    /* JADX WARN: Removed duplicated region for block: B:791:0x17fa  */
    /* JADX WARN: Removed duplicated region for block: B:794:0x181a  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:812:0x18c3  */
    /* JADX WARN: Removed duplicated region for block: B:815:0x18fb  */
    /* JADX WARN: Removed duplicated region for block: B:817:0x1903  */
    /* JADX WARN: Removed duplicated region for block: B:831:0x1959 A[PHI: r173
      0x1959: PHI (r173v57 java.util.Iterator) = (r173v59 java.util.Iterator), (r173v60 java.util.Iterator) binds: [B:830:0x1957, B:826:0x1948] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:894:0x1b9c  */
    /* JADX WARN: Removed duplicated region for block: B:897:0x1bcd  */
    /* JADX WARN: Removed duplicated region for block: B:898:0x1bd6  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:901:0x1be7  */
    /* JADX WARN: Removed duplicated region for block: B:904:0x1bfc  */
    /* JADX WARN: Removed duplicated region for block: B:907:0x1c11  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:910:0x1c2a  */
    /* JADX WARN: Removed duplicated region for block: B:911:0x1c2c  */
    /* JADX WARN: Removed duplicated region for block: B:918:0x1c3f  */
    /* JADX WARN: Removed duplicated region for block: B:921:0x1c73  */
    /* JADX WARN: Removed duplicated region for block: B:922:0x1c86  */
    /* JADX WARN: Removed duplicated region for block: B:925:0x1c9a  */
    /* JADX WARN: Removed duplicated region for block: B:928:0x1cb4  */
    /* JADX WARN: Removed duplicated region for block: B:929:0x1cb6  */
    /* JADX WARN: Removed duplicated region for block: B:936:0x1cd3  */
    /* JADX WARN: Removed duplicated region for block: B:939:0x1cea  */
    /* JADX WARN: Removed duplicated region for block: B:940:0x1cec  */
    /* JADX WARN: Removed duplicated region for block: B:945:0x1cf9  */
    /* JADX WARN: Removed duplicated region for block: B:948:0x1d14  */
    /* JADX WARN: Removed duplicated region for block: B:951:0x1d26  */
    /* JADX WARN: Removed duplicated region for block: B:954:0x1d38  */
    /* JADX WARN: Removed duplicated region for block: B:957:0x1d4a  */
    /* JADX WARN: Removed duplicated region for block: B:960:0x1d5a  */
    /* JADX WARN: Removed duplicated region for block: B:963:0x1d74  */
    /* JADX WARN: Removed duplicated region for block: B:964:0x1d84  */
    /* JADX WARN: Removed duplicated region for block: B:967:0x1d9e  */
    /* JADX WARN: Removed duplicated region for block: B:968:0x1da9  */
    /* JADX WARN: Removed duplicated region for block: B:971:0x1db6  */
    /* JADX WARN: Removed duplicated region for block: B:972:0x1dc4  */
    /* JADX WARN: Removed duplicated region for block: B:975:0x1dd4  */
    /* JADX WARN: Removed duplicated region for block: B:976:0x1de2  */
    /* JADX WARN: Removed duplicated region for block: B:979:0x1df4  */
    /* JADX WARN: Removed duplicated region for block: B:986:0x1e35  */
    /* JADX WARN: Removed duplicated region for block: B:993:0x1e96  */
    /* JADX WARN: Removed duplicated region for block: B:996:0x1ecc  */
    /* JADX WARN: Removed duplicated region for block: B:997:0x1ed8  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x011b  */
    /* JADX WARN: Type inference failed for: r12v40, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r12v43, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r12v44, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r12v47, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r12v48 */
    /* JADX WARN: Type inference failed for: r12v69 */
    /* JADX WARN: Type inference failed for: r12v70 */
    /* JADX WARN: Type inference failed for: r12v71 */
    /* JADX WARN: Type inference failed for: r12v72 */
    /* JADX WARN: Type inference failed for: r13v204 */
    /* JADX WARN: Type inference failed for: r13v246, types: [java.lang.Object, ka.v1[]] */
    /* JADX WARN: Type inference failed for: r13v258, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r13v262 */
    /* JADX WARN: Type inference failed for: r13v263 */
    /* JADX WARN: Type inference failed for: r13v264 */
    /* JADX WARN: Type inference failed for: r13v85 */
    /* JADX WARN: Type inference failed for: r177v1, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r28v24, types: [androidx.compose.runtime.Composer] */
    /* JADX WARN: Type inference failed for: r2v117, types: [boolean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void LiveTvScreen(com.arflix.tv.ui.screens.tv.TvViewModel r173, com.arflix.tv.data.model.Profile r174, java.lang.String r175, java.lang.String r176, r7.l<? super java.lang.Boolean, x6.t0> r177, r7.a<x6.t0> r178, r7.a<x6.t0> r179, r7.a<x6.t0> r180, r7.a<x6.t0> r181, r7.a<x6.t0> r182, r7.a<x6.t0> r183, r7.a<x6.t0> r184, androidx.compose.runtime.Composer r185, int r186, int r187, int r188) {
        /*
            Method dump skipped, instruction units count: 19222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.LiveTvScreen(com.arflix.tv.ui.screens.tv.TvViewModel, com.arflix.tv.data.model.Profile, java.lang.String, java.lang.String, r7.l, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, r7.a, androidx.compose.runtime.Composer, int, int, int):void");
    }

    private static final void LiveTvScreen$commitFocusedChannel(x6.x[] xVarArr, ka.v1[] v1VarArr, ka.k0 k0Var, MutableState<String> mutableState, Map<String, String> map, MutableState<String> mutableState2, MutableState<String> mutableState3, EnrichedChannel enrichedChannel) {
        xVarArr[0] = new x6.x(enrichedChannel.getId(), LiveTvScreen$lambda$13(mutableState));
        ka.v1 v1Var = v1VarArr[0];
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        v1VarArr[0] = ka.m0.p(k0Var, null, 0, new LiveTvScreenKt$LiveTvScreen$commitFocusedChannel$1(xVarArr, map, mutableState2, mutableState3, null), 3);
    }

    private static final void LiveTvScreen$cycleCategory(MutableState<EnrichedChannels> mutableState, MutableState<String> mutableState2, boolean z) {
        int size;
        List<String> listLiveTvScreen$getAvailableCategoryIds = LiveTvScreen$getAvailableCategoryIds(mutableState.getValue().getTree());
        if (listLiveTvScreen$getAvailableCategoryIds.isEmpty()) {
            return;
        }
        int iIndexOf = listLiveTvScreen$getAvailableCategoryIds.indexOf(LiveTvScreen$lambda$13(mutableState2));
        if (z) {
            size = (iIndexOf + 1) % listLiveTvScreen$getAvailableCategoryIds.size();
        } else {
            size = (listLiveTvScreen$getAvailableCategoryIds.size() + (iIndexOf - 1)) % listLiveTvScreen$getAvailableCategoryIds.size();
        }
        String str = (String) kotlin.collections.x.p0(size, listLiveTvScreen$getAvailableCategoryIds);
        if (str == null) {
            str = TtmlNode.COMBINE_ALL;
        }
        mutableState2.setValue(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$exitFullScreenPlayback(ka.k0 k0Var, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<Boolean> mutableState3, MutableState<Boolean> mutableState4, MutableState<Integer> mutableState5, MutableLongState mutableLongState, Map<String, String> map, Map<String, Integer> map2, List<EnrichedChannel> list, MutableState<String> mutableState6, MutableState<String> mutableState7, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState<LiveTvFocusZone> mutableState8, MutableIntState mutableIntState3, FocusRequester focusRequester) {
        String strLiveTvScreen$lambda$63 = LiveTvScreen$lambda$63(mutableState);
        if (strLiveTvScreen$lambda$63 == null) {
            strLiveTvScreen$lambda$63 = LiveTvScreen$lambda$68(mutableState2);
        }
        LiveTvScreen$lambda$143(mutableState3, false);
        LiveTvScreen$lambda$140(mutableState4, false);
        LiveTvScreen$lambda$156(mutableState5, LiveTvScreen$lambda$155(mutableState5) + 1);
        ka.m0.p(k0Var, null, 0, new LiveTvScreenKt$LiveTvScreen$exitFullScreenPlayback$1(strLiveTvScreen$lambda$63, mutableState2, mutableState, mutableLongState, map, map2, list, mutableState6, mutableState7, mutableIntState, mutableIntState2, mutableState8, mutableIntState3, focusRequester, null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$focusChannelList(MutableState<String> mutableState, MutableState<String> mutableState2, MutableLongState mutableLongState, Map<String, String> map, Map<String, Integer> map2, List<EnrichedChannel> list, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState<LiveTvFocusZone> mutableState5, MutableIntState mutableIntState3, FocusRequester focusRequester, String str) {
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        if (str != null) {
            mutableState.setValue(str);
            mutableState3.setValue(str);
            map.put(LiveTvScreen$lambda$13(mutableState4), str);
            Integer num = map2.get(str);
            if (num != null) {
                int intValue = mutableIntState.getIntValue();
                int intValue2 = mutableIntState2.getIntValue();
                int iIntValue = num.intValue();
                if (intValue > iIntValue || iIntValue >= intValue2) {
                    LiveTvScreen$setGuideWindow(list, mutableIntState, mutableIntState2, guideWindowAround(num.intValue(), list.size()));
                }
            }
        }
        mutableState5.setValue(LiveTvFocusZone.CHANNEL_LIST);
        mutableIntState3.setIntValue(mutableIntState3.getIntValue() + 1);
        try {
            focusRequester.requestFocus();
        } catch (Throwable unused) {
        }
    }

    public static /* synthetic */ void LiveTvScreen$focusChannelList$default(MutableState mutableState, MutableState mutableState2, MutableLongState mutableLongState, Map map, Map map2, List list, MutableState mutableState3, MutableState mutableState4, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState5, MutableIntState mutableIntState3, FocusRequester focusRequester, String str, int i10, Object obj) {
        String str2;
        if ((i10 & 8192) != 0) {
            String strLiveTvScreen$lambda$68 = LiveTvScreen$lambda$68(mutableState);
            if (strLiveTvScreen$lambda$68 == null) {
                strLiveTvScreen$lambda$68 = LiveTvScreen$lambda$63(mutableState2);
            }
            str2 = strLiveTvScreen$lambda$68;
        } else {
            str2 = str;
        }
        LiveTvScreen$focusChannelList(mutableState, mutableState2, mutableLongState, map, map2, list, mutableState3, mutableState4, mutableIntState, mutableIntState2, mutableState5, mutableIntState3, focusRequester, str2);
    }

    private static final void LiveTvScreen$focusEpg(Map<String, String> map, Map<String, Integer> map2, List<EnrichedChannel> list, MutableLongState mutableLongState, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState<LiveTvFocusZone> mutableState4, MutableIntState mutableIntState3, FocusRequester focusRequester, String str) {
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        mutableState.setValue(str);
        mutableState2.setValue(str);
        map.put(LiveTvScreen$lambda$13(mutableState3), str);
        Integer num = map2.get(str);
        if (num != null) {
            int intValue = mutableIntState.getIntValue();
            int intValue2 = mutableIntState2.getIntValue();
            int iIntValue = num.intValue();
            if (intValue > iIntValue || iIntValue >= intValue2) {
                LiveTvScreen$setGuideWindow(list, mutableIntState, mutableIntState2, guideWindowAround(num.intValue(), list.size()));
            }
        }
        mutableState4.setValue(LiveTvFocusZone.EPG);
        mutableIntState3.setIntValue(mutableIntState3.getIntValue() + 1);
        try {
            focusRequester.requestFocus();
        } catch (Throwable unused) {
        }
    }

    private static final void LiveTvScreen$focusPlaylistSearch(MutableLongState mutableLongState, MutableState<LiveTvFocusZone> mutableState, MutableIntState mutableIntState, FocusRequester focusRequester) {
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        mutableState.setValue(LiveTvFocusZone.CATEGORY_LIST);
        mutableIntState.setIntValue(mutableIntState.getIntValue() + 1);
        try {
            focusRequester.requestFocus();
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$focusProviderSwitcher(List<TvProviderFilter> list, MutableLongState mutableLongState, MutableState<LiveTvFocusZone> mutableState, MutableIntState mutableIntState, FocusRequester focusRequester, FocusRequester focusRequester2) {
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        if (list.size() <= 1) {
            LiveTvScreen$focusPlaylistSearch(mutableLongState, mutableState, mutableIntState, focusRequester);
            return;
        }
        mutableState.setValue(LiveTvFocusZone.PROVIDER_SWITCHER);
        try {
            focusRequester2.requestFocus();
        } catch (Throwable unused) {
        }
    }

    private static final List<String> LiveTvScreen$getAvailableCategoryIds(LiveCategoryTree liveCategoryTree) {
        ArrayList arrayList = new ArrayList();
        for (LiveCategory liveCategory : liveCategoryTree.getTop()) {
            if (liveCategory.getCount() > 0 || kotlin.jvm.internal.p.a(liveCategory.getId(), TtmlNode.COMBINE_ALL)) {
                arrayList.add(liveCategory.getId());
                if (kotlin.jvm.internal.p.a(liveCategory.getId(), TtmlNode.COMBINE_ALL)) {
                    for (LiveCategory liveCategory2 : liveCategory.getChildren()) {
                        if (liveCategory2.getCount() > 0) {
                            arrayList.add(liveCategory2.getId());
                        }
                    }
                }
            }
        }
        for (LiveCategory liveCategory3 : liveCategoryTree.getGlobal().getCategories()) {
            if (liveCategory3.getCount() > 0) {
                arrayList.add(liveCategory3.getId());
            }
        }
        for (LiveCategory liveCategory4 : liveCategoryTree.getCountries().getCategories()) {
            if (liveCategory4.getCount() > 0) {
                arrayList.add(liveCategory4.getId());
                for (LiveCategory liveCategory5 : liveCategory4.getChildren()) {
                    if (liveCategory5.getCount() > 0) {
                        arrayList.add(liveCategory5.getId());
                    }
                }
            }
        }
        for (LiveCategory liveCategory6 : liveCategoryTree.getAdult().getCategories()) {
            if (liveCategory6.getCount() > 0) {
                arrayList.add(liveCategory6.getId());
            }
        }
        return kotlin.collections.x.g0(arrayList);
    }

    private static final IptvNowNext LiveTvScreen$guideForChannel(State<TvUiState> state, EnrichedChannel enrichedChannel) {
        if (enrichedChannel == null) {
            return null;
        }
        return LiveTvScreen$lambda$7(state).getSnapshot().getNowNext().get(enrichedChannel.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$handleChannelNumberDigit(MutableState<EnrichedChannels> mutableState, MutableState<Long> mutableState2, MutableState<String> mutableState3, Map<String, String> map, MutableLongState mutableLongState, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<String> mutableState6, MutableState<IptvProgram> mutableState7, MutableLongState mutableLongState2, MutableState<Boolean> mutableState8, MutableState<String> mutableState9, Map<String, Integer> map2, List<EnrichedChannel> list, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState<LiveTvFocusZone> mutableState10, MutableIntState mutableIntState3, FocusRequester focusRequester, MutableState<Integer> mutableState11, int i10) {
        Object next;
        long jCurrentTimeMillis = System.currentTimeMillis();
        mutableState3.setValue(kotlin.text.o.J0(4, (jCurrentTimeMillis - LiveTvScreen$lambda$185(mutableState2) > 1500 ? "" : LiveTvScreen$lambda$182(mutableState3)) + i10));
        LiveTvScreen$lambda$186(mutableState2, jCurrentTimeMillis);
        Iterator<T> it = mutableState.getValue().getAll().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (kotlin.jvm.internal.p.a(String.valueOf(((EnrichedChannel) next).getNumber()), LiveTvScreen$lambda$182(mutableState3))) {
                break;
            }
        }
        EnrichedChannel enrichedChannel = (EnrichedChannel) next;
        if (enrichedChannel == null) {
            return true;
        }
        LiveTvScreen$tuneChannelNumber(map, mutableLongState, mutableState4, mutableState5, mutableState6, mutableState7, mutableLongState2, mutableState8, mutableState9, map2, list, mutableIntState, mutableIntState2, mutableState10, mutableIntState3, focusRequester, mutableState11, enrichedChannel);
        mutableState3.setValue("");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$isGuideUserNavigating(MutableLongState mutableLongState) {
        return System.currentTimeMillis() - mutableLongState.getLongValue() < 2500;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$0$0(boolean z) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$102$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$103(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long LiveTvScreen$lambda$11(State<Long> state) {
        return state.getValue().longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$114$1$0(Set set, EnrichedChannel enrichedChannel) {
        return set.contains(enrichedChannel.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$114$1$1(Map map, EnrichedChannel enrichedChannel) {
        return map.containsKey(enrichedChannel.getId());
    }

    private static final void LiveTvScreen$lambda$114$1$addChannel(List<String> list, EnrichedChannel enrichedChannel) {
        String id;
        if (enrichedChannel == null || (id = enrichedChannel.getId()) == null || list.contains(id)) {
            return;
        }
        list.add(id);
    }

    private static final void LiveTvScreen$lambda$114$1$addGuideFirst(List<EnrichedChannel> list, List<String> list2, int i10) {
        EnrichedChannel enrichedChannel = (EnrichedChannel) kotlin.collections.x.p0(i10, list);
        if (enrichedChannel != null && hasGuideIdentity(enrichedChannel)) {
            LiveTvScreen$lambda$114$1$addChannel(list2, enrichedChannel);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$12$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TtmlNode.COMBINE_ALL, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$123$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$124(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LiveTvScreen$lambda$125(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$13(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$138$0(String str) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(str != null), null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$139(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LiveTvScreen$lambda$140(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$142(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$lambda$143(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final EnrichedChannel LiveTvScreen$lambda$145(MutableState<EnrichedChannel> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LiveTvScreen$lambda$148$0(final r7.l lVar, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$148$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                lVar.invoke(Boolean.FALSE);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$15$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(TtmlNode.COMBINE_ALL, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LiveTvScreen$lambda$155(MutableState<Integer> mutableState) {
        return mutableState.getValue().intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$lambda$156(MutableState<Integer> mutableState, int i10) {
        mutableState.setValue(Integer.valueOf(i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$158(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LiveTvScreen$lambda$159(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$16(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$161(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LiveTvScreen$lambda$162(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean LiveTvScreen$lambda$164(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LiveTvScreen$lambda$165(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final EnrichedChannel LiveTvScreen$lambda$167(MutableState<EnrichedChannel> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LiveTvScreen$lambda$173$0(boolean z, final Activity activity, MutableState mutableState, DisposableEffectScope disposableEffectScope) {
        if (!z || !LiveTvScreen$lambda$139(mutableState)) {
            return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$173$0$$inlined$onDispose$1
                @Override // androidx.compose.runtime.DisposableEffectResult
                public void dispose() {
                }
            };
        }
        final Integer numValueOf = activity != null ? Integer.valueOf(activity.getRequestedOrientation()) : null;
        if (activity != null) {
            activity.setRequestedOrientation(6);
        }
        final Window window = activity != null ? activity.getWindow() : null;
        if (window != null) {
            androidx.core.view.x0 x0Var = new androidx.core.view.x0(window.getDecorView());
            int i10 = Build.VERSION.SDK_INT;
            k2.c t3Var = i10 >= 30 ? new t3(window, x0Var) : i10 >= 26 ? new s3(window, x0Var) : new r3(window, x0Var);
            t3Var.D();
            t3Var.u();
        }
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$173$0$$inlined$onDispose$2
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                Integer num = numValueOf;
                if (num != null) {
                    activity.setRequestedOrientation(num.intValue());
                }
                Window window2 = window;
                if (window2 != null) {
                    androidx.core.view.x0 x0Var2 = new androidx.core.view.x0(window2.getDecorView());
                    int i11 = Build.VERSION.SDK_INT;
                    (i11 >= 30 ? new t3(window2, x0Var2) : i11 >= 26 ? new s3(window2, x0Var2) : new r3(window2, x0Var2)).E();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$182(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final long LiveTvScreen$lambda$185(MutableState<Long> mutableState) {
        return mutableState.getValue().longValue();
    }

    private static final void LiveTvScreen$lambda$186(MutableState<Long> mutableState, long j10) {
        mutableState.setValue(Long.valueOf(j10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LiveTvScreen$lambda$196$0(final ExoPlayer exoPlayer, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$196$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                exoPlayer.release();
            }
        };
    }

    private static final boolean LiveTvScreen$lambda$204(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$lambda$205(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean LiveTvScreen$lambda$207(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$lambda$208(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean LiveTvScreen$lambda$210(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$lambda$211(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LiveTvScreen$lambda$213$0(final androidx.lifecycle.y yVar, ExoPlayer exoPlayer, TvViewModel tvViewModel, MutableState mutableState, State state, DisposableEffectScope disposableEffectScope) {
        final androidx.activity.result.e eVar = new androidx.activity.result.e(exoPlayer, tvViewModel, mutableState, state, 1);
        yVar.getLifecycle().addObserver(eVar);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$213$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                yVar.getLifecycle().removeObserver(eVar);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$lambda$213$0$0(ExoPlayer exoPlayer, TvViewModel tvViewModel, MutableState mutableState, State state, androidx.lifecycle.y yVar, androidx.lifecycle.p pVar) {
        int i10 = WhenMappings.$EnumSwitchMapping$1[pVar.ordinal()];
        if (i10 == 1) {
            exoPlayer.pause();
            return;
        }
        if (i10 != 2) {
            return;
        }
        if (LiveTvScreen$lambda$63(mutableState) != null) {
            exoPlayer.play();
        }
        if (!LiveTvScreen$lambda$8(state).isConfigured() || LiveTvScreen$lambda$8(state).getSnapshot().getChannels().isEmpty() || tvViewModel.getIptvRepository().cachedEpgAgeMs() <= 21600000) {
            return;
        }
        tvViewModel.refresh(false, false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$215(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$218(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void LiveTvScreen$lambda$219(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<String, String> LiveTvScreen$lambda$221(MutableState<Map<String, String>> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState LiveTvScreen$lambda$23$0() {
        return SnapshotIntStateKt.mutableIntStateOf(GuideMaxWindowRows);
    }

    private static final PlaybackDiagnostic LiveTvScreen$lambda$230(MutableState<PlaybackDiagnostic> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$238$0(String str, MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
        if (LiveTvScreen$lambda$63(mutableState) != null || str != null) {
            LiveTvScreen$lambda$140(mutableState2, true);
            LiveTvScreen$lambda$156(mutableState3, LiveTvScreen$lambda$155(mutableState3) + 1);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.media3.common.Player$Listener, com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$40$1$listener$1] */
    public static final DisposableEffectResult LiveTvScreen$lambda$240$0(final ExoPlayer exoPlayer, final MutableState mutableState, final MutableState mutableState2, final EnrichedChannel enrichedChannel, final long j10, final TvViewModel tvViewModel, final Context context, final ka.k0 k0Var, final MutableState mutableState3, final MutableState mutableState4, final MutableIntState mutableIntState, final MutableState mutableState5, final MutableState mutableState6, final MutableLongState mutableLongState, final Map map, final OkHttpDataSource.Factory factory, final MutableLongState mutableLongState2, DisposableEffectScope disposableEffectScope) {
        final ?? r02 = new Player.Listener() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$40$1$listener$1
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
            public void onIsLoadingChanged(boolean isLoading) {
                if (exoPlayer.getPlaybackState() == 2 || (isLoading && !exoPlayer.isPlaying())) {
                    LiveTvScreenKt.LiveTvScreen$lambda$211(mutableState, true);
                } else if (exoPlayer.getPlaybackState() == 3) {
                    LiveTvScreenKt.LiveTvScreen$lambda$211(mutableState, false);
                }
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
            public final /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j11) {
                androidx.media3.common.j.l(this, j11);
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
            public void onPlaybackStateChanged(int playbackState) {
                LiveTvScreenKt.LiveTvScreen$lambda$211(mutableState, playbackState == 2);
                if (playbackState == 3) {
                    mutableState2.setValue(null);
                    LiveTvScreenKt.LiveTvScreen$lambda$211(mutableState, false);
                }
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlaybackSuppressionReasonChanged(int i10) {
                androidx.media3.common.j.s(this, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlayerError(PlaybackException error) {
                int size;
                int i10;
                Map<String, String> mapLiveTvScreen$lambda$221;
                LiveTvScreenKt.LiveTvScreen$lambda$211(mutableState, false);
                String strLiveTvScreen$lambda$215 = LiveTvScreenKt.LiveTvScreen$lambda$215(mutableState3);
                if (strLiveTvScreen$lambda$215 == null) {
                    return;
                }
                boolean zLiveTvScreen$lambda$218 = LiveTvScreenKt.LiveTvScreen$lambda$218(mutableState4);
                int intValue = mutableIntState.getIntValue() + 1;
                mutableIntState.setIntValue(intValue);
                EnrichedChannel enrichedChannel2 = enrichedChannel;
                IptvChannel source = enrichedChannel2 != null ? enrichedChannel2.getSource() : null;
                IptvProgram iptvProgramLiveTvScreen$lambda$77 = LiveTvScreenKt.LiveTvScreen$lambda$77(mutableState5);
                IptvProgram iptvProgramShiftedForCatchup = iptvProgramLiveTvScreen$lambda$77 != null ? LiveTvScreenKt.shiftedForCatchup(iptvProgramLiveTvScreen$lambda$77, j10) : null;
                if (source == null || iptvProgramLiveTvScreen$lambda$77 == null) {
                    size = 0;
                } else {
                    size = tvViewModel.getIptvRepository().getCatchupUrlCandidates(source, iptvProgramShiftedForCatchup == null ? iptvProgramLiveTvScreen$lambda$77 : iptvProgramShiftedForCatchup).size();
                }
                if (iptvProgramLiveTvScreen$lambda$77 != null) {
                    int i11 = size - 1;
                    if (i11 < 0) {
                        i11 = 0;
                    }
                    if (i11 > 2) {
                        i11 = 2;
                    }
                    i10 = i11;
                } else {
                    i10 = 3;
                }
                if (intValue <= i10) {
                    if (source == null || (mapLiveTvScreen$lambda$221 = source.getRequestHeaders()) == null) {
                        mapLiveTvScreen$lambda$221 = LiveTvScreenKt.LiveTvScreen$lambda$221(mutableState6);
                    }
                    ka.m0.p(k0Var, null, 0, new LiveTvScreenKt$LiveTvScreen$40$1$listener$1$onPlayerError$1(intValue, error, size, context, i10, mapLiveTvScreen$lambda$221, source, tvViewModel, iptvProgramShiftedForCatchup, iptvProgramLiveTvScreen$lambda$77, strLiveTvScreen$lambda$215, zLiveTvScreen$lambda$218, mutableState2, mutableLongState, map, factory, j10, exoPlayer, enrichedChannel, mutableState3, mutableState4, mutableState6, mutableState5, mutableLongState2, mutableState, mutableIntState, null), 3);
                    return;
                }
                mutableState2.setValue(new PlaybackDiagnostic(context.getString(R.string.live_diag_playback_failed), androidx.compose.foundation.c.t(error.getErrorCodeName(), ": ", LiveTvScreenKt.classifyPlaybackError(error)), PlaybackDiagnosticSeverity.Error));
                PrintStream printStream = System.err;
                int i12 = error.errorCode;
                String errorCodeName = error.getErrorCodeName();
                Object objHttpResponseCode = LiveTvScreenKt.httpResponseCode(error);
                if (objHttpResponseCode == null) {
                    objHttpResponseCode = "-";
                }
                String strRedactPlaybackUrl = LiveTvScreenKt.redactPlaybackUrl(strLiveTvScreen$lambda$215);
                StringBuilder sbO = androidx.fragment.app.a2.o("[IPTV] Live playback failed after retries code=", i12, " name=", errorCodeName, " status=");
                sbO.append(objHttpResponseCode);
                sbO.append(" attempts=");
                sbO.append(i10);
                sbO.append(" candidates=");
                sbO.append(size);
                sbO.append(" url=");
                sbO.append(strRedactPlaybackUrl);
                printStream.println(sbO.toString());
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
            public final /* synthetic */ void onSeekBackIncrementChanged(long j11) {
                androidx.media3.common.j.B(this, j11);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onSeekForwardIncrementChanged(long j11) {
                androidx.media3.common.j.C(this, j11);
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
        exoPlayer.addListener(r02);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$240$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                exoPlayer.removeListener(r02);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$243$0(MutableState mutableState) {
        LiveTvScreen$lambda$125(mutableState, false);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$244$0(MutableState mutableState) {
        mutableState.setValue(null);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$245$0(MutableState mutableState) {
        LiveTvScreen$lambda$143(mutableState, false);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$246$0(MutableState mutableState, ExoPlayer exoPlayer, MutableState mutableState2, MutableLongState mutableLongState, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, ka.k0 k0Var, MutableState mutableState7, MutableState mutableState8, MutableLongState mutableLongState2, Map map, Map map2, List list, MutableState mutableState9, MutableState mutableState10, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState11, MutableIntState mutableIntState3, FocusRequester focusRequester) {
        if (LiveTvScreen$lambda$77(mutableState) != null) {
            LiveTvScreen$returnCatchupToLive(exoPlayer, mutableState, mutableState2, mutableLongState, mutableState3, mutableState4, mutableState5, mutableState6);
        } else {
            LiveTvScreen$exitFullScreenPlayback(k0Var, mutableState2, mutableState7, mutableState3, mutableState8, mutableState6, mutableLongState2, map, map2, list, mutableState9, mutableState10, mutableIntState, mutableIntState2, mutableState11, mutableIntState3, focusRequester);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$247$0(r7.a aVar) {
        aVar.invoke();
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$0$0(boolean z, int i10, MutableState mutableState, MutableIntState mutableIntState) {
        mutableState.setValue(LiveTvFocusZone.TOPBAR);
        mutableIntState.setIntValue(qb.d.n(AppTopBarKt.topBarSelectedIndex(SidebarItem.TV, z), 0, i10));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$1$0$0(MutableLongState mutableLongState, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, String str) {
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        mutableState.setValue(str);
        mutableState2.setValue(TtmlNode.COMBINE_ALL);
        mutableState3.setValue(null);
        mutableState4.setValue(null);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$1$1$0(MutableLongState mutableLongState, MutableState mutableState, MutableIntState mutableIntState, FocusRequester focusRequester) {
        LiveTvScreen$focusPlaylistSearch(mutableLongState, mutableState, mutableIntState, focusRequester);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$1$10$0(TvViewModel tvViewModel, String str) {
        tvViewModel.toggleFavoriteChannel(str);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LiveTvScreen$lambda$251$1$11$0(Map map, EnrichedChannel enrichedChannel) {
        return LiveTvEnhancementsKt.variantCountFor(enrichedChannel, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$1$12$0(Map map, MutableLongState mutableLongState, MutableState mutableState, EnrichedChannel enrichedChannel) {
        LiveTvScreen$openVariantPicker(map, mutableLongState, mutableState, enrichedChannel);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$1$13$0(MutableLongState mutableLongState, MutableState mutableState, MutableIntState mutableIntState, FocusRequester focusRequester) {
        LiveTvScreen$focusPlaylistSearch(mutableLongState, mutableState, mutableIntState, focusRequester);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$1$14$0(Map map, Map map2, List list, MutableLongState mutableLongState, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState4, MutableIntState mutableIntState3, FocusRequester focusRequester, EnrichedChannel enrichedChannel) {
        LiveTvScreen$focusEpg(map, map2, list, mutableLongState, mutableState, mutableState2, mutableState3, mutableIntState, mutableIntState2, mutableState4, mutableIntState3, focusRequester, enrichedChannel.getId());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$1$15$0(MutableState mutableState, MutableState mutableState2, MutableLongState mutableLongState, Map map, Map map2, List list, MutableState mutableState3, MutableState mutableState4, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState5, MutableIntState mutableIntState3, FocusRequester focusRequester, EnrichedChannel enrichedChannel) {
        String strLiveTvScreen$lambda$68;
        if ((enrichedChannel == null || (strLiveTvScreen$lambda$68 = enrichedChannel.getId()) == null) && (strLiveTvScreen$lambda$68 = LiveTvScreen$lambda$68(mutableState)) == null) {
            strLiveTvScreen$lambda$68 = LiveTvScreen$lambda$63(mutableState2);
        }
        LiveTvScreen$focusChannelList(mutableState, mutableState2, mutableLongState, map, map2, list, mutableState3, mutableState4, mutableIntState, mutableIntState2, mutableState5, mutableIntState3, focusRequester, strLiveTvScreen$lambda$68);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$1$3$0$0(EnrichedChannel enrichedChannel, Map map, MutableLongState mutableLongState, MutableState mutableState) {
        LiveTvScreen$openVariantPicker(map, mutableLongState, mutableState, enrichedChannel);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$1$4$0(TvViewModel tvViewModel, String str) {
        tvViewModel.toggleFavoriteChannel(str);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$1$5$0(MutableLongState mutableLongState, MutableState mutableState, String str) {
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        mutableState.setValue(str);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$1$6$0(MutableState mutableState) {
        LiveTvScreen$lambda$125(mutableState, true);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$1$7$0(MutableState mutableState, Map map, MutableState mutableState2, Map map2, MutableLongState mutableLongState, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableState mutableState8, MutableLongState mutableLongState2, MutableState mutableState9, MutableState mutableState10, EnrichedChannel enrichedChannel, IptvProgram iptvProgram) {
        mutableState.setValue(LiveTvFocusZone.CHANNEL_LIST);
        LiveTvScreen$selectChannel(map, mutableState2, map2, mutableLongState, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableState8, mutableLongState2, mutableState9, mutableState10, enrichedChannel);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$1$8$0(List list, Map map, MutableLongState mutableLongState, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableLongState mutableLongState2, MutableState mutableState6, Map map2, List list2, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState7, MutableIntState mutableIntState3, FocusRequester focusRequester, EnrichedChannel enrichedChannel, IptvProgram iptvProgram) {
        LiveTvScreen$playProgramInMini(list, map, mutableLongState, mutableState, mutableState2, mutableState3, mutableState4, mutableState5, mutableLongState2, mutableState6, map2, list2, mutableIntState, mutableIntState2, mutableState7, mutableIntState3, focusRequester, enrichedChannel, iptvProgram);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$1$9$0(x6.x[] xVarArr, ka.v1[] v1VarArr, ka.k0 k0Var, MutableState mutableState, Map map, MutableState mutableState2, MutableState mutableState3, EnrichedChannel enrichedChannel) {
        LiveTvScreen$commitFocusedChannel(xVarArr, v1VarArr, k0Var, mutableState, map, mutableState2, mutableState3, enrichedChannel);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlayerView LiveTvScreen$lambda$251$10$0$0(ExoPlayer exoPlayer, Context context) {
        PlayerView playerView = new PlayerView(context);
        playerView.setKeepScreenOn(true);
        playerView.setPlayer(exoPlayer);
        playerView.setUseController(false);
        playerView.setKeepContentOnPlayerReset(true);
        return playerView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$1$0(ExoPlayer exoPlayer, PlayerView playerView) {
        playerView.setKeepScreenOn(true);
        if (playerView.getPlayer() != exoPlayer) {
            playerView.setPlayer(exoPlayer);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$10$0(EnrichedChannel enrichedChannel, MutableState mutableState, MutableLongState mutableLongState, ExoPlayer exoPlayer, long j10, MutableLongState mutableLongState2, MutableLongState mutableLongState3, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, Map map, OkHttpDataSource.Factory factory, Context context, MutableLongState mutableLongState4, MutableState mutableState7, MutableIntState mutableIntState, MutableState mutableState8) {
        IptvChannel source;
        if (LiveTvScreen$lambda$77(mutableState) != null) {
            LiveTvScreen$seekCatchupBy(exoPlayer, j10, enrichedChannel, mutableState, mutableLongState2, mutableLongState3, mutableState2, mutableLongState, mutableState3, -mutableLongState.getLongValue());
        } else {
            String strLiveTvScreen$lambda$215 = LiveTvScreen$lambda$215(mutableState4);
            if (strLiveTvScreen$lambda$215 != null) {
                LiveTvScreen$prepareStream$default(map, factory, j10, exoPlayer, context, enrichedChannel, mutableState4, mutableState5, mutableState6, mutableState, mutableLongState4, mutableState7, mutableIntState, mutableState8, mutableLongState3, strLiveTvScreen$lambda$215, LiveTvScreen$lambda$218(mutableState5), LiveTvScreen$lambda$221(mutableState6), true, 0L, (enrichedChannel == null || (source = enrichedChannel.getSource()) == null) ? null : source.getDrmInfo(), true, 524288, null);
            }
            LiveTvScreen$lambda$156(mutableState2, LiveTvScreen$lambda$155(mutableState2) + 1);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$11$0(ExoPlayer exoPlayer, MutableState mutableState, MutableState mutableState2, MutableLongState mutableLongState, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6) {
        LiveTvScreen$returnCatchupToLive(exoPlayer, mutableState, mutableState2, mutableLongState, mutableState3, mutableState4, mutableState5, mutableState6);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$12$0(IptvNowNext iptvNowNext, EnrichedChannel enrichedChannel, ExoPlayer exoPlayer, MutableState mutableState, MutableLongState mutableLongState, long j10, MutableLongState mutableLongState2, MutableLongState mutableLongState3, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, long j11) {
        LiveTvScreen$seekToPosition(iptvNowNext, enrichedChannel, exoPlayer, mutableState, mutableLongState, j10, mutableLongState2, mutableLongState3, mutableState2, mutableState3, mutableState4, mutableState5, j11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$13$0(MutableState mutableState, MutableState mutableState2) {
        LiveTvScreen$lambda$159(mutableState, true);
        LiveTvScreen$lambda$162(mutableState2, false);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$14$0(MutableState mutableState, boolean z) {
        LiveTvScreen$lambda$162(mutableState, z);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$15$0(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4) {
        LiveTvScreen$lambda$143(mutableState, false);
        if (LiveTvScreen$lambda$164(mutableState2)) {
            LiveTvScreen$lambda$165(mutableState2, false);
            LiveTvScreen$lambda$159(mutableState3, true);
        } else {
            LiveTvScreen$lambda$156(mutableState4, LiveTvScreen$lambda$155(mutableState4) + 1);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$16$0(EnrichedChannel enrichedChannel, MutableState mutableState, MutableState mutableState2, List list, MutableState mutableState3, MutableLongState mutableLongState, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableState mutableState8, MutableState mutableState9, IptvProgram iptvProgram) {
        EnrichedChannel enrichedChannelLiveTvScreen$lambda$167 = LiveTvScreen$lambda$167(mutableState);
        EnrichedChannel enrichedChannel2 = enrichedChannelLiveTvScreen$lambda$167 == null ? enrichedChannel : enrichedChannelLiveTvScreen$lambda$167;
        LiveTvScreen$lambda$165(mutableState2, false);
        LiveTvScreen$playProgramInFullscreen(enrichedChannel, list, mutableState3, mutableLongState, mutableState4, mutableState5, mutableState6, mutableState7, mutableState8, mutableState9, iptvProgram, enrichedChannel2);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$17$0(MutableState mutableState, MutableState mutableState2) {
        LiveTvScreen$lambda$143(mutableState, false);
        LiveTvScreen$lambda$159(mutableState2, true);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$18$0(MutableState mutableState, String str) {
        mutableState.setValue(str);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$19$0(MutableState mutableState, MutableState mutableState2) {
        LiveTvScreen$lambda$159(mutableState, false);
        LiveTvScreen$lambda$156(mutableState2, LiveTvScreen$lambda$155(mutableState2) + 1);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$20$0(Map map, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableLongState mutableLongState, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, EnrichedChannel enrichedChannel) {
        mutableState.setValue(enrichedChannel.getId());
        mutableState2.setValue(enrichedChannel.getId());
        mutableState3.setValue(enrichedChannel.getId());
        mutableState4.setValue(null);
        mutableLongState.setLongValue(0L);
        LiveTvScreen$lambda$159(mutableState5, false);
        map.put(LiveTvScreen$lambda$13(mutableState6), enrichedChannel.getId());
        LiveTvScreen$lambda$156(mutableState7, LiveTvScreen$lambda$155(mutableState7) + 1);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$21$0(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, EnrichedChannel enrichedChannel) {
        mutableState.setValue(enrichedChannel);
        LiveTvScreen$lambda$159(mutableState2, false);
        LiveTvScreen$lambda$165(mutableState3, true);
        LiveTvScreen$lambda$143(mutableState4, true);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$3$0(MutableState mutableState, ExoPlayer exoPlayer, MutableState mutableState2, MutableLongState mutableLongState, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, ka.k0 k0Var, MutableState mutableState7, MutableState mutableState8, MutableLongState mutableLongState2, Map map, Map map2, List list, MutableState mutableState9, MutableState mutableState10, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState11, MutableIntState mutableIntState3, FocusRequester focusRequester) {
        if (LiveTvScreen$lambda$77(mutableState) != null) {
            LiveTvScreen$returnCatchupToLive(exoPlayer, mutableState, mutableState2, mutableLongState, mutableState3, mutableState4, mutableState5, mutableState6);
        } else {
            LiveTvScreen$exitFullScreenPlayback(k0Var, mutableState2, mutableState7, mutableState3, mutableState8, mutableState6, mutableLongState2, map, map2, list, mutableState9, mutableState10, mutableIntState, mutableIntState2, mutableState11, mutableIntState3, focusRequester);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$4$0(EnrichedChannel enrichedChannel, TvViewModel tvViewModel, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4) {
        LiveTvScreen$openFullscreenGuide(enrichedChannel, tvViewModel, mutableState, mutableState2, mutableState3, mutableState4);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$5$0(ExoPlayer exoPlayer, MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
        if (LiveTvScreen$lambda$77(mutableState) != null) {
            LiveTvScreen$toggleCatchupPlayback(exoPlayer, mutableState, mutableState2, mutableState3);
        } else {
            if (exoPlayer.isPlaying()) {
                exoPlayer.pause();
                LiveTvScreen$lambda$208(mutableState2, false);
            } else {
                exoPlayer.setPlayWhenReady(true);
                exoPlayer.play();
                LiveTvScreen$lambda$208(mutableState2, true);
            }
            LiveTvScreen$lambda$156(mutableState3, LiveTvScreen$lambda$155(mutableState3) + 1);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$6$0(IptvNowNext iptvNowNext, MutableLongState mutableLongState, EnrichedChannel enrichedChannel, ExoPlayer exoPlayer, MutableState mutableState, long j10, MutableLongState mutableLongState2, MutableLongState mutableLongState3, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5) {
        long longValue;
        IptvProgram now = iptvNowNext != null ? iptvNowNext.getNow() : null;
        if (now == null || now.getStartUtcMillis() <= 0) {
            longValue = mutableLongState.getLongValue();
        } else {
            longValue = System.currentTimeMillis() - now.getStartUtcMillis();
            if (longValue < 0) {
                longValue = 0;
            }
        }
        long j11 = longValue - 10000;
        LiveTvScreen$seekToPosition(iptvNowNext, enrichedChannel, exoPlayer, mutableState, mutableLongState, j10, mutableLongState2, mutableLongState3, mutableState2, mutableState3, mutableState4, mutableState5, j11 < 0 ? 0L : j11);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 LiveTvScreen$lambda$251$10$7$0(com.arflix.tv.data.model.IptvNowNext r20, androidx.compose.runtime.MutableLongState r21, com.arflix.tv.ui.screens.tv.live.EnrichedChannel r22, androidx.media3.exoplayer.ExoPlayer r23, androidx.compose.runtime.MutableState r24, long r25, androidx.compose.runtime.MutableLongState r27, androidx.compose.runtime.MutableLongState r28, androidx.compose.runtime.MutableState r29, androidx.compose.runtime.MutableState r30, androidx.compose.runtime.MutableState r31, androidx.compose.runtime.MutableState r32) {
        /*
            if (r20 == 0) goto L7
            com.arflix.tv.data.model.IptvProgram r0 = r20.getNow()
            goto L8
        L7:
            r0 = 0
        L8:
            if (r0 == 0) goto L24
            long r1 = r0.getStartUtcMillis()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 <= 0) goto L24
            long r1 = java.lang.System.currentTimeMillis()
            long r5 = r0.getStartUtcMillis()
            long r1 = r1 - r5
            int r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r0 >= 0) goto L22
            goto L28
        L22:
            r3 = r1
            goto L28
        L24:
            long r3 = LiveTvScreen$lambda$198(r21)
        L28:
            r0 = 10000(0x2710, double:4.9407E-320)
            long r18 = r3 + r0
            r5 = r20
            r9 = r21
            r6 = r22
            r7 = r23
            r8 = r24
            r10 = r25
            r12 = r27
            r13 = r28
            r14 = r29
            r15 = r30
            r16 = r31
            r17 = r32
            LiveTvScreen$seekToPosition(r5, r6, r7, r8, r9, r10, r12, r13, r14, r15, r16, r17, r18)
            x6.t0 r0 = x6.t0.f22605a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.LiveTvScreen$lambda$251$10$7$0(com.arflix.tv.data.model.IptvNowNext, androidx.compose.runtime.MutableLongState, com.arflix.tv.ui.screens.tv.live.EnrichedChannel, androidx.media3.exoplayer.ExoPlayer, androidx.compose.runtime.MutableState, long, androidx.compose.runtime.MutableLongState, androidx.compose.runtime.MutableLongState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$8$0(List list, EnrichedChannel enrichedChannel, Map map, MutableState mutableState, Map map2, MutableLongState mutableLongState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableLongState mutableLongState2, MutableState mutableState8, MutableState mutableState9) {
        Iterator it = list.iterator();
        int i10 = 0;
        while (true) {
            if (!it.hasNext()) {
                i10 = -1;
                break;
            }
            if (kotlin.jvm.internal.p.a(((EnrichedChannel) it.next()).getId(), enrichedChannel != null ? enrichedChannel.getId() : null)) {
                break;
            }
            i10++;
        }
        if (i10 > 0) {
            LiveTvScreen$selectChannel(map, mutableState, map2, mutableLongState, mutableState2, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableLongState2, mutableState8, mutableState9, (EnrichedChannel) list.get(i10 - 1));
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$10$9$0(List list, EnrichedChannel enrichedChannel, Map map, MutableState mutableState, Map map2, MutableLongState mutableLongState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableLongState mutableLongState2, MutableState mutableState8, MutableState mutableState9) {
        Iterator it = list.iterator();
        int i10 = 0;
        while (true) {
            if (!it.hasNext()) {
                i10 = -1;
                break;
            }
            if (kotlin.jvm.internal.p.a(((EnrichedChannel) it.next()).getId(), enrichedChannel != null ? enrichedChannel.getId() : null)) {
                break;
            }
            i10++;
        }
        if (i10 >= 0 && i10 < list.size() - 1) {
            LiveTvScreen$selectChannel(map, mutableState, map2, mutableLongState, mutableState2, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableLongState2, mutableState8, mutableState9, (EnrichedChannel) list.get(i10 + 1));
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$12$0(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(1.0f - LiveTvScreen$lambda$251$3(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$14(List list, HashMap map, TvViewModel tvViewModel, Set set, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, Map map2, MutableIntState mutableIntState, MutableIntState mutableIntState2, List list2, MutableState mutableState6, MutableState mutableState7, MutableLongState mutableLongState, Map map3, MutableIntState mutableIntState3, FocusRequester focusRequester, AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
        Composer composer2 = composer;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1872213106, i10, -1, "com.arflix.tv.ui.screens.tv.live.LiveTvScreen.<anonymous>.<anonymous> (LiveTvScreen.kt:2973)");
        }
        boolean zChangedInstance = composer2.changedInstance(tvViewModel) | composer2.changed(set);
        Object objRememberedValue = composer2.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new LiveTvScreenKt$LiveTvScreen$49$14$1$1(tvViewModel, set, null);
            composer2.updateRememberedValue(objRememberedValue);
        }
        r7.p pVar = (r7.p) objRememberedValue;
        boolean zChanged = composer2.changed(mutableState);
        Object objRememberedValue2 = composer2.rememberedValue();
        if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new b(mutableState, 2);
            composer2.updateRememberedValue(objRememberedValue2);
        }
        r7.a aVar = (r7.a) objRememberedValue2;
        boolean zChanged2 = composer2.changed(mutableState2) | composer2.changed(mutableState3) | composer2.changed(mutableState4) | composer2.changed(mutableState5) | composer2.changed(mutableState) | composer2.changed(map2) | composer2.changed(mutableIntState) | composer2.changed(mutableIntState2) | composer2.changed(list2) | composer2.changed(mutableState6);
        Object objRememberedValue3 = composer2.rememberedValue();
        if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            com.arflix.tv.ui.screens.player.n0 n0Var = new com.arflix.tv.ui.screens.player.n0(mutableState7, mutableState2, mutableState3, mutableState4, mutableState5, mutableState, mutableLongState, map3, map2, list2, mutableIntState, mutableIntState2, mutableState6, mutableIntState3, focusRequester);
            composer2 = composer;
            composer2.updateRememberedValue(n0Var);
            objRememberedValue3 = n0Var;
        }
        SearchOverlayKt.SearchOverlay(list, map, pVar, aVar, (r7.l) objRememberedValue3, composer2, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$14$1$0(MutableState mutableState) {
        LiveTvScreen$lambda$125(mutableState, false);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$14$2$0(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableLongState mutableLongState, Map map, Map map2, List list, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState7, MutableIntState mutableIntState3, FocusRequester focusRequester, EnrichedChannel enrichedChannel) {
        mutableState2.setValue(LiveCategoryKt.bestCategoryIdForChannel(enrichedChannel, ((EnrichedChannels) mutableState.getValue()).getTree()));
        mutableState3.setValue(enrichedChannel.getId());
        mutableState4.setValue(enrichedChannel.getId());
        mutableState5.setValue(enrichedChannel.getId());
        LiveTvScreen$lambda$125(mutableState6, false);
        LiveTvScreen$focusChannelList(mutableState4, mutableState3, mutableLongState, map, map2, list, mutableState5, mutableState2, mutableIntState, mutableIntState2, mutableState7, mutableIntState3, focusRequester, enrichedChannel.getId());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$16$0(MutableState mutableState) {
        mutableState.setValue(null);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$17$0(MutableState mutableState, Map map, Map map2, MutableLongState mutableLongState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableLongState mutableLongState2, MutableState mutableState7, Map map3, List list, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState8, MutableIntState mutableIntState3, FocusRequester focusRequester, EnrichedChannel enrichedChannel) {
        LiveTvScreen$playVariant(mutableState, map, map2, mutableLongState, mutableState2, mutableState3, mutableState4, mutableState5, mutableState6, mutableLongState2, mutableState7, map3, list, mutableIntState, mutableIntState2, mutableState8, mutableIntState3, focusRequester, enrichedChannel);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$0$0(MutableLongState mutableLongState, MutableState mutableState, String str) {
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        mutableState.setValue(str);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$1$0(MutableState mutableState) {
        LiveTvScreen$lambda$125(mutableState, true);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$10$0$0(MutableLongState mutableLongState, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, String str) {
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        mutableState.setValue(str);
        mutableState2.setValue(TtmlNode.COMBINE_ALL);
        mutableState3.setValue(null);
        mutableState4.setValue(null);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$10$1$0(boolean z, int i10, MutableIntState mutableIntState, MutableState mutableState) {
        mutableIntState.setIntValue(qb.d.n(AppTopBarKt.topBarSelectedIndex(SidebarItem.TV, z), 0, i10));
        mutableState.setValue(LiveTvFocusZone.TOPBAR);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$10$10$0(TvViewModel tvViewModel, String str) {
        tvViewModel.toggleFavoriteChannel(str);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int LiveTvScreen$lambda$251$2$10$11$0(Map map, EnrichedChannel enrichedChannel) {
        return LiveTvEnhancementsKt.variantCountFor(enrichedChannel, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$10$12$0(Map map, MutableLongState mutableLongState, MutableState mutableState, EnrichedChannel enrichedChannel) {
        LiveTvScreen$openVariantPicker(map, mutableLongState, mutableState, enrichedChannel);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$10$13$0(MutableLongState mutableLongState, MutableState mutableState, MutableIntState mutableIntState, FocusRequester focusRequester) {
        LiveTvScreen$focusPlaylistSearch(mutableLongState, mutableState, mutableIntState, focusRequester);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$10$14$0(Map map, Map map2, List list, MutableLongState mutableLongState, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState4, MutableIntState mutableIntState3, FocusRequester focusRequester, EnrichedChannel enrichedChannel) {
        LiveTvScreen$focusEpg(map, map2, list, mutableLongState, mutableState, mutableState2, mutableState3, mutableIntState, mutableIntState2, mutableState4, mutableIntState3, focusRequester, enrichedChannel.getId());
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$10$15$0(MutableState mutableState, MutableState mutableState2, MutableLongState mutableLongState, Map map, Map map2, List list, MutableState mutableState3, MutableState mutableState4, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState5, MutableIntState mutableIntState3, FocusRequester focusRequester, EnrichedChannel enrichedChannel) {
        String strLiveTvScreen$lambda$68;
        if ((enrichedChannel == null || (strLiveTvScreen$lambda$68 = enrichedChannel.getId()) == null) && (strLiveTvScreen$lambda$68 = LiveTvScreen$lambda$68(mutableState)) == null) {
            strLiveTvScreen$lambda$68 = LiveTvScreen$lambda$63(mutableState2);
        }
        LiveTvScreen$focusChannelList(mutableState, mutableState2, mutableLongState, map, map2, list, mutableState3, mutableState4, mutableIntState, mutableIntState2, mutableState5, mutableIntState3, focusRequester, strLiveTvScreen$lambda$68);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$10$2$0(MutableLongState mutableLongState, MutableState mutableState, MutableIntState mutableIntState, FocusRequester focusRequester) {
        LiveTvScreen$focusPlaylistSearch(mutableLongState, mutableState, mutableIntState, focusRequester);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$10$4$0$0(EnrichedChannel enrichedChannel, Map map, MutableLongState mutableLongState, MutableState mutableState) {
        LiveTvScreen$openVariantPicker(map, mutableLongState, mutableState, enrichedChannel);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$10$5$0(TvViewModel tvViewModel, String str) {
        tvViewModel.toggleFavoriteChannel(str);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$10$6$0(MutableState mutableState, FocusState focusState) {
        if (focusState.getHasFocus() && LiveTvScreen$lambda$41(mutableState) == LiveTvFocusZone.CATEGORY_LIST) {
            mutableState.setValue(LiveTvFocusZone.CHANNEL_LIST);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$10$7$0(Map map, MutableState mutableState, Map map2, MutableLongState mutableLongState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableLongState mutableLongState2, MutableState mutableState8, MutableState mutableState9, EnrichedChannel enrichedChannel, IptvProgram iptvProgram) {
        LiveTvScreen$selectChannel(map, mutableState, map2, mutableLongState, mutableState2, mutableState3, mutableState4, mutableState5, mutableState6, mutableState7, mutableLongState2, mutableState8, mutableState9, enrichedChannel);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$10$8$0(List list, Map map, MutableLongState mutableLongState, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableLongState mutableLongState2, MutableState mutableState6, Map map2, List list2, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState7, MutableIntState mutableIntState3, FocusRequester focusRequester, EnrichedChannel enrichedChannel, IptvProgram iptvProgram) {
        LiveTvScreen$playProgramInMini(list, map, mutableLongState, mutableState, mutableState2, mutableState3, mutableState4, mutableState5, mutableLongState2, mutableState6, map2, list2, mutableIntState, mutableIntState2, mutableState7, mutableIntState3, focusRequester, enrichedChannel, iptvProgram);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$10$9$0(x6.x[] xVarArr, ka.v1[] v1VarArr, ka.k0 k0Var, MutableState mutableState, Map map, MutableState mutableState2, MutableState mutableState3, EnrichedChannel enrichedChannel) {
        LiveTvScreen$commitFocusedChannel(xVarArr, v1VarArr, k0Var, mutableState, map, mutableState2, mutableState3, enrichedChannel);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$2$0(TvViewModel tvViewModel, MutableLongState mutableLongState, MutableState mutableState, String str, String str2) {
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        mutableState.setValue(TtmlNode.COMBINE_ALL);
        tvViewModel.toggleHiddenGroup(str, str2);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$3$0(TvViewModel tvViewModel, MutableLongState mutableLongState, String str, String str2) {
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        tvViewModel.toggleHiddenGroup(str, str2);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$4$0(TvViewModel tvViewModel, String str, String str2) {
        tvViewModel.moveGroupUp(str, str2);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$5$0(TvViewModel tvViewModel, String str, String str2) {
        tvViewModel.moveGroupToTop(str, str2);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$6$0(TvViewModel tvViewModel, String str, String str2) {
        tvViewModel.moveGroupDown(str, str2);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$7$0(MutableState mutableState) {
        if (LiveTvScreen$lambda$41(mutableState) != LiveTvFocusZone.TOPBAR) {
            mutableState.setValue(LiveTvFocusZone.CATEGORY_LIST);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005d A[PHI: r0
      0x005d: PHI (r0v5 java.lang.String) = (r0v4 java.lang.String), (r0v10 java.lang.String), (r0v13 java.lang.String) binds: [B:8:0x0017, B:15:0x0027, B:22:0x0037] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 LiveTvScreen$lambda$251$2$8$0(java.util.Map r14, java.util.List r15, androidx.compose.runtime.MutableState r16, java.util.Map r17, androidx.compose.runtime.MutableState r18, androidx.compose.runtime.MutableState r19, androidx.compose.runtime.MutableLongState r20, androidx.compose.runtime.MutableState r21, androidx.compose.runtime.MutableIntState r22, androidx.compose.runtime.MutableIntState r23, androidx.compose.runtime.MutableState r24, androidx.compose.runtime.MutableIntState r25, androidx.compose.ui.focus.FocusRequester r26) {
        /*
            r4 = r17
            java.lang.String r0 = LiveTvScreen$lambda$13(r16)
            java.lang.Object r0 = r14.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            r1 = 0
            if (r0 == 0) goto L16
            boolean r2 = r4.containsKey(r0)
            if (r2 == 0) goto L16
            goto L17
        L16:
            r0 = r1
        L17:
            if (r0 != 0) goto L5d
            java.lang.String r0 = LiveTvScreen$lambda$68(r18)
            if (r0 == 0) goto L26
            boolean r2 = r4.containsKey(r0)
            if (r2 == 0) goto L26
            goto L27
        L26:
            r0 = r1
        L27:
            if (r0 != 0) goto L5d
            java.lang.String r0 = LiveTvScreen$lambda$63(r19)
            if (r0 == 0) goto L36
            boolean r2 = r4.containsKey(r0)
            if (r2 == 0) goto L36
            goto L37
        L36:
            r0 = r1
        L37:
            if (r0 != 0) goto L5d
            java.lang.Object r0 = kotlin.collections.x.o0(r15)
            com.arflix.tv.ui.screens.tv.live.EnrichedChannel r0 = (com.arflix.tv.ui.screens.tv.live.EnrichedChannel) r0
            if (r0 == 0) goto L45
            java.lang.String r1 = r0.getId()
        L45:
            r3 = r14
            r5 = r15
            r7 = r16
            r0 = r18
            r2 = r20
            r6 = r21
            r8 = r22
            r9 = r23
            r10 = r24
            r11 = r25
            r12 = r26
            r13 = r1
            r1 = r19
            goto L74
        L5d:
            r3 = r14
            r5 = r15
            r7 = r16
            r1 = r19
            r2 = r20
            r6 = r21
            r8 = r22
            r9 = r23
            r10 = r24
            r11 = r25
            r12 = r26
            r13 = r0
            r0 = r18
        L74:
            LiveTvScreen$focusChannelList(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            x6.t0 r14 = x6.t0.f22605a
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.LiveTvScreen$lambda$251$2$8$0(java.util.Map, java.util.List, androidx.compose.runtime.MutableState, java.util.Map, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableLongState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableIntState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableIntState, androidx.compose.ui.focus.FocusRequester):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$2$9$0(boolean z, int i10, MutableIntState mutableIntState, MutableState mutableState) {
        mutableIntState.setIntValue(qb.d.n(AppTopBarKt.topBarSelectedIndex(SidebarItem.TV, z), 0, i10));
        mutableState.setValue(LiveTvFocusZone.TOPBAR);
        return x6.t0.f22605a;
    }

    private static final float LiveTvScreen$lambda$251$3(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$4$0(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, ExoPlayer exoPlayer, MutableState mutableState5, MutableLongState mutableLongState, MutableState mutableState6, MutableState mutableState7, ka.k0 k0Var, MutableState mutableState8, MutableState mutableState9, MutableLongState mutableLongState2, Map map, Map map2, List list, MutableState mutableState10, MutableState mutableState11, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState12, MutableIntState mutableIntState3, FocusRequester focusRequester) {
        if (LiveTvScreen$lambda$142(mutableState)) {
            LiveTvScreen$lambda$143(mutableState, false);
            LiveTvScreen$lambda$156(mutableState2, LiveTvScreen$lambda$155(mutableState2) + 1);
        } else if (!LiveTvScreen$lambda$158(mutableState3)) {
            if (LiveTvScreen$lambda$77(mutableState4) != null) {
                LiveTvScreen$returnCatchupToLive(exoPlayer, mutableState4, mutableState5, mutableLongState, mutableState, mutableState6, mutableState7, mutableState2);
            } else {
                LiveTvScreen$exitFullScreenPlayback(k0Var, mutableState5, mutableState8, mutableState, mutableState9, mutableState2, mutableLongState2, map, map2, list, mutableState10, mutableState11, mutableIntState, mutableIntState2, mutableState12, mutableIntState3, focusRequester);
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$5$0(float f10, State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.mo3646setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.22f, 0.18f));
        graphicsLayerScope.setScaleX(f10);
        graphicsLayerScope.setScaleY(f10);
        graphicsLayerScope.setAlpha(LiveTvScreen$lambda$251$3(state));
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$251$8$0(MutableState mutableState) {
        LiveTvScreen$lambda$156(mutableState, LiveTvScreen$lambda$155(mutableState) + 1);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 LiveTvScreen$lambda$252(TvViewModel tvViewModel, Profile profile, String str, String str2, r7.l lVar, r7.a aVar, r7.a aVar2, r7.a aVar3, r7.a aVar4, r7.a aVar5, r7.a aVar6, r7.a aVar7, int i10, int i11, int i12, Composer composer, int i13) {
        LiveTvScreen(tvViewModel, profile, str, str2, lVar, aVar, aVar2, aVar3, aVar4, aVar5, aVar6, aVar7, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState LiveTvScreen$lambda$26$0() {
        return SnapshotIntStateKt.mutableIntStateOf(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<x6.j0> LiveTvScreen$lambda$30(MutableState<List<x6.j0>> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$40$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(LiveTvFocusZone.CATEGORY_LIST, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LiveTvFocusZone LiveTvScreen$lambda$41(MutableState<LiveTvFocusZone> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState LiveTvScreen$lambda$43$0(boolean z, int i10) {
        return SnapshotIntStateKt.mutableIntStateOf(qb.d.n(AppTopBarKt.topBarSelectedIndex(SidebarItem.TV, z), 0, i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$51(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$62$0(String str) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(str, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$63(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LiveTvScreen$lambda$66$0(final TvViewModel tvViewModel, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$66$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                tvViewModel.setLiveTvPlaybackActive(false);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$67$0(String str) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(str, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LiveTvScreen$lambda$68(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TvUiState LiveTvScreen$lambda$7(State<TvUiState> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$70$0(String str) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(str, null, 2, null);
    }

    private static final String LiveTvScreen$lambda$71(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableState LiveTvScreen$lambda$73$0() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean LiveTvScreen$lambda$74(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$lambda$75(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IptvProgram LiveTvScreen$lambda$77(MutableState<IptvProgram> mutableState) {
        return mutableState.getValue();
    }

    private static final TvUiState LiveTvScreen$lambda$8(State<TvUiState> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LiveTvScreen$lambda$84$0(final ka.v1[] v1VarArr, DisposableEffectScope disposableEffectScope) {
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$LiveTvScreen$lambda$84$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                ka.v1 v1Var = v1VarArr[0];
                if (v1Var != null) {
                    v1Var.cancel((CancellationException) null);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final EnrichedChannel LiveTvScreen$lambda$88(MutableState<EnrichedChannel> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState LiveTvScreen$lambda$95$0() {
        return SnapshotIntStateKt.mutableIntStateOf(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MutableIntState LiveTvScreen$lambda$98$0() {
        return SnapshotIntStateKt.mutableIntStateOf(48);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$noteGuideUserNavigation(MutableLongState mutableLongState) {
        mutableLongState.setLongValue(System.currentTimeMillis());
    }

    private static final void LiveTvScreen$openFullscreenGuide(EnrichedChannel enrichedChannel, TvViewModel tvViewModel, MutableState<EnrichedChannel> mutableState, MutableState<String> mutableState2, MutableState<Boolean> mutableState3, MutableState<Integer> mutableState4) {
        mutableState.setValue(enrichedChannel);
        tvViewModel.refreshCatchupHistoryForChannel(LiveTvScreen$lambda$63(mutableState2));
        LiveTvScreen$lambda$143(mutableState3, true);
        LiveTvScreen$lambda$156(mutableState4, LiveTvScreen$lambda$155(mutableState4) + 1);
    }

    private static final void LiveTvScreen$openVariantPicker(Map<String, ? extends List<EnrichedChannel>> map, MutableLongState mutableLongState, MutableState<EnrichedChannel> mutableState, EnrichedChannel enrichedChannel) {
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        if (LiveTvEnhancementsKt.variantCountFor(enrichedChannel, map) > 1) {
            mutableState.setValue(enrichedChannel);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$playProgramInFullscreen(EnrichedChannel enrichedChannel, List<EnrichedChannel> list, MutableState<IptvProgram> mutableState, MutableLongState mutableLongState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<Boolean> mutableState5, MutableState<Boolean> mutableState6, MutableState<Integer> mutableState7, IptvProgram iptvProgram, EnrichedChannel enrichedChannel2) {
        if (enrichedChannel2 != null) {
            enrichedChannel = enrichedChannel2;
        }
        if (!kotlin.jvm.internal.p.a(iptvProgram, LiveTvScreen$lambda$77(mutableState))) {
            mutableLongState.setLongValue(0L);
        }
        if (enrichedChannel != null) {
            EnrichedChannel enrichedChannelCatchupPlaybackVariant = catchupPlaybackVariant(enrichedChannel, list);
            if (!kotlin.jvm.internal.p.a(enrichedChannelCatchupPlaybackVariant.getId(), LiveTvScreen$lambda$63(mutableState2))) {
                PrintStream printStream = System.err;
                String id = enrichedChannel.getId();
                String id2 = enrichedChannelCatchupPlaybackVariant.getId();
                String label = enrichedChannelCatchupPlaybackVariant.getQuality().getLabel();
                int catchupDays = enrichedChannelCatchupPlaybackVariant.getCatchupDays();
                StringBuilder sbR = androidx.fragment.app.a2.r("[IPTV-Catchup] using fullscreen archive variant source=", id, " playback=", id2, " quality=");
                sbR.append(label);
                sbR.append(" days=");
                sbR.append(catchupDays);
                printStream.println(sbR.toString());
                mutableState2.setValue(enrichedChannelCatchupPlaybackVariant.getId());
                mutableState3.setValue(enrichedChannelCatchupPlaybackVariant.getId());
                mutableState4.setValue(enrichedChannelCatchupPlaybackVariant.getId());
            }
        }
        mutableState.setValue(iptvProgram);
        LiveTvScreen$lambda$143(mutableState5, false);
        LiveTvScreen$lambda$140(mutableState6, true);
        LiveTvScreen$lambda$156(mutableState7, LiveTvScreen$lambda$155(mutableState7) + 1);
    }

    private static final void LiveTvScreen$playProgramInMini(List<EnrichedChannel> list, Map<String, String> map, MutableLongState mutableLongState, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<IptvProgram> mutableState5, MutableLongState mutableLongState2, MutableState<Boolean> mutableState6, Map<String, Integer> map2, List<EnrichedChannel> list2, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState<LiveTvFocusZone> mutableState7, MutableIntState mutableIntState3, FocusRequester focusRequester, EnrichedChannel enrichedChannel, IptvProgram iptvProgram) {
        EnrichedChannel enrichedChannel2;
        EnrichedChannel enrichedChannelCatchupPlaybackVariant;
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        if (iptvProgram != null) {
            enrichedChannel2 = enrichedChannel;
            enrichedChannelCatchupPlaybackVariant = catchupPlaybackVariant(enrichedChannel2, list);
        } else {
            enrichedChannel2 = enrichedChannel;
            enrichedChannelCatchupPlaybackVariant = enrichedChannel2;
        }
        if (iptvProgram != null && !kotlin.jvm.internal.p.a(enrichedChannelCatchupPlaybackVariant.getId(), enrichedChannel2.getId())) {
            PrintStream printStream = System.err;
            String id = enrichedChannel2.getId();
            String id2 = enrichedChannelCatchupPlaybackVariant.getId();
            String label = enrichedChannelCatchupPlaybackVariant.getQuality().getLabel();
            int catchupDays = enrichedChannelCatchupPlaybackVariant.getCatchupDays();
            StringBuilder sbR = androidx.fragment.app.a2.r("[IPTV-Catchup] using archive variant source=", id, " playback=", id2, " quality=");
            sbR.append(label);
            sbR.append(" days=");
            sbR.append(catchupDays);
            printStream.println(sbR.toString());
        }
        mutableState.setValue(enrichedChannelCatchupPlaybackVariant.getId());
        mutableState2.setValue(enrichedChannelCatchupPlaybackVariant.getId());
        map.put(LiveTvScreen$lambda$13(mutableState3), enrichedChannelCatchupPlaybackVariant.getId());
        mutableState4.setValue(enrichedChannelCatchupPlaybackVariant.getId());
        mutableState5.setValue(iptvProgram);
        mutableLongState2.setLongValue(0L);
        LiveTvScreen$lambda$143(mutableState6, false);
        LiveTvScreen$focusChannelList(mutableState, mutableState4, mutableLongState, map, map2, list2, mutableState2, mutableState3, mutableIntState, mutableIntState2, mutableState7, mutableIntState3, focusRequester, enrichedChannelCatchupPlaybackVariant.getId());
    }

    private static final void LiveTvScreen$playVariant(MutableState<EnrichedChannels> mutableState, Map<String, ? extends List<EnrichedChannel>> map, Map<String, String> map2, MutableLongState mutableLongState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<IptvProgram> mutableState6, MutableLongState mutableLongState2, MutableState<Boolean> mutableState7, Map<String, Integer> map3, List<EnrichedChannel> list, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState<LiveTvFocusZone> mutableState8, MutableIntState mutableIntState3, FocusRequester focusRequester, EnrichedChannel enrichedChannel) {
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        String strDisplayChannelIdFor = LiveTvEnhancementsKt.displayChannelIdFor(enrichedChannel.getId(), mutableState.getValue().getIndex().getById(), map);
        if (strDisplayChannelIdFor == null) {
            strDisplayChannelIdFor = enrichedChannel.getId();
        }
        String str = strDisplayChannelIdFor;
        mutableState2.setValue(enrichedChannel.getId());
        mutableState3.setValue(str);
        mutableState4.setValue(str);
        map2.put(LiveTvScreen$lambda$13(mutableState5), str);
        mutableState6.setValue(null);
        mutableLongState2.setLongValue(0L);
        LiveTvScreen$lambda$143(mutableState7, false);
        LiveTvScreen$focusChannelList(mutableState3, mutableState2, mutableLongState, map2, map3, list, mutableState4, mutableState5, mutableIntState, mutableIntState2, mutableState8, mutableIntState3, focusRequester, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$prepareStream(Map<String, String> map, OkHttpDataSource.Factory factory, long j10, ExoPlayer exoPlayer, Context context, EnrichedChannel enrichedChannel, MutableState<String> mutableState, MutableState<Boolean> mutableState2, MutableState<Map<String, String>> mutableState3, MutableState<IptvProgram> mutableState4, MutableLongState mutableLongState, MutableState<Boolean> mutableState5, MutableIntState mutableIntState, MutableState<PlaybackDiagnostic> mutableState6, MutableLongState mutableLongState2, String str, boolean z, Map<String, String> map2, boolean z5, long j11, DrmInfo drmInfo, boolean z10) {
        String string;
        String licenseUrl;
        factory.setDefaultRequestProperties(safePlaybackHeaders(kotlin.collections.h0.v0(map, map2)));
        if (!z10 && kotlin.jvm.internal.p.a(str, LiveTvScreen$lambda$215(mutableState)) && z == LiveTvScreen$lambda$218(mutableState2) && map2.equals(LiveTvScreen$lambda$221(mutableState3)) && (LiveTvScreen$lambda$77(mutableState4) == null || j10 == mutableLongState.getLongValue())) {
            return;
        }
        LiveTvScreen$lambda$211(mutableState5, true);
        exoPlayer.stop();
        exoPlayer.clearMediaItems();
        MediaItem.Builder uri = new MediaItem.Builder().setUri(str);
        if (z) {
            uri.setMimeType(MimeTypes.APPLICATION_M3U8);
        } else if (looksLikeMpegTsUrl(str)) {
            uri.setMimeType(MimeTypes.VIDEO_MP2T);
        }
        if (LiveTvScreen$lambda$77(mutableState4) == null) {
            uri.setLiveConfiguration(new MediaItem.LiveConfiguration.Builder().setMinPlaybackSpeed(1.0f).setMaxPlaybackSpeed(1.0f).setTargetOffsetMs(8000L).build());
        }
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
            uri.setDrmConfiguration(builder.build());
        }
        MediaItem mediaItemBuild = uri.build();
        if (j11 > 0) {
            exoPlayer.setMediaItem(mediaItemBuild, j11);
        } else {
            exoPlayer.setMediaItem(mediaItemBuild);
        }
        exoPlayer.prepare();
        exoPlayer.setPlayWhenReady(true);
        exoPlayer.play();
        mutableState.setValue(str);
        LiveTvScreen$lambda$219(mutableState2, z);
        mutableState3.setValue(map2);
        mutableLongState.setLongValue(LiveTvScreen$lambda$77(mutableState4) != null ? j10 : -1L);
        if (z5) {
            mutableIntState.setIntValue(0);
        }
        if (z5) {
            String string2 = context.getString((LiveTvScreen$lambda$77(mutableState4) == null || j11 <= 0) ? R.string.live_diag_starting_stream : R.string.live_diag_seeking_catchup);
            if (enrichedChannel == null || (string = enrichedChannel.getName()) == null) {
                string = context.getString(R.string.live_diag_preparing_source);
            }
            mutableState6.setValue(new PlaybackDiagnostic(string2, string, PlaybackDiagnosticSeverity.Info));
        }
        PrintStream printStream = System.err;
        boolean z11 = LiveTvScreen$lambda$77(mutableState4) != null;
        long longValue = mutableLongState2.getLongValue();
        String strRedactPlaybackUrl = redactPlaybackUrl(str);
        StringBuilder sb2 = new StringBuilder("[IPTV-Catchup] prepare catchup=");
        sb2.append(z11);
        sb2.append(" anchor=");
        sb2.append(j10);
        androidx.compose.foundation.c.z(sb2, " inSegment=", j11, " target=");
        sb2.append(longValue);
        sb2.append(" url=");
        sb2.append(strRedactPlaybackUrl);
        printStream.println(sb2.toString());
    }

    public static /* synthetic */ void LiveTvScreen$prepareStream$default(Map map, OkHttpDataSource.Factory factory, long j10, ExoPlayer exoPlayer, Context context, EnrichedChannel enrichedChannel, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableLongState mutableLongState, MutableState mutableState5, MutableIntState mutableIntState, MutableState mutableState6, MutableLongState mutableLongState2, String str, boolean z, Map map2, boolean z5, long j11, DrmInfo drmInfo, boolean z10, int i10, Object obj) {
        LiveTvScreen$prepareStream(map, factory, j10, exoPlayer, context, enrichedChannel, mutableState, mutableState2, mutableState3, mutableState4, mutableLongState, mutableState5, mutableIntState, mutableState6, mutableLongState2, str, z, map2, z5, (i10 & 524288) != 0 ? 0L : j11, (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? null : drmInfo, (i10 & 2097152) != 0 ? false : z10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$requestGuideWindowAfter(int i10, List<EnrichedChannel> list, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableIntState mutableIntState3) {
        if (i10 > list.size()) {
            int intValue = mutableIntState.getIntValue();
            int size = list.size() - 48;
            if (size < 0) {
                size = 0;
            }
            if (intValue >= size) {
                int intValue2 = mutableIntState2.getIntValue() + 192;
                if (intValue2 > i10) {
                    intValue2 = i10;
                }
                if (intValue2 < GuideMaxWindowRows) {
                    intValue2 = GuideMaxWindowRows;
                }
                mutableIntState2.setIntValue(intValue2);
            }
        }
        int size2 = list.size();
        int intValue3 = mutableIntState2.getIntValue();
        if (intValue3 <= i10) {
            i10 = intValue3;
        }
        LiveTvScreen$setGuideWindow(list, mutableIntState3, mutableIntState, expandGuideWindowAfter(mutableIntState3.getIntValue(), mutableIntState.getIntValue(), Math.max(size2, i10)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$requestGuideWindowBefore(MutableIntState mutableIntState, MutableIntState mutableIntState2, List<EnrichedChannel> list) {
        LiveTvScreen$setGuideWindow(list, mutableIntState, mutableIntState2, expandGuideWindowBefore(mutableIntState.getIntValue(), mutableIntState2.getIntValue()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$returnCatchupToLive(ExoPlayer exoPlayer, MutableState<IptvProgram> mutableState, MutableState<String> mutableState2, MutableLongState mutableLongState, MutableState<Boolean> mutableState3, MutableState<String> mutableState4, MutableState<Boolean> mutableState5, MutableState<Integer> mutableState6) {
        if (LiveTvScreen$lambda$77(mutableState) == null) {
            return;
        }
        PrintStream printStream = System.err;
        String strLiveTvScreen$lambda$63 = LiveTvScreen$lambda$63(mutableState2);
        if (strLiveTvScreen$lambda$63 == null) {
            strLiveTvScreen$lambda$63 = "";
        }
        printStream.println("[IPTV-Catchup] return-live channel=".concat(strLiveTvScreen$lambda$63));
        mutableState.setValue(null);
        mutableLongState.setLongValue(0L);
        LiveTvScreen$lambda$143(mutableState3, false);
        mutableState4.setValue(null);
        LiveTvScreen$lambda$211(mutableState5, true);
        exoPlayer.play();
        LiveTvScreen$lambda$156(mutableState6, LiveTvScreen$lambda$155(mutableState6) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$seekCatchupBy(ExoPlayer exoPlayer, long j10, EnrichedChannel enrichedChannel, MutableState<IptvProgram> mutableState, MutableLongState mutableLongState, MutableLongState mutableLongState2, MutableState<Integer> mutableState2, MutableLongState mutableLongState3, MutableState<Boolean> mutableState3, long j11) {
        IptvProgram iptvProgramLiveTvScreen$lambda$77 = LiveTvScreen$lambda$77(mutableState);
        if (iptvProgramLiveTvScreen$lambda$77 == null) {
            return;
        }
        long endUtcMillis = iptvProgramLiveTvScreen$lambda$77.getEndUtcMillis() - iptvProgramLiveTvScreen$lambda$77.getStartUtcMillis();
        if (endUtcMillis < 0) {
            endUtcMillis = 0;
        }
        Long lValueOf = Long.valueOf(endUtcMillis);
        if (lValueOf.longValue() <= 0) {
            lValueOf = null;
        }
        long jLongValue = lValueOf != null ? lValueOf.longValue() : mutableLongState.getLongValue();
        boolean playWhenReady = exoPlayer.getPlayWhenReady();
        long j12 = jLongValue > 1000 ? jLongValue - 1000 : jLongValue;
        long currentPosition = exoPlayer.getCurrentPosition();
        if (currentPosition < 0) {
            currentPosition = 0;
        }
        long j13 = j10 + currentPosition;
        if (j12 > 0 && j13 > j12) {
            j13 = j12;
        }
        long j14 = j13 + j11;
        if (j14 < 0) {
            j14 = 0;
        }
        if (j12 <= 0 || j14 <= j12) {
            j12 = j14;
        }
        if (j12 == mutableLongState2.getLongValue()) {
            LiveTvScreen$lambda$156(mutableState2, LiveTvScreen$lambda$155(mutableState2) + 1);
            return;
        }
        IptvChannel source = enrichedChannel != null ? enrichedChannel.getSource() : null;
        long jCatchupUrlAnchorOffset = source != null ? catchupUrlAnchorOffset(source, j12) : 0L;
        long jCatchupInSegmentSeekOffset = source != null ? catchupInSegmentSeekOffset(source, j12) : j12;
        boolean z = jCatchupUrlAnchorOffset == j10;
        mutableLongState2.setLongValue(j12);
        mutableLongState3.setLongValue(j12);
        exoPlayer.setPlayWhenReady(true);
        boolean z5 = z;
        if (z5) {
            exoPlayer.seekTo(jCatchupInSegmentSeekOffset);
        }
        exoPlayer.play();
        LiveTvScreen$lambda$208(mutableState3, true);
        PrintStream printStream = System.err;
        int playbackState = exoPlayer.getPlaybackState();
        long currentPosition2 = exoPlayer.getCurrentPosition();
        StringBuilder sbR = androidx.compose.material3.d.r(j11, "[IPTV-Catchup] seek delta=", " current=");
        sbR.append(j13);
        androidx.compose.foundation.c.z(sbR, " target=", j12, " duration=");
        sbR.append(jLongValue);
        sbR.append(" wasPlayRequested=");
        sbR.append(playWhenReady);
        sbR.append(" state=");
        sbR.append(playbackState);
        sbR.append(" anchor=");
        sbR.append(j10);
        androidx.compose.foundation.c.z(sbR, " targetAnchor=", jCatchupUrlAnchorOffset, " inSegment=");
        sbR.append(jCatchupInSegmentSeekOffset);
        sbR.append(" sameAnchor=");
        sbR.append(z5);
        sbR.append(" exo=");
        sbR.append(currentPosition2);
        printStream.println(sbR.toString());
        LiveTvScreen$lambda$156(mutableState2, LiveTvScreen$lambda$155(mutableState2) + 1);
    }

    private static final void LiveTvScreen$seekToPosition(IptvNowNext iptvNowNext, EnrichedChannel enrichedChannel, ExoPlayer exoPlayer, MutableState<IptvProgram> mutableState, MutableLongState mutableLongState, long j10, MutableLongState mutableLongState2, MutableLongState mutableLongState3, MutableState<Integer> mutableState2, MutableState<Boolean> mutableState3, MutableState<String> mutableState4, MutableState<Boolean> mutableState5, long j11) {
        long longValue;
        if (LiveTvScreen$lambda$77(mutableState) != null) {
            LiveTvScreen$seekCatchupBy(exoPlayer, j10, enrichedChannel, mutableState, mutableLongState2, mutableLongState3, mutableState2, mutableLongState, mutableState3, j11 - mutableLongState.getLongValue());
            return;
        }
        Long l10 = null;
        IptvProgram now = iptvNowNext != null ? iptvNowNext.getNow() : null;
        if (now == null || now.getStartUtcMillis() <= 0) {
            longValue = mutableLongState.getLongValue();
        } else {
            longValue = System.currentTimeMillis() - now.getStartUtcMillis();
            if (longValue < 0) {
                longValue = 0;
            }
        }
        long j12 = longValue;
        long jO = qb.d.o(j11, 0L, j12);
        if (jO >= j12) {
            LiveTvScreen$lambda$156(mutableState2, LiveTvScreen$lambda$155(mutableState2) + 1);
            return;
        }
        if (enrichedChannel == null || now == null || !supportsCatchupHistory(enrichedChannel)) {
            long currentPosition = exoPlayer.getCurrentPosition();
            Long lValueOf = Long.valueOf(exoPlayer.getDuration());
            long jLongValue = lValueOf.longValue();
            if (jLongValue > 0 && jLongValue != C.TIME_UNSET) {
                l10 = lValueOf;
            }
            exoPlayer.seekTo(qb.d.o((jO - j12) + currentPosition, 0L, l10 != null ? l10.longValue() : 60000L));
            LiveTvScreen$lambda$156(mutableState2, LiveTvScreen$lambda$155(mutableState2) + 1);
            return;
        }
        System.err.println("[IPTV-Catchup] auto-switch catchup program=" + now.getTitle() + " targetMs=" + jO);
        mutableState.setValue(now);
        mutableLongState3.setLongValue(jO);
        mutableLongState.setLongValue(jO);
        mutableState4.setValue(null);
        LiveTvScreen$lambda$211(mutableState5, true);
        LiveTvScreen$lambda$156(mutableState2, LiveTvScreen$lambda$155(mutableState2) + 1);
    }

    private static final void LiveTvScreen$selectChannel(Map<String, String> map, MutableState<EnrichedChannels> mutableState, Map<String, ? extends List<EnrichedChannel>> map2, MutableLongState mutableLongState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<Boolean> mutableState6, MutableState<IptvProgram> mutableState7, MutableLongState mutableLongState2, MutableState<Integer> mutableState8, MutableState<Boolean> mutableState9, EnrichedChannel enrichedChannel) {
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        mutableState2.setValue(enrichedChannel.getId());
        mutableState3.setValue(enrichedChannel.getId());
        map.put(LiveTvScreen$lambda$13(mutableState4), enrichedChannel.getId());
        String strDisplayChannelIdFor = LiveTvEnhancementsKt.displayChannelIdFor(LiveTvScreen$lambda$63(mutableState5), mutableState.getValue().getIndex().getById(), map2);
        if ((kotlin.jvm.internal.p.a(enrichedChannel.getId(), LiveTvScreen$lambda$63(mutableState5)) || kotlin.jvm.internal.p.a(enrichedChannel.getId(), strDisplayChannelIdFor)) && !LiveTvScreen$lambda$139(mutableState6)) {
            mutableState7.setValue(null);
            mutableLongState2.setLongValue(0L);
            LiveTvScreen$lambda$140(mutableState6, true);
            LiveTvScreen$lambda$156(mutableState8, LiveTvScreen$lambda$155(mutableState8) + 1);
            return;
        }
        mutableState5.setValue(enrichedChannel.getId());
        mutableState7.setValue(null);
        mutableLongState2.setLongValue(0L);
        LiveTvScreen$lambda$143(mutableState9, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$setGuideWindow(List<EnrichedChannel> list, MutableIntState mutableIntState, MutableIntState mutableIntState2, x6.x xVar) {
        int size = list.size();
        int iN = qb.d.n(((Number) xVar.f22608i).intValue(), 0, size < 0 ? 0 : size);
        int iN2 = qb.d.n(((Number) xVar.f22609l).intValue(), iN, size);
        mutableIntState.setIntValue(iN);
        mutableIntState2.setIntValue(iN2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$toggleCatchupPlayback(ExoPlayer exoPlayer, MutableState<IptvProgram> mutableState, MutableState<Boolean> mutableState2, MutableState<Integer> mutableState3) {
        if (LiveTvScreen$lambda$77(mutableState) == null) {
            return;
        }
        if (exoPlayer.isPlaying()) {
            exoPlayer.pause();
            LiveTvScreen$lambda$208(mutableState2, false);
            System.err.println("[IPTV-Catchup] pause position=" + exoPlayer.getCurrentPosition());
        } else {
            exoPlayer.setPlayWhenReady(true);
            exoPlayer.play();
            LiveTvScreen$lambda$208(mutableState2, true);
            System.err.println("[IPTV-Catchup] play position=" + exoPlayer.getCurrentPosition());
        }
        LiveTvScreen$lambda$156(mutableState3, LiveTvScreen$lambda$155(mutableState3) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void LiveTvScreen$tuneChannelNumber(Map<String, String> map, MutableLongState mutableLongState, MutableState<String> mutableState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<IptvProgram> mutableState4, MutableLongState mutableLongState2, MutableState<Boolean> mutableState5, MutableState<String> mutableState6, Map<String, Integer> map2, List<EnrichedChannel> list, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState<LiveTvFocusZone> mutableState7, MutableIntState mutableIntState3, FocusRequester focusRequester, MutableState<Integer> mutableState8, EnrichedChannel enrichedChannel) {
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        mutableState.setValue(enrichedChannel.getId());
        mutableState2.setValue(enrichedChannel.getId());
        mutableState3.setValue(enrichedChannel.getId());
        mutableState4.setValue(null);
        mutableLongState2.setLongValue(0L);
        LiveTvScreen$lambda$143(mutableState5, false);
        map.put(LiveTvScreen$lambda$13(mutableState6), enrichedChannel.getId());
        LiveTvScreen$focusChannelList(mutableState2, mutableState, mutableLongState, map, map2, list, mutableState3, mutableState6, mutableIntState, mutableIntState2, mutableState7, mutableIntState3, focusRequester, enrichedChannel.getId());
        LiveTvScreen$lambda$156(mutableState8, LiveTvScreen$lambda$155(mutableState8) + 1);
    }

    private static final void LiveTvScreen$zap(List<EnrichedChannel> list, MutableState<EnrichedChannels> mutableState, Map<String, ? extends List<EnrichedChannel>> map, Map<String, String> map2, MutableLongState mutableLongState, MutableState<String> mutableState2, MutableState<String> mutableState3, MutableState<String> mutableState4, MutableState<String> mutableState5, MutableState<IptvProgram> mutableState6, MutableLongState mutableLongState2, MutableState<Boolean> mutableState7, int i10) {
        LiveTvScreen$noteGuideUserNavigation(mutableLongState);
        if (list.isEmpty()) {
            return;
        }
        String strDisplayChannelIdFor = LiveTvEnhancementsKt.displayChannelIdFor(LiveTvScreen$lambda$63(mutableState2), mutableState.getValue().getIndex().getById(), map);
        int i11 = -1;
        if (strDisplayChannelIdFor != null) {
            Iterator<EnrichedChannel> it = list.iterator();
            int i12 = 0;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (kotlin.jvm.internal.p.a(it.next().getId(), strDisplayChannelIdFor)) {
                    i11 = i12;
                    break;
                }
                i12++;
            }
        }
        if (i11 < 0) {
            i11 = 0;
        }
        int size = list.size();
        int i13 = (((i11 + i10) % size) + size) % size;
        mutableState2.setValue(list.get(i13).getId());
        mutableState3.setValue(list.get(i13).getId());
        mutableState4.setValue(list.get(i13).getId());
        map2.put(LiveTvScreen$lambda$13(mutableState5), list.get(i13).getId());
        mutableState6.setValue(null);
        mutableLongState2.setLongValue(0L);
        LiveTvScreen$lambda$143(mutableState7, false);
    }

    private static final LiveTvBufferProfile buildLiveTvBufferProfile(int i10, boolean z) {
        if (i10 < 256) {
            i10 = 256;
        }
        boolean z5 = z || i10 <= 384;
        return new LiveTvBufferProfile(15000, 30000, 1000, z5 ? 2500 : AuthApiStatusCodes.AUTH_API_INVALID_CREDENTIALS, (z5 ? 32 : 48) * ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES, 5000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long catchupInSegmentSeekOffset(IptvChannel iptvChannel, long j10) {
        if (j10 < 0) {
            j10 = 0;
        }
        long jCatchupUrlAnchorOffset = j10 - catchupUrlAnchorOffset(iptvChannel, j10);
        if (jCatchupUrlAnchorOffset < 0) {
            return 0L;
        }
        return jCatchupUrlAnchorOffset;
    }

    private static final EnrichedChannel catchupPlaybackVariant(EnrichedChannel enrichedChannel, List<EnrichedChannel> list) {
        Object next;
        if (!hasExplicitCatchupSource(enrichedChannel)) {
            ga.i iVarI = ga.r.I(ga.r.I(new ga.p(list, 3), new x2(enrichedChannel, LiveTvEnhancementsKt.variantGroupKey(enrichedChannel))), new w0(13));
            final Comparator comparator = new Comparator() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$catchupPlaybackVariant$$inlined$compareBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    return com.google.common.util.concurrent.r0.e(Integer.valueOf(((EnrichedChannel) t2).getSource().getCatchupDays()), Integer.valueOf(((EnrichedChannel) t10).getSource().getCatchupDays()));
                }
            };
            Comparator comparator2 = new Comparator() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$catchupPlaybackVariant$$inlined$thenBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    int iCompare = comparator.compare(t2, t10);
                    return iCompare != 0 ? iCompare : com.google.common.util.concurrent.r0.e(Integer.valueOf(LiveTvScreenKt.catchupQualityRank((EnrichedChannel) t2)), Integer.valueOf(LiveTvScreenKt.catchupQualityRank((EnrichedChannel) t10)));
                }
            };
            ga.h hVar = new ga.h(iVarI);
            if (hVar.hasNext()) {
                next = hVar.next();
                while (hVar.hasNext()) {
                    Object next2 = hVar.next();
                    if (comparator2.compare(next, next2) < 0) {
                        next = next2;
                    }
                }
            } else {
                next = null;
            }
            EnrichedChannel enrichedChannel2 = (EnrichedChannel) next;
            if (enrichedChannel2 != null) {
                return enrichedChannel2;
            }
        }
        return enrichedChannel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean catchupPlaybackVariant$lambda$0(EnrichedChannel enrichedChannel, String str, EnrichedChannel enrichedChannel2) {
        return !kotlin.jvm.internal.p.a(enrichedChannel2.getId(), enrichedChannel.getId()) && kotlin.jvm.internal.p.a(LiveTvEnhancementsKt.variantGroupKey(enrichedChannel2), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int catchupQualityRank(EnrichedChannel enrichedChannel) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[enrichedChannel.getQuality().ordinal()];
        if (i10 == 1) {
            return 4;
        }
        if (i10 == 2) {
            return 3;
        }
        if (i10 == 3) {
            return 2;
        }
        if (i10 == 4) {
            return 1;
        }
        throw new NoWhenBranchMatchedException();
    }

    private static final long catchupUrlAnchorOffset(IptvChannel iptvChannel, long j10) {
        String string;
        if (j10 < 0) {
            j10 = 0;
        }
        String catchupType = iptvChannel.getCatchupType();
        String lowerCase = (catchupType == null || (string = kotlin.text.o.L0(catchupType).toString()) == null) ? null : string.toLowerCase(Locale.ROOT);
        if (lowerCase == null) {
            lowerCase = "";
        }
        return (kotlin.collections.r.p0(new String[]{"xtream", "xc", "xciptv", "timeshift"}).contains(lowerCase) || iptvChannel.getXtreamStreamId() != null || kotlin.text.o.T(iptvChannel.getStreamUrl(), "/live/", true)) ? j10 - (j10 % 60000) : j10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String chooseStartupChannelId(List<EnrichedChannel> list, Set<String> set, String str, String str2, boolean z, List<String> list2, boolean z5) {
        Object next;
        if (str != null) {
            String str3 = set.contains(str) ? str : null;
            if (str3 != null) {
                return str3;
            }
        }
        if (str != null && !z5) {
            return null;
        }
        Iterator<T> it = list2.iterator();
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
        String str4 = (String) next;
        if (str4 != null) {
            return str4;
        }
        if (!list2.isEmpty() && !z5) {
            return null;
        }
        if (z) {
            String str5 = (kotlin.text.o.h0(str2) || !set.contains(str2)) ? null : str2;
            if (str5 != null) {
                return str5;
            }
            if (!kotlin.text.o.h0(str2) && !z5) {
                return null;
            }
        }
        return ((EnrichedChannel) kotlin.collections.x.m0(list)).getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String classifyPlaybackError(PlaybackException playbackException) {
        Integer numHttpResponseCode = httpResponseCode(playbackException);
        if (numHttpResponseCode != null) {
            return a0.c.i(numHttpResponseCode.intValue(), "provider returned HTTP ");
        }
        String lowerCase = playbackException.getErrorCodeName().toLowerCase(Locale.ROOT);
        return kotlin.text.o.T(lowerCase, "timeout", false) ? "network timeout" : (kotlin.text.o.T(lowerCase, "network", false) || kotlin.text.o.T(lowerCase, "io", false)) ? "network or provider error" : (kotlin.text.o.T(lowerCase, "parser", false) || kotlin.text.o.T(lowerCase, "manifest", false)) ? "stream format issue" : (kotlin.text.o.T(lowerCase, "decoder", false) || kotlin.text.o.T(lowerCase, "audio", false) || kotlin.text.o.T(lowerCase, "video", false)) ? "device codec issue" : "source did not start";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String compactIptvGroupKey(String str) throws IOException {
        if (str == null) {
            str = "";
        }
        String lowerCase = str.toLowerCase(Locale.ROOT);
        StringBuilder sb2 = new StringBuilder();
        int length = lowerCase.length();
        for (int i10 = 0; i10 < length; i10++) {
            char cCharAt = lowerCase.charAt(i10);
            if (Character.isLetterOrDigit(cCharAt)) {
                sb2.append(cCharAt);
            }
        }
        return sb2.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer countForCategory(LiveCategoryTree liveCategoryTree, String str) {
        ga.j jVar;
        int i10 = 3;
        ga.m mVarG = kotlin.collections.r.G(new ga.m[]{new ga.p(liveCategoryTree.getTop(), i10), new ga.p(liveCategoryTree.getGlobal().getCategories(), i10), new ga.p(liveCategoryTree.getCountries().getCategories(), i10), new ga.p(liveCategoryTree.getAdult().getCategories(), i10), new ga.p(liveCategoryTree.getHidden().getCategories(), i10)});
        com.arflix.tv.util.a aVar = new com.arflix.tv.util.a(1);
        if (mVarG instanceof ga.t) {
            ga.t tVar = (ga.t) mVarG;
            jVar = new ga.j(tVar.f15603a, tVar.f15604b, aVar);
        } else {
            jVar = new ga.j(mVarG, new com.arflix.tv.util.a(i10), aVar);
        }
        return countForCategory$findCount(jVar, str);
    }

    private static final Integer countForCategory$findCount(ga.m<LiveCategory> mVar, String str) {
        for (LiveCategory liveCategory : mVar) {
            if (kotlin.jvm.internal.p.a(liveCategory.getId(), str)) {
                return Integer.valueOf(liveCategory.getCount());
            }
            Integer numCountForCategory$findCount = countForCategory$findCount(new ga.p(liveCategory.getChildren(), 3), str);
            if (numCountForCategory$findCount != null) {
                return numCountForCategory$findCount;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0016  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0008  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x000f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Integer digitForTvKeyCode(int r0) {
        /*
            switch(r0) {
                case 7: goto L40;
                case 8: goto L3a;
                case 9: goto L34;
                case 10: goto L2e;
                case 11: goto L28;
                case 12: goto L22;
                case 13: goto L1c;
                case 14: goto L16;
                case 15: goto Lf;
                case 16: goto L8;
                default: goto L3;
            }
        L3:
            switch(r0) {
                case 144: goto L40;
                case 145: goto L3a;
                case 146: goto L34;
                case 147: goto L2e;
                case 148: goto L28;
                case 149: goto L22;
                case 150: goto L1c;
                case 151: goto L16;
                case 152: goto Lf;
                case 153: goto L8;
                default: goto L6;
            }
        L6:
            r0 = 0
            return r0
        L8:
            r0 = 9
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            return r0
        Lf:
            r0 = 8
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            return r0
        L16:
            r0 = 7
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            return r0
        L1c:
            r0 = 6
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            return r0
        L22:
            r0 = 5
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            return r0
        L28:
            r0 = 4
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            return r0
        L2e:
            r0 = 3
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            return r0
        L34:
            r0 = 2
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            return r0
        L3a:
            r0 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            return r0
        L40:
            r0 = 0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt.digitForTvKeyCode(int):java.lang.Integer");
    }

    private static final x6.x expandGuideWindowAfter(int i10, int i11, int i12) {
        if (i11 >= i12) {
            return new x6.x(Integer.valueOf(i10), Integer.valueOf(i11));
        }
        int i13 = i11 + 48;
        if (i13 <= i12) {
            i12 = i13;
        }
        int i14 = (i12 - i10) - 144;
        if (i14 < 0) {
            i14 = 0;
        }
        int i15 = i10 + i14;
        if (i15 > i12) {
            i15 = i12;
        }
        return new x6.x(Integer.valueOf(i15), Integer.valueOf(i12));
    }

    private static final x6.x expandGuideWindowBefore(int i10, int i11) {
        if (i10 <= 0) {
            return new x6.x(Integer.valueOf(i10), Integer.valueOf(i11));
        }
        int i12 = i10 - 48;
        if (i12 < 0) {
            i12 = 0;
        }
        int i13 = (i11 - i12) - 144;
        int i14 = i13 >= 0 ? i13 : 0;
        Integer numValueOf = Integer.valueOf(i12);
        int i15 = i11 - i14;
        if (i15 >= i12) {
            i12 = i15;
        }
        return new x6.x(numValueOf, Integer.valueOf(i12));
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

    private static final List<String> guideFallbackKeys(EnrichedChannel enrichedChannel) {
        String string = kotlin.text.o.L0(kotlin.text.o.G0(':', enrichedChannel.getId(), "")).toString();
        if (kotlin.text.o.h0(string)) {
            string = "default";
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        guideFallbackKeys$addKey(linkedHashSet, string, "epg", enrichedChannel.getSource().getEpgId());
        guideFallbackKeys$addKey(linkedHashSet, string, "tvg", enrichedChannel.getSource().getTvgName());
        String variantKey = enrichedChannel.getSource().getVariantKey();
        if (variantKey != null) {
            if (variantKey.equals(enrichedChannel.getSource().getId())) {
                variantKey = null;
            }
            if (variantKey != null) {
                guideFallbackKeys$addKey(linkedHashSet, string, "variant", variantKey);
            }
        }
        String strC0 = kotlin.text.o.C0('|', enrichedChannel.getName(), enrichedChannel.getName());
        LiveTvScreenRegexes liveTvScreenRegexes = LiveTvScreenRegexes.INSTANCE;
        guideFallbackKeys$addKey(linkedHashSet, string, ContentDisposition.Parameters.Name, com.arflix.tv.data.repository.g.o(liveTvScreenRegexes.getMULTI_SPACE(), liveTvScreenRegexes.getQUALITY_REMOVAL().f(" ", strC0), " "));
        return kotlin.collections.x.c1(linkedHashSet);
    }

    private static final void guideFallbackKeys$addKey(LinkedHashSet<String> linkedHashSet, String str, String str2, String str3) {
        String string;
        if (str3 == null || (string = kotlin.text.o.L0(str3).toString()) == null) {
            return;
        }
        String lowerCase = string.toLowerCase(Locale.ROOT);
        if (kotlin.text.o.h0(lowerCase)) {
            lowerCase = null;
        }
        if (lowerCase != null) {
            linkedHashSet.add(androidx.compose.material3.d.o(str, "|", str2, ":", lowerCase));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.x guideWindowAround(int i10, int i11) {
        if (i11 <= 0) {
            return new x6.x(0, 0);
        }
        int iN = qb.d.n(i10, 0, i11 - 1);
        if (iN < 0) {
            iN = 0;
        }
        int i12 = iN + 48;
        if (i12 <= i11) {
            i11 = i12;
        }
        int i13 = i11 - 48;
        return new x6.x(Integer.valueOf(i13 >= 0 ? i13 : 0), Integer.valueOf(i11));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasExplicitCatchupSource(EnrichedChannel enrichedChannel) {
        String catchupSource;
        IptvChannel source = enrichedChannel.getSource();
        if (source.getCatchupDays() > 0) {
            return true;
        }
        String catchupType = source.getCatchupType();
        if ((catchupType == null || kotlin.text.o.h0(catchupType)) && ((catchupSource = source.getCatchupSource()) == null || kotlin.text.o.h0(catchupSource))) {
            return kotlin.text.o.T(source.getStreamUrl(), "/timeshift/", true);
        }
        return true;
    }

    private static final boolean hasGuideData(IptvNowNext iptvNowNext) {
        if (iptvNowNext != null) {
            return (iptvNowNext.getNow() == null && iptvNowNext.getNext() == null && iptvNowNext.getLater() == null && iptvNowNext.getUpcoming().isEmpty() && iptvNowNext.getRecent().isEmpty()) ? false : true;
        }
        return false;
    }

    private static final boolean hasGuideIdentity(EnrichedChannel enrichedChannel) {
        String epgId = enrichedChannel.getSource().getEpgId();
        if (epgId != null && !kotlin.text.o.h0(epgId)) {
            return true;
        }
        String tvgName = enrichedChannel.getSource().getTvgName();
        return (tvgName == null || kotlin.text.o.h0(tvgName)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer httpResponseCode(PlaybackException playbackException) {
        for (Throwable cause = playbackException; cause != null; cause = cause.getCause()) {
            if (cause instanceof HttpDataSource.InvalidResponseCodeException) {
                return Integer.valueOf(((HttpDataSource.InvalidResponseCodeException) cause).responseCode);
            }
        }
        return null;
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

    private static final boolean looksLikeMpegTsUrl(String str) {
        String lowerCase = str.toLowerCase(Locale.ROOT);
        String strG0 = kotlin.text.o.G0('?', lowerCase, lowerCase);
        if (!kotlin.text.u.K(strG0, ".m3u8", false) && !kotlin.text.o.T(lowerCase, "output=m3u8", false)) {
            if (!kotlin.text.u.K(strG0, ".ts", false) && !kotlin.text.u.K(strG0, "timeshift.php", false) && !kotlin.text.o.T(lowerCase, "output=ts", false) && !kotlin.text.o.T(strG0, "/timeshift/", false)) {
                List listX0 = kotlin.text.o.x0(kotlin.text.o.M0(kotlin.text.o.C0('/', kotlin.text.o.D0(strG0, "://", ""), ""), '/'), new char[]{'/'}, 6);
                ArrayList arrayList = new ArrayList();
                for (Object obj : listX0) {
                    if (!kotlin.text.o.h0((String) obj)) {
                        arrayList.add(obj);
                    }
                }
                if (arrayList.size() >= 4 && kotlin.text.u.L((String) kotlin.collections.x.m0(arrayList), "live", true)) {
                    String str2 = (String) kotlin.collections.x.w0(arrayList);
                    if (kotlin.text.u.R(kotlin.text.o.G0('.', str2, str2)) != null) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String looseIptvGroupKey(String str) {
        if (str == null) {
            str = "";
        }
        return IptvGroupWhitespaceRegex.f(" ", IptvGroupPipeSpacingRegex.f("|", kotlin.text.o.L0(str).toString())).toLowerCase(Locale.ROOT);
    }

    private static final IptvNowNext mergeGuideSlices(IptvNowNext iptvNowNext, IptvNowNext iptvNowNext2) {
        if (hasGuideData(iptvNowNext)) {
            if (!hasGuideData(iptvNowNext2)) {
                return iptvNowNext;
            }
            if (iptvNowNext != null) {
                if (iptvNowNext2 == null) {
                    return iptvNowNext;
                }
                IptvProgram now = iptvNowNext.getNow();
                if (now == null) {
                    now = iptvNowNext2.getNow();
                }
                IptvProgram iptvProgram = now;
                IptvProgram next = iptvNowNext.getNext();
                if (next == null) {
                    next = iptvNowNext2.getNext();
                }
                IptvProgram iptvProgram2 = next;
                IptvProgram later = iptvNowNext.getLater();
                if (later == null) {
                    later = iptvNowNext2.getLater();
                }
                return new IptvNowNext(iptvProgram, iptvProgram2, later, mergeProgramLists(iptvNowNext.getUpcoming(), iptvNowNext2.getUpcoming()), mergeProgramLists(iptvNowNext.getRecent(), iptvNowNext2.getRecent()));
            }
        }
        return iptvNowNext2;
    }

    private static final List<IptvProgram> mergeProgramLists(List<IptvProgram> list, List<IptvProgram> list2) {
        if (list.isEmpty()) {
            return list2;
        }
        if (list2.isEmpty()) {
            return list;
        }
        ArrayList arrayListI0 = kotlin.collections.x.I0(list, list2);
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : arrayListI0) {
            IptvProgram iptvProgram = (IptvProgram) obj;
            if (hashSet.add(iptvProgram.getStartUtcMillis() + ":" + iptvProgram.getEndUtcMillis() + ":" + iptvProgram.getTitle())) {
                arrayList.add(obj);
            }
        }
        return kotlin.collections.x.W0(arrayList, new Comparator() { // from class: com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt$mergeProgramLists$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return com.google.common.util.concurrent.r0.e(Long.valueOf(((IptvProgram) t2).getStartUtcMillis()), Long.valueOf(((IptvProgram) t10).getStartUtcMillis()));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String redactPlaybackUrl(String str) {
        String string;
        String string2;
        LiveTvScreenRegexes liveTvScreenRegexes = LiveTvScreenRegexes.INSTANCE;
        kotlin.text.m query_secrets = liveTvScreenRegexes.getQUERY_SECRETS();
        query_secrets.getClass();
        kotlin.text.k kVarB = kotlin.text.m.b(query_secrets, str);
        int i10 = 0;
        if (kVarB == null) {
            string = str.toString();
        } else {
            int length = str.length();
            StringBuilder sb2 = new StringBuilder(length);
            int i11 = 0;
            do {
                sb2.append((CharSequence) str, i11, kVarB.d().f22619i);
                sb2.append(redactPlaybackUrl$lambda$0(kVarB));
                i11 = kVarB.d().f22620l + 1;
                kVarB = kVarB.next();
                if (i11 >= length) {
                    break;
                }
            } while (kVarB != null);
            if (i11 < length) {
                sb2.append((CharSequence) str, i11, length);
            }
            string = sb2.toString();
        }
        kotlin.text.m iptv_url_redact_regex = liveTvScreenRegexes.getIPTV_URL_REDACT_REGEX();
        iptv_url_redact_regex.getClass();
        kotlin.text.k kVarB2 = kotlin.text.m.b(iptv_url_redact_regex, string);
        if (kVarB2 == null) {
            string2 = string.toString();
        } else {
            int length2 = string.length();
            StringBuilder sb3 = new StringBuilder(length2);
            do {
                sb3.append((CharSequence) string, i10, kVarB2.d().f22619i);
                sb3.append(redactPlaybackUrl$lambda$1(kVarB2));
                i10 = kVarB2.d().f22620l + 1;
                kVarB2 = kVarB2.next();
                if (i10 >= length2) {
                    break;
                }
            } while (kVarB2 != null);
            if (i10 < length2) {
                sb3.append((CharSequence) string, i10, length2);
            }
            string2 = sb3.toString();
        }
        return kotlin.text.o.I0(260, string2);
    }

    private static final CharSequence redactPlaybackUrl$lambda$0(kotlin.text.i iVar) {
        return iVar.b().get(1) + "***";
    }

    private static final CharSequence redactPlaybackUrl$lambda$1(kotlin.text.i iVar) {
        return iVar.b().get(1) + "***/***" + iVar.b().get(4);
    }

    private static final Map<String, String> safePlaybackHeaders(Map<String, String> map) {
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

    public static final List<IptvChannel> selectPagedChannelsInProviderOrder(String str, List<IptvChannel> list, List<IptvChannel> list2, List<IptvChannel> list3, int i10) {
        if (str.equals("fav")) {
            list = list2;
        } else if (str.equals("recent")) {
            list = list3;
        }
        if (i10 < 0) {
            i10 = 0;
        }
        return kotlin.collections.x.X0(list, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IptvProgram shiftedForCatchup(IptvProgram iptvProgram, long j10) {
        long endUtcMillis = (iptvProgram.getEndUtcMillis() - iptvProgram.getStartUtcMillis()) - 1000;
        long jO = qb.d.o(j10, 0L, endUtcMillis < 0 ? 0L : endUtcMillis);
        if (jO <= 0) {
            return iptvProgram;
        }
        long startUtcMillis = iptvProgram.getStartUtcMillis() + jO;
        long endUtcMillis2 = iptvProgram.getEndUtcMillis() - 1000;
        return IptvProgram.copy$default(iptvProgram, null, null, startUtcMillis > endUtcMillis2 ? endUtcMillis2 : startUtcMillis, 0L, null, 27, null);
    }

    private static final boolean supportsCatchupHistory(EnrichedChannel enrichedChannel) {
        IptvChannel source;
        String catchupSource;
        if (enrichedChannel == null || (source = enrichedChannel.getSource()) == null) {
            return false;
        }
        if (source.getCatchupDays() > 0) {
            return true;
        }
        String catchupType = source.getCatchupType();
        return !(catchupType == null || kotlin.text.o.h0(catchupType)) || !((catchupSource = source.getCatchupSource()) == null || kotlin.text.o.h0(catchupSource)) || kotlin.text.o.T(source.getStreamUrl(), "/timeshift/", true) || source.getXtreamStreamId() != null || kotlin.text.o.T(source.getStreamUrl(), "/live/", true);
    }
}
