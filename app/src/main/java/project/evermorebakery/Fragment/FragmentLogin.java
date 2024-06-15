package project.evermorebakery.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import project.evermorebakery.Activity.ActivityMain;
import project.evermorebakery.Handler.HandlerAPI;
import project.evermorebakery.Helper.HelperInterface;
import project.evermorebakery.Helper.HelperSharedPreferences;
import project.evermorebakery.Interface.InterfaceVolleyResponseListener;
import project.evermorebakery.Manager.ManagerAccount;
import project.evermorebakery.Model.ModelAccount;
import project.evermorebakery.R;

public class FragmentLogin extends Fragment
{
    View view;
    EditText uText_fLogin_Username;
    EditText uText_fLogin_Password;
    TextView vText_fLogin_UsernameAnnotation;
    TextView vText_fLogin_PasswordAnnotation;
    TextView vText_fLogin_Recover;
    TextView vText_fLogin_Register;
    CheckBox uCheck_fLogin_Remember;
    AppCompatButton uButton_fLogin_Login;
    HandlerAPI handler_api;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        addControls();
        addEvents();
        return view;
    }

    void addControls()
    {
        uText_fLogin_Username = view.findViewById(R.id.uText_fLogin_Username);
        uText_fLogin_Password = view.findViewById(R.id.uText_fLogin_Password);
        vText_fLogin_UsernameAnnotation = view.findViewById(R.id.vText_fLogin_UsernameAnnotation);
        vText_fLogin_PasswordAnnotation = view.findViewById(R.id.vText_fLogin_PasswordAnnotation);
        vText_fLogin_Recover = view.findViewById(R.id.vText_fLogin_Recover);
        vText_fLogin_Register = view.findViewById(R.id.vText_fLogin_Register);
        uCheck_fLogin_Remember = view.findViewById(R.id.uCheck_fLogin_Remember);
        uButton_fLogin_Login = view.findViewById(R.id.uButton_fLogin_Login);

        vText_fLogin_UsernameAnnotation.setText("");
        vText_fLogin_PasswordAnnotation.setText("");
        HelperInterface.toggleVisibility(uText_fLogin_Password);
    }

    void addEvents()
    {
        vText_fLogin_Recover.setOnClickListener(view -> loadFragment(new FragmentRecover()));

        vText_fLogin_Register.setOnClickListener(view -> loadFragment(new FragmentRegister()));

        uButton_fLogin_Login.setOnClickListener(view ->
        {
            if(annotateMissing()) return;

            getAccount();

            if(ManagerAccount.getInstance().getAccount().getId() == null) return;

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

    @SuppressLint("SetTextI18n")
    boolean annotateMissing()
    {
        boolean mistake = false;

        if (TextUtils.isEmpty(uText_fLogin_Username.getText().toString()))
        {
            vText_fLogin_UsernameAnnotation.setText("Missing Username");
            mistake = true;
        }
        else vText_fLogin_UsernameAnnotation.setText("");

        if (TextUtils.isEmpty(uText_fLogin_Password.getText().toString()))
        {
            vText_fLogin_PasswordAnnotation.setText("Missing Password");
            mistake = true;
        }
        else vText_fLogin_PasswordAnnotation.setText("");

        return mistake;
    }

    void getAccount()
    {
        String username = uText_fLogin_Username.getText().toString();
        String password = uText_fLogin_Password.getText().toString();

        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        handler_api = new HandlerAPI(requestQueue);
        String query =
                "select ac.account_id, ac.username, ac.password, cu.customer_id, cu.customer_name, cu.phone, cu.gender, cu.email, cu.address, cu.image_filename " +
                "from account ac " +
                "inner join customer cu on cu.account_id = ac.account_id " +
                "where ac.username = '" + username + "'";

        handler_api.fetchData(query, new InterfaceVolleyResponseListener()
        {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(JSONArray response)
            {
                try
                {
                    if(response.length() == 0)
                    {
                        vText_fLogin_UsernameAnnotation.setText("Incorrect Username");
                        return;
                    }
                    else vText_fLogin_UsernameAnnotation.setText("");

                    JSONObject object = response.getJSONObject(0);

                    if(!object.getString("password").equals(password))
                    {
                        vText_fLogin_PasswordAnnotation.setText("Incorrect Password");
                        return;
                    }
                    else vText_fLogin_PasswordAnnotation.setText("");

                    ModelAccount account = new ModelAccount();

                    account.setId(object.getString("account_id"));
                    account.setUsername(object.getString("username"));
                    account.setPassword(object.getString("password"));
                    account.setCustomer(object.getString("customer_id"));
                    account.setName(object.getString("customer_name"));
                    account.setPhone(object.getString("phone"));
                    account.setGender(object.getString("gender"));
                    account.setEmail(object.getString("email"));
                    account.setAddress(object.getString("address"));
                    account.setAvatar("square_cat");
                    account.setAddress("Evermore City");

                    ManagerAccount.getInstance().setAccount(account);
                    if(uCheck_fLogin_Remember.isChecked())
                    {
                        HelperSharedPreferences shared_preferences = new HelperSharedPreferences(requireContext());
                        shared_preferences.saveAccount(ManagerAccount.getInstance().getAccount());
                    }
                }
                catch (Exception exception)
                {
                    //noinspection DataFlowIssue
                    Log.e("ERROR", exception.getMessage());
                }
            }

            @Override
            public void onError(String errorMessage)
            {
                Log.e("ERROR", errorMessage);
            }
        });
    }

}
