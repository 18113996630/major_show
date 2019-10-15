package com.hrong.major.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrong.major.dao.MajorQuestionMapper;
import com.hrong.major.dao.QuestionAnswerMapper;
import com.hrong.major.model.MajorQuestion;
import com.hrong.major.model.QuestionAnswer;
import com.hrong.major.model.vo.MajorQuestionVo;
import com.hrong.major.service.MajorQuestionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hrong
 * @since 2019-10-11
 */
@Service
public class MajorQuestionServiceImpl extends ServiceImpl<MajorQuestionMapper, MajorQuestion> implements MajorQuestionService {
	@Resource
	private QuestionAnswerMapper questionAnswerMapper;
	@Resource
	private MajorQuestionMapper majorQuestionMapper;
	@Override
	public List<MajorQuestionVo> findQuestionsByMajorId(int id) {
		List<MajorQuestion> questions = list(new QueryWrapper<MajorQuestion>().eq("major_id", id).orderByDesc("answer_count"));
		List<MajorQuestionVo> result = new ArrayList<>(questions.size());
		Random random = new Random();
		for (MajorQuestion question : questions) {
			System.out.println(question.getId()+"  "+question.getTitle());
			QuestionAnswer firstAnswer = questionAnswerMapper.selectOne(new QueryWrapper<QuestionAnswer>()
					.eq("major_question_id", question.getId())
					.orderByDesc("order_number")
					.last("limit 1"));
			String authorName = firstAnswer.getAuthorName();

			String content = firstAnswer.getContent().length() >= 45 ? firstAnswer.getContent().substring(0, random.nextInt(5) + 35) : firstAnswer.getContent();
			MajorQuestionVo vo = new MajorQuestionVo();
			vo.setAnswerCount(question.getAnswerCount());
			vo.setFirstAnswer(authorName + ": " + content + "...");
			vo.setId(question.getId());
			vo.setTitle(question.getTitle());
			result.add(vo);
		}
		return result;
	}

	@Override
	public Integer findNextMajorQuestion(int id) {
		Integer majorId = getById(id).getMajorId();
		List<MajorQuestion> questions = list(new QueryWrapper<MajorQuestion>().eq("major_id", majorId).orderByDesc("answer_count"));
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getId() == id && i < questions.size()-1) {
				return questions.get(i + 1).getId();
			}
		}
		return null;
	}

	@Override
	public List<MajorQuestion> findQuestionsPageData(Page page, String majorName) {
		return majorQuestionMapper.findQuestionsPageData(page, majorName);
	}

	@Override
	public Integer countQuestionsPageData(String majorName) {
		return majorQuestionMapper.countQuestionsPageData(majorName);
	}
}
