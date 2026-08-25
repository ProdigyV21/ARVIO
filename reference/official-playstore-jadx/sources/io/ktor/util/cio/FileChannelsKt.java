package io.ktor.util.cio;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.media3.extractor.text.ttml.TtmlNode;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.CoroutinesKt;
import io.ktor.utils.io.ReaderScope;
import io.ktor.utils.io.WriterScope;
import io.ktor.utils.io.core.CloseableJVMKt;
import io.ktor.utils.io.jvm.nio.WritingKt;
import java.io.Closeable;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import k2.c;
import ka.j0;
import ka.k0;
import ka.l0;
import ka.n1;
import ka.x0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a/\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00012\b\b\u0002\u0010\u0003\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\b\u001a\u001b\u0010\n\u001a\u00020\t*\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Ljava/io/File;", "", TtmlNode.START, "endInclusive", "Ld7/j;", "coroutineContext", "Lio/ktor/utils/io/ByteReadChannel;", "readChannel", "(Ljava/io/File;JJLd7/j;)Lio/ktor/utils/io/ByteReadChannel;", "Lio/ktor/utils/io/ByteWriteChannel;", "writeChannel", "(Ljava/io/File;Ld7/j;)Lio/ktor/utils/io/ByteWriteChannel;", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FileChannelsKt {

    /* JADX INFO: renamed from: io.ktor.util.cio.FileChannelsKt$readChannel$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/WriterScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/WriterScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.util.cio.FileChannelsKt$readChannel$1", f = "FileChannels.kt", l = {44, ColorSpace.MaxId}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<WriterScope, d<? super t0>, Object> {
        final /* synthetic */ long $endInclusive;
        final /* synthetic */ long $fileLength;
        final /* synthetic */ long $start;
        final /* synthetic */ File $this_readChannel;
        int I$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(long j10, long j11, long j12, File file, d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$start = j10;
            this.$endInclusive = j11;
            this.$fileLength = j12;
            this.$this_readChannel = file;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$start, this.$endInclusive, this.$fileLength, this.$this_readChannel, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(WriterScope writerScope, d<? super t0> dVar) {
            return ((AnonymousClass1) create(writerScope, dVar)).invokeSuspend(t0.f22605a);
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0095, code lost:
        
            if (r13.writeWhile(r3, r12) == r6) goto L34;
         */
        /* JADX WARN: Type inference failed for: r0v0, types: [int, java.io.Closeable] */
        @Override // f7.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.lang.IllegalAccessException, java.io.IOException, java.lang.reflect.InvocationTargetException {
            /*
                Method dump skipped, instruction units count: 209
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.util.cio.FileChannelsKt.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: io.ktor.util.cio.FileChannelsKt$writeChannel$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lio/ktor/utils/io/ReaderScope;", "Lx6/t0;", "<anonymous>", "(Lio/ktor/utils/io/ReaderScope;)V"}, k = 3, mv = {1, 8, 0})
    @e(c = "io.ktor.util.cio.FileChannelsKt$writeChannel$1", f = "FileChannels.kt", l = {96}, m = "invokeSuspend")
    public static final class C19501 extends j implements p<ReaderScope, d<? super t0>, Object> {
        final /* synthetic */ File $this_writeChannel;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C19501(File file, d<? super C19501> dVar) {
            super(2, dVar);
            this.$this_writeChannel = file;
        }

        @Override // f7.a
        public final d<t0> create(Object obj, d<?> dVar) {
            C19501 c19501 = new C19501(this.$this_writeChannel, dVar);
            c19501.L$0 = obj;
            return c19501;
        }

        @Override // r7.p
        public final Object invoke(ReaderScope readerScope, d<? super t0> dVar) {
            return ((C19501) create(readerScope, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Throwable th;
            Closeable closeable;
            RandomAccessFile randomAccessFile;
            int i10 = this.label;
            if (i10 == 0) {
                c.G(obj);
                ReaderScope readerScope = (ReaderScope) this.L$0;
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(this.$this_writeChannel, "rw");
                try {
                    ByteReadChannel channel = readerScope.getChannel();
                    FileChannel channel2 = randomAccessFile2.getChannel();
                    this.L$0 = randomAccessFile2;
                    this.L$1 = randomAccessFile2;
                    this.I$0 = 0;
                    this.label = 1;
                    obj = WritingKt.copyTo$default(channel, channel2, 0L, this, 2, (Object) null);
                    a aVar = a.f15033i;
                    if (obj == aVar) {
                        return aVar;
                    }
                    randomAccessFile = randomAccessFile2;
                    closeable = randomAccessFile;
                } catch (Throwable th2) {
                    th = th2;
                    closeable = randomAccessFile2;
                    closeable.close();
                    throw th;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                randomAccessFile = (RandomAccessFile) this.L$1;
                closeable = (Closeable) this.L$0;
                try {
                    c.G(obj);
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        closeable.close();
                        throw th;
                    } catch (Throwable th4) {
                        CloseableJVMKt.addSuppressedInternal(th, th4);
                        throw th;
                    }
                }
            }
            randomAccessFile.setLength(((Number) obj).longValue());
            closeable.close();
            return t0.f22605a;
        }
    }

    public static final ByteReadChannel readChannel(File file, long j10, long j11, d7.j jVar) {
        return CoroutinesKt.writer((k0) l0.a(jVar), new j0("file-reader").plus(jVar), false, (p<? super WriterScope, ? super d<? super t0>, ? extends Object>) new AnonymousClass1(j10, j11, file.length(), file, null)).getChannel();
    }

    public static ByteReadChannel readChannel$default(File file, long j10, long j11, d7.j jVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = 0;
        }
        long j12 = j10;
        if ((i10 & 2) != 0) {
            j11 = -1;
        }
        long j13 = j11;
        if ((i10 & 4) != 0) {
            jVar = x0.f19655d;
        }
        return readChannel(file, j12, j13, jVar);
    }

    public static final ByteWriteChannel writeChannel(File file, d7.j jVar) {
        return CoroutinesKt.reader((k0) n1.f19624i, new j0("file-writer").plus(jVar), true, (p<? super ReaderScope, ? super d<? super t0>, ? extends Object>) new C19501(file, null)).getChannel();
    }

    public static ByteWriteChannel writeChannel$default(File file, d7.j jVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            jVar = x0.f19655d;
        }
        return writeChannel(file, jVar);
    }
}
