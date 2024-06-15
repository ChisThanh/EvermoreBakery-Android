package project.evermorebakery.Helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

/** @noinspection SpellCheckingInspection*/
public class HelperVNPay
{
    private static final String VNP_VERSION = "2.1.0";
    private static final String VNP_COMMAND = "pay";

    private final String mTmnCode;
    private final String mHashSecret;
    private final Context mContext;

    public HelperVNPay(Context context, String tmnCode, String hashSecret)
    {
        this.mContext = context;
        this.mTmnCode = tmnCode;
        this.mHashSecret = hashSecret;
    }

    public String getPaymentUrl(Map<String, String> vnp_Params)
    {
        vnp_Params.put("vnp_Version", VNP_VERSION);
        vnp_Params.put("vnp_Command", VNP_COMMAND);

        String vnp_SecureHash = hmacSHA512(vnp_Params, mHashSecret);
        vnp_Params.put("vnp_SecureHash", vnp_SecureHash);

        StringBuilder query = new StringBuilder();
        Iterator<Map.Entry<String, String>> itr = vnp_Params.entrySet().iterator();
        while (itr.hasNext())
        {
            //noinspection rawtypes
            Map.Entry entry = itr.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();

            if (value != null && !value.isEmpty())
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    query.append(value);
                query.append('=');
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    query.append(value);
                if (itr.hasNext()) query.append('&');
            }
        }

        return "https://sandbox.vnpayment.vn/cgi-bin/vnp_Pay.cgi?" + query;
    }

    String hmacSHA512(Map<String, String> map, String hashSecret)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("HmacSHA512");
            byte[] keyBytes = hashSecret.getBytes(StandardCharsets.UTF_8);
            digest.update(keyBytes);

            for (Map.Entry<String, String> entry : map.entrySet())
            {
                String key = entry.getKey();
                String value = entry.getValue();
                if (value != null && !value.isEmpty())
                {
                    byte[] data = (key + "=" + value).getBytes(StandardCharsets.UTF_8);
                    digest.update(data);
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                return Base64.getEncoder().encodeToString(digest.digest());
        } catch (NoSuchAlgorithmException e)
        {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return null;
    }

    String generateTransactionReference()
    {
        Calendar cal = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

        return "VNPAY_" + formatter.format(cal.getTime());
    }

    public String getmTmnCode()
    {
        return mTmnCode;
    }

    public String getmHashSecret()
    {
        return mHashSecret;
    }

    public Context getmContext()
    {
        return mContext;
    }
}
