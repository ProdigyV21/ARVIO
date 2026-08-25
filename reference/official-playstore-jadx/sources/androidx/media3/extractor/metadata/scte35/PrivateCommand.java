package androidx.media3.extractor.metadata.scte35;

import a0.c;
import androidx.media3.common.util.ParsableByteArray;

/* JADX INFO: loaded from: classes3.dex */
public final class PrivateCommand extends SpliceCommand {
    public final byte[] commandBytes;
    public final long identifier;
    public final long ptsAdjustment;

    private PrivateCommand(long j10, byte[] bArr, long j11) {
        this.ptsAdjustment = j11;
        this.identifier = j10;
        this.commandBytes = bArr;
    }

    public static PrivateCommand parseFromSection(ParsableByteArray parsableByteArray, int i10, long j10) {
        long unsignedInt = parsableByteArray.readUnsignedInt();
        int i11 = i10 - 4;
        byte[] bArr = new byte[i11];
        parsableByteArray.readBytes(bArr, 0, i11);
        return new PrivateCommand(unsignedInt, bArr, j10);
    }

    @Override // androidx.media3.extractor.metadata.scte35.SpliceCommand
    public String toString() {
        StringBuilder sb2 = new StringBuilder("SCTE-35 PrivateCommand { ptsAdjustment=");
        sb2.append(this.ptsAdjustment);
        sb2.append(", identifier= ");
        return c.j(this.identifier, " }", sb2);
    }
}
