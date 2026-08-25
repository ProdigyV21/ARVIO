package com.arflix.tv.data.repository;

import android.content.Context;
import androidx.fragment.app.a2;
import androidx.media3.datasource.cache.ContentMetadata;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.BuildConfig;
import com.arflix.tv.R;
import com.arflix.tv.data.api.TraktApi;
import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.AddonCatalog;
import com.arflix.tv.data.model.AddonCatalogExtra;
import com.arflix.tv.data.model.AddonManifest;
import com.arflix.tv.data.model.AddonType;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.CatalogKind;
import com.arflix.tv.data.model.CatalogPackManifest;
import com.arflix.tv.data.model.CatalogSourceType;
import com.arflix.tv.data.model.CatalogValidationResult;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.CollectionGroupKind;
import com.arflix.tv.data.model.CollectionSourceConfig;
import com.arflix.tv.data.model.CollectionTileShape;
import com.arflix.tv.data.model.SportsAddonCapabilities;
import com.arflix.tv.network.OkHttpProvider;
import com.arflix.tv.util.AppLogger;
import com.arflix.tv.util.CatalogUrlParser;
import com.arflix.tv.util.ParsedCatalogUrl;
import com.google.android.gms.cast.MediaError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import dagger.hilt.android.qualifiers.ApplicationContext;
import gb.s0;
import io.ktor.http.LinkHeader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import javax.inject.Inject;
import javax.inject.Singleton;
import ka.x0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Singleton
@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010$\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 É\u00012\u00020\u0001:\u0004Ê\u0001É\u0001B3\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u0019\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0086@¢\u0006\u0004\b\u0013\u0010\u0014J\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0016\u001a\u00020\u0015H\u0086@¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u000fH\u0086@¢\u0006\u0004\b\u0019\u0010\u0014J\u001e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00150\u000f2\u0006\u0010\u0016\u001a\u00020\u0015H\u0086@¢\u0006\u0004\b\u001a\u0010\u0018J\u001e\u0010\u001d\u001a\u00020\u001c2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00150\u000fH\u0086@¢\u0006\u0004\b\u001d\u0010\u001eJ&\u0010\u001f\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u00152\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00150\u000fH\u0086@¢\u0006\u0004\b\u001f\u0010 J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00150\u000fH\u0086@¢\u0006\u0004\b!\u0010\u0014J\u001e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00150\u000f2\u0006\u0010\u0016\u001a\u00020\u0015H\u0086@¢\u0006\u0004\b\"\u0010\u0018J\u001e\u0010#\u001a\u00020\u001c2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00150\u000fH\u0086@¢\u0006\u0004\b#\u0010\u001eJ&\u0010$\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u00152\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00150\u000fH\u0086@¢\u0006\u0004\b$\u0010 J\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00150\u000f2\u0006\u0010\u0016\u001a\u00020\u0015H\u0086@¢\u0006\u0004\b%\u0010\u0018J&\u0010&\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u00152\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00150\u000fH\u0086@¢\u0006\u0004\b&\u0010 J&\u0010(\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u00152\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0086@¢\u0006\u0004\b(\u0010 J$\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020)0\u000fH\u0086@¢\u0006\u0004\b+\u0010\u001eJ$\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0086@¢\u0006\u0004\b-\u0010\u001eJ\u001e\u00101\u001a\u0002002\f\u0010/\u001a\b\u0012\u0004\u0012\u00020.0\u000fH\u0086@¢\u0006\u0004\b1\u0010\u001eJ\u001e\u00104\u001a\u0002002\f\u00103\u001a\b\u0012\u0004\u0012\u0002020\u000fH\u0086@¢\u0006\u0004\b4\u0010\u001eJ\u001e\u00109\u001a\b\u0012\u0004\u0012\u000207062\u0006\u00105\u001a\u00020\u0015H\u0086@¢\u0006\u0004\b8\u0010\u0018J*\u0010=\u001a\b\u0012\u0004\u0012\u000207062\u0006\u00105\u001a\u00020\u00152\n\b\u0002\u0010:\u001a\u0004\u0018\u000107H\u0086@¢\u0006\u0004\b;\u0010<J\u001e\u0010@\u001a\b\u0012\u0004\u0012\u00020\u001c062\u0006\u0010>\u001a\u00020\u0015H\u0086@¢\u0006\u0004\b?\u0010\u0018J\u001e\u0010C\u001a\b\u0012\u0004\u0012\u00020\u0010062\u0006\u0010A\u001a\u00020\u0015H\u0086@¢\u0006\u0004\bB\u0010\u0018J&\u0010G\u001a\b\u0012\u0004\u0012\u00020\u0010062\u0006\u0010D\u001a\u00020\u00152\u0006\u0010A\u001a\u00020\u0015H\u0086@¢\u0006\u0004\bE\u0010FJ\u001e\u0010I\u001a\b\u0012\u0004\u0012\u00020\u001c062\u0006\u0010D\u001a\u00020\u0015H\u0086@¢\u0006\u0004\bH\u0010\u0018J \u0010K\u001a\u0002002\u0006\u0010D\u001a\u00020\u00152\u0006\u0010J\u001a\u00020\u0015H\u0086@¢\u0006\u0004\bK\u0010FJ\u0018\u0010L\u001a\u0002002\u0006\u0010D\u001a\u00020\u0015H\u0086@¢\u0006\u0004\bL\u0010\u0018J\u0018\u0010M\u001a\u0002002\u0006\u0010D\u001a\u00020\u0015H\u0086@¢\u0006\u0004\bM\u0010\u0018J\u001e\u0010N\u001a\u00020\u001c2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0086@¢\u0006\u0004\bN\u0010\u001eJ\u0015\u0010P\u001a\u00020O2\u0006\u0010A\u001a\u00020\u0015¢\u0006\u0004\bP\u0010QJ\u001d\u0010S\u001a\b\u0012\u0004\u0012\u00020\u00150R2\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\bS\u0010TJ\u001d\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00150R2\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\bU\u0010TJ\u001d\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00150R2\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\bV\u0010TJ\u001d\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00150R2\u0006\u0010\u0016\u001a\u00020\u0015H\u0002¢\u0006\u0004\bW\u0010TJ%\u0010[\u001a\b\u0012\u0004\u0012\u00020\u00150Z2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010Y\u001a\u00020XH\u0002¢\u0006\u0004\b[\u0010\\J%\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00150Z2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010Y\u001a\u00020XH\u0002¢\u0006\u0004\b]\u0010\\J%\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00150Z2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010Y\u001a\u00020XH\u0002¢\u0006\u0004\b^\u0010\\J \u0010_\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010D\u001a\u00020\u0015H\u0082@¢\u0006\u0004\b_\u0010FJ \u0010`\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010D\u001a\u00020\u0015H\u0082@¢\u0006\u0004\b`\u0010FJ \u0010a\u001a\u00020\u001c2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010D\u001a\u00020\u0015H\u0082@¢\u0006\u0004\ba\u0010FJ\u0010\u0010b\u001a\u00020\u0015H\u0082@¢\u0006\u0004\bb\u0010\u0014J\u0016\u0010c\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0082@¢\u0006\u0004\bc\u0010\u0014J\u0017\u0010d\u001a\u0002002\u0006\u0010D\u001a\u00020\u0015H\u0002¢\u0006\u0004\bd\u0010eJ\u0017\u0010g\u001a\u0002002\u0006\u0010f\u001a\u00020\u0010H\u0002¢\u0006\u0004\bg\u0010hJ\u0017\u0010i\u001a\u00020\u00102\u0006\u0010f\u001a\u00020\u0010H\u0002¢\u0006\u0004\bi\u0010jJ#\u0010k\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002¢\u0006\u0004\bk\u0010lJ\u001e\u0010m\u001a\u00020\u001c2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0082@¢\u0006\u0004\bm\u0010\u001eJ%\u0010n\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\f\u0010/\u001a\b\u0012\u0004\u0012\u00020.0\u000fH\u0002¢\u0006\u0004\bn\u0010oJ!\u0010s\u001a\u0004\u0018\u00010\u00102\u0006\u0010p\u001a\u00020.2\u0006\u0010r\u001a\u00020qH\u0002¢\u0006\u0004\bs\u0010tJ\u001b\u0010v\u001a\u0004\u0018\u00010\u00152\b\u0010u\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0004\bv\u0010wJ'\u0010z\u001a\u00020\u00152\u0006\u0010x\u001a\u00020\u00152\u0006\u0010y\u001a\u00020\u00152\u0006\u0010D\u001a\u00020\u0015H\u0002¢\u0006\u0004\bz\u0010{J-\u0010~\u001a\u0016\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0015\u0018\u00010}2\b\u0010|\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0004\b~\u0010\u007fJ\u001a\u0010\u0081\u0001\u001a\u00020\u00152\u0007\u0010\u0080\u0001\u001a\u00020\u0015H\u0002¢\u0006\u0005\b\u0081\u0001\u0010wJ\u001a\u0010\u0082\u0001\u001a\u00020\u00152\u0007\u0010\u0080\u0001\u001a\u00020\u0015H\u0002¢\u0006\u0005\b\u0082\u0001\u0010wJ\u001a\u0010\u0083\u0001\u001a\u00020\u00152\u0007\u0010\u0080\u0001\u001a\u00020\u0015H\u0002¢\u0006\u0005\b\u0083\u0001\u0010wJ6\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\r\u0010\u0084\u0001\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\r\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0002¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u0019\u0010\u0088\u0001\u001a\u0002002\u0006\u0010f\u001a\u00020\u0010H\u0002¢\u0006\u0005\b\u0088\u0001\u0010hJ)\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008c\u00012\u0007\u0010\u0089\u0001\u001a\u00020\u00152\b\u0010\u008b\u0001\u001a\u00030\u008a\u0001H\u0082@¢\u0006\u0006\b\u008d\u0001\u0010\u008e\u0001J(\u0010\u008f\u0001\u001a\u0005\u0018\u00010\u008c\u00012\u0007\u0010\u0089\u0001\u001a\u00020\u00152\b\u0010\u008b\u0001\u001a\u00030\u008a\u0001H\u0002¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001J\u001a\u0010\u0091\u0001\u001a\u00020\u00152\u0007\u0010\u0089\u0001\u001a\u00020\u0015H\u0002¢\u0006\u0005\b\u0091\u0001\u0010wJ\u001e\u0010\u0092\u0001\u001a\u0005\u0018\u00010\u008c\u00012\u0007\u0010\u0089\u0001\u001a\u00020\u0015H\u0082@¢\u0006\u0005\b\u0092\u0001\u0010\u0018J\u001e\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u008c\u00012\u0007\u0010\u0089\u0001\u001a\u00020\u0015H\u0082@¢\u0006\u0005\b\u0093\u0001\u0010\u0018J\u001c\u0010\u0094\u0001\u001a\u0004\u0018\u00010\u00152\u0007\u0010\u0089\u0001\u001a\u00020\u0015H\u0002¢\u0006\u0005\b\u0094\u0001\u0010wJ\u001c\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00152\u0007\u0010\u0095\u0001\u001a\u00020\u0015H\u0002¢\u0006\u0005\b\u0096\u0001\u0010wJ\u001d\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u00152\u0007\u0010\u0089\u0001\u001a\u00020\u0015H\u0082@¢\u0006\u0005\b\u0097\u0001\u0010\u0018J#\u0010\u0099\u0001\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\t\u0010\u0098\u0001\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0006\b\u0099\u0001\u0010\u009a\u0001J\u001b\u0010\u009b\u0001\u001a\u0004\u0018\u00010\u00102\u0006\u0010f\u001a\u00020\u0010H\u0002¢\u0006\u0005\b\u009b\u0001\u0010jJ\u001e\u0010\u009e\u0001\u001a\u00030\u009d\u00012\t\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0006\b\u009e\u0001\u0010\u009f\u0001J \u0010¡\u0001\u001a\u0005\u0018\u00010 \u00012\t\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0006\b¡\u0001\u0010¢\u0001J\u001e\u0010¤\u0001\u001a\u00030£\u00012\t\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0006\b¤\u0001\u0010¥\u0001J1\u0010§\u0001\u001a\u00030\u008a\u00012\u0007\u0010\u009c\u0001\u001a\u00020\u00152\t\u0010¦\u0001\u001a\u0004\u0018\u00010\u00152\b\u0010|\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0006\b§\u0001\u0010¨\u0001J(\u0010©\u0001\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010Y\u001a\u00020XH\u0002¢\u0006\u0006\b©\u0001\u0010ª\u0001J\u001f\u0010«\u0001\u001a\u0004\u0018\u00010\u00152\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0006\b«\u0001\u0010¬\u0001R\u0015\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0003\u0010\u00ad\u0001R\u0015\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0005\u0010®\u0001R\u0015\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0007\u0010¯\u0001R\u0015\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\t\u0010°\u0001R\u0015\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u000b\u0010±\u0001R-\u0010·\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00100²\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b³\u0001\u0010´\u0001\u001a\u0006\bµ\u0001\u0010¶\u0001R\u001b\u0010¸\u0001\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¸\u0001\u0010¹\u0001R&\u0010½\u0001\u001a\b\u0012\u0004\u0012\u00020\u00150Z8BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bº\u0001\u0010´\u0001\u001a\u0006\b»\u0001\u0010¼\u0001R\u0018\u0010¿\u0001\u001a\u00030¾\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¿\u0001\u0010À\u0001R\u001d\u0010Á\u0001\u001a\b\u0012\u0004\u0012\u00020\u00150R8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÁ\u0001\u0010Â\u0001R\u001d\u0010Ã\u0001\u001a\b\u0012\u0004\u0012\u00020\u00150R8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÃ\u0001\u0010Â\u0001R\"\u0010Æ\u0001\u001a\r Å\u0001*\u0005\u0018\u00010Ä\u00010Ä\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÆ\u0001\u0010Ç\u0001R\"\u0010È\u0001\u001a\r Å\u0001*\u0005\u0018\u00010Ä\u00010Ä\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÈ\u0001\u0010Ç\u0001¨\u0006Ë\u0001"}, d2 = {"Lcom/arflix/tv/data/repository/CatalogRepository;", "", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/ProfileManager;", "profileManager", "Lcom/arflix/tv/data/api/TraktApi;", "traktApi", "Lgb/h0;", "okHttpClient", "Lcom/arflix/tv/data/repository/CloudSyncInvalidationBus;", "invalidationBus", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/ProfileManager;Lcom/arflix/tv/data/api/TraktApi;Lgb/h0;Lcom/arflix/tv/data/repository/CloudSyncInvalidationBus;)V", "Lna/j;", "", "Lcom/arflix/tv/data/model/CatalogConfig;", "observeCatalogs", "()Lna/j;", "getCatalogs", "(Ld7/d;)Ljava/lang/Object;", "", "profileId", "getCatalogsForProfile", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "getHiddenPreinstalledCatalogIdsForActiveProfile", "getHiddenPreinstalledCatalogIdsForProfile", "ids", "Lx6/t0;", "setHiddenPreinstalledCatalogIdsForActiveProfile", "(Ljava/util/List;Ld7/d;)Ljava/lang/Object;", "setHiddenPreinstalledCatalogIdsForProfile", "(Ljava/lang/String;Ljava/util/List;Ld7/d;)Ljava/lang/Object;", "getHiddenAddonCatalogIdsForActiveProfile", "getHiddenAddonCatalogIdsForProfile", "setHiddenAddonCatalogIdsForActiveProfile", "setHiddenAddonCatalogIdsForProfile", "getHiddenHomeServerCatalogIdsForProfile", "setHiddenHomeServerCatalogIdsForProfile", "catalogs", "replaceCatalogsForProfile", "Lcom/arflix/tv/data/model/Category;", "defaultCategories", "ensurePreinstalled", "defaultPreinstalled", "ensurePreinstalledDefaults", "Lcom/arflix/tv/data/model/Addon;", "addons", "", "syncAddonCatalogs", "Lcom/arflix/tv/data/repository/HomeServerCatalogCandidate;", "candidates", "syncHomeServerCatalogs", "packUrl", "Lx6/d0;", "Lcom/arflix/tv/data/model/CatalogPackManifest;", "fetchCatalogPackManifest-gIAlu-s", "fetchCatalogPackManifest", "manifest", "addCatalogPack-0E7RQCE", "(Ljava/lang/String;Lcom/arflix/tv/data/model/CatalogPackManifest;Ld7/d;)Ljava/lang/Object;", "addCatalogPack", "packId", "removeCatalogPack-gIAlu-s", "removeCatalogPack", "rawUrl", "addCustomCatalog-gIAlu-s", "addCustomCatalog", "catalogId", "updateCustomCatalog-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "updateCustomCatalog", "removeCustomCatalog-gIAlu-s", "removeCustomCatalog", "newTitle", "renameCatalog", "moveCatalogUp", "moveCatalogDown", "replaceCatalogsForActiveProfile", "Lcom/arflix/tv/data/model/CatalogValidationResult;", "validateCatalogUrl", "(Ljava/lang/String;)Lcom/arflix/tv/data/model/CatalogValidationResult;", "Lc1/e;", "catalogsKey", "(Ljava/lang/String;)Lc1/e;", "hiddenPreinstalledKey", "hiddenAddonKey", "hiddenHomeServerKey", "Lc1/g;", "prefs", "", "decodeHiddenPreinstalled", "(Ljava/lang/String;Lc1/g;)Ljava/util/Set;", "decodeHiddenAddon", "decodeHiddenHomeServer", "hidePreinstalledCatalog", "hideAddonCatalog", "hideHomeServerCatalog", "activeProfileId", "readCatalogsForActiveProfile", "isBundledPreinstalledCatalogId", "(Ljava/lang/String;)Z", "config", "isPreinstalledCatalog", "(Lcom/arflix/tv/data/model/CatalogConfig;)Z", "refreshBundledPreinstalledCatalog", "(Lcom/arflix/tv/data/model/CatalogConfig;)Lcom/arflix/tv/data/model/CatalogConfig;", "sanitizeCollectionCatalogs", "(Ljava/util/List;)Ljava/util/List;", "saveCatalogs", "buildAddonFingerprint", "(Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;", "addon", "Lcom/arflix/tv/data/model/AddonCatalog;", "catalog", "buildAddonCatalogConfig", "(Lcom/arflix/tv/data/model/Addon;Lcom/arflix/tv/data/model/AddonCatalog;)Lcom/arflix/tv/data/model/CatalogConfig;", "rawType", "normalizeAddonCatalogType", "(Ljava/lang/String;)Ljava/lang/String;", "addonId", "catalogType", "buildAddonSourceRef", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "sourceRef", "Lx6/j0;", "parseAddonSourceRef", "(Ljava/lang/String;)Lx6/j0;", "value", "urlEncode", "urlDecode", "sha256Short", "current", "desiredDefaults", "migrateLegacyCollectionBlockOrder", "(Ljava/util/List;Ljava/util/List;)Ljava/util/List;", "isVisibleCatalogInSettings", "url", "Lcom/arflix/tv/data/model/CatalogSourceType;", "sourceType", "Lcom/arflix/tv/data/repository/CatalogRepository$ResolvedCatalog;", "resolveMetadata", "(Ljava/lang/String;Lcom/arflix/tv/data/model/CatalogSourceType;Ld7/d;)Ljava/lang/Object;", "fallbackMetadata", "(Ljava/lang/String;Lcom/arflix/tv/data/model/CatalogSourceType;)Lcom/arflix/tv/data/repository/CatalogRepository$ResolvedCatalog;", "canonicalizeTraktUrl", "resolveTraktMetadata", "resolveMdblistMetadata", "extractMdblistSlugTitle", "html", "extractTraktUrl", "fetchUrl", "json", "parseCatalogsJson", "(Ljava/lang/String;)Ljava/util/List;", "normalizeCatalogConfig", "raw", "Lcom/arflix/tv/data/model/CatalogKind;", "parseCatalogKindCompat", "(Ljava/lang/String;)Lcom/arflix/tv/data/model/CatalogKind;", "Lcom/arflix/tv/data/model/CollectionGroupKind;", "parseCollectionGroupCompat", "(Ljava/lang/String;)Lcom/arflix/tv/data/model/CollectionGroupKind;", "Lcom/arflix/tv/data/model/CollectionTileShape;", "parseCollectionTileShapeCompat", "(Ljava/lang/String;)Lcom/arflix/tv/data/model/CollectionTileShape;", "sourceUrl", "parseSourceTypeCompat", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/model/CatalogSourceType;", "readCatalogsFromPrefs", "(Ljava/lang/String;Lc1/g;)Ljava/util/List;", "asTrimmedString", "(Ljava/lang/Object;)Ljava/lang/String;", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/ProfileManager;", "Lcom/arflix/tv/data/api/TraktApi;", "Lgb/h0;", "Lcom/arflix/tv/data/repository/CloudSyncInvalidationBus;", "", "bundledPreinstalledCatalogsById$delegate", "Lx6/s;", "getBundledPreinstalledCatalogsById", "()Ljava/util/Map;", "bundledPreinstalledCatalogsById", "lastSyncedAddonFingerprint", "Ljava/lang/String;", "bundledPreinstalledCatalogIds$delegate", "getBundledPreinstalledCatalogIds", "()Ljava/util/Set;", "bundledPreinstalledCatalogIds", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "legacyDefaultKey", "Lc1/e;", "legacyGlobalKey", "Ljava/lang/reflect/Type;", "kotlin.jvm.PlatformType", "listType", "Ljava/lang/reflect/Type;", "hiddenListType", "Companion", "ResolvedCatalog", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class CatalogRepository {
    private static final String ADDON_SOURCE_REF_PREFIX = "addon_catalog|";

    /* JADX INFO: renamed from: bundledPreinstalledCatalogIds$delegate, reason: from kotlin metadata */
    private final x6.s bundledPreinstalledCatalogIds;

    /* JADX INFO: renamed from: bundledPreinstalledCatalogsById$delegate, reason: from kotlin metadata */
    private final x6.s bundledPreinstalledCatalogsById;
    private final Context context;
    private final CloudSyncInvalidationBus invalidationBus;
    private volatile String lastSyncedAddonFingerprint;
    private final gb.h0 okHttpClient;
    private final ProfileManager profileManager;
    private final TraktApi traktApi;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private final Gson gson = new Gson();
    private final c1.e legacyDefaultKey = new c1.e("profile_default_catalogs_v1");
    private final c1.e legacyGlobalKey = new c1.e("catalogs_v1");
    private final Type listType = TypeToken.getParameterized(List.class, CatalogConfig.class).getType();
    private final Type hiddenListType = TypeToken.getParameterized(List.class, String.class).getType();

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/arflix/tv/data/repository/CatalogRepository$Companion;", "", "<init>", "()V", "ADDON_SOURCE_REF_PREFIX", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/data/repository/CatalogRepository$ResolvedCatalog;", "", LinkHeader.Parameters.Title, "", "sourceRef", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "getSourceRef", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ResolvedCatalog {
        private final String sourceRef;
        private final String title;

        public ResolvedCatalog(String str, String str2) {
            this.title = str;
            this.sourceRef = str2;
        }

        public static /* synthetic */ ResolvedCatalog copy$default(ResolvedCatalog resolvedCatalog, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = resolvedCatalog.title;
            }
            if ((i10 & 2) != 0) {
                str2 = resolvedCatalog.sourceRef;
            }
            return resolvedCatalog.copy(str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSourceRef() {
            return this.sourceRef;
        }

        public final ResolvedCatalog copy(String title, String sourceRef) {
            return new ResolvedCatalog(title, sourceRef);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ResolvedCatalog)) {
                return false;
            }
            ResolvedCatalog resolvedCatalog = (ResolvedCatalog) other;
            return kotlin.jvm.internal.p.a(this.title, resolvedCatalog.title) && kotlin.jvm.internal.p.a(this.sourceRef, resolvedCatalog.sourceRef);
        }

        public final String getSourceRef() {
            return this.sourceRef;
        }

        public final String getTitle() {
            return this.title;
        }

        public int hashCode() {
            return this.sourceRef.hashCode() + (this.title.hashCode() * 31);
        }

        public String toString() {
            return a2.m("ResolvedCatalog(title=", this.title, ", sourceRef=", this.sourceRef, ")");
        }
    }

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
                iArr[CatalogSourceType.PREINSTALLED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[CatalogSourceType.ADDON.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[CatalogSourceType.HOME_SERVER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$activeProfileId$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {170}, m = "activeProfileId", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int I$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.activeProfileId(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$ensurePreinstalledDefaults$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {355, 356, 365, 438}, m = "ensurePreinstalledDefaults", v = 2)
    public static final class C06921 extends f7.c {
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

        public C06921(d7.d<? super C06921> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.ensurePreinstalledDefaults(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$fetchUrl$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Ljava/lang/String;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository$fetchUrl$2", f = "CatalogRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super String>, Object> {
        final /* synthetic */ String $url;
        int label;
        final /* synthetic */ CatalogRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, CatalogRepository catalogRepository, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$url = str;
            this.this$0 = catalogRepository;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return new AnonymousClass2(this.$url, this.this$0, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            s0 s0Var;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            try {
                gb.j0 j0Var = new gb.j0();
                j0Var.i(this.$url);
                j0Var.f15724c.h("User-Agent", OkHttpProvider.INSTANCE.userAgentOr("Mozilla/5.0 (Android TV; ARVIO)"));
                gb.p0 p0VarD = this.this$0.okHttpClient.a(j0Var.b()).d();
                try {
                    String strX = (p0VarD.k() && (s0Var = p0VarD.f15786q) != null) ? s0Var.x() : null;
                    p0VarD.close();
                    return strX;
                } finally {
                }
            } catch (CancellationException e5) {
                throw e5;
            } catch (Exception unused) {
                return null;
            }
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super String> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$getCatalogsForProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {194}, m = "getCatalogsForProfile", v = 2)
    public static final class C06931 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C06931(d7.d<? super C06931> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.getCatalogsForProfile(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$getHiddenAddonCatalogIdsForActiveProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {264, 265}, m = "getHiddenAddonCatalogIdsForActiveProfile", v = 2)
    public static final class C06941 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C06941(d7.d<? super C06941> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.getHiddenAddonCatalogIdsForActiveProfile(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$getHiddenAddonCatalogIdsForProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {271}, m = "getHiddenAddonCatalogIdsForProfile", v = 2)
    public static final class C06951 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C06951(d7.d<? super C06951> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.getHiddenAddonCatalogIdsForProfile(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$getHiddenHomeServerCatalogIdsForProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {303}, m = "getHiddenHomeServerCatalogIdsForProfile", v = 2)
    public static final class C06961 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C06961(d7.d<? super C06961> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.getHiddenHomeServerCatalogIdsForProfile(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$getHiddenPreinstalledCatalogIdsForActiveProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {226, 227}, m = "getHiddenPreinstalledCatalogIdsForActiveProfile", v = 2)
    public static final class C06971 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C06971(d7.d<? super C06971> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.getHiddenPreinstalledCatalogIdsForActiveProfile(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$getHiddenPreinstalledCatalogIdsForProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {233}, m = "getHiddenPreinstalledCatalogIdsForProfile", v = 2)
    public static final class C06981 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C06981(d7.d<? super C06981> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.getHiddenPreinstalledCatalogIdsForProfile(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$hideAddonCatalog$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {TsExtractor.TS_STREAM_TYPE_DTS}, m = "hideAddonCatalog", v = 2)
    public static final class C06991 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C06991(d7.d<? super C06991> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.hideAddonCatalog(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$hideAddonCatalog$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository$hideAddonCatalog$2", f = "CatalogRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C07002 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ String $profileId;
        final /* synthetic */ String $trimmed;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07002(String str, String str2, d7.d<? super C07002> dVar) {
            super(2, dVar);
            this.$profileId = str;
            this.$trimmed = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07002 c07002 = CatalogRepository.this.new C07002(this.$profileId, this.$trimmed, dVar);
            c07002.L$0 = obj;
            return c07002;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C07002) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            Set setF1 = kotlin.collections.x.f1(CatalogRepository.this.decodeHiddenAddon(this.$profileId, bVar));
            setF1.add(this.$trimmed);
            bVar.d(CatalogRepository.this.hiddenAddonKey(this.$profileId), CatalogRepository.this.gson.toJson(kotlin.collections.x.c1(setF1)));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$hideHomeServerCatalog$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {149}, m = "hideHomeServerCatalog", v = 2)
    public static final class C07011 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C07011(d7.d<? super C07011> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.hideHomeServerCatalog(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$hideHomeServerCatalog$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository$hideHomeServerCatalog$2", f = "CatalogRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C07022 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ String $profileId;
        final /* synthetic */ String $trimmed;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07022(String str, String str2, d7.d<? super C07022> dVar) {
            super(2, dVar);
            this.$profileId = str;
            this.$trimmed = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07022 c07022 = CatalogRepository.this.new C07022(this.$profileId, this.$trimmed, dVar);
            c07022.L$0 = obj;
            return c07022;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C07022) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            Set setF1 = kotlin.collections.x.f1(CatalogRepository.this.decodeHiddenHomeServer(this.$profileId, bVar));
            setF1.add(this.$trimmed);
            bVar.d(CatalogRepository.this.hiddenHomeServerKey(this.$profileId), CatalogRepository.this.gson.toJson(kotlin.collections.x.c1(setF1)));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$hidePreinstalledCatalog$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {127}, m = "hidePreinstalledCatalog", v = 2)
    public static final class C07031 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C07031(d7.d<? super C07031> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.hidePreinstalledCatalog(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$hidePreinstalledCatalog$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository$hidePreinstalledCatalog$2", f = "CatalogRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C07042 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ String $profileId;
        final /* synthetic */ String $trimmed;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07042(String str, String str2, d7.d<? super C07042> dVar) {
            super(2, dVar);
            this.$profileId = str;
            this.$trimmed = str2;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07042 c07042 = CatalogRepository.this.new C07042(this.$profileId, this.$trimmed, dVar);
            c07042.L$0 = obj;
            return c07042;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C07042) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            Set setF1 = kotlin.collections.x.f1(CatalogRepository.this.decodeHiddenPreinstalled(this.$profileId, bVar));
            setF1.add(this.$trimmed);
            bVar.d(CatalogRepository.this.hiddenPreinstalledKey(this.$profileId), CatalogRepository.this.gson.toJson(kotlin.collections.x.c1(setF1)));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$moveCatalogDown$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {928, 939}, m = "moveCatalogDown", v = 2)
    public static final class C07051 extends f7.c {
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

        public C07051(d7.d<? super C07051> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.moveCatalogDown(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$moveCatalogUp$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {912, 923}, m = "moveCatalogUp", v = 2)
    public static final class C07061 extends f7.c {
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

        public C07061(d7.d<? super C07061> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.moveCatalogUp(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$readCatalogsForActiveProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {175, 176, 183}, m = "readCatalogsForActiveProfile", v = 2)
    public static final class C07071 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C07071(d7.d<? super C07071> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.readCatalogsForActiveProfile(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$renameCatalog$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {MediaError.DetailedErrorCode.IMAGE_ERROR, 907}, m = "renameCatalog", v = 2)
    public static final class C07081 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C07081(d7.d<? super C07081> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.renameCatalog(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$replaceCatalogsForProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {336}, m = "replaceCatalogsForProfile", v = 2)
    public static final class C07091 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C07091(d7.d<? super C07091> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.replaceCatalogsForProfile(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$replaceCatalogsForProfile$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository$replaceCatalogsForProfile$2", f = "CatalogRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C07102 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ String $safeProfileId;
        final /* synthetic */ List<CatalogConfig> $sanitized;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07102(String str, List<CatalogConfig> list, d7.d<? super C07102> dVar) {
            super(2, dVar);
            this.$safeProfileId = str;
            this.$sanitized = list;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07102 c07102 = CatalogRepository.this.new C07102(this.$safeProfileId, this.$sanitized, dVar);
            c07102.L$0 = obj;
            return c07102;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C07102) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.d(CatalogRepository.this.catalogsKey(this.$safeProfileId), CatalogRepository.this.gson.toJson(this.$sanitized));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$resolveMdblistMetadata$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {1090, 1093}, m = "resolveMdblistMetadata", v = 2)
    public static final class C07111 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C07111(d7.d<? super C07111> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.resolveMdblistMetadata(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$resolveTraktMetadata$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {1054, 1071}, m = "resolveTraktMetadata", v = 2)
    public static final class C07121 extends f7.c {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C07121(d7.d<? super C07121> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.resolveTraktMetadata(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$saveCatalogs$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {MediaError.DetailedErrorCode.DASH_NETWORK, 325}, m = "saveCatalogs", v = 2)
    public static final class C07131 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C07131(d7.d<? super C07131> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.saveCatalogs(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$saveCatalogs$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository$saveCatalogs$2", f = "CatalogRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C07142 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ String $profileId;
        final /* synthetic */ List<CatalogConfig> $sanitized;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07142(String str, List<CatalogConfig> list, d7.d<? super C07142> dVar) {
            super(2, dVar);
            this.$profileId = str;
            this.$sanitized = list;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07142 c07142 = CatalogRepository.this.new C07142(this.$profileId, this.$sanitized, dVar);
            c07142.L$0 = obj;
            return c07142;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C07142) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.d(CatalogRepository.this.catalogsKey(this.$profileId), CatalogRepository.this.gson.toJson(this.$sanitized));
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$setHiddenAddonCatalogIdsForActiveProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {276, 278}, m = "setHiddenAddonCatalogIdsForActiveProfile", v = 2)
    public static final class C07151 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C07151(d7.d<? super C07151> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.setHiddenAddonCatalogIdsForActiveProfile(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$setHiddenAddonCatalogIdsForActiveProfile$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository$setHiddenAddonCatalogIdsForActiveProfile$2", f = "CatalogRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C07162 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ List<String> $cleaned;
        final /* synthetic */ String $profileId;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ CatalogRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07162(List<String> list, CatalogRepository catalogRepository, String str, d7.d<? super C07162> dVar) {
            super(2, dVar);
            this.$cleaned = list;
            this.this$0 = catalogRepository;
            this.$profileId = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07162 c07162 = new C07162(this.$cleaned, this.this$0, this.$profileId, dVar);
            c07162.L$0 = obj;
            return c07162;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C07162) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            if (this.$cleaned.isEmpty()) {
                bVar.d(this.this$0.hiddenAddonKey(this.$profileId), "");
            } else {
                bVar.d(this.this$0.hiddenAddonKey(this.$profileId), this.this$0.gson.toJson(this.$cleaned));
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$setHiddenAddonCatalogIdsForProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {291}, m = "setHiddenAddonCatalogIdsForProfile", v = 2)
    public static final class C07171 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C07171(d7.d<? super C07171> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.setHiddenAddonCatalogIdsForProfile(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$setHiddenAddonCatalogIdsForProfile$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository$setHiddenAddonCatalogIdsForProfile$2", f = "CatalogRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C07182 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ List<String> $cleaned;
        final /* synthetic */ String $safeProfileId;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ CatalogRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07182(List<String> list, CatalogRepository catalogRepository, String str, d7.d<? super C07182> dVar) {
            super(2, dVar);
            this.$cleaned = list;
            this.this$0 = catalogRepository;
            this.$safeProfileId = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07182 c07182 = new C07182(this.$cleaned, this.this$0, this.$safeProfileId, dVar);
            c07182.L$0 = obj;
            return c07182;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C07182) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            if (this.$cleaned.isEmpty()) {
                bVar.d(this.this$0.hiddenAddonKey(this.$safeProfileId), "");
            } else {
                bVar.d(this.this$0.hiddenAddonKey(this.$safeProfileId), this.this$0.gson.toJson(this.$cleaned));
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$setHiddenHomeServerCatalogIdsForProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {BuildConfig.VERSION_CODE}, m = "setHiddenHomeServerCatalogIdsForProfile", v = 2)
    public static final class C07191 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C07191(d7.d<? super C07191> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.setHiddenHomeServerCatalogIdsForProfile(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$setHiddenHomeServerCatalogIdsForProfile$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository$setHiddenHomeServerCatalogIdsForProfile$2", f = "CatalogRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C07202 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ List<String> $cleaned;
        final /* synthetic */ String $safeProfileId;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ CatalogRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07202(List<String> list, CatalogRepository catalogRepository, String str, d7.d<? super C07202> dVar) {
            super(2, dVar);
            this.$cleaned = list;
            this.this$0 = catalogRepository;
            this.$safeProfileId = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07202 c07202 = new C07202(this.$cleaned, this.this$0, this.$safeProfileId, dVar);
            c07202.L$0 = obj;
            return c07202;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C07202) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            if (this.$cleaned.isEmpty()) {
                bVar.d(this.this$0.hiddenHomeServerKey(this.$safeProfileId), "");
            } else {
                bVar.d(this.this$0.hiddenHomeServerKey(this.$safeProfileId), this.this$0.gson.toJson(this.$cleaned));
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$setHiddenPreinstalledCatalogIdsForActiveProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {238, PsExtractor.VIDEO_STREAM_MASK}, m = "setHiddenPreinstalledCatalogIdsForActiveProfile", v = 2)
    public static final class C07211 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C07211(d7.d<? super C07211> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.setHiddenPreinstalledCatalogIdsForActiveProfile(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$setHiddenPreinstalledCatalogIdsForActiveProfile$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository$setHiddenPreinstalledCatalogIdsForActiveProfile$2", f = "CatalogRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C07222 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ List<String> $cleaned;
        final /* synthetic */ String $profileId;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ CatalogRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07222(List<String> list, CatalogRepository catalogRepository, String str, d7.d<? super C07222> dVar) {
            super(2, dVar);
            this.$cleaned = list;
            this.this$0 = catalogRepository;
            this.$profileId = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07222 c07222 = new C07222(this.$cleaned, this.this$0, this.$profileId, dVar);
            c07222.L$0 = obj;
            return c07222;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C07222) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            if (this.$cleaned.isEmpty()) {
                bVar.d(this.this$0.hiddenPreinstalledKey(this.$profileId), "");
            } else {
                bVar.d(this.this$0.hiddenPreinstalledKey(this.$profileId), this.this$0.gson.toJson(this.$cleaned));
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$setHiddenPreinstalledCatalogIdsForProfile$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {253}, m = "setHiddenPreinstalledCatalogIdsForProfile", v = 2)
    public static final class C07231 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C07231(d7.d<? super C07231> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.setHiddenPreinstalledCatalogIdsForProfile(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$setHiddenPreinstalledCatalogIdsForProfile$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository$setHiddenPreinstalledCatalogIdsForProfile$2", f = "CatalogRepository.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C07242 extends f7.j implements r7.p<c1.b, d7.d<? super t0>, Object> {
        final /* synthetic */ List<String> $cleaned;
        final /* synthetic */ String $safeProfileId;
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ CatalogRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C07242(List<String> list, CatalogRepository catalogRepository, String str, d7.d<? super C07242> dVar) {
            super(2, dVar);
            this.$cleaned = list;
            this.this$0 = catalogRepository;
            this.$safeProfileId = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C07242 c07242 = new C07242(this.$cleaned, this.this$0, this.$safeProfileId, dVar);
            c07242.L$0 = obj;
            return c07242;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super t0> dVar) {
            return ((C07242) create(bVar, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            if (this.$cleaned.isEmpty()) {
                bVar.d(this.this$0.hiddenPreinstalledKey(this.$safeProfileId), "");
            } else {
                bVar.d(this.this$0.hiddenPreinstalledKey(this.$safeProfileId), this.this$0.gson.toJson(this.$cleaned));
            }
            return t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$syncAddonCatalogs$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {444, 454, 477, 516}, m = "syncAddonCatalogs", v = 2)
    public static final class C07251 extends f7.c {
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
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C07251(d7.d<? super C07251> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.syncAddonCatalogs(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.data.repository.CatalogRepository$syncHomeServerCatalogs$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.data.repository.CatalogRepository", f = "CatalogRepository.kt", l = {555, 557, 579, 615}, m = "syncHomeServerCatalogs", v = 2)
    public static final class C07261 extends f7.c {
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
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C07261(d7.d<? super C07261> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CatalogRepository.this.syncHomeServerCatalogs(null, this);
        }
    }

    @Inject
    public CatalogRepository(@ApplicationContext Context context, ProfileManager profileManager, TraktApi traktApi, gb.h0 h0Var, CloudSyncInvalidationBus cloudSyncInvalidationBus) {
        this.context = context;
        this.profileManager = profileManager;
        this.traktApi = traktApi;
        this.okHttpClient = h0Var;
        this.invalidationBus = cloudSyncInvalidationBus;
        int i10 = 2;
        this.bundledPreinstalledCatalogsById = f4.f.o(3, new g0(i10));
        this.bundledPreinstalledCatalogIds = f4.f.o(3, new a0(this, i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0054 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object activeProfileId(d7.d<? super java.lang.String> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.arflix.tv.data.repository.CatalogRepository.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r5
            com.arflix.tv.data.repository.CatalogRepository$activeProfileId$1 r0 = (com.arflix.tv.data.repository.CatalogRepository.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$activeProfileId$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$activeProfileId$1
            r0.<init>(r5)
        L18:
            java.lang.Object r5 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L2d
            if (r1 != r2) goto L25
            k2.c.G(r5)
            goto L4c
        L25:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L2d:
            k2.c.G(r5)
            com.arflix.tv.data.repository.ProfileManager r5 = r4.profileManager
            java.lang.String r5 = r5.getProfileIdSync()
            boolean r1 = kotlin.text.o.h0(r5)
            if (r1 == 0) goto L4e
            com.arflix.tv.data.repository.ProfileManager r5 = r4.profileManager
            r1 = 0
            r0.I$0 = r1
            r0.label = r2
            java.lang.Object r5 = r5.getProfileId(r0)
            e7.a r0 = e7.a.f15033i
            if (r5 != r0) goto L4c
            return r0
        L4c:
            java.lang.String r5 = (java.lang.String) r5
        L4e:
            boolean r0 = kotlin.text.o.h0(r5)
            if (r0 == 0) goto L56
            java.lang.String r5 = "default"
        L56:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.activeProfileId(d7.d):java.lang.Object");
    }

    /* JADX INFO: renamed from: addCatalogPack-0E7RQCE$default, reason: not valid java name */
    public static /* synthetic */ Object m6113addCatalogPack0E7RQCE$default(CatalogRepository catalogRepository, String str, CatalogPackManifest catalogPackManifest, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            catalogPackManifest = null;
        }
        return catalogRepository.m6114addCatalogPack0E7RQCE(str, catalogPackManifest, dVar);
    }

    private final String asTrimmedString(Object value) {
        String str = value instanceof String ? (String) value : null;
        String string = str != null ? kotlin.text.o.L0(str).toString() : null;
        if (string == null || kotlin.text.o.h0(string)) {
            return null;
        }
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CatalogConfig buildAddonCatalogConfig(Addon addon, AddonCatalog catalog) {
        String strNormalizeAddonCatalogType = normalizeAddonCatalogType(catalog.getType());
        if (strNormalizeAddonCatalogType == null) {
            return null;
        }
        String string = kotlin.text.o.L0(catalog.getId()).toString();
        String str = !kotlin.text.o.h0(string) ? string : null;
        if (str == null) {
            return null;
        }
        List<AddonCatalogExtra> extra = catalog.getExtra();
        if (extra != null && !extra.isEmpty()) {
            for (AddonCatalogExtra addonCatalogExtra : extra) {
                if (addonCatalogExtra.isRequired() && !kotlin.text.u.L(addonCatalogExtra.getName(), "skip", true)) {
                    return null;
                }
            }
        }
        String string2 = kotlin.text.o.L0(addon.getId()).toString();
        String str2 = !kotlin.text.o.h0(string2) ? string2 : null;
        if (str2 == null) {
            return null;
        }
        String string3 = kotlin.text.o.L0(catalog.getName()).toString();
        String displayTitle = kotlin.text.o.h0(string3) ? null : string3;
        if (displayTitle == null) {
            displayTitle = CatalogRepositoryKt.toDisplayTitle(str);
        }
        return new CatalogConfig(androidx.compose.material3.d.C("addon_", sha256Short(androidx.compose.material3.d.o(str2, "|", strNormalizeAddonCatalogType, "|", str))), displayTitle, CatalogSourceType.ADDON, null, buildAddonSourceRef(str2, strNormalizeAddonCatalogType, str), false, str2, strNormalizeAddonCatalogType, str, addon.getName(), null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, 33553416, null);
    }

    private final String buildAddonFingerprint(String profileId, List<Addon> addons) {
        List<AddonCatalog> catalogs;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(profileId);
        sb2.append('|');
        for (Addon addon : addons) {
            sb2.append(addon.getId());
            sb2.append(':');
            sb2.append(addon.isInstalled() ? '1' : '0');
            sb2.append(addon.isEnabled() ? '1' : '0');
            sb2.append(addon.getType().name());
            String url = addon.getUrl();
            if (url == null) {
                url = "";
            }
            sb2.append(url);
            AddonManifest manifest = addon.getManifest();
            if (manifest != null && (catalogs = manifest.getCatalogs()) != null) {
                for (AddonCatalog addonCatalog : catalogs) {
                    sb2.append('[');
                    sb2.append(addonCatalog.getType());
                    sb2.append(':');
                    sb2.append(addonCatalog.getId());
                    sb2.append(']');
                }
            }
            sb2.append(',');
        }
        return sb2.toString();
    }

    private final String buildAddonSourceRef(String addonId, String catalogType, String catalogId) {
        String strUrlEncode = urlEncode(addonId);
        String strUrlEncode2 = urlEncode(catalogType);
        String strUrlEncode3 = urlEncode(catalogId);
        StringBuilder sbR = a2.r(ADDON_SOURCE_REF_PREFIX, strUrlEncode, "|", strUrlEncode2, "|");
        sbR.append(strUrlEncode3);
        return sbR.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set bundledPreinstalledCatalogIds_delegate$lambda$0(CatalogRepository catalogRepository) {
        return catalogRepository.getBundledPreinstalledCatalogsById().keySet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map bundledPreinstalledCatalogsById_delegate$lambda$0() {
        List<CatalogConfig> listBuildPreinstalledDefaults$app_playRelease = MediaRepository.INSTANCE.buildPreinstalledDefaults$app_playRelease();
        int iQ0 = kotlin.collections.i0.q0(kotlin.collections.s.U(listBuildPreinstalledDefaults$app_playRelease, 10));
        if (iQ0 < 16) {
            iQ0 = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(iQ0);
        for (Object obj : listBuildPreinstalledDefaults$app_playRelease) {
            linkedHashMap.put(((CatalogConfig) obj).getId(), obj);
        }
        return linkedHashMap;
    }

    private final String canonicalizeTraktUrl(String url) {
        CatalogUrlParser catalogUrlParser = CatalogUrlParser.INSTANCE;
        ParsedCatalogUrl trakt = catalogUrlParser.parseTrakt(url);
        if (trakt == null) {
            return catalogUrlParser.normalize(url);
        }
        if (!(trakt instanceof ParsedCatalogUrl.TraktUserList)) {
            return trakt instanceof ParsedCatalogUrl.TraktList ? androidx.compose.material3.d.C("https://trakt.tv/lists/", ((ParsedCatalogUrl.TraktList) trakt).getListId()) : catalogUrlParser.normalize(url);
        }
        ParsedCatalogUrl.TraktUserList traktUserList = (ParsedCatalogUrl.TraktUserList) trakt;
        return androidx.compose.material3.d.n("https://trakt.tv/users/", traktUserList.getUsername(), "/lists/", traktUserList.getListId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e catalogsKey(String profileId) {
        return new c1.e(a0.c.l("profile_", profileId, "_catalogs_v1"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<String> decodeHiddenAddon(String profileId, c1.g prefs) {
        String str = (String) ((c1.b) prefs).f7368a.get(hiddenAddonKey(profileId));
        kotlin.collections.b0 b0Var = kotlin.collections.b0.f19686i;
        if (str != null && !kotlin.text.o.h0(str)) {
            try {
                Iterable iterable = (List) this.gson.fromJson(str, this.hiddenListType);
                if (iterable == null) {
                    iterable = kotlin.collections.z.f19728i;
                }
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iterable, 10));
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    arrayList.add(kotlin.text.o.L0((String) it.next()).toString());
                }
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : arrayList) {
                    if (!kotlin.text.o.h0((String) obj)) {
                        arrayList2.add(obj);
                    }
                }
                return kotlin.collections.x.g1(arrayList2);
            } catch (Exception e5) {
                AppLogger.INSTANCE.e("CatalogRepository", "Error fetching data, returning empty set", e5);
            }
        }
        return b0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<String> decodeHiddenHomeServer(String profileId, c1.g prefs) {
        String str = (String) ((c1.b) prefs).f7368a.get(hiddenHomeServerKey(profileId));
        kotlin.collections.b0 b0Var = kotlin.collections.b0.f19686i;
        if (str != null && !kotlin.text.o.h0(str)) {
            try {
                Iterable iterable = (List) this.gson.fromJson(str, this.hiddenListType);
                if (iterable == null) {
                    iterable = kotlin.collections.z.f19728i;
                }
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iterable, 10));
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    arrayList.add(kotlin.text.o.L0((String) it.next()).toString());
                }
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : arrayList) {
                    if (!kotlin.text.o.h0((String) obj)) {
                        arrayList2.add(obj);
                    }
                }
                return kotlin.collections.x.g1(arrayList2);
            } catch (Exception e5) {
                AppLogger.INSTANCE.e("CatalogRepository", "Error fetching data, returning empty set", e5);
            }
        }
        return b0Var;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<String> decodeHiddenPreinstalled(String profileId, c1.g prefs) {
        String str = (String) ((c1.b) prefs).f7368a.get(hiddenPreinstalledKey(profileId));
        kotlin.collections.b0 b0Var = kotlin.collections.b0.f19686i;
        if (str != null && !kotlin.text.o.h0(str)) {
            try {
                Iterable iterable = (List) this.gson.fromJson(str, this.hiddenListType);
                if (iterable == null) {
                    iterable = kotlin.collections.z.f19728i;
                }
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(iterable, 10));
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    arrayList.add(kotlin.text.o.L0((String) it.next()).toString());
                }
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : arrayList) {
                    if (!kotlin.text.o.h0((String) obj)) {
                        arrayList2.add(obj);
                    }
                }
                return kotlin.collections.x.g1(arrayList2);
            } catch (Exception e5) {
                AppLogger.INSTANCE.e("CatalogRepository", "Error fetching data, returning empty set", e5);
            }
        }
        return b0Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v6, types: [kotlin.collections.z] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.util.ArrayList] */
    private final String extractMdblistSlugTitle(String url) {
        String strM0;
        ?? arrayList;
        try {
            strM0 = kotlin.text.o.M0(new URI(url).getPath(), '/');
        } catch (Exception unused) {
            strM0 = null;
        }
        if (strM0 != null) {
            List listX0 = kotlin.text.o.x0(strM0, new char[]{'/'}, 6);
            arrayList = new ArrayList();
            for (Object obj : listX0) {
                if (!kotlin.text.o.h0((String) obj)) {
                    arrayList.add(obj);
                }
            }
        } else {
            arrayList = 0;
        }
        if (arrayList == 0) {
            arrayList = kotlin.collections.z.f19728i;
        }
        if (!arrayList.isEmpty()) {
            String str = (String) kotlin.collections.x.w0(arrayList);
            if (!kotlin.text.u.L(str, "lists", true)) {
                return CatalogRepositoryKt.toDisplayTitle(str);
            }
        }
        return null;
    }

    private final String extractTraktUrl(String html) {
        kotlin.text.k kVarB = kotlin.text.m.b(CatalogRepoRegexes.INSTANCE.getTRAKT_URL_REGEX(), html);
        if (kVarB != null) {
            return kVarB.f19946a.group();
        }
        return null;
    }

    private final ResolvedCatalog fallbackMetadata(String url, CatalogSourceType sourceType) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[sourceType.ordinal()];
        if (i10 != 1) {
            if (i10 == 2) {
                return new ResolvedCatalog(this.context.getString(R.string.catalog_mdblist_title), androidx.compose.material3.d.C("mdblist:", url));
            }
            if (i10 == 3 || i10 == 4 || i10 == 5) {
                return null;
            }
            throw new NoWhenBranchMatchedException();
        }
        ParsedCatalogUrl trakt = CatalogUrlParser.INSTANCE.parseTrakt(url);
        if (trakt instanceof ParsedCatalogUrl.TraktUserList) {
            ParsedCatalogUrl.TraktUserList traktUserList = (ParsedCatalogUrl.TraktUserList) trakt;
            return new ResolvedCatalog(CatalogRepositoryKt.toDisplayTitle(traktUserList.getListId()), androidx.compose.material3.d.n("trakt_user:", traktUserList.getUsername(), ":", traktUserList.getListId()));
        }
        if (!(trakt instanceof ParsedCatalogUrl.TraktList)) {
            return null;
        }
        ParsedCatalogUrl.TraktList traktList = (ParsedCatalogUrl.TraktList) trakt;
        return new ResolvedCatalog(CatalogRepositoryKt.toDisplayTitle(traktList.getListId()), androidx.compose.material3.d.C("trakt_list:", traktList.getListId()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object fetchUrl(String str, d7.d<? super String> dVar) {
        return ka.m0.y(x0.f19655d, new AnonymousClass2(str, this, null), dVar);
    }

    private final Set<String> getBundledPreinstalledCatalogIds() {
        return (Set) this.bundledPreinstalledCatalogIds.getValue();
    }

    private final Map<String, CatalogConfig> getBundledPreinstalledCatalogsById() {
        return (Map) this.bundledPreinstalledCatalogsById.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e hiddenAddonKey(String profileId) {
        return new c1.e(a0.c.l("profile_", profileId, "_hidden_addon_catalogs_v1"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e hiddenHomeServerKey(String profileId) {
        return new c1.e(a0.c.l("profile_", profileId, "_hidden_home_server_catalogs_v1"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e hiddenPreinstalledKey(String profileId) {
        return new c1.e(a0.c.l("profile_", profileId, "_hidden_preinstalled_catalogs_v2"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object hideAddonCatalog(java.lang.String r6, java.lang.String r7, d7.d<? super x6.t0> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.CatalogRepository.C06991
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.CatalogRepository$hideAddonCatalog$1 r0 = (com.arflix.tv.data.repository.CatalogRepository.C06991) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$hideAddonCatalog$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$hideAddonCatalog$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            x6.t0 r2 = x6.t0.f22605a
            r3 = 1
            if (r1 == 0) goto L3b
            if (r1 != r3) goto L33
            java.lang.Object r6 = r0.L$2
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r6 = r0.L$1
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r6 = r0.L$0
            java.lang.String r6 = (java.lang.String) r6
            k2.c.G(r8)
            goto L63
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            java.lang.String r7 = androidx.fragment.app.a2.l(r8, r7)
            boolean r8 = kotlin.text.o.h0(r7)
            if (r8 == 0) goto L46
            return r2
        L46:
            android.content.Context r8 = r5.context
            z0.g r8 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r8)
            com.arflix.tv.data.repository.CatalogRepository$hideAddonCatalog$2 r1 = new com.arflix.tv.data.repository.CatalogRepository$hideAddonCatalog$2
            r4 = 0
            r1.<init>(r6, r7, r4)
            r0.L$0 = r6
            r0.L$1 = r4
            r0.L$2 = r4
            r0.label = r3
            java.lang.Object r7 = androidx.work.impl.t.o(r8, r1, r0)
            e7.a r8 = e7.a.f15033i
            if (r7 != r8) goto L63
            return r8
        L63:
            com.arflix.tv.data.repository.CloudSyncInvalidationBus r7 = r5.invalidationBus
            com.arflix.tv.data.repository.CloudSyncScope r8 = com.arflix.tv.data.repository.CloudSyncScope.CATALOGS
            java.lang.String r0 = "hide addon catalog"
            r7.markDirty(r8, r6, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.hideAddonCatalog(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object hideHomeServerCatalog(java.lang.String r6, java.lang.String r7, d7.d<? super x6.t0> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.CatalogRepository.C07011
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.CatalogRepository$hideHomeServerCatalog$1 r0 = (com.arflix.tv.data.repository.CatalogRepository.C07011) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$hideHomeServerCatalog$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$hideHomeServerCatalog$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            x6.t0 r2 = x6.t0.f22605a
            r3 = 1
            if (r1 == 0) goto L3b
            if (r1 != r3) goto L33
            java.lang.Object r6 = r0.L$2
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r6 = r0.L$1
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r6 = r0.L$0
            java.lang.String r6 = (java.lang.String) r6
            k2.c.G(r8)
            goto L63
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            java.lang.String r7 = androidx.fragment.app.a2.l(r8, r7)
            boolean r8 = kotlin.text.o.h0(r7)
            if (r8 == 0) goto L46
            return r2
        L46:
            android.content.Context r8 = r5.context
            z0.g r8 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r8)
            com.arflix.tv.data.repository.CatalogRepository$hideHomeServerCatalog$2 r1 = new com.arflix.tv.data.repository.CatalogRepository$hideHomeServerCatalog$2
            r4 = 0
            r1.<init>(r6, r7, r4)
            r0.L$0 = r6
            r0.L$1 = r4
            r0.L$2 = r4
            r0.label = r3
            java.lang.Object r7 = androidx.work.impl.t.o(r8, r1, r0)
            e7.a r8 = e7.a.f15033i
            if (r7 != r8) goto L63
            return r8
        L63:
            com.arflix.tv.data.repository.CloudSyncInvalidationBus r7 = r5.invalidationBus
            com.arflix.tv.data.repository.CloudSyncScope r8 = com.arflix.tv.data.repository.CloudSyncScope.CATALOGS
            java.lang.String r0 = "hide home server catalog"
            r7.markDirty(r8, r6, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.hideHomeServerCatalog(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object hidePreinstalledCatalog(java.lang.String r6, java.lang.String r7, d7.d<? super x6.t0> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.CatalogRepository.C07031
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.CatalogRepository$hidePreinstalledCatalog$1 r0 = (com.arflix.tv.data.repository.CatalogRepository.C07031) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$hidePreinstalledCatalog$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$hidePreinstalledCatalog$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            x6.t0 r2 = x6.t0.f22605a
            r3 = 1
            if (r1 == 0) goto L3b
            if (r1 != r3) goto L33
            java.lang.Object r6 = r0.L$2
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r6 = r0.L$1
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r6 = r0.L$0
            java.lang.String r6 = (java.lang.String) r6
            k2.c.G(r8)
            goto L63
        L33:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3b:
            java.lang.String r7 = androidx.fragment.app.a2.l(r8, r7)
            boolean r8 = kotlin.text.o.h0(r7)
            if (r8 == 0) goto L46
            return r2
        L46:
            android.content.Context r8 = r5.context
            z0.g r8 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r8)
            com.arflix.tv.data.repository.CatalogRepository$hidePreinstalledCatalog$2 r1 = new com.arflix.tv.data.repository.CatalogRepository$hidePreinstalledCatalog$2
            r4 = 0
            r1.<init>(r6, r7, r4)
            r0.L$0 = r6
            r0.L$1 = r4
            r0.L$2 = r4
            r0.label = r3
            java.lang.Object r7 = androidx.work.impl.t.o(r8, r1, r0)
            e7.a r8 = e7.a.f15033i
            if (r7 != r8) goto L63
            return r8
        L63:
            com.arflix.tv.data.repository.CloudSyncInvalidationBus r7 = r5.invalidationBus
            com.arflix.tv.data.repository.CloudSyncScope r8 = com.arflix.tv.data.repository.CloudSyncScope.CATALOGS
            java.lang.String r0 = "hide preinstalled catalog"
            r7.markDirty(r8, r6, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.hidePreinstalledCatalog(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    private final boolean isBundledPreinstalledCatalogId(String catalogId) {
        return getBundledPreinstalledCatalogIds().contains(kotlin.text.o.L0(catalogId).toString());
    }

    private final boolean isPreinstalledCatalog(CatalogConfig config) {
        return config.isPreinstalled() || isBundledPreinstalledCatalogId(config.getId());
    }

    private final boolean isVisibleCatalogInSettings(CatalogConfig config) {
        if (config.getKind() == CatalogKind.COLLECTION) {
            return false;
        }
        if (config.getKind() == CatalogKind.COLLECTION_RAIL) {
            return CollectionTemplateManifest.INSTANCE.isValidCollectionConfig(config);
        }
        return true;
    }

    private final List<CatalogConfig> migrateLegacyCollectionBlockOrder(List<CatalogConfig> current, List<CatalogConfig> desiredDefaults) {
        return current;
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

    private final CatalogConfig normalizeCatalogConfig(CatalogConfig config) throws Exception {
        String strName;
        CatalogKind catalogKindValueOf;
        CollectionTileShape collectionTileShapeValueOf;
        CatalogKind catalogKind;
        if (kotlin.text.o.h0(config.getId()) || kotlin.text.o.h0(config.getTitle())) {
            return null;
        }
        String sourceUrl = config.getSourceUrl();
        String string = sourceUrl != null ? kotlin.text.o.L0(sourceUrl).toString() : null;
        String str = (string == null || kotlin.text.o.h0(string)) ? null : string;
        String sourceRef = config.getSourceRef();
        String string2 = sourceRef != null ? kotlin.text.o.L0(sourceRef).toString() : null;
        String str2 = (string2 == null || kotlin.text.o.h0(string2)) ? null : string2;
        boolean zIsBundledPreinstalledCatalogId = isBundledPreinstalledCatalogId(config.getId());
        x6.j0 addonSourceRef = parseAddonSourceRef(str2);
        try {
            strName = config.getSourceType().name();
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
            strName = "PREINSTALLED";
        }
        String str3 = strName;
        try {
            catalogKindValueOf = CatalogKind.valueOf(config.getKind().name());
        } catch (Exception e6) {
            if (e6 instanceof CancellationException) {
                throw e6;
            }
            catalogKindValueOf = CatalogKind.STANDARD;
        }
        List<CollectionSourceConfig> collectionSources = config.getCollectionSources();
        List<String> list = kotlin.collections.z.f19728i;
        List<String> list2 = collectionSources == null ? list : collectionSources;
        List<String> requiredAddonUrls = config.getRequiredAddonUrls();
        if (requiredAddonUrls != null) {
            list = requiredAddonUrls;
        }
        try {
            collectionTileShapeValueOf = CollectionTileShape.valueOf(config.getCollectionTileShape().name());
        } catch (Exception unused) {
            collectionTileShapeValueOf = CollectionTileShape.LANDSCAPE;
        }
        CollectionTileShape collectionTileShape = collectionTileShapeValueOf;
        String addonId = config.getAddonId();
        String string3 = addonId != null ? kotlin.text.o.L0(addonId).toString() : null;
        if (string3 == null || kotlin.text.o.h0(string3)) {
            string3 = null;
        }
        if (string3 == null) {
            string3 = addonSourceRef != null ? (String) addonSourceRef.f22587i : null;
        }
        String strNormalizeAddonCatalogType = normalizeAddonCatalogType(config.getAddonCatalogType());
        if (strNormalizeAddonCatalogType == null) {
            strNormalizeAddonCatalogType = addonSourceRef != null ? (String) addonSourceRef.f22588l : null;
        }
        String addonCatalogId = config.getAddonCatalogId();
        String string4 = addonCatalogId != null ? kotlin.text.o.L0(addonCatalogId).toString() : null;
        if (string4 == null || kotlin.text.o.h0(string4)) {
            string4 = null;
        }
        if (string4 == null) {
            string4 = addonSourceRef != null ? (String) addonSourceRef.f22589m : null;
        }
        String addonName = config.getAddonName();
        String string5 = addonName != null ? kotlin.text.o.L0(addonName).toString() : null;
        if (string5 == null || kotlin.text.o.h0(string5)) {
            string5 = null;
        }
        CatalogSourceType sourceTypeCompat = parseSourceTypeCompat(str3, str, str2);
        CatalogKind catalogKind2 = CatalogKind.COLLECTION_RAIL;
        if (catalogKindValueOf == catalogKind2) {
            catalogKind = catalogKind2;
        } else {
            if (!list2.isEmpty()) {
                catalogKindValueOf = CatalogKind.COLLECTION;
            }
            catalogKind = catalogKindValueOf;
        }
        boolean zIsPreinstalled = zIsBundledPreinstalledCatalogId ? true : (catalogKind == CatalogKind.COLLECTION || catalogKind == catalogKind2 || sourceTypeCompat == CatalogSourceType.PREINSTALLED) ? config.isPreinstalled() : false;
        CatalogSourceType catalogSourceType = CatalogSourceType.ADDON;
        if (sourceTypeCompat != catalogSourceType) {
            string3 = null;
        }
        String str4 = sourceTypeCompat == catalogSourceType ? strNormalizeAddonCatalogType : null;
        String str5 = sourceTypeCompat == catalogSourceType ? string4 : null;
        String str6 = sourceTypeCompat == catalogSourceType ? string5 : null;
        String collectionDescription = config.getCollectionDescription();
        String string6 = collectionDescription != null ? kotlin.text.o.L0(collectionDescription).toString() : null;
        String str7 = (string6 == null || kotlin.text.o.h0(string6)) ? null : string6;
        String collectionCoverImageUrl = config.getCollectionCoverImageUrl();
        String string7 = collectionCoverImageUrl != null ? kotlin.text.o.L0(collectionCoverImageUrl).toString() : null;
        String str8 = (string7 == null || kotlin.text.o.h0(string7)) ? null : string7;
        String collectionFocusGifUrl = config.getCollectionFocusGifUrl();
        String string8 = collectionFocusGifUrl != null ? kotlin.text.o.L0(collectionFocusGifUrl).toString() : null;
        String str9 = (string8 == null || kotlin.text.o.h0(string8)) ? null : string8;
        String collectionHeroImageUrl = config.getCollectionHeroImageUrl();
        String string9 = collectionHeroImageUrl != null ? kotlin.text.o.L0(collectionHeroImageUrl).toString() : null;
        String str10 = (string9 == null || kotlin.text.o.h0(string9)) ? null : string9;
        String collectionHeroGifUrl = config.getCollectionHeroGifUrl();
        String string10 = collectionHeroGifUrl != null ? kotlin.text.o.L0(collectionHeroGifUrl).toString() : null;
        String str11 = (string10 == null || kotlin.text.o.h0(string10)) ? null : string10;
        String collectionHeroVideoUrl = config.getCollectionHeroVideoUrl();
        String string11 = collectionHeroVideoUrl != null ? kotlin.text.o.L0(collectionHeroVideoUrl).toString() : null;
        String str12 = (string11 == null || kotlin.text.o.h0(string11)) ? null : string11;
        String collectionClearLogoUrl = config.getCollectionClearLogoUrl();
        String string12 = collectionClearLogoUrl != null ? kotlin.text.o.L0(collectionClearLogoUrl).toString() : null;
        String str13 = (string12 == null || kotlin.text.o.h0(string12)) ? null : string12;
        boolean collectionHideTitle = config.getCollectionHideTitle();
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(kotlin.text.o.L0((String) it.next()).toString());
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (!kotlin.text.o.h0((String) obj)) {
                arrayList2.add(obj);
            }
        }
        return CatalogConfig.copy$default(config, null, null, sourceTypeCompat, str, str2, zIsPreinstalled, string3, str4, str5, str6, catalogKind, null, str7, str8, str9, str10, str11, str12, str13, collectionTileShape, collectionHideTitle, list2, kotlin.collections.x.g0(arrayList2), null, null, 25167875, null);
    }

    private final x6.j0 parseAddonSourceRef(String sourceRef) {
        String string = sourceRef != null ? kotlin.text.o.L0(sourceRef).toString() : null;
        if (string == null) {
            string = "";
        }
        if (!kotlin.text.u.P(string, ADDON_SOURCE_REF_PREFIX, false)) {
            return null;
        }
        List listY0 = kotlin.text.o.y0(kotlin.text.o.r0(string, ADDON_SOURCE_REF_PREFIX), new String[]{"|"}, 0, 6);
        if (listY0.size() != 3) {
            return null;
        }
        String string2 = kotlin.text.o.L0(urlDecode((String) listY0.get(0))).toString();
        String strNormalizeAddonCatalogType = normalizeAddonCatalogType(urlDecode((String) listY0.get(1)));
        String string3 = kotlin.text.o.L0(urlDecode((String) listY0.get(2))).toString();
        if (kotlin.text.o.h0(string2) || strNormalizeAddonCatalogType == null || kotlin.text.o.h0(string3)) {
            return null;
        }
        return new x6.j0(string2, strNormalizeAddonCatalogType, string3);
    }

    private final CatalogKind parseCatalogKindCompat(String raw) {
        String string;
        String upperCase = (raw == null || (string = kotlin.text.o.L0(raw).toString()) == null) ? null : string.toUpperCase(Locale.ROOT);
        return kotlin.jvm.internal.p.a(upperCase, "COLLECTION") ? CatalogKind.COLLECTION : kotlin.jvm.internal.p.a(upperCase, "COLLECTION_RAIL") ? CatalogKind.COLLECTION_RAIL : CatalogKind.STANDARD;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v0 */
    /* JADX WARN: Type inference failed for: r18v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r18v2 */
    /* JADX WARN: Type inference failed for: r33v0 */
    /* JADX WARN: Type inference failed for: r33v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r33v2 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v24 */
    private final List<CatalogConfig> parseCatalogsJson(String json) throws Exception {
        List list;
        char c10;
        CatalogConfig catalogConfigNormalizeCatalogConfig;
        List list2;
        List list3;
        ?? r18;
        String str;
        List arrayList;
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        if (json == null || kotlin.text.o.h0(json)) {
            return zVar;
        }
        try {
            list = (List) this.gson.fromJson(json, this.listType);
        } catch (Exception unused) {
        }
        if (list == null) {
            list = zVar;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            CatalogConfig catalogConfigNormalizeCatalogConfig2 = normalizeCatalogConfig((CatalogConfig) it.next());
            if (catalogConfigNormalizeCatalogConfig2 != null) {
                arrayList2.add(catalogConfigNormalizeCatalogConfig2);
            }
        }
        if (!arrayList2.isEmpty()) {
            return arrayList2;
        }
        try {
            char c11 = 0;
            List<Map> list4 = (List) this.gson.fromJson(json, TypeToken.getParameterized(List.class, TypeToken.getParameterized(Map.class, String.class, Object.class).getType()).getType());
            if (list4 == null) {
                list4 = zVar;
            }
            ArrayList arrayList3 = new ArrayList();
            for (Map map : list4) {
                Object obj = map.get(TtmlNode.ATTR_ID);
                String str2 = obj instanceof String ? (String) obj : null;
                String string = str2 != null ? kotlin.text.o.L0(str2).toString() : null;
                String str3 = "";
                String str4 = string == null ? "" : string;
                Object obj2 = map.get(LinkHeader.Parameters.Title);
                String str5 = obj2 instanceof String ? (String) obj2 : null;
                String string2 = str5 != null ? kotlin.text.o.L0(str5).toString() : null;
                String str6 = string2 == null ? "" : string2;
                if (kotlin.text.o.h0(str4) || kotlin.text.o.h0(str6)) {
                    c10 = c11;
                    catalogConfigNormalizeCatalogConfig = null;
                } else {
                    Object obj3 = map.get("sourceUrl");
                    String str7 = obj3 instanceof String ? (String) obj3 : null;
                    String string3 = str7 != null ? kotlin.text.o.L0(str7).toString() : null;
                    String str8 = (string3 == null || kotlin.text.o.h0(string3)) ? null : string3;
                    Object obj4 = map.get("sourceRef");
                    String str9 = obj4 instanceof String ? (String) obj4 : null;
                    String string4 = str9 != null ? kotlin.text.o.L0(str9).toString() : null;
                    String str10 = (string4 == null || kotlin.text.o.h0(string4)) ? null : string4;
                    String strAsTrimmedString = asTrimmedString(map.get("addonId"));
                    String strAsTrimmedString2 = asTrimmedString(map.get("addonCatalogType"));
                    String strAsTrimmedString3 = asTrimmedString(map.get("addonCatalogId"));
                    String strAsTrimmedString4 = asTrimmedString(map.get("addonName"));
                    CatalogKind catalogKindCompat = parseCatalogKindCompat(asTrimmedString(map.get("kind")));
                    CollectionGroupKind collectionGroupCompat = parseCollectionGroupCompat(asTrimmedString(map.get("collectionGroup")));
                    String strAsTrimmedString5 = asTrimmedString(map.get("collectionDescription"));
                    String strAsTrimmedString6 = asTrimmedString(map.get("collectionCoverImageUrl"));
                    String strAsTrimmedString7 = asTrimmedString(map.get("collectionFocusGifUrl"));
                    String strAsTrimmedString8 = asTrimmedString(map.get("collectionHeroImageUrl"));
                    String strAsTrimmedString9 = asTrimmedString(map.get("collectionHeroGifUrl"));
                    String strAsTrimmedString10 = asTrimmedString(map.get("collectionHeroVideoUrl"));
                    CollectionTileShape collectionTileShapeCompat = parseCollectionTileShapeCompat(asTrimmedString(map.get("collectionTileShape")));
                    Object obj5 = map.get("collectionHideTitle");
                    c10 = c11;
                    Boolean bool = obj5 instanceof Boolean ? (Boolean) obj5 : null;
                    ?? BooleanValue = bool != null ? bool.booleanValue() : c10;
                    try {
                        String json2 = this.gson.toJson(map.get("collectionSources"));
                        Gson gson = this.gson;
                        Type[] typeArr = new Type[1];
                        typeArr[c10] = CollectionSourceConfig.class;
                        List list5 = (List) gson.fromJson(json2, TypeToken.getParameterized(List.class, typeArr).getType());
                        if (list5 == null) {
                            list5 = zVar;
                        }
                        list2 = list5;
                    } catch (JsonSyntaxException unused2) {
                        list2 = zVar;
                    } catch (Exception e5) {
                        if (e5 instanceof CancellationException) {
                            throw e5;
                        }
                        list2 = zVar;
                    }
                    try {
                        String json3 = this.gson.toJson(map.get("requiredAddonUrls"));
                        Gson gson2 = this.gson;
                        Type[] typeArr2 = new Type[1];
                        typeArr2[c10] = String.class;
                        List list6 = (List) gson2.fromJson(json3, TypeToken.getParameterized(List.class, typeArr2).getType());
                        if (list6 != null) {
                            ArrayList arrayList4 = new ArrayList(kotlin.collections.s.U(list6, 10));
                            Iterator it2 = list6.iterator();
                            while (it2.hasNext()) {
                                arrayList4.add(kotlin.text.o.L0((String) it2.next()).toString());
                            }
                            arrayList = new ArrayList();
                            for (Object obj6 : arrayList4) {
                                if (!kotlin.text.o.h0((String) obj6)) {
                                    arrayList.add(obj6);
                                }
                            }
                        } else {
                            arrayList = zVar;
                        }
                        list3 = arrayList;
                    } catch (JsonSyntaxException unused3) {
                        list3 = zVar;
                    } catch (Exception e6) {
                        if (e6 instanceof CancellationException) {
                            throw e6;
                        }
                        list3 = zVar;
                    }
                    Object obj7 = map.get("sourceType");
                    String str11 = obj7 instanceof String ? (String) obj7 : null;
                    String string5 = str11 != null ? kotlin.text.o.L0(str11).toString() : null;
                    if (string5 != null) {
                        str3 = string5;
                    }
                    CatalogSourceType sourceTypeCompat = parseSourceTypeCompat(str3, str8, str10);
                    Object obj8 = map.get("isPreinstalled");
                    Boolean bool2 = obj8 instanceof Boolean ? (Boolean) obj8 : null;
                    ?? BooleanValue2 = bool2 != null ? bool2.booleanValue() : c10;
                    if (str8 == null && sourceTypeCompat == CatalogSourceType.PREINSTALLED) {
                        r18 = BooleanValue2;
                        str = str8;
                    } else {
                        str = str8;
                        r18 = c10;
                    }
                    catalogConfigNormalizeCatalogConfig = normalizeCatalogConfig(new CatalogConfig(str4, str6, sourceTypeCompat, str, str10, r18, strAsTrimmedString, strAsTrimmedString2, strAsTrimmedString3, strAsTrimmedString4, catalogKindCompat, collectionGroupCompat, strAsTrimmedString5, strAsTrimmedString6, strAsTrimmedString7, strAsTrimmedString8, strAsTrimmedString9, strAsTrimmedString10, null, collectionTileShapeCompat, BooleanValue, list2, list3, null, null, 25427968, null));
                }
                if (catalogConfigNormalizeCatalogConfig != null) {
                    arrayList3.add(catalogConfigNormalizeCatalogConfig);
                }
                c11 = c10;
            }
            return arrayList3;
        } catch (Exception unused4) {
            return zVar;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final CollectionGroupKind parseCollectionGroupCompat(String raw) {
        String string;
        String upperCase = (raw == null || (string = kotlin.text.o.L0(raw).toString()) == null) ? null : string.toUpperCase(Locale.ROOT);
        if (upperCase != null) {
            switch (upperCase.hashCode()) {
                case -1733499378:
                    if (upperCase.equals("NETWORK")) {
                        return CollectionGroupKind.NETWORK;
                    }
                    break;
                case -1592831339:
                    if (upperCase.equals("SERVICE")) {
                        return CollectionGroupKind.SERVICE;
                    }
                    break;
                case -935869571:
                    if (upperCase.equals("FRANCHISE")) {
                        return CollectionGroupKind.FRANCHISE;
                    }
                    break;
                case 67703139:
                    if (upperCase.equals("GENRE")) {
                        return CollectionGroupKind.GENRE;
                    }
                    break;
                case 491967534:
                    if (upperCase.equals("FEATURED")) {
                        return CollectionGroupKind.FEATURED;
                    }
                    break;
                case 2012565856:
                    if (upperCase.equals("DECADE")) {
                        return CollectionGroupKind.DECADE;
                    }
                    break;
            }
        }
        return null;
    }

    private final CollectionTileShape parseCollectionTileShapeCompat(String raw) {
        String string;
        return kotlin.jvm.internal.p.a((raw == null || (string = kotlin.text.o.L0(raw).toString()) == null) ? null : string.toUpperCase(Locale.ROOT), "POSTER") ? CollectionTileShape.POSTER : CollectionTileShape.LANDSCAPE;
    }

    private final CatalogSourceType parseSourceTypeCompat(String raw, String sourceUrl, String sourceRef) {
        String upperCase = kotlin.text.o.L0(raw).toString().toUpperCase(Locale.ROOT);
        return (sourceRef == null || !kotlin.text.u.P(sourceRef, HomeServerRepository.CATALOG_SOURCE_REF_PREFIX, true)) ? (sourceRef == null || !kotlin.text.u.P(sourceRef, ADDON_SOURCE_REF_PREFIX, true)) ? (sourceRef == null || !kotlin.text.u.P(sourceRef, "trakt_", true)) ? (sourceRef == null || !kotlin.text.u.P(sourceRef, "mdblist", true)) ? (sourceUrl == null || !kotlin.text.o.T(sourceUrl, "trakt.tv", true)) ? (sourceUrl == null || !kotlin.text.o.T(sourceUrl, "mdblist.com", true)) ? upperCase.equals("TRAKT") ? CatalogSourceType.TRAKT : upperCase.equals("MDBLIST") ? CatalogSourceType.MDBLIST : upperCase.equals("ADDON") ? CatalogSourceType.ADDON : upperCase.equals("HOME_SERVER") ? CatalogSourceType.HOME_SERVER : upperCase.equals("PREINSTALLED") ? CatalogSourceType.PREINSTALLED : (kotlin.text.o.T(upperCase, "HOME_SERVER", false) || kotlin.text.o.T(upperCase, "HOME SERVER", false)) ? CatalogSourceType.HOME_SERVER : kotlin.text.o.T(upperCase, "ADDON", false) ? CatalogSourceType.ADDON : kotlin.text.o.T(upperCase, "TRAKT", false) ? CatalogSourceType.TRAKT : (kotlin.text.o.T(upperCase, "MDB", false) || kotlin.text.o.T(upperCase, "MDL", false)) ? CatalogSourceType.MDBLIST : (sourceUrl == null || kotlin.text.o.h0(sourceUrl)) ? CatalogSourceType.PREINSTALLED : CatalogSourceType.TRAKT : CatalogSourceType.MDBLIST : CatalogSourceType.TRAKT : CatalogSourceType.MDBLIST : CatalogSourceType.TRAKT : CatalogSourceType.ADDON : CatalogSourceType.HOME_SERVER;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object readCatalogsForActiveProfile(d7.d<? super java.util.List<com.arflix.tv.data.model.CatalogConfig>> r10) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.readCatalogsForActiveProfile(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<CatalogConfig> readCatalogsFromPrefs(String profileId, c1.g prefs) throws Exception {
        Set<String> setDecodeHiddenPreinstalled = decodeHiddenPreinstalled(profileId, prefs);
        Set<String> setDecodeHiddenAddon = decodeHiddenAddon(profileId, prefs);
        Set<String> setDecodeHiddenHomeServer = decodeHiddenHomeServer(profileId, prefs);
        c1.b bVar = (c1.b) prefs;
        List<CatalogConfig> catalogsJson = parseCatalogsJson((String) bVar.f7368a.get(catalogsKey(profileId)));
        if (catalogsJson.isEmpty()) {
            c1.e eVar = this.legacyDefaultKey;
            LinkedHashMap linkedHashMap = bVar.f7368a;
            List<CatalogConfig> catalogsJson2 = parseCatalogsJson((String) linkedHashMap.get(eVar));
            if (!catalogsJson2.isEmpty()) {
                HashSet hashSet = new HashSet();
                ArrayList arrayList = new ArrayList();
                for (Object obj : catalogsJson2) {
                    if (hashSet.add(((CatalogConfig) obj).getId())) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(refreshBundledPreinstalledCatalog((CatalogConfig) it.next()));
                }
                ArrayList arrayList3 = new ArrayList();
                for (Object obj2 : arrayList2) {
                    if (!readCatalogsFromPrefs$isHidden((CatalogConfig) obj2, this, setDecodeHiddenPreinstalled, setDecodeHiddenAddon, setDecodeHiddenHomeServer)) {
                        arrayList3.add(obj2);
                    }
                }
                return arrayList3;
            }
            List<CatalogConfig> catalogsJson3 = parseCatalogsJson((String) linkedHashMap.get(this.legacyGlobalKey));
            if (catalogsJson3.isEmpty()) {
                return kotlin.collections.z.f19728i;
            }
            HashSet hashSet2 = new HashSet();
            ArrayList arrayList4 = new ArrayList();
            for (Object obj3 : catalogsJson3) {
                if (hashSet2.add(((CatalogConfig) obj3).getId())) {
                    arrayList4.add(obj3);
                }
            }
            ArrayList arrayList5 = new ArrayList(kotlin.collections.s.U(arrayList4, 10));
            Iterator it2 = arrayList4.iterator();
            while (it2.hasNext()) {
                arrayList5.add(refreshBundledPreinstalledCatalog((CatalogConfig) it2.next()));
            }
            ArrayList arrayList6 = new ArrayList();
            for (Object obj4 : arrayList5) {
                if (!readCatalogsFromPrefs$isHidden((CatalogConfig) obj4, this, setDecodeHiddenPreinstalled, setDecodeHiddenAddon, setDecodeHiddenHomeServer)) {
                    arrayList6.add(obj4);
                }
            }
            return arrayList6;
        }
        HashSet hashSet3 = new HashSet();
        ArrayList arrayList7 = new ArrayList();
        for (Object obj5 : catalogsJson) {
            if (hashSet3.add(((CatalogConfig) obj5).getId())) {
                arrayList7.add(obj5);
            }
        }
        ArrayList arrayList8 = new ArrayList(kotlin.collections.s.U(arrayList7, 10));
        Iterator it3 = arrayList7.iterator();
        while (it3.hasNext()) {
            arrayList8.add(refreshBundledPreinstalledCatalog((CatalogConfig) it3.next()));
        }
        ArrayList arrayList9 = new ArrayList();
        for (Object obj6 : arrayList8) {
            if (!readCatalogsFromPrefs$isHidden((CatalogConfig) obj6, this, setDecodeHiddenPreinstalled, setDecodeHiddenAddon, setDecodeHiddenHomeServer)) {
                arrayList9.add(obj6);
            }
        }
        ArrayList arrayList10 = new ArrayList(arrayList9);
        ArrayList arrayList11 = new ArrayList(kotlin.collections.s.U(arrayList10, 10));
        Iterator it4 = arrayList10.iterator();
        while (true) {
            String str = "";
            if (!it4.hasNext()) {
                break;
            }
            CatalogConfig catalogConfig = (CatalogConfig) it4.next();
            String id = catalogConfig.getId();
            String sourceUrl = catalogConfig.getSourceUrl();
            if (sourceUrl != null) {
                str = sourceUrl;
            }
            arrayList11.add(id + "|" + str);
        }
        Set setF1 = kotlin.collections.x.f1(arrayList11);
        if (kotlin.jvm.internal.p.a(profileId, "default")) {
            c1.e eVar2 = this.legacyDefaultKey;
            LinkedHashMap linkedHashMap2 = bVar.f7368a;
            ArrayList arrayListI0 = kotlin.collections.x.I0(parseCatalogsJson((String) linkedHashMap2.get(eVar2)), parseCatalogsJson((String) linkedHashMap2.get(this.legacyGlobalKey)));
            ArrayList arrayList12 = new ArrayList();
            for (Object obj7 : arrayListI0) {
                if (!((CatalogConfig) obj7).isPreinstalled()) {
                    arrayList12.add(obj7);
                }
            }
            ArrayList arrayList13 = new ArrayList();
            for (Object obj8 : arrayList12) {
                if (!readCatalogsFromPrefs$isHidden((CatalogConfig) obj8, this, setDecodeHiddenPreinstalled, setDecodeHiddenAddon, setDecodeHiddenHomeServer)) {
                    arrayList13.add(obj8);
                }
            }
            HashSet hashSet4 = new HashSet();
            ArrayList<CatalogConfig> arrayList14 = new ArrayList();
            for (Object obj9 : arrayList13) {
                CatalogConfig catalogConfig2 = (CatalogConfig) obj9;
                String id2 = catalogConfig2.getId();
                String sourceUrl2 = catalogConfig2.getSourceUrl();
                if (sourceUrl2 == null) {
                    sourceUrl2 = "";
                }
                if (hashSet4.add(id2 + "|" + sourceUrl2)) {
                    arrayList14.add(obj9);
                }
            }
            for (CatalogConfig catalogConfig3 : arrayList14) {
                String id3 = catalogConfig3.getId();
                String sourceUrl3 = catalogConfig3.getSourceUrl();
                if (sourceUrl3 == null) {
                    sourceUrl3 = "";
                }
                String strT = androidx.compose.foundation.c.t(id3, "|", sourceUrl3);
                if (!setF1.contains(strT)) {
                    arrayList10.add(catalogConfig3);
                    setF1.add(strT);
                }
            }
        }
        return arrayList10;
    }

    private static final boolean readCatalogsFromPrefs$isHidden(CatalogConfig catalogConfig, CatalogRepository catalogRepository, Set<String> set, Set<String> set2, Set<String> set3) {
        if (catalogRepository.isPreinstalledCatalog(catalogConfig) && set.contains(catalogConfig.getId())) {
            return true;
        }
        if (catalogConfig.getSourceType() == CatalogSourceType.ADDON && set2.contains(catalogConfig.getId())) {
            return true;
        }
        return catalogConfig.getSourceType() == CatalogSourceType.HOME_SERVER && set3.contains(catalogConfig.getId());
    }

    private final CatalogConfig refreshBundledPreinstalledCatalog(CatalogConfig config) {
        CatalogConfig catalogConfig = getBundledPreinstalledCatalogsById().get(config.getId());
        return catalogConfig == null ? config : (config.isPreinstalled() || config.getSourceType() == CatalogSourceType.PREINSTALLED || config.getKind() == CatalogKind.COLLECTION || config.getKind() == CatalogKind.COLLECTION_RAIL) ? catalogConfig : config;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeCatalogPack_gIAlu_s$lambda$0(String str, CatalogConfig catalogConfig) {
        return kotlin.jvm.internal.p.a(catalogConfig.getPackId(), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeCustomCatalog_gIAlu_s$lambda$1(String str, CatalogConfig catalogConfig) {
        return kotlin.jvm.internal.p.a(catalogConfig.getId(), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveMdblistMetadata(java.lang.String r8, d7.d<? super com.arflix.tv.data.repository.CatalogRepository.ResolvedCatalog> r9) {
        /*
            Method dump skipped, instruction units count: 241
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.resolveMdblistMetadata(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object resolveMetadata(String str, CatalogSourceType catalogSourceType, d7.d<? super ResolvedCatalog> dVar) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[catalogSourceType.ordinal()];
        if (i10 == 1) {
            return resolveTraktMetadata(str, dVar);
        }
        if (i10 == 2) {
            return resolveMdblistMetadata(str, dVar);
        }
        if (i10 == 3 || i10 == 4 || i10 == 5) {
            return null;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a7 A[Catch: CancellationException -> 0x0057, Exception -> 0x0134, TryCatch #1 {CancellationException -> 0x0057, blocks: (B:21:0x0053, B:31:0x009b, B:33:0x00a7, B:34:0x00b2, B:27:0x006b), top: B:52:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0111 A[Catch: CancellationException -> 0x0040, Exception -> 0x0134, TryCatch #0 {CancellationException -> 0x0040, blocks: (B:14:0x003b, B:43:0x0105, B:45:0x0111, B:46:0x011c, B:39:0x00e0), top: B:52:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveTraktMetadata(java.lang.String r18, d7.d<? super com.arflix.tv.data.repository.CatalogRepository.ResolvedCatalog> r19) {
        /*
            Method dump skipped, instruction units count: 311
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.resolveTraktMetadata(java.lang.String, d7.d):java.lang.Object");
    }

    private final List<CatalogConfig> sanitizeCollectionCatalogs(List<CatalogConfig> catalogs) {
        ArrayList arrayList = new ArrayList();
        for (CatalogConfig catalogConfig : catalogs) {
            if (!CollectionTemplateManifest.INSTANCE.isValidCollectionConfig(catalogConfig)) {
                catalogConfig = null;
            }
            if (catalogConfig != null) {
                arrayList.add(catalogConfig);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object saveCatalogs(java.util.List<com.arflix.tv.data.model.CatalogConfig> r8, d7.d<? super x6.t0> r9) throws java.lang.Exception {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.arflix.tv.data.repository.CatalogRepository.C07131
            if (r0 == 0) goto L13
            r0 = r9
            com.arflix.tv.data.repository.CatalogRepository$saveCatalogs$1 r0 = (com.arflix.tv.data.repository.CatalogRepository.C07131) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$saveCatalogs$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$saveCatalogs$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L47
            if (r1 == r3) goto L3f
            if (r1 != r2) goto L37
            java.lang.Object r8 = r0.L$2
            java.util.List r8 = (java.util.List) r8
            java.lang.Object r8 = r0.L$1
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r0 = r0.L$0
            java.util.List r0 = (java.util.List) r0
            k2.c.G(r9)
            goto Lbb
        L37:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3f:
            java.lang.Object r8 = r0.L$0
            java.util.List r8 = (java.util.List) r8
            k2.c.G(r9)
            goto L55
        L47:
            k2.c.G(r9)
            r0.L$0 = r8
            r0.label = r3
            java.lang.Object r9 = r7.activeProfileId(r0)
            if (r9 != r4) goto L55
            goto Lb9
        L55:
            java.lang.String r9 = (java.lang.String) r9
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r8 = r8.iterator()
        L65:
            boolean r5 = r8.hasNext()
            if (r5 == 0) goto L80
            java.lang.Object r5 = r8.next()
            r6 = r5
            com.arflix.tv.data.model.CatalogConfig r6 = (com.arflix.tv.data.model.CatalogConfig) r6
            java.lang.String r6 = r6.getId()
            boolean r6 = r1.add(r6)
            if (r6 == 0) goto L65
            r3.add(r5)
            goto L65
        L80:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.Iterator r1 = r3.iterator()
        L89:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L9f
            java.lang.Object r3 = r1.next()
            com.arflix.tv.data.model.CatalogConfig r3 = (com.arflix.tv.data.model.CatalogConfig) r3
            com.arflix.tv.data.model.CatalogConfig r3 = r7.normalizeCatalogConfig(r3)
            if (r3 == 0) goto L89
            r8.add(r3)
            goto L89
        L9f:
            android.content.Context r1 = r7.context
            z0.g r1 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r1)
            com.arflix.tv.data.repository.CatalogRepository$saveCatalogs$2 r3 = new com.arflix.tv.data.repository.CatalogRepository$saveCatalogs$2
            r5 = 0
            r3.<init>(r9, r8, r5)
            r0.L$0 = r5
            r0.L$1 = r9
            r0.L$2 = r5
            r0.label = r2
            java.lang.Object r8 = androidx.work.impl.t.o(r1, r3, r0)
            if (r8 != r4) goto Lba
        Lb9:
            return r4
        Lba:
            r8 = r9
        Lbb:
            com.arflix.tv.data.repository.CloudSyncInvalidationBus r9 = r7.invalidationBus
            com.arflix.tv.data.repository.CloudSyncScope r0 = com.arflix.tv.data.repository.CloudSyncScope.CATALOGS
            java.lang.String r1 = "save catalogs"
            r9.markDirty(r0, r8, r1)
            x6.t0 r8 = x6.t0.f22605a
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.saveCatalogs(java.util.List, d7.d):java.lang.Object");
    }

    private final String sha256Short(String value) {
        return kotlin.collections.x.u0(kotlin.collections.r.l0(8, MessageDigest.getInstance("SHA-256").digest(value.getBytes(kotlin.text.a.f19924a))), "", null, null, new f(19), 30);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence sha256Short$lambda$0(byte b10) {
        return String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b10)}, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean syncAddonCatalogs$lambda$1(Addon addon) {
        String url;
        if (!addon.isInstalled() || !addon.isEnabled() || addon.getType() == AddonType.SUBTITLE || SportsAddonCapabilities.INSTANCE.isSportsLiveTvAddon(addon) || (url = addon.getUrl()) == null || kotlin.text.o.h0(url)) {
            return false;
        }
        AddonManifest manifest = addon.getManifest();
        List<AddonCatalog> catalogs = manifest != null ? manifest.getCatalogs() : null;
        return (catalogs == null || catalogs.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ga.m syncAddonCatalogs$lambda$2(CatalogRepository catalogRepository, Addon addon) {
        AddonManifest manifest = addon.getManifest();
        List<AddonCatalog> catalogs = manifest != null ? manifest.getCatalogs() : null;
        if (catalogs == null) {
            catalogs = kotlin.collections.z.f19728i;
        }
        return ga.r.Q(new ga.p(catalogs, 3), new a(catalogRepository, addon, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean syncAddonCatalogs$lambda$3(Set set, CatalogConfig catalogConfig) {
        return set.contains(catalogConfig.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean syncAddonCatalogs$lambda$6(Map map, CatalogConfig catalogConfig) {
        return catalogConfig.getSourceType() == CatalogSourceType.ADDON && !map.containsKey(catalogConfig.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean syncHomeServerCatalogs$lambda$8(Map map, CatalogConfig catalogConfig) {
        return catalogConfig.getSourceType() == CatalogSourceType.HOME_SERVER && !map.containsKey(catalogConfig.getId());
    }

    private final String urlDecode(String value) {
        return URLDecoder.decode(value, "UTF-8");
    }

    private final String urlEncode(String value) {
        return URLEncoder.encode(value, "UTF-8");
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x00e7, code lost:
    
        if (r2 == r11) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01fb, code lost:
    
        if (r9 == r11) goto L103;
     */
    /* JADX WARN: Path cross not found for [B:53:0x0186, B:54:0x0189], limit reached: 101 */
    /* JADX WARN: Path cross not found for [B:93:0x023b, B:87:0x022b], limit reached: 101 */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0284  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x0186 -> B:96:0x0281). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:71:0x01cc -> B:96:0x0281). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:76:0x01fb -> B:19:0x00b0). Please report as a decompilation issue!!! */
    /* JADX INFO: renamed from: addCatalogPack-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6114addCatalogPack0E7RQCE(java.lang.String r46, com.arflix.tv.data.model.CatalogPackManifest r47, d7.d<? super x6.d0> r48) {
        /*
            Method dump skipped, instruction units count: 711
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.m6114addCatalogPack0E7RQCE(java.lang.String, com.arflix.tv.data.model.CatalogPackManifest, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x018f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX INFO: renamed from: addCustomCatalog-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6115addCustomCataloggIAlus(java.lang.String r39, d7.d<? super x6.d0> r40) {
        /*
            Method dump skipped, instruction units count: 419
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.m6115addCustomCataloggIAlus(java.lang.String, d7.d):java.lang.Object");
    }

    public final Object ensurePreinstalled(List<Category> list, d7.d<? super List<CatalogConfig>> dVar) {
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
        for (Category category : list) {
            arrayList.add(new CatalogConfig(category.getId(), category.getTitle(), CatalogSourceType.PREINSTALLED, null, null, true, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, 33554392, null));
        }
        return ensurePreinstalledDefaults(arrayList, dVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:140:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x010e A[LOOP:8: B:38:0x0108->B:40:0x010e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x020f A[LOOP:1: B:75:0x0209->B:77:0x020f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0226  */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r42v1 */
    /* JADX WARN: Type inference failed for: r43v0, types: [com.arflix.tv.data.repository.CatalogRepository] */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.lang.Iterable, java.util.List] */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object, java.util.ArrayList] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object ensurePreinstalledDefaults(java.util.List<com.arflix.tv.data.model.CatalogConfig> r44, d7.d<? super java.util.List<com.arflix.tv.data.model.CatalogConfig>> r45) {
        /*
            Method dump skipped, instruction units count: 935
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.ensurePreinstalledDefaults(java.util.List, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: fetchCatalogPackManifest-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6116fetchCatalogPackManifestgIAlus(java.lang.String r5, d7.d<? super x6.d0> r6) {
        /*
            Method dump skipped, instruction units count: 321
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.m6116fetchCatalogPackManifestgIAlus(java.lang.String, d7.d):java.lang.Object");
    }

    public final Object getCatalogs(d7.d<? super List<CatalogConfig>> dVar) {
        return readCatalogsForActiveProfile(dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getCatalogsForProfile(java.lang.String r5, d7.d<? super java.util.List<com.arflix.tv.data.model.CatalogConfig>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.CatalogRepository.C06931
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.CatalogRepository$getCatalogsForProfile$1 r0 = (com.arflix.tv.data.repository.CatalogRepository.C06931) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$getCatalogsForProfile$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$getCatalogsForProfile$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r5 = r0.L$1
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            k2.c.G(r6)
            goto L5b
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            java.lang.String r5 = androidx.fragment.app.a2.l(r6, r5)
            boolean r6 = kotlin.text.o.h0(r5)
            if (r6 == 0) goto L41
            java.lang.String r5 = "default"
        L41:
            android.content.Context r6 = r4.context
            z0.g r6 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r6)
            na.j r6 = r6.getData()
            r1 = 0
            r0.L$0 = r1
            r0.L$1 = r5
            r0.label = r2
            java.lang.Object r6 = na.y0.l(r6, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L5b
            return r0
        L5b:
            c1.g r6 = (c1.g) r6
            java.util.List r5 = r4.readCatalogsFromPrefs(r5, r6)
            java.util.List r5 = r4.sanitizeCollectionCatalogs(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.getCatalogsForProfile(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getHiddenAddonCatalogIdsForActiveProfile(d7.d<? super java.util.List<java.lang.String>> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.CatalogRepository.C06941
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.CatalogRepository$getHiddenAddonCatalogIdsForActiveProfile$1 r0 = (com.arflix.tv.data.repository.CatalogRepository.C06941) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$getHiddenAddonCatalogIdsForActiveProfile$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$getHiddenAddonCatalogIdsForActiveProfile$1
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
            goto L60
        L2e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L36:
            k2.c.G(r7)
            goto L46
        L3a:
            k2.c.G(r7)
            r0.label = r3
            java.lang.Object r7 = r6.activeProfileId(r0)
            if (r7 != r4) goto L46
            goto L5c
        L46:
            java.lang.String r7 = (java.lang.String) r7
            android.content.Context r1 = r6.context
            z0.g r1 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r1)
            na.j r1 = r1.getData()
            r0.L$0 = r7
            r0.label = r2
            java.lang.Object r0 = na.y0.l(r1, r0)
            if (r0 != r4) goto L5d
        L5c:
            return r4
        L5d:
            r5 = r0
            r0 = r7
            r7 = r5
        L60:
            c1.g r7 = (c1.g) r7
            java.util.Set r7 = r6.decodeHiddenAddon(r0, r7)
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.List r7 = kotlin.collections.x.c1(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.getHiddenAddonCatalogIdsForActiveProfile(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getHiddenAddonCatalogIdsForProfile(java.lang.String r5, d7.d<? super java.util.List<java.lang.String>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.CatalogRepository.C06951
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.CatalogRepository$getHiddenAddonCatalogIdsForProfile$1 r0 = (com.arflix.tv.data.repository.CatalogRepository.C06951) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$getHiddenAddonCatalogIdsForProfile$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$getHiddenAddonCatalogIdsForProfile$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r5 = r0.L$1
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            k2.c.G(r6)
            goto L5b
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            java.lang.String r5 = androidx.fragment.app.a2.l(r6, r5)
            boolean r6 = kotlin.text.o.h0(r5)
            if (r6 == 0) goto L41
            java.lang.String r5 = "default"
        L41:
            android.content.Context r6 = r4.context
            z0.g r6 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r6)
            na.j r6 = r6.getData()
            r1 = 0
            r0.L$0 = r1
            r0.L$1 = r5
            r0.label = r2
            java.lang.Object r6 = na.y0.l(r6, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L5b
            return r0
        L5b:
            c1.g r6 = (c1.g) r6
            java.util.Set r5 = r4.decodeHiddenAddon(r5, r6)
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.List r5 = kotlin.collections.x.c1(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.getHiddenAddonCatalogIdsForProfile(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getHiddenHomeServerCatalogIdsForProfile(java.lang.String r5, d7.d<? super java.util.List<java.lang.String>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.CatalogRepository.C06961
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.CatalogRepository$getHiddenHomeServerCatalogIdsForProfile$1 r0 = (com.arflix.tv.data.repository.CatalogRepository.C06961) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$getHiddenHomeServerCatalogIdsForProfile$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$getHiddenHomeServerCatalogIdsForProfile$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r5 = r0.L$1
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            k2.c.G(r6)
            goto L5b
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            java.lang.String r5 = androidx.fragment.app.a2.l(r6, r5)
            boolean r6 = kotlin.text.o.h0(r5)
            if (r6 == 0) goto L41
            java.lang.String r5 = "default"
        L41:
            android.content.Context r6 = r4.context
            z0.g r6 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r6)
            na.j r6 = r6.getData()
            r1 = 0
            r0.L$0 = r1
            r0.L$1 = r5
            r0.label = r2
            java.lang.Object r6 = na.y0.l(r6, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L5b
            return r0
        L5b:
            c1.g r6 = (c1.g) r6
            java.util.Set r5 = r4.decodeHiddenHomeServer(r5, r6)
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.List r5 = kotlin.collections.x.c1(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.getHiddenHomeServerCatalogIdsForProfile(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getHiddenPreinstalledCatalogIdsForActiveProfile(d7.d<? super java.util.List<java.lang.String>> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.CatalogRepository.C06971
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.CatalogRepository$getHiddenPreinstalledCatalogIdsForActiveProfile$1 r0 = (com.arflix.tv.data.repository.CatalogRepository.C06971) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$getHiddenPreinstalledCatalogIdsForActiveProfile$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$getHiddenPreinstalledCatalogIdsForActiveProfile$1
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
            goto L60
        L2e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L36:
            k2.c.G(r7)
            goto L46
        L3a:
            k2.c.G(r7)
            r0.label = r3
            java.lang.Object r7 = r6.activeProfileId(r0)
            if (r7 != r4) goto L46
            goto L5c
        L46:
            java.lang.String r7 = (java.lang.String) r7
            android.content.Context r1 = r6.context
            z0.g r1 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r1)
            na.j r1 = r1.getData()
            r0.L$0 = r7
            r0.label = r2
            java.lang.Object r0 = na.y0.l(r1, r0)
            if (r0 != r4) goto L5d
        L5c:
            return r4
        L5d:
            r5 = r0
            r0 = r7
            r7 = r5
        L60:
            c1.g r7 = (c1.g) r7
            java.util.Set r7 = r6.decodeHiddenPreinstalled(r0, r7)
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.List r7 = kotlin.collections.x.c1(r7)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.getHiddenPreinstalledCatalogIdsForActiveProfile(d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getHiddenPreinstalledCatalogIdsForProfile(java.lang.String r5, d7.d<? super java.util.List<java.lang.String>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.data.repository.CatalogRepository.C06981
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.data.repository.CatalogRepository$getHiddenPreinstalledCatalogIdsForProfile$1 r0 = (com.arflix.tv.data.repository.CatalogRepository.C06981) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$getHiddenPreinstalledCatalogIdsForProfile$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$getHiddenPreinstalledCatalogIdsForProfile$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r5 = r0.L$1
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            k2.c.G(r6)
            goto L5b
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            java.lang.String r5 = androidx.fragment.app.a2.l(r6, r5)
            boolean r6 = kotlin.text.o.h0(r5)
            if (r6 == 0) goto L41
            java.lang.String r5 = "default"
        L41:
            android.content.Context r6 = r4.context
            z0.g r6 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r6)
            na.j r6 = r6.getData()
            r1 = 0
            r0.L$0 = r1
            r0.L$1 = r5
            r0.label = r2
            java.lang.Object r6 = na.y0.l(r6, r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L5b
            return r0
        L5b:
            c1.g r6 = (c1.g) r6
            java.util.Set r5 = r4.decodeHiddenPreinstalled(r5, r6)
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.List r5 = kotlin.collections.x.c1(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.getHiddenPreinstalledCatalogIdsForProfile(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x0131, code lost:
    
        if (saveCatalogs(r1, r0) == r4) goto L65;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object moveCatalogDown(java.lang.String r11, d7.d<? super java.lang.Boolean> r12) {
        /*
            Method dump skipped, instruction units count: 317
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.moveCatalogDown(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x0129, code lost:
    
        if (saveCatalogs(r1, r0) == r4) goto L63;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object moveCatalogUp(java.lang.String r11, d7.d<? super java.lang.Boolean> r12) {
        /*
            Method dump skipped, instruction units count: 306
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.moveCatalogUp(java.lang.String, d7.d):java.lang.Object");
    }

    public final na.j<List<CatalogConfig>> observeCatalogs() {
        return y0.h(y0.p(this.profileManager.getActiveProfileId(), new CatalogRepository$observeCatalogs$$inlined$flatMapLatest$1(null, this)));
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0088, code lost:
    
        if (saveCatalogs(r1, r0) == r4) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: removeCatalogPack-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6117removeCatalogPackgIAlus(java.lang.String r7, d7.d<? super x6.d0> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.CatalogRepository$removeCatalogPack$1
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.CatalogRepository$removeCatalogPack$1 r0 = (com.arflix.tv.data.repository.CatalogRepository$removeCatalogPack$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$removeCatalogPack$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$removeCatalogPack$1
            r0.<init>(r6, r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L42
            if (r1 == r3) goto L3a
            if (r1 != r2) goto L32
            java.lang.Object r7 = r0.L$1
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            k2.c.G(r8)
            goto L8b
        L32:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3a:
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            k2.c.G(r8)
            goto L50
        L42:
            k2.c.G(r8)
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r6.getCatalogs(r0)
            if (r8 != r4) goto L50
            goto L8a
        L50:
            java.util.Collection r8 = (java.util.Collection) r8
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>(r8)
            int r8 = r1.size()
            com.arflix.tv.data.repository.x r3 = new com.arflix.tv.data.repository.x
            r5 = 3
            r3.<init>(r7, r5)
            kotlin.collections.x.M0(r1, r3)
            int r3 = r1.size()
            if (r3 != r8) goto L7b
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "No catalogs found for pack: "
            java.lang.String r7 = androidx.compose.material3.d.C(r0, r7)
            r8.<init>(r7)
            x6.c0 r7 = new x6.c0
            r7.<init>(r8)
            return r7
        L7b:
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.I$0 = r8
            r0.label = r2
            java.lang.Object r7 = r6.saveCatalogs(r1, r0)
            if (r7 != r4) goto L8b
        L8a:
            return r4
        L8b:
            x6.t0 r7 = x6.t0.f22605a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.m6117removeCatalogPackgIAlus(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00e5, code lost:
    
        if (hidePreinstalledCatalog(r10, r4, r0) == r3) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00e8, code lost:
    
        r9 = r1;
        r1 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0104, code lost:
    
        if (hideAddonCatalog(r10, r4, r0) == r3) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x011e, code lost:
    
        if (hideHomeServerCatalog(r10, r4, r0) == r3) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0139, code lost:
    
        if (saveCatalogs(r1, r0) != r3) goto L54;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: removeCustomCatalog-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6118removeCustomCataloggIAlus(java.lang.String r9, d7.d<? super x6.d0> r10) {
        /*
            Method dump skipped, instruction units count: 338
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.m6118removeCustomCataloggIAlus(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x00f8, code lost:
    
        if (saveCatalogs(r3, r2) == r7) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object renameCatalog(java.lang.String r37, java.lang.String r38, d7.d<? super java.lang.Boolean> r39) {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.renameCatalog(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    public final Object replaceCatalogsForActiveProfile(List<CatalogConfig> list, d7.d<? super t0> dVar) throws Exception {
        String sourceUrl;
        String sourceRef;
        HashSet hashSet = new HashSet();
        ArrayList<CatalogConfig> arrayList = new ArrayList();
        for (Object obj : list) {
            if (hashSet.add(((CatalogConfig) obj).getId())) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        for (CatalogConfig catalogConfigCopy$default : arrayList) {
            if (kotlin.text.u.P(catalogConfigCopy$default.getId(), ContentMetadata.KEY_CUSTOM_PREFIX, false) || catalogConfigCopy$default.getSourceType() == CatalogSourceType.ADDON || catalogConfigCopy$default.getSourceType() == CatalogSourceType.HOME_SERVER || (((sourceUrl = catalogConfigCopy$default.getSourceUrl()) != null && !kotlin.text.o.h0(sourceUrl)) || ((sourceRef = catalogConfigCopy$default.getSourceRef()) != null && !kotlin.text.o.h0(sourceRef)))) {
                catalogConfigCopy$default = CatalogConfig.copy$default(catalogConfigCopy$default, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, 33554399, null);
            }
            arrayList2.add(catalogConfigCopy$default);
        }
        Object objSaveCatalogs = saveCatalogs(arrayList2, dVar);
        return objSaveCatalogs == e7.a.f15033i ? objSaveCatalogs : t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object replaceCatalogsForProfile(java.lang.String r6, java.util.List<com.arflix.tv.data.model.CatalogConfig> r7, d7.d<? super x6.t0> r8) throws java.lang.Exception {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.arflix.tv.data.repository.CatalogRepository.C07091
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.data.repository.CatalogRepository$replaceCatalogsForProfile$1 r0 = (com.arflix.tv.data.repository.CatalogRepository.C07091) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$replaceCatalogsForProfile$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$replaceCatalogsForProfile$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3e
            if (r1 != r2) goto L36
            java.lang.Object r6 = r0.L$3
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r6 = r0.L$2
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r0.L$1
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r7 = r0.L$0
            java.lang.String r7 = (java.lang.String) r7
            k2.c.G(r8)
            goto Lb1
        L36:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3e:
            java.lang.String r6 = androidx.fragment.app.a2.l(r8, r6)
            boolean r8 = kotlin.text.o.h0(r6)
            if (r8 == 0) goto L4a
            java.lang.String r6 = "default"
        L4a:
            java.util.HashSet r8 = new java.util.HashSet
            r8.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Iterator r7 = r7.iterator()
        L58:
            boolean r3 = r7.hasNext()
            if (r3 == 0) goto L73
            java.lang.Object r3 = r7.next()
            r4 = r3
            com.arflix.tv.data.model.CatalogConfig r4 = (com.arflix.tv.data.model.CatalogConfig) r4
            java.lang.String r4 = r4.getId()
            boolean r4 = r8.add(r4)
            if (r4 == 0) goto L58
            r1.add(r3)
            goto L58
        L73:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r8 = r1.iterator()
        L7c:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L92
            java.lang.Object r1 = r8.next()
            com.arflix.tv.data.model.CatalogConfig r1 = (com.arflix.tv.data.model.CatalogConfig) r1
            com.arflix.tv.data.model.CatalogConfig r1 = r5.normalizeCatalogConfig(r1)
            if (r1 == 0) goto L7c
            r7.add(r1)
            goto L7c
        L92:
            android.content.Context r8 = r5.context
            z0.g r8 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r8)
            com.arflix.tv.data.repository.CatalogRepository$replaceCatalogsForProfile$2 r1 = new com.arflix.tv.data.repository.CatalogRepository$replaceCatalogsForProfile$2
            r3 = 0
            r1.<init>(r6, r7, r3)
            r0.L$0 = r3
            r0.L$1 = r3
            r0.L$2 = r6
            r0.L$3 = r3
            r0.label = r2
            java.lang.Object r7 = androidx.work.impl.t.o(r8, r1, r0)
            e7.a r8 = e7.a.f15033i
            if (r7 != r8) goto Lb1
            return r8
        Lb1:
            com.arflix.tv.data.repository.CloudSyncInvalidationBus r7 = r5.invalidationBus
            com.arflix.tv.data.repository.CloudSyncScope r8 = com.arflix.tv.data.repository.CloudSyncScope.CATALOGS
            java.lang.String r0 = "replace catalogs"
            r7.markDirty(r8, r6, r0)
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.replaceCatalogsForProfile(java.lang.String, java.util.List, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object setHiddenAddonCatalogIdsForActiveProfile(java.util.List<java.lang.String> r7, d7.d<? super x6.t0> r8) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.setHiddenAddonCatalogIdsForActiveProfile(java.util.List, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object setHiddenAddonCatalogIdsForProfile(java.lang.String r5, java.util.List<java.lang.String> r6, d7.d<? super x6.t0> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.CatalogRepository.C07171
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.CatalogRepository$setHiddenAddonCatalogIdsForProfile$1 r0 = (com.arflix.tv.data.repository.CatalogRepository.C07171) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$setHiddenAddonCatalogIdsForProfile$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$setHiddenAddonCatalogIdsForProfile$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3e
            if (r1 != r2) goto L36
            java.lang.Object r5 = r0.L$3
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r5 = r0.L$2
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r6 = r0.L$1
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r6 = r0.L$0
            java.lang.String r6 = (java.lang.String) r6
            k2.c.G(r7)
            goto Lb4
        L36:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3e:
            java.lang.String r5 = androidx.fragment.app.a2.l(r7, r5)
            boolean r7 = kotlin.text.o.h0(r5)
            if (r7 == 0) goto L4a
            java.lang.String r5 = "default"
        L4a:
            java.util.ArrayList r7 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.s.U(r6, r1)
            r7.<init>(r1)
            java.util.Iterator r6 = r6.iterator()
        L59:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L71
            java.lang.Object r1 = r6.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.CharSequence r1 = kotlin.text.o.L0(r1)
            java.lang.String r1 = r1.toString()
            r7.add(r1)
            goto L59
        L71:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r7 = r7.iterator()
        L7a:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L91
            java.lang.Object r1 = r7.next()
            r3 = r1
            java.lang.String r3 = (java.lang.String) r3
            boolean r3 = kotlin.text.o.h0(r3)
            if (r3 != 0) goto L7a
            r6.add(r1)
            goto L7a
        L91:
            java.util.List r6 = kotlin.collections.x.g0(r6)
            android.content.Context r7 = r4.context
            z0.g r7 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r7)
            com.arflix.tv.data.repository.CatalogRepository$setHiddenAddonCatalogIdsForProfile$2 r1 = new com.arflix.tv.data.repository.CatalogRepository$setHiddenAddonCatalogIdsForProfile$2
            r3 = 0
            r1.<init>(r6, r4, r5, r3)
            r0.L$0 = r3
            r0.L$1 = r3
            r0.L$2 = r5
            r0.L$3 = r3
            r0.label = r2
            java.lang.Object r6 = androidx.work.impl.t.o(r7, r1, r0)
            e7.a r7 = e7.a.f15033i
            if (r6 != r7) goto Lb4
            return r7
        Lb4:
            com.arflix.tv.data.repository.CloudSyncInvalidationBus r6 = r4.invalidationBus
            com.arflix.tv.data.repository.CloudSyncScope r7 = com.arflix.tv.data.repository.CloudSyncScope.CATALOGS
            java.lang.String r0 = "set hidden addon catalogs"
            r6.markDirty(r7, r5, r0)
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.setHiddenAddonCatalogIdsForProfile(java.lang.String, java.util.List, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object setHiddenHomeServerCatalogIdsForProfile(java.lang.String r5, java.util.List<java.lang.String> r6, d7.d<? super x6.t0> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.CatalogRepository.C07191
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.CatalogRepository$setHiddenHomeServerCatalogIdsForProfile$1 r0 = (com.arflix.tv.data.repository.CatalogRepository.C07191) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$setHiddenHomeServerCatalogIdsForProfile$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$setHiddenHomeServerCatalogIdsForProfile$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3e
            if (r1 != r2) goto L36
            java.lang.Object r5 = r0.L$3
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r5 = r0.L$2
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r6 = r0.L$1
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r6 = r0.L$0
            java.lang.String r6 = (java.lang.String) r6
            k2.c.G(r7)
            goto Lb4
        L36:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3e:
            java.lang.String r5 = androidx.fragment.app.a2.l(r7, r5)
            boolean r7 = kotlin.text.o.h0(r5)
            if (r7 == 0) goto L4a
            java.lang.String r5 = "default"
        L4a:
            java.util.ArrayList r7 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.s.U(r6, r1)
            r7.<init>(r1)
            java.util.Iterator r6 = r6.iterator()
        L59:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L71
            java.lang.Object r1 = r6.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.CharSequence r1 = kotlin.text.o.L0(r1)
            java.lang.String r1 = r1.toString()
            r7.add(r1)
            goto L59
        L71:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r7 = r7.iterator()
        L7a:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L91
            java.lang.Object r1 = r7.next()
            r3 = r1
            java.lang.String r3 = (java.lang.String) r3
            boolean r3 = kotlin.text.o.h0(r3)
            if (r3 != 0) goto L7a
            r6.add(r1)
            goto L7a
        L91:
            java.util.List r6 = kotlin.collections.x.g0(r6)
            android.content.Context r7 = r4.context
            z0.g r7 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r7)
            com.arflix.tv.data.repository.CatalogRepository$setHiddenHomeServerCatalogIdsForProfile$2 r1 = new com.arflix.tv.data.repository.CatalogRepository$setHiddenHomeServerCatalogIdsForProfile$2
            r3 = 0
            r1.<init>(r6, r4, r5, r3)
            r0.L$0 = r3
            r0.L$1 = r3
            r0.L$2 = r5
            r0.L$3 = r3
            r0.label = r2
            java.lang.Object r6 = androidx.work.impl.t.o(r7, r1, r0)
            e7.a r7 = e7.a.f15033i
            if (r6 != r7) goto Lb4
            return r7
        Lb4:
            com.arflix.tv.data.repository.CloudSyncInvalidationBus r6 = r4.invalidationBus
            com.arflix.tv.data.repository.CloudSyncScope r7 = com.arflix.tv.data.repository.CloudSyncScope.CATALOGS
            java.lang.String r0 = "set hidden home server catalogs"
            r6.markDirty(r7, r5, r0)
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.setHiddenHomeServerCatalogIdsForProfile(java.lang.String, java.util.List, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object setHiddenPreinstalledCatalogIdsForActiveProfile(java.util.List<java.lang.String> r7, d7.d<? super x6.t0> r8) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.setHiddenPreinstalledCatalogIdsForActiveProfile(java.util.List, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object setHiddenPreinstalledCatalogIdsForProfile(java.lang.String r5, java.util.List<java.lang.String> r6, d7.d<? super x6.t0> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.arflix.tv.data.repository.CatalogRepository.C07231
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.data.repository.CatalogRepository$setHiddenPreinstalledCatalogIdsForProfile$1 r0 = (com.arflix.tv.data.repository.CatalogRepository.C07231) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.data.repository.CatalogRepository$setHiddenPreinstalledCatalogIdsForProfile$1 r0 = new com.arflix.tv.data.repository.CatalogRepository$setHiddenPreinstalledCatalogIdsForProfile$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L3e
            if (r1 != r2) goto L36
            java.lang.Object r5 = r0.L$3
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r5 = r0.L$2
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r6 = r0.L$1
            java.util.List r6 = (java.util.List) r6
            java.lang.Object r6 = r0.L$0
            java.lang.String r6 = (java.lang.String) r6
            k2.c.G(r7)
            goto Lb4
        L36:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L3e:
            java.lang.String r5 = androidx.fragment.app.a2.l(r7, r5)
            boolean r7 = kotlin.text.o.h0(r5)
            if (r7 == 0) goto L4a
            java.lang.String r5 = "default"
        L4a:
            java.util.ArrayList r7 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.s.U(r6, r1)
            r7.<init>(r1)
            java.util.Iterator r6 = r6.iterator()
        L59:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L71
            java.lang.Object r1 = r6.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.CharSequence r1 = kotlin.text.o.L0(r1)
            java.lang.String r1 = r1.toString()
            r7.add(r1)
            goto L59
        L71:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r7 = r7.iterator()
        L7a:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L91
            java.lang.Object r1 = r7.next()
            r3 = r1
            java.lang.String r3 = (java.lang.String) r3
            boolean r3 = kotlin.text.o.h0(r3)
            if (r3 != 0) goto L7a
            r6.add(r1)
            goto L7a
        L91:
            java.util.List r6 = kotlin.collections.x.g0(r6)
            android.content.Context r7 = r4.context
            z0.g r7 = com.arflix.tv.util.DataStoresKt.getSettingsDataStore(r7)
            com.arflix.tv.data.repository.CatalogRepository$setHiddenPreinstalledCatalogIdsForProfile$2 r1 = new com.arflix.tv.data.repository.CatalogRepository$setHiddenPreinstalledCatalogIdsForProfile$2
            r3 = 0
            r1.<init>(r6, r4, r5, r3)
            r0.L$0 = r3
            r0.L$1 = r3
            r0.L$2 = r5
            r0.L$3 = r3
            r0.label = r2
            java.lang.Object r6 = androidx.work.impl.t.o(r7, r1, r0)
            e7.a r7 = e7.a.f15033i
            if (r6 != r7) goto Lb4
            return r7
        Lb4:
            com.arflix.tv.data.repository.CloudSyncInvalidationBus r6 = r4.invalidationBus
            com.arflix.tv.data.repository.CloudSyncScope r7 = com.arflix.tv.data.repository.CloudSyncScope.CATALOGS
            java.lang.String r0 = "set hidden preinstalled catalogs"
            r6.markDirty(r7, r5, r0)
            x6.t0 r5 = x6.t0.f22605a
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.setHiddenPreinstalledCatalogIdsForProfile(java.lang.String, java.util.List, d7.d):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x015e A[PHI: r1 r4
      0x015e: PHI (r1v18 java.lang.Object) = (r1v15 java.lang.Object), (r1v1 java.lang.Object) binds: [B:43:0x015a, B:17:0x0066] A[DONT_GENERATE, DONT_INLINE]
      0x015e: PHI (r4v13 ??) = (r4v36 ??), (r4v37 ??) binds: [B:43:0x015a, B:17:0x0066] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0183 A[LOOP:0: B:49:0x017d->B:51:0x0183, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0236 A[LOOP:2: B:65:0x0230->B:67:0x0236, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Type inference failed for: r44v0, types: [com.arflix.tv.data.repository.CatalogRepository, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v10, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r4v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v37 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object syncAddonCatalogs(java.util.List<com.arflix.tv.data.model.Addon> r45, d7.d<? super java.lang.Boolean> r46) {
        /*
            Method dump skipped, instruction units count: 676
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.syncAddonCatalogs(java.util.List, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x02ed  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0355 A[LOOP:1: B:106:0x034f->B:108:0x0355, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x037c A[LOOP:2: B:110:0x0376->B:112:0x037c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0115 A[LOOP:5: B:36:0x010f->B:38:0x0115, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0192 A[LOOP:8: B:52:0x018c->B:54:0x0192, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0204 A[LOOP:9: B:59:0x01fe->B:61:0x0204, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0235 A[LOOP:10: B:67:0x022f->B:69:0x0235, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0291  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object syncHomeServerCatalogs(java.util.List<com.arflix.tv.data.repository.HomeServerCatalogCandidate> r46, d7.d<? super java.lang.Boolean> r47) {
        /*
            Method dump skipped, instruction units count: 1000
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.syncHomeServerCatalogs(java.util.List, d7.d):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x01f5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX INFO: renamed from: updateCustomCatalog-0E7RQCE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6119updateCustomCatalog0E7RQCE(java.lang.String r39, java.lang.String r40, d7.d<? super x6.d0> r41) {
        /*
            Method dump skipped, instruction units count: 521
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.CatalogRepository.m6119updateCustomCatalog0E7RQCE(java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    public final CatalogValidationResult validateCatalogUrl(String rawUrl) {
        URI uri;
        String strNormalize = CatalogUrlParser.INSTANCE.normalize(rawUrl);
        if (kotlin.text.o.h0(strNormalize)) {
            return new CatalogValidationResult(false, null, null, "URL is required", 6, null);
        }
        try {
            uri = new URI(strNormalize);
        } catch (Exception unused) {
            uri = null;
        }
        if (uri == null) {
            return new CatalogValidationResult(false, null, null, "Invalid URL format", 6, null);
        }
        String host = uri.getHost();
        if (host == null) {
            return new CatalogValidationResult(false, null, null, "Invalid host", 6, null);
        }
        String lowerCase = host.toLowerCase(Locale.ROOT);
        if (!lowerCase.equals("trakt.tv") && !kotlin.text.u.K(lowerCase, ".trakt.tv", false)) {
            return (lowerCase.equals("mdblist.com") || kotlin.text.u.K(lowerCase, ".mdblist.com", false)) ? new CatalogValidationResult(true, strNormalize, CatalogSourceType.MDBLIST, null, 8, null) : new CatalogValidationResult(false, null, null, "Only Trakt and MDBList URLs are supported", 6, null);
        }
        String strCanonicalizeTraktUrl = canonicalizeTraktUrl(strNormalize);
        return CatalogUrlParser.INSTANCE.parseTrakt(strCanonicalizeTraktUrl) == null ? new CatalogValidationResult(false, null, null, "Use a Trakt list URL: trakt.tv/users/{user}/lists/{list}", 6, null) : new CatalogValidationResult(true, strCanonicalizeTraktUrl, CatalogSourceType.TRAKT, null, 8, null);
    }
}
