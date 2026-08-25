package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.fragment.app.a2;
import androidx.media3.common.C;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\bn\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001BÓ\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0005\u0012\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0014\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%\u0012\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'\u0012\b\b\u0002\u0010(\u001a\u00020\u0014\u0012\b\b\u0002\u0010)\u001a\u00020\u0005\u0012\b\b\u0002\u0010*\u001a\u00020+\u0012\b\b\u0002\u0010,\u001a\u00020!\u0012\b\b\u0002\u0010-\u001a\u00020\u0003\u0012\b\b\u0002\u0010.\u001a\u00020\u0014\u0012\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u00100\u001a\u00020\u0014\u0012\b\b\u0002\u00101\u001a\u00020\u0014\u0012\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b6\u00107J\t\u0010l\u001a\u00020\u0003HÆ\u0003J\t\u0010m\u001a\u00020\u0005HÆ\u0003J\t\u0010n\u001a\u00020\u0005HÆ\u0003J\t\u0010o\u001a\u00020\u0005HÆ\u0003J\t\u0010p\u001a\u00020\u0005HÆ\u0003J\u000b\u0010q\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010r\u001a\u00020\u0005HÆ\u0003J\t\u0010s\u001a\u00020\u0005HÆ\u0003J\t\u0010t\u001a\u00020\u0005HÆ\u0003J\t\u0010u\u001a\u00020\u0005HÆ\u0003J\t\u0010v\u001a\u00020\u000fHÆ\u0003J\t\u0010w\u001a\u00020\u0005HÆ\u0003J\u000b\u0010x\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010y\u001a\u00020\u0003HÆ\u0003J\t\u0010z\u001a\u00020\u0014HÆ\u0003J\u0010\u0010{\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010KJ\u000b\u0010|\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000f\u0010}\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018HÆ\u0003J\u000b\u0010~\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u007f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\n\u0010\u0080\u0001\u001a\u00020\u0014HÆ\u0003J\u0011\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010KJ\u0011\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010KJ\f\u0010\u0083\u0001\u001a\u0004\u0018\u00010\u001fHÆ\u0003J\u0011\u0010\u0084\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010WJ\u0011\u0010\u0085\u0001\u001a\u0004\u0018\u00010!HÆ\u0003¢\u0006\u0002\u0010WJ\f\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u0087\u0001\u001a\u0004\u0018\u00010%HÆ\u0003J\f\u0010\u0088\u0001\u001a\u0004\u0018\u00010'HÆ\u0003J\n\u0010\u0089\u0001\u001a\u00020\u0014HÆ\u0003J\n\u0010\u008a\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u008b\u0001\u001a\u00020+HÆ\u0003J\n\u0010\u008c\u0001\u001a\u00020!HÆ\u0003J\n\u0010\u008d\u0001\u001a\u00020\u0003HÆ\u0003J\n\u0010\u008e\u0001\u001a\u00020\u0014HÆ\u0003J\f\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\n\u0010\u0090\u0001\u001a\u00020\u0014HÆ\u0003J\n\u0010\u0091\u0001\u001a\u00020\u0014HÆ\u0003J\f\u0010\u0092\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u0094\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\f\u0010\u0095\u0001\u001a\u0004\u0018\u00010\u0005HÆ\u0003Jà\u0003\u0010\u0096\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00052\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u001b\u001a\u00020\u00142\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010!2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010(\u001a\u00020\u00142\b\b\u0002\u0010)\u001a\u00020\u00052\b\b\u0002\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020!2\b\b\u0002\u0010-\u001a\u00020\u00032\b\b\u0002\u0010.\u001a\u00020\u00142\n\b\u0002\u0010/\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u00100\u001a\u00020\u00142\b\b\u0002\u00101\u001a\u00020\u00142\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00103\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0003\u0010\u0097\u0001J\u0017\u0010\u0098\u0001\u001a\u00020\u00142\n\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u009a\u0001HÖ\u0083\u0004J\u000b\u0010\u009b\u0001\u001a\u00020\u0003HÖ\u0081\u0004J\u000b\u0010\u009c\u0001\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b<\u0010;R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b=\u0010;R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b>\u0010;R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b?\u0010;R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b@\u0010;R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bA\u0010;R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bB\u0010;R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bC\u0010;R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bF\u0010;R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bG\u0010;R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bH\u00109R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010IR\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010L\u001a\u0004\bJ\u0010KR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bM\u0010;R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018¢\u0006\b\n\u0000\u001a\u0004\bN\u0010OR\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bP\u0010;R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010;R\u0011\u0010\u001b\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010IR\u0015\u0010\u001c\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010L\u001a\u0004\bR\u0010KR\u0015\u0010\u001d\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010L\u001a\u0004\bS\u0010KR\u0013\u0010\u001e\u001a\u0004\u0018\u00010\u001f¢\u0006\b\n\u0000\u001a\u0004\bT\u0010UR\u0015\u0010 \u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010X\u001a\u0004\bV\u0010WR\u0015\u0010\"\u001a\u0004\u0018\u00010!¢\u0006\n\n\u0002\u0010X\u001a\u0004\bY\u0010WR\u0013\u0010#\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010;R\u0013\u0010$\u001a\u0004\u0018\u00010%¢\u0006\b\n\u0000\u001a\u0004\b[\u0010\\R\u0013\u0010&\u001a\u0004\u0018\u00010'¢\u0006\b\n\u0000\u001a\u0004\b]\u0010^R\u0011\u0010(\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b_\u0010IR\u0011\u0010)\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b`\u0010;R\u0011\u0010*\u001a\u00020+¢\u0006\b\n\u0000\u001a\u0004\ba\u0010bR\u0011\u0010,\u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\bc\u0010dR\u0011\u0010-\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\be\u00109R\u0011\u0010.\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b.\u0010IR\u0013\u0010/\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bf\u0010;R\u0011\u00100\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\bg\u0010IR\u0011\u00101\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b1\u0010IR\u0013\u00102\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bh\u0010;R\u0013\u00103\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bi\u0010;R\u0013\u00104\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bj\u0010;R\u0013\u00105\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\bk\u0010;¨\u0006\u009d\u0001"}, d2 = {"Lcom/arflix/tv/data/model/MediaItem;", "Ljava/io/Serializable;", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Title, "", MediaTrack.ROLE_SUBTITLE, "overview", "year", "releaseDate", "rating", "duration", "imdbRating", "tmdbRating", "mediaType", "Lcom/arflix/tv/data/model/MediaType;", "image", "backdrop", "progress", "isWatched", "", "traktId", "badge", "genreIds", "", "originalLanguage", "primaryNetworkLogo", "isOngoing", "totalEpisodes", "watchedEpisodes", "nextEpisode", "Lcom/arflix/tv/data/model/NextEpisode;", "budget", "", "revenue", "status", "collectionGroup", "Lcom/arflix/tv/data/model/CollectionGroupKind;", "collectionTileShape", "Lcom/arflix/tv/data/model/CollectionTileShape;", "collectionHideTitle", "character", "popularity", "", "addedAt", "sourceOrder", "isPlaceholder", "timeRemainingLabel", "showPlaybackProgress", "isHomeServer", "homeServerItemId", "homeServerSourceRef", "homeServerProvider", "homeServerImdbId", "<init>", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/MediaType;Ljava/lang/String;Ljava/lang/String;IZLjava/lang/Integer;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Integer;Ljava/lang/Integer;Lcom/arflix/tv/data/model/NextEpisode;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Lcom/arflix/tv/data/model/CollectionGroupKind;Lcom/arflix/tv/data/model/CollectionTileShape;ZLjava/lang/String;FJIZLjava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getTitle", "()Ljava/lang/String;", "getSubtitle", "getOverview", "getYear", "getReleaseDate", "getRating", "getDuration", "getImdbRating", "getTmdbRating", "getMediaType", "()Lcom/arflix/tv/data/model/MediaType;", "getImage", "getBackdrop", "getProgress", "()Z", "getTraktId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getBadge", "getGenreIds", "()Ljava/util/List;", "getOriginalLanguage", "getPrimaryNetworkLogo", "getTotalEpisodes", "getWatchedEpisodes", "getNextEpisode", "()Lcom/arflix/tv/data/model/NextEpisode;", "getBudget", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRevenue", "getStatus", "getCollectionGroup", "()Lcom/arflix/tv/data/model/CollectionGroupKind;", "getCollectionTileShape", "()Lcom/arflix/tv/data/model/CollectionTileShape;", "getCollectionHideTitle", "getCharacter", "getPopularity", "()F", "getAddedAt", "()J", "getSourceOrder", "getTimeRemainingLabel", "getShowPlaybackProgress", "getHomeServerItemId", "getHomeServerSourceRef", "getHomeServerProvider", "getHomeServerImdbId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component40", "component41", "component42", "copy", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/MediaType;Ljava/lang/String;Ljava/lang/String;IZLjava/lang/Integer;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Integer;Ljava/lang/Integer;Lcom/arflix/tv/data/model/NextEpisode;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Lcom/arflix/tv/data/model/CollectionGroupKind;Lcom/arflix/tv/data/model/CollectionTileShape;ZLjava/lang/String;FJIZLjava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/model/MediaItem;", "equals", "other", "", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MediaItem implements Serializable {
    public static final int $stable = 0;
    private final long addedAt;
    private final String backdrop;
    private final String badge;
    private final Long budget;
    private final String character;
    private final CollectionGroupKind collectionGroup;
    private final boolean collectionHideTitle;
    private final CollectionTileShape collectionTileShape;
    private final String duration;
    private final List<Integer> genreIds;
    private final String homeServerImdbId;
    private final String homeServerItemId;
    private final String homeServerProvider;
    private final String homeServerSourceRef;
    private final int id;
    private final String image;
    private final String imdbRating;
    private final boolean isHomeServer;
    private final boolean isOngoing;
    private final boolean isPlaceholder;
    private final boolean isWatched;
    private final MediaType mediaType;
    private final NextEpisode nextEpisode;
    private final String originalLanguage;
    private final String overview;
    private final float popularity;
    private final String primaryNetworkLogo;
    private final int progress;
    private final String rating;
    private final String releaseDate;
    private final Long revenue;
    private final boolean showPlaybackProgress;
    private final int sourceOrder;
    private final String status;
    private final String subtitle;
    private final String timeRemainingLabel;
    private final String title;
    private final String tmdbRating;
    private final Integer totalEpisodes;
    private final Integer traktId;
    private final Integer watchedEpisodes;
    private final String year;

    public MediaItem(int i10, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, MediaType mediaType, String str10, String str11, int i11, boolean z, Integer num, String str12, List<Integer> list, String str13, String str14, boolean z5, Integer num2, Integer num3, NextEpisode nextEpisode, Long l10, Long l11, String str15, CollectionGroupKind collectionGroupKind, CollectionTileShape collectionTileShape, boolean z10, String str16, float f10, long j10, int i12, boolean z11, String str17, boolean z12, boolean z13, String str18, String str19, String str20, String str21) {
        this.id = i10;
        this.title = str;
        this.subtitle = str2;
        this.overview = str3;
        this.year = str4;
        this.releaseDate = str5;
        this.rating = str6;
        this.duration = str7;
        this.imdbRating = str8;
        this.tmdbRating = str9;
        this.mediaType = mediaType;
        this.image = str10;
        this.backdrop = str11;
        this.progress = i11;
        this.isWatched = z;
        this.traktId = num;
        this.badge = str12;
        this.genreIds = list;
        this.originalLanguage = str13;
        this.primaryNetworkLogo = str14;
        this.isOngoing = z5;
        this.totalEpisodes = num2;
        this.watchedEpisodes = num3;
        this.nextEpisode = nextEpisode;
        this.budget = l10;
        this.revenue = l11;
        this.status = str15;
        this.collectionGroup = collectionGroupKind;
        this.collectionTileShape = collectionTileShape;
        this.collectionHideTitle = z10;
        this.character = str16;
        this.popularity = f10;
        this.addedAt = j10;
        this.sourceOrder = i12;
        this.isPlaceholder = z11;
        this.timeRemainingLabel = str17;
        this.showPlaybackProgress = z12;
        this.isHomeServer = z13;
        this.homeServerItemId = str18;
        this.homeServerSourceRef = str19;
        this.homeServerProvider = str20;
        this.homeServerImdbId = str21;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MediaItem copy$default(MediaItem mediaItem, int i10, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, MediaType mediaType, String str10, String str11, int i11, boolean z, Integer num, String str12, List list, String str13, String str14, boolean z5, Integer num2, Integer num3, NextEpisode nextEpisode, Long l10, Long l11, String str15, CollectionGroupKind collectionGroupKind, CollectionTileShape collectionTileShape, boolean z10, String str16, float f10, long j10, int i12, boolean z11, String str17, boolean z12, boolean z13, String str18, String str19, String str20, String str21, int i13, int i14, Object obj) {
        boolean z14;
        String str22;
        long j11;
        int i15 = (i13 & 1) != 0 ? mediaItem.id : i10;
        String str23 = (i13 & 2) != 0 ? mediaItem.title : str;
        String str24 = (i13 & 4) != 0 ? mediaItem.subtitle : str2;
        String str25 = (i13 & 8) != 0 ? mediaItem.overview : str3;
        String str26 = (i13 & 16) != 0 ? mediaItem.year : str4;
        String str27 = (i13 & 32) != 0 ? mediaItem.releaseDate : str5;
        String str28 = (i13 & 64) != 0 ? mediaItem.rating : str6;
        String str29 = (i13 & 128) != 0 ? mediaItem.duration : str7;
        String str30 = (i13 & 256) != 0 ? mediaItem.imdbRating : str8;
        String str31 = (i13 & 512) != 0 ? mediaItem.tmdbRating : str9;
        MediaType mediaType2 = (i13 & 1024) != 0 ? mediaItem.mediaType : mediaType;
        String str32 = (i13 & 2048) != 0 ? mediaItem.image : str10;
        String str33 = (i13 & 4096) != 0 ? mediaItem.backdrop : str11;
        int i16 = i15;
        int i17 = (i13 & 8192) != 0 ? mediaItem.progress : i11;
        boolean z15 = (i13 & 16384) != 0 ? mediaItem.isWatched : z;
        Integer num4 = (i13 & 32768) != 0 ? mediaItem.traktId : num;
        String str34 = (i13 & 65536) != 0 ? mediaItem.badge : str12;
        List list2 = (i13 & 131072) != 0 ? mediaItem.genreIds : list;
        String str35 = (i13 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? mediaItem.originalLanguage : str13;
        String str36 = (i13 & 524288) != 0 ? mediaItem.primaryNetworkLogo : str14;
        boolean z16 = (i13 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? mediaItem.isOngoing : z5;
        Integer num5 = (i13 & 2097152) != 0 ? mediaItem.totalEpisodes : num2;
        Integer num6 = (i13 & 4194304) != 0 ? mediaItem.watchedEpisodes : num3;
        NextEpisode nextEpisode2 = (i13 & 8388608) != 0 ? mediaItem.nextEpisode : nextEpisode;
        Long l12 = (i13 & 16777216) != 0 ? mediaItem.budget : l10;
        Long l13 = (i13 & 33554432) != 0 ? mediaItem.revenue : l11;
        String str37 = (i13 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? mediaItem.status : str15;
        CollectionGroupKind collectionGroupKind2 = (i13 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? mediaItem.collectionGroup : collectionGroupKind;
        CollectionTileShape collectionTileShape2 = (i13 & 268435456) != 0 ? mediaItem.collectionTileShape : collectionTileShape;
        boolean z17 = (i13 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? mediaItem.collectionHideTitle : z10;
        String str38 = (i13 & 1073741824) != 0 ? mediaItem.character : str16;
        float f11 = (i13 & Integer.MIN_VALUE) != 0 ? mediaItem.popularity : f10;
        if ((i14 & 1) != 0) {
            z14 = z15;
            str22 = str23;
            j11 = mediaItem.addedAt;
        } else {
            z14 = z15;
            str22 = str23;
            j11 = j10;
        }
        return mediaItem.copy(i16, str22, str24, str25, str26, str27, str28, str29, str30, str31, mediaType2, str32, str33, i17, z14, num4, str34, list2, str35, str36, z16, num5, num6, nextEpisode2, l12, l13, str37, collectionGroupKind2, collectionTileShape2, z17, str38, f11, j11, (i14 & 2) != 0 ? mediaItem.sourceOrder : i12, (i14 & 4) != 0 ? mediaItem.isPlaceholder : z11, (i14 & 8) != 0 ? mediaItem.timeRemainingLabel : str17, (i14 & 16) != 0 ? mediaItem.showPlaybackProgress : z12, (i14 & 32) != 0 ? mediaItem.isHomeServer : z13, (i14 & 64) != 0 ? mediaItem.homeServerItemId : str18, (i14 & 128) != 0 ? mediaItem.homeServerSourceRef : str19, (i14 & 256) != 0 ? mediaItem.homeServerProvider : str20, (i14 & 512) != 0 ? mediaItem.homeServerImdbId : str21);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getTmdbRating() {
        return this.tmdbRating;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final MediaType getMediaType() {
        return this.mediaType;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getImage() {
        return this.image;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getBackdrop() {
        return this.backdrop;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final int getProgress() {
        return this.progress;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final boolean getIsWatched() {
        return this.isWatched;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final Integer getTraktId() {
        return this.traktId;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getBadge() {
        return this.badge;
    }

    public final List<Integer> component18() {
        return this.genreIds;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getOriginalLanguage() {
        return this.originalLanguage;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getPrimaryNetworkLogo() {
        return this.primaryNetworkLogo;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final boolean getIsOngoing() {
        return this.isOngoing;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final Integer getTotalEpisodes() {
        return this.totalEpisodes;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final Integer getWatchedEpisodes() {
        return this.watchedEpisodes;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final NextEpisode getNextEpisode() {
        return this.nextEpisode;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final Long getBudget() {
        return this.budget;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final Long getRevenue() {
        return this.revenue;
    }

    /* JADX INFO: renamed from: component27, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component28, reason: from getter */
    public final CollectionGroupKind getCollectionGroup() {
        return this.collectionGroup;
    }

    /* JADX INFO: renamed from: component29, reason: from getter */
    public final CollectionTileShape getCollectionTileShape() {
        return this.collectionTileShape;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSubtitle() {
        return this.subtitle;
    }

    /* JADX INFO: renamed from: component30, reason: from getter */
    public final boolean getCollectionHideTitle() {
        return this.collectionHideTitle;
    }

    /* JADX INFO: renamed from: component31, reason: from getter */
    public final String getCharacter() {
        return this.character;
    }

    /* JADX INFO: renamed from: component32, reason: from getter */
    public final float getPopularity() {
        return this.popularity;
    }

    /* JADX INFO: renamed from: component33, reason: from getter */
    public final long getAddedAt() {
        return this.addedAt;
    }

    /* JADX INFO: renamed from: component34, reason: from getter */
    public final int getSourceOrder() {
        return this.sourceOrder;
    }

    /* JADX INFO: renamed from: component35, reason: from getter */
    public final boolean getIsPlaceholder() {
        return this.isPlaceholder;
    }

    /* JADX INFO: renamed from: component36, reason: from getter */
    public final String getTimeRemainingLabel() {
        return this.timeRemainingLabel;
    }

    /* JADX INFO: renamed from: component37, reason: from getter */
    public final boolean getShowPlaybackProgress() {
        return this.showPlaybackProgress;
    }

    /* JADX INFO: renamed from: component38, reason: from getter */
    public final boolean getIsHomeServer() {
        return this.isHomeServer;
    }

    /* JADX INFO: renamed from: component39, reason: from getter */
    public final String getHomeServerItemId() {
        return this.homeServerItemId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getOverview() {
        return this.overview;
    }

    /* JADX INFO: renamed from: component40, reason: from getter */
    public final String getHomeServerSourceRef() {
        return this.homeServerSourceRef;
    }

    /* JADX INFO: renamed from: component41, reason: from getter */
    public final String getHomeServerProvider() {
        return this.homeServerProvider;
    }

    /* JADX INFO: renamed from: component42, reason: from getter */
    public final String getHomeServerImdbId() {
        return this.homeServerImdbId;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getYear() {
        return this.year;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getReleaseDate() {
        return this.releaseDate;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getRating() {
        return this.rating;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getDuration() {
        return this.duration;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getImdbRating() {
        return this.imdbRating;
    }

    public final MediaItem copy(int id, String title, String subtitle, String overview, String year, String releaseDate, String rating, String duration, String imdbRating, String tmdbRating, MediaType mediaType, String image, String backdrop, int progress, boolean isWatched, Integer traktId, String badge, List<Integer> genreIds, String originalLanguage, String primaryNetworkLogo, boolean isOngoing, Integer totalEpisodes, Integer watchedEpisodes, NextEpisode nextEpisode, Long budget, Long revenue, String status, CollectionGroupKind collectionGroup, CollectionTileShape collectionTileShape, boolean collectionHideTitle, String character, float popularity, long addedAt, int sourceOrder, boolean isPlaceholder, String timeRemainingLabel, boolean showPlaybackProgress, boolean isHomeServer, String homeServerItemId, String homeServerSourceRef, String homeServerProvider, String homeServerImdbId) {
        return new MediaItem(id, title, subtitle, overview, year, releaseDate, rating, duration, imdbRating, tmdbRating, mediaType, image, backdrop, progress, isWatched, traktId, badge, genreIds, originalLanguage, primaryNetworkLogo, isOngoing, totalEpisodes, watchedEpisodes, nextEpisode, budget, revenue, status, collectionGroup, collectionTileShape, collectionHideTitle, character, popularity, addedAt, sourceOrder, isPlaceholder, timeRemainingLabel, showPlaybackProgress, isHomeServer, homeServerItemId, homeServerSourceRef, homeServerProvider, homeServerImdbId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MediaItem)) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) other;
        return this.id == mediaItem.id && p.a(this.title, mediaItem.title) && p.a(this.subtitle, mediaItem.subtitle) && p.a(this.overview, mediaItem.overview) && p.a(this.year, mediaItem.year) && p.a(this.releaseDate, mediaItem.releaseDate) && p.a(this.rating, mediaItem.rating) && p.a(this.duration, mediaItem.duration) && p.a(this.imdbRating, mediaItem.imdbRating) && p.a(this.tmdbRating, mediaItem.tmdbRating) && this.mediaType == mediaItem.mediaType && p.a(this.image, mediaItem.image) && p.a(this.backdrop, mediaItem.backdrop) && this.progress == mediaItem.progress && this.isWatched == mediaItem.isWatched && p.a(this.traktId, mediaItem.traktId) && p.a(this.badge, mediaItem.badge) && p.a(this.genreIds, mediaItem.genreIds) && p.a(this.originalLanguage, mediaItem.originalLanguage) && p.a(this.primaryNetworkLogo, mediaItem.primaryNetworkLogo) && this.isOngoing == mediaItem.isOngoing && p.a(this.totalEpisodes, mediaItem.totalEpisodes) && p.a(this.watchedEpisodes, mediaItem.watchedEpisodes) && p.a(this.nextEpisode, mediaItem.nextEpisode) && p.a(this.budget, mediaItem.budget) && p.a(this.revenue, mediaItem.revenue) && p.a(this.status, mediaItem.status) && this.collectionGroup == mediaItem.collectionGroup && this.collectionTileShape == mediaItem.collectionTileShape && this.collectionHideTitle == mediaItem.collectionHideTitle && p.a(this.character, mediaItem.character) && Float.compare(this.popularity, mediaItem.popularity) == 0 && this.addedAt == mediaItem.addedAt && this.sourceOrder == mediaItem.sourceOrder && this.isPlaceholder == mediaItem.isPlaceholder && p.a(this.timeRemainingLabel, mediaItem.timeRemainingLabel) && this.showPlaybackProgress == mediaItem.showPlaybackProgress && this.isHomeServer == mediaItem.isHomeServer && p.a(this.homeServerItemId, mediaItem.homeServerItemId) && p.a(this.homeServerSourceRef, mediaItem.homeServerSourceRef) && p.a(this.homeServerProvider, mediaItem.homeServerProvider) && p.a(this.homeServerImdbId, mediaItem.homeServerImdbId);
    }

    public final long getAddedAt() {
        return this.addedAt;
    }

    public final String getBackdrop() {
        return this.backdrop;
    }

    public final String getBadge() {
        return this.badge;
    }

    public final Long getBudget() {
        return this.budget;
    }

    public final String getCharacter() {
        return this.character;
    }

    public final CollectionGroupKind getCollectionGroup() {
        return this.collectionGroup;
    }

    public final boolean getCollectionHideTitle() {
        return this.collectionHideTitle;
    }

    public final CollectionTileShape getCollectionTileShape() {
        return this.collectionTileShape;
    }

    public final String getDuration() {
        return this.duration;
    }

    public final List<Integer> getGenreIds() {
        return this.genreIds;
    }

    public final String getHomeServerImdbId() {
        return this.homeServerImdbId;
    }

    public final String getHomeServerItemId() {
        return this.homeServerItemId;
    }

    public final String getHomeServerProvider() {
        return this.homeServerProvider;
    }

    public final String getHomeServerSourceRef() {
        return this.homeServerSourceRef;
    }

    public final int getId() {
        return this.id;
    }

    public final String getImage() {
        return this.image;
    }

    public final String getImdbRating() {
        return this.imdbRating;
    }

    public final MediaType getMediaType() {
        return this.mediaType;
    }

    public final NextEpisode getNextEpisode() {
        return this.nextEpisode;
    }

    public final String getOriginalLanguage() {
        return this.originalLanguage;
    }

    public final String getOverview() {
        return this.overview;
    }

    public final float getPopularity() {
        return this.popularity;
    }

    public final String getPrimaryNetworkLogo() {
        return this.primaryNetworkLogo;
    }

    public final int getProgress() {
        return this.progress;
    }

    public final String getRating() {
        return this.rating;
    }

    public final String getReleaseDate() {
        return this.releaseDate;
    }

    public final Long getRevenue() {
        return this.revenue;
    }

    public final boolean getShowPlaybackProgress() {
        return this.showPlaybackProgress;
    }

    public final int getSourceOrder() {
        return this.sourceOrder;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final String getTimeRemainingLabel() {
        return this.timeRemainingLabel;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getTmdbRating() {
        return this.tmdbRating;
    }

    public final Integer getTotalEpisodes() {
        return this.totalEpisodes;
    }

    public final Integer getTraktId() {
        return this.traktId;
    }

    public final Integer getWatchedEpisodes() {
        return this.watchedEpisodes;
    }

    public final String getYear() {
        return this.year;
    }

    public int hashCode() {
        int iC = c.c(c.c(c.c(c.c(this.id * 31, 31, this.title), 31, this.subtitle), 31, this.overview), 31, this.year);
        String str = this.releaseDate;
        int iC2 = c.c((this.mediaType.hashCode() + c.c(c.c(c.c(c.c((iC + (str == null ? 0 : str.hashCode())) * 31, 31, this.rating), 31, this.duration), 31, this.imdbRating), 31, this.tmdbRating)) * 31, 31, this.image);
        String str2 = this.backdrop;
        int iHashCode = (((((iC2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.progress) * 31) + (this.isWatched ? 1231 : 1237)) * 31;
        Integer num = this.traktId;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str3 = this.badge;
        int iE = d.e(this.genreIds, (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31, 31);
        String str4 = this.originalLanguage;
        int iHashCode3 = (iE + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.primaryNetworkLogo;
        int iHashCode4 = (((iHashCode3 + (str5 == null ? 0 : str5.hashCode())) * 31) + (this.isOngoing ? 1231 : 1237)) * 31;
        Integer num2 = this.totalEpisodes;
        int iHashCode5 = (iHashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.watchedEpisodes;
        int iHashCode6 = (iHashCode5 + (num3 == null ? 0 : num3.hashCode())) * 31;
        NextEpisode nextEpisode = this.nextEpisode;
        int iHashCode7 = (iHashCode6 + (nextEpisode == null ? 0 : nextEpisode.hashCode())) * 31;
        Long l10 = this.budget;
        int iHashCode8 = (iHashCode7 + (l10 == null ? 0 : l10.hashCode())) * 31;
        Long l11 = this.revenue;
        int iHashCode9 = (iHashCode8 + (l11 == null ? 0 : l11.hashCode())) * 31;
        String str6 = this.status;
        int iHashCode10 = (iHashCode9 + (str6 == null ? 0 : str6.hashCode())) * 31;
        CollectionGroupKind collectionGroupKind = this.collectionGroup;
        int iHashCode11 = (iHashCode10 + (collectionGroupKind == null ? 0 : collectionGroupKind.hashCode())) * 31;
        CollectionTileShape collectionTileShape = this.collectionTileShape;
        int iB = a0.c.b(this.popularity, c.c((((iHashCode11 + (collectionTileShape == null ? 0 : collectionTileShape.hashCode())) * 31) + (this.collectionHideTitle ? 1231 : 1237)) * 31, 31, this.character), 31);
        long j10 = this.addedAt;
        int i10 = (((((iB + ((int) (j10 ^ (j10 >>> 32)))) * 31) + this.sourceOrder) * 31) + (this.isPlaceholder ? 1231 : 1237)) * 31;
        String str7 = this.timeRemainingLabel;
        int iHashCode12 = (((((i10 + (str7 == null ? 0 : str7.hashCode())) * 31) + (this.showPlaybackProgress ? 1231 : 1237)) * 31) + (this.isHomeServer ? 1231 : 1237)) * 31;
        String str8 = this.homeServerItemId;
        int iHashCode13 = (iHashCode12 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.homeServerSourceRef;
        int iHashCode14 = (iHashCode13 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.homeServerProvider;
        int iHashCode15 = (iHashCode14 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.homeServerImdbId;
        return iHashCode15 + (str11 != null ? str11.hashCode() : 0);
    }

    public final boolean isHomeServer() {
        return this.isHomeServer;
    }

    public final boolean isOngoing() {
        return this.isOngoing;
    }

    public final boolean isPlaceholder() {
        return this.isPlaceholder;
    }

    public final boolean isWatched() {
        return this.isWatched;
    }

    public String toString() {
        int i10 = this.id;
        String str = this.title;
        String str2 = this.subtitle;
        String str3 = this.overview;
        String str4 = this.year;
        String str5 = this.releaseDate;
        String str6 = this.rating;
        String str7 = this.duration;
        String str8 = this.imdbRating;
        String str9 = this.tmdbRating;
        MediaType mediaType = this.mediaType;
        String str10 = this.image;
        String str11 = this.backdrop;
        int i11 = this.progress;
        boolean z = this.isWatched;
        Integer num = this.traktId;
        String str12 = this.badge;
        List<Integer> list = this.genreIds;
        String str13 = this.originalLanguage;
        String str14 = this.primaryNetworkLogo;
        boolean z5 = this.isOngoing;
        Integer num2 = this.totalEpisodes;
        Integer num3 = this.watchedEpisodes;
        NextEpisode nextEpisode = this.nextEpisode;
        Long l10 = this.budget;
        Long l11 = this.revenue;
        String str15 = this.status;
        CollectionGroupKind collectionGroupKind = this.collectionGroup;
        CollectionTileShape collectionTileShape = this.collectionTileShape;
        boolean z10 = this.collectionHideTitle;
        String str16 = this.character;
        float f10 = this.popularity;
        long j10 = this.addedAt;
        int i12 = this.sourceOrder;
        boolean z11 = this.isPlaceholder;
        String str17 = this.timeRemainingLabel;
        boolean z12 = this.showPlaybackProgress;
        boolean z13 = this.isHomeServer;
        String str18 = this.homeServerItemId;
        String str19 = this.homeServerSourceRef;
        String str20 = this.homeServerProvider;
        String str21 = this.homeServerImdbId;
        StringBuilder sbO = a2.o("MediaItem(id=", i10, ", title=", str, ", subtitle=");
        a.i(sbO, str2, ", overview=", str3, ", year=");
        a.i(sbO, str4, ", releaseDate=", str5, ", rating=");
        a.i(sbO, str6, ", duration=", str7, ", imdbRating=");
        a.i(sbO, str8, ", tmdbRating=", str9, ", mediaType=");
        sbO.append(mediaType);
        sbO.append(", image=");
        sbO.append(str10);
        sbO.append(", backdrop=");
        d.A(sbO, str11, ", progress=", i11, ", isWatched=");
        sbO.append(z);
        sbO.append(", traktId=");
        sbO.append(num);
        sbO.append(", badge=");
        sbO.append(str12);
        sbO.append(", genreIds=");
        sbO.append(list);
        sbO.append(", originalLanguage=");
        a.i(sbO, str13, ", primaryNetworkLogo=", str14, ", isOngoing=");
        sbO.append(z5);
        sbO.append(", totalEpisodes=");
        sbO.append(num2);
        sbO.append(", watchedEpisodes=");
        sbO.append(num3);
        sbO.append(", nextEpisode=");
        sbO.append(nextEpisode);
        sbO.append(", budget=");
        sbO.append(l10);
        sbO.append(", revenue=");
        sbO.append(l11);
        sbO.append(", status=");
        sbO.append(str15);
        sbO.append(", collectionGroup=");
        sbO.append(collectionGroupKind);
        sbO.append(", collectionTileShape=");
        sbO.append(collectionTileShape);
        sbO.append(", collectionHideTitle=");
        sbO.append(z10);
        sbO.append(", character=");
        sbO.append(str16);
        sbO.append(", popularity=");
        sbO.append(f10);
        sbO.append(", addedAt=");
        sbO.append(j10);
        sbO.append(", sourceOrder=");
        sbO.append(i12);
        sbO.append(", isPlaceholder=");
        sbO.append(z11);
        sbO.append(", timeRemainingLabel=");
        sbO.append(str17);
        sbO.append(", showPlaybackProgress=");
        sbO.append(z12);
        sbO.append(", isHomeServer=");
        sbO.append(z13);
        a.i(sbO, ", homeServerItemId=", str18, ", homeServerSourceRef=", str19);
        a.i(sbO, ", homeServerProvider=", str20, ", homeServerImdbId=", str21);
        sbO.append(")");
        return sbO.toString();
    }

    public /* synthetic */ MediaItem(int i10, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, MediaType mediaType, String str10, String str11, int i11, boolean z, Integer num, String str12, List list, String str13, String str14, boolean z5, Integer num2, Integer num3, NextEpisode nextEpisode, Long l10, Long l11, String str15, CollectionGroupKind collectionGroupKind, CollectionTileShape collectionTileShape, boolean z10, String str16, float f10, long j10, int i12, boolean z11, String str17, boolean z12, boolean z13, String str18, String str19, String str20, String str21, int i13, int i14, h hVar) {
        this(i10, str, (i13 & 4) != 0 ? "" : str2, (i13 & 8) != 0 ? "" : str3, (i13 & 16) != 0 ? "" : str4, (i13 & 32) != 0 ? null : str5, (i13 & 64) != 0 ? "" : str6, (i13 & 128) != 0 ? "" : str7, (i13 & 256) != 0 ? "" : str8, (i13 & 512) != 0 ? "" : str9, (i13 & 1024) != 0 ? MediaType.MOVIE : mediaType, (i13 & 2048) != 0 ? "" : str10, (i13 & 4096) != 0 ? null : str11, (i13 & 8192) != 0 ? 0 : i11, (i13 & 16384) != 0 ? false : z, (i13 & 32768) != 0 ? null : num, (i13 & 65536) != 0 ? null : str12, (i13 & 131072) != 0 ? z.f19728i : list, (i13 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? null : str13, (i13 & 524288) != 0 ? null : str14, (i13 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? false : z5, (i13 & 2097152) != 0 ? null : num2, (i13 & 4194304) != 0 ? null : num3, (i13 & 8388608) != 0 ? null : nextEpisode, (i13 & 16777216) != 0 ? null : l10, (i13 & 33554432) != 0 ? null : l11, (i13 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? null : str15, (i13 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? null : collectionGroupKind, (i13 & 268435456) != 0 ? null : collectionTileShape, (i13 & C.BUFFER_FLAG_LAST_SAMPLE) != 0 ? false : z10, (i13 & 1073741824) == 0 ? str16 : "", (i13 & Integer.MIN_VALUE) != 0 ? 0.0f : f10, (i14 & 1) != 0 ? 0L : j10, (i14 & 2) != 0 ? Integer.MAX_VALUE : i12, (i14 & 4) != 0 ? false : z11, (i14 & 8) != 0 ? null : str17, (i14 & 16) != 0 ? true : z12, (i14 & 32) == 0 ? z13 : false, (i14 & 64) != 0 ? null : str18, (i14 & 128) != 0 ? null : str19, (i14 & 256) != 0 ? null : str20, (i14 & 512) != 0 ? null : str21);
    }
}
