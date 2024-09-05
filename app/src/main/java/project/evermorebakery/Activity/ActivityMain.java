package project.evermorebakery.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import project.evermorebakery.Fragment.FragmentCart;
import project.evermorebakery.Fragment.FragmentHome;
import project.evermorebakery.Fragment.FragmentMenu;
import project.evermorebakery.Fragment.FragmentNotification;
import project.evermorebakery.Fragment.FragmentProfile;
import project.evermorebakery.Fragment.FragmentSearch;
import project.evermorebakery.Helper.HelperSharedPreferences;
import project.evermorebakery.Manager.ManagerCart;
import project.evermorebakery.Manager.ManagerNotification;
import project.evermorebakery.Manager.ManagerAccount;
import project.evermorebakery.R;

public class ActivityMain extends AppCompatActivity
{
    private final ActivityResultLauncher<String> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(),
                o -> {
                    if (o)
                        Log.d("TAG", "Post notification permission granted");
                    else
                        Log.d("TAG", "Post notification permission denied");
                });

    TextView vText_aMain_Subtitle;
    ImageView vImage_aMain_Action;
    SearchView vSearch_aMain_Search;
    FrameLayout lFrame_aMain_Layout;
    BottomNavigationView vBottom_aMain_Navigate;

    @Override
    protected void onCreate(Bundle saved_instance_state)
    {
        super.onCreate(saved_instance_state);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
                ActivityCompat.checkSelfPermission(ActivityMain.this,
                        Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            activityResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
        }


        addControls();
        setSearchView();
        addBadge();
        addEvents();

        checkReturn();
    }

    void addControls()
    {
        vText_aMain_Subtitle = findViewById(R.id.vText_aMain_Subtitle);
        vImage_aMain_Action = findViewById(R.id.vImage_aMain_Action);
        vSearch_aMain_Search = findViewById(R.id.vSearch_aMain_Search);
        lFrame_aMain_Layout = findViewById(R.id.lFrame_aMain_Layout);
        vBottom_aMain_Navigate = findViewById(R.id.vBottom_aMain_Navigate);
    }

    void checkReturn()
    {
        String location = getIntent().getStringExtra("location");
        if(location == null)
        {
            loadFragment(new FragmentHome());
            return;
        }

        switch (location)
        {
            case "menu":
                loadFragment(new FragmentMenu());
                break;
            case "cart":
                loadFragment(new FragmentCart());
                break;
            case "search":
                loadFragment(new FragmentSearch());
                break;
            default:
                loadFragment(new FragmentHome());
                break;
        }
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

        vSearch_aMain_Search.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                FragmentSearch fragment = new FragmentSearch();
                Bundle bundle = new Bundle();
                bundle.putString("search", vSearch_aMain_Search.getQuery().toString());
                fragment.setArguments(bundle);

                loadFragment(fragment);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query)
            {
                return false;
            }
        });

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
                loadFragment(new FragmentCart());
                return true;
            }
            else if(id == R.id.iMenu_mMain_Notification)
            {
                loadFragment(new FragmentNotification());
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

    /** @noinspection deprecation*/
    void addBadge()
    {
        BadgeDrawable cart_badge = vBottom_aMain_Navigate.getOrCreateBadge(R.id.iMenu_mMain_Cart);
        cart_badge.setBackgroundColor(getResources().getColor(R.color.sandstorm));
        cart_badge.setBadgeTextColor(getResources().getColor(R.color.black));
        ManagerCart.getInstance().addListener(count -> updateBadge(cart_badge, count));
        updateBadge(cart_badge, ManagerCart.getInstance().getQuantity());

        BadgeDrawable notification_badge = vBottom_aMain_Navigate.getOrCreateBadge(R.id.iMenu_mMain_Notification);
        notification_badge.setBackgroundColor(getResources().getColor(R.color.sandstorm));
        notification_badge.setBadgeTextColor(getResources().getColor(R.color.black));
        ManagerNotification.getInstance().addListener(count -> updateBadge(notification_badge, count));
        updateBadge(notification_badge, ManagerNotification.getInstance().getQuantity());
    }

    void updateBadge(BadgeDrawable badge, int count)
    {
        if(count > 0)
        {
            badge.setVisible(true);
            badge.setNumber(Math.min(count, 99));
        }
        else badge.setVisible(false);
    }

    @SuppressLint("DiscouragedPrivateApi")
    void showPopupMenu()
    {
        ContextThemeWrapper wrapper = new ContextThemeWrapper(ActivityMain.this, R.style.PopupMenu);
        PopupMenu popup_menu = new PopupMenu(wrapper, vImage_aMain_Action);
        popup_menu.getMenuInflater().inflate(R.menu.menu_action, popup_menu.getMenu());

        try
        {
            //noinspection JavaReflectionMemberAccess
            Field field = PopupMenu.class.getDeclaredField("mPopup");
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
                ManagerAccount.getInstance().setAccount(null);
                HelperSharedPreferences shared_preferences = new HelperSharedPreferences(ActivityMain.this);
                shared_preferences.clearSavedAccount();
                Intent intent = new Intent(ActivityMain.this, ActivityStart.class);
                startActivity(intent);
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

    public void refreshActivity()
    {
        @SuppressLint("UnsafeIntentLaunch")
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
