package com.hrong.major.conf;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author hrong
 **/
@Slf4j
@Component
public class UserMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		this.setInsertFieldValByName("time", LocalDateTime.now(), metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {

	}
}
