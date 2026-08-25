package com.arflix.tv.data.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.model.IptvProgram;
import com.google.android.gms.cast.MediaTrack;
import com.google.common.util.concurrent.r0;
import io.ktor.http.LinkHeader;
import java.io.IOException;
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
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0017\b\u0001\u0018\u0000 >2\u00020\u0001:\u0002?>B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005JS\u0010\u0012\u001a\u00020\u0010*\u00020\u00062\u0006\u0010\b\u001a\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0018\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J/\u0010\u0017\u001a\u00020\u0010*\u00020\u00062\u0006\u0010\b\u001a\u00020\u00072\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00150\u0014H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J#\u0010\u001a\u001a\u00020\u0010*\u00020\u00062\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ'\u0010\u001f\u001a\u0004\u0018\u00010\u00152\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001c2\u0006\u0010\u001e\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u001f\u0010 J-\u0010$\u001a\u00020\u0010*\u00020\u00062\u0017\u0010#\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00100!¢\u0006\u0002\b\"H\u0082\b¢\u0006\u0004\b$\u0010%J\u0017\u0010'\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u0006H\u0016¢\u0006\u0004\b'\u0010(J'\u0010,\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00062\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)H\u0016¢\u0006\u0004\b,\u0010-J1\u0010.\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00072\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0019\u001a\u00020\u000b¢\u0006\u0004\b.\u0010/J1\u00100\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00072\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0019\u001a\u00020\u000b¢\u0006\u0004\b0\u0010/JM\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\b\u001a\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\b\b\u0002\u0010\u001e\u001a\u00020\u000b2\b\b\u0002\u00101\u001a\u00020\u000b2\b\b\u0002\u00102\u001a\u00020\u000b¢\u0006\u0004\b3\u00104JE\u00107\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u001c0\u00142\u0006\u0010\b\u001a\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u00105\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\u000b¢\u0006\u0004\b7\u00108J\u0015\u00109\u001a\u00020)2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b9\u0010:J\u0015\u0010;\u001a\u00020)2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b;\u0010:J\u0015\u0010<\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b<\u0010=¨\u0006@"}, d2 = {"Lcom/arflix/tv/data/repository/IptvEpgIndex;", "Landroid/database/sqlite/SQLiteOpenHelper;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/database/sqlite/SQLiteDatabase;", "", "sourceKey", "", "channelIds", "", "startBound", "endBound", "Lkotlin/Function2;", "Lcom/arflix/tv/data/model/IptvProgram;", "Lx6/t0;", "onProgram", "useQueryChunks", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/Set;JJLr7/p;)V", "", "Lcom/arflix/tv/data/model/IptvNowNext;", "nowNext", "insertNowNextRows", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;Ljava/util/Map;)V", "updatedAtMs", "upsertSource", "(Landroid/database/sqlite/SQLiteDatabase;Ljava/lang/String;J)V", "", "programs", "nowMs", "buildNowNext", "(Ljava/util/List;J)Lcom/arflix/tv/data/model/IptvNowNext;", "Lkotlin/Function1;", "Lx6/n;", "block", "runInTransaction", "(Landroid/database/sqlite/SQLiteDatabase;Lr7/l;)V", "db", "onCreate", "(Landroid/database/sqlite/SQLiteDatabase;)V", "", "oldVersion", "newVersion", "onUpgrade", "(Landroid/database/sqlite/SQLiteDatabase;II)V", "replaceAll", "(Ljava/lang/String;Ljava/util/Map;J)V", "replaceChannels", "pastWindowMs", "futureWindowMs", "loadNowNext", "(Ljava/lang/String;Ljava/util/Set;JJJ)Ljava/util/Map;", "startMs", "endMs", "loadWindow", "(Ljava/lang/String;Ljava/util/Set;JJ)Ljava/util/Map;", "countChannelsWithPrograms", "(Ljava/lang/String;)I", "countPrograms", "deleteSource", "(Ljava/lang/String;)V", "Companion", "ProgramDedupKey", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class IptvEpgIndex extends SQLiteOpenHelper {

    @Deprecated
    public static final String DATABASE_NAME = "arvio_iptv_epg_index.db";

    @Deprecated
    public static final int DATABASE_VERSION = 2;

    @Deprecated
    public static final long DEFAULT_FUTURE_WINDOW_MS = 172800000;

    @Deprecated
    public static final long DEFAULT_PAST_WINDOW_MS = 172800000;

    @Deprecated
    public static final int MAX_DESCRIPTION_CHARS = 200;

    @Deprecated
    public static final int MAX_RECENT_PROGRAMS = 48;

    @Deprecated
    public static final int MAX_SQL_ARGS = 900;

    @Deprecated
    public static final int MAX_UPCOMING_PROGRAMS = 48;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/arflix/tv/data/repository/IptvEpgIndex$Companion;", "", "<init>", "()V", "DATABASE_NAME", "", "DATABASE_VERSION", "", "MAX_SQL_ARGS", "MAX_DESCRIPTION_CHARS", "MAX_UPCOMING_PROGRAMS", "MAX_RECENT_PROGRAMS", "DEFAULT_PAST_WINDOW_MS", "", "DEFAULT_FUTURE_WINDOW_MS", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0096\u0082\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/data/repository/IptvEpgIndex$ProgramDedupKey;", "", TtmlNode.START, "", TtmlNode.END, LinkHeader.Parameters.Title, "", "<init>", "(JJLjava/lang/String;)V", "getStart", "()J", "getEnd", "getTitle", "()Ljava/lang/String;", "equals", "", "other", "hashCode", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class ProgramDedupKey {
        private final long end;
        private final long start;
        private final String title;

        public ProgramDedupKey(long j10, long j11, String str) {
            this.start = j10;
            this.end = j11;
            this.title = str;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ProgramDedupKey)) {
                return false;
            }
            ProgramDedupKey programDedupKey = (ProgramDedupKey) other;
            return this.start == programDedupKey.start && this.end == programDedupKey.end && kotlin.jvm.internal.p.a(this.title, programDedupKey.title);
        }

        public final long getEnd() {
            return this.end;
        }

        public final long getStart() {
            return this.start;
        }

        public final String getTitle() {
            return this.title;
        }

        public int hashCode() {
            long j10 = this.start;
            long j11 = this.end;
            return this.title.hashCode() + (((((int) (j10 ^ (j10 >>> 32))) * 31) + ((int) ((j11 >>> 32) ^ j11))) * 31);
        }
    }

    public IptvEpgIndex(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 2);
    }

    private final IptvNowNext buildNowNext(List<IptvProgram> programs, long nowMs) {
        List listSingletonList;
        Object objPrevious;
        if (!programs.isEmpty()) {
            ga.c cVar = new ga.c(ga.r.I(new ga.p(programs, 3), new h(1)), new h(2), 0);
            Comparator comparator = new Comparator() { // from class: com.arflix.tv.data.repository.IptvEpgIndex$buildNowNext$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    return r0.e(Long.valueOf(((IptvProgram) t2).getStartUtcMillis()), Long.valueOf(((IptvProgram) t10).getStartUtcMillis()));
                }
            };
            List listV = ga.r.V(cVar);
            kotlin.collections.w.X(comparator, listV);
            Iterator it = ((ArrayList) listV).iterator();
            if (it.hasNext()) {
                Object next = it.next();
                if (it.hasNext()) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(next);
                    while (it.hasNext()) {
                        arrayList.add(it.next());
                    }
                    listSingletonList = arrayList;
                } else {
                    listSingletonList = Collections.singletonList(next);
                }
            } else {
                listSingletonList = kotlin.collections.z.f19728i;
            }
            if (!listSingletonList.isEmpty()) {
                ListIterator listIterator = listSingletonList.listIterator(listSingletonList.size());
                while (true) {
                    if (!listIterator.hasPrevious()) {
                        objPrevious = null;
                        break;
                    }
                    objPrevious = listIterator.previous();
                    if (((IptvProgram) objPrevious).isLive(nowMs)) {
                        break;
                    }
                }
                IptvProgram iptvProgram = (IptvProgram) objPrevious;
                List listU = ga.r.U(ga.r.S(ga.r.I(new ga.p(listSingletonList, 3), new i(nowMs, 0)), 48));
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : listSingletonList) {
                    if (((IptvProgram) obj).getEndUtcMillis() <= nowMs) {
                        arrayList2.add(obj);
                    }
                }
                IptvNowNext iptvNowNext = new IptvNowNext(iptvProgram, (IptvProgram) kotlin.collections.x.p0(0, listU), (IptvProgram) kotlin.collections.x.p0(1, listU), listU, kotlin.collections.x.Y0(48, arrayList2));
                if (iptvNowNext.getNow() != null || iptvNowNext.getNext() != null || iptvNowNext.getLater() != null || !iptvNowNext.getUpcoming().isEmpty() || !iptvNowNext.getRecent().isEmpty()) {
                    return iptvNowNext;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean buildNowNext$lambda$0(IptvProgram iptvProgram) {
        return iptvProgram.getEndUtcMillis() > iptvProgram.getStartUtcMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String buildNowNext$lambda$1(IptvProgram iptvProgram) {
        long startUtcMillis = iptvProgram.getStartUtcMillis();
        long endUtcMillis = iptvProgram.getEndUtcMillis();
        String title = iptvProgram.getTitle();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(startUtcMillis);
        sb2.append("|");
        sb2.append(endUtcMillis);
        return a0.c.p(sb2, "|", title);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean buildNowNext$lambda$4(long j10, IptvProgram iptvProgram) {
        return iptvProgram.getStartUtcMillis() > j10;
    }

    private final void insertNowNextRows(SQLiteDatabase sQLiteDatabase, String str, Map<String, IptvNowNext> map) {
        SQLiteStatement sQLiteStatementCompileStatement = sQLiteDatabase.compileStatement("INSERT OR REPLACE INTO epg_programs\n(source_key, channel_id, start_ms, end_ms, title, description)\nVALUES (?, ?, ?, ?, ?, ?)");
        try {
            HashSet hashSet = new HashSet(128);
            for (Map.Entry<String, IptvNowNext> entry : map.entrySet()) {
                String key = entry.getKey();
                IptvNowNext value = entry.getValue();
                String string = kotlin.text.o.L0(key).toString();
                if (!kotlin.text.o.h0(string)) {
                    hashSet.clear();
                    IptvProgram now = value.getNow();
                    if (now != null) {
                        insertNowNextRows$lambda$0$insertProgram(hashSet, sQLiteStatementCompileStatement, str, string, now);
                    }
                    IptvProgram next = value.getNext();
                    if (next != null) {
                        insertNowNextRows$lambda$0$insertProgram(hashSet, sQLiteStatementCompileStatement, str, string, next);
                    }
                    IptvProgram later = value.getLater();
                    if (later != null) {
                        insertNowNextRows$lambda$0$insertProgram(hashSet, sQLiteStatementCompileStatement, str, string, later);
                    }
                    Iterator<T> it = value.getUpcoming().iterator();
                    while (it.hasNext()) {
                        insertNowNextRows$lambda$0$insertProgram(hashSet, sQLiteStatementCompileStatement, str, string, (IptvProgram) it.next());
                    }
                    Iterator<T> it2 = value.getRecent().iterator();
                    while (it2.hasNext()) {
                        insertNowNextRows$lambda$0$insertProgram(hashSet, sQLiteStatementCompileStatement, str, string, (IptvProgram) it2.next());
                    }
                }
            }
            sQLiteStatementCompileStatement.close();
        } catch (Throwable th) {
            sQLiteStatementCompileStatement.close();
            throw th;
        }
    }

    private static final void insertNowNextRows$lambda$0$insertProgram(HashSet<ProgramDedupKey> hashSet, SQLiteStatement sQLiteStatement, String str, String str2, IptvProgram iptvProgram) {
        String string;
        if (kotlin.text.o.h0(iptvProgram.getTitle()) || iptvProgram.getEndUtcMillis() <= iptvProgram.getStartUtcMillis()) {
            return;
        }
        String string2 = kotlin.text.o.L0(iptvProgram.getTitle()).toString();
        if (hashSet.add(new ProgramDedupKey(iptvProgram.getStartUtcMillis(), iptvProgram.getEndUtcMillis(), string2))) {
            String description = iptvProgram.getDescription();
            String strI0 = (description == null || (string = kotlin.text.o.L0(description).toString()) == null) ? null : kotlin.text.o.I0(200, string);
            sQLiteStatement.clearBindings();
            sQLiteStatement.bindString(1, str);
            sQLiteStatement.bindString(2, str2);
            sQLiteStatement.bindLong(3, iptvProgram.getStartUtcMillis());
            sQLiteStatement.bindLong(4, iptvProgram.getEndUtcMillis());
            sQLiteStatement.bindString(5, string2);
            if (strI0 == null || kotlin.text.o.h0(strI0)) {
                sQLiteStatement.bindNull(6);
            } else {
                sQLiteStatement.bindString(6, strI0);
            }
            sQLiteStatement.executeInsert();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Map loadNowNext$default(IptvEpgIndex iptvEpgIndex, String str, Set set, long j10, long j11, long j12, int i10, Object obj) {
        long j13;
        IptvEpgIndex iptvEpgIndex2;
        String str2;
        Set set2;
        if ((i10 & 4) != 0) {
            j10 = System.currentTimeMillis();
        }
        long j14 = j10;
        long j15 = (i10 & 8) != 0 ? 172800000L : j11;
        if ((i10 & 16) != 0) {
            j13 = 172800000;
            set2 = set;
            iptvEpgIndex2 = iptvEpgIndex;
            str2 = str;
        } else {
            j13 = j12;
            iptvEpgIndex2 = iptvEpgIndex;
            str2 = str;
            set2 = set;
        }
        return iptvEpgIndex2.loadNowNext(str2, set2, j14, j15, j13);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 loadNowNext$lambda$0(LinkedHashMap linkedHashMap, String str, IptvProgram iptvProgram) {
        Object objT = linkedHashMap.get(str);
        if (objT == null) {
            objT = androidx.compose.material3.d.t(linkedHashMap, str);
        }
        ((List) objT).add(iptvProgram);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 loadWindow$lambda$0(LinkedHashMap linkedHashMap, String str, IptvProgram iptvProgram) {
        Object objT = linkedHashMap.get(str);
        if (objT == null) {
            objT = androidx.compose.material3.d.t(linkedHashMap, str);
        }
        ((List) objT).add(iptvProgram);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean loadWindow$lambda$1$0$0(IptvProgram iptvProgram) {
        return iptvProgram.getEndUtcMillis() > iptvProgram.getStartUtcMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String loadWindow$lambda$1$0$1(IptvProgram iptvProgram) {
        long startUtcMillis = iptvProgram.getStartUtcMillis();
        long endUtcMillis = iptvProgram.getEndUtcMillis();
        String title = iptvProgram.getTitle();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(startUtcMillis);
        sb2.append("|");
        sb2.append(endUtcMillis);
        return a0.c.p(sb2, "|", title);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean replaceChannels$lambda$0$0(String str) {
        return !kotlin.text.o.h0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence replaceChannels$lambda$0$1$0(String str) {
        return "?";
    }

    private final void runInTransaction(SQLiteDatabase sQLiteDatabase, r7.l<? super SQLiteDatabase, t0> lVar) {
        sQLiteDatabase.beginTransaction();
        try {
            lVar.invoke(sQLiteDatabase);
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private final void upsertSource(SQLiteDatabase sQLiteDatabase, String str, long j10) {
        SQLiteStatement sQLiteStatementCompileStatement = sQLiteDatabase.compileStatement("INSERT OR REPLACE INTO epg_sources(source_key, updated_ms) VALUES (?, ?)");
        try {
            sQLiteStatementCompileStatement.bindString(1, str);
            sQLiteStatementCompileStatement.bindLong(2, j10);
            sQLiteStatementCompileStatement.executeInsert();
            xc.d.L(sQLiteStatementCompileStatement, null);
        } finally {
        }
    }

    private final void useQueryChunks(SQLiteDatabase sQLiteDatabase, String str, Set<String> set, long j10, long j11, r7.p<? super String, ? super IptvProgram, t0> pVar) throws IOException {
        int i10 = 3;
        ga.i iVarI = ga.r.I(new ga.p(set, i10), new h(i10));
        androidx.work.impl.t.g(897, 897);
        Iterator itC = androidx.work.impl.t.C(new ga.h(iVarI), 897, 897, true, false);
        while (itC.hasNext()) {
            List list = (List) itC.next();
            String strY = f4.f.y("\n                    SELECT channel_id, start_ms, end_ms, title, description\n                    FROM epg_programs\n                    WHERE source_key = ?\n                      AND channel_id IN (" + kotlin.collections.x.u0(list, ",", null, null, new h(4), 30) + ")\n                      AND end_ms > ?\n                      AND start_ms < ?\n                    ORDER BY channel_id, start_ms\n                ");
            z6.c cVarN = t7.a.n();
            cVarN.add(str);
            cVarN.addAll(list);
            cVarN.add(String.valueOf(j10));
            cVarN.add(String.valueOf(j11));
            Cursor cursorRawQuery = sQLiteDatabase.rawQuery(strY, (String[]) t7.a.e(cVarN).toArray(new String[0]));
            try {
                int columnIndexOrThrow = cursorRawQuery.getColumnIndexOrThrow("channel_id");
                int columnIndexOrThrow2 = cursorRawQuery.getColumnIndexOrThrow("start_ms");
                int columnIndexOrThrow3 = cursorRawQuery.getColumnIndexOrThrow("end_ms");
                int columnIndexOrThrow4 = cursorRawQuery.getColumnIndexOrThrow(LinkHeader.Parameters.Title);
                int columnIndexOrThrow5 = cursorRawQuery.getColumnIndexOrThrow(MediaTrack.ROLE_DESCRIPTION);
                while (cursorRawQuery.moveToNext()) {
                    String string = cursorRawQuery.getString(columnIndexOrThrow);
                    if (string == null) {
                        string = "";
                    }
                    long j12 = cursorRawQuery.getLong(columnIndexOrThrow2);
                    long j13 = cursorRawQuery.getLong(columnIndexOrThrow3);
                    String string2 = cursorRawQuery.getString(columnIndexOrThrow4);
                    String str2 = string2 == null ? "" : string2;
                    if (!kotlin.text.o.h0(string) && !kotlin.text.o.h0(str2)) {
                        if (j13 > j12) {
                            String string3 = cursorRawQuery.isNull(columnIndexOrThrow5) ? null : cursorRawQuery.getString(columnIndexOrThrow5);
                            pVar.invoke(string, new IptvProgram(str2, (string3 == null || kotlin.text.o.h0(string3)) ? null : string3, j12, j13, null, 16, null));
                        }
                    }
                }
                cursorRawQuery.close();
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean useQueryChunks$lambda$0(String str) {
        return !kotlin.text.o.h0(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence useQueryChunks$lambda$1$0(String str) {
        return "?";
    }

    public final int countChannelsWithPrograms(String sourceKey) {
        if (kotlin.text.o.h0(sourceKey)) {
            return 0;
        }
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT COUNT(DISTINCT channel_id) FROM epg_programs WHERE source_key = ?", new String[]{sourceKey});
        try {
            int i10 = cursorRawQuery.moveToFirst() ? cursorRawQuery.getInt(0) : 0;
            xc.d.L(cursorRawQuery, null);
            return i10;
        } finally {
        }
    }

    public final int countPrograms(String sourceKey) {
        if (kotlin.text.o.h0(sourceKey)) {
            return 0;
        }
        Cursor cursorRawQuery = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM epg_programs WHERE source_key = ?", new String[]{sourceKey});
        try {
            int i10 = cursorRawQuery.moveToFirst() ? cursorRawQuery.getInt(0) : 0;
            xc.d.L(cursorRawQuery, null);
            return i10;
        } finally {
        }
    }

    public final void deleteSource(String sourceKey) {
        if (kotlin.text.o.h0(sourceKey)) {
            return;
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            writableDatabase.delete("epg_programs", "source_key = ?", new String[]{sourceKey});
            writableDatabase.delete("epg_sources", "source_key = ?", new String[]{sourceKey});
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public final Map<String, IptvNowNext> loadNowNext(String sourceKey, Set<String> channelIds, long nowMs, long pastWindowMs, long futureWindowMs) throws IOException {
        if (!kotlin.text.o.h0(sourceKey) && !channelIds.isEmpty()) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            useQueryChunks(getReadableDatabase(), sourceKey, channelIds, nowMs - pastWindowMs, nowMs + futureWindowMs, new j(linkedHashMap, 0));
            if (!linkedHashMap.isEmpty()) {
                z6.f fVar = new z6.f();
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    String str = (String) entry.getKey();
                    IptvNowNext iptvNowNextBuildNowNext = buildNowNext((List) entry.getValue(), nowMs);
                    if (iptvNowNextBuildNowNext != null) {
                        fVar.put(str, iptvNowNextBuildNowNext);
                    }
                }
                return fVar.c();
            }
        }
        return kotlin.collections.a0.f19683i;
    }

    public final Map<String, List<IptvProgram>> loadWindow(String sourceKey, Set<String> channelIds, long startMs, long endMs) throws IOException {
        Collection collectionSingletonList;
        if (!kotlin.text.o.h0(sourceKey) && !channelIds.isEmpty() && endMs > startMs) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            useQueryChunks(getReadableDatabase(), sourceKey, channelIds, startMs, endMs, new j(linkedHashMap, 1));
            if (!linkedHashMap.isEmpty()) {
                z6.f fVar = new z6.f();
                for (Map.Entry entry : linkedHashMap.entrySet()) {
                    String str = (String) entry.getKey();
                    ga.c cVar = new ga.c(ga.r.I(new ga.p((List) entry.getValue(), 3), new h(5)), new f(29), 0);
                    Comparator comparator = new Comparator() { // from class: com.arflix.tv.data.repository.IptvEpgIndex$loadWindow$lambda$1$0$$inlined$sortedBy$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t2, T t10) {
                            return r0.e(Long.valueOf(((IptvProgram) t2).getStartUtcMillis()), Long.valueOf(((IptvProgram) t10).getStartUtcMillis()));
                        }
                    };
                    List listV = ga.r.V(cVar);
                    kotlin.collections.w.X(comparator, listV);
                    Iterator it = ((ArrayList) listV).iterator();
                    if (it.hasNext()) {
                        Object next = it.next();
                        if (it.hasNext()) {
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(next);
                            while (it.hasNext()) {
                                arrayList.add(it.next());
                            }
                            collectionSingletonList = arrayList;
                        } else {
                            collectionSingletonList = Collections.singletonList(next);
                        }
                    } else {
                        collectionSingletonList = kotlin.collections.z.f19728i;
                    }
                    if (!collectionSingletonList.isEmpty()) {
                        fVar.put(str, collectionSingletonList);
                    }
                }
                return fVar.c();
            }
        }
        return kotlin.collections.a0.f19683i;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db2) {
        db2.execSQL("CREATE TABLE epg_programs (\n    source_key TEXT NOT NULL,\n    channel_id TEXT NOT NULL,\n    start_ms INTEGER NOT NULL,\n    end_ms INTEGER NOT NULL,\n    title TEXT NOT NULL,\n    description TEXT,\n    PRIMARY KEY(source_key, channel_id, start_ms, end_ms, title)\n)");
        db2.execSQL("CREATE INDEX idx_epg_programs_window ON epg_programs(source_key, channel_id, start_ms, end_ms)");
        db2.execSQL("CREATE TABLE epg_sources (\n    source_key TEXT PRIMARY KEY NOT NULL,\n    updated_ms INTEGER NOT NULL\n)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {
        db2.execSQL("DROP TABLE IF EXISTS epg_programs");
        db2.execSQL("DROP TABLE IF EXISTS epg_sources");
        onCreate(db2);
    }

    public final void replaceAll(String sourceKey, Map<String, IptvNowNext> nowNext, long updatedAtMs) {
        if (kotlin.text.o.h0(sourceKey) || nowNext.isEmpty()) {
            return;
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            writableDatabase.delete("epg_programs", "source_key = ?", new String[]{sourceKey});
            insertNowNextRows(writableDatabase, sourceKey, nowNext);
            upsertSource(writableDatabase, sourceKey, updatedAtMs);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public final void replaceChannels(String sourceKey, Map<String, IptvNowNext> nowNext, long updatedAtMs) {
        if (kotlin.text.o.h0(sourceKey) || nowNext.isEmpty()) {
            return;
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            ga.i iVarI = ga.r.I(new ga.p(nowNext.keySet(), 3), new f(28));
            androidx.work.impl.t.g(899, 899);
            Iterator itC = androidx.work.impl.t.C(new ga.h(iVarI), 899, 899, true, false);
            while (itC.hasNext()) {
                List list = (List) itC.next();
                String strU0 = kotlin.collections.x.u0(list, ",", null, null, new h(0), 30);
                Object[] array = list.toArray(new String[0]);
                int length = array.length;
                Object[] objArrCopyOf = Arrays.copyOf(new String[]{sourceKey}, 1 + length);
                System.arraycopy(array, 0, objArrCopyOf, 1, length);
                writableDatabase.delete("epg_programs", "source_key = ? AND channel_id IN (" + strU0 + ")", (String[]) objArrCopyOf);
            }
            insertNowNextRows(writableDatabase, sourceKey, nowNext);
            upsertSource(writableDatabase, sourceKey, updatedAtMs);
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }
}
