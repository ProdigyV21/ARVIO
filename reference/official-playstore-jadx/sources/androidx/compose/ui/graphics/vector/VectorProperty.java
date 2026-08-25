package androidx.compose.ui.graphics.vector;

import androidx.compose.ui.graphics.Brush;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0010\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013B\u0007\b\u0004¢\u0006\u0002\u0010\u0003\u0082\u0001\u0010\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#¨\u0006$"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty;", "T", "", "()V", "Fill", "FillAlpha", "PathData", "PivotX", "PivotY", "Rotation", "ScaleX", "ScaleY", "Stroke", "StrokeAlpha", "StrokeLineWidth", "TranslateX", "TranslateY", "TrimPathEnd", "TrimPathOffset", "TrimPathStart", "Landroidx/compose/ui/graphics/vector/VectorProperty$Fill;", "Landroidx/compose/ui/graphics/vector/VectorProperty$FillAlpha;", "Landroidx/compose/ui/graphics/vector/VectorProperty$PathData;", "Landroidx/compose/ui/graphics/vector/VectorProperty$PivotX;", "Landroidx/compose/ui/graphics/vector/VectorProperty$PivotY;", "Landroidx/compose/ui/graphics/vector/VectorProperty$Rotation;", "Landroidx/compose/ui/graphics/vector/VectorProperty$ScaleX;", "Landroidx/compose/ui/graphics/vector/VectorProperty$ScaleY;", "Landroidx/compose/ui/graphics/vector/VectorProperty$Stroke;", "Landroidx/compose/ui/graphics/vector/VectorProperty$StrokeAlpha;", "Landroidx/compose/ui/graphics/vector/VectorProperty$StrokeLineWidth;", "Landroidx/compose/ui/graphics/vector/VectorProperty$TranslateX;", "Landroidx/compose/ui/graphics/vector/VectorProperty$TranslateY;", "Landroidx/compose/ui/graphics/vector/VectorProperty$TrimPathEnd;", "Landroidx/compose/ui/graphics/vector/VectorProperty$TrimPathOffset;", "Landroidx/compose/ui/graphics/vector/VectorProperty$TrimPathStart;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class VectorProperty<T> {
    public static final int $stable = 0;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$Fill;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "Landroidx/compose/ui/graphics/Brush;", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Fill extends VectorProperty<Brush> {
        public static final int $stable = 0;
        public static final Fill INSTANCE = new Fill();

        private Fill() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$FillAlpha;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class FillAlpha extends VectorProperty<Float> {
        public static final int $stable = 0;
        public static final FillAlpha INSTANCE = new FillAlpha();

        private FillAlpha() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$PathData;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "", "Landroidx/compose/ui/graphics/vector/PathNode;", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class PathData extends VectorProperty<List<? extends PathNode>> {
        public static final int $stable = 0;
        public static final PathData INSTANCE = new PathData();

        private PathData() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$PivotX;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class PivotX extends VectorProperty<Float> {
        public static final int $stable = 0;
        public static final PivotX INSTANCE = new PivotX();

        private PivotX() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$PivotY;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class PivotY extends VectorProperty<Float> {
        public static final int $stable = 0;
        public static final PivotY INSTANCE = new PivotY();

        private PivotY() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$Rotation;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Rotation extends VectorProperty<Float> {
        public static final int $stable = 0;
        public static final Rotation INSTANCE = new Rotation();

        private Rotation() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$ScaleX;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ScaleX extends VectorProperty<Float> {
        public static final int $stable = 0;
        public static final ScaleX INSTANCE = new ScaleX();

        private ScaleX() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$ScaleY;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ScaleY extends VectorProperty<Float> {
        public static final int $stable = 0;
        public static final ScaleY INSTANCE = new ScaleY();

        private ScaleY() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$Stroke;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "Landroidx/compose/ui/graphics/Brush;", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Stroke extends VectorProperty<Brush> {
        public static final int $stable = 0;
        public static final Stroke INSTANCE = new Stroke();

        private Stroke() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$StrokeAlpha;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class StrokeAlpha extends VectorProperty<Float> {
        public static final int $stable = 0;
        public static final StrokeAlpha INSTANCE = new StrokeAlpha();

        private StrokeAlpha() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$StrokeLineWidth;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class StrokeLineWidth extends VectorProperty<Float> {
        public static final int $stable = 0;
        public static final StrokeLineWidth INSTANCE = new StrokeLineWidth();

        private StrokeLineWidth() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$TranslateX;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class TranslateX extends VectorProperty<Float> {
        public static final int $stable = 0;
        public static final TranslateX INSTANCE = new TranslateX();

        private TranslateX() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$TranslateY;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class TranslateY extends VectorProperty<Float> {
        public static final int $stable = 0;
        public static final TranslateY INSTANCE = new TranslateY();

        private TranslateY() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$TrimPathEnd;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class TrimPathEnd extends VectorProperty<Float> {
        public static final int $stable = 0;
        public static final TrimPathEnd INSTANCE = new TrimPathEnd();

        private TrimPathEnd() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$TrimPathOffset;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class TrimPathOffset extends VectorProperty<Float> {
        public static final int $stable = 0;
        public static final TrimPathOffset INSTANCE = new TrimPathOffset();

        private TrimPathOffset() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorProperty$TrimPathStart;", "Landroidx/compose/ui/graphics/vector/VectorProperty;", "", "()V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class TrimPathStart extends VectorProperty<Float> {
        public static final int $stable = 0;
        public static final TrimPathStart INSTANCE = new TrimPathStart();

        private TrimPathStart() {
            super(null);
        }
    }

    public /* synthetic */ VectorProperty(h hVar) {
        this();
    }

    private VectorProperty() {
    }
}
