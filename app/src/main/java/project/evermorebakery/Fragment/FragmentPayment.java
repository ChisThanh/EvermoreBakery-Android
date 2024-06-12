package project.evermorebakery.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import project.evermorebakery.Helper.HelperInterface;
import project.evermorebakery.R;

public class FragmentPayment extends Fragment
{
    View view;
    Button uButton_fPayment_Momo;
    Button uButton_fPayment_DebitCards;
    Button uButton_fPayment_Cash;
    Button uButton_fPayment_Payment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle saved_instance_state)
    {

        view = inflater.inflate(R.layout.fragment_payment, container, false);

        addControls();
        addEvents();

        return view;
    }

    void addControls()
    {
        uButton_fPayment_Momo = view.findViewById(R.id.uButton_fPayment_Momo);
        uButton_fPayment_DebitCards = view.findViewById(R.id.uButton_fPayment_DebitCards);
        uButton_fPayment_Cash = view.findViewById(R.id.uButton_fPayment_Cash);
        uButton_fPayment_Payment = view.findViewById(R.id.uButton_fPayment_Payment);
    }

    void addEvents()
    {
        uButton_fPayment_Momo.setOnClickListener(view -> {});
        uButton_fPayment_DebitCards.setOnClickListener(view -> {});
        uButton_fPayment_Cash.setOnClickListener(view -> {});
        uButton_fPayment_Payment.setOnClickListener(view -> {});
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aMain_Layout, fragment);
        fragment_transaction.commit();
    }
}