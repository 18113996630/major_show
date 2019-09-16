package com.hrong.major.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrong.major.model.Video;
import com.hrong.major.model.VideoNeeds;
import com.hrong.major.model.vo.Author;
import com.hrong.major.model.vo.Result;
import com.hrong.major.model.vo.VideoNeedsVo;
import com.hrong.major.model.vo.VideoVoWithMajorName;
import com.hrong.major.service.MajorDetailService;
import com.hrong.major.service.VideoNeedsService;
import com.hrong.major.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author hrong
 **/
@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminVideoController {

	@Resource
	private VideoService videoService;
	@Resource
	private MajorDetailService majorDetailService;
	@Resource
	private VideoNeedsService videoNeedsService;
	/**
	 * 表格初始化
	 */
	@ResponseBody
	@GetMapping("/videos")
	public Map<String, Object> getAllVideos(int pageNumber, int pageSize, String videoName, String majorName, String upName, String isAuth) {
		Map<String, Object> res = new HashMap<>(2);
		List<VideoVoWithMajorName> videos = videoService.findVideosByNameAndSubjectName(new Page(pageNumber, pageSize), videoName, majorName, upName, isAuth);
		int total = videoService.countVideosByNameAndSubjectName(videoName, majorName, upName, isAuth);
		res.put("total", total);
		res.put("rows", videos);
		return res;
	}

	@ResponseBody
	@GetMapping(value = "/video/{id}")
	public Object findById(@PathVariable int id) {
		return Result.success(videoService.getById(id));
	}

	@ResponseBody
	@PostMapping(value = "/video")
	public Object saveOrUpdateSubject(@RequestBody Video video) {
		return videoService.saveOrUpdateVideo(video);
	}

	@ResponseBody
	@DeleteMapping(value = "/video/{id}")
	public Object deleteSubject(@PathVariable int id) {
		videoService.removeById(id);
		return Result.success("删除成功");
	}

	@ResponseBody
	@PostMapping(value = "/video/{id}")
	public Object updateSubject(@PathVariable int id) {
		Video video = videoService.getById(id);
		if (video.getDeleted() != null) {
			int value = video.getDeleted() == 0 ? 1 : 0;
			videoService.update(video, new UpdateWrapper<Video>().eq("id", id).set("deleted", value));
		}
		return Result.success("操作成功");
	}

	@ResponseBody
	@PostMapping(value = "/videos/auth")
	public Object authVideo(String ids) {
		List<String> idsList = Arrays.asList(ids.split(","));
		List<Video> videos = videoService.list(new QueryWrapper<Video>().in("id", idsList));
		if (videos.size() != 0) {
			List<Video> updateVideos = videos.stream().peek(video -> {
				Integer isAuth = video.getIsAuth() == 1 ? 0 : 1;
				video.setIsAuth(isAuth);
			}).collect(Collectors.toList());
			videoService.saveOrUpdateBatch(updateVideos);
		}
		return Result.success("授权成功");
	}

	@ResponseBody
	@GetMapping(value = "/video/authors")
	public Object authors(int pageNumber, int pageSize ,String upName, String isAuth) {
		Map<String, Object> res = new HashMap<>(2);
		List<Author> videos = videoService.findAllAuthors(new Page(pageNumber, pageSize),upName, isAuth);
		int total = videoService.countAllAuthors(upName, isAuth);
		res.put("total", total);
		res.put("rows", videos);
		return res;
	}
	@ResponseBody
	@GetMapping(value = "/video/needsInfo")
	public Object videoNeedsInfo(int pageNumber, int pageSize) {
		Map<String, Object> res = new HashMap<>(2);
		List<VideoNeedsVo> videoNeeds = videoNeedsService.findVideoNeeds(new Page(pageNumber, pageSize));
		int total = videoNeedsService.count(new QueryWrapper<VideoNeeds>().eq("status", 0));
		res.put("total", total);
		res.put("rows", videoNeeds);
		return res;
	}


	@ResponseBody
	@PostMapping(value = "/video/need/{id}")
	public Object fixVideoNeedsInfo(@PathVariable int id) {
		VideoNeeds videoNeeds = videoNeedsService.getById(id);
		videoNeedsService.update(new UpdateWrapper<VideoNeeds>().eq("major_id", videoNeeds.getMajorId()).set("status", 1));
		return Result.success("success");
	}


}
