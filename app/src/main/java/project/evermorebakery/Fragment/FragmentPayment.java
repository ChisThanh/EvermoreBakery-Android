package project.evermorebakery.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.vnpay.authentication.VNP_AuthenticationActivity;
import com.vnpay.authentication.VNP_SdkCompletedCallback;

import project.evermorebakery.Activity.ActivityMain;
import project.evermorebakery.Adapter.AdapterSpinner;
import project.evermorebakery.Manager.ManagerCart;
import project.evermorebakery.Manager.ManagerNotification;
import project.evermorebakery.Model.ModelNotification;
import project.evermorebakery.R;

public class FragmentPayment extends Fragment
{
    View view;
    AppCompatButton uButton_fPayment_VnPay;
    AppCompatButton uButton_fPayment_Card;
    AppCompatButton uButton_fPayment_Cash;
    AppCompatButton uButton_fPayment_Payment;
    LinearLayout lLinear_fPayment_Payment;
    Spinner uSpinner_fPayment_Bank;
    EditText uText_fPayment_CardNumber;
    EditText uText_fPayment_CardHolder;
    EditText uText_fPayment_CVV;
    EditText uText_fPayment_ExpiryDate;
    TextView vText_fPayment_BankAnnotation;
    TextView vText_fPayment_CardNumberAnnotation;
    TextView vText_fPayment_CardHolderAnnotation;
    TextView vText_fPayment_CVVAnnotation;
    TextView vText_fPayment_ExpiryDateAnnotation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {

        view = inflater.inflate(R.layout.fragment_payment, container, false);

        addControls();
        addSpinner();
        addEvents();

        return view;
    }

    void addControls()
    {
        uButton_fPayment_VnPay = view.findViewById(R.id.uButton_fPayment_VnPay);
        uButton_fPayment_Card = view.findViewById(R.id.uButton_fPayment_Card);
        uButton_fPayment_Cash = view.findViewById(R.id.uButton_fPayment_Cash);
        uButton_fPayment_Payment = view.findViewById(R.id.uButton_fPayment_Payment);
        lLinear_fPayment_Payment = view.findViewById(R.id.lLinear_fPayment_Payment);
        uSpinner_fPayment_Bank = view.findViewById(R.id.uSpinner_fPayment_Bank);
        uText_fPayment_CardNumber = view.findViewById(R.id.uText_fPayment_CardNumber);
        uText_fPayment_CardHolder = view.findViewById(R.id.uText_fPayment_CardHolder);
        uText_fPayment_CVV = view.findViewById(R.id.uText_fPayment_CVV);
        uText_fPayment_ExpiryDate = view.findViewById(R.id.uText_fPayment_ExpiryDate);
        vText_fPayment_BankAnnotation = view.findViewById(R.id.vText_fPayment_BankAnnotation);
        vText_fPayment_CardNumberAnnotation = view.findViewById(R.id.vText_fPayment_CardNumberAnnotation);
        vText_fPayment_CardHolderAnnotation = view.findViewById(R.id.vText_fPayment_CardHolderAnnotation);
        vText_fPayment_CVVAnnotation = view.findViewById(R.id.vText_fPayment_CVVAnnotation);
        vText_fPayment_ExpiryDateAnnotation = view.findViewById(R.id.vText_fPayment_ExpiryDateAnnotation);

        vText_fPayment_BankAnnotation.setText("");
        vText_fPayment_CardNumberAnnotation.setText("");
        vText_fPayment_CardHolderAnnotation.setText("");
        vText_fPayment_CVVAnnotation.setText("");
        vText_fPayment_ExpiryDateAnnotation.setText("");

        lLinear_fPayment_Payment.setVisibility(View.GONE);
    }

    void addSpinner()
    {
        String[] gender = {"MB Bank", "ABC Bank"};
        AdapterSpinner gender_adapter = new AdapterSpinner(requireContext(), gender);
        uSpinner_fPayment_Bank.setAdapter(gender_adapter);
    }

