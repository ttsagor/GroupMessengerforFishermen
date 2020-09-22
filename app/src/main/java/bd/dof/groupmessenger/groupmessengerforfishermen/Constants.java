package bd.dof.groupmessenger.groupmessengerforfishermen;

/**
 * Created by sagor on 11/22/16.
 */
public class Constants {


    public interface ACTION {
        public static String MAIN_ACTION = "com.marothiatechs.foregroundservice.action.main";
        public static String INIT_ACTION = "com.marothiatechs.foregroundservice.action.init";
        public static String STOP_ACTION = "com.marothiatechs.foregroundservice.action.stop";
        public static String PLAY_ACTION = "com.marothiatechs.foregroundservice.action.play";
        public static String PAUSE_ACTION = "com.marothiatechs.foregroundservice.action.pause";
        public static String STARTFOREGROUND_ACTION = "com.marothiatechs.foregroundservice.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "com.marothiatechs.foregroundservice.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}