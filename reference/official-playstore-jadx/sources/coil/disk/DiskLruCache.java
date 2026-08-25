package coil.disk;

import android.content.FileSystems;
import com.google.common.util.concurrent.r0;
import d7.d;
import f7.e;
import f7.j;
import io.ktor.http.ContentDisposition;
import java.io.Closeable;
import java.io.EOFException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import k2.c;
import ka.k0;
import ka.l0;
import ka.m0;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import kotlin.text.m;
import kotlin.text.o;
import kotlin.text.u;
import x6.t0;
import xb.f;
import xb.f0;
import xb.h0;
import xb.i0;
import xb.k;
import xb.s;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0081\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\b\u0007*\u0001S\b\u0000\u0018\u0000 U2\u00060\u0001j\u0002`\u00022\u00020\u0003:\u0004UVWXB7\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u001e\u0010\u0017\u001a\b\u0018\u00010\u0016R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0014H\u0086\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u001b\u0010\u001a\u001a\b\u0018\u00010\u0019R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u001a\u0010\u001bJ\r\u0010\u001c\u001a\u00020\n¢\u0006\u0004\b\u001c\u0010\u001dJ\u0015\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\b\u001f\u0010 J\u000f\u0010!\u001a\u00020\u0011H\u0016¢\u0006\u0004\b!\u0010\u0013J\u000f\u0010\"\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\"\u0010\u0013J\r\u0010#\u001a\u00020\u0011¢\u0006\u0004\b#\u0010\u0013J\u000f\u0010$\u001a\u00020\u0011H\u0002¢\u0006\u0004\b$\u0010\u0013J\u000f\u0010&\u001a\u00020%H\u0002¢\u0006\u0004\b&\u0010'J\u0017\u0010)\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\u0014H\u0002¢\u0006\u0004\b)\u0010*J\u000f\u0010+\u001a\u00020\u0011H\u0002¢\u0006\u0004\b+\u0010\u0013J\u000f\u0010,\u001a\u00020\u0011H\u0002¢\u0006\u0004\b,\u0010\u0013J#\u0010/\u001a\u00020\u00112\n\u0010-\u001a\u00060\u0019R\u00020\u00002\u0006\u0010.\u001a\u00020\u001eH\u0002¢\u0006\u0004\b/\u00100J\u000f\u00101\u001a\u00020\u001eH\u0002¢\u0006\u0004\b1\u00102J\u001b\u00105\u001a\u00020\u001e2\n\u00104\u001a\u000603R\u00020\u0000H\u0002¢\u0006\u0004\b5\u00106J\u000f\u00107\u001a\u00020\u0011H\u0002¢\u0006\u0004\b7\u0010\u0013J\u000f\u00108\u001a\u00020\u0011H\u0002¢\u0006\u0004\b8\u0010\u0013J\u000f\u00109\u001a\u00020\u001eH\u0002¢\u0006\u0004\b9\u00102J\u000f\u0010:\u001a\u00020\u0011H\u0002¢\u0006\u0004\b:\u0010\u0013J\u000f\u0010;\u001a\u00020\u0011H\u0002¢\u0006\u0004\b;\u0010\u0013J\u0017\u0010<\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0014H\u0002¢\u0006\u0004\b<\u0010*R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010=R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010>R\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010?R\u0014\u0010\u000e\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010?R\u0014\u0010@\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b@\u0010=R\u0014\u0010A\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bA\u0010=R\u0014\u0010B\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010=R8\u0010E\u001a&\u0012\u0004\u0012\u00020\u0014\u0012\b\u0012\u000603R\u00020\u00000Cj\u0012\u0012\u0004\u0012\u00020\u0014\u0012\b\u0012\u000603R\u00020\u0000`D8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010FR\u0014\u0010H\u001a\u00020G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010IR\u0016\u0010\u001c\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010>R\u0016\u0010J\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010?R\u0018\u0010K\u001a\u0004\u0018\u00010%8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bK\u0010LR\u0016\u0010M\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u0010NR\u0016\u0010O\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bO\u0010NR\u0016\u0010P\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bP\u0010NR\u0016\u0010Q\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bQ\u0010NR\u0016\u0010R\u001a\u00020\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bR\u0010NR\u0014\u0010\u0005\u001a\u00020S8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010T¨\u0006Y"}, d2 = {"Lcoil/disk/DiskLruCache;", "Ljava/io/Closeable;", "Lokio/Closeable;", "Ljava/io/Flushable;", "Lxb/s;", "fileSystem", "Lxb/f0;", "directory", "Lka/f0;", "cleanupDispatcher", "", "maxSize", "", "appVersion", "valueCount", "<init>", "(Lxb/s;Lxb/f0;Lka/f0;JII)V", "Lx6/t0;", "initialize", "()V", "", "key", "Lcoil/disk/DiskLruCache$Snapshot;", "get", "(Ljava/lang/String;)Lcoil/disk/DiskLruCache$Snapshot;", "Lcoil/disk/DiskLruCache$Editor;", "edit", "(Ljava/lang/String;)Lcoil/disk/DiskLruCache$Editor;", ContentDisposition.Parameters.Size, "()J", "", "remove", "(Ljava/lang/String;)Z", "close", "flush", "evictAll", "readJournal", "Lxb/k;", "newJournalWriter", "()Lxb/k;", "line", "readJournalLine", "(Ljava/lang/String;)V", "processJournal", "writeJournal", "editor", "success", "completeEdit", "(Lcoil/disk/DiskLruCache$Editor;Z)V", "journalRewriteRequired", "()Z", "Lcoil/disk/DiskLruCache$Entry;", "entry", "removeEntry", "(Lcoil/disk/DiskLruCache$Entry;)Z", "checkNotClosed", "trimToSize", "removeOldestEntry", "delete", "launchCleanup", "validateKey", "Lxb/f0;", "J", "I", "journalFile", "journalFileTmp", "journalFileBackup", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "lruEntries", "Ljava/util/LinkedHashMap;", "Lka/k0;", "cleanupScope", "Lka/k0;", "operationsSinceRewrite", "journalWriter", "Lxb/k;", "hasJournalErrors", "Z", "initialized", "closed", "mostRecentTrimFailed", "mostRecentRebuildFailed", "coil/disk/DiskLruCache$fileSystem$1", "Lcoil/disk/DiskLruCache$fileSystem$1;", "Companion", "Editor", "Entry", "Snapshot", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DiskLruCache implements Closeable, Flushable {
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    public static final String JOURNAL_FILE = "journal";
    public static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    public static final String JOURNAL_FILE_TMP = "journal.tmp";
    public static final String MAGIC = "libcore.io.DiskLruCache";
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    public static final String VERSION = "1";
    private final int appVersion;
    private final k0 cleanupScope;
    private boolean closed;
    private final f0 directory;
    private final DiskLruCache$fileSystem$1 fileSystem;
    private boolean hasJournalErrors;
    private boolean initialized;
    private final f0 journalFile;
    private final f0 journalFileBackup;
    private final f0 journalFileTmp;
    private k journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries;
    private final long maxSize;
    private boolean mostRecentRebuildFailed;
    private boolean mostRecentTrimFailed;
    private int operationsSinceRewrite;
    private long size;
    private final int valueCount;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final m LEGAL_KEY_PATTERN = new m("[a-z0-9_-]{1,120}");

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0005\u001a\u00020\u00048\u0000X\u0081T¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u0012\u0004\b\u0007\u0010\u0003R\u001a\u0010\b\u001a\u00020\u00048\u0000X\u0081T¢\u0006\f\n\u0004\b\b\u0010\u0006\u0012\u0004\b\t\u0010\u0003R\u001a\u0010\n\u001a\u00020\u00048\u0000X\u0081T¢\u0006\f\n\u0004\b\n\u0010\u0006\u0012\u0004\b\u000b\u0010\u0003R\u001a\u0010\f\u001a\u00020\u00048\u0000X\u0081T¢\u0006\f\n\u0004\b\f\u0010\u0006\u0012\u0004\b\r\u0010\u0003R\u001a\u0010\u000e\u001a\u00020\u00048\u0000X\u0081T¢\u0006\f\n\u0004\b\u000e\u0010\u0006\u0012\u0004\b\u000f\u0010\u0003R\u0014\u0010\u0010\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0011\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0015\u0010\u0006R\u0014\u0010\u0016\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0016\u0010\u0006¨\u0006\u0017"}, d2 = {"Lcoil/disk/DiskLruCache$Companion;", "", "<init>", "()V", "", "JOURNAL_FILE", "Ljava/lang/String;", "getJOURNAL_FILE$coil_base_release$annotations", "JOURNAL_FILE_TMP", "getJOURNAL_FILE_TMP$coil_base_release$annotations", "JOURNAL_FILE_BACKUP", "getJOURNAL_FILE_BACKUP$coil_base_release$annotations", "MAGIC", "getMAGIC$coil_base_release$annotations", "VERSION", "getVERSION$coil_base_release$annotations", DiskLruCache.CLEAN, DiskLruCache.DIRTY, "Lkotlin/text/m;", "LEGAL_KEY_PATTERN", "Lkotlin/text/m;", DiskLruCache.READ, DiskLruCache.REMOVE, "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public static /* synthetic */ void getJOURNAL_FILE$coil_base_release$annotations() {
        }

        public static /* synthetic */ void getJOURNAL_FILE_BACKUP$coil_base_release$annotations() {
        }

        public static /* synthetic */ void getJOURNAL_FILE_TMP$coil_base_release$annotations() {
        }

        public static /* synthetic */ void getMAGIC$coil_base_release$annotations() {
        }

        public static /* synthetic */ void getVERSION$coil_base_release$annotations() {
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0018\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0004\u001a\u00060\u0002R\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\t¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\t¢\u0006\u0004\b\u0013\u0010\u0012J\u0013\u0010\u0015\u001a\b\u0018\u00010\u0014R\u00020\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\t¢\u0006\u0004\b\u0017\u0010\u0012R\u001b\u0010\u0004\u001a\u00060\u0002R\u00020\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001e\u001a\u00020\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!¨\u0006\""}, d2 = {"Lcoil/disk/DiskLruCache$Editor;", "", "Lcoil/disk/DiskLruCache$Entry;", "Lcoil/disk/DiskLruCache;", "entry", "<init>", "(Lcoil/disk/DiskLruCache;Lcoil/disk/DiskLruCache$Entry;)V", "", "success", "Lx6/t0;", "complete", "(Z)V", "", "index", "Lxb/f0;", "file", "(I)Lxb/f0;", "detach", "()V", "commit", "Lcoil/disk/DiskLruCache$Snapshot;", "commitAndGet", "()Lcoil/disk/DiskLruCache$Snapshot;", "abort", "Lcoil/disk/DiskLruCache$Entry;", "getEntry", "()Lcoil/disk/DiskLruCache$Entry;", "closed", "Z", "", "written", "[Z", "getWritten", "()[Z", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class Editor {
        private boolean closed;
        private final Entry entry;
        private final boolean[] written;

        public Editor(Entry entry) {
            this.entry = entry;
            this.written = new boolean[DiskLruCache.this.valueCount];
        }

        private final void complete(boolean success) {
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                try {
                    if (this.closed) {
                        throw new IllegalStateException("editor is closed");
                    }
                    if (p.a(this.entry.getCurrentEditor(), this)) {
                        diskLruCache.completeEdit(this, success);
                    }
                    this.closed = true;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public final void abort() {
            complete(false);
        }

        public final void commit() {
            complete(true);
        }

        public final Snapshot commitAndGet() {
            Snapshot snapshot;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                commit();
                snapshot = diskLruCache.get(this.entry.getKey());
            }
            return snapshot;
        }

        public final void detach() {
            if (p.a(this.entry.getCurrentEditor(), this)) {
                this.entry.setZombie(true);
            }
        }

        public final f0 file(int index) {
            f0 f0Var;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                if (this.closed) {
                    throw new IllegalStateException("editor is closed");
                }
                this.written[index] = true;
                f0 f0Var2 = this.entry.getDirtyFiles().get(index);
                FileSystems.createFile(diskLruCache.fileSystem, f0Var2);
                f0Var = f0Var2;
            }
            return f0Var;
        }

        public final Entry getEntry() {
            return this.entry;
        }

        public final boolean[] getWritten() {
            return this.written;
        }
    }

    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0016\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\t\u001a\u00020\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\r\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u0011\u001a\b\u0018\u00010\u000fR\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0017\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR'\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u001c0\u001bj\b\u0012\u0004\u0012\u00020\u001c`\u001d8\u0006¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R'\u0010\"\u001a\u0012\u0012\u0004\u0012\u00020\u001c0\u001bj\b\u0012\u0004\u0012\u00020\u001c`\u001d8\u0006¢\u0006\f\n\u0004\b\"\u0010\u001f\u001a\u0004\b#\u0010!R\"\u0010%\u001a\u00020$8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\"\u0010+\u001a\u00020$8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b+\u0010&\u001a\u0004\b,\u0010(\"\u0004\b-\u0010*R(\u0010/\u001a\b\u0018\u00010.R\u00020\u00108\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\"\u00106\u001a\u0002058\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;¨\u0006<"}, d2 = {"Lcoil/disk/DiskLruCache$Entry;", "", "", "key", "<init>", "(Lcoil/disk/DiskLruCache;Ljava/lang/String;)V", "", "strings", "Lx6/t0;", "setLengths", "(Ljava/util/List;)V", "Lxb/k;", "writer", "writeLengths", "(Lxb/k;)V", "Lcoil/disk/DiskLruCache$Snapshot;", "Lcoil/disk/DiskLruCache;", "snapshot", "()Lcoil/disk/DiskLruCache$Snapshot;", "Ljava/lang/String;", "getKey", "()Ljava/lang/String;", "", "lengths", "[J", "getLengths", "()[J", "Ljava/util/ArrayList;", "Lxb/f0;", "Lkotlin/collections/ArrayList;", "cleanFiles", "Ljava/util/ArrayList;", "getCleanFiles", "()Ljava/util/ArrayList;", "dirtyFiles", "getDirtyFiles", "", "readable", "Z", "getReadable", "()Z", "setReadable", "(Z)V", "zombie", "getZombie", "setZombie", "Lcoil/disk/DiskLruCache$Editor;", "currentEditor", "Lcoil/disk/DiskLruCache$Editor;", "getCurrentEditor", "()Lcoil/disk/DiskLruCache$Editor;", "setCurrentEditor", "(Lcoil/disk/DiskLruCache$Editor;)V", "", "lockingSnapshotCount", "I", "getLockingSnapshotCount", "()I", "setLockingSnapshotCount", "(I)V", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class Entry {
        private final ArrayList<f0> cleanFiles;
        private Editor currentEditor;
        private final ArrayList<f0> dirtyFiles;
        private final String key;
        private final long[] lengths;
        private int lockingSnapshotCount;
        private boolean readable;
        private boolean zombie;

        public Entry(String str) {
            this.key = str;
            this.lengths = new long[DiskLruCache.this.valueCount];
            this.cleanFiles = new ArrayList<>(DiskLruCache.this.valueCount);
            this.dirtyFiles = new ArrayList<>(DiskLruCache.this.valueCount);
            StringBuilder sb2 = new StringBuilder(str);
            sb2.append('.');
            int length = sb2.length();
            int i10 = DiskLruCache.this.valueCount;
            for (int i11 = 0; i11 < i10; i11++) {
                sb2.append(i11);
                this.cleanFiles.add(DiskLruCache.this.directory.i(sb2.toString()));
                sb2.append(".tmp");
                this.dirtyFiles.add(DiskLruCache.this.directory.i(sb2.toString()));
                sb2.setLength(length);
            }
        }

        public final ArrayList<f0> getCleanFiles() {
            return this.cleanFiles;
        }

        public final Editor getCurrentEditor() {
            return this.currentEditor;
        }

        public final ArrayList<f0> getDirtyFiles() {
            return this.dirtyFiles;
        }

        public final String getKey() {
            return this.key;
        }

        public final long[] getLengths() {
            return this.lengths;
        }

        public final int getLockingSnapshotCount() {
            return this.lockingSnapshotCount;
        }

        public final boolean getReadable() {
            return this.readable;
        }

        public final boolean getZombie() {
            return this.zombie;
        }

        public final void setCurrentEditor(Editor editor) {
            this.currentEditor = editor;
        }

        public final void setLengths(List<String> strings) throws IOException {
            if (strings.size() != DiskLruCache.this.valueCount) {
                throw new IOException("unexpected journal line: " + strings);
            }
            try {
                int size = strings.size();
                for (int i10 = 0; i10 < size; i10++) {
                    this.lengths[i10] = Long.parseLong(strings.get(i10));
                }
            } catch (NumberFormatException unused) {
                throw new IOException("unexpected journal line: " + strings);
            }
        }

        public final void setLockingSnapshotCount(int i10) {
            this.lockingSnapshotCount = i10;
        }

        public final void setReadable(boolean z) {
            this.readable = z;
        }

        public final void setZombie(boolean z) {
            this.zombie = z;
        }

        public final Snapshot snapshot() {
            if (!this.readable || this.currentEditor != null || this.zombie) {
                return null;
            }
            ArrayList<f0> arrayList = this.cleanFiles;
            DiskLruCache diskLruCache = DiskLruCache.this;
            int size = arrayList.size();
            for (int i10 = 0; i10 < size; i10++) {
                if (!diskLruCache.fileSystem.exists(arrayList.get(i10))) {
                    try {
                        diskLruCache.removeEntry(this);
                    } catch (IOException unused) {
                    }
                    return null;
                }
            }
            this.lockingSnapshotCount++;
            return DiskLruCache.this.new Snapshot(this);
        }

        public final void writeLengths(k writer) {
            for (long j10 : this.lengths) {
                writer.writeByte(32).Y(j10);
            }
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0013\u0012\n\u0010\u0005\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0011\u001a\b\u0018\u00010\u0010R\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u0012R\u001b\u0010\u0005\u001a\u00060\u0003R\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcoil/disk/DiskLruCache$Snapshot;", "Ljava/io/Closeable;", "Lokio/Closeable;", "Lcoil/disk/DiskLruCache$Entry;", "Lcoil/disk/DiskLruCache;", "entry", "<init>", "(Lcoil/disk/DiskLruCache;Lcoil/disk/DiskLruCache$Entry;)V", "", "index", "Lxb/f0;", "file", "(I)Lxb/f0;", "Lx6/t0;", "close", "()V", "Lcoil/disk/DiskLruCache$Editor;", "closeAndEdit", "()Lcoil/disk/DiskLruCache$Editor;", "Lcoil/disk/DiskLruCache$Entry;", "getEntry", "()Lcoil/disk/DiskLruCache$Entry;", "", "closed", "Z", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class Snapshot implements Closeable {
        private boolean closed;
        private final Entry entry;

        public Snapshot(Entry entry) {
            this.entry = entry;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                this.entry.setLockingSnapshotCount(r1.getLockingSnapshotCount() - 1);
                if (this.entry.getLockingSnapshotCount() == 0 && this.entry.getZombie()) {
                    diskLruCache.removeEntry(this.entry);
                }
            }
        }

        public final Editor closeAndEdit() {
            Editor editorEdit;
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                close();
                editorEdit = diskLruCache.edit(this.entry.getKey());
            }
            return editorEdit;
        }

        public final f0 file(int index) {
            if (this.closed) {
                throw new IllegalStateException("snapshot is closed");
            }
            return this.entry.getCleanFiles().get(index);
        }

        public final Entry getEntry() {
            return this.entry;
        }
    }

    /* JADX INFO: renamed from: coil.disk.DiskLruCache$launchCleanup$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 9, 0})
    @e(c = "coil.disk.DiskLruCache$launchCleanup$1", f = "DiskLruCache.kt", l = {}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements r7.p<k0, d<? super t0>, Object> {
        int label;

        public AnonymousClass1(d<? super AnonymousClass1> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            return DiskLruCache.this.new AnonymousClass1(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
            DiskLruCache diskLruCache = DiskLruCache.this;
            synchronized (diskLruCache) {
                if (!diskLruCache.initialized || diskLruCache.closed) {
                    return t0.f22605a;
                }
                try {
                    diskLruCache.trimToSize();
                } catch (IOException unused) {
                    diskLruCache.mostRecentTrimFailed = true;
                }
                try {
                    if (diskLruCache.journalRewriteRequired()) {
                        diskLruCache.writeJournal();
                    }
                } catch (IOException unused2) {
                    diskLruCache.mostRecentRebuildFailed = true;
                    diskLruCache.journalWriter = new h0(new f());
                }
                return t0.f22605a;
            }
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public DiskLruCache(s sVar, f0 f0Var, ka.f0 f0Var2, long j10, int i10, int i11) {
        this.directory = f0Var;
        this.maxSize = j10;
        this.appVersion = i10;
        this.valueCount = i11;
        if (j10 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i11 <= 0) {
            throw new IllegalArgumentException("valueCount <= 0");
        }
        this.journalFile = f0Var.i(JOURNAL_FILE);
        this.journalFileTmp = f0Var.i(JOURNAL_FILE_TMP);
        this.journalFileBackup = f0Var.i(JOURNAL_FILE_BACKUP);
        this.lruEntries = new LinkedHashMap<>(0, 0.75f, true);
        this.cleanupScope = l0.a(d7.h.a(m0.d(), f0Var2.limitedParallelism(1)));
        this.fileSystem = new DiskLruCache$fileSystem$1(sVar);
    }

    private final void checkNotClosed() {
        if (this.closed) {
            throw new IllegalStateException("cache is closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void completeEdit(Editor editor, boolean success) {
        Entry entry = editor.getEntry();
        if (!p.a(entry.getCurrentEditor(), editor)) {
            throw new IllegalStateException("Check failed.");
        }
        int i10 = 0;
        if (!success || entry.getZombie()) {
            int i11 = this.valueCount;
            while (i10 < i11) {
                this.fileSystem.delete(entry.getDirtyFiles().get(i10));
                i10++;
            }
        } else {
            int i12 = this.valueCount;
            for (int i13 = 0; i13 < i12; i13++) {
                if (editor.getWritten()[i13] && !this.fileSystem.exists(entry.getDirtyFiles().get(i13))) {
                    editor.abort();
                    return;
                }
            }
            int i14 = this.valueCount;
            while (i10 < i14) {
                f0 f0Var = entry.getDirtyFiles().get(i10);
                f0 f0Var2 = entry.getCleanFiles().get(i10);
                if (this.fileSystem.exists(f0Var)) {
                    this.fileSystem.atomicMove(f0Var, f0Var2);
                } else {
                    FileSystems.createFile(this.fileSystem, entry.getCleanFiles().get(i10));
                }
                long j10 = entry.getLengths()[i10];
                Long l10 = this.fileSystem.metadata(f0Var2).f22786d;
                long jLongValue = l10 != null ? l10.longValue() : 0L;
                entry.getLengths()[i10] = jLongValue;
                this.size = (this.size - j10) + jLongValue;
                i10++;
            }
        }
        entry.setCurrentEditor(null);
        if (entry.getZombie()) {
            removeEntry(entry);
            return;
        }
        this.operationsSinceRewrite++;
        k kVar = this.journalWriter;
        if (success || entry.getReadable()) {
            entry.setReadable(true);
            kVar.H(CLEAN);
            kVar.writeByte(32);
            kVar.H(entry.getKey());
            entry.writeLengths(kVar);
            kVar.writeByte(10);
        } else {
            this.lruEntries.remove(entry.getKey());
            kVar.H(REMOVE);
            kVar.writeByte(32);
            kVar.H(entry.getKey());
            kVar.writeByte(10);
        }
        kVar.flush();
        if (this.size > this.maxSize || journalRewriteRequired()) {
            launchCleanup();
        }
    }

    private final void delete() throws IOException {
        close();
        FileSystems.deleteContents(this.fileSystem, this.directory);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean journalRewriteRequired() {
        return this.operationsSinceRewrite >= 2000;
    }

    private final void launchCleanup() {
        m0.p(this.cleanupScope, null, 0, new AnonymousClass1(null), 3);
    }

    private final k newJournalWriter() {
        return new h0(new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile), new DiskLruCache$newJournalWriter$faultHidingSink$1(this)));
    }

    private final void processJournal() throws IOException {
        Iterator<Entry> it = this.lruEntries.values().iterator();
        long j10 = 0;
        while (it.hasNext()) {
            Entry next = it.next();
            int i10 = 0;
            if (next.getCurrentEditor() == null) {
                int i11 = this.valueCount;
                while (i10 < i11) {
                    j10 += next.getLengths()[i10];
                    i10++;
                }
            } else {
                next.setCurrentEditor(null);
                int i12 = this.valueCount;
                while (i10 < i12) {
                    this.fileSystem.delete(next.getCleanFiles().get(i10));
                    this.fileSystem.delete(next.getDirtyFiles().get(i10));
                    i10++;
                }
                it.remove();
            }
        }
        this.size = j10;
    }

    private final void readJournal() throws Throwable {
        i0 i0Var = new i0(this.fileSystem.source(this.journalFile));
        try {
            String strB = i0Var.B(Long.MAX_VALUE);
            String strB2 = i0Var.B(Long.MAX_VALUE);
            String strB3 = i0Var.B(Long.MAX_VALUE);
            String strB4 = i0Var.B(Long.MAX_VALUE);
            String strB5 = i0Var.B(Long.MAX_VALUE);
            if (!MAGIC.equals(strB) || !"1".equals(strB2) || !p.a(String.valueOf(this.appVersion), strB3) || !p.a(String.valueOf(this.valueCount), strB4) || strB5.length() > 0) {
                throw new IOException("unexpected journal header: [" + strB + ", " + strB2 + ", " + strB3 + ", " + strB4 + ", " + strB5 + ']');
            }
            int i10 = 0;
            while (true) {
                try {
                    readJournalLine(i0Var.B(Long.MAX_VALUE));
                    i10++;
                } catch (EOFException unused) {
                    this.operationsSinceRewrite = i10 - this.lruEntries.size();
                    if (i0Var.i()) {
                        this.journalWriter = newJournalWriter();
                    } else {
                        writeJournal();
                    }
                    try {
                        i0Var.close();
                        th = null;
                    } catch (Throwable th) {
                        th = th;
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            try {
                i0Var.close();
            } catch (Throwable th3) {
                r0.a(th, th3);
            }
        }
        if (th != null) {
            throw th;
        }
    }

    private final void readJournalLine(String line) throws IOException {
        String strSubstring;
        int iE0 = o.e0(line, ' ', 0, false, 6);
        if (iE0 == -1) {
            throw new IOException(androidx.compose.material3.d.C("unexpected journal line: ", line));
        }
        int i10 = iE0 + 1;
        int iE02 = o.e0(line, ' ', i10, false, 4);
        if (iE02 == -1) {
            strSubstring = line.substring(i10);
            if (iE0 == 6 && u.P(line, REMOVE, false)) {
                this.lruEntries.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = line.substring(i10, iE02);
        }
        LinkedHashMap<String, Entry> linkedHashMap = this.lruEntries;
        Entry entry = linkedHashMap.get(strSubstring);
        if (entry == null) {
            entry = new Entry(strSubstring);
            linkedHashMap.put(strSubstring, entry);
        }
        Entry entry2 = entry;
        if (iE02 != -1 && iE0 == 5 && u.P(line, CLEAN, false)) {
            List<String> listX0 = o.x0(line.substring(iE02 + 1), new char[]{' '}, 6);
            entry2.setReadable(true);
            entry2.setCurrentEditor(null);
            entry2.setLengths(listX0);
            return;
        }
        if (iE02 == -1 && iE0 == 5 && u.P(line, DIRTY, false)) {
            entry2.setCurrentEditor(new Editor(entry2));
        } else if (iE02 != -1 || iE0 != 4 || !u.P(line, READ, false)) {
            throw new IOException(androidx.compose.material3.d.C("unexpected journal line: ", line));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean removeEntry(Entry entry) throws IOException {
        k kVar;
        if (entry.getLockingSnapshotCount() > 0 && (kVar = this.journalWriter) != null) {
            kVar.H(DIRTY);
            kVar.writeByte(32);
            kVar.H(entry.getKey());
            kVar.writeByte(10);
            kVar.flush();
        }
        if (entry.getLockingSnapshotCount() > 0 || entry.getCurrentEditor() != null) {
            entry.setZombie(true);
            return true;
        }
        int i10 = this.valueCount;
        for (int i11 = 0; i11 < i10; i11++) {
            this.fileSystem.delete(entry.getCleanFiles().get(i11));
            this.size -= entry.getLengths()[i11];
            entry.getLengths()[i11] = 0;
        }
        this.operationsSinceRewrite++;
        k kVar2 = this.journalWriter;
        if (kVar2 != null) {
            kVar2.H(REMOVE);
            kVar2.writeByte(32);
            kVar2.H(entry.getKey());
            kVar2.writeByte(10);
        }
        this.lruEntries.remove(entry.getKey());
        if (journalRewriteRequired()) {
            launchCleanup();
        }
        return true;
    }

    private final boolean removeOldestEntry() throws IOException {
        for (Entry entry : this.lruEntries.values()) {
            if (!entry.getZombie()) {
                removeEntry(entry);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void trimToSize() {
        while (this.size > this.maxSize) {
            if (!removeOldestEntry()) {
                return;
            }
        }
        this.mostRecentTrimFailed = false;
    }

    private final void validateKey(String key) {
        if (!LEGAL_KEY_PATTERN.e(key)) {
            throw new IllegalArgumentException(androidx.compose.material3.d.i('\"', "keys must match regex [a-z0-9_-]{1,120}: \"", key).toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void writeJournal() {
        try {
            k kVar = this.journalWriter;
            if (kVar != null) {
                kVar.close();
            }
            h0 h0Var = new h0(this.fileSystem.sink(this.journalFileTmp, false));
            try {
                h0Var.H(MAGIC);
                h0Var.writeByte(10);
                h0Var.H("1");
                h0Var.writeByte(10);
                h0Var.Y(this.appVersion);
                h0Var.writeByte(10);
                h0Var.Y(this.valueCount);
                h0Var.writeByte(10);
                h0Var.writeByte(10);
                for (Entry entry : this.lruEntries.values()) {
                    if (entry.getCurrentEditor() != null) {
                        h0Var.H(DIRTY);
                        h0Var.writeByte(32);
                        h0Var.H(entry.getKey());
                        h0Var.writeByte(10);
                    } else {
                        h0Var.H(CLEAN);
                        h0Var.writeByte(32);
                        h0Var.H(entry.getKey());
                        entry.writeLengths(h0Var);
                        h0Var.writeByte(10);
                    }
                }
                try {
                    h0Var.close();
                    th = null;
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    h0Var.close();
                } catch (Throwable th3) {
                    r0.a(th, th3);
                }
            }
            if (th != null) {
                throw th;
            }
            if (this.fileSystem.exists(this.journalFile)) {
                this.fileSystem.atomicMove(this.journalFile, this.journalFileBackup);
                this.fileSystem.atomicMove(this.journalFileTmp, this.journalFile);
                this.fileSystem.delete(this.journalFileBackup);
            } else {
                this.fileSystem.atomicMove(this.journalFileTmp, this.journalFile);
            }
            this.journalWriter = newJournalWriter();
            this.operationsSinceRewrite = 0;
            this.hasJournalErrors = false;
            this.mostRecentRebuildFailed = false;
        } catch (Throwable th4) {
            throw th4;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        try {
            if (this.initialized && !this.closed) {
                for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[0])) {
                    Editor currentEditor = entry.getCurrentEditor();
                    if (currentEditor != null) {
                        currentEditor.detach();
                    }
                }
                trimToSize();
                l0.b(this.cleanupScope, null);
                this.journalWriter.close();
                this.journalWriter = null;
                this.closed = true;
                return;
            }
            this.closed = true;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized Editor edit(String key) {
        checkNotClosed();
        validateKey(key);
        initialize();
        Entry entry = this.lruEntries.get(key);
        if ((entry != null ? entry.getCurrentEditor() : null) != null) {
            return null;
        }
        if (entry != null && entry.getLockingSnapshotCount() != 0) {
            return null;
        }
        if (!this.mostRecentTrimFailed && !this.mostRecentRebuildFailed) {
            k kVar = this.journalWriter;
            kVar.H(DIRTY);
            kVar.writeByte(32);
            kVar.H(key);
            kVar.writeByte(10);
            kVar.flush();
            if (this.hasJournalErrors) {
                return null;
            }
            if (entry == null) {
                entry = new Entry(key);
                this.lruEntries.put(key, entry);
            }
            Editor editor = new Editor(entry);
            entry.setCurrentEditor(editor);
            return editor;
        }
        launchCleanup();
        return null;
    }

    public final synchronized void evictAll() {
        try {
            initialize();
            for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[0])) {
                removeEntry(entry);
            }
            this.mostRecentTrimFailed = false;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.Flushable
    public synchronized void flush() {
        if (this.initialized) {
            checkNotClosed();
            trimToSize();
            this.journalWriter.flush();
        }
    }

    public final synchronized Snapshot get(String key) {
        Snapshot snapshot;
        checkNotClosed();
        validateKey(key);
        initialize();
        Entry entry = this.lruEntries.get(key);
        if (entry != null && (snapshot = entry.snapshot()) != null) {
            this.operationsSinceRewrite++;
            k kVar = this.journalWriter;
            kVar.H(READ);
            kVar.writeByte(32);
            kVar.H(key);
            kVar.writeByte(10);
            if (journalRewriteRequired()) {
                launchCleanup();
            }
            return snapshot;
        }
        return null;
    }

    public final synchronized void initialize() {
        try {
            if (this.initialized) {
                return;
            }
            this.fileSystem.delete(this.journalFileTmp);
            if (this.fileSystem.exists(this.journalFileBackup)) {
                if (this.fileSystem.exists(this.journalFile)) {
                    this.fileSystem.delete(this.journalFileBackup);
                } else {
                    this.fileSystem.atomicMove(this.journalFileBackup, this.journalFile);
                }
            }
            if (this.fileSystem.exists(this.journalFile)) {
                try {
                    readJournal();
                    processJournal();
                    this.initialized = true;
                    return;
                } catch (IOException unused) {
                    try {
                        delete();
                        this.closed = false;
                        writeJournal();
                        this.initialized = true;
                    } catch (Throwable th) {
                        this.closed = false;
                        throw th;
                    }
                }
            }
            writeJournal();
            this.initialized = true;
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public final synchronized boolean remove(String key) {
        checkNotClosed();
        validateKey(key);
        initialize();
        Entry entry = this.lruEntries.get(key);
        if (entry == null) {
            return false;
        }
        boolean zRemoveEntry = removeEntry(entry);
        if (zRemoveEntry && this.size <= this.maxSize) {
            this.mostRecentTrimFailed = false;
        }
        return zRemoveEntry;
    }

    public final synchronized long size() {
        initialize();
        return this.size;
    }
}
