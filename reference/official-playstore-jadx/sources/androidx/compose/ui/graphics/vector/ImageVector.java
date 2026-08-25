package androidx.compose.ui.graphics.vector;

import androidx.compose.foundation.c;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b \b\u0007\u0018\u0000 02\u00020\u0001:\u000210B[\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0017\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u0096\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010\u0005\u001a\u00020\u00048\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001d\u0010\u0006\u001a\u00020\u00048\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b!\u0010 R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b\"\u0010 R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\u001e\u001a\u0004\b#\u0010 R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010$\u001a\u0004\b%\u0010&R\u001d\u0010\r\u001a\u00020\f8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\r\u0010'\u001a\u0004\b(\u0010)R\u001d\u0010\u000f\u001a\u00020\u000e8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b\u000f\u0010*\u001a\u0004\b+\u0010\u001aR\u0017\u0010\u0011\u001a\u00020\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010,\u001a\u0004\b-\u0010.R\u001a\u0010\u0013\u001a\u00020\u00128\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0013\u0010*\u001a\u0004\b/\u0010\u001a\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00062"}, d2 = {"Landroidx/compose/ui/graphics/vector/ImageVector;", "", "", ContentDisposition.Parameters.Name, "Landroidx/compose/ui/unit/Dp;", "defaultWidth", "defaultHeight", "", "viewportWidth", "viewportHeight", "Landroidx/compose/ui/graphics/vector/VectorGroup;", "root", "Landroidx/compose/ui/graphics/Color;", "tintColor", "Landroidx/compose/ui/graphics/BlendMode;", "tintBlendMode", "", "autoMirror", "", "genId", "<init>", "(Ljava/lang/String;FFFFLandroidx/compose/ui/graphics/vector/VectorGroup;JIZILkotlin/jvm/internal/h;)V", "other", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "Ljava/lang/String;", "getName", "()Ljava/lang/String;", "F", "getDefaultWidth-D9Ej5fM", "()F", "getDefaultHeight-D9Ej5fM", "getViewportWidth", "getViewportHeight", "Landroidx/compose/ui/graphics/vector/VectorGroup;", "getRoot", "()Landroidx/compose/ui/graphics/vector/VectorGroup;", "J", "getTintColor-0d7_KjU", "()J", "I", "getTintBlendMode-0nO6VwU", "Z", "getAutoMirror", "()Z", "getGenId$ui_release", "Companion", "Builder", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ImageVector {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static int imageVectorCount;
    private final boolean autoMirror;
    private final float defaultHeight;
    private final float defaultWidth;
    private final int genId;
    private final String name;
    private final VectorGroup root;
    private final int tintBlendMode;
    private final long tintColor;
    private final float viewportHeight;
    private final float viewportWidth;

    @Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001:\u0001NBO\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011BG\b\u0017\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u0012J\u000f\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0013\u0010\u0018\u001a\u00020\u0017*\u00020\u0016H\u0002¢\u0006\u0004\b\u0018\u0010\u0019Jm\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u00072\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001d\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u00072\b\b\u0002\u0010\u001f\u001a\u00020\u00072\b\b\u0002\u0010 \u001a\u00020\u00072\u000e\b\u0002\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!¢\u0006\u0004\b$\u0010%J\r\u0010&\u001a\u00020\u0000¢\u0006\u0004\b&\u0010'J¤\u0001\u0010;\u001a\u00020\u00002\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\"0!2\b\b\u0002\u0010*\u001a\u00020)2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010,\u001a\u0004\u0018\u00010+2\b\b\u0002\u0010-\u001a\u00020\u00072\n\b\u0002\u0010.\u001a\u0004\u0018\u00010+2\b\b\u0002\u0010/\u001a\u00020\u00072\b\b\u0002\u00100\u001a\u00020\u00072\b\b\u0002\u00102\u001a\u0002012\b\b\u0002\u00104\u001a\u0002032\b\b\u0002\u00105\u001a\u00020\u00072\b\b\u0002\u00106\u001a\u00020\u00072\b\b\u0002\u00107\u001a\u00020\u00072\b\b\u0002\u00108\u001a\u00020\u0007ø\u0001\u0000¢\u0006\u0004\b9\u0010:J\r\u0010=\u001a\u00020<¢\u0006\u0004\b=\u0010>R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010?R\u001a\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0005\u0010@R\u001a\u0010\u0006\u001a\u00020\u00048\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0006\u0010@R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010@R\u0014\u0010\t\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010@R\u001a\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u000b\u0010AR\u001a\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\r\u0010BR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010CR$\u0010F\u001a\u0012\u0012\u0004\u0012\u00020\u00160Dj\b\u0012\u0004\u0012\u00020\u0016`E8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bF\u0010GR\u0016\u0010H\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bH\u0010IR\u0016\u0010J\u001a\u00020\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bJ\u0010CR\u0014\u0010M\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bK\u0010L\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006O"}, d2 = {"Landroidx/compose/ui/graphics/vector/ImageVector$Builder;", "", "", ContentDisposition.Parameters.Name, "Landroidx/compose/ui/unit/Dp;", "defaultWidth", "defaultHeight", "", "viewportWidth", "viewportHeight", "Landroidx/compose/ui/graphics/Color;", "tintColor", "Landroidx/compose/ui/graphics/BlendMode;", "tintBlendMode", "", "autoMirror", "<init>", "(Ljava/lang/String;FFFFJIZLkotlin/jvm/internal/h;)V", "(Ljava/lang/String;FFFFJILkotlin/jvm/internal/h;)V", "Lx6/t0;", "ensureNotConsumed", "()V", "Landroidx/compose/ui/graphics/vector/ImageVector$Builder$GroupParams;", "Landroidx/compose/ui/graphics/vector/VectorGroup;", "asVectorGroup", "(Landroidx/compose/ui/graphics/vector/ImageVector$Builder$GroupParams;)Landroidx/compose/ui/graphics/vector/VectorGroup;", "rotate", "pivotX", "pivotY", "scaleX", "scaleY", "translationX", "translationY", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "clipPathData", "addGroup", "(Ljava/lang/String;FFFFFFFLjava/util/List;)Landroidx/compose/ui/graphics/vector/ImageVector$Builder;", "clearGroup", "()Landroidx/compose/ui/graphics/vector/ImageVector$Builder;", "pathData", "Landroidx/compose/ui/graphics/PathFillType;", "pathFillType", "Landroidx/compose/ui/graphics/Brush;", "fill", "fillAlpha", "stroke", "strokeAlpha", "strokeLineWidth", "Landroidx/compose/ui/graphics/StrokeCap;", "strokeLineCap", "Landroidx/compose/ui/graphics/StrokeJoin;", "strokeLineJoin", "strokeLineMiter", "trimPathStart", "trimPathEnd", "trimPathOffset", "addPath-oIyEayM", "(Ljava/util/List;ILjava/lang/String;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/Brush;FFIIFFFF)Landroidx/compose/ui/graphics/vector/ImageVector$Builder;", "addPath", "Landroidx/compose/ui/graphics/vector/ImageVector;", "build", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "Ljava/lang/String;", "F", "J", "I", "Z", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "nodes", "Ljava/util/ArrayList;", "root", "Landroidx/compose/ui/graphics/vector/ImageVector$Builder$GroupParams;", "isConsumed", "getCurrentGroup", "()Landroidx/compose/ui/graphics/vector/ImageVector$Builder$GroupParams;", "currentGroup", "GroupParams", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        public static final int $stable = 8;
        private final boolean autoMirror;
        private final float defaultHeight;
        private final float defaultWidth;
        private boolean isConsumed;
        private final String name;
        private final ArrayList<GroupParams> nodes;
        private GroupParams root;
        private final int tintBlendMode;
        private final long tintColor;
        private final float viewportHeight;
        private final float viewportWidth;

        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0002\u0018\u00002\u00020\u0001Bu\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\u0002\u0010\u0012R \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001e\"\u0004\b\"\u0010 R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001e\"\u0004\b$\u0010 R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u001e\"\u0004\b&\u0010 R\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001e\"\u0004\b(\u0010 R\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001e\"\u0004\b*\u0010 R\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001e\"\u0004\b,\u0010 ¨\u0006-"}, d2 = {"Landroidx/compose/ui/graphics/vector/ImageVector$Builder$GroupParams;", "", ContentDisposition.Parameters.Name, "", "rotate", "", "pivotX", "pivotY", "scaleX", "scaleY", "translationX", "translationY", "clipPathData", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "children", "", "Landroidx/compose/ui/graphics/vector/VectorNode;", "(Ljava/lang/String;FFFFFFFLjava/util/List;Ljava/util/List;)V", "getChildren", "()Ljava/util/List;", "setChildren", "(Ljava/util/List;)V", "getClipPathData", "setClipPathData", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getPivotX", "()F", "setPivotX", "(F)V", "getPivotY", "setPivotY", "getRotate", "setRotate", "getScaleX", "setScaleX", "getScaleY", "setScaleY", "getTranslationX", "setTranslationX", "getTranslationY", "setTranslationY", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class GroupParams {
            private List<VectorNode> children;
            private List<? extends PathNode> clipPathData;
            private String name;
            private float pivotX;
            private float pivotY;
            private float rotate;
            private float scaleX;
            private float scaleY;
            private float translationX;
            private float translationY;

            public GroupParams() {
                this(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, null, null, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
            }

            public final List<VectorNode> getChildren() {
                return this.children;
            }

            public final List<PathNode> getClipPathData() {
                return this.clipPathData;
            }

            public final String getName() {
                return this.name;
            }

            public final float getPivotX() {
                return this.pivotX;
            }

            public final float getPivotY() {
                return this.pivotY;
            }

            public final float getRotate() {
                return this.rotate;
            }

            public final float getScaleX() {
                return this.scaleX;
            }

            public final float getScaleY() {
                return this.scaleY;
            }

            public final float getTranslationX() {
                return this.translationX;
            }

            public final float getTranslationY() {
                return this.translationY;
            }

            public final void setChildren(List<VectorNode> list) {
                this.children = list;
            }

            public final void setClipPathData(List<? extends PathNode> list) {
                this.clipPathData = list;
            }

            public final void setName(String str) {
                this.name = str;
            }

            public final void setPivotX(float f10) {
                this.pivotX = f10;
            }

            public final void setPivotY(float f10) {
                this.pivotY = f10;
            }

            public final void setRotate(float f10) {
                this.rotate = f10;
            }

            public final void setScaleX(float f10) {
                this.scaleX = f10;
            }

            public final void setScaleY(float f10) {
                this.scaleY = f10;
            }

            public final void setTranslationX(float f10) {
                this.translationX = f10;
            }

            public final void setTranslationY(float f10) {
                this.translationY = f10;
            }

            public GroupParams(String str, float f10, float f11, float f12, float f13, float f14, float f15, float f16, List<? extends PathNode> list, List<VectorNode> list2) {
                this.name = str;
                this.rotate = f10;
                this.pivotX = f11;
                this.pivotY = f12;
                this.scaleX = f13;
                this.scaleY = f14;
                this.translationX = f15;
                this.translationY = f16;
                this.clipPathData = list;
                this.children = list2;
            }

            public /* synthetic */ GroupParams(String str, float f10, float f11, float f12, float f13, float f14, float f15, float f16, List list, List list2, int i10, h hVar) {
                this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? 0.0f : f10, (i10 & 4) != 0 ? 0.0f : f11, (i10 & 8) != 0 ? 0.0f : f12, (i10 & 16) != 0 ? 1.0f : f13, (i10 & 32) != 0 ? 1.0f : f14, (i10 & 64) != 0 ? 0.0f : f15, (i10 & 128) != 0 ? 0.0f : f16, (i10 & 256) != 0 ? VectorKt.getEmptyPath() : list, (i10 & 512) != 0 ? new ArrayList() : list2);
            }
        }

        @e
        public /* synthetic */ Builder(String str, float f10, float f11, float f12, float f13, long j10, int i10, h hVar) {
            this(str, f10, f11, f12, f13, j10, i10);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Builder addGroup$default(Builder builder, String str, float f10, float f11, float f12, float f13, float f14, float f15, float f16, List list, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = "";
            }
            if ((i10 & 2) != 0) {
                f10 = 0.0f;
            }
            if ((i10 & 4) != 0) {
                f11 = 0.0f;
            }
            if ((i10 & 8) != 0) {
                f12 = 0.0f;
            }
            if ((i10 & 16) != 0) {
                f13 = 1.0f;
            }
            if ((i10 & 32) != 0) {
                f14 = 1.0f;
            }
            if ((i10 & 64) != 0) {
                f15 = 0.0f;
            }
            if ((i10 & 128) != 0) {
                f16 = 0.0f;
            }
            if ((i10 & 256) != 0) {
                list = VectorKt.getEmptyPath();
            }
            float f17 = f16;
            List list2 = list;
            float f18 = f15;
            float f19 = f13;
            return builder.addGroup(str, f10, f11, f12, f19, f14, f18, f17, list2);
        }

        /* JADX INFO: renamed from: addPath-oIyEayM$default, reason: not valid java name */
        public static /* synthetic */ Builder m4024addPathoIyEayM$default(Builder builder, List list, int i10, String str, Brush brush, float f10, Brush brush2, float f11, float f12, int i11, int i12, float f13, float f14, float f15, float f16, int i13, Object obj) {
            return builder.m4025addPathoIyEayM(list, (i13 & 2) != 0 ? VectorKt.getDefaultFillType() : i10, (i13 & 4) != 0 ? "" : str, (i13 & 8) != 0 ? null : brush, (i13 & 16) != 0 ? 1.0f : f10, (i13 & 32) == 0 ? brush2 : null, (i13 & 64) != 0 ? 1.0f : f11, (i13 & 128) != 0 ? 0.0f : f12, (i13 & 256) != 0 ? VectorKt.getDefaultStrokeLineCap() : i11, (i13 & 512) != 0 ? VectorKt.getDefaultStrokeLineJoin() : i12, (i13 & 1024) != 0 ? 4.0f : f13, (i13 & 2048) != 0 ? 0.0f : f14, (i13 & 4096) == 0 ? f15 : 1.0f, (i13 & 8192) != 0 ? 0.0f : f16);
        }

        private final VectorGroup asVectorGroup(GroupParams groupParams) {
            return new VectorGroup(groupParams.getName(), groupParams.getRotate(), groupParams.getPivotX(), groupParams.getPivotY(), groupParams.getScaleX(), groupParams.getScaleY(), groupParams.getTranslationX(), groupParams.getTranslationY(), groupParams.getClipPathData(), groupParams.getChildren());
        }

        private final void ensureNotConsumed() {
            if (this.isConsumed) {
                throw new IllegalStateException("ImageVector.Builder is single use, create a new instance to create a new ImageVector");
            }
        }

        private final GroupParams getCurrentGroup() {
            return (GroupParams) ImageVectorKt.peek(this.nodes);
        }

        public final Builder addGroup(String name, float rotate, float pivotX, float pivotY, float scaleX, float scaleY, float translationX, float translationY, List<? extends PathNode> clipPathData) {
            ensureNotConsumed();
            ImageVectorKt.push(this.nodes, new GroupParams(name, rotate, pivotX, pivotY, scaleX, scaleY, translationX, translationY, clipPathData, null, 512, null));
            return this;
        }

        /* JADX INFO: renamed from: addPath-oIyEayM, reason: not valid java name */
        public final Builder m4025addPathoIyEayM(List<? extends PathNode> pathData, int pathFillType, String name, Brush fill, float fillAlpha, Brush stroke, float strokeAlpha, float strokeLineWidth, int strokeLineCap, int strokeLineJoin, float strokeLineMiter, float trimPathStart, float trimPathEnd, float trimPathOffset) {
            ensureNotConsumed();
            getCurrentGroup().getChildren().add(new VectorPath(name, pathData, pathFillType, fill, fillAlpha, stroke, strokeAlpha, strokeLineWidth, strokeLineCap, strokeLineJoin, strokeLineMiter, trimPathStart, trimPathEnd, trimPathOffset, null));
            return this;
        }

        public final ImageVector build() {
            ensureNotConsumed();
            while (this.nodes.size() > 1) {
                clearGroup();
            }
            ImageVector imageVector = new ImageVector(this.name, this.defaultWidth, this.defaultHeight, this.viewportWidth, this.viewportHeight, asVectorGroup(this.root), this.tintColor, this.tintBlendMode, this.autoMirror, 0, 512, null);
            this.isConsumed = true;
            return imageVector;
        }

        public final Builder clearGroup() {
            ensureNotConsumed();
            getCurrentGroup().getChildren().add(asVectorGroup((GroupParams) ImageVectorKt.pop(this.nodes)));
            return this;
        }

        public /* synthetic */ Builder(String str, float f10, float f11, float f12, float f13, long j10, int i10, boolean z, h hVar) {
            this(str, f10, f11, f12, f13, j10, i10, z);
        }

        private Builder(String str, float f10, float f11, float f12, float f13, long j10, int i10, boolean z) {
            this.name = str;
            this.defaultWidth = f10;
            this.defaultHeight = f11;
            this.viewportWidth = f12;
            this.viewportHeight = f13;
            this.tintColor = j10;
            this.tintBlendMode = i10;
            this.autoMirror = z;
            ArrayList<GroupParams> arrayList = new ArrayList<>();
            this.nodes = arrayList;
            GroupParams groupParams = new GroupParams(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, null, null, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
            this.root = groupParams;
            ImageVectorKt.push(arrayList, groupParams);
        }

        public /* synthetic */ Builder(String str, float f10, float f11, float f12, float f13, long j10, int i10, boolean z, int i11, h hVar) {
            this((i11 & 1) != 0 ? "" : str, f10, f11, f12, f13, (i11 & 32) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10, (i11 & 64) != 0 ? BlendMode.INSTANCE.m3418getSrcIn0nO6VwU() : i10, (i11 & 128) != 0 ? false : z, (h) null);
        }

        public /* synthetic */ Builder(String str, float f10, float f11, float f12, float f13, long j10, int i10, int i11, h hVar) {
            this((i11 & 1) != 0 ? "" : str, f10, f11, f12, f13, (i11 & 32) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10, (i11 & 64) != 0 ? BlendMode.INSTANCE.m3418getSrcIn0nO6VwU() : i10, (h) null);
        }

        private Builder(String str, float f10, float f11, float f12, float f13, long j10, int i10) {
            this(str, f10, f11, f12, f13, j10, i10, false, (h) null);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/graphics/vector/ImageVector$Companion;", "", "()V", "imageVectorCount", "", "generateImageVectorId", "generateImageVectorId$ui_release", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final int generateImageVectorId$ui_release() {
            int i10;
            synchronized (this) {
                i10 = ImageVector.imageVectorCount;
                ImageVector.imageVectorCount = i10 + 1;
            }
            return i10;
        }

        private Companion() {
        }
    }

    public /* synthetic */ ImageVector(String str, float f10, float f11, float f12, float f13, VectorGroup vectorGroup, long j10, int i10, boolean z, int i11, h hVar) {
        this(str, f10, f11, f12, f13, vectorGroup, j10, i10, z, i11);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImageVector)) {
            return false;
        }
        ImageVector imageVector = (ImageVector) other;
        return p.a(this.name, imageVector.name) && Dp.m5683equalsimpl0(this.defaultWidth, imageVector.defaultWidth) && Dp.m5683equalsimpl0(this.defaultHeight, imageVector.defaultHeight) && this.viewportWidth == imageVector.viewportWidth && this.viewportHeight == imageVector.viewportHeight && p.a(this.root, imageVector.root) && Color.m3473equalsimpl0(this.tintColor, imageVector.tintColor) && BlendMode.m3389equalsimpl0(this.tintBlendMode, imageVector.tintBlendMode) && this.autoMirror == imageVector.autoMirror;
    }

    public final boolean getAutoMirror() {
        return this.autoMirror;
    }

    /* JADX INFO: renamed from: getDefaultHeight-D9Ej5fM, reason: not valid java name and from getter */
    public final float getDefaultHeight() {
        return this.defaultHeight;
    }

    /* JADX INFO: renamed from: getDefaultWidth-D9Ej5fM, reason: not valid java name and from getter */
    public final float getDefaultWidth() {
        return this.defaultWidth;
    }

    /* JADX INFO: renamed from: getGenId$ui_release, reason: from getter */
    public final int getGenId() {
        return this.genId;
    }

    public final String getName() {
        return this.name;
    }

    public final VectorGroup getRoot() {
        return this.root;
    }

    /* JADX INFO: renamed from: getTintBlendMode-0nO6VwU, reason: not valid java name and from getter */
    public final int getTintBlendMode() {
        return this.tintBlendMode;
    }

    /* JADX INFO: renamed from: getTintColor-0d7_KjU, reason: not valid java name and from getter */
    public final long getTintColor() {
        return this.tintColor;
    }

    public final float getViewportHeight() {
        return this.viewportHeight;
    }

    public final float getViewportWidth() {
        return this.viewportWidth;
    }

    public int hashCode() {
        return ((BlendMode.m3390hashCodeimpl(this.tintBlendMode) + c.a((this.root.hashCode() + a0.c.b(this.viewportHeight, a0.c.b(this.viewportWidth, a0.c.C(this.defaultHeight, a0.c.C(this.defaultWidth, this.name.hashCode() * 31, 31), 31), 31), 31)) * 31, 31, this.tintColor)) * 31) + (this.autoMirror ? 1231 : 1237);
    }

    private ImageVector(String str, float f10, float f11, float f12, float f13, VectorGroup vectorGroup, long j10, int i10, boolean z, int i11) {
        this.name = str;
        this.defaultWidth = f10;
        this.defaultHeight = f11;
        this.viewportWidth = f12;
        this.viewportHeight = f13;
        this.root = vectorGroup;
        this.tintColor = j10;
        this.tintBlendMode = i10;
        this.autoMirror = z;
        this.genId = i11;
    }

    public /* synthetic */ ImageVector(String str, float f10, float f11, float f12, float f13, VectorGroup vectorGroup, long j10, int i10, boolean z, int i11, int i12, h hVar) {
        this(str, f10, f11, f12, f13, vectorGroup, j10, i10, z, (i12 & 512) != 0 ? INSTANCE.generateImageVectorId$ui_release() : i11, null);
    }
}
