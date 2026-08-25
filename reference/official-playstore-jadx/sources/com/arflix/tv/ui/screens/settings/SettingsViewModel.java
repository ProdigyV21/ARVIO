package com.arflix.tv.ui.screens.settings;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.R;
import com.arflix.tv.data.api.SimklPinResponse;
import com.arflix.tv.data.api.TraktDeviceCode;
import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.CatalogDiscoveryResult;
import com.arflix.tv.data.model.CatalogKind;
import com.arflix.tv.data.model.CatalogPackManifest;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.IptvSnapshot;
import com.arflix.tv.data.model.QualityFilterConfig;
import com.arflix.tv.data.repository.AuthRepository;
import com.arflix.tv.data.repository.AuthState;
import com.arflix.tv.data.repository.CatalogDiscoveryRepository;
import com.arflix.tv.data.repository.CatalogRepository;
import com.arflix.tv.data.repository.CloudSyncRepository;
import com.arflix.tv.data.repository.CollectionTemplateManifest;
import com.arflix.tv.data.repository.HomeServerConnection;
import com.arflix.tv.data.repository.HomeServerRepository;
import com.arflix.tv.data.repository.IptvConfig;
import com.arflix.tv.data.repository.IptvLoadProgress;
import com.arflix.tv.data.repository.IptvPlaylistEntry;
import com.arflix.tv.data.repository.IptvRepository;
import com.arflix.tv.data.repository.IptvRepositoryKt;
import com.arflix.tv.data.repository.LauncherContinueWatchingRepository;
import com.arflix.tv.data.repository.MdbListRepository;
import com.arflix.tv.data.repository.MediaRepository;
import com.arflix.tv.data.repository.PlexPinAuthSession;
import com.arflix.tv.data.repository.ProfileManager;
import com.arflix.tv.data.repository.ProfileRepository;
import com.arflix.tv.data.repository.StreamRepository;
import com.arflix.tv.data.repository.SyncProgress;
import com.arflix.tv.data.repository.SyncResult;
import com.arflix.tv.data.repository.TraktRepository;
import com.arflix.tv.data.repository.TraktSyncService;
import com.arflix.tv.data.repository.TraktSyncSummary;
import com.arflix.tv.data.repository.TvDeviceAuthRepository;
import com.arflix.tv.data.repository.TvDeviceAuthSession;
import com.arflix.tv.data.repository.TvDeviceAuthStatusType;
import com.arflix.tv.data.repository.WatchHistoryRepository;
import com.arflix.tv.data.repository.WatchlistRepository;
import com.arflix.tv.data.repository.simkl.SimklAuthManager;
import com.arflix.tv.data.repository.sync.SyncProvider;
import com.arflix.tv.data.repository.sync.SyncProviderStore;
import com.arflix.tv.data.repository.sync.TrackingFeature;
import com.arflix.tv.data.repository.sync.TrackingReadMode;
import com.arflix.tv.network.OkHttpProvider;
import com.arflix.tv.server.AiKeyConfigServer;
import com.arflix.tv.ui.components.CardLayoutModeKt;
import com.arflix.tv.ui.screens.player.SubtitleAiModel;
import com.arflix.tv.updater.ApkDownloader;
import com.arflix.tv.updater.ApkInstaller;
import com.arflix.tv.updater.AppUpdate;
import com.arflix.tv.updater.AppUpdateRepository;
import com.arflix.tv.updater.UpdatePreferences;
import com.arflix.tv.updater.UpdateStatus;
import com.arflix.tv.updater.UpdateStatusManager;
import com.arflix.tv.util.AppLanguageKt;
import com.arflix.tv.util.AuthEmailValidator;
import com.arflix.tv.util.Constants;
import com.arflix.tv.util.DataStoresKt;
import com.arflix.tv.util.DeviceIpAddress;
import com.arflix.tv.util.DeviceTypeKt;
import com.arflix.tv.util.DiagnosticsManager;
import com.arflix.tv.util.QrCodeGenerator;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.gson.Gson;
import dagger.hilt.android.qualifiers.ApplicationContext;
import io.github.jan.supabase.gotrue.SettingsSessionManager;
import j$.time.Instant;
import j$.time.ZoneId;
import j$.time.format.DateTimeFormatter;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.inject.Inject;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000æ\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b9\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b7\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\bI\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001:\u0004È\u0003É\u0003BË\u0001\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001b\u001a\u00020\u001a\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\u0006\u0010\u001f\u001a\u00020\u001e\u0012\u0006\u0010!\u001a\u00020 \u0012\u0006\u0010#\u001a\u00020\"\u0012\u0006\u0010%\u001a\u00020$\u0012\u0006\u0010'\u001a\u00020&\u0012\u0006\u0010)\u001a\u00020(\u0012\u0006\u0010+\u001a\u00020*\u0012\u0006\u0010-\u001a\u00020,\u0012\u0006\u0010/\u001a\u00020.\u0012\u0006\u00101\u001a\u000200¢\u0006\u0004\b2\u00103J\u0015\u00107\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\b7\u00108J\r\u00109\u001a\u000206¢\u0006\u0004\b9\u0010:J\r\u0010;\u001a\u000206¢\u0006\u0004\b;\u0010:J\u0015\u0010>\u001a\u0002062\u0006\u0010=\u001a\u00020<¢\u0006\u0004\b>\u0010?J\u0017\u0010@\u001a\u0002062\b\u0010=\u001a\u0004\u0018\u00010<¢\u0006\u0004\b@\u0010?J\u001d\u0010B\u001a\u0002062\u0006\u0010=\u001a\u00020<2\u0006\u0010A\u001a\u00020<¢\u0006\u0004\bB\u0010CJ\u001d\u0010D\u001a\u0002062\u0006\u0010=\u001a\u00020<2\u0006\u0010A\u001a\u00020<¢\u0006\u0004\bD\u0010CJ\u001d\u0010E\u001a\u0002062\u0006\u0010=\u001a\u00020<2\u0006\u0010A\u001a\u00020<¢\u0006\u0004\bE\u0010CJ\u001d\u0010F\u001a\u0002062\u0006\u0010=\u001a\u00020<2\u0006\u0010A\u001a\u00020<¢\u0006\u0004\bF\u0010CJ\u0017\u0010H\u001a\u0002062\b\b\u0002\u0010G\u001a\u000204¢\u0006\u0004\bH\u00108J\r\u0010I\u001a\u000206¢\u0006\u0004\bI\u0010:J\u0015\u0010K\u001a\u0002062\u0006\u0010J\u001a\u00020<¢\u0006\u0004\bK\u0010?J\u0015\u0010L\u001a\u0002062\u0006\u0010J\u001a\u00020<¢\u0006\u0004\bL\u0010?J\u0015\u0010M\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\bM\u00108J\u0015\u0010N\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\bN\u00108J\u0015\u0010O\u001a\u0002062\u0006\u0010J\u001a\u00020<¢\u0006\u0004\bO\u0010?J\u0015\u0010P\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\bP\u00108J\r\u0010Q\u001a\u000206¢\u0006\u0004\bQ\u0010:J\r\u0010R\u001a\u000206¢\u0006\u0004\bR\u0010:J\u0015\u0010T\u001a\u0002062\u0006\u0010S\u001a\u00020<¢\u0006\u0004\bT\u0010?J\u0015\u0010V\u001a\u0002062\u0006\u0010U\u001a\u00020<¢\u0006\u0004\bV\u0010?J\u0015\u0010W\u001a\u0002062\u0006\u0010S\u001a\u00020<¢\u0006\u0004\bW\u0010?J\u0015\u0010Y\u001a\u0002062\u0006\u0010X\u001a\u000204¢\u0006\u0004\bY\u00108J\u0015\u0010Z\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\bZ\u00108J\r\u0010[\u001a\u000206¢\u0006\u0004\b[\u0010:J\u0015\u0010\\\u001a\u0002062\u0006\u0010S\u001a\u00020<¢\u0006\u0004\b\\\u0010?J\u0015\u0010]\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\b]\u00108J\u0015\u0010^\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\b^\u00108J\u0015\u0010_\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\b_\u00108J\u0015\u0010`\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\b`\u00108J\r\u0010a\u001a\u000206¢\u0006\u0004\ba\u0010:J\u0015\u0010b\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\bb\u00108J\u0015\u0010c\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\bc\u00108J\u0015\u0010d\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\bd\u00108J\u0015\u0010e\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\be\u00108J\r\u0010f\u001a\u000206¢\u0006\u0004\bf\u0010:J\r\u0010g\u001a\u000206¢\u0006\u0004\bg\u0010:J\r\u0010h\u001a\u000206¢\u0006\u0004\bh\u0010:J\r\u0010i\u001a\u000206¢\u0006\u0004\bi\u0010:J\r\u0010j\u001a\u000206¢\u0006\u0004\bj\u0010:J\r\u0010k\u001a\u000206¢\u0006\u0004\bk\u0010:J\r\u0010l\u001a\u000206¢\u0006\u0004\bl\u0010:J\r\u0010m\u001a\u000206¢\u0006\u0004\bm\u0010:J\u0015\u0010n\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\bn\u00108J\u0015\u0010o\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\bo\u00108J\u0015\u0010p\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\bp\u00108J\u0015\u0010q\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\bq\u00108J\u0015\u0010r\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\br\u00108J\u0015\u0010s\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0004\bs\u00108J\u0015\u0010u\u001a\u0002062\u0006\u0010t\u001a\u00020<¢\u0006\u0004\bu\u0010?J\u0015\u0010x\u001a\u0002062\u0006\u0010w\u001a\u00020v¢\u0006\u0004\bx\u0010yJ\r\u0010z\u001a\u000206¢\u0006\u0004\bz\u0010:J\r\u0010{\u001a\u000206¢\u0006\u0004\b{\u0010:J\u0015\u0010}\u001a\u0002062\u0006\u0010|\u001a\u00020<¢\u0006\u0004\b}\u0010?J\u0015\u0010\u007f\u001a\u0002062\u0006\u0010~\u001a\u00020<¢\u0006\u0004\b\u007f\u0010?J\u0017\u0010\u0080\u0001\u001a\u0002062\u0006\u00105\u001a\u000204¢\u0006\u0005\b\u0080\u0001\u00108J\"\u0010\u0083\u0001\u001a\u0002042\u0007\u0010\u0081\u0001\u001a\u00020<2\u0007\u0010\u0082\u0001\u001a\u00020<¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J+\u0010\u0086\u0001\u001a\u0002042\u0007\u0010\u0085\u0001\u001a\u00020<2\u0007\u0010\u0081\u0001\u001a\u00020<2\u0007\u0010\u0082\u0001\u001a\u00020<¢\u0006\u0006\b\u0086\u0001\u0010\u0087\u0001J\u000f\u0010\u0088\u0001\u001a\u000206¢\u0006\u0005\b\u0088\u0001\u0010:J\u0018\u0010\u0089\u0001\u001a\u0002062\u0007\u0010\u0085\u0001\u001a\u00020<¢\u0006\u0005\b\u0089\u0001\u0010?J\u0018\u0010\u008a\u0001\u001a\u0002062\u0007\u0010\u0085\u0001\u001a\u00020<¢\u0006\u0005\b\u008a\u0001\u0010?J\u0018\u0010\u008c\u0001\u001a\u0002062\u0007\u0010\u008b\u0001\u001a\u00020<¢\u0006\u0005\b\u008c\u0001\u0010?J\u0018\u0010\u008d\u0001\u001a\u0002062\u0007\u0010\u008b\u0001\u001a\u00020<¢\u0006\u0005\b\u008d\u0001\u0010?J\u0018\u0010\u008e\u0001\u001a\u0002062\u0007\u0010\u008b\u0001\u001a\u00020<¢\u0006\u0005\b\u008e\u0001\u0010?J\u0018\u0010\u0090\u0001\u001a\u0002062\u0007\u0010\u008f\u0001\u001a\u00020<¢\u0006\u0005\b\u0090\u0001\u0010?J\u000f\u0010\u0091\u0001\u001a\u000206¢\u0006\u0005\b\u0091\u0001\u0010:J\u0018\u0010\u0092\u0001\u001a\u0002062\u0007\u0010\u008f\u0001\u001a\u00020<¢\u0006\u0005\b\u0092\u0001\u0010?J\u000f\u0010\u0093\u0001\u001a\u000206¢\u0006\u0005\b\u0093\u0001\u0010:J\u0018\u0010\u0094\u0001\u001a\u0002062\u0007\u0010\u008f\u0001\u001a\u00020<¢\u0006\u0005\b\u0094\u0001\u0010?J\u0018\u0010\u0096\u0001\u001a\u0002062\u0007\u0010\u0095\u0001\u001a\u00020<¢\u0006\u0005\b\u0096\u0001\u0010?J\u0018\u0010\u0097\u0001\u001a\u0002062\u0007\u0010\u008f\u0001\u001a\u00020<¢\u0006\u0005\b\u0097\u0001\u0010?J\u0018\u0010\u0099\u0001\u001a\u0002062\u0007\u0010\u0098\u0001\u001a\u00020<¢\u0006\u0005\b\u0099\u0001\u0010?J\u001a\u0010\u009a\u0001\u001a\u0002062\t\b\u0002\u0010\u0098\u0001\u001a\u00020<¢\u0006\u0005\b\u009a\u0001\u0010?J\u000f\u0010\u009b\u0001\u001a\u000206¢\u0006\u0005\b\u009b\u0001\u0010:J\u001a\u0010\u009e\u0001\u001a\u0002062\b\u0010\u009d\u0001\u001a\u00030\u009c\u0001¢\u0006\u0006\b\u009e\u0001\u0010\u009f\u0001J!\u0010¡\u0001\u001a\u0002062\u0007\u0010 \u0001\u001a\u00020<2\u0007\u0010\u008f\u0001\u001a\u00020<¢\u0006\u0005\b¡\u0001\u0010CJ\u0018\u0010¢\u0001\u001a\u0002062\u0007\u0010 \u0001\u001a\u00020<¢\u0006\u0005\b¢\u0001\u0010?J\u0018\u0010£\u0001\u001a\u0002062\u0007\u0010 \u0001\u001a\u00020<¢\u0006\u0005\b£\u0001\u0010?J!\u0010¥\u0001\u001a\u0002062\u0007\u0010 \u0001\u001a\u00020<2\u0007\u0010¤\u0001\u001a\u00020<¢\u0006\u0005\b¥\u0001\u0010CJ\u0018\u0010¦\u0001\u001a\u0002062\u0007\u0010 \u0001\u001a\u00020<¢\u0006\u0005\b¦\u0001\u0010?J\u0018\u0010§\u0001\u001a\u0002062\u0007\u0010 \u0001\u001a\u00020<¢\u0006\u0005\b§\u0001\u0010?J!\u0010ª\u0001\u001a\u0002062\u0007\u0010¨\u0001\u001a\u00020<2\u0007\u0010©\u0001\u001a\u00020<¢\u0006\u0005\bª\u0001\u0010CJ!\u0010\u00ad\u0001\u001a\u0002062\u0007\u0010«\u0001\u001a\u00020<2\u0007\u0010¬\u0001\u001a\u00020<¢\u0006\u0005\b\u00ad\u0001\u0010CJ4\u0010±\u0001\u001a\u0002062\u0007\u0010®\u0001\u001a\u00020<2\u0007\u0010©\u0001\u001a\u00020<2\u0007\u0010¯\u0001\u001a\u00020<2\u0007\u0010°\u0001\u001a\u00020<¢\u0006\u0006\b±\u0001\u0010²\u0001J!\u0010¶\u0001\u001a\u0002062\u000f\u0010µ\u0001\u001a\n\u0012\u0005\u0012\u00030´\u00010³\u0001¢\u0006\u0006\b¶\u0001\u0010·\u0001J1\u0010»\u0001\u001a\u0002062\t\b\u0002\u0010¸\u0001\u001a\u0002042\t\b\u0002\u0010¹\u0001\u001a\u0002042\t\b\u0002\u0010º\u0001\u001a\u000204¢\u0006\u0006\b»\u0001\u0010¼\u0001J\u0017\u0010½\u0001\u001a\u0002062\u0006\u0010S\u001a\u00020<¢\u0006\u0005\b½\u0001\u0010?J\u000f\u0010¾\u0001\u001a\u000206¢\u0006\u0005\b¾\u0001\u0010:J\u0018\u0010¿\u0001\u001a\u0002062\u0007\u0010\u008b\u0001\u001a\u00020<¢\u0006\u0005\b¿\u0001\u0010?J\u0018\u0010À\u0001\u001a\u0002062\u0007\u0010\u008f\u0001\u001a\u00020<¢\u0006\u0005\bÀ\u0001\u0010?J\u000f\u0010Á\u0001\u001a\u000206¢\u0006\u0005\bÁ\u0001\u0010:J\u000f\u0010Â\u0001\u001a\u000206¢\u0006\u0005\bÂ\u0001\u0010:J\u000f\u0010Ã\u0001\u001a\u000206¢\u0006\u0005\bÃ\u0001\u0010:J\u000f\u0010Ä\u0001\u001a\u000206¢\u0006\u0005\bÄ\u0001\u0010:J+\u0010È\u0001\u001a\u0002062\u0007\u0010Å\u0001\u001a\u00020<2\u0007\u0010Æ\u0001\u001a\u00020<2\u0007\u0010Ç\u0001\u001a\u000204¢\u0006\u0006\bÈ\u0001\u0010É\u0001J6\u0010Í\u0001\u001a\u0002062\u0007\u0010Ê\u0001\u001a\u00020<2\u0007\u0010Ë\u0001\u001a\u00020<2\u0007\u0010Æ\u0001\u001a\u00020<2\t\b\u0002\u0010Ì\u0001\u001a\u00020<¢\u0006\u0006\bÍ\u0001\u0010²\u0001J#\u0010Î\u0001\u001a\u0002062\u0007\u0010Ê\u0001\u001a\u00020<2\t\b\u0002\u0010Ì\u0001\u001a\u00020<¢\u0006\u0005\bÎ\u0001\u0010CJ\u001a\u0010Ð\u0001\u001a\u0002062\t\b\u0002\u0010Ï\u0001\u001a\u000204¢\u0006\u0005\bÐ\u0001\u00108J\u000f\u0010Ñ\u0001\u001a\u000206¢\u0006\u0005\bÑ\u0001\u0010:J\u000f\u0010Ò\u0001\u001a\u000206¢\u0006\u0005\bÒ\u0001\u0010:J%\u0010Ó\u0001\u001a\u0002062\b\b\u0002\u0010G\u001a\u0002042\t\b\u0002\u0010º\u0001\u001a\u000204¢\u0006\u0006\bÓ\u0001\u0010Ô\u0001J\u0019\u0010Õ\u0001\u001a\u0002062\b\b\u0002\u0010G\u001a\u000204¢\u0006\u0005\bÕ\u0001\u00108J\u000f\u0010Ö\u0001\u001a\u000206¢\u0006\u0005\bÖ\u0001\u0010:J\u000f\u0010×\u0001\u001a\u000206¢\u0006\u0005\b×\u0001\u0010:J\u000f\u0010Ø\u0001\u001a\u000206¢\u0006\u0005\bØ\u0001\u0010:J\u000f\u0010Ù\u0001\u001a\u000206¢\u0006\u0005\bÙ\u0001\u0010:J\"\u0010Û\u0001\u001a\u0002062\u0007\u0010º\u0001\u001a\u0002042\u0007\u0010Ú\u0001\u001a\u000204¢\u0006\u0006\bÛ\u0001\u0010Ô\u0001J\u000f\u0010Ü\u0001\u001a\u000206¢\u0006\u0005\bÜ\u0001\u0010:J\u000f\u0010Ý\u0001\u001a\u000206¢\u0006\u0005\bÝ\u0001\u0010:J\u000f\u0010Þ\u0001\u001a\u000206¢\u0006\u0005\bÞ\u0001\u0010:J\u000f\u0010ß\u0001\u001a\u000206¢\u0006\u0005\bß\u0001\u0010:J\u000f\u0010à\u0001\u001a\u000206¢\u0006\u0005\bà\u0001\u0010:J\u000f\u0010á\u0001\u001a\u000206¢\u0006\u0005\bá\u0001\u0010:J\u000f\u0010â\u0001\u001a\u000206¢\u0006\u0005\bâ\u0001\u0010:J\u000f\u0010ã\u0001\u001a\u000206¢\u0006\u0005\bã\u0001\u0010:J\u000f\u0010ä\u0001\u001a\u000206¢\u0006\u0005\bä\u0001\u0010:J\u000f\u0010å\u0001\u001a\u000206¢\u0006\u0005\bå\u0001\u0010:J\u0018\u0010ç\u0001\u001a\u0002062\u0007\u0010æ\u0001\u001a\u00020<¢\u0006\u0005\bç\u0001\u0010?J\u000f\u0010è\u0001\u001a\u000206¢\u0006\u0005\bè\u0001\u0010:J\u000f\u0010é\u0001\u001a\u000206¢\u0006\u0005\bé\u0001\u0010:J\u000f\u0010ê\u0001\u001a\u000206¢\u0006\u0005\bê\u0001\u0010:J\u000f\u0010ë\u0001\u001a\u000206¢\u0006\u0005\bë\u0001\u0010:J#\u0010ï\u0001\u001a\u0002062\b\u0010í\u0001\u001a\u00030ì\u00012\u0007\u0010S\u001a\u00030î\u0001¢\u0006\u0006\bï\u0001\u0010ð\u0001J\"\u0010ó\u0001\u001a\u0002062\b\u0010ò\u0001\u001a\u00030ñ\u00012\u0006\u00105\u001a\u000204¢\u0006\u0006\bó\u0001\u0010ô\u0001J\u000f\u0010õ\u0001\u001a\u000206¢\u0006\u0005\bõ\u0001\u0010:J\u000f\u0010ö\u0001\u001a\u000206¢\u0006\u0005\bö\u0001\u0010:J\u0011\u0010÷\u0001\u001a\u000206H\u0014¢\u0006\u0005\b÷\u0001\u0010:J+\u0010ú\u0001\u001a\n\u0012\u0005\u0012\u00030ø\u00010³\u00012\u000f\u0010ù\u0001\u001a\n\u0012\u0005\u0012\u00030ø\u00010³\u0001H\u0002¢\u0006\u0006\bú\u0001\u0010û\u0001J\u0019\u0010ý\u0001\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\bý\u0001\u0010þ\u0001J\u0019\u0010ÿ\u0001\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\bÿ\u0001\u0010þ\u0001J\"\u0010\u0081\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u00012\u0007\u0010\u0080\u0002\u001a\u00020<H\u0002¢\u0006\u0006\b\u0081\u0002\u0010\u0082\u0002J\u0019\u0010\u0083\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\b\u0083\u0002\u0010þ\u0001J\u0019\u0010\u0084\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\b\u0084\u0002\u0010þ\u0001J\"\u0010\u0085\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u00012\u0007\u0010\u0080\u0002\u001a\u00020<H\u0002¢\u0006\u0006\b\u0085\u0002\u0010\u0082\u0002J\u0019\u0010\u0086\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\b\u0086\u0002\u0010þ\u0001J\u0019\u0010\u0087\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\b\u0087\u0002\u0010þ\u0001J\"\u0010\u0088\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u00012\u0007\u0010\u0080\u0002\u001a\u00020<H\u0002¢\u0006\u0006\b\u0088\u0002\u0010\u0082\u0002J\u0019\u0010\u0089\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\b\u0089\u0002\u0010þ\u0001J\"\u0010\u008a\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u00012\u0007\u0010\u0080\u0002\u001a\u00020<H\u0002¢\u0006\u0006\b\u008a\u0002\u0010\u0082\u0002J\u0019\u0010\u008b\u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u0001H\u0002¢\u0006\u0006\b\u008b\u0002\u0010þ\u0001J\"\u0010\u008c\u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u00012\u0007\u0010\u0080\u0002\u001a\u00020<H\u0002¢\u0006\u0006\b\u008c\u0002\u0010\u0082\u0002J\u0019\u0010\u008d\u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u0001H\u0002¢\u0006\u0006\b\u008d\u0002\u0010þ\u0001J\"\u0010\u008e\u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u00012\u0007\u0010\u0080\u0002\u001a\u00020<H\u0002¢\u0006\u0006\b\u008e\u0002\u0010\u0082\u0002J\u0019\u0010\u008f\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\b\u008f\u0002\u0010þ\u0001J\"\u0010\u0090\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u00012\u0007\u0010\u0080\u0002\u001a\u00020<H\u0002¢\u0006\u0006\b\u0090\u0002\u0010\u0082\u0002J\u0019\u0010\u0091\u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u0001H\u0002¢\u0006\u0006\b\u0091\u0002\u0010þ\u0001J\u0019\u0010\u0092\u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u0001H\u0002¢\u0006\u0006\b\u0092\u0002\u0010þ\u0001J\u0019\u0010\u0093\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\b\u0093\u0002\u0010þ\u0001J\u0019\u0010\u0094\u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u0001H\u0002¢\u0006\u0006\b\u0094\u0002\u0010þ\u0001J\u0019\u0010\u0095\u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u0001H\u0002¢\u0006\u0006\b\u0095\u0002\u0010þ\u0001J\u0019\u0010\u0096\u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u0001H\u0002¢\u0006\u0006\b\u0096\u0002\u0010þ\u0001J\u0019\u0010\u0097\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\b\u0097\u0002\u0010þ\u0001J\u0019\u0010\u0098\u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u0001H\u0002¢\u0006\u0006\b\u0098\u0002\u0010þ\u0001J\u0019\u0010\u0099\u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u0001H\u0002¢\u0006\u0006\b\u0099\u0002\u0010þ\u0001J\u0019\u0010\u009a\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\b\u009a\u0002\u0010þ\u0001J\u0019\u0010\u009b\u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u0001H\u0002¢\u0006\u0006\b\u009b\u0002\u0010þ\u0001J\u0019\u0010\u009c\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\b\u009c\u0002\u0010þ\u0001J\u0019\u0010\u009d\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\b\u009d\u0002\u0010þ\u0001J\u0019\u0010\u009e\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\b\u009e\u0002\u0010þ\u0001J\u0019\u0010\u009f\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\b\u009f\u0002\u0010þ\u0001J\u0019\u0010 \u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u0001H\u0002¢\u0006\u0006\b \u0002\u0010þ\u0001J\u0019\u0010¡\u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u0001H\u0002¢\u0006\u0006\b¡\u0002\u0010þ\u0001J\u0019\u0010¢\u0002\u001a\t\u0012\u0004\u0012\u00020<0ü\u0001H\u0002¢\u0006\u0006\b¢\u0002\u0010þ\u0001J\u0019\u0010£\u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u0001H\u0002¢\u0006\u0006\b£\u0002\u0010þ\u0001J\"\u0010¤\u0002\u001a\t\u0012\u0004\u0012\u0002040ü\u00012\u0007\u0010\u0080\u0002\u001a\u00020<H\u0002¢\u0006\u0006\b¤\u0002\u0010\u0082\u0002J\u0011\u0010¥\u0002\u001a\u000206H\u0002¢\u0006\u0005\b¥\u0002\u0010:J\u0011\u0010¦\u0002\u001a\u000206H\u0002¢\u0006\u0005\b¦\u0002\u0010:J\u0011\u0010§\u0002\u001a\u000206H\u0002¢\u0006\u0005\b§\u0002\u0010:J8\u0010«\u0002\u001a\u0002062\u0007\u0010\u0080\u0002\u001a\u00020<2\u0007\u0010¨\u0002\u001a\u0002042\u0007\u0010©\u0002\u001a\u0002042\t\b\u0002\u0010ª\u0002\u001a\u000204H\u0002¢\u0006\u0006\b«\u0002\u0010¬\u0002J\u001a\u0010\u00ad\u0002\u001a\u0002062\u0007\u0010\u0080\u0002\u001a\u00020<H\u0002¢\u0006\u0005\b\u00ad\u0002\u0010?J\u0011\u0010®\u0002\u001a\u000206H\u0002¢\u0006\u0005\b®\u0002\u0010:J\u0011\u0010¯\u0002\u001a\u000206H\u0002¢\u0006\u0005\b¯\u0002\u0010:J\u0011\u0010°\u0002\u001a\u000206H\u0002¢\u0006\u0005\b°\u0002\u0010:J\u0011\u0010±\u0002\u001a\u000206H\u0002¢\u0006\u0005\b±\u0002\u0010:J\u0011\u0010²\u0002\u001a\u000206H\u0002¢\u0006\u0005\b²\u0002\u0010:J\u001f\u0010´\u0002\u001a\u0004\u0018\u00010<2\t\u0010³\u0002\u001a\u0004\u0018\u00010<H\u0002¢\u0006\u0006\b´\u0002\u0010µ\u0002J\"\u0010¶\u0002\u001a\t\u0012\u0004\u0012\u00020<0³\u00012\u0006\u0010=\u001a\u00020<H\u0082@¢\u0006\u0006\b¶\u0002\u0010·\u0002J#\u0010¹\u0002\u001a\t\u0012\u0004\u0012\u00020<0³\u00012\u0007\u0010¸\u0002\u001a\u00020<H\u0082@¢\u0006\u0006\b¹\u0002\u0010·\u0002J\"\u0010º\u0002\u001a\t\u0012\u0004\u0012\u00020<0³\u00012\u0007\u0010¸\u0002\u001a\u00020<H\u0002¢\u0006\u0006\bº\u0002\u0010»\u0002J\u001b\u0010½\u0002\u001a\u00020<2\u0007\u0010¼\u0002\u001a\u00020<H\u0002¢\u0006\u0006\b½\u0002\u0010µ\u0002J\u0019\u0010¾\u0002\u001a\u0002062\u0006\u0010~\u001a\u00020<H\u0002¢\u0006\u0005\b¾\u0002\u0010?J\u001d\u0010À\u0002\u001a\u00020<2\t\u0010¿\u0002\u001a\u0004\u0018\u00010<H\u0002¢\u0006\u0006\bÀ\u0002\u0010µ\u0002J\u001d\u0010Á\u0002\u001a\u00020<2\t\u0010¿\u0002\u001a\u0004\u0018\u00010<H\u0002¢\u0006\u0006\bÁ\u0002\u0010µ\u0002J\u0011\u0010Â\u0002\u001a\u000206H\u0002¢\u0006\u0005\bÂ\u0002\u0010:J\u001d\u0010Ã\u0002\u001a\u00020<2\t\u0010¿\u0002\u001a\u0004\u0018\u00010<H\u0002¢\u0006\u0006\bÃ\u0002\u0010µ\u0002J\u001a\u0010Ä\u0002\u001a\u00020<2\u0006\u0010~\u001a\u00020<H\u0002¢\u0006\u0006\bÄ\u0002\u0010µ\u0002J\u001a\u0010Å\u0002\u001a\u00020<2\u0006\u0010|\u001a\u00020<H\u0002¢\u0006\u0006\bÅ\u0002\u0010µ\u0002J$\u0010È\u0002\u001a\u0002062\u000f\u0010Ç\u0002\u001a\n\u0012\u0005\u0012\u00030Æ\u00020³\u0001H\u0082@¢\u0006\u0006\bÈ\u0002\u0010É\u0002J$\u0010Ë\u0002\u001a\u00030Ê\u00022\u000f\u0010Ç\u0002\u001a\n\u0012\u0005\u0012\u00030Æ\u00020³\u0001H\u0002¢\u0006\u0006\bË\u0002\u0010Ì\u0002J$\u0010Î\u0002\u001a\u0002062\u0007\u0010\u008b\u0001\u001a\u00020<2\u0007\u0010Í\u0002\u001a\u000204H\u0002¢\u0006\u0006\bÎ\u0002\u0010Ï\u0002J\u0011\u0010Ð\u0002\u001a\u000206H\u0002¢\u0006\u0005\bÐ\u0002\u0010:J\u0011\u0010Ñ\u0002\u001a\u000206H\u0002¢\u0006\u0005\bÑ\u0002\u0010:J\u0011\u0010Ò\u0002\u001a\u000206H\u0002¢\u0006\u0005\bÒ\u0002\u0010:J\u0011\u0010Ó\u0002\u001a\u000206H\u0002¢\u0006\u0005\bÓ\u0002\u0010:J\u0011\u0010Ô\u0002\u001a\u000206H\u0002¢\u0006\u0005\bÔ\u0002\u0010:J\u0012\u0010Õ\u0002\u001a\u000204H\u0002¢\u0006\u0006\bÕ\u0002\u0010Ö\u0002J\u001c\u0010Ù\u0002\u001a\u0002062\b\u0010Ø\u0002\u001a\u00030×\u0002H\u0002¢\u0006\u0006\bÙ\u0002\u0010Ú\u0002J\u001c\u0010Ü\u0002\u001a\u0002062\t\b\u0002\u0010Û\u0002\u001a\u000204H\u0002¢\u0006\u0005\bÜ\u0002\u00108J#\u0010á\u0002\u001a\t\u0012\u0004\u0012\u0002060Þ\u00022\u0007\u0010Ý\u0002\u001a\u000204H\u0082@¢\u0006\u0006\bß\u0002\u0010à\u0002J%\u0010ã\u0002\u001a\u0002062\u0007\u0010Ê\u0001\u001a\u00020<2\b\u0010Ø\u0002\u001a\u00030â\u0002H\u0002¢\u0006\u0006\bã\u0002\u0010ä\u0002J\u0013\u0010å\u0002\u001a\u000206H\u0082@¢\u0006\u0006\bå\u0002\u0010æ\u0002J\u0013\u0010ç\u0002\u001a\u000204H\u0082@¢\u0006\u0006\bç\u0002\u0010æ\u0002J'\u0010ê\u0002\u001a\u00030é\u00022\u0006\u0010G\u001a\u0002042\t\b\u0002\u0010è\u0002\u001a\u000204H\u0082@¢\u0006\u0006\bê\u0002\u0010ë\u0002J\u001c\u0010î\u0002\u001a\u0002062\b\u0010í\u0002\u001a\u00030ì\u0002H\u0002¢\u0006\u0006\bî\u0002\u0010ï\u0002J/\u0010ô\u0002\u001a\u0002062\u0007\u0010ð\u0002\u001a\u00020<2\b\u0010ò\u0002\u001a\u00030ñ\u00022\b\u0010ó\u0002\u001a\u00030ñ\u0002H\u0002¢\u0006\u0006\bô\u0002\u0010õ\u0002R\u0015\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0003\u0010ö\u0002R\u0015\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0005\u0010÷\u0002R\u0015\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0007\u0010ø\u0002R\u0015\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\t\u0010ù\u0002R\u0015\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u000b\u0010ú\u0002R\u0015\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\r\u0010û\u0002R\u0015\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u000f\u0010ü\u0002R\u0015\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0011\u0010ý\u0002R\u0015\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0013\u0010þ\u0002R\u0015\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0015\u0010ÿ\u0002R\u0015\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0017\u0010\u0080\u0003R\u0015\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0019\u0010\u0081\u0003R\u0015\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u001b\u0010\u0082\u0003R\u0015\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u001d\u0010\u0083\u0003R\u0015\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u001f\u0010\u0084\u0003R\u0015\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b!\u0010\u0085\u0003R\u0015\u0010#\u001a\u00020\"8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b#\u0010\u0086\u0003R\u0015\u0010%\u001a\u00020$8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b%\u0010\u0087\u0003R\u0015\u0010'\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b'\u0010\u0088\u0003R\u0015\u0010)\u001a\u00020(8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b)\u0010\u0089\u0003R\u0015\u0010+\u001a\u00020*8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b+\u0010\u008a\u0003R\u0015\u0010-\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b-\u0010\u008b\u0003R\u0015\u0010/\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b/\u0010\u008c\u0003R\u0015\u00101\u001a\u0002008\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b1\u0010\u008d\u0003R\u001f\u0010\u0090\u0003\u001a\n\u0012\u0005\u0012\u00030\u008f\u00030\u008e\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0090\u0003\u0010\u0091\u0003R$\u0010\u0093\u0003\u001a\n\u0012\u0005\u0012\u00030\u008f\u00030\u0092\u00038\u0006¢\u0006\u0010\n\u0006\b\u0093\u0003\u0010\u0094\u0003\u001a\u0006\b\u0095\u0003\u0010\u0096\u0003R\u001e\u0010\u0097\u0003\u001a\t\u0012\u0004\u0012\u00020<0ü\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0097\u0003\u0010\u0098\u0003R\u001e\u0010\u0099\u0003\u001a\t\u0012\u0004\u0012\u00020<0ü\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0099\u0003\u0010\u0098\u0003R\u001e\u0010\u009a\u0003\u001a\t\u0012\u0004\u0012\u00020<0ü\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009a\u0003\u0010\u0098\u0003R\u001e\u0010\u009b\u0003\u001a\t\u0012\u0004\u0012\u0002040ü\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009b\u0003\u0010\u0098\u0003R\u001e\u0010\u009c\u0003\u001a\t\u0012\u0004\u0012\u0002040ü\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009c\u0003\u0010\u0098\u0003R\u001e\u0010\u009d\u0003\u001a\t\u0012\u0004\u0012\u0002040ü\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009d\u0003\u0010\u0098\u0003R\u001e\u0010\u009e\u0003\u001a\t\u0012\u0004\u0012\u0002040ü\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009e\u0003\u0010\u0098\u0003R\u001e\u0010\u009f\u0003\u001a\t\u0012\u0004\u0012\u0002040ü\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009f\u0003\u0010\u0098\u0003R\u001e\u0010 \u0003\u001a\t\u0012\u0004\u0012\u00020<0ü\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b \u0003\u0010\u0098\u0003R\u001e\u0010¡\u0003\u001a\t\u0012\u0004\u0012\u00020<0ü\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¡\u0003\u0010\u0098\u0003R\u001e\u0010¢\u0003\u001a\t\u0012\u0004\u0012\u0002040ü\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¢\u0003\u0010\u0098\u0003R\u0018\u0010¤\u0003\u001a\u00030£\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¤\u0003\u0010¥\u0003R\u0019\u0010¦\u0003\u001a\u00020<8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¦\u0003\u0010§\u0003R\u0019\u0010¨\u0003\u001a\u00020<8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¨\u0003\u0010§\u0003R\u001c\u0010ª\u0003\u001a\u0005\u0018\u00010©\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bª\u0003\u0010«\u0003R\u001c\u0010¬\u0003\u001a\u0005\u0018\u00010©\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¬\u0003\u0010«\u0003R\u001c\u0010\u00ad\u0003\u001a\u0005\u0018\u00010©\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u00ad\u0003\u0010«\u0003R\u001c\u0010®\u0003\u001a\u0005\u0018\u00010©\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b®\u0003\u0010«\u0003R\u001c\u0010¯\u0003\u001a\u0005\u0018\u00010©\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¯\u0003\u0010«\u0003R\u001c\u0010°\u0003\u001a\u0005\u0018\u00010©\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b°\u0003\u0010«\u0003R\u001c\u0010±\u0003\u001a\u0005\u0018\u00010©\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b±\u0003\u0010«\u0003R\u001b\u0010²\u0003\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b²\u0003\u0010§\u0003R\u001b\u0010³\u0003\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b³\u0003\u0010§\u0003R\u001c\u0010´\u0003\u001a\u0005\u0018\u00010©\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b´\u0003\u0010«\u0003R\u001c\u0010µ\u0003\u001a\u0005\u0018\u00010©\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bµ\u0003\u0010«\u0003R\u001c\u0010·\u0003\u001a\u0005\u0018\u00010¶\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b·\u0003\u0010¸\u0003R\u001b\u0010¹\u0003\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¹\u0003\u0010§\u0003R\u001b\u0010º\u0003\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bº\u0003\u0010§\u0003R\u001b\u0010»\u0003\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b»\u0003\u0010§\u0003R\u001b\u0010¼\u0003\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¼\u0003\u0010§\u0003R\u001a\u0010¾\u0003\u001a\u00030½\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¾\u0003\u0010¿\u0003R\u001a\u0010À\u0003\u001a\u00030½\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÀ\u0003\u0010¿\u0003R\u001c\u0010Á\u0003\u001a\u0005\u0018\u00010©\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÁ\u0003\u0010«\u0003R\u0019\u0010Â\u0003\u001a\u0002048\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÂ\u0003\u0010Ã\u0003R\u001b\u0010Ä\u0003\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÄ\u0003\u0010§\u0003R\u0019\u0010Å\u0003\u001a\u0002048\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÅ\u0003\u0010Ã\u0003R\u001b\u0010Æ\u0003\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÆ\u0003\u0010§\u0003R\u001c\u0010Ç\u0003\u001a\u0005\u0018\u00010©\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÇ\u0003\u0010«\u0003¨\u0006Ê\u0003"}, d2 = {"Lcom/arflix/tv/ui/screens/settings/SettingsViewModel;", "Landroidx/lifecycle/d1;", "Landroid/content/Context;", "context", "Lcom/arflix/tv/data/repository/ProfileManager;", "profileManager", "Lcom/arflix/tv/data/repository/TraktRepository;", "traktRepository", "Lcom/arflix/tv/data/repository/StreamRepository;", "streamRepository", "Lcom/arflix/tv/data/repository/MediaRepository;", "mediaRepository", "Lcom/arflix/tv/data/repository/CatalogRepository;", "catalogRepository", "Lcom/arflix/tv/data/repository/CatalogDiscoveryRepository;", "catalogDiscoveryRepository", "Lcom/arflix/tv/data/repository/IptvRepository;", "iptvRepository", "Lcom/arflix/tv/data/repository/HomeServerRepository;", "homeServerRepository", "Lcom/arflix/tv/data/repository/WatchlistRepository;", "watchlistRepository", "Lcom/arflix/tv/data/repository/AuthRepository;", "authRepository", "Lcom/arflix/tv/data/repository/ProfileRepository;", "profileRepository", "Lcom/arflix/tv/data/repository/TvDeviceAuthRepository;", "tvDeviceAuthRepository", "Lcom/arflix/tv/data/repository/TraktSyncService;", "traktSyncService", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "cloudSyncRepository", "Lcom/arflix/tv/data/repository/LauncherContinueWatchingRepository;", "launcherContinueWatchingRepository", "Lcom/arflix/tv/updater/AppUpdateRepository;", "appUpdateRepository", "Lcom/arflix/tv/updater/UpdatePreferences;", "updatePreferences", "Lcom/arflix/tv/updater/ApkDownloader;", "apkDownloader", "Lcom/arflix/tv/updater/UpdateStatusManager;", "updateStatusManager", "Lcom/arflix/tv/data/repository/MdbListRepository;", "mdbListRepository", "Lcom/arflix/tv/data/repository/sync/SyncProviderStore;", "syncProviderStore", "Lcom/arflix/tv/data/repository/WatchHistoryRepository;", "watchHistoryRepository", "Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;", "simklAuthManager", "<init>", "(Landroid/content/Context;Lcom/arflix/tv/data/repository/ProfileManager;Lcom/arflix/tv/data/repository/TraktRepository;Lcom/arflix/tv/data/repository/StreamRepository;Lcom/arflix/tv/data/repository/MediaRepository;Lcom/arflix/tv/data/repository/CatalogRepository;Lcom/arflix/tv/data/repository/CatalogDiscoveryRepository;Lcom/arflix/tv/data/repository/IptvRepository;Lcom/arflix/tv/data/repository/HomeServerRepository;Lcom/arflix/tv/data/repository/WatchlistRepository;Lcom/arflix/tv/data/repository/AuthRepository;Lcom/arflix/tv/data/repository/ProfileRepository;Lcom/arflix/tv/data/repository/TvDeviceAuthRepository;Lcom/arflix/tv/data/repository/TraktSyncService;Lcom/arflix/tv/data/repository/CloudSyncRepository;Lcom/arflix/tv/data/repository/LauncherContinueWatchingRepository;Lcom/arflix/tv/updater/AppUpdateRepository;Lcom/arflix/tv/updater/UpdatePreferences;Lcom/arflix/tv/updater/ApkDownloader;Lcom/arflix/tv/updater/UpdateStatusManager;Lcom/arflix/tv/data/repository/MdbListRepository;Lcom/arflix/tv/data/repository/sync/SyncProviderStore;Lcom/arflix/tv/data/repository/WatchHistoryRepository;Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;)V", "", "enabled", "Lx6/t0;", "setDiagnosticsSharingEnabled", "(Z)V", "refreshSubtitleOptions", "()V", "refreshAudioLanguageOptions", "", "playlistId", "resetIptvGroupOrder", "(Ljava/lang/String;)V", "setIptvSelectedPlaylistId", "groupName", "toggleIptvHiddenGroup", "(Ljava/lang/String;Ljava/lang/String;)V", "moveIptvGroupUp", "moveIptvGroupDown", "moveIptvGroupToTop", "silent", "performFullSync", "performIncrementalSync", "language", "setDefaultSubtitle", "setDefaultAudioLanguage", "setAutoPlayNext", "setAutoPlaySingleSource", "setSecondarySubtitle", "setFilterSubtitlesByLanguage", "cycleAutoPlayMinQuality", "toggleCardLayoutMode", "mode", "setCardLayoutMode", "lang", "setContentLanguage", "setDeviceModeOverride", "skip", "setSkipProfileSelection", "setOledBlackBackground", "cycleFrameRateMatchingMode", "setFrameRateMatchingMode", "setSpoilerBlurEnabled", "setTrailerAutoPlay", "setTrailerSoundEnabled", "setTrailerInCards", "cycleTrailerDelay", "setShowBudget", "setShowEpisodeRatings", "setSmoothScrolling", "setShowLoadingStats", "cycleClockFormat", "cycleAccentColor", "cycleVolumeBoost", "cycleSubtitleSize", "cycleSubtitleColor", "cycleSubtitleOffset", "cycleSubtitleStyle", "toggleSubtitleStylized", "setSubtitleAiEnabled", "setSubtitleAiAutoSelect", "setSubtitleAiFindBestMatch", "setSubtitlePreloadEnabled", "setDolbyVisionCompatEnabled", "setSubtitleRemoveHearingImpaired", "key", "saveSubtitleAiApiKey", "Lcom/arflix/tv/ui/screens/player/SubtitleAiModel;", "model", "setSubtitleAiModel", "(Lcom/arflix/tv/ui/screens/player/SubtitleAiModel;)V", "startAiKeyServer", "stopAiKeyServer", "label", "setDnsProvider", "value", "setCustomUserAgent", "setIncludeSpecials", "deviceName", "regexPattern", "addQualityFilter", "(Ljava/lang/String;Ljava/lang/String;)Z", "filterId", "updateQualityFilter", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Z", "cycleQualityFilterPreset", "toggleQualityFilter", "deleteQualityFilter", "addonId", "toggleAddon", "moveAddonUp", "moveAddonDown", "url", "addCustomAddon", "refreshAddons", "loadPackManifest", "clearPendingPack", "confirmInstallPack", "packId", "removeCatalogPack", "addCatalog", "query", "setCatalogSearchQuery", "searchCatalogLists", "clearCatalogDiscovery", "Lcom/arflix/tv/data/model/CatalogDiscoveryResult;", "result", "addDiscoveredCatalog", "(Lcom/arflix/tv/data/model/CatalogDiscoveryResult;)V", "catalogId", "updateCatalog", "removeCatalog", "unpackCatalog", "newTitle", "renameCatalog", "moveCatalogUp", "moveCatalogDown", "m3uUrl", "epgUrl", "saveIptvConfig", "portalUrl", "macAddress", "saveStalkerConfig", "sourceOrHost", "xtreamUsername", "xtreamPassword", "saveIptvConfigWithXtream", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "", "Lcom/arflix/tv/data/repository/IptvPlaylistEntry;", "playlists", "saveIptvPlaylists", "(Ljava/util/List;)V", "showToast", "configured", "force", "refreshIptv", "(ZZZ)V", "setIptvSortOrder", "clearIptvConfig", "removeAddon", "setTorrServerBaseUrl", "startCloudAuth", "cancelCloudAuth", "openCloudEmailPasswordDialog", "closeCloudEmailPasswordDialog", "email", "password", "createAccount", "completeCloudAuthWithEmailPassword", "(Ljava/lang/String;Ljava/lang/String;Z)V", "serverUrl", "username", "displayName", "connectHomeServer", "startPlexHomeServerAuth", "updateState", "cancelPlexHomeServerAuth", "testHomeServerConnection", "disconnectHomeServer", "syncLocalStateToCloud", "(ZZ)V", "syncCloudStateToLocal", "forceCloudSyncNow", "forceCloudPushOnly", "forceCloudPullOnly", "onCloudProfileSwitchHandled", "showNoUpdateFeedback", "checkForAppUpdates", "dismissAppUpdateDialog", "ignoreAppUpdate", "downloadAppUpdate", "cancelDownloadAppUpdate", "installAppUpdateOrRequestPermission", "openUnknownSourcesSettings", "startTraktAuth", "reconnectTrakt", "cancelTraktAuth", "disconnectTrakt", "apiKey", "connectMdbList", "disconnectMdbList", "startSimklAuth", "pollSimklAuth", "disconnectSimkl", "Lcom/arflix/tv/data/repository/sync/TrackingFeature;", "feature", "Lcom/arflix/tv/data/repository/sync/TrackingReadMode;", "setTrackingReadMode", "(Lcom/arflix/tv/data/repository/sync/TrackingFeature;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;)V", "Lcom/arflix/tv/data/repository/sync/SyncProvider;", "provider", "setTrackingWriteTarget", "(Lcom/arflix/tv/data/repository/sync/SyncProvider;Z)V", "dismissToast", "logout", "onCleared", "Lcom/arflix/tv/data/model/CatalogConfig;", "catalogs", "visibleCatalogs", "(Ljava/util/List;)Ljava/util/List;", "Lc1/e;", "contentLanguageKey", "()Lc1/e;", "defaultSubtitleKey", "profileId", "defaultSubtitleKeyFor", "(Ljava/lang/String;)Lc1/e;", "subtitleSettingsUpdatedAtKey", "defaultAudioLanguageKey", "defaultAudioLanguageKeyFor", "subtitleUsageKey", "cardLayoutModeKey", "cardLayoutModeKeyFor", "frameRateMatchingModeKey", "frameRateMatchingModeKeyFor", "autoPlayNextKey", "autoPlayNextKeyFor", "autoPlaySingleSourceKey", "autoPlaySingleSourceKeyFor", "autoPlayMinQualityKey", "autoPlayMinQualityKeyFor", "trailerAutoPlayKey", "trailerSoundEnabledKey", "trailerDelayKey", "trailerInCardsKey", "showBudgetKey", "showEpisodeRatingsKey", "clockFormatKey", "smoothScrollingKey", "spoilerBlurKey", "volumeBoostDbKey", "showLoadingStatsKey", "subtitleSizeKey", "subtitleColorKey", "subtitleOffsetKey", "subtitleStyleKey", "subtitleStylizedKey", "filterSubtitlesByLanguageKey", "secondarySubtitleKey", "includeSpecialsKey", "includeSpecialsKeyFor", "observeIptvGroupPrefs", "initializeUpdaterState", "loadSettings", "isTraktConnected", "isMdbListConnected", "isSimklConnected", "refreshIntegrationUsernames", "(Ljava/lang/String;ZZZ)V", "refreshSyncSummary", "observeProfileChanges", "observeAddons", "observeTorrServer", "observeHomeServer", "observeSyncState", "isoTime", "formatSyncTime", "(Ljava/lang/String;)Ljava/lang/String;", "loadIptvGroupsForPlaylist", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "current", "loadSubtitleOptions", "loadAudioLanguageOptions", "(Ljava/lang/String;)Ljava/util/List;", "code", "displayLanguage", "setAutoPlayMinQuality", "raw", "normalizeFrameRateMode", "normalizeAutoPlayMinQuality", "stopAiKeyServerInternal", "normalizeDnsProviderValue", "dnsProviderLabel", "dnsProviderValueFromLabel", "Lcom/arflix/tv/data/model/QualityFilterConfig;", "filters", "saveQualityFilters", "(Ljava/util/List;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/ui/screens/settings/SettingsViewModel$QualityFilterPreset;", "detectQualityFilterPreset", "(Ljava/util/List;)Lcom/arflix/tv/ui/screens/settings/SettingsViewModel$QualityFilterPreset;", "moveUp", "moveAddon", "(Ljava/lang/String;Z)V", "observeAuthState", "observeIptvConfig", "observeCatalogs", "initializeCatalogs", "startCloudPolling", "hasActiveCloudAuthSession", "()Z", "Lcom/arflix/tv/data/repository/TvDeviceAuthSession;", SettingsSessionManager.SETTINGS_KEY, "applyCloudAuthSession", "(Lcom/arflix/tv/data/repository/TvDeviceAuthSession;)V", "cancelPolling", "clearCloudAuthSession", "startPolling", "Lx6/d0;", "ensureCloudAuthSession-gIAlu-s", "(ZLd7/d;)Ljava/lang/Object;", "ensureCloudAuthSession", "Lcom/arflix/tv/data/repository/PlexPinAuthSession;", "startPlexHomeServerPolling", "(Ljava/lang/String;Lcom/arflix/tv/data/repository/PlexPinAuthSession;)V", "syncHomeServerCatalogsFromConnections", "(Ld7/d;)Ljava/lang/Object;", "ensureCloudSyncSession", "pushPendingLocalFirst", "Lcom/arflix/tv/ui/screens/settings/SettingsViewModel$CloudRestoreResult;", "restoreCloudStateToLocalInternal", "(ZZLd7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/TraktDeviceCode;", "deviceCode", "startTraktPolling", "(Lcom/arflix/tv/data/api/TraktDeviceCode;)V", "userCode", "", "expiresInSec", "intervalSec", "startSimklPolling", "(Ljava/lang/String;II)V", "Landroid/content/Context;", "Lcom/arflix/tv/data/repository/ProfileManager;", "Lcom/arflix/tv/data/repository/TraktRepository;", "Lcom/arflix/tv/data/repository/StreamRepository;", "Lcom/arflix/tv/data/repository/MediaRepository;", "Lcom/arflix/tv/data/repository/CatalogRepository;", "Lcom/arflix/tv/data/repository/CatalogDiscoveryRepository;", "Lcom/arflix/tv/data/repository/IptvRepository;", "Lcom/arflix/tv/data/repository/HomeServerRepository;", "Lcom/arflix/tv/data/repository/WatchlistRepository;", "Lcom/arflix/tv/data/repository/AuthRepository;", "Lcom/arflix/tv/data/repository/ProfileRepository;", "Lcom/arflix/tv/data/repository/TvDeviceAuthRepository;", "Lcom/arflix/tv/data/repository/TraktSyncService;", "Lcom/arflix/tv/data/repository/CloudSyncRepository;", "Lcom/arflix/tv/data/repository/LauncherContinueWatchingRepository;", "Lcom/arflix/tv/updater/AppUpdateRepository;", "Lcom/arflix/tv/updater/UpdatePreferences;", "Lcom/arflix/tv/updater/ApkDownloader;", "Lcom/arflix/tv/updater/UpdateStatusManager;", "Lcom/arflix/tv/data/repository/MdbListRepository;", "Lcom/arflix/tv/data/repository/sync/SyncProviderStore;", "Lcom/arflix/tv/data/repository/WatchHistoryRepository;", "Lcom/arflix/tv/data/repository/simkl/SimklAuthManager;", "Lna/q0;", "Lcom/arflix/tv/ui/screens/settings/SettingsUiState;", "_uiState", "Lna/q0;", "Lna/h1;", "uiState", "Lna/h1;", "getUiState", "()Lna/h1;", "dnsProviderKey", "Lc1/e;", "customUserAgentKey", "qualityFiltersKey", "subtitleAiEnabledKey", "subtitleAiAutoSelectKey", "subtitleAiFindBestMatchKey", "subtitlePreloadEnabledKey", "dolbyVisionCompatKey", "subtitleAiApiKeyKey", "subtitleAiModelKey", "subtitleRemoveHearingImpairedKey", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "lastObservedIptvM3u", "Ljava/lang/String;", "lastObservedStalkerUrl", "Lka/v1;", "traktPollingJob", "Lka/v1;", "simklPollingJob", "traktStartupJob", "loadSettingsJob", "integrationMetadataJob", "syncSummaryJob", "plexHomeServerPollingJob", "plexHomeServerUrl", "plexHomeServerDisplayName", "iptvLoadJob", "catalogSearchJob", "Lcom/arflix/tv/server/AiKeyConfigServer;", "aiKeyServer", "Lcom/arflix/tv/server/AiKeyConfigServer;", "lastCloudSyncedUserId", "cloudDeviceCode", "cloudUserCode", "cloudVerificationUrl", "", "cloudPollIntervalMs", "J", "cloudExpiresAtMs", "cloudPollingJob", "pendingProfileSwitchAfterCloudLogin", "Z", "observedProfileId", "hasObservedIptvConfig", "lastObservedIptvConfigSignature", "downloadJob", "CloudRestoreResult", "QualityFilterPreset", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SettingsViewModel extends androidx.lifecycle.d1 {
    public static final int $stable = 8;
    private final na.q0<SettingsUiState> _uiState;
    private AiKeyConfigServer aiKeyServer;
    private final ApkDownloader apkDownloader;
    private final AppUpdateRepository appUpdateRepository;
    private final AuthRepository authRepository;
    private final CatalogDiscoveryRepository catalogDiscoveryRepository;
    private final CatalogRepository catalogRepository;
    private ka.v1 catalogSearchJob;
    private String cloudDeviceCode;
    private long cloudExpiresAtMs;
    private long cloudPollIntervalMs;
    private ka.v1 cloudPollingJob;
    private final CloudSyncRepository cloudSyncRepository;
    private String cloudUserCode;
    private String cloudVerificationUrl;
    private final Context context;
    private final c1.e customUserAgentKey;
    private final c1.e dnsProviderKey;
    private final c1.e dolbyVisionCompatKey;
    private ka.v1 downloadJob;
    private final Gson gson;
    private boolean hasObservedIptvConfig;
    private final HomeServerRepository homeServerRepository;
    private ka.v1 integrationMetadataJob;
    private ka.v1 iptvLoadJob;
    private final IptvRepository iptvRepository;
    private String lastCloudSyncedUserId;
    private String lastObservedIptvConfigSignature;
    private String lastObservedIptvM3u;
    private String lastObservedStalkerUrl;
    private final LauncherContinueWatchingRepository launcherContinueWatchingRepository;
    private ka.v1 loadSettingsJob;
    private final MdbListRepository mdbListRepository;
    private final MediaRepository mediaRepository;
    private String observedProfileId;
    private boolean pendingProfileSwitchAfterCloudLogin;
    private String plexHomeServerDisplayName;
    private ka.v1 plexHomeServerPollingJob;
    private String plexHomeServerUrl;
    private final ProfileManager profileManager;
    private final ProfileRepository profileRepository;
    private final c1.e qualityFiltersKey;
    private final SimklAuthManager simklAuthManager;
    private ka.v1 simklPollingJob;
    private final StreamRepository streamRepository;
    private final c1.e subtitleAiApiKeyKey;
    private final c1.e subtitleAiAutoSelectKey;
    private final c1.e subtitleAiEnabledKey;
    private final c1.e subtitleAiFindBestMatchKey;
    private final c1.e subtitleAiModelKey;
    private final c1.e subtitlePreloadEnabledKey;
    private final c1.e subtitleRemoveHearingImpairedKey;
    private final SyncProviderStore syncProviderStore;
    private ka.v1 syncSummaryJob;
    private ka.v1 traktPollingJob;
    private final TraktRepository traktRepository;
    private ka.v1 traktStartupJob;
    private final TraktSyncService traktSyncService;
    private final TvDeviceAuthRepository tvDeviceAuthRepository;
    private final na.h1<SettingsUiState> uiState;
    private final UpdatePreferences updatePreferences;
    private final UpdateStatusManager updateStatusManager;
    private final WatchHistoryRepository watchHistoryRepository;
    private final WatchlistRepository watchlistRepository;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/arflix/tv/ui/screens/settings/SettingsViewModel$CloudRestoreResult;", "", "<init>", "(Ljava/lang/String;I)V", "RESTORED", "NO_BACKUP", "FAILED", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class CloudRestoreResult {
        private static final /* synthetic */ g7.a $ENTRIES;
        private static final /* synthetic */ CloudRestoreResult[] $VALUES;
        public static final CloudRestoreResult RESTORED = new CloudRestoreResult("RESTORED", 0);
        public static final CloudRestoreResult NO_BACKUP = new CloudRestoreResult("NO_BACKUP", 1);
        public static final CloudRestoreResult FAILED = new CloudRestoreResult("FAILED", 2);

        private static final /* synthetic */ CloudRestoreResult[] $values() {
            return new CloudRestoreResult[]{RESTORED, NO_BACKUP, FAILED};
        }

        static {
            CloudRestoreResult[] cloudRestoreResultArr$values = $values();
            $VALUES = cloudRestoreResultArr$values;
            $ENTRIES = new g7.b(cloudRestoreResultArr$values);
        }

        private CloudRestoreResult(String str, int i10) {
        }

        public static g7.a<CloudRestoreResult> getEntries() {
            return $ENTRIES;
        }

        public static CloudRestoreResult valueOf(String str) {
            return (CloudRestoreResult) Enum.valueOf(CloudRestoreResult.class, str);
        }

        public static CloudRestoreResult[] values() {
            return (CloudRestoreResult[]) $VALUES.clone();
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B%\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/ui/screens/settings/SettingsViewModel$QualityFilterPreset;", "", "label", "", "filterId", "regexPattern", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "getFilterId", "getRegexPattern", "OFF", "HD_1080_PLUS", "HD_1080_ONLY", "HD_720_PLUS", "CUSTOM", "toFilters", "", "Lcom/arflix/tv/data/model/QualityFilterConfig;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class QualityFilterPreset {
        private static final /* synthetic */ g7.a $ENTRIES;
        private static final /* synthetic */ QualityFilterPreset[] $VALUES;
        private final String filterId;
        private final String label;
        private final String regexPattern;
        public static final QualityFilterPreset OFF = new QualityFilterPreset("OFF", 0, "OFF", null, null);
        public static final QualityFilterPreset HD_1080_PLUS = new QualityFilterPreset("HD_1080_PLUS", 1, "1080p+", "preset_quality_1080_plus", "(?:360|480|576|720)p|cam|hdcam|hdts|hdtc|telesync|telecine|ts|tc|screener|scr|sd");
        public static final QualityFilterPreset HD_1080_ONLY = new QualityFilterPreset("HD_1080_ONLY", 2, "1080p only", "preset_quality_1080_only", "(?:2160|4k|uhd)|(?:360|480|576|720)p|cam|hdcam|hdts|hdtc|telesync|telecine|ts|tc|screener|scr|sd");
        public static final QualityFilterPreset HD_720_PLUS = new QualityFilterPreset("HD_720_PLUS", 3, "720p+", "preset_quality_720_plus", "(?:360|480|576)p|cam|hdcam|hdts|hdtc|telesync|telecine|ts|tc|screener|scr|sd");
        public static final QualityFilterPreset CUSTOM = new QualityFilterPreset("CUSTOM", 4, "CUSTOM", null, null);

        private static final /* synthetic */ QualityFilterPreset[] $values() {
            return new QualityFilterPreset[]{OFF, HD_1080_PLUS, HD_1080_ONLY, HD_720_PLUS, CUSTOM};
        }

        static {
            QualityFilterPreset[] qualityFilterPresetArr$values = $values();
            $VALUES = qualityFilterPresetArr$values;
            $ENTRIES = new g7.b(qualityFilterPresetArr$values);
        }

        private QualityFilterPreset(String str, int i10, String str2, String str3, String str4) {
            this.label = str2;
            this.filterId = str3;
            this.regexPattern = str4;
        }

        public static g7.a<QualityFilterPreset> getEntries() {
            return $ENTRIES;
        }

        public static QualityFilterPreset valueOf(String str) {
            return (QualityFilterPreset) Enum.valueOf(QualityFilterPreset.class, str);
        }

        public static QualityFilterPreset[] values() {
            return (QualityFilterPreset[]) $VALUES.clone();
        }

        public final String getFilterId() {
            return this.filterId;
        }

        public final String getLabel() {
            return this.label;
        }

        public final String getRegexPattern() {
            return this.regexPattern;
        }

        public final List<QualityFilterConfig> toFilters() {
            String str;
            return (this == OFF || this == CUSTOM || (str = this.filterId) == null || this.regexPattern == null) ? kotlin.collections.z.f19728i : Collections.singletonList(new QualityFilterConfig(str, androidx.compose.material3.d.C("Preset: ", this.label), this.regexPattern, true, 0L, 16, null));
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[CatalogKind.values().length];
            try {
                iArr[CatalogKind.COLLECTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CatalogKind.COLLECTION_RAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[CloudSyncRepository.RestoreResult.values().length];
            try {
                iArr2[CloudSyncRepository.RestoreResult.RESTORED.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[CloudSyncRepository.RestoreResult.NO_BACKUP.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[CloudSyncRepository.RestoreResult.FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$addCatalog$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$addCatalog$1", f = "SettingsViewModel.kt", l = {RemoteMediaPlayer.STATUS_CANCELED}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$url = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new AnonymousClass1(this.$url, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object objM6115addCustomCataloggIAlus;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                CatalogRepository catalogRepository = SettingsViewModel.this.catalogRepository;
                String str = this.$url;
                this.label = 1;
                objM6115addCustomCataloggIAlus = catalogRepository.m6115addCustomCataloggIAlus(str, this);
                e7.a aVar = e7.a.f15033i;
                if (objM6115addCustomCataloggIAlus == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objM6115addCustomCataloggIAlus = ((x6.d0) obj).f22580i;
            }
            SettingsViewModel settingsViewModel = SettingsViewModel.this;
            if (!(objM6115addCustomCataloggIAlus instanceof x6.c0)) {
                settingsViewModel._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, androidx.compose.material3.d.C("Added ", ((CatalogConfig) objM6115addCustomCataloggIAlus).getTitle()), ToastType.SUCCESS, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
                SettingsViewModel.syncLocalStateToCloud$default(settingsViewModel, true, false, 2, null);
            }
            SettingsViewModel settingsViewModel2 = SettingsViewModel.this;
            Throwable thA = x6.d0.a(objM6115addCustomCataloggIAlus);
            if (thA != null) {
                na.q0 q0Var = settingsViewModel2._uiState;
                SettingsUiState settingsUiState = (SettingsUiState) settingsViewModel2._uiState.getValue();
                String message = thA.getMessage();
                if (message == null) {
                    message = settingsViewModel2.context.getString(R.string.catalog_failed_add);
                }
                q0Var.setValue(SettingsUiState.copy$default(settingsUiState, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, message, ToastType.ERROR, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$addCustomAddon$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$addCustomAddon$1", f = "SettingsViewModel.kt", l = {1834, 1837, 1838, 1841}, m = "invokeSuspend", v = 2)
    public static final class C14661 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $url;
        int I$0;
        int I$1;
        int I$2;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14661(String str, d7.d<? super C14661> dVar) {
            super(2, dVar);
            this.$url = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C14661 c14661 = SettingsViewModel.this.new C14661(this.$url, dVar);
            c14661.L$0 = obj;
            return c14661;
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x00c9  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x00e2  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0104  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x012c  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x014c  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x026b  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x028d  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r147) {
            /*
                Method dump skipped, instruction units count: 933
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C14661.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14661) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$addDiscoveredCatalog$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$addDiscoveredCatalog$1", f = "SettingsViewModel.kt", l = {2171}, m = "invokeSuspend", v = 2)
    public static final class C14671 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ CatalogDiscoveryResult $result;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14671(CatalogDiscoveryResult catalogDiscoveryResult, d7.d<? super C14671> dVar) {
            super(2, dVar);
            this.$result = catalogDiscoveryResult;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14671(this.$result, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object objM6115addCustomCataloggIAlus;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                CatalogRepository catalogRepository = SettingsViewModel.this.catalogRepository;
                String sourceUrl = this.$result.getSourceUrl();
                this.label = 1;
                objM6115addCustomCataloggIAlus = catalogRepository.m6115addCustomCataloggIAlus(sourceUrl, this);
                e7.a aVar = e7.a.f15033i;
                if (objM6115addCustomCataloggIAlus == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objM6115addCustomCataloggIAlus = ((x6.d0) obj).f22580i;
            }
            SettingsViewModel settingsViewModel = SettingsViewModel.this;
            if (!(objM6115addCustomCataloggIAlus instanceof x6.c0)) {
                settingsViewModel._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, androidx.compose.material3.d.C("Added ", ((CatalogConfig) objM6115addCustomCataloggIAlus).getTitle()), ToastType.SUCCESS, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
                SettingsViewModel.syncLocalStateToCloud$default(settingsViewModel, true, false, 2, null);
            }
            SettingsViewModel settingsViewModel2 = SettingsViewModel.this;
            Throwable thA = x6.d0.a(objM6115addCustomCataloggIAlus);
            if (thA != null) {
                na.q0 q0Var = settingsViewModel2._uiState;
                SettingsUiState settingsUiState = (SettingsUiState) settingsViewModel2._uiState.getValue();
                String message = thA.getMessage();
                if (message == null) {
                    message = settingsViewModel2.context.getString(R.string.catalog_failed_add);
                }
                q0Var.setValue(SettingsUiState.copy$default(settingsUiState, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, message, ToastType.ERROR, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14671) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$addQualityFilter$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$addQualityFilter$3", f = "SettingsViewModel.kt", l = {1695}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass3 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $deviceName;
        final /* synthetic */ String $trimmedRegex;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(String str, String str2, d7.d<? super AnonymousClass3> dVar) {
            super(2, dVar);
            this.$deviceName = str;
            this.$trimmedRegex = str2;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new AnonymousClass3(this.$deviceName, this.$trimmedRegex, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                ArrayList arrayListH0 = kotlin.collections.x.H0(new QualityFilterConfig(UUID.randomUUID().toString(), kotlin.text.o.L0(this.$deviceName).toString(), this.$trimmedRegex, true, 0L, 16, null), ((SettingsUiState) SettingsViewModel.this._uiState.getValue()).getQualityFilters());
                SettingsViewModel settingsViewModel = SettingsViewModel.this;
                this.L$0 = null;
                this.label = 1;
                Object objSaveQualityFilters = settingsViewModel.saveQualityFilters(arrayListH0, this);
                e7.a aVar = e7.a.f15033i;
                if (objSaveQualityFilters == aVar) {
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

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$checkForAppUpdates$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$checkForAppUpdates$1", f = "SettingsViewModel.kt", l = {3289, 3290}, m = "invokeSuspend", v = 2)
    public static final class C14681 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $force;
        final /* synthetic */ boolean $showNoUpdateFeedback;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14681(boolean z, boolean z5, d7.d<? super C14681> dVar) {
            super(2, dVar);
            this.$force = z;
            this.$showNoUpdateFeedback = z5;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14681(this.$force, this.$showNoUpdateFeedback, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0055, code lost:
        
            if (r3.setLastCheckAtMs(r5, r137) == r4) goto L15;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r138) {
            /*
                Method dump skipped, instruction units count: 1009
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C14681.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14681) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$clearIptvConfig$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$clearIptvConfig$1", f = "SettingsViewModel.kt", l = {2464}, m = "invokeSuspend", v = 2)
    public static final class C14691 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C14691(d7.d<? super C14691> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14691(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws NoSuchAlgorithmException {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                ka.v1 v1Var = SettingsViewModel.this.iptvLoadJob;
                if (v1Var != null) {
                    v1Var.cancel((CancellationException) null);
                }
                IptvRepository iptvRepository = SettingsViewModel.this.iptvRepository;
                this.label = 1;
                Object objClearConfig = iptvRepository.clearConfig(this);
                e7.a aVar = e7.a.f15033i;
                if (objClearConfig == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            na.q0 q0Var = SettingsViewModel.this._uiState;
            SettingsUiState settingsUiState = (SettingsUiState) SettingsViewModel.this._uiState.getValue();
            ToastType toastType = ToastType.SUCCESS;
            q0Var.setValue(SettingsUiState.copy$default(settingsUiState, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, "IPTV playlist removed", toastType, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, "IPTV playlist removed", toastType, false, false, false, false, false, null, null, false, null, false, -1, -1, -32513, 1072955391, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14691) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$completeCloudAuthWithEmailPassword$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$completeCloudAuthWithEmailPassword$2", f = "SettingsViewModel.kt", l = {2589, 2611}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $createAccount;
        final /* synthetic */ String $password;
        final /* synthetic */ String $trimmedEmail;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(String str, String str2, boolean z, d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
            this.$trimmedEmail = str;
            this.$password = str2;
            this.$createAccount = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new AnonymousClass2(this.$trimmedEmail, this.$password, this.$createAccount, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:34:0x02cf, code lost:
        
            if (r0 == r7) goto L35;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r141) {
            /*
                Method dump skipped, instruction units count: 1600
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$confirmInstallPack$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$confirmInstallPack$1", f = "SettingsViewModel.kt", l = {2062}, m = "invokeSuspend", v = 2)
    public static final class C14701 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ CatalogPackManifest $manifest;
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14701(String str, CatalogPackManifest catalogPackManifest, d7.d<? super C14701> dVar) {
            super(2, dVar);
            this.$url = str;
            this.$manifest = catalogPackManifest;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14701(this.$url, this.$manifest, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object objM6114addCatalogPack0E7RQCE;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, true, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, 1073741823, 1073741823, null));
                CatalogRepository catalogRepository = SettingsViewModel.this.catalogRepository;
                String str = this.$url;
                CatalogPackManifest catalogPackManifest = this.$manifest;
                this.label = 1;
                objM6114addCatalogPack0E7RQCE = catalogRepository.m6114addCatalogPack0E7RQCE(str, catalogPackManifest, this);
                e7.a aVar = e7.a.f15033i;
                if (objM6114addCatalogPack0E7RQCE == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objM6114addCatalogPack0E7RQCE = ((x6.d0) obj).f22580i;
            }
            SettingsViewModel settingsViewModel = SettingsViewModel.this;
            if (!(objM6114addCatalogPack0E7RQCE instanceof x6.c0)) {
                settingsViewModel._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, androidx.compose.material3.d.C("Installed pack: ", ((CatalogPackManifest) objM6114addCatalogPack0E7RQCE).getName()), ToastType.SUCCESS, false, false, false, false, false, null, null, false, null, false, -1, -1, -1879048193, 1072955391, null));
                SettingsViewModel.syncLocalStateToCloud$default(settingsViewModel, true, false, 2, null);
            }
            SettingsViewModel settingsViewModel2 = SettingsViewModel.this;
            Throwable thA = x6.d0.a(objM6114addCatalogPack0E7RQCE);
            if (thA != null) {
                na.q0 q0Var = settingsViewModel2._uiState;
                SettingsUiState settingsUiState = (SettingsUiState) settingsViewModel2._uiState.getValue();
                String message = thA.getMessage();
                if (message == null) {
                    message = "Failed to install pack";
                }
                q0Var.setValue(SettingsUiState.copy$default(settingsUiState, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, message, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, 1073741823, 1073741823, null));
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14701) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$connectHomeServer$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$connectHomeServer$1", f = "SettingsViewModel.kt", l = {2804, 2806, 2807}, m = "invokeSuspend", v = 2)
    public static final class C14711 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $displayName;
        final /* synthetic */ String $password;
        final /* synthetic */ String $serverUrl;
        final /* synthetic */ String $username;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14711(String str, String str2, String str3, String str4, d7.d<? super C14711> dVar) {
            super(2, dVar);
            this.$serverUrl = str;
            this.$username = str2;
            this.$password = str3;
            this.$displayName = str4;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14711(this.$serverUrl, this.$username, this.$password, this.$displayName, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x01c2, code lost:
        
            if (r1 != r11) goto L8;
         */
        /* JADX WARN: Removed duplicated region for block: B:28:0x02ea  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r146) {
            /*
                Method dump skipped, instruction units count: 1057
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C14711.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14711) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$connectMdbList$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$connectMdbList$1", f = "SettingsViewModel.kt", l = {3645, 3656, 3659, 3660, 3661, 3662, 3694}, m = "invokeSuspend", v = 2)
    public static final class C14721 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $trimmed;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14721(String str, d7.d<? super C14721> dVar) {
            super(2, dVar);
            this.$trimmed = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C14721 c14721 = SettingsViewModel.this.new C14721(this.$trimmed, dVar);
            c14721.L$0 = obj;
            return c14721;
        }

        /* JADX WARN: Code restructure failed: missing block: B:39:0x02f1, code lost:
        
            if (r8.logout(r142) == r7) goto L60;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x031a, code lost:
        
            if (r8.disconnect(r142) == r7) goto L60;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x04c9, code lost:
        
            if (r2.refreshForCurrentProfile(r142) != r7) goto L69;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:32:0x01ac  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x01b5  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x02d7  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x034e A[PHI: r0
          0x034e: PHI (r0v18 boolean) = (r0v44 boolean), (r0v45 boolean) binds: [B:52:0x034a, B:10:0x0031] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:57:0x0363 A[PHI: r0 r2
          0x0363: PHI (r0v19 boolean) = (r0v42 boolean), (r0v43 boolean) binds: [B:55:0x035f, B:9:0x0028] A[DONT_GENERATE, DONT_INLINE]
          0x0363: PHI (r2v10 java.lang.Object) = (r2v9 java.lang.Object), (r2v20 java.lang.Object) binds: [B:55:0x035f, B:9:0x0028] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Type inference failed for: r0v14 */
        /* JADX WARN: Type inference failed for: r0v16 */
        /* JADX WARN: Type inference failed for: r0v48 */
        /* JADX WARN: Type inference failed for: r0v49 */
        /* JADX WARN: Type inference failed for: r0v50 */
        /* JADX WARN: Type inference failed for: r0v51 */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r143) {
            /*
                Method dump skipped, instruction units count: 1250
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C14721.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14721) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleAccentColor$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleAccentColor$1", f = "SettingsViewModel.kt", l = {1429}, m = "invokeSuspend", v = 2)
    public static final class C14731 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $next;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleAccentColor$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleAccentColor$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01591 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $next;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01591(String str, d7.d<? super C01591> dVar) {
                super(2, dVar);
                this.$next = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01591 c01591 = new C01591(this.$next, dVar);
                c01591.L$0 = obj;
                return c01591;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01591) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(DeviceTypeKt.getACCENT_COLOR_KEY(), this.$next);
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14731(String str, d7.d<? super C14731> dVar) {
            super(2, dVar);
            this.$next = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14731(this.$next, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01591 c01591 = new C01591(this.$next, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01591, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, this.$next, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1073676287, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14731) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleClockFormat$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleClockFormat$1", f = "SettingsViewModel.kt", l = {1413}, m = "invokeSuspend", v = 2)
    public static final class C14741 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $next;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleClockFormat$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleClockFormat$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01601 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $next;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01601(SettingsViewModel settingsViewModel, String str, d7.d<? super C01601> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$next = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01601 c01601 = new C01601(this.this$0, this.$next, dVar);
                c01601.L$0 = obj;
                return c01601;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01601) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.clockFormatKey(), this.$next);
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14741(String str, d7.d<? super C14741> dVar) {
            super(2, dVar);
            this.$next = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14741(this.$next, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01601 c01601 = new C01601(SettingsViewModel.this, this.$next, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01601, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, this.$next, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1073733631, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14741) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleQualityFilterPreset$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleQualityFilterPreset$1", f = "SettingsViewModel.kt", l = {1747}, m = "invokeSuspend", v = 2)
    public static final class C14751 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        Object L$0;
        Object L$1;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleQualityFilterPreset$1$WhenMappings */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[QualityFilterPreset.values().length];
                try {
                    iArr[QualityFilterPreset.OFF.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[QualityFilterPreset.HD_1080_PLUS.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[QualityFilterPreset.HD_1080_ONLY.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[QualityFilterPreset.HD_720_PLUS.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr[QualityFilterPreset.CUSTOM.ordinal()] = 5;
                } catch (NoSuchFieldError unused5) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public C14751(d7.d<? super C14751> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14751(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            QualityFilterPreset qualityFilterPreset;
            int i10 = this.label;
            x6.t0 t0Var = x6.t0.f22605a;
            if (i10 == 0) {
                k2.c.G(obj);
                SettingsViewModel settingsViewModel = SettingsViewModel.this;
                QualityFilterPreset qualityFilterPresetDetectQualityFilterPreset = settingsViewModel.detectQualityFilterPreset(((SettingsUiState) settingsViewModel._uiState.getValue()).getQualityFilters());
                if (qualityFilterPresetDetectQualityFilterPreset == QualityFilterPreset.CUSTOM) {
                    SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, "Custom filters detected — use manual editing to modify", ToastType.INFO, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
                    return t0Var;
                }
                int i11 = WhenMappings.$EnumSwitchMapping$0[qualityFilterPresetDetectQualityFilterPreset.ordinal()];
                if (i11 == 1) {
                    qualityFilterPreset = QualityFilterPreset.HD_1080_PLUS;
                } else if (i11 == 2) {
                    qualityFilterPreset = QualityFilterPreset.HD_1080_ONLY;
                } else if (i11 == 3) {
                    qualityFilterPreset = QualityFilterPreset.HD_720_PLUS;
                } else {
                    if (i11 != 4) {
                        if (i11 == 5) {
                            return t0Var;
                        }
                        throw new NoWhenBranchMatchedException();
                    }
                    qualityFilterPreset = QualityFilterPreset.OFF;
                }
                SettingsViewModel settingsViewModel2 = SettingsViewModel.this;
                List<QualityFilterConfig> filters = qualityFilterPreset.toFilters();
                this.L$0 = null;
                this.L$1 = null;
                this.label = 1;
                Object objSaveQualityFilters = settingsViewModel2.saveQualityFilters(filters, this);
                e7.a aVar = e7.a.f15033i;
                if (objSaveQualityFilters == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14751) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleColor$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleColor$1", f = "SettingsViewModel.kt", l = {1465}, m = "invokeSuspend", v = 2)
    public static final class C14761 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $next;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleColor$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleColor$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01611 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $next;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01611(SettingsViewModel settingsViewModel, String str, d7.d<? super C01611> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$next = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01611 c01611 = new C01611(this.this$0, this.$next, dVar);
                c01611.L$0 = obj;
                return c01611;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01611) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.subtitleColorKey(), this.$next);
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14761(String str, d7.d<? super C14761> dVar) {
            super(2, dVar);
            this.$next = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14761(this.$next, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01611 c01611 = new C01611(SettingsViewModel.this, this.$next, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01611, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, this.$next, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -8193, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14761) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleOffset$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleOffset$1", f = "SettingsViewModel.kt", l = {1470}, m = "invokeSuspend", v = 2)
    public static final class C14771 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $next;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleOffset$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleOffset$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01621 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $next;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01621(SettingsViewModel settingsViewModel, String str, d7.d<? super C01621> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$next = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01621 c01621 = new C01621(this.this$0, this.$next, dVar);
                c01621.L$0 = obj;
                return c01621;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01621) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.subtitleOffsetKey(), this.$next);
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14771(String str, d7.d<? super C14771> dVar) {
            super(2, dVar);
            this.$next = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14771(this.$next, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01621 c01621 = new C01621(SettingsViewModel.this, this.$next, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01621, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, this.$next, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -32769, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14771) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleSize$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleSize$1", f = "SettingsViewModel.kt", l = {1460}, m = "invokeSuspend", v = 2)
    public static final class C14781 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $next;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleSize$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleSize$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01631 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $next;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01631(SettingsViewModel settingsViewModel, String str, d7.d<? super C01631> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$next = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01631 c01631 = new C01631(this.this$0, this.$next, dVar);
                c01631.L$0 = obj;
                return c01631;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01631) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.subtitleSizeKey(), this.$next);
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14781(String str, d7.d<? super C14781> dVar) {
            super(2, dVar);
            this.$next = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14781(this.$next, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01631 c01631 = new C01631(SettingsViewModel.this, this.$next, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01631, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, this.$next, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -4097, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14781) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleStyle$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleStyle$1", f = "SettingsViewModel.kt", l = {1475}, m = "invokeSuspend", v = 2)
    public static final class C14791 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $next;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleStyle$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleStyle$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01641 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $next;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01641(SettingsViewModel settingsViewModel, String str, d7.d<? super C01641> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$next = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01641 c01641 = new C01641(this.this$0, this.$next, dVar);
                c01641.L$0 = obj;
                return c01641;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01641) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.subtitleStyleKey(), this.$next);
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14791(String str, d7.d<? super C14791> dVar) {
            super(2, dVar);
            this.$next = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14791(this.$next, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01641 c01641 = new C01641(SettingsViewModel.this, this.$next, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01641, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, this.$next, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -16385, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14791) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleTrailerDelay$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleTrailerDelay$1", f = "SettingsViewModel.kt", l = {1372}, m = "invokeSuspend", v = 2)
    public static final class C14801 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ int $next;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleTrailerDelay$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleTrailerDelay$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01651 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ int $next;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01651(SettingsViewModel settingsViewModel, int i10, d7.d<? super C01651> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$next = i10;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01651 c01651 = new C01651(this.this$0, this.$next, dVar);
                c01651.L$0 = obj;
                return c01651;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01651) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.trailerDelayKey(), String.valueOf(this.$next));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14801(int i10, d7.d<? super C14801> dVar) {
            super(2, dVar);
            this.$next = i10;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14801(this.$next, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01651 c01651 = new C01651(SettingsViewModel.this, this.$next, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01651, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, this.$next, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -2097153, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14801) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleVolumeBoost$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleVolumeBoost$1", f = "SettingsViewModel.kt", l = {1452}, m = "invokeSuspend", v = 2)
    public static final class C14811 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ int $next;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleVolumeBoost$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleVolumeBoost$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01661 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ int $next;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01661(SettingsViewModel settingsViewModel, int i10, d7.d<? super C01661> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$next = i10;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01661 c01661 = new C01661(this.this$0, this.$next, dVar);
                c01661.L$0 = obj;
                return c01661;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01661) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.volumeBoostDbKey(), String.valueOf(this.$next));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14811(int i10, d7.d<? super C14811> dVar) {
            super(2, dVar);
            this.$next = i10;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14811(this.$next, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01661 c01661 = new C01661(SettingsViewModel.this, this.$next, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01661, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, this.$next, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -33554433, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14811) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$deleteQualityFilter$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$deleteQualityFilter$1", f = "SettingsViewModel.kt", l = {1763}, m = "invokeSuspend", v = 2)
    public static final class C14821 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $filterId;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14821(String str, d7.d<? super C14821> dVar) {
            super(2, dVar);
            this.$filterId = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14821(this.$filterId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                List<QualityFilterConfig> qualityFilters = ((SettingsUiState) SettingsViewModel.this._uiState.getValue()).getQualityFilters();
                String str = this.$filterId;
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : qualityFilters) {
                    if (!kotlin.jvm.internal.p.a(((QualityFilterConfig) obj2).getId(), str)) {
                        arrayList.add(obj2);
                    }
                }
                SettingsViewModel settingsViewModel = SettingsViewModel.this;
                this.L$0 = null;
                this.label = 1;
                Object objSaveQualityFilters = settingsViewModel.saveQualityFilters(arrayList, this);
                e7.a aVar = e7.a.f15033i;
                if (objSaveQualityFilters == aVar) {
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
            return ((C14821) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$disconnectHomeServer$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$disconnectHomeServer$1", f = "SettingsViewModel.kt", l = {2987, 2988}, m = "invokeSuspend", v = 2)
    public static final class C14831 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C14831(d7.d<? super C14831> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14831(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0044, code lost:
        
            if (r1.syncHomeServerCatalogs(r2, r137) == r6) goto L15;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r138) {
            /*
                Method dump skipped, instruction units count: 602
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C14831.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14831) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$disconnectMdbList$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$disconnectMdbList$1", f = "SettingsViewModel.kt", l = {3700, 3701, 3702}, m = "invokeSuspend", v = 2)
    public static final class C14841 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C14841(d7.d<? super C14841> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14841(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0057, code lost:
        
            if (r1 != r5) goto L21;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r138) {
            /*
                Method dump skipped, instruction units count: 410
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C14841.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14841) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$disconnectSimkl$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$disconnectSimkl$1", f = "SettingsViewModel.kt", l = {3845, 3846}, m = "invokeSuspend", v = 2)
    public static final class C14851 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C14851(d7.d<? super C14851> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14851(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x003e, code lost:
        
            if (r1 == r4) goto L15;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r137) {
            /*
                Method dump skipped, instruction units count: 363
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C14851.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14851) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$disconnectTrakt$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$disconnectTrakt$1", f = "SettingsViewModel.kt", l = {3611, 3612, 3613}, m = "invokeSuspend", v = 2)
    public static final class C14861 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C14861(d7.d<? super C14861> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14861(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x005b, code lost:
        
            if (r1 != r5) goto L21;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r138) {
            /*
                Method dump skipped, instruction units count: 398
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C14861.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14861) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$downloadAppUpdate$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$downloadAppUpdate$1", f = "SettingsViewModel.kt", l = {3357}, m = "invokeSuspend", v = 2)
    public static final class C14871 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ AppUpdate $update;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14871(AppUpdate appUpdate, d7.d<? super C14871> dVar) {
            super(2, dVar);
            this.$update = appUpdate;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14871(this.$update, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SettingsViewModel.this.updateStatusManager.updateStatus(new UpdateStatus.Downloading(new Float(0.0f), this.$update));
                File file = new File(new File(SettingsViewModel.this.context.getCacheDir(), "updates"), Pattern.compile("[^a-zA-Z0-9._-]").matcher(this.$update.getAssetName()).replaceAll("_"));
                ra.c cVar = ka.x0.f19655d;
                SettingsViewModel$downloadAppUpdate$1$result$1 settingsViewModel$downloadAppUpdate$1$result$1 = new SettingsViewModel$downloadAppUpdate$1$result$1(SettingsViewModel.this, this.$update, file, null);
                this.L$0 = null;
                this.L$1 = null;
                this.label = 1;
                obj = ka.m0.y(cVar, settingsViewModel$downloadAppUpdate$1$result$1, this);
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
            Object obj2 = ((x6.d0) obj).f22580i;
            SettingsViewModel settingsViewModel = SettingsViewModel.this;
            AppUpdate appUpdate = this.$update;
            if (!(obj2 instanceof x6.c0)) {
                settingsViewModel.updateStatusManager.updateStatus(new UpdateStatus.ReadyToInstall(((File) obj2).getAbsolutePath(), appUpdate));
                settingsViewModel.installAppUpdateOrRequestPermission();
            }
            SettingsViewModel settingsViewModel2 = SettingsViewModel.this;
            AppUpdate appUpdate2 = this.$update;
            Throwable thA = x6.d0.a(obj2);
            if (thA != null) {
                UpdateStatusManager updateStatusManager = settingsViewModel2.updateStatusManager;
                String message = thA.getMessage();
                if (message == null) {
                    message = settingsViewModel2.context.getString(R.string.update_download_failed);
                }
                updateStatusManager.updateStatus(new UpdateStatus.Failure(message, appUpdate2));
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14871) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$ensureCloudSyncSession$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel", f = "SettingsViewModel.kt", l = {3229, 3232, 3233, 3235}, m = "ensureCloudSyncSession", v = 2)
    public static final class C14881 extends f7.c {
        int label;
        /* synthetic */ Object result;

        public C14881(d7.d<? super C14881> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SettingsViewModel.this.ensureCloudSyncSession(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$forceCloudPullOnly$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$forceCloudPullOnly$1", f = "SettingsViewModel.kt", l = {3194, 3204}, m = "invokeSuspend", v = 2)
    public static final class C14891 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$forceCloudPullOnly$1$WhenMappings */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[CloudRestoreResult.values().length];
                try {
                    iArr[CloudRestoreResult.RESTORED.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[CloudRestoreResult.NO_BACKUP.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[CloudRestoreResult.FAILED.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public C14891(d7.d<? super C14891> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14891(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x02a5, code lost:
        
            if (r1 == r5) goto L19;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r140) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 1095
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C14891.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14891) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$forceCloudPushOnly$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$forceCloudPushOnly$1", f = "SettingsViewModel.kt", l = {3149, 3159, 3160}, m = "invokeSuspend", v = 2)
    public static final class C14901 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C14901(d7.d<? super C14901> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14901(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x02bd, code lost:
        
            if (r1 != r6) goto L25;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r141) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 1345
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C14901.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14901) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$forceCloudSyncNow$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$forceCloudSyncNow$1", f = "SettingsViewModel.kt", l = {3057, 3069, 3070, 3083, 3084, 3100, 3108, 3109}, m = "invokeSuspend", v = 2)
    public static final class C14911 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        Object L$0;
        Object L$1;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$forceCloudSyncNow$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "Lx6/d0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$forceCloudSyncNow$1$1", f = "SettingsViewModel.kt", l = {3085}, m = "invokeSuspend", v = 2)
        public static final class C01671 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01671(SettingsViewModel settingsViewModel, d7.d<? super C01671> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                return new C01671(this.this$0, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                Object objM6124pushLocalSnapshotToCloudIoAF18A;
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    CloudSyncRepository cloudSyncRepository = this.this$0.cloudSyncRepository;
                    this.label = 1;
                    objM6124pushLocalSnapshotToCloudIoAF18A = cloudSyncRepository.m6124pushLocalSnapshotToCloudIoAF18A(this);
                    e7.a aVar = e7.a.f15033i;
                    if (objM6124pushLocalSnapshotToCloudIoAF18A == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    objM6124pushLocalSnapshotToCloudIoAF18A = ((x6.d0) obj).f22580i;
                }
                return new x6.d0(objM6124pushLocalSnapshotToCloudIoAF18A);
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
                return ((C01671) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$forceCloudSyncNow$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/ui/screens/settings/SettingsViewModel$CloudRestoreResult;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/ui/screens/settings/SettingsViewModel$CloudRestoreResult;"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$forceCloudSyncNow$1$2", f = "SettingsViewModel.kt", l = {3110}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super CloudRestoreResult>, Object> {
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(SettingsViewModel settingsViewModel, d7.d<? super AnonymousClass2> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass2(this.this$0, dVar);
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
                SettingsViewModel settingsViewModel = this.this$0;
                this.label = 1;
                Object objRestoreCloudStateToLocalInternal = settingsViewModel.restoreCloudStateToLocalInternal(true, false, this);
                e7.a aVar = e7.a.f15033i;
                return objRestoreCloudStateToLocalInternal == aVar ? aVar : objRestoreCloudStateToLocalInternal;
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super CloudRestoreResult> dVar) {
                return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$forceCloudSyncNow$1$WhenMappings */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[CloudRestoreResult.values().length];
                try {
                    iArr[CloudRestoreResult.RESTORED.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[CloudRestoreResult.NO_BACKUP.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[CloudRestoreResult.FAILED.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public C14911(d7.d<? super C14911> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14911(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:37:0x0420, code lost:
        
            if (r1 != r11) goto L39;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x046d, code lost:
        
            if (r1 != r11) goto L58;
         */
        /* JADX WARN: Removed duplicated region for block: B:19:0x019f  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x02bc  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x02dd A[PHI: r1
          0x02dd: PHI (r1v16 java.lang.Object) = (r1v15 java.lang.Object), (r1v22 java.lang.Object) binds: [B:25:0x02d9, B:11:0x0056] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x02e1  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x03fe  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0446  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x044c  */
        /* JADX WARN: Removed duplicated region for block: B:63:0x0495  */
        /* JADX WARN: Removed duplicated region for block: B:70:0x04a7  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x04b2  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x04c2  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x04c9  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x04ce  */
        /* JADX WARN: Removed duplicated region for block: B:92:0x05e5  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r146) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 1838
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C14911.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14911) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$ignoreAppUpdate$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$ignoreAppUpdate$1", f = "SettingsViewModel.kt", l = {3332}, m = "invokeSuspend", v = 2)
    public static final class C14921 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ UpdateStatus $currentStatus;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14921(UpdateStatus updateStatus, d7.d<? super C14921> dVar) {
            super(2, dVar);
            this.$currentStatus = updateStatus;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14921(this.$currentStatus, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                UpdatePreferences updatePreferences = SettingsViewModel.this.updatePreferences;
                String tag = ((UpdateStatus.UpdateAvailable) this.$currentStatus).getUpdate().getTag();
                this.label = 1;
                Object ignoredTag = updatePreferences.setIgnoredTag(tag, this);
                e7.a aVar = e7.a.f15033i;
                if (ignoredTag == aVar) {
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
            return ((C14921) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$initializeCatalogs$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$initializeCatalogs$1", f = "SettingsViewModel.kt", l = {CastStatusCodes.DEVICE_CONNECTION_SUSPENDED}, m = "invokeSuspend", v = 2)
    public static final class C14931 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        public C14931(d7.d<? super C14931> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C14931 c14931 = SettingsViewModel.this.new C14931(dVar);
            c14931.L$0 = obj;
            return c14931;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    SettingsViewModel settingsViewModel = SettingsViewModel.this;
                    CatalogRepository catalogRepository = settingsViewModel.catalogRepository;
                    List<CatalogConfig> defaultCatalogConfigs = settingsViewModel.mediaRepository.getDefaultCatalogConfigs();
                    this.L$0 = null;
                    this.L$1 = null;
                    this.I$0 = 0;
                    this.label = 1;
                    obj = catalogRepository.ensurePreinstalledDefaults(defaultCatalogConfigs, this);
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
            return ((C14931) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$initializeUpdaterState$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$initializeUpdaterState$1", f = "SettingsViewModel.kt", l = {449, 455}, m = "invokeSuspend", v = 2)
    public static final class C14941 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        public C14941(d7.d<? super C14941> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14941(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:20:0x007b, code lost:
        
            if (r7.setIgnoredTag(null, r6) == r3) goto L21;
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
                if (r0 == 0) goto L2c
                if (r0 == r2) goto L28
                if (r0 != r1) goto L20
                java.lang.Object r0 = r6.L$3
                java.lang.String r0 = (java.lang.String) r0
                java.lang.Object r0 = r6.L$2
                java.lang.String r0 = (java.lang.String) r0
                java.lang.Object r0 = r6.L$1
                java.lang.String r0 = (java.lang.String) r0
                java.lang.Object r0 = r6.L$0
                java.lang.String r0 = (java.lang.String) r0
                k2.c.G(r7)
                goto L7e
            L20:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L28:
                k2.c.G(r7)
                goto L42
            L2c:
                k2.c.G(r7)
                com.arflix.tv.ui.screens.settings.SettingsViewModel r7 = com.arflix.tv.ui.screens.settings.SettingsViewModel.this
                com.arflix.tv.updater.UpdatePreferences r7 = com.arflix.tv.ui.screens.settings.SettingsViewModel.access$getUpdatePreferences$p(r7)
                na.j r7 = r7.getIgnoredTag()
                r6.label = r2
                java.lang.Object r7 = na.y0.l(r7, r6)
                if (r7 != r3) goto L42
                goto L7d
            L42:
                java.lang.String r7 = (java.lang.String) r7
                if (r7 == 0) goto L7e
                com.arflix.tv.ui.screens.settings.SettingsViewModel r0 = com.arflix.tv.ui.screens.settings.SettingsViewModel.this
                com.arflix.tv.updater.AppUpdateRepository r0 = com.arflix.tv.ui.screens.settings.SettingsViewModel.access$getAppUpdateRepository$p(r0)
                java.lang.String r0 = r0.getInstalledVersionName()
                com.arflix.tv.updater.VersionUtils r2 = com.arflix.tv.updater.VersionUtils.INSTANCE
                java.lang.String r4 = r2.normalize(r7)
                java.lang.String r5 = r2.normalize(r0)
                boolean r4 = kotlin.jvm.internal.p.a(r4, r5)
                if (r4 != 0) goto L66
                boolean r7 = r2.isRemoteNewer(r7, r0)
                if (r7 != 0) goto L7e
            L66:
                com.arflix.tv.ui.screens.settings.SettingsViewModel r7 = com.arflix.tv.ui.screens.settings.SettingsViewModel.this
                com.arflix.tv.updater.UpdatePreferences r7 = com.arflix.tv.ui.screens.settings.SettingsViewModel.access$getUpdatePreferences$p(r7)
                r0 = 0
                r6.L$0 = r0
                r6.L$1 = r0
                r6.L$2 = r0
                r6.L$3 = r0
                r6.label = r1
                java.lang.Object r7 = r7.setIgnoredTag(r0, r6)
                if (r7 != r3) goto L7e
            L7d:
                return r3
            L7e:
                x6.t0 r7 = x6.t0.f22605a
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C14941.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14941) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$initializeUpdaterState$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$initializeUpdaterState$2", f = "SettingsViewModel.kt", l = {461}, m = "invokeSuspend", v = 2)
    public static final class C14952 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C14952(d7.d<? super C14952> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14952(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.h1<UpdateStatus> status = SettingsViewModel.this.updateStatusManager.getStatus();
                final SettingsViewModel settingsViewModel = SettingsViewModel.this;
                na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.settings.SettingsViewModel.initializeUpdaterState.2.1
                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                        return emit((UpdateStatus) obj2, (d7.d<? super x6.t0>) dVar);
                    }

                    public final Object emit(UpdateStatus updateStatus, d7.d<? super x6.t0> dVar) {
                        settingsViewModel._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, updateStatus, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1048577, 1073741823, null));
                        return x6.t0.f22605a;
                    }
                };
                this.label = 1;
                Object objCollect = status.collect(kVar, this);
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
            throw new KotlinNothingValueException();
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14952) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$installAppUpdateOrRequestPermission$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$installAppUpdateOrRequestPermission$1", f = "SettingsViewModel.kt", l = {3415}, m = "invokeSuspend", v = 2)
    public static final class C14961 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ AppUpdate $update;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14961(AppUpdate appUpdate, d7.d<? super C14961> dVar) {
            super(2, dVar);
            this.$update = appUpdate;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14961(this.$update, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                UpdatePreferences updatePreferences = SettingsViewModel.this.updatePreferences;
                String tag = this.$update.getTag();
                this.label = 1;
                Object ignoredTag = updatePreferences.setIgnoredTag(tag, this);
                e7.a aVar = e7.a.f15033i;
                if (ignoredTag == aVar) {
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
            return ((C14961) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$loadIptvGroupsForPlaylist$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel", f = "SettingsViewModel.kt", l = {885, 895, 896, 897}, m = "loadIptvGroupsForPlaylist", v = 2)
    public static final class C14971 extends f7.c {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C14971(d7.d<? super C14971> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SettingsViewModel.this.loadIptvGroupsForPlaylist(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$loadIptvGroupsForPlaylist$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$loadIptvGroupsForPlaylist$2", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C14982 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends String>>, Object> {
        final /* synthetic */ String $playlistId;
        final /* synthetic */ IptvSnapshot $snapshot;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14982(IptvSnapshot iptvSnapshot, String str, d7.d<? super C14982> dVar) {
            super(2, dVar);
            this.$snapshot = iptvSnapshot;
            this.$playlistId = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invokeSuspend$lambda$0(String str, IptvChannel iptvChannel) {
            return kotlin.text.u.P(iptvChannel.getId(), str + ":", false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String invokeSuspend$lambda$1(IptvChannel iptvChannel) {
            String string = kotlin.text.o.L0(iptvChannel.getGroup()).toString();
            return kotlin.text.o.h0(string) ? "Ungrouped" : string;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return new C14982(this.$snapshot, this.$playlistId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            List<IptvChannel> channels;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            IptvSnapshot iptvSnapshot = this.$snapshot;
            List listU = (iptvSnapshot == null || (channels = iptvSnapshot.getChannels()) == null) ? null : ga.r.U(ga.r.G(new ga.t(ga.r.I(new ga.p(channels, 3), new h4(this.$playlistId, 0)), new i4(0))));
            return listU == null ? kotlin.collections.z.f19728i : listU;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super List<String>> dVar) {
            return ((C14982) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$loadPackManifest$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$loadPackManifest$1", f = "SettingsViewModel.kt", l = {2029}, m = "invokeSuspend", v = 2)
    public static final class C14991 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14991(String str, d7.d<? super C14991> dVar) {
            super(2, dVar);
            this.$url = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C14991(this.$url, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object objM6116fetchCatalogPackManifestgIAlus;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, true, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, 268435455, 1073741823, null));
                CatalogRepository catalogRepository = SettingsViewModel.this.catalogRepository;
                String str = this.$url;
                this.label = 1;
                objM6116fetchCatalogPackManifestgIAlus = catalogRepository.m6116fetchCatalogPackManifestgIAlus(str, this);
                e7.a aVar = e7.a.f15033i;
                if (objM6116fetchCatalogPackManifestgIAlus == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objM6116fetchCatalogPackManifestgIAlus = ((x6.d0) obj).f22580i;
            }
            SettingsViewModel settingsViewModel = SettingsViewModel.this;
            String str2 = this.$url;
            if (!(objM6116fetchCatalogPackManifestgIAlus instanceof x6.c0)) {
                settingsViewModel._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, (CatalogPackManifest) objM6116fetchCatalogPackManifestgIAlus, str2, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1879048193, 1073741823, null));
            }
            SettingsViewModel settingsViewModel2 = SettingsViewModel.this;
            Throwable thA = x6.d0.a(objM6116fetchCatalogPackManifestgIAlus);
            if (thA != null) {
                na.q0 q0Var = settingsViewModel2._uiState;
                SettingsUiState settingsUiState = (SettingsUiState) settingsViewModel2._uiState.getValue();
                String message = thA.getMessage();
                if (message == null) {
                    message = "Failed to load pack manifest";
                }
                q0Var.setValue(SettingsUiState.copy$default(settingsUiState, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, message, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, 536870911, 1073741823, null));
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C14991) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$loadSettings$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$loadSettings$1", f = "SettingsViewModel.kt", l = {481, 497, 500, 564, 567, 568, 569, 570, 577, 580, 583, 586, 593}, m = "invokeSuspend", v = 2)
    public static final class C15001 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int I$0;
        int I$1;
        int I$10;
        int I$11;
        int I$12;
        int I$13;
        int I$14;
        int I$15;
        int I$16;
        int I$17;
        int I$18;
        int I$19;
        int I$2;
        int I$20;
        int I$21;
        int I$22;
        int I$23;
        int I$24;
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
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        boolean Z$1;
        boolean Z$2;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$loadSettings$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$loadSettings$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01681 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01681(SettingsViewModel settingsViewModel, d7.d<? super C01681> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01681 c01681 = new C01681(this.this$0, dVar);
                c01681.L$0 = obj;
                return c01681;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01681) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.autoPlaySingleSourceKey(), Boolean.TRUE);
                return x6.t0.f22605a;
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$loadSettings$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$loadSettings$1$2", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass2 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(SettingsViewModel settingsViewModel, d7.d<? super AnonymousClass2> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, dVar);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass2) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.autoPlayNextKey(), Boolean.TRUE);
                return x6.t0.f22605a;
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$loadSettings$1$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$loadSettings$1$3", f = "SettingsViewModel.kt", l = {518}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass3 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ c1.e $OLD_FOCUS_BORDER_COLOR_KEY;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$loadSettings$1$3$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
            @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$loadSettings$1$3$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
            public static final class C01691 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
                final /* synthetic */ c1.e $OLD_FOCUS_BORDER_COLOR_KEY;
                /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01691(c1.e eVar, d7.d<? super C01691> dVar) {
                    super(2, dVar);
                    this.$OLD_FOCUS_BORDER_COLOR_KEY = eVar;
                }

                @Override // f7.a
                public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                    C01691 c01691 = new C01691(this.$OLD_FOCUS_BORDER_COLOR_KEY, dVar);
                    c01691.L$0 = obj;
                    return c01691;
                }

                @Override // r7.p
                public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                    return ((C01691) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    c1.b bVar = (c1.b) this.L$0;
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    String str = (String) bVar.f7368a.get(this.$OLD_FOCUS_BORDER_COLOR_KEY);
                    x6.t0 t0Var = x6.t0.f22605a;
                    if (str == null) {
                        return t0Var;
                    }
                    bVar.d(DeviceTypeKt.getACCENT_COLOR_KEY(), str);
                    bVar.c(this.$OLD_FOCUS_BORDER_COLOR_KEY);
                    return t0Var;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(SettingsViewModel settingsViewModel, c1.e eVar, d7.d<? super AnonymousClass3> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$OLD_FOCUS_BORDER_COLOR_KEY = eVar;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass3(this.this$0, this.$OLD_FOCUS_BORDER_COLOR_KEY, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(this.this$0.context);
                    C01691 c01691 = new C01691(this.$OLD_FOCUS_BORDER_COLOR_KEY, null);
                    this.label = 1;
                    Object objO = androidx.work.impl.t.o(settingsDataStore, c01691, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objO == aVar) {
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

        public C15001(d7.d<? super C15001> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C15001 c15001 = SettingsViewModel.this.new C15001(dVar);
            c15001.L$0 = obj;
            return c15001;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(79:72|73|75|(2:77|(2:80|81))|82|(1:84)(1:85)|86|(1:88)(1:89)|90|(1:95)(1:94)|96|(1:98)(1:99)|100|(1:102)(1:103)|104|(1:106)(1:107)|108|(1:110)(1:111)|112|(1:114)|115|(1:(1:118)(1:119))|(1:121)(1:122)|123|(1:128)(1:127)|129|(1:131)(1:132)|133|(1:135)(1:136)|137|(1:139)|140|(1:142)(1:143)|144|(1:146)|147|(1:149)|150|(1:152)(1:153)|154|(1:156)(1:157)|158|(38:162|(1:165)|(0)(1:168)|170|(1:172)|173|(1:175)(1:176)|177|903|178|(1:180)|181|(1:183)(12:184|899|185|186|187|188|889|189|190|885|191|(1:193))|207|(1:209)|210|(1:212)(1:213)|214|(1:216)(1:217)|218|(1:220)(1:221)|222|(1:224)(1:225)|226|(1:228)(1:229)|230|(1:232)(1:233)|234|845|235|(1:237)|240|243|(1:245)|246|(1:248)(1:249)|250|(1:909)(8:253|30|254|(1:256)(1:257)|(1:259)(1:261)|260|262|(1:910)(3:265|266|(1:911)(4:269|27|270|(1:912)(3:273|274|(1:913)(6:277|24|278|(1:(2:282|(1:914)(5:285|286|287|289|(2:291|907)(4:292|21|293|(1:915)(138:296|297|873|298|299|877|300|301|302|303|304|305|306|307|736|308|309|730|310|311|744|312|313|716|314|315|714|316|317|718|318|319|724|320|321|782|322|323|776|324|325|794|326|327|784|328|329|760|330|331|752|332|333|772|334|335|768|336|337|837|338|339|861|340|341|853|342|343|811|344|345|805|346|347|831|348|349|821|350|351|893|352|353|901|354|355|897|356|357|875|358|359|740|360|361|720|362|363|792|364|365|754|366|367|835|368|369|809|370|371|829|372|373|881|374|375|895|376|377|867|378|379|879|380|381|732|382|383|726|384|385|728|386|387|742|388|389|(1:916)(140:392|798|393|394|395|780|470|471|790|472|473|474|475|796|476|477|799|478|479|786|480|481|758|482|483|762|484|485|748|486|487|750|488|489|770|490|491|774|492|493|764|494|495|847|496|497|849|498|499|841|500|501|843|502|503|863|504|505|851|506|507|859|508|509|813|510|511|801|512|513|807|514|515|825|516|517|833|518|519|819|520|521|887|522|523|891|524|525|905|526|527|871|528|529|734|530|531|738|532|533|722|534|535|778|536|537|788|538|539|756|540|541|766|542|543|839|544|545|865|546|547|857|548|549|815|550|551|803|552|553|823|554|555|827|556|557|817|558|559|(1:908)(29:562|869|563|564|636|(1:638)(1:639)|(1:641)(1:642)|(1:644)(1:645)|(1:647)(1:648)|(1:650)(1:651)|(1:653)(1:654)|(1:656)(1:657)|(1:659)(1:660)|(1:662)(1:663)|(1:665)(1:666)|(1:668)(1:669)|(1:671)(1:672)|(1:674)(1:675)|(1:677)(1:678)|(1:680)(1:681)|(1:683)(1:684)|(1:686)(1:687)|(1:689)(1:690)|(1:692)(1:693)|(1:695)(1:696)|(1:698)(1:699)|(1:701)(1:702)|703|(1:705)))))))(4:288|287|289|(0)(0)))|706|707))))))|169|170|(0)|173|(0)(0)|177|903|178|(0)|181|(0)(0)|207|(0)|210|(0)(0)|214|(0)(0)|218|(0)(0)|222|(0)(0)|226|(0)(0)|230|(0)(0)|234|845|235|(0)|240|243|(0)|246|(0)(0)|250|(0)(0)) */
        /* JADX WARN: Code restructure failed: missing block: B:204:0x131b, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:205:0x131c, code lost:
        
            r34 = r3;
            r32 = r4;
            r35 = r7;
            r33 = r8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:238:0x13c8, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:242:0x13cf, code lost:
        
            r0 = k2.c.h(r0);
         */
        /* JADX WARN: Multi-variable search skipped. Vars limit reached: 6233 (expected less than 5000) */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:102:0x10ef  */
        /* JADX WARN: Removed duplicated region for block: B:103:0x10f6  */
        /* JADX WARN: Removed duplicated region for block: B:106:0x1106  */
        /* JADX WARN: Removed duplicated region for block: B:107:0x110d  */
        /* JADX WARN: Removed duplicated region for block: B:110:0x111d  */
        /* JADX WARN: Removed duplicated region for block: B:111:0x1124  */
        /* JADX WARN: Removed duplicated region for block: B:114:0x1134  */
        /* JADX WARN: Removed duplicated region for block: B:117:0x1153  */
        /* JADX WARN: Removed duplicated region for block: B:121:0x115c  */
        /* JADX WARN: Removed duplicated region for block: B:122:0x1179  */
        /* JADX WARN: Removed duplicated region for block: B:125:0x118f  */
        /* JADX WARN: Removed duplicated region for block: B:131:0x11b1  */
        /* JADX WARN: Removed duplicated region for block: B:132:0x11b7  */
        /* JADX WARN: Removed duplicated region for block: B:135:0x11c6  */
        /* JADX WARN: Removed duplicated region for block: B:136:0x11cc  */
        /* JADX WARN: Removed duplicated region for block: B:139:0x11db  */
        /* JADX WARN: Removed duplicated region for block: B:142:0x11ee  */
        /* JADX WARN: Removed duplicated region for block: B:143:0x11f1  */
        /* JADX WARN: Removed duplicated region for block: B:146:0x1200  */
        /* JADX WARN: Removed duplicated region for block: B:149:0x1213  */
        /* JADX WARN: Removed duplicated region for block: B:152:0x1226  */
        /* JADX WARN: Removed duplicated region for block: B:153:0x122d  */
        /* JADX WARN: Removed duplicated region for block: B:156:0x123d  */
        /* JADX WARN: Removed duplicated region for block: B:157:0x1244  */
        /* JADX WARN: Removed duplicated region for block: B:160:0x1254  */
        /* JADX WARN: Removed duplicated region for block: B:165:0x1265  */
        /* JADX WARN: Removed duplicated region for block: B:168:0x126a  */
        /* JADX WARN: Removed duplicated region for block: B:172:0x1291  */
        /* JADX WARN: Removed duplicated region for block: B:175:0x12b1  */
        /* JADX WARN: Removed duplicated region for block: B:176:0x12b8  */
        /* JADX WARN: Removed duplicated region for block: B:180:0x12ca  */
        /* JADX WARN: Removed duplicated region for block: B:183:0x12d2  */
        /* JADX WARN: Removed duplicated region for block: B:184:0x12df  */
        /* JADX WARN: Removed duplicated region for block: B:209:0x132f  */
        /* JADX WARN: Removed duplicated region for block: B:212:0x1342  */
        /* JADX WARN: Removed duplicated region for block: B:213:0x1347  */
        /* JADX WARN: Removed duplicated region for block: B:216:0x1357  */
        /* JADX WARN: Removed duplicated region for block: B:217:0x135d  */
        /* JADX WARN: Removed duplicated region for block: B:220:0x136d  */
        /* JADX WARN: Removed duplicated region for block: B:221:0x1373  */
        /* JADX WARN: Removed duplicated region for block: B:224:0x1383  */
        /* JADX WARN: Removed duplicated region for block: B:225:0x138a  */
        /* JADX WARN: Removed duplicated region for block: B:228:0x139a  */
        /* JADX WARN: Removed duplicated region for block: B:229:0x13a1  */
        /* JADX WARN: Removed duplicated region for block: B:232:0x13b3  */
        /* JADX WARN: Removed duplicated region for block: B:233:0x13b6  */
        /* JADX WARN: Removed duplicated region for block: B:237:0x13c5  */
        /* JADX WARN: Removed duplicated region for block: B:245:0x13db  */
        /* JADX WARN: Removed duplicated region for block: B:248:0x13ef  */
        /* JADX WARN: Removed duplicated region for block: B:249:0x13f4  */
        /* JADX WARN: Removed duplicated region for block: B:253:0x14c6  */
        /* JADX WARN: Removed duplicated region for block: B:256:0x1510  */
        /* JADX WARN: Removed duplicated region for block: B:257:0x1513  */
        /* JADX WARN: Removed duplicated region for block: B:259:0x1517  */
        /* JADX WARN: Removed duplicated region for block: B:261:0x151e  */
        /* JADX WARN: Removed duplicated region for block: B:265:0x161b  */
        /* JADX WARN: Removed duplicated region for block: B:269:0x1765  */
        /* JADX WARN: Removed duplicated region for block: B:273:0x18b7  */
        /* JADX WARN: Removed duplicated region for block: B:277:0x1a12  */
        /* JADX WARN: Removed duplicated region for block: B:281:0x1a75  */
        /* JADX WARN: Removed duplicated region for block: B:291:0x1d7d  */
        /* JADX WARN: Removed duplicated region for block: B:292:0x1d80  */
        /* JADX WARN: Removed duplicated region for block: B:296:0x1f38  */
        /* JADX WARN: Removed duplicated region for block: B:392:0x20f3  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0ef8  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0efa  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0f09  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x0f38  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0f47  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x0f4c  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x0f59  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0f5e  */
        /* JADX WARN: Removed duplicated region for block: B:562:0x25b1  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x0f6d  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x0f87  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x0f8c  */
        /* JADX WARN: Removed duplicated region for block: B:638:0x2878  */
        /* JADX WARN: Removed duplicated region for block: B:639:0x287b  */
        /* JADX WARN: Removed duplicated region for block: B:641:0x287f  */
        /* JADX WARN: Removed duplicated region for block: B:642:0x2882  */
        /* JADX WARN: Removed duplicated region for block: B:644:0x2886  */
        /* JADX WARN: Removed duplicated region for block: B:645:0x2889  */
        /* JADX WARN: Removed duplicated region for block: B:647:0x288d  */
        /* JADX WARN: Removed duplicated region for block: B:648:0x2890  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x0f9b  */
        /* JADX WARN: Removed duplicated region for block: B:650:0x2894  */
        /* JADX WARN: Removed duplicated region for block: B:651:0x2897  */
        /* JADX WARN: Removed duplicated region for block: B:653:0x289b  */
        /* JADX WARN: Removed duplicated region for block: B:654:0x289e  */
        /* JADX WARN: Removed duplicated region for block: B:656:0x28a2  */
        /* JADX WARN: Removed duplicated region for block: B:657:0x28a5  */
        /* JADX WARN: Removed duplicated region for block: B:659:0x28a9  */
        /* JADX WARN: Removed duplicated region for block: B:660:0x28ac  */
        /* JADX WARN: Removed duplicated region for block: B:662:0x28b0  */
        /* JADX WARN: Removed duplicated region for block: B:663:0x28b3  */
        /* JADX WARN: Removed duplicated region for block: B:665:0x28b7  */
        /* JADX WARN: Removed duplicated region for block: B:666:0x28ba  */
        /* JADX WARN: Removed duplicated region for block: B:668:0x28be  */
        /* JADX WARN: Removed duplicated region for block: B:669:0x28c1  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x0fa2  */
        /* JADX WARN: Removed duplicated region for block: B:671:0x28c5  */
        /* JADX WARN: Removed duplicated region for block: B:672:0x28c8  */
        /* JADX WARN: Removed duplicated region for block: B:674:0x28cc  */
        /* JADX WARN: Removed duplicated region for block: B:675:0x28cf  */
        /* JADX WARN: Removed duplicated region for block: B:677:0x28d3  */
        /* JADX WARN: Removed duplicated region for block: B:678:0x28d6  */
        /* JADX WARN: Removed duplicated region for block: B:680:0x28da  */
        /* JADX WARN: Removed duplicated region for block: B:681:0x28dd  */
        /* JADX WARN: Removed duplicated region for block: B:683:0x28e1  */
        /* JADX WARN: Removed duplicated region for block: B:684:0x28e4  */
        /* JADX WARN: Removed duplicated region for block: B:686:0x28e8  */
        /* JADX WARN: Removed duplicated region for block: B:687:0x28eb  */
        /* JADX WARN: Removed duplicated region for block: B:689:0x28ef  */
        /* JADX WARN: Removed duplicated region for block: B:690:0x28f2  */
        /* JADX WARN: Removed duplicated region for block: B:692:0x28f6  */
        /* JADX WARN: Removed duplicated region for block: B:693:0x28f9  */
        /* JADX WARN: Removed duplicated region for block: B:695:0x28fd  */
        /* JADX WARN: Removed duplicated region for block: B:696:0x2900  */
        /* JADX WARN: Removed duplicated region for block: B:698:0x2904  */
        /* JADX WARN: Removed duplicated region for block: B:699:0x2907  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x0fb0  */
        /* JADX WARN: Removed duplicated region for block: B:701:0x290b  */
        /* JADX WARN: Removed duplicated region for block: B:702:0x290e  */
        /* JADX WARN: Removed duplicated region for block: B:705:0x29c4  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x1005  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x1020  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x108b  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x1093  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x10a4  */
        /* JADX WARN: Removed duplicated region for block: B:89:0x10ab  */
        /* JADX WARN: Removed duplicated region for block: B:908:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:909:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:910:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:911:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:912:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:913:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:915:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:916:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:92:0x10bb  */
        /* JADX WARN: Removed duplicated region for block: B:98:0x10d8  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x10df  */
        /* JADX WARN: Type inference failed for: r0v1188 */
        /* JADX WARN: Type inference failed for: r0v192 */
        /* JADX WARN: Type inference failed for: r0v193, types: [com.arflix.tv.data.repository.AuthState$Authenticated] */
        /* JADX WARN: Type inference failed for: r0v212 */
        /* JADX WARN: Type inference failed for: r0v217 */
        /* JADX WARN: Type inference failed for: r19v55 */
        /* JADX WARN: Type inference failed for: r19v57 */
        /* JADX WARN: Type inference failed for: r19v58 */
        /* JADX WARN: Type inference failed for: r19v59 */
        /* JADX WARN: Type inference failed for: r19v60 */
        /* JADX WARN: Type inference failed for: r19v64 */
        /* JADX WARN: Type inference failed for: r19v67 */
        /* JADX WARN: Type inference failed for: r19v69 */
        /* JADX WARN: Type inference failed for: r19v72 */
        /* JADX WARN: Type inference failed for: r19v75 */
        /* JADX WARN: Type inference failed for: r19v82 */
        /* JADX WARN: Type inference failed for: r19v83 */
        /* JADX WARN: Type inference failed for: r19v84 */
        /* JADX WARN: Type inference failed for: r25v0 */
        /* JADX WARN: Type inference failed for: r25v1 */
        /* JADX WARN: Type inference failed for: r25v3 */
        /* JADX WARN: Type inference failed for: r25v95 */
        /* JADX WARN: Type inference failed for: r27v0 */
        /* JADX WARN: Type inference failed for: r27v1 */
        /* JADX WARN: Type inference failed for: r27v2 */
        /* JADX WARN: Type inference failed for: r27v3 */
        /* JADX WARN: Type inference failed for: r27v5 */
        /* JADX WARN: Type inference failed for: r27v89 */
        /* JADX WARN: Type inference failed for: r28v0 */
        /* JADX WARN: Type inference failed for: r28v1 */
        /* JADX WARN: Type inference failed for: r28v2 */
        /* JADX WARN: Type inference failed for: r28v3 */
        /* JADX WARN: Type inference failed for: r28v5 */
        /* JADX WARN: Type inference failed for: r28v86 */
        /* JADX WARN: Type inference failed for: r370v0 */
        /* JADX WARN: Type inference failed for: r370v1 */
        /* JADX WARN: Type inference failed for: r37v55 */
        /* JADX WARN: Type inference failed for: r37v57, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r37v59 */
        /* JADX WARN: Type inference failed for: r3v152 */
        /* JADX WARN: Type inference failed for: r3v154 */
        /* JADX WARN: Type inference failed for: r3v155 */
        /* JADX WARN: Type inference failed for: r3v28 */
        /* JADX WARN: Type inference failed for: r3v29, types: [int] */
        /* JADX WARN: Type inference failed for: r3v30 */
        /* JADX WARN: Type inference failed for: r3v36 */
        /* JADX WARN: Type inference failed for: r3v42 */
        /* JADX WARN: Type inference failed for: r3v62, types: [d7.d, d7.j] */
        /* JADX WARN: Type inference failed for: r3v66 */
        /* JADX WARN: Type inference failed for: r49v38 */
        /* JADX WARN: Type inference failed for: r49v40, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r49v43 */
        /* JADX WARN: Type inference failed for: r4v26 */
        /* JADX WARN: Type inference failed for: r4v27, types: [int] */
        /* JADX WARN: Type inference failed for: r4v7 */
        /* JADX WARN: Type inference failed for: r4v8, types: [int] */
        /* JADX WARN: Type inference failed for: r4v90 */
        /* JADX WARN: Type inference failed for: r4v91 */
        /* JADX WARN: Type inference failed for: r58v48 */
        /* JADX WARN: Type inference failed for: r58v50, types: [int] */
        /* JADX WARN: Type inference failed for: r58v52 */
        /* JADX WARN: Type inference failed for: r59v72 */
        /* JADX WARN: Type inference failed for: r59v74, types: [int] */
        /* JADX WARN: Type inference failed for: r59v76 */
        /* JADX WARN: Type inference failed for: r5v11 */
        /* JADX WARN: Type inference failed for: r5v12, types: [int] */
        /* JADX WARN: Type inference failed for: r5v14 */
        /* JADX WARN: Type inference failed for: r5v18 */
        /* JADX WARN: Type inference failed for: r5v74 */
        /* JADX WARN: Type inference failed for: r5v75 */
        /* JADX WARN: Type inference failed for: r64v3 */
        /* JADX WARN: Type inference failed for: r64v4, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r64v5 */
        /* JADX WARN: Type inference failed for: r76v0 */
        /* JADX WARN: Type inference failed for: r77v0 */
        /* JADX WARN: Type inference failed for: r79v0 */
        /* JADX WARN: Type inference failed for: r7v10 */
        /* JADX WARN: Type inference failed for: r7v12, types: [int] */
        /* JADX WARN: Type inference failed for: r7v13 */
        /* JADX WARN: Type inference failed for: r7v14 */
        /* JADX WARN: Type inference failed for: r7v16 */
        /* JADX WARN: Type inference failed for: r7v27 */
        /* JADX WARN: Type inference failed for: r7v6 */
        /* JADX WARN: Type inference failed for: r7v79 */
        /* JADX WARN: Type inference failed for: r7v80 */
        /* JADX WARN: Type inference failed for: r7v81 */
        /* JADX WARN: Type inference failed for: r7v82 */
        /* JADX WARN: Type inference failed for: r7v83 */
        /* JADX WARN: Type inference failed for: r7v9 */
        /* JADX WARN: Type inference failed for: r86v0 */
        /* JADX WARN: Type inference failed for: r86v1 */
        /* JADX WARN: Type inference failed for: r86v2 */
        /* JADX WARN: Type inference failed for: r8v1 */
        /* JADX WARN: Type inference failed for: r8v16 */
        /* JADX WARN: Type inference failed for: r8v17, types: [int] */
        /* JADX WARN: Type inference failed for: r8v2, types: [int] */
        /* JADX WARN: Type inference failed for: r8v25 */
        /* JADX WARN: Type inference failed for: r8v4 */
        /* JADX WARN: Type inference failed for: r8v7 */
        /* JADX WARN: Type inference failed for: r8v74 */
        /* JADX WARN: Type inference failed for: r8v75 */
        /* JADX WARN: Type inference failed for: r8v76 */
        /* JADX WARN: Type inference failed for: r8v77 */
        /* JADX WARN: Type inference failed for: r8v78 */
        /* JADX WARN: Type inference failed for: r91v0 */
        /* JADX WARN: Type inference failed for: r91v1 */
        /* JADX WARN: Type inference failed for: r91v2 */
        /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r372) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 10734
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15001.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15001) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$loadSubtitleOptions$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel", f = "SettingsViewModel.kt", l = {AnalyticsListener.EVENT_DRM_KEYS_RESTORED}, m = "loadSubtitleOptions", v = 2)
    public static final class C15011 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C15011(d7.d<? super C15011> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SettingsViewModel.this.loadSubtitleOptions(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$logout$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$logout$1", f = "SettingsViewModel.kt", l = {3903}, m = "invokeSuspend", v = 2)
    public static final class C15021 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C15021(d7.d<? super C15021> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15021(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SettingsViewModel.this.cancelCloudAuth();
                SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, "Signing out...", ToastType.INFO, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
                AuthRepository authRepository = SettingsViewModel.this.authRepository;
                this.label = 1;
                Object objSignOut = authRepository.signOut(this);
                e7.a aVar = e7.a.f15033i;
                if (objSignOut == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, "Signed out", ToastType.SUCCESS, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15021) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$moveAddon$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$moveAddon$1", f = "SettingsViewModel.kt", l = {1818, 1820, 1823, 1825}, m = "invokeSuspend", v = 2)
    public static final class C15031 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $addonId;
        final /* synthetic */ boolean $moveUp;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;
        final /* synthetic */ SettingsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15031(boolean z, SettingsViewModel settingsViewModel, String str, d7.d<? super C15031> dVar) {
            super(2, dVar);
            this.$moveUp = z;
            this.this$0 = settingsViewModel;
            this.$addonId = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C15031 c15031 = new C15031(this.$moveUp, this.this$0, this.$addonId, dVar);
            c15031.L$0 = obj;
            return c15031;
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x005f, code lost:
        
            if (r2 == r10) goto L34;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x0079, code lost:
        
            if (r2 == r10) goto L34;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x00b4, code lost:
        
            if (r2 != r10) goto L35;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r107v0, types: [java.util.List] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r143) {
            /*
                Method dump skipped, instruction units count: 478
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15031.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15031) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$moveCatalogDown$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$moveCatalogDown$1", f = "SettingsViewModel.kt", l = {2268}, m = "invokeSuspend", v = 2)
    public static final class C15041 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $catalogId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15041(String str, d7.d<? super C15041> dVar) {
            super(2, dVar);
            this.$catalogId = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15041(this.$catalogId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                CatalogRepository catalogRepository = SettingsViewModel.this.catalogRepository;
                String str = this.$catalogId;
                this.label = 1;
                Object objMoveCatalogDown = catalogRepository.moveCatalogDown(str, this);
                e7.a aVar = e7.a.f15033i;
                if (objMoveCatalogDown == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15041) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$moveCatalogUp$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$moveCatalogUp$1", f = "SettingsViewModel.kt", l = {2261}, m = "invokeSuspend", v = 2)
    public static final class C15051 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $catalogId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15051(String str, d7.d<? super C15051> dVar) {
            super(2, dVar);
            this.$catalogId = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15051(this.$catalogId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                CatalogRepository catalogRepository = SettingsViewModel.this.catalogRepository;
                String str = this.$catalogId;
                this.label = 1;
                Object objMoveCatalogUp = catalogRepository.moveCatalogUp(str, this);
                e7.a aVar = e7.a.f15033i;
                if (objMoveCatalogUp == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15051) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$moveIptvGroupDown$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$moveIptvGroupDown$1", f = "SettingsViewModel.kt", l = {922}, m = "invokeSuspend", v = 2)
    public static final class C15061 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $groupName;
        final /* synthetic */ String $playlistId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15061(String str, String str2, d7.d<? super C15061> dVar) {
            super(2, dVar);
            this.$playlistId = str;
            this.$groupName = str2;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15061(this.$playlistId, this.$groupName, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                IptvRepository iptvRepository = SettingsViewModel.this.iptvRepository;
                String str = this.$playlistId;
                String str2 = this.$groupName;
                List<String> iptvAvailableGroups = ((SettingsUiState) SettingsViewModel.this._uiState.getValue()).getIptvAvailableGroups();
                this.label = 1;
                Object objMoveGroupDown = iptvRepository.moveGroupDown(str, str2, iptvAvailableGroups, this);
                e7.a aVar = e7.a.f15033i;
                if (objMoveGroupDown == aVar) {
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
            return ((C15061) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$moveIptvGroupToTop$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$moveIptvGroupToTop$1", f = "SettingsViewModel.kt", l = {928}, m = "invokeSuspend", v = 2)
    public static final class C15071 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $groupName;
        final /* synthetic */ String $playlistId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15071(String str, String str2, d7.d<? super C15071> dVar) {
            super(2, dVar);
            this.$playlistId = str;
            this.$groupName = str2;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15071(this.$playlistId, this.$groupName, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                IptvRepository iptvRepository = SettingsViewModel.this.iptvRepository;
                String str = this.$playlistId;
                String str2 = this.$groupName;
                List<String> iptvAvailableGroups = ((SettingsUiState) SettingsViewModel.this._uiState.getValue()).getIptvAvailableGroups();
                this.label = 1;
                Object objMoveGroupToTop = iptvRepository.moveGroupToTop(str, str2, iptvAvailableGroups, this);
                e7.a aVar = e7.a.f15033i;
                if (objMoveGroupToTop == aVar) {
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
            return ((C15071) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$moveIptvGroupUp$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$moveIptvGroupUp$1", f = "SettingsViewModel.kt", l = {916}, m = "invokeSuspend", v = 2)
    public static final class C15081 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $groupName;
        final /* synthetic */ String $playlistId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15081(String str, String str2, d7.d<? super C15081> dVar) {
            super(2, dVar);
            this.$playlistId = str;
            this.$groupName = str2;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15081(this.$playlistId, this.$groupName, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                IptvRepository iptvRepository = SettingsViewModel.this.iptvRepository;
                String str = this.$playlistId;
                String str2 = this.$groupName;
                List<String> iptvAvailableGroups = ((SettingsUiState) SettingsViewModel.this._uiState.getValue()).getIptvAvailableGroups();
                this.label = 1;
                Object objMoveGroupUp = iptvRepository.moveGroupUp(str, str2, iptvAvailableGroups, this);
                e7.a aVar = e7.a.f15033i;
                if (objMoveGroupUp == aVar) {
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
            return ((C15081) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$observeAddons$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$observeAddons$1", f = "SettingsViewModel.kt", l = {788}, m = "invokeSuspend", v = 2)
    public static final class C15091 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$observeAddons$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final class C01701<T> implements na.k {
            final /* synthetic */ ka.k0 $$this$launch;
            final /* synthetic */ SettingsViewModel this$0;

            public C01701(ka.k0 k0Var, SettingsViewModel settingsViewModel) {
                this.$$this$launch = k0Var;
                this.this$0 = settingsViewModel;
            }

            @Override // na.k
            public /* bridge */ /* synthetic */ Object emit(Object obj, d7.d dVar) {
                return emit((List<Addon>) obj, (d7.d<? super x6.t0>) dVar);
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(java.util.List<com.arflix.tv.data.model.Addon> r136, d7.d<? super x6.t0> r137) {
                /*
                    Method dump skipped, instruction units count: 577
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15091.C01701.emit(java.util.List, d7.d):java.lang.Object");
            }
        }

        public C15091(d7.d<? super C15091> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C15091 c15091 = SettingsViewModel.this.new C15091(dVar);
            c15091.L$0 = obj;
            return c15091;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ka.k0 k0Var = (ka.k0) this.L$0;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j<List<Addon>> installedAddons = SettingsViewModel.this.streamRepository.getInstalledAddons();
                C01701 c01701 = new C01701(k0Var, SettingsViewModel.this);
                this.L$0 = null;
                this.label = 1;
                Object objCollect = installedAddons.collect(c01701, this);
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
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15091) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$observeAuthState$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$observeAuthState$1", f = "SettingsViewModel.kt", l = {1907}, m = "invokeSuspend", v = 2)
    public static final class C15101 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C15101(d7.d<? super C15101> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15101(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.h1<AuthState> authState = SettingsViewModel.this.authRepository.getAuthState();
                C01711 c01711 = new C01711(SettingsViewModel.this);
                this.label = 1;
                Object objCollect = authState.collect(c01711, this);
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
            throw new KotlinNothingValueException();
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15101) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$observeAuthState$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final class C01711<T> implements na.k {
            final /* synthetic */ SettingsViewModel this$0;

            public C01711(SettingsViewModel settingsViewModel) {
                this.this$0 = settingsViewModel;
            }

            /* JADX WARN: Code restructure failed: missing block: B:43:0x01ea, code lost:
            
                if (r2 == r9) goto L44;
             */
            /* JADX WARN: Removed duplicated region for block: B:50:0x0202  */
            /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(com.arflix.tv.data.repository.AuthState r143, d7.d<? super x6.t0> r144) {
                /*
                    Method dump skipped, instruction units count: 807
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15101.C01711.emit(com.arflix.tv.data.repository.AuthState, d7.d):java.lang.Object");
            }

            @Override // na.k
            public /* bridge */ /* synthetic */ Object emit(Object obj, d7.d dVar) {
                return emit((AuthState) obj, (d7.d<? super x6.t0>) dVar);
            }
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$observeCatalogs$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$observeCatalogs$1", f = "SettingsViewModel.kt", l = {2003}, m = "invokeSuspend", v = 2)
    public static final class C15111 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$observeCatalogs$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final class C01721<T> implements na.k {
            final /* synthetic */ SettingsViewModel this$0;

            public C01721(SettingsViewModel settingsViewModel) {
                this.this$0 = settingsViewModel;
            }

            @Override // na.k
            public /* bridge */ /* synthetic */ Object emit(Object obj, d7.d dVar) {
                return emit((List<CatalogConfig>) obj, (d7.d<? super x6.t0>) dVar);
            }

            /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(java.util.List<com.arflix.tv.data.model.CatalogConfig> r136, d7.d<? super x6.t0> r137) {
                /*
                    Method dump skipped, instruction units count: 396
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15111.C01721.emit(java.util.List, d7.d):java.lang.Object");
            }
        }

        public C15111(d7.d<? super C15111> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15111(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j<List<CatalogConfig>> jVarObserveCatalogs = SettingsViewModel.this.catalogRepository.observeCatalogs();
                C01721 c01721 = new C01721(SettingsViewModel.this);
                this.label = 1;
                Object objCollect = jVarObserveCatalogs.collect(c01721, this);
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
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15111) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$observeHomeServer$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$observeHomeServer$1", f = "SettingsViewModel.kt", l = {811}, m = "invokeSuspend", v = 2)
    public static final class C15121 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C15121(d7.d<? super C15121> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15121(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j<List<HomeServerConnection>> connections = SettingsViewModel.this.homeServerRepository.getConnections();
                final SettingsViewModel settingsViewModel = SettingsViewModel.this;
                na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.settings.SettingsViewModel.observeHomeServer.1.1
                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                        return emit((List<HomeServerConnection>) obj2, (d7.d<? super x6.t0>) dVar);
                    }

                    public final Object emit(List<HomeServerConnection> list, d7.d<? super x6.t0> dVar) {
                        settingsViewModel._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, (HomeServerConnection) kotlin.collections.x.o0(list), list, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1073741799, null));
                        return x6.t0.f22605a;
                    }
                };
                this.label = 1;
                Object objCollect = connections.collect(kVar, this);
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
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15121) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$observeIptvConfig$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$observeIptvConfig$1", f = "SettingsViewModel.kt", l = {1941}, m = "invokeSuspend", v = 2)
    public static final class C15131 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C15131(d7.d<? super C15131> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15131(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j<IptvConfig> jVarObserveConfig = SettingsViewModel.this.iptvRepository.observeConfig();
                final SettingsViewModel settingsViewModel = SettingsViewModel.this;
                na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.settings.SettingsViewModel.observeIptvConfig.1.1
                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                        return emit((IptvConfig) obj2, (d7.d<? super x6.t0>) dVar);
                    }

                    /* JADX WARN: Removed duplicated region for block: B:35:0x01e6  */
                    /* JADX WARN: Removed duplicated region for block: B:37:0x02fd  */
                    /* JADX WARN: Removed duplicated region for block: B:75:0x03b2  */
                    /* JADX WARN: Removed duplicated region for block: B:76:0x03b4  */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final java.lang.Object emit(com.arflix.tv.data.repository.IptvConfig r137, d7.d<? super x6.t0> r138) {
                        /*
                            Method dump skipped, instruction units count: 1244
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15131.C01741.emit(com.arflix.tv.data.repository.IptvConfig, d7.d):java.lang.Object");
                    }
                };
                this.label = 1;
                Object objCollect = jVarObserveConfig.collect(kVar, this);
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
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15131) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$observeIptvGroupPrefs$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$observeIptvGroupPrefs$1", f = "SettingsViewModel.kt", l = {433}, m = "invokeSuspend", v = 2)
    public static final class C15141 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$observeIptvGroupPrefs$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00000\u00042\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"", "", "hidden", "order", "Lx6/x;", "<anonymous>", "(Ljava/util/List;Ljava/util/List;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$observeIptvGroupPrefs$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01751 extends f7.j implements r7.q<List<? extends String>, List<? extends String>, d7.d<? super x6.x>, Object> {
            /* synthetic */ Object L$0;
            /* synthetic */ Object L$1;
            int label;

            public C01751(d7.d<? super C01751> dVar) {
                super(3, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                List list = (List) this.L$0;
                List list2 = (List) this.L$1;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return new x6.x(list, list2);
            }

            @Override // r7.q
            public final Object invoke(List<String> list, List<String> list2, d7.d<? super x6.x> dVar) {
                C01751 c01751 = new C01751(dVar);
                c01751.L$0 = list;
                c01751.L$1 = list2;
                return c01751.invokeSuspend(x6.t0.f22605a);
            }
        }

        public C15141(d7.d<? super C15141> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15141(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.n0 n0Var = new na.n0(SettingsViewModel.this.iptvRepository.observeHiddenGroups(), SettingsViewModel.this.iptvRepository.observeGroupOrder(), new C01751(null));
                final SettingsViewModel settingsViewModel = SettingsViewModel.this;
                na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.settings.SettingsViewModel.observeIptvGroupPrefs.1.2
                    public final Object emit(x6.x xVar, d7.d<? super x6.t0> dVar) {
                        settingsViewModel._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, (List) xVar.f22608i, (List) xVar.f22609l, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -393217, 1073741823, null));
                        return x6.t0.f22605a;
                    }

                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                        return emit((x6.x) obj2, (d7.d<? super x6.t0>) dVar);
                    }
                };
                this.label = 1;
                Object objCollect = n0Var.collect(kVar, this);
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
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15141) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$observeProfileChanges$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$observeProfileChanges$1", f = "SettingsViewModel.kt", l = {758}, m = "invokeSuspend", v = 2)
    public static final class C15151 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C15151(d7.d<? super C15151> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15151(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j<String> activeProfileId = SettingsViewModel.this.profileManager.getActiveProfileId();
                final SettingsViewModel settingsViewModel = SettingsViewModel.this;
                na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.settings.SettingsViewModel.observeProfileChanges.1.1
                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                        return emit((String) obj2, (d7.d<? super x6.t0>) dVar);
                    }

                    public final Object emit(String str, d7.d<? super x6.t0> dVar) {
                        boolean zA = kotlin.jvm.internal.p.a(settingsViewModel.observedProfileId, str);
                        x6.t0 t0Var = x6.t0.f22605a;
                        if (zA) {
                            return t0Var;
                        }
                        settingsViewModel.observedProfileId = str;
                        settingsViewModel.hasObservedIptvConfig = false;
                        settingsViewModel.lastObservedIptvConfigSignature = null;
                        settingsViewModel.loadSettings();
                        return t0Var;
                    }
                };
                this.label = 1;
                Object objCollect = activeProfileId.collect(kVar, this);
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
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15151) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$observeSyncState$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$observeSyncState$1", f = "SettingsViewModel.kt", l = {823}, m = "invokeSuspend", v = 2)
    public static final class C15161 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C15161(d7.d<? super C15161> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15161(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.h1<SyncProgress> syncProgress = SettingsViewModel.this.traktSyncService.getSyncProgress();
                final SettingsViewModel settingsViewModel = SettingsViewModel.this;
                na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.settings.SettingsViewModel.observeSyncState.1.1
                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                        return emit((SyncProgress) obj2, (d7.d<? super x6.t0>) dVar);
                    }

                    public final Object emit(SyncProgress syncProgress2, d7.d<? super x6.t0> dVar) {
                        if (!kotlin.jvm.internal.p.a(((SettingsUiState) settingsViewModel._uiState.getValue()).getSyncProgress(), syncProgress2)) {
                            settingsViewModel._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, syncProgress2, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1073741825, -1, 1073741823, null));
                        }
                        return x6.t0.f22605a;
                    }
                };
                this.label = 1;
                Object objCollect = syncProgress.collect(kVar, this);
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
            throw new KotlinNothingValueException();
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15161) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$observeSyncState$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$observeSyncState$2", f = "SettingsViewModel.kt", l = {832}, m = "invokeSuspend", v = 2)
    public static final class C15172 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C15172(d7.d<? super C15172> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15172(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.h1<Boolean> h1VarIsSyncing = SettingsViewModel.this.traktSyncService.isSyncing();
                final SettingsViewModel settingsViewModel = SettingsViewModel.this;
                na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.settings.SettingsViewModel.observeSyncState.2.1
                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                        return emit(((Boolean) obj2).booleanValue(), (d7.d<? super x6.t0>) dVar);
                    }

                    public final Object emit(boolean z, d7.d<? super x6.t0> dVar) {
                        if (((SettingsUiState) settingsViewModel._uiState.getValue()).isSyncing() != z) {
                            settingsViewModel._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, z, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -536870913, -1, 1073741823, null));
                        }
                        return x6.t0.f22605a;
                    }
                };
                this.label = 1;
                Object objCollect = h1VarIsSyncing.collect(kVar, this);
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
            throw new KotlinNothingValueException();
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15172) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$observeTorrServer$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$observeTorrServer$1", f = "SettingsViewModel.kt", l = {801}, m = "invokeSuspend", v = 2)
    public static final class C15181 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C15181(d7.d<? super C15181> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15181(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                na.j<String> jVarObserveTorrServerBaseUrl = SettingsViewModel.this.streamRepository.observeTorrServerBaseUrl();
                final SettingsViewModel settingsViewModel = SettingsViewModel.this;
                na.k kVar = new na.k() { // from class: com.arflix.tv.ui.screens.settings.SettingsViewModel.observeTorrServer.1.1
                    @Override // na.k
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                        return emit((String) obj2, (d7.d<? super x6.t0>) dVar);
                    }

                    public final Object emit(String str, d7.d<? super x6.t0> dVar) {
                        if (!kotlin.jvm.internal.p.a(((SettingsUiState) settingsViewModel._uiState.getValue()).getTorrServerBaseUrl(), str)) {
                            settingsViewModel._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, str, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1073741819, null));
                        }
                        return x6.t0.f22605a;
                    }
                };
                this.label = 1;
                Object objCollect = jVarObserveTorrServerBaseUrl.collect(kVar, this);
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
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15181) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$openCloudEmailPasswordDialog$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$openCloudEmailPasswordDialog$1", f = "SettingsViewModel.kt", l = {2541}, m = "invokeSuspend", v = 2)
    public static final class C15191 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C15191(d7.d<? super C15191> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15191(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object objM6324ensureCloudAuthSessiongIAlus;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, true, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, Integer.MAX_VALUE, -13, -1, 1073741823, null));
                SettingsViewModel settingsViewModel = SettingsViewModel.this;
                this.label = 1;
                objM6324ensureCloudAuthSessiongIAlus = settingsViewModel.m6324ensureCloudAuthSessiongIAlus(false, this);
                e7.a aVar = e7.a.f15033i;
                if (objM6324ensureCloudAuthSessiongIAlus == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objM6324ensureCloudAuthSessiongIAlus = ((x6.d0) obj).f22580i;
            }
            SettingsViewModel settingsViewModel2 = SettingsViewModel.this;
            if (!(objM6324ensureCloudAuthSessiongIAlus instanceof x6.c0)) {
                settingsViewModel2._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel2._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, true, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, Integer.MAX_VALUE, -13, -1, 1073741823, null));
            }
            SettingsViewModel settingsViewModel3 = SettingsViewModel.this;
            Throwable thA = x6.d0.a(objM6324ensureCloudAuthSessiongIAlus);
            if (thA != null) {
                SettingsViewModel.clearCloudAuthSession$default(settingsViewModel3, false, 1, null);
                na.q0 q0Var = settingsViewModel3._uiState;
                SettingsUiState settingsUiState = (SettingsUiState) settingsViewModel3._uiState.getValue();
                String message = thA.getMessage();
                if (message == null) {
                    message = settingsViewModel3.context.getString(R.string.cloud_signin_failed_start);
                }
                q0Var.setValue(SettingsUiState.copy$default(settingsUiState, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, message, ToastType.ERROR, false, false, false, false, false, null, null, false, null, false, -1, -13, -1, 1072955391, null));
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15191) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$performFullSync$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$performFullSync$1", f = "SettingsViewModel.kt", l = {937, 949}, m = "invokeSuspend", v = 2)
    public static final class C15201 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $silent;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15201(boolean z, d7.d<? super C15201> dVar) {
            super(2, dVar);
            this.$silent = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15201(this.$silent, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object objPerformFullSync;
            int i10 = this.label;
            x6.t0 t0Var = x6.t0.f22605a;
            e7.a aVar = e7.a.f15033i;
            if (i10 == 0) {
                k2.c.G(obj);
                if (((SettingsUiState) SettingsViewModel.this._uiState.getValue()).isSyncing()) {
                    return t0Var;
                }
                TraktSyncService traktSyncService = SettingsViewModel.this.traktSyncService;
                this.label = 1;
                objPerformFullSync = traktSyncService.performFullSync(this);
                if (objPerformFullSync != aVar) {
                }
                return aVar;
            }
            if (i10 != 1) {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                return t0Var;
            }
            k2.c.G(obj);
            objPerformFullSync = obj;
            SyncResult syncResult = (SyncResult) objPerformFullSync;
            if (syncResult instanceof SyncResult.Success) {
                SyncResult.Success success = (SyncResult.Success) syncResult;
                SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, SettingsViewModel.this.formatSyncTime(Instant.now().toString()), success.getMoviesSynced(), success.getEpisodesSynced(), null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, androidx.compose.foundation.c.s("Synced ", success.getMoviesSynced(), " movies and ", success.getEpisodesSynced(), " episodes"), ToastType.SUCCESS, false, false, false, false, false, null, null, false, null, false, -1, Integer.MAX_VALUE, -4, 1072955391, null));
                SettingsViewModel.this.traktRepository.invalidateWatchedCache();
                TraktRepository traktRepository = SettingsViewModel.this.traktRepository;
                this.L$0 = null;
                this.label = 2;
                if (traktRepository.initializeWatchedCache(this) == aVar) {
                    return aVar;
                }
            } else {
                if (!(syncResult instanceof SyncResult.Error)) {
                    throw new NoWhenBranchMatchedException();
                }
                if (!this.$silent) {
                    SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, SettingsViewModel.this.context.getString(R.string.sync_failed, ((SyncResult.Error) syncResult).getMessage()), ToastType.ERROR, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
                }
            }
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15201) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$performIncrementalSync$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$performIncrementalSync$1", f = "SettingsViewModel.kt", l = {965, 980}, m = "invokeSuspend", v = 2)
    public static final class C15211 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        Object L$0;
        int label;

        public C15211(d7.d<? super C15211> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15211(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x01c7, code lost:
        
            if (r1.initializeWatchedCache(r138) == r4) goto L24;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r139) {
            /*
                Method dump skipped, instruction units count: 771
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15211.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15211) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$pollSimklAuth$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$pollSimklAuth$1", f = "SettingsViewModel.kt", l = {3807, 3809, 3810, 3811, 3835}, m = "invokeSuspend", v = 2)
    public static final class C15221 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $userCode;
        int I$0;
        int I$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        boolean Z$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15221(String str, d7.d<? super C15221> dVar) {
            super(2, dVar);
            this.$userCode = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C15221 c15221 = SettingsViewModel.this.new C15221(this.$userCode, dVar);
            c15221.L$0 = obj;
            return c15221;
        }

        /* JADX WARN: Code restructure failed: missing block: B:46:0x0267, code lost:
        
            if (r4.refreshForCurrentProfile(r144) != r10) goto L48;
         */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00e1  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00e3  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x010b  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x010d  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r145) {
            /*
                Method dump skipped, instruction units count: 621
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15221.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15221) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$reconnectTrakt$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$reconnectTrakt$1", f = "SettingsViewModel.kt", l = {3480}, m = "invokeSuspend", v = 2)
    public static final class C15231 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C15231(d7.d<? super C15231> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15231(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SettingsViewModel.this.cancelTraktAuth();
                TraktRepository traktRepository = SettingsViewModel.this.traktRepository;
                this.label = 1;
                Object objLogout = traktRepository.logout(this);
                e7.a aVar = e7.a.f15033i;
                if (objLogout == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -25089, -1, 1073741823, null));
            SettingsViewModel.this.startTraktAuth();
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15231) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshAddons$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshAddons$1", f = "SettingsViewModel.kt", l = {1867, 1868, 1881, 1882, 1884}, m = "invokeSuspend", v = 2)
    public static final class C15241 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        public C15241(d7.d<? super C15241> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C15241 c15241 = SettingsViewModel.this.new C15241(dVar);
            c15241.L$0 = obj;
            return c15241;
        }

        /* JADX WARN: Code restructure failed: missing block: B:43:0x01e6, code lost:
        
            if (r4 != r11) goto L45;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:33:0x008d A[Catch: Exception -> 0x0046, TryCatch #0 {Exception -> 0x0046, blocks: (B:47:0x01f0, B:49:0x022a, B:52:0x0232, B:51:0x022f, B:15:0x003e, B:41:0x01ce, B:18:0x0049, B:38:0x01b5, B:19:0x0050, B:31:0x0087, B:33:0x008d, B:35:0x01a4, B:20:0x0056, B:26:0x0071, B:28:0x0079, B:23:0x005f), top: B:60:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x01b4  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x01b5 A[Catch: Exception -> 0x0046, PHI: r2
          0x01b5: PHI (r2v30 java.lang.Object) = (r2v23 java.lang.Object), (r2v36 java.lang.Object) binds: [B:36:0x01b2, B:18:0x0049] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {Exception -> 0x0046, blocks: (B:47:0x01f0, B:49:0x022a, B:52:0x0232, B:51:0x022f, B:15:0x003e, B:41:0x01ce, B:18:0x0049, B:38:0x01b5, B:19:0x0050, B:31:0x0087, B:33:0x008d, B:35:0x01a4, B:20:0x0056, B:26:0x0071, B:28:0x0079, B:23:0x005f), top: B:60:0x0013 }] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x01cd  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x01ce A[Catch: Exception -> 0x0046, PHI: r0 r2
          0x01ce: PHI (r0v16 java.lang.Object) = (r0v15 java.lang.Object), (r0v21 java.lang.Object) binds: [B:39:0x01cb, B:15:0x003e] A[DONT_GENERATE, DONT_INLINE]
          0x01ce: PHI (r2v32 com.arflix.tv.data.repository.AddonRefreshReport) = (r2v43 com.arflix.tv.data.repository.AddonRefreshReport), (r2v37 com.arflix.tv.data.repository.AddonRefreshReport) binds: [B:39:0x01cb, B:15:0x003e] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #0 {Exception -> 0x0046, blocks: (B:47:0x01f0, B:49:0x022a, B:52:0x0232, B:51:0x022f, B:15:0x003e, B:41:0x01ce, B:18:0x0049, B:38:0x01b5, B:19:0x0050, B:31:0x0087, B:33:0x008d, B:35:0x01a4, B:20:0x0056, B:26:0x0071, B:28:0x0079, B:23:0x005f), top: B:60:0x0013 }] */
        /* JADX WARN: Type inference failed for: r108v1, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r2v0, types: [int] */
        /* JADX WARN: Type inference failed for: r2v1 */
        /* JADX WARN: Type inference failed for: r2v33 */
        /* JADX WARN: Type inference failed for: r2v41 */
        /* JADX WARN: Type inference failed for: r2v42 */
        /* JADX WARN: Type inference failed for: r2v7, types: [com.arflix.tv.data.repository.AddonRefreshReport] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r144) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 1113
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15241.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15241) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshAudioLanguageOptions$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshAudioLanguageOptions$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C15251 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C15251(d7.d<? super C15251> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15251(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            SettingsViewModel settingsViewModel = SettingsViewModel.this;
            List listLoadAudioLanguageOptions = settingsViewModel.loadAudioLanguageOptions(((SettingsUiState) settingsViewModel._uiState.getValue()).getDefaultAudioLanguage());
            if (!kotlin.jvm.internal.p.a(((SettingsUiState) SettingsViewModel.this._uiState.getValue()).getAudioLanguageOptions(), listLoadAudioLanguageOptions)) {
                SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, listLoadAudioLanguageOptions, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -9, -1, -1, 1073741823, null));
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15251) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshIntegrationUsernames$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshIntegrationUsernames$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C15261 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $isMdbListConnected;
        final /* synthetic */ boolean $isSimklConnected;
        final /* synthetic */ boolean $isTraktConnected;
        final /* synthetic */ String $profileId;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SettingsViewModel this$0;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshIntegrationUsernames$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshIntegrationUsernames$1$1", f = "SettingsViewModel.kt", l = {686}, m = "invokeSuspend", v = 2)
        public static final class C01791 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $profileId;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01791(SettingsViewModel settingsViewModel, String str, d7.d<? super C01791> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$profileId = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                return new C01791(this.this$0, this.$profileId, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                String str;
                Object objA;
                int i10 = this.label;
                try {
                    if (i10 == 0) {
                        k2.c.G(obj);
                        SettingsViewModel$refreshIntegrationUsernames$1$1$username$1 settingsViewModel$refreshIntegrationUsernames$1$1$username$1 = new SettingsViewModel$refreshIntegrationUsernames$1$1$username$1(this.this$0, null);
                        this.label = 1;
                        objA = ka.m0.A(5000L, settingsViewModel$refreshIntegrationUsernames$1$1$username$1, this);
                        e7.a aVar = e7.a.f15033i;
                        if (objA == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                        objA = obj;
                    }
                    str = (String) objA;
                } catch (CancellationException e5) {
                    throw e5;
                } catch (Exception unused) {
                    str = null;
                }
                if (kotlin.jvm.internal.p.a(this.this$0.profileManager.getProfileIdSync(), this.$profileId) && ((SettingsUiState) this.this$0._uiState.getValue()).isTraktAuthenticated()) {
                    this.this$0._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) this.this$0._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, str, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -16385, -1, 1073741823, null));
                }
                return x6.t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((C01791) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshIntegrationUsernames$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshIntegrationUsernames$1$2", f = "SettingsViewModel.kt", l = {704}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass2 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $profileId;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(SettingsViewModel settingsViewModel, String str, d7.d<? super AnonymousClass2> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$profileId = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass2(this.this$0, this.$profileId, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                String str;
                Object objA;
                int i10 = this.label;
                try {
                    if (i10 == 0) {
                        k2.c.G(obj);
                        SettingsViewModel$refreshIntegrationUsernames$1$2$username$1 settingsViewModel$refreshIntegrationUsernames$1$2$username$1 = new SettingsViewModel$refreshIntegrationUsernames$1$2$username$1(this.this$0, null);
                        this.label = 1;
                        objA = ka.m0.A(5000L, settingsViewModel$refreshIntegrationUsernames$1$2$username$1, this);
                        e7.a aVar = e7.a.f15033i;
                        if (objA == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                        objA = obj;
                    }
                    str = (String) objA;
                } catch (CancellationException e5) {
                    throw e5;
                } catch (Exception unused) {
                    str = null;
                }
                if (kotlin.jvm.internal.p.a(this.this$0.profileManager.getProfileIdSync(), this.$profileId) && ((SettingsUiState) this.this$0._uiState.getValue()).isMdbListConnected()) {
                    this.this$0._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) this.this$0._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, str, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -131073, -1, 1073741823, null));
                }
                return x6.t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass2) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshIntegrationUsernames$1$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshIntegrationUsernames$1$3", f = "SettingsViewModel.kt", l = {722}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass3 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $profileId;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(SettingsViewModel settingsViewModel, String str, d7.d<? super AnonymousClass3> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$profileId = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                return new AnonymousClass3(this.this$0, this.$profileId, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) throws Throwable {
                String str;
                Object objA;
                int i10 = this.label;
                try {
                    if (i10 == 0) {
                        k2.c.G(obj);
                        SettingsViewModel$refreshIntegrationUsernames$1$3$username$1 settingsViewModel$refreshIntegrationUsernames$1$3$username$1 = new SettingsViewModel$refreshIntegrationUsernames$1$3$username$1(this.this$0, null);
                        this.label = 1;
                        objA = ka.m0.A(5000L, settingsViewModel$refreshIntegrationUsernames$1$3$username$1, this);
                        e7.a aVar = e7.a.f15033i;
                        if (objA == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i10 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        k2.c.G(obj);
                        objA = obj;
                    }
                    str = (String) objA;
                } catch (CancellationException e5) {
                    throw e5;
                } catch (Exception unused) {
                    str = null;
                }
                if (kotlin.jvm.internal.p.a(this.this$0.profileManager.getProfileIdSync(), this.$profileId) && ((SettingsUiState) this.this$0._uiState.getValue()).isSimklConnected()) {
                    this.this$0._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) this.this$0._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, str, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -8388609, -1, 1073741823, null));
                }
                return x6.t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass3) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15261(boolean z, boolean z5, boolean z10, SettingsViewModel settingsViewModel, String str, d7.d<? super C15261> dVar) {
            super(2, dVar);
            this.$isTraktConnected = z;
            this.$isMdbListConnected = z5;
            this.$isSimklConnected = z10;
            this.this$0 = settingsViewModel;
            this.$profileId = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C15261 c15261 = new C15261(this.$isTraktConnected, this.$isMdbListConnected, this.$isSimklConnected, this.this$0, this.$profileId, dVar);
            c15261.L$0 = obj;
            return c15261;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            ka.k0 k0Var = (ka.k0) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            if (this.$isTraktConnected) {
                ka.m0.p(k0Var, null, 0, new C01791(this.this$0, this.$profileId, null), 3);
            }
            if (this.$isMdbListConnected) {
                ka.m0.p(k0Var, null, 0, new AnonymousClass2(this.this$0, this.$profileId, null), 3);
            }
            if (this.$isSimklConnected) {
                ka.m0.p(k0Var, null, 0, new AnonymousClass3(this.this$0, this.$profileId, null), 3);
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15261) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshIptv$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshIptv$1", f = "SettingsViewModel.kt", l = {2356, 2364}, m = "invokeSuspend", v = 2)
    public static final class C15271 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $configured;
        final /* synthetic */ boolean $force;
        final /* synthetic */ boolean $showToast;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshIptv$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshIptv$1$1", f = "SettingsViewModel.kt", l = {2375, 2378}, m = "invokeSuspend", v = 2)
        public static final class C01801 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $configured;
            final /* synthetic */ boolean $force;
            final /* synthetic */ boolean $showToast;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            boolean Z$0;
            boolean Z$1;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01801(SettingsViewModel settingsViewModel, boolean z, boolean z5, boolean z10, d7.d<? super C01801> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$force = z;
                this.$configured = z5;
                this.$showToast = z10;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static final x6.t0 invokeSuspend$lambda$1$0(SettingsViewModel settingsViewModel, IptvLoadProgress iptvLoadProgress) {
                na.q0 q0Var = settingsViewModel._uiState;
                SettingsUiState settingsUiState = (SettingsUiState) settingsViewModel._uiState.getValue();
                String message = iptvLoadProgress.getMessage();
                Integer percent = iptvLoadProgress.getPercent();
                q0Var.setValue(SettingsUiState.copy$default(settingsUiState, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, true, null, null, null, message, percent != null ? percent.intValue() : ((SettingsUiState) settingsViewModel._uiState.getValue()).getIptvProgressPercent(), null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -25089, 1073741823, null));
                return x6.t0.f22605a;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01801 c01801 = new C01801(this.this$0, this.$force, this.$configured, this.$showToast, dVar);
                c01801.L$0 = obj;
                return c01801;
            }

            /* JADX WARN: Code restructure failed: missing block: B:20:0x0179, code lost:
            
                if (r0.purgeAllIptvSourceCaches(r147) == r14) goto L25;
             */
            /* JADX WARN: Removed duplicated region for block: B:100:0x0461  */
            /* JADX WARN: Removed duplicated region for block: B:101:0x0464  */
            /* JADX WARN: Removed duplicated region for block: B:104:0x047b  */
            /* JADX WARN: Removed duplicated region for block: B:105:0x047e  */
            /* JADX WARN: Removed duplicated region for block: B:108:0x0486  */
            /* JADX WARN: Removed duplicated region for block: B:109:0x0489  */
            /* JADX WARN: Removed duplicated region for block: B:113:0x0492  */
            /* JADX WARN: Removed duplicated region for block: B:115:0x04a3  */
            /* JADX WARN: Removed duplicated region for block: B:116:0x04a6  */
            /* JADX WARN: Removed duplicated region for block: B:26:0x01b4  */
            /* JADX WARN: Removed duplicated region for block: B:29:0x01bf  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x01c0  */
            /* JADX WARN: Removed duplicated region for block: B:35:0x01cb A[Catch: all -> 0x002b, TryCatch #0 {all -> 0x002b, blocks: (B:7:0x0022, B:27:0x01b7, B:32:0x01c3, B:55:0x021b, B:58:0x0227, B:66:0x025b, B:68:0x0261, B:73:0x02a9, B:75:0x02c6, B:78:0x02ce, B:82:0x02de, B:83:0x02f6, B:88:0x0327, B:91:0x033b, B:90:0x032c, B:85:0x02fd, B:86:0x0316, B:77:0x02cb, B:70:0x0283, B:72:0x0289, B:62:0x0236, B:35:0x01cb, B:36:0x01d0, B:38:0x01d6, B:40:0x01ec, B:42:0x01f2, B:44:0x01f8, B:46:0x01fe, B:48:0x0208, B:50:0x0212, B:53:0x0217, B:54:0x021a, B:23:0x0185), top: B:121:0x000f }] */
            /* JADX WARN: Removed duplicated region for block: B:38:0x01d6 A[Catch: all -> 0x002b, TryCatch #0 {all -> 0x002b, blocks: (B:7:0x0022, B:27:0x01b7, B:32:0x01c3, B:55:0x021b, B:58:0x0227, B:66:0x025b, B:68:0x0261, B:73:0x02a9, B:75:0x02c6, B:78:0x02ce, B:82:0x02de, B:83:0x02f6, B:88:0x0327, B:91:0x033b, B:90:0x032c, B:85:0x02fd, B:86:0x0316, B:77:0x02cb, B:70:0x0283, B:72:0x0289, B:62:0x0236, B:35:0x01cb, B:36:0x01d0, B:38:0x01d6, B:40:0x01ec, B:42:0x01f2, B:44:0x01f8, B:46:0x01fe, B:48:0x0208, B:50:0x0212, B:53:0x0217, B:54:0x021a, B:23:0x0185), top: B:121:0x000f }] */
            /* JADX WARN: Removed duplicated region for block: B:57:0x0226  */
            /* JADX WARN: Removed duplicated region for block: B:60:0x0231  */
            /* JADX WARN: Removed duplicated region for block: B:61:0x0234  */
            /* JADX WARN: Removed duplicated region for block: B:66:0x025b A[Catch: all -> 0x002b, TRY_ENTER, TryCatch #0 {all -> 0x002b, blocks: (B:7:0x0022, B:27:0x01b7, B:32:0x01c3, B:55:0x021b, B:58:0x0227, B:66:0x025b, B:68:0x0261, B:73:0x02a9, B:75:0x02c6, B:78:0x02ce, B:82:0x02de, B:83:0x02f6, B:88:0x0327, B:91:0x033b, B:90:0x032c, B:85:0x02fd, B:86:0x0316, B:77:0x02cb, B:70:0x0283, B:72:0x0289, B:62:0x0236, B:35:0x01cb, B:36:0x01d0, B:38:0x01d6, B:40:0x01ec, B:42:0x01f2, B:44:0x01f8, B:46:0x01fe, B:48:0x0208, B:50:0x0212, B:53:0x0217, B:54:0x021a, B:23:0x0185), top: B:121:0x000f }] */
            /* JADX WARN: Removed duplicated region for block: B:70:0x0283 A[Catch: all -> 0x002b, TryCatch #0 {all -> 0x002b, blocks: (B:7:0x0022, B:27:0x01b7, B:32:0x01c3, B:55:0x021b, B:58:0x0227, B:66:0x025b, B:68:0x0261, B:73:0x02a9, B:75:0x02c6, B:78:0x02ce, B:82:0x02de, B:83:0x02f6, B:88:0x0327, B:91:0x033b, B:90:0x032c, B:85:0x02fd, B:86:0x0316, B:77:0x02cb, B:70:0x0283, B:72:0x0289, B:62:0x0236, B:35:0x01cb, B:36:0x01d0, B:38:0x01d6, B:40:0x01ec, B:42:0x01f2, B:44:0x01f8, B:46:0x01fe, B:48:0x0208, B:50:0x0212, B:53:0x0217, B:54:0x021a, B:23:0x0185), top: B:121:0x000f }] */
            /* JADX WARN: Removed duplicated region for block: B:75:0x02c6 A[Catch: all -> 0x002b, TryCatch #0 {all -> 0x002b, blocks: (B:7:0x0022, B:27:0x01b7, B:32:0x01c3, B:55:0x021b, B:58:0x0227, B:66:0x025b, B:68:0x0261, B:73:0x02a9, B:75:0x02c6, B:78:0x02ce, B:82:0x02de, B:83:0x02f6, B:88:0x0327, B:91:0x033b, B:90:0x032c, B:85:0x02fd, B:86:0x0316, B:77:0x02cb, B:70:0x0283, B:72:0x0289, B:62:0x0236, B:35:0x01cb, B:36:0x01d0, B:38:0x01d6, B:40:0x01ec, B:42:0x01f2, B:44:0x01f8, B:46:0x01fe, B:48:0x0208, B:50:0x0212, B:53:0x0217, B:54:0x021a, B:23:0x0185), top: B:121:0x000f }] */
            /* JADX WARN: Removed duplicated region for block: B:77:0x02cb A[Catch: all -> 0x002b, TryCatch #0 {all -> 0x002b, blocks: (B:7:0x0022, B:27:0x01b7, B:32:0x01c3, B:55:0x021b, B:58:0x0227, B:66:0x025b, B:68:0x0261, B:73:0x02a9, B:75:0x02c6, B:78:0x02ce, B:82:0x02de, B:83:0x02f6, B:88:0x0327, B:91:0x033b, B:90:0x032c, B:85:0x02fd, B:86:0x0316, B:77:0x02cb, B:70:0x0283, B:72:0x0289, B:62:0x0236, B:35:0x01cb, B:36:0x01d0, B:38:0x01d6, B:40:0x01ec, B:42:0x01f2, B:44:0x01f8, B:46:0x01fe, B:48:0x0208, B:50:0x0212, B:53:0x0217, B:54:0x021a, B:23:0x0185), top: B:121:0x000f }] */
            /* JADX WARN: Removed duplicated region for block: B:80:0x02da  */
            /* JADX WARN: Removed duplicated region for block: B:86:0x0316 A[Catch: all -> 0x002b, TryCatch #0 {all -> 0x002b, blocks: (B:7:0x0022, B:27:0x01b7, B:32:0x01c3, B:55:0x021b, B:58:0x0227, B:66:0x025b, B:68:0x0261, B:73:0x02a9, B:75:0x02c6, B:78:0x02ce, B:82:0x02de, B:83:0x02f6, B:88:0x0327, B:91:0x033b, B:90:0x032c, B:85:0x02fd, B:86:0x0316, B:77:0x02cb, B:70:0x0283, B:72:0x0289, B:62:0x0236, B:35:0x01cb, B:36:0x01d0, B:38:0x01d6, B:40:0x01ec, B:42:0x01f2, B:44:0x01f8, B:46:0x01fe, B:48:0x0208, B:50:0x0212, B:53:0x0217, B:54:0x021a, B:23:0x0185), top: B:121:0x000f }] */
            /* JADX WARN: Removed duplicated region for block: B:88:0x0327 A[Catch: all -> 0x002b, TryCatch #0 {all -> 0x002b, blocks: (B:7:0x0022, B:27:0x01b7, B:32:0x01c3, B:55:0x021b, B:58:0x0227, B:66:0x025b, B:68:0x0261, B:73:0x02a9, B:75:0x02c6, B:78:0x02ce, B:82:0x02de, B:83:0x02f6, B:88:0x0327, B:91:0x033b, B:90:0x032c, B:85:0x02fd, B:86:0x0316, B:77:0x02cb, B:70:0x0283, B:72:0x0289, B:62:0x0236, B:35:0x01cb, B:36:0x01d0, B:38:0x01d6, B:40:0x01ec, B:42:0x01f2, B:44:0x01f8, B:46:0x01fe, B:48:0x0208, B:50:0x0212, B:53:0x0217, B:54:0x021a, B:23:0x0185), top: B:121:0x000f }] */
            /* JADX WARN: Removed duplicated region for block: B:90:0x032c A[Catch: all -> 0x002b, TryCatch #0 {all -> 0x002b, blocks: (B:7:0x0022, B:27:0x01b7, B:32:0x01c3, B:55:0x021b, B:58:0x0227, B:66:0x025b, B:68:0x0261, B:73:0x02a9, B:75:0x02c6, B:78:0x02ce, B:82:0x02de, B:83:0x02f6, B:88:0x0327, B:91:0x033b, B:90:0x032c, B:85:0x02fd, B:86:0x0316, B:77:0x02cb, B:70:0x0283, B:72:0x0289, B:62:0x0236, B:35:0x01cb, B:36:0x01d0, B:38:0x01d6, B:40:0x01ec, B:42:0x01f2, B:44:0x01f8, B:46:0x01fe, B:48:0x0208, B:50:0x0212, B:53:0x0217, B:54:0x021a, B:23:0x0185), top: B:121:0x000f }] */
            @Override // f7.a
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r148) {
                /*
                    Method dump skipped, instruction units count: 1462
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15271.C01801.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
                return ((C01801) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15271(boolean z, boolean z5, boolean z10, d7.d<? super C15271> dVar) {
            super(2, dVar);
            this.$force = z;
            this.$configured = z5;
            this.$showToast = z10;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final x6.t0 invokeSuspend$lambda$1$0(SettingsViewModel settingsViewModel, ka.v1 v1Var, Throwable th) {
            if (settingsViewModel.iptvLoadJob == v1Var) {
                settingsViewModel.iptvLoadJob = null;
            }
            return x6.t0.f22605a;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C15271 c15271 = SettingsViewModel.this.new C15271(this.$force, this.$configured, this.$showToast, dVar);
            c15271.L$0 = obj;
            return c15271;
        }

        /* JADX WARN: Code restructure failed: missing block: B:40:0x00af, code lost:
        
            if (ka.m0.h(r15, r14) == r7) goto L41;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r15) {
            /*
                Method dump skipped, instruction units count: 212
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15271.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15271) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshSubtitleOptions$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshSubtitleOptions$1", f = "SettingsViewModel.kt", l = {770}, m = "invokeSuspend", v = 2)
    public static final class C15281 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C15281(d7.d<? super C15281> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15281(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object objLoadSubtitleOptions;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SettingsViewModel settingsViewModel = SettingsViewModel.this;
                String defaultSubtitle = ((SettingsUiState) settingsViewModel._uiState.getValue()).getDefaultSubtitle();
                this.label = 1;
                objLoadSubtitleOptions = settingsViewModel.loadSubtitleOptions(defaultSubtitle, this);
                e7.a aVar = e7.a.f15033i;
                if (objLoadSubtitleOptions == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objLoadSubtitleOptions = obj;
            }
            List list = (List) objLoadSubtitleOptions;
            if (!kotlin.jvm.internal.p.a(((SettingsUiState) SettingsViewModel.this._uiState.getValue()).getSubtitleOptions(), list)) {
                SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, list, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -3, -1, -1, 1073741823, null));
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15281) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshSyncSummary$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$refreshSyncSummary$1", f = "SettingsViewModel.kt", l = {743}, m = "invokeSuspend", v = 2)
    public static final class C15291 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $profileId;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15291(String str, d7.d<? super C15291> dVar) {
            super(2, dVar);
            this.$profileId = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15291(this.$profileId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            String lastSyncTime;
            Object lastSyncSummary;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                lastSyncTime = ((SettingsUiState) SettingsViewModel.this._uiState.getValue()).getLastSyncTime();
                TraktSyncService traktSyncService = SettingsViewModel.this.traktSyncService;
                this.L$0 = lastSyncTime;
                this.label = 1;
                lastSyncSummary = traktSyncService.getLastSyncSummary(this);
                e7.a aVar = e7.a.f15033i;
                if (lastSyncSummary == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                lastSyncTime = (String) this.L$0;
                k2.c.G(obj);
                lastSyncSummary = obj;
            }
            TraktSyncSummary traktSyncSummary = (TraktSyncSummary) lastSyncSummary;
            boolean zA = kotlin.jvm.internal.p.a(SettingsViewModel.this.profileManager.getProfileIdSync(), this.$profileId);
            x6.t0 t0Var = x6.t0.f22605a;
            if (zA && kotlin.jvm.internal.p.a(((SettingsUiState) SettingsViewModel.this._uiState.getValue()).getLastSyncTime(), lastSyncTime)) {
                SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, SettingsViewModel.this.formatSyncTime(traktSyncSummary != null ? traktSyncSummary.getLastSyncAt() : null), traktSyncSummary != null ? traktSyncSummary.getMoviesSynced() : 0, traktSyncSummary != null ? traktSyncSummary.getEpisodesSynced() : 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, Integer.MAX_VALUE, -4, 1073741823, null));
            }
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15291) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$removeAddon$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$removeAddon$1", f = "SettingsViewModel.kt", l = {2482, 2483, 2485}, m = "invokeSuspend", v = 2)
    public static final class C15301 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $addonId;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15301(String str, d7.d<? super C15301> dVar) {
            super(2, dVar);
            this.$addonId = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C15301 c15301 = SettingsViewModel.this.new C15301(this.$addonId, dVar);
            c15301.L$0 = obj;
            return c15301;
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0072, code lost:
        
            if (r9 != r7) goto L24;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = r8.L$0
                ka.k0 r0 = (ka.k0) r0
                int r1 = r8.label
                r2 = 0
                r3 = 3
                r4 = 2
                r5 = 1
                r6 = 0
                e7.a r7 = e7.a.f15033i
                if (r1 == 0) goto L31
                if (r1 == r5) goto L2d
                if (r1 == r4) goto L29
                if (r1 != r3) goto L21
                java.lang.Object r0 = r8.L$2
                ka.k0 r0 = (ka.k0) r0
                java.lang.Object r0 = r8.L$1
                java.util.List r0 = (java.util.List) r0
                k2.c.G(r9)     // Catch: java.lang.Throwable -> L7a
                goto L75
            L21:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L29:
                k2.c.G(r9)
                goto L5c
            L2d:
                k2.c.G(r9)
                goto L47
            L31:
                k2.c.G(r9)
                com.arflix.tv.ui.screens.settings.SettingsViewModel r9 = com.arflix.tv.ui.screens.settings.SettingsViewModel.this
                com.arflix.tv.data.repository.StreamRepository r9 = com.arflix.tv.ui.screens.settings.SettingsViewModel.access$getStreamRepository$p(r9)
                java.lang.String r1 = r8.$addonId
                r8.L$0 = r0
                r8.label = r5
                java.lang.Object r9 = r9.removeAddon(r1, r8)
                if (r9 != r7) goto L47
                goto L74
            L47:
                com.arflix.tv.ui.screens.settings.SettingsViewModel r9 = com.arflix.tv.ui.screens.settings.SettingsViewModel.this
                com.arflix.tv.data.repository.StreamRepository r9 = com.arflix.tv.ui.screens.settings.SettingsViewModel.access$getStreamRepository$p(r9)
                na.j r9 = r9.getInstalledAddons()
                r8.L$0 = r0
                r8.label = r4
                java.lang.Object r9 = na.y0.l(r9, r8)
                if (r9 != r7) goto L5c
                goto L74
            L5c:
                java.util.List r9 = (java.util.List) r9
                com.arflix.tv.ui.screens.settings.SettingsViewModel r0 = com.arflix.tv.ui.screens.settings.SettingsViewModel.this
                com.arflix.tv.data.repository.CatalogRepository r0 = com.arflix.tv.ui.screens.settings.SettingsViewModel.access$getCatalogRepository$p(r0)     // Catch: java.lang.Throwable -> L7a
                r8.L$0 = r6     // Catch: java.lang.Throwable -> L7a
                r8.L$1 = r6     // Catch: java.lang.Throwable -> L7a
                r8.L$2 = r6     // Catch: java.lang.Throwable -> L7a
                r8.I$0 = r2     // Catch: java.lang.Throwable -> L7a
                r8.label = r3     // Catch: java.lang.Throwable -> L7a
                java.lang.Object r9 = r0.syncAddonCatalogs(r9, r8)     // Catch: java.lang.Throwable -> L7a
                if (r9 != r7) goto L75
            L74:
                return r7
            L75:
                java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L7a
                r9.booleanValue()     // Catch: java.lang.Throwable -> L7a
            L7a:
                com.arflix.tv.ui.screens.settings.SettingsViewModel r9 = com.arflix.tv.ui.screens.settings.SettingsViewModel.this
                com.arflix.tv.ui.screens.settings.SettingsViewModel.syncLocalStateToCloud$default(r9, r5, r2, r4, r6)
                x6.t0 r9 = x6.t0.f22605a
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15301.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15301) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$removeCatalog$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$removeCatalog$1", f = "SettingsViewModel.kt", l = {2207, 2210}, m = "invokeSuspend", v = 2)
    public static final class C15311 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $catalogId;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15311(String str, d7.d<? super C15311> dVar) {
            super(2, dVar);
            this.$catalogId = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15311(this.$catalogId, dVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x0195  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r142) {
            /*
                Method dump skipped, instruction units count: 699
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15311.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15311) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$removeCatalogPack$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$removeCatalogPack$1", f = "SettingsViewModel.kt", l = {2083}, m = "invokeSuspend", v = 2)
    public static final class C15321 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $packId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15321(String str, d7.d<? super C15321> dVar) {
            super(2, dVar);
            this.$packId = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15321(this.$packId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object objM6117removeCatalogPackgIAlus;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                CatalogRepository catalogRepository = SettingsViewModel.this.catalogRepository;
                String str = this.$packId;
                this.label = 1;
                objM6117removeCatalogPackgIAlus = catalogRepository.m6117removeCatalogPackgIAlus(str, this);
                e7.a aVar = e7.a.f15033i;
                if (objM6117removeCatalogPackgIAlus == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objM6117removeCatalogPackgIAlus = ((x6.d0) obj).f22580i;
            }
            SettingsViewModel settingsViewModel = SettingsViewModel.this;
            if (!(objM6117removeCatalogPackgIAlus instanceof x6.c0)) {
                settingsViewModel._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, "Pack removed", ToastType.SUCCESS, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
                SettingsViewModel.syncLocalStateToCloud$default(settingsViewModel, true, false, 2, null);
            }
            SettingsViewModel settingsViewModel2 = SettingsViewModel.this;
            Throwable thA = x6.d0.a(objM6117removeCatalogPackgIAlus);
            if (thA != null) {
                na.q0 q0Var = settingsViewModel2._uiState;
                SettingsUiState settingsUiState = (SettingsUiState) settingsViewModel2._uiState.getValue();
                String message = thA.getMessage();
                if (message == null) {
                    message = "Failed to remove pack";
                }
                q0Var.setValue(SettingsUiState.copy$default(settingsUiState, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, message, ToastType.ERROR, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15321) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$renameCatalog$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$renameCatalog$1", f = "SettingsViewModel.kt", l = {2252}, m = "invokeSuspend", v = 2)
    public static final class C15331 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $catalogId;
        final /* synthetic */ String $newTitle;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15331(String str, String str2, d7.d<? super C15331> dVar) {
            super(2, dVar);
            this.$catalogId = str;
            this.$newTitle = str2;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15331(this.$catalogId, this.$newTitle, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                CatalogRepository catalogRepository = SettingsViewModel.this.catalogRepository;
                String str = this.$catalogId;
                String str2 = this.$newTitle;
                this.label = 1;
                obj = catalogRepository.renameCatalog(str, str2, this);
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
            if (((Boolean) obj).booleanValue()) {
                SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15331) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$resetIptvGroupOrder$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$resetIptvGroupOrder$1", f = "SettingsViewModel.kt", l = {858}, m = "invokeSuspend", v = 2)
    public static final class C15341 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $playlistId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15341(String str, d7.d<? super C15341> dVar) {
            super(2, dVar);
            this.$playlistId = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15341(this.$playlistId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                IptvRepository iptvRepository = SettingsViewModel.this.iptvRepository;
                String str = this.$playlistId;
                this.label = 1;
                Object objResetGroupOrder = iptvRepository.resetGroupOrder(str, this);
                e7.a aVar = e7.a.f15033i;
                if (objResetGroupOrder == aVar) {
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
            return ((C15341) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$restoreCloudStateToLocalInternal$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel", f = "SettingsViewModel.kt", l = {3242, 3245}, m = "restoreCloudStateToLocalInternal", v = 2)
    public static final class C15351 extends f7.c {
        int I$0;
        Object L$0;
        boolean Z$0;
        boolean Z$1;
        int label;
        /* synthetic */ Object result;

        public C15351(d7.d<? super C15351> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SettingsViewModel.this.restoreCloudStateToLocalInternal(false, false, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$saveIptvConfig$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$saveIptvConfig$1", f = "SettingsViewModel.kt", l = {2287}, m = "invokeSuspend", v = 2)
    public static final class C15361 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $epgUrl;
        final /* synthetic */ String $m3uUrl;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ SettingsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15361(String str, String str2, SettingsViewModel settingsViewModel, d7.d<? super C15361> dVar) {
            super(2, dVar);
            this.$m3uUrl = str;
            this.$epgUrl = str2;
            this.this$0 = settingsViewModel;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return new C15361(this.$m3uUrl, this.$epgUrl, this.this$0, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws NoSuchAlgorithmException {
            int i10 = this.label;
            x6.t0 t0Var = x6.t0.f22605a;
            if (i10 == 0) {
                k2.c.G(obj);
                String string = kotlin.text.o.L0(this.$m3uUrl).toString();
                String string2 = kotlin.text.o.L0(this.$epgUrl).toString();
                if (kotlin.text.o.h0(string)) {
                    this.this$0._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) this.this$0._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, "M3U URL is required", ToastType.ERROR, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
                    return t0Var;
                }
                this.this$0.lastObservedIptvM3u = string;
                IptvRepository iptvRepository = this.this$0.iptvRepository;
                this.L$0 = null;
                this.L$1 = null;
                this.label = 1;
                Object objSaveConfig = iptvRepository.saveConfig(string, string2, this);
                e7.a aVar = e7.a.f15033i;
                if (objSaveConfig == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.syncLocalStateToCloud$default(this.this$0, true, false, 2, null);
            this.this$0.refreshIptv(true, true, false);
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15361) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$saveIptvPlaylists$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$saveIptvPlaylists$1", f = "SettingsViewModel.kt", l = {2344}, m = "invokeSuspend", v = 2)
    public static final class C15371 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ List<IptvPlaylistEntry> $playlists;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15371(List<IptvPlaylistEntry> list, d7.d<? super C15371> dVar) {
            super(2, dVar);
            this.$playlists = list;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15371(this.$playlists, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws NoSuchAlgorithmException {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                IptvRepository iptvRepository = SettingsViewModel.this.iptvRepository;
                List<IptvPlaylistEntry> list = this.$playlists;
                this.label = 1;
                Object objSavePlaylists = iptvRepository.savePlaylists(list, this);
                e7.a aVar = e7.a.f15033i;
                if (objSavePlaylists == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            na.q0 q0Var = SettingsViewModel.this._uiState;
            SettingsUiState settingsUiState = (SettingsUiState) SettingsViewModel.this._uiState.getValue();
            List<IptvPlaylistEntry> list2 = this.$playlists;
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : list2) {
                if (!kotlin.text.o.h0(((IptvPlaylistEntry) obj2).getM3uUrl())) {
                    arrayList.add(obj2);
                }
            }
            q0Var.setValue(SettingsUiState.copy$default(settingsUiState, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, arrayList, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, "IPTV playlists updated", ToastType.SUCCESS, false, false, false, false, false, null, null, false, null, false, -1, -1, -17, 1072955391, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15371) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$saveQualityFilters$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel", f = "SettingsViewModel.kt", l = {1768}, m = "saveQualityFilters", v = 2)
    public static final class C15381 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C15381(d7.d<? super C15381> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SettingsViewModel.this.saveQualityFilters(null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$saveQualityFilters$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$saveQualityFilters$2", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C15392 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ List<QualityFilterConfig> $filters;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15392(List<QualityFilterConfig> list, d7.d<? super C15392> dVar) {
            super(2, dVar);
            this.$filters = list;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C15392 c15392 = SettingsViewModel.this.new C15392(this.$filters, dVar);
            c15392.L$0 = obj;
            return c15392;
        }

        @Override // r7.p
        public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
            return ((C15392) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            c1.b bVar = (c1.b) this.L$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            bVar.d(SettingsViewModel.this.qualityFiltersKey, SettingsViewModel.this.gson.toJson(this.$filters));
            return x6.t0.f22605a;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$saveStalkerConfig$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$saveStalkerConfig$1", f = "SettingsViewModel.kt", l = {2301}, m = "invokeSuspend", v = 2)
    public static final class C15401 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $macAddress;
        final /* synthetic */ String $portalUrl;
        int label;
        final /* synthetic */ SettingsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15401(String str, String str2, SettingsViewModel settingsViewModel, d7.d<? super C15401> dVar) {
            super(2, dVar);
            this.$portalUrl = str;
            this.$macAddress = str2;
            this.this$0 = settingsViewModel;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return new C15401(this.$portalUrl, this.$macAddress, this.this$0, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws NoSuchAlgorithmException {
            int i10 = this.label;
            x6.t0 t0Var = x6.t0.f22605a;
            if (i10 == 0) {
                k2.c.G(obj);
                if (kotlin.text.o.h0(this.$portalUrl) || kotlin.text.o.h0(this.$macAddress)) {
                    this.this$0._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) this.this$0._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, "Portal URL and MAC address are required", ToastType.ERROR, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
                    return t0Var;
                }
                IptvRepository iptvRepository = this.this$0.iptvRepository;
                String str = this.$portalUrl;
                String str2 = this.$macAddress;
                this.label = 1;
                Object objSaveStalkerConfig = iptvRepository.saveStalkerConfig(str, str2, this);
                e7.a aVar = e7.a.f15033i;
                if (objSaveStalkerConfig == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.syncLocalStateToCloud$default(this.this$0, true, false, 2, null);
            this.this$0.refreshIptv(true, true, true);
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15401) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$saveSubtitleAiApiKey$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$saveSubtitleAiApiKey$1", f = "SettingsViewModel.kt", l = {1539}, m = "invokeSuspend", v = 2)
    public static final class C15411 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $key;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$saveSubtitleAiApiKey$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$saveSubtitleAiApiKey$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01811 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $key;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01811(SettingsViewModel settingsViewModel, String str, d7.d<? super C01811> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$key = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01811 c01811 = new C01811(this.this$0, this.$key, dVar);
                c01811.L$0 = obj;
                return c01811;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01811) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.subtitleAiApiKeyKey, kotlin.text.o.L0(this.$key).toString());
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15411(String str, d7.d<? super C15411> dVar) {
            super(2, dVar);
            this.$key = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15411(this.$key, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01811 c01811 = new C01811(SettingsViewModel.this, this.$key, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01811, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, kotlin.text.o.L0(this.$key).toString(), null, false, null, false, -1, -1, -1, 1040187391, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15411) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$searchCatalogLists$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$searchCatalogLists$1", f = "SettingsViewModel.kt", l = {2141}, m = "invokeSuspend", v = 2)
    public static final class C15421 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $normalizedQuery;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15421(String str, d7.d<? super C15421> dVar) {
            super(2, dVar);
            this.$normalizedQuery = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15421(this.$normalizedQuery, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object objM6112searchCatalogListsgIAlus;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, true, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -201326593, 1073741823, null));
                CatalogDiscoveryRepository catalogDiscoveryRepository = SettingsViewModel.this.catalogDiscoveryRepository;
                String str = this.$normalizedQuery;
                this.label = 1;
                objM6112searchCatalogListsgIAlus = catalogDiscoveryRepository.m6112searchCatalogListsgIAlus(str, this);
                e7.a aVar = e7.a.f15033i;
                if (objM6112searchCatalogListsgIAlus == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objM6112searchCatalogListsgIAlus = ((x6.d0) obj).f22580i;
            }
            SettingsViewModel settingsViewModel = SettingsViewModel.this;
            if (!(objM6112searchCatalogListsgIAlus instanceof x6.c0)) {
                List list = (List) objM6112searchCatalogListsgIAlus;
                settingsViewModel._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, list, false, list.isEmpty() ? "No public Trakt lists found" : null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -234881025, 1073741823, null));
            }
            SettingsViewModel settingsViewModel2 = SettingsViewModel.this;
            Throwable thA = x6.d0.a(objM6112searchCatalogListsgIAlus);
            if (thA != null) {
                na.q0 q0Var = settingsViewModel2._uiState;
                SettingsUiState settingsUiState = (SettingsUiState) settingsViewModel2._uiState.getValue();
                String message = thA.getMessage();
                if (message == null) {
                    message = settingsViewModel2.context.getString(R.string.catalog_failed_search);
                }
                q0Var.setValue(SettingsUiState.copy$default(settingsUiState, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, kotlin.collections.z.f19728i, false, message, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -234881025, 1073741823, null));
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15421) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setAutoPlayMinQuality$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setAutoPlayMinQuality$1", f = "SettingsViewModel.kt", l = {1223}, m = "invokeSuspend", v = 2)
    public static final class C15431 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $normalized;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setAutoPlayMinQuality$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setAutoPlayMinQuality$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01821 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $normalized;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01821(SettingsViewModel settingsViewModel, String str, d7.d<? super C01821> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$normalized = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01821 c01821 = new C01821(this.this$0, this.$normalized, dVar);
                c01821.L$0 = obj;
                return c01821;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01821) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.autoPlayMinQualityKey(), this.$normalized);
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15431(String str, d7.d<? super C15431> dVar) {
            super(2, dVar);
            this.$normalized = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15431(this.$normalized, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01821 c01821 = new C01821(SettingsViewModel.this, this.$normalized, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01821, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, this.$normalized, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -257, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15431) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setAutoPlayNext$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setAutoPlayNext$1", f = "SettingsViewModel.kt", l = {1168, 1174}, m = "invokeSuspend", v = 2)
    public static final class C15441 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setAutoPlayNext$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setAutoPlayNext$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01831 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01831(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C01831> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01831 c01831 = new C01831(this.this$0, this.$enabled, dVar);
                c01831.L$0 = obj;
                return c01831;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01831) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.autoPlayNextKey(), Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15441(boolean z, d7.d<? super C15441> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15441(this.$enabled, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x016f, code lost:
        
            if (r1.m6106saveAutoPlayNextToProfilegIAlus(r6, r139) == r5) goto L15;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r140) {
            /*
                Method dump skipped, instruction units count: 379
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15441.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15441) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setAutoPlaySingleSource$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setAutoPlaySingleSource$1", f = "SettingsViewModel.kt", l = {1181}, m = "invokeSuspend", v = 2)
    public static final class C15451 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setAutoPlaySingleSource$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setAutoPlaySingleSource$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01841 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01841(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C01841> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01841 c01841 = new C01841(this.this$0, this.$enabled, dVar);
                c01841.L$0 = obj;
                return c01841;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01841) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.autoPlaySingleSourceKey(), Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15451(boolean z, d7.d<? super C15451> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15451(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01841 c01841 = new C01841(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01841, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, this.$enabled, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -129, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15451) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setCardLayoutMode$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setCardLayoutMode$1", f = "SettingsViewModel.kt", l = {1243}, m = "invokeSuspend", v = 2)
    public static final class C15461 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $normalized;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setCardLayoutMode$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setCardLayoutMode$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01851 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $normalized;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01851(SettingsViewModel settingsViewModel, String str, d7.d<? super C01851> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$normalized = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01851 c01851 = new C01851(this.this$0, this.$normalized, dVar);
                c01851.L$0 = obj;
                return c01851;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01851) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.cardLayoutModeKey(), this.$normalized);
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15461(String str, d7.d<? super C15461> dVar) {
            super(2, dVar);
            this.$normalized = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15461(this.$normalized, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01851 c01851 = new C01851(SettingsViewModel.this, this.$normalized, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01851, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, this.$normalized, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -17, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15461) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setContentLanguage$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setContentLanguage$1", f = "SettingsViewModel.kt", l = {1254}, m = "invokeSuspend", v = 2)
    public static final class C15471 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $lang;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setContentLanguage$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setContentLanguage$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01861 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $lang;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01861(SettingsViewModel settingsViewModel, String str, d7.d<? super C01861> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$lang = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01861 c01861 = new C01861(this.this$0, this.$lang, dVar);
                c01861.L$0 = obj;
                return c01861;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01861) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.contentLanguageKey(), this.$lang);
                bVar.d(AppLanguageKt.getLAST_APP_LANGUAGE_KEY(), this.$lang);
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15471(String str, d7.d<? super C15471> dVar) {
            super(2, dVar);
            this.$lang = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15471(this.$lang, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01861 c01861 = new C01861(SettingsViewModel.this, this.$lang, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01861, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this.context.getSharedPreferences("app_locale", 0).edit().putString("locale_tag", this.$lang).apply();
            SettingsViewModel.this.mediaRepository.setContentLanguage(this.$lang);
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, this.$lang, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1073741311, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15471) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setCustomUserAgent$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setCustomUserAgent$1", f = "SettingsViewModel.kt", l = {1652}, m = "invokeSuspend", v = 2)
    public static final class C15481 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $trimmed;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setCustomUserAgent$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setCustomUserAgent$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01871 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $trimmed;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01871(String str, SettingsViewModel settingsViewModel, d7.d<? super C01871> dVar) {
                super(2, dVar);
                this.$trimmed = str;
                this.this$0 = settingsViewModel;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01871 c01871 = new C01871(this.$trimmed, this.this$0, dVar);
                c01871.L$0 = obj;
                return c01871;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01871) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                if (kotlin.text.o.h0(this.$trimmed)) {
                    bVar.c(this.this$0.customUserAgentKey);
                } else {
                    bVar.d(this.this$0.customUserAgentKey, this.$trimmed);
                }
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15481(String str, d7.d<? super C15481> dVar) {
            super(2, dVar);
            this.$trimmed = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15481(this.$trimmed, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01871 c01871 = new C01871(this.$trimmed, SettingsViewModel.this, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01871, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            OkHttpProvider.INSTANCE.setCustomUserAgent(this.$trimmed);
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, this.$trimmed, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -2049, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15481) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setDefaultAudioLanguage$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setDefaultAudioLanguage$1", f = "SettingsViewModel.kt", l = {AnalyticsListener.EVENT_AUDIO_DISABLED}, m = "invokeSuspend", v = 2)
    public static final class C15491 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $language;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setDefaultAudioLanguage$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setDefaultAudioLanguage$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01881 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $language;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01881(SettingsViewModel settingsViewModel, String str, d7.d<? super C01881> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$language = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01881 c01881 = new C01881(this.this$0, this.$language, dVar);
                c01881.L$0 = obj;
                return c01881;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01881) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.defaultAudioLanguageKey(), this.$language);
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15491(String str, d7.d<? super C15491> dVar) {
            super(2, dVar);
            this.$language = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15491(this.$language, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01881 c01881 = new C01881(SettingsViewModel.this, this.$language, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01881, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            na.q0 q0Var = SettingsViewModel.this._uiState;
            SettingsUiState settingsUiState = (SettingsUiState) SettingsViewModel.this._uiState.getValue();
            String str = this.$language;
            q0Var.setValue(SettingsUiState.copy$default(settingsUiState, null, null, str, SettingsViewModel.this.loadAudioLanguageOptions(str), null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -13, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15491) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setDefaultSubtitle$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setDefaultSubtitle$1", f = "SettingsViewModel.kt", l = {996, 1002, AnalyticsListener.EVENT_BANDWIDTH_ESTIMATE}, m = "invokeSuspend", v = 2)
    public static final class C15501 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $language;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setDefaultSubtitle$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setDefaultSubtitle$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01891 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ long $changedAt;
            final /* synthetic */ String $language;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01891(SettingsViewModel settingsViewModel, String str, long j10, d7.d<? super C01891> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$language = str;
                this.$changedAt = j10;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01891 c01891 = new C01891(this.this$0, this.$language, this.$changedAt, dVar);
                c01891.L$0 = obj;
                return c01891;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01891) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.defaultSubtitleKey(), this.$language);
                bVar.d(this.this$0.subtitleSettingsUpdatedAtKey(), String.valueOf(this.$changedAt));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15501(String str, d7.d<? super C15501> dVar) {
            super(2, dVar);
            this.$language = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15501(this.$language, dVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r144) {
            /*
                Method dump skipped, instruction units count: 704
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15501.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15501) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setDeviceModeOverride$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setDeviceModeOverride$1", f = "SettingsViewModel.kt", l = {1270}, m = "invokeSuspend", v = 2)
    public static final class C15511 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $mode;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setDeviceModeOverride$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setDeviceModeOverride$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01901 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $mode;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01901(String str, d7.d<? super C01901> dVar) {
                super(2, dVar);
                this.$mode = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01901 c01901 = new C01901(this.$mode, dVar);
                c01901.L$0 = obj;
                return c01901;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01901) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(DeviceTypeKt.getDEVICE_MODE_OVERRIDE_KEY(), this.$mode);
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15511(String str, d7.d<? super C15511> dVar) {
            super(2, dVar);
            this.$mode = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15511(this.$mode, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01901 c01901 = new C01901(this.$mode, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01901, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            DeviceTypeKt.setDeviceModeOverrideCache(SettingsViewModel.this.context, kotlin.jvm.internal.p.a(this.$mode, TtmlNode.TEXT_EMPHASIS_AUTO) ? null : this.$mode);
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, this.$mode, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1073740799, null));
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15511) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setDnsProvider$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setDnsProvider$1", f = "SettingsViewModel.kt", l = {1627, 1633, 1642}, m = "invokeSuspend", v = 2)
    public static final class C15521 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $value;
        Object L$0;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setDnsProvider$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Lx6/d0;", "", "Ljava/net/InetAddress;", "<anonymous>", "(Lka/k0;)Lx6/d0;"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setDnsProvider$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01911 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.d0>, Object> {
            final /* synthetic */ String $value;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01911(String str, d7.d<? super C01911> dVar) {
                super(2, dVar);
                this.$value = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01911 c01911 = new C01911(this.$value, dVar);
                c01911.L$0 = obj;
                return c01911;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                Object c0Var;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                OkHttpProvider okHttpProvider = OkHttpProvider.INSTANCE;
                okHttpProvider.setDnsProvider(okHttpProvider.parseDnsProvider(this.$value));
                try {
                    c0Var = okHttpProvider.getDns().lookup("image.tmdb.org");
                } catch (Throwable th) {
                    c0Var = new x6.c0(th);
                }
                return new x6.d0(c0Var);
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super x6.d0> dVar) {
                return ((C01911) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setDnsProvider$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setDnsProvider$1$2", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class AnonymousClass2 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $value;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(SettingsViewModel settingsViewModel, String str, d7.d<? super AnonymousClass2> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$value = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, this.$value, dVar);
                anonymousClass2.L$0 = obj;
                return anonymousClass2;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((AnonymousClass2) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.dnsProviderKey, this.$value);
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15521(String str, d7.d<? super C15521> dVar) {
            super(2, dVar);
            this.$value = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15521(this.$value, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x01c3, code lost:
        
            if (r1 != r7) goto L24;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r142) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 460
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15521.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15521) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setDolbyVisionCompatEnabled$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setDolbyVisionCompatEnabled$1", f = "SettingsViewModel.kt", l = {1523}, m = "invokeSuspend", v = 2)
    public static final class C15531 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setDolbyVisionCompatEnabled$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setDolbyVisionCompatEnabled$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01921 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01921(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C01921> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01921 c01921 = new C01921(this.this$0, this.$enabled, dVar);
                c01921.L$0 = obj;
                return c01921;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01921) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.dolbyVisionCompatKey, Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15531(boolean z, d7.d<? super C15531> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15531(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01921 c01921 = new C01921(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01921, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, this.$enabled, null, null, false, null, false, -1, -1, -1, 1056964607, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15531) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setFilterSubtitlesByLanguage$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setFilterSubtitlesByLanguage$1", f = "SettingsViewModel.kt", l = {1201}, m = "invokeSuspend", v = 2)
    public static final class C15541 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setFilterSubtitlesByLanguage$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setFilterSubtitlesByLanguage$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01931 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01931(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C01931> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01931 c01931 = new C01931(this.this$0, this.$enabled, dVar);
                c01931.L$0 = obj;
                return c01931;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01931) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.filterSubtitlesByLanguageKey(), Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15541(boolean z, d7.d<? super C15541> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15541(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01931 c01931 = new C01931(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01931, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, this.$enabled, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -131073, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15541) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setFrameRateMatchingMode$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setFrameRateMatchingMode$1", f = "SettingsViewModel.kt", l = {1316}, m = "invokeSuspend", v = 2)
    public static final class C15551 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $normalized;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setFrameRateMatchingMode$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setFrameRateMatchingMode$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01941 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $normalized;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01941(SettingsViewModel settingsViewModel, String str, d7.d<? super C01941> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$normalized = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01941 c01941 = new C01941(this.this$0, this.$normalized, dVar);
                c01941.L$0 = obj;
                return c01941;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01941) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.frameRateMatchingModeKey(), this.$normalized);
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15551(String str, d7.d<? super C15551> dVar) {
            super(2, dVar);
            this.$normalized = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15551(this.$normalized, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01941 c01941 = new C01941(SettingsViewModel.this, this.$normalized, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01941, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, this.$normalized, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -33, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15551) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setIncludeSpecials$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setIncludeSpecials$1", f = "SettingsViewModel.kt", l = {1669}, m = "invokeSuspend", v = 2)
    public static final class C15561 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setIncludeSpecials$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setIncludeSpecials$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01951 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01951(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C01951> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01951 c01951 = new C01951(this.this$0, this.$enabled, dVar);
                c01951.L$0 = obj;
                return c01951;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01951) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.includeSpecialsKey(), Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15561(boolean z, d7.d<? super C15561> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15561(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01951 c01951 = new C01951(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01951, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, this.$enabled, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -268435457, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15561) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setIptvSelectedPlaylistId$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setIptvSelectedPlaylistId$1", f = "SettingsViewModel.kt", l = {877}, m = "invokeSuspend", v = 2)
    public static final class C15571 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $selectedPlaylistId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15571(String str, d7.d<? super C15571> dVar) {
            super(2, dVar);
            this.$selectedPlaylistId = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15571(this.$selectedPlaylistId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objLoadIptvGroupsForPlaylist;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SettingsViewModel settingsViewModel = SettingsViewModel.this;
                String str = this.$selectedPlaylistId;
                this.label = 1;
                objLoadIptvGroupsForPlaylist = settingsViewModel.loadIptvGroupsForPlaylist(str, this);
                e7.a aVar = e7.a.f15033i;
                if (objLoadIptvGroupsForPlaylist == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objLoadIptvGroupsForPlaylist = obj;
            }
            List list = (List) objLoadIptvGroupsForPlaylist;
            if (kotlin.jvm.internal.p.a(((SettingsUiState) SettingsViewModel.this._uiState.getValue()).getIptvSelectedPlaylistId(), this.$selectedPlaylistId)) {
                SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, list, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -65537, 1073741823, null));
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15571) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setIptvSortOrder$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setIptvSortOrder$1", f = "SettingsViewModel.kt", l = {2456}, m = "invokeSuspend", v = 2)
    public static final class C15581 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $normalized;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15581(String str, d7.d<? super C15581> dVar) {
            super(2, dVar);
            this.$normalized = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15581(this.$normalized, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                IptvRepository iptvRepository = SettingsViewModel.this.iptvRepository;
                String str = this.$normalized;
                this.label = 1;
                Object objSaveSortOrder = iptvRepository.saveSortOrder(str, this);
                e7.a aVar = e7.a.f15033i;
                if (objSaveSortOrder == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15581) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setOledBlackBackground$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setOledBlackBackground$1", f = "SettingsViewModel.kt", l = {1295}, m = "invokeSuspend", v = 2)
    public static final class C15591 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setOledBlackBackground$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setOledBlackBackground$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01961 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01961(boolean z, d7.d<? super C01961> dVar) {
                super(2, dVar);
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01961 c01961 = new C01961(this.$enabled, dVar);
                c01961.L$0 = obj;
                return c01961;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01961) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(DeviceTypeKt.getOLED_BLACK_BACKGROUND_KEY(), Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15591(boolean z, d7.d<? super C15591> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15591(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01961 c01961 = new C01961(this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01961, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, this.$enabled, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1073737727, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15591) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSecondarySubtitle$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSecondarySubtitle$1", f = "SettingsViewModel.kt", l = {1191}, m = "invokeSuspend", v = 2)
    public static final class C15601 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $language;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSecondarySubtitle$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSecondarySubtitle$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01971 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ String $language;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01971(SettingsViewModel settingsViewModel, String str, d7.d<? super C01971> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$language = str;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01971 c01971 = new C01971(this.this$0, this.$language, dVar);
                c01971.L$0 = obj;
                return c01971;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01971) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.secondarySubtitleKey(), this.$language);
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15601(String str, d7.d<? super C15601> dVar) {
            super(2, dVar);
            this.$language = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15601(this.$language, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01971 c01971 = new C01971(SettingsViewModel.this, this.$language, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01971, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, this.$language, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -262145, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15601) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setShowBudget$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setShowBudget$1", f = "SettingsViewModel.kt", l = {1380}, m = "invokeSuspend", v = 2)
    public static final class C15611 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setShowBudget$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setShowBudget$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01981 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01981(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C01981> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01981 c01981 = new C01981(this.this$0, this.$enabled, dVar);
                c01981.L$0 = obj;
                return c01981;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01981) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.showBudgetKey(), Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15611(boolean z, d7.d<? super C15611> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15611(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01981 c01981 = new C01981(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01981, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, this.$enabled, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -8388609, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15611) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setShowEpisodeRatings$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setShowEpisodeRatings$1", f = "SettingsViewModel.kt", l = {1388}, m = "invokeSuspend", v = 2)
    public static final class C15621 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setShowEpisodeRatings$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setShowEpisodeRatings$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C01991 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01991(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C01991> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C01991 c01991 = new C01991(this.this$0, this.$enabled, dVar);
                c01991.L$0 = obj;
                return c01991;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C01991) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.showEpisodeRatingsKey(), Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15621(boolean z, d7.d<? super C15621> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15621(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C01991 c01991 = new C01991(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c01991, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, this.$enabled, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -16777217, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15621) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setShowLoadingStats$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setShowLoadingStats$1", f = "SettingsViewModel.kt", l = {1404}, m = "invokeSuspend", v = 2)
    public static final class C15631 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setShowLoadingStats$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setShowLoadingStats$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02001 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02001(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C02001> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C02001 c02001 = new C02001(this.this$0, this.$enabled, dVar);
                c02001.L$0 = obj;
                return c02001;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C02001) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.showLoadingStatsKey(), Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15631(boolean z, d7.d<? super C15631> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15631(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C02001 c02001 = new C02001(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c02001, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, this.$enabled, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -67108865, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15631) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSkipProfileSelection$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSkipProfileSelection$1", f = "SettingsViewModel.kt", l = {1285}, m = "invokeSuspend", v = 2)
    public static final class C15641 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $skip;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSkipProfileSelection$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "prefs", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSkipProfileSelection$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02011 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $skip;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02011(boolean z, d7.d<? super C02011> dVar) {
                super(2, dVar);
                this.$skip = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C02011 c02011 = new C02011(this.$skip, dVar);
                c02011.L$0 = obj;
                return c02011;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C02011) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(DeviceTypeKt.getSKIP_PROFILE_SELECTION_KEY(), Boolean.valueOf(this.$skip));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15641(boolean z, d7.d<? super C15641> dVar) {
            super(2, dVar);
            this.$skip = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15641(this.$skip, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C02011 c02011 = new C02011(this.$skip, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c02011, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, this.$skip, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1073739775, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15641) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSmoothScrolling$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSmoothScrolling$1", f = "SettingsViewModel.kt", l = {1396}, m = "invokeSuspend", v = 2)
    public static final class C15651 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSmoothScrolling$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSmoothScrolling$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02021 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02021(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C02021> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C02021 c02021 = new C02021(this.this$0, this.$enabled, dVar);
                c02021.L$0 = obj;
                return c02021;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C02021) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.smoothScrollingKey(), Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15651(boolean z, d7.d<? super C15651> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15651(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C02021 c02021 = new C02021(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c02021, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, this.$enabled, -1, -1, -1, 536870911, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15651) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSpoilerBlurEnabled$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSpoilerBlurEnabled$1", f = "SettingsViewModel.kt", l = {1345}, m = "invokeSuspend", v = 2)
    public static final class C15661 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSpoilerBlurEnabled$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSpoilerBlurEnabled$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02031 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02031(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C02031> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C02031 c02031 = new C02031(this.this$0, this.$enabled, dVar);
                c02031.L$0 = obj;
                return c02031;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C02031) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.spoilerBlurKey(), Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15661(boolean z, d7.d<? super C15661> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15661(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C02031 c02031 = new C02031(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c02031, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, this.$enabled, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1073709055, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15661) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiAutoSelect$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiAutoSelect$1", f = "SettingsViewModel.kt", l = {1499}, m = "invokeSuspend", v = 2)
    public static final class C15671 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiAutoSelect$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiAutoSelect$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02041 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02041(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C02041> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C02041 c02041 = new C02041(this.this$0, this.$enabled, dVar);
                c02041.L$0 = obj;
                return c02041;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C02041) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.subtitleAiAutoSelectKey, Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15671(boolean z, d7.d<? super C15671> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15671(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C02041 c02041 = new C02041(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c02041, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, this.$enabled, false, false, false, null, null, false, null, false, -1, -1, -1, 1071644671, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15671) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiEnabled$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiEnabled$1", f = "SettingsViewModel.kt", l = {1491}, m = "invokeSuspend", v = 2)
    public static final class C15681 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiEnabled$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiEnabled$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02051 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02051(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C02051> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C02051 c02051 = new C02051(this.this$0, this.$enabled, dVar);
                c02051.L$0 = obj;
                return c02051;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C02051) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.subtitleAiEnabledKey, Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15681(boolean z, d7.d<? super C15681> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15681(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C02051 c02051 = new C02051(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c02051, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, this.$enabled, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072693247, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15681) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiFindBestMatch$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiFindBestMatch$1", f = "SettingsViewModel.kt", l = {1507}, m = "invokeSuspend", v = 2)
    public static final class C15691 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiFindBestMatch$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiFindBestMatch$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02061 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02061(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C02061> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C02061 c02061 = new C02061(this.this$0, this.$enabled, dVar);
                c02061.L$0 = obj;
                return c02061;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C02061) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.subtitleAiFindBestMatchKey, Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15691(boolean z, d7.d<? super C15691> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15691(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C02061 c02061 = new C02061(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c02061, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, this.$enabled, false, false, null, null, false, null, false, -1, -1, -1, 1069547519, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15691) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiModel$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiModel$1", f = "SettingsViewModel.kt", l = {1547}, m = "invokeSuspend", v = 2)
    public static final class C15701 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ SubtitleAiModel $model;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiModel$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleAiModel$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02071 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ SubtitleAiModel $model;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02071(SettingsViewModel settingsViewModel, SubtitleAiModel subtitleAiModel, d7.d<? super C02071> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$model = subtitleAiModel;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C02071 c02071 = new C02071(this.this$0, this.$model, dVar);
                c02071.L$0 = obj;
                return c02071;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C02071) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.subtitleAiModelKey, this.$model.name());
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15701(SubtitleAiModel subtitleAiModel, d7.d<? super C15701> dVar) {
            super(2, dVar);
            this.$model = subtitleAiModel;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15701(this.$model, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C02071 c02071 = new C02071(SettingsViewModel.this, this.$model, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c02071, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, this.$model, false, null, false, -1, -1, -1, 1006632959, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15701) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitlePreloadEnabled$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitlePreloadEnabled$1", f = "SettingsViewModel.kt", l = {1515}, m = "invokeSuspend", v = 2)
    public static final class C15711 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitlePreloadEnabled$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitlePreloadEnabled$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02081 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02081(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C02081> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C02081 c02081 = new C02081(this.this$0, this.$enabled, dVar);
                c02081.L$0 = obj;
                return c02081;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C02081) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.subtitlePreloadEnabledKey, Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15711(boolean z, d7.d<? super C15711> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15711(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C02081 c02081 = new C02081(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c02081, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, this.$enabled, false, null, null, false, null, false, -1, -1, -1, 1065353215, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15711) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleRemoveHearingImpaired$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleRemoveHearingImpaired$1", f = "SettingsViewModel.kt", l = {1531}, m = "invokeSuspend", v = 2)
    public static final class C15721 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleRemoveHearingImpaired$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setSubtitleRemoveHearingImpaired$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02091 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02091(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C02091> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C02091 c02091 = new C02091(this.this$0, this.$enabled, dVar);
                c02091.L$0 = obj;
                return c02091;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C02091) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.subtitleRemoveHearingImpairedKey, Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15721(boolean z, d7.d<? super C15721> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15721(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C02091 c02091 = new C02091(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c02091, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, this.$enabled, null, false, -1, -1, -1, 939524095, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15721) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setTorrServerBaseUrl$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setTorrServerBaseUrl$1", f = "SettingsViewModel.kt", l = {2493}, m = "invokeSuspend", v = 2)
    public static final class C15731 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15731(String str, d7.d<? super C15731> dVar) {
            super(2, dVar);
            this.$url = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15731(this.$url, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                StreamRepository streamRepository = SettingsViewModel.this.streamRepository;
                String str = this.$url;
                this.label = 1;
                Object torrServerBaseUrl = streamRepository.setTorrServerBaseUrl(str, this);
                e7.a aVar = e7.a.f15033i;
                if (torrServerBaseUrl == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15731) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrackingReadMode$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrackingReadMode$1", f = "SettingsViewModel.kt", l = {3869, 3870}, m = "invokeSuspend", v = 2)
    public static final class C15741 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ TrackingFeature $feature;
        final /* synthetic */ TrackingReadMode $mode;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15741(TrackingFeature trackingFeature, TrackingReadMode trackingReadMode, d7.d<? super C15741> dVar) {
            super(2, dVar);
            this.$feature = trackingFeature;
            this.$mode = trackingReadMode;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15741(this.$feature, this.$mode, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0042, code lost:
        
            if (r1 == r4) goto L15;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r138) {
            /*
                Method dump skipped, instruction units count: 365
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15741.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15741) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrackingWriteTarget$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrackingWriteTarget$1", f = "SettingsViewModel.kt", l = {3882, 3883}, m = "invokeSuspend", v = 2)
    public static final class C15751 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ SyncProvider $provider;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15751(SyncProvider syncProvider, boolean z, d7.d<? super C15751> dVar) {
            super(2, dVar);
            this.$provider = syncProvider;
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15751(this.$provider, this.$enabled, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0042, code lost:
        
            if (r1 == r4) goto L15;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r138) {
            /*
                Method dump skipped, instruction units count: 373
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15751.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15751) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrailerAutoPlay$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrailerAutoPlay$1", f = "SettingsViewModel.kt", l = {1352}, m = "invokeSuspend", v = 2)
    public static final class C15761 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrailerAutoPlay$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrailerAutoPlay$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02101 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02101(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C02101> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C02101 c02101 = new C02101(this.this$0, this.$enabled, dVar);
                c02101.L$0 = obj;
                return c02101;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C02101) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.trailerAutoPlayKey(), Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15761(boolean z, d7.d<? super C15761> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15761(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C02101 c02101 = new C02101(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c02101, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, this.$enabled, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -524289, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15761) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrailerInCards$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrailerInCards$1", f = "SettingsViewModel.kt", l = {1360}, m = "invokeSuspend", v = 2)
    public static final class C15771 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrailerInCards$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrailerInCards$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02111 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02111(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C02111> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C02111 c02111 = new C02111(this.this$0, this.$enabled, dVar);
                c02111.L$0 = obj;
                return c02111;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C02111) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.trailerInCardsKey(), Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15771(boolean z, d7.d<? super C15771> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15771(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C02111 c02111 = new C02111(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c02111, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, this.$enabled, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -4194305, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15771) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrailerSoundEnabled$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrailerSoundEnabled$1", f = "SettingsViewModel.kt", l = {1356}, m = "invokeSuspend", v = 2)
    public static final class C15781 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $enabled;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrailerSoundEnabled$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$setTrailerSoundEnabled$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02121 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $enabled;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02121(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C02121> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$enabled = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C02121 c02121 = new C02121(this.this$0, this.$enabled, dVar);
                c02121.L$0 = obj;
                return c02121;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C02121) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.trailerSoundEnabledKey(), Boolean.valueOf(this.$enabled));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15781(boolean z, d7.d<? super C15781> dVar) {
            super(2, dVar);
            this.$enabled = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15781(this.$enabled, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C02121 c02121 = new C02121(SettingsViewModel.this, this.$enabled, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c02121, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, this.$enabled, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1048577, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15781) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$startAiKeyServer$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$startAiKeyServer$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class C15791 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public C15791(d7.d<? super C15791> dVar) {
            super(2, dVar);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final x6.t0 invokeSuspend$lambda$0(SettingsViewModel settingsViewModel, String str) {
            ka.m0.p(androidx.lifecycle.z0.h(settingsViewModel), null, 0, new SettingsViewModel$startAiKeyServer$1$server$1$1(settingsViewModel, str, null), 3);
            return x6.t0.f22605a;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C15791 c15791 = SettingsViewModel.this.new C15791(dVar);
            c15791.L$0 = obj;
            return c15791;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object c0Var;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            SettingsViewModel.this.stopAiKeyServerInternal();
            AiKeyConfigServer aiKeyConfigServerStartOnAvailablePort$default = AiKeyConfigServer.Companion.startOnAvailablePort$default(AiKeyConfigServer.INSTANCE, new j4(SettingsViewModel.this, 1), null, 0, 0, null, 30, null);
            x6.t0 t0Var = x6.t0.f22605a;
            if (aiKeyConfigServerStartOnAvailablePort$default == null) {
                return t0Var;
            }
            SettingsViewModel.this.aiKeyServer = aiKeyConfigServerStartOnAvailablePort$default;
            String str = DeviceIpAddress.INSTANCE.get(SettingsViewModel.this.context);
            if (str == null) {
                str = "device-ip";
            }
            int listeningPort = aiKeyConfigServerStartOnAvailablePort$default.getListeningPort();
            String token = aiKeyConfigServerStartOnAvailablePort$default.getToken();
            StringBuilder sbQ = androidx.fragment.app.a2.q("http://", str, ":", listeningPort, "?t=");
            sbQ.append(token);
            String string = sbQ.toString();
            try {
                c0Var = QrCodeGenerator.INSTANCE.generate(string, 512);
            } catch (Throwable th) {
                c0Var = new x6.c0(th);
            }
            if (c0Var instanceof x6.c0) {
                c0Var = null;
            }
            boolean z = true;
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, new AiKeyServerState(z, string, (Bitmap) c0Var, false, 8, null), false, -1, -1, -1, 805306367, null));
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15791) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$startCloudAuth$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$startCloudAuth$1", f = "SettingsViewModel.kt", l = {2502}, m = "invokeSuspend", v = 2)
    public static final class C15801 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C15801(d7.d<? super C15801> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15801(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object objM6324ensureCloudAuthSessiongIAlus;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, true, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -9, -1, 1073741823, null));
                SettingsViewModel settingsViewModel = SettingsViewModel.this;
                this.label = 1;
                objM6324ensureCloudAuthSessiongIAlus = settingsViewModel.m6324ensureCloudAuthSessiongIAlus(true, this);
                e7.a aVar = e7.a.f15033i;
                if (objM6324ensureCloudAuthSessiongIAlus == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objM6324ensureCloudAuthSessiongIAlus = ((x6.d0) obj).f22580i;
            }
            SettingsViewModel settingsViewModel2 = SettingsViewModel.this;
            if (!(objM6324ensureCloudAuthSessiongIAlus instanceof x6.c0)) {
                settingsViewModel2._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel2._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, true, settingsViewModel2.cloudUserCode, settingsViewModel2.cloudVerificationUrl, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, Integer.MAX_VALUE, -12, -1, 1073741823, null));
            }
            SettingsViewModel settingsViewModel3 = SettingsViewModel.this;
            Throwable thA = x6.d0.a(objM6324ensureCloudAuthSessiongIAlus);
            if (thA != null) {
                SettingsViewModel.clearCloudAuthSession$default(settingsViewModel3, false, 1, null);
                na.q0 q0Var = settingsViewModel3._uiState;
                SettingsUiState settingsUiState = (SettingsUiState) settingsViewModel3._uiState.getValue();
                String message = thA.getMessage();
                if (message == null) {
                    message = settingsViewModel3.context.getString(R.string.cloud_login_failed_start);
                }
                q0Var.setValue(SettingsUiState.copy$default(settingsUiState, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, message, ToastType.ERROR, false, false, false, false, false, null, null, false, null, false, -1, -9, -1, 1072955391, null));
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15801) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$startCloudPolling$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$startCloudPolling$1", f = "SettingsViewModel.kt", l = {2649, 2664, 2671, 2678, 2679, 2739}, m = "invokeSuspend", v = 2)
    public static final class C15811 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $deviceCode;
        long J$0;
        long J$1;
        long J$2;
        long J$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$startCloudPolling$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/ui/screens/settings/SettingsViewModel$CloudRestoreResult;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/ui/screens/settings/SettingsViewModel$CloudRestoreResult;"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$startCloudPolling$1$1", f = "SettingsViewModel.kt", l = {2680}, m = "invokeSuspend", v = 2)
        public static final class C02131 extends f7.j implements r7.p<ka.k0, d7.d<? super CloudRestoreResult>, Object> {
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02131(SettingsViewModel settingsViewModel, d7.d<? super C02131> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                return new C02131(this.this$0, dVar);
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
                SettingsViewModel settingsViewModel = this.this$0;
                this.label = 1;
                Object objRestoreCloudStateToLocalInternal = settingsViewModel.restoreCloudStateToLocalInternal(true, false, this);
                e7.a aVar = e7.a.f15033i;
                return objRestoreCloudStateToLocalInternal == aVar ? aVar : objRestoreCloudStateToLocalInternal;
            }

            @Override // r7.p
            public final Object invoke(ka.k0 k0Var, d7.d<? super CloudRestoreResult> dVar) {
                return ((C02131) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
            }
        }

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$startCloudPolling$1$WhenMappings */
        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                int[] iArr = new int[CloudRestoreResult.values().length];
                try {
                    iArr[CloudRestoreResult.RESTORED.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[CloudRestoreResult.NO_BACKUP.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[CloudRestoreResult.FAILED.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
                int[] iArr2 = new int[TvDeviceAuthStatusType.values().length];
                try {
                    iArr2[TvDeviceAuthStatusType.PENDING.ordinal()] = 1;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr2[TvDeviceAuthStatusType.APPROVED.ordinal()] = 2;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr2[TvDeviceAuthStatusType.EXPIRED.ordinal()] = 3;
                } catch (NoSuchFieldError unused6) {
                }
                try {
                    iArr2[TvDeviceAuthStatusType.ERROR.ordinal()] = 4;
                } catch (NoSuchFieldError unused7) {
                }
                $EnumSwitchMapping$1 = iArr2;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15811(String str, d7.d<? super C15811> dVar) {
            super(2, dVar);
            this.$deviceCode = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15811(this.$deviceCode, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:46:0x02af, code lost:
        
            if (ka.s0.a(r2, r156) == r11) goto L88;
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x05da, code lost:
        
            if (r1 != r11) goto L89;
         */
        /* JADX WARN: Removed duplicated region for block: B:103:0x0621  */
        /* JADX WARN: Removed duplicated region for block: B:106:0x062d  */
        /* JADX WARN: Removed duplicated region for block: B:108:0x0632  */
        /* JADX WARN: Removed duplicated region for block: B:111:0x0741  */
        /* JADX WARN: Removed duplicated region for block: B:125:0x09a8  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0253  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x027e  */
        /* JADX WARN: Removed duplicated region for block: B:35:0x0284  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x0289  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x028c  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x028e  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0298  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x0521  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x056a  */
        /* JADX WARN: Removed duplicated region for block: B:79:0x0591  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x0597  */
        /* JADX WARN: Removed duplicated region for block: B:95:0x060e  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x02af -> B:7:0x0028). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r157) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 2782
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15811.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15811) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$startPlexHomeServerAuth$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$startPlexHomeServerAuth$1", f = "SettingsViewModel.kt", l = {2844}, m = "invokeSuspend", v = 2)
    public static final class C15821 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $displayName;
        final /* synthetic */ String $trimmedUrl;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15821(String str, String str2, d7.d<? super C15821> dVar) {
            super(2, dVar);
            this.$trimmedUrl = str;
            this.$displayName = str2;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15821(this.$trimmedUrl, this.$displayName, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object objM6136startHomeServerCodeAuthgIAlus;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SettingsViewModel.this.cancelPlexHomeServerAuth(false);
                SettingsViewModel.this.plexHomeServerUrl = this.$trimmedUrl;
                SettingsViewModel.this.plexHomeServerDisplayName = kotlin.text.o.L0(this.$displayName).toString();
                SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, true, null, null, false, null, null, false, false, null, null, false, null, null, "Starting code sign in...", ToastType.INFO, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072954911, null));
                HomeServerRepository homeServerRepository = SettingsViewModel.this.homeServerRepository;
                String str = this.$trimmedUrl;
                this.label = 1;
                objM6136startHomeServerCodeAuthgIAlus = homeServerRepository.m6136startHomeServerCodeAuthgIAlus(str, this);
                e7.a aVar = e7.a.f15033i;
                if (objM6136startHomeServerCodeAuthgIAlus == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objM6136startHomeServerCodeAuthgIAlus = ((x6.d0) obj).f22580i;
            }
            SettingsViewModel settingsViewModel = SettingsViewModel.this;
            String str2 = this.$trimmedUrl;
            if (!(objM6136startHomeServerCodeAuthgIAlus instanceof x6.c0)) {
                PlexPinAuthSession plexPinAuthSession = (PlexPinAuthSession) objM6136startHomeServerCodeAuthgIAlus;
                settingsViewModel._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, plexPinAuthSession, true, null, null, false, false, null, null, false, null, null, "Enter the code to connect", ToastType.INFO, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072954911, null));
                settingsViewModel.startPlexHomeServerPolling(str2, plexPinAuthSession);
            }
            SettingsViewModel settingsViewModel2 = SettingsViewModel.this;
            Throwable thA = x6.d0.a(objM6136startHomeServerCodeAuthgIAlus);
            if (thA != null) {
                settingsViewModel2.plexHomeServerUrl = null;
                settingsViewModel2.plexHomeServerDisplayName = null;
                na.q0 q0Var = settingsViewModel2._uiState;
                SettingsUiState settingsUiState = (SettingsUiState) settingsViewModel2._uiState.getValue();
                String message = thA.getMessage();
                if (message == null) {
                    message = settingsViewModel2.context.getString(R.string.homeserver_code_signin_failed);
                }
                String str3 = message;
                String message2 = thA.getMessage();
                if (message2 == null) {
                    message2 = settingsViewModel2.context.getString(R.string.homeserver_code_signin_failed);
                }
                q0Var.setValue(SettingsUiState.copy$default(settingsUiState, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, str3, null, false, null, null, false, false, null, null, false, null, null, message2, ToastType.ERROR, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072954911, null));
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15821) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$startPlexHomeServerPolling$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$startPlexHomeServerPolling$1", f = "SettingsViewModel.kt", l = {2876, 2877, 2896, 2897}, m = "invokeSuspend", v = 2)
    public static final class C15831 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $serverUrl;
        final /* synthetic */ PlexPinAuthSession $session;
        int I$0;
        long J$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        final /* synthetic */ SettingsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15831(PlexPinAuthSession plexPinAuthSession, SettingsViewModel settingsViewModel, String str, d7.d<? super C15831> dVar) {
            super(2, dVar);
            this.$session = plexPinAuthSession;
            this.this$0 = settingsViewModel;
            this.$serverUrl = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C15831 c15831 = new C15831(this.$session, this.this$0, this.$serverUrl, dVar);
            c15831.L$0 = obj;
            return c15831;
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x007b A[PHI: r0 r4 r13 r16
          0x007b: PHI (r0v7 kotlin.jvm.internal.k0) = (r0v16 kotlin.jvm.internal.k0), (r0v32 kotlin.jvm.internal.k0) binds: [B:34:0x00ff, B:21:0x006a] A[DONT_GENERATE, DONT_INLINE]
          0x007b: PHI (r4v4 long) = (r4v8 long), (r4v16 long) binds: [B:34:0x00ff, B:21:0x006a] A[DONT_GENERATE, DONT_INLINE]
          0x007b: PHI (r13v5 java.lang.Object) = (r13v9 java.lang.Object), (r13v24 java.lang.Object) binds: [B:34:0x00ff, B:21:0x006a] A[DONT_GENERATE, DONT_INLINE]
          0x007b: PHI (r16v2 long) = (r16v3 long), (r16v10 long) binds: [B:34:0x00ff, B:21:0x006a] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x00b8  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00f1  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x010a  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0115  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0119  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x0278  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x03b8  */
        /* JADX WARN: Removed duplicated region for block: B:72:0x04f6  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x04fb  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x0115 -> B:25:0x00b0). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x0278 -> B:84:0x027a). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:61:0x03a9 -> B:62:0x03ab). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r154) {
            /*
                Method dump skipped, instruction units count: 1585
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15831.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15831) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$startSimklAuth$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$startSimklAuth$1", f = "SettingsViewModel.kt", l = {3725}, m = "invokeSuspend", v = 2)
    public static final class C15841 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        public C15841(d7.d<? super C15841> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C15841 c15841 = SettingsViewModel.this.new C15841(dVar);
            c15841.L$0 = obj;
            return c15841;
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object c0Var;
            SettingsViewModel settingsViewModel;
            Object objStartPinAuth;
            int i10 = this.label;
            x6.t0 t0Var = x6.t0.f22605a;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, true, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -524289, -1, 1073741823, null));
                    settingsViewModel = SettingsViewModel.this;
                    SimklAuthManager simklAuthManager = settingsViewModel.simklAuthManager;
                    this.L$0 = null;
                    this.L$1 = settingsViewModel;
                    this.L$2 = null;
                    this.I$0 = 0;
                    this.label = 1;
                    objStartPinAuth = simklAuthManager.startPinAuth(this);
                    e7.a aVar = e7.a.f15033i;
                    if (objStartPinAuth == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    settingsViewModel = (SettingsViewModel) this.L$1;
                    k2.c.G(obj);
                    objStartPinAuth = obj;
                }
                SimklPinResponse simklPinResponse = (SimklPinResponse) objStartPinAuth;
                settingsViewModel._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, true, simklPinResponse.getUserCode(), simklPinResponse.getVerificationUrl(), null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -7864321, -1, 1073741823, null));
                settingsViewModel.startSimklPolling(simklPinResponse.getUserCode(), simklPinResponse.getExpiresIn(), simklPinResponse.getInterval());
                c0Var = t0Var;
            } catch (Throwable th) {
                c0Var = new x6.c0(th);
            }
            SettingsViewModel settingsViewModel2 = SettingsViewModel.this;
            Throwable thA = x6.d0.a(c0Var);
            if (thA != null) {
                if (thA instanceof CancellationException) {
                    throw thA;
                }
                settingsViewModel2._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel2._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, androidx.compose.material3.d.C("Simkl Auth Error: ", thA.getMessage()), ToastType.ERROR, false, false, false, false, false, null, null, false, null, false, -1, -7864321, -1, 1072955391, null));
            }
            return t0Var;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15841) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$startSimklPolling$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$startSimklPolling$1", f = "SettingsViewModel.kt", l = {3754, 3756, 3758, 3759, 3760, 3784}, m = "invokeSuspend", v = 2)
    public static final class C15851 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ int $expiresInSec;
        final /* synthetic */ int $intervalSec;
        final /* synthetic */ String $userCode;
        int I$0;
        long J$0;
        long J$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        boolean Z$1;
        int label;
        final /* synthetic */ SettingsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15851(int i10, int i11, SettingsViewModel settingsViewModel, String str, d7.d<? super C15851> dVar) {
            super(2, dVar);
            this.$expiresInSec = i10;
            this.$intervalSec = i11;
            this.this$0 = settingsViewModel;
            this.$userCode = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C15851 c15851 = new C15851(this.$expiresInSec, this.$intervalSec, this.this$0, this.$userCode, dVar);
            c15851.L$0 = obj;
            return c15851;
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x005f A[PHI: r0 r4 r9 r11
          0x005f: PHI (r0v12 java.lang.Object) = (r0v7 java.lang.Object), (r0v30 java.lang.Object) binds: [B:36:0x00b2, B:22:0x005d] A[DONT_GENERATE, DONT_INLINE]
          0x005f: PHI (r4v8 int) = (r4v4 int), (r4v0 int) binds: [B:36:0x00b2, B:22:0x005d] A[DONT_GENERATE, DONT_INLINE]
          0x005f: PHI (r9v3 long) = (r9v1 long), (r9v8 long) binds: [B:36:0x00b2, B:22:0x005d] A[DONT_GENERATE, DONT_INLINE]
          0x005f: PHI (r11v3 long) = (r11v1 long), (r11v8 long) binds: [B:36:0x00b2, B:22:0x005d] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0081 A[PHI: r4 r8 r10
          0x0081: PHI (r4v5 int) = (r4v3 int), (r4v8 int), (r4v0 int) binds: [B:59:0x0284, B:39:0x00bc, B:29:0x007f] A[DONT_GENERATE, DONT_INLINE]
          0x0081: PHI (r8v4 long) = (r8v1 long), (r8v6 long), (r8v17 long) binds: [B:59:0x0284, B:39:0x00bc, B:29:0x007f] A[DONT_GENERATE, DONT_INLINE]
          0x0081: PHI (r10v3 long) = (r10v1 long), (r10v5 long), (r10v8 long) binds: [B:59:0x0284, B:39:0x00bc, B:29:0x007f] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0089  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00be A[Catch: Exception -> 0x027f, TRY_LEAVE, TryCatch #0 {Exception -> 0x027f, blocks: (B:38:0x00b6, B:40:0x00be), top: B:64:0x00b6 }] */
        /* JADX WARN: Removed duplicated region for block: B:46:0x00ef  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x00f1 A[Catch: Exception -> 0x0039, PHI: r0 r8 r9 r11
          0x00f1: PHI (r0v18 boolean) = (r0v17 boolean), (r0v32 boolean) binds: [B:45:0x00ed, B:16:0x0047] A[DONT_GENERATE, DONT_INLINE]
          0x00f1: PHI (r8v10 java.lang.Object) = (r8v9 java.lang.Object), (r8v19 java.lang.Object) binds: [B:45:0x00ed, B:16:0x0047] A[DONT_GENERATE, DONT_INLINE]
          0x00f1: PHI (r9v6 long) = (r9v5 long), (r9v10 long) binds: [B:45:0x00ed, B:16:0x0047] A[DONT_GENERATE, DONT_INLINE]
          0x00f1: PHI (r11v6 long) = (r11v5 long), (r11v10 long) binds: [B:45:0x00ed, B:16:0x0047] A[DONT_GENERATE, DONT_INLINE], TryCatch #1 {Exception -> 0x0039, blocks: (B:10:0x0030, B:51:0x0115, B:35:0x009d, B:44:0x00d8, B:47:0x00f1, B:15:0x0044, B:18:0x0051, B:21:0x005a), top: B:66:0x0010 }] */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0110  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x0112  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x029e  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x00bc -> B:30:0x0081). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x0112 -> B:51:0x0115). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:56:0x027f
            	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
            	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
            	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
            */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r147) {
            /*
                Method dump skipped, instruction units count: 968
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15851.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15851) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$startTraktAuth$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$startTraktAuth$1", f = "SettingsViewModel.kt", l = {3443, 3444}, m = "invokeSuspend", v = 2)
    public static final class C15861 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int label;

        public C15861(d7.d<? super C15861> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15861(dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x0160, code lost:
        
            if (r0 == r5) goto L20;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r140) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 975
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15861.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15861) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$startTraktPolling$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$startTraktPolling$1", f = "SettingsViewModel.kt", l = {3498, 3501, 3504, 3510, 3511, 3512, 3513, 3542, 3545}, m = "invokeSuspend", v = 2)
    public static final class C15871 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ TraktDeviceCode $deviceCode;
        int I$0;
        long J$0;
        long J$1;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        final /* synthetic */ SettingsViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15871(TraktDeviceCode traktDeviceCode, SettingsViewModel settingsViewModel, d7.d<? super C15871> dVar) {
            super(2, dVar);
            this.$deviceCode = traktDeviceCode;
            this.this$0 = settingsViewModel;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C15871 c15871 = new C15871(this.$deviceCode, this.this$0, dVar);
            c15871.L$0 = obj;
            return c15871;
        }

        /* JADX WARN: Code restructure failed: missing block: B:155:0x042e, code lost:
        
            if (r9 == null) goto L156;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x0359, code lost:
        
            if (r0.refreshForCurrentProfile(r152) == r10) goto L82;
         */
        /* JADX WARN: Not initialized variable reg: 11, insn: 0x006a: MOVE (r13 I:??[long, double]) = (r11 I:??[long, double]) (LINE:107), block:B:18:0x0068 */
        /* JADX WARN: Not initialized variable reg: 13, insn: 0x0068: MOVE (r16 I:??[long, double]) = (r13 I:??[long, double]) (LINE:105), block:B:18:0x0068 */
        /* JADX WARN: Removed duplicated region for block: B:158:0x043e  */
        /* JADX WARN: Removed duplicated region for block: B:161:0x0454  */
        /* JADX WARN: Removed duplicated region for block: B:162:0x0457  */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0061 A[PHI: r0 r5 r11 r13 r15 r16
          0x0061: PHI (r0v6 boolean) = (r0v16 boolean), (r0v72 boolean) binds: [B:69:0x01ba, B:15:0x005d] A[DONT_GENERATE, DONT_INLINE]
          0x0061: PHI (r5v1 java.lang.Object) = (r5v12 java.lang.Object), (r5v45 java.lang.Object) binds: [B:69:0x01ba, B:15:0x005d] A[DONT_GENERATE, DONT_INLINE]
          0x0061: PHI (r11v4 long) = (r11v5 long), (r11v27 long) binds: [B:69:0x01ba, B:15:0x005d] A[DONT_GENERATE, DONT_INLINE]
          0x0061: PHI (r13v4 long) = (r13v5 long), (r13v32 long) binds: [B:69:0x01ba, B:15:0x005d] A[DONT_GENERATE, DONT_INLINE]
          0x0061: PHI (r15v1 java.lang.String) = (r15v3 java.lang.String), (r15v13 java.lang.String) binds: [B:69:0x01ba, B:15:0x005d] A[DONT_GENERATE, DONT_INLINE]
          0x0061: PHI (r16v4 long) = (r16v5 long), (r16v22 long) binds: [B:69:0x01ba, B:15:0x005d] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0080 A[PHI: r0 r5 r11 r13 r16
          0x0080: PHI (r0v15 java.lang.String) = (r0v17 java.lang.String), (r0v71 java.lang.String) binds: [B:66:0x0199, B:21:0x007c] A[DONT_GENERATE, DONT_INLINE]
          0x0080: PHI (r5v8 java.lang.Object) = (r5v15 java.lang.Object), (r5v44 java.lang.Object) binds: [B:66:0x0199, B:21:0x007c] A[DONT_GENERATE, DONT_INLINE]
          0x0080: PHI (r11v5 long) = (r11v6 long), (r11v26 long) binds: [B:66:0x0199, B:21:0x007c] A[DONT_GENERATE, DONT_INLINE]
          0x0080: PHI (r13v5 long) = (r13v6 long), (r13v31 long) binds: [B:66:0x0199, B:21:0x007c] A[DONT_GENERATE, DONT_INLINE]
          0x0080: PHI (r16v5 long) = (r16v6 long), (r16v21 long) binds: [B:66:0x0199, B:21:0x007c] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00e4  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x011c  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x011e A[Catch: Exception -> 0x0143, PHI: r11 r13 r16
          0x011e: PHI (r11v9 long) = (r11v10 long), (r11v22 long) binds: [B:49:0x011a, B:34:0x00b5] A[DONT_GENERATE, DONT_INLINE]
          0x011e: PHI (r13v9 long) = (r13v10 long), (r13v27 long) binds: [B:49:0x011a, B:34:0x00b5] A[DONT_GENERATE, DONT_INLINE]
          0x011e: PHI (r16v9 long) = (r16v11 long), (r16v17 long) binds: [B:49:0x011a, B:34:0x00b5] A[DONT_GENERATE, DONT_INLINE], TryCatch #4 {Exception -> 0x0143, blocks: (B:79:0x0331, B:71:0x01be, B:48:0x0114, B:51:0x011e, B:54:0x0135, B:56:0x013f, B:59:0x014b, B:62:0x0169, B:65:0x0184, B:68:0x019d), top: B:173:0x0114 }] */
        /* JADX WARN: Removed duplicated region for block: B:53:0x0133  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0135 A[Catch: Exception -> 0x0143, PHI: r0 r11 r13 r16
          0x0135: PHI (r0v19 java.lang.Object) = (r0v23 java.lang.Object), (r0v65 java.lang.Object) binds: [B:52:0x0131, B:31:0x00a8] A[DONT_GENERATE, DONT_INLINE]
          0x0135: PHI (r11v8 long) = (r11v9 long), (r11v23 long) binds: [B:52:0x0131, B:31:0x00a8] A[DONT_GENERATE, DONT_INLINE]
          0x0135: PHI (r13v8 long) = (r13v9 long), (r13v28 long) binds: [B:52:0x0131, B:31:0x00a8] A[DONT_GENERATE, DONT_INLINE]
          0x0135: PHI (r16v8 long) = (r16v9 long), (r16v18 long) binds: [B:52:0x0131, B:31:0x00a8] A[DONT_GENERATE, DONT_INLINE], TryCatch #4 {Exception -> 0x0143, blocks: (B:79:0x0331, B:71:0x01be, B:48:0x0114, B:51:0x011e, B:54:0x0135, B:56:0x013f, B:59:0x014b, B:62:0x0169, B:65:0x0184, B:68:0x019d), top: B:173:0x0114 }] */
        /* JADX WARN: Removed duplicated region for block: B:56:0x013f A[Catch: Exception -> 0x0143, TryCatch #4 {Exception -> 0x0143, blocks: (B:79:0x0331, B:71:0x01be, B:48:0x0114, B:51:0x011e, B:54:0x0135, B:56:0x013f, B:59:0x014b, B:62:0x0169, B:65:0x0184, B:68:0x019d), top: B:173:0x0114 }] */
        /* JADX WARN: Removed duplicated region for block: B:61:0x0167  */
        /* JADX WARN: Removed duplicated region for block: B:62:0x0169 A[Catch: Exception -> 0x0143, PHI: r0 r11 r13 r16
          0x0169: PHI (r0v18 java.lang.String) = (r0v20 java.lang.String), (r0v67 java.lang.String) binds: [B:60:0x0165, B:28:0x009d] A[DONT_GENERATE, DONT_INLINE]
          0x0169: PHI (r11v7 long) = (r11v8 long), (r11v24 long) binds: [B:60:0x0165, B:28:0x009d] A[DONT_GENERATE, DONT_INLINE]
          0x0169: PHI (r13v7 long) = (r13v8 long), (r13v29 long) binds: [B:60:0x0165, B:28:0x009d] A[DONT_GENERATE, DONT_INLINE]
          0x0169: PHI (r16v7 long) = (r16v8 long), (r16v19 long) binds: [B:60:0x0165, B:28:0x009d] A[DONT_GENERATE, DONT_INLINE], TryCatch #4 {Exception -> 0x0143, blocks: (B:79:0x0331, B:71:0x01be, B:48:0x0114, B:51:0x011e, B:54:0x0135, B:56:0x013f, B:59:0x014b, B:62:0x0169, B:65:0x0184, B:68:0x019d), top: B:173:0x0114 }] */
        /* JADX WARN: Removed duplicated region for block: B:64:0x0182  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x0184 A[Catch: Exception -> 0x0143, PHI: r0 r11 r13 r16
          0x0184: PHI (r0v17 java.lang.String) = (r0v18 java.lang.String), (r0v69 java.lang.String) binds: [B:63:0x0180, B:25:0x008e] A[DONT_GENERATE, DONT_INLINE]
          0x0184: PHI (r11v6 long) = (r11v7 long), (r11v25 long) binds: [B:63:0x0180, B:25:0x008e] A[DONT_GENERATE, DONT_INLINE]
          0x0184: PHI (r13v6 long) = (r13v7 long), (r13v30 long) binds: [B:63:0x0180, B:25:0x008e] A[DONT_GENERATE, DONT_INLINE]
          0x0184: PHI (r16v6 long) = (r16v7 long), (r16v20 long) binds: [B:63:0x0180, B:25:0x008e] A[DONT_GENERATE, DONT_INLINE], TryCatch #4 {Exception -> 0x0143, blocks: (B:79:0x0331, B:71:0x01be, B:48:0x0114, B:51:0x011e, B:54:0x0135, B:56:0x013f, B:59:0x014b, B:62:0x0169, B:65:0x0184, B:68:0x019d), top: B:173:0x0114 }] */
        /* JADX WARN: Removed duplicated region for block: B:67:0x019b  */
        /* JADX WARN: Removed duplicated region for block: B:70:0x01bc  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x032b  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x0366  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:173:0x0114 -> B:57:0x0143). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:73:0x0329 -> B:175:0x032c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:85:0x035e -> B:58:0x0144). Please report as a decompilation issue!!! */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r153) throws java.lang.Exception {
            /*
                Method dump skipped, instruction units count: 1402
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15871.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15871) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$syncCloudStateToLocal$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$syncCloudStateToLocal$1", f = "SettingsViewModel.kt", l = {3042}, m = "invokeSuspend", v = 2)
    public static final class C15881 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $silent;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15881(boolean z, d7.d<? super C15881> dVar) {
            super(2, dVar);
            this.$silent = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15881(this.$silent, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                SettingsViewModel settingsViewModel = SettingsViewModel.this;
                boolean z = this.$silent;
                this.label = 1;
                Object objRestoreCloudStateToLocalInternal$default = SettingsViewModel.restoreCloudStateToLocalInternal$default(settingsViewModel, z, false, this, 2, null);
                e7.a aVar = e7.a.f15033i;
                if (objRestoreCloudStateToLocalInternal$default == aVar) {
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
            return ((C15881) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$syncHomeServerCatalogsFromConnections$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel", f = "SettingsViewModel.kt", l = {3003, 3004}, m = "syncHomeServerCatalogsFromConnections", v = 2)
    public static final class C15891 extends f7.c {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C15891(d7.d<? super C15891> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SettingsViewModel.this.syncHomeServerCatalogsFromConnections(this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$syncLocalStateToCloud$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$syncLocalStateToCloud$1", f = "SettingsViewModel.kt", l = {3010, 3012, 3017, 3019, 3021, 3022}, m = "invokeSuspend", v = 2)
    public static final class C15901 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $force;
        final /* synthetic */ boolean $silent;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15901(boolean z, boolean z5, d7.d<? super C15901> dVar) {
            super(2, dVar);
            this.$force = z;
            this.$silent = z5;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15901(this.$force, this.$silent, dVar);
        }

        /* JADX WARN: Code restructure failed: missing block: B:38:0x00b9, code lost:
        
            if (r1 != r4) goto L40;
         */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0057  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0078  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0084  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0096 A[PHI: r1
          0x0096: PHI (r1v19 java.lang.Object) = (r1v18 java.lang.Object), (r1v24 java.lang.Object) binds: [B:30:0x0093, B:8:0x0024] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x009a  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00c0  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x01dd  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x0204  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r138) {
            /*
                Method dump skipped, instruction units count: 806
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15901.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15901) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$testHomeServerConnection$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$testHomeServerConnection$1", f = "SettingsViewModel.kt", l = {2961, 2963}, m = "invokeSuspend", v = 2)
    public static final class C15911 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        public C15911(d7.d<? super C15911> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15911(dVar);
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x02a7  */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r143) {
            /*
                Method dump skipped, instruction units count: 989
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15911.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15911) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$toggleAddon$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$toggleAddon$1", f = "SettingsViewModel.kt", l = {1798, 1799, 1801}, m = "invokeSuspend", v = 2)
    public static final class C15921 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $addonId;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15921(String str, d7.d<? super C15921> dVar) {
            super(2, dVar);
            this.$addonId = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            C15921 c15921 = SettingsViewModel.this.new C15921(this.$addonId, dVar);
            c15921.L$0 = obj;
            return c15921;
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x0072, code lost:
        
            if (r9 != r7) goto L24;
         */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = r8.L$0
                ka.k0 r0 = (ka.k0) r0
                int r1 = r8.label
                r2 = 0
                r3 = 3
                r4 = 2
                r5 = 1
                r6 = 0
                e7.a r7 = e7.a.f15033i
                if (r1 == 0) goto L31
                if (r1 == r5) goto L2d
                if (r1 == r4) goto L29
                if (r1 != r3) goto L21
                java.lang.Object r0 = r8.L$2
                ka.k0 r0 = (ka.k0) r0
                java.lang.Object r0 = r8.L$1
                java.util.List r0 = (java.util.List) r0
                k2.c.G(r9)     // Catch: java.lang.Throwable -> L7a
                goto L75
            L21:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L29:
                k2.c.G(r9)
                goto L5c
            L2d:
                k2.c.G(r9)
                goto L47
            L31:
                k2.c.G(r9)
                com.arflix.tv.ui.screens.settings.SettingsViewModel r9 = com.arflix.tv.ui.screens.settings.SettingsViewModel.this
                com.arflix.tv.data.repository.StreamRepository r9 = com.arflix.tv.ui.screens.settings.SettingsViewModel.access$getStreamRepository$p(r9)
                java.lang.String r1 = r8.$addonId
                r8.L$0 = r0
                r8.label = r5
                java.lang.Object r9 = r9.toggleAddon(r1, r8)
                if (r9 != r7) goto L47
                goto L74
            L47:
                com.arflix.tv.ui.screens.settings.SettingsViewModel r9 = com.arflix.tv.ui.screens.settings.SettingsViewModel.this
                com.arflix.tv.data.repository.StreamRepository r9 = com.arflix.tv.ui.screens.settings.SettingsViewModel.access$getStreamRepository$p(r9)
                na.j r9 = r9.getInstalledAddons()
                r8.L$0 = r0
                r8.label = r4
                java.lang.Object r9 = na.y0.l(r9, r8)
                if (r9 != r7) goto L5c
                goto L74
            L5c:
                java.util.List r9 = (java.util.List) r9
                com.arflix.tv.ui.screens.settings.SettingsViewModel r0 = com.arflix.tv.ui.screens.settings.SettingsViewModel.this
                com.arflix.tv.data.repository.CatalogRepository r0 = com.arflix.tv.ui.screens.settings.SettingsViewModel.access$getCatalogRepository$p(r0)     // Catch: java.lang.Throwable -> L7a
                r8.L$0 = r6     // Catch: java.lang.Throwable -> L7a
                r8.L$1 = r6     // Catch: java.lang.Throwable -> L7a
                r8.L$2 = r6     // Catch: java.lang.Throwable -> L7a
                r8.I$0 = r2     // Catch: java.lang.Throwable -> L7a
                r8.label = r3     // Catch: java.lang.Throwable -> L7a
                java.lang.Object r9 = r0.syncAddonCatalogs(r9, r8)     // Catch: java.lang.Throwable -> L7a
                if (r9 != r7) goto L75
            L74:
                return r7
            L75:
                java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L7a
                r9.booleanValue()     // Catch: java.lang.Throwable -> L7a
            L7a:
                com.arflix.tv.ui.screens.settings.SettingsViewModel r9 = com.arflix.tv.ui.screens.settings.SettingsViewModel.this
                com.arflix.tv.ui.screens.settings.SettingsViewModel.syncLocalStateToCloud$default(r9, r5, r2, r4, r6)
                x6.t0 r9 = x6.t0.f22605a
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.C15921.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15921) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$toggleIptvHiddenGroup$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$toggleIptvHiddenGroup$1", f = "SettingsViewModel.kt", l = {910}, m = "invokeSuspend", v = 2)
    public static final class C15931 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $groupName;
        final /* synthetic */ String $playlistId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15931(String str, String str2, d7.d<? super C15931> dVar) {
            super(2, dVar);
            this.$playlistId = str;
            this.$groupName = str2;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15931(this.$playlistId, this.$groupName, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                IptvRepository iptvRepository = SettingsViewModel.this.iptvRepository;
                String str = this.$playlistId;
                String str2 = this.$groupName;
                this.label = 1;
                Object obj2 = iptvRepository.toggleHiddenGroup(str, str2, this);
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
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15931) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$toggleQualityFilter$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$toggleQualityFilter$1", f = "SettingsViewModel.kt", l = {1756}, m = "invokeSuspend", v = 2)
    public static final class C15941 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $filterId;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15941(String str, d7.d<? super C15941> dVar) {
            super(2, dVar);
            this.$filterId = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15941(this.$filterId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                List<QualityFilterConfig> qualityFilters = ((SettingsUiState) SettingsViewModel.this._uiState.getValue()).getQualityFilters();
                String str = this.$filterId;
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(qualityFilters, 10));
                for (QualityFilterConfig qualityFilterConfigCopy$default : qualityFilters) {
                    if (kotlin.jvm.internal.p.a(qualityFilterConfigCopy$default.getId(), str)) {
                        qualityFilterConfigCopy$default = QualityFilterConfig.copy$default(qualityFilterConfigCopy$default, null, null, null, !qualityFilterConfigCopy$default.getEnabled(), 0L, 23, null);
                    }
                    arrayList.add(qualityFilterConfigCopy$default);
                }
                SettingsViewModel settingsViewModel = SettingsViewModel.this;
                this.L$0 = null;
                this.label = 1;
                Object objSaveQualityFilters = settingsViewModel.saveQualityFilters(arrayList, this);
                e7.a aVar = e7.a.f15033i;
                if (objSaveQualityFilters == aVar) {
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
            return ((C15941) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$toggleSubtitleStylized$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$toggleSubtitleStylized$1", f = "SettingsViewModel.kt", l = {1481}, m = "invokeSuspend", v = 2)
    public static final class C15951 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ boolean $next;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$toggleSubtitleStylized$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$toggleSubtitleStylized$1$1", f = "SettingsViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
        public static final class C02141 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
            final /* synthetic */ boolean $next;
            /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ SettingsViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C02141(SettingsViewModel settingsViewModel, boolean z, d7.d<? super C02141> dVar) {
                super(2, dVar);
                this.this$0 = settingsViewModel;
                this.$next = z;
            }

            @Override // f7.a
            public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
                C02141 c02141 = new C02141(this.this$0, this.$next, dVar);
                c02141.L$0 = obj;
                return c02141;
            }

            @Override // r7.p
            public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
                return ((C02141) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                c1.b bVar = (c1.b) this.L$0;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                bVar.d(this.this$0.subtitleStylizedKey(), Boolean.valueOf(this.$next));
                return x6.t0.f22605a;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15951(boolean z, d7.d<? super C15951> dVar) {
            super(2, dVar);
            this.$next = z;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15951(this.$next, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                z0.g<c1.g> settingsDataStore = DataStoresKt.getSettingsDataStore(SettingsViewModel.this.context);
                C02141 c02141 = new C02141(SettingsViewModel.this, this.$next, null);
                this.label = 1;
                Object objO = androidx.work.impl.t.o(settingsDataStore, c02141, this);
                e7.a aVar = e7.a.f15033i;
                if (objO == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, this.$next, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -65537, -1, -1, 1073741823, null));
            SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15951) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$unpackCatalog$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$unpackCatalog$1", f = "SettingsViewModel.kt", l = {2228, 2235}, m = "invokeSuspend", v = 2)
    public static final class C15961 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $catalogId;
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15961(String str, d7.d<? super C15961> dVar) {
            super(2, dVar);
            this.$catalogId = str;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15961(this.$catalogId, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object catalogs;
            List list;
            int i10 = this.label;
            e7.a aVar = e7.a.f15033i;
            if (i10 == 0) {
                k2.c.G(obj);
                CatalogRepository catalogRepository = SettingsViewModel.this.catalogRepository;
                this.label = 1;
                catalogs = catalogRepository.getCatalogs(this);
                if (catalogs != aVar) {
                }
                return aVar;
            }
            if (i10 != 1) {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                list = (List) this.L$2;
                k2.c.G(obj);
                SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, SettingsViewModel.this.visibleCatalogs(list), null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, "Catalog row extracted from pack", ToastType.SUCCESS, false, false, false, false, false, null, null, false, null, false, -1, -1, -8388609, 1072955391, null));
                SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
                return x6.t0.f22605a;
            }
            k2.c.G(obj);
            catalogs = obj;
            List list2 = (List) catalogs;
            String str = this.$catalogId;
            Iterator it = list2.iterator();
            int i11 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i11 = -1;
                    break;
                }
                if (kotlin.jvm.internal.p.a(((CatalogConfig) it.next()).getId(), str)) {
                    break;
                }
                i11++;
            }
            if (i11 != -1) {
                CatalogConfig catalogConfig = (CatalogConfig) list2.get(i11);
                if (catalogConfig.getPackId() != null) {
                    ArrayList arrayList = new ArrayList(list2);
                    arrayList.set(i11, CatalogConfig.copy$default(catalogConfig, null, null, null, null, null, false, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, 8388607, null));
                    CatalogRepository catalogRepository2 = SettingsViewModel.this.catalogRepository;
                    this.L$0 = null;
                    this.L$1 = null;
                    this.L$2 = arrayList;
                    this.I$0 = i11;
                    this.label = 2;
                    if (catalogRepository2.replaceCatalogsForActiveProfile(arrayList, this) != aVar) {
                        list = arrayList;
                        SettingsViewModel.this._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) SettingsViewModel.this._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, SettingsViewModel.this.visibleCatalogs(list), null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, "Catalog row extracted from pack", ToastType.SUCCESS, false, false, false, false, false, null, null, false, null, false, -1, -1, -8388609, 1072955391, null));
                        SettingsViewModel.syncLocalStateToCloud$default(SettingsViewModel.this, true, false, 2, null);
                    }
                    return aVar;
                }
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15961) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$updateCatalog$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$updateCatalog$1", f = "SettingsViewModel.kt", l = {2189}, m = "invokeSuspend", v = 2)
    public static final class C15971 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $catalogId;
        final /* synthetic */ String $url;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15971(String str, String str2, d7.d<? super C15971> dVar) {
            super(2, dVar);
            this.$catalogId = str;
            this.$url = str2;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15971(this.$catalogId, this.$url, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            Object objM6119updateCustomCatalog0E7RQCE;
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                CatalogRepository catalogRepository = SettingsViewModel.this.catalogRepository;
                String str = this.$catalogId;
                String str2 = this.$url;
                this.label = 1;
                objM6119updateCustomCatalog0E7RQCE = catalogRepository.m6119updateCustomCatalog0E7RQCE(str, str2, this);
                e7.a aVar = e7.a.f15033i;
                if (objM6119updateCustomCatalog0E7RQCE == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
                objM6119updateCustomCatalog0E7RQCE = ((x6.d0) obj).f22580i;
            }
            SettingsViewModel settingsViewModel = SettingsViewModel.this;
            if (!(objM6119updateCustomCatalog0E7RQCE instanceof x6.c0)) {
                settingsViewModel._uiState.setValue(SettingsUiState.copy$default((SettingsUiState) settingsViewModel._uiState.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, androidx.compose.material3.d.C("Updated ", ((CatalogConfig) objM6119updateCustomCatalog0E7RQCE).getTitle()), ToastType.SUCCESS, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
                SettingsViewModel.syncLocalStateToCloud$default(settingsViewModel, true, false, 2, null);
            }
            SettingsViewModel settingsViewModel2 = SettingsViewModel.this;
            Throwable thA = x6.d0.a(objM6119updateCustomCatalog0E7RQCE);
            if (thA != null) {
                na.q0 q0Var = settingsViewModel2._uiState;
                SettingsUiState settingsUiState = (SettingsUiState) settingsViewModel2._uiState.getValue();
                String message = thA.getMessage();
                if (message == null) {
                    message = settingsViewModel2.context.getString(R.string.catalog_failed_update);
                }
                q0Var.setValue(SettingsUiState.copy$default(settingsUiState, null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, message, ToastType.ERROR, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
            }
            return x6.t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
            return ((C15971) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.settings.SettingsViewModel$updateQualityFilter$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsViewModel$updateQualityFilter$3", f = "SettingsViewModel.kt", l = {1722}, m = "invokeSuspend", v = 2)
    public static final class C15983 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
        final /* synthetic */ String $deviceName;
        final /* synthetic */ String $filterId;
        final /* synthetic */ String $trimmedRegex;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C15983(String str, String str2, String str3, d7.d<? super C15983> dVar) {
            super(2, dVar);
            this.$filterId = str;
            this.$deviceName = str2;
            this.$trimmedRegex = str3;
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            return SettingsViewModel.this.new C15983(this.$filterId, this.$deviceName, this.$trimmedRegex, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                List<QualityFilterConfig> qualityFilters = ((SettingsUiState) SettingsViewModel.this._uiState.getValue()).getQualityFilters();
                String str = this.$filterId;
                String str2 = this.$deviceName;
                String str3 = this.$trimmedRegex;
                ArrayList arrayList = new ArrayList(kotlin.collections.s.U(qualityFilters, 10));
                for (QualityFilterConfig qualityFilterConfigCopy$default : qualityFilters) {
                    if (kotlin.jvm.internal.p.a(qualityFilterConfigCopy$default.getId(), str)) {
                        qualityFilterConfigCopy$default = QualityFilterConfig.copy$default(qualityFilterConfigCopy$default, null, kotlin.text.o.L0(str2).toString(), str3, false, 0L, 25, null);
                    }
                    arrayList.add(qualityFilterConfigCopy$default);
                }
                SettingsViewModel settingsViewModel = SettingsViewModel.this;
                this.L$0 = null;
                this.label = 1;
                Object objSaveQualityFilters = settingsViewModel.saveQualityFilters(arrayList, this);
                e7.a aVar = e7.a.f15033i;
                if (objSaveQualityFilters == aVar) {
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
            return ((C15983) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    @Inject
    public SettingsViewModel(@ApplicationContext Context context, ProfileManager profileManager, TraktRepository traktRepository, StreamRepository streamRepository, MediaRepository mediaRepository, CatalogRepository catalogRepository, CatalogDiscoveryRepository catalogDiscoveryRepository, IptvRepository iptvRepository, HomeServerRepository homeServerRepository, WatchlistRepository watchlistRepository, AuthRepository authRepository, ProfileRepository profileRepository, TvDeviceAuthRepository tvDeviceAuthRepository, TraktSyncService traktSyncService, CloudSyncRepository cloudSyncRepository, LauncherContinueWatchingRepository launcherContinueWatchingRepository, AppUpdateRepository appUpdateRepository, UpdatePreferences updatePreferences, ApkDownloader apkDownloader, UpdateStatusManager updateStatusManager, MdbListRepository mdbListRepository, SyncProviderStore syncProviderStore, WatchHistoryRepository watchHistoryRepository, SimklAuthManager simklAuthManager) {
        this.context = context;
        this.profileManager = profileManager;
        this.traktRepository = traktRepository;
        this.streamRepository = streamRepository;
        this.mediaRepository = mediaRepository;
        this.catalogRepository = catalogRepository;
        this.catalogDiscoveryRepository = catalogDiscoveryRepository;
        this.iptvRepository = iptvRepository;
        this.homeServerRepository = homeServerRepository;
        this.watchlistRepository = watchlistRepository;
        this.authRepository = authRepository;
        this.profileRepository = profileRepository;
        this.tvDeviceAuthRepository = tvDeviceAuthRepository;
        this.traktSyncService = traktSyncService;
        this.cloudSyncRepository = cloudSyncRepository;
        this.launcherContinueWatchingRepository = launcherContinueWatchingRepository;
        this.appUpdateRepository = appUpdateRepository;
        this.updatePreferences = updatePreferences;
        this.apkDownloader = apkDownloader;
        this.updateStatusManager = updateStatusManager;
        this.mdbListRepository = mdbListRepository;
        this.syncProviderStore = syncProviderStore;
        this.watchHistoryRepository = watchHistoryRepository;
        this.simklAuthManager = simklAuthManager;
        na.j1 j1VarB = na.y0.b(new SettingsUiState(null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1073741823, null));
        this._uiState = j1VarB;
        this.uiState = na.y0.e(j1VarB);
        this.dnsProviderKey = new c1.e(OkHttpProvider.DNS_PROVIDER_PREF_KEY);
        this.customUserAgentKey = new c1.e(OkHttpProvider.USER_AGENT_PREF_KEY);
        this.qualityFiltersKey = new c1.e("quality_filters");
        this.subtitleAiEnabledKey = new c1.e("subtitle_ai_enabled");
        this.subtitleAiAutoSelectKey = new c1.e("subtitle_ai_auto_select");
        this.subtitleAiFindBestMatchKey = new c1.e("subtitle_ai_find_best_match");
        this.subtitlePreloadEnabledKey = new c1.e("subtitle_preload_enabled");
        this.dolbyVisionCompatKey = new c1.e("dolby_vision_compat");
        this.subtitleAiApiKeyKey = new c1.e("subtitle_ai_api_key");
        this.subtitleAiModelKey = new c1.e("subtitle_ai_model");
        this.subtitleRemoveHearingImpairedKey = new c1.e("subtitle_remove_hearing_impaired");
        this.gson = new Gson();
        this.lastObservedIptvM3u = "";
        this.lastObservedStalkerUrl = "";
        this.cloudPollIntervalMs = 800L;
        j1VarB.setValue(SettingsUiState.copy$default((SettingsUiState) j1VarB.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, DiagnosticsManager.INSTANCE.isReportingEnabled(context), false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -134217729, -1, -1, 1073741823, null));
        loadSettings();
        observeProfileChanges();
        observeAddons();
        observeTorrServer();
        observeHomeServer();
        observeSyncState();
        observeAuthState();
        observeIptvConfig();
        observeIptvGroupPrefs();
        initializeCatalogs();
        observeCatalogs();
        initializeUpdaterState();
        checkForAppUpdates(false, false);
    }

    private final void applyCloudAuthSession(TvDeviceAuthSession session) {
        this.cloudDeviceCode = session.getDeviceCode();
        this.cloudUserCode = session.getUserCode();
        this.cloudVerificationUrl = session.getVerificationUrl();
        this.cloudPollIntervalMs = ((long) qb.d.n(session.getIntervalSeconds(), 1, 10)) * 1000;
        long jCurrentTimeMillis = System.currentTimeMillis();
        int expiresInSeconds = session.getExpiresInSeconds();
        if (expiresInSeconds < 30) {
            expiresInSeconds = 30;
        }
        this.cloudExpiresAtMs = (((long) expiresInSeconds) * 1000) + jCurrentTimeMillis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e autoPlayMinQualityKey() {
        return this.profileManager.profileStringKey("auto_play_min_quality");
    }

    private final c1.e autoPlayMinQualityKeyFor(String profileId) {
        return this.profileManager.profileStringKeyFor(profileId, "auto_play_min_quality");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e autoPlayNextKey() {
        return this.profileManager.profileBooleanKey(Constants.PREF_AUTO_PLAY_NEXT);
    }

    private final c1.e autoPlayNextKeyFor(String profileId) {
        return this.profileManager.profileBooleanKeyFor(profileId, Constants.PREF_AUTO_PLAY_NEXT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e autoPlaySingleSourceKey() {
        return this.profileManager.profileBooleanKey("auto_play_single_source");
    }

    private final c1.e autoPlaySingleSourceKeyFor(String profileId) {
        return this.profileManager.profileBooleanKeyFor(profileId, "auto_play_single_source");
    }

    public static /* synthetic */ void cancelPlexHomeServerAuth$default(SettingsViewModel settingsViewModel, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        settingsViewModel.cancelPlexHomeServerAuth(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e cardLayoutModeKey() {
        return this.profileManager.profileStringKey("card_layout_mode");
    }

    private final c1.e cardLayoutModeKeyFor(String profileId) {
        return this.profileManager.profileStringKeyFor(profileId, "card_layout_mode");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void clearCloudAuthSession(boolean cancelPolling) {
        ka.v1 v1Var;
        this.cloudDeviceCode = null;
        this.cloudUserCode = null;
        this.cloudVerificationUrl = null;
        this.cloudPollIntervalMs = 800L;
        this.cloudExpiresAtMs = 0L;
        if (cancelPolling && (v1Var = this.cloudPollingJob) != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.cloudPollingJob = null;
    }

    public static /* synthetic */ void clearCloudAuthSession$default(SettingsViewModel settingsViewModel, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        settingsViewModel.clearCloudAuthSession(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e clockFormatKey() {
        return this.profileManager.profileStringKey("clock_format");
    }

    public static /* synthetic */ void connectHomeServer$default(SettingsViewModel settingsViewModel, String str, String str2, String str3, String str4, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            str4 = "";
        }
        settingsViewModel.connectHomeServer(str, str2, str3, str4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e contentLanguageKey() {
        return this.profileManager.profileStringKey("content_language");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e defaultAudioLanguageKey() {
        return this.profileManager.profileStringKey("default_audio_language");
    }

    private final c1.e defaultAudioLanguageKeyFor(String profileId) {
        return this.profileManager.profileStringKeyFor(profileId, "default_audio_language");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e defaultSubtitleKey() {
        return this.profileManager.profileStringKey(Constants.PREF_DEFAULT_SUBTITLE);
    }

    private final c1.e defaultSubtitleKeyFor(String profileId) {
        return this.profileManager.profileStringKeyFor(profileId, Constants.PREF_DEFAULT_SUBTITLE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final QualityFilterPreset detectQualityFilterPreset(List<QualityFilterConfig> filters) {
        QualityFilterPreset next;
        ArrayList arrayList = new ArrayList();
        for (Object obj : filters) {
            QualityFilterConfig qualityFilterConfig = (QualityFilterConfig) obj;
            if (qualityFilterConfig.getEnabled() && !kotlin.text.o.h0(qualityFilterConfig.getRegexPattern())) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            return QualityFilterPreset.OFF;
        }
        if (arrayList.size() != 1) {
            return QualityFilterPreset.CUSTOM;
        }
        QualityFilterConfig qualityFilterConfig2 = (QualityFilterConfig) kotlin.collections.x.m0(arrayList);
        Iterator<QualityFilterPreset> it = QualityFilterPreset.getEntries().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            QualityFilterPreset qualityFilterPreset = next;
            if (qualityFilterPreset != QualityFilterPreset.OFF && qualityFilterPreset != QualityFilterPreset.CUSTOM && kotlin.jvm.internal.p.a(qualityFilterPreset.getFilterId(), qualityFilterConfig2.getId()) && kotlin.jvm.internal.p.a(qualityFilterPreset.getRegexPattern(), qualityFilterConfig2.getRegexPattern())) {
                break;
            }
        }
        QualityFilterPreset qualityFilterPreset2 = next;
        return qualityFilterPreset2 == null ? QualityFilterPreset.CUSTOM : qualityFilterPreset2;
    }

    private final String displayLanguage(String code) {
        String string = kotlin.text.o.L0(code).toString();
        if (kotlin.text.o.h0(string)) {
            return "";
        }
        if (string.length() <= 3) {
            for (int i10 = 0; i10 < string.length(); i10++) {
                if (Character.isLetter(string.charAt(i10))) {
                }
            }
            String displayLanguage = new Locale(string).getDisplayLanguage(Locale.ENGLISH);
            return (displayLanguage == null || kotlin.text.o.h0(displayLanguage)) ? string : displayLanguage;
        }
        if (string.length() <= 0) {
            return string;
        }
        return ((Object) String.valueOf(string.charAt(0)).toUpperCase(Locale.ROOT)) + string.substring(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String dnsProviderLabel(String value) {
        String strNormalizeDnsProviderValue = normalizeDnsProviderValue(value);
        int iHashCode = strNormalizeDnsProviderValue.hashCode();
        return iHashCode != -1240244679 ? iHashCode != -1144835998 ? (iHashCode == -887328209 && strNormalizeDnsProviderValue.equals("system")) ? "System DNS" : "Cloudflare" : !strNormalizeDnsProviderValue.equals("adguard") ? "Cloudflare" : "AdGuard" : !strNormalizeDnsProviderValue.equals("google") ? "Cloudflare" : "Google";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String dnsProviderValueFromLabel(String label) {
        String lowerCase = kotlin.text.o.L0(label).toString().toLowerCase(Locale.ROOT);
        int iHashCode = lowerCase.hashCode();
        return iHashCode != -1240244679 ? iHashCode != -1144835998 ? (iHashCode == 641323960 && lowerCase.equals("system dns")) ? "system" : "cloudflare" : !lowerCase.equals("adguard") ? "cloudflare" : "adguard" : !lowerCase.equals("google") ? "cloudflare" : "google";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX INFO: renamed from: ensureCloudAuthSession-gIAlu-s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object m6324ensureCloudAuthSessiongIAlus(boolean r5, d7.d<? super x6.d0> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof com.arflix.tv.ui.screens.settings.SettingsViewModel$ensureCloudAuthSession$1
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.ui.screens.settings.SettingsViewModel$ensureCloudAuthSession$1 r0 = (com.arflix.tv.ui.screens.settings.SettingsViewModel$ensureCloudAuthSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.ui.screens.settings.SettingsViewModel$ensureCloudAuthSession$1 r0 = new com.arflix.tv.ui.screens.settings.SettingsViewModel$ensureCloudAuthSession$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            x6.t0 r2 = x6.t0.f22605a
            r3 = 1
            if (r1 == 0) goto L35
            if (r1 != r3) goto L2d
            boolean r5 = r0.Z$0
            k2.c.G(r6)
            x6.d0 r6 = (x6.d0) r6
            java.lang.Object r6 = r6.f22580i
            goto L63
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L35:
            k2.c.G(r6)
            boolean r6 = r4.hasActiveCloudAuthSession()
            if (r6 == 0) goto L4f
            if (r5 == 0) goto L4e
            ka.v1 r5 = r4.cloudPollingJob
            if (r5 == 0) goto L4b
            boolean r5 = r5.isActive()
            if (r5 != r3) goto L4b
            goto L4e
        L4b:
            r4.startCloudPolling()
        L4e:
            return r2
        L4f:
            r6 = 0
            r1 = 0
            clearCloudAuthSession$default(r4, r6, r3, r1)
            com.arflix.tv.data.repository.TvDeviceAuthRepository r6 = r4.tvDeviceAuthRepository
            r0.Z$0 = r5
            r0.label = r3
            java.lang.Object r6 = r6.m6148startSessionIoAF18A(r0)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L63
            return r0
        L63:
            boolean r0 = r6 instanceof x6.c0
            if (r0 != 0) goto L72
            com.arflix.tv.data.repository.TvDeviceAuthSession r6 = (com.arflix.tv.data.repository.TvDeviceAuthSession) r6
            r4.applyCloudAuthSession(r6)
            if (r5 == 0) goto L71
            r4.startCloudPolling()
        L71:
            return r2
        L72:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.m6324ensureCloudAuthSessiongIAlus(boolean, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0088 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object ensureCloudSyncSession(d7.d<? super java.lang.Boolean> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.arflix.tv.ui.screens.settings.SettingsViewModel.C14881
            if (r0 == 0) goto L13
            r0 = r8
            com.arflix.tv.ui.screens.settings.SettingsViewModel$ensureCloudSyncSession$1 r0 = (com.arflix.tv.ui.screens.settings.SettingsViewModel.C14881) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.ui.screens.settings.SettingsViewModel$ensureCloudSyncSession$1 r0 = new com.arflix.tv.ui.screens.settings.SettingsViewModel$ensureCloudSyncSession$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            int r1 = r0.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            e7.a r6 = e7.a.f15033i
            if (r1 == 0) goto L44
            if (r1 == r5) goto L40
            if (r1 == r4) goto L3c
            if (r1 == r3) goto L38
            if (r1 != r2) goto L30
            k2.c.G(r8)
            return r8
        L30:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L38:
            k2.c.G(r8)
            goto L7d
        L3c:
            k2.c.G(r8)
            goto L68
        L40:
            k2.c.G(r8)
            goto L52
        L44:
            k2.c.G(r8)
            com.arflix.tv.data.repository.AuthRepository r8 = r7.authRepository
            r0.label = r5
            java.lang.Object r8 = r8.hasValidCloudSyncSession(r0)
            if (r8 != r6) goto L52
            goto L87
        L52:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L5d
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            return r8
        L5d:
            com.arflix.tv.data.repository.AuthRepository r8 = r7.authRepository
            r0.label = r4
            java.lang.Object r8 = r8.getCurrentUserIdForSync(r0)
            if (r8 != r6) goto L68
            goto L87
        L68:
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            if (r8 == 0) goto L72
            boolean r8 = kotlin.text.o.h0(r8)
            if (r8 == 0) goto L7d
        L72:
            com.arflix.tv.data.repository.AuthRepository r8 = r7.authRepository
            r0.label = r3
            java.lang.Object r8 = r8.checkAuthState(r0)
            if (r8 != r6) goto L7d
            goto L87
        L7d:
            com.arflix.tv.data.repository.AuthRepository r8 = r7.authRepository
            r0.label = r2
            java.lang.Object r8 = r8.hasValidCloudSyncSession(r0)
            if (r8 != r6) goto L88
        L87:
            return r6
        L88:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.ensureCloudSyncSession(d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e filterSubtitlesByLanguageKey() {
        return this.profileManager.profileBooleanKey("filter_subtitles_by_lang");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatSyncTime(String isoTime) throws Exception {
        if (isoTime == null) {
            return null;
        }
        try {
            return DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' h:mm a").withZone(ZoneId.systemDefault()).format(Instant.parse(isoTime));
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e frameRateMatchingModeKey() {
        return this.profileManager.profileStringKey("frame_rate_matching_mode");
    }

    private final c1.e frameRateMatchingModeKeyFor(String profileId) {
        return this.profileManager.profileStringKeyFor(profileId, "frame_rate_matching_mode");
    }

    private final boolean hasActiveCloudAuthSession() {
        String str;
        String str2 = this.cloudDeviceCode;
        if (str2 == null || kotlin.text.o.h0(str2) || (str = this.cloudUserCode) == null || kotlin.text.o.h0(str)) {
            return false;
        }
        return this.cloudExpiresAtMs <= 0 || System.currentTimeMillis() < this.cloudExpiresAtMs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e includeSpecialsKey() {
        return this.profileManager.profileBooleanKey("include_specials");
    }

    private final c1.e includeSpecialsKeyFor(String profileId) {
        return this.profileManager.profileBooleanKeyFor(profileId, "include_specials");
    }

    private final void initializeCatalogs() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14931(null), 3);
    }

    private final void initializeUpdaterState() {
        na.q0<SettingsUiState> q0Var = this._uiState;
        q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, this.appUpdateRepository.supportsSelfUpdate(), null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -524289, 1073741823, null));
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14941(null), 3);
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14952(null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> loadAudioLanguageOptions(String current) {
        List listE = t7.a.E("Auto (Original)", "None", "English", "Arabic", "Bengali", "Bulgarian", "Chinese", "Croatian", "Czech", "Danish", "Dutch", "Estonian", "Finnish", "French", "German", "Greek", "Gujarati", "Hebrew", "Hindi", "Hungarian", "Indonesian", "Italian", "Japanese", "Kannada", "Korean", "Lithuanian", "Malayalam", "Marathi", "Norwegian", "Persian", "Polish", "Portuguese", "Portuguese (Brazil)", "Punjabi", "Romanian", "Russian", "Serbian", "Slovak", "Slovenian", "Spanish", "Swedish", "Tamil", "Telugu", "Thai", "Turkish", "Ukrainian", "Vietnamese");
        z6.c cVarN = t7.a.n();
        if (!kotlin.text.o.h0(current)) {
            cVarN.add(current);
        }
        cVarN.addAll(listE);
        return kotlin.collections.x.X0(kotlin.collections.x.g0(t7.a.e(cVarN)), 60);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a2, code lost:
    
        if (r10 == r7) goto L38;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a7 A[PHI: r9 r10
      0x00a7: PHI (r9v3 java.lang.String) = (r9v2 java.lang.String), (r9v5 java.lang.String) binds: [B:31:0x0094, B:35:0x00a5] A[DONT_GENERATE, DONT_INLINE]
      0x00a7: PHI (r10v12 com.arflix.tv.data.model.IptvSnapshot) = (r10v9 com.arflix.tv.data.model.IptvSnapshot), (r10v14 com.arflix.tv.data.model.IptvSnapshot) binds: [B:31:0x0094, B:35:0x00a5] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00bd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadIptvGroupsForPlaylist(java.lang.String r9, d7.d<? super java.util.List<java.lang.String>> r10) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r10 instanceof com.arflix.tv.ui.screens.settings.SettingsViewModel.C14971
            if (r0 == 0) goto L13
            r0 = r10
            com.arflix.tv.ui.screens.settings.SettingsViewModel$loadIptvGroupsForPlaylist$1 r0 = (com.arflix.tv.ui.screens.settings.SettingsViewModel.C14971) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.ui.screens.settings.SettingsViewModel$loadIptvGroupsForPlaylist$1 r0 = new com.arflix.tv.ui.screens.settings.SettingsViewModel$loadIptvGroupsForPlaylist$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            int r1 = r0.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            e7.a r7 = e7.a.f15033i
            if (r1 == 0) goto L65
            if (r1 == r5) goto L5d
            if (r1 == r4) goto L51
            if (r1 == r3) goto L45
            if (r1 != r2) goto L3d
            java.lang.Object r9 = r0.L$2
            com.arflix.tv.data.model.IptvSnapshot r9 = (com.arflix.tv.data.model.IptvSnapshot) r9
            java.lang.Object r9 = r0.L$1
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r9 = r0.L$0
            java.lang.String r9 = (java.lang.String) r9
            k2.c.G(r10)
            return r10
        L3d:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L45:
            java.lang.Object r9 = r0.L$1
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r9 = r0.L$0
            java.lang.String r9 = (java.lang.String) r9
            k2.c.G(r10)
            goto La5
        L51:
            java.lang.Object r9 = r0.L$1
            java.util.List r9 = (java.util.List) r9
            java.lang.Object r9 = r0.L$0
            java.lang.String r9 = (java.lang.String) r9
            k2.c.G(r10)
            goto L92
        L5d:
            java.lang.Object r9 = r0.L$0
            java.lang.String r9 = (java.lang.String) r9
            k2.c.G(r10)
            goto L7a
        L65:
            k2.c.G(r10)
            ra.c r10 = ka.x0.f19655d
            com.arflix.tv.ui.screens.settings.SettingsViewModel$loadIptvGroupsForPlaylist$pagedGroups$1 r1 = new com.arflix.tv.ui.screens.settings.SettingsViewModel$loadIptvGroupsForPlaylist$pagedGroups$1
            r1.<init>(r8, r9, r6)
            r0.L$0 = r9
            r0.label = r5
            java.lang.Object r10 = ka.m0.y(r10, r1, r0)
            if (r10 != r7) goto L7a
            goto Lbc
        L7a:
            java.util.List r10 = (java.util.List) r10
            boolean r1 = r10.isEmpty()
            if (r1 != 0) goto L83
            return r10
        L83:
            com.arflix.tv.data.repository.IptvRepository r10 = r8.iptvRepository
            r0.L$0 = r9
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r10 = r10.getMemoryCachedSnapshot(r0)
            if (r10 != r7) goto L92
            goto Lbc
        L92:
            com.arflix.tv.data.model.IptvSnapshot r10 = (com.arflix.tv.data.model.IptvSnapshot) r10
            if (r10 != 0) goto La7
            com.arflix.tv.data.repository.IptvRepository r10 = r8.iptvRepository
            r0.L$0 = r9
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r10 = r10.getCachedSnapshotOrNull(r0)
            if (r10 != r7) goto La5
            goto Lbc
        La5:
            com.arflix.tv.data.model.IptvSnapshot r10 = (com.arflix.tv.data.model.IptvSnapshot) r10
        La7:
            ra.d r1 = ka.x0.f19653b
            com.arflix.tv.ui.screens.settings.SettingsViewModel$loadIptvGroupsForPlaylist$2 r3 = new com.arflix.tv.ui.screens.settings.SettingsViewModel$loadIptvGroupsForPlaylist$2
            r3.<init>(r10, r9, r6)
            r0.L$0 = r6
            r0.L$1 = r6
            r0.L$2 = r6
            r0.label = r2
            java.lang.Object r9 = ka.m0.y(r1, r3, r0)
            if (r9 != r7) goto Lbd
        Lbc:
            return r7
        Lbd:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.loadIptvGroupsForPlaylist(java.lang.String, d7.d):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadSettings() {
        ka.v1 v1Var = this.loadSettingsJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        ka.v1 v1Var2 = this.integrationMetadataJob;
        if (v1Var2 != null) {
            v1Var2.cancel((CancellationException) null);
        }
        ka.v1 v1Var3 = this.syncSummaryJob;
        if (v1Var3 != null) {
            v1Var3.cancel((CancellationException) null);
        }
        this.loadSettingsJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15001(null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadSubtitleOptions(java.lang.String r49, d7.d<? super java.util.List<java.lang.String>> r50) {
        /*
            Method dump skipped, instruction units count: 388
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.loadSubtitleOptions(java.lang.String, d7.d):java.lang.Object");
    }

    private final void moveAddon(String addonId, boolean moveUp) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15031(moveUp, this, addonId, null), 3);
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
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.normalizeAutoPlayMinQuality(java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0068 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String normalizeDnsProviderValue(java.lang.String r5) {
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
            java.lang.String r0 = "system"
            if (r5 == 0) goto L69
            int r1 = r5.hashCode()
            java.lang.String r2 = "adguard"
            java.lang.String r3 = "cloudflare"
            switch(r1) {
                case -2131773630: goto L5f;
                case -2129896797: goto L56;
                case -1240244679: goto L4c;
                case -1144835998: goto L44;
                case -887328209: goto L40;
                case -308958535: goto L39;
                case 641323960: goto L35;
                case 643200793: goto L2e;
                case 1119770728: goto L25;
                default: goto L24;
            }
        L24:
            goto L69
        L25:
            java.lang.String r1 = "ad guard"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L4b
            goto L69
        L2e:
            java.lang.String r1 = "system_dns"
        L31:
            r5.equals(r1)
            goto L69
        L35:
            java.lang.String r1 = "system dns"
            goto L31
        L39:
            boolean r5 = r5.equals(r3)
            if (r5 != 0) goto L68
            goto L69
        L40:
            r5.equals(r0)
            goto L69
        L44:
            boolean r5 = r5.equals(r2)
            if (r5 != 0) goto L4b
            goto L69
        L4b:
            return r2
        L4c:
            java.lang.String r1 = "google"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L55
            goto L69
        L55:
            return r1
        L56:
            java.lang.String r1 = "cloudflare_dns"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L68
            goto L69
        L5f:
            java.lang.String r1 = "cloudflare dns"
            boolean r5 = r5.equals(r1)
            if (r5 != 0) goto L68
            goto L69
        L68:
            return r3
        L69:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.normalizeDnsProviderValue(java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final String normalizeFrameRateMode(String raw) {
        String string;
        String lowerCase = (raw == null || (string = kotlin.text.o.L0(raw).toString()) == null) ? null : string.toLowerCase(Locale.ROOT);
        if (lowerCase != null) {
            switch (lowerCase.hashCode()) {
                case -1970393658:
                    if (lowerCase.equals("only if seamless")) {
                        return "Seamless only";
                    }
                    break;
                case -1414557169:
                    if (lowerCase.equals("always")) {
                        return "Always";
                    }
                    break;
                case -1372220011:
                    if (lowerCase.equals("seamless only")) {
                        return "Seamless only";
                    }
                    break;
                case -333631130:
                    if (lowerCase.equals("only_if_seamless")) {
                        return "Seamless only";
                    }
                    break;
                case 109935:
                    lowerCase.equals("off");
                    return "Off";
                case 884293783:
                    if (lowerCase.equals("seamless")) {
                        return "Seamless only";
                    }
                    break;
            }
        }
        return "Off";
    }

    private final void observeAddons() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15091(null), 3);
    }

    private final void observeAuthState() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15101(null), 3);
    }

    private final void observeCatalogs() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15111(null), 3);
    }

    private final void observeHomeServer() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15121(null), 3);
    }

    private final void observeIptvConfig() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15131(null), 3);
    }

    private final void observeIptvGroupPrefs() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15141(null), 3);
    }

    private final void observeProfileChanges() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15151(null), 3);
    }

    private final void observeSyncState() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15161(null), 3);
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15172(null), 3);
    }

    private final void observeTorrServer() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15181(null), 3);
    }

    public static /* synthetic */ void performFullSync$default(SettingsViewModel settingsViewModel, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        settingsViewModel.performFullSync(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshIntegrationUsernames(String profileId, boolean isTraktConnected, boolean isMdbListConnected, boolean isSimklConnected) {
        ka.v1 v1Var = this.integrationMetadataJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.integrationMetadataJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15261(isTraktConnected, isMdbListConnected, isSimklConnected, this, profileId, null), 3);
    }

    public static /* synthetic */ void refreshIntegrationUsernames$default(SettingsViewModel settingsViewModel, String str, boolean z, boolean z5, boolean z10, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            z10 = false;
        }
        settingsViewModel.refreshIntegrationUsernames(str, z, z5, z10);
    }

    public static /* synthetic */ void refreshIptv$default(SettingsViewModel settingsViewModel, boolean z, boolean z5, boolean z10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = true;
        }
        if ((i10 & 2) != 0) {
            z5 = false;
        }
        if ((i10 & 4) != 0) {
            z10 = true;
        }
        settingsViewModel.refreshIptv(z, z5, z10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshSyncSummary(String profileId) {
        ka.v1 v1Var = this.syncSummaryJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.syncSummaryJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15291(profileId, null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x02a8, code lost:
    
        if (r2.refreshForCurrentProfile(r3) == r7) goto L39;
     */
    /* JADX WARN: Removed duplicated region for block: B:42:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object restoreCloudStateToLocalInternal(boolean r136, boolean r137, d7.d<? super com.arflix.tv.ui.screens.settings.SettingsViewModel.CloudRestoreResult> r138) {
        /*
            Method dump skipped, instruction units count: 956
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.restoreCloudStateToLocalInternal(boolean, boolean, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object restoreCloudStateToLocalInternal$default(SettingsViewModel settingsViewModel, boolean z, boolean z5, d7.d dVar, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z5 = true;
        }
        return settingsViewModel.restoreCloudStateToLocalInternal(z, z5, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0019  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object saveQualityFilters(java.util.List<com.arflix.tv.data.model.QualityFilterConfig> r137, d7.d<? super x6.t0> r138) {
        /*
            Method dump skipped, instruction units count: 367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.saveQualityFilters(java.util.List, d7.d):java.lang.Object");
    }

    public static /* synthetic */ void searchCatalogLists$default(SettingsViewModel settingsViewModel, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = ((SettingsUiState) settingsViewModel._uiState.getValue()).getCatalogSearchQuery();
        }
        settingsViewModel.searchCatalogLists(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e secondarySubtitleKey() {
        return this.profileManager.profileStringKey("secondary_subtitle");
    }

    private final void setAutoPlayMinQuality(String value) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15431(normalizeAutoPlayMinQuality(value), null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e showBudgetKey() {
        return this.profileManager.profileBooleanKey("show_budget_on_home");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e showEpisodeRatingsKey() {
        return this.profileManager.profileBooleanKey("show_episode_ratings");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e showLoadingStatsKey() {
        return this.profileManager.profileBooleanKey("show_loading_stats");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e smoothScrollingKey() {
        return this.profileManager.profileBooleanKey("smooth_scrolling");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e spoilerBlurKey() {
        return this.profileManager.profileBooleanKey("spoiler_blur");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startCloudPolling() {
        String str = this.cloudDeviceCode;
        if (str == null) {
            return;
        }
        ka.v1 v1Var = this.cloudPollingJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.cloudPollingJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15811(str, null), 3);
    }

    public static /* synthetic */ void startPlexHomeServerAuth$default(SettingsViewModel settingsViewModel, String str, String str2, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str2 = "";
        }
        settingsViewModel.startPlexHomeServerAuth(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPlexHomeServerPolling(String serverUrl, PlexPinAuthSession session) {
        ka.v1 v1Var = this.plexHomeServerPollingJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.plexHomeServerPollingJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15831(session, this, serverUrl, null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startSimklPolling(String userCode, int expiresInSec, int intervalSec) {
        ka.v1 v1Var = this.simklPollingJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.simklPollingJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15851(expiresInSec, intervalSec, this, userCode, null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startTraktPolling(TraktDeviceCode deviceCode) {
        ka.v1 v1Var = this.traktPollingJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.traktPollingJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15871(deviceCode, this, null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopAiKeyServerInternal() {
        AiKeyConfigServer aiKeyConfigServer = this.aiKeyServer;
        if (aiKeyConfigServer != null) {
            aiKeyConfigServer.stop();
        }
        this.aiKeyServer = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e subtitleColorKey() {
        return this.profileManager.profileStringKey("subtitle_color");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e subtitleOffsetKey() {
        return this.profileManager.profileStringKey("subtitle_offset");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e subtitleSettingsUpdatedAtKey() {
        return this.profileManager.profileStringKey("subtitle_settings_updated_at");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e subtitleSizeKey() {
        return this.profileManager.profileStringKey("subtitle_size");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e subtitleStyleKey() {
        return this.profileManager.profileStringKey("subtitle_style");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e subtitleStylizedKey() {
        return this.profileManager.profileBooleanKey("subtitle_stylized");
    }

    private final c1.e subtitleUsageKey() {
        return this.profileManager.profileStringKey("subtitle_usage_v1");
    }

    public static /* synthetic */ void syncCloudStateToLocal$default(SettingsViewModel settingsViewModel, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        settingsViewModel.syncCloudStateToLocal(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0055, code lost:
    
        if (r1.syncHomeServerCatalogs((java.util.List) r6, r0) == r4) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object syncHomeServerCatalogsFromConnections(d7.d<? super x6.t0> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.arflix.tv.ui.screens.settings.SettingsViewModel.C15891
            if (r0 == 0) goto L13
            r0 = r6
            com.arflix.tv.ui.screens.settings.SettingsViewModel$syncHomeServerCatalogsFromConnections$1 r0 = (com.arflix.tv.ui.screens.settings.SettingsViewModel.C15891) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.arflix.tv.ui.screens.settings.SettingsViewModel$syncHomeServerCatalogsFromConnections$1 r0 = new com.arflix.tv.ui.screens.settings.SettingsViewModel$syncHomeServerCatalogsFromConnections$1
            r0.<init>(r6)
        L18:
            java.lang.Object r6 = r0.result
            int r1 = r0.label
            r2 = 2
            r3 = 1
            e7.a r4 = e7.a.f15033i
            if (r1 == 0) goto L3a
            if (r1 == r3) goto L36
            if (r1 != r2) goto L2e
            java.lang.Object r0 = r0.L$0
            java.util.List r0 = (java.util.List) r0
            k2.c.G(r6)
            goto L58
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L36:
            k2.c.G(r6)
            goto L48
        L3a:
            k2.c.G(r6)
            com.arflix.tv.data.repository.HomeServerRepository r6 = r5.homeServerRepository
            r0.label = r3
            java.lang.Object r6 = r6.getCatalogCandidates(r0)
            if (r6 != r4) goto L48
            goto L57
        L48:
            java.util.List r6 = (java.util.List) r6
            com.arflix.tv.data.repository.CatalogRepository r1 = r5.catalogRepository
            r3 = 0
            r0.L$0 = r3
            r0.label = r2
            java.lang.Object r6 = r1.syncHomeServerCatalogs(r6, r0)
            if (r6 != r4) goto L58
        L57:
            return r4
        L58:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.syncHomeServerCatalogsFromConnections(d7.d):java.lang.Object");
    }

    public static /* synthetic */ void syncLocalStateToCloud$default(SettingsViewModel settingsViewModel, boolean z, boolean z5, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        if ((i10 & 2) != 0) {
            z5 = false;
        }
        settingsViewModel.syncLocalStateToCloud(z, z5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e trailerAutoPlayKey() {
        return this.profileManager.profileBooleanKey("trailer_auto_play");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e trailerDelayKey() {
        return this.profileManager.profileStringKey("trailer_delay_seconds");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e trailerInCardsKey() {
        return this.profileManager.profileBooleanKey("trailer_in_cards");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e trailerSoundEnabledKey() {
        return this.profileManager.profileBooleanKey("trailer_sound_enabled");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<CatalogConfig> visibleCatalogs(List<CatalogConfig> catalogs) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : catalogs) {
            CatalogConfig catalogConfig = (CatalogConfig) obj;
            int i10 = WhenMappings.$EnumSwitchMapping$0[catalogConfig.getKind().ordinal()];
            boolean zIsValidCollectionConfig = true;
            if (i10 == 1) {
                zIsValidCollectionConfig = false;
            } else if (i10 == 2) {
                zIsValidCollectionConfig = CollectionTemplateManifest.INSTANCE.isValidCollectionConfig(catalogConfig);
            }
            if (zIsValidCollectionConfig) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final c1.e volumeBoostDbKey() {
        return this.profileManager.profileStringKey("volume_boost_db");
    }

    public final void addCatalog(String url) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new AnonymousClass1(url, null), 3);
    }

    public final void addCustomAddon(String url) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14661(url, null), 3);
    }

    public final void addDiscoveredCatalog(CatalogDiscoveryResult result) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14671(result, null), 3);
    }

    public final boolean addQualityFilter(String deviceName, String regexPattern) {
        String string = kotlin.text.o.L0(regexPattern).toString();
        if (kotlin.text.o.h0(string)) {
            return false;
        }
        try {
            Pattern.compile(string);
            ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new AnonymousClass3(deviceName, string, null), 3);
            return true;
        } catch (PatternSyntaxException | IllegalArgumentException unused) {
            return false;
        }
    }

    public final void cancelCloudAuth() {
        clearCloudAuthSession$default(this, false, 1, null);
        na.q0<SettingsUiState> q0Var = this._uiState;
        q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, Integer.MAX_VALUE, -16, -1, 1073741823, null));
    }

    public final void cancelDownloadAppUpdate() {
        ka.v1 v1Var = this.downloadJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.downloadJob = null;
        UpdateStatus updateStatus = (UpdateStatus) this.updateStatusManager.getStatus().getValue();
        if (updateStatus instanceof UpdateStatus.Downloading) {
            this.updateStatusManager.updateStatus(new UpdateStatus.UpdateAvailable(((UpdateStatus.Downloading) updateStatus).getUpdate()));
        }
    }

    public final void cancelPlexHomeServerAuth(boolean updateState) {
        ka.v1 v1Var = this.plexHomeServerPollingJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.plexHomeServerPollingJob = null;
        this.plexHomeServerUrl = null;
        this.plexHomeServerDisplayName = null;
        if (updateState) {
            na.q0<SettingsUiState> q0Var = this._uiState;
            q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1073741407, null));
        }
    }

    public final void cancelTraktAuth() {
        ka.v1 v1Var = this.traktPollingJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        ka.v1 v1Var2 = this.traktStartupJob;
        if (v1Var2 != null) {
            v1Var2.cancel((CancellationException) null);
        }
        na.q0<SettingsUiState> q0Var = this._uiState;
        q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -23553, -1, 1073741823, null));
    }

    public final void checkForAppUpdates(boolean force, boolean showNoUpdateFeedback) {
        if (this.appUpdateRepository.supportsSelfUpdate()) {
            ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14681(force, showNoUpdateFeedback, null), 3);
        } else {
            na.q0<SettingsUiState> q0Var = this._uiState;
            q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, force, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -2097153, 1073741823, null));
        }
    }

    public final void clearCatalogDiscovery() {
        ka.v1 v1Var = this.catalogSearchJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.catalogSearchJob = null;
        na.q0<SettingsUiState> q0Var = this._uiState;
        q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, "", kotlin.collections.z.f19728i, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -251658241, 1073741823, null));
    }

    public final void clearIptvConfig() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14691(null), 3);
    }

    public final void clearPendingPack() {
        na.q0<SettingsUiState> q0Var = this._uiState;
        q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, 268435455, 1073741823, null));
    }

    public final void closeCloudEmailPasswordDialog() {
        na.q0<SettingsUiState> q0Var = this._uiState;
        q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -5, -1, 1073741823, null));
    }

    public final void completeCloudAuthWithEmailPassword(String email, String password, boolean createAccount) {
        AuthEmailValidator authEmailValidator = AuthEmailValidator.INSTANCE;
        String strNormalize = authEmailValidator.normalize(email);
        Integer numValidate = authEmailValidator.validate(strNormalize, createAccount);
        if (numValidate != null) {
            String string = this.context.getString(numValidate.intValue());
            na.q0<SettingsUiState> q0Var = this._uiState;
            q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, string, ToastType.ERROR, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
            return;
        }
        if (!kotlin.text.o.h0(password)) {
            ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new AnonymousClass2(strNormalize, password, createAccount, null), 3);
        } else {
            na.q0<SettingsUiState> q0Var2 = this._uiState;
            q0Var2.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var2.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, "Password is required", ToastType.ERROR, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
        }
    }

    public final void confirmInstallPack(String url) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14701(url, ((SettingsUiState) this._uiState.getValue()).getPendingPackManifest(), null), 3);
    }

    public final void connectHomeServer(String serverUrl, String username, String password, String displayName) {
        if (((SettingsUiState) this._uiState.getValue()).isHomeServerConnecting()) {
            return;
        }
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14711(serverUrl, username, password, displayName, null), 3);
    }

    public final void connectMdbList(String apiKey) {
        String string = kotlin.text.o.L0(apiKey).toString();
        if (string.length() == 0 || ((SettingsUiState) this._uiState.getValue()).getMdbListConnecting()) {
            return;
        }
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14721(string, null), 3);
    }

    public final void cycleAccentColor() {
        List listE = t7.a.E("White", "Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet");
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14731((String) listE.get((listE.indexOf(((SettingsUiState) this._uiState.getValue()).getAccentColor()) + 1) % listE.size()), null), 3);
    }

    public final void cycleAutoPlayMinQuality() {
        String strNormalizeAutoPlayMinQuality = normalizeAutoPlayMinQuality(((SettingsUiState) this._uiState.getValue()).getAutoPlayMinQuality());
        int iHashCode = strNormalizeAutoPlayMinQuality.hashCode();
        String str = "Any";
        if (iHashCode != 65996) {
            if (iHashCode != 1688155) {
                if (iHashCode == 46737913 && strNormalizeAutoPlayMinQuality.equals("1080p")) {
                    str = "4K";
                }
            } else if (strNormalizeAutoPlayMinQuality.equals("720p")) {
                str = "1080p";
            }
        } else if (strNormalizeAutoPlayMinQuality.equals("Any")) {
            str = "720p";
        }
        setAutoPlayMinQuality(str);
    }

    public final void cycleClockFormat() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14741(kotlin.jvm.internal.p.a(((SettingsUiState) this._uiState.getValue()).getClockFormat(), "24h") ? "12h" : "24h", null), 3);
    }

    public final void cycleFrameRateMatchingMode() {
        String strNormalizeFrameRateMode = normalizeFrameRateMode(((SettingsUiState) this._uiState.getValue()).getFrameRateMatchingMode());
        String str = "Off";
        if (kotlin.jvm.internal.p.a(strNormalizeFrameRateMode, "Off")) {
            str = "Seamless only";
        } else if (kotlin.jvm.internal.p.a(strNormalizeFrameRateMode, "Seamless only")) {
            str = "Always";
        }
        setFrameRateMatchingMode(str);
    }

    public final void cycleQualityFilterPreset() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14751(null), 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void cycleSubtitleColor() {
        /*
            r6 = this;
            na.q0<com.arflix.tv.ui.screens.settings.SettingsUiState> r0 = r6._uiState
            java.lang.Object r0 = r0.getValue()
            com.arflix.tv.ui.screens.settings.SettingsUiState r0 = (com.arflix.tv.ui.screens.settings.SettingsUiState) r0
            java.lang.String r0 = r0.getSubtitleColor()
            int r1 = r0.hashCode()
            r2 = -1650372460(0xffffffff9da15094, float:-4.269966E-21)
            java.lang.String r3 = "Green"
            java.lang.String r4 = "Yellow"
            java.lang.String r5 = "White"
            if (r1 == r2) goto L39
            r2 = 69066467(0x41ddee3, float:1.8557606E-36)
            if (r1 == r2) goto L2f
            r2 = 83549193(0x4fadc09, float:5.8976788E-36)
            if (r1 == r2) goto L26
            goto L3f
        L26:
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L2d
            goto L3f
        L2d:
            r3 = r4
            goto L40
        L2f:
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L36
            goto L3f
        L36:
            java.lang.String r3 = "Cyan"
            goto L40
        L39:
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L40
        L3f:
            r3 = r5
        L40:
            ka.k0 r0 = androidx.lifecycle.z0.h(r6)
            com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleColor$1 r1 = new com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleColor$1
            r2 = 0
            r1.<init>(r3, r2)
            r3 = 3
            r4 = 0
            ka.m0.p(r0, r2, r4, r1, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.cycleSubtitleColor():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void cycleSubtitleOffset() {
        /*
            r6 = this;
            na.q0<com.arflix.tv.ui.screens.settings.SettingsUiState> r0 = r6._uiState
            java.lang.Object r0 = r0.getValue()
            com.arflix.tv.ui.screens.settings.SettingsUiState r0 = (com.arflix.tv.ui.screens.settings.SettingsUiState) r0
            java.lang.String r0 = r0.getSubtitleOffset()
            int r1 = r0.hashCode()
            r2 = -1994163307(0xffffffff89237b95, float:-1.967852E-33)
            java.lang.String r3 = "Medium"
            java.lang.String r4 = "Bottom"
            if (r1 == r2) goto L35
            r2 = 76596(0x12b34, float:1.07334E-40)
            java.lang.String r5 = "Low"
            if (r1 == r2) goto L2e
            r2 = 1995605579(0x76f2864b, float:2.4594915E33)
            if (r1 == r2) goto L26
            goto L3b
        L26:
            boolean r0 = r0.equals(r4)
            if (r0 == 0) goto L3b
            r3 = r5
            goto L3f
        L2e:
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L3f
            goto L3b
        L35:
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L3d
        L3b:
            r3 = r4
            goto L3f
        L3d:
            java.lang.String r3 = "High"
        L3f:
            ka.k0 r0 = androidx.lifecycle.z0.h(r6)
            com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleOffset$1 r1 = new com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleOffset$1
            r2 = 0
            r1.<init>(r3, r2)
            r3 = 3
            r4 = 0
            ka.m0.p(r0, r2, r4, r1, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.cycleSubtitleOffset():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void cycleSubtitleSize() {
        /*
            r6 = this;
            na.q0<com.arflix.tv.ui.screens.settings.SettingsUiState> r0 = r6._uiState
            java.lang.Object r0 = r0.getValue()
            com.arflix.tv.ui.screens.settings.SettingsUiState r0 = (com.arflix.tv.ui.screens.settings.SettingsUiState) r0
            java.lang.String r0 = r0.getSubtitleSize()
            int r1 = r0.hashCode()
            r2 = -1994163307(0xffffffff89237b95, float:-1.967852E-33)
            java.lang.String r3 = "Large"
            java.lang.String r4 = "Medium"
            java.lang.String r5 = "Small"
            if (r1 == r2) goto L39
            r2 = 73190171(0x45ccb1b, float:2.5954137E-36)
            if (r1 == r2) goto L2f
            r2 = 79996135(0x4c4a4e7, float:4.6230817E-36)
            if (r1 == r2) goto L26
            goto L3f
        L26:
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L2d
            goto L3f
        L2d:
            r3 = r4
            goto L40
        L2f:
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L36
            goto L3f
        L36:
            java.lang.String r3 = "Extra Large"
            goto L40
        L39:
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L40
        L3f:
            r3 = r5
        L40:
            ka.k0 r0 = androidx.lifecycle.z0.h(r6)
            com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleSize$1 r1 = new com.arflix.tv.ui.screens.settings.SettingsViewModel$cycleSubtitleSize$1
            r2 = 0
            r1.<init>(r3, r2)
            r3 = 3
            r4 = 0
            ka.m0.p(r0, r2, r4, r1, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.settings.SettingsViewModel.cycleSubtitleSize():void");
    }

    public final void cycleSubtitleStyle() {
        String subtitleStyle = ((SettingsUiState) this._uiState.getValue()).getSubtitleStyle();
        String str = "Bold";
        if (kotlin.jvm.internal.p.a(subtitleStyle, "Bold")) {
            str = "Normal";
        } else if (kotlin.jvm.internal.p.a(subtitleStyle, "Normal")) {
            str = "Background";
        }
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14791(str, null), 3);
    }

    public final void cycleTrailerDelay() {
        int trailerDelaySeconds = ((SettingsUiState) this._uiState.getValue()).getTrailerDelaySeconds();
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14801(trailerDelaySeconds != 0 ? trailerDelaySeconds != 1 ? trailerDelaySeconds != 2 ? trailerDelaySeconds != 3 ? 0 : 5 : 3 : 2 : 1, null), 3);
    }

    public final void cycleVolumeBoost() {
        int i10;
        int volumeBoostDb = ((SettingsUiState) this._uiState.getValue()).getVolumeBoostDb();
        if (volumeBoostDb < 3) {
            i10 = 3;
        } else {
            i10 = 6;
            if (volumeBoostDb >= 6) {
                i10 = 9;
                if (volumeBoostDb >= 9) {
                    i10 = 12;
                    if (volumeBoostDb >= 12) {
                        i10 = 15;
                        if (volumeBoostDb >= 15) {
                            i10 = 0;
                        }
                    }
                }
            }
        }
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14811(i10, null), 3);
    }

    public final void deleteQualityFilter(String filterId) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14821(filterId, null), 3);
    }

    public final void disconnectHomeServer() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14831(null), 3);
    }

    public final void disconnectMdbList() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14841(null), 3);
    }

    public final void disconnectSimkl() {
        ka.v1 v1Var = this.simklPollingJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.simklPollingJob = null;
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14851(null), 3);
    }

    public final void disconnectTrakt() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14861(null), 3);
    }

    public final void dismissAppUpdateDialog() {
        na.q0<SettingsUiState> q0Var = this._uiState;
        q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -6291457, 1073741823, null));
    }

    public final void dismissToast() {
        na.q0<SettingsUiState> q0Var = this._uiState;
        q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1073479679, null));
    }

    public final void downloadAppUpdate() {
        AppUpdate update;
        UpdateStatus updateStatus = (UpdateStatus) this.updateStatusManager.getStatus().getValue();
        if (updateStatus instanceof UpdateStatus.UpdateAvailable) {
            update = ((UpdateStatus.UpdateAvailable) updateStatus).getUpdate();
        } else if (!(updateStatus instanceof UpdateStatus.Failure)) {
            return;
        } else {
            update = ((UpdateStatus.Failure) updateStatus).getUpdate();
        }
        if (update != null && this.appUpdateRepository.supportsSelfUpdate()) {
            this.downloadJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14871(update, null), 3);
        }
    }

    public final void forceCloudPullOnly() {
        if (((SettingsUiState) this._uiState.getValue()).isForceCloudSyncing()) {
            return;
        }
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14891(null), 3);
    }

    public final void forceCloudPushOnly() {
        if (((SettingsUiState) this._uiState.getValue()).isForceCloudSyncing()) {
            return;
        }
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14901(null), 3);
    }

    public final void forceCloudSyncNow() {
        if (((SettingsUiState) this._uiState.getValue()).isForceCloudSyncing()) {
            return;
        }
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14911(null), 3);
    }

    public final na.h1<SettingsUiState> getUiState() {
        return this.uiState;
    }

    public final void ignoreAppUpdate() {
        UpdateStatus updateStatus = (UpdateStatus) this.updateStatusManager.getStatus().getValue();
        if (updateStatus instanceof UpdateStatus.UpdateAvailable) {
            this.updateStatusManager.setSessionIgnoredTag(((UpdateStatus.UpdateAvailable) updateStatus).getUpdate().getTag());
            ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14921(updateStatus, null), 3);
        }
        na.q0<SettingsUiState> q0Var = this._uiState;
        q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -2097153, 1073741823, null));
        this.updateStatusManager.reset();
    }

    public final void installAppUpdateOrRequestPermission() {
        UpdateStatus updateStatus = (UpdateStatus) this.updateStatusManager.getStatus().getValue();
        boolean z = updateStatus instanceof UpdateStatus.ReadyToInstall;
        if ((z || (updateStatus instanceof UpdateStatus.Failure)) && z) {
            UpdateStatus.ReadyToInstall readyToInstall = (UpdateStatus.ReadyToInstall) updateStatus;
            String apkPath = readyToInstall.getApkPath();
            AppUpdate update = readyToInstall.getUpdate();
            File file = new File(apkPath);
            if (!file.exists()) {
                this.updateStatusManager.updateStatus(new UpdateStatus.Failure("Downloaded file is missing", update));
                return;
            }
            ApkInstaller apkInstaller = ApkInstaller.INSTANCE;
            if (!apkInstaller.canRequestPackageInstalls(this.context)) {
                na.q0<SettingsUiState> q0Var = this._uiState;
                q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, true, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -6291457, 1073741823, null));
                return;
            }
            String strCheckSignatureConflict = apkInstaller.checkSignatureConflict(this.context, file);
            if (strCheckSignatureConflict != null) {
                this.updateStatusManager.updateStatus(new UpdateStatus.Failure(strCheckSignatureConflict, update));
                return;
            }
            apkInstaller.launchInstall(this.context, file);
            this.updateStatusManager.updateStatus(new UpdateStatus.Installing(update));
            ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14961(update, null), 3);
        }
    }

    public final void loadPackManifest(String url) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C14991(url, null), 3);
    }

    public final void logout() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15021(null), 3);
    }

    public final void moveAddonDown(String addonId) {
        moveAddon(addonId, false);
    }

    public final void moveAddonUp(String addonId) {
        moveAddon(addonId, true);
    }

    public final void moveCatalogDown(String catalogId) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15041(catalogId, null), 3);
    }

    public final void moveCatalogUp(String catalogId) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15051(catalogId, null), 3);
    }

    public final void moveIptvGroupDown(String playlistId, String groupName) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15061(playlistId, groupName, null), 3);
    }

    public final void moveIptvGroupToTop(String playlistId, String groupName) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15071(playlistId, groupName, null), 3);
    }

    public final void moveIptvGroupUp(String playlistId, String groupName) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15081(playlistId, groupName, null), 3);
    }

    @Override // androidx.lifecycle.d1
    public void onCleared() {
        super.onCleared();
        ka.v1 v1Var = this.traktPollingJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        stopAiKeyServerInternal();
        ka.v1 v1Var2 = this.plexHomeServerPollingJob;
        if (v1Var2 != null) {
            v1Var2.cancel((CancellationException) null);
        }
    }

    public final void onCloudProfileSwitchHandled() {
        if (((SettingsUiState) this._uiState.getValue()).getShouldSwitchProfile()) {
            na.q0<SettingsUiState> q0Var = this._uiState;
            q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -65, -1, 1073741823, null));
        }
    }

    public final void openCloudEmailPasswordDialog() {
        if (((SettingsUiState) this._uiState.getValue()).isLoggedIn()) {
            return;
        }
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15191(null), 3);
    }

    public final void openUnknownSourcesSettings() {
        Intent intentBuildUnknownSourcesSettingsIntent = ApkInstaller.INSTANCE.buildUnknownSourcesSettingsIntent(this.context);
        if (intentBuildUnknownSourcesSettingsIntent != null) {
            this.context.startActivity(intentBuildUnknownSourcesSettingsIntent);
        }
    }

    public final void performFullSync(boolean silent) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15201(silent, null), 3);
    }

    public final void performIncrementalSync() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15211(null), 3);
    }

    public final void pollSimklAuth() {
        String simklUserCode = ((SettingsUiState) this._uiState.getValue()).getSimklUserCode();
        if (simklUserCode == null) {
            return;
        }
        ka.v1 v1Var = this.simklPollingJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.simklPollingJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15221(simklUserCode, null), 3);
    }

    public final void reconnectTrakt() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15231(null), 3);
    }

    public final void refreshAddons() {
        if (((SettingsUiState) this._uiState.getValue()).isRefreshingAddons()) {
            return;
        }
        na.q0<SettingsUiState> q0Var = this._uiState;
        q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, true, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1073741821, null));
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15241(null), 3);
    }

    public final void refreshAudioLanguageOptions() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15251(null), 3);
    }

    public final void refreshIptv(boolean showToast, boolean configured, boolean force) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15271(force, configured, showToast, null), 3);
    }

    public final void refreshSubtitleOptions() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15281(null), 3);
    }

    public final void removeAddon(String addonId) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15301(addonId, null), 3);
    }

    public final void removeCatalog(String catalogId) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15311(catalogId, null), 3);
    }

    public final void removeCatalogPack(String packId) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15321(packId, null), 3);
    }

    public final void renameCatalog(String catalogId, String newTitle) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15331(catalogId, newTitle, null), 3);
    }

    public final void resetIptvGroupOrder(String playlistId) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15341(playlistId, null), 3);
    }

    public final void saveIptvConfig(String m3uUrl, String epgUrl) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15361(m3uUrl, epgUrl, this, null), 3);
    }

    public final void saveIptvConfigWithXtream(String sourceOrHost, String epgUrl, String xtreamUsername, String xtreamPassword) {
        String string = kotlin.text.o.L0(sourceOrHost).toString();
        String string2 = kotlin.text.o.L0(epgUrl).toString();
        String string3 = kotlin.text.o.L0(xtreamUsername).toString();
        String string4 = kotlin.text.o.L0(xtreamPassword).toString();
        boolean z = (kotlin.text.o.h0(string3) && kotlin.text.o.h0(string4)) ? false : true;
        if (z && (kotlin.text.o.h0(string3) || kotlin.text.o.h0(string4))) {
            na.q0<SettingsUiState> q0Var = this._uiState;
            q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, "Xtream requires both username and password", ToastType.ERROR, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1072955391, null));
            return;
        }
        String strO = z ? androidx.compose.material3.d.o(string, " ", string3, " ", string4) : string;
        if (kotlin.text.o.h0(string2) && z) {
            string2 = androidx.compose.material3.d.o(string, " ", string3, " ", string4);
        }
        saveIptvConfig(strO, string2);
    }

    public final void saveIptvPlaylists(List<IptvPlaylistEntry> playlists) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15371(playlists, null), 3);
    }

    public final void saveStalkerConfig(String portalUrl, String macAddress) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15401(portalUrl, macAddress, this, null), 3);
    }

    public final void saveSubtitleAiApiKey(String key) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15411(key, null), 3);
    }

    public final void searchCatalogLists(String query) {
        String string = kotlin.text.o.L0(query).toString();
        ka.v1 v1Var = this.catalogSearchJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        if (string.length() >= 2) {
            this.catalogSearchJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15421(string, null), 3);
        } else {
            na.q0<SettingsUiState> q0Var = this._uiState;
            q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, kotlin.collections.z.f19728i, false, kotlin.text.o.h0(string) ? null : "Type at least 2 characters", null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -234881025, 1073741823, null));
        }
    }

    public final void setAutoPlayNext(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15441(enabled, null), 3);
    }

    public final void setAutoPlaySingleSource(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15451(enabled, null), 3);
    }

    public final void setCardLayoutMode(String mode) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15461(CardLayoutModeKt.normalizeCardLayoutMode(mode), null), 3);
    }

    public final void setCatalogSearchQuery(String query) {
        na.q0<SettingsUiState> q0Var = this._uiState;
        q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, query, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -150994945, 1073741823, null));
    }

    public final void setContentLanguage(String lang) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15471(lang, null), 3);
    }

    public final void setCustomUserAgent(String value) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15481(kotlin.text.o.L0(value).toString(), null), 3);
    }

    public final void setDefaultAudioLanguage(String language) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15491(language, null), 3);
    }

    public final void setDefaultSubtitle(String language) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15501(language, null), 3);
    }

    public final void setDeviceModeOverride(String mode) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15511(mode, null), 3);
    }

    public final void setDiagnosticsSharingEnabled(boolean enabled) {
        DiagnosticsManager.INSTANCE.setReportingEnabled(this.context, enabled);
        na.q0<SettingsUiState> q0Var = this._uiState;
        q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, enabled, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -134217729, -1, -1, 1073741823, null));
    }

    public final void setDnsProvider(String label) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15521(dnsProviderValueFromLabel(label), null), 3);
    }

    public final void setDolbyVisionCompatEnabled(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15531(enabled, null), 3);
    }

    public final void setFilterSubtitlesByLanguage(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15541(enabled, null), 3);
    }

    public final void setFrameRateMatchingMode(String mode) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15551(normalizeFrameRateMode(mode), null), 3);
    }

    public final void setIncludeSpecials(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15561(enabled, null), 3);
    }

    public final void setIptvSelectedPlaylistId(String playlistId) {
        String string = playlistId != null ? kotlin.text.o.L0(playlistId).toString() : null;
        if (string == null) {
            string = "";
        }
        String str = string;
        boolean zH0 = kotlin.text.o.h0(str);
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        if (zH0) {
            na.q0<SettingsUiState> q0Var = this._uiState;
            q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, zVar, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -98305, 1073741823, null));
        } else {
            na.q0<SettingsUiState> q0Var2 = this._uiState;
            q0Var2.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var2.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, str, zVar, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -98305, 1073741823, null));
            ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15571(str, null), 3);
        }
    }

    public final void setIptvSortOrder(String mode) {
        String strNormalizeIptvSortOrder = IptvRepositoryKt.normalizeIptvSortOrder(mode);
        na.q0<SettingsUiState> q0Var = this._uiState;
        q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, strNormalizeIptvSortOrder, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -129, 1073741823, null));
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15581(strNormalizeIptvSortOrder, null), 3);
    }

    public final void setOledBlackBackground(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15591(enabled, null), 3);
    }

    public final void setSecondarySubtitle(String language) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15601(language, null), 3);
    }

    public final void setShowBudget(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15611(enabled, null), 3);
    }

    public final void setShowEpisodeRatings(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15621(enabled, null), 3);
    }

    public final void setShowLoadingStats(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15631(enabled, null), 3);
    }

    public final void setSkipProfileSelection(boolean skip) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15641(skip, null), 3);
    }

    public final void setSmoothScrolling(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15651(enabled, null), 3);
    }

    public final void setSpoilerBlurEnabled(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15661(enabled, null), 3);
    }

    public final void setSubtitleAiAutoSelect(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15671(enabled, null), 3);
    }

    public final void setSubtitleAiEnabled(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15681(enabled, null), 3);
    }

    public final void setSubtitleAiFindBestMatch(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15691(enabled, null), 3);
    }

    public final void setSubtitleAiModel(SubtitleAiModel model) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15701(model, null), 3);
    }

    public final void setSubtitlePreloadEnabled(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15711(enabled, null), 3);
    }

    public final void setSubtitleRemoveHearingImpaired(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15721(enabled, null), 3);
    }

    public final void setTorrServerBaseUrl(String url) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15731(url, null), 3);
    }

    public final void setTrackingReadMode(TrackingFeature feature, TrackingReadMode mode) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15741(feature, mode, null), 3);
    }

    public final void setTrackingWriteTarget(SyncProvider provider, boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15751(provider, enabled, null), 3);
    }

    public final void setTrailerAutoPlay(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15761(enabled, null), 3);
    }

    public final void setTrailerInCards(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15771(enabled, null), 3);
    }

    public final void setTrailerSoundEnabled(boolean enabled) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15781(enabled, null), 3);
    }

    public final void startAiKeyServer() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15791(null), 3);
    }

    public final void startCloudAuth() {
        if (((SettingsUiState) this._uiState.getValue()).isLoggedIn() || ((SettingsUiState) this._uiState.getValue()).isCloudAuthWorking()) {
            return;
        }
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15801(null), 3);
    }

    public final void startPlexHomeServerAuth(String serverUrl, String displayName) {
        if (((SettingsUiState) this._uiState.getValue()).isHomeServerConnecting() || ((SettingsUiState) this._uiState.getValue()).isPlexHomeServerPolling()) {
            return;
        }
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15821(kotlin.text.o.L0(serverUrl).toString(), displayName, null), 3);
    }

    public final void startSimklAuth() {
        ka.v1 v1Var = this.simklPollingJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.simklPollingJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15841(null), 3);
    }

    public final void startTraktAuth() {
        SettingsUiState settingsUiState = (SettingsUiState) this._uiState.getValue();
        if (settingsUiState.isTraktAuthStarting() || settingsUiState.isTraktPolling()) {
            return;
        }
        ka.v1 v1Var = this.traktStartupJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        ka.v1 v1Var2 = this.traktPollingJob;
        if (v1Var2 != null) {
            v1Var2.cancel((CancellationException) null);
        }
        this.traktStartupJob = ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15861(null), 3);
    }

    public final void stopAiKeyServer() {
        stopAiKeyServerInternal();
        na.q0<SettingsUiState> q0Var = this._uiState;
        q0Var.setValue(SettingsUiState.copy$default((SettingsUiState) q0Var.getValue(), null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, new AiKeyServerState(false, null, null, false, 15, null), false, -1, -1, -1, 805306367, null));
    }

    public final void syncCloudStateToLocal(boolean silent) {
        if (((SettingsUiState) this._uiState.getValue()).isLoggedIn()) {
            ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15881(silent, null), 3);
        }
    }

    public final void syncLocalStateToCloud(boolean silent, boolean force) {
        if (force || ((SettingsUiState) this._uiState.getValue()).isLoggedIn()) {
            ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15901(force, silent, null), 3);
        }
    }

    public final void testHomeServerConnection() {
        if (((SettingsUiState) this._uiState.getValue()).isHomeServerConnecting()) {
            return;
        }
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15911(null), 3);
    }

    public final void toggleAddon(String addonId) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15921(addonId, null), 3);
    }

    public final void toggleCardLayoutMode() {
        String cardLayoutMode = ((SettingsUiState) this._uiState.getValue()).getCardLayoutMode();
        String str = CardLayoutModeKt.CARD_LAYOUT_MODE_POSTER;
        if (kotlin.text.u.L(cardLayoutMode, CardLayoutModeKt.CARD_LAYOUT_MODE_POSTER, true)) {
            str = CardLayoutModeKt.CARD_LAYOUT_MODE_LANDSCAPE;
        }
        setCardLayoutMode(str);
    }

    public final void toggleIptvHiddenGroup(String playlistId, String groupName) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15931(playlistId, groupName, null), 3);
    }

    public final void toggleQualityFilter(String filterId) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15941(filterId, null), 3);
    }

    public final void toggleSubtitleStylized() {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15951(!((SettingsUiState) this._uiState.getValue()).getSubtitleStylized(), null), 3);
    }

    public final void unpackCatalog(String catalogId) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15961(catalogId, null), 3);
    }

    public final void updateCatalog(String catalogId, String url) {
        ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15971(catalogId, url, null), 3);
    }

    public final boolean updateQualityFilter(String filterId, String deviceName, String regexPattern) {
        String string = kotlin.text.o.L0(regexPattern).toString();
        if (kotlin.text.o.h0(string)) {
            return false;
        }
        try {
            Pattern.compile(string);
            ka.m0.p(androidx.lifecycle.z0.h(this), null, 0, new C15983(filterId, deviceName, string, null), 3);
            return true;
        } catch (PatternSyntaxException | IllegalArgumentException unused) {
            return false;
        }
    }
}
