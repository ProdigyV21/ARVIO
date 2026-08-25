package androidx.media3.extractor.jpeg;

import androidx.media3.common.MimeTypes;
import androidx.media3.extractor.metadata.MotionPhotoMetadata;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class MotionPhotoDescription {
    public final List<ContainerItem> items;
    public final long photoPresentationTimestampUs;

    public static final class ContainerItem {
        public final long length;
        public final String mime;
        public final long padding;
        public final String semantic;

        public ContainerItem(String str, String str2, long j10, long j11) {
            this.mime = str;
            this.semantic = str2;
            this.length = j10;
            this.padding = j11;
        }
    }

    public MotionPhotoDescription(long j10, List<ContainerItem> list) {
        this.photoPresentationTimestampUs = j10;
        this.items = list;
    }

    public MotionPhotoMetadata getMotionPhotoMetadata(long j10) {
        MotionPhotoMetadata motionPhotoMetadata;
        long j11;
        MotionPhotoMetadata motionPhotoMetadata2 = null;
        if (this.items.size() < 2) {
            return null;
        }
        boolean z = true;
        int size = this.items.size() - 1;
        long j12 = j10;
        long j13 = -1;
        long j14 = -1;
        long j15 = -1;
        long j16 = -1;
        while (size >= 0) {
            ContainerItem containerItem = this.items.get(size);
            boolean z5 = (containerItem.mime.equals(MimeTypes.VIDEO_MP4) || containerItem.mime.equals(MimeTypes.VIDEO_QUICK_TIME)) ? z : false;
            if (size == 0) {
                motionPhotoMetadata = motionPhotoMetadata2;
                j12 -= containerItem.padding;
                j11 = 0;
            } else {
                motionPhotoMetadata = motionPhotoMetadata2;
                j11 = j12 - containerItem.length;
            }
            long j17 = j12;
            j12 = j11;
            if (z5 && j12 != j17) {
                j16 = j17 - j12;
                j15 = j12;
            }
            if (size == 0) {
                j14 = j17;
                j13 = j12;
            }
            size--;
            motionPhotoMetadata2 = motionPhotoMetadata;
            z = true;
        }
        return (j15 == -1 || j16 == -1 || j13 == -1 || j14 == -1) ? motionPhotoMetadata2 : new MotionPhotoMetadata(j13, j14, this.photoPresentationTimestampUs, j15, j16);
    }
}
