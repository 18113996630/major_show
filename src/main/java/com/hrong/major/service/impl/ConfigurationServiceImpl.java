package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.dao.ConfigurationMapper;
import com.hrong.major.model.Configuration;
import com.hrong.major.service.ConfigurationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hrong
 * @since 2019-08-29
 */
@Service
public class ConfigurationServiceImpl extends ServiceImpl<ConfigurationMapper, Configuration> implements ConfigurationService {

}
