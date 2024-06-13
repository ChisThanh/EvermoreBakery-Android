package project.evermorebakery.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vnpay.authentication.VNP_AuthenticationActivity;
import com.vnpay.authentication.VNP_SdkCompletedCallback;

import project.evermorebakery.Activity.ActivityMain;
import project.evermorebakery.Manager.ManagerCart;
import project.evermorebakery.Manager.ManagerNotification;
import project.evermorebakery.Model.ModelNotification;
import project.evermorebakery.R;

public class FragmentPayment extends Fragment
{
    View view;
    AppCompatButton uButton_fPayment_VnPay;
    AppCompatButton uButton_fPayment_DebitCards;
    AppCompatButton uButton_fPayment_Cash;
    AppCompatButton uButton_fPayment_Payment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {

        view = inflater.inflate(R.layout.fragment_payment, container, false);

        addControls();
        addEvents();

        return view;
    }

    void addControls()
    {
        uButton_fPayment_VnPay = view.findViewById(R.id.uButton_fPayment_VnPay);
        uButton_fPayment_DebitCards = view.findViewById(R.id.uButton_fPayment_DebitCards);
        uButton_fPayment_Cash = view.findViewById(R.id.uButton_fPayment_Cash);
        uButton_fPayment_Payment = view.findViewById(R.id.uButton_fPayment_Payment);
    }

    void addEvents()
    {
        uButton_fPayment_VnPay.setOnClickListener(v -> openSdk());
        uButton_fPayment_DebitCards.setOnClickListener(view -> {});
        uButton_fPayment_Cash.setOnClickListener(view -> {});
        uButton_fPayment_Payment.setOnClickListener(view ->
        {
            ManagerCart.getInstance().clearCart();
            ActivityMain activity = (ActivityMain) getActivity();
            if (activity != null) activity.refreshActivity();
            ManagerNotification.getInstance().addNotification(new ModelNotification("Order", "New Order", "New order is being delivered to your address"));
        });
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aMain_Layout, fragment);
        fragment_transaction.commit();
    }

    public void openSdk()
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