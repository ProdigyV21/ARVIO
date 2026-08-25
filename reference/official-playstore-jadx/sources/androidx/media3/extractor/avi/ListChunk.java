package androidx.media3.extractor.avi;

import androidx.media3.common.util.ParsableByteArray;
import com.google.common.collect.a0;
import com.google.common.collect.b1;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
final class ListChunk implements AviChunk {
    public final h1 children;
    private final int type;

    private ListChunk(int i10, h1 h1Var) {
        this.type = i10;
        this.children = h1Var;
    }

    private static AviChunk createBox(int i10, int i11, ParsableByteArray parsableByteArray) {
        switch (i10) {
            case AviExtractor.FOURCC_strf /* 1718776947 */:
                return StreamFormatChunk.parseFrom(i11, parsableByteArray);
            case AviExtractor.FOURCC_avih /* 1751742049 */:
                return AviMainHeaderChunk.parseFrom(parsableByteArray);
            case AviExtractor.FOURCC_strh /* 1752331379 */:
                return AviStreamHeaderChunk.parseFrom(parsableByteArray);
            case AviExtractor.FOURCC_strn /* 1852994675 */:
                return StreamNameChunk.parseFrom(parsableByteArray);
            default:
                return null;
        }
    }

    public static ListChunk parseFrom(int i10, ParsableByteArray parsableByteArray) {
        a0.c(4, "initialCapacity");
        Object[] objArrCopyOf = new Object[4];
        int iLimit = parsableByteArray.limit();
        int i11 = 0;
        int trackType = -2;
        while (parsableByteArray.bytesLeft() > 8) {
            int littleEndianInt = parsableByteArray.readLittleEndianInt();
            int position = parsableByteArray.getPosition() + parsableByteArray.readLittleEndianInt();
            parsableByteArray.setLimit(position);
            AviChunk from = littleEndianInt == 1414744396 ? parseFrom(parsableByteArray.readLittleEndianInt(), parsableByteArray) : createBox(littleEndianInt, trackType, parsableByteArray);
            if (from != null) {
                if (from.getType() == 1752331379) {
                    trackType = ((AviStreamHeaderChunk) from).getTrackType();
                }
                int i12 = i11 + 1;
                int iB = b1.b(objArrCopyOf.length, i12);
                if (iB > objArrCopyOf.length) {
                    objArrCopyOf = Arrays.copyOf(objArrCopyOf, iB);
                }
                objArrCopyOf[i11] = from;
                i11 = i12;
            }
            parsableByteArray.setPosition(position);
            parsableByteArray.setLimit(iLimit);
        }
        return new ListChunk(i10, h1.h(i11, objArrCopyOf));
    }

    public <T extends AviChunk> T getChild(Class<T> cls) {
        f1 f1VarListIterator = this.children.listIterator(0);
        while (f1VarListIterator.hasNext()) {
            T t2 = (T) f1VarListIterator.next();
            if (t2.getClass() == cls) {
                return t2;
            }
        }
        return null;
    }

    @Override // androidx.media3.extractor.avi.AviChunk
    public int getType() {
        return this.type;
    }
}
