package androidx.media3.exoplayer.source;

import androidx.media3.common.DataReader;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.CryptoInfo;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.upstream.Allocation;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.extractor.TrackOutput;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
class SampleDataQueue {
    private static final int INITIAL_SCRATCH_SIZE = 32;
    private final int allocationLength;
    private final Allocator allocator;
    private AllocationNode firstAllocationNode;
    private AllocationNode readAllocationNode;
    private final ParsableByteArray scratch;
    private long totalBytesWritten;
    private AllocationNode writeAllocationNode;

    public static final class AllocationNode implements Allocator.AllocationNode {
        public Allocation allocation;
        public long endPosition;
        public AllocationNode next;
        public long startPosition;

        public AllocationNode(long j10, int i10) {
            reset(j10, i10);
        }

        public AllocationNode clear() {
            this.allocation = null;
            AllocationNode allocationNode = this.next;
            this.next = null;
            return allocationNode;
        }

        @Override // androidx.media3.exoplayer.upstream.Allocator.AllocationNode
        public Allocation getAllocation() {
            Allocation allocation = this.allocation;
            allocation.getClass();
            return allocation;
        }

        public void initialize(Allocation allocation, AllocationNode allocationNode) {
            this.allocation = allocation;
            this.next = allocationNode;
        }

        @Override // androidx.media3.exoplayer.upstream.Allocator.AllocationNode
        public Allocator.AllocationNode next() {
            AllocationNode allocationNode = this.next;
            if (allocationNode == null || allocationNode.allocation == null) {
                return null;
            }
            return allocationNode;
        }

        public void reset(long j10, int i10) {
            ac.b.s(this.allocation == null);
            this.startPosition = j10;
            this.endPosition = j10 + ((long) i10);
        }

        public int translateOffset(long j10) {
            return ((int) (j10 - this.startPosition)) + this.allocation.offset;
        }
    }

    public SampleDataQueue(Allocator allocator) {
        this.allocator = allocator;
        int individualAllocationLength = allocator.getIndividualAllocationLength();
        this.allocationLength = individualAllocationLength;
        this.scratch = new ParsableByteArray(32);
        AllocationNode allocationNode = new AllocationNode(0L, individualAllocationLength);
        this.firstAllocationNode = allocationNode;
        this.readAllocationNode = allocationNode;
        this.writeAllocationNode = allocationNode;
    }

    private void clearAllocationNodes(AllocationNode allocationNode) {
        if (allocationNode.allocation == null) {
            return;
        }
        this.allocator.release(allocationNode);
        allocationNode.clear();
    }

    private static AllocationNode getNodeContainingPosition(AllocationNode allocationNode, long j10) {
        while (j10 >= allocationNode.endPosition) {
            allocationNode = allocationNode.next;
        }
        return allocationNode;
    }

    private void postAppend(int i10) {
        long j10 = this.totalBytesWritten + ((long) i10);
        this.totalBytesWritten = j10;
        AllocationNode allocationNode = this.writeAllocationNode;
        if (j10 == allocationNode.endPosition) {
            this.writeAllocationNode = allocationNode.next;
        }
    }

    private int preAppend(int i10) {
        AllocationNode allocationNode = this.writeAllocationNode;
        if (allocationNode.allocation == null) {
            allocationNode.initialize(this.allocator.allocate(), new AllocationNode(this.writeAllocationNode.endPosition, this.allocationLength));
        }
        return Math.min(i10, (int) (this.writeAllocationNode.endPosition - this.totalBytesWritten));
    }

    private static AllocationNode readData(AllocationNode allocationNode, long j10, ByteBuffer byteBuffer, int i10) {
        AllocationNode nodeContainingPosition = getNodeContainingPosition(allocationNode, j10);
        while (i10 > 0) {
            int iMin = Math.min(i10, (int) (nodeContainingPosition.endPosition - j10));
            byteBuffer.put(nodeContainingPosition.allocation.data, nodeContainingPosition.translateOffset(j10), iMin);
            i10 -= iMin;
            j10 += (long) iMin;
            if (j10 == nodeContainingPosition.endPosition) {
                nodeContainingPosition = nodeContainingPosition.next;
            }
        }
        return nodeContainingPosition;
    }

