package project.evermorebakery.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

import project.evermorebakery.Activity.ActivityLoginGoogle;
import project.evermorebakery.Activity.ActivityMain;
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
    AppCompatButton uButton_fLogin_Login;
    AppCompatButton uButton_fLogin_Login_Google;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        addControls();
        addEvents();
        return view;
    }

    SignInButton signInButton;
    void addControls()
    {
        uText_fLogin_Email = view.findViewById(R.id.uText_fLogin_Email);
        uText_fLogin_Password = view.findViewById(R.id.uText_fLogin_Password);
        vText_fLogin_EmailAnnotation = view.findViewById(R.id.vText_fLogin_EmailAnnotation);
        vText_fLogin_PasswordAnnotation = view.findViewById(R.id.vText_fLogin_PasswordAnnotation);
        vText_fLogin_Recover = view.findViewById(R.id.vText_fLogin_Recover);
        vText_fLogin_Register = view.findViewById(R.id.vText_fLogin_Register);
        uButton_fLogin_Login = view.findViewById(R.id.uButton_fLogin_Login);

        vText_fLogin_EmailAnnotation.setText("");
        vText_fLogin_PasswordAnnotation.setText("");
        uButton_fLogin_Login_Google = view.findViewById(R.id.uButton_fLogin_Login_Google);
        uButton_fLogin_Login_Google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ActivityLoginGoogle.class);
                startActivity(intent);
            }
        });
        HelperInterface.toggleVisibility(uText_fLogin_Password);
    }

    void addEvents()
    {
        vText_fLogin_Recover.setOnClickListener(view -> loadFragment(new FragmentRecover()));

        vText_fLogin_Register.setOnClickListener(view -> loadFragment(new FragmentRegister()));

        uButton_fLogin_Login.setOnClickListener(view ->
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
