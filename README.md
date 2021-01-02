# Sensor App
### Challenge that I have faced
- To Inserting the record of the sensor values in the android SQLite DB every 5 minutes. I have worked with SQLite before but the problem that I faced to make this app is to inserting the sensor value every specific time. I didn't get a proper solution or any hints over the internet that make sense to me to solve the problem. Though I had written the code from my little knowledge to inserting sensor values every 5 minutes into the SQLite database it didn't work for me.
- The problem that I have faced is to run the service in the background with notification. The problem was while I was running the service in the background with notification. It was working with notification but while I tried to display the 4 Realtime value to the notification I called the notification function into the onSensorChanged function and the notification calling in every updated value and while I removing the notification by swipe notification getting called again and again and notification bell ringing continuously that was annoying. I didn’t get the proper logic that where  I can call the notification function once that will displaye every Realtime values .

###  Things that I have learned
- I have learned how to work with built in sensor's (light sensor, proximity sensor, accelerometer senso, gyroscope sensor) in android.
- I have learned how to work with Realtime graph chart in android. I used third party graph chart (MP Android Graph).
- I have learned how to work with android built is notification. I also learn how to work with android custom notification.
- How to service run in background in android.
- During working with notification I have stacked with android custom notification. I asked question on stackoverflow and got the guideline. Learned form that to how to create custom notification. (asked question link: [a link](https://stackoverflow.com/questions/65507240/how-to-pass-the-multiple-sensor-values-to-the-notification-bar-in-android-when-t))

