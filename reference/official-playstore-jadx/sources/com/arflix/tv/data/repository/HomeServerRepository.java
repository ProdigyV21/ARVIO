package com.arflix.tv.data.repository;

import android.content.Context;
import android.provider.Settings;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.fragment.app.a2;
import androidx.media3.common.PlaybackException;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.BuildConfig;
import com.arflix.tv.R;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.ProxyHeaders;
import com.arflix.tv.data.model.StreamBehaviorHints;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.repository.HomeServerRepository;
import com.arflix.tv.util.AppLogger;
import com.arflix.tv.util.DataStoresKt;
import com.arflix.tv.util.SecureStorage;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.android.gms.cast.MediaError;
import com.google.common.util.concurrent.r0;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import dagger.hilt.android.qualifiers.ApplicationContext;
import gb.s0;
import io.github.jan.supabase.gotrue.SettingsSessionManager;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import j$.time.Instant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.regex.Pattern;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.x0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import na.y0;
import org.jsoup.helper.HttpConnection;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000¼\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b*\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r*\u0002å\u0002\b\u0007\u0018\u0000 ñ\u00022\u00020\u0001:\u0010ñ\u0002ò\u0002ó\u0002ô\u0002õ\u0002ö\u0002÷\u0002ø\u0002B#\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ8\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\nH\u0086@¢\u0006\u0004\b\u0011\u0010\u0012J2\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0014\u001a\u00020\n2\b\b\u0002\u0010\u0015\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\nH\u0086@¢\u0006\u0004\b\u0016\u0010\u0017J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0086@¢\u0006\u0004\b\u0019\u0010\u001aJ\u001c\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001c0\u000fH\u0086@¢\u0006\u0004\b\u001d\u0010\u001aJ\u0010\u0010 \u001a\u00020\u001fH\u0086@¢\u0006\u0004\b \u0010\u001aJ\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020\"0\u000f2\u0006\u0010!\u001a\u00020\nH\u0086@¢\u0006\u0004\b#\u0010$J\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00020\"0\u000fH\u0086@¢\u0006\u0004\b&\u0010\u001aJ \u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000f2\u0006\u0010(\u001a\u00020\nH\u0086@¢\u0006\u0004\b)\u0010$J4\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f2\u0006\u0010+\u001a\u00020\"2\b\b\u0002\u0010\u0015\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\nH\u0086@¢\u0006\u0004\b,\u0010-J\u0012\u0010/\u001a\u0004\u0018\u00010\u0010H\u0086@¢\u0006\u0004\b/\u0010\u001aJ\u0016\u00100\u001a\b\u0012\u0004\u0012\u00020\u00100\u001cH\u0086@¢\u0006\u0004\b0\u0010\u001aJ\u0010\u00102\u001a\u000201H\u0086@¢\u0006\u0004\b2\u0010\u001aJ\u0016\u00104\u001a\b\u0012\u0004\u0012\u0002030\u001cH\u0086@¢\u0006\u0004\b4\u0010\u001aJT\u0010@\u001a\u00020?2\b\u00105\u001a\u0004\u0018\u00010\n2\u0006\u00107\u001a\u0002062\u0006\u00108\u001a\u0002062\b\b\u0002\u0010:\u001a\u0002092\n\b\u0002\u0010<\u001a\u0004\u0018\u00010;2\b\b\u0002\u0010=\u001a\u00020\n2\b\b\u0002\u0010>\u001a\u000201H\u0086@¢\u0006\u0004\b@\u0010AJ<\u0010G\u001a\b\u0012\u0004\u0012\u00020F0\u001c2\b\u0010B\u001a\u0004\u0018\u00010\n2\u0006\u0010C\u001a\u00020\n2\b\u0010D\u001a\u0004\u0018\u0001062\b\u0010E\u001a\u0004\u0018\u000106H\u0086@¢\u0006\u0004\bG\u0010HJL\u0010L\u001a\b\u0012\u0004\u0012\u00020F0\u001c2\b\u0010B\u001a\u0004\u0018\u00010\n2\u0006\u0010C\u001a\u00020\n2\u0006\u0010I\u001a\u0002062\u0006\u0010J\u001a\u0002062\b\u0010E\u001a\u0004\u0018\u0001062\b\u0010K\u001a\u0004\u0018\u000106H\u0086@¢\u0006\u0004\bL\u0010MJ\u0018\u0010O\u001a\u00020\n2\u0006\u0010N\u001a\u00020\nH\u0086@¢\u0006\u0004\bO\u0010$J\"\u0010Q\u001a\u00020\u001f2\u0006\u0010N\u001a\u00020\n2\b\u0010P\u001a\u0004\u0018\u00010\nH\u0086@¢\u0006\u0004\bQ\u0010RJ\u000f\u0010S\u001a\u00020\"H\u0002¢\u0006\u0004\bS\u0010TJ\u0017\u0010U\u001a\u00020\"2\u0006\u0010!\u001a\u00020\nH\u0002¢\u0006\u0004\bU\u0010VJ\"\u0010W\u001a\u0004\u0018\u00010\u00102\u0006\u0010+\u001a\u00020\"2\u0006\u0010\u000e\u001a\u00020\nH\u0082@¢\u0006\u0004\bW\u0010XJ\u0018\u0010Z\u001a\u00020\u001f2\u0006\u0010Y\u001a\u00020\u0010H\u0082@¢\u0006\u0004\bZ\u0010[J\u001e\u0010]\u001a\u00020\u001f2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00100\u001cH\u0082@¢\u0006\u0004\b]\u0010^J&\u0010_\u001a\u00020\u001f2\u0006\u0010N\u001a\u00020\n2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00100\u001cH\u0082@¢\u0006\u0004\b_\u0010`J\u001e\u0010a\u001a\b\u0012\u0004\u0012\u00020\u00100\u001c2\u0006\u0010N\u001a\u00020\nH\u0082@¢\u0006\u0004\ba\u0010$J\u001d\u0010c\u001a\b\u0012\u0004\u0012\u00020\n0b2\u0006\u0010N\u001a\u00020\nH\u0002¢\u0006\u0004\bc\u0010dJ\u001b\u0010e\u001a\u0004\u0018\u00010\u00102\b\u0010P\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\be\u0010fJ\u001f\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00100\u001c2\b\u0010P\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0004\bg\u0010hJ\u0013\u0010i\u001a\u00020\u0010*\u00020\u0010H\u0002¢\u0006\u0004\bi\u0010jJ\u0013\u0010k\u001a\u00020\u0010*\u00020\u0010H\u0002¢\u0006\u0004\bk\u0010jJ\u0013\u0010l\u001a\u00020\u0010*\u00020\u0010H\u0002¢\u0006\u0004\bl\u0010jJ'\u0010p\u001a\u00020\n2\u0006\u0010!\u001a\u00020\n2\u0006\u0010n\u001a\u00020m2\u0006\u0010o\u001a\u00020\nH\u0002¢\u0006\u0004\bp\u0010qJ\u0017\u0010r\u001a\u00020\n2\u0006\u0010Y\u001a\u00020\u0010H\u0002¢\u0006\u0004\br\u0010sJi\u0010u\u001a\u00020\n2\u0006\u0010t\u001a\u00020\n2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00100\u001c2\u0006\u0010C\u001a\u00020\n2\b\u0010D\u001a\u0004\u0018\u0001062\b\u0010B\u001a\u0004\u0018\u00010\n2\b\u0010E\u001a\u0004\u0018\u0001062\b\u0010K\u001a\u0004\u0018\u0001062\b\u0010I\u001a\u0004\u0018\u0001062\b\u0010J\u001a\u0004\u0018\u000106H\u0002¢\u0006\u0004\bu\u0010vJ\u001f\u0010x\u001a\n\u0012\u0004\u0012\u00020F\u0018\u00010\u001c2\u0006\u0010w\u001a\u00020\nH\u0002¢\u0006\u0004\bx\u0010hJB\u0010|\u001a\b\u0012\u0004\u0012\u00020F0\u001c2\u0006\u0010w\u001a\u00020\n2\"\u0010{\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020F0\u001c0z\u0012\u0006\u0012\u0004\u0018\u00010\u00010yH\u0082@¢\u0006\u0004\b|\u0010}J&\u0010\u007f\u001a\u00020\u001f2\u0006\u0010w\u001a\u00020\n2\f\u0010~\u001a\b\u0012\u0004\u0012\u00020F0\u001cH\u0002¢\u0006\u0005\b\u007f\u0010\u0080\u0001J\u0012\u0010\u0081\u0001\u001a\u00020\u001fH\u0002¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J\u001a\u0010\u0083\u0001\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J$\u0010\u0087\u0001\u001a\u00020m2\u0007\u0010\u0085\u0001\u001a\u00020\n2\u0007\u0010\u0086\u0001\u001a\u00020\nH\u0002¢\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001J\u0012\u0010\u0089\u0001\u001a\u00020\nH\u0002¢\u0006\u0006\b\u0089\u0001\u0010\u008a\u0001J\u001f\u0010\u008c\u0001\u001a\u00020\n2\u000b\b\u0002\u0010\u008b\u0001\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0006\b\u008c\u0001\u0010\u0084\u0001J\u0013\u0010\u008e\u0001\u001a\u00030\u008d\u0001H\u0002¢\u0006\u0006\b\u008e\u0001\u0010\u008f\u0001J,\u0010\u0091\u0001\u001a\u000f\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0090\u00012\u000b\b\u0002\u0010\u008b\u0001\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0006\b\u0091\u0001\u0010\u0092\u0001J\u001b\u0010\u0094\u0001\u001a\u00020\n2\u0007\u0010\u0093\u0001\u001a\u00020\nH\u0002¢\u0006\u0006\b\u0094\u0001\u0010\u0084\u0001J(\u0010\u0097\u0001\u001a\u00030\u0096\u00012\u0007\u0010\u0095\u0001\u001a\u00020\n2\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0006\b\u0097\u0001\u0010\u0098\u0001J'\u0010\u0099\u0001\u001a\u000f\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u0090\u00012\u0006\u0010Y\u001a\u00020\u0010H\u0002¢\u0006\u0006\b\u0099\u0001\u0010\u009a\u0001J>\u0010\u009e\u0001\u001a\u00020\n2\u0007\u0010\u009b\u0001\u001a\u00020\n2\u0007\u0010\u009c\u0001\u001a\u00020\n2\u0018\b\u0002\u0010\u009d\u0001\u001a\u0011\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0090\u0001H\u0002¢\u0006\u0006\b\u009e\u0001\u0010\u009f\u0001J$\u0010¡\u0001\u001a\u00020\n2\u0007\u0010\u009b\u0001\u001a\u00020\n2\u0007\u0010 \u0001\u001a\u00020\nH\u0002¢\u0006\u0006\b¡\u0001\u0010¢\u0001J\"\u0010¤\u0001\u001a\u00030\u0096\u0001*\u00030\u0096\u00012\b\u0010£\u0001\u001a\u00030\u008d\u0001H\u0002¢\u0006\u0006\b¤\u0001\u0010¥\u0001J(\u0010§\u0001\u001a\u00030¦\u00012\u0007\u0010\u0095\u0001\u001a\u00020\n2\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0006\b§\u0001\u0010¨\u0001J'\u0010©\u0001\u001a\u00020\n2\u0007\u0010\u0095\u0001\u001a\u00020\n2\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0006\b©\u0001\u0010ª\u0001J2\u0010¬\u0001\u001a\u00030¦\u00012\u0007\u0010\u0095\u0001\u001a\u00020\n2\b\u0010«\u0001\u001a\u00030¦\u00012\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u0010H\u0002¢\u0006\u0006\b¬\u0001\u0010\u00ad\u0001J\u001b\u0010¯\u0001\u001a\u00030®\u00012\u0006\u0010!\u001a\u00020\nH\u0002¢\u0006\u0006\b¯\u0001\u0010°\u0001J\u001b\u0010±\u0001\u001a\u00030®\u00012\u0006\u0010Y\u001a\u00020\u0010H\u0002¢\u0006\u0006\b±\u0001\u0010²\u0001J+\u0010´\u0001\u001a\u00030³\u00012\u0006\u0010!\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0002¢\u0006\u0006\b´\u0001\u0010µ\u0001J$\u0010·\u0001\u001a\u00030³\u00012\u0006\u0010!\u001a\u00020\n2\u0007\u0010¶\u0001\u001a\u00020\nH\u0002¢\u0006\u0006\b·\u0001\u0010¸\u0001J\u001b\u0010¹\u0001\u001a\u00020\n2\u0007\u0010\u008b\u0001\u001a\u00020\nH\u0002¢\u0006\u0006\b¹\u0001\u0010\u0084\u0001JA\u0010¼\u0001\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\n2\u0007\u0010º\u0001\u001a\u00020\n2\n\u0010»\u0001\u001a\u0005\u0018\u00010®\u00012\b\b\u0002\u0010\u000e\u001a\u00020\nH\u0002¢\u0006\u0006\b¼\u0001\u0010½\u0001J \u0010¿\u0001\u001a\t\u0012\u0005\u0012\u00030¾\u00010\u001c2\u0006\u0010\u0014\u001a\u00020\nH\u0002¢\u0006\u0005\b¿\u0001\u0010hJ!\u0010Á\u0001\u001a\t\u0012\u0005\u0012\u00030¾\u00010\u001c2\u0007\u0010À\u0001\u001a\u00020\nH\u0002¢\u0006\u0005\bÁ\u0001\u0010hJ6\u0010Ä\u0001\u001a\u0005\u0018\u00010¾\u00012\u000e\u0010Â\u0001\u001a\t\u0012\u0005\u0012\u00030¾\u00010\u001c2\u0007\u0010Ã\u0001\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\nH\u0002¢\u0006\u0006\bÄ\u0001\u0010Å\u0001J,\u0010Ç\u0001\u001a\b\u0012\u0004\u0012\u00020\n0\u001c2\u0006\u0010\u0015\u001a\u00020\n2\n\u0010Æ\u0001\u001a\u0005\u0018\u00010¾\u0001H\u0002¢\u0006\u0006\bÇ\u0001\u0010È\u0001J$\u0010Ë\u0001\u001a\u0002012\u0007\u0010É\u0001\u001a\u00020\n2\u0007\u0010Ê\u0001\u001a\u00020\nH\u0002¢\u0006\u0006\bË\u0001\u0010Ì\u0001J#\u0010Î\u0001\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\n2\u0007\u0010Í\u0001\u001a\u00020\nH\u0002¢\u0006\u0006\bÎ\u0001\u0010¢\u0001J\u0019\u0010Ï\u0001\u001a\u00020\u00102\u0006\u0010Y\u001a\u00020\u0010H\u0002¢\u0006\u0005\bÏ\u0001\u0010jJ9\u0010Ó\u0001\u001a\t\u0012\u0005\u0012\u00030Ð\u00010\u001c2\u000e\u0010Ñ\u0001\u001a\t\u0012\u0005\u0012\u00030Ð\u00010\u001c2\u000e\u0010Ò\u0001\u001a\t\u0012\u0005\u0012\u00030Ð\u00010\u001cH\u0002¢\u0006\u0006\bÓ\u0001\u0010Ô\u0001J!\u0010Õ\u0001\u001a\t\u0012\u0005\u0012\u00030Ð\u00010\u001c2\u0006\u0010Y\u001a\u00020\u0010H\u0002¢\u0006\u0006\bÕ\u0001\u0010Ö\u0001J \u0010Ø\u0001\u001a\u000203*\u00020\u00102\b\u0010×\u0001\u001a\u00030Ð\u0001H\u0002¢\u0006\u0006\bØ\u0001\u0010Ù\u0001J \u0010Ú\u0001\u001a\b\u0012\u0004\u0012\u0002030\u001c2\u0006\u0010Y\u001a\u00020\u0010H\u0002¢\u0006\u0006\bÚ\u0001\u0010Ö\u0001J \u0010Û\u0001\u001a\b\u0012\u0004\u0012\u0002030\u001c2\u0006\u0010Y\u001a\u00020\u0010H\u0002¢\u0006\u0006\bÛ\u0001\u0010Ö\u0001J \u0010Ü\u0001\u001a\b\u0012\u0004\u0012\u0002030\u001c2\u0006\u0010Y\u001a\u00020\u0010H\u0002¢\u0006\u0006\bÜ\u0001\u0010Ö\u0001JV\u0010ß\u0001\u001a\u00020?2\u0006\u0010Y\u001a\u00020\u00102\u0007\u0010Ý\u0001\u001a\u00020\n2\u0007\u0010Þ\u0001\u001a\u00020\n2\u0006\u00107\u001a\u0002062\u0006\u00108\u001a\u0002062\u0006\u0010:\u001a\u0002092\b\u0010<\u001a\u0004\u0018\u00010;2\u0006\u0010=\u001a\u00020\nH\u0002¢\u0006\u0006\bß\u0001\u0010à\u0001JV\u0010á\u0001\u001a\u00020?2\u0006\u0010Y\u001a\u00020\u00102\u0007\u0010Ý\u0001\u001a\u00020\n2\u0007\u0010Þ\u0001\u001a\u00020\n2\u0006\u00107\u001a\u0002062\u0006\u00108\u001a\u0002062\u0006\u0010:\u001a\u0002092\b\u0010<\u001a\u0004\u0018\u00010;2\u0006\u0010=\u001a\u00020\nH\u0002¢\u0006\u0006\bá\u0001\u0010à\u0001JV\u0010â\u0001\u001a\u00020?2\u0006\u0010Y\u001a\u00020\u00102\u0007\u0010Ý\u0001\u001a\u00020\n2\u0007\u0010Þ\u0001\u001a\u00020\n2\u0006\u00107\u001a\u0002062\u0006\u00108\u001a\u0002062\u0006\u0010:\u001a\u0002092\b\u0010<\u001a\u0004\u0018\u00010;2\u0006\u0010=\u001a\u00020\nH\u0002¢\u0006\u0006\bâ\u0001\u0010à\u0001JH\u0010ä\u0001\u001a\t\u0012\u0005\u0012\u00030ã\u00010\u001c2\u0006\u0010Y\u001a\u00020\u00102\b\u0010B\u001a\u0004\u0018\u00010\n2\u0006\u0010C\u001a\u00020\n2\b\u0010D\u001a\u0004\u0018\u0001062\b\u0010E\u001a\u0004\u0018\u000106H\u0082@¢\u0006\u0006\bä\u0001\u0010å\u0001JP\u0010è\u0001\u001a\t\u0012\u0005\u0012\u00030ã\u00010\u001c2\u000f\u0010ç\u0001\u001a\n\u0012\u0005\u0012\u00030ã\u00010æ\u00012\u0006\u0010C\u001a\u00020\n2\b\u0010D\u001a\u0004\u0018\u0001062\b\u0010B\u001a\u0004\u0018\u00010\n2\b\u0010E\u001a\u0004\u0018\u000106H\u0002¢\u0006\u0006\bè\u0001\u0010é\u0001JN\u0010ê\u0001\u001a\u0005\u0018\u00010ã\u00012\u0006\u0010Y\u001a\u00020\u00102\b\u0010B\u001a\u0004\u0018\u00010\n2\u0006\u0010C\u001a\u00020\n2\b\u0010D\u001a\u0004\u0018\u0001062\b\u0010E\u001a\u0004\u0018\u0001062\b\u0010K\u001a\u0004\u0018\u000106H\u0082@¢\u0006\u0006\bê\u0001\u0010ë\u0001J6\u0010ì\u0001\u001a\b\u0012\u0004\u0012\u00020\n0\u001c2\b\u0010B\u001a\u0004\u0018\u00010\n2\b\u0010E\u001a\u0004\u0018\u0001062\b\u0010K\u001a\u0004\u0018\u000106H\u0002¢\u0006\u0006\bì\u0001\u0010í\u0001JV\u0010î\u0001\u001a\u0005\u0018\u00010ã\u00012\u000f\u0010ç\u0001\u001a\n\u0012\u0005\u0012\u00030ã\u00010æ\u00012\u0006\u0010C\u001a\u00020\n2\b\u0010D\u001a\u0004\u0018\u0001062\b\u0010B\u001a\u0004\u0018\u00010\n2\b\u0010E\u001a\u0004\u0018\u0001062\b\u0010K\u001a\u0004\u0018\u000106H\u0002¢\u0006\u0006\bî\u0001\u0010ï\u0001J6\u0010ñ\u0001\u001a\u0005\u0018\u00010ã\u00012\u0006\u0010Y\u001a\u00020\u00102\u0007\u0010ð\u0001\u001a\u00020\n2\u0006\u0010I\u001a\u0002062\u0006\u0010J\u001a\u000206H\u0002¢\u0006\u0006\bñ\u0001\u0010ò\u0001J:\u0010ó\u0001\u001a\t\u0012\u0005\u0012\u00030ã\u00010\u001c2\u0006\u0010Y\u001a\u00020\u00102\u0007\u0010ð\u0001\u001a\u00020\n2\u0006\u0010I\u001a\u0002062\u0006\u0010J\u001a\u000206H\u0002¢\u0006\u0006\bó\u0001\u0010ô\u0001JS\u0010õ\u0001\u001a\u0005\u0018\u00010ã\u00012\u0006\u0010Y\u001a\u00020\u00102\u0006\u0010C\u001a\u00020\n2\u0006\u0010I\u001a\u0002062\u0006\u0010J\u001a\u0002062\b\u0010B\u001a\u0004\u0018\u00010\n2\b\u0010E\u001a\u0004\u0018\u0001062\b\u0010K\u001a\u0004\u0018\u000106H\u0002¢\u0006\u0006\bõ\u0001\u0010ö\u0001JB\u0010ø\u0001\u001a\t\u0012\u0005\u0012\u00030ã\u00010\u001c2\u0006\u0010Y\u001a\u00020\u00102\u0007\u0010÷\u0001\u001a\u00020\n2\u0016\u0010\u009d\u0001\u001a\u0011\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0090\u0001H\u0002¢\u0006\u0006\bø\u0001\u0010ù\u0001JB\u0010ú\u0001\u001a\t\u0012\u0005\u0012\u00030ã\u00010\u001c2\u0006\u0010Y\u001a\u00020\u00102\u0007\u0010÷\u0001\u001a\u00020\n2\u0016\u0010\u009d\u0001\u001a\u0011\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0090\u0001H\u0002¢\u0006\u0006\bú\u0001\u0010ù\u0001JM\u0010þ\u0001\u001a\t\u0012\u0005\u0012\u00030ã\u00010\u001c2\u0006\u0010Y\u001a\u00020\u00102\u000e\u0010û\u0001\u001a\t\u0012\u0005\u0012\u00030Ð\u00010\u001c2\t\u0010ü\u0001\u001a\u0004\u0018\u00010\n2\u0007\u0010ý\u0001\u001a\u00020\n2\u0006\u00108\u001a\u00020\nH\u0002¢\u0006\u0006\bþ\u0001\u0010ÿ\u0001J=\u0010\u0081\u0002\u001a\t\u0012\u0005\u0012\u00030ã\u00010\u001c2\u0006\u0010Y\u001a\u00020\u00102\u0007\u0010\u0080\u0002\u001a\u00020\n2\t\u0010ü\u0001\u001a\u0004\u0018\u00010\n2\u0006\u00108\u001a\u00020\nH\u0002¢\u0006\u0006\b\u0081\u0002\u0010\u0082\u0002JA\u0010\u0084\u0002\u001a\t\u0012\u0005\u0012\u00030ã\u00010\u001c2\u000e\u0010\u0083\u0002\u001a\t\u0012\u0005\u0012\u00030ã\u00010\u001c2\u0016\u0010\u009d\u0001\u001a\u0011\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0090\u0001H\u0002¢\u0006\u0006\b\u0084\u0002\u0010\u0085\u0002J,\u0010\u0087\u0002\u001a\u0002012\b\u0010\u0086\u0002\u001a\u00030ã\u00012\u000e\u0010û\u0001\u001a\t\u0012\u0005\u0012\u00030Ð\u00010\u001cH\u0002¢\u0006\u0006\b\u0087\u0002\u0010\u0088\u0002J \u0010\u008a\u0002\u001a\b\u0012\u0004\u0012\u00020\n0\u001c2\u0007\u0010\u0089\u0002\u001a\u00020\nH\u0002¢\u0006\u0005\b\u008a\u0002\u0010hJ*\u0010\u008b\u0002\u001a\t\u0012\u0005\u0012\u00030Ð\u00010\u001c2\u0006\u0010Y\u001a\u00020\u00102\u0007\u0010÷\u0001\u001a\u00020\nH\u0002¢\u0006\u0006\b\u008b\u0002\u0010\u008c\u0002J\u0012\u0010\u008d\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\b\u008d\u0002\u0010\u008a\u0001J\u0012\u0010\u008e\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\b\u008e\u0002\u0010\u008a\u0001J*\u0010\u008f\u0002\u001a\b\u0012\u0004\u0012\u00020F0\u001c2\u0006\u0010Y\u001a\u00020\u00102\b\u0010\u0086\u0002\u001a\u00030ã\u0001H\u0002¢\u0006\u0006\b\u008f\u0002\u0010\u0090\u0002J?\u0010\u0093\u0002\u001a\u00020F*\u00030\u0091\u00022\u0006\u0010Y\u001a\u00020\u00102\b\u0010\u0086\u0002\u001a\u00030ã\u00012\u0007\u0010\u0095\u0001\u001a\u00020\n2\u000b\b\u0002\u0010\u0092\u0002\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0006\b\u0093\u0002\u0010\u0094\u0002J\u0017\u0010\u0095\u0002\u001a\u00020\n*\u00030\u0091\u0002H\u0002¢\u0006\u0006\b\u0095\u0002\u0010\u0096\u0002J*\u0010\u0098\u0002\u001a\u0004\u0018\u00010\n*\u00030\u0091\u00022\u0006\u0010Y\u001a\u00020\u00102\u0007\u0010\u0097\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\b\u0098\u0002\u0010\u0099\u0002J\"\u0010\u009a\u0002\u001a\u00020\n2\u0006\u0010Y\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0006\b\u009a\u0002\u0010\u009b\u0002J\u0017\u0010\u009c\u0002\u001a\u000201*\u00030\u0091\u0002H\u0002¢\u0006\u0006\b\u009c\u0002\u0010\u009d\u0002J*\u0010\u009e\u0002\u001a\u0004\u0018\u00010\n*\u00030\u0091\u00022\u0006\u0010Y\u001a\u00020\u00102\u0007\u0010\u0097\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\b\u009e\u0002\u0010\u0099\u0002J\u001a\u0010\u009f\u0002\u001a\u00020\n2\u0006\u0010n\u001a\u00020mH\u0002¢\u0006\u0006\b\u009f\u0002\u0010 \u0002J\u0015\u0010¡\u0002\u001a\u00020\n*\u00020\u0010H\u0002¢\u0006\u0005\b¡\u0002\u0010sJ\u001a\u0010¢\u0002\u001a\u00020\n2\u0006\u0010n\u001a\u00020mH\u0002¢\u0006\u0006\b¢\u0002\u0010 \u0002J\u001a\u0010£\u0002\u001a\u00020\n2\u0006\u0010!\u001a\u00020\nH\u0002¢\u0006\u0006\b£\u0002\u0010\u0084\u0001J\u0019\u0010¤\u0002\u001a\u0004\u0018\u00010\n*\u00030\u0091\u0002H\u0002¢\u0006\u0006\b¤\u0002\u0010\u0096\u0002J\u001c\u0010¦\u0002\u001a\u00020\n2\b\u0010¥\u0002\u001a\u00030\u0091\u0002H\u0002¢\u0006\u0006\b¦\u0002\u0010\u0096\u0002J\u001b\u0010¨\u0002\u001a\u0002062\u0007\u0010§\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\b¨\u0002\u0010©\u0002J\u0016\u0010ª\u0002\u001a\u00020\n*\u00020\nH\u0002¢\u0006\u0006\bª\u0002\u0010\u0084\u0001J\u001c\u0010\u00ad\u0002\u001a\u00020\n2\b\u0010¬\u0002\u001a\u00030«\u0002H\u0002¢\u0006\u0006\b\u00ad\u0002\u0010®\u0002J\u001e\u0010¯\u0002\u001a\t\u0012\u0005\u0012\u00030ã\u00010\u001c*\u00030¦\u0001H\u0002¢\u0006\u0006\b¯\u0002\u0010°\u0002J\u001e\u0010²\u0002\u001a\t\u0012\u0005\u0012\u00030±\u00020\u001c*\u00030¦\u0001H\u0002¢\u0006\u0006\b²\u0002\u0010°\u0002J&\u0010³\u0002\u001a\t\u0012\u0005\u0012\u00030ã\u00010\u001c*\u00030¦\u00012\u0006\u0010n\u001a\u00020mH\u0002¢\u0006\u0006\b³\u0002\u0010´\u0002J\u001e\u0010µ\u0002\u001a\t\u0012\u0005\u0012\u00030\u0091\u00020\u001c*\u00030¦\u0001H\u0002¢\u0006\u0006\bµ\u0002\u0010°\u0002J \u0010¶\u0002\u001a\u00030ã\u0001*\u00030¦\u00012\u0006\u0010n\u001a\u00020mH\u0002¢\u0006\u0006\b¶\u0002\u0010·\u0002JD\u0010»\u0002\u001a\u00030\u0091\u0002*\u00030¦\u00012\u0006\u0010n\u001a\u00020m2\f\b\u0002\u0010¸\u0002\u001a\u0005\u0018\u00010¦\u00012\t\b\u0002\u0010¹\u0002\u001a\u0002062\t\b\u0002\u0010º\u0002\u001a\u000206H\u0002¢\u0006\u0006\b»\u0002\u0010¼\u0002J \u0010¾\u0002\u001a\u00020\n*\u00030¦\u00012\u0007\u0010½\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\b¾\u0002\u0010¿\u0002J\"\u0010À\u0002\u001a\u0004\u0018\u000101*\u00030¦\u00012\u0007\u0010½\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\bÀ\u0002\u0010Á\u0002J\"\u0010Â\u0002\u001a\u0004\u0018\u000106*\u00030¦\u00012\u0007\u0010½\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\bÂ\u0002\u0010Ã\u0002J#\u0010Ä\u0002\u001a\u0005\u0018\u00010«\u0002*\u00030¦\u00012\u0007\u0010½\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\bÄ\u0002\u0010Å\u0002J#\u0010Æ\u0002\u001a\u0005\u0018\u00010¦\u0001*\u00030¦\u00012\u0007\u0010½\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\bÆ\u0002\u0010Ç\u0002J'\u0010È\u0002\u001a\t\u0012\u0005\u0012\u00030±\u00020\u001c*\u00030¦\u00012\u0007\u0010½\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\bÈ\u0002\u0010É\u0002J0\u0010È\u0002\u001a\t\u0012\u0005\u0012\u00030±\u00020\u001c*\u00030¦\u00012\u0007\u0010Ê\u0002\u001a\u00020\n2\u0007\u0010½\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\bÈ\u0002\u0010Ë\u0002J\u001a\u0010Ì\u0002\u001a\u0005\u0018\u00010¦\u0001*\u00030±\u0002H\u0002¢\u0006\u0006\bÌ\u0002\u0010Í\u0002J\u0019\u0010Î\u0002\u001a\u0004\u0018\u00010\n*\u00030±\u0002H\u0002¢\u0006\u0006\bÎ\u0002\u0010Ï\u0002J\u001f\u0010Ð\u0002\u001a\u00020\n*\u00020\n2\u0007\u0010½\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\bÐ\u0002\u0010¢\u0001J\u001f\u0010Ñ\u0002\u001a\u000201*\u00020\n2\u0007\u0010½\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\bÑ\u0002\u0010Ì\u0001J\u0016\u0010Ò\u0002\u001a\u00020\n*\u00020\nH\u0002¢\u0006\u0006\bÒ\u0002\u0010\u0084\u0001J(\u0010Õ\u0002\u001a\u000f\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0Ô\u00022\u0007\u0010Ó\u0002\u001a\u00020\nH\u0002¢\u0006\u0006\bÕ\u0002\u0010Ö\u0002R\u0015\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0003\u0010×\u0002R\u0015\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0005\u0010Ø\u0002R\u0015\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0007\u0010Ù\u0002R\u0018\u0010Û\u0002\u001a\u00030Ú\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÛ\u0002\u0010Ü\u0002R\u0018\u0010Þ\u0002\u001a\u00030Ý\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÞ\u0002\u0010ß\u0002R\u0017\u0010à\u0002\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bà\u0002\u0010á\u0002R\u0018\u0010ã\u0002\u001a\u00030â\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bã\u0002\u0010ä\u0002R\u0018\u0010æ\u0002\u001a\u00030å\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bæ\u0002\u0010ç\u0002R1\u0010ê\u0002\u001a\u001c\u0012\u0004\u0012\u00020\n\u0012\u0011\u0012\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020F0\u001c0é\u00020è\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bê\u0002\u0010ë\u0002R'\u0010\\\u001a\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001c0ì\u00028\u0006¢\u0006\u000f\n\u0005\b\\\u0010í\u0002\u001a\u0006\bî\u0002\u0010ï\u0002R#\u0010Y\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00100ì\u00028\u0006¢\u0006\u000f\n\u0005\bY\u0010í\u0002\u001a\u0006\bð\u0002\u0010ï\u0002¨\u0006ù\u0002"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerRepository;", "", "Landroid/content/Context;", "context", "Lgb/h0;", "okHttpClient", "Lcom/arflix/tv/data/repository/ProfileManager;", "profileManager", "<init>", "(Landroid/content/Context;Lgb/h0;Lcom/arflix/tv/data/repository/ProfileManager;)V", "", "rawUrl", "username", "password", "displayName", "Lx6/d0;", "Lcom/arflix/tv/data/repository/HomeServerConnection;", "connect-yxL6bBk", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "connect", "accountToken", "preferredServerUrl", "connectPlexAccount-BWLJW6A", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "connectPlexAccount", "testConnection-IoAF18A", "(Ld7/d;)Ljava/lang/Object;", "testConnection", "", "testConnections-IoAF18A", "testConnections", "Lx6/t0;", "disconnect", "serverUrl", "Lcom/arflix/tv/data/repository/PlexPinAuthSession;", "startHomeServerCodeAuth-gIAlu-s", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "startHomeServerCodeAuth", "startPlexPinAuth-IoAF18A", "startPlexPinAuth", "pinId", "pollPlexPinAuth-gIAlu-s", "pollPlexPinAuth", SettingsSessionManager.SETTINGS_KEY, "pollHomeServerCodeAuth-BWLJW6A", "(Lcom/arflix/tv/data/repository/PlexPinAuthSession;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "pollHomeServerCodeAuth", "currentConnection", "currentConnections", "", "hasUsableConnections", "Lcom/arflix/tv/data/repository/HomeServerCatalogCandidate;", "getCatalogCandidates", "sourceRef", "", "offset", "limit", "Lcom/arflix/tv/data/repository/HomeServerLibrarySort;", "sort", "Lcom/arflix/tv/data/model/MediaType;", "mediaType", "searchQuery", "propagateErrors", "Lcom/arflix/tv/data/repository/HomeServerCatalogPage;", "loadCatalogItems", "(Ljava/lang/String;IILcom/arflix/tv/data/repository/HomeServerLibrarySort;Lcom/arflix/tv/data/model/MediaType;Ljava/lang/String;ZLd7/d;)Ljava/lang/Object;", "imdbId", LinkHeader.Parameters.Title, "year", "tmdbId", "Lcom/arflix/tv/data/model/StreamSource;", "resolveMovieSources", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "season", "episode", "tvdbId", "resolveEpisodeSources", "(Ljava/lang/String;Ljava/lang/String;IILjava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "profileId", "exportCloudConnectionsJsonForProfile", "json", "importCloudConnectionsJsonForProfile", "(Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "startPlexPinAuthInternal", "()Lcom/arflix/tv/data/repository/PlexPinAuthSession;", "startJellyfinQuickConnect", "(Ljava/lang/String;)Lcom/arflix/tv/data/repository/PlexPinAuthSession;", "pollJellyfinQuickConnect", "(Lcom/arflix/tv/data/repository/PlexPinAuthSession;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "connection", "saveConnection", "(Lcom/arflix/tv/data/repository/HomeServerConnection;Ld7/d;)Ljava/lang/Object;", "connections", "saveConnections", "(Ljava/util/List;Ld7/d;)Ljava/lang/Object;", "saveConnectionsForProfile", "(Ljava/lang/String;Ljava/util/List;Ld7/d;)Ljava/lang/Object;", "currentConnectionsForProfile", "Lc1/e;", "connectionKeyFor", "(Ljava/lang/String;)Lc1/e;", "parseConnection", "(Ljava/lang/String;)Lcom/arflix/tv/data/repository/HomeServerConnection;", "parseConnections", "(Ljava/lang/String;)Ljava/util/List;", "encryptedForStorage", "(Lcom/arflix/tv/data/repository/HomeServerConnection;)Lcom/arflix/tv/data/repository/HomeServerConnection;", "decryptedForUse", "sanitized", "Lcom/arflix/tv/data/repository/HomeServerKind;", "kind", "userIdentity", "createConnectionId", "(Ljava/lang/String;Lcom/arflix/tv/data/repository/HomeServerKind;Ljava/lang/String;)Ljava/lang/String;", "connectionIdentity", "(Lcom/arflix/tv/data/repository/HomeServerConnection;)Ljava/lang/String;", LinkHeader.Parameters.Type, "sourceCacheKey", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/String;", "key", "getCachedSourcesLocked", "Lkotlin/Function1;", "Ld7/d;", "loader", "resolveSourcesCached", "(Ljava/lang/String;Lr7/l;Ld7/d;)Ljava/lang/Object;", "sources", "putCachedSources", "(Ljava/lang/String;Ljava/util/List;)V", "clearSourceCache", "()V", "normalizeServerUrl", "(Ljava/lang/String;)Ljava/lang/String;", "productName", "serverName", "detectServerKind", "(Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/repository/HomeServerKind;", "deviceId", "()Ljava/lang/String;", "token", "authHeader", "Lgb/a0;", "plexPublicHeaders", "()Lgb/a0;", "", "plexHeaders", "(Ljava/lang/String;)Ljava/util/Map;", "code", "plexActivationUrl", "url", "Lgb/j0;", "requestBuilder", "(Ljava/lang/String;Lcom/arflix/tv/data/repository/HomeServerConnection;)Lgb/j0;", "playbackHeaders", "(Lcom/arflix/tv/data/repository/HomeServerConnection;)Ljava/util/Map;", "baseUrl", "path", "query", "buildUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)Ljava/lang/String;", "pathOrUrl", "absoluteUrl", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "headers", "headersWith", "(Lgb/j0;Lgb/a0;)Lgb/j0;", "Lcom/google/gson/JsonObject;", "getJson", "(Ljava/lang/String;Lcom/arflix/tv/data/repository/HomeServerConnection;)Lcom/google/gson/JsonObject;", "getText", "(Ljava/lang/String;Lcom/arflix/tv/data/repository/HomeServerConnection;)Ljava/lang/String;", "bodyJson", "postJson", "(Ljava/lang/String;Lcom/google/gson/JsonObject;Lcom/arflix/tv/data/repository/HomeServerConnection;)Lcom/google/gson/JsonObject;", "Lcom/arflix/tv/data/repository/HomeServerRepository$ServerInfo;", "fetchPublicInfo", "(Ljava/lang/String;)Lcom/arflix/tv/data/repository/HomeServerRepository$ServerInfo;", "fetchSystemInfo", "(Lcom/arflix/tv/data/repository/HomeServerConnection;)Lcom/arflix/tv/data/repository/HomeServerRepository$ServerInfo;", "Lcom/arflix/tv/data/repository/HomeServerRepository$AuthResponse;", "authenticate", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/repository/HomeServerRepository$AuthResponse;", "secret", "authenticateWithQuickConnect", "(Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/repository/HomeServerRepository$AuthResponse;", "validatePlexAccount", "preferredUsername", "preferredInfo", "buildPlexConnection", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/repository/HomeServerRepository$ServerInfo;Ljava/lang/String;)Lcom/arflix/tv/data/repository/HomeServerConnection;", "Lcom/arflix/tv/data/repository/HomeServerRepository$PlexResourceDevice;", "fetchPlexResources", "xml", "parsePlexResourcesXml", "resources", "preferredServerId", "selectPlexResourceDevice", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/repository/HomeServerRepository$PlexResourceDevice;", "device", "plexCandidateServerUrls", "(Ljava/lang/String;Lcom/arflix/tv/data/repository/HomeServerRepository$PlexResourceDevice;)Ljava/util/List;", TtmlNode.LEFT, TtmlNode.RIGHT, "sameServerEndpoint", "(Ljava/lang/String;Ljava/lang/String;)Z", "serverId", "resolvePlexServerToken", "refreshConnection", "Lcom/arflix/tv/data/repository/HomeServerCollection;", "refreshed", "previous", "mergeCollectionStates", "(Ljava/util/List;Ljava/util/List;)Ljava/util/List;", "fetchCollections", "(Lcom/arflix/tv/data/repository/HomeServerConnection;)Ljava/util/List;", "collection", "toCatalogCandidate", "(Lcom/arflix/tv/data/repository/HomeServerConnection;Lcom/arflix/tv/data/repository/HomeServerCollection;)Lcom/arflix/tv/data/repository/HomeServerCatalogCandidate;", "fetchServerCollectionCatalogs", "fetchPlexCollectionCatalogs", "fetchJellyfinCollectionCatalogs", "collectionId", "collectionType", "loadConnectionCatalogItems", "(Lcom/arflix/tv/data/repository/HomeServerConnection;Ljava/lang/String;Ljava/lang/String;IILcom/arflix/tv/data/repository/HomeServerLibrarySort;Lcom/arflix/tv/data/model/MediaType;Ljava/lang/String;)Lcom/arflix/tv/data/repository/HomeServerCatalogPage;", "loadPlexCatalogItems", "loadJellyfinCatalogItems", "Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerItem;", "findMovieMatches", "(Lcom/arflix/tv/data/repository/HomeServerConnection;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "", "candidates", "matchingMovieCandidates", "(Ljava/util/Collection;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;)Ljava/util/List;", "findBestSeries", "(Lcom/arflix/tv/data/repository/HomeServerConnection;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "providerQueries", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/util/List;", "bestCandidate", "(Ljava/util/Collection;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerItem;", "seriesId", "findEpisode", "(Lcom/arflix/tv/data/repository/HomeServerConnection;Ljava/lang/String;II)Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerItem;", "findEpisodes", "(Lcom/arflix/tv/data/repository/HomeServerConnection;Ljava/lang/String;II)Ljava/util/List;", "findEpisodeBySearch", "(Lcom/arflix/tv/data/repository/HomeServerConnection;Ljava/lang/String;IILjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerItem;", "itemTypes", "queryItems", "(Lcom/arflix/tv/data/repository/HomeServerConnection;Ljava/lang/String;Ljava/util/Map;)Ljava/util/List;", "queryPlexItems", "collections", "plexType", "guid", "queryPlexByGuid", "(Lcom/arflix/tv/data/repository/HomeServerConnection;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;", "searchTerm", "queryPlexSearch", "(Lcom/arflix/tv/data/repository/HomeServerConnection;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;", "results", "filterPlexEpisodeNumbers", "(Ljava/util/List;Ljava/util/Map;)Ljava/util/List;", "item", "itemBelongsToEnabledPlexCollection", "(Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerItem;Ljava/util/List;)Z", "providerId", "plexGuidQueries", "eligibleCollections", "(Lcom/arflix/tv/data/repository/HomeServerConnection;Ljava/lang/String;)Ljava/util/List;", "itemFields", "catalogItemFields", "buildStreamSources", "(Lcom/arflix/tv/data/repository/HomeServerConnection;Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerItem;)Ljava/util/List;", "Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerMediaSource;", "sourceSuffix", "toStreamSource", "(Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerMediaSource;Lcom/arflix/tv/data/repository/HomeServerConnection;Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerItem;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/model/StreamSource;", "identityKey", "(Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerMediaSource;)Ljava/lang/String;", "itemId", "playbackUrl", "(Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerMediaSource;Lcom/arflix/tv/data/repository/HomeServerConnection;Ljava/lang/String;)Ljava/lang/String;", "plexUrlWithToken", "(Lcom/arflix/tv/data/repository/HomeServerConnection;Ljava/lang/String;)Ljava/lang/String;", "needsPlexCompatiblePlayback", "(Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerMediaSource;)Z", "plexCompatiblePlaybackUrl", "homeServerKindLabel", "(Lcom/arflix/tv/data/repository/HomeServerKind;)Ljava/lang/String;", "displayLabel", "specificHomeServerKindLabel", "serverHostLabel", "streamExtension", "source", "qualityLabel", "quality", "qualityRank", "(Ljava/lang/String;)I", "extractQualityLabel", "", "bytes", "formatBytes", "(J)Ljava/lang/String;", "items", "(Lcom/google/gson/JsonObject;)Ljava/util/List;", "Lcom/google/gson/JsonElement;", "itemsArray", "metadataItems", "(Lcom/google/gson/JsonObject;Lcom/arflix/tv/data/repository/HomeServerKind;)Ljava/util/List;", "mediaSources", "toHomeServerItem", "(Lcom/google/gson/JsonObject;Lcom/arflix/tv/data/repository/HomeServerKind;)Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerItem;", "parentMedia", "mediaIndex", "partIndex", "toMediaSource", "(Lcom/google/gson/JsonObject;Lcom/arflix/tv/data/repository/HomeServerKind;Lcom/google/gson/JsonObject;II)Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerMediaSource;", ContentDisposition.Parameters.Name, "string", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/String;", "boolean", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/Boolean;", "int", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/Integer;", "long", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/Long;", "obj", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonObject;", "array", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/util/List;", "parent", "(Lcom/google/gson/JsonObject;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;", "asJsonObjectOrNull", "(Lcom/google/gson/JsonElement;)Lcom/google/gson/JsonObject;", "asStringOrNull", "(Lcom/google/gson/JsonElement;)Ljava/lang/String;", "xmlAttribute", "xmlBooleanAttribute", "xmlDecoded", TtmlNode.TAG_BODY, "Lx6/x;", "parsePlexIdentity", "(Ljava/lang/String;)Lx6/x;", "Landroid/content/Context;", "Lgb/h0;", "Lcom/arflix/tv/data/repository/ProfileManager;", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "Lgb/d0;", "jsonMediaType", "Lgb/d0;", "sourceCacheLock", "Ljava/lang/Object;", "Lka/k0;", "sourceRequestScope", "Lka/k0;", "com/arflix/tv/data/repository/HomeServerRepository$sourceCache$1", "sourceCache", "Lcom/arflix/tv/data/repository/HomeServerRepository$sourceCache$1;", "", "Lka/p0;", "sourceRequests", "Ljava/util/Map;", "Lna/j;", "Lna/j;", "getConnections", "()Lna/j;", "getConnection", "Companion", "CachedHomeServerSources", "PlexResourceConnection", "PlexResourceDevice", "ServerInfo", "AuthResponse", "HomeServerItem", "HomeServerMediaSource", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class HomeServerRepository {
    public static final String ADDON_ID = "home_server";
    public static final String ADDON_NAME = "Home Server";
    public static final String CATALOG_SOURCE_REF_PREFIX = "home_server_catalog|";
    public static final String CONNECTION_KEY_NAME = "home_server_connection_v1";
    private static final long EMPTY_SOURCE_CACHE_TTL_MS = 30000;
    private static final String HOME_SERVER_SECRET_ALIAS = "arvio_home_server_credentials_v1";
    private static final int SOURCE_CACHE_MAX_ENTRIES = 128;
    private static final long SOURCE_CACHE_TTL_MS = 1800000;
    private final na.j<HomeServerConnection> connection;
    private final na.j<List<HomeServerConnection>> connections;
    private final Context context;
    private final Gson gson = new Gson();
    private final gb.d0 jsonMediaType;
    private final gb.h0 okHttpClient;
    private final ProfileManager profileManager;
    private final HomeServerRepository$sourceCache$1 sourceCache;
    private final Object sourceCacheLock;
    private final ka.k0 sourceRequestScope;
    private final Map<String, ka.p0<List<StreamSource>>> sourceRequests;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerRepository$CachedHomeServerSources;", "", "sources", "", "Lcom/arflix/tv/data/model/StreamSource;", "createdAtMs", "", "<init>", "(Ljava/util/List;J)V", "getSources", "()Ljava/util/List;", "getCreatedAtMs", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class CachedHomeServerSources {
        private final long createdAtMs;
        private final List<StreamSource> sources;

        public CachedHomeServerSources(List<StreamSource> list, long j10) {
            this.sources = list;
            this.createdAtMs = j10;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CachedHomeServerSources copy$default(CachedHomeServerSources cachedHomeServerSources, List list, long j10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                list = cachedHomeServerSources.sources;
            }
            if ((i10 & 2) != 0) {
                j10 = cachedHomeServerSources.createdAtMs;
            }
            return cachedHomeServerSources.copy(list, j10);
        }

        public final List<StreamSource> component1() {
            return this.sources;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getCreatedAtMs() {
            return this.createdAtMs;
        }

        public final CachedHomeServerSources copy(List<StreamSource> sources, long createdAtMs) {
            return new CachedHomeServerSources(sources, createdAtMs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CachedHomeServerSources)) {
                return false;
            }
            CachedHomeServerSources cachedHomeServerSources = (CachedHomeServerSources) other;
            return kotlin.jvm.internal.p.a(this.sources, cachedHomeServerSources.sources) && this.createdAtMs == cachedHomeServerSources.createdAtMs;
        }

        public final long getCreatedAtMs() {
            return this.createdAtMs;
        }

        public final List<StreamSource> getSources() {
            return this.sources;
        }

        public int hashCode() {
            int iHashCode = this.sources.hashCode() * 31;
            long j10 = this.createdAtMs;
            return iHashCode + ((int) (j10 ^ (j10 >>> 32)));
        }

        public String toString() {
            return "CachedHomeServerSources(sources=" + this.sources + ", createdAtMs=" + this.createdAtMs + ")";
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\b\u0010\u0007J\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u000f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J+\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0018\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0019\u0010\u0016R\u0014\u0010\u001a\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001a\u0010\u0016R\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u001e8\u0002X\u0082T¢\u0006\u0006\n\u0004\b!\u0010 ¨\u0006\""}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerRepository$Companion;", "", "<init>", "()V", "", "value", "urlEncodeStatic", "(Ljava/lang/String;)Ljava/lang/String;", "urlDecodeStatic", "Lcom/arflix/tv/data/repository/HomeServerConnection;", "connection", "catalogServerKey", "(Lcom/arflix/tv/data/repository/HomeServerConnection;)Ljava/lang/String;", "Lcom/arflix/tv/data/repository/HomeServerCollection;", "collection", "buildCatalogSourceRef", "(Lcom/arflix/tv/data/repository/HomeServerConnection;Lcom/arflix/tv/data/repository/HomeServerCollection;)Ljava/lang/String;", "sourceRef", "Lx6/j0;", "parseCatalogSourceRef", "(Ljava/lang/String;)Lx6/j0;", "ADDON_ID", "Ljava/lang/String;", "ADDON_NAME", "CONNECTION_KEY_NAME", "CATALOG_SOURCE_REF_PREFIX", "HOME_SERVER_SECRET_ALIAS", "", "SOURCE_CACHE_MAX_ENTRIES", "I", "", "SOURCE_CACHE_TTL_MS", "J", "EMPTY_SOURCE_CACHE_TTL_MS", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence buildCatalogSourceRef$lambda$0(String str) {
            return HomeServerRepository.INSTANCE.urlEncodeStatic(str);
        }

        private final String urlDecodeStatic(String value) {
            return URLDecoder.decode(value, "UTF-8");
        }

        private final String urlEncodeStatic(String value) {
            return URLEncoder.encode(value, "UTF-8");
        }

        public final String buildCatalogSourceRef(HomeServerConnection connection, HomeServerCollection collection) {
            return androidx.compose.material3.d.C(HomeServerRepository.CATALOG_SOURCE_REF_PREFIX, kotlin.collections.x.u0(t7.a.E(catalogServerKey(connection), collection.getId(), collection.getType()), "|", null, null, new f(24), 30));
        }

        public final String catalogServerKey(HomeServerConnection connection) {
            String serverId = connection.getServerId();
            if (kotlin.text.o.h0(serverId)) {
                serverId = connection.getConnectionId();
            }
            return kotlin.text.o.h0(serverId) ? androidx.compose.foundation.c.t(connection.getServerKind().name(), ":", connection.getServerUrl()) : serverId;
        }

        public final x6.j0 parseCatalogSourceRef(String sourceRef) {
            String string = sourceRef != null ? kotlin.text.o.L0(sourceRef).toString() : null;
            if (string == null) {
                string = "";
            }
            if (kotlin.text.u.P(string, HomeServerRepository.CATALOG_SOURCE_REF_PREFIX, true)) {
                List listY0 = kotlin.text.o.y0(string.substring(20), new String[]{"|"}, 0, 6);
                if (listY0.size() >= 2) {
                    String string2 = kotlin.text.o.L0(urlDecodeStatic((String) listY0.get(0))).toString();
                    String string3 = kotlin.text.o.L0(urlDecodeStatic((String) listY0.get(1))).toString();
                    String str = (String) kotlin.collections.x.p0(2, listY0);
                    String string4 = str != null ? kotlin.text.o.L0(HomeServerRepository.INSTANCE.urlDecodeStatic(str)).toString() : null;
                    String str2 = string4 != null ? string4 : "";
                    if (!kotlin.text.o.h0(string2) && !kotlin.text.o.h0(string3)) {
                        return new x6.j0(string2, string3, str2);
                    }
                }
            }
            return null;
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerRepository$PlexResourceConnection;", "", "uri", "", "local", "", "relay", "<init>", "(Ljava/lang/String;ZZ)V", "getUri", "()Ljava/lang/String;", "getLocal", "()Z", "getRelay", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class PlexResourceConnection {
        private final boolean local;
        private final boolean relay;
        private final String uri;

        public PlexResourceConnection(String str, boolean z, boolean z5) {
            this.uri = str;
            this.local = z;
            this.relay = z5;
        }

        public static /* synthetic */ PlexResourceConnection copy$default(PlexResourceConnection plexResourceConnection, String str, boolean z, boolean z5, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = plexResourceConnection.uri;
            }
            if ((i10 & 2) != 0) {
                z = plexResourceConnection.local;
            }
            if ((i10 & 4) != 0) {
                z5 = plexResourceConnection.relay;
            }
            return plexResourceConnection.copy(str, z, z5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUri() {
            return this.uri;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getLocal() {
            return this.local;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getRelay() {
            return this.relay;
        }

        public final PlexResourceConnection copy(String uri, boolean local, boolean relay) {
            return new PlexResourceConnection(uri, local, relay);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PlexResourceConnection)) {
                return false;
            }
            PlexResourceConnection plexResourceConnection = (PlexResourceConnection) other;
            return kotlin.jvm.internal.p.a(this.uri, plexResourceConnection.uri) && this.local == plexResourceConnection.local && this.relay == plexResourceConnection.relay;
        }

        public final boolean getLocal() {
            return this.local;
        }

        public final boolean getRelay() {
            return this.relay;
        }

        public final String getUri() {
            return this.uri;
        }

        public int hashCode() {
            return (((this.uri.hashCode() * 31) + (this.local ? 1231 : 1237)) * 31) + (this.relay ? 1231 : 1237);
        }

        public String toString() {
            String str = this.uri;
            boolean z = this.local;
            boolean z5 = this.relay;
            StringBuilder sb2 = new StringBuilder("PlexResourceConnection(uri=");
            sb2.append(str);
            sb2.append(", local=");
            sb2.append(z);
            sb2.append(", relay=");
            return a0.c.m(")", z5, sb2);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\tHÆ\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0003JU\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bHÆ\u0001J\u0014\u0010\"\u001a\u00020\t2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010$\u001a\u00020%HÖ\u0081\u0004J\n\u0010&\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0016¨\u0006'"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerRepository$PlexResourceDevice;", "", ContentDisposition.Parameters.Name, "", "product", "provides", "clientIdentifier", "accessToken", "owned", "", "connections", "", "Lcom/arflix/tv/data/repository/HomeServerRepository$PlexResourceConnection;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/util/List;)V", "getName", "()Ljava/lang/String;", "getProduct", "getProvides", "getClientIdentifier", "getAccessToken", "getOwned", "()Z", "getConnections", "()Ljava/util/List;", "isServer", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class PlexResourceDevice {
        private final String accessToken;
        private final String clientIdentifier;
        private final List<PlexResourceConnection> connections;
        private final String name;
        private final boolean owned;
        private final String product;
        private final String provides;

        public PlexResourceDevice(String str, String str2, String str3, String str4, String str5, boolean z, List<PlexResourceConnection> list) {
            this.name = str;
            this.product = str2;
            this.provides = str3;
            this.clientIdentifier = str4;
            this.accessToken = str5;
            this.owned = z;
            this.connections = list;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ PlexResourceDevice copy$default(PlexResourceDevice plexResourceDevice, String str, String str2, String str3, String str4, String str5, boolean z, List list, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = plexResourceDevice.name;
            }
            if ((i10 & 2) != 0) {
                str2 = plexResourceDevice.product;
            }
            if ((i10 & 4) != 0) {
                str3 = plexResourceDevice.provides;
            }
            if ((i10 & 8) != 0) {
                str4 = plexResourceDevice.clientIdentifier;
            }
            if ((i10 & 16) != 0) {
                str5 = plexResourceDevice.accessToken;
            }
            if ((i10 & 32) != 0) {
                z = plexResourceDevice.owned;
            }
            if ((i10 & 64) != 0) {
                list = plexResourceDevice.connections;
            }
            boolean z5 = z;
            List list2 = list;
            String str6 = str5;
            String str7 = str3;
            return plexResourceDevice.copy(str, str2, str7, str4, str6, z5, list2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getProduct() {
            return this.product;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getProvides() {
            return this.provides;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getClientIdentifier() {
            return this.clientIdentifier;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getAccessToken() {
            return this.accessToken;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getOwned() {
            return this.owned;
        }

        public final List<PlexResourceConnection> component7() {
            return this.connections;
        }

        public final PlexResourceDevice copy(String name, String product, String provides, String clientIdentifier, String accessToken, boolean owned, List<PlexResourceConnection> connections) {
            return new PlexResourceDevice(name, product, provides, clientIdentifier, accessToken, owned, connections);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PlexResourceDevice)) {
                return false;
            }
            PlexResourceDevice plexResourceDevice = (PlexResourceDevice) other;
            return kotlin.jvm.internal.p.a(this.name, plexResourceDevice.name) && kotlin.jvm.internal.p.a(this.product, plexResourceDevice.product) && kotlin.jvm.internal.p.a(this.provides, plexResourceDevice.provides) && kotlin.jvm.internal.p.a(this.clientIdentifier, plexResourceDevice.clientIdentifier) && kotlin.jvm.internal.p.a(this.accessToken, plexResourceDevice.accessToken) && this.owned == plexResourceDevice.owned && kotlin.jvm.internal.p.a(this.connections, plexResourceDevice.connections);
        }

        public final String getAccessToken() {
            return this.accessToken;
        }

        public final String getClientIdentifier() {
            return this.clientIdentifier;
        }

        public final List<PlexResourceConnection> getConnections() {
            return this.connections;
        }

        public final String getName() {
            return this.name;
        }

        public final boolean getOwned() {
            return this.owned;
        }

        public final String getProduct() {
            return this.product;
        }

        public final String getProvides() {
            return this.provides;
        }

        public int hashCode() {
            return this.connections.hashCode() + ((androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.name.hashCode() * 31, 31, this.product), 31, this.provides), 31, this.clientIdentifier), 31, this.accessToken) + (this.owned ? 1231 : 1237)) * 31);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0040 A[RETURN] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean isServer() {
            /*
                r5 = this;
                java.lang.String r0 = r5.provides
                r1 = 1
                char[] r2 = new char[r1]
                r3 = 0
                r4 = 44
                r2[r3] = r4
                r4 = 6
                java.util.List r0 = kotlin.text.o.x0(r0, r2, r4)
                boolean r2 = r0.isEmpty()
                java.lang.String r4 = "server"
                if (r2 == 0) goto L18
                goto L37
            L18:
                java.util.Iterator r0 = r0.iterator()
            L1c:
                boolean r2 = r0.hasNext()
                if (r2 == 0) goto L37
                java.lang.Object r2 = r0.next()
                java.lang.String r2 = (java.lang.String) r2
                java.lang.CharSequence r2 = kotlin.text.o.L0(r2)
                java.lang.String r2 = r2.toString()
                boolean r2 = kotlin.text.u.L(r2, r4, r1)
                if (r2 == 0) goto L1c
                goto L3f
            L37:
                java.lang.String r0 = r5.product
                boolean r0 = kotlin.text.o.T(r0, r4, r1)
                if (r0 == 0) goto L40
            L3f:
                return r1
            L40:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.PlexResourceDevice.isServer():boolean");
        }

        public String toString() {
            String str = this.name;
            String str2 = this.product;
            String str3 = this.provides;
            String str4 = this.clientIdentifier;
            String str5 = this.accessToken;
            boolean z = this.owned;
            List<PlexResourceConnection> list = this.connections;
            StringBuilder sbR = a2.r("PlexResourceDevice(name=", str, ", product=", str2, ", provides=");
            y.a.i(sbR, str3, ", clientIdentifier=", str4, ", accessToken=");
            sbR.append(str5);
            sbR.append(", owned=");
            sbR.append(z);
            sbR.append(", connections=");
            return a0.c.q(sbR, list, ")");
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[HomeServerKind.values().length];
            try {
                iArr[HomeServerKind.PLEX.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[HomeServerKind.JELLYFIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[HomeServerKind.EMBY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[HomeServerKind.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[MediaType.values().length];
            try {
                iArr2[MediaType.MOVIE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[MediaType.TV.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[HomeServerLibrarySort.values().length];
            try {
                iArr3[HomeServerLibrarySort.RECENTLY_ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr3[HomeServerLibrarySort.RATING.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr3[HomeServerLibrarySort.TITLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$currentConnection$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository", f = "HomeServerRepository.kt", l = {581}, m = "currentConnection", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HomeServerRepository.this.currentConnection(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$currentConnections$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository", f = "HomeServerRepository.kt", l = {585, 586}, m = "currentConnections", v = 2)
    public static final class C07401 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C07401(d7.d<? super C07401> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HomeServerRepository.this.currentConnections(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$currentConnectionsForProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository", f = "HomeServerRepository.kt", l = {773}, m = "currentConnectionsForProfile", v = 2)
    public static final class C07411 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C07411(d7.d<? super C07411> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HomeServerRepository.this.currentConnectionsForProfile(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$disconnect$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository", f = "HomeServerRepository.kt", l = {417, 418}, m = "disconnect", v = 2)
    public static final class C07421 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C07421(d7.d<? super C07421> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HomeServerRepository.this.disconnect(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$disconnect$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$disconnect$2", f = "HomeServerRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ String $profileId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$profileId = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = HomeServerRepository.this.new AnonymousClass2(this.$profileId, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((AnonymousClass2) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.c(HomeServerRepository.this.connectionKeyFor(this.$profileId));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$exportCloudConnectionsJsonForProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository", f = "HomeServerRepository.kt", l = {758}, m = "exportCloudConnectionsJsonForProfile", v = 2)
    public static final class C07431 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C07431(d7.d<? super C07431> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HomeServerRepository.this.exportCloudConnectionsJsonForProfile(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$findBestSeries$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository", f = "HomeServerRepository.kt", l = {1766}, m = "findBestSeries", v = 2)
    public static final class C07441 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C07441(d7.d<? super C07441> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HomeServerRepository.this.findBestSeries(null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$findBestSeries$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$findBestSeries$2", f = "HomeServerRepository.kt", l = {1777}, m = "invokeSuspend", v = 2)
    public static final class C07452 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ LinkedHashMap<String, HomeServerItem> $candidates;
        final /* synthetic */ HomeServerConnection $connection;
        final /* synthetic */ String $imdbId;
        final /* synthetic */ Integer $tmdbId;
        final /* synthetic */ Integer $tvdbId;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07452(String str, Integer num, Integer num2, HomeServerConnection homeServerConnection, LinkedHashMap<String, HomeServerItem> linkedHashMap, d7.d<? super C07452> dVar) {
            super(2, dVar);
            this.$imdbId = str;
            this.$tmdbId = num;
            this.$tvdbId = num2;
            this.$connection = homeServerConnection;
            this.$candidates = linkedHashMap;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07452 c07452 = HomeServerRepository.this.new C07452(this.$imdbId, this.$tmdbId, this.$tvdbId, this.$connection, this.$candidates, dVar);
            c07452.L$0 = obj;
            return c07452;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ka.k0 k0Var = (ka.k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                List listProviderQueries = HomeServerRepository.this.providerQueries(this.$imdbId, this.$tmdbId, this.$tvdbId);
                HomeServerRepository homeServerRepository = HomeServerRepository.this;
                HomeServerConnection homeServerConnection = this.$connection;
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(listProviderQueries, 10));
                Iterator it = listProviderQueries.iterator();
                while (it.hasNext()) {
                    arrayList.add(ka.m0.e(3, null, k0Var, new HomeServerRepository$findBestSeries$2$1$1(homeServerRepository, homeServerConnection, (String) it.next(), null)));
                }
                this.L$0 = null;
                this.label = 1;
                obj = ka.m0.f(arrayList, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            ArrayList<HomeServerItem> arrayListV = kotlin.collections.s.V((Iterable) obj);
            LinkedHashMap<String, HomeServerItem> linkedHashMap = this.$candidates;
            for (HomeServerItem homeServerItem : arrayListV) {
                linkedHashMap.put(homeServerItem.getId(), homeServerItem);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C07452) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$findMovieMatches$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository", f = "HomeServerRepository.kt", l = {1709}, m = "findMovieMatches", v = 2)
    public static final class C07461 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C07461(d7.d<? super C07461> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HomeServerRepository.this.findMovieMatches(null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$findMovieMatches$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$findMovieMatches$2", f = "HomeServerRepository.kt", l = {1720}, m = "invokeSuspend", v = 2)
    public static final class C07472 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
        final /* synthetic */ LinkedHashMap<String, HomeServerItem> $candidates;
        final /* synthetic */ HomeServerConnection $connection;
        final /* synthetic */ String $imdbId;
        final /* synthetic */ Integer $tmdbId;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07472(String str, Integer num, HomeServerConnection homeServerConnection, LinkedHashMap<String, HomeServerItem> linkedHashMap, d7.d<? super C07472> dVar) {
            super(2, dVar);
            this.$imdbId = str;
            this.$tmdbId = num;
            this.$connection = homeServerConnection;
            this.$candidates = linkedHashMap;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07472 c07472 = HomeServerRepository.this.new C07472(this.$imdbId, this.$tmdbId, this.$connection, this.$candidates, dVar);
            c07472.L$0 = obj;
            return c07472;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ka.k0 k0Var = (ka.k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                List listProviderQueries = HomeServerRepository.this.providerQueries(this.$imdbId, this.$tmdbId, null);
                HomeServerRepository homeServerRepository = HomeServerRepository.this;
                HomeServerConnection homeServerConnection = this.$connection;
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(listProviderQueries, 10));
                Iterator it = listProviderQueries.iterator();
                while (it.hasNext()) {
                    arrayList.add(ka.m0.e(3, null, k0Var, new HomeServerRepository$findMovieMatches$2$1$1(homeServerRepository, homeServerConnection, (String) it.next(), null)));
                }
                this.L$0 = null;
                this.label = 1;
                obj = ka.m0.f(arrayList, this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            ArrayList<HomeServerItem> arrayListV = kotlin.collections.s.V((Iterable) obj);
            LinkedHashMap<String, HomeServerItem> linkedHashMap = this.$candidates;
            for (HomeServerItem homeServerItem : arrayListV) {
                linkedHashMap.put(homeServerItem.getId(), homeServerItem);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
            return ((C07472) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$getCatalogCandidates$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/repository/HomeServerCatalogCandidate;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$getCatalogCandidates$2", f = "HomeServerRepository.kt", l = {593}, m = "invokeSuspend", v = 2)
    public static final class C07482 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends HomeServerCatalogCandidate>>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public C07482(d7.d<? super C07482> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07482 c07482 = HomeServerRepository.this.new C07482(dVar);
            c07482.L$0 = obj;
            return c07482;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object c0Var;
            ka.k0 k0Var = (ka.k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                HomeServerRepository homeServerRepository = HomeServerRepository.this;
                this.L$0 = k0Var;
                this.label = 1;
                obj = homeServerRepository.currentConnections(this);
                e7.a aVar = e7.a.f15033i;
                if (obj == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            ArrayList<HomeServerConnection> arrayList = new ArrayList();
            for (Object obj2 : (Iterable) obj) {
                if (((HomeServerConnection) obj2).isUsable()) {
                    arrayList.add(obj2);
                }
            }
            HomeServerRepository homeServerRepository2 = HomeServerRepository.this;
            ArrayList arrayList2 = new ArrayList();
            for (HomeServerConnection homeServerConnection : arrayList) {
                List<HomeServerCollection> collections = homeServerConnection.getCollections();
                ArrayList arrayList3 = new ArrayList();
                for (Object obj3 : collections) {
                    HomeServerCollection homeServerCollection = (HomeServerCollection) obj3;
                    if (homeServerCollection.getEnabled() && !kotlin.text.o.h0(homeServerCollection.getId())) {
                        arrayList3.add(obj3);
                    }
                }
                ArrayList arrayList4 = new ArrayList(kotlin.collections.s.U(arrayList3, 10));
                Iterator it = arrayList3.iterator();
                while (it.hasNext()) {
                    arrayList4.add(homeServerRepository2.toCatalogCandidate(homeServerConnection, (HomeServerCollection) it.next()));
                }
                try {
                    c0Var = homeServerRepository2.fetchServerCollectionCatalogs(homeServerConnection);
                } catch (Throwable th) {
                    c0Var = new x6.c0(th);
                }
                if (c0Var instanceof x6.c0) {
                    c0Var = kotlin.collections.z.f19728i;
                }
                kotlin.collections.x.b0(arrayList2, kotlin.collections.x.I0(arrayList4, (List) c0Var));
            }
            HashSet hashSet = new HashSet();
            ArrayList arrayList5 = new ArrayList();
            for (Object obj4 : arrayList2) {
                if (hashSet.add(((HomeServerCatalogCandidate) obj4).getSourceRef())) {
                    arrayList5.add(obj4);
                }
            }
            return arrayList5;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<HomeServerCatalogCandidate>> dVar) {
            return ((C07482) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$hasUsableConnections$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository", f = "HomeServerRepository.kt", l = {590}, m = "hasUsableConnections", v = 2)
    public static final class C07491 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public C07491(d7.d<? super C07491> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HomeServerRepository.this.hasUsableConnections(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$importCloudConnectionsJsonForProfile$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$importCloudConnectionsJsonForProfile$2", f = "HomeServerRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C07502 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ String $profileId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07502(String str, d7.d<? super C07502> dVar) {
            super(2, dVar);
            this.$profileId = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07502 c07502 = HomeServerRepository.this.new C07502(this.$profileId, dVar);
            c07502.L$0 = obj;
            return c07502;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C07502) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.c(HomeServerRepository.this.connectionKeyFor(this.$profileId));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$loadCatalogItems$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/HomeServerCatalogPage;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/HomeServerCatalogPage;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$loadCatalogItems$2", f = "HomeServerRepository.kt", l = {620}, m = "invokeSuspend", v = 2)
    public static final class C07512 extends f7.j implements r7.p<ka.k0, d7.d<? super HomeServerCatalogPage>, Object> {
        final /* synthetic */ int $limit;
        final /* synthetic */ MediaType $mediaType;
        final /* synthetic */ int $offset;
        final /* synthetic */ boolean $propagateErrors;
        final /* synthetic */ String $searchQuery;
        final /* synthetic */ HomeServerLibrarySort $sort;
        final /* synthetic */ String $sourceRef;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ HomeServerRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07512(int i10, int i11, String str, HomeServerRepository homeServerRepository, boolean z, HomeServerLibrarySort homeServerLibrarySort, MediaType mediaType, String str2, d7.d<? super C07512> dVar) {
            super(2, dVar);
            this.$limit = i10;
            this.$offset = i11;
            this.$sourceRef = str;
            this.this$0 = homeServerRepository;
            this.$propagateErrors = z;
            this.$sort = homeServerLibrarySort;
            this.$mediaType = mediaType;
            this.$searchQuery = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new C07512(this.$limit, this.$offset, this.$sourceRef, this.this$0, this.$propagateErrors, this.$sort, this.$mediaType, this.$searchQuery, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String str;
            String str2;
            String str3;
            Object c0Var;
            int i10 = this.label;
            Object obj2 = null;
            kotlin.collections.z zVar = kotlin.collections.z.f19728i;
            if (i10 == 0) {
                k2.c.G(obj);
                if (this.$limit <= 0 || this.$offset < 0) {
                    return new HomeServerCatalogPage(zVar, false);
                }
                x6.j0 catalogSourceRef = HomeServerRepository.INSTANCE.parseCatalogSourceRef(this.$sourceRef);
                if (catalogSourceRef == null) {
                    return new HomeServerCatalogPage(zVar, false);
                }
                str = (String) catalogSourceRef.f22587i;
                String str4 = (String) catalogSourceRef.f22588l;
                String str5 = (String) catalogSourceRef.f22589m;
                HomeServerRepository homeServerRepository = this.this$0;
                this.L$0 = null;
                this.L$1 = str;
                this.L$2 = str4;
                this.L$3 = str5;
                this.label = 1;
                Object objCurrentConnections = homeServerRepository.currentConnections(this);
                e7.a aVar = e7.a.f15033i;
                if (objCurrentConnections == aVar) {
                    return aVar;
                }
                str2 = str5;
                str3 = str4;
                obj = objCurrentConnections;
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                String str6 = (String) this.L$3;
                String str7 = (String) this.L$2;
                str = (String) this.L$1;
                k2.c.G(obj);
                str2 = str6;
                str3 = str7;
            }
            Iterator it = ((Iterable) obj).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                HomeServerConnection homeServerConnection = (HomeServerConnection) next;
                if (homeServerConnection.isUsable() && kotlin.jvm.internal.p.a(HomeServerRepository.INSTANCE.catalogServerKey(homeServerConnection), str)) {
                    obj2 = next;
                    break;
                }
            }
            HomeServerConnection homeServerConnection2 = (HomeServerConnection) obj2;
            if (homeServerConnection2 == null) {
                return new HomeServerCatalogPage(zVar, false);
            }
            HomeServerRepository homeServerRepository2 = this.this$0;
            int i11 = this.$offset;
            int i12 = this.$limit;
            HomeServerLibrarySort homeServerLibrarySort = this.$sort;
            MediaType mediaType = this.$mediaType;
            String str8 = this.$searchQuery;
            if (this.$propagateErrors) {
                return homeServerRepository2.loadConnectionCatalogItems(homeServerConnection2, str3, str2, i11, i12, homeServerLibrarySort, mediaType, str8);
            }
            try {
                c0Var = homeServerRepository2.loadConnectionCatalogItems(homeServerConnection2, str3, str2, i11, i12, homeServerLibrarySort, mediaType, str8);
            } catch (Throwable th) {
                c0Var = new x6.c0(th);
            }
            Object homeServerCatalogPage = new HomeServerCatalogPage(zVar, false);
            if (c0Var instanceof x6.c0) {
                c0Var = homeServerCatalogPage;
            }
            return (HomeServerCatalogPage) c0Var;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super HomeServerCatalogPage> dVar) {
            return ((C07512) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$pollJellyfinQuickConnect$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository", f = "HomeServerRepository.kt", l = {576}, m = "pollJellyfinQuickConnect", v = 2)
    public static final class C07521 extends f7.c {
        int I$0;
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
        /* synthetic */ Object result;

        public C07521(d7.d<? super C07521> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HomeServerRepository.this.pollJellyfinQuickConnect(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$resolveEpisodeSources$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$resolveEpisodeSources$2", f = "HomeServerRepository.kt", l = {677, 690}, m = "invokeSuspend", v = 2)
    public static final class C07532 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends StreamSource>>, Object> {
        final /* synthetic */ int $episode;
        final /* synthetic */ String $imdbId;
        final /* synthetic */ int $season;
        final /* synthetic */ String $title;
        final /* synthetic */ Integer $tmdbId;
        final /* synthetic */ Integer $tvdbId;
        Object L$0;
        Object L$1;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$resolveEpisodeSources$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "Lcom/arflix/tv/data/model/StreamSource;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$resolveEpisodeSources$2$1", f = "HomeServerRepository.kt", l = {691}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass1 extends f7.j implements r7.l<d7.d<? super List<? extends StreamSource>>, Object> {
            final /* synthetic */ List<HomeServerConnection> $connections;
            final /* synthetic */ int $episode;
            final /* synthetic */ String $imdbId;
            final /* synthetic */ int $season;
            final /* synthetic */ String $title;
            final /* synthetic */ Integer $tmdbId;
            final /* synthetic */ Integer $tvdbId;
            int label;
            final /* synthetic */ HomeServerRepository this$0;

            /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$resolveEpisodeSources$2$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
            @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$resolveEpisodeSources$2$1$1", f = "HomeServerRepository.kt", l = {718}, m = "invokeSuspend", v = 2)
            public static final class C01301 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends StreamSource>>, Object> {
                final /* synthetic */ List<HomeServerConnection> $connections;
                final /* synthetic */ int $episode;
                final /* synthetic */ String $imdbId;
                final /* synthetic */ int $season;
                final /* synthetic */ String $title;
                final /* synthetic */ Integer $tmdbId;
                final /* synthetic */ Integer $tvdbId;
                private /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ HomeServerRepository this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01301(List<HomeServerConnection> list, HomeServerRepository homeServerRepository, String str, String str2, Integer num, Integer num2, int i10, int i11, d7.d<? super C01301> dVar) {
                    super(2, dVar);
                    this.$connections = list;
                    this.this$0 = homeServerRepository;
                    this.$imdbId = str;
                    this.$title = str2;
                    this.$tmdbId = num;
                    this.$tvdbId = num2;
                    this.$season = i10;
                    this.$episode = i11;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    C01301 c01301 = new C01301(this.$connections, this.this$0, this.$imdbId, this.$title, this.$tmdbId, this.$tvdbId, this.$season, this.$episode, dVar);
                    c01301.L$0 = obj;
                    return c01301;
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    ka.k0 k0Var = (ka.k0) this.L$0;
                    int i10 = this.label;
                    if (i10 == 0) {
                        k2.c.G(obj);
                        List<HomeServerConnection> list = this.$connections;
                        HomeServerRepository homeServerRepository = this.this$0;
                        String str = this.$imdbId;
                        String str2 = this.$title;
                        Integer num = this.$tmdbId;
                        Integer num2 = this.$tvdbId;
                        int i11 = this.$season;
                        int i12 = this.$episode;
                        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
                        Iterator<T> it = list.iterator();
                        while (it.hasNext()) {
                            arrayList.add(ka.m0.e(3, null, k0Var, new HomeServerRepository$resolveEpisodeSources$2$1$1$1$1(homeServerRepository, (HomeServerConnection) it.next(), str, str2, num, num2, i11, i12, null)));
                        }
                        this.L$0 = null;
                        this.label = 1;
                        obj = ka.m0.f(arrayList, this);
                        e7.a aVar = e7.a.f15033i;
                        if (obj == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                    }
                    ArrayList arrayListV = kotlin.collections.s.V((Iterable) obj);
                    HashSet hashSet = new HashSet();
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj2 : arrayListV) {
                        StreamSource streamSource = (StreamSource) obj2;
                        if (hashSet.add(androidx.compose.material3.d.o(streamSource.getAddonId(), "|", streamSource.getSource(), "|", streamSource.getUrl()))) {
                            arrayList2.add(obj2);
                        }
                    }
                    return arrayList2;
                }

                @Override // r7.p
                public final Object invoke(ka.k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
                    return ((C01301) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(List<HomeServerConnection> list, HomeServerRepository homeServerRepository, String str, String str2, Integer num, Integer num2, int i10, int i11, d7.d<? super AnonymousClass1> dVar) {
                super(1, dVar);
                this.$connections = list;
                this.this$0 = homeServerRepository;
                this.$imdbId = str;
                this.$title = str2;
                this.$tmdbId = num;
                this.$tvdbId = num2;
                this.$season = i10;
                this.$episode = i11;
            }

            @Override // f7.a
            public final d7.d<t0> create(d7.d<?> dVar) {
                return new AnonymousClass1(this.$connections, this.this$0, this.$imdbId, this.$title, this.$tmdbId, this.$tvdbId, this.$season, this.$episode, dVar);
            }

            @Override // r7.l
            public final Object invoke(d7.d<? super List<StreamSource>> dVar) {
                return ((AnonymousClass1) create(dVar)).invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 != 0) {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    return obj;
                }
                k2.c.G(obj);
                C01301 c01301 = new C01301(this.$connections, this.this$0, this.$imdbId, this.$title, this.$tmdbId, this.$tvdbId, this.$season, this.$episode, null);
                this.label = 1;
                Object objC = ka.l0.c(c01301, this);
                e7.a aVar = e7.a.f15033i;
                return objC == aVar ? aVar : objC;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07532(String str, String str2, Integer num, Integer num2, int i10, int i11, d7.d<? super C07532> dVar) {
            super(2, dVar);
            this.$title = str;
            this.$imdbId = str2;
            this.$tmdbId = num;
            this.$tvdbId = num2;
            this.$season = i10;
            this.$episode = i11;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return HomeServerRepository.this.new C07532(this.$title, this.$imdbId, this.$tmdbId, this.$tvdbId, this.$season, this.$episode, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            e7.a aVar = e7.a.f15033i;
            if (i10 == 0) {
                k2.c.G(obj);
                HomeServerRepository homeServerRepository = HomeServerRepository.this;
                this.label = 1;
                obj = homeServerRepository.currentConnections(this);
                if (obj != aVar) {
                }
            }
            if (i10 != 1) {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : (Iterable) obj) {
                if (((HomeServerConnection) obj2).isUsable()) {
                    arrayList.add(obj2);
                }
            }
            if (arrayList.isEmpty()) {
                return kotlin.collections.z.f19728i;
            }
            String strSourceCacheKey = HomeServerRepository.this.sourceCacheKey("episode", arrayList, this.$title, null, this.$imdbId, this.$tmdbId, this.$tvdbId, new Integer(this.$season), new Integer(this.$episode));
            HomeServerRepository homeServerRepository2 = HomeServerRepository.this;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(arrayList, homeServerRepository2, this.$imdbId, this.$title, this.$tmdbId, this.$tvdbId, this.$season, this.$episode, null);
            this.L$0 = null;
            this.L$1 = null;
            this.label = 2;
            Object objResolveSourcesCached = homeServerRepository2.resolveSourcesCached(strSourceCacheKey, anonymousClass1, this);
            return objResolveSourcesCached == aVar ? aVar : objResolveSourcesCached;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
            return ((C07532) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$resolveMovieSources$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$resolveMovieSources$2", f = "HomeServerRepository.kt", l = {636, 649}, m = "invokeSuspend", v = 2)
    public static final class C07542 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends StreamSource>>, Object> {
        final /* synthetic */ String $imdbId;
        final /* synthetic */ String $title;
        final /* synthetic */ Integer $tmdbId;
        final /* synthetic */ Integer $year;
        Object L$0;
        Object L$1;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$resolveMovieSources$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "Lcom/arflix/tv/data/model/StreamSource;"}, k = 3, mv = {2, 3, 0}, xi = 48)
        @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$resolveMovieSources$2$1", f = "HomeServerRepository.kt", l = {650}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass1 extends f7.j implements r7.l<d7.d<? super List<? extends StreamSource>>, Object> {
            final /* synthetic */ List<HomeServerConnection> $connections;
            final /* synthetic */ String $imdbId;
            final /* synthetic */ String $title;
            final /* synthetic */ Integer $tmdbId;
            final /* synthetic */ Integer $year;
            int label;
            final /* synthetic */ HomeServerRepository this$0;

            /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$resolveMovieSources$2$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/StreamSource;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
            @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$resolveMovieSources$2$1$1", f = "HomeServerRepository.kt", l = {662}, m = "invokeSuspend", v = 2)
            public static final class C01311 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends StreamSource>>, Object> {
                final /* synthetic */ List<HomeServerConnection> $connections;
                final /* synthetic */ String $imdbId;
                final /* synthetic */ String $title;
                final /* synthetic */ Integer $tmdbId;
                final /* synthetic */ Integer $year;
                private /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ HomeServerRepository this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01311(List<HomeServerConnection> list, HomeServerRepository homeServerRepository, String str, String str2, Integer num, Integer num2, d7.d<? super C01311> dVar) {
                    super(2, dVar);
                    this.$connections = list;
                    this.this$0 = homeServerRepository;
                    this.$imdbId = str;
                    this.$title = str2;
                    this.$year = num;
                    this.$tmdbId = num2;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    C01311 c01311 = new C01311(this.$connections, this.this$0, this.$imdbId, this.$title, this.$year, this.$tmdbId, dVar);
                    c01311.L$0 = obj;
                    return c01311;
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    ka.k0 k0Var = (ka.k0) this.L$0;
                    int i10 = this.label;
                    if (i10 == 0) {
                        k2.c.G(obj);
                        List<HomeServerConnection> list = this.$connections;
                        HomeServerRepository homeServerRepository = this.this$0;
                        String str = this.$imdbId;
                        String str2 = this.$title;
                        Integer num = this.$year;
                        Integer num2 = this.$tmdbId;
                        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
                        Iterator<T> it = list.iterator();
                        while (it.hasNext()) {
                            arrayList.add(ka.m0.e(3, null, k0Var, new HomeServerRepository$resolveMovieSources$2$1$1$1$1(homeServerRepository, (HomeServerConnection) it.next(), str, str2, num, num2, null)));
                        }
                        this.L$0 = null;
                        this.label = 1;
                        obj = ka.m0.f(arrayList, this);
                        e7.a aVar = e7.a.f15033i;
                        if (obj == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                    }
                    ArrayList arrayListV = kotlin.collections.s.V((Iterable) obj);
                    HashSet hashSet = new HashSet();
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj2 : arrayListV) {
                        StreamSource streamSource = (StreamSource) obj2;
                        if (hashSet.add(androidx.compose.material3.d.o(streamSource.getAddonId(), "|", streamSource.getSource(), "|", streamSource.getUrl()))) {
                            arrayList2.add(obj2);
                        }
                    }
                    return arrayList2;
                }

                @Override // r7.p
                public final Object invoke(ka.k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
                    return ((C01311) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(List<HomeServerConnection> list, HomeServerRepository homeServerRepository, String str, String str2, Integer num, Integer num2, d7.d<? super AnonymousClass1> dVar) {
                super(1, dVar);
                this.$connections = list;
                this.this$0 = homeServerRepository;
                this.$imdbId = str;
                this.$title = str2;
                this.$year = num;
                this.$tmdbId = num2;
            }

            @Override // f7.a
            public final d7.d<t0> create(d7.d<?> dVar) {
                return new AnonymousClass1(this.$connections, this.this$0, this.$imdbId, this.$title, this.$year, this.$tmdbId, dVar);
            }

            @Override // r7.l
            public final Object invoke(d7.d<? super List<StreamSource>> dVar) {
                return ((AnonymousClass1) create(dVar)).invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 != 0) {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    return obj;
                }
                k2.c.G(obj);
                C01311 c01311 = new C01311(this.$connections, this.this$0, this.$imdbId, this.$title, this.$year, this.$tmdbId, null);
                this.label = 1;
                Object objC = ka.l0.c(c01311, this);
                e7.a aVar = e7.a.f15033i;
                return objC == aVar ? aVar : objC;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07542(String str, Integer num, String str2, Integer num2, d7.d<? super C07542> dVar) {
            super(2, dVar);
            this.$title = str;
            this.$year = num;
            this.$imdbId = str2;
            this.$tmdbId = num2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return HomeServerRepository.this.new C07542(this.$title, this.$year, this.$imdbId, this.$tmdbId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            e7.a aVar = e7.a.f15033i;
            if (i10 == 0) {
                k2.c.G(obj);
                HomeServerRepository homeServerRepository = HomeServerRepository.this;
                this.label = 1;
                obj = homeServerRepository.currentConnections(this);
                if (obj != aVar) {
                }
            }
            if (i10 != 1) {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : (Iterable) obj) {
                if (((HomeServerConnection) obj2).isUsable()) {
                    arrayList.add(obj2);
                }
            }
            if (arrayList.isEmpty()) {
                return kotlin.collections.z.f19728i;
            }
            String strSourceCacheKey = HomeServerRepository.this.sourceCacheKey("movie", arrayList, this.$title, this.$year, this.$imdbId, this.$tmdbId, null, null, null);
            HomeServerRepository homeServerRepository2 = HomeServerRepository.this;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(arrayList, homeServerRepository2, this.$imdbId, this.$title, this.$year, this.$tmdbId, null);
            this.L$0 = null;
            this.L$1 = null;
            this.label = 2;
            Object objResolveSourcesCached = homeServerRepository2.resolveSourcesCached(strSourceCacheKey, anonymousClass1, this);
            return objResolveSourcesCached == aVar ? aVar : objResolveSourcesCached;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<StreamSource>> dVar) {
            return ((C07542) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$saveConnection$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository", f = "HomeServerRepository.kt", l = {727, 729}, m = "saveConnection", v = 2)
    public static final class C07551 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C07551(d7.d<? super C07551> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HomeServerRepository.this.saveConnection(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$saveConnections$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository", f = "HomeServerRepository.kt", l = {742, 742}, m = "saveConnections", v = 2)
    public static final class C07561 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C07561(d7.d<? super C07561> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HomeServerRepository.this.saveConnections(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$saveConnectionsForProfile$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$saveConnectionsForProfile$2", f = "HomeServerRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C07572 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ List<HomeServerConnection> $connections;
        final /* synthetic */ String $profileId;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07572(String str, List<HomeServerConnection> list, d7.d<? super C07572> dVar) {
            super(2, dVar);
            this.$profileId = str;
            this.$connections = list;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07572 c07572 = HomeServerRepository.this.new C07572(this.$profileId, this.$connections, dVar);
            c07572.L$0 = obj;
            return c07572;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C07572) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            c1.e eVarConnectionKeyFor = HomeServerRepository.this.connectionKeyFor(this.$profileId);
            Gson gson = HomeServerRepository.this.gson;
            List<HomeServerConnection> list = this.$connections;
            HomeServerRepository homeServerRepository = HomeServerRepository.this;
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(homeServerRepository.encryptedForStorage(homeServerRepository.sanitized((HomeServerConnection) it.next())));
            }
            bVar.d(eVarConnectionKeyFor, gson.toJson(new HomeServerProfileConfig(arrayList)));
            return t0.f22605a;
        }
    }

    @Inject
    public HomeServerRepository(@ApplicationContext Context context, gb.h0 h0Var, ProfileManager profileManager) {
        this.context = context;
        this.okHttpClient = h0Var;
        this.profileManager = profileManager;
        Pattern pattern = gb.d0.f15633d;
        this.jsonMediaType = xc.d.i0("application/json; charset=utf-8");
        this.sourceCacheLock = new Object();
        this.sourceRequestScope = ka.l0.a(d7.h.a(ka.m0.d(), x0.f19655d));
        this.sourceCache = new HomeServerRepository$sourceCache$1();
        this.sourceRequests = new LinkedHashMap();
        final na.j<List<HomeServerConnection>> jVarH = y0.h(new na.n0(profileManager.getActiveProfileId(), DataStoresKt.getSettingsDataStore(context).getData(), new HomeServerRepository$connections$1(this, null)));
        this.connections = jVarH;
        this.connection = y0.h(new na.j<HomeServerConnection>() { // from class: com.arflix.tv.data.repository.HomeServerRepository$special$$inlined$map$1

            /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$special$$inlined$map$1$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u00012\u0006\u0010\u0002\u001a\u00028\u0000H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "R", "value", "Lx6/t0;", "emit", "(Ljava/lang/Object;Ld7/d;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {2, 3, 0})
            public static final class AnonymousClass2<T> implements na.k {
                final /* synthetic */ na.k $this_unsafeFlow;

                /* JADX INFO: renamed from: com.arflix.tv.data.repository.HomeServerRepository$special$$inlined$map$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
                @f7.e(c = "com.arflix.tv.data.repository.HomeServerRepository$special$$inlined$map$1$2", f = "HomeServerRepository.kt", l = {223}, m = "emit", v = 2)
                public static final class AnonymousClass1 extends f7.c {
                    int I$0;
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(d7.d dVar) {
                        super(dVar);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(na.k kVar) {
                    this.$this_unsafeFlow = kVar;
                }

                /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
                @Override // na.k
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, d7.d r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.arflix.tv.data.repository.HomeServerRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.arflix.tv.data.repository.HomeServerRepository$special$$inlined$map$1$2$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.arflix.tv.data.repository.HomeServerRepository$special$$inlined$map$1$2$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        int r1 = r0.label
                        r2 = 1
                        if (r1 == 0) goto L35
                        if (r1 != r2) goto L2d
                        java.lang.Object r5 = r0.L$3
                        na.k r5 = (na.k) r5
                        java.lang.Object r5 = r0.L$1
                        com.arflix.tv.data.repository.HomeServerRepository$special$$inlined$map$1$2$1 r5 = (com.arflix.tv.data.repository.HomeServerRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r5
                        k2.c.G(r6)
                        goto L57
                    L2d:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L35:
                        k2.c.G(r6)
                        na.k r6 = r4.$this_unsafeFlow
                        java.util.List r5 = (java.util.List) r5
                        java.lang.Object r5 = kotlin.collections.x.o0(r5)
                        r1 = 0
                        r0.L$0 = r1
                        r0.L$1 = r1
                        r0.L$2 = r1
                        r0.L$3 = r1
                        r1 = 0
                        r0.I$0 = r1
                        r0.label = r2
                        java.lang.Object r5 = r6.emit(r5, r0)
                        e7.a r6 = e7.a.f15033i
                        if (r5 != r6) goto L57
                        return r6
                    L57:
                        x6.t0 r5 = x6.t0.f22605a
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, d7.d):java.lang.Object");
                }
            }

            @Override // na.j
            public Object collect(na.k kVar, d7.d dVar) {
                Object objCollect = jVarH.collect(new AnonymousClass2(kVar), dVar);
                return objCollect == e7.a.f15033i ? objCollect : t0.f22605a;
            }
        });
    }

    private final String absoluteUrl(String baseUrl, String pathOrUrl) {
        if (!kotlin.text.u.P(pathOrUrl, "http://", true) && !kotlin.text.u.P(pathOrUrl, "https://", true)) {
            gb.b0 b0VarD = null;
            try {
                androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
                kVar.i(null, baseUrl);
                b0VarD = kVar.d();
            } catch (IllegalArgumentException unused) {
            }
            if (b0VarD != null) {
                androidx.tv.foundation.lazy.list.k kVarF = b0VarD.f();
                List listX0 = kotlin.text.o.x0(kotlin.text.o.M0(kotlin.text.o.G0('?', pathOrUrl, pathOrUrl), '/'), new char[]{'/'}, 6);
                ArrayList arrayList = new ArrayList();
                for (Object obj : listX0) {
                    if (!kotlin.text.o.h0((String) obj)) {
                        arrayList.add(obj);
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    kVarF.b((String) it.next());
                }
                String strC0 = kotlin.text.o.C0('?', pathOrUrl, "");
                if (!kotlin.text.o.h0(strC0)) {
                    for (String str : kotlin.text.o.x0(strC0, new char[]{'&'}, 6)) {
                        String strG0 = kotlin.text.o.G0('=', str, str);
                        String strC02 = kotlin.text.o.C0('=', str, "");
                        if (!kotlin.text.o.h0(strG0)) {
                            kVarF.a(strG0, strC02);
                        }
                    }
                }
                return kVarF.d().f15625i;
            }
        }
        return pathOrUrl;
    }

    private final List<JsonElement> array(JsonObject jsonObject, String str) {
        JsonArray asJsonArray;
        JsonElement jsonElement = jsonObject.get(str);
        List<JsonElement> listC1 = null;
        if (jsonElement != null) {
            if (!jsonElement.isJsonArray()) {
                jsonElement = null;
            }
            if (jsonElement != null && (asJsonArray = jsonElement.getAsJsonArray()) != null) {
                listC1 = kotlin.collections.x.c1(asJsonArray);
            }
        }
        return listC1 == null ? kotlin.collections.z.f19728i : listC1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JsonObject asJsonObjectOrNull(JsonElement jsonElement) {
        if (!jsonElement.isJsonObject()) {
            jsonElement = null;
        }
        if (jsonElement != null) {
            return jsonElement.getAsJsonObject();
        }
        return null;
    }

    private final String asStringOrNull(JsonElement jsonElement) throws Exception {
        try {
            if (jsonElement.isJsonNull()) {
                jsonElement = null;
            }
            if (jsonElement != null) {
                return jsonElement.getAsString();
            }
            return null;
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
            return null;
        }
    }

    private final String authHeader(String token) {
        String strL = a0.c.l("MediaBrowser Client=\"ARVIO\", Device=\"Android\", DeviceId=\"", deviceId(), "\", Version=\"1.9.995\"");
        if (token == null || kotlin.text.o.h0(token)) {
            return strL;
        }
        return strL + ", Token=\"" + token + "\"";
    }

    public static /* synthetic */ String authHeader$default(HomeServerRepository homeServerRepository, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        return homeServerRepository.authHeader(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AuthResponse authenticate(String serverUrl, String username, String password) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Username", username);
        jsonObject.addProperty("Pw", password);
        jsonObject.addProperty("Password", password);
        JsonObject jsonObjectPostJson$default = postJson$default(this, buildUrl$default(this, serverUrl, "/Users/AuthenticateByName", null, 4, null), jsonObject, null, 4, null);
        JsonObject jsonObjectObj = obj(jsonObjectPostJson$default, "User");
        String strString = string(jsonObjectPostJson$default, "AccessToken");
        String strString2 = string(jsonObjectPostJson$default, "ServerId");
        String strString3 = jsonObjectObj != null ? string(jsonObjectObj, "ServerName") : null;
        if (strString3 == null) {
            strString3 = "";
        }
        String strString4 = jsonObjectObj != null ? string(jsonObjectObj, "Id") : null;
        if (strString4 == null) {
            strString4 = "";
        }
        String strString5 = jsonObjectObj != null ? string(jsonObjectObj, "Name") : null;
        AuthResponse authResponse = new AuthResponse(strString, strString2, strString3, strString4, strString5 == null ? "" : strString5, null, 32, null);
        if (kotlin.text.o.h0(authResponse.getAccessToken()) || kotlin.text.o.h0(authResponse.getUserId())) {
            throw new IllegalArgumentException(this.context.getString(R.string.homeserver_no_playable_account).toString());
        }
        return authResponse;
    }

    private final AuthResponse authenticateWithQuickConnect(String serverUrl, String secret) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Secret", secret);
        JsonObject jsonObjectPostJson$default = postJson$default(this, buildUrl$default(this, serverUrl, "/Users/AuthenticateWithQuickConnect", null, 4, null), jsonObject, null, 4, null);
        JsonObject jsonObjectObj = obj(jsonObjectPostJson$default, "User");
        String strString = string(jsonObjectPostJson$default, "AccessToken");
        String strString2 = string(jsonObjectPostJson$default, "ServerId");
        String strString3 = jsonObjectObj != null ? string(jsonObjectObj, "ServerName") : null;
        if (strString3 == null) {
            strString3 = "";
        }
        String strString4 = jsonObjectObj != null ? string(jsonObjectObj, "Id") : null;
        if (strString4 == null) {
            strString4 = "";
        }
        String strString5 = jsonObjectObj != null ? string(jsonObjectObj, "Name") : null;
        AuthResponse authResponse = new AuthResponse(strString, strString2, strString3, strString4, strString5 != null ? strString5 : "", null, 32, null);
        if (kotlin.text.o.h0(authResponse.getAccessToken()) || kotlin.text.o.h0(authResponse.getUserId())) {
            throw new IllegalArgumentException(this.context.getString(R.string.homeserver_no_playable_account).toString());
        }
        return authResponse;
    }

    private final HomeServerItem bestCandidate(Collection<HomeServerItem> candidates, String title, Integer year, String imdbId, Integer tmdbId, Integer tvdbId) {
        Object next;
        Collection<HomeServerItem> collection = candidates;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(collection, 10));
        for (HomeServerItem homeServerItem : collection) {
            String str = title;
            Integer num = year;
            arrayList.add(new x6.x(homeServerItem, Integer.valueOf(HomeServerMatcher.INSTANCE.score(str, num, imdbId, tmdbId, tvdbId, homeServerItem.info()))));
            title = str;
            year = num;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (HomeServerMatcher.INSTANCE.isAcceptable(((Number) ((x6.x) obj).f22609l).intValue())) {
                arrayList2.add(obj);
            }
        }
        Iterator it = arrayList2.iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                int iIntValue = ((Number) ((x6.x) next).f22609l).intValue();
                do {
                    Object next2 = it.next();
                    int iIntValue2 = ((Number) ((x6.x) next2).f22609l).intValue();
                    if (iIntValue < iIntValue2) {
                        next = next2;
                        iIntValue = iIntValue2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        x6.x xVar = (x6.x) next;
        if (xVar != null) {
            return (HomeServerItem) xVar.f22608i;
        }
        return null;
    }

    /* JADX INFO: renamed from: boolean, reason: not valid java name */
    private final Boolean m6126boolean(JsonObject jsonObject, String str) {
        String strAsStringOrNull;
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement != null && (strAsStringOrNull = asStringOrNull(jsonElement)) != null) {
            if (strAsStringOrNull.equals("true")) {
                return Boolean.TRUE;
            }
            if (strAsStringOrNull.equals("false")) {
                return Boolean.FALSE;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0105 A[PHI: r0
      0x0105: PHI (r0v65 java.lang.String) = (r0v25 java.lang.String), (r0v67 java.lang.String) binds: [B:84:0x0114, B:78:0x0103] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0121  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.arflix.tv.data.repository.HomeServerConnection buildPlexConnection(java.lang.String r60, java.lang.String r61, java.lang.String r62, com.arflix.tv.data.repository.HomeServerRepository.ServerInfo r63, java.lang.String r64) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 714
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.buildPlexConnection(java.lang.String, java.lang.String, java.lang.String, com.arflix.tv.data.repository.HomeServerRepository$ServerInfo, java.lang.String):com.arflix.tv.data.repository.HomeServerConnection");
    }

    public static /* synthetic */ HomeServerConnection buildPlexConnection$default(HomeServerRepository homeServerRepository, String str, String str2, String str3, ServerInfo serverInfo, String str4, int i10, Object obj) {
        if ((i10 & 16) != 0) {
            str4 = "";
        }
        return homeServerRepository.buildPlexConnection(str, str2, str3, serverInfo, str4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<StreamSource> buildStreamSources(HomeServerConnection connection, HomeServerItem item) {
        Object c0Var;
        ArrayList<HomeServerMediaSource> arrayList;
        List listSingletonList;
        Object c0Var2;
        HomeServerConnection homeServerConnection = connection;
        HomeServerKind serverKind = homeServerConnection.getServerKind();
        HomeServerKind homeServerKind = HomeServerKind.PLEX;
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        if (serverKind == homeServerKind) {
            try {
                HomeServerItem homeServerItem = (HomeServerItem) kotlin.collections.x.o0(metadataItems(getJson(buildUrl(homeServerConnection.getServerUrl(), "/library/metadata/" + item.getId(), kotlin.collections.h0.t0(new x6.x("includeGuids", "1"), new x6.x("includeMedia", "1"))), homeServerConnection), homeServerConnection.getServerKind()));
                c0Var = homeServerItem != null ? homeServerItem.getMediaSources() : null;
                if (c0Var == null) {
                    c0Var = zVar;
                }
            } catch (Throwable th) {
                c0Var = new x6.c0(th);
            }
            if (c0Var instanceof x6.c0) {
                c0Var = zVar;
            }
            ArrayList arrayListI0 = kotlin.collections.x.I0((List) c0Var, item.getMediaSources());
            HashSet hashSet = new HashSet();
            arrayList = new ArrayList();
            for (Object obj : arrayListI0) {
                if (hashSet.add(identityKey((HomeServerMediaSource) obj))) {
                    arrayList.add(obj);
                }
            }
        } else {
            try {
                c0Var2 = mediaSources(postJson(buildUrl(homeServerConnection.getServerUrl(), "/Items/" + item.getId() + "/PlaybackInfo", kotlin.collections.h0.t0(new x6.x("UserId", homeServerConnection.getUserId()), new x6.x("StartTimeTicks", "0"), new x6.x("IsPlayback", "true"), new x6.x("AutoOpenLiveStream", "true"), new x6.x("MaxStreamingBitrate", "2147483647"))), new JsonObject(), homeServerConnection));
            } catch (Throwable th2) {
                c0Var2 = new x6.c0(th2);
            }
            if (c0Var2 instanceof x6.c0) {
                c0Var2 = zVar;
            }
            ArrayList arrayListI02 = kotlin.collections.x.I0((List) c0Var2, item.getMediaSources());
            HashSet hashSet2 = new HashSet();
            arrayList = new ArrayList();
            for (Object obj2 : arrayListI02) {
                if (hashSet2.add(identityKey((HomeServerMediaSource) obj2))) {
                    arrayList.add(obj2);
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (HomeServerMediaSource homeServerMediaSource : arrayList) {
            String strPlaybackUrl = playbackUrl(homeServerMediaSource, homeServerConnection, item.getId());
            if (strPlaybackUrl == null) {
                listSingletonList = zVar;
            } else {
                StreamSource streamSource$default = toStreamSource$default(this, homeServerMediaSource, homeServerConnection, item, strPlaybackUrl, null, 8, null);
                if (homeServerConnection.getServerKind() == HomeServerKind.PLEX && needsPlexCompatiblePlayback(homeServerMediaSource)) {
                    String strPlexCompatiblePlaybackUrl = plexCompatiblePlaybackUrl(homeServerMediaSource, homeServerConnection, item.getId());
                    listSingletonList = strPlexCompatiblePlaybackUrl == null ? Collections.singletonList(streamSource$default) : t7.a.E(toStreamSource(homeServerMediaSource, homeServerConnection, item, strPlexCompatiblePlaybackUrl, "Compatible"), streamSource$default);
                } else {
                    listSingletonList = Collections.singletonList(streamSource$default);
                }
            }
            kotlin.collections.x.b0(arrayList2, listSingletonList);
            homeServerConnection = connection;
        }
        HashSet hashSet3 = new HashSet();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj3 : arrayList2) {
            StreamSource streamSource = (StreamSource) obj3;
            String addonId = streamSource.getAddonId();
            String url = streamSource.getUrl();
            String string = url != null ? kotlin.text.o.L0(url).toString() : null;
            if (string == null) {
                string = "";
            }
            if (hashSet3.add(androidx.compose.material3.d.o(addonId, "|", string, "|", streamSource.getSource()))) {
                arrayList3.add(obj3);
            }
        }
        final Comparator comparator = new Comparator() { // from class: com.arflix.tv.data.repository.HomeServerRepository$buildStreamSources$$inlined$compareByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e(Integer.valueOf(this.this$0.qualityRank(((StreamSource) t10).getQuality())), Integer.valueOf(this.this$0.qualityRank(((StreamSource) t2).getQuality())));
            }
        };
        return kotlin.collections.x.W0(arrayList3, new Comparator() { // from class: com.arflix.tv.data.repository.HomeServerRepository$buildStreamSources$$inlined$thenByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator.compare(t2, t10);
                if (iCompare != 0) {
                    return iCompare;
                }
                Long sizeBytes = ((StreamSource) t10).getSizeBytes();
                if (sizeBytes == null) {
                    sizeBytes = l;
                }
                Long sizeBytes2 = ((StreamSource) t2).getSizeBytes();
                return r0.e(sizeBytes, sizeBytes2 != null ? sizeBytes2 : 0L);
            }
        });
    }

    private final String buildUrl(String baseUrl, String path, Map<String, String> query) {
        gb.b0 b0VarD = null;
        try {
            androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
            kVar.i(null, baseUrl);
            b0VarD = kVar.d();
        } catch (IllegalArgumentException unused) {
        }
        if (b0VarD == null) {
            throw new IllegalStateException(this.context.getString(R.string.homeserver_invalid_url).toString());
        }
        androidx.tv.foundation.lazy.list.k kVarF = b0VarD.f();
        List listX0 = kotlin.text.o.x0(kotlin.text.o.M0(path, '/'), new char[]{'/'}, 6);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listX0) {
            if (!kotlin.text.o.h0((String) obj)) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            kVarF.b((String) it.next());
        }
        for (Map.Entry<String, String> entry : query.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value != null && !kotlin.text.o.h0(value)) {
                kVarF.c(key, value);
            }
        }
        return kVarF.d().f15625i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ String buildUrl$default(HomeServerRepository homeServerRepository, String str, String str2, Map map, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            map = kotlin.collections.a0.f19683i;
        }
        return homeServerRepository.buildUrl(str, str2, map);
    }

    private final String catalogItemFields() {
        return "ProviderIds,ProductionYear,Overview,CommunityRating,ImageTags,BackdropImageTags,DateCreated";
    }

    private final void clearSourceCache() {
        List listC1;
        synchronized (this.sourceCacheLock) {
            this.sourceCache.clear();
            listC1 = kotlin.collections.x.c1(this.sourceRequests.values());
            this.sourceRequests.clear();
        }
        Iterator it = listC1.iterator();
        while (it.hasNext()) {
            ((ka.p0) it.next()).cancel((CancellationException) null);
        }
    }

    /* JADX INFO: renamed from: connect-yxL6bBk$default, reason: not valid java name */
    public static /* synthetic */ Object m6127connectyxL6bBk$default(HomeServerRepository homeServerRepository, String str, String str2, String str3, String str4, d7.d dVar, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            str4 = "";
        }
        return homeServerRepository.m6132connectyxL6bBk(str, str2, str3, str4, dVar);
    }

    /* JADX INFO: renamed from: connectPlexAccount-BWLJW6A$default, reason: not valid java name */
    public static /* synthetic */ Object m6128connectPlexAccountBWLJW6A$default(HomeServerRepository homeServerRepository, String str, String str2, String str3, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = "";
        }
        if ((i10 & 4) != 0) {
            str3 = "";
        }
        return homeServerRepository.m6133connectPlexAccountBWLJW6A(str, str2, str3, dVar);
    }

    private final String connectionIdentity(HomeServerConnection connection) {
        String connectionId = connection.getConnectionId();
        if (!kotlin.text.o.h0(connectionId)) {
            return connectionId;
        }
        String serverUrl = connection.getServerUrl();
        HomeServerKind serverKind = connection.getServerKind();
        String userId = connection.getUserId();
        if (kotlin.text.o.h0(userId)) {
            userId = connection.getUserName();
        }
        return createConnectionId(serverUrl, serverKind, userId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e connectionKeyFor(String profileId) {
        return this.profileManager.profileStringKeyFor(profileId, CONNECTION_KEY_NAME);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String createConnectionId(String serverUrl, HomeServerKind kind, String userIdentity) {
        String strName = kind.name();
        String strO0 = kotlin.text.o.O0(serverUrl, '/');
        Locale locale = Locale.US;
        return HomeServerRegexes.INSTANCE.getCONNECTION_ID_SANITIZER_REGEX().f("_", androidx.compose.material3.d.o(strName, ":", strO0.toLowerCase(locale), ":", userIdentity.toLowerCase(locale)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object currentConnectionsForProfile(java.lang.String r5, d7.d<? super java.util.List<com.arflix.tv.data.repository.HomeServerConnection>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.HomeServerRepository.C07411
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.HomeServerRepository$currentConnectionsForProfile$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository.C07411) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$currentConnectionsForProfile$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$currentConnectionsForProfile$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            k2.c.G(r6)
            goto L4b
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r6)
            android.content.Context r6 = r4.context
            z0.g r6 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r6)
            na.j r6 = r6.getData()
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r6 = na.y0.l(r6, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L4b
            return r0
        L4b:
            c1.g r6 = (c1.g) r6
            c1.e r5 = r4.connectionKeyFor(r5)
            c1.b r6 = (c1.b) r6
            java.util.LinkedHashMap r6 = r6.f7368a
            java.lang.Object r5 = r6.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            java.util.List r5 = r4.parseConnections(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.currentConnectionsForProfile(java.lang.String, d7.d):java.lang.Object");
    }

    private final HomeServerConnection decryptedForUse(HomeServerConnection homeServerConnection) {
        SecureStorage secureStorage = SecureStorage.INSTANCE;
        String accessToken = homeServerConnection.getAccessToken();
        if (accessToken == null) {
            accessToken = "";
        }
        String strDecrypt = secureStorage.decrypt(accessToken, HOME_SERVER_SECRET_ALIAS);
        String str = strDecrypt == null ? "" : strDecrypt;
        String accountToken = homeServerConnection.getAccountToken();
        if (accountToken == null) {
            accountToken = "";
        }
        String strDecrypt2 = secureStorage.decrypt(accountToken, HOME_SERVER_SECRET_ALIAS);
        return homeServerConnection.copy((PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 1) != 0 ? homeServerConnection.enabled : false, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 2) != 0 ? homeServerConnection.connectionId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 4) != 0 ? homeServerConnection.serverUrl : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 8) != 0 ? homeServerConnection.displayName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 16) != 0 ? homeServerConnection.serverName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 32) != 0 ? homeServerConnection.serverKind : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 64) != 0 ? homeServerConnection.serverId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 128) != 0 ? homeServerConnection.userId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 256) != 0 ? homeServerConnection.userName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 512) != 0 ? homeServerConnection.accessToken : str, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 1024) != 0 ? homeServerConnection.accountToken : strDecrypt2 == null ? "" : strDecrypt2, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 2048) != 0 ? homeServerConnection.collections : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 4096) != 0 ? homeServerConnection.lastConnectedAt : 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HomeServerKind detectServerKind(String productName, String serverName) {
        String lowerCase = androidx.compose.foundation.c.t(productName, " ", serverName).toLowerCase(Locale.US);
        return kotlin.text.o.T(lowerCase, "plex", false) ? HomeServerKind.PLEX : kotlin.text.o.T(lowerCase, "emby", false) ? HomeServerKind.EMBY : kotlin.text.o.T(lowerCase, "jellyfin", false) ? HomeServerKind.JELLYFIN : HomeServerKind.UNKNOWN;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String deviceId() {
        String string = Settings.Secure.getString(this.context.getContentResolver(), "android_id");
        if (string == null) {
            return "arvio-android";
        }
        if (kotlin.text.o.h0(string)) {
            string = null;
        }
        return string != null ? string : "arvio-android";
    }

    private final String displayLabel(HomeServerConnection homeServerConnection) {
        String displayName = homeServerConnection.getDisplayName();
        if (kotlin.text.o.h0(displayName)) {
            displayName = homeServerConnection.getServerName();
        }
        if (kotlin.text.o.h0(displayName)) {
            displayName = serverHostLabel(homeServerConnection.getServerUrl());
        }
        if (kotlin.text.o.h0(displayName)) {
            displayName = ADDON_NAME;
        }
        String strSpecificHomeServerKindLabel = specificHomeServerKindLabel(homeServerConnection.getServerKind());
        return (kotlin.text.o.h0(strSpecificHomeServerKindLabel) || kotlin.text.o.T(displayName, strSpecificHomeServerKindLabel, true)) ? displayName : androidx.compose.foundation.c.t(strSpecificHomeServerKindLabel, " ", displayName);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<com.arflix.tv.data.repository.HomeServerCollection> eligibleCollections(com.arflix.tv.data.repository.HomeServerConnection r7, java.lang.String r8) {
        /*
            r6 = this;
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r8 = r8.toLowerCase(r0)
            java.util.List r7 = r7.getCollections()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r7 = r7.iterator()
        L13:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L2a
            java.lang.Object r1 = r7.next()
            r2 = r1
            com.arflix.tv.data.repository.HomeServerCollection r2 = (com.arflix.tv.data.repository.HomeServerCollection) r2
            boolean r2 = r2.getEnabled()
            if (r2 == 0) goto L13
            r0.add(r1)
            goto L13
        L2a:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r0 = r0.iterator()
        L33:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto La0
            java.lang.Object r1 = r0.next()
            r2 = r1
            com.arflix.tv.data.repository.HomeServerCollection r2 = (com.arflix.tv.data.repository.HomeServerCollection) r2
            java.lang.String r2 = r2.getType()
            java.util.Locale r3 = java.util.Locale.US
            java.lang.String r2 = r2.toLowerCase(r3)
            int r3 = r8.hashCode()
            r4 = -1544438277(0xffffffffa3f1bdfb, float:-2.6209732E-17)
            java.lang.String r5 = "series"
            if (r3 == r4) goto L7f
            r4 = -905838985(0xffffffffca01fe77, float:-2129821.8)
            if (r3 == r4) goto L78
            r4 = 104087344(0x6343f30, float:3.390066E-35)
            if (r3 == r4) goto L60
            goto L87
        L60:
            java.lang.String r3 = "movie"
            boolean r4 = r8.equals(r3)
            if (r4 != 0) goto L69
            goto L87
        L69:
            java.lang.String r4 = "movies"
            java.lang.String[] r3 = new java.lang.String[]{r4, r3}
            java.util.Set r3 = kotlin.collections.r.p0(r3)
            boolean r2 = r3.contains(r2)
            goto L9a
        L78:
            boolean r3 = r8.equals(r5)
            if (r3 != 0) goto L89
            goto L87
        L7f:
            java.lang.String r3 = "episode"
            boolean r3 = r8.equals(r3)
            if (r3 != 0) goto L89
        L87:
            r2 = 1
            goto L9a
        L89:
            java.lang.String r3 = "tvshows"
            java.lang.String r4 = "show"
            java.lang.String[] r3 = new java.lang.String[]{r3, r5, r4}
            java.util.Set r3 = kotlin.collections.r.p0(r3)
            boolean r2 = r3.contains(r2)
        L9a:
            if (r2 == 0) goto L33
            r7.add(r1)
            goto L33
        La0:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.eligibleCollections(com.arflix.tv.data.repository.HomeServerConnection, java.lang.String):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HomeServerConnection encryptedForStorage(HomeServerConnection homeServerConnection) {
        SecureStorage secureStorage = SecureStorage.INSTANCE;
        String accessToken = homeServerConnection.getAccessToken();
        if (accessToken == null) {
            accessToken = "";
        }
        String strEncrypt = secureStorage.encrypt(accessToken, HOME_SERVER_SECRET_ALIAS);
        String accountToken = homeServerConnection.getAccountToken();
        return homeServerConnection.copy((PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 1) != 0 ? homeServerConnection.enabled : false, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 2) != 0 ? homeServerConnection.connectionId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 4) != 0 ? homeServerConnection.serverUrl : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 8) != 0 ? homeServerConnection.displayName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 16) != 0 ? homeServerConnection.serverName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 32) != 0 ? homeServerConnection.serverKind : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 64) != 0 ? homeServerConnection.serverId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 128) != 0 ? homeServerConnection.userId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 256) != 0 ? homeServerConnection.userName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 512) != 0 ? homeServerConnection.accessToken : strEncrypt, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 1024) != 0 ? homeServerConnection.accountToken : secureStorage.encrypt(accountToken != null ? accountToken : "", HOME_SERVER_SECRET_ALIAS), (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 2048) != 0 ? homeServerConnection.collections : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 4096) != 0 ? homeServerConnection.lastConnectedAt : 0L);
    }

    private final String extractQualityLabel(String str) {
        String lowerCase = str.toLowerCase(Locale.US);
        return (kotlin.text.o.T(lowerCase, "2160", false) || kotlin.text.o.T(lowerCase, "4k", false) || kotlin.text.o.T(lowerCase, "uhd", false)) ? "4K" : kotlin.text.o.T(lowerCase, "1440", false) ? "1440p" : kotlin.text.o.T(lowerCase, "1080", false) ? "1080p" : kotlin.text.o.T(lowerCase, "720", false) ? "720p" : kotlin.text.o.T(lowerCase, "576", false) ? "576p" : kotlin.text.o.T(lowerCase, "480", false) ? "480p" : "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<HomeServerCollection> fetchCollections(HomeServerConnection connection) {
        HomeServerCollection homeServerCollection;
        HomeServerCollection homeServerCollection2;
        if (connection.getServerKind() != HomeServerKind.PLEX) {
            List<JsonElement> listItemsArray = itemsArray(getJson(buildUrl$default(this, connection.getServerUrl(), a0.c.l("/Users/", connection.getUserId(), "/Views"), null, 4, null), connection));
            ArrayList<JsonObject> arrayList = new ArrayList();
            Iterator<T> it = listItemsArray.iterator();
            while (it.hasNext()) {
                JsonObject jsonObjectAsJsonObjectOrNull = asJsonObjectOrNull((JsonElement) it.next());
                if (jsonObjectAsJsonObjectOrNull != null) {
                    arrayList.add(jsonObjectAsJsonObjectOrNull);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (JsonObject jsonObject : arrayList) {
                String strString = string(jsonObject, "Id");
                if (kotlin.text.o.h0(strString)) {
                    homeServerCollection = null;
                } else {
                    String strString2 = string(jsonObject, "Name");
                    if (kotlin.text.o.h0(strString2)) {
                        strString2 = this.context.getString(R.string.library_default);
                    }
                    homeServerCollection = new HomeServerCollection(strString, strString2, string(jsonObject, "CollectionType"), true);
                }
                if (homeServerCollection != null) {
                    arrayList2.add(homeServerCollection);
                }
            }
            return arrayList2;
        }
        List<JsonElement> listArray = array(getJson(buildUrl$default(this, connection.getServerUrl(), "/library/sections", null, 4, null), connection), "MediaContainer", "Directory");
        ArrayList<JsonObject> arrayList3 = new ArrayList();
        Iterator<T> it2 = listArray.iterator();
        while (it2.hasNext()) {
            JsonObject jsonObjectAsJsonObjectOrNull2 = asJsonObjectOrNull((JsonElement) it2.next());
            if (jsonObjectAsJsonObjectOrNull2 != null) {
                arrayList3.add(jsonObjectAsJsonObjectOrNull2);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        for (JsonObject jsonObject2 : arrayList3) {
            String strString3 = string(jsonObject2, "key");
            if (kotlin.text.o.h0(strString3)) {
                homeServerCollection2 = null;
            } else {
                String strString4 = string(jsonObject2, LinkHeader.Parameters.Title);
                if (kotlin.text.o.h0(strString4)) {
                    strString4 = string(jsonObject2, ContentDisposition.Parameters.Name);
                }
                if (kotlin.text.o.h0(strString4)) {
                    strString4 = this.context.getString(R.string.library_named, strString3);
                }
                homeServerCollection2 = new HomeServerCollection(strString3, strString4, string(jsonObject2, LinkHeader.Parameters.Type), true);
            }
            if (homeServerCollection2 != null) {
                arrayList4.add(homeServerCollection2);
            }
        }
        return arrayList4;
    }

    private final List<HomeServerCatalogCandidate> fetchJellyfinCollectionCatalogs(HomeServerConnection connection) {
        List<HomeServerItem> listItems = items(getJson(buildUrl(connection.getServerUrl(), a0.c.l("/Users/", connection.getUserId(), "/Items"), kotlin.collections.h0.t0(new x6.x("Recursive", "true"), new x6.x("IncludeItemTypes", "BoxSet"), new x6.x("Fields", itemFields()), new x6.x("StartIndex", "0"), new x6.x("Limit", "100"))), connection));
        ArrayList arrayList = new ArrayList();
        for (HomeServerItem homeServerItem : listItems) {
            String id = homeServerItem.getId();
            if (kotlin.text.o.h0(id)) {
                id = null;
            }
            HomeServerCatalogCandidate catalogCandidate = id != null ? toCatalogCandidate(connection, new HomeServerCollection("collection:".concat(id), homeServerItem.getName(), "collection", true)) : null;
            if (catalogCandidate != null) {
                arrayList.add(catalogCandidate);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [x6.c0] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v4, types: [kotlin.collections.z] */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.util.ArrayList] */
    private final List<HomeServerCatalogCandidate> fetchPlexCollectionCatalogs(HomeServerConnection connection) {
        ?? c0Var;
        List<HomeServerCollection> collections = connection.getCollections();
        ArrayList<HomeServerCollection> arrayList = new ArrayList();
        for (Object obj : collections) {
            HomeServerCollection homeServerCollection = (HomeServerCollection) obj;
            if (homeServerCollection.getEnabled() && !kotlin.text.o.h0(homeServerCollection.getId())) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (HomeServerCollection homeServerCollection2 : arrayList) {
            try {
                List<HomeServerItem> listMetadataItems = metadataItems(getJson(buildUrl(connection.getServerUrl(), "/library/sections/" + homeServerCollection2.getId() + "/collections", kotlin.collections.h0.t0(new x6.x("includeGuids", "1"), new x6.x("X-Plex-Container-Start", "0"), new x6.x("X-Plex-Container-Size", "100"))), connection), connection.getServerKind());
                c0Var = new ArrayList();
                for (HomeServerItem homeServerItem : listMetadataItems) {
                    String id = homeServerItem.getId();
                    HomeServerCatalogCandidate catalogCandidate = null;
                    if (kotlin.text.o.h0(id)) {
                        id = null;
                    }
                    if (id != null) {
                        catalogCandidate = toCatalogCandidate(connection, new HomeServerCollection("collection:" + homeServerCollection2.getId() + ":" + id, homeServerItem.getName(), "collection", true));
                    }
                    if (catalogCandidate != null) {
                        c0Var.add(catalogCandidate);
                    }
                }
            } catch (Throwable th) {
                c0Var = new x6.c0(th);
            }
            if (c0Var instanceof x6.c0) {
                c0Var = kotlin.collections.z.f19728i;
            }
            kotlin.collections.x.b0(arrayList2, (Iterable) c0Var);
        }
        return arrayList2;
    }

    private final List<PlexResourceDevice> fetchPlexResources(String accountToken) {
        gb.b0 b0VarD;
        Object c0Var;
        List<PlexResourceDevice> plexResourcesXml;
        try {
            androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
            kVar.i(null, "https://plex.tv/api/resources");
            b0VarD = kVar.d();
        } catch (IllegalArgumentException unused) {
            b0VarD = null;
        }
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        if (b0VarD == null) {
            return zVar;
        }
        androidx.tv.foundation.lazy.list.k kVarF = b0VarD.f();
        kVarF.c("includeHttps", "1");
        kVarF.c("includeRelay", "1");
        kVarF.c("X-Plex-Token", accountToken);
        String str = kVarF.d().f15625i;
        gb.j0 j0Var = new gb.j0();
        j0Var.i(str);
        gb.j0 j0VarHeadersWith = headersWith(j0Var, plexPublicHeaders());
        j0VarHeadersWith.f15724c.h("Accept", "application/xml");
        j0VarHeadersWith.f15724c.h("X-Plex-Token", accountToken);
        try {
            gb.p0 p0VarD = this.okHttpClient.a(j0VarHeadersWith.b()).d();
            try {
                if (p0VarD.k()) {
                    s0 s0Var = p0VarD.f15786q;
                    String strX = s0Var != null ? s0Var.x() : null;
                    if (strX == null) {
                        strX = "";
                    }
                    plexResourcesXml = parsePlexResourcesXml(strX);
                } else {
                    plexResourcesXml = zVar;
                }
                p0VarD.close();
                c0Var = plexResourcesXml;
            } finally {
            }
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        Object obj = zVar;
        if (!(c0Var instanceof x6.c0)) {
            obj = c0Var;
        }
        return (List) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Unreachable blocks removed: 1, instructions: 2 */
    public final ServerInfo fetchPublicInfo(String serverUrl) {
        JsonObject json$default;
        String text$default = null;
        try {
            json$default = getJson$default(this, buildUrl$default(this, serverUrl, "/System/Info/Public", null, 4, null), null, 2, null);
        } catch (Exception unused) {
            json$default = null;
        }
        if (json$default != null && !json$default.entrySet().isEmpty()) {
            return new ServerInfo(string(json$default, "ServerName"), string(json$default, "Id"), string(json$default, "ProductName"), detectServerKind(string(json$default, "ProductName"), string(json$default, "ServerName")));
        }
        try {
            text$default = getText$default(this, buildUrl$default(this, serverUrl, "/identity", null, 4, null), null, 2, null);
        } catch (Exception unused2) {
        }
        String str = "";
        x6.x plexIdentity = parsePlexIdentity(text$default == null ? "" : text$default);
        String str2 = (String) plexIdentity.f22608i;
        String str3 = (String) plexIdentity.f22609l;
        if (!kotlin.text.o.h0(str3) || (text$default != null && kotlin.text.o.T(text$default, "MediaContainer", false))) {
            str = "Media Server";
        }
        return new ServerInfo(str2, str3, str, (!kotlin.text.o.h0(str3) || (text$default != null && kotlin.text.o.T(text$default, "MediaContainer", false))) ? HomeServerKind.PLEX : HomeServerKind.UNKNOWN);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<HomeServerCatalogCandidate> fetchServerCollectionCatalogs(HomeServerConnection connection) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[connection.getServerKind().ordinal()];
        if (i10 == 1) {
            return fetchPlexCollectionCatalogs(connection);
        }
        if (i10 == 2 || i10 == 3) {
            return fetchJellyfinCollectionCatalogs(connection);
        }
        if (i10 == 4) {
            return kotlin.collections.z.f19728i;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final ServerInfo fetchSystemInfo(HomeServerConnection connection) {
        HomeServerKind serverKind = connection.getServerKind();
        HomeServerKind homeServerKind = HomeServerKind.PLEX;
        if (serverKind != homeServerKind) {
            JsonObject json = getJson(buildUrl$default(this, connection.getServerUrl(), "/System/Info", null, 4, null), connection);
            return new ServerInfo(string(json, "ServerName"), string(json, "Id"), string(json, "ProductName"), detectServerKind(string(json, "ProductName"), string(json, "ServerName")));
        }
        x6.x plexIdentity = parsePlexIdentity(getText(buildUrl$default(this, connection.getServerUrl(), "/identity", null, 4, null), connection));
        String serverName = (String) plexIdentity.f22608i;
        String serverId = (String) plexIdentity.f22609l;
        if (kotlin.text.o.h0(serverName)) {
            serverName = connection.getServerName();
        }
        if (kotlin.text.o.h0(serverId)) {
            serverId = connection.getServerId();
        }
        return new ServerInfo(serverName, serverId, "Media Server", homeServerKind);
    }

    private final List<HomeServerItem> filterPlexEpisodeNumbers(List<HomeServerItem> results, Map<String, String> query) {
        String str = query.get("ParentIndexNumber");
        Integer numR = str != null ? kotlin.text.u.R(str) : null;
        String str2 = query.get("IndexNumber");
        Integer numR2 = str2 != null ? kotlin.text.u.R(str2) : null;
        ArrayList arrayList = new ArrayList();
        for (Object obj : results) {
            HomeServerItem homeServerItem = (HomeServerItem) obj;
            if (numR != null) {
                Integer parentIndexNumber = homeServerItem.getParentIndexNumber();
                int iIntValue = numR.intValue();
                if (parentIndexNumber != null && parentIndexNumber.intValue() == iIntValue) {
                }
            }
            if (numR2 != null) {
                Integer indexNumber = homeServerItem.getIndexNumber();
                int iIntValue2 = numR2.intValue();
                if (indexNumber != null && indexNumber.intValue() == iIntValue2) {
                }
            }
            arrayList.add(obj);
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (hashSet.add(((HomeServerItem) obj2).getId())) {
                arrayList2.add(obj2);
            }
        }
        return arrayList2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object findBestSeries(com.arflix.tv.data.repository.HomeServerConnection r13, java.lang.String r14, java.lang.String r15, java.lang.Integer r16, java.lang.Integer r17, java.lang.Integer r18, d7.d<? super com.arflix.tv.data.repository.HomeServerRepository.HomeServerItem> r19) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.findBestSeries(com.arflix.tv.data.repository.HomeServerConnection, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    private final HomeServerItem findEpisode(HomeServerConnection connection, String seriesId, int season, int episode) {
        return (HomeServerItem) kotlin.collections.x.o0(findEpisodes(connection, seriesId, season, episode));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HomeServerItem findEpisodeBySearch(HomeServerConnection connection, String title, int season, int episode, String imdbId, Integer tmdbId, Integer tvdbId) {
        Integer indexNumber;
        List<HomeServerItem> listQueryItems = queryItems(connection, "Episode", kotlin.collections.h0.t0(new x6.x("SearchTerm", title), new x6.x("ParentIndexNumber", String.valueOf(season)), new x6.x("IndexNumber", String.valueOf(episode)), new x6.x("Limit", "25")));
        ArrayList arrayList = new ArrayList();
        for (Object obj : listQueryItems) {
            HomeServerItem homeServerItem = (HomeServerItem) obj;
            Integer parentIndexNumber = homeServerItem.getParentIndexNumber();
            if (parentIndexNumber != null && parentIndexNumber.intValue() == season && (indexNumber = homeServerItem.getIndexNumber()) != null && indexNumber.intValue() == episode) {
                arrayList.add(obj);
            }
        }
        return bestCandidate(arrayList, title, null, imdbId, tmdbId, tvdbId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<HomeServerItem> findEpisodes(HomeServerConnection connection, String seriesId, int season, int episode) {
        Integer indexNumber;
        Integer indexNumber2;
        Integer indexNumber3;
        if (connection.getServerKind() == HomeServerKind.PLEX) {
            List<HomeServerItem> listMetadataItems = metadataItems(getJson(buildUrl(connection.getServerUrl(), a0.c.l("/library/metadata/", seriesId, "/allLeaves"), Collections.singletonMap("includeGuids", "1")), connection), connection.getServerKind());
            ArrayList arrayList = new ArrayList();
            for (Object obj : listMetadataItems) {
                HomeServerItem homeServerItem = (HomeServerItem) obj;
                Integer parentIndexNumber = homeServerItem.getParentIndexNumber();
                if (parentIndexNumber != null && parentIndexNumber.intValue() == season && (indexNumber3 = homeServerItem.getIndexNumber()) != null && indexNumber3.intValue() == episode) {
                    arrayList.add(obj);
                }
            }
            HashSet hashSet = new HashSet();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : arrayList) {
                if (hashSet.add(((HomeServerItem) obj2).getId())) {
                    arrayList2.add(obj2);
                }
            }
            return arrayList2;
        }
        List<HomeServerItem> listItems = items(getJson(buildUrl(connection.getServerUrl(), a0.c.l("/Shows/", seriesId, "/Episodes"), kotlin.collections.h0.t0(new x6.x("UserId", connection.getUserId()), new x6.x("Season", String.valueOf(season)), new x6.x("Fields", itemFields()))), connection));
        ArrayList arrayList3 = new ArrayList();
        for (Object obj3 : listItems) {
            HomeServerItem homeServerItem2 = (HomeServerItem) obj3;
            Integer parentIndexNumber2 = homeServerItem2.getParentIndexNumber();
            if (parentIndexNumber2 != null && parentIndexNumber2.intValue() == season && (indexNumber2 = homeServerItem2.getIndexNumber()) != null && indexNumber2.intValue() == episode) {
                arrayList3.add(obj3);
            }
        }
        HashSet hashSet2 = new HashSet();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj4 : arrayList3) {
            if (hashSet2.add(((HomeServerItem) obj4).getId())) {
                arrayList4.add(obj4);
            }
        }
        if (!arrayList4.isEmpty()) {
            return arrayList4;
        }
        List<HomeServerItem> listQueryItems = queryItems(connection, "Episode", kotlin.collections.h0.t0(new x6.x("SeriesId", seriesId), new x6.x("ParentIndexNumber", String.valueOf(season)), new x6.x("IndexNumber", String.valueOf(episode)), new x6.x("Limit", "10")));
        ArrayList arrayList5 = new ArrayList();
        for (Object obj5 : listQueryItems) {
            HomeServerItem homeServerItem3 = (HomeServerItem) obj5;
            Integer parentIndexNumber3 = homeServerItem3.getParentIndexNumber();
            if (parentIndexNumber3 != null && parentIndexNumber3.intValue() == season && (indexNumber = homeServerItem3.getIndexNumber()) != null && indexNumber.intValue() == episode) {
                arrayList5.add(obj5);
            }
        }
        HashSet hashSet3 = new HashSet();
        ArrayList arrayList6 = new ArrayList();
        for (Object obj6 : arrayList5) {
            if (hashSet3.add(((HomeServerItem) obj6).getId())) {
                arrayList6.add(obj6);
            }
        }
        return arrayList6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object findMovieMatches(com.arflix.tv.data.repository.HomeServerConnection r13, java.lang.String r14, java.lang.String r15, java.lang.Integer r16, java.lang.Integer r17, d7.d<? super java.util.List<com.arflix.tv.data.repository.HomeServerRepository.HomeServerItem>> r18) {
        /*
            Method dump skipped, instruction units count: 263
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.findMovieMatches(com.arflix.tv.data.repository.HomeServerConnection, java.lang.String, java.lang.String, java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    private final String formatBytes(long bytes) {
        if (bytes <= 0) {
            return "";
        }
        double d4 = bytes;
        double d10 = d4 / 1.073741824E9d;
        return d10 >= 1.0d ? String.format(Locale.US, "%.2f GB", Arrays.copyOf(new Object[]{Double.valueOf(d10)}, 1)) : String.format(Locale.US, "%.0f MB", Arrays.copyOf(new Object[]{Double.valueOf(d4 / 1048576.0d)}, 1));
    }

    private final List<StreamSource> getCachedSourcesLocked(String key) {
        CachedHomeServerSources cachedHomeServerSources = (CachedHomeServerSources) this.sourceCache.get((Object) key);
        if (cachedHomeServerSources == null) {
            return null;
        }
        if (System.currentTimeMillis() - cachedHomeServerSources.getCreatedAtMs() < (cachedHomeServerSources.getSources().isEmpty() ? 30000L : SOURCE_CACHE_TTL_MS)) {
            return cachedHomeServerSources.getSources();
        }
        this.sourceCache.remove((Object) key);
        return null;
    }

    private final JsonObject getJson(String url, HomeServerConnection connection) {
        gb.j0 j0VarRequestBuilder = requestBuilder(url, connection);
        j0VarRequestBuilder.d();
        gb.p0 p0VarD = this.okHttpClient.a(j0VarRequestBuilder.b()).d();
        try {
            s0 s0Var = p0VarD.f15786q;
            String strX = s0Var != null ? s0Var.x() : null;
            if (strX == null) {
                strX = "";
            }
            if (!p0VarD.k()) {
                throw new IllegalStateException(this.context.getString(R.string.homeserver_request_failed, Integer.valueOf(p0VarD.f15783n)).toString());
            }
            JsonObject jsonObjectAsJsonObjectOrNull = asJsonObjectOrNull(new JsonParser().parse(strX));
            if (jsonObjectAsJsonObjectOrNull == null) {
                jsonObjectAsJsonObjectOrNull = new JsonObject();
            }
            p0VarD.close();
            return jsonObjectAsJsonObjectOrNull;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                xc.d.L(p0VarD, th);
                throw th2;
            }
        }
    }

    public static /* synthetic */ JsonObject getJson$default(HomeServerRepository homeServerRepository, String str, HomeServerConnection homeServerConnection, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            homeServerConnection = null;
        }
        return homeServerRepository.getJson(str, homeServerConnection);
    }

    private final String getText(String url, HomeServerConnection connection) {
        gb.j0 j0VarRequestBuilder = requestBuilder(url, connection);
        j0VarRequestBuilder.d();
        gb.p0 p0VarD = this.okHttpClient.a(j0VarRequestBuilder.b()).d();
        try {
            s0 s0Var = p0VarD.f15786q;
            String strX = s0Var != null ? s0Var.x() : null;
            if (strX == null) {
                strX = "";
            }
            if (!p0VarD.k()) {
                throw new IllegalStateException(this.context.getString(R.string.homeserver_request_failed, Integer.valueOf(p0VarD.f15783n)).toString());
            }
            p0VarD.close();
            return strX;
        } finally {
        }
    }

    public static /* synthetic */ String getText$default(HomeServerRepository homeServerRepository, String str, HomeServerConnection homeServerConnection, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            homeServerConnection = null;
        }
        return homeServerRepository.getText(str, homeServerConnection);
    }

    private final gb.j0 headersWith(gb.j0 j0Var, gb.a0 a0Var) {
        int size = a0Var.size();
        for (int i10 = 0; i10 < size; i10++) {
            j0Var.e(a0Var.b(i10), a0Var.e(i10));
        }
        return j0Var;
    }

    private final String homeServerKindLabel(HomeServerKind kind) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
        if (i10 == 1 || i10 == 2 || i10 == 3) {
            return "Media Server";
        }
        if (i10 == 4) {
            return "";
        }
        throw new NoWhenBranchMatchedException();
    }

    private final String identityKey(HomeServerMediaSource homeServerMediaSource) {
        String variantKey = homeServerMediaSource.getVariantKey();
        if (kotlin.text.o.h0(variantKey)) {
            variantKey = null;
        }
        if (variantKey == null) {
            variantKey = homeServerMediaSource.getId();
            if (kotlin.text.o.h0(variantKey)) {
                variantKey = null;
            }
            if (variantKey == null) {
                variantKey = homeServerMediaSource.getKey();
                if (kotlin.text.o.h0(variantKey)) {
                    variantKey = null;
                }
                if (variantKey == null) {
                    variantKey = homeServerMediaSource.getPath();
                    if (kotlin.text.o.h0(variantKey)) {
                        variantKey = null;
                    }
                    if (variantKey == null) {
                        String name = homeServerMediaSource.getName();
                        String str = kotlin.text.o.h0(name) ? null : name;
                        if (str != null) {
                            return str;
                        }
                        return homeServerMediaSource.getContainer() + "|" + homeServerMediaSource.getSizeBytes() + "|" + homeServerMediaSource.getVideoWidth() + "|" + homeServerMediaSource.getVideoHeight();
                    }
                }
            }
        }
        return variantKey;
    }

    /* JADX INFO: renamed from: int, reason: not valid java name */
    private final Integer m6129int(JsonObject jsonObject, String str) {
        String strAsStringOrNull;
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement == null || (strAsStringOrNull = asStringOrNull(jsonElement)) == null) {
            return null;
        }
        return kotlin.text.u.R(strAsStringOrNull);
    }

    private final boolean itemBelongsToEnabledPlexCollection(HomeServerItem item, List<HomeServerCollection> collections) {
        if (!collections.isEmpty() && !kotlin.text.o.h0(item.getLibrarySectionId())) {
            if (collections.isEmpty()) {
                return false;
            }
            Iterator<T> it = collections.iterator();
            while (it.hasNext()) {
                if (kotlin.jvm.internal.p.a(((HomeServerCollection) it.next()).getId(), item.getLibrarySectionId())) {
                }
            }
            return false;
        }
        return true;
    }

    private final String itemFields() {
        return "ProviderIds,MediaSources,MediaStreams,Path,PremiereDate,ProductionYear,RunTimeTicks,Overview,CommunityRating,ImageTags,BackdropImageTags,DateCreated";
    }

    private final List<HomeServerItem> items(JsonObject jsonObject) {
        List<JsonElement> listItemsArray = itemsArray(jsonObject);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listItemsArray.iterator();
        while (it.hasNext()) {
            JsonObject jsonObjectAsJsonObjectOrNull = asJsonObjectOrNull((JsonElement) it.next());
            HomeServerItem homeServerItem = jsonObjectAsJsonObjectOrNull != null ? toHomeServerItem(jsonObjectAsJsonObjectOrNull, HomeServerKind.UNKNOWN) : null;
            if (homeServerItem != null) {
                arrayList.add(homeServerItem);
            }
        }
        return arrayList;
    }

    private final List<JsonElement> itemsArray(JsonObject jsonObject) {
        return array(jsonObject, "Items");
    }

    public static /* synthetic */ Object loadCatalogItems$default(HomeServerRepository homeServerRepository, String str, int i10, int i11, HomeServerLibrarySort homeServerLibrarySort, MediaType mediaType, String str2, boolean z, d7.d dVar, int i12, Object obj) {
        if ((i12 & 8) != 0) {
            homeServerLibrarySort = HomeServerLibrarySort.TITLE;
        }
        HomeServerLibrarySort homeServerLibrarySort2 = homeServerLibrarySort;
        if ((i12 & 16) != 0) {
            mediaType = null;
        }
        return homeServerRepository.loadCatalogItems(str, i10, i11, homeServerLibrarySort2, mediaType, (i12 & 32) != 0 ? "" : str2, (i12 & 64) != 0 ? false : z, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HomeServerCatalogPage loadConnectionCatalogItems(HomeServerConnection connection, String collectionId, String collectionType, int offset, int limit, HomeServerLibrarySort sort, MediaType mediaType, String searchQuery) {
        return connection.getServerKind() == HomeServerKind.PLEX ? loadPlexCatalogItems(connection, collectionId, collectionType, offset, limit, sort, mediaType, searchQuery) : loadJellyfinCatalogItems(connection, collectionId, collectionType, offset, limit, sort, mediaType, searchQuery);
    }

    private final HomeServerCatalogPage loadJellyfinCatalogItems(HomeServerConnection connection, String collectionId, String collectionType, int offset, int limit, HomeServerLibrarySort sort, MediaType mediaType, String searchQuery) throws UnsupportedEncodingException {
        String str;
        String str2;
        String str3 = collectionId;
        String string = kotlin.text.o.L0(kotlin.text.o.r0(str3, "collection:")).toString();
        String serverUrl = connection.getServerUrl();
        String strL = a0.c.l("/Users/", connection.getUserId(), "/Items");
        x6.x xVar = new x6.x("ParentId", string);
        x6.x xVar2 = new x6.x("Recursive", "true");
        MediaType mediaTypeHomeServerCatalogMediaType = HomeServerRepositoryKt.homeServerCatalogMediaType(collectionType, mediaType);
        int i10 = mediaTypeHomeServerCatalogMediaType == null ? -1 : WhenMappings.$EnumSwitchMapping$1[mediaTypeHomeServerCatalogMediaType.ordinal()];
        if (i10 == -1) {
            str = "Movie,Series";
        } else if (i10 == 1) {
            str = "Movie";
        } else {
            if (i10 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            str = "Series";
        }
        x6.x xVar3 = new x6.x("IncludeItemTypes", str);
        x6.x xVar4 = new x6.x("Fields", catalogItemFields());
        int i11 = WhenMappings.$EnumSwitchMapping$2[sort.ordinal()];
        if (i11 == 1) {
            str2 = "DateCreated";
        } else if (i11 == 2) {
            str2 = "CommunityRating";
        } else {
            if (i11 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            str2 = "SortName";
        }
        x6.x xVar5 = new x6.x("SortBy", str2);
        x6.x xVar6 = new x6.x("SortOrder", sort == HomeServerLibrarySort.TITLE ? "Ascending" : "Descending");
        String string2 = kotlin.text.o.L0(searchQuery).toString();
        if (kotlin.text.o.h0(string2)) {
            string2 = null;
        }
        JsonObject json = getJson(buildUrl(serverUrl, strL, kotlin.collections.h0.t0(xVar, xVar2, xVar3, xVar4, xVar5, xVar6, new x6.x("SearchTerm", string2), new x6.x("StartIndex", String.valueOf(offset)), new x6.x("Limit", String.valueOf(limit)))), connection);
        Integer numM6129int = m6129int(json, "TotalRecordCount");
        int iIntValue = numM6129int != null ? numM6129int.intValue() : items(json).size();
        List<HomeServerItem> listItems = items(json);
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listItems.iterator();
        while (it.hasNext()) {
            HomeServerCatalogItem catalogItem = ((HomeServerItem) it.next()).toCatalogItem(connection, INSTANCE.buildCatalogSourceRef(connection, new HomeServerCollection(str3, null, "mixed", false, 10, null)));
            if (catalogItem != null) {
                arrayList.add(catalogItem);
            }
            str3 = collectionId;
        }
        return new HomeServerCatalogPage(arrayList, arrayList.size() + offset < iIntValue);
    }

    private final HomeServerCatalogPage loadPlexCatalogItems(HomeServerConnection connection, String collectionId, String collectionType, int offset, int limit, HomeServerLibrarySort sort, MediaType mediaType, String searchQuery) throws UnsupportedEncodingException {
        String strL;
        String str;
        String str2;
        int iIntValue;
        Integer numM6129int;
        String str3 = collectionId;
        List listY0 = kotlin.text.o.y0(str3, new String[]{":"}, 0, 6);
        if (!kotlin.jvm.internal.p.a(kotlin.collections.x.o0(listY0), "collection") || listY0.size() < 3) {
            strL = a0.c.l("/library/sections/", str3, "/all");
        } else {
            strL = "/library/metadata/" + listY0.get(2) + "/children";
        }
        String str4 = collectionType;
        MediaType mediaTypeHomeServerCatalogMediaType = HomeServerRepositoryKt.homeServerCatalogMediaType(str4, mediaType);
        int i10 = mediaTypeHomeServerCatalogMediaType == null ? -1 : WhenMappings.$EnumSwitchMapping$1[mediaTypeHomeServerCatalogMediaType.ordinal()];
        if (i10 == -1) {
            str = null;
        } else if (i10 == 1) {
            str = "1";
        } else {
            if (i10 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            str = "2";
        }
        String serverUrl = connection.getServerUrl();
        x6.x xVar = new x6.x(LinkHeader.Parameters.Type, str);
        x6.x xVar2 = new x6.x("includeGuids", "1");
        int i11 = WhenMappings.$EnumSwitchMapping$2[sort.ordinal()];
        if (i11 == 1) {
            str2 = "addedAt:desc";
        } else if (i11 == 2) {
            str2 = "rating:desc";
        } else {
            if (i11 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            str2 = "titleSort:asc";
        }
        x6.x xVar3 = new x6.x("sort", str2);
        String string = kotlin.text.o.L0(searchQuery).toString();
        if (kotlin.text.o.h0(string)) {
            string = null;
        }
        JsonObject json = getJson(buildUrl(serverUrl, strL, kotlin.collections.h0.t0(xVar, xVar2, xVar3, new x6.x(LinkHeader.Parameters.Title, string), new x6.x("X-Plex-Container-Start", String.valueOf(offset)), new x6.x("X-Plex-Container-Size", String.valueOf(limit)))), connection);
        JsonObject jsonObjectObj = obj(json, "MediaContainer");
        if (jsonObjectObj == null || (numM6129int = m6129int(jsonObjectObj, "totalSize")) == null) {
            Integer numM6129int2 = jsonObjectObj != null ? m6129int(jsonObjectObj, ContentDisposition.Parameters.Size) : null;
            iIntValue = numM6129int2 != null ? numM6129int2.intValue() : metadataItems(json, connection.getServerKind()).size();
        } else {
            iIntValue = numM6129int.intValue();
        }
        int i12 = iIntValue;
        List<HomeServerItem> listMetadataItems = metadataItems(json, connection.getServerKind());
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listMetadataItems.iterator();
        while (it.hasNext()) {
            HomeServerCatalogItem catalogItem = ((HomeServerItem) it.next()).toCatalogItem(connection, INSTANCE.buildCatalogSourceRef(connection, new HomeServerCollection(str3, null, str4, false, 10, null)));
            if (catalogItem != null) {
                arrayList.add(catalogItem);
            }
            str3 = collectionId;
            str4 = collectionType;
        }
        return new HomeServerCatalogPage(arrayList, arrayList.size() + offset < i12);
    }

    /* JADX INFO: renamed from: long, reason: not valid java name */
    private final Long m6130long(JsonObject jsonObject, String str) {
        String strAsStringOrNull;
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement == null || (strAsStringOrNull = asStringOrNull(jsonElement)) == null) {
            return null;
        }
        return kotlin.text.u.S(strAsStringOrNull);
    }

    private final List<HomeServerItem> matchingMovieCandidates(Collection<HomeServerItem> candidates, String title, Integer year, String imdbId, Integer tmdbId) {
        Integer num;
        Collection<HomeServerItem> collection = candidates;
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(collection, 10));
        for (HomeServerItem homeServerItem : collection) {
            String str = title;
            Integer num2 = year;
            arrayList.add(new x6.x(homeServerItem, Integer.valueOf(HomeServerMatcher.INSTANCE.score(str, num2, imdbId, tmdbId, null, homeServerItem.info()))));
            title = str;
            year = num2;
        }
        String str2 = title;
        Integer num3 = year;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (HomeServerMatcher.INSTANCE.isAcceptable(((Number) ((x6.x) obj).f22609l).intValue())) {
                arrayList2.add(obj);
            }
        }
        Iterator it = arrayList2.iterator();
        if (it.hasNext()) {
            Integer numValueOf = Integer.valueOf(((Number) ((x6.x) it.next()).f22609l).intValue());
            while (it.hasNext()) {
                Integer numValueOf2 = Integer.valueOf(((Number) ((x6.x) it.next()).f22609l).intValue());
                if (numValueOf.compareTo(numValueOf2) < 0) {
                    numValueOf = numValueOf2;
                }
            }
            num = numValueOf;
        } else {
            num = null;
        }
        if (num == null) {
            return kotlin.collections.z.f19728i;
        }
        int iIntValue = num.intValue();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : arrayList2) {
            x6.x xVar = (x6.x) obj2;
            HomeServerItem homeServerItem2 = (HomeServerItem) xVar.f22608i;
            if (((Number) xVar.f22609l).intValue() == iIntValue || HomeServerMatcher.INSTANCE.isLikelySameVersion(str2, num3, homeServerItem2.info())) {
                arrayList3.add(obj2);
            }
        }
        List listW0 = kotlin.collections.x.W0(arrayList3, new Comparator() { // from class: com.arflix.tv.data.repository.HomeServerRepository$matchingMovieCandidates$$inlined$sortedByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e(Integer.valueOf(((Number) ((x6.x) t10).f22609l).intValue()), Integer.valueOf(((Number) ((x6.x) t2).f22609l).intValue()));
            }
        });
        ArrayList arrayList4 = new ArrayList(kotlin.collections.s.U(listW0, 10));
        Iterator it2 = listW0.iterator();
        while (it2.hasNext()) {
            arrayList4.add((HomeServerItem) ((x6.x) it2.next()).f22608i);
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList5 = new ArrayList();
        for (Object obj3 : arrayList4) {
            if (hashSet.add(((HomeServerItem) obj3).getId())) {
                arrayList5.add(obj3);
            }
        }
        return arrayList5;
    }

    private final List<HomeServerMediaSource> mediaSources(JsonObject jsonObject) {
        List<JsonElement> listArray = array(jsonObject, "MediaSources");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listArray.iterator();
        while (it.hasNext()) {
            JsonObject jsonObjectAsJsonObjectOrNull = asJsonObjectOrNull((JsonElement) it.next());
            HomeServerMediaSource mediaSource$default = jsonObjectAsJsonObjectOrNull != null ? toMediaSource$default(this, jsonObjectAsJsonObjectOrNull, HomeServerKind.UNKNOWN, null, 0, 0, 14, null) : null;
            if (mediaSource$default != null) {
                arrayList.add(mediaSource$default);
            }
        }
        return arrayList;
    }

    private final List<HomeServerCollection> mergeCollectionStates(List<HomeServerCollection> refreshed, List<HomeServerCollection> previous) {
        int iQ0 = kotlin.collections.i0.q0(kotlin.collections.s.U(previous, 10));
        if (iQ0 < 16) {
            iQ0 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
        for (Object obj : previous) {
            linkedHashMap.put(((HomeServerCollection) obj).getId(), obj);
        }
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(refreshed, 10));
        for (HomeServerCollection homeServerCollection : refreshed) {
            HomeServerCollection homeServerCollection2 = (HomeServerCollection) linkedHashMap.get(homeServerCollection.getId());
            arrayList.add(HomeServerCollection.copy$default(homeServerCollection, null, null, null, homeServerCollection2 != null ? homeServerCollection2.getEnabled() : homeServerCollection.getEnabled(), 7, null));
        }
        return arrayList;
    }

    private final List<HomeServerItem> metadataItems(JsonObject jsonObject, HomeServerKind homeServerKind) {
        List<JsonElement> listArray = array(jsonObject, "MediaContainer", "Metadata");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = listArray.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            JsonObject jsonObjectAsJsonObjectOrNull = asJsonObjectOrNull((JsonElement) it.next());
            HomeServerItem homeServerItem = jsonObjectAsJsonObjectOrNull != null ? toHomeServerItem(jsonObjectAsJsonObjectOrNull, homeServerKind) : null;
            if (homeServerItem != null) {
                arrayList.add(homeServerItem);
            }
        }
        if (!arrayList.isEmpty()) {
            return arrayList;
        }
        List<JsonElement> listArray2 = array(jsonObject, "Metadata");
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it2 = listArray2.iterator();
        while (it2.hasNext()) {
            JsonObject jsonObjectAsJsonObjectOrNull2 = asJsonObjectOrNull((JsonElement) it2.next());
            HomeServerItem homeServerItem2 = jsonObjectAsJsonObjectOrNull2 != null ? toHomeServerItem(jsonObjectAsJsonObjectOrNull2, homeServerKind) : null;
            if (homeServerItem2 != null) {
                arrayList2.add(homeServerItem2);
            }
        }
        return arrayList2;
    }

    private final boolean needsPlexCompatiblePlayback(HomeServerMediaSource homeServerMediaSource) {
        String container = homeServerMediaSource.getContainer();
        Locale locale = Locale.US;
        String lowerCase = container.toLowerCase(locale);
        String lowerCase2 = homeServerMediaSource.getVideoCodec().toLowerCase(locale);
        String lowerCase3 = homeServerMediaSource.getVideoProfile().toLowerCase(locale);
        String lowerCase4 = homeServerMediaSource.getAudioCodec().toLowerCase(locale);
        String lowerCase5 = homeServerMediaSource.getAudioProfile().toLowerCase(locale);
        return kotlin.collections.r.p0(new String[]{"mkv", "matroska"}).contains(lowerCase) && ((kotlin.collections.r.p0(new String[]{"hevc", "h265", "h.265"}).contains(lowerCase2) && (homeServerMediaSource.getVideoBitDepth() >= 10 || kotlin.text.o.T(lowerCase3, "main 10", false) || kotlin.text.o.T(lowerCase3, "main10", false))) || (lowerCase4.equals(HlsSegmentFormat.AAC) && (kotlin.text.o.T(lowerCase5, "he", false) || kotlin.text.o.T(lowerCase5, "sbr", false))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String normalizeServerUrl(String rawUrl) {
        gb.b0 b0VarD;
        String strO0 = kotlin.text.o.O0(kotlin.text.o.L0(rawUrl).toString(), '/');
        if (kotlin.text.o.h0(strO0)) {
            return "";
        }
        if (!kotlin.text.u.P(strO0, "http://", true) && !kotlin.text.u.P(strO0, "https://", true)) {
            strO0 = "http://".concat(strO0);
        }
        try {
            androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
            kVar.i(null, strO0);
            b0VarD = kVar.d();
        } catch (IllegalArgumentException unused) {
            b0VarD = null;
        }
        String strO02 = b0VarD != null ? kotlin.text.o.O0(b0VarD.f15625i, '/') : null;
        return strO02 == null ? "" : strO02;
    }

    private final JsonObject obj(JsonObject jsonObject, String str) {
        JsonElement jsonElement = jsonObject.get(str);
        if (jsonElement != null) {
            return asJsonObjectOrNull(jsonElement);
        }
        return null;
    }

    private final HomeServerConnection parseConnection(String json) {
        return (HomeServerConnection) kotlin.collections.x.o0(parseConnections(json));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<HomeServerConnection> parseConnections(String json) throws Exception {
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        if (json == null || kotlin.text.o.h0(json)) {
            return zVar;
        }
        try {
            JsonElement jsonElement = new JsonParser().parse(json);
            List<HomeServerConnection> connections = (jsonElement.isJsonObject() && jsonElement.getAsJsonObject().has("connections")) ? ((HomeServerProfileConfig) this.gson.fromJson(jsonElement, HomeServerProfileConfig.class)).getConnections() : jsonElement.isJsonArray() ? (List) this.gson.fromJson(jsonElement, TypeToken.getParameterized(List.class, HomeServerConnection.class).getType()) : jsonElement.isJsonObject() ? Collections.singletonList(this.gson.fromJson(jsonElement, HomeServerConnection.class)) : zVar;
            ArrayList arrayList = new ArrayList(kotlin.collections.s.U(connections, 10));
            Iterator<T> it = connections.iterator();
            while (it.hasNext()) {
                arrayList.add(decryptedForUse(sanitized((HomeServerConnection) it.next())));
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                HomeServerConnection homeServerConnection = (HomeServerConnection) obj;
                if (!kotlin.text.o.h0(homeServerConnection.getServerUrl()) || !kotlin.text.o.h0(homeServerConnection.getAccessToken())) {
                    arrayList2.add(obj);
                }
            }
            HashSet hashSet = new HashSet();
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : arrayList2) {
                if (hashSet.add(connectionIdentity((HomeServerConnection) obj2))) {
                    arrayList3.add(obj2);
                }
            }
            return arrayList3;
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
            AppLogger.recordException$default(AppLogger.INSTANCE, e5, null, 2, null);
            return zVar;
        }
    }

    private final x6.x parsePlexIdentity(String body) {
        Object c0Var;
        Object obj;
        JsonObject jsonObjectAsJsonObjectOrNull;
        try {
            jsonObjectAsJsonObjectOrNull = asJsonObjectOrNull(new JsonParser().parse(body));
            obj = jsonObjectAsJsonObjectOrNull;
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (jsonObjectAsJsonObjectOrNull != null) {
            c0Var = obj(jsonObjectAsJsonObjectOrNull, "MediaContainer");
            obj = jsonObjectAsJsonObjectOrNull;
            if (c0Var != null) {
                obj = c0Var;
            }
        }
        boolean z = obj instanceof x6.c0;
        Object obj2 = obj;
        if (z) {
            obj2 = null;
        }
        JsonObject jsonObject = (JsonObject) obj2;
        String strString = jsonObject != null ? string(jsonObject, "friendlyName") : null;
        if (strString == null) {
            strString = "";
        }
        String strString2 = jsonObject != null ? string(jsonObject, "machineIdentifier") : null;
        String str = strString2 != null ? strString2 : "";
        return (kotlin.text.o.h0(strString) && kotlin.text.o.h0(str)) ? new x6.x(xmlAttribute(body, "friendlyName"), xmlAttribute(body, "machineIdentifier")) : new x6.x(strString, str);
    }

    private final List<PlexResourceDevice> parsePlexResourcesXml(String xml) {
        return ga.r.U(ga.r.I(new ga.t(kotlin.text.m.c(xml, HomeServerRegexes.INSTANCE.getPLEX_DEVICE_REGEX()), new e(this, 0)), new f(0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlexResourceDevice parsePlexResourcesXml$lambda$0(HomeServerRepository homeServerRepository, kotlin.text.i iVar) {
        List list;
        List listSingletonList;
        String str = (String) kotlin.collections.x.p0(1, iVar.b());
        if (str == null) {
            str = "";
        }
        if (kotlin.text.o.h0(str) && (str = (String) kotlin.collections.x.p0(3, iVar.b())) == null) {
            str = "";
        }
        String str2 = (String) kotlin.collections.x.p0(2, iVar.b());
        String str3 = str2 != null ? str2 : "";
        String strXmlAttribute = homeServerRepository.xmlAttribute(str, ContentDisposition.Parameters.Name);
        String strXmlAttribute2 = homeServerRepository.xmlAttribute(str, "product");
        String strXmlAttribute3 = homeServerRepository.xmlAttribute(str, "provides");
        String strXmlAttribute4 = homeServerRepository.xmlAttribute(str, "clientIdentifier");
        String strXmlAttribute5 = homeServerRepository.xmlAttribute(str, "accessToken");
        boolean zXmlBooleanAttribute = homeServerRepository.xmlBooleanAttribute(str, "owned");
        ga.b bVar = new ga.b(new ga.h(ga.r.I(new ga.t(kotlin.text.m.c(str3, HomeServerRegexes.INSTANCE.getPLEX_CONNECTION_REGEX()), new e(homeServerRepository, 2)), new f(1))), new f(2));
        if (bVar.hasNext()) {
            Object next = bVar.next();
            if (bVar.hasNext()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(next);
                while (bVar.hasNext()) {
                    arrayList.add(bVar.next());
                }
                list = arrayList;
                return new PlexResourceDevice(strXmlAttribute, strXmlAttribute2, strXmlAttribute3, strXmlAttribute4, strXmlAttribute5, zXmlBooleanAttribute, list);
            }
            listSingletonList = Collections.singletonList(next);
        } else {
            listSingletonList = kotlin.collections.z.f19728i;
        }
        list = listSingletonList;
        return new PlexResourceDevice(strXmlAttribute, strXmlAttribute2, strXmlAttribute3, strXmlAttribute4, strXmlAttribute5, zXmlBooleanAttribute, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlexResourceConnection parsePlexResourcesXml$lambda$0$1(HomeServerRepository homeServerRepository, kotlin.text.i iVar) {
        String str = (String) kotlin.collections.x.p0(1, iVar.b());
        if (str == null) {
            str = "";
        }
        return new PlexResourceConnection(homeServerRepository.normalizeServerUrl(homeServerRepository.xmlAttribute(str, "uri")), homeServerRepository.xmlBooleanAttribute(str, "local"), homeServerRepository.xmlBooleanAttribute(str, "relay"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean parsePlexResourcesXml$lambda$0$2(PlexResourceConnection plexResourceConnection) {
        return !kotlin.text.o.h0(plexResourceConnection.getUri());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String parsePlexResourcesXml$lambda$0$3(PlexResourceConnection plexResourceConnection) {
        return plexResourceConnection.getUri().toLowerCase(Locale.US);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean parsePlexResourcesXml$lambda$1(PlexResourceDevice plexResourceDevice) {
        return plexResourceDevice.isServer() && !kotlin.text.o.h0(plexResourceDevice.getClientIdentifier());
    }

    private final Map<String, String> playbackHeaders(HomeServerConnection connection) {
        return connection.getServerKind() == HomeServerKind.PLEX ? plexHeaders(connection.getAccessToken()) : kotlin.collections.h0.t0(new x6.x("User-Agent", "ARVIO/1.9.995"), new x6.x("X-Emby-Authorization", authHeader(connection.getAccessToken())), new x6.x("X-Emby-Token", connection.getAccessToken()));
    }

    private final String playbackUrl(HomeServerMediaSource homeServerMediaSource, HomeServerConnection homeServerConnection, String str) {
        gb.b0 b0VarD = null;
        if (homeServerConnection.getServerKind() == HomeServerKind.PLEX) {
            String key = homeServerMediaSource.getKey();
            if (kotlin.text.o.h0(key)) {
                key = null;
            }
            if (key != null) {
                return plexUrlWithToken(homeServerConnection, absoluteUrl(homeServerConnection.getServerUrl(), key));
            }
            String path = homeServerMediaSource.getPath();
            if (!kotlin.text.u.P(path, "http://", true) && !kotlin.text.u.P(path, "https://", true)) {
                path = null;
            }
            if (path != null) {
                return plexUrlWithToken(homeServerConnection, path);
            }
            String id = homeServerMediaSource.getId();
            if (kotlin.text.o.h0(id)) {
                id = null;
            }
            if (id != null) {
                return buildUrl(homeServerConnection.getServerUrl(), a0.c.l("/library/parts/", id, "/file"), Collections.singletonMap("X-Plex-Token", homeServerConnection.getAccessToken()));
            }
            return null;
        }
        String path2 = homeServerMediaSource.getPath();
        if (!kotlin.text.u.P(path2, "http://", true) && !kotlin.text.u.P(path2, "https://", true)) {
            path2 = null;
        }
        if (path2 != null) {
            return path2;
        }
        String transcodingUrl = homeServerMediaSource.getTranscodingUrl();
        if (kotlin.text.o.h0(transcodingUrl)) {
            transcodingUrl = null;
        }
        if (transcodingUrl == null) {
            String strStreamExtension = streamExtension(homeServerMediaSource);
            String strN = strStreamExtension != null ? androidx.compose.material3.d.n("/Videos/", str, "/stream.", strStreamExtension) : a0.c.l("/Videos/", str, "/stream");
            String serverUrl = homeServerConnection.getServerUrl();
            x6.x xVar = new x6.x("Static", "true");
            x6.x xVar2 = new x6.x("MediaSourceId", homeServerMediaSource.getId());
            x6.x xVar3 = new x6.x("DeviceId", deviceId());
            x6.x xVar4 = new x6.x("api_key", homeServerConnection.getAccessToken());
            String eTag = homeServerMediaSource.getETag();
            return buildUrl(serverUrl, strN, kotlin.collections.h0.t0(xVar, xVar2, xVar3, xVar4, new x6.x("Tag", kotlin.text.o.h0(eTag) ? null : eTag)));
        }
        String strAbsoluteUrl = absoluteUrl(homeServerConnection.getServerUrl(), transcodingUrl);
        try {
            androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
            kVar.i(null, strAbsoluteUrl);
            b0VarD = kVar.d();
        } catch (IllegalArgumentException unused) {
        }
        if (b0VarD == null) {
            return strAbsoluteUrl;
        }
        androidx.tv.foundation.lazy.list.k kVarF = b0VarD.f();
        String strG = b0VarD.g("api_key");
        if (strG == null || kotlin.text.o.h0(strG)) {
            kVarF.c("api_key", homeServerConnection.getAccessToken());
        }
        return kVarF.d().f15625i;
    }

    private final String plexActivationUrl(String code) {
        gb.b0 b0VarD;
        try {
            androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
            kVar.i(null, "https://app.plex.tv/auth");
            b0VarD = kVar.d();
        } catch (IllegalArgumentException unused) {
            b0VarD = null;
        }
        if (b0VarD == null) {
            return "https://app.plex.tv/auth";
        }
        androidx.tv.foundation.lazy.list.k kVarF = b0VarD.f();
        String strC = androidx.compose.material3.d.C("!?", kotlin.collections.x.u0(t7.a.E(androidx.compose.material3.d.C("clientID=", deviceId()), androidx.compose.material3.d.C("code=", code), "context[device][product]=ARVIO"), "&", null, null, null, 62));
        kVarF.f5415i = strC != null ? gb.u.c(0, 0, 187, strC, "", false) : null;
        return kVarF.d().f15625i;
    }

    private final List<String> plexCandidateServerUrls(String preferredServerUrl, PlexResourceDevice device) {
        List<PlexResourceConnection> connections;
        z6.c cVarN = t7.a.n();
        if (kotlin.text.o.h0(preferredServerUrl)) {
            preferredServerUrl = null;
        }
        if (preferredServerUrl != null) {
            cVarN.add(preferredServerUrl);
        }
        if (device != null && (connections = device.getConnections()) != null) {
            final Comparator comparator = new Comparator() { // from class: com.arflix.tv.data.repository.HomeServerRepository$plexCandidateServerUrls$lambda$0$$inlined$compareByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    HomeServerRepository.PlexResourceConnection plexResourceConnection = (HomeServerRepository.PlexResourceConnection) t10;
                    boolean z = false;
                    Boolean boolValueOf = Boolean.valueOf(plexResourceConnection.getLocal() && !plexResourceConnection.getRelay());
                    HomeServerRepository.PlexResourceConnection plexResourceConnection2 = (HomeServerRepository.PlexResourceConnection) t2;
                    if (plexResourceConnection2.getLocal() && !plexResourceConnection2.getRelay()) {
                        z = true;
                    }
                    return r0.e(boolValueOf, Boolean.valueOf(z));
                }
            };
            final Comparator comparator2 = new Comparator() { // from class: com.arflix.tv.data.repository.HomeServerRepository$plexCandidateServerUrls$lambda$0$$inlined$thenBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    int iCompare = comparator.compare(t2, t10);
                    return iCompare != 0 ? iCompare : r0.e(Boolean.valueOf(((HomeServerRepository.PlexResourceConnection) t2).getRelay()), Boolean.valueOf(((HomeServerRepository.PlexResourceConnection) t10).getRelay()));
                }
            };
            Iterator it = kotlin.collections.x.W0(connections, new Comparator() { // from class: com.arflix.tv.data.repository.HomeServerRepository$plexCandidateServerUrls$lambda$0$$inlined$thenByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    int iCompare = comparator2.compare(t2, t10);
                    return iCompare != 0 ? iCompare : r0.e(Boolean.valueOf(kotlin.text.u.P(((HomeServerRepository.PlexResourceConnection) t10).getUri(), "https://", true)), Boolean.valueOf(kotlin.text.u.P(((HomeServerRepository.PlexResourceConnection) t2).getUri(), "https://", true)));
                }
            }).iterator();
            while (it.hasNext()) {
                cVarN.add(((PlexResourceConnection) it.next()).getUri());
            }
        }
        z6.c cVarE = t7.a.e(cVarN);
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(cVarE, 10));
        ListIterator listIterator = cVarE.listIterator(0);
        while (true) {
            z6.a aVar = (z6.a) listIterator;
            if (!aVar.hasNext()) {
                break;
            }
            arrayList.add(normalizeServerUrl((String) aVar.next()));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (!kotlin.text.o.h0((String) obj)) {
                arrayList2.add(obj);
            }
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : arrayList2) {
            if (hashSet.add(((String) obj2).toLowerCase(Locale.US))) {
                arrayList3.add(obj2);
            }
        }
        return arrayList3;
    }

    private final String plexCompatiblePlaybackUrl(HomeServerMediaSource homeServerMediaSource, HomeServerConnection homeServerConnection, String str) {
        gb.b0 b0VarD;
        if (!kotlin.text.o.h0(str)) {
            String serverUrl = homeServerConnection.getServerUrl();
            try {
                androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
                kVar.i(null, serverUrl);
                b0VarD = kVar.d();
            } catch (IllegalArgumentException unused) {
                b0VarD = null;
            }
            if (b0VarD != null) {
                androidx.tv.foundation.lazy.list.k kVarF = b0VarD.f();
                if (!kotlin.text.u.P("/video/:/transcode/universal/start.m3u8", DomExceptionUtils.SEPARATOR, false)) {
                    throw new IllegalArgumentException("unexpected encodedPath: ".concat("/video/:/transcode/universal/start.m3u8").toString());
                }
                kVarF.k(0, 39, "/video/:/transcode/universal/start.m3u8");
                kVarF.c("path", "/library/metadata/".concat(str));
                kVarF.c("mediaIndex", String.valueOf(homeServerMediaSource.getMediaIndex()));
                kVarF.c("partIndex", String.valueOf(homeServerMediaSource.getPartIndex()));
                kVarF.c("protocol", "hls");
                kVarF.c("directPlay", "0");
                kVarF.c("directStream", "0");
                kVarF.c("videoQuality", "100");
                kVarF.c("maxVideoBitrate", "20000");
                kVarF.c("subtitleSize", "100");
                kVarF.c("audioBoost", "100");
                String strDeviceId = deviceId();
                String id = homeServerMediaSource.getId();
                if (kotlin.text.o.h0(id)) {
                    id = String.valueOf(homeServerMediaSource.getPartIndex());
                }
                StringBuilder sbR = a2.r("arvio-", strDeviceId, "-", str, "-");
                sbR.append((Object) id);
                kVarF.c(SettingsSessionManager.SETTINGS_KEY, sbR.toString());
                kVarF.c("X-Plex-Token", homeServerConnection.getAccessToken());
                return kVarF.d().f15625i;
            }
        }
        return null;
    }

    private final List<String> plexGuidQueries(String providerId) {
        String lowerCase = kotlin.text.o.G0('.', providerId, "").toLowerCase(Locale.US);
        String string = kotlin.text.o.L0(kotlin.text.o.C0('.', providerId, "")).toString();
        boolean zH0 = kotlin.text.o.h0(lowerCase);
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        if (zH0 || kotlin.text.o.h0(string)) {
            return zVar;
        }
        int iHashCode = lowerCase.hashCode();
        if (iHashCode != 3236002) {
            if (iHashCode != 3563703) {
                if (iHashCode == 3572352 && lowerCase.equals("tvdb")) {
                    return Collections.singletonList("tvdb://" + string);
                }
            } else if (lowerCase.equals("tmdb")) {
                return Collections.singletonList("tmdb://" + string);
            }
        } else if (lowerCase.equals("imdb")) {
            return Collections.singletonList("imdb://" + string);
        }
        return zVar;
    }

    private final Map<String, String> plexHeaders(String token) {
        z6.f fVar = new z6.f();
        fVar.put("Accept", "application/json");
        fVar.put("User-Agent", "ARVIO/1.9.995");
        fVar.put("X-Plex-Client-Identifier", deviceId());
        fVar.put("X-Plex-Product", BuildConfig.GITHUB_REPO);
        fVar.put("X-Plex-Version", BuildConfig.VERSION_NAME);
        fVar.put("X-Plex-Device", "Android");
        fVar.put("X-Plex-Platform", "Android");
        if (token != null) {
            if (kotlin.text.o.h0(token)) {
                token = null;
            }
            if (token != null) {
                fVar.put("X-Plex-Token", token);
            }
        }
        return fVar.c();
    }

    public static /* synthetic */ Map plexHeaders$default(HomeServerRepository homeServerRepository, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = null;
        }
        return homeServerRepository.plexHeaders(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final gb.a0 plexPublicHeaders() {
        gb.z zVar = new gb.z();
        zVar.a("Accept", "application/json");
        zVar.a("User-Agent", "ARVIO/1.9.995");
        zVar.a("X-Plex-Client-Identifier", deviceId());
        zVar.a("X-Plex-Product", BuildConfig.GITHUB_REPO);
        zVar.a("X-Plex-Version", BuildConfig.VERSION_NAME);
        zVar.a("X-Plex-Device", "Android");
        zVar.a("X-Plex-Platform", "Android");
        return zVar.e();
    }

    private final String plexUrlWithToken(HomeServerConnection connection, String rawUrl) {
        String strG;
        gb.b0 b0VarD = null;
        try {
            androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
            kVar.i(null, rawUrl);
            b0VarD = kVar.d();
        } catch (IllegalArgumentException unused) {
        }
        if (b0VarD == null || ((strG = b0VarD.g("X-Plex-Token")) != null && !kotlin.text.o.h0(strG))) {
            return rawUrl;
        }
        androidx.tv.foundation.lazy.list.k kVarF = b0VarD.f();
        kVarF.c("X-Plex-Token", connection.getAccessToken());
        return kVarF.d().f15625i;
    }

    /* JADX INFO: renamed from: pollHomeServerCodeAuth-BWLJW6A$default, reason: not valid java name */
    public static /* synthetic */ Object m6131pollHomeServerCodeAuthBWLJW6A$default(HomeServerRepository homeServerRepository, PlexPinAuthSession plexPinAuthSession, String str, String str2, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str = "";
        }
        if ((i10 & 4) != 0) {
            str2 = "";
        }
        return homeServerRepository.m6134pollHomeServerCodeAuthBWLJW6A(plexPinAuthSession, str, str2, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object pollJellyfinQuickConnect(com.arflix.tv.data.repository.PlexPinAuthSession r38, java.lang.String r39, d7.d<? super com.arflix.tv.data.repository.HomeServerConnection> r40) {
        /*
            Method dump skipped, instruction units count: 328
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.pollJellyfinQuickConnect(com.arflix.tv.data.repository.PlexPinAuthSession, java.lang.String, d7.d):java.lang.Object");
    }

    private final JsonObject postJson(String url, JsonObject bodyJson, HomeServerConnection connection) {
        gb.j0 j0VarRequestBuilder = requestBuilder(url, connection);
        String json = this.gson.toJson((JsonElement) bodyJson);
        gb.d0 d0VarI0 = this.jsonMediaType;
        Charset charset = kotlin.text.a.f19924a;
        if (d0VarI0 != null) {
            Pattern pattern = gb.d0.f15633d;
            Charset charsetA = d0VarI0.a(null);
            if (charsetA == null) {
                try {
                    d0VarI0 = xc.d.i0(d0VarI0 + "; charset=utf-8");
                } catch (IllegalArgumentException unused) {
                    d0VarI0 = null;
                }
            } else {
                charset = charsetA;
            }
        }
        byte[] bytes = json.getBytes(charset);
        int length = bytes.length;
        ib.c.c(bytes.length, 0, length);
        j0VarRequestBuilder.f(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, new gb.m0(d0VarI0, length, bytes));
        j0VarRequestBuilder.f15724c.h(HttpConnection.CONTENT_TYPE, "application/json");
        gb.p0 p0VarD = this.okHttpClient.a(j0VarRequestBuilder.b()).d();
        try {
            s0 s0Var = p0VarD.f15786q;
            String strX = s0Var != null ? s0Var.x() : null;
            if (strX == null) {
                strX = "";
            }
            if (!p0VarD.k()) {
                throw new IllegalStateException(this.context.getString(R.string.homeserver_signin_failed_code, Integer.valueOf(p0VarD.f15783n)).toString());
            }
            JsonObject jsonObjectAsJsonObjectOrNull = asJsonObjectOrNull(new JsonParser().parse(strX));
            if (jsonObjectAsJsonObjectOrNull == null) {
                jsonObjectAsJsonObjectOrNull = new JsonObject();
            }
            p0VarD.close();
            return jsonObjectAsJsonObjectOrNull;
        } finally {
        }
    }

    public static /* synthetic */ JsonObject postJson$default(HomeServerRepository homeServerRepository, String str, JsonObject jsonObject, HomeServerConnection homeServerConnection, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            homeServerConnection = null;
        }
        return homeServerRepository.postJson(str, jsonObject, homeServerConnection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> providerQueries(String imdbId, Integer tmdbId, Integer tvdbId) {
        String string;
        z6.c cVarN = t7.a.n();
        if (imdbId != null && (string = kotlin.text.o.L0(imdbId).toString()) != null) {
            if (kotlin.text.o.h0(string)) {
                string = null;
            }
            if (string != null) {
                cVarN.add("imdb.".concat(string));
                cVarN.add("Imdb.".concat(string));
            }
        }
        if (tmdbId != null) {
            if (tmdbId.intValue() <= 0) {
                tmdbId = null;
            }
            if (tmdbId != null) {
                int iIntValue = tmdbId.intValue();
                cVarN.add("tmdb." + iIntValue);
                cVarN.add("Tmdb." + iIntValue);
            }
        }
        if (tvdbId != null) {
            if (tvdbId.intValue() <= 0) {
                tvdbId = null;
            }
            if (tvdbId != null) {
                int iIntValue2 = tvdbId.intValue();
                cVarN.add("tvdb." + iIntValue2);
                cVarN.add("Tvdb." + iIntValue2);
            }
        }
        return kotlin.collections.x.g0(t7.a.e(cVarN));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void putCachedSources(String key, List<StreamSource> sources) {
        synchronized (this.sourceCacheLock) {
            this.sourceCache.put(key, new CachedHomeServerSources(sources, System.currentTimeMillis()));
        }
    }

    private final String qualityLabel(HomeServerMediaSource source) {
        int videoHeight = source.getVideoHeight();
        return (videoHeight >= 2160 || source.getVideoWidth() >= 3800) ? "4K" : videoHeight >= 1440 ? "1440p" : videoHeight >= 1080 ? "1080p" : videoHeight >= 720 ? "720p" : videoHeight >= 576 ? "576p" : videoHeight >= 480 ? "480p" : videoHeight > 0 ? a2.i(videoHeight, TtmlNode.TAG_P) : extractQualityLabel(source.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int qualityRank(String quality) {
        String lowerCase = quality.toLowerCase(Locale.US);
        if (kotlin.text.o.T(lowerCase, "4k", false) || kotlin.text.o.T(lowerCase, "2160", false) || kotlin.text.o.T(lowerCase, "uhd", false)) {
            return 2160;
        }
        if (kotlin.text.o.T(lowerCase, "1440", false)) {
            return 1440;
        }
        if (kotlin.text.o.T(lowerCase, "1080", false)) {
            return 1080;
        }
        if (kotlin.text.o.T(lowerCase, "720", false)) {
            return 720;
        }
        if (kotlin.text.o.T(lowerCase, "576", false)) {
            return 576;
        }
        return kotlin.text.o.T(lowerCase, "480", false) ? 480 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<HomeServerItem> queryItems(HomeServerConnection connection, String itemTypes, Map<String, String> query) {
        return connection.getServerKind() == HomeServerKind.PLEX ? queryPlexItems(connection, itemTypes, query) : items(getJson(buildUrl(connection.getServerUrl(), a0.c.l("/Users/", connection.getUserId(), "/Items"), kotlin.collections.h0.v0(kotlin.collections.h0.t0(new x6.x("Recursive", "true"), new x6.x("IncludeItemTypes", itemTypes), new x6.x("Fields", itemFields())), query)), connection));
    }

    private final List<HomeServerItem> queryPlexByGuid(HomeServerConnection connection, List<HomeServerCollection> collections, String plexType, String guid, String limit) {
        Object c0Var;
        if (collections.isEmpty()) {
            List<HomeServerCollection> collections2 = connection.getCollections();
            ArrayList arrayList = new ArrayList();
            for (Object obj : collections2) {
                if (((HomeServerCollection) obj).getEnabled()) {
                    arrayList.add(obj);
                }
            }
            collections = arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        for (HomeServerCollection homeServerCollection : collections) {
            try {
                c0Var = metadataItems(getJson(buildUrl(connection.getServerUrl(), "/library/sections/" + homeServerCollection.getId() + "/all", kotlin.collections.h0.t0(new x6.x(LinkHeader.Parameters.Type, plexType), new x6.x("guid", guid), new x6.x("includeGuids", "1"), new x6.x("limit", limit))), connection), connection.getServerKind());
            } catch (Throwable th) {
                c0Var = new x6.c0(th);
            }
            if (c0Var instanceof x6.c0) {
                c0Var = kotlin.collections.z.f19728i;
            }
            kotlin.collections.x.b0(arrayList2, (Iterable) c0Var);
        }
        return arrayList2;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<com.arflix.tv.data.repository.HomeServerRepository.HomeServerItem> queryPlexItems(com.arflix.tv.data.repository.HomeServerConnection r18, java.lang.String r19, java.util.Map<java.lang.String, java.lang.String> r20) {
        /*
            Method dump skipped, instruction units count: 514
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.queryPlexItems(com.arflix.tv.data.repository.HomeServerConnection, java.lang.String, java.util.Map):java.util.List");
    }

    private final List<HomeServerItem> queryPlexSearch(HomeServerConnection connection, String searchTerm, String plexType, String limit) {
        Object c0Var;
        try {
            c0Var = metadataItems(getJson(buildUrl(connection.getServerUrl(), "/search", kotlin.collections.h0.t0(new x6.x("query", searchTerm), new x6.x(LinkHeader.Parameters.Type, plexType), new x6.x("includeGuids", "1"), new x6.x("limit", limit))), connection), connection.getServerKind());
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (c0Var instanceof x6.c0) {
            c0Var = kotlin.collections.z.f19728i;
        }
        return (List) c0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HomeServerConnection refreshConnection(HomeServerConnection connection) throws Exception {
        HomeServerKind homeServerKind;
        if (!connection.isUsable()) {
            throw new IllegalArgumentException(this.context.getString(R.string.homeserver_disabled_incomplete).toString());
        }
        HomeServerKind serverKind = connection.getServerKind();
        HomeServerKind homeServerKind2 = HomeServerKind.PLEX;
        if (serverKind == homeServerKind2) {
            String accountToken = connection.getAccountToken();
            if (kotlin.text.o.h0(accountToken)) {
                accountToken = connection.getAccessToken();
            }
            HomeServerConnection homeServerConnectionBuildPlexConnection = buildPlexConnection(accountToken, connection.getServerUrl(), connection.getUserName(), new ServerInfo(connection.getServerName(), connection.getServerId(), "Media Server", homeServerKind2), connection.getDisplayName());
            boolean enabled = connection.getEnabled();
            String connectionId = connection.getConnectionId();
            if (kotlin.text.o.h0(connectionId)) {
                connectionId = homeServerConnectionBuildPlexConnection.getConnectionId();
            }
            String str = connectionId;
            String displayName = connection.getDisplayName();
            if (kotlin.text.o.h0(displayName)) {
                displayName = homeServerConnectionBuildPlexConnection.getDisplayName();
            }
            return homeServerConnectionBuildPlexConnection.copy((PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 1) != 0 ? homeServerConnectionBuildPlexConnection.enabled : enabled, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 2) != 0 ? homeServerConnectionBuildPlexConnection.connectionId : str, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 4) != 0 ? homeServerConnectionBuildPlexConnection.serverUrl : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 8) != 0 ? homeServerConnectionBuildPlexConnection.displayName : displayName, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 16) != 0 ? homeServerConnectionBuildPlexConnection.serverName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 32) != 0 ? homeServerConnectionBuildPlexConnection.serverKind : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 64) != 0 ? homeServerConnectionBuildPlexConnection.serverId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 128) != 0 ? homeServerConnectionBuildPlexConnection.userId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 256) != 0 ? homeServerConnectionBuildPlexConnection.userName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 512) != 0 ? homeServerConnectionBuildPlexConnection.accessToken : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 1024) != 0 ? homeServerConnectionBuildPlexConnection.accountToken : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 2048) != 0 ? homeServerConnectionBuildPlexConnection.collections : mergeCollectionStates(homeServerConnectionBuildPlexConnection.getCollections(), connection.getCollections()), (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 4096) != 0 ? homeServerConnectionBuildPlexConnection.lastConnectedAt : 0L);
        }
        ServerInfo serverInfoFetchSystemInfo = fetchSystemInfo(connection);
        HomeServerKind serverKind2 = serverInfoFetchSystemInfo.getServerKind();
        HomeServerKind homeServerKind3 = HomeServerKind.UNKNOWN;
        if (serverKind2 == homeServerKind3) {
            serverKind2 = null;
        }
        if (serverKind2 != null) {
            homeServerKind = serverKind2;
        } else {
            HomeServerKind homeServerKindDetectServerKind = detectServerKind(serverInfoFetchSystemInfo.getProductName(), serverInfoFetchSystemInfo.getServerName());
            HomeServerKind homeServerKind4 = homeServerKindDetectServerKind != homeServerKind3 ? homeServerKindDetectServerKind : null;
            if (homeServerKind4 == null) {
                serverKind2 = connection.getServerKind();
                homeServerKind = serverKind2;
            } else {
                homeServerKind = homeServerKind4;
            }
        }
        String serverName = serverInfoFetchSystemInfo.getServerName();
        if (kotlin.text.o.h0(serverName)) {
            serverName = connection.getServerName();
        }
        String str2 = serverName;
        String serverId = serverInfoFetchSystemInfo.getServerId();
        if (kotlin.text.o.h0(serverId)) {
            serverId = connection.getServerId();
        }
        HomeServerConnection homeServerConnectionCopy = connection.copy((PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 1) != 0 ? connection.enabled : false, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 2) != 0 ? connection.connectionId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 4) != 0 ? connection.serverUrl : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 8) != 0 ? connection.displayName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 16) != 0 ? connection.serverName : str2, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 32) != 0 ? connection.serverKind : homeServerKind, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 64) != 0 ? connection.serverId : serverId, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 128) != 0 ? connection.userId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 256) != 0 ? connection.userName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 512) != 0 ? connection.accessToken : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 1024) != 0 ? connection.accountToken : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 2048) != 0 ? connection.collections : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 4096) != 0 ? connection.lastConnectedAt : System.currentTimeMillis());
        List<HomeServerCollection> listFetchCollections = fetchCollections(homeServerConnectionCopy);
        if (listFetchCollections.isEmpty()) {
            listFetchCollections = connection.getCollections();
        }
        return homeServerConnectionCopy.copy((PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 1) != 0 ? homeServerConnectionCopy.enabled : false, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 2) != 0 ? homeServerConnectionCopy.connectionId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 4) != 0 ? homeServerConnectionCopy.serverUrl : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 8) != 0 ? homeServerConnectionCopy.displayName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 16) != 0 ? homeServerConnectionCopy.serverName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 32) != 0 ? homeServerConnectionCopy.serverKind : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 64) != 0 ? homeServerConnectionCopy.serverId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 128) != 0 ? homeServerConnectionCopy.userId : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 256) != 0 ? homeServerConnectionCopy.userName : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 512) != 0 ? homeServerConnectionCopy.accessToken : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 1024) != 0 ? homeServerConnectionCopy.accountToken : null, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 2048) != 0 ? homeServerConnectionCopy.collections : listFetchCollections, (PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED & 4096) != 0 ? homeServerConnectionCopy.lastConnectedAt : 0L);
    }

    private final gb.j0 requestBuilder(String url, HomeServerConnection connection) {
        gb.j0 j0Var = new gb.j0();
        j0Var.i(url);
        j0Var.f15724c.h("Accept", "application/json");
        j0Var.f15724c.h("User-Agent", "ARVIO/1.9.995");
        if ((connection != null ? connection.getServerKind() : null) == HomeServerKind.PLEX) {
            for (Map.Entry<String, String> entry : plexHeaders(connection.getAccessToken()).entrySet()) {
                j0Var.e(entry.getKey(), entry.getValue());
            }
        } else {
            j0Var.f15724c.h("X-Emby-Authorization", authHeader(connection != null ? connection.getAccessToken() : null));
            if (connection != null) {
                j0Var.f15724c.h("X-Emby-Token", connection.getAccessToken());
            }
        }
        return j0Var;
    }

    public static /* synthetic */ gb.j0 requestBuilder$default(HomeServerRepository homeServerRepository, String str, HomeServerConnection homeServerConnection, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            homeServerConnection = null;
        }
        return homeServerRepository.requestBuilder(str, homeServerConnection);
    }

    private final String resolvePlexServerToken(String accountToken, String serverId) {
        Object next;
        if (kotlin.text.o.h0(serverId)) {
            return "";
        }
        Iterator<T> it = fetchPlexResources(accountToken).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (kotlin.jvm.internal.p.a(((PlexResourceDevice) next).getClientIdentifier(), serverId)) {
                break;
            }
        }
        PlexResourceDevice plexResourceDevice = (PlexResourceDevice) next;
        String accessToken = plexResourceDevice != null ? plexResourceDevice.getAccessToken() : null;
        return accessToken == null ? "" : accessToken;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resolveSourcesCached(String str, r7.l<? super d7.d<? super List<StreamSource>>, ? extends Object> lVar, d7.d<? super List<StreamSource>> dVar) {
        synchronized (this.sourceCacheLock) {
            List<StreamSource> cachedSourcesLocked = getCachedSourcesLocked(str);
            if (cachedSourcesLocked != null) {
                return cachedSourcesLocked;
            }
            ka.p0<List<StreamSource>> p0VarE = this.sourceRequests.get(str);
            if (p0VarE == null) {
                p0VarE = ka.m0.e(3, null, this.sourceRequestScope, new HomeServerRepository$resolveSourcesCached$request$1$2(lVar, this, str, null));
                this.sourceRequests.put(str, p0VarE);
                p0VarE.invokeOnCompletion(new n(2, this, p0VarE, str));
            }
            return p0VarE.o(dVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 resolveSourcesCached$lambda$0$1$0(HomeServerRepository homeServerRepository, String str, ka.p0 p0Var, Throwable th) {
        synchronized (homeServerRepository.sourceCacheLock) {
            if (homeServerRepository.sourceRequests.get(str) == p0Var) {
                homeServerRepository.sourceRequests.remove(str);
            }
        }
        return t0.f22605a;
    }

    private final boolean sameServerEndpoint(String left, String right) {
        gb.b0 b0VarD;
        gb.b0 b0VarD2 = null;
        try {
            androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
            kVar.i(null, left);
            b0VarD = kVar.d();
        } catch (IllegalArgumentException unused) {
            b0VarD = null;
        }
        if (b0VarD != null) {
            try {
                androidx.tv.foundation.lazy.list.k kVar2 = new androidx.tv.foundation.lazy.list.k(1);
                kVar2.i(null, right);
                b0VarD2 = kVar2.d();
            } catch (IllegalArgumentException unused2) {
            }
            return b0VarD2 != null && kotlin.text.u.L(b0VarD.f15620d, b0VarD2.f15620d, true) && b0VarD.f15621e == b0VarD2.f15621e;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HomeServerConnection sanitized(HomeServerConnection homeServerConnection) {
        boolean enabled = homeServerConnection.getEnabled();
        String connectionId = homeServerConnection.getConnectionId();
        String str = "";
        if (connectionId == null) {
            connectionId = "";
        }
        if (kotlin.text.o.h0(connectionId)) {
            String serverUrl = homeServerConnection.getServerUrl();
            if (serverUrl == null) {
                serverUrl = "";
            }
            HomeServerKind serverKind = homeServerConnection.getServerKind();
            String userId = homeServerConnection.getUserId();
            if (userId == null) {
                userId = "";
            }
            if (kotlin.text.o.h0(userId) && (userId = homeServerConnection.getUserName()) == null) {
                userId = "";
            }
            connectionId = createConnectionId(serverUrl, serverKind, userId);
        }
        String serverUrl2 = homeServerConnection.getServerUrl();
        if (serverUrl2 == null) {
            serverUrl2 = "";
        }
        String strNormalizeServerUrl = normalizeServerUrl(serverUrl2);
        String displayName = homeServerConnection.getDisplayName();
        if (displayName == null) {
            displayName = "";
        }
        String serverName = homeServerConnection.getServerName();
        if (serverName == null) {
            serverName = "";
        }
        HomeServerKind serverKind2 = homeServerConnection.getServerKind();
        String serverId = homeServerConnection.getServerId();
        if (serverId == null) {
            serverId = "";
        }
        String userId2 = homeServerConnection.getUserId();
        if (userId2 == null) {
            userId2 = "";
        }
        String userName = homeServerConnection.getUserName();
        if (userName == null) {
            userName = "";
        }
        String accessToken = homeServerConnection.getAccessToken();
        if (accessToken == null) {
            accessToken = "";
        }
        String accountToken = homeServerConnection.getAccountToken();
        if (accountToken == null) {
            accountToken = "";
        }
        List<HomeServerCollection> collections = homeServerConnection.getCollections();
        if (collections == null) {
            collections = kotlin.collections.z.f19728i;
        }
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(collections, 10));
        for (HomeServerCollection homeServerCollection : collections) {
            String id = homeServerCollection.getId();
            String str2 = connectionId;
            String str3 = id == null ? str : id;
            String name = homeServerCollection.getName();
            boolean z = enabled;
            String str4 = name == null ? str : name;
            String type = homeServerCollection.getType();
            String str5 = str;
            if (type != null) {
                str = type;
            }
            arrayList.add(new HomeServerCollection(str3, str4, str, homeServerCollection.getEnabled()));
            connectionId = str2;
            enabled = z;
            str = str5;
        }
        return new HomeServerConnection(enabled, connectionId, strNormalizeServerUrl, displayName, serverName, serverKind2, serverId, userId2, userName, accessToken, accountToken, arrayList, homeServerConnection.getLastConnectedAt());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b6, code lost:
    
        if (saveConnections(r10, r0) == r4) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object saveConnection(com.arflix.tv.data.repository.HomeServerConnection r10, d7.d<? super x6.t0> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.arflix.tv.data.repository.HomeServerRepository.C07551
            if (r0 == 0) goto L13
            r0 = r11
            com.arflix.tv.data.repository.HomeServerRepository$saveConnection$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository.C07551) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$saveConnection$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$saveConnection$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L47
            if (r1 == r3) goto L3f
            if (r1 != r2) goto L37
            java.lang.Object r10 = r0.L$2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r10 = r0.L$1
            java.util.List r10 = (java.util.List) r10
            java.lang.Object r10 = r0.L$0
            com.arflix.tv.data.repository.HomeServerConnection r10 = (com.arflix.tv.data.repository.HomeServerConnection) r10
            k2.c.G(r11)
            goto Lb9
        L37:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L3f:
            java.lang.Object r10 = r0.L$0
            com.arflix.tv.data.repository.HomeServerConnection r10 = (com.arflix.tv.data.repository.HomeServerConnection) r10
            k2.c.G(r11)
            goto L58
        L47:
            k2.c.G(r11)
            r9.clearSourceCache()
            r0.L$0 = r10
            r0.label = r3
            java.lang.Object r11 = r9.currentConnections(r0)
            if (r11 != r4) goto L58
            goto Lb8
        L58:
            java.util.List r11 = (java.util.List) r11
            java.lang.String r1 = r9.connectionIdentity(r10)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r11 = r11.iterator()
        L67:
            boolean r5 = r11.hasNext()
            if (r5 == 0) goto La5
            java.lang.Object r5 = r11.next()
            r6 = r5
            com.arflix.tv.data.repository.HomeServerConnection r6 = (com.arflix.tv.data.repository.HomeServerConnection) r6
            java.lang.String r7 = r9.connectionIdentity(r6)
            boolean r7 = kotlin.jvm.internal.p.a(r7, r1)
            if (r7 != 0) goto L67
            com.arflix.tv.data.repository.HomeServerKind r7 = r10.getServerKind()
            com.arflix.tv.data.repository.HomeServerKind r8 = r6.getServerKind()
            if (r7 != r8) goto La1
            java.lang.String r7 = r10.getServerId()
            boolean r7 = kotlin.text.o.h0(r7)
            if (r7 != 0) goto La1
            java.lang.String r7 = r10.getServerId()
            java.lang.String r6 = r6.getServerId()
            boolean r6 = kotlin.jvm.internal.p.a(r7, r6)
            if (r6 == 0) goto La1
            goto L67
        La1:
            r3.add(r5)
            goto L67
        La5:
            java.util.ArrayList r10 = kotlin.collections.x.H0(r10, r3)
            r11 = 0
            r0.L$0 = r11
            r0.L$1 = r11
            r0.L$2 = r11
            r0.label = r2
            java.lang.Object r10 = r9.saveConnections(r10, r0)
            if (r10 != r4) goto Lb9
        Lb8:
            return r4
        Lb9:
            x6.t0 r10 = x6.t0.f22605a
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.saveConnection(com.arflix.tv.data.repository.HomeServerConnection, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0063, code lost:
    
        if (r6.saveConnectionsForProfile((java.lang.String) r7, r1, r0) == r4) goto L22;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object saveConnections(java.util.List<com.arflix.tv.data.repository.HomeServerConnection> r6, d7.d<? super x6.t0> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.HomeServerRepository.C07561
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.HomeServerRepository$saveConnections$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository.C07561) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$saveConnections$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$saveConnections$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L42
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r6 = r0.L$0
            java.util.List r6 = (java.util.List) r6
            k2.c.G(r7)
            goto L66
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L36:
            java.lang.Object r6 = r0.L$1
            com.arflix.tv.data.repository.HomeServerRepository r6 = (com.arflix.tv.data.repository.HomeServerRepository) r6
            java.lang.Object r1 = r0.L$0
            java.util.List r1 = (java.util.List) r1
            k2.c.G(r7)
            goto L56
        L42:
            k2.c.G(r7)
            com.arflix.tv.data.repository.ProfileManager r7 = r5.profileManager
            r0.L$0 = r6
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r7 = r7.getProfileId(r0)
            if (r7 != r4) goto L54
            goto L65
        L54:
            r1 = r6
            r6 = r5
        L56:
            java.lang.String r7 = (java.lang.String) r7
            r3 = 0
            r0.L$0 = r3
            r0.L$1 = r3
            r0.label = r2
            java.lang.Object r6 = r6.saveConnectionsForProfile(r7, r1, r0)
            if (r6 != r4) goto L66
        L65:
            return r4
        L66:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.saveConnections(java.util.List, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object saveConnectionsForProfile(String str, List<HomeServerConnection> list, d7.d<? super t0> dVar) {
        clearSourceCache();
        Object objO = androidx.work.impl.t.o(DataStoresKt.getSettingsDataStore(this.context), new C07572(str, list, null), dVar);
        return objO == e7.a.f15033i ? objO : t0.f22605a;
    }

    private final PlexResourceDevice selectPlexResourceDevice(List<PlexResourceDevice> resources, String preferredServerId, String preferredServerUrl) {
        Object next;
        Object next2;
        Object obj = null;
        if (resources.isEmpty()) {
            return null;
        }
        if (kotlin.text.o.h0(preferredServerId)) {
            preferredServerId = null;
        }
        if (preferredServerId != null) {
            Iterator<T> it = resources.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next2 = null;
                    break;
                }
                next2 = it.next();
                if (kotlin.jvm.internal.p.a(((PlexResourceDevice) next2).getClientIdentifier(), preferredServerId)) {
                    break;
                }
            }
            PlexResourceDevice plexResourceDevice = (PlexResourceDevice) next2;
            if (plexResourceDevice != null) {
                return plexResourceDevice;
            }
        }
        if (kotlin.text.o.h0(preferredServerUrl)) {
            preferredServerUrl = null;
        }
        if (preferredServerUrl != null) {
            Iterator<T> it2 = resources.iterator();
            loop1: while (true) {
                if (!it2.hasNext()) {
                    next = null;
                    break;
                }
                next = it2.next();
                List<PlexResourceConnection> connections = ((PlexResourceDevice) next).getConnections();
                if (connections == null || !connections.isEmpty()) {
                    Iterator<T> it3 = connections.iterator();
                    while (it3.hasNext()) {
                        if (sameServerEndpoint(((PlexResourceConnection) it3.next()).getUri(), preferredServerUrl)) {
                            break loop1;
                        }
                    }
                }
            }
            PlexResourceDevice plexResourceDevice2 = (PlexResourceDevice) next;
            if (plexResourceDevice2 != null) {
                return plexResourceDevice2;
            }
        }
        Iterator<T> it4 = resources.iterator();
        boolean z = false;
        Object obj2 = null;
        while (true) {
            if (it4.hasNext()) {
                Object next3 = it4.next();
                if (!kotlin.text.o.h0(((PlexResourceDevice) next3).getAccessToken())) {
                    if (z) {
                        break;
                    }
                    z = true;
                    obj2 = next3;
                }
            } else if (z) {
                obj = obj2;
            }
        }
        PlexResourceDevice plexResourceDevice3 = (PlexResourceDevice) obj;
        if (plexResourceDevice3 != null) {
            return plexResourceDevice3;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj3 : resources) {
            if (!kotlin.text.o.h0(((PlexResourceDevice) obj3).getAccessToken())) {
                arrayList.add(obj3);
            }
        }
        final Comparator comparator = new Comparator() { // from class: com.arflix.tv.data.repository.HomeServerRepository$selectPlexResourceDevice$$inlined$compareByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return r0.e(Boolean.valueOf(((HomeServerRepository.PlexResourceDevice) t10).getOwned()), Boolean.valueOf(((HomeServerRepository.PlexResourceDevice) t2).getOwned()));
            }
        };
        return (PlexResourceDevice) kotlin.collections.x.o0(kotlin.collections.x.W0(arrayList, new Comparator() { // from class: com.arflix.tv.data.repository.HomeServerRepository$selectPlexResourceDevice$$inlined$thenByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator.compare(t2, t10);
                return iCompare != 0 ? iCompare : r0.e(Boolean.valueOf(!((HomeServerRepository.PlexResourceDevice) t10).getConnections().isEmpty()), Boolean.valueOf(!((HomeServerRepository.PlexResourceDevice) t2).getConnections().isEmpty()));
            }
        }));
    }

    private final String serverHostLabel(String serverUrl) {
        gb.b0 b0VarD;
        String strJ = null;
        try {
            androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
            kVar.i(null, serverUrl);
            b0VarD = kVar.d();
        } catch (IllegalArgumentException unused) {
            b0VarD = null;
        }
        if (b0VarD != null) {
            strJ = b0VarD.f15620d;
            int i10 = kotlin.jvm.internal.p.a(b0VarD.f15617a, "https") ? 443 : 80;
            int i11 = b0VarD.f15621e;
            if (i11 != i10) {
                strJ = a2.j(i11, strJ, ":");
            }
        }
        return strJ == null ? "" : strJ;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String sourceCacheKey(String type, List<HomeServerConnection> connections, String title, Integer year, String imdbId, Integer tmdbId, Integer tvdbId, Integer season, Integer episode) {
        String strU0 = kotlin.collections.x.u0(connections, "|", null, null, new e(this, 1), 30);
        String strContentIdentity = HomeServerSourceCacheKey.INSTANCE.contentIdentity(title, year, imdbId, tmdbId, tvdbId);
        String strValueOf = season != null ? String.valueOf(season.intValue()) : null;
        if (strValueOf == null) {
            strValueOf = "";
        }
        String strValueOf2 = episode != null ? String.valueOf(episode.intValue()) : null;
        return kotlin.collections.x.u0(t7.a.E(type, strU0, strContentIdentity, strValueOf, strValueOf2 != null ? strValueOf2 : ""), "|", null, null, null, 62);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence sourceCacheKey$lambda$0(HomeServerRepository homeServerRepository, HomeServerConnection homeServerConnection) {
        List<HomeServerCollection> collections = homeServerConnection.getCollections();
        ArrayList arrayList = new ArrayList();
        for (Object obj : collections) {
            if (((HomeServerCollection) obj).getEnabled()) {
                arrayList.add(obj);
            }
        }
        String strU0 = kotlin.collections.x.u0(arrayList, ",", null, null, new f(23), 30);
        return homeServerRepository.connectionIdentity(homeServerConnection) + ":" + homeServerConnection.getLastConnectedAt() + ":" + strU0;
    }

    private final String specificHomeServerKindLabel(HomeServerKind kind) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
        if (i10 == 1) {
            return "Plex";
        }
        if (i10 == 2) {
            return "Jellyfin";
        }
        if (i10 == 3) {
            return "Emby";
        }
        if (i10 == 4) {
            return "";
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PlexPinAuthSession startJellyfinQuickConnect(String serverUrl) {
        JsonObject jsonObjectPostJson$default = postJson$default(this, buildUrl$default(this, serverUrl, "/QuickConnect/Initiate", null, 4, null), new JsonObject(), null, 4, null);
        String strString = string(jsonObjectPostJson$default, "Code");
        if (kotlin.text.o.h0(strString)) {
            strString = string(jsonObjectPostJson$default, "code");
        }
        String str = strString;
        String strString2 = string(jsonObjectPostJson$default, "Secret");
        if (kotlin.text.o.h0(strString2)) {
            strString2 = string(jsonObjectPostJson$default, "secret");
        }
        String str2 = strString2;
        if (kotlin.text.o.h0(str) || kotlin.text.o.h0(str2)) {
            throw new IllegalArgumentException(this.context.getString(R.string.homeserver_no_activation_code).toString());
        }
        return new PlexPinAuthSession(str2, str2, str, kotlin.text.o.O0(serverUrl, '/'), MediaError.DetailedErrorCode.TEXT_UNKNOWN, 5, HomeServerKind.JELLYFIN, serverUrl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PlexPinAuthSession startPlexPinAuthInternal() {
        gb.b0 b0VarD;
        try {
            androidx.tv.foundation.lazy.list.k kVar = new androidx.tv.foundation.lazy.list.k(1);
            kVar.i(null, "https://plex.tv/api/v2/pins");
            b0VarD = kVar.d();
        } catch (IllegalArgumentException unused) {
            b0VarD = null;
        }
        if (b0VarD == null) {
            throw new IllegalStateException(this.context.getString(R.string.homeserver_invalid_code_url).toString());
        }
        androidx.tv.foundation.lazy.list.k kVarF = b0VarD.f();
        kVarF.c("strong", "true");
        kVarF.c("X-Plex-Client-Identifier", deviceId());
        kVarF.c("X-Plex-Product", BuildConfig.GITHUB_REPO);
        String str = kVarF.d().f15625i;
        gb.j0 j0Var = new gb.j0();
        j0Var.i(str);
        long j10 = 0;
        ib.c.c(j10, j10, j10);
        j0Var.f(HlsMediaPlaylist.Interstitial.CUE_TRIGGER_POST, new gb.m0(null, 0, new byte[0]));
        j0Var.f15724c = plexPublicHeaders().c();
        gb.p0 p0VarD = this.okHttpClient.a(j0Var.b()).d();
        try {
            s0 s0Var = p0VarD.f15786q;
            String strX = s0Var != null ? s0Var.x() : null;
            if (strX == null) {
                strX = "";
            }
            if (!p0VarD.k()) {
                throw new IllegalStateException(this.context.getString(R.string.homeserver_code_signin_failed_code, Integer.valueOf(p0VarD.f15783n)).toString());
            }
            JsonObject jsonObjectAsJsonObjectOrNull = asJsonObjectOrNull(new JsonParser().parse(strX));
            if (jsonObjectAsJsonObjectOrNull == null) {
                jsonObjectAsJsonObjectOrNull = new JsonObject();
            }
            String strString = string(jsonObjectAsJsonObjectOrNull, TtmlNode.ATTR_ID);
            if (kotlin.text.o.h0(strString)) {
                strString = string(jsonObjectAsJsonObjectOrNull, "pinId");
            }
            String str2 = strString;
            String strString2 = string(jsonObjectAsJsonObjectOrNull, "code");
            if (kotlin.text.o.h0(str2) || kotlin.text.o.h0(strString2)) {
                throw new IllegalArgumentException(this.context.getString(R.string.homeserver_no_activation_code).toString());
            }
            String strPlexActivationUrl = plexActivationUrl(strString2);
            Integer numM6129int = m6129int(jsonObjectAsJsonObjectOrNull, "expiresIn");
            int iIntValue = (numM6129int == null && (numM6129int = m6129int(jsonObjectAsJsonObjectOrNull, "expires_in")) == null) ? MediaError.DetailedErrorCode.TEXT_UNKNOWN : numM6129int.intValue();
            Integer numM6129int2 = m6129int(jsonObjectAsJsonObjectOrNull, "interval");
            PlexPinAuthSession plexPinAuthSession = new PlexPinAuthSession(str2, null, strString2, strPlexActivationUrl, iIntValue, qb.d.n(numM6129int2 != null ? numM6129int2.intValue() : 5, 2, 15), HomeServerKind.PLEX, null, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, null);
            p0VarD.close();
            return plexPinAuthSession;
        } finally {
        }
    }

    private final String streamExtension(HomeServerMediaSource homeServerMediaSource) {
        String string;
        String str = (String) kotlin.collections.x.o0(kotlin.text.o.x0(homeServerMediaSource.getContainer(), new char[]{','}, 6));
        String strF = (str == null || (string = kotlin.text.o.L0(str).toString()) == null) ? null : HomeServerRegexes.INSTANCE.getNON_ALPHA_NUM_STRICT_REGEX().f("", kotlin.text.u.O(string.toLowerCase(Locale.US), "matroska", "mkv", false));
        String str2 = strF != null ? strF : "";
        if (kotlin.text.o.h0(str2) || str2.length() > 5) {
            return null;
        }
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String string(JsonObject jsonObject, String str) {
        JsonElement jsonElement = jsonObject.get(str);
        String strAsStringOrNull = jsonElement != null ? asStringOrNull(jsonElement) : null;
        return strAsStringOrNull == null ? "" : strAsStringOrNull;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final HomeServerCatalogCandidate toCatalogCandidate(HomeServerConnection homeServerConnection, HomeServerCollection homeServerCollection) {
        String strDisplayLabel = displayLabel(homeServerConnection);
        String name = homeServerCollection.getName();
        if (kotlin.text.o.h0(name)) {
            name = this.context.getString(R.string.library_default);
        }
        if (!kotlin.text.o.T(name, strDisplayLabel, true)) {
            name = androidx.compose.foundation.c.t(strDisplayLabel, " - ", name);
        }
        return new HomeServerCatalogCandidate(name, INSTANCE.buildCatalogSourceRef(homeServerConnection, homeServerCollection), strDisplayLabel, homeServerCollection.getName(), homeServerCollection.getType(), homeServerConnection.getServerKind(), homeServerConnection.getConnectionId());
    }

    private final HomeServerItem toHomeServerItem(JsonObject jsonObject, HomeServerKind homeServerKind) throws Exception {
        Map linkedHashMap;
        Object c0Var;
        Set<Map.Entry<String, JsonElement>> setEntrySet;
        HomeServerKind homeServerKind2 = homeServerKind;
        if (homeServerKind2 != HomeServerKind.PLEX) {
            JsonObject jsonObjectObj = obj(jsonObject, "ProviderIds");
            if (jsonObjectObj == null || (setEntrySet = jsonObjectObj.entrySet()) == null) {
                linkedHashMap = null;
            } else {
                Set<Map.Entry<String, JsonElement>> set = setEntrySet;
                int iQ0 = kotlin.collections.i0.q0(kotlin.collections.s.U(set, 10));
                if (iQ0 < 16) {
                    iQ0 = 16;
                }
                linkedHashMap = new LinkedHashMap(iQ0);
                Iterator<T> it = set.iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    String lowerCase = ((String) entry.getKey()).toLowerCase(Locale.US);
                    String strAsStringOrNull = asStringOrNull((JsonElement) entry.getValue());
                    if (strAsStringOrNull == null) {
                        strAsStringOrNull = "";
                    }
                    linkedHashMap.put(lowerCase, strAsStringOrNull);
                }
            }
            if (linkedHashMap == null) {
                linkedHashMap = kotlin.collections.a0.f19683i;
            }
            Map map = linkedHashMap;
            Integer numM6129int = m6129int(jsonObject, "ProductionYear");
            if (numM6129int == null) {
                numM6129int = kotlin.text.u.R(kotlin.text.o.I0(4, string(jsonObject, "PremiereDate")));
            }
            Integer num = numM6129int;
            String strString = string(jsonObject, "Id");
            String strString2 = string(jsonObject, "Name");
            String strString3 = string(jsonObject, "Type");
            String strString4 = string(jsonObject, "Overview");
            Double dG = kotlin.text.t.G(string(jsonObject, "CommunityRating"));
            JsonObject jsonObjectObj2 = obj(jsonObject, "ImageTags");
            String strString5 = jsonObjectObj2 != null ? string(jsonObjectObj2, "Primary") : null;
            if (strString5 == null) {
                strString5 = "";
            }
            if (kotlin.text.o.h0(strString5)) {
                strString5 = string(jsonObject, "PrimaryImageTag");
            }
            String str = strString5;
            JsonElement jsonElement = (JsonElement) kotlin.collections.x.o0(array(jsonObject, "BackdropImageTags"));
            String strAsStringOrNull2 = jsonElement != null ? asStringOrNull(jsonElement) : null;
            String str2 = strAsStringOrNull2 == null ? "" : strAsStringOrNull2;
            try {
                c0Var = Long.valueOf(Instant.parse(string(jsonObject, "DateCreated")).toEpochMilli());
            } catch (Throwable th) {
                c0Var = new x6.c0(th);
            }
            if (c0Var instanceof x6.c0) {
                c0Var = 0L;
            }
            return new HomeServerItem(strString, strString2, strString3, num, map, strString4, dG, str, str2, ((Number) c0Var).longValue(), "", m6129int(jsonObject, "IndexNumber"), m6129int(jsonObject, "ParentIndexNumber"), mediaSources(jsonObject));
        }
        List<JsonElement> listArray = array(jsonObject, "Guid");
        ArrayList<String> arrayList = new ArrayList();
        Iterator<T> it2 = listArray.iterator();
        while (it2.hasNext()) {
            JsonObject jsonObjectAsJsonObjectOrNull = asJsonObjectOrNull((JsonElement) it2.next());
            String strString6 = jsonObjectAsJsonObjectOrNull != null ? string(jsonObjectAsJsonObjectOrNull, TtmlNode.ATTR_ID) : null;
            if (strString6 != null) {
                arrayList.add(strString6);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (String str3 : arrayList) {
            String lowerCase2 = kotlin.text.o.H0(str3, "://").toLowerCase(Locale.US);
            String strH0 = kotlin.text.o.H0(kotlin.text.o.D0(str3, "://", ""), "?");
            if (kotlin.text.o.h0(lowerCase2) || kotlin.text.o.h0(strH0)) {
                lowerCase2 = null;
            }
            x6.x xVar = lowerCase2 != null ? new x6.x(lowerCase2, strH0) : null;
            if (xVar != null) {
                arrayList2.add(xVar);
            }
        }
        Map mapA0 = kotlin.collections.h0.A0(arrayList2);
        String strString7 = string(jsonObject, "ratingKey");
        if (kotlin.text.o.h0(strString7)) {
            strString7 = string(jsonObject, "key");
        }
        String str4 = strString7;
        String strString8 = string(jsonObject, LinkHeader.Parameters.Title);
        String strString9 = string(jsonObject, LinkHeader.Parameters.Type);
        Integer numM6129int2 = m6129int(jsonObject, "year");
        if (numM6129int2 == null) {
            numM6129int2 = kotlin.text.u.R(kotlin.text.o.I0(4, string(jsonObject, "originallyAvailableAt")));
        }
        Integer num2 = numM6129int2;
        String strString10 = string(jsonObject, "summary");
        Double dG2 = kotlin.text.t.G(string(jsonObject, "rating"));
        String strString11 = string(jsonObject, "thumb");
        String strString12 = string(jsonObject, "art");
        Long lM6130long = m6130long(jsonObject, "addedAt");
        long jLongValue = (lM6130long != null ? lM6130long.longValue() : 0L) * 1000;
        String strString13 = string(jsonObject, "librarySectionID");
        Integer numM6129int3 = m6129int(jsonObject, "index");
        Integer numM6129int4 = m6129int(jsonObject, "parentIndex");
        List<JsonElement> listArray2 = array(jsonObject, "Media");
        ArrayList arrayList3 = new ArrayList();
        Iterator<T> it3 = listArray2.iterator();
        while (it3.hasNext()) {
            JsonObject jsonObjectAsJsonObjectOrNull2 = asJsonObjectOrNull((JsonElement) it3.next());
            if (jsonObjectAsJsonObjectOrNull2 != null) {
                arrayList3.add(jsonObjectAsJsonObjectOrNull2);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        int i10 = 0;
        for (Object obj : arrayList3) {
            int i11 = i10 + 1;
            if (i10 < 0) {
                t7.a.Q();
                throw null;
            }
            JsonObject jsonObject2 = (JsonObject) obj;
            List<JsonElement> listArray3 = array(jsonObject2, "Part");
            ArrayList arrayList5 = new ArrayList();
            int i12 = 0;
            for (Object obj2 : listArray3) {
                int i13 = i12 + 1;
                if (i12 < 0) {
                    t7.a.Q();
                    throw null;
                }
                JsonObject jsonObjectAsJsonObjectOrNull3 = asJsonObjectOrNull((JsonElement) obj2);
                HomeServerMediaSource mediaSource = jsonObjectAsJsonObjectOrNull3 != null ? toMediaSource(jsonObjectAsJsonObjectOrNull3, homeServerKind2, jsonObject2, i10, i12) : null;
                if (mediaSource != null) {
                    arrayList5.add(mediaSource);
                }
                homeServerKind2 = homeServerKind;
                i12 = i13;
            }
            kotlin.collections.x.b0(arrayList4, arrayList5);
            homeServerKind2 = homeServerKind;
            i10 = i11;
        }
        return new HomeServerItem(str4, strString8, strString9, num2, mapA0, strString10, dG2, strString11, strString12, jLongValue, strString13, numM6129int3, numM6129int4, arrayList4);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0268  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x00a3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:228:0x00d0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.arflix.tv.data.repository.HomeServerRepository.HomeServerMediaSource toMediaSource(com.google.gson.JsonObject r32, com.arflix.tv.data.repository.HomeServerKind r33, com.google.gson.JsonObject r34, int r35, int r36) {
        /*
            Method dump skipped, instruction units count: 896
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.toMediaSource(com.google.gson.JsonObject, com.arflix.tv.data.repository.HomeServerKind, com.google.gson.JsonObject, int, int):com.arflix.tv.data.repository.HomeServerRepository$HomeServerMediaSource");
    }

    public static /* synthetic */ HomeServerMediaSource toMediaSource$default(HomeServerRepository homeServerRepository, JsonObject jsonObject, HomeServerKind homeServerKind, JsonObject jsonObject2, int i10, int i11, int i12, Object obj) {
        if ((i12 & 2) != 0) {
            jsonObject2 = null;
        }
        return homeServerRepository.toMediaSource(jsonObject, homeServerKind, jsonObject2, (i12 & 4) != 0 ? 0 : i10, (i12 & 8) != 0 ? 0 : i11);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final StreamSource toStreamSource(HomeServerMediaSource homeServerMediaSource, HomeServerConnection homeServerConnection, HomeServerItem homeServerItem, String str, String str2) {
        String strQualityLabel = qualityLabel(homeServerMediaSource);
        String strDisplayLabel = displayLabel(homeServerConnection);
        String str3 = (str2 == null || kotlin.text.o.h0(str2)) ? null : str2;
        String str4 = !kotlin.text.o.h0(strQualityLabel) ? strQualityLabel : null;
        String container = homeServerMediaSource.getContainer();
        if (kotlin.text.o.h0(container)) {
            container = null;
        }
        String strU0 = kotlin.collections.x.u0(kotlin.collections.r.X(new String[]{strDisplayLabel, str3, str4, container != null ? container.toUpperCase(Locale.US) : null}), " ", null, null, null, 62);
        if (kotlin.text.o.h0(strQualityLabel)) {
            strQualityLabel = "Unknown";
        }
        String str5 = strQualityLabel;
        String bytes = formatBytes(homeServerMediaSource.getSizeBytes());
        long sizeBytes = homeServerMediaSource.getSizeBytes();
        Long lValueOf = sizeBytes > 0 ? Long.valueOf(sizeBytes) : null;
        String name = homeServerMediaSource.getName();
        if (kotlin.text.o.h0(name)) {
            name = homeServerItem.getName();
        }
        String str6 = name;
        long sizeBytes2 = homeServerMediaSource.getSizeBytes();
        String str7 = null;
        return new StreamSource(strU0, strDisplayLabel, ADDON_ID, str5, bytes, lValueOf, str, null, null, new StreamBehaviorHints(false, Boolean.TRUE, str7, null, new ProxyHeaders(playbackHeaders(homeServerConnection), null, 2, null), null, sizeBytes2 > 0 ? Long.valueOf(sizeBytes2) : null, str6, null, null, null, null, null, null, 16173, null), null, null, str7, 0 == true ? 1 : 0, null, 32128, null);
    }

    public static /* synthetic */ StreamSource toStreamSource$default(HomeServerRepository homeServerRepository, HomeServerMediaSource homeServerMediaSource, HomeServerConnection homeServerConnection, HomeServerItem homeServerItem, String str, String str2, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            str2 = null;
        }
        return homeServerRepository.toStreamSource(homeServerMediaSource, homeServerConnection, homeServerItem, str, str2);
    }

    private final String validatePlexAccount(String token) {
        Object c0Var;
        gb.p0 p0VarD;
        String strString;
        String str = "";
        gb.j0 j0Var = new gb.j0();
        j0Var.i("https://plex.tv/api/v2/user");
        j0Var.f15724c.h("Accept", "application/json");
        j0Var.f15724c.h("X-Plex-Token", token);
        try {
            p0VarD = this.okHttpClient.a(j0Var.b()).d();
            try {
            } finally {
            }
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (p0VarD.k()) {
            s0 s0Var = p0VarD.f15786q;
            String strX = s0Var != null ? s0Var.x() : null;
            if (strX == null) {
                strX = "";
            }
            JsonObject jsonObjectAsJsonObjectOrNull = asJsonObjectOrNull(new JsonParser().parse(strX));
            if (jsonObjectAsJsonObjectOrNull == null) {
                strString = "";
                p0VarD.close();
                c0Var = strString;
            } else {
                String strString2 = string(jsonObjectAsJsonObjectOrNull, "friendlyName");
                boolean zH0 = kotlin.text.o.h0(strString2);
                String strString3 = strString2;
                if (zH0) {
                    strString3 = string(jsonObjectAsJsonObjectOrNull, "username");
                }
                boolean zH02 = kotlin.text.o.h0(strString3);
                strString = strString3;
                if (zH02) {
                    strString = string(jsonObjectAsJsonObjectOrNull, LinkHeader.Parameters.Title);
                }
                p0VarD.close();
                c0Var = strString;
            }
            c0Var = new x6.c0(th);
        } else {
            strString = "";
            p0VarD.close();
            c0Var = strString;
        }
        Object obj = str;
        if (!(c0Var instanceof x6.c0)) {
            obj = c0Var;
        }
        return (String) obj;
    }

    private final String xmlAttribute(String str, String str2) {
        List listB;
        kotlin.text.k kVarB = kotlin.text.m.b(HomeServerXmlRegexCache.INSTANCE.getRegex(str2), str);
        String str3 = (kVarB == null || (listB = kVarB.b()) == null) ? null : (String) kotlin.collections.x.p0(1, listB);
        if (str3 == null) {
            str3 = "";
        }
        return xmlDecoded(str3);
    }

    private final boolean xmlBooleanAttribute(String str, String str2) {
        String strXmlAttribute = xmlAttribute(str, str2);
        return kotlin.jvm.internal.p.a(strXmlAttribute, "1") || kotlin.text.u.L(strXmlAttribute, "true", true);
    }

    private final String xmlDecoded(String str) {
        return kotlin.text.u.O(kotlin.text.u.O(kotlin.text.u.O(kotlin.text.u.O(kotlin.text.u.O(str, "&amp;", "&", false), "&quot;", "\"", false), "&apos;", "'", false), "&lt;", "<", false), "&gt;", ">", false);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: connect-yxL6bBk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6132connectyxL6bBk(java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, d7.d<? super x6.d0> r15) {
        /*
            r10 = this;
            boolean r0 = r15 instanceof com.arflix.tv.data.repository.HomeServerRepository$connect$1
            if (r0 == 0) goto L13
            r0 = r15
            com.arflix.tv.data.repository.HomeServerRepository$connect$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository$connect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$connect$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$connect$1
            r0.<init>(r10, r15)
        L18:
            java.lang.Object r15 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3d
            if (r1 != r2) goto L35
            java.lang.Object r11 = r0.L$3
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r11 = r0.L$2
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r11 = r0.L$1
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r11 = r0.L$0
            java.lang.String r11 = (java.lang.String) r11
            k2.c.G(r15)
            goto L61
        L35:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L3d:
            k2.c.G(r15)
            ra.c r15 = ka.x0.f19655d
            com.arflix.tv.data.repository.HomeServerRepository$connect$2 r3 = new com.arflix.tv.data.repository.HomeServerRepository$connect$2
            r9 = 0
            r4 = r10
            r5 = r11
            r6 = r12
            r8 = r13
            r7 = r14
            r3.<init>(r4, r5, r6, r7, r8, r9)
            r11 = 0
            r0.L$0 = r11
            r0.L$1 = r11
            r0.L$2 = r11
            r0.L$3 = r11
            r0.label = r2
            java.lang.Object r15 = ka.m0.y(r15, r3, r0)
            e7.a r11 = e7.a.f15033i
            if (r15 != r11) goto L61
            return r11
        L61:
            x6.d0 r15 = (x6.d0) r15
            java.lang.Object r11 = r15.f22580i
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.m6132connectyxL6bBk(java.lang.String, java.lang.String, java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: connectPlexAccount-BWLJW6A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6133connectPlexAccountBWLJW6A(java.lang.String r10, java.lang.String r11, java.lang.String r12, d7.d<? super x6.d0> r13) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r13 instanceof com.arflix.tv.data.repository.HomeServerRepository$connectPlexAccount$1
            if (r0 == 0) goto L13
            r0 = r13
            com.arflix.tv.data.repository.HomeServerRepository$connectPlexAccount$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository$connectPlexAccount$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$connectPlexAccount$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$connectPlexAccount$1
            r0.<init>(r9, r13)
        L18:
            java.lang.Object r13 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L39
            if (r1 != r2) goto L31
            java.lang.Object r10 = r0.L$2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r10 = r0.L$1
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r10 = r0.L$0
            java.lang.String r10 = (java.lang.String) r10
            k2.c.G(r13)
            goto L5a
        L31:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L39:
            k2.c.G(r13)
            ra.c r13 = ka.x0.f19655d
            com.arflix.tv.data.repository.HomeServerRepository$connectPlexAccount$2 r3 = new com.arflix.tv.data.repository.HomeServerRepository$connectPlexAccount$2
            r8 = 0
            r4 = r9
            r5 = r10
            r6 = r11
            r7 = r12
            r3.<init>(r4, r5, r6, r7, r8)
            r10 = 0
            r0.L$0 = r10
            r0.L$1 = r10
            r0.L$2 = r10
            r0.label = r2
            java.lang.Object r13 = ka.m0.y(r13, r3, r0)
            e7.a r10 = e7.a.f15033i
            if (r13 != r10) goto L5a
            return r10
        L5a:
            x6.d0 r13 = (x6.d0) r13
            java.lang.Object r10 = r13.f22580i
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.m6133connectPlexAccountBWLJW6A(java.lang.String, java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object currentConnection(d7.d<? super com.arflix.tv.data.repository.HomeServerConnection> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.HomeServerRepository.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.HomeServerRepository$currentConnection$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$currentConnection$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$currentConnection$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3b
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            r0.label = r2
            java.lang.Object r5 = r4.currentConnections(r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3b
            return r0
        L3b:
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r5 = kotlin.collections.x.o0(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.currentConnection(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object currentConnections(d7.d<? super java.util.List<com.arflix.tv.data.repository.HomeServerConnection>> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.HomeServerRepository.C07401
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.HomeServerRepository$currentConnections$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository.C07401) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$currentConnections$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$currentConnections$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3a
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            k2.c.G(r7)
            goto L62
        L2e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L36:
            k2.c.G(r7)
            goto L48
        L3a:
            k2.c.G(r7)
            com.arflix.tv.data.repository.ProfileManager r7 = r6.profileManager
            r0.label = r3
            java.lang.Object r7 = r7.getProfileId(r0)
            if (r7 != r4) goto L48
            goto L5e
        L48:
            java.lang.String r7 = (java.lang.String) r7
            android.content.Context r1 = r6.context
            z0.g r1 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r1)
            na.j r1 = r1.getData()
            r0.L$0 = r7
            r0.label = r2
            java.lang.Object r0 = na.y0.l(r1, r0)
            if (r0 != r4) goto L5f
        L5e:
            return r4
        L5f:
            r5 = r0
            r0 = r7
            r7 = r5
        L62:
            c1.g r7 = (c1.g) r7
            c1.e r0 = r6.connectionKeyFor(r0)
            c1.b r7 = (c1.b) r7
            java.util.LinkedHashMap r7 = r7.f7368a
            java.lang.Object r7 = r7.get(r0)
            java.lang.String r7 = (java.lang.String) r7
            java.util.List r7 = r6.parseConnections(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.currentConnections(d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0061, code lost:
    
        if (androidx.work.impl.t.o(r1, r3, r0) == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object disconnect(d7.d<? super x6.t0> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.HomeServerRepository.C07421
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.HomeServerRepository$disconnect$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository.C07421) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$disconnect$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$disconnect$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3a
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            k2.c.G(r7)
            goto L64
        L2e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L36:
            k2.c.G(r7)
            goto L4b
        L3a:
            k2.c.G(r7)
            r6.clearSourceCache()
            com.arflix.tv.data.repository.ProfileManager r7 = r6.profileManager
            r0.label = r3
            java.lang.Object r7 = r7.getProfileId(r0)
            if (r7 != r4) goto L4b
            goto L63
        L4b:
            java.lang.String r7 = (java.lang.String) r7
            android.content.Context r1 = r6.context
            z0.g r1 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r1)
            com.arflix.tv.data.repository.HomeServerRepository$disconnect$2 r3 = new com.arflix.tv.data.repository.HomeServerRepository$disconnect$2
            r5 = 0
            r3.<init>(r7, r5)
            r0.L$0 = r5
            r0.label = r2
            java.lang.Object r7 = androidx.work.impl.t.o(r1, r3, r0)
            if (r7 != r4) goto L64
        L63:
            return r4
        L64:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.disconnect(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object exportCloudConnectionsJsonForProfile(java.lang.String r5, d7.d<? super java.lang.String> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.HomeServerRepository.C07431
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.HomeServerRepository$exportCloudConnectionsJsonForProfile$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository.C07431) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$exportCloudConnectionsJsonForProfile$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$exportCloudConnectionsJsonForProfile$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            k2.c.G(r6)
            goto L42
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r6)
            r6 = 0
            r0.L$0 = r6
            r0.label = r2
            java.lang.Object r6 = r4.currentConnectionsForProfile(r5, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L42
            return r5
        L42:
            java.util.List r6 = (java.util.List) r6
            boolean r5 = r6.isEmpty()
            if (r5 == 0) goto L4d
            java.lang.String r5 = ""
            return r5
        L4d:
            com.google.gson.Gson r5 = r4.gson
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.s.U(r6, r1)
            r0.<init>(r1)
            java.util.Iterator r6 = r6.iterator()
        L5e:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L72
            java.lang.Object r1 = r6.next()
            com.arflix.tv.data.repository.HomeServerConnection r1 = (com.arflix.tv.data.repository.HomeServerConnection) r1
            com.arflix.tv.data.repository.HomeServerConnection r1 = r4.sanitized(r1)
            r0.add(r1)
            goto L5e
        L72:
            com.arflix.tv.data.repository.HomeServerProfileConfig r6 = new com.arflix.tv.data.repository.HomeServerProfileConfig
            r6.<init>(r0)
            java.lang.String r5 = r5.toJson(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.exportCloudConnectionsJsonForProfile(java.lang.String, d7.d):java.lang.Object");
    }

    public final Object getCatalogCandidates(d7.d<? super List<HomeServerCatalogCandidate>> dVar) {
        return ka.m0.y(x0.f19655d, new C07482(null), dVar);
    }

    public final na.j<HomeServerConnection> getConnection() {
        return this.connection;
    }

    public final na.j<List<HomeServerConnection>> getConnections() {
        return this.connections;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object hasUsableConnections(d7.d<? super java.lang.Boolean> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.HomeServerRepository.C07491
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.HomeServerRepository$hasUsableConnections$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository.C07491) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$hasUsableConnections$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$hasUsableConnections$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L3b
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            r0.label = r2
            java.lang.Object r5 = r4.currentConnections(r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L3b
            return r0
        L3b:
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            boolean r0 = r5 instanceof java.util.Collection
            r1 = 0
            if (r0 == 0) goto L4d
            r0 = r5
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L4d
        L4b:
            r2 = r1
            goto L63
        L4d:
            java.util.Iterator r5 = r5.iterator()
        L51:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L4b
            java.lang.Object r0 = r5.next()
            com.arflix.tv.data.repository.HomeServerConnection r0 = (com.arflix.tv.data.repository.HomeServerConnection) r0
            boolean r0 = r0.isUsable()
            if (r0 == 0) goto L51
        L63:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.hasUsableConnections(d7.d):java.lang.Object");
    }

    public final Object importCloudConnectionsJsonForProfile(String str, String str2, d7.d<? super t0> dVar) {
        clearSourceCache();
        t0 t0Var = t0.f22605a;
        e7.a aVar = e7.a.f15033i;
        if (str2 == null || kotlin.text.o.h0(str2)) {
            Object objO = androidx.work.impl.t.o(DataStoresKt.getSettingsDataStore(this.context), new C07502(str, null), dVar);
            return objO == aVar ? objO : t0Var;
        }
        Object objSaveConnectionsForProfile = saveConnectionsForProfile(str, parseConnections(str2), dVar);
        return objSaveConnectionsForProfile == aVar ? objSaveConnectionsForProfile : t0Var;
    }

    public final Object loadCatalogItems(String str, int i10, int i11, HomeServerLibrarySort homeServerLibrarySort, MediaType mediaType, String str2, boolean z, d7.d<? super HomeServerCatalogPage> dVar) {
        return ka.m0.y(x0.f19655d, new C07512(i11, i10, str, this, z, homeServerLibrarySort, mediaType, str2, null), dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: pollHomeServerCodeAuth-BWLJW6A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6134pollHomeServerCodeAuthBWLJW6A(com.arflix.tv.data.repository.PlexPinAuthSession r10, java.lang.String r11, java.lang.String r12, d7.d<? super x6.d0> r13) {
        /*
            r9 = this;
            boolean r0 = r13 instanceof com.arflix.tv.data.repository.HomeServerRepository$pollHomeServerCodeAuth$1
            if (r0 == 0) goto L13
            r0 = r13
            com.arflix.tv.data.repository.HomeServerRepository$pollHomeServerCodeAuth$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository$pollHomeServerCodeAuth$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$pollHomeServerCodeAuth$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$pollHomeServerCodeAuth$1
            r0.<init>(r9, r13)
        L18:
            java.lang.Object r13 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L39
            if (r1 != r2) goto L31
            java.lang.Object r10 = r0.L$2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r10 = r0.L$1
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r10 = r0.L$0
            com.arflix.tv.data.repository.PlexPinAuthSession r10 = (com.arflix.tv.data.repository.PlexPinAuthSession) r10
            k2.c.G(r13)
            goto L5a
        L31:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L39:
            k2.c.G(r13)
            ra.c r13 = ka.x0.f19655d
            com.arflix.tv.data.repository.HomeServerRepository$pollHomeServerCodeAuth$2 r3 = new com.arflix.tv.data.repository.HomeServerRepository$pollHomeServerCodeAuth$2
            r8 = 0
            r5 = r9
            r4 = r10
            r6 = r11
            r7 = r12
            r3.<init>(r4, r5, r6, r7, r8)
            r10 = 0
            r0.L$0 = r10
            r0.L$1 = r10
            r0.L$2 = r10
            r0.label = r2
            java.lang.Object r13 = ka.m0.y(r13, r3, r0)
            e7.a r10 = e7.a.f15033i
            if (r13 != r10) goto L5a
            return r10
        L5a:
            x6.d0 r13 = (x6.d0) r13
            java.lang.Object r10 = r13.f22580i
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.m6134pollHomeServerCodeAuthBWLJW6A(com.arflix.tv.data.repository.PlexPinAuthSession, java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: pollPlexPinAuth-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6135pollPlexPinAuthgIAlus(java.lang.String r5, d7.d<? super x6.d0> r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.HomeServerRepository$pollPlexPinAuth$1
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.HomeServerRepository$pollPlexPinAuth$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository$pollPlexPinAuth$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$pollPlexPinAuth$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$pollPlexPinAuth$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            k2.c.G(r6)
            goto L49
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r6)
            ra.c r6 = ka.x0.f19655d
            com.arflix.tv.data.repository.HomeServerRepository$pollPlexPinAuth$2 r1 = new com.arflix.tv.data.repository.HomeServerRepository$pollPlexPinAuth$2
            r3 = 0
            r1.<init>(r5, r4, r3)
            r0.L$0 = r3
            r0.label = r2
            java.lang.Object r6 = ka.m0.y(r6, r1, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L49
            return r5
        L49:
            x6.d0 r6 = (x6.d0) r6
            java.lang.Object r5 = r6.f22580i
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.m6135pollPlexPinAuthgIAlus(java.lang.String, d7.d):java.lang.Object");
    }

    public final Object resolveEpisodeSources(String str, String str2, int i10, int i11, Integer num, Integer num2, d7.d<? super List<StreamSource>> dVar) {
        return ka.m0.y(x0.f19655d, new C07532(str2, str, num, num2, i10, i11, null), dVar);
    }

    public final Object resolveMovieSources(String str, String str2, Integer num, Integer num2, d7.d<? super List<StreamSource>> dVar) {
        return ka.m0.y(x0.f19655d, new C07542(str2, num, str, num2, null), dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: startHomeServerCodeAuth-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6136startHomeServerCodeAuthgIAlus(java.lang.String r5, d7.d<? super x6.d0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.HomeServerRepository$startHomeServerCodeAuth$1
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.HomeServerRepository$startHomeServerCodeAuth$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository$startHomeServerCodeAuth$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$startHomeServerCodeAuth$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$startHomeServerCodeAuth$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            k2.c.G(r6)
            goto L49
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r6)
            ra.c r6 = ka.x0.f19655d
            com.arflix.tv.data.repository.HomeServerRepository$startHomeServerCodeAuth$2 r1 = new com.arflix.tv.data.repository.HomeServerRepository$startHomeServerCodeAuth$2
            r3 = 0
            r1.<init>(r4, r5, r3)
            r0.L$0 = r3
            r0.label = r2
            java.lang.Object r6 = ka.m0.y(r6, r1, r0)
            e7.a r5 = e7.a.f15033i
            if (r6 != r5) goto L49
            return r5
        L49:
            x6.d0 r6 = (x6.d0) r6
            java.lang.Object r5 = r6.f22580i
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.m6136startHomeServerCodeAuthgIAlus(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: startPlexPinAuth-IoAF18A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6137startPlexPinAuthIoAF18A(d7.d<? super x6.d0> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.HomeServerRepository$startPlexPinAuth$1
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.HomeServerRepository$startPlexPinAuth$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository$startPlexPinAuth$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$startPlexPinAuth$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$startPlexPinAuth$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L43
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            ra.c r5 = ka.x0.f19655d
            com.arflix.tv.data.repository.HomeServerRepository$startPlexPinAuth$2 r1 = new com.arflix.tv.data.repository.HomeServerRepository$startPlexPinAuth$2
            r3 = 0
            r1.<init>(r4, r3)
            r0.label = r2
            java.lang.Object r5 = ka.m0.y(r5, r1, r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L43
            return r0
        L43:
            x6.d0 r5 = (x6.d0) r5
            java.lang.Object r5 = r5.f22580i
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.m6137startPlexPinAuthIoAF18A(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: testConnection-IoAF18A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6138testConnectionIoAF18A(d7.d<? super x6.d0> r5) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.HomeServerRepository$testConnection$1
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.HomeServerRepository$testConnection$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository$testConnection$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$testConnection$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$testConnection$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L43
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            ra.c r5 = ka.x0.f19655d
            com.arflix.tv.data.repository.HomeServerRepository$testConnection$2 r1 = new com.arflix.tv.data.repository.HomeServerRepository$testConnection$2
            r3 = 0
            r1.<init>(r4, r3)
            r0.label = r2
            java.lang.Object r5 = ka.m0.y(r5, r1, r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L43
            return r0
        L43:
            x6.d0 r5 = (x6.d0) r5
            java.lang.Object r5 = r5.f22580i
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.m6138testConnectionIoAF18A(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: testConnections-IoAF18A, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6139testConnectionsIoAF18A(d7.d<? super x6.d0> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.HomeServerRepository$testConnections$1
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.HomeServerRepository$testConnections$1 r0 = (com.arflix.tv.data.repository.HomeServerRepository$testConnections$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.HomeServerRepository$testConnections$1 r0 = new com.arflix.tv.data.repository.HomeServerRepository$testConnections$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L43
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            ra.c r5 = ka.x0.f19655d
            com.arflix.tv.data.repository.HomeServerRepository$testConnections$2 r1 = new com.arflix.tv.data.repository.HomeServerRepository$testConnections$2
            r3 = 0
            r1.<init>(r4, r3)
            r0.label = r2
            java.lang.Object r5 = ka.m0.y(r5, r1, r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L43
            return r0
        L43:
            x6.d0 r5 = (x6.d0) r5
            java.lang.Object r5 = r5.f22580i
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.m6139testConnectionsIoAF18A(d7.d):java.lang.Object");
    }

    private final List<JsonElement> array(JsonObject jsonObject, String str, String str2) {
        JsonObject jsonObjectObj = obj(jsonObject, str);
        List<JsonElement> listArray = jsonObjectObj != null ? array(jsonObjectObj, str2) : null;
        return listArray == null ? kotlin.collections.z.f19728i : listArray;
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerRepository$ServerInfo;", "", "serverName", "", "serverId", "productName", "serverKind", "Lcom/arflix/tv/data/repository/HomeServerKind;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/repository/HomeServerKind;)V", "getServerName", "()Ljava/lang/String;", "getServerId", "getProductName", "getServerKind", "()Lcom/arflix/tv/data/repository/HomeServerKind;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ServerInfo {
        private final String productName;
        private final String serverId;
        private final HomeServerKind serverKind;
        private final String serverName;

        public ServerInfo(String str, String str2, String str3, HomeServerKind homeServerKind) {
            this.serverName = str;
            this.serverId = str2;
            this.productName = str3;
            this.serverKind = homeServerKind;
        }

        public static /* synthetic */ ServerInfo copy$default(ServerInfo serverInfo, String str, String str2, String str3, HomeServerKind homeServerKind, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = serverInfo.serverName;
            }
            if ((i10 & 2) != 0) {
                str2 = serverInfo.serverId;
            }
            if ((i10 & 4) != 0) {
                str3 = serverInfo.productName;
            }
            if ((i10 & 8) != 0) {
                homeServerKind = serverInfo.serverKind;
            }
            return serverInfo.copy(str, str2, str3, homeServerKind);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getServerName() {
            return this.serverName;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getServerId() {
            return this.serverId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getProductName() {
            return this.productName;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final HomeServerKind getServerKind() {
            return this.serverKind;
        }

        public final ServerInfo copy(String serverName, String serverId, String productName, HomeServerKind serverKind) {
            return new ServerInfo(serverName, serverId, productName, serverKind);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ServerInfo)) {
                return false;
            }
            ServerInfo serverInfo = (ServerInfo) other;
            return kotlin.jvm.internal.p.a(this.serverName, serverInfo.serverName) && kotlin.jvm.internal.p.a(this.serverId, serverInfo.serverId) && kotlin.jvm.internal.p.a(this.productName, serverInfo.productName) && this.serverKind == serverInfo.serverKind;
        }

        public final String getProductName() {
            return this.productName;
        }

        public final String getServerId() {
            return this.serverId;
        }

        public final HomeServerKind getServerKind() {
            return this.serverKind;
        }

        public final String getServerName() {
            return this.serverName;
        }

        public int hashCode() {
            return this.serverKind.hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.serverName.hashCode() * 31, 31, this.serverId), 31, this.productName);
        }

        public String toString() {
            String str = this.serverName;
            String str2 = this.serverId;
            String str3 = this.productName;
            HomeServerKind homeServerKind = this.serverKind;
            StringBuilder sbR = a2.r("ServerInfo(serverName=", str, ", serverId=", str2, ", productName=");
            sbR.append(str3);
            sbR.append(", serverKind=");
            sbR.append(homeServerKind);
            sbR.append(")");
            return sbR.toString();
        }

        public /* synthetic */ ServerInfo(String str, String str2, String str3, HomeServerKind homeServerKind, int i10, kotlin.jvm.internal.h hVar) {
            this(str, str2, str3, (i10 & 8) != 0 ? HomeServerKind.UNKNOWN : homeServerKind);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003JE\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerRepository$AuthResponse;", "", "accessToken", "", "serverId", "serverName", "userId", "userName", "accountToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccessToken", "()Ljava/lang/String;", "getServerId", "getServerName", "getUserId", "getUserName", "getAccountToken", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class AuthResponse {
        private final String accessToken;
        private final String accountToken;
        private final String serverId;
        private final String serverName;
        private final String userId;
        private final String userName;

        public AuthResponse(String str, String str2, String str3, String str4, String str5, String str6) {
            this.accessToken = str;
            this.serverId = str2;
            this.serverName = str3;
            this.userId = str4;
            this.userName = str5;
            this.accountToken = str6;
        }

        public static /* synthetic */ AuthResponse copy$default(AuthResponse authResponse, String str, String str2, String str3, String str4, String str5, String str6, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = authResponse.accessToken;
            }
            if ((i10 & 2) != 0) {
                str2 = authResponse.serverId;
            }
            if ((i10 & 4) != 0) {
                str3 = authResponse.serverName;
            }
            if ((i10 & 8) != 0) {
                str4 = authResponse.userId;
            }
            if ((i10 & 16) != 0) {
                str5 = authResponse.userName;
            }
            if ((i10 & 32) != 0) {
                str6 = authResponse.accountToken;
            }
            String str7 = str5;
            String str8 = str6;
            return authResponse.copy(str, str2, str3, str4, str7, str8);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAccessToken() {
            return this.accessToken;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getServerId() {
            return this.serverId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getServerName() {
            return this.serverName;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getUserId() {
            return this.userId;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getUserName() {
            return this.userName;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getAccountToken() {
            return this.accountToken;
        }

        public final AuthResponse copy(String accessToken, String serverId, String serverName, String userId, String userName, String accountToken) {
            return new AuthResponse(accessToken, serverId, serverName, userId, userName, accountToken);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AuthResponse)) {
                return false;
            }
            AuthResponse authResponse = (AuthResponse) other;
            return kotlin.jvm.internal.p.a(this.accessToken, authResponse.accessToken) && kotlin.jvm.internal.p.a(this.serverId, authResponse.serverId) && kotlin.jvm.internal.p.a(this.serverName, authResponse.serverName) && kotlin.jvm.internal.p.a(this.userId, authResponse.userId) && kotlin.jvm.internal.p.a(this.userName, authResponse.userName) && kotlin.jvm.internal.p.a(this.accountToken, authResponse.accountToken);
        }

        public final String getAccessToken() {
            return this.accessToken;
        }

        public final String getAccountToken() {
            return this.accountToken;
        }

        public final String getServerId() {
            return this.serverId;
        }

        public final String getServerName() {
            return this.serverName;
        }

        public final String getUserId() {
            return this.userId;
        }

        public final String getUserName() {
            return this.userName;
        }

        public int hashCode() {
            return this.accountToken.hashCode() + androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.accessToken.hashCode() * 31, 31, this.serverId), 31, this.serverName), 31, this.userId), 31, this.userName);
        }

        public String toString() {
            String str = this.accessToken;
            String str2 = this.serverId;
            String str3 = this.serverName;
            String str4 = this.userId;
            String str5 = this.userName;
            String str6 = this.accountToken;
            StringBuilder sbR = a2.r("AuthResponse(accessToken=", str, ", serverId=", str2, ", serverName=");
            y.a.i(sbR, str3, ", userId=", str4, ", userName=");
            return a2.n(sbR, str5, ", accountToken=", str6, ")");
        }

        public /* synthetic */ AuthResponse(String str, String str2, String str3, String str4, String str5, String str6, int i10, kotlin.jvm.internal.h hVar) {
            this(str, str2, str3, str4, str5, (i10 & 32) != 0 ? "" : str6);
        }
    }

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B\u009b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\u0006\u0010/\u001a\u000200J\u0018\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u0003J\t\u00106\u001a\u00020\u0003HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\u0010\u00109\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0015\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\tHÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\u0010\u0010<\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010$J\t\u0010=\u001a\u00020\u0003HÆ\u0003J\t\u0010>\u001a\u00020\u0003HÆ\u0003J\t\u0010?\u001a\u00020\u0010HÆ\u0003J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\u0010\u0010A\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u0010B\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000f\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015HÆ\u0003J´\u0001\u0010D\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015HÆ\u0001¢\u0006\u0002\u0010EJ\u0014\u0010F\u001a\u00020G2\b\u0010H\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010I\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010J\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010%\u001a\u0004\b#\u0010$R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001aR\u0015\u0010\u0012\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b+\u0010\u001eR\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b,\u0010\u001eR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.¨\u0006K"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerItem;", "", TtmlNode.ATTR_ID, "", ContentDisposition.Parameters.Name, LinkHeader.Parameters.Type, "productionYear", "", "providerIds", "", "overview", "rating", "", "primaryImageTag", "backdropImageTag", "addedAt", "", "librarySectionId", "indexNumber", "parentIndexNumber", "mediaSources", "", "Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerMediaSource;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getName", "getType", "getProductionYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getProviderIds", "()Ljava/util/Map;", "getOverview", "getRating", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getPrimaryImageTag", "getBackdropImageTag", "getAddedAt", "()J", "getLibrarySectionId", "getIndexNumber", "getParentIndexNumber", "getMediaSources", "()Ljava/util/List;", "info", "Lcom/arflix/tv/data/repository/HomeServerCandidateInfo;", "toCatalogItem", "Lcom/arflix/tv/data/repository/HomeServerCatalogItem;", "connection", "Lcom/arflix/tv/data/repository/HomeServerConnection;", "sourceRef", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;)Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerItem;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class HomeServerItem {
        private final long addedAt;
        private final String backdropImageTag;
        private final String id;
        private final Integer indexNumber;
        private final String librarySectionId;
        private final List<HomeServerMediaSource> mediaSources;
        private final String name;
        private final String overview;
        private final Integer parentIndexNumber;
        private final String primaryImageTag;
        private final Integer productionYear;
        private final Map<String, String> providerIds;
        private final Double rating;
        private final String type;

        public HomeServerItem(String str, String str2, String str3, Integer num, Map<String, String> map, String str4, Double d4, String str5, String str6, long j10, String str7, Integer num2, Integer num3, List<HomeServerMediaSource> list) {
            this.id = str;
            this.name = str2;
            this.type = str3;
            this.productionYear = num;
            this.providerIds = map;
            this.overview = str4;
            this.rating = d4;
            this.primaryImageTag = str5;
            this.backdropImageTag = str6;
            this.addedAt = j10;
            this.librarySectionId = str7;
            this.indexNumber = num2;
            this.parentIndexNumber = num3;
            this.mediaSources = list;
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final long getAddedAt() {
            return this.addedAt;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getLibrarySectionId() {
            return this.librarySectionId;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final Integer getIndexNumber() {
            return this.indexNumber;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final Integer getParentIndexNumber() {
            return this.parentIndexNumber;
        }

        public final List<HomeServerMediaSource> component14() {
            return this.mediaSources;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final Integer getProductionYear() {
            return this.productionYear;
        }

        public final Map<String, String> component5() {
            return this.providerIds;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getOverview() {
            return this.overview;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final Double getRating() {
            return this.rating;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getPrimaryImageTag() {
            return this.primaryImageTag;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getBackdropImageTag() {
            return this.backdropImageTag;
        }

        public final HomeServerItem copy(String id, String name, String type, Integer productionYear, Map<String, String> providerIds, String overview, Double rating, String primaryImageTag, String backdropImageTag, long addedAt, String librarySectionId, Integer indexNumber, Integer parentIndexNumber, List<HomeServerMediaSource> mediaSources) {
            return new HomeServerItem(id, name, type, productionYear, providerIds, overview, rating, primaryImageTag, backdropImageTag, addedAt, librarySectionId, indexNumber, parentIndexNumber, mediaSources);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HomeServerItem)) {
                return false;
            }
            HomeServerItem homeServerItem = (HomeServerItem) other;
            return kotlin.jvm.internal.p.a(this.id, homeServerItem.id) && kotlin.jvm.internal.p.a(this.name, homeServerItem.name) && kotlin.jvm.internal.p.a(this.type, homeServerItem.type) && kotlin.jvm.internal.p.a(this.productionYear, homeServerItem.productionYear) && kotlin.jvm.internal.p.a(this.providerIds, homeServerItem.providerIds) && kotlin.jvm.internal.p.a(this.overview, homeServerItem.overview) && kotlin.jvm.internal.p.a(this.rating, homeServerItem.rating) && kotlin.jvm.internal.p.a(this.primaryImageTag, homeServerItem.primaryImageTag) && kotlin.jvm.internal.p.a(this.backdropImageTag, homeServerItem.backdropImageTag) && this.addedAt == homeServerItem.addedAt && kotlin.jvm.internal.p.a(this.librarySectionId, homeServerItem.librarySectionId) && kotlin.jvm.internal.p.a(this.indexNumber, homeServerItem.indexNumber) && kotlin.jvm.internal.p.a(this.parentIndexNumber, homeServerItem.parentIndexNumber) && kotlin.jvm.internal.p.a(this.mediaSources, homeServerItem.mediaSources);
        }

        public final long getAddedAt() {
            return this.addedAt;
        }

        public final String getBackdropImageTag() {
            return this.backdropImageTag;
        }

        public final String getId() {
            return this.id;
        }

        public final Integer getIndexNumber() {
            return this.indexNumber;
        }

        public final String getLibrarySectionId() {
            return this.librarySectionId;
        }

        public final List<HomeServerMediaSource> getMediaSources() {
            return this.mediaSources;
        }

        public final String getName() {
            return this.name;
        }

        public final String getOverview() {
            return this.overview;
        }

        public final Integer getParentIndexNumber() {
            return this.parentIndexNumber;
        }

        public final String getPrimaryImageTag() {
            return this.primaryImageTag;
        }

        public final Integer getProductionYear() {
            return this.productionYear;
        }

        public final Map<String, String> getProviderIds() {
            return this.providerIds;
        }

        public final Double getRating() {
            return this.rating;
        }

        public final String getType() {
            return this.type;
        }

        public int hashCode() {
            int iC = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.name), 31, this.type);
            Integer num = this.productionYear;
            int iC2 = androidx.compose.foundation.c.c(a2.g(this.providerIds, (iC + (num == null ? 0 : num.hashCode())) * 31, 31), 31, this.overview);
            Double d4 = this.rating;
            int iC3 = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((iC2 + (d4 == null ? 0 : d4.hashCode())) * 31, 31, this.primaryImageTag), 31, this.backdropImageTag);
            long j10 = this.addedAt;
            int iC4 = androidx.compose.foundation.c.c((iC3 + ((int) (j10 ^ (j10 >>> 32)))) * 31, 31, this.librarySectionId);
            Integer num2 = this.indexNumber;
            int iHashCode = (iC4 + (num2 == null ? 0 : num2.hashCode())) * 31;
            Integer num3 = this.parentIndexNumber;
            return this.mediaSources.hashCode() + ((iHashCode + (num3 != null ? num3.hashCode() : 0)) * 31);
        }

        public final HomeServerCandidateInfo info() {
            return new HomeServerCandidateInfo(this.name, this.productionYear, this.providerIds);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x0026, code lost:
        
            if (r1.equals("show") == false) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0030, code lost:
        
            if (r1.equals("tv") == false) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x003a, code lost:
        
            if (r1.equals("tvshow") == false) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0043, code lost:
        
            if (r1.equals("series") == false) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0046, code lost:
        
            r1 = com.arflix.tv.data.model.MediaType.TV;
         */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final com.arflix.tv.data.repository.HomeServerCatalogItem toCatalogItem(com.arflix.tv.data.repository.HomeServerConnection r19, java.lang.String r20) throws java.io.UnsupportedEncodingException {
            /*
                Method dump skipped, instruction units count: 416
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.HomeServerRepository.HomeServerItem.toCatalogItem(com.arflix.tv.data.repository.HomeServerConnection, java.lang.String):com.arflix.tv.data.repository.HomeServerCatalogItem");
        }

        public String toString() {
            String str = this.id;
            String str2 = this.name;
            String str3 = this.type;
            Integer num = this.productionYear;
            Map<String, String> map = this.providerIds;
            String str4 = this.overview;
            Double d4 = this.rating;
            String str5 = this.primaryImageTag;
            String str6 = this.backdropImageTag;
            long j10 = this.addedAt;
            String str7 = this.librarySectionId;
            Integer num2 = this.indexNumber;
            Integer num3 = this.parentIndexNumber;
            List<HomeServerMediaSource> list = this.mediaSources;
            StringBuilder sbR = a2.r("HomeServerItem(id=", str, ", name=", str2, ", type=");
            sbR.append(str3);
            sbR.append(", productionYear=");
            sbR.append(num);
            sbR.append(", providerIds=");
            sbR.append(map);
            sbR.append(", overview=");
            sbR.append(str4);
            sbR.append(", rating=");
            sbR.append(d4);
            sbR.append(", primaryImageTag=");
            sbR.append(str5);
            sbR.append(", backdropImageTag=");
            sbR.append(str6);
            sbR.append(", addedAt=");
            sbR.append(j10);
            sbR.append(", librarySectionId=");
            sbR.append(str7);
            sbR.append(", indexNumber=");
            sbR.append(num2);
            sbR.append(", parentIndexNumber=");
            sbR.append(num3);
            sbR.append(", mediaSources=");
            sbR.append(list);
            sbR.append(")");
            return sbR.toString();
        }

        public /* synthetic */ HomeServerItem(String str, String str2, String str3, Integer num, Map map, String str4, Double d4, String str5, String str6, long j10, String str7, Integer num2, Integer num3, List list, int i10, kotlin.jvm.internal.h hVar) {
            this(str, str2, str3, num, map, (i10 & 32) != 0 ? "" : str4, (i10 & 64) != 0 ? null : d4, (i10 & 128) != 0 ? "" : str5, (i10 & 256) != 0 ? "" : str6, (i10 & 512) != 0 ? 0L : j10, str7, num2, num3, list);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b4\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B§\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\r\u0012\b\b\u0002\u0010\u0015\u001a\u00020\r\u0012\b\b\u0002\u0010\u0016\u001a\u00020\r¢\u0006\u0004\b\u0017\u0010\u0018J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\nHÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\t\u00106\u001a\u00020\rHÆ\u0003J\t\u00107\u001a\u00020\rHÆ\u0003J\t\u00108\u001a\u00020\u0003HÆ\u0003J\t\u00109\u001a\u00020\u0003HÆ\u0003J\t\u0010:\u001a\u00020\u0003HÆ\u0003J\t\u0010;\u001a\u00020\u0003HÆ\u0003J\t\u0010<\u001a\u00020\u0003HÆ\u0003J\t\u0010=\u001a\u00020\rHÆ\u0003J\t\u0010>\u001a\u00020\rHÆ\u0003J\t\u0010?\u001a\u00020\rHÆ\u0003J½\u0001\u0010@\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\r2\b\b\u0002\u0010\u0015\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\rHÆ\u0001J\u0014\u0010A\u001a\u00020B2\b\u0010C\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010D\u001a\u00020\rHÖ\u0081\u0004J\n\u0010E\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001aR\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001aR\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001aR\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001aR\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001aR\u0011\u0010\u0014\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b+\u0010$R\u0011\u0010\u0015\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b,\u0010$R\u0011\u0010\u0016\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b-\u0010$¨\u0006F"}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerRepository$HomeServerMediaSource;", "", TtmlNode.ATTR_ID, "", "key", ContentDisposition.Parameters.Name, "path", TtmlNode.RUBY_CONTAINER, "eTag", "sizeBytes", "", "transcodingUrl", "videoWidth", "", "videoHeight", "variantKey", "videoCodec", "videoProfile", "audioCodec", "audioProfile", "videoBitDepth", "mediaIndex", "partIndex", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;III)V", "getId", "()Ljava/lang/String;", "getKey", "getName", "getPath", "getContainer", "getETag", "getSizeBytes", "()J", "getTranscodingUrl", "getVideoWidth", "()I", "getVideoHeight", "getVariantKey", "getVideoCodec", "getVideoProfile", "getAudioCodec", "getAudioProfile", "getVideoBitDepth", "getMediaIndex", "getPartIndex", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class HomeServerMediaSource {
        private final String audioCodec;
        private final String audioProfile;
        private final String container;
        private final String eTag;
        private final String id;
        private final String key;
        private final int mediaIndex;
        private final String name;
        private final int partIndex;
        private final String path;
        private final long sizeBytes;
        private final String transcodingUrl;
        private final String variantKey;
        private final int videoBitDepth;
        private final String videoCodec;
        private final int videoHeight;
        private final String videoProfile;
        private final int videoWidth;

        public HomeServerMediaSource(String str, String str2, String str3, String str4, String str5, String str6, long j10, String str7, int i10, int i11, String str8, String str9, String str10, String str11, String str12, int i12, int i13, int i14) {
            this.id = str;
            this.key = str2;
            this.name = str3;
            this.path = str4;
            this.container = str5;
            this.eTag = str6;
            this.sizeBytes = j10;
            this.transcodingUrl = str7;
            this.videoWidth = i10;
            this.videoHeight = i11;
            this.variantKey = str8;
            this.videoCodec = str9;
            this.videoProfile = str10;
            this.audioCodec = str11;
            this.audioProfile = str12;
            this.videoBitDepth = i12;
            this.mediaIndex = i13;
            this.partIndex = i14;
        }

        public static /* synthetic */ HomeServerMediaSource copy$default(HomeServerMediaSource homeServerMediaSource, String str, String str2, String str3, String str4, String str5, String str6, long j10, String str7, int i10, int i11, String str8, String str9, String str10, String str11, String str12, int i12, int i13, int i14, int i15, Object obj) {
            int i16;
            int i17;
            String str13 = (i15 & 1) != 0 ? homeServerMediaSource.id : str;
            String str14 = (i15 & 2) != 0 ? homeServerMediaSource.key : str2;
            String str15 = (i15 & 4) != 0 ? homeServerMediaSource.name : str3;
            String str16 = (i15 & 8) != 0 ? homeServerMediaSource.path : str4;
            String str17 = (i15 & 16) != 0 ? homeServerMediaSource.container : str5;
            String str18 = (i15 & 32) != 0 ? homeServerMediaSource.eTag : str6;
            long j11 = (i15 & 64) != 0 ? homeServerMediaSource.sizeBytes : j10;
            String str19 = (i15 & 128) != 0 ? homeServerMediaSource.transcodingUrl : str7;
            int i18 = (i15 & 256) != 0 ? homeServerMediaSource.videoWidth : i10;
            int i19 = (i15 & 512) != 0 ? homeServerMediaSource.videoHeight : i11;
            String str20 = (i15 & 1024) != 0 ? homeServerMediaSource.variantKey : str8;
            String str21 = (i15 & 2048) != 0 ? homeServerMediaSource.videoCodec : str9;
            String str22 = (i15 & 4096) != 0 ? homeServerMediaSource.videoProfile : str10;
            String str23 = str13;
            String str24 = (i15 & 8192) != 0 ? homeServerMediaSource.audioCodec : str11;
            String str25 = (i15 & 16384) != 0 ? homeServerMediaSource.audioProfile : str12;
            int i20 = (i15 & 32768) != 0 ? homeServerMediaSource.videoBitDepth : i12;
            int i21 = (i15 & 65536) != 0 ? homeServerMediaSource.mediaIndex : i13;
            if ((i15 & 131072) != 0) {
                i17 = i21;
                i16 = homeServerMediaSource.partIndex;
            } else {
                i16 = i14;
                i17 = i21;
            }
            return homeServerMediaSource.copy(str23, str14, str15, str16, str17, str18, j11, str19, i18, i19, str20, str21, str22, str24, str25, i20, i17, i16);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final int getVideoHeight() {
            return this.videoHeight;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getVariantKey() {
            return this.variantKey;
        }

        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getVideoCodec() {
            return this.videoCodec;
        }

        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getVideoProfile() {
            return this.videoProfile;
        }

        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getAudioCodec() {
            return this.audioCodec;
        }

        /* JADX INFO: renamed from: component15, reason: from getter */
        public final String getAudioProfile() {
            return this.audioProfile;
        }

        /* JADX INFO: renamed from: component16, reason: from getter */
        public final int getVideoBitDepth() {
            return this.videoBitDepth;
        }

        /* JADX INFO: renamed from: component17, reason: from getter */
        public final int getMediaIndex() {
            return this.mediaIndex;
        }

        /* JADX INFO: renamed from: component18, reason: from getter */
        public final int getPartIndex() {
            return this.partIndex;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getKey() {
            return this.key;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getPath() {
            return this.path;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getContainer() {
            return this.container;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getETag() {
            return this.eTag;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final long getSizeBytes() {
            return this.sizeBytes;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getTranscodingUrl() {
            return this.transcodingUrl;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final int getVideoWidth() {
            return this.videoWidth;
        }

        public final HomeServerMediaSource copy(String id, String key, String name, String path, String container, String eTag, long sizeBytes, String transcodingUrl, int videoWidth, int videoHeight, String variantKey, String videoCodec, String videoProfile, String audioCodec, String audioProfile, int videoBitDepth, int mediaIndex, int partIndex) {
            return new HomeServerMediaSource(id, key, name, path, container, eTag, sizeBytes, transcodingUrl, videoWidth, videoHeight, variantKey, videoCodec, videoProfile, audioCodec, audioProfile, videoBitDepth, mediaIndex, partIndex);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HomeServerMediaSource)) {
                return false;
            }
            HomeServerMediaSource homeServerMediaSource = (HomeServerMediaSource) other;
            return kotlin.jvm.internal.p.a(this.id, homeServerMediaSource.id) && kotlin.jvm.internal.p.a(this.key, homeServerMediaSource.key) && kotlin.jvm.internal.p.a(this.name, homeServerMediaSource.name) && kotlin.jvm.internal.p.a(this.path, homeServerMediaSource.path) && kotlin.jvm.internal.p.a(this.container, homeServerMediaSource.container) && kotlin.jvm.internal.p.a(this.eTag, homeServerMediaSource.eTag) && this.sizeBytes == homeServerMediaSource.sizeBytes && kotlin.jvm.internal.p.a(this.transcodingUrl, homeServerMediaSource.transcodingUrl) && this.videoWidth == homeServerMediaSource.videoWidth && this.videoHeight == homeServerMediaSource.videoHeight && kotlin.jvm.internal.p.a(this.variantKey, homeServerMediaSource.variantKey) && kotlin.jvm.internal.p.a(this.videoCodec, homeServerMediaSource.videoCodec) && kotlin.jvm.internal.p.a(this.videoProfile, homeServerMediaSource.videoProfile) && kotlin.jvm.internal.p.a(this.audioCodec, homeServerMediaSource.audioCodec) && kotlin.jvm.internal.p.a(this.audioProfile, homeServerMediaSource.audioProfile) && this.videoBitDepth == homeServerMediaSource.videoBitDepth && this.mediaIndex == homeServerMediaSource.mediaIndex && this.partIndex == homeServerMediaSource.partIndex;
        }

        public final String getAudioCodec() {
            return this.audioCodec;
        }

        public final String getAudioProfile() {
            return this.audioProfile;
        }

        public final String getContainer() {
            return this.container;
        }

        public final String getETag() {
            return this.eTag;
        }

        public final String getId() {
            return this.id;
        }

        public final String getKey() {
            return this.key;
        }

        public final int getMediaIndex() {
            return this.mediaIndex;
        }

        public final String getName() {
            return this.name;
        }

        public final int getPartIndex() {
            return this.partIndex;
        }

        public final String getPath() {
            return this.path;
        }

        public final long getSizeBytes() {
            return this.sizeBytes;
        }

        public final String getTranscodingUrl() {
            return this.transcodingUrl;
        }

        public final String getVariantKey() {
            return this.variantKey;
        }

        public final int getVideoBitDepth() {
            return this.videoBitDepth;
        }

        public final String getVideoCodec() {
            return this.videoCodec;
        }

        public final int getVideoHeight() {
            return this.videoHeight;
        }

        public final String getVideoProfile() {
            return this.videoProfile;
        }

        public final int getVideoWidth() {
            return this.videoWidth;
        }

        public int hashCode() {
            int iC = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.key), 31, this.name), 31, this.path), 31, this.container), 31, this.eTag);
            long j10 = this.sizeBytes;
            return ((((androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((((androidx.compose.foundation.c.c((iC + ((int) (j10 ^ (j10 >>> 32)))) * 31, 31, this.transcodingUrl) + this.videoWidth) * 31) + this.videoHeight) * 31, 31, this.variantKey), 31, this.videoCodec), 31, this.videoProfile), 31, this.audioCodec), 31, this.audioProfile) + this.videoBitDepth) * 31) + this.mediaIndex) * 31) + this.partIndex;
        }

        public String toString() {
            String str = this.id;
            String str2 = this.key;
            String str3 = this.name;
            String str4 = this.path;
            String str5 = this.container;
            String str6 = this.eTag;
            long j10 = this.sizeBytes;
            String str7 = this.transcodingUrl;
            int i10 = this.videoWidth;
            int i11 = this.videoHeight;
            String str8 = this.variantKey;
            String str9 = this.videoCodec;
            String str10 = this.videoProfile;
            String str11 = this.audioCodec;
            String str12 = this.audioProfile;
            int i12 = this.videoBitDepth;
            int i13 = this.mediaIndex;
            int i14 = this.partIndex;
            StringBuilder sbR = a2.r("HomeServerMediaSource(id=", str, ", key=", str2, ", name=");
            y.a.i(sbR, str3, ", path=", str4, ", container=");
            y.a.i(sbR, str5, ", eTag=", str6, ", sizeBytes=");
            sbR.append(j10);
            sbR.append(", transcodingUrl=");
            sbR.append(str7);
            sbR.append(", videoWidth=");
            sbR.append(i10);
            sbR.append(", videoHeight=");
            sbR.append(i11);
            y.a.i(sbR, ", variantKey=", str8, ", videoCodec=", str9);
            y.a.i(sbR, ", videoProfile=", str10, ", audioCodec=", str11);
            sbR.append(", audioProfile=");
            sbR.append(str12);
            sbR.append(", videoBitDepth=");
            sbR.append(i12);
            sbR.append(", mediaIndex=");
            sbR.append(i13);
            sbR.append(", partIndex=");
            sbR.append(i14);
            sbR.append(")");
            return sbR.toString();
        }

        public /* synthetic */ HomeServerMediaSource(String str, String str2, String str3, String str4, String str5, String str6, long j10, String str7, int i10, int i11, String str8, String str9, String str10, String str11, String str12, int i12, int i13, int i14, int i15, kotlin.jvm.internal.h hVar) {
            this(str, str2, str3, str4, str5, str6, j10, str7, i10, i11, (i15 & 1024) != 0 ? "" : str8, (i15 & 2048) != 0 ? "" : str9, (i15 & 4096) != 0 ? "" : str10, (i15 & 8192) != 0 ? "" : str11, (i15 & 16384) != 0 ? "" : str12, (32768 & i15) != 0 ? 0 : i12, (65536 & i15) != 0 ? 0 : i13, (i15 & 131072) != 0 ? 0 : i14);
        }
    }
}
