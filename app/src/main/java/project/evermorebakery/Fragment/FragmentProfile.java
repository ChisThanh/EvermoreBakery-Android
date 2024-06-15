package project.evermorebakery.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import project.evermorebakery.Activity.ActivityStart;
import project.evermorebakery.Adapter.AdapterSpinner;
import project.evermorebakery.Helper.HelperInterface;
import project.evermorebakery.Helper.HelperSharedPreferences;
import project.evermorebakery.Manager.ManagerAccount;
import project.evermorebakery.Model.ModelAccount;
import project.evermorebakery.R;

public class FragmentProfile extends Fragment
{
    View view;
    ImageView vImage_fProfile_Avatar;
    TextView vText_fProfile_Avatar;
    TextView vText_fProfile_Name;
    EditText uText_fProfile_Username;
    EditText uText_fProfile_Password;
    EditText uText_fProfile_Confirm;
    EditText uText_fProfile_Name;
    EditText uText_fProfile_Email;
    EditText uText_fProfile_Phone;
    EditText uText_fProfile_Address;
    Spinner uSpinner_fProfile_Gender;
    AppCompatButton uButton_fProfile_Update;
    AppCompatButton uButton_fProfile_Logout;
    ModelAccount account;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        account = ManagerAccount.getInstance().getAccount();

        addControls();
        changeEnabled();
        addSpinner();
        addData();
        addEvents();

        vImage_fProfile_Avatar.setImageResource(R.drawable.square_cat);

        HelperInterface.toggleVisibility(uText_fProfile_Password);
        HelperInterface.toggleVisibility(uText_fProfile_Confirm);

        return view;
    }

    void addControls()
    {
        vImage_fProfile_Avatar = view.findViewById(R.id.vImage_fProfile_Avatar);
        vText_fProfile_Avatar = view.findViewById(R.id.vText_fProfile_Avatar);
        vText_fProfile_Name = view.findViewById(R.id.vText_fProfile_Name);
        uText_fProfile_Username = view.findViewById(R.id.uText_fProfile_Username);
        uText_fProfile_Password = view.findViewById(R.id.uText_fProfile_Password);
        uText_fProfile_Confirm = view.findViewById(R.id.uText_fProfile_Confirm);
        uText_fProfile_Name = view.findViewById(R.id.uText_fProfile_Name);
        uText_fProfile_Email = view.findViewById(R.id.uText_fProfile_Email);
        uText_fProfile_Phone = view.findViewById(R.id.uText_fProfile_Phone);
        uText_fProfile_Address = view.findViewById(R.id.uText_fProfile_Address);
        uSpinner_fProfile_Gender = view.findViewById(R.id.uSpinner_fProfile_Gender);
        uButton_fProfile_Update = view.findViewById(R.id.uButton_fProfile_Update);
        uButton_fProfile_Logout = view.findViewById(R.id.uButton_fProfile_Logout);
    }

    void addSpinner()
    {
        String[] gender = {"Male", "Female", "Other"};
        AdapterSpinner gender_adapter = new AdapterSpinner(requireContext(), gender);
        uSpinner_fProfile_Gender.setAdapter(gender_adapter);
    }

    @SuppressLint("DiscouragedApi")
    void addData()
    {
         int drawable_id = requireContext().getResources().getIdentifier(account.getAvatar(),
                "drawable", requireContext().getPackageName());

        if(drawable_id != 0)
            Picasso.get()
                    .load(drawable_id)
                    .resize(300, 300)
                    .placeholder(R.drawable.square_cat)
                    .error(R.drawable.square_cat).into(vImage_fProfile_Avatar);
        else vImage_fProfile_Avatar.setImageResource(R.drawable.square_cat);

        vText_fProfile_Name.setText(account.getName());
        uText_fProfile_Username.setText(account.getUsername());
        uText_fProfile_Password.setText(account.getPassword());
        uText_fProfile_Confirm.setText(account.getPassword());
        uText_fProfile_Name.setText(account.getUsername());
        uText_fProfile_Email.setText(account.getEmail());
        uText_fProfile_Phone.setText(account.getPhone());
        uText_fProfile_Address.setText(account.getAddress());

        String gender = account.getGender();
        switch(gender)
        {
            case "Nam":
                uSpinner_fProfile_Gender.setSelection(0);
                break;
            case "Nữ":
                uSpinner_fProfile_Gender.setSelection(1);
                break;
            default:
                uSpinner_fProfile_Gender.setSelection(2);
                break;
        }
    }

    void changeEnabled()
    {
        uText_fProfile_Username.setEnabled(!uText_fProfile_Username.isEnabled());
        uText_fProfile_Password.setEnabled(!uText_fProfile_Password.isEnabled());
        uText_fProfile_Confirm.setEnabled(!uText_fProfile_Confirm.isEnabled());
        uText_fProfile_Name.setEnabled(!uText_fProfile_Name.isEnabled());
        uText_fProfile_Email.setEnabled(!uText_fProfile_Email.isEnabled());
        uText_fProfile_Phone.setEnabled(!uText_fProfile_Phone.isEnabled());
        uText_fProfile_Address.setEnabled(!uText_fProfile_Address.isEnabled());
        uSpinner_fProfile_Gender.setEnabled(!uSpinner_fProfile_Gender.isEnabled());
    }

    void addEvents()
    {
        vText_fProfile_Avatar.setOnClickListener(view -> {});

        uButton_fProfile_Update.setOnClickListener(view ->
        {
            changeEnabled();
            if(!uText_fProfile_Email.isEnabled())
            {
                uButton_fProfile_Update.setText(R.string.update_account);
            }
            else
            {
                uButton_fProfile_Update.setText(R.string.confirm_update);
            }
        });

        uButton_fProfile_Logout.setOnClickListener(view ->
        {
            ManagerAccount.getInstance().setAccount(null);
            HelperSharedPreferences shared_preferences = new HelperSharedPreferences(requireContext());
            shared_preferences.clearSavedAccount();
            Intent intent = new Intent(requireContext(), ActivityStart.class);
            startActivity(intent);
        });
    }
}
