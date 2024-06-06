package project.evermorebakery.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import project.evermorebakery.Activity.ActivityContinue;
import project.evermorebakery.Helper.HelperInterface;
import project.evermorebakery.R;

public class FragmentLogin extends Fragment
{
    View view;
    EditText uText_fLogin_Email;
    EditText uText_fLogin_Password;
    TextView vText_fLogin_EmailAnnotation;
    TextView vText_fLogin_PasswordAnnotation;
    TextView vText_fLogin_Recover;
    TextView vText_fLogin_Register;
    CheckBox uCheck_fLogin_Remember;
    Button uButton_fLogin_Login;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_login, container, false);

        addControls();
        addEvents();

        vText_fLogin_EmailAnnotation.setText("");
        vText_fLogin_PasswordAnnotation.setText("");

        HelperInterface.toggleVisibility(uText_fLogin_Password);

        return view;
    }

    void addControls()
    {
        uText_fLogin_Email = view.findViewById(R.id.uText_fLogin_Email);
        uText_fLogin_Password = view.findViewById(R.id.uText_fLogin_Password);
        vText_fLogin_EmailAnnotation = view.findViewById(R.id.vText_fLogin_EmailAnnotation);
        vText_fLogin_PasswordAnnotation = view.findViewById(R.id.vText_fLogin_PasswordAnnotation);
        vText_fLogin_Recover = view.findViewById(R.id.vText_fLogin_Recover);
        vText_fLogin_Register = view.findViewById(R.id.vText_fLogin_Register);
        uCheck_fLogin_Remember = view.findViewById(R.id.uCheck_fLogin_Remember);
        uButton_fLogin_Login = view.findViewById(R.id.uButton_fLogin_Login);
    }

    void addEvents()
    {
        vText_fLogin_Recover.setOnClickListener(view -> loadFragment(new FragmentRecover()));

        vText_fLogin_Register.setOnClickListener(view -> loadFragment(new FragmentRegister()));

        uButton_fLogin_Login.setOnClickListener(view ->
        {
            Intent intent = new Intent(getActivity(), ActivityContinue.class);
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
