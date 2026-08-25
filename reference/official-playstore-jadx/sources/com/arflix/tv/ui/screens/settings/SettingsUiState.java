package com.arflix.tv.ui.screens.settings;

import androidx.media3.common.C;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import com.arflix.tv.data.api.TraktDeviceCode;
import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.CatalogDiscoveryResult;
import com.arflix.tv.data.model.CatalogPackManifest;
import com.arflix.tv.data.model.QualityFilterConfig;
import com.arflix.tv.data.repository.HomeServerConnection;
import com.arflix.tv.data.repository.IptvPlaylistEntry;
import com.arflix.tv.data.repository.PlexPinAuthSession;
import com.arflix.tv.data.repository.SyncProgress;
import com.arflix.tv.data.repository.sync.TrackingReadMode;
import com.arflix.tv.ui.components.CardLayoutModeKt;
import com.arflix.tv.ui.screens.player.SubtitleAiModel;
import com.arflix.tv.updater.UpdateStatus;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000¡\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0003\b\u0081\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0080\u000b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u001b\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u001b\u0012\b\b\u0002\u0010 \u001a\u00020\u000b\u0012\b\b\u0002\u0010!\u001a\u00020\u000b\u0012\b\b\u0002\u0010\"\u001a\u00020\u000b\u0012\b\b\u0002\u0010#\u001a\u00020\u000b\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010%\u001a\u00020\u000b\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010(\u001a\u00020\u000b\u0012\b\b\u0002\u0010)\u001a\u00020\u000b\u0012\b\b\u0002\u0010*\u001a\u00020\u000b\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010,\u001a\u00020\u000b\u0012\b\b\u0002\u0010-\u001a\u00020\u001b\u0012\b\b\u0002\u0010.\u001a\u00020\u001b\u0012\b\b\u0002\u0010/\u001a\u00020\u000b\u0012\n\b\u0002\u00100\u001a\u0004\u0018\u000101\u0012\b\b\u0002\u00102\u001a\u00020\u000b\u0012\b\b\u0002\u00103\u001a\u00020\u000b\u0012\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u00106\u001a\u00020\u000b\u0012\b\b\u0002\u00107\u001a\u00020\u000b\u0012\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u00109\u001a\u00020\u000b\u0012\b\b\u0002\u0010:\u001a\u00020\u000b\u0012\b\b\u0002\u0010;\u001a\u00020\u000b\u0012\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010?\u001a\u00020@\u0012\b\b\u0002\u0010A\u001a\u00020@\u0012\b\b\u0002\u0010B\u001a\u00020@\u0012\b\b\u0002\u0010C\u001a\u00020\u000b\u0012\b\b\u0002\u0010D\u001a\u00020\u000b\u0012\b\b\u0002\u0010E\u001a\u00020\u000b\u0012\b\b\u0002\u0010F\u001a\u00020G\u0012\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010I\u001a\u00020\u001b\u0012\b\b\u0002\u0010J\u001a\u00020\u001b\u0012\b\b\u0002\u0010K\u001a\u00020\u0003\u0012\b\b\u0002\u0010L\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010M\u001a\b\u0012\u0004\u0012\u00020N0\u0005\u0012\b\b\u0002\u0010O\u001a\u00020\u0003\u0012\b\b\u0002\u0010P\u001a\u00020\u0003\u0012\b\b\u0002\u0010Q\u001a\u00020\u0003\u0012\b\b\u0002\u0010R\u001a\u00020\u001b\u0012\b\b\u0002\u0010S\u001a\u00020\u000b\u0012\n\b\u0002\u0010T\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010U\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010V\u001a\u00020W\u0012\n\b\u0002\u0010X\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010Y\u001a\u00020\u001b\u0012\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010[\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u000e\b\u0002\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u000e\b\u0002\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\b\b\u0002\u0010^\u001a\u00020\u000b\u0012\b\b\u0002\u0010_\u001a\u00020`\u0012\b\b\u0002\u0010a\u001a\u00020\u000b\u0012\b\b\u0002\u0010b\u001a\u00020\u000b\u0012\u000e\b\u0002\u0010c\u001a\b\u0012\u0004\u0012\u00020d0\u0005\u0012\b\b\u0002\u0010e\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010f\u001a\b\u0012\u0004\u0012\u00020g0\u0005\u0012\b\b\u0002\u0010h\u001a\u00020\u000b\u0012\n\b\u0002\u0010i\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010j\u001a\u0004\u0018\u00010k\u0012\n\b\u0002\u0010l\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010m\u001a\u00020\u000b\u0012\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010o\u001a\b\u0012\u0004\u0012\u00020p0\u0005\u0012\b\b\u0002\u0010q\u001a\u00020\u000b\u0012\b\b\u0002\u0010r\u001a\u00020\u0003\u0012\n\b\u0002\u0010s\u001a\u0004\u0018\u00010t\u0012\u000e\b\u0002\u0010u\u001a\b\u0012\u0004\u0012\u00020t0\u0005\u0012\b\b\u0002\u0010v\u001a\u00020\u000b\u0012\n\b\u0002\u0010w\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010x\u001a\u0004\u0018\u00010y\u0012\b\b\u0002\u0010z\u001a\u00020\u000b\u0012\b\b\u0002\u0010{\u001a\u00020\u0003\u0012\b\b\u0002\u0010|\u001a\u00020\u0003\u0012\b\b\u0002\u0010}\u001a\u00020\u000b\u0012\b\b\u0002\u0010~\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u007f\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0080\u0001\u001a\t\u0012\u0005\u0012\u00030\u0081\u00010\u0005\u0012\t\b\u0002\u0010\u0082\u0001\u001a\u00020\u000b\u0012\t\b\u0002\u0010\u0083\u0001\u001a\u00020\u0003\u0012\t\b\u0002\u0010\u0084\u0001\u001a\u00020\u0003\u0012\u000b\b\u0002\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0003\u0012\t\b\u0002\u0010\u0086\u0001\u001a\u00020W\u0012\t\b\u0002\u0010\u0087\u0001\u001a\u00020\u000b\u0012\t\b\u0002\u0010\u0088\u0001\u001a\u00020\u000b\u0012\t\b\u0002\u0010\u0089\u0001\u001a\u00020\u000b\u0012\t\b\u0002\u0010\u008a\u0001\u001a\u00020\u000b\u0012\t\b\u0002\u0010\u008b\u0001\u001a\u00020\u000b\u0012\t\b\u0002\u0010\u008c\u0001\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u008d\u0001\u001a\u00030\u008e\u0001\u0012\t\b\u0002\u0010\u008f\u0001\u001a\u00020\u000b\u0012\n\b\u0002\u0010\u0090\u0001\u001a\u00030\u0091\u0001\u0012\t\b\u0002\u0010\u0092\u0001\u001a\u00020\u000b¢\u0006\u0006\b\u0093\u0001\u0010\u0094\u0001J\n\u0010\u008f\u0002\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0090\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\n\u0010\u0091\u0002\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0092\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\n\u0010\u0093\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0094\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0095\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010\u0096\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010\u0097\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0098\u0002\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0099\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\n\u0010\u009a\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u009b\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u009c\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u009d\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u009e\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u009f\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010 \u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010¡\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010¢\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010£\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010¤\u0002\u001a\u00020\u001bHÆ\u0003J\n\u0010¥\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010¦\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010§\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010¨\u0002\u001a\u00020\u001bHÆ\u0003J\n\u0010©\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010ª\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010«\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010¬\u0002\u001a\u00020\u000bHÆ\u0003J\f\u0010\u00ad\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010®\u0002\u001a\u00020\u000bHÆ\u0003J\f\u0010¯\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010°\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010±\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010²\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010³\u0002\u001a\u00020\u000bHÆ\u0003J\f\u0010´\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010µ\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010¶\u0002\u001a\u00020\u001bHÆ\u0003J\n\u0010·\u0002\u001a\u00020\u001bHÆ\u0003J\n\u0010¸\u0002\u001a\u00020\u000bHÆ\u0003J\f\u0010¹\u0002\u001a\u0004\u0018\u000101HÆ\u0003J\n\u0010º\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010»\u0002\u001a\u00020\u000bHÆ\u0003J\f\u0010¼\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010½\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010¾\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010¿\u0002\u001a\u00020\u000bHÆ\u0003J\f\u0010À\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010Á\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010Â\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010Ã\u0002\u001a\u00020\u000bHÆ\u0003J\f\u0010Ä\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010Å\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010Æ\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010Ç\u0002\u001a\u00020@HÆ\u0003J\n\u0010È\u0002\u001a\u00020@HÆ\u0003J\n\u0010É\u0002\u001a\u00020@HÆ\u0003J\n\u0010Ê\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010Ë\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010Ì\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010Í\u0002\u001a\u00020GHÆ\u0003J\f\u0010Î\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010Ï\u0002\u001a\u00020\u001bHÆ\u0003J\n\u0010Ð\u0002\u001a\u00020\u001bHÆ\u0003J\n\u0010Ñ\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010Ò\u0002\u001a\u00020\u0003HÆ\u0003J\u0010\u0010Ó\u0002\u001a\b\u0012\u0004\u0012\u00020N0\u0005HÆ\u0003J\n\u0010Ô\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010Õ\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010Ö\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010×\u0002\u001a\u00020\u001bHÆ\u0003J\n\u0010Ø\u0002\u001a\u00020\u000bHÆ\u0003J\f\u0010Ù\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010Ú\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010Û\u0002\u001a\u00020WHÆ\u0003J\f\u0010Ü\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010Ý\u0002\u001a\u00020\u001bHÆ\u0003J\f\u0010Þ\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010ß\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u0010\u0010à\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u0010\u0010á\u0002\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\n\u0010â\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010ã\u0002\u001a\u00020`HÆ\u0003J\n\u0010ä\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010å\u0002\u001a\u00020\u000bHÆ\u0003J\u0010\u0010æ\u0002\u001a\b\u0012\u0004\u0012\u00020d0\u0005HÆ\u0003J\n\u0010ç\u0002\u001a\u00020\u0003HÆ\u0003J\u0010\u0010è\u0002\u001a\b\u0012\u0004\u0012\u00020g0\u0005HÆ\u0003J\n\u0010é\u0002\u001a\u00020\u000bHÆ\u0003J\f\u0010ê\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010ë\u0002\u001a\u0004\u0018\u00010kHÆ\u0003J\f\u0010ì\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010í\u0002\u001a\u00020\u000bHÆ\u0003J\f\u0010î\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010ï\u0002\u001a\b\u0012\u0004\u0012\u00020p0\u0005HÆ\u0003J\n\u0010ð\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010ñ\u0002\u001a\u00020\u0003HÆ\u0003J\f\u0010ò\u0002\u001a\u0004\u0018\u00010tHÆ\u0003J\u0010\u0010ó\u0002\u001a\b\u0012\u0004\u0012\u00020t0\u0005HÆ\u0003J\n\u0010ô\u0002\u001a\u00020\u000bHÆ\u0003J\f\u0010õ\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\f\u0010ö\u0002\u001a\u0004\u0018\u00010yHÆ\u0003J\n\u0010÷\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010ø\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010ù\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010ú\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010û\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010ü\u0002\u001a\u00020\u0003HÆ\u0003J\u0011\u0010ý\u0002\u001a\t\u0012\u0005\u0012\u00030\u0081\u00010\u0005HÆ\u0003J\n\u0010þ\u0002\u001a\u00020\u000bHÆ\u0003J\n\u0010ÿ\u0002\u001a\u00020\u0003HÆ\u0003J\n\u0010\u0080\u0003\u001a\u00020\u0003HÆ\u0003J\f\u0010\u0081\u0003\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\n\u0010\u0082\u0003\u001a\u00020WHÆ\u0003J\n\u0010\u0083\u0003\u001a\u00020\u000bHÆ\u0003J\n\u0010\u0084\u0003\u001a\u00020\u000bHÆ\u0003J\n\u0010\u0085\u0003\u001a\u00020\u000bHÆ\u0003J\n\u0010\u0086\u0003\u001a\u00020\u000bHÆ\u0003J\n\u0010\u0087\u0003\u001a\u00020\u000bHÆ\u0003J\n\u0010\u0088\u0003\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0089\u0003\u001a\u00030\u008e\u0001HÆ\u0003J\n\u0010\u008a\u0003\u001a\u00020\u000bHÆ\u0003J\u000b\u0010\u008b\u0003\u001a\u00030\u0091\u0001HÆ\u0003J\n\u0010\u008c\u0003\u001a\u00020\u000bHÆ\u0003J\u0081\u000b\u0010\u008d\u0003\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u0010\u001f\u001a\u00020\u001b2\b\b\u0002\u0010 \u001a\u00020\u000b2\b\b\u0002\u0010!\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020\u000b2\b\b\u0002\u0010#\u001a\u00020\u000b2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010%\u001a\u00020\u000b2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010'\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010(\u001a\u00020\u000b2\b\b\u0002\u0010)\u001a\u00020\u000b2\b\b\u0002\u0010*\u001a\u00020\u000b2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010,\u001a\u00020\u000b2\b\b\u0002\u0010-\u001a\u00020\u001b2\b\b\u0002\u0010.\u001a\u00020\u001b2\b\b\u0002\u0010/\u001a\u00020\u000b2\n\b\u0002\u00100\u001a\u0004\u0018\u0001012\b\b\u0002\u00102\u001a\u00020\u000b2\b\b\u0002\u00103\u001a\u00020\u000b2\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u00106\u001a\u00020\u000b2\b\b\u0002\u00107\u001a\u00020\u000b2\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u00109\u001a\u00020\u000b2\b\b\u0002\u0010:\u001a\u00020\u000b2\b\b\u0002\u0010;\u001a\u00020\u000b2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010?\u001a\u00020@2\b\b\u0002\u0010A\u001a\u00020@2\b\b\u0002\u0010B\u001a\u00020@2\b\b\u0002\u0010C\u001a\u00020\u000b2\b\b\u0002\u0010D\u001a\u00020\u000b2\b\b\u0002\u0010E\u001a\u00020\u000b2\b\b\u0002\u0010F\u001a\u00020G2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010I\u001a\u00020\u001b2\b\b\u0002\u0010J\u001a\u00020\u001b2\b\b\u0002\u0010K\u001a\u00020\u00032\b\b\u0002\u0010L\u001a\u00020\u00032\u000e\b\u0002\u0010M\u001a\b\u0012\u0004\u0012\u00020N0\u00052\b\b\u0002\u0010O\u001a\u00020\u00032\b\b\u0002\u0010P\u001a\u00020\u00032\b\b\u0002\u0010Q\u001a\u00020\u00032\b\b\u0002\u0010R\u001a\u00020\u001b2\b\b\u0002\u0010S\u001a\u00020\u000b2\n\b\u0002\u0010T\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010U\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010V\u001a\u00020W2\n\b\u0002\u0010X\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010Y\u001a\u00020\u001b2\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010[\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010^\u001a\u00020\u000b2\b\b\u0002\u0010_\u001a\u00020`2\b\b\u0002\u0010a\u001a\u00020\u000b2\b\b\u0002\u0010b\u001a\u00020\u000b2\u000e\b\u0002\u0010c\u001a\b\u0012\u0004\u0012\u00020d0\u00052\b\b\u0002\u0010e\u001a\u00020\u00032\u000e\b\u0002\u0010f\u001a\b\u0012\u0004\u0012\u00020g0\u00052\b\b\u0002\u0010h\u001a\u00020\u000b2\n\b\u0002\u0010i\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010j\u001a\u0004\u0018\u00010k2\n\b\u0002\u0010l\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010m\u001a\u00020\u000b2\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010o\u001a\b\u0012\u0004\u0012\u00020p0\u00052\b\b\u0002\u0010q\u001a\u00020\u000b2\b\b\u0002\u0010r\u001a\u00020\u00032\n\b\u0002\u0010s\u001a\u0004\u0018\u00010t2\u000e\b\u0002\u0010u\u001a\b\u0012\u0004\u0012\u00020t0\u00052\b\b\u0002\u0010v\u001a\u00020\u000b2\n\b\u0002\u0010w\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010x\u001a\u0004\u0018\u00010y2\b\b\u0002\u0010z\u001a\u00020\u000b2\b\b\u0002\u0010{\u001a\u00020\u00032\b\b\u0002\u0010|\u001a\u00020\u00032\b\b\u0002\u0010}\u001a\u00020\u000b2\b\b\u0002\u0010~\u001a\u00020\u000b2\b\b\u0002\u0010\u007f\u001a\u00020\u00032\u0010\b\u0002\u0010\u0080\u0001\u001a\t\u0012\u0005\u0012\u00030\u0081\u00010\u00052\t\b\u0002\u0010\u0082\u0001\u001a\u00020\u000b2\t\b\u0002\u0010\u0083\u0001\u001a\u00020\u00032\t\b\u0002\u0010\u0084\u0001\u001a\u00020\u00032\u000b\b\u0002\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u00032\t\b\u0002\u0010\u0086\u0001\u001a\u00020W2\t\b\u0002\u0010\u0087\u0001\u001a\u00020\u000b2\t\b\u0002\u0010\u0088\u0001\u001a\u00020\u000b2\t\b\u0002\u0010\u0089\u0001\u001a\u00020\u000b2\t\b\u0002\u0010\u008a\u0001\u001a\u00020\u000b2\t\b\u0002\u0010\u008b\u0001\u001a\u00020\u000b2\t\b\u0002\u0010\u008c\u0001\u001a\u00020\u00032\n\b\u0002\u0010\u008d\u0001\u001a\u00030\u008e\u00012\t\b\u0002\u0010\u008f\u0001\u001a\u00020\u000b2\n\b\u0002\u0010\u0090\u0001\u001a\u00030\u0091\u00012\t\b\u0002\u0010\u0092\u0001\u001a\u00020\u000bHÆ\u0001J\u0016\u0010\u008e\u0003\u001a\u00020\u000b2\t\u0010\u008f\u0003\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\u000b\u0010\u0090\u0003\u001a\u00020\u001bHÖ\u0081\u0004J\u000b\u0010\u0091\u0003\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\b\u0095\u0001\u0010\u0096\u0001R\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\n\n\u0000\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001R\u0013\u0010\u0006\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\b\u0099\u0001\u0010\u0096\u0001R\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\n\n\u0000\u001a\u0006\b\u009a\u0001\u0010\u0098\u0001R\u0013\u0010\b\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\b\u009b\u0001\u0010\u0096\u0001R\u0013\u0010\t\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\b\u009c\u0001\u0010\u0096\u0001R\u0013\u0010\n\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R\u0013\u0010\f\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b\u009f\u0001\u0010\u009e\u0001R\u0013\u0010\r\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\b \u0001\u0010\u0096\u0001R\u0013\u0010\u000e\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\b¡\u0001\u0010\u0096\u0001R\u0019\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\n\n\u0000\u001a\u0006\b¢\u0001\u0010\u0098\u0001R\u0013\u0010\u0010\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\b£\u0001\u0010\u0096\u0001R\u0013\u0010\u0011\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\b¤\u0001\u0010\u0096\u0001R\u0013\u0010\u0012\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\b¥\u0001\u0010\u0096\u0001R\u0013\u0010\u0013\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\b¦\u0001\u0010\u0096\u0001R\u0013\u0010\u0014\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\b§\u0001\u0010\u0096\u0001R\u0013\u0010\u0015\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b¨\u0001\u0010\u009e\u0001R\u0013\u0010\u0016\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b©\u0001\u0010\u009e\u0001R\u0013\u0010\u0017\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\bª\u0001\u0010\u0096\u0001R\u0013\u0010\u0018\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b«\u0001\u0010\u009e\u0001R\u0013\u0010\u0019\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b¬\u0001\u0010\u009e\u0001R\u0013\u0010\u001a\u001a\u00020\u001b¢\u0006\n\n\u0000\u001a\u0006\b\u00ad\u0001\u0010®\u0001R\u0013\u0010\u001c\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b¯\u0001\u0010\u009e\u0001R\u0013\u0010\u001d\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b°\u0001\u0010\u009e\u0001R\u0013\u0010\u001e\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b±\u0001\u0010\u009e\u0001R\u0013\u0010\u001f\u001a\u00020\u001b¢\u0006\n\n\u0000\u001a\u0006\b²\u0001\u0010®\u0001R\u0013\u0010 \u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b³\u0001\u0010\u009e\u0001R\u0013\u0010!\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b´\u0001\u0010\u009e\u0001R\u0013\u0010\"\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\bµ\u0001\u0010\u009e\u0001R\u0012\u0010#\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\b#\u0010\u009e\u0001R\u0015\u0010$\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\b¶\u0001\u0010\u0096\u0001R\u0013\u0010%\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b·\u0001\u0010\u009e\u0001R\u0015\u0010&\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\b¸\u0001\u0010\u0096\u0001R\u0015\u0010'\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\b¹\u0001\u0010\u0096\u0001R\u0013\u0010(\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\bº\u0001\u0010\u009e\u0001R\u0012\u0010)\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\b)\u0010\u009e\u0001R\u0012\u0010*\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\b*\u0010\u009e\u0001R\u0015\u0010+\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\b»\u0001\u0010\u0096\u0001R\u0013\u0010,\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b¼\u0001\u0010\u009e\u0001R\u0013\u0010-\u001a\u00020\u001b¢\u0006\n\n\u0000\u001a\u0006\b½\u0001\u0010®\u0001R\u0013\u0010.\u001a\u00020\u001b¢\u0006\n\n\u0000\u001a\u0006\b¾\u0001\u0010®\u0001R\u0012\u0010/\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\b/\u0010\u009e\u0001R\u0015\u00100\u001a\u0004\u0018\u000101¢\u0006\n\n\u0000\u001a\u0006\b¿\u0001\u0010À\u0001R\u0012\u00102\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\b2\u0010\u009e\u0001R\u0012\u00103\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\b3\u0010\u009e\u0001R\u0015\u00104\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\bÁ\u0001\u0010\u0096\u0001R\u0015\u00105\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\bÂ\u0001\u0010\u0096\u0001R\u0012\u00106\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\b6\u0010\u009e\u0001R\u0013\u00107\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\bÃ\u0001\u0010\u009e\u0001R\u0015\u00108\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\bÄ\u0001\u0010\u0096\u0001R\u0012\u00109\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\b9\u0010\u009e\u0001R\u0012\u0010:\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\b:\u0010\u009e\u0001R\u0012\u0010;\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\b;\u0010\u009e\u0001R\u0015\u0010<\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\bÅ\u0001\u0010\u0096\u0001R\u0015\u0010=\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\bÆ\u0001\u0010\u0096\u0001R\u0015\u0010>\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\bÇ\u0001\u0010\u0096\u0001R\u0013\u0010?\u001a\u00020@¢\u0006\n\n\u0000\u001a\u0006\bÈ\u0001\u0010É\u0001R\u0013\u0010A\u001a\u00020@¢\u0006\n\n\u0000\u001a\u0006\bÊ\u0001\u0010É\u0001R\u0013\u0010B\u001a\u00020@¢\u0006\n\n\u0000\u001a\u0006\bË\u0001\u0010É\u0001R\u0013\u0010C\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\bÌ\u0001\u0010\u009e\u0001R\u0013\u0010D\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\bÍ\u0001\u0010\u009e\u0001R\u0012\u0010E\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\bE\u0010\u009e\u0001R\u0013\u0010F\u001a\u00020G¢\u0006\n\n\u0000\u001a\u0006\bÎ\u0001\u0010Ï\u0001R\u0015\u0010H\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\bÐ\u0001\u0010\u0096\u0001R\u0013\u0010I\u001a\u00020\u001b¢\u0006\n\n\u0000\u001a\u0006\bÑ\u0001\u0010®\u0001R\u0013\u0010J\u001a\u00020\u001b¢\u0006\n\n\u0000\u001a\u0006\bÒ\u0001\u0010®\u0001R\u0013\u0010K\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\bÓ\u0001\u0010\u0096\u0001R\u0013\u0010L\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\bÔ\u0001\u0010\u0096\u0001R\u0019\u0010M\u001a\b\u0012\u0004\u0012\u00020N0\u0005¢\u0006\n\n\u0000\u001a\u0006\bÕ\u0001\u0010\u0098\u0001R\u0013\u0010O\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\bÖ\u0001\u0010\u0096\u0001R\u0013\u0010P\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\b×\u0001\u0010\u0096\u0001R\u0013\u0010Q\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\bØ\u0001\u0010\u0096\u0001R\u0013\u0010R\u001a\u00020\u001b¢\u0006\n\n\u0000\u001a\u0006\bÙ\u0001\u0010®\u0001R\u0012\u0010S\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\bS\u0010\u009e\u0001R\u0015\u0010T\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\bÚ\u0001\u0010\u0096\u0001R\u0015\u0010U\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\bÛ\u0001\u0010\u0096\u0001R\u0013\u0010V\u001a\u00020W¢\u0006\n\n\u0000\u001a\u0006\bÜ\u0001\u0010Ý\u0001R\u0015\u0010X\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\bÞ\u0001\u0010\u0096\u0001R\u0013\u0010Y\u001a\u00020\u001b¢\u0006\n\n\u0000\u001a\u0006\bß\u0001\u0010®\u0001R\u0015\u0010Z\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\bà\u0001\u0010\u0096\u0001R\u0019\u0010[\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\n\n\u0000\u001a\u0006\bá\u0001\u0010\u0098\u0001R\u0019\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\n\n\u0000\u001a\u0006\bâ\u0001\u0010\u0098\u0001R\u0019\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\n\n\u0000\u001a\u0006\bã\u0001\u0010\u0098\u0001R\u0012\u0010^\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\b^\u0010\u009e\u0001R\u0013\u0010_\u001a\u00020`¢\u0006\n\n\u0000\u001a\u0006\bä\u0001\u0010å\u0001R\u0013\u0010a\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\bæ\u0001\u0010\u009e\u0001R\u0013\u0010b\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\bç\u0001\u0010\u009e\u0001R\u0019\u0010c\u001a\b\u0012\u0004\u0012\u00020d0\u0005¢\u0006\n\n\u0000\u001a\u0006\bè\u0001\u0010\u0098\u0001R\u0013\u0010e\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\bé\u0001\u0010\u0096\u0001R\u0019\u0010f\u001a\b\u0012\u0004\u0012\u00020g0\u0005¢\u0006\n\n\u0000\u001a\u0006\bê\u0001\u0010\u0098\u0001R\u0012\u0010h\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\bh\u0010\u009e\u0001R\u0015\u0010i\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\bë\u0001\u0010\u0096\u0001R\u0015\u0010j\u001a\u0004\u0018\u00010k¢\u0006\n\n\u0000\u001a\u0006\bì\u0001\u0010í\u0001R\u0015\u0010l\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\bî\u0001\u0010\u0096\u0001R\u0012\u0010m\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\bm\u0010\u009e\u0001R\u0015\u0010n\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\bï\u0001\u0010\u0096\u0001R\u0019\u0010o\u001a\b\u0012\u0004\u0012\u00020p0\u0005¢\u0006\n\n\u0000\u001a\u0006\bð\u0001\u0010\u0098\u0001R\u0012\u0010q\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\bq\u0010\u009e\u0001R\u0013\u0010r\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\bñ\u0001\u0010\u0096\u0001R\u0015\u0010s\u001a\u0004\u0018\u00010t¢\u0006\n\n\u0000\u001a\u0006\bò\u0001\u0010ó\u0001R\u0019\u0010u\u001a\b\u0012\u0004\u0012\u00020t0\u0005¢\u0006\n\n\u0000\u001a\u0006\bô\u0001\u0010\u0098\u0001R\u0012\u0010v\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\bv\u0010\u009e\u0001R\u0015\u0010w\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\bõ\u0001\u0010\u0096\u0001R\u0015\u0010x\u001a\u0004\u0018\u00010y¢\u0006\n\n\u0000\u001a\u0006\bö\u0001\u0010÷\u0001R\u0012\u0010z\u001a\u00020\u000b¢\u0006\t\n\u0000\u001a\u0005\bz\u0010\u009e\u0001R\u0013\u0010{\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\bø\u0001\u0010\u0096\u0001R\u0013\u0010|\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\bù\u0001\u0010\u0096\u0001R\u0013\u0010}\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\bú\u0001\u0010\u009e\u0001R\u0013\u0010~\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\bû\u0001\u0010\u009e\u0001R\u0013\u0010\u007f\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\bü\u0001\u0010\u0096\u0001R\u001b\u0010\u0080\u0001\u001a\t\u0012\u0005\u0012\u00030\u0081\u00010\u0005¢\u0006\n\n\u0000\u001a\u0006\bý\u0001\u0010\u0098\u0001R\u0014\u0010\u0082\u0001\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\bþ\u0001\u0010\u009e\u0001R\u0014\u0010\u0083\u0001\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\bÿ\u0001\u0010\u0096\u0001R\u0014\u0010\u0084\u0001\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\b\u0080\u0002\u0010\u0096\u0001R\u0016\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0000\u001a\u0006\b\u0081\u0002\u0010\u0096\u0001R\u0014\u0010\u0086\u0001\u001a\u00020W¢\u0006\n\n\u0000\u001a\u0006\b\u0082\u0002\u0010Ý\u0001R\u0014\u0010\u0087\u0001\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b\u0083\u0002\u0010\u009e\u0001R\u0014\u0010\u0088\u0001\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b\u0084\u0002\u0010\u009e\u0001R\u0014\u0010\u0089\u0001\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b\u0085\u0002\u0010\u009e\u0001R\u0014\u0010\u008a\u0001\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b\u0086\u0002\u0010\u009e\u0001R\u0014\u0010\u008b\u0001\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b\u0087\u0002\u0010\u009e\u0001R\u0014\u0010\u008c\u0001\u001a\u00020\u0003¢\u0006\n\n\u0000\u001a\u0006\b\u0088\u0002\u0010\u0096\u0001R\u0015\u0010\u008d\u0001\u001a\u00030\u008e\u0001¢\u0006\n\n\u0000\u001a\u0006\b\u0089\u0002\u0010\u008a\u0002R\u0014\u0010\u008f\u0001\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b\u008b\u0002\u0010\u009e\u0001R\u0015\u0010\u0090\u0001\u001a\u00030\u0091\u0001¢\u0006\n\n\u0000\u001a\u0006\b\u008c\u0002\u0010\u008d\u0002R\u0014\u0010\u0092\u0001\u001a\u00020\u000b¢\u0006\n\n\u0000\u001a\u0006\b\u008e\u0002\u0010\u009e\u0001¨\u0006\u0092\u0003"}, d2 = {"Lcom/arflix/tv/ui/screens/settings/SettingsUiState;", "", "defaultSubtitle", "", "subtitleOptions", "", "defaultAudioLanguage", "audioLanguageOptions", "cardLayoutMode", "frameRateMatchingMode", "autoPlayNext", "", "autoPlaySingleSource", "autoPlayMinQuality", "dnsProvider", "dnsProviderOptions", "customUserAgent", "subtitleSize", "subtitleColor", "subtitleStyle", "subtitleOffset", "subtitleStylized", "filterSubtitlesByLanguage", "secondarySubtitle", "trailerAutoPlay", "trailerSoundEnabled", "trailerDelaySeconds", "", "trailerInCards", "showBudget", "showEpisodeRatings", "volumeBoostDb", "showLoadingStats", "diagnosticsSharingEnabled", "includeSpecials", "isLoggedIn", "accountEmail", "showCloudPairDialog", "cloudUserCode", "cloudVerificationUrl", "showCloudEmailPasswordDialog", "isCloudAuthWorking", "isForceCloudSyncing", "lastCloudSyncStatus", "shouldSwitchProfile", "watchlistCount", "historyCount", "isTraktAuthenticated", "traktCode", "Lcom/arflix/tv/data/api/TraktDeviceCode;", "isTraktAuthStarting", "isTraktPolling", "traktExpiration", "traktUsername", "isMdbListConnected", "mdbListConnecting", "mdbListUsername", "isSimklConnected", "isSimklAuthStarting", "isSimklPolling", "simklUserCode", "simklVerificationUrl", "simklUsername", "trackingWatchlistReadMode", "Lcom/arflix/tv/data/repository/sync/TrackingReadMode;", "trackingContinueReadMode", "trackingWatchedReadMode", "trackingWriteToTrakt", "trackingWriteToSimkl", "isSyncing", "syncProgress", "Lcom/arflix/tv/data/repository/SyncProgress;", "lastSyncTime", "syncedMovies", "syncedEpisodes", "iptvM3uUrl", "iptvEpgUrl", "iptvPlaylists", "Lcom/arflix/tv/data/repository/IptvPlaylistEntry;", "iptvStalkerUrl", "iptvStalkerMac", "iptvSortOrder", "iptvChannelCount", "isIptvLoading", "iptvError", "iptvStatusMessage", "iptvStatusType", "Lcom/arflix/tv/ui/screens/settings/ToastType;", "iptvProgressText", "iptvProgressPercent", "iptvSelectedPlaylistId", "iptvAvailableGroups", "iptvHiddenGroups", "iptvGroupOrder", "isSelfUpdateSupported", "updateStatus", "Lcom/arflix/tv/updater/UpdateStatus;", "showAppUpdateDialog", "showUnknownSourcesDialog", "catalogs", "Lcom/arflix/tv/data/model/CatalogConfig;", "catalogSearchQuery", "catalogSearchResults", "Lcom/arflix/tv/data/model/CatalogDiscoveryResult;", "isCatalogSearching", "catalogSearchError", "pendingPackManifest", "Lcom/arflix/tv/data/model/CatalogPackManifest;", "pendingPackUrl", "isPackLoading", "packError", "addons", "Lcom/arflix/tv/data/model/Addon;", "isRefreshingAddons", "torrServerBaseUrl", "homeServerConnection", "Lcom/arflix/tv/data/repository/HomeServerConnection;", "homeServerConnections", "isHomeServerConnecting", "homeServerError", "plexHomeServerAuth", "Lcom/arflix/tv/data/repository/PlexPinAuthSession;", "isPlexHomeServerPolling", "contentLanguage", "deviceModeOverride", "skipProfileSelection", "oledBlackBackground", "clockFormat", "qualityFilters", "Lcom/arflix/tv/data/model/QualityFilterConfig;", "spoilerBlurEnabled", "accentColor", "qualityFilterPresetLabel", "toastMessage", "toastType", "subtitleAiEnabled", "subtitleAiAutoSelect", "subtitleAiFindBestMatch", "subtitlePreloadEnabled", "dolbyVisionCompatEnabled", "subtitleAiApiKey", "subtitleAiModel", "Lcom/arflix/tv/ui/screens/player/SubtitleAiModel;", "subtitleRemoveHearingImpaired", "aiKeyServerState", "Lcom/arflix/tv/ui/screens/settings/AiKeyServerState;", "smoothScrolling", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;ZZIZZZIZZZZLjava/lang/String;ZLjava/lang/String;Ljava/lang/String;ZZZLjava/lang/String;ZIIZLcom/arflix/tv/data/api/TraktDeviceCode;ZZLjava/lang/String;Ljava/lang/String;ZZLjava/lang/String;ZZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;Lcom/arflix/tv/data/repository/sync/TrackingReadMode;ZZZLcom/arflix/tv/data/repository/SyncProgress;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZLjava/lang/String;Ljava/lang/String;Lcom/arflix/tv/ui/screens/settings/ToastType;Ljava/lang/String;ILjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLcom/arflix/tv/updater/UpdateStatus;ZZLjava/util/List;Ljava/lang/String;Ljava/util/List;ZLjava/lang/String;Lcom/arflix/tv/data/model/CatalogPackManifest;Ljava/lang/String;ZLjava/lang/String;Ljava/util/List;ZLjava/lang/String;Lcom/arflix/tv/data/repository/HomeServerConnection;Ljava/util/List;ZLjava/lang/String;Lcom/arflix/tv/data/repository/PlexPinAuthSession;ZLjava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/util/List;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/ui/screens/settings/ToastType;ZZZZZLjava/lang/String;Lcom/arflix/tv/ui/screens/player/SubtitleAiModel;ZLcom/arflix/tv/ui/screens/settings/AiKeyServerState;Z)V", "getDefaultSubtitle", "()Ljava/lang/String;", "getSubtitleOptions", "()Ljava/util/List;", "getDefaultAudioLanguage", "getAudioLanguageOptions", "getCardLayoutMode", "getFrameRateMatchingMode", "getAutoPlayNext", "()Z", "getAutoPlaySingleSource", "getAutoPlayMinQuality", "getDnsProvider", "getDnsProviderOptions", "getCustomUserAgent", "getSubtitleSize", "getSubtitleColor", "getSubtitleStyle", "getSubtitleOffset", "getSubtitleStylized", "getFilterSubtitlesByLanguage", "getSecondarySubtitle", "getTrailerAutoPlay", "getTrailerSoundEnabled", "getTrailerDelaySeconds", "()I", "getTrailerInCards", "getShowBudget", "getShowEpisodeRatings", "getVolumeBoostDb", "getShowLoadingStats", "getDiagnosticsSharingEnabled", "getIncludeSpecials", "getAccountEmail", "getShowCloudPairDialog", "getCloudUserCode", "getCloudVerificationUrl", "getShowCloudEmailPasswordDialog", "getLastCloudSyncStatus", "getShouldSwitchProfile", "getWatchlistCount", "getHistoryCount", "getTraktCode", "()Lcom/arflix/tv/data/api/TraktDeviceCode;", "getTraktExpiration", "getTraktUsername", "getMdbListConnecting", "getMdbListUsername", "getSimklUserCode", "getSimklVerificationUrl", "getSimklUsername", "getTrackingWatchlistReadMode", "()Lcom/arflix/tv/data/repository/sync/TrackingReadMode;", "getTrackingContinueReadMode", "getTrackingWatchedReadMode", "getTrackingWriteToTrakt", "getTrackingWriteToSimkl", "getSyncProgress", "()Lcom/arflix/tv/data/repository/SyncProgress;", "getLastSyncTime", "getSyncedMovies", "getSyncedEpisodes", "getIptvM3uUrl", "getIptvEpgUrl", "getIptvPlaylists", "getIptvStalkerUrl", "getIptvStalkerMac", "getIptvSortOrder", "getIptvChannelCount", "getIptvError", "getIptvStatusMessage", "getIptvStatusType", "()Lcom/arflix/tv/ui/screens/settings/ToastType;", "getIptvProgressText", "getIptvProgressPercent", "getIptvSelectedPlaylistId", "getIptvAvailableGroups", "getIptvHiddenGroups", "getIptvGroupOrder", "getUpdateStatus", "()Lcom/arflix/tv/updater/UpdateStatus;", "getShowAppUpdateDialog", "getShowUnknownSourcesDialog", "getCatalogs", "getCatalogSearchQuery", "getCatalogSearchResults", "getCatalogSearchError", "getPendingPackManifest", "()Lcom/arflix/tv/data/model/CatalogPackManifest;", "getPendingPackUrl", "getPackError", "getAddons", "getTorrServerBaseUrl", "getHomeServerConnection", "()Lcom/arflix/tv/data/repository/HomeServerConnection;", "getHomeServerConnections", "getHomeServerError", "getPlexHomeServerAuth", "()Lcom/arflix/tv/data/repository/PlexPinAuthSession;", "getContentLanguage", "getDeviceModeOverride", "getSkipProfileSelection", "getOledBlackBackground", "getClockFormat", "getQualityFilters", "getSpoilerBlurEnabled", "getAccentColor", "getQualityFilterPresetLabel", "getToastMessage", "getToastType", "getSubtitleAiEnabled", "getSubtitleAiAutoSelect", "getSubtitleAiFindBestMatch", "getSubtitlePreloadEnabled", "getDolbyVisionCompatEnabled", "getSubtitleAiApiKey", "getSubtitleAiModel", "()Lcom/arflix/tv/ui/screens/player/SubtitleAiModel;", "getSubtitleRemoveHearingImpaired", "getAiKeyServerState", "()Lcom/arflix/tv/ui/screens/settings/AiKeyServerState;", "getSmoothScrolling", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component50", "component51", "component52", "component53", "component54", "component55", "component56", "component57", "component58", "component59", "component60", "component61", "component62", "component63", "component64", "component65", "component66", "component67", "component68", "component69", "component70", "component71", "component72", "component73", "component74", "component75", "component76", "component77", "component78", "component79", "component80", "component81", "component82", "component83", "component84", "component85", "component86", "component87", "component88", "component89", "component90", "component91", "component92", "component93", "component94", "component95", "component96", "component97", "component98", "component99", "component100", "component101", "component102", "component103", "component104", "component105", "component106", "component107", "component108", "component109", "component110", "component111", "component112", "component113", "component114", "component115", "component116", "component117", "component118", "component119", "component120", "component121", "component122", "component123", "component124", "component125", "component126", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SettingsUiState {
    public static final int $stable = 8;
    private final String accentColor;
    private final String accountEmail;
    private final List<Addon> addons;
    private final AiKeyServerState aiKeyServerState;
    private final List<String> audioLanguageOptions;
    private final String autoPlayMinQuality;
    private final boolean autoPlayNext;
    private final boolean autoPlaySingleSource;
    private final String cardLayoutMode;
    private final String catalogSearchError;
    private final String catalogSearchQuery;
    private final List<CatalogDiscoveryResult> catalogSearchResults;
    private final List<CatalogConfig> catalogs;
    private final String clockFormat;
    private final String cloudUserCode;
    private final String cloudVerificationUrl;
    private final String contentLanguage;
    private final String customUserAgent;
    private final String defaultAudioLanguage;
    private final String defaultSubtitle;
    private final String deviceModeOverride;
    private final boolean diagnosticsSharingEnabled;
    private final String dnsProvider;
    private final List<String> dnsProviderOptions;
    private final boolean dolbyVisionCompatEnabled;
    private final boolean filterSubtitlesByLanguage;
    private final String frameRateMatchingMode;
    private final int historyCount;
    private final HomeServerConnection homeServerConnection;
    private final List<HomeServerConnection> homeServerConnections;
    private final String homeServerError;
    private final boolean includeSpecials;
    private final List<String> iptvAvailableGroups;
    private final int iptvChannelCount;
    private final String iptvEpgUrl;
    private final String iptvError;
    private final List<String> iptvGroupOrder;
    private final List<String> iptvHiddenGroups;
    private final String iptvM3uUrl;
    private final List<IptvPlaylistEntry> iptvPlaylists;
    private final int iptvProgressPercent;
    private final String iptvProgressText;
    private final String iptvSelectedPlaylistId;
    private final String iptvSortOrder;
    private final String iptvStalkerMac;
    private final String iptvStalkerUrl;
    private final String iptvStatusMessage;
    private final ToastType iptvStatusType;
    private final boolean isCatalogSearching;
    private final boolean isCloudAuthWorking;
    private final boolean isForceCloudSyncing;
    private final boolean isHomeServerConnecting;
    private final boolean isIptvLoading;
    private final boolean isLoggedIn;
    private final boolean isMdbListConnected;
    private final boolean isPackLoading;
    private final boolean isPlexHomeServerPolling;
    private final boolean isRefreshingAddons;
    private final boolean isSelfUpdateSupported;
    private final boolean isSimklAuthStarting;
    private final boolean isSimklConnected;
    private final boolean isSimklPolling;
    private final boolean isSyncing;
    private final boolean isTraktAuthStarting;
    private final boolean isTraktAuthenticated;
    private final boolean isTraktPolling;
    private final String lastCloudSyncStatus;
    private final String lastSyncTime;
    private final boolean mdbListConnecting;
    private final String mdbListUsername;
    private final boolean oledBlackBackground;
    private final String packError;
    private final CatalogPackManifest pendingPackManifest;
    private final String pendingPackUrl;
    private final PlexPinAuthSession plexHomeServerAuth;
    private final String qualityFilterPresetLabel;
    private final List<QualityFilterConfig> qualityFilters;
    private final String secondarySubtitle;
    private final boolean shouldSwitchProfile;
    private final boolean showAppUpdateDialog;
    private final boolean showBudget;
    private final boolean showCloudEmailPasswordDialog;
    private final boolean showCloudPairDialog;
    private final boolean showEpisodeRatings;
    private final boolean showLoadingStats;
    private final boolean showUnknownSourcesDialog;
    private final String simklUserCode;
    private final String simklUsername;
    private final String simklVerificationUrl;
    private final boolean skipProfileSelection;
    private final boolean smoothScrolling;
    private final boolean spoilerBlurEnabled;
    private final String subtitleAiApiKey;
    private final boolean subtitleAiAutoSelect;
    private final boolean subtitleAiEnabled;
    private final boolean subtitleAiFindBestMatch;
    private final SubtitleAiModel subtitleAiModel;
    private final String subtitleColor;
    private final String subtitleOffset;
    private final List<String> subtitleOptions;
    private final boolean subtitlePreloadEnabled;
    private final boolean subtitleRemoveHearingImpaired;
    private final String subtitleSize;
    private final String subtitleStyle;
    private final boolean subtitleStylized;
    private final SyncProgress syncProgress;
    private final int syncedEpisodes;
    private final int syncedMovies;
    private final String toastMessage;
    private final ToastType toastType;
    private final String torrServerBaseUrl;
    private final TrackingReadMode trackingContinueReadMode;
    private final TrackingReadMode trackingWatchedReadMode;
    private final TrackingReadMode trackingWatchlistReadMode;
    private final boolean trackingWriteToSimkl;
    private final boolean trackingWriteToTrakt;
    private final boolean trailerAutoPlay;
    private final int trailerDelaySeconds;
    private final boolean trailerInCards;
    private final boolean trailerSoundEnabled;
    private final TraktDeviceCode traktCode;
    private final String traktExpiration;
    private final String traktUsername;
    private final UpdateStatus updateStatus;
    private final int volumeBoostDb;
    private final int watchlistCount;

    public SettingsUiState() {
        this(null, null, null, null, null, null, false, false, null, null, null, null, null, null, null, null, false, false, null, false, false, 0, false, false, false, 0, false, false, false, false, null, false, null, null, false, false, false, null, false, 0, 0, false, null, false, false, null, null, false, false, null, false, false, false, null, null, null, null, null, null, false, false, false, null, null, 0, 0, null, null, null, null, null, null, 0, false, null, null, null, null, 0, null, null, null, null, false, null, false, false, null, null, null, false, null, null, null, false, null, null, false, null, null, null, false, null, null, false, null, null, false, false, null, null, false, null, null, null, null, false, false, false, false, false, null, null, false, null, false, -1, -1, -1, 1073741823, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SettingsUiState copy$default(SettingsUiState settingsUiState, String str, List list, String str2, List list2, String str3, String str4, boolean z, boolean z5, String str5, String str6, List list3, String str7, String str8, String str9, String str10, String str11, boolean z10, boolean z11, String str12, boolean z12, boolean z13, int i10, boolean z14, boolean z15, boolean z16, int i11, boolean z17, boolean z18, boolean z19, boolean z20, String str13, boolean z21, String str14, String str15, boolean z22, boolean z23, boolean z24, String str16, boolean z25, int i12, int i13, boolean z26, TraktDeviceCode traktDeviceCode, boolean z27, boolean z28, String str17, String str18, boolean z29, boolean z30, String str19, boolean z31, boolean z32, boolean z33, String str20, String str21, String str22, TrackingReadMode trackingReadMode, TrackingReadMode trackingReadMode2, TrackingReadMode trackingReadMode3, boolean z34, boolean z35, boolean z36, SyncProgress syncProgress, String str23, int i14, int i15, String str24, String str25, List list4, String str26, String str27, String str28, int i16, boolean z37, String str29, String str30, ToastType toastType, String str31, int i17, String str32, List list5, List list6, List list7, boolean z38, UpdateStatus updateStatus, boolean z39, boolean z40, List list8, String str33, List list9, boolean z41, String str34, CatalogPackManifest catalogPackManifest, String str35, boolean z42, String str36, List list10, boolean z43, String str37, HomeServerConnection homeServerConnection, List list11, boolean z44, String str38, PlexPinAuthSession plexPinAuthSession, boolean z45, String str39, String str40, boolean z46, boolean z47, String str41, List list12, boolean z48, String str42, String str43, String str44, ToastType toastType2, boolean z49, boolean z50, boolean z51, boolean z52, boolean z53, String str45, SubtitleAiModel subtitleAiModel, boolean z54, AiKeyServerState aiKeyServerState, boolean z55, int i18, int i19, int i20, int i21, Object obj) {
        String str46 = (i18 & 1) != 0 ? settingsUiState.defaultSubtitle : str;
        List list13 = (i18 & 2) != 0 ? settingsUiState.subtitleOptions : list;
        String str47 = (i18 & 4) != 0 ? settingsUiState.defaultAudioLanguage : str2;
        List list14 = (i18 & 8) != 0 ? settingsUiState.audioLanguageOptions : list2;
        String str48 = (i18 & 16) != 0 ? settingsUiState.cardLayoutMode : str3;
        String str49 = (i18 & 32) != 0 ? settingsUiState.frameRateMatchingMode : str4;
        boolean z56 = (i18 & 64) != 0 ? settingsUiState.autoPlayNext : z;
        boolean z57 = (i18 & 128) != 0 ? settingsUiState.autoPlaySingleSource : z5;
        String str50 = (i18 & 256) != 0 ? settingsUiState.autoPlayMinQuality : str5;
        String str51 = (i18 & 512) != 0 ? settingsUiState.dnsProvider : str6;
        List list15 = (i18 & 1024) != 0 ? settingsUiState.dnsProviderOptions : list3;
        String str52 = str46;
        String str53 = (i18 & 2048) != 0 ? settingsUiState.customUserAgent : str7;
        String str54 = (i18 & 4096) != 0 ? settingsUiState.subtitleSize : str8;
        String str55 = (i18 & 8192) != 0 ? settingsUiState.subtitleColor : str9;
        String str56 = (i18 & 16384) != 0 ? settingsUiState.subtitleStyle : str10;
        String str57 = (i18 & 32768) != 0 ? settingsUiState.subtitleOffset : str11;
        boolean z58 = (i18 & 65536) != 0 ? settingsUiState.subtitleStylized : z10;
        boolean z59 = (i18 & 131072) != 0 ? settingsUiState.filterSubtitlesByLanguage : z11;
        String str58 = (i18 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? settingsUiState.secondarySubtitle : str12;
        boolean z60 = (i18 & 524288) != 0 ? settingsUiState.trailerAutoPlay : z12;
        boolean z61 = (i18 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? settingsUiState.trailerSoundEnabled : z13;
        int i22 = (i18 & 2097152) != 0 ? settingsUiState.trailerDelaySeconds : i10;
        boolean z62 = (i18 & 4194304) != 0 ? settingsUiState.trailerInCards : z14;
        boolean z63 = (i18 & 8388608) != 0 ? settingsUiState.showBudget : z15;
        boolean z64 = (i18 & 16777216) != 0 ? settingsUiState.showEpisodeRatings : z16;
        int i23 = (i18 & 33554432) != 0 ? settingsUiState.volumeBoostDb : i11;
        boolean z65 = (i18 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? settingsUiState.showLoadingStats : z17;
        boolean z66 = (i18 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? settingsUiState.diagnosticsSharingEnabled : z18;
        boolean z67 = (i18 & 268435456) != 0 ? settingsUiState.includeSpecials : z19;
        boolean z68 = (i18 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? settingsUiState.isLoggedIn : z20;
        String str59 = (i18 & 1073741824) != 0 ? settingsUiState.accountEmail : str13;
        boolean z69 = (i18 & Integer.MIN_VALUE) != 0 ? settingsUiState.showCloudPairDialog : z21;
        String str60 = (i19 & 1) != 0 ? settingsUiState.cloudUserCode : str14;
        String str61 = (i19 & 2) != 0 ? settingsUiState.cloudVerificationUrl : str15;
        boolean z70 = (i19 & 4) != 0 ? settingsUiState.showCloudEmailPasswordDialog : z22;
        boolean z71 = (i19 & 8) != 0 ? settingsUiState.isCloudAuthWorking : z23;
        boolean z72 = (i19 & 16) != 0 ? settingsUiState.isForceCloudSyncing : z24;
        String str62 = (i19 & 32) != 0 ? settingsUiState.lastCloudSyncStatus : str16;
        boolean z73 = (i19 & 64) != 0 ? settingsUiState.shouldSwitchProfile : z25;
        int i24 = (i19 & 128) != 0 ? settingsUiState.watchlistCount : i12;
        int i25 = (i19 & 256) != 0 ? settingsUiState.historyCount : i13;
        boolean z74 = (i19 & 512) != 0 ? settingsUiState.isTraktAuthenticated : z26;
        TraktDeviceCode traktDeviceCode2 = (i19 & 1024) != 0 ? settingsUiState.traktCode : traktDeviceCode;
        boolean z75 = (i19 & 2048) != 0 ? settingsUiState.isTraktAuthStarting : z27;
        boolean z76 = (i19 & 4096) != 0 ? settingsUiState.isTraktPolling : z28;
        String str63 = (i19 & 8192) != 0 ? settingsUiState.traktExpiration : str17;
        String str64 = (i19 & 16384) != 0 ? settingsUiState.traktUsername : str18;
        boolean z77 = (i19 & 32768) != 0 ? settingsUiState.isMdbListConnected : z29;
        boolean z78 = (i19 & 65536) != 0 ? settingsUiState.mdbListConnecting : z30;
        String str65 = (i19 & 131072) != 0 ? settingsUiState.mdbListUsername : str19;
        boolean z79 = (i19 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? settingsUiState.isSimklConnected : z31;
        boolean z80 = (i19 & 524288) != 0 ? settingsUiState.isSimklAuthStarting : z32;
        boolean z81 = (i19 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? settingsUiState.isSimklPolling : z33;
        String str66 = (i19 & 2097152) != 0 ? settingsUiState.simklUserCode : str20;
        String str67 = (i19 & 4194304) != 0 ? settingsUiState.simklVerificationUrl : str21;
        String str68 = (i19 & 8388608) != 0 ? settingsUiState.simklUsername : str22;
        TrackingReadMode trackingReadMode4 = (i19 & 16777216) != 0 ? settingsUiState.trackingWatchlistReadMode : trackingReadMode;
        TrackingReadMode trackingReadMode5 = (i19 & 33554432) != 0 ? settingsUiState.trackingContinueReadMode : trackingReadMode2;
        TrackingReadMode trackingReadMode6 = (i19 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? settingsUiState.trackingWatchedReadMode : trackingReadMode3;
        boolean z82 = (i19 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? settingsUiState.trackingWriteToTrakt : z34;
        boolean z83 = (i19 & 268435456) != 0 ? settingsUiState.trackingWriteToSimkl : z35;
        boolean z84 = (i19 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? settingsUiState.isSyncing : z36;
        SyncProgress syncProgress2 = (i19 & 1073741824) != 0 ? settingsUiState.syncProgress : syncProgress;
        return settingsUiState.copy(str52, list13, str47, list14, str48, str49, z56, z57, str50, str51, list15, str53, str54, str55, str56, str57, z58, z59, str58, z60, z61, i22, z62, z63, z64, i23, z65, z66, z67, z68, str59, z69, str60, str61, z70, z71, z72, str62, z73, i24, i25, z74, traktDeviceCode2, z75, z76, str63, str64, z77, z78, str65, z79, z80, z81, str66, str67, str68, trackingReadMode4, trackingReadMode5, trackingReadMode6, z82, z83, z84, syncProgress2, (i19 & Integer.MIN_VALUE) != 0 ? settingsUiState.lastSyncTime : str23, (i20 & 1) != 0 ? settingsUiState.syncedMovies : i14, (i20 & 2) != 0 ? settingsUiState.syncedEpisodes : i15, (i20 & 4) != 0 ? settingsUiState.iptvM3uUrl : str24, (i20 & 8) != 0 ? settingsUiState.iptvEpgUrl : str25, (i20 & 16) != 0 ? settingsUiState.iptvPlaylists : list4, (i20 & 32) != 0 ? settingsUiState.iptvStalkerUrl : str26, (i20 & 64) != 0 ? settingsUiState.iptvStalkerMac : str27, (i20 & 128) != 0 ? settingsUiState.iptvSortOrder : str28, (i20 & 256) != 0 ? settingsUiState.iptvChannelCount : i16, (i20 & 512) != 0 ? settingsUiState.isIptvLoading : z37, (i20 & 1024) != 0 ? settingsUiState.iptvError : str29, (i20 & 2048) != 0 ? settingsUiState.iptvStatusMessage : str30, (i20 & 4096) != 0 ? settingsUiState.iptvStatusType : toastType, (i20 & 8192) != 0 ? settingsUiState.iptvProgressText : str31, (i20 & 16384) != 0 ? settingsUiState.iptvProgressPercent : i17, (i20 & 32768) != 0 ? settingsUiState.iptvSelectedPlaylistId : str32, (i20 & 65536) != 0 ? settingsUiState.iptvAvailableGroups : list5, (i20 & 131072) != 0 ? settingsUiState.iptvHiddenGroups : list6, (i20 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? settingsUiState.iptvGroupOrder : list7, (i20 & 524288) != 0 ? settingsUiState.isSelfUpdateSupported : z38, (i20 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? settingsUiState.updateStatus : updateStatus, (i20 & 2097152) != 0 ? settingsUiState.showAppUpdateDialog : z39, (i20 & 4194304) != 0 ? settingsUiState.showUnknownSourcesDialog : z40, (i20 & 8388608) != 0 ? settingsUiState.catalogs : list8, (i20 & 16777216) != 0 ? settingsUiState.catalogSearchQuery : str33, (i20 & 33554432) != 0 ? settingsUiState.catalogSearchResults : list9, (i20 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? settingsUiState.isCatalogSearching : z41, (i20 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? settingsUiState.catalogSearchError : str34, (i20 & 268435456) != 0 ? settingsUiState.pendingPackManifest : catalogPackManifest, (i20 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? settingsUiState.pendingPackUrl : str35, (i20 & 1073741824) != 0 ? settingsUiState.isPackLoading : z42, (i20 & Integer.MIN_VALUE) != 0 ? settingsUiState.packError : str36, (i21 & 1) != 0 ? settingsUiState.addons : list10, (i21 & 2) != 0 ? settingsUiState.isRefreshingAddons : z43, (i21 & 4) != 0 ? settingsUiState.torrServerBaseUrl : str37, (i21 & 8) != 0 ? settingsUiState.homeServerConnection : homeServerConnection, (i21 & 16) != 0 ? settingsUiState.homeServerConnections : list11, (i21 & 32) != 0 ? settingsUiState.isHomeServerConnecting : z44, (i21 & 64) != 0 ? settingsUiState.homeServerError : str38, (i21 & 128) != 0 ? settingsUiState.plexHomeServerAuth : plexPinAuthSession, (i21 & 256) != 0 ? settingsUiState.isPlexHomeServerPolling : z45, (i21 & 512) != 0 ? settingsUiState.contentLanguage : str39, (i21 & 1024) != 0 ? settingsUiState.deviceModeOverride : str40, (i21 & 2048) != 0 ? settingsUiState.skipProfileSelection : z46, (i21 & 4096) != 0 ? settingsUiState.oledBlackBackground : z47, (i21 & 8192) != 0 ? settingsUiState.clockFormat : str41, (i21 & 16384) != 0 ? settingsUiState.qualityFilters : list12, (i21 & 32768) != 0 ? settingsUiState.spoilerBlurEnabled : z48, (i21 & 65536) != 0 ? settingsUiState.accentColor : str42, (i21 & 131072) != 0 ? settingsUiState.qualityFilterPresetLabel : str43, (i21 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? settingsUiState.toastMessage : str44, (i21 & 524288) != 0 ? settingsUiState.toastType : toastType2, (i21 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? settingsUiState.subtitleAiEnabled : z49, (i21 & 2097152) != 0 ? settingsUiState.subtitleAiAutoSelect : z50, (i21 & 4194304) != 0 ? settingsUiState.subtitleAiFindBestMatch : z51, (i21 & 8388608) != 0 ? settingsUiState.subtitlePreloadEnabled : z52, (i21 & 16777216) != 0 ? settingsUiState.dolbyVisionCompatEnabled : z53, (i21 & 33554432) != 0 ? settingsUiState.subtitleAiApiKey : str45, (i21 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? settingsUiState.subtitleAiModel : subtitleAiModel, (i21 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? settingsUiState.subtitleRemoveHearingImpaired : z54, (i21 & 268435456) != 0 ? settingsUiState.aiKeyServerState : aiKeyServerState, (i21 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? settingsUiState.smoothScrolling : z55);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDefaultSubtitle() {
        return this.defaultSubtitle;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getDnsProvider() {
        return this.dnsProvider;
    }

    /* JADX INFO: renamed from: component100, reason: from getter */
    public final HomeServerConnection getHomeServerConnection() {
        return this.homeServerConnection;
    }

    public final List<HomeServerConnection> component101() {
        return this.homeServerConnections;
    }

    /* JADX INFO: renamed from: component102, reason: from getter */
    public final boolean getIsHomeServerConnecting() {
        return this.isHomeServerConnecting;
    }

    /* JADX INFO: renamed from: component103, reason: from getter */
    public final String getHomeServerError() {
        return this.homeServerError;
    }

    /* JADX INFO: renamed from: component104, reason: from getter */
    public final PlexPinAuthSession getPlexHomeServerAuth() {
        return this.plexHomeServerAuth;
    }

    /* JADX INFO: renamed from: component105, reason: from getter */
    public final boolean getIsPlexHomeServerPolling() {
        return this.isPlexHomeServerPolling;
    }

    /* JADX INFO: renamed from: component106, reason: from getter */
    public final String getContentLanguage() {
        return this.contentLanguage;
    }

    /* JADX INFO: renamed from: component107, reason: from getter */
    public final String getDeviceModeOverride() {
        return this.deviceModeOverride;
    }

    /* JADX INFO: renamed from: component108, reason: from getter */
    public final boolean getSkipProfileSelection() {
        return this.skipProfileSelection;
    }

    /* JADX INFO: renamed from: component109, reason: from getter */
    public final boolean getOledBlackBackground() {
        return this.oledBlackBackground;
    }

    public final List<String> component11() {
        return this.dnsProviderOptions;
    }

    /* JADX INFO: renamed from: component110, reason: from getter */
    public final String getClockFormat() {
        return this.clockFormat;
    }

    public final List<QualityFilterConfig> component111() {
        return this.qualityFilters;
    }

    /* JADX INFO: renamed from: component112, reason: from getter */
    public final boolean getSpoilerBlurEnabled() {
        return this.spoilerBlurEnabled;
    }

    /* JADX INFO: renamed from: component113, reason: from getter */
    public final String getAccentColor() {
        return this.accentColor;
    }

    /* JADX INFO: renamed from: component114, reason: from getter */
    public final String getQualityFilterPresetLabel() {
        return this.qualityFilterPresetLabel;
    }

    /* JADX INFO: renamed from: component115, reason: from getter */
    public final String getToastMessage() {
        return this.toastMessage;
    }

    /* JADX INFO: renamed from: component116, reason: from getter */
    public final ToastType getToastType() {
        return this.toastType;
    }

    /* JADX INFO: renamed from: component117, reason: from getter */
    public final boolean getSubtitleAiEnabled() {
        return this.subtitleAiEnabled;
    }

    /* JADX INFO: renamed from: component118, reason: from getter */
    public final boolean getSubtitleAiAutoSelect() {
        return this.subtitleAiAutoSelect;
    }

    /* JADX INFO: renamed from: component119, reason: from getter */
    public final boolean getSubtitleAiFindBestMatch() {
        return this.subtitleAiFindBestMatch;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getCustomUserAgent() {
        return this.customUserAgent;
    }

    /* JADX INFO: renamed from: component120, reason: from getter */
    public final boolean getSubtitlePreloadEnabled() {
        return this.subtitlePreloadEnabled;
    }

    /* JADX INFO: renamed from: component121, reason: from getter */
    public final boolean getDolbyVisionCompatEnabled() {
        return this.dolbyVisionCompatEnabled;
    }

    /* JADX INFO: renamed from: component122, reason: from getter */
    public final String getSubtitleAiApiKey() {
        return this.subtitleAiApiKey;
    }

    /* JADX INFO: renamed from: component123, reason: from getter */
    public final SubtitleAiModel getSubtitleAiModel() {
        return this.subtitleAiModel;
    }

    /* JADX INFO: renamed from: component124, reason: from getter */
    public final boolean getSubtitleRemoveHearingImpaired() {
        return this.subtitleRemoveHearingImpaired;
    }

    /* JADX INFO: renamed from: component125, reason: from getter */
    public final AiKeyServerState getAiKeyServerState() {
        return this.aiKeyServerState;
    }

    /* JADX INFO: renamed from: component126, reason: from getter */
    public final boolean getSmoothScrolling() {
        return this.smoothScrolling;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getSubtitleSize() {
        return this.subtitleSize;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getSubtitleColor() {
        return this.subtitleColor;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getSubtitleStyle() {
        return this.subtitleStyle;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getSubtitleOffset() {
        return this.subtitleOffset;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final boolean getSubtitleStylized() {
        return this.subtitleStylized;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final boolean getFilterSubtitlesByLanguage() {
        return this.filterSubtitlesByLanguage;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getSecondarySubtitle() {
        return this.secondarySubtitle;
    }

    public final List<String> component2() {
        return this.subtitleOptions;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final boolean getTrailerAutoPlay() {
        return this.trailerAutoPlay;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final boolean getTrailerSoundEnabled() {
        return this.trailerSoundEnabled;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final int getTrailerDelaySeconds() {
        return this.trailerDelaySeconds;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final boolean getTrailerInCards() {
        return this.trailerInCards;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final boolean getShowBudget() {
        return this.showBudget;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final boolean getShowEpisodeRatings() {
        return this.showEpisodeRatings;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final int getVolumeBoostDb() {
        return this.volumeBoostDb;
    }

    /* JADX INFO: renamed from: component27, reason: from getter */
    public final boolean getShowLoadingStats() {
        return this.showLoadingStats;
    }

    /* JADX INFO: renamed from: component28, reason: from getter */
    public final boolean getDiagnosticsSharingEnabled() {
        return this.diagnosticsSharingEnabled;
    }

    /* JADX INFO: renamed from: component29, reason: from getter */
    public final boolean getIncludeSpecials() {
        return this.includeSpecials;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDefaultAudioLanguage() {
        return this.defaultAudioLanguage;
    }

    /* JADX INFO: renamed from: component30, reason: from getter */
    public final boolean getIsLoggedIn() {
        return this.isLoggedIn;
    }

    /* JADX INFO: renamed from: component31, reason: from getter */
    public final String getAccountEmail() {
        return this.accountEmail;
    }

    /* JADX INFO: renamed from: component32, reason: from getter */
    public final boolean getShowCloudPairDialog() {
        return this.showCloudPairDialog;
    }

    /* JADX INFO: renamed from: component33, reason: from getter */
    public final String getCloudUserCode() {
        return this.cloudUserCode;
    }

    /* JADX INFO: renamed from: component34, reason: from getter */
    public final String getCloudVerificationUrl() {
        return this.cloudVerificationUrl;
    }

    /* JADX INFO: renamed from: component35, reason: from getter */
    public final boolean getShowCloudEmailPasswordDialog() {
        return this.showCloudEmailPasswordDialog;
    }

    /* JADX INFO: renamed from: component36, reason: from getter */
    public final boolean getIsCloudAuthWorking() {
        return this.isCloudAuthWorking;
    }

    /* JADX INFO: renamed from: component37, reason: from getter */
    public final boolean getIsForceCloudSyncing() {
        return this.isForceCloudSyncing;
    }

    /* JADX INFO: renamed from: component38, reason: from getter */
    public final String getLastCloudSyncStatus() {
        return this.lastCloudSyncStatus;
    }

    /* JADX INFO: renamed from: component39, reason: from getter */
    public final boolean getShouldSwitchProfile() {
        return this.shouldSwitchProfile;
    }

    public final List<String> component4() {
        return this.audioLanguageOptions;
    }

    /* JADX INFO: renamed from: component40, reason: from getter */
    public final int getWatchlistCount() {
        return this.watchlistCount;
    }

    /* JADX INFO: renamed from: component41, reason: from getter */
    public final int getHistoryCount() {
        return this.historyCount;
    }

    /* JADX INFO: renamed from: component42, reason: from getter */
    public final boolean getIsTraktAuthenticated() {
        return this.isTraktAuthenticated;
    }

    /* JADX INFO: renamed from: component43, reason: from getter */
    public final TraktDeviceCode getTraktCode() {
        return this.traktCode;
    }

    /* JADX INFO: renamed from: component44, reason: from getter */
    public final boolean getIsTraktAuthStarting() {
        return this.isTraktAuthStarting;
    }

    /* JADX INFO: renamed from: component45, reason: from getter */
    public final boolean getIsTraktPolling() {
        return this.isTraktPolling;
    }

    /* JADX INFO: renamed from: component46, reason: from getter */
    public final String getTraktExpiration() {
        return this.traktExpiration;
    }

    /* JADX INFO: renamed from: component47, reason: from getter */
    public final String getTraktUsername() {
        return this.traktUsername;
    }

    /* JADX INFO: renamed from: component48, reason: from getter */
    public final boolean getIsMdbListConnected() {
        return this.isMdbListConnected;
    }

    /* JADX INFO: renamed from: component49, reason: from getter */
    public final boolean getMdbListConnecting() {
        return this.mdbListConnecting;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getCardLayoutMode() {
        return this.cardLayoutMode;
    }

    /* JADX INFO: renamed from: component50, reason: from getter */
    public final String getMdbListUsername() {
        return this.mdbListUsername;
    }

    /* JADX INFO: renamed from: component51, reason: from getter */
    public final boolean getIsSimklConnected() {
        return this.isSimklConnected;
    }

    /* JADX INFO: renamed from: component52, reason: from getter */
    public final boolean getIsSimklAuthStarting() {
        return this.isSimklAuthStarting;
    }

    /* JADX INFO: renamed from: component53, reason: from getter */
    public final boolean getIsSimklPolling() {
        return this.isSimklPolling;
    }

    /* JADX INFO: renamed from: component54, reason: from getter */
    public final String getSimklUserCode() {
        return this.simklUserCode;
    }

    /* JADX INFO: renamed from: component55, reason: from getter */
    public final String getSimklVerificationUrl() {
        return this.simklVerificationUrl;
    }

    /* JADX INFO: renamed from: component56, reason: from getter */
    public final String getSimklUsername() {
        return this.simklUsername;
    }

    /* JADX INFO: renamed from: component57, reason: from getter */
    public final TrackingReadMode getTrackingWatchlistReadMode() {
        return this.trackingWatchlistReadMode;
    }

    /* JADX INFO: renamed from: component58, reason: from getter */
    public final TrackingReadMode getTrackingContinueReadMode() {
        return this.trackingContinueReadMode;
    }

    /* JADX INFO: renamed from: component59, reason: from getter */
    public final TrackingReadMode getTrackingWatchedReadMode() {
        return this.trackingWatchedReadMode;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getFrameRateMatchingMode() {
        return this.frameRateMatchingMode;
    }

    /* JADX INFO: renamed from: component60, reason: from getter */
    public final boolean getTrackingWriteToTrakt() {
        return this.trackingWriteToTrakt;
    }

    /* JADX INFO: renamed from: component61, reason: from getter */
    public final boolean getTrackingWriteToSimkl() {
        return this.trackingWriteToSimkl;
    }

    /* JADX INFO: renamed from: component62, reason: from getter */
    public final boolean getIsSyncing() {
        return this.isSyncing;
    }

    /* JADX INFO: renamed from: component63, reason: from getter */
    public final SyncProgress getSyncProgress() {
        return this.syncProgress;
    }

    /* JADX INFO: renamed from: component64, reason: from getter */
    public final String getLastSyncTime() {
        return this.lastSyncTime;
    }

    /* JADX INFO: renamed from: component65, reason: from getter */
    public final int getSyncedMovies() {
        return this.syncedMovies;
    }

    /* JADX INFO: renamed from: component66, reason: from getter */
    public final int getSyncedEpisodes() {
        return this.syncedEpisodes;
    }

    /* JADX INFO: renamed from: component67, reason: from getter */
    public final String getIptvM3uUrl() {
        return this.iptvM3uUrl;
    }

    /* JADX INFO: renamed from: component68, reason: from getter */
    public final String getIptvEpgUrl() {
        return this.iptvEpgUrl;
    }

    public final List<IptvPlaylistEntry> component69() {
        return this.iptvPlaylists;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getAutoPlayNext() {
        return this.autoPlayNext;
    }

    /* JADX INFO: renamed from: component70, reason: from getter */
    public final String getIptvStalkerUrl() {
        return this.iptvStalkerUrl;
    }

    /* JADX INFO: renamed from: component71, reason: from getter */
    public final String getIptvStalkerMac() {
        return this.iptvStalkerMac;
    }

    /* JADX INFO: renamed from: component72, reason: from getter */
    public final String getIptvSortOrder() {
        return this.iptvSortOrder;
    }

    /* JADX INFO: renamed from: component73, reason: from getter */
    public final int getIptvChannelCount() {
        return this.iptvChannelCount;
    }

    /* JADX INFO: renamed from: component74, reason: from getter */
    public final boolean getIsIptvLoading() {
        return this.isIptvLoading;
    }

    /* JADX INFO: renamed from: component75, reason: from getter */
    public final String getIptvError() {
        return this.iptvError;
    }

    /* JADX INFO: renamed from: component76, reason: from getter */
    public final String getIptvStatusMessage() {
        return this.iptvStatusMessage;
    }

    /* JADX INFO: renamed from: component77, reason: from getter */
    public final ToastType getIptvStatusType() {
        return this.iptvStatusType;
    }

    /* JADX INFO: renamed from: component78, reason: from getter */
    public final String getIptvProgressText() {
        return this.iptvProgressText;
    }

    /* JADX INFO: renamed from: component79, reason: from getter */
    public final int getIptvProgressPercent() {
        return this.iptvProgressPercent;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getAutoPlaySingleSource() {
        return this.autoPlaySingleSource;
    }

    /* JADX INFO: renamed from: component80, reason: from getter */
    public final String getIptvSelectedPlaylistId() {
        return this.iptvSelectedPlaylistId;
    }

    public final List<String> component81() {
        return this.iptvAvailableGroups;
    }

    public final List<String> component82() {
        return this.iptvHiddenGroups;
    }

    public final List<String> component83() {
        return this.iptvGroupOrder;
    }

    /* JADX INFO: renamed from: component84, reason: from getter */
    public final boolean getIsSelfUpdateSupported() {
        return this.isSelfUpdateSupported;
    }

    /* JADX INFO: renamed from: component85, reason: from getter */
    public final UpdateStatus getUpdateStatus() {
        return this.updateStatus;
    }

    /* JADX INFO: renamed from: component86, reason: from getter */
    public final boolean getShowAppUpdateDialog() {
        return this.showAppUpdateDialog;
    }

    /* JADX INFO: renamed from: component87, reason: from getter */
    public final boolean getShowUnknownSourcesDialog() {
        return this.showUnknownSourcesDialog;
    }

    public final List<CatalogConfig> component88() {
        return this.catalogs;
    }

    /* JADX INFO: renamed from: component89, reason: from getter */
    public final String getCatalogSearchQuery() {
        return this.catalogSearchQuery;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getAutoPlayMinQuality() {
        return this.autoPlayMinQuality;
    }

    public final List<CatalogDiscoveryResult> component90() {
        return this.catalogSearchResults;
    }

    /* JADX INFO: renamed from: component91, reason: from getter */
    public final boolean getIsCatalogSearching() {
        return this.isCatalogSearching;
    }

    /* JADX INFO: renamed from: component92, reason: from getter */
    public final String getCatalogSearchError() {
        return this.catalogSearchError;
    }

    /* JADX INFO: renamed from: component93, reason: from getter */
    public final CatalogPackManifest getPendingPackManifest() {
        return this.pendingPackManifest;
    }

    /* JADX INFO: renamed from: component94, reason: from getter */
    public final String getPendingPackUrl() {
        return this.pendingPackUrl;
    }

    /* JADX INFO: renamed from: component95, reason: from getter */
    public final boolean getIsPackLoading() {
        return this.isPackLoading;
    }

    /* JADX INFO: renamed from: component96, reason: from getter */
    public final String getPackError() {
        return this.packError;
    }

    public final List<Addon> component97() {
        return this.addons;
    }

    /* JADX INFO: renamed from: component98, reason: from getter */
    public final boolean getIsRefreshingAddons() {
        return this.isRefreshingAddons;
    }

    /* JADX INFO: renamed from: component99, reason: from getter */
    public final String getTorrServerBaseUrl() {
        return this.torrServerBaseUrl;
    }

    public final SettingsUiState copy(String defaultSubtitle, List<String> subtitleOptions, String defaultAudioLanguage, List<String> audioLanguageOptions, String cardLayoutMode, String frameRateMatchingMode, boolean autoPlayNext, boolean autoPlaySingleSource, String autoPlayMinQuality, String dnsProvider, List<String> dnsProviderOptions, String customUserAgent, String subtitleSize, String subtitleColor, String subtitleStyle, String subtitleOffset, boolean subtitleStylized, boolean filterSubtitlesByLanguage, String secondarySubtitle, boolean trailerAutoPlay, boolean trailerSoundEnabled, int trailerDelaySeconds, boolean trailerInCards, boolean showBudget, boolean showEpisodeRatings, int volumeBoostDb, boolean showLoadingStats, boolean diagnosticsSharingEnabled, boolean includeSpecials, boolean isLoggedIn, String accountEmail, boolean showCloudPairDialog, String cloudUserCode, String cloudVerificationUrl, boolean showCloudEmailPasswordDialog, boolean isCloudAuthWorking, boolean isForceCloudSyncing, String lastCloudSyncStatus, boolean shouldSwitchProfile, int watchlistCount, int historyCount, boolean isTraktAuthenticated, TraktDeviceCode traktCode, boolean isTraktAuthStarting, boolean isTraktPolling, String traktExpiration, String traktUsername, boolean isMdbListConnected, boolean mdbListConnecting, String mdbListUsername, boolean isSimklConnected, boolean isSimklAuthStarting, boolean isSimklPolling, String simklUserCode, String simklVerificationUrl, String simklUsername, TrackingReadMode trackingWatchlistReadMode, TrackingReadMode trackingContinueReadMode, TrackingReadMode trackingWatchedReadMode, boolean trackingWriteToTrakt, boolean trackingWriteToSimkl, boolean isSyncing, SyncProgress syncProgress, String lastSyncTime, int syncedMovies, int syncedEpisodes, String iptvM3uUrl, String iptvEpgUrl, List<IptvPlaylistEntry> iptvPlaylists, String iptvStalkerUrl, String iptvStalkerMac, String iptvSortOrder, int iptvChannelCount, boolean isIptvLoading, String iptvError, String iptvStatusMessage, ToastType iptvStatusType, String iptvProgressText, int iptvProgressPercent, String iptvSelectedPlaylistId, List<String> iptvAvailableGroups, List<String> iptvHiddenGroups, List<String> iptvGroupOrder, boolean isSelfUpdateSupported, UpdateStatus updateStatus, boolean showAppUpdateDialog, boolean showUnknownSourcesDialog, List<CatalogConfig> catalogs, String catalogSearchQuery, List<CatalogDiscoveryResult> catalogSearchResults, boolean isCatalogSearching, String catalogSearchError, CatalogPackManifest pendingPackManifest, String pendingPackUrl, boolean isPackLoading, String packError, List<Addon> addons, boolean isRefreshingAddons, String torrServerBaseUrl, HomeServerConnection homeServerConnection, List<HomeServerConnection> homeServerConnections, boolean isHomeServerConnecting, String homeServerError, PlexPinAuthSession plexHomeServerAuth, boolean isPlexHomeServerPolling, String contentLanguage, String deviceModeOverride, boolean skipProfileSelection, boolean oledBlackBackground, String clockFormat, List<QualityFilterConfig> qualityFilters, boolean spoilerBlurEnabled, String accentColor, String qualityFilterPresetLabel, String toastMessage, ToastType toastType, boolean subtitleAiEnabled, boolean subtitleAiAutoSelect, boolean subtitleAiFindBestMatch, boolean subtitlePreloadEnabled, boolean dolbyVisionCompatEnabled, String subtitleAiApiKey, SubtitleAiModel subtitleAiModel, boolean subtitleRemoveHearingImpaired, AiKeyServerState aiKeyServerState, boolean smoothScrolling) {
        return new SettingsUiState(defaultSubtitle, subtitleOptions, defaultAudioLanguage, audioLanguageOptions, cardLayoutMode, frameRateMatchingMode, autoPlayNext, autoPlaySingleSource, autoPlayMinQuality, dnsProvider, dnsProviderOptions, customUserAgent, subtitleSize, subtitleColor, subtitleStyle, subtitleOffset, subtitleStylized, filterSubtitlesByLanguage, secondarySubtitle, trailerAutoPlay, trailerSoundEnabled, trailerDelaySeconds, trailerInCards, showBudget, showEpisodeRatings, volumeBoostDb, showLoadingStats, diagnosticsSharingEnabled, includeSpecials, isLoggedIn, accountEmail, showCloudPairDialog, cloudUserCode, cloudVerificationUrl, showCloudEmailPasswordDialog, isCloudAuthWorking, isForceCloudSyncing, lastCloudSyncStatus, shouldSwitchProfile, watchlistCount, historyCount, isTraktAuthenticated, traktCode, isTraktAuthStarting, isTraktPolling, traktExpiration, traktUsername, isMdbListConnected, mdbListConnecting, mdbListUsername, isSimklConnected, isSimklAuthStarting, isSimklPolling, simklUserCode, simklVerificationUrl, simklUsername, trackingWatchlistReadMode, trackingContinueReadMode, trackingWatchedReadMode, trackingWriteToTrakt, trackingWriteToSimkl, isSyncing, syncProgress, lastSyncTime, syncedMovies, syncedEpisodes, iptvM3uUrl, iptvEpgUrl, iptvPlaylists, iptvStalkerUrl, iptvStalkerMac, iptvSortOrder, iptvChannelCount, isIptvLoading, iptvError, iptvStatusMessage, iptvStatusType, iptvProgressText, iptvProgressPercent, iptvSelectedPlaylistId, iptvAvailableGroups, iptvHiddenGroups, iptvGroupOrder, isSelfUpdateSupported, updateStatus, showAppUpdateDialog, showUnknownSourcesDialog, catalogs, catalogSearchQuery, catalogSearchResults, isCatalogSearching, catalogSearchError, pendingPackManifest, pendingPackUrl, isPackLoading, packError, addons, isRefreshingAddons, torrServerBaseUrl, homeServerConnection, homeServerConnections, isHomeServerConnecting, homeServerError, plexHomeServerAuth, isPlexHomeServerPolling, contentLanguage, deviceModeOverride, skipProfileSelection, oledBlackBackground, clockFormat, qualityFilters, spoilerBlurEnabled, accentColor, qualityFilterPresetLabel, toastMessage, toastType, subtitleAiEnabled, subtitleAiAutoSelect, subtitleAiFindBestMatch, subtitlePreloadEnabled, dolbyVisionCompatEnabled, subtitleAiApiKey, subtitleAiModel, subtitleRemoveHearingImpaired, aiKeyServerState, smoothScrolling);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SettingsUiState)) {
            return false;
        }
        SettingsUiState settingsUiState = (SettingsUiState) other;
        return kotlin.jvm.internal.p.a(this.defaultSubtitle, settingsUiState.defaultSubtitle) && kotlin.jvm.internal.p.a(this.subtitleOptions, settingsUiState.subtitleOptions) && kotlin.jvm.internal.p.a(this.defaultAudioLanguage, settingsUiState.defaultAudioLanguage) && kotlin.jvm.internal.p.a(this.audioLanguageOptions, settingsUiState.audioLanguageOptions) && kotlin.jvm.internal.p.a(this.cardLayoutMode, settingsUiState.cardLayoutMode) && kotlin.jvm.internal.p.a(this.frameRateMatchingMode, settingsUiState.frameRateMatchingMode) && this.autoPlayNext == settingsUiState.autoPlayNext && this.autoPlaySingleSource == settingsUiState.autoPlaySingleSource && kotlin.jvm.internal.p.a(this.autoPlayMinQuality, settingsUiState.autoPlayMinQuality) && kotlin.jvm.internal.p.a(this.dnsProvider, settingsUiState.dnsProvider) && kotlin.jvm.internal.p.a(this.dnsProviderOptions, settingsUiState.dnsProviderOptions) && kotlin.jvm.internal.p.a(this.customUserAgent, settingsUiState.customUserAgent) && kotlin.jvm.internal.p.a(this.subtitleSize, settingsUiState.subtitleSize) && kotlin.jvm.internal.p.a(this.subtitleColor, settingsUiState.subtitleColor) && kotlin.jvm.internal.p.a(this.subtitleStyle, settingsUiState.subtitleStyle) && kotlin.jvm.internal.p.a(this.subtitleOffset, settingsUiState.subtitleOffset) && this.subtitleStylized == settingsUiState.subtitleStylized && this.filterSubtitlesByLanguage == settingsUiState.filterSubtitlesByLanguage && kotlin.jvm.internal.p.a(this.secondarySubtitle, settingsUiState.secondarySubtitle) && this.trailerAutoPlay == settingsUiState.trailerAutoPlay && this.trailerSoundEnabled == settingsUiState.trailerSoundEnabled && this.trailerDelaySeconds == settingsUiState.trailerDelaySeconds && this.trailerInCards == settingsUiState.trailerInCards && this.showBudget == settingsUiState.showBudget && this.showEpisodeRatings == settingsUiState.showEpisodeRatings && this.volumeBoostDb == settingsUiState.volumeBoostDb && this.showLoadingStats == settingsUiState.showLoadingStats && this.diagnosticsSharingEnabled == settingsUiState.diagnosticsSharingEnabled && this.includeSpecials == settingsUiState.includeSpecials && this.isLoggedIn == settingsUiState.isLoggedIn && kotlin.jvm.internal.p.a(this.accountEmail, settingsUiState.accountEmail) && this.showCloudPairDialog == settingsUiState.showCloudPairDialog && kotlin.jvm.internal.p.a(this.cloudUserCode, settingsUiState.cloudUserCode) && kotlin.jvm.internal.p.a(this.cloudVerificationUrl, settingsUiState.cloudVerificationUrl) && this.showCloudEmailPasswordDialog == settingsUiState.showCloudEmailPasswordDialog && this.isCloudAuthWorking == settingsUiState.isCloudAuthWorking && this.isForceCloudSyncing == settingsUiState.isForceCloudSyncing && kotlin.jvm.internal.p.a(this.lastCloudSyncStatus, settingsUiState.lastCloudSyncStatus) && this.shouldSwitchProfile == settingsUiState.shouldSwitchProfile && this.watchlistCount == settingsUiState.watchlistCount && this.historyCount == settingsUiState.historyCount && this.isTraktAuthenticated == settingsUiState.isTraktAuthenticated && kotlin.jvm.internal.p.a(this.traktCode, settingsUiState.traktCode) && this.isTraktAuthStarting == settingsUiState.isTraktAuthStarting && this.isTraktPolling == settingsUiState.isTraktPolling && kotlin.jvm.internal.p.a(this.traktExpiration, settingsUiState.traktExpiration) && kotlin.jvm.internal.p.a(this.traktUsername, settingsUiState.traktUsername) && this.isMdbListConnected == settingsUiState.isMdbListConnected && this.mdbListConnecting == settingsUiState.mdbListConnecting && kotlin.jvm.internal.p.a(this.mdbListUsername, settingsUiState.mdbListUsername) && this.isSimklConnected == settingsUiState.isSimklConnected && this.isSimklAuthStarting == settingsUiState.isSimklAuthStarting && this.isSimklPolling == settingsUiState.isSimklPolling && kotlin.jvm.internal.p.a(this.simklUserCode, settingsUiState.simklUserCode) && kotlin.jvm.internal.p.a(this.simklVerificationUrl, settingsUiState.simklVerificationUrl) && kotlin.jvm.internal.p.a(this.simklUsername, settingsUiState.simklUsername) && this.trackingWatchlistReadMode == settingsUiState.trackingWatchlistReadMode && this.trackingContinueReadMode == settingsUiState.trackingContinueReadMode && this.trackingWatchedReadMode == settingsUiState.trackingWatchedReadMode && this.trackingWriteToTrakt == settingsUiState.trackingWriteToTrakt && this.trackingWriteToSimkl == settingsUiState.trackingWriteToSimkl && this.isSyncing == settingsUiState.isSyncing && kotlin.jvm.internal.p.a(this.syncProgress, settingsUiState.syncProgress) && kotlin.jvm.internal.p.a(this.lastSyncTime, settingsUiState.lastSyncTime) && this.syncedMovies == settingsUiState.syncedMovies && this.syncedEpisodes == settingsUiState.syncedEpisodes && kotlin.jvm.internal.p.a(this.iptvM3uUrl, settingsUiState.iptvM3uUrl) && kotlin.jvm.internal.p.a(this.iptvEpgUrl, settingsUiState.iptvEpgUrl) && kotlin.jvm.internal.p.a(this.iptvPlaylists, settingsUiState.iptvPlaylists) && kotlin.jvm.internal.p.a(this.iptvStalkerUrl, settingsUiState.iptvStalkerUrl) && kotlin.jvm.internal.p.a(this.iptvStalkerMac, settingsUiState.iptvStalkerMac) && kotlin.jvm.internal.p.a(this.iptvSortOrder, settingsUiState.iptvSortOrder) && this.iptvChannelCount == settingsUiState.iptvChannelCount && this.isIptvLoading == settingsUiState.isIptvLoading && kotlin.jvm.internal.p.a(this.iptvError, settingsUiState.iptvError) && kotlin.jvm.internal.p.a(this.iptvStatusMessage, settingsUiState.iptvStatusMessage) && this.iptvStatusType == settingsUiState.iptvStatusType && kotlin.jvm.internal.p.a(this.iptvProgressText, settingsUiState.iptvProgressText) && this.iptvProgressPercent == settingsUiState.iptvProgressPercent && kotlin.jvm.internal.p.a(this.iptvSelectedPlaylistId, settingsUiState.iptvSelectedPlaylistId) && kotlin.jvm.internal.p.a(this.iptvAvailableGroups, settingsUiState.iptvAvailableGroups) && kotlin.jvm.internal.p.a(this.iptvHiddenGroups, settingsUiState.iptvHiddenGroups) && kotlin.jvm.internal.p.a(this.iptvGroupOrder, settingsUiState.iptvGroupOrder) && this.isSelfUpdateSupported == settingsUiState.isSelfUpdateSupported && kotlin.jvm.internal.p.a(this.updateStatus, settingsUiState.updateStatus) && this.showAppUpdateDialog == settingsUiState.showAppUpdateDialog && this.showUnknownSourcesDialog == settingsUiState.showUnknownSourcesDialog && kotlin.jvm.internal.p.a(this.catalogs, settingsUiState.catalogs) && kotlin.jvm.internal.p.a(this.catalogSearchQuery, settingsUiState.catalogSearchQuery) && kotlin.jvm.internal.p.a(this.catalogSearchResults, settingsUiState.catalogSearchResults) && this.isCatalogSearching == settingsUiState.isCatalogSearching && kotlin.jvm.internal.p.a(this.catalogSearchError, settingsUiState.catalogSearchError) && kotlin.jvm.internal.p.a(this.pendingPackManifest, settingsUiState.pendingPackManifest) && kotlin.jvm.internal.p.a(this.pendingPackUrl, settingsUiState.pendingPackUrl) && this.isPackLoading == settingsUiState.isPackLoading && kotlin.jvm.internal.p.a(this.packError, settingsUiState.packError) && kotlin.jvm.internal.p.a(this.addons, settingsUiState.addons) && this.isRefreshingAddons == settingsUiState.isRefreshingAddons && kotlin.jvm.internal.p.a(this.torrServerBaseUrl, settingsUiState.torrServerBaseUrl) && kotlin.jvm.internal.p.a(this.homeServerConnection, settingsUiState.homeServerConnection) && kotlin.jvm.internal.p.a(this.homeServerConnections, settingsUiState.homeServerConnections) && this.isHomeServerConnecting == settingsUiState.isHomeServerConnecting && kotlin.jvm.internal.p.a(this.homeServerError, settingsUiState.homeServerError) && kotlin.jvm.internal.p.a(this.plexHomeServerAuth, settingsUiState.plexHomeServerAuth) && this.isPlexHomeServerPolling == settingsUiState.isPlexHomeServerPolling && kotlin.jvm.internal.p.a(this.contentLanguage, settingsUiState.contentLanguage) && kotlin.jvm.internal.p.a(this.deviceModeOverride, settingsUiState.deviceModeOverride) && this.skipProfileSelection == settingsUiState.skipProfileSelection && this.oledBlackBackground == settingsUiState.oledBlackBackground && kotlin.jvm.internal.p.a(this.clockFormat, settingsUiState.clockFormat) && kotlin.jvm.internal.p.a(this.qualityFilters, settingsUiState.qualityFilters) && this.spoilerBlurEnabled == settingsUiState.spoilerBlurEnabled && kotlin.jvm.internal.p.a(this.accentColor, settingsUiState.accentColor) && kotlin.jvm.internal.p.a(this.qualityFilterPresetLabel, settingsUiState.qualityFilterPresetLabel) && kotlin.jvm.internal.p.a(this.toastMessage, settingsUiState.toastMessage) && this.toastType == settingsUiState.toastType && this.subtitleAiEnabled == settingsUiState.subtitleAiEnabled && this.subtitleAiAutoSelect == settingsUiState.subtitleAiAutoSelect && this.subtitleAiFindBestMatch == settingsUiState.subtitleAiFindBestMatch && this.subtitlePreloadEnabled == settingsUiState.subtitlePreloadEnabled && this.dolbyVisionCompatEnabled == settingsUiState.dolbyVisionCompatEnabled && kotlin.jvm.internal.p.a(this.subtitleAiApiKey, settingsUiState.subtitleAiApiKey) && this.subtitleAiModel == settingsUiState.subtitleAiModel && this.subtitleRemoveHearingImpaired == settingsUiState.subtitleRemoveHearingImpaired && kotlin.jvm.internal.p.a(this.aiKeyServerState, settingsUiState.aiKeyServerState) && this.smoothScrolling == settingsUiState.smoothScrolling;
    }

    public final String getAccentColor() {
        return this.accentColor;
    }

    public final String getAccountEmail() {
        return this.accountEmail;
    }

    public final List<Addon> getAddons() {
        return this.addons;
    }

    public final AiKeyServerState getAiKeyServerState() {
        return this.aiKeyServerState;
    }

    public final List<String> getAudioLanguageOptions() {
        return this.audioLanguageOptions;
    }

    public final String getAutoPlayMinQuality() {
        return this.autoPlayMinQuality;
    }

    public final boolean getAutoPlayNext() {
        return this.autoPlayNext;
    }

    public final boolean getAutoPlaySingleSource() {
        return this.autoPlaySingleSource;
    }

    public final String getCardLayoutMode() {
        return this.cardLayoutMode;
    }

    public final String getCatalogSearchError() {
        return this.catalogSearchError;
    }

    public final String getCatalogSearchQuery() {
        return this.catalogSearchQuery;
    }

    public final List<CatalogDiscoveryResult> getCatalogSearchResults() {
        return this.catalogSearchResults;
    }

    public final List<CatalogConfig> getCatalogs() {
        return this.catalogs;
    }

    public final String getClockFormat() {
        return this.clockFormat;
    }

    public final String getCloudUserCode() {
        return this.cloudUserCode;
    }

    public final String getCloudVerificationUrl() {
        return this.cloudVerificationUrl;
    }

    public final String getContentLanguage() {
        return this.contentLanguage;
    }

    public final String getCustomUserAgent() {
        return this.customUserAgent;
    }

    public final String getDefaultAudioLanguage() {
        return this.defaultAudioLanguage;
    }

    public final String getDefaultSubtitle() {
        return this.defaultSubtitle;
    }

    public final String getDeviceModeOverride() {
        return this.deviceModeOverride;
    }

    public final boolean getDiagnosticsSharingEnabled() {
        return this.diagnosticsSharingEnabled;
    }

    public final String getDnsProvider() {
        return this.dnsProvider;
    }

    public final List<String> getDnsProviderOptions() {
        return this.dnsProviderOptions;
    }

    public final boolean getDolbyVisionCompatEnabled() {
        return this.dolbyVisionCompatEnabled;
    }

    public final boolean getFilterSubtitlesByLanguage() {
        return this.filterSubtitlesByLanguage;
    }

    public final String getFrameRateMatchingMode() {
        return this.frameRateMatchingMode;
    }

    public final int getHistoryCount() {
        return this.historyCount;
    }

    public final HomeServerConnection getHomeServerConnection() {
        return this.homeServerConnection;
    }

    public final List<HomeServerConnection> getHomeServerConnections() {
        return this.homeServerConnections;
    }

    public final String getHomeServerError() {
        return this.homeServerError;
    }

    public final boolean getIncludeSpecials() {
        return this.includeSpecials;
    }

    public final List<String> getIptvAvailableGroups() {
        return this.iptvAvailableGroups;
    }

    public final int getIptvChannelCount() {
        return this.iptvChannelCount;
    }

    public final String getIptvEpgUrl() {
        return this.iptvEpgUrl;
    }

    public final String getIptvError() {
        return this.iptvError;
    }

    public final List<String> getIptvGroupOrder() {
        return this.iptvGroupOrder;
    }

    public final List<String> getIptvHiddenGroups() {
        return this.iptvHiddenGroups;
    }

    public final String getIptvM3uUrl() {
        return this.iptvM3uUrl;
    }

    public final List<IptvPlaylistEntry> getIptvPlaylists() {
        return this.iptvPlaylists;
    }

    public final int getIptvProgressPercent() {
        return this.iptvProgressPercent;
    }

    public final String getIptvProgressText() {
        return this.iptvProgressText;
    }

    public final String getIptvSelectedPlaylistId() {
        return this.iptvSelectedPlaylistId;
    }

    public final String getIptvSortOrder() {
        return this.iptvSortOrder;
    }

    public final String getIptvStalkerMac() {
        return this.iptvStalkerMac;
    }

    public final String getIptvStalkerUrl() {
        return this.iptvStalkerUrl;
    }

    public final String getIptvStatusMessage() {
        return this.iptvStatusMessage;
    }

    public final ToastType getIptvStatusType() {
        return this.iptvStatusType;
    }

    public final String getLastCloudSyncStatus() {
        return this.lastCloudSyncStatus;
    }

    public final String getLastSyncTime() {
        return this.lastSyncTime;
    }

    public final boolean getMdbListConnecting() {
        return this.mdbListConnecting;
    }

    public final String getMdbListUsername() {
        return this.mdbListUsername;
    }

    public final boolean getOledBlackBackground() {
        return this.oledBlackBackground;
    }

    public final String getPackError() {
        return this.packError;
    }

    public final CatalogPackManifest getPendingPackManifest() {
        return this.pendingPackManifest;
    }

    public final String getPendingPackUrl() {
        return this.pendingPackUrl;
    }

    public final PlexPinAuthSession getPlexHomeServerAuth() {
        return this.plexHomeServerAuth;
    }

    public final String getQualityFilterPresetLabel() {
        return this.qualityFilterPresetLabel;
    }

    public final List<QualityFilterConfig> getQualityFilters() {
        return this.qualityFilters;
    }

    public final String getSecondarySubtitle() {
        return this.secondarySubtitle;
    }

    public final boolean getShouldSwitchProfile() {
        return this.shouldSwitchProfile;
    }

    public final boolean getShowAppUpdateDialog() {
        return this.showAppUpdateDialog;
    }

    public final boolean getShowBudget() {
        return this.showBudget;
    }

    public final boolean getShowCloudEmailPasswordDialog() {
        return this.showCloudEmailPasswordDialog;
    }

    public final boolean getShowCloudPairDialog() {
        return this.showCloudPairDialog;
    }

    public final boolean getShowEpisodeRatings() {
        return this.showEpisodeRatings;
    }

    public final boolean getShowLoadingStats() {
        return this.showLoadingStats;
    }

    public final boolean getShowUnknownSourcesDialog() {
        return this.showUnknownSourcesDialog;
    }

    public final String getSimklUserCode() {
        return this.simklUserCode;
    }

    public final String getSimklUsername() {
        return this.simklUsername;
    }

    public final String getSimklVerificationUrl() {
        return this.simklVerificationUrl;
    }

    public final boolean getSkipProfileSelection() {
        return this.skipProfileSelection;
    }

    public final boolean getSmoothScrolling() {
        return this.smoothScrolling;
    }

    public final boolean getSpoilerBlurEnabled() {
        return this.spoilerBlurEnabled;
    }

    public final String getSubtitleAiApiKey() {
        return this.subtitleAiApiKey;
    }

    public final boolean getSubtitleAiAutoSelect() {
        return this.subtitleAiAutoSelect;
    }

    public final boolean getSubtitleAiEnabled() {
        return this.subtitleAiEnabled;
    }

    public final boolean getSubtitleAiFindBestMatch() {
        return this.subtitleAiFindBestMatch;
    }

    public final SubtitleAiModel getSubtitleAiModel() {
        return this.subtitleAiModel;
    }

    public final String getSubtitleColor() {
        return this.subtitleColor;
    }

    public final String getSubtitleOffset() {
        return this.subtitleOffset;
    }

    public final List<String> getSubtitleOptions() {
        return this.subtitleOptions;
    }

    public final boolean getSubtitlePreloadEnabled() {
        return this.subtitlePreloadEnabled;
    }

    public final boolean getSubtitleRemoveHearingImpaired() {
        return this.subtitleRemoveHearingImpaired;
    }

    public final String getSubtitleSize() {
        return this.subtitleSize;
    }

    public final String getSubtitleStyle() {
        return this.subtitleStyle;
    }

    public final boolean getSubtitleStylized() {
        return this.subtitleStylized;
    }

    public final SyncProgress getSyncProgress() {
        return this.syncProgress;
    }

    public final int getSyncedEpisodes() {
        return this.syncedEpisodes;
    }

    public final int getSyncedMovies() {
        return this.syncedMovies;
    }

    public final String getToastMessage() {
        return this.toastMessage;
    }

    public final ToastType getToastType() {
        return this.toastType;
    }

    public final String getTorrServerBaseUrl() {
        return this.torrServerBaseUrl;
    }

    public final TrackingReadMode getTrackingContinueReadMode() {
        return this.trackingContinueReadMode;
    }

    public final TrackingReadMode getTrackingWatchedReadMode() {
        return this.trackingWatchedReadMode;
    }

    public final TrackingReadMode getTrackingWatchlistReadMode() {
        return this.trackingWatchlistReadMode;
    }

    public final boolean getTrackingWriteToSimkl() {
        return this.trackingWriteToSimkl;
    }

    public final boolean getTrackingWriteToTrakt() {
        return this.trackingWriteToTrakt;
    }

    public final boolean getTrailerAutoPlay() {
        return this.trailerAutoPlay;
    }

    public final int getTrailerDelaySeconds() {
        return this.trailerDelaySeconds;
    }

    public final boolean getTrailerInCards() {
        return this.trailerInCards;
    }

    public final boolean getTrailerSoundEnabled() {
        return this.trailerSoundEnabled;
    }

    public final TraktDeviceCode getTraktCode() {
        return this.traktCode;
    }

    public final String getTraktExpiration() {
        return this.traktExpiration;
    }

    public final String getTraktUsername() {
        return this.traktUsername;
    }

    public final UpdateStatus getUpdateStatus() {
        return this.updateStatus;
    }

    public final int getVolumeBoostDb() {
        return this.volumeBoostDb;
    }

    public final int getWatchlistCount() {
        return this.watchlistCount;
    }

    public int hashCode() {
        int iC = (((((((((((((((((((((androidx.compose.foundation.c.c((((androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.material3.d.e(this.dnsProviderOptions, androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((((androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.material3.d.e(this.audioLanguageOptions, androidx.compose.foundation.c.c(androidx.compose.material3.d.e(this.subtitleOptions, this.defaultSubtitle.hashCode() * 31, 31), 31, this.defaultAudioLanguage), 31), 31, this.cardLayoutMode), 31, this.frameRateMatchingMode) + (this.autoPlayNext ? 1231 : 1237)) * 31) + (this.autoPlaySingleSource ? 1231 : 1237)) * 31, 31, this.autoPlayMinQuality), 31, this.dnsProvider), 31), 31, this.customUserAgent), 31, this.subtitleSize), 31, this.subtitleColor), 31, this.subtitleStyle), 31, this.subtitleOffset) + (this.subtitleStylized ? 1231 : 1237)) * 31) + (this.filterSubtitlesByLanguage ? 1231 : 1237)) * 31, 31, this.secondarySubtitle) + (this.trailerAutoPlay ? 1231 : 1237)) * 31) + (this.trailerSoundEnabled ? 1231 : 1237)) * 31) + this.trailerDelaySeconds) * 31) + (this.trailerInCards ? 1231 : 1237)) * 31) + (this.showBudget ? 1231 : 1237)) * 31) + (this.showEpisodeRatings ? 1231 : 1237)) * 31) + this.volumeBoostDb) * 31) + (this.showLoadingStats ? 1231 : 1237)) * 31) + (this.diagnosticsSharingEnabled ? 1231 : 1237)) * 31) + (this.includeSpecials ? 1231 : 1237)) * 31) + (this.isLoggedIn ? 1231 : 1237)) * 31;
        String str = this.accountEmail;
        int iHashCode = (((iC + (str == null ? 0 : str.hashCode())) * 31) + (this.showCloudPairDialog ? 1231 : 1237)) * 31;
        String str2 = this.cloudUserCode;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.cloudVerificationUrl;
        int iHashCode3 = (((((((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + (this.showCloudEmailPasswordDialog ? 1231 : 1237)) * 31) + (this.isCloudAuthWorking ? 1231 : 1237)) * 31) + (this.isForceCloudSyncing ? 1231 : 1237)) * 31;
        String str4 = this.lastCloudSyncStatus;
        int iHashCode4 = (((((((((iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31) + (this.shouldSwitchProfile ? 1231 : 1237)) * 31) + this.watchlistCount) * 31) + this.historyCount) * 31) + (this.isTraktAuthenticated ? 1231 : 1237)) * 31;
        TraktDeviceCode traktDeviceCode = this.traktCode;
        int iHashCode5 = (((((iHashCode4 + (traktDeviceCode == null ? 0 : traktDeviceCode.hashCode())) * 31) + (this.isTraktAuthStarting ? 1231 : 1237)) * 31) + (this.isTraktPolling ? 1231 : 1237)) * 31;
        String str5 = this.traktExpiration;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.traktUsername;
        int iHashCode7 = (((((iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31) + (this.isMdbListConnected ? 1231 : 1237)) * 31) + (this.mdbListConnecting ? 1231 : 1237)) * 31;
        String str7 = this.mdbListUsername;
        int iHashCode8 = (((((((iHashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31) + (this.isSimklConnected ? 1231 : 1237)) * 31) + (this.isSimklAuthStarting ? 1231 : 1237)) * 31) + (this.isSimklPolling ? 1231 : 1237)) * 31;
        String str8 = this.simklUserCode;
        int iHashCode9 = (iHashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.simklVerificationUrl;
        int iHashCode10 = (iHashCode9 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.simklUsername;
        int iHashCode11 = (this.syncProgress.hashCode() + ((((((((this.trackingWatchedReadMode.hashCode() + ((this.trackingContinueReadMode.hashCode() + ((this.trackingWatchlistReadMode.hashCode() + ((iHashCode10 + (str10 == null ? 0 : str10.hashCode())) * 31)) * 31)) * 31)) * 31) + (this.trackingWriteToTrakt ? 1231 : 1237)) * 31) + (this.trackingWriteToSimkl ? 1231 : 1237)) * 31) + (this.isSyncing ? 1231 : 1237)) * 31)) * 31;
        String str11 = this.lastSyncTime;
        int iC2 = (((androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.foundation.c.c(androidx.compose.material3.d.e(this.iptvPlaylists, androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((((((iHashCode11 + (str11 == null ? 0 : str11.hashCode())) * 31) + this.syncedMovies) * 31) + this.syncedEpisodes) * 31, 31, this.iptvM3uUrl), 31, this.iptvEpgUrl), 31), 31, this.iptvStalkerUrl), 31, this.iptvStalkerMac), 31, this.iptvSortOrder) + this.iptvChannelCount) * 31) + (this.isIptvLoading ? 1231 : 1237)) * 31;
        String str12 = this.iptvError;
        int iHashCode12 = (iC2 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.iptvStatusMessage;
        int iHashCode13 = (this.iptvStatusType.hashCode() + ((iHashCode12 + (str13 == null ? 0 : str13.hashCode())) * 31)) * 31;
        String str14 = this.iptvProgressText;
        int iHashCode14 = (((iHashCode13 + (str14 == null ? 0 : str14.hashCode())) * 31) + this.iptvProgressPercent) * 31;
        String str15 = this.iptvSelectedPlaylistId;
        int iE = (androidx.compose.material3.d.e(this.catalogSearchResults, androidx.compose.foundation.c.c(androidx.compose.material3.d.e(this.catalogs, (((((this.updateStatus.hashCode() + ((androidx.compose.material3.d.e(this.iptvGroupOrder, androidx.compose.material3.d.e(this.iptvHiddenGroups, androidx.compose.material3.d.e(this.iptvAvailableGroups, (iHashCode14 + (str15 == null ? 0 : str15.hashCode())) * 31, 31), 31), 31) + (this.isSelfUpdateSupported ? 1231 : 1237)) * 31)) * 31) + (this.showAppUpdateDialog ? 1231 : 1237)) * 31) + (this.showUnknownSourcesDialog ? 1231 : 1237)) * 31, 31), 31, this.catalogSearchQuery), 31) + (this.isCatalogSearching ? 1231 : 1237)) * 31;
        String str16 = this.catalogSearchError;
        int iHashCode15 = (iE + (str16 == null ? 0 : str16.hashCode())) * 31;
        CatalogPackManifest catalogPackManifest = this.pendingPackManifest;
        int iHashCode16 = (iHashCode15 + (catalogPackManifest == null ? 0 : catalogPackManifest.hashCode())) * 31;
        String str17 = this.pendingPackUrl;
        int iHashCode17 = (((iHashCode16 + (str17 == null ? 0 : str17.hashCode())) * 31) + (this.isPackLoading ? 1231 : 1237)) * 31;
        String str18 = this.packError;
        int iC3 = androidx.compose.foundation.c.c((androidx.compose.material3.d.e(this.addons, (iHashCode17 + (str18 == null ? 0 : str18.hashCode())) * 31, 31) + (this.isRefreshingAddons ? 1231 : 1237)) * 31, 31, this.torrServerBaseUrl);
        HomeServerConnection homeServerConnection = this.homeServerConnection;
        int iE2 = (androidx.compose.material3.d.e(this.homeServerConnections, (iC3 + (homeServerConnection == null ? 0 : homeServerConnection.hashCode())) * 31, 31) + (this.isHomeServerConnecting ? 1231 : 1237)) * 31;
        String str19 = this.homeServerError;
        int iHashCode18 = (iE2 + (str19 == null ? 0 : str19.hashCode())) * 31;
        PlexPinAuthSession plexPinAuthSession = this.plexHomeServerAuth;
        int iC4 = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((androidx.compose.material3.d.e(this.qualityFilters, androidx.compose.foundation.c.c((((androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((((iHashCode18 + (plexPinAuthSession == null ? 0 : plexPinAuthSession.hashCode())) * 31) + (this.isPlexHomeServerPolling ? 1231 : 1237)) * 31, 31, this.contentLanguage), 31, this.deviceModeOverride) + (this.skipProfileSelection ? 1231 : 1237)) * 31) + (this.oledBlackBackground ? 1231 : 1237)) * 31, 31, this.clockFormat), 31) + (this.spoilerBlurEnabled ? 1231 : 1237)) * 31, 31, this.accentColor), 31, this.qualityFilterPresetLabel);
        String str20 = this.toastMessage;
        return ((this.aiKeyServerState.hashCode() + ((((this.subtitleAiModel.hashCode() + androidx.compose.foundation.c.c((((((((((((this.toastType.hashCode() + ((iC4 + (str20 != null ? str20.hashCode() : 0)) * 31)) * 31) + (this.subtitleAiEnabled ? 1231 : 1237)) * 31) + (this.subtitleAiAutoSelect ? 1231 : 1237)) * 31) + (this.subtitleAiFindBestMatch ? 1231 : 1237)) * 31) + (this.subtitlePreloadEnabled ? 1231 : 1237)) * 31) + (this.dolbyVisionCompatEnabled ? 1231 : 1237)) * 31, 31, this.subtitleAiApiKey)) * 31) + (this.subtitleRemoveHearingImpaired ? 1231 : 1237)) * 31)) * 31) + (this.smoothScrolling ? 1231 : 1237);
    }

    public final boolean isCatalogSearching() {
        return this.isCatalogSearching;
    }

    public final boolean isCloudAuthWorking() {
        return this.isCloudAuthWorking;
    }

    public final boolean isForceCloudSyncing() {
        return this.isForceCloudSyncing;
    }

    public final boolean isHomeServerConnecting() {
        return this.isHomeServerConnecting;
    }

    public final boolean isIptvLoading() {
        return this.isIptvLoading;
    }

    public final boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    public final boolean isMdbListConnected() {
        return this.isMdbListConnected;
    }

    public final boolean isPackLoading() {
        return this.isPackLoading;
    }

    public final boolean isPlexHomeServerPolling() {
        return this.isPlexHomeServerPolling;
    }

    public final boolean isRefreshingAddons() {
        return this.isRefreshingAddons;
    }

    public final boolean isSelfUpdateSupported() {
        return this.isSelfUpdateSupported;
    }

    public final boolean isSimklAuthStarting() {
        return this.isSimklAuthStarting;
    }

    public final boolean isSimklConnected() {
        return this.isSimklConnected;
    }

    public final boolean isSimklPolling() {
        return this.isSimklPolling;
    }

    public final boolean isSyncing() {
        return this.isSyncing;
    }

    public final boolean isTraktAuthStarting() {
        return this.isTraktAuthStarting;
    }

    public final boolean isTraktAuthenticated() {
        return this.isTraktAuthenticated;
    }

    public final boolean isTraktPolling() {
        return this.isTraktPolling;
    }

    public String toString() {
        String str = this.defaultSubtitle;
        List<String> list = this.subtitleOptions;
        String str2 = this.defaultAudioLanguage;
        List<String> list2 = this.audioLanguageOptions;
        String str3 = this.cardLayoutMode;
        String str4 = this.frameRateMatchingMode;
        boolean z = this.autoPlayNext;
        boolean z5 = this.autoPlaySingleSource;
        String str5 = this.autoPlayMinQuality;
        String str6 = this.dnsProvider;
        List<String> list3 = this.dnsProviderOptions;
        String str7 = this.customUserAgent;
        String str8 = this.subtitleSize;
        String str9 = this.subtitleColor;
        String str10 = this.subtitleStyle;
        String str11 = this.subtitleOffset;
        boolean z10 = this.subtitleStylized;
        boolean z11 = this.filterSubtitlesByLanguage;
        String str12 = this.secondarySubtitle;
        boolean z12 = this.trailerAutoPlay;
        boolean z13 = this.trailerSoundEnabled;
        int i10 = this.trailerDelaySeconds;
        boolean z14 = this.trailerInCards;
        boolean z15 = this.showBudget;
        boolean z16 = this.showEpisodeRatings;
        int i11 = this.volumeBoostDb;
        boolean z17 = this.showLoadingStats;
        boolean z18 = this.diagnosticsSharingEnabled;
        boolean z19 = this.includeSpecials;
        boolean z20 = this.isLoggedIn;
        String str13 = this.accountEmail;
        boolean z21 = this.showCloudPairDialog;
        String str14 = this.cloudUserCode;
        String str15 = this.cloudVerificationUrl;
        boolean z22 = this.showCloudEmailPasswordDialog;
        boolean z23 = this.isCloudAuthWorking;
        boolean z24 = this.isForceCloudSyncing;
        String str16 = this.lastCloudSyncStatus;
        boolean z25 = this.shouldSwitchProfile;
        int i12 = this.watchlistCount;
        int i13 = this.historyCount;
        boolean z26 = this.isTraktAuthenticated;
        TraktDeviceCode traktDeviceCode = this.traktCode;
        boolean z27 = this.isTraktAuthStarting;
        boolean z28 = this.isTraktPolling;
        String str17 = this.traktExpiration;
        String str18 = this.traktUsername;
        boolean z29 = this.isMdbListConnected;
        boolean z30 = this.mdbListConnecting;
        String str19 = this.mdbListUsername;
        boolean z31 = this.isSimklConnected;
        boolean z32 = this.isSimklAuthStarting;
        boolean z33 = this.isSimklPolling;
        String str20 = this.simklUserCode;
        String str21 = this.simklVerificationUrl;
        String str22 = this.simklUsername;
        TrackingReadMode trackingReadMode = this.trackingWatchlistReadMode;
        TrackingReadMode trackingReadMode2 = this.trackingContinueReadMode;
        TrackingReadMode trackingReadMode3 = this.trackingWatchedReadMode;
        boolean z34 = this.trackingWriteToTrakt;
        boolean z35 = this.trackingWriteToSimkl;
        boolean z36 = this.isSyncing;
        SyncProgress syncProgress = this.syncProgress;
        String str23 = this.lastSyncTime;
        int i14 = this.syncedMovies;
        int i15 = this.syncedEpisodes;
        String str24 = this.iptvM3uUrl;
        String str25 = this.iptvEpgUrl;
        List<IptvPlaylistEntry> list4 = this.iptvPlaylists;
        String str26 = this.iptvStalkerUrl;
        String str27 = this.iptvStalkerMac;
        String str28 = this.iptvSortOrder;
        int i16 = this.iptvChannelCount;
        boolean z37 = this.isIptvLoading;
        String str29 = this.iptvError;
        String str30 = this.iptvStatusMessage;
        ToastType toastType = this.iptvStatusType;
        String str31 = this.iptvProgressText;
        int i17 = this.iptvProgressPercent;
        String str32 = this.iptvSelectedPlaylistId;
        List<String> list5 = this.iptvAvailableGroups;
        List<String> list6 = this.iptvHiddenGroups;
        List<String> list7 = this.iptvGroupOrder;
        boolean z38 = this.isSelfUpdateSupported;
        UpdateStatus updateStatus = this.updateStatus;
        boolean z39 = this.showAppUpdateDialog;
        boolean z40 = this.showUnknownSourcesDialog;
        List<CatalogConfig> list8 = this.catalogs;
        String str33 = this.catalogSearchQuery;
        List<CatalogDiscoveryResult> list9 = this.catalogSearchResults;
        boolean z41 = this.isCatalogSearching;
        String str34 = this.catalogSearchError;
        CatalogPackManifest catalogPackManifest = this.pendingPackManifest;
        String str35 = this.pendingPackUrl;
        boolean z42 = this.isPackLoading;
        String str36 = this.packError;
        List<Addon> list10 = this.addons;
        boolean z43 = this.isRefreshingAddons;
        String str37 = this.torrServerBaseUrl;
        HomeServerConnection homeServerConnection = this.homeServerConnection;
        List<HomeServerConnection> list11 = this.homeServerConnections;
        boolean z44 = this.isHomeServerConnecting;
        String str38 = this.homeServerError;
        PlexPinAuthSession plexPinAuthSession = this.plexHomeServerAuth;
        boolean z45 = this.isPlexHomeServerPolling;
        String str39 = this.contentLanguage;
        String str40 = this.deviceModeOverride;
        boolean z46 = this.skipProfileSelection;
        boolean z47 = this.oledBlackBackground;
        String str41 = this.clockFormat;
        List<QualityFilterConfig> list12 = this.qualityFilters;
        boolean z48 = this.spoilerBlurEnabled;
        String str42 = this.accentColor;
        String str43 = this.qualityFilterPresetLabel;
        String str44 = this.toastMessage;
        ToastType toastType2 = this.toastType;
        boolean z49 = this.subtitleAiEnabled;
        boolean z50 = this.subtitleAiAutoSelect;
        boolean z51 = this.subtitleAiFindBestMatch;
        boolean z52 = this.subtitlePreloadEnabled;
        boolean z53 = this.dolbyVisionCompatEnabled;
        String str45 = this.subtitleAiApiKey;
        SubtitleAiModel subtitleAiModel = this.subtitleAiModel;
        boolean z54 = this.subtitleRemoveHearingImpaired;
        AiKeyServerState aiKeyServerState = this.aiKeyServerState;
        boolean z55 = this.smoothScrolling;
        StringBuilder sb2 = new StringBuilder("SettingsUiState(defaultSubtitle=");
        sb2.append(str);
        sb2.append(", subtitleOptions=");
        sb2.append(list);
        sb2.append(", defaultAudioLanguage=");
        sb2.append(str2);
        sb2.append(", audioLanguageOptions=");
        sb2.append(list2);
        sb2.append(", cardLayoutMode=");
        y.a.i(sb2, str3, ", frameRateMatchingMode=", str4, ", autoPlayNext=");
        v.f.h(sb2, z, ", autoPlaySingleSource=", z5, ", autoPlayMinQuality=");
        y.a.i(sb2, str5, ", dnsProvider=", str6, ", dnsProviderOptions=");
        sb2.append(list3);
        sb2.append(", customUserAgent=");
        sb2.append(str7);
        sb2.append(", subtitleSize=");
        y.a.i(sb2, str8, ", subtitleColor=", str9, ", subtitleStyle=");
        y.a.i(sb2, str10, ", subtitleOffset=", str11, ", subtitleStylized=");
        v.f.h(sb2, z10, ", filterSubtitlesByLanguage=", z11, ", secondarySubtitle=");
        sb2.append(str12);
        sb2.append(", trailerAutoPlay=");
        sb2.append(z12);
        sb2.append(", trailerSoundEnabled=");
        sb2.append(z13);
        sb2.append(", trailerDelaySeconds=");
        sb2.append(i10);
        sb2.append(", trailerInCards=");
        v.f.h(sb2, z14, ", showBudget=", z15, ", showEpisodeRatings=");
        sb2.append(z16);
        sb2.append(", volumeBoostDb=");
        sb2.append(i11);
        sb2.append(", showLoadingStats=");
        v.f.h(sb2, z17, ", diagnosticsSharingEnabled=", z18, ", includeSpecials=");
        v.f.h(sb2, z19, ", isLoggedIn=", z20, ", accountEmail=");
        sb2.append(str13);
        sb2.append(", showCloudPairDialog=");
        sb2.append(z21);
        sb2.append(", cloudUserCode=");
        y.a.i(sb2, str14, ", cloudVerificationUrl=", str15, ", showCloudEmailPasswordDialog=");
        v.f.h(sb2, z22, ", isCloudAuthWorking=", z23, ", isForceCloudSyncing=");
        androidx.fragment.app.a2.B(sb2, z24, ", lastCloudSyncStatus=", str16, ", shouldSwitchProfile=");
        sb2.append(z25);
        sb2.append(", watchlistCount=");
        sb2.append(i12);
        sb2.append(", historyCount=");
        sb2.append(i13);
        sb2.append(", isTraktAuthenticated=");
        sb2.append(z26);
        sb2.append(", traktCode=");
        sb2.append(traktDeviceCode);
        sb2.append(", isTraktAuthStarting=");
        sb2.append(z27);
        sb2.append(", isTraktPolling=");
        androidx.fragment.app.a2.B(sb2, z28, ", traktExpiration=", str17, ", traktUsername=");
        sb2.append(str18);
        sb2.append(", isMdbListConnected=");
        sb2.append(z29);
        sb2.append(", mdbListConnecting=");
        androidx.fragment.app.a2.B(sb2, z30, ", mdbListUsername=", str19, ", isSimklConnected=");
        v.f.h(sb2, z31, ", isSimklAuthStarting=", z32, ", isSimklPolling=");
        androidx.fragment.app.a2.B(sb2, z33, ", simklUserCode=", str20, ", simklVerificationUrl=");
        y.a.i(sb2, str21, ", simklUsername=", str22, ", trackingWatchlistReadMode=");
        sb2.append(trackingReadMode);
        sb2.append(", trackingContinueReadMode=");
        sb2.append(trackingReadMode2);
        sb2.append(", trackingWatchedReadMode=");
        sb2.append(trackingReadMode3);
        sb2.append(", trackingWriteToTrakt=");
        sb2.append(z34);
        sb2.append(", trackingWriteToSimkl=");
        v.f.h(sb2, z35, ", isSyncing=", z36, ", syncProgress=");
        sb2.append(syncProgress);
        sb2.append(", lastSyncTime=");
        sb2.append(str23);
        sb2.append(", syncedMovies=");
        androidx.fragment.app.a2.y(sb2, i14, ", syncedEpisodes=", i15, ", iptvM3uUrl=");
        y.a.i(sb2, str24, ", iptvEpgUrl=", str25, ", iptvPlaylists=");
        sb2.append(list4);
        sb2.append(", iptvStalkerUrl=");
        sb2.append(str26);
        sb2.append(", iptvStalkerMac=");
        y.a.i(sb2, str27, ", iptvSortOrder=", str28, ", iptvChannelCount=");
        sb2.append(i16);
        sb2.append(", isIptvLoading=");
        sb2.append(z37);
        sb2.append(", iptvError=");
        y.a.i(sb2, str29, ", iptvStatusMessage=", str30, ", iptvStatusType=");
        sb2.append(toastType);
        sb2.append(", iptvProgressText=");
        sb2.append(str31);
        sb2.append(", iptvProgressPercent=");
        sb2.append(i17);
        sb2.append(", iptvSelectedPlaylistId=");
        sb2.append(str32);
        sb2.append(", iptvAvailableGroups=");
        androidx.fragment.app.a2.A(sb2, list5, ", iptvHiddenGroups=", list6, ", iptvGroupOrder=");
        sb2.append(list7);
        sb2.append(", isSelfUpdateSupported=");
        sb2.append(z38);
        sb2.append(", updateStatus=");
        sb2.append(updateStatus);
        sb2.append(", showAppUpdateDialog=");
        sb2.append(z39);
        sb2.append(", showUnknownSourcesDialog=");
        sb2.append(z40);
        sb2.append(", catalogs=");
        sb2.append(list8);
        sb2.append(", catalogSearchQuery=");
        sb2.append(str33);
        sb2.append(", catalogSearchResults=");
        sb2.append(list9);
        sb2.append(", isCatalogSearching=");
        androidx.fragment.app.a2.B(sb2, z41, ", catalogSearchError=", str34, ", pendingPackManifest=");
        sb2.append(catalogPackManifest);
        sb2.append(", pendingPackUrl=");
        sb2.append(str35);
        sb2.append(", isPackLoading=");
        androidx.fragment.app.a2.B(sb2, z42, ", packError=", str36, ", addons=");
        sb2.append(list10);
        sb2.append(", isRefreshingAddons=");
        sb2.append(z43);
        sb2.append(", torrServerBaseUrl=");
        sb2.append(str37);
        sb2.append(", homeServerConnection=");
        sb2.append(homeServerConnection);
        sb2.append(", homeServerConnections=");
        sb2.append(list11);
        sb2.append(", isHomeServerConnecting=");
        sb2.append(z44);
        sb2.append(", homeServerError=");
        sb2.append(str38);
        sb2.append(", plexHomeServerAuth=");
        sb2.append(plexPinAuthSession);
        sb2.append(", isPlexHomeServerPolling=");
        androidx.fragment.app.a2.B(sb2, z45, ", contentLanguage=", str39, ", deviceModeOverride=");
        sb2.append(str40);
        sb2.append(", skipProfileSelection=");
        sb2.append(z46);
        sb2.append(", oledBlackBackground=");
        androidx.fragment.app.a2.B(sb2, z47, ", clockFormat=", str41, ", qualityFilters=");
        sb2.append(list12);
        sb2.append(", spoilerBlurEnabled=");
        sb2.append(z48);
        sb2.append(", accentColor=");
        y.a.i(sb2, str42, ", qualityFilterPresetLabel=", str43, ", toastMessage=");
        sb2.append(str44);
        sb2.append(", toastType=");
        sb2.append(toastType2);
        sb2.append(", subtitleAiEnabled=");
        v.f.h(sb2, z49, ", subtitleAiAutoSelect=", z50, ", subtitleAiFindBestMatch=");
        v.f.h(sb2, z51, ", subtitlePreloadEnabled=", z52, ", dolbyVisionCompatEnabled=");
        androidx.fragment.app.a2.B(sb2, z53, ", subtitleAiApiKey=", str45, ", subtitleAiModel=");
        sb2.append(subtitleAiModel);
        sb2.append(", subtitleRemoveHearingImpaired=");
        sb2.append(z54);
        sb2.append(", aiKeyServerState=");
        sb2.append(aiKeyServerState);
        sb2.append(", smoothScrolling=");
        sb2.append(z55);
        sb2.append(")");
        return sb2.toString();
    }

    public SettingsUiState(String str, List<String> list, String str2, List<String> list2, String str3, String str4, boolean z, boolean z5, String str5, String str6, List<String> list3, String str7, String str8, String str9, String str10, String str11, boolean z10, boolean z11, String str12, boolean z12, boolean z13, int i10, boolean z14, boolean z15, boolean z16, int i11, boolean z17, boolean z18, boolean z19, boolean z20, String str13, boolean z21, String str14, String str15, boolean z22, boolean z23, boolean z24, String str16, boolean z25, int i12, int i13, boolean z26, TraktDeviceCode traktDeviceCode, boolean z27, boolean z28, String str17, String str18, boolean z29, boolean z30, String str19, boolean z31, boolean z32, boolean z33, String str20, String str21, String str22, TrackingReadMode trackingReadMode, TrackingReadMode trackingReadMode2, TrackingReadMode trackingReadMode3, boolean z34, boolean z35, boolean z36, SyncProgress syncProgress, String str23, int i14, int i15, String str24, String str25, List<IptvPlaylistEntry> list4, String str26, String str27, String str28, int i16, boolean z37, String str29, String str30, ToastType toastType, String str31, int i17, String str32, List<String> list5, List<String> list6, List<String> list7, boolean z38, UpdateStatus updateStatus, boolean z39, boolean z40, List<CatalogConfig> list8, String str33, List<CatalogDiscoveryResult> list9, boolean z41, String str34, CatalogPackManifest catalogPackManifest, String str35, boolean z42, String str36, List<Addon> list10, boolean z43, String str37, HomeServerConnection homeServerConnection, List<HomeServerConnection> list11, boolean z44, String str38, PlexPinAuthSession plexPinAuthSession, boolean z45, String str39, String str40, boolean z46, boolean z47, String str41, List<QualityFilterConfig> list12, boolean z48, String str42, String str43, String str44, ToastType toastType2, boolean z49, boolean z50, boolean z51, boolean z52, boolean z53, String str45, SubtitleAiModel subtitleAiModel, boolean z54, AiKeyServerState aiKeyServerState, boolean z55) {
        this.defaultSubtitle = str;
        this.subtitleOptions = list;
        this.defaultAudioLanguage = str2;
        this.audioLanguageOptions = list2;
        this.cardLayoutMode = str3;
        this.frameRateMatchingMode = str4;
        this.autoPlayNext = z;
        this.autoPlaySingleSource = z5;
        this.autoPlayMinQuality = str5;
        this.dnsProvider = str6;
        this.dnsProviderOptions = list3;
        this.customUserAgent = str7;
        this.subtitleSize = str8;
        this.subtitleColor = str9;
        this.subtitleStyle = str10;
        this.subtitleOffset = str11;
        this.subtitleStylized = z10;
        this.filterSubtitlesByLanguage = z11;
        this.secondarySubtitle = str12;
        this.trailerAutoPlay = z12;
        this.trailerSoundEnabled = z13;
        this.trailerDelaySeconds = i10;
        this.trailerInCards = z14;
        this.showBudget = z15;
        this.showEpisodeRatings = z16;
        this.volumeBoostDb = i11;
        this.showLoadingStats = z17;
        this.diagnosticsSharingEnabled = z18;
        this.includeSpecials = z19;
        this.isLoggedIn = z20;
        this.accountEmail = str13;
        this.showCloudPairDialog = z21;
        this.cloudUserCode = str14;
        this.cloudVerificationUrl = str15;
        this.showCloudEmailPasswordDialog = z22;
        this.isCloudAuthWorking = z23;
        this.isForceCloudSyncing = z24;
        this.lastCloudSyncStatus = str16;
        this.shouldSwitchProfile = z25;
        this.watchlistCount = i12;
        this.historyCount = i13;
        this.isTraktAuthenticated = z26;
        this.traktCode = traktDeviceCode;
        this.isTraktAuthStarting = z27;
        this.isTraktPolling = z28;
        this.traktExpiration = str17;
        this.traktUsername = str18;
        this.isMdbListConnected = z29;
        this.mdbListConnecting = z30;
        this.mdbListUsername = str19;
        this.isSimklConnected = z31;
        this.isSimklAuthStarting = z32;
        this.isSimklPolling = z33;
        this.simklUserCode = str20;
        this.simklVerificationUrl = str21;
        this.simklUsername = str22;
        this.trackingWatchlistReadMode = trackingReadMode;
        this.trackingContinueReadMode = trackingReadMode2;
        this.trackingWatchedReadMode = trackingReadMode3;
        this.trackingWriteToTrakt = z34;
        this.trackingWriteToSimkl = z35;
        this.isSyncing = z36;
        this.syncProgress = syncProgress;
        this.lastSyncTime = str23;
        this.syncedMovies = i14;
        this.syncedEpisodes = i15;
        this.iptvM3uUrl = str24;
        this.iptvEpgUrl = str25;
        this.iptvPlaylists = list4;
        this.iptvStalkerUrl = str26;
        this.iptvStalkerMac = str27;
        this.iptvSortOrder = str28;
        this.iptvChannelCount = i16;
        this.isIptvLoading = z37;
        this.iptvError = str29;
        this.iptvStatusMessage = str30;
        this.iptvStatusType = toastType;
        this.iptvProgressText = str31;
        this.iptvProgressPercent = i17;
        this.iptvSelectedPlaylistId = str32;
        this.iptvAvailableGroups = list5;
        this.iptvHiddenGroups = list6;
        this.iptvGroupOrder = list7;
        this.isSelfUpdateSupported = z38;
        this.updateStatus = updateStatus;
        this.showAppUpdateDialog = z39;
        this.showUnknownSourcesDialog = z40;
        this.catalogs = list8;
        this.catalogSearchQuery = str33;
        this.catalogSearchResults = list9;
        this.isCatalogSearching = z41;
        this.catalogSearchError = str34;
        this.pendingPackManifest = catalogPackManifest;
        this.pendingPackUrl = str35;
        this.isPackLoading = z42;
        this.packError = str36;
        this.addons = list10;
        this.isRefreshingAddons = z43;
        this.torrServerBaseUrl = str37;
        this.homeServerConnection = homeServerConnection;
        this.homeServerConnections = list11;
        this.isHomeServerConnecting = z44;
        this.homeServerError = str38;
        this.plexHomeServerAuth = plexPinAuthSession;
        this.isPlexHomeServerPolling = z45;
        this.contentLanguage = str39;
        this.deviceModeOverride = str40;
        this.skipProfileSelection = z46;
        this.oledBlackBackground = z47;
        this.clockFormat = str41;
        this.qualityFilters = list12;
        this.spoilerBlurEnabled = z48;
        this.accentColor = str42;
        this.qualityFilterPresetLabel = str43;
        this.toastMessage = str44;
        this.toastType = toastType2;
        this.subtitleAiEnabled = z49;
        this.subtitleAiAutoSelect = z50;
        this.subtitleAiFindBestMatch = z51;
        this.subtitlePreloadEnabled = z52;
        this.dolbyVisionCompatEnabled = z53;
        this.subtitleAiApiKey = str45;
        this.subtitleAiModel = subtitleAiModel;
        this.subtitleRemoveHearingImpaired = z54;
        this.aiKeyServerState = aiKeyServerState;
        this.smoothScrolling = z55;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ SettingsUiState(String str, List list, String str2, List list2, String str3, String str4, boolean z, boolean z5, String str5, String str6, List list3, String str7, String str8, String str9, String str10, String str11, boolean z10, boolean z11, String str12, boolean z12, boolean z13, int i10, boolean z14, boolean z15, boolean z16, int i11, boolean z17, boolean z18, boolean z19, boolean z20, String str13, boolean z21, String str14, String str15, boolean z22, boolean z23, boolean z24, String str16, boolean z25, int i12, int i13, boolean z26, TraktDeviceCode traktDeviceCode, boolean z27, boolean z28, String str17, String str18, boolean z29, boolean z30, String str19, boolean z31, boolean z32, boolean z33, String str20, String str21, String str22, TrackingReadMode trackingReadMode, TrackingReadMode trackingReadMode2, TrackingReadMode trackingReadMode3, boolean z34, boolean z35, boolean z36, SyncProgress syncProgress, String str23, int i14, int i15, String str24, String str25, List list4, String str26, String str27, String str28, int i16, boolean z37, String str29, String str30, ToastType toastType, String str31, int i17, String str32, List list5, List list6, List list7, boolean z38, UpdateStatus updateStatus, boolean z39, boolean z40, List list8, String str33, List list9, boolean z41, String str34, CatalogPackManifest catalogPackManifest, String str35, boolean z42, String str36, List list10, boolean z43, String str37, HomeServerConnection homeServerConnection, List list11, boolean z44, String str38, PlexPinAuthSession plexPinAuthSession, boolean z45, String str39, String str40, boolean z46, boolean z47, String str41, List list12, boolean z48, String str42, String str43, String str44, ToastType toastType2, boolean z49, boolean z50, boolean z51, boolean z52, boolean z53, String str45, SubtitleAiModel subtitleAiModel, boolean z54, AiKeyServerState aiKeyServerState, boolean z55, int i18, int i19, int i20, int i21, kotlin.jvm.internal.h hVar) {
        String str46;
        List list13;
        List list14;
        List listE;
        String str47 = (i18 & 1) != 0 ? "Off" : str;
        int i22 = i18 & 2;
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        List list15 = i22 != 0 ? zVar : list;
        String str48 = (i18 & 4) != 0 ? "Auto (Original)" : str2;
        List list16 = (i18 & 8) != 0 ? zVar : list2;
        String str49 = (i18 & 16) != 0 ? CardLayoutModeKt.CARD_LAYOUT_MODE_LANDSCAPE : str3;
        String str50 = (i18 & 32) != 0 ? "Off" : str4;
        boolean z56 = (i18 & 64) != 0 ? true : z;
        boolean z57 = (i18 & 128) != 0 ? true : z5;
        String str51 = (i18 & 256) != 0 ? "Any" : str5;
        String str52 = (i18 & 512) != 0 ? "System DNS" : str6;
        if ((i18 & 1024) != 0) {
            str46 = str47;
            list13 = list15;
            list14 = zVar;
            listE = t7.a.E("System DNS", "Cloudflare", "Google", "AdGuard");
        } else {
            str46 = str47;
            list13 = list15;
            list14 = zVar;
            listE = list3;
        }
        String str53 = (i18 & 2048) != 0 ? "" : str7;
        String str54 = (i18 & 4096) != 0 ? "Medium" : str8;
        List list17 = listE;
        String str55 = (i18 & 8192) != 0 ? "White" : str9;
        String str56 = (i18 & 16384) != 0 ? "Bold" : str10;
        String str57 = (i18 & 32768) != 0 ? "Bottom" : str11;
        boolean z58 = (i18 & 65536) != 0 ? true : z10;
        boolean z59 = (i18 & 131072) != 0 ? true : z11;
        String str58 = (i18 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? "Off" : str12;
        boolean z60 = (i18 & 524288) != 0 ? false : z12;
        boolean z61 = (i18 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? false : z13;
        int i23 = (i18 & 2097152) != 0 ? 2 : i10;
        boolean z62 = (i18 & 4194304) != 0 ? true : z14;
        boolean z63 = (i18 & 8388608) != 0 ? true : z15;
        boolean z64 = (i18 & 16777216) != 0 ? true : z16;
        int i24 = (i18 & 33554432) != 0 ? 0 : i11;
        boolean z65 = (i18 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? true : z17;
        boolean z66 = (i18 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? true : z18;
        boolean z67 = (i18 & 268435456) != 0 ? false : z19;
        boolean z68 = (i18 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? false : z20;
        String str59 = (i18 & 1073741824) != 0 ? null : str13;
        boolean z69 = (i18 & Integer.MIN_VALUE) != 0 ? false : z21;
        this(str46, list13, str48, list16, str49, str50, z56, z57, str51, str52, list17, str53, str54, str55, str56, str57, z58, z59, str58, z60, z61, i23, z62, z63, z64, i24, z65, z66, z67, z68, str59, z69, (i19 & 1) != 0 ? null : str14, (i19 & 2) != 0 ? null : str15, (i19 & 4) != 0 ? false : z22, (i19 & 8) != 0 ? false : z23, (i19 & 16) != 0 ? false : z24, (i19 & 32) != 0 ? null : str16, (i19 & 64) != 0 ? false : z25, (i19 & 128) != 0 ? 0 : i12, (i19 & 256) != 0 ? 0 : i13, (i19 & 512) != 0 ? false : z26, (i19 & 1024) != 0 ? null : traktDeviceCode, (i19 & 2048) != 0 ? false : z27, (i19 & 4096) != 0 ? false : z28, (i19 & 8192) != 0 ? null : str17, (i19 & 16384) != 0 ? null : str18, (i19 & 32768) != 0 ? false : z29, (i19 & 65536) != 0 ? false : z30, (i19 & 131072) != 0 ? null : str19, (i19 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? false : z31, (i19 & 524288) != 0 ? false : z32, (i19 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? false : z33, (i19 & 2097152) != 0 ? null : str20, (i19 & 4194304) != 0 ? null : str21, (i19 & 8388608) != 0 ? null : str22, (i19 & 16777216) != 0 ? TrackingReadMode.AUTO : trackingReadMode, (i19 & 33554432) != 0 ? TrackingReadMode.AUTO : trackingReadMode2, (i19 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? TrackingReadMode.AUTO : trackingReadMode3, (i19 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? false : z34, (i19 & 268435456) != 0 ? false : z35, (i19 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? false : z36, (i19 & 1073741824) != 0 ? new SyncProgress(null, null, 0, 0, 0, 0, 63, null) : syncProgress, (i19 & Integer.MIN_VALUE) != 0 ? null : str23, (i20 & 1) != 0 ? 0 : i14, (i20 & 2) != 0 ? 0 : i15, (i20 & 4) != 0 ? "" : str24, (i20 & 8) != 0 ? "" : str25, (i20 & 16) != 0 ? list14 : list4, (i20 & 32) != 0 ? "" : str26, (i20 & 64) != 0 ? "" : str27, (i20 & 128) != 0 ? "provider" : str28, (i20 & 256) != 0 ? 0 : i16, (i20 & 512) != 0 ? false : z37, (i20 & 1024) != 0 ? null : str29, (i20 & 2048) != 0 ? null : str30, (i20 & 4096) != 0 ? ToastType.INFO : toastType, (i20 & 8192) != 0 ? null : str31, (i20 & 16384) != 0 ? 0 : i17, (i20 & 32768) != 0 ? null : str32, (i20 & 65536) != 0 ? list14 : list5, (i20 & 131072) != 0 ? list14 : list6, (i20 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? list14 : list7, (i20 & 524288) != 0 ? true : z38, (i20 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? UpdateStatus.Idle.INSTANCE : updateStatus, (i20 & 2097152) != 0 ? false : z39, (i20 & 4194304) != 0 ? false : z40, (i20 & 8388608) != 0 ? list14 : list8, (i20 & 16777216) != 0 ? "" : str33, (i20 & 33554432) != 0 ? list14 : list9, (i20 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? false : z41, (i20 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? null : str34, (i20 & 268435456) != 0 ? null : catalogPackManifest, (i20 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? null : str35, (i20 & 1073741824) != 0 ? false : z42, (i20 & Integer.MIN_VALUE) != 0 ? null : str36, (i21 & 1) != 0 ? list14 : list10, (i21 & 2) != 0 ? false : z43, (i21 & 4) != 0 ? "" : str37, (i21 & 8) != 0 ? null : homeServerConnection, (i21 & 16) != 0 ? list14 : list11, (i21 & 32) != 0 ? false : z44, (i21 & 64) != 0 ? null : str38, (i21 & 128) != 0 ? null : plexPinAuthSession, (i21 & 256) != 0 ? false : z45, (i21 & 512) != 0 ? "en-US" : str39, (i21 & 1024) != 0 ? TtmlNode.TEXT_EMPHASIS_AUTO : str40, (i21 & 2048) != 0 ? false : z46, (i21 & 4096) != 0 ? false : z47, (i21 & 8192) != 0 ? "24h" : str41, (i21 & 16384) == 0 ? list12 : list14, (i21 & 32768) != 0 ? false : z48, (i21 & 65536) != 0 ? "White" : str42, (i21 & 131072) != 0 ? "OFF" : str43, (i21 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) == 0 ? str44 : null, (i21 & 524288) != 0 ? ToastType.INFO : toastType2, (i21 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? false : z49, (i21 & 2097152) != 0 ? false : z50, (i21 & 4194304) == 0 ? z51 : false, (i21 & 8388608) != 0 ? true : z52, (i21 & 16777216) != 0 ? true : z53, (i21 & 33554432) == 0 ? str45 : "", (i21 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? SubtitleAiModel.GROQ_LLAMA_70B : subtitleAiModel, (i21 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? true : z54, (i21 & 268435456) != 0 ? new AiKeyServerState(false, null, null, false, 15, null) : aiKeyServerState, (i21 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? true : z55);
    }
}
