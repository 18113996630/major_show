package com.hrong.analysis.source;

import ch.ethz.ssh2.Connection;
import com.hrong.analysis.entity.Log;
import com.hrong.analysis.util.LogParseUtil;
import com.hrong.analysis.util.RemoteCommandUtil;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

/**
 * @Author hrong
 * @Description
 **/
public class NginxLogSource extends RichSourceFunction<Log> {
	private static String ip = "";
	private static String user = "";
	private static String password = "";
	private static String script = "tail -n 20 /usr/local/nginx/logs/access.log";
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
				Thread.sleep(5000);
			}
		}
	}

	@Override
	public void cancel() {

	}
}
