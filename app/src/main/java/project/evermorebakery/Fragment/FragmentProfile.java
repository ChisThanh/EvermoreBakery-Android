package project.evermorebakery.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import project.evermorebakery.Activity.ActivityStart;
import project.evermorebakery.Adapter.AdapterGender;
import project.evermorebakery.Helper.HelperInterface;
import project.evermorebakery.R;

public class FragmentProfile extends Fragment
{
    View view;
    ImageView vImage_fProfile_Avatar;
    TextView vText_fProfile_Avatar;
    TextView vText_fProfile_Name;
    EditText uText_fProfile_Email;
    EditText uText_fProfile_Password;
    EditText uText_fProfile_Confirm;
    EditText uText_fProfile_Name;
    EditText uText_fProfile_DoB;
    EditText uText_fProfile_Phone;
    EditText uText_fProfile_Address;
    Spinner uSpinner_fProfile_Gender;
    Button uButton_fProfile_Update;
    Button uButton_fProfile_Logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        addControls();
        changeEnabled();
        addSpinner();
        addEvents();

        vImage_fProfile_Avatar.setImageResource(R.drawable.square_logo);

        HelperInterface.toggleVisibility(uText_fProfile_Password);
        HelperInterface.toggleVisibility(uText_fProfile_Confirm);

        return view;
    }

    void addControls()
    {
        vImage_fProfile_Avatar = view.findViewById(R.id.vImage_fProfile_Avatar);
        vText_fProfile_Avatar = view.findViewById(R.id.vText_fProfile_Avatar);
        vText_fProfile_Name = view.findViewById(R.id.vText_fProfile_Name);
        uText_fProfile_Email = view.findViewById(R.id.uText_fProfile_Email);
        uText_fProfile_Password = view.findViewById(R.id.uText_fProfile_Password);
        uText_fProfile_Confirm = view.findViewById(R.id.uText_fProfile_Confirm);
        uText_fProfile_Name = view.findViewById(R.id.uText_fProfile_Name);
        uText_fProfile_DoB = view.findViewById(R.id.uText_fProfile_DoB);
        uText_fProfile_Phone = view.findViewById(R.id.uText_fProfile_Phone);
        uText_fProfile_Address = view.findViewById(R.id.uText_fProfile_Address);
        uSpinner_fProfile_Gender = view.findViewById(R.id.uSpinner_fProfile_Gender);
        uButton_fProfile_Update = view.findViewById(R.id.uButton_fProfile_Update);
        uButton_fProfile_Logout = view.findViewById(R.id.uButton_fProfile_Logout);
    }

    void addSpinner()
    {
        String[] gender = {"Male", "Female", "Other"};
        AdapterGender gender_adapter = new AdapterGender(requireContext(), gender);
        uSpinner_fProfile_Gender.setAdapter(gender_adapter);
    }

    void changeEnabled()
    {
        uText_fProfile_Email.setEnabled(!uText_fProfile_Email.isEnabled());
        uText_fProfile_Password.setEnabled(!uText_fProfile_Password.isEnabled());
        uText_fProfile_Confirm.setEnabled(!uText_fProfile_Confirm.isEnabled());
        uText_fProfile_Name.setEnabled(!uText_fProfile_Name.isEnabled());
        uText_fProfile_DoB.setEnabled(!uText_fProfile_DoB.isEnabled());
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
            Intent intent = new Intent(requireContext(), ActivityStart.class);
            startActivity(intent);
        });
    }
}