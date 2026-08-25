package android.content;

import android.view.Size;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bp\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\b\u0010\u0006\u001a\u00020\u0003H'\u0082\u0001\u0002\u0007\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lcoil/util/HardwareBitmapService;", "", "allowHardwareMainThread", "", ContentDisposition.Parameters.Size, "Lcoil/size/Size;", "allowHardwareWorkerThread", "Lcoil/util/ImmutableHardwareBitmapService;", "Lcoil/util/LimitedFileDescriptorHardwareBitmapService;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface HardwareBitmapService {
    boolean allowHardwareMainThread(Size size);

    boolean allowHardwareWorkerThread();
}
