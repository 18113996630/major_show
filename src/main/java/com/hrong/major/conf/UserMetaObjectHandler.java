package com.hrong.major.conf;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author hrong
 **/
@Slf4j
@Component
public class UserMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		this.setInsertFieldValByName("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {

	}
}