    private static AllocationNode readEncryptionData(AllocationNode allocationNode, DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder, ParsableByteArray parsableByteArray) {
        long j10 = sampleExtrasHolder.offset;
        int unsignedShort = 1;
        parsableByteArray.reset(1);
        AllocationNode data = readData(allocationNode, j10, parsableByteArray.getData(), 1);
        long j11 = j10 + 1;
        byte b10 = parsableByteArray.getData()[0];
        boolean z = (b10 & 128) != 0;
        int i10 = b10 & 127;
        CryptoInfo cryptoInfo = decoderInputBuffer.cryptoInfo;
        byte[] bArr = cryptoInfo.iv;
        if (bArr == null) {
            cryptoInfo.iv = new byte[16];
        } else {
            Arrays.fill(bArr, (byte) 0);
        }
        AllocationNode data2 = readData(data, j11, cryptoInfo.iv, i10);
        long j12 = j11 + ((long) i10);
        if (z) {
            parsableByteArray.reset(2);
            data2 = readData(data2, j12, parsableByteArray.getData(), 2);
            j12 += 2;
            unsignedShort = parsableByteArray.readUnsignedShort();
        }
        int i11 = unsignedShort;
        int[] iArr = cryptoInfo.numBytesOfClearData;
        if (iArr == null || iArr.length < i11) {
            iArr = new int[i11];
        }
        int[] iArr2 = iArr;
        int[] iArr3 = cryptoInfo.numBytesOfEncryptedData;
        if (iArr3 == null || iArr3.length < i11) {
            iArr3 = new int[i11];
        }
        int[] iArr4 = iArr3;
        if (z) {
            int i12 = i11 * 6;
            parsableByteArray.reset(i12);
            data2 = readData(data2, j12, parsableByteArray.getData(), i12);
            j12 += (long) i12;
            parsableByteArray.setPosition(0);
            for (int i13 = 0; i13 < i11; i13++) {
                iArr2[i13] = parsableByteArray.readUnsignedShort();
                iArr4[i13] = parsableByteArray.readUnsignedIntToInt();
            }
        } else {
            iArr2[0] = 0;
            iArr4[0] = sampleExtrasHolder.size - ((int) (j12 - sampleExtrasHolder.offset));
        }
        TrackOutput.CryptoData cryptoData = (TrackOutput.CryptoData) Util.castNonNull(sampleExtrasHolder.cryptoData);
        cryptoInfo.set(i11, iArr2, iArr4, cryptoData.encryptionKey, cryptoInfo.iv, cryptoData.cryptoMode, cryptoData.encryptedBlocks, cryptoData.clearBlocks);
        long j13 = sampleExtrasHolder.offset;
        int i14 = (int) (j12 - j13);
        sampleExtrasHolder.offset = j13 + ((long) i14);
        sampleExtrasHolder.size -= i14;
        return data2;
    }

    private static AllocationNode readSampleData(AllocationNode allocationNode, DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder, ParsableByteArray parsableByteArray) {
        if (decoderInputBuffer.isEncrypted()) {
            allocationNode = readEncryptionData(allocationNode, decoderInputBuffer, sampleExtrasHolder, parsableByteArray);
        }
        if (!decoderInputBuffer.hasSupplementalData()) {
            decoderInputBuffer.ensureSpaceForWrite(sampleExtrasHolder.size);
            return readData(allocationNode, sampleExtrasHolder.offset, decoderInputBuffer.data, sampleExtrasHolder.size);
        }
        parsableByteArray.reset(4);
        AllocationNode data = readData(allocationNode, sampleExtrasHolder.offset, parsableByteArray.getData(), 4);
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        sampleExtrasHolder.offset += 4;
        sampleExtrasHolder.size -= 4;
        decoderInputBuffer.ensureSpaceForWrite(unsignedIntToInt);
        AllocationNode data2 = readData(data, sampleExtrasHolder.offset, decoderInputBuffer.data, unsignedIntToInt);
        sampleExtrasHolder.offset += (long) unsignedIntToInt;
        int i10 = sampleExtrasHolder.size - unsignedIntToInt;
        sampleExtrasHolder.size = i10;
        decoderInputBuffer.resetSupplementalData(i10);
        return readData(data2, sampleExtrasHolder.offset, decoderInputBuffer.supplementalData, sampleExtrasHolder.size);
    }

