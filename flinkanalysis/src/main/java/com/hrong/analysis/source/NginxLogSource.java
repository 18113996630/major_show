package com.hrong.analysis.source;

import ch.ethz.ssh2.Connection;
import com.hrong.analysis.entity.Log;
import com.hrong.analysis.util.LogParseUtil;
import com.hrong.analysis.util.PropertyUtil;
import com.hrong.analysis.util.RemoteCommandUtil;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author hrong
 * @Description 读取nginx日志文件
 **/
public class NginxLogSource extends RichSourceFunction<Log> {
	private static String ip = PropertyUtil.get("ip");
	private static String user = PropertyUtil.get("account");
	private static String password = PropertyUtil.get("password");
	private static String script = PropertyUtil.get("script");
	private static int interval = Integer.parseInt(PropertyUtil.get("interval"));
	private Connection connection = null;

	@Override
	public void open(Configuration parameters) throws Exception {
		this.connection = RemoteCommandUtil.login(ip, user, password);
		super.open(parameters);
	}

	@Override
	public void run(SourceContext<Log> ctx) throws Exception {
		if (connection != null) {
			long currentMaxTime = 0L;
			while (true) {
				String logString = RemoteCommandUtil.execute(connection, script);
				if (logString != null) {
					String[] logs = logString.split("\n");
					for (String originLog : logs) {
						Log log = LogParseUtil.parseLog(originLog);
						if (log.getTime() > currentMaxTime) {
							currentMaxTime = log.getTime();
							ctx.collect(log);
						}
					}
				}
				Thread.sleep(interval);
			}
		}
	}

	@Override
	public void cancel() {

	}
}
