package androidx.media3.exoplayer.source;

import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.mp3.Mp3Extractor;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class BundledExtractorsAdapter implements ProgressiveMediaExtractor {
    private Extractor extractor;
    private ExtractorInput extractorInput;
    private final ExtractorsFactory extractorsFactory;

    public BundledExtractorsAdapter(ExtractorsFactory extractorsFactory) {
        this.extractorsFactory = extractorsFactory;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$init$0(Extractor extractor) {
        return extractor.getUnderlyingImplementation().getClass().getSimpleName();
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void disableSeekingOnMp3Streams() {
        Extractor extractor = this.extractor;
        if (extractor == null) {
            return;
        }
        Extractor underlyingImplementation = extractor.getUnderlyingImplementation();
        if (underlyingImplementation instanceof Mp3Extractor) {
            ((Mp3Extractor) underlyingImplementation).disableSeeking();
        }
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public long getCurrentInputPosition() {
        ExtractorInput extractorInput = this.extractorInput;
        if (extractorInput != null) {
            return extractorInput.getPosition();
        }
        return -1L;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004f  */
    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void init(androidx.media3.common.DataReader r8, android.net.Uri r9, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r10, long r11, long r13, androidx.media3.extractor.ExtractorOutput r15) throws java.io.IOException {
        /*
            r7 = this;
            androidx.media3.extractor.DefaultExtractorInput r1 = new androidx.media3.extractor.DefaultExtractorInput
            r2 = r8
            r3 = r11
            r5 = r13
            r1.<init>(r2, r3, r5)
            r7.extractorInput = r1
            androidx.media3.extractor.Extractor r8 = r7.extractor
            if (r8 == 0) goto Lf
            return
        Lf:
            androidx.media3.extractor.ExtractorsFactory r8 = r7.extractorsFactory
            androidx.media3.extractor.Extractor[] r8 = r8.createExtractors(r9, r10)
            int r10 = r8.length
            com.google.common.collect.e1 r10 = com.google.common.collect.h1.m(r10)
            int r11 = r8.length
            r12 = 0
            r13 = 1
            if (r11 != r13) goto L24
            r8 = r8[r12]
            r7.extractor = r8
            goto L7f
        L24:
            int r11 = r8.length
            r14 = r12
        L26:
            if (r14 >= r11) goto L7b
            r0 = r8[r14]
            boolean r2 = r0.sniff(r1)     // Catch: java.lang.Throwable -> L36 java.io.EOFException -> L6b
            if (r2 == 0) goto L39
            r7.extractor = r0     // Catch: java.lang.Throwable -> L36 java.io.EOFException -> L6b
            r1.resetPeekPosition()
            goto L7b
        L36:
            r0 = move-exception
            r8 = r0
            goto L57
        L39:
            java.util.List r0 = r0.getSniffFailureDetails()     // Catch: java.lang.Throwable -> L36 java.io.EOFException -> L6b
            r10.d(r0)     // Catch: java.lang.Throwable -> L36 java.io.EOFException -> L6b
            androidx.media3.extractor.Extractor r0 = r7.extractor
            if (r0 != 0) goto L4f
            long r5 = r1.getPosition()
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L4d
            goto L4f
        L4d:
            r0 = r12
            goto L50
        L4f:
            r0 = r13
        L50:
            ac.b.s(r0)
            r1.resetPeekPosition()
            goto L78
        L57:
            androidx.media3.extractor.Extractor r9 = r7.extractor
            if (r9 != 0) goto L63
            long r9 = r1.getPosition()
            int r9 = (r9 > r3 ? 1 : (r9 == r3 ? 0 : -1))
            if (r9 != 0) goto L64
        L63:
            r12 = r13
        L64:
            ac.b.s(r12)
            r1.resetPeekPosition()
            throw r8
        L6b:
            androidx.media3.extractor.Extractor r0 = r7.extractor
            if (r0 != 0) goto L4f
            long r5 = r1.getPosition()
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L4d
            goto L4f
        L78:
            int r14 = r14 + 1
            goto L26
        L7b:
            androidx.media3.extractor.Extractor r11 = r7.extractor
            if (r11 == 0) goto L85
        L7f:
            androidx.media3.extractor.Extractor r8 = r7.extractor
            r8.init(r15)
            return
        L85:
            androidx.media3.exoplayer.source.UnrecognizedInputFormatException r11 = new androidx.media3.exoplayer.source.UnrecognizedInputFormatException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "None of the available extractors ("
            r12.<init>(r13)
            com.google.common.base.o r13 = new com.google.common.base.o
            java.lang.String r14 = ", "
            r13.<init>(r14)
            com.google.common.collect.o3 r8 = com.google.common.collect.h1.p(r8)
            androidx.media3.exoplayer.source.k r14 = new androidx.media3.exoplayer.source.k
            r15 = 1
            r14.<init>(r15)
            java.util.AbstractList r8 = com.google.common.collect.a0.x(r8, r14)
            java.lang.String r8 = r13.b(r8)
            r12.append(r8)
            java.lang.String r8 = ") could read the stream."
            r12.append(r8)
            java.lang.String r8 = r12.toString()
            r9.getClass()
            com.google.common.collect.o3 r10 = r10.f()
            r11.<init>(r8, r9, r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.source.BundledExtractorsAdapter.init(androidx.media3.common.DataReader, android.net.Uri, java.util.Map, long, long, androidx.media3.extractor.ExtractorOutput):void");
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public int read(PositionHolder positionHolder) throws IOException {
        Extractor extractor = this.extractor;
        extractor.getClass();
        ExtractorInput extractorInput = this.extractorInput;
        extractorInput.getClass();
        return extractor.read(extractorInput, positionHolder);
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void release() {
        Extractor extractor = this.extractor;
        if (extractor != null) {
            extractor.release();
            this.extractor = null;
        }
        this.extractorInput = null;
    }

    @Override // androidx.media3.exoplayer.source.ProgressiveMediaExtractor
    public void seek(long j10, long j11) {
        Extractor extractor = this.extractor;
        extractor.getClass();
        extractor.seek(j10, j11);
    }
}
