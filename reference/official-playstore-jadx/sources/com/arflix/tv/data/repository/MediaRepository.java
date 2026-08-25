package com.arflix.tv.data.repository;

import android.content.Context;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import androidx.fragment.app.a2;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.R;
import com.arflix.tv.data.api.StremioMetaPreview;
import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.api.TmdbImage;
import com.arflix.tv.data.api.TmdbListResponse;
import com.arflix.tv.data.api.TmdbMediaItem;
import com.arflix.tv.data.api.TmdbWatchProvider;
import com.arflix.tv.data.api.TmdbWatchProviderRegion;
import com.arflix.tv.data.api.TraktApi;
import com.arflix.tv.data.api.TraktPublicListItem;
import com.arflix.tv.data.model.CastMember;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.CatalogKind;
import com.arflix.tv.data.model.CatalogSourceType;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.CollectionGroupKind;
import com.arflix.tv.data.model.CollectionSourceConfig;
import com.arflix.tv.data.model.CollectionSourceKind;
import com.arflix.tv.data.model.Episode;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.Review;
import com.arflix.tv.network.OkHttpProvider;
import com.arflix.tv.util.CatalogUrlParser;
import com.arflix.tv.util.Constants;
import com.arflix.tv.util.ParsedCatalogUrl;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.common.util.concurrent.r0;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dagger.hilt.android.qualifiers.ApplicationContext;
import gb.s0;
import io.ktor.http.ContentDisposition;
import io.ktor.http.LinkHeader;
import j$.util.concurrent.ConcurrentHashMap;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.x0;
import kotlin.Metadata;
import org.json.JSONArray;
import org.json.JSONObject;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000â\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\b\u0016\b\u0007\u0018\u0000 ±\u00022\u00020\u0001:\f²\u0002³\u0002±\u0002´\u0002µ\u0002¶\u0002BC\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u001f\u0010\u001a\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001c\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u001c\u0010\u001bJ%\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001f\u0010 J\u001f\u0010!\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b!\u0010\"J.\u0010#\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0086@¢\u0006\u0004\b#\u0010$J\u0015\u0010&\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u0019¢\u0006\u0004\b&\u0010'J\u0013\u0010*\u001a\b\u0012\u0004\u0012\u00020)0(¢\u0006\u0004\b*\u0010+J\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020,0(H\u0086@¢\u0006\u0004\b-\u0010.J \u00102\u001a\u0002012\u0006\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u00020\u0017H\u0086@¢\u0006\u0004\b2\u00103J$\u00106\u001a\u0004\u0018\u00010,2\u0006\u00104\u001a\u00020)2\b\b\u0002\u00105\u001a\u00020\u0017H\u0086@¢\u0006\u0004\b6\u00107J(\u0010:\u001a\u0002012\u0006\u00104\u001a\u00020)2\u0006\u00108\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u0017H\u0086@¢\u0006\u0004\b:\u0010;JH\u0010@\u001a\u0002012\u0006\u0010<\u001a\u00020\u001d2\u0006\u00108\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u00172\b\b\u0002\u0010>\u001a\u00020=2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010?\u001a\u00020\u001dH\u0086@¢\u0006\u0004\b@\u0010AJ(\u0010B\u001a\u0002012\u0006\u00104\u001a\u00020)2\u0006\u00108\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u0017H\u0086@¢\u0006\u0004\bB\u0010;J\u0018\u0010D\u001a\u00020\u00192\u0006\u0010C\u001a\u00020\u0017H\u0086@¢\u0006\u0004\bD\u0010EJ\u0018\u0010G\u001a\u00020\u00192\u0006\u0010F\u001a\u00020\u0017H\u0086@¢\u0006\u0004\bG\u0010EJ\u001a\u0010I\u001a\u0004\u0018\u00010H2\u0006\u0010C\u001a\u00020\u0017H\u0086@¢\u0006\u0004\bI\u0010EJ\u001e\u0010K\u001a\b\u0012\u0004\u0012\u00020\u00190(2\u0006\u0010J\u001a\u00020\u0017H\u0086@¢\u0006\u0004\bK\u0010EJ&\u0010N\u001a\b\u0012\u0004\u0012\u00020M0(2\u0006\u0010F\u001a\u00020\u00172\u0006\u0010L\u001a\u00020\u0017H\u0086@¢\u0006\u0004\bN\u0010OJ&\u0010Q\u001a\b\u0012\u0004\u0012\u00020P0(2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0086@¢\u0006\u0004\bQ\u0010RJ&\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00190(2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0086@¢\u0006\u0004\bS\u0010RJ\"\u0010T\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0086@¢\u0006\u0004\bT\u0010RJ\u001a\u0010T\u001a\u0004\u0018\u00010\u001d2\u0006\u0010%\u001a\u00020\u0019H\u0086@¢\u0006\u0004\bT\u0010UJ\u001f\u0010V\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\bV\u0010\"J%\u0010W\u001a\n\u0012\u0004\u0012\u00020M\u0018\u00010(2\u0006\u0010F\u001a\u00020\u00172\u0006\u0010L\u001a\u00020\u0017¢\u0006\u0004\bW\u0010XJ\"\u0010Y\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0086@¢\u0006\u0004\bY\u0010RJ\u0018\u0010\\\u001a\u00020[2\u0006\u0010Z\u001a\u00020\u0017H\u0086@¢\u0006\u0004\b\\\u0010EJ\u001e\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00190(2\u0006\u0010]\u001a\u00020\u001dH\u0086@¢\u0006\u0004\b^\u0010_J(\u0010b\u001a\b\u0012\u0004\u0012\u00020a0(2\u0006\u0010]\u001a\u00020\u001d2\b\b\u0002\u0010`\u001a\u00020\u0017H\u0086@¢\u0006\u0004\bb\u00103J~\u0010k\u001a\b\u0012\u0004\u0012\u00020\u00190(2\n\b\u0002\u0010c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010d\u001a\u00020\u001d2\n\b\u0002\u0010e\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u00100\u001a\u00020\u00172\n\b\u0002\u0010f\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010g\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010h\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010i\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010j\u001a\u0004\u0018\u00010\u001dH\u0086@¢\u0006\u0004\bk\u0010lJ~\u0010o\u001a\b\u0012\u0004\u0012\u00020\u00190(2\n\b\u0002\u0010c\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010d\u001a\u00020\u001d2\n\b\u0002\u0010e\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u00100\u001a\u00020\u00172\n\b\u0002\u0010f\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010g\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010h\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010m\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u001dH\u0086@¢\u0006\u0004\bo\u0010lJ\"\u0010q\u001a\u0004\u0018\u00010,2\u0006\u0010/\u001a\u00020\u001d2\u0006\u0010p\u001a\u00020\u001dH\u0086@¢\u0006\u0004\bq\u0010rJ&\u0010t\u001a\b\u0012\u0004\u0012\u00020s0(2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0086@¢\u0006\u0004\bt\u0010RJ.\u0010w\u001a\u0004\u0018\u00010v2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\n\b\u0002\u0010u\u001a\u0004\u0018\u00010\u001dH\u0086@¢\u0006\u0004\bw\u0010$J9\u0010}\u001a\u0004\u0018\u00018\u0000\"\u0004\b\u0000\u0010x2\u0018\u0010{\u001a\u0014\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000z0y2\u0006\u0010|\u001a\u00020\u001dH\u0002¢\u0006\u0004\b}\u0010~J\u001f\u0010\u007f\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u007f\u0010\"J1\u0010\u0081\u0001\u001a\u0019\u0012\u0013\u0012\u0011\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0080\u0001\u0018\u00010z2\u0006\u0010\u001e\u001a\u00020\u001dH\u0002¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J)\u0010\u0083\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0080\u00012\u0006\u0010\u001e\u001a\u00020\u001dH\u0002¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J2\u0010\u0086\u0001\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001d2\u0016\u0010\u0085\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0080\u0001H\u0002¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J1\u0010\u0088\u0001\u001a\u0019\u0012\u0013\u0012\u0011\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0080\u0001\u0018\u00010z2\u0006\u0010|\u001a\u00020\u001dH\u0002¢\u0006\u0006\b\u0088\u0001\u0010\u0082\u0001J2\u0010\u0089\u0001\u001a\u00020\u00122\u0006\u0010|\u001a\u00020\u001d2\u0016\u0010\u0085\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0080\u0001H\u0002¢\u0006\u0006\b\u0089\u0001\u0010\u0087\u0001J/\u0010\u008a\u0001\u001a\u0017\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u0001\u0018\u00010(2\u0006\u0010|\u001a\u00020\u001dH\u0002¢\u0006\u0006\b\u008a\u0001\u0010\u008b\u0001J\u001a\u0010\u008c\u0001\u001a\u00020\u001d2\u0006\u00104\u001a\u00020)H\u0002¢\u0006\u0006\b\u008c\u0001\u0010\u008d\u0001J6\u0010\u008f\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\u0006\u00104\u001a\u00020)2\u0007\u0010\u008e\u0001\u001a\u00020\u0017H\u0082@¢\u0006\u0005\b\u008f\u0001\u00107J0\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00172\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0082@¢\u0006\u0005\b\u0090\u0001\u0010$J%\u0010\u0092\u0001\u001a\u0005\u0018\u00010\u0091\u00012\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0082@¢\u0006\u0005\b\u0092\u0001\u0010RJ%\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001dH\u0082@¢\u0006\u0006\b\u0093\u0001\u0010\u0094\u0001J\u001e\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0096\u0001\u001a\u00030\u0095\u0001H\u0002¢\u0006\u0006\b\u0097\u0001\u0010\u0098\u0001JW\u0010\u009a\u0001\u001a\u001b\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0080\u0001\u0012\u0004\u0012\u00020\u001d0y2\u0006\u0010F\u001a\u00020\u00172\u0006\u0010L\u001a\u00020\u00172\r\u0010\u0099\u0001\u001a\b\u0012\u0004\u0012\u00020\u00170(2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0082@¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J@\u0010\u009c\u0001\u001a\u001b\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0080\u0001\u0012\u0004\u0012\u00020\u001d0y2\u0006\u0010F\u001a\u00020\u00172\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0082@¢\u0006\u0006\b\u009c\u0001\u0010\u009d\u0001J>\u0010\u009e\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001d0y2\u0006\u0010F\u001a\u00020\u00172\u0006\u0010L\u001a\u00020\u00172\r\u0010\u0099\u0001\u001a\b\u0012\u0004\u0012\u00020\u00170(H\u0082@¢\u0006\u0006\b\u009e\u0001\u0010\u009f\u0001J.\u0010¡\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001d0y2\r\u0010 \u0001\u001a\b\u0012\u0004\u0012\u00020\u001d0(H\u0082@¢\u0006\u0006\b¡\u0001\u0010¢\u0001J3\u0010£\u0001\u001a\u001b\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0080\u0001\u0012\u0004\u0012\u00020\u001d0y2\u0006\u0010\u001e\u001a\u00020\u001dH\u0082@¢\u0006\u0005\b£\u0001\u0010_J\u0019\u0010¤\u0001\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\u0019H\u0002¢\u0006\u0005\b¤\u0001\u0010'J!\u0010¦\u0001\u001a\u00020\u00122\r\u0010¥\u0001\u001a\b\u0012\u0004\u0012\u00020\u00190(H\u0002¢\u0006\u0006\b¦\u0001\u0010§\u0001J\u0018\u0010¨\u0001\u001a\b\u0012\u0004\u0012\u00020,0(H\u0082@¢\u0006\u0005\b¨\u0001\u0010.J*\u0010©\u0001\u001a\u0002012\u0006\u00104\u001a\u00020)2\u0006\u00108\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u0017H\u0082@¢\u0006\u0005\b©\u0001\u0010;J\u001e\u0010«\u0001\u001a\u0004\u0018\u00010\u00192\u0007\u0010%\u001a\u00030ª\u0001H\u0082@¢\u0006\u0006\b«\u0001\u0010¬\u0001J\u001e\u0010\u00ad\u0001\u001a\u0004\u0018\u00010\u00192\u0007\u0010%\u001a\u00030ª\u0001H\u0082@¢\u0006\u0006\b\u00ad\u0001\u0010¬\u0001J@\u0010°\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\b\u0010¯\u0001\u001a\u00030®\u00012\u0006\u00108\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u0017H\u0082@¢\u0006\u0006\b°\u0001\u0010±\u0001J8\u0010²\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\b\u0010¯\u0001\u001a\u00030®\u00012\u0006\u00109\u001a\u00020\u0017H\u0082@¢\u0006\u0006\b²\u0001\u0010³\u0001J7\u0010´\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\b\u0010¯\u0001\u001a\u00030®\u00012\u0006\u00109\u001a\u00020\u0017H\u0002¢\u0006\u0006\b´\u0001\u0010µ\u0001J8\u0010¶\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\b\u0010¯\u0001\u001a\u00030®\u00012\u0006\u00109\u001a\u00020\u0017H\u0082@¢\u0006\u0006\b¶\u0001\u0010³\u0001J8\u0010·\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\b\u0010¯\u0001\u001a\u00030®\u00012\u0006\u00109\u001a\u00020\u0017H\u0082@¢\u0006\u0006\b·\u0001\u0010³\u0001J8\u0010¸\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\b\u0010¯\u0001\u001a\u00030®\u00012\u0006\u00109\u001a\u00020\u0017H\u0082@¢\u0006\u0006\b¸\u0001\u0010³\u0001J@\u0010¹\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\b\u0010¯\u0001\u001a\u00030®\u00012\u0006\u00108\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u0017H\u0082@¢\u0006\u0006\b¹\u0001\u0010±\u0001J8\u0010º\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\b\u0010¯\u0001\u001a\u00030®\u00012\u0006\u00109\u001a\u00020\u0017H\u0082@¢\u0006\u0006\bº\u0001\u0010³\u0001J8\u0010»\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\b\u0010¯\u0001\u001a\u00030®\u00012\u0006\u00109\u001a\u00020\u0017H\u0082@¢\u0006\u0006\b»\u0001\u0010³\u0001J^\u0010À\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u00109\u001a\u00020\u00172&\u0010¿\u0001\u001a!\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\f\u0012\n\u0012\u0005\u0012\u00030¾\u00010½\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00010¼\u0001H\u0082@¢\u0006\u0006\bÀ\u0001\u0010Á\u0001J@\u0010Ä\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\b\u0010Ã\u0001\u001a\u00030Â\u00012\u0006\u00108\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u0017H\u0082@¢\u0006\u0006\bÄ\u0001\u0010Å\u0001J+\u0010Ç\u0001\u001a\u00030Æ\u00012\u0006\u00104\u001a\u00020)2\u0006\u00108\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u0017H\u0082@¢\u0006\u0005\bÇ\u0001\u0010;J@\u0010Ê\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\u000e\u0010É\u0001\u001a\t\u0012\u0005\u0012\u00030È\u00010(2\b\u0010Ã\u0001\u001a\u00030Â\u0001H\u0082@¢\u0006\u0006\bÊ\u0001\u0010Ë\u0001J6\u0010Î\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0080\u00012\b\u0010Ã\u0001\u001a\u00030Â\u00012\b\u0010Í\u0001\u001a\u00030Ì\u0001H\u0082@¢\u0006\u0006\bÎ\u0001\u0010Ï\u0001J5\u0010Ñ\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0080\u00012\u0006\u0010\u001e\u001a\u00020\u001d2\t\u0010Ð\u0001\u001a\u0004\u0018\u00010\u0015H\u0082@¢\u0006\u0006\bÑ\u0001\u0010Ò\u0001J6\u0010Ô\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0080\u00012\u0007\u0010Ó\u0001\u001a\u00020\u001d2\t\u0010Ð\u0001\u001a\u0004\u0018\u00010\u0015H\u0082@¢\u0006\u0006\bÔ\u0001\u0010Ò\u0001J\u001d\u0010Õ\u0001\u001a\u0005\u0018\u00010Â\u00012\u0006\u00104\u001a\u00020)H\u0002¢\u0006\u0006\bÕ\u0001\u0010Ö\u0001J\u001b\u0010×\u0001\u001a\u00020\u001d2\u0007\u0010\u0085\u0001\u001a\u00020\u001dH\u0002¢\u0006\u0006\b×\u0001\u0010Ø\u0001J\u001f\u0010Ú\u0001\u001a\u0004\u0018\u00010\u001d2\t\u0010Ù\u0001\u001a\u0004\u0018\u00010\u001dH\u0002¢\u0006\u0006\bÚ\u0001\u0010Ø\u0001J\u001f\u0010Û\u0001\u001a\u0004\u0018\u00010\u00152\t\u0010Ù\u0001\u001a\u0004\u0018\u00010\u001dH\u0002¢\u0006\u0006\bÛ\u0001\u0010Ü\u0001J6\u0010Þ\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0080\u00012\b\u0010\u0096\u0001\u001a\u00030È\u00012\t\u0010Ý\u0001\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0006\bÞ\u0001\u0010ß\u0001J\u001e\u0010à\u0001\u001a\u0004\u0018\u00010\u001d2\b\u0010\u0096\u0001\u001a\u00030È\u0001H\u0002¢\u0006\u0006\bà\u0001\u0010á\u0001J)\u0010â\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0080\u00012\u0006\u0010%\u001a\u00020\u0019H\u0082@¢\u0006\u0005\bâ\u0001\u0010UJ)\u0010ã\u0001\u001a\u0011\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0080\u00012\u0006\u0010%\u001a\u00020\u0019H\u0082@¢\u0006\u0005\bã\u0001\u0010UJ4\u0010ç\u0001\u001a\u0005\u0018\u00010ä\u00012\u0014\u0010å\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u001d\u0012\u0005\u0012\u00030ä\u00010y2\u0007\u0010æ\u0001\u001a\u00020\u001dH\u0002¢\u0006\u0006\bç\u0001\u0010è\u0001J#\u0010ë\u0001\u001a\t\u0012\u0005\u0012\u00030ê\u00010(2\b\u0010é\u0001\u001a\u00030ä\u0001H\u0002¢\u0006\u0006\bë\u0001\u0010ì\u0001J\u0017\u0010î\u0001\u001a\u00030í\u0001*\u00020)H\u0002¢\u0006\u0006\bî\u0001\u0010ï\u0001J\u001d\u0010ñ\u0001\u001a\u00020\u001d2\t\u0010ð\u0001\u001a\u0004\u0018\u00010\u001dH\u0002¢\u0006\u0006\bñ\u0001\u0010Ø\u0001J\u001d\u0010ó\u0001\u001a\u0004\u0018\u00010\u001d2\u0007\u0010ò\u0001\u001a\u00020\u001dH\u0002¢\u0006\u0006\bó\u0001\u0010Ø\u0001J\u001d\u0010ô\u0001\u001a\u00020\u001d2\t\u0010æ\u0001\u001a\u0004\u0018\u00010\u001dH\u0002¢\u0006\u0006\bô\u0001\u0010Ø\u0001J<\u0010ö\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\t\u0010õ\u0001\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u001dH\u0082@¢\u0006\u0005\bö\u0001\u0010rJ6\u0010ø\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\u000e\u0010¥\u0001\u001a\t\u0012\u0005\u0012\u00030÷\u00010(H\u0082@¢\u0006\u0006\bø\u0001\u0010¢\u0001J<\u0010ù\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\t\u0010õ\u0001\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u001dH\u0082@¢\u0006\u0005\bù\u0001\u0010rJ\u001f\u0010û\u0001\u001a\u0005\u0018\u00010ú\u00012\b\u0010<\u001a\u0004\u0018\u00010\u001dH\u0002¢\u0006\u0006\bû\u0001\u0010ü\u0001J.\u0010þ\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(2\u0007\u0010ý\u0001\u001a\u00020\u001dH\u0002¢\u0006\u0006\bþ\u0001\u0010\u008b\u0001J\u001d\u0010\u0080\u0002\u001a\u0004\u0018\u00010\u001d2\u0007\u0010ÿ\u0001\u001a\u00020\u001dH\u0002¢\u0006\u0006\b\u0080\u0002\u0010Ø\u0001R\u0015\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0003\u0010\u0081\u0002R\u0015\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0005\u0010\u0082\u0002R\u0015\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0007\u0010\u0083\u0002R\u0015\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\t\u0010\u0084\u0002R\u0015\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u000b\u0010\u0085\u0002R\u0015\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\r\u0010\u0086\u0002R\u0015\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u000f\u0010\u0087\u0002R\u0017\u0010\u0088\u0002\u001a\u00020\u001d8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0088\u0002\u0010\u0089\u0002R\u0018\u0010\u008b\u0002\u001a\u00030\u008a\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008b\u0002\u0010\u008c\u0002R2\u0010\u008d\u0002\u001a\u00020\u001d2\u0007\u0010\u0085\u0001\u001a\u00020\u001d8\u0006@FX\u0086\u000e¢\u0006\u0018\n\u0006\b\u008d\u0002\u0010\u0089\u0002\u001a\u0006\b\u008e\u0002\u0010\u008f\u0002\"\u0006\b\u0090\u0002\u0010\u0091\u0002R\u0018\u0010\u0093\u0002\u001a\u00030\u0092\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0093\u0002\u0010\u0094\u0002R5\u0010\u0095\u0002\u001a\b\u0012\u0004\u0012\u00020,0(2\r\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020,0(8\u0006@BX\u0086\u000e¢\u0006\u000f\n\u0006\b\u0095\u0002\u0010\u0096\u0002\u001a\u0005\b\u0097\u0002\u0010+R\u001a\u0010\u0098\u0002\u001a\u00030\u0092\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0098\u0002\u0010\u0094\u0002R\u0018\u0010\u0099\u0002\u001a\u00030\u0092\u00028\u0002X\u0082D¢\u0006\b\n\u0006\b\u0099\u0002\u0010\u0094\u0002R*\u0010\u009b\u0002\u001a\u0015\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190z0\u009a\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009b\u0002\u0010\u009c\u0002R\u001e\u0010\u009e\u0002\u001a\t\u0012\u0004\u0012\u00020\u001d0\u009d\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009e\u0002\u0010\u009f\u0002R0\u0010 \u0002\u001a\u001b\u0012\u0004\u0012\u00020\u001d\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020P0(0z0\u009a\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b \u0002\u0010\u009c\u0002R0\u0010¡\u0002\u001a\u001b\u0012\u0004\u0012\u00020\u001d\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190(0z0\u009a\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¡\u0002\u0010\u009c\u0002R,\u0010£\u0002\u001a\u0017\u0012\u0004\u0012\u00020\u001d\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0z0¢\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b£\u0002\u0010¤\u0002R0\u0010¥\u0002\u001a\u001b\u0012\u0004\u0012\u00020\u001d\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020s0(0z0\u009a\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¥\u0002\u0010\u009c\u0002R,\u0010¦\u0002\u001a\u0017\u0012\u0004\u0012\u00020\u001d\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010v0z0\u009a\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¦\u0002\u0010\u009c\u0002R0\u0010§\u0002\u001a\u001b\u0012\u0004\u0012\u00020\u001d\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020M0(0z0\u009a\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b§\u0002\u0010\u009c\u0002R*\u0010¨\u0002\u001a\u0015\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0z0¢\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¨\u0002\u0010¤\u0002RC\u0010©\u0002\u001a.\u0012\u0004\u0012\u00020\u001d\u0012#\u0012!\u0012\u001d\u0012\u001b\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00170\u0080\u0001\u0012\u0004\u0012\u00020\u001d0y0z0¢\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b©\u0002\u0010¤\u0002R*\u0010ª\u0002\u001a\u0015\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0z0¢\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bª\u0002\u0010¤\u0002R*\u0010«\u0002\u001a\u0015\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0z0¢\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b«\u0002\u0010¤\u0002R$\u0010¬\u0002\u001a\u000f\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001d0¢\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¬\u0002\u0010¤\u0002R9\u0010\u00ad\u0002\u001a$\u0012\u0004\u0012\u00020\u001d\u0012\u0019\u0012\u0017\u0012\u0013\u0012\u0011\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0080\u00010z0¢\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u00ad\u0002\u0010¤\u0002R9\u0010®\u0002\u001a$\u0012\u0004\u0012\u00020\u001d\u0012\u0019\u0012\u0017\u0012\u0013\u0012\u0011\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0080\u00010z0¢\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b®\u0002\u0010¤\u0002R9\u0010¯\u0002\u001a$\u0012\u0004\u0012\u00020\u001d\u0012\u0019\u0012\u0017\u0012\u0013\u0012\u0011\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0080\u00010z0¢\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¯\u0002\u0010¤\u0002R=\u0010°\u0002\u001a(\u0012\u0004\u0012\u00020\u001d\u0012\u001d\u0012\u001b\u0012\u0017\u0012\u0015\u0012\u0011\u0012\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00170\u0080\u00010(0z0¢\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b°\u0002\u0010¤\u0002¨\u0006·\u0002"}, d2 = {"Lcom/arflix/tv/data/repository/MediaRepository;", "", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/api/TmdbApi;", "tmdbApi", "Lcom/arflix/tv/data/repository/TraktRepository;", "traktRepository", "Lcom/arflix/tv/data/api/TraktApi;", "traktApi", "Lgb/h0;", "okHttpClient", "Lcom/arflix/tv/data/repository/StreamRepository;", "streamRepository", "Lcom/arflix/tv/data/repository/HomeServerRepository;", "homeServerRepository", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/api/TmdbApi;Lcom/arflix/tv/data/repository/TraktRepository;Lcom/arflix/tv/data/api/TraktApi;Lgb/h0;Lcom/arflix/tv/data/repository/StreamRepository;Lcom/arflix/tv/data/repository/HomeServerRepository;)V", "Lx6/t0;", "clearMediaCache", "()V", "Lcom/arflix/tv/data/model/MediaType;", "mediaType", "", "mediaId", "Lcom/arflix/tv/data/model/MediaItem;", "getCachedItem", "(Lcom/arflix/tv/data/model/MediaType;I)Lcom/arflix/tv/data/model/MediaItem;", "getCachedFullItem", "", "imdbId", "cacheImdbId", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/String;)V", "getCachedImdbId", "(Lcom/arflix/tv/data/model/MediaType;I)Ljava/lang/String;", "getImdbRating", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/String;Ld7/d;)Ljava/lang/Object;", "item", "cacheItem", "(Lcom/arflix/tv/data/model/MediaItem;)V", "", "Lcom/arflix/tv/data/model/CatalogConfig;", "getDefaultCatalogConfigs", "()Ljava/util/List;", "Lcom/arflix/tv/data/model/Category;", "getHomeCategories", "(Ld7/d;)Ljava/lang/Object;", "categoryId", "page", "Lcom/arflix/tv/data/repository/MediaRepository$CategoryPageResult;", "loadHomeCategoryPage", "(Ljava/lang/String;ILd7/d;)Ljava/lang/Object;", "catalog", "maxItems", "loadCustomCatalog", "(Lcom/arflix/tv/data/model/CatalogConfig;ILd7/d;)Ljava/lang/Object;", "offset", "limit", "loadCustomCatalogPage", "(Lcom/arflix/tv/data/model/CatalogConfig;IILd7/d;)Ljava/lang/Object;", "sourceRef", "Lcom/arflix/tv/data/repository/HomeServerLibrarySort;", "sort", "searchQuery", "loadHomeServerLibraryPage", "(Ljava/lang/String;IILcom/arflix/tv/data/repository/HomeServerLibrarySort;Lcom/arflix/tv/data/model/MediaType;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "loadCollectionCatalogPage", "movieId", "getMovieDetails", "(ILd7/d;)Ljava/lang/Object;", "tvId", "getTvDetails", "Lcom/arflix/tv/data/api/TmdbCollectionRef;", "getMovieCollectionRef", "collectionId", "getTmdbCollectionItems", "seasonNumber", "Lcom/arflix/tv/data/model/Episode;", "getSeasonEpisodes", "(IILd7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/model/CastMember;", "getCast", "(Lcom/arflix/tv/data/model/MediaType;ILd7/d;)Ljava/lang/Object;", "getSimilar", "getLogoUrl", "(Lcom/arflix/tv/data/model/MediaItem;Ld7/d;)Ljava/lang/Object;", "peekCachedLogoUrl", "peekCachedSeasonEpisodes", "(II)Ljava/util/List;", "getTrailerKey", "personId", "Lcom/arflix/tv/data/model/PersonDetails;", "getPersonDetails", "query", "search", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "maxPeople", "Lcom/arflix/tv/data/repository/PersonMediaSearchResult;", "searchPeopleKnownFor", "genres", "sortBy", "minVoteCount", "language", "year", "keywords", "releaseDateLte", "releaseDateGte", "discoverMovies", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;ILjava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "airDateLte", "airDateGte", "discoverTv", LinkHeader.Parameters.Title, "loadDiscoverCategory", "(Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/model/Review;", "getReviews", "preferredRegion", "Lcom/arflix/tv/data/repository/StreamingServicesResult;", "getStreamingServices", "T", "", "Lcom/arflix/tv/data/repository/MediaRepository$CacheEntry;", "cache", "key", "getFromCache", "(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/Object;", "detailsCacheKey", "Lx6/x;", "getAddonImdbLookupEntry", "(Ljava/lang/String;)Lcom/arflix/tv/data/repository/MediaRepository$CacheEntry;", "getAddonImdbLookup", "(Ljava/lang/String;)Lx6/x;", "value", "cacheAddonImdbLookup", "(Ljava/lang/String;Lx6/x;)V", "getAddonTitleLookupEntry", "cacheAddonTitleLookup", "getCollectionRefsCache", "(Ljava/lang/String;)Ljava/util/List;", "collectionRefsCacheKey", "(Lcom/arflix/tv/data/model/CatalogConfig;)Ljava/lang/String;", "requiredCount", "resolveCollectionCatalogRefs", "resolveImdbId", "Lcom/arflix/tv/data/api/TmdbExternalIds;", "resolveExternalIds", "fetchCinemetaImdbRating", "(Lcom/arflix/tv/data/model/MediaType;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lorg/json/JSONObject;", "meta", "parseCinemetaMetaRating", "(Lorg/json/JSONObject;)Ljava/lang/String;", "episodeNumbers", "getSeasonEpisodeImdbRatings", "(IILjava/util/List;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "getSeriesCinemetaEpisodeRatings", "(ILjava/lang/String;Ld7/d;)Ljava/lang/Object;", "resolveEpisodeImdbIds", "(IILjava/util/List;Ld7/d;)Ljava/lang/Object;", "imdbIds", "getAgregarrImdbRatings", "(Ljava/util/List;Ld7/d;)Ljava/lang/Object;", "fetchCinemetaEpisodeRatings", "cacheFullDetailsItem", "items", "cacheItems", "(Ljava/util/List;)V", "getHomeCategoriesInternal", "loadHomeServerCatalogPage", "Lcom/arflix/tv/data/repository/HomeServerCatalogItem;", "resolveHomeServerCatalogItem", "(Lcom/arflix/tv/data/repository/HomeServerCatalogItem;Ld7/d;)Ljava/lang/Object;", "resolveHomeServerCatalogItemByTitle", "Lcom/arflix/tv/data/model/CollectionSourceConfig;", "source", "resolveCollectionSourceRefs", "(Lcom/arflix/tv/data/model/CollectionSourceConfig;IILd7/d;)Ljava/lang/Object;", "loadCollectionMdblistPublicRefs", "(Lcom/arflix/tv/data/model/CollectionSourceConfig;ILd7/d;)Ljava/lang/Object;", "loadCollectionCuratedRefs", "(Lcom/arflix/tv/data/model/CollectionSourceConfig;I)Ljava/util/List;", "loadCollectionTmdbCollectionRefs", "loadCollectionKeywordRefs", "loadCollectionWatchProviderRefs", "loadCollectionAddonRefs", "loadCollectionGenreRefs", "loadCollectionPersonRefs", "Lkotlin/Function2;", "Ld7/d;", "Lcom/arflix/tv/data/api/TmdbListResponse;", "fetchPage", "loadPagedTmdbDiscoverRefs", "(Lcom/arflix/tv/data/model/MediaType;ILr7/p;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/MediaRepository$AddonCatalogDescriptor;", "descriptor", "loadPagedAddonCollectionRefs", "(Lcom/arflix/tv/data/repository/MediaRepository$AddonCatalogDescriptor;IILd7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/MediaRepository$AddonCatalogRefsPage;", "loadAddonCatalogRefsPage", "Lcom/arflix/tv/data/api/StremioMetaPreview;", "metas", "parseAddonPageRefs", "(Ljava/util/List;Lcom/arflix/tv/data/repository/MediaRepository$AddonCatalogDescriptor;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/repository/MediaRepository$UnresolvedAddonMeta;", "unresolved", "resolveAddonMetaToTmdbRef", "(Lcom/arflix/tv/data/repository/MediaRepository$AddonCatalogDescriptor;Lcom/arflix/tv/data/repository/MediaRepository$UnresolvedAddonMeta;Ld7/d;)Ljava/lang/Object;", "mediaTypeHint", "resolveImdbToTmdbRef", "(Ljava/lang/String;Lcom/arflix/tv/data/model/MediaType;Ld7/d;)Ljava/lang/Object;", "rawTitle", "resolveTitleToTmdbRef", "resolveAddonCatalogDescriptor", "(Lcom/arflix/tv/data/model/CatalogConfig;)Lcom/arflix/tv/data/repository/MediaRepository$AddonCatalogDescriptor;", "decodeCatalogRefPart", "(Ljava/lang/String;)Ljava/lang/String;", "rawType", "normalizeAddonCatalogType", "addonCatalogTypeToMediaType", "(Ljava/lang/String;)Lcom/arflix/tv/data/model/MediaType;", "typeHint", "parseTmdbRefFromAddonMeta", "(Lcom/arflix/tv/data/api/StremioMetaPreview;Lcom/arflix/tv/data/model/MediaType;)Lx6/x;", "extractImdbId", "(Lcom/arflix/tv/data/api/StremioMetaPreview;)Ljava/lang/String;", "resolveHomeServerLogoRef", "resolveHomeServerLogoRefByTitle", "Lcom/arflix/tv/data/api/TmdbWatchProviderRegion;", "allRegions", TtmlNode.TAG_REGION, "findRegionProviders", "(Ljava/util/Map;Ljava/lang/String;)Lcom/arflix/tv/data/api/TmdbWatchProviderRegion;", "regionData", "Lcom/arflix/tv/data/repository/StreamingServiceInfo;", "toStreamingServiceList", "(Lcom/arflix/tv/data/api/TmdbWatchProviderRegion;)Ljava/util/List;", "", "isTop10Catalog", "(Lcom/arflix/tv/data/model/CatalogConfig;)Z", "raw", "canonicalStreamingServiceName", "canonicalName", "bundledStreamingLogoUri", "normalizeWatchRegion", "sourceUrl", "loadTraktCatalogRefs", "Lcom/arflix/tv/data/api/TraktPublicListItem;", "mapTraktItemsToTmdbRefs", "loadMdblistCatalogRefs", "Lcom/arflix/tv/util/ParsedCatalogUrl;", "parseTraktRef", "(Ljava/lang/String;)Lcom/arflix/tv/util/ParsedCatalogUrl;", "payload", "parseMdblistJson", "url", "fetchUrl", "Landroid/content/Context;", "Lcom/arflix/tv/data/api/TmdbApi;", "Lcom/arflix/tv/data/repository/TraktRepository;", "Lcom/arflix/tv/data/api/TraktApi;", "Lgb/h0;", "Lcom/arflix/tv/data/repository/StreamRepository;", "Lcom/arflix/tv/data/repository/HomeServerRepository;", "apiKey", "Ljava/lang/String;", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "contentLanguage", "getContentLanguage", "()Ljava/lang/String;", "setContentLanguage", "(Ljava/lang/String;)V", "", "CACHE_TTL_MS", "J", "cachedHomeCategories", "Ljava/util/List;", "getCachedHomeCategories", "homeCategoriesFetchedAt", "HOME_CATEGORIES_CACHE_MS", "", "detailsCache", "Ljava/util/Map;", "", "fullDetailsCacheKeys", "Ljava/util/Set;", "castCache", "similarCache", "j$/util/concurrent/ConcurrentHashMap", "logoCache", "Lj$/util/concurrent/ConcurrentHashMap;", "reviewsCache", "watchProvidersCache", "seasonEpisodesCache", "imdbRatingCache", "imdbEpisodeRatingsCache", "imdbRatingsByIdCache", "episodeImdbIdCache", "imdbIdCache", "addonImdbToTmdbCache", "addonTitleToTmdbCache", "homeServerLogoRefCache", "collectionRefsCache", "Companion", "CategoryPageResult", "CacheEntry", "AddonCatalogDescriptor", "UnresolvedAddonMeta", "AddonCatalogRefsPage", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class MediaRepository {
    public static final String STREAMING_COLLECTION_ADDON_URL = "https://pastebin.com/raw/P4gfd98n";
    private final Context context;
    private volatile long homeCategoriesFetchedAt;
    private final HomeServerRepository homeServerRepository;
    private final gb.h0 okHttpClient;
    private final StreamRepository streamRepository;
    private final TmdbApi tmdbApi;
    private final TraktApi traktApi;
    private final TraktRepository traktRepository;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final String UPLOADED_COVER_BASE = "https://nuvioapp.space/uploads/covers/";
    private final String apiKey = Constants.INSTANCE.getTMDB_API_KEY();
    private final Gson gson = new Gson();
    private volatile String contentLanguage = "en-US";
    private final long CACHE_TTL_MS = 300000;
    private volatile List<Category> cachedHomeCategories = kotlin.collections.z.f19728i;
    private final long HOME_CATEGORIES_CACHE_MS = 120000;
    private final Map<String, CacheEntry<MediaItem>> detailsCache = new LinkedHashMap();
    private final Set<String> fullDetailsCacheKeys = new LinkedHashSet();
    private final Map<String, CacheEntry<List<CastMember>>> castCache = new LinkedHashMap();
    private final Map<String, CacheEntry<List<MediaItem>>> similarCache = new LinkedHashMap();
    private final ConcurrentHashMap<String, CacheEntry<String>> logoCache = new ConcurrentHashMap<>();
    private final Map<String, CacheEntry<List<Review>>> reviewsCache = new LinkedHashMap();
    private final Map<String, CacheEntry<StreamingServicesResult>> watchProvidersCache = new LinkedHashMap();
    private final Map<String, CacheEntry<List<Episode>>> seasonEpisodesCache = new LinkedHashMap();
    private final ConcurrentHashMap<String, CacheEntry<String>> imdbRatingCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, CacheEntry<Map<x6.x, String>>> imdbEpisodeRatingsCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, CacheEntry<String>> imdbRatingsByIdCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, CacheEntry<String>> episodeImdbIdCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> imdbIdCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, CacheEntry<x6.x>> addonImdbToTmdbCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, CacheEntry<x6.x>> addonTitleToTmdbCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, CacheEntry<x6.x>> homeServerLogoRefCache = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, CacheEntry<List<x6.x>>> collectionRefsCache = new ConcurrentHashMap<>();

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/repository/MediaRepository$AddonCatalogDescriptor;", "", "addonId", "", "catalogType", "catalogId", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAddonId", "()Ljava/lang/String;", "getCatalogType", "getCatalogId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class AddonCatalogDescriptor {
        private final String addonId;
        private final String catalogId;
        private final String catalogType;

        public AddonCatalogDescriptor(String str, String str2, String str3) {
            this.addonId = str;
            this.catalogType = str2;
            this.catalogId = str3;
        }

        public static /* synthetic */ AddonCatalogDescriptor copy$default(AddonCatalogDescriptor addonCatalogDescriptor, String str, String str2, String str3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = addonCatalogDescriptor.addonId;
            }
            if ((i10 & 2) != 0) {
                str2 = addonCatalogDescriptor.catalogType;
            }
            if ((i10 & 4) != 0) {
                str3 = addonCatalogDescriptor.catalogId;
            }
            return addonCatalogDescriptor.copy(str, str2, str3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAddonId() {
            return this.addonId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getCatalogType() {
            return this.catalogType;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getCatalogId() {
            return this.catalogId;
        }

        public final AddonCatalogDescriptor copy(String addonId, String catalogType, String catalogId) {
            return new AddonCatalogDescriptor(addonId, catalogType, catalogId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AddonCatalogDescriptor)) {
                return false;
            }
            AddonCatalogDescriptor addonCatalogDescriptor = (AddonCatalogDescriptor) other;
            return kotlin.jvm.internal.p.a(this.addonId, addonCatalogDescriptor.addonId) && kotlin.jvm.internal.p.a(this.catalogType, addonCatalogDescriptor.catalogType) && kotlin.jvm.internal.p.a(this.catalogId, addonCatalogDescriptor.catalogId);
        }

        public final String getAddonId() {
            return this.addonId;
        }

        public final String getCatalogId() {
            return this.catalogId;
        }

        public final String getCatalogType() {
            return this.catalogType;
        }

        public int hashCode() {
            return this.catalogId.hashCode() + androidx.compose.foundation.c.c(this.addonId.hashCode() * 31, 31, this.catalogType);
        }

        public String toString() {
            String str = this.addonId;
            String str2 = this.catalogType;
            return a0.c.p(a2.r("AddonCatalogDescriptor(addonId=", str, ", catalogType=", str2, ", catalogId="), this.catalogId, ")");
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\f\b\u0082\b\u0018\u00002\u00020\u0001B)\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\"\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0007HÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ6\u0010\u000f\u001a\u00020\u00002\u001a\b\u0002\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u00022\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0017\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0017\u0010\u0018R)\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0019\u001a\u0004\b\u001a\u0010\fR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001b\u001a\u0004\b\u001c\u0010\u000e¨\u0006\u001d"}, d2 = {"Lcom/arflix/tv/data/repository/MediaRepository$AddonCatalogRefsPage;", "", "", "Lx6/x;", "Lcom/arflix/tv/data/model/MediaType;", "", "refs", "", "hasMore", "<init>", "(Ljava/util/List;Z)V", "component1", "()Ljava/util/List;", "component2", "()Z", "copy", "(Ljava/util/List;Z)Lcom/arflix/tv/data/repository/MediaRepository$AddonCatalogRefsPage;", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/util/List;", "getRefs", "Z", "getHasMore", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class AddonCatalogRefsPage {
        private final boolean hasMore;
        private final List<x6.x> refs;

        /* JADX WARN: Multi-variable type inference failed */
        public AddonCatalogRefsPage(List<? extends x6.x> list, boolean z) {
            this.refs = list;
            this.hasMore = z;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ AddonCatalogRefsPage copy$default(AddonCatalogRefsPage addonCatalogRefsPage, List list, boolean z, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                list = addonCatalogRefsPage.refs;
            }
            if ((i10 & 2) != 0) {
                z = addonCatalogRefsPage.hasMore;
            }
            return addonCatalogRefsPage.copy(list, z);
        }

        public final List<x6.x> component1() {
            return this.refs;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getHasMore() {
            return this.hasMore;
        }

        public final AddonCatalogRefsPage copy(List<? extends x6.x> refs, boolean hasMore) {
            return new AddonCatalogRefsPage(refs, hasMore);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AddonCatalogRefsPage)) {
                return false;
            }
            AddonCatalogRefsPage addonCatalogRefsPage = (AddonCatalogRefsPage) other;
            return kotlin.jvm.internal.p.a(this.refs, addonCatalogRefsPage.refs) && this.hasMore == addonCatalogRefsPage.hasMore;
        }

        public final boolean getHasMore() {
            return this.hasMore;
        }

        public final List<x6.x> getRefs() {
            return this.refs;
        }

        public int hashCode() {
            return (this.refs.hashCode() * 31) + (this.hasMore ? 1231 : 1237);
        }

        public String toString() {
            return "AddonCatalogRefsPage(refs=" + this.refs + ", hasMore=" + this.hasMore + ")";
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\r\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J(\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/data/repository/MediaRepository$CacheEntry;", "T", "", "data", "timestamp", "", "<init>", "(Ljava/lang/Object;J)V", "getData", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getTimestamp", "()J", "component1", "component2", "copy", "(Ljava/lang/Object;J)Lcom/arflix/tv/data/repository/MediaRepository$CacheEntry;", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class CacheEntry<T> {
        private final T data;
        private final long timestamp;

        public CacheEntry(T t2, long j10) {
            this.data = t2;
            this.timestamp = j10;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CacheEntry copy$default(CacheEntry cacheEntry, Object obj, long j10, int i10, Object obj2) {
            if ((i10 & 1) != 0) {
                obj = cacheEntry.data;
            }
            if ((i10 & 2) != 0) {
                j10 = cacheEntry.timestamp;
            }
            return cacheEntry.copy(obj, j10);
        }

        public final T component1() {
            return this.data;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getTimestamp() {
            return this.timestamp;
        }

        public final CacheEntry<T> copy(T data, long timestamp) {
            return new CacheEntry<>(data, timestamp);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CacheEntry)) {
                return false;
            }
            CacheEntry cacheEntry = (CacheEntry) other;
            return kotlin.jvm.internal.p.a(this.data, cacheEntry.data) && this.timestamp == cacheEntry.timestamp;
        }

        public final T getData() {
            return this.data;
        }

        public final long getTimestamp() {
            return this.timestamp;
        }

        public int hashCode() {
            T t2 = this.data;
            int iHashCode = t2 == null ? 0 : t2.hashCode();
            long j10 = this.timestamp;
            return (iHashCode * 31) + ((int) (j10 ^ (j10 >>> 32)));
        }

        public String toString() {
            return "CacheEntry(data=" + this.data + ", timestamp=" + this.timestamp + ")";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/repository/MediaRepository$CategoryPageResult;", "", "items", "", "Lcom/arflix/tv/data/model/MediaItem;", "hasMore", "", "<init>", "(Ljava/util/List;Z)V", "getItems", "()Ljava/util/List;", "getHasMore", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class CategoryPageResult {
        public static final int $stable = 0;
        private final boolean hasMore;
        private final List<MediaItem> items;

        public CategoryPageResult(List<MediaItem> list, boolean z) {
            this.items = list;
            this.hasMore = z;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ CategoryPageResult copy$default(CategoryPageResult categoryPageResult, List list, boolean z, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                list = categoryPageResult.items;
            }
            if ((i10 & 2) != 0) {
                z = categoryPageResult.hasMore;
            }
            return categoryPageResult.copy(list, z);
        }

        public final List<MediaItem> component1() {
            return this.items;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getHasMore() {
            return this.hasMore;
        }

        public final CategoryPageResult copy(List<MediaItem> items, boolean hasMore) {
            return new CategoryPageResult(items, hasMore);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CategoryPageResult)) {
                return false;
            }
            CategoryPageResult categoryPageResult = (CategoryPageResult) other;
            return kotlin.jvm.internal.p.a(this.items, categoryPageResult.items) && this.hasMore == categoryPageResult.hasMore;
        }

        public final boolean getHasMore() {
            return this.hasMore;
        }

        public final List<MediaItem> getItems() {
            return this.items;
        }

        public int hashCode() {
            return (this.items.hashCode() * 31) + (this.hasMore ? 1231 : 1237);
        }

        public String toString() {
            return "CategoryPageResult(items=" + this.items + ", hasMore=" + this.hasMore + ")";
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0000¢\u0006\u0002\b\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/arflix/tv/data/repository/MediaRepository$Companion;", "", "<init>", "()V", "STREAMING_COLLECTION_ADDON_URL", "", "UPLOADED_COVER_BASE", "buildPreinstalledDefaults", "", "Lcom/arflix/tv/data/model/CatalogConfig;", "buildPreinstalledDefaults$app_playRelease", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {

        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                int[] iArr = new int[MediaType.values().length];
                try {
                    iArr[MediaType.MOVIE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[MediaType.TV.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
                int[] iArr2 = new int[CollectionGroupKind.values().length];
                try {
                    iArr2[CollectionGroupKind.FRANCHISE.ordinal()] = 1;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr2[CollectionGroupKind.SERVICE.ordinal()] = 2;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr2[CollectionGroupKind.GENRE.ordinal()] = 3;
                } catch (NoSuchFieldError unused5) {
                }
                $EnumSwitchMapping$1 = iArr2;
            }
        }

        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        private static final CollectionSourceConfig buildPreinstalledDefaults$addonCollectionSource(String str, String str2, String str3) {
            return new CollectionSourceConfig(CollectionSourceKind.ADDON_CATALOG, null, str, str2, str3, null, null, null, null, null, null, null, null, null, 16354, null);
        }

        private static final CatalogConfig buildPreinstalledDefaults$collection(String str, String str2, CollectionGroupKind collectionGroupKind, String str3, String str4, String str5, String str6, String str7, String str8, List<CollectionSourceConfig> list, List<String> list2) {
            return new CatalogConfig(str, str2, CatalogSourceType.PREINSTALLED, null, null, true, null, null, null, null, CatalogKind.COLLECTION, collectionGroupKind, str3, str4, str5 == null ? str4 : str5, str6 == null ? str4 : str6, str5 == null ? str6 == null ? str4 : str6 : str5, str7, str8, null, false, list, list2, null, null, 26739672, null);
        }

        public static /* synthetic */ CatalogConfig buildPreinstalledDefaults$collection$default(String str, String str2, CollectionGroupKind collectionGroupKind, String str3, String str4, String str5, String str6, String str7, String str8, List list, List list2, int i10, Object obj) {
            if ((i10 & 16) != 0) {
                str4 = null;
            }
            if ((i10 & 32) != 0) {
                str5 = null;
            }
            if ((i10 & 64) != 0) {
                str6 = null;
            }
            if ((i10 & 128) != 0) {
                str7 = null;
            }
            if ((i10 & 256) != 0) {
                str8 = null;
            }
            if ((i10 & 1024) != 0) {
                list2 = kotlin.collections.z.f19728i;
            }
            return buildPreinstalledDefaults$collection(str, str2, collectionGroupKind, str3, str4, str5, str6, str7, str8, list, list2);
        }

        private static final CollectionSourceConfig buildPreinstalledDefaults$curatedSource(String... strArr) {
            return new CollectionSourceConfig(CollectionSourceKind.CURATED_IDS, null, null, null, null, null, null, null, null, null, null, null, kotlin.collections.r.o0(strArr), null, 12286, null);
        }

        private static final CollectionSourceConfig buildPreinstalledDefaults$mdblistSource(String str) {
            return new CollectionSourceConfig(CollectionSourceKind.MDBLIST_PUBLIC, null, null, null, null, null, null, null, null, null, null, null, null, str, 8190, null);
        }

        private static final List<CollectionSourceConfig> buildPreinstalledDefaults$mergeCollectionSources(List<CollectionSourceConfig> list, List<CollectionSourceConfig> list2) {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            ArrayList arrayListI0 = kotlin.collections.x.I0(list, list2);
            HashSet hashSet = new HashSet();
            ArrayList arrayList = new ArrayList();
            for (Object obj : arrayListI0) {
                CollectionSourceConfig collectionSourceConfig = (CollectionSourceConfig) obj;
                String strName = collectionSourceConfig.getKind().name();
                String mediaType = collectionSourceConfig.getMediaType();
                if (mediaType == null) {
                    mediaType = "";
                }
                String addonId = collectionSourceConfig.getAddonId();
                if (addonId == null) {
                    addonId = "";
                }
                String addonCatalogType = collectionSourceConfig.getAddonCatalogType();
                if (addonCatalogType == null) {
                    addonCatalogType = "";
                }
                String addonCatalogId = collectionSourceConfig.getAddonCatalogId();
                if (addonCatalogId == null) {
                    addonCatalogId = "";
                }
                Integer tmdbGenreId = collectionSourceConfig.getTmdbGenreId();
                String strValueOf = tmdbGenreId != null ? String.valueOf(tmdbGenreId.intValue()) : null;
                if (strValueOf == null) {
                    strValueOf = "";
                }
                Integer tmdbPersonId = collectionSourceConfig.getTmdbPersonId();
                String strValueOf2 = tmdbPersonId != null ? String.valueOf(tmdbPersonId.intValue()) : null;
                if (strValueOf2 == null) {
                    strValueOf2 = "";
                }
                Integer tmdbCollectionId = collectionSourceConfig.getTmdbCollectionId();
                String strValueOf3 = tmdbCollectionId != null ? String.valueOf(tmdbCollectionId.intValue()) : null;
                if (strValueOf3 == null) {
                    strValueOf3 = "";
                }
                Integer tmdbKeywordId = collectionSourceConfig.getTmdbKeywordId();
                String strValueOf4 = tmdbKeywordId != null ? String.valueOf(tmdbKeywordId.intValue()) : null;
                if (strValueOf4 == null) {
                    strValueOf4 = "";
                }
                Integer tmdbWatchProviderId = collectionSourceConfig.getTmdbWatchProviderId();
                String strValueOf5 = tmdbWatchProviderId != null ? String.valueOf(tmdbWatchProviderId.intValue()) : null;
                if (strValueOf5 == null) {
                    strValueOf5 = "";
                }
                String watchRegion = collectionSourceConfig.getWatchRegion();
                if (watchRegion == null) {
                    watchRegion = "";
                }
                String sortBy = collectionSourceConfig.getSortBy();
                if (sortBy == null) {
                    sortBy = "";
                }
                List<String> curatedRefs = collectionSourceConfig.getCuratedRefs();
                String strU0 = curatedRefs != null ? kotlin.collections.x.u0(curatedRefs, ",", null, null, null, 62) : null;
                if (strU0 == null) {
                    strU0 = "";
                }
                String mdblistSlug = collectionSourceConfig.getMdblistSlug();
                if (mdblistSlug == null) {
                    String str9 = sortBy;
                    str = strU0;
                    str2 = strValueOf2;
                    str3 = strValueOf3;
                    str4 = strValueOf4;
                    str5 = strValueOf5;
                    str6 = watchRegion;
                    str7 = str9;
                    str8 = "";
                } else {
                    String str10 = sortBy;
                    str = strU0;
                    str2 = strValueOf2;
                    str3 = strValueOf3;
                    str4 = strValueOf4;
                    str5 = strValueOf5;
                    str6 = watchRegion;
                    str7 = str10;
                    str8 = mdblistSlug;
                }
                if (hashSet.add(kotlin.collections.x.u0(t7.a.E(strName, mediaType, addonId, addonCatalogType, addonCatalogId, strValueOf, str2, str3, str4, str5, str6, str7, str, str8), "|", null, null, null, 62))) {
                    arrayList.add(obj);
                }
            }
            return arrayList;
        }

        private static final CatalogConfig buildPreinstalledDefaults$resolveLegacyCollection(Map<String, CatalogConfig> map, Map<String, String> map2, String str) {
            String lowerCase = kotlin.text.o.L0(str).toString().toLowerCase(Locale.US);
            CatalogConfig catalogConfig = map.get(lowerCase);
            if (catalogConfig != null) {
                return catalogConfig;
            }
            String str2 = map2.get(lowerCase);
            if (str2 != null) {
                return map.get(str2);
            }
            return null;
        }

        private static final CollectionSourceConfig buildPreinstalledDefaults$tmdbCollectionSource(int i10) {
            return new CollectionSourceConfig(CollectionSourceKind.TMDB_COLLECTION, null, null, null, null, null, null, Integer.valueOf(i10), null, null, null, null, null, null, 16254, null);
        }

        private static final CollectionSourceConfig buildPreinstalledDefaults$tmdbGenreSource(MediaType mediaType, int i10) {
            return new CollectionSourceConfig(CollectionSourceKind.TMDB_GENRE, mediaType == MediaType.MOVIE ? "movie" : "series", null, null, null, Integer.valueOf(i10), null, null, null, null, null, "popularity.desc", null, null, 14300, null);
        }

        private static final CollectionSourceConfig buildPreinstalledDefaults$tmdbKeywordSource(MediaType mediaType, int i10) {
            CollectionSourceKind collectionSourceKind = CollectionSourceKind.TMDB_KEYWORD;
            int i11 = mediaType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[mediaType.ordinal()];
            return new CollectionSourceConfig(collectionSourceKind, i11 != 1 ? i11 != 2 ? null : "series" : "movie", null, null, null, null, null, null, Integer.valueOf(i10), null, null, "popularity.desc", null, null, 14076, null);
        }

        private static final CollectionSourceConfig buildPreinstalledDefaults$watchProviderSource(MediaType mediaType, int i10) {
            return new CollectionSourceConfig(CollectionSourceKind.TMDB_WATCH_PROVIDER, mediaType == MediaType.MOVIE ? "movie" : "series", null, null, null, null, null, null, null, Integer.valueOf(i10), "US", "popularity.desc", null, null, 12796, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0c2c  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.util.List<com.arflix.tv.data.model.CatalogConfig> buildPreinstalledDefaults$app_playRelease() {
            /*
                Method dump skipped, instruction units count: 3395
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.Companion.buildPreinstalledDefaults$app_playRelease():java.util.List");
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/data/repository/MediaRepository$UnresolvedAddonMeta;", "", TtmlNode.ATTR_ID, "", "typeHint", "Lcom/arflix/tv/data/model/MediaType;", "<init>", "(Ljava/lang/String;Lcom/arflix/tv/data/model/MediaType;)V", "getId", "()Ljava/lang/String;", "getTypeHint", "()Lcom/arflix/tv/data/model/MediaType;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class UnresolvedAddonMeta {
        private final String id;
        private final MediaType typeHint;

        public UnresolvedAddonMeta(String str, MediaType mediaType) {
            this.id = str;
            this.typeHint = mediaType;
        }

        public static /* synthetic */ UnresolvedAddonMeta copy$default(UnresolvedAddonMeta unresolvedAddonMeta, String str, MediaType mediaType, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = unresolvedAddonMeta.id;
            }
            if ((i10 & 2) != 0) {
                mediaType = unresolvedAddonMeta.typeHint;
            }
            return unresolvedAddonMeta.copy(str, mediaType);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final MediaType getTypeHint() {
            return this.typeHint;
        }

        public final UnresolvedAddonMeta copy(String id, MediaType typeHint) {
            return new UnresolvedAddonMeta(id, typeHint);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UnresolvedAddonMeta)) {
                return false;
            }
            UnresolvedAddonMeta unresolvedAddonMeta = (UnresolvedAddonMeta) other;
            return kotlin.jvm.internal.p.a(this.id, unresolvedAddonMeta.id) && this.typeHint == unresolvedAddonMeta.typeHint;
        }

        public final String getId() {
            return this.id;
        }

        public final MediaType getTypeHint() {
            return this.typeHint;
        }

        public int hashCode() {
            int iHashCode = this.id.hashCode() * 31;
            MediaType mediaType = this.typeHint;
            return iHashCode + (mediaType == null ? 0 : mediaType.hashCode());
        }

        public String toString() {
            return "UnresolvedAddonMeta(id=" + this.id + ", typeHint=" + this.typeHint + ")";
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CollectionSourceKind.values().length];
            try {
                iArr[CollectionSourceKind.ADDON_CATALOG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CollectionSourceKind.MDBLIST_PUBLIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[CollectionSourceKind.TMDB_GENRE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CollectionSourceKind.TMDB_PERSON.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[CollectionSourceKind.TMDB_COLLECTION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[CollectionSourceKind.TMDB_KEYWORD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[CollectionSourceKind.TMDB_WATCH_PROVIDER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[CollectionSourceKind.CURATED_IDS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[MediaType.values().length];
            try {
                iArr2[MediaType.MOVIE.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[MediaType.TV.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$discoverMovies$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3309}, m = "discoverMovies", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.discoverMovies(null, null, null, 0, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$discoverTv$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3329}, m = "discoverTv", v = 2)
    public static final class C08841 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        public C08841(d7.d<? super C08841> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.discoverTv(null, null, null, 0, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$fetchCinemetaEpisodeRatings$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lka/k0;", "", "Lx6/x;", "", "", "<anonymous>", "(Lka/k0;)Ljava/util/Map;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$fetchCinemetaEpisodeRatings$2", f = "MediaRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super Map<x6.x, ? extends String>>, Object> {
        final /* synthetic */ String $imdbId;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ MediaRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, MediaRepository mediaRepository, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$imdbId = str;
            this.this$0 = mediaRepository;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$imdbId, this.this$0, dVar);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x005a  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) {
            /*
                Method dump skipped, instruction units count: 272
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super Map<x6.x, String>> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$fetchCinemetaImdbRating$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$fetchCinemetaImdbRating$2", f = "MediaRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C08852 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
        final /* synthetic */ String $imdbId;
        final /* synthetic */ MediaType $mediaType;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ MediaRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C08852(MediaType mediaType, String str, MediaRepository mediaRepository, d7.d<? super C08852> dVar) {
            super(2, dVar);
            this.$mediaType = mediaType;
            this.$imdbId = str;
            this.this$0 = mediaRepository;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C08852 c08852 = new C08852(this.$mediaType, this.$imdbId, this.this$0, dVar);
            c08852.L$0 = obj;
            return c08852;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object c0Var;
            gb.p0 p0VarD;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            String str = this.$mediaType == MediaType.TV ? "series" : "movie";
            gb.j0 j0Var = new gb.j0();
            j0Var.i(a2.m("https://v3-cinemeta.strem.io/meta/", str, DomExceptionUtils.SEPARATOR, this.$imdbId, ".json"));
            j0Var.f15724c.h("Accept", "application/json");
            j0Var.f15724c.h("User-Agent", OkHttpProvider.INSTANCE.userAgentOr("Mozilla/5.0 (Android TV; ARVIO)"));
            gb.k0 k0VarB = j0Var.b();
            MediaRepository mediaRepository = this.this$0;
            try {
                p0VarD = mediaRepository.okHttpClient.a(k0VarB).d();
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
                JSONObject jSONObjectOptJSONObject = new JSONObject(strX).optJSONObject("meta");
                if (jSONObjectOptJSONObject == null) {
                    c0Var = null;
                    p0VarD.close();
                } else {
                    c0Var = mediaRepository.parseCinemetaMetaRating(jSONObjectOptJSONObject);
                    p0VarD.close();
                }
                c0Var = new x6.c0(th);
            } else {
                c0Var = null;
                p0VarD.close();
            }
            if (c0Var instanceof x6.c0) {
                return null;
            }
            return c0Var;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
            return ((C08852) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getAgregarrImdbRatings$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "", "<anonymous>", "(Lka/k0;)Ljava/util/Map;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$getAgregarrImdbRatings$2", f = "MediaRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C08862 extends f7.j implements r7.p<ka.k0, d7.d<? super Map<String, ? extends String>>, Object> {
        final /* synthetic */ List<String> $imdbIds;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ MediaRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C08862(List<String> list, MediaRepository mediaRepository, d7.d<? super C08862> dVar) {
            super(2, dVar);
            this.$imdbIds = list;
            this.this$0 = mediaRepository;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C08862 c08862 = new C08862(this.$imdbIds, this.this$0, dVar);
            c08862.L$0 = obj;
            return c08862;
        }

        /* JADX WARN: Removed duplicated region for block: B:57:0x016b  */
        /* JADX WARN: Removed duplicated region for block: B:89:0x01d3  */
        /* JADX WARN: Removed duplicated region for block: B:93:0x01e4 A[LOOP:5: B:91:0x01de->B:93:0x01e4, LOOP_END] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r21) {
            /*
                Method dump skipped, instruction units count: 534
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.C08862.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super Map<String, String>> dVar) {
            return ((C08862) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getCast$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3003}, m = "getCast", v = 2)
    public static final class C08871 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C08871(d7.d<? super C08871> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getCast(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getHomeCategories$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/Category;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$getHomeCategories$2", f = "MediaRepository.kt", l = {1608, 1610, 1611, 1613, 1614}, m = "invokeSuspend", v = 2)
    public static final class C08882 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends Category>>, Object> {
        long J$0;
        Object L$0;
        int label;

        public C08882(d7.d<? super C08882> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return MediaRepository.this.new C08882(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:42:0x00d2, code lost:
        
            if (r15 != r7) goto L44;
         */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00ad  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00b6  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x00dd  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instruction units count: 236
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.C08882.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<Category>> dVar) {
            return ((C08882) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getHomeCategoriesInternal$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/data/model/Category;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$getHomeCategoriesInternal$2", f = "MediaRepository.kt", l = {1682, 1687, 1692}, m = "invokeSuspend", v = 2)
    public static final class C08892 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends Category>>, Object> {
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        public C08892(d7.d<? super C08892> dVar) {
            super(2, dVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00b6, code lost:
        
            if (r11 == r8) goto L44;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.Object, ka.k0] */
        /* JADX WARN: Type inference failed for: r9v1 */
        /* JADX WARN: Type inference failed for: r9v10 */
        /* JADX WARN: Type inference failed for: r9v21 */
        /* JADX WARN: Type inference failed for: r9v22 */
        /* JADX WARN: Type inference failed for: r9v23 */
        /* JADX WARN: Type inference failed for: r9v24 */
        /* JADX WARN: Type inference failed for: r9v6, types: [java.util.Collection] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static final java.lang.Object invokeSuspend$fetchUpTo40(ka.k0 r9, r7.p<? super java.lang.Integer, ? super d7.d<? super com.arflix.tv.data.api.TmdbListResponse>, ? extends java.lang.Object> r10, d7.d<? super java.util.List<com.arflix.tv.data.api.TmdbMediaItem>> r11) {
            /*
                Method dump skipped, instruction units count: 270
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.C08892.invokeSuspend$fetchUpTo40(ka.k0, r7.p, d7.d):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static final java.lang.Object invokeSuspend$safeItems(ka.k0 r3, int r4, r7.l<? super d7.d<? super java.util.List<com.arflix.tv.data.api.TmdbMediaItem>>, ? extends java.lang.Object> r5, com.arflix.tv.data.model.MediaType r6, d7.d<? super java.util.List<com.arflix.tv.data.model.MediaItem>> r7) {
            /*
                boolean r3 = r7 instanceof com.arflix.tv.data.repository.MediaRepository$getHomeCategoriesInternal$2$safeItems$1
                if (r3 == 0) goto L13
                r3 = r7
                com.arflix.tv.data.repository.MediaRepository$getHomeCategoriesInternal$2$safeItems$1 r3 = (com.arflix.tv.data.repository.MediaRepository$getHomeCategoriesInternal$2$safeItems$1) r3
                int r0 = r3.label
                r1 = -2147483648(0xffffffff80000000, float:-0.0)
                r2 = r0 & r1
                if (r2 == 0) goto L13
                int r0 = r0 - r1
                r3.label = r0
                goto L18
            L13:
                com.arflix.tv.data.repository.MediaRepository$getHomeCategoriesInternal$2$safeItems$1 r3 = new com.arflix.tv.data.repository.MediaRepository$getHomeCategoriesInternal$2$safeItems$1
                r3.<init>(r7)
            L18:
                java.lang.Object r7 = r3.result
                int r0 = r3.label
                r1 = 1
                if (r0 == 0) goto L42
                if (r0 != r1) goto L3a
                int r4 = r3.I$0
                java.lang.Object r5 = r3.L$3
                ka.k0 r5 = (ka.k0) r5
                java.lang.Object r5 = r3.L$2
                r6 = r5
                com.arflix.tv.data.model.MediaType r6 = (com.arflix.tv.data.model.MediaType) r6
                java.lang.Object r5 = r3.L$1
                r7.l r5 = (r7.l) r5
                java.lang.Object r3 = r3.L$0
                ka.k0 r3 = (ka.k0) r3
                k2.c.G(r7)     // Catch: java.lang.Throwable -> L38
                goto L5e
            L38:
                r3 = move-exception
                goto L61
            L3a:
                java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
                java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
                r3.<init>(r4)
                throw r3
            L42:
                k2.c.G(r7)
                r7 = 0
                r3.L$0 = r7     // Catch: java.lang.Throwable -> L38
                r3.L$1 = r7     // Catch: java.lang.Throwable -> L38
                r3.L$2 = r6     // Catch: java.lang.Throwable -> L38
                r3.L$3 = r7     // Catch: java.lang.Throwable -> L38
                r3.I$0 = r4     // Catch: java.lang.Throwable -> L38
                r7 = 0
                r3.I$1 = r7     // Catch: java.lang.Throwable -> L38
                r3.label = r1     // Catch: java.lang.Throwable -> L38
                java.lang.Object r7 = r5.invoke(r3)     // Catch: java.lang.Throwable -> L38
                e7.a r3 = e7.a.f15033i
                if (r7 != r3) goto L5e
                return r3
            L5e:
                java.util.List r7 = (java.util.List) r7     // Catch: java.lang.Throwable -> L38
                goto L66
            L61:
                x6.c0 r7 = new x6.c0
                r7.<init>(r3)
            L66:
                java.lang.Throwable r3 = x6.d0.a(r7)
                if (r3 != 0) goto L6d
                goto L6f
            L6d:
                kotlin.collections.z r7 = kotlin.collections.z.f19728i
            L6f:
                java.lang.Iterable r7 = (java.lang.Iterable) r7
                java.util.List r3 = kotlin.collections.x.X0(r7, r4)
                java.util.ArrayList r4 = new java.util.ArrayList
                r5 = 10
                int r5 = kotlin.collections.s.U(r3, r5)
                r4.<init>(r5)
                java.util.Iterator r3 = r3.iterator()
            L84:
                boolean r5 = r3.hasNext()
                if (r5 == 0) goto L98
                java.lang.Object r5 = r3.next()
                com.arflix.tv.data.api.TmdbMediaItem r5 = (com.arflix.tv.data.api.TmdbMediaItem) r5
                com.arflix.tv.data.model.MediaItem r5 = com.arflix.tv.data.repository.MediaRepositoryKt.access$toMediaItem(r5, r6)
                r4.add(r5)
                goto L84
            L98:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.C08892.invokeSuspend$safeItems(ka.k0, int, r7.l, com.arflix.tv.data.model.MediaType, d7.d):java.lang.Object");
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C08892 c08892 = MediaRepository.this.new C08892(dVar);
            c08892.L$0 = obj;
            return c08892;
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x01f1  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x020e  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x022f A[LOOP:1: B:31:0x0229->B:33:0x022f, LOOP_END] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) {
            /*
                Method dump skipped, instruction units count: 574
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.C08892.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<Category>> dVar) {
            return ((C08892) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getImdbRating$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {350, 354, 355}, m = "getImdbRating", v = 2)
    public static final class C08901 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C08901(d7.d<? super C08901> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getImdbRating(null, 0, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getLogoUrl$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3070}, m = "getLogoUrl", v = 2)
    public static final class C08911 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C08911(d7.d<? super C08911> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getLogoUrl(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getLogoUrl$3, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3097, 3100, 3101}, m = "getLogoUrl", v = 2)
    public static final class AnonymousClass3 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass3(d7.d<? super AnonymousClass3> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getLogoUrl(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getMovieCollectionRef$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {2905}, m = "getMovieCollectionRef", v = 2)
    public static final class C08921 extends f7.c {
        int I$0;
        int label;
        /* synthetic */ Object result;

        public C08921(d7.d<? super C08921> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getMovieCollectionRef(0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getMovieDetails$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {2846, 2854}, m = "getMovieDetails", v = 2)
    public static final class C08931 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C08931(d7.d<? super C08931> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getMovieDetails(0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getPersonDetails$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3220}, m = "getPersonDetails", v = 2)
    public static final class C08941 extends f7.c {
        int I$0;
        int label;
        /* synthetic */ Object result;

        public C08941(d7.d<? super C08941> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getPersonDetails(0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getReviews$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3389}, m = "getReviews", v = 2)
    public static final class C08951 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C08951(d7.d<? super C08951> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getReviews(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getSeasonEpisodeImdbRatings$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {426, 435, 438}, m = "getSeasonEpisodeImdbRatings", v = 2)
    public static final class C08961 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C08961(d7.d<? super C08961> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getSeasonEpisodeImdbRatings(0, 0, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getSeasonEpisodes$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {2939, 2946, 2958, 2977, 2978}, m = "getSeasonEpisodes", v = 2)
    public static final class C08971 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C08971(d7.d<? super C08971> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getSeasonEpisodes(0, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getSeriesCinemetaEpisodeRatings$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {449, 453}, m = "getSeriesCinemetaEpisodeRatings", v = 2)
    public static final class C08981 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C08981(d7.d<? super C08981> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getSeriesCinemetaEpisodeRatings(0, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getSimilar$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3032, 3045}, m = "getSimilar", v = 2)
    public static final class C08991 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C08991(d7.d<? super C08991> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getSimilar(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getStreamingServices$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3433, 3434}, m = "getStreamingServices", v = 2)
    public static final class C09001 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C09001(d7.d<? super C09001> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getStreamingServices(null, 0, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getTmdbCollectionItems$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {2920}, m = "getTmdbCollectionItems", v = 2)
    public static final class C09011 extends f7.c {
        int I$0;
        int label;
        /* synthetic */ Object result;

        public C09011(d7.d<? super C09011> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getTmdbCollectionItems(0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getTrailerKey$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3195, 3202}, m = "getTrailerKey", v = 2)
    public static final class C09021 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C09021(d7.d<? super C09021> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getTrailerKey(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$getTvDetails$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {2876, 2884}, m = "getTvDetails", v = 2)
    public static final class C09031 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C09031(d7.d<? super C09031> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.getTvDetails(0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadAddonCatalogRefsPage$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/MediaRepository$AddonCatalogRefsPage;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/MediaRepository$AddonCatalogRefsPage;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadAddonCatalogRefsPage$2", f = "MediaRepository.kt", l = {2474, 2488}, m = "invokeSuspend", v = 2)
    public static final class C09042 extends f7.j implements r7.p<ka.k0, d7.d<? super AddonCatalogRefsPage>, Object> {
        final /* synthetic */ CatalogConfig $catalog;
        final /* synthetic */ int $limit;
        final /* synthetic */ int $offset;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09042(CatalogConfig catalogConfig, int i10, int i11, d7.d<? super C09042> dVar) {
            super(2, dVar);
            this.$catalog = catalogConfig;
            this.$offset = i10;
            this.$limit = i11;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09042 c09042 = MediaRepository.this.new C09042(this.$catalog, this.$offset, this.$limit, dVar);
            c09042.L$0 = obj;
            return c09042;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(8:25|(1:79)|26|27|75|28|(11:31|32|33|39|(1:41)|42|(4:45|(2:47|(1:49)(1:50))|51|(1:53)(2:54|(7:57|58|(1:59)|81|62|(0)(0)|(0))))|69|(0)|73|74)|56) */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x00f2, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x00f3, code lost:
        
            r1 = r13;
            r2 = r14;
            r3 = r15;
            r13 = r16;
            r14 = r17;
            r4 = r18;
         */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0110  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0115 A[PHI: r4 r15
          0x0115: PHI (r4v10 java.util.LinkedHashSet) = (r4v3 java.util.LinkedHashSet), (r4v11 java.util.LinkedHashSet) binds: [B:43:0x0113, B:66:0x0174] A[DONT_GENERATE, DONT_INLINE]
          0x0115: PHI (r15v6 int) = (r15v2 int), (r15v10 int) binds: [B:43:0x0113, B:66:0x0174] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:45:0x0118  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x015f A[LOOP:0: B:59:0x0159->B:61:0x015f, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:64:0x0171  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x0173  */
        /* JADX WARN: Removed duplicated region for block: B:67:0x0176  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x0193  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x014d -> B:58:0x0153). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r21) {
            /*
                Method dump skipped, instruction units count: 408
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.C09042.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super AddonCatalogRefsPage> dVar) {
            return ((C09042) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCollectionAddonRefs$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lka/k0;", "", "Lx6/x;", "Lcom/arflix/tv/data/model/MediaType;", "", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCollectionAddonRefs$2", f = "MediaRepository.kt", l = {2302, 2309}, m = "invokeSuspend", v = 2)
    public static final class C09052 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends x6.x>>, Object> {
        final /* synthetic */ int $limit;
        final /* synthetic */ int $offset;
        final /* synthetic */ CollectionSourceConfig $source;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        final /* synthetic */ MediaRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09052(CollectionSourceConfig collectionSourceConfig, MediaRepository mediaRepository, int i10, int i11, d7.d<? super C09052> dVar) {
            super(2, dVar);
            this.$source = collectionSourceConfig;
            this.this$0 = mediaRepository;
            this.$offset = i10;
            this.$limit = i11;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09052 c09052 = new C09052(this.$source, this.this$0, this.$offset, this.$limit, dVar);
            c09052.L$0 = obj;
            return c09052;
        }

        /* JADX WARN: Code restructure failed: missing block: B:42:0x00bf, code lost:
        
            if (r12 == r6) goto L43;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                Method dump skipped, instruction units count: 217
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.C09052.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<? extends x6.x>> dVar) {
            return ((C09052) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCollectionCatalogPage$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/MediaRepository$CategoryPageResult;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/MediaRepository$CategoryPageResult;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCollectionCatalogPage$2", f = "MediaRepository.kt", l = {2027, 2060}, m = "invokeSuspend", v = 2)
    public static final class C09062 extends f7.j implements r7.p<ka.k0, d7.d<? super CategoryPageResult>, Object> {
        final /* synthetic */ CatalogConfig $catalog;
        final /* synthetic */ int $limit;
        final /* synthetic */ int $offset;
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$10;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        final /* synthetic */ MediaRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09062(CatalogConfig catalogConfig, int i10, int i11, MediaRepository mediaRepository, d7.d<? super C09062> dVar) {
            super(2, dVar);
            this.$catalog = catalogConfig;
            this.$limit = i10;
            this.$offset = i11;
            this.this$0 = mediaRepository;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09062 c09062 = new C09062(this.$catalog, this.$limit, this.$offset, this.this$0, dVar);
            c09062.L$0 = obj;
            return c09062;
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0079, code lost:
        
            if (r2 == r8) goto L45;
         */
        /* JADX WARN: Removed duplicated region for block: B:43:0x012d  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0165  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x017d  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x0191  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x0193  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r18) {
            /*
                Method dump skipped, instruction units count: 414
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.C09062.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super CategoryPageResult> dVar) {
            return ((C09062) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCollectionGenreRefs$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/TmdbListResponse;", "page", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCollectionGenreRefs$2", f = "MediaRepository.kt", l = {2334}, m = "invokeSuspend", v = 2)
    public static final class C09072 extends f7.j implements r7.p<Integer, d7.d<? super TmdbListResponse>, Object> {
        final /* synthetic */ int $genreId;
        final /* synthetic */ String $sortBy;
        /* synthetic */ int I$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09072(int i10, String str, d7.d<? super C09072> dVar) {
            super(2, dVar);
            this.$genreId = i10;
            this.$sortBy = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09072 c09072 = MediaRepository.this.new C09072(this.$genreId, this.$sortBy, dVar);
            c09072.I$0 = ((Number) obj).intValue();
            return c09072;
        }

        public final Object invoke(int i10, d7.d<? super TmdbListResponse> dVar) {
            return ((C09072) create(Integer.valueOf(i10), dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.I$0;
            int i11 = this.label;
            if (i11 != 0) {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            TmdbApi tmdbApi = MediaRepository.this.tmdbApi;
            String str = MediaRepository.this.apiKey;
            String strValueOf = String.valueOf(this.$genreId);
            String str2 = this.$sortBy;
            String contentLanguage = MediaRepository.this.getContentLanguage();
            this.I$0 = i10;
            this.label = 1;
            Object objA = com.arflix.tv.data.api.g.a(tmdbApi, str, strValueOf, null, str2, null, null, null, null, null, null, null, null, contentLanguage, i10, this, 4084, null);
            e7.a aVar = e7.a.f15033i;
            return objA == aVar ? aVar : objA;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).intValue(), (d7.d<? super TmdbListResponse>) obj2);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCollectionGenreRefs$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/TmdbListResponse;", "page", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCollectionGenreRefs$3", f = "MediaRepository.kt", l = {2346}, m = "invokeSuspend", v = 2)
    public static final class C09083 extends f7.j implements r7.p<Integer, d7.d<? super TmdbListResponse>, Object> {
        final /* synthetic */ int $genreId;
        final /* synthetic */ String $sortBy;
        /* synthetic */ int I$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09083(int i10, String str, d7.d<? super C09083> dVar) {
            super(2, dVar);
            this.$genreId = i10;
            this.$sortBy = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09083 c09083 = MediaRepository.this.new C09083(this.$genreId, this.$sortBy, dVar);
            c09083.I$0 = ((Number) obj).intValue();
            return c09083;
        }

        public final Object invoke(int i10, d7.d<? super TmdbListResponse> dVar) {
            return ((C09083) create(Integer.valueOf(i10), dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.I$0;
            int i11 = this.label;
            if (i11 != 0) {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            TmdbApi tmdbApi = MediaRepository.this.tmdbApi;
            String str = MediaRepository.this.apiKey;
            String strValueOf = String.valueOf(this.$genreId);
            String contentLanguage = MediaRepository.this.getContentLanguage();
            String str2 = this.$sortBy;
            this.I$0 = i10;
            this.label = 1;
            Object objB = com.arflix.tv.data.api.g.b(tmdbApi, str, null, null, str2, strValueOf, null, null, null, null, null, null, null, contentLanguage, i10, this, 4070, null);
            e7.a aVar = e7.a.f15033i;
            return objB == aVar ? aVar : objB;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).intValue(), (d7.d<? super TmdbListResponse>) obj2);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCollectionKeywordRefs$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lka/k0;", "", "Lx6/x;", "Lcom/arflix/tv/data/model/MediaType;", "", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCollectionKeywordRefs$2", f = "MediaRepository.kt", l = {2192, 2204, 2245, 2245}, m = "invokeSuspend", v = 2)
    public static final class C09092 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends x6.x>>, Object> {
        final /* synthetic */ int $limit;
        final /* synthetic */ CollectionSourceConfig $source;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        final /* synthetic */ MediaRepository this$0;

        /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCollectionKeywordRefs$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/TmdbListResponse;", "page", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
        @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCollectionKeywordRefs$2$1", f = "MediaRepository.kt", l = {2196}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass1 extends f7.j implements r7.p<Integer, d7.d<? super TmdbListResponse>, Object> {
            final /* synthetic */ String $keyword;
            final /* synthetic */ String $sortBy;
            /* synthetic */ int I$0;
            int label;
            final /* synthetic */ MediaRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(MediaRepository mediaRepository, String str, String str2, d7.d<? super AnonymousClass1> dVar) {
                super(2, dVar);
                this.this$0 = mediaRepository;
                this.$sortBy = str;
                this.$keyword = str2;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$sortBy, this.$keyword, dVar);
                anonymousClass1.I$0 = ((Number) obj).intValue();
                return anonymousClass1;
            }

            public final Object invoke(int i10, d7.d<? super TmdbListResponse> dVar) {
                return ((AnonymousClass1) create(Integer.valueOf(i10), dVar)).invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.I$0;
                int i11 = this.label;
                if (i11 != 0) {
                    if (i11 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    return obj;
                }
                k2.c.G(obj);
                TmdbApi tmdbApi = this.this$0.tmdbApi;
                String str = this.this$0.apiKey;
                String contentLanguage = this.this$0.getContentLanguage();
                String str2 = this.$sortBy;
                String str3 = this.$keyword;
                this.I$0 = i10;
                this.label = 1;
                Object objA = com.arflix.tv.data.api.g.a(tmdbApi, str, null, null, str2, null, str3, null, null, null, null, null, null, contentLanguage, i10, this, 4054, null);
                e7.a aVar = e7.a.f15033i;
                return objA == aVar ? aVar : objA;
            }

            @Override // r7.p
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke(((Number) obj).intValue(), (d7.d<? super TmdbListResponse>) obj2);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCollectionKeywordRefs$2$2, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/TmdbListResponse;", "page", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
        @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCollectionKeywordRefs$2$2", f = "MediaRepository.kt", l = {2208}, m = "invokeSuspend", v = 2)
        public static final class C01322 extends f7.j implements r7.p<Integer, d7.d<? super TmdbListResponse>, Object> {
            final /* synthetic */ String $keyword;
            final /* synthetic */ String $sortBy;
            /* synthetic */ int I$0;
            int label;
            final /* synthetic */ MediaRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01322(MediaRepository mediaRepository, String str, String str2, d7.d<? super C01322> dVar) {
                super(2, dVar);
                this.this$0 = mediaRepository;
                this.$sortBy = str;
                this.$keyword = str2;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                C01322 c01322 = new C01322(this.this$0, this.$sortBy, this.$keyword, dVar);
                c01322.I$0 = ((Number) obj).intValue();
                return c01322;
            }

            public final Object invoke(int i10, d7.d<? super TmdbListResponse> dVar) {
                return ((C01322) create(Integer.valueOf(i10), dVar)).invokeSuspend(t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.I$0;
                int i11 = this.label;
                if (i11 != 0) {
                    if (i11 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    return obj;
                }
                k2.c.G(obj);
                TmdbApi tmdbApi = this.this$0.tmdbApi;
                String str = this.this$0.apiKey;
                String contentLanguage = this.this$0.getContentLanguage();
                String str2 = this.$sortBy;
                String str3 = this.$keyword;
                this.I$0 = i10;
                this.label = 1;
                Object objB = com.arflix.tv.data.api.g.b(tmdbApi, str, null, null, str2, null, null, null, null, null, str3, null, null, contentLanguage, i10, this, 3574, null);
                e7.a aVar = e7.a.f15033i;
                return objB == aVar ? aVar : objB;
            }

            @Override // r7.p
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke(((Number) obj).intValue(), (d7.d<? super TmdbListResponse>) obj2);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09092(CollectionSourceConfig collectionSourceConfig, MediaRepository mediaRepository, int i10, d7.d<? super C09092> dVar) {
            super(2, dVar);
            this.$source = collectionSourceConfig;
            this.this$0 = mediaRepository;
            this.$limit = i10;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09092 c09092 = new C09092(this.$source, this.this$0, this.$limit, dVar);
            c09092.L$0 = obj;
            return c09092;
        }

        /* JADX WARN: Code restructure failed: missing block: B:40:0x00ea, code lost:
        
            if (r0 == r7) goto L63;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x0126, code lost:
        
            if (r0 == r7) goto L63;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x0172, code lost:
        
            if (r0 != r7) goto L64;
         */
        /* JADX WARN: Removed duplicated region for block: B:53:0x010d  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instruction units count: 389
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.C09092.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<? extends x6.x>> dVar) {
            return ((C09092) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCollectionMdblistPublicRefs$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {CastStatusCodes.ERROR_CAST_PLATFORM_INCOMPATIBLE}, m = "loadCollectionMdblistPublicRefs", v = 2)
    public static final class C09101 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C09101(d7.d<? super C09101> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.loadCollectionMdblistPublicRefs(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCollectionPersonRefs$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/TmdbListResponse;", "page", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCollectionPersonRefs$2", f = "MediaRepository.kt", l = {2369}, m = "invokeSuspend", v = 2)
    public static final class C09112 extends f7.j implements r7.p<Integer, d7.d<? super TmdbListResponse>, Object> {
        final /* synthetic */ int $personId;
        final /* synthetic */ String $sortBy;
        /* synthetic */ int I$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09112(int i10, String str, d7.d<? super C09112> dVar) {
            super(2, dVar);
            this.$personId = i10;
            this.$sortBy = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09112 c09112 = MediaRepository.this.new C09112(this.$personId, this.$sortBy, dVar);
            c09112.I$0 = ((Number) obj).intValue();
            return c09112;
        }

        public final Object invoke(int i10, d7.d<? super TmdbListResponse> dVar) {
            return ((C09112) create(Integer.valueOf(i10), dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.I$0;
            int i11 = this.label;
            if (i11 != 0) {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            TmdbApi tmdbApi = MediaRepository.this.tmdbApi;
            String str = MediaRepository.this.apiKey;
            String strValueOf = String.valueOf(this.$personId);
            String str2 = this.$sortBy;
            String contentLanguage = MediaRepository.this.getContentLanguage();
            this.I$0 = i10;
            this.label = 1;
            Object objA = com.arflix.tv.data.api.g.a(tmdbApi, str, null, strValueOf, str2, null, null, null, null, null, null, null, null, contentLanguage, i10, this, 4082, null);
            e7.a aVar = e7.a.f15033i;
            return objA == aVar ? aVar : objA;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).intValue(), (d7.d<? super TmdbListResponse>) obj2);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCollectionPersonRefs$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/TmdbListResponse;", "page", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCollectionPersonRefs$3", f = "MediaRepository.kt", l = {2381}, m = "invokeSuspend", v = 2)
    public static final class C09123 extends f7.j implements r7.p<Integer, d7.d<? super TmdbListResponse>, Object> {
        final /* synthetic */ int $personId;
        final /* synthetic */ String $sortBy;
        /* synthetic */ int I$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09123(int i10, String str, d7.d<? super C09123> dVar) {
            super(2, dVar);
            this.$personId = i10;
            this.$sortBy = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09123 c09123 = MediaRepository.this.new C09123(this.$personId, this.$sortBy, dVar);
            c09123.I$0 = ((Number) obj).intValue();
            return c09123;
        }

        public final Object invoke(int i10, d7.d<? super TmdbListResponse> dVar) {
            return ((C09123) create(Integer.valueOf(i10), dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.I$0;
            int i11 = this.label;
            if (i11 != 0) {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            TmdbApi tmdbApi = MediaRepository.this.tmdbApi;
            String str = MediaRepository.this.apiKey;
            String strValueOf = String.valueOf(this.$personId);
            String contentLanguage = MediaRepository.this.getContentLanguage();
            String str2 = this.$sortBy;
            this.I$0 = i10;
            this.label = 1;
            Object objB = com.arflix.tv.data.api.g.b(tmdbApi, str, null, null, str2, null, strValueOf, null, null, null, null, null, null, contentLanguage, i10, this, 4054, null);
            e7.a aVar = e7.a.f15033i;
            return objB == aVar ? aVar : objB;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).intValue(), (d7.d<? super TmdbListResponse>) obj2);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCollectionTmdbCollectionRefs$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {2164}, m = "loadCollectionTmdbCollectionRefs", v = 2)
    public static final class C09131 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C09131(d7.d<? super C09131> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.loadCollectionTmdbCollectionRefs(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCollectionWatchProviderRefs$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/TmdbListResponse;", "page", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCollectionWatchProviderRefs$2", f = "MediaRepository.kt", l = {2268}, m = "invokeSuspend", v = 2)
    public static final class C09142 extends f7.j implements r7.p<Integer, d7.d<? super TmdbListResponse>, Object> {
        final /* synthetic */ int $providerId;
        final /* synthetic */ String $region;
        final /* synthetic */ String $sortBy;
        /* synthetic */ int I$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09142(String str, int i10, String str2, d7.d<? super C09142> dVar) {
            super(2, dVar);
            this.$sortBy = str;
            this.$providerId = i10;
            this.$region = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09142 c09142 = MediaRepository.this.new C09142(this.$sortBy, this.$providerId, this.$region, dVar);
            c09142.I$0 = ((Number) obj).intValue();
            return c09142;
        }

        public final Object invoke(int i10, d7.d<? super TmdbListResponse> dVar) {
            return ((C09142) create(Integer.valueOf(i10), dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.I$0;
            int i11 = this.label;
            if (i11 != 0) {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            TmdbApi tmdbApi = MediaRepository.this.tmdbApi;
            String str = MediaRepository.this.apiKey;
            String contentLanguage = MediaRepository.this.getContentLanguage();
            String str2 = this.$sortBy;
            Integer num = new Integer(this.$providerId);
            String str3 = this.$region;
            this.I$0 = i10;
            this.label = 1;
            Object objA = com.arflix.tv.data.api.g.a(tmdbApi, str, null, null, str2, null, null, null, null, null, null, num, str3, contentLanguage, i10, this, AnalyticsListener.EVENT_AUDIO_SINK_ERROR, null);
            e7.a aVar = e7.a.f15033i;
            return objA == aVar ? aVar : objA;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).intValue(), (d7.d<? super TmdbListResponse>) obj2);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCollectionWatchProviderRefs$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lcom/arflix/tv/data/api/TmdbListResponse;", "page", ""}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCollectionWatchProviderRefs$3", f = "MediaRepository.kt", l = {2281}, m = "invokeSuspend", v = 2)
    public static final class C09153 extends f7.j implements r7.p<Integer, d7.d<? super TmdbListResponse>, Object> {
        final /* synthetic */ int $providerId;
        final /* synthetic */ String $region;
        final /* synthetic */ String $sortBy;
        /* synthetic */ int I$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09153(int i10, String str, String str2, d7.d<? super C09153> dVar) {
            super(2, dVar);
            this.$providerId = i10;
            this.$region = str;
            this.$sortBy = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09153 c09153 = MediaRepository.this.new C09153(this.$providerId, this.$region, this.$sortBy, dVar);
            c09153.I$0 = ((Number) obj).intValue();
            return c09153;
        }

        public final Object invoke(int i10, d7.d<? super TmdbListResponse> dVar) {
            return ((C09153) create(Integer.valueOf(i10), dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.I$0;
            int i11 = this.label;
            if (i11 != 0) {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return obj;
            }
            k2.c.G(obj);
            TmdbApi tmdbApi = MediaRepository.this.tmdbApi;
            String str = MediaRepository.this.apiKey;
            Integer num = new Integer(this.$providerId);
            String str2 = this.$region;
            String str3 = this.$sortBy;
            String contentLanguage = MediaRepository.this.getContentLanguage();
            this.I$0 = i10;
            this.label = 1;
            Object objB = com.arflix.tv.data.api.g.b(tmdbApi, str, num, str2, str3, null, null, null, null, null, null, null, null, contentLanguage, i10, this, 4080, null);
            e7.a aVar = e7.a.f15033i;
            return objB == aVar ? aVar : objB;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).intValue(), (d7.d<? super TmdbListResponse>) obj2);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCustomCatalog$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/model/Category;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/model/Category;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCustomCatalog$2", f = "MediaRepository.kt", l = {1749, 1754, 1758, 1759, 1760, 1779}, m = "invokeSuspend", v = 2)
    public static final class C09162 extends f7.j implements r7.p<ka.k0, d7.d<? super Category>, Object> {
        final /* synthetic */ CatalogConfig $catalog;
        final /* synthetic */ int $maxItems;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        final /* synthetic */ MediaRepository this$0;

        /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCustomCatalog$2$WhenMappings */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[CatalogSourceType.values().length];
                try {
                    iArr[CatalogSourceType.TRAKT.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[CatalogSourceType.MDBLIST.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[CatalogSourceType.ADDON.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[CatalogSourceType.PREINSTALLED.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[CatalogSourceType.HOME_SERVER.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09162(CatalogConfig catalogConfig, MediaRepository mediaRepository, int i10, d7.d<? super C09162> dVar) {
            super(2, dVar);
            this.$catalog = catalogConfig;
            this.this$0 = mediaRepository;
            this.$maxItems = i10;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09162 c09162 = new C09162(this.$catalog, this.this$0, this.$maxItems, dVar);
            c09162.L$0 = obj;
            return c09162;
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0092, code lost:
        
            if (r1 == r8) goto L74;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x00e5, code lost:
        
            if (r1 == r8) goto L74;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x013e, code lost:
        
            if (r9 == r8) goto L74;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x0161, code lost:
        
            if (r9 == r8) goto L74;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x0181, code lost:
        
            if (r9 == r8) goto L74;
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x021a, code lost:
        
            if (r11 == r8) goto L74;
         */
        /* JADX WARN: Removed duplicated region for block: B:65:0x0190  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x01ea  */
        /* JADX WARN: Removed duplicated region for block: B:78:0x0225  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:73:0x021a -> B:75:0x021d). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r20) {
            /*
                Method dump skipped, instruction units count: 594
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.C09162.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super Category> dVar) {
            return ((C09162) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCustomCatalogPage$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/MediaRepository$CategoryPageResult;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/MediaRepository$CategoryPageResult;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadCustomCatalogPage$2", f = "MediaRepository.kt", l = {1794, 1806, 1808, 1813, 1814, 1843}, m = "invokeSuspend", v = 2)
    public static final class C09172 extends f7.j implements r7.p<ka.k0, d7.d<? super CategoryPageResult>, Object> {
        final /* synthetic */ CatalogConfig $catalog;
        final /* synthetic */ int $limit;
        final /* synthetic */ int $offset;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        int I$6;
        int I$7;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        final /* synthetic */ MediaRepository this$0;

        /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadCustomCatalogPage$2$WhenMappings */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[CatalogSourceType.values().length];
                try {
                    iArr[CatalogSourceType.TRAKT.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[CatalogSourceType.MDBLIST.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[CatalogSourceType.ADDON.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[CatalogSourceType.PREINSTALLED.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[CatalogSourceType.HOME_SERVER.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09172(CatalogConfig catalogConfig, MediaRepository mediaRepository, int i10, int i11, d7.d<? super C09172> dVar) {
            super(2, dVar);
            this.$catalog = catalogConfig;
            this.this$0 = mediaRepository;
            this.$offset = i10;
            this.$limit = i11;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09172 c09172 = new C09172(this.$catalog, this.this$0, this.$offset, this.$limit, dVar);
            c09172.L$0 = obj;
            return c09172;
        }

        /* JADX WARN: Code restructure failed: missing block: B:46:0x010f, code lost:
        
            if (r7 == r10) goto L94;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x016c, code lost:
        
            if (r12 == r10) goto L94;
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x018f, code lost:
        
            if (r12 == r10) goto L94;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x0260, code lost:
        
            if (r6 == r10) goto L94;
         */
        /* JADX WARN: Path cross not found for [B:50:0x011f, B:53:0x012b], limit reached: 103 */
        /* JADX WARN: Removed duplicated region for block: B:74:0x019f  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x01a5  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x01e9 A[LOOP:0: B:86:0x01e3->B:88:0x01e9, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:92:0x022c  */
        /* JADX WARN: Removed duplicated region for block: B:98:0x026b  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:93:0x0260 -> B:95:0x0263). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r21) {
            /*
                Method dump skipped, instruction units count: 668
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.C09172.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super CategoryPageResult> dVar) {
            return ((C09172) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadDiscoverCategory$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3343, 3347, 3351, 3355, 3363}, m = "loadDiscoverCategory", v = 2)
    public static final class C09181 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C09181(d7.d<? super C09181> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.loadDiscoverCategory(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadHomeCategoryPage$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {1716, 1717, 1718}, m = "loadHomeCategoryPage", v = 2)
    public static final class C09191 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C09191(d7.d<? super C09191> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.loadHomeCategoryPage(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadHomeServerCatalogPage$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/data/repository/MediaRepository$CategoryPageResult;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/data/repository/MediaRepository$CategoryPageResult;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$loadHomeServerCatalogPage$2", f = "MediaRepository.kt", l = {1858, 1874}, m = "invokeSuspend", v = 2)
    public static final class C09202 extends f7.j implements r7.p<ka.k0, d7.d<? super CategoryPageResult>, Object> {
        final /* synthetic */ CatalogConfig $catalog;
        final /* synthetic */ int $limit;
        final /* synthetic */ int $offset;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$10;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09202(CatalogConfig catalogConfig, int i10, int i11, d7.d<? super C09202> dVar) {
            super(2, dVar);
            this.$catalog = catalogConfig;
            this.$offset = i10;
            this.$limit = i11;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09202 c09202 = MediaRepository.this.new C09202(this.$catalog, this.$offset, this.$limit, dVar);
            c09202.L$0 = obj;
            return c09202;
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0075, code lost:
        
            if (r0 == r15) goto L26;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0102, code lost:
        
            if (r6 == r15) goto L26;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0104, code lost:
        
            return r15;
         */
        /* JADX WARN: Removed duplicated region for block: B:24:0x00d6  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x010d  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0102 -> B:27:0x0105). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) {
            /*
                Method dump skipped, instruction units count: 361
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.C09202.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super CategoryPageResult> dVar) {
            return ((C09202) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadHomeServerLibraryPage$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {1899}, m = "loadHomeServerLibraryPage", v = 2)
    public static final class C09211 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C09211(d7.d<? super C09211> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.loadHomeServerLibraryPage(null, 0, 0, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadMdblistCatalogRefs$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3691, 3705}, m = "loadMdblistCatalogRefs", v = 2)
    public static final class C09221 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C09221(d7.d<? super C09221> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.loadMdblistCatalogRefs(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadPagedAddonCollectionRefs$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {2424, 2433}, m = "loadPagedAddonCollectionRefs", v = 2)
    public static final class C09231 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C09231(d7.d<? super C09231> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.loadPagedAddonCollectionRefs(null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadPagedTmdbDiscoverRefs$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {2403}, m = "loadPagedTmdbDiscoverRefs", v = 2)
    public static final class C09241 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C09241(d7.d<? super C09241> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.loadPagedTmdbDiscoverRefs(null, 0, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$loadTraktCatalogRefs$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3614, 3618}, m = "loadTraktCatalogRefs", v = 2)
    public static final class C09251 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C09251(d7.d<? super C09251> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.loadTraktCatalogRefs(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$mapTraktItemsToTmdbRefs$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lka/k0;", "", "Lx6/x;", "Lcom/arflix/tv/data/model/MediaType;", "", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$mapTraktItemsToTmdbRefs$2", f = "MediaRepository.kt", l = {3682}, m = "invokeSuspend", v = 2)
    public static final class C09262 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends x6.x>>, Object> {
        final /* synthetic */ List<TraktPublicListItem> $items;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        final /* synthetic */ MediaRepository this$0;

        /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$mapTraktItemsToTmdbRefs$2$Unresolved */
        @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ.\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"com/arflix/tv/data/repository/MediaRepository$mapTraktItemsToTmdbRefs$2$Unresolved", "", LinkHeader.Parameters.Type, "Lcom/arflix/tv/data/model/MediaType;", LinkHeader.Parameters.Title, "", "year", "", "<init>", "(Lcom/arflix/tv/data/model/MediaType;Ljava/lang/String;Ljava/lang/Integer;)V", "getType", "()Lcom/arflix/tv/data/model/MediaType;", "getTitle", "()Ljava/lang/String;", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "copy", "(Lcom/arflix/tv/data/model/MediaType;Ljava/lang/String;Ljava/lang/Integer;)Lcom/arflix/tv/data/repository/MediaRepository$mapTraktItemsToTmdbRefs$2$Unresolved;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final /* data */ class Unresolved {
            private final String title;
            private final MediaType type;
            private final Integer year;

            public Unresolved(MediaType mediaType, String str, Integer num) {
                this.type = mediaType;
                this.title = str;
                this.year = num;
            }

            public static /* synthetic */ Unresolved copy$default(Unresolved unresolved, MediaType mediaType, String str, Integer num, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    mediaType = unresolved.type;
                }
                if ((i10 & 2) != 0) {
                    str = unresolved.title;
                }
                if ((i10 & 4) != 0) {
                    num = unresolved.year;
                }
                return unresolved.copy(mediaType, str, num);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final MediaType getType() {
                return this.type;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getTitle() {
                return this.title;
            }

            /* JADX INFO: renamed from: component3, reason: from getter */
            public final Integer getYear() {
                return this.year;
            }

            public final Unresolved copy(MediaType type, String title, Integer year) {
                return new Unresolved(type, title, year);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Unresolved)) {
                    return false;
                }
                Unresolved unresolved = (Unresolved) other;
                return this.type == unresolved.type && kotlin.jvm.internal.p.a(this.title, unresolved.title) && kotlin.jvm.internal.p.a(this.year, unresolved.year);
            }

            public final String getTitle() {
                return this.title;
            }

            public final MediaType getType() {
                return this.type;
            }

            public final Integer getYear() {
                return this.year;
            }

            public int hashCode() {
                int iC = androidx.compose.foundation.c.c(this.type.hashCode() * 31, 31, this.title);
                Integer num = this.year;
                return iC + (num == null ? 0 : num.hashCode());
            }

            public String toString() {
                return "Unresolved(type=" + this.type + ", title=" + this.title + ", year=" + this.year + ")";
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09262(List<TraktPublicListItem> list, MediaRepository mediaRepository, d7.d<? super C09262> dVar) {
            super(2, dVar);
            this.$items = list;
            this.this$0 = mediaRepository;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09262 c09262 = new C09262(this.$items, this.this$0, dVar);
            c09262.L$0 = obj;
            return c09262;
        }

        /* JADX WARN: Removed duplicated region for block: B:74:0x0173  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x01aa  */
        /* JADX WARN: Removed duplicated region for block: B:80:0x01ae  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:75:0x01a3 -> B:77:0x01a6). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                Method dump skipped, instruction units count: 441
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.C09262.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<? extends x6.x>> dVar) {
            return ((C09262) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$parseAddonPageRefs$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lka/k0;", "", "Lx6/x;", "Lcom/arflix/tv/data/model/MediaType;", "", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$parseAddonPageRefs$2", f = "MediaRepository.kt", l = {2562, 2571, 2580}, m = "invokeSuspend", v = 2)
    public static final class C09272 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends x6.x>>, Object> {
        final /* synthetic */ AddonCatalogDescriptor $descriptor;
        final /* synthetic */ List<StremioMetaPreview> $metas;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
        Object L$18;
        Object L$19;
        Object L$2;
        Object L$20;
        Object L$21;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09272(AddonCatalogDescriptor addonCatalogDescriptor, List<StremioMetaPreview> list, d7.d<? super C09272> dVar) {
            super(2, dVar);
            this.$descriptor = addonCatalogDescriptor;
            this.$metas = list;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09272 c09272 = MediaRepository.this.new C09272(this.$descriptor, this.$metas, dVar);
            c09272.L$0 = obj;
            return c09272;
        }

        /* JADX WARN: Code restructure failed: missing block: B:101:0x0424, code lost:
        
            if (r7 == r9) goto L102;
         */
        /* JADX WARN: Code restructure failed: missing block: B:102:0x0426, code lost:
        
            return r9;
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x02d5, code lost:
        
            if (r3 == r9) goto L102;
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x0376, code lost:
        
            if (r3 == r9) goto L102;
         */
        /* JADX WARN: Removed duplicated region for block: B:100:0x03e0  */
        /* JADX WARN: Removed duplicated region for block: B:106:0x042f  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x0298  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x02e1  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x0334  */
        /* JADX WARN: Removed duplicated region for block: B:93:0x0384  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:101:0x0424 -> B:103:0x0427). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:74:0x02d5 -> B:76:0x02d9). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:87:0x0376 -> B:89:0x037a). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r26) {
            /*
                Method dump skipped, instruction units count: 1090
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.C09272.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<? extends x6.x>> dVar) {
            return ((C09272) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$resolveAddonMetaToTmdbRef$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {2595, 2604}, m = "resolveAddonMetaToTmdbRef", v = 2)
    public static final class C09281 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C09281(d7.d<? super C09281> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.resolveAddonMetaToTmdbRef(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$resolveCollectionCatalogRefs$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {275}, m = "resolveCollectionCatalogRefs", v = 2)
    public static final class C09291 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C09291(d7.d<? super C09291> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.resolveCollectionCatalogRefs(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$resolveCollectionSourceRefs$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {2079, 2080, 2081, 2082, 2083, 2084, 2086}, m = "resolveCollectionSourceRefs", v = 2)
    public static final class C09301 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C09301(d7.d<? super C09301> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.resolveCollectionSourceRefs(null, 0, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$resolveEpisodeImdbIds$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "", "", "", "<anonymous>", "(Lka/k0;)Ljava/util/Map;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository$resolveEpisodeImdbIds$2", f = "MediaRepository.kt", l = {483}, m = "invokeSuspend", v = 2)
    public static final class C09312 extends f7.j implements r7.p<ka.k0, d7.d<? super Map<Integer, ? extends String>>, Object> {
        final /* synthetic */ List<Integer> $episodeNumbers;
        final /* synthetic */ int $seasonNumber;
        final /* synthetic */ int $tvId;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ MediaRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C09312(List<Integer> list, int i10, int i11, MediaRepository mediaRepository, d7.d<? super C09312> dVar) {
            super(2, dVar);
            this.$episodeNumbers = list;
            this.$tvId = i10;
            this.$seasonNumber = i11;
            this.this$0 = mediaRepository;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C09312 c09312 = new C09312(this.$episodeNumbers, this.$tvId, this.$seasonNumber, this.this$0, dVar);
            c09312.L$0 = obj;
            return c09312;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ka.k0 k0Var = (ka.k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                ua.i iVarA = ua.j.a(8);
                List listG0 = kotlin.collections.x.g0(this.$episodeNumbers);
                int i11 = this.$tvId;
                int i12 = this.$seasonNumber;
                MediaRepository mediaRepository = this.this$0;
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(listG0, 10));
                Iterator it = listG0.iterator();
                while (it.hasNext()) {
                    arrayList.add(ka.m0.e(2, x0.f19655d, k0Var, new MediaRepository$resolveEpisodeImdbIds$2$1$1(i11, i12, ((Number) it.next()).intValue(), mediaRepository, iVarA, null)));
                }
                this.L$0 = null;
                this.L$1 = null;
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
            return kotlin.collections.h0.A0(kotlin.collections.x.k0((Iterable) obj));
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super Map<Integer, String>> dVar) {
            return ((C09312) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$resolveExternalIds$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {379, 380}, m = "resolveExternalIds", v = 2)
    public static final class C09321 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C09321(d7.d<? super C09321> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.resolveExternalIds(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$resolveHomeServerCatalogItem$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {1939, 1940, 1945, 1948, 1949, 1954}, m = "resolveHomeServerCatalogItem", v = 2)
    public static final class C09331 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C09331(d7.d<? super C09331> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.resolveHomeServerCatalogItem(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$resolveHomeServerCatalogItemByTitle$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {1962, 1969, 2012, 2013}, m = "resolveHomeServerCatalogItemByTitle", v = 2)
    public static final class C09341 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C09341(d7.d<? super C09341> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.resolveHomeServerCatalogItemByTitle(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$resolveHomeServerLogoRef$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3118, 3119}, m = "resolveHomeServerLogoRef", v = 2)
    public static final class C09351 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C09351(d7.d<? super C09351> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.resolveHomeServerLogoRef(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$resolveHomeServerLogoRefByTitle$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3130, 3137}, m = "resolveHomeServerLogoRefByTitle", v = 2)
    public static final class C09361 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C09361(d7.d<? super C09361> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.resolveHomeServerLogoRefByTitle(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$resolveImdbId$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {373}, m = "resolveImdbId", v = 2)
    public static final class C09371 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C09371(d7.d<? super C09371> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.resolveImdbId(null, 0, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$resolveImdbToTmdbRef$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {2619}, m = "resolveImdbToTmdbRef", v = 2)
    public static final class C09381 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C09381(d7.d<? super C09381> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.resolveImdbToTmdbRef(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$resolveTitleToTmdbRef$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {2672}, m = "resolveTitleToTmdbRef", v = 2)
    public static final class C09391 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C09391(d7.d<? super C09391> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.resolveTitleToTmdbRef(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$search$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3228}, m = "search", v = 2)
    public static final class C09401 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C09401(d7.d<? super C09401> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.search(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.MediaRepository$searchPeopleKnownFor$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.MediaRepository", f = "MediaRepository.kt", l = {3251, 3277}, m = "searchPeopleKnownFor", v = 2)
    public static final class C09411 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        int I$6;
        int I$7;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C09411(d7.d<? super C09411> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return MediaRepository.this.searchPeopleKnownFor(null, 0, this);
        }
    }

    @Inject
    public MediaRepository(@ApplicationContext Context context, TmdbApi tmdbApi, TraktRepository traktRepository, TraktApi traktApi, gb.h0 h0Var, StreamRepository streamRepository, HomeServerRepository homeServerRepository) {
        this.context = context;
        this.tmdbApi = tmdbApi;
        this.traktRepository = traktRepository;
        this.traktApi = traktApi;
        this.okHttpClient = h0Var;
        this.streamRepository = streamRepository;
        this.homeServerRepository = homeServerRepository;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final MediaType addonCatalogTypeToMediaType(String rawType) {
        String strNormalizeAddonCatalogType = normalizeAddonCatalogType(rawType);
        if (strNormalizeAddonCatalogType == null) {
            return null;
        }
        switch (strNormalizeAddonCatalogType.hashCode()) {
            case -905838985:
                if (!strNormalizeAddonCatalogType.equals("series")) {
                    return null;
                }
                break;
            case 3714:
                if (!strNormalizeAddonCatalogType.equals("tv")) {
                    return null;
                }
                break;
            case 3529469:
                if (!strNormalizeAddonCatalogType.equals("show")) {
                    return null;
                }
                break;
            case 104087344:
                if (strNormalizeAddonCatalogType.equals("movie")) {
                    return MediaType.MOVIE;
                }
                return null;
            case 109413654:
                if (!strNormalizeAddonCatalogType.equals("shows")) {
                    return null;
                }
                break;
            default:
                return null;
        }
        return MediaType.TV;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final String bundledStreamingLogoUri(String canonicalName) {
        Integer numValueOf;
        String lowerCase = canonicalName.toLowerCase(Locale.US);
        switch (lowerCase.hashCode()) {
            case -2095672285:
                numValueOf = !lowerCase.equals("apple tv+") ? null : Integer.valueOf(R.drawable.apple_tv_plus_logo);
                break;
            case -694617888:
                numValueOf = !lowerCase.equals("peacock") ? null : Integer.valueOf(R.raw.logo_peacock);
                break;
            case -259192087:
                numValueOf = !lowerCase.equals("crunchyroll") ? null : Integer.valueOf(R.raw.logo_crunchyroll);
                break;
            case 3214166:
                numValueOf = !lowerCase.equals("hulu") ? null : Integer.valueOf(R.raw.logo_hulu);
                break;
            case 3349624:
                numValueOf = !lowerCase.equals("mgm+") ? null : Integer.valueOf(R.raw.logo_mgm_plus);
                break;
            case 109757544:
                numValueOf = !lowerCase.equals("starz") ? null : Integer.valueOf(R.raw.logo_starz);
                break;
            case 332709786:
                numValueOf = !lowerCase.equals("prime video") ? null : Integer.valueOf(R.raw.logo_prime_video);
                break;
            case 468009746:
                numValueOf = !lowerCase.equals("paramount+") ? null : Integer.valueOf(R.raw.logo_paramount_plus);
                break;
            case 537538683:
                numValueOf = !lowerCase.equals("discovery+") ? null : Integer.valueOf(R.raw.logo_discovery_plus);
                break;
            case 720331129:
                numValueOf = !lowerCase.equals("hbo max") ? null : Integer.valueOf(R.raw.logo_hbo_max);
                break;
            case 1671698519:
                numValueOf = !lowerCase.equals("disney+") ? null : Integer.valueOf(R.raw.logo_disney_plus);
                break;
            case 1842975634:
                numValueOf = !lowerCase.equals("netflix") ? null : Integer.valueOf(R.raw.logo_netflix);
                break;
            case 2072270317:
                numValueOf = !lowerCase.equals("shudder") ? null : Integer.valueOf(R.raw.logo_shudder);
                break;
            default:
                numValueOf = null;
                break;
        }
        if (numValueOf == null) {
            return null;
        }
        return "android.resource://" + this.context.getPackageName() + DomExceptionUtils.SEPARATOR + numValueOf.intValue();
    }

    private final void cacheAddonImdbLookup(String imdbId, x6.x value) {
        this.addonImdbToTmdbCache.put(imdbId, new CacheEntry<>(value, System.currentTimeMillis()));
    }

    private final void cacheAddonTitleLookup(String key, x6.x value) {
        this.addonTitleToTmdbCache.put(key, new CacheEntry<>(value, System.currentTimeMillis()));
    }

    private final void cacheFullDetailsItem(MediaItem item) {
        String strDetailsCacheKey = detailsCacheKey(item.getMediaType(), item.getId());
        this.detailsCache.put(strDetailsCacheKey, new CacheEntry<>(item, System.currentTimeMillis()));
        this.fullDetailsCacheKeys.add(strDetailsCacheKey);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cacheItems(List<MediaItem> items) {
        Iterator<T> it = items.iterator();
        while (it.hasNext()) {
            cacheItem((MediaItem) it.next());
        }
    }

    private final String canonicalStreamingServiceName(String raw) {
        String string = raw != null ? kotlin.text.o.L0(raw).toString() : null;
        if (string == null) {
            string = "";
        }
        if (kotlin.text.o.h0(string)) {
            return "";
        }
        String lowerCase = string.toLowerCase(Locale.US);
        return (lowerCase.equals("max") || kotlin.text.o.T(lowerCase, "hbo", false)) ? "HBO Max" : kotlin.text.o.T(lowerCase, "netflix", false) ? "Netflix" : (kotlin.text.o.T(lowerCase, "prime", false) || kotlin.text.o.T(lowerCase, "amazon", false)) ? "Prime Video" : kotlin.text.o.T(lowerCase, "disney", false) ? "Disney+" : kotlin.text.o.T(lowerCase, "apple tv", false) ? "Apple TV+" : kotlin.text.o.T(lowerCase, "paramount", false) ? "Paramount+" : kotlin.text.o.T(lowerCase, "hulu", false) ? "Hulu" : kotlin.text.o.T(lowerCase, "peacock", false) ? "Peacock" : kotlin.text.o.T(lowerCase, "crunchyroll", false) ? "Crunchyroll" : kotlin.text.o.T(lowerCase, "discovery", false) ? "Discovery+" : kotlin.text.o.T(lowerCase, "mgm", false) ? "MGM+" : kotlin.text.o.T(lowerCase, "shudder", false) ? "Shudder" : kotlin.text.o.T(lowerCase, "starz", false) ? "Starz" : kotlin.text.o.T(lowerCase, "youtube", false) ? "YouTube" : string;
    }

    private final String collectionRefsCacheKey(CatalogConfig catalog) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(catalog.getId());
        sb2.append('|');
        for (CollectionSourceConfig collectionSourceConfig : catalog.getCollectionSources()) {
            sb2.append(collectionSourceConfig.getKind().name());
            sb2.append(':');
            String mediaType = collectionSourceConfig.getMediaType();
            String str = "";
            if (mediaType == null) {
                mediaType = "";
            }
            sb2.append(mediaType);
            sb2.append(':');
            String addonId = collectionSourceConfig.getAddonId();
            if (addonId == null) {
                addonId = "";
            }
            sb2.append(addonId);
            sb2.append(':');
            String addonCatalogType = collectionSourceConfig.getAddonCatalogType();
            if (addonCatalogType == null) {
                addonCatalogType = "";
            }
            sb2.append(addonCatalogType);
            sb2.append(':');
            String addonCatalogId = collectionSourceConfig.getAddonCatalogId();
            if (addonCatalogId == null) {
                addonCatalogId = "";
            }
            sb2.append(addonCatalogId);
            sb2.append(':');
            Integer tmdbGenreId = collectionSourceConfig.getTmdbGenreId();
            sb2.append(tmdbGenreId != null ? tmdbGenreId.intValue() : -1);
            sb2.append(':');
            Integer tmdbPersonId = collectionSourceConfig.getTmdbPersonId();
            sb2.append(tmdbPersonId != null ? tmdbPersonId.intValue() : -1);
            sb2.append(':');
            Integer tmdbCollectionId = collectionSourceConfig.getTmdbCollectionId();
            sb2.append(tmdbCollectionId != null ? tmdbCollectionId.intValue() : -1);
            sb2.append(':');
            Integer tmdbKeywordId = collectionSourceConfig.getTmdbKeywordId();
            sb2.append(tmdbKeywordId != null ? tmdbKeywordId.intValue() : -1);
            sb2.append(':');
            Integer tmdbWatchProviderId = collectionSourceConfig.getTmdbWatchProviderId();
            sb2.append(tmdbWatchProviderId != null ? tmdbWatchProviderId.intValue() : -1);
            sb2.append(':');
            String watchRegion = collectionSourceConfig.getWatchRegion();
            if (watchRegion == null) {
                watchRegion = "";
            }
            sb2.append(watchRegion);
            sb2.append(':');
            String sortBy = collectionSourceConfig.getSortBy();
            if (sortBy == null) {
                sortBy = "";
            }
            sb2.append(sortBy);
            sb2.append(':');
            String mdblistSlug = collectionSourceConfig.getMdblistSlug();
            if (mdblistSlug == null) {
                mdblistSlug = "";
            }
            sb2.append(mdblistSlug);
            sb2.append(':');
            List<String> curatedRefs = collectionSourceConfig.getCuratedRefs();
            String strU0 = curatedRefs != null ? kotlin.collections.x.u0(curatedRefs, ",", null, null, null, 62) : null;
            if (strU0 != null) {
                str = strU0;
            }
            sb2.append(str);
            sb2.append(';');
        }
        return sb2.toString();
    }

    private final String decodeCatalogRefPart(String value) {
        Object c0Var;
        try {
            c0Var = URLDecoder.decode(value, "UTF-8");
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        Object obj = value;
        if (!(c0Var instanceof x6.c0)) {
            obj = c0Var;
        }
        return (String) obj;
    }

    private final String detailsCacheKey(MediaType mediaType, int mediaId) {
        return a0.c.i(mediaId, mediaType == MediaType.MOVIE ? "movie_" : "tv_");
    }

    public static /* synthetic */ Object discoverMovies$default(MediaRepository mediaRepository, String str, String str2, Integer num, int i10, String str3, Integer num2, String str4, String str5, String str6, d7.d dVar, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = null;
        }
        if ((i11 & 2) != 0) {
            str2 = "popularity.desc";
        }
        if ((i11 & 4) != 0) {
            num = null;
        }
        if ((i11 & 8) != 0) {
            i10 = 1;
        }
        if ((i11 & 16) != 0) {
            str3 = null;
        }
        if ((i11 & 32) != 0) {
            num2 = null;
        }
        if ((i11 & 64) != 0) {
            str4 = null;
        }
        if ((i11 & 128) != 0) {
            str5 = null;
        }
        if ((i11 & 256) != 0) {
            str6 = null;
        }
        return mediaRepository.discoverMovies(str, str2, num, i10, str3, num2, str4, str5, str6, dVar);
    }

    public static /* synthetic */ Object discoverTv$default(MediaRepository mediaRepository, String str, String str2, Integer num, int i10, String str3, Integer num2, String str4, String str5, String str6, d7.d dVar, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = null;
        }
        if ((i11 & 2) != 0) {
            str2 = "popularity.desc";
        }
        if ((i11 & 4) != 0) {
            num = null;
        }
        if ((i11 & 8) != 0) {
            i10 = 1;
        }
        if ((i11 & 16) != 0) {
            str3 = null;
        }
        if ((i11 & 32) != 0) {
            num2 = null;
        }
        if ((i11 & 64) != 0) {
            str4 = null;
        }
        if ((i11 & 128) != 0) {
            str5 = null;
        }
        if ((i11 & 256) != 0) {
            str6 = null;
        }
        return mediaRepository.discoverTv(str, str2, num, i10, str3, num2, str4, str5, str6, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String extractImdbId(StremioMetaPreview meta) {
        String imdbId = meta.getImdbId();
        String string = imdbId != null ? kotlin.text.o.L0(imdbId).toString() : null;
        if (string == null || kotlin.text.o.h0(string)) {
            string = null;
        }
        if (string != null && !kotlin.text.o.h0(string) && kotlin.text.u.P(string, TtmlNode.TAG_TT, false)) {
            return string;
        }
        String id = meta.getId();
        String string2 = id != null ? kotlin.text.o.L0(id).toString() : null;
        if (string2 == null) {
            string2 = "";
        }
        if (kotlin.text.u.P(string2, TtmlNode.TAG_TT, true)) {
            return string2;
        }
        if (kotlin.text.u.P(string2, "imdb:", true)) {
            String string3 = kotlin.text.o.L0(kotlin.text.o.C0(':', string2, string2)).toString();
            if (kotlin.text.u.P(string3, TtmlNode.TAG_TT, true)) {
                return string3;
            }
        }
        kotlin.text.k kVarB = kotlin.text.m.b(MediaRegexes.INSTANCE.getIMDB_ID_REGEX(), string2);
        if (kVarB != null) {
            return kVarB.f19946a.group();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object fetchCinemetaEpisodeRatings(String str, d7.d<? super Map<x6.x, String>> dVar) throws Throwable {
        return ka.m0.y(x0.f19655d, new AnonymousClass2(str, this, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object fetchCinemetaImdbRating(MediaType mediaType, String str, d7.d<? super String> dVar) {
        return ka.m0.y(x0.f19655d, new C08852(mediaType, str, this, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String fetchUrl(String url) {
        Object c0Var;
        s0 s0Var;
        gb.j0 j0Var = new gb.j0();
        j0Var.i(url);
        j0Var.f15724c.h("User-Agent", OkHttpProvider.INSTANCE.userAgentOr("Mozilla/5.0 (Android TV; ARVIO)"));
        try {
            gb.p0 p0VarD = this.okHttpClient.a(j0Var.b()).d();
            try {
                c0Var = (p0VarD.k() && (s0Var = p0VarD.f15786q) != null) ? s0Var.x() : null;
                p0VarD.close();
            } finally {
            }
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        return (String) (c0Var instanceof x6.c0 ? null : c0Var);
    }

    private final TmdbWatchProviderRegion findRegionProviders(Map<String, TmdbWatchProviderRegion> allRegions, String region) {
        Object next;
        Iterator<T> it = allRegions.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (kotlin.text.u.L((String) ((Map.Entry) next).getKey(), region, true)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) next;
        if (entry != null) {
            return (TmdbWatchProviderRegion) entry.getValue();
        }
        return null;
    }

    private final x6.x getAddonImdbLookup(String imdbId) {
        CacheEntry<x6.x> addonImdbLookupEntry = getAddonImdbLookupEntry(imdbId);
        if (addonImdbLookupEntry != null) {
            return addonImdbLookupEntry.getData();
        }
        return null;
    }

    private final CacheEntry<x6.x> getAddonImdbLookupEntry(String imdbId) {
        CacheEntry<x6.x> cacheEntry = this.addonImdbToTmdbCache.get(imdbId);
        if (cacheEntry == null) {
            return null;
        }
        if (System.currentTimeMillis() - cacheEntry.getTimestamp() < this.CACHE_TTL_MS) {
            return cacheEntry;
        }
        this.addonImdbToTmdbCache.remove(imdbId);
        return null;
    }

    private final CacheEntry<x6.x> getAddonTitleLookupEntry(String key) {
        CacheEntry<x6.x> cacheEntry = this.addonTitleToTmdbCache.get(key);
        if (cacheEntry == null) {
            return null;
        }
        if (System.currentTimeMillis() - cacheEntry.getTimestamp() < this.CACHE_TTL_MS) {
            return cacheEntry;
        }
        this.addonTitleToTmdbCache.remove(key);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getAgregarrImdbRatings(List<String> list, d7.d<? super Map<String, String>> dVar) throws Throwable {
        return ka.m0.y(x0.f19655d, new C08862(list, this, null), dVar);
    }

    private final List<x6.x> getCollectionRefsCache(String key) {
        CacheEntry<List<x6.x>> cacheEntry = this.collectionRefsCache.get(key);
        if (cacheEntry == null) {
            return null;
        }
        if (System.currentTimeMillis() - cacheEntry.getTimestamp() < this.CACHE_TTL_MS) {
            return cacheEntry.getData();
        }
        this.collectionRefsCache.remove(key);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final <T> T getFromCache(Map<String, CacheEntry<T>> cache, String key) {
        CacheEntry<T> cacheEntry = cache.get(key);
        if (cacheEntry != null && System.currentTimeMillis() - cacheEntry.getTimestamp() < this.CACHE_TTL_MS) {
            return cacheEntry.getData();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object getHomeCategoriesInternal(d7.d<? super List<Category>> dVar) {
        return ka.l0.c(new C08892(null), dVar);
    }

    public static /* synthetic */ Object getImdbRating$default(MediaRepository mediaRepository, MediaType mediaType, int i10, String str, d7.d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            str = null;
        }
        return mediaRepository.getImdbRating(mediaType, i10, str, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getLogoUrl$lambda$1(TmdbImage tmdbImage) {
        String filePath = tmdbImage.getFilePath();
        return !(filePath == null || kotlin.text.o.h0(filePath));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getLogoUrl$lambda$2(TmdbImage tmdbImage) {
        return kotlin.text.u.K(tmdbImage.getFilePath(), ".svg", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:50:0x014d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getSeasonEpisodeImdbRatings(int r11, int r12, java.util.List<java.lang.Integer> r13, java.lang.String r14, d7.d<? super java.util.Map<x6.x, java.lang.String>> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 441
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getSeasonEpisodeImdbRatings(int, int, java.util.List, java.lang.String, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object getSeasonEpisodeImdbRatings$default(MediaRepository mediaRepository, int i10, int i11, List list, String str, d7.d dVar, int i12, Object obj) throws Throwable {
        if ((i12 & 8) != 0) {
            str = null;
        }
        return mediaRepository.getSeasonEpisodeImdbRatings(i10, i11, list, str, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getSeriesCinemetaEpisodeRatings(int r7, java.lang.String r8, d7.d<? super java.util.Map<x6.x, java.lang.String>> r9) throws java.lang.Throwable {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.arflix.tv.data.repository.MediaRepository.C08981
            if (r0 == 0) goto L13
            r0 = r9
            com.arflix.tv.data.repository.MediaRepository$getSeriesCinemetaEpisodeRatings$1 r0 = (com.arflix.tv.data.repository.MediaRepository.C08981) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.MediaRepository$getSeriesCinemetaEpisodeRatings$1 r0 = new com.arflix.tv.data.repository.MediaRepository$getSeriesCinemetaEpisodeRatings$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            r4 = 0
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L49
            if (r1 == r3) goto L3f
            if (r1 != r2) goto L37
            java.lang.Object r7 = r0.L$2
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r0.L$1
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r8 = r0.L$0
            java.lang.String r8 = (java.lang.String) r8
            k2.c.G(r9)
            goto L85
        L37:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3f:
            int r7 = r0.I$0
            java.lang.Object r8 = r0.L$0
            java.lang.String r8 = (java.lang.String) r8
            k2.c.G(r9)
            goto L5b
        L49:
            k2.c.G(r9)
            com.arflix.tv.data.model.MediaType r9 = com.arflix.tv.data.model.MediaType.TV
            r0.L$0 = r4
            r0.I$0 = r7
            r0.label = r3
            java.lang.Object r9 = r6.resolveImdbId(r9, r7, r8, r0)
            if (r9 != r5) goto L5b
            goto L83
        L5b:
            java.lang.String r9 = (java.lang.String) r9
            if (r9 != 0) goto L62
            kotlin.collections.a0 r7 = kotlin.collections.a0.f19683i
            return r7
        L62:
            java.lang.String r8 = "series_"
            java.lang.String r8 = r8.concat(r9)
            j$.util.concurrent.ConcurrentHashMap<java.lang.String, com.arflix.tv.data.repository.MediaRepository$CacheEntry<java.util.Map<x6.x, java.lang.String>>> r1 = r6.imdbEpisodeRatingsCache
            java.lang.Object r1 = r6.getFromCache(r1, r8)
            java.util.Map r1 = (java.util.Map) r1
            if (r1 == 0) goto L73
            return r1
        L73:
            r0.L$0 = r4
            r0.L$1 = r4
            r0.L$2 = r8
            r0.I$0 = r7
            r0.label = r2
            java.lang.Object r9 = r6.fetchCinemetaEpisodeRatings(r9, r0)
            if (r9 != r5) goto L84
        L83:
            return r5
        L84:
            r7 = r8
        L85:
            java.util.Map r9 = (java.util.Map) r9
            j$.util.concurrent.ConcurrentHashMap<java.lang.String, com.arflix.tv.data.repository.MediaRepository$CacheEntry<java.util.Map<x6.x, java.lang.String>>> r8 = r6.imdbEpisodeRatingsCache
            com.arflix.tv.data.repository.MediaRepository$CacheEntry r0 = new com.arflix.tv.data.repository.MediaRepository$CacheEntry
            long r1 = java.lang.System.currentTimeMillis()
            r0.<init>(r9, r1)
            r8.put(r7, r0)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getSeriesCinemetaEpisodeRatings(int, java.lang.String, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object getSeriesCinemetaEpisodeRatings$default(MediaRepository mediaRepository, int i10, String str, d7.d dVar, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            str = null;
        }
        return mediaRepository.getSeriesCinemetaEpisodeRatings(i10, str, dVar);
    }

    public static /* synthetic */ Object getStreamingServices$default(MediaRepository mediaRepository, MediaType mediaType, int i10, String str, d7.d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            str = null;
        }
        return mediaRepository.getStreamingServices(mediaType, i10, str, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isTop10Catalog(CatalogConfig catalogConfig) {
        String sourceUrl;
        String sourceRef;
        return kotlin.text.o.T(catalogConfig.getId(), "top10", true) || kotlin.text.o.T(catalogConfig.getTitle(), "Top 10", true) || ((sourceUrl = catalogConfig.getSourceUrl()) != null && kotlin.text.o.T(sourceUrl, "top-10", true)) || ((sourceRef = catalogConfig.getSourceRef()) != null && kotlin.text.o.T(sourceRef, "top-10", true));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadAddonCatalogRefsPage(CatalogConfig catalogConfig, int i10, int i11, d7.d<? super AddonCatalogRefsPage> dVar) {
        return ka.l0.c(new C09042(catalogConfig, i10, i11, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadCollectionAddonRefs(CollectionSourceConfig collectionSourceConfig, int i10, int i11, d7.d<? super List<? extends x6.x>> dVar) {
        return ka.l0.c(new C09052(collectionSourceConfig, this, i10, i11, null), dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0068, code lost:
    
        if (r2.equals("show") == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0072, code lost:
    
        if (r2.equals("tv") == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x007b, code lost:
    
        if (r2.equals("series") == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x007e, code lost:
    
        r2 = com.arflix.tv.data.model.MediaType.TV;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final java.util.List<x6.x> loadCollectionCuratedRefs(com.arflix.tv.data.model.CollectionSourceConfig r7, int r8) {
        /*
            r6 = this;
            java.util.List r7 = r7.getCuratedRefs()
            if (r7 != 0) goto L9
            kotlin.collections.z r7 = kotlin.collections.z.f19728i
            return r7
        L9:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r7 = r7.iterator()
        L12:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L99
            java.lang.Object r1 = r7.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = ":"
            java.lang.String[] r2 = new java.lang.String[]{r2}
            r3 = 2
            java.util.List r1 = kotlin.text.o.y0(r1, r2, r3, r3)
            int r2 = r1.size()
            r4 = 0
            if (r2 == r3) goto L31
            goto L92
        L31:
            r2 = 0
            java.lang.Object r2 = r1.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            java.util.Locale r3 = java.util.Locale.US
            java.lang.String r2 = r2.toLowerCase(r3)
            int r3 = r2.hashCode()
            r5 = -905838985(0xffffffffca01fe77, float:-2129821.8)
            if (r3 == r5) goto L75
            r5 = 3714(0xe82, float:5.204E-42)
            if (r3 == r5) goto L6b
            r5 = 3529469(0x35dafd, float:4.94584E-39)
            if (r3 == r5) goto L62
            r5 = 104087344(0x6343f30, float:3.390066E-35)
            if (r3 == r5) goto L56
            goto L92
        L56:
            java.lang.String r3 = "movie"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L5f
            goto L92
        L5f:
            com.arflix.tv.data.model.MediaType r2 = com.arflix.tv.data.model.MediaType.MOVIE
            goto L80
        L62:
            java.lang.String r3 = "show"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L7e
            goto L92
        L6b:
            java.lang.String r3 = "tv"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L7e
            goto L92
        L75:
            java.lang.String r3 = "series"
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L7e
            goto L92
        L7e:
            com.arflix.tv.data.model.MediaType r2 = com.arflix.tv.data.model.MediaType.TV
        L80:
            r3 = 1
            java.lang.Object r1 = r1.get(r3)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Integer r1 = kotlin.text.u.R(r1)
            if (r1 == 0) goto L92
            x6.x r4 = new x6.x
            r4.<init>(r2, r1)
        L92:
            if (r4 == 0) goto L12
            r0.add(r4)
            goto L12
        L99:
            java.util.List r7 = kotlin.collections.x.X0(r0, r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.loadCollectionCuratedRefs(com.arflix.tv.data.model.CollectionSourceConfig, int):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0058, code lost:
    
        if (r7.equals("show") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0062, code lost:
    
        if (r7.equals("tv") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006b, code lost:
    
        if (r7.equals("series") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0079, code lost:
    
        return loadPagedTmdbDiscoverRefs(com.arflix.tv.data.model.MediaType.TV, r8, new com.arflix.tv.data.repository.MediaRepository.C09083(r6, r0, r2, null), r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadCollectionGenreRefs(com.arflix.tv.data.model.CollectionSourceConfig r7, int r8, d7.d<? super java.util.List<? extends x6.x>> r9) {
        /*
            r6 = this;
            java.lang.Integer r0 = r7.getTmdbGenreId()
            kotlin.collections.z r1 = kotlin.collections.z.f19728i
            if (r0 == 0) goto L7a
            int r0 = r0.intValue()
            java.lang.String r2 = r7.getSortBy()
            if (r2 != 0) goto L14
            java.lang.String r2 = "popularity.desc"
        L14:
            java.lang.String r7 = r7.getMediaType()
            r3 = 0
            if (r7 == 0) goto L22
            java.util.Locale r4 = java.util.Locale.US
            java.lang.String r7 = r7.toLowerCase(r4)
            goto L23
        L22:
            r7 = r3
        L23:
            if (r7 == 0) goto L7a
            int r4 = r7.hashCode()
            r5 = -905838985(0xffffffffca01fe77, float:-2129821.8)
            if (r4 == r5) goto L65
            r5 = 3714(0xe82, float:5.204E-42)
            if (r4 == r5) goto L5b
            r5 = 3529469(0x35dafd, float:4.94584E-39)
            if (r4 == r5) goto L52
            r5 = 104087344(0x6343f30, float:3.390066E-35)
            if (r4 == r5) goto L3d
            goto L7a
        L3d:
            java.lang.String r4 = "movie"
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L46
            goto L7a
        L46:
            com.arflix.tv.data.model.MediaType r7 = com.arflix.tv.data.model.MediaType.MOVIE
            com.arflix.tv.data.repository.MediaRepository$loadCollectionGenreRefs$2 r1 = new com.arflix.tv.data.repository.MediaRepository$loadCollectionGenreRefs$2
            r1.<init>(r0, r2, r3)
            java.lang.Object r7 = r6.loadPagedTmdbDiscoverRefs(r7, r8, r1, r9)
            return r7
        L52:
            java.lang.String r4 = "show"
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L6e
            goto L7a
        L5b:
            java.lang.String r4 = "tv"
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L6e
            goto L7a
        L65:
            java.lang.String r4 = "series"
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L6e
            goto L7a
        L6e:
            com.arflix.tv.data.model.MediaType r7 = com.arflix.tv.data.model.MediaType.TV
            com.arflix.tv.data.repository.MediaRepository$loadCollectionGenreRefs$3 r1 = new com.arflix.tv.data.repository.MediaRepository$loadCollectionGenreRefs$3
            r1.<init>(r0, r2, r3)
            java.lang.Object r7 = r6.loadPagedTmdbDiscoverRefs(r7, r8, r1, r9)
            return r7
        L7a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.loadCollectionGenreRefs(com.arflix.tv.data.model.CollectionSourceConfig, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadCollectionKeywordRefs(CollectionSourceConfig collectionSourceConfig, int i10, d7.d<? super List<? extends x6.x>> dVar) {
        return ka.l0.c(new C09092(collectionSourceConfig, this, i10, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadCollectionMdblistPublicRefs(com.arflix.tv.data.model.CollectionSourceConfig r8, int r9, d7.d<? super java.util.List<? extends x6.x>> r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.loadCollectionMdblistPublicRefs(com.arflix.tv.data.model.CollectionSourceConfig, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0058, code lost:
    
        if (r7.equals("show") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0062, code lost:
    
        if (r7.equals("tv") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006b, code lost:
    
        if (r7.equals("series") == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0079, code lost:
    
        return loadPagedTmdbDiscoverRefs(com.arflix.tv.data.model.MediaType.TV, r8, new com.arflix.tv.data.repository.MediaRepository.C09123(r6, r0, r2, null), r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadCollectionPersonRefs(com.arflix.tv.data.model.CollectionSourceConfig r7, int r8, d7.d<? super java.util.List<? extends x6.x>> r9) {
        /*
            r6 = this;
            java.lang.Integer r0 = r7.getTmdbPersonId()
            kotlin.collections.z r1 = kotlin.collections.z.f19728i
            if (r0 == 0) goto L7a
            int r0 = r0.intValue()
            java.lang.String r2 = r7.getSortBy()
            if (r2 != 0) goto L14
            java.lang.String r2 = "popularity.desc"
        L14:
            java.lang.String r7 = r7.getMediaType()
            r3 = 0
            if (r7 == 0) goto L22
            java.util.Locale r4 = java.util.Locale.US
            java.lang.String r7 = r7.toLowerCase(r4)
            goto L23
        L22:
            r7 = r3
        L23:
            if (r7 == 0) goto L7a
            int r4 = r7.hashCode()
            r5 = -905838985(0xffffffffca01fe77, float:-2129821.8)
            if (r4 == r5) goto L65
            r5 = 3714(0xe82, float:5.204E-42)
            if (r4 == r5) goto L5b
            r5 = 3529469(0x35dafd, float:4.94584E-39)
            if (r4 == r5) goto L52
            r5 = 104087344(0x6343f30, float:3.390066E-35)
            if (r4 == r5) goto L3d
            goto L7a
        L3d:
            java.lang.String r4 = "movie"
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L46
            goto L7a
        L46:
            com.arflix.tv.data.model.MediaType r7 = com.arflix.tv.data.model.MediaType.MOVIE
            com.arflix.tv.data.repository.MediaRepository$loadCollectionPersonRefs$2 r1 = new com.arflix.tv.data.repository.MediaRepository$loadCollectionPersonRefs$2
            r1.<init>(r0, r2, r3)
            java.lang.Object r7 = r6.loadPagedTmdbDiscoverRefs(r7, r8, r1, r9)
            return r7
        L52:
            java.lang.String r4 = "show"
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L6e
            goto L7a
        L5b:
            java.lang.String r4 = "tv"
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L6e
            goto L7a
        L65:
            java.lang.String r4 = "series"
            boolean r7 = r7.equals(r4)
            if (r7 != 0) goto L6e
            goto L7a
        L6e:
            com.arflix.tv.data.model.MediaType r7 = com.arflix.tv.data.model.MediaType.TV
            com.arflix.tv.data.repository.MediaRepository$loadCollectionPersonRefs$3 r1 = new com.arflix.tv.data.repository.MediaRepository$loadCollectionPersonRefs$3
            r1.<init>(r0, r2, r3)
            java.lang.Object r7 = r6.loadPagedTmdbDiscoverRefs(r7, r8, r1, r9)
            return r7
        L7a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.loadCollectionPersonRefs(com.arflix.tv.data.model.CollectionSourceConfig, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadCollectionTmdbCollectionRefs(com.arflix.tv.data.model.CollectionSourceConfig r8, int r9, d7.d<? super java.util.List<? extends x6.x>> r10) {
        /*
            r7 = this;
            kotlin.collections.z r0 = kotlin.collections.z.f19728i
            boolean r1 = r10 instanceof com.arflix.tv.data.repository.MediaRepository.C09131
            if (r1 == 0) goto L15
            r1 = r10
            com.arflix.tv.data.repository.MediaRepository$loadCollectionTmdbCollectionRefs$1 r1 = (com.arflix.tv.data.repository.MediaRepository.C09131) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            com.arflix.tv.data.repository.MediaRepository$loadCollectionTmdbCollectionRefs$1 r1 = new com.arflix.tv.data.repository.MediaRepository$loadCollectionTmdbCollectionRefs$1
            r1.<init>(r10)
        L1a:
            java.lang.Object r10 = r1.result
            e7.a r2 = e7.a.f15033i
            int r3 = r1.label
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L3a
            if (r3 != r4) goto L32
            int r9 = r1.I$0
            java.lang.Object r8 = r1.L$0
            com.arflix.tv.data.model.CollectionSourceConfig r8 = (com.arflix.tv.data.model.CollectionSourceConfig) r8
            k2.c.G(r10)     // Catch: java.util.concurrent.CancellationException -> L30 java.lang.Throwable -> L5f
            goto L5c
        L30:
            r8 = move-exception
            goto La3
        L32:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3a:
            k2.c.G(r10)
            java.lang.Integer r8 = r8.getTmdbCollectionId()
            if (r8 == 0) goto La4
            int r8 = r8.intValue()
            com.arflix.tv.data.api.TmdbApi r10 = r7.tmdbApi     // Catch: java.util.concurrent.CancellationException -> L30 java.lang.Throwable -> L5f java.lang.Throwable -> L5f
            java.lang.String r3 = r7.apiKey     // Catch: java.util.concurrent.CancellationException -> L30 java.lang.Throwable -> L5f java.lang.Throwable -> L5f
            java.lang.String r6 = r7.contentLanguage     // Catch: java.util.concurrent.CancellationException -> L30 java.lang.Throwable -> L5f java.lang.Throwable -> L5f
            r1.L$0 = r5     // Catch: java.util.concurrent.CancellationException -> L30 java.lang.Throwable -> L5f java.lang.Throwable -> L5f
            r1.I$0 = r9     // Catch: java.util.concurrent.CancellationException -> L30 java.lang.Throwable -> L5f java.lang.Throwable -> L5f
            r1.I$1 = r8     // Catch: java.util.concurrent.CancellationException -> L30 java.lang.Throwable -> L5f java.lang.Throwable -> L5f
            r1.label = r4     // Catch: java.util.concurrent.CancellationException -> L30 java.lang.Throwable -> L5f java.lang.Throwable -> L5f
            java.lang.Object r10 = r10.getTmdbCollection(r8, r3, r6, r1)     // Catch: java.util.concurrent.CancellationException -> L30 java.lang.Throwable -> L5f java.lang.Throwable -> L5f
            if (r10 != r2) goto L5c
            return r2
        L5c:
            com.arflix.tv.data.api.TmdbCollectionResponse r10 = (com.arflix.tv.data.api.TmdbCollectionResponse) r10     // Catch: java.util.concurrent.CancellationException -> L30 java.lang.Throwable -> L5f java.lang.Throwable -> L5f
            r5 = r10
        L5f:
            if (r5 != 0) goto L62
            return r0
        L62:
            java.util.List r8 = r5.getParts()
            com.arflix.tv.data.repository.MediaRepository$loadCollectionTmdbCollectionRefs$$inlined$sortedBy$1 r10 = new com.arflix.tv.data.repository.MediaRepository$loadCollectionTmdbCollectionRefs$$inlined$sortedBy$1
            r10.<init>()
            java.util.List r8 = kotlin.collections.x.W0(r8, r10)
            java.util.ArrayList r10 = new java.util.ArrayList
            r0 = 10
            int r0 = kotlin.collections.s.U(r8, r0)
            r10.<init>(r0)
            java.util.Iterator r8 = r8.iterator()
        L7e:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L9e
            java.lang.Object r0 = r8.next()
            com.arflix.tv.data.api.TmdbMediaItem r0 = (com.arflix.tv.data.api.TmdbMediaItem) r0
            com.arflix.tv.data.model.MediaType r1 = com.arflix.tv.data.model.MediaType.MOVIE
            int r0 = r0.getId()
            java.lang.Integer r2 = new java.lang.Integer
            r2.<init>(r0)
            x6.x r0 = new x6.x
            r0.<init>(r1, r2)
            r10.add(r0)
            goto L7e
        L9e:
            java.util.List r8 = kotlin.collections.x.X0(r10, r9)
            return r8
        La3:
            throw r8
        La4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.loadCollectionTmdbCollectionRefs(com.arflix.tv.data.model.CollectionSourceConfig, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0075, code lost:
    
        if (r2.equals("show") == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0080, code lost:
    
        if (r2.equals("tv") == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x008a, code lost:
    
        if (r2.equals("series") == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0099, code lost:
    
        return loadPagedTmdbDiscoverRefs(com.arflix.tv.data.model.MediaType.TV, r10, new com.arflix.tv.data.repository.MediaRepository.C09153(r3, r4, r5, r6, null), r11);
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadCollectionWatchProviderRefs(com.arflix.tv.data.model.CollectionSourceConfig r9, int r10, d7.d<? super java.util.List<? extends x6.x>> r11) {
        /*
            r8 = this;
            java.lang.Integer r0 = r9.getTmdbWatchProviderId()
            kotlin.collections.z r1 = kotlin.collections.z.f19728i
            if (r0 == 0) goto L9b
            int r4 = r0.intValue()
            java.lang.String r0 = r9.getWatchRegion()
            r2 = 0
            if (r0 == 0) goto L20
            boolean r3 = kotlin.text.o.h0(r0)
            if (r3 != 0) goto L1a
            goto L1b
        L1a:
            r0 = r2
        L1b:
            if (r0 != 0) goto L1e
            goto L20
        L1e:
            r5 = r0
            goto L23
        L20:
            java.lang.String r0 = "US"
            goto L1e
        L23:
            java.lang.String r0 = r9.getSortBy()
            if (r0 != 0) goto L2b
            java.lang.String r0 = "popularity.desc"
        L2b:
            r6 = r0
            java.lang.String r9 = r9.getMediaType()
            if (r9 == 0) goto L38
            java.util.Locale r0 = java.util.Locale.US
            java.lang.String r2 = r9.toLowerCase(r0)
        L38:
            if (r2 == 0) goto L51
            int r9 = r2.hashCode()
            r0 = -905838985(0xffffffffca01fe77, float:-2129821.8)
            if (r9 == r0) goto L83
            r0 = 3714(0xe82, float:5.204E-42)
            if (r9 == r0) goto L78
            r0 = 3529469(0x35dafd, float:4.94584E-39)
            if (r9 == r0) goto L6e
            r0 = 104087344(0x6343f30, float:3.390066E-35)
            if (r9 == r0) goto L53
        L51:
            r3 = r8
            goto L9a
        L53:
            java.lang.String r9 = "movie"
            boolean r9 = r2.equals(r9)
            if (r9 != 0) goto L5c
            goto L51
        L5c:
            com.arflix.tv.data.model.MediaType r9 = com.arflix.tv.data.model.MediaType.MOVIE
            com.arflix.tv.data.repository.MediaRepository$loadCollectionWatchProviderRefs$2 r2 = new com.arflix.tv.data.repository.MediaRepository$loadCollectionWatchProviderRefs$2
            r7 = 0
            r3 = r5
            r5 = r4
            r4 = r6
            r6 = r3
            r3 = r8
            r2.<init>(r4, r5, r6, r7)
            java.lang.Object r9 = r8.loadPagedTmdbDiscoverRefs(r9, r10, r2, r11)
            return r9
        L6e:
            r3 = r8
            java.lang.String r9 = "show"
            boolean r9 = r2.equals(r9)
            if (r9 != 0) goto L8d
            goto L9a
        L78:
            r3 = r8
            java.lang.String r9 = "tv"
            boolean r9 = r2.equals(r9)
            if (r9 != 0) goto L8d
            goto L9a
        L83:
            r3 = r8
            java.lang.String r9 = "series"
            boolean r9 = r2.equals(r9)
            if (r9 != 0) goto L8d
            goto L9a
        L8d:
            com.arflix.tv.data.model.MediaType r9 = com.arflix.tv.data.model.MediaType.TV
            com.arflix.tv.data.repository.MediaRepository$loadCollectionWatchProviderRefs$3 r2 = new com.arflix.tv.data.repository.MediaRepository$loadCollectionWatchProviderRefs$3
            r7 = 0
            r2.<init>(r4, r5, r6, r7)
            java.lang.Object r9 = r8.loadPagedTmdbDiscoverRefs(r9, r10, r2, r11)
            return r9
        L9a:
            return r1
        L9b:
            r3 = r8
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.loadCollectionWatchProviderRefs(com.arflix.tv.data.model.CollectionSourceConfig, int, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object loadCustomCatalog$default(MediaRepository mediaRepository, CatalogConfig catalogConfig, int i10, d7.d dVar, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = 40;
        }
        return mediaRepository.loadCustomCatalog(catalogConfig, i10, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object loadHomeServerCatalogPage(CatalogConfig catalogConfig, int i10, int i11, d7.d<? super CategoryPageResult> dVar) {
        return ka.l0.c(new C09202(catalogConfig, i10, i11, null), dVar);
    }

    public static /* synthetic */ Object loadHomeServerLibraryPage$default(MediaRepository mediaRepository, String str, int i10, int i11, HomeServerLibrarySort homeServerLibrarySort, MediaType mediaType, String str2, d7.d dVar, int i12, Object obj) {
        if ((i12 & 8) != 0) {
            homeServerLibrarySort = HomeServerLibrarySort.RECENTLY_ADDED;
        }
        HomeServerLibrarySort homeServerLibrarySort2 = homeServerLibrarySort;
        if ((i12 & 16) != 0) {
            mediaType = null;
        }
        MediaType mediaType2 = mediaType;
        if ((i12 & 32) != 0) {
            str2 = "";
        }
        return mediaRepository.loadHomeServerLibraryPage(str, i10, i11, homeServerLibrarySort2, mediaType2, str2, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0094, code lost:
    
        if (r11 == r7) goto L54;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a1 A[PHI: r9
      0x00a1: PHI (r9v1 java.lang.String) = 
      (r9v0 java.lang.String)
      (r9v0 java.lang.String)
      (r9v0 java.lang.String)
      (r9v0 java.lang.String)
      (r9v9 java.lang.String)
     binds: [B:19:0x0064, B:21:0x006a, B:24:0x0074, B:26:0x0086, B:31:0x009e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadMdblistCatalogRefs(java.lang.String r9, java.lang.String r10, d7.d<? super java.util.List<? extends x6.x>> r11) {
        /*
            Method dump skipped, instruction units count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.loadMdblistCatalogRefs(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object loadMdblistCatalogRefs$default(MediaRepository mediaRepository, String str, String str2, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        return mediaRepository.loadMdblistCatalogRefs(str, str2, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(7:(1:79)|31|32|75|33|(9:36|37|38|39|45|(1:47)|48|(4:51|(2:53|(1:55)(1:56))|57|(2:59|(6:62|63|(1:64)|81|67|(0))))|71)|61) */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0100, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0101, code lost:
    
        r14 = r2;
        r11 = r3;
        r10 = r5;
        r2 = r15;
        r13 = r16;
        r5 = r17;
        r3 = r18;
        r12 = r19;
     */
    /* JADX WARN: Removed duplicated region for block: B:47:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x017d A[LOOP:0: B:64:0x0177->B:66:0x017d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:62:0x0165 -> B:63:0x0171). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadPagedAddonCollectionRefs(com.arflix.tv.data.repository.MediaRepository.AddonCatalogDescriptor r22, int r23, int r24, d7.d<? super java.util.List<? extends x6.x>> r25) {
        /*
            Method dump skipped, instruction units count: 430
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.loadPagedAddonCollectionRefs(com.arflix.tv.data.repository.MediaRepository$AddonCatalogDescriptor, int, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0089 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x008a -> B:32:0x0090). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadPagedTmdbDiscoverRefs(com.arflix.tv.data.model.MediaType r10, int r11, r7.p<? super java.lang.Integer, ? super d7.d<? super com.arflix.tv.data.api.TmdbListResponse>, ? extends java.lang.Object> r12, d7.d<? super java.util.List<? extends x6.x>> r13) {
        /*
            Method dump skipped, instruction units count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.loadPagedTmdbDiscoverRefs(com.arflix.tv.data.model.MediaType, int, r7.p, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0068, code lost:
    
        if (r9 == r5) goto L31;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadTraktCatalogRefs(java.lang.String r7, java.lang.String r8, d7.d<? super java.util.List<? extends x6.x>> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.arflix.tv.data.repository.MediaRepository.C09251
            if (r0 == 0) goto L13
            r0 = r9
            com.arflix.tv.data.repository.MediaRepository$loadTraktCatalogRefs$1 r0 = (com.arflix.tv.data.repository.MediaRepository.C09251) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.MediaRepository$loadTraktCatalogRefs$1 r0 = new com.arflix.tv.data.repository.MediaRepository$loadTraktCatalogRefs$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            r4 = 0
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L53
            if (r1 == r3) goto L43
            if (r1 != r2) goto L3b
            java.lang.Object r7 = r0.L$3
            com.arflix.tv.util.ParsedCatalogUrl r7 = (com.arflix.tv.util.ParsedCatalogUrl) r7
            java.lang.Object r7 = r0.L$2
            com.arflix.tv.util.ParsedCatalogUrl r7 = (com.arflix.tv.util.ParsedCatalogUrl) r7
            java.lang.Object r7 = r0.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            k2.c.G(r9)
            return r9
        L3b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L43:
            java.lang.Object r7 = r0.L$2
            com.arflix.tv.util.ParsedCatalogUrl r7 = (com.arflix.tv.util.ParsedCatalogUrl) r7
            java.lang.Object r7 = r0.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            k2.c.G(r9)
            goto L6b
        L53:
            k2.c.G(r9)
            com.arflix.tv.util.ParsedCatalogUrl r8 = r6.parseTraktRef(r8)
            if (r8 == 0) goto L74
            r0.L$0 = r7
            r0.L$1 = r4
            r0.L$2 = r4
            r0.label = r3
            java.lang.Object r9 = loadTraktCatalogRefs$loadFromParsed(r6, r8, r0)
            if (r9 != r5) goto L6b
            goto L8f
        L6b:
            java.util.List r9 = (java.util.List) r9
            boolean r8 = r9.isEmpty()
            if (r8 != 0) goto L74
            return r9
        L74:
            if (r7 == 0) goto L91
            com.arflix.tv.util.CatalogUrlParser r8 = com.arflix.tv.util.CatalogUrlParser.INSTANCE
            com.arflix.tv.util.ParsedCatalogUrl r7 = r8.parseTrakt(r7)
            if (r7 != 0) goto L7f
            goto L91
        L7f:
            r0.L$0 = r4
            r0.L$1 = r4
            r0.L$2 = r4
            r0.L$3 = r4
            r0.label = r2
            java.lang.Object r7 = loadTraktCatalogRefs$loadFromParsed(r6, r7, r0)
            if (r7 != r5) goto L90
        L8f:
            return r5
        L90:
            return r7
        L91:
            kotlin.collections.z r7 = kotlin.collections.z.f19728i
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.loadTraktCatalogRefs(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object loadTraktCatalogRefs$default(MediaRepository mediaRepository, String str, String str2, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = null;
        }
        return mediaRepository.loadTraktCatalogRefs(str, str2, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0204, code lost:
    
        if (r0 == r3) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01b1, code lost:
    
        if (r0 == r3) goto L120;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:115:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x023d A[PHI: r3
      0x023d: PHI (r3v21 e7.a) = (r3v4 e7.a), (r3v6 e7.a), (r3v10 e7.a), (r3v18 e7.a), (r3v22 e7.a) binds: [B:84:0x01b1, B:48:0x0103, B:65:0x015c, B:102:0x0204, B:119:0x023b] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:121:0x023e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /* JADX WARN: Type inference failed for: r0v50, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v20, types: [com.arflix.tv.data.repository.MediaRepository] */
    /* JADX WARN: Type inference failed for: r2v43 */
    /* JADX WARN: Type inference failed for: r2v44 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object loadTraktCatalogRefs$loadFromParsed(com.arflix.tv.data.repository.MediaRepository r21, com.arflix.tv.util.ParsedCatalogUrl r22, d7.d<? super java.util.List<? extends x6.x>> r23) {
        /*
            Method dump skipped, instruction units count: 575
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.loadTraktCatalogRefs$loadFromParsed(com.arflix.tv.data.repository.MediaRepository, com.arflix.tv.util.ParsedCatalogUrl, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object mapTraktItemsToTmdbRefs(List<TraktPublicListItem> list, d7.d<? super List<? extends x6.x>> dVar) {
        return ka.l0.c(new C09262(list, this, null), dVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final String normalizeAddonCatalogType(String rawType) {
        String string;
        String lowerCase = (rawType == null || (string = kotlin.text.o.L0(rawType).toString()) == null) ? null : string.toLowerCase(Locale.ROOT);
        if (lowerCase != null) {
            switch (lowerCase.hashCode()) {
                case -905838985:
                    if (lowerCase.equals("series")) {
                        return "series";
                    }
                    break;
                case 3714:
                    if (lowerCase.equals("tv")) {
                        return "tv";
                    }
                    break;
                case 3529469:
                    if (lowerCase.equals("show")) {
                        return "show";
                    }
                    break;
                case 104087344:
                    if (lowerCase.equals("movie")) {
                        return "movie";
                    }
                    break;
                case 109413654:
                    if (lowerCase.equals("shows")) {
                        return "shows";
                    }
                    break;
            }
        }
        return null;
    }

    private final String normalizeWatchRegion(String region) {
        String string;
        String upperCase = (region == null || (string = kotlin.text.o.L0(region).toString()) == null) ? null : string.toUpperCase(Locale.US);
        if (upperCase == null) {
            upperCase = "";
        }
        String str = upperCase.length() == 2 ? upperCase : null;
        return str == null ? "US" : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object parseAddonPageRefs(List<StremioMetaPreview> list, AddonCatalogDescriptor addonCatalogDescriptor, d7.d<? super List<? extends x6.x>> dVar) {
        return ka.l0.c(new C09272(addonCatalogDescriptor, list, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String parseCinemetaMetaRating(JSONObject meta) {
        Object next;
        Iterator it = t7.a.E(meta.optString("imdbRating"), meta.optString("rating")).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            String str = (String) next;
            if (!kotlin.text.o.h0(str) && !str.equalsIgnoreCase("N/A")) {
                break;
            }
        }
        String str2 = (String) next;
        if (str2 != null) {
            return str2;
        }
        JSONArray jSONArrayOptJSONArray = meta.optJSONArray("links");
        if (jSONArrayOptJSONArray == null) {
            return null;
        }
        int length = jSONArrayOptJSONArray.length();
        for (int i10 = 0; i10 < length; i10++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i10);
            if (jSONObjectOptJSONObject != null && kotlin.text.u.L(jSONObjectOptJSONObject.optString("category"), "imdb", true)) {
                String strOptString = jSONObjectOptJSONObject.optString(ContentDisposition.Parameters.Name);
                if (kotlin.text.o.h0(strOptString) || strOptString.equalsIgnoreCase("N/A")) {
                    return null;
                }
                return strOptString;
            }
        }
        return null;
    }

    private final List<x6.x> parseMdblistJson(String payload) {
        Object c0Var;
        x6.x xVar;
        int i10 = 2;
        int i11 = 1;
        try {
            c0Var = (List) this.gson.fromJson(payload, TypeToken.getParameterized(List.class, TypeToken.getParameterized(Map.class, String.class, Object.class).getType()).getType());
        } catch (Throwable th) {
            c0Var = new x6.c0(th);
        }
        if (c0Var instanceof x6.c0) {
            c0Var = null;
        }
        List<Map> list = (List) c0Var;
        if (list == null) {
            return kotlin.collections.z.f19728i;
        }
        ArrayList arrayList = new ArrayList();
        for (Map map : list) {
            Integer num = (Integer) ga.r.K(ga.r.Q(kotlin.collections.r.G(new String[]{"tmdb_id", "tmdb", "tmdbId", TtmlNode.ATTR_ID}), new c(i11, map)));
            if (num != null) {
                String str = (String) ga.r.K(ga.r.Q(kotlin.collections.r.G(new String[]{"mediatype", "media_type", LinkHeader.Parameters.Type}), new c(i10, map)));
                if (str == null) {
                    str = "movie";
                }
                xVar = new x6.x((kotlin.text.o.T(str, "tv", false) || kotlin.text.o.T(str, "show", false) || kotlin.text.o.T(str, "series", false)) ? MediaType.TV : MediaType.MOVIE, num);
            } else {
                xVar = null;
            }
            if (xVar != null) {
                arrayList.add(xVar);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer parseMdblistJson$lambda$1$0(Map map, String str) {
        return MediaRepositoryKt.toIntSafe(map.get(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String parseMdblistJson$lambda$1$1(Map map, String str) {
        String string;
        Object obj = map.get(str);
        if (obj == null || (string = obj.toString()) == null) {
            return null;
        }
        return string.toLowerCase(Locale.ROOT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x01a3, code lost:
    
        if (r13 == null) goto L116;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:109:0x019d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final x6.x parseTmdbRefFromAddonMeta(com.arflix.tv.data.api.StremioMetaPreview r12, com.arflix.tv.data.model.MediaType r13) {
        /*
            Method dump skipped, instruction units count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.parseTmdbRefFromAddonMeta(com.arflix.tv.data.api.StremioMetaPreview, com.arflix.tv.data.model.MediaType):x6.x");
    }

    private final ParsedCatalogUrl parseTraktRef(String sourceRef) {
        if (sourceRef == null || kotlin.text.o.h0(sourceRef)) {
            return null;
        }
        if (kotlin.text.u.P(sourceRef, "trakt_user:", false)) {
            List listY0 = kotlin.text.o.y0(kotlin.text.o.r0(sourceRef, "trakt_user:"), new String[]{":"}, 0, 6);
            if (listY0.size() >= 2) {
                return new ParsedCatalogUrl.TraktUserList((String) listY0.get(0), (String) listY0.get(1));
            }
            return null;
        }
        if (kotlin.text.u.P(sourceRef, "trakt_list:", false)) {
            String string = kotlin.text.o.L0(kotlin.text.o.r0(sourceRef, "trakt_list:")).toString();
            if (kotlin.text.o.h0(string)) {
                return null;
            }
            return new ParsedCatalogUrl.TraktList(string);
        }
        if (!kotlin.text.u.P(sourceRef, "mdblist_trakt:", false)) {
            return null;
        }
        String string2 = kotlin.text.o.L0(kotlin.text.o.r0(sourceRef, "mdblist_trakt:")).toString();
        if (kotlin.text.o.h0(string2)) {
            return null;
        }
        return CatalogUrlParser.INSTANCE.parseTrakt(string2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AddonCatalogDescriptor resolveAddonCatalogDescriptor(CatalogConfig catalog) {
        String addonId = catalog.getAddonId();
        String string = addonId != null ? kotlin.text.o.L0(addonId).toString() : null;
        if (string == null || kotlin.text.o.h0(string)) {
            string = null;
        }
        String strNormalizeAddonCatalogType = normalizeAddonCatalogType(catalog.getAddonCatalogType());
        String addonCatalogId = catalog.getAddonCatalogId();
        String string2 = addonCatalogId != null ? kotlin.text.o.L0(addonCatalogId).toString() : null;
        if (string2 == null || kotlin.text.o.h0(string2)) {
            string2 = null;
        }
        if (string != null && strNormalizeAddonCatalogType != null && string2 != null) {
            return new AddonCatalogDescriptor(string, strNormalizeAddonCatalogType, string2);
        }
        String sourceRef = catalog.getSourceRef();
        String string3 = sourceRef != null ? kotlin.text.o.L0(sourceRef).toString() : null;
        if (string3 == null) {
            string3 = "";
        }
        if (!kotlin.text.u.P(string3, "addon_catalog|", false)) {
            return null;
        }
        List listY0 = kotlin.text.o.y0(kotlin.text.o.r0(string3, "addon_catalog|"), new String[]{"|"}, 0, 6);
        if (listY0.size() != 3) {
            return null;
        }
        String string4 = kotlin.text.o.L0(decodeCatalogRefPart((String) listY0.get(0))).toString();
        String strNormalizeAddonCatalogType2 = normalizeAddonCatalogType(decodeCatalogRefPart((String) listY0.get(1)));
        String string5 = kotlin.text.o.L0(decodeCatalogRefPart((String) listY0.get(2))).toString();
        if (kotlin.text.o.h0(string4) || strNormalizeAddonCatalogType2 == null || kotlin.text.o.h0(string5)) {
            return null;
        }
        return new AddonCatalogDescriptor(string4, strNormalizeAddonCatalogType2, string5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveAddonMetaToTmdbRef(com.arflix.tv.data.repository.MediaRepository.AddonCatalogDescriptor r10, com.arflix.tv.data.repository.MediaRepository.UnresolvedAddonMeta r11, d7.d<? super x6.x> r12) {
        /*
            Method dump skipped, instruction units count: 235
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.resolveAddonMetaToTmdbRef(com.arflix.tv.data.repository.MediaRepository$AddonCatalogDescriptor, com.arflix.tv.data.repository.MediaRepository$UnresolvedAddonMeta, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009b A[PHI: r8
      0x009b: PHI (r8v9 int) = (r8v2 int), (r8v4 int), (r8v6 int), (r8v10 int) binds: [B:63:0x00ce, B:56:0x00c3, B:49:0x00b8, B:38:0x0099] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveCollectionCatalogRefs(com.arflix.tv.data.model.CatalogConfig r12, int r13, d7.d<? super java.util.List<? extends x6.x>> r14) {
        /*
            Method dump skipped, instruction units count: 484
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.resolveCollectionCatalogRefs(com.arflix.tv.data.model.CatalogConfig, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x010a, code lost:
    
        if (r8 == r2) goto L77;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveCollectionSourceRefs(com.arflix.tv.data.model.CollectionSourceConfig r5, int r6, int r7, d7.d<? super java.util.List<? extends x6.x>> r8) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.resolveCollectionSourceRefs(com.arflix.tv.data.model.CollectionSourceConfig, int, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resolveEpisodeImdbIds(int i10, int i11, List<Integer> list, d7.d<? super Map<Integer, String>> dVar) {
        return ka.l0.c(new C09312(list, i10, i11, this, null), dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveExternalIds(com.arflix.tv.data.model.MediaType r6, int r7, d7.d<? super com.arflix.tv.data.api.TmdbExternalIds> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.MediaRepository.C09321
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.MediaRepository$resolveExternalIds$1 r0 = (com.arflix.tv.data.repository.MediaRepository.C09321) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.MediaRepository$resolveExternalIds$1 r0 = new com.arflix.tv.data.repository.MediaRepository$resolveExternalIds$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L47
            if (r1 == r3) goto L3b
            if (r1 != r2) goto L33
            java.lang.Object r6 = r0.L$1
            com.arflix.tv.data.repository.MediaRepository r6 = (com.arflix.tv.data.repository.MediaRepository) r6
            java.lang.Object r6 = r0.L$0
            com.arflix.tv.data.model.MediaType r6 = (com.arflix.tv.data.model.MediaType) r6
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L31
            goto L6f
        L31:
            r6 = move-exception
            goto L91
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            java.lang.Object r6 = r0.L$1
            com.arflix.tv.data.repository.MediaRepository r6 = (com.arflix.tv.data.repository.MediaRepository) r6
            java.lang.Object r6 = r0.L$0
            com.arflix.tv.data.model.MediaType r6 = (com.arflix.tv.data.model.MediaType) r6
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L31
            goto L8e
        L47:
            k2.c.G(r8)
            int[] r8 = com.arflix.tv.data.repository.MediaRepository.WhenMappings.$EnumSwitchMapping$1     // Catch: java.lang.Throwable -> L31
            int r6 = r6.ordinal()     // Catch: java.lang.Throwable -> L31
            r6 = r8[r6]     // Catch: java.lang.Throwable -> L31
            e7.a r8 = e7.a.f15033i
            r1 = 0
            if (r6 == r3) goto L78
            if (r6 != r2) goto L72
            com.arflix.tv.data.api.TmdbApi r6 = r5.tmdbApi     // Catch: java.lang.Throwable -> L31
            java.lang.String r3 = r5.apiKey     // Catch: java.lang.Throwable -> L31
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L31
            r0.L$1 = r4     // Catch: java.lang.Throwable -> L31
            r0.I$0 = r7     // Catch: java.lang.Throwable -> L31
            r0.I$1 = r1     // Catch: java.lang.Throwable -> L31
            r0.label = r2     // Catch: java.lang.Throwable -> L31
            java.lang.Object r6 = r6.getTvExternalIds(r7, r3, r0)     // Catch: java.lang.Throwable -> L31
            if (r6 != r8) goto L6e
            goto L8c
        L6e:
            r8 = r6
        L6f:
            com.arflix.tv.data.api.TmdbExternalIds r8 = (com.arflix.tv.data.api.TmdbExternalIds) r8     // Catch: java.lang.Throwable -> L31
            goto L96
        L72:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException     // Catch: java.lang.Throwable -> L31
            r6.<init>()     // Catch: java.lang.Throwable -> L31
            throw r6     // Catch: java.lang.Throwable -> L31
        L78:
            com.arflix.tv.data.api.TmdbApi r6 = r5.tmdbApi     // Catch: java.lang.Throwable -> L31
            java.lang.String r2 = r5.apiKey     // Catch: java.lang.Throwable -> L31
            r0.L$0 = r4     // Catch: java.lang.Throwable -> L31
            r0.L$1 = r4     // Catch: java.lang.Throwable -> L31
            r0.I$0 = r7     // Catch: java.lang.Throwable -> L31
            r0.I$1 = r1     // Catch: java.lang.Throwable -> L31
            r0.label = r3     // Catch: java.lang.Throwable -> L31
            java.lang.Object r6 = r6.getMovieExternalIds(r7, r2, r0)     // Catch: java.lang.Throwable -> L31
            if (r6 != r8) goto L8d
        L8c:
            return r8
        L8d:
            r8 = r6
        L8e:
            com.arflix.tv.data.api.TmdbExternalIds r8 = (com.arflix.tv.data.api.TmdbExternalIds) r8     // Catch: java.lang.Throwable -> L31
            goto L96
        L91:
            x6.c0 r8 = new x6.c0
            r8.<init>(r6)
        L96:
            boolean r6 = r8 instanceof x6.c0
            if (r6 == 0) goto L9b
            goto L9c
        L9b:
            r4 = r8
        L9c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.resolveExternalIds(com.arflix.tv.data.model.MediaType, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:101:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01eb A[PHI: r12
      0x01eb: PHI (r12v21 com.arflix.tv.data.repository.HomeServerCatalogItem) = 
      (r12v0 com.arflix.tv.data.repository.HomeServerCatalogItem)
      (r12v0 com.arflix.tv.data.repository.HomeServerCatalogItem)
      (r12v23 com.arflix.tv.data.repository.HomeServerCatalogItem)
     binds: [B:61:0x0158, B:66:0x0164, B:72:0x0181] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveHomeServerCatalogItem(com.arflix.tv.data.repository.HomeServerCatalogItem r12, d7.d<? super com.arflix.tv.data.model.MediaItem> r13) {
        /*
            Method dump skipped, instruction units count: 526
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.resolveHomeServerCatalogItem(com.arflix.tv.data.repository.HomeServerCatalogItem, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0316, code lost:
    
        if (r0 == r3) goto L168;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x013f, code lost:
    
        if (r0 == r3) goto L168;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:70:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /* JADX WARN: Type inference failed for: r0v13, types: [com.arflix.tv.data.repository.HomeServerMatcher] */
    /* JADX WARN: Type inference failed for: r14v15 */
    /* JADX WARN: Type inference failed for: r14v16 */
    /* JADX WARN: Type inference failed for: r14v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r18v0, types: [com.arflix.tv.data.repository.MediaRepository] */
    /* JADX WARN: Type inference failed for: r2v0, types: [com.arflix.tv.data.repository.HomeServerCatalogItem, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [com.arflix.tv.data.repository.HomeServerCatalogItem] */
    /* JADX WARN: Type inference failed for: r2v44 */
    /* JADX WARN: Type inference failed for: r2v45 */
    /* JADX WARN: Type inference failed for: r2v46 */
    /* JADX WARN: Type inference failed for: r2v47 */
    /* JADX WARN: Type inference failed for: r2v48 */
    /* JADX WARN: Type inference failed for: r2v49 */
    /* JADX WARN: Type inference failed for: r2v50 */
    /* JADX WARN: Type inference failed for: r2v51 */
    /* JADX WARN: Type inference failed for: r2v52 */
    /* JADX WARN: Type inference failed for: r2v53 */
    /* JADX WARN: Type inference failed for: r2v54 */
    /* JADX WARN: Type inference failed for: r2v55 */
    /* JADX WARN: Type inference failed for: r2v56 */
    /* JADX WARN: Type inference failed for: r2v57 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r4v0, types: [int] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v25, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v31 */
    /* JADX WARN: Type inference failed for: r6v32 */
    /* JADX WARN: Type inference failed for: r6v33 */
    /* JADX WARN: Type inference failed for: r6v34 */
    /* JADX WARN: Type inference failed for: r6v35 */
    /* JADX WARN: Type inference failed for: r6v36 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v24, types: [int] */
    /* JADX WARN: Type inference failed for: r7v28 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [int] */
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
    public final java.lang.Object resolveHomeServerCatalogItemByTitle(com.arflix.tv.data.repository.HomeServerCatalogItem r19, d7.d<? super com.arflix.tv.data.model.MediaItem> r20) {
        /*
            Method dump skipped, instruction units count: 814
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.resolveHomeServerCatalogItemByTitle(com.arflix.tv.data.repository.HomeServerCatalogItem, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00d5, code lost:
    
        if (r1 == r5) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00f0, code lost:
    
        if (r15 == r5) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00f2, code lost:
    
        return r5;
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveHomeServerLogoRef(com.arflix.tv.data.model.MediaItem r14, d7.d<? super x6.x> r15) {
        /*
            Method dump skipped, instruction units count: 261
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.resolveHomeServerLogoRef(com.arflix.tv.data.model.MediaItem, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:164:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /* JADX WARN: Type inference failed for: r0v11, types: [com.arflix.tv.data.repository.HomeServerMatcher] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v3, types: [int] */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v13 */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r14v15 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1 */
    /* JADX WARN: Type inference failed for: r17v10 */
    /* JADX WARN: Type inference failed for: r17v11 */
    /* JADX WARN: Type inference failed for: r17v12 */
    /* JADX WARN: Type inference failed for: r17v13 */
    /* JADX WARN: Type inference failed for: r17v14 */
    /* JADX WARN: Type inference failed for: r17v15 */
    /* JADX WARN: Type inference failed for: r17v16 */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r17v4 */
    /* JADX WARN: Type inference failed for: r17v5 */
    /* JADX WARN: Type inference failed for: r17v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r17v7 */
    /* JADX WARN: Type inference failed for: r17v8 */
    /* JADX WARN: Type inference failed for: r17v9 */
    /* JADX WARN: Type inference failed for: r2v0, types: [com.arflix.tv.data.model.MediaItem, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17, types: [com.arflix.tv.data.model.MediaItem] */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v38 */
    /* JADX WARN: Type inference failed for: r3v39 */
    /* JADX WARN: Type inference failed for: r4v0, types: [int] */
    /* JADX WARN: Type inference failed for: r6v11, types: [x6.x] */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v36 */
    /* JADX WARN: Type inference failed for: r6v37 */
    /* JADX WARN: Type inference failed for: r6v38 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [int] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10, types: [java.lang.Integer, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7, types: [java.lang.Integer, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
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
    public final java.lang.Object resolveHomeServerLogoRefByTitle(com.arflix.tv.data.model.MediaItem r19, d7.d<? super x6.x> r20) {
        /*
            Method dump skipped, instruction units count: 603
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.resolveHomeServerLogoRefByTitle(com.arflix.tv.data.model.MediaItem, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveImdbId(com.arflix.tv.data.model.MediaType r5, int r6, java.lang.String r7, d7.d<? super java.lang.String> r8) {
        /*
            r4 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.MediaRepository.C09371
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.MediaRepository$resolveImdbId$1 r0 = (com.arflix.tv.data.repository.MediaRepository.C09371) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.MediaRepository$resolveImdbId$1 r0 = new com.arflix.tv.data.repository.MediaRepository$resolveImdbId$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L3c
            if (r1 != r2) goto L34
            int r6 = r0.I$0
            java.lang.Object r5 = r0.L$2
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$1
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$0
            com.arflix.tv.data.model.MediaType r5 = (com.arflix.tv.data.model.MediaType) r5
            k2.c.G(r8)
            goto L7b
        L34:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3c:
            k2.c.G(r8)
            if (r7 == 0) goto L54
            java.lang.CharSequence r7 = kotlin.text.o.L0(r7)
            java.lang.String r7 = r7.toString()
            if (r7 == 0) goto L54
            java.lang.String r8 = "tt"
            boolean r8 = kotlin.text.u.P(r7, r8, r2)
            if (r8 == 0) goto L54
            goto L55
        L54:
            r7 = r3
        L55:
            if (r7 == 0) goto L62
            boolean r8 = kotlin.text.o.h0(r7)
            if (r8 == 0) goto L5e
            goto L62
        L5e:
            r4.cacheImdbId(r5, r6, r7)
            return r7
        L62:
            java.lang.String r7 = r4.getCachedImdbId(r5, r6)
            if (r7 != 0) goto L8a
            r0.L$0 = r5
            r0.L$1 = r3
            r0.L$2 = r3
            r0.I$0 = r6
            r0.label = r2
            java.lang.Object r8 = r4.resolveExternalIds(r5, r6, r0)
            e7.a r7 = e7.a.f15033i
            if (r8 != r7) goto L7b
            return r7
        L7b:
            com.arflix.tv.data.api.TmdbExternalIds r8 = (com.arflix.tv.data.api.TmdbExternalIds) r8
            if (r8 == 0) goto L89
            java.lang.String r7 = r8.getImdbId()
            if (r7 == 0) goto L89
            r4.cacheImdbId(r5, r6, r7)
            return r7
        L89:
            return r3
        L8a:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.resolveImdbId(com.arflix.tv.data.model.MediaType, int, java.lang.String, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object resolveImdbId$default(MediaRepository mediaRepository, MediaType mediaType, int i10, String str, d7.d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            str = null;
        }
        return mediaRepository.resolveImdbId(mediaType, i10, str, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveImdbToTmdbRef(java.lang.String r7, com.arflix.tv.data.model.MediaType r8, d7.d<? super x6.x> r9) {
        /*
            Method dump skipped, instruction units count: 533
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.resolveImdbToTmdbRef(java.lang.String, com.arflix.tv.data.model.MediaType, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveTitleToTmdbRef(java.lang.String r9, com.arflix.tv.data.model.MediaType r10, d7.d<? super x6.x> r11) {
        /*
            Method dump skipped, instruction units count: 418
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.resolveTitleToTmdbRef(java.lang.String, com.arflix.tv.data.model.MediaType, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object searchPeopleKnownFor$default(MediaRepository mediaRepository, String str, int i10, d7.d dVar, int i11, Object obj) {
        if ((i11 & 2) != 0) {
            i10 = 3;
        }
        return mediaRepository.searchPeopleKnownFor(str, i10, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean searchPeopleKnownFor$lambda$0(TmdbMediaItem tmdbMediaItem) {
        String name;
        return kotlin.jvm.internal.p.a(tmdbMediaItem.getMediaType(), "person") && tmdbMediaItem.getId() > 0 && (name = tmdbMediaItem.getName()) != null && !kotlin.text.o.h0(name);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean searchPeopleKnownFor$lambda$3$0(TmdbMediaItem tmdbMediaItem) {
        if (tmdbMediaItem.getPosterPath() != null) {
            return kotlin.jvm.internal.p.a(tmdbMediaItem.getMediaType(), "movie") || kotlin.jvm.internal.p.a(tmdbMediaItem.getMediaType(), "tv");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MediaItem searchPeopleKnownFor$lambda$3$3(TmdbMediaItem tmdbMediaItem) {
        return MediaRepositoryKt.toMediaItem(tmdbMediaItem, kotlin.jvm.internal.p.a(tmdbMediaItem.getMediaType(), "tv") ? MediaType.TV : MediaType.MOVIE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String searchPeopleKnownFor$lambda$3$4(MediaItem mediaItem) {
        return mediaItem.getMediaType() + "_" + mediaItem.getId();
    }

    private final List<StreamingServiceInfo> toStreamingServiceList(TmdbWatchProviderRegion regionData) {
        List listE = t7.a.E(regionData.getFlatrate(), regionData.getFree(), regionData.getAds(), regionData.getRent(), regionData.getBuy());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = listE.iterator();
        while (it.hasNext()) {
            for (TmdbWatchProvider tmdbWatchProvider : kotlin.collections.x.W0((List) it.next(), new Comparator() { // from class: com.arflix.tv.data.repository.MediaRepository$toStreamingServiceList$lambda$0$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    return r0.e(Integer.valueOf(((TmdbWatchProvider) t2).getDisplayPriority()), Integer.valueOf(((TmdbWatchProvider) t10).getDisplayPriority()));
                }
            })) {
                String strCanonicalStreamingServiceName = canonicalStreamingServiceName(tmdbWatchProvider.getProviderName());
                if (!kotlin.text.o.h0(strCanonicalStreamingServiceName)) {
                    String lowerCase = strCanonicalStreamingServiceName.toLowerCase(Locale.US);
                    if (!linkedHashMap.containsKey(lowerCase)) {
                        Integer numValueOf = Integer.valueOf(tmdbWatchProvider.getProviderId());
                        String strConcat = null;
                        if (numValueOf.intValue() <= 0) {
                            numValueOf = null;
                        }
                        int iIntValue = numValueOf != null ? numValueOf.intValue() : strCanonicalStreamingServiceName.hashCode();
                        String strBundledStreamingLogoUri = bundledStreamingLogoUri(strCanonicalStreamingServiceName);
                        if (strBundledStreamingLogoUri == null) {
                            String logoPath = tmdbWatchProvider.getLogoPath();
                            if (logoPath != null) {
                                strConcat = "https://image.tmdb.org/t/p/w92".concat(logoPath);
                            }
                        } else {
                            strConcat = strBundledStreamingLogoUri;
                        }
                        linkedHashMap.put(lowerCase, new StreamingServiceInfo(iIntValue, strCanonicalStreamingServiceName, strConcat));
                    }
                }
            }
        }
        return kotlin.collections.x.X0(linkedHashMap.values(), 10);
    }

    public final void cacheImdbId(MediaType mediaType, int mediaId, String imdbId) {
        if (kotlin.text.o.h0(imdbId)) {
            return;
        }
        this.imdbIdCache.put(detailsCacheKey(mediaType, mediaId), imdbId);
    }

    public final void cacheItem(MediaItem item) {
        String strDetailsCacheKey = detailsCacheKey(item.getMediaType(), item.getId());
        if (this.fullDetailsCacheKeys.contains(strDetailsCacheKey)) {
            if (((MediaItem) getFromCache(this.detailsCache, strDetailsCacheKey)) != null) {
                return;
            } else {
                this.fullDetailsCacheKeys.remove(strDetailsCacheKey);
            }
        }
        this.detailsCache.put(strDetailsCacheKey, new CacheEntry<>(item, System.currentTimeMillis()));
    }

    public final void clearMediaCache() {
        this.cachedHomeCategories = kotlin.collections.z.f19728i;
        this.homeCategoriesFetchedAt = 0L;
        synchronized (this.detailsCache) {
            this.detailsCache.clear();
        }
        synchronized (this.fullDetailsCacheKeys) {
            this.fullDetailsCacheKeys.clear();
        }
        synchronized (this.castCache) {
            this.castCache.clear();
        }
        synchronized (this.similarCache) {
            this.similarCache.clear();
        }
        synchronized (this.logoCache) {
            this.logoCache.clear();
        }
        this.homeServerLogoRefCache.clear();
        synchronized (this.reviewsCache) {
            this.reviewsCache.clear();
        }
        synchronized (this.seasonEpisodesCache) {
            this.seasonEpisodesCache.clear();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object discoverMovies(java.lang.String r22, java.lang.String r23, java.lang.Integer r24, int r25, java.lang.String r26, java.lang.Integer r27, java.lang.String r28, java.lang.String r29, java.lang.String r30, d7.d<? super java.util.List<com.arflix.tv.data.model.MediaItem>> r31) {
        /*
            r21 = this;
            r0 = r21
            r1 = r31
            boolean r2 = r1 instanceof com.arflix.tv.data.repository.MediaRepository.AnonymousClass1
            if (r2 == 0) goto L17
            r2 = r1
            com.arflix.tv.data.repository.MediaRepository$discoverMovies$1 r2 = (com.arflix.tv.data.repository.MediaRepository.AnonymousClass1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.label = r3
            goto L1c
        L17:
            com.arflix.tv.data.repository.MediaRepository$discoverMovies$1 r2 = new com.arflix.tv.data.repository.MediaRepository$discoverMovies$1
            r2.<init>(r1)
        L1c:
            java.lang.Object r1 = r2.result
            e7.a r3 = e7.a.f15033i
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L53
            if (r4 != r5) goto L4b
            java.lang.Object r3 = r2.L$7
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r3 = r2.L$6
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r3 = r2.L$5
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r3 = r2.L$4
            java.lang.Integer r3 = (java.lang.Integer) r3
            java.lang.Object r3 = r2.L$3
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r3 = r2.L$2
            java.lang.Integer r3 = (java.lang.Integer) r3
            java.lang.Object r3 = r2.L$1
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r2 = r2.L$0
            java.lang.String r2 = (java.lang.String) r2
            k2.c.G(r1)
            goto L99
        L4b:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L53:
            k2.c.G(r1)
            r1 = r3
            com.arflix.tv.data.api.TmdbApi r3 = r0.tmdbApi
            java.lang.String r4 = r0.apiKey
            java.lang.String r6 = r0.contentLanguage
            r7 = 0
            r2.L$0 = r7
            r2.L$1 = r7
            r2.L$2 = r7
            r2.L$3 = r7
            r2.L$4 = r7
            r2.L$5 = r7
            r2.L$6 = r7
            r2.L$7 = r7
            r7 = r25
            r2.I$0 = r7
            r2.label = r5
            r16 = r6
            r6 = 0
            r14 = 0
            r15 = 0
            r19 = 3076(0xc04, float:4.31E-42)
            r20 = 0
            r5 = r22
            r8 = r24
            r10 = r26
            r11 = r27
            r9 = r28
            r13 = r29
            r12 = r30
            r18 = r2
            r17 = r7
            r7 = r23
            java.lang.Object r2 = com.arflix.tv.data.api.g.a(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            if (r2 != r1) goto L98
            return r1
        L98:
            r1 = r2
        L99:
            com.arflix.tv.data.api.TmdbListResponse r1 = (com.arflix.tv.data.api.TmdbListResponse) r1
            java.util.List r1 = r1.getResults()
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 10
            int r3 = kotlin.collections.s.U(r1, r3)
            r2.<init>(r3)
            java.util.Iterator r1 = r1.iterator()
        Lae:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto Lc4
            java.lang.Object r3 = r1.next()
            com.arflix.tv.data.api.TmdbMediaItem r3 = (com.arflix.tv.data.api.TmdbMediaItem) r3
            com.arflix.tv.data.model.MediaType r4 = com.arflix.tv.data.model.MediaType.MOVIE
            com.arflix.tv.data.model.MediaItem r3 = com.arflix.tv.data.repository.MediaRepositoryKt.access$toMediaItem(r3, r4)
            r2.add(r3)
            goto Lae
        Lc4:
            r0.cacheItems(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.discoverMovies(java.lang.String, java.lang.String, java.lang.Integer, int, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object discoverTv(java.lang.String r22, java.lang.String r23, java.lang.Integer r24, int r25, java.lang.String r26, java.lang.Integer r27, java.lang.String r28, java.lang.String r29, java.lang.String r30, d7.d<? super java.util.List<com.arflix.tv.data.model.MediaItem>> r31) {
        /*
            r21 = this;
            r0 = r21
            r1 = r31
            boolean r2 = r1 instanceof com.arflix.tv.data.repository.MediaRepository.C08841
            if (r2 == 0) goto L17
            r2 = r1
            com.arflix.tv.data.repository.MediaRepository$discoverTv$1 r2 = (com.arflix.tv.data.repository.MediaRepository.C08841) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.label = r3
            goto L1c
        L17:
            com.arflix.tv.data.repository.MediaRepository$discoverTv$1 r2 = new com.arflix.tv.data.repository.MediaRepository$discoverTv$1
            r2.<init>(r1)
        L1c:
            java.lang.Object r1 = r2.result
            e7.a r3 = e7.a.f15033i
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L53
            if (r4 != r5) goto L4b
            java.lang.Object r3 = r2.L$7
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r3 = r2.L$6
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r3 = r2.L$5
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r3 = r2.L$4
            java.lang.Integer r3 = (java.lang.Integer) r3
            java.lang.Object r3 = r2.L$3
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r3 = r2.L$2
            java.lang.Integer r3 = (java.lang.Integer) r3
            java.lang.Object r3 = r2.L$1
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r2 = r2.L$0
            java.lang.String r2 = (java.lang.String) r2
            k2.c.G(r1)
            goto L99
        L4b:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L53:
            k2.c.G(r1)
            r1 = r3
            com.arflix.tv.data.api.TmdbApi r3 = r0.tmdbApi
            java.lang.String r4 = r0.apiKey
            java.lang.String r6 = r0.contentLanguage
            r7 = 0
            r2.L$0 = r7
            r2.L$1 = r7
            r2.L$2 = r7
            r2.L$3 = r7
            r2.L$4 = r7
            r2.L$5 = r7
            r2.L$6 = r7
            r2.L$7 = r7
            r7 = r25
            r2.I$0 = r7
            r2.label = r5
            r5 = 0
            r16 = r6
            r6 = 0
            r9 = 0
            r19 = 38
            r20 = 0
            r8 = r22
            r12 = r24
            r10 = r26
            r11 = r27
            r13 = r28
            r15 = r29
            r14 = r30
            r18 = r2
            r17 = r7
            r7 = r23
            java.lang.Object r2 = com.arflix.tv.data.api.g.b(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            if (r2 != r1) goto L98
            return r1
        L98:
            r1 = r2
        L99:
            com.arflix.tv.data.api.TmdbListResponse r1 = (com.arflix.tv.data.api.TmdbListResponse) r1
            java.util.List r1 = r1.getResults()
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 10
            int r3 = kotlin.collections.s.U(r1, r3)
            r2.<init>(r3)
            java.util.Iterator r1 = r1.iterator()
        Lae:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto Lc4
            java.lang.Object r3 = r1.next()
            com.arflix.tv.data.api.TmdbMediaItem r3 = (com.arflix.tv.data.api.TmdbMediaItem) r3
            com.arflix.tv.data.model.MediaType r4 = com.arflix.tv.data.model.MediaType.TV
            com.arflix.tv.data.model.MediaItem r3 = com.arflix.tv.data.repository.MediaRepositoryKt.access$toMediaItem(r3, r4)
            r2.add(r3)
            goto Lae
        Lc4:
            r0.cacheItems(r2)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.discoverTv(java.lang.String, java.lang.String, java.lang.Integer, int, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    public final MediaItem getCachedFullItem(MediaType mediaType, int mediaId) {
        String strDetailsCacheKey = detailsCacheKey(mediaType, mediaId);
        if (!this.fullDetailsCacheKeys.contains(strDetailsCacheKey)) {
            return null;
        }
        MediaItem mediaItem = (MediaItem) getFromCache(this.detailsCache, strDetailsCacheKey);
        if (mediaItem == null) {
            this.fullDetailsCacheKeys.remove(strDetailsCacheKey);
        }
        return mediaItem;
    }

    public final List<Category> getCachedHomeCategories() {
        return this.cachedHomeCategories;
    }

    public final String getCachedImdbId(MediaType mediaType, int mediaId) {
        return this.imdbIdCache.get(detailsCacheKey(mediaType, mediaId));
    }

    public final MediaItem getCachedItem(MediaType mediaType, int mediaId) {
        return (MediaItem) getFromCache(this.detailsCache, detailsCacheKey(mediaType, mediaId));
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getCast(com.arflix.tv.data.model.MediaType r10, int r11, d7.d<? super java.util.List<com.arflix.tv.data.model.CastMember>> r12) {
        /*
            Method dump skipped, instruction units count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getCast(com.arflix.tv.data.model.MediaType, int, d7.d):java.lang.Object");
    }

    public final String getContentLanguage() {
        return this.contentLanguage;
    }

    public final List<CatalogConfig> getDefaultCatalogConfigs() {
        return INSTANCE.buildPreinstalledDefaults$app_playRelease();
    }

    public final Object getHomeCategories(d7.d<? super List<Category>> dVar) {
        return ka.l0.c(new C08882(null), dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00e0, code lost:
    
        if (r12 == r6) goto L35;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getImdbRating(com.arflix.tv.data.model.MediaType r9, int r10, java.lang.String r11, d7.d<? super java.lang.String> r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getImdbRating(com.arflix.tv.data.model.MediaType, int, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getLogoUrl(com.arflix.tv.data.model.MediaType r11, int r12, d7.d<? super java.lang.String> r13) {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getLogoUrl(com.arflix.tv.data.model.MediaType, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getMovieCollectionRef(int r6, d7.d<? super com.arflix.tv.data.api.TmdbCollectionRef> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.MediaRepository.C08921
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.MediaRepository$getMovieCollectionRef$1 r0 = (com.arflix.tv.data.repository.MediaRepository.C08921) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.MediaRepository$getMovieCollectionRef$1 r0 = new com.arflix.tv.data.repository.MediaRepository$getMovieCollectionRef$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            k2.c.G(r7)     // Catch: java.lang.Throwable -> L4a
            goto L43
        L27:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2f:
            k2.c.G(r7)
            com.arflix.tv.data.api.TmdbApi r7 = r5.tmdbApi     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4a
            java.lang.String r2 = r5.apiKey     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4a
            java.lang.String r4 = r5.contentLanguage     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4a
            r0.I$0 = r6     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4a
            r0.label = r3     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4a
            java.lang.Object r7 = r7.getMovieDetails(r6, r2, r4, r0)     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4a
            if (r7 != r1) goto L43
            return r1
        L43:
            com.arflix.tv.data.api.TmdbMovieDetails r7 = (com.arflix.tv.data.api.TmdbMovieDetails) r7     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4a
            com.arflix.tv.data.api.TmdbCollectionRef r6 = r7.getBelongsToCollection()     // Catch: java.lang.Throwable -> L4a java.lang.Throwable -> L4a
            return r6
        L4a:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getMovieCollectionRef(int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x010c, code lost:
    
        if (r1 == r8) goto L42;
     */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getMovieDetails(int r50, d7.d<? super com.arflix.tv.data.model.MediaItem> r51) {
        /*
            Method dump skipped, instruction units count: 277
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getMovieDetails(int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getPersonDetails(int r10, d7.d<? super com.arflix.tv.data.model.PersonDetails> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.arflix.tv.data.repository.MediaRepository.C08941
            if (r0 == 0) goto L14
            r0 = r11
            com.arflix.tv.data.repository.MediaRepository$getPersonDetails$1 r0 = (com.arflix.tv.data.repository.MediaRepository.C08941) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            com.arflix.tv.data.repository.MediaRepository$getPersonDetails$1 r0 = new com.arflix.tv.data.repository.MediaRepository$getPersonDetails$1
            r0.<init>(r11)
            goto L12
        L1a:
            java.lang.Object r11 = r6.result
            e7.a r0 = e7.a.f15033i
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            k2.c.G(r11)
            goto L49
        L29:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L31:
            k2.c.G(r11)
            com.arflix.tv.data.api.TmdbApi r1 = r9.tmdbApi
            java.lang.String r3 = r9.apiKey
            java.lang.String r5 = r9.contentLanguage
            r6.I$0 = r10
            r6.label = r2
            r4 = 0
            r7 = 4
            r8 = 0
            r2 = r10
            java.lang.Object r11 = com.arflix.tv.data.api.g.f(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r11 != r0) goto L49
            return r0
        L49:
            com.arflix.tv.data.api.TmdbPersonDetails r11 = (com.arflix.tv.data.api.TmdbPersonDetails) r11
            com.arflix.tv.data.model.PersonDetails r10 = com.arflix.tv.data.repository.MediaRepositoryKt.access$toPersonDetails(r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getPersonDetails(int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getReviews(com.arflix.tv.data.model.MediaType r20, int r21, d7.d<? super java.util.List<com.arflix.tv.data.model.Review>> r22) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getReviews(com.arflix.tv.data.model.MediaType, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x02bc A[LOOP:1: B:99:0x02b6->B:101:0x02bc, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0274  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getSeasonEpisodes(int r32, int r33, d7.d<? super java.util.List<com.arflix.tv.data.model.Episode>> r34) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 900
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getSeasonEpisodes(int, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x0143, code lost:
    
        if (r0 == r8) goto L58;
     */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getSimilar(com.arflix.tv.data.model.MediaType r14, int r15, d7.d<? super java.util.List<com.arflix.tv.data.model.MediaItem>> r16) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 434
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getSimilar(com.arflix.tv.data.model.MediaType, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0187 A[EDGE_INSN: B:92:0x0187->B:76:0x0187 BREAK  A[LOOP:0: B:64:0x015f->B:95:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:? A[LOOP:0: B:64:0x015f->B:95:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r11v0, types: [com.arflix.tv.data.model.MediaType, java.lang.Enum, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v32 */
    /* JADX WARN: Type inference failed for: r11v33 */
    /* JADX WARN: Type inference failed for: r11v34 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getStreamingServices(com.arflix.tv.data.model.MediaType r11, int r12, java.lang.String r13, d7.d<? super com.arflix.tv.data.repository.StreamingServicesResult> r14) {
        /*
            Method dump skipped, instruction units count: 468
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getStreamingServices(com.arflix.tv.data.model.MediaType, int, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getTmdbCollectionItems(int r7, d7.d<? super java.util.List<com.arflix.tv.data.model.MediaItem>> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.MediaRepository.C09011
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.MediaRepository$getTmdbCollectionItems$1 r0 = (com.arflix.tv.data.repository.MediaRepository.C09011) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.MediaRepository$getTmdbCollectionItems$1 r0 = new com.arflix.tv.data.repository.MediaRepository$getTmdbCollectionItems$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            e7.a r1 = e7.a.f15033i
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L30
            if (r2 != r4) goto L28
            k2.c.G(r8)     // Catch: java.lang.Throwable -> L47
            goto L44
        L28:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L30:
            k2.c.G(r8)
            com.arflix.tv.data.api.TmdbApi r8 = r6.tmdbApi     // Catch: java.lang.Throwable -> L47 java.lang.Throwable -> L47
            java.lang.String r2 = r6.apiKey     // Catch: java.lang.Throwable -> L47 java.lang.Throwable -> L47
            java.lang.String r5 = r6.contentLanguage     // Catch: java.lang.Throwable -> L47 java.lang.Throwable -> L47
            r0.I$0 = r7     // Catch: java.lang.Throwable -> L47 java.lang.Throwable -> L47
            r0.label = r4     // Catch: java.lang.Throwable -> L47 java.lang.Throwable -> L47
            java.lang.Object r8 = r8.getTmdbCollection(r7, r2, r5, r0)     // Catch: java.lang.Throwable -> L47 java.lang.Throwable -> L47
            if (r8 != r1) goto L44
            return r1
        L44:
            com.arflix.tv.data.api.TmdbCollectionResponse r8 = (com.arflix.tv.data.api.TmdbCollectionResponse) r8     // Catch: java.lang.Throwable -> L47 java.lang.Throwable -> L47
            r3 = r8
        L47:
            if (r3 != 0) goto L4c
            kotlin.collections.z r7 = kotlin.collections.z.f19728i
            return r7
        L4c:
            java.util.List r7 = r3.getParts()
            com.arflix.tv.data.repository.MediaRepository$getTmdbCollectionItems$$inlined$sortedBy$1 r8 = new com.arflix.tv.data.repository.MediaRepository$getTmdbCollectionItems$$inlined$sortedBy$1
            r8.<init>()
            java.util.List r7 = kotlin.collections.x.W0(r7, r8)
            java.util.ArrayList r8 = new java.util.ArrayList
            r0 = 10
            int r0 = kotlin.collections.s.U(r7, r0)
            r8.<init>(r0)
            java.util.Iterator r7 = r7.iterator()
        L68:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L7e
            java.lang.Object r0 = r7.next()
            com.arflix.tv.data.api.TmdbMediaItem r0 = (com.arflix.tv.data.api.TmdbMediaItem) r0
            com.arflix.tv.data.model.MediaType r1 = com.arflix.tv.data.model.MediaType.MOVIE
            com.arflix.tv.data.model.MediaItem r0 = com.arflix.tv.data.repository.MediaRepositoryKt.access$toMediaItem(r0, r1)
            r8.add(r0)
            goto L68
        L7e:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getTmdbCollectionItems(int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00cf, code lost:
    
        if (r14 == r0) goto L49;
     */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0106 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00e4 A[Catch: Exception -> 0x0040, TRY_ENTER, TryCatch #0 {Exception -> 0x0040, blocks: (B:14:0x003b, B:50:0x00d2, B:51:0x00d8, B:52:0x00dc, B:55:0x00e4, B:57:0x00f5, B:59:0x00ff, B:63:0x0107, B:65:0x010b, B:66:0x010f, B:68:0x0115, B:70:0x0126, B:74:0x0132, B:76:0x0136, B:77:0x013a, B:79:0x0140, B:81:0x0153, B:85:0x015f, B:87:0x0163, B:88:0x0167, B:90:0x016d, B:94:0x0180, B:96:0x0185, B:21:0x0056, B:31:0x0081, B:36:0x008e, B:45:0x00b0, B:47:0x00ba, B:39:0x0095, B:40:0x0099, B:42:0x009f, B:28:0x006b), top: B:102:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x010b A[Catch: Exception -> 0x0040, TryCatch #0 {Exception -> 0x0040, blocks: (B:14:0x003b, B:50:0x00d2, B:51:0x00d8, B:52:0x00dc, B:55:0x00e4, B:57:0x00f5, B:59:0x00ff, B:63:0x0107, B:65:0x010b, B:66:0x010f, B:68:0x0115, B:70:0x0126, B:74:0x0132, B:76:0x0136, B:77:0x013a, B:79:0x0140, B:81:0x0153, B:85:0x015f, B:87:0x0163, B:88:0x0167, B:90:0x016d, B:94:0x0180, B:96:0x0185, B:21:0x0056, B:31:0x0081, B:36:0x008e, B:45:0x00b0, B:47:0x00ba, B:39:0x0095, B:40:0x0099, B:42:0x009f, B:28:0x006b), top: B:102:0x0025 }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0185 A[Catch: Exception -> 0x0040, TRY_LEAVE, TryCatch #0 {Exception -> 0x0040, blocks: (B:14:0x003b, B:50:0x00d2, B:51:0x00d8, B:52:0x00dc, B:55:0x00e4, B:57:0x00f5, B:59:0x00ff, B:63:0x0107, B:65:0x010b, B:66:0x010f, B:68:0x0115, B:70:0x0126, B:74:0x0132, B:76:0x0136, B:77:0x013a, B:79:0x0140, B:81:0x0153, B:85:0x015f, B:87:0x0163, B:88:0x0167, B:90:0x016d, B:94:0x0180, B:96:0x0185, B:21:0x0056, B:31:0x0081, B:36:0x008e, B:45:0x00b0, B:47:0x00ba, B:39:0x0095, B:40:0x0099, B:42:0x009f, B:28:0x006b), top: B:102:0x0025 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getTrailerKey(com.arflix.tv.data.model.MediaType r12, int r13, d7.d<? super java.lang.String> r14) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 400
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getTrailerKey(com.arflix.tv.data.model.MediaType, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x010d, code lost:
    
        if (r1 == r8) goto L42;
     */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getTvDetails(int r50, d7.d<? super com.arflix.tv.data.model.MediaItem> r51) {
        /*
            Method dump skipped, instruction units count: 278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getTvDetails(int, d7.d):java.lang.Object");
    }

    public final Object loadCollectionCatalogPage(CatalogConfig catalogConfig, int i10, int i11, d7.d<? super CategoryPageResult> dVar) {
        return ka.l0.c(new C09062(catalogConfig, i11, i10, this, null), dVar);
    }

    public final Object loadCustomCatalog(CatalogConfig catalogConfig, int i10, d7.d<? super Category> dVar) {
        return ka.l0.c(new C09162(catalogConfig, this, i10, null), dVar);
    }

    public final Object loadCustomCatalogPage(CatalogConfig catalogConfig, int i10, int i11, d7.d<? super CategoryPageResult> dVar) {
        return ka.l0.c(new C09172(catalogConfig, this, i10, i11, null), dVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0306 A[Catch: Exception -> 0x0316, LOOP:3: B:108:0x0300->B:110:0x0306, LOOP_END, TryCatch #4 {Exception -> 0x0316, blocks: (B:113:0x0318, B:116:0x031f, B:107:0x02ed, B:108:0x0300, B:110:0x0306, B:103:0x02e5), top: B:131:0x02e5 }] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0335 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0156 A[Catch: Exception -> 0x0166, LOOP:0: B:46:0x0150->B:48:0x0156, LOOP_END, TryCatch #5 {Exception -> 0x0166, blocks: (B:45:0x013d, B:46:0x0150, B:48:0x0156, B:59:0x01b9, B:60:0x01cc, B:62:0x01d2, B:92:0x0280, B:93:0x0293, B:95:0x0299, B:38:0x00d5, B:41:0x00df, B:52:0x016f, B:55:0x017c, B:85:0x0240, B:88:0x024a, B:96:0x02a9, B:99:0x02b8, B:98:0x02b4), top: B:128:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01d2 A[Catch: Exception -> 0x0166, LOOP:1: B:60:0x01cc->B:62:0x01d2, LOOP_END, TRY_LEAVE, TryCatch #5 {Exception -> 0x0166, blocks: (B:45:0x013d, B:46:0x0150, B:48:0x0156, B:59:0x01b9, B:60:0x01cc, B:62:0x01d2, B:92:0x0280, B:93:0x0293, B:95:0x0299, B:38:0x00d5, B:41:0x00df, B:52:0x016f, B:55:0x017c, B:85:0x0240, B:88:0x024a, B:96:0x02a9, B:99:0x02b8, B:98:0x02b4), top: B:128:0x002e }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0220 A[Catch: Exception -> 0x0230, LOOP:4: B:75:0x021a->B:77:0x0220, LOOP_END, TRY_LEAVE, TryCatch #0 {Exception -> 0x0230, blocks: (B:74:0x0207, B:75:0x021a, B:77:0x0220, B:70:0x01fa), top: B:124:0x01fa }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001b  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0299 A[Catch: Exception -> 0x0166, LOOP:2: B:93:0x0293->B:95:0x0299, LOOP_END, TryCatch #5 {Exception -> 0x0166, blocks: (B:45:0x013d, B:46:0x0150, B:48:0x0156, B:59:0x01b9, B:60:0x01cc, B:62:0x01d2, B:92:0x0280, B:93:0x0293, B:95:0x0299, B:38:0x00d5, B:41:0x00df, B:52:0x016f, B:55:0x017c, B:85:0x0240, B:88:0x024a, B:96:0x02a9, B:99:0x02b8, B:98:0x02b4), top: B:128:0x002e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadDiscoverCategory(java.lang.String r26, java.lang.String r27, d7.d<? super com.arflix.tv.data.model.Category> r28) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 846
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.loadDiscoverCategory(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:92:0x0210, code lost:
    
        if (r3 == r0) goto L93;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001d  */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v19 */
    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v21 */
    /* JADX WARN: Type inference failed for: r10v22 */
    /* JADX WARN: Type inference failed for: r10v5, types: [boolean] */
    /* JADX WARN: Type inference failed for: r11v22 */
    /* JADX WARN: Type inference failed for: r11v5, types: [boolean] */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r14v17 */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.arflix.tv.data.repository.MediaRepository] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v25 */
    /* JADX WARN: Type inference failed for: r1v26 */
    /* JADX WARN: Type inference failed for: r1v27 */
    /* JADX WARN: Type inference failed for: r1v28 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v33 */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v38 */
    /* JADX WARN: Type inference failed for: r1v39 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v26 */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r4v32 */
    /* JADX WARN: Type inference failed for: r4v6, types: [com.arflix.tv.data.repository.MediaRepository] */
    /* JADX WARN: Type inference failed for: r4v7, types: [com.arflix.tv.data.repository.MediaRepository] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v28 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 4 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadHomeCategoryPage(java.lang.String r29, int r30, d7.d<? super com.arflix.tv.data.repository.MediaRepository.CategoryPageResult> r31) {
        /*
            Method dump skipped, instruction units count: 704
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.loadHomeCategoryPage(java.lang.String, int, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadHomeServerLibraryPage(java.lang.String r62, int r63, int r64, com.arflix.tv.data.repository.HomeServerLibrarySort r65, com.arflix.tv.data.model.MediaType r66, java.lang.String r67, d7.d<? super com.arflix.tv.data.repository.MediaRepository.CategoryPageResult> r68) {
        /*
            Method dump skipped, instruction units count: 432
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.loadHomeServerLibraryPage(java.lang.String, int, int, com.arflix.tv.data.repository.HomeServerLibrarySort, com.arflix.tv.data.model.MediaType, java.lang.String, d7.d):java.lang.Object");
    }

    public final String peekCachedLogoUrl(MediaType mediaType, int mediaId) {
        String str = mediaType + "_logo_" + mediaId;
        if (this.logoCache.containsKey(str)) {
            return (String) getFromCache(this.logoCache, str);
        }
        return null;
    }

    public final List<Episode> peekCachedSeasonEpisodes(int tvId, int seasonNumber) {
        return (List) getFromCache(this.seasonEpisodesCache, androidx.compose.foundation.c.n(tvId, seasonNumber, "tv_", "_season_"));
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object search(java.lang.String r10, d7.d<? super java.util.List<com.arflix.tv.data.model.MediaItem>> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.arflix.tv.data.repository.MediaRepository.C09401
            if (r0 == 0) goto L14
            r0 = r11
            com.arflix.tv.data.repository.MediaRepository$search$1 r0 = (com.arflix.tv.data.repository.MediaRepository.C09401) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L14
            int r1 = r1 - r2
            r0.label = r1
        L12:
            r6 = r0
            goto L1a
        L14:
            com.arflix.tv.data.repository.MediaRepository$search$1 r0 = new com.arflix.tv.data.repository.MediaRepository$search$1
            r0.<init>(r11)
            goto L12
        L1a:
            java.lang.Object r11 = r6.result
            e7.a r0 = e7.a.f15033i
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r10 = r6.L$0
            java.lang.String r10 = (java.lang.String) r10
            k2.c.G(r11)
            goto L50
        L2d:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L35:
            k2.c.G(r11)
            com.arflix.tv.data.api.TmdbApi r1 = r9.tmdbApi
            r11 = r2
            java.lang.String r2 = r9.apiKey
            java.lang.String r4 = r9.contentLanguage
            r3 = 0
            r6.L$0 = r3
            r6.label = r11
            r5 = 0
            r7 = 8
            r8 = 0
            r3 = r10
            java.lang.Object r11 = com.arflix.tv.data.api.g.q(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r11 != r0) goto L50
            return r0
        L50:
            com.arflix.tv.data.api.TmdbListResponse r11 = (com.arflix.tv.data.api.TmdbListResponse) r11
            java.util.List r10 = r11.getResults()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.Iterator r10 = r10.iterator()
        L5f:
            boolean r0 = r10.hasNext()
            java.lang.String r1 = "tv"
            if (r0 == 0) goto L89
            java.lang.Object r0 = r10.next()
            r2 = r0
            com.arflix.tv.data.api.TmdbMediaItem r2 = (com.arflix.tv.data.api.TmdbMediaItem) r2
            java.lang.String r3 = r2.getMediaType()
            java.lang.String r4 = "movie"
            boolean r3 = kotlin.jvm.internal.p.a(r3, r4)
            if (r3 != 0) goto L85
            java.lang.String r2 = r2.getMediaType()
            boolean r1 = kotlin.jvm.internal.p.a(r2, r1)
            if (r1 == 0) goto L5f
        L85:
            r11.add(r0)
            goto L5f
        L89:
            java.util.ArrayList r10 = new java.util.ArrayList
            r0 = 10
            int r0 = kotlin.collections.s.U(r11, r0)
            r10.<init>(r0)
            java.util.Iterator r11 = r11.iterator()
        L98:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto Lbb
            java.lang.Object r0 = r11.next()
            com.arflix.tv.data.api.TmdbMediaItem r0 = (com.arflix.tv.data.api.TmdbMediaItem) r0
            java.lang.String r2 = r0.getMediaType()
            boolean r2 = kotlin.jvm.internal.p.a(r2, r1)
            if (r2 == 0) goto Lb1
            com.arflix.tv.data.model.MediaType r2 = com.arflix.tv.data.model.MediaType.TV
            goto Lb3
        Lb1:
            com.arflix.tv.data.model.MediaType r2 = com.arflix.tv.data.model.MediaType.MOVIE
        Lb3:
            com.arflix.tv.data.model.MediaItem r0 = com.arflix.tv.data.repository.MediaRepositoryKt.access$toMediaItem(r0, r2)
            r10.add(r0)
            goto L98
        Lbb:
            r9.cacheItems(r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.search(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:13|(3:79|14|15)|73|39|48|(1:50)|51|53|(1:55)(3:56|(1:58)|59)|(1:61)|62|28|(2:30|(7:77|32|33|75|34|35|36)(7:52|53|(0)(0)|(0)|62|28|(7:63|(2:66|64)|81|67|(1:69)|(1:71)|72)(0)))(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(7:(1:77)|32|33|75|34|35|36) */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00ac, code lost:
    
        if (r0 == r3) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0187, code lost:
    
        if (r0 == r3) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0189, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0191, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0193, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0194, code lost:
    
        r13 = 2;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x0187 -> B:73:0x018a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x0199 -> B:48:0x019f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x01a8 -> B:53:0x01aa). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object searchPeopleKnownFor(java.lang.String r17, int r18, d7.d<? super java.util.List<com.arflix.tv.data.repository.PersonMediaSearchResult>> r19) {
        /*
            Method dump skipped, instruction units count: 503
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.searchPeopleKnownFor(java.lang.String, int, d7.d):java.lang.Object");
    }

    public final void setContentLanguage(String str) {
        if (kotlin.text.o.h0(str)) {
            str = "en-US";
        }
        this.contentLanguage = kotlin.text.u.O(str, "iw", "he", false).replace('_', '-');
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getLogoUrl(com.arflix.tv.data.model.MediaItem r8, d7.d<? super java.lang.String> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.arflix.tv.data.repository.MediaRepository.AnonymousClass3
            if (r0 == 0) goto L13
            r0 = r9
            com.arflix.tv.data.repository.MediaRepository$getLogoUrl$3 r0 = (com.arflix.tv.data.repository.MediaRepository.AnonymousClass3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.MediaRepository$getLogoUrl$3 r0 = new com.arflix.tv.data.repository.MediaRepository$getLogoUrl$3
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            e7.a r6 = e7.a.f15033i
            if (r1 == 0) goto L46
            if (r1 == r4) goto L2e
            if (r1 == r3) goto L3e
            if (r1 != r2) goto L36
            java.lang.Object r8 = r0.L$1
            x6.x r8 = (x6.x) r8
        L2e:
            java.lang.Object r8 = r0.L$0
            com.arflix.tv.data.model.MediaItem r8 = (com.arflix.tv.data.model.MediaItem) r8
            k2.c.G(r9)
            return r9
        L36:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3e:
            java.lang.Object r8 = r0.L$0
            com.arflix.tv.data.model.MediaItem r8 = (com.arflix.tv.data.model.MediaItem) r8
            k2.c.G(r9)
            goto L87
        L46:
            k2.c.G(r9)
            int r9 = r8.getId()
            java.lang.Integer r1 = new java.lang.Integer
            r1.<init>(r9)
            int r9 = r1.intValue()
            if (r9 <= 0) goto L59
            goto L5a
        L59:
            r1 = r5
        L5a:
            if (r1 == 0) goto L75
            int r9 = r1.intValue()
            com.arflix.tv.data.model.MediaType r8 = r8.getMediaType()
            r0.L$0 = r5
            r0.I$0 = r9
            r1 = 0
            r0.I$1 = r1
            r0.label = r4
            java.lang.Object r8 = r7.getLogoUrl(r8, r9, r0)
            if (r8 != r6) goto L74
            goto La4
        L74:
            return r8
        L75:
            boolean r9 = r8.isHomeServer()
            if (r9 != 0) goto L7c
            return r5
        L7c:
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r9 = r7.resolveHomeServerLogoRef(r8, r0)
            if (r9 != r6) goto L87
            goto La4
        L87:
            x6.x r9 = (x6.x) r9
            if (r9 != 0) goto L8c
            return r5
        L8c:
            java.lang.Object r8 = r9.f22608i
            com.arflix.tv.data.model.MediaType r8 = (com.arflix.tv.data.model.MediaType) r8
            java.lang.Object r9 = r9.f22609l
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r2
            java.lang.Object r8 = r7.getLogoUrl(r8, r9, r0)
            if (r8 != r6) goto La5
        La4:
            return r6
        La5:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepository.getLogoUrl(com.arflix.tv.data.model.MediaItem, d7.d):java.lang.Object");
    }
}
