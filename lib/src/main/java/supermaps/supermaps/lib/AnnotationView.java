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
     */
    private String reuseId;

    public String getReuseId() {
        return reuseId;
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
