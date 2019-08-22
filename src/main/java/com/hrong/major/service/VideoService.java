package com.hrong.major.service;

import com.hrong.major.model.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrong.major.model.vo.TopVideoAuthorVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hrong
 * @since 2019-08-17
 */
public interface VideoService extends IService<Video> {
	/**
	 * 视频前十贡献者
	 * @return vos
	 */
	List<TopVideoAuthorVo> findTopTenVideoAuthor();
}