    void addEvents()
    {
        uButton_fPayment_VnPay.setOnClickListener(v ->
        {
            openSdk();
            lLinear_fPayment_Payment.setVisibility(View.GONE);
        });

        uButton_fPayment_Card.setOnClickListener(view -> lLinear_fPayment_Payment.setVisibility(View.VISIBLE));

        uButton_fPayment_Cash.setOnClickListener(view -> lLinear_fPayment_Payment.setVisibility(View.GONE));

        uButton_fPayment_Payment.setOnClickListener(view ->
        {
            if(!annotation()) payment();
        });
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aMain_Layout, fragment);
        fragment_transaction.commit();
    }

    @SuppressLint("SetTextI18n")
    boolean annotation()
    {
        boolean mistake = false;

        if(lLinear_fPayment_Payment.getVisibility() == View.VISIBLE)
        {
            if(uSpinner_fPayment_Bank.getSelectedItemPosition() == 0)
            {
                vText_fPayment_BankAnnotation.setText("Missing Bank");
                mistake = true;
            }
            else vText_fPayment_BankAnnotation.setText("");

            if (TextUtils.isEmpty(uText_fPayment_CardNumber.getText().toString()) || uText_fPayment_CardNumber.getText().toString().length() != 16)
            {
                vText_fPayment_CardNumberAnnotation.setText("Missing or Invalid Card Number");
                mistake = true;
            }
            else vText_fPayment_CardNumberAnnotation.setText("");

            if (TextUtils.isEmpty(uText_fPayment_CardHolder.getText().toString()))
            {
                vText_fPayment_CardNumberAnnotation.setText("Missing Card Holder");
                mistake = true;
            }
            else vText_fPayment_CardNumberAnnotation.setText("");

            if (TextUtils.isEmpty(uText_fPayment_ExpiryDate.getText().toString()) || !uText_fPayment_ExpiryDate.getText().toString().matches("(0[1-9]|1[0-2])/[0-9]{2}"))
            {
                vText_fPayment_ExpiryDateAnnotation.setText("Missing or Invalid Expiry Date");
                mistake = true;
            }
            else vText_fPayment_ExpiryDateAnnotation.setText("");

            if (TextUtils.isEmpty(uText_fPayment_CVV.getText().toString()) || uText_fPayment_CVV.getText().toString().length() != 3)
            {
                vText_fPayment_CVVAnnotation.setText("Missing or Invalid CVV");
                mistake = true;
            }
            else vText_fPayment_CVVAnnotation.setText("");
        }

        return mistake;
    }

    void payment()
    {
        ManagerCart.getInstance().clearCart();
        ActivityMain activity = (ActivityMain) getActivity();
        if (activity != null) activity.refreshActivity();
        ManagerNotification.getInstance().addNotification(new ModelNotification("Order", "New Order", "New order is being delivered to your address"));
    }

    void openSdk()
    {
        Intent intent = new Intent(view.getContext(), VNP_AuthenticationActivity.class);
        intent.putExtra("url", "https://sandbox.vnpayment.vn/testsdk/"); //bắt buộc, VNPAY cung cấp
        intent.putExtra("tmn_code", "FAHASA03"); //bắt buộc, VNPAY cung cấp
        intent.putExtra("scheme", "resultactivity"); //bắt buộc, scheme để mở lại app khi có kết quả thanh toán từ mobile banking
        intent.putExtra("is_sandbox", false); //bắt buộc, true <=> môi trường test, true <=> môi trường live
        VNP_AuthenticationActivity.setSdkCompletedCallback(new VNP_SdkCompletedCallback()
        {
            @Override
            public void sdkAction(String action)
            {
                Log.wtf("SplashActivity", "action: " + action);
                //action == AppBackAction
                //Người dùng nhấn back từ sdk để quay lại

                //action == CallMobileBankingApp
                //Người dùng nhấn chọn thanh toán qua app thanh toán (Mobile Banking, Ví...)
                //lúc này app tích hợp sẽ cần lưu lại cái PNR, khi nào người dùng mở lại app tích hợp thì sẽ gọi kiểm tra trạng thái thanh toán của PNR Đó xem đã thanh toán hay chưa.

                //action == WebBackAction
                //Người dùng nhấn back từ trang thanh toán thành công khi thanh toán qua thẻ khi url có chứa: cancel.sdk.merchantbackapp

                //action == FaildBackAction
                //giao dịch thanh toán bị failed

                //action == SuccessBackAction
                //thanh toán thành công trên webview
            }
        });
        startActivity(intent);
    }
}