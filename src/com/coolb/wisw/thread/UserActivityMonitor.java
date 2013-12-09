package com.coolb.wisw.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.coolb.wisw.scope.WiswApplication;
import com.coolb.wisw.util.DateUtils;

public class UserActivityMonitor {
	
	private static final Logger log = Logger.getLogger(UserActivityMonitor.class);
	
	private static Timer monitor;

	public static void start() {
		if (monitor == null) {
			monitor = new Timer();
		}
		monitor.schedule(new CheckUserActivityTask(), 0, 60000);
	}

	public static void stop() {
		monitor.cancel();
	}
	
	private static class CheckUserActivityTask extends TimerTask {

		@Override
		public void run() {
			Map<Long, Date> userActivityRecord = WiswApplication.userActivityRecord;
			List<Long> unactivityUserList = new ArrayList<Long>();
			// 扫描不活跃用户
			for (Long userId : userActivityRecord.keySet()) {
				if (DateUtils.getDiffMinutes(new Date(), userActivityRecord.get(userId)) > 5) {
					unactivityUserList.add(userId);
				}
			}
			// 移除不活跃用户
			for (int i = 0; i < unactivityUserList.size(); i++) {
				log.debug("移除不活跃用户[" + unactivityUserList.get(i) + "]");
				WiswApplication.removeUser(unactivityUserList.get(i));
			}
		}
		
	}
	
}
