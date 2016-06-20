package supermaps.supermaps.lib;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by maximilianalexander on 5/7/16.
 * This should be an abstract class that hosts the view presentable on the map.
 * It will have a reference to the annotation that it represent.
 */
public abstract class AnnotationView extends View {

    abstract Annotation getAnnotation();
    abstract void setAnnotation(Annotation annotation);

    /**
     * The User supplies a ReuseId for an AnnotationView And the mapmanager
     * just remembers them and uses them to enqueue and dequeue
     *
     * Added a default Value because we have a system of reusing the views.
     * The user may not provide a reuseID in which case all the views will get enqueued.
     *
     *
     */
    private String reuseId;

    public String getReuseId() {
        return this.reuseId == null ? "defaultID" : this.reuseId;
    }

    public void setReuseId(String reuseId) {
        this.reuseId = reuseId;
    }

    public AnnotationView(Context context) {
        super(context);
    }

    public AnnotationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnnotationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AnnotationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * NOT REQUIRED: Do not need to use this method.
     *
     * super does nothing. Stub for implementer to build if required.
     *
     * Implement this method for when the view is being reused. The reuse is based on the
     * String reuseId.
     *
     * null all the contents of the views and clear out the images and text.
     *
     * Dispose anything the view may have caused to run in background or other long running tasks.
     */
    public void prepareForReuse() {}

    /**
     * Clears the association of the annotation with the view allowing it to be reused for a
     * different annotation that has the same reuseId
     */
    public void clearAnnotationContext() {
        this.setAnnotation(null);
    }

    /**
     * Used to move the view on the screen.
     * When the center is set the view is moved to the location specified by the parameters
     *
     * @param center
     *
     * The point (xPixelPosition,yPixelPosition) is the new position for the center of the view.
     * the view is measured in pixels by the getHeight() and getWidth() methods
     * */
    public void setCenter(Point center) {
        int y = this.getHeight()/2;
        int x = this.getWidth()/2;

        this.setX(center.x-x);
        this.setY(center.y-y);

    }
}
