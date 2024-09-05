package project.evermorebakery.Helper;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import project.evermorebakery.R;
public class HelperNotification {

    public static void showNotification(Context context, String title, String content) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            Toast.makeText(context, "You don't have notifications turned on", Toast.LENGTH_SHORT).show();
            return;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        String channelId = "test";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(project.evermorebakery.R.string.app_name);
            String description = "Example Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.icon_notification)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(10, builder.build());
    }
}


//    Trong activity:
//NotificationHelper.showNotification(this, getString(R.string.app_name), "Example Notification");

//NotificationHelper.showNotification(requireContext(), getString(R.string.app_name), "Example Notification");
