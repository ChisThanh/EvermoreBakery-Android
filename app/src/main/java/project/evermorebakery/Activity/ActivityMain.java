package project.evermorebakery.Activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import project.evermorebakery.Fragment.FragmentHome;
import project.evermorebakery.Fragment.FragmentMenu;
import project.evermorebakery.Fragment.FragmentProfile;
import project.evermorebakery.R;

public class ActivityMain extends AppCompatActivity
{
    TextView vText_aMain_Subtitle;
    ImageView vImage_aMain_Action;
    SearchView vSearch_aMain_Search;
    FrameLayout lFrame_aMain_Layout;
    BottomNavigationView vBottom_aMain_Navigate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        setSearchView();
        addEvents();

        loadFragment(new FragmentHome());
    }

    void addControls()
    {
        vText_aMain_Subtitle = findViewById(R.id.vText_aMain_Subtitle);
        vImage_aMain_Action = findViewById(R.id.vImage_aMain_Action);
        vSearch_aMain_Search = findViewById(R.id.vSearch_aMain_Search);
        lFrame_aMain_Layout = findViewById(R.id.lFrame_aMain_Layout);
        vBottom_aMain_Navigate = findViewById(R.id.vBottom_aMain_Navigate);
    }

    void setSearchView()
    {
        @SuppressLint("DiscouragedApi")
        int search_edittext_id = vSearch_aMain_Search.getContext()
                .getResources()
                .getIdentifier("android:id/search_src_text", null, null);
        EditText search_src_text = vSearch_aMain_Search.findViewById(search_edittext_id);

        if (search_src_text != null)
        {
            search_src_text.setTextColor(Color.rgb(139, 58, 13));
            search_src_text.setHint(R.string.search);
            search_src_text.setHintTextColor(Color.rgb(139, 58, 13));
        }
    }

    void addEvents()
    {
        vImage_aMain_Action.setOnClickListener(view -> showPopupMenu());

        vSearch_aMain_Search.setOnClickListener(view -> vSearch_aMain_Search.setIconified(false));

        vBottom_aMain_Navigate.setOnItemSelectedListener(item ->
        {
            int id = item.getItemId();
            if(id == R.id.iMenu_mMain_Home)
            {
                loadFragment(new FragmentHome());
                return true;
            }
            else if(id == R.id.iMenu_mMain_Menu)
            {
                loadFragment(new FragmentMenu());
                return true;
            }
            else if(id == R.id.iMenu_mMain_Cart)
            {
                return true;
            }
            else if(id == R.id.iMenu_mMain_Notification)
            {
                return true;
            }
            else if(id == R.id.iMenu_mMain_Profile)
            {
                loadFragment(new FragmentProfile());
                return true;
            }

            return false;
        });
    }

    @SuppressLint("RestrictedApi")
    void showPopupMenu()
    {
        ContextThemeWrapper wrapper = new ContextThemeWrapper(ActivityMain.this, R.style.PopupMenu);
        PopupMenu popup_menu = new PopupMenu(wrapper, vImage_aMain_Action);
        popup_menu.getMenuInflater().inflate(R.menu.menu_action, popup_menu.getMenu());

        try
        {
            //noinspection JavaReflectionMemberAccess
            @SuppressLint("DiscouragedPrivateApi") Field field = PopupMenu.class.getDeclaredField("mPopup");
            field.setAccessible(true);
            Object menu_popup_helper = field.get(popup_menu);
            assert menu_popup_helper != null;
            Class<?> class_popup_helper = Class.forName(menu_popup_helper.getClass().getName());
            Method set_force_icon = class_popup_helper.getMethod("setForceShowIcon", boolean.class);
            set_force_icon.invoke(menu_popup_helper, true);
        }
        catch (Exception exception)
        {
            //noinspection CallToPrintStackTrace
            exception.printStackTrace();
        }

        popup_menu.setOnMenuItemClickListener(item ->
        {
            int id = item.getItemId();

            if(id == R.id.iMenu_mAction_Profile)
            {
                vBottom_aMain_Navigate.setSelectedItemId(R.id.iMenu_mMain_Profile);
                return true;
            }
            else if(id == R.id.iMenu_mAction_Settings)
            {
                return true;
            }
            else if(id == R.id.iMenu_mAction_Help)
            {
                return true;
            }
            else if(id == R.id.iMenu_mAction_Logout)
            {
                return true;
            }

            return false;
        });

        popup_menu.show();
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getSupportFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aMain_Layout, fragment);
        fragment_transaction.commit();
    }
}
