package project.evermorebakery.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import project.evermorebakery.Activity.ActivityContinue;
import project.evermorebakery.Helper.HelperInterface;
import project.evermorebakery.R;

public class FragmentRegister extends Fragment
{
    View view; //View: Fragment Register Layout
    EditText uText_fRegister_Email; //EditText: Input Email
    EditText uText_fRegister_Password; //EditText: Input Password
    EditText uText_fRegister_Confirm; //EditText: Input Confirm Password
    TextView vText_fRegister_EmailAnnotation; //TextView: Show Annotation when Input Email
    TextView vText_fRegister_PasswordAnnotation; //TextView: Show Annotation when Input Password
    TextView vText_fRegister_ConfirmAnnotation; //TextView: Show Annotation when Input Confirm Password
    TextView vText_fRegister_Recover; //TextView: Go to Fragment Recover
    TextView vText_fRegister_Login; //TextView: Go to Fragment Login
    Button uButton_fRegister_Register; //Button: Register

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle saved_instance_state)

    {
        //View: Get the View of The Fragment
        view = inflater.inflate(R.layout.fragment_register, container, false);

        addControls();
        addEvents();

        vText_fRegister_EmailAnnotation.setText("");
        vText_fRegister_PasswordAnnotation.setText("");
        vText_fRegister_ConfirmAnnotation.setText("");

        HelperInterface.toggleVisibility(uText_fRegister_Password);
        HelperInterface.toggleVisibility(uText_fRegister_Confirm);

        return view;
    }

    void addControls() //Function: Add Controls for Easy Access
    {
        uText_fRegister_Email = view.findViewById(R.id.uText_fRegister_Email);
        uText_fRegister_Password = view.findViewById(R.id.uText_fRegister_Password);
        uText_fRegister_Confirm = view.findViewById(R.id.uText_fRegister_Confirm);
        vText_fRegister_EmailAnnotation = view.findViewById(R.id.vText_fRegister_EmailAnnotation);
        vText_fRegister_PasswordAnnotation = view.findViewById(R.id.vText_fRegister_PasswordAnnotation);
        vText_fRegister_ConfirmAnnotation = view.findViewById(R.id.vText_fRegister_ConfirmAnnotation);
        vText_fRegister_Login = view.findViewById(R.id.vText_fRegister_Login);
        uButton_fRegister_Register = view.findViewById(R.id.uButton_fRegister_Register);
    }

    void addEvents() //Function: Add Events for User to Interact
    {
        //TextView: Click to Open Fragment Recover
        vText_fRegister_Recover.setOnClickListener(view -> loadFragment(new FragmentRecover()));

        //TextView: Click to Open Fragment Login
        vText_fRegister_Login.setOnClickListener(view -> loadFragment(new FragmentLogin()));

        //Button: Click to Register
        uButton_fRegister_Register.setOnClickListener(view ->
        {
            //Code: Check Register Details
            //Code: Show Annotation if Wrong
            //Code: Register (Save User Details and Open Activity Continue) If Right
            Intent intent = new Intent(getActivity(), ActivityContinue.class);
            startActivity(intent);
        });
    }

    void loadFragment(Fragment fragment) //Function: Load Fragment from Input Fragment into Activity Start's Frame Layout
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aStart_Layout, fragment);
        fragment_transaction.commit();
    }
}
