package project.evermorebakery.Handler;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import project.evermorebakery.Helper.HelperNotification;

public class HandlerFirebase extends FirebaseMessagingService {
    private static final String TAG = "HandlerFirebaseService";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            sendNotification(title, body);
        }
    }
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    private void sendNotification(String title, String messageBody) {
        HelperNotification.showNotification(this, title, messageBody);
    }
}

//       FirebaseMessaging.getInstance().getToken()
//               .addOnCompleteListener(new OnCompleteListener<String>() {
//@Override
//public void onComplete(@NonNull Task<String> task) {
//        if (!task.isSuccessful()) {
//        Log.w("Log", "Fetching FCM registration token failed", task.getException());
//        return;
//        }
//        // Get new FCM registration token
//        String token = task.getResult();
//        // Log and toast
//        Log.d("mgs", token);
//        }
//        });
