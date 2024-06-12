package project.evermorebakery.Custom;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomVerticalSpacingItemDecoration extends RecyclerView.ItemDecoration
{
    private final int SPACING;

    public CustomVerticalSpacingItemDecoration(int SPACING)
    {
        this.SPACING = SPACING;
    }

    @Override
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state)
    {
        rect.bottom = SPACING;
    }
}
