package androidx.media3.extractor;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import java.io.EOFException;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public final class Id3Peeker {
    private final ParsableByteArray scratch = new ParsableByteArray(10);

    private boolean peekId3HeaderIntoScratch(ExtractorInput extractorInput, int i10) throws IOException {
        int i11 = 0;
        do {
            int i12 = i11 % 10;
            int i13 = i12 + 10;
            if (i12 == 0 && i11 != 0) {
                System.arraycopy(this.scratch.getData(), 10, this.scratch.getData(), 0, 9);
            }
            int i14 = i11 != 0 ? 1 : 10;
            try {
                extractorInput.peekFully(this.scratch.getData(), i13 - i14, i14);
                this.scratch.setPosition(i12);
                this.scratch.setLimit(i13);
                if (this.scratch.peekUnsignedInt24() == 4801587) {
                    return true;
                }
                if (MpegAudioUtil.getFrameSize(this.scratch.peekInt()) != -1) {
                    return false;
                }
                if (i11 == 0) {
                    this.scratch.ensureCapacity(20);
                }
                i11++;
            } catch (EOFException unused) {
            }
        } while (i11 <= i10);
        return false;
    }

    @Deprecated
    public Metadata peekId3Data(ExtractorInput extractorInput, Id3Decoder.FramePredicate framePredicate) throws IOException {
        return peekId3Data(extractorInput, framePredicate, 0);
    }

    public Metadata peekId3Data(ExtractorInput extractorInput, Id3Decoder.FramePredicate framePredicate, int i10) throws IOException {
        Metadata metadataDecode = null;
        int i11 = 0;
        while (peekId3HeaderIntoScratch(extractorInput, i10)) {
            int position = this.scratch.getPosition();
            this.scratch.skipBytes(6);
            int synchSafeInt = this.scratch.readSynchSafeInt();
            int i12 = synchSafeInt + 10;
            if (metadataDecode == null) {
                byte[] bArr = new byte[i12];
                System.arraycopy(this.scratch.getData(), position, bArr, 0, 10);
                extractorInput.peekFully(bArr, 10, synchSafeInt);
                metadataDecode = new Id3Decoder(framePredicate).decode(bArr, i12);
            } else {
                extractorInput.advancePeekPosition(synchSafeInt);
            }
            i11 += i12;
        }
        extractorInput.resetPeekPosition();
        extractorInput.advancePeekPosition(i11);
        return metadataDecode;
    }
}
