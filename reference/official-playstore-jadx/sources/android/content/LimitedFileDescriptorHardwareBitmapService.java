package android.content;

import android.view.Dimension;
import android.view.Size;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcoil/util/LimitedFileDescriptorHardwareBitmapService;", "Lcoil/util/HardwareBitmapService;", "logger", "Lcoil/util/Logger;", "(Lcoil/util/Logger;)V", "allowHardwareMainThread", "", ContentDisposition.Parameters.Size, "Lcoil/size/Size;", "allowHardwareWorkerThread", "Companion", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
final class LimitedFileDescriptorHardwareBitmapService implements HardwareBitmapService {
    private static final int MIN_SIZE_DIMENSION = 100;
    private final Logger logger;

    public LimitedFileDescriptorHardwareBitmapService(Logger logger) {
        this.logger = logger;
    }

    @Override // android.content.HardwareBitmapService
    public boolean allowHardwareMainThread(Size size) {
        Dimension width = size.getWidth();
        if ((width instanceof Dimension.Pixels ? ((Dimension.Pixels) width).px : Integer.MAX_VALUE) <= 100) {
            return false;
        }
        Dimension height = size.getHeight();
        return (height instanceof Dimension.Pixels ? ((Dimension.Pixels) height).px : Integer.MAX_VALUE) > 100;
    }

    @Override // android.content.HardwareBitmapService
    /* JADX INFO: renamed from: allowHardwareWorkerThread */
    public boolean getAllowHardware() {
        return FileDescriptorCounter.INSTANCE.hasAvailableFileDescriptors(this.logger);
    }
}
