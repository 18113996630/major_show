package com.hrong.major.service.impl;

import com.hrong.major.model.Video;
import com.hrong.major.dao.VideoMapper;
import com.hrong.major.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hrong
 * @since 2019-08-17
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
