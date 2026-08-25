package androidx.media3.exoplayer.video.spherical;

import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.video.spherical.Projection;
import java.util.ArrayList;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes3.dex */
final class ProjectionDecoder {
    private static final int MAX_COORDINATE_COUNT = 10000;
    private static final int MAX_TRIANGLE_INDICES = 128000;
    private static final int MAX_VERTEX_COUNT = 32000;
    private static final int TYPE_DFL8 = 1684433976;
    private static final int TYPE_MESH = 1835365224;
    private static final int TYPE_MSHP = 1836279920;
    private static final int TYPE_PROJ = 1886547818;
    private static final int TYPE_RAW = 1918990112;
    private static final int TYPE_YTMP = 2037673328;

    private ProjectionDecoder() {
    }

    public static Projection decode(byte[] bArr, int i10) {
        ArrayList<Projection.Mesh> proj;
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr);
        try {
            proj = isProj(parsableByteArray) ? parseProj(parsableByteArray) : parseMshp(parsableByteArray);
        } catch (ArrayIndexOutOfBoundsException unused) {
            proj = null;
        }
        if (proj == null) {
            return null;
        }
        int size = proj.size();
        if (size == 1) {
            return new Projection(proj.get(0), i10);
        }
        if (size != 2) {
            return null;
        }
        return new Projection(proj.get(0), proj.get(1), i10);
    }

    private static int decodeZigZag(int i10) {
        return (-(i10 & 1)) ^ (i10 >> 1);
    }

    private static boolean isProj(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        int i10 = parsableByteArray.readInt();
        parsableByteArray.setPosition(0);
        return i10 == 1886547818;
    }

    private static Projection.Mesh parseMesh(ParsableByteArray parsableByteArray) {
        int i10 = parsableByteArray.readInt();
        Projection.Mesh mesh = null;
        if (i10 > 10000) {
            return null;
        }
        float[] fArr = new float[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            fArr[i11] = parsableByteArray.readFloat();
        }
        int i12 = parsableByteArray.readInt();
        if (i12 > MAX_VERTEX_COUNT) {
            return null;
        }
        double d4 = 2.0d;
        double dLog = Math.log(2.0d);
        int iCeil = (int) Math.ceil(Math.log(((double) i10) * 2.0d) / dLog);
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray.getData());
        int i13 = 8;
        parsableBitArray.setPosition(parsableByteArray.getPosition() * 8);
        float[] fArr2 = new float[i12 * 5];
        int[] iArr = new int[5];
        int i14 = 0;
        int i15 = 0;
        while (i14 < i12) {
            Projection.Mesh mesh2 = mesh;
            int i16 = 0;
            while (i16 < 5) {
                int iDecodeZigZag = iArr[i16] + decodeZigZag(parsableBitArray.readBits(iCeil));
                if (iDecodeZigZag >= i10 || iDecodeZigZag < 0) {
                    return mesh2;
                }
                fArr2[i15] = fArr[iDecodeZigZag];
                iArr[i16] = iDecodeZigZag;
                i16++;
                i15++;
            }
            i14++;
            mesh = mesh2;
        }
        Projection.Mesh mesh3 = mesh;
        parsableBitArray.setPosition((parsableBitArray.getPosition() + 7) & (-8));
        int i17 = 32;
        int bits = parsableBitArray.readBits(32);
        Projection.SubMesh[] subMeshArr = new Projection.SubMesh[bits];
        int i18 = 0;
        while (i18 < bits) {
            int bits2 = parsableBitArray.readBits(i13);
            int bits3 = parsableBitArray.readBits(i13);
            int bits4 = parsableBitArray.readBits(i17);
            if (bits4 > MAX_TRIANGLE_INDICES) {
                return mesh3;
            }
            int i19 = bits;
            int iCeil2 = (int) Math.ceil(Math.log(((double) i12) * d4) / dLog);
            float[] fArr3 = new float[bits4 * 3];
            float[] fArr4 = new float[bits4 * 2];
            int i20 = 0;
            int i21 = 0;
            while (i20 < bits4) {
                int iDecodeZigZag2 = i21 + decodeZigZag(parsableBitArray.readBits(iCeil2));
                if (iDecodeZigZag2 < 0 || iDecodeZigZag2 >= i12) {
                    return mesh3;
                }
                int i22 = i20 * 3;
                int i23 = iDecodeZigZag2 * 5;
                fArr3[i22] = fArr2[i23];
                fArr3[i22 + 1] = fArr2[i23 + 1];
                fArr3[i22 + 2] = fArr2[i23 + 2];
                int i24 = i20 * 2;
                fArr4[i24] = fArr2[i23 + 3];
                fArr4[i24 + 1] = fArr2[i23 + 4];
                i20++;
                i21 = iDecodeZigZag2;
            }
            subMeshArr[i18] = new Projection.SubMesh(bits2, fArr3, fArr4, bits3);
            i18++;
            bits = i19;
            i17 = 32;
            d4 = 2.0d;
            i13 = 8;
        }
        return new Projection.Mesh(subMeshArr);
    }

    private static ArrayList<Projection.Mesh> parseMshp(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.readUnsignedByte() != 0) {
            return null;
        }
        parsableByteArray.skipBytes(7);
        int i10 = parsableByteArray.readInt();
        if (i10 == TYPE_DFL8) {
            ParsableByteArray parsableByteArray2 = new ParsableByteArray();
            Inflater inflater = new Inflater(true);
            try {
                if (!Util.inflate(parsableByteArray, parsableByteArray2, inflater)) {
                    return null;
                }
                inflater.end();
                parsableByteArray = parsableByteArray2;
            } finally {
                inflater.end();
            }
        } else if (i10 != TYPE_RAW) {
            return null;
        }
        return parseRawMshpData(parsableByteArray);
    }

    private static ArrayList<Projection.Mesh> parseProj(ParsableByteArray parsableByteArray) {
        int i10;
        parsableByteArray.skipBytes(8);
        int position = parsableByteArray.getPosition();
        int iLimit = parsableByteArray.limit();
        while (position < iLimit && (i10 = parsableByteArray.readInt() + position) > position && i10 <= iLimit) {
            int i11 = parsableByteArray.readInt();
            if (i11 == TYPE_YTMP || i11 == TYPE_MSHP) {
                parsableByteArray.setLimit(i10);
                return parseMshp(parsableByteArray);
            }
            parsableByteArray.setPosition(i10);
            position = i10;
        }
        return null;
    }

    private static ArrayList<Projection.Mesh> parseRawMshpData(ParsableByteArray parsableByteArray) {
        ArrayList<Projection.Mesh> arrayList = new ArrayList<>();
        int position = parsableByteArray.getPosition();
        int iLimit = parsableByteArray.limit();
        while (position < iLimit) {
            int i10 = parsableByteArray.readInt() + position;
            if (i10 <= position || i10 > iLimit) {
                return null;
            }
            if (parsableByteArray.readInt() == TYPE_MESH) {
                Projection.Mesh mesh = parseMesh(parsableByteArray);
                if (mesh == null) {
                    return null;
                }
                arrayList.add(mesh);
            }
            parsableByteArray.setPosition(i10);
            position = i10;
        }
        return arrayList;
    }
}
