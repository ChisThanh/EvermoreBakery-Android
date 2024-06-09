package project.evermorebakery.Custom;

import android.graphics.Canvas;
import android.widget.EdgeEffect;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomEdgeEffectFactory extends RecyclerView.EdgeEffectFactory
{
    @NonNull
    @Override
    protected EdgeEffect createEdgeEffect(@NonNull RecyclerView view, int direction)
    {
        return new EdgeEffect(view.getContext())
        {
            @Override
            public void onPull(float distance, float displacement)
            {

            }

            @Override
            public void onPull(float distance)
            {

            }

            @Override
            public void onRelease()
            {

            }

            @Override
            public void onAbsorb(int velocity)
            {

            }

            @Override
            public boolean draw(Canvas canvas)
            {
                return false;
            }
        };
    }
}
