package project.evermorebakery.Custom;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomGridSpacingItemDecoration extends RecyclerView.ItemDecoration
{
    private final int SPAN_COUNT;
    private final int SPACING;
    private final boolean INCLUDE_EDGE;

    public CustomGridSpacingItemDecoration(int SPAN_COUNT, int SPACING, boolean INCLUDE_EDGE)
    {
        this.SPAN_COUNT = SPAN_COUNT;
        this.SPACING = SPACING;
        this.INCLUDE_EDGE = INCLUDE_EDGE;
    }

    @Override
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state)
    {
        int position = parent.getChildAdapterPosition(view);
        int column = position % SPAN_COUNT;

        if (INCLUDE_EDGE)
        {
            rect.left = SPACING - column * SPACING / SPAN_COUNT;
            rect.right = (column + 1) * SPACING / SPAN_COUNT;

            if (position < SPAN_COUNT) rect.top = SPACING;
            rect.bottom = SPACING;
        }
        else
        {
            rect.left = column * SPACING / SPAN_COUNT;
            rect.right = SPACING - (column + 1) * SPACING / SPAN_COUNT;
            if (position >= SPAN_COUNT) rect.top = SPACING;
        }
    }
}