package project.evermorebakery.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.vnpay.authentication.VNP_AuthenticationActivity;
import com.vnpay.authentication.VNP_SdkCompletedCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import project.evermorebakery.Adapter.AdapterSpinner;
import project.evermorebakery.Handler.HandlerAPI;
import project.evermorebakery.Interface.InterfaceVolleyResponseListener;
import project.evermorebakery.Manager.ManagerCart;
import project.evermorebakery.Manager.ManagerDelivery;
import project.evermorebakery.Manager.ManagerNotification;
import project.evermorebakery.Manager.ManagerAccount;
import project.evermorebakery.Model.ModelCart;
import project.evermorebakery.Model.ModelDelivery;
import project.evermorebakery.Model.ModelNotification;
import project.evermorebakery.Model.ModelAccount;
import project.evermorebakery.R;

/** @noinspection SpellCheckingInspection*/
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
    ArrayList<ModelCart> cart_list;
    ModelAccount account;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        cart_list = ManagerCart.getInstance().getCartList();
        view = inflater.inflate(R.layout.fragment_payment, container, false);

        account = ManagerAccount.getInstance().getAccount();

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
        String[] bank =
        {
            "MB Bank",
            "ACB",
            "Vietcombank",
            "VietinBank",
            "BIDV",
            "Techcombank",
            "VPBank",
            "Sacombank",
            "MSB",
            "TPBank",
            "SHB",
            "Eximbank",
            "VIB",
            "BacABank",
            "SeABank",
            "OCB",
            "Nam A Bank",
            "LienVietPostBank",
            "Shinhan Bank Vietnam",
            "Hong Leong Bank Vietnam"
        };
        AdapterSpinner gender_adapter = new AdapterSpinner(requireContext(), bank);
        uSpinner_fPayment_Bank.setAdapter(gender_adapter);
    }

    String paymentMethod = "";

    @SuppressLint("UseCompatLoadingForDrawables")
    void addEvents()
    {
        uButton_fPayment_VnPay.setOnClickListener(v ->
        {
            openSdk();
            lLinear_fPayment_Payment.setVisibility(View.GONE);

            //noinspection deprecation
            uButton_fPayment_VnPay.setBackground(getResources().getDrawable(R.drawable.custom_radius_fifteen_background_fresh_green));
            //noinspection deprecation
            uButton_fPayment_Card.setBackground(getResources().getDrawable(R.drawable.custom_radius_fifteen_background_white));
            //noinspection deprecation
            uButton_fPayment_Cash.setBackground(getResources().getDrawable(R.drawable.custom_radius_fifteen_background_white));

            paymentMethod = "HelperVNPay";
        });

        uButton_fPayment_Card.setOnClickListener(view ->
        {
            lLinear_fPayment_Payment.setVisibility(View.VISIBLE);

            //noinspection deprecation
            uButton_fPayment_Card.setBackground(getResources().getDrawable(R.drawable.custom_radius_fifteen_background_fresh_green));
            //noinspection deprecation
            uButton_fPayment_VnPay.setBackground(getResources().getDrawable(R.drawable.custom_radius_fifteen_background_white));
            //noinspection deprecation
            uButton_fPayment_Cash.setBackground(getResources().getDrawable(R.drawable.custom_radius_fifteen_background_white));

            paymentMethod = "Card";
        });

        uButton_fPayment_Cash.setOnClickListener(view ->
        {
            lLinear_fPayment_Payment.setVisibility(View.GONE);

            //noinspection deprecation
            uButton_fPayment_Cash.setBackground(getResources().getDrawable(R.drawable.custom_radius_fifteen_background_fresh_green));
            //noinspection deprecation
            uButton_fPayment_VnPay.setBackground(getResources().getDrawable(R.drawable.custom_radius_fifteen_background_white));
            //noinspection deprecation
            uButton_fPayment_Card.setBackground(getResources().getDrawable(R.drawable.custom_radius_fifteen_background_white));

            paymentMethod = "On Received";
        });

        uButton_fPayment_Payment.setOnClickListener(view ->
        {
            if (!annotate()) addOrder();
        });
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aMain_Layout, fragment);
        fragment_transaction.commit();
    }

    @SuppressLint("SetTextI18n")
    boolean annotate()
    {
        boolean mistake = false;

        if (lLinear_fPayment_Payment.getVisibility() == View.VISIBLE)
        {
            if (uSpinner_fPayment_Bank.getSelectedItemPosition() == 0)
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

            if (TextUtils.isEmpty(uText_fPayment_ExpiryDate.getText().toString()) ||
                    !uText_fPayment_ExpiryDate.getText().toString().matches("(0[1-9]|1[0-2])/[0-9]{2}"))
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
        ManagerNotification.getInstance().addNotification(new
                ModelNotification("Order", "New Order", "New order is being delivered to your address"));
        loadFragment(new FragmentSuccess());
    }

    void openSdk()
    {
        Intent intent = new Intent(view.getContext(), VNP_AuthenticationActivity.class);
        intent.putExtra("url", "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html");
        intent.putExtra("tmn_code", "MSJAQ97J");
        intent.putExtra("scheme", "activitysuccess");
        intent.putExtra("is_sandbox", false);
        VNP_AuthenticationActivity.setSdkCompletedCallback((VNP_SdkCompletedCallback) action ->
                Log.wtf("SplashActivity", "action: " + action));
        startActivity(intent);
    }

    HandlerAPI handlerAPI;

    private void addOrder()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());

        if (paymentMethod.isEmpty() || paymentMethod.compareTo("") == 0)
        {
            Toast.makeText(requireContext(), "Missing Payment Method", Toast.LENGTH_SHORT).show();
            return;
        }

        Date today = new Date();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String orderDate = sdf.format(today);
        double totalAmount = ManagerCart.getInstance().getTotal();
        String status = "Processing";
        double additionalCharges = ManagerCart.getInstance().getTotal() + 30000;
        @SuppressLint("DefaultLocale")
        String query = String.format("INSERT INTO ordertable (`CUSTOMER_ID`, `ORDER_DATE`, `TOTAL_AMOUNT`, `STATUS`, `PAYMENT_METHOD`, `ADDITIONAL_CHARGES`) VALUES ('%s', '%s', %f, '%s', '%s', %f);",
                account.getCustomer(), orderDate, totalAmount, status, paymentMethod, additionalCharges);

        ManagerDelivery.setDeliveryData(new ModelDelivery(null, additionalCharges, today));

        handlerAPI = new HandlerAPI(requestQueue);
        handlerAPI.updateData(query, new InterfaceVolleyResponseListener()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                try
                {
                    JSONObject object = response.getJSONObject(0);
                    addOrderDetail(object);
                }
                catch (JSONException e)
                {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onError(String errorMessage)
            {

            }
        });
    }


    @SuppressLint("DefaultLocale")
    void addOrderDetail(JSONObject object) throws JSONException
    {
        boolean success = object.getBoolean("success");
        JSONObject data = object.getJSONObject("data");
        String orderID = data.getString("ORDER_ID");

        ManagerDelivery.getInstance().setId(orderID);

        if (success)
        {
            RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
            StringBuilder query = new StringBuilder("INSERT INTO `orderdetail` (`ORDER_ID`, `ITEM_ID`, `QUANTITY`, `PRICE`) VALUES");
            for (ModelCart orderDetail : cart_list)
            {
                query.append(String.format(" ('%s', '%s', %s, %s),",
                        orderID,
                        orderDetail.getProduct().getId(),
                        orderDetail.getQuantity(),
                        orderDetail.getPrice()));
            }
            query = new StringBuilder(query.substring(0, query.length() - 1));
            handlerAPI = new HandlerAPI(requestQueue);
            handlerAPI.updateData(query.toString(), new InterfaceVolleyResponseListener()
            {
                @Override
                public void onResponse(JSONArray response)
                {
                    try
                    {
                        JSONObject object = response.getJSONObject(0);
                        boolean success = object.getBoolean("success");
                        if (success) payment();
                    }
                    catch (JSONException e)
                    {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onError(String errorMessage)
                {
                    Log.e("ERROR", errorMessage);
                }
            });
        }
        else
        {
            String message = object.getString("message");
            Log.e("Error", "Error: " + message);
        }

    }
}