//Fragment Login:
//Login a User by Finding the User Data and Check the Account
//Has Text View to Open Fragment Recover and Fragment Register

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
import project.evermorebakery.R;

public class FragmentLogin extends Fragment
{
    View view; //View: Fragment Login Layout
    EditText uText_fLogin_Email; //EditText: Input Email
    EditText uText_fLogin_Password; //EditText: Input Password
    TextView vText_fLogin_EmailAnnotation; //TextView: Show Annotation when Input Email
    TextView vText_fLogin_PasswordAnnotation; //TextView: Show Annotation when Input Password
    TextView vText_fLogin_Recover; //TextView: Go to Fragment Recover
    TextView vText_fLogin_Register; //TextView: Go to Fragment Register
    Button uButton_fLogin_Login; //Button: Login

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) //Extends: Extends from Fragment
    {
        //View: Get the View of The Fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);

        addControls();
        addEvents();

        //Code: Hide Annotation when Start
        vText_fLogin_EmailAnnotation.setText("");
        vText_fLogin_PasswordAnnotation.setText("");

        return view;
    }

    void addControls() //Function: Add Controls for Easy Access
    {
        uText_fLogin_Email = view.findViewById(R.id.uText_fLogin_Email);
        uText_fLogin_Password = view.findViewById(R.id.uText_fLogin_Password);
        vText_fLogin_EmailAnnotation = view.findViewById(R.id.vText_fLogin_EmailAnnotation);
        vText_fLogin_PasswordAnnotation = view.findViewById(R.id.vText_fLogin_PasswordAnnotation);
        vText_fLogin_Recover = view.findViewById(R.id.vText_fLogin_Recover);
        vText_fLogin_Register = view.findViewById(R.id.vText_fLogin_Register);
        uButton_fLogin_Login = view.findViewById(R.id.uButton_fLogin_Login);
    }

    void addEvents() //Function: Add Events for User to Interact
    {
        //TextView: Click to Open Fragment Recover
        vText_fLogin_Recover.setOnClickListener(view -> loadFragment(new FragmentRecover()));

        //TextView: Click to Open Fragment Register
        vText_fLogin_Register.setOnClickListener(view -> loadFragment(new FragmentRegister()));

        //Button: Click to Login
        uButton_fLogin_Login.setOnClickListener(view ->
        {
            //Code: Check Login Details
            //Code: Show Annotation if Wrong
            //Code: Login (Save User Details and Open Activity Continue) If Right
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
