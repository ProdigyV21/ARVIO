package com.arflix.tv.data.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.model.DrmInfo;
import com.arflix.tv.data.model.IptvChannel;
import com.google.gson.Gson;
import io.ktor.http.ContentDisposition;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0002\b\u0018\n\u0002\u0010\u001e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 J2\u00020\u0001:\u0002KJB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ)\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J'\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ+\u0010#\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u00112\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\n0\u001f2\u0006\u0010\"\u001a\u00020!¢\u0006\u0004\b#\u0010$J\u0015\u0010%\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0011¢\u0006\u0004\b%\u0010&J\u0015\u0010\"\u001a\u00020!2\u0006\u0010\u001e\u001a\u00020\u0011¢\u0006\u0004\b\"\u0010'J\u001b\u0010(\u001a\b\u0012\u0004\u0012\u00020\n0\u001f2\u0006\u0010\u001e\u001a\u00020\u0011¢\u0006\u0004\b(\u0010)J+\u0010,\u001a\b\u0012\u0004\u0012\u00020\n0\u001f2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u000f¢\u0006\u0004\b,\u0010-J5\u0010/\u001a\b\u0012\u0004\u0012\u00020\n0\u001f2\u0006\u0010\u001e\u001a\u00020\u00112\b\u0010.\u001a\u0004\u0018\u00010\u00112\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u000f¢\u0006\u0004\b/\u00100J?\u00102\u001a\b\u0012\u0004\u0012\u00020\n0\u001f2\u0006\u0010\u001e\u001a\u00020\u00112\b\u00101\u001a\u0004\u0018\u00010\u00112\b\u0010.\u001a\u0004\u0018\u00010\u00112\u0006\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020\u000f¢\u0006\u0004\b2\u00103J\u001f\u00104\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u00112\b\u0010.\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b4\u00105J)\u00106\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u00112\b\u00101\u001a\u0004\u0018\u00010\u00112\b\u0010.\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b6\u00107J'\u00109\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u00112\b\u0010.\u001a\u0004\u0018\u00010\u00112\u0006\u00108\u001a\u00020\u0011¢\u0006\u0004\b9\u00107J)\u0010<\u001a\b\u0012\u0004\u0012\u00020\n0\u001f2\u0006\u0010\u001e\u001a\u00020\u00112\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00110:¢\u0006\u0004\b<\u0010=J+\u0010?\u001a\b\u0012\u0004\u0012\u00020\n0\u001f2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010>\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u000f¢\u0006\u0004\b?\u0010@J'\u0010B\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000f0A0\u001f2\u0006\u0010\u001e\u001a\u00020\u0011¢\u0006\u0004\bB\u0010)J-\u0010D\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000f0C0\u001f2\u0006\u0010\u001e\u001a\u00020\u0011¢\u0006\u0004\bD\u0010)J\u0015\u0010E\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0011¢\u0006\u0004\bE\u0010FR\u0014\u0010H\u001a\u00020G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010I¨\u0006L"}, d2 = {"Lcom/arflix/tv/data/repository/IptvChannelStore;", "Landroid/database/sqlite/SQLiteOpenHelper;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/database/Cursor;", "cursor", "Lcom/arflix/tv/data/repository/IptvChannelStore$ColumnIndices;", "c", "Lcom/arflix/tv/data/model/IptvChannel;", "readChannel", "(Landroid/database/Cursor;Lcom/arflix/tv/data/repository/IptvChannelStore$ColumnIndices;)Lcom/arflix/tv/data/model/IptvChannel;", "Landroid/database/sqlite/SQLiteStatement;", "statement", "", "index", "", "value", "Lx6/t0;", "bindNullableString", "(Landroid/database/sqlite/SQLiteStatement;ILjava/lang/String;)V", "Landroid/database/sqlite/SQLiteDatabase;", "db", "onCreate", "(Landroid/database/sqlite/SQLiteDatabase;)V", "oldVersion", "newVersion", "onUpgrade", "(Landroid/database/sqlite/SQLiteDatabase;II)V", "sourceKey", "", "channels", "", "updatedAtMs", "replaceAll", "(Ljava/lang/String;Ljava/util/List;J)V", "count", "(Ljava/lang/String;)I", "(Ljava/lang/String;)J", "loadAll", "(Ljava/lang/String;)Ljava/util/List;", "offset", "limit", "window", "(Ljava/lang/String;II)Ljava/util/List;", "groupTitle", "windowForGroup", "(Ljava/lang/String;Ljava/lang/String;II)Ljava/util/List;", "playlistId", "windowForPlaylistGroup", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)Ljava/util/List;", "countForGroup", "(Ljava/lang/String;Ljava/lang/String;)I", "countForPlaylistGroup", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I", "channelId", "indexOfId", "", "ids", "getByIds", "(Ljava/lang/String;Ljava/util/Collection;)Ljava/util/List;", "query", "search", "(Ljava/lang/String;Ljava/lang/String;I)Ljava/util/List;", "Lx6/x;", "groupCounts", "Lx6/j0;", "playlistGroupCounts", "deleteSource", "(Ljava/lang/String;)V", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "Companion", "ColumnIndices", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class IptvChannelStore extends SQLiteOpenHelper {

    @Deprecated
    public static final String DATABASE_NAME = "arvio_iptv_channels.db";

    @Deprecated
    public static final int DATABASE_VERSION = 3;

    @Deprecated
    public static final int MAX_SQL_ARGS = 900;
    private final Gson gson;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b'\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u0011\u0010\u0010\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\tR\u0011\u0010\u0012\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\tR\u0011\u0010\u0014\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\tR\u0011\u0010\u0016\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\tR\u0011\u0010\u0018\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\tR\u0011\u0010\u001a\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\tR\u0011\u0010\u001c\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\tR\u0011\u0010\u001e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\tR\u0011\u0010 \u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\tR\u0011\u0010\"\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\tR\u0011\u0010$\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\tR\u0011\u0010&\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\tR\u0011\u0010(\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\tR\u0011\u0010*\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\tR\u0011\u0010,\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\t¨\u0006."}, d2 = {"Lcom/arflix/tv/data/repository/IptvChannelStore$ColumnIndices;", "", "cursor", "Landroid/database/Cursor;", "<init>", "(Landroid/database/Cursor;)V", TtmlNode.ATTR_ID, "", "getId", "()I", ContentDisposition.Parameters.Name, "getName", "streamUrl", "getStreamUrl", "group", "getGroup", "logo", "getLogo", "epgId", "getEpgId", "rawTitle", "getRawTitle", "xtreamStreamId", "getXtreamStreamId", "catchupDays", "getCatchupDays", "catchupType", "getCatchupType", "catchupSource", "getCatchupSource", "tvgName", "getTvgName", "providerNumber", "getProviderNumber", "requestHeaders", "getRequestHeaders", "language", "getLanguage", "country", "getCountry", "quality", "getQuality", "variantKey", "getVariantKey", "drm", "getDrm", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class ColumnIndices {
        private final int catchupDays;
        private final int catchupSource;
        private final int catchupType;
        private final int country;
        private final int drm;
        private final int epgId;
        private final int group;
        private final int id;
        private final int language;
        private final int logo;
        private final int name;
        private final int providerNumber;
        private final int quality;
        private final int rawTitle;
        private final int requestHeaders;
        private final int streamUrl;
        private final int tvgName;
        private final int variantKey;
        private final int xtreamStreamId;

        public ColumnIndices(Cursor cursor) {
            this.id = cursor.getColumnIndexOrThrow(TtmlNode.ATTR_ID);
            this.name = cursor.getColumnIndexOrThrow(ContentDisposition.Parameters.Name);
            this.streamUrl = cursor.getColumnIndexOrThrow("stream_url");
            this.group = cursor.getColumnIndexOrThrow("group_title");
            this.logo = cursor.getColumnIndexOrThrow("logo");
            this.epgId = cursor.getColumnIndexOrThrow("epg_id");
            this.rawTitle = cursor.getColumnIndexOrThrow("raw_title");
            this.xtreamStreamId = cursor.getColumnIndexOrThrow("xtream_stream_id");
            this.catchupDays = cursor.getColumnIndexOrThrow("catchup_days");
            this.catchupType = cursor.getColumnIndexOrThrow("catchup_type");
            this.catchupSource = cursor.getColumnIndexOrThrow("catchup_source");
            this.tvgName = cursor.getColumnIndexOrThrow("tvg_name");
            this.providerNumber = cursor.getColumnIndexOrThrow("provider_channel_number");
            this.requestHeaders = cursor.getColumnIndexOrThrow("request_headers_json");
            this.language = cursor.getColumnIndexOrThrow("language");
            this.country = cursor.getColumnIndexOrThrow("country");
            this.quality = cursor.getColumnIndexOrThrow("quality_label");
            this.variantKey = cursor.getColumnIndexOrThrow("variant_key");
            this.drm = cursor.getColumnIndexOrThrow("drm_json");
        }

        public final int getCatchupDays() {
            return this.catchupDays;
        }

        public final int getCatchupSource() {
            return this.catchupSource;
        }

        public final int getCatchupType() {
            return this.catchupType;
        }

        public final int getCountry() {
            return this.country;
        }

        public final int getDrm() {
            return this.drm;
        }

        public final int getEpgId() {
            return this.epgId;
        }

        public final int getGroup() {
            return this.group;
        }

        public final int getId() {
            return this.id;
        }

        public final int getLanguage() {
            return this.language;
        }

        public final int getLogo() {
            return this.logo;
        }

        public final int getName() {
            return this.name;
        }

        public final int getProviderNumber() {
            return this.providerNumber;
        }

        public final int getQuality() {
            return this.quality;
        }

        public final int getRawTitle() {
            return this.rawTitle;
        }

        public final int getRequestHeaders() {
            return this.requestHeaders;
        }

        public final int getStreamUrl() {
            return this.streamUrl;
        }

        public final int getTvgName() {
            return this.tvgName;
        }

        public final int getVariantKey() {
            return this.variantKey;
        }

        public final int getXtreamStreamId() {
            return this.xtreamStreamId;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/arflix/tv/data/repository/IptvChannelStore$Companion;", "", "<init>", "()V", "DATABASE_NAME", "", "DATABASE_VERSION", "", "MAX_SQL_ARGS", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        private Companion() {
        }
    }

    public IptvChannelStore(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 3);
        this.gson = new Gson();
    }

    private final void bindNullableString(SQLiteStatement statement, int index, String value) {
        if (value == null) {
            statement.bindNull(index);
        } else {
            statement.bindString(index, value);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getByIds$lambda$0(String str) {
        return !kotlin.text.o.h0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence getByIds$lambda$1$0(String str) {
        return "?";
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.arflix.tv.data.model.IptvChannel readChannel(android.database.Cursor r25, com.arflix.tv.data.repository.IptvChannelStore.ColumnIndices r26) {
        /*
            Method dump skipped, instruction units count: 504
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.IptvChannelStore.readChannel(android.database.Cursor, com.arflix.tv.data.repository.IptvChannelStore$ColumnIndices):com.arflix.tv.data.model.IptvChannel");
    }

    private static final List<IptvChannel> windowForPlaylistGroup$query(String str, String str2, IptvChannelStore iptvChannelStore, int i10, int i11, String str3, boolean z) throws IOException {
        boolean z5 = str == null || str.length() == 0;
        boolean z10 = str2 == null || kotlin.text.o.h0(str2);
        StringBuilder sb2 = new StringBuilder("SELECT * FROM channels WHERE source_key = ?");
        if (!z10) {
            sb2.append(" AND id LIKE ?");
        }
        if (!z5) {
            sb2.append(z ? " AND trim(group_title) = ?" : " AND group_title = ?");
        }
        sb2.append(" ORDER BY ord");
        if (i10 >= 0) {
            sb2.append(" LIMIT ");
            sb2.append(i10);
            sb2.append(" OFFSET ");
            if (i11 < 0) {
                i11 = 0;
            }
            sb2.append(i11);
        }
        String string = sb2.toString();
        z6.c cVarN = t7.a.n();
        cVarN.add(str3);
        if (!z10) {
            cVarN.add(str2 + ":%");
        }
        if (!z5) {
            if (z) {
                str = kotlin.text.o.L0(str).toString();
            }
            cVarN.add(str);
        }
        Cursor cursorRawQuery = iptvChannelStore.getReadableDatabase().rawQuery(string, (String[]) t7.a.e(cVarN).toArray(new String[0]));
        try {
            if (1 > i10 || i10 >= 100001) {
                i10 = cursorRawQuery.getCount();
            }
            ArrayList arrayList = new ArrayList(i10);
            ColumnIndices columnIndices = new ColumnIndices(cursorRawQuery);
            while (cursorRawQuery.moveToNext()) {
                arrayList.add(iptvChannelStore.readChannel(cursorRawQuery, columnIndices));
            }
            cursorRawQuery.close();
            return arrayList;
        } finally {
        }
    }

    public final int count(String sourceKey) {
        if (kotlin.text.o.h0(sourceKey)) {
            return 0;
        }
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT channel_count FROM channel_sources WHERE source_key = ?", new String[]{sourceKey});
        try {
            int i10 = cursorRawQuery.moveToFirst() ? cursorRawQuery.getInt(0) : 0;
            xc.d.L(cursorRawQuery, null);
            return i10;
        } finally {
        }
    }

    public final int countForGroup(String sourceKey, String groupTitle) {
        return countForPlaylistGroup(sourceKey, null, groupTitle);
    }

    public final int countForPlaylistGroup(String sourceKey, String playlistId, String groupTitle) throws IOException {
        if (kotlin.text.o.h0(sourceKey)) {
            return 0;
        }
        boolean z = true;
        boolean z5 = groupTitle == null || groupTitle.length() == 0;
        if (playlistId != null && !kotlin.text.o.h0(playlistId)) {
            z = false;
        }
        if (z5 && z) {
            return count(sourceKey);
        }
        StringBuilder sb2 = new StringBuilder("SELECT COUNT(*) FROM channels WHERE source_key = ?");
        if (!z) {
            sb2.append(" AND id LIKE ?");
        }
        if (!z5) {
            sb2.append(" AND group_title = ?");
        }
        String string = sb2.toString();
        z6.c cVarN = t7.a.n();
        cVarN.add(sourceKey);
        if (!z) {
            cVarN.add(playlistId + ":%");
        }
        if (!z5) {
            cVarN.add(groupTitle);
        }
        Cursor cursorRawQuery = getReadableDatabase().rawQuery(string, (String[]) t7.a.e(cVarN).toArray(new String[0]));
        try {
            int i10 = cursorRawQuery.moveToFirst() ? cursorRawQuery.getInt(0) : 0;
            cursorRawQuery.close();
            return i10;
        } finally {
        }
    }

    public final void deleteSource(String sourceKey) {
        if (kotlin.text.o.h0(sourceKey)) {
            return;
        }
        try {
            getWritableDatabase().beginTransaction();
            try {
                getWritableDatabase().delete("channels", "source_key = ?", new String[]{sourceKey});
                getWritableDatabase().delete("channel_sources", "source_key = ?", new String[]{sourceKey});
                getWritableDatabase().setTransactionSuccessful();
            } finally {
                getWritableDatabase().endTransaction();
            }
        } catch (Exception unused) {
        }
    }

    public final List<IptvChannel> getByIds(String sourceKey, Collection<String> ids) throws IOException {
        if (kotlin.text.o.h0(sourceKey) || ids.isEmpty()) {
            return kotlin.collections.z.f19728i;
        }
        ArrayList arrayList = new ArrayList(ids.size());
        ga.i iVarI = ga.r.I(new ga.p(ids, 3), new f(26));
        androidx.work.impl.t.g(899, 899);
        Iterator itC = androidx.work.impl.t.C(new ga.h(iVarI), 899, 899, true, false);
        while (itC.hasNext()) {
            List list = (List) itC.next();
            Cursor cursorRawQuery = getReadableDatabase().rawQuery(a0.c.l("SELECT * FROM channels WHERE source_key = ? AND id IN (", kotlin.collections.x.u0(list, ",", null, null, new f(27), 30), ") ORDER BY ord"), (String[]) kotlin.collections.x.I0(Collections.singletonList(sourceKey), list).toArray(new String[0]));
            try {
                ColumnIndices columnIndices = new ColumnIndices(cursorRawQuery);
                while (cursorRawQuery.moveToNext()) {
                    arrayList.add(readChannel(cursorRawQuery, columnIndices));
                }
                cursorRawQuery.close();
            } finally {
            }
        }
        return arrayList;
    }

    public final List<x6.x> groupCounts(String sourceKey) {
        if (kotlin.text.o.h0(sourceKey)) {
            return kotlin.collections.z.f19728i;
        }
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT group_title, COUNT(*), MIN(ord) AS first_ord FROM channels WHERE source_key = ? GROUP BY group_title ORDER BY first_ord", new String[]{sourceKey});
        try {
            ArrayList arrayList = new ArrayList(cursorRawQuery.getCount());
            while (cursorRawQuery.moveToNext()) {
                String string = cursorRawQuery.getString(0);
                if (string == null) {
                    string = "";
                }
                arrayList.add(new x6.x(string, Integer.valueOf(cursorRawQuery.getInt(1))));
            }
            xc.d.L(cursorRawQuery, null);
            return arrayList;
        } finally {
        }
    }

    public final int indexOfId(String sourceKey, String groupTitle, String channelId) {
        if (kotlin.text.o.h0(sourceKey) || kotlin.text.o.h0(channelId)) {
            return -1;
        }
        boolean z = groupTitle == null || groupTitle.length() == 0;
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT ord FROM channels WHERE source_key = ? AND id = ? LIMIT 1", new String[]{sourceKey, channelId});
        try {
            if (!cursorRawQuery.moveToFirst()) {
                xc.d.L(cursorRawQuery, null);
                return -1;
            }
            long j10 = cursorRawQuery.getLong(0);
            xc.d.L(cursorRawQuery, null);
            StringBuilder sb2 = new StringBuilder("SELECT COUNT(*) FROM channels WHERE source_key = ?");
            if (!z) {
                sb2.append(" AND group_title = ?");
            }
            sb2.append(" AND ord < ?");
            cursorRawQuery = getReadableDatabase().rawQuery(sb2.toString(), !z ? new String[]{sourceKey, groupTitle, String.valueOf(j10)} : new String[]{sourceKey, String.valueOf(j10)});
            try {
                int i10 = cursorRawQuery.moveToFirst() ? cursorRawQuery.getInt(0) : -1;
                xc.d.L(cursorRawQuery, null);
                return i10;
            } finally {
            }
        } finally {
            try {
                throw th;
            } finally {
            }
        }
    }

    public final List<IptvChannel> loadAll(String sourceKey) {
        return window(sourceKey, 0, -1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db2) {
        db2.execSQL("CREATE TABLE channels (\n    source_key TEXT NOT NULL,\n    ord INTEGER NOT NULL,\n    id TEXT NOT NULL,\n    name TEXT NOT NULL,\n    stream_url TEXT NOT NULL,\n    group_title TEXT NOT NULL,\n    logo TEXT,\n    epg_id TEXT,\n    raw_title TEXT,\n    xtream_stream_id INTEGER,\n    catchup_days INTEGER NOT NULL DEFAULT 0,\n    catchup_type TEXT,\n    catchup_source TEXT,\n    tvg_name TEXT,\n    provider_channel_number TEXT,\n    request_headers_json TEXT,\n    language TEXT,\n    country TEXT,\n    quality_label TEXT,\n    variant_key TEXT,\n    drm_json TEXT,\n    PRIMARY KEY(source_key, ord)\n)");
        db2.execSQL("CREATE INDEX idx_channels_group ON channels(source_key, group_title)");
        db2.execSQL("CREATE TABLE channel_sources (\n    source_key TEXT PRIMARY KEY NOT NULL,\n    updated_ms INTEGER NOT NULL,\n    channel_count INTEGER NOT NULL\n)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {
        db2.execSQL("DROP TABLE IF EXISTS channels");
        db2.execSQL("DROP TABLE IF EXISTS channel_sources");
        onCreate(db2);
    }

    public final List<x6.j0> playlistGroupCounts(String sourceKey) {
        if (kotlin.text.o.h0(sourceKey)) {
            return kotlin.collections.z.f19728i;
        }
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT\n    CASE WHEN instr(id, ':') > 0 THEN substr(id, 1, instr(id, ':') - 1) ELSE '' END AS playlist_id,\n    group_title,\n    COUNT(*),\n    MIN(ord) AS first_ord\nFROM channels\nWHERE source_key = ?\nGROUP BY playlist_id, group_title\nORDER BY first_ord", new String[]{sourceKey});
        try {
            ArrayList arrayList = new ArrayList(cursorRawQuery.getCount());
            while (cursorRawQuery.moveToNext()) {
                String string = cursorRawQuery.getString(0);
                String str = "";
                if (string == null) {
                    string = "";
                }
                String string2 = cursorRawQuery.getString(1);
                if (string2 != null) {
                    str = string2;
                }
                arrayList.add(new x6.j0(string, str, Integer.valueOf(cursorRawQuery.getInt(2))));
            }
            xc.d.L(cursorRawQuery, null);
            return arrayList;
        } finally {
        }
    }

    public final void replaceAll(String sourceKey, List<IptvChannel> channels, long updatedAtMs) {
        if (kotlin.text.o.h0(sourceKey)) {
            return;
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            writableDatabase.delete("channels", "source_key = ?", new String[]{sourceKey});
            if (!channels.isEmpty()) {
                SQLiteStatement sQLiteStatementCompileStatement = writableDatabase.compileStatement("INSERT OR REPLACE INTO channels\n(source_key, ord, id, name, stream_url, group_title, logo, epg_id, raw_title,\n xtream_stream_id, catchup_days, catchup_type, catchup_source, tvg_name,\n provider_channel_number, request_headers_json, language, country, quality_label,\n variant_key, drm_json)\nVALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                try {
                    int i10 = 0;
                    for (Object obj : channels) {
                        int i11 = i10 + 1;
                        if (i10 < 0) {
                            t7.a.Q();
                            throw null;
                        }
                        IptvChannel iptvChannel = (IptvChannel) obj;
                        sQLiteStatementCompileStatement.clearBindings();
                        sQLiteStatementCompileStatement.bindString(1, sourceKey);
                        sQLiteStatementCompileStatement.bindLong(2, i10);
                        sQLiteStatementCompileStatement.bindString(3, iptvChannel.getId());
                        sQLiteStatementCompileStatement.bindString(4, iptvChannel.getName());
                        sQLiteStatementCompileStatement.bindString(5, iptvChannel.getStreamUrl());
                        sQLiteStatementCompileStatement.bindString(6, iptvChannel.getGroup());
                        bindNullableString(sQLiteStatementCompileStatement, 7, iptvChannel.getLogo());
                        bindNullableString(sQLiteStatementCompileStatement, 8, iptvChannel.getEpgId());
                        bindNullableString(sQLiteStatementCompileStatement, 9, iptvChannel.getRawTitle());
                        if (iptvChannel.getXtreamStreamId() != null) {
                            sQLiteStatementCompileStatement.bindLong(10, iptvChannel.getXtreamStreamId().intValue());
                        } else {
                            sQLiteStatementCompileStatement.bindNull(10);
                        }
                        sQLiteStatementCompileStatement.bindLong(11, iptvChannel.getCatchupDays());
                        bindNullableString(sQLiteStatementCompileStatement, 12, iptvChannel.getCatchupType());
                        bindNullableString(sQLiteStatementCompileStatement, 13, iptvChannel.getCatchupSource());
                        bindNullableString(sQLiteStatementCompileStatement, 14, iptvChannel.getTvgName());
                        bindNullableString(sQLiteStatementCompileStatement, 15, iptvChannel.getProviderChannelNumber());
                        Map<String, String> requestHeaders = iptvChannel.getRequestHeaders();
                        if (requestHeaders.isEmpty()) {
                            requestHeaders = null;
                        }
                        bindNullableString(sQLiteStatementCompileStatement, 16, requestHeaders != null ? this.gson.toJson(requestHeaders) : null);
                        bindNullableString(sQLiteStatementCompileStatement, 17, iptvChannel.getLanguage());
                        bindNullableString(sQLiteStatementCompileStatement, 18, iptvChannel.getCountry());
                        bindNullableString(sQLiteStatementCompileStatement, 19, iptvChannel.getQualityLabel());
                        bindNullableString(sQLiteStatementCompileStatement, 20, iptvChannel.getVariantKey());
                        DrmInfo drmInfo = iptvChannel.getDrmInfo();
                        bindNullableString(sQLiteStatementCompileStatement, 21, drmInfo != null ? this.gson.toJson(drmInfo) : null);
                        sQLiteStatementCompileStatement.executeInsert();
                        i10 = i11;
                    }
                    sQLiteStatementCompileStatement.close();
                } catch (Throwable th) {
                    sQLiteStatementCompileStatement.close();
                    throw th;
                }
            }
            SQLiteStatement sQLiteStatementCompileStatement2 = writableDatabase.compileStatement("INSERT OR REPLACE INTO channel_sources(source_key, updated_ms, channel_count) VALUES (?,?,?)");
            try {
                sQLiteStatementCompileStatement2.bindString(1, sourceKey);
                sQLiteStatementCompileStatement2.bindLong(2, updatedAtMs);
                sQLiteStatementCompileStatement2.bindLong(3, channels.size());
                sQLiteStatementCompileStatement2.executeInsert();
                xc.d.L(sQLiteStatementCompileStatement2, null);
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
            } finally {
            }
        } catch (Throwable th2) {
            writableDatabase.endTransaction();
            throw th2;
        }
    }

    public final List<IptvChannel> search(String sourceKey, String query, int limit) throws IOException {
        if (kotlin.text.o.h0(sourceKey) || kotlin.text.o.h0(query)) {
            return kotlin.collections.z.f19728i;
        }
        String strL = a0.c.l("%", kotlin.text.u.O(kotlin.text.u.O(kotlin.text.o.L0(query).toString(), "%", "", false), "_", "", false), "%");
        SQLiteDatabase readableDatabase = getReadableDatabase();
        if (limit < 1) {
            limit = 1;
        }
        Cursor cursorRawQuery = readableDatabase.rawQuery("SELECT * FROM channels WHERE source_key = ? AND name LIKE ? ORDER BY ord LIMIT ?", new String[]{sourceKey, strL, String.valueOf(limit)});
        try {
            ArrayList arrayList = new ArrayList();
            ColumnIndices columnIndices = new ColumnIndices(cursorRawQuery);
            while (cursorRawQuery.moveToNext()) {
                arrayList.add(readChannel(cursorRawQuery, columnIndices));
            }
            cursorRawQuery.close();
            return arrayList;
        } finally {
        }
    }

    public final long updatedAtMs(String sourceKey) {
        if (kotlin.text.o.h0(sourceKey)) {
            return 0L;
        }
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT updated_ms FROM channel_sources WHERE source_key = ?", new String[]{sourceKey});
        try {
            long j10 = cursorRawQuery.moveToFirst() ? cursorRawQuery.getLong(0) : 0L;
            xc.d.L(cursorRawQuery, null);
            return j10;
        } finally {
        }
    }

    public final List<IptvChannel> window(String sourceKey, int offset, int limit) {
        if (kotlin.text.o.h0(sourceKey)) {
            return kotlin.collections.z.f19728i;
        }
        StringBuilder sb2 = new StringBuilder("SELECT * FROM channels WHERE source_key = ? ORDER BY ord");
        if (limit >= 0) {
            sb2.append(" LIMIT ");
            sb2.append(limit);
            sb2.append(" OFFSET ");
            if (offset < 0) {
                offset = 0;
            }
            sb2.append(offset);
        }
        Cursor cursorRawQuery = getReadableDatabase().rawQuery(sb2.toString(), new String[]{sourceKey});
        try {
            if (1 > limit || limit >= 100001) {
                limit = cursorRawQuery.getCount();
            }
            ArrayList arrayList = new ArrayList(limit);
            ColumnIndices columnIndices = new ColumnIndices(cursorRawQuery);
            while (cursorRawQuery.moveToNext()) {
                arrayList.add(readChannel(cursorRawQuery, columnIndices));
            }
            xc.d.L(cursorRawQuery, null);
            return arrayList;
        } finally {
        }
    }

    public final List<IptvChannel> windowForGroup(String sourceKey, String groupTitle, int offset, int limit) {
        return windowForPlaylistGroup(sourceKey, null, groupTitle, offset, limit);
    }

    public final List<IptvChannel> windowForPlaylistGroup(String sourceKey, String playlistId, String groupTitle, int offset, int limit) throws IOException {
        if (kotlin.text.o.h0(sourceKey)) {
            return kotlin.collections.z.f19728i;
        }
        List<IptvChannel> listWindowForPlaylistGroup$query = windowForPlaylistGroup$query(groupTitle, playlistId, this, limit, offset, sourceKey, false);
        return (!listWindowForPlaylistGroup$query.isEmpty() || groupTitle == null || kotlin.text.o.h0(groupTitle)) ? listWindowForPlaylistGroup$query : windowForPlaylistGroup$query(groupTitle, playlistId, this, limit, offset, sourceKey, true);
    }
}
