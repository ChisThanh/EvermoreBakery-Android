package project.evermorebakery.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import project.evermorebakery.Activity.ActivityMain;
import project.evermorebakery.Helper.HelperInterface;
import project.evermorebakery.R;

public class FragmentRegister extends Fragment
{
    View view;
    EditText uText_fRegister_Username;
    EditText uText_fRegister_Password;
    EditText uText_fRegister_Confirm;
    TextView vText_fRegister_UsernameAnnotation;
    TextView vText_fRegister_PasswordAnnotation;
    TextView vText_fRegister_ConfirmAnnotation;
    TextView vText_fRegister_Login;
    AppCompatButton uButton_fRegister_Register;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)

    {
        view = inflater.inflate(R.layout.fragment_register, container, false);

        addControls();
        addEvents();

        return view;
    }

    void addControls()
    {
        uText_fRegister_Username = view.findViewById(R.id.uText_fRegister_Username);
        uText_fRegister_Password = view.findViewById(R.id.uText_fRegister_Password);
        uText_fRegister_Confirm = view.findViewById(R.id.uText_fRegister_Confirm);
        vText_fRegister_UsernameAnnotation = view.findViewById(R.id.vText_fRegister_UsernameAnnotation);
        vText_fRegister_PasswordAnnotation = view.findViewById(R.id.vText_fRegister_PasswordAnnotation);
        vText_fRegister_ConfirmAnnotation = view.findViewById(R.id.vText_fRegister_ConfirmAnnotation);
        vText_fRegister_Login = view.findViewById(R.id.vText_fRegister_Login);
        uButton_fRegister_Register = view.findViewById(R.id.uButton_fRegister_Register);

        vText_fRegister_UsernameAnnotation.setText("");
        vText_fRegister_PasswordAnnotation.setText("");
        vText_fRegister_ConfirmAnnotation.setText("");

        HelperInterface.toggleVisibility(uText_fRegister_Password);
        HelperInterface.toggleVisibility(uText_fRegister_Confirm);
    }

    void addEvents()
    {
        vText_fRegister_Login.setOnClickListener(view -> loadFragment(new FragmentLogin()));

        uButton_fRegister_Register.setOnClickListener(view ->
        {
            Intent intent = new Intent(getActivity(), ActivityMain.class);
            startActivity(intent);
        });
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aStart_Layout, fragment);
        fragment_transaction.commit();
    }
}
