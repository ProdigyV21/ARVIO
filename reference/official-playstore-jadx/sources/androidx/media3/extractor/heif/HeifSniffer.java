package androidx.media3.extractor.heif;

/* JADX INFO: loaded from: classes3.dex */
final class HeifSniffer {
    private HeifSniffer() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x006b, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean sniff(androidx.media3.extractor.ExtractorInput r12, boolean r13) throws java.io.IOException {
        /*
            androidx.media3.common.util.ParsableByteArray r0 = new androidx.media3.common.util.ParsableByteArray
            r1 = 16
            r0.<init>(r1)
            r2 = 1
            r3 = r2
        L9:
            r4 = 8
            r0.reset(r4)
            byte[] r5 = r0.getData()
            r6 = 0
            boolean r5 = r12.peekFully(r5, r6, r4, r2)
            if (r5 != 0) goto L1a
            return r6
        L1a:
            long r7 = r0.readUnsignedInt()
            int r5 = r0.readInt()
            r9 = 1
            int r9 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r9 != 0) goto L39
            byte[] r7 = r0.getData()
            boolean r7 = r12.peekFully(r7, r4, r4, r2)
            if (r7 != 0) goto L33
            return r6
        L33:
            long r7 = r0.readUnsignedLongToLong()
            r9 = r1
            goto L3a
        L39:
            r9 = r4
        L3a:
            long r9 = (long) r9
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 >= 0) goto L40
            return r6
        L40:
            long r7 = r7 - r9
            int r7 = (int) r7
            if (r3 == 0) goto L6c
            r3 = 1718909296(0x66747970, float:2.8862439E23)
            if (r5 != r3) goto L6b
            if (r7 >= r4) goto L4c
            goto L6b
        L4c:
            r3 = 4
            r0.reset(r3)
            byte[] r4 = r0.getData()
            r12.peekFully(r4, r6, r3)
            int r3 = r0.readInt()
            r4 = 1751476579(0x68656963, float:4.333464E24)
            if (r3 == r4) goto L61
            return r6
        L61:
            if (r13 != 0) goto L64
            return r2
        L64:
            int r7 = r7 + (-4)
            r12.advancePeekPosition(r7)
            r3 = r6
            goto L9
        L6b:
            return r6
        L6c:
            r4 = 1836086884(0x6d707664, float:4.6512205E27)
            if (r5 != r4) goto L72
            return r2
        L72:
            if (r7 == 0) goto L9
            r12.advancePeekPosition(r7)
            goto L9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.heif.HeifSniffer.sniff(androidx.media3.extractor.ExtractorInput, boolean):boolean");
    }
}