    public void discardDownstreamTo(long j10) {
        AllocationNode allocationNode;
        if (j10 == -1) {
            return;
        }
        while (true) {
            allocationNode = this.firstAllocationNode;
            if (j10 < allocationNode.endPosition) {
                break;
            }
            this.allocator.release(allocationNode.allocation);
            this.firstAllocationNode = this.firstAllocationNode.clear();
        }
        if (this.readAllocationNode.startPosition < allocationNode.startPosition) {
            this.readAllocationNode = allocationNode;
        }
    }

    public void discardUpstreamSampleBytes(long j10) {
        ac.b.j(j10 <= this.totalBytesWritten);
        this.totalBytesWritten = j10;
        if (j10 != 0) {
            AllocationNode allocationNode = this.firstAllocationNode;
            if (j10 != allocationNode.startPosition) {
                while (this.totalBytesWritten > allocationNode.endPosition) {
                    allocationNode = allocationNode.next;
                }
                AllocationNode allocationNode2 = allocationNode.next;
                allocationNode2.getClass();
                clearAllocationNodes(allocationNode2);
                AllocationNode allocationNode3 = new AllocationNode(allocationNode.endPosition, this.allocationLength);
                allocationNode.next = allocationNode3;
                if (this.totalBytesWritten == allocationNode.endPosition) {
                    allocationNode = allocationNode3;
                }
                this.writeAllocationNode = allocationNode;
                if (this.readAllocationNode == allocationNode2) {
                    this.readAllocationNode = allocationNode3;
                    return;
                }
                return;
            }
        }
        clearAllocationNodes(this.firstAllocationNode);
        AllocationNode allocationNode4 = new AllocationNode(this.totalBytesWritten, this.allocationLength);
        this.firstAllocationNode = allocationNode4;
        this.readAllocationNode = allocationNode4;
        this.writeAllocationNode = allocationNode4;
    }

    public long getTotalBytesWritten() {
        return this.totalBytesWritten;
    }

    public void peekToBuffer(DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder) {
        readSampleData(this.readAllocationNode, decoderInputBuffer, sampleExtrasHolder, this.scratch);
    }

    public void readToBuffer(DecoderInputBuffer decoderInputBuffer, SampleQueue.SampleExtrasHolder sampleExtrasHolder) {
        this.readAllocationNode = readSampleData(this.readAllocationNode, decoderInputBuffer, sampleExtrasHolder, this.scratch);
    }

    public void reset() {
        clearAllocationNodes(this.firstAllocationNode);
        this.firstAllocationNode.reset(0L, this.allocationLength);
        AllocationNode allocationNode = this.firstAllocationNode;
        this.readAllocationNode = allocationNode;
        this.writeAllocationNode = allocationNode;
        this.totalBytesWritten = 0L;
        this.allocator.trim();
    }

    public void rewind() {
        this.readAllocationNode = this.firstAllocationNode;
    }

    public int sampleData(DataReader dataReader, int i10, boolean z) throws IOException {
        int iPreAppend = preAppend(i10);
        AllocationNode allocationNode = this.writeAllocationNode;
        int i11 = dataReader.read(allocationNode.allocation.data, allocationNode.translateOffset(this.totalBytesWritten), iPreAppend);
        if (i11 != -1) {
            postAppend(i11);
            return i11;
        }
        if (z) {
            return -1;
        }
        throw new EOFException();
    }

    private static AllocationNode readData(AllocationNode allocationNode, long j10, byte[] bArr, int i10) {
        AllocationNode nodeContainingPosition = getNodeContainingPosition(allocationNode, j10);
        int i11 = i10;
        while (i11 > 0) {
            int iMin = Math.min(i11, (int) (nodeContainingPosition.endPosition - j10));
            System.arraycopy(nodeContainingPosition.allocation.data, nodeContainingPosition.translateOffset(j10), bArr, i10 - i11, iMin);
            i11 -= iMin;
            j10 += (long) iMin;
            if (j10 == nodeContainingPosition.endPosition) {
                nodeContainingPosition = nodeContainingPosition.next;
            }
        }
        return nodeContainingPosition;
    }

    public void sampleData(ParsableByteArray parsableByteArray, int i10) {
        while (i10 > 0) {
            int iPreAppend = preAppend(i10);
            AllocationNode allocationNode = this.writeAllocationNode;
            parsableByteArray.readBytes(allocationNode.allocation.data, allocationNode.translateOffset(this.totalBytesWritten), iPreAppend);
            i10 -= iPreAppend;
            postAppend(iPreAppend);
        }
    }
}
