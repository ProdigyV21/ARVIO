package com.arflix.tv.ui.screens.details;

import android.content.Context;
import android.util.Log;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.arflix.tv.R;
import com.arflix.tv.core.plugin.PluginManager;
import com.arflix.tv.data.api.TmdbApi;
import com.arflix.tv.data.api.TmdbCollectionRef;
import com.arflix.tv.data.api.TmdbTvSeason;
import com.arflix.tv.data.api.TraktComment;
import com.arflix.tv.data.api.TraktCommentUserStats;
import com.arflix.tv.data.model.Episode;
import com.arflix.tv.data.model.EpisodeIdentity;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.PersonDetails;
import com.arflix.tv.data.model.Review;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.StreamRepository;
import com.arflix.tv.data.repository.StreamingServiceInfo;
import com.arflix.tv.data.repository.StreamingServicesResult;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.WatchlistRepository;
import com.arflix.tv.data.repository.sync.RemoteSyncManager;
import com.arflix.tv.util.AnimeMapper;
import com.arflix.tv.util.AnimeSeasonStructure;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.firebase.crashlytics.internal.common.IdManager;
import dagger.hilt.android.qualifiers.ApplicationContext;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import javax.inject.Inject;
import ka.v1;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0098\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0007\u0018\u0000 á\u00012\u00020\u0001:\u0004á\u0001â\u0001Bs\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010#\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b#\u0010$J5\u0010)\u001a\u00020\"2\u0006\u0010&\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020\u001e2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u001e2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b)\u0010*J\u0015\u0010,\u001a\u00020\"2\u0006\u0010+\u001a\u00020\u001e¢\u0006\u0004\b,\u0010-J7\u00102\u001a\u0004\u0018\u00010 2\b\u0010.\u001a\u0004\u0018\u00010\u001e2\b\u0010/\u001a\u0004\u0018\u00010\u001e2\b\u00100\u001a\u0004\u0018\u00010\u001e2\b\u00101\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b2\u00103J\u0019\u00105\u001a\u00020\"2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b5\u00106J\r\u00107\u001a\u00020\"¢\u0006\u0004\b7\u00108J\r\u00109\u001a\u00020\"¢\u0006\u0004\b9\u00108J\u001f\u0010>\u001a\u00020\"2\u0006\u0010;\u001a\u00020:2\b\b\u0002\u0010=\u001a\u00020<¢\u0006\u0004\b>\u0010?J\r\u0010@\u001a\u00020\"¢\u0006\u0004\b@\u00108J\u0015\u0010B\u001a\u00020\"2\u0006\u0010A\u001a\u00020\u001e¢\u0006\u0004\bB\u0010-J\r\u0010C\u001a\u00020\"¢\u0006\u0004\bC\u00108J\u0015\u0010F\u001a\u00020\"2\u0006\u0010E\u001a\u00020D¢\u0006\u0004\bF\u0010GJ#\u0010J\u001a\u00020\"2\u0006\u0010E\u001a\u00020D2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020D0H¢\u0006\u0004\bJ\u0010KJ#\u0010M\u001a\u00020\"2\b\u0010L\u001a\u0004\u0018\u00010:2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\bM\u0010NJ%\u0010S\u001a\u00020\"2\u0006\u0010O\u001a\u00020\u001e2\u0006\u0010P\u001a\u00020\u001e2\u0006\u0010R\u001a\u00020Q¢\u0006\u0004\bS\u0010TJ\u0015\u0010U\u001a\u00020\"2\u0006\u0010O\u001a\u00020\u001e¢\u0006\u0004\bU\u0010-J\u0015\u0010V\u001a\u00020\"2\u0006\u0010O\u001a\u00020\u001e¢\u0006\u0004\bV\u0010-J\u0015\u0010X\u001a\b\u0012\u0004\u0012\u00020Q0WH\u0002¢\u0006\u0004\bX\u0010YJ\u0015\u0010Z\u001a\b\u0012\u0004\u0012\u00020:0WH\u0002¢\u0006\u0004\bZ\u0010YJ\u0015\u0010[\u001a\b\u0012\u0004\u0012\u00020Q0WH\u0002¢\u0006\u0004\b[\u0010YJ\u0015\u0010\\\u001a\b\u0012\u0004\u0012\u00020Q0WH\u0002¢\u0006\u0004\b\\\u0010YJ\u0017\u0010^\u001a\u00020Q2\u0006\u0010]\u001a\u00020:H\u0002¢\u0006\u0004\b^\u0010_J\u0019\u0010a\u001a\u00020:2\b\u0010`\u001a\u0004\u0018\u00010:H\u0002¢\u0006\u0004\ba\u0010bJ!\u0010f\u001a\u00020c2\u0006\u0010d\u001a\u00020c2\b\u0010e\u001a\u0004\u0018\u00010cH\u0002¢\u0006\u0004\bf\u0010gJ.\u0010l\u001a\b\u0012\u0004\u0012\u00020k0H2\u0006\u0010h\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020\u001e2\u0006\u0010j\u001a\u00020iH\u0082@¢\u0006\u0004\bl\u0010mJ=\u0010p\u001a\b\u0012\u0004\u0012\u00020k0H2\u0006\u0010h\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020\u001e2\b\u0010n\u001a\u0004\u0018\u00010c2\f\u0010o\u001a\b\u0012\u0004\u0012\u00020k0HH\u0002¢\u0006\u0004\bp\u0010qJ\u0017\u0010r\u001a\u00020Q2\u0006\u0010E\u001a\u00020DH\u0002¢\u0006\u0004\br\u0010sJ#\u0010t\u001a\b\u0012\u0004\u0012\u00020D0H2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020D0HH\u0002¢\u0006\u0004\bt\u0010uJ(\u0010y\u001a\u0004\u0018\u00010x2\u0006\u0010h\u001a\u00020\u001e2\f\u0010w\u001a\b\u0012\u0004\u0012\u00020:0vH\u0082@¢\u0006\u0004\by\u0010zJ+\u0010{\u001a\u00020\"2\u0006\u0010L\u001a\u00020:2\b\u0010O\u001a\u0004\u0018\u00010\u001e2\b\u0010P\u001a\u0004\u0018\u00010\u001eH\u0002¢\u0006\u0004\b{\u0010|J\u001d\u0010}\u001a\u00020\"2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020D0HH\u0002¢\u0006\u0004\b}\u0010~J\u001b\u0010\u0080\u0001\u001a\u00020\u007f2\u0006\u0010h\u001a\u00020\u001eH\u0082@¢\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001J@\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0084\u00012\u0006\u0010h\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020%2\u000b\b\u0002\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u001e2\u000b\b\u0002\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u001eH\u0082@¢\u0006\u0006\b\u0085\u0001\u0010\u0086\u0001J&\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0084\u00012\u0006\u0010h\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020%H\u0082@¢\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001Jc\u0010\u008f\u0001\u001a\u0005\u0018\u00010\u0084\u00012\u0006\u0010&\u001a\u00020%2\u0006\u0010h\u001a\u00020\u001e2\b\u0010O\u001a\u0004\u0018\u00010\u001e2\b\u0010P\u001a\u0004\u0018\u00010\u001e2\b\u0010\u008a\u0001\u001a\u00030\u0089\u00012\b\u0010\u008c\u0001\u001a\u00030\u008b\u00012\b\u0010\u008d\u0001\u001a\u00030\u008b\u00012\t\b\u0002\u0010\u008e\u0001\u001a\u00020QH\u0082@¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001J8\u0010\u0091\u0001\u001a\u00030\u008b\u00012\u0006\u0010h\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020%2\b\u0010O\u001a\u0004\u0018\u00010\u001e2\b\u0010P\u001a\u0004\u0018\u00010\u001eH\u0082@¢\u0006\u0006\b\u0091\u0001\u0010\u0086\u0001J\u001c\u0010\u0093\u0001\u001a\u00020:2\b\u0010\u0092\u0001\u001a\u00030\u008b\u0001H\u0002¢\u0006\u0006\b\u0093\u0001\u0010\u0094\u0001J3\u0010\u0097\u0001\u001a\u0004\u0018\u00010x2\u0006\u0010&\u001a\u00020%2\t\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u007f2\n\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0084\u0001H\u0002¢\u0006\u0006\b\u0097\u0001\u0010\u0098\u0001J$\u0010\u009a\u0001\u001a\u00030\u0099\u00012\u0006\u0010&\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020\u001eH\u0082@¢\u0006\u0006\b\u009a\u0001\u0010\u009b\u0001J4\u0010\u009d\u0001\u001a\t\u0012\u0005\u0012\u00030\u009c\u00010H2\u0006\u0010&\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010L\u001a\u0004\u0018\u00010:H\u0082@¢\u0006\u0006\b\u009d\u0001\u0010\u009e\u0001J+\u0010¡\u0001\u001a\t\u0012\u0005\u0012\u00030 \u00010H2\u0006\u0010&\u001a\u00020%2\u0007\u0010\u009f\u0001\u001a\u00020:H\u0082@¢\u0006\u0006\b¡\u0001\u0010¢\u0001J-\u0010¤\u0001\u001a\t\u0012\u0005\u0012\u00030\u009c\u00010H*\t\u0012\u0005\u0012\u00030 \u00010H2\u0007\u0010£\u0001\u001a\u00020QH\u0002¢\u0006\u0006\b¤\u0001\u0010¥\u0001J*\u0010¦\u0001\u001a\t\u0012\u0005\u0012\u00030\u009c\u00010H2\u0006\u0010&\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020\u001eH\u0082@¢\u0006\u0006\b¦\u0001\u0010\u009b\u0001J\u001a\u0010§\u0001\u001a\u0005\u0018\u00010\u009c\u0001*\u00030 \u0001H\u0002¢\u0006\u0006\b§\u0001\u0010¨\u0001J\u0019\u0010©\u0001\u001a\u00020:2\u0006\u0010`\u001a\u00020:H\u0002¢\u0006\u0005\b©\u0001\u0010bJ\u0019\u0010ª\u0001\u001a\u00020Q2\u0006\u0010`\u001a\u00020:H\u0002¢\u0006\u0005\bª\u0001\u0010_JW\u0010¯\u0001\u001a\u00020\"2\b\u0010L\u001a\u0004\u0018\u00010:2\b\u0010O\u001a\u0004\u0018\u00010\u001e2\b\u0010P\u001a\u0004\u0018\u00010\u001e2\b\u0010«\u0001\u001a\u00030\u008b\u00012\b\u0010¬\u0001\u001a\u00030\u008b\u00012\u0007\u0010\u00ad\u0001\u001a\u00020%2\u0007\u0010®\u0001\u001a\u00020\u001eH\u0082@¢\u0006\u0006\b¯\u0001\u0010°\u0001JW\u0010±\u0001\u001a\u00020\"2\b\u0010L\u001a\u0004\u0018\u00010:2\b\u0010O\u001a\u0004\u0018\u00010\u001e2\b\u0010P\u001a\u0004\u0018\u00010\u001e2\b\u0010«\u0001\u001a\u00030\u008b\u00012\b\u0010¬\u0001\u001a\u00030\u008b\u00012\u0007\u0010\u00ad\u0001\u001a\u00020%2\u0007\u0010®\u0001\u001a\u00020\u001eH\u0082@¢\u0006\u0006\b±\u0001\u0010°\u0001R\u0015\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0003\u0010²\u0001R\u0015\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0005\u0010³\u0001R\u0015\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0007\u0010´\u0001R\u0015\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\t\u0010µ\u0001R\u0015\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u000b\u0010¶\u0001R\u0015\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\r\u0010·\u0001R\u0015\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u000f\u0010¸\u0001R\u0015\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0011\u0010¹\u0001R\u0015\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0013\u0010º\u0001R\u0015\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0015\u0010»\u0001R\u0015\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0017\u0010¼\u0001R\u0015\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0019\u0010½\u0001R\u0015\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u001b\u0010¾\u0001R\u001f\u0010Á\u0001\u001a\n\u0012\u0005\u0012\u00030À\u00010¿\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÁ\u0001\u0010Â\u0001R$\u0010Ä\u0001\u001a\n\u0012\u0005\u0012\u00030À\u00010Ã\u00018\u0006¢\u0006\u0010\n\u0006\bÄ\u0001\u0010Å\u0001\u001a\u0006\bÆ\u0001\u0010Ç\u0001R\u0019\u0010È\u0001\u001a\u00020%8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÈ\u0001\u0010É\u0001R\u0019\u0010Ê\u0001\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÊ\u0001\u0010Ë\u0001R\u001b\u0010Ì\u0001\u001a\u0004\u0018\u00010i8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÌ\u0001\u0010Í\u0001R\u0019\u0010Î\u0001\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÎ\u0001\u0010Ë\u0001R\u001b\u0010Ï\u0001\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÏ\u0001\u0010Ð\u0001R\u001c\u0010Ò\u0001\u001a\u0005\u0018\u00010Ñ\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÒ\u0001\u0010Ó\u0001R\u001c\u0010Ô\u0001\u001a\u0005\u0018\u00010Ñ\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÔ\u0001\u0010Ó\u0001R\u001c\u0010Õ\u0001\u001a\u0005\u0018\u00010Ñ\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÕ\u0001\u0010Ó\u0001R\u001a\u0010Ö\u0001\u001a\u00030\u008b\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÖ\u0001\u0010×\u0001R\u001c\u0010Ø\u0001\u001a\u0005\u0018\u00010Ñ\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bØ\u0001\u0010Ó\u0001R\u001c\u0010Ù\u0001\u001a\u0005\u0018\u00010Ñ\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÙ\u0001\u0010Ó\u0001R\u0019\u0010Ú\u0001\u001a\u00020:8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÚ\u0001\u0010Û\u0001R\u001c\u0010Ü\u0001\u001a\u0005\u0018\u00010Ñ\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÜ\u0001\u0010Ó\u0001R\u0019\u0010Ý\u0001\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÝ\u0001\u0010Ë\u0001R\u0019\u0010Þ\u0001\u001a\u00020Q8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÞ\u0001\u0010ß\u0001R\u001c\u0010à\u0001\u001a\u0005\u0018\u00010Ñ\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bà\u0001\u0010Ó\u0001¨\u0006ã\u0001"}, d2 = {"Lcom/arflix/tv/ui/screens/details/DetailsViewModel;", "Landroidx/lifecycle/d1;", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/MediaRepository;", "mediaRepository", "Lcom/arflix/tv/core/plugin/PluginManager;", "pluginManager", "Lcom/arflix/tv/data/repository/ProfileManager;", "profileManager", "Lcom/arflix/tv/data/repository/TraktRepository;", "traktRepository", "Lcom/arflix/tv/data/repository/sync/RemoteSyncManager;", "remoteSyncManager", "Lcom/arflix/tv/data/repository/StreamRepository;", "streamRepository", "Lcom/arflix/tv/util/AnimeMapper;", "animeMapper", "Lcom/arflix/tv/data/api/TmdbApi;", "tmdbApi", "Lcom/arflix/tv/data/repository/WatchHistoryRepository;", "watchHistoryRepository", "Lcom/arflix/tv/data/repository/WatchlistRepository;", "watchlistRepository", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "cloudSyncRepository", "Lcom/arflix/tv/data/repository/LauncherContinueWatchingRepository;", "launcherContinueWatchingRepository", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/MediaRepository;Lcom/arflix/tv/core/plugin/PluginManager;Lcom/arflix/tv/data/repository/ProfileManager;Lcom/arflix/tv/data/repository/TraktRepository;Lcom/arflix/tv/data/repository/sync/RemoteSyncManager;Lcom/arflix/tv/data/repository/StreamRepository;Lcom/arflix/tv/util/AnimeMapper;Lcom/arflix/tv/data/api/TmdbApi;Lcom/arflix/tv/data/repository/WatchHistoryRepository;Lcom/arflix/tv/data/repository/WatchlistRepository;Lcom/arflix/tv/data/repository/CloudSyncRepository;Lcom/arflix/tv/data/repository/LauncherContinueWatchingRepository;)V", "", "mediaId", "Lcom/arflix/tv/data/model/EpisodeIdentity;", "identity", "Lx6/t0;", "recordPlayedEpisode", "(ILcom/arflix/tv/data/model/EpisodeIdentity;)V", "Lcom/arflix/tv/data/model/MediaType;", "mediaType", "initialSeason", "initialEpisode", "loadDetails", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;)V", "seasonNumber", "loadSeason", "(I)V", "displaySeason", "displayEpisode", "tmdbSeason", "tmdbEpisode", "resolveEpisodeIdentity", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/arflix/tv/data/model/EpisodeIdentity;", "episodeIndex", "toggleWatched", "(Ljava/lang/Integer;)V", "toggleWatchlist", "()V", "dismissToast", "", "message", "Lcom/arflix/tv/ui/screens/details/ToastType;", LinkHeader.Parameters.Type, "showToast", "(Ljava/lang/String;Lcom/arflix/tv/ui/screens/details/ToastType;)V", "refreshAfterPlayerReturn", "personId", "loadPerson", "closePersonModal", "Lcom/arflix/tv/data/model/StreamSource;", "stream", "prewarmStream", "(Lcom/arflix/tv/data/model/StreamSource;)V", "", "streams", "prewarmStreamsAround", "(Lcom/arflix/tv/data/model/StreamSource;Ljava/util/List;)V", "imdbId", "loadStreams", "(Ljava/lang/String;Lcom/arflix/tv/data/model/EpisodeIdentity;)V", "season", "episode", "", "watched", "markEpisodeWatched", "(IIZ)V", "markSeasonWatched", "markSeasonUnwatched", "Lc1/e;", "autoPlaySingleSourceKey", "()Lc1/e;", "autoPlayMinQualityKey", "showBudgetKey", "showEpisodeRatingsKey", "value", "isBlankRating", "(Ljava/lang/String;)Z", "raw", "normalizeAutoPlayMinQuality", "(Ljava/lang/String;)Ljava/lang/String;", "Lcom/arflix/tv/data/model/MediaItem;", "primary", "fallback", "mergeItem", "(Lcom/arflix/tv/data/model/MediaItem;Lcom/arflix/tv/data/model/MediaItem;)Lcom/arflix/tv/data/model/MediaItem;", "tmdbId", "Lcom/arflix/tv/util/AnimeSeasonStructure;", "structure", "Lcom/arflix/tv/data/model/Episode;", "loadAnimeDisplaySeason", "(IILcom/arflix/tv/util/AnimeSeasonStructure;Ld7/d;)Ljava/lang/Object;", "item", "canonicalEpisodes", "normalizeAnimeEpisodesForDisplay", "(IILcom/arflix/tv/data/model/MediaItem;Ljava/util/List;)Ljava/util/List;", "isPendingDebridStream", "(Lcom/arflix/tv/data/model/StreamSource;)Z", "sortPlayableStreamsFirst", "(Ljava/util/List;)Ljava/util/List;", "", "watchedKeys", "Lcom/arflix/tv/ui/screens/details/PlayTarget;", "deriveNextUnwatchedPlayTarget", "(ILjava/util/Set;Ld7/d;)Ljava/lang/Object;", "prefetchStreamsInBackground", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)V", "prewarmVisibleStreams", "(Ljava/util/List;)V", "Lcom/arflix/tv/ui/screens/details/SeasonProgressResult;", "fetchSeasonProgress", "(ILd7/d;)Ljava/lang/Object;", "preferredSeason", "preferredEpisode", "Lcom/arflix/tv/ui/screens/details/ResumeInfo;", "fetchResumeInfo", "(ILcom/arflix/tv/data/model/MediaType;Ljava/lang/Integer;Ljava/lang/Integer;Ld7/d;)Ljava/lang/Object;", "fetchResumeInfoFromHistoryOnly", "(ILcom/arflix/tv/data/model/MediaType;Ld7/d;)Ljava/lang/Object;", "", "progress", "", "positionSeconds", "durationSeconds", "allowProgressDerivedResume", "buildResumeFromProgress", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;FJJZLd7/d;)Ljava/lang/Object;", "resolveRuntimeSeconds", "seconds", "formatResumeTime", "(J)Ljava/lang/String;", "result", "resumeInfo", "buildPlayTarget", "(Lcom/arflix/tv/data/model/MediaType;Lcom/arflix/tv/ui/screens/details/SeasonProgressResult;Lcom/arflix/tv/ui/screens/details/ResumeInfo;)Lcom/arflix/tv/ui/screens/details/PlayTarget;", "Lcom/arflix/tv/ui/screens/details/DetailsViewModel$ExternalIds;", "resolveExternalIds", "(Lcom/arflix/tv/data/model/MediaType;ILd7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/model/Review;", "loadCommunityReviews", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/String;Ld7/d;)Ljava/lang/Object;", "traktId", "Lcom/arflix/tv/data/api/TraktComment;", "loadCommunityComments", "(Lcom/arflix/tv/data/model/MediaType;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "requireReview", "toFilteredCommunityReviews", "(Ljava/util/List;Z)Ljava/util/List;", "loadFilteredTmdbReviews", "toCommunityReview", "(Lcom/arflix/tv/data/api/TraktComment;)Lcom/arflix/tv/data/model/Review;", "cleanCommunityReviewText", "isSpammyReviewText", "timeoutMs", "requestId", "requestMediaType", "requestMediaId", "appendHomeServerSourcesInBackground", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;JJLcom/arflix/tv/data/model/MediaType;ILd7/d;)Ljava/lang/Object;", "appendVodSourceInBackground", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/MediaRepository;", "Lcom/arflix/tv/core/plugin/PluginManager;", "Lcom/arflix/tv/data/repository/ProfileManager;", "Lcom/arflix/tv/data/repository/TraktRepository;", "Lcom/arflix/tv/data/repository/sync/RemoteSyncManager;", "Lcom/arflix/tv/data/repository/StreamRepository;", "Lcom/arflix/tv/util/AnimeMapper;", "Lcom/arflix/tv/data/api/TmdbApi;", "Lcom/arflix/tv/data/repository/WatchHistoryRepository;", "Lcom/arflix/tv/data/repository/WatchlistRepository;", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "Lcom/arflix/tv/data/repository/LauncherContinueWatchingRepository;", "Lna/q0;", "Lcom/arflix/tv/ui/screens/details/DetailsUiState;", "_uiState", "Lna/q0;", "Lna/h1;", "uiState", "Lna/h1;", "getUiState", "()Lna/h1;", "currentMediaType", "Lcom/arflix/tv/data/model/MediaType;", "currentMediaId", "I", "animeSeasonStructure", "Lcom/arflix/tv/util/AnimeSeasonStructure;", "lastPlayedMediaId", "lastPlayedIdentity", "Lcom/arflix/tv/data/model/EpisodeIdentity;", "Lka/v1;", "vodAppendJob", "Lka/v1;", "homeServerAppendJob", "loadStreamsJob", "loadStreamsRequestId", "J", "focusedStreamPrewarmJob", "streamListPrewarmJob", "lastStreamListPrewarmKey", "Ljava/lang/String;", "seasonLoadJob", "seasonLoadRequestedSeason", "initialLoadComplete", "Z", "prefetchJob", "Companion", "ExternalIds", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DetailsViewModel extends androidx.lifecycle.d1 {
    private static final int MAX_COMMUNITY_REVIEW_CHARS = 1400;
    private static final int MIN_COMMUNITY_REVIEW_CHARS = 40;
    private static final int MIN_COMMUNITY_REVIEW_COUNT = 1;
    private static final int MIN_COMMUNITY_REVIEW_WORDS = 8;
    private static final String TAG = "DetailsViewModel";
    private final na.q0<DetailsUiState> _uiState;
    private final AnimeMapper animeMapper;
    private AnimeSeasonStructure animeSeasonStructure;
    private final CloudSyncRepository cloudSyncRepository;
    private final Context context;
    private int currentMediaId;
    private MediaType currentMediaType;
    private v1 focusedStreamPrewarmJob;
    private v1 homeServerAppendJob;
    private volatile boolean initialLoadComplete;
    private EpisodeIdentity lastPlayedIdentity;
    private int lastPlayedMediaId;
    private String lastStreamListPrewarmKey;
    private final LauncherContinueWatchingRepository launcherContinueWatchingRepository;
    private v1 loadStreamsJob;
    private long loadStreamsRequestId;
    private final MediaRepository mediaRepository;
    private final PluginManager pluginManager;
    private v1 prefetchJob;
    private final ProfileManager profileManager;
    private final RemoteSyncManager remoteSyncManager;
    private v1 seasonLoadJob;
    private int seasonLoadRequestedSeason;
    private v1 streamListPrewarmJob;
    private final StreamRepository streamRepository;
    private final TmdbApi tmdbApi;
    private final TraktRepository traktRepository;
    private final na.h1<DetailsUiState> uiState;
    private v1 vodAppendJob;
    private final WatchHistoryRepository watchHistoryRepository;
    private final WatchlistRepository watchlistRepository;
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ&\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/ui/screens/details/DetailsViewModel$ExternalIds;", "", "imdbId", "", "tvdbId", "", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;)V", "getImdbId", "()Ljava/lang/String;", "getTvdbId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Integer;)Lcom/arflix/tv/ui/screens/details/DetailsViewModel$ExternalIds;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ExternalIds {
        private final String imdbId;
        private final Integer tvdbId;

        public ExternalIds(String str, Integer num) {
            this.imdbId = str;
            this.tvdbId = num;
        }

        public static /* synthetic */ ExternalIds copy$default(ExternalIds externalIds, String str, Integer num, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = externalIds.imdbId;
            }
            if ((i10 & 2) != 0) {
                num = externalIds.tvdbId;
            }
            return externalIds.copy(str, num);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getImdbId() {
            return this.imdbId;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getTvdbId() {
            return this.tvdbId;
        }

        public final ExternalIds copy(String imdbId, Integer tvdbId) {
            return new ExternalIds(imdbId, tvdbId);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ExternalIds)) {
                return false;
            }
            ExternalIds externalIds = (ExternalIds) other;
            return kotlin.jvm.internal.p.a(this.imdbId, externalIds.imdbId) && kotlin.jvm.internal.p.a(this.tvdbId, externalIds.tvdbId);
        }

        public final String getImdbId() {
            return this.imdbId;
        }

        public final Integer getTvdbId() {
            return this.tvdbId;
        }

        public int hashCode() {
            String str = this.imdbId;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            Integer num = this.tvdbId;
            return iHashCode + (num != null ? num.hashCode() : 0);
        }

        public String toString() {
            return "ExternalIds(imdbId=" + this.imdbId + ", tvdbId=" + this.tvdbId + ")";
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

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
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$appendHomeServerSourcesInBackground$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel", f = "DetailsViewModel.kt", l = {2792, 2800}, m = "appendHomeServerSourcesInBackground", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int I$0;
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DetailsViewModel.this.appendHomeServerSourcesInBackground(null, null, null, 0L, 0L, null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$appendVodSourceInBackground$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel", f = "DetailsViewModel.kt", l = {2853, 2861}, m = "appendVodSourceInBackground", v = 2)
    public static final class C13101 extends f7.c {
        int I$0;
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C13101(d7.d<? super C13101> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DetailsViewModel.this.appendVodSourceInBackground(null, null, null, 0L, 0L, null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$deriveNextUnwatchedPlayTarget$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel", f = "DetailsViewModel.kt", l = {1423, 1426}, m = "deriveNextUnwatchedPlayTarget", v = 2)
    public static final class C13111 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C13111(d7.d<? super C13111> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DetailsViewModel.this.deriveNextUnwatchedPlayTarget(0, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$fetchResumeInfo$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel", f = "DetailsViewModel.kt", l = {2339, 2353, 2367, 2369, 2380, 2382, 2391, 2401, 2419}, m = "fetchResumeInfo", v = 2)
    public static final class C13121 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
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
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C13121(d7.d<? super C13121> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DetailsViewModel.this.fetchResumeInfo(0, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$fetchResumeInfoFromHistoryOnly$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel", f = "DetailsViewModel.kt", l = {2460, 2465, 2469}, m = "fetchResumeInfoFromHistoryOnly", v = 2)
    public static final class C13131 extends f7.c {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C13131(d7.d<? super C13131> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DetailsViewModel.this.fetchResumeInfoFromHistoryOnly(0, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$fetchSeasonProgress$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel", f = "DetailsViewModel.kt", l = {2267, 2270, 2285}, m = "fetchSeasonProgress", v = 2)
    public static final class C13141 extends f7.c {
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
        /* synthetic */ Object result;

        public C13141(d7.d<? super C13141> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DetailsViewModel.this.fetchSeasonProgress(0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadAnimeDisplaySeason$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel", f = "DetailsViewModel.kt", l = {1071}, m = "loadAnimeDisplaySeason", v = 2)
    public static final class C13151 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
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

        public C13151(d7.d<? super C13151> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DetailsViewModel.this.loadAnimeDisplaySeason(0, 0, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadCommunityComments$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel", f = "DetailsViewModel.kt", l = {2669, 2671, 2675, 2677}, m = "loadCommunityComments", v = 2)
    public static final class C13161 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C13161(d7.d<? super C13161> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DetailsViewModel.this.loadCommunityComments(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadCommunityReviews$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel", f = "DetailsViewModel.kt", l = {2653, 2664}, m = "loadCommunityReviews", v = 2)
    public static final class C13171 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        /* synthetic */ Object result;

        public C13171(d7.d<? super C13171> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DetailsViewModel.this.loadCommunityReviews(null, 0, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1", f = "DetailsViewModel.kt", l = {303, 424, 465, 504}, m = "invokeSuspend", v = 2)
    public static final class C13181 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ Integer $initialEpisode;
        final /* synthetic */ Integer $initialSeason;
        final /* synthetic */ int $mediaId;
        final /* synthetic */ MediaType $mediaType;
        int I$0;
        int I$1;
        int I$10;
        int I$11;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        int I$6;
        int I$7;
        int I$8;
        int I$9;
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
        Object L$22;
        Object L$23;
        Object L$24;
        Object L$25;
        Object L$26;
        Object L$27;
        Object L$28;
        Object L$29;
        Object L$3;
        Object L$30;
        Object L$31;
        Object L$32;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$10, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$10", f = "DetailsViewModel.kt", l = {638, 639}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass10 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ int $mediaId;
            final /* synthetic */ MediaType $mediaType;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass10(DetailsViewModel detailsViewModel, MediaType mediaType, int i10, int i11, MediaType mediaType2, d7.d<? super AnonymousClass10> dVar) {
                super(2, dVar);
                this.this$0 = detailsViewModel;
                this.$mediaType = mediaType;
                this.$mediaId = i10;
                this.$requestMediaId = i11;
                this.$requestMediaType = mediaType2;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DetailsUiState invokeSuspend$lambda$1(List list, DetailsUiState detailsUiState) {
                return DetailsUiState.copy$default(detailsUiState, false, null, null, null, null, null, null, 0, 0, list, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -513, 1048575, null);
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass10 anonymousClass10 = new AnonymousClass10(this.this$0, this.$mediaType, this.$mediaId, this.$requestMediaId, this.$requestMediaType, dVar);
                anonymousClass10.L$0 = obj;
                return anonymousClass10;
            }

            /* JADX WARN: Code restructure failed: missing block: B:19:0x004e, code lost:
            
                if (r7 == r5) goto L20;
             */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r7) {
                /*
                    r6 = this;
                    java.lang.Object r0 = r6.L$0
                    ka.k0 r0 = (ka.k0) r0
                    int r1 = r6.label
                    r2 = 2
                    r3 = 1
                    r4 = 0
                    e7.a r5 = e7.a.f15033i
                    if (r1 == 0) goto L27
                    if (r1 == r3) goto L23
                    if (r1 != r2) goto L1b
                    java.lang.Object r0 = r6.L$1
                    ka.k0 r0 = (ka.k0) r0
                    k2.c.G(r7)     // Catch: java.lang.Throwable -> L19
                    goto L51
                L19:
                    r7 = move-exception
                    goto L54
                L1b:
                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r7.<init>(r0)
                    throw r7
                L23:
                    k2.c.G(r7)
                    goto L37
                L27:
                    k2.c.G(r7)
                    r6.L$0 = r0
                    r6.label = r3
                    r0 = 220(0xdc, double:1.087E-321)
                    java.lang.Object r7 = ka.s0.a(r0, r6)
                    if (r7 != r5) goto L37
                    goto L50
                L37:
                    com.arflix.tv.ui.screens.details.DetailsViewModel r7 = r6.this$0
                    com.arflix.tv.data.model.MediaType r0 = r6.$mediaType
                    int r1 = r6.$mediaId
                    com.arflix.tv.data.repository.MediaRepository r7 = com.arflix.tv.ui.screens.details.DetailsViewModel.access$getMediaRepository$p(r7)     // Catch: java.lang.Throwable -> L19
                    r6.L$0 = r4     // Catch: java.lang.Throwable -> L19
                    r6.L$1 = r4     // Catch: java.lang.Throwable -> L19
                    r3 = 0
                    r6.I$0 = r3     // Catch: java.lang.Throwable -> L19
                    r6.label = r2     // Catch: java.lang.Throwable -> L19
                    java.lang.Object r7 = r7.getCast(r0, r1, r6)     // Catch: java.lang.Throwable -> L19
                    if (r7 != r5) goto L51
                L50:
                    return r5
                L51:
                    java.util.List r7 = (java.util.List) r7     // Catch: java.lang.Throwable -> L19
                    goto L5a
                L54:
                    x6.c0 r0 = new x6.c0
                    r0.<init>(r7)
                    r7 = r0
                L5a:
                    boolean r0 = r7 instanceof x6.c0
                    if (r0 == 0) goto L60
                    goto L61
                L60:
                    r4 = r7
                L61:
                    java.util.List r4 = (java.util.List) r4
                    if (r4 == 0) goto L7b
                    boolean r7 = r4.isEmpty()
                    if (r7 == 0) goto L6c
                    goto L7b
                L6c:
                    com.arflix.tv.ui.screens.details.DetailsViewModel r7 = r6.this$0
                    int r0 = r6.$requestMediaId
                    com.arflix.tv.data.model.MediaType r1 = r6.$requestMediaType
                    com.arflix.tv.ui.screens.details.g1 r2 = new com.arflix.tv.ui.screens.details.g1
                    r3 = 0
                    r2.<init>(r4, r3)
                    com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.access$invokeSuspend$updateState(r7, r0, r1, r2)
                L7b:
                    x6.t0 r7 = x6.t0.f22605a
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.AnonymousClass10.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass10) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$11, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$11", f = "DetailsViewModel.kt", l = {646, 647, 657}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass11 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ int $mediaId;
            final /* synthetic */ MediaType $mediaType;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            int I$0;
            int I$1;
            int I$2;
            int I$3;
            int I$4;
            int I$5;
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
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass11(DetailsViewModel detailsViewModel, MediaType mediaType, int i10, int i11, MediaType mediaType2, d7.d<? super AnonymousClass11> dVar) {
                super(2, dVar);
                this.this$0 = detailsViewModel;
                this.$mediaType = mediaType;
                this.$mediaId = i10;
                this.$requestMediaId = i11;
                this.$requestMediaType = mediaType2;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DetailsUiState invokeSuspend$lambda$3(List list, Map map, DetailsUiState detailsUiState) {
                return DetailsUiState.copy$default(detailsUiState, false, null, null, null, null, null, null, 0, 0, null, list, map, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -3073, 1048575, null);
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass11 anonymousClass11 = new AnonymousClass11(this.this$0, this.$mediaType, this.$mediaId, this.$requestMediaId, this.$requestMediaType, dVar);
                anonymousClass11.L$0 = obj;
                return anonymousClass11;
            }

            /* JADX WARN: Code restructure failed: missing block: B:47:0x0112, code lost:
            
                if (r13 == r7) goto L48;
             */
            /* JADX WARN: Removed duplicated region for block: B:32:0x0092  */
            /* JADX WARN: Removed duplicated region for block: B:41:0x00bc A[LOOP:0: B:39:0x00b6->B:41:0x00bc, LOOP_END] */
            /* JADX WARN: Removed duplicated region for block: B:45:0x00e4  */
            /* JADX WARN: Removed duplicated region for block: B:54:0x0123  */
            /* JADX WARN: Removed duplicated region for block: B:57:0x0128  */
            /* JADX WARN: Removed duplicated region for block: B:58:0x012c  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x0112 -> B:49:0x0115). Please report as a decompilation issue!!! */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r13) {
                /*
                    Method dump skipped, instruction units count: 324
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.AnonymousClass11.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass11) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$12, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$12", f = "DetailsViewModel.kt", l = {668, 669, 671}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass12 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ ka.p0<ExternalIds> $externalIdsDeferred;
            final /* synthetic */ int $mediaId;
            final /* synthetic */ MediaType $mediaType;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass12(ka.p0<ExternalIds> p0Var, DetailsViewModel detailsViewModel, MediaType mediaType, int i10, int i11, MediaType mediaType2, d7.d<? super AnonymousClass12> dVar) {
                super(2, dVar);
                this.$externalIdsDeferred = p0Var;
                this.this$0 = detailsViewModel;
                this.$mediaType = mediaType;
                this.$mediaId = i10;
                this.$requestMediaId = i11;
                this.$requestMediaType = mediaType2;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DetailsUiState invokeSuspend$lambda$2(List list, DetailsUiState detailsUiState) {
                return DetailsUiState.copy$default(detailsUiState, false, null, null, null, null, null, null, 0, 0, null, null, null, list, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -4097, 1048575, null);
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass12 anonymousClass12 = new AnonymousClass12(this.$externalIdsDeferred, this.this$0, this.$mediaType, this.$mediaId, this.$requestMediaId, this.$requestMediaType, dVar);
                anonymousClass12.L$0 = obj;
                return anonymousClass12;
            }

            /* JADX WARN: Code restructure failed: missing block: B:38:0x0089, code lost:
            
                if (r11 != r7) goto L40;
             */
            /* JADX WARN: Removed duplicated region for block: B:32:0x006a  */
            /* JADX WARN: Removed duplicated region for block: B:35:0x0075 A[Catch: all -> 0x0022, TRY_ENTER, TryCatch #0 {all -> 0x0022, blocks: (B:8:0x001d, B:40:0x008c, B:35:0x0075, B:37:0x007b), top: B:55:0x000d }] */
            /* JADX WARN: Removed duplicated region for block: B:36:0x007a  */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r11) {
                /*
                    r10 = this;
                    java.lang.Object r0 = r10.L$0
                    ka.k0 r0 = (ka.k0) r0
                    int r1 = r10.label
                    r2 = 0
                    r3 = 3
                    r4 = 2
                    r5 = 1
                    r6 = 0
                    e7.a r7 = e7.a.f15033i
                    if (r1 == 0) goto L3b
                    if (r1 == r5) goto L37
                    if (r1 == r4) goto L2d
                    if (r1 != r3) goto L25
                    java.lang.Object r0 = r10.L$2
                    ka.k0 r0 = (ka.k0) r0
                    java.lang.Object r0 = r10.L$1
                    com.arflix.tv.ui.screens.details.DetailsViewModel$ExternalIds r0 = (com.arflix.tv.ui.screens.details.DetailsViewModel.ExternalIds) r0
                    k2.c.G(r11)     // Catch: java.lang.Throwable -> L22
                    goto L8c
                L22:
                    r11 = move-exception
                    goto L8f
                L25:
                    java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r11.<init>(r0)
                    throw r11
                L2d:
                    java.lang.Object r0 = r10.L$1
                    ka.k0 r0 = (ka.k0) r0
                    k2.c.G(r11)     // Catch: java.lang.Throwable -> L35
                    goto L5c
                L35:
                    r11 = move-exception
                    goto L5f
                L37:
                    k2.c.G(r11)
                    goto L4b
                L3b:
                    k2.c.G(r11)
                    r10.L$0 = r0
                    r10.label = r5
                    r8 = 420(0x1a4, double:2.075E-321)
                    java.lang.Object r11 = ka.s0.a(r8, r10)
                    if (r11 != r7) goto L4b
                    goto L8b
                L4b:
                    ka.p0<com.arflix.tv.ui.screens.details.DetailsViewModel$ExternalIds> r11 = r10.$externalIdsDeferred
                    r10.L$0 = r0     // Catch: java.lang.Throwable -> L35
                    r10.L$1 = r6     // Catch: java.lang.Throwable -> L35
                    r10.I$0 = r2     // Catch: java.lang.Throwable -> L35
                    r10.label = r4     // Catch: java.lang.Throwable -> L35
                    java.lang.Object r11 = r11.o(r10)     // Catch: java.lang.Throwable -> L35
                    if (r11 != r7) goto L5c
                    goto L8b
                L5c:
                    com.arflix.tv.ui.screens.details.DetailsViewModel$ExternalIds r11 = (com.arflix.tv.ui.screens.details.DetailsViewModel.ExternalIds) r11     // Catch: java.lang.Throwable -> L35
                    goto L65
                L5f:
                    x6.c0 r0 = new x6.c0
                    r0.<init>(r11)
                    r11 = r0
                L65:
                    boolean r0 = r11 instanceof x6.c0
                    if (r0 == 0) goto L6b
                    r11 = r6
                L6b:
                    com.arflix.tv.ui.screens.details.DetailsViewModel$ExternalIds r11 = (com.arflix.tv.ui.screens.details.DetailsViewModel.ExternalIds) r11
                    com.arflix.tv.ui.screens.details.DetailsViewModel r0 = r10.this$0
                    com.arflix.tv.data.model.MediaType r1 = r10.$mediaType
                    int r4 = r10.$mediaId
                    if (r11 == 0) goto L7a
                    java.lang.String r11 = r11.getImdbId()     // Catch: java.lang.Throwable -> L22
                    goto L7b
                L7a:
                    r11 = r6
                L7b:
                    r10.L$0 = r6     // Catch: java.lang.Throwable -> L22
                    r10.L$1 = r6     // Catch: java.lang.Throwable -> L22
                    r10.L$2 = r6     // Catch: java.lang.Throwable -> L22
                    r10.I$0 = r2     // Catch: java.lang.Throwable -> L22
                    r10.label = r3     // Catch: java.lang.Throwable -> L22
                    java.lang.Object r11 = com.arflix.tv.ui.screens.details.DetailsViewModel.access$loadCommunityReviews(r0, r1, r4, r11, r10)     // Catch: java.lang.Throwable -> L22
                    if (r11 != r7) goto L8c
                L8b:
                    return r7
                L8c:
                    java.util.List r11 = (java.util.List) r11     // Catch: java.lang.Throwable -> L22
                    goto L95
                L8f:
                    x6.c0 r0 = new x6.c0
                    r0.<init>(r11)
                    r11 = r0
                L95:
                    boolean r0 = r11 instanceof x6.c0
                    if (r0 == 0) goto L9b
                    goto L9c
                L9b:
                    r6 = r11
                L9c:
                    java.util.List r6 = (java.util.List) r6
                    if (r6 == 0) goto Lb6
                    boolean r11 = r6.isEmpty()
                    if (r11 == 0) goto La7
                    goto Lb6
                La7:
                    com.arflix.tv.ui.screens.details.DetailsViewModel r11 = r10.this$0
                    int r0 = r10.$requestMediaId
                    com.arflix.tv.data.model.MediaType r1 = r10.$requestMediaType
                    com.arflix.tv.ui.screens.details.g1 r2 = new com.arflix.tv.ui.screens.details.g1
                    r3 = 1
                    r2.<init>(r6, r3)
                    com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.access$invokeSuspend$updateState(r11, r0, r1, r2)
                Lb6:
                    x6.t0 r11 = x6.t0.f22605a
                    return r11
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.AnonymousClass12.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass12) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$13, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$13", f = "DetailsViewModel.kt", l = {682}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass13 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ int $mediaId;
            final /* synthetic */ MediaType $mediaType;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$13$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
            @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$13$2", f = "DetailsViewModel.kt", l = {695}, m = "invokeSuspend", v = 2)
            public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
                final /* synthetic */ TmdbCollectionRef $collectionRef;
                final /* synthetic */ int $requestMediaId;
                final /* synthetic */ MediaType $requestMediaType;
                int I$0;
                private /* synthetic */ Object L$0;
                Object L$1;
                int label;
                final /* synthetic */ DetailsViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(DetailsViewModel detailsViewModel, TmdbCollectionRef tmdbCollectionRef, int i10, MediaType mediaType, d7.d<? super AnonymousClass2> dVar) {
                    super(2, dVar);
                    this.this$0 = detailsViewModel;
                    this.$collectionRef = tmdbCollectionRef;
                    this.$requestMediaId = i10;
                    this.$requestMediaType = mediaType;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final DetailsUiState invokeSuspend$lambda$1(TmdbCollectionRef tmdbCollectionRef, List list, DetailsUiState detailsUiState) {
                    Integer collectionId = detailsUiState.getCollectionId();
                    return (collectionId != null && collectionId.intValue() == tmdbCollectionRef.getId()) ? DetailsUiState.copy$default(detailsUiState, false, null, null, null, null, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, list, null, -1, 786431, null) : detailsUiState;
                }

                @Override // f7.a
                public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, this.$collectionRef, this.$requestMediaId, this.$requestMediaType, dVar);
                    anonymousClass2.L$0 = obj;
                    return anonymousClass2;
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    Object c0Var;
                    int i10 = this.label;
                    try {
                        if (i10 == 0) {
                            k2.c.G(obj);
                            DetailsViewModel detailsViewModel = this.this$0;
                            TmdbCollectionRef tmdbCollectionRef = this.$collectionRef;
                            MediaRepository mediaRepository = detailsViewModel.mediaRepository;
                            int id = tmdbCollectionRef.getId();
                            this.L$0 = null;
                            this.L$1 = null;
                            this.I$0 = 0;
                            this.label = 1;
                            obj = mediaRepository.getTmdbCollectionItems(id, this);
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
                        c0Var = (List) obj;
                    } catch (Throwable th) {
                        c0Var = new x6.c0(th);
                    }
                    List list = (List) (c0Var instanceof x6.c0 ? null : c0Var);
                    if (list == null) {
                        list = kotlin.collections.z.f19728i;
                    }
                    C13181.invokeSuspend$updateState(this.this$0, this.$requestMediaId, this.$requestMediaType, new h1(this.$collectionRef, list));
                    return x6.t0.f22605a;
                }

                @Override // r7.p
                public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                    return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass13(MediaType mediaType, DetailsViewModel detailsViewModel, int i10, int i11, MediaType mediaType2, d7.d<? super AnonymousClass13> dVar) {
                super(2, dVar);
                this.$mediaType = mediaType;
                this.this$0 = detailsViewModel;
                this.$mediaId = i10;
                this.$requestMediaId = i11;
                this.$requestMediaType = mediaType2;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DetailsUiState invokeSuspend$lambda$1(TmdbCollectionRef tmdbCollectionRef, DetailsUiState detailsUiState) {
                return DetailsUiState.copy$default(detailsUiState, false, null, null, null, null, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, Integer.valueOf(tmdbCollectionRef.getId()), tmdbCollectionRef.getName(), null, tmdbCollectionRef.getPosterPath(), -1, 327679, null);
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass13 anonymousClass13 = new AnonymousClass13(this.$mediaType, this.this$0, this.$mediaId, this.$requestMediaId, this.$requestMediaType, dVar);
                anonymousClass13.L$0 = obj;
                return anonymousClass13;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                Object c0Var;
                ka.k0 k0Var = (ka.k0) this.L$0;
                int i10 = this.label;
                try {
                } catch (Throwable th) {
                    c0Var = new x6.c0(th);
                }
                if (i10 == 0) {
                    k2.c.G(obj);
                    if (this.$mediaType == MediaType.MOVIE) {
                        DetailsViewModel detailsViewModel = this.this$0;
                        int i11 = this.$mediaId;
                        MediaRepository mediaRepository = detailsViewModel.mediaRepository;
                        this.L$0 = k0Var;
                        this.L$1 = null;
                        this.I$0 = 0;
                        this.label = 1;
                        obj = mediaRepository.getMovieCollectionRef(i11, this);
                        e7.a aVar = e7.a.f15033i;
                        if (obj == aVar) {
                            return aVar;
                        }
                    }
                    return x6.t0.f22605a;
                }
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                c0Var = (TmdbCollectionRef) obj;
                if (c0Var instanceof x6.c0) {
                    c0Var = null;
                }
                TmdbCollectionRef tmdbCollectionRef = (TmdbCollectionRef) c0Var;
                if (tmdbCollectionRef != null) {
                    C13181.invokeSuspend$updateState(this.this$0, this.$requestMediaId, this.$requestMediaType, new i1(tmdbCollectionRef, 0));
                    ka.m0.p(k0Var, null, 0, new AnonymousClass2(this.this$0, tmdbCollectionRef, this.$requestMediaId, this.$requestMediaType, null), 3);
                }
                return x6.t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass13) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$14, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$14", f = "DetailsViewModel.kt", l = {707, 709}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass14 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ int $mediaId;
            final /* synthetic */ MediaType $mediaType;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass14(DetailsViewModel detailsViewModel, MediaType mediaType, int i10, int i11, MediaType mediaType2, d7.d<? super AnonymousClass14> dVar) {
                super(2, dVar);
                this.this$0 = detailsViewModel;
                this.$mediaType = mediaType;
                this.$mediaId = i10;
                this.$requestMediaId = i11;
                this.$requestMediaType = mediaType2;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DetailsUiState invokeSuspend$lambda$1(StreamingServicesResult streamingServicesResult, DetailsUiState detailsUiState) {
                List<StreamingServiceInfo> services = streamingServicesResult.getServices();
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(services, 10));
                for (StreamingServiceInfo streamingServiceInfo : services) {
                    arrayList.add(new StreamingServiceUi(streamingServiceInfo.getName(), streamingServiceInfo.getLogoUrl()));
                }
                return DetailsUiState.copy$default(detailsUiState, false, null, null, null, null, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, arrayList, streamingServicesResult.getRegion(), 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -1, 1048551, null);
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass14 anonymousClass14 = new AnonymousClass14(this.this$0, this.$mediaType, this.$mediaId, this.$requestMediaId, this.$requestMediaType, dVar);
                anonymousClass14.L$0 = obj;
                return anonymousClass14;
            }

            /* JADX WARN: Code restructure failed: missing block: B:19:0x0056, code lost:
            
                if (r8 == r5) goto L20;
             */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r8) {
                /*
                    r7 = this;
                    java.lang.Object r0 = r7.L$0
                    ka.k0 r0 = (ka.k0) r0
                    int r1 = r7.label
                    r2 = 2
                    r3 = 1
                    r4 = 0
                    e7.a r5 = e7.a.f15033i
                    if (r1 == 0) goto L27
                    if (r1 == r3) goto L23
                    if (r1 != r2) goto L1b
                    java.lang.Object r0 = r7.L$1
                    ka.k0 r0 = (ka.k0) r0
                    k2.c.G(r8)     // Catch: java.lang.Throwable -> L19
                    goto L59
                L19:
                    r8 = move-exception
                    goto L5c
                L1b:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r0)
                    throw r8
                L23:
                    k2.c.G(r8)
                    goto L37
                L27:
                    k2.c.G(r8)
                    r7.L$0 = r0
                    r7.label = r3
                    r0 = 260(0x104, double:1.285E-321)
                    java.lang.Object r8 = ka.s0.a(r0, r7)
                    if (r8 != r5) goto L37
                    goto L58
                L37:
                    com.arflix.tv.ui.screens.details.DetailsViewModel r8 = r7.this$0
                    com.arflix.tv.data.model.MediaType r0 = r7.$mediaType
                    int r1 = r7.$mediaId
                    com.arflix.tv.data.repository.MediaRepository r8 = com.arflix.tv.ui.screens.details.DetailsViewModel.access$getMediaRepository$p(r8)     // Catch: java.lang.Throwable -> L19
                    java.util.Locale r3 = java.util.Locale.getDefault()     // Catch: java.lang.Throwable -> L19
                    java.lang.String r3 = r3.getCountry()     // Catch: java.lang.Throwable -> L19
                    r7.L$0 = r4     // Catch: java.lang.Throwable -> L19
                    r7.L$1 = r4     // Catch: java.lang.Throwable -> L19
                    r6 = 0
                    r7.I$0 = r6     // Catch: java.lang.Throwable -> L19
                    r7.label = r2     // Catch: java.lang.Throwable -> L19
                    java.lang.Object r8 = r8.getStreamingServices(r0, r1, r3, r7)     // Catch: java.lang.Throwable -> L19
                    if (r8 != r5) goto L59
                L58:
                    return r5
                L59:
                    com.arflix.tv.data.repository.StreamingServicesResult r8 = (com.arflix.tv.data.repository.StreamingServicesResult) r8     // Catch: java.lang.Throwable -> L19
                    goto L62
                L5c:
                    x6.c0 r0 = new x6.c0
                    r0.<init>(r8)
                    r8 = r0
                L62:
                    boolean r0 = r8 instanceof x6.c0
                    if (r0 == 0) goto L68
                    goto L69
                L68:
                    r4 = r8
                L69:
                    com.arflix.tv.data.repository.StreamingServicesResult r4 = (com.arflix.tv.data.repository.StreamingServicesResult) r4
                    if (r4 == 0) goto L7c
                    com.arflix.tv.ui.screens.details.DetailsViewModel r8 = r7.this$0
                    int r0 = r7.$requestMediaId
                    com.arflix.tv.data.model.MediaType r1 = r7.$requestMediaType
                    com.arflix.tv.ui.screens.details.i1 r2 = new com.arflix.tv.ui.screens.details.i1
                    r3 = 1
                    r2.<init>(r4, r3)
                    com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.access$invokeSuspend$updateState(r8, r0, r1, r2)
                L7c:
                    x6.t0 r8 = x6.t0.f22605a
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.AnonymousClass14.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass14) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$15, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$15", f = "DetailsViewModel.kt", l = {731, 733, 739}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass15 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ ka.p0<List<Episode>> $episodesDeferred;
            final /* synthetic */ boolean $hasExplicitEpisodeTarget;
            final /* synthetic */ Integer $initialEpisode;
            final /* synthetic */ Integer $initialSeason;
            final /* synthetic */ int $mediaId;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            final /* synthetic */ ka.p0<ResumeInfo> $resumeDeferred;
            final /* synthetic */ int $seasonToLoad;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass15(Integer num, Integer num2, int i10, DetailsViewModel detailsViewModel, ka.p0<? extends List<Episode>> p0Var, ka.p0<ResumeInfo> p0Var2, int i11, boolean z, int i12, MediaType mediaType, d7.d<? super AnonymousClass15> dVar) {
                super(2, dVar);
                this.$initialSeason = num;
                this.$initialEpisode = num2;
                this.$seasonToLoad = i10;
                this.this$0 = detailsViewModel;
                this.$episodesDeferred = p0Var;
                this.$resumeDeferred = p0Var2;
                this.$mediaId = i11;
                this.$hasExplicitEpisodeTarget = z;
                this.$requestMediaId = i12;
                this.$requestMediaType = mediaType;
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX WARN: Removed duplicated region for block: B:22:0x004c  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x0061  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x0068  */
            /* JADX WARN: Removed duplicated region for block: B:36:0x00a0  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public static final com.arflix.tv.ui.screens.details.DetailsUiState invokeSuspend$lambda$10(boolean r62, com.arflix.tv.ui.screens.details.DetailsViewModel r63, java.util.List r64, int r65, com.arflix.tv.data.model.Episode r66, boolean r67, com.arflix.tv.ui.screens.details.DetailsUiState r68) {
                /*
                    Method dump skipped, instruction units count: 268
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.AnonymousClass15.invokeSuspend$lambda$10(boolean, com.arflix.tv.ui.screens.details.DetailsViewModel, java.util.List, int, com.arflix.tv.data.model.Episode, boolean, com.arflix.tv.ui.screens.details.DetailsUiState):com.arflix.tv.ui.screens.details.DetailsUiState");
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass15 anonymousClass15 = new AnonymousClass15(this.$initialSeason, this.$initialEpisode, this.$seasonToLoad, this.this$0, this.$episodesDeferred, this.$resumeDeferred, this.$mediaId, this.$hasExplicitEpisodeTarget, this.$requestMediaId, this.$requestMediaType, dVar);
                anonymousClass15.L$0 = obj;
                return anonymousClass15;
            }

            /* JADX WARN: Removed duplicated region for block: B:130:0x0284  */
            /* JADX WARN: Removed duplicated region for block: B:134:0x028f  */
            /* JADX WARN: Removed duplicated region for block: B:139:0x02a2  */
            /* JADX WARN: Removed duplicated region for block: B:148:0x02c4  */
            /* JADX WARN: Removed duplicated region for block: B:150:0x02c7  */
            /* JADX WARN: Removed duplicated region for block: B:158:0x02d9  */
            /* JADX WARN: Removed duplicated region for block: B:159:0x02db  */
            /* JADX WARN: Removed duplicated region for block: B:181:0x029d A[EDGE_INSN: B:181:0x029d->B:137:0x029d BREAK  A[LOOP:1: B:132:0x0289->B:183:?], SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:36:0x0085  */
            /* JADX WARN: Removed duplicated region for block: B:46:0x00a5  */
            /* JADX WARN: Removed duplicated region for block: B:54:0x00b7  */
            /* JADX WARN: Removed duplicated region for block: B:57:0x00be  */
            /* JADX WARN: Removed duplicated region for block: B:62:0x00ca  */
            /* JADX WARN: Removed duplicated region for block: B:69:0x00f2  */
            /* JADX WARN: Removed duplicated region for block: B:77:0x0105  */
            /* JADX WARN: Removed duplicated region for block: B:80:0x0114  */
            /* JADX WARN: Removed duplicated region for block: B:88:0x016f  */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r25) {
                /*
                    Method dump skipped, instruction units count: 778
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.AnonymousClass15.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass15) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$16, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$16", f = "DetailsViewModel.kt", l = {811}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass16 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            final /* synthetic */ ka.p0<Boolean> $watchlistDeferred;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass16(ka.p0<Boolean> p0Var, DetailsViewModel detailsViewModel, int i10, MediaType mediaType, d7.d<? super AnonymousClass16> dVar) {
                super(2, dVar);
                this.$watchlistDeferred = p0Var;
                this.this$0 = detailsViewModel;
                this.$requestMediaId = i10;
                this.$requestMediaType = mediaType;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DetailsUiState invokeSuspend$lambda$1(boolean z, DetailsUiState detailsUiState) {
                return DetailsUiState.copy$default(detailsUiState, false, null, null, null, null, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, z, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -134217729, 1048575, null);
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass16 anonymousClass16 = new AnonymousClass16(this.$watchlistDeferred, this.this$0, this.$requestMediaId, this.$requestMediaType, dVar);
                anonymousClass16.L$0 = obj;
                return anonymousClass16;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                Object c0Var;
                int i10 = this.label;
                try {
                    if (i10 == 0) {
                        k2.c.G(obj);
                        ka.p0<Boolean> p0Var = this.$watchlistDeferred;
                        this.L$0 = null;
                        this.L$1 = null;
                        this.I$0 = 0;
                        this.label = 1;
                        obj = p0Var.o(this);
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
                    c0Var = (Boolean) obj;
                    c0Var.getClass();
                } catch (Throwable th) {
                    c0Var = new x6.c0(th);
                }
                Object obj2 = Boolean.FALSE;
                if (c0Var instanceof x6.c0) {
                    c0Var = obj2;
                }
                C13181.invokeSuspend$updateState(this.this$0, this.$requestMediaId, this.$requestMediaType, new j1(((Boolean) c0Var).booleanValue(), 0));
                return x6.t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass16) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$17, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$17", f = "DetailsViewModel.kt", l = {818, 820, 823}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass17 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ DetailsUiState $baseState;
            final /* synthetic */ Integer $initialEpisode;
            final /* synthetic */ Integer $initialSeason;
            final /* synthetic */ int $mediaId;
            final /* synthetic */ MediaType $mediaType;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            final /* synthetic */ ka.p0<ResumeInfo> $resumeDeferred;
            final /* synthetic */ ka.p0<SeasonProgressResult> $seasonProgressDeferred;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass17(DetailsViewModel detailsViewModel, Integer num, Integer num2, MediaType mediaType, DetailsUiState detailsUiState, ka.p0<SeasonProgressResult> p0Var, int i10, ka.p0<ResumeInfo> p0Var2, int i11, MediaType mediaType2, d7.d<? super AnonymousClass17> dVar) {
                super(2, dVar);
                this.this$0 = detailsViewModel;
                this.$initialSeason = num;
                this.$initialEpisode = num2;
                this.$mediaType = mediaType;
                this.$baseState = detailsUiState;
                this.$seasonProgressDeferred = p0Var;
                this.$mediaId = i10;
                this.$resumeDeferred = p0Var2;
                this.$requestMediaId = i11;
                this.$requestMediaType = mediaType2;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DetailsUiState invokeSuspend$lambda$4(AnimeSeasonStructure animeSeasonStructure, DetailsViewModel detailsViewModel, int i10, Map map, DetailsUiState detailsUiState) {
                return (animeSeasonStructure != null || detailsViewModel.animeSeasonStructure == null) ? DetailsUiState.copy$default(detailsUiState, false, null, null, null, null, null, null, i10, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, map, null, null, null, null, null, null, false, null, null, null, null, null, -129, 1048447, null) : detailsUiState;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass17 anonymousClass17 = new AnonymousClass17(this.this$0, this.$initialSeason, this.$initialEpisode, this.$mediaType, this.$baseState, this.$seasonProgressDeferred, this.$mediaId, this.$resumeDeferred, this.$requestMediaId, this.$requestMediaType, dVar);
                anonymousClass17.L$0 = obj;
                return anonymousClass17;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:38:0x0088  */
            /* JADX WARN: Removed duplicated region for block: B:49:0x00af  */
            /* JADX WARN: Removed duplicated region for block: B:52:0x00b5  */
            /* JADX WARN: Removed duplicated region for block: B:54:0x00bb  */
            /* JADX WARN: Removed duplicated region for block: B:59:0x00d4  */
            /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r1v0, types: [int] */
            /* JADX WARN: Type inference failed for: r1v1 */
            /* JADX WARN: Type inference failed for: r1v15 */
            /* JADX WARN: Type inference failed for: r1v16 */
            /* JADX WARN: Type inference failed for: r1v17 */
            /* JADX WARN: Type inference failed for: r1v2 */
            /* JADX WARN: Type inference failed for: r1v24 */
            /* JADX WARN: Type inference failed for: r1v25 */
            /* JADX WARN: Type inference failed for: r1v26 */
            /* JADX WARN: Type inference failed for: r1v27 */
            /* JADX WARN: Type inference failed for: r1v28 */
            /* JADX WARN: Type inference failed for: r1v29 */
            /* JADX WARN: Type inference failed for: r1v3 */
            /* JADX WARN: Type inference failed for: r1v30 */
            /* JADX WARN: Type inference failed for: r1v31 */
            /* JADX WARN: Type inference failed for: r1v32 */
            /* JADX WARN: Type inference failed for: r1v33 */
            /* JADX WARN: Type inference failed for: r1v34 */
            /* JADX WARN: Type inference failed for: r1v35 */
            /* JADX WARN: Type inference failed for: r1v4, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r1v7 */
            /* JADX WARN: Type inference failed for: r2v1 */
            /* JADX WARN: Type inference failed for: r2v2 */
            /* JADX WARN: Type inference failed for: r2v20 */
            /* JADX WARN: Type inference failed for: r2v22, types: [com.arflix.tv.util.AnimeSeasonStructure] */
            /* JADX WARN: Type inference failed for: r2v23 */
            /* JADX WARN: Type inference failed for: r2v24 */
            /* JADX WARN: Type inference failed for: r2v25 */
            /* JADX WARN: Type inference failed for: r2v3 */
            /* JADX WARN: Type inference failed for: r2v4 */
            /* JADX WARN: Type inference failed for: r2v5 */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r14) {
                /*
                    Method dump skipped, instruction units count: 483
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.AnonymousClass17.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass17) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$18, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$18", f = "DetailsViewModel.kt", l = {864, 908}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass18 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ Integer $initialEpisode;
            final /* synthetic */ Integer $initialSeason;
            final /* synthetic */ MediaType $mediaType;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            final /* synthetic */ ka.p0<ResumeInfo> $resumeDeferred;
            final /* synthetic */ ka.p0<SeasonProgressResult> $seasonProgressDeferred;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass18(Integer num, Integer num2, MediaType mediaType, DetailsViewModel detailsViewModel, ka.p0<ResumeInfo> p0Var, int i10, MediaType mediaType2, ka.p0<SeasonProgressResult> p0Var2, d7.d<? super AnonymousClass18> dVar) {
                super(2, dVar);
                this.$initialSeason = num;
                this.$initialEpisode = num2;
                this.$mediaType = mediaType;
                this.this$0 = detailsViewModel;
                this.$resumeDeferred = p0Var;
                this.$requestMediaId = i10;
                this.$requestMediaType = mediaType2;
                this.$seasonProgressDeferred = p0Var2;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DetailsUiState invokeSuspend$lambda$2(DetailsViewModel detailsViewModel, Integer num, Integer num2, ResumeInfo resumeInfo, DetailsUiState detailsUiState) {
                String label;
                AnimeSeasonStructure animeSeasonStructure = detailsViewModel.animeSeasonStructure;
                EpisodeIdentity episodeIdentityIdentityForTmdb = animeSeasonStructure != null ? animeSeasonStructure.identityForTmdb(num.intValue(), num2.intValue()) : null;
                Integer numValueOf = Integer.valueOf(episodeIdentityIdentityForTmdb != null ? episodeIdentityIdentityForTmdb.getDisplaySeason() : num.intValue());
                Integer numValueOf2 = Integer.valueOf(episodeIdentityIdentityForTmdb != null ? episodeIdentityIdentityForTmdb.getDisplayEpisode() : num2.intValue());
                if (episodeIdentityIdentityForTmdb == null || (label = detailsViewModel.context.getString(R.string.continue_season_episode, Integer.valueOf(episodeIdentityIdentityForTmdb.getDisplaySeason()), Integer.valueOf(episodeIdentityIdentityForTmdb.getDisplayEpisode()))) == null) {
                    label = resumeInfo != null ? resumeInfo.getLabel() : detailsViewModel.context.getString(R.string.continue_season_episode, num, num2);
                }
                return DetailsUiState.copy$default(detailsUiState, false, null, null, null, null, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, numValueOf, numValueOf2, num, num2, label, resumeInfo != null ? Long.valueOf(resumeInfo.getPositionMs()) : null, false, null, null, null, null, null, -1, 1032447, null);
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX WARN: Removed duplicated region for block: B:11:0x0022  */
            /* JADX WARN: Removed duplicated region for block: B:19:0x003b  */
            /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
            /* JADX WARN: Removed duplicated region for block: B:25:0x0051  */
            /* JADX WARN: Removed duplicated region for block: B:26:0x0058  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x005c  */
            /* JADX WARN: Removed duplicated region for block: B:29:0x0063  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x0067  */
            /* JADX WARN: Removed duplicated region for block: B:35:0x0090  */
            /* JADX WARN: Removed duplicated region for block: B:36:0x0092  */
            /* JADX WARN: Removed duplicated region for block: B:37:0x0097  */
            /* JADX WARN: Removed duplicated region for block: B:39:0x009b  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public static final com.arflix.tv.ui.screens.details.DetailsUiState invokeSuspend$lambda$3(com.arflix.tv.ui.screens.details.PlayTarget r60, com.arflix.tv.ui.screens.details.DetailsViewModel r61, com.arflix.tv.ui.screens.details.DetailsUiState r62) {
                /*
                    Method dump skipped, instruction units count: 255
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.AnonymousClass18.invokeSuspend$lambda$3(com.arflix.tv.ui.screens.details.PlayTarget, com.arflix.tv.ui.screens.details.DetailsViewModel, com.arflix.tv.ui.screens.details.DetailsUiState):com.arflix.tv.ui.screens.details.DetailsUiState");
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX WARN: Removed duplicated region for block: B:11:0x0022  */
            /* JADX WARN: Removed duplicated region for block: B:19:0x003b  */
            /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
            /* JADX WARN: Removed duplicated region for block: B:25:0x0051  */
            /* JADX WARN: Removed duplicated region for block: B:26:0x0058  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x005c  */
            /* JADX WARN: Removed duplicated region for block: B:29:0x0063  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x0067  */
            /* JADX WARN: Removed duplicated region for block: B:35:0x0090  */
            /* JADX WARN: Removed duplicated region for block: B:36:0x0092  */
            /* JADX WARN: Removed duplicated region for block: B:37:0x0097  */
            /* JADX WARN: Removed duplicated region for block: B:39:0x009b  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public static final com.arflix.tv.ui.screens.details.DetailsUiState invokeSuspend$lambda$5(com.arflix.tv.ui.screens.details.PlayTarget r60, com.arflix.tv.ui.screens.details.DetailsViewModel r61, com.arflix.tv.ui.screens.details.DetailsUiState r62) {
                /*
                    Method dump skipped, instruction units count: 255
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.AnonymousClass18.invokeSuspend$lambda$5(com.arflix.tv.ui.screens.details.PlayTarget, com.arflix.tv.ui.screens.details.DetailsViewModel, com.arflix.tv.ui.screens.details.DetailsUiState):com.arflix.tv.ui.screens.details.DetailsUiState");
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass18 anonymousClass18 = new AnonymousClass18(this.$initialSeason, this.$initialEpisode, this.$mediaType, this.this$0, this.$resumeDeferred, this.$requestMediaId, this.$requestMediaType, this.$seasonProgressDeferred, dVar);
                anonymousClass18.L$0 = obj;
                return anonymousClass18;
            }

            /* JADX WARN: Code restructure failed: missing block: B:54:0x00d3, code lost:
            
                if (r14 == r7) goto L55;
             */
            /* JADX WARN: Removed duplicated region for block: B:46:0x0092  */
            /* JADX WARN: Removed duplicated region for block: B:63:0x00e6  */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r14) {
                /*
                    Method dump skipped, instruction units count: 257
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.AnonymousClass18.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass18) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$19, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$19", f = "DetailsViewModel.kt", l = {938, 944, 946, 952, 953, 963}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass19 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ DetailsUiState $baseState;
            final /* synthetic */ ka.p0<List<Episode>> $episodesDeferred;
            final /* synthetic */ ka.p0<ExternalIds> $externalIdsDeferred;
            final /* synthetic */ Integer $initialEpisode;
            final /* synthetic */ Integer $initialSeason;
            final /* synthetic */ int $mediaId;
            final /* synthetic */ MediaItem $mergedItem;
            final /* synthetic */ ka.p0<ResumeInfo> $resumeDeferred;
            final /* synthetic */ int $seasonToLoad;
            int I$0;
            int I$1;
            int I$2;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass19(DetailsUiState detailsUiState, Integer num, int i10, Integer num2, MediaItem mediaItem, DetailsViewModel detailsViewModel, int i11, ka.p0<ExternalIds> p0Var, ka.p0<ResumeInfo> p0Var2, ka.p0<? extends List<Episode>> p0Var3, d7.d<? super AnonymousClass19> dVar) {
                super(2, dVar);
                this.$baseState = detailsUiState;
                this.$initialSeason = num;
                this.$seasonToLoad = i10;
                this.$initialEpisode = num2;
                this.$mergedItem = mediaItem;
                this.this$0 = detailsViewModel;
                this.$mediaId = i11;
                this.$externalIdsDeferred = p0Var;
                this.$resumeDeferred = p0Var2;
                this.$episodesDeferred = p0Var3;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass19 anonymousClass19 = new AnonymousClass19(this.$baseState, this.$initialSeason, this.$seasonToLoad, this.$initialEpisode, this.$mergedItem, this.this$0, this.$mediaId, this.$externalIdsDeferred, this.$resumeDeferred, this.$episodesDeferred, dVar);
                anonymousClass19.L$0 = obj;
                return anonymousClass19;
            }

            /* JADX WARN: Code restructure failed: missing block: B:145:0x0250, code lost:
            
                if (r15 == r5) goto L146;
             */
            /* JADX WARN: Code restructure failed: missing block: B:50:0x00e5, code lost:
            
                if (r15 == r5) goto L146;
             */
            /* JADX WARN: Removed duplicated region for block: B:100:0x01b0  */
            /* JADX WARN: Removed duplicated region for block: B:104:0x01b8  */
            /* JADX WARN: Removed duplicated region for block: B:107:0x01bd  */
            /* JADX WARN: Removed duplicated region for block: B:110:0x01c3 A[PHI: r15
              0x01c3: PHI (r15v59 java.lang.Integer) = (r15v37 java.lang.Integer), (r15v39 java.lang.Integer) binds: [B:109:0x01c1, B:115:0x01d1] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:112:0x01c9  */
            /* JADX WARN: Removed duplicated region for block: B:126:0x01f5 A[PHI: r15
              0x01f5: PHI (r15v58 java.lang.Integer) = (r15v47 java.lang.Integer), (r15v49 java.lang.Integer) binds: [B:125:0x01f3, B:131:0x0203] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:155:0x0189 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:65:0x011e  */
            /* JADX WARN: Removed duplicated region for block: B:69:0x012c A[Catch: all -> 0x007a, TryCatch #3 {all -> 0x007a, blocks: (B:22:0x0075, B:67:0x0126, B:69:0x012c, B:71:0x0132), top: B:153:0x000c }] */
            /* JADX WARN: Removed duplicated region for block: B:70:0x0131  */
            /* JADX WARN: Removed duplicated region for block: B:75:0x0152 A[PHI: r6 r7 r15
              0x0152: PHI (r6v8 com.arflix.tv.ui.screens.details.DetailsViewModel$ExternalIds) = 
              (r6v6 com.arflix.tv.ui.screens.details.DetailsViewModel$ExternalIds)
              (r6v24 com.arflix.tv.ui.screens.details.DetailsViewModel$ExternalIds)
             binds: [B:72:0x0148, B:22:0x0075] A[DONT_GENERATE, DONT_INLINE]
              0x0152: PHI (r7v2 java.lang.String) = (r7v1 java.lang.String), (r7v16 java.lang.String) binds: [B:72:0x0148, B:22:0x0075] A[DONT_GENERATE, DONT_INLINE]
              0x0152: PHI (r15v23 java.lang.Object) = (r15v6 java.lang.Object), (r15v19 java.lang.Object), (r15v0 java.lang.Object) binds: [B:74:0x014c, B:72:0x0148, B:22:0x0075] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:77:0x0158  */
            /* JADX WARN: Removed duplicated region for block: B:81:0x0172  */
            /* JADX WARN: Removed duplicated region for block: B:87:0x0182  */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r15) throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 634
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.AnonymousClass19.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass19) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$2", f = "DetailsViewModel.kt", l = {442, 444}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ ka.p0<List<Episode>> $episodesDeferred;
            final /* synthetic */ MediaItem $initialItem;
            final /* synthetic */ ka.p0<MediaItem> $itemDeferred;
            final /* synthetic */ int $mediaId;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            final /* synthetic */ int $seasonToLoad;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass2(MediaItem mediaItem, DetailsViewModel detailsViewModel, int i10, int i11, ka.p0<? extends List<Episode>> p0Var, ka.p0<MediaItem> p0Var2, int i12, MediaType mediaType, d7.d<? super AnonymousClass2> dVar) {
                super(2, dVar);
                this.$initialItem = mediaItem;
                this.this$0 = detailsViewModel;
                this.$mediaId = i10;
                this.$seasonToLoad = i11;
                this.$episodesDeferred = p0Var;
                this.$itemDeferred = p0Var2;
                this.$requestMediaId = i12;
                this.$requestMediaType = mediaType;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DetailsUiState invokeSuspend$lambda$2(int i10, List list, DetailsUiState detailsUiState) {
                List list2;
                if (detailsUiState.getCurrentSeason() == i10) {
                    list2 = list;
                    if (kotlin.jvm.internal.p.a(detailsUiState.getEpisodes(), list2)) {
                        return detailsUiState;
                    }
                } else {
                    list2 = list;
                }
                return DetailsUiState.copy$default(detailsUiState, false, null, null, null, null, null, list2, 0, i10, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -321, 1048575, null);
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$initialItem, this.this$0, this.$mediaId, this.$seasonToLoad, this.$episodesDeferred, this.$itemDeferred, this.$requestMediaId, this.$requestMediaType, dVar);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            /* JADX WARN: Code restructure failed: missing block: B:38:0x0074, code lost:
            
                if (r8 != r6) goto L40;
             */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:30:0x0058  */
            /* JADX WARN: Type inference failed for: r0v21 */
            /* JADX WARN: Type inference failed for: r0v22 */
            /* JADX WARN: Type inference failed for: r0v8, types: [java.util.List] */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r8) {
                /*
                    r7 = this;
                    java.lang.Object r0 = r7.L$0
                    ka.k0 r0 = (ka.k0) r0
                    int r1 = r7.label
                    r2 = 0
                    r3 = 2
                    r4 = 1
                    r5 = 0
                    e7.a r6 = e7.a.f15033i
                    if (r1 == 0) goto L32
                    if (r1 == r4) goto L28
                    if (r1 != r3) goto L20
                    java.lang.Object r0 = r7.L$2
                    ka.k0 r0 = (ka.k0) r0
                    java.lang.Object r0 = r7.L$1
                    java.util.List r0 = (java.util.List) r0
                    k2.c.G(r8)     // Catch: java.lang.Throwable -> L1e
                    goto L77
                L1e:
                    r8 = move-exception
                    goto L7a
                L20:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r0)
                    throw r8
                L28:
                    java.lang.Object r0 = r7.L$1
                    ka.k0 r0 = (ka.k0) r0
                    k2.c.G(r8)     // Catch: java.lang.Throwable -> L30
                    goto L48
                L30:
                    r8 = move-exception
                    goto L4b
                L32:
                    k2.c.G(r8)
                    ka.p0<java.util.List<com.arflix.tv.data.model.Episode>> r8 = r7.$episodesDeferred
                    if (r8 == 0) goto L51
                    r7.L$0 = r0     // Catch: java.lang.Throwable -> L30
                    r7.L$1 = r5     // Catch: java.lang.Throwable -> L30
                    r7.I$0 = r2     // Catch: java.lang.Throwable -> L30
                    r7.label = r4     // Catch: java.lang.Throwable -> L30
                    java.lang.Object r8 = r8.o(r7)     // Catch: java.lang.Throwable -> L30
                    if (r8 != r6) goto L48
                    goto L76
                L48:
                    java.util.List r8 = (java.util.List) r8     // Catch: java.lang.Throwable -> L30
                    goto L52
                L4b:
                    x6.c0 r0 = new x6.c0
                    r0.<init>(r8)
                    goto L53
                L51:
                    r8 = r5
                L52:
                    r0 = r8
                L53:
                    boolean r8 = r0 instanceof x6.c0
                    if (r8 == 0) goto L59
                    r0 = r5
                L59:
                    java.util.List r0 = (java.util.List) r0
                    if (r0 == 0) goto La7
                    boolean r8 = r0.isEmpty()
                    if (r8 == 0) goto L64
                    goto La7
                L64:
                    ka.p0<com.arflix.tv.data.model.MediaItem> r8 = r7.$itemDeferred
                    r7.L$0 = r5     // Catch: java.lang.Throwable -> L1e
                    r7.L$1 = r0     // Catch: java.lang.Throwable -> L1e
                    r7.L$2 = r5     // Catch: java.lang.Throwable -> L1e
                    r7.I$0 = r2     // Catch: java.lang.Throwable -> L1e
                    r7.label = r3     // Catch: java.lang.Throwable -> L1e
                    java.lang.Object r8 = r8.o(r7)     // Catch: java.lang.Throwable -> L1e
                    if (r8 != r6) goto L77
                L76:
                    return r6
                L77:
                    com.arflix.tv.data.model.MediaItem r8 = (com.arflix.tv.data.model.MediaItem) r8     // Catch: java.lang.Throwable -> L1e
                    goto L80
                L7a:
                    x6.c0 r1 = new x6.c0
                    r1.<init>(r8)
                    r8 = r1
                L80:
                    boolean r1 = r8 instanceof x6.c0
                    if (r1 == 0) goto L86
                    goto L87
                L86:
                    r5 = r8
                L87:
                    com.arflix.tv.data.model.MediaItem r5 = (com.arflix.tv.data.model.MediaItem) r5
                    if (r5 != 0) goto L8d
                    com.arflix.tv.data.model.MediaItem r5 = r7.$initialItem
                L8d:
                    com.arflix.tv.ui.screens.details.DetailsViewModel r8 = r7.this$0
                    int r1 = r7.$mediaId
                    int r2 = r7.$seasonToLoad
                    java.util.List r8 = com.arflix.tv.ui.screens.details.DetailsViewModel.access$normalizeAnimeEpisodesForDisplay(r8, r1, r2, r5, r0)
                    com.arflix.tv.ui.screens.details.DetailsViewModel r0 = r7.this$0
                    int r1 = r7.$requestMediaId
                    com.arflix.tv.data.model.MediaType r2 = r7.$requestMediaType
                    int r3 = r7.$seasonToLoad
                    com.arflix.tv.ui.screens.details.m1 r4 = new com.arflix.tv.ui.screens.details.m1
                    r4.<init>()
                    com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.access$invokeSuspend$updateState(r0, r1, r2, r4)
                La7:
                    x6.t0 r8 = x6.t0.f22605a
                    return r8
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$5, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$5", f = "DetailsViewModel.kt", l = {536, 553, 575}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass5 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ Integer $initialEpisode;
            final /* synthetic */ int $mediaId;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            final /* synthetic */ int $seasonToLoad;
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
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass5(DetailsViewModel detailsViewModel, int i10, int i11, Integer num, int i12, MediaType mediaType, d7.d<? super AnonymousClass5> dVar) {
                super(2, dVar);
                this.this$0 = detailsViewModel;
                this.$mediaId = i10;
                this.$seasonToLoad = i11;
                this.$initialEpisode = num;
                this.$requestMediaId = i12;
                this.$requestMediaType = mediaType;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DetailsUiState invokeSuspend$lambda$0(AnimeSeasonStructure animeSeasonStructure, int i10, EpisodeIdentity episodeIdentity, List list, int i11, int i12, DetailsViewModel detailsViewModel, DetailsUiState detailsUiState) {
                String playLabel;
                int seasonCount = animeSeasonStructure.getSeasonCount();
                int i13 = i10 - 1;
                Integer numValueOf = episodeIdentity != null ? Integer.valueOf(episodeIdentity.getDisplaySeason()) : detailsUiState.getPlaySeason();
                Integer numValueOf2 = episodeIdentity != null ? Integer.valueOf(episodeIdentity.getDisplayEpisode()) : detailsUiState.getPlayEpisode();
                if (episodeIdentity == null || (playLabel = detailsViewModel.context.getString(R.string.continue_season_episode, Integer.valueOf(episodeIdentity.getDisplaySeason()), Integer.valueOf(episodeIdentity.getDisplayEpisode()))) == null) {
                    playLabel = detailsUiState.getPlayLabel();
                }
                return DetailsUiState.copy$default(detailsUiState, false, null, null, null, null, null, list, seasonCount, i10, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, i13, null, numValueOf, numValueOf2, Integer.valueOf(i11), Integer.valueOf(i12), playLabel, null, false, null, null, null, null, null, -449, 1040575, null);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DetailsUiState invokeSuspend$lambda$2(SeasonProgressResult seasonProgressResult, AnimeSeasonStructure animeSeasonStructure, DetailsUiState detailsUiState) {
                Map<Integer, x6.x> seasonProgress;
                if (seasonProgressResult == null || (seasonProgress = seasonProgressResult.getProgress()) == null) {
                    seasonProgress = detailsUiState.getSeasonProgress();
                }
                return DetailsUiState.copy$default(detailsUiState, false, null, null, null, null, null, null, animeSeasonStructure.getSeasonCount(), 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, seasonProgress, null, null, null, null, null, null, false, null, null, null, null, null, -129, 1048447, null);
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass5 anonymousClass5 = new AnonymousClass5(this.this$0, this.$mediaId, this.$seasonToLoad, this.$initialEpisode, this.$requestMediaId, this.$requestMediaType, dVar);
                anonymousClass5.L$0 = obj;
                return anonymousClass5;
            }

            /* JADX WARN: Removed duplicated region for block: B:60:0x014a  */
            /* JADX WARN: Removed duplicated region for block: B:69:0x015c  */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r17) throws java.lang.Exception {
                /*
                    Method dump skipped, instruction units count: 389
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.AnonymousClass5.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass5) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$7, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$7", f = "DetailsViewModel.kt", l = {592}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass7 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ ka.p0<ExternalIds> $externalIdsDeferred;
            final /* synthetic */ Integer $initialEpisode;
            final /* synthetic */ Integer $initialSeason;
            final /* synthetic */ int $mediaId;
            final /* synthetic */ MediaType $mediaType;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$7$2, reason: invalid class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
            @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$7$2", f = "DetailsViewModel.kt", l = {607}, m = "invokeSuspend", v = 2)
            public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
                final /* synthetic */ String $imdbId;
                final /* synthetic */ int $mediaId;
                final /* synthetic */ MediaType $mediaType;
                final /* synthetic */ int $requestMediaId;
                final /* synthetic */ MediaType $requestMediaType;
                int I$0;
                private /* synthetic */ Object L$0;
                Object L$1;
                int label;
                final /* synthetic */ DetailsViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(DetailsViewModel detailsViewModel, MediaType mediaType, int i10, String str, int i11, MediaType mediaType2, d7.d<? super AnonymousClass2> dVar) {
                    super(2, dVar);
                    this.this$0 = detailsViewModel;
                    this.$mediaType = mediaType;
                    this.$mediaId = i10;
                    this.$imdbId = str;
                    this.$requestMediaId = i11;
                    this.$requestMediaType = mediaType2;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final DetailsUiState invokeSuspend$lambda$1(String str, DetailsUiState detailsUiState) {
                    MediaItem item = detailsUiState.getItem();
                    return DetailsUiState.copy$default(detailsUiState, false, item != null ? MediaItem.copy$default(item, 0, null, null, null, null, null, null, null, str, null, null, null, null, 0, false, null, null, null, null, null, false, null, null, null, null, null, null, null, null, false, null, 0.0f, 0L, 0, false, null, false, false, null, null, null, null, -257, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null) : null, null, null, null, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -3, 1048575, null);
                }

                @Override // f7.a
                public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, this.$mediaType, this.$mediaId, this.$imdbId, this.$requestMediaId, this.$requestMediaType, dVar);
                    anonymousClass2.L$0 = obj;
                    return anonymousClass2;
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    Object c0Var;
                    int i10 = this.label;
                    try {
                        if (i10 == 0) {
                            k2.c.G(obj);
                            DetailsViewModel detailsViewModel = this.this$0;
                            MediaType mediaType = this.$mediaType;
                            int i11 = this.$mediaId;
                            String str = this.$imdbId;
                            MediaRepository mediaRepository = detailsViewModel.mediaRepository;
                            this.L$0 = null;
                            this.L$1 = null;
                            this.I$0 = 0;
                            this.label = 1;
                            obj = mediaRepository.getImdbRating(mediaType, i11, str, this);
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
                        c0Var = (String) obj;
                    } catch (Throwable th) {
                        c0Var = new x6.c0(th);
                    }
                    String str2 = (String) (c0Var instanceof x6.c0 ? null : c0Var);
                    if (str2 != null && !kotlin.text.o.h0(str2)) {
                        C13181.invokeSuspend$updateState(this.this$0, this.$requestMediaId, this.$requestMediaType, new o1(str2, 0));
                    }
                    return x6.t0.f22605a;
                }

                @Override // r7.p
                public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                    return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass7(DetailsViewModel detailsViewModel, MediaType mediaType, int i10, Integer num, Integer num2, ka.p0<ExternalIds> p0Var, int i11, MediaType mediaType2, d7.d<? super AnonymousClass7> dVar) {
                super(2, dVar);
                this.this$0 = detailsViewModel;
                this.$mediaType = mediaType;
                this.$mediaId = i10;
                this.$initialSeason = num;
                this.$initialEpisode = num2;
                this.$externalIdsDeferred = p0Var;
                this.$requestMediaId = i11;
                this.$requestMediaType = mediaType2;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DetailsUiState invokeSuspend$lambda$1(String str, Integer num, DetailsUiState detailsUiState) {
                return DetailsUiState.copy$default(detailsUiState, false, null, str, num, null, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -13, 1048575, null);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DetailsUiState invokeSuspend$lambda$2(Integer num, DetailsUiState detailsUiState) {
                return DetailsUiState.copy$default(detailsUiState, false, null, null, num, null, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -9, 1048575, null);
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass7 anonymousClass7 = new AnonymousClass7(this.this$0, this.$mediaType, this.$mediaId, this.$initialSeason, this.$initialEpisode, this.$externalIdsDeferred, this.$requestMediaId, this.$requestMediaType, dVar);
                anonymousClass7.L$0 = obj;
                return anonymousClass7;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                Object c0Var;
                Integer num;
                Integer num2;
                ka.k0 k0Var = (ka.k0) this.L$0;
                int i10 = this.label;
                try {
                    if (i10 == 0) {
                        k2.c.G(obj);
                        ka.p0<ExternalIds> p0Var = this.$externalIdsDeferred;
                        this.L$0 = k0Var;
                        this.L$1 = null;
                        this.I$0 = 0;
                        this.label = 1;
                        obj = p0Var.o(this);
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
                    c0Var = (ExternalIds) obj;
                } catch (Throwable th) {
                    c0Var = new x6.c0(th);
                }
                if (c0Var instanceof x6.c0) {
                    c0Var = null;
                }
                ExternalIds externalIds = (ExternalIds) c0Var;
                String imdbId = externalIds != null ? externalIds.getImdbId() : null;
                Integer tvdbId = externalIds != null ? externalIds.getTvdbId() : null;
                if (imdbId != null && !kotlin.text.o.h0(imdbId)) {
                    this.this$0.mediaRepository.cacheImdbId(this.$mediaType, this.$mediaId, imdbId);
                    C13181.invokeSuspend$updateState(this.this$0, this.$requestMediaId, this.$requestMediaType, new h1(imdbId, tvdbId, 3));
                    MediaType mediaType = this.$mediaType;
                    MediaType mediaType2 = MediaType.TV;
                    if (mediaType == mediaType2) {
                        Integer num3 = this.$initialSeason;
                        num = new Integer(num3 != null ? num3.intValue() : 1);
                    } else {
                        num = null;
                    }
                    if (this.$mediaType == mediaType2) {
                        Integer num4 = this.$initialEpisode;
                        num2 = new Integer(num4 != null ? num4.intValue() : 1);
                    } else {
                        num2 = null;
                    }
                    this.this$0.prefetchStreamsInBackground(imdbId, num, num2);
                    ka.m0.p(k0Var, null, 0, new AnonymousClass2(this.this$0, this.$mediaType, this.$mediaId, imdbId, this.$requestMediaId, this.$requestMediaType, null), 3);
                } else if (tvdbId != null) {
                    C13181.invokeSuspend$updateState(this.this$0, this.$requestMediaId, this.$requestMediaType, new i1(tvdbId, 2));
                }
                return x6.t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass7) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$8, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$8", f = "DetailsViewModel.kt", l = {623}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass8 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ ka.p0<String> $logoDeferred;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass8(DetailsViewModel detailsViewModel, ka.p0<String> p0Var, int i10, MediaType mediaType, d7.d<? super AnonymousClass8> dVar) {
                super(2, dVar);
                this.this$0 = detailsViewModel;
                this.$logoDeferred = p0Var;
                this.$requestMediaId = i10;
                this.$requestMediaType = mediaType;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DetailsUiState invokeSuspend$lambda$1(String str, DetailsUiState detailsUiState) {
                return DetailsUiState.copy$default(detailsUiState, false, null, null, null, str, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -17, 1048575, null);
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass8 anonymousClass8 = new AnonymousClass8(this.this$0, this.$logoDeferred, this.$requestMediaId, this.$requestMediaType, dVar);
                anonymousClass8.L$0 = obj;
                return anonymousClass8;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                Object c0Var;
                int i10 = this.label;
                try {
                    if (i10 == 0) {
                        k2.c.G(obj);
                        ka.p0<String> p0Var = this.$logoDeferred;
                        this.L$0 = null;
                        this.L$1 = null;
                        this.I$0 = 0;
                        this.label = 1;
                        obj = p0Var.o(this);
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
                    c0Var = (String) obj;
                } catch (Throwable th) {
                    c0Var = new x6.c0(th);
                }
                String str = (String) (c0Var instanceof x6.c0 ? null : c0Var);
                if (str != null && !str.equals(((DetailsUiState) this.this$0._uiState.getValue()).getLogoUrl())) {
                    C13181.invokeSuspend$updateState(this.this$0, this.$requestMediaId, this.$requestMediaType, new o1(str, 1));
                }
                return x6.t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass8) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$9, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$9", f = "DetailsViewModel.kt", l = {630, 631}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass9 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ int $mediaId;
            final /* synthetic */ MediaType $mediaType;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass9(DetailsViewModel detailsViewModel, MediaType mediaType, int i10, int i11, MediaType mediaType2, d7.d<? super AnonymousClass9> dVar) {
                super(2, dVar);
                this.this$0 = detailsViewModel;
                this.$mediaType = mediaType;
                this.$mediaId = i10;
                this.$requestMediaId = i11;
                this.$requestMediaType = mediaType2;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final DetailsUiState invokeSuspend$lambda$1(String str, DetailsUiState detailsUiState) {
                return DetailsUiState.copy$default(detailsUiState, false, null, null, null, null, str, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -33, 1048575, null);
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass9 anonymousClass9 = new AnonymousClass9(this.this$0, this.$mediaType, this.$mediaId, this.$requestMediaId, this.$requestMediaType, dVar);
                anonymousClass9.L$0 = obj;
                return anonymousClass9;
            }

            /* JADX WARN: Code restructure failed: missing block: B:19:0x004e, code lost:
            
                if (r7 == r5) goto L20;
             */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r7) {
                /*
                    r6 = this;
                    java.lang.Object r0 = r6.L$0
                    ka.k0 r0 = (ka.k0) r0
                    int r1 = r6.label
                    r2 = 2
                    r3 = 1
                    r4 = 0
                    e7.a r5 = e7.a.f15033i
                    if (r1 == 0) goto L27
                    if (r1 == r3) goto L23
                    if (r1 != r2) goto L1b
                    java.lang.Object r0 = r6.L$1
                    ka.k0 r0 = (ka.k0) r0
                    k2.c.G(r7)     // Catch: java.lang.Throwable -> L19
                    goto L51
                L19:
                    r7 = move-exception
                    goto L54
                L1b:
                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r7.<init>(r0)
                    throw r7
                L23:
                    k2.c.G(r7)
                    goto L37
                L27:
                    k2.c.G(r7)
                    r6.L$0 = r0
                    r6.label = r3
                    r0 = 180(0xb4, double:8.9E-322)
                    java.lang.Object r7 = ka.s0.a(r0, r6)
                    if (r7 != r5) goto L37
                    goto L50
                L37:
                    com.arflix.tv.ui.screens.details.DetailsViewModel r7 = r6.this$0
                    com.arflix.tv.data.model.MediaType r0 = r6.$mediaType
                    int r1 = r6.$mediaId
                    com.arflix.tv.data.repository.MediaRepository r7 = com.arflix.tv.ui.screens.details.DetailsViewModel.access$getMediaRepository$p(r7)     // Catch: java.lang.Throwable -> L19
                    r6.L$0 = r4     // Catch: java.lang.Throwable -> L19
                    r6.L$1 = r4     // Catch: java.lang.Throwable -> L19
                    r3 = 0
                    r6.I$0 = r3     // Catch: java.lang.Throwable -> L19
                    r6.label = r2     // Catch: java.lang.Throwable -> L19
                    java.lang.Object r7 = r7.getTrailerKey(r0, r1, r6)     // Catch: java.lang.Throwable -> L19
                    if (r7 != r5) goto L51
                L50:
                    return r5
                L51:
                    java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Throwable -> L19
                    goto L5a
                L54:
                    x6.c0 r0 = new x6.c0
                    r0.<init>(r7)
                    r7 = r0
                L5a:
                    boolean r0 = r7 instanceof x6.c0
                    if (r0 == 0) goto L60
                    goto L61
                L60:
                    r4 = r7
                L61:
                    java.lang.String r4 = (java.lang.String) r4
                    if (r4 == 0) goto L74
                    com.arflix.tv.ui.screens.details.DetailsViewModel r7 = r6.this$0
                    int r0 = r6.$requestMediaId
                    com.arflix.tv.data.model.MediaType r1 = r6.$requestMediaType
                    com.arflix.tv.ui.screens.details.o1 r2 = new com.arflix.tv.ui.screens.details.o1
                    r3 = 2
                    r2.<init>(r4, r3)
                    com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.access$invokeSuspend$updateState(r7, r0, r1, r2)
                L74:
                    x6.t0 r7 = x6.t0.f22605a
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.AnonymousClass9.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass9) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13181(int i10, MediaType mediaType, Integer num, Integer num2, d7.d<? super C13181> dVar) {
            super(2, dVar);
            this.$mediaId = i10;
            this.$mediaType = mediaType;
            this.$initialSeason = num;
            this.$initialEpisode = num2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invokeSuspend$isCurrentRequest(DetailsViewModel detailsViewModel, int i10, MediaType mediaType) {
            return detailsViewModel.currentMediaId == i10 && detailsViewModel.currentMediaType == mediaType;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final DetailsUiState invokeSuspend$lambda$13(int i10, DetailsUiState detailsUiState) {
            return DetailsUiState.copy$default(detailsUiState, false, null, null, null, null, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, i10, null, null, null, null, null, null, null, false, null, null, null, null, null, -1, 1048511, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static final <T> java.lang.Object invokeSuspend$loadDetailsPart(ka.k0 r3, java.lang.String r4, r7.l<? super d7.d<? super T>, ? extends java.lang.Object> r5, d7.d<? super T> r6) throws java.lang.Throwable {
            /*
                boolean r3 = r6 instanceof com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$loadDetailsPart$1
                if (r3 == 0) goto L13
                r3 = r6
                com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$loadDetailsPart$1 r3 = (com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$loadDetailsPart$1) r3
                int r0 = r3.label
                r1 = -2147483648(0xffffffff80000000, float:-0.0)
                r2 = r0 & r1
                if (r2 == 0) goto L13
                int r0 = r0 - r1
                r3.label = r0
                goto L18
            L13:
                com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$loadDetailsPart$1 r3 = new com.arflix.tv.ui.screens.details.DetailsViewModel$loadDetails$1$loadDetailsPart$1
                r3.<init>(r6)
            L18:
                java.lang.Object r6 = r3.result
                int r0 = r3.label
                r1 = 1
                r2 = 0
                if (r0 == 0) goto L40
                if (r0 != r1) goto L38
                java.lang.Object r4 = r3.L$3
                ka.k0 r4 = (ka.k0) r4
                java.lang.Object r4 = r3.L$2
                r7.l r4 = (r7.l) r4
                java.lang.Object r4 = r3.L$1
                java.lang.String r4 = (java.lang.String) r4
                java.lang.Object r3 = r3.L$0
                ka.k0 r3 = (ka.k0) r3
                k2.c.G(r6)     // Catch: java.lang.Throwable -> L36
                goto L5e
            L36:
                r3 = move-exception
                goto L59
            L38:
                java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
                java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
                r3.<init>(r4)
                throw r3
            L40:
                k2.c.G(r6)
                r3.L$0 = r2     // Catch: java.lang.Throwable -> L36
                r3.L$1 = r4     // Catch: java.lang.Throwable -> L36
                r3.L$2 = r2     // Catch: java.lang.Throwable -> L36
                r3.L$3 = r2     // Catch: java.lang.Throwable -> L36
                r6 = 0
                r3.I$0 = r6     // Catch: java.lang.Throwable -> L36
                r3.label = r1     // Catch: java.lang.Throwable -> L36
                java.lang.Object r6 = r5.invoke(r3)     // Catch: java.lang.Throwable -> L36
                e7.a r3 = e7.a.f15033i
                if (r6 != r3) goto L5e
                return r3
            L59:
                x6.c0 r6 = new x6.c0
                r6.<init>(r3)
            L5e:
                java.lang.Throwable r3 = x6.d0.a(r6)
                if (r3 == 0) goto L67
                invokeSuspend$logDetailsLoadFailure(r4, r3)
            L67:
                boolean r3 = r6 instanceof x6.c0
                if (r3 == 0) goto L6c
                goto L6d
            L6c:
                r2 = r6
            L6d:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.invokeSuspend$loadDetailsPart(ka.k0, java.lang.String, r7.l, d7.d):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$logDetailsLoadFailure(String str, Throwable th) throws Throwable {
            if (th instanceof CancellationException) {
                throw th;
            }
            Log.w(DetailsViewModel.TAG, "Failed to load details " + str, th);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$updateState(DetailsViewModel detailsViewModel, int i10, MediaType mediaType, r7.l<? super DetailsUiState, DetailsUiState> lVar) {
            if (invokeSuspend$isCurrentRequest(detailsViewModel, i10, mediaType)) {
                detailsViewModel._uiState.setValue(lVar.invoke(detailsViewModel._uiState.getValue()));
            }
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C13181 c13181 = DetailsViewModel.this.new C13181(this.$mediaId, this.$mediaType, this.$initialSeason, this.$initialEpisode, dVar);
            c13181.L$0 = obj;
            return c13181;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:122:0x03c1  */
        /* JADX WARN: Removed duplicated region for block: B:125:0x03c6  */
        /* JADX WARN: Removed duplicated region for block: B:138:0x03f3  */
        /* JADX WARN: Removed duplicated region for block: B:141:0x03fd  */
        /* JADX WARN: Removed duplicated region for block: B:142:0x0400  */
        /* JADX WARN: Removed duplicated region for block: B:144:0x0404 A[Catch: Exception -> 0x022d, TryCatch #4 {Exception -> 0x022d, blocks: (B:370:0x0ac0, B:372:0x0ac6, B:375:0x0ae2, B:377:0x0bc5, B:379:0x0be1, B:382:0x0c06, B:387:0x0cd8, B:389:0x0d3a, B:374:0x0ad5, B:266:0x07fe, B:269:0x0806, B:274:0x0812, B:275:0x089b, B:284:0x08b9, B:286:0x08bf, B:288:0x08cf, B:290:0x08d3, B:292:0x08dc, B:293:0x08eb, B:295:0x08f1, B:297:0x0909, B:299:0x090f, B:301:0x091c, B:303:0x0928, B:305:0x0930, B:307:0x0938, B:309:0x093e, B:311:0x094c, B:316:0x095f, B:318:0x0967, B:320:0x096e, B:325:0x0989, B:328:0x0995, B:330:0x099d, B:324:0x0984, B:291:0x08d8, B:265:0x07f8, B:199:0x0679, B:201:0x0681, B:203:0x069f, B:205:0x06a5, B:24:0x0227, B:32:0x0251, B:34:0x0261, B:36:0x026a, B:38:0x0288, B:40:0x028f, B:42:0x029d, B:44:0x02a6, B:46:0x02b8, B:48:0x02c0, B:50:0x02c6, B:52:0x02cc, B:57:0x02d6, B:61:0x02e0, B:63:0x02e8, B:68:0x02f6, B:71:0x0302, B:80:0x031e, B:85:0x032c, B:91:0x033a, B:93:0x034c, B:95:0x0356, B:99:0x035e, B:101:0x036e, B:106:0x0385, B:108:0x0395, B:113:0x03a2, B:115:0x03a6, B:117:0x03b4, B:119:0x03ba, B:123:0x03c2, B:126:0x03c8, B:128:0x03ce, B:139:0x03f5, B:144:0x0404, B:145:0x0406, B:149:0x0410, B:151:0x0429, B:153:0x050f, B:155:0x0552, B:157:0x0556, B:130:0x03da, B:132:0x03e4, B:137:0x03ee, B:83:0x0326, B:78:0x0319, B:75:0x0311, B:29:0x0235, B:321:0x0970), top: B:406:0x000f, inners: #19 }] */
        /* JADX WARN: Removed duplicated region for block: B:147:0x040c A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:150:0x0427  */
        /* JADX WARN: Removed duplicated region for block: B:153:0x050f A[Catch: Exception -> 0x022d, TryCatch #4 {Exception -> 0x022d, blocks: (B:370:0x0ac0, B:372:0x0ac6, B:375:0x0ae2, B:377:0x0bc5, B:379:0x0be1, B:382:0x0c06, B:387:0x0cd8, B:389:0x0d3a, B:374:0x0ad5, B:266:0x07fe, B:269:0x0806, B:274:0x0812, B:275:0x089b, B:284:0x08b9, B:286:0x08bf, B:288:0x08cf, B:290:0x08d3, B:292:0x08dc, B:293:0x08eb, B:295:0x08f1, B:297:0x0909, B:299:0x090f, B:301:0x091c, B:303:0x0928, B:305:0x0930, B:307:0x0938, B:309:0x093e, B:311:0x094c, B:316:0x095f, B:318:0x0967, B:320:0x096e, B:325:0x0989, B:328:0x0995, B:330:0x099d, B:324:0x0984, B:291:0x08d8, B:265:0x07f8, B:199:0x0679, B:201:0x0681, B:203:0x069f, B:205:0x06a5, B:24:0x0227, B:32:0x0251, B:34:0x0261, B:36:0x026a, B:38:0x0288, B:40:0x028f, B:42:0x029d, B:44:0x02a6, B:46:0x02b8, B:48:0x02c0, B:50:0x02c6, B:52:0x02cc, B:57:0x02d6, B:61:0x02e0, B:63:0x02e8, B:68:0x02f6, B:71:0x0302, B:80:0x031e, B:85:0x032c, B:91:0x033a, B:93:0x034c, B:95:0x0356, B:99:0x035e, B:101:0x036e, B:106:0x0385, B:108:0x0395, B:113:0x03a2, B:115:0x03a6, B:117:0x03b4, B:119:0x03ba, B:123:0x03c2, B:126:0x03c8, B:128:0x03ce, B:139:0x03f5, B:144:0x0404, B:145:0x0406, B:149:0x0410, B:151:0x0429, B:153:0x050f, B:155:0x0552, B:157:0x0556, B:130:0x03da, B:132:0x03e4, B:137:0x03ee, B:83:0x0326, B:78:0x0319, B:75:0x0311, B:29:0x0235, B:321:0x0970), top: B:406:0x000f, inners: #19 }] */
        /* JADX WARN: Removed duplicated region for block: B:154:0x0539  */
        /* JADX WARN: Removed duplicated region for block: B:157:0x0556 A[Catch: Exception -> 0x022d, TRY_LEAVE, TryCatch #4 {Exception -> 0x022d, blocks: (B:370:0x0ac0, B:372:0x0ac6, B:375:0x0ae2, B:377:0x0bc5, B:379:0x0be1, B:382:0x0c06, B:387:0x0cd8, B:389:0x0d3a, B:374:0x0ad5, B:266:0x07fe, B:269:0x0806, B:274:0x0812, B:275:0x089b, B:284:0x08b9, B:286:0x08bf, B:288:0x08cf, B:290:0x08d3, B:292:0x08dc, B:293:0x08eb, B:295:0x08f1, B:297:0x0909, B:299:0x090f, B:301:0x091c, B:303:0x0928, B:305:0x0930, B:307:0x0938, B:309:0x093e, B:311:0x094c, B:316:0x095f, B:318:0x0967, B:320:0x096e, B:325:0x0989, B:328:0x0995, B:330:0x099d, B:324:0x0984, B:291:0x08d8, B:265:0x07f8, B:199:0x0679, B:201:0x0681, B:203:0x069f, B:205:0x06a5, B:24:0x0227, B:32:0x0251, B:34:0x0261, B:36:0x026a, B:38:0x0288, B:40:0x028f, B:42:0x029d, B:44:0x02a6, B:46:0x02b8, B:48:0x02c0, B:50:0x02c6, B:52:0x02cc, B:57:0x02d6, B:61:0x02e0, B:63:0x02e8, B:68:0x02f6, B:71:0x0302, B:80:0x031e, B:85:0x032c, B:91:0x033a, B:93:0x034c, B:95:0x0356, B:99:0x035e, B:101:0x036e, B:106:0x0385, B:108:0x0395, B:113:0x03a2, B:115:0x03a6, B:117:0x03b4, B:119:0x03ba, B:123:0x03c2, B:126:0x03c8, B:128:0x03ce, B:139:0x03f5, B:144:0x0404, B:145:0x0406, B:149:0x0410, B:151:0x0429, B:153:0x050f, B:155:0x0552, B:157:0x0556, B:130:0x03da, B:132:0x03e4, B:137:0x03ee, B:83:0x0326, B:78:0x0319, B:75:0x0311, B:29:0x0235, B:321:0x0970), top: B:406:0x000f, inners: #19 }] */
        /* JADX WARN: Removed duplicated region for block: B:198:0x0652  */
        /* JADX WARN: Removed duplicated region for block: B:201:0x0681 A[Catch: Exception -> 0x022d, TryCatch #4 {Exception -> 0x022d, blocks: (B:370:0x0ac0, B:372:0x0ac6, B:375:0x0ae2, B:377:0x0bc5, B:379:0x0be1, B:382:0x0c06, B:387:0x0cd8, B:389:0x0d3a, B:374:0x0ad5, B:266:0x07fe, B:269:0x0806, B:274:0x0812, B:275:0x089b, B:284:0x08b9, B:286:0x08bf, B:288:0x08cf, B:290:0x08d3, B:292:0x08dc, B:293:0x08eb, B:295:0x08f1, B:297:0x0909, B:299:0x090f, B:301:0x091c, B:303:0x0928, B:305:0x0930, B:307:0x0938, B:309:0x093e, B:311:0x094c, B:316:0x095f, B:318:0x0967, B:320:0x096e, B:325:0x0989, B:328:0x0995, B:330:0x099d, B:324:0x0984, B:291:0x08d8, B:265:0x07f8, B:199:0x0679, B:201:0x0681, B:203:0x069f, B:205:0x06a5, B:24:0x0227, B:32:0x0251, B:34:0x0261, B:36:0x026a, B:38:0x0288, B:40:0x028f, B:42:0x029d, B:44:0x02a6, B:46:0x02b8, B:48:0x02c0, B:50:0x02c6, B:52:0x02cc, B:57:0x02d6, B:61:0x02e0, B:63:0x02e8, B:68:0x02f6, B:71:0x0302, B:80:0x031e, B:85:0x032c, B:91:0x033a, B:93:0x034c, B:95:0x0356, B:99:0x035e, B:101:0x036e, B:106:0x0385, B:108:0x0395, B:113:0x03a2, B:115:0x03a6, B:117:0x03b4, B:119:0x03ba, B:123:0x03c2, B:126:0x03c8, B:128:0x03ce, B:139:0x03f5, B:144:0x0404, B:145:0x0406, B:149:0x0410, B:151:0x0429, B:153:0x050f, B:155:0x0552, B:157:0x0556, B:130:0x03da, B:132:0x03e4, B:137:0x03ee, B:83:0x0326, B:78:0x0319, B:75:0x0311, B:29:0x0235, B:321:0x0970), top: B:406:0x000f, inners: #19 }] */
        /* JADX WARN: Removed duplicated region for block: B:202:0x0698  */
        /* JADX WARN: Removed duplicated region for block: B:205:0x06a5 A[Catch: Exception -> 0x022d, TRY_LEAVE, TryCatch #4 {Exception -> 0x022d, blocks: (B:370:0x0ac0, B:372:0x0ac6, B:375:0x0ae2, B:377:0x0bc5, B:379:0x0be1, B:382:0x0c06, B:387:0x0cd8, B:389:0x0d3a, B:374:0x0ad5, B:266:0x07fe, B:269:0x0806, B:274:0x0812, B:275:0x089b, B:284:0x08b9, B:286:0x08bf, B:288:0x08cf, B:290:0x08d3, B:292:0x08dc, B:293:0x08eb, B:295:0x08f1, B:297:0x0909, B:299:0x090f, B:301:0x091c, B:303:0x0928, B:305:0x0930, B:307:0x0938, B:309:0x093e, B:311:0x094c, B:316:0x095f, B:318:0x0967, B:320:0x096e, B:325:0x0989, B:328:0x0995, B:330:0x099d, B:324:0x0984, B:291:0x08d8, B:265:0x07f8, B:199:0x0679, B:201:0x0681, B:203:0x069f, B:205:0x06a5, B:24:0x0227, B:32:0x0251, B:34:0x0261, B:36:0x026a, B:38:0x0288, B:40:0x028f, B:42:0x029d, B:44:0x02a6, B:46:0x02b8, B:48:0x02c0, B:50:0x02c6, B:52:0x02cc, B:57:0x02d6, B:61:0x02e0, B:63:0x02e8, B:68:0x02f6, B:71:0x0302, B:80:0x031e, B:85:0x032c, B:91:0x033a, B:93:0x034c, B:95:0x0356, B:99:0x035e, B:101:0x036e, B:106:0x0385, B:108:0x0395, B:113:0x03a2, B:115:0x03a6, B:117:0x03b4, B:119:0x03ba, B:123:0x03c2, B:126:0x03c8, B:128:0x03ce, B:139:0x03f5, B:144:0x0404, B:145:0x0406, B:149:0x0410, B:151:0x0429, B:153:0x050f, B:155:0x0552, B:157:0x0556, B:130:0x03da, B:132:0x03e4, B:137:0x03ee, B:83:0x0326, B:78:0x0319, B:75:0x0311, B:29:0x0235, B:321:0x0970), top: B:406:0x000f, inners: #19 }] */
        /* JADX WARN: Removed duplicated region for block: B:207:0x06c7  */
        /* JADX WARN: Removed duplicated region for block: B:234:0x074d  */
        /* JADX WARN: Removed duplicated region for block: B:268:0x0804  */
        /* JADX WARN: Removed duplicated region for block: B:271:0x080c  */
        /* JADX WARN: Removed duplicated region for block: B:272:0x080e  */
        /* JADX WARN: Removed duplicated region for block: B:274:0x0812 A[Catch: Exception -> 0x022d, TryCatch #4 {Exception -> 0x022d, blocks: (B:370:0x0ac0, B:372:0x0ac6, B:375:0x0ae2, B:377:0x0bc5, B:379:0x0be1, B:382:0x0c06, B:387:0x0cd8, B:389:0x0d3a, B:374:0x0ad5, B:266:0x07fe, B:269:0x0806, B:274:0x0812, B:275:0x089b, B:284:0x08b9, B:286:0x08bf, B:288:0x08cf, B:290:0x08d3, B:292:0x08dc, B:293:0x08eb, B:295:0x08f1, B:297:0x0909, B:299:0x090f, B:301:0x091c, B:303:0x0928, B:305:0x0930, B:307:0x0938, B:309:0x093e, B:311:0x094c, B:316:0x095f, B:318:0x0967, B:320:0x096e, B:325:0x0989, B:328:0x0995, B:330:0x099d, B:324:0x0984, B:291:0x08d8, B:265:0x07f8, B:199:0x0679, B:201:0x0681, B:203:0x069f, B:205:0x06a5, B:24:0x0227, B:32:0x0251, B:34:0x0261, B:36:0x026a, B:38:0x0288, B:40:0x028f, B:42:0x029d, B:44:0x02a6, B:46:0x02b8, B:48:0x02c0, B:50:0x02c6, B:52:0x02cc, B:57:0x02d6, B:61:0x02e0, B:63:0x02e8, B:68:0x02f6, B:71:0x0302, B:80:0x031e, B:85:0x032c, B:91:0x033a, B:93:0x034c, B:95:0x0356, B:99:0x035e, B:101:0x036e, B:106:0x0385, B:108:0x0395, B:113:0x03a2, B:115:0x03a6, B:117:0x03b4, B:119:0x03ba, B:123:0x03c2, B:126:0x03c8, B:128:0x03ce, B:139:0x03f5, B:144:0x0404, B:145:0x0406, B:149:0x0410, B:151:0x0429, B:153:0x050f, B:155:0x0552, B:157:0x0556, B:130:0x03da, B:132:0x03e4, B:137:0x03ee, B:83:0x0326, B:78:0x0319, B:75:0x0311, B:29:0x0235, B:321:0x0970), top: B:406:0x000f, inners: #19 }] */
        /* JADX WARN: Removed duplicated region for block: B:275:0x089b A[Catch: Exception -> 0x022d, TryCatch #4 {Exception -> 0x022d, blocks: (B:370:0x0ac0, B:372:0x0ac6, B:375:0x0ae2, B:377:0x0bc5, B:379:0x0be1, B:382:0x0c06, B:387:0x0cd8, B:389:0x0d3a, B:374:0x0ad5, B:266:0x07fe, B:269:0x0806, B:274:0x0812, B:275:0x089b, B:284:0x08b9, B:286:0x08bf, B:288:0x08cf, B:290:0x08d3, B:292:0x08dc, B:293:0x08eb, B:295:0x08f1, B:297:0x0909, B:299:0x090f, B:301:0x091c, B:303:0x0928, B:305:0x0930, B:307:0x0938, B:309:0x093e, B:311:0x094c, B:316:0x095f, B:318:0x0967, B:320:0x096e, B:325:0x0989, B:328:0x0995, B:330:0x099d, B:324:0x0984, B:291:0x08d8, B:265:0x07f8, B:199:0x0679, B:201:0x0681, B:203:0x069f, B:205:0x06a5, B:24:0x0227, B:32:0x0251, B:34:0x0261, B:36:0x026a, B:38:0x0288, B:40:0x028f, B:42:0x029d, B:44:0x02a6, B:46:0x02b8, B:48:0x02c0, B:50:0x02c6, B:52:0x02cc, B:57:0x02d6, B:61:0x02e0, B:63:0x02e8, B:68:0x02f6, B:71:0x0302, B:80:0x031e, B:85:0x032c, B:91:0x033a, B:93:0x034c, B:95:0x0356, B:99:0x035e, B:101:0x036e, B:106:0x0385, B:108:0x0395, B:113:0x03a2, B:115:0x03a6, B:117:0x03b4, B:119:0x03ba, B:123:0x03c2, B:126:0x03c8, B:128:0x03ce, B:139:0x03f5, B:144:0x0404, B:145:0x0406, B:149:0x0410, B:151:0x0429, B:153:0x050f, B:155:0x0552, B:157:0x0556, B:130:0x03da, B:132:0x03e4, B:137:0x03ee, B:83:0x0326, B:78:0x0319, B:75:0x0311, B:29:0x0235, B:321:0x0970), top: B:406:0x000f, inners: #19 }] */
        /* JADX WARN: Removed duplicated region for block: B:372:0x0ac6 A[Catch: Exception -> 0x022d, TryCatch #4 {Exception -> 0x022d, blocks: (B:370:0x0ac0, B:372:0x0ac6, B:375:0x0ae2, B:377:0x0bc5, B:379:0x0be1, B:382:0x0c06, B:387:0x0cd8, B:389:0x0d3a, B:374:0x0ad5, B:266:0x07fe, B:269:0x0806, B:274:0x0812, B:275:0x089b, B:284:0x08b9, B:286:0x08bf, B:288:0x08cf, B:290:0x08d3, B:292:0x08dc, B:293:0x08eb, B:295:0x08f1, B:297:0x0909, B:299:0x090f, B:301:0x091c, B:303:0x0928, B:305:0x0930, B:307:0x0938, B:309:0x093e, B:311:0x094c, B:316:0x095f, B:318:0x0967, B:320:0x096e, B:325:0x0989, B:328:0x0995, B:330:0x099d, B:324:0x0984, B:291:0x08d8, B:265:0x07f8, B:199:0x0679, B:201:0x0681, B:203:0x069f, B:205:0x06a5, B:24:0x0227, B:32:0x0251, B:34:0x0261, B:36:0x026a, B:38:0x0288, B:40:0x028f, B:42:0x029d, B:44:0x02a6, B:46:0x02b8, B:48:0x02c0, B:50:0x02c6, B:52:0x02cc, B:57:0x02d6, B:61:0x02e0, B:63:0x02e8, B:68:0x02f6, B:71:0x0302, B:80:0x031e, B:85:0x032c, B:91:0x033a, B:93:0x034c, B:95:0x0356, B:99:0x035e, B:101:0x036e, B:106:0x0385, B:108:0x0395, B:113:0x03a2, B:115:0x03a6, B:117:0x03b4, B:119:0x03ba, B:123:0x03c2, B:126:0x03c8, B:128:0x03ce, B:139:0x03f5, B:144:0x0404, B:145:0x0406, B:149:0x0410, B:151:0x0429, B:153:0x050f, B:155:0x0552, B:157:0x0556, B:130:0x03da, B:132:0x03e4, B:137:0x03ee, B:83:0x0326, B:78:0x0319, B:75:0x0311, B:29:0x0235, B:321:0x0970), top: B:406:0x000f, inners: #19 }] */
        /* JADX WARN: Removed duplicated region for block: B:374:0x0ad5 A[Catch: Exception -> 0x022d, TryCatch #4 {Exception -> 0x022d, blocks: (B:370:0x0ac0, B:372:0x0ac6, B:375:0x0ae2, B:377:0x0bc5, B:379:0x0be1, B:382:0x0c06, B:387:0x0cd8, B:389:0x0d3a, B:374:0x0ad5, B:266:0x07fe, B:269:0x0806, B:274:0x0812, B:275:0x089b, B:284:0x08b9, B:286:0x08bf, B:288:0x08cf, B:290:0x08d3, B:292:0x08dc, B:293:0x08eb, B:295:0x08f1, B:297:0x0909, B:299:0x090f, B:301:0x091c, B:303:0x0928, B:305:0x0930, B:307:0x0938, B:309:0x093e, B:311:0x094c, B:316:0x095f, B:318:0x0967, B:320:0x096e, B:325:0x0989, B:328:0x0995, B:330:0x099d, B:324:0x0984, B:291:0x08d8, B:265:0x07f8, B:199:0x0679, B:201:0x0681, B:203:0x069f, B:205:0x06a5, B:24:0x0227, B:32:0x0251, B:34:0x0261, B:36:0x026a, B:38:0x0288, B:40:0x028f, B:42:0x029d, B:44:0x02a6, B:46:0x02b8, B:48:0x02c0, B:50:0x02c6, B:52:0x02cc, B:57:0x02d6, B:61:0x02e0, B:63:0x02e8, B:68:0x02f6, B:71:0x0302, B:80:0x031e, B:85:0x032c, B:91:0x033a, B:93:0x034c, B:95:0x0356, B:99:0x035e, B:101:0x036e, B:106:0x0385, B:108:0x0395, B:113:0x03a2, B:115:0x03a6, B:117:0x03b4, B:119:0x03ba, B:123:0x03c2, B:126:0x03c8, B:128:0x03ce, B:139:0x03f5, B:144:0x0404, B:145:0x0406, B:149:0x0410, B:151:0x0429, B:153:0x050f, B:155:0x0552, B:157:0x0556, B:130:0x03da, B:132:0x03e4, B:137:0x03ee, B:83:0x0326, B:78:0x0319, B:75:0x0311, B:29:0x0235, B:321:0x0970), top: B:406:0x000f, inners: #19 }] */
        /* JADX WARN: Removed duplicated region for block: B:377:0x0bc5 A[Catch: Exception -> 0x022d, TryCatch #4 {Exception -> 0x022d, blocks: (B:370:0x0ac0, B:372:0x0ac6, B:375:0x0ae2, B:377:0x0bc5, B:379:0x0be1, B:382:0x0c06, B:387:0x0cd8, B:389:0x0d3a, B:374:0x0ad5, B:266:0x07fe, B:269:0x0806, B:274:0x0812, B:275:0x089b, B:284:0x08b9, B:286:0x08bf, B:288:0x08cf, B:290:0x08d3, B:292:0x08dc, B:293:0x08eb, B:295:0x08f1, B:297:0x0909, B:299:0x090f, B:301:0x091c, B:303:0x0928, B:305:0x0930, B:307:0x0938, B:309:0x093e, B:311:0x094c, B:316:0x095f, B:318:0x0967, B:320:0x096e, B:325:0x0989, B:328:0x0995, B:330:0x099d, B:324:0x0984, B:291:0x08d8, B:265:0x07f8, B:199:0x0679, B:201:0x0681, B:203:0x069f, B:205:0x06a5, B:24:0x0227, B:32:0x0251, B:34:0x0261, B:36:0x026a, B:38:0x0288, B:40:0x028f, B:42:0x029d, B:44:0x02a6, B:46:0x02b8, B:48:0x02c0, B:50:0x02c6, B:52:0x02cc, B:57:0x02d6, B:61:0x02e0, B:63:0x02e8, B:68:0x02f6, B:71:0x0302, B:80:0x031e, B:85:0x032c, B:91:0x033a, B:93:0x034c, B:95:0x0356, B:99:0x035e, B:101:0x036e, B:106:0x0385, B:108:0x0395, B:113:0x03a2, B:115:0x03a6, B:117:0x03b4, B:119:0x03ba, B:123:0x03c2, B:126:0x03c8, B:128:0x03ce, B:139:0x03f5, B:144:0x0404, B:145:0x0406, B:149:0x0410, B:151:0x0429, B:153:0x050f, B:155:0x0552, B:157:0x0556, B:130:0x03da, B:132:0x03e4, B:137:0x03ee, B:83:0x0326, B:78:0x0319, B:75:0x0311, B:29:0x0235, B:321:0x0970), top: B:406:0x000f, inners: #19 }] */
        /* JADX WARN: Removed duplicated region for block: B:381:0x0c03  */
        /* JADX WARN: Removed duplicated region for block: B:384:0x0cd1  */
        /* JADX WARN: Removed duplicated region for block: B:386:0x0cd6  */
        /* JADX WARN: Removed duplicated region for block: B:389:0x0d3a A[Catch: Exception -> 0x022d, TRY_LEAVE, TryCatch #4 {Exception -> 0x022d, blocks: (B:370:0x0ac0, B:372:0x0ac6, B:375:0x0ae2, B:377:0x0bc5, B:379:0x0be1, B:382:0x0c06, B:387:0x0cd8, B:389:0x0d3a, B:374:0x0ad5, B:266:0x07fe, B:269:0x0806, B:274:0x0812, B:275:0x089b, B:284:0x08b9, B:286:0x08bf, B:288:0x08cf, B:290:0x08d3, B:292:0x08dc, B:293:0x08eb, B:295:0x08f1, B:297:0x0909, B:299:0x090f, B:301:0x091c, B:303:0x0928, B:305:0x0930, B:307:0x0938, B:309:0x093e, B:311:0x094c, B:316:0x095f, B:318:0x0967, B:320:0x096e, B:325:0x0989, B:328:0x0995, B:330:0x099d, B:324:0x0984, B:291:0x08d8, B:265:0x07f8, B:199:0x0679, B:201:0x0681, B:203:0x069f, B:205:0x06a5, B:24:0x0227, B:32:0x0251, B:34:0x0261, B:36:0x026a, B:38:0x0288, B:40:0x028f, B:42:0x029d, B:44:0x02a6, B:46:0x02b8, B:48:0x02c0, B:50:0x02c6, B:52:0x02cc, B:57:0x02d6, B:61:0x02e0, B:63:0x02e8, B:68:0x02f6, B:71:0x0302, B:80:0x031e, B:85:0x032c, B:91:0x033a, B:93:0x034c, B:95:0x0356, B:99:0x035e, B:101:0x036e, B:106:0x0385, B:108:0x0395, B:113:0x03a2, B:115:0x03a6, B:117:0x03b4, B:119:0x03ba, B:123:0x03c2, B:126:0x03c8, B:128:0x03ce, B:139:0x03f5, B:144:0x0404, B:145:0x0406, B:149:0x0410, B:151:0x0429, B:153:0x050f, B:155:0x0552, B:157:0x0556, B:130:0x03da, B:132:0x03e4, B:137:0x03ee, B:83:0x0326, B:78:0x0319, B:75:0x0311, B:29:0x0235, B:321:0x0970), top: B:406:0x000f, inners: #19 }] */
        /* JADX WARN: Removed duplicated region for block: B:474:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:55:0x02d2  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x02de  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x02e8 A[Catch: Exception -> 0x022d, TryCatch #4 {Exception -> 0x022d, blocks: (B:370:0x0ac0, B:372:0x0ac6, B:375:0x0ae2, B:377:0x0bc5, B:379:0x0be1, B:382:0x0c06, B:387:0x0cd8, B:389:0x0d3a, B:374:0x0ad5, B:266:0x07fe, B:269:0x0806, B:274:0x0812, B:275:0x089b, B:284:0x08b9, B:286:0x08bf, B:288:0x08cf, B:290:0x08d3, B:292:0x08dc, B:293:0x08eb, B:295:0x08f1, B:297:0x0909, B:299:0x090f, B:301:0x091c, B:303:0x0928, B:305:0x0930, B:307:0x0938, B:309:0x093e, B:311:0x094c, B:316:0x095f, B:318:0x0967, B:320:0x096e, B:325:0x0989, B:328:0x0995, B:330:0x099d, B:324:0x0984, B:291:0x08d8, B:265:0x07f8, B:199:0x0679, B:201:0x0681, B:203:0x069f, B:205:0x06a5, B:24:0x0227, B:32:0x0251, B:34:0x0261, B:36:0x026a, B:38:0x0288, B:40:0x028f, B:42:0x029d, B:44:0x02a6, B:46:0x02b8, B:48:0x02c0, B:50:0x02c6, B:52:0x02cc, B:57:0x02d6, B:61:0x02e0, B:63:0x02e8, B:68:0x02f6, B:71:0x0302, B:80:0x031e, B:85:0x032c, B:91:0x033a, B:93:0x034c, B:95:0x0356, B:99:0x035e, B:101:0x036e, B:106:0x0385, B:108:0x0395, B:113:0x03a2, B:115:0x03a6, B:117:0x03b4, B:119:0x03ba, B:123:0x03c2, B:126:0x03c8, B:128:0x03ce, B:139:0x03f5, B:144:0x0404, B:145:0x0406, B:149:0x0410, B:151:0x0429, B:153:0x050f, B:155:0x0552, B:157:0x0556, B:130:0x03da, B:132:0x03e4, B:137:0x03ee, B:83:0x0326, B:78:0x0319, B:75:0x0311, B:29:0x0235, B:321:0x0970), top: B:406:0x000f, inners: #19 }] */
        /* JADX WARN: Removed duplicated region for block: B:66:0x02f3  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x0338  */
        /* JADX WARN: Removed duplicated region for block: B:93:0x034c A[Catch: Exception -> 0x022d, TryCatch #4 {Exception -> 0x022d, blocks: (B:370:0x0ac0, B:372:0x0ac6, B:375:0x0ae2, B:377:0x0bc5, B:379:0x0be1, B:382:0x0c06, B:387:0x0cd8, B:389:0x0d3a, B:374:0x0ad5, B:266:0x07fe, B:269:0x0806, B:274:0x0812, B:275:0x089b, B:284:0x08b9, B:286:0x08bf, B:288:0x08cf, B:290:0x08d3, B:292:0x08dc, B:293:0x08eb, B:295:0x08f1, B:297:0x0909, B:299:0x090f, B:301:0x091c, B:303:0x0928, B:305:0x0930, B:307:0x0938, B:309:0x093e, B:311:0x094c, B:316:0x095f, B:318:0x0967, B:320:0x096e, B:325:0x0989, B:328:0x0995, B:330:0x099d, B:324:0x0984, B:291:0x08d8, B:265:0x07f8, B:199:0x0679, B:201:0x0681, B:203:0x069f, B:205:0x06a5, B:24:0x0227, B:32:0x0251, B:34:0x0261, B:36:0x026a, B:38:0x0288, B:40:0x028f, B:42:0x029d, B:44:0x02a6, B:46:0x02b8, B:48:0x02c0, B:50:0x02c6, B:52:0x02cc, B:57:0x02d6, B:61:0x02e0, B:63:0x02e8, B:68:0x02f6, B:71:0x0302, B:80:0x031e, B:85:0x032c, B:91:0x033a, B:93:0x034c, B:95:0x0356, B:99:0x035e, B:101:0x036e, B:106:0x0385, B:108:0x0395, B:113:0x03a2, B:115:0x03a6, B:117:0x03b4, B:119:0x03ba, B:123:0x03c2, B:126:0x03c8, B:128:0x03ce, B:139:0x03f5, B:144:0x0404, B:145:0x0406, B:149:0x0410, B:151:0x0429, B:153:0x050f, B:155:0x0552, B:157:0x0556, B:130:0x03da, B:132:0x03e4, B:137:0x03ee, B:83:0x0326, B:78:0x0319, B:75:0x0311, B:29:0x0235, B:321:0x0970), top: B:406:0x000f, inners: #19 }] */
        /* JADX WARN: Removed duplicated region for block: B:98:0x035d  */
        /* JADX WARN: Type inference failed for: r11v12 */
        /* JADX WARN: Type inference failed for: r11v13 */
        /* JADX WARN: Type inference failed for: r11v14 */
        /* JADX WARN: Type inference failed for: r11v15 */
        /* JADX WARN: Type inference failed for: r11v17 */
        /* JADX WARN: Type inference failed for: r11v19 */
        /* JADX WARN: Type inference failed for: r11v23 */
        /* JADX WARN: Type inference failed for: r11v5 */
        /* JADX WARN: Type inference failed for: r11v63 */
        /* JADX WARN: Type inference failed for: r11v64 */
        /* JADX WARN: Type inference failed for: r11v65 */
        /* JADX WARN: Type inference failed for: r11v66 */
        /* JADX WARN: Type inference failed for: r11v67 */
        /* JADX WARN: Type inference failed for: r11v68 */
        /* JADX WARN: Type inference failed for: r11v69 */
        /* JADX WARN: Type inference failed for: r11v70 */
        /* JADX WARN: Type inference failed for: r11v71 */
        /* JADX WARN: Type inference failed for: r12v15 */
        /* JADX WARN: Type inference failed for: r12v16 */
        /* JADX WARN: Type inference failed for: r12v17 */
        /* JADX WARN: Type inference failed for: r12v18 */
        /* JADX WARN: Type inference failed for: r12v19 */
        /* JADX WARN: Type inference failed for: r12v20 */
        /* JADX WARN: Type inference failed for: r12v21 */
        /* JADX WARN: Type inference failed for: r12v22 */
        /* JADX WARN: Type inference failed for: r12v23 */
        /* JADX WARN: Type inference failed for: r12v26 */
        /* JADX WARN: Type inference failed for: r12v27 */
        /* JADX WARN: Type inference failed for: r12v28 */
        /* JADX WARN: Type inference failed for: r12v33 */
        /* JADX WARN: Type inference failed for: r12v4 */
        /* JADX WARN: Type inference failed for: r12v52, types: [int] */
        /* JADX WARN: Type inference failed for: r12v60 */
        /* JADX WARN: Type inference failed for: r12v61 */
        /* JADX WARN: Type inference failed for: r12v62 */
        /* JADX WARN: Type inference failed for: r12v63 */
        /* JADX WARN: Type inference failed for: r12v64 */
        /* JADX WARN: Type inference failed for: r12v65 */
        /* JADX WARN: Type inference failed for: r12v66 */
        /* JADX WARN: Type inference failed for: r12v67 */
        /* JADX WARN: Type inference failed for: r12v68 */
        /* JADX WARN: Type inference failed for: r12v69 */
        /* JADX WARN: Type inference failed for: r12v70 */
        /* JADX WARN: Type inference failed for: r15v9 */
        /* JADX WARN: Type inference failed for: r21v18 */
        /* JADX WARN: Type inference failed for: r21v19 */
        /* JADX WARN: Type inference failed for: r21v28 */
        /* JADX WARN: Type inference failed for: r22v1 */
        /* JADX WARN: Type inference failed for: r22v18 */
        /* JADX WARN: Type inference failed for: r22v2, types: [boolean] */
        /* JADX WARN: Type inference failed for: r22v22 */
        /* JADX WARN: Type inference failed for: r22v23 */
        /* JADX WARN: Type inference failed for: r22v24 */
        /* JADX WARN: Type inference failed for: r22v28 */
        /* JADX WARN: Type inference failed for: r36v13 */
        /* JADX WARN: Type inference failed for: r36v14 */
        /* JADX WARN: Type inference failed for: r36v15 */
        /* JADX WARN: Type inference failed for: r36v16 */
        /* JADX WARN: Type inference failed for: r36v17 */
        /* JADX WARN: Type inference failed for: r36v18 */
        /* JADX WARN: Type inference failed for: r36v19 */
        /* JADX WARN: Type inference failed for: r36v2 */
        /* JADX WARN: Type inference failed for: r36v20 */
        /* JADX WARN: Type inference failed for: r36v21 */
        /* JADX WARN: Type inference failed for: r36v22 */
        /* JADX WARN: Type inference failed for: r36v3 */
        /* JADX WARN: Type inference failed for: r36v4 */
        /* JADX WARN: Type inference failed for: r36v5 */
        /* JADX WARN: Type inference failed for: r36v6 */
        /* JADX WARN: Type inference failed for: r36v8 */
        /* JADX WARN: Type inference failed for: r7v12 */
        /* JADX WARN: Type inference failed for: r7v16 */
        /* JADX WARN: Type inference failed for: r7v20 */
        /* JADX WARN: Type inference failed for: r7v29 */
        /* JADX WARN: Type inference failed for: r7v68 */
        /* JADX WARN: Type inference failed for: r7v69 */
        /* JADX WARN: Type inference failed for: r7v70 */
        /* JADX WARN: Type inference failed for: r8v11 */
        /* JADX WARN: Type inference failed for: r8v20 */
        /* JADX WARN: Type inference failed for: r8v24 */
        /* JADX WARN: Type inference failed for: r8v7 */
        /* JADX WARN: Type inference failed for: r8v75 */
        /* JADX WARN: Type inference failed for: r8v76 */
        /* JADX WARN: Type inference failed for: r8v77 */
        /* JADX WARN: Type inference failed for: r8v8 */
        /* JADX WARN: Type inference failed for: r9v10 */
        /* JADX WARN: Type inference failed for: r9v15 */
        /* JADX WARN: Type inference failed for: r9v18 */
        /* JADX WARN: Type inference failed for: r9v21 */
        /* JADX WARN: Type inference failed for: r9v22 */
        /* JADX WARN: Type inference failed for: r9v23 */
        /* JADX WARN: Type inference failed for: r9v24 */
        /* JADX WARN: Type inference failed for: r9v30 */
        /* JADX WARN: Type inference failed for: r9v49, types: [int] */
        /* JADX WARN: Type inference failed for: r9v5 */
        /* JADX WARN: Type inference failed for: r9v54 */
        /* JADX WARN: Type inference failed for: r9v55 */
        /* JADX WARN: Type inference failed for: r9v56 */
        /* JADX WARN: Type inference failed for: r9v57 */
        /* JADX WARN: Type inference failed for: r9v58 */
        /* JADX WARN: Type inference failed for: r9v59 */
        /* JADX WARN: Type inference failed for: r9v6 */
        /* JADX WARN: Type inference failed for: r9v7 */
        /* JADX WARN: Type inference failed for: r9v9 */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r120) {
            /*
                Method dump skipped, instruction units count: 3549
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13181.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C13181) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadFilteredTmdbReviews$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel", f = "DetailsViewModel.kt", l = {2705}, m = "loadFilteredTmdbReviews", v = 2)
    public static final class C13191 extends f7.c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C13191(d7.d<? super C13191> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DetailsViewModel.this.loadFilteredTmdbReviews(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadPerson$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadPerson$1", f = "DetailsViewModel.kt", l = {1458}, m = "invokeSuspend", v = 2)
    public static final class C13201 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ int $personId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13201(int i10, d7.d<? super C13201> dVar) {
            super(2, dVar);
            this.$personId = i10;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return DetailsViewModel.this.new C13201(this.$personId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object personDetails;
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    DetailsViewModel.this._uiState.setValue(DetailsUiState.copy$default((DetailsUiState) DetailsViewModel.this._uiState.getValue(), false, null, null, null, null, null, null, 0, 0, null, null, null, null, null, true, null, true, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -114689, 1048575, null));
                    MediaRepository mediaRepository = DetailsViewModel.this.mediaRepository;
                    int i11 = this.$personId;
                    this.label = 1;
                    personDetails = mediaRepository.getPersonDetails(i11, this);
                    e7.a aVar = e7.a.f15033i;
                    if (personDetails == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    personDetails = obj;
                }
                DetailsViewModel.this._uiState.setValue(DetailsUiState.copy$default((DetailsUiState) DetailsViewModel.this._uiState.getValue(), false, null, null, null, null, null, null, 0, 0, null, null, null, null, null, false, (PersonDetails) personDetails, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -98305, 1048575, null));
            } catch (Exception unused) {
                DetailsViewModel.this._uiState.setValue(DetailsUiState.copy$default((DetailsUiState) DetailsViewModel.this._uiState.getValue(), false, null, null, null, null, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -65537, 1048575, null));
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C13201) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadSeason$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadSeason$1", f = "DetailsViewModel.kt", l = {1002, 1004, AnalyticsListener.EVENT_VIDEO_INPUT_FORMAT_CHANGED}, m = "invokeSuspend", v = 2)
    public static final class C13211 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ int $seasonNumber;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13211(int i10, d7.d<? super C13211> dVar) {
            super(2, dVar);
            this.$seasonNumber = i10;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C13211 c13211 = DetailsViewModel.this.new C13211(this.$seasonNumber, dVar);
            c13211.L$0 = obj;
            return c13211;
        }

        /* JADX WARN: Code restructure failed: missing block: B:41:0x00fb, code lost:
        
            if (r0 == r10) goto L42;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r16v1 */
        /* JADX WARN: Type inference failed for: r16v2 */
        /* JADX WARN: Type inference failed for: r16v4, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r16v5 */
        /* JADX WARN: Type inference failed for: r3v0, types: [int] */
        /* JADX WARN: Type inference failed for: r3v19 */
        /* JADX WARN: Type inference failed for: r3v2 */
        /* JADX WARN: Type inference failed for: r3v22 */
        /* JADX WARN: Type inference failed for: r3v23 */
        /* JADX WARN: Type inference failed for: r3v24 */
        /* JADX WARN: Type inference failed for: r3v25 */
        /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Iterable] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r67) {
            /*
                Method dump skipped, instruction units count: 968
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13211.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C13211) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadStreams$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadStreams$1", f = "DetailsViewModel.kt", l = {1639, 1653, 1683, 1773, 1802, 1812, 1852, 1874}, m = "invokeSuspend", v = 2)
    public static final class C13221 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ EpisodeIdentity $identity;
        final /* synthetic */ String $imdbId;
        final /* synthetic */ long $requestId;
        final /* synthetic */ int $requestMediaId;
        final /* synthetic */ MediaType $requestMediaType;
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        final /* synthetic */ DetailsViewModel this$0;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadStreams$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadStreams$1$1", f = "DetailsViewModel.kt", l = {1641}, m = "invokeSuspend", v = 2)
        public static final class C01391 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ kotlin.jvm.internal.k0 $currentImdbId;
            final /* synthetic */ long $requestId;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01391(kotlin.jvm.internal.k0 k0Var, DetailsViewModel detailsViewModel, long j10, MediaType mediaType, int i10, d7.d<? super C01391> dVar) {
                super(2, dVar);
                this.$currentImdbId = k0Var;
                this.this$0 = detailsViewModel;
                this.$requestId = j10;
                this.$requestMediaType = mediaType;
                this.$requestMediaId = i10;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                return new C01391(this.$currentImdbId, this.this$0, this.$requestId, this.$requestMediaType, this.$requestMediaId, dVar);
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x001e  */
            /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
            /* JADX WARN: Removed duplicated region for block: B:17:0x003e A[RETURN] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x003c -> B:18:0x003f). Please report as a decompilation issue!!! */
            /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r6) {
                /*
                    r5 = this;
                    int r0 = r5.label
                    r1 = 1
                    if (r0 == 0) goto L13
                    if (r0 != r1) goto Lb
                    k2.c.G(r6)
                    goto L3f
                Lb:
                    java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r6.<init>(r0)
                    throw r6
                L13:
                    k2.c.G(r6)
                L16:
                    kotlin.jvm.internal.k0 r6 = r5.$currentImdbId
                    java.lang.Object r6 = r6.f19746i
                    java.lang.CharSequence r6 = (java.lang.CharSequence) r6
                    if (r6 == 0) goto L24
                    boolean r6 = kotlin.text.o.h0(r6)
                    if (r6 == 0) goto L54
                L24:
                    long r2 = r5.$requestId
                    com.arflix.tv.ui.screens.details.DetailsViewModel r6 = r5.this$0
                    com.arflix.tv.data.model.MediaType r0 = r5.$requestMediaType
                    int r4 = r5.$requestMediaId
                    boolean r6 = com.arflix.tv.ui.screens.details.DetailsViewModel.C13221.access$invokeSuspend$isCurrentRequest(r2, r6, r0, r4)
                    if (r6 == 0) goto L54
                    r5.label = r1
                    r2 = 200(0xc8, double:9.9E-322)
                    java.lang.Object r6 = ka.s0.a(r2, r5)
                    e7.a r0 = e7.a.f15033i
                    if (r6 != r0) goto L3f
                    return r0
                L3f:
                    kotlin.jvm.internal.k0 r6 = r5.$currentImdbId
                    com.arflix.tv.ui.screens.details.DetailsViewModel r0 = r5.this$0
                    na.q0 r0 = com.arflix.tv.ui.screens.details.DetailsViewModel.access$get_uiState$p(r0)
                    java.lang.Object r0 = r0.getValue()
                    com.arflix.tv.ui.screens.details.DetailsUiState r0 = (com.arflix.tv.ui.screens.details.DetailsUiState) r0
                    java.lang.String r0 = r0.getImdbId()
                    r6.f19746i = r0
                    goto L16
                L54:
                    x6.t0 r6 = x6.t0.f22605a
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13221.C01391.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((C01391) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadStreams$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadStreams$1$2", f = "DetailsViewModel.kt", l = {1686}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ Integer $canonicalEpisode;
            final /* synthetic */ Integer $canonicalSeason;
            final /* synthetic */ long $requestId;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            final /* synthetic */ String $resolvedImdbId;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(DetailsViewModel detailsViewModel, String str, Integer num, Integer num2, long j10, MediaType mediaType, int i10, d7.d<? super AnonymousClass2> dVar) {
                super(2, dVar);
                this.this$0 = detailsViewModel;
                this.$resolvedImdbId = str;
                this.$canonicalSeason = num;
                this.$canonicalEpisode = num2;
                this.$requestId = j10;
                this.$requestMediaType = mediaType;
                this.$requestMediaId = i10;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass2(this.this$0, this.$resolvedImdbId, this.$canonicalSeason, this.$canonicalEpisode, this.$requestId, this.$requestMediaType, this.$requestMediaId, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    DetailsViewModel detailsViewModel = this.this$0;
                    String str = this.$resolvedImdbId;
                    Integer num = this.$canonicalSeason;
                    Integer num2 = this.$canonicalEpisode;
                    long j10 = this.$requestId;
                    MediaType mediaType = this.$requestMediaType;
                    int i11 = this.$requestMediaId;
                    this.label = 1;
                    Object objAppendHomeServerSourcesInBackground = detailsViewModel.appendHomeServerSourcesInBackground(str, num, num2, 5000L, j10, mediaType, i11, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objAppendHomeServerSourcesInBackground == aVar) {
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

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadStreams$1$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadStreams$1$3", f = "DetailsViewModel.kt", l = {1701}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass3 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ Integer $canonicalEpisode;
            final /* synthetic */ Integer $canonicalSeason;
            final /* synthetic */ long $requestId;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            final /* synthetic */ String $resolvedImdbId;
            long J$0;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(DetailsViewModel detailsViewModel, String str, Integer num, Integer num2, long j10, MediaType mediaType, int i10, d7.d<? super AnonymousClass3> dVar) {
                super(2, dVar);
                this.this$0 = detailsViewModel;
                this.$resolvedImdbId = str;
                this.$canonicalSeason = num;
                this.$canonicalEpisode = num2;
                this.$requestId = j10;
                this.$requestMediaType = mediaType;
                this.$requestMediaId = i10;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass3(this.this$0, this.$resolvedImdbId, this.$canonicalSeason, this.$canonicalEpisode, this.$requestId, this.$requestMediaType, this.$requestMediaId, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    long j10 = this.this$0.currentMediaType == MediaType.MOVIE ? 30000L : 45000L;
                    DetailsViewModel detailsViewModel = this.this$0;
                    String str = this.$resolvedImdbId;
                    Integer num = this.$canonicalSeason;
                    Integer num2 = this.$canonicalEpisode;
                    long j11 = this.$requestId;
                    MediaType mediaType = this.$requestMediaType;
                    int i11 = this.$requestMediaId;
                    this.J$0 = j10;
                    this.label = 1;
                    Object objAppendVodSourceInBackground = detailsViewModel.appendVodSourceInBackground(str, num, num2, j10, j11, mediaType, i11, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objAppendVodSourceInBackground == aVar) {
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

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$loadStreams$1$4, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$loadStreams$1$4", f = "DetailsViewModel.kt", l = {1723}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass4 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ Integer $canonicalEpisode;
            final /* synthetic */ Integer $canonicalSeason;
            final /* synthetic */ long $requestId;
            final /* synthetic */ int $requestMediaId;
            final /* synthetic */ MediaType $requestMediaType;
            Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ DetailsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass4(DetailsViewModel detailsViewModel, int i10, MediaType mediaType, Integer num, Integer num2, long j10, d7.d<? super AnonymousClass4> dVar) {
                super(2, dVar);
                this.this$0 = detailsViewModel;
                this.$requestMediaId = i10;
                this.$requestMediaType = mediaType;
                this.$canonicalSeason = num;
                this.$canonicalEpisode = num2;
                this.$requestId = j10;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass4(this.this$0, this.$requestMediaId, this.$requestMediaType, this.$canonicalSeason, this.$canonicalEpisode, this.$requestId, dVar);
            }

            /* JADX WARN: Removed duplicated region for block: B:61:0x016a A[PHI: r0
              0x016a: PHI (r0v9 com.arflix.tv.ui.screens.details.DetailsUiState) = 
              (r0v8 com.arflix.tv.ui.screens.details.DetailsUiState)
              (r0v8 com.arflix.tv.ui.screens.details.DetailsUiState)
              (r0v8 com.arflix.tv.ui.screens.details.DetailsUiState)
              (r0v23 com.arflix.tv.ui.screens.details.DetailsUiState)
              (r0v23 com.arflix.tv.ui.screens.details.DetailsUiState)
              (r0v23 com.arflix.tv.ui.screens.details.DetailsUiState)
             binds: [B:81:0x022f, B:83:0x0239, B:84:0x023b, B:56:0x0159, B:58:0x0163, B:59:0x0165] A[DONT_GENERATE, DONT_INLINE]] */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r87) {
                /*
                    Method dump skipped, instruction units count: 775
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13221.AnonymousClass4.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass4) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13221(String str, DetailsViewModel detailsViewModel, MediaType mediaType, int i10, long j10, EpisodeIdentity episodeIdentity, d7.d<? super C13221> dVar) {
            super(2, dVar);
            this.$imdbId = str;
            this.this$0 = detailsViewModel;
            this.$requestMediaType = mediaType;
            this.$requestMediaId = i10;
            this.$requestId = j10;
            this.$identity = episodeIdentity;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invokeSuspend$isCurrentRequest(long j10, DetailsViewModel detailsViewModel, MediaType mediaType, int i10) {
            return j10 == detailsViewModel.loadStreamsRequestId && detailsViewModel.currentMediaType == mediaType && detailsViewModel.currentMediaId == i10;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return new C13221(this.$imdbId, this.this$0, this.$requestMediaType, this.$requestMediaId, this.$requestId, this.$identity, dVar);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:101:0x0420 A[Catch: Exception -> 0x0402, TryCatch #0 {Exception -> 0x0402, blocks: (B:200:0x07e9, B:202:0x07ef, B:220:0x0826, B:205:0x07fa, B:206:0x07ff, B:208:0x0805, B:210:0x0811, B:213:0x0816, B:214:0x081b, B:167:0x06dd, B:169:0x06e3, B:187:0x071a, B:172:0x06ee, B:173:0x06f3, B:175:0x06f9, B:177:0x0705, B:180:0x070a, B:181:0x070f, B:120:0x0531, B:121:0x053c, B:123:0x0542, B:125:0x054f, B:126:0x0553, B:141:0x0586, B:143:0x05e2, B:145:0x05e9, B:148:0x05f3, B:150:0x05fb, B:156:0x060a, B:158:0x0610, B:160:0x0619, B:163:0x0664, B:130:0x0560, B:131:0x0565, B:133:0x056b, B:135:0x0579, B:138:0x057e, B:139:0x0583, B:111:0x0467, B:113:0x049c, B:114:0x049f, B:116:0x04ff, B:190:0x078e, B:196:0x079b, B:222:0x0898, B:224:0x089c, B:225:0x08b0, B:227:0x08b6, B:231:0x08c9, B:233:0x08cd, B:236:0x08d5, B:240:0x08e0, B:242:0x08e8, B:245:0x08f3, B:247:0x08fc, B:249:0x0918, B:254:0x0924, B:83:0x03e6, B:85:0x03f8, B:93:0x0408, B:95:0x040e, B:97:0x0412, B:99:0x041c, B:101:0x0420, B:103:0x042a, B:105:0x042e, B:107:0x0434), top: B:265:0x03e6 }] */
        /* JADX WARN: Removed duplicated region for block: B:102:0x0429  */
        /* JADX WARN: Removed duplicated region for block: B:105:0x042e A[Catch: Exception -> 0x0402, TryCatch #0 {Exception -> 0x0402, blocks: (B:200:0x07e9, B:202:0x07ef, B:220:0x0826, B:205:0x07fa, B:206:0x07ff, B:208:0x0805, B:210:0x0811, B:213:0x0816, B:214:0x081b, B:167:0x06dd, B:169:0x06e3, B:187:0x071a, B:172:0x06ee, B:173:0x06f3, B:175:0x06f9, B:177:0x0705, B:180:0x070a, B:181:0x070f, B:120:0x0531, B:121:0x053c, B:123:0x0542, B:125:0x054f, B:126:0x0553, B:141:0x0586, B:143:0x05e2, B:145:0x05e9, B:148:0x05f3, B:150:0x05fb, B:156:0x060a, B:158:0x0610, B:160:0x0619, B:163:0x0664, B:130:0x0560, B:131:0x0565, B:133:0x056b, B:135:0x0579, B:138:0x057e, B:139:0x0583, B:111:0x0467, B:113:0x049c, B:114:0x049f, B:116:0x04ff, B:190:0x078e, B:196:0x079b, B:222:0x0898, B:224:0x089c, B:225:0x08b0, B:227:0x08b6, B:231:0x08c9, B:233:0x08cd, B:236:0x08d5, B:240:0x08e0, B:242:0x08e8, B:245:0x08f3, B:247:0x08fc, B:249:0x0918, B:254:0x0924, B:83:0x03e6, B:85:0x03f8, B:93:0x0408, B:95:0x040e, B:97:0x0412, B:99:0x041c, B:101:0x0420, B:103:0x042a, B:105:0x042e, B:107:0x0434), top: B:265:0x03e6 }] */
        /* JADX WARN: Removed duplicated region for block: B:106:0x0433  */
        /* JADX WARN: Removed duplicated region for block: B:109:0x045c  */
        /* JADX WARN: Removed duplicated region for block: B:110:0x045e  */
        /* JADX WARN: Removed duplicated region for block: B:113:0x049c A[Catch: Exception -> 0x0402, TryCatch #0 {Exception -> 0x0402, blocks: (B:200:0x07e9, B:202:0x07ef, B:220:0x0826, B:205:0x07fa, B:206:0x07ff, B:208:0x0805, B:210:0x0811, B:213:0x0816, B:214:0x081b, B:167:0x06dd, B:169:0x06e3, B:187:0x071a, B:172:0x06ee, B:173:0x06f3, B:175:0x06f9, B:177:0x0705, B:180:0x070a, B:181:0x070f, B:120:0x0531, B:121:0x053c, B:123:0x0542, B:125:0x054f, B:126:0x0553, B:141:0x0586, B:143:0x05e2, B:145:0x05e9, B:148:0x05f3, B:150:0x05fb, B:156:0x060a, B:158:0x0610, B:160:0x0619, B:163:0x0664, B:130:0x0560, B:131:0x0565, B:133:0x056b, B:135:0x0579, B:138:0x057e, B:139:0x0583, B:111:0x0467, B:113:0x049c, B:114:0x049f, B:116:0x04ff, B:190:0x078e, B:196:0x079b, B:222:0x0898, B:224:0x089c, B:225:0x08b0, B:227:0x08b6, B:231:0x08c9, B:233:0x08cd, B:236:0x08d5, B:240:0x08e0, B:242:0x08e8, B:245:0x08f3, B:247:0x08fc, B:249:0x0918, B:254:0x0924, B:83:0x03e6, B:85:0x03f8, B:93:0x0408, B:95:0x040e, B:97:0x0412, B:99:0x041c, B:101:0x0420, B:103:0x042a, B:105:0x042e, B:107:0x0434), top: B:265:0x03e6 }] */
        /* JADX WARN: Removed duplicated region for block: B:116:0x04ff A[Catch: Exception -> 0x0402, TryCatch #0 {Exception -> 0x0402, blocks: (B:200:0x07e9, B:202:0x07ef, B:220:0x0826, B:205:0x07fa, B:206:0x07ff, B:208:0x0805, B:210:0x0811, B:213:0x0816, B:214:0x081b, B:167:0x06dd, B:169:0x06e3, B:187:0x071a, B:172:0x06ee, B:173:0x06f3, B:175:0x06f9, B:177:0x0705, B:180:0x070a, B:181:0x070f, B:120:0x0531, B:121:0x053c, B:123:0x0542, B:125:0x054f, B:126:0x0553, B:141:0x0586, B:143:0x05e2, B:145:0x05e9, B:148:0x05f3, B:150:0x05fb, B:156:0x060a, B:158:0x0610, B:160:0x0619, B:163:0x0664, B:130:0x0560, B:131:0x0565, B:133:0x056b, B:135:0x0579, B:138:0x057e, B:139:0x0583, B:111:0x0467, B:113:0x049c, B:114:0x049f, B:116:0x04ff, B:190:0x078e, B:196:0x079b, B:222:0x0898, B:224:0x089c, B:225:0x08b0, B:227:0x08b6, B:231:0x08c9, B:233:0x08cd, B:236:0x08d5, B:240:0x08e0, B:242:0x08e8, B:245:0x08f3, B:247:0x08fc, B:249:0x0918, B:254:0x0924, B:83:0x03e6, B:85:0x03f8, B:93:0x0408, B:95:0x040e, B:97:0x0412, B:99:0x041c, B:101:0x0420, B:103:0x042a, B:105:0x042e, B:107:0x0434), top: B:265:0x03e6 }] */
        /* JADX WARN: Removed duplicated region for block: B:123:0x0542 A[Catch: Exception -> 0x0402, TryCatch #0 {Exception -> 0x0402, blocks: (B:200:0x07e9, B:202:0x07ef, B:220:0x0826, B:205:0x07fa, B:206:0x07ff, B:208:0x0805, B:210:0x0811, B:213:0x0816, B:214:0x081b, B:167:0x06dd, B:169:0x06e3, B:187:0x071a, B:172:0x06ee, B:173:0x06f3, B:175:0x06f9, B:177:0x0705, B:180:0x070a, B:181:0x070f, B:120:0x0531, B:121:0x053c, B:123:0x0542, B:125:0x054f, B:126:0x0553, B:141:0x0586, B:143:0x05e2, B:145:0x05e9, B:148:0x05f3, B:150:0x05fb, B:156:0x060a, B:158:0x0610, B:160:0x0619, B:163:0x0664, B:130:0x0560, B:131:0x0565, B:133:0x056b, B:135:0x0579, B:138:0x057e, B:139:0x0583, B:111:0x0467, B:113:0x049c, B:114:0x049f, B:116:0x04ff, B:190:0x078e, B:196:0x079b, B:222:0x0898, B:224:0x089c, B:225:0x08b0, B:227:0x08b6, B:231:0x08c9, B:233:0x08cd, B:236:0x08d5, B:240:0x08e0, B:242:0x08e8, B:245:0x08f3, B:247:0x08fc, B:249:0x0918, B:254:0x0924, B:83:0x03e6, B:85:0x03f8, B:93:0x0408, B:95:0x040e, B:97:0x0412, B:99:0x041c, B:101:0x0420, B:103:0x042a, B:105:0x042e, B:107:0x0434), top: B:265:0x03e6 }] */
        /* JADX WARN: Removed duplicated region for block: B:128:0x055d  */
        /* JADX WARN: Removed duplicated region for block: B:130:0x0560 A[Catch: Exception -> 0x0402, TryCatch #0 {Exception -> 0x0402, blocks: (B:200:0x07e9, B:202:0x07ef, B:220:0x0826, B:205:0x07fa, B:206:0x07ff, B:208:0x0805, B:210:0x0811, B:213:0x0816, B:214:0x081b, B:167:0x06dd, B:169:0x06e3, B:187:0x071a, B:172:0x06ee, B:173:0x06f3, B:175:0x06f9, B:177:0x0705, B:180:0x070a, B:181:0x070f, B:120:0x0531, B:121:0x053c, B:123:0x0542, B:125:0x054f, B:126:0x0553, B:141:0x0586, B:143:0x05e2, B:145:0x05e9, B:148:0x05f3, B:150:0x05fb, B:156:0x060a, B:158:0x0610, B:160:0x0619, B:163:0x0664, B:130:0x0560, B:131:0x0565, B:133:0x056b, B:135:0x0579, B:138:0x057e, B:139:0x0583, B:111:0x0467, B:113:0x049c, B:114:0x049f, B:116:0x04ff, B:190:0x078e, B:196:0x079b, B:222:0x0898, B:224:0x089c, B:225:0x08b0, B:227:0x08b6, B:231:0x08c9, B:233:0x08cd, B:236:0x08d5, B:240:0x08e0, B:242:0x08e8, B:245:0x08f3, B:247:0x08fc, B:249:0x0918, B:254:0x0924, B:83:0x03e6, B:85:0x03f8, B:93:0x0408, B:95:0x040e, B:97:0x0412, B:99:0x041c, B:101:0x0420, B:103:0x042a, B:105:0x042e, B:107:0x0434), top: B:265:0x03e6 }] */
        /* JADX WARN: Removed duplicated region for block: B:143:0x05e2 A[Catch: Exception -> 0x0402, TryCatch #0 {Exception -> 0x0402, blocks: (B:200:0x07e9, B:202:0x07ef, B:220:0x0826, B:205:0x07fa, B:206:0x07ff, B:208:0x0805, B:210:0x0811, B:213:0x0816, B:214:0x081b, B:167:0x06dd, B:169:0x06e3, B:187:0x071a, B:172:0x06ee, B:173:0x06f3, B:175:0x06f9, B:177:0x0705, B:180:0x070a, B:181:0x070f, B:120:0x0531, B:121:0x053c, B:123:0x0542, B:125:0x054f, B:126:0x0553, B:141:0x0586, B:143:0x05e2, B:145:0x05e9, B:148:0x05f3, B:150:0x05fb, B:156:0x060a, B:158:0x0610, B:160:0x0619, B:163:0x0664, B:130:0x0560, B:131:0x0565, B:133:0x056b, B:135:0x0579, B:138:0x057e, B:139:0x0583, B:111:0x0467, B:113:0x049c, B:114:0x049f, B:116:0x04ff, B:190:0x078e, B:196:0x079b, B:222:0x0898, B:224:0x089c, B:225:0x08b0, B:227:0x08b6, B:231:0x08c9, B:233:0x08cd, B:236:0x08d5, B:240:0x08e0, B:242:0x08e8, B:245:0x08f3, B:247:0x08fc, B:249:0x0918, B:254:0x0924, B:83:0x03e6, B:85:0x03f8, B:93:0x0408, B:95:0x040e, B:97:0x0412, B:99:0x041c, B:101:0x0420, B:103:0x042a, B:105:0x042e, B:107:0x0434), top: B:265:0x03e6 }] */
        /* JADX WARN: Removed duplicated region for block: B:165:0x06d0  */
        /* JADX WARN: Removed duplicated region for block: B:166:0x06d2  */
        /* JADX WARN: Removed duplicated region for block: B:172:0x06ee A[Catch: Exception -> 0x0402, TryCatch #0 {Exception -> 0x0402, blocks: (B:200:0x07e9, B:202:0x07ef, B:220:0x0826, B:205:0x07fa, B:206:0x07ff, B:208:0x0805, B:210:0x0811, B:213:0x0816, B:214:0x081b, B:167:0x06dd, B:169:0x06e3, B:187:0x071a, B:172:0x06ee, B:173:0x06f3, B:175:0x06f9, B:177:0x0705, B:180:0x070a, B:181:0x070f, B:120:0x0531, B:121:0x053c, B:123:0x0542, B:125:0x054f, B:126:0x0553, B:141:0x0586, B:143:0x05e2, B:145:0x05e9, B:148:0x05f3, B:150:0x05fb, B:156:0x060a, B:158:0x0610, B:160:0x0619, B:163:0x0664, B:130:0x0560, B:131:0x0565, B:133:0x056b, B:135:0x0579, B:138:0x057e, B:139:0x0583, B:111:0x0467, B:113:0x049c, B:114:0x049f, B:116:0x04ff, B:190:0x078e, B:196:0x079b, B:222:0x0898, B:224:0x089c, B:225:0x08b0, B:227:0x08b6, B:231:0x08c9, B:233:0x08cd, B:236:0x08d5, B:240:0x08e0, B:242:0x08e8, B:245:0x08f3, B:247:0x08fc, B:249:0x0918, B:254:0x0924, B:83:0x03e6, B:85:0x03f8, B:93:0x0408, B:95:0x040e, B:97:0x0412, B:99:0x041c, B:101:0x0420, B:103:0x042a, B:105:0x042e, B:107:0x0434), top: B:265:0x03e6 }] */
        /* JADX WARN: Removed duplicated region for block: B:175:0x06f9 A[Catch: Exception -> 0x0402, TryCatch #0 {Exception -> 0x0402, blocks: (B:200:0x07e9, B:202:0x07ef, B:220:0x0826, B:205:0x07fa, B:206:0x07ff, B:208:0x0805, B:210:0x0811, B:213:0x0816, B:214:0x081b, B:167:0x06dd, B:169:0x06e3, B:187:0x071a, B:172:0x06ee, B:173:0x06f3, B:175:0x06f9, B:177:0x0705, B:180:0x070a, B:181:0x070f, B:120:0x0531, B:121:0x053c, B:123:0x0542, B:125:0x054f, B:126:0x0553, B:141:0x0586, B:143:0x05e2, B:145:0x05e9, B:148:0x05f3, B:150:0x05fb, B:156:0x060a, B:158:0x0610, B:160:0x0619, B:163:0x0664, B:130:0x0560, B:131:0x0565, B:133:0x056b, B:135:0x0579, B:138:0x057e, B:139:0x0583, B:111:0x0467, B:113:0x049c, B:114:0x049f, B:116:0x04ff, B:190:0x078e, B:196:0x079b, B:222:0x0898, B:224:0x089c, B:225:0x08b0, B:227:0x08b6, B:231:0x08c9, B:233:0x08cd, B:236:0x08d5, B:240:0x08e0, B:242:0x08e8, B:245:0x08f3, B:247:0x08fc, B:249:0x0918, B:254:0x0924, B:83:0x03e6, B:85:0x03f8, B:93:0x0408, B:95:0x040e, B:97:0x0412, B:99:0x041c, B:101:0x0420, B:103:0x042a, B:105:0x042e, B:107:0x0434), top: B:265:0x03e6 }] */
        /* JADX WARN: Removed duplicated region for block: B:186:0x0718  */
        /* JADX WARN: Removed duplicated region for block: B:189:0x078c  */
        /* JADX WARN: Removed duplicated region for block: B:205:0x07fa A[Catch: Exception -> 0x0402, TryCatch #0 {Exception -> 0x0402, blocks: (B:200:0x07e9, B:202:0x07ef, B:220:0x0826, B:205:0x07fa, B:206:0x07ff, B:208:0x0805, B:210:0x0811, B:213:0x0816, B:214:0x081b, B:167:0x06dd, B:169:0x06e3, B:187:0x071a, B:172:0x06ee, B:173:0x06f3, B:175:0x06f9, B:177:0x0705, B:180:0x070a, B:181:0x070f, B:120:0x0531, B:121:0x053c, B:123:0x0542, B:125:0x054f, B:126:0x0553, B:141:0x0586, B:143:0x05e2, B:145:0x05e9, B:148:0x05f3, B:150:0x05fb, B:156:0x060a, B:158:0x0610, B:160:0x0619, B:163:0x0664, B:130:0x0560, B:131:0x0565, B:133:0x056b, B:135:0x0579, B:138:0x057e, B:139:0x0583, B:111:0x0467, B:113:0x049c, B:114:0x049f, B:116:0x04ff, B:190:0x078e, B:196:0x079b, B:222:0x0898, B:224:0x089c, B:225:0x08b0, B:227:0x08b6, B:231:0x08c9, B:233:0x08cd, B:236:0x08d5, B:240:0x08e0, B:242:0x08e8, B:245:0x08f3, B:247:0x08fc, B:249:0x0918, B:254:0x0924, B:83:0x03e6, B:85:0x03f8, B:93:0x0408, B:95:0x040e, B:97:0x0412, B:99:0x041c, B:101:0x0420, B:103:0x042a, B:105:0x042e, B:107:0x0434), top: B:265:0x03e6 }] */
        /* JADX WARN: Removed duplicated region for block: B:208:0x0805 A[Catch: Exception -> 0x0402, TryCatch #0 {Exception -> 0x0402, blocks: (B:200:0x07e9, B:202:0x07ef, B:220:0x0826, B:205:0x07fa, B:206:0x07ff, B:208:0x0805, B:210:0x0811, B:213:0x0816, B:214:0x081b, B:167:0x06dd, B:169:0x06e3, B:187:0x071a, B:172:0x06ee, B:173:0x06f3, B:175:0x06f9, B:177:0x0705, B:180:0x070a, B:181:0x070f, B:120:0x0531, B:121:0x053c, B:123:0x0542, B:125:0x054f, B:126:0x0553, B:141:0x0586, B:143:0x05e2, B:145:0x05e9, B:148:0x05f3, B:150:0x05fb, B:156:0x060a, B:158:0x0610, B:160:0x0619, B:163:0x0664, B:130:0x0560, B:131:0x0565, B:133:0x056b, B:135:0x0579, B:138:0x057e, B:139:0x0583, B:111:0x0467, B:113:0x049c, B:114:0x049f, B:116:0x04ff, B:190:0x078e, B:196:0x079b, B:222:0x0898, B:224:0x089c, B:225:0x08b0, B:227:0x08b6, B:231:0x08c9, B:233:0x08cd, B:236:0x08d5, B:240:0x08e0, B:242:0x08e8, B:245:0x08f3, B:247:0x08fc, B:249:0x0918, B:254:0x0924, B:83:0x03e6, B:85:0x03f8, B:93:0x0408, B:95:0x040e, B:97:0x0412, B:99:0x041c, B:101:0x0420, B:103:0x042a, B:105:0x042e, B:107:0x0434), top: B:265:0x03e6 }] */
        /* JADX WARN: Removed duplicated region for block: B:219:0x0824  */
        /* JADX WARN: Removed duplicated region for block: B:239:0x08de  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0243  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x024b  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x0266  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x027c  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x02a2 A[LOOP:6: B:67:0x029c->B:69:0x02a2, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:72:0x03a4  */
        /* JADX WARN: Removed duplicated region for block: B:93:0x0408 A[Catch: Exception -> 0x0402, TryCatch #0 {Exception -> 0x0402, blocks: (B:200:0x07e9, B:202:0x07ef, B:220:0x0826, B:205:0x07fa, B:206:0x07ff, B:208:0x0805, B:210:0x0811, B:213:0x0816, B:214:0x081b, B:167:0x06dd, B:169:0x06e3, B:187:0x071a, B:172:0x06ee, B:173:0x06f3, B:175:0x06f9, B:177:0x0705, B:180:0x070a, B:181:0x070f, B:120:0x0531, B:121:0x053c, B:123:0x0542, B:125:0x054f, B:126:0x0553, B:141:0x0586, B:143:0x05e2, B:145:0x05e9, B:148:0x05f3, B:150:0x05fb, B:156:0x060a, B:158:0x0610, B:160:0x0619, B:163:0x0664, B:130:0x0560, B:131:0x0565, B:133:0x056b, B:135:0x0579, B:138:0x057e, B:139:0x0583, B:111:0x0467, B:113:0x049c, B:114:0x049f, B:116:0x04ff, B:190:0x078e, B:196:0x079b, B:222:0x0898, B:224:0x089c, B:225:0x08b0, B:227:0x08b6, B:231:0x08c9, B:233:0x08cd, B:236:0x08d5, B:240:0x08e0, B:242:0x08e8, B:245:0x08f3, B:247:0x08fc, B:249:0x0918, B:254:0x0924, B:83:0x03e6, B:85:0x03f8, B:93:0x0408, B:95:0x040e, B:97:0x0412, B:99:0x041c, B:101:0x0420, B:103:0x042a, B:105:0x042e, B:107:0x0434), top: B:265:0x03e6 }] */
        /* JADX WARN: Removed duplicated region for block: B:94:0x040d  */
        /* JADX WARN: Removed duplicated region for block: B:97:0x0412 A[Catch: Exception -> 0x0402, TryCatch #0 {Exception -> 0x0402, blocks: (B:200:0x07e9, B:202:0x07ef, B:220:0x0826, B:205:0x07fa, B:206:0x07ff, B:208:0x0805, B:210:0x0811, B:213:0x0816, B:214:0x081b, B:167:0x06dd, B:169:0x06e3, B:187:0x071a, B:172:0x06ee, B:173:0x06f3, B:175:0x06f9, B:177:0x0705, B:180:0x070a, B:181:0x070f, B:120:0x0531, B:121:0x053c, B:123:0x0542, B:125:0x054f, B:126:0x0553, B:141:0x0586, B:143:0x05e2, B:145:0x05e9, B:148:0x05f3, B:150:0x05fb, B:156:0x060a, B:158:0x0610, B:160:0x0619, B:163:0x0664, B:130:0x0560, B:131:0x0565, B:133:0x056b, B:135:0x0579, B:138:0x057e, B:139:0x0583, B:111:0x0467, B:113:0x049c, B:114:0x049f, B:116:0x04ff, B:190:0x078e, B:196:0x079b, B:222:0x0898, B:224:0x089c, B:225:0x08b0, B:227:0x08b6, B:231:0x08c9, B:233:0x08cd, B:236:0x08d5, B:240:0x08e0, B:242:0x08e8, B:245:0x08f3, B:247:0x08fc, B:249:0x0918, B:254:0x0924, B:83:0x03e6, B:85:0x03f8, B:93:0x0408, B:95:0x040e, B:97:0x0412, B:99:0x041c, B:101:0x0420, B:103:0x042a, B:105:0x042e, B:107:0x0434), top: B:265:0x03e6 }] */
        /* JADX WARN: Removed duplicated region for block: B:98:0x041b  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r126) {
            /*
                Method dump skipped, instruction units count: 2632
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13221.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C13221) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$markEpisodeWatched$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$markEpisodeWatched$1", f = "DetailsViewModel.kt", l = {1921, 1923, 1932, 1948, 1964, 1974, 1977}, m = "invokeSuspend", v = 2)
    public static final class C13231 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ int $episode;
        final /* synthetic */ int $season;
        final /* synthetic */ boolean $watched;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13231(int i10, int i11, boolean z, d7.d<? super C13231> dVar) {
            super(2, dVar);
            this.$season = i10;
            this.$episode = i11;
            this.$watched = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C13231 c13231 = DetailsViewModel.this.new C13231(this.$season, this.$episode, this.$watched, dVar);
            c13231.L$0 = obj;
            return c13231;
        }

        /* JADX WARN: Code restructure failed: missing block: B:97:0x029a, code lost:
        
            if (r4.saveProgress(r6, r8, r4, r11, r10, r12, r8, null, 0.01f, 0, 0, (30720 & 2048) != 0 ? null : null, (30720 & 4096) != 0 ? null : null, (30720 & 8192) != 0 ? null : null, (30720 & 16384) != 0 ? 0 : 0, r88) != r0) goto L99;
         */
        /* JADX WARN: Removed duplicated region for block: B:110:0x02db  */
        /* JADX WARN: Removed duplicated region for block: B:120:0x0348 A[Catch: all -> 0x0451, TryCatch #12 {all -> 0x0451, blocks: (B:7:0x0024, B:135:0x0432, B:117:0x031d, B:118:0x0342, B:120:0x0348, B:122:0x0356, B:124:0x035c, B:127:0x0382, B:128:0x0388, B:21:0x009c, B:52:0x0145, B:24:0x00a9, B:49:0x011d, B:27:0x00b1, B:28:0x00c9, B:30:0x00cf, B:32:0x00dc, B:36:0x00e4, B:38:0x00e8, B:42:0x00f3, B:44:0x00fa, B:46:0x00fe, B:112:0x02ea, B:43:0x00f8, B:40:0x00ee), top: B:155:0x000d }] */
        /* JADX WARN: Removed duplicated region for block: B:160:0x0157 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:181:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:182:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:51:0x0144  */
        /* JADX WARN: Type inference failed for: r13v0 */
        /* JADX WARN: Type inference failed for: r13v1 */
        /* JADX WARN: Type inference failed for: r13v2, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r13v8 */
        /* JADX WARN: Type inference failed for: r13v9 */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r89) {
            /*
                Method dump skipped, instruction units count: 1128
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13231.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C13231) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$markSeasonUnwatched$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$markSeasonUnwatched$1", f = "DetailsViewModel.kt", l = {2162, 2163, 2212, 2224, 2233, 2234}, m = "invokeSuspend", v = 2)
    public static final class C13241 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ MediaItem $currentItem;
        final /* synthetic */ int $season;
        int I$0;
        int I$1;
        int I$2;
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13241(int i10, MediaItem mediaItem, d7.d<? super C13241> dVar) {
            super(2, dVar);
            this.$season = i10;
            this.$currentItem = mediaItem;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C13241 c13241 = DetailsViewModel.this.new C13241(this.$season, this.$currentItem, dVar);
            c13241.L$0 = obj;
            return c13241;
        }

        /* JADX WARN: Code restructure failed: missing block: B:109:0x0623, code lost:
        
            if (r0.refreshForCurrentProfile(r70) != r8) goto L121;
         */
        /* JADX WARN: Code restructure failed: missing block: B:113:0x064d, code lost:
        
            if (com.arflix.tv.data.repository.CloudSyncRepository.m6121pushToCloudgIAlus$default(r0, false, r70, 1, null) == r8) goto L114;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0131, code lost:
        
            if (r0 != null) goto L41;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:106:0x0577  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0158 A[Catch: Exception -> 0x0650, TryCatch #0 {Exception -> 0x0650, blocks: (B:111:0x0626, B:97:0x04f0, B:100:0x04f5, B:102:0x056d, B:107:0x0579, B:96:0x04e9, B:18:0x00b5, B:74:0x03e6, B:68:0x03ae, B:70:0x03b4, B:75:0x03f3, B:80:0x03fc, B:90:0x04b9, B:83:0x0406, B:84:0x040a, B:86:0x0410, B:88:0x041c, B:19:0x00c0, B:40:0x0150, B:41:0x0152, B:43:0x0158, B:45:0x01f0, B:47:0x0206, B:48:0x0225, B:50:0x022b, B:52:0x0238, B:53:0x0257, B:56:0x026f, B:57:0x032f, B:59:0x0335, B:61:0x034f, B:62:0x0357, B:63:0x035d, B:64:0x0370, B:66:0x0376, B:67:0x0399, B:55:0x025e, B:22:0x00cb, B:35:0x012f, B:37:0x0133, B:25:0x00d4, B:27:0x00e8, B:29:0x00fe, B:30:0x010f, B:32:0x0117, B:13:0x007f, B:94:0x04e4, B:91:0x04bb), top: B:119:0x0011, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:45:0x01f0 A[Catch: Exception -> 0x0650, TryCatch #0 {Exception -> 0x0650, blocks: (B:111:0x0626, B:97:0x04f0, B:100:0x04f5, B:102:0x056d, B:107:0x0579, B:96:0x04e9, B:18:0x00b5, B:74:0x03e6, B:68:0x03ae, B:70:0x03b4, B:75:0x03f3, B:80:0x03fc, B:90:0x04b9, B:83:0x0406, B:84:0x040a, B:86:0x0410, B:88:0x041c, B:19:0x00c0, B:40:0x0150, B:41:0x0152, B:43:0x0158, B:45:0x01f0, B:47:0x0206, B:48:0x0225, B:50:0x022b, B:52:0x0238, B:53:0x0257, B:56:0x026f, B:57:0x032f, B:59:0x0335, B:61:0x034f, B:62:0x0357, B:63:0x035d, B:64:0x0370, B:66:0x0376, B:67:0x0399, B:55:0x025e, B:22:0x00cb, B:35:0x012f, B:37:0x0133, B:25:0x00d4, B:27:0x00e8, B:29:0x00fe, B:30:0x010f, B:32:0x0117, B:13:0x007f, B:94:0x04e4, B:91:0x04bb), top: B:119:0x0011, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:70:0x03b4 A[Catch: Exception -> 0x0650, TryCatch #0 {Exception -> 0x0650, blocks: (B:111:0x0626, B:97:0x04f0, B:100:0x04f5, B:102:0x056d, B:107:0x0579, B:96:0x04e9, B:18:0x00b5, B:74:0x03e6, B:68:0x03ae, B:70:0x03b4, B:75:0x03f3, B:80:0x03fc, B:90:0x04b9, B:83:0x0406, B:84:0x040a, B:86:0x0410, B:88:0x041c, B:19:0x00c0, B:40:0x0150, B:41:0x0152, B:43:0x0158, B:45:0x01f0, B:47:0x0206, B:48:0x0225, B:50:0x022b, B:52:0x0238, B:53:0x0257, B:56:0x026f, B:57:0x032f, B:59:0x0335, B:61:0x034f, B:62:0x0357, B:63:0x035d, B:64:0x0370, B:66:0x0376, B:67:0x0399, B:55:0x025e, B:22:0x00cb, B:35:0x012f, B:37:0x0133, B:25:0x00d4, B:27:0x00e8, B:29:0x00fe, B:30:0x010f, B:32:0x0117, B:13:0x007f, B:94:0x04e4, B:91:0x04bb), top: B:119:0x0011, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:75:0x03f3 A[Catch: Exception -> 0x0650, TryCatch #0 {Exception -> 0x0650, blocks: (B:111:0x0626, B:97:0x04f0, B:100:0x04f5, B:102:0x056d, B:107:0x0579, B:96:0x04e9, B:18:0x00b5, B:74:0x03e6, B:68:0x03ae, B:70:0x03b4, B:75:0x03f3, B:80:0x03fc, B:90:0x04b9, B:83:0x0406, B:84:0x040a, B:86:0x0410, B:88:0x041c, B:19:0x00c0, B:40:0x0150, B:41:0x0152, B:43:0x0158, B:45:0x01f0, B:47:0x0206, B:48:0x0225, B:50:0x022b, B:52:0x0238, B:53:0x0257, B:56:0x026f, B:57:0x032f, B:59:0x0335, B:61:0x034f, B:62:0x0357, B:63:0x035d, B:64:0x0370, B:66:0x0376, B:67:0x0399, B:55:0x025e, B:22:0x00cb, B:35:0x012f, B:37:0x0133, B:25:0x00d4, B:27:0x00e8, B:29:0x00fe, B:30:0x010f, B:32:0x0117, B:13:0x007f, B:94:0x04e4, B:91:0x04bb), top: B:119:0x0011, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:99:0x04f4  */
        /* JADX WARN: Type inference failed for: r12v25, types: [java.util.ArrayList] */
        /* JADX WARN: Type inference failed for: r12v4, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r12v5 */
        /* JADX WARN: Type inference failed for: r14v11, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r14v15 */
        /* JADX WARN: Type inference failed for: r14v4 */
        /* JADX WARN: Type inference failed for: r15v11 */
        /* JADX WARN: Type inference failed for: r15v7 */
        /* JADX WARN: Type inference failed for: r15v8 */
        /* JADX WARN: Type inference failed for: r16v1, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r19v6, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r20v3, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r3v0 */
        /* JADX WARN: Type inference failed for: r3v1 */
        /* JADX WARN: Type inference failed for: r3v13 */
        /* JADX WARN: Type inference failed for: r3v16 */
        /* JADX WARN: Type inference failed for: r3v17 */
        /* JADX WARN: Type inference failed for: r3v18 */
        /* JADX WARN: Type inference failed for: r3v19 */
        /* JADX WARN: Type inference failed for: r3v3 */
        /* JADX WARN: Type inference failed for: r50v2 */
        /* JADX WARN: Type inference failed for: r50v3, types: [java.util.Map] */
        /* JADX WARN: Type inference failed for: r50v4 */
        /* JADX WARN: Type inference failed for: r9v45, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r9v48 */
        /* JADX WARN: Type inference failed for: r9v51 */
        /* JADX WARN: Type inference failed for: r9v52 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:73:0x03e2 -> B:74:0x03e6). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r71) {
            /*
                Method dump skipped, instruction units count: 1774
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13241.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C13241) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$markSeasonWatched$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$markSeasonWatched$1", f = "DetailsViewModel.kt", l = {1993, 1994, 2030, 2033, 2041, 2048, 2065, 2069, 2076, 2092, 2108, CastStatusCodes.ERROR_NO_CAST_CONFIGURATION, 2140, 2143}, m = "invokeSuspend", v = 2)
    public static final class C13251 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ MediaItem $currentItem;
        final /* synthetic */ int $season;
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
        public C13251(int i10, MediaItem mediaItem, d7.d<? super C13251> dVar) {
            super(2, dVar);
            this.$season = i10;
            this.$currentItem = mediaItem;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C13251 c13251 = DetailsViewModel.this.new C13251(this.$season, this.$currentItem, dVar);
            c13251.L$0 = obj;
            return c13251;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(5:126|(2:129|127)|384|131|(8:134|135|138|(2:141|139)|402|142|143|(5:145|(1:147)|148|143|(3:149|124|(10:150|151|152|(1:154)|155|158|(1:160)|161|(1:163)(1:164)|(19:166|(1:168)(1:169)|170|380|171|172|374|173|(2:348|175)(1:178)|179|(1:181)(1:183)|184|185|364|186|187|(12:190|342|191|192|354|193|194|195|196|340|197|198)|189|415)(29:210|211|362|212|213|346|214|215|396|216|217|392|218|219|376|220|221|372|222|223|366|224|225|360|226|227|356|228|(1:417)(4:231|350|232|(1:418)(6:235|236|254|255|(2:257|(2:259|(18:261|(1:263)(1:264)|265|(1:267)(1:268)|269|(1:275)(1:274)|(1:277)(2:(1:280)(1:281)|(1:283))|278|(1:285)(2:(1:288)(1:289)|(1:291))|286|(1:297)|296|(1:303)|302|(13:305|(1:307)|308|(1:318)|317|319|378|320|(1:419)|323|324|(1:416)|388)(1:309)|(1:311)(1:312)|308|(12:314|316|318|317|319|378|320|(0)|323|324|(0)|388)(0))(0))(0))(0)|328))))(0))(0))(0))) */
        /* JADX WARN: Code restructure failed: missing block: B:110:0x058b, code lost:
        
            if (r12.removeFromHistory(r10, r11, null, r5) != r9) goto L112;
         */
        /* JADX WARN: Code restructure failed: missing block: B:137:0x068f, code lost:
        
            r6 = r0;
            r0 = r2;
            r2 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:199:0x08d6, code lost:
        
            if (r8.saveProgress(r9, r12, r13, r14, r11, r8, r9, null, 0.01f, 0, 0, (30720 & 2048) != 0 ? null : null, (30720 & 4096) != 0 ? null : null, (30720 & 8192) != 0 ? null : null, (30720 & 16384) != 0 ? 0 : 0, r86) != r1) goto L201;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x02c1, code lost:
        
            if (r0 == r9) goto L65;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x02c8, code lost:
        
            if (r0 == null) goto L68;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:119:0x05ce A[Catch: Exception -> 0x0c7c, TryCatch #28 {Exception -> 0x0c7c, blocks: (B:323:0x0c4b, B:255:0x09fe, B:257:0x0a0a, B:259:0x0a12, B:261:0x0a1c, B:263:0x0a26, B:265:0x0a2c, B:269:0x0a49, B:271:0x0aa9, B:277:0x0ab7, B:285:0x0adf, B:293:0x0b07, B:299:0x0b24, B:305:0x0b41, B:311:0x0b6e, B:312:0x0b73, B:314:0x0b86, B:319:0x0ba1, B:318:0x0b90, B:303:0x0b2e, B:297:0x0b11, B:288:0x0aec, B:291:0x0af4, B:280:0x0ac4, B:283:0x0acc, B:158:0x0749, B:161:0x0750, B:163:0x0754, B:166:0x075c, B:168:0x0778, B:170:0x077e, B:211:0x092c, B:157:0x0745, B:34:0x01a5, B:143:0x06c8, B:145:0x06ce, B:124:0x060a, B:126:0x0610, B:127:0x0635, B:129:0x063b, B:138:0x0692, B:139:0x06a1, B:141:0x06a7, B:142:0x06bd, B:151:0x0710, B:40:0x020a, B:117:0x05c8, B:119:0x05ce, B:112:0x058f, B:113:0x05a2, B:115:0x05a8, B:116:0x05c0, B:108:0x0564, B:49:0x0254, B:71:0x02e6, B:72:0x02e8, B:74:0x02ee, B:76:0x038e, B:78:0x03a2, B:79:0x03c3, B:81:0x03c9, B:83:0x03d7, B:84:0x03f7, B:87:0x0411, B:88:0x04d9, B:90:0x04df, B:92:0x04f4, B:93:0x04fc, B:94:0x0502, B:96:0x050a, B:97:0x0519, B:99:0x051f, B:100:0x0531, B:123:0x05fb, B:86:0x0400, B:52:0x0260, B:66:0x02c6, B:68:0x02ca, B:55:0x0269, B:57:0x027d, B:59:0x0293, B:61:0x02a5, B:63:0x02ad, B:29:0x015f, B:155:0x0742, B:152:0x0712), top: B:344:0x0010, inners: #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:126:0x0610 A[Catch: Exception -> 0x0c7c, TryCatch #28 {Exception -> 0x0c7c, blocks: (B:323:0x0c4b, B:255:0x09fe, B:257:0x0a0a, B:259:0x0a12, B:261:0x0a1c, B:263:0x0a26, B:265:0x0a2c, B:269:0x0a49, B:271:0x0aa9, B:277:0x0ab7, B:285:0x0adf, B:293:0x0b07, B:299:0x0b24, B:305:0x0b41, B:311:0x0b6e, B:312:0x0b73, B:314:0x0b86, B:319:0x0ba1, B:318:0x0b90, B:303:0x0b2e, B:297:0x0b11, B:288:0x0aec, B:291:0x0af4, B:280:0x0ac4, B:283:0x0acc, B:158:0x0749, B:161:0x0750, B:163:0x0754, B:166:0x075c, B:168:0x0778, B:170:0x077e, B:211:0x092c, B:157:0x0745, B:34:0x01a5, B:143:0x06c8, B:145:0x06ce, B:124:0x060a, B:126:0x0610, B:127:0x0635, B:129:0x063b, B:138:0x0692, B:139:0x06a1, B:141:0x06a7, B:142:0x06bd, B:151:0x0710, B:40:0x020a, B:117:0x05c8, B:119:0x05ce, B:112:0x058f, B:113:0x05a2, B:115:0x05a8, B:116:0x05c0, B:108:0x0564, B:49:0x0254, B:71:0x02e6, B:72:0x02e8, B:74:0x02ee, B:76:0x038e, B:78:0x03a2, B:79:0x03c3, B:81:0x03c9, B:83:0x03d7, B:84:0x03f7, B:87:0x0411, B:88:0x04d9, B:90:0x04df, B:92:0x04f4, B:93:0x04fc, B:94:0x0502, B:96:0x050a, B:97:0x0519, B:99:0x051f, B:100:0x0531, B:123:0x05fb, B:86:0x0400, B:52:0x0260, B:66:0x02c6, B:68:0x02ca, B:55:0x0269, B:57:0x027d, B:59:0x0293, B:61:0x02a5, B:63:0x02ad, B:29:0x015f, B:155:0x0742, B:152:0x0712), top: B:344:0x0010, inners: #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:141:0x06a7 A[Catch: Exception -> 0x0c7c, LOOP:1: B:139:0x06a1->B:141:0x06a7, LOOP_END, TryCatch #28 {Exception -> 0x0c7c, blocks: (B:323:0x0c4b, B:255:0x09fe, B:257:0x0a0a, B:259:0x0a12, B:261:0x0a1c, B:263:0x0a26, B:265:0x0a2c, B:269:0x0a49, B:271:0x0aa9, B:277:0x0ab7, B:285:0x0adf, B:293:0x0b07, B:299:0x0b24, B:305:0x0b41, B:311:0x0b6e, B:312:0x0b73, B:314:0x0b86, B:319:0x0ba1, B:318:0x0b90, B:303:0x0b2e, B:297:0x0b11, B:288:0x0aec, B:291:0x0af4, B:280:0x0ac4, B:283:0x0acc, B:158:0x0749, B:161:0x0750, B:163:0x0754, B:166:0x075c, B:168:0x0778, B:170:0x077e, B:211:0x092c, B:157:0x0745, B:34:0x01a5, B:143:0x06c8, B:145:0x06ce, B:124:0x060a, B:126:0x0610, B:127:0x0635, B:129:0x063b, B:138:0x0692, B:139:0x06a1, B:141:0x06a7, B:142:0x06bd, B:151:0x0710, B:40:0x020a, B:117:0x05c8, B:119:0x05ce, B:112:0x058f, B:113:0x05a2, B:115:0x05a8, B:116:0x05c0, B:108:0x0564, B:49:0x0254, B:71:0x02e6, B:72:0x02e8, B:74:0x02ee, B:76:0x038e, B:78:0x03a2, B:79:0x03c3, B:81:0x03c9, B:83:0x03d7, B:84:0x03f7, B:87:0x0411, B:88:0x04d9, B:90:0x04df, B:92:0x04f4, B:93:0x04fc, B:94:0x0502, B:96:0x050a, B:97:0x0519, B:99:0x051f, B:100:0x0531, B:123:0x05fb, B:86:0x0400, B:52:0x0260, B:66:0x02c6, B:68:0x02ca, B:55:0x0269, B:57:0x027d, B:59:0x0293, B:61:0x02a5, B:63:0x02ad, B:29:0x015f, B:155:0x0742, B:152:0x0712), top: B:344:0x0010, inners: #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:145:0x06ce A[Catch: Exception -> 0x0c7c, TryCatch #28 {Exception -> 0x0c7c, blocks: (B:323:0x0c4b, B:255:0x09fe, B:257:0x0a0a, B:259:0x0a12, B:261:0x0a1c, B:263:0x0a26, B:265:0x0a2c, B:269:0x0a49, B:271:0x0aa9, B:277:0x0ab7, B:285:0x0adf, B:293:0x0b07, B:299:0x0b24, B:305:0x0b41, B:311:0x0b6e, B:312:0x0b73, B:314:0x0b86, B:319:0x0ba1, B:318:0x0b90, B:303:0x0b2e, B:297:0x0b11, B:288:0x0aec, B:291:0x0af4, B:280:0x0ac4, B:283:0x0acc, B:158:0x0749, B:161:0x0750, B:163:0x0754, B:166:0x075c, B:168:0x0778, B:170:0x077e, B:211:0x092c, B:157:0x0745, B:34:0x01a5, B:143:0x06c8, B:145:0x06ce, B:124:0x060a, B:126:0x0610, B:127:0x0635, B:129:0x063b, B:138:0x0692, B:139:0x06a1, B:141:0x06a7, B:142:0x06bd, B:151:0x0710, B:40:0x020a, B:117:0x05c8, B:119:0x05ce, B:112:0x058f, B:113:0x05a2, B:115:0x05a8, B:116:0x05c0, B:108:0x0564, B:49:0x0254, B:71:0x02e6, B:72:0x02e8, B:74:0x02ee, B:76:0x038e, B:78:0x03a2, B:79:0x03c3, B:81:0x03c9, B:83:0x03d7, B:84:0x03f7, B:87:0x0411, B:88:0x04d9, B:90:0x04df, B:92:0x04f4, B:93:0x04fc, B:94:0x0502, B:96:0x050a, B:97:0x0519, B:99:0x051f, B:100:0x0531, B:123:0x05fb, B:86:0x0400, B:52:0x0260, B:66:0x02c6, B:68:0x02ca, B:55:0x0269, B:57:0x027d, B:59:0x0293, B:61:0x02a5, B:63:0x02ad, B:29:0x015f, B:155:0x0742, B:152:0x0712), top: B:344:0x0010, inners: #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:149:0x0709  */
        /* JADX WARN: Removed duplicated region for block: B:150:0x070e  */
        /* JADX WARN: Removed duplicated region for block: B:154:0x0740  */
        /* JADX WARN: Removed duplicated region for block: B:160:0x074f  */
        /* JADX WARN: Removed duplicated region for block: B:163:0x0754 A[Catch: Exception -> 0x0c7c, TryCatch #28 {Exception -> 0x0c7c, blocks: (B:323:0x0c4b, B:255:0x09fe, B:257:0x0a0a, B:259:0x0a12, B:261:0x0a1c, B:263:0x0a26, B:265:0x0a2c, B:269:0x0a49, B:271:0x0aa9, B:277:0x0ab7, B:285:0x0adf, B:293:0x0b07, B:299:0x0b24, B:305:0x0b41, B:311:0x0b6e, B:312:0x0b73, B:314:0x0b86, B:319:0x0ba1, B:318:0x0b90, B:303:0x0b2e, B:297:0x0b11, B:288:0x0aec, B:291:0x0af4, B:280:0x0ac4, B:283:0x0acc, B:158:0x0749, B:161:0x0750, B:163:0x0754, B:166:0x075c, B:168:0x0778, B:170:0x077e, B:211:0x092c, B:157:0x0745, B:34:0x01a5, B:143:0x06c8, B:145:0x06ce, B:124:0x060a, B:126:0x0610, B:127:0x0635, B:129:0x063b, B:138:0x0692, B:139:0x06a1, B:141:0x06a7, B:142:0x06bd, B:151:0x0710, B:40:0x020a, B:117:0x05c8, B:119:0x05ce, B:112:0x058f, B:113:0x05a2, B:115:0x05a8, B:116:0x05c0, B:108:0x0564, B:49:0x0254, B:71:0x02e6, B:72:0x02e8, B:74:0x02ee, B:76:0x038e, B:78:0x03a2, B:79:0x03c3, B:81:0x03c9, B:83:0x03d7, B:84:0x03f7, B:87:0x0411, B:88:0x04d9, B:90:0x04df, B:92:0x04f4, B:93:0x04fc, B:94:0x0502, B:96:0x050a, B:97:0x0519, B:99:0x051f, B:100:0x0531, B:123:0x05fb, B:86:0x0400, B:52:0x0260, B:66:0x02c6, B:68:0x02ca, B:55:0x0269, B:57:0x027d, B:59:0x0293, B:61:0x02a5, B:63:0x02ad, B:29:0x015f, B:155:0x0742, B:152:0x0712), top: B:344:0x0010, inners: #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:164:0x0759  */
        /* JADX WARN: Removed duplicated region for block: B:166:0x075c A[Catch: Exception -> 0x0c7c, TryCatch #28 {Exception -> 0x0c7c, blocks: (B:323:0x0c4b, B:255:0x09fe, B:257:0x0a0a, B:259:0x0a12, B:261:0x0a1c, B:263:0x0a26, B:265:0x0a2c, B:269:0x0a49, B:271:0x0aa9, B:277:0x0ab7, B:285:0x0adf, B:293:0x0b07, B:299:0x0b24, B:305:0x0b41, B:311:0x0b6e, B:312:0x0b73, B:314:0x0b86, B:319:0x0ba1, B:318:0x0b90, B:303:0x0b2e, B:297:0x0b11, B:288:0x0aec, B:291:0x0af4, B:280:0x0ac4, B:283:0x0acc, B:158:0x0749, B:161:0x0750, B:163:0x0754, B:166:0x075c, B:168:0x0778, B:170:0x077e, B:211:0x092c, B:157:0x0745, B:34:0x01a5, B:143:0x06c8, B:145:0x06ce, B:124:0x060a, B:126:0x0610, B:127:0x0635, B:129:0x063b, B:138:0x0692, B:139:0x06a1, B:141:0x06a7, B:142:0x06bd, B:151:0x0710, B:40:0x020a, B:117:0x05c8, B:119:0x05ce, B:112:0x058f, B:113:0x05a2, B:115:0x05a8, B:116:0x05c0, B:108:0x0564, B:49:0x0254, B:71:0x02e6, B:72:0x02e8, B:74:0x02ee, B:76:0x038e, B:78:0x03a2, B:79:0x03c3, B:81:0x03c9, B:83:0x03d7, B:84:0x03f7, B:87:0x0411, B:88:0x04d9, B:90:0x04df, B:92:0x04f4, B:93:0x04fc, B:94:0x0502, B:96:0x050a, B:97:0x0519, B:99:0x051f, B:100:0x0531, B:123:0x05fb, B:86:0x0400, B:52:0x0260, B:66:0x02c6, B:68:0x02ca, B:55:0x0269, B:57:0x027d, B:59:0x0293, B:61:0x02a5, B:63:0x02ad, B:29:0x015f, B:155:0x0742, B:152:0x0712), top: B:344:0x0010, inners: #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:210:0x0922  */
        /* JADX WARN: Removed duplicated region for block: B:235:0x09a6  */
        /* JADX WARN: Removed duplicated region for block: B:257:0x0a0a A[Catch: Exception -> 0x0c7c, TryCatch #28 {Exception -> 0x0c7c, blocks: (B:323:0x0c4b, B:255:0x09fe, B:257:0x0a0a, B:259:0x0a12, B:261:0x0a1c, B:263:0x0a26, B:265:0x0a2c, B:269:0x0a49, B:271:0x0aa9, B:277:0x0ab7, B:285:0x0adf, B:293:0x0b07, B:299:0x0b24, B:305:0x0b41, B:311:0x0b6e, B:312:0x0b73, B:314:0x0b86, B:319:0x0ba1, B:318:0x0b90, B:303:0x0b2e, B:297:0x0b11, B:288:0x0aec, B:291:0x0af4, B:280:0x0ac4, B:283:0x0acc, B:158:0x0749, B:161:0x0750, B:163:0x0754, B:166:0x075c, B:168:0x0778, B:170:0x077e, B:211:0x092c, B:157:0x0745, B:34:0x01a5, B:143:0x06c8, B:145:0x06ce, B:124:0x060a, B:126:0x0610, B:127:0x0635, B:129:0x063b, B:138:0x0692, B:139:0x06a1, B:141:0x06a7, B:142:0x06bd, B:151:0x0710, B:40:0x020a, B:117:0x05c8, B:119:0x05ce, B:112:0x058f, B:113:0x05a2, B:115:0x05a8, B:116:0x05c0, B:108:0x0564, B:49:0x0254, B:71:0x02e6, B:72:0x02e8, B:74:0x02ee, B:76:0x038e, B:78:0x03a2, B:79:0x03c3, B:81:0x03c9, B:83:0x03d7, B:84:0x03f7, B:87:0x0411, B:88:0x04d9, B:90:0x04df, B:92:0x04f4, B:93:0x04fc, B:94:0x0502, B:96:0x050a, B:97:0x0519, B:99:0x051f, B:100:0x0531, B:123:0x05fb, B:86:0x0400, B:52:0x0260, B:66:0x02c6, B:68:0x02ca, B:55:0x0269, B:57:0x027d, B:59:0x0293, B:61:0x02a5, B:63:0x02ad, B:29:0x015f, B:155:0x0742, B:152:0x0712), top: B:344:0x0010, inners: #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:264:0x0a2b  */
        /* JADX WARN: Removed duplicated region for block: B:318:0x0b90 A[Catch: Exception -> 0x0c7c, TryCatch #28 {Exception -> 0x0c7c, blocks: (B:323:0x0c4b, B:255:0x09fe, B:257:0x0a0a, B:259:0x0a12, B:261:0x0a1c, B:263:0x0a26, B:265:0x0a2c, B:269:0x0a49, B:271:0x0aa9, B:277:0x0ab7, B:285:0x0adf, B:293:0x0b07, B:299:0x0b24, B:305:0x0b41, B:311:0x0b6e, B:312:0x0b73, B:314:0x0b86, B:319:0x0ba1, B:318:0x0b90, B:303:0x0b2e, B:297:0x0b11, B:288:0x0aec, B:291:0x0af4, B:280:0x0ac4, B:283:0x0acc, B:158:0x0749, B:161:0x0750, B:163:0x0754, B:166:0x075c, B:168:0x0778, B:170:0x077e, B:211:0x092c, B:157:0x0745, B:34:0x01a5, B:143:0x06c8, B:145:0x06ce, B:124:0x060a, B:126:0x0610, B:127:0x0635, B:129:0x063b, B:138:0x0692, B:139:0x06a1, B:141:0x06a7, B:142:0x06bd, B:151:0x0710, B:40:0x020a, B:117:0x05c8, B:119:0x05ce, B:112:0x058f, B:113:0x05a2, B:115:0x05a8, B:116:0x05c0, B:108:0x0564, B:49:0x0254, B:71:0x02e6, B:72:0x02e8, B:74:0x02ee, B:76:0x038e, B:78:0x03a2, B:79:0x03c3, B:81:0x03c9, B:83:0x03d7, B:84:0x03f7, B:87:0x0411, B:88:0x04d9, B:90:0x04df, B:92:0x04f4, B:93:0x04fc, B:94:0x0502, B:96:0x050a, B:97:0x0519, B:99:0x051f, B:100:0x0531, B:123:0x05fb, B:86:0x0400, B:52:0x0260, B:66:0x02c6, B:68:0x02ca, B:55:0x0269, B:57:0x027d, B:59:0x0293, B:61:0x02a5, B:63:0x02ad, B:29:0x015f, B:155:0x0742, B:152:0x0712), top: B:344:0x0010, inners: #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:416:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:418:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:419:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:74:0x02ee A[Catch: Exception -> 0x0c7c, TryCatch #28 {Exception -> 0x0c7c, blocks: (B:323:0x0c4b, B:255:0x09fe, B:257:0x0a0a, B:259:0x0a12, B:261:0x0a1c, B:263:0x0a26, B:265:0x0a2c, B:269:0x0a49, B:271:0x0aa9, B:277:0x0ab7, B:285:0x0adf, B:293:0x0b07, B:299:0x0b24, B:305:0x0b41, B:311:0x0b6e, B:312:0x0b73, B:314:0x0b86, B:319:0x0ba1, B:318:0x0b90, B:303:0x0b2e, B:297:0x0b11, B:288:0x0aec, B:291:0x0af4, B:280:0x0ac4, B:283:0x0acc, B:158:0x0749, B:161:0x0750, B:163:0x0754, B:166:0x075c, B:168:0x0778, B:170:0x077e, B:211:0x092c, B:157:0x0745, B:34:0x01a5, B:143:0x06c8, B:145:0x06ce, B:124:0x060a, B:126:0x0610, B:127:0x0635, B:129:0x063b, B:138:0x0692, B:139:0x06a1, B:141:0x06a7, B:142:0x06bd, B:151:0x0710, B:40:0x020a, B:117:0x05c8, B:119:0x05ce, B:112:0x058f, B:113:0x05a2, B:115:0x05a8, B:116:0x05c0, B:108:0x0564, B:49:0x0254, B:71:0x02e6, B:72:0x02e8, B:74:0x02ee, B:76:0x038e, B:78:0x03a2, B:79:0x03c3, B:81:0x03c9, B:83:0x03d7, B:84:0x03f7, B:87:0x0411, B:88:0x04d9, B:90:0x04df, B:92:0x04f4, B:93:0x04fc, B:94:0x0502, B:96:0x050a, B:97:0x0519, B:99:0x051f, B:100:0x0531, B:123:0x05fb, B:86:0x0400, B:52:0x0260, B:66:0x02c6, B:68:0x02ca, B:55:0x0269, B:57:0x027d, B:59:0x0293, B:61:0x02a5, B:63:0x02ad, B:29:0x015f, B:155:0x0742, B:152:0x0712), top: B:344:0x0010, inners: #3 }] */
        /* JADX WARN: Removed duplicated region for block: B:76:0x038e A[Catch: Exception -> 0x0c7c, TryCatch #28 {Exception -> 0x0c7c, blocks: (B:323:0x0c4b, B:255:0x09fe, B:257:0x0a0a, B:259:0x0a12, B:261:0x0a1c, B:263:0x0a26, B:265:0x0a2c, B:269:0x0a49, B:271:0x0aa9, B:277:0x0ab7, B:285:0x0adf, B:293:0x0b07, B:299:0x0b24, B:305:0x0b41, B:311:0x0b6e, B:312:0x0b73, B:314:0x0b86, B:319:0x0ba1, B:318:0x0b90, B:303:0x0b2e, B:297:0x0b11, B:288:0x0aec, B:291:0x0af4, B:280:0x0ac4, B:283:0x0acc, B:158:0x0749, B:161:0x0750, B:163:0x0754, B:166:0x075c, B:168:0x0778, B:170:0x077e, B:211:0x092c, B:157:0x0745, B:34:0x01a5, B:143:0x06c8, B:145:0x06ce, B:124:0x060a, B:126:0x0610, B:127:0x0635, B:129:0x063b, B:138:0x0692, B:139:0x06a1, B:141:0x06a7, B:142:0x06bd, B:151:0x0710, B:40:0x020a, B:117:0x05c8, B:119:0x05ce, B:112:0x058f, B:113:0x05a2, B:115:0x05a8, B:116:0x05c0, B:108:0x0564, B:49:0x0254, B:71:0x02e6, B:72:0x02e8, B:74:0x02ee, B:76:0x038e, B:78:0x03a2, B:79:0x03c3, B:81:0x03c9, B:83:0x03d7, B:84:0x03f7, B:87:0x0411, B:88:0x04d9, B:90:0x04df, B:92:0x04f4, B:93:0x04fc, B:94:0x0502, B:96:0x050a, B:97:0x0519, B:99:0x051f, B:100:0x0531, B:123:0x05fb, B:86:0x0400, B:52:0x0260, B:66:0x02c6, B:68:0x02ca, B:55:0x0269, B:57:0x027d, B:59:0x0293, B:61:0x02a5, B:63:0x02ad, B:29:0x015f, B:155:0x0742, B:152:0x0712), top: B:344:0x0010, inners: #3 }] */
        /* JADX WARN: Type inference failed for: r0v15 */
        /* JADX WARN: Type inference failed for: r0v16 */
        /* JADX WARN: Type inference failed for: r0v18 */
        /* JADX WARN: Type inference failed for: r0v20 */
        /* JADX WARN: Type inference failed for: r0v236 */
        /* JADX WARN: Type inference failed for: r0v237 */
        /* JADX WARN: Type inference failed for: r0v238 */
        /* JADX WARN: Type inference failed for: r0v28 */
        /* JADX WARN: Type inference failed for: r0v29 */
        /* JADX WARN: Type inference failed for: r0v34, types: [java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r0v36 */
        /* JADX WARN: Type inference failed for: r0v37 */
        /* JADX WARN: Type inference failed for: r0v38 */
        /* JADX WARN: Type inference failed for: r0v43, types: [java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r0v45 */
        /* JADX WARN: Type inference failed for: r0v84, types: [java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r0v86, types: [java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r10v13 */
        /* JADX WARN: Type inference failed for: r10v15 */
        /* JADX WARN: Type inference failed for: r10v16 */
        /* JADX WARN: Type inference failed for: r10v19 */
        /* JADX WARN: Type inference failed for: r10v22, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r10v23, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r10v24 */
        /* JADX WARN: Type inference failed for: r10v31, types: [java.util.ArrayList] */
        /* JADX WARN: Type inference failed for: r10v38 */
        /* JADX WARN: Type inference failed for: r10v39, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r10v42 */
        /* JADX WARN: Type inference failed for: r10v56 */
        /* JADX WARN: Type inference failed for: r10v66 */
        /* JADX WARN: Type inference failed for: r10v77 */
        /* JADX WARN: Type inference failed for: r10v8 */
        /* JADX WARN: Type inference failed for: r10v80 */
        /* JADX WARN: Type inference failed for: r10v9 */
        /* JADX WARN: Type inference failed for: r13v27 */
        /* JADX WARN: Type inference failed for: r13v41, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r13v42 */
        /* JADX WARN: Type inference failed for: r13v43 */
        /* JADX WARN: Type inference failed for: r13v44 */
        /* JADX WARN: Type inference failed for: r13v45, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r13v47, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r13v48 */
        /* JADX WARN: Type inference failed for: r13v51 */
        /* JADX WARN: Type inference failed for: r13v64 */
        /* JADX WARN: Type inference failed for: r13v65 */
        /* JADX WARN: Type inference failed for: r13v66 */
        /* JADX WARN: Type inference failed for: r13v67 */
        /* JADX WARN: Type inference failed for: r13v68 */
        /* JADX WARN: Type inference failed for: r13v69 */
        /* JADX WARN: Type inference failed for: r24v2 */
        /* JADX WARN: Type inference failed for: r24v3 */
        /* JADX WARN: Type inference failed for: r24v4 */
        /* JADX WARN: Type inference failed for: r24v5 */
        /* JADX WARN: Type inference failed for: r24v6 */
        /* JADX WARN: Type inference failed for: r24v7 */
        /* JADX WARN: Type inference failed for: r2v12, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v18 */
        /* JADX WARN: Type inference failed for: r2v5 */
        /* JADX WARN: Type inference failed for: r2v96 */
        /* JADX WARN: Type inference failed for: r2v97 */
        /* JADX WARN: Type inference failed for: r2v98 */
        /* JADX WARN: Type inference failed for: r31v1 */
        /* JADX WARN: Type inference failed for: r31v10 */
        /* JADX WARN: Type inference failed for: r31v11 */
        /* JADX WARN: Type inference failed for: r31v12 */
        /* JADX WARN: Type inference failed for: r31v13 */
        /* JADX WARN: Type inference failed for: r31v14 */
        /* JADX WARN: Type inference failed for: r31v15 */
        /* JADX WARN: Type inference failed for: r31v16 */
        /* JADX WARN: Type inference failed for: r31v17 */
        /* JADX WARN: Type inference failed for: r31v18 */
        /* JADX WARN: Type inference failed for: r31v19 */
        /* JADX WARN: Type inference failed for: r31v2 */
        /* JADX WARN: Type inference failed for: r31v21 */
        /* JADX WARN: Type inference failed for: r31v22 */
        /* JADX WARN: Type inference failed for: r31v29 */
        /* JADX WARN: Type inference failed for: r31v3 */
        /* JADX WARN: Type inference failed for: r31v30 */
        /* JADX WARN: Type inference failed for: r31v31 */
        /* JADX WARN: Type inference failed for: r31v32 */
        /* JADX WARN: Type inference failed for: r31v33 */
        /* JADX WARN: Type inference failed for: r31v34 */
        /* JADX WARN: Type inference failed for: r31v35 */
        /* JADX WARN: Type inference failed for: r31v36 */
        /* JADX WARN: Type inference failed for: r31v4 */
        /* JADX WARN: Type inference failed for: r31v5 */
        /* JADX WARN: Type inference failed for: r31v6 */
        /* JADX WARN: Type inference failed for: r31v7 */
        /* JADX WARN: Type inference failed for: r31v8 */
        /* JADX WARN: Type inference failed for: r31v9 */
        /* JADX WARN: Type inference failed for: r33v2 */
        /* JADX WARN: Type inference failed for: r33v3, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r33v4 */
        /* JADX WARN: Type inference failed for: r33v5, types: [java.lang.Object, java.util.List] */
        /* JADX WARN: Type inference failed for: r3v37 */
        /* JADX WARN: Type inference failed for: r3v38 */
        /* JADX WARN: Type inference failed for: r3v46, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v47 */
        /* JADX WARN: Type inference failed for: r3v50 */
        /* JADX WARN: Type inference failed for: r3v53, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r3v55 */
        /* JADX WARN: Type inference failed for: r3v65, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v70 */
        /* JADX WARN: Type inference failed for: r3v76, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v91 */
        /* JADX WARN: Type inference failed for: r3v92 */
        /* JADX WARN: Type inference failed for: r3v93 */
        /* JADX WARN: Type inference failed for: r3v94 */
        /* JADX WARN: Type inference failed for: r3v95 */
        /* JADX WARN: Type inference failed for: r68v1, types: [java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r69v2, types: [java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r6v10 */
        /* JADX WARN: Type inference failed for: r6v11, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r6v73 */
        /* JADX WARN: Type inference failed for: r7v10 */
        /* JADX WARN: Type inference failed for: r7v12 */
        /* JADX WARN: Type inference failed for: r7v15, types: [com.arflix.tv.ui.screens.details.ResumeInfo, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r7v18, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r7v23 */
        /* JADX WARN: Type inference failed for: r7v30 */
        /* JADX WARN: Type inference failed for: r8v12 */
        /* JADX WARN: Type inference failed for: r8v13, types: [com.arflix.tv.data.model.EpisodeIdentity] */
        /* JADX WARN: Type inference failed for: r8v19 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:142:0x06bd -> B:143:0x06c8). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:146:0x0703 -> B:148:0x0707). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r87) {
            /*
                Method dump skipped, instruction units count: 3380
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13251.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C13251) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$prefetchStreamsInBackground$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$prefetchStreamsInBackground$1", f = "DetailsViewModel.kt", l = {1506, 1540}, m = "invokeSuspend", v = 2)
    public static final class C13261 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ Integer $episode;
        final /* synthetic */ String $imdbId;
        final /* synthetic */ int $requestMediaId;
        final /* synthetic */ MediaType $requestMediaType;
        final /* synthetic */ Integer $season;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ DetailsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13261(MediaType mediaType, DetailsViewModel detailsViewModel, String str, Integer num, Integer num2, int i10, d7.d<? super C13261> dVar) {
            super(2, dVar);
            this.$requestMediaType = mediaType;
            this.this$0 = detailsViewModel;
            this.$imdbId = str;
            this.$season = num;
            this.$episode = num2;
            this.$requestMediaId = i10;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C13261 c13261 = new C13261(this.$requestMediaType, this.this$0, this.$imdbId, this.$season, this.$episode, this.$requestMediaId, dVar);
            c13261.L$0 = obj;
            return c13261;
        }

        /* JADX WARN: Code restructure failed: missing block: B:68:0x018d, code lost:
        
            if (r5.collect(r7, r21) == r2) goto L69;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r22) {
            /*
                Method dump skipped, instruction units count: 403
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13261.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C13261) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$prewarmStream$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$prewarmStream$1", f = "DetailsViewModel.kt", l = {1560}, m = "invokeSuspend", v = 2)
    public static final class C13271 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ StreamSource $stream;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13271(StreamSource streamSource, d7.d<? super C13271> dVar) {
            super(2, dVar);
            this.$stream = streamSource;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C13271 c13271 = DetailsViewModel.this.new C13271(this.$stream, dVar);
            c13271.L$0 = obj;
            return c13271;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    DetailsViewModel detailsViewModel = DetailsViewModel.this;
                    StreamSource streamSource = this.$stream;
                    StreamRepository streamRepository = detailsViewModel.streamRepository;
                    this.L$0 = null;
                    this.L$1 = null;
                    this.I$0 = 0;
                    this.label = 1;
                    obj = streamRepository.prewarmStreamForPlayback(streamSource, true, this);
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
            } catch (Throwable unused) {
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C13271) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$prewarmStreamsAround$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$prewarmStreamsAround$1", f = "DetailsViewModel.kt", l = {1574}, m = "invokeSuspend", v = 2)
    public static final class C13281 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ StreamSource $stream;
        final /* synthetic */ List<StreamSource> $streams;
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;
        final /* synthetic */ DetailsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13281(List<StreamSource> list, StreamSource streamSource, DetailsViewModel detailsViewModel, d7.d<? super C13281> dVar) {
            super(2, dVar);
            this.$streams = list;
            this.$stream = streamSource;
            this.this$0 = detailsViewModel;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C13281 c13281 = new C13281(this.$streams, this.$stream, this.this$0, dVar);
            c13281.L$0 = obj;
            return c13281;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String strG0;
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    Integer num = new Integer(this.$streams.indexOf(this.$stream));
                    if (num.intValue() < 0) {
                        num = null;
                    }
                    int iIntValue = num != null ? num.intValue() : 0;
                    List listE = t7.a.E(new Integer(iIntValue), new Integer(iIntValue + 1), new Integer(iIntValue + 2));
                    List<StreamSource> list = this.$streams;
                    ArrayList arrayList = new ArrayList();
                    Iterator it = listE.iterator();
                    while (it.hasNext()) {
                        StreamSource streamSource = (StreamSource) kotlin.collections.x.p0(((Number) it.next()).intValue(), list);
                        if (streamSource != null) {
                            arrayList.add(streamSource);
                        }
                    }
                    HashSet hashSet = new HashSet();
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj2 : arrayList) {
                        StreamSource streamSource2 = (StreamSource) obj2;
                        String addonId = streamSource2.getAddonId();
                        String source = streamSource2.getSource();
                        String url = streamSource2.getUrl();
                        if (url != null) {
                            String strG02 = kotlin.text.o.G0('|', url, url);
                            strG0 = kotlin.text.o.G0('#', strG02, strG02);
                        } else {
                            strG0 = null;
                        }
                        if (hashSet.add(androidx.compose.material3.d.o(addonId, ":", source, ":", strG0))) {
                            arrayList2.add(obj2);
                        }
                    }
                    StreamRepository streamRepository = this.this$0.streamRepository;
                    int size = arrayList2.size();
                    this.L$0 = null;
                    this.L$1 = null;
                    this.L$2 = null;
                    this.I$0 = iIntValue;
                    this.I$1 = 0;
                    this.label = 1;
                    obj = streamRepository.prewarmStreamsForPlayback(arrayList2, size, true, this);
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
            } catch (Throwable unused) {
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C13281) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$prewarmVisibleStreams$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$prewarmVisibleStreams$1", f = "DetailsViewModel.kt", l = {1594}, m = "invokeSuspend", v = 2)
    public static final class C13291 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ List<StreamSource> $topStreams;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13291(List<StreamSource> list, d7.d<? super C13291> dVar) {
            super(2, dVar);
            this.$topStreams = list;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C13291 c13291 = DetailsViewModel.this.new C13291(this.$topStreams, dVar);
            c13291.L$0 = obj;
            return c13291;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    DetailsViewModel detailsViewModel = DetailsViewModel.this;
                    List<StreamSource> list = this.$topStreams;
                    StreamRepository streamRepository = detailsViewModel.streamRepository;
                    int size = list.size();
                    this.L$0 = null;
                    this.L$1 = null;
                    this.I$0 = 0;
                    this.label = 1;
                    obj = streamRepository.prewarmStreamsForPlayback(list, size, true, this);
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
            } catch (Throwable unused) {
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C13291) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$refreshAfterPlayerReturn$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$refreshAfterPlayerReturn$1", f = "DetailsViewModel.kt", l = {1336, 1377, 1387}, m = "invokeSuspend", v = 2)
    public static final class C13301 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ MediaType $mediaType;
        final /* synthetic */ int $tmdbId;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        final /* synthetic */ DetailsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13301(MediaType mediaType, DetailsViewModel detailsViewModel, int i10, d7.d<? super C13301> dVar) {
            super(2, dVar);
            this.$mediaType = mediaType;
            this.this$0 = detailsViewModel;
            this.$tmdbId = i10;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C13301 c13301 = new C13301(this.$mediaType, this.this$0, this.$tmdbId, dVar);
            c13301.L$0 = obj;
            return c13301;
        }

        /* JADX WARN: Removed duplicated region for block: B:100:0x02d3  */
        /* JADX WARN: Removed duplicated region for block: B:110:0x0311  */
        /* JADX WARN: Removed duplicated region for block: B:117:0x0332  */
        /* JADX WARN: Removed duplicated region for block: B:120:0x033b  */
        /* JADX WARN: Removed duplicated region for block: B:124:0x034a  */
        /* JADX WARN: Removed duplicated region for block: B:128:0x0358  */
        /* JADX WARN: Removed duplicated region for block: B:130:0x035f  */
        /* JADX WARN: Removed duplicated region for block: B:132:0x036b  */
        /* JADX WARN: Removed duplicated region for block: B:138:0x037d  */
        /* JADX WARN: Removed duplicated region for block: B:140:0x0389  */
        /* JADX WARN: Removed duplicated region for block: B:146:0x039a  */
        /* JADX WARN: Removed duplicated region for block: B:150:0x03a4  */
        /* JADX WARN: Removed duplicated region for block: B:152:0x03ab  */
        /* JADX WARN: Removed duplicated region for block: B:156:0x03b5  */
        /* JADX WARN: Removed duplicated region for block: B:158:0x03bc  */
        /* JADX WARN: Removed duplicated region for block: B:162:0x03e6  */
        /* JADX WARN: Removed duplicated region for block: B:166:0x03f4  */
        /* JADX WARN: Removed duplicated region for block: B:169:0x03ff  */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00ca  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x017f  */
        /* JADX WARN: Removed duplicated region for block: B:92:0x0291  */
        /* JADX WARN: Removed duplicated region for block: B:96:0x02b0  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x02b8  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r75) {
            /*
                Method dump skipped, instruction units count: 1135
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13301.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C13301) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$resolveExternalIds$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel", f = "DetailsViewModel.kt", l = {2638, 2639}, m = "resolveExternalIds", v = 2)
    public static final class C13311 extends f7.c {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C13311(d7.d<? super C13311> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DetailsViewModel.this.resolveExternalIds(null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$resolveRuntimeSeconds$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel", f = "DetailsViewModel.kt", l = {2560, 2563, 2570}, m = "resolveRuntimeSeconds", v = 2)
    public static final class C13321 extends f7.c {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public C13321(d7.d<? super C13321> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return DetailsViewModel.this.resolveRuntimeSeconds(0, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$toggleWatched$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$toggleWatched$1", f = "DetailsViewModel.kt", l = {1130, 1131, 1133, 1140, 1153, 1158, 1172, 1188, 1206, 1234, 1236}, m = "invokeSuspend", v = 2)
    public static final class C13331 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ MediaItem $currentItem;
        final /* synthetic */ Integer $episodeIndex;
        int I$0;
        int I$1;
        int I$2;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13331(MediaItem mediaItem, Integer num, d7.d<? super C13331> dVar) {
            super(2, dVar);
            this.$currentItem = mediaItem;
            this.$episodeIndex = num;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C13331 c13331 = DetailsViewModel.this.new C13331(this.$currentItem, this.$episodeIndex, dVar);
            c13331.L$0 = obj;
            return c13331;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:106:0x03bd  */
        /* JADX WARN: Removed duplicated region for block: B:109:0x03c5 A[Catch: Exception -> 0x03b4, TRY_ENTER, TRY_LEAVE, TryCatch #3 {Exception -> 0x03b4, blocks: (B:101:0x03af, B:109:0x03c5), top: B:204:0x03af }] */
        /* JADX WARN: Removed duplicated region for block: B:111:0x03ca  */
        /* JADX WARN: Removed duplicated region for block: B:120:0x042d  */
        /* JADX WARN: Removed duplicated region for block: B:129:0x04a2  */
        /* JADX WARN: Removed duplicated region for block: B:149:0x04ec A[Catch: Exception -> 0x0743, TryCatch #14 {Exception -> 0x0743, blocks: (B:189:0x0728, B:11:0x0041, B:156:0x0543, B:157:0x0562, B:159:0x0568, B:161:0x057a, B:166:0x058b, B:167:0x05a9, B:168:0x05af, B:177:0x05d0, B:179:0x0646, B:182:0x0694, B:181:0x066e, B:171:0x05b8, B:172:0x05bc, B:174:0x05c2, B:147:0x04e8, B:149:0x04ec, B:150:0x050a, B:24:0x008c, B:27:0x0099, B:82:0x0328, B:33:0x00a9, B:54:0x011b, B:58:0x0138, B:60:0x0198, B:61:0x01a0, B:63:0x01b0, B:62:0x01a7, B:36:0x00af, B:47:0x00e5, B:39:0x00b6, B:41:0x00c0, B:43:0x00ca, B:50:0x00ff, B:68:0x0241, B:70:0x0255, B:72:0x025b, B:74:0x0264, B:76:0x02fa, B:78:0x0302, B:151:0x050b), top: B:216:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:150:0x050a A[Catch: Exception -> 0x0743, TryCatch #14 {Exception -> 0x0743, blocks: (B:189:0x0728, B:11:0x0041, B:156:0x0543, B:157:0x0562, B:159:0x0568, B:161:0x057a, B:166:0x058b, B:167:0x05a9, B:168:0x05af, B:177:0x05d0, B:179:0x0646, B:182:0x0694, B:181:0x066e, B:171:0x05b8, B:172:0x05bc, B:174:0x05c2, B:147:0x04e8, B:149:0x04ec, B:150:0x050a, B:24:0x008c, B:27:0x0099, B:82:0x0328, B:33:0x00a9, B:54:0x011b, B:58:0x0138, B:60:0x0198, B:61:0x01a0, B:63:0x01b0, B:62:0x01a7, B:36:0x00af, B:47:0x00e5, B:39:0x00b6, B:41:0x00c0, B:43:0x00ca, B:50:0x00ff, B:68:0x0241, B:70:0x0255, B:72:0x025b, B:74:0x0264, B:76:0x02fa, B:78:0x0302, B:151:0x050b), top: B:216:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:159:0x0568 A[Catch: Exception -> 0x0743, TryCatch #14 {Exception -> 0x0743, blocks: (B:189:0x0728, B:11:0x0041, B:156:0x0543, B:157:0x0562, B:159:0x0568, B:161:0x057a, B:166:0x058b, B:167:0x05a9, B:168:0x05af, B:177:0x05d0, B:179:0x0646, B:182:0x0694, B:181:0x066e, B:171:0x05b8, B:172:0x05bc, B:174:0x05c2, B:147:0x04e8, B:149:0x04ec, B:150:0x050a, B:24:0x008c, B:27:0x0099, B:82:0x0328, B:33:0x00a9, B:54:0x011b, B:58:0x0138, B:60:0x0198, B:61:0x01a0, B:63:0x01b0, B:62:0x01a7, B:36:0x00af, B:47:0x00e5, B:39:0x00b6, B:41:0x00c0, B:43:0x00ca, B:50:0x00ff, B:68:0x0241, B:70:0x0255, B:72:0x025b, B:74:0x0264, B:76:0x02fa, B:78:0x0302, B:151:0x050b), top: B:216:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:170:0x05b5  */
        /* JADX WARN: Removed duplicated region for block: B:171:0x05b8 A[Catch: Exception -> 0x0743, TryCatch #14 {Exception -> 0x0743, blocks: (B:189:0x0728, B:11:0x0041, B:156:0x0543, B:157:0x0562, B:159:0x0568, B:161:0x057a, B:166:0x058b, B:167:0x05a9, B:168:0x05af, B:177:0x05d0, B:179:0x0646, B:182:0x0694, B:181:0x066e, B:171:0x05b8, B:172:0x05bc, B:174:0x05c2, B:147:0x04e8, B:149:0x04ec, B:150:0x050a, B:24:0x008c, B:27:0x0099, B:82:0x0328, B:33:0x00a9, B:54:0x011b, B:58:0x0138, B:60:0x0198, B:61:0x01a0, B:63:0x01b0, B:62:0x01a7, B:36:0x00af, B:47:0x00e5, B:39:0x00b6, B:41:0x00c0, B:43:0x00ca, B:50:0x00ff, B:68:0x0241, B:70:0x0255, B:72:0x025b, B:74:0x0264, B:76:0x02fa, B:78:0x0302, B:151:0x050b), top: B:216:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:179:0x0646 A[Catch: Exception -> 0x0743, TryCatch #14 {Exception -> 0x0743, blocks: (B:189:0x0728, B:11:0x0041, B:156:0x0543, B:157:0x0562, B:159:0x0568, B:161:0x057a, B:166:0x058b, B:167:0x05a9, B:168:0x05af, B:177:0x05d0, B:179:0x0646, B:182:0x0694, B:181:0x066e, B:171:0x05b8, B:172:0x05bc, B:174:0x05c2, B:147:0x04e8, B:149:0x04ec, B:150:0x050a, B:24:0x008c, B:27:0x0099, B:82:0x0328, B:33:0x00a9, B:54:0x011b, B:58:0x0138, B:60:0x0198, B:61:0x01a0, B:63:0x01b0, B:62:0x01a7, B:36:0x00af, B:47:0x00e5, B:39:0x00b6, B:41:0x00c0, B:43:0x00ca, B:50:0x00ff, B:68:0x0241, B:70:0x0255, B:72:0x025b, B:74:0x0264, B:76:0x02fa, B:78:0x0302, B:151:0x050b), top: B:216:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:181:0x066e A[Catch: Exception -> 0x0743, TryCatch #14 {Exception -> 0x0743, blocks: (B:189:0x0728, B:11:0x0041, B:156:0x0543, B:157:0x0562, B:159:0x0568, B:161:0x057a, B:166:0x058b, B:167:0x05a9, B:168:0x05af, B:177:0x05d0, B:179:0x0646, B:182:0x0694, B:181:0x066e, B:171:0x05b8, B:172:0x05bc, B:174:0x05c2, B:147:0x04e8, B:149:0x04ec, B:150:0x050a, B:24:0x008c, B:27:0x0099, B:82:0x0328, B:33:0x00a9, B:54:0x011b, B:58:0x0138, B:60:0x0198, B:61:0x01a0, B:63:0x01b0, B:62:0x01a7, B:36:0x00af, B:47:0x00e5, B:39:0x00b6, B:41:0x00c0, B:43:0x00ca, B:50:0x00ff, B:68:0x0241, B:70:0x0255, B:72:0x025b, B:74:0x0264, B:76:0x02fa, B:78:0x0302, B:151:0x050b), top: B:216:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:204:0x03af A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:237:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:238:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:239:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:240:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:49:0x00fd  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x0133  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x0136  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x0198 A[Catch: Exception -> 0x0743, TryCatch #14 {Exception -> 0x0743, blocks: (B:189:0x0728, B:11:0x0041, B:156:0x0543, B:157:0x0562, B:159:0x0568, B:161:0x057a, B:166:0x058b, B:167:0x05a9, B:168:0x05af, B:177:0x05d0, B:179:0x0646, B:182:0x0694, B:181:0x066e, B:171:0x05b8, B:172:0x05bc, B:174:0x05c2, B:147:0x04e8, B:149:0x04ec, B:150:0x050a, B:24:0x008c, B:27:0x0099, B:82:0x0328, B:33:0x00a9, B:54:0x011b, B:58:0x0138, B:60:0x0198, B:61:0x01a0, B:63:0x01b0, B:62:0x01a7, B:36:0x00af, B:47:0x00e5, B:39:0x00b6, B:41:0x00c0, B:43:0x00ca, B:50:0x00ff, B:68:0x0241, B:70:0x0255, B:72:0x025b, B:74:0x0264, B:76:0x02fa, B:78:0x0302, B:151:0x050b), top: B:216:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:62:0x01a7 A[Catch: Exception -> 0x0743, TryCatch #14 {Exception -> 0x0743, blocks: (B:189:0x0728, B:11:0x0041, B:156:0x0543, B:157:0x0562, B:159:0x0568, B:161:0x057a, B:166:0x058b, B:167:0x05a9, B:168:0x05af, B:177:0x05d0, B:179:0x0646, B:182:0x0694, B:181:0x066e, B:171:0x05b8, B:172:0x05bc, B:174:0x05c2, B:147:0x04e8, B:149:0x04ec, B:150:0x050a, B:24:0x008c, B:27:0x0099, B:82:0x0328, B:33:0x00a9, B:54:0x011b, B:58:0x0138, B:60:0x0198, B:61:0x01a0, B:63:0x01b0, B:62:0x01a7, B:36:0x00af, B:47:0x00e5, B:39:0x00b6, B:41:0x00c0, B:43:0x00ca, B:50:0x00ff, B:68:0x0241, B:70:0x0255, B:72:0x025b, B:74:0x0264, B:76:0x02fa, B:78:0x0302, B:151:0x050b), top: B:216:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:87:0x035d A[Catch: Exception -> 0x005a, TRY_ENTER, TryCatch #9 {Exception -> 0x005a, blocks: (B:14:0x0052, B:87:0x035d, B:90:0x036d, B:94:0x0378), top: B:216:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:88:0x036a  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x036d A[Catch: Exception -> 0x005a, TRY_LEAVE, TryCatch #9 {Exception -> 0x005a, blocks: (B:14:0x0052, B:87:0x035d, B:90:0x036d, B:94:0x0378), top: B:216:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:92:0x0372 A[Catch: Exception -> 0x04de, TRY_ENTER, TRY_LEAVE, TryCatch #6 {Exception -> 0x04de, blocks: (B:85:0x0355, B:97:0x0382, B:96:0x037d, B:92:0x0372), top: B:210:0x0355 }] */
        /* JADX WARN: Removed duplicated region for block: B:94:0x0378 A[Catch: Exception -> 0x005a, TRY_ENTER, TRY_LEAVE, TryCatch #9 {Exception -> 0x005a, blocks: (B:14:0x0052, B:87:0x035d, B:90:0x036d, B:94:0x0378), top: B:216:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:96:0x037d A[Catch: Exception -> 0x04de, TRY_ENTER, TryCatch #6 {Exception -> 0x04de, blocks: (B:85:0x0355, B:97:0x0382, B:96:0x037d, B:92:0x0372), top: B:210:0x0355 }] */
        /* JADX WARN: Type inference failed for: r13v1 */
        /* JADX WARN: Type inference failed for: r13v2 */
        /* JADX WARN: Type inference failed for: r13v3 */
        /* JADX WARN: Type inference failed for: r13v4, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r13v5 */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r85) {
            /*
                Method dump skipped, instruction units count: 2038
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13331.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C13331) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.details.DetailsViewModel$toggleWatchlist$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.details.DetailsViewModel$toggleWatchlist$1", f = "DetailsViewModel.kt", l = {1255, 1257, 1261, 1263, 1266, 1268}, m = "invokeSuspend", v = 2)
    public static final class C13341 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ MediaItem $currentItem;
        final /* synthetic */ boolean $newInWatchlist;
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        final /* synthetic */ DetailsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C13341(MediaItem mediaItem, boolean z, DetailsViewModel detailsViewModel, d7.d<? super C13341> dVar) {
            super(2, dVar);
            this.$currentItem = mediaItem;
            this.$newInWatchlist = z;
            this.this$0 = detailsViewModel;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C13341 c13341 = new C13341(this.$currentItem, this.$newInWatchlist, this.this$0, dVar);
            c13341.L$0 = obj;
            return c13341;
        }

        /* JADX WARN: Code restructure failed: missing block: B:81:0x01af, code lost:
        
            if (com.arflix.tv.data.repository.CloudSyncRepository.m6121pushToCloudgIAlus$default(r7, false, r60, 1, null) == r6) goto L82;
         */
        /* JADX WARN: Removed duplicated region for block: B:43:0x00b3  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x00be  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x00f2  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x00f3 A[Catch: Exception -> 0x0031, TryCatch #2 {Exception -> 0x0031, blocks: (B:83:0x01b2, B:85:0x01c9, B:88:0x01dd, B:89:0x01e2, B:87:0x01d5, B:10:0x002c, B:79:0x0198, B:15:0x0038, B:71:0x0159, B:76:0x0174, B:74:0x0162, B:75:0x0173, B:18:0x0043, B:54:0x00ea, B:59:0x0105, B:57:0x00f3, B:58:0x0104, B:41:0x00ad, B:44:0x00b4, B:47:0x00c0, B:51:0x00d7, B:64:0x0130, B:68:0x0147, B:40:0x00a7, B:26:0x005b, B:28:0x0065, B:30:0x0073, B:34:0x0089, B:21:0x0050, B:38:0x00a1, B:35:0x008b), top: B:96:0x000e, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:61:0x012a  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x012e  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x0161  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x0162 A[Catch: Exception -> 0x0031, TryCatch #2 {Exception -> 0x0031, blocks: (B:83:0x01b2, B:85:0x01c9, B:88:0x01dd, B:89:0x01e2, B:87:0x01d5, B:10:0x002c, B:79:0x0198, B:15:0x0038, B:71:0x0159, B:76:0x0174, B:74:0x0162, B:75:0x0173, B:18:0x0043, B:54:0x00ea, B:59:0x0105, B:57:0x00f3, B:58:0x0104, B:41:0x00ad, B:44:0x00b4, B:47:0x00c0, B:51:0x00d7, B:64:0x0130, B:68:0x0147, B:40:0x00a7, B:26:0x005b, B:28:0x0065, B:30:0x0073, B:34:0x0089, B:21:0x0050, B:38:0x00a1, B:35:0x008b), top: B:96:0x000e, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:78:0x0197  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r61) {
            /*
                Method dump skipped, instruction units count: 790
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.C13341.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C13341) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    @Inject
    public DetailsViewModel(@ApplicationContext Context context, MediaRepository mediaRepository, PluginManager pluginManager, ProfileManager profileManager, TraktRepository traktRepository, RemoteSyncManager remoteSyncManager, StreamRepository streamRepository, AnimeMapper animeMapper, TmdbApi tmdbApi, WatchHistoryRepository watchHistoryRepository, WatchlistRepository watchlistRepository, CloudSyncRepository cloudSyncRepository, LauncherContinueWatchingRepository launcherContinueWatchingRepository) {
        this.context = context;
        this.mediaRepository = mediaRepository;
        this.pluginManager = pluginManager;
        this.profileManager = profileManager;
        this.traktRepository = traktRepository;
        this.remoteSyncManager = remoteSyncManager;
        this.streamRepository = streamRepository;
        this.animeMapper = animeMapper;
        this.tmdbApi = tmdbApi;
        this.watchHistoryRepository = watchHistoryRepository;
        this.watchlistRepository = watchlistRepository;
        this.cloudSyncRepository = cloudSyncRepository;
        this.launcherContinueWatchingRepository = launcherContinueWatchingRepository;
        na.j1 j1VarB = na.y0.b(new DetailsUiState(false, null, null, null, null, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -1, 1048575, null));
        this._uiState = j1VarB;
        this.uiState = na.y0.e(j1VarB);
        this.currentMediaType = MediaType.MOVIE;
        this.lastStreamListPrewarmKey = "";
        this.seasonLoadRequestedSeason = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object appendHomeServerSourcesInBackground(java.lang.String r76, java.lang.Integer r77, java.lang.Integer r78, long r79, long r81, com.arflix.tv.data.model.MediaType r83, int r84, d7.d<? super x6.t0> r85) {
        /*
            Method dump skipped, instruction units count: 747
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.appendHomeServerSourcesInBackground(java.lang.String, java.lang.Integer, java.lang.Integer, long, long, com.arflix.tv.data.model.MediaType, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object appendVodSourceInBackground(java.lang.String r77, java.lang.Integer r78, java.lang.Integer r79, long r80, long r82, com.arflix.tv.data.model.MediaType r84, int r85, d7.d<? super x6.t0> r86) {
        /*
            Method dump skipped, instruction units count: 587
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.appendVodSourceInBackground(java.lang.String, java.lang.Integer, java.lang.Integer, long, long, com.arflix.tv.data.model.MediaType, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e autoPlayMinQualityKey() {
        return this.profileManager.profileStringKey("auto_play_min_quality");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e autoPlaySingleSourceKey() {
        return this.profileManager.profileBooleanKey("auto_play_single_source");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PlayTarget buildPlayTarget(MediaType mediaType, SeasonProgressResult result, ResumeInfo resumeInfo) {
        int i10 = 1;
        if (resumeInfo != null) {
            return new PlayTarget(resumeInfo.getSeason(), resumeInfo.getEpisode(), resumeInfo.getLabel(), Long.valueOf(resumeInfo.getPositionMs()));
        }
        if (mediaType == MediaType.MOVIE || result == null) {
            return null;
        }
        if (!result.getHasWatched()) {
            return new PlayTarget(i10, i10, this.context.getString(R.string.play_start_s1e1), null, 8, null);
        }
        x6.x nextUnwatched = result.getNextUnwatched();
        if (nextUnwatched == null) {
            return new PlayTarget(i10, i10, this.context.getString(R.string.play_start_s1e1), null, 8, null);
        }
        Object obj = nextUnwatched.f22608i;
        Object obj2 = nextUnwatched.f22609l;
        return new PlayTarget((Integer) obj, (Integer) obj2, this.context.getString(R.string.continue_season_episode, obj, obj2), null, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object buildResumeFromProgress(MediaType mediaType, int i10, Integer num, Integer num2, float f10, long j10, long j11, boolean z, d7.d<? super ResumeInfo> dVar) {
        long j12 = j11 > 86400 ? j11 / 1000 : j11;
        long jO = j10 > 86400 ? j10 / 1000 : j10;
        float fM = qb.d.m(f10, 0.0f, 1.0f);
        if (jO <= 0 && j12 <= 0) {
            return null;
        }
        if (mediaType == MediaType.TV && num != null && num2 != null && jO > 0 && ((fM <= 0.03f && j12 < 60) || (1 <= j12 && j12 < 60 && jO >= 60))) {
            return null;
        }
        if (jO <= 0) {
            jO = (!z || j12 <= 0 || fM <= 0.0f) ? 0L : (long) (j12 * fM);
        }
        if (j12 > 0 && jO > 0) {
            if (j12 < 1) {
                j12 = 1;
            }
            jO = qb.d.o(jO, 1L, j12);
        }
        if (jO <= 0) {
            return null;
        }
        String resumeTime = formatResumeTime(jO);
        if (kotlin.text.o.h0(resumeTime)) {
            return null;
        }
        if (mediaType == MediaType.MOVIE) {
            return new ResumeInfo(null, null, this.context.getString(R.string.continue_at, resumeTime), jO * 1000, 3, null);
        }
        if (num != null) {
            int iIntValue = num.intValue();
            if (num2 != null) {
                int iIntValue2 = num2.intValue();
                return new ResumeInfo(new Integer(iIntValue), new Integer(iIntValue2), this.context.getString(R.string.continue_season_episode_at, new Integer(iIntValue), new Integer(iIntValue2), resumeTime), jO * 1000);
            }
        }
        return null;
    }

    public static /* synthetic */ Object buildResumeFromProgress$default(DetailsViewModel detailsViewModel, MediaType mediaType, int i10, Integer num, Integer num2, float f10, long j10, long j11, boolean z, d7.d dVar, int i11, Object obj) {
        return detailsViewModel.buildResumeFromProgress(mediaType, i10, num, num2, f10, j10, j11, (i11 & 128) != 0 ? true : z, dVar);
    }

    private final String cleanCommunityReviewText(String raw) {
        DetailsVMRegexes detailsVMRegexes = DetailsVMRegexes.INSTANCE;
        return com.arflix.tv.data.repository.g.o(detailsVMRegexes.getReviewWhitespaceRegex(), detailsVMRegexes.getReviewMarkdownNoiseRegex().f(" ", detailsVMRegexes.getReviewHtmlTagRegex().f(" ", detailsVMRegexes.getReviewMarkdownLinkRegex().f("$1", raw))), " ");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(8:63|68|31|32|72|33|(8:36|37|38|44|(0)|47|(0)(0)|(0))|35) */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00c9, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00ca, code lost:
    
        r3 = r4;
        r5 = r17;
        r4 = r18;
        r8 = r21;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00f5 A[Catch: Exception -> 0x01a6, TryCatch #3 {Exception -> 0x01a6, blocks: (B:44:0x00e7, B:47:0x00ed, B:63:0x017b, B:43:0x00de, B:64:0x0183, B:50:0x00f5, B:51:0x00fd, B:53:0x0103, B:58:0x013b, B:60:0x013f, B:21:0x0058, B:28:0x0083, B:24:0x0064), top: B:74:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x017b A[Catch: Exception -> 0x01a6, TryCatch #3 {Exception -> 0x01a6, blocks: (B:44:0x00e7, B:47:0x00ed, B:63:0x017b, B:43:0x00de, B:64:0x0183, B:50:0x00f5, B:51:0x00fd, B:53:0x0103, B:58:0x013b, B:60:0x013f, B:21:0x0058, B:28:0x0083, B:24:0x0064), top: B:74:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x00ba -> B:37:0x00c1). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00de -> B:44:0x00e7). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object deriveNextUnwatchedPlayTarget(int r26, java.util.Set<java.lang.String> r27, d7.d<? super com.arflix.tv.ui.screens.details.PlayTarget> r28) {
        /*
            Method dump skipped, instruction units count: 423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.deriveNextUnwatchedPlayTarget(int, java.util.Set, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02bd A[Catch: Exception -> 0x0072, TRY_LEAVE, TryCatch #8 {Exception -> 0x0072, blocks: (B:14:0x006b, B:18:0x009e, B:36:0x015a, B:39:0x0176, B:101:0x02b1, B:104:0x02bd, B:91:0x0284, B:95:0x028b, B:97:0x0291, B:90:0x027e, B:47:0x01af, B:58:0x01df, B:60:0x01ec, B:62:0x01f0, B:75:0x0237, B:78:0x023d, B:74:0x0231, B:50:0x01b6, B:54:0x01be, B:63:0x0201, B:64:0x0212, B:66:0x0218, B:68:0x0225, B:72:0x022c), top: B:289:0x0032, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0320  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0393  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x039d  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x03e9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0426  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0436 A[Catch: Exception -> 0x031c, TRY_LEAVE, TryCatch #12 {Exception -> 0x031c, blocks: (B:198:0x046a, B:206:0x048b, B:184:0x0422, B:187:0x0427, B:191:0x0436, B:194:0x0449, B:183:0x041c, B:166:0x03df, B:169:0x03e4, B:165:0x03d8, B:129:0x036d, B:132:0x0374, B:128:0x0366, B:110:0x0310, B:106:0x02e9), top: B:289:0x0032 }] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0479  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0484  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0488  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x048b A[Catch: Exception -> 0x031c, TRY_LEAVE, TryCatch #12 {Exception -> 0x031c, blocks: (B:198:0x046a, B:206:0x048b, B:184:0x0422, B:187:0x0427, B:191:0x0436, B:194:0x0449, B:183:0x041c, B:166:0x03df, B:169:0x03e4, B:165:0x03d8, B:129:0x036d, B:132:0x0374, B:128:0x0366, B:110:0x0310, B:106:0x02e9), top: B:289:0x0032 }] */
    /* JADX WARN: Removed duplicated region for block: B:216:0x04f0  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x04f8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:231:0x051f  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x054d  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x054f  */
    /* JADX WARN: Removed duplicated region for block: B:317:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:318:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01f0 A[Catch: Exception -> 0x0072, TRY_LEAVE, TryCatch #8 {Exception -> 0x0072, blocks: (B:14:0x006b, B:18:0x009e, B:36:0x015a, B:39:0x0176, B:101:0x02b1, B:104:0x02bd, B:91:0x0284, B:95:0x028b, B:97:0x0291, B:90:0x027e, B:47:0x01af, B:58:0x01df, B:60:0x01ec, B:62:0x01f0, B:75:0x0237, B:78:0x023d, B:74:0x0231, B:50:0x01b6, B:54:0x01be, B:63:0x0201, B:64:0x0212, B:66:0x0218, B:68:0x0225, B:72:0x022c), top: B:289:0x0032, inners: #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0289  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0291 A[Catch: Exception -> 0x0072, TryCatch #8 {Exception -> 0x0072, blocks: (B:14:0x006b, B:18:0x009e, B:36:0x015a, B:39:0x0176, B:101:0x02b1, B:104:0x02bd, B:91:0x0284, B:95:0x028b, B:97:0x0291, B:90:0x027e, B:47:0x01af, B:58:0x01df, B:60:0x01ec, B:62:0x01f0, B:75:0x0237, B:78:0x023d, B:74:0x0231, B:50:0x01b6, B:54:0x01be, B:63:0x0201, B:64:0x0212, B:66:0x0218, B:68:0x0225, B:72:0x022c), top: B:289:0x0032, inners: #5 }] */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v9 */
    /* JADX WARN: Type inference failed for: r22v0, types: [com.arflix.tv.ui.screens.details.DetailsViewModel] */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v28, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r4v70 */
    /* JADX WARN: Type inference failed for: r4v74, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r4v81 */
    /* JADX WARN: Type inference failed for: r4v82 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v24, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v31 */
    /* JADX WARN: Type inference failed for: r5v32, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v35 */
    /* JADX WARN: Type inference failed for: r5v37, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r5v65 */
    /* JADX WARN: Type inference failed for: r5v66 */
    /* JADX WARN: Type inference failed for: r5v67 */
    /* JADX WARN: Type inference failed for: r5v68 */
    /* JADX WARN: Type inference failed for: r6v30 */
    /* JADX WARN: Type inference failed for: r6v31 */
    /* JADX WARN: Type inference failed for: r6v37 */
    /* JADX WARN: Type inference failed for: r6v38, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v39 */
    /* JADX WARN: Type inference failed for: r6v44 */
    /* JADX WARN: Type inference failed for: r6v45 */
    /* JADX WARN: Type inference failed for: r6v46 */
    /* JADX WARN: Type inference failed for: r6v47 */
    /* JADX WARN: Type inference failed for: r6v50 */
    /* JADX WARN: Type inference failed for: r6v51 */
    /* JADX WARN: Type inference failed for: r6v55, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r6v66 */
    /* JADX WARN: Type inference failed for: r6v67 */
    /* JADX WARN: Type inference failed for: r6v68 */
    /* JADX WARN: Type inference failed for: r6v69 */
    /* JADX WARN: Type inference failed for: r6v70 */
    /* JADX WARN: Type inference failed for: r6v71 */
    /* JADX WARN: Type inference failed for: r6v72 */
    /* JADX WARN: Type inference failed for: r6v73 */
    /* JADX WARN: Type inference failed for: r7v40 */
    /* JADX WARN: Type inference failed for: r7v51 */
    /* JADX WARN: Type inference failed for: r7v60 */
    /* JADX WARN: Type inference failed for: r9v22, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchResumeInfo(int r23, com.arflix.tv.data.model.MediaType r24, java.lang.Integer r25, java.lang.Integer r26, d7.d<? super com.arflix.tv.ui.screens.details.ResumeInfo> r27) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1486
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.fetchResumeInfo(int, com.arflix.tv.data.model.MediaType, java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object fetchResumeInfo$default(DetailsViewModel detailsViewModel, int i10, MediaType mediaType, Integer num, Integer num2, d7.d dVar, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            num = null;
        }
        if ((i11 & 8) != 0) {
            num2 = null;
        }
        return detailsViewModel.fetchResumeInfo(i10, mediaType, num, num2, dVar);
    }

    private static final ResumeInfo fetchResumeInfo$dropIfWatchedEpisode(ResumeInfo resumeInfo, MediaType mediaType, int i10, Set<String> set) {
        if (resumeInfo == null) {
            return null;
        }
        if (mediaType == MediaType.TV) {
            Integer season = resumeInfo.getSeason();
            if (season != null) {
                int iIntValue = season.intValue();
                Integer episode = resumeInfo.getEpisode();
                if (episode != null) {
                    int iIntValue2 = episode.intValue();
                    StringBuilder sbV = androidx.compose.foundation.c.v("show_tmdb:", i10, ":", iIntValue, ":");
                    sbV.append(iIntValue2);
                    if (set.contains(sbV.toString())) {
                        return null;
                    }
                }
            }
            return null;
        }
        return resumeInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0118 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchResumeInfoFromHistoryOnly(int r20, com.arflix.tv.data.model.MediaType r21, d7.d<? super com.arflix.tv.ui.screens.details.ResumeInfo> r22) {
        /*
            Method dump skipped, instruction units count: 347
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.fetchResumeInfoFromHistoryOnly(int, com.arflix.tv.data.model.MediaType, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(10:113|114|213|140|(8:142|(2:143|(2:145|(2:233|147)(1:234))(2:232|148))|149|(2:151|152)|153|154|99|(7:101|209|102|103|230|104|(1:261)(4:107|221|108|(4:117|228|118|(7:123|(8:126|127|217|128|129|130|(2:246|134)(1:243)|124)|244|114|213|140|(0))(4:122|213|140|(0)))(0)))(11:160|205|161|(6:164|(1:174)(4:168|(0)(1:172)|(3:236|176|239)(1:238)|237)|173|(0)(0)|237|162)|235|177|(1:182)(1:181)|183|(3:185|(3:190|(3:193|(3:241|195|197)(1:242)|191)|240)(1:189)|196)(1:197)|198|262))|155|153|154|99|(0)(0)) */
    /* JADX WARN: Can't wrap try/catch for region: R(17:0|2|(2:4|(1:6)(1:8))(0)|7|9|(1:223)|(4:(1:(1:(1:(7:14|207|15|16|221|108|(4:110|224|111|(10:113|114|213|140|(8:142|(2:143|(2:145|(2:233|147)(1:234))(2:232|148))|149|(2:151|152)|153|154|99|(7:101|209|102|103|230|104|(1:261)(4:107|221|108|(4:117|228|118|(7:123|(8:126|127|217|128|129|130|(2:246|134)(1:243)|124)|244|114|213|140|(0))(4:122|213|140|(0)))(0)))(11:160|205|161|(6:164|(1:174)(4:168|(0)(1:172)|(3:236|176|239)(1:238)|237)|173|(0)(0)|237|162)|235|177|(1:182)(1:181)|183|(3:185|(3:190|(3:193|(3:241|195|197)(1:242)|191)|240)(1:189)|196)(1:197)|198|262))|155|153|154|99|(0)(0))(9:116|213|140|(0)|155|153|154|99|(0)(0)))(0))(2:18|19))(10:20|21|22|80|(1:82)(2:83|(1:85)(4:86|(2:89|87)|247|90))|91|(3:93|(1:95)|96)|98|99|(0)(0)))(3:24|25|26))(13:29|215|30|34|(1:36)|37|(4:40|(3:249|42|252)(1:251)|250|38)|248|43|(4:46|(1:258)(5:255|50|(1:52)(1:53)|54|259)|256|44)|253|55|(9:57|72|73|211|74|75|219|76|(2:78|260)(8:79|80|(0)(0)|91|(0)|98|99|(0)(0)))(5:58|59|226|60|(2:62|106)))|219|76|(0)(0))|63|67|(1:69)(1:70)|71|72|73|211|74|75|(1:(0))) */
    /* JADX WARN: Can't wrap try/catch for region: R(7:101|209|102|103|230|104|(1:261)(4:107|221|108|(4:117|228|118|(7:123|(8:126|127|217|128|129|130|(2:246|134)(1:243)|124)|244|114|213|140|(0))(4:122|213|140|(0)))(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x0347, code lost:
    
        t7.a.P();
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x034a, code lost:
    
        throw r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x034b, code lost:
    
        r0 = r3;
        r3 = r10;
        r4 = r19;
        r7 = r21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x03e6, code lost:
    
        r18 = r14;
        r0 = r19;
        r8 = r23;
        r3 = r3;
        r7 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x04ac, code lost:
    
        r18 = null;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:110:0x02c2, B:117:0x02ea], limit reached: 260 */
    /* JADX WARN: Path cross not found for [B:117:0x02ea, B:110:0x02c2], limit reached: 260 */
    /* JADX WARN: Path cross not found for [B:142:0x037a, B:155:0x03db], limit reached: 260 */
    /* JADX WARN: Path cross not found for [B:155:0x03db, B:142:0x037a], limit reached: 260 */
    /* JADX WARN: Removed duplicated region for block: B:101:0x026b A[Catch: Exception -> 0x0252, TRY_LEAVE, TryCatch #7 {Exception -> 0x0252, blocks: (B:99:0x0265, B:101:0x026b, B:80:0x01d8, B:82:0x0212, B:91:0x023b, B:93:0x0241, B:96:0x024a, B:98:0x0256, B:83:0x0215, B:85:0x021f, B:86:0x0224, B:87:0x022c, B:89:0x0232, B:76:0x01cb), top: B:219:0x01cb }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x037a A[Catch: Exception -> 0x034b, TryCatch #4 {Exception -> 0x034b, blocks: (B:140:0x0361, B:142:0x037a, B:143:0x0382, B:145:0x0388, B:149:0x03b7, B:151:0x03bb, B:130:0x0336, B:132:0x033c, B:135:0x0347, B:136:0x034a), top: B:213:0x0361 }] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0458 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0404 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0212 A[Catch: Exception -> 0x0252, TryCatch #7 {Exception -> 0x0252, blocks: (B:99:0x0265, B:101:0x026b, B:80:0x01d8, B:82:0x0212, B:91:0x023b, B:93:0x0241, B:96:0x024a, B:98:0x0256, B:83:0x0215, B:85:0x021f, B:86:0x0224, B:87:0x022c, B:89:0x0232, B:76:0x01cb), top: B:219:0x01cb }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0215 A[Catch: Exception -> 0x0252, TryCatch #7 {Exception -> 0x0252, blocks: (B:99:0x0265, B:101:0x026b, B:80:0x01d8, B:82:0x0212, B:91:0x023b, B:93:0x0241, B:96:0x024a, B:98:0x0256, B:83:0x0215, B:85:0x021f, B:86:0x0224, B:87:0x022c, B:89:0x0232, B:76:0x01cb), top: B:219:0x01cb }] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0241 A[Catch: Exception -> 0x0252, TryCatch #7 {Exception -> 0x0252, blocks: (B:99:0x0265, B:101:0x026b, B:80:0x01d8, B:82:0x0212, B:91:0x023b, B:93:0x0241, B:96:0x024a, B:98:0x0256, B:83:0x0215, B:85:0x021f, B:86:0x0224, B:87:0x022c, B:89:0x0232, B:76:0x01cb), top: B:219:0x01cb }] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v57 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r14v5, types: [x6.x] */
    /* JADX WARN: Type inference failed for: r21v1 */
    /* JADX WARN: Type inference failed for: r21v2 */
    /* JADX WARN: Type inference failed for: r21v3 */
    /* JADX WARN: Type inference failed for: r21v4 */
    /* JADX WARN: Type inference failed for: r21v5 */
    /* JADX WARN: Type inference failed for: r21v6 */
    /* JADX WARN: Type inference failed for: r21v7 */
    /* JADX WARN: Type inference failed for: r21v8 */
    /* JADX WARN: Type inference failed for: r21v9 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.lang.Object, x6.x] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v24, types: [java.lang.Object, java.util.LinkedHashMap, java.util.Map] */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v3, types: [int] */
    /* JADX WARN: Type inference failed for: r3v37 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v40 */
    /* JADX WARN: Type inference failed for: r3v41 */
    /* JADX WARN: Type inference failed for: r3v42 */
    /* JADX WARN: Type inference failed for: r3v43 */
    /* JADX WARN: Type inference failed for: r3v44 */
    /* JADX WARN: Type inference failed for: r3v45 */
    /* JADX WARN: Type inference failed for: r3v46 */
    /* JADX WARN: Type inference failed for: r3v47 */
    /* JADX WARN: Type inference failed for: r3v48 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v56 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v24 */
    /* JADX WARN: Type inference failed for: r7v25, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r7v26 */
    /* JADX WARN: Type inference failed for: r7v28 */
    /* JADX WARN: Type inference failed for: r7v29 */
    /* JADX WARN: Type inference failed for: r7v31 */
    /* JADX WARN: Type inference failed for: r7v37 */
    /* JADX WARN: Type inference failed for: r7v58 */
    /* JADX WARN: Type inference failed for: r7v59 */
    /* JADX WARN: Type inference failed for: r7v60 */
    /* JADX WARN: Type inference failed for: r7v61 */
    /* JADX WARN: Type inference failed for: r7v62 */
    /* JADX WARN: Type inference failed for: r7v63 */
    /* JADX WARN: Type inference failed for: r7v64 */
    /* JADX WARN: Type inference failed for: r7v65 */
    /* JADX WARN: Type inference failed for: r7v66 */
    /* JADX WARN: Type inference failed for: r7v67 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:107:0x02ab -> B:221:0x02b2). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:159:0x03f3 -> B:154:0x03d7). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetchSeasonProgress(int r28, d7.d<? super com.arflix.tv.ui.screens.details.SeasonProgressResult> r29) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1209
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.fetchSeasonProgress(int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean fetchSeasonProgress$lambda$3(TmdbTvSeason tmdbTvSeason) {
        return tmdbTvSeason.getSeasonNumber() > 0 && tmdbTvSeason.getEpisodeCount() > 0;
    }

    private final String formatResumeTime(long seconds) {
        if (seconds < 0) {
            seconds = 0;
        }
        long j10 = 3600;
        long j11 = seconds / j10;
        long j12 = 60;
        long j13 = (seconds % j10) / j12;
        long j14 = seconds % j12;
        return j11 > 0 ? String.format("%d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j11), Long.valueOf(j13), Long.valueOf(j14)}, 3)) : String.format("%d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j13), Long.valueOf(j14)}, 2));
    }

    private final boolean isBlankRating(String value) {
        return kotlin.text.o.h0(value) || kotlin.jvm.internal.p.a(value, IdManager.DEFAULT_VERSION_NAME) || kotlin.jvm.internal.p.a(value, "0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isPendingDebridStream(StreamSource stream) {
        String lowerCase = kotlin.collections.x.u0(kotlin.collections.r.X(new String[]{stream.getSource(), stream.getAddonName(), stream.getQuality(), stream.getUrl()}), " ", null, null, null, 62).toLowerCase(Locale.ROOT);
        List listE = t7.a.E("torrent being downloaded", "being downloaded", "still downloading", "queued", "not cached", "uncached", "cache pending", "caching", "processing torrent", "download in progress");
        if (!listE.isEmpty()) {
            Iterator it = listE.iterator();
            while (it.hasNext()) {
                if (kotlin.text.o.T(lowerCase, (String) it.next(), false)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean isSpammyReviewText(String raw) {
        String string = kotlin.text.o.L0(raw).toString();
        if (!kotlin.text.o.h0(string)) {
            DetailsVMRegexes detailsVMRegexes = DetailsVMRegexes.INSTANCE;
            if (!detailsVMRegexes.getReviewSpamRegex().a(string) && !detailsVMRegexes.getReviewDomainRegex().a(string)) {
                int i10 = 0;
                for (int i11 = 0; i11 < string.length(); i11++) {
                    if (string.charAt(i11) == '$') {
                        i10++;
                    }
                }
                if (i10 <= 2) {
                    int i12 = 0;
                    for (int i13 = 0; i13 < string.length(); i13++) {
                        if (string.charAt(i13) == '!') {
                            i12++;
                        }
                    }
                    if (i12 <= 6) {
                        int i14 = 0;
                        for (int i15 = 0; i15 < string.length(); i15++) {
                            if (!com.google.common.util.concurrent.r0.u(string.charAt(i15))) {
                                i14++;
                            }
                        }
                        if (i14 < 1) {
                            i14 = 1;
                        }
                        int i16 = 0;
                        for (int i17 = 0; i17 < string.length(); i17++) {
                            if (Character.isLetter(string.charAt(i17))) {
                                i16++;
                            }
                        }
                        if (i16 / i14 >= 0.45f) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0126 A[LOOP:0: B:36:0x0120->B:38:0x0126, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0103 -> B:32:0x010a). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadAnimeDisplaySeason(int r29, int r30, com.arflix.tv.util.AnimeSeasonStructure r31, d7.d<? super java.util.List<com.arflix.tv.data.model.Episode>> r32) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 433
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.loadAnimeDisplaySeason(int, int, com.arflix.tv.util.AnimeSeasonStructure, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00cf, code lost:
    
        if (r13 == r9) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00e8, code lost:
    
        if (r13 == r9) goto L44;
     */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadCommunityComments(com.arflix.tv.data.model.MediaType r11, java.lang.String r12, d7.d<? super java.util.List<com.arflix.tv.data.api.TraktComment>> r13) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 291
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.loadCommunityComments(com.arflix.tv.data.model.MediaType, java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadCommunityReviews(com.arflix.tv.data.model.MediaType r7, int r8, java.lang.String r9, d7.d<? super java.util.List<com.arflix.tv.data.model.Review>> r10) throws java.lang.Exception {
        /*
            r6 = this;
            boolean r0 = r10 instanceof com.arflix.tv.ui.screens.details.DetailsViewModel.C13171
            if (r0 == 0) goto L13
            r0 = r10
            com.arflix.tv.ui.screens.details.DetailsViewModel$loadCommunityReviews$1 r0 = (com.arflix.tv.ui.screens.details.DetailsViewModel.C13171) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.ui.screens.details.DetailsViewModel$loadCommunityReviews$1 r0 = new com.arflix.tv.ui.screens.details.DetailsViewModel$loadCommunityReviews$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            r4 = 0
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L5d
            if (r1 == r3) goto L4b
            if (r1 != r2) goto L43
            java.lang.Object r7 = r0.L$5
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r7 = r0.L$4
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r7 = r0.L$3
            java.util.List r7 = (java.util.List) r7
            java.lang.Object r7 = r0.L$2
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r7 = r0.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r7 = r0.L$0
            com.arflix.tv.data.model.MediaType r7 = (com.arflix.tv.data.model.MediaType) r7
            k2.c.G(r10)
            return r10
        L43:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L4b:
            int r8 = r0.I$0
            java.lang.Object r7 = r0.L$2
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r7 = r0.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r7 = r0.L$0
            com.arflix.tv.data.model.MediaType r7 = (com.arflix.tv.data.model.MediaType) r7
            k2.c.G(r10)
            goto L84
        L5d:
            k2.c.G(r10)
            if (r9 == 0) goto L6f
            java.lang.String r10 = "tt"
            boolean r10 = kotlin.text.u.P(r9, r10, r3)
            if (r10 == 0) goto L6c
            goto L6d
        L6c:
            r9 = r4
        L6d:
            if (r9 != 0) goto L73
        L6f:
            java.lang.String r9 = java.lang.String.valueOf(r8)
        L73:
            r0.L$0 = r7
            r0.L$1 = r4
            r0.L$2 = r4
            r0.I$0 = r8
            r0.label = r3
            java.lang.Object r10 = r6.loadCommunityComments(r7, r9, r0)
            if (r10 != r5) goto L84
            goto Lb3
        L84:
            java.util.List r10 = (java.util.List) r10
            java.util.List r9 = r6.toFilteredCommunityReviews(r10, r3)
            boolean r1 = r9.isEmpty()
            if (r1 != 0) goto L91
            goto L96
        L91:
            r9 = 0
            java.util.List r9 = r6.toFilteredCommunityReviews(r10, r9)
        L96:
            int r10 = r9.size()
            if (r10 < r3) goto L9d
            return r9
        L9d:
            r0.L$0 = r4
            r0.L$1 = r4
            r0.L$2 = r4
            r0.L$3 = r4
            r0.L$4 = r4
            r0.L$5 = r4
            r0.I$0 = r8
            r0.label = r2
            java.lang.Object r7 = r6.loadFilteredTmdbReviews(r7, r8, r0)
            if (r7 != r5) goto Lb4
        Lb3:
            return r5
        Lb4:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.loadCommunityReviews(com.arflix.tv.data.model.MediaType, int, java.lang.String, d7.d):java.lang.Object");
    }

    public static /* synthetic */ void loadDetails$default(DetailsViewModel detailsViewModel, MediaType mediaType, int i10, Integer num, Integer num2, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            num = null;
        }
        if ((i11 & 8) != 0) {
            num2 = null;
        }
        detailsViewModel.loadDetails(mediaType, i10, num, num2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadFilteredTmdbReviews(com.arflix.tv.data.model.MediaType r5, int r6, d7.d<? super java.util.List<com.arflix.tv.data.model.Review>> r7) throws java.lang.Exception {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.arflix.tv.ui.screens.details.DetailsViewModel.C13191
            if (r0 == 0) goto L13
            r0 = r7
            com.arflix.tv.ui.screens.details.DetailsViewModel$loadFilteredTmdbReviews$1 r0 = (com.arflix.tv.ui.screens.details.DetailsViewModel.C13191) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.ui.screens.details.DetailsViewModel$loadFilteredTmdbReviews$1 r0 = new com.arflix.tv.ui.screens.details.DetailsViewModel$loadFilteredTmdbReviews$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            int r1 = r0.label
            r2 = 1
            if (r1 == 0) goto L31
            if (r1 != r2) goto L29
            java.lang.Object r5 = r0.L$0
            com.arflix.tv.data.model.MediaType r5 = (com.arflix.tv.data.model.MediaType) r5
            k2.c.G(r7)
            goto L46
        L29:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L31:
            k2.c.G(r7)
            com.arflix.tv.data.repository.MediaRepository r7 = r4.mediaRepository
            r1 = 0
            r0.L$0 = r1
            r0.I$0 = r6
            r0.label = r2
            java.lang.Object r7 = r7.getReviews(r5, r6, r0)
            e7.a r5 = e7.a.f15033i
            if (r7 != r5) goto L46
            return r5
        L46:
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            ga.p r5 = new ga.p
            r6 = 3
            r5.<init>(r7, r6)
            com.arflix.tv.ui.screens.details.e1 r6 = new com.arflix.tv.ui.screens.details.e1
            r7 = 2
            r6.<init>(r4, r7)
            ga.i r5 = ga.r.J(r5, r6)
            com.arflix.tv.ui.screens.details.e1 r6 = new com.arflix.tv.ui.screens.details.e1
            r7 = 3
            r6.<init>(r4, r7)
            ga.i r5 = ga.r.Q(r5, r6)
            com.arflix.tv.ui.screens.details.p1 r6 = new com.arflix.tv.ui.screens.details.p1
            r7 = 14
            r6.<init>(r7)
            ga.c r7 = new ga.c
            r0 = 0
            r7.<init>(r5, r6, r0)
            com.arflix.tv.ui.screens.details.DetailsViewModel$loadFilteredTmdbReviews$$inlined$compareByDescending$1 r5 = new com.arflix.tv.ui.screens.details.DetailsViewModel$loadFilteredTmdbReviews$$inlined$compareByDescending$1
            r5.<init>()
            com.arflix.tv.ui.screens.details.DetailsViewModel$loadFilteredTmdbReviews$$inlined$thenByDescending$1 r6 = new com.arflix.tv.ui.screens.details.DetailsViewModel$loadFilteredTmdbReviews$$inlined$thenByDescending$1
            r6.<init>()
            ga.l r5 = new ga.l
            r0 = 1
            r5.<init>(r7, r6, r0)
            r6 = 8
            ga.m r5 = ga.r.S(r5, r6)
            java.util.List r5 = ga.r.U(r5)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.loadFilteredTmdbReviews(com.arflix.tv.data.model.MediaType, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean loadFilteredTmdbReviews$lambda$0(DetailsViewModel detailsViewModel, Review review) {
        return detailsViewModel.isSpammyReviewText(review.getContent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Review loadFilteredTmdbReviews$lambda$1(DetailsViewModel detailsViewModel, Review review) {
        String strCleanCommunityReviewText = detailsViewModel.cleanCommunityReviewText(review.getContent());
        int length = strCleanCommunityReviewText.length();
        if (40 <= length && length < 1401) {
            List listH = DetailsVMRegexes.INSTANCE.getReviewWhitespaceRegex().h(strCleanCommunityReviewText);
            int i10 = 0;
            if (!listH.isEmpty()) {
                Iterator it = listH.iterator();
                while (it.hasNext()) {
                    if (((String) it.next()).length() > 1 && (i10 = i10 + 1) < 0) {
                        t7.a.P();
                        throw null;
                    }
                }
            }
            if (i10 >= 8) {
                return Review.copy$default(review, null, null, null, null, strCleanCommunityReviewText, null, null, 111, null);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String loadFilteredTmdbReviews$lambda$2(Review review) {
        String authorUsername = review.getAuthorUsername();
        if (kotlin.text.o.h0(authorUsername)) {
            authorUsername = review.getAuthor();
        }
        return ((Object) authorUsername) + ":" + kotlin.text.o.I0(140, review.getContent().toLowerCase(Locale.US));
    }

    public static /* synthetic */ void loadStreams$default(DetailsViewModel detailsViewModel, String str, EpisodeIdentity episodeIdentity, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            episodeIdentity = null;
        }
        detailsViewModel.loadStreams(str, episodeIdentity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MediaItem mergeItem(MediaItem primary, MediaItem fallback) {
        if (fallback == null) {
            return primary;
        }
        String title = primary.getTitle();
        if (kotlin.text.o.h0(title)) {
            title = fallback.getTitle();
        }
        String str = title;
        String subtitle = primary.getSubtitle();
        if (kotlin.text.o.h0(subtitle)) {
            subtitle = fallback.getSubtitle();
        }
        String str2 = subtitle;
        String overview = primary.getOverview();
        if (kotlin.text.o.h0(overview)) {
            overview = fallback.getOverview();
        }
        String str3 = overview;
        String year = primary.getYear();
        if (kotlin.text.o.h0(year)) {
            year = fallback.getYear();
        }
        String str4 = year;
        String releaseDate = primary.getReleaseDate();
        if (releaseDate == null) {
            releaseDate = fallback.getReleaseDate();
        }
        String str5 = releaseDate;
        String rating = primary.getRating();
        if (kotlin.text.o.h0(rating)) {
            rating = fallback.getRating();
        }
        String str6 = rating;
        String duration = primary.getDuration();
        if (kotlin.text.o.h0(duration)) {
            duration = fallback.getDuration();
        }
        String str7 = duration;
        String imdbRating = isBlankRating(primary.getImdbRating()) ? fallback.getImdbRating() : primary.getImdbRating();
        String tmdbRating = isBlankRating(primary.getTmdbRating()) ? fallback.getTmdbRating() : primary.getTmdbRating();
        String image = primary.getImage();
        if (kotlin.text.o.h0(image)) {
            image = fallback.getImage();
        }
        String str8 = image;
        String backdrop = primary.getBackdrop();
        if (backdrop == null) {
            backdrop = fallback.getBackdrop();
        }
        String str9 = backdrop;
        String primaryNetworkLogo = primary.getPrimaryNetworkLogo();
        if (primaryNetworkLogo == null) {
            primaryNetworkLogo = fallback.getPrimaryNetworkLogo();
        }
        String str10 = primaryNetworkLogo;
        List<Integer> genreIds = primary.getGenreIds().isEmpty() ? fallback.getGenreIds() : primary.getGenreIds();
        String originalLanguage = primary.getOriginalLanguage();
        if (originalLanguage == null) {
            originalLanguage = fallback.getOriginalLanguage();
        }
        String str11 = originalLanguage;
        boolean z = primary.isOngoing() || fallback.isOngoing();
        Integer totalEpisodes = primary.getTotalEpisodes();
        if (totalEpisodes == null) {
            totalEpisodes = fallback.getTotalEpisodes();
        }
        Integer num = totalEpisodes;
        Integer watchedEpisodes = primary.getWatchedEpisodes();
        if (watchedEpisodes == null) {
            watchedEpisodes = fallback.getWatchedEpisodes();
        }
        Integer num2 = watchedEpisodes;
        Long budget = primary.getBudget();
        if (budget == null) {
            budget = fallback.getBudget();
        }
        Long l10 = budget;
        Long revenue = primary.getRevenue();
        if (revenue == null) {
            revenue = fallback.getRevenue();
        }
        Long l11 = revenue;
        String status = primary.getStatus();
        if (status == null) {
            status = fallback.getStatus();
        }
        return MediaItem.copy$default(primary, 0, str, str2, str3, str4, str5, str6, str7, imdbRating, tmdbRating, null, str8, str9, 0, false, null, null, genreIds, str11, str10, z, num, num2, null, l10, l11, status, null, null, false, null, 0.0f, 0L, 0, false, null, false, false, null, null, null, null, -125705215, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Episode> normalizeAnimeEpisodesForDisplay(int tmdbId, int displaySeason, MediaItem item, List<Episode> canonicalEpisodes) {
        if (!canonicalEpisodes.isEmpty() && ((Episode) kotlin.collections.x.m0(canonicalEpisodes)).getEpisodeNumber() != 1) {
            AnimeMapper animeMapper = this.animeMapper;
            Integer numValueOf = Integer.valueOf(tmdbId);
            List<Integer> genreIds = item != null ? item.getGenreIds() : null;
            if (genreIds == null) {
                genreIds = kotlin.collections.z.f19728i;
            }
            if (animeMapper.isAnimeContent(numValueOf, genreIds, item != null ? item.getOriginalLanguage() : null)) {
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(canonicalEpisodes, 10));
                int i10 = 0;
                for (Object obj : canonicalEpisodes) {
                    int i11 = i10 + 1;
                    if (i10 < 0) {
                        t7.a.Q();
                        throw null;
                    }
                    Episode episode = (Episode) obj;
                    arrayList.add(Episode.copy$default(episode, 0, i11, displaySeason, null, null, null, 0.0f, null, 0, null, false, new EpisodeIdentity(displaySeason, i11, episode.getSeasonNumber(), episode.getEpisodeNumber(), null, null, null, 112, null), 2041, null));
                    i10 = i11;
                }
                return arrayList;
            }
        }
        return canonicalEpisodes;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0073 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String normalizeAutoPlayMinQuality(java.lang.String r5) {
        /*
            r4 = this;
            if (r5 == 0) goto L13
            java.lang.CharSequence r5 = kotlin.text.o.L0(r5)
            java.lang.String r5 = r5.toString()
            if (r5 == 0) goto L13
            java.util.Locale r0 = java.util.Locale.ROOT
            java.lang.String r5 = r5.toLowerCase(r0)
            goto L14
        L13:
            r5 = 0
        L14:
            java.lang.String r0 = "Any"
            if (r5 == 0) goto L74
            int r1 = r5.hashCode()
            java.lang.String r2 = "720p"
            java.lang.String r3 = "1080p"
            switch(r1) {
                case -1263417397: goto L6a;
                case 1719: goto L5e;
                case 3324: goto L54;
                case 96748: goto L4e;
                case 101346: goto L45;
                case 115761: goto L3b;
                case 1688155: goto L34;
                case 46737913: goto L2d;
                case 47689303: goto L24;
                default: goto L23;
            }
        L23:
            goto L74
        L24:
            java.lang.String r1 = "2160p"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L67
            goto L74
        L2d:
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L73
            goto L74
        L34:
            boolean r5 = r5.equals(r2)
            if (r5 != 0) goto L5d
            goto L74
        L3b:
            java.lang.String r1 = "uhd"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L67
            goto L74
        L45:
            java.lang.String r1 = "fhd"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L73
            goto L74
        L4e:
            java.lang.String r1 = "any"
            r5.equals(r1)
            return r0
        L54:
            java.lang.String r1 = "hd"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L5d
            goto L74
        L5d:
            return r2
        L5e:
            java.lang.String r1 = "4k"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L67
            goto L74
        L67:
            java.lang.String r5 = "4K"
            return r5
        L6a:
            java.lang.String r1 = "fullhd"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L73
            goto L74
        L73:
            return r3
        L74:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.normalizeAutoPlayMinQuality(java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void prefetchStreamsInBackground(String imdbId, Integer season, Integer episode) {
        v1 v1Var = this.prefetchJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.prefetchJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C13261(this.currentMediaType, this, imdbId, season, episode, this.currentMediaId, null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void prewarmVisibleStreams(List<StreamSource> streams) {
        if (streams.isEmpty()) {
            return;
        }
        List listX0 = kotlin.collections.x.X0(streams, 3);
        String strU0 = kotlin.collections.x.u0(listX0, "|", null, null, new p1(10), 30);
        if (kotlin.jvm.internal.p.a(strU0, this.lastStreamListPrewarmKey)) {
            return;
        }
        this.lastStreamListPrewarmKey = strU0;
        v1 v1Var = this.streamListPrewarmJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.streamListPrewarmJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C13291(listX0, null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence prewarmVisibleStreams$lambda$0(StreamSource streamSource) {
        String strG0;
        String addonId = streamSource.getAddonId();
        String source = streamSource.getSource();
        String url = streamSource.getUrl();
        if (url != null) {
            String strG02 = kotlin.text.o.G0('|', url, url);
            strG0 = kotlin.text.o.G0('#', strG02, strG02);
        } else {
            strG0 = null;
        }
        return androidx.compose.material3.d.o(addonId, ":", source, ":", strG0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveExternalIds(com.arflix.tv.data.model.MediaType r6, int r7, d7.d<? super com.arflix.tv.ui.screens.details.DetailsViewModel.ExternalIds> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.arflix.tv.ui.screens.details.DetailsViewModel.C13311
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.ui.screens.details.DetailsViewModel$resolveExternalIds$1 r0 = (com.arflix.tv.ui.screens.details.DetailsViewModel.C13311) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.ui.screens.details.DetailsViewModel$resolveExternalIds$1 r0 = new com.arflix.tv.ui.screens.details.DetailsViewModel$resolveExternalIds$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L3d
            if (r1 == r3) goto L35
            if (r1 != r2) goto L2d
            java.lang.Object r6 = r0.L$0
            com.arflix.tv.data.model.MediaType r6 = (com.arflix.tv.data.model.MediaType) r6
            k2.c.G(r8)     // Catch: java.lang.Exception -> L93
            goto L64
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L35:
            java.lang.Object r6 = r0.L$0
            com.arflix.tv.data.model.MediaType r6 = (com.arflix.tv.data.model.MediaType) r6
            k2.c.G(r8)     // Catch: java.lang.Exception -> L93
            goto L83
        L3d:
            k2.c.G(r8)
            int[] r8 = com.arflix.tv.ui.screens.details.DetailsViewModel.WhenMappings.$EnumSwitchMapping$0     // Catch: java.lang.Exception -> L93
            int r6 = r6.ordinal()     // Catch: java.lang.Exception -> L93
            r6 = r8[r6]     // Catch: java.lang.Exception -> L93
            e7.a r8 = e7.a.f15033i
            if (r6 == r3) goto L6d
            if (r6 != r2) goto L67
            com.arflix.tv.data.api.TmdbApi r6 = r5.tmdbApi     // Catch: java.lang.Exception -> L93
            com.arflix.tv.util.Constants r1 = com.arflix.tv.util.Constants.INSTANCE     // Catch: java.lang.Exception -> L93
            java.lang.String r1 = r1.getTMDB_API_KEY()     // Catch: java.lang.Exception -> L93
            r0.L$0 = r4     // Catch: java.lang.Exception -> L93
            r0.I$0 = r7     // Catch: java.lang.Exception -> L93
            r0.label = r2     // Catch: java.lang.Exception -> L93
            java.lang.Object r6 = r6.getTvExternalIds(r7, r1, r0)     // Catch: java.lang.Exception -> L93
            if (r6 != r8) goto L63
            goto L81
        L63:
            r8 = r6
        L64:
            com.arflix.tv.data.api.TmdbExternalIds r8 = (com.arflix.tv.data.api.TmdbExternalIds) r8     // Catch: java.lang.Exception -> L93
            goto L85
        L67:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException     // Catch: java.lang.Exception -> L93
            r6.<init>()     // Catch: java.lang.Exception -> L93
            throw r6     // Catch: java.lang.Exception -> L93
        L6d:
            com.arflix.tv.data.api.TmdbApi r6 = r5.tmdbApi     // Catch: java.lang.Exception -> L93
            com.arflix.tv.util.Constants r1 = com.arflix.tv.util.Constants.INSTANCE     // Catch: java.lang.Exception -> L93
            java.lang.String r1 = r1.getTMDB_API_KEY()     // Catch: java.lang.Exception -> L93
            r0.L$0 = r4     // Catch: java.lang.Exception -> L93
            r0.I$0 = r7     // Catch: java.lang.Exception -> L93
            r0.label = r3     // Catch: java.lang.Exception -> L93
            java.lang.Object r6 = r6.getMovieExternalIds(r7, r1, r0)     // Catch: java.lang.Exception -> L93
            if (r6 != r8) goto L82
        L81:
            return r8
        L82:
            r8 = r6
        L83:
            com.arflix.tv.data.api.TmdbExternalIds r8 = (com.arflix.tv.data.api.TmdbExternalIds) r8     // Catch: java.lang.Exception -> L93
        L85:
            com.arflix.tv.ui.screens.details.DetailsViewModel$ExternalIds r6 = new com.arflix.tv.ui.screens.details.DetailsViewModel$ExternalIds     // Catch: java.lang.Exception -> L93
            java.lang.String r7 = r8.getImdbId()     // Catch: java.lang.Exception -> L93
            java.lang.Integer r8 = r8.getTvdbId()     // Catch: java.lang.Exception -> L93
            r6.<init>(r7, r8)     // Catch: java.lang.Exception -> L93
            return r6
        L93:
            com.arflix.tv.ui.screens.details.DetailsViewModel$ExternalIds r6 = new com.arflix.tv.ui.screens.details.DetailsViewModel$ExternalIds
            r6.<init>(r4, r4)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.resolveExternalIds(com.arflix.tv.data.model.MediaType, int, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x009f, code lost:
    
        if (r1 == r10) goto L30;
     */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00e9 A[Catch: Exception -> 0x0197, TryCatch #0 {Exception -> 0x0197, blocks: (B:15:0x004a, B:53:0x012d, B:54:0x0137, B:56:0x013d, B:60:0x014c, B:62:0x0150, B:64:0x0156, B:35:0x00b2, B:65:0x015c, B:66:0x0164, B:68:0x016a, B:72:0x0179, B:74:0x017d, B:77:0x0185, B:20:0x0067, B:40:0x00db, B:42:0x00e9, B:47:0x00f5, B:49:0x00fb, B:78:0x018b, B:80:0x0191, B:23:0x0079, B:31:0x00a4, B:33:0x00ac, B:26:0x0080, B:28:0x0086, B:36:0x00b7), top: B:85:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f2  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x013d A[Catch: Exception -> 0x0197, TryCatch #0 {Exception -> 0x0197, blocks: (B:15:0x004a, B:53:0x012d, B:54:0x0137, B:56:0x013d, B:60:0x014c, B:62:0x0150, B:64:0x0156, B:35:0x00b2, B:65:0x015c, B:66:0x0164, B:68:0x016a, B:72:0x0179, B:74:0x017d, B:77:0x0185, B:20:0x0067, B:40:0x00db, B:42:0x00e9, B:47:0x00f5, B:49:0x00fb, B:78:0x018b, B:80:0x0191, B:23:0x0079, B:31:0x00a4, B:33:0x00ac, B:26:0x0080, B:28:0x0086, B:36:0x00b7), top: B:85:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x015c A[Catch: Exception -> 0x0197, TryCatch #0 {Exception -> 0x0197, blocks: (B:15:0x004a, B:53:0x012d, B:54:0x0137, B:56:0x013d, B:60:0x014c, B:62:0x0150, B:64:0x0156, B:35:0x00b2, B:65:0x015c, B:66:0x0164, B:68:0x016a, B:72:0x0179, B:74:0x017d, B:77:0x0185, B:20:0x0067, B:40:0x00db, B:42:0x00e9, B:47:0x00f5, B:49:0x00fb, B:78:0x018b, B:80:0x0191, B:23:0x0079, B:31:0x00a4, B:33:0x00ac, B:26:0x0080, B:28:0x0086, B:36:0x00b7), top: B:85:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x016a A[Catch: Exception -> 0x0197, TryCatch #0 {Exception -> 0x0197, blocks: (B:15:0x004a, B:53:0x012d, B:54:0x0137, B:56:0x013d, B:60:0x014c, B:62:0x0150, B:64:0x0156, B:35:0x00b2, B:65:0x015c, B:66:0x0164, B:68:0x016a, B:72:0x0179, B:74:0x017d, B:77:0x0185, B:20:0x0067, B:40:0x00db, B:42:0x00e9, B:47:0x00f5, B:49:0x00fb, B:78:0x018b, B:80:0x0191, B:23:0x0079, B:31:0x00a4, B:33:0x00ac, B:26:0x0080, B:28:0x0086, B:36:0x00b7), top: B:85:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x017d A[Catch: Exception -> 0x0197, TryCatch #0 {Exception -> 0x0197, blocks: (B:15:0x004a, B:53:0x012d, B:54:0x0137, B:56:0x013d, B:60:0x014c, B:62:0x0150, B:64:0x0156, B:35:0x00b2, B:65:0x015c, B:66:0x0164, B:68:0x016a, B:72:0x0179, B:74:0x017d, B:77:0x0185, B:20:0x0067, B:40:0x00db, B:42:0x00e9, B:47:0x00f5, B:49:0x00fb, B:78:0x018b, B:80:0x0191, B:23:0x0079, B:31:0x00a4, B:33:0x00ac, B:26:0x0080, B:28:0x0086, B:36:0x00b7), top: B:85:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0185 A[Catch: Exception -> 0x0197, TryCatch #0 {Exception -> 0x0197, blocks: (B:15:0x004a, B:53:0x012d, B:54:0x0137, B:56:0x013d, B:60:0x014c, B:62:0x0150, B:64:0x0156, B:35:0x00b2, B:65:0x015c, B:66:0x0164, B:68:0x016a, B:72:0x0179, B:74:0x017d, B:77:0x0185, B:20:0x0067, B:40:0x00db, B:42:0x00e9, B:47:0x00f5, B:49:0x00fb, B:78:0x018b, B:80:0x0191, B:23:0x0079, B:31:0x00a4, B:33:0x00ac, B:26:0x0080, B:28:0x0086, B:36:0x00b7), top: B:85:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x014b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0178 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveRuntimeSeconds(int r19, com.arflix.tv.data.model.MediaType r20, java.lang.Integer r21, java.lang.Integer r22, d7.d<? super java.lang.Long> r23) {
        /*
            Method dump skipped, instruction units count: 413
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.resolveRuntimeSeconds(int, com.arflix.tv.data.model.MediaType, java.lang.Integer, java.lang.Integer, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e showBudgetKey() {
        return this.profileManager.profileBooleanKey("show_budget_on_home");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e showEpisodeRatingsKey() {
        return this.profileManager.profileBooleanKey("show_episode_ratings");
    }

    public static /* synthetic */ void showToast$default(DetailsViewModel detailsViewModel, String str, ToastType toastType, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            toastType = ToastType.INFO;
        }
        detailsViewModel.showToast(str, toastType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<StreamSource> sortPlayableStreamsFirst(List<StreamSource> streams) {
        return kotlin.collections.x.W0(streams, new Comparator() { // from class: com.arflix.tv.ui.screens.details.DetailsViewModel$sortPlayableStreamsFirst$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return com.google.common.util.concurrent.r0.e(Integer.valueOf(this.this$0.isPendingDebridStream((StreamSource) t2) ? 1 : 0), Integer.valueOf(this.this$0.isPendingDebridStream((StreamSource) t10) ? 1 : 0));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:42:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.arflix.tv.data.model.Review toCommunityReview(com.arflix.tv.data.api.TraktComment r10) {
        /*
            Method dump skipped, instruction units count: 213
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.details.DetailsViewModel.toCommunityReview(com.arflix.tv.data.api.TraktComment):com.arflix.tv.data.model.Review");
    }

    private final List<Review> toFilteredCommunityReviews(List<TraktComment> list, boolean z) {
        ga.i iVarJ = ga.r.J(ga.r.I(ga.r.I(new ga.p(list, 3), new p1(9)), new j1(z, 2)), new e1(this, 0));
        final Comparator comparator = new Comparator() { // from class: com.arflix.tv.ui.screens.details.DetailsViewModel$toFilteredCommunityReviews$$inlined$compareByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return com.google.common.util.concurrent.r0.e(Boolean.valueOf(((TraktComment) t10).getReview()), Boolean.valueOf(((TraktComment) t2).getReview()));
            }
        };
        final Comparator comparator2 = new Comparator() { // from class: com.arflix.tv.ui.screens.details.DetailsViewModel$toFilteredCommunityReviews$$inlined$thenByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator.compare(t2, t10);
                return iCompare != 0 ? iCompare : com.google.common.util.concurrent.r0.e(Integer.valueOf(((TraktComment) t10).getLikes()), Integer.valueOf(((TraktComment) t2).getLikes()));
            }
        };
        final Comparator comparator3 = new Comparator() { // from class: com.arflix.tv.ui.screens.details.DetailsViewModel$toFilteredCommunityReviews$$inlined$thenByDescending$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                Integer rating;
                Integer rating2;
                Integer num = 0;
                int iCompare = comparator2.compare(t2, t10);
                if (iCompare != 0) {
                    return iCompare;
                }
                TraktCommentUserStats userStats = ((TraktComment) t10).getUserStats();
                if (userStats == null || (rating = userStats.getRating()) == null) {
                    rating = num;
                }
                TraktCommentUserStats userStats2 = ((TraktComment) t2).getUserStats();
                if (userStats2 != null && (rating2 = userStats2.getRating()) != null) {
                    num = rating2;
                }
                return com.google.common.util.concurrent.r0.e(rating, num);
            }
        };
        return ga.r.U(ga.r.S(new ga.c(ga.r.Q(new ga.l(iVarJ, new Comparator() { // from class: com.arflix.tv.ui.screens.details.DetailsViewModel$toFilteredCommunityReviews$$inlined$thenByDescending$3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator3.compare(t2, t10);
                return iCompare != 0 ? iCompare : com.google.common.util.concurrent.r0.e(((TraktComment) t10).getCreatedAt(), ((TraktComment) t2).getCreatedAt());
            }
        }, 1), new e1(this, 1)), new p1(11), 0), 8));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean toFilteredCommunityReviews$lambda$0(TraktComment traktComment) {
        return traktComment.getParentId() == null && !traktComment.getSpoiler();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean toFilteredCommunityReviews$lambda$1(boolean z, TraktComment traktComment) {
        return !z || traktComment.getReview();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean toFilteredCommunityReviews$lambda$2(DetailsViewModel detailsViewModel, TraktComment traktComment) {
        return detailsViewModel.isSpammyReviewText(traktComment.getComment());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toFilteredCommunityReviews$lambda$8(Review review) {
        return androidx.compose.foundation.c.t(review.getAuthorUsername(), ":", kotlin.text.o.I0(140, review.getContent().toLowerCase(Locale.US)));
    }

    public static /* synthetic */ void toggleWatched$default(DetailsViewModel detailsViewModel, Integer num, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            num = null;
        }
        detailsViewModel.toggleWatched(num);
    }

    public final void closePersonModal() {
        na.q0<DetailsUiState> q0Var = this._uiState;
        q0Var.setValue(DetailsUiState.copy$default((DetailsUiState) q0Var.getValue(), false, null, null, null, null, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -49153, 1048575, null));
    }

    public final void dismissToast() {
        na.q0<DetailsUiState> q0Var = this._uiState;
        q0Var.setValue(DetailsUiState.copy$default((DetailsUiState) q0Var.getValue(), false, null, null, null, null, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -536870913, 1048575, null));
    }

    public final na.h1<DetailsUiState> getUiState() {
        return this.uiState;
    }

    public final void loadDetails(MediaType mediaType, int mediaId, Integer initialSeason, Integer initialEpisode) {
        this.currentMediaType = mediaType;
        this.currentMediaId = mediaId;
        this.animeSeasonStructure = null;
        this.initialLoadComplete = false;
        v1 v1Var = this.vodAppendJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        v1 v1Var2 = this.homeServerAppendJob;
        if (v1Var2 != null) {
            v1Var2.cancel((CancellationException) null);
        }
        v1 v1Var3 = this.streamListPrewarmJob;
        if (v1Var3 != null) {
            v1Var3.cancel((CancellationException) null);
        }
        v1 v1Var4 = this.focusedStreamPrewarmJob;
        if (v1Var4 != null) {
            v1Var4.cancel((CancellationException) null);
        }
        this.lastStreamListPrewarmKey = "";
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C13181(mediaId, mediaType, initialSeason, initialEpisode, null), 3);
    }

    public final void loadPerson(int personId) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C13201(personId, null), 3);
    }

    public final void loadSeason(int seasonNumber) {
        v1 v1Var;
        if (this.currentMediaType != MediaType.TV) {
            return;
        }
        if (((DetailsUiState) this._uiState.getValue()).getCurrentSeason() != seasonNumber || ((DetailsUiState) this._uiState.getValue()).getEpisodes().isEmpty()) {
            if (this.seasonLoadRequestedSeason == seasonNumber && (v1Var = this.seasonLoadJob) != null && v1Var.isActive()) {
                return;
            }
            v1 v1Var2 = this.seasonLoadJob;
            if (v1Var2 != null) {
                v1Var2.cancel((CancellationException) null);
            }
            this.seasonLoadRequestedSeason = seasonNumber;
            this.seasonLoadJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C13211(seasonNumber, null), 3);
        }
    }

    public final void loadStreams(String imdbId, EpisodeIdentity identity) {
        v1 v1Var = this.loadStreamsJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        v1 v1Var2 = this.focusedStreamPrewarmJob;
        if (v1Var2 != null) {
            v1Var2.cancel((CancellationException) null);
        }
        v1 v1Var3 = this.streamListPrewarmJob;
        if (v1Var3 != null) {
            v1Var3.cancel((CancellationException) null);
        }
        v1 v1Var4 = this.homeServerAppendJob;
        if (v1Var4 != null) {
            v1Var4.cancel((CancellationException) null);
        }
        na.q0<DetailsUiState> q0Var = this._uiState;
        DetailsUiState detailsUiState = (DetailsUiState) q0Var.getValue();
        long jCurrentTimeMillis = System.currentTimeMillis();
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        q0Var.setValue(DetailsUiState.copy$default(detailsUiState, false, null, null, null, null, null, null, 0, 0, null, null, null, null, null, false, null, false, zVar, zVar, true, jCurrentTimeMillis, false, null, 0, 0, false, null, false, false, null, null, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -29229057, 1048575, null));
        long j10 = this.loadStreamsRequestId + 1;
        this.loadStreamsRequestId = j10;
        this.loadStreamsJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C13221(imdbId, this, this.currentMediaType, this.currentMediaId, j10, identity, null), 3);
    }

    public final void markEpisodeWatched(int season, int episode, boolean watched) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C13231(season, episode, watched, null), 3);
    }

    public final void markSeasonUnwatched(int season) {
        MediaItem item;
        if (this.currentMediaType == MediaType.TV && (item = ((DetailsUiState) this._uiState.getValue()).getItem()) != null) {
            ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C13241(season, item, null), 3);
        }
    }

    public final void markSeasonWatched(int season) {
        MediaItem item;
        if (this.currentMediaType == MediaType.TV && (item = ((DetailsUiState) this._uiState.getValue()).getItem()) != null) {
            ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C13251(season, item, null), 3);
        }
    }

    public final void prewarmStream(StreamSource stream) {
        v1 v1Var = this.focusedStreamPrewarmJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.focusedStreamPrewarmJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C13271(stream, null), 3);
    }

    public final void prewarmStreamsAround(StreamSource stream, List<StreamSource> streams) {
        if (streams.isEmpty()) {
            return;
        }
        v1 v1Var = this.focusedStreamPrewarmJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.focusedStreamPrewarmJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C13281(streams, stream, this, null), 3);
    }

    public final void recordPlayedEpisode(int mediaId, EpisodeIdentity identity) {
        this.lastPlayedMediaId = mediaId;
        this.lastPlayedIdentity = identity;
    }

    public final void refreshAfterPlayerReturn() {
        int i10 = this.currentMediaId;
        if (i10 == 0 || ((DetailsUiState) this._uiState.getValue()).isLoading() || !this.initialLoadComplete) {
            return;
        }
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C13301(this.currentMediaType, this, i10, null), 3);
    }

    public final EpisodeIdentity resolveEpisodeIdentity(Integer displaySeason, Integer displayEpisode, Integer tmdbSeason, Integer tmdbEpisode) {
        EpisodeIdentity episodeIdentityIdentityForDisplay;
        if (displaySeason != null && displayEpisode != null && tmdbSeason != null && tmdbEpisode != null) {
            AnimeSeasonStructure animeSeasonStructure = this.animeSeasonStructure;
            if (animeSeasonStructure != null && (episodeIdentityIdentityForDisplay = animeSeasonStructure.identityForDisplay(displaySeason.intValue(), displayEpisode.intValue())) != null) {
                return episodeIdentityIdentityForDisplay;
            }
            AnimeSeasonStructure animeSeasonStructure2 = this.animeSeasonStructure;
            episodeIdentityIdentityForTmdb = animeSeasonStructure2 != null ? animeSeasonStructure2.identityForTmdb(tmdbSeason.intValue(), tmdbEpisode.intValue()) : null;
            if (episodeIdentityIdentityForTmdb == null) {
                return new EpisodeIdentity(displaySeason.intValue(), displayEpisode.intValue(), tmdbSeason.intValue(), tmdbEpisode.intValue(), null, null, null, 112, null);
            }
        }
        return episodeIdentityIdentityForTmdb;
    }

    public final void showToast(String message, ToastType type) {
        na.q0<DetailsUiState> q0Var = this._uiState;
        q0Var.setValue(DetailsUiState.copy$default((DetailsUiState) q0Var.getValue(), false, null, null, null, null, null, null, 0, 0, null, null, null, null, null, false, null, false, null, null, false, 0L, false, null, 0, 0, false, null, false, false, message, type, null, null, null, null, null, null, 0, 0, null, null, null, null, null, null, null, false, null, null, null, null, null, -1610612737, 1048575, null));
    }

    public final void toggleWatched(Integer episodeIndex) {
        MediaItem item = ((DetailsUiState) this._uiState.getValue()).getItem();
        if (item == null) {
            return;
        }
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C13331(item, episodeIndex, null), 3);
    }

    public final void toggleWatchlist() {
        MediaItem item = ((DetailsUiState) this._uiState.getValue()).getItem();
        if (item == null) {
            return;
        }
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C13341(item, !((DetailsUiState) this._uiState.getValue()).isInWatchlist(), this, null), 3);
    }
}
