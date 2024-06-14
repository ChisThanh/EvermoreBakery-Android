package project.evermorebakery.Model;

import android.content.Context;
import android.os.Build;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

public class VNPay {

    private static final String VNP_VERSION = "2.1.0";
    private static final String VNP_COMMAND = "pay";

    private String mTmnCode;
    private String mHashSecret;
    private Context mContext;

    public VNPay(Context context, String tmnCode, String hashSecret) {
        this.mContext = context;
        this.mTmnCode = tmnCode;
        this.mHashSecret = hashSecret;
    }

    /**
     * Generates the payment URL using the provided transaction data.
     *
     * @param vnp_Params A map of key-value pairs containing transaction details.
     * @return The generated payment URL.
     */
    public String getPaymentUrl(Map<String, String> vnp_Params) {
        // Add VNP_VERSION and VNP_COMMAND to the params
        vnp_Params.put("vnp_Version", VNP_VERSION);
        vnp_Params.put("vnp_Command", VNP_COMMAND);

        // Calculate the secure hash using HMAC SHA512
        String vnp_SecureHash = hmacSHA512(vnp_Params, mHashSecret);
        vnp_Params.put("vnp_SecureHash", vnp_SecureHash);

        // Build query string for payment URL
        StringBuilder query = new StringBuilder();
        Iterator itr = vnp_Params.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry entry = (Map.Entry) itr.next();
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (value != null && !value.isEmpty()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    query.append(value);
                }
                query.append('=');
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    query.append(value);
                }
                if (itr.hasNext()) {
                    query.append('&');
                }
            }
        }

        // Construct payment URL
        String vnp_PayUrl = "https://sandbox.vnpayment.vn/cgi-bin/vnp_Pay.cgi?" + query.toString(); // Sandbox URL

        return vnp_PayUrl;
    }

    /**
     * Generates a secure hash using HMAC SHA512 algorithm.
     *
     * @param map The map of key-value pairs containing transaction data.
     * @param hashSecret The VNPAY merchant's hash secret key.
     * @return The generated secure hash.
     */
    private String hmacSHA512(Map<String, String> map, String hashSecret) {
        try {
            MessageDigest digest = MessageDigest.getInstance("HmacSHA512");
            byte[] keyBytes = hashSecret.getBytes(StandardCharsets.UTF_8);
            digest.update(keyBytes);

            Iterator<Map.Entry<String, String>> itr = map.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<String, String> entry = itr.next();
                String key = entry.getKey();
                String value = entry.getValue();
                if (value != null && !value.isEmpty()) {
                    byte[] data = (key + "=" + value).getBytes(StandardCharsets.UTF_8);
                    digest.update(data);
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return Base64.getEncoder().encodeToString(digest.digest());
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Generates a unique transaction reference.
     *
     * @return The generated transaction reference.
     */
    private String generateTransactionReference() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String txnRef = "VNPAY_" + formatter.format(cal.getTime());
        return txnRef;
    }
}
