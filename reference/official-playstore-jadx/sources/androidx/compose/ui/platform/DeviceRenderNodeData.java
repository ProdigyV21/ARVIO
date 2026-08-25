package androidx.compose.ui.platform;

import androidx.compose.ui.graphics.CompositingStrategy;
import androidx.compose.ui.graphics.RenderEffect;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\u000e\n\u0002\b;\b\u0080\b\u0018\u00002\u00020\u0001BÑ\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b\u0012\u0006\u0010\u000f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u000b\u0012\u0006\u0010\u0011\u001a\u00020\u0004\u0012\u0006\u0010\u0012\u001a\u00020\u0004\u0012\u0006\u0010\u0013\u001a\u00020\u000b\u0012\u0006\u0010\u0014\u001a\u00020\u000b\u0012\u0006\u0010\u0015\u001a\u00020\u000b\u0012\u0006\u0010\u0016\u001a\u00020\u000b\u0012\u0006\u0010\u0017\u001a\u00020\u000b\u0012\u0006\u0010\u0018\u001a\u00020\u000b\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010\u001b\u001a\u00020\u0019\u0012\u0006\u0010\u001c\u001a\u00020\u000b\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\u0006\u0010 \u001a\u00020\u001f¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b%\u0010&J\u0010\u0010'\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b'\u0010&J\u0010\u0010(\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b(\u0010&J\u0010\u0010)\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b)\u0010&J\u0010\u0010*\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b*\u0010&J\u0010\u0010+\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b+\u0010&J\u0010\u0010,\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b,\u0010-J\u0010\u0010.\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b.\u0010-J\u0010\u0010/\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b/\u0010-J\u0010\u00100\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b0\u0010-J\u0010\u00101\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b1\u0010-J\u0010\u00102\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b2\u0010&J\u0010\u00103\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b3\u0010&J\u0010\u00104\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b4\u0010-J\u0010\u00105\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b5\u0010-J\u0010\u00106\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b6\u0010-J\u0010\u00107\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b7\u0010-J\u0010\u00108\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b8\u0010-J\u0010\u00109\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b9\u0010-J\u0010\u0010:\u001a\u00020\u0019HÆ\u0003¢\u0006\u0004\b:\u0010;J\u0010\u0010<\u001a\u00020\u0019HÆ\u0003¢\u0006\u0004\b<\u0010;J\u0010\u0010=\u001a\u00020\u000bHÆ\u0003¢\u0006\u0004\b=\u0010-J\u0012\u0010>\u001a\u0004\u0018\u00010\u001dHÆ\u0003¢\u0006\u0004\b>\u0010?J\u0016\u0010A\u001a\u00020\u001fHÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b@\u0010&J\u008f\u0002\u0010D\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000b2\b\b\u0002\u0010\u000f\u001a\u00020\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u000b2\b\b\u0002\u0010\u0011\u001a\u00020\u00042\b\b\u0002\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0013\u001a\u00020\u000b2\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u000b2\b\b\u0002\u0010\u0016\u001a\u00020\u000b2\b\b\u0002\u0010\u0017\u001a\u00020\u000b2\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001b\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u000b2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\b\u0002\u0010 \u001a\u00020\u001fHÆ\u0001ø\u0001\u0001¢\u0006\u0004\bB\u0010CJ\u0010\u0010F\u001a\u00020EHÖ\u0001¢\u0006\u0004\bF\u0010GJ\u0010\u0010H\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\bH\u0010&J\u001a\u0010J\u001a\u00020\u00192\b\u0010I\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\bJ\u0010KR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010L\u001a\u0004\bM\u0010$R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010N\u001a\u0004\bO\u0010&R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010N\u001a\u0004\bP\u0010&R\u0017\u0010\u0007\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0007\u0010N\u001a\u0004\bQ\u0010&R\u0017\u0010\b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\b\u0010N\u001a\u0004\bR\u0010&R\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010N\u001a\u0004\bS\u0010&R\u0017\u0010\n\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\n\u0010N\u001a\u0004\bT\u0010&R\"\u0010\f\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010U\u001a\u0004\bV\u0010-\"\u0004\bW\u0010XR\"\u0010\r\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010U\u001a\u0004\bY\u0010-\"\u0004\bZ\u0010XR\"\u0010\u000e\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000e\u0010U\u001a\u0004\b[\u0010-\"\u0004\b\\\u0010XR\"\u0010\u000f\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010U\u001a\u0004\b]\u0010-\"\u0004\b^\u0010XR\"\u0010\u0010\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0010\u0010U\u001a\u0004\b_\u0010-\"\u0004\b`\u0010XR\"\u0010\u0011\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010N\u001a\u0004\ba\u0010&\"\u0004\bb\u0010cR\"\u0010\u0012\u001a\u00020\u00048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0012\u0010N\u001a\u0004\bd\u0010&\"\u0004\be\u0010cR\"\u0010\u0013\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u0010U\u001a\u0004\bf\u0010-\"\u0004\bg\u0010XR\"\u0010\u0014\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010U\u001a\u0004\bh\u0010-\"\u0004\bi\u0010XR\"\u0010\u0015\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010U\u001a\u0004\bj\u0010-\"\u0004\bk\u0010XR\"\u0010\u0016\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0016\u0010U\u001a\u0004\bl\u0010-\"\u0004\bm\u0010XR\"\u0010\u0017\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010U\u001a\u0004\bn\u0010-\"\u0004\bo\u0010XR\"\u0010\u0018\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010U\u001a\u0004\bp\u0010-\"\u0004\bq\u0010XR\"\u0010\u001a\u001a\u00020\u00198\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010r\u001a\u0004\bs\u0010;\"\u0004\bt\u0010uR\"\u0010\u001b\u001a\u00020\u00198\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010r\u001a\u0004\bv\u0010;\"\u0004\bw\u0010uR\"\u0010\u001c\u001a\u00020\u000b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010U\u001a\u0004\bx\u0010-\"\u0004\by\u0010XR$\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010z\u001a\u0004\b{\u0010?\"\u0004\b|\u0010}R(\u0010 \u001a\u00020\u001f8\u0006@\u0006X\u0086\u000eø\u0001\u0001ø\u0001\u0000¢\u0006\u0012\n\u0004\b \u0010N\u001a\u0004\b~\u0010&\"\u0004\b\u007f\u0010c\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006\u0080\u0001"}, d2 = {"Landroidx/compose/ui/platform/DeviceRenderNodeData;", "", "", "uniqueId", "", TtmlNode.LEFT, "top", TtmlNode.RIGHT, "bottom", "width", "height", "", "scaleX", "scaleY", "translationX", "translationY", "elevation", "ambientShadowColor", "spotShadowColor", "rotationZ", "rotationX", "rotationY", "cameraDistance", "pivotX", "pivotY", "", "clipToOutline", "clipToBounds", "alpha", "Landroidx/compose/ui/graphics/RenderEffect;", "renderEffect", "Landroidx/compose/ui/graphics/CompositingStrategy;", "compositingStrategy", "<init>", "(JIIIIIIFFFFFIIFFFFFFZZFLandroidx/compose/ui/graphics/RenderEffect;ILkotlin/jvm/internal/h;)V", "component1", "()J", "component2", "()I", "component3", "component4", "component5", "component6", "component7", "component8", "()F", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "()Z", "component22", "component23", "component24", "()Landroidx/compose/ui/graphics/RenderEffect;", "component25--NrFUSI", "component25", "copy-fuCbV5c", "(JIIIIIIFFFFFIIFFFFFFZZFLandroidx/compose/ui/graphics/RenderEffect;I)Landroidx/compose/ui/platform/DeviceRenderNodeData;", "copy", "", "toString", "()Ljava/lang/String;", "hashCode", "other", "equals", "(Ljava/lang/Object;)Z", "J", "getUniqueId", "I", "getLeft", "getTop", "getRight", "getBottom", "getWidth", "getHeight", "F", "getScaleX", "setScaleX", "(F)V", "getScaleY", "setScaleY", "getTranslationX", "setTranslationX", "getTranslationY", "setTranslationY", "getElevation", "setElevation", "getAmbientShadowColor", "setAmbientShadowColor", "(I)V", "getSpotShadowColor", "setSpotShadowColor", "getRotationZ", "setRotationZ", "getRotationX", "setRotationX", "getRotationY", "setRotationY", "getCameraDistance", "setCameraDistance", "getPivotX", "setPivotX", "getPivotY", "setPivotY", "Z", "getClipToOutline", "setClipToOutline", "(Z)V", "getClipToBounds", "setClipToBounds", "getAlpha", "setAlpha", "Landroidx/compose/ui/graphics/RenderEffect;", "getRenderEffect", "setRenderEffect", "(Landroidx/compose/ui/graphics/RenderEffect;)V", "getCompositingStrategy--NrFUSI", "setCompositingStrategy-aDBOjCE", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class DeviceRenderNodeData {
    public static final int $stable = 8;
    private float alpha;
    private int ambientShadowColor;
    private final int bottom;
    private float cameraDistance;
    private boolean clipToBounds;
    private boolean clipToOutline;
    private int compositingStrategy;
    private float elevation;
    private final int height;
    private final int left;
    private float pivotX;
    private float pivotY;
    private RenderEffect renderEffect;
    private final int right;
    private float rotationX;
    private float rotationY;
    private float rotationZ;
    private float scaleX;
    private float scaleY;
    private int spotShadowColor;
    private final int top;
    private float translationX;
    private float translationY;
    private final long uniqueId;
    private final int width;

    public /* synthetic */ DeviceRenderNodeData(long j10, int i10, int i11, int i12, int i13, int i14, int i15, float f10, float f11, float f12, float f13, float f14, int i16, int i17, float f15, float f16, float f17, float f18, float f19, float f20, boolean z, boolean z5, float f21, RenderEffect renderEffect, int i18, kotlin.jvm.internal.h hVar) {
        this(j10, i10, i11, i12, i13, i14, i15, f10, f11, f12, f13, f14, i16, i17, f15, f16, f17, f18, f19, f20, z, z5, f21, renderEffect, i18);
    }

    /* JADX INFO: renamed from: copy-fuCbV5c$default, reason: not valid java name */
    public static /* synthetic */ DeviceRenderNodeData m4956copyfuCbV5c$default(DeviceRenderNodeData deviceRenderNodeData, long j10, int i10, int i11, int i12, int i13, int i14, int i15, float f10, float f11, float f12, float f13, float f14, int i16, int i17, float f15, float f16, float f17, float f18, float f19, float f20, boolean z, boolean z5, float f21, RenderEffect renderEffect, int i18, int i19, Object obj) {
        int i20;
        RenderEffect renderEffect2;
        long j11 = (i19 & 1) != 0 ? deviceRenderNodeData.uniqueId : j10;
        int i21 = (i19 & 2) != 0 ? deviceRenderNodeData.left : i10;
        int i22 = (i19 & 4) != 0 ? deviceRenderNodeData.top : i11;
        int i23 = (i19 & 8) != 0 ? deviceRenderNodeData.right : i12;
        int i24 = (i19 & 16) != 0 ? deviceRenderNodeData.bottom : i13;
        int i25 = (i19 & 32) != 0 ? deviceRenderNodeData.width : i14;
        int i26 = (i19 & 64) != 0 ? deviceRenderNodeData.height : i15;
        float f22 = (i19 & 128) != 0 ? deviceRenderNodeData.scaleX : f10;
        float f23 = (i19 & 256) != 0 ? deviceRenderNodeData.scaleY : f11;
        float f24 = (i19 & 512) != 0 ? deviceRenderNodeData.translationX : f12;
        float f25 = (i19 & 1024) != 0 ? deviceRenderNodeData.translationY : f13;
        float f26 = (i19 & 2048) != 0 ? deviceRenderNodeData.elevation : f14;
        int i27 = (i19 & 4096) != 0 ? deviceRenderNodeData.ambientShadowColor : i16;
        long j12 = j11;
        int i28 = (i19 & 8192) != 0 ? deviceRenderNodeData.spotShadowColor : i17;
        float f27 = (i19 & 16384) != 0 ? deviceRenderNodeData.rotationZ : f15;
        float f28 = (i19 & 32768) != 0 ? deviceRenderNodeData.rotationX : f16;
        float f29 = (i19 & 65536) != 0 ? deviceRenderNodeData.rotationY : f17;
        float f30 = (i19 & 131072) != 0 ? deviceRenderNodeData.cameraDistance : f18;
        float f31 = (i19 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? deviceRenderNodeData.pivotX : f19;
        float f32 = (i19 & 524288) != 0 ? deviceRenderNodeData.pivotY : f20;
        boolean z10 = (i19 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? deviceRenderNodeData.clipToOutline : z;
        boolean z11 = (i19 & 2097152) != 0 ? deviceRenderNodeData.clipToBounds : z5;
        float f33 = (i19 & 4194304) != 0 ? deviceRenderNodeData.alpha : f21;
        RenderEffect renderEffect3 = (i19 & 8388608) != 0 ? deviceRenderNodeData.renderEffect : renderEffect;
        if ((i19 & 16777216) != 0) {
            renderEffect2 = renderEffect3;
            i20 = deviceRenderNodeData.compositingStrategy;
        } else {
            i20 = i18;
            renderEffect2 = renderEffect3;
        }
        return deviceRenderNodeData.m4958copyfuCbV5c(j12, i21, i22, i23, i24, i25, i26, f22, f23, f24, f25, f26, i27, i28, f27, f28, f29, f30, f31, f32, z10, z11, f33, renderEffect2, i20);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getUniqueId() {
        return this.uniqueId;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final float getTranslationX() {
        return this.translationX;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final float getTranslationY() {
        return this.translationY;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final float getElevation() {
        return this.elevation;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final int getAmbientShadowColor() {
        return this.ambientShadowColor;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final int getSpotShadowColor() {
        return this.spotShadowColor;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final float getRotationZ() {
        return this.rotationZ;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final float getRotationX() {
        return this.rotationX;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final float getRotationY() {
        return this.rotationY;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final float getCameraDistance() {
        return this.cameraDistance;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final float getPivotX() {
        return this.pivotX;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getLeft() {
        return this.left;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final float getPivotY() {
        return this.pivotY;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final boolean getClipToOutline() {
        return this.clipToOutline;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final boolean getClipToBounds() {
        return this.clipToBounds;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final float getAlpha() {
        return this.alpha;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final RenderEffect getRenderEffect() {
        return this.renderEffect;
    }

    /* JADX INFO: renamed from: component25--NrFUSI, reason: not valid java name and from getter */
    public final int getCompositingStrategy() {
        return this.compositingStrategy;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getTop() {
        return this.top;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getRight() {
        return this.right;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getBottom() {
        return this.bottom;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final float getScaleX() {
        return this.scaleX;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final float getScaleY() {
        return this.scaleY;
    }

    /* JADX INFO: renamed from: copy-fuCbV5c, reason: not valid java name */
    public final DeviceRenderNodeData m4958copyfuCbV5c(long uniqueId, int left, int top, int right, int bottom, int width, int height, float scaleX, float scaleY, float translationX, float translationY, float elevation, int ambientShadowColor, int spotShadowColor, float rotationZ, float rotationX, float rotationY, float cameraDistance, float pivotX, float pivotY, boolean clipToOutline, boolean clipToBounds, float alpha, RenderEffect renderEffect, int compositingStrategy) {
        return new DeviceRenderNodeData(uniqueId, left, top, right, bottom, width, height, scaleX, scaleY, translationX, translationY, elevation, ambientShadowColor, spotShadowColor, rotationZ, rotationX, rotationY, cameraDistance, pivotX, pivotY, clipToOutline, clipToBounds, alpha, renderEffect, compositingStrategy, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceRenderNodeData)) {
            return false;
        }
        DeviceRenderNodeData deviceRenderNodeData = (DeviceRenderNodeData) other;
        return this.uniqueId == deviceRenderNodeData.uniqueId && this.left == deviceRenderNodeData.left && this.top == deviceRenderNodeData.top && this.right == deviceRenderNodeData.right && this.bottom == deviceRenderNodeData.bottom && this.width == deviceRenderNodeData.width && this.height == deviceRenderNodeData.height && Float.compare(this.scaleX, deviceRenderNodeData.scaleX) == 0 && Float.compare(this.scaleY, deviceRenderNodeData.scaleY) == 0 && Float.compare(this.translationX, deviceRenderNodeData.translationX) == 0 && Float.compare(this.translationY, deviceRenderNodeData.translationY) == 0 && Float.compare(this.elevation, deviceRenderNodeData.elevation) == 0 && this.ambientShadowColor == deviceRenderNodeData.ambientShadowColor && this.spotShadowColor == deviceRenderNodeData.spotShadowColor && Float.compare(this.rotationZ, deviceRenderNodeData.rotationZ) == 0 && Float.compare(this.rotationX, deviceRenderNodeData.rotationX) == 0 && Float.compare(this.rotationY, deviceRenderNodeData.rotationY) == 0 && Float.compare(this.cameraDistance, deviceRenderNodeData.cameraDistance) == 0 && Float.compare(this.pivotX, deviceRenderNodeData.pivotX) == 0 && Float.compare(this.pivotY, deviceRenderNodeData.pivotY) == 0 && this.clipToOutline == deviceRenderNodeData.clipToOutline && this.clipToBounds == deviceRenderNodeData.clipToBounds && Float.compare(this.alpha, deviceRenderNodeData.alpha) == 0 && kotlin.jvm.internal.p.a(this.renderEffect, deviceRenderNodeData.renderEffect) && CompositingStrategy.m3557equalsimpl0(this.compositingStrategy, deviceRenderNodeData.compositingStrategy);
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final int getAmbientShadowColor() {
        return this.ambientShadowColor;
    }

    public final int getBottom() {
        return this.bottom;
    }

    public final float getCameraDistance() {
        return this.cameraDistance;
    }

    public final boolean getClipToBounds() {
        return this.clipToBounds;
    }

    public final boolean getClipToOutline() {
        return this.clipToOutline;
    }

    /* JADX INFO: renamed from: getCompositingStrategy--NrFUSI, reason: not valid java name */
    public final int m4959getCompositingStrategyNrFUSI() {
        return this.compositingStrategy;
    }

    public final float getElevation() {
        return this.elevation;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getLeft() {
        return this.left;
    }

    public final float getPivotX() {
        return this.pivotX;
    }

    public final float getPivotY() {
        return this.pivotY;
    }

    public final RenderEffect getRenderEffect() {
        return this.renderEffect;
    }

    public final int getRight() {
        return this.right;
    }

    public final float getRotationX() {
        return this.rotationX;
    }

    public final float getRotationY() {
        return this.rotationY;
    }

    public final float getRotationZ() {
        return this.rotationZ;
    }

    public final float getScaleX() {
        return this.scaleX;
    }

    public final float getScaleY() {
        return this.scaleY;
    }

    public final int getSpotShadowColor() {
        return this.spotShadowColor;
    }

    public final int getTop() {
        return this.top;
    }

    public final float getTranslationX() {
        return this.translationX;
    }

    public final float getTranslationY() {
        return this.translationY;
    }

    public final long getUniqueId() {
        return this.uniqueId;
    }

    public final int getWidth() {
        return this.width;
    }

    public int hashCode() {
        long j10 = this.uniqueId;
        int iB = a0.c.b(this.alpha, (((a0.c.b(this.pivotY, a0.c.b(this.pivotX, a0.c.b(this.cameraDistance, a0.c.b(this.rotationY, a0.c.b(this.rotationX, a0.c.b(this.rotationZ, (((a0.c.b(this.elevation, a0.c.b(this.translationY, a0.c.b(this.translationX, a0.c.b(this.scaleY, a0.c.b(this.scaleX, ((((((((((((((int) (j10 ^ (j10 >>> 32))) * 31) + this.left) * 31) + this.top) * 31) + this.right) * 31) + this.bottom) * 31) + this.width) * 31) + this.height) * 31, 31), 31), 31), 31), 31) + this.ambientShadowColor) * 31) + this.spotShadowColor) * 31, 31), 31), 31), 31), 31), 31) + (this.clipToOutline ? 1231 : 1237)) * 31) + (this.clipToBounds ? 1231 : 1237)) * 31, 31);
        RenderEffect renderEffect = this.renderEffect;
        return CompositingStrategy.m3558hashCodeimpl(this.compositingStrategy) + ((iB + (renderEffect == null ? 0 : renderEffect.hashCode())) * 31);
    }

    public final void setAlpha(float f10) {
        this.alpha = f10;
    }

    public final void setAmbientShadowColor(int i10) {
        this.ambientShadowColor = i10;
    }

    public final void setCameraDistance(float f10) {
        this.cameraDistance = f10;
    }

    public final void setClipToBounds(boolean z) {
        this.clipToBounds = z;
    }

    public final void setClipToOutline(boolean z) {
        this.clipToOutline = z;
    }

    /* JADX INFO: renamed from: setCompositingStrategy-aDBOjCE, reason: not valid java name */
    public final void m4960setCompositingStrategyaDBOjCE(int i10) {
        this.compositingStrategy = i10;
    }

    public final void setElevation(float f10) {
        this.elevation = f10;
    }

    public final void setPivotX(float f10) {
        this.pivotX = f10;
    }

    public final void setPivotY(float f10) {
        this.pivotY = f10;
    }

    public final void setRenderEffect(RenderEffect renderEffect) {
        this.renderEffect = renderEffect;
    }

    public final void setRotationX(float f10) {
        this.rotationX = f10;
    }

    public final void setRotationY(float f10) {
        this.rotationY = f10;
    }

    public final void setRotationZ(float f10) {
        this.rotationZ = f10;
    }

    public final void setScaleX(float f10) {
        this.scaleX = f10;
    }

    public final void setScaleY(float f10) {
        this.scaleY = f10;
    }

    public final void setSpotShadowColor(int i10) {
        this.spotShadowColor = i10;
    }

    public final void setTranslationX(float f10) {
        this.translationX = f10;
    }

    public final void setTranslationY(float f10) {
        this.translationY = f10;
    }

    public String toString() {
        return "DeviceRenderNodeData(uniqueId=" + this.uniqueId + ", left=" + this.left + ", top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + ", width=" + this.width + ", height=" + this.height + ", scaleX=" + this.scaleX + ", scaleY=" + this.scaleY + ", translationX=" + this.translationX + ", translationY=" + this.translationY + ", elevation=" + this.elevation + ", ambientShadowColor=" + this.ambientShadowColor + ", spotShadowColor=" + this.spotShadowColor + ", rotationZ=" + this.rotationZ + ", rotationX=" + this.rotationX + ", rotationY=" + this.rotationY + ", cameraDistance=" + this.cameraDistance + ", pivotX=" + this.pivotX + ", pivotY=" + this.pivotY + ", clipToOutline=" + this.clipToOutline + ", clipToBounds=" + this.clipToBounds + ", alpha=" + this.alpha + ", renderEffect=" + this.renderEffect + ", compositingStrategy=" + ((Object) CompositingStrategy.m3559toStringimpl(this.compositingStrategy)) + ')';
    }

    private DeviceRenderNodeData(long j10, int i10, int i11, int i12, int i13, int i14, int i15, float f10, float f11, float f12, float f13, float f14, int i16, int i17, float f15, float f16, float f17, float f18, float f19, float f20, boolean z, boolean z5, float f21, RenderEffect renderEffect, int i18) {
        this.uniqueId = j10;
        this.left = i10;
        this.top = i11;
        this.right = i12;
        this.bottom = i13;
        this.width = i14;
        this.height = i15;
        this.scaleX = f10;
        this.scaleY = f11;
        this.translationX = f12;
        this.translationY = f13;
        this.elevation = f14;
        this.ambientShadowColor = i16;
        this.spotShadowColor = i17;
        this.rotationZ = f15;
        this.rotationX = f16;
        this.rotationY = f17;
        this.cameraDistance = f18;
        this.pivotX = f19;
        this.pivotY = f20;
        this.clipToOutline = z;
        this.clipToBounds = z5;
        this.alpha = f21;
        this.renderEffect = renderEffect;
        this.compositingStrategy = i18;
    }
}
