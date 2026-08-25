package com.arflix.tv.ui.screens.tv;

import android.content.Context;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.fragment.app.a2;
import androidx.lifecycle.d1;
import androidx.lifecycle.z0;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.data.model.IptvSnapshot;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.IptvConfig;
import com.arflix.tv.data.repository.IptvPlaybackUrlResolver;
import com.arflix.tv.data.repository.IptvPlaylistEntry;
import com.arflix.tv.data.repository.IptvRepository;
import com.arflix.tv.data.repository.IptvTvSessionState;
import com.arflix.tv.network.OkHttpProvider;
import com.arflix.tv.ui.screens.tv.live.LiveTvGuideSources;
import com.arflix.tv.util.AppLogger;
import com.google.android.gms.cast.MediaError;
import dagger.hilt.android.qualifiers.ApplicationContext;
import io.ktor.http.LinkHeader;
import io.ktor.network.sockets.DatagramKt;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import ka.m0;
import ka.o2;
import ka.s0;
import ka.v1;
import ka.x0;
import kotlin.Metadata;
import na.h1;
import na.j1;
import na.q0;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000î\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\"\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u001f\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0003\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0007\u0018\u00002\u00020\u0001:\u0002ù\u0001B#\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ)\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\n¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u0015\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0014¢\u0006\u0004\b\u0019\u0010\u0017J\u0015\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u0014¢\u0006\u0004\b\u001b\u0010\u0017J\u0015\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0014¢\u0006\u0004\b\u001c\u0010\u0017J\u001f\u0010\u001c\u001a\u00020\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0018\u001a\u00020\u0014¢\u0006\u0004\b\u001c\u0010\u001eJC\u0010&\u001a\u00020\u000e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00140\u001f2\b\u0010!\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010#\u001a\u00020\"2\b\b\u0002\u0010$\u001a\u00020\"2\b\b\u0002\u0010%\u001a\u00020\n¢\u0006\u0004\b&\u0010'J!\u0010)\u001a\u00020\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u00142\b\b\u0002\u0010(\u001a\u00020\n¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b+\u0010\u0017J\u0015\u0010,\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0014¢\u0006\u0004\b,\u0010\u0017J\u001f\u0010,\u001a\u00020\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0018\u001a\u00020\u0014¢\u0006\u0004\b,\u0010\u001eJ\u0015\u0010-\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0014¢\u0006\u0004\b-\u0010\u0017J\u001f\u0010-\u001a\u00020\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0018\u001a\u00020\u0014¢\u0006\u0004\b-\u0010\u001eJ\u0015\u0010.\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0014¢\u0006\u0004\b.\u0010\u0017J\u001f\u0010.\u001a\u00020\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0018\u001a\u00020\u0014¢\u0006\u0004\b.\u0010\u001eJ3\u00103\u001a\u00020\u000e2\b\u0010/\u001a\u0004\u0018\u00010\u00142\b\u00100\u001a\u0004\u0018\u00010\u00142\u0006\u00101\u001a\u00020\u00142\b\b\u0002\u00102\u001a\u00020\n¢\u0006\u0004\b3\u00104J8\u0010>\u001a\u00020;2\u0006\u00106\u001a\u0002052\n\b\u0002\u00108\u001a\u0004\u0018\u0001072\b\b\u0002\u00109\u001a\u00020\n2\b\b\u0002\u0010:\u001a\u00020\"H\u0080@¢\u0006\u0004\b<\u0010=J\u0017\u0010@\u001a\u00020\u00142\u0006\u0010?\u001a\u00020\"H\u0002¢\u0006\u0004\b@\u0010AJ\u000f\u0010B\u001a\u00020\u000eH\u0002¢\u0006\u0004\bB\u0010CJ\u000f\u0010D\u001a\u00020\u000eH\u0002¢\u0006\u0004\bD\u0010CJ\u000f\u0010E\u001a\u00020\u000eH\u0002¢\u0006\u0004\bE\u0010CJ\u0017\u0010H\u001a\u00020\n2\u0006\u0010G\u001a\u00020FH\u0002¢\u0006\u0004\bH\u0010IJ\u0019\u0010L\u001a\u00020\n2\b\u0010K\u001a\u0004\u0018\u00010JH\u0002¢\u0006\u0004\bL\u0010MJ\u0019\u0010N\u001a\u00020\n2\b\u0010K\u001a\u0004\u0018\u00010JH\u0002¢\u0006\u0004\bN\u0010MJ\u0019\u0010O\u001a\u00020\n2\b\u0010K\u001a\u0004\u0018\u00010JH\u0002¢\u0006\u0004\bO\u0010MJ\u0019\u0010P\u001a\u00020\n2\b\u00106\u001a\u0004\u0018\u000105H\u0002¢\u0006\u0004\bP\u0010QJ#\u0010T\u001a\u00020\"2\b\u0010K\u001a\u0004\u0018\u00010J2\b\b\u0002\u0010S\u001a\u00020RH\u0002¢\u0006\u0004\bT\u0010UJ-\u0010V\u001a\u00020\n2\b\u00106\u001a\u0004\u0018\u0001052\b\u0010K\u001a\u0004\u0018\u00010J2\b\b\u0002\u0010S\u001a\u00020RH\u0002¢\u0006\u0004\bV\u0010WJ\u0019\u0010X\u001a\u00020R2\b\u00106\u001a\u0004\u0018\u000105H\u0002¢\u0006\u0004\bX\u0010YJ#\u0010Z\u001a\u00020\n2\b\u0010K\u001a\u0004\u0018\u00010J2\b\b\u0002\u0010S\u001a\u00020RH\u0002¢\u0006\u0004\bZ\u0010[J\u0010\u0010\\\u001a\u00020\u000eH\u0082@¢\u0006\u0004\b\\\u0010]J\u001e\u0010\\\u001a\u00020\u000e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00140^H\u0082@¢\u0006\u0004\b\\\u0010_J#\u0010b\u001a\u00020\u000e2\u0012\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020J0`H\u0002¢\u0006\u0004\bb\u0010cJ\u001f\u0010g\u001a\u00020F2\u0006\u0010d\u001a\u00020F2\u0006\u0010f\u001a\u00020eH\u0002¢\u0006\u0004\bg\u0010hJI\u0010n\u001a\u00020F2\u0006\u0010G\u001a\u00020F2\u0018\u0010i\u001a\u0014\u0012\u0004\u0012\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002050\u001f0`2\u0006\u0010k\u001a\u00020j2\u000e\b\u0002\u0010m\u001a\b\u0012\u0004\u0012\u00020\u00140lH\u0002¢\u0006\u0004\bn\u0010oJ!\u0010r\u001a\u00020J2\b\u0010p\u001a\u0004\u0018\u00010J2\u0006\u0010q\u001a\u00020JH\u0002¢\u0006\u0004\br\u0010sJ\u000f\u0010t\u001a\u00020\nH\u0002¢\u0006\u0004\bt\u0010uJ\u000f\u0010v\u001a\u00020\nH\u0002¢\u0006\u0004\bv\u0010uJ!\u0010y\u001a\u0004\u0018\u0001052\u0006\u0010w\u001a\u00020e2\u0006\u0010x\u001a\u00020\u0014H\u0002¢\u0006\u0004\by\u0010zJ\u001d\u0010{\u001a\u00020\u000e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00140lH\u0002¢\u0006\u0004\b{\u0010|J\u001d\u0010}\u001a\u00020\u000e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00140lH\u0002¢\u0006\u0004\b}\u0010|J\u001d\u0010~\u001a\u00020\u000e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00140lH\u0002¢\u0006\u0004\b~\u0010|J/\u0010\u0081\u0001\u001a\b\u0012\u0004\u0012\u00020\u00140^2\f\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020\u00140^2\u0007\u0010\u0080\u0001\u001a\u00020\"H\u0002¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J1\u0010\u0084\u0001\u001a\b\u0012\u0004\u0012\u00020\u00140^2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00140l2\t\b\u0002\u0010\u0083\u0001\u001a\u00020\nH\u0002¢\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001J\u001f\u0010\u0086\u0001\u001a\u00020\u000e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00140lH\u0002¢\u0006\u0005\b\u0086\u0001\u0010|J\u001a\u0010\u0088\u0001\u001a\u00020\u000e2\u0007\u0010\u0087\u0001\u001a\u00020\nH\u0002¢\u0006\u0005\b\u0088\u0001\u0010\u0013J \u0010\u008a\u0001\u001a\u00020\u000e2\r\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020\u00140lH\u0002¢\u0006\u0005\b\u008a\u0001\u0010|J\u0011\u0010\u008b\u0001\u001a\u00020\u000eH\u0002¢\u0006\u0005\b\u008b\u0001\u0010CJ\u001b\u0010\u008d\u0001\u001a\u00030\u008c\u00012\u0006\u0010G\u001a\u00020FH\u0002¢\u0006\u0006\b\u008d\u0001\u0010\u008e\u0001J\u0011\u0010\u008f\u0001\u001a\u00020\u000eH\u0002¢\u0006\u0005\b\u008f\u0001\u0010CJ\u0011\u0010\u0090\u0001\u001a\u00020\u000eH\u0002¢\u0006\u0005\b\u0090\u0001\u0010CJ-\u0010\u0091\u0001\u001a\u00020\u000e2\b\b\u0002\u0010\u000b\u001a\u00020\n2\u000f\b\u0002\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020\u00140lH\u0002¢\u0006\u0006\b\u0091\u0001\u0010\u0092\u0001J\"\u0010\u0093\u0001\u001a\u00020\u000e2\u000f\b\u0002\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020\u00140lH\u0002¢\u0006\u0005\b\u0093\u0001\u0010|J*\u0010\u0094\u0001\u001a\u00020\u000e2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00140\u001f2\b\u0010!\u001a\u0004\u0018\u00010\u0014H\u0002¢\u0006\u0006\b\u0094\u0001\u0010\u0095\u0001J\u0011\u0010\u0096\u0001\u001a\u00020\u000eH\u0002¢\u0006\u0005\b\u0096\u0001\u0010CJ\u001c\u0010\u0099\u0001\u001a\u00030\u0098\u00012\u0007\u0010\u0097\u0001\u001a\u00020\"H\u0002¢\u0006\u0006\b\u0099\u0001\u0010\u009a\u0001J$\u0010\u009b\u0001\u001a\b\u0012\u0004\u0012\u00020\u00140\u001f2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0014H\u0002¢\u0006\u0006\b\u009b\u0001\u0010\u009c\u0001J$\u0010\u009e\u0001\u001a\u00020\u00142\u0007\u0010\u009d\u0001\u001a\u00020\u00142\u0006\u00109\u001a\u00020\nH\u0082@¢\u0006\u0006\b\u009e\u0001\u0010\u009f\u0001J\u001b\u0010¡\u0001\u001a\u00020\u000e2\u0007\u0010 \u0001\u001a\u00020eH\u0002¢\u0006\u0006\b¡\u0001\u0010¢\u0001J>\u0010§\u0001\u001a\u00020\u000e2\b\u0010¤\u0001\u001a\u00030£\u00012\u0007\u0010¥\u0001\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0007\u0010¦\u0001\u001a\u00020\nH\u0002¢\u0006\u0006\b§\u0001\u0010¨\u0001J$\u0010«\u0001\u001a\u00020\n2\u0007\u0010©\u0001\u001a\u00020e2\u0007\u0010ª\u0001\u001a\u00020eH\u0002¢\u0006\u0006\b«\u0001\u0010¬\u0001J\u0011\u0010\u00ad\u0001\u001a\u00020\u000eH\u0002¢\u0006\u0005\b\u00ad\u0001\u0010CJ\u0011\u0010®\u0001\u001a\u00020\u000eH\u0002¢\u0006\u0005\b®\u0001\u0010CR\u0015\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0003\u0010¯\u0001R\u001a\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\u000f\n\u0005\b\u0005\u0010°\u0001\u001a\u0006\b±\u0001\u0010²\u0001R\u0015\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0007\u0010³\u0001R\u001e\u0010µ\u0001\u001a\t\u0012\u0004\u0012\u00020e0´\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bµ\u0001\u0010¶\u0001R#\u0010¸\u0001\u001a\t\u0012\u0004\u0012\u00020e0·\u00018\u0006¢\u0006\u0010\n\u0006\b¸\u0001\u0010¹\u0001\u001a\u0006\bº\u0001\u0010»\u0001R\u001c\u0010½\u0001\u001a\u0005\u0018\u00010¼\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b½\u0001\u0010¾\u0001R\u001c\u0010¿\u0001\u001a\u0005\u0018\u00010¼\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¿\u0001\u0010¾\u0001R\u001c\u0010À\u0001\u001a\u0005\u0018\u00010¼\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÀ\u0001\u0010¾\u0001R\u0019\u0010Á\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÁ\u0001\u0010Â\u0001R\u001c\u0010Ã\u0001\u001a\u0005\u0018\u00010¼\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÃ\u0001\u0010¾\u0001R\u001c\u0010Ä\u0001\u001a\u0005\u0018\u00010¼\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÄ\u0001\u0010¾\u0001R\u001b\u0010Å\u0001\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÅ\u0001\u0010Æ\u0001R\u0019\u0010Ç\u0001\u001a\u00020R8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÇ\u0001\u0010È\u0001R\u001c\u0010É\u0001\u001a\u0005\u0018\u00010¼\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÉ\u0001\u0010¾\u0001R\u001b\u0010Ê\u0001\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÊ\u0001\u0010Æ\u0001R\u0019\u0010Ë\u0001\u001a\u00020R8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bË\u0001\u0010È\u0001R\u0018\u0010Í\u0001\u001a\u00030Ì\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÍ\u0001\u0010Î\u0001R)\u0010Ñ\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u00140Ï\u0001j\t\u0012\u0004\u0012\u00020\u0014`Ð\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÑ\u0001\u0010Ò\u0001R\u001b\u0010Ó\u0001\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÓ\u0001\u0010Æ\u0001R\u001c\u0010Ô\u0001\u001a\u0005\u0018\u00010¼\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÔ\u0001\u0010¾\u0001R\u001b\u0010Õ\u0001\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÕ\u0001\u0010Æ\u0001R\u001c\u0010Ö\u0001\u001a\u0005\u0018\u00010¼\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÖ\u0001\u0010¾\u0001R\u001b\u0010×\u0001\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b×\u0001\u0010Æ\u0001R\u001c\u0010Ø\u0001\u001a\u0005\u0018\u00010¼\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bØ\u0001\u0010¾\u0001R\u001b\u0010Ù\u0001\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÙ\u0001\u0010Æ\u0001R\u0019\u0010Ú\u0001\u001a\u00020R8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÚ\u0001\u0010È\u0001R\u0019\u0010Û\u0001\u001a\u00020R8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÛ\u0001\u0010È\u0001R\u0019\u0010Ü\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÜ\u0001\u0010Â\u0001R\u0019\u0010Ý\u0001\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÝ\u0001\u0010Â\u0001R)\u0010Þ\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u00140Ï\u0001j\t\u0012\u0004\u0012\u00020\u0014`Ð\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÞ\u0001\u0010Ò\u0001R\u001c\u0010ß\u0001\u001a\u0005\u0018\u00010¼\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bß\u0001\u0010¾\u0001R\u001c\u0010à\u0001\u001a\u0005\u0018\u00010¼\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bà\u0001\u0010¾\u0001R\u0019\u0010á\u0001\u001a\u00020R8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bá\u0001\u0010È\u0001R5\u0010ä\u0001\u001a \u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140â\u0001j\u000f\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0014`ã\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bä\u0001\u0010å\u0001R!\u0010ë\u0001\u001a\u00030æ\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bç\u0001\u0010è\u0001\u001a\u0006\bé\u0001\u0010ê\u0001R5\u0010ì\u0001\u001a \u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020R0â\u0001j\u000f\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020R`ã\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bì\u0001\u0010å\u0001R5\u0010í\u0001\u001a \u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020R0â\u0001j\u000f\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020R`ã\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bí\u0001\u0010å\u0001R\u0018\u0010î\u0001\u001a\u00030Ì\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bî\u0001\u0010Î\u0001R)\u0010ï\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u00140Ï\u0001j\t\u0012\u0004\u0012\u00020\u0014`Ð\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bï\u0001\u0010Ò\u0001R,\u0010ð\u0001\u001a\u0005\u0018\u00010Ì\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\bð\u0001\u0010Î\u0001\u001a\u0006\bñ\u0001\u0010ò\u0001\"\u0006\bó\u0001\u0010ô\u0001R*\u0010õ\u0001\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0086\u000e¢\u0006\u0017\n\u0006\bõ\u0001\u0010Æ\u0001\u001a\u0006\bö\u0001\u0010÷\u0001\"\u0005\bø\u0001\u0010\u0017¨\u0006ú\u0001"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/TvViewModel;", "Landroidx/lifecycle/d1;", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/IptvRepository;", "iptvRepository", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "cloudSyncRepository", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/IptvRepository;Lcom/arflix/tv/data/repository/CloudSyncRepository;)V", "", "force", "showLoading", "forceEpg", "Lx6/t0;", "refresh", "(ZZZ)V", "active", "setLiveTvPlaybackActive", "(Z)V", "", "query", "setQuery", "(Ljava/lang/String;)V", "groupName", "toggleFavoriteGroup", "channelId", "toggleFavoriteChannel", "toggleHiddenGroup", "playlistId", "(Ljava/lang/String;Ljava/lang/String;)V", "", "channelIds", "selectedChannelId", "", "eagerLimit", "backgroundLimit", "allowFocusedNetworkRefresh", "prefetchVisibleCategoryEpg", "(Ljava/util/List;Ljava/lang/String;IIZ)V", "forceNetworkForLargeList", "refreshCurrentChannelEpg", "(Ljava/lang/String;Z)V", "refreshCatchupHistoryForChannel", "moveGroupUp", "moveGroupToTop", "moveGroupDown", "lastChannelId", "lastGroupName", "lastFocusedZone", "markOpened", "rememberTvSession", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "Lcom/arflix/tv/data/model/IptvChannel;", "channel", "Lcom/arflix/tv/data/model/IptvProgram;", "program", "forceRefresh", "catchupAttempt", "Lcom/arflix/tv/data/repository/IptvPlaybackTarget;", "resolvePlayableStreamUrl$app_playRelease", "(Lcom/arflix/tv/data/model/IptvChannel;Lcom/arflix/tv/data/model/IptvProgram;ZILd7/d;)Ljava/lang/Object;", "resolvePlayableStreamUrl", "count", "countBucket", "(I)Ljava/lang/String;", "observeTvSession", "()V", "observeConfigAndFavorites", "warmXtreamVodCache", "Lcom/arflix/tv/data/model/IptvSnapshot;", "snapshot", "hasAnyEpgData", "(Lcom/arflix/tv/data/model/IptvSnapshot;)Z", "Lcom/arflix/tv/data/model/IptvNowNext;", "item", "hasProgramData", "(Lcom/arflix/tv/data/model/IptvNowNext;)Z", "hasUsefulVisibleGuideData", "hasRichSelectedGuideData", "supportsCatchup", "(Lcom/arflix/tv/data/model/IptvChannel;)Z", "", "now", "recentCatchupCount", "(Lcom/arflix/tv/data/model/IptvNowNext;J)I", "hasRecentCatchupHistory", "(Lcom/arflix/tv/data/model/IptvChannel;Lcom/arflix/tv/data/model/IptvNowNext;J)Z", "catchupHistoryTargetWindowMs", "(Lcom/arflix/tv/data/model/IptvChannel;)J", "hasRecentAiredHistory", "(Lcom/arflix/tv/data/model/IptvNowNext;J)Z", "refreshGuideFromCache", "(Ld7/d;)Ljava/lang/Object;", "", "(Ljava/util/Set;Ld7/d;)Ljava/lang/Object;", "", "updated", "mergeNowNext", "(Ljava/util/Map;)V", "incoming", "Lcom/arflix/tv/ui/screens/tv/TvUiState;", "current", "mergeIncomingSnapshotWithCurrentGuide", "(Lcom/arflix/tv/data/model/IptvSnapshot;Lcom/arflix/tv/ui/screens/tv/TvUiState;)Lcom/arflix/tv/data/model/IptvSnapshot;", "channelsByGroup", "Lcom/arflix/tv/data/repository/IptvTvSessionState;", "tvSession", "", "keepChannelIds", "capLargeListGuideSnapshot", "(Lcom/arflix/tv/data/model/IptvSnapshot;Ljava/util/Map;Lcom/arflix/tv/data/repository/IptvTvSessionState;Ljava/util/Collection;)Lcom/arflix/tv/data/model/IptvSnapshot;", "existing", "fresh", "mergeGuideSlice", "(Lcom/arflix/tv/data/model/IptvNowNext;Lcom/arflix/tv/data/model/IptvNowNext;)Lcom/arflix/tv/data/model/IptvNowNext;", "shouldEmitEpgSpinnerState", "()Z", "isActiveLargeIptvList", "state", TtmlNode.ATTR_ID, "lookupChannelById", "(Lcom/arflix/tv/ui/screens/tv/TvUiState;Ljava/lang/String;)Lcom/arflix/tv/data/model/IptvChannel;", "markEpgLoading", "(Ljava/util/Collection;)V", "clearEpgLoading", "finishEpgAttempt", "ids", "limit", "capChannelStateSet", "(Ljava/util/Set;I)Ljava/util/Set;", "allowLargeListFocusedRefresh", "claimEpgNetworkRefresh", "(Ljava/util/Collection;Z)Ljava/util/Set;", "releaseEpgNetworkRefresh", "inProgress", "setEpgBackfillInProgress", "priorityChannelIds", "deferCompleteEpgBackfill", "scheduleDeferredCompleteEpgBackfill", "", "epgCoverageRatio", "(Lcom/arflix/tv/data/model/IptvSnapshot;)F", "startPeriodicEpgRefresh", "startFullEpgWarmup", "startCompleteEpgBackfill", "(ZLjava/util/Collection;)V", "requestVisibleCompleteEpgBackfill", "enqueueVisibleEpgRefresh", "(Ljava/util/List;Ljava/lang/String;)V", "startVisibleEpgDrain", "maxChannels", "Lcom/arflix/tv/ui/screens/tv/TvViewModel$VisibleEpgDrain;", "drainVisibleEpgBatch", "(I)Lcom/arflix/tv/ui/screens/tv/TvViewModel$VisibleEpgDrain;", "currentVisiblePlaylistGroups", "(Ljava/lang/String;)Ljava/util/List;", "rawUrl", "resolveStalkerStreamIfNeeded", "(Ljava/lang/String;ZLd7/d;)Ljava/lang/Object;", "nextState", "setUiState", "(Lcom/arflix/tv/ui/screens/tv/TvUiState;)V", "", "error", "phase", "hasExistingChannels", "logIptvRefreshFailure", "(Ljava/lang/Throwable;Ljava/lang/String;ZZZ)V", "previous", LinkHeader.Rel.Next, "canReusePreparedContent", "(Lcom/arflix/tv/ui/screens/tv/TvUiState;Lcom/arflix/tv/ui/screens/tv/TvUiState;)Z", "maybeWarmStartupGuide", "scheduleIptvCloudSync", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/IptvRepository;", "getIptvRepository", "()Lcom/arflix/tv/data/repository/IptvRepository;", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "Lna/q0;", "_uiState", "Lna/q0;", "Lna/h1;", "uiState", "Lna/h1;", "getUiState", "()Lna/h1;", "Lka/v1;", "refreshJob", "Lka/v1;", "epgRefreshJob", "warmVodJob", "pendingForcedReload", "Z", "periodicEpgJob", "iptvCloudSyncJob", "lastObservedConfigSignature", "Ljava/lang/String;", "lastAutomaticEpgReloadAt", "J", "visibleEpgRefreshJob", "lastVisibleEpgRefreshKey", "lastVisibleEpgRefreshAt", "", "visibleEpgQueueLock", "Ljava/lang/Object;", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "pendingVisibleEpgChannelIds", "Ljava/util/LinkedHashSet;", "pendingVisibleEpgSelectedChannelId", "tvSessionSaveJob", "startupGuideWarmupKey", "fullEpgWarmupJob", "lastFullEpgWarmupKey", "completeEpgBackfillJob", "lastCompleteEpgBackfillKey", "lastVisibleForcedCompleteEpgAt", "lastCompleteEpgBackfillCompletedAt", "liveTvPlaybackActive", "deferredCompleteEpgBackfill", "deferredCompleteEpgPriorityIds", "deferredCompleteEpgBackfillJob", "preparedContentJob", "preparedContentRevision", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "resolvedStalkerStreamCache", "Ljava/util/LinkedHashMap;", "Lcom/arflix/tv/data/repository/IptvPlaybackUrlResolver;", "iptvPlaybackUrlResolver$delegate", "Lx6/s;", "getIptvPlaybackUrlResolver", "()Lcom/arflix/tv/data/repository/IptvPlaybackUrlResolver;", "iptvPlaybackUrlResolver", "catchupHistoryRefreshAt", "currentChannelEpgRefreshAt", "epgNetworkRefreshLock", "epgNetworkRefreshInFlight", "cachedEnrichedChannels", "getCachedEnrichedChannels", "()Ljava/lang/Object;", "setCachedEnrichedChannels", "(Ljava/lang/Object;)V", "cachedChannelsSignature", "getCachedChannelsSignature", "()Ljava/lang/String;", "setCachedChannelsSignature", "VisibleEpgDrain", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class TvViewModel extends d1 {
    public static final int $stable = 8;
    private final q0<TvUiState> _uiState;
    private volatile String cachedChannelsSignature;
    private volatile Object cachedEnrichedChannels;
    private final LinkedHashMap<String, Long> catchupHistoryRefreshAt;
    private final CloudSyncRepository cloudSyncRepository;
    private v1 completeEpgBackfillJob;
    private final Context context;
    private final LinkedHashMap<String, Long> currentChannelEpgRefreshAt;
    private boolean deferredCompleteEpgBackfill;
    private v1 deferredCompleteEpgBackfillJob;
    private final LinkedHashSet<String> deferredCompleteEpgPriorityIds;
    private final LinkedHashSet<String> epgNetworkRefreshInFlight;
    private final Object epgNetworkRefreshLock;
    private v1 epgRefreshJob;
    private v1 fullEpgWarmupJob;
    private v1 iptvCloudSyncJob;

    /* JADX INFO: renamed from: iptvPlaybackUrlResolver$delegate, reason: from kotlin metadata */
    private final x6.s iptvPlaybackUrlResolver;
    private final IptvRepository iptvRepository;
    private long lastAutomaticEpgReloadAt;
    private long lastCompleteEpgBackfillCompletedAt;
    private String lastCompleteEpgBackfillKey;
    private String lastFullEpgWarmupKey;
    private String lastObservedConfigSignature;
    private long lastVisibleEpgRefreshAt;
    private String lastVisibleEpgRefreshKey;
    private long lastVisibleForcedCompleteEpgAt;
    private boolean liveTvPlaybackActive;
    private boolean pendingForcedReload;
    private final LinkedHashSet<String> pendingVisibleEpgChannelIds;
    private String pendingVisibleEpgSelectedChannelId;
    private v1 periodicEpgJob;
    private v1 preparedContentJob;
    private long preparedContentRevision;
    private v1 refreshJob;
    private final LinkedHashMap<String, String> resolvedStalkerStreamCache;
    private String startupGuideWarmupKey;
    private v1 tvSessionSaveJob;
    private final h1<TvUiState> uiState;
    private final Object visibleEpgQueueLock;
    private v1 visibleEpgRefreshJob;
    private v1 warmVodJob;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$1", f = "TvViewModel.kt", l = {164, 166, 167, 169}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = TvViewModel.this.new AnonymousClass1(dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0057, code lost:
        
            if (r1.warmupFromCacheOnly(r36) == r8) goto L33;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x007f, code lost:
        
            if (r1 == r8) goto L33;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x009a, code lost:
        
            if (r2 == r8) goto L33;
         */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0071  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0084 A[PHI: r1
          0x0084: PHI (r1v11 com.arflix.tv.data.model.IptvSnapshot) = (r1v7 com.arflix.tv.data.model.IptvSnapshot), (r1v14 com.arflix.tv.data.model.IptvSnapshot) binds: [B:25:0x006f, B:29:0x0082] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:31:0x0086  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x01bf  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r37) {
            /*
                Method dump skipped, instruction units count: 460
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0004HÆ\u0003J%\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0004HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/TvViewModel$VisibleEpgDrain;", "", "ids", "", "", "selectedId", "<init>", "(Ljava/util/List;Ljava/lang/String;)V", "getIds", "()Ljava/util/List;", "getSelectedId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class VisibleEpgDrain {
        private final List<String> ids;
        private final String selectedId;

        public VisibleEpgDrain(List<String> list, String str) {
            this.ids = list;
            this.selectedId = str;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ VisibleEpgDrain copy$default(VisibleEpgDrain visibleEpgDrain, List list, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                list = visibleEpgDrain.ids;
            }
            if ((i10 & 2) != 0) {
                str = visibleEpgDrain.selectedId;
            }
            return visibleEpgDrain.copy(list, str);
        }

        public final List<String> component1() {
            return this.ids;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSelectedId() {
            return this.selectedId;
        }

        public final VisibleEpgDrain copy(List<String> ids, String selectedId) {
            return new VisibleEpgDrain(ids, selectedId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VisibleEpgDrain)) {
                return false;
            }
            VisibleEpgDrain visibleEpgDrain = (VisibleEpgDrain) other;
            return kotlin.jvm.internal.p.a(this.ids, visibleEpgDrain.ids) && kotlin.jvm.internal.p.a(this.selectedId, visibleEpgDrain.selectedId);
        }

        public final List<String> getIds() {
            return this.ids;
        }

        public final String getSelectedId() {
            return this.selectedId;
        }

        public int hashCode() {
            int iHashCode = this.ids.hashCode() * 31;
            String str = this.selectedId;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "VisibleEpgDrain(ids=" + this.ids + ", selectedId=" + this.selectedId + ")";
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$moveGroupDown$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$moveGroupDown$1", f = "TvViewModel.kt", l = {1874, 1876, 1879}, m = "invokeSuspend", v = 2)
    public static final class C16001 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $groupName;
        final /* synthetic */ String $playlistId;
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        final /* synthetic */ TvViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16001(String str, TvViewModel tvViewModel, String str2, d7.d<? super C16001> dVar) {
            super(2, dVar);
            this.$playlistId = str;
            this.this$0 = tvViewModel;
            this.$groupName = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C16001(this.$playlistId, this.this$0, this.$groupName, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:21:0x0082, code lost:
        
            if (r0.moveGroupDown(r11, r1, r2, r10) == r6) goto L41;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x0098, code lost:
        
            if (r11 == r6) goto L41;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                Method dump skipped, instruction units count: 300
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.C16001.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16001) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$moveGroupToTop$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$moveGroupToTop$1", f = "TvViewModel.kt", l = {1854, 1856, 1859}, m = "invokeSuspend", v = 2)
    public static final class C16011 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $groupName;
        final /* synthetic */ String $playlistId;
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        final /* synthetic */ TvViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16011(String str, TvViewModel tvViewModel, String str2, d7.d<? super C16011> dVar) {
            super(2, dVar);
            this.$playlistId = str;
            this.this$0 = tvViewModel;
            this.$groupName = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C16011(this.$playlistId, this.this$0, this.$groupName, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:21:0x0082, code lost:
        
            if (r0.moveGroupToTop(r11, r1, r2, r10) == r6) goto L41;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x0098, code lost:
        
            if (r11 == r6) goto L41;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                Method dump skipped, instruction units count: 300
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.C16011.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16011) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$moveGroupUp$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$moveGroupUp$1", f = "TvViewModel.kt", l = {1834, 1836, 1839}, m = "invokeSuspend", v = 2)
    public static final class C16021 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $groupName;
        final /* synthetic */ String $playlistId;
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        final /* synthetic */ TvViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16021(String str, TvViewModel tvViewModel, String str2, d7.d<? super C16021> dVar) {
            super(2, dVar);
            this.$playlistId = str;
            this.this$0 = tvViewModel;
            this.$groupName = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C16021(this.$playlistId, this.this$0, this.$groupName, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:21:0x0082, code lost:
        
            if (r0.moveGroupUp(r11, r1, r2, r10) == r6) goto L41;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x0098, code lost:
        
            if (r11 == r6) goto L41;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                Method dump skipped, instruction units count: 300
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.C16021.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16021) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$observeConfigAndFavorites$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$observeConfigAndFavorites$1", f = "TvViewModel.kt", l = {251}, m = "invokeSuspend", v = 2)
    public static final class C16031 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$observeConfigAndFavorites$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0007\u001a \u0012\u0004\u0012\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"Lcom/arflix/tv/data/repository/IptvConfig;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "", "", "b", "c", "Lx6/j0;", "<anonymous>", "(Lcom/arflix/tv/data/repository/IptvConfig;Ljava/util/List;Ljava/util/List;)Lx6/j0;"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$observeConfigAndFavorites$1$1", f = "TvViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02151 extends f7.j implements r7.r<IptvConfig, List<? extends String>, List<? extends String>, d7.d<? super x6.j0>, Object> {
            /* synthetic */ Object L$0;
            /* synthetic */ Object L$1;
            /* synthetic */ Object L$2;
            int label;

            public C02151(d7.d<? super C02151> dVar) {
                super(4, dVar);
            }

            @Override // r7.r
            public final Object invoke(IptvConfig iptvConfig, List<String> list, List<String> list2, d7.d<? super x6.j0> dVar) {
                C02151 c02151 = new C02151(dVar);
                c02151.L$0 = iptvConfig;
                c02151.L$1 = list;
                c02151.L$2 = list2;
                return c02151.invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                IptvConfig iptvConfig = (IptvConfig) this.L$0;
                List list = (List) this.L$1;
                List list2 = (List) this.L$2;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return new x6.j0(iptvConfig, list, list2);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$observeConfigAndFavorites$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\u0010\u0007\u001a>\u0012\"\u0012 \u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00002$\u0010\u0004\u001a \u0012\u0004\u0012\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00002\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"Lx6/j0;", "Lcom/arflix/tv/data/repository/IptvConfig;", "", "", "triple", "hiddenGroups", "groupOrder", "<anonymous>", "(Lx6/j0;Ljava/util/List;Ljava/util/List;)Lx6/j0;"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$observeConfigAndFavorites$1$2", f = "TvViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass2 extends f7.j implements r7.r<x6.j0, List<? extends String>, List<? extends String>, d7.d<? super x6.j0>, Object> {
            /* synthetic */ Object L$0;
            /* synthetic */ Object L$1;
            /* synthetic */ Object L$2;
            int label;

            public AnonymousClass2(d7.d<? super AnonymousClass2> dVar) {
                super(4, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                x6.j0 j0Var = (x6.j0) this.L$0;
                List list = (List) this.L$1;
                List list2 = (List) this.L$2;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return new x6.j0(j0Var, list, list2);
            }

            @Override // r7.r
            public final Object invoke(x6.j0 j0Var, List<String> list, List<String> list2, d7.d<? super x6.j0> dVar) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar);
                anonymousClass2.L$0 = j0Var;
                anonymousClass2.L$1 = list;
                anonymousClass2.L$2 = list2;
                return anonymousClass2.invokeSuspend(t0.f22605a);
            }
        }

        public C16031(d7.d<? super C16031> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return TvViewModel.this.new C16031(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j jVarH = y0.h(new na.s(new na.j[]{new na.s(new na.j[]{TvViewModel.this.getIptvRepository().observeConfig(), TvViewModel.this.getIptvRepository().observeFavoriteGroups(), TvViewModel.this.getIptvRepository().observeFavoriteChannels()}, new C02151(null)), TvViewModel.this.getIptvRepository().observeHiddenGroups(), TvViewModel.this.getIptvRepository().observeGroupOrder()}, new AnonymousClass2(null)));
                final TvViewModel tvViewModel = TvViewModel.this;
                na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.tv.TvViewModel.observeConfigAndFavorites.1.3
                    public final Object emit(x6.j0 j0Var, d7.d<? super t0> dVar) {
                        v1 v1Var;
                        v1 v1Var2;
                        x6.j0 j0Var2 = (x6.j0) j0Var.f22587i;
                        List list = (List) j0Var.f22588l;
                        List list2 = (List) j0Var.f22589m;
                        IptvConfig iptvConfig = (IptvConfig) j0Var2.f22587i;
                        List list3 = (List) j0Var2.f22588l;
                        List list4 = (List) j0Var2.f22589m;
                        String strSyncSignature = TvViewModelKt.syncSignature(iptvConfig);
                        boolean z = (tvViewModel.lastObservedConfigSignature == null || kotlin.jvm.internal.p.a(tvViewModel.lastObservedConfigSignature, strSyncSignature)) ? false : true;
                        tvViewModel.lastObservedConfigSignature = strSyncSignature;
                        IptvSnapshot iptvSnapshotCopy$default = IptvSnapshot.copy$default(((TvUiState) tvViewModel._uiState.getValue()).getSnapshot(), null, null, null, list3, list4, list, list2, iptvConfig.getSortOrder(), null, null, 775, null);
                        TvViewModel tvViewModel2 = tvViewModel;
                        tvViewModel2.setUiState(TvUiState.copy$default((TvUiState) tvViewModel2._uiState.getValue(), false, null, null, 0, iptvConfig, iptvSnapshotCopy$default, null, null, null, null, true, false, false, null, null, null, false, 129999, null));
                        tvViewModel.maybeWarmStartupGuide();
                        tvViewModel.startFullEpgWarmup();
                        if (kotlin.text.o.h0(iptvConfig.getM3uUrl()) && kotlin.text.o.h0(iptvConfig.getStalkerPortalUrl())) {
                            List<IptvPlaylistEntry> playlists = iptvConfig.getPlaylists();
                            if (playlists == null || !playlists.isEmpty()) {
                                for (IptvPlaylistEntry iptvPlaylistEntry : playlists) {
                                    if (iptvPlaylistEntry.getEnabled() && !kotlin.text.o.h0(iptvPlaylistEntry.getM3uUrl())) {
                                        if (!iptvSnapshotCopy$default.getChannels().isEmpty()) {
                                        }
                                    }
                                }
                            }
                            if (z) {
                                tvViewModel.setCachedEnrichedChannels(null);
                                tvViewModel.setCachedChannelsSignature(null);
                                tvViewModel.refresh(true, false, false);
                            }
                        } else if (!iptvSnapshotCopy$default.getChannels().isEmpty() && ((v1Var = tvViewModel.refreshJob) == null || !v1Var.isActive())) {
                            TvViewModel.refresh$default(tvViewModel, false, false, false, 4, null);
                        } else if (z && ((v1Var2 = tvViewModel.refreshJob) == null || !v1Var2.isActive())) {
                            tvViewModel.setCachedEnrichedChannels(null);
                            tvViewModel.setCachedChannelsSignature(null);
                            tvViewModel.refresh(true, false, false);
                        }
                        return t0.f22605a;
                    }

                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                        return emit((x6.j0) obj2, (d7.d<? super t0>) dVar);
                    }
                };
                this.label = 1;
                Object objCollect = jVarH.collect(kVar, this);
                e7.a aVar = e7.a.f15033i;
                if (objCollect == aVar) {
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

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16031) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$observeTvSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$observeTvSession$1", f = "TvViewModel.kt", l = {231}, m = "invokeSuspend", v = 2)
    public static final class C16041 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        int label;

        public C16041(d7.d<? super C16041> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return TvViewModel.this.new C16041(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j jVarH = y0.h(TvViewModel.this.getIptvRepository().observeTvSessionState());
                final TvViewModel tvViewModel = TvViewModel.this;
                na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.tv.TvViewModel.observeTvSession.1.1
                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                        return emit((IptvTvSessionState) obj2, (d7.d<? super t0>) dVar);
                    }

                    public final Object emit(IptvTvSessionState iptvTvSessionState, d7.d<? super t0> dVar) {
                        tvViewModel._uiState.setValue(TvUiState.copy$default((TvUiState) tvViewModel._uiState.getValue(), false, null, null, 0, null, null, null, null, null, iptvTvSessionState, false, true, false, null, null, null, false, 128511, null));
                        tvViewModel.maybeWarmStartupGuide();
                        return t0.f22605a;
                    }
                };
                this.label = 1;
                Object objCollect = jVarH.collect(kVar, this);
                e7.a aVar = e7.a.f15033i;
                if (objCollect == aVar) {
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

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16041) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$prefetchVisibleCategoryEpg$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$prefetchVisibleCategoryEpg$1", f = "TvViewModel.kt", l = {1359, 1385, 1426}, m = "invokeSuspend", v = 2)
    public static final class C16051 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ boolean $allowFocusedNetworkRefresh;
        final /* synthetic */ int $eagerLimit;
        final /* synthetic */ HashSet<String> $favoriteIds;
        final /* synthetic */ int $firstPaintLimit;
        final /* synthetic */ List<String> $missingIds;
        final /* synthetic */ List<String> $orderedIds;
        final /* synthetic */ String $selectedId;
        int I$0;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        final /* synthetic */ TvViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16051(List<String> list, int i10, int i11, List<String> list2, TvViewModel tvViewModel, String str, boolean z, HashSet<String> hashSet, d7.d<? super C16051> dVar) {
            super(2, dVar);
            this.$missingIds = list;
            this.$firstPaintLimit = i10;
            this.$eagerLimit = i11;
            this.$orderedIds = list2;
            this.this$0 = tvViewModel;
            this.$selectedId = str;
            this.$allowFocusedNetworkRefresh = z;
            this.$favoriteIds = hashSet;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invokeSuspend$lambda$5(TvViewModel tvViewModel, String str) {
            return tvViewModel.hasUsefulVisibleGuideData(((TvUiState) tvViewModel._uiState.getValue()).getSnapshot().getNowNext().get(str));
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C16051(this.$missingIds, this.$firstPaintLimit, this.$eagerLimit, this.$orderedIds, this.this$0, this.$selectedId, this.$allowFocusedNetworkRefresh, this.$favoriteIds, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:80:0x026a, code lost:
        
            if (r2 == r7) goto L81;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:64:0x01e7  */
        /* JADX WARN: Removed duplicated region for block: B:70:0x01fd  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x0200  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x021a  */
        /* JADX WARN: Removed duplicated region for block: B:95:0x02ca  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x02e2  */
        /* JADX WARN: Type inference failed for: r3v0 */
        /* JADX WARN: Type inference failed for: r3v1 */
        /* JADX WARN: Type inference failed for: r3v22 */
        /* JADX WARN: Type inference failed for: r3v38 */
        /* JADX WARN: Type inference failed for: r3v39 */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 790
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.C16051.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16051) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$refresh$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$refresh$1", f = "TvViewModel.kt", l = {307, MediaError.DetailedErrorCode.DASH_MANIFEST_UNKNOWN, MediaError.DetailedErrorCode.DASH_MANIFEST_UNKNOWN}, m = "invokeSuspend", v = 2)
    public static final class C16061 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ boolean $force;
        final /* synthetic */ boolean $forceEpg;
        final /* synthetic */ boolean $showLoading;
        int I$0;
        int I$1;
        int I$2;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16061(boolean z, boolean z5, boolean z10, d7.d<? super C16061> dVar) {
            super(2, dVar);
            this.$showLoading = z;
            this.$force = z5;
            this.$forceEpg = z10;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C16061 c16061 = TvViewModel.this.new C16061(this.$showLoading, this.$force, this.$forceEpg, dVar);
            c16061.L$0 = obj;
            return c16061;
        }

        /* JADX WARN: Removed duplicated region for block: B:64:0x01f4 A[Catch: all -> 0x0216, TRY_LEAVE, TryCatch #1 {all -> 0x0216, blocks: (B:62:0x01f0, B:64:0x01f4), top: B:103:0x01f0 }] */
        /* JADX WARN: Removed duplicated region for block: B:78:0x0228  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x022d  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x028f  */
        /* JADX WARN: Removed duplicated region for block: B:95:0x0302  */
        /* JADX WARN: Removed duplicated region for block: B:97:0x0317  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r36) {
            /*
                Method dump skipped, instruction units count: 845
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.C16061.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16061) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$refreshCatchupHistoryForChannel$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$refreshCatchupHistoryForChannel$1", f = "TvViewModel.kt", l = {1562, 1583}, m = "invokeSuspend", v = 2)
    public static final class C16071 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ TvUiState $current;
        final /* synthetic */ String $id;
        final /* synthetic */ long $now;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ TvViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16071(String str, TvViewModel tvViewModel, TvUiState tvUiState, long j10, d7.d<? super C16071> dVar) {
            super(2, dVar);
            this.$id = str;
            this.this$0 = tvViewModel;
            this.$current = tvUiState;
            this.$now = j10;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C16071(this.$id, this.this$0, this.$current, this.$now, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Set set;
            Map map;
            int i10 = this.label;
            t0 t0Var = t0.f22605a;
            e7.a aVar = e7.a.f15033i;
            if (i10 == 0) {
                k2.c.G(obj);
                System.err.println("[EPG-Catchup] refreshing history channel=" + this.$id + " recent=" + this.this$0.recentCatchupCount(this.$current.getSnapshot().getNowNext().get(this.$id), this.$now));
                TvViewModel tvViewModel = this.this$0;
                Set setSingleton = Collections.singleton(this.$id);
                this.label = 1;
                if (tvViewModel.refreshGuideFromCache(setSingleton, this) != aVar) {
                }
                return aVar;
            }
            if (i10 != 1) {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                set = (Set) this.L$2;
                try {
                    k2.c.G(obj);
                    map = (Map) obj;
                    this.this$0.releaseEpgNetworkRefresh(set);
                    if (map != null || map.isEmpty()) {
                        a2.v("[EPG-Catchup] no history returned channel=", this.$id, System.err);
                        this.this$0.finishEpgAttempt(Collections.singleton(this.$id));
                        return t0Var;
                    }
                    PrintStream printStream = System.err;
                    String str = this.$id;
                    int size = map.size();
                    int iRecentCatchupCount$default = TvViewModel.recentCatchupCount$default(this.this$0, (IptvNowNext) map.get(this.$id), 0L, 2, null);
                    StringBuilder sbQ = a2.q("[EPG-Catchup] refreshed channel=", str, " keys=", size, " recent=");
                    sbQ.append(iRecentCatchupCount$default);
                    printStream.println(sbQ.toString());
                    this.this$0.mergeNowNext(map);
                    return t0Var;
                } catch (Throwable th) {
                    th = th;
                    Throwable th2 = th;
                    this.this$0.releaseEpgNetworkRefresh(set);
                    throw th2;
                }
            }
            k2.c.G(obj);
            TvUiState tvUiState = (TvUiState) this.this$0._uiState.getValue();
            IptvChannel iptvChannelLookupChannelById = tvUiState.getChannelLookup().get(this.$id);
            if (iptvChannelLookupChannelById == null) {
                iptvChannelLookupChannelById = this.this$0.lookupChannelById(tvUiState, this.$id);
            }
            if (TvViewModel.hasRecentCatchupHistory$default(this.this$0, iptvChannelLookupChannelById, tvUiState.getSnapshot().getNowNext().get(this.$id), 0L, 4, null)) {
                System.err.println("[EPG-Catchup] cache satisfied channel=" + this.$id + " recent=" + TvViewModel.recentCatchupCount$default(this.this$0, tvUiState.getSnapshot().getNowNext().get(this.$id), 0L, 2, null));
                this.this$0.clearEpgLoading(Collections.singleton(this.$id));
                return t0Var;
            }
            Set setClaimEpgNetworkRefresh = this.this$0.claimEpgNetworkRefresh(Collections.singleton(this.$id), true);
            if (setClaimEpgNetworkRefresh.isEmpty()) {
                this.this$0.clearEpgLoading(Collections.singleton(this.$id));
                return t0Var;
            }
            try {
                ra.c cVar = x0.f19655d;
                TvViewModel$refreshCatchupHistoryForChannel$1$refreshed$1 tvViewModel$refreshCatchupHistoryForChannel$1$refreshed$1 = new TvViewModel$refreshCatchupHistoryForChannel$1$refreshed$1(this.this$0, setClaimEpgNetworkRefresh, null);
                this.L$0 = null;
                this.L$1 = null;
                this.L$2 = setClaimEpgNetworkRefresh;
                this.label = 2;
                Object objY = m0.y(cVar, tvViewModel$refreshCatchupHistoryForChannel$1$refreshed$1, this);
                if (objY != aVar) {
                    set = setClaimEpgNetworkRefresh;
                    obj = objY;
                    map = (Map) obj;
                    this.this$0.releaseEpgNetworkRefresh(set);
                    if (map != null) {
                    }
                    a2.v("[EPG-Catchup] no history returned channel=", this.$id, System.err);
                    this.this$0.finishEpgAttempt(Collections.singleton(this.$id));
                    return t0Var;
                }
                return aVar;
            } catch (Throwable th3) {
                th = th3;
                set = setClaimEpgNetworkRefresh;
                Throwable th22 = th;
                this.this$0.releaseEpgNetworkRefresh(set);
                throw th22;
            }
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16071) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$refreshCurrentChannelEpg$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$refreshCurrentChannelEpg$1", f = "TvViewModel.kt", l = {1479, 1518}, m = "invokeSuspend", v = 2)
    public static final class C16081 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ boolean $forceNetworkForLargeList;
        final /* synthetic */ String $id;
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16081(String str, boolean z, d7.d<? super C16081> dVar) {
            super(2, dVar);
            this.$id = str;
            this.$forceNetworkForLargeList = z;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return TvViewModel.this.new C16081(this.$id, this.$forceNetworkForLargeList, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:70:0x01f8, code lost:
        
            if (r0 == r5) goto L71;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00b6  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00ca A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00d3  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x014d  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x0159 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:60:0x01b0  */
        /* JADX WARN: Removed duplicated region for block: B:67:0x01c7  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x01d5 A[Catch: all -> 0x002e, TRY_ENTER, TryCatch #0 {all -> 0x002e, blocks: (B:7:0x0025, B:72:0x01fb, B:69:0x01d5), top: B:84:0x000a }] */
        /* JADX WARN: Type inference failed for: r2v0 */
        /* JADX WARN: Type inference failed for: r2v1 */
        /* JADX WARN: Type inference failed for: r2v25 */
        /* JADX WARN: Type inference failed for: r2v35 */
        /* JADX WARN: Type inference failed for: r2v36 */
        /* JADX WARN: Type inference failed for: r7v10 */
        /* JADX WARN: Type inference failed for: r7v6 */
        /* JADX WARN: Type inference failed for: r7v7, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r8v1 */
        /* JADX WARN: Type inference failed for: r8v2, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r8v4 */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) {
            /*
                Method dump skipped, instruction units count: 553
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.C16081.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16081) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$refreshGuideFromCache$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel", f = "TvViewModel.kt", l = {598}, m = "refreshGuideFromCache", v = 2)
    public static final class C16091 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C16091(d7.d<? super C16091> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TvViewModel.this.refreshGuideFromCache(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$refreshGuideFromCache$2, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel", f = "TvViewModel.kt", l = {606}, m = "refreshGuideFromCache", v = 2)
    public static final class AnonymousClass2 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass2(d7.d<? super AnonymousClass2> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TvViewModel.this.refreshGuideFromCache(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$rememberTvSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$rememberTvSession$1", f = "TvViewModel.kt", l = {1940, 1941}, m = "invokeSuspend", v = 2)
    public static final class C16101 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ boolean $channelChanged;
        final /* synthetic */ boolean $markOpened;
        final /* synthetic */ IptvTvSessionState $next;
        int label;
        final /* synthetic */ TvViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16101(boolean z, boolean z5, TvViewModel tvViewModel, IptvTvSessionState iptvTvSessionState, d7.d<? super C16101> dVar) {
            super(2, dVar);
            this.$markOpened = z;
            this.$channelChanged = z5;
            this.this$0 = tvViewModel;
            this.$next = iptvTvSessionState;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C16101(this.$markOpened, this.$channelChanged, this.this$0, this.$next, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:21:0x0044, code lost:
        
            if (r7.saveTvSessionState(r0, r6) == r3) goto L22;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                int r0 = r6.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L1c
                if (r0 == r2) goto L18
                if (r0 != r1) goto L10
                k2.c.G(r7)
                goto L47
            L10:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L18:
                k2.c.G(r7)
                goto L36
            L1c:
                k2.c.G(r7)
                boolean r7 = r6.$markOpened
                if (r7 != 0) goto L2b
                boolean r7 = r6.$channelChanged
                if (r7 == 0) goto L28
                goto L2b
            L28:
                r4 = 220(0xdc, double:1.087E-321)
                goto L2d
            L2b:
                r4 = 0
            L2d:
                r6.label = r2
                java.lang.Object r7 = ka.s0.a(r4, r6)
                if (r7 != r3) goto L36
                goto L46
            L36:
                com.arflix.tv.ui.screens.tv.TvViewModel r7 = r6.this$0
                com.arflix.tv.data.repository.IptvRepository r7 = r7.getIptvRepository()
                com.arflix.tv.data.repository.IptvTvSessionState r0 = r6.$next
                r6.label = r1
                java.lang.Object r7 = r7.saveTvSessionState(r0, r6)
                if (r7 != r3) goto L47
            L46:
                return r3
            L47:
                boolean r7 = r6.$markOpened
                if (r7 != 0) goto L4f
                boolean r7 = r6.$channelChanged
                if (r7 == 0) goto L54
            L4f:
                com.arflix.tv.ui.screens.tv.TvViewModel r7 = r6.this$0
                com.arflix.tv.ui.screens.tv.TvViewModel.access$scheduleIptvCloudSync(r7)
            L54:
                x6.t0 r7 = x6.t0.f22605a
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.C16101.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16101) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$resolveStalkerStreamIfNeeded$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel", f = "TvViewModel.kt", l = {1977}, m = "resolveStalkerStreamIfNeeded", v = 2)
    public static final class C16111 extends f7.c {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C16111(d7.d<? super C16111> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TvViewModel.this.resolveStalkerStreamIfNeeded(null, false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$scheduleDeferredCompleteEpgBackfill$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$scheduleDeferredCompleteEpgBackfill$1", f = "TvViewModel.kt", l = {861}, m = "invokeSuspend", v = 2)
    public static final class C16121 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        int label;

        public C16121(d7.d<? super C16121> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return TvViewModel.this.new C16121(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                this.label = 1;
                Object objA = s0.a(90000L, this);
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
            boolean z = TvViewModel.this.liveTvPlaybackActive;
            t0 t0Var = t0.f22605a;
            if (z) {
                return t0Var;
            }
            List listC1 = kotlin.collections.x.c1(TvViewModel.this.deferredCompleteEpgPriorityIds);
            TvViewModel.this.deferredCompleteEpgPriorityIds.clear();
            TvViewModel.this.deferredCompleteEpgBackfill = false;
            System.err.println("[EPG-Complete] Resuming deferred full guide backfill after playback idle");
            TvViewModel.this.startCompleteEpgBackfill(true, listC1);
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16121) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$scheduleIptvCloudSync$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$scheduleIptvCloudSync$1", f = "TvViewModel.kt", l = {2120, 2121, 2123, 2124}, m = "invokeSuspend", v = 2)
    public static final class C16131 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        public C16131(d7.d<? super C16131> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C16131 c16131 = TvViewModel.this.new C16131(dVar);
            c16131.L$0 = obj;
            return c16131;
        }

        /* JADX WARN: Code restructure failed: missing block: B:43:0x00ae, code lost:
        
            if (r12.m6125pushToCloudgIAlus(true, r11) != r8) goto L48;
         */
        /* JADX WARN: Removed duplicated region for block: B:32:0x007f  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x009a  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                r11 = this;
                java.lang.Object r0 = r11.L$0
                ka.k0 r0 = (ka.k0) r0
                int r1 = r11.label
                r2 = 0
                r3 = 4
                r4 = 3
                r5 = 2
                r6 = 1
                r7 = 0
                e7.a r8 = e7.a.f15033i
                if (r1 == 0) goto L4b
                if (r1 == r6) goto L47
                if (r1 == r5) goto L39
                if (r1 == r4) goto L31
                if (r1 != r3) goto L29
                java.lang.Object r0 = r11.L$2
                ka.k0 r0 = (ka.k0) r0
                java.lang.Object r0 = r11.L$1
                x6.d0 r0 = (x6.d0) r0
                k2.c.G(r12)     // Catch: java.lang.Throwable -> Lb1
                x6.d0 r12 = (x6.d0) r12     // Catch: java.lang.Throwable -> Lb1
                java.lang.Object r12 = r12.f22580i     // Catch: java.lang.Throwable -> Lb1
                goto Lb1
            L29:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r0)
                throw r12
            L31:
                java.lang.Object r0 = r11.L$1
                x6.d0 r0 = (x6.d0) r0
                k2.c.G(r12)
                goto L9a
            L39:
                java.lang.Object r1 = r11.L$1
                ka.k0 r1 = (ka.k0) r1
                k2.c.G(r12)     // Catch: java.lang.Throwable -> L45
                x6.d0 r12 = (x6.d0) r12     // Catch: java.lang.Throwable -> L45
                java.lang.Object r12 = r12.f22580i     // Catch: java.lang.Throwable -> L45
                goto L70
            L45:
                r12 = move-exception
                goto L76
            L47:
                k2.c.G(r12)
                goto L5b
            L4b:
                k2.c.G(r12)
                r11.L$0 = r0
                r11.label = r6
                r9 = 350(0x15e, double:1.73E-321)
                java.lang.Object r12 = ka.s0.a(r9, r11)
                if (r12 != r8) goto L5b
                goto Lb0
            L5b:
                com.arflix.tv.ui.screens.tv.TvViewModel r12 = com.arflix.tv.ui.screens.tv.TvViewModel.this
                com.arflix.tv.data.repository.CloudSyncRepository r12 = com.arflix.tv.ui.screens.tv.TvViewModel.access$getCloudSyncRepository$p(r12)     // Catch: java.lang.Throwable -> L45
                r11.L$0 = r0     // Catch: java.lang.Throwable -> L45
                r11.L$1 = r7     // Catch: java.lang.Throwable -> L45
                r11.I$0 = r2     // Catch: java.lang.Throwable -> L45
                r11.label = r5     // Catch: java.lang.Throwable -> L45
                java.lang.Object r12 = r12.m6125pushToCloudgIAlus(r6, r11)     // Catch: java.lang.Throwable -> L45
                if (r12 != r8) goto L70
                goto Lb0
            L70:
                x6.d0 r1 = new x6.d0     // Catch: java.lang.Throwable -> L45
                r1.<init>(r12)     // Catch: java.lang.Throwable -> L45
                goto L7b
            L76:
                x6.c0 r1 = new x6.c0
                r1.<init>(r12)
            L7b:
                boolean r12 = r1 instanceof x6.c0
                if (r12 == 0) goto L80
                r1 = r7
            L80:
                x6.d0 r1 = (x6.d0) r1
                if (r1 == 0) goto L8b
                java.lang.Object r12 = r1.f22580i
                boolean r12 = r12 instanceof x6.c0
                if (r12 != 0) goto L8b
                goto Lb1
            L8b:
                r11.L$0 = r0
                r11.L$1 = r7
                r11.label = r4
                r0 = 1200(0x4b0, double:5.93E-321)
                java.lang.Object r12 = ka.s0.a(r0, r11)
                if (r12 != r8) goto L9a
                goto Lb0
            L9a:
                com.arflix.tv.ui.screens.tv.TvViewModel r12 = com.arflix.tv.ui.screens.tv.TvViewModel.this
                com.arflix.tv.data.repository.CloudSyncRepository r12 = com.arflix.tv.ui.screens.tv.TvViewModel.access$getCloudSyncRepository$p(r12)     // Catch: java.lang.Throwable -> Lb1
                r11.L$0 = r7     // Catch: java.lang.Throwable -> Lb1
                r11.L$1 = r7     // Catch: java.lang.Throwable -> Lb1
                r11.L$2 = r7     // Catch: java.lang.Throwable -> Lb1
                r11.I$0 = r2     // Catch: java.lang.Throwable -> Lb1
                r11.label = r3     // Catch: java.lang.Throwable -> Lb1
                java.lang.Object r12 = r12.m6125pushToCloudgIAlus(r6, r11)     // Catch: java.lang.Throwable -> Lb1
                if (r12 != r8) goto Lb1
            Lb0:
                return r8
            Lb1:
                x6.t0 r12 = x6.t0.f22605a
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.C16131.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16131) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$setUiState$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$setUiState$1", f = "TvViewModel.kt", l = {2011}, m = "invokeSuspend", v = 2)
    public static final class C16141 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ TvUiState $nextState;
        final /* synthetic */ long $revision;
        Object L$0;
        int label;
        final /* synthetic */ TvViewModel this$0;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$setUiState$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$setUiState$1$1", f = "TvViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02171 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
            final /* synthetic */ TvUiState $prepared;
            final /* synthetic */ long $revision;
            int label;
            final /* synthetic */ TvViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02171(long j10, TvViewModel tvViewModel, TvUiState tvUiState, d7.d<? super C02171> dVar) {
                super(2, dVar);
                this.$revision = j10;
                this.this$0 = tvViewModel;
                this.$prepared = tvUiState;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new C02171(this.$revision, this.this$0, this.$prepared, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                if (this.$revision == this.this$0.preparedContentRevision) {
                    this.this$0._uiState.setValue(TvUiState.copy$default((TvUiState) this.this$0._uiState.getValue(), false, null, null, 0, null, null, this.$prepared.getChannelLookup(), this.$prepared.getGroups(), this.$prepared.getChannelsByGroup(), null, false, false, false, null, null, null, false, 130623, null));
                }
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
                return ((C02171) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16141(TvUiState tvUiState, long j10, TvViewModel tvViewModel, d7.d<? super C16141> dVar) {
            super(2, dVar);
            this.$nextState = tvUiState;
            this.$revision = j10;
            this.this$0 = tvViewModel;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C16141(this.$nextState, this.$revision, this.this$0, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                TvUiState preparedContent = TvViewModelKt.setPreparedContent(this.$nextState);
                x0 x0Var = x0.f19652a;
                la.d dVar = ((la.d) pa.q.f21294a).f20077n;
                C02171 c02171 = new C02171(this.$revision, this.this$0, preparedContent, null);
                this.L$0 = null;
                this.label = 1;
                Object objY = m0.y(dVar, c02171, this);
                e7.a aVar = e7.a.f15033i;
                if (objY == aVar) {
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

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16141) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$startCompleteEpgBackfill$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$startCompleteEpgBackfill$2", f = "TvViewModel.kt", l = {1117, 1127, 1173}, m = "invokeSuspend", v = 2)
    public static final class C16152 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ List<IptvChannel> $channels;
        final /* synthetic */ float $coverage;
        final /* synthetic */ boolean $force;
        final /* synthetic */ boolean $hasGuideData;
        final /* synthetic */ boolean $largeList;
        final /* synthetic */ Collection<String> $priorityChannelIds;
        final /* synthetic */ TvUiState $state;
        long J$0;
        Object L$0;
        int label;
        final /* synthetic */ TvViewModel this$0;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$startCompleteEpgBackfill$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$startCompleteEpgBackfill$2$1", f = "TvViewModel.kt", l = {1193}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
            final /* synthetic */ List<IptvChannel> $channels;
            final /* synthetic */ boolean $largeList;
            final /* synthetic */ Collection<String> $priorityChannelIds;
            final /* synthetic */ IptvSnapshot $snapshot;
            final /* synthetic */ TvUiState $state;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            int label;
            final /* synthetic */ TvViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(TvViewModel tvViewModel, TvUiState tvUiState, boolean z, Collection<String> collection, IptvSnapshot iptvSnapshot, List<IptvChannel> list, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = tvViewModel;
                this.$state = tvUiState;
                this.$largeList = z;
                this.$priorityChannelIds = collection;
                this.$snapshot = iptvSnapshot;
                this.$channels = list;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final boolean invokeSuspend$lambda$0(String str) {
                return !kotlin.text.o.h0(str);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final boolean invokeSuspend$lambda$1(String str) {
                return !kotlin.text.o.h0(str);
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$state, this.$largeList, this.$priorityChannelIds, this.$snapshot, this.$channels, dVar);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                LinkedHashSet linkedHashSet;
                Object objY;
                int i10;
                Object c0Var;
                Object c0Var2;
                ka.k0 k0Var = (ka.k0) this.L$0;
                int i11 = this.label;
                t0 t0Var = t0.f22605a;
                if (i11 == 0) {
                    k2.c.G(obj);
                    TvUiState tvUiState = (TvUiState) this.this$0._uiState.getValue();
                    if (kotlin.jvm.internal.p.a(TvViewModelKt.syncSignature(tvUiState.getConfig()), TvViewModelKt.syncSignature(this.$state.getConfig()))) {
                        if (this.$largeList) {
                            linkedHashSet = new LinkedHashSet(3200);
                            Iterator it = ga.r.S(ga.r.I(new ga.p(this.$priorityChannelIds, 3), new j0(1)), 3200).iterator();
                            while (it.hasNext()) {
                                linkedHashSet.add((String) it.next());
                            }
                            Iterator it2 = ga.r.S(ga.r.I(new ga.p(tvUiState.getEpgLoadingChannelIds(), 3), new j0(2)), 3200 - linkedHashSet.size()).iterator();
                            while (it2.hasNext()) {
                                linkedHashSet.add((String) it2.next());
                            }
                            Iterator it3 = ga.r.S(new ga.p(TvViewModelKt.buildPriorityEpgChannelIds(tvUiState, 360), 3), 3200 - linkedHashSet.size()).iterator();
                            while (it3.hasNext()) {
                                linkedHashSet.add((String) it3.next());
                            }
                            ra.d dVar = x0.f19653b;
                            TvViewModel$startCompleteEpgBackfill$2$1$visibleGuide$1 tvViewModel$startCompleteEpgBackfill$2$1$visibleGuide$1 = new TvViewModel$startCompleteEpgBackfill$2$1$visibleGuide$1(this.this$0, linkedHashSet, this.$snapshot, null);
                            this.L$0 = k0Var;
                            this.L$1 = null;
                            this.L$2 = linkedHashSet;
                            this.label = 1;
                            objY = m0.y(dVar, tvViewModel$startCompleteEpgBackfill$2$1$visibleGuide$1, this);
                            e7.a aVar = e7.a.f15033i;
                            if (objY == aVar) {
                                return aVar;
                            }
                        } else {
                            IptvSnapshot iptvSnapshotCopy$default = IptvSnapshot.copy$default(this.$snapshot, null, null, null, tvUiState.getSnapshot().getFavoriteGroups(), tvUiState.getSnapshot().getFavoriteChannels(), tvUiState.getSnapshot().getHiddenGroups(), tvUiState.getSnapshot().getGroupOrder(), null, null, null, MediaError.DetailedErrorCode.IMAGE_ERROR, null);
                            this.this$0.setUiState(TvUiState.copy$default(tvUiState, false, null, null, 0, null, iptvSnapshotCopy$default, null, null, null, null, false, false, false, null, kotlin.collections.b0.f19686i, null, false, 114655, null));
                            int iEpgCoverageRatio = (int) (this.this$0.epgCoverageRatio(iptvSnapshotCopy$default) * 100);
                            this.this$0.lastCompleteEpgBackfillCompletedAt = System.currentTimeMillis();
                            System.err.println("[EPG-Complete] merged full guide coverage=" + iEpgCoverageRatio + "%");
                            if (iEpgCoverageRatio < 80) {
                                AppLogger.INSTANCE.breadcrumb("IPTV", "complete_epg_low_coverage channel_count=" + this.this$0.countBucket(this.$channels.size()) + " coverage=" + iEpgCoverageRatio, "warning");
                            }
                        }
                    }
                    return t0Var;
                }
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                LinkedHashSet linkedHashSet2 = (LinkedHashSet) this.L$2;
                k2.c.G(obj);
                linkedHashSet = linkedHashSet2;
                objY = obj;
                Map map = (Map) objY;
                this.this$0.mergeNowNext(map);
                this.this$0.lastCompleteEpgBackfillCompletedAt = System.currentTimeMillis();
                TvViewModel tvViewModel = this.this$0;
                if (map.isEmpty()) {
                    i10 = 0;
                } else {
                    Iterator it4 = map.entrySet().iterator();
                    i10 = 0;
                    while (it4.hasNext()) {
                        if (tvViewModel.hasProgramData((IptvNowNext) ((Map.Entry) it4.next()).getValue())) {
                            i10++;
                        }
                    }
                }
                try {
                    c0Var = new Integer(this.this$0.getIptvRepository().indexedGuideChannelCount());
                } catch (Throwable th) {
                    c0Var = new x6.c0(th);
                }
                Object num = new Integer(0);
                if (c0Var instanceof x6.c0) {
                    c0Var = num;
                }
                int iIntValue = ((Number) c0Var).intValue();
                try {
                    c0Var2 = new Integer(this.this$0.getIptvRepository().indexedGuideProgramCount());
                } catch (Throwable th2) {
                    c0Var2 = new x6.c0(th2);
                }
                Object num2 = new Integer(0);
                if (c0Var2 instanceof x6.c0) {
                    c0Var2 = num2;
                }
                int iIntValue2 = ((Number) c0Var2).intValue();
                PrintStream printStream = System.err;
                int size = linkedHashSet.size();
                StringBuilder sbV = androidx.compose.foundation.c.v("[EPG-Complete] indexed full guide; index=", iIntValue, " channels/", iIntValue2, " programs; merged visible guide ");
                sbV.append(i10);
                sbV.append(DomExceptionUtils.SEPARATOR);
                sbV.append(size);
                sbV.append(" channels");
                printStream.println(sbV.toString());
                return t0Var;
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
                return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16152(boolean z, boolean z5, boolean z10, TvViewModel tvViewModel, Collection<String> collection, List<IptvChannel> list, float f10, TvUiState tvUiState, d7.d<? super C16152> dVar) {
            super(2, dVar);
            this.$largeList = z;
            this.$force = z5;
            this.$hasGuideData = z10;
            this.this$0 = tvViewModel;
            this.$priorityChannelIds = collection;
            this.$channels = list;
            this.$coverage = f10;
            this.$state = tvUiState;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C16152(this.$largeList, this.$force, this.$hasGuideData, this.this$0, this.$priorityChannelIds, this.$channels, this.$coverage, this.$state, dVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:48:0x00fd  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x012f  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r28) {
            /*
                Method dump skipped, instruction units count: 441
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.C16152.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16152) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$startFullEpgWarmup$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$startFullEpgWarmup$1", f = "TvViewModel.kt", l = {924, 925}, m = "invokeSuspend", v = 2)
    public static final class C16161 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ LinkedHashSet<String> $cacheWarmIds;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16161(LinkedHashSet<String> linkedHashSet, d7.d<? super C16161> dVar) {
            super(2, dVar);
            this.$cacheWarmIds = linkedHashSet;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return TvViewModel.this.new C16161(this.$cacheWarmIds, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0034, code lost:
        
            if (r7.refreshGuideFromCache(r0, r6) == r3) goto L15;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                int r0 = r6.label
                r1 = 2
                r2 = 1
                e7.a r3 = e7.a.f15033i
                if (r0 == 0) goto L1c
                if (r0 == r2) goto L18
                if (r0 != r1) goto L10
                k2.c.G(r7)
                goto L37
            L10:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L18:
                k2.c.G(r7)
                goto L2a
            L1c:
                k2.c.G(r7)
                r6.label = r2
                r4 = 800(0x320, double:3.953E-321)
                java.lang.Object r7 = ka.s0.a(r4, r6)
                if (r7 != r3) goto L2a
                goto L36
            L2a:
                com.arflix.tv.ui.screens.tv.TvViewModel r7 = com.arflix.tv.ui.screens.tv.TvViewModel.this
                java.util.LinkedHashSet<java.lang.String> r0 = r6.$cacheWarmIds
                r6.label = r1
                java.lang.Object r7 = com.arflix.tv.ui.screens.tv.TvViewModel.access$refreshGuideFromCache(r7, r0, r6)
                if (r7 != r3) goto L37
            L36:
                return r3
            L37:
                x6.t0 r7 = x6.t0.f22605a
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.C16161.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16161) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$startFullEpgWarmup$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$startFullEpgWarmup$3", f = "TvViewModel.kt", l = {959, 961, 963, 976}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass3 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ int $missingCount;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(int i10, d7.d<? super AnonymousClass3> dVar) {
            super(2, dVar);
            this.$missingCount = i10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invokeSuspend$lambda$1(TvViewModel tvViewModel, TvUiState tvUiState, String str) {
            return !tvViewModel.hasProgramData(tvUiState.getSnapshot().getNowNext().get(str));
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass3 anonymousClass3 = TvViewModel.this.new AnonymousClass3(this.$missingCount, dVar);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        /* JADX WARN: Code restructure failed: missing block: B:38:0x0108, code lost:
        
            if (r0 == r8) goto L39;
         */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0073  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0081  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00c7  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x011a  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                Method dump skipped, instruction units count: 302
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$startPeriodicEpgRefresh$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$startPeriodicEpgRefresh$1", f = "TvViewModel.kt", l = {890, 893}, m = "invokeSuspend", v = 2)
    public static final class C16171 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        Object L$0;
        int label;

        public C16171(d7.d<? super C16171> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return TvViewModel.this.new C16171(dVar);
        }

        /* JADX WARN: Path cross not found for [B:11:0x0021, B:16:0x0041], limit reached: 20 */
        /* JADX WARN: Path cross not found for [B:11:0x0021, B:18:0x004f], limit reached: 20 */
        /* JADX WARN: Removed duplicated region for block: B:14:0x002f  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x003f -> B:11:0x0021). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x004d -> B:11:0x0021). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0059 -> B:11:0x0021). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                int r0 = r7.label
                r1 = 0
                r2 = 2
                r3 = 1
                e7.a r4 = e7.a.f15033i
                if (r0 == 0) goto L1e
                if (r0 == r3) goto L1a
                if (r0 != r2) goto L12
                java.lang.Object r0 = r7.L$0
                com.arflix.tv.ui.screens.tv.TvUiState r0 = (com.arflix.tv.ui.screens.tv.TvUiState) r0
                goto L1e
            L12:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L1a:
                k2.c.G(r8)
                goto L2f
            L1e:
                k2.c.G(r8)
            L21:
                r7.L$0 = r1
                r7.label = r3
                r5 = 60000(0xea60, double:2.9644E-319)
                java.lang.Object r8 = ka.s0.a(r5, r7)
                if (r8 != r4) goto L2f
                goto L5b
            L2f:
                com.arflix.tv.ui.screens.tv.TvViewModel r8 = com.arflix.tv.ui.screens.tv.TvViewModel.this
                na.q0 r8 = com.arflix.tv.ui.screens.tv.TvViewModel.access$get_uiState$p(r8)
                java.lang.Object r8 = r8.getValue()
                com.arflix.tv.ui.screens.tv.TvUiState r8 = (com.arflix.tv.ui.screens.tv.TvUiState) r8
                boolean r0 = r8.isConfigured()
                if (r0 == 0) goto L21
                com.arflix.tv.data.model.IptvSnapshot r8 = r8.getSnapshot()
                java.util.List r8 = r8.getChannels()
                boolean r8 = r8.isEmpty()
                if (r8 != 0) goto L21
                com.arflix.tv.ui.screens.tv.TvViewModel r8 = com.arflix.tv.ui.screens.tv.TvViewModel.this
                r7.L$0 = r1
                r7.label = r2
                java.lang.Object r8 = com.arflix.tv.ui.screens.tv.TvViewModel.access$refreshGuideFromCache(r8, r7)
                if (r8 != r4) goto L21
            L5b:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.C16171.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16171) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$startVisibleEpgDrain$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$startVisibleEpgDrain$1", f = "TvViewModel.kt", l = {1633, 1650, 1671, 1701, 1728, 1781}, m = "invokeSuspend", v = 2)
    public static final class C16181 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        boolean Z$1;
        int label;

        public C16181(d7.d<? super C16181> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C16181 c16181 = TvViewModel.this.new C16181(dVar);
            c16181.L$0 = obj;
            return c16181;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(14:305|146|(1:148)|149|150|151|(3:295|161|(1:163)(1:164))|170|285|174|(4:177|(2:179|334)(1:335)|180|175)|333|184|(7:186|(1:188)|189|190|272|191|(4:275|193|194|(1:337)(24:197|270|198|311|199|200|(3:309|211|(1:213)(1:214))|221|227|(1:229)(1:230)|231|(4:234|(3:324|236|327)(1:326)|325|232)|323|237|(6:239|(1:244)(1:243)|245|(1:250)(1:249)|251|(10:261|(1:263)|264|(1:336)|7|267|35|(0)(0)|41|(2:268|269)(0))(1:260))|257|264|(0)|7|267|35|(0)(0)|41|(0)(0)))(20:209|200|(0)|221|227|(0)(0)|231|(1:232)|323|237|(0)|257|264|(0)|7|267|35|(0)(0)|41|(0)(0)))(18:220|221|227|(0)(0)|231|(1:232)|323|237|(0)|257|264|(0)|7|267|35|(0)(0)|41|(0)(0))) */
        /* JADX WARN: Can't wrap try/catch for region: R(24:197|270|198|311|199|200|(3:309|211|(1:213)(1:214))|221|227|(1:229)(1:230)|231|(4:234|(3:324|236|327)(1:326)|325|232)|323|237|(6:239|(1:244)(1:243)|245|(1:250)(1:249)|251|(10:261|(1:263)|264|(1:336)|7|267|35|(0)(0)|41|(2:268|269)(0))(1:260))|257|264|(0)|7|267|35|(0)(0)|41|(0)(0)) */
        /* JADX WARN: Can't wrap try/catch for region: R(5:(1:301)|54|55|291|56) */
        /* JADX WARN: Can't wrap try/catch for region: R(5:93|94|283|95|(3:281|97|(27:100|279|101|102|(3:277|113|(1:115)(1:116))|124|(1:125)|319|130|131|221|227|(0)(0)|231|(1:232)|323|237|(0)|257|264|(0)|7|267|35|(0)(0)|41|(0)(0)))(24:111|(0)|124|(1:125)|319|130|131|221|227|(0)(0)|231|(1:232)|323|237|(0)|257|264|(0)|7|267|35|(0)(0)|41|(0)(0))) */
        /* JADX WARN: Can't wrap try/catch for region: R(6:51|26|299|52|(5:301|54|55|291|56)(1:63)|(5:132|293|133|(1:140)|(5:313|142|143|144|(14:305|146|(1:148)|149|150|151|(3:295|161|(1:163)(1:164))|170|285|174|(4:177|(2:179|334)(1:335)|180|175)|333|184|(7:186|(1:188)|189|190|272|191|(4:275|193|194|(1:337)(24:197|270|198|311|199|200|(3:309|211|(1:213)(1:214))|221|227|(1:229)(1:230)|231|(4:234|(3:324|236|327)(1:326)|325|232)|323|237|(6:239|(1:244)(1:243)|245|(1:250)(1:249)|251|(10:261|(1:263)|264|(1:336)|7|267|35|(0)(0)|41|(2:268|269)(0))(1:260))|257|264|(0)|7|267|35|(0)(0)|41|(0)(0)))(20:209|200|(0)|221|227|(0)(0)|231|(1:232)|323|237|(0)|257|264|(0)|7|267|35|(0)(0)|41|(0)(0)))(18:220|221|227|(0)(0)|231|(1:232)|323|237|(0)|257|264|(0)|7|267|35|(0)(0)|41|(0)(0)))(10:159|151|(0)|170|285|174|(1:175)|333|184|(0)(0)))(7:173|285|174|(1:175)|333|184|(0)(0)))(8:(1:66)(1:67)|(2:72|(4:75|(3:329|80|81)|330|73))(1:71)|289|82|(4:85|(2:87|316)(1:317)|88|83)|315|89|(23:123|124|(4:127|(3:318|129|322)(1:321)|320|125)|319|130|131|221|227|(0)(0)|231|(1:232)|323|237|(0)|257|264|(0)|7|267|35|(0)(0)|41|(0)(0))(5:93|94|283|95|(3:281|97|(27:100|279|101|102|(3:277|113|(1:115)(1:116))|124|(1:125)|319|130|131|221|227|(0)(0)|231|(1:232)|323|237|(0)|257|264|(0)|7|267|35|(0)(0)|41|(0)(0)))(24:111|(0)|124|(1:125)|319|130|131|221|227|(0)(0)|231|(1:232)|323|237|(0)|257|264|(0)|7|267|35|(0)(0)|41|(0)(0))))) */
        /* JADX WARN: Can't wrap try/catch for region: R(7:186|(1:188)|189|190|272|191|(4:275|193|194|(1:337)(24:197|270|198|311|199|200|(3:309|211|(1:213)(1:214))|221|227|(1:229)(1:230)|231|(4:234|(3:324|236|327)(1:326)|325|232)|323|237|(6:239|(1:244)(1:243)|245|(1:250)(1:249)|251|(10:261|(1:263)|264|(1:336)|7|267|35|(0)(0)|41|(2:268|269)(0))(1:260))|257|264|(0)|7|267|35|(0)(0)|41|(0)(0)))(20:209|200|(0)|221|227|(0)(0)|231|(1:232)|323|237|(0)|257|264|(0)|7|267|35|(0)(0)|41|(0)(0))) */
        /* JADX WARN: Can't wrap try/catch for region: R(8:(1:66)(1:67)|(2:72|(4:75|(3:329|80|81)|330|73))(1:71)|289|82|(4:85|(2:87|316)(1:317)|88|83)|315|89|(23:123|124|(4:127|(3:318|129|322)(1:321)|320|125)|319|130|131|221|227|(0)(0)|231|(1:232)|323|237|(0)|257|264|(0)|7|267|35|(0)(0)|41|(0)(0))(5:93|94|283|95|(3:281|97|(27:100|279|101|102|(3:277|113|(1:115)(1:116))|124|(1:125)|319|130|131|221|227|(0)(0)|231|(1:232)|323|237|(0)|257|264|(0)|7|267|35|(0)(0)|41|(0)(0)))(24:111|(0)|124|(1:125)|319|130|131|221|227|(0)(0)|231|(1:232)|323|237|(0)|257|264|(0)|7|267|35|(0)(0)|41|(0)(0)))) */
        /* JADX WARN: Code restructure failed: missing block: B:119:0x0327, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:120:0x0328, code lost:
        
            r11 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:121:0x032b, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:122:0x032c, code lost:
        
            r21 = r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:181:0x0488, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:182:0x0489, code lost:
        
            r6 = r10;
         */
        /* JADX WARN: Code restructure failed: missing block: B:183:0x048a, code lost:
        
            r21 = r13;
            r11 = r14;
            r7 = r15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:201:0x051d, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:218:0x0540, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:219:0x0541, code lost:
        
            r6 = r21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:224:0x0555, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:225:0x0556, code lost:
        
            r20 = r4;
            r13 = r5;
            r6 = r10;
            r11 = r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0135, code lost:
        
            if (ka.s0.a(120, r22) == r10) goto L33;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x01cd, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Not initialized variable reg: 12, insn: 0x0302: MOVE (r13 I:??[OBJECT, ARRAY]) = (r12 I:??[OBJECT, ARRAY]) (LINE:771), block:B:104:0x0301 */
        /* JADX WARN: Not initialized variable reg: 3, insn: 0x0414: MOVE (r11 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]) (LINE:1045), block:B:153:0x0414 */
        /* JADX WARN: Path cross not found for [B:239:0x05e9, B:257:0x063b], limit reached: 330 */
        /* JADX WARN: Path cross not found for [B:257:0x063b, B:239:0x05e9], limit reached: 330 */
        /* JADX WARN: Removed duplicated region for block: B:127:0x0343 A[Catch: all -> 0x0324, TryCatch #4 {all -> 0x0324, blocks: (B:113:0x0319, B:116:0x0320, B:124:0x0334, B:125:0x033d, B:127:0x0343, B:129:0x0368, B:130:0x036c), top: B:277:0x0319 }] */
        /* JADX WARN: Removed duplicated region for block: B:132:0x0375  */
        /* JADX WARN: Removed duplicated region for block: B:177:0x045b A[Catch: all -> 0x0488, TryCatch #8 {all -> 0x0488, blocks: (B:174:0x044c, B:175:0x0455, B:177:0x045b, B:179:0x0482, B:184:0x048f, B:186:0x0497, B:189:0x04a7), top: B:285:0x044c }] */
        /* JADX WARN: Removed duplicated region for block: B:186:0x0497 A[Catch: all -> 0x0488, TryCatch #8 {all -> 0x0488, blocks: (B:174:0x044c, B:175:0x0455, B:177:0x045b, B:179:0x0482, B:184:0x048f, B:186:0x0497, B:189:0x04a7), top: B:285:0x044c }] */
        /* JADX WARN: Removed duplicated region for block: B:220:0x0545  */
        /* JADX WARN: Removed duplicated region for block: B:229:0x0569  */
        /* JADX WARN: Removed duplicated region for block: B:230:0x05a4  */
        /* JADX WARN: Removed duplicated region for block: B:234:0x05ba  */
        /* JADX WARN: Removed duplicated region for block: B:239:0x05e9  */
        /* JADX WARN: Removed duplicated region for block: B:268:0x068c  */
        /* JADX WARN: Removed duplicated region for block: B:277:0x0319 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:295:0x0426 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:301:0x01ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:309:0x0532 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:336:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0146  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x014e  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x015e  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x01da  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x01de  */
        /* JADX WARN: Type inference failed for: r18v0, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r18v1 */
        /* JADX WARN: Type inference failed for: r18v2 */
        /* JADX WARN: Type inference failed for: r9v31, types: [d7.d, java.lang.Object] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:265:0x0680 -> B:7:0x003f). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r23) {
            /*
                Method dump skipped, instruction units count: 1698
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.C16181.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16181) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$toggleFavoriteChannel$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$toggleFavoriteChannel$1", f = "TvViewModel.kt", l = {1269}, m = "invokeSuspend", v = 2)
    public static final class C16191 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $channelId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16191(String str, d7.d<? super C16191> dVar) {
            super(2, dVar);
            this.$channelId = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return TvViewModel.this.new C16191(this.$channelId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                IptvRepository iptvRepository = TvViewModel.this.getIptvRepository();
                String str = this.$channelId;
                this.label = 1;
                Object obj2 = iptvRepository.toggleFavoriteChannel(str, this);
                e7.a aVar = e7.a.f15033i;
                if (obj2 == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            TvViewModel.this.scheduleIptvCloudSync();
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16191) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$toggleFavoriteGroup$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$toggleFavoriteGroup$1", f = "TvViewModel.kt", l = {1262}, m = "invokeSuspend", v = 2)
    public static final class C16201 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $groupName;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16201(String str, d7.d<? super C16201> dVar) {
            super(2, dVar);
            this.$groupName = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return TvViewModel.this.new C16201(this.$groupName, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                IptvRepository iptvRepository = TvViewModel.this.getIptvRepository();
                String str = this.$groupName;
                this.label = 1;
                Object obj2 = iptvRepository.toggleFavoriteGroup(str, this);
                e7.a aVar = e7.a.f15033i;
                if (obj2 == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            TvViewModel.this.scheduleIptvCloudSync();
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16201) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$toggleHiddenGroup$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$toggleHiddenGroup$1", f = "TvViewModel.kt", l = {1282, 1284, 1287}, m = "invokeSuspend", v = 2)
    public static final class C16211 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $groupName;
        final /* synthetic */ String $playlistId;
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        final /* synthetic */ TvViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C16211(String str, TvViewModel tvViewModel, String str2, d7.d<? super C16211> dVar) {
            super(2, dVar);
            this.$playlistId = str;
            this.this$0 = tvViewModel;
            this.$groupName = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C16211(this.$playlistId, this.this$0, this.$groupName, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:21:0x007c, code lost:
        
            if (r0.toggleHiddenGroup(r10, r1, r9) == r6) goto L41;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x0092, code lost:
        
            if (r10 == r6) goto L41;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                Method dump skipped, instruction units count: 290
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.C16211.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16211) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.tv.TvViewModel$warmXtreamVodCache$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.tv.TvViewModel$warmXtreamVodCache$1", f = "TvViewModel.kt", l = {481}, m = "invokeSuspend", v = 2)
    public static final class C16221 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public C16221(d7.d<? super C16221> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C16221 c16221 = TvViewModel.this.new C16221(dVar);
            c16221.L$0 = obj;
            return c16221;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    IptvRepository iptvRepository = TvViewModel.this.getIptvRepository();
                    this.L$0 = null;
                    this.L$1 = null;
                    this.I$0 = 0;
                    this.label = 1;
                    Object objWarmXtreamVodCachesIfPossible = iptvRepository.warmXtreamVodCachesIfPossible(this);
                    e7.a aVar = e7.a.f15033i;
                    if (objWarmXtreamVodCachesIfPossible == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
            } catch (Throwable unused) {
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C16221) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public TvViewModel(@ApplicationContext Context context, IptvRepository iptvRepository, CloudSyncRepository cloudSyncRepository) {
        this.context = context;
        this.iptvRepository = iptvRepository;
        this.cloudSyncRepository = cloudSyncRepository;
        j1 j1VarB = y0.b(new TvUiState(false, null, null, 0, null, null, null, null, null, null, false, false, false, null, null, null, false, 131071, null));
        this._uiState = j1VarB;
        this.uiState = y0.e(j1VarB);
        this.visibleEpgQueueLock = new Object();
        this.pendingVisibleEpgChannelIds = new LinkedHashSet<>();
        this.deferredCompleteEpgPriorityIds = new LinkedHashSet<>();
        this.resolvedStalkerStreamCache = new LinkedHashMap<>();
        this.iptvPlaybackUrlResolver = new x6.i0(new g(21));
        this.catchupHistoryRefreshAt = new LinkedHashMap<>();
        this.currentChannelEpgRefreshAt = new LinkedHashMap<>();
        this.epgNetworkRefreshLock = new Object();
        this.epgNetworkRefreshInFlight = new LinkedHashSet<>();
        observeConfigAndFavorites();
        observeTvSession();
        m0.p(z0.h(this), null, 0, new AnonymousClass1(null), 3);
    }

    private final boolean canReusePreparedContent(TvUiState previous, TvUiState next) {
        IptvSnapshot snapshot = previous.getSnapshot();
        IptvSnapshot snapshot2 = next.getSnapshot();
        return kotlin.jvm.internal.p.a(previous.getQuery(), next.getQuery()) && snapshot.getChannels() == snapshot2.getChannels() && snapshot.getGrouped() == snapshot2.getGrouped() && kotlin.jvm.internal.p.a(snapshot.getFavoriteChannels(), snapshot2.getFavoriteChannels()) && kotlin.jvm.internal.p.a(snapshot.getFavoriteGroups(), snapshot2.getFavoriteGroups()) && kotlin.jvm.internal.p.a(snapshot.getHiddenGroups(), snapshot2.getHiddenGroups()) && kotlin.jvm.internal.p.a(snapshot.getGroupOrder(), snapshot2.getGroupOrder());
    }

    private final Set<String> capChannelStateSet(Set<String> ids, int limit) {
        return ids.size() <= limit ? ids : kotlin.collections.x.g1(kotlin.collections.x.Y0(limit, kotlin.collections.x.c1(ids)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IptvSnapshot capLargeListGuideSnapshot(IptvSnapshot snapshot, Map<String, ? extends List<IptvChannel>> channelsByGroup, IptvTvSessionState tvSession, Collection<String> keepChannelIds) {
        if (TvViewModelKt.isLargeIptvList(snapshot.getChannels().size()) && snapshot.getNowNext().size() > 360) {
            LinkedHashSet linkedHashSetBuildPriorityEpgChannelIds = TvViewModelKt.buildPriorityEpgChannelIds(new TvUiState(false, null, null, 0, null, snapshot, null, kotlin.collections.x.c1(channelsByGroup.keySet()), channelsByGroup, tvSession, false, false, false, null, null, null, false, 130143, null), 360);
            LinkedHashSet linkedHashSet = new LinkedHashSet(540);
            Iterator it = linkedHashSetBuildPriorityEpgChannelIds.iterator();
            while (it.hasNext()) {
                linkedHashSet.add((String) it.next());
            }
            ga.h hVar = new ga.h(ga.r.I(new ga.t(new ga.p(snapshot.getFavoriteChannels(), 3), new j0(13)), new j0(14)));
            while (hVar.hasNext()) {
                linkedHashSet.add((String) hVar.next());
            }
            Iterator it2 = ga.r.S(ga.r.I(new ga.p(keepChannelIds, 3), new j0(15)), 180).iterator();
            while (it2.hasNext()) {
                linkedHashSet.add((String) it2.next());
            }
            if (linkedHashSet.isEmpty()) {
                return IptvSnapshot.copy$default(snapshot, null, null, kotlin.collections.a0.f19683i, null, null, null, null, null, null, null, AnalyticsListener.EVENT_VIDEO_DECODER_RELEASED, null);
            }
            Map<String, IptvNowNext> nowNext = snapshot.getNowNext();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry<String, IptvNowNext> entry : nowNext.entrySet()) {
                if (linkedHashSet.contains(entry.getKey())) {
                    linkedHashMap.put(entry.getKey(), entry.getValue());
                }
            }
            if (linkedHashMap.size() != snapshot.getNowNext().size()) {
                PrintStream printStream = System.err;
                int size = snapshot.getNowNext().size();
                int size2 = linkedHashMap.size();
                int size3 = snapshot.getChannels().size();
                StringBuilder sbV = androidx.compose.foundation.c.v("[EPG-Memory] capped UI guide ", size, " -> ", size2, " for ");
                sbV.append(size3);
                sbV.append(" channels");
                printStream.println(sbV.toString());
                return IptvSnapshot.copy$default(snapshot, null, null, linkedHashMap, null, null, null, null, null, null, null, AnalyticsListener.EVENT_VIDEO_DECODER_RELEASED, null);
            }
        }
        return snapshot;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IptvSnapshot capLargeListGuideSnapshot$default(TvViewModel tvViewModel, IptvSnapshot iptvSnapshot, Map map, IptvTvSessionState iptvTvSessionState, Collection collection, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            collection = kotlin.collections.z.f19728i;
        }
        return tvViewModel.capLargeListGuideSnapshot(iptvSnapshot, map, iptvTvSessionState, collection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String capLargeListGuideSnapshot$lambda$0(String str) {
        return kotlin.text.o.L0(str).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean capLargeListGuideSnapshot$lambda$1(String str) {
        return !kotlin.text.o.h0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean capLargeListGuideSnapshot$lambda$3(String str) {
        return !kotlin.text.o.h0(str);
    }

    private final long catchupHistoryTargetWindowMs(IptvChannel channel) {
        int iN = channel != null ? qb.d.n(channel.getCatchupDays(), 0, 7) : 0;
        return (iN > 0 ? Math.min(48L, ((long) iN) * 24) : 48L) * 3600000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<String> claimEpgNetworkRefresh(Collection<String> channelIds, boolean allowLargeListFocusedRefresh) {
        Set<String> setW;
        if (channelIds.isEmpty()) {
            return kotlin.collections.b0.f19686i;
        }
        if (isActiveLargeIptvList() && !allowLargeListFocusedRefresh) {
            System.err.println("[EPG-Refresh] Skipping network prefetch for large paged TV list");
            return kotlin.collections.b0.f19686i;
        }
        synchronized (this.epgNetworkRefreshLock) {
            setW = ga.r.W(ga.r.I(ga.r.I(new ga.p(channelIds, 3), new j0(20)), new h0(this, 8)));
        }
        return setW;
    }

    public static /* synthetic */ Set claimEpgNetworkRefresh$default(TvViewModel tvViewModel, Collection collection, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        return tvViewModel.claimEpgNetworkRefresh(collection, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean claimEpgNetworkRefresh$lambda$0$0(String str) {
        return !kotlin.text.o.h0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean claimEpgNetworkRefresh$lambda$0$1(TvViewModel tvViewModel, String str) {
        return tvViewModel.epgNetworkRefreshInFlight.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearEpgLoading(Collection<String> channelIds) {
        if (shouldEmitEpgSpinnerState()) {
            Set setW = ga.r.W(ga.r.I(new ga.p(channelIds, 3), new j0(17)));
            if (setW.isEmpty()) {
                return;
            }
            TvUiState tvUiState = (TvUiState) this._uiState.getValue();
            setUiState(TvUiState.copy$default(tvUiState, false, null, null, 0, null, null, null, null, null, null, false, false, false, null, kotlin.collections.q0.a0(tvUiState.getEpgLoadingChannelIds(), setW), null, false, 114687, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean clearEpgLoading$lambda$0(String str) {
        return !kotlin.text.o.h0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String countBucket(int count) {
        return count < 100 ? "lt_100" : count < 1000 ? "lt_1k" : count < 10000 ? "lt_10k" : count < 50000 ? "lt_50k" : "gte_50k";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> currentVisiblePlaylistGroups(String playlistId) {
        String string = playlistId != null ? kotlin.text.o.L0(playlistId).toString() : null;
        if (string == null) {
            string = "";
        }
        if (!kotlin.text.o.h0(string) && this.iptvRepository.pagedChannelsReady()) {
            List<String> hiddenGroups = ((TvUiState) this._uiState.getValue()).getSnapshot().getHiddenGroups();
            HashSet hashSet = new HashSet();
            Iterator<T> it = hiddenGroups.iterator();
            while (it.hasNext()) {
                hashSet.add(kotlin.text.o.L0((String) it.next()).toString());
            }
            return ga.r.U(ga.r.G(ga.r.I(new ga.t(ga.r.I(new ga.p(this.iptvRepository.pagedPlaylistGroupCounts(), 3), new g0(string, 2)), new j0(18)), new f0(hashSet, 2))));
        }
        IptvSnapshot snapshot = ((TvUiState) this._uiState.getValue()).getSnapshot();
        List<String> hiddenGroups2 = snapshot.getHiddenGroups();
        HashSet hashSet2 = new HashSet();
        Iterator<T> it2 = hiddenGroups2.iterator();
        while (it2.hasNext()) {
            hashSet2.add(kotlin.text.o.L0((String) it2.next()).toString());
        }
        return ga.r.U(ga.r.G(ga.r.I(new ga.t(new ga.p(snapshot.getGrouped().keySet(), 3), new j0(19)), new f0(hashSet2, 3))));
    }

    public static /* synthetic */ List currentVisiblePlaylistGroups$default(TvViewModel tvViewModel, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        return tvViewModel.currentVisiblePlaylistGroups(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean currentVisiblePlaylistGroups$lambda$1(String str, x6.j0 j0Var) {
        return kotlin.jvm.internal.p.a((String) j0Var.f22587i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String currentVisiblePlaylistGroups$lambda$2(x6.j0 j0Var) {
        return kotlin.text.o.L0((String) j0Var.f22588l).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean currentVisiblePlaylistGroups$lambda$3(HashSet hashSet, String str) {
        return (kotlin.text.o.h0(str) || hashSet.contains(str)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String currentVisiblePlaylistGroups$lambda$5(String str) {
        return kotlin.text.o.L0(str).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean currentVisiblePlaylistGroups$lambda$6(HashSet hashSet, String str) {
        return (kotlin.text.o.h0(str) || hashSet.contains(str)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deferCompleteEpgBackfill(Collection<String> priorityChannelIds) {
        this.deferredCompleteEpgBackfill = true;
        ga.m mVarS = ga.r.S(ga.r.I(new ga.p(priorityChannelIds, 3), new j0(21)), 3200 - this.deferredCompleteEpgPriorityIds.size());
        LinkedHashSet<String> linkedHashSet = this.deferredCompleteEpgPriorityIds;
        Iterator it = mVarS.iterator();
        while (it.hasNext()) {
            linkedHashSet.add((String) it.next());
        }
        setEpgBackfillInProgress(false);
        System.err.println("[EPG-Complete] Deferred full guide backfill while live playback is active");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean deferCompleteEpgBackfill$lambda$0(String str) {
        return !kotlin.text.o.h0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VisibleEpgDrain drainVisibleEpgBatch(int maxChannels) {
        VisibleEpgDrain visibleEpgDrain;
        String str = null;
        if (maxChannels <= 0) {
            return new VisibleEpgDrain(kotlin.collections.z.f19728i, null);
        }
        Map<String, IptvNowNext> nowNext = ((TvUiState) this._uiState.getValue()).getSnapshot().getNowNext();
        synchronized (this.visibleEpgQueueLock) {
            try {
                if (this.pendingVisibleEpgChannelIds.isEmpty()) {
                    visibleEpgDrain = new VisibleEpgDrain(kotlin.collections.z.f19728i, null);
                } else {
                    ArrayList arrayList = new ArrayList(maxChannels);
                    String str2 = this.pendingVisibleEpgSelectedChannelId;
                    drainVisibleEpgBatch$lambda$0$takePending(arrayList, maxChannels, this, nowNext, str2);
                    Iterator<String> it = this.pendingVisibleEpgChannelIds.iterator();
                    while (it.hasNext() && arrayList.size() < maxChannels) {
                        String next = it.next();
                        it.remove();
                        if (!hasUsefulVisibleGuideData(nowNext.get(next))) {
                            arrayList.add(next);
                        }
                    }
                    String str3 = this.pendingVisibleEpgSelectedChannelId;
                    if (str3 == null || !this.pendingVisibleEpgChannelIds.contains(str3)) {
                        this.pendingVisibleEpgSelectedChannelId = null;
                    }
                    if (str2 != null && arrayList.contains(str2)) {
                        str = str2;
                    }
                    visibleEpgDrain = new VisibleEpgDrain(arrayList, str);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return visibleEpgDrain;
    }

    private static final void drainVisibleEpgBatch$lambda$0$takePending(ArrayList<String> arrayList, int i10, TvViewModel tvViewModel, Map<String, IptvNowNext> map, String str) {
        if (str == null || kotlin.text.o.h0(str) || arrayList.size() >= i10 || !tvViewModel.pendingVisibleEpgChannelIds.remove(str) || tvViewModel.hasUsefulVisibleGuideData(map.get(str))) {
            return;
        }
        arrayList.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:9:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void enqueueVisibleEpgRefresh(java.util.List<java.lang.String> r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.visibleEpgQueueLock
            monitor-enter(r0)
            r1 = 0
            if (r7 == 0) goto Ld
            boolean r2 = r6.contains(r7)     // Catch: java.lang.Throwable -> Lf
            if (r2 == 0) goto Ld
            goto L11
        Ld:
            r7 = r1
            goto L11
        Lf:
            r6 = move-exception
            goto L60
        L11:
            r2 = 1
            if (r7 == 0) goto L1e
            java.lang.String r3 = r5.pendingVisibleEpgSelectedChannelId     // Catch: java.lang.Throwable -> Lf
            boolean r3 = r7.equals(r3)     // Catch: java.lang.Throwable -> Lf
            if (r3 != 0) goto L1e
            r3 = r2
            goto L1f
        L1e:
            r3 = 0
        L1f:
            if (r3 == 0) goto L26
            java.util.LinkedHashSet<java.lang.String> r4 = r5.pendingVisibleEpgChannelIds     // Catch: java.lang.Throwable -> Lf
            r4.clear()     // Catch: java.lang.Throwable -> Lf
        L26:
            if (r7 == 0) goto L2a
            r5.pendingVisibleEpgSelectedChannelId = r7     // Catch: java.lang.Throwable -> Lf
        L2a:
            java.util.Iterator r6 = r6.iterator()     // Catch: java.lang.Throwable -> Lf
        L2e:
            boolean r7 = r6.hasNext()     // Catch: java.lang.Throwable -> Lf
            if (r7 == 0) goto L46
            java.lang.Object r7 = r6.next()     // Catch: java.lang.Throwable -> Lf
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Throwable -> Lf
            boolean r4 = kotlin.text.o.h0(r7)     // Catch: java.lang.Throwable -> Lf
            if (r4 != 0) goto L2e
            java.util.LinkedHashSet<java.lang.String> r4 = r5.pendingVisibleEpgChannelIds     // Catch: java.lang.Throwable -> Lf
            r4.add(r7)     // Catch: java.lang.Throwable -> Lf
            goto L2e
        L46:
            monitor-exit(r0)
            if (r3 == 0) goto L5c
            ka.v1 r6 = r5.visibleEpgRefreshJob
            if (r6 == 0) goto L5c
            boolean r6 = r6.isActive()
            if (r6 != r2) goto L5c
            ka.v1 r6 = r5.visibleEpgRefreshJob
            if (r6 == 0) goto L5a
            r6.cancel(r1)
        L5a:
            r5.visibleEpgRefreshJob = r1
        L5c:
            r5.startVisibleEpgDrain()
            return
        L60:
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.enqueueVisibleEpgRefresh(java.util.List, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float epgCoverageRatio(IptvSnapshot snapshot) {
        if (snapshot.getChannels().isEmpty()) {
            return 0.0f;
        }
        List<IptvChannel> channels = snapshot.getChannels();
        int i10 = 0;
        if (channels == null || !channels.isEmpty()) {
            Iterator<T> it = channels.iterator();
            while (it.hasNext()) {
                IptvNowNext iptvNowNext = snapshot.getNowNext().get(((IptvChannel) it.next()).getId());
                if (iptvNowNext != null && (iptvNowNext.getNow() != null || iptvNowNext.getNext() != null || iptvNowNext.getLater() != null || !iptvNowNext.getUpcoming().isEmpty())) {
                    i10++;
                    if (i10 < 0) {
                        t7.a.P();
                        throw null;
                    }
                }
            }
        }
        return i10 / snapshot.getChannels().size();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishEpgAttempt(Collection<String> channelIds) {
        if (shouldEmitEpgSpinnerState()) {
            Set setW = ga.r.W(ga.r.I(new ga.p(channelIds, 3), new j0(22)));
            if (setW.isEmpty()) {
                return;
            }
            TvUiState tvUiState = (TvUiState) this._uiState.getValue();
            Set set = setW;
            setUiState(TvUiState.copy$default(tvUiState, false, null, null, 0, null, null, null, null, null, null, false, false, false, null, kotlin.collections.q0.a0(tvUiState.getEpgLoadingChannelIds(), set), capChannelStateSet(kotlin.collections.q0.b0(tvUiState.getEpgAttemptedChannelIds(), set), 2400), false, 81919, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean finishEpgAttempt$lambda$0(String str) {
        return !kotlin.text.o.h0(str);
    }

    private final IptvPlaybackUrlResolver getIptvPlaybackUrlResolver() {
        return (IptvPlaybackUrlResolver) this.iptvPlaybackUrlResolver.getValue();
    }

    private final boolean hasAnyEpgData(IptvSnapshot snapshot) {
        if (snapshot.getNowNext().isEmpty()) {
            return false;
        }
        Collection<IptvNowNext> collectionValues = snapshot.getNowNext().values();
        if ((collectionValues instanceof Collection) && collectionValues.isEmpty()) {
            return false;
        }
        for (IptvNowNext iptvNowNext : collectionValues) {
            if (iptvNowNext.getNow() != null || iptvNowNext.getNext() != null || iptvNowNext.getLater() != null || !iptvNowNext.getUpcoming().isEmpty() || !iptvNowNext.getRecent().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasProgramData(IptvNowNext item) {
        if (item != null) {
            return (item.getNow() == null && item.getNext() == null && item.getLater() == null && item.getUpcoming().isEmpty() && item.getRecent().isEmpty()) ? false : true;
        }
        return false;
    }

    private final boolean hasRecentAiredHistory(IptvNowNext item, long now) {
        Long lValueOf = null;
        List<IptvProgram> recent = item != null ? item.getRecent() : null;
        if (recent == null) {
            recent = kotlin.collections.z.f19728i;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : recent) {
            IptvProgram iptvProgram = (IptvProgram) obj;
            if (iptvProgram.getEndUtcMillis() <= now && iptvProgram.getEndUtcMillis() >= now - 172800000) {
                arrayList.add(obj);
            }
        }
        if (arrayList.size() < 6) {
            return false;
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            lValueOf = Long.valueOf(((IptvProgram) it.next()).getStartUtcMillis());
            while (it.hasNext()) {
                Long lValueOf2 = Long.valueOf(((IptvProgram) it.next()).getStartUtcMillis());
                if (lValueOf.compareTo(lValueOf2) > 0) {
                    lValueOf = lValueOf2;
                }
            }
        }
        if (lValueOf != null) {
            return now - lValueOf.longValue() >= 129600000 || arrayList.size() >= 24;
        }
        return false;
    }

    public static /* synthetic */ boolean hasRecentAiredHistory$default(TvViewModel tvViewModel, IptvNowNext iptvNowNext, long j10, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = System.currentTimeMillis();
        }
        return tvViewModel.hasRecentAiredHistory(iptvNowNext, j10);
    }

    private final boolean hasRecentCatchupHistory(IptvChannel channel, IptvNowNext item, long now) {
        if (!supportsCatchup(channel)) {
            return true;
        }
        long jCatchupHistoryTargetWindowMs = catchupHistoryTargetWindowMs(channel);
        Long lValueOf = null;
        List<IptvProgram> recent = item != null ? item.getRecent() : null;
        if (recent == null) {
            recent = kotlin.collections.z.f19728i;
        }
        List listU = ga.r.U(ga.r.I(new ga.p(recent, 3), new com.arflix.tv.data.repository.u(now, jCatchupHistoryTargetWindowMs, 2)));
        if (listU.size() < 6) {
            return false;
        }
        Iterator it = listU.iterator();
        if (it.hasNext()) {
            lValueOf = Long.valueOf(((IptvProgram) it.next()).getStartUtcMillis());
            while (it.hasNext()) {
                Long lValueOf2 = Long.valueOf(((IptvProgram) it.next()).getStartUtcMillis());
                if (lValueOf.compareTo(lValueOf2) > 0) {
                    lValueOf = lValueOf2;
                }
            }
        }
        if (lValueOf != null) {
            return now - lValueOf.longValue() >= (jCatchupHistoryTargetWindowMs * ((long) 3)) / ((long) 4) || listU.size() >= 24;
        }
        return false;
    }

    public static /* synthetic */ boolean hasRecentCatchupHistory$default(TvViewModel tvViewModel, IptvChannel iptvChannel, IptvNowNext iptvNowNext, long j10, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            j10 = System.currentTimeMillis();
        }
        return tvViewModel.hasRecentCatchupHistory(iptvChannel, iptvNowNext, j10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasRecentCatchupHistory$lambda$0(long j10, long j11, IptvProgram iptvProgram) {
        return iptvProgram.getEndUtcMillis() <= j10 && iptvProgram.getEndUtcMillis() >= j10 - j11;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasRichSelectedGuideData(IptvNowNext item) {
        int i10;
        if (hasProgramData(item) && item != null) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            z6.c cVarN = t7.a.n();
            IptvProgram next = item.getNext();
            if (next != null) {
                cVarN.add(next);
            }
            IptvProgram later = item.getLater();
            if (later != null) {
                cVarN.add(later);
            }
            cVarN.addAll(item.getUpcoming());
            z6.c cVarE = t7.a.e(cVarN);
            HashSet hashSet = new HashSet();
            ArrayList arrayList = new ArrayList();
            ListIterator listIterator = cVarE.listIterator(0);
            while (true) {
                z6.a aVar = (z6.a) listIterator;
                if (!aVar.hasNext()) {
                    break;
                }
                Object next2 = aVar.next();
                IptvProgram iptvProgram = (IptvProgram) next2;
                if (hashSet.add(iptvProgram.getStartUtcMillis() + "|" + iptvProgram.getEndUtcMillis() + "|" + iptvProgram.getTitle())) {
                    arrayList.add(next2);
                }
            }
            if (arrayList.isEmpty()) {
                i10 = 0;
            } else {
                Iterator it = arrayList.iterator();
                i10 = 0;
                while (it.hasNext()) {
                    if (((IptvProgram) it.next()).getStartUtcMillis() > jCurrentTimeMillis && (i10 = i10 + 1) < 0) {
                        t7.a.P();
                        throw null;
                    }
                }
            }
            IptvProgram now = item.getNow();
            boolean z = now != null && now.getEndUtcMillis() - jCurrentTimeMillis > 1200000;
            if (i10 >= 6 || (z && i10 >= 3)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasUsefulVisibleGuideData(IptvNowNext item) {
        if (!hasProgramData(item) || item == null) {
            return false;
        }
        if (item.getNext() != null || item.getLater() != null || !item.getUpcoming().isEmpty()) {
            return true;
        }
        IptvProgram now = item.getNow();
        return now != null && now.getEndUtcMillis() - System.currentTimeMillis() > 2700000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IptvPlaybackUrlResolver iptvPlaybackUrlResolver_delegate$lambda$0() {
        gb.g0 g0VarB = OkHttpProvider.INSTANCE.getPlaybackClient().b();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        g0VarB.b(3L, timeUnit);
        g0VarB.e(4L, timeUnit);
        g0VarB.f(3L);
        g0VarB.a(5L, timeUnit);
        return new IptvPlaybackUrlResolver(new gb.h0(g0VarB), 0L, 0, 6, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isActiveLargeIptvList() {
        Object c0Var;
        if (TvViewModelKt.isLargeIptvList(((TvUiState) this._uiState.getValue()).getSnapshot().getChannels().size())) {
            return true;
        }
        try {
            c0Var = Integer.valueOf(this.iptvRepository.pagedChannelStoreCount());
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (c0Var instanceof x6.c0) {
            c0Var = 0;
        }
        return ((Number) c0Var).intValue() > 10000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void logIptvRefreshFailure(Throwable error, String phase, boolean force, boolean forceEpg, boolean hasExistingChannels) {
        String message = error.getMessage();
        if (message == null) {
            message = "";
        }
        if (!kotlin.text.o.T(message, "IPTV load timed out", true) && !kotlin.text.o.T(message, "Playlist loaded but contains no channels", true) && !kotlin.text.o.T(message, "Xtream provider timed out", true)) {
            AppLogger.INSTANCE.recordException(error, kotlin.collections.h0.t0(new x6.x("error_area", "IPTV"), new x6.x("iptv_phase", phase), new x6.x("force_playlist_reload", String.valueOf(force)), new x6.x("force_epg_reload", String.valueOf(forceEpg)), new x6.x("had_existing_channels", String.valueOf(hasExistingChannels))));
            return;
        }
        AppLogger.INSTANCE.breadcrumb("IPTV", phase + " soft_fail force=" + force + " force_epg=" + forceEpg + " had_existing=" + hasExistingChannels + " reason=" + error.getClass().getSimpleName(), "warning");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IptvChannel lookupChannelById(TvUiState state, String id) {
        Object c0Var;
        Object obj = null;
        if (kotlin.text.o.h0(id)) {
            return null;
        }
        if (!TvViewModelKt.isLargeIptvList(state.getSnapshot().getChannels().size())) {
            try {
                c0Var = Integer.valueOf(this.iptvRepository.pagedChannelStoreCount());
            } catch (Throwable th) {
                c0Var = new x6.c0(th);
            }
            if (c0Var instanceof x6.c0) {
                c0Var = 0;
            }
            if (((Number) c0Var).intValue() <= 10000) {
                Iterator<T> it = state.getSnapshot().getChannels().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    if (kotlin.jvm.internal.p.a(((IptvChannel) next).getId(), id)) {
                        obj = next;
                        break;
                    }
                }
                return (IptvChannel) obj;
            }
        }
        return (IptvChannel) kotlin.collections.x.o0(this.iptvRepository.pagedChannelsByIds(Collections.singletonList(id)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void markEpgLoading(Collection<String> channelIds) {
        if (shouldEmitEpgSpinnerState()) {
            Set setW = ga.r.W(ga.r.S(ga.r.I(new ga.p(channelIds, 3), new j0(16)), 800));
            if (setW.isEmpty()) {
                return;
            }
            TvUiState tvUiState = (TvUiState) this._uiState.getValue();
            Set set = setW;
            setUiState(TvUiState.copy$default(tvUiState, false, null, null, 0, null, null, null, null, null, null, false, false, false, null, capChannelStateSet(kotlin.collections.q0.b0(tvUiState.getEpgLoadingChannelIds(), set), 800), kotlin.collections.q0.a0(tvUiState.getEpgAttemptedChannelIds(), set), false, 81919, null));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean markEpgLoading$lambda$0(String str) {
        return !kotlin.text.o.h0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void maybeWarmStartupGuide() {
        TvUiState tvUiState = (TvUiState) this._uiState.getValue();
        if (tvUiState.getChannelsByGroup().isEmpty()) {
            return;
        }
        boolean zIsActiveLargeIptvList = isActiveLargeIptvList();
        List listBuildStartupWarmGroups = TvViewModelKt.buildStartupWarmGroups(tvUiState);
        if (listBuildStartupWarmGroups.isEmpty()) {
            return;
        }
        z6.c cVarN = t7.a.n();
        Iterator it = listBuildStartupWarmGroups.iterator();
        int i10 = 0;
        int i11 = 0;
        while (true) {
            if (!it.hasNext()) {
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
                    if (hashSet.add(((IptvChannel) next).getId())) {
                        arrayList.add(next);
                    }
                }
                if (arrayList.isEmpty()) {
                    return;
                }
                if (!arrayList.isEmpty()) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        if (hasProgramData(tvUiState.getSnapshot().getNowNext().get(((IptvChannel) it2.next()).getId())) && (i10 = i10 + 1) < 0) {
                            t7.a.P();
                            throw null;
                        }
                    }
                }
                if (i10 >= Math.min(arrayList.size(), zIsActiveLargeIptvList ? 12 : 24)) {
                    return;
                }
                String lastChannelId = tvUiState.getTvSession().getLastChannelId();
                if (kotlin.text.o.h0(lastChannelId) || arrayList.isEmpty()) {
                    lastChannelId = null;
                } else {
                    Iterator it3 = arrayList.iterator();
                    while (it3.hasNext()) {
                        if (kotlin.jvm.internal.p.a(((IptvChannel) it3.next()).getId(), lastChannelId)) {
                            break;
                        }
                    }
                    lastChannelId = null;
                }
                if (lastChannelId == null) {
                    IptvChannel iptvChannel = (IptvChannel) kotlin.collections.x.o0(arrayList);
                    lastChannelId = iptvChannel != null ? iptvChannel.getId() : null;
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(kotlin.collections.x.u0(listBuildStartupWarmGroups, ",", null, null, null, 62));
                sb2.append('|');
                IptvChannel iptvChannel2 = (IptvChannel) kotlin.collections.x.o0(arrayList);
                String id = iptvChannel2 != null ? iptvChannel2.getId() : null;
                if (id == null) {
                    id = "";
                }
                sb2.append(id);
                sb2.append('|');
                sb2.append(arrayList.size());
                sb2.append('|');
                sb2.append(lastChannelId != null ? lastChannelId : "");
                String string = sb2.toString();
                if (kotlin.jvm.internal.p.a(string, this.startupGuideWarmupKey)) {
                    return;
                }
                this.startupGuideWarmupKey = string;
                String str = lastChannelId;
                ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
                Iterator it4 = arrayList.iterator();
                while (it4.hasNext()) {
                    arrayList2.add(((IptvChannel) it4.next()).getId());
                }
                prefetchVisibleCategoryEpg$default(this, arrayList2, str, Math.min(arrayList.size(), zIsActiveLargeIptvList ? 32 : 96), Math.min(arrayList.size(), zIsActiveLargeIptvList ? 96 : 520), false, 16, null);
                return;
            }
            Object next2 = it.next();
            int i12 = i11 + 1;
            if (i11 < 0) {
                t7.a.Q();
                throw null;
            }
            String str2 = (String) next2;
            if (zIsActiveLargeIptvList && (kotlin.jvm.internal.p.a(str2, TvViewModelKt.FAVORITES_GROUP_NAME) || i11 == 0)) {
                i = 48;
            } else if (zIsActiveLargeIptvList) {
                i = 16;
            } else if (!kotlin.jvm.internal.p.a(str2, TvViewModelKt.FAVORITES_GROUP_NAME) && i11 != 0) {
                i = 56;
            }
            List<IptvChannel> list = tvUiState.getChannelsByGroup().get(str2);
            if (list == null) {
                list = kotlin.collections.z.f19728i;
            }
            cVarN.addAll(kotlin.collections.x.X0(list, i));
            i11 = i12;
        }
    }

    private final IptvNowNext mergeGuideSlice(IptvNowNext existing, IptvNowNext fresh) {
        if (existing == null) {
            return fresh;
        }
        IptvProgram now = fresh.getNow();
        if (now == null) {
            now = existing.getNow();
        }
        IptvProgram next = fresh.getNext();
        if (next == null) {
            next = existing.getNext();
        }
        IptvProgram later = fresh.getLater();
        if (later == null) {
            later = existing.getLater();
        }
        return new IptvNowNext(now, next, later, !fresh.getUpcoming().isEmpty() ? fresh.getUpcoming() : existing.getUpcoming(), !fresh.getRecent().isEmpty() ? fresh.getRecent() : existing.getRecent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IptvSnapshot mergeIncomingSnapshotWithCurrentGuide(IptvSnapshot incoming, TvUiState current) {
        Map<String, IptvNowNext> nowNext = current.getSnapshot().getNowNext();
        if (nowNext.isEmpty()) {
            return incoming;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(incoming.getNowNext());
        for (Map.Entry<String, IptvNowNext> entry : nowNext.entrySet()) {
            String key = entry.getKey();
            IptvNowNext value = entry.getValue();
            if (hasProgramData(value)) {
                IptvNowNext iptvNowNext = (IptvNowNext) linkedHashMap.get(key);
                if (iptvNowNext != null) {
                    value = mergeGuideSlice(value, iptvNowNext);
                }
                linkedHashMap.put(key, value);
            }
        }
        return IptvSnapshot.copy$default(incoming, null, null, linkedHashMap, null, null, null, null, null, null, null, AnalyticsListener.EVENT_VIDEO_DECODER_RELEASED, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void mergeNowNext(Map<String, IptvNowNext> updated) {
        if (updated.isEmpty()) {
            return;
        }
        TvUiState tvUiState = (TvUiState) this._uiState.getValue();
        Set<String> setKeySet = updated.keySet();
        LinkedHashMap linkedHashMap = new LinkedHashMap(tvUiState.getSnapshot().getNowNext());
        for (Map.Entry<String, IptvNowNext> entry : updated.entrySet()) {
            String key = entry.getKey();
            linkedHashMap.put(key, mergeGuideSlice((IptvNowNext) linkedHashMap.get(key), entry.getValue()));
        }
        IptvSnapshot iptvSnapshotCopy$default = IptvSnapshot.copy$default(tvUiState.getSnapshot(), null, null, linkedHashMap, null, null, null, null, null, null, null, AnalyticsListener.EVENT_VIDEO_DECODER_RELEASED, null);
        Map<String, List<IptvChannel>> channelsByGroup = tvUiState.getChannelsByGroup();
        if (channelsByGroup.isEmpty()) {
            channelsByGroup = iptvSnapshotCopy$default.getGrouped();
        }
        IptvSnapshot iptvSnapshotCapLargeListGuideSnapshot = capLargeListGuideSnapshot(iptvSnapshotCopy$default, channelsByGroup, tvUiState.getTvSession(), setKeySet);
        Set<String> set = setKeySet;
        setUiState(TvUiState.copy$default(tvUiState, false, null, null, 0, null, iptvSnapshotCapLargeListGuideSnapshot, null, null, null, null, false, false, false, null, kotlin.collections.q0.a0(tvUiState.getEpgLoadingChannelIds(), set), capChannelStateSet(kotlin.collections.q0.b0(tvUiState.getEpgAttemptedChannelIds(), set), 2400), false, 81887, null));
    }

    private final void observeConfigAndFavorites() {
        m0.p(z0.h(this), null, 0, new C16031(null), 3);
    }

    private final void observeTvSession() {
        m0.p(z0.h(this), null, 0, new C16041(null), 3);
    }

    public static /* synthetic */ void prefetchVisibleCategoryEpg$default(TvViewModel tvViewModel, List list, String str, int i10, int i11, boolean z, int i12, Object obj) {
        if ((i12 & 4) != 0) {
            i10 = 96;
        }
        int i13 = i10;
        if ((i12 & 8) != 0) {
            i11 = 640;
        }
        int i14 = i11;
        if ((i12 & 16) != 0) {
            z = false;
        }
        tvViewModel.prefetchVisibleCategoryEpg(list, str, i13, i14, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int recentCatchupCount(IptvNowNext item, long now) {
        List<IptvProgram> recent = item != null ? item.getRecent() : null;
        if (recent == null) {
            recent = kotlin.collections.z.f19728i;
        }
        int i10 = 0;
        if (recent.isEmpty()) {
            return 0;
        }
        for (IptvProgram iptvProgram : recent) {
            if (iptvProgram.getEndUtcMillis() <= now && iptvProgram.getEndUtcMillis() >= now - 172800000 && (i10 = i10 + 1) < 0) {
                t7.a.P();
                throw null;
            }
        }
        return i10;
    }

    public static /* synthetic */ int recentCatchupCount$default(TvViewModel tvViewModel, IptvNowNext iptvNowNext, long j10, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            j10 = System.currentTimeMillis();
        }
        return tvViewModel.recentCatchupCount(iptvNowNext, j10);
    }

    public static /* synthetic */ void refresh$default(TvViewModel tvViewModel, boolean z, boolean z5, boolean z10, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z5 = true;
        }
        if ((i10 & 4) != 0) {
            z10 = false;
        }
        tvViewModel.refresh(z, z5, z10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 refresh$lambda$0$0(TvViewModel tvViewModel, Throwable th) {
        tvViewModel.refreshJob = null;
        if (tvViewModel.pendingForcedReload) {
            tvViewModel.pendingForcedReload = false;
            tvViewModel.refresh(true, false, false);
        }
        return t0.f22605a;
    }

    public static /* synthetic */ void refreshCurrentChannelEpg$default(TvViewModel tvViewModel, String str, boolean z, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z = false;
        }
        tvViewModel.refreshCurrentChannelEpg(str, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object refreshGuideFromCache(d7.d<? super x6.t0> r7) throws java.lang.Throwable {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.arflix.tv.ui.screens.tv.TvViewModel.C16091
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.ui.screens.tv.TvViewModel$refreshGuideFromCache$1 r0 = (com.arflix.tv.ui.screens.tv.TvViewModel.C16091) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.ui.screens.tv.TvViewModel$refreshGuideFromCache$1 r0 = new com.arflix.tv.ui.screens.tv.TvViewModel$refreshGuideFromCache$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            x6.t0 r3 = x6.t0.f22605a
            if (r1 == 0) goto L37
            if (r1 != r2) goto L2f
            java.lang.Object r1 = r0.L$1
            java.util.LinkedHashSet r1 = (java.util.LinkedHashSet) r1
            java.lang.Object r0 = r0.L$0
            com.arflix.tv.ui.screens.tv.TvUiState r0 = (com.arflix.tv.ui.screens.tv.TvUiState) r0
            k2.c.G(r7)
            goto L7b
        L2f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L37:
            k2.c.G(r7)
            na.q0<com.arflix.tv.ui.screens.tv.TvUiState> r7 = r6._uiState
            java.lang.Object r7 = r7.getValue()
            com.arflix.tv.ui.screens.tv.TvUiState r7 = (com.arflix.tv.ui.screens.tv.TvUiState) r7
            com.arflix.tv.data.model.IptvSnapshot r1 = r7.getSnapshot()
            java.util.List r1 = r1.getChannels()
            int r1 = r1.size()
            boolean r1 = com.arflix.tv.ui.screens.tv.TvViewModelKt.access$isLargeIptvList(r1)
            if (r1 == 0) goto L57
            r1 = 360(0x168, float:5.04E-43)
            goto L59
        L57:
            r1 = 3200(0xc80, float:4.484E-42)
        L59:
            java.util.LinkedHashSet r7 = com.arflix.tv.ui.screens.tv.TvViewModelKt.access$buildPriorityEpgChannelIds(r7, r1)
            boolean r1 = r7.isEmpty()
            if (r1 == 0) goto L64
            goto L7f
        L64:
            ra.d r1 = ka.x0.f19653b
            com.arflix.tv.ui.screens.tv.TvViewModel$refreshGuideFromCache$updated$1 r4 = new com.arflix.tv.ui.screens.tv.TvViewModel$refreshGuideFromCache$updated$1
            r5 = 0
            r4.<init>(r6, r7, r5)
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r2
            java.lang.Object r7 = ka.m0.y(r1, r4, r0)
            e7.a r0 = e7.a.f15033i
            if (r7 != r0) goto L7b
            return r0
        L7b:
            java.util.Map r7 = (java.util.Map) r7
            if (r7 != 0) goto L80
        L7f:
            return r3
        L80:
            r6.mergeNowNext(r7)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.refreshGuideFromCache(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void releaseEpgNetworkRefresh(Collection<String> channelIds) {
        if (channelIds.isEmpty()) {
            return;
        }
        synchronized (this.epgNetworkRefreshLock) {
            Iterator<T> it = channelIds.iterator();
            while (it.hasNext()) {
                this.epgNetworkRefreshInFlight.remove((String) it.next());
            }
        }
    }

    public static /* synthetic */ void rememberTvSession$default(TvViewModel tvViewModel, String str, String str2, String str3, boolean z, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            z = false;
        }
        tvViewModel.rememberTvSession(str, str2, str3, z);
    }

    private final void requestVisibleCompleteEpgBackfill(Collection<String> priorityChannelIds) {
        boolean zIsActiveLargeIptvList = isActiveLargeIptvList();
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.lastVisibleForcedCompleteEpgAt < 60000) {
            return;
        }
        this.lastVisibleForcedCompleteEpgAt = jCurrentTimeMillis;
        if (zIsActiveLargeIptvList) {
            System.err.println("[EPG-Complete] Visible guide unresolved on large paged list; skip on-device XMLTV during TV page");
        } else {
            startCompleteEpgBackfill(true, priorityChannelIds);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void requestVisibleCompleteEpgBackfill$default(TvViewModel tvViewModel, Collection collection, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            collection = kotlin.collections.z.f19728i;
        }
        tvViewModel.requestVisibleCompleteEpgBackfill(collection);
    }

    public static /* synthetic */ Object resolvePlayableStreamUrl$app_playRelease$default(TvViewModel tvViewModel, IptvChannel iptvChannel, IptvProgram iptvProgram, boolean z, int i10, d7.d dVar, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            iptvProgram = null;
        }
        return tvViewModel.resolvePlayableStreamUrl$app_playRelease(iptvChannel, iptvProgram, (i11 & 4) != 0 ? false : z, (i11 & 8) != 0 ? 0 : i10, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveStalkerStreamIfNeeded(java.lang.String r6, boolean r7, d7.d<? super java.lang.String> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.resolveStalkerStreamIfNeeded(java.lang.String, boolean, d7.d):java.lang.Object");
    }

    private final void scheduleDeferredCompleteEpgBackfill() {
        if (this.deferredCompleteEpgBackfill) {
            v1 v1Var = this.completeEpgBackfillJob;
            if (v1Var == null || !v1Var.isActive()) {
                v1 v1Var2 = this.deferredCompleteEpgBackfillJob;
                if (v1Var2 != null) {
                    v1Var2.cancel((CancellationException) null);
                }
                o2 o2VarP = m0.p(z0.h(this), null, 0, new C16121(null), 3);
                o2VarP.invokeOnCompletion(new e0(this, o2VarP, 3));
                this.deferredCompleteEpgBackfillJob = o2VarP;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 scheduleDeferredCompleteEpgBackfill$lambda$0$0(TvViewModel tvViewModel, v1 v1Var, Throwable th) {
        if (tvViewModel.deferredCompleteEpgBackfillJob == v1Var) {
            tvViewModel.deferredCompleteEpgBackfillJob = null;
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleIptvCloudSync() {
        v1 v1Var = this.iptvCloudSyncJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.iptvCloudSyncJob = m0.p(z0.h(this), x0.f19655d, 0, new C16131(null), 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setEpgBackfillInProgress(boolean inProgress) {
        TvUiState tvUiState = (TvUiState) this._uiState.getValue();
        if (tvUiState.getEpgBackfillInProgress() == inProgress) {
            return;
        }
        setUiState(TvUiState.copy$default(tvUiState, false, null, null, 0, null, null, null, null, null, null, false, false, false, null, null, null, inProgress, DatagramKt.MAX_DATAGRAM_SIZE, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setUiState(TvUiState nextState) {
        TvUiState tvUiState = (TvUiState) this._uiState.getValue();
        if (canReusePreparedContent(tvUiState, nextState)) {
            this._uiState.setValue(TvUiState.copy$default(nextState, false, null, null, 0, null, null, tvUiState.getChannelLookup(), tvUiState.getGroups(), tvUiState.getChannelsByGroup(), null, false, false, false, null, null, null, false, 130623, null));
            return;
        }
        long j10 = this.preparedContentRevision + 1;
        this.preparedContentRevision = j10;
        v1 v1Var = this.preparedContentJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this._uiState.setValue(TvUiState.copy$default(nextState, false, null, null, 0, null, null, tvUiState.getChannelLookup(), tvUiState.getGroups(), tvUiState.getChannelsByGroup(), null, false, false, false, null, null, null, false, 130623, null));
        this.preparedContentJob = m0.p(z0.h(this), x0.f19653b, 0, new C16141(nextState, j10, this, null), 2);
    }

    private final boolean shouldEmitEpgSpinnerState() {
        return !isActiveLargeIptvList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCompleteEpgBackfill(boolean force, Collection<String> priorityChannelIds) {
        Object c0Var;
        int iIntValue;
        Object c0Var2;
        int iIntValue2;
        boolean z;
        List<IptvChannel> list;
        String tvgName;
        TvUiState tvUiState = (TvUiState) this._uiState.getValue();
        List<IptvChannel> channels = tvUiState.getSnapshot().getChannels();
        boolean zIsActiveLargeIptvList = isActiveLargeIptvList();
        if (tvUiState.isConfigured() && !channels.isEmpty() && TvViewModelKt.hasNetworkEpgSource(tvUiState.getConfig())) {
            if (!force && channels.size() <= 1) {
                AppLogger.INSTANCE.breadcrumb("IPTV", androidx.compose.foundation.c.n(channels.size(), tvUiState.getSnapshot().getNowNext().size(), "complete_epg_skipped_sparse_snapshot channels=", " now_next="), "info");
                return;
            }
            if (!force) {
                if (!channels.isEmpty()) {
                    for (IptvChannel iptvChannel : channels) {
                        String epgId = iptvChannel.getEpgId();
                        if ((epgId == null || kotlin.text.o.h0(epgId)) && ((tvgName = iptvChannel.getTvgName()) == null || kotlin.text.o.h0(tvgName))) {
                        }
                    }
                }
                AppLogger.INSTANCE.breadcrumb("IPTV", "complete_epg_skipped_no_channel_identity channels=" + channels.size(), "info");
                return;
            }
            if (this.liveTvPlaybackActive) {
                deferCompleteEpgBackfill(priorityChannelIds);
                return;
            }
            if (zIsActiveLargeIptvList && !LiveTvGuideSources.INSTANCE.hasXmltvSource(tvUiState.getConfig())) {
                setEpgBackfillInProgress(false);
                System.err.println("[EPG-Complete] Skipping on-device full guide backfill for large playlist; visible guide loads on demand");
                return;
            }
            if (zIsActiveLargeIptvList) {
                try {
                    c0Var = Integer.valueOf(this.iptvRepository.indexedGuideChannelCount());
                } catch (Throwable th) {
                    c0Var = new x6.c0(th);
                }
                if (c0Var instanceof x6.c0) {
                    c0Var = 0;
                }
                iIntValue = ((Number) c0Var).intValue();
            } else {
                iIntValue = 0;
            }
            if (zIsActiveLargeIptvList) {
                try {
                    c0Var2 = Integer.valueOf(this.iptvRepository.indexedGuideProgramCount());
                } catch (Throwable th2) {
                    c0Var2 = new x6.c0(th2);
                }
                if (c0Var2 instanceof x6.c0) {
                    c0Var2 = 0;
                }
                iIntValue2 = ((Number) c0Var2).intValue();
            } else {
                iIntValue2 = 0;
            }
            int iGuideCapableChannelCount = zIsActiveLargeIptvList ? TvViewModelKt.guideCapableChannelCount(channels) : channels.size();
            if (iGuideCapableChannelCount < 1) {
                iGuideCapableChannelCount = 1;
            }
            float f10 = (!zIsActiveLargeIptvList || channels.isEmpty()) ? 0.0f : iIntValue / iGuideCapableChannelCount;
            boolean zHasAnyEpgData = hasAnyEpgData(tvUiState.getSnapshot());
            long jCachedEpgAgeMs = this.iptvRepository.cachedEpgAgeMs();
            if (!force && zHasAnyEpgData && jCachedEpgAgeMs < 86400000 && (!zIsActiveLargeIptvList || f10 >= 0.75f)) {
                setEpgBackfillInProgress(false);
                System.err.println("[EPG-Complete] Keeping cached guide; age=" + (jCachedEpgAgeMs / ((long) 1000)) + "s index=" + iIntValue + DomExceptionUtils.SEPARATOR + iIntValue2);
                return;
            }
            if (!force && zIsActiveLargeIptvList && zHasAnyEpgData && f10 >= 0.75f) {
                v1 v1Var = this.completeEpgBackfillJob;
                if (v1Var != null) {
                    v1Var.cancel((CancellationException) null);
                }
                this.completeEpgBackfillJob = null;
                setEpgBackfillInProgress(false);
                return;
            }
            float fEpgCoverageRatio = epgCoverageRatio(tvUiState.getSnapshot());
            boolean z5 = !zIsActiveLargeIptvList ? fEpgCoverageRatio < 0.98f || jCachedEpgAgeMs >= 21600000 : f10 < 0.75f || jCachedEpgAgeMs >= 86400000;
            if (force || !z5) {
                v1 v1Var2 = this.completeEpgBackfillJob;
                if (v1Var2 == null || !v1Var2.isActive()) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(TvViewModelKt.syncSignature(tvUiState.getConfig()));
                    sb2.append('|');
                    sb2.append(channels.size());
                    sb2.append('|');
                    float f11 = 1000;
                    sb2.append((int) (fEpgCoverageRatio * f11));
                    sb2.append('|');
                    sb2.append((int) (f11 * f10));
                    sb2.append('|');
                    float f12 = f10;
                    sb2.append(jCachedEpgAgeMs / 1800000);
                    String string = sb2.toString();
                    if (force || !kotlin.jvm.internal.p.a(string, this.lastCompleteEpgBackfillKey)) {
                        this.lastCompleteEpgBackfillKey = string;
                        if (zIsActiveLargeIptvList) {
                            PrintStream printStream = System.err;
                            int size = channels.size();
                            int i10 = (int) (100 * f12);
                            long j10 = jCachedEpgAgeMs / ((long) 1000);
                            long j11 = force ? 0L : 180L;
                            z = zIsActiveLargeIptvList;
                            list = channels;
                            StringBuilder sbV = androidx.compose.foundation.c.v("[EPG-Complete] Scheduling large-list full guide index: channels=", size, " guideCapable=", iGuideCapableChannelCount, " indexed=");
                            a2.y(sbV, iIntValue, " programs=", iIntValue2, " coverage=");
                            sbV.append(i10);
                            sbV.append("% age=");
                            sbV.append(j10);
                            sbV.append("s delay=");
                            sbV.append(j11);
                            sbV.append(CmcdData.STREAMING_FORMAT_SS);
                            printStream.println(sbV.toString());
                        } else {
                            z = zIsActiveLargeIptvList;
                            list = channels;
                        }
                        o2 o2VarP = m0.p(z0.h(this), x0.f19655d, 0, new C16152(z, force, zHasAnyEpgData, this, priorityChannelIds, list, fEpgCoverageRatio, tvUiState, null), 2);
                        o2VarP.invokeOnCompletion(new e0(this, o2VarP, 2));
                        this.completeEpgBackfillJob = o2VarP;
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void startCompleteEpgBackfill$default(TvViewModel tvViewModel, boolean z, Collection collection, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        if ((i10 & 2) != 0) {
            collection = kotlin.collections.z.f19728i;
        }
        tvViewModel.startCompleteEpgBackfill(z, collection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 startCompleteEpgBackfill$lambda$4$0(TvViewModel tvViewModel, v1 v1Var, Throwable th) {
        if (tvViewModel.completeEpgBackfillJob == v1Var) {
            tvViewModel.completeEpgBackfillJob = null;
            m0.p(z0.h(tvViewModel), null, 0, new TvViewModel$startCompleteEpgBackfill$3$1$1(tvViewModel, null), 3);
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startFullEpgWarmup() {
        int i10;
        TvUiState tvUiState = (TvUiState) this._uiState.getValue();
        List<IptvChannel> channels = tvUiState.getSnapshot().getChannels();
        if (channels.isEmpty()) {
            return;
        }
        if (TvViewModelKt.isLargeIptvList(channels.size())) {
            LinkedHashSet linkedHashSetBuildPriorityEpgChannelIds = TvViewModelKt.buildPriorityEpgChannelIds(tvUiState, 360);
            if (linkedHashSetBuildPriorityEpgChannelIds.isEmpty()) {
                return;
            }
            StringBuilder sb2 = new StringBuilder("large-cache-only|");
            sb2.append(TvViewModelKt.syncSignature(tvUiState.getConfig()));
            sb2.append('|');
            sb2.append(channels.size());
            sb2.append('|');
            String str = (String) kotlin.collections.x.n0(linkedHashSetBuildPriorityEpgChannelIds);
            if (str == null) {
                str = "";
            }
            sb2.append(str);
            sb2.append('|');
            String str2 = (String) kotlin.collections.x.x0(linkedHashSetBuildPriorityEpgChannelIds);
            sb2.append(str2 != null ? str2 : "");
            String string = sb2.toString();
            if (kotlin.jvm.internal.p.a(string, this.lastFullEpgWarmupKey)) {
                return;
            }
            this.lastFullEpgWarmupKey = string;
            v1 v1Var = this.fullEpgWarmupJob;
            if (v1Var != null) {
                v1Var.cancel((CancellationException) null);
            }
            o2 o2VarP = m0.p(z0.h(this), x0.f19655d, 0, new C16161(linkedHashSetBuildPriorityEpgChannelIds, null), 2);
            o2VarP.invokeOnCompletion(new e0(this, o2VarP, 0));
            this.fullEpgWarmupJob = o2VarP;
            return;
        }
        LinkedHashSet linkedHashSetBuildPriorityEpgChannelIds2 = TvViewModelKt.buildPriorityEpgChannelIds(tvUiState, 3200);
        if (linkedHashSetBuildPriorityEpgChannelIds2.isEmpty()) {
            return;
        }
        if (linkedHashSetBuildPriorityEpgChannelIds2.isEmpty()) {
            i10 = 0;
        } else {
            Iterator it = linkedHashSetBuildPriorityEpgChannelIds2.iterator();
            i10 = 0;
            while (it.hasNext()) {
                if (!hasProgramData(tvUiState.getSnapshot().getNowNext().get((String) it.next())) && (i10 = i10 + 1) < 0) {
                    t7.a.P();
                    throw null;
                }
            }
        }
        if (i10 == 0) {
            return;
        }
        StringBuilder sb3 = new StringBuilder();
        sb3.append(TvViewModelKt.syncSignature(tvUiState.getConfig()));
        sb3.append('|');
        sb3.append(channels.size());
        sb3.append('|');
        sb3.append(linkedHashSetBuildPriorityEpgChannelIds2.size());
        sb3.append('|');
        String str3 = (String) kotlin.collections.x.n0(linkedHashSetBuildPriorityEpgChannelIds2);
        if (str3 == null) {
            str3 = "";
        }
        sb3.append(str3);
        sb3.append('|');
        String str4 = (String) kotlin.collections.x.x0(linkedHashSetBuildPriorityEpgChannelIds2);
        sb3.append(str4 != null ? str4 : "");
        String string2 = sb3.toString();
        if (kotlin.jvm.internal.p.a(string2, this.lastFullEpgWarmupKey)) {
            return;
        }
        this.lastFullEpgWarmupKey = string2;
        v1 v1Var2 = this.fullEpgWarmupJob;
        if (v1Var2 != null) {
            v1Var2.cancel((CancellationException) null);
        }
        o2 o2VarP2 = m0.p(z0.h(this), x0.f19655d, 0, new AnonymousClass3(i10, null), 2);
        o2VarP2.invokeOnCompletion(new e0(this, o2VarP2, 1));
        this.fullEpgWarmupJob = o2VarP2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 startFullEpgWarmup$lambda$1$0(TvViewModel tvViewModel, v1 v1Var, Throwable th) {
        if (tvViewModel.fullEpgWarmupJob == v1Var) {
            tvViewModel.fullEpgWarmupJob = null;
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 startFullEpgWarmup$lambda$4$0(TvViewModel tvViewModel, v1 v1Var, Throwable th) {
        if (tvViewModel.fullEpgWarmupJob == v1Var) {
            tvViewModel.fullEpgWarmupJob = null;
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPeriodicEpgRefresh() {
        v1 v1Var = this.periodicEpgJob;
        if (v1Var == null || !v1Var.isActive()) {
            this.periodicEpgJob = m0.p(z0.h(this), null, 0, new C16171(null), 3);
        }
    }

    private final void startVisibleEpgDrain() {
        v1 v1Var = this.visibleEpgRefreshJob;
        if (v1Var == null || !v1Var.isActive()) {
            o2 o2VarP = m0.p(z0.h(this), null, 0, new C16181(null), 3);
            o2VarP.invokeOnCompletion(new e0(this, o2VarP, 4));
            this.visibleEpgRefreshJob = o2VarP;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 startVisibleEpgDrain$lambda$0$0(TvViewModel tvViewModel, v1 v1Var, Throwable th) {
        if (tvViewModel.visibleEpgRefreshJob == v1Var) {
            tvViewModel.visibleEpgRefreshJob = null;
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean supportsCatchup(IptvChannel channel) {
        String catchupSource;
        if (channel == null) {
            return false;
        }
        if (channel.getCatchupDays() > 0) {
            return true;
        }
        String catchupType = channel.getCatchupType();
        return !(catchupType == null || kotlin.text.o.h0(catchupType)) || !((catchupSource = channel.getCatchupSource()) == null || kotlin.text.o.h0(catchupSource)) || kotlin.text.o.T(channel.getStreamUrl(), "/timeshift/", true) || channel.getXtreamStreamId() != null || kotlin.text.o.T(channel.getStreamUrl(), "/live/", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void warmXtreamVodCache() {
        v1 v1Var = this.warmVodJob;
        if (v1Var == null || !v1Var.isActive()) {
            o2 o2VarP = m0.p(z0.h(this), x0.f19655d, 0, new C16221(null), 2);
            o2VarP.invokeOnCompletion(new h0(this, 7));
            this.warmVodJob = o2VarP;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 warmXtreamVodCache$lambda$0$0(TvViewModel tvViewModel, Throwable th) {
        tvViewModel.warmVodJob = null;
        return t0.f22605a;
    }

    public final String getCachedChannelsSignature() {
        return this.cachedChannelsSignature;
    }

    public final Object getCachedEnrichedChannels() {
        return this.cachedEnrichedChannels;
    }

    public final IptvRepository getIptvRepository() {
        return this.iptvRepository;
    }

    public final h1<TvUiState> getUiState() {
        return this.uiState;
    }

    public final void moveGroupDown(String groupName) {
        moveGroupDown(null, groupName);
    }

    public final void moveGroupToTop(String groupName) {
        moveGroupToTop(null, groupName);
    }

    public final void moveGroupUp(String groupName) {
        moveGroupUp(null, groupName);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void prefetchVisibleCategoryEpg(java.util.List<java.lang.String> r19, java.lang.String r20, int r21, int r22, boolean r23) {
        /*
            Method dump skipped, instruction units count: 416
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.prefetchVisibleCategoryEpg(java.util.List, java.lang.String, int, int, boolean):void");
    }

    public final void refresh(boolean force, boolean showLoading, boolean forceEpg) {
        v1 v1Var;
        v1 v1Var2 = this.refreshJob;
        if (v1Var2 == null || !v1Var2.isActive()) {
            if (force && (v1Var = this.epgRefreshJob) != null) {
                v1Var.cancel((CancellationException) null);
            }
            o2 o2VarP = m0.p(z0.h(this), null, 0, new C16061(showLoading, force, forceEpg, null), 3);
            o2VarP.invokeOnCompletion(new h0(this, 6));
            this.refreshJob = o2VarP;
        }
    }

    public final void refreshCatchupHistoryForChannel(String channelId) {
        String str;
        String string = channelId != null ? kotlin.text.o.L0(channelId).toString() : null;
        if (string == null) {
            string = "";
        }
        String str2 = string;
        if (kotlin.text.o.h0(str2)) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        TvUiState tvUiState = (TvUiState) this._uiState.getValue();
        IptvChannel iptvChannelLookupChannelById = tvUiState.getChannelLookup().get(str2);
        if (iptvChannelLookupChannelById == null) {
            iptvChannelLookupChannelById = lookupChannelById(tvUiState, str2);
        }
        if (supportsCatchup(iptvChannelLookupChannelById) && !hasRecentCatchupHistory(iptvChannelLookupChannelById, tvUiState.getSnapshot().getNowNext().get(str2), jCurrentTimeMillis)) {
            Long l10 = this.catchupHistoryRefreshAt.get(str2);
            if (jCurrentTimeMillis - (l10 != null ? l10.longValue() : 0L) < 45000) {
                return;
            }
            this.catchupHistoryRefreshAt.put(str2, Long.valueOf(jCurrentTimeMillis));
            while (this.catchupHistoryRefreshAt.size() > 120 && (str = (String) kotlin.collections.x.n0(this.catchupHistoryRefreshAt.keySet())) != null) {
                this.catchupHistoryRefreshAt.remove(str);
            }
            markEpgLoading(Collections.singleton(str2));
            m0.p(z0.h(this), null, 0, new C16071(str2, this, tvUiState, jCurrentTimeMillis, null), 3);
        }
    }

    public final void refreshCurrentChannelEpg(String channelId, boolean forceNetworkForLargeList) {
        String str;
        String string = channelId != null ? kotlin.text.o.L0(channelId).toString() : null;
        if (string == null) {
            string = "";
        }
        if (kotlin.text.o.h0(string)) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        Long l10 = this.currentChannelEpgRefreshAt.get(string);
        if (jCurrentTimeMillis - (l10 != null ? l10.longValue() : 0L) < 12000) {
            return;
        }
        this.currentChannelEpgRefreshAt.put(string, Long.valueOf(jCurrentTimeMillis));
        while (this.currentChannelEpgRefreshAt.size() > 160 && (str = (String) kotlin.collections.x.n0(this.currentChannelEpgRefreshAt.keySet())) != null) {
            this.currentChannelEpgRefreshAt.remove(str);
        }
        markEpgLoading(Collections.singleton(string));
        m0.p(z0.h(this), null, 0, new C16081(string, forceNetworkForLargeList, null), 3);
    }

    public final void rememberTvSession(String lastChannelId, String lastGroupName, String lastFocusedZone, boolean markOpened) {
        List<String> recentChannelIds;
        IptvTvSessionState tvSession = ((TvUiState) this._uiState.getValue()).getTvSession();
        String string = kotlin.text.o.L0(lastChannelId == null ? "" : lastChannelId).toString();
        if (kotlin.text.o.h0(string)) {
            string = tvSession.getLastChannelId();
        }
        String str = string;
        String string2 = kotlin.text.o.L0(lastGroupName != null ? lastGroupName : "").toString();
        if (kotlin.text.o.h0(string2)) {
            string2 = tvSession.getLastGroupName();
        }
        String str2 = string2;
        String string3 = kotlin.text.o.L0(lastFocusedZone).toString();
        if (kotlin.text.o.h0(string3)) {
            string3 = tvSession.getLastFocusedZone();
            if (kotlin.text.o.h0(string3)) {
                string3 = "GUIDE";
            }
        }
        String str3 = string3;
        boolean z = (kotlin.text.o.h0(str) || str.equals(tvSession.getLastChannelId())) ? false : true;
        if (kotlin.text.o.h0(str) || !(markOpened || z)) {
            recentChannelIds = tvSession.getRecentChannelIds();
        } else {
            List<String> recentChannelIds2 = tvSession.getRecentChannelIds();
            ArrayList arrayList = new ArrayList();
            for (Object obj : recentChannelIds2) {
                if (!kotlin.jvm.internal.p.a((String) obj, str)) {
                    arrayList.add(obj);
                }
            }
            recentChannelIds = kotlin.collections.x.Y0(40, kotlin.collections.x.H0(str, arrayList));
        }
        IptvTvSessionState iptvTvSessionStateCopy = tvSession.copy(str, str2, str3, (markOpened || z) ? System.currentTimeMillis() : tvSession.getLastOpenedAt(), recentChannelIds);
        if (kotlin.jvm.internal.p.a(iptvTvSessionStateCopy, tvSession)) {
            return;
        }
        q0<TvUiState> q0Var = this._uiState;
        q0Var.setValue(TvUiState.copy$default((TvUiState) q0Var.getValue(), false, null, null, 0, null, null, null, null, null, iptvTvSessionStateCopy, false, false, false, null, null, null, false, 130559, null));
        maybeWarmStartupGuide();
        v1 v1Var = this.tvSessionSaveJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.tvSessionSaveJob = m0.p(z0.h(this), x0.f19655d, 0, new C16101(markOpened, z, this, iptvTvSessionStateCopy, null), 2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x007f, code lost:
    
        if (r12 == r6) goto L32;
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00be A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolvePlayableStreamUrl$app_playRelease(com.arflix.tv.data.model.IptvChannel r8, com.arflix.tv.data.model.IptvProgram r9, boolean r10, int r11, d7.d<? super com.arflix.tv.data.repository.IptvPlaybackTarget> r12) throws java.lang.Throwable {
        /*
            r7 = this;
            boolean r0 = r12 instanceof com.arflix.tv.ui.screens.tv.TvViewModel$resolvePlayableStreamUrl$1
            if (r0 == 0) goto L13
            r0 = r12
            com.arflix.tv.ui.screens.tv.TvViewModel$resolvePlayableStreamUrl$1 r0 = (com.arflix.tv.ui.screens.tv.TvViewModel$resolvePlayableStreamUrl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.ui.screens.tv.TvViewModel$resolvePlayableStreamUrl$1 r0 = new com.arflix.tv.ui.screens.tv.TvViewModel$resolvePlayableStreamUrl$1
            r0.<init>(r7, r12)
        L18:
            java.lang.Object r12 = r0.result
            int r1 = r0.label
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            e7.a r6 = e7.a.f15033i
            if (r1 == 0) goto L6a
            if (r1 == r4) goto L5a
            if (r1 == r3) goto L46
            if (r1 != r2) goto L3e
            java.lang.Object r8 = r0.L$3
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r8 = r0.L$2
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r8 = r0.L$1
            com.arflix.tv.data.model.IptvProgram r8 = (com.arflix.tv.data.model.IptvProgram) r8
            java.lang.Object r8 = r0.L$0
            com.arflix.tv.data.model.IptvChannel r8 = (com.arflix.tv.data.model.IptvChannel) r8
            k2.c.G(r12)
            return r12
        L3e:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L46:
            int r8 = r0.I$0
            boolean r9 = r0.Z$0
            java.lang.Object r10 = r0.L$2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r10 = r0.L$1
            com.arflix.tv.data.model.IptvProgram r10 = (com.arflix.tv.data.model.IptvProgram) r10
            java.lang.Object r10 = r0.L$0
            com.arflix.tv.data.model.IptvChannel r10 = (com.arflix.tv.data.model.IptvChannel) r10
            k2.c.G(r12)
            goto L9f
        L5a:
            int r11 = r0.I$0
            boolean r10 = r0.Z$0
            java.lang.Object r8 = r0.L$1
            com.arflix.tv.data.model.IptvProgram r8 = (com.arflix.tv.data.model.IptvProgram) r8
            java.lang.Object r8 = r0.L$0
            com.arflix.tv.data.model.IptvChannel r8 = (com.arflix.tv.data.model.IptvChannel) r8
            k2.c.G(r12)
            goto L82
        L6a:
            k2.c.G(r12)
            if (r9 == 0) goto L85
            com.arflix.tv.data.repository.IptvRepository r12 = r7.iptvRepository
            r0.L$0 = r8
            r0.L$1 = r5
            r0.Z$0 = r10
            r0.I$0 = r11
            r0.label = r4
            java.lang.Object r12 = r12.resolvePlayableCatchupUrl(r8, r9, r11, r0)
            if (r12 != r6) goto L82
            goto Lbd
        L82:
            java.lang.String r12 = (java.lang.String) r12
            goto L89
        L85:
            java.lang.String r12 = r8.getStreamUrl()
        L89:
            r0.L$0 = r8
            r0.L$1 = r5
            r0.L$2 = r5
            r0.Z$0 = r10
            r0.I$0 = r11
            r0.label = r3
            java.lang.Object r12 = r7.resolveStalkerStreamIfNeeded(r12, r10, r0)
            if (r12 != r6) goto L9c
            goto Lbd
        L9c:
            r9 = r10
            r10 = r8
            r8 = r11
        L9f:
            java.lang.String r12 = (java.lang.String) r12
            com.arflix.tv.data.repository.IptvPlaybackUrlResolver r11 = r7.getIptvPlaybackUrlResolver()
            java.util.Map r10 = r10.getRequestHeaders()
            r0.L$0 = r5
            r0.L$1 = r5
            r0.L$2 = r5
            r0.L$3 = r5
            r0.Z$0 = r9
            r0.I$0 = r8
            r0.label = r2
            java.lang.Object r8 = r11.resolve(r12, r10, r9, r0)
            if (r8 != r6) goto Lbe
        Lbd:
            return r6
        Lbe:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.resolvePlayableStreamUrl$app_playRelease(com.arflix.tv.data.model.IptvChannel, com.arflix.tv.data.model.IptvProgram, boolean, int, d7.d):java.lang.Object");
    }

    public final void setCachedChannelsSignature(String str) {
        this.cachedChannelsSignature = str;
    }

    public final void setCachedEnrichedChannels(Object obj) {
        this.cachedEnrichedChannels = obj;
    }

    public final void setLiveTvPlaybackActive(boolean active) {
        if (this.liveTvPlaybackActive == active) {
            return;
        }
        this.liveTvPlaybackActive = active;
        if (!active) {
            scheduleDeferredCompleteEpgBackfill();
            return;
        }
        v1 v1Var = this.deferredCompleteEpgBackfillJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.deferredCompleteEpgBackfillJob = null;
        v1 v1Var2 = this.completeEpgBackfillJob;
        if (v1Var2 == null || !v1Var2.isActive()) {
            return;
        }
        this.deferredCompleteEpgBackfill = true;
        this.lastCompleteEpgBackfillKey = null;
        System.err.println("[EPG-Complete] Pausing full guide backfill because live playback started");
        v1 v1Var3 = this.completeEpgBackfillJob;
        if (v1Var3 != null) {
            v1Var3.cancel((CancellationException) null);
        }
        setEpgBackfillInProgress(false);
    }

    public final void setQuery(String query) {
        setUiState(TvUiState.copy$default((TvUiState) this._uiState.getValue(), false, null, null, 0, null, null, null, null, null, null, false, false, false, query, null, null, false, 122879, null));
    }

    public final void toggleFavoriteChannel(String channelId) {
        m0.p(z0.h(this), null, 0, new C16191(channelId, null), 3);
    }

    public final void toggleFavoriteGroup(String groupName) {
        m0.p(z0.h(this), null, 0, new C16201(groupName, null), 3);
    }

    public final void toggleHiddenGroup(String groupName) {
        toggleHiddenGroup(null, groupName);
    }

    public final void moveGroupDown(String playlistId, String groupName) {
        m0.p(z0.h(this), x0.f19655d, 0, new C16001(playlistId, this, groupName, null), 2);
    }

    public final void moveGroupToTop(String playlistId, String groupName) {
        m0.p(z0.h(this), x0.f19655d, 0, new C16011(playlistId, this, groupName, null), 2);
    }

    public final void moveGroupUp(String playlistId, String groupName) {
        m0.p(z0.h(this), x0.f19655d, 0, new C16021(playlistId, this, groupName, null), 2);
    }

    public final void toggleHiddenGroup(String playlistId, String groupName) {
        m0.p(z0.h(this), null, 0, new C16211(playlistId, this, groupName, null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object refreshGuideFromCache(java.util.Set<java.lang.String> r6, d7.d<? super x6.t0> r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.arflix.tv.ui.screens.tv.TvViewModel.AnonymousClass2
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.ui.screens.tv.TvViewModel$refreshGuideFromCache$2 r0 = (com.arflix.tv.ui.screens.tv.TvViewModel.AnonymousClass2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.ui.screens.tv.TvViewModel$refreshGuideFromCache$2 r0 = new com.arflix.tv.ui.screens.tv.TvViewModel$refreshGuideFromCache$2
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            x6.t0 r3 = x6.t0.f22605a
            if (r1 == 0) goto L33
            if (r1 != r2) goto L2b
            java.lang.Object r6 = r0.L$0
            java.util.Set r6 = (java.util.Set) r6
            k2.c.G(r7)
            goto L52
        L2b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L33:
            k2.c.G(r7)
            boolean r7 = r6.isEmpty()
            if (r7 == 0) goto L3d
            goto L56
        L3d:
            ra.d r7 = ka.x0.f19653b
            com.arflix.tv.ui.screens.tv.TvViewModel$refreshGuideFromCache$updated$2 r1 = new com.arflix.tv.ui.screens.tv.TvViewModel$refreshGuideFromCache$updated$2
            r4 = 0
            r1.<init>(r5, r6, r4)
            r0.L$0 = r4
            r0.label = r2
            java.lang.Object r7 = ka.m0.y(r7, r1, r0)
            e7.a r6 = e7.a.f15033i
            if (r7 != r6) goto L52
            return r6
        L52:
            java.util.Map r7 = (java.util.Map) r7
            if (r7 != 0) goto L57
        L56:
            return r3
        L57:
            r5.mergeNowNext(r7)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.TvViewModel.refreshGuideFromCache(java.util.Set, d7.d):java.lang.Object");
    }
}
